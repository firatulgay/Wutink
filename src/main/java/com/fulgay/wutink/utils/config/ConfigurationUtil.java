package com.fulgay.wutink.utils.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author Fırat ÜLGAY
 * @since 27.11.2021
 */

@PropertySource("classpath:application.${spring.profiles.active}.properties")
@PropertySource("classpath:generalMessages-tr.properties")
@Configuration
public class ConfigurationUtil {

    private static final Logger LOG = Logger.getLogger(ConfigurationUtil.class);

    @Value("${allowed.origins:}")
    private String allowedOrigins;


    public static Properties getGeneralMessagesProperty() {
        Properties p = new Properties();

        try {
            FileInputStream input = new FileInputStream(new File("generalMessages-tr.properties"));
            p.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            return p;
        }catch (IOException e ){
            LOG.error("Error while loading properties file", e);
            return null;
        }
    }

    public String getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    /*
       OLD WAY TO REACH THE PROPERTIES FILE
        public static Properties getAppProperties() {
        Properties p = new Properties();

        try {
            FileInputStream input = new FileInputStream(new File("src/main/resources/application.prod.properties"));
            p.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            return p;
        }catch (IOException e ){
            LOG.error("Error while loading properties file", e);
            return null;
        }
    }
     */

}
