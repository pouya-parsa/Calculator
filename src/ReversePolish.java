import java.util.Stack;

public class ReversePolish {
    protected static Stack ReversePolishStack = new Stack<String>();
    protected static double calculate(Stack ShauntingYardStack) {

        while(!ShauntingYardStack.isEmpty()) {
            String token = String.valueOf(ShauntingYardStack.remove(0));


            switch (TokenHandler.tokenType(token)) {
                case "number":
                    ReversePolishStack.push(token);
                    break;
                case "function":
                    if (TokenHandler.isFunctionTwoInput(token)) {
                        String input1 = String.valueOf(ReversePolishStack.pop());
                        String input2 = String.valueOf(ReversePolishStack.pop());
                        ReversePolishStack.push(MathHandler.functionCaller(token, input1, input2));
                    } else {
                        String input = String.valueOf(ReversePolishStack.pop());
                        ReversePolishStack.push(MathHandler.functionCaller(token, input));
                    }
                    break;
                case "operator":
                    String input1 = String.valueOf(ReversePolishStack.pop());
                    String input2 = String.valueOf(ReversePolishStack.pop());
                    ReversePolishStack.push(MathHandler.operatorCaller(token, input1, input2));
                    break;

            }
        }

        return Double.valueOf(String.valueOf(ReversePolishStack.pop()));
    }
}
