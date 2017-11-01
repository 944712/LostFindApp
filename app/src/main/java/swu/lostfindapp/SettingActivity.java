package swu.lostfindapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    TextView instructionsBtn;
    Button qna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        instructionsBtn = (TextView) findViewById(R.id.inst);
        qna = (Button) findViewById(R.id.qna);

        instructionsBtn.setOnClickListener(mClickListener);

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case  R.id.inst:
                    Intent intent = new Intent(SettingActivity.this,InstructionsActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
