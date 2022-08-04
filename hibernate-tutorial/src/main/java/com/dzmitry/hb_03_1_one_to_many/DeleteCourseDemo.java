package com.dzmitry.hb_03_1_one_to_many;

import com.dzmitry.hb_03_1_one_to_many.entity.Course;
import com.dzmitry.hb_03_1_one_to_many.entity.Instructor;
import com.dzmitry.hb_03_1_one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

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

            long id = 10;
            Course course = session.get(Course.class, id);
            System.out.println("Deleting course: " + course);
            session.delete(course);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
        session.close();
        factory.close();
    }
    }
}
