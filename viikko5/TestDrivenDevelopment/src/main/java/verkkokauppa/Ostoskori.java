package verkkokauppa;

import java.util.ArrayList;

public class Ostoskori {

    private ArrayList<Ostos> ostokset = new ArrayList<Ostos>();

    public int tuotteitaKorissa() {
        int tuotteitaKorissa = 0;
        for(Ostos o: ostokset){
            tuotteitaKorissa += o.lukumaara();
        }

        return tuotteitaKorissa;
    }

    public int hinta() {
        int hinta = 0;
        for(Ostos o: ostokset){
            hinta += o.hinta();
        }

        return hinta;
    }

    public void lisaaTuote(Tuote lisattava) {
        Ostos ostos = new Ostos(lisattava);
        Boolean olikoListalla = false;

        if (ostokset.size() != 0) {
            for (Ostos o : ostokset) {
                if (lisattava.getNimi() == o.tuotteenNimi()) {
                    o.muutaLukumaaraa(1);
                    olikoListalla = true;
                }
            }
        }

        if (olikoListalla == false) {
            ostokset.add(ostos);
        }
        
    }

    public void poista(Tuote poistettava) {
        Ostos ostos = new Ostos(poistettava);
        Boolean olikoListalla = false;
        for (Ostos o : ostokset) {
                if (poistettava.getNimi() == o.tuotteenNimi() && o.lukumaara() > 1) {
                    o.muutaLukumaaraa(-1);
                    olikoListalla = true;
                }
            }
       if(olikoListalla == false){
           ostokset.remove(ostos);
       }
    }

    public ArrayList<Ostos> ostokset() {
        // palauttaa listan jossa on korissa olevat ostokset

        return ostokset;
    }

    public void tyhjenna() {
        // tyhjentää korin
        ostokset.clear();
    }
}
