package start;

public class RefactoringMain {

    public static void main(String[] args) {
        Customer donald = new Customer("Donald Duck");
        Movie movie1 = new Movie("Monty Python and the Holy Grail", Movie.REGULAR);
        Movie movie2 = new Movie("Star Trek 27                   ", Movie.NEW_RELEASE);
        Movie movie3 = new Movie("Star Wars                      ", Movie.REGULAR);
        Movie movie4 = new Movie("Shrek                          ", Movie.CHILDRENS);
        Rental rental1 = new Rental(movie1, 3);
        Rental rental2 = new Rental(movie2, 3);
        Rental rental3 = new Rental(movie3, 4);
        Rental rental4 = new Rental(movie4, 6);
        donald.addRental(rental1);
        donald.addRental(rental2);
        donald.addRental(rental3);
        donald.addRental(rental4);
        String record = donald.statement();
        System.out.println(record);
    }
}