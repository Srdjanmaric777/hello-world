package com.comtrade.helloworld.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Controller
@Profile("externalAPI")
public class ExternalAPIController {

    @GetMapping("/hello/{language}")
    public String getTranslationsFromAPI(Model modelMessage, @PathVariable(value = "language") String language) throws IOException, InterruptedException {

        String target = getTarget(language);

        if(target == null) {
            modelMessage.addAttribute("message", "Language doesn't exists");
        } else if(!target.equalsIgnoreCase("en")) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("Accept-Encoding", "application/gzip")
                    .header("X-RapidAPI-Key", "6e35ed755fmsh9770bc385f97d71p166555jsn172cfe22ab75")
                    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString("q=Hello%2C%20world!&target=" + target + "&source=en"))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            modelMessage.addAttribute("message", response.body().toString().split("\"translatedText\":\"")[1].split("\"}")[0]);

        }else {
            modelMessage.addAttribute("message", "Hello World!");
        }

        return "hello";
    }

    private String getTarget(String language) {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("localizations/helloworld_localization.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.split(" ")[0].equalsIgnoreCase(language))
                    return line.split(" ")[1];
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
