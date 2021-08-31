package com.appz9001.boot.util;

import java.text.DecimalFormat;

public class FormatUtil {
   private static DecimalFormat decimalFormatNoDot = new DecimalFormat("###,##0");

   public static String formatCurrencyNoDot(Double val){
       try{
           return decimalFormatNoDot.format(val);
       }
       catch(Exception e){
           e.printStackTrace();
       }
       return "";
    }
}
