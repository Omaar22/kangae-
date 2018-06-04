package App.model;

import javax.persistence.Entity;

@Entity
public class Teacher extends User {

    public Teacher() {}

    public Teacher(User user) {
        super(user);
    }

}
