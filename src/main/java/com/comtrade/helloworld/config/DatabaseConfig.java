package com.comtrade.helloworld.config;

import com.comtrade.helloworld.model.HelloWorld;
import com.comtrade.helloworld.repository.HelloWorldRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/*
@Configuration
public class DatabaseConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            HelloWorldRepository repository) {
        return args -> {
            if(repository.findAll().isEmpty()){

                List<HelloWorld> lists = List.of(
                        new HelloWorld("English","Hello World!"),
                        new HelloWorld("German","Hallo Welt!"),
                        new HelloWorld("French","Bonjour monde!"),
                        new HelloWorld("Norwegian","Hei Verden"),
                        new HelloWorld("Romanian","Salut Lume!"),
                        new HelloWorld("Spanish","Hola Mundo!"),
                        new HelloWorld("Estonian","Tere maailm!"),
                        new HelloWorld("Finnish","Hei maailma!"),
                        new HelloWorld("Danish","Hej Verden!"),
                        new HelloWorld("Indonesian","Halo Dunia!")
                );

                for (HelloWorld helloWorld:repository.findAll()) {
                    if(!lists.contains(helloWorld))
                        repository.save(helloWorld);
                }

            }
        };
    }
}
*/