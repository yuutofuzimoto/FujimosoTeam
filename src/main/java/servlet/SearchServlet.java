package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import dao.Dao;

//SampleBBS/ArticleListServletにアクセスすると，このサーブレットが動作
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

 //コンストラクタ（省略可能）
    public SearchServlet() {
        super();
    }

    //POSTアクセスされた場合は，doGetに丸投げ
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request,response);
 }
 
 //GETアクセスされた場合に動作
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
  Dao dao = new Dao();
  String search = request.getParameter("body");
  List<Article> Search = dao.getSearch(search);

  System.out.println("Searchリストの内容:");
  for (Article article : Search) {
      System.out.println(article.toString()); // ArticleクラスでtoString()が適切に実装されていることを想定
  }
  
  //requestに記事リストを格納．
  request.setAttribute("articleList" , Search);
  
  
  //./WEB-INF/jsp/articleList.jspを表示
  RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/jsp/articleList.jsp");
  dispatcher.forward(request, response);
 }

}