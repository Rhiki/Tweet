public class Tweet {
	private String user_name;
	private String tweet;

	public Tweet(String user_name, String tweet) {
		this.user_name = user_name;
		this.tweet     = tweet;
	}

	public String getUserName() { return this.user_name; }
	public String getTweet()    { return this.tweet; }
}
