package App.model;

import javax.persistence.*;

/**
 * Created by lenovo Z on 13/05/2017.
 */
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;

    String content;

}
