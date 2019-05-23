package com.aws.lambda_to_lambda_service.lambda;

import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda_to_lambda_service.domain.Runner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetRunnerDetails implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request,
            Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Inside handleRequest()");
        
        return null;
    }
    
    public List<Runner> getRunnerDetails(String lambdaName, APIGatewayProxyResponseEvent payLoad, ObjectMapper mapper){
        return null;
    }

}
