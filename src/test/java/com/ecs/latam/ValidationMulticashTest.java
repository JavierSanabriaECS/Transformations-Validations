package com.ecs.latam;

import com.ecs.latam.kafka.domain.InvalidMCException;
import com.ecs.latam.kafka.domain.ValidationMulticash;
import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.text.ParseException;

class ValidationMulticashTest {

    @Test
    public void givenMulticash_WhenFilesAreOk_ThenValidationIsTrue() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("13C08112019C0311002224.txt");
        String inMsgD = Lib.readResource("13D08112019C0311002224.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isTrue();
        }

    }

    @Test
    public void givenMulticash_WhenHeaderIsNotOk_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Bad_Header.txt");
        String inMsgD = Lib.readResource("Good_Details.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveBancoInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ClaveBancoInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveBancoIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ClaveBancoIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenCuentaBancariaIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_CuentaBancariaIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenCuentaBancariaInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_CuentaBancariaInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenConsecutivoInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ConsecutivoInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenConsecutivoIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ConsecutivoIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenConsecutivoIsZero_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ConsecutivoIsZero.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenConsecutivoInvalidFormart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ConsecutivoInvalidFormart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechasMovimientosIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_FechasMovimientosIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechasMovimientosInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_FechasMovimientosInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechasMovimientosInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_FechasMovimientosInvalidFormat.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveMonedaIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ClaveMonedaIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveMonedaInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ClaveMonedaInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveMonedaInvalidType_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_ClaveMonedaInvalidType.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoInicialCuentaIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoInicialCuentaIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoInicialCuentaInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoInicialCuentaInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoInicialCuentaInvalidSigno_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoInicialCuentaInvalidSigno.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoInicialCuentaInvalidIntegerPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoInicialCuentaInvalidIntegerPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoInicialCuentaInvalidDecimalPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoInicialCuentaInvalidDecimalPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoInicialCuentaWithoutDecimalPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoInicialCuentaWithoutDecimalPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalDebitosIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalDebitosIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalDebitosInvalidSigno_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalDebitosInvalidSigno.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalDebitosInvalidIntegerPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalDebitosInvalidIntegerPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalDebitosInvalidDecimalPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalDebitosInvalidDecimalPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalDebitosInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalDebitosInvalidFormat.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalDebitosInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalDebitosInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalCreditosIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalCreditosIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalCreditosInvalidSigno_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalCreditosInvalidSigno.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalCreditosInvalidIntegerPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalCreditosInvalidIntegerPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalCreditosInvalidDecimalPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalCreditosInvalidDecimalPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalCreditosInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalCreditosInvalidFormat.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTotalCreditosInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TotalCreditosInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoFinalIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoFinalIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoFinalInvalidSigno_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoFinalInvalidSigno.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoFinalInvalidIntegerPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoFinalInvalidIntegerPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoFinalInvalidDecimalPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoFinalInvalidDecimalPart.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoFinalInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoFinalInvalidFormat.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenSaldoFinalInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_SaldoFinalInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTipoCuentaIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TipoCuentaIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTipoCuentaInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TipoCuentaInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenTipoCuentaInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_TipoCuentaInvalidFormat.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNumeroMovimientosIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_NumeroMovimientosIsMissing.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNumeroMovimientosInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_NumeroMovimientosInvalidLength.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNumeroMovimientosInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Header_NumeroMovimientosInvalidFormat.txt");
        String inMsgD = Lib.readResource("Details_OK.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveBancoDetalleIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ClaveBancoDetalleIsMissing.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveBancoDetalleInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ClaveBancoDetalleInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenCuentaBancariaDetalleIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_CuentaBancariaDetalleIsMissing.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenCuentaBancariaDetalleInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_CuentaBancariaDetalleInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenConsecutivoDetalleIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ConsecutivoDetalleIsMissing.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenConsecutivoDetalleInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ConsecutivoDetalleInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenConsecutivoDetalleInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ConsecutivoDetalleInvalidFormat.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechaTransaccionDetalleIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_FechatransaccionDetalleIsMissing.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechaTransaccionDetalleInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_FechaTransaccionDetalleInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechaTransaccionDetalleInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_FechaTransaccionDetalleInvalidFormat.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveTransaccionBancoDetalleIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ClaveTransaccionBancoDetalleIsMissing.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenClaveTransaccionBancoDetalleInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ClaveTransaccionBancoDetalleInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNumeroChequePagadoDetalleInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_NumeroChequePagadoDetalleInvalidFormat.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNumeroChequePagadoDetalleInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_NumeroChequePagadoDetalleInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenMontoDetalleIsMissing_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_MontoDetalleIsMissing.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenMontoDetalleInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_MontoDetalleInvalidFormat.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenMontoDetalleInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_MontoDetalleInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenMontoDetalleInvalidSigno_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_MontoDetalleInvalidSigno.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenMontoDetalleInvalidIntegerPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_MontoDetalleInvalidIntegerPart.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenMontoDetalleInvalidDecimalPart_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_MontoDetalleInvalidDecimalPart.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechaEfectivaIsMissign_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_FechaEfectivaIsMissign.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechaEfectivaInvalidFormat_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_FechaEfectivaInvalidFormat.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenFechaEfectivaInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_FechaEfectivaInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNitreferencia1InvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_Nitreferencia1InvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNitreferencia2InvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_Nitreferencia2InvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenReferenciaInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_ReferenciaInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenCausalRechazoInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_CausalRechazoInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenCodigoTransaccionBancoInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_CodigoTransaccionBancoInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }

    @Test
    public void givenMulticlass_WhenNumeroFormatoConsignacionInvalidLength_ThenValidationIsFalse() throws IOException {
        ValidationMulticash v = new ValidationMulticash();
        String inMsgC = Lib.readResource("Good_Header.txt");
        String inMsgD = Lib.readResource("Details_NumeroFormatoConsignacionInvalidLength.txt");
        try {
            v.validateMC(inMsgC,inMsgD);

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }
    }
}