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
 * @author Jenni
 */
public class LukutilastoTest {

    Lukutilasto tilasto = new Lukutilasto();

    public LukutilastoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void AlussaMaaraNolla() {
        assertEquals(0, tilasto.haeLukujenMaara());
    }

    @Test
    public void AlussaSummaNOlla() {
        assertEquals(0, tilasto.summa());
    }

    @Test
    public void lisaaLukuLisaaMaaraa() {
        tilasto.lisaaLuku(3);
        assertEquals(1, tilasto.haeLukujenMaara());
    }

    @Test
    public void lisaalukuLisaaSummaan() {
        tilasto.lisaaLuku(3);
        assertEquals(3, tilasto.summa());
    }

    @Test
    public void haeLukujenMaaraToimii() {
        tilasto.lisaaLuku(1);
        tilasto.lisaaLuku(7);
        tilasto.lisaaLuku(4);
        assertEquals(3, tilasto.haeLukujenMaara());
    }

    @Test
    public void summaToimii() {
        tilasto.lisaaLuku(8);
        tilasto.lisaaLuku(12);
        tilasto.lisaaLuku(3);
        assertEquals(23, tilasto.summa());
    }
    
    @Test
    public void keskiarvoPalauttaaNollanJosLukujenMaaraNolla() {
        assertEquals(0, tilasto.keskiarvo(), 0.001);
    }
    
    @Test
    public void keskiarvoToimii() {
        tilasto.lisaaLuku(5);
        tilasto.lisaaLuku(3);
        assertEquals(4, tilasto.keskiarvo(), 0.001);
    }

}
