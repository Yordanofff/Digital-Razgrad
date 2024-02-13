package com.holidays.h10.Resort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResortService {
    @Autowired
    private ResortTypeRepository resortTypeRepository;

    @Autowired
    private ResortRepository resortRepository;

    public void addResort(String name, Country country, List<ResortType> resortTypeListInput) {
        List<ResortType> resortTypeList = getResortTypeList(resortTypeListInput);
        Resort resort = new Resort(name, country, resortTypeList);
        resortRepository.save(resort);
    }

    private List<ResortType> getResortTypeList(List<ResortType> resortTypeListInput){
        // If the list is null - return list with single ResortType - "No data".
        List<ResortType> resortTypeList = new ArrayList<>();
        if (resortTypeListInput == null) {
            ResortType nullResortType = resortTypeRepository.findByName("Няма данни").orElse(null);
            resortTypeList.add(nullResortType);
        } else {
            resortTypeList = resortTypeListInput;
        }
        return resortTypeList;
    }
}
