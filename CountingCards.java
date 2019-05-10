/**
 * The CountingCards program reads a text file and interprets the card hands stored in the file. The input is tested to check its validity, and stored into
 * an appropriate String ArrayList based on its suit. The program also calculates the number of points in a card hand, and outputs the point and card
 * information of each hand in descending order.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 19.04.03
 * @author Juleen Chen
 *
 * Variable name       Type                Description
 * CARDS               final String        A list of all the valid card values. The indices of these values are used to sort the hands in descending order.
 * nextline            String              Stores the next line of input from the file.
 * input               Scanner             Global Input Stream.
 * spades              ArrayList<String>   Stores the cards with the spades suit.
 * hearts              ArrayList<String>   Stores the cards with the hearts suit.
 * diamonds            ArrayList<String>   Stores the cards with the diamonds suit.
 * clubs               ArrayList<String>   Stores the cards with the clubs suit.
 * hands               ArrayList<String>   Stores all the information read from the file.
 * card                String              Stores the current card's face value.
 * suit                String              Stores the current card's suit.
 * points              int                 Stores the total amount of points for the hand.
 * fileName            String              Name of the file to be read from.
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class CountingCards
{
    //Global variable declaration
    static final String CARDS = "AKQJT98765432";
    static String nextline;
    static Scanner input;
    ArrayList < String > spades;
    ArrayList < String > hearts;
    ArrayList < String > diamonds;
    ArrayList < String > clubs;
    ArrayList < String > hands;
    String card;
    String suit;
    int points;
    String fileName;

    /**
     * The class constructor passes a String fileName to determine which file to read from and initialises the point value
     * as 0. The five array lists are initialised here. The constructor also calls the process method.
     *
     * @param fn This is the file name.
     */
    public CountingCards (String fn)
    {
        fileName = fn;
        points = 0;
        hands = new ArrayList < String > ();
        spades = new ArrayList < String > ();
        hearts = new ArrayList < String > ();
        diamonds = new ArrayList < String > ();
        clubs = new ArrayList < String > ();
        process ();
    }


    /**
     * The process method reads information from the file and stores it into a String ArrayList. The String is then checked to make sure
     * that it is a valid hand. Point values are then calculated for the cards in the hand. The cards in the hand are then sorted into their
     * appropriate suits. The sort method is then called to organise the ArrayList for each suit into descending order. The information is then
     * outputted to the user. The process repeats until there are no more hands to read or when there is an invalid hand.
     */
    public void process ()
    {
        //When the bridge hand is invalid, the program catches an IllegalArgumentException and displays an error message.
        try
        {
            //Get the user's input
            File file = new File (fileName);
            Scanner input = new Scanner (file);

            //read from file input to the ArrayList
            while (input.hasNextLine ())
            {
                hands.add (input.nextLine ());
            }

            //repeats until there are no hands left to read
            for (String next:
            hands)
            {

                //reinitialise values for each hand
                nextline = next;
                points = 0;
                spades.clear ();
                hearts.clear ();
                diamonds.clear ();
                clubs.clear ();

                //If the length isn't 26, it's an automatic error.
                if (next.length () != 26)
                {
                    throw new IllegalArgumentException ();
                }

                //Processes each individual card in the hand
                for (int i = 0 ; i < next.length () ; i += 2)
                {
                    card = next.charAt (i) + "";

                    //Check if the card is a valid card value
                    if (!CARDS.contains (card))
                    {
                        throw new IllegalArgumentException ();
                    }

                    //Gives points based on card values
                    if (card.equals ("A"))
                    {
                        points += 4;
                    }
                    else if (card.equals ("K"))
                    {
                        points += 3;
                    }
                    else if (card.equals ("Q"))
                    {
                        points += 2;
                    }
                    else if (card.equals ("J"))
                    {
                        points++;
                    }

                    //Gets the suit for the card
                    suit = next.charAt (i + 1) + "";

                    //Assigns the card to its suit's ArrayList
                    if (suit.equals ("S"))
                    {
                        spades.add (card);
                    }
                    else if (suit.equals ("H"))
                    {
                        hearts.add (card);
                    }
                    else if (suit.equals ("D"))
                    {
                        diamonds.add (card);
                    }
                    else if (suit.equals ("C"))
                    {
                        clubs.add (card);
                    }
                    else
                    {
                        throw new IllegalArgumentException ();
                    }
                }

                //Adds points for doubleton, singleton, and void for every suit
                if (spades.isEmpty ())
                {
                    points += 3;
                }
                else if (spades.size () == 1)
                {
                    points += 2;
                }
                else if (spades.size () == 2)
                {
                    points++;
                }
                if (hearts.isEmpty ())
                {
                    points += 3;
                }
                else if (hearts.size () == 1)
                {
                    points += 2;
                }
                else if (hearts.size () == 2)
                {
                    points++;
                }
                if (diamonds.isEmpty ())
                {
                    points += 3;
                }
                else if (diamonds.size () == 1)
                {
                    points += 2;
                }
                else if (diamonds.size () == 2)
                {
                    points++;
                }
                if (clubs.isEmpty ())
                {
                    points += 3;
                }
                else if (clubs.size () == 1)
                {
                    points += 2;
                }
                else if (clubs.size () == 2)
                {
                    points++;
                }

                //Sorts the ArrayLists and outputs them in descending order
                sort (spades);
                sort (hearts);
                sort (diamonds);
                sort (clubs);
                System.out.println ("Your hand contains " + points + " points.");

                //Makes sure the output is readable to users by substituting letters for the common names and adding spacing
                System.out.print ("Spades: ");
                if (spades.isEmpty ())
                {
                    System.out.print ("void");
                }
                for (String s:
                spades)
                {
                    if (s.equals ("A"))
                    {
                        System.out.print ("ace ");
                    }
                    else if (s.equals ("K"))
                    {
                        System.out.print ("king ");
                    }
                    else if (s.equals ("Q"))
                    {
                        System.out.print ("queen ");
                    }
                    else if (s.equals ("J"))
                    {
                        System.out.print ("jack ");
                    }
                    else if (s.equals ("T"))
                    {
                        System.out.print ("10 ");
                    }
                    else
                    {
                        System.out.print (s + " ");
                    }
                }
                System.out.println ();
                System.out.print ("Hearts: ");
                if (hearts.isEmpty ())
                {
                    System.out.print ("void");
                }
                for (String s:
                hearts)
                {
                    if (s.equals ("A"))
                    {
                        System.out.print ("ace ");
                    }
                    else if (s.equals ("K"))
                    {
                        System.out.print ("king ");
                    }
                    else if (s.equals ("Q"))
                    {
                        System.out.print ("queen ");
                    }
                    else if (s.equals ("J"))
                    {
                        System.out.print ("jack ");
                    }
                    else if (s.equals ("T"))
                    {
                        System.out.print ("10 ");
                    }
                    else
                    {
                        System.out.print (s + " ");
                    }
                }
                System.out.println ();
                System.out.print ("Diamonds: ");
                if (diamonds.isEmpty ())
                {
                    System.out.print ("void");
                }
                for (String s:
                diamonds)
                {
                    if (s.equals ("A"))
                    {
                        System.out.print ("ace ");
                    }
                    else if (s.equals ("K"))
                    {
                        System.out.print ("king ");
                    }
                    else if (s.equals ("Q"))
                    {
                        System.out.print ("queen ");
                    }
                    else if (s.equals ("J"))
                    {
                        System.out.print ("jack ");
                    }
                    else if (s.equals ("T"))
                    {
                        System.out.print ("10 ");
                    }
                    else
                    {
                        System.out.print (s + " ");
                    }
                }
                System.out.println ();
                System.out.print ("Clubs: ");
                if (clubs.isEmpty ())
                {
                    System.out.print ("void");
                }
                for (String s:
                clubs)
                {
                    if (s.equals ("A"))
                    {
                        System.out.print ("ace ");
                    }
                    else if (s.equals ("K"))
                    {
                        System.out.print ("king ");
                    }
                    else if (s.equals ("Q"))
                    {
                        System.out.print ("queen ");
                    }
                    else if (s.equals ("J"))
                    {
                        System.out.print ("jack ");
                    }
                    else if (s.equals ("T"))
                    {
                        System.out.print ("10 ");
                    }
                    else
                    {
                        System.out.print (s + " ");
                    }
                }
                System.out.println ();
                System.out.println ();
            }
        }
        catch (Exception e)
        {
            System.out.println ("\"" + nextline + "\" is not a valid hand.");
        }
    }

   /** 
    * The main method creates a new CountingCards object.
    * 
    * @param args [ ]  String array that allows command line parameters to be used when executing the program.
    */ 
    public static void main (String[] args)
    {
        CountingCards c = new CountingCards ("Cards.txt");
    }


    /**
     * The sort method uses insertion sort to sort an ArrayList of card values into the order given in the CARDS String.
     *
     * @param arr This is the String ArrayList being sorted.
     */
    public static void sort (ArrayList < String > arr)
    {
        int temp = -1;
        int i;

        //Loops through all the cards in the arraylist
        for (int k = 1 ; k < arr.size () ; k++)
        {
            //Stores the value for the current card (because it will get overwritten)
            temp = CARDS.indexOf (arr.get (k));

            //Goes through every card in front of temp and "smaller" than temp (smaller is determined by if its index in CARDS is bigger, it's "smaller")
            //and moves them back. This will end up sorting the array in descending order.
            for (i = k ; i > 0 && CARDS.indexOf (arr.get (i - 1)) > temp ; i--)
            {
                arr.set (i, arr.get (i - 1));
            }
            arr.set (i, CARDS.substring (temp, temp + 1));
        }
    }
}
