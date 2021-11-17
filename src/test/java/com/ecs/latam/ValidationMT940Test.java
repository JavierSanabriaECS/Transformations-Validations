package com.ecs.latam;

import com.ecs.latam.kafka.domain.ValidationMT940;
import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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