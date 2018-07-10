package br.com.tiberio.fechamentoexterno.atividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.tiberio.fechamentoexterno.R;
import br.com.tiberio.fechamentoexterno.adapter.PesquisarList;
import br.com.tiberio.fechamentoexterno.adapter.PesquisarListAdapter;
import br.com.tiberio.fechamentoexterno.task.TaskPesquisarArquivos;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

public class Pesquisar extends Activity {

    ListView lstResultados;
    List<PesquisarList> pesquisas = null;
    String usuario = "";
    String pass="";
    String nomearquivo = "";
    String btnVoltarPesquisa = "";
    EditText editsearch;
    WebServiceSoap wssoap;
    String[] arrayPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pesquisar );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );


        Bundle extras = getIntent().getExtras();
        usuario = extras.getString( "user" );
        pass = extras.getString( "pass" );
        btnVoltarPesquisa = extras.getString( "btnvoltarpesquisa" );
        nomearquivo = extras.getString( "nomearquivo" );


        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar2);

        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal(usuario);
            }
        } );

        lstResultados = findViewById( R.id.listview );
        lstResultados.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                //Buscar  o nome do item selecionado para passar para proxima tela
                TextView nomeitemselecionado = view.findViewById( R.id.nameLabel );
                nomearquivo = nomeitemselecionado.getText().toString();

                if (nomearquivo.equals("Não encontrou arquivo(s) para o(s) termo(s) digitado(s) !")) {
                    Toast.makeText( Pesquisar.this, nomearquivo, Toast.LENGTH_SHORT ).show();
                }else{
                    //Abrir a Tela de Detalhe do Arquivo
                    abrirDetalheArquivo(usuario, nomearquivo);

                }



            }
        } );


        // Locate the EditText in listview in Pesquisa.xml
        editsearch = (EditText) findViewById( R.id.home_search );
        editsearch.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //Pesquisar o texto digitado
                PesquisarArquivos(usuario,editsearch.getText().toString());
                return true;
                //return false;
            }
        } );



    }



    public void PesquisarArquivos(String usuario, String termopesquisado ){

        pesquisas = new ArrayList<>();

        ////////////////////////////////////////////////

        wssoap = new WebServiceSoap();
        if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
            Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
        } else {

            //Monta chave de usuário
            String chavebusca = usuario+"#"+termopesquisado;
            List<String> PesquisaArray = new ArrayList<>();

            try {

                PesquisaArray = new TaskPesquisarArquivos(this).execute( chavebusca, "" ).get();
                //Vector to String Array e Monta Lista de Pesquisa com o Retorno do WS executado na Task Produtos
                arrayPesquisar = PesquisaArray.toArray( new String[PesquisaArray.size()] );
                for (int i = 0; i < arrayPesquisar.length; i++) {
                    System.out.println();
                    pesquisas.add( new PesquisarList( usuario.toString(), arrayPesquisar[i].toString(), R.drawable.fechamentoexternoapptoplogin ) );
                }
                // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();
            } catch (Exception e) {

            }
        }

        //Retorna o Lista de chamados conforme ArrayList de Contas
        PesquisarListAdapter adapter = new PesquisarListAdapter( getApplicationContext(), R.layout.modelo_listaprodutos, pesquisas );
        lstResultados.setAdapter( adapter );

    }


    ///Método para Montar o Menu Toobar da Aplicação
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menuitens, menu );
        return true;
    }

    public void abrirMenuPrincipal(String user){
        try {
            Pesquisar.this.finish();

            Intent intent = new Intent(Pesquisar.this, Menuprincipal.class);
            intent.putExtra("user", usuario);
            intent.putExtra( "pass", pass );

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTela", ex);
        }
    }


    public void abrirDetalheArquivo(String user, String nomearquivo){
        try {
            Pesquisar.this.finish();

            Intent intent = new Intent(Pesquisar.this, DetalheArquivo.class);
            intent.putExtra("user", usuario);
            intent.putExtra( "pass", pass );
            intent.putExtra( "nomearquivo", nomearquivo );
            intent.putExtra( "OrigemMenu", "Pesquisar" );
            intent.putExtra( "Termo", editsearch.getText().toString() );
            intent.putExtra( "btnvoltarpesquisa", "" );



//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTela", ex);
        }
    }



}
