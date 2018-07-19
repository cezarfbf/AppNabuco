package app.appnabuco;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.appnabuco.disciplina.Disciplina;
import app.appnabuco.usuario.RepositorioUsuario;
import app.appnabuco.usuario.RepositorioUsuarioImpl;


/**
 * Created by Phixa on 29/04/2015.
 */
public class AdapterHorario extends BaseAdapter{
   private LayoutInflater inflater;
    private ArrayList<Disciplina> itens;
    RepositorioUsuario repUsuario = new RepositorioUsuarioImpl();


    public AdapterHorario(Context context, ArrayList<Disciplina> itens){
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
        convertView = inflater.inflate(R.layout.layout_horario,null);
          Log.i("descrição:", repUsuario.getUsuario(disc.getProfessor()).getNome());
        ((TextView)convertView.findViewById(R.id.professor_horario)).setText(repUsuario.getUsuario(disc.getProfessor()).getNome());
        ((TextView)convertView.findViewById(R.id.horario)).setText("");
        ((TextView)convertView.findViewById(R.id.disciplina_horario)).setText(disc.getDescricao());



        return convertView;
    }
}
