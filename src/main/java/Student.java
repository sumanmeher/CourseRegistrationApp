import java.util.Scanner;

public class Student extends People {
//	Course c;
	String enrolledCourseId;
	int marks;

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Student(String id) {
		this.setId(id);
	}

	void addStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the Student");
		setName(sc.nextLine());
		System.out.println("Enter the age of the Student");
		setAge(sc.nextInt());
		System.out.println("Enter the Password:");
		setPassword(sc.next());
	}

	static void addStudentMain(Admin ad) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n---Add Student---");
		if (ad.coursesSequence.size() != 0) {
			Course.showAllCourse(ad);
			System.out.println("Select an option:");
			int userInp = sc.nextInt();

			String courseId = ad.coursesSequence.get(userInp - 1);
			Course course = ad.courses.get(courseId);
			Student s1 = new Student("Sd" + ad.studentNo++);
			s1.addStudent();
			s1.enrolledCourseId = courseId;
			course.studIdList.add(s1.getId());
			ad.addStudentInList(s1.getId(), s1);
			System.out.println("Student Created Successfully..");
			System.out.println(s1.getName() + " will be learning " + ad.courses.get(s1.enrolledCourseId).getName());
			s1.displayStudent(ad);
		} else {
			System.out.println("Redirecting to Admin menu..");
			return;
		}

		System.out.println("Do you want to Add more Students(Yes/No):");
		String userInp = sc.next();
		if (userInp.equalsIgnoreCase("yes")) {
			addStudentMain(ad);
		} else if (userInp.equalsIgnoreCase("no")) {
			return;
		} else {
			System.out.println("invalid Input");
		}

	}

	void displayStudent(Admin ad) {
		System.out.println("Id: " + getId());
		System.out.println("Name: " + getName());
		System.out.println("Age: " + getAge());
		System.out.println("Course: " + ad.courses.get(enrolledCourseId).getName());
	}

	void setCourse(String courseId) {
		enrolledCourseId = courseId;
	}

	static Student loginStudentCheck(Admin ad) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student User Id:");
		String userId = sc.next();
		System.out.println("Enter Password:");
		String pass = sc.next();
		if (ad.students.get(userId) != null) {
			if (ad.students.get(userId).checkPassword(pass)) {
				return ad.students.get(userId);
			}
		}
		return null;
	}

	static void studentLogin(Admin ad) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Login to get your marks and Report.");
		Student stud = loginStudentCheck(ad);

		if (stud == null) {
			System.out.println("wrong userId and Password. Please try again.");
			System.out.println("Try again for Student Login (yes/No)?");
			String cont = sc.next();
			if (cont.equalsIgnoreCase("no"))
				return;
			else {
				studentLogin(ad);
				return;
			}
		} else {

			Course course = ad.courses.get(stud.enrolledCourseId);
			getUserInput(ad, stud);
//			printStudentSection();
//			System.out.println("Select an option:");
//			int userInp = sc.nextInt();
//			if (userInp == 1) {
//				System.out.println("Your marks is: " + stud.getMarks());
//				studentLogin(ad);
//			} else if (userInp == 2) {
////				System.out.println("Report Coming Soon.");
//				stud.printScoreCard(ad);
//				studentLogin(ad);
//			} else if (userInp == 3) {
//				studentLogin(ad);
//			}else if(userInp==4) {
//				Launch.mainMenu(ad);
//				return;
//			}else if(userInp==5) {
//				System.out.println("Exiting the Application.");
//				System.out.println("Thanks for visiting us.");
//				System.exit(0);
//			}else {
//				System.out.println("wrong input");
//				System.exit(0);
//			}
		}
	}

	static void getUserInput(Admin ad, Student stud) {
		Scanner sc = new Scanner(System.in);
		printStudentSection();
		System.out.println("Select an option:");
		int userInp = sc.nextInt();
		if (userInp == 1) {
			System.out.println("Your marks is: " + stud.getMarks());
			getUserInput(ad, stud);
		} else if (userInp == 2) {
//			System.out.println("Report Coming Soon.");
			stud.printScoreCard(ad);
			getUserInput(ad, stud);
		} else if (userInp == 3) {
			studentLogin(ad);
		} else if (userInp == 4) {
			Launch.mainMenu(ad);
			return;
		} else if (userInp == 5) {
			System.out.println("Exiting the Application.");
			System.out.println("Thanks for visiting us.");
			System.exit(0);
		} else {
			System.out.println("wrong input");
			System.exit(0);
		}
	}

	static void printStudentSection() {
		System.out.println("----Student section----");
		System.out.println("1. Check score");
		System.out.println("2. Get Report");
		System.out.println("3. Login as Other student");
		System.out.println("4. Goto Main Menu");
		System.out.println("5. Exit");
	}

	void printScoreCard(Admin ad) {
		Course c = ad.courses.get(enrolledCourseId);
		String sname;
		Professor p = ad.professors.get(c.getProfId());
		sname = String.format("%s", this.getName());
		String sdurationAndCName;
		System.out.print("*");
		sdurationAndCName = String.format("%s of %s course", c.getDuration(), c.getName());
		String sdescription;
		System.out.print("*");
		sdescription = String.format("containing %s", c.getContent());

		System.out.println("******************************************************");
		System.out.println("*                                                    *");
		System.out.println("*         Certificate of Course Completion           *");
		System.out.println("*                                                    *");
		System.out.println("*              This is to certify that               *");

		System.out.println("*                                                    *");
		System.out.print("*");
		centerAlign(sname);
		System.out.print("*");

		System.out.println("*                                                    *");
		System.out.println("*            has successfully completed              *");
		System.out.println("*                                                    *");

		System.out.print("*");
		centerAlign(sdurationAndCName);
		System.out.print("*");

		System.out.println("*                                                    *");

		System.out.print("*");
		centerAlign(sdescription);
		System.out.print("*");

		System.out.println("*                                                    *");
		String sgrade;
		sgrade = String.format("with a grade of %s", getMarks() + "%");
		centerAlign(sgrade);
		System.out.println("*                                                    *");
		System.out.println("*                under the guidance of               *");
		System.out.println("*                                                    *");
		String pName;
		pName = String.format("%s", p.getName());
		centerAlign(pName);
		System.out.println("*                                                    *");
		System.out.println("*                                                    *");
		System.out.println("******************************************************");
	}

	// func for center alignment
	private static void centerAlign(String text) {
		int totalSpaces = 52 - text.length();
		int leftSpaces = totalSpaces / 2;
		int rightSpaces = totalSpaces - leftSpaces;

		// non-negative totalSpaces value
		if (totalSpaces >= 0) {
			String alignedText = " ".repeat(leftSpaces) + text + " ".repeat(rightSpaces);
			System.out.println(alignedText);
		} else {
			// if text is already longer than 50 characters, print as it is
			System.out.println(text);
		}
	}

}