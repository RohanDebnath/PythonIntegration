package com.example.pythonintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {
EditText Et1,Et2;
Button btn;
TextView tv;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Et1=findViewById(R.id.num1);
        Et2=findViewById(R.id.num2);
        btn=findViewById(R.id.button);
        tv=findViewById(R.id.answer);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyobj=py.getModule("script"); //Here we have to give the name of our python file


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Function Name and Argument
               PyObject obj=pyobj.callAttr("main", Et1.getText().toString(),
                                                Et2.getText().toString()
                                                );
               tv.setText(obj.toString());

            }
        });
    }
}