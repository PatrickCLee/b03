package tw.org.iii.brad.brad03;
//ListView, Toast, SimpleAdapter
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private String[] from = {"field1","field2","field3"}; //此處代表的是key的值,與value無關,故可隨意取名
    //用key抓到value, from指要用的value, to是丟到哪個地方呈現
    private int[] to = {R.id.itemname_title,R.id.itemname_content,R.id.itemname_date};
    //用id指向

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);
        initListView();
    }

    private void initListView(){
        for (int i = 0; i<100; i++){
            HashMap<String, String> row0 = new HashMap<>();
            row0.put(from[0],"value"+i);
            row0.put(from[1],"abble");
            row0.put(from[2],"2020-03-07 10:30:00");
            data.add(row0);
        }

        simpleAdapter = new SimpleAdapter(this,data,R.layout.itemname,from,to);//5個參數,2為資料(需為List,內為Map的第一參數是String),3為要呈現的地方(右鍵專案new一個layout)
        lv.setAdapter(simpleAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showOneItem(position);
            }
        });//listview裡面的項目被點到時OnItemClick
    }

    private void showOneItem(int pos){
        Toast.makeText(this,data.get(pos).get(from[0]),Toast.LENGTH_SHORT).show();//記得show
    }

    public void addItem(View view) {
        HashMap<String, String> row0 = new HashMap<>();
        row0.put(from[0],"value"+(int)(Math.random()*100 + 100));
        row0.put(from[1],"new data");
        row0.put(from[2],"2050-01-01 12:00:00");
        data.add(0,row0);//將新增資料加在起始處
        simpleAdapter.notifyDataSetChanged();
    }
}
