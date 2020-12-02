package net.avanishkpandey.universum.continentservice;

import net.avanishkpandey.universum.commonsswagger.CommonSwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CommonSwaggerConfiguration.class})
public class ContinentInfoServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ContinentInfoServiceMain.class, args);
    }
}
