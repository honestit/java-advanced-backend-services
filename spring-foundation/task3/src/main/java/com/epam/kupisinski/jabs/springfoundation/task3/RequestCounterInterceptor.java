package com.epam.kupisinski.jabs.springfoundation.task3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class RequestCounterInterceptor implements HandlerInterceptor {

    private final RequestCounter requestCounter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("Request to {}", request.getRequestURI());
        requestCounter.increment();
        return true;
    }
}
