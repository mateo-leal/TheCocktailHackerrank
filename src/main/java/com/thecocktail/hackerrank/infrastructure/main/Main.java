package com.thecocktail.hackerrank.infrastructure.main;

import com.thecocktail.hackerrank.application.command.IOTDeviceCommand;
import com.thecocktail.hackerrank.domain.AverageRotorSpeedService;
import com.thecocktail.hackerrank.infrastructure.controller.api.APIController;

import java.io.*;
import java.util.List;

public class Main {
    private static final APIController controller = new APIController();
    private static final AverageRotorSpeedService service = new AverageRotorSpeedService();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String statusQuery = bufferedReader.readLine();
        int parentId = Integer.parseInt(bufferedReader.readLine().trim());
        List<IOTDeviceCommand> devices = controller.getByStatusAndParentId(statusQuery, parentId);
        long result = service.avgRotorSpeed(devices);
        System.out.println(result);
        bufferedReader.close();
    }
}
