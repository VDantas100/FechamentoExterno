package br.com.tiberio.fechamentoexterno.atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import br.com.tiberio.fechamentoexterno.R;

public class VisualizarArquivo extends AppCompatActivity {

    String linkarquivo = "";
    String usuario= "";
    String pass= "";
    String linkg = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_visualizar_arquivo );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( "" );
        setSupportActionBar( toolbar );

        Bundle extras = getIntent().getExtras();
        linkarquivo = extras.getString("urlarquivo");
        usuario = extras.getString("user");
        pass = extras.getString("pass");


        //"http://docs.google.com/gview?embedded=true&url="+
        linkg = linkarquivo;

        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);
        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        } );


        //WebView webView = (WebView) findViewById( R.id.webhttpid);
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl(linkg);

        WebView webView = (WebView) findViewById( R.id.webhttpid);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(linkg);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url){
                // do your stuff here

            }
        });




    }

    public void abrirTelaDetalheArquivo(String user, String nomeproduto, String tipodocumento, String nomearquivo){
        try {
            VisualizarArquivo.this.finish();

            Intent intent = new Intent(VisualizarArquivo.this, DetalheArquivo.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
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

}
