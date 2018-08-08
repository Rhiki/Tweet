import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PostTweetLogic;
import Tweet;
import User;



@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//つぶやきリストをアプリケーションスコープから取得
		ServletContext app = getServletContext();
		List<Tweet> tweetList = (List<Tweet>) app.getAttribute("tweetList");

		//取得できなかった場合は、つぶやきリストを新規作成してアプリケーションスコープに保存
		if(tweetList == null) {
			tweetList = new ArrayList<Tweet>();
			app.setAttribute("tweetList", tweetList);
		}

		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {//ログインしていない場合
			//リダイレクト
			response.sendRedirect("index.jsp");
		} else {
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("tweet.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータを取得
		String text = request.getParameter("text");

		//入力値チェック
		if(text != null && text.length() != 0) {
			//アプリケーションスコープに保存されたつぶやきリストを取得
			ServletContext app = getServletContext();
			List<Tweet> tweetList = (List<Tweet>) app.getAttribute("tweetList");

			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//つぶやきをつぶやきリストに追加
			Tweet tweet = new Tweet(loginUser.getName(), text);
			PostTweetLogic logic = new PostTweetLogic();
			logic.execute(tweet, tweetList);
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("tweet.jsp");
		dispatcher.forward(request, response);
	}
}
