package com.dzmitry.hb_01_one_to_one_uni;

import com.dzmitry.hb_01_one_to_one_uni.entity.Instructor;
import com.dzmitry.hb_01_one_to_one_uni.entity.InstructorDetail;
import com.dzmitry.hibernate_tutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hb-01-hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
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
