package br.com.tiberio.fechamentoexterno.adapter;

/**
 * Created by Vinicius.Dantas on 12/03/2018.
 */

public class TipodocumentoList {

    public String usuario;
    private String nomeproduto;
    private String tipodocumento;
    private int resIdImagem;

    public TipodocumentoList(String usuario, String nomeproduto, String tipodocumento,int resIdImagem){
        this.usuario = usuario;
        this.nomeproduto = nomeproduto;
        this.tipodocumento = tipodocumento;
        this.resIdImagem = resIdImagem;

    }

    public String getusuario(){ return this.usuario; }
    public String getnomeproduto(){ return this.nomeproduto; }
    public String getTipodocumento(){ return this.tipodocumento; }
    public int   getIdImagem(){ return this.resIdImagem; }

}
