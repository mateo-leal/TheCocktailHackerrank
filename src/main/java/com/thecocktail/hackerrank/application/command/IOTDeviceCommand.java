package com.thecocktail.hackerrank.application.command;

import lombok.Getter;

@Getter
public class IOTDeviceCommand {
    private int id;
    private long timestamp;
    private String status;
    private OperatingParamsCommand operatingParams;
    private AssetCommand asset;
    private AssetCommand parent;
}
