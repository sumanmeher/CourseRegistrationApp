public class People {

	private String name;

	private int age;

	private String id;

	private String password;

	// getters and setters for name

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getPassword() {
//
//		return password;
//
//	}
	public boolean checkPassword(String pass) {
		if (this.password.equals(pass))
			return true;
		return false;
	}

	public void setPassword(String password) {

		this.password = password;

	}

}