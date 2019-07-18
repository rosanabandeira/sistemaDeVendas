package android.studio.sistemadevendas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText ed1, ed2;
    Button b1, b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ed1 = findViewById(R.id.user);
        ed2 = findViewById(R.id.pass);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

    }


    public void Login() {
        String username = ed1.getText().toString();
        String password = ed2.getText().toString();

        if (username.trim().length() == 0 || password.trim().length() == 0) {
            Toast.makeText(this, "Usuário e senha não pode ser nulo", Toast.LENGTH_SHORT).show();
        } else {
            if (username.equals("san") && password.equals("123")) {
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Login.this, Main.class);
                        startActivity(i);
                    }
                });
            } else {
                Toast.makeText(this, "Usuário ou senha não corresponde", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

