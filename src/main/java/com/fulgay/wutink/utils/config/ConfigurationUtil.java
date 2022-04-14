package com.fulgay.wutink.utils.config;

import org.apache.log4j.Logger;

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

public class ConfigurationUtil {

    private static final Logger LOG = Logger.getLogger(ConfigurationUtil.class);


    public static Properties getGeneralMessagesProperty() {
        Properties p = new Properties();

        try {
            FileInputStream input = new FileInputStream(new File("src/main/resources/generalMessages-tr.properties"));
            p.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            return p;
        }catch (IOException e ){
            LOG.error("Error while loading properties file", e);
            return null;
        }
    }

    public static Properties getAppProperties() {
        Properties p = new Properties();

        try {
            FileInputStream input = new FileInputStream(new File("src/main/resources/application.properties"));
            p.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            return p;
        }catch (IOException e ){
            LOG.error("Error while loading properties file", e);
            return null;
        }
    }
}
