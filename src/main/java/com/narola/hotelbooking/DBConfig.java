package com.narola.hotelbooking;

import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.dao.IStateDAO;
import com.narola.hotelbooking.dao.ConnectDB;
import com.narola.hotelbooking.dao.DAOFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DBConfig {

//    @Autowired
//    Environment environment;

    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.dbType}")
    String DBType;
    @Value("${jdbc.dbName}")
    String DBName;
    @Value("${jdbc.username}")
    String userName;
    @Value("${jdbc.password}")
    String password;

    @Bean
    public DAOFactory daoFactory() throws Exception {
        String dbType = DBType;
        DAOFactory.init(dbType);
        ConnectDB.setDbname(DBName);
        ConnectDB.setUrl(url);
        ConnectDB.setUsername(userName);
        ConnectDB.setPassword(password);
        ConnectDB.initializeConnection();
        return DAOFactory.getDaoFactory();
    }

    @Bean
    public ICityDAO getICityDAO(DAOFactory daoFactory) {
        return daoFactory.getCityDAO();
    }

    @Bean
    public IStateDAO getIStateDAO(DAOFactory daoFactory) {
        return daoFactory.getStateDAO();
    }
}
