package de.ea234.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3Lobby
{
  /*
   * --- Day 3: Lobby ---
   * https://adventofcode.com/2025/day/3
   *
   * You descend a short staircase, enter the surprisingly
   * vast lobby, and are quickly cleared by the security checkpoint.
   * When you get to the main elevators, however, you discover
   * that each one has a red light above it: they're all offline.
   * 
   * "Sorry about that," an Elf apologizes as she tinkers with
   * a nearby control panel. "Some kind of electrical surge
   * seems to have fried them. I'll try to get them online soon."
   * 
   * You explain your need to get further underground. "Well,
   * you could at least take the escalator down to the printing
   * department, not that you'd get much further than that without
   * the elevators working. That is, you could if the escalator
   * weren't also offline."
   * 
   * "But, don't worry! It's not fried; it just needs power.
   * Maybe you can get it running while I keep working on the
   * elevators."
   * 
   * There are batteries nearby that can supply emergency power
   * to the escalator for just such an occasion. The batteries
   * are each labeled with their joltage rating, a value from
   * 1 to 9. You make a note of their joltage ratings (your
   * puzzle input). For example:
   * 
   * 987654321111111
   * 811111111111119
   * 234234234234278
   * 818181911112111
   * 
   * The batteries are arranged into banks; each line of digits
   * in your input corresponds to a single bank of batteries.
   * Within each bank, you need to turn on exactly two batteries;
   * the joltage that the bank produces is equal to the number
   * formed by the digits on the batteries you've turned on.
   * For example, if you have a bank like 12345 and you turn
   * on batteries 2 and 4, the bank would produce 24 jolts.
   * (You cannot rearrange batteries.)
   * 
   * You'll need to find the largest possible joltage each
   * bank can produce. In the above example:
   * 
   * In 987654321111111, you can make the largest joltage
   * possible, 98, by turning on the first two batteries.
   * 
   * In 811111111111119, you can make the largest joltage
   * possible by turning on the batteries labeled 8 and 9, 
   * producing 89 jolts.
   * 
   * In 234234234234278, you can make 78 by turning on
   * the last two batteries (marked 7 and 8).
   * 
   * In 818181911112111, the largest joltage you can produce is 92.
   * 
   * The total output joltage is the sum of the maximum joltage
   * from each bank, so in this example, the total output joltage
   * is 98 + 89 + 78 + 92 = 357.
   * 
   * There are many batteries in front of you. Find the maximum
   * joltage possible from each bank; what is the total output
   * joltage?
   * 
   * sum_ranges 17155
   * 
   * 
   * --- Part Two -----------------------------------------------------------
   * 
   * The escalator doesn't move. The Elf explains that it probably
   * needs more joltage to overcome the static friction of the
   * system and hits the big red "joltage limit safety override"
   * button. You lose count of the number of times she needs
   * to confirm "yes, I'm sure" and decorate the lobby a bit
   * while you wait.
   * 
   * Now, you need to make the largest joltage by turning on
   * exactly twelve batteries within each bank.
   * 
   * The joltage output for the bank is still the number formed
   * by the digits of the batteries you've turned on; the only
   * difference is that now there will be 12 digits in each
   * bank's joltage output instead of two.
   * 
   * Consider again the example from before:
   * 987654321111111
   * 811111111111119
   * 234234234234278
   * 818181911112111
   * 
   * Now, the joltages are much larger:
   * 
   * In 987654321111111, the largest joltage can be found
   * by turning on everything except some 1s at the end to 
   * produce 987654321111.
   * 
   * In the digit sequence 811111111111119, the largest
   * joltage can be found by turning on everything except some
   * 1s, producing 811111111119.
   *     
   * In 234234234234278, the largest joltage can be found
   * by turning on everything except a 2 battery, a 3 battery,
   * and another 2 battery near the start to produce 434234234278.
   *
   * In 818181911112111, the joltage 888911112111 is produced
   * by turning on everything except some 1s near the front.
   * 
   * The total output joltage is now much larger: 
   * 
   * 987654321111 + 811111111119 + 434234234278 + 888911112111 = 3121910778619.
   *                                                             3121910778619
   *  
   * What is the new total output joltage?
   *
   * sum_joltages 169685670469164
   * 
   * 
   * ------------------------------------------------------------------------
   * 
   * A 1 9 0
   * A 2 8 1
   * calcJoltage( "987654321111111" ) = 98
   * 
   * A 1 8 0
   * A 2 9 14
   * calcJoltage( "811111111111119" ) = 89
   * 
   * A 1 7 13
   * A 2 8 14
   * calcJoltage( "234234234234278" ) = 78
   * 
   * A 1 9 6
   * A 2 2 11
   * calcJoltage( "818181911112111" ) = 92
   * 
   * sum_joltages 357
   * 
   * 
   * ------------------------------------------------------------------------
   * 
   * 
   * nr 0 von 3 (6) nach 0 (9)
   * nr 1 von 4 (5) nach 1 (8)
   * nr 2 von 5 (4) nach 2 (7)
   * nr 3 von 6 (3) nach 3 (6)
   * nr 4 von 7 (2) nach 4 (5)
   * nr 5 von 8 (1) nach 5 (4)
   * nr 6 von 9 (1) nach 6 (3)
   * nr 7 von 10 (1) nach 7 (2)
   * nr 8 von 11 (1) nach 8 (1)
   * nr 9 von 12 (1) nach 9 (1)
   * nr 10 von 13 (1) nach 10 (1)
   * nr 11 von 14 (1) nach 11 (1)
   * calcJoltage( "987654321111111" ) = 987654321111
   *                                    987654321111
   * 
   * 
   * nr 0 von 3 (1) nach 0 (8)
   * nr 1 von 4 (1) nach 1 (1)
   * nr 2 von 5 (1) nach 2 (1)
   * nr 3 von 6 (1) nach 3 (1)
   * nr 4 von 7 (1) nach 4 (1)
   * nr 5 von 8 (1) nach 5 (1)
   * nr 6 von 9 (1) nach 6 (1)
   * nr 7 von 10 (1) nach 7 (1)
   * nr 8 von 11 (1) nach 8 (1)
   * nr 9 von 12 (1) nach 9 (1)
   * nr 10 von 13 (1) nach 10 (1)
   * nr 11 von 14 (9) nach 14 (9)
   * calcJoltage( "811111111111119" ) = 811111111119
   *                                    811111111119
   * nr 0 von 3 (2) nach 2 (4)
   * nr 1 von 4 (3) nach 4 (3)
   * nr 2 von 5 (4) nach 5 (4)
   * nr 3 von 6 (2) nach 6 (2)
   * nr 4 von 7 (3) nach 7 (3)
   * nr 5 von 8 (4) nach 8 (4)
   * nr 6 von 9 (2) nach 9 (2)
   * nr 7 von 10 (3) nach 10 (3)
   * nr 8 von 11 (4) nach 11 (4)
   * nr 9 von 12 (2) nach 12 (2)
   * nr 10 von 13 (7) nach 13 (7)
   * nr 11 von 14 (8) nach 14 (8)
   * calcJoltage( "234234234234278" ) = 434234234278
   *                                    434234234278
   * nr 0 von 3 (1) nach 0 (8)
   * nr 1 von 4 (8) nach 2 (8)
   * nr 2 von 5 (1) nach 4 (8)
   * nr 3 von 6 (9) nach 6 (9)
   * nr 4 von 7 (1) nach 7 (1)
   * nr 5 von 8 (1) nach 8 (1)
   * nr 6 von 9 (1) nach 9 (1)
   * nr 7 von 10 (1) nach 10 (1)
   * nr 8 von 11 (2) nach 11 (2)
   * nr 9 von 12 (1) nach 12 (1)
   * nr 10 von 13 (1) nach 13 (1)
   * nr 11 von 14 (1) nach 14 (1)
   * calcJoltage( "818181911112111" ) = 888911112111
   *                                    888911112111
   */

  public static final BigDecimal BIG_DECIMAL_0  = new BigDecimal( "0" );

  public static final BigDecimal BIG_DECIMAL_10 = new BigDecimal( "10" );

  public static void main( String[] args )
  {
    String test_content = "987654321111111,811111111111119,234234234234278,818181911112111";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    startListCalcJoltage( getListProd(), 2, false );
    startListCalcJoltage( getListProd(), 12, false );

    startListCalcJoltage( test_content_list, 2, true );
    startListCalcJoltage( test_content_list, 12, true );

    //startTestCalcJoltage();
  }

  public static void startListCalcJoltage( List< String > pList, int pAmountOfNumbers, boolean pKnzDebug )
  {
    if ( pList == null )
    {
      wl( "pList == null" );

      return;
    }

    BigDecimal sum_joltage = BIG_DECIMAL_0;

    for ( int list_index = 0; list_index < pList.size(); list_index++ )
    {
      String list_string = pList.get( list_index );

      BigDecimal sum_jol2tage = calcJoltage( list_string, pAmountOfNumbers, pKnzDebug );

      sum_joltage = sum_joltage.add( sum_jol2tage );
    }

    wl( "" );
    wl( "sum_joltages " + sum_joltage.toPlainString() );
    wl( "" );
    wl( "" );
  }

  private static void startTestCalcJoltage()
  {
    calcJoltage( "8119", 2, true );
    calcJoltage( "987654321111111", 12, true );
    calcJoltage( "981234567890000000", 12, true );
  }

  private static BigDecimal calcJoltage( String pString, int pAmountOfNumbers, boolean pKnzDebug )
  {
    int str_length = pString.length();

    /*
     * The List is not really neccessary.
     */
    List< Integer > indexList = new ArrayList<>();

    for ( int i = pAmountOfNumbers; i > 0; i-- )
    {
      indexList.add( new Integer( str_length - i ) );
    }

    BigDecimal joltage = BIG_DECIMAL_0;

    for ( int i = 0; i < indexList.size(); i++ )
    {
      int index_previous = ( i > 0 ) ? ( (Integer) indexList.get( i - 1 ) ).intValue() : -1;

      int index_current = ( (Integer) indexList.get( i ) ).intValue();

      int index_calculated = calculateIndex( pString, index_current, index_previous );

      indexList.set( i, new Integer( index_calculated ) );

      int number_1_value = ( (int) pString.charAt( index_calculated ) ) - 48;

      if ( pKnzDebug )
      {
        int number_1_start = ( (int) pString.charAt( index_current ) ) - 48;

        wl( "nr " + i + " From " + index_current + " (" + number_1_start + ") To " + index_calculated + " (" + number_1_value + ")" );
      }

      joltage = joltage.multiply( BIG_DECIMAL_10 ).add( new BigDecimal( number_1_value ) );
    }

    if ( pKnzDebug )
    {
      wl( "calcJoltage( \"" + pString + "\" ) = " + joltage.toPlainString() );
      wl( "" );
    }

    return joltage;
  }

  private static int calculateIndex( String pString, int pIndexStart, int pIndexEnd )
  {
    /*
     * The value in pIndexEnd is the Index of the previous element and 
     * must not be included in this search. 
     * 
     * Since the search order is from the highest to the lowest Index, 
     * this search ends one Index to the left.
     */
    int end_index_caculated = pIndexEnd + 1;

    /*
     * If the calculated end index is equal to the start index, than 
     * the start index can't be moved to another position. 
     * 
     * The search for a better position is over. 
     * The value from the parameter pIndexStart ist returned.
     */
    if ( end_index_caculated == pIndexStart )
    {
      return pIndexStart;
    }

    /*
     * The search for a better position starts one index towards 
     * the beginning of the string. 
     * 
     * If the search index is less than zero, than the start index 
     * can't be moved to another position. 
     * 
     * The search for a better position is over. 
     * The value from the parameter pIndexStart ist returned.
     */
    int index_search = pIndexStart - 1;

    if ( index_search < 0 )
    {
      return pIndexStart;
    }

    int index_result = pIndexStart;

    /*
     * The start value for the best number, starts with the 
     * number found at the start index.
     */
    int number_best = ( ( (int) pString.charAt( pIndexStart ) ) - 48 );

    while ( index_search >= end_index_caculated )
    {
      int number_act_value = ( ( (int) pString.charAt( index_search ) ) - 48 );

      if ( number_act_value >= number_best )
      {
        number_best = number_act_value;

        index_result = index_search;
      }

      index_search--;
    }

    return index_result;
  }

  private static List< String > getListProd()
  {
    int anzahl_zeilen_gelesen = 0;

    List< String > zeilenArrays = new ArrayList();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day3_input.txt";

    try (BufferedReader br = new BufferedReader( new FileReader( datei_input ) ))
    {
      String zeile;

      while ( ( zeile = br.readLine() ) != null )
      {
        zeile = zeile.trim();

        zeilenArrays.add( zeile );

        anzahl_zeilen_gelesen++;

        wl( "File Row " + zeile );
      }
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }

    wl( "File Row Count " + anzahl_zeilen_gelesen + " " + zeilenArrays.size() );

    return zeilenArrays;
  }

  /**
   * Ausgabe auf System.out
   * 
   * @param pString der auszugebende String
   */
  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
