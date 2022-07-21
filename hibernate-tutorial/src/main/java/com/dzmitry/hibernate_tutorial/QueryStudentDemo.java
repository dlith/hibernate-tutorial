package com.dzmitry.hibernate_tutorial;

import com.dzmitry.hibernate_tutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            List<Student> students = session.createQuery("from student", Student.class).getResultList();

            displayStudents(students);
            System.out.println("===================");
            students = session.createQuery("from student s where s.lastName='Liashuk'", Student.class).getResultList();
            displayStudents(students);
            System.out.println("===================");
            students = session.createQuery("from student s where s.firstName='John' or s.lastName='Liashuk'", Student.class).getResultList();
            displayStudents(students);

            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
        session.close();
        factory.close();
    }
    }

    private static void  displayStudents(List<Student> students) {
        for(Student s: students){
            System.out.println(s);
        }
    }
}
