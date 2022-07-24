package com.dzmitry.hibernate_tutorial;

import com.dzmitry.hibernate_tutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;
        try{
            long studentId = 1;
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentId);
            Student newStudent = session.get(Student.class, studentId);

            System.out.println("Updating student: " + newStudent);
            newStudent.setFirstName("Scooby");
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            session.createQuery("update student set email='foo@gmail.com'").executeUpdate();

            System.out.println("Done!");
        }finally {
        session.close();
        factory.close();
    }
    }
}
