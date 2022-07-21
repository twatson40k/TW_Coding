package com.VendingMachine.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CoinMath {

   enum Coins {
       ONE_PENCE (new BigDecimal("0.01")),
       TWO_PENCE (new BigDecimal("0.02")),
       FIVE_PENCE (new BigDecimal("0.05")),
       TEN_PENCE(new BigDecimal("0.10")),
       TWENTY_PENCE(new BigDecimal("0.20")),
       FIFTY_PENCE(new BigDecimal("0.50")),
       ONE_POUND(new BigDecimal("1.00")),
       TWO_POUND (new BigDecimal("2.00"));

       private final BigDecimal currencyValue;

       Coins(BigDecimal currencyValue){
           this.currencyValue = currencyValue;
       }
   }

    public static String getChange(BigDecimal customerBalance, BigDecimal itemPrice)
    {

        BigDecimal change = customerBalance.subtract(itemPrice);
        String changeString = "";
        if (change.compareTo(new BigDecimal("0.00")) == 0) {
            changeString = "No change";
        } else {

            int[] coinCount = new int[8];

            if (change.compareTo(Coins.TWO_POUND.currencyValue) >= 0) {
                coinCount[0] = change.divide(Coins.TWO_POUND.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.TWO_POUND.currencyValue.multiply(new BigDecimal(coinCount[0])));
            }
            if (change.compareTo(Coins.ONE_POUND.currencyValue) >= 0) {
                coinCount[1] = change.divide(Coins.ONE_POUND.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.ONE_POUND.currencyValue.multiply(new BigDecimal(coinCount[1])));
            }
            if (change.compareTo(Coins.FIFTY_PENCE.currencyValue) >= 0) {
                coinCount[2] = change.divide(Coins.FIFTY_PENCE.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.FIFTY_PENCE.currencyValue.multiply(new BigDecimal(coinCount[2])));
            }
            if (change.compareTo(Coins.TWENTY_PENCE.currencyValue) >= 0) {
                coinCount[3] = change.divide(Coins.TWENTY_PENCE.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.TWENTY_PENCE.currencyValue.multiply(new BigDecimal(coinCount[3])));
            }
            if (change.compareTo(Coins.TEN_PENCE.currencyValue) >= 0) {
                coinCount[4] = change.divide(Coins.TEN_PENCE.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.TEN_PENCE.currencyValue.multiply(new BigDecimal(coinCount[4])));
            }
            if (change.compareTo(Coins.FIVE_PENCE.currencyValue) >= 0) {
                coinCount[5] = change.divide(Coins.FIVE_PENCE.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.FIVE_PENCE.currencyValue.multiply(new BigDecimal(coinCount[5])));
            }
            if (change.compareTo(Coins.TWO_PENCE.currencyValue) >= 0) {
                coinCount[6] = change.divide(Coins.TWO_PENCE.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.TWO_PENCE.currencyValue.multiply(new BigDecimal(coinCount[6])));
            }
            if (change.compareTo(Coins.ONE_PENCE.currencyValue) >= 0) {
                coinCount[7] = change.divide(Coins.ONE_PENCE.currencyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Coins.ONE_PENCE.currencyValue.multiply(new BigDecimal(coinCount[7])));
            }
            changeString = "Change: " + coinCount[0] + " | " + Coins.TWO_POUND + " | " + coinCount[1] + " | "
                    + Coins.ONE_POUND + " | " + coinCount[2] + " | " + Coins.FIFTY_PENCE + " | " + coinCount[3]
                    + " | " + Coins.TWENTY_PENCE + " | " + coinCount[4] + " | " + Coins.TEN_PENCE + " | "
                    + coinCount[5] + " | " + Coins.FIVE_PENCE + " | " + coinCount[6] + " | " + Coins.TWO_PENCE
                    + " | " + coinCount[7] + " | " + Coins.ONE_PENCE;

        }

        return changeString;
    }

}
