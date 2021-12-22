package com.ecs.latam.kafka.domain;

import lombok.extern.log4j.Log4j2;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Log4j2
public class ValidationMulticash {

    public boolean flagValidator =true;
    TransformMCtoXML transformMC = new TransformMCtoXML();

    public boolean validarMC(String inMsgC,String inMsgD) throws DatatypeConfigurationException, ParseException {
        String[] cabecero = null;



        try {
            if(inMsgC.contains(";")){
                cabecero = inMsgC.split(";");
            }else{
                log.error("Invalid Header message Format");
                flagValidator = false;
            }


        }catch (Exception e){
            log.error("Invalid Header message Format");
            flagValidator = false;
        }


        try {
            if(cabecero[0].length()>12){
                log.error("Clave del banco invalid max length");
                flagValidator = false;
            }else if(cabecero[0].trim().length()<1){
                log.error("Clave del banco invalid");
                flagValidator = false;
            }
        }catch (NullPointerException e){
            log.error("Invalid Header message Format");
            flagValidator = false;
        }




        try {
            if(cabecero[1].length()>24){
                log.error("Cuenta bancaria invalid max length");
                flagValidator = false;
            }else if(cabecero[1].trim().length()<1){
                log.error("Cuenta bancaria invalid");
                flagValidator = false;
            }
        }catch (NullPointerException e){
            log.error("Invalid Header message Format");
            flagValidator = false;
        }


        try{

            if(cabecero[2].length()>4){
                log.error("Consecutivo invalid max length");
                flagValidator = false;
            }else if(Integer.parseInt(cabecero[2])==0){
                log.error("Consecutivo invalid");
                flagValidator = false;
            }
        }catch (Exception e){
            log.error("Header Consecutivo is missing or invalid format");
            flagValidator = false;
        }

        try {
            if(cabecero[3].length()>8){
                log.error("Fechas movimientos invalid max length");
                flagValidator = false;
            }else if(cabecero[3].trim().length()>1){

                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yy");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(cabecero[3]);

                } catch (ParseException e) {

                    log.error("Fechas movimientos format invalid");
                    flagValidator = false;

                }
            }else{
                log.error("Fechas movimientos is missing");
                flagValidator = false;
            }
        }catch (Exception e){
            log.error("Invalid Header message Format");
            flagValidator = false;
        }

        try {
            if(cabecero[4].length()>3){
                log.error("Clave de la moneda invalid max length");
                flagValidator = false;
            }else if(cabecero[4].trim().length()<1){
                log.error("Clave de la moneda is missing");
                flagValidator = false;
            }else {
                if (!cabecero[4].equals("COP")) {
                    if (!cabecero[4].equals("USD")) {
                        log.error("Clave de la moneda invalid");
                        flagValidator = false;
                    }

                }
            }
        }catch (Exception e){
            log.error("Invalid Header message Format");
            flagValidator = false;
        }



        try {
            String saldoInicialCuenta =null;
            saldoInicialCuenta = cabecero[5];

            if(saldoInicialCuenta.length()>20){
                log.error("Saldo Inicial Cuenta invalid max length");
                flagValidator = false;
            }else if(saldoInicialCuenta.trim().length()<1) {
                log.error("Saldo Inicial Cuenta invalid max length");
                flagValidator = false;
            }else{
                String signoInicial = null;
                signoInicial = cabecero[5].substring(0,1);

                if(!signoInicial.equals("+")){
                    if(!signoInicial.equals("-")){
                        log.error("Signo valor invalid");
                        flagValidator = false;
                    }
                }

                if(cabecero[5].contains(".")){
                    String enterosInicial = null;
                    enterosInicial = cabecero[5].substring(1,cabecero[5].indexOf("."));

                    try {
                        Long.parseLong(enterosInicial);

                    }catch (NumberFormatException e){
                        log.error("Invalid field Saldo Inicial de la cuenta");
                        flagValidator = false;
                    }


                    String decimalInicial = null;
                    decimalInicial = cabecero[5].substring(cabecero[5].indexOf(".")+1);

                    try {
                        Integer.parseInt(decimalInicial);
                        if(decimalInicial.length()<2){
                            log.error("Saldo Inicial de la cuenta format invalid");
                            flagValidator = false;
                        }

                    }catch (NumberFormatException e){
                        log.error("Invalid field Saldo Inicial de la cuenta");
                        flagValidator = false;
                    }
                }else{
                    log.error("Invalid field Saldo Inicial de la cuenta Format");
                    flagValidator = false;
                }


            }

            if(cabecero[6].contains(".")){
                String totalDebitos =null;
                totalDebitos = cabecero[6];

                if(totalDebitos.length()>20){
                    log.error("Total Debitos invalid max length");
                    flagValidator = false;
                }else if(totalDebitos.trim().length()<1){
                    log.error("Total Debitos invalid max length");
                    flagValidator = false;
                }else{
                    String signoTotalDebitos = null;
                    signoTotalDebitos = cabecero[6].substring(0,1);

                    if(!signoTotalDebitos.equals("-")){
                        log.error("Signo Total Debitos invalid");
                        flagValidator = false;
                    }

                    String enterosTotalDebitos = null;
                    enterosTotalDebitos = cabecero[6].substring(1,cabecero[6].indexOf("."));

                    try {
                        Long.parseLong(enterosTotalDebitos);

                    }catch (NumberFormatException e){
                        log.error("Invalid field Total de Debitos");
                        flagValidator = false;
                    }

                    String decimalTotalDebitos = null;
                    decimalTotalDebitos = cabecero[6].substring(cabecero[6].indexOf(".")+1);

                    try {
                        Integer.parseInt(decimalTotalDebitos);
                        if(decimalTotalDebitos.length()<2){
                            log.error("Total de Debitos format invalid");
                            flagValidator = false;
                        }

                    }catch (NumberFormatException e){
                        log.error("Invalid field Total de Debitos");
                        flagValidator = false;
                    }

                }
            }else{
                log.error("Invalid field Total de Debitos");
                flagValidator = false;
            }

            if(cabecero[7].contains(".")){
                String totalCreditos =null;
                totalCreditos = cabecero[7];

                if(totalCreditos.length()>20){
                    log.error("Total Creditos invalid max length");
                    flagValidator = false;
                }else if(totalCreditos.trim().length()<1){
                    log.error("Total Creditos invalid max length");
                    flagValidator = false;
                }else{
                    String signoTotalCreditos = null;
                    signoTotalCreditos = cabecero[7].substring(0,1);

                    if(!signoTotalCreditos.equals("+")){
                        log.error("Signo Total Creditos invalid");
                        flagValidator = false;
                    }

                    String enterosTotalCreditos = null;
                    enterosTotalCreditos = cabecero[7].substring(1,cabecero[7].indexOf("."));

                    try {
                        Long.parseLong(enterosTotalCreditos);

                    }catch (NumberFormatException e){
                        log.error("Invalid field Total de Creditos");
                        flagValidator = false;
                    }

                    String decimalTotalCreditos = null;
                    decimalTotalCreditos = cabecero[7].substring(cabecero[7].indexOf(".")+1);

                    try {
                        Integer.parseInt(decimalTotalCreditos);
                        if(decimalTotalCreditos.length()<2){
                            log.error("Total de Creditos format invalid");
                            flagValidator = false;
                        }

                    }catch (NumberFormatException e){
                        log.error("Invalid field Total de Creditos");
                        flagValidator = false;
                    }

                }
            }else{
                log.error("Invalid field Total de Creditos");
                flagValidator = false;
            }

            if(cabecero[8].contains(".")){
                String saldoFinalCuenta =null;
                saldoFinalCuenta = cabecero[8];

                if(saldoFinalCuenta.length()>20){
                    log.error("Saldo Final de la Cuenta invalid max length");
                    flagValidator = false;
                }else if(saldoFinalCuenta.trim().length()<1){
                    log.error("Saldo Final de la Cuenta invalid max length");
                    flagValidator = false;
                }else{
                    String signoSaldoFinal = null;
                    signoSaldoFinal = cabecero[8].substring(0,1);

                    if(!signoSaldoFinal.equals("+")){
                        if(!signoSaldoFinal.equals("-")){
                            log.error("Signo Saldo Final invalid");
                            flagValidator = false;
                        }
                    }

                    String enterosSaldoFinal = null;
                    enterosSaldoFinal = cabecero[8].substring(1,cabecero[8].indexOf("."));

                    try {
                        Long.parseLong(enterosSaldoFinal);

                    }catch (NumberFormatException e){
                        log.error("Invalid field Saldo Final de la Cuenta");
                        flagValidator = false;
                    }

                    String decimalSaldoFinal = null;
                    decimalSaldoFinal = cabecero[8].substring(cabecero[8].indexOf(".")+1);

                    try {
                        Integer.parseInt(decimalSaldoFinal);
                        if(decimalSaldoFinal.length()<2){
                            log.error("Saldo Final de la Cuenta format invalid");
                            flagValidator = false;
                        }

                    }catch (NumberFormatException e){
                        log.error("Invalid field Saldo Final de la Cuenta");
                        flagValidator = false;
                    }

                }
            }else{
                log.error("Invalid field Saldo Final de la Cuenta");
                flagValidator = false;
            }


            String tipoCuenta = null;
            tipoCuenta = cabecero[9];

            if(tipoCuenta.trim().length()<1){
                log.error("Tipo de Cuenta is missing");
                flagValidator = false;
            }else if(tipoCuenta.trim().length()>2){
                log.error("Tipo de Cuenta invalid length");
                flagValidator = false;

            }else{
                if(!tipoCuenta.equals("01")){
                    if(!tipoCuenta.equals("02")){
                        log.error("Invalid Tipo de Cuenta");
                        flagValidator = false;
                    }
                }
            }



            String numMovimientos = null;
            numMovimientos = cabecero[17];


            try {
                Integer.parseInt(numMovimientos.trim());
                if(numMovimientos.trim().length()>8){
                    log.error("Numero movimientos Detalle invalid max length");
                    flagValidator = false;
                }else if(numMovimientos.trim().length()<1){
                    log.error("Numero movimientos Detalle is missing");
                    flagValidator = false;
                }
            }catch (NumberFormatException e){
                log.error("Invalid Numero movimientos Detalle");
                flagValidator = false;
            }
        }catch (Exception e){
            log.error("Invalid Header message Format");
            flagValidator = false;
        }




        /////////////////DETALLE

        try {
            String[] detalle = inMsgD.split("\n");


            ArrayList<String> detalles = new ArrayList<>();
            for (int i = 0; i < detalle.length; i++) {
                String message1 = detalle[i] ;
                detalles.add(message1);

            }



            for(String trans:detalles){
                String[] detail = null;
                try {
                    detail = trans.split(";");

                }catch (Exception e){
                    log.error("ERROR DE ARCHIVO: "+e.getMessage().toString());
                    flagValidator = false;
                }



                if(detail[0].length()>12){
                    log.error("Clave del banco Detail invalid max length");
                    flagValidator = false;
                }else if(detail[0].trim().length()<1){
                    log.error("Clave del banco invalid");
                    flagValidator = false;
                }




                if(detail[1].length()>24){
                    log.error("Cuenta bancaria invalid max length");
                    flagValidator = false;
                }else if(detail[1].trim().length()<1){
                    log.error("Cuenta bancaria invalid");
                    flagValidator = false;
                }


                try{

                    if(detail[2].length()>4){
                        log.error("Consecutivo invalid max length");
                        flagValidator = false;
                    }else if(Integer.parseInt(detail[2])==0){
                        log.error("Consecutivo invalid");
                        flagValidator = false;
                    }else if(detail[2].trim().length()<1){
                        log.error("Consecutivo invalid");
                        flagValidator = false;
                    }
                }catch (NumberFormatException  e){
                    log.error("Invalid field Consecutivo");
                    flagValidator = false;
                }

                if(detail[3].length()>8){
                    log.error("Fechas Transaccion invalid max length");
                    flagValidator = false;
                }else if(detail[3].trim().length()<1){
                    log.error("Fechas Transaccion invalid");
                    flagValidator = false;
                }else{
                    try {

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yy");

                        formatoFecha.setLenient(false);

                        formatoFecha.parse(detail[3]);

                    } catch (ParseException e) {

                        log.error("Fechas Transacciones format invalid");
                        flagValidator = false;

                    }
                }


                String claveTransBanco = null;
                claveTransBanco = detail[6];

                if(claveTransBanco.length()>27){
                    log.error("Clave Transaccion del Banco invalid max length");
                    flagValidator = false;
                }else if(claveTransBanco.trim().length()<1){
                    log.error("Clave Transaccion del Banco invalid");
                    flagValidator = false;
                }

                String numeroChequePagado = null;
                numeroChequePagado = detail[9];

                if(numeroChequePagado.length()>16){
                    log.error("Numero de Cheque Pagado invalid max length");
                    flagValidator = false;
                }else if(numeroChequePagado.startsWith("0")){
                    log.error("Numero de Cheque Pagado invalid format");
                    flagValidator = false;
                }

                try {
                    String monto =null;
                    monto = detail[10];

                    if(monto.length()>20){
                        log.error("Monto invalid max length");
                        flagValidator = false;
                    }else if(monto.trim().length()<1){
                        log.error("Monto invalid");
                        flagValidator = false;
                    }else{
                        String signoMonto = null;
                        signoMonto = detail[10].substring(0,1);

                        if(!signoMonto.equals("+")){
                            if(!signoMonto.equals("-")){
                                log.error("Signo Monto invalid");
                                flagValidator = false;
                            }
                        }

                        String enterosMonto = null;
                        enterosMonto = detail[10].substring(1,detail[10].indexOf("."));

                        try {
                            Long.parseLong(enterosMonto);

                        }catch (NumberFormatException e){
                            log.error("Invalid field Monto");
                            flagValidator = false;
                        }

                        String decimalMonto = null;
                        decimalMonto = detail[10].substring(detail[10].indexOf(".")+1);

                        try {
                            Integer.parseInt(decimalMonto);
                            if(decimalMonto.length()<2){
                                log.error("Monto invalid format");
                                flagValidator = false;
                            }else if(decimalMonto.length()>2){
                                log.error("Monto invalid format");
                                flagValidator = false;
                            }

                        }catch (NumberFormatException e){
                            log.error("Invalid field Monto");
                            flagValidator = false;
                        }


                    }
                }catch (Exception e){
                    log.error("Invalid Monto Format");
                    flagValidator = false;
                }




                if(detail[13].length()>8){
                    log.error("Fecha Efectiva invalid max length");
                    flagValidator = false;
                }else if(detail[13].trim().length()<1){
                    log.error("Fecha Efectiva invalid");
                    flagValidator = false;
                }else{
                    try {

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yy");

                        formatoFecha.setLenient(false);

                        formatoFecha.parse(detail[13]);

                    } catch (ParseException e) {

                        log.error("Fecha Efectiva format invalid");
                        flagValidator = false;

                    }
                }

                String nitReferencia1 =null;
                nitReferencia1 = detail[16];


                if(nitReferencia1.length()>27){
                    log.error("Nit Referencia 1 invalid max length");
                    flagValidator = false;
                }

                String referencia2 =null;
                referencia2 = detail[17];


                if(referencia2.length()>27){
                    log.error("Referencia 2 invalid max length");
                    flagValidator = false;
                }

                String referencia =null;
                referencia = detail[18];


                if(referencia.length()>24){
                    log.error("Referencia invalid max length");
                    flagValidator = false;
                }

                String causalRechazo =null;
                causalRechazo = detail[19];


                if(causalRechazo.length()>27){
                    log.error("Causal Rechazo invalid max length");
                    flagValidator = false;
                }

                String codigoTransBanco =null;
                codigoTransBanco = detail[20];


                if(codigoTransBanco.length()>27){
                    log.error("Codigo unico de Transaccion del Banco invalid max length");
                    flagValidator = false;
                }

                String numFormatoConsignacion =null;
                numFormatoConsignacion = detail[21];


                if(numFormatoConsignacion.length()>27){
                    log.error("Numero de Formato de Consignacion invalid max length");
                    flagValidator = false;
                }

            }
        }catch (Exception e){
            log.error("Invalid Detail message Format");
            flagValidator = false;
        }




        /*if(flagValidator){
            transformMC.transformacionMC(inMsgC,inMsgD);
        }else{
            log.error("Error in the MC message");
            flagValidator = false;
        }*/

        return flagValidator;

    }



}