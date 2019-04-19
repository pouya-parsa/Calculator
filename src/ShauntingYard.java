import java.util.Stack;

public class ShauntingYard {
    private static Stack ShauntingYardStack = new Stack();
    private static Stack operatorStack = new Stack();
    public static Stack createStack(String[] parsedInput) {

        for(String token : parsedInput) {

            if (TokenHandler.tokenType(token).equals("number")) {
                ShauntingYardStack.push(token);
            } else if (TokenHandler.tokenType(token).equals("function")){
                operatorStack.push(token);
            } else if (TokenHandler.tokenType(token).equals("operator")){
                if(!operatorStack.isEmpty()) {
                    String lastElement = String.valueOf(operatorStack.lastElement());

                    while (!lastElement.equals("(")
                            && !operatorStack.isEmpty()
                            && (TokenHandler.tokenType(lastElement).equals("function")
                            || TokenHandler.hasGreaterPresedence(lastElement, token)
                            || TokenHandler.hasEqualPresedence(lastElement, token))

                    ) {
                        ShauntingYardStack.push(String.valueOf(operatorStack.pop()));
                        if(!operatorStack.isEmpty()) {
                            lastElement = String.valueOf(operatorStack.lastElement());
                        }


                    }
                }
                operatorStack.push(token);
            } else if (token.equals("(")){
                operatorStack.push(token);
            } else if (token.equals(")")){

                String lastElement = String.valueOf(operatorStack.lastElement());
                while(!lastElement.equals("(")) {
                    ShauntingYardStack.push(operatorStack.pop());
                    lastElement = String.valueOf(operatorStack.lastElement());
                }
                if(lastElement.equals("(")) {
                    operatorStack.pop();
                }
            }

        }
        while(!operatorStack.isEmpty()) {
            ShauntingYardStack.push(String.valueOf(operatorStack.pop()));
        }
        return ShauntingYard.ShauntingYardStack;
    }

}
