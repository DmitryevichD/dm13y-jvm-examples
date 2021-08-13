package by.dm13y.properties.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * see ENV example in the test.env
 */
@Data
@Component
@ConfigurationProperties(prefix = "env.camel-case")
public class CamelCaseProperties {
    private String variableCase;
}
