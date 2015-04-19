package verkkokauppa;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    private Ostoskori ostoskori;

    public OstoskoriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ostoskori = new Ostoskori();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodunOstoskorinHintaJaTuotteidenMaara0() {

        assertEquals(0, ostoskori.tuotteitaKorissa());
        assertEquals(0, ostoskori.hinta());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiTuote() {
        Tuote karjala = new Tuote("Karjala", 3);
        ostoskori.lisaaTuote(karjala);

        assertEquals(1, ostoskori.tuotteitaKorissa());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorinHintaOnTuotteenHinta() {
        Tuote karjala = new Tuote("Karjala", 3);
        ostoskori.lisaaTuote(karjala);

        assertEquals(karjala.getHinta(), ostoskori.hinta());
    }

    @Test
    public void KahdenTuotteenLisaamisenJalkeenKorissaKaksiTuotetta() {
        Tuote karjala = new Tuote("Karjala", 3);
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karjala);
        ostoskori.lisaaTuote(karhu);

        assertEquals(2, ostoskori.tuotteitaKorissa());
    }

    @Test
    public void KahdenTuotteenLisaamisenJalkeenKorinHintaTuotteidenHintojenSumma() {
        Tuote karjala = new Tuote("Karjala", 3);
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karjala);
        ostoskori.lisaaTuote(karhu);

        assertEquals((karjala.getHinta() + karhu.getHinta()), ostoskori.hinta());
    }

    @Test
    public void KahdenSamanTuotteenLisaamisenJalkeenKorissaKaksiTuotetta() {
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karhu);
        ostoskori.lisaaTuote(karhu);

        assertEquals(2, ostoskori.tuotteitaKorissa());
    }

    @Test
    public void KahdenSamanTuotteenLisaamisenJalkeenKorinHintaOnKaksiKertaaTuotteenHinta() {
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karhu);
        ostoskori.lisaaTuote(karhu);

        assertEquals((karhu.getHinta() * 2), ostoskori.hinta());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlio() {
        Tuote karhu = new Tuote("Karhu", 2);
        ostoskori.lisaaTuote(karhu);

        ArrayList<Ostos> ostokset = ostoskori.ostokset();

        assertEquals(1, ostokset.size());
    }

    @Test
    public void yhdenTuotteenLisaamisenKorissaYksiOstosOlioJollaOikeaTuotteenNimiJaMaara() {
        Tuote karhu = new Tuote("Karhu", 2);
        ostoskori.lisaaTuote(karhu);

        Ostos ostos = ostoskori.ostokset().get(0);

        assertEquals(karhu.getNimi(), ostos.tuotteenNimi());
        assertEquals(1, ostos.lukumaara());
    }
    
    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenKorissaOnKaksiOstosta() {
        Tuote karjala = new Tuote("Karjala", 3);
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karjala);
        ostoskori.lisaaTuote(karhu);
        
        ArrayList<Ostos> ostokset = ostoskori.ostokset();
        
        assertEquals(2, ostokset.size());
    }
    
    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaOnVainYksiOstos() {
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karhu);
        ostoskori.lisaaTuote(karhu);
        
        ArrayList<Ostos> ostokset = ostoskori.ostokset();
        
        assertEquals(1, ostokset.size());
    }
    
    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaYksiOstosJollaSamaNimiKuinTuotteellaJaLukumaara2(){
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karhu);
        ostoskori.lisaaTuote(karhu);

        ArrayList<Ostos> ostokset = ostoskori.ostokset();
        
       assertEquals(1, ostokset.size());
       assertEquals(karhu.getNimi(), ostokset.get(0).tuotteenNimi());
       assertEquals(2, ostokset.get(0).lukumaara());
    }
    
    @Test
    public void josKoriinLisättyYksiTuotePoistettaanOnKoriTyhja(){
        Tuote karhu = new Tuote("Karhu", 2);

        ostoskori.lisaaTuote(karhu);
        ostoskori.poista(karhu);
        
        ArrayList<Ostos> ostokset = ostoskori.ostokset();
        
        assertEquals(0, ostokset.size());
        assertEquals(0, ostoskori.hinta());
        assertEquals(0, ostoskori.tuotteitaKorissa());
    }
    
    @Test
    public void josKorissaKaksiTuotettaJaToinenPoistetaanJaaYksiKpl(){
        Tuote karhu = new Tuote("Karhu", 2);
        
        ostoskori.lisaaTuote(karhu);
        ostoskori.lisaaTuote(karhu);
        ostoskori.poista(karhu);
        
        ArrayList<Ostos> ostokset = ostoskori.ostokset();
        
        assertEquals(1, ostokset.get(0).lukumaara());
    }
    
    @Test
    public void tyhjennaTyhjentaaKorin() {
        Tuote karhu = new Tuote("Karhu", 2);
        Tuote karjala = new Tuote("Karjala", 3);
        
        ostoskori.lisaaTuote(karhu);
        ostoskori.lisaaTuote(karhu);
        ostoskori.lisaaTuote(karjala);
        
        ostoskori.tyhjenna();
        
        ArrayList<Ostos> ostokset = ostoskori.ostokset();
        
        assertEquals(0, ostokset.size());

    }
}
