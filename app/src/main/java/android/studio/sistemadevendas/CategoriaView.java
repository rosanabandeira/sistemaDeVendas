package android.studio.sistemadevendas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoriaView extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_view);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select * from categoria", null);
        int id = c.getColumnIndex("id");
        int categoria = c.getColumnIndex("categoria");
        int catdes = c.getColumnIndex("catdes");

        titles.clear();

        arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, titles);

        lst1.setAdapter(arrayAdapter);
        final ArrayList<Cate> cates = new ArrayList<Cate>();

        if (c.moveToNext()) {
            do {
                Cate ca = new Cate();
                ca.id = c.getString(id);
                ca.categoria = c.getString(categoria);
                ca.descricao = c.getString(catdes);
                cates.add(ca);

                titles.add(c.getString(id) + "\t" + c.getString(categoria) + "\t" + c.getString(catdes));

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                Cate ca = cates.get((position));
                Intent i = new Intent(getApplicationContext(), CategoriaEditar.class);
                i.putExtra("id", ca.id);
                i.putExtra("categoria", ca.categoria);
                i.putExtra("catdes", ca.descricao);

                startActivity(i);

            }
        });
    }
}
