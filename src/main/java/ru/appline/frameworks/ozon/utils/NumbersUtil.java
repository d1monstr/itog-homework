package ru.appline.frameworks.ozon.utils;

public class NumbersUtil {

    public static String cleanNumber(String numberString){
        String number = "";
        String numbers = "0987654321.,";
        for (int i = 0; i < numberString.length(); ++i) {
            if (numbers.contains("" + numberString.charAt(i))) {
                number += numberString.charAt(i);
            }
        }
        return number;
    }

    public static String numberWithThinSpace(String numberString){
        String number = "", buff;
        for (int i = numberString.length() - 1, n = 0; i >= 0; i--, n++) {
            buff = number;
            if ((n % 3 == 0) && n != 0 && (n != numberString.length())) {
                buff = " " + buff;
            }
            number = numberString.charAt(i) + buff;
        }
        return number;
    }

}
