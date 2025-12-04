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

import de.ea234.util.FkSystem;

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
   * Nr 0 From 13 (1) To 0 (9)
   * Nr 1 From 14 (1) To 1 (8)
   * calcJoltage( "987654321111111" ) = 98
   * 
   * Nr 0 From 13 (1) To 0 (8)
   * Nr 1 From 14 (9) To 14 (9)
   * calcJoltage( "811111111111119" ) = 89
   * 
   * Nr 0 From 13 (7) To 13 (7)
   * Nr 1 From 14 (8) To 14 (8)
   * calcJoltage( "234234234234278" ) = 78
   * 
   * Nr 0 From 13 (1) To 6 (9)
   * Nr 1 From 14 (1) To 11 (2)
   * calcJoltage( "818181911112111" ) = 92
   * 
   * 
   * sum_joltages 357
   * 
   * 
   * ------------------------------------------------------------------------
   * 
   * Nr 0 From 3 (6) To 0 (9)
   * Nr 1 From 4 (5) To 1 (8)
   * Nr 2 From 5 (4) To 2 (7)
   * Nr 3 From 6 (3) To 3 (6)
   * Nr 4 From 7 (2) To 4 (5)
   * Nr 5 From 8 (1) To 5 (4)
   * Nr 6 From 9 (1) To 6 (3)
   * Nr 7 From 10 (1) To 7 (2)
   * Nr 8 From 11 (1) To 8 (1)
   * Nr 9 From 12 (1) To 9 (1)
   * Nr 10 From 13 (1) To 10 (1)
   * Nr 11 From 14 (1) To 11 (1)
   * calcJoltage( "987654321111111" ) = 987654321111
   * 
   * Nr 0 From 3 (1) To 0 (8)
   * Nr 1 From 4 (1) To 1 (1)
   * Nr 2 From 5 (1) To 2 (1)
   * Nr 3 From 6 (1) To 3 (1)
   * Nr 4 From 7 (1) To 4 (1)
   * Nr 5 From 8 (1) To 5 (1)
   * Nr 6 From 9 (1) To 6 (1)
   * Nr 7 From 10 (1) To 7 (1)
   * Nr 8 From 11 (1) To 8 (1)
   * Nr 9 From 12 (1) To 9 (1)
   * Nr 10 From 13 (1) To 10 (1)
   * Nr 11 From 14 (9) To 14 (9)
   * calcJoltage( "811111111111119" ) = 811111111119
   * 
   * Nr 0 From 3 (2) To 2 (4)
   * Nr 1 From 4 (3) To 4 (3)
   * Nr 2 From 5 (4) To 5 (4)
   * Nr 3 From 6 (2) To 6 (2)
   * Nr 4 From 7 (3) To 7 (3)
   * Nr 5 From 8 (4) To 8 (4)
   * Nr 6 From 9 (2) To 9 (2)
   * Nr 7 From 10 (3) To 10 (3)
   * Nr 8 From 11 (4) To 11 (4)
   * Nr 9 From 12 (2) To 12 (2)
   * Nr 10 From 13 (7) To 13 (7)
   * Nr 11 From 14 (8) To 14 (8)
   * calcJoltage( "234234234234278" ) = 434234234278
   * 
   * Nr 0 From 3 (1) To 0 (8)
   * Nr 1 From 4 (8) To 2 (8)
   * Nr 2 From 5 (1) To 4 (8)
   * Nr 3 From 6 (9) To 6 (9)
   * Nr 4 From 7 (1) To 7 (1)
   * Nr 5 From 8 (1) To 8 (1)
   * Nr 6 From 9 (1) To 9 (1)
   * Nr 7 From 10 (1) To 10 (1)
   * Nr 8 From 11 (2) To 11 (2)
   * Nr 9 From 12 (1) To 12 (1)
   * Nr 10 From 13 (1) To 13 (1)
   * Nr 11 From 14 (1) To 14 (1)
   * calcJoltage( "818181911112111" ) = 888911112111
   * 
   * 
   * sum_joltages 3121910778619
   * 
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

  public static void startListCalcJoltage( List< String > pList, int pDigitAmount, boolean pKnzDebug )
  {
    if ( pList == null )
    {
      wl( "pList == null" );

      return;
    }

    long time_start = System.currentTimeMillis();

    BigDecimal sum_joltage = BIG_DECIMAL_0;

    for ( int list_index = 0; list_index < pList.size(); list_index++ )
    {
      String list_string = pList.get( list_index );

      sum_joltage = sum_joltage.add( calcJoltage( list_string, pDigitAmount, pKnzDebug ) );
    }

    long time_end = System.currentTimeMillis();

    long time_diff = time_end - time_start;

    wl( "" );
    wl( "sum_joltages " + sum_joltage.toPlainString() );
    wl( "" );
    wl( "Runtime " + pList.size() + " Input Lines = " + FkSystem.getDebugRuntime( time_diff ) );
    wl( "" );
  }

  private static void startTestCalcJoltage()
  {
    calcJoltage( "8119", 2, true );
    calcJoltage( "987654321111111", 12, true );
  }

  private static BigDecimal calcJoltage( String pString, int pDigitAmount, boolean pKnzDebug )
  {
    int str_length = pString.length();

    BigDecimal joltage = BIG_DECIMAL_0;

    int index_previous = -1;

    for ( int digit_index = 0; digit_index < pDigitAmount; digit_index++ )
    {
      int index_current = str_length - ( pDigitAmount - digit_index );

      int index_calculated = calculateIndex( pString, index_current, index_previous );

      int number_1_value = ( (int) pString.charAt( index_calculated ) ) - 48;

      if ( pKnzDebug )
      {
        int number_1_start = ( (int) pString.charAt( index_current ) ) - 48;

        wl( "Nr " + digit_index + " From " + index_current + " (" + number_1_start + ") To " + index_calculated + " (" + number_1_value + ")" );
      }

      joltage = joltage.multiply( BIG_DECIMAL_10 ).add( new BigDecimal( number_1_value ) );

      index_previous = index_calculated;
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
     * The value from the parameter pIndexStart is returned.
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
     * The value from the parameter pIndexStart is returned.
     */
    int index_search = pIndexStart - 1;

    if ( index_search < 0 )
    {
      return pIndexStart;
    }

    /*
     * The function result is initially set to the start-position.
     */
    int index_result = pIndexStart;

    /*
     * The start value for the best number, starts with the 
     * number found at the start index.
     */
    int number_best_value = ( ( (int) pString.charAt( pIndexStart ) ) - 48 );

    /*
     * Searching a better value.
     * The better value, can be higher then the current value, or it 
     * can be a better index-position in the string (if the values are equal).
     */
    while ( index_search >= end_index_caculated )
    {
      int number_act_value = ( ( (int) pString.charAt( index_search ) ) - 48 );

      if ( number_act_value >= number_best_value )
      {
        number_best_value = number_act_value;

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
