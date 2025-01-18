package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/GoodAddServlet")
public class GoodAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GoodAddServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//requestのデータの文字コードをUTF-8に設定
		request.setCharacterEncoding("UTF-8");
		
		String good = request.getParameter("good");
		System.out.println(good);
		Dao dao = new Dao();
		//データベースのいいね数を取得
		int goodCount = dao.updateGood(good);
		//取得したいいね数に1加算してデータベースに登録
		dao.addGood(goodCount+1,good);
		
		//記事リストに戻る
		RequestDispatcher dispatcher = request.getRequestDispatcher("./ArticleListServlet");
		dispatcher.forward(request, response);
	}

}