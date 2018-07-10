package br.com.tiberio.fechamentoexterno.adapter;

/**
 * Created by Vinicius.Dantas on 12/03/2018.
 */

public class ArquivoList {

    public String usuario;
    private String nomeproduto;
    private String tipodocumento;
    private String nomearquivo;
    private int resIdImagem;

    public ArquivoList(String usuario, String nomeproduto, String tipodocumento,String nomearquivo , int resIdImagem){
        this.usuario = usuario;
        this.nomeproduto = nomeproduto;
        this.tipodocumento = tipodocumento;
        this.nomearquivo = nomearquivo;
        this.resIdImagem = resIdImagem;

    }

    public String getusuario(){ return this.usuario; }
    public String getnomeproduto(){ return this.nomeproduto; }
    public String getTipodocumento(){ return this.tipodocumento; }
    public String getnomearquivo(){ return this.nomearquivo; }
    public int   getIdImagem(){ return this.resIdImagem; }
}
