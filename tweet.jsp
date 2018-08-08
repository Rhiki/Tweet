<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="User" %>
<%@ page import="Tweet" %>
<%@ page import="java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//アプリケーションスコープに保存されたつぶやきリストを取得
List<Tweet> tweetList = (List<Tweet>) application.getAttribute("tweetList");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Tweet</title>
<link rel="stylesheet" href="style.css">
</head>

<body>
<div class="tweet-list">
	<p>ツイート一覧</p>

	<% for(Tweet tweet : tweetList) { %>
		<p><%= tweet.getTweet() %></p>
	<% } %>
</div>

<div class="tweet-form">
	<p><%= loginUser.getName() %>さんログイン中</p>
	<form action="Servlet.java" method="post">
		<textarea name="text" rows="4" cols="40">ここにつぶやきを入力してください</textarea>
		<br>
		<input type="submit" value="ツイート">
		<input type="reset" value="リセット">
	</form>
	<br>
	<a href="Logout.java">ログアウト</a>
</div>

</body>

</html>
