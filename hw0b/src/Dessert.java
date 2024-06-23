public class Dessert {
    // One static variable int numDesserts that keeps track of the number of desserts created so far.
    public static int numDesserts = 0;

    // Two instance variables
    public int flavor;
    public int price;

    // Constructor for Dessert Class
    public Dessert(int fl, int pr){
        flavor = fl;
        price = pr;
        Dessert.numDesserts += 1;
    }

    public void printDessert(){
        System.out.print(flavor + " " + price + " " + numDesserts);
    }

    public static void main(String[] args){
        System.out.print("I love dessert!");
    }
}
