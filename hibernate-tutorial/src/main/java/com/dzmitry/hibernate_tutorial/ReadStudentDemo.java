package com.dzmitry.hibernate_tutorial;

import com.dzmitry.hibernate_tutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;
        try{
            session = factory.getCurrentSession();
            System.out.println("Creating a new student object...");
            Student student = new Student("John", "Lennon", "jlennon@gmail.com");

            session.beginTransaction();
            System.out.println("Saving the student...");
            System.out.println(student);
            session.save(student);
            session.getTransaction().commit();

            //retrieve object
            System.out.println("Saved student. Generated id: " + student.getId());

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nGetting student with id: " + student.getEmail());
            Student newStudent = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + newStudent);
            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
        session.close();
        factory.close();
    }
    }
}
