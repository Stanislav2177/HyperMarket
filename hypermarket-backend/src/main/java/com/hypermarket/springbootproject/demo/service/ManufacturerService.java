package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ManufacturerService {

    Manufacturer saveManufacturer(Manufacturer manufacturer);
    List<Manufacturer> getAllManufacturers();

    Manufacturer updateManufacturer(Manufacturer manufacturer, int id);

    void deleteManufacturer(int id);

    Manufacturer getSpecificManufacturer(int id);

}
