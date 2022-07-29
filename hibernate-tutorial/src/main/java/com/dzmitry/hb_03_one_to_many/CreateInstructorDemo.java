package com.dzmitry.hb_03_one_to_many;

import com.dzmitry.hb_03_one_to_many.entity.Course;
import com.dzmitry.hb_03_one_to_many.entity.Instructor;
import com.dzmitry.hb_03_one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hb-03-hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = null;
        try{

            Instructor instructor = new Instructor("Ivan", "Cholik", "test@foo.com");
            InstructorDetail instructorDetail = new InstructorDetail("youtube", "drums");
            instructor.setInstructorDetail(instructorDetail);

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
        session.close();
        factory.close();
    }
    }
}
