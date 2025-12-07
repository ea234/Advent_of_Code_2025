package de.ea234.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.ea234.util.FkString;

public class Day07Laboratories
{
  /*
   * --- Day 7: Laboratories ---
   * https://adventofcode.com/2025/day/7
   * 
   * 
   * ------------------------------------------------------------------------
   * 
   *    1  A = ".......S......." B = "..............." C = ".......|......." - Splits 0 - Char hit 0 - Char not hit 0 - E 0 - NR 0 - NL 0 - NS 0
   *    2  A = ".......|......." B = ".......^......." C = "......|^|......" - Splits 2 - Char hit 1 - Char not hit 0 - E 0 - NR 1 - NL 1 - NS 2
   *    3  A = "......|^|......" B = "..............." C = "......|.|......" - Splits 0 - Char hit 0 - Char not hit 0 - E 2 - NR 0 - NL 0 - NS 0
   *    4  A = "......|.|......" B = "......^.^......" C = ".....|^|^|....." - Splits 3 - Char hit 2 - Char not hit 0 - E 0 - NR 1 - NL 2 - NS 3
   *    5  A = ".....|^|^|....." B = "..............." C = ".....|.|.|....." - Splits 0 - Char hit 0 - Char not hit 0 - E 3 - NR 0 - NL 0 - NS 0
   *    6  A = ".....|.|.|....." B = ".....^.^.^....." C = "....|^|^|^|...." - Splits 4 - Char hit 3 - Char not hit 0 - E 0 - NR 1 - NL 3 - NS 4
   *    7  A = "....|^|^|^|...." B = "..............." C = "....|.|.|.|...." - Splits 0 - Char hit 0 - Char not hit 0 - E 4 - NR 0 - NL 0 - NS 0
   *    8  A = "....|.|.|.|...." B = "....^.^...^...." C = "...|^|^|||^|..." - Splits 5 - Char hit 3 - Char not hit 0 - E 1 - NR 2 - NL 3 - NS 5
   *    9  A = "...|^|^|||^|..." B = "..............." C = "...|.|.|||.|..." - Splits 0 - Char hit 0 - Char not hit 0 - E 6 - NR 0 - NL 0 - NS 0
   *   10  A = "...|.|.|||.|..." B = "...^.^...^.^..." C = "..|^|^|||^|^|.." - Splits 5 - Char hit 4 - Char not hit 0 - E 2 - NR 1 - NL 4 - NS 5
   *   11  A = "..|^|^|||^|^|.." B = "..............." C = "..|.|.|||.|.|.." - Splits 0 - Char hit 0 - Char not hit 0 - E 7 - NR 0 - NL 0 - NS 0
   *   12  A = "..|.|.|||.|.|.." B = "..^...^.....^.." C = ".|^|||^||.||^|." - Splits 6 - Char hit 3 - Char not hit 0 - E 3 - NR 3 - NL 3 - NS 6
   *   13  A = ".|^|||^||.||^|." B = "..............." C = ".|.|||.||.||.|." - Splits 0 - Char hit 0 - Char not hit 0 - E 9 - NR 0 - NL 0 - NS 0
   *   14  A = ".|.|||.||.||.|." B = ".^.^.^.^.^...^." C = "|^|^|^|^|.|||^|" - Splits 7 - Char hit 5 - Char not hit 1 - E 2 - NR 2 - NL 5 - NS 7
   *   15  A = "|^|^|^|^|.|||^|" B = "..............." C = "|.|.|.|.|.|||.|" - Splits 0 - Char hit 0 - Char not hit 0 - E 9 - NR 0 - NL 0 - NS 0
   * 
   * sum_split_chars_hit     = 21 
   * sum_split_chars_not_hit = 1 
   * 
   * sum_existing_beams      = 48 
   * 
   * sum_new_beams_left      = 21 
   * sum_new_beams_right     = 11 
   * sum_new_beams_total     = 32
   * 
   */

  private static final char CHAR_START_POSITION   = 'S';

  private static final char CHAR_EMPTY_SPACE      = '.';

  private static final char CHAR_SPLIT_POSITION   = '^';

  private static final char CHAR_BEAM             = '|';

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

    for ( String[] result_array_row : res_list )
    {
      sum_splits += getNumber( result_array_row[ 1 ] );
      sum_split_chars_hit += getNumber( result_array_row[ 2 ] );
      sum_existing_beams += getNumber( result_array_row[ 3 ] );
      sum_new_beams_right += getNumber( result_array_row[ 4 ] );
      sum_new_beams_left += getNumber( result_array_row[ 5 ] );
      sum_new_beams_total += getNumber( result_array_row[ 6 ] );

      sum_split_chars_not_hit += getNumber( result_array_row[ 7 ] );
    }

    wl( "" );
    wl( "sum_split_characters    = " + sum_splits + " " );
    wl( "sum_split_chars_hit     = " + sum_split_chars_hit + " " );
    wl( "sum_split_chars_not_hit = " + sum_split_chars_not_hit + " " );
    wl( "" );
    wl( "sum_existing_beams      = " + sum_existing_beams + " " );
    wl( "" );
    wl( "sum_new_beams_left      = " + sum_new_beams_left + " " );
    wl( "sum_new_beams_right     = " + sum_new_beams_right + " " );
    wl( "sum_new_beams_total     = " + sum_new_beams_total + " " );
  }

  private static int getNumber( String pString )
  {
    return Integer.parseInt( pString );
  }

  private static String[] testCalcRow( int pnr, String pRowCurrentBeamPosition, String pRowSplitPattern, boolean pKnzDebug )
  {
    String[] str_result_line = calcRow( pRowCurrentBeamPosition, pRowSplitPattern );

    if ( pKnzDebug )
    {
      String debug_row = "";

      debug_row += FkString.getFeldRechtsMin( pnr, 4 );
      debug_row += " A = \"" + pRowCurrentBeamPosition + "\"";
      debug_row += " B = \"" + pRowSplitPattern + "\"";
      debug_row += " C = \"" + str_result_line[ 0 ] + "\"";
      debug_row += " - Char sum " + str_result_line[ 1 ]; // Sum Split Characters 
      debug_row += " - Char hit " + str_result_line[ 2 ]; // Sum Split Characters hit
      debug_row += " - Char not hit " + str_result_line[ 7 ]; // Sum Split Characters not hit
      debug_row += " - E " + str_result_line[ 3 ]; // E = Existing Beams
      debug_row += " - NR " + str_result_line[ 4 ]; // NR = New Beams to the right
      debug_row += " - NL " + str_result_line[ 5 ]; // NL = New Beams to the left
      debug_row += " - NS " + str_result_line[ 6 ]; // NS = Sum of New Beams

      wl( debug_row );
    }

    return str_result_line;
  }

  private static String[] calcRow( String pRowCurrentBeamPosition, String pRowSplitPattern )
  {
    String str_new_beam_position = "";

    int input_str_index = 0;

    int count_split_chars_hit = 0;
    int count_split_chars_not_hit = 0;
    
    int count_new_beams_left = 0;
    int count_new_beams_right = 0;
    int count_existing_beams = 0;

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
        str_new_beam_position += CHAR_BEAM;

        flag_position_is_beam = 0;
      }
      else if ( char_split_pattern == CHAR_SPLIT_POSITION )
      {
        if ( char_beam_position != CHAR_BEAM )
        {
          /*
           * Split character found, but not hit by beam
           * the next line is empty space
           */
          count_split_chars_not_hit++;

          str_new_beam_position += CHAR_EMPTY_SPACE;
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
              count_new_beams_right++;

              if ( input_str_index == 1 )
              {
                /*
                 * Split-Position at second character
                 * result has only 1 character at this time.
                 * 
                 */
                str_new_beam_position = "" + CHAR_BEAM;

              }
              else
              {
                str_new_beam_position = str_new_beam_position.substring( 0, str_new_beam_position.length() - 1 ) + CHAR_BEAM;
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
              count_new_beams_left++;

              flag_position_is_beam = 1;
            }
          }
        }
      }
      else if ( char_beam_position == CHAR_BEAM )
      {
        /*
         * Continue beam down.
         */
        count_existing_beams++;

        str_new_beam_position += CHAR_BEAM;
      }
      else if ( char_beam_position == CHAR_START_POSITION )
      {
        if ( char_split_pattern == CHAR_EMPTY_SPACE )
        {
          str_new_beam_position += CHAR_BEAM;
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

    return new String[] { str_new_beam_position, "" + ( count_split_chars_hit + count_split_chars_not_hit ), "" + count_split_chars_hit, "" + count_existing_beams, "" + count_new_beams_right, "" + count_new_beams_left, "" + ( count_new_beams_right + count_new_beams_left ), "" + count_split_chars_not_hit };
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
