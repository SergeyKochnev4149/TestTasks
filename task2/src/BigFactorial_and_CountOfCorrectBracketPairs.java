import java.math.BigInteger;


public class Task1_and_Task3 {

    public static void main(String[] args) {
        System.out.println(getNumberOfCorrectBracketPairs(2));
        System.out.println(getNumberOfCorrectBracketPairs(5));
        System.out.println(getNumberOfCorrectBracketPairs(10));

        System.out.println();

        BigInteger factorial = getFactorialOfNumber(100);
        System.out.println(getSumOfDigitInFactorialOfNumber(factorial));
        factorial = getFactorialOfNumber(4);
        System.out.println(getSumOfDigitInFactorialOfNumber(factorial));


    }

//        1.  Найти число правильных скобочных выражений,
//        содержащих N открывающихся и N закрывающихся скобок.
//        N вводится с клавиатуры. N неотрицательное целое число.

//        The number of correct pairs of brackets is Catalan's number.
//        Catalan's number(n) = (2n)! / (n! * (n + 1)!)

    public static BigInteger getNumberOfCorrectBracketPairs(int amountOfBracket) {
        if (amountOfBracket < 1) return new BigInteger(String.valueOf(0));

        BigInteger factorialOfBracketPairs = getFactorialOfNumber(amountOfBracket * 2);
        BigInteger factorialOfBracket = getFactorialOfNumber(amountOfBracket);
        BigInteger numberOfCorrectBracketPairs = factorialOfBracketPairs
                .divide(factorialOfBracket.multiply(Task1_and_Task3.getFactorialOfNumber(amountOfBracket + 1)));

        return numberOfCorrectBracketPairs;
    }


    //    3. Find the sum of the digits in the number 100! (i.e. 100 factorial)
    public static int getSumOfDigitInFactorialOfNumber(BigInteger factorialOfNumber) {
        String factorial = String.valueOf(factorialOfNumber);
        int sumOfDigit = 0;

        for (int i = 0; i < factorial.length(); i++) {
            if (factorial.charAt(i) == '0')
                continue;

            int digit = Character.getNumericValue(factorial.charAt(i));
            sumOfDigit += digit;
        }
        return sumOfDigit;
    }


    public static BigInteger getFactorialOfNumber(int number) {
        if (number < 0) {
            System.out.println("Entered a negative number");
            return null;
        }

        if (number == 1 || number == 0)
            return new BigInteger("1");

        BigInteger n = new BigInteger(String.valueOf(number));
        BigInteger recursion = new BigInteger(String.valueOf(getFactorialOfNumber(number - 1)));
        return n.multiply(recursion);
    }

}
