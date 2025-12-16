import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
			
			
	//BURAYA KADAR OLAN KISIM KARTLARI TANIMLAR TÜRÜ VE DEĞERİ	
			
			
			
			

	public class Card {
	    private final Suit suit;
	    private final Rank rank;

	    public Card(Suit suit, Rank rank) {
	        this.suit = suit;
	        this.rank = rank;
	    }
	    public int getBaseValue() {
	        return rank.getValue();
	    }
	    
	    public String getCardKey() {
	        return rank.name() + "_OF_" + suit.name();
	    }
	    
	    @Override
	    public String toString() {
	        return getCardKey();
	    }
	}
	
	
	//BURAYA KADAR OLAN KISIMDA KARTI YAPILANDIRIRIZ
	
	
	
	
	
	public class Deck {
	    private final List<Card> cards; 

	    public Deck() {
	        this.cards = new ArrayList<>();
	        resetAndShuffle(); 
	    }

	 
	    private void initializeDeck() {
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
	        System.out.println("Tek deste (52 kart) yeni oyun için yeniden hazırlandı ve karıştırıldı.");
	    }

	    public Card dealCard() {
	        if (cards.isEmpty()) {
	          
	            throw new IllegalStateException("Destede kart kalmadı! Yeni oyun başlatılmalı.");
	        }
	        return cards.remove(0); 
	    }

	    public int remainingCards() {
	        return cards.size();
	    }
	}
}
	
	// BURAYA KADAR OLAN KISIMDA DESTE OLUŞUYOR,KARILIYOR VE DAĞITILIYOR
	//YENİ OYUN KISMI ALTA EKLENECEK



