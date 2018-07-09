package mz.co.passebem.passebem.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;


public class Pergunta {

    private String descricao;
    private String imagem;
    private Map<String, Resposta> respostas;

    public String getDescricao() {
        return descricao;
    }

    public Pergunta(){}

    public Pergunta(String descricao, String imagem, Map<String, Resposta> respostas) {
        this.descricao = descricao;
        this.imagem = imagem;
        this.respostas = respostas;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Map<String, Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Map<String, Resposta> respostas) {
        this.respostas = respostas;
    }
}
