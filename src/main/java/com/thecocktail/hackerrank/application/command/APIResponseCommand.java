package com.thecocktail.hackerrank.application.command;

import lombok.Getter;

import java.util.List;

@Getter
public class APIResponseCommand  {
    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private List<IOTDeviceCommand> data;
}