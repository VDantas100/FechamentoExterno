package br.com.tiberio.fechamentoexterno.adapter;

/**
 * Created by Vinicius.Dantas on 22/03/2018.
 */

public class PesquisarList {

    private String usuario;
    private String nomearquivo;
    private int resIdImagem;

    public PesquisarList(String usuario, String nomearquivo, int resIdImagem){
        this.usuario = usuario;
        this.nomearquivo = nomearquivo;
        this.resIdImagem = resIdImagem;
    }

    public String getusuario(){ return this.usuario; }
    public String getnomearquivo(){ return this.nomearquivo; }
    public int   getIdImagem(){ return this.resIdImagem; }

}
