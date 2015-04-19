package com.mycompany.unicafe;

import java.util.HashMap;

public class Kassapaate {

    private int kassassaRahaa;
    private HashMap<String, Integer> myydytLounaat;
    private HashMap<String, Integer> lounaidenHinnat;

    public Kassapaate() {
        this.kassassaRahaa = 100000;
        this.myydytLounaat = new HashMap<String, Integer>();
        myydytLounaat.put("edullinen", 0);
        myydytLounaat.put("maukas", 0);
        this.lounaidenHinnat = new HashMap<String, Integer>();
        lounaidenHinnat.put("edullinen", 240);
        lounaidenHinnat.put("maukas", 400);

    }

    public int syoEdullisesti(int maksu) {
        String lounas = "edullinen";
        return veloitaMaksu(maksu, lounas);
    }

    public int syoMaukkaasti(int maksu) {
        String lounas = "maukas";
        return veloitaMaksu(maksu, lounas);
    }

    private int veloitaMaksu(int maksu, String lounas) {
        if (maksu >= lounaidenHinnat.get(lounas)) {
            this.kassassaRahaa = kassassaRahaa + lounaidenHinnat.get(lounas);
            lisaaMyytyja(lounas);
            return maksu - lounaidenHinnat.get(lounas);
        } else {
            return maksu;
        }
    }

    public boolean syoEdullisesti(LyyraKortti kortti) {
        String lounas = "edullinen";
        return veloitaKortti(kortti, lounas);
    }

    public boolean syoMaukkaasti(LyyraKortti kortti) {
        String lounas = "maukas";
        return veloitaKortti(kortti, lounas);
    }

    private boolean veloitaKortti(LyyraKortti kortti, String lounas) {
        if (kortti.saldo() >= lounaidenHinnat.get(lounas)) {
            kortti.otaRahaa(lounaidenHinnat.get(lounas));
            lisaaMyytyja(lounas);
            return true;
        } else {
            return false;
        }
    }

    public void lataaRahaaKortille(LyyraKortti kortti, int summa) {
        if (summa >= 0) {
            kortti.lataaRahaa(summa);
            this.kassassaRahaa += summa;
        } else {
            return;
        }
    }

    public int kassassaRahaa() {
        return kassassaRahaa;
    }

    public int maukkaitaLounaitaMyyty() {
        return myydytLounaat.get("maukas");
    }

    public int edullisiaLounaitaMyyty() {
        return myydytLounaat.get("edullinen");
    }

    private void lisaaMyytyja(String key) {
        int myydyt = myydytLounaat.get(key);
        myydyt++;
        myydytLounaat.put(key, myydyt);
    }
}
