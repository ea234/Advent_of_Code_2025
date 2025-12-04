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
   * You ride the escalator down to the printing department.
   * They're clearly getting ready for Christmas; they have
   * lots of large rolls of paper everywhere, and there's even
   * a massive printer in the corner (to handle the really big
   * print jobs).
   * 
   * Decorating here will be easy: they can make their own
   * decorations. What you really need is a way to get further
   * into the North Pole base while the elevators are offline.
   * 
   * "Actually, maybe we can help with that," one of the Elves
   * replies when you ask for help. "We're pretty sure there's
   * a cafeteria on the other side of the back wall. If we could
   * break through the wall, you'd be able to keep moving. It's
   * too bad all of our forklifts are so busy moving those big
   * rolls of paper around."
   * 
   * If you can optimize the work the forklifts are doing,
   * maybe they would have time to spare to break through 
   * the wall.
   * 
   * The rolls of paper (@) are arranged on a large grid; the
   * Elves even have a helpful diagram (your puzzle input) 
   * indicating where everything is located.
   * 
   * For example:
   * ..@@.@@@@.
   * @@@.@.@.@@
   * @@@@@.@.@@
   * @.@@@@..@.
   * @@.@@@@.@@
   * .@@@@@@@.@
   * .@.@.@.@@@
   * @.@@@.@@@@
   * .@@@@@@@@.
   * @.@.@@@.@.
   * 
   * The forklifts can only access a roll of paper if there
   * are fewer than four rolls of paper in the eight adjacent
   * positions. If you can figure out which rolls of paper the
   * forklifts can access, they'll spend less time looking and
   * more time breaking down the wall to the cafeteria.
   * 
   * In this example, there are 13 rolls of paper that can
   * be accessed by a forklift (marked with x):
   * 
   * ..xx.xx@x.
   * x@@.@.@.@@
   * @@@@@.x.@@
   * @.@@@@..@.
   * x@.@@@@.@x
   * .@@@@@@@.@
   * .@.@.@.@@@
   * x.@@@.@@@@
   * .@@@@@@@@.
   * x.x.@@@.x.
   * 
   * Consider your complete diagram of the paper roll locations.
   * How many rolls of paper can be accessed by a forklift?
   * 
   * Your puzzle answer was 1505.
   * 
   * 
   * --- Part Two -----------------------------------------------------------
   * 
   * Now, the Elves just need help accessing as much of the
   * paper as they can.
   * 
   * Once a roll of paper can be accessed by a forklift, it can be removed. 
   * 
   * Once a roll of paper is removed, the forklifts might be able to access 
   * more rolls of paper, which they might also be able to remove. 
   * 
   * How many total rolls of paper could the Elves remove if they keep 
   * repeating this process?
   * 
   * Starting with the same example as above, here is one way
   * you could remove as many rolls of paper as possible, using
   * highlighted @ to indicate that a roll of paper is about
   * to be removed, and using x to indicate that a roll of paper
   * was just removed:
   * 
   * Initial state:
   * ..@@.@@@@.
   * @@@.@.@.@@
   * @@@@@.@.@@
   * @.@@@@..@.
   * @@.@@@@.@@
   * .@@@@@@@.@
   * .@.@.@.@@@
   * @.@@@.@@@@
   * .@@@@@@@@.
   * @.@.@@@.@.
   * 
   * Remove 13 rolls of paper:
   * ..xx.xx@x.
   * x@@.@.@.@@
   * @@@@@.x.@@
   * @.@@@@..@.
   * x@.@@@@.@x
   * .@@@@@@@.@
   * .@.@.@.@@@
   * x.@@@.@@@@
   * .@@@@@@@@.
   * x.x.@@@.x.
   * 
   * Remove 12 rolls of paper:
   * .......x..
   * .@@.x.x.@x
   * x@@@@...@@
   * x.@@@@..x.
   * .@.@@@@.x.
   * .x@@@@@@.x
   * .x.@.@.@@@
   * ..@@@.@@@@
   * .x@@@@@@@.
   * ....@@@...
   * 
   * Remove 7 rolls of paper:
   * ..........
   * .x@.....x.
   * .@@@@...xx
   * ..@@@@....
   * .x.@@@@...
   * ..@@@@@@..
   * ...@.@.@@x
   * ..@@@.@@@@
   * ..x@@@@@@.
   * ....@@@...
   * 
   * Remove 5 rolls of paper:
   * ..........
   * ..x.......
   * .x@@@.....
   * ..@@@@....
   * ...@@@@...
   * ..x@@@@@..
   * ...@.@.@@.
   * ..x@@.@@@x
   * ...@@@@@@.
   * ....@@@...
   * 
   * Remove 2 rolls of paper:
   * ..........
   * ..........
   * ..x@@.....
   * ..@@@@....
   * ...@@@@...
   * ...@@@@@..
   * ...@.@.@@.
   * ...@@.@@@.
   * ...@@@@@x.
   * ....@@@...
   * 
   * Remove 1 roll of paper:
   * ..........
   * ..........
   * ...@@.....
   * ..x@@@....
   * ...@@@@...
   * ...@@@@@..
   * ...@.@.@@.
   * ...@@.@@@.
   * ...@@@@@..
   * ....@@@...
   * 
   * Remove 1 roll of paper:
   * ..........
   * ..........
   * ...x@.....
   * ...@@@....
   * ...@@@@...
   * ...@@@@@..
   * ...@.@.@@.
   * ...@@.@@@.
   * ...@@@@@..
   * ....@@@...
   * 
   * Remove 1 roll of paper:
   * ..........
   * ..........
   * ....x.....
   * ...@@@....
   * ...@@@@...
   * ...@@@@@..
   * ...@.@.@@.
   * ...@@.@@@.
   * ...@@@@@..
   * ....@@@...
   * 
   * Remove 1 roll of paper:
   * ..........
   * ..........
   * ..........
   * ...x@@....
   * ...@@@@...
   * ...@@@@@..
   * ...@.@.@@.
   * ...@@.@@@.
   * ...@@@@@..
   * ....@@@...
   *
   * Stop once no more rolls of paper are accessible by a forklift.
   * In this example, a total of 43 rolls of paper can be removed.
   * 
   * Start with your original diagram. How many rolls of paper
   * in total can be removed by the Elves and their forklifts?
   *  
   * Your puzzle answer was 9182.
   * 
   * 
   * ------------------------------------------------------------------------
   * 
   * Initial Grid
   * 
   * ..@@.@@@@.    ..xx.xx@x.    ..33.3343.
   * @@@.@.@.@@    x@@.@.@.@@    366.4.4.54
   * @@@@@.@.@@    @@@@@.x.@@    47675.2.44
   * @.@@@@..@.    @.@@@@..@.    4.6776..4.
   * @@.@@@@.@@    x@.@@@@.@x    35.7875.43
   * .@@@@@@@.@    .@@@@@@@.@    .4657665.4
   * .@.@.@.@@@    .@.@.@.@@@    .4.6.5.674
   * @.@@@.@@@@    x.@@@.@@@@    2.666.6774
   * .@@@@@@@@.    .@@@@@@@@.    .55767675.
   * @.@.@@@.@.    x.x.@@@.x.    1.3.454.2.
   * 
   * Remove 13 roll of paper
   * 
   * 
   * --- Round 1------------------------------------------------------
   * 
   * .......@..    .......x..    .......2..
   * .@@.@.@.@@    .@@.x.x.@x    .44.2.1.43
   * @@@@@...@@    x@@@@...@@    36675...44
   * @.@@@@..@.    x.@@@@..x.    3.6775..3.
   * .@.@@@@.@.    .@.@@@@.x.    .4.7875.3.
   * .@@@@@@@.@    .x@@@@@@.x    .3657665.3
   * .@.@.@.@@@    .x.@.@.@@@    .3.6.5.674
   * ..@@@.@@@@    ..@@@.@@@@    ..666.6774
   * .@@@@@@@@.    .x@@@@@@@.    .24667664.
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 12 roll of paper
   * 
   * 
   * --- Round 2------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * .@@.....@.    .x@.....x.    .34.....2.
   * .@@@@...@@    .@@@@...xx    .4664...22
   * ..@@@@....    ..@@@@....    ..6775....
   * .@.@@@@...    .x.@@@@...    .2.7875...
   * ..@@@@@@..    ..@@@@@@..    ..457664..
   * ...@.@.@@@    ...@.@.@@x    ...6.5.663
   * ..@@@.@@@@    ..@@@.@@@@    ..466.6774
   * ..@@@@@@@.    ..x@@@@@@.    ..3667664.
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 7 roll of paper
   * 
   * 
   * --- Round 3------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * ..@.......    ..x.......    ..3.......
   * .@@@@.....    .x@@@.....    .3564.....
   * ..@@@@....    ..@@@@....    ..5775....
   * ...@@@@...    ...@@@@...    ...7875...
   * ..@@@@@@..    ..x@@@@@..    ..357664..
   * ...@.@.@@.    ...@.@.@@.    ...6.5.65.
   * ..@@@.@@@@    ..x@@.@@@x    ..356.6763
   * ...@@@@@@.    ...@@@@@@.    ...567664.
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 5 roll of paper
   * 
   * 
   * --- Round 4------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ..@@@.....    ..x@@.....    ..354.....
   * ..@@@@....    ..@@@@....    ..4775....
   * ...@@@@...    ...@@@@...    ...6875...
   * ...@@@@@..    ...@@@@@..    ...47664..
   * ...@.@.@@.    ...@.@.@@.    ...4.5.64.
   * ...@@.@@@.    ...@@.@@@.    ...46.675.
   * ...@@@@@@.    ...@@@@@x.    ...467663.
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 2 roll of paper
   * 
   * 
   * --- Round 5------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ...@@.....    ...@@.....    ...44.....
   * ..@@@@....    ..x@@@....    ..3675....
   * ...@@@@...    ...@@@@...    ...6875...
   * ...@@@@@..    ...@@@@@..    ...47664..
   * ...@.@.@@.    ...@.@.@@.    ...4.5.64.
   * ...@@.@@@.    ...@@.@@@.    ...46.664.
   * ...@@@@@..    ...@@@@@..    ...46765..
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 1 roll of paper
   * 
   * 
   * --- Round 6------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ...@@.....    ...x@.....    ...34.....
   * ...@@@....    ...@@@....    ...575....
   * ...@@@@...    ...@@@@...    ...5875...
   * ...@@@@@..    ...@@@@@..    ...47664..
   * ...@.@.@@.    ...@.@.@@.    ...4.5.64.
   * ...@@.@@@.    ...@@.@@@.    ...46.664.
   * ...@@@@@..    ...@@@@@..    ...46765..
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 1 roll of paper
   * 
   * 
   * --- Round 7------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ....@.....    ....x.....    ....3.....
   * ...@@@....    ...@@@....    ...465....
   * ...@@@@...    ...@@@@...    ...5875...
   * ...@@@@@..    ...@@@@@..    ...47664..
   * ...@.@.@@.    ...@.@.@@.    ...4.5.64.
   * ...@@.@@@.    ...@@.@@@.    ...46.664.
   * ...@@@@@..    ...@@@@@..    ...46765..
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 1 roll of paper
   * 
   * 
   * --- Round 8------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ...@@@....    ...x@@....    ...354....
   * ...@@@@...    ...@@@@...    ...5875...
   * ...@@@@@..    ...@@@@@..    ...47664..
   * ...@.@.@@.    ...@.@.@@.    ...4.5.64.
   * ...@@.@@@.    ...@@.@@@.    ...46.664.
   * ...@@@@@..    ...@@@@@..    ...46765..
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 1 roll of paper
   * 
   * 
   * --- Round 9------------------------------------------------------
   * 
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ..........    ..........    ..........
   * ....@@....    ....@@....    ....44....
   * ...@@@@...    ...@@@@...    ...4775...
   * ...@@@@@..    ...@@@@@..    ...47664..
   * ...@.@.@@.    ...@.@.@@.    ...4.5.64.
   * ...@@.@@@.    ...@@.@@@.    ...46.664.
   * ...@@@@@..    ...@@@@@..    ...46765..
   * ....@@@...    ....@@@...    ....454...
   * 
   * Remove 0 roll of paper
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
