package swu.lostfindapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LostActivity extends AppCompatActivity implements View.OnClickListener{

    private final int LFRAGMENT1 = 1;
    private final int LFRAGMENT2 = 2;

    private Button lost_tab1, lost_tab2;

    ImageButton writeBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        writeBtn2 = (ImageButton)findViewById(R.id.writeBtn2);
        lost_tab1 = (Button)findViewById(R.id.lost_tab1);
        lost_tab2 = (Button)findViewById(R.id.lost_tab2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lost_tab1.setOnClickListener(this);
        lost_tab2.setOnClickListener(this);
        writeBtn2.setOnClickListener(this);

//        callFragment(LFRAGMENT1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lost_tab1 :
                // '버튼1' 클릭 시 '프래그먼트1' 호출
                callFragment(LFRAGMENT1);
                break;

            case R.id.lost_tab2 :
                // '버튼2' 클릭 시 '프래그먼트2' 호출
                callFragment(LFRAGMENT2);
                break;

            case R.id.writeBtn2 :
                Intent intent = new Intent(LostActivity.this, WriteActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void callFragment(int frament_no) {

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no) {
            case 1:
                // '프래그먼트1' 호출
                LFragment1 fragment1 = new LFragment1();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // '프래그먼트2' 호출
                LFragment2 fragment2 = new LFragment2();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

