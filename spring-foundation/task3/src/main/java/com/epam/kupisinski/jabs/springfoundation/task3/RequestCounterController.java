package com.epam.kupisinski.jabs.springfoundation.task3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCounterController {

    @GetMapping("/dummy")
    public void dummyRequest() {
    }
}
