package br.com.tiberio.fechamentoexterno.task;

import android.os.AsyncTask;
import android.util.Log;

import java.util.StringTokenizer;

import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

/**
 * Created by Vinicius.Dantas on 26/03/2018.
 */

public class TaskLogin extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {

        try {

            String parametro = strings[0];

            // Cria um StringTokenizer para separar valores
            StringTokenizer st = new StringTokenizer(parametro.toString());

            String usuario = st.nextToken("#");
            String senha = st.nextToken("#");

            // Verifica o próximo token
            Log.i( "usuario: " + usuario, "doInBackground: " + usuario );
            //Log.i( "senha: " + senha, "doInBackground: " + senha);

            WebServiceSoap wssoap =  new WebServiceSoap();
            String res;
            //Chama o serviço Domino para Retornar Lista de Arquivos Pesquisados
            res = wssoap.loginFExterno(usuario, senha);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERRO-XmlPull", e.toString());
            return null;
        }

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute( s );
    }
}
