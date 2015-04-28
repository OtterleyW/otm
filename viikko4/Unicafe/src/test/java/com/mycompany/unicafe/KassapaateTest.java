/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setUp() {
      kassa = new Kassapaate();
    }
    
    @Test
    public void aluksiKassapaatteellaOikeaSaldo(){
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKateiselleToimii(){
        int annettuSumma = 240;
        int paluu = kassa.syoEdullisesti(annettuSumma);
        
        assertEquals(annettuSumma - 240, paluu);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000+240, kassa.kassassaRahaa());        
    }
    
    @Test
    public void maukkaanLounaanOstoKateisellaToimii(){
        int annettuSumma = 400;
        int paluu = kassa.syoMaukkaasti(annettuSumma);

        assertEquals(annettuSumma - 400, paluu);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000 + 400, kassa.kassassaRahaa());
    }
    
        @Test
        public void RahatPalautetaanJosEiTarpeeksiEdulliseenLounaaseen() {
        int annettuSumma = 200;
        int paluu = kassa.syoEdullisesti(annettuSumma);
        
        assertEquals(annettuSumma, paluu);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());

    }
        
        @Test
        public void RahatPalautetaanJosEiTarpeeksiMaukkaaseenLounaaseen() {
        int annettuSumma = 300;
        int paluu = kassa.syoMaukkaasti(annettuSumma);

        assertEquals(annettuSumma, paluu);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());

    }
        
    @Test
    public void edullisenLounaanOstoKortilla() {
        LyyraKortti kortti = new LyyraKortti(1000);
        boolean olikoRahaa = kassa.syoEdullisesti(kortti);
        assertEquals(true, olikoRahaa);
        assertEquals(1000-240, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullinenLounasKortillaJollaTasanLounaanHintaRahaa() {
        LyyraKortti kortti = new LyyraKortti(240);
        boolean olikoRahaa = kassa.syoEdullisesti(kortti);
        assertEquals(true, olikoRahaa);
        assertEquals(0, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullistaLounastaEiMyydaJosKortillaEiTarpeeksiRahaa() {
        LyyraKortti kortti = new LyyraKortti(140);
        boolean olikoRahaa = kassa.syoEdullisesti(kortti);
        assertEquals(false, olikoRahaa);
        assertEquals(140, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanLounaanOstoKortilla() {
        LyyraKortti kortti = new LyyraKortti(1000);
        boolean olikoRahaa = kassa.syoMaukkaasti(kortti);
        assertEquals(true, olikoRahaa);
        assertEquals(1000 - 400, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukasLounasKortillaJollaTasanLounaanHintaRahaa() {
        LyyraKortti kortti = new LyyraKortti(400);
        boolean olikoRahaa = kassa.syoMaukkaasti(kortti);
        assertEquals(true, olikoRahaa);
        assertEquals(0, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukastaLounastaEiMyydaJosKortillaEiTarpeeksiRahaa() {
        LyyraKortti kortti = new LyyraKortti(140);
        boolean olikoRahaa = kassa.syoMaukkaasti(kortti);
        assertEquals(false, olikoRahaa);
        assertEquals(140, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void rahanLataaminenToimii() {
        int ladattuSumma = 1000;
        LyyraKortti kortti = new LyyraKortti(0);
        kassa.lataaRahaaKortille(kortti, ladattuSumma);
        assertEquals(ladattuSumma, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }

    @Test
    public void rahanLataaminenToimiiEiNegatiivisilla() {
        int ladattuSumma = -1000;
        LyyraKortti kortti = new LyyraKortti(0);
        kassa.lataaRahaaKortille(kortti, ladattuSumma);
        assertEquals(0, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }



}
