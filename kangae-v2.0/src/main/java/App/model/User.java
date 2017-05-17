package App.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String email;
    @NotNull
    private String name;
    private int age;
    @Size(min = 6)
    private String password;
    private String gender;
    private String birthDate;
    private String collegeSchool;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCollegeSchool() {
        return collegeSchool;
    }

    public void setCollegeSchool(String collegeSchool) {
        this.collegeSchool = collegeSchool;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public User() {
    }

    public User(User user) {
        this.email = user.email;
        this.name = user.name;
        this.age = user.age;
        this.password = user.password;
        this.gender = user.gender;
        this.birthDate = user.birthDate;
        this.collegeSchool = user.collegeSchool;
    }

    public User(String email, String name, Integer age, String password, String gender, String birthDate, String collegeSchool) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.collegeSchool = collegeSchool;
    }
}
