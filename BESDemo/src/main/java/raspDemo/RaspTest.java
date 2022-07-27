package raspDemo;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class RaspTest {
    public Docket userApi() {
        new Docket(DocumentationType.SWAGGER_2)
                .groupName("appguard")
                .select()
                .apis(RequestHandlerSelectors.basePackage())

    }
}
