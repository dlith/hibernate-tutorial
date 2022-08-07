package com.dzmitry.hb_05_many_to_many;


import com.dzmitry.hb_05_many_to_many.entity.Review;
import com.dzmitry.hb_05_many_to_many.entity.Course;
import com.dzmitry.hb_05_many_to_many.entity.Instructor;
import com.dzmitry.hb_05_many_to_many.entity.InstructorDetail;
import com.dzmitry.hb_05_many_to_many.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {

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

            Course course = new Course("Pacman - how to score one million points");

            System.out.println("Saving course");
            session.save(course);

            Student student1 = new Student("John", "Doe", "test@gmail.com");
            Student student2 = new Student("Mary", "Public", "public@gmail.com");

            course.addStudent(student1);
            course.addStudent(student2);

            System.out.println("Saving students");
            session.save(student1);
            session.save(student2);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
        session.close();
        factory.close();
    }
    }
}
