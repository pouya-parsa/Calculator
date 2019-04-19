import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String input = "cos(3.14)*1";

        ArrayList<String> matched = new ArrayList<>();

        String patternString = "(\\d?\\.?(?:\\d+)\\s*((PI)|e)*)|(((PI)|e)+)|[(]|[)]|(sin)|(cos)|(max)|[*/+-^]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            matched.add(matcher.group(0).trim());
        }

        System.out.println(matched.toString());


        String[] output = new String[matched.size()];
        output = matched.toArray(output);


        Stack ShauntingYardStack =  ShauntingYard.createStack(output);


        double result = ReversePolish.calculate(ShauntingYardStack);
        if(result < 0.000001 && result > 0) {
            result = 0;
        }
        System.out.println(result);
        
    }
}
