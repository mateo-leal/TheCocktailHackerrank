package com.thecocktail.hackerrank.domain;

import com.thecocktail.hackerrank.application.command.IOTDeviceCommand;
import com.thecocktail.hackerrank.application.command.OperatingParamsCommand;

import java.util.List;
import java.util.stream.Collectors;

public class AverageRotorSpeedService {

    public long avgRotorSpeed(List<IOTDeviceCommand> devices) {
        return getRotorSpeeds(devices).stream()
                .mapToInt(Integer::intValue)
                .average()
                .stream().mapToLong(Math::round)
                .findFirst().orElse(0);
    }

    private List<Integer> getRotorSpeeds(List<IOTDeviceCommand> devices) {
        return devices.stream()
                .map(IOTDeviceCommand::getOperatingParams)
                .map(OperatingParamsCommand::getRotorSpeed)
                .collect(Collectors.toList());
    }
}
