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
import br.com.tiberio.fechamentoexterno.task.TaskBairros;

public class Bairro extends AppCompatActivity {

    ListView lstResultados2;
    List<BairroList> bairros = null;

    String usuario = "";
    String pass="";
    String nomelocalidade = "";
    String nomebairro = "";
    WebServiceSoap wssoap;
    String[] arrayMenuBairro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bairro );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );
        setSupportActionBar( toolbar );


        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("user");
        pass = extras.getString("pass");
        nomelocalidade = extras.getString("nomelocalidade");
        bairros = new ArrayList<>();


        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);

        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirTelaMenuLocalidades(usuario);
            }
        } );

        ////////////////////////////////////////////////

        wssoap = new WebServiceSoap();
        if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
            Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
        } else {

            //Monta chave de usuário para Tarefa Chave Composta
            String chavebusca = usuario+"#"+nomelocalidade;
            List<String> MenuTipoDocumentosArray = new ArrayList<>();

            try {

                MenuTipoDocumentosArray = new TaskBairros(this).execute( chavebusca, "" ).get();
                //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                arrayMenuBairro = MenuTipoDocumentosArray.toArray( new String[MenuTipoDocumentosArray.size()] );
                for (int i = 0; i < arrayMenuBairro.length; i++) {
                    System.out.println();
                    bairros.add( new BairroList( usuario.toString(), nomelocalidade, arrayMenuBairro[i].toString(), R.drawable.fechamentoexternoapptoplogin ) );
                }
                // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

            } catch (Exception e) {

            }

        }

        lstResultados2 = findViewById(R.id.lstprodutos1 );

        ///Titulo da Localidade
        ((TextView) findViewById(R.id.txtTituloModelo )).setText(nomelocalidade);


        //Retorna o Lista de bairros ArrayList no listView
        BairroListAdapter adapter = new BairroListAdapter( getBaseContext(), R.layout.modelo_listabairros, bairros );

        lstResultados2.setAdapter(adapter);


        lstResultados2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                //Buscar  o nome do item selecionado para passar para proxima tela
                TextView nomeitemselecionado = view.findViewById( R.id.txtBairro );

                nomebairro = nomeitemselecionado.getText().toString();

                Toast.makeText(getBaseContext(), nomebairro, Toast.LENGTH_LONG).show();

                //Abrir Tela de Produtos Menu Empreendimentos
                abrirTelaMenuProdutos1(usuario, nomelocalidade, nomebairro);



            }
        });



    }

    public void abrirTelaMenuProdutos1(String user, String nomelocalidade,String nomebairro) {
        try {
            Bairro.this.finish();

            Intent intent = new Intent( Bairro.this, Produtos1.class );
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

    public void abrirTelaMenuLocalidades(String user) {
        try {
            Bairro.this.finish();

            Intent intent = new Intent( Bairro.this, Localidade.class );
            intent.putExtra( "user", user );
            intent.putExtra( "pass", pass );

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
