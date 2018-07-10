package br.com.tiberio.fechamentoexterno.atividades;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.tiberio.fechamentoexterno.R;
import br.com.tiberio.fechamentoexterno.adapter.ArquivoList;
import br.com.tiberio.fechamentoexterno.adapter.ArquivoListAdapter;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;
import br.com.tiberio.fechamentoexterno.task.TaskAquivos1;

public class Arquivos1 extends AppCompatActivity {

    ListView lstResultados2;
    List<ArquivoList> arquivos = null;

    String usuario = "";
    String pass="";
    String nomeproduto = "";
    String nomelocalidade = "";
    String nomebairro = "";
    String tipodocumento = "";
    String nomearquivo = "";
    WebServiceSoap wssoap;
    String[] arrayMenuArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_arquivos1 );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );
        setSupportActionBar( toolbar );

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("user");
        pass = extras.getString("pass");
        nomelocalidade = extras.getString("nomelocalidade");
        nomebairro  = extras.getString("nomebairro");
        nomeproduto = extras.getString("nomeproduto");
        tipodocumento = extras.getString("tipodocumento");

/////////////////////////////////////////////////////////////////////////////////////////////


        arquivos = new ArrayList<>();

        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);

        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaMenuTipoDocumento1(usuario, nomeproduto);
            }
        } );




        ////////////////////////////////////////////////
        wssoap = new WebServiceSoap();
        if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
            Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
        } else {

            //(User As String,Local As String,Bairro As String, nomeproduto As String , tipodocumento As String

            //Monta chave de usuário para Tarefa Chave Composta
            String chavebusca = usuario+"#"+nomelocalidade+"#"+nomebairro+"#"+nomeproduto+"#"+tipodocumento;
            List<String> MenuArquivosArray = new ArrayList<>();

            try {

                MenuArquivosArray = new TaskAquivos1(this).execute( chavebusca, "" ).get();
                //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                arrayMenuArquivo = MenuArquivosArray.toArray( new String[MenuArquivosArray.size()] );
                for (int i = 0; i < arrayMenuArquivo.length; i++) {
                    //Criar Lista de Arquivos
                    arquivos.add(new ArquivoList(usuario,nomeproduto, tipodocumento ,arrayMenuArquivo[i].toString(),R.drawable.fechamentoexternoapptoplogin));

                }

                // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

            } catch (Exception e) {

            }

        }

        lstResultados2 = findViewById(R.id.lstvlocalidades );
        lstResultados2.setFastScrollAlwaysVisible(true);

        ///Titulo do Tipo de Documento
        ((TextView) findViewById(R.id.txtTituloModelo )).setText(tipodocumento);


        //Retorna o Lista de chamados conforme ArrayList de Arquivos
        ArquivoListAdapter adapter = new ArquivoListAdapter(getBaseContext(), R.layout.modelo_listaarquivos, arquivos);
        lstResultados2.setAdapter(adapter);



        lstResultados2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                //Buscar  o nome do item selecionado para passar para proxima tela
                TextView nomeitemselecionado = view.findViewById( R.id.TituloArquivo );

                nomearquivo = nomeitemselecionado.getText().toString();

                // Toast.makeText(getBaseContext(), nomeproduto.toString(), Toast.LENGTH_LONG).show();
                // Toast.makeText(getBaseContext(), usuario.toString(), Toast.LENGTH_LONG).show();

                //Abrir Tela de Detalhe do Arquivo
                abrirTelaDetalheArquivo1(usuario, nomeproduto, tipodocumento,nomearquivo);
                //Toast.makeText(getBaseContext(), nomeitemselecionado, Toast.LENGTH_LONG).show();

            }
        });

    }

    public void abrirTelaMenuTipoDocumento1(String user, String nomeproduto) {
        try {
            Arquivos1.this.finish();

            Intent intent = new Intent( Arquivos1.this, TipoDocumentos1.class );
            intent.putExtra( "user", user );
            intent.putExtra("pass", pass);
            intent.putExtra( "nomelocalidade", nomelocalidade );
            intent.putExtra( "nomebairro", nomebairro );
            intent.putExtra( "nomeproduto", nomeproduto );
            intent.putExtra( "tipodocumento", tipodocumento );

            startActivity( intent );
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        } catch (Exception ex) {
            Log.e( "Login", "Erro abrirTelaMenuTipoDocumento", ex );
        }
    }


    public void abrirTelaDetalheArquivo1(String user, String nomeproduto, String tipodocumento, String nomearquivo){
        try {
            Arquivos1.this.finish();

            Intent intent = new Intent(Arquivos1.this, DetalheArquivo.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra( "nomelocalidade", nomelocalidade );
            intent.putExtra( "nomebairro", nomebairro );
            intent.putExtra("nomeproduto", nomeproduto);
            intent.putExtra("tipodocumento", tipodocumento);
            intent.putExtra("nomearquivo", nomearquivo);
            intent.putExtra("OrigemMenu", "Empreendimentos");

            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaDetalheArquivo", ex);
        }
    }


    ///Método para Montar o Menu Toobar da Aplicação
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menuitens, menu );
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed
        String message = "";
        if (item.getItemId() == R.id.menu_action_sair) {
            //message = "You selected option 2!";

            //sair
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();

            }

        }
        else {
            //message = "Why would you select that!?";
        }

        // show message via toast
        //Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        //toast.show();

        return true;
    }


}
