package com.example.u0151051.toast_select_city;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//List view介面元件稱為選項清單,建立選項清單供使用者選取
public class MainActivity extends AppCompatActivity {
    Spinner sp4, sp5;// 下拉式選單spinner和spinner5
    Button bt;
    TextView tv;
    String[] subcity = null;// 暫存子城市的陣列,實際的子城市陣列已在values資料夾下的strings.xml新增
    String strcity;// 紀錄sp1被選到的同時所放陣列的值(主城市)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    void findview() {
        //第一個spinner要先填入字串陣列(在xml的spinner下加android:entries="@array/陣列名稱")
        sp4 = (Spinner) findViewById(R.id.spinner4);
        // 設監聽器OnItemSelectedListener給spinner4
        sp4.setOnItemSelectedListener(i);
        sp5 = (Spinner) findViewById(R.id.spinner5);
        tv = (TextView) findViewById(R.id.textView);
        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(c);
    }

    // spinner專用監聽器OnItemSelectedListener
    AdapterView.OnItemSelectedListener i = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position == 0) {
                // 利用getResources().getStringArray取得R檔資源
                subcity = getResources().getStringArray(R.array.subcity1);
            } else if (position == 1) {
                subcity = getResources().getStringArray(R.array.subcity2);
            } else {
                subcity = getResources().getStringArray(R.array.subcity3);
            }
            /*
             * 1.ArrayAdapter: 當資料來源是陣列或List集合時，可使用ArrayAdapter
			 *
			 * 2.SimpleCursorAdapter:
			 * 當資料來源是由資料庫(SQLite)查詢的Cursor結果時，可使用SimpleCursorAdapter。
			 *
			 * 3.SimpleAdapter:
			 * 資料來源是類似表格有列與欄的時候，可使用Map集合儲存列，再使用List將每一列收集後，使用SimpleAdapter。
			 *
			 * 4.BaseAdapter: 當有客製化需求時，可繼承BaseAdapter後再自行實作對應的方法。
			 *
			 * 5.BaseAdapter與CursorAdapter是抽象類別，其他ArrayAdapter、
			 * SimpleAdapter與SimpleCursorAdapter都是一般類別，可以直接建構出物件的類別。
			 */
            /*
             * 1.adapter(1:是一個介面2:把資料跟view結合3:並把view放入AdapterView)
			 * 2.AdapterView(像一個容器,裡面裝textview)
			 * 3.ArrayAdapter是Android當中最簡單的adapter，
			 * 它可以連結一個字串陣列到一個只有一個TextView元件的List物件當中
			 * 4.ArrayAdapter(下拉式選單用法):ArrayAdapter(Context context, int resource樣式, T[] objects放入的資料)
			 * 5.android.R.layout.simple_spinner_item式系統內建樣式
			 */
            // 建立一個ArrayAdapter物件，並放置下拉選單的內容
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, subcity);
            // 設定下拉選單的樣式
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            // 當spinner2被按到會開啟ArrayAdapter
            sp5.setAdapter(arrayAdapter);
            strcity = getResources().getStringArray(R.array.city)[position];//給Toast用
            // Toast(推播器)用法:Toast.makeText(context,text顯示的文字,要顯示多久).show();
            // Toast.LENGTH_SHORT (Toast的短時間)
            Toast.makeText(MainActivity.this, "你主要選擇的城市:" + strcity, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    View.OnClickListener c = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s = sp4.getSelectedItem().toString() + "-" + sp5.getSelectedItem();
            tv.setText(s);
        }

    };

}
