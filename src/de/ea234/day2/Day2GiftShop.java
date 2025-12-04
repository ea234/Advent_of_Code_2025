package de.ea234.day2;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2GiftShop
{
  /*
   * --- Day 2: Gift Shop ---
   * https://adventofcode.com/2025/day/2
   * https://www.youtube.com/watch?v=bPMhQiVn6KI
   *
   * You get inside and take the elevator to its only other stop: the gift shop. "Thank
   * you for visiting the North Pole!" gleefully exclaims a nearby sign. You aren't sure
   * who is even allowed to visit the North Pole, but you know you can access the lobby
   * through here, and from there you can access the rest of the North Pole base.
   * 
   * As you make your way through the surprisingly extensive selection, one of the clerks
   * recognizes you and asks for your help.
   * 
   * As it turns out, one of the younger Elves was playing on a gift shop computer and
   * managed to add a whole bunch of invalid product IDs to their gift shop database!
   * Surely, it would be no trouble for you to identify the invalid product IDs for them,
   * right?
   * 
   * They've even checked most of the product ID ranges already; they only have a few
   * product ID ranges (your puzzle input) that you'll need to check. For example:
   * 
   * 11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
   * 1698522-1698528,446443-446449,38593856-38593862,565653-565659,
   * 824824821-824824827,2121212118-2121212124
   * 
   * (The ID ranges are wrapped here for legibility; in your input, they appear on a
   * single long line.)
   * 
   * The ranges are separated by commas (,); each range gives its first ID and last
   * ID separated by a dash (-).
   * 
   * Since the young Elf was just doing silly patterns, you can find the invalid IDs
   * by looking for any ID which is made only of some sequence of digits repeated twice.
   * So, 55 (5 twice), 6464 (64 twice), and 123123 (123 twice) would all be invalid IDs.
   * 
   * None of the numbers have leading zeroes; 0101 isn't an ID at all. (101 is a valid
   * ID that you would ignore.)
   * 
   * Your job is to find all of the invalid IDs that appear in the given ranges. In
   * the above example:
   * 
   *     11-22                    has two invalid IDs, 11 and 22.
   *     95-115                   has one invalid ID, 99.
   *     998-1012                 has one invalid ID, 1010.
   *     1188511880-1188511890    has one invalid ID, 1188511885.
   *     222220-222224            has one invalid ID, 222222.
   *     1698522-1698528          contains no invalid IDs.
   *     446443-446449            has one invalid ID, 446446.
   *     38593856-38593862        has one invalid ID, 38593859.
   * 
   *     The rest of the ranges contain no invalid IDs.
   *  
   * Adding up all the invalid IDs in this example produces 1227775554.
   * 
   * What do you get if you add up all of the invalid IDs?
   * sum_ranges 64215794229
   * 
   * 
   * --- Part Two -----------------------------------------------------------
   * 
   * The clerk quickly discovers that there are still invalid IDs in the ranges in your
   * list. Maybe the young Elf was doing other silly patterns as well?
   * 
   * Now, an ID is invalid if it is made only of some sequence of digits repeated at
   * least twice. So, 12341234 (1234 two times), 123123123 (123 three times), 1212121212
   * (12 five times), and 1111111 (1 seven times) are all invalid IDs.
   * 
   * From the same example as before:
   *     11-22                 still has two invalid IDs, 11 and 22.
   *     95-115                now has two invalid IDs, 99 and 111.
   *     998-1012              now has two invalid IDs, 999 and 1010.
   *     1188511880-1188511890 still has one invalid ID, 1188511885.
   *     222220-222224         still has one invalid ID, 222222.
   *     1698522-1698528       still contains no invalid IDs.
   *     446443-446449         still has one invalid ID, 446446.
   *     38593856-38593862     still has one invalid ID, 38593859.
   *     565653-565659         now has one invalid ID, 565656.
   *     824824821-824824827   now has one invalid ID, 824824824.
   *     2121212118-2121212124 now has one invalid ID, 2121212121.
   *     
   * Adding up all the invalid IDs in this example produces 4174379265.
   *                                                        
   * What do you get if you add up all of the invalid IDs using these new rules?
   * 
   * sum_ranges 85513235135
   * 
   * 
   * ------------------------------------------------------------------------
   * 
   * Range From 11 To 22 = Number of Values 11
   *     Nr. 1  1 11 is_even true 
   *     Nr. 12  2 22 is_even true 
   * 
   * Range From 95 To 115 = Number of Values 20
   *     Nr. 5  1 99 is_even true 
   * 
   * Range From 998 To 1012 = Number of Values 14
   *     Nr. 13  1 1010 is_even true 
   * 
   * Range From 1188511880 To 1188511890 = Number of Values 10
   *     Nr. 6  1 1188511885 is_even true 
   * 
   * Range From 222220 To 222224 = Number of Values 4
   *     Nr. 3  1 222222 is_even true 
   * 
   * Range From 1698522 To 1698528 = Number of Values 6
   * 
   * Range From 446443 To 446449 = Number of Values 6
   *     Nr. 4  1 446446 is_even true 
   * 
   * Range From 38593856 To 38593862 = Number of Values 6
   *     Nr. 4  1 38593859 is_even true 
   * 
   * Range From 565653 To 565659 = Number of Values 6
   * 
   * Range From 824824821 To 824824827 = Number of Values 6
   * 
   * Range From 2121212118 To 2121212124 = Number of Values 6
   * 
   * sum_ranges 1227775554
   * 
   * 
   * ------------------------------------------------------------------------
   * 
   * Range From 11 To 22 = Number of Values 11
   *     Nr. 1  1 11 is_even  pattern_count 1
   *     Nr. 12  2 22 is_even  pattern_count 1
   * 
   * Range From 95 To 115 = Number of Values 20
   *     Nr. 5  1 99 is_even  pattern_count 1
   *     Nr. 17  2 111 is_even  pattern_count 2
   * 
   * Range From 998 To 1012 = Number of Values 14
   *     Nr. 2  1 999 is_even  pattern_count 2
   *     Nr. 13  2 1010 is_even  pattern_count 1
   * 
   * Range From 1188511880 To 1188511890 = Number of Values 10
   *     Nr. 6  1 1188511885 is_even  pattern_count 1
   * 
   * Range From 222220 To 222224 = Number of Values 4
   *     Nr. 3  1 222222 is_even  pattern_count 1
   * 
   * Range From 1698522 To 1698528 = Number of Values 6
   * 
   * Range From 446443 To 446449 = Number of Values 6
   *     Nr. 4  1 446446 is_even  pattern_count 1
   * 
   * Range From 38593856 To 38593862 = Number of Values 6
   *     Nr. 4  1 38593859 is_even  pattern_count 1
   * 
   * Range From 565653 To 565659 = Number of Values 6
   *     Nr. 4  1 565656 is_even  pattern_count 2
   * 
   * Range From 824824821 To 824824827 = Number of Values 6
   *     Nr. 4  1 824824824 is_even  pattern_count 2
   * 
   * Range From 2121212118 To 2121212124 = Number of Values 6
   *     Nr. 4  1 2121212121 is_even  pattern_count 4
   * 
   * sum_ranges 4174379265
   * 
   */

  private static final BigDecimal BIG_DECIMAL_0  = new BigDecimal( "0" );

  private static final BigDecimal BIG_DECIMAL_10 = new BigDecimal( "10" );

  public static void main( String[] args )
  {
    String test_content = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    //checkListPart1( test_content_list );
    checkListPart2( test_content_list );

    //checkListPart1( getListProd() );
    checkListPart2( getListProd() );

    //startTestCountNr();
  }

  private static void checkListPart1( List< String > pListIdRanges )
  {
    if ( pListIdRanges == null )
    {
      wl( "pListIdRanges == null" );

      return;
    }

    BigDecimal sum_ranges = BIG_DECIMAL_0;

    for ( int list_index = 0; list_index < pListIdRanges.size(); list_index++ )
    {
      String list_string = pListIdRanges.get( list_index );

      sum_ranges = sum_ranges.add( checkRangePart1( list_string ) );
    }

    wl( "" );
    wl( "sum_ranges " + sum_ranges.toPlainString() );
    wl( "" );
    wl( "" );
  }

  private static BigDecimal checkRangePart1( String pRange )
  {
    String[] range_split = pRange.trim().split( "-" );

    String range_von = range_split[ 0 ];
    String range_bis = range_split[ 1 ];

    long range_start = Long.parseLong( range_von );
    long range_end = Long.parseLong( range_bis );

    if ( range_start > range_end )
    {
      long temp = range_end;

      range_end = range_start;

      range_start = temp;
    }

    wl( "" );
    wl( "Range From " + range_start + " To " + range_end + " = Number of Values " + ( range_end - range_start ) );

    BigDecimal sum_counter = BIG_DECIMAL_0;

    int nr_counter = 0;

    int counter_equal = 0;

    for ( long range_value = range_start; range_value <= range_end; range_value++ )
    {
      nr_counter++;

      String range_value_str = Long.toString( Math.abs( range_value ) );

      boolean knz_strings_equal = false;

      boolean knz_is_even_character_length = ( range_value_str.length() % 2 ) == 0;

      if ( knz_is_even_character_length )
      {
        int range_value_str_len = range_value_str.length();

        int range_value_str_mitte = range_value_str_len / 2;

        String first_half = range_value_str.substring( 0, range_value_str_mitte );
        String second_half = range_value_str.substring( range_value_str_mitte );

        knz_strings_equal = first_half.equals( second_half );
      }

      if ( knz_strings_equal )
      {
        counter_equal++;

        sum_counter = sum_counter.add( new BigDecimal( range_value ) );

        wl( "    Nr. " + nr_counter + "  " + counter_equal + " " + range_value + " is_even " + knz_is_even_character_length + " " ); // + sum_counter.toPlainString() );
      }
    }

    return sum_counter;
  }

  private static void checkListPart2( List< String > pListIdRanges )
  {
    if ( pListIdRanges == null )
    {
      wl( "pListIdRanges == null" );

      return;
    }

    BigDecimal sum_ranges = new BigDecimal( 0 );

    for ( int list_index = 0; list_index < pListIdRanges.size(); list_index++ )
    {
      String list_string = pListIdRanges.get( list_index );

      sum_ranges = sum_ranges.add( checkRangePart2( list_string ) );
    }

    wl( "" );
    wl( "sum_ranges " + sum_ranges.toPlainString() );
    wl( "" );
    wl( "" );
  }

  private static BigDecimal checkRangePart2( String pRange )
  {
    String[] range_split = pRange.trim().split( "-" );

    String range_von = range_split[ 0 ];
    String range_bis = range_split[ 1 ];

    long range_start = Long.parseLong( range_von );
    long range_end = Long.parseLong( range_bis );

    if ( range_start > range_end )
    {
      long temp = range_end;

      range_end = range_start;

      range_start = temp;
    }

    wl( "" );
    wl( "Range From " + range_start + " To " + range_end + " = Number of Values " + ( range_end - range_start ) );

    BigDecimal sum_counter = new BigDecimal( 0 );

    int nr_counter = 0;

    int counter_equal = 0;

    for ( long range_value = range_start; range_value <= range_end; range_value++ )
    {
      nr_counter++;

      String range_value_str = Long.toString( Math.abs( range_value ) );

      int range_value_str_len = range_value_str.length();

      int range_value_str_mitte = (int) range_value_str_len / 2;

      int index_end_pattern = range_value_str_mitte;

      /*
       * pattern_count does not include the first occurance of the pattern 
       */
      int pattern_count = 0;

      while ( ( index_end_pattern >= 0 ) && ( pattern_count == 0 ) )
      {
        pattern_count = countPattenOccurence( range_value_str, index_end_pattern );

        if ( pattern_count == 0 )
        {
          index_end_pattern--;
        }
      }

      boolean knz_number_illegal = pattern_count > 0;

      if ( knz_number_illegal )
      {
        counter_equal++;

        sum_counter = sum_counter.add( new BigDecimal( range_value ) );

        wl( "    Nr. " + nr_counter + "  " + counter_equal + " " + range_value + " is_even " + " pattern_count " + ( pattern_count ) );
      }
    }

    return sum_counter;
  }

  private static void startTestCountNr()
  {
    testCountNr( "585858", 1 );
    testCountNr( "12341234", 1 );
    testCountNr( "12341234", 2 );
    testCountNr( "12341234", 3 );
    testCountNr( "12341234", 4 );
    testCountNr( "12341234", 5 );

    testCountNr( "11111111111", 0 );
    testCountNr( "11111111111", 1 );
    testCountNr( "11111111111", 2 );
    testCountNr( "11111111111", 3 );
    testCountNr( "11111111111", 4 );
    testCountNr( "11111111111", 5 );
    testCountNr( "11111111111", 6 );
    testCountNr( "11111111111", 7 );

    testCountNr( "1231235", 2 );

    testCountNr( "824824824", 2 );

    testCountNr( "2121212121", 1 );
    testCountNr( "1188511885", 4 );

    testCountNr( "12345", 4 );
  }

  private static void testCountNr( String pString, int pPatternIndexEnd )
  {
    wl( "countNr( \"" + pString + "\", " + pPatternIndexEnd + " ) = " + countPattenOccurence( pString, pPatternIndexEnd ) );
  }

  private static int countPattenOccurence( String pString, int pPatternIndexEnd )
  {
    int pattern_count = 0;

    if ( pString != null )
    {
      int str_length = pString.length();

      if ( pPatternIndexEnd < str_length )
      {
        /*
         * Points to the character in the first pattern.
         * Next character to be compared.
         */
        int pattern_index_act = 0;

        /*
         * Points to the string-index, where the compare pattern ends.
         */
        int pattern_index_end = pPatternIndexEnd;

        /*
         * Points to the next character, compared to the first pattern.
         */
        int pattern_check_index = pPatternIndexEnd + 1;

        while ( pattern_check_index < str_length )
        {
          char pattern_char = pString.charAt( pattern_index_act );

          char check_char = pString.charAt( pattern_check_index );

          if ( pattern_char == check_char )
          {
            /*
             * Character match
             */

            if ( pattern_index_act == pattern_index_end )
            {
              /*
               * If the pattern_index_act reaches the end index,
               * than we found a new pattern occurance.
               */
              pattern_count++;

              /*
               * For the next round, the pattern_index_act is reseted to the index 0.
               */
              pattern_index_act = 0;
            }
            else
            {
              /*
               * If we're not done with the pattern matching, the 
               * index for the first pattern needs to be increased.
               */
              pattern_index_act++;
            }

            /*
             * The pattern_check_index is increased for every round.
             */
            pattern_check_index++;
          }
          else
          {
            /*
             * Character mismatch = return 0
             */
            return 0;
          }
        }

        if ( pattern_index_act != 0 )
        {
          return 0;
        }
      }
    }

    return pattern_count;
  }

  private static List< String > getListProd()
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
