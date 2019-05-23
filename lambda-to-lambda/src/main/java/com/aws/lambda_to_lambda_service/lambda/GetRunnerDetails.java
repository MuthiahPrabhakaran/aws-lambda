package com.aws.lambda_to_lambda_service.lambda;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda_to_lambda_service.data_access.RunRepository;
import com.aws.lambda_to_lambda_service.domain.Runner;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetRunnerDetails
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request,
            Context context) {
        LambdaLogger logger = context.getLogger();
        List<Runner> runnerList = new ArrayList<>();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        logger.log("Inside handleRequest()");
        try {
            runnerList = getRunnerDetails("GetRunnerStats", new APIGatewayProxyRequestEvent(),
                    new ObjectMapper());
            response.setBody(new ObjectMapper().writeValueAsString(runnerList));
            response.setStatusCode(200);
        } catch (IOException e) {
            logger.log(e.getMessage());
            response.setStatusCode(400);
            response.setBody("Error occured while fetching the runner stats");
        }
        
        URL url;
        try {
            url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();
            logger.log("connected");
        } catch (Exception e) {
            logger.log("Not connected");
        }
        
        
        return response;
    }

    public List<Runner> getRunnerDetails(String lambdaName, APIGatewayProxyRequestEvent payLoad,
            ObjectMapper mapper) throws IOException {
        AWSLambda lambdaClient = AWSLambdaClientBuilder.defaultClient();
        RunRepository runRepo = new RunRepository();
        APIGatewayProxyResponseEvent response =
                runRepo.invokeLambda(lambdaClient, mapper, lambdaName, payLoad);
        /*if(response.getStatusCode()!=200)
            throw exception*/
        return mapper.readValue(response.getBody(), new TypeReference<List<Runner>>() {});

    }

}
