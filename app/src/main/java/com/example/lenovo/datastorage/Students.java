package com.example.lenovo.datastorage;


public class Students {

    private String rollno;
    private String name;
    private String cgpa;



    public Students(String rollno, String name, String cgpa){
        this.rollno=rollno;
        this.name=name;
        this.cgpa=cgpa;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }


    public String getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }



}
