package br.com.tiberio.fechamentoexterno.webservice;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Vinicius.Dantas on 04/12/2017.
 */

public class WebServiceSoap {

    public WebServiceSoap() {
    }


    //ValidarUsuario(String user, String pass)
    public String loginFExterno(String user, String pass) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/LoginAndoidWS?WSDL", "ValidarUsuarioFExterno"); //Java


        soap.addProperty("user", user);
        soap.addProperty("query", pass);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE("http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/LoginAndoidWS?OpenWebService");
            httpTrans.call("ValidarUsuarioFExterno", envelope);


            Object resultado = envelope.getResponse();
            return resultado.toString();

        }catch (Exception ex){
            Log.e("ConexãoWS-loginnames","Erro",ex);
            return ex.getMessage().toString();
        }
    }


    //Serviço para retornar lista de documentos da view
    public Object RetornarListaProdEmp(String user) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivel1"); //Java
        soap.addProperty("user", user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivel1", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Emprendimentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }




    //Serviço para retornar lista de documentos da view
    public Object RetornarListaLocalidades(String user) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivelLocal"); //Java
        soap.addProperty("user", user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivelLocal", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Emprendimentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }


    public Object RetornarListaBairros(String user,String nomelocalidade) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivelBairro"); //Java
        soap.addProperty("user", user);
        soap.addProperty("Local", nomelocalidade);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivelBairro", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Bairros",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }

    public Object RetornarListaTipoDocumentos1(String user,String nomelocalidade,String nomebairro, String nomeproduto) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivelTipoDocumento1"); //Java
        soap.addProperty("user", user);
        soap.addProperty("Local", nomelocalidade);
        soap.addProperty("Bairro", nomebairro);
        soap.addProperty("nomeproduto", nomeproduto);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivelTipoDocumento1", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Bairros",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }



    public Object RetornarListaProdutos1(String user,String nomelocalidade, String nomebairro) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivelProduto1"); //Java
        soap.addProperty("user", user);
        soap.addProperty("Local", nomelocalidade);
        soap.addProperty("Bairro", nomebairro);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivelProduto1", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Bairros",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }

    public Object RetornarTipoDocumentosEmp(String user, String nomeproduto ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivel2"); //Java
        soap.addProperty("user", user);
        soap.addProperty("nomeproduto", nomeproduto);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivel2", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Tipo Documentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }

    public Object RetornarTipoDocumentosOC(String user ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuOUTCATNivel1"); //Java
        soap.addProperty("user", user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuOUTCATNivel1", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Tipo Documentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }


    public Object RetornarTipoDocumentosOC2(String user, String tipodocumento ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuOUTCATNivel2"); //Java
        soap.addProperty("user", user);
        soap.addProperty("tipodocumento", tipodocumento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuOUTCATNivel2", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Tipo Documentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }

    public Object RetornarArquivosOC(String user, String tipodocumento1, String  tipodocumento2) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuOUTCATNivel3"); //Java
        soap.addProperty("user", user);
        soap.addProperty("tipodocumento1", tipodocumento1);
        soap.addProperty("tipodocumento2", tipodocumento2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuOUTCATNivel3", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Tipo Documentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }



    public Object RetornarArquivosEmp(String user, String nomeproduto, String tipodocumento ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivel3"); //Java
        soap.addProperty("user", user);
        soap.addProperty("nomeproduto", nomeproduto);
        soap.addProperty("tipodocumento", tipodocumento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivel3", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Arquivos Empreendimentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }

    public Object RetornarArquivos1Emp(String user, String nomelocalidade, String nomebairro,String nomeproduto ,String tipodocumento ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpNivelArquivo1"); //Java
        soap.addProperty("user", user);
        soap.addProperty("Local", nomelocalidade);
        soap.addProperty("Bairro", nomebairro);
        soap.addProperty("nomeproduto", nomeproduto);
        soap.addProperty("tipodocumento", tipodocumento);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpNivelArquivo1", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Arquivos Empreendimentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }


    public Object RetornarDetalheArquivosEmp(String user, String nomeproduto, String tipodocumento, String NomeArquivo ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuEmpDetalheArquivo"); //Java
        soap.addProperty("user", user);
        soap.addProperty("nomemkt", nomeproduto);
        soap.addProperty("tipodocumento", tipodocumento);
        soap.addProperty("NomeArquivo", NomeArquivo);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuEmpDetalheArquivo", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Arquivos Empreendimentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }


    public Object RetornarDetalheArquivosOC(String user, String tipodocumento1, String tipodocumento2, String NomeArquivo ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuOUTCATDetalheArquivo"); //Java
        soap.addProperty("user", user);
        soap.addProperty("tipodocumentoa", tipodocumento1);
        soap.addProperty("tipodocumentob", tipodocumento2);
        soap.addProperty("nomearquivo", NomeArquivo);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuOUTCATDetalheArquivo", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Arquivos Empreendimentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }

    public Object RetornarPesquisaArquivos(String user, String Filtro ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarPesquisaModeLk"); //Java
        soap.addProperty("user", user);
        soap.addProperty("Filtro", Filtro);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarPesquisaModeLk", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Pesquisa Arquivos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }


    public Object RetornarDetalheArquivosPesquisar(String user, String NomeArquivo ) throws IOException,XmlPullParserException {
        SoapObject soap = new SoapObject("http://portal.tiberio.com.br/apps/tib_webservice.nsf/AppFechamentoExterno?WSDL", "RetornarMenuPesquisarDetalheArquivo"); //Java
        soap.addProperty("user", user);
        soap.addProperty("nomearquivo", NomeArquivo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        try {
            HttpTransportSE httpTrans = new HttpTransportSE( "http://portal.tiberio.com.br:80/apps/tib_webservice.nsf/AppFechamentoExterno?OpenWebService" );
            httpTrans.call( "RetornarMenuPesquisarDetalheArquivo", envelope );

            Vector<String> res;

            Object resultado = envelope.getResponse();
            res = (Vector<String>) resultado;

            if (res.get(0).isEmpty() || res == null){
                res.set(0,"");
                return res;
            }else{
                return resultado;
            }

        }catch (Exception ex){

            Log.e("ConexãoWS-dados","Erro retorno Menu Arquivos Empreendimentos",ex);
            Vector<String> res2 = new Vector(0);
            res2.add("");
            return res2;

        }
    }


    public static boolean VericarConexaoRede(Context contexto) {
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);// Pego a conectividade do contexto
        NetworkInfo netInfo = cm.getActiveNetworkInfo();// Crio o objeto netInfo que recebe as informacoes da Network
        if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())) { // Se nao tem conectividade retorna false
            return true;
        }
        return false;
    }


}
