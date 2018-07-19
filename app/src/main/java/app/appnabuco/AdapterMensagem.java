package app.appnabuco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.appnabuco.mensagem.Mensagem;
import app.appnabuco.notas_faltas.NotasFaltas;
import app.appnabuco.usuario.RepositorioUsuario;
import app.appnabuco.usuario.RepositorioUsuarioImpl;
import app.appnabuco.usuario.Usuario;

/**
 * Created by cezar on 16/06/2015.
 */
public class AdapterMensagem extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Mensagem> itens;
    RepositorioUsuario repUsuario = new RepositorioUsuarioImpl();

    public AdapterMensagem(Context context, ArrayList<Mensagem> itens){
        this.itens = itens;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mensagem msg = itens.get(position);
        convertView = inflater.inflate(R.layout.layout_mensagens,null);

        String professor = repUsuario.getUsuario(msg.getProfessor()).getNome();

        ((TextView) convertView.findViewById(R.id.professor_mensagem)).setText(professor);
        ((TextView)convertView.findViewById(R.id.mensagem)).setText(msg.getConteudo());
        ((TextView)convertView.findViewById(R.id.data_mensagem)).setText(msg.getData());

        return convertView;
    }
}
