package by.dm13y.properties.env;

import by.dm13y.properties.env.properties.ResourceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
@RequiredArgsConstructor
public class EnvPropertiesApplication implements CommandLineRunner {
    private final ResourceProperties resourceProperties;

    public static void main(String[] args) {
        SpringApplication.run(EnvPropertiesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(resourceProperties.getEnvironments());
    }
}
