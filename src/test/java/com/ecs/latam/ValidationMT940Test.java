package com.ecs.latam;

import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ValidationMT940Test {

    @Test
    void validarMT() {
    }
    @Test
    public void entradaMensaje() throws IOException {
       ValidationMT940 v = new ValidationMT940();
        String inMsg ="";
        inMsg = Lib.readResource("Ejemplo MT940 ScotiabankColombia.txt");
        Assertions.assertNotEquals(inMsg,"");
        v.validarMT(inMsg);
    }
}