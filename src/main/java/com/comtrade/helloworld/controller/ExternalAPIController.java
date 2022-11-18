package com.comtrade.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Controller
public class ExternalAPIController {

    @GetMapping("/api/hello/{language}")
    String getExternal(Model modelMessage, @PathVariable(value = "language") String language) throws IOException, InterruptedException {

        String target = getTarget(language);

        if(target == null) {
            modelMessage.addAttribute("message", "Language doesn't exists");
        } else if(!target.equalsIgnoreCase("en")) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("Accept-Encoding", "application/gzip")
                    .header("X-RapidAPI-Key", "829bdfebb5msh119852c7aded65ep1354f8jsn508de12c7b00")
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
