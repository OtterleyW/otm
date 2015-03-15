/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kellondebuggaus;

/**
 *
 * @author Jenni
 */
public class KellonDebuggaus {

  public static void main(String[] args) {

        YlhaaltaRajoitettuLaskuri sekunnit = new YlhaaltaRajoitettuLaskuri(59);
        YlhaaltaRajoitettuLaskuri minuutit = new YlhaaltaRajoitettuLaskuri(59);
        YlhaaltaRajoitettuLaskuri tunnit = new YlhaaltaRajoitettuLaskuri(23);

        tunnit.asetaArvo(23);
        minuutit.asetaArvo(50);       

        int i = 0;
        while (i < 602) {
            System.out.println(tunnit + ":" + minuutit + ":" + sekunnit);
            sekunnit.seuraava();
            if (sekunnit.arvo() == 0) {
                minuutit.seuraava();
                if (minuutit.arvo()==0 ) {
                    tunnit.seuraava();
                }
            }

            i++;
        }
    }
    
}
