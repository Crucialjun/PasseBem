package mz.co.passebem.passebem.exam;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mz.co.passebem.passebem.OnQuestionChangeListener;
import mz.co.passebem.passebem.R;
import mz.co.passebem.passebem.model.Answer;
import mz.co.passebem.passebem.model.Pergunta;
import mz.co.passebem.passebem.model.Resposta;
import mz.co.passebem.passebem.viewmodel.ExamViewModel;

public class ExamFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "ExamFragment";


//    @BindView(R.id.image_pergunta)
//    public ImageView imagePergunta;
    @BindView(R.id.radio_group)
    public RadioGroup radioGroupRespostas;

    @BindView(R.id.text_pergunta_actual)
    public TextView textPerguntaActual;

    @BindView(R.id.radio_a)
    public RadioButton textRespostaA;

    @BindView(R.id.radio_b)
    public RadioButton textRespostaB;

    @BindView(R.id.radio_c)
    public RadioButton textRespostaC;

    @BindView(R.id.radio_d)
    public RadioButton textRespostaD;

    @BindView(R.id.text_numero_pergunta)
    public TextView textViewNumeroPergunta;

    @BindView(R.id.button_anterior)
    public Button buttonAnterior;

    @BindView(R.id.button_seguinte)
    public Button buttonSeguinte;

    @BindView(R.id.progress_bar)
    public ProgressBar progressBar;

    @BindView(R.id.scroll_view)
    public ScrollView scrollView;



    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference dataBaseReference = database.getReference("perguntas");
    private List<Pergunta> perguntasList = new ArrayList<>();
    private Animation mBounceAnimation;
    OnQuestionChangeListener onQuestionChangeListener;
    private int nextIndex;
    private int prevIndex;
    private int currentIndex;
    private View view;
    private Pergunta actualPergunta;
    private int mCurrentIndex = 0;
    private List<Answer> answersList;
    private boolean checked;


    public ExamFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.question_fragment, container, false);
        ButterKnife.bind(this, view);
        answersList = new ArrayList<>();
        mBounceAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
        radioGroupRespostas.setOnCheckedChangeListener(this);


        ExamViewModel examViewModel = ViewModelProviders.of(this).get(ExamViewModel.class);
        showProgress();
        LiveData<List<Pergunta>> liveData = examViewModel.getPerguntas();
        liveData.observe(this, new Observer<List<Pergunta>>() {
            @Override
            public void onChanged(@Nullable List<Pergunta> perguntas) {
                if(perguntas != null){
                    perguntasList = perguntas;
                    hideProgress();
                    populateView(currentIndex);
                }
            }

        });


        buttonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //onQuestionChangeListener.onPreviousQuestion(perguntasList);
//                if(!checked)
//                    saveAnswer(-1, mCurrentIndex);

                if(mCurrentIndex > perguntasList.size()-1) {
                    mCurrentIndex = perguntasList.size() - 2;
                }else if(mCurrentIndex > 0) {
                    mCurrentIndex--;
                }
                //checked = false;
                //radioGroupRespostas.clearCheck();
                populateView(mCurrentIndex);




            }
        });

        buttonSeguinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!checked)
//                    saveAnswer(-1, mCurrentIndex);
                mCurrentIndex++;
                if(mCurrentIndex <= perguntasList.size()-1) {
                    //radioGroupRespostas.clearCheck();
                    populateView(mCurrentIndex);
                }
                //checked = false;
            }
        });

        return view;
    }

    private void getAnswer(int mCurrentIndex) {
        Log.i(TAG, "size "+answersList.size());
        for(Answer answer: answersList){
            Log.i(TAG, "respostaIndex: "+answer.getIndex()+" id: "+answer.getCheckedId());
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = getActivity().findViewById(checkedId);
        //radioButton.startAnimation(mBounceAnimation);
//        this.checked = true;
//        if(radioButton != null) {
//            if (radioButton.isChecked()) {
//                saveAnswer(checkedId, mCurrentIndex);
//            }
//        }
    }

//    private void saveAnswer(int checkedId, int mCurrentIndex) {
//        Log.i(TAG, "Check: " + checkedId + " indexNav: " + mCurrentIndex);
//        //this.answersList.set(mCurrentIndex, new Answer(checkedId, mCurrentIndex));
////        if (this.answersList. == 0) {
//            this.answersList.add(mCurrentIndex, new Answer(checkedId, mCurrentIndex));
////        }
////
////        if (this.answersList.get(mCurrentIndex) != null){
////            this.answersList.set(mCurrentIndex, new Answer(checkedId, mCurrentIndex));
////        }else {
////            this.answersList.add(mCurrentIndex, new Answer(checkedId, mCurrentIndex));
////        }
//
//        getAnswer(mCurrentIndex);
//
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
           onQuestionChangeListener  = (OnQuestionChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onQuestionChangeListener");
        }
    }

    public void setPerguntaToView(Pergunta pergunta){
        this.actualPergunta = pergunta;
    }


    public int getListSize(){
        return perguntasList.size();
    }



    public void populateView(int currentIndex){
        Pergunta pergunta = perguntasList.get(currentIndex);
        Map<String, Resposta> respostas = pergunta.getRespostas();
        int position = 1;
        textViewNumeroPergunta.setText((currentIndex+1)+"");
        textPerguntaActual.setText(pergunta.getDescricao());
        initFields(currentIndex);

        for (Map.Entry<String, Resposta> resposta : respostas.entrySet())
        {

            switch (position){
                case  1: textRespostaA.setVisibility(View.VISIBLE);
                    textRespostaA.setText(resposta.getValue().getDescricao()); break;

                case 2: textRespostaB.setVisibility(View.VISIBLE);
                    textRespostaB.setText(resposta.getValue().getDescricao());break;

                case 3: textRespostaC.setVisibility(View.VISIBLE);
                    textRespostaC.setText(resposta.getValue().getDescricao());break;

                case 4: textRespostaD.setVisibility(View.VISIBLE);
                    textRespostaD.setText(resposta.getValue().getDescricao());break;

                default: break;
            }
            position++;
        }



    }

    void initFields(int currentIndex){
        textRespostaA.setVisibility(View.GONE);
        textRespostaB.setVisibility(View.GONE);
        textRespostaC.setVisibility(View.GONE);
        textRespostaD.setVisibility(View.GONE);
        if(currentIndex == 0) {
            buttonAnterior.setVisibility(View.GONE);
        }else{
            buttonAnterior.setVisibility(View.VISIBLE);
        }

        //if(radioGroupRespostas != null){
            //radioGroupRespostas.clearCheck();
        //}

        if(currentIndex == (perguntasList.size()-1)){
            buttonSeguinte.setText("Terminar");
        }else{
            buttonSeguinte.setText("Próxima");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
    }


    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }


    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }
}
