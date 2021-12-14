package com.ecs.latam.kafka.domain;

import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.ecs.latam.kafka.domain.Util;

@Log4j2
public class ValidationMT940 {

  public boolean flagValidator = true;

  public boolean validateMT(String inMsg) {
    MT940 mt = null;
    try {
      mt = MT940.parse(inMsg);

    } catch (Throwable e) {
      e.getMessage();
      log.error(e.toString());
      flagValidator = false;
    }

    try {
      String sender = mt.getSender();
      if (Util.isEmptyTag(sender)) {
        log.error("MT without sender BIC");
        flagValidator = false;
      }

    } catch (NullPointerException e) {
      e.getMessage();
      log.error("MT without sender BIC: " + e.toString());
      flagValidator = false;
    }

    try {
      String receiver = mt.getReceiver();
      if (Util.isEmptyTag(receiver)) {
        log.error("MT without Receiver BIC");
        flagValidator = false;
      }

    } catch (NullPointerException e) {
      e.getMessage();
      log.error("MT without Receiver BIC: " + e.toString());
      flagValidator = false;
    }

    try {
      String f20 = mt.getField20().getValue();
      if (!Util.isEmptyTag(f20)) {
        if (f20.length() > 16) {
          log.error("Length of Tag20 is more than 16 characters ");
          flagValidator = false;
        } else if (f20.startsWith("/") || f20.endsWith("/") || f20.contains("//")) {
          log.error(
              "This field must not start or end with a slash and must "
                  + "not contain two consecutive slashes '/'");
          flagValidator = false;
        }
      } else {
        log.error("MT without tag20");
        flagValidator = false;
      }
    } catch (NullPointerException e) {
      e.getMessage();
      log.error("MT without tag20: " + e.toString());
      flagValidator = false;
    }


    try {
      String f21 = mt.getField21().getValue();
      if (!Util.isEmptyTag(f21)) {
        if (f21.length() > 16) {
          log.error("Length of Tag21 is more than 16 characters");
        } else if (f21.startsWith("/") || f21.endsWith("/") || f21.contains("//")) {
          log.error(
              "This field must not start or end with a slash and must "
                  + "not contain two consecutive slashes'/'");
        }
      }
    } catch (NullPointerException e) {
      log.debug("OPTIONAL: MT without tag21: " + e.toString());
    }

    try {
      String f25 = mt.getField25().getValue();
      if (!Util.isEmptyTag(f25)) {
        if (f25.length() > 35) {
          log.error("Length of Tag25 is more than 35 characters");
          flagValidator = false;
        }
      } else {
        log.error("Tag25 is missing");
        flagValidator = false;
      }
    } catch (NullPointerException e) {
      log.error("MT without tag25: " + e.toString());
      flagValidator = false;
    }

    try {

      String f28C = mt.getField28C().getValue();


      if (!Util.isEmptyTag(f28C)) {

        try {
          mt.getField28C().getStatementNumber().isEmpty();
          mt.getField28C().getSequenceNumber().isEmpty();
          if(mt.getField28C().getStatementNumber().trim().length()>5){
            log.error("Tag28C wit Statement Number invalid length");
            flagValidator = false;
          }
          if(mt.getField28C().getSequenceNumber().trim().length()>5){
            log.error("Tag28C wit Sequence Number invalid length");
            flagValidator = false;
          }

        } catch (NullPointerException e) {
          log.error("Tag28C without Statement Number or Sequence Number: " + e.toString());
          flagValidator = false;
        }

      } else {
        log.error("MT without Tag28C");
        flagValidator = false;
      }
    } catch (NullPointerException e) {
      log.error("MT without tag28C: " + e.toString());
      flagValidator = false;
    }

    try {
        Field60F f60F = mt.getField60F();
        Field60M f60m = mt.getField60M();

        if (Util.isEmptyTag(f60F.toString())) {

            if (null == f60F.getDCMark()) {
                log.error("tag60F without D/C Mark");
                flagValidator = false;
            } else if (!(f60F.getDCMark().equals("D")
                    || f60F.getDCMark().equals("C")
                    || f60F.getDCMark().substring(0, 1).equals("C")
                    || f60F.getDCMark().substring(0, 1).equals("D"))) {
                log.error("tag60F with D/C Mark invalid");
                flagValidator = false;
            }

            if (null == f60F.getDate()) {
                log.error("tag60F without Date");
                flagValidator = false;
            } else {
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(f60F.getDate());

                } catch (ParseException e) {

                    log.error("Tag60F with Date format invalid");
                    flagValidator = false;
                }
            }

            if (null == f60F.getCurrency()) {
                log.error("tag60F without Currency");
                flagValidator = false;
            } else {
                // log.error("VALIDAR MONEDAS");

            }

            try {
                if (null == f60F.getAmount()) {
                    log.error("tag60F without Amount");
                    flagValidator = false;
                } else if (!(f60F.getAmount().length() > 15)) {
                    if (!f60F.getAmount().contains(",")) {
                        log.error("tag60F The decimal comma ',' is mandatory");
                        flagValidator = false;
                    } else {
                        BigInteger valor = new BigInteger("0");
                        try {
                            valor = new BigInteger(f60F.getAmount().substring(0, f60F.getAmount().indexOf(",")));
                        } catch (StringIndexOutOfBoundsException e) {
                            log.error("tag60F, The integer part of Amount must contain at least one digit");
                            flagValidator = false;
                        }
                        if (f60F.getAmount().substring(f60F.getAmount().indexOf(",") + 1).length() == 0) {
                            log.error("tag60F, Invalid Amount format");
                            flagValidator = false;
                        } else if (f60F.getAmount().substring(f60F.getAmount().indexOf(",") + 1).length() > 2) {
                            log.error(
                                    "tag60F, The number of digits following the comma must not exceed the "
                                            + "maximum number allowed (2)");
                            flagValidator = false;
                        }
                    }

                } else {
                    log.error("tag60F, Amount length invalid");
                    flagValidator = false;
                }

            } catch (NumberFormatException e) {
                log.error("tag60F, Invalid Amount format only numbers");
                flagValidator = false;
            }

        } else if (Util.isEmptyTag(f60m.toString())) {
            if (null == f60m.getDCMark()) {
                log.error("tag60M without D/C Mark");
                flagValidator = false;
            } else if (!(f60m.getDCMark().equals("D")
                    || f60m.getDCMark().equals("C")
                    || f60m.getDCMark().substring(0, 1).equals("C")
                    || f60m.getDCMark().substring(0, 1).equals("D"))) {
                log.error("tag60M with D/C Mark invalid");
                flagValidator = false;
            }

            if (null == f60m.getDate()) {
                log.error("tag60M without Date");
                flagValidator = false;
            } else {
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(f60m.getDate());

                } catch (ParseException e) {

                    log.error("Tag60M with Date format invalid");
                    flagValidator = false;
                }
            }

            if (null == f60m.getCurrency()) {
                log.error("tag60M without Currency");
                flagValidator = false;
            } else {
                // log.error("VALIDAR MONEDAS");

            }

            try {
                if (null == f60m.getAmount()) {
                    log.error("tag60M without Amount");
                    flagValidator = false;
                } else if (!(f60m.getAmount().length() > 15)) {
                    if (!f60m.getAmount().contains(",")) {
                        log.error("tag60M The decimal comma ',' is mandatory");
                        flagValidator = false;
                    } else {
                        BigInteger valor = new BigInteger("0");
                        try {
                            valor = new BigInteger(f60m.getAmount().substring(0, f60m.getAmount().indexOf(",")));
                        } catch (StringIndexOutOfBoundsException e) {
                            log.error("tag60M, The integer part of Amount must contain at least one digit");
                            flagValidator = false;
                        }
                        if (f60m.getAmount().substring(f60m.getAmount().indexOf(",") + 1).length() == 0) {
                            log.error("tag60M, Invalid Amount format");
                            flagValidator = false;
                        } else if (f60m.getAmount().substring(f60m.getAmount().indexOf(",") + 1).length() > 2) {
                            log.error(
                                    "tag60M, The number of digits following the comma must not exceed the "
                                            + "maximum number allowed (2)");
                            flagValidator = false;
                        }
                    }

                } else {
                    log.error("tag60M, Amount length invalid");
                    flagValidator = false;
                }
            } catch (NumberFormatException e) {
                log.error("tag60M, Invalid Amount format only numbers");
                flagValidator = false;
            }
        }
    }catch (NullPointerException e){
        log.error("tag60F or tag60M, are missing");
        flagValidator = false;
      }

    int n61 = mt.getField61().size();
    int n86 = mt.getField86().size();
    // log.error(n61+", "+n86);
    if (n61 < n86) {
      log.error("tag61 is missing");
      flagValidator = false;
    } else {

      for (int y = 0; y < n61; y++) {
        Field61 f61 = mt.getField61().get(y);

        if (null == f61.getValueDate()) {
          log.error("tag61 without Value Date");
          flagValidator = false;
        } else {
          try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

            formatoFecha.setLenient(false);

            formatoFecha.parse(f61.getValueDate());

          } catch (ParseException e) {

            log.error("Tag61 with  Value Date format invalid");
            flagValidator = false;
          }
        }

        if (null == f61.getDCMark()) {
          log.error("tag61 without D/C Mark");
          flagValidator = false;
        } else if (!(f61.getDCMark().equals("D")
            || f61.getDCMark().equals("C")
            || f61.getDCMark().substring(0, 1).equals("C")
            || f61.getDCMark().substring(0, 1).equals("D"))) {
          log.error("tag61 with D/C Mark invalid");
          flagValidator = false;
        }
        // log.error(f61.getAmount());

        try {
          if (Double.parseDouble(f61.getAmount().replace(",", "")) == 0) {
            log.error("tag61 without Amount");
            flagValidator = false;
          } else if (!(f61.getAmount().length() > 15)) {
            if (!f61.getAmount().contains(",")) {
              log.error("tag61 Invalid Amount format");
              flagValidator = false;
            } else {
              BigInteger valor = new BigInteger("0");
              try {
                valor = new BigInteger(f61.getAmount().substring(0, f61.getAmount().indexOf(",")));
              } catch (StringIndexOutOfBoundsException e) {
                log.error("tag61, The integer part of Amount must contain at least one digit");
                flagValidator = false;
              }
              if (f61.getAmount().substring(f61.getAmount().indexOf(",") + 1).length() == 0) {
                log.error("tag61, Invalid Amount format");
                flagValidator = false;
              } else if (f61.getAmount().substring(f61.getAmount().indexOf(",") + 1).length() > 2) {
                log.error(
                    "tag61, The number of digits following the comma must not exceed the "
                        + "maximum number allowed (2)");
                flagValidator = false;
              }
            }
            String transType = f61.getTransactionType();
            if (transType.equals("N") || transType.equals("F")) {
              // log.error("Validacion CODIGOS SWIFT :"+f61.getIdentificationCode());
            } else if (transType.equals("S")) {
              // log.error("Validacion CODIGOS SWIFT :"+f61.getIdentificationCode());
            } else {
              log.error("tag61, Transaction Type is invalid");
              flagValidator = false;
            }

          } else {
            log.error("tag61, Amount length invalid");
            flagValidator = false;
          }
        } catch (NumberFormatException e) {
          log.error("tag61, Invalid Amount format only numbers");
          flagValidator = false;
        }

        if (null != f61.getReferenceForTheAccountOwner()) {
          if (f61.getReferenceForTheAccountOwner().length() > 16) {
            log.error("tag61, length of Reference of the Account Owner is invalid");
            flagValidator = false;
          }
        } else {
          log.error("tag61, Reference of the Account Owner is missing");
          flagValidator = false;
        }
      }
    }

    Field62F f62F = mt.getField62F();
    Field62M f62m = mt.getField62M();

    if (null != f62F) {

      if (null == f62F.getDCMark()) {
        log.error("Tag62F without D/C Mark");
        flagValidator = false;
      } else if (!(f62F.getDCMark().equals("D")
          || f62F.getDCMark().equals("C")
          || f62F.getDCMark().substring(0, 1).equals("C")
          || f62F.getDCMark().substring(0, 1).equals("D"))) {
        log.error("Tag62F with D/C Mark invalid");
        flagValidator = false;
      }

      if (null == f62F.getDate()) {
        log.error("Tag62F without Date");
        flagValidator = false;
      } else {
        try {

          SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

          formatoFecha.setLenient(false);

          formatoFecha.parse(f62F.getDate());

        } catch (ParseException e) {

          log.error("Tag62F with Date format invalid");
          flagValidator = false;
        }
      }

      if (null == f62F.getCurrency()) {
        log.error("Tag62F without Currency");
        flagValidator = false;
      } else {
        // log.error("VALIDAR MONEDAS");

      }

      try {
        if (null == f62F.getAmount()) {
          log.error("Tag62F without Amount");
          flagValidator = false;
        } else if (!(f62F.getAmount().length() > 15)) {
          if (!f62F.getAmount().contains(",")) {
            log.error("Tag62F The decimal comma ',' is mandatory");
            flagValidator = false;
          } else {
            BigInteger valor = new BigInteger("0");
            try {
              valor = new BigInteger(f62F.getAmount().substring(0, f62F.getAmount().indexOf(",")));
            } catch (StringIndexOutOfBoundsException e) {
              log.error("Tag62F, The integer part of Amount must contain at least one digit");
              flagValidator = false;
            }
            if (f62F.getAmount().substring(f62F.getAmount().indexOf(",") + 1).length() == 0) {
              log.error("Tag62F, Invalid Amount format");
              flagValidator = false;
            } else if (f62F.getAmount().substring(f62F.getAmount().indexOf(",") + 1).length() > 2) {
              log.error(
                  "Tag62F, The number of digits following the comma must not exceed the "
                      + "maximum number allowed (2)");
              flagValidator = false;
            }
          }

        } else {
          log.error("Tag62F, Invalid Amount length");
          flagValidator = false;
        }
      } catch (NumberFormatException e) {
        log.error("Tag62F, Invalid Amount format only numbers");
        flagValidator = false;
      }

    } else if (null != f62m) {
      if (null == f62m.getDCMark()) {
        log.error("tag62M without D/C Mark");
        flagValidator = false;
      } else if (!(f62m.getDCMark().equals("D")
          || f62m.getDCMark().equals("C")
          || f62m.getDCMark().substring(0, 1).equals("C")
          || f62m.getDCMark().substring(0, 1).equals("D"))) {
        log.error("tag62M with D/C Mark invalid");
        flagValidator = false;
      }

      if (null == f62m.getDate()) {
        log.error("tag62M without Date");
        flagValidator = false;
      } else {
        try {

          SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

          formatoFecha.setLenient(false);

          formatoFecha.parse(f62m.getDate());

        } catch (ParseException e) {

          log.error("tag62M with Date format invalid");
          flagValidator = false;
        }
      }

      if (null == f62m.getCurrency()) {
        log.error("tag62M without Currency");
        flagValidator = false;
      } else {
        // log.error("VALIDAR MONEDAS");

      }

      try {
        if (null == f62m.getAmount()) {
          log.error("tag62M without Amount");
          flagValidator = false;
        } else if (!(f62m.getAmount().length() > 15)) {
          if (!f62m.getAmount().contains(",")) {
            log.error("tag62M The decimal comma ',' is mandatory");
            flagValidator = false;
          } else {
            BigInteger valor = new BigInteger("0");
            try {
              valor = new BigInteger(f62m.getAmount().substring(0, f62m.getAmount().indexOf(",")));
            } catch (StringIndexOutOfBoundsException e) {
              log.error("tag62M, The integer part of Amount must contain at least one digit");
              flagValidator = false;
            }
            if (f62m.getAmount().substring(f62m.getAmount().indexOf(",") + 1).length() == 0) {
              log.error("tag62M, Invalid Amount format");
              flagValidator = false;
            } else if (f62m.getAmount().substring(f62m.getAmount().indexOf(",") + 1).length() > 2) {
              log.error(
                  "tag62M, The number of digits following the comma must not exceed the "
                      + "maximum number allowed (2)");
              flagValidator = false;
            }
          }

        } else {
          log.error("tag62M, Invalid Amount length");
          flagValidator = false;
        }
      } catch (NumberFormatException e) {
        log.error("tag60M, Invalid Amount format only numbers");
        flagValidator = false;
      }
    } else {
      log.error("tag62F or tag62M, are missing");
      flagValidator = false;
    }

    return flagValidator;
  }
}
