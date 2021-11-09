public interface Membership {
	public void checkMembership(User user);
	public String toString();
	public Membership checkValidMembershipStatus();
	public void rentManga(User user);
	public void readManga(User user);
}
