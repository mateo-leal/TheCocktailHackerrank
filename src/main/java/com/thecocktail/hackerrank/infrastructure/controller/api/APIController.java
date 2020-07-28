package com.thecocktail.hackerrank.infrastructure.controller.api;

import com.google.gson.Gson;
import com.thecocktail.hackerrank.application.command.APIResponseCommand;
import com.thecocktail.hackerrank.application.command.IOTDeviceCommand;
import com.thecocktail.hackerrank.infrastructure.net.Rest;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class APIController {

    private Rest rest = new Rest();

    public List<IOTDeviceCommand> getByStatusAndParentId(String statusQuery, int parentId) {
        int numberPages = 5;
        List<APIResponseCommand> responses = new ArrayList<>();
        for (int i = 0; i < numberPages; i++) {
            getByStatusAndPage(statusQuery, i)
                    .ifPresent(responses::add);
        }
        return responses.stream().map(APIResponseCommand::getData)
                .flatMap(List::stream)
                .filter(device -> Optional.ofNullable(device.getParent())
                       .filter(parent -> parent.getId() == parentId)
                       .map(p -> true)
                       .orElse(false))
                .collect(Collectors.toList());
    }

    private Optional<APIResponseCommand> getByStatusAndPage(String statusQuery, int page) {
        try {
            return Optional.ofNullable(rest.get(
                    String.format("https://jsonmock.hackerrank.com/api/iot_devices/search?status=%s&page=%s", statusQuery, page))
                    .thenApply(HttpResponse::body)
                    .thenApply(s -> new Gson().fromJson(s, APIResponseCommand.class))
                    .join());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
