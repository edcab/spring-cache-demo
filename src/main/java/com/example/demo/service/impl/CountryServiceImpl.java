package com.example.demo.service.impl;

import com.example.demo.entity.CountryEntity;
import com.example.demo.model.CountryDTO;
import com.example.demo.repository.ICountryRepository;
import com.example.demo.service.ICountryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CountryServiceImpl implements ICountryService {

    private final ICountryRepository countryRepository;

    private final CacheManager cacheManager;

    @Autowired
    public CountryServiceImpl(ICountryRepository countryRepository, CacheManager cacheManager) {
        this.countryRepository = countryRepository;
        this.cacheManager = cacheManager;
    }

    @Cacheable(value = "countries")
    @Override
    public List<CountryDTO> getAllCountries() {
        log.info("Get all countries from database");

        List<CountryEntity> collect = countryRepository.findAll();

        List<CountryDTO> collect1 = collect.stream().map(this::convertToDTO)
            .collect(Collectors.toList());

        return collect1;
    }

    @Override
    public void refreshCache() {
        log.info("Refresh cache");
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();
        }
    }

    private CountryDTO convertToDTO(CountryEntity countryEntity) {
        return new CountryDTO(countryEntity.getId(), countryEntity.getName());
    }
}
