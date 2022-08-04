package com.dzmitry.hb_04_one_to_many_uni;

import com.dzmitry.hb_04_one_to_many_uni.entity.Course;
import com.dzmitry.hb_04_one_to_many_uni.entity.Instructor;
import com.dzmitry.hb_04_one_to_many_uni.entity.InstructorDetail;
import com.dzmitry.hb_04_one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateReviewsDemo {

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

            Course course = new Course("Pacman");
            course.addReview(new Review("Great"));
            course.addReview(new Review("Cool"));
            course.addReview(new Review("OK"));
            System.out.println("Saving course");
            session.save(course);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
        session.close();
        factory.close();
    }
    }
}
