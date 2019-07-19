package android.studio.sistemadevendas;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fabricante.this, Main.class);
                startActivity(i);

            }
        });
    }

    public void insert() {
        try {
            String fabricante = ed1.getText().toString();
            String fabricante_desc = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS categoria(id INTEGER PRIMARY KEY AUTOINCREMENT, fabricante VARCHAR, fabdesc VARCHAR)");


            String sql = "insert into fabricante(fabricante, fabdesc)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, fabricante);
            statement.bindString(2, fabricante_desc);
            statement.execute();
            Toast.makeText(this, "Fabricante incluído com sucesso", Toast.LENGTH_SHORT).show();

            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(this, "Fabricante deu pau", Toast.LENGTH_SHORT).show();
        }
    }
}
