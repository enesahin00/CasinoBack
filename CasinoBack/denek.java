import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class denek {
    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }

    public enum Rank {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), 
        SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);
        
        private final int value;
        Rank(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    // STATİC ekledik, böylece BlackjackGame bunu görebilecek
    public static class Card { 
        private final Suit suit;
        private final Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
        public int getBaseValue() { return rank.getValue(); }
        public Rank getRank() { return this.rank; }
        public String getCardKey() { return rank.name() + "_OF_" + suit.name(); }
        
        @Override
        public String toString() { return getCardKey(); }
    }

    // STATİC ekledik
    public static class Deck {
        private final List<Card> cards = new ArrayList<>();

        public void initializeDeck() {
            cards.clear();
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    this.cards.add(new Card(suit, rank));
                }
            }
        }

        public void resetAndShuffle() {
            initializeDeck();
            Collections.shuffle(cards);
            System.out.println("Tek deste (52 kart) hazırlandı ve karıştırıldı.");
        }

        public Card dealCard() {
            if (cards.isEmpty()) throw new IllegalStateException("Deste boş!");
            return cards.remove(0);
        }
    }
    
}