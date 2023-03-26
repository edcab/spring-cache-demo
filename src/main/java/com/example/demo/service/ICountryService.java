package com.example.demo.service;

import com.example.demo.model.CountryDTO;
import java.util.List;

public interface ICountryService {

    List<CountryDTO> getAllCountries();

    void refreshCache();
}
