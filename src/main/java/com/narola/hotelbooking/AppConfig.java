package com.narola.hotelbooking;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.narola.hotelbooking")
@Import(DBConfig.class)
@PropertySource("classpath:jpa.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.narola.hotelbooking.JPA.Repository")
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    Environment env;

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        Map<String, Object> jpaPropertiesMap = new HashMap<>();
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_DRIVER, env.getProperty("db.driver"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_USER, env.getProperty("db.user"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_PASSWORD, env.getProperty("db.password"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_URL, env.getProperty("db.url"));
        jpaPropertiesMap.put(AvailableSettings.HBM2DDL_DATABASE_ACTION, env.getProperty(AvailableSettings.HBM2DDL_DATABASE_ACTION));
        jpaPropertiesMap.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty(AvailableSettings.HBM2DDL_AUTO));
        jpaPropertiesMap.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        jpaPropertiesMap.put(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));


        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("TestPersistence");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("com.narola.hotelbooking");
        entityManagerFactoryBean.setJpaPropertyMap(jpaPropertiesMap);
        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(new CustomInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocalResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/admin/resources/**")
                .addResourceLocations("/admin/layouts/");
        registry
                .addResourceHandler("/user/resources/**")
                .addResourceLocations("/customerside/layouts/");
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("/images/");

    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver irv = new InternalResourceViewResolver();
        irv.setPrefix("/");
        irv.setSuffix(".jsp");
        return irv;
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver;
    }

    @Bean
    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("ValidationMessages");
//        messageSource.setDefaultLocale(Locale.ENGLISH);
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(120);
//        messageSource.setFileEncodings();
        return messageSource;
    }
}
