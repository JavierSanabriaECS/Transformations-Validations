package com.ecs.latam.kafka.domain;


import com.prowidesoftware.swift.model.field.Field60F;
import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.field.Field62F;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import com.prowidesoftware.swift.model.mx.BusinessAppHdrV02;
import com.prowidesoftware.swift.model.mx.MxCamt05300108;
import com.prowidesoftware.swift.model.mx.MxWriteConfiguration;
import com.prowidesoftware.swift.model.mx.dic.*;
import lombok.extern.log4j.Log4j2;


import javax.xml.datatype.DatatypeConfigurationException;
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
public class TransformMT940toXML {

    public static List<String> separadorMT940 (String msg) throws InvalidMTException {

        List<String> mensaje = new ArrayList<>();
        if(msg.contains("$")){

            String[] inmsg = msg.split("\\$");
            ArrayList<String> xmlListsOrgn = new ArrayList<>();
            for (int i = 0; i < inmsg.length; i++) {
                String message1 = inmsg[i] ;
                xmlListsOrgn.add(message1);

            }
            boolean resultVal = true;
            for(String valString:xmlListsOrgn){
                ValidationMT940.validateMT(valString);

            }

            if(resultVal){
                if(xmlListsOrgn.size()>1){
                    MT940 valMsg = MT940.parse(xmlListsOrgn.get(0));
                    String tag25inicio = valMsg.getField25().getValue();
                    MT940 valMsg2 = MT940.parse(xmlListsOrgn.get(1));
                    String tag25final = valMsg2.getField25().getValue();

                    if(tag25inicio.equals(tag25final)){
                        mensaje.add(transformacionPaginas(xmlListsOrgn));
                    }else{
                        mensaje = transformacionUnoaUno(xmlListsOrgn);
                    }
                }else{
                    mensaje= transformacionUnoaUno(xmlListsOrgn);
                }
            }



        }else{
            String[] inmsg = msg.split("-}");
            ArrayList<String> xmlListsOrgn = new ArrayList<>();
            for (int i = 0; i < inmsg.length; i++) {
                String message1 = inmsg[i]+"-}" ;
                xmlListsOrgn.add(message1);
            }
            boolean resultVal = true;
            for(String valString:xmlListsOrgn){
                ValidationMT940.validateMT(valString);
            }

            if(resultVal){
                if(xmlListsOrgn.size()>1){
                    MT940 valMsg = MT940.parse(xmlListsOrgn.get(0));
                    String tag25inicio = valMsg.getField25().getValue();
                    MT940 valMsg2 = MT940.parse(xmlListsOrgn.get(1));
                    String tag25final = valMsg2.getField25().getValue();

                    if(tag25inicio.equals(tag25final)){
                        mensaje.add(transformacionPaginas(xmlListsOrgn));
                    }else{
                        mensaje = transformacionUnoaUno(xmlListsOrgn);
                    }
                }else{
                    mensaje = transformacionUnoaUno(xmlListsOrgn);
                }
            }




        }
        //log.debug(mensaje);
        return mensaje;


    }


    private static String transformacionPaginas(ArrayList<String> msg) throws InvalidMTException {
        String outMsg="";
        try{

            ArrayList<String> xmlListsOrgn = msg;

            MT940 mt = MT940.parse(xmlListsOrgn.get(0));

            Field60F f60f = mt.getField60F();

            MxCamt05300108 mx = new MxCamt05300108();

            MxWriteConfiguration valor = new MxWriteConfiguration();
            valor.documentPrefix = null;

            DateFormat format = new SimpleDateFormat("yyMMdd");

            GregorianCalendar gcal = new GregorianCalendar();
            XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

            mx.document();

            BusinessAppHdrV02 hedapp = new BusinessAppHdrV02();

            Party44Choice part = new Party44Choice();
            BranchAndFinancialInstitutionIdentification6 brafin = new BranchAndFinancialInstitutionIdentification6();
            brafin.setFinInstnId(new FinancialInstitutionIdentification18().setBICFI(mt.getSender()));
            part.setFIId(brafin);
            hedapp.setFr(part);
            brafin.setFinInstnId(new FinancialInstitutionIdentification18().setBICFI(mt.getReceiver()));
            part.setFIId(brafin);
            hedapp.setTo(part);
            hedapp.setBizMsgIdr(mt.getField20().getValue());
            hedapp.setMsgDefIdr("camt.053.001.08");
            hedapp.setCreDt(xgcal.normalize());

            mx.setAppHdr(hedapp);

            mx.setBkToCstmrStmt(new BankToCustomerStatementV08().setGrpHdr(new GroupHeader81()));
            mx.getBkToCstmrStmt().getGrpHdr().setMsgId(mt.getField20().getValue());
            mx.getBkToCstmrStmt().getGrpHdr().setCreDtTm(xgcal.normalize());
            AccountStatement9 stmt = new AccountStatement9();

            stmt = new AccountStatement9();
            stmt.setId(mt.getField20().getValue());

            stmt.setStmtPgntn(new Pagination1().setPgNb(mt.getField28C().getSequenceNumber()));

            CashAccount39 ca39 = new CashAccount39();
            AccountIdentification4Choice ai4c = new AccountIdentification4Choice();
            ca39.setId(ai4c.setOthr(new GenericAccountIdentification1().setId(mt.getField25().getValue())));
            ca39.setCcy(mt.getField60F().getCurrency());
            stmt.setAcct(ca39);
            CashBalance8 cb8 = new CashBalance8();
            cb8.setTp(new BalanceType13().setCdOrPrtry(new BalanceType10Choice().setCd("OPBD")));


            ActiveOrHistoricCurrencyAndAmount amt = new ActiveOrHistoricCurrencyAndAmount();
            BigDecimal valorTot;
            valorTot=new BigDecimal(f60f.getAmount().replace(",",".")).setScale(2, RoundingMode.UNNECESSARY);
            amt.setCcy(f60f.getCurrency());
            cb8.setAmt(amt.setValue(valorTot));

            String dcmarck;
            dcmarck = mt.getField60F().getDCMark();

            if(f60f.getAmount().equals("0")||f60f.getAmount().equals(null)) {
                cb8.setCdtDbtInd(CreditDebitCode.CRDT);

            }else{
                if (dcmarck.equals("C")) {
                    cb8.setCdtDbtInd(CreditDebitCode.CRDT);
                } else if (dcmarck.equals("D")) {
                    cb8.setCdtDbtInd(CreditDebitCode.DBIT);
                }
            }
            DateAndDateTime2Choice dt = new DateAndDateTime2Choice();

            Date date = format.parse(f60f.getDate());

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

            cb8.setDt(dt.setDtTm(xmlGregCal.normalize()));

            stmt.addBal(cb8);


            MT940 mt2 = MT940.parse(xmlListsOrgn.get(xmlListsOrgn.size()-1));

            CashBalance8 cb8two = new CashBalance8();
            cb8two.setTp(new BalanceType13().setCdOrPrtry(new BalanceType10Choice().setCd("CLBD")));


            ActiveOrHistoricCurrencyAndAmount amttwo = new ActiveOrHistoricCurrencyAndAmount();
            BigDecimal valorTottwo;
            valorTottwo=new BigDecimal(mt2.getField62F().getAmount().replace(",",".")).setScale(2, RoundingMode.UNNECESSARY);
            amttwo.setCcy(mt2.getField62F().getCurrency());
            String currenTot = mt2.getField62F().getCurrency();
            cb8two.setAmt(amttwo.setValue(valorTottwo));

            String dcmarcktwo;
            dcmarcktwo = mt2.getField62F().getDCMark();

            if(mt2.getField62F().getAmount().equals("0")||mt2.getField62F().getAmount().equals(null)) {
                cb8two.setCdtDbtInd(CreditDebitCode.CRDT);

            }else{
                if (dcmarcktwo.equals("C")) {
                    cb8two.setCdtDbtInd(CreditDebitCode.CRDT);
                } else if (dcmarcktwo.equals("D")) {
                    cb8two.setCdtDbtInd(CreditDebitCode.DBIT);
                }
            }
            DateAndDateTime2Choice dt2 = new DateAndDateTime2Choice();

            Date date2 = format.parse(mt2.getField62F().getDate());
            GregorianCalendar cal2 = new GregorianCalendar();
            cal.setTime(date2);
            XMLGregorianCalendar xmlGregCal2 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
            cb8two.setDt(dt2.setDtTm(xmlGregCal2.normalize()));

            stmt.addBal(cb8two);

            for(String trans : xmlListsOrgn){

                MT940 mttras = new MT940(trans);
                List<Field61> f61 = mttras.getField61();

                for(int j=0;j<f61.size();j++){
                    ReportEntry10 entry = new ReportEntry10();
                    entry.setNtryRef(f61.get(j).getIdentificationCode());
                    ActiveOrHistoricCurrencyAndAmount amtntry = new ActiveOrHistoricCurrencyAndAmount();
                    BigDecimal valorntry;
                    valorntry=new BigDecimal(f61.get(j).getAmount().replace(",",".")).setScale(2, RoundingMode.UNNECESSARY);
                    amtntry.setCcy(currenTot);
                    entry.setAmt(amtntry.setValue(valorntry));
                    String dcmarcktrans;
                    dcmarcktrans = f61.get(j).getDCMark();

                    if (dcmarcktrans.equals("C")) {
                        entry.setCdtDbtInd(CreditDebitCode.CRDT);
                    } else if (dcmarcktrans.equals("D")) {
                        entry.setCdtDbtInd(CreditDebitCode.DBIT);
                    }
                    DateAndDateTime2Choice dt3 = new DateAndDateTime2Choice();

                    Date date3 = format.parse(f61.get(j).getValueDate());
                    GregorianCalendar cal3 = new GregorianCalendar();
                    cal3.setTime(date3);
                    XMLGregorianCalendar xmlGregCal3 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal3);

                    entry.setValDt(dt3.setDtTm(xmlGregCal3.normalize()));

                    BankTransactionCodeStructure4 bank4 = new BankTransactionCodeStructure4();

                    bank4.setDomn(new BankTransactionCodeStructure5().setCd(f61.get(j).getReferenceForTheAccountOwner()));

                    entry.setBkTxCd(bank4);

                    stmt.addNtry(entry);

                }
            }

            mx.getBkToCstmrStmt().addStmt(stmt);

            log.debug(mx.message(valor));

            outMsg =mx.message(valor);


        }catch (Exception e){
            log.error("Error in transformation: "+e.getMessage());
            throw new InvalidMTException(e);
        }
        return outMsg;


    }

    private static List<String> transformacionUnoaUno(ArrayList<String> msg) throws InvalidMTException {

        List<String> camtUnoaUno = new ArrayList<>();

        try{
            ArrayList<String> xmlListsOrgn = msg;

            for(String transBig : xmlListsOrgn){
                MT940 mt = MT940.parse(transBig);


                Field60F f60f = mt.getField60F();

                MxCamt05300108 mx = new MxCamt05300108();

                MxWriteConfiguration valor = new MxWriteConfiguration();
                valor.documentPrefix = null;

                DateFormat format = new SimpleDateFormat("yyMMdd");

                GregorianCalendar gcal = new GregorianCalendar();
                XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
                mx.document();

                BusinessAppHdrV02 hedapp = new BusinessAppHdrV02();

                Party44Choice part = new Party44Choice();
                BranchAndFinancialInstitutionIdentification6 brafin = new BranchAndFinancialInstitutionIdentification6();
                brafin.setFinInstnId(new FinancialInstitutionIdentification18().setBICFI(mt.getSender()));
                part.setFIId(brafin);
                hedapp.setFr(part);
                brafin = new BranchAndFinancialInstitutionIdentification6();
                brafin.setFinInstnId(new FinancialInstitutionIdentification18().setBICFI(mt.getReceiver()));
                part = new Party44Choice();
                part.setFIId(brafin);
                hedapp.setTo(part);
                hedapp.setBizMsgIdr(mt.getField20().getValue());
                hedapp.setMsgDefIdr("camt.053.001.08");
                hedapp.setCreDt(xgcal.normalize());

                mx.setAppHdr(hedapp);

                mx.setBkToCstmrStmt(new BankToCustomerStatementV08().setGrpHdr(new GroupHeader81()));
                mx.getBkToCstmrStmt().getGrpHdr().setMsgId(mt.getField20().getValue());
                mx.getBkToCstmrStmt().getGrpHdr().setCreDtTm(xgcal.normalize());
                AccountStatement9 stmt;

                stmt = new AccountStatement9();
                stmt.setId(mt.getField20().getValue());
                //log.debug(stmt.getId());
                //stmt.setCreDtTm(xgcal.normalize());
                stmt.setStmtPgntn(new Pagination1().setPgNb(mt.getField28C().getSequenceNumber()));
                stmt.getStmtPgntn().setLastPgInd(true);
                CashAccount39 ca39 = new CashAccount39();
                AccountIdentification4Choice ai4c = new AccountIdentification4Choice();
                ca39.setId(ai4c.setOthr(new GenericAccountIdentification1().setId(mt.getField25().getValue())));
                ca39.setCcy(mt.getField60F().getCurrency());
                stmt.setAcct(ca39);
                CashBalance8 cb8 = new CashBalance8();
                cb8.setTp(new BalanceType13().setCdOrPrtry(new BalanceType10Choice().setCd("OPBD")));

                ActiveOrHistoricCurrencyAndAmount amt = new ActiveOrHistoricCurrencyAndAmount();
                BigDecimal valorTot;
                valorTot=new BigDecimal(f60f.getAmount().replace(",",".")).setScale(2, RoundingMode.UNNECESSARY);
                amt.setCcy(f60f.getCurrency());
                cb8.setAmt(amt.setValue(valorTot));

                String dcmarck;
                dcmarck = mt.getField60F().getDCMark();

                if(f60f.getAmount().equals("0")||f60f.getAmount().isBlank()) {
                    cb8.setCdtDbtInd(CreditDebitCode.CRDT);

                }else{
                    if (dcmarck.equals("C")) {
                        cb8.setCdtDbtInd(CreditDebitCode.CRDT);
                    } else if (dcmarck.equals("D")) {
                        cb8.setCdtDbtInd(CreditDebitCode.DBIT);
                    }
                }
                DateAndDateTime2Choice dt = new DateAndDateTime2Choice();

                Date date = format.parse(f60f.getDate());

                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(date);

                XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

                cb8.setDt(dt.setDtTm(xmlGregCal.normalize()));

                stmt.addBal(cb8);

                //LAST STATEMENT

                Field62F f62F = mt.getField62F();

                CashBalance8 cb8two = new CashBalance8();
                cb8two.setTp(new BalanceType13().setCdOrPrtry(new BalanceType10Choice().setCd("CLBD")));

                ActiveOrHistoricCurrencyAndAmount amttwo = new ActiveOrHistoricCurrencyAndAmount();
                BigDecimal valorTottwo;
                valorTottwo=new BigDecimal(f62F.getAmount().replace(",",".")).setScale(2, RoundingMode.UNNECESSARY);
                amttwo.setCcy(f62F.getCurrency());
                String currenTot = f62F.getCurrency();
                cb8two.setAmt(amttwo.setValue(valorTottwo));

                String dcmarcktwo;
                dcmarcktwo = f62F.getDCMark();

                if(f62F.getAmount().equals("0")||f62F.getAmount().isBlank()) {
                    cb8two.setCdtDbtInd(CreditDebitCode.CRDT);

                }else{
                    if (dcmarcktwo.equals("C")) {
                        cb8two.setCdtDbtInd(CreditDebitCode.CRDT);
                    } else if (dcmarcktwo.equals("D")) {
                        cb8two.setCdtDbtInd(CreditDebitCode.DBIT);
                    }
                }
                DateAndDateTime2Choice dt2 = new DateAndDateTime2Choice();

                Date date2 = format.parse(f62F.getDate());
                GregorianCalendar cal2 = new GregorianCalendar();
                cal2.setTime(date2);
                XMLGregorianCalendar xmlGregCal2 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
                cb8two.setDt(dt2.setDtTm(xmlGregCal2.normalize()));

                stmt.addBal(cb8two);


                List<Field61> f61 = mt.getField61();

                for(int j=0;j<f61.size();j++){
                    ReportEntry10 entry = new ReportEntry10();
                    entry.setNtryRef(f61.get(j).getIdentificationCode());
                    ActiveOrHistoricCurrencyAndAmount amtntry = new ActiveOrHistoricCurrencyAndAmount();
                    BigDecimal valorntry;
                    valorntry=new BigDecimal(f61.get(j).getAmount().replace(",",".")).setScale(2, RoundingMode.UNNECESSARY);
                    amtntry.setCcy(currenTot);
                    entry.setAmt(amtntry.setValue(valorntry));
                    String dcmarcktrans;
                    dcmarcktrans = f61.get(j).getDCMark();

                    if (dcmarcktrans.equals("C")) {
                        entry.setCdtDbtInd(CreditDebitCode.CRDT);
                    } else if (dcmarcktrans.equals("D")) {
                        entry.setCdtDbtInd(CreditDebitCode.DBIT);
                    }
                    DateAndDateTime2Choice dt3 = new DateAndDateTime2Choice();

                    Date date3 = format.parse(f61.get(j).getValueDate());
                    GregorianCalendar cal3 = new GregorianCalendar();
                    cal3.setTime(date3);
                    XMLGregorianCalendar xmlGregCal3 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal3);

                    entry.setValDt(dt3.setDtTm(xmlGregCal3.normalize()));

                    BankTransactionCodeStructure4 bank4 = new BankTransactionCodeStructure4();

                    bank4.setDomn(new BankTransactionCodeStructure5().setCd(f61.get(j).getReferenceForTheAccountOwner()));

                    entry.setBkTxCd(bank4);

                    stmt.addNtry(entry);

                }

                mx.getBkToCstmrStmt().addStmt(stmt);

                log.debug(mx.message(valor));

                camtUnoaUno.add(mx.message(valor));

            }

        }catch (Exception e){
            log.error("Error in transformation: "+e.getMessage());
            throw new InvalidMTException(e);

        }
           return camtUnoaUno;
    }

}
