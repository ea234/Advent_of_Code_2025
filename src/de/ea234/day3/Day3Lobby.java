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
   * 
   * What is the new total output joltage?
   *
   * sum_joltages 17155
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
   */

  public static void main( String[] args )
  {
    String test_content = "987654321111111,811111111111119,234234234234278,818181911112111";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    checkCalcJoltagePart1( getListProd() );

    checkCalcJoltagePart1( test_content_list );

    startTestCalcJoltage();
  }

  public static void checkCalcJoltagePart1( List< String > pList )
  {
    if ( pList == null )
    {
      wl( "pList == null" );

      return;
    }

    long sum_joltage = 0;

    for ( int rotation_list_index = 0; rotation_list_index < pList.size(); rotation_list_index++ )
    {
      String range_string = pList.get( rotation_list_index );

      sum_joltage += testCalcJoltage( range_string );
    }

    wl( "" );
    wl( "sum_joltages " + sum_joltage );
    wl( "" );
    wl( "" );
  }

  private static void startTestCalcJoltage()
  {
    testCalcJoltage( "111111111111111119" );
  }

  private static int testCalcJoltage( String pString )
  {
    int joltage = calcJoltage( pString );

    wl( "calcJoltage( \"" + pString + "\" ) = " + joltage );

    return joltage;
  }

  private static int calcJoltage( String pString )
  {
    int joltage = 0;

    if ( pString != null )
    {
      int str_length = pString.length();

      int number_act_index = 0;

      int number_1_index = -1;
      int number_1_value = -1;

      int number_2_index = -1;
      int number_2_value = -1;

      while ( number_act_index < str_length )
      {
        /*
         * Die aktuelle Nummer aus der Eingabe holen 
         */
        int number_act_value = ( ( (int) pString.charAt( number_act_index ) ) - 48 );

        /*
         * Ist die aktuelle Zahl am Leseindex größer als die Zahl am Index 1,
         * setze die aktuelle Zahl am Leseindex als neue Zahl am Index 1.
         * 
         * Sollte die Zahl am Index 2 jedoch kleiner sein, als die aktuelle Zahl am Index 1, 
         * muss die jetzige Zahl am Index 1 gespeichert werden.
         * 
         * Groesser gleich muss es sein, weil in zahl 2 eine kleinere Zahl stehen kann. 
         */

        /*
         * Bis zur zweitletzten Position kann noch der erste Index gesetzt werden. 
         * Der letzte Wert kann nur noch der Index 2 Wert sein.
         */
        if ( ( number_act_index < str_length - 1 ) && ( number_act_value >= number_1_value ) )
        {
          if ( number_act_value == number_1_value )
          {
            number_2_index = number_1_index;

            number_2_value = number_1_value;
          }
          else
          {
            number_2_index = -1;

            number_2_value = -1;
          }

          /*
           * Set the new number 1
           */
          number_1_index = number_act_index;

          number_1_value = number_act_value;
        }
        else if ( number_act_value >= number_2_value )
        {
          /*
           * We found a higher number for index 2. 
           * The new number is less than the number 1, but 
           * heigher than the current number 2.
           */
          number_2_index = number_act_index;

          number_2_value = number_act_value;
        }

        number_act_index++;
      }

      if ( number_1_index == number_2_index )
      {
        wl( "Fehler Index gleich" );
      }

      /*
       * Es muss ueber den index verglichen werden
       */
      if ( number_1_index < number_2_index )
      {
        wl( "\nA 1 " + number_1_value + " " + number_1_index );
        wl( "A 2 " + number_2_value + " " + number_2_index );

        joltage = ( number_1_value * 10 ) + number_2_value;
      }
      else
      {
        wl( "\nB 2 " + number_2_value + " " + number_2_index );
        wl( "B 1 " + number_1_value + " " + number_1_index );

        joltage = ( number_2_value * 10 ) + number_1_value;
      }
    }

    return joltage;
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

  private static List< String > getListProd1()
  {
    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day2_input.txt";

    try
    {
      String content = Files.readString( Path.of( datei_input ) );

      List< String > list_string = Arrays.stream( content.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

      return list_string;
    }
    catch ( IOException error_inst )
    {
      error_inst.printStackTrace();
    }

    return null;
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
