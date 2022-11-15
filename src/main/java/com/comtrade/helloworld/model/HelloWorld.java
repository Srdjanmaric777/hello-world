package com.comtrade.helloworld.model;

import javax.persistence.*;

@Entity
@Table(name = "Languages")
public class HelloWorld {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private String word;

    public HelloWorld() {
    }

    public HelloWorld(String language, String word) {
        this.language = language;
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "HelloWorld{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}
