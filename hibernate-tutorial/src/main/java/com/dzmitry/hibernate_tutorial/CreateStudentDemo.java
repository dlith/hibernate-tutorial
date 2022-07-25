package com.dzmitry.hibernate_tutorial;

import com.dzmitry.hibernate_tutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;
        try{
            session = factory.getCurrentSession();
            System.out.println("Creating a new student object...");
            String dateOfBirthStr = "01/01/2001";
            Date theDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirthStr);
            Student student = new Student("Dzmitry", "Liashuk", "test@gmail.com", theDateOfBirth);

            session.beginTransaction();
            System.out.println("Saving the student...");
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
        session.close();
        factory.close();
    }
    }
}
