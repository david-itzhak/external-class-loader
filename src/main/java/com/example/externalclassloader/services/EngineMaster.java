package com.example.externalclassloader.services;

import com.example.externalclassloader.dto.request.CarTuningOrder;
import com.example.externalclassloader.dto.response.Action;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Itskov
 */
@Component
public class EngineMaster implements CarMaster {

    @Override
    public Action doCarTuning(CarTuningOrder order) {
        System.out.println("A am adding power to " + order.getName());
        return new Action(order.getName() + " has more power");
    }

    @Override
    public String carMasterType() {
        return "engineMaster";
    }
}
