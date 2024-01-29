package com.ri8info.ri8.firebase;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "com.ri8info.ri8")
@Configuration
@Data
public class FireBaseConfigProperties {

    private Configuration firebase = new Configuration();

    @Data
    public class Configuration {

        private String firebasePrivateKey;

    }

}