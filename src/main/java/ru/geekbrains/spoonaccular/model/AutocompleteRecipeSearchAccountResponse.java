package ru.geekbrains.spoonaccular.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data // геттеры и сеттеры на весь класс
public class AutocompleteRecipeSearchAccountResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("imageType")
    private String imageType;


    public AutocompleteRecipeSearchAccountResponse() {

    }
}

