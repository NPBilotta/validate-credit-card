package dev.bilotta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreditCardCheck {

    public boolean isValidCreditCard(String string) {
        //Clean input
        //return false if there is a punctuation
        string = string.replaceAll(" ","");
        String[] input = string.split("");
        System.out.println("String input: "+input);

        //check for non-numeric fields
        for (int f = 0; f < input.length; f++) {
            List<String> nums = new ArrayList( Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0") );
            if (!nums.contains(input[f])) {
                return false;
            }
        }

        List<Integer> numbers = new ArrayList<>();
        //add the int values of the input array to the numbers list
        for (String s : input) {
            try {
                numbers.add(Integer.parseInt(s));
            } catch (NumberFormatException n) {
                return false;
            }
        }
        System.out.println("Int input: "+numbers);

        int index;
        int checkDigit = numbers.get(numbers.size()-1);
        numbers.remove(numbers.size()-1);
        //Double every odd digit starting from the left
        for (int d = 2; d < numbers.size(); d+=2) {
            index = numbers.size()-d;

            numbers.set(index, numbers.get(index)*2);

            if (numbers.get(index)>9) {
                //If the new digit is > 9, subtract 9
                numbers.set(index, numbers.get(index)-9);
            }

        }
        System.out.println("Doubled");
        System.out.println("Int input: "+numbers);

        //after doubling every other digit, sum all digits
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        System.out.println("Sum: "+sum);

        //Take the last digit of the sum, subtract that from 10, and compare to the check digit
        int lastDigit = sum % 10;
        System.out.println("Last Digit: "+lastDigit);
        System.out.println("10 - last digit: "+(10-lastDigit)+"   -   Check Digit: "+checkDigit);
        return ((10-lastDigit) == checkDigit);
    }

}
