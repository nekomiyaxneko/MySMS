package io.github.nekomiyaxneko.mybms;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport{
    @Column(unique = true, defaultValue = "unknown")
    private int id;
    private String student_id;
    private String name;
    private String score;
    public int getId(){
        return id;
    }
    public String getStudent_id(){
        return student_id;
    }
    public String getName(){
        return name;
    }
    public String getScore(){
        return score;
    }
    public void setStudent_Id(String stu_id){
        this.student_id=stu_id;
    }
    public void setName(String name) {
        this.name=name;
    }
    public void setScore(String score) {
        this.score=score;
    }
}
