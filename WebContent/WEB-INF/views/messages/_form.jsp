<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for = "title">タイトル</label><br/>
<%--  value="${message.title}"とすることによってmessageオブジェクトからデータを参照して入力内容の初期値を表示することができる。editなどで役立つ --%>
<input type="text" name="title" value="${message.title}" />
<br/><br/>

<label for="context">メッセージ</label><br/>
<input type="text" name="content" value="${message.content}" />
<br/><br/>

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>