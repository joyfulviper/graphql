package com.asd.asdfasdfasdfa.global;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler {

    @GraphQlExceptionHandler(Exception.class)
    public GraphQLError handleException(Throwable e) {
        return GraphQLError.newError()
                .errorType(ErrorClassification.errorClassification("INTERNAL_SERVER_ERROR"))
                .message("Internal server error occurred")
                .build();
    }
}
