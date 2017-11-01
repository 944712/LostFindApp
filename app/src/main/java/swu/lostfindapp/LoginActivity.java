package swu.lostfindapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginActivity extends ActionBarActivity {
    Button loginBtn,joinBtn;
    EditText etID,etPw;
    public static final int REQUEST_CODE_JOIN = 1001;
    Object that;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginBtn = (Button)findViewById(R.id.butLogin);
        joinBtn = (Button) findViewById(R.id.butJoin);
        etID = (EditText)findViewById(R.id.ID);
        etPw=(EditText)findViewById(R.id.password);
        that = this;

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivityForResult(intent, REQUEST_CODE_JOIN);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String id  = etID.getText().toString();
                String pw = etPw.getText().toString();
                try {
                    Communication communication = new Communication();
                    JSONObject json = new JSONObject();
                    json.put("userId",id);
                    json.put("password",pw);
                    communication.execute(json);
                }
                catch (Exception e){
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_x) {
            Toast.makeText(this, "닫기", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE_JOIN) {
            if (resultCode == RESULT_OK) {
                int join = intent.getExtras().getInt("join");

                switch (join) {
                    case 1:
                        Toast.makeText(this, "가입", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
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
                sendHTTPData("http://172.16.8.71:3000/userLogin", obj[0]);

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
//                Log.d("=========", "--------");
                Log.d("", rslt = resultJson.get("status").toString());
                if(resultJson.get("status").toString().equals("success")){
                    finish();
                }
                else {
                    Toast.makeText((LoginActivity)that, resultJson.get("status").toString(), Toast.LENGTH_SHORT).show();
                }
//                rslt += "/";
//                JSONArray arr1 = (JSONArray)resultJson.get("data");
//                for(int i = 0; i < arr1.length(); i++) {
//                    Log.d("", rslt += arr1.getString(i));
//                    rslt += "/";
//                }
//
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