package mz.co.passebem.passebem.repository;

import mz.co.passebem.passebem.mapper.PerguntaMapper;
import mz.co.passebem.passebem.model.Pergunta;

public class PerguntaRepository extends FirebaseDatabaseRepository<Pergunta> {

    public PerguntaRepository() {
        super(new PerguntaMapper());
    }

    @Override
    protected String getRootNode() {
        return "perguntas";
    }
}
