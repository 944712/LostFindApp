package swu.lostfindapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WriteFindActivity extends AppCompatActivity{
    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;
    Spinner findPlaceDrop;
    Button findDateBtn2;
    Button findTimeBtn2;
    EditText titleTxt2, findWhatBtn, placeEdit, others;
    ImageButton uploadBtn2;
    private Uri mImageCaptureUri;
    private ImageButton cameraBtn2;
    private ImageView ivCapture2;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_find);

        titleTxt2 = (EditText)findViewById(R.id.titleTxt2);
        findWhatBtn = (EditText)findViewById(R.id.findWhatBtn);
        placeEdit = (EditText)findViewById(R.id.placeEdit);
        others = (EditText)findViewById(R.id.others);

        findPlaceDrop = (Spinner) findViewById(R.id.findPlaceDrop);
        findDateBtn2 = (Button) findViewById(R.id.findDateBtn2);
        findTimeBtn2 = (Button) findViewById(R.id.findTimeBtn2);
        cameraBtn2 = (ImageButton) findViewById(R.id.cameraBtn2);
        ivCapture2 = (ImageView) findViewById(R.id.ivCapture2);
        uploadBtn2 = (ImageButton)findViewById(R.id.uploadBtn2);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.place, android.R.layout.simple_spinner_item);

        findPlaceDrop.setAdapter(adapter);


        uploadBtn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String title = titleTxt2.getText().toString();
                String findDate = findDateBtn2.getText().toString();
                String findTime = findTimeBtn2.getText().toString();
                String findWhat = findWhatBtn.getText().toString();
                String findPlace = findPlaceDrop.getSelectedItem().toString();
                String place = placeEdit.getText().toString();
                String other = others.getText().toString();
                try{
                    Communication comm = new Communication();
                    JSONObject json = new JSONObject();
                    json.put("title", title);
                    json.put("date", findDate);
                    json.put("time", findTime);
                    json.put("findWhat", findWhat);
                    json.put("place", findPlace);
                    json.put("storage", place);
                    json.put("others", other);
                    json.put("isLost", false);

                    comm.execute(json);
                }
                catch (Exception e){

                }
            }
        });


        findDateBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });
        findTimeBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_TIME);
            }
        });
        cameraBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };

                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                };

                new AlertDialog.Builder(WriteFindActivity.this)
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("사진촬영", cameraListener)
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
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

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            findDateBtn2.setText(year + "년 " +(monthOfYear+1) + "월 " + dayOfMonth + "일");
        }

    };

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch(id){
            case DIALOG_DATE :
                DatePickerDialog dd = new DatePickerDialog
                        (WriteFindActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear,int dayOfMonth) {
                                        findDateBtn2.setText(year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일");
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                2017, 7, 30); // 기본값 연월일
                return dd;
            case DIALOG_TIME :
                TimePickerDialog td =
                        new TimePickerDialog(WriteFindActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view,
                                                          int hourOfDay, int minute) {
                                        findTimeBtn2.setText(hourOfDay +"시 " + minute+"분");
                                    }
                                }, // 값설정시 호출될 리스너 등록
                                9,30, false); // 기본값 시분 등록
                // true : 24 시간(0~23) 표시
                // false : 오전/오후 항목이 생김
                return td;
        }


        return super.onCreateDialog(id);
    }

    private  void doTakePhotoAction(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String url = "tmp_"+String.valueOf(System.currentTimeMillis())+".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }
    private void doTakeAlbumAction()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(resultCode != RESULT_OK)
        {
            return;
        }
        switch(requestCode) {
            case CROP_FROM_CAMERA:
            {
                final Bundle extras = data.getExtras();
                if(extras != null)
                {
                    Bitmap photo = extras.getParcelable("data");
                    ivCapture2.setImageBitmap(photo);
                }
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists())
                {
                    f.delete();
                }

                break;
            }
            case PICK_FROM_ALBUM:
            {
                mImageCaptureUri = data.getData();
            }
            case PICK_FROM_CAMERA:
            {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 90);
                intent.putExtra("outputY", 90);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }

    }


    private boolean isExistsCameraApplication(){
        PackageManager packageManager = getPackageManager();
        Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List cameraApps = packageManager.queryIntentActivities(cameraApp,PackageManager.MATCH_DEFAULT_ONLY);
        return cameraApps.size() > 0;
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
                sendHTTPData("http://172.16.8.71:3000/insertFind", obj[0]);

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
                finish();
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

