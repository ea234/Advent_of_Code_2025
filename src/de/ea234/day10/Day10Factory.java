package de.ea234.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10Factory
{
  /*
   * --- Day 10: Factory ---
   * https://adventofcode.com/2025/day/10
   * 
   * [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
   * light_diagram_str        =>.##.<
   * joltage_requirements_str =>{3,5,4,7}<
   * wiring_schematics_str    =>(3) (1,3) (2) (2,3) (0,2) (0,1)<
   * Nr 0  3
   * Nr 1  1 3
   * Nr 2  2
   * Nr 3  2 3
   * Nr 4  0 2
   * Nr 5  0 1
   * 
   * light_diagram_cur   =>....<
   * light_diagram_ref   =>.##.<
   * [0, 0, 0, 0]
   * SB .##.
   * number_of_button_presses = 4
   */

  public static final char CHAR_LIGHT_DIAGRAMM_ON  = '#';

  public static final char CHAR_LIGHT_DIAGRAMM_OFF = '.';

  public static void main( String[] args )
  {
    String test_content = "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7};[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2};[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}";

    List< String > test_content_list = Arrays.stream( test_content.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calcInput( "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}", true );
  }

  private static void calcNewGrid( List< String > pListInput, boolean pKnzDebug )
  {
    for ( String input_str : pListInput )
    {
      calcInput( input_str, pKnzDebug );
    }
  }

  static class WiringScheme
  {
    int   scheme_index   = 0;

    int[] array_switches = null;

    public WiringScheme( int pNr, String pInput )
    {
      String[] btn_i = pInput.split( "," );

      array_switches = java.util.Arrays.stream( btn_i ).mapToInt( s -> Integer.parseInt( s.replaceAll( "[()]", "" ) ) ).toArray();

      scheme_index = pNr;
    }

    public int getIndex( int pIndex )
    {
      return (int) array_switches[ pIndex ];
    }

    public int getLength()
    {
      return array_switches.length;
    }

    public int[] getArray()
    {
      return array_switches;
    }

    public String toString()
    {
      String x_res = "Nr " + scheme_index + " ";

      for ( int akt_nr = 0; akt_nr < array_switches.length; akt_nr++ )
      {
        x_res += " " + array_switches[ akt_nr ];
      }

      return x_res;
    }
  }

  private static int calcInput( String pInput, boolean pKnzDebug )
  {
    int result_value = -1;

    int light_diagram_pos_end = pInput.indexOf( "]" );

    int joltage_requirements_pos_start = pInput.indexOf( "{" );

    if ( ( light_diagram_pos_end > 0 ) && ( joltage_requirements_pos_start > light_diagram_pos_end ) )
    {
      /*
       * Removing brackets
       */
      String light_diagram_str = pInput.substring( 1, light_diagram_pos_end );

      String joltage_requirements_str = pInput.substring( joltage_requirements_pos_start );

      String wiring_schematics_str = pInput.substring( light_diagram_pos_end + 1, joltage_requirements_pos_start ).trim();

      if ( pKnzDebug )
      {
        wl( "" );
        wl( pInput );

        wl( "light_diagram_str        =>" + light_diagram_str + "<" );
        wl( "joltage_requirements_str =>" + joltage_requirements_str + "<" );
        wl( "wiring_schematics_str    =>" + wiring_schematics_str + "<" );

        String[] wiring_schematics_array = wiring_schematics_str.split( " " );

        List< WiringScheme > list_wiring_schematicsm = new ArrayList< WiringScheme >();

        int index_nr = 0;

        for ( String wiring_schematics_curr : wiring_schematics_array )
        {
          WiringScheme neu_instanzwr = new WiringScheme( index_nr, wiring_schematics_curr );

          list_wiring_schematicsm.add( neu_instanzwr );

          index_nr++;
        }

        for ( WiringScheme wiring_schematics_curr : list_wiring_schematicsm )
        {
          wl( wiring_schematics_curr.toString() );
        }

        String light_diagram_cur = ".".repeat( light_diagram_str.length() );

        wl( "" );
        wl( "light_diagram_cur   =>" + light_diagram_cur + "<" );
        wl( "light_diagram_ref   =>" + light_diagram_str + "<" );

        int[] ld_array_currrent = light_diagram_str.chars().map( c -> 0 ).toArray();

        wl( Arrays.toString( ld_array_currrent ) );

        int[] arr1 = { 0, 3, 4 };

        int number_of_button_presses = testButtonPress2( light_diagram_str, arr1, list_wiring_schematicsm );

        wl( "number_of_button_presses = " + number_of_button_presses );
      }
    }

    return result_value;
  }

  /*
   * Rekursion
   * - Abbruchbedingung, wenn geforderte Stellung erreicht ist.
   * 
   * 
   * 
   */

  public static int testButtonPress2( String pLdVorgabe, int[] pCombination, List< WiringScheme > list_wiring_schematicsm )
  {
    int[] ld_array_currrent = pLdVorgabe.chars().map( c -> 0 ).toArray();

    int number_of_button_presses = 0;

    int index_combination = 0;

    while ( index_combination < pCombination.length )
    {
      WiringScheme ws_c = list_wiring_schematicsm.get( index_combination );

      int index_wiring_scheme = 0;

      while ( index_wiring_scheme < ws_c.getLength() )
      {
        ld_array_currrent[ ws_c.getIndex( index_wiring_scheme ) ] = ld_array_currrent[ ws_c.getIndex( index_wiring_scheme ) ] == 0 ? 1 : 0;

        number_of_button_presses++;

        index_wiring_scheme++;
      }

      index_combination++;
    }

    StringBuilder result_string = new StringBuilder();

    for ( int v : ld_array_currrent )
    {
      result_string.append( v == 0 ? CHAR_LIGHT_DIAGRAMM_OFF : CHAR_LIGHT_DIAGRAMM_ON );
    }

    String ld_pattern_after_button_presses = result_string.toString();

    wl( "SB " + ld_pattern_after_button_presses );

    if ( ld_pattern_after_button_presses.equalsIgnoreCase( pLdVorgabe ) )
    {
      return number_of_button_presses;
    }

    return Integer.MAX_VALUE;
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
