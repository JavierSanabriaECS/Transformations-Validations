package com.ecs.latam.kafka.domain;


import com.prowidesoftware.swift.model.mx.BusinessAppHdrV02;
import com.prowidesoftware.swift.model.mx.MxCamt05300108;
import com.prowidesoftware.swift.model.mx.MxWriteConfiguration;
import com.prowidesoftware.swift.model.mx.dic.*;
import lombok.extern.log4j.Log4j2;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@Log4j2
public class TransformMCtoXML {

    public static List<String> transformationMCtoCAMT(String cabecero, String detalles) throws InvalidMCException {
        List<String> outMC = new ArrayList<>();
        ValidationMulticash.validateMC(cabecero, detalles);
        try {
            String[] inMsgC = cabecero.split(";");

            MxCamt05300108 mx = new MxCamt05300108();

            MxWriteConfiguration valor = new MxWriteConfiguration();
            valor.documentPrefix = null;

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            GregorianCalendar gcal = new GregorianCalendar();

            mx.document();

            BusinessAppHdrV02 hedapp = new BusinessAppHdrV02();

            Party44Choice part = new Party44Choice();
            BranchAndFinancialInstitutionIdentification6 brafin = new BranchAndFinancialInstitutionIdentification6();
            String frmBic = null;
            if (inMsgC[0].equals("13")) {
                frmBic = "GEROCOBB";
            } else {
                log.debug("VALIDACION DE BIC");
            }
            brafin.setFinInstnId(new FinancialInstitutionIdentification18().setBICFI(frmBic));
            part.setFIId(brafin);
            hedapp.setFr(part);
            String toBIC = "ECSNUS30BHX";
            brafin.setFinInstnId(new FinancialInstitutionIdentification18().setBICFI(toBIC));
            part.setFIId(brafin);
            hedapp.setTo(part);
            hedapp.setBizMsgIdr(inMsgC[2]);
            hedapp.setMsgDefIdr("camt.053.001.08");
            Date date1 = format.parse(getdateFormatted(inMsgC[3], "dd.MM.yy", "yyyy-MM-dd"));
            gcal.setTime(date1);
            XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            hedapp.setCreDt(xgcal.normalize());

            mx.setAppHdr(hedapp);

            mx.setBkToCstmrStmt(new BankToCustomerStatementV08().setGrpHdr(new GroupHeader81()));
            mx.getBkToCstmrStmt().getGrpHdr().setMsgId(inMsgC[2]);
            mx.getBkToCstmrStmt().getGrpHdr().setCreDtTm(xgcal.normalize());
            AccountStatement9 stmt;

            stmt = new AccountStatement9();
            stmt.setId(inMsgC[2]);

            //stmt.setStmtPgntn(new Pagination1().setPgNb(mt.getField28C().getSequenceNumber()));

            CashAccount39 ca39 = new CashAccount39();
            AccountIdentification4Choice ai4c = new AccountIdentification4Choice();
            ca39.setId(ai4c.setOthr(new GenericAccountIdentification1().setId(inMsgC[1])));
            ca39.setCcy(inMsgC[4]);
            stmt.setAcct(ca39);
            CashBalance8 cb8 = new CashBalance8();
            cb8.setTp(new BalanceType13().setCdOrPrtry(new BalanceType10Choice().setCd("OPBD")));


            ActiveOrHistoricCurrencyAndAmount amt = new ActiveOrHistoricCurrencyAndAmount();
            BigDecimal valorTot;
            valorTot = new BigDecimal(inMsgC[5].substring(1)).setScale(2, RoundingMode.UNNECESSARY);
            amt.setCcy(inMsgC[4]);
            cb8.setAmt(amt.setValue(valorTot));

            String dcmarck;
            dcmarck = inMsgC[5].substring(0, 1);


            if (dcmarck.equals("+")) {
                cb8.setCdtDbtInd(CreditDebitCode.CRDT);
            } else if (dcmarck.equals("-")) {
                cb8.setCdtDbtInd(CreditDebitCode.DBIT);
            }

            DateAndDateTime2Choice dt = new DateAndDateTime2Choice();

            Date date = format.parse(getdateFormatted(inMsgC[3], "dd.MM.yy", "yyyy-MM-dd"));

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

            cb8.setDt(dt.setDtTm(xmlGregCal.normalize()));

            stmt.addBal(cb8);


            CashBalance8 cb8two = new CashBalance8();
            cb8two.setTp(new BalanceType13().setCdOrPrtry(new BalanceType10Choice().setCd("CLBD")));


            ActiveOrHistoricCurrencyAndAmount amttwo = new ActiveOrHistoricCurrencyAndAmount();
            BigDecimal valorTottwo ;
            valorTottwo = new BigDecimal(inMsgC[8].substring(1)).setScale(2, RoundingMode.UNNECESSARY);
            amttwo.setCcy(inMsgC[4]);
            //String currenTot = mt2.getField62F().getCurrency();
            cb8two.setAmt(amttwo.setValue(valorTottwo));

            String dcmarcktwo;
            dcmarcktwo = inMsgC[8].substring(0, 1);


            if (dcmarcktwo.equals("+")) {
                cb8two.setCdtDbtInd(CreditDebitCode.CRDT);
            } else if (dcmarcktwo.equals("-")) {
                cb8two.setCdtDbtInd(CreditDebitCode.DBIT);
            }

            DateAndDateTime2Choice dt2 = new DateAndDateTime2Choice();

            Date date2 = format.parse(getdateFormatted(inMsgC[3], "dd.MM.yy", "yyyy-MM-dd"));
            GregorianCalendar cal2 = new GregorianCalendar();
            cal.setTime(date2);
            XMLGregorianCalendar xmlGregCal2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
            cb8two.setDt(dt2.setDtTm(xmlGregCal2.normalize()));

            stmt.addBal(cb8two);

            String[] separarDetalles = detalles.trim().split("\r\n");


            ArrayList<String> detalle = new ArrayList<>();
            for (int i = 0; i < separarDetalles.length; i++) {
                String message1 = separarDetalles[i];
                detalle.add(message1);

            }

            for (String trans : detalle) {

                String[] inMsgD = trans.split(";");

                ReportEntry10 entry = new ReportEntry10();
                entry.setNtryRef(inMsgD[2]);
                ActiveOrHistoricCurrencyAndAmount amtntry = new ActiveOrHistoricCurrencyAndAmount();
                BigDecimal valorntry = new BigDecimal("0");
                valorntry = new BigDecimal(inMsgD[10].substring(1)).setScale(2, RoundingMode.UNNECESSARY);
                amtntry.setCcy(inMsgC[4]);
                entry.setAmt(amtntry.setValue(valorntry));
                String dcmarcktrans;
                dcmarcktrans = inMsgD[10].substring(0, 1);

                if (dcmarcktrans.equals("+")) {
                    entry.setCdtDbtInd(CreditDebitCode.CRDT);
                } else if (dcmarcktrans.equals("-")) {
                    entry.setCdtDbtInd(CreditDebitCode.DBIT);
                }
                DateAndDateTime2Choice dt3 = new DateAndDateTime2Choice();

                Date date3 = format.parse(getdateFormatted(inMsgC[3], "dd.MM.yy", "yyyy-MM-dd"));
                GregorianCalendar cal3 = new GregorianCalendar();
                cal3.setTime(date3);
                XMLGregorianCalendar xmlGregCal3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal3);

                entry.setValDt(dt3.setDtTm(xmlGregCal3.normalize()));

                BankTransactionCodeStructure4 bank4 = new BankTransactionCodeStructure4();

                bank4.setDomn(new BankTransactionCodeStructure5().setCd(inMsgD[6]));

                entry.setBkTxCd(bank4);

                stmt.addNtry(entry);
            }

            mx.getBkToCstmrStmt().addStmt(stmt);

            log.debug(mx.message(valor));

            outMC.add(mx.message(valor));


        } catch (Exception e) {
            log.error("Error in transformation: " + e.getMessage());
            throw new InvalidMCException(e);
        }

        return outMC;


    }

    private static String getdateFormatted(String date, String srcFormat, String destFormat) {
        String dest = null;
        try {
            Date dateAct = new SimpleDateFormat(srcFormat).parse(date);
            dest = new SimpleDateFormat(destFormat).format(dateAct);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dest;
    }


}