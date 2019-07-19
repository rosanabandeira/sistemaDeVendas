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

public class Produto extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        ed1 = findViewById(R.id.produto);
        ed2 = findViewById(R.id.produto_desc);
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
                Intent i = new Intent(Produto.this, Main.class);
                startActivity(i);

            }
        });
    }

    public void insert() {
        try {
            String produto = ed1.getText().toString();
            String produto_desc = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS produto(id INTEGER PRIMARY KEY AUTOINCREMENT, produto VARCHAR, proddes VARCHAR)");


            String sql = "insert into produto(produto, proddes)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, produto);
            statement.bindString(2, produto_desc);
            statement.execute();
            Toast.makeText(this, "Produto incluído com sucesso", Toast.LENGTH_SHORT).show();

            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(this, "Produto deu pau", Toast.LENGTH_SHORT).show();
        }
    }
}
