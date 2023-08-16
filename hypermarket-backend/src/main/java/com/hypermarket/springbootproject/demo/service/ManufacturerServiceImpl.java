package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Manufacturer;
import com.hypermarket.springbootproject.demo.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManufacturerServiceImpl implements ManufacturerService{

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer updateManufacturer(Manufacturer newManufacturer, int id) {
        Manufacturer manufacturer = getSpecificManufacturer(id);
        manufacturer.setManufacturerName(manufacturer.getManufacturerName());
        manufacturer.setCountry(manufacturer.getCountry());
        manufacturer.setContactInfo(manufacturer.getContactInfo());

        return manufacturer;
    }

    @Override
    public void deleteManufacturer(int id) {
        Manufacturer manufacturer = getSpecificManufacturer(id);
        manufacturerRepository.delete(manufacturer);
    }

    @Override
    public Manufacturer getSpecificManufacturer(int id) {
        return getAllManufacturers().get(id);
    }
}
