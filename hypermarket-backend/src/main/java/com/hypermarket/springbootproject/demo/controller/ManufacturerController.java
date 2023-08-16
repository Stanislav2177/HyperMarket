package com.hypermarket.springbootproject.demo.controller;

import com.hypermarket.springbootproject.demo.entity.Manufacturer;
import com.hypermarket.springbootproject.demo.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController {
    public final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping("/post")
    public ResponseEntity<Manufacturer> saveManufacturer(@RequestBody Manufacturer manufacturer){
        Manufacturer manufacturer1 = manufacturerService.saveManufacturer(manufacturer);

        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturer1);
    }

    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
        return ResponseEntity.ok(manufacturerService.getAllManufacturers());
    }


    @PostMapping("/update")
    public ResponseEntity<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer, int id){
        return ResponseEntity.ok(manufacturerService.updateManufacturer(manufacturer, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable int id){
        manufacturerService.deleteManufacturer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getSpecificManufacturer(@PathVariable int id){
        Manufacturer specificManufacturer = manufacturerService.getSpecificManufacturer(id);

        return ResponseEntity.ok(specificManufacturer);
    }

}
