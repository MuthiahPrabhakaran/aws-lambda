package com.aws.lambda_to_lambda_service.lambda;

import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda_to_lambda_service.data_access.RunRepository;
import com.aws.lambda_to_lambda_service.domain.Runner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetRunStats implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request,
            Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Inside handleRequest()");
        RunRepository runRepository = new RunRepository();
        List<Runner> runnerStats = runRepository.getRunnersList();
        
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try {
            response.setBody(new ObjectMapper().writeValueAsString(runnerStats));
            response.setStatusCode(200);
        } catch (JsonProcessingException e) {
            logger.log(e.getMessage());
            response.setStatusCode(400);
            response.setBody("Error occured while fetching the runner stats");
        }
        return response;
    }

}
