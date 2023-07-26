import java.util.Scanner;


public class Launch {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Course Regestration Website!");
		System.out.println();
		Admin ad = new Admin();

		mainMenu(ad);

	}
	
	static void printMainMenu() {
		System.out.println("----Main Menu----");
		System.out.println("1. Admin Login");
		System.out.println("2. Professor Login");
		System.out.println("3. Student Login");
		System.out.println("4. Exit Application");
	}
	static void mainMenu(Admin ad) {
		Scanner sc = new Scanner(System.in);
		printMainMenu();
		System.out.println("Select an option:");
		int userInp = sc.nextInt();
		if(userInp==1) {
			boolean isAuth = ad.checkUsernamePassword();
			if(!isAuth) {
				System.out.println("Authentication Failed.. \nThanx for visiting us..");
				System.exit(0);
			}
			Admin.adminMenu(ad);
		}else if(userInp==2) {
			Professor.professorLogin(ad);
			mainMenu(ad);
		}else if(userInp==3) {
			Student.studentLogin(ad);
			mainMenu(ad);
		}else if(userInp==4) {
			System.out.println("Exiting the Application");
			System.out.println("Thanks for visiting us..");
			System.exit(0);
		}else {
			
		}
		
	}
	
	

}