import java.util.Scanner;

public class FractionCalculator {
    
    public FractionCalculator(){}
    
    public static void main(String[] args) {
        FractionCalculator calculator = new FractionCalculator();
        calculator.launch();
    }
    
    private void launch(){
        
        // variables
        String input = "";
        Fraction value = new Fraction(1,1);
        
        // create Scanner
        Scanner scan = new Scanner(System.in);
        
        // welcome message
        System.out.println("Welcome to James Hills' Fraction Calculator.");
        System.out.println("Please enter a calculation below:");
        
        // obtain intital input
        input = scan.nextLine();
        
        // take commands from the user and display the result
        do  {
            
            value = evaluate(value, input);
            System.out.println(" = " + value.toString());
            input = scan.nextLine();
            
            } while (!input.equalsIgnoreCase("Goodbye"));
        
        // closing
        System.out.println();
        System.out.println("Fraction Calculator now closing.");
    }
    
    public Fraction evaluate(Fraction fraction, String inputString) {
        
        // create variables
        String operator = "";
        Fraction current = fraction;
        String item;
        String delims = "[ ]+";
        String[] tokens = inputString.split(delims);
        boolean hasDigit = false;
        
        // parse the string and perform calculations
        for(int i = 0; i < tokens.length; i++) {
            item = tokens[i];
            
            for(int j = 0; j < item.length(); j++){
                if(Character.isDigit(item.charAt(j))){
                    hasDigit = true;
                }
            }
            
            // parse numerals
            if(hasDigit){
                switch(operator){
                    case "+":
                        current = current.add(parseNumerals(item));
                        operator = "";
                        break;
                    case "-":
                        current = current.subtract(parseNumerals(item));
                        operator = "";
                        break;
                    case "*":
                        current = current.multiply(parseNumerals(item));
                        operator = "";
                        break;
                    case "/":
                        current = current.divide(parseNumerals(item));
                        operator = "";
                        break;
                    default:
                        current = parseNumerals(item);
                }
                hasDigit = false;
            }
            else{
                // check and note the operation
                switch(item){
                    case "+": operator = item; break;
                    case "-": operator = item; break;
                    case "*": operator = item; break;
                    case "/": operator = item; break;
                }
            }
        }
        
        return current;
    }
    
    private Fraction parseNumerals(String item){
        // create variables
        int num, denom;

        // create Scanner
        Scanner s2 = new Scanner(item);
        s2.useDelimiter("/");
        
        // get 1st integer
        num = s2.nextInt();
        
        // check for 2nd integer & return Fraction
        if(s2.hasNext()){
            denom = s2.nextInt();
            return new Fraction(num, denom);
        }
        else{
            return new Fraction(num, 1);
        }
    }
}