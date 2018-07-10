package br.com.tiberio.fechamentoexterno.adapter;

/**
 * Created by Vinicius.Dantas on 09/03/2018.
 */

public class ProdutoList {

    public String usuario;
    private String nomeproduto;
    private int resIdImagem;

    public ProdutoList(String usuario, String nomeproduto,int resIdImagem){
        this.usuario = usuario;
        this.nomeproduto = nomeproduto;
        this.resIdImagem = resIdImagem;

    }

    public String getusuario(){ return this.usuario; }
    public String getnomeproduto(){ return this.nomeproduto; }
    public int   getIdImagem(){ return this.resIdImagem; }
}
