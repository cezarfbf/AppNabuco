package app.appnabuco.notas_faltas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import app.appnabuco.bd.ConexaoBD;
import app.appnabuco.usuario.Usuario;

/**
 * Created by cezar on 30/05/2015.
 */
public class RepositorioNotasFaltasImpl implements RepositorioNotasFaltas {

    @Override
    public NotasFaltas getNotasFaltas(Usuario aluno) {
        NotasFaltas nf = new NotasFaltas();
        Statement stmt = ConexaoBD.getStatement();
        String sql = "SELECT * FROM  WHERE aluno_fk = "+aluno.getMatricula();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector lista = this.gerarNotasFaltas(rs);
        if(lista.size() > 0){
            nf = (NotasFaltas)lista.elementAt(0);
        }
        return nf;
    }



    private Vector gerarNotasFaltas(ResultSet rs){
        Vector lista = new Vector();
        NotasFaltas  nf = new NotasFaltas();
        //Statement stmt = ConexaoBD.getStatement();
        try{
            while(rs.next()) {
                nf.setFalta(rs.getInt("faltas"));
                nf.setAv1(rs.getDouble("nota_av1"));
                nf.setAv2(rs.getDouble("nota_av2"));
                nf.setCh2(rs.getDouble("nota_sc"));
                nf.setProfessor(rs.getInt("professor_fk"));

                lista.add(nf);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public ArrayList getArrayNotasFaltas(Usuario usuario) {
        ArrayList notasFaltas;
        Statement stmt = ConexaoBD.getStatement();
        String sql ="select * from notas_faltas_completa where matricula = "+usuario.getMatricula();

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        notasFaltas = this.gerarArrayNotasFaltas(rs);

        return notasFaltas;

    }

    private ArrayList gerarArrayNotasFaltas(ResultSet rs)
    {
        ArrayList<NotasFaltas> itens = new ArrayList();
        NotasFaltas nf;
        try{

            while(rs.next())
            {
                nf = new NotasFaltas();

                nf.setFalta(rs.getInt("faltas"));
                nf.setAv1(rs.getDouble("nota_av1"));
                nf.setAv2(rs.getDouble("nota_av2"));
                nf.setCh2(rs.getDouble("nota_sc"));
                nf.setFin(rs.getDouble("final"));
                nf.setDisciplina(rs.getString("disciplina"));
                nf.setProfessor(rs.getInt("professor_fk"));



                itens.add(nf);
            }
            rs.close();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println(itens.toString());
        return itens;
    }
}
