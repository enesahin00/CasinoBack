
import java.util.ArrayList;
import java.util.List;

public class Hand extends denek{
	private final List<Card> cards;

	public  Hand() {
		this.cards = new ArrayList<>();
	}

	// YENİ KART EKLEME METODU (Dağıtılan Kartı Ele Koyma eylemi budur)
	public void addCard(Card card) {
		this.cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getCardCount() {
		return cards.size();
	}

	
	public int calculateScore() {
		return 0;
	}

	
	
	
	//BURAYA KADAR OLAN KISIM ELE KART ALMAYI KONTROL EDER
	
	
	
	
	
}