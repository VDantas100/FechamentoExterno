package br.com.tiberio.fechamentoexterno.atividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import br.com.tiberio.fechamentoexterno.R;
import br.com.tiberio.fechamentoexterno.task.TaskDetalheAquivoPesquisar;
import br.com.tiberio.fechamentoexterno.task.TaskDetalheArquivoEmp;
import br.com.tiberio.fechamentoexterno.task.TaskDetalheArquivoOutrasCategoras;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

public class DetalheArquivo extends AppCompatActivity {

    String usuario = "";
    String pass = "";
    String nomelocalidade = "";
    String nomebairro = "";
    String nomeproduto = "";
    String tipodocumento = "";
    String tipodocumento1 = "";
    String nomearquivo = "";
    String OrigemMenu = "" ;
    WebServiceSoap wssoap;
    String[] arrayDetalheArquivo;
    String UrlArquivo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );



        setContentView( R.layout.activity_detalhe_arquivo );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );
        setSupportActionBar( toolbar );

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("user");
        pass = extras.getString("pass");
        nomelocalidade = extras.getString("nomelocalidade");
        nomebairro = extras.getString("nomebairro");
        nomeproduto = extras.getString("nomeproduto");
        tipodocumento = extras.getString("tipodocumento");
        tipodocumento1 = extras.getString("tipodocumento1");
        nomearquivo = extras.getString("nomearquivo");
        OrigemMenu = extras.getString("OrigemMenu");
        final String opcao = OrigemMenu.toString();

        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);
        Button btvisualizar = (Button) findViewById(R.id.id_botao_visualisar);

        //Click do botão voltar
        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (opcao.equals("Empreendimentos") ) {
                    //Voltar para o Menu Arquivo Empreendimentos
                    abrirTelaMenuArquivos1( usuario, nomelocalidade, nomebairro, nomeproduto, tipodocumento );
                }

                if(opcao.equals("Mkt")) {
                    //Voltar para o Menu Arquivo Marketing
                    abrirTelaMenuArquivos(usuario, nomeproduto, tipodocumento);
                }

                if (opcao.equals( "OutrasCategorias" )) {
                    abrirTelaTipoArquivosOC(usuario, tipodocumento, tipodocumento1);
                }

                if (opcao.equals( "Pesquisar" )) {
                    abrirTelaPesquisar(usuario, nomearquivo);
                }

            }
        } );



        switch (OrigemMenu) {
             case "Empreendimentos":
                 wssoap = new WebServiceSoap();
                 if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
                     Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
                 } else {

                     //Monta chave de usuário para Tarefa Chave Composta
                     String chavebusca = usuario+"#"+nomeproduto+"#"+tipodocumento+"#"+nomearquivo;
                     List<String> DetalheArquivoArray = new ArrayList<>();

                     try {

                         DetalheArquivoArray = new TaskDetalheArquivoEmp(this).execute( chavebusca, "" ).get();
                         //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                         arrayDetalheArquivo = DetalheArquivoArray.toArray( new String[DetalheArquivoArray.size()] );

                         // campos do Arquivo
                         ((TextView) findViewById(R.id.txtTituloArquivoDetalhe )).setText(arrayDetalheArquivo[2]);
                         ((TextView) findViewById(R.id.txtMesVigencia )).setText(arrayDetalheArquivo[3]);
                         ((TextView) findViewById(R.id.txtVersao )).setText(arrayDetalheArquivo[4]);
                         ((TextView) findViewById(R.id.txtnomearquivo )).setText(arrayDetalheArquivo[2]);

                         UrlArquivo =arrayDetalheArquivo[5];

                         btvisualizar.setOnClickListener( new View.OnClickListener() {
                             @SuppressLint("NewApi")
                             @Override
                             public void onClick(View v) {

                                //Testar Novo Titulo
                                   //abrirTelaBaixarArquivo(UrlArquivo,usuario,pass);

                                   //abrirTelaVisualisarArquivo(UrlArquivo);
                                   abrirTelaVisualisarArquivonobrowser(UrlArquivo,usuario,pass);
                                   //abrirTelaVisualisarArquivo(UrlArquivo,usuario,pass);

                             }
                         } );

                         // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

                     } catch (Exception e) {

                     }

                 }

                System.out.println("Menu Empreendimentos");
                break;

            case "Mkt":

                wssoap = new WebServiceSoap();
                if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
                    Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
                } else {

                    //Monta chave de usuário para Tarefa Chave Composta
                    String chavebusca = usuario+"#"+nomeproduto+"#"+tipodocumento+"#"+nomearquivo;
                    List<String> DetalheArquivoArray = new ArrayList<>();

                    try {

                        DetalheArquivoArray = new TaskDetalheArquivoEmp(this).execute( chavebusca, "" ).get();


                        //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                        arrayDetalheArquivo = DetalheArquivoArray.toArray( new String[DetalheArquivoArray.size()] );

                        // campos do Arquivo
                        ((TextView) findViewById(R.id.txtTituloArquivoDetalhe )).setText(arrayDetalheArquivo[2]);
                        ((TextView) findViewById(R.id.txtMesVigencia )).setText(arrayDetalheArquivo[3]);
                        ((TextView) findViewById(R.id.txtVersao )).setText(arrayDetalheArquivo[4]);
                        ((TextView) findViewById(R.id.txtnomearquivo )).setText(arrayDetalheArquivo[2]);

                        UrlArquivo =arrayDetalheArquivo[5];

                        btvisualizar.setOnClickListener( new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(View v) {

                                // abrirTelaVisualisarArquivo(UrlArquivo);
                                abrirTelaVisualisarArquivonobrowser(UrlArquivo,usuario,pass);
                                //abrirTelaVisualisarArquivo(UrlArquivo,usuario,pass);

                            }
                        } );




                        // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

                    } catch (Exception e) {

                    }

                }

                System.out.println("Menu Empreendimentos");
                break;



            case "OutrasCategorias":


                wssoap = new WebServiceSoap();
                if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
                    Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
                } else {

                    //Monta chave de usuário para Tarefa Chave Composta
                    String chavebusca = usuario+"#"+tipodocumento+"#"+tipodocumento1+"#"+nomearquivo;
                    List<String> DetalheArquivoArray = new ArrayList<>();

                    try {

                        DetalheArquivoArray = new TaskDetalheArquivoOutrasCategoras(this).execute( chavebusca, "" ).get();
                        //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                        arrayDetalheArquivo = DetalheArquivoArray.toArray( new String[DetalheArquivoArray.size()] );

                        // campos do Arquivo
                        ((TextView) findViewById(R.id.txtTituloArquivoDetalhe )).setText(arrayDetalheArquivo[2]);
                        ((TextView) findViewById(R.id.txtMesVigencia )).setText(arrayDetalheArquivo[3]);
                        ((TextView) findViewById(R.id.txtVersao )).setText(arrayDetalheArquivo[4]);
                        ((TextView) findViewById(R.id.txtnomearquivo )).setText(arrayDetalheArquivo[2]);

                        UrlArquivo =arrayDetalheArquivo[5];

                        btvisualizar.setOnClickListener( new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(View v) {

                                 // abrirTelaVisualisarArquivo(UrlArquivo);
                                  abrirTelaVisualisarArquivonobrowser(UrlArquivo,usuario,pass);
                                 //abrirTelaVisualisarArquivo(UrlArquivo,usuario,pass);

                            }
                        } );

                        // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

                    } catch (Exception e) {

                    }

                }


                System.out.println("Menu OutrasCategorias");
                break;

            case "Pesquisar":

                wssoap = new WebServiceSoap();
                if (!wssoap.VericarConexaoRede( getApplicationContext() )) {
                    Toast.makeText( getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG ).show();
                } else {

                    //Monta chave de usuário para Tarefa Chave Composta
                    String chavebusca = usuario+"#"+nomearquivo;
                    List<String> DetalheArquivoArray = new ArrayList<>();

                    try {

                        DetalheArquivoArray = new TaskDetalheAquivoPesquisar(this).execute( chavebusca, "" ).get();
                        //Vector to String Array e Monta Lista de Produtos com o Retorno do WS executado na Task Produtos
                        arrayDetalheArquivo = DetalheArquivoArray.toArray( new String[DetalheArquivoArray.size()] );

                        // campos do Arquivo
                        ((TextView) findViewById(R.id.txtTituloArquivoDetalhe )).setText(arrayDetalheArquivo[2]);
                        ((TextView) findViewById(R.id.txtMesVigencia )).setText(arrayDetalheArquivo[3]);
                        ((TextView) findViewById(R.id.txtVersao )).setText(arrayDetalheArquivo[4]);
                        ((TextView) findViewById(R.id.txtnomearquivo )).setText(arrayDetalheArquivo[2]);

                        UrlArquivo =arrayDetalheArquivo[5];

                        btvisualizar.setOnClickListener( new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(View v) {

                                // abrirTelaVisualisarArquivo(UrlArquivo);

                                  abrirTelaVisualisarArquivonobrowser(UrlArquivo,usuario,pass);

                                //abrirTelaVisualisarArquivo(UrlArquivo,usuario,pass);

                            }
                        } );

                        // Toast.makeText( this, "" + MenuProdutosArray.get( 0 ), Toast.LENGTH_SHORT ).show();

                    } catch (Exception e) {

                    }

                }

                System.out.println("Menu Pesquisar");
                break;

            default:
              //  System.out.println("Else");

        }



    }

    public void abrirTelaMenuArquivos(String user, String nomeproduto, String tipodoc){
        try {
            DetalheArquivo.this.finish();

            Intent intent = new Intent(DetalheArquivo.this, Arquivos.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("nomeproduto", nomeproduto);
            intent.putExtra("tipodocumento", tipodoc);

            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaMenuArquivo", ex);
        }
    }


    public void abrirTelaMenuArquivos1(String user, String nomelocalidade, String nomebairro, String nomeproduto, String tipodoc){
        try {
            DetalheArquivo.this.finish();

            Intent intent = new Intent(DetalheArquivo.this, Arquivos1.class);
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


    public void abrirTelaTipoArquivosOC(String user ,String tipodocumentooc1, String tipodocumentooc2 ){
        try {
            DetalheArquivo.this.finish();

            Intent intent = new Intent(DetalheArquivo.this, ArquivosOC.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("tipodocumentooc1", tipodocumentooc1);
            intent.putExtra("tipodocumentooc2", tipodocumentooc2);

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaProdutos", ex);
        }
    }

    public void abrirTelaPesquisar(String user ,String nomearquivo){
        try {
            DetalheArquivo.this.finish();

            Intent intent = new Intent(DetalheArquivo.this, Pesquisar.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("btnVoltarPesquisa", "Sim");
            intent.putExtra("nomearquivo", nomearquivo);

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaProdutos", ex);
        }
    }



    public void abrirTelaVisualisarArquivo(String urlarquivo,  String user, String pass) {
        try {
            //DetalheArquivo.this.finish();

            String urlSessaouserNotes = "http://portal.tiberio.com.br/names.nsf?login&Username="+user+"&Password="+pass;
            String urlSessaoUserArquivo = urlSessaouserNotes + UrlArquivo;


            Intent intent = new Intent( DetalheArquivo.this, VisualizarArquivo.class );

            intent.putExtra( "urlarquivo", urlSessaoUserArquivo );
            intent.putExtra( "user", usuario );
            intent.putExtra( "pass", pass );
            intent.putExtra("nomeproduto", nomeproduto);
            intent.putExtra("tipodocumento", tipodocumento);

            startActivity( intent );
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        } catch (Exception ex) {
            Log.e( "Login", "Erro abrirTelaVisualisarArquivo", ex );
        }
    }


    public void abrirTelaBaixarArquivo(String urlarquivo,  String user, String pass) {
        try {
            //DetalheArquivo.this.finish();

            String urlSessaouserNotes = "http://portal.tiberio.com.br/names.nsf?login&Username="+user+"&Password="+pass;
            String urlSessaoUserArquivo = urlSessaouserNotes + UrlArquivo;


            Intent intent = new Intent( DetalheArquivo.this, VisualizarFile.class );


            intent.putExtra( "urlarquivo", urlSessaoUserArquivo );
            intent.putExtra( "user", usuario );
            intent.putExtra( "pass", pass );
            intent.putExtra("nomeproduto", nomeproduto);
            intent.putExtra("tipodocumento", tipodocumento);
            intent.putExtra("tipodocumento1", tipodocumento1);
            intent.putExtra("tipodocumentooc1", tipodocumento);
            intent.putExtra("tipodocumentooc2", tipodocumento1);
            intent.putExtra("nomelocalidade", nomelocalidade);
            intent.putExtra("nomebairro", nomebairro);
            intent.putExtra("nomeproduto", nomeproduto);
            intent.putExtra("OrigemMenu", OrigemMenu);



            startActivity( intent );
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        } catch (Exception ex) {
            Log.e( "Login", "Erro abrirTelaVisualisarArquivo", ex );
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void abrirTelaVisualisarArquivonobrowser(String UrlArquivo, String user, String pass){

         String urlSessaouserNotes = "http://portal.tiberio.com.br/names.nsf?login&Username="+user+"&Password="+pass;
         String urlSessaoUserArquivo = urlSessaouserNotes + UrlArquivo;

          byte[] encodeValue1 = Base64.encode(user.getBytes(), Base64.DEFAULT);
          String  parm1 = new String(encodeValue1);

          byte[] encodeValue2 = Base64.encode(pass.getBytes(), Base64.DEFAULT);
           String parm2 = new String(encodeValue2);

           String urlSessaouserNotestesta = "http://portal.tiberio.com.br/names.nsf?login&Username="+user+"&Password="+pass;
           String urlSessaoUserArquivo1 = urlSessaouserNotestesta + UrlArquivo;

          String encodedUrl ="";

          // URLEncoder.encode(urlSessaoUserArquivo1, "UTF-8");
        try {
            encodedUrl = URLEncoder.encode(urlSessaoUserArquivo, "UTF-8");
            String NOVA = "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(novaurl.toString()));

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( urlSessaoUserArquivo.toString()) );

        //urlString.getQueryParameter("file")

        //Intent browserChooserIntent = Intent.createChooser(browserIntent , "Selecione um browser de sua escolha");
        //browserIntent.FLAG_ACTIVITY_NO_USER_ACTION =0x00040000;
        startActivity(browserIntent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


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
