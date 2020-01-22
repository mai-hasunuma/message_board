package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Message;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        // データベースの書き換えが必要となる場合には、「トランザクション」というものを使わなければならない
        // なぜかというと複数の場所からアクセスされていることがあるため、他からデータがあくせすされている最中にデータを書き換えたりするとトラブルの原因となるから　
        // ちなみにトランザクション処理とは 複数の SQL 文によるデータ更新を1つの処理としてまとめてデータベースに反映させること
        em.getTransaction().begin();

        // Messageのインスタンスを生成
        Message m = new Message();

        // mの各プロパティにデータを代入
        String title = "taro";
        m.setTitle(title);

        String content = "hello";
        m.setContent(content);

        // 現在の日時を取得
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());     // 現在の日時を取得
        m.setCreated_at(currentTime);
        m.setUpdated_at(currentTime);

        // エンティティの新規追加はpersistというメソッドを使う
        em.persist(m);
        // commitすることでpersistしておいたエンティティがすべてデータベースに保存される
        // https://www.tuyano.com/index3?id=9736003&page=2
        em.getTransaction().commit();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(m.getId()).toString());


    }

}
