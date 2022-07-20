package com.dzmitry.hibernate_tutorial;

import com.dzmitry.hibernate_tutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            Student student = new Student("Dzmitry", "Liashuk", "test@gmail.com");

            session.beginTransaction();
            System.out.println("Saving the student...");
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
        session.close();
        factory.close();
    }
    }
}
