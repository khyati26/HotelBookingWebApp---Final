package com.narola.hotelbooking.StateCity.controller;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.JPA.Repository.CityRepository;
import com.narola.hotelbooking.StateCity.model.City;
import com.narola.hotelbooking.StateCity.service.ICityService;
import com.narola.hotelbooking.StateCity.service.IStateService;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.UserURLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/city")
public class AdminCityController {


    @Autowired
    private Validator cityValidator;
    @Autowired
    private IStateService stateService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(cityValidator);
    }


    @GetMapping(value = AdminURLConstant.DISPLAYALL_CITIES_URL)
    public ModelAndView displayAllCity(Locale locale) throws ApplicationException {
        ModelAndView modelAndView = new ModelAndView("admin/statecity/City");

        List<City> cityList = cityService.getAllCity()
                .stream()
                .map(cityEntity ->
                        new City(cityEntity.getState().getId(), cityEntity.getCityName(), cityEntity.getPopular(), cityEntity.getId(), cityEntity.getImage(), cityEntity.getState().getStatename()))
                .collect(Collectors.toList());

        modelAndView.addObject("cities", cityList);
//        modelAndView.addObject("title", messageSource.getMessage("city.title", null, locale));
        return modelAndView;
    }

    @GetMapping(AdminURLConstant.POPULAR_CITIES_URL)
    public ResponseEntity<String> getPopularCities(@RequestParam(value = "uncheckedids", required = false) String uncheckedids, @RequestParam(value = "checkedids", required = false) String checkedids) throws ApplicationException {
        if (!uncheckedids.isEmpty()) {
            cityService.uncheckPopularCity(uncheckedids);
        }
        if (!checkedids.isEmpty()) {
            cityService.checkPopularCity(checkedids);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("text/html;charset=UTF-8"));
        return ResponseEntity.ok().headers(httpHeaders).body("True");
    }

    @GetMapping(value = UserURLConstant.CITY_STATEWISE_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<City>> getStateWiseCities(@RequestParam("stateid") String stateId) throws ApplicationException {
        List<City> cityList = cityService.getStateWiseCities(Integer.parseInt(stateId));
        return ResponseEntity.ok().body(cityList);
    }

    @GetMapping(AdminURLConstant.ADD_CITYPAGE_URL)
    public ModelAndView getAddCityPage() throws ApplicationException {
        ModelAndView modelAndView = new ModelAndView("admin/statecity/AddCity");
        modelAndView.addObject("states", stateService.getAllState());
        return modelAndView;
    }

    @PostMapping(AdminURLConstant.ADD_CITYPAGE_URL)
    public ModelAndView addCity(@Validated City city, BindingResult bindingResult, HttpServletRequest request, Locale locale) throws ApplicationException {

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            List<String> errorList = new ArrayList<>();
            for (FieldError fieldError : fieldErrorList) {
                errorList.add(messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), locale));
            }
            ModelAndView modelAndView = new ModelAndView("admin/statecity/AddCity");
            modelAndView.addObject("states", stateService.getAllState());
            modelAndView.addObject("errMsgs", errorList);
            return modelAndView;
        } else {
            int cityId = cityService.addCity(city, request);
            ModelAndView modelAndView = new ModelAndView("redirect:cities");
            return modelAndView;
        }
    }

    @GetMapping(AdminURLConstant.EDIT_CITYPAGE_URL)
    public ModelAndView getUpdateCityPage(@RequestParam("id") String cityId) {
        int id = Integer.parseInt(cityId);
        ModelAndView modelAndView = new ModelAndView("admin/statecity/UpdateCity");
        modelAndView.addObject("states", stateService.getAllState());
        modelAndView.addObject("city", cityService.getCity(id));
        return modelAndView;
    }

    @PostMapping(AdminURLConstant.EDIT_CITYPAGE_URL)
    public ModelAndView updateCity(@Validated City city, BindingResult bindingResult, HttpServletRequest request) throws ApplicationException {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            List<String> errorList = new ArrayList<>();
            for (FieldError fieldError : fieldErrorList) {
                errorList.add(messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), Locale.getDefault()));
            }
            ModelAndView modelAndView = new ModelAndView("admin/statecity/UpdateCity");
            modelAndView.addObject("states", stateService.getAllState());
            modelAndView.addObject("city", cityService.getCity(city.getId()));
            modelAndView.addObject("errMsgs", errorList);
            return modelAndView;
        } else {
            cityService.updateCity(city, request);
            ModelAndView modelAndView = new ModelAndView("redirect:cities");
            return modelAndView;
        }
    }
}
