package com.narola.hotelbooking;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CustomLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        System.out.println("in customlocal resolver");
        if (request.getParameter("lang") == null) {
            return Locale.getDefault();
        }
        return new Locale(request.getParameter("lang"));
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        if (!response.isCommitted()) {
            response.setLocale(locale);
            response.setCharacterEncoding("UTF-8");
        }
    }
}
