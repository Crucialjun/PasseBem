package mz.co.passebem.passebem.mapper;

import mz.co.passebem.passebem.entity.PerguntaEntity;
import mz.co.passebem.passebem.model.Pergunta;

public class PerguntaMapper extends FirebaseMapper<PerguntaEntity, Pergunta>{
    @Override
    public Pergunta map(PerguntaEntity perguntaEntity) {
        Pergunta p = new Pergunta();
        p.setDescricao(perguntaEntity.getDescricao());
        p.setImagem(perguntaEntity.getImagem());
        p.setRespostas(perguntaEntity.getRespostas());
        return p;
    }
}
