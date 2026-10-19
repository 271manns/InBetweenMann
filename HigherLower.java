//--------------------------------------------------------------------
// HigherLower.java         by Dale/Joyce/Weems              Chapter 6
//
// Plays Higher or Lower? with user through command line interface. 
//--------------------------------------------------------------------
import java.util.Scanner;

public class HigherLower 
{
    public static void main(String[] args)
    {
        CardDeck gameDeck = new CardDeck();
        gameDeck.shuffle();
        int bankroll = 100;
        boolean playAgain = true;
        boolean validInput = false;
        int totalCards = 52;
        int bet = 0;
        Scanner sc = new Scanner(System.in);
        ArrayLog<Card> deck = new ArrayLog<>("Deck");
        bankroll = 100;
        totalCards = 52;
        gameDeck = new CardDeck();
        gameDeck.shuffle();
        for(int i = 0; i<52; i++)
        {
            Card c1 = gameDeck.nextCard();
            deck.add(c1);
        }
        System.out.println("Welcome to InBetween!");
        System.out.println("Your Starting bankroll is " + bankroll + " dollars, how much would you like to bet on the first round?");
        while(playAgain)
        {
            if(totalCards<10)
            {
                System.out.println("Deck needs to be Reshuffled");
                totalCards = 52;
                gameDeck = new CardDeck();
                gameDeck.shuffle();
                deck.clear();
                for(int i = 0; i<52; i++)
                {
                    Card c1 = gameDeck.nextCard();
                    deck.add(c1);
                }
            }
            
            System.out.println("Your balance is " + bankroll);
            if(bankroll == 0)
            {
                System.out.println("You are out of money. Game Ended");
                playAgain=false;
                break;
                
            }
            System.out.println("What is your bet?");
            validInput = false;
            while(!validInput)
            {
                if(!sc.hasNextInt())
                {
                    sc.next();
                    System.out.println("Enter an Integer");
                    validInput = false;
                }
                if(sc.hasNextInt())
                {
                    bet = sc.nextInt();
                    if(bet>bankroll)
                    {
                        System.out.println("Enter less than total Bankroll");
                    }
                    if(bet>=0 && bet<=bankroll)
                    {
                        validInput = true;
                    }
                }
                
            }
            System.out.println("Your bet is " + bet + " Dollars! Good Luck!");
            Card card1 = deck.get(0);
            deck.remove(0);
            Card card2 = deck.get(0);
            deck.remove(0);
            Card card3 = deck.get(0);
            deck.remove(0);
            totalCards -= 3;
            System.out.println("Card 1: " + card1.getRank());
            System.out.println("Card 2: " + card2.getRank());
            if(bet *2 <= bankroll && !(card1.getRank().equals(card2.getRank())))
            {
                System.out.println("Would you like to Double Down? Enter Yes or No");
                validInput = false;
                while(!validInput)
                {
                    String input = sc.next();
                    if(input.equalsIgnoreCase("yes"))
                    {
                        bet*=2;
                        validInput=true;
                    }
                    else if(input.equalsIgnoreCase("No"))
                    {
                        validInput=true;
                    }
                    else
                    {
                        System.out.println("Enter Valid Input");
                    }
                } 
            }
            System.out.println("Card 3 equals " + card3.getRank());
            if(card3.compareTo(card1)>0 && card3.compareTo(card2)<0
            || card3.compareTo(card1)<0 && card3.compareTo(card2)>0)
            {
                bankroll += bet;
                System.out.println("You Win! Your new Bankroll is " + bankroll + "!");
            }
            else if(card2.getRank().equals(card1.getRank()))
            {
                bankroll += bet;
                System.out.println("You Win! Your new Bankroll is " + bankroll + "!");
            }
            else
            {
                bankroll-=bet;
                System.out.println("You Lose. Your new Bankroll is " + bankroll);
            }
            validInput = false;
            System.out.println("Would you like to play Again?");
            while(!validInput)
            {
                String input = sc.next();
                if(input.equalsIgnoreCase("yes"))
                {
                    playAgain = true;
                    validInput = true;
                }
                else if(input.equalsIgnoreCase("No"))
                {
                    validInput = true;
                    playAgain= false;
                }
                else
                {
                    System.out.println("Enter Valid Input");
                }

                } 
                    
        }
        sc.close(); 
        System.out.println("Thanks For Playing!");  
    }

}
