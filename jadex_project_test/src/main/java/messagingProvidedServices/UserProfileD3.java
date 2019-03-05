package messagingProvidedServices;

public class UserProfileD3 {

	String name;
	int age;
	boolean gender;
	String description;
	
	
	public UserProfileD3() {
		
	}
	public UserProfileD3(int randomProfile) {
		switch(randomProfile) {
		  case 1:
		    this.name="Filipe";
		    this.age=28;
		    this.gender=false;
		    this.description="developer";
		    break;
		  case 2:
			this.name="Ania";
			this.age=26;
			this.gender=false;
			this.description="namorada";
		    break;
		  case 3:
			this.name="Maria";
			this.age=20;
			this.gender=true;
			this.description="customer";
			break;
		  case 4:
			this.name="Joana";
			this.age=32;
			this.gender=false;
			this.description="customer";
			break;
		  default:
			this.name="Ricardo";
			this.age=29;
			this.gender=true;
			this.description="friend";
		}
	}
	public UserProfileD3(String name, int age, boolean gender, String description) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.description = description;
	}
	
	
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
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "UserProfileD3(name="+this.name+", age="+this.age+", gender="+this.gender+", descrition="+this.description+".)";
	}
	
}
