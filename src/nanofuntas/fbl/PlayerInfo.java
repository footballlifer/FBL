package nanofuntas.fbl;

public class PlayerInfo {

	static class PlayerProfile {

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
	}
	
	static class PlayerRating {

		private long mUid;
		// TODO: all the following items should be int
		private long mAttack;
		private long mDefense;
		private long mTeamwork;
		private long mMental;
		private long mPower;
		private long mSpeed;
		private long mStamina;
		private long mBallControl;
		private long mPass;
		private long mShot;
		private long mHeader;
		private long mCutting;
		private long mOverall;
		
		public long getUid() {
			return mUid;
		}
		public void setUid(long id) {
			this.mUid = id;
		}
		public long getAttack() {
			return mAttack;
		}
		public void setAttack(long attack) {
			this.mAttack = attack;
		}
		public long getDefense() {
			return mDefense;
		}
		public void setDefense(long defense) {
			this.mDefense = defense;
		}
		public long getTeamwork() {
			return mTeamwork;
		}
		public void setTeamwork(long teamwork) {
			this.mTeamwork = teamwork;
		}
		public long getMental() {
			return mMental;
		}
		public void setMental(long mental) {
			this.mMental = mental;
		}
		public long getPower() {
			return mPower;
		}
		public void setPower(long power) {
			this.mPower = power;
		}
		public long getSpeed() {
			return mSpeed;
		}
		public void setSpeed(long speed) {
			this.mSpeed = speed;
		}
		public long getStamina() {
			return mStamina;
		}
		public void setStamina(long stamina) {
			this.mStamina = stamina;
		}
		public long getBallControl() {
			return mBallControl;
		}
		public void setBallControl(long ballControl) {
			this.mBallControl = ballControl;
		}
		public long getPass() {
			return mPass;
		}
		public void setPass(long pass) {
			this.mPass = pass;
		}
		public long getShot() {
			return mShot;
		}
		public void setShot(long shot) {
			this.mShot = shot;
		}
		public long getHeader() {
			return mHeader;
		}
		public void setHeader(long header) {
			this.mHeader = header;
		}
		public long getCutting() {
			return mCutting;
		}
		public void setCutting(long cutting) {
			this.mCutting = cutting;
		}
		public long getOverall() {
			return mOverall;
		}
		public void setOverall(long overall) {
			this.mOverall = overall;
		}

	}
}
