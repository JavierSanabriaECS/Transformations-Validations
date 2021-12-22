package com.ecs.latam;

import com.ecs.latam.kafka.domain.InvalidMTException;
import com.ecs.latam.kafka.domain.ValidationMT940;
import com.prowidesoftware.swift.utils.Lib;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import java.io.IOException;

class ValidationMT940Test {


    @Test
    public void givenMT940_WhenFileIsOk_ThenValidationIsTrue() throws IOException {

        String MT940_OK = Lib.readResource("MT940_accounts.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);

        }catch (InvalidMTException e){
            assertThat(e.getMessage().isBlank()).isTrue();
        }


    }

    @Test
    public void givenMT940_WhenFileComesWithoutSender_ThenValidationIsFalse() throws IOException{

        String MT940_OK = Lib.readResource("MT940_withoutSender.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutReceiver_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutReceiver.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag20_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag20.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag20lengthMoreThan16_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag20lengthMoreThan16.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag20StartEndsHaveSlash_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag20StartEndsHaveSlash.txt");

        // when
        Throwable thrown = catchThrowable(() -> {
            ValidationMT940.validateMT(MT940_OK);
        });

// then
        assertThat(thrown)
                .isInstanceOf(InvalidMTException.class)
                .hasMessageContaining("blkblblblbb");




    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag25_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag25.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag25lengthMoreThan35_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag25lengthMoreThan35.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag28C_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag28C.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28CWithoutStatementNumber_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_ithTag28CWithoutStatementNumber.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28CWithoutSequenceNumber_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_ithTag28CWithoutSequenceNumber.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28C_StatementNumber_lengthMoreThan5_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag28C_StatementNumber_lengthMoreThan5.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag28C_SequenceNumber_lengthMoreThan5_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag28C_SequenceNumber_lengthMoreThan5.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag60F_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag60F.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithoutDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithoutDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithDCMarkInvalid_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithDCMarkInvalid.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithoutDate_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithoutDate.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithDate_InvalidFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithDate_InvalidFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithoutCurrency_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithoutCurrency.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithoutAmount_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithoutAmount.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithAmount_LengthInvalid_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithAmount_LengthInvalid.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithAmount_CommaFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithAmount_CommaFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithAmount_InvalidPartInteger_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithAmount_InvalidPartInteger.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithAmount_InvalidDecimalPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithAmount_InvalidDecimalPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithAmount_WithoutDecimalPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithAmount_WithoutDecimalPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60F_WithInvalidAmountFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithInvalidAmountFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag60M_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag60M.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithoutDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithoutDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithDCMarkInvalid_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithDCMarkInvalid.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithoutDate_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithoutDate.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithDate_InvalidFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithDate_InvalidFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithoutCurrency_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithoutCurrency.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithoutAmount_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithoutAmount.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithAmount_LengthInvalid_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60F_WithAmount_LengthInvalid.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithAmount_CommaFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithAmount_CommaFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithAmount_InvalidPartInteger_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithAmount_InvalidPartInteger.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithAmount_InvalidDecimalPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithAmount_InvalidDecimalPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithAmount_WithoutDecimalPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithAmount_WithoutDecimalPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag60M_WithInvalidAmountFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag60M_WithInvalidAmountFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag61_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag61.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithoutDate_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithoutDate.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithInvalidFormatDate_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithInvalidFormatDate.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithoutDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithoutDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithInvalidDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WitInvalidDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithoutAmount_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithoutAmount.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithAmount_CommaInvalidFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithAmount_CommaInvalidFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithAmount_InvalidIntegerPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithAmount_InvalidIntegerPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithAmount_InvalidDecimalPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithAmount_InvalidDecimalPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_WithAmount_DecimalPartInvalidLength_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_WithAmount_DecimalPartInvalidLength.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_InvalidTransactionType_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_InvalidTransactionType.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_Without_ReferenceForTheAccountOwner_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_Without_ReferenceForTheAccountOwner.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag61_With_ReferenceForTheAccountOwner_InvalidLength_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag61_With_ReferenceForTheAccountOwner_InvalidLength.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag62F_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag62F.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithoutDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithoutDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithInvalidDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithInvalidDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithoutDate_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithoutDate.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithInvalidDateFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithInvalidDateFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithoutCurrency_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithoutCurrency.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithoutAmount_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithoutAmount.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithAmountInvalidLength_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithAmountInvalidLength.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithInvalidCommaFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithInvalidCommaFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithoutIntegerPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithoutIntegerPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithoutDecimalPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithoutDecimalPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithDecimalPartInvalidLength_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithDecimalPartInvalidLength.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62F_WithInvalidAmountFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62F_WithInvalidAmountFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithoutTag62M_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithoutTag62M.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithoutDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithoutDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithInvalidDCMark_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithInvalidDCMark.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithoutDate_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithoutDate.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithInvalidDateFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithInvalidDateFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithoutCurrency_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithoutCurrency.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithoutAmount_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithoutAmount.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithAmountInvalidLength_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithAmountInvalidLength.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithInvalidCommaFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithInvalidCommaFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithoutIntegerPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithoutIntegerPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithoutDecimalPart_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithoutDecimalPart.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithDecimalPartInvalidLength_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithDecimalPartInvalidLength.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMT940_WhenFileComesWithTag62M_WithInvalidAmountFormat_ThenValidationIsFalse() throws IOException {

        String MT940_OK = Lib.readResource("MT940_WithTag62M_WithInvalidAmountFormat.txt");
        ValidationMT940 v = new ValidationMT940();

        try {
            v.validateMT(MT940_OK);
        }catch (InvalidMTException e){
            System.out.println(e.getMessage());
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }












}