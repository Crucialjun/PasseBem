package mz.co.passebem.passebem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mz.co.passebem.passebem.exam.ExamActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTextActivity(View view) {
        startActivity(new Intent(getApplicationContext(), ExamActivity.class));
    }
}
