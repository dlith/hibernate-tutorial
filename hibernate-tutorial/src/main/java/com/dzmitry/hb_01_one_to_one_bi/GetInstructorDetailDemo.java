package com.dzmitry.hb_01_one_to_one_bi;

import com.dzmitry.hb_01_one_to_one_bi.entity.Instructor;
import com.dzmitry.hb_01_one_to_one_bi.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hb-01-hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = null;
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            long id = 1;

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            System.out.println(instructorDetail);
            System.out.println(instructorDetail.getInstructor());

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
        session.close();
        factory.close();
    }
    }
}
