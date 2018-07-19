package app.appnabuco.mensagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.appnabuco.bd.ConexaoBD;
import app.appnabuco.notas_faltas.NotasFaltas;

/**
 * Created by cezar on 15/06/2015.
 */
public class RepositorioMensagemImpl implements RepositorioMensagem {
    @Override
    public ArrayList getMensagens(int professor) {
        ArrayList msg;
        Statement stmt = ConexaoBD.getStatement();
        String sql ="select * from mensagem where professor_fk = "+professor+" order by data desc";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        msg = this.gerarArrayMsg(rs);

        return msg;

    }

    private ArrayList gerarArrayMsg(ResultSet rs)
    {
        ArrayList<Mensagem> itens = new ArrayList();
        Mensagem msg;
        try{

            while(rs.next())
            {
                msg = new Mensagem();
                msg.setConteudo(rs.getString("conteudo"));
                msg.setProfessor(rs.getInt("professor_fk"));
                msg.setData(String.valueOf(rs.getDate("data")));


                itens.add(msg);
            }
            rs.close();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println(itens.toString());
        return itens;
    }

    @Override
    public void inserirMensagem(String conteudo, int professor) {
        String sql = "insert into mensagem values(?,?,?)";
        PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
        int i = 1;
        try{
            Statement stmt = ConexaoBD.getStatement();
            String sql1 ="select count(*) as codigo from mensagem";
            ResultSet rs = stmt.executeQuery(sql1);
            int codigo=0;
            while (rs.next()) {
                codigo = rs.getInt("codigo");
            }

                pst.setInt(i++, codigo+1);
                pst.setString(i++, conteudo);
                pst.setInt(i++, professor);
                pst.execute();

            pst.clearParameters();
            pst.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
