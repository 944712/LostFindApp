package swu.lostfindapp;

import java.sql.Time;
import java.util.Date;

public class list_item {
    private String profile_image;
    private String title;
    private String what;
    private String findPlaceDrop;
    private String placeEdit;
    private Date write_date;
    private Time time;
    private String content;

    public String getWhat() {return what;}
    public void setWhat(String what) {this.what = what;}

    public String getFindPlaceDrop() {return findPlaceDrop;}
    public void setFindPlaceDrop(String findPlaceDrop) {this.findPlaceDrop = findPlaceDrop;}

    public String getPlaceEdit() {return placeEdit;}
    public void setPlaceEdit(String placeEdit) {this.placeEdit = placeEdit;}

    public Time getTime() {return time;}
    public void setTime(Time time) {this.time = time;}

    public  String getProfile_image(){return profile_image;}
    public void setProfile_image(String profile_image) {this.profile_image = profile_image; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWrite_date() {
        return write_date;
    }
    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


    public list_item(String profile_image, String title, String what, String findPlaceDrop, String placeEdit, Date write_date, Time time, String content) {
        this.profile_image = profile_image;
        this.title = title;
        this.what = what;
        this.findPlaceDrop = findPlaceDrop;
        this.placeEdit = placeEdit;
        this.write_date = write_date;
        this.time = time;
        this.content = content;
    }
}