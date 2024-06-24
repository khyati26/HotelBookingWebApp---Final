package com.narola.hotelbooking.JPA;

import com.narola.hotelbooking.JPA.Repository.CityRepository;
import com.narola.hotelbooking.JPA.Repository.StateRepository;
import com.narola.hotelbooking.StateCity.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class StateController {
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;

    @GetMapping(value = "/jpa")
    @Transactional
    public List<StateEntity> testJPA(@RequestBody State state) {
        stateRepository.save(new StateEntity(0, state.getStateName(), new Date(), new Date()));
        Optional<StateEntity> stateEntity = stateRepository.findById(1);
        cityRepository.save(new CityEntity(0, "city", stateEntity.get(), "city.jpg", 0, new Date(), new Date()));
        cityRepository.save(new CityEntity(0, "city2", stateEntity.get(), "city2.jpg", 1, new Date(), new Date()));

        System.out.println(stateRepository.findById(1));
        List<StateEntity> stateList = new ArrayList<>();
        stateList = (List<StateEntity>) stateRepository.findAll();
//    State(0, stateEntity1.getStatename())));
        return stateList;
    }
}
