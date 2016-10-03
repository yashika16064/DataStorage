package com.example.lenovo.datastorage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "students.db";
    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ROLLNO = "rollno";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CGPA = "cgpa";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE " +TABLE_STUDENTS+ "(" +COLUMN_ROLLNO+ " TEXT," +COLUMN_NAME+ " TEXT," +COLUMN_CGPA+ " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS"+TABLE_STUDENTS);
        onCreate(db);
    }

    public String addStudent(Students student){

        ContentValues values=new ContentValues();
        values.put(COLUMN_ROLLNO,student.getRollno());
        values.put(COLUMN_NAME,student.getName());
        values.put(COLUMN_CGPA,student.getCgpa());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_STUDENTS,null,values);
        db.close();
        String out="Value entered";
        return out;
    }

    public String deleteStudent(Students student){

        String roll=student.getRollno();
        String name=student.getName();
        String cgpa=student.getCgpa();
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM " +TABLE_STUDENTS+ " WHERE "+COLUMN_ROLLNO+"=\"" +roll+ "\" AND " +COLUMN_NAME+ "=\"" +name+ "\" AND "
                +COLUMN_CGPA+ "=\"" +cgpa+"\";";
        db.execSQL(query);
        String out="Value deleted";
        return out;

    }

    public String updateStudent(Students student){

        String roll=student.getRollno();
        String name=student.getName();
        String cgpa=student.getCgpa();
        SQLiteDatabase db=getWritableDatabase();
        String query="UPDATE " +TABLE_STUDENTS+ " SET "+COLUMN_NAME+ "=\"" +name+ "\","+COLUMN_CGPA+"=\""+cgpa+"\" WHERE "+COLUMN_ROLLNO+"=\"" +roll+"\";";

        db.execSQL(query);
        String out="Value updated";
        return out;

    }
    /*public String display()
    {
        String rollList="";
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_STUDENTS+" WHERE 1;";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(1)!=null) {
                rollList += c.getString(1);
                rollList += "\n";
            }
        }
        db.close();
        return rollList;

    }*/

}
