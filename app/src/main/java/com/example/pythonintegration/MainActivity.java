package com.example.pythonintegration;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {
    EditText Et1, Et2;
    Button btn;
    TextView tv;
    Spinner operationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Et1 = findViewById(R.id.num1);
        Et2 = findViewById(R.id.num2);
        btn = findViewById(R.id.button);
        tv = findViewById(R.id.answer);
        operationSpinner = findViewById(R.id.operation_spinner);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("script"); //Here we have to give the name of our Python file.

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.operations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSpinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String operation = operationSpinner.getSelectedItem().toString();
                PyObject obj = pyobj.callAttr("perform_operation", //Here we have to mention the Python Function.
                        operation, Et1.getText().toString(), Et2.getText().toString()); //Maintain the order.
                tv.setText(obj.toString());
            }
        });
    }
}
