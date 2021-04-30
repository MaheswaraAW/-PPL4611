package com.example.catalogboardgame.model;

public class CatalogBoardGame {
    private String SNamaGame;
    private String SKategori;
    private String SGambarGame;
    private Integer IJumlahPemain;
    private String SDeskripsiGame;

    public CatalogBoardGame(){

    }

    public CatalogBoardGame(String SGambarGame){
        this.SGambarGame=SGambarGame;
    }

    public String getSGambarGame() {
        return SGambarGame;
    }

    public void setSGambarGame(String SGambarGame) {
        this.SGambarGame = SGambarGame;
    }

    public String getSNamaGame() {
        return SNamaGame;
    }

    public void setSNamaGame(String SNamaGame) {
        this.SNamaGame = SNamaGame;
    }

    public String getSKategori() {
        return SKategori;
    }

    public void setSKategori(String SKategori) {
        this.SKategori = SKategori;
    }

//    public Integer getIGambarGame() {
//        return IGambarGame;
//    }
//
//    public void setIGambarGame(Integer IGambarGame) {
//        this.IGambarGame = IGambarGame;
//    }

    public Integer getIJumlahPemain() {
        return IJumlahPemain;
    }

    public void setIJumlahPemain(Integer IJumlahPemain) {
        this.IJumlahPemain = IJumlahPemain;
    }

    public String getSDeskripsiGame() {
        return SDeskripsiGame;
    }

    public void setSDeskripsiGame(String SDeskripsiGame) {
        this.SDeskripsiGame = SDeskripsiGame;
    }
}
