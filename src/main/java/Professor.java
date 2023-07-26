import java.util.ArrayList;
import java.util.Scanner;

public class Professor extends People {
	Scanner sc = new Scanner(System.in);
//	Course 
//	Course course;
	String courseIdTeaching;

	public Professor(String s) {
		// TODO Auto-generated constructor stub
		setId(s);
	}

	void addProfessor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the Professor:");
		setName(sc.nextLine());
		System.out.println("Enter the age of the Professor");
		setAge(sc.nextInt());
		System.out.println("Enter the Password:");
		setPassword(sc.next());
//		courseIdTeaching=courseID;
	}

	static void addProfessorMain(Admin ad) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n---Add Professor---");
		ArrayList<Course> remCourseArr = Course.unAssignedCourse(ad);

		// asign the course
		if (remCourseArr.size() > 0) {
			Course.showUnAssignedCourse(remCourseArr);
			System.out.println("Select an option:");
			int userInp = sc.nextInt();

			String courseId = ad.coursesSequence.get(userInp - 1);
			Professor p1 = new Professor("Pr" + ad.professorNo++);
			p1.addProfessor();
			p1.courseIdTeaching = courseId;
			Course course = ad.courses.get(courseId);
			course.setProf(p1.getId());
			ad.addProfessorInList(p1.getId(), p1);
			p1.displayProfessor(ad);
			System.out.println(p1.getName() + " will be teaching " + course.getName());
		} else {
			Course.showUnAssignedCourse(remCourseArr);
			System.out.println("Redirecting to Admin Menu...");
			return;
//			Admin.adminMenu(ad);
		}
		// add more professors
		System.out.println("Do you want to Add more Professors(Yes/No):");
		String userInp = sc.next();
		if (userInp.equalsIgnoreCase("yes")) {
			addProfessorMain(ad);
		} else if (userInp.equalsIgnoreCase("no")) {
			return;
		} else {
			System.out.println("invalid Input");
		}
	}

	void displayProfessor(Admin ad) {
		System.out.println("Id: " + getId());
		System.out.println("Name: " + getName());
		System.out.println("Age: " + getAge());
		System.out.println("Course: " + ad.courses.get(courseIdTeaching).getName());
	}

	static Professor checkLoginProfessor(Admin ad) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Professor User Id:");
		String userId = sc.next();
		System.out.println("Enter Password:");
		String pass = sc.next();
		if (ad.professors.get(userId) != null) {
			if (ad.professors.get(userId).checkPassword(pass)) {
				return ad.professors.get(userId);
			}
		}
		return null;

	}

	static void professorLogin(Admin ad) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Login to mark the students.");
		Professor prof = Professor.checkLoginProfessor(ad);
		if (prof == null) {
			System.out.println("wrong userId and Password. Please try again.");
			System.out.println("Try again for professot Login (yes/No)?");
			String cont = sc.next();
			if (cont.equalsIgnoreCase("no"))
				return;
			else
				professorLogin(ad);
			return;
		} else {
			System.out.println("Professor Logged In...");
			prof.provideMarks(ad);
			

		}
		
		
	}
	
	void provideMarks(Admin ad) {
		Course course = ad.courses.get(this.courseIdTeaching);
		System.out.println("Provide marks for students of course " + course.getName());
		int i = 1;
		for (String str : course.studIdList) {
//			System.out.println(str);
			Student student = ad.students.get(str);
			System.out.println(i++ + ". " + student.getName());
		}
		System.out.println("Select the student to grade.");
		int userInp = sc.nextInt();
		String studentId = course.studIdList.get(userInp-1);
		Student student = ad.students.get(studentId);
		System.out.println("Give marks to " + student.getName());
		int marks = sc.nextInt();
		student.setMarks(marks);

		// Give marks to more student?
		System.out.println("Do you want grade other Students(Yes/No):");
		String userInpStr = sc.next();
		if (userInpStr.equalsIgnoreCase("yes")) {
			provideMarks(ad);
		} else if (userInpStr.equalsIgnoreCase("no")) {
			return;
		} else {
			System.out.println("invalid Input");
		}
	}

}