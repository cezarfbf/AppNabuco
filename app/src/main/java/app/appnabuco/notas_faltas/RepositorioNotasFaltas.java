package app.appnabuco.notas_faltas;



import java.util.ArrayList;

import app.appnabuco.usuario.Usuario;

/**
 * Created by cezar on 30/05/2015.
 */
public interface RepositorioNotasFaltas {

    public NotasFaltas getNotasFaltas(Usuario aluno);

    ArrayList getArrayNotasFaltas(Usuario usuario);
}