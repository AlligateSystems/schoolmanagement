package com.as;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Common {
	 private static final String[] specialNamesMonthDay = {
		        "",
		        " first",
		        " second",
		        " third",
		        " fourth",
		        " fifth",
		        " sixth",
		        " seventh",
		        " eighth",
		        " nineth",
		        " tenth",
		        " eleventh",
		        " twelveth",
		        " thirteenth",
		        " fourteenth",
		        " fifteenth",
		        " sixteenth",
		        " seventeenth",
		        " eighteenth",
		        " nineteenth",
		        " twenth",
		        " twenty first",
		        " twenty second",
		        " twenty third",
		        " twenty fourth",
		        " twenty fifth",
		        " twenty sixth",
		        " twenty seventh",
		        " twenty eighth",
		        " twenty nineth",
		        " thirty",
		        " thirty first"

		    };
		    private static final String[] specialNames = {
		        "",
		        " thousand"

		    };

		    private static final String[] tensNames = {
		        "",
		        " ten",
		        " twenty",
		        " thirty",
		        " forty",
		        " fifty",
		        " sixty",
		        " seventy",
		        " eighty",
		        " ninety"
		    };

		    private static final String[] numNames = {
		        "",
		        " one",
		        " two",
		        " three",
		        " four",
		        " five",
		        " six",
		        " seven",
		        " eight",
		        " nine",
		        " ten",
		        " eleven",
		        " twelve",
		        " thirteen",
		        " fourteen",
		        " fifteen",
		        " sixteen",
		        " seventeen",
		        " eighteen",
		        " nineteen"
		    };

		    public static String DateToWordsInput(String input) {

		        String strDateToWords=null;
		        if (validateDate(input)) {

		            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		            ParsePosition parsePosition = new ParsePosition(0);
		            Calendar cal = Calendar.getInstance();
		            cal.setTime(dateFormatter.parse(input, new ParsePosition(0)));
		            DateFormat format2 = new SimpleDateFormat("MMMMM ");

		            int day = cal.get(Calendar.DATE);
		            strDateToWords = getMonthDay(day);
		            strDateToWords += " " + format2.format(cal.getTime());

		            int year = cal.get(Calendar.YEAR);
		            strDateToWords += " " + convert(year);

		            System.out.println(strDateToWords.toLowerCase());
		        } else {
		            System.out.println("Wrongt! Please enter date in dd-mm-yyyy format");
		        }
		        return strDateToWords.toLowerCase();
		    }

		    public static String getMonthDay(int day) {
		        return specialNamesMonthDay[day];
		    }

		    private static String convertLessThanOneThousand(int number) {
		        String current;

		        if (number % 100 < 20) {
		            current = numNames[number % 100];
		            number /= 100;
		        } else {
		            current = numNames[number % 10];
		            number /= 10;

		            current = tensNames[number % 10] + current;
		            number /= 10;
		        }
		        if (number == 0) {
		            return current;
		        }
		        return numNames[number] + " hundred" + current;
		    }

		    public static String convert(int number) {

		        if (number == 0) {
		            return "zero";
		        }

		        String prefix = "";

		        String current = "";
		        int place = 0;

		        if (number >= 1 && number < 2000) {
		            do {
		                int n = number % 100;
		                if (n != 0) {
		                    String s = convertLessThanOneThousand(n);
		                    current = s + current;
		                }
		                place++;
		                number /= 100;
		            } while (number > 0);
		        } else {
		            do {
		                int n = number % 1000;
		                if (n != 0) {
		                    String s = convertLessThanOneThousand(n);
		                    current = s + specialNames[place] + current;
		                }
		                place++;
		                number /= 1000;
		            } while (number > 0);
		        }

		        return (prefix + current).trim();
		    }

		    public static boolean validateDate(String date) {
		        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		        try {
		            sdf.setLenient(false);
		            sdf.parse(date);
		            return true;
		        } catch (ParseException ex) {
		            return false;
		        }
		    }
}
