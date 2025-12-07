package de.ea234.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import de.ea234.util.FkString;

public class Day07Laboratories
{
  /*
   * --- Day 7: Laboratories ---
   * https://adventofcode.com/2025/day/7
   * 
   * 
   * 187987920774390
   * 
   * 
   * 
   *    0 A = ".......S......." B = "..............." C = ".......|......." - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 0 - NR 0 - NL 0 - NS 0 - P 0
   *    1 A = ".......|......." B = ".......^......." C = "......|^|......" - Char sum 1 - Char hit 1 - Char not hit 0 - Char hit Exi 0 - E 0 - NR 1 - NL 1 - NS 2 - P 2
   *    2 A = "......|^|......" B = "..............." C = "......|.|......" - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 2 - NR 0 - NL 0 - NS 0 - P 0
   *    3 A = "......|.|......" B = "......^.^......" C = ".....|^|^|....." - Char sum 2 - Char hit 2 - Char not hit 0 - Char hit Exi 0 - E 0 - NR 2 - NL 1 - NS 3 - P 4
   *    4 A = ".....|^|^|....." B = "..............." C = ".....|.|.|....." - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 3 - NR 0 - NL 0 - NS 0 - P 0
   *    5 A = ".....|.|.|....." B = ".....^.^.^....." C = "....|^|^|^|...." - Char sum 3 - Char hit 3 - Char not hit 0 - Char hit Exi 0 - E 0 - NR 3 - NL 1 - NS 4 - P 6
   *    6 A = "....|^|^|^|...." B = "..............." C = "....|.|.|.|...." - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 4 - NR 0 - NL 0 - NS 0 - P 0
   *    7 A = "....|.|.|.|...." B = "....^.^...^...." C = "...|^|^|||^|..." - Char sum 3 - Char hit 3 - Char not hit 0 - Char hit Exi 0 - E 1 - NR 3 - NL 2 - NS 5 - P 6
   *    8 A = "...|^|^|||^|..." B = "..............." C = "...|.|.|||.|..." - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 6 - NR 0 - NL 0 - NS 0 - P 0
   *    9 A = "...|.|.|||.|..." B = "...^.^...^.^..." C = "..|^|^|||^|^|.." - Char sum 4 - Char hit 4 - Char not hit 0 - Char hit Exi 0 - E 2 - NR 4 - NL 1 - NS 5 - P 8
   *   10 A = "..|^|^|||^|^|.." B = "..............." C = "..|.|.|||.|.|.." - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 7 - NR 0 - NL 0 - NS 0 - P 0
   *   11 A = "..|.|.|||.|.|.." B = "..^...^.....^.." C = ".|^|||^||.||^|." - Char sum 3 - Char hit 3 - Char not hit 0 - Char hit Exi 0 - E 3 - NR 3 - NL 3 - NS 6 - P 6
   *   12 A = ".|^|||^||.||^|." B = "..............." C = ".|.|||.||.||.|." - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 9 - NR 0 - NL 0 - NS 0 - P 0
   *   13 A = ".|.|||.||.||.|." B = ".^.^.^.^.^...^." C = "|^|^|^|^|A|||^|" - Char sum 6 - Char hit 5 - Char not hit 1 - Char hit Exi 0 - E 2 - NR 5 - NL 2 - NS 7 - P 12
   *   14 A = "|^|^|^|^|A|||^|" B = "..............." C = "|.|.|.|.|.|||.|" - Char sum 0 - Char hit 0 - Char not hit 0 - Char hit Exi 0 - E 9 - NR 0 - NL 0 - NS 0 - P 0
   * 
   * sum_split_characters    = 22 
   * sum_split_chars_hit     = 21 
   * sum_split_chars_not_hit = 1 
   * 
   * sum_hit_existing_beams  = 1 
   * 
   * 
   * sum_split_chars_not_hit = 1 
   * 
   * sum_existing_beams      = 48 
   * 
   * sum_new_beams_left      = 11 
   * sum_new_beams_right     = 21 
   * sum_new_beams_total     = 32 
   * 
   * Sum Possibilities 40
   *      0     0     0     0     0     0     0     1     0     0     0     0     0     0     0
   *      0     0     0     0     0     0     1     0     1     0     0     0     0     0     0
   *      0     0     0     0     0     0     1     0     1     0     0     0     0     0     0
   *      0     0     0     0     0     1     0     2     0     1     0     0     0     0     0
   *      0     0     0     0     0     1     0     2     0     1     0     0     0     0     0
   *      0     0     0     0     1     0     3     0     3     0     1     0     0     0     0
   *      0     0     0     0     1     0     3     0     3     0     1     0     0     0     0
   *      0     0     0     1     0     4     0     3     3     1     0     1     0     0     0
   *      0     0     0     1     0     4     0     3     3     1     0     1     0     0     0
   *      0     0     1     0     5     0     4     3     4     0     2     0     1     0     0
   *      0     0     1     0     5     0     4     3     4     0     2     0     1     0     0
   *      0     1     0     1     5     4     0     7     4     0     2     1     0     1     0
   *      0     1     0     1     5     4     0     7     4     0     2     1     0     1     0
   *      1     0     2     0    10     0    11     0    11     0     2     1     1     0     1
   *      1     0     2     0    10     0    11     0    11     0     2     1     1     0     1
   *                                                                                           
   * File Row Count 142 142
   * 
   * sum_split_characters    = 1770 
   * sum_split_chars_hit     = 1675 
   * sum_split_chars_not_hit = 95 
   * 
   * sum_hit_existing_beams  = 95 
   * 
   * 
   * sum_split_chars_not_hit = 95 
   * 
   * sum_existing_beams      = 4062 
   * 
   * sum_new_beams_left      = 451 
   * sum_new_beams_right     = 1675 
   * sum_new_beams_total     = 2126 
   * Sum Possibilities 187987920774390
   * 
   * ------------------------------------------------------------------------
   * 
   * 
   */

  private static final char CHAR_START_POSITION   = 'S';

  private static final char CHAR_EMPTY_SPACE      = '.';

  private static final char CHAR_SPLIT_POSITION   = '^';

  private static final char CHAR_EXISTING_BEAM    = '|';

  private static final char CHAR_NEW_BEAM_RIGHT   = '|';

  private static final char CHAR_NEW_BEAM_LEFT    = '|';

  private static final char CHAR_NEW_BEAM_BENEATH = '|';

  private static final char CHAR_SPLIT_NOT_HIT    = 'A';

  private static Properties prop_part_2           = null;

  public static void main( String[] args )
  {
    String test_content = ".......S.......,...............,.......^.......,...............,......^.^......,...............,.....^.^.^.....,...............,....^.^...^....,...............,...^.^...^.^...,...............,..^...^.....^..,...............,.^.^.^.^.^...^.,...............";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    calcNewGrid( test_content_list, true );

    calcNewGrid( getListProd(), false );
  }

  private static void calcNewGrid( List< String > pListSplitPattern, boolean pKnzDebug )
  {
    if ( pListSplitPattern == null )
    {
      wl( "pList == null" );

      return;
    }

    List< String[] > res_list = new ArrayList< String[] >();

    String str_beam_positions = null;

    int debug_row_nr_split_pattern = 0;

    for ( int list_index = 0; list_index < pListSplitPattern.size(); list_index++ )
    {
      String str_split_pattern = pListSplitPattern.get( list_index );

      if ( list_index > 0 )
      {
        String[] result_line_current = testCalcRow( debug_row_nr_split_pattern++, str_beam_positions, str_split_pattern, pKnzDebug );

        res_list.add( result_line_current );

        str_beam_positions = result_line_current[ 0 ];
      }
      else
      {
        str_beam_positions = str_split_pattern;
      }
    }

    int sum_splits = 0;
    int sum_split_chars_hit = 0;
    int sum_new_beams_left = 0;
    int sum_new_beams_right = 0;
    int sum_existing_beams = 0;
    int sum_new_beams_total = 0;
    int sum_split_chars_not_hit = 0;
    int sum_hit_existing_beam = 0;

    for ( String[] result_array_row : res_list )
    {
      sum_splits += getNumberInt( result_array_row[ 1 ] );
      sum_split_chars_hit += getNumberInt( result_array_row[ 2 ] );
      sum_existing_beams += getNumberInt( result_array_row[ 3 ] );
      sum_new_beams_right += getNumberInt( result_array_row[ 4 ] );
      sum_new_beams_left += getNumberInt( result_array_row[ 5 ] );
      sum_new_beams_total += getNumberInt( result_array_row[ 6 ] );

      sum_split_chars_not_hit += getNumberInt( result_array_row[ 7 ] );
      sum_hit_existing_beam += getNumberInt( result_array_row[ 7 ] );
    }

    wl( "" );
    wl( "sum_split_characters    = " + sum_splits + " " );
    wl( "sum_split_chars_hit     = " + sum_split_chars_hit + " " );
    wl( "sum_split_chars_not_hit = " + sum_split_chars_not_hit + " " );
    wl( "" );
    wl( "sum_hit_existing_beams  = " + sum_hit_existing_beam + " " );
    wl( "" );
    wl( "" );
    wl( "sum_split_chars_not_hit = " + sum_split_chars_not_hit + " " );
    wl( "" );
    wl( "sum_existing_beams      = " + sum_existing_beams + " " );
    wl( "" );
    wl( "sum_new_beams_left      = " + sum_new_beams_left + " " );
    wl( "sum_new_beams_right     = " + sum_new_beams_right + " " );
    wl( "sum_new_beams_total     = " + sum_new_beams_total + " " );

    String str_split_pattern = pListSplitPattern.get( 0 );

    calcPart2( res_list );

    if ( pKnzDebug )
    {
      for ( int x = 0; x < pListSplitPattern.size(); x++ )
      {
        wl( getDebugStringx( x, str_split_pattern.length() ) );
      }
    }
  }

  private static String[] testCalcRow( int pRowIndex, String pRowCurrentBeamPosition, String pRowSplitPattern, boolean pKnzDebug )
  {
    String[] str_result_line = calcRow( pRowIndex, pRowCurrentBeamPosition, pRowSplitPattern );

    if ( pKnzDebug )
    {
      String debug_row = "";

      debug_row += FkString.getFeldRechtsMin( pRowIndex, 4 );
      debug_row += " A = \"" + pRowCurrentBeamPosition + "\"";
      debug_row += " B = \"" + pRowSplitPattern + "\"";
      debug_row += " C = \"" + str_result_line[ 0 ] + "\"";
      debug_row += " - Char sum " + str_result_line[ 1 ]; // Sum Split Characters 
      debug_row += " - Char hit " + str_result_line[ 2 ]; // Sum Split Characters hit
      debug_row += " - Char not hit " + str_result_line[ 7 ]; // Sum Split Characters not hit
      debug_row += " - Char hit Exi " + str_result_line[ 8 ]; // Sum Split Characters not hit
      debug_row += " - E " + str_result_line[ 3 ]; // E = Existing Beams
      debug_row += " - NR " + str_result_line[ 4 ]; // NR = New Beams to the right
      debug_row += " - NL " + str_result_line[ 5 ]; // NL = New Beams to the left
      debug_row += " - NS " + str_result_line[ 6 ]; // NS = Sum of New Beams
      debug_row += " - P " + str_result_line[ 9 ]; // NS = Sum of New Beams

      wl( debug_row );
    }

    return str_result_line;
  }

  private static String[] calcRow( int pRowIndex, String pRowCurrentBeamPosition, String pRowSplitPattern )
  {
    String str_new_beam_position = "";

    int input_str_index = 0;

    int count_split_chars_hit = 0;
    int count_split_chars_not_hit = 0;

    int count_new_beams_left = 0;
    int count_new_beams_right = 0;
    int count_existing_beams = 0;
    int count_hit_existing_beam = 0;

    int count_pos = 0;

    int flag_position_is_beam = 0;

    while ( input_str_index < pRowSplitPattern.length() )
    {
      /*
       * pRowCurrentBeamPosition = contains the pattern with beam positions
       *                           (How far has the beam come)
       *               
       * pRowSplitPattern = contains the pattern for beam splitting
       * 
       * S = CHAR_START_POSITION = creates a new beam beneath the index
       * 
       * ^ = CHAR_SPLIT_POSITION = creates 2 new beams to the left and right
       *                           - to the left, only if the position not less than 0
       *                           - to the right, only if the position is lower then string-length
       *                           - not, when there is already a beam at the right or left position
       *                           
       *                           creates no new beams, if the character is not hit by a beam.
       *                            
       * . CHAR_EMPTY_SPACE      = creates a beam beneath the current beam
       *  
       */

      char char_beam_position = pRowCurrentBeamPosition.charAt( input_str_index );

      char char_split_pattern = pRowSplitPattern.charAt( input_str_index );

      if ( flag_position_is_beam == 1 )
      {
        str_new_beam_position += CHAR_NEW_BEAM_RIGHT;

        flag_position_is_beam = 0;
      }
      else if ( char_split_pattern == CHAR_SPLIT_POSITION )
      {
        count_pos += 2;

        if ( char_beam_position != CHAR_EXISTING_BEAM )
        {
          /*
           * Split character found, but not hit by beam
           * the next line is empty space
           */
          count_split_chars_not_hit++;

          str_new_beam_position += CHAR_SPLIT_NOT_HIT;
        }
        else
        {
          count_split_chars_hit++;

          /*
           * Checking left side
           */
          if ( str_new_beam_position.length() > 0 )
          {
            /*
             * Checking left sidenr_splits
             */
            char char_temp = str_new_beam_position.charAt( str_new_beam_position.length() - 1 );

            if ( char_temp == CHAR_EMPTY_SPACE )
            {
              if ( input_str_index == 1 )
              {
                /*
                 * Split-Position at second character
                 * result has only 1 character at this time.
                 * 
                 */
                str_new_beam_position = "" + CHAR_NEW_BEAM_LEFT;

                count_new_beams_left++;
              }
              else
              {
                if ( char_temp == CHAR_EXISTING_BEAM )
                {
                  str_new_beam_position = str_new_beam_position.substring( 0, str_new_beam_position.length() - 1 ) + CHAR_EXISTING_BEAM;

                  count_hit_existing_beam++;
                }
                else
                {
                  str_new_beam_position = str_new_beam_position.substring( 0, str_new_beam_position.length() - 1 ) + CHAR_NEW_BEAM_LEFT;

                  count_new_beams_left++;
                }
              }
            }
            else
            {
              /*
               * Do Nothing to the left position.
               * There is only the need for a Change, if the left position 
               * is an empty space.
               */
            }

            str_new_beam_position += CHAR_SPLIT_POSITION;
          }

          /*
           * Checking right side
           */
          if ( ( input_str_index + 1 ) < pRowCurrentBeamPosition.length() )
          {
            /*
             * Checking, wether there is an empty space in the 
             * string (str_next_line).
             */
            char char_temp = pRowSplitPattern.charAt( input_str_index + 1 );

            if ( char_temp == CHAR_EMPTY_SPACE )
            {
              count_new_beams_right++;

              flag_position_is_beam = 1;
            }
            else if ( char_temp == CHAR_EXISTING_BEAM )
            {
              count_hit_existing_beam++;
            }
          }
        }
      }
      else if ( char_beam_position == CHAR_EXISTING_BEAM )
      {
        /*
         * Continue beam down.
         */
        count_existing_beams++;

        str_new_beam_position += CHAR_EXISTING_BEAM;

      }
      else if ( char_beam_position == CHAR_START_POSITION )
      {
        if ( char_split_pattern == CHAR_EMPTY_SPACE )
        {
          str_new_beam_position += CHAR_NEW_BEAM_BENEATH;
        }
        else
        {
          str_new_beam_position += CHAR_EMPTY_SPACE;
        }
      }
      else
      {
        str_new_beam_position += CHAR_EMPTY_SPACE;
      }

      input_str_index++;
    }

    return new String[] { str_new_beam_position, "" + ( count_split_chars_hit + count_split_chars_not_hit ), "" + count_split_chars_hit, "" + count_existing_beams, "" + count_new_beams_right, "" + count_new_beams_left, "" + ( count_new_beams_right + count_new_beams_left ), "" + count_split_chars_not_hit, "" + count_hit_existing_beam, "" + count_pos };
  }

  private static void calcPart2( List< String[] > res_list )
  {
    int akt_row = 0;

    long sum_possibilities_row = 0;

    String prev_row = null;

    for ( String[] result_array_row : res_list )
    {
      String current_row = result_array_row[ 0 ];

      sum_possibilities_row = 0;

      int input_str_index = 0;

      while ( input_str_index < current_row.length() )
      {
        long akt_value = 0;

        char char_beam_position = current_row.charAt( input_str_index );

        if ( char_beam_position == CHAR_EXISTING_BEAM )
        {
          if ( prev_row == null )
          {
            akt_value = 1; // Start Value
          }
          else
          {
            if ( ( input_str_index + 1 ) < current_row.length() )
            {
              char char_beam_position_plus_one = current_row.charAt( input_str_index + 1 );

              if ( char_beam_position_plus_one == CHAR_SPLIT_POSITION )
              {
                if ( prev_row != null )
                {
                  long prev_value_from_above_right = getSplitValue( akt_row - 1, input_str_index + 1 );

                  akt_value += prev_value_from_above_right;
                }
              }
            }

            if ( ( input_str_index - 1 ) >= 0 )
            {
              char char_beam_position_minus_one = current_row.charAt( input_str_index - 1 );

              if ( char_beam_position_minus_one == CHAR_SPLIT_POSITION )
              {
                if ( prev_row != null )
                {
                  long prev_value_from_above_left = getSplitValue( akt_row - 1, input_str_index - 1 );

                  akt_value += prev_value_from_above_left;
                }
              }
            }

            long value_from_direct_above = getSplitValue( akt_row - 1, input_str_index );

            akt_value += value_from_direct_above;
          }
        }

        setSplitValue( akt_row, input_str_index, akt_value );

        sum_possibilities_row += akt_value;

        input_str_index++;
      }

      getProperties().setProperty( "R" + akt_row + "_SUM", "" + sum_possibilities_row );

      akt_row++;
      prev_row = current_row;
    }

    wl( "Sum Possibilities " + sum_possibilities_row );
  }

  private static long getSplitValue( int pRowIndex, int pColIndex )
  {
    return getNumberLong( getProperties().getProperty( "R" + pRowIndex + "S" + pColIndex, "0" ) );
  }

  private static void setSplitValue( int pRowIndex, int pColIndex, long pValue )
  {
    getProperties().setProperty( "R" + pRowIndex + "S" + pColIndex, "" + pValue );
  }

  private static Properties getProperties()
  {
    if ( prop_part_2 == null )
    {
      prop_part_2 = new Properties();
    }

    return prop_part_2;
  }

  private static int getNumberInt( String pString )
  {
    return Integer.parseInt( pString );
  }

  private static long getNumberLong( String pString )
  {
    return Long.parseLong( pString );
  }

  private static String getDebugStringx( int pRowIndex, int pAnzahl )
  {
    String erg_s = "";

    for ( int x = 0; x < pAnzahl; x++ )
    {
      erg_s += " " + FkString.getFeldRechtsMin( getProperties().getProperty( "R" + pRowIndex + "S" + x, "" ), 5 );
    }

    return erg_s;
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day7_input.txt";

    try (BufferedReader buffered_reader = new BufferedReader( new FileReader( datei_input ) ))
    {
      String zeile;

      while ( ( zeile = buffered_reader.readLine() ) != null )
      {
        zeile = zeile.trim();

        string_array.add( zeile );

        row_count++;
      }
    }
    catch ( IOException err_inst )
    {
      err_inst.printStackTrace();
    }

    wl( "File Row Count " + row_count + " " + string_array.size() );

    return string_array;
  }

  /**
   * wl = write log 
   * 
   * @param pString der auszugebende String
   */
  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
