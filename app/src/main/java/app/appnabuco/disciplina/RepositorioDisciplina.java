package app.appnabuco.disciplina;

import java.util.ArrayList;

import app.appnabuco.usuario.Usuario;

/**
 * Created by cezar on 11/06/2015.
 */
public interface RepositorioDisciplina {

    Disciplina getDisciplina(int codigo);
    ArrayList getDisciplinaAluno(Usuario usuario);
    ArrayList getDisciplinaProfessor(Usuario usuario);
}
