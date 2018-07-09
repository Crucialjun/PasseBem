package mz.co.passebem.passebem.exam;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mz.co.passebem.passebem.OnQuestionChangeListener;
import mz.co.passebem.passebem.R;
import mz.co.passebem.passebem.model.Pergunta;

public class ExamActivity extends AppCompatActivity implements OnQuestionChangeListener {

    private static final String TAG = "DataSource";
    private ExamFragment examFragment;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference dataBaseReference = database.getReference("perguntas");
    private List<Pergunta> perguntasList = new ArrayList<>();
    private Pergunta actualPergunta;
    private int mCurrentIndex = 0;
    private int mLastIndex;
    private int firstIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);


        //prepareExam();
        examFragment = new ExamFragment();
        examFragment.setCurrentIndex(mCurrentIndex);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container, examFragment)
                .commit();



    }



    @Override
    public void onNextQuestion(List<Pergunta> perguntasList) {
        mCurrentIndex++;
        if(mCurrentIndex <= perguntasList.size()-1) {
            examFragment.populateView(mCurrentIndex);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, examFragment)
                    .commit();
        }
    }

    @Override
    public void onPreviousQuestion(List<Pergunta> perguntasList) {
        if(mCurrentIndex > perguntasList.size()-1) {
            mCurrentIndex = perguntasList.size() - 2;
        }else if(mCurrentIndex > 0) {
            mCurrentIndex--;
        }

            examFragment.populateView(mCurrentIndex);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, examFragment)
                    .commit();

    }

    @Override
    public List<Pergunta> getList() {
        return perguntasList;
    }


    @Override
    public void showFirstQuestion() {

    }
}
