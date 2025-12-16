public class BlackjackGame extends Hand {

	
		private final Deck deck = new Deck();
		private final Hand playerHand = new Hand();
		private final Hand dealerHand = new Hand();

		public void startRound() {
			System.out.println("--- YENİ TUR BAŞLIYOR ---");

			// Gerekirse desteyi sıfırla ve karıştır (Deck sınıfındaki metodu kullan)
			deck.resetAndShuffle();

			// Oyuncuya 2 kart dağıt
			dealInitialCards();

			System.out.println("Oyuncu Elindeki Kartlar: " + playerHand.getCards());
			System.out.println("Kasa Elindeki Kartlar: " + dealerHand.getCards());
		}

		private void dealInitialCards() {
			// 1. Kartı oyuncuya dağıt
			Card card1 = deck.dealCard();
			playerHand.addCard(card1);

			// 2. Kartı kasaya dağıt
			Card card2 = deck.dealCard();
			dealerHand.addCard(card2);

			// 3. Kartı oyuncuya dağıt
			Card card3 = deck.dealCard();
			playerHand.addCard(card3);

			// 4. Kartı kasaya dağıt
			Card card4 = deck.dealCard();
			dealerHand.addCard(card4);
		}

		
	}