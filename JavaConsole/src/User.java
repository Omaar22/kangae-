import java.io.Serializable;

public class User implements Serializable{
    private String firstName, lastName, userName, password, email, gender, biography, birthDate;

    public User(String firstName, String lastName, String userName, String password, String email, String gender, String biography, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.biography = biography;
        this.birthDate = birthDate;
    }

    public String viewProfile() {
        return (firstName + "\n" + lastName + "\n" + userName + "\n" + email + "\n" + gender + "\n" + biography + "\n" + birthDate);
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return gender;
    }
    public String getBiography() {
        return biography;
    }
    public String getBirthDate(){
        return birthDate;
    }
}
