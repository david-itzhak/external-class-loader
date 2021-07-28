package com.example.externalclassloader.services;

import com.example.externalclassloader.dto.request.CarTuningOrder;
import com.example.externalclassloader.dto.response.Action;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Itskov
 */
@Component
public class Boss implements CarMaster{

    @Override
    public Action doCarTuning(CarTuningOrder order) {
        System.out.println("Some fool sent an order, but didn't say what he want.");
        return new Action("What are you want?");
    }

    @Override
    public String carMasterType() {
        return "boss";
    }
}
