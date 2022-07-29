package com.dzmitry.hb_03_one_to_many.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hb-03-hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = null;
        try{

            session = factory.getCurrentSession();
            session.beginTransaction();

            long id = 1;
            Instructor instructor = session.get(Instructor.class, id);
            Course course1 = new Course("Air Guitar");
            Course course2 = new Course("The Pinball");

            instructor.addCourse(course1);
            instructor.addCourse(course2);

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
