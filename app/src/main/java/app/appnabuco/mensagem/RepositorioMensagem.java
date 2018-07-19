package app.appnabuco.mensagem;

import java.util.ArrayList;

/**
 * Created by cezar on 15/06/2015.
 */
public interface RepositorioMensagem {

    ArrayList getMensagens(int professor);

    void inserirMensagem(String conteudo,int professor);
}
