package com.ecs.latam;

import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ValidationMulticashTest {
    @Test
    public void entradaMensaje() throws IOException, DatatypeConfigurationException, ParseException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC ="";
        String inMsgD ="";
        inMsgC = Lib.readResource("13C08112019C0311002224.txt");
        inMsgD = Lib.readResource("13D08112019C0311002224.txt");
        Assertions.assertNotEquals(inMsgC,"");
        Assertions.assertNotEquals(inMsgD,"");
        v.validarMC(inMsgC,inMsgD);
    }

}