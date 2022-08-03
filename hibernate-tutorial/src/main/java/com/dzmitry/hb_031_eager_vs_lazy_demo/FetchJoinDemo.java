package com.dzmitry.hb_031_eager_vs_lazy_demo;

import com.dzmitry.hb_03_one_to_many.entity.Course;
import com.dzmitry.hb_03_one_to_many.entity.Instructor;
import com.dzmitry.hb_03_one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            Query<Instructor> query = session.createQuery("select i from Instructor i"
                    + " JOIN FETCH i.courses " +
                    " where i.id=:theInstructorId", Instructor.class);

            query.setParameter("theInstructorId", id);

            Instructor instructor = query.getSingleResult();
            System.out.println(instructor);

            session.getTransaction().commit();

            System.out.println("Session is closed!");
            System.out.println("Courses: " + instructor.getCourses());

            System.out.println("Done!");
        } finally {
        session.close();
        factory.close();
    }
    }
}
