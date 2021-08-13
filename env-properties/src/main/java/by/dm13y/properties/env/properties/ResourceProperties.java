package by.dm13y.properties.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "test-case.in")
public class ResourceProperties {
    private String environments;
}
