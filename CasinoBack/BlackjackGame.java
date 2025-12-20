import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {
    private int bakiye = 1000; 
    private int bahis = 0;      
    
    private final denek.Deck deck = new denek.Deck();
    private final List<Hand> playerHands = new ArrayList<>();
    private final Hand dealerHand = new Hand();
    
    // Parametre olarak Scanner alıyoruz
    public void bahisAl(Scanner input) {
        while (true) {
            System.out.println("\nCüzdan: " + bakiye + " TL");
            System.out.print("Bahis miktarını girin: ");
            
            if (!input.hasNextInt()) { // Sayı dışında giriş kontrolü
                System.out.println("Lütfen sadece rakam girin!");
                input.nextLine();
                continue;
            }

            int miktar = input.nextInt();
            input.nextLine(); 

            if (miktar > 0 && miktar <= bakiye) {
                bahis = miktar;
                bakiye -= miktar;
                System.out.println(miktar + " TL bahis kabul edildi.");
                break;
            }
            System.out.println("Geçersiz miktar! Bakiyeniz: " + bakiye);
        }
    }

    public void play() {
        Scanner input = new Scanner(System.in); // Scanner'ı burada bir kez açıyoruz
        
        while (bakiye > 0) { // Bakiye bitene kadar oyun döner
            bahisAl(input); // Scanner'ı gönderdik
            
            deck.resetAndShuffle();
            playerHands.clear();
            dealerHand.clear(); // Hand sınıfına clear eklediysen kullan

            Hand initialHand = new Hand();
            initialHand.addCard(deck.dealCard());
            dealerHand.addCard(deck.dealCard());
            initialHand.addCard(deck.dealCard());
            dealerHand.addCard(deck.dealCard());
            playerHands.add(initialHand);

            boolean isBlackjack = (playerHands.get(0).getCardCount() == 2 && playerHands.get(0).calculateScore() == 21);

            // 1. OYUNCU SIRASI
            for (int i = 0; i < playerHands.size(); i++) {
                Hand currentHand = playerHands.get(i);
                boolean handFinished = false;
            
                while (!handFinished) {
                    int score = currentHand.calculateScore();
                    
                    System.out.println("\n--- EL " + (i + 1) + " ---");
                    System.out.println("Kartların: " + currentHand.getCards() + " | Puan: " + score);
                    System.out.println("Kasanın Görünen Kartı: " + dealerHand.getCards().get(0)
                    		+" | Puanı:  "+ dealerHand.getCards().get(0).getBaseValue() );

                    if (score > 21) {
                        System.out.println("BUST! Bu el battı.");
                        handFinished = true;
                        continue;
                    }

                    String menu = "(H)it, (S)tand";
                    if (currentHand.getCards().size() == 2) menu += ", (D)ouble";
                    if (currentHand.canSplit()) menu += ", (P)split";
                    
                    System.out.print("Seçimin " + menu + ": ");
                    String action = input.nextLine().toLowerCase();

                    switch (action) {
                        case "h":
                            currentHand.addCard(deck.dealCard());
                            break;
                        case "s":
                            handFinished = true;
                            break;
                        case "d":
                            if (currentHand.getCards().size() == 2) {
                                if (bakiye >= bahis) { // Bakiye kontrolü
                                    bakiye -= bahis;
                                    bahis *= 2;
                                    System.out.println("Bahis ikiye katlandı!");
                                    currentHand.addCard(deck.dealCard());
                                    System.out.println("Son kartın: " + currentHand.getCards().get(currentHand.getCards().size()-1));
                                    handFinished = true;
                                } else {
                                    System.out.println("Double için bakiyeniz yetersiz!");
                                }
                            }
                            break;
                        case "p":
                            if (currentHand.canSplit()) {
                                if (bakiye >= bahis) { // Bakiye kontrolü
                                    bakiye -= bahis;
                                    Hand newHand = new Hand();
                                    newHand.addCard(currentHand.removeCard());
                                    currentHand.addCard(deck.dealCard());
                                    newHand.addCard(deck.dealCard());
                                    playerHands.add(newHand);
                                    System.out.println("El bölündü!");
                                } else {
                                    System.out.println("Split için bakiyeniz yetersiz!");
                                }
                            }
                            break;
                    }
                }
            }

            // 2. KASA SIRASI (Senin akıllı döngün)
            int mainScore = playerHands.get(0).calculateScore();
            System.out.println("\n--- KASANIN SIRASI ---");
            // Kasa sadece oyuncu batmadıysa ve Blackjack değilse çeker
            while (dealerHand.calculateScore() < 17 && mainScore <= 21 && !isBlackjack) {
                dealerHand.addCard(deck.dealCard());
            }
            System.out.println("Kasa Kartları: " + dealerHand.getCards() + " | Puan: " + dealerHand.calculateScore());

            // 3. SONUÇLAR
            determineFinalResults();

            if (bakiye <= 0) {
                System.out.println("Bakiyeniz bitti! Kumarhaneden kovuldunuz.");
                break;
            }

            System.out.print("\nYeni tur oynamak ister misin? (E/H): ");
            if (!input.nextLine().equalsIgnoreCase("e")) break;
        }
        input.close(); 
    }

    private void determineFinalResults() {
        int dScore = dealerHand.calculateScore();
        System.out.println("\n=== TUR SONU VE ÖDEMELER ===");
        
        for (int i = 0; i < playerHands.size(); i++) {
            int pScore = playerHands.get(i).calculateScore();
            System.out.print("El " + (i + 1) + " (" + pScore + "): ");
            
            if (pScore > 21) {
                System.out.println("KAYBETTİN (Bust)");
            } else if (dScore > 21 || pScore > dScore) {
                System.out.println("KAZANDIN! + " + (bahis * 2) + " TL");
                bakiye += (bahis * 2); 
            } else if (dScore > pScore) {
                System.out.println("KAYBETTİN (Kasa Geçti)");
            } else {
                System.out.println("BERABERE (Push) + " + bahis + " TL");
                bakiye += bahis;
            }
        }
        System.out.println("Yeni Bakiyen: " + bakiye + " TL");
    }
}