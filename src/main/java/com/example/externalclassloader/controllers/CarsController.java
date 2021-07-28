package com.example.externalclassloader.controllers;

import com.example.externalclassloader.dto.request.CarTuningOrder;
import com.example.externalclassloader.dto.response.Action;
import com.example.externalclassloader.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Dmitry Itskov
 */
@RestController
@RequiredArgsConstructor
public class CarsController {

    private static final String URL = "/carservice";

    @Autowired
    private CarService service;

    @PostMapping(value = URL, produces = "application/json")
    public Action tuneCar(@Valid @RequestBody CarTuningOrder order) {
        return service.tuneCar(order);
    }
}
