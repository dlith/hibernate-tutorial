package com.dzmitry.hb_05_many_to_many;


import com.dzmitry.hb_05_many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesToStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hb-05-hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;
        try{

            session = factory.getCurrentSession();
            session.beginTransaction();

            long studentId = 2;

            Student student = session.get(Student.class, studentId);

            Course course1 = new Course("Football");
            Course course2 = new Course("Volleyball");

            course1.addStudent(student);
            course2.addStudent(student);

            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
        session.close();
        factory.close();
    }
    }
}
