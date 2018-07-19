package app.appnabuco.usuario;

/**
 * Created by cezar on 30/05/2015.
 */
public class Usuario {
    private int matricula;
    private String senha;
    private String nome;
    private int turma;
    private int tipo;
    private String curso;


    public Usuario(){
        super();
    }

    public int getMatricula() {
        return matricula;
    }
    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "matricula=" + matricula +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", turma=" + turma +
                ", tipo=" + tipo +
                ", curso='" + curso + '\'' +
                '}';
    }
}
