package my.dal.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Value;
import io.micronaut.security.token.jwt.signature.rsa.RSASignatureConfiguration;

/**
 * SecureSignatureConfig
 */

@Named("generator") 
@Singleton
public class SecureSignatureConfig implements RSASignatureConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecureSignatureConfig.class);
    private RSAPublicKey publicKey;

    @Value("${keycloack.pub}") 
    private String pubKey;
    public String getPubKey() {return pubKey;}
    public void setPubKey(String pubKey) {this.pubKey = pubKey;}

    public SecureSignatureConfig(){
        log.info("hello 123");
        

    }
    
    @Override
    public RSAPublicKey getPublicKey() {
        if(this.publicKey == null) {
            KeyFactory kf;
            try {
                kf = KeyFactory.getInstance("RSA");
                X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(this.getPubKey()));
            this.publicKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
			}
            
        }

        log.info(this.pubKey);

		return this.publicKey;
	}



    
}