package com.example.helloworld.core;
import javax.validation.constraints.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "LearningAssistants")
@NamedQueries(
	    {
	        @NamedQuery(
	            name = "com.example.helloworld.core.LA.findAll",
	            query = "SELECT p FROM LA p"
	        )
	    }
	)
public class LA {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "weekstartdate", nullable = false)
    private String weekstartdate;
    @NotNull
    @Column(name = "day", nullable = false)
    private String day;
    @NotNull
    @Column(name = "start", nullable = false)
    private int start;
    @NotNull
    @Column(name = "end", nullable = false)
    private int end;
    @NotNull
    @Column(name = "gender", nullable = false)
    private String gender;
    @NotNull
    @Column(name = "experiencelevel", nullable = false)
    private int experiencelevel;
    @NotNull
    @Column(name = "hourscapacity", nullable = false)
    private int hourscapacity;


    public LA() {
    }
    public LA(String name, String day, int start, int end, String weekstartdate,String gender, int experiencelevel, int hourscapacity) {
    this.name = name;
    this.day= day;
    this.end = end;
    this.weekstartdate=weekstartdate;
    this.start = start;
    this.gender = gender;
    this.experiencelevel = experiencelevel;
    this.hourscapacity = hourscapacity;
    }
 public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
   public String getName() {
        return name;
    }
    public String getGender(){
        return gender;
    }
      public void setGender(String gender){
        this.gender= gender;
    }
    public int getHoursCapacity(){
        return hourscapacity;
    }
    public void setHoursCapacity(int hourscapacity){
        this.hourscapacity= hourscapacity;
    }
    public int getExperienceLevel(){
        return experiencelevel;
    }
    public void setExperienceLevel(int experiencelevel){
        this.experiencelevel= experiencelevel;
    }
    public void setWeekStartDate(String weekstartdate) {
        this.weekstartdate = weekstartdate;
    }

    public String getWeekStartDate() {
        return weekstartdate;
    }

    public void setFullName(String fullName) {
        this.name = fullName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
