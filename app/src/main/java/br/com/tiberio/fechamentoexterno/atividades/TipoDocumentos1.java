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
import br.com.tiberio.fechamentoexterno.adapter.TipodocumentoList;
import br.com.tiberio.fechamentoexterno.adapter.TipodocumentoListAdapter;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;
import br.com.tiberio.fechamentoexterno.task.TaskTipoDocumentos1;

public class TipoDocumentos1 extends AppCompatActivity {

    ListView lstResultados4;
    List<TipodocumentoList> tpdocumentos = null;

    String usuario = "";
    String nomeproduto = "";
    String pass="";
    String tipodoc= "";
    String nomelocalidade = "";
    String nomebairro = "";
    WebServiceSoap wssoap;
    String[] arrayMenuTipoDocumento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tipo_documentos1 );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle("");
        setSupportActionBar( toolbar );

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("user");
        pass = extras.getString("pass");
        nomelocalidade = extras.getString("nomelocalidade");
        nomebairro = extras.getString("nomebairro");
        nomeproduto =extras.getString("nomeproduto");
        tpdocumentos = new ArrayList<>();


        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);

        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Voltar a tela de produtos
                abrirTelaMenuProdutos1(usuario, nomelocalidade, nomebairro);
            }
        } );

        ///////////////////////////////////////////////////////////////////

        wssoap = new WebServiceSoap();
        if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
            Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
        } else {

            //Monta chave de usuário para Tarefa Chave Composta
            String chavebusca = usuario+"#"+nomelocalidade+"#"+nomebairro+"#"+nomeproduto;
            List<String> MenuTipoDocumentosArray = new ArrayList<>();

            try {

                MenuTipoDocumentosArray = new TaskTipoDocumentos1(this).execute( chavebusca, "" ).get();
                //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                arrayMenuTipoDocumento = MenuTipoDocumentosArray.toArray( new String[MenuTipoDocumentosArray.size()] );
                for (int i = 0; i < arrayMenuTipoDocumento.length; i++) {
                    System.out.println();
                    tpdocumentos.add(new TipodocumentoList(usuario.toString(),nomeproduto ,arrayMenuTipoDocumento[i].toString(), R.drawable.fechamentoexternoapptoplogin));

                }

                // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

            } catch (Exception e) {

            }

        }

        lstResultados4 = findViewById(R.id.lstprodutos1 );

        ///Titulo do Nome Produto
        ((TextView) findViewById(R.id.txtTituloModelo )).setText(nomeproduto);


        //Retorna o Lista de chamados conforme ArrayList de Contas
        TipodocumentoListAdapter adapter = new TipodocumentoListAdapter(getBaseContext(), R.layout.modelo_listatipodocumentos, tpdocumentos);
        lstResultados4.setAdapter(adapter);


        lstResultados4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                //Buscar  o nome do item selecionado para passar para proxima tela
                TextView nomeitemselecionado = view.findViewById( R.id.txtTipoDocumento );

                tipodoc = nomeitemselecionado.getText().toString();


                //Abrir Tela de Tipo de Documentos
                abrirTelaMenuArquivos1(usuario,nomelocalidade,nomebairro, nomeproduto, tipodoc);

                //Toast.makeText(getBaseContext(), nomeitemselecionado, Toast.LENGTH_LONG).show();

            }
        });

//========================================================================================================
    }

    public void abrirTelaMenuArquivos1(String user, String nomelocalidade,String nomebairro, String nomeproduto, String tipodoc){
        try {
            TipoDocumentos1.this.finish();

            Intent intent = new Intent(TipoDocumentos1.this, Arquivos1.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("nomelocalidade", nomelocalidade);
            intent.putExtra("nomebairro", nomebairro);
            intent.putExtra("nomeproduto", nomeproduto);
            intent.putExtra("tipodocumento", tipodoc);

            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaMenuArquivo", ex);
        }
    }


    public void abrirTelaMenuProdutos1(String user, String nomelocalidade,String nomebairro) {
        try {
            TipoDocumentos1.this.finish();

            Intent intent = new Intent( TipoDocumentos1.this, Produtos1.class );
            intent.putExtra( "user", user );
            intent.putExtra( "pass", pass );
            intent.putExtra( "nomelocalidade", nomelocalidade );
            intent.putExtra( "nomebairro", nomebairro );

            startActivity( intent );
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        } catch (Exception ex) {
            Log.e( "Login", "Erro abrirTelaMenuEmpreendimentos1", ex );
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
