package de.ea234.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.ea234.util.FkString;
import de.ea234.util.FkSystem;

public class Day10Factory
{

  /*
   * --- Day 10: Factory ---
   * https://adventofcode.com/2025/day/10
   * 
   * 
   * Result Part 1 481
   * Runtime 00:01:26:965
   */

  public static final char CHAR_LIGHT_DIAGRAMM_ON  = '#';

  public static final char CHAR_LIGHT_DIAGRAMM_OFF = '.';

  public static void main( String[] args )
  {
    String test_content = "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7};[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2};[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}";

    List< String > test_content_list = Arrays.stream( test_content.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calcMachine( 1, "[.##.] (1,2) (2,3) (1,3,0) (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}", true );
    //calcInput( "[.##.] (0,1,2,3)  (0,2,3) (0,3) {3,5,4,7}", true );

    //calcInput( test_content_list, true );

    calcPart1( getListProd(), false );
  }

  private static List< Day10Machine > list_machines = new ArrayList< Day10Machine >();

  private static void calcPart1( List< String > pListInput, boolean pKnzDebug )
  {
    long time_start = System.currentTimeMillis();

    int machine_nr = 0;

    for ( String input_str : pListInput )
    {
      calcMachine( machine_nr, input_str, pKnzDebug );

      machine_nr++;
    }

    long result_p1 = 0;

    for ( Day10Machine cm : list_machines )
    {
      wl( cm.toString() );

      result_p1 += cm.getNrPressCombinationMin();
    }

    wl( "Result Part 1 " + result_p1 );

    long time_end = System.currentTimeMillis();

    wl( "Runtime " + FkSystem.getDebugRuntime( time_end - time_start ) );
  }

  private static int calcMachine( int pNr, String pInput, boolean pKnzDebug )
  {
    int result_value = -1;

    int light_diagram_pos_end = pInput.indexOf( "]" );

    int joltage_requirements_pos_start = pInput.indexOf( "{" );

    if ( ( light_diagram_pos_end > 0 ) && ( joltage_requirements_pos_start > light_diagram_pos_end ) )
    {
      String wiring_schematics_str = pInput.substring( light_diagram_pos_end + 1, joltage_requirements_pos_start ).trim();

      Day10Machine cur_machine = new Day10Machine( pNr, pInput );

      String str_combinations = Day10SubSetGenerator.getStringSubSets( wiring_schematics_str );

      cur_machine.start( str_combinations, pKnzDebug );

      list_machines.add( cur_machine );
    }

    return result_value;
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day10_input.txt";

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
