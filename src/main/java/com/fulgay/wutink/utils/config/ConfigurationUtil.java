package com.fulgay.wutink.utils.config;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Fırat ÜLGAY
 * @since 27.11.2021
 */

@Component
public class ConfigurationUtil {

    private static final Logger LOG = Logger.getLogger(ConfigurationUtil.class);


    public Properties getGeneralMessagesProperty(){
        Resource resource = new ClassPathResource("/globalmessage_TR.properties");
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            LOG.error("Error while loading properties file",e);
            return null;
        }
    }
}
