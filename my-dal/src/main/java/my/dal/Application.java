package my.dal;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
            title = "my-dal",
            version = "0.0"
    )
)
// @Introspected(packages="my.com.common.scalar",includedAnnotations=Entity.class)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}