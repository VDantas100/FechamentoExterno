package br.com.tiberio.fechamentoexterno.atividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.tiberio.fechamentoexterno.R;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

public class Menuprincipal extends AppCompatActivity {

    boolean check = false;
    Button btnMenuItem1;
    Button btnMenuItem2;
    Button btnMenuItem3;
    Button btnMenuItem4;
    String usuario = "";
    String pass = "";
    String msgapp = "";

    private WebServiceSoap wssoap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menuprincipal );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );
        setSupportActionBar( toolbar );

        //Método para Colocar Icone na action bar ao lado do Titulo
       // ActionBar actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeAsUpIndicator(R.drawable.appicontoolbar);

        Bundle extras = getIntent().getExtras();
        final String usuario = extras.getString("user");
        pass = extras.getString( "pass" );


        //seta botões
        final Button btnMenuItem1 = (Button) findViewById(R.id.btn_menu_empreendimentos);
        final Button btnMenuItem2 = (Button) findViewById(R.id.btn_menu_marketing);
        final Button btnMenuItem3 = (Button) findViewById(R.id.btn_menu_outrascategorias);
        final Button btnMenuItem4 = (Button) findViewById(R.id.btn_menu_pesquisar);

        //final Button btnMenuItem3 = (Button) findViewById(R.id.);

        btnMenuItem1.setOnClickListener( new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                btnMenuItem1.setTextColor( Color.rgb(255,255,255) );
                btnMenuItem1.setBackgroundColor( Color.parseColor("#4047cc"));
                btnMenuItem2.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem2.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem3.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem3.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem4.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem4.setTextColor( Color.rgb(128,127,123) );
                Toast.makeText( Menuprincipal.this, "Empreendimentos", Toast.LENGTH_SHORT ).show();

                //Abrir Tela de Lista de Produtos Empreendimentos
                abrirTelaMenuEmprendimentos(usuario);


            }
        } );


        btnMenuItem2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnMenuItem2.setTextColor( Color.rgb(255,255,255) );
                btnMenuItem2.setBackgroundColor( Color.parseColor("#4047cc"));
                btnMenuItem1.setBackgroundColor( Color.parseColor( "#ebebeb"));
                btnMenuItem1.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem3.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem3.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem4.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem4.setTextColor( Color.rgb(128,127,123) );

                Toast.makeText( Menuprincipal.this, "Marketing", Toast.LENGTH_SHORT ).show();

                //Abrir Tela de Lista de Produtos Marketing
                abrirTelaMenuMarketing(usuario);

            }
        } );


        btnMenuItem3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnMenuItem3.setTextColor( Color.rgb(255,255,255) );
                btnMenuItem3.setBackgroundColor( Color.parseColor("#4047cc"));
                btnMenuItem4.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem4.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem2.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem2.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem1.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem1.setTextColor( Color.rgb(128,127,123) );

                abrirTelaMenuOutrasCategorias(usuario);

                Toast.makeText( Menuprincipal.this, "Outras Categorias", Toast.LENGTH_SHORT ).show();
            }
        } );

        btnMenuItem4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnMenuItem4.setTextColor( Color.rgb(255,255,255) );
                btnMenuItem4.setBackgroundColor( Color.parseColor("#4047cc"));
                btnMenuItem3.setBackgroundColor( Color.parseColor( "#ebebeb"));
                btnMenuItem3.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem2.setBackgroundColor( Color.parseColor( "#ebebeb"));
                btnMenuItem2.setTextColor( Color.rgb(128,127,123) );
                btnMenuItem1.setBackgroundColor( Color.parseColor("#ebebeb"));
                btnMenuItem1.setTextColor( Color.rgb(128,127,123) );

                abrirTelaPesquisa(usuario);

                Toast.makeText( Menuprincipal.this, "Pesquisar", Toast.LENGTH_SHORT ).show();
            }
        } );


    }


    ///Método para Montar o Menu Toobar da Aplicação
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitens, menu);
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

////////////////////////////////////////////////////////
//Firm do Menu
////////////////////////////////////////////////////////




    public void abrirTelaMenuEmprendimentos(String user){
        try {
            Menuprincipal.this.finish();


            Intent intent = new Intent(Menuprincipal.this, Localidade.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);



            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaProdutos", ex);
        }
    }


    public void abrirTelaMenuMarketing(String user){
        try {
            Menuprincipal.this.finish();

            Intent intent = new Intent(Menuprincipal.this, Produtos.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaProdutos", ex);
        }
    }

    public void abrirTelaPesquisa(String user){

        try {
            Menuprincipal.this.finish();

            Intent intent = new Intent(Menuprincipal.this, Pesquisar.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);


//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaPesquisar", ex);
        }


    }


    public void abrirTelaMenuOutrasCategorias(String user){
        try {
            Menuprincipal.this.finish();

            Intent intent = new Intent(Menuprincipal.this, tipodocumentosoc.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("tipodocumentosoc", "");


//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaProdutos", ex);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }





}
