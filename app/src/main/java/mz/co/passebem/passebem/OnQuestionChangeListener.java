package mz.co.passebem.passebem;


import java.util.List;

import mz.co.passebem.passebem.model.Pergunta;

public interface OnQuestionChangeListener {

    void onNextQuestion(List<Pergunta> perguntasList);

    void onPreviousQuestion(List<Pergunta> perguntasList);
    List<Pergunta> getList();

    void showFirstQuestion();
}
