package com.ecs.latam;

import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import com.prowidesoftware.swift.model.mx.dic.SystemStatusRequestOrNotificationV01;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationMT940 {
    public boolean flagValidator =true;

    public boolean validarMT(String inMsg){
        MT940 mt = null;
        try {
            mt = MT940.parse(inMsg);

        }catch (Throwable e){
            e.getMessage();
            System.out.println(e.toString());
            flagValidator = false;

        }

        try{
            String sender = mt.getSender();
            if(sender.trim().equals("")||sender.isEmpty()){
                System.out.println("MT without sender BIC");
                flagValidator = false;
            }

        }catch (NullPointerException e){
            e.getMessage();
            System.out.println("MT without sender BIC: "+e.toString());
            flagValidator = false;
        }

        try{
            String receiver = mt.getReceiver();
            if(receiver.trim().equals("")||receiver.isEmpty()){
                System.out.println("MT without Receiver BIC");
                flagValidator = false;
            }

        }catch (NullPointerException e){
            e.getMessage();
            System.out.println("MT without Receiver BIC: "+e.toString());
            flagValidator = false;
        }


        try {
            String f20 =mt.getField20().getValue();
            if(!f20.trim().equals("")){
                if(f20.length()>16){
                    System.out.println("Length of Tag20 is more than 16 characters ");
                    flagValidator = false;
                }else if(f20.startsWith("/")||f20.endsWith("/")||f20.contains("//")){
                    System.out.println("This field must not start or end with a slash and must " +
                            "not contain two consecutive slashes '/'");
                    flagValidator = false;
                }
            }else{
                System.out.println("MT without tag20");
                flagValidator = false;
            }
        }catch (NullPointerException e){
            e.getMessage();
            System.out.println("MT without tag20: "+e.toString());
            flagValidator = false;
        }

        String f21 = null;
        try {
            f21 =mt.getField21().getValue();
            if(!f21.isEmpty()){
                if(f21.length()>16){
                    System.out.println("Length of Tag21 is more than 16 characters");
                }else if(f21.startsWith("/")||f21.endsWith("/")||f21.contains("//")){
                    System.out.println("This field must not start or end with a slash and must " +
                            "not contain two consecutive slashes'/'");
                }
            }
        }catch (NullPointerException e){
            System.out.println("OPTIONAL: MT without tag21: "+e.toString());
        }




        try {
            String f25 = null;
            f25 =mt.getField25().getValue();
            if(!f25.isEmpty()||!f25.trim().equals("")){
                if(f25.length()>35){
                    System.out.println("Length of Tag25 is more than 35 characters");
                    flagValidator = false;
                }
            }else{
                System.out.println("Tag25 is missing");
                flagValidator = false;
            }
        }catch (NullPointerException e){
            System.out.println("MT without tag25: "+e.toString());
            flagValidator = false;

        }

        try {
            String f28C = null;
            f28C =mt.getField28C().getValue();

            if(!f28C.isEmpty()||!f28C.trim().equals("")){

                try{
                    mt.getField28C().getStatementNumber().isEmpty();
                    mt.getField28C().getSequenceNumber().isEmpty();


                }catch (NullPointerException e){
                    System.out.println("Tag28C without Statement Number or Sequence Number: "+e.toString());
                    flagValidator = false;
                }

            }else{
                System.out.println("Tag28C without Pagination Number");
                flagValidator = false;
            }
        }catch (NullPointerException e){
            System.out.println("MT without tag28C: "+e.toString());

            flagValidator = false;

        }



        Field60F f60F =mt.getField60F();
        Field60M f60m = mt.getField60M();


        if(null !=f60F){

            if(null==f60F.getDCMark()){
                System.out.println("tag60F without D/C Mark");
                flagValidator = false;
            }else if(!(f60F.getDCMark().equals("D")||f60F.getDCMark().equals("C")||f60F.getDCMark().substring(0,1).equals("C")||f60F.getDCMark().substring(0,1).equals("D"))){
                System.out.println("tag60F with D/C Mark invalid");
                flagValidator = false;
            }



            if(null==f60F.getDate()){
                System.out.println("tag60F without Date");
                flagValidator = false;
            }else{
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(f60F.getDate());

                } catch (ParseException e) {

                    System.out.println("Tag60F with Date format invalid");
                    flagValidator = false;

                }
            }

            if(null==f60F.getCurrency()){
                System.out.println("tag60F without Currency");
                flagValidator = false;
            }else{
                //System.out.println("VALIDAR MONEDAS");

            }

            try {
                if(null==f60F.getAmount()){
                    System.out.println("tag60F without Amount");
                    flagValidator = false;
                }else if(!(f60F.getAmount().length() >15)){
                    if(!f60F.getAmount().contains(",")){
                        System.out.println("tag60F The decimal comma ',' is mandatory");
                        flagValidator = false;
                    }else{
                        BigInteger valor = new BigInteger("0");
                        try {
                            valor = new BigInteger(f60F.getAmount().substring(0, f60F.getAmount().indexOf(",")));
                        }catch (StringIndexOutOfBoundsException e){
                            System.out.println("tag60F, The integer part of Amount must contain at least one digit");
                            flagValidator = false;
                        }
                        if(f60F.getAmount().substring(f60F.getAmount().indexOf(",")+1).length()==0){
                            System.out.println("tag60F, Invalid Amount format");
                            flagValidator = false;
                        }else if(f60F.getAmount().substring(f60F.getAmount().indexOf(",")+1).length()>2){
                            System.out.println("tag60F, The number of digits following the comma must not exceed the " +
                                    "maximum number allowed (2)");
                            flagValidator = false;
                        }
                    }

                }else{
                    System.out.println("tag60F, Amount length invalid");
                    flagValidator = false;
                }

            }catch (NumberFormatException e){
                System.out.println("tag60F, Invalid Amount format only numbers");
                flagValidator = false;
            }


        }else if(null !=f60m){
            if(null==f60m.getDCMark()){
                System.out.println("tag60M without D/C Mark");
                flagValidator = false;
            }else if(!(f60m.getDCMark().equals("D")||f60m.getDCMark().equals("C")||f60m.getDCMark().substring(0,1).equals("C")||f60m.getDCMark().substring(0,1).equals("D"))){
                System.out.println("tag60M with D/C Mark invalid");
                flagValidator = false;
            }



            if(null==f60m.getDate()){
                System.out.println("tag60M without Date");
                flagValidator = false;
            }else{
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(f60m.getDate());

                } catch (ParseException e) {

                    System.out.println("Tag60M with Date format invalid");
                    flagValidator = false;

                }
            }

            if(null==f60m.getCurrency()){
                System.out.println("tag60M without Currency");
                flagValidator = false;
            }else{
                //System.out.println("VALIDAR MONEDAS");

            }

            try {
                if(null==f60m.getAmount()){
                    System.out.println("tag60M without Amount");
                    flagValidator = false;
                }else if(!(f60m.getAmount().length() >15)){
                    if(!f60m.getAmount().contains(",")){
                        System.out.println("tag60M The decimal comma ',' is mandatory");
                        flagValidator = false;
                    }else{
                        BigInteger valor = new BigInteger("0");
                        try {
                            valor = new BigInteger(f60m.getAmount().substring(0, f60m.getAmount().indexOf(",")));
                        }catch (StringIndexOutOfBoundsException e){
                            System.out.println("tag60M, The integer part of Amount must contain at least one digit");
                            flagValidator = false;
                        }
                        if(f60m.getAmount().substring(f60m.getAmount().indexOf(",")+1).length()==0){
                            System.out.println("tag60M, Invalid Amount format");
                            flagValidator = false;
                        }else if(f60m.getAmount().substring(f60m.getAmount().indexOf(",")+1).length()>2){
                            System.out.println("tag60M, The number of digits following the comma must not exceed the " +
                                    "maximum number allowed (2)");
                            flagValidator = false;
                        }
                    }

                }else{
                    System.out.println("tag60M, Amount length invalid");
                    flagValidator = false;
                }
            }catch (NumberFormatException e){
                System.out.println("tag60M, Invalid Amount format only numbers");
                flagValidator = false;
            }
        }else{
            System.out.println("tag60F or tag60M, are missing");
            flagValidator = false;
        }

        int n61 = mt.getField61().size();
        int n86 = mt.getField86().size();
        //System.out.println(n61+", "+n86);
        if(n61<n86){
            System.out.println("tag61 is missing");
            flagValidator = false;
        }else{

            for(int y=0; y<n61;y++){
                Field61 f61 = mt.getField61().get(y);

                if(null==f61.getValueDate()){
                    System.out.println("tag61 without Value Date");
                    flagValidator = false;
                }else{
                    try {

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

                        formatoFecha.setLenient(false);

                        formatoFecha.parse(f61.getValueDate());

                    } catch (ParseException e) {

                        System.out.println("Tag61 with  Value Date format invalid");
                        flagValidator = false;

                    }
                }

                if(null==f61.getDCMark()){
                    System.out.println("tag61 without D/C Mark");
                    flagValidator = false;
                }else if(!(f61.getDCMark().equals("D")||f61.getDCMark().equals("C")||f61.getDCMark().substring(0,1).equals("C")||f61.getDCMark().substring(0,1).equals("D"))){
                    System.out.println("tag61 with D/C Mark invalid");
                    flagValidator = false;
                }
                //System.out.println(f61.getAmount());


                try {
                    if(Double.parseDouble(f61.getAmount().replace(",",""))==0){
                        System.out.println("tag61 without Amount");
                        flagValidator = false;
                    }else if(!(f61.getAmount().length() >15)){
                        if(!f61.getAmount().contains(",")){
                            System.out.println("tag61 Invalid Amount format");
                            flagValidator = false;
                        }else{
                            BigInteger valor = new BigInteger("0");
                            try {
                                valor = new BigInteger(f61.getAmount().substring(0, f61.getAmount().indexOf(",")));
                            }catch (StringIndexOutOfBoundsException e){
                                System.out.println("tag61, The integer part of Amount must contain at least one digit");
                                flagValidator = false;
                            }
                            if(f61.getAmount().substring(f61.getAmount().indexOf(",")+1).length()==0){
                                System.out.println("tag61, Invalid Amount format");
                                flagValidator = false;
                            }else if(f61.getAmount().substring(f61.getAmount().indexOf(",")+1).length()>2){
                                System.out.println("tag61, The number of digits following the comma must not exceed the " +
                                        "maximum number allowed (2)");
                                flagValidator = false;
                            }
                        }
                        String transType =f61.getTransactionType();
                        if(transType.equals("N")||transType.equals("F")){
                            //System.out.println("Validacion CODIGOS SWIFT :"+f61.getIdentificationCode());
                        }else if(transType.equals("S")){
                            //System.out.println("Validacion CODIGOS SWIFT :"+f61.getIdentificationCode());
                        }else{
                            System.out.println("tag61, Transaction Type is invalid");
                            flagValidator = false;
                        }



                    }else{
                        System.out.println("tag61, Amount length invalid");
                        flagValidator = false;
                    }
                }catch (NumberFormatException e){
                    System.out.println("tag61, Invalid Amount format only numbers");
                    flagValidator = false;
                }

                if(null!=f61.getReferenceForTheAccountOwner()){
                    if(f61.getReferenceForTheAccountOwner().length()>16){
                        System.out.println("tag61, length of Reference of the Account Owner is invalid");
                        flagValidator = false;
                    }
                }else{
                    System.out.println("tag61, Reference of the Account Owner is missing");
                    flagValidator = false;
                }

            }


        }

        Field62F f62F =mt.getField62F();
        Field62M f62m = mt.getField62M();


        if(null !=f62F){

            if(null==f62F.getDCMark()){
                System.out.println("Tag62F without D/C Mark");
                flagValidator = false;
            }else if(!(f62F.getDCMark().equals("D")||f62F.getDCMark().equals("C")||f62F.getDCMark().substring(0,1).equals("C")||f62F.getDCMark().substring(0,1).equals("D"))){
                System.out.println("Tag62F with D/C Mark invalid");
                flagValidator = false;
            }



            if(null==f62F.getDate()){
                System.out.println("Tag62F without Date");
                flagValidator = false;
            }else{
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(f62F.getDate());

                } catch (ParseException e) {

                    System.out.println("Tag62F with Date format invalid");
                    flagValidator = false;

                }
            }

            if(null==f62F.getCurrency()){
                System.out.println("Tag62F without Currency");
                flagValidator = false;
            }else{
                //System.out.println("VALIDAR MONEDAS");

            }

            try {
                if(null==f62F.getAmount()){
                    System.out.println("Tag62F without Amount");
                    flagValidator = false;
                }else if(!(f62F.getAmount().length() >15)){
                    if(!f62F.getAmount().contains(",")){
                        System.out.println("Tag62F The decimal comma ',' is mandatory");
                        flagValidator = false;
                    }else{
                        BigInteger valor = new BigInteger("0");
                        try {
                            valor = new BigInteger(f62F.getAmount().substring(0, f62F.getAmount().indexOf(",")));
                        }catch (StringIndexOutOfBoundsException e){
                            System.out.println("Tag62F, The integer part of Amount must contain at least one digit");
                            flagValidator = false;
                        }
                        if(f62F.getAmount().substring(f62F.getAmount().indexOf(",")+1).length()==0){
                            System.out.println("Tag62F, Invalid Amount format");
                            flagValidator = false;
                        }else if(f62F.getAmount().substring(f62F.getAmount().indexOf(",")+1).length()>2){
                            System.out.println("Tag62F, The number of digits following the comma must not exceed the " +
                                    "maximum number allowed (2)");
                            flagValidator = false;
                        }
                    }





                }else{
                    System.out.println("Tag62F, Invalid Amount length");
                    flagValidator = false;
                }
            }catch (NumberFormatException e){
                System.out.println("Tag62F, Invalid Amount format only numbers");
                flagValidator = false;
            }


        }else if(null !=f62m){
            if(null==f62m.getDCMark()){
                System.out.println("tag62M without D/C Mark");
                flagValidator = false;
            }else if(!(f62m.getDCMark().equals("D")||f62m.getDCMark().equals("C")||f62m.getDCMark().substring(0,1).equals("C")||f62m.getDCMark().substring(0,1).equals("D"))){
                System.out.println("tag62M with D/C Mark invalid");
                flagValidator = false;
            }



            if(null==f62m.getDate()){
                System.out.println("tag62M without Date");
                flagValidator = false;
            }else{
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyMMdd");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(f62m.getDate());

                } catch (ParseException e) {

                    System.out.println("tag62M with Date format invalid");
                    flagValidator = false;

                }
            }

            if(null==f62m.getCurrency()){
                System.out.println("tag62M without Currency");
                flagValidator = false;
            }else{
                //System.out.println("VALIDAR MONEDAS");

            }

            try {
                if(null==f62m.getAmount()){
                    System.out.println("tag62M without Amount");
                    flagValidator = false;
                }else if(!(f62m.getAmount().length() >15)){
                    if(!f62m.getAmount().contains(",")){
                        System.out.println("tag62M The decimal comma ',' is mandatory");
                        flagValidator = false;
                    }else{
                        BigInteger valor = new BigInteger("0");
                        try {
                            valor = new BigInteger(f62m.getAmount().substring(0, f62m.getAmount().indexOf(",")));
                        }catch (StringIndexOutOfBoundsException e){
                            System.out.println("tag62M, The integer part of Amount must contain at least one digit");
                            flagValidator = false;
                        }
                        if(f62m.getAmount().substring(f62m.getAmount().indexOf(",")+1).length()==0){
                            System.out.println("tag62M, Invalid Amount format");
                            flagValidator = false;
                        }else if(f62m.getAmount().substring(f62m.getAmount().indexOf(",")+1).length()>2){
                            System.out.println("tag62M, The number of digits following the comma must not exceed the " +
                                    "maximum number allowed (2)");
                            flagValidator = false;
                        }
                    }





                }else{
                    System.out.println("tag62M, Invalid Amount length");
                    flagValidator = false;
                }
            }catch (NumberFormatException e){
                System.out.println("tag60M, Invalid Amount format only numbers");
                flagValidator = false;
            }
        }else{
            System.out.println("tag62F or tag62M, are missing");
            flagValidator = false;
        }


        return flagValidator;
    }


}
