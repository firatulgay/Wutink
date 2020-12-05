package com.fulgay;

import com.fulgay.bilenyum.dao.UserDaoImpl;
import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.utils.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> all = (List<User>) userDao.findAll();
        for (User user : all) {
            System.out.println("***************");
            System.out.println(user.getId()+"  "+ user.getName() + "  " + user.getSurname());
            System.out.println("***************");

        }


//        User user = new User();
//        user.setName("Doruk");
//        user.setSurname("Aşan");
//        user.setPassword("234jdlg834");
//        user.setUserName("dAsan");
//        user.setUserType(EnumUserType.USER);

//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();

//        User userSaved = (User)session.merge(user);
//        transaction.commit();

//        Experience experience = new Experience();
//        experience.setDescription("Bu bir açıklama");
//        experience.setExperienceCategory(EnumExperienceCategory.TECHNOLOGY);
//        experience.setHeader("KONU BAŞLIĞI");
//        experience.setUser(userSaved);
//
//        Session session2 = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction2 = session2.beginTransaction();
//
//
//        session2.merge(experience);
//        transaction2.commit();

//
    }
}
