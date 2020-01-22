package model;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// @Entity　Hebernateを用いてクラスを書くときは必ず記述するルールになっている
@Entity
@NamedQueries({
    // @NameQueryアノテーションをもちいることで書きの通りselect文に名前をつけることができる
    @NamedQuery(
            // 下のselect文にgetAllMessagesという名前をつけた
            name = "getAllMessages",
            // select m : select * と同じ意味
            query = "SELECT m FROM Message AS m ORDER BY m.id DESC"
            )
})
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    // @GeneratedValueは主キーにつけることでユニークな値を自動で生成してくれる
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "created_at", nullable= false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}