package com.dzmitry.hb_04_one_to_many_uni;

import com.dzmitry.hb_04_one_to_many_uni.entity.Course;
import com.dzmitry.hb_04_one_to_many_uni.entity.Instructor;
import com.dzmitry.hb_04_one_to_many_uni.entity.InstructorDetail;
import com.dzmitry.hb_04_one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hb-04-hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
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
