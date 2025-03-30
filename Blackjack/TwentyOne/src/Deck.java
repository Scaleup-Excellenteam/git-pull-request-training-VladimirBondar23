import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> deck;
    int size;
    public Deck(){
        size = 52;
        deck = new ArrayList<>();
        for (Rank rank : Rank.values())
            for (Sign sign : Sign.values())
                deck.add(new Card(rank,sign));
    }
    public Card getCard(){if (size <= 0) {
        System.out.println("The deck is empty. No more cards can be drawn.");
    }
        Random random = new Random();
        int index = random.nextInt(size);
        size -= 1;
        return deck.remove(index);
    }
}
