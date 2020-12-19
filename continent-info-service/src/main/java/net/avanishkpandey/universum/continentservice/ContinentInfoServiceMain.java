package net.avanishkpandey.universum.continentservice;

import net.avanishkpandey.universum.commonsswagger.CommonSwaggerConfiguration;
import net.avanishkpandey.universum.continentservice.config.CacheConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableCaching
@Import({CommonSwaggerConfiguration.class, CacheConfig.class})
public class ContinentInfoServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ContinentInfoServiceMain.class, args);
    }
}
