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
import br.com.tiberio.fechamentoexterno.adapter.ProdutoList;
import br.com.tiberio.fechamentoexterno.adapter.ProdutoListAdapter;
import br.com.tiberio.fechamentoexterno.task.TaskProdutos;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

public class Produtos extends AppCompatActivity {

    ListView lstResultados;
    List<ProdutoList> produtos = null;

    String usuario = "";
    String pass = "";
    String nomeproduto = "";
    WebServiceSoap wssoap;
    String[] arrayMenuProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_produtos );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );
        setSupportActionBar( toolbar );

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString( "user" );
        pass = extras.getString( "pass" );

        produtos = new ArrayList<>();

        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);

        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  abrirTelaMenuTipoDocumento(usuario, nomeproduto);
                abrirMenuPrincipal(usuario);
            }
        } );

        ////////////////////////////////////////////////

        wssoap = new WebServiceSoap();
        if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
            Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
        } else {

            //Monta chave de usuário
            String chavebusca = usuario;
            List<String> MenuProdutosArray = new ArrayList<>();

            try {


                MenuProdutosArray = new TaskProdutos(Produtos.this).execute( chavebusca, "" ).get();
                //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                arrayMenuProduto = MenuProdutosArray.toArray( new String[MenuProdutosArray.size()] );
                for (int i = 0; i < arrayMenuProduto.length; i++) {
                    System.out.println();
                    produtos.add( new ProdutoList( usuario.toString(), arrayMenuProduto[i].toString(), R.drawable.fechamentoexternoapptoplogin ) );
                }



               // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

            } catch (Exception e) {

            }

        }

        lstResultados = findViewById( R.id.lstvlocalidades );
        //Retorna o Lista de chamados conforme ArrayList de Contas
        ProdutoListAdapter adapter = new ProdutoListAdapter( getBaseContext(), R.layout.modelo_listaprodutos, produtos );
        lstResultados.setAdapter( adapter );


        lstResultados.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                //Buscar  o nome do item selecionado para passar para proxima tela
                TextView nomeitemselecionado = view.findViewById( R.id.txtTitulo );

                nomeproduto = nomeitemselecionado.getText().toString();

                //Toast.makeText( getBaseContext(), nomeproduto.toString(), Toast.LENGTH_LONG ).show();
                //Toast.makeText( getBaseContext(), usuario.toString(), Toast.LENGTH_LONG ).show();

                //Abrir Tela de Tipo de Documentos
                abrirTelaMenuTipoDocumento( usuario, nomeproduto );


                //Toast.makeText(getBaseContext(), nomeitemselecionado, Toast.LENGTH_LONG).show();

            }
        } );
    }

    public void abrirTelaMenuTipoDocumento(String user, String nomeproduto) {
        try {
            Produtos.this.finish();

            Intent intent = new Intent( Produtos.this, TipoDocumentos.class );
            intent.putExtra( "user", user );
            intent.putExtra( "nomeproduto", nomeproduto );
            intent.putExtra( "pass", pass );

            startActivity( intent );
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        } catch (Exception ex) {
            Log.e( "Login", "Erro abrirTelaMenuTipoDocumento", ex );
        }
    }

    public void abrirMenuPrincipal(String user){
        try {
            Produtos.this.finish();

            Intent intent = new Intent(Produtos.this, Menuprincipal.class);
            intent.putExtra("user", user);
            intent.putExtra( "pass", pass );

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTela", ex);
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