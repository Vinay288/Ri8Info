package com.ri8info.ri8.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.ri8info.ri8.NewsEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class NewsService {
    private final Firestore firestore;

    public ResponseEntity<?> createArticle(final NewsEntity news) {
        final var response = new JSONObject();
        final var movieId = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
        String createdAt = news.getCreatedAt();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(createdAt, inputFormatter);

        // Format the LocalDateTime using the desired output format and add time zone information
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String outputDateTimeString = dateTime.atOffset(ZoneOffset.UTC).format(outputFormatter);
        news.setCreatedAt(outputDateTimeString);


        firestore.collection("news").document(movieId).set(news);

        response.put("id", movieId);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response.toString());
    }

    public List<NewsEntity> getAllArticles() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querySnapshot = firestore.collection("news").get();
        List<NewsEntity> newsEntities = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            // Convert document data to Java object
            newsEntities.add(documentToObject(document));

            // Use the Java object as needed
        }
        return newsEntities;
    }
    private static <T> T documentToObject(QueryDocumentSnapshot document) {
        Gson gson = new Gson();
        String json = gson.toJson(document.getData());
        return gson.fromJson(json, (Class<T>) NewsEntity.class);
    }
}
