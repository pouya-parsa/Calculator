import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenHandler {
    public static String input;

    protected static int lastTokenIndex = 0;

    private static final String[] OPERATORS = {"+", "-", "*", "/", "^"};
    private static final String[] FUNCTIONS = {"sin", "cos", "max"};

    private static HashMap<String, Integer> presedence = new HashMap<>();
    private static HashMap<String, String> associativity = new HashMap<>();

    static {
        presedence.put("^", 2);
        presedence.put("*", 1);
        presedence.put("/", 1);
        presedence.put("+", 0);
        presedence.put("-", 0);


        associativity.put("^", "right");
        associativity.put("*", "left");
        associativity.put("/", "left");
        associativity.put("+", "left");
        associativity.put("-", "left");
    }


    protected static char next() {
        if(TokenHandler.input.length() > lastTokenIndex + 1)
            return TokenHandler.input.charAt(++TokenHandler.lastTokenIndex);
        else
            return 0;
    }

    protected static String tokenType(String token) {

        System.out.println("token " + token);
        if(TokenHandler.isDouble(token) || token.equals("e") || token.equals("PI")) {
            return "number";
        } else if(TokenHandler.isFunction(token)) {
            return "function";
        }else if(TokenHandler.isOperator(token)) {
            return "operator";
        }
        return "nothing";
    }

    private static boolean isOperator(String token) {
        for(int index = 0; index < TokenHandler.OPERATORS.length; index++) {
            if(TokenHandler.OPERATORS[index].equals(token)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean hasGreaterPresedence(String token1, String token2) {
        return presedence.get(token1) > presedence.get(token2);
    }

    protected static boolean hasEqualPresedence(String token1, String token2) {
        return presedence.get(token1).equals(presedence.get(token2))
                && associativity.get(token1).equals("left");
    }
    private static boolean isFunction(String token) {
        for(int index = 0; index < TokenHandler.FUNCTIONS.length; index++) {
            if(TokenHandler.FUNCTIONS[index].equals(token)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDouble(String str) {

        ArrayList<String> matched = new ArrayList<>();

        String patternString = "\\d*.?\\d+|(PI)+|e+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()) {
            matched.add(matcher.group(0).trim());
        }


        String[] output = new String[matched.size()];
        output = matched.toArray(output);

        String value = output.length > 0 ? "1" : str;

        for(String number : output) {
            if(number.equals("PI")) {
                value = String.valueOf(Double.parseDouble(value) * Math.PI);
            } else if(number.equals("e")) {
                value = String.valueOf(Double.parseDouble(value) * Math.E);
            } else {
                value = String.valueOf(Double.parseDouble(value) * Double.parseDouble(number));
            }
        }



        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected static boolean isFunctionTwoInput(String function) {
        if(function.equals("max")) {
            return true;
        } else {
            return false;
        }
    }
}
