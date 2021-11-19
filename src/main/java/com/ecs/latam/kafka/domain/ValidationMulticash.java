package com.ecs.latam.kafka.domain;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ValidationMulticash {

    public boolean flagValidator =true;
    TransformMCtoXML transformMC = new TransformMCtoXML();

    public void validarMC(String inMsgC,String inMsgD) throws DatatypeConfigurationException, ParseException {
        String[] cabecero = null;


        try {
            cabecero = inMsgC.split(";");

        }catch (Exception e){
            log.debug("ERROR DE ARCHIVO: "+e.getMessage().toString());
            flagValidator = false;
        }



        if(cabecero[0].length()>12){
            log.debug("Clave del banco invalid max length");
            flagValidator = false;
        }else if(cabecero[0].trim().length()<1){
            log.debug("Clave del banco invalid");
            flagValidator = false;
        }




        if(cabecero[1].length()>24){
            log.debug("Cuenta bancaria invalid max length");
            flagValidator = false;
        }else if(cabecero[1].trim().length()<1){
            log.debug("Cuenta bancaria invalid");
            flagValidator = false;
        }


        try{

            if(cabecero[2].length()>4){
                log.debug("Consecutivo invalid max length");
                flagValidator = false;
            }else if(Integer.parseInt(cabecero[2])==0){
                log.debug("Consecutivo invalid");
                flagValidator = false;
            }else if(cabecero[2].trim().length()<1){
                log.debug("Consecutivo invalid");
                flagValidator = false;
            }
        }catch (NumberFormatException  e){
            log.debug("Invalid field Consecutivo, only numbers");
            flagValidator = false;
        }

        if(cabecero[3].length()>8){
            log.debug("Fechas movimientos invalid max length");
            flagValidator = false;
        }else if(cabecero[3].trim().length()>1){

            try {

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yy");

                formatoFecha.setLenient(false);

                formatoFecha.parse(cabecero[3]);

            } catch (ParseException e) {

                log.debug("Fechas movimientos format invalid");
                flagValidator = false;

            }
        }else{
            log.debug("Fechas movimientos invalid");
            flagValidator = false;
        }

        if(cabecero[4].length()>3){
            log.debug("Clave de la moneda invalid max length");
            flagValidator = false;
        }else if(cabecero[4].trim().length()<1){
            log.debug("Clave de la moneda invalid");
            flagValidator = false;
        }else {
            if (!cabecero[4].equals("COP")) {
                if (!cabecero[4].equals("USD")) {
                    log.debug("Clave de la moneda invalid");
                    flagValidator = false;
                }

            }
        }


        String saldoInicialCuenta =null;
        saldoInicialCuenta = cabecero[5];

        if(saldoInicialCuenta.length()>20){
            log.debug("Saldo Inicial Cuenta invalid max length");
            flagValidator = false;
        }else if(saldoInicialCuenta.trim().length()<1) {
            log.debug("Saldo Inicial Cuenta invalid max length");
            flagValidator = false;
        }else{
            String signoInicial = null;
            signoInicial = cabecero[5].substring(0,1);

            if(!signoInicial.equals("+")){
                if(!signoInicial.equals("-")){
                    log.debug("Signo valor invalid");
                    flagValidator = false;
                }
            }

            String enterosInicial = null;
            enterosInicial = cabecero[5].substring(1,cabecero[5].indexOf("."));

            try {
                Long.parseLong(enterosInicial);

            }catch (NumberFormatException e){
                log.debug("Invalid field Saldo Inicial de la cuenta");
                flagValidator = false;
            }

            String decimalInicial = null;
            decimalInicial = cabecero[5].substring(cabecero[5].indexOf(".")+1);

            try {
                Integer.parseInt(decimalInicial);
                if(decimalInicial.length()<2){
                    log.debug("Saldo Inicial de la cuenta format invalid");
                    flagValidator = false;
                }

            }catch (NumberFormatException e){
                log.debug("Invalid field Saldo Inicial de la cuenta");
                flagValidator = false;
            }
        }

        String totalDebitos =null;
        totalDebitos = cabecero[6];

        if(totalDebitos.length()>20){
            log.debug("Total Debitos invalid max length");
            flagValidator = false;
        }else if(totalDebitos.trim().length()<1){
            log.debug("Total Debitos invalid max length");
            flagValidator = false;
        }else{
            String signoTotalDebitos = null;
            signoTotalDebitos = cabecero[6].substring(0,1);

            if(!signoTotalDebitos.equals("-")){
                log.debug("Signo Total Debitos invalid");
                flagValidator = false;
            }

            String enterosTotalDebitos = null;
            enterosTotalDebitos = cabecero[6].substring(1,cabecero[6].indexOf("."));

            try {
                Long.parseLong(enterosTotalDebitos);

            }catch (NumberFormatException e){
                log.debug("Invalid field Total de Debitos");
                flagValidator = false;
            }

            String decimalTotalDebitos = null;
            decimalTotalDebitos = cabecero[6].substring(cabecero[6].indexOf(".")+1);

            try {
                Integer.parseInt(decimalTotalDebitos);
                if(decimalTotalDebitos.length()<2){
                    log.debug("Total de Debitos format invalid");
                    flagValidator = false;
                }

            }catch (NumberFormatException e){
                log.debug("Invalid field Total de Debitos");
                flagValidator = false;
            }

        }

        String totalCreditos =null;
        totalCreditos = cabecero[7];

        if(totalCreditos.length()>20){
            log.debug("Total Creditos invalid max length");
            flagValidator = false;
        }else if(totalCreditos.trim().length()<1){
            log.debug("Total Creditos invalid max length");
            flagValidator = false;
        }else{
            String signoTotalCreditos = null;
            signoTotalCreditos = cabecero[7].substring(0,1);

            if(!signoTotalCreditos.equals("+")){
                log.debug("Signo Total Creditos invalid");
                flagValidator = false;
            }

            String enterosTotalCreditos = null;
            enterosTotalCreditos = cabecero[7].substring(1,cabecero[7].indexOf("."));

            try {
                Long.parseLong(enterosTotalCreditos);

            }catch (NumberFormatException e){
                log.debug("Invalid field Total de Creditos");
                flagValidator = false;
            }

            String decimalTotalCreditos = null;
            decimalTotalCreditos = cabecero[7].substring(cabecero[7].indexOf(".")+1);

            try {
                Integer.parseInt(decimalTotalCreditos);
                if(decimalTotalCreditos.length()<2){
                    log.debug("Total de Creditos format invalid");
                    flagValidator = false;
                }

            }catch (NumberFormatException e){
                log.debug("Invalid field Total de Creditos");
                flagValidator = false;
            }

        }

        String saldoFinalCuenta =null;
        saldoFinalCuenta = cabecero[8];

        if(saldoFinalCuenta.length()>20){
            log.debug("Saldo Final de la Cuenta invalid max length");
            flagValidator = false;
        }else if(saldoFinalCuenta.trim().length()<1){
            log.debug("Saldo Final de la Cuenta invalid max length");
            flagValidator = false;
        }else{
            String signoSaldoFinal = null;
            signoSaldoFinal = cabecero[8].substring(0,1);

            if(!signoSaldoFinal.equals("+")){
                if(!signoSaldoFinal.equals("-")){
                    log.debug("Signo Saldo Final invalid");
                    flagValidator = false;
                }
            }

            String enterosSaldoFinal = null;
            enterosSaldoFinal = cabecero[8].substring(1,cabecero[8].indexOf("."));

            try {
                Long.parseLong(enterosSaldoFinal);

            }catch (NumberFormatException e){
                log.debug("Invalid field Saldo Final de la Cuenta");
                flagValidator = false;
            }

            String decimalSaldoFinal = null;
            decimalSaldoFinal = cabecero[8].substring(cabecero[8].indexOf(".")+1);

            try {
                Integer.parseInt(decimalSaldoFinal);
                if(decimalSaldoFinal.length()<2){
                    log.debug("Saldo Final de la Cuenta format invalid");
                    flagValidator = false;
                }

            }catch (NumberFormatException e){
                log.debug("Invalid field Saldo Final de la Cuenta");
                flagValidator = false;
            }

        }

        String tipoCuenta = null;
        tipoCuenta = cabecero[9];

        if(tipoCuenta.trim().length()<1){
            log.debug("Invalid Tipo de Cuenta");
            flagValidator = false;
        }else{
            if(!tipoCuenta.equals("01")){
                if(!tipoCuenta.equals("02")){
                    log.debug("Invalid Tipo de Cuenta");
                    flagValidator = false;
                }
            }
        }



        String numMovimientos = null;
        numMovimientos = cabecero[17];


        try {
            Integer.parseInt(numMovimientos.trim());
            if(numMovimientos.trim().length()>8){
                log.debug("Numero movimientos Detalle invalid max length");
                flagValidator = false;
            }else if(numMovimientos.trim().length()<1){
                log.debug("Numero movimientos Detalle invalid max length");
                flagValidator = false;
            }
        }catch (NumberFormatException e){
            log.debug("Invalid Numero movimientos Detalle");
            flagValidator = false;
        }


        /////////////////DETALLE


        String[] detalle = inMsgD.split("  \n");


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
                log.debug("ERROR DE ARCHIVO: "+e.getMessage().toString());
                flagValidator = false;
            }



            if(detail[0].length()>12){
                log.debug("Clave del banco invalid max length");
                flagValidator = false;
            }else if(detail[0].trim().length()<1){
                log.debug("Clave del banco invalid");
                flagValidator = false;
            }




            if(detail[1].length()>24){
                log.debug("Cuenta bancaria invalid max length");
                flagValidator = false;
            }else if(detail[1].trim().length()<1){
                log.debug("Cuenta bancaria invalid");
                flagValidator = false;
            }


            try{

                if(detail[2].length()>4){
                    log.debug("Consecutivo invalid max length");
                    flagValidator = false;
                }else if(Integer.parseInt(detail[2])==0){
                    log.debug("Consecutivo invalid");
                    flagValidator = false;
                }else if(detail[2].trim().length()<1){
                    log.debug("Consecutivo invalid");
                    flagValidator = false;
                }
            }catch (NumberFormatException  e){
                log.debug("Invalid field Consecutivo, only numbers");
                flagValidator = false;
            }

            if(detail[3].length()>8){
                log.debug("Fechas Transaccion invalid max length");
                flagValidator = false;
            }else if(detail[3].trim().length()<1){
                log.debug("Fechas Transaccion invalid");
                flagValidator = false;
            }else{
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yy");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(detail[3]);

                } catch (ParseException e) {

                    log.debug("Fechas Transacciones format invalid");
                    flagValidator = false;

                }
            }


            String claveTransBanco = null;
            claveTransBanco = detail[6];

            if(claveTransBanco.length()>27){
                log.debug("Clave Transaccion del Banco invalid max length");
                flagValidator = false;
            }else if(claveTransBanco.trim().length()<1){
                log.debug("Clave Transaccion del Banco invalid");
                flagValidator = false;
            }

            String numeroChequePagado = null;
            numeroChequePagado = detail[9];

            if(numeroChequePagado.length()>16){
                log.debug("Numero de Cheque Pagado invalid max length");
                flagValidator = false;
            }else if(numeroChequePagado.startsWith("0")){
                log.debug("Numero de Cheque Pagado invalid format");
                flagValidator = false;
            }

            String monto =null;
            monto = detail[10];

            if(monto.length()>20){
                log.debug("Monto invalid max length");
                flagValidator = false;
            }else if(monto.trim().length()<1){
                log.debug("Monto invalid max length");
                flagValidator = false;
            }else{
                String signoMonto = null;
                signoMonto = detail[10].substring(0,1);

                if(!signoMonto.equals("+")){
                    if(!signoMonto.equals("-")){
                        log.debug("Signo Montoinvalid");
                        flagValidator = false;
                    }
                }

                String enterosMonto = null;
                enterosMonto = detail[10].substring(1,detail[10].indexOf("."));

                try {
                    Long.parseLong(enterosMonto);

                }catch (NumberFormatException e){
                    log.debug("Invalid field Monto");
                    flagValidator = false;
                }

                String decimalMonto = null;
                decimalMonto = detail[10].substring(detail[10].indexOf(".")+1);

                try {
                    Integer.parseInt(decimalMonto);
                    if(decimalMonto.length()<2){
                        log.debug("Monto invalid format");
                        flagValidator = false;
                    }

                }catch (NumberFormatException e){
                    log.debug("Invalid field Monto");
                    flagValidator = false;
                }


            }

            if(detail[13].length()>8){
                log.debug("Fecha Efectiva invalid max length");
                flagValidator = false;
            }else if(detail[13].trim().length()<1){
                log.debug("Fecha Efectiva invalid");
                flagValidator = false;
            }else{
                try {

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yy");

                    formatoFecha.setLenient(false);

                    formatoFecha.parse(detail[13]);

                } catch (ParseException e) {

                    log.debug("Fecha Efectiva format invalid");
                    flagValidator = false;

                }
            }

            String nitReferencia1 =null;
            nitReferencia1 = detail[16];


            if(nitReferencia1.length()>27){
                log.debug("Nit Referencia 1 invalid max length");
                flagValidator = false;
            }

            String referencia2 =null;
            referencia2 = detail[17];


            if(referencia2.length()>27){
                log.debug("Referencia 2 invalid max length");
                flagValidator = false;
            }

            String referencia =null;
            referencia = detail[18];


            if(referencia.length()>24){
                log.debug("Referencia invalid max length");
                flagValidator = false;
            }

            String causalRechazo =null;
            causalRechazo = detail[19];


            if(causalRechazo.length()>27){
                log.debug("Causal Rechazo invalid max length");
                flagValidator = false;
            }

            String codigoTransBanco =null;
            codigoTransBanco = detail[20];


            if(codigoTransBanco.length()>27){
                log.debug("Codigo unico de Transaccion del Banco invalid max length");
                flagValidator = false;
            }

            String numFormatoConsignacion =null;
            numFormatoConsignacion = detail[21];


            if(numFormatoConsignacion.length()>27){
                log.debug("Numero de Formato de Consignacion invalid max length");
                flagValidator = false;
            }

        }

        if(flagValidator){
            transformMC.transformacionMC(inMsgC,inMsgD);
        }else{
            log.debug("Error in the MC message");
            flagValidator = false;
        }



    }


}