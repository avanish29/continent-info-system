package net.avanishkpandey.universum.continentservice.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public Config config() {
        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPublicAddress("192.168.1.190").addOutboundPort(5701);
        networkConfig.setPublicAddress("192.168.1.190").addOutboundPort(5702);

        Config config = new Config();
        config.setInstanceName("CONTINENT_SERVICE_CACHE");
        config.setNetworkConfig(networkConfig);
        return config;
    }
}
