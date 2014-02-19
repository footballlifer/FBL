package nanofuntas.fbl;

public class PlayerProfile {
	private long uid;
	private String name;
	private String position;
	private String age;
	private String height;
	private String weight;
	private String foot;

	public PlayerProfile() {}
	
	public PlayerProfile(long uid, String name, String position,
			String age, String height, String weight, String foot) {
		this.uid = uid;
		this.name = name;
		this.position = position;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.foot = foot;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
