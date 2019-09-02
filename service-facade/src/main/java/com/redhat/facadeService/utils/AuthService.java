package com.redhat.facadeService.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.facadeService.SecretService;
import com.redhat.facadeService.model.JwtResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class AuthService {
    @Autowired
    private Environment env;

    @Autowired
    private SecretService secretService;

    
    Logger log=Logger.getLogger(this.getClass().getName());
    
	private String getPublicKey() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
	                    .loadTrustMaterial(new File("/home/virtuser/rh-sso-7.3/standalone/configuration/application.keystore"))
	                    .build();
	    
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

	    CloseableHttpClient httpClient = HttpClients.custom()
	                    .setSSLSocketFactory(csf)
	                    .build();
	    HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

	    requestFactory.setHttpClient(httpClient);
	    final String uri = env.getProperty("sso.internal.endpoint");
        log.info("uri: "+uri);
		
        ResponseEntity<String> response 
        = new RestTemplate(requestFactory).exchange(
        uri, HttpMethod.GET, null, String.class);
        ObjectMapper mapper = new ObjectMapper();
        log.info(response.getBody());
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode publicKey = root.path("public_key");  
        log.info(publicKey.asText());
		//return response.getBody();
        return publicKey.asText();
	}
	
	public JwtResponse getInfo(String jwt) {
		JwtResponse jwtResponse=null;
		try {
			String publickey=getPublicKey();
			jwtResponse=parser(jwt, publickey);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jwtResponse;
	}

    public JwtResponse parser(String jwt, String publickey) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {

    	log.info(jwt);
//    	File resource = new File("/home/virtuser/rh-sso-7.3/standalone/configuration/application.keystore");
//        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
//        keystore.load(new FileInputStream(resource), "password".toCharArray());

        //Key key = keystore.getKey("server", "password".toCharArray());    	
        Jws<Claims> jws = Jwts.parser()
            //.setSigningKeyResolver(secretService.getSigningKeyResolver())
        	.setSigningKey(getKey(publickey))
            .parseClaimsJws(jwt);
        	log.info("size of claims: "+jws.getBody().entrySet().size());
        	
        	Iterator<String> i=jws.getBody().keySet().iterator();
        	while (i.hasNext()) {
            	log.info(i.next());

        	}

        	
        return new JwtResponse(jws);
    } 	

    private PublicKey getKey(String key){
        try{
            byte[] byteKey = Base64.getDecoder().decode(key);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }    
}

