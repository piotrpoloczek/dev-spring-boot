package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.plaf.synth.SynthUI;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {

//			createCourseAndReviews(appDAO);

//			retrieveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);

		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int id = 10;

		System.out.println("Deleting course id: " + id);

		appDAO.deleteCourseById(id);

		System.out.println("Done.");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int id = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		// print the course
		System.out.println(course);

		// print the reviews
		System.out.println(course.getReviews());

		System.out.println("Done.");

	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course course = new Course("Pacman - ho to score one million points");

		// add some reviews
		course.addReview(new Review("Great course ...loved it!"));
		course.addReview(new Review("2Great course ...loved it!"));
		course.addReview(new Review("3Great course ...loved it!"));

		// save the course
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());

		appDAO.save(course);

		System.out.println("Done");

	}

	private void deleteCourse(AppDAO appDAO) {

		int id = 10;

		System.out.println("Deleting course id: " + id);

		appDAO.deleteCourseById(id);

		System.out.println("Done.");

	}

	private void updateCourse(AppDAO appDAO) {

		int id = 10;

		// find the course
		System.out.println("Finding course id: " + id);
		Course course = appDAO.findCourseById(id);

		// update the course
		System.out.println("Updating course id: " + id);
		course.setTitle("TEST");

		appDAO.update(course);
		System.out.println("Done.");

	}

	private void updateInstructor(AppDAO appDAO) {

		int id = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		// update the instructor
		System.out.println("Updateing instructor id: " + id);
		instructor.setLastName("TESTER");

		appDAO.update(instructor);

		System.out.println("Done.");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done.");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int id = 1;

		// find instructor
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		// associate the objects
		instructor.setCourses(courses);

		System.out.println("the associated coures: " + instructor.getCourses());
		System.out.println("Done.");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int id = 1;
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done.");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan.public@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com/susanpublic",
						"Video Games"
				);

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course course1 = new Course("Air Guitar - the ultimate guide");
		Course course2 = new Course("The Pinball Masterclass");

		// add courses to instructor
		tempInstructor.add(course1);
		tempInstructor.add(course2);

		// save the instructor
		//
		// NOTE: this will also save the courses
		// beacuse of CascadeType.PERSIST
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done.");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 4;
		System.out.println("Deleting instructro detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId = 4;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

	}


	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
					"http://www.luv2code.com/youtube",
						"Luv 2 code!!!"
				);

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the details object
		// beacuse of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

}
