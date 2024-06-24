package com.narola.hotelbooking.Hotel.controller;

import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @GetMapping(AdminURLConstant.DISPLAYALL_HOTELS_URL)
    public ModelAndView getAllHotel() {
        ModelAndView modelAndView = new ModelAndView("admin/hotel/ShowAllHotels");
        modelAndView.addObject("hotels", hotelService.getAllHotel());
        return modelAndView;
    }

    @GetMapping(AdminURLConstant.VIEW_HOTELPAGE_URL)
    public ModelAndView viewHotel(@RequestParam("id") int hotelId) {
        ModelAndView modelAndView = new ModelAndView("admin/hotel/ViewHotel");
        modelAndView.addObject("hotel", hotelService.getHotel(hotelId));
        return modelAndView;
    }
}
