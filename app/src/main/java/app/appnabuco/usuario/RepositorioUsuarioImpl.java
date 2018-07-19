package app.appnabuco.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import app.appnabuco.disciplina.Disciplina;
import app.appnabuco.bd.ConexaoBD;

/**
 * Created by cezar on 30/05/2015.
 */
public class RepositorioUsuarioImpl implements RepositorioUsuario{
    @Override
    public Usuario getUsuario(int matricula) {
        Usuario usuario = new Usuario();
        Statement stmt = ConexaoBD.getStatement();
        String sql = "SELECT * FROM USUARIO_COMPLETO WHERE matricula = "+matricula;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector lista = this.gerarUsuario(rs);
        if(lista.size() > 0){
            usuario = (Usuario)lista.elementAt(0);
        }
        return usuario;
    }


    private Vector gerarUsuario(ResultSet rs){
        Vector lista = new Vector();
        Usuario  usuario = new Usuario();
        //Statement stmt = ConexaoBD.getStatement();
        try{
            while(rs.next()) {
                usuario.setMatricula(rs.getInt("matricula"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getInt("tipo"));
                usuario.setTurma(rs.getInt("codigo_turma"));
                usuario.setCurso(rs.getString("descricao_curso"));

                lista.add(usuario);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }




}
