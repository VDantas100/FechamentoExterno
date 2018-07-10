package br.com.tiberio.fechamentoexterno.atividades;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import br.com.tiberio.fechamentoexterno.R;
import br.com.tiberio.fechamentoexterno.externas.FileOpen;

public class VisualizarFile extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    private String file_url;
    String nomeaquivoandroid = null;
    String nomearquivoext = null;
    String linkarquivo = "";
    String usuario= "";
    String pass= "";
    String linkg = "";
    String nomeproduto = "";
    String tipodocumento = "";
    String tipodocumento1="";
    String nomearquivo = "";
    String OrigemMenu="";


    // File url to download
    //private static String file_url = "http://www.qwikisoft.com/demo/ashade/20001.kml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_visualizar_file );

        Bundle extras = getIntent().getExtras();
        linkarquivo = extras.getString("urlarquivo");
        usuario = extras.getString("user");
        pass = extras.getString("pass");

        nomeproduto = extras.getString("nomeproduto");
        tipodocumento = extras.getString("tipodocumento");
        tipodocumento1 = extras.getString("tipodocumento1");
        nomearquivo = extras.getString("nomearquivo");
        OrigemMenu = extras.getString("OrigemMenu");


        String file_url = linkarquivo;
        linkg = linkarquivo;

        new DownloadFileFromURL().execute(file_url);

        Button btvoltar     = (Button) findViewById(R.id.id_botao_voltar);
        btvoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirTelaDetalheArquivo(usuario , nomeproduto, tipodocumento, nomearquivo);

            }
        } );


    }


    /**
     * Showing Dialog
     * */

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0

                pDialog = new ProgressDialog(VisualizarFile.this, R.style.MyAlertDialogStyle);
                pDialog.setMessage("Baixando Arquivo. Aguarde...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;

            try {
                URL url = new URL( f_url[0] );
                URLConnection conection = url.openConnection();
                conection.connect();

                StringTokenizer st = new StringTokenizer(f_url[0].toString());

                String st1 = st.nextToken("/");
                String st2 = st.nextToken("/");
                String st3 = st.nextToken("/");
                String st4 = st.nextToken("/");
                String st5 = st.nextToken("/");
                String st6 = st.nextToken("/");
                String st7 = st.nextToken("/");
                String st8 = st.nextToken("/");
                String st9 = st.nextToken("/");

                String strnomearquivo = st9;
                String sub = "?OpenElement";
                String strnomearquivoCExtensao = strnomearquivo.replace(strnomearquivo.substring(strnomearquivo.indexOf(sub), strnomearquivo.indexOf(sub)+sub.length()), "");
                Log.i( "NomeArquivo", strnomearquivo);



                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream( url.openStream(),
                        8192 );

                nomeaquivoandroid = Environment
                        .getExternalStorageDirectory().toString()
                        + "/"+strnomearquivoCExtensao;

                // Output stream
                OutputStream output = new FileOutputStream( nomeaquivoandroid );

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read( data )) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress( "" + (int) ((total * 100) / lenghtOfFile) );

                    // writing data to file
                    output.write( data, 0, count );
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();




                nomearquivoext = strnomearquivoCExtensao;

                return  nomearquivoext.toString();


            } catch (Exception e) {

                Toast.makeText( VisualizarFile.this, e.getMessage().toString(), Toast.LENGTH_SHORT ).show();

                Log.e( "Error: ", e.getMessage() );
            }

            return nomeaquivoandroid.toString();
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            //AbrirArquivoBaixadoMetodo1(nomearquivoext);

            //readFromExternalStorage(nomearquivoext);

            //AbrirArquivoBaixadoMetodo3(nomeaquivoandroid);

            //AbrirArquivoBaixadoMetodo4(nomeaquivoandroid);

            openFilePDF(linkarquivo.toString());





        }

    }


    public void abrirTelaDetalheArquivo(String user, String nomeproduto, String tipodocumento, String nomearquivo){
        try {
            VisualizarFile.this.finish();

            Intent intent = new Intent(VisualizarFile.this, DetalheArquivo.class);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            intent.putExtra("nomeproduto", nomeproduto);
            intent.putExtra("tipodocumento", tipodocumento);
            intent.putExtra("nomearquivo", nomearquivo);
            intent.putExtra("OrigemMenu", "Empreendimentos");

            startActivity(intent);
            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTelaDetalheArquivo", ex);
        }
    }

    private void readFromExternalStorage(String filename){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ filename);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), "application/pdf");

        Intent intent = Intent.createChooser(target, "Abrir arquivo");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //Caso o usuário não tenha um visualizador de PDF, instrua-o aqui a baixar
        }
    }


    public void AbrirArquivoBaixadoMetodo1(String nomearquivoextencao){

        //Abrir o Arquivo já baixado
        File file = new File( Environment.getExternalStorageDirectory(), nomearquivoextencao );
        Uri path = Uri.fromFile(file);
        FileOpen arquivoNovo = new FileOpen();

        try {
            arquivoNovo.openFile(VisualizarFile.this,file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void AbrirArquivoBaixadoMetodo3(String nomeaquivoandroid){

        String novonome = nomeaquivoandroid;
        String caminhoarquivo = novonome;

        File file = new File(caminhoarquivo);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), "application/pdf");

        target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //target.setClassName("com.android.chrome", "com.google.android.apps.chrome.Main");
        Intent intent = target.createChooser(target, "Abrir arquivo");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //Caso o usuário não tenha um visualizador de PDF, instrua-o aqui a baixar
        }


    }

    public void AbrirArquivoBaixadoMetodo4(String caminho){

        //Abrir o Arquivo já baixado
        File file = new File( caminho );
        Uri path = Uri.fromFile(file);
        FileOpen arquivoNovo = new FileOpen();

        try {
            arquivoNovo.openFile(VisualizarFile.this,file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    private void openFilePDF(String url){
        try{
            Toast.makeText(getBaseContext(), "Opening PDF... ", Toast.LENGTH_SHORT).show();
            Intent inte = new Intent(Intent.ACTION_VIEW);
            inte.setDataAndType(
                    Uri.parse(url.toString()),
                    "application/pdf");

            startActivity(inte);
        }catch(ActivityNotFoundException e){

            //Log.e("Viewer not installed on your device.", e.getMessage());
        }
    }


}
