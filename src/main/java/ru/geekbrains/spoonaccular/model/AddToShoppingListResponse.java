package ru.geekbrains.spoonaccular.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class AddToShoppingListResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("measures")
    private Measures measures;
    @JsonProperty("usages")
    private List<Object> usages = new ArrayList<Object>();
    @JsonProperty("usageRecipeIds")
    private List<Object> usageRecipeIds = new ArrayList<Object>();
    @JsonProperty("pantryItem")
    private Boolean pantryItem;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("ingredientId")
    private Integer ingredientId;

    @Data
    public static class Metric {

        @JsonProperty("amount")
        private Double amount;
        @JsonProperty("unit")
        private String unit;

    }

    @Data
    public static class Original {

        @JsonProperty("amount")
        private Double amount;
        @JsonProperty("unit")
        private String unit;

    }

    @Data
    public static class Us {

        @JsonProperty("amount")
        private Double amount;
        @JsonProperty("unit")
        private String unit;

    }

    @Data
    public class Measures {

        @JsonProperty("original")
        private Original original;
        @JsonProperty("metric")
        private Metric metric;
        @JsonProperty("us")
        private Us us;
    }
}
