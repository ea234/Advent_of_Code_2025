package de.ea234.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4PrintingDepartment
{
  /*
   * --- Day 4: Printing Department ---
   * https://adventofcode.com/2025/day/4
   * 
   * Total removal of paper rolls 43
   */

  private static final char CHAR_PAPER_ROLL       = '@';

  private static final char CHAR_EMPTY_SPACE      = '.';

  private static final char CHAR_SPOT_REMOVAL     = 'x';

  private static final int  NUMBER_OF_PAPER_ROLLS = 3;

  public static void main( String[] args )
  {
    String test_content = "..@@.@@@@.,@@@.@.@.@@,@@@@@.@.@@,@.@@@@..@.,@@.@@@@.@@,.@@@@@@@.@,.@.@.@.@@@,@.@@@.@@@@,.@@@@@@@@.,@.@.@@@.@.";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    calcPaperRollRemoval( getListProd(), false, false );

    calcPaperRollRemoval( test_content_list, true, true );
  }

  private static void calcPaperRollRemoval( List< String > pList, boolean pKnzCalculatePart2, boolean pKnzDebug )
  {
    wl( "" );
    wl( "Initial Grid" );
    wl( "" );

    wl( getDebugGrid( pList ) );

    if ( pKnzCalculatePart2 )
    {
      List< String > new_grid = pList;

      int removal_counter = 0;

      int removal_number_current_grid = calcGridRemovalNumber( pList );

      int removal_number_total = removal_number_current_grid;

      while ( removal_number_current_grid > 0 )
      {
        new_grid = calcNewInputGrid( new_grid );

        if ( pKnzDebug )
        {
          removal_counter++;

          wl( "" );
          wl( "--- Round " + removal_counter + "------------------------------------------------------" );
          wl( "" );
          wl( getDebugGrid( new_grid ) );
        }

        removal_number_current_grid = calcGridRemovalNumber( new_grid );

        removal_number_total += removal_number_current_grid;
      }

      wl( "Total removal of paper rolls " + removal_number_total );
    }
  }

  private static String getDebugGrid( List< String > pList )
  {
    if ( pList == null )
    {
      wl( "pList == null" );

      return "";
    }

    int removal_positions = 0;

    String result_str = "";

    String str_cr_lf = "";

    for ( int list_index = 0; list_index < pList.size(); list_index++ )
    {
      String str_previos_line = null;

      String str_next_line = null;

      String str_current_line = pList.get( list_index );

      if ( list_index > 0 )
      {
        str_previos_line = pList.get( list_index - 1 );
      }

      if ( list_index + 1 < pList.size() )
      {
        str_next_line = pList.get( list_index + 1 );
      }

      String str_floor_plan = "";

      String str_current_position_count = "";

      for ( int col_index = 0; col_index < str_current_line.length(); col_index++ )
      {
        int number_of_adjacent_paper_rolls = 0;

        if ( ( str_current_line.charAt( col_index ) == CHAR_PAPER_ROLL ) )
        {
          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_previos_line, col_index, true );

          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_current_line, col_index, false );

          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_next_line, col_index, true );

          if ( number_of_adjacent_paper_rolls <= NUMBER_OF_PAPER_ROLLS )
          {
            removal_positions++;

            str_floor_plan += CHAR_SPOT_REMOVAL;
          }
          else
          {
            str_floor_plan += CHAR_PAPER_ROLL;
          }

          str_current_position_count += "" + number_of_adjacent_paper_rolls;
        }
        else
        {
          str_current_position_count += CHAR_EMPTY_SPACE;

          str_floor_plan += CHAR_EMPTY_SPACE;
        }
      }

      result_str += str_cr_lf + str_current_line + "    " + str_floor_plan + "    " + str_current_position_count;

      str_cr_lf = "\n";
    }

    result_str += "\n\nRemove " + removal_positions + " roll of paper\n";

    return result_str;
  }

  private static int calcGridRemovalNumber( List< String > pList )
  {
    if ( pList == null )
    {
      wl( "pList == null" );

      return 0;
    }

    int removal_positions = 0;

    for ( int list_index = 0; list_index < pList.size(); list_index++ )
    {
      String str_previos_line = null;

      String str_next_line = null;

      String str_current_line = pList.get( list_index );

      if ( list_index > 0 )
      {
        str_previos_line = pList.get( list_index - 1 );
      }

      if ( list_index + 1 < pList.size() )
      {
        str_next_line = pList.get( list_index + 1 );
      }

      for ( int col_index = 0; col_index < str_current_line.length(); col_index++ )
      {
        int number_of_adjacent_paper_rolls = 0;

        if ( ( str_current_line.charAt( col_index ) == CHAR_PAPER_ROLL ) )
        {
          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_previos_line, col_index, true );

          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_current_line, col_index, false );

          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_next_line, col_index, true );

          if ( number_of_adjacent_paper_rolls <= NUMBER_OF_PAPER_ROLLS )
          {
            removal_positions++;
          }
        }
      }
    }

    return removal_positions;
  }

  private static List< String > calcNewInputGrid( List< String > pList )
  {
    if ( pList == null )
    {
      wl( "pList == null" );

      return null;
    }

    List< String > string_array = new ArrayList< String >();

    for ( int list_index = 0; list_index < pList.size(); list_index++ )
    {
      String str_previos_line = null;

      String str_next_line = null;

      String str_current_line = pList.get( list_index );

      if ( list_index > 0 )
      {
        str_previos_line = pList.get( list_index - 1 );
      }

      if ( list_index + 1 < pList.size() )
      {
        str_next_line = pList.get( list_index + 1 );
      }

      String akt_line = "";

      for ( int col_index = 0; col_index < str_current_line.length(); col_index++ )
      {
        int number_of_adjacent_paper_rolls = 0;

        if ( ( str_current_line.charAt( col_index ) == CHAR_PAPER_ROLL ) )
        {
          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_previos_line, col_index, true );

          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_current_line, col_index, false );

          number_of_adjacent_paper_rolls += calcAdjacentPaperRolls( str_next_line, col_index, true );

          if ( number_of_adjacent_paper_rolls <= NUMBER_OF_PAPER_ROLLS )
          {
            akt_line += CHAR_EMPTY_SPACE;
          }
          else
          {
            akt_line += CHAR_PAPER_ROLL;
          }
        }
        else
        {
          akt_line += CHAR_EMPTY_SPACE;
        }
      }

      string_array.add( akt_line );
    }

    return string_array;
  }

  private static int calcAdjacentPaperRolls( String pString, int pIndexPosition, boolean pKnzCountCenterPosition )
  {
    if ( pString == null )
    {
      return 0;
    }

    int number_of_adjacent_paper_rolls = 0;

    if ( ( pIndexPosition - 1 ) >= 0 )
    {
      if ( pString.charAt( ( pIndexPosition - 1 ) ) == CHAR_PAPER_ROLL )
      {
        number_of_adjacent_paper_rolls++;
      }
    }

    if ( ( pKnzCountCenterPosition ) && ( pString.charAt( ( pIndexPosition ) ) == CHAR_PAPER_ROLL ) )
    {
      number_of_adjacent_paper_rolls++;
    }

    if ( ( pIndexPosition + 1 ) < pString.length() )
    {
      if ( pString.charAt( ( pIndexPosition + 1 ) ) == CHAR_PAPER_ROLL )
      {
        number_of_adjacent_paper_rolls++;
      }
    }

    return number_of_adjacent_paper_rolls;
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day4_input.txt";

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
   * Ausgabe auf System.out
   * 
   * @param pString der auszugebende String
   */
  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
