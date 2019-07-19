package android.studio.sistemadevendas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class Fabricante extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabricante);

        ed1 = findViewById(R.id.fabricante);
        ed2 = findViewById(R.id.faricante_desc);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);




    }
}
