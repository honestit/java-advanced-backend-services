package com.epam.kupisinski.jabs.springfoundation.task3;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "requestCounter")
@RequiredArgsConstructor
public class RequestCounterEndpoint {

    private final RequestCounter requestCounter;

    @ReadOperation
    public Long getCounter() {
        return requestCounter.get();
    }


}
