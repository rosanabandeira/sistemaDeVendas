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

public class CategoriaEditar extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_editar);

        ed1 = findViewById(R.id.cat_id);
        ed2 = findViewById(R.id.categoria);
        ed3 = findViewById(R.id.categoria_desc);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String categoria = i.getStringExtra("categoria").toString();
        String descricao = i.getStringExtra("catdes").toString();

        ed1.setText(id);
        ed2.setText(categoria);
        ed3.setText(descricao);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();

            }
        });
    }

    public void editar() {
        try {
            String catid = ed1.getText().toString();
            String categoriaNome = ed2.getText().toString();
            String catDescricao = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            String sql = "update categoria set categoria =?,catdes =? where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, categoriaNome);
            statement.bindString(2, catDescricao);
            statement.bindString(3, catid);
            statement.execute();
            Toast.makeText(this, "Categoria atualizada com sucesso", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);


        } catch (Exception ex) {
            Toast.makeText(this, "Categoria deu pau", Toast.LENGTH_SHORT).show();
        }
    }

    public void deletar() {
        try {
            String catid = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            String sql = "delete from categoria where id=?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, catid);
            statement.execute();
            Toast.makeText(this, "Categoria deletada com sucesso", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);


        } catch (Exception ex) {
            Toast.makeText(this, "Categoria deu pau", Toast.LENGTH_SHORT).show();
        }
    }
}
