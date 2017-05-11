package App.model;

import javax.persistence.*;

/**
 * Created by lenovo Z on 11/05/2017.
 */
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String content;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


}
