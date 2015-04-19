 package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {

    Kassapaate paate;
    LyyraKortti kortti;
    final int EDULLINEN = 240;
    final int MAUKAS = 400;
    final int KORTIN_ALKUSALDO = 1000;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new LyyraKortti(KORTIN_ALKUSALDO);
    }

    @Test
    public void alustettuOikein() {
        assertKassa(0, 0, 0);
    }

    @Test
    public void edullinenLounasKateisella() {
        int annettuSumma = EDULLINEN;
        kortti = new LyyraKortti(KORTIN_ALKUSALDO);
        int paluu = paate.syoEdullisesti(annettuSumma);
        assertEquals(annettuSumma - EDULLINEN, paluu);
        assertKassa(1, 0, annettuSumma);
    }

    @Test
    public void kokoRahaPalautetaanJosEiTarpeeksiEdulliseenLounaaseen() {
        int annettuSumma = EDULLINEN - 1;
        int paluu = paate.syoEdullisesti(annettuSumma);
        assertEquals(annettuSumma, paluu);
        assertKassa(0, 0, 0);

    }

    @Test
    public void maukasLounasKateisella() {
        int annettuSumma = MAUKAS;
        int paluu = paate.syoMaukkaasti(MAUKAS);
        assertEquals(annettuSumma - MAUKAS, paluu);
        assertKassa(0, 1, annettuSumma);
    }

    @Test
    public void kokoRahaPalautetaanJosEiTarpeeksiMaukkaaseenLounaaseen() {
        int annettuSumma = MAUKAS - 1;
        int paluu = paate.syoMaukkaasti(annettuSumma);
        assertEquals(annettuSumma, paluu);
        assertKassa(0, 0, 0);
    }

    @Test
    public void edullinenLounasKortilla() {
        boolean ok = paate.syoEdullisesti(kortti);
        assertTrue(ok);
        assertEquals(KORTIN_ALKUSALDO - EDULLINEN, kortti.saldo());
        assertKassa(1, 0, 0);
    }

    @Test
    public void edullinenLounasKortillaJollaJuuriTarpeeksiRAhaa() {
        kortti = new LyyraKortti(EDULLINEN);
        boolean ok = paate.syoEdullisesti(kortti);
        assertTrue(ok);
        assertEquals(0, kortti.saldo());
        assertKassa(1, 0, 0);
    }

    @Test
    public void edullistaLounastaEiMyydaJosKortillaEiTarpeeksiRahaa() {
        int alkusaldo = EDULLINEN - 1;
        kortti = new LyyraKortti(alkusaldo);
        boolean ok = paate.syoEdullisesti(kortti);
        assertFalse(ok);
        assertEquals(alkusaldo, kortti.saldo());
        assertKassa(0, 0, 0);
    }

    @Test
    public void maukasLounasKortilla() {
        boolean ok = paate.syoMaukkaasti(kortti);
        assertTrue(ok);
        assertEquals(KORTIN_ALKUSALDO - MAUKAS, kortti.saldo());
        assertKassa(0, 1, 0);
    }

    @Test
    public void maukasLounasKortillaJollaJuuriTarpeeksiRahaa() {
        kortti = new LyyraKortti(MAUKAS);
        boolean ok = paate.syoMaukkaasti(kortti);
        assertTrue(ok);
        assertEquals(0, kortti.saldo());
        assertKassa(0, 1, 0);
    }

    @Test
    public void maukastaLounastaEiMyydaJosKortillaEiTarpeeksiRahaa() {
        int alkusaldo = MAUKAS - 1;
        kortti = new LyyraKortti(alkusaldo);
        boolean ok = paate.syoMaukkaasti(kortti);
        assertFalse(ok);
        assertEquals(alkusaldo, kortti.saldo());
        assertKassa(0, 0, 0);
    }

    @Test
    public void rahanLataaminenToimii() {
        int ladattuSumma = 1000;
        paate.lataaRahaaKortille(kortti, ladattuSumma);
        assertEquals(KORTIN_ALKUSALDO + ladattuSumma, kortti.saldo());
        assertKassa(0, 0, ladattuSumma);
    }

    @Test
    public void rahanLataaminenToimiiEiNegatiivisilla() {
        int ladattuSumma = 1;
        paate.lataaRahaaKortille(kortti, ladattuSumma);
        assertEquals(KORTIN_ALKUSALDO + ladattuSumma, kortti.saldo());
        assertKassa(0, 0, ladattuSumma);
    }

    private void assertKassa(int edullisia, int maukkaita, int rahaa) {
        int KASSASSA_ALUSSA = 100000;
        assertEquals(edullisia, paate.edullisiaLounaitaMyyty());
        assertEquals(maukkaita, paate.maukkaitaLounaitaMyyty());
        assertEquals(KASSASSA_ALUSSA + rahaa, paate.kassassaRahaa());
    }

    private void negatiivinenLataus(int negatiivinenSumma) {
        paate.lataaRahaaKortille(kortti, negatiivinenSumma);
        assertEquals(KORTIN_ALKUSALDO, kortti.saldo());
        assertKassa(0, 0, 0);
    }

    private void einegatiivisenLataaminen(int ladattuSumma) {
        kortti = new LyyraKortti(KORTIN_ALKUSALDO);
        paate = new Kassapaate();

        paate.lataaRahaaKortille(kortti, ladattuSumma);
        assertEquals(KORTIN_ALKUSALDO + ladattuSumma, kortti.saldo());
        assertKassa(0, 0, ladattuSumma);
    }
}