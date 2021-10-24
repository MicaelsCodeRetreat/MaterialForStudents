package fizzbuzz;

public class FizzBussApp {
    public String getGreeting() {
        return "Hello FizzBuzz.";
    }

    public static void main(String[] args) {
        System.out.println(new FizzBussApp().getGreeting());
//        FizzBuzz fizzBuzz = new FizzBuzz();
//        for (int i = 1; i <= 20; i++) {
//        	System.out.println("" + i + " -> " + fizzBuzz.evaluate(i));
//        }
    }
}
