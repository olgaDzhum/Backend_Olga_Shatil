package ru.geekbrains.spoonaccular.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClassifyGroceryProductResponse {

    @JsonProperty("matched")
    public String matched;
    @JsonProperty("breadcrumbs")
    public List<String> breadcrumbs = new ArrayList<String>();
    @JsonProperty("category")
    public String category;
    @JsonProperty("usdaCode")
    public Integer usdaCode;
    @JsonProperty("image")
    public String image;
    @JsonProperty("cleanTitle")
    public String cleanTitle;

}