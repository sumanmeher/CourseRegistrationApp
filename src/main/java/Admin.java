import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {
	
	//Login Credentials
	String username;
	String password;
	public Admin() {
		this.username = "admin";
		this.password = "Admin";
	}
	
	int courseNo = 1;
	int professorNo = 1;
	int studentNo = 1;
	
	ArrayList<String> professorSequence = new ArrayList<String>();
	Map<String, Professor> professors = new HashMap<String, Professor>();
	
	ArrayList<String> coursesSequence = new ArrayList<String>();
	Map<String, Course> courses = new HashMap<String, Course>();

	ArrayList<String> studentSequence = new ArrayList<String>();
	Map<String, Student> students = new HashMap<String, Student>();
	
	boolean checkUsernamePassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Admin Username:");
		String name = sc.next();
		System.out.println("Enter Admin Password");
		String pass = sc.next();
		
		if(name.equals(username) && pass.equals(this.password))
			return true;
		return false;
		
	}
	
	void addCourseInList(String courseId, Course c) {
//		this.courses.add(c);
		this.courses.put(courseId, c);
	}
	
	void addProfessorInList(String profId, Professor p) {
//		this.professors.add(p);
		this.professors.put(profId, p);
	}
	
	void addStudentInList(String studId, Student s) {
//		this.students.add(s);
		this.students.put(studId, s);
	}
	
	void showAllCourses() {
		for (int i=0; i<coursesSequence.size();i++) {
			Course course = courses.get(coursesSequence.get(i));
			System.out.println((i+1)+")"+course.getName()+"("+course.id+")");
		}
	}
	
	void showAllProfessors() {
//		for (var professor : professors) {
//			System.out.println(professor.getName()+"("+professor.getId()+")");
//		}
		for (Map.Entry m : professors.entrySet()) {
			System.out.println(m.getKey() + " -> " + m.getValue());
		}
	}
	
	void showAllStudents() {
		for (Map.Entry m : students.entrySet()) {
			System.out.println(m.getKey() + " -> " + m.getValue());
		}
	}
	
	static void printAdminMenu() {
		System.out.println("----Admin Menu----");
		System.out.println("1. Add Course");
		System.out.println("2. Add Professor");
		System.out.println("3. Add Student");
		System.out.println("4. Goto Main Menu");
	}
	
	static void adminMenu(Admin ad) {
		Scanner sc = new Scanner(System.in);
		
		printAdminMenu();
		System.out.println("Select an option:");
		int userInp = sc.nextInt();
		if(userInp==1) {
			Course.addCourseMain(ad);
			adminMenu(ad);
		}else if(userInp==2) {
			Professor.addProfessorMain(ad);
			Admin.adminMenu(ad);
		}else if(userInp==3) {
			Student.addStudentMain(ad);
			Admin.adminMenu(ad);
		}else if(userInp==4) {
			Launch.mainMenu(ad);
		}else {
			return;
		}
	}

}
