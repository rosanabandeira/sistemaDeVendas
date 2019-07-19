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

public class FabricanteEditar extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabricante_editar);

        ed1 = findViewById(R.id.fab_id);
        ed2 = findViewById(R.id.fabricante);
        ed3 = findViewById(R.id.faricante_desc);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String fabricante = i.getStringExtra("fabricante").toString();
        String fabdes = i.getStringExtra("fabdes").toString();

        ed1.setText(id);
        ed2.setText(fabricante);
        ed3.setText(fabdes);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main.class);
                startActivity(i);
            }
        });

    }

    public void editar() {
        try {
            String fabid = ed1.getText().toString();
            String fabricante = ed2.getText().toString();
            String fabdes = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            String sql = "update fabricante set fabricante =?,fabdes =? where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, fabricante);
            statement.bindString(2, fabdes);
            statement.bindString(3, fabid);
            statement.execute();
            Toast.makeText(this, "Fabricante atualizado com sucesso", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);


        } catch (Exception ex) {
            Toast.makeText(this, "Fabricante deu pau", Toast.LENGTH_SHORT).show();
        }
    }

    public void deletar() {
        try {
            String fabid = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            String sql = "delete from fabricante where id=?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, fabid);
            statement.execute();
            Toast.makeText(this, "Fabricante deletado com sucesso", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);


        } catch (Exception ex) {
            Toast.makeText(this, "Fabricante deu pau", Toast.LENGTH_SHORT).show();
        }
    }
}
