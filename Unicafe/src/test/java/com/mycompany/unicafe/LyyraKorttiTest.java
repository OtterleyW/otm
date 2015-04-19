package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LyyraKorttiTest {

    LyyraKortti kortti;
    int ALKUSALDO = 1000;

    @Before
    public void setUp() {
        kortti = new LyyraKortti(ALKUSALDO);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void luodullaKortillaOikeaSaldo() {
        assertEquals(ALKUSALDO, kortti.saldo());
    }

    @Test
    public void ottoVahentaaRahaa() {
        boolean ok = kortti.otaRahaa(400);

        assertTrue(ok);
        assertEquals(ALKUSALDO - 400, kortti.saldo());
    }

    @Test
    public void eiVoiVahentaaYhtaanSaldoaSuurempaaMaaraa() {
        boolean ok = kortti.otaRahaa(ALKUSALDO + 1);

        assertFalse(ok);
        assertEquals(ALKUSALDO, kortti.saldo());
    }

    @Test
    public void ottoVahentaaKokoSaldonVerran() {
        boolean ok = kortti.otaRahaa(ALKUSALDO);

        assertTrue(ok);
        assertEquals(0, kortti.saldo());
    }

    @Test
    public void liianSuuriOttoEiVahennaRahaa() {
        boolean ok = kortti.otaRahaa(4000);

        assertFalse(ok);
        assertEquals(ALKUSALDO, kortti.saldo());
    }

    @Test
    public void latausKasvattaaSaldoa() {
        kortti.lataaRahaa(1000);

        assertEquals(ALKUSALDO + 1000, kortti.saldo());
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void toStringToimiiJosDesimaaleja() {
        kortti = new LyyraKortti(1010);
        assertEquals("saldo: 10.10", kortti.toString());
    }

    @Test
    public void toStringToimiiJosDesimaalissaEtunolla() {
        kortti = new LyyraKortti(1009);
        assertEquals("saldo: 10.9", kortti.toString());
    }
}