package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Manufacturer;
import com.hypermarket.springbootproject.demo.exception.ManufacturerNotFoundException;
import com.hypermarket.springbootproject.demo.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        try {
            return manufacturerRepository.save(manufacturer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save manufacturer", e);
        }
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        try {
            return manufacturerRepository.findAll();
        } catch (Exception e) {
            throw new ManufacturerNotFoundException("Failed to fetch manufacturers");
        }
    }

    @Override
    public Manufacturer updateManufacturer(Manufacturer newManufacturer, int id) {
        try {
            Manufacturer manufacturer = getSpecificManufacturer(id);

            manufacturer.setManufacturerName(newManufacturer.getManufacturerName());
            manufacturer.setCountry(newManufacturer.getCountry());
            manufacturer.setContactInfo(newManufacturer.getContactInfo());

            return manufacturerRepository.save(manufacturer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update manufacturer", e);
        }
    }

    @Override
    public void deleteManufacturer(int id) {
        try {
            Manufacturer manufacturer = getSpecificManufacturer(id);
            manufacturerRepository.delete(manufacturer);
        } catch (Exception e) {
            throw new ManufacturerNotFoundException("Failed to delete manufacturer");
        }
    }

    @Override
    public Manufacturer getSpecificManufacturer(int id) {
        try {
            Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);
            if (manufacturerOptional.isPresent()) {
                return manufacturerOptional.get();
            } else {
                throw new ManufacturerNotFoundException("Manufacturer not found with id: " + id);
            }
        } catch (Exception e) {
            throw new ManufacturerNotFoundException("Failed to get specific manufacturer");
        }
    }
}
