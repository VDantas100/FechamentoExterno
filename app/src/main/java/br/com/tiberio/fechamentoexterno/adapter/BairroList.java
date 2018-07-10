package br.com.tiberio.fechamentoexterno.adapter;

/**
 * Created by Vinicius.Dantas on 16/03/2018.
 */

public class BairroList {

    public String usuario;
    private String nomelocalidade;
    private String nomebairro;
    private int resIdImagem;

    public BairroList(String usuario, String nomelocalidade, String nomebairro,int resIdImagem){
        this.usuario = usuario;
        this.nomelocalidade = nomelocalidade;
        this.nomebairro = nomebairro;
        this.resIdImagem = resIdImagem;

    }

    public String getusuario(){ return this.usuario; }
    public String getlocalidade(){ return this.nomelocalidade; }
    public String getbairro(){ return this.nomebairro; }
    public int   getIdImagem(){ return this.resIdImagem; }

}
