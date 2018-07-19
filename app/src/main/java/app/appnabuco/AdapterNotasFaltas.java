package app.appnabuco;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.appnabuco.disciplina.Disciplina;
import app.appnabuco.notas_faltas.NotasFaltas;
import app.appnabuco.notas_faltas.RepositorioNotasFaltas;
import app.appnabuco.notas_faltas.RepositorioNotasFaltasImpl;
import app.appnabuco.usuario.RepositorioUsuario;
import app.appnabuco.usuario.RepositorioUsuarioImpl;

/**
 * Created by cezar on 13/06/2015.
 */
public class AdapterNotasFaltas extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<NotasFaltas> itens;
    RepositorioUsuario repUsuario = new RepositorioUsuarioImpl();

    public AdapterNotasFaltas(Context context, ArrayList<NotasFaltas> itens){
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

        NotasFaltas nf = itens.get(position);
        convertView = inflater.inflate(R.layout.layout_notas,null);

        String disciplina = nf.getDisciplina().toUpperCase();
        String professor = repUsuario.getUsuario(nf.getProfessor()).getNome();
        String faltas = String.valueOf(nf.getFalta());
        double av1 = nf.getAv1();
        double av2 = nf.getAv2();
        double ch2 = nf.getCh2();
        double fin = nf.getFin();
        double media = 0;

        if (av1 > 0 && av2 > 0 && (av1+av2)/2 >= 3){
            media = (av1+av2)/2;
        }else if (av1>0 && ch2>0 && (av1+ch2)/2 >= 3){
            media = (av1+ch2)/2;
        }else if (av2>0 && ch2>0 && (ch2+av2)/2 >= 3){
            media = (ch2+av2)/2;
        }else{
            media = 0;
        }
        if (media > 3 && media<7 && fin> 0){
            media = (media+fin)/2;
        }


        ((TextView)convertView.findViewById(R.id.disciplina)).setText(disciplina);
        ((TextView)convertView.findViewById(R.id.professor)).setText(professor);
        ((TextView)convertView.findViewById(R.id.faltas)).setText(faltas);
        ((TextView)convertView.findViewById(R.id.av1)).setText(String.valueOf(av1));
        ((TextView)convertView.findViewById(R.id.av2)).setText(String.valueOf(av2));
        ((TextView)convertView.findViewById(R.id.sc)).setText(String.valueOf(ch2));
        ((TextView)convertView.findViewById(R.id.fin)).setText(String.valueOf(fin));
        if (media>7){
            ((TextView)convertView.findViewById(R.id.media)).setTextColor(Color.BLUE);
            ((TextView)convertView.findViewById(R.id.media)).setText(String.valueOf(media));
        }else if (media > 0 && media<7){
            ((TextView)convertView.findViewById(R.id.media)).setTextColor(Color.RED);
            ((TextView)convertView.findViewById(R.id.media)).setText(String.valueOf(media));
        }else {
            ((TextView)convertView.findViewById(R.id.media)).setText(String.valueOf(media));
        }

        return convertView;
    }
}
