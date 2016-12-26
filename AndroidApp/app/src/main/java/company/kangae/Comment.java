package company.kangae;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Parcelable{

    private String userName;
    private String comment;
    private ArrayList <String> replies = new ArrayList<>();

    protected Comment(Parcel in) {
        userName = in.readString();
        comment = in.readString();
        replies = in.createStringArrayList();
    }

    public Comment (String userName, String comment){
        this.userName = userName;
        this.comment = comment;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public ArrayList<String> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<String> replies) {
        this.replies = replies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(comment);
        parcel.writeStringList(replies);
    }
}
