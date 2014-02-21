package nanofuntas.fbl;

public class TeamInfo {
	
	static class TeamProfile {
		
		private long mTid;
		private String mTeamName;
		
		public TeamProfile() {}
		public TeamProfile(long tid, String tname) {
			this.mTid = tid;
			this.mTeamName = tname;
		}
		
		public long getTid() {
			return mTid;
		}
		public void setTid(long tid) {
			this.mTid = tid;
		}
		public String getTeamName() {
			return mTeamName;
		}
		public void setTeamName(String tname) {
			this.mTeamName = tname;
		}

	}
	
	static class TeamLevel {

		private long mTid;
		private long mATK;
		private long mDFS;
		private long mTEC;
		private long mPHY;
		private long mTWK;
		private long mMTL;
		private long mOVERALL;
		
		public TeamLevel() {}
		public TeamLevel(long tid, long atk, long dfs,
				long tec, long phy, long twk, long mtl, long overall) {
			this.mTid = tid;
			this.mATK = atk;
			this.mDFS = dfs;
			this.mTEC = tec;
			this.mPHY = phy;
			this.mTWK = twk;
			this.mMTL = mtl;
			this.mOVERALL = overall;
		}
		
		public long getTid() {
			return mTid;
		}
		public void setTid(long tid) {
			this.mTid = tid;
		}
		public long getATK() {
			return mATK;
		}
		public void setATK(long atk) {
			this.mATK = atk;
		}
		public long getDFS() {
			return mDFS;
		}
		public void setDFS(long dfs) {
			this.mDFS = dfs;
		}
		public long getTEC() {
			return mTEC;
		}
		public void setTEC(long tec) {
			this.mTEC = tec;
		}
		public long getPHY() {
			return mPHY;
		}
		public void setPHY(long phy) {
			this.mPHY = phy;
		}
		public long getTWK() {
			return mTWK;
		}
		public void setTWK(long twk) {
			this.mTWK = twk;
		}
		public long getMTL() {
			return mMTL;
		}
		public void setMTL(long mtl) {
			this.mMTL = mtl;
		}
		public long getOVERALL() {
			return mOVERALL;
		}
		public void setOVERALL(long overall) {
			this.mOVERALL = overall;
		}	
	}
	
	static class TeamRating {

		private long mTid;
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
		
		public long getTid() {
			return mTid;
		}
		public void setTid(long id) {
			this.mTid = id;
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
