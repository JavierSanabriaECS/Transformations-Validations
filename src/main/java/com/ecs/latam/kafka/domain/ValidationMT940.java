package com.ecs.latam.kafka.domain;

import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import lombok.extern.log4j.Log4j2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class ValidationMT940 {
    private static final List<String> errorList = new ArrayList<>();

    public static void validateMT(String inMsg) throws InvalidMTException {
        MT940 mt = null;
        try {
            mt = MT940.parse(inMsg);
        } catch (Throwable e) {
            log.error(e.toString());
            errorList.add("Invalid MT Format" + e.getMessage());
        }
        if(0!=mt.getFields().size()){
            validateSenderBIC(mt);
            validateReceiverBIC(mt);
            validateTag20(mt);
            validateTag21(mt);
            validateTag25(mt);
            validateTag28(mt);
            validateTags60FAnd60M(mt);
            validateNumbersOfTags61And86(mt);
            validateTags62FAnd62M(mt);
        }else{
            log.error("Invalid MT Format");
            errorList.add("Invalid MT Format");
        }

        if (errorList.size() > 0) {
            throw new InvalidMTException(errorList);
        }
    }

    private static void validateSenderBIC(MT940 mt) {
        String msg = "MT without sender BIC ";
        try {
            String bic = mt.getSender();
            if (bic.isBlank()) {
                log.error(msg);
                errorList.add(msg);
            }
        } catch (NullPointerException e) {
            log.error(msg + e.getMessage());
            errorList.add(msg + e.getMessage());

        }
    }

    private static void validateReceiverBIC(MT940 mt) {
        String msg = "MT without receiver BIC ";
        try {
            String bic = mt.getReceiver();
            if (bic.isBlank()) {
                log.error(msg);
                errorList.add(msg);

            }
        } catch (NullPointerException e) {
            log.error(msg + e.getMessage());
            errorList.add(msg + e.getMessage());

        }
    }

    private static void validateTag20(MT940 mt) {
        String msg = "MT without tag20";
        Field20 field20 = mt.getField20();
        if (null != field20) {
            validateLength(field20.getValue(), "Tag20", 16);
            validateStartEndsContainSlash(field20.getValue(), "20");
        } else {
            log.error(msg);
            errorList.add(msg);
        }
    }

    private static void validateTag21(MT940 mt) {
        String msg = "MT without tag21";
        Field21 field21 = mt.getField21();
        if (null != field21) {
            validateLength(field21.getValue(), "Tag21", 16);
            validateStartEndsContainSlash(field21.getValue(), "21");
        } else {
            log.debug(msg);
        }
    }

    private static void validateTag25(MT940 mt) {
        String msg = "MT without tag25";
        Field25 field25 = mt.getField25();
        if (null != field25) {
            validateLength(field25.getValue(), "Tag25", 16);
        } else {
            log.error(msg);
            errorList.add(msg);
        }
    }

    private static void validateTag28(MT940 mt) {
        String msg = "Tag28C is missing or without Statement Number or Sequence Number";
        try {
            Field28C field28C = mt.getField28C();
            if (!field28C.isEmpty()) {
                validateLength(field28C.getStatementNumber(), "Tag28C, Statement Number", 5);
                validateLength(field28C.getSequenceNumber(), "Tag28C, Sequence Number", 5);
            } else {
                log.error(msg);
                errorList.add(msg);
            }
        } catch (NullPointerException e) {
            log.error(msg);
            errorList.add(msg);
        }
    }

    private static void validateTags60FAnd60M(MT940 mt) {
        Field60F field60F = mt.getField60F();
        if (null != field60F) {
            if (!field60F.isEmpty()) {
                validateDCMark(field60F.getDCMark(), "60F");
                validateDateFormat(field60F.getDate(), "60F");
                validateCurrency(field60F.getCurrency(), "60F");
                validateAmount(field60F.getAmount(), "60F");
            } else {
                log.error("Tag60F invalid format");
                errorList.add("Tag60F invalid format");
            }
        } else {
            validateTag60M(mt);
        }
    }

    private static void validateTag60M(MT940 mt) {
        Field60M field60M = mt.getField60M();
        if (null != field60M) {
            if (!field60M.isEmpty()) {
                validateDCMark(field60M.getDCMark(), "60M");
                validateDateFormat(field60M.getDate(), "60M");
                validateCurrency(field60M.getCurrency(), "60M");
                validateAmount(field60M.getAmount(), "60M");
            } else {
                log.error("Tag60M invalid format");
                errorList.add("Tag60M invalid format");
            }
        } else {
            log.error("Tag60F and Tag60M are missing");
            errorList.add("Tag60F and Tag60M are missing");
        }
    }

    private static void validateNumbersOfTags61And86(MT940 mt) {
        if (mt.getField61().size() < mt.getField86().size()) {
            log.error("Tag61 is missing");
            errorList.add("Tag61 is missing");
        } else {
            validateTag61Content(mt);
        }
    }

    private static void validateTag61Content(MT940 mt) {
        for (int y = 0; y < mt.getField61().size(); y++) {
            Field61 f61 = mt.getField61().get(y);
            validateDateFormat(f61.getValueDate(), "61");
            validateDCMark(f61.getDCMark(), "61");
            validateAmount(f61.getAmount(), "61");
            validateTransactionType(f61.getTransactionType());
            validateReferenceForTheAccountOwner(f61.getReferenceForTheAccountOwner());
        }
    }

    private static void validateTags62FAnd62M(MT940 mt) {
        Field62F field62F = mt.getField62F();
        if (null != field62F) {
            if (!field62F.isEmpty()) {
                validateDCMark(field62F.getDCMark(), "62F");
                validateDateFormat(field62F.getDate(), "62F");
                validateCurrency(field62F.getCurrency(), "62F");
                validateAmount(field62F.getAmount(), "62F");
            } else {
                log.error("Tag62F invalid format");
                errorList.add("Tag62F invalid format");
            }
        } else {
            validateTag62M(mt);
        }
    }

    private static void validateTag62M(MT940 mt) {
        Field62M field62M = mt.getField62M();
        if (null != field62M) {
            if (!field62M.isEmpty()) {
                validateDCMark(field62M.getDCMark(), "62M");
                validateDateFormat(field62M.getDate(), "62M");
                validateCurrency(field62M.getCurrency(), "62M");
                validateAmount(field62M.getAmount(), "62M");
            } else {
                log.error("Tag62M invalid format");
                errorList.add("Tag62M invalid format");
            }
        } else {
            log.error("Tag62F and Tag62M are missing");
            errorList.add("Tag62F and Tag62M are missing");
        }
    }

    private static void validateLength(String tag, String type, int length) {
        if (tag.length() <= 0 || tag.length() > length) {
            String format = String.format("Length of %s is invalid. Length equals to %s", type, tag.length());
            log.error(format);
            errorList.add(format);
        }
    }

    private static void validateStartEndsContainSlash(String tag, String type) {
        if (tag.startsWith("/") || tag.endsWith("/") || tag.contains("//")) {
            String format = String.format("Tag %s, This field must not start or end with a slash and must "
                    + "not contain two consecutive slashes '/'", type);
            log.error(format);
            errorList.add(format);

        }
    }

    private static void validateDCMark(String mark, String type) {
        String format = String.format("Tag %s with D/C mark invalid", type);
        if (null == mark) {
            log.error(format);
            errorList.add(format);
        } else if (!(mark.equals("D")
                || mark.equals("C")
                || mark.charAt(0) == 'C'
                || mark.charAt(0) == 'D')) {
            log.error(format);
            errorList.add(format);
        }
    }

    private static void validateDateFormat(String date, String type) {
        String format = String.format("Tag %s with Date missing or format invalid", type);
        if (null == date) {
            log.error(format);
            errorList.add(format);
        } else {
            try {
                SimpleDateFormat formatDate = new SimpleDateFormat("yyMMdd");
                formatDate.setLenient(false);
                formatDate.parse(date);
            } catch (ParseException e) {
                log.error(format);
                errorList.add(format);
            }
        }
    }

    private static void validateCurrency(String currency, String type) {
        String format = String.format("Tag %s without Currency", type);
        if (null == currency) {
            log.error(format);
            errorList.add(format);
        } else {
            // log.error("VALIDAR MONEDAS");
        }
    }

    private static void validateAmount(String amount, String type) {
        String format = String.format("Tag %s without Amount", type);
        if (null == amount) {
            log.error(format);
            errorList.add(format);
        } else {
            validateLength(amount, "Amount in Tag" + type, 15);
            validateAmountFormat(amount, type);
        }
    }

    private static void validateAmountFormat(String amount, String type) {
        String format = String.format("Tag %s with Invalid Amount format", type);
        Pattern pattern = Pattern.compile("\\d*,\\d{2}$");
        Matcher matcher = pattern.matcher(amount);
        if (matcher.matches()) {
            if (amount.substring(amount.indexOf(",") + 1).length() == 0) {
                log.error(format);
                errorList.add(format);
            }
        } else {
            log.error(format);
            errorList.add(format);
        }
    }

    private static void validateTransactionType(String transactionType){
        String msg = ("Tag61, Transaction Type is invalid");
        if (transactionType.equals("N") || transactionType.equals("F")) {
            // log.error("Validacion CODIGOS SWIFT :"+f61.getIdentificationCode());
        } else if (transactionType.equals("S")) {
            // log.error("Validacion CODIGOS SWIFT :"+f61.getIdentificationCode());
        } else {
            log.error(msg);
            errorList.add(msg);
        }
    }

    private static void validateReferenceForTheAccountOwner(String referenceForTheAccountOwner){
       String msg = ("Tag61, Reference of the Account Owner is missing");
        if (null == referenceForTheAccountOwner) {
            log.error(msg);
            errorList.add(msg);
        } else {
            validateLength(referenceForTheAccountOwner,"61",16);
        }
    }
}
