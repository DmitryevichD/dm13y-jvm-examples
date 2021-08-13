package by.dm13y.properties.env;

import by.dm13y.properties.env.properties.CamelCaseProperties;
import by.dm13y.properties.env.properties.CebabCaseProperties;
import by.dm13y.properties.env.properties.SnakeCaseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
@RequiredArgsConstructor
public class EnvPropertiesApplication implements CommandLineRunner {
    private final CebabCaseProperties cebabCaseProperties;
    private final SnakeCaseProperties snakeCaseProperties;
    private final CamelCaseProperties camelCaseProperties;

    public static void main(String[] args) {
        SpringApplication.run(EnvPropertiesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(cebabCaseProperties.getVariableCase());
        System.out.println(camelCaseProperties.getVariableCase());
        System.out.println(snakeCaseProperties.getVariableCase());
    }
}
