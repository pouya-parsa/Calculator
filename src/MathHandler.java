import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathHandler {

    protected static double translateSymbols(String symbol) {
        ArrayList<String> matched = new ArrayList<>();

        String patternString = "\\d*.?\\d+|(PI)+|e+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(symbol);

        while(matcher.find()) {
            matched.add(matcher.group(0).trim());
        }


        String[] output = new String[matched.size()];
        output = matched.toArray(output);

        double value = 1;

        for(String number : output) {
            if(number.equals("PI")) {
                value *= Math.PI;
            } else if(number.equals("e")) {
                value *= Math.E;
            } else {
                value *= Double.parseDouble(number);
            }
        }
        return value;
    }

    protected static double functionCaller(String function, String input1, String input2) {
        switch (function) {
            case "max" :
                return Math.max(translateSymbols(input1), translateSymbols(input2));
        }
        return 0;
    }

    protected static double functionCaller(String function, String input) {
        switch (function) {
            case "sin":
                double result = Math.sin(translateSymbols(input));
                if(result < 0.000001 && result > 0) {
                    result = 0;
                }
                return result;
            case "cos":
                double result1 = Math.cos(translateSymbols(input));
                if(result1 < 0.000001 && result1 > 0) {
                    result = 0;
                }
                return result1;
        }
        return 0;

    }

    protected static double operatorCaller(String operator, String input1, String input2) {
        Double in1 = translateSymbols(input1);
        Double in2 = translateSymbols(input2);
        if(operator.equals("+")) {
            return in2 + in1;
        } else if(operator.equals("-")) {
            return in2 - in1;
        } else if(operator.equals("*")) {
            return in2 * in1;
        } else if(operator.equals("/")) {
            return in2 / in1 ;
        } else if(operator.equals("^")) {
            return Math.pow(in2, in1);
        }
        return 0;
    }
}