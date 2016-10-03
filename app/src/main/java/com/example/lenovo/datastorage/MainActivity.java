package com.example.lenovo.datastorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText roll;
    EditText name;
    EditText cgpa;
    TextView textView;
    MyDBHandler dbHandler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        roll = (EditText) findViewById(R.id.rollno);
        name = (EditText) findViewById(R.id.name);
        cgpa = (EditText) findViewById(R.id.cgpa);
        Button viewSubmit = (Button) findViewById(R.id.viewSubmit);
        Button submit = (Button) findViewById(R.id.submit);
        Button update = (Button) findViewById(R.id.update);
        Button delete = (Button) findViewById(R.id.delete);

        dbHandler = new MyDBHandler(this, null, null, 1);
        // info();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void viewSubmitPress(View v) {

        String sroll = roll.getText().toString();
        String sname = name.getText().toString();
        String scgpa = cgpa.getText().toString();
        SharedPreferences sharedPrefers = getSharedPreferences("prefers", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefers.edit();
        editor.putString("myroll", sroll);
        editor.putString("myname", sname);
        editor.putString("mycgpa", scgpa);
        editor.commit();

        startActivity(new Intent(getApplicationContext(), DisplayActivity.class));
    }

    public void onSubmit(View v) {
        String sroll = roll.getText().toString();
        String sname = name.getText().toString();
        String scgpa = cgpa.getText().toString();
        Students student = new Students(sroll, sname, scgpa);
        String out=dbHandler.addStudent(student);
        Toast t = Toast.makeText(getApplicationContext(),out, Toast.LENGTH_SHORT);
        t.show();
        roll.setText("");
        name.setText("");
        cgpa.setText("");
        // info();
    }

    public void onUpdate(View v) {

        String sroll = roll.getText().toString();
        String sname = name.getText().toString();
        String scgpa = cgpa.getText().toString();
        Students student = new Students(sroll, sname, scgpa);
        String out=dbHandler.updateStudent(student);
        Toast t = Toast.makeText(getApplicationContext(),out, Toast.LENGTH_SHORT);
        t.show();
        roll.setText("");
        name.setText("");
        cgpa.setText("");
    }

    public void onDelete(View v) {
        String sroll = roll.getText().toString();
        String sname = name.getText().toString();
        String scgpa = cgpa.getText().toString();
        Students student = new Students(sroll, sname, scgpa);
        String out=dbHandler.deleteStudent(student);
        Toast t = Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT);
        t.show();
        roll.setText("");
        name.setText("");
        cgpa.setText("");
        //info();


    }

    public void writeToExternal(View v) {
        String state;
        state = Environment.getExternalStorageState();
        File file;
        File filePrivate;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File Root =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) ;
            File dir = new File(Root.getAbsolutePath() + "/Roll");

            File RootPrivate = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File dirPrivate=new File(RootPrivate.getAbsolutePath() +"/RollPrivate");

            file=new File(dir,"MyRoll.txt");
            filePrivate=new File(dirPrivate,"MyRoll.txt");

            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!dirPrivate.exists()) {
                dirPrivate.mkdir();
            }

            String rollList = roll.getText().toString();
            FileOutputStream outputStream;
            FileOutputStream outputStream1;

            try {
                outputStream = new FileOutputStream(file);
                outputStream1=new FileOutputStream(filePrivate);
                outputStream.write(rollList.getBytes());
                outputStream1.write(rollList.getBytes());
                outputStream.close();
                outputStream1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No media found", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
        roll.setText("");
        name.setText("");
        cgpa.setText("");

    }

    public void writeToInternal(View v)
    {
        File file;
        String rollList = roll.getText().toString();
        String filename="ROLLno.txt";
        File Root =this.getFilesDir() ;
        File dir = new File(Root.getAbsolutePath() + "/Roll");

        if (!dir.exists()) {
            dir.mkdir();
        }
        file=new File(dir,"MyRollNO.txt");

        FileOutputStream outputStream= null;
        try {
            outputStream =new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            outputStream.write(rollList.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
        roll.setText("");
        name.setText("");
        cgpa.setText("");

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lenovo.datastorage/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lenovo.datastorage/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /*public void info(){
        String rollnos=dbHandler.display();
        textView.setText(rollnos);

    }*/

}
