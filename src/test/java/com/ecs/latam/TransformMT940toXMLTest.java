package com.ecs.latam;


import com.ecs.latam.kafka.domain.TransformMT940toXML;
import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.text.ParseException;

class TransformMT940toXMLTest {

    @Test
    public void cargarMensaje() throws IOException, DatatypeConfigurationException, ParseException {
        //TransformMT940toXML t = new TransformMT940toXML();
        String inMsg ="";
        inMsg = Lib.readResource("MT940_Pages.txt");
        Assertions.assertNotEquals(inMsg,"");
       // t.transformacion(inMsg);

    }



    @Test
    public void separadorTest() throws IOException, DatatypeConfigurationException, ParseException {
        TransformMT940toXML t = new TransformMT940toXML();
        String inMsg ="";
        inMsg = Lib.readResource("MT940_Pages.txt");
        Assertions.assertNotEquals(t.separadorMT940(inMsg),"","()-->No hay mensaje en la salida");


    }












}