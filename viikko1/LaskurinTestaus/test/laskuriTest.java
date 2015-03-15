/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jenni_000
 */
public class laskuriTest {
    
    YlhaaltaRajoitettuLaskuri laskuri;
    
    public laskuriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        laskuri = new YlhaaltaRajoitettuLaskuri(4);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void laskurinAlkuarvoOnNolla() {
        assertEquals("00", laskuri.toString());
    }
    
    @Test
    public void laskuriEteneeKerranArvoYksi() {
        laskuri.seuraava();
        assertEquals("01", laskuri.toString());
    }
    
    @Test
    public void laskuriEeteneeKahdestiArvoKaksi() {
        laskuri.seuraava();
        laskuri.seuraava();
        assertEquals("02", laskuri.toString());
    }
    
    @Test
    public void laskuriEteneeYlarajanVerranArvoYlaraja() {
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        assertEquals("04", laskuri.toString());
    }
    
    @Test
    public void laskuriEteneeYhdenYlarajaaEnemmanArvoNolla() {
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        assertEquals("00", laskuri.toString());
    }
    
    @Test
    public void laskuriEteneeKaksiYlarajaaEnemmanArvoYksi() {
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        laskuri.seuraava();
        assertEquals("01", laskuri.toString());
    }
    
    @Test
    public void laskuriAsetArvoToimiiKunNollanJaYlarajanValilla() {
        laskuri.asetaArvo(3);
        assertEquals("03", laskuri.toString());
    }
    
    @Test
    public void etunollaJodArvoAlleKymmenen() {
        laskuri.asetaArvo(1);
        assertEquals("01", laskuri.toString());
    }
    
    @Test
    public void eiEtunollaaJosArvoYliKymmenen() {
      laskuri = new YlhaaltaRajoitettuLaskuri(15);
      laskuri.asetaArvo(11);
      assertEquals("11", laskuri.toString());
    }
}
