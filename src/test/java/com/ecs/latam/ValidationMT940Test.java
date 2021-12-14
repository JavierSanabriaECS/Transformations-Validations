package com.ecs.latam;

import com.ecs.latam.kafka.domain.ValidationMT940;
import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

class ValidationMT940Test {


    @Test
    public void givenMT940_WhenFileIsOk_ThenValidationIsTrue() throws IOException {

        String MT940_OK = Lib.readResource("MT940_accounts.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isTrue();
    }

    @Test
    public void givenMT940_WhenFileComesWithoutSender_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_withoutSender.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithoutReceiver_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutReceiver.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag20_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag20.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithTag20lengthMoreThan16_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag20lengthMoreThan16.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithTag20StartEndsHaveSlash_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag20StartEndsHaveSlash.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag25_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag25.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithTag25lengthMoreThan35_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag25lengthMoreThan35.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag28C_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag28C.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28CWithoutStatementNumber_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_ithTag28CWithoutStatementNumber.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28CWithoutSequenceNumber_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_ithTag28CWithoutSequenceNumber.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28C_StatementNumber_lengthMoreThan5_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag28C_StatementNumber_lengthMoreThan5.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28C_SequenceNumber_lengthMoreThan5_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WWithTag28C_SequenceNumber_lengthMoreThan5.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag60F_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag60F.txt");
        ValidationMT940 v = new ValidationMT940();

        boolean isValidated = v.validateMT(MT940_OK);
        assertThat(isValidated).isFalse();
    }






}