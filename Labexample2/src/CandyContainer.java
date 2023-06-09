import java.util.LinkedList;


public class CandyContainer {
    public static void main(String[] args) {
        LinkedList<String> candyContainer = new LinkedList<>();

        // Insert candies into the container
        candyContainer.push("Orange");
        candyContainer.push("Red");
        candyContainer.push("Blue");
        candyContainer.push("Orange");
        candyContainer.push("Yellow");
        candyContainer.push("Yellow");
        candyContainer.push("Blue");

        System.out.println("The candies in the container:");
        printStack(candyContainer);

        // Ali takes the blue candies
        System.out.println("Ali takes all the candies one by one from the container and eats the blue ones.");

        LinkedList<String> tempContainer = new LinkedList<>();
        while (!candyContainer.isEmpty()) {
            String candy = candyContainer.pop();
            if (candy.equals("Blue")) {
                // Ali eats the blue candy
                System.out.println("Ali eats a blue candy.");
            } else {
                // Put back the non-blue candy into the temporary container
                tempContainer.push(candy);
            }
        }

        // Put the non-blue candies back into the container
        while (!tempContainer.isEmpty()) {
            candyContainer.push(tempContainer.pop());
        }

        System.out.println("He puts back the rest of candies in the container.");
        System.out.println("The candies in the container:");
        printStack(candyContainer);
    }

    public static void printStack(LinkedList<String> stack) {
        for (String candy : stack) {
            System.out.print("<-- " + candy + " ");
        }
        System.out.println();
    }
}
