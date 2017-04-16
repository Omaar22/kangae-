package App.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Teacher extends User {
    @NotNull
    private String collegeSchool;

    public String getCollegeSchool() {
        return collegeSchool;
    }

    public void setCollegeSchool(String collegeSchool) {
        this.collegeSchool = collegeSchool;
    }
}
