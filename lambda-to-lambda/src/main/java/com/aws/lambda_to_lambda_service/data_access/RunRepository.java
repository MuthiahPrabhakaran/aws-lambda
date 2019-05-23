package com.aws.lambda_to_lambda_service.data_access;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda_to_lambda_service.domain.Runner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RunRepository {

    public List<Runner> getRunnersList() {
        return Arrays.asList(new Runner("name1", 120L, 24, "B+", "emergencyContactName-1", 01L),
                new Runner("name2", 130L, 24, "B+", "emergencyContactName-1", 05L),
                new Runner("name3", 140L, 25, "O+", "emergencyContactName-2", 02L),
                new Runner("name4", 150L, 27, "B+", "emergencyContactName-3", 03L),
                new Runner("name5", 160L, 54, "B+", "emergencyContactName-4", 04L));
    }

    public APIGatewayProxyResponseEvent invokeLambda(AWSLambda lambdaClient,
            ObjectMapper objectMapper, String lambdaName, APIGatewayProxyRequestEvent request)
            throws IOException {
        InvokeRequest invokeRequest = new InvokeRequest().withFunctionName(lambdaName)
                .withPayload(objectMapper.writeValueAsString(request));
        InvokeResult invokeResult = lambdaClient.invoke(invokeRequest);
        // invokeResult.getStatusCode() --> Determines whether the lambda invoked or connection
        // problem
        return objectMapper.readValue(new String(invokeResult.getPayload().array()),
                APIGatewayProxyResponseEvent.class);
    }
}
