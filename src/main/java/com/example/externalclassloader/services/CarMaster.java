package com.example.externalclassloader.services;

import com.example.externalclassloader.dto.request.CarTuningOrder;
import com.example.externalclassloader.dto.response.Action;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Dmitry Itskov
 */
public interface CarMaster {

    Action doCarTuning(CarTuningOrder order);

    String carMasterType();

    @Autowired
    default void registerCarMaster(CarService carService){
        carService.register(carMasterType(), this);
    }
}
