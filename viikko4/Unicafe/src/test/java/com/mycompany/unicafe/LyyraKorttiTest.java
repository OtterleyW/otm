package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LyyraKorttiTest {

    LyyraKortti kortti;

    @Before
    public void setUp() {
        kortti = new LyyraKortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void luodullaKortillaOnAnnettuSaldo(){
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa(){
        kortti.lataaRahaa(3);
        assertEquals(13, kortti.saldo());
        
    }
    
    @Test
    public void rahanOttaminenToimiiJosRahaaTarpeeksi(){
        boolean olikoRahaa =  kortti.otaRahaa(3);
        
        assertEquals(true, olikoRahaa);
        assertEquals(7, kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenEiMuutaSaldoaJosRahaaEiTarpeeksi(){
        boolean olikoRahaa =  kortti.otaRahaa(13);
        
        assertEquals(false, olikoRahaa);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenToimiiJosRahaaOtetaanSaldonVerran(){
        boolean olikoRahaa = kortti.otaRahaa(10);
        assertEquals(true, olikoRahaa);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void KorttiToStringToimii(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}
    
