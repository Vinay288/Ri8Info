package com.ri8info.ri8;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ri8info.ri8.service.NewsService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
@CrossOrigin("*")
public class NewsController {
    private NewsService newsService;
    @GetMapping("/form")
    public ModelAndView showForm() {
        return new ModelAndView("articleForm", "newsEntity", new NewsEntity());
    }

    @PostMapping("/form")
    public ResponseEntity<?> submitForm(NewsEntity user) {
        System.out.println("Submitted Article: " + user.getTitle() + ", " + user.getAuthor() + ", " + user.getContent());
        if(!StringUtils.equals(user.getPassword(),"Qwerty@123")) {
            return ResponseEntity.ok("Invalid Password");
        }
        return newsService.createArticle(user);
    }

    @GetMapping("/show")
    @ResponseBody
    public String showNewsArticles() throws ExecutionException, InterruptedException {
        List<NewsEntity> newsEntities = newsService.getAllArticles();
        Gson gson = new GsonBuilder().create();

        JsonArray jsonArray = new JsonArray();
        for (NewsEntity newss : newsEntities) {
            JsonObject jsonObject = gson.toJsonTree(newss).getAsJsonObject();
            jsonArray.add(jsonObject);
        }

        // Set pretty printing using JsonWriter
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(jsonArray);
    }
}
