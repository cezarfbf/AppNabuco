package app.appnabuco;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.appnabuco.bd.ConexaoBD;
import app.appnabuco.disciplina.RepositorioDisciplina;
import app.appnabuco.disciplina.RepositorioDisciplinaImpl;
import app.appnabuco.mensagem.RepositorioMensagem;
import app.appnabuco.mensagem.RepositorioMensagemImpl;
import app.appnabuco.notas_faltas.RepositorioNotasFaltas;
import app.appnabuco.notas_faltas.RepositorioNotasFaltasImpl;
import app.appnabuco.usuario.RepositorioUsuario;
import app.appnabuco.usuario.RepositorioUsuarioImpl;
import app.appnabuco.usuario.Usuario;


public class MainActivity extends ActionBarActivity {

    EditText matricula;
    EditText senha;
    Button btnEntrar;
    Button btnSair;
    Button btnNotas;
    Button btnHorario;
    Button btnEventos;
    Button btnCalendario;
    Button btnMensagem;
    TextView nomeUsuario;
    Usuario usuario;
    ListView listView;
    Button voltar;
    Bundle instacia;
    WebView webView;
    RepositorioUsuario repUsuario = new RepositorioUsuarioImpl();
    RepositorioNotasFaltas repNotasFaltas =new RepositorioNotasFaltasImpl();
    RepositorioMensagem repMsg = new RepositorioMensagemImpl();
    RepositorioDisciplina repDisc = new RepositorioDisciplinaImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instacia = savedInstanceState;

        setContentView(R.layout.login);


        matricula = (EditText) findViewById(R.id.matricula);
        senha = (EditText) findViewById(R.id.senha);
        btnEntrar = (Button) findViewById(R.id.btn_entrar);

        if(ConexaoBD.getConnection() != null) {


            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (!matricula.getText().toString().isEmpty() & !senha.getText().toString().isEmpty()) {
                        usuario = repUsuario.getUsuario(Integer.valueOf(matricula.getText().toString()));
                        //Log.i("Usuario:",""+usuario);

                        if (usuario.getMatricula() > 0) {
                            if (usuario.getSenha().equals(senha.getText().toString())) {
                                setContentView(R.layout.activity_main);
                                funcoesMenu();

                            } else {
                                Toast.makeText(getApplicationContext(), "Matricula/Senha invalidos!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Matricula não existe", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Preencha os campos!", Toast.LENGTH_SHORT).show();
                    }
                }
                // }
            });
        }else {
            AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Erro");
            alert.setMessage("Servidor indisponível");
            alert.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }



    }

    public void funcoesMenu(){
        btnNotas = (Button) findViewById(R.id.btn_notas);
        btnHorario = (Button) findViewById(R.id.btn_quadro_horarios);
        btnSair = (Button) findViewById(R.id.btn_sair);
        btnMensagem = (Button) findViewById(R.id.btn_msg_prof);
        btnEventos = (Button) findViewById(R.id.btn_eventos);
        nomeUsuario = (TextView) findViewById(R.id.nome_usuario);






        if (usuario.getTipo() == 1) {
            nomeUsuario.setText("Professor:"+usuario.getNome());
        }else {
            nomeUsuario.setText("Aluno:"+usuario.getNome());
        }

        btnNotas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                setContentView(R.layout.notas_faltas);
               // System.out.println(usuario.toString());
                voltar = (Button) findViewById(R.id.voltar_notas);
                listView = (ListView) findViewById(R.id.listView_notas);

                if (usuario.getTipo() == 0) {

                    ArrayList itens = repNotasFaltas.getArrayNotasFaltas(usuario);

                    AdapterNotasFaltas adapterNotasFaltas = new AdapterNotasFaltas(MainActivity.this, itens);

                    listView.setAdapter(adapterNotasFaltas);
                }else {
                    ArrayList itens = repDisc.getDisciplinaProfessor(usuario);

                    AdapterDiscProf adapterDiscProf = new AdapterDiscProf(MainActivity.this,itens);
                    listView.setAdapter(adapterDiscProf);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Toast.makeText(MainActivity.this,"voce clicou na disciplina "+view.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);
                        funcoesMenu();
                    }
                });


            }
        });

        /*btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disciplinas = repUsuario.getDisciplinas(usuario);
                System.out.println("disciplina: " + disciplinas.get(0).toString());
                adapterListView = new AdapterHorario(MainActivity.this,disciplinas);
                listView = (ListView) findViewById(R.id.listView_horario);
                listView.setAdapter(adapterListView);

            }
        });*/

        btnMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getTipo() == 1) {
                    setContentView(R.layout.mensagem_prof);
                    enviarMsg();
                }else {
                    setContentView(R.layout.mensagem_aluno);
                    carregarMsg();

                }
            }
        });

        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.eventos);
                voltar = (Button) findViewById(R.id.voltar_eventos);
                webView = (WebView) findViewById(R.id.webView);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);

                webView.loadUrl("http://www.joaquimnabuco.edu.br/evento/listar");
                webView.setWebViewClient(new WebViewClient());
                webView.setWebViewClient(new WebViewEventos());

                voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);
                        funcoesMenu();
                    }
                });

            }
           });

                btnSair.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //setContentView(R.layout.login);
                        finish();
                    }
                });

    }

    public void enviarMsg(){

        Button btnEnviarMsg;
        final TextView mensagem_tv = (TextView) findViewById(R.id.mensagem_tv);
        final EditText escrever = (EditText) findViewById(R.id.mensagem_editText);
        voltar = (Button) findViewById(R.id.voltar_msg);
        btnEnviarMsg = (Button) findViewById(R.id.mensagem_button);
        btnEnviarMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "";
                msg = escrever.getText().toString();
                mensagem_tv.setText(msg);
                repMsg.inserirMensagem(msg, usuario.getMatricula());
                escrever.setText("");

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                funcoesMenu();
            }
        });
    }

    public void carregarMsg(){
        listView = (ListView) findViewById(R.id.listView_mensagens);
        voltar = (Button) findViewById(R.id.voltar_msg);
        ArrayList mensagens = repMsg.getMensagens(2);

        AdapterMensagem adapterMensagem = new AdapterMensagem(MainActivity.this,mensagens);

        listView.setAdapter(adapterMensagem);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                funcoesMenu();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
