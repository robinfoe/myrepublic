# myrepublic
demo and poc

 
curl -k -v -X POST \
    -H "Content-Type: application/json" \
    -d '{"name" : "robin" , "address":"compassvale" , "email": "rfoe@redhat.com","idnumber":"s1234567H"}' \
 http://localhost:8081/api/dal/customer



 curl -k -v -X GET \
    -H "Content-Type: application/json" \
    http://localhost:8080/api/dal/customer/list/all

curl -k -v -X GET \
    -H "Content-Type: application/json" \
    http://localhost:8080/api/dal/customer/2




== KEYCLOACK docker run 


docker run -p 8080:8080 jboss/keycloak


docker run -e KEYCLOAK_USER=admin \
    -e KEYCLOAK_PASSWORD=password \
    -e KEYCLOAK_IMPORT=/hostdrive/realm-export.json \
    -p 8080:8080 \
    -v jboss-keycloack:/hostdrive \
    jboss/keycloak:7.0.0


    docker run -e KEYCLOAK_USER=<username> -e KEYCLOAK_PASSWORD=<password> \
    -e KEYCLOAK_IMPORT=/tmp/example-realm.json -v /tmp/example-realm.json:/tmp/example-realm.json jboss/keycloak

