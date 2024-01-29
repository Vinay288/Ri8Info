package com.ri8info.ri8.firebase;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import lombok.AllArgsConstructor;

@Configuration
@EnableConfigurationProperties(FireBaseConfigProperties.class)
@AllArgsConstructor
public class FireBaseConfiguration {

    private final FireBaseConfigProperties fireBaseConfigProperties;

    @PostConstruct
    public void initialize() throws IOException {
        final var properties = fireBaseConfigProperties.getFirebase();

        FileInputStream serviceAccount = new FileInputStream(properties.getFirebasePrivateKey());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

    }

    @Bean
    public Firestore firestore() {
        return FirestoreClient.getFirestore();
    }

}
