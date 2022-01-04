package com.ecs.latam.kafka.domain;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class ValidationMulticash {
    private static final List<String> errorList = new ArrayList<>();


    public static void validateMC(String inMsgC, String inMsgD) throws InvalidMCException {
        String[] header;
        if (isHeaderFormatOK(inMsgC)) {
            header = inMsgC.split(";");
            validateLength(header[0].trim(), "Clave del banco", 12, "M");
            validateLength(header[1].trim(), "Cuenta bancaria", 24,"M");
            validateConsecutivo(header[2].trim(), "Consecutivo header");
            validateDateFormat(header[3].trim(), "Fechas movimientos");
            validateCurrency(header[4].trim(), "Clave de la moneda");
            validateAmountFormat(header[5].trim(), "Saldo Inicial Cuenta", "");
            validateAmountFormat(header[6].trim(), "Total Debitos", "D");
            validateAmountFormat(header[7].trim(), "Total Creditos", "C");
            validateAmountFormat(header[8].trim(), "Saldo Final de la Cuenta", "");
            validateTipoCuenta(header[9].trim());
            validateNumeroMovimientos(header[17].trim());

            List<String> details = DetailsFormatOK(inMsgD);
            for (String trans : details) {
                String[] detail = null;
                detail = trans.split(";");
                validateLength(detail[0].trim(), "Clave del banco", 12,"M");
                validateLength(detail[1].trim(), "Cuenta bancaria", 24,"M");
                validateConsecutivo(detail[2].trim(),"Consecutivo detail");
                validateDateFormat(detail[3].trim(), "Fechas Transaccion");
                validateLength(detail[6].trim(), "Clave Transaccion del Banco", 27,"M");
                validateLength(detail[9].trim(), "Numero de Cheque Pagado", 16,"O");
                validateAmountFormat(detail[10].trim(), "Monto", "");
                validateDateFormat(detail[13].trim(), "Fechas Efectiva");
                validateLength(detail[16].trim(), "Referencia1", 27,"O");
                validateLength(detail[17].trim(), "Referencia2", 27,"O");
                validateLength(detail[18].trim(), "Referencia", 24,"O");
                validateLength(detail[19].trim(), "Causal Rechazo", 27,"O");
                validateLength(detail[20].trim(), "Codigo unico de Transaccion", 27,"O");
                validateLength(detail[21].trim(), "Numero de Formato de Consignacion", 27,"O");

            }


        }else{
            log.error("Invalid Header message Format");
            errorList.add("Invalid Header message Format");
        }

        if (errorList.size() > 0) {
            throw new InvalidMCException(errorList);
        }



    }

    private static boolean isHeaderFormatOK(String header) {
        if (header.isBlank() || !header.contains(";")) {
            log.error("Invalid Header message Format");
            errorList.add("Invalid Header message Format");
            return false;
        } else {
            return true;
        }
    }

    private static void validateLength(String field, String name, int length, String mode) {
        String format = String.format("Length of %s is invalid. Length equals to %s", name, field.length());
        boolean isMaxOrMinLength = field.length() < 1 || field.length() > length;
        boolean isMaxLength = field.length() > length;
        if(mode.equals("M")){
            if (isMaxOrMinLength) {
                log.error(format);
                errorList.add(format);
            }
        }else{
            if (isMaxLength) {
                log.debug(format);
            }
        }
    }

    private static void validateConsecutivo(String consecutivo, String type){
        validateLength(consecutivo, "Consecutivo", 4,"M");
        if(!StringUtils.isNumeric(consecutivo)||Integer.parseInt(consecutivo)==0){
            log.error("Invalid format Consecutivo");
            errorList.add("Invalid format Consecutivo");
        }
    }

    private static void validateDateFormat(String date, String type) {
        String format = String.format("%s with Date missing or format invalid", type);
        validateLength(date,type, 8,"M");
        if (null == date) {
            log.error(format);
            errorList.add(format);
        } else {
            try {
                SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yy");
                formatDate.setLenient(false);
                formatDate.parse(date);
            } catch (ParseException e) {
                log.error(format);
                errorList.add(format);
            }
        }
    }

    private static void validateCurrency(String currency,String type) {
        String format = String.format("%s is invalid",type);
        validateLength(currency,type,3,"M");
        if (!currency.equals("COP")) {
            if (!currency.equals("USD")) {
                log.error(format);
                errorList.add(format);
            }

        }
    }

    private static void validateAmountFormat(String amount, String field, String type) {
        String format = String.format("Invalid field %s", field);
        if(amount.isEmpty()){
            validateLength(amount,field,20,"M");
        }else{
            validateSign(amount.substring(0, 1), field, type);
            validateLength(amount,field,20,"M");
            if (amount.contains(".")) {
                if (!StringUtils.isNumeric(amount.substring(1, amount.indexOf(".")))) {
                    log.error(format);
                    errorList.add(format);
                }
                if (!StringUtils.isNumeric(amount.substring(amount.indexOf(".") + 1))) {
                    log.error(format);
                    errorList.add(format);
                }
                validateLength(amount.substring(1, amount.indexOf(".")), field + " integer part", 18,"M");
                validateLength(amount.substring(amount.indexOf(".") + 1), field + " decimal part", 2,"M");

            } else {
                log.error(format);
                errorList.add(format);
            }
        }


    }

    private static void validateSign(String sign, String field, String type) {
        String format = String.format("Sign of the field %s is invalid", field);
        if (type.equals("D")) {
            if (!sign.equals("-")) {
                log.error(format);
                errorList.add(format);
            }
        } else if (type.equals("C")) {
            if (!sign.equals("+")) {
                log.error(format);
                errorList.add(format);
            }
        } else {
            if (!sign.equals("+")) {
                if (!sign.equals("-")) {
                    log.error(format);
                    errorList.add(format);
                }
            }
        }
    }

    private static void validateTipoCuenta(String tipoCuenta) {
        validateLength(tipoCuenta, "Tipo de Cuenta", 2,"M");
        if (!tipoCuenta.equals("01")) {
            if (!tipoCuenta.equals("02")) {
                log.error("Invalid Tipo de Cuenta");
                errorList.add("Invalid Tipo de Cuenta");
            }
        }
    }

    private static void validateNumeroMovimientos(String numMovimientos) {
        validateLength(numMovimientos, "Numero movimientos Detalle", 5,"M");
        if(!StringUtils.isNumeric(numMovimientos)){
            log.error("Invalid Numero movimientos Detalle");
            errorList.add("Invalid Numero movimientos Detalle");
        }
    }

    private static List<String> DetailsFormatOK(String details) {
        List<String> detailsArray = new ArrayList<>();
        if (details.isBlank() || !details.contains(";")) {
            log.error("Invalid Details message Format");
            errorList.add("Invalid Details message Format");
        } else {
            String[] detail = details.split("\n");
            for (int i = 0; i < detail.length; i++) {
                String message = detail[i];
                detailsArray.add(message);

            }
        }
        return detailsArray;
    }


}