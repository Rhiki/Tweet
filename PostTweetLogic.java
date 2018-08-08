import java.util.List;

public class PostTweetLogic {
	public void execute(Tweet tweet, List<Tweet> tweetList) {
		tweetList.add(0, tweet);
	}
}
