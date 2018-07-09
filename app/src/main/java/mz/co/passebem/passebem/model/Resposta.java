package mz.co.passebem.passebem.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Resposta {
    private String descricao;
    private boolean certa;

    public Resposta(){}

    public Resposta(String descricao, boolean certa) {
        this.descricao = descricao;
        this.certa = certa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCerta() {
        return certa;
    }

    public void setCerta(boolean certa) {
        this.certa = certa;
    }
}
