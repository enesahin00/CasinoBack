import java.util.ArrayList;
import java.util.List;

public class Hand { // 'extends denek' kısmını sildik, gerek yok.
    private final List<denek.Card> cards; // denek içindeki Card'ı kullanıyoruz

    public Hand() {
        this.cards = new ArrayList<>();
    }

    // Yeni kart ekleme
    public void addCard(denek.Card card) {
        this.cards.add(card);
    }

    public List<denek.Card> getCards() {
        return cards;
    }

    public int getCardCount() {
        return cards.size();
    }

    // Split Kontrolü: 2 kart olmalı ve puan değerleri aynı olmalı
    public boolean canSplit() {
        return cards.size() == 2 && 
               cards.get(0).getBaseValue() == cards.get(1).getBaseValue();
    }

    // İkinci kartı ayırıp döndürür (Split için)
    public denek.Card removeCard() {
        if (cards.size() < 2) {
            throw new IllegalStateException("Bölünecek kart yok!");
        }
        return cards.remove(1); 
    }

    // Puan Hesaplama ve AS (Ace) Mantığı
    public int calculateScore() {
        int total = 0;
        int aceCount = 0;

        for (denek.Card card : cards) {
            total += card.getBaseValue();
            // Card denek içinde olduğu için denek.Rank.ACE şeklinde kontrol ediyoruz
            if (card.getRank() == denek.Rank.ACE) {
                aceCount++;
            }
        }

        // Skor 21'i geçerse As'ları 11'den 1'e düşür
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }
    
    // Test amaçlı: Eli tamamen temizleme (Her yeni turda gerekebilir)
    public void clear() {
        cards.clear();
    }
}