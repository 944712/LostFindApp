package swu.lostfindapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

public class JoinActivity extends ActionBarActivity {

    Button btnDone, btnCen;
    EditText joinId,joinName,joinPw,joinPw2;
    Object that;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        that = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnDone = (Button) findViewById(R.id.btnDone);
        btnCen = (Button) findViewById(R.id.btnCancel);
        joinId = (EditText)findViewById(R.id.ID);
        joinName = (EditText)findViewById(R.id.name);
        joinPw = (EditText)findViewById(R.id.etPassword);
        joinPw2=(EditText)findViewById(R.id.etPasswordConfirm);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = joinId .getText().toString();
                String name = joinName.getText().toString();
                String pw = joinPw.getText().toString();
                String pw2 = joinPw2.getText().toString();
                try{
                    Communication communication = new Communication();
                    JSONObject json = new JSONObject();
                    json.put("ID",id);
                    json.put("Name",name);
                    json.put("Pw",pw);
                    json.put("Pw2",pw2);
                    communication.execute(json);

                }
                catch (Exception e){

                }
            }
        });
        btnCen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("join", 2);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
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
                sendHTTPData("http://172.16.8.71:3000/userAdd", obj[0]);

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
                if(resultJson.get("status").toString().equals("error")){
                    Toast.makeText((JoinActivity)that, "비밀번호를 확인 해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(resultJson.get("status").toString().equals("success")){
                        Toast.makeText((JoinActivity)that, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                    }
                    finish();
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


                Log.d("=========", "--------");

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

