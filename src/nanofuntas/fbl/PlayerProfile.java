package nanofuntas.fbl;

public class PlayerProfile {
	private long uid;
	private String name;
	private String position;
	
	public PlayerProfile() {
	}

	public PlayerProfile(long uid, String name, String position) {
		this.uid = uid;
		this.name = name;
		this.position = position;
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
