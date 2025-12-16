
public class denek{
	public enum Suit {
	    HEARTS, DIAMONDS,CLUBS,SPADES       
	    
	     }
	public enum Rank {
	    ACE(11), 
	    TWO(2),
	    THREE(3),
	    FOUR(4),
	    FIVE(5),
	    SIX(6),
	    SEVEN(7),
	    EIGHT(8),
	    NINE(9),
	    TEN(10),
	    JACK(10), 
	    QUEEN(10),
	    KING(10);
	    
	    private final int value;

	    Rank(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	// denek.java dosyasının içine veya ayrı bir Card.java dosyasına ekleyin

	public class Card {
	    private final Suit suit;
	    private final Rank rank;

	    public Card(Suit suit, Rank rank) {
	        this.suit = suit;
	        this.rank = rank;
	    }

	    // Kartın temel Blackjack değerini döndürür. (As kuralı Hand sınıfında halledilecek)
	    public int getBaseValue() {
	        return rank.getValue();
	    }
	    
	    // Frontend'e görsel anahtar sağlamak için:
	    public String getCardKey() {
	        // Örn: "QUEEN_OF_HEARTS"
	        return rank.name() + "_OF_" + suit.name();
	    }
	    
	    @Override
	    public String toString() {
	        return getCardKey();
	    }
	}
}

