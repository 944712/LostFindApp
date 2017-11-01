package swu.lostfindapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TabHost.OnTabChangeListener{
    TabHost tabHost1;

    ListView listview ;
    ListView listView2;
    ListView listView3;
    ListViewAdapter adapter;
    ListViewAdapter adapter2;
    ListViewAdapter adapter3;

    Object that;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        that = this;

        // Adapter 생성
        adapter = new ListViewAdapter() ;
        adapter2 = new ListViewAdapter();
        adapter3 = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listView2 = (ListView) findViewById(R.id.listview2);
        listView3 = (ListView) findViewById(R.id.listview3);
        listview.setAdapter(adapter);
        listView2.setAdapter(adapter2);
        listView3.setAdapter(adapter3);


//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.if_camera_01_1976059),
//                "Box", "Account Box Black 36dp") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.if_camera_01_1976059),
//                "Box", "Account Box Black 36dp") ;

        adapter2.addItem(ContextCompat.getDrawable(this, R.drawable.if_camera_01_1976059),
                "Box", "Account Box Black 36dp") ;

        adapter3.addItem(ContextCompat.getDrawable(this, R.drawable.if_camera_01_1976059),
                "Box", "Account Box Black 36dp") ;

        Communication comm = new Communication();
        JSONObject json = new JSONObject();
        comm.execute(json);



        Button noticeButtonImage = (Button)findViewById(R.id.noticeButtonImage);
        Button noticeButtonText = (Button)findViewById(R.id.noticeButtonText);
        Button noticeButtonText2 = (Button)findViewById(R.id.noticeButtonText2);
        Button serchButton= (Button)findViewById(R.id.serchButton);

        noticeButtonImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });

        noticeButtonText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });

        noticeButtonText2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        serchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchListActivity.class);
                startActivity(intent);
            }
        });

        tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        tabHost1.setup();
        tabHost1.setOnTabChangedListener(this);

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("찾아요");
        tabHost1.addTab(ts1);

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("있어요");
        tabHost1.addTab(ts2);

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("폐기직전");
        tabHost1.addTab(ts3);

        //Tab에 색 지정
        for (int i = 0; i < tabHost1.getTabWidget().getChildCount(); i++) {
            tabHost1.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#92A8D1"));


        }
        tabHost1.getTabWidget().setCurrentTab(0);
        tabHost1.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#F7CAC9"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.notice) {
            Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
            startActivity(intent);

        } else if (id == R.id.found) {
            Intent intent = new Intent(MainActivity.this, LostActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.lost) {
            Intent intent = new Intent(MainActivity.this, FindActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.disposal) {
            Intent intent = new Intent(MainActivity.this, DisuseActivity.class);
            startActivity(intent);
        } else if (id == R.id.login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabChanged(String tabId) {
        for (int i = 0; i < tabHost1.getTabWidget().getChildCount(); i++) {
            tabHost1.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#92A8D1"));
        }
        tabHost1.getTabWidget().getChildAt(tabHost1.getCurrentTab()).setBackgroundColor(Color.parseColor("#F7CAC9"));
    }
    public class Communication extends AsyncTask<JSONObject, Integer, Long> {
        HttpURLConnection myConnection;
        Activity mainActivity;
        JSONObject resultJson;

        public String sendHTTPData(String urlpath, JSONObject json) {
            HttpURLConnection connection = null;
            try {
                URL url=new URL(urlpath);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter streamWriter =
                        new OutputStreamWriter(connection.getOutputStream());
                streamWriter.write(json.toString());
                streamWriter.flush();
                StringBuilder stringBuilder = new StringBuilder();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(streamReader);
                    String response = null;
                    while ((response = bufferedReader.readLine()) != null) {
                        stringBuilder.append(response + "\n");
                    }
                    bufferedReader.close();

                    Log.d("test", stringBuilder.toString());
                    resultJson = new JSONObject(stringBuilder.toString());
                    return stringBuilder.toString();
                } else {
                    Log.e("test", connection.getResponseMessage());
                    return null;
                }
            } catch (Exception exception){
                Log.e("test", exception.toString());
                return null;
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
        }

        protected void onPreExecute() {
        }

        protected Long doInBackground(JSONObject ...obj) {
//            for (String str : strs) {
//                Log.d("", str);
//            }


            try {
                // Create URL
                sendHTTPData("http://172.16.8.71:3000/getFinderListToday", obj[0]);

            }
            catch(Exception e) {
                Log.d("", e.toString());
            }
            finally {
            }
            return 100L;

        }

        protected void onProgressUpdate(Integer... values) {

        }

        protected void onPostExecute(Long result) {
//            TextView tvResult = (TextView) findViewById(R.id.tvResult);
            try {
                String rslt;
                Log.d("=========", "--------");
                Log.d("", rslt = resultJson.get("status").toString());

                JSONArray arr1 = (JSONArray)resultJson.get("data");
                for(int i = 0; i < arr1.length(); i++) {
                    JSONObject tmpObject = arr1.getJSONObject(i);
                    String tmpTitle = tmpObject.getString("title");
                    String tmpWhat = tmpObject.getString("lostWhat");
                    adapter.addItem(ContextCompat.getDrawable((MainActivity)that, R.drawable.if_camera_01_1976059),
                            tmpTitle,tmpWhat) ;
                }


//                JSONArray arr2 = (JSONArray)resultJson.get("data4");
//                for(int i = 0; i < arr2.length(); i++) {
//                    Log.d("", rslt += arr2.getString(i));
//                    rslt += "/";
//                }


//                JSONObject obj1 = (JSONObject)resultJson.get("data");
//                JSONObject obj2 = (JSONObject)resultJson.get("data2");
//                adapter.addItem(ContextCompat.getDrawable((MainActivity)that, R.drawable.if_camera_01_1976059),
//                        arr1,arr2) ;

//                adapter.addItem(ContextCompat.getDrawable(this.mainActivity, R.drawable.if_camera_01_1976059),
//                        obj1, obj2) ;
//                rslt += "/";

//                JSONObject obj1 = (JSONObject)resultJson.get("data2");
//                Log.d("", rslt += obj1.getString("one2"));
//                rslt += "/";
//
//                JSONArray arr2 = (JSONArray)resultJson.get("data3");
//                JSONObject obj2 = arr2.getJSONObject(0);
//                Log.d("", rslt += obj2.getString("one3"));


//                Log.d("=========", "--------");

//                tvResult.setText(rslt);
            }
            catch(Exception e) {

            }


//            tvResult.setText();
        }

        protected void onCancelled() {

            throw new RuntimeException("Stub!");

        }

    }



}