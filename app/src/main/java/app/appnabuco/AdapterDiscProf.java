package app.appnabuco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import app.appnabuco.disciplina.Disciplina;
import app.appnabuco.mensagem.Mensagem;
import app.appnabuco.usuario.RepositorioUsuario;
import app.appnabuco.usuario.RepositorioUsuarioImpl;

/**
 * Created by cezar on 16/06/2015.
 */
public class AdapterDiscProf extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Disciplina> itens;


    public AdapterDiscProf(Context context, ArrayList<Disciplina> itens){
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

        Disciplina disc = itens.get(position);
        convertView = inflater.inflate(R.layout.layout_disc_prof,null);
        String disciplina = disc.getDescricao().toUpperCase();

                ((Button) convertView.findViewById(R.id.button)).setText(disciplina);


        return convertView;
    }
}
