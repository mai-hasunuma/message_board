package controllers;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// modelとutilsのインポート
import model.Message;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // EntityManagerオブジェクトの生成　データベースにアクセスできる　
        // EntityManagerにはpersist（新規保存）、remove（削除）、merge（更新）、find（検索）などの基本メソッドがある
        EntityManager em = DBUtil.createEntityManager();

        //　開くページ数を取得 でぉるとは１
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}


        // createNamedQuery("名前付きクエリ名", クラス名.class)→名前つきクエリを実行する
        // デフォルトは　page = 1なのでsetFirstResult(0)となり、データの一番目となる
        // setMaxResults(15) 最大15件取得という意味
        // .getResultList() Listで結果を取得する
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        // 全件数を取得
        // .getSingleResult(); データは１件だけということ
        long messages_count = (long)em.createNamedQuery("getMessagesCount", Long.class)
                .getSingleResult();

        em.close();

        request.setAttribute("messages", messages);
        request.setAttribute("messages_count", messages_count);     // 全件数
        request.setAttribute("page", page);                         // ページ数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する(セッションスコープからは削除)
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);

    }

}
