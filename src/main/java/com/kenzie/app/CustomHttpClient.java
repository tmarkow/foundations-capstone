package com.kenzie.app;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CustomHttpClient {

    public static String getDesiredURL(ClueOptions co) {

        Random rand = new Random();
        int numOfClues = 355237;
        int randClue = rand.nextInt(numOfClues + 1);
        int clueOffset = rand.nextInt(355000);

        switch (co) {
            case CLUE_LIST:
                return "https://jservice.kenzie.academy/api/clues?offset=" + clueOffset;
            case SINGLE_QUESTION:
                return "https://jservice.kenzie.academy/api/clues/" + randClue;
            case CATEGORY_LIST:
                return "https://jservice.kenzie.academy/api/categories";
            default:
                return "";
        }
    }

    public static String sendGET(String URLString) {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URLString);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public static List<CluesDTO.Clues> getClueList(String httpResponseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<CluesDTO> typeReferenceListCluesDTO = new TypeReference<>() {
        };
        CluesDTO clueResponse = objectMapper.readValue(httpResponseBody, typeReferenceListCluesDTO);

        List<CluesDTO.Clues> clueList = clueResponse.getClues();

        return clueList;
    }

//    public static List<CluesDTO.Category> getCategoryList(String httpResponseBody) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        TypeReference<CluesDTO> typeReferenceCategoryListCluesDTO = new TypeReference<>() {
//        };
//        CluesDTO categoryResponse = objectMapper.readValue(httpResponseBody, typeReferenceCategoryListCluesDTO);
//
//        List<CluesDTO.Category> categoryList = CluesDTO.Category.getTitle();
//
//    }
}

