package com.ecs.latam;

import com.ecs.latam.kafka.domain.ValidationMT940;
import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

class ValidationMT940Test {
    private  String MT940_OK;

    @BeforeAll
    void setup() throws IOException {

        MT940_OK  = Lib.readResource("MT940_accounts.txt");

    }


    @Test
    public void givenMT940_WhenFileIsOk_ThenValidationIsTrue() {
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validarMT(MT940_OK);
        assertThat(isValidated).isTrue();
    }
}