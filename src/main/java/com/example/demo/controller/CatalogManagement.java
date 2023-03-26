package com.example.demo.controller;

import com.example.demo.model.CountryDTO;
import com.example.demo.service.ICountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class CatalogManagement {

    private final ICountryService countryService;

    @Autowired
    public CatalogManagement(ICountryService ICountryService) {
        this.countryService = ICountryService;
    }

    @GetMapping("/country")
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok().body(countryService.getAllCountries());
    }

    @GetMapping("/country/refresh")
    public ResponseEntity<Void> refreshCache() {
        countryService.refreshCache();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
