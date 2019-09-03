### FUSE with integration to RH-SSO

#### Local Testing

Pre req

- Running RH-SSO
- Realm setup (realm-export.json) 
- using a default configurations of RH SSO, you can extract the certs / keystores at ```<RH-SSO ROOT DIR>/standalone/configuration/application.keystore```

e.g.
```
keytool -export -alias server -keystore standalone/configuration/application.keystore -file ~/sso.crt

openssl x509 -inform der -in /home/virtuser/sso.crt -out /home/virtuser/sso.pem
```
To test the use case, you can get a access token from RHSSO using the following curl command. The token is stored in $access_token.

```
export access_token=$(\
curl --cacert /home/virtuser/sso.pem -vv -X POST   https://localhost:8443/auth/realms/demo-realm/protocol/openid-connect/token \
   -H 'Authorization: Basic c3ByaW5nYm9vdC1hcHA6ODlhMGM0OGEtODYwNy00OTM2LWI0MzctYmQ2MjM0MTRmNmY0' \
   -H 'content-type: application/x-www-form-urlencoded' \
   -d 'username=alice&password=password&grant_type=password&client_id=springboot-app' | jq --raw-output '.access_token' \
)
```
calling the endpoint exposed by fuse.

```
curl -H "Authorization: Bearer "$access_token -H "Content-type: application/json" -v localhost:8081/api/customers/1234

```
The route in fuse does a few things
- download the public key from RHSSO, use it to validate the token.
- create JwtResponse wrapper object that makes it easy to access the scope and claims in that token.
- store the JwtResponse object in request attribute REQ_JWT_RESPONSE


