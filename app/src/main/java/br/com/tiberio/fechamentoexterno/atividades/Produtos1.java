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
import br.com.tiberio.fechamentoexterno.adapter.BairroList;
import br.com.tiberio.fechamentoexterno.adapter.BairroListAdapter;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;
import br.com.tiberio.fechamentoexterno.task.TaskProdutos1;

public class Produtos1 extends AppCompatActivity {

    ListView lstResultados3;
    List<BairroList> produtos = null;

    String usuario = "";
    String pass="";
    String nomelocalidade = "";
    String nomebairro = "";
    String nomeproduto = "";
    String produto = "";
    WebServiceSoap wssoap;
    String[] arrayMenuProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_produtos1 );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );
        setSupportActionBar( toolbar );

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("user");
        pass = extras.getString("pass");
        nomelocalidade = extras.getString("nomelocalidade");
        nomebairro = extras.getString("nomebairro");


        produtos = new ArrayList<>();

        ///Titulo do Bairro
        ((TextView) findViewById(R.id.txtTituloModelo )).setText(nomebairro);


        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);

        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirTelaMenuBairro(usuario, nomelocalidade);
            }
        } );

        ////////////////////////////////////////////////

        wssoap = new WebServiceSoap();
        if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
            Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
        } else {

            //Monta chave de usuário para Tarefa Chave Composta
            String chavebusca = usuario+"#"+nomelocalidade+"#"+nomebairro;
            List<String> MenuProdutosArray = new ArrayList<>();

            try {

                MenuProdutosArray = new TaskProdutos1(this).execute( chavebusca, "" ).get();
                //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                arrayMenuProdutos = MenuProdutosArray.toArray( new String[MenuProdutosArray.size()] );
                for (int i = 0; i < arrayMenuProdutos.length; i++) {
                    System.out.println();
                    produtos.add( new BairroList( usuario.toString(), nomelocalidade, arrayMenuProdutos[i].toString(), R.drawable.fechamentoexternoapptoplogin ) );
                }
                // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

            } catch (Exception e) {

            }

        }

        lstResultados3 = findViewById(R.id.lstprodutos1 );

        //Retorna o Lista de bairros ArrayList no listView
        BairroListAdapter adapter = new BairroListAdapter( getBaseContext(), R.layout.modelo_listabairros, produtos );

        lstResultados3.setAdapter(adapter);


        lstResultados3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                //Buscar  o nome do item selecionado para passar para proxima tela
                TextView nomeitemselecionado = view.findViewById( R.id.txtBairro );

                nomeproduto = nomeitemselecionado.getText().toString();

                //Abrir Tela de Tipo de Documentos
                abrirTelaMenuTipoDocumentos1(usuario, nomelocalidade, nomebairro, nomeproduto );

                Toast.makeText(getBaseContext(), nomeproduto, Toast.LENGTH_LONG).show();

            }
        });




    }

    public void abrirTelaMenuBairro(String user, String nomelocalidade) {
        try {
            Produtos1.this.finish();

            Intent intent = new Intent( Produtos1.this, Bairro.class );
            intent.putExtra( "user", user );
            intent.putExtra( "nomelocalidade", nomelocalidade );
            intent.putExtra( "pass", pass );

            startActivity( intent );
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        } catch (Exception ex) {
            Log.e( "Login", "Erro abrirTelaMenuBairro", ex );
        }
    }


    public void abrirTelaMenuTipoDocumentos1(String user, String nomelocalidade, String nomebairro, String nomeproduto ) {
        try {
            Produtos1.this.finish();

            Intent intent = new Intent( Produtos1.this, TipoDocumentos1.class );

            intent.putExtra( "user", user );
            intent.putExtra( "pass", pass );
            intent.putExtra( "nomelocalidade", nomelocalidade );
            intent.putExtra( "nomebairro", nomebairro );
            intent.putExtra( "nomeproduto", nomeproduto );


            startActivity( intent );
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        } catch (Exception ex) {
            Log.e( "Login", "Erro abrirTelaMenuBairro", ex );
        }
    }


//////////////////////////////////////////////////////////////////////////////////////
/////Fim de Tread de Execução Menu Empreendimentos
/////////////////////////////////////////////////////////////////////////////////////

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
