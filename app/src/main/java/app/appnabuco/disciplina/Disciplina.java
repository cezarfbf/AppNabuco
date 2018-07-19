package app.appnabuco.disciplina;

/**
 * Created by cezar on 08/06/2015.
 */
public class Disciplina {
    int codigo;
    String descricao;
    int professor;
    int turno;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", professor=" + professor +
                ", turno=" + turno +
                '}';
    }
}
