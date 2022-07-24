package com.dzmitry.hibernate_tutorial;

import com.dzmitry.hibernate_tutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;
        try{
            long studentId = 2;
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentId);
            Student newStudent = session.get(Student.class, studentId);

            //delete the student id = 2
            System.out.println("Deleting student: "  + newStudent);
            session.delete(newStudent);

            //delete the student id = 1
            session.createQuery("delete from student where id = 1").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
        session.close();
        factory.close();
    }
    }
}
