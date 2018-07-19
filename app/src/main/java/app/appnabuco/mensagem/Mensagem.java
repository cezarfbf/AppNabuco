package app.appnabuco.mensagem;

import java.util.Date;

/**
 * Created by cezar on 15/06/2015.
 */
public class Mensagem {

    private String conteudo;
    private int professor;
    private String data;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "conteudo='" + conteudo + '\'' +
                ", professor=" + professor +
                ", data=" + data +
                '}';
    }
}
