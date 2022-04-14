package com.fulgay;

import com.fulgay.wutink.security.config.ApiSecurityConfig;
import com.fulgay.wutink.utils.config.ConfigurationUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Import( { ApiSecurityConfig.class } )
public class App implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);

    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList(ConfigurationUtil.getAppProperties().getProperty("allowed.origins")));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void run(String... strings) throws Exception {
//        UserDaoImpl userDao = new UserDaoImpl();
//        List<User> all = (List<User>) userDao.findAll();
//        for (User user : all) {
//            System.out.println("***************");
//            System.out.println(user.getId()+"  "+ user.getName() + "  " + user.getSurname());
//            System.out.println("***************");
//
//        }


//        User user = new User();
//        user.setPassword("234jdlg834");
//        user.setUserName("dAsan");
////
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//
//        User userSaved = (User)session.merge(user);
//        transaction.commit();

//        Experience experience = new Experience();
//        experience.setDescription("Bu bir açıklama");
//        experience.setExperienceCategory(EnumExperienceCategory.TECHNOLOGY);
//        experience.setHeader("KONU BAŞLIĞI");
//        experience.setUser(userSaved);

//        Session session2 = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction2 = session2.beginTransaction();

//
//        session2.merge(experience);
//        transaction2.commit();

//
    }
}
