package com.narola.hotelbooking.StateCity;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Utility.Constant;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice(assignableTypes = UserCityController.class)
public class UserGlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handlerApplicationException(ApplicationException applicationException) {
        ModelAndView modelAndView = new ModelAndView("customerside/ErrorPage");
        modelAndView.addObject(Constant.ERROR_MSG, "From StateCityException : " + applicationException.getMessage() + "\\n" + applicationException.getClass());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerApplicationException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("customerside/ErrorPage");
        modelAndView.addObject(Constant.ERROR_MSG, "From StateCityException : " + exception.getMessage() + "\\n" + exception.getClass());
        return modelAndView;
    }
}
