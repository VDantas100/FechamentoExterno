package br.com.tiberio.fechamentoexterno.atividades;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import br.com.tiberio.fechamentoexterno.adapter.ProdutoList;
import br.com.tiberio.fechamentoexterno.adapter.ProdutoListAdapter;
import br.com.tiberio.fechamentoexterno.task.TaskTipodocumentosoc2;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

public class tipodocumentosoc1 extends AppCompatActivity {

    ListView lstResultados;
    List<ProdutoList> tpdocumentos = null;
    String nometipodocumento;
    String usuario;
    String pass;
    String tipodocumentosoc;
    String chavebusca;
    WebServiceSoap wssoap;
    String[] arrayMenuTipoDocumento;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tipodocumentosoc1 );


        Bundle extras = getIntent().getExtras();
        usuario = extras.getString( "user" );
        pass = extras.getString( "pass" );
        tipodocumentosoc = extras.getString( "tipodocumentooc" );
        tpdocumentos = new ArrayList<>();

        TextView titulotpdocumentos = (TextView) findViewById( R.id.txtTituloModelo );
        titulotpdocumentos.setText( tipodocumentosoc );

        Button btvoltar = (Button) findViewById( R.id.id_botao_voltar );

        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  abrirTelaMenuTipoDocumento(usuario, nomeproduto);
                abrirTelaTipoDocumentosOC( usuario );
            }
        } );

        ///////////////////////////////////////////////

        ////////////////////////////////////////////////
        wssoap = new WebServiceSoap();
        if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
            Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
        } else {

            //Monta chave de usuário
            chavebusca = usuario + "#" + tipodocumentosoc;

            List<String> MenuTipoDocumentosArray = new ArrayList<>();

            try {

                MenuTipoDocumentosArray = new TaskTipodocumentosoc2( this ).execute( chavebusca, "" ).get();
                //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Tipo de Documentos Outras Categoria
                arrayMenuTipoDocumento = MenuTipoDocumentosArray.toArray( new String[MenuTipoDocumentosArray.size()] );
                for (int i = 0; i < arrayMenuTipoDocumento.length; i++) {
                    System.out.println( arrayMenuTipoDocumento[i].toString() );
                    tpdocumentos.add( new ProdutoList( usuario.toString(), arrayMenuTipoDocumento[i].toString(), R.drawable.fechamentoexternoapptoplogin ) );
                }

                // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

            } catch (Exception e) {

            }
            ////////////////
            lstResultados = findViewById( R.id.lstprodutos1 );
            //Retorna o Lista de chamados conforme ArrayList de Contas
            ProdutoListAdapter adapter = new ProdutoListAdapter( getBaseContext(), R.layout.modelo_listaprodutos, tpdocumentos );
            lstResultados.setAdapter( adapter );

            lstResultados.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    //Buscar  o nome do item selecionado para passar para proxima tela
                    TextView nomeitemselecionado = view.findViewById( R.id.txtTitulo );
                    nometipodocumento = nomeitemselecionado.getText().toString();
                    Toast.makeText( getBaseContext(), nometipodocumento.toString(), Toast.LENGTH_LONG ).show();
                    //Toast.makeText( getBaseContext(), usuario.toString(), Toast.LENGTH_LONG ).show();
                    abrirTelaTipoArquivosOC( usuario, tipodocumentosoc, nometipodocumento );


                    //Toast.makeText(getBaseContext(), nomeitemselecionado, Toast.LENGTH_LONG).show();
                }
            } );
            ////////////////

        }

        ////////////////////////////////////////


    }

    public void abrirTelaTipoDocumentosOC(String user ){
        try {
            tipodocumentosoc1.this.finish();

            Intent intent = new Intent(tipodocumentosoc1.this, tipodocumentosoc.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("tipodocumentooc", "");

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaProdutos", ex);
        }
    }


    public void abrirTelaTipoArquivosOC(String user ,String tipodocumentooc1, String tipodocumentooc2 ){
        try {
            tipodocumentosoc1.this.finish();

            Intent intent = new Intent(tipodocumentosoc1.this, ArquivosOC.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("tipodocumentooc1", tipodocumentooc1);
            intent.putExtra("tipodocumentooc2", tipodocumentooc2);

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaProdutos", ex);
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
