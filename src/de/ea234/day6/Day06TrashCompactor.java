package de.ea234.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06TrashCompactor
{
  /*
  * --- Day 6: Trash Compactor ---
  * https://adventofcode.com/2025/day/6
  * 
  *
  * Result Part 1 7098065460541
  * Result Part 2 13807151830618
  * 
  * 
  * ------------------------------------------------------------------------
  * 
  * Setting up Accumulators
  * 
  * Aufgabe nr 0 Modus 3 1 Aufgabe *
  * Aufgabe nr 1 Modus 1 0 Aufgabe +
  * Aufgabe nr 2 Modus 3 1 Aufgabe *
  * Aufgabe nr 3 Modus 1 0 Aufgabe +
  * 123 328 51 64 
  * 
  * Addint Input from line 0
  * 
  * Aufgabe nr 0 Modus 3 123 Aufgabe *,123
  * Aufgabe nr 1 Modus 1 328 Aufgabe +,328
  * Aufgabe nr 2 Modus 3 51 Aufgabe *,51
  * Aufgabe nr 3 Modus 1 64 Aufgabe +,64
  *  45 64 387 23 
  * 
  * Addint Input from line 1
  * 
  * Aufgabe nr 0 Modus 3 5535 Aufgabe *,123,45
  * Aufgabe nr 1 Modus 1 392 Aufgabe +,328,64
  * Aufgabe nr 2 Modus 3 19737 Aufgabe *,51,387
  * Aufgabe nr 3 Modus 1 87 Aufgabe +,64,23
  *  6 98 215 314 
  * 
  * Addint Input from line 2
  * 
  * Aufgabe nr 0 Modus 3 33210 Aufgabe *,123,45,6
  * Aufgabe nr 1 Modus 1 490 Aufgabe +,328,64,98
  * Aufgabe nr 2 Modus 3 4243455 Aufgabe *,51,387,215
  * Aufgabe nr 3 Modus 1 401 Aufgabe +,64,23,314
  * 
  * Result Part 1 4277556
  */

  public static void main( String[] args )
  {
    String test_content = "123 328  51 64 , 45 64  387 23 ,  6 98  215 314,*   +   *   +  ";

    test_content = "123 328  51 64 , 45 64  387 23 ,  6 98  215 314,*1  +2  *3  +4 ";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).collect( Collectors.toList() );

    //calcPart1( getListProd(), false );
    //calcPart1( test_content_list, true );

    calcPart2( getListProd(), false );
    calcPart2( test_content_list, true );
  }

  public static void calcPart2( List< String > pList, boolean pKnzDebug )
  {
    int input_index_last_line = pList.size() - 1;

    String last_line_string = pList.get( input_index_last_line );

    wl( "" );
    wl( "Setting up Accumulators" );
    wl( "" );

    if ( pKnzDebug )
    {
      wl( "Last Line \"" + last_line_string + "\"" );
    }

    List< Day06Accumulator > list_accumulators = new ArrayList< Day06Accumulator >();

    int count_nr_accumulators = 0;

    int column_start = -1;

    int input_str_index = 0;

    while ( input_str_index < last_line_string.length() )
    {
      if ( ( last_line_string.charAt( input_str_index ) == '+' ) || ( last_line_string.charAt( input_str_index ) == '*' ) )
      {
        if ( column_start != -1 )
        {
          count_nr_accumulators++;

          Day06Accumulator new_accumulator = new Day06Accumulator( count_nr_accumulators, "" + last_line_string.charAt( column_start ), column_start, input_str_index - 1 );

          list_accumulators.add( new_accumulator );

          if ( pKnzDebug )
          {
            wl( new_accumulator.toString() );
          }
        }

        column_start = input_str_index;
      }

      input_str_index++;
    }

    /*
     * First input line is longer than the last line.
     * 
     * The end-index for the last accumulator is the length
     * of the first input line.
     */
    String first_input_line = pList.get( 0 );

    wl( "+ Accumulator " + column_start + " = " + first_input_line.length() + " " + last_line_string.length() );

    count_nr_accumulators++;

    Day06Accumulator new_accumulator = new Day06Accumulator( count_nr_accumulators, "" + last_line_string.charAt( column_start ), column_start, first_input_line.length() - 1 );

    list_accumulators.add( new_accumulator );

    wl( new_accumulator.toString() );

    wl( "Number of Accumulators " + count_nr_accumulators );
    wl( "" );
    wl( "Adding Input-Lines to Accumulators" );
    wl( "" );

    for ( Day06Accumulator current_accumulator : list_accumulators )
    {
      current_accumulator.calcInputList( pList );
    }

    if ( pKnzDebug )
    {
      for ( Day06Accumulator current_accumulator : list_accumulators )
      {
        wl( current_accumulator.toString() );
      }
    }

    BigDecimal result = new BigDecimal( "0" );

    for ( Day06Accumulator current_accumulator : list_accumulators )
    {
      result = result.add( current_accumulator.getAccumulatorValue() );
    }

    wl( "" );
    wl( "Result Part 2 " + result.toPlainString() );
  }

  public static void calcPart1( List< String > pList, boolean pKnzDebug )
  {
    int input_index_last_line = pList.size() - 1;
    int input_index_current_line = 0;

    String last_line = pList.get( input_index_last_line );

    String[] range_split = last_line.trim().replaceAll( " +", " " ).split( " " );

    wl( "" );
    wl( "Setting up Accumulators" );
    wl( "" );

    List< Day06Accumulator > list_accumulators = new ArrayList< Day06Accumulator >();

    int count_nr_accumulators = range_split.length;

    for ( int column_index = 0; column_index < count_nr_accumulators; column_index++ )
    {
      Day06Accumulator new_accumulator = new Day06Accumulator( column_index, range_split[ column_index ], -1, -1 );

      list_accumulators.add( new_accumulator );

      wl( new_accumulator.toString() );
    }

    while ( input_index_current_line < input_index_last_line )
    {
      String current_input_line = pList.get( input_index_current_line );

      wl( current_input_line );

      String[] array_current_line_split = current_input_line.trim().replaceAll( " +", " " ).split( " " );

      if ( count_nr_accumulators != array_current_line_split.length )
      {
        wl( "Error in Line " + input_index_current_line + " count_nr_accumulators " + count_nr_accumulators + " array_current_line_split.length " + array_current_line_split.length );

        return;
      }
      else
      {
        if ( pKnzDebug )
        {
          wl( "" );
          wl( "Addint Input from line " + input_index_current_line );
          wl( "" );
        }

        for ( int column_index = 0; column_index < count_nr_accumulators; column_index++ )
        {
          Day06Accumulator current_accumulator = list_accumulators.get( column_index );

          current_accumulator.addInput( array_current_line_split[ column_index ] );

          if ( pKnzDebug )
          {
            wl( current_accumulator.toString() );
          }
        }
      }

      input_index_current_line++;
    }

    BigDecimal result = new BigDecimal( "0" );

    for ( Day06Accumulator current_accumulator : list_accumulators )
    {
      result = result.add( current_accumulator.getAccumulatorValue() );
    }

    wl( "" );
    wl( "Result Part 1 " + result.toPlainString() );
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day6_input.txt";

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
