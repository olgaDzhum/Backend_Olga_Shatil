package ru.geekbrains.spoonaccular.model;

import lombok.Data;

@Data
public class ConnectUserResponse {


    private String status;
    private String username;
    private String spoonacularPassword;
    private String hash;

}
