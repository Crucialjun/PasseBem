package mz.co.passebem.passebem.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import mz.co.passebem.passebem.model.Pergunta;
import mz.co.passebem.passebem.repository.FirebaseDatabaseRepository;
import mz.co.passebem.passebem.repository.PerguntaRepository;

public class ExamViewModel extends ViewModel {
    private PerguntaRepository repository = new PerguntaRepository();
    private MutableLiveData<List<Pergunta>> perguntas;
    private final MutableLiveData<Pergunta> selected = new MutableLiveData<Pergunta>();

    public LiveData<List<Pergunta>> getPerguntas() {
        if (perguntas == null) {
            perguntas = new MutableLiveData<>();
            loadPerguntas();
        }
        return perguntas;
    }



    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void loadPerguntas() {
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<Pergunta>() {
            @Override
            public void onSuccess(List<Pergunta> result) {
                perguntas.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                perguntas.setValue(null);
            }
        });
    }
}
