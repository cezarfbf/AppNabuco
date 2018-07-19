package app.appnabuco.disciplina;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import app.appnabuco.bd.ConexaoBD;
import app.appnabuco.notas_faltas.NotasFaltas;
import app.appnabuco.usuario.Usuario;

/**
 * Created by cezar on 11/06/2015.
 */
public class RepositorioDisciplinaImpl implements RepositorioDisciplina {

    @Override
    public Disciplina getDisciplina(int codigo) {
        Disciplina disciplina = new Disciplina();
        Statement stmt = ConexaoBD.getStatement();
        String sql = "SELECT * FROM disciplina WHERE codigo_disciplina = "+codigo;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector lista = this.gerarDisc(rs);
        if(lista.size() > 0){
            disciplina = (Disciplina)lista.elementAt(0);
        }
        return disciplina;
    }



    private Vector gerarDisc(ResultSet rs){
        Vector lista = new Vector();
        Disciplina  disciplina = new Disciplina();
        try{
            while(rs.next()) {
                disciplina.setCodigo(rs.getInt("codigo_disciplina"));
                disciplina.setDescricao(rs.getString("nota_av1"));
                disciplina.setProfessor(rs.getInt("nota_av2"));
                disciplina.setTurno(rs.getInt("turno"));

                lista.add(disciplina);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    @Override
    public ArrayList getDisciplinaAluno(Usuario usuario) {


        ArrayList itens;
        Statement stmt = ConexaoBD.getStatement();
        String sql = " select distinct codigo_disciplina, disciplina.descricao, disciplina.professor_fk, disciplina.turno \n" +
                "from disciplina, curso, usuario, turma, curso_disciplina\n" +
                "where \n" +
                "usuario.turma = codigo_turma and \n" +
                "turma.curso_fk = curso_disciplina.curso_fk and\n" +
                "codigo_disciplina = curso_disciplina.disciplina_fk and\n" +
                "matricula= "+usuario.getMatricula();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        itens = this.gerarDisciplina(rs);

        return itens;
    }

    @Override
    public ArrayList getDisciplinaProfessor(Usuario usuario) {


        ArrayList itens;
        Statement stmt = ConexaoBD.getStatement();
        String sql = "select * from disciplina_por_professor where matricula ="+usuario.getMatricula();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        itens = this.gerarDisciplina(rs);

        return itens;
    }

    private ArrayList<Disciplina> gerarDisciplina(ResultSet rs)
    {
        ArrayList<Disciplina> itens = new ArrayList();
        Disciplina disciplina;
        try{

            while(rs.next())
            {
                disciplina = new Disciplina();

                disciplina.setCodigo(rs.getInt("codigo_disciplina"));
                disciplina.setDescricao(rs.getString("descricao"));
                disciplina.setProfessor(rs.getInt("professor_fk"));
                disciplina.setTurno(rs.getInt("turno"));


                itens.add(disciplina);
            }
            System.out.println(itens.toString());
            rs.close();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return itens;
    }
}
