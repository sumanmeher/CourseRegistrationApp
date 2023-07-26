import java.util.ArrayList;
import java.util.Scanner;

public class Course {
	String name;
	int price;
	String duration;
	String content;
	String id;
//	Professor prof;
	String profId;
	ArrayList<String> studIdList = new ArrayList<String>();

	public Course(String id) {
		this.id = id;
	}

	String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProfId() {
		return profId;
	}

	public void setProf(String professorId) {
//		this.prof = prof;
		profId = professorId;
	}

	// method to add course
	void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Name of the course: ");
		name = sc.nextLine();
		setName(name);

		System.out.println("Enter the Duration of the course: ");
		duration = sc.nextLine();
		setDuration(duration);

		System.out.println("Enter the Content of the course using comma (,): ");
		content = sc.nextLine();
		setContent(content);

		System.out.println("Enter the Price of the course: ");
		price = sc.nextInt();
		setPrice(price);

	}

	static void addCourseMain(Admin ad) {
		System.out.println("\n---Add courses---");
		Scanner sc = new Scanner(System.in);
		Course c1 = new Course("C" + ad.courseNo++);
		c1.addCourse();
		c1.displayCourse();
		ad.coursesSequence.add(c1.getId());
		ad.addCourseInList(c1.getId(), c1);
		System.out.println("Do you want to Create more Courses(Yes/No):");
		String userInp = sc.next();
		if (userInp.equalsIgnoreCase("yes")) {
			addCourseMain(ad);
		} else if (userInp.equalsIgnoreCase("no")) {
			return;
		} else {
			System.out.println("invalid Input");
		}
	}

	static ArrayList<Course> unAssignedCourse(Admin ad) {
//		System.out.println("\nSelect a course to Teach.");
		int numOfCourse = ad.courses.size();
		ArrayList<Course> arr = new ArrayList<Course>();
		for (int i = 0; i < numOfCourse; i++) {
			String courseId = ad.coursesSequence.get(i);
			Course course = ad.courses.get(courseId);
			if (course.getProfId() == null) {
				arr.add(course);
			}
		}
		return arr;
	}

	static void showUnAssignedCourse(ArrayList<Course> arr) {
		if (arr.size() == 0) {
			System.out.println("No Course is Left to be Assigned.");
			return;
		} else {
			System.out.println("\nSelect a course to Teach.");
			for (int i = 0; i < arr.size(); i++) {
				Course course = arr.get(i);
				System.out.println((i + 1) + ". " + course.getName());
			}
		}
	}

	static void showAllCourse(Admin ad) {
		if (ad.coursesSequence.size() == 0) {
			System.out.println("No Course available.");
			return;
		} else {
			System.out.println("\nSelect a course to Learn.");
			for (int i = 0; i < ad.coursesSequence.size(); i++) {
				String courseId = ad.coursesSequence.get(i);
				Course course = ad.courses.get(courseId);
				System.out.println((i + 1) + ". " + course.getName());
			}
		}
	}
	int count=1;
	private void commaSeparator() {
		String[] cont = content.split(",");
		for (String word : cont) {
			System.out.println(count+". "+word.trim());
			count++;
		}
	}

	void displayCourse() {
		System.out.println("Id:             " + id);
		System.out.println("Name:           " + name);
		System.out.println("Price:          Rs." + price);
		System.out.println("Duration:       " + duration);
		System.out.println("Course Content: ");
		commaSeparator();
		System.out.println();
	}

}