package app.appnabuco.notas_faltas;

/**
 * Created by cezar on 30/05/2015.
 */
public class NotasFaltas {

    private int falta;
    private double av1;
    private double av2;
    private double ch2;
    private double fin;
    private String disciplina;
    private int professor;




    public int getFalta() {
        return falta;
    }

    public void setFalta(int falta) {
        this.falta = falta;
    }

    public double getAv1() {
        return av1;
    }

    public void setAv1(double av1) {
        this.av1 = av1;
    }

    public double getAv2() {
        return av2;
    }

    public void setAv2(double av2) {
        this.av2 = av2;
    }

    public double getCh2() {
        return ch2;
    }

    public void setCh2(double ch2) {
        this.ch2 = ch2;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    public double getFin() {
        return fin;
    }

    public void setFin(double fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "NotasFaltas{" +
                "falta=" + falta +
                ", av1=" + av1 +
                ", av2=" + av2 +
                ", ch2=" + ch2 +
                ", fin=" + fin +
                ", disciplina='" + disciplina + '\'' +
                ", professor=" + professor +
                '}';
    }
}
