package de.ea234.day10;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.ea234.util.FkString;

class Day10Machine
{
  private String   light_diagram_required_str       = null;

  private String   joltage_requirements_str         = null;

  private String   wiring_schematics_str            = null;

  private String[] wiring_schematics_array          = null;

  int              machine_nr                       = 0;

  int[]            ld_array_required;

  int[]            ld_array_currrent;

  String           combination_min                  = "";

  String           button_press_order_min           = "";

  String           button_press_order_current       = "";

  long             number_of_combination_min        = Integer.MAX_VALUE;

  long             number_of_combination_cur        = Integer.MAX_VALUE;

  long             number_of_button_presses_min     = Integer.MAX_VALUE;

  long             number_of_button_presses_current = Integer.MAX_VALUE;

  public Day10Machine( int pNr, String pInput )
  {
    machine_nr = pNr;

    int light_diagram_pos_end = pInput.indexOf( "]" );

    int joltage_requirements_pos_start = pInput.indexOf( "{" );

    if ( ( light_diagram_pos_end > 0 ) && ( joltage_requirements_pos_start > light_diagram_pos_end ) )
    {
      /*
       * Removing brackets
       */
      light_diagram_required_str = pInput.substring( 1, light_diagram_pos_end );

      joltage_requirements_str = pInput.substring( joltage_requirements_pos_start );

      wiring_schematics_str = pInput.substring( light_diagram_pos_end + 1, joltage_requirements_pos_start ).trim();

      wiring_schematics_array = wiring_schematics_str.split( " " );

      ld_array_required = light_diagram_required_str.chars().map( c -> c == Day10Factory.CHAR_LIGHT_DIAGRAMM_OFF ? 0 : 1 ).toArray();

      ld_array_currrent = light_diagram_required_str.chars().map( c -> 0 ).toArray();
    }
  }

  public long getNrPressCombinationMin()
  {
    return number_of_combination_min;
  }

  public void reset()
  {
    number_of_button_presses_current = 0;
    number_of_combination_cur = Integer.MAX_VALUE;

    for ( int arr_index = 0; arr_index < ld_array_currrent.length; arr_index++ )
    {
      ld_array_currrent[ arr_index ] = 0;
    }
  }

  public boolean checkLights()
  {
    for ( int arr_index = 0; arr_index < ld_array_required.length; arr_index++ )
    {
      if ( ld_array_currrent[ arr_index ] != ld_array_required[ arr_index ] )
      {
        return false; // lights do not match
      }
    }

    return true;
  }

  public void switchButton( int pButton )
  {
    if ( ( pButton >= 0 ) && ( pButton <= ld_array_currrent.length ) )
    {
      ld_array_currrent[ pButton ] = ld_array_currrent[ pButton ] == 0 ? 1 : 0;

      number_of_button_presses_current++;

      button_press_order_current += "," + pButton;
    }
  }

  public String getLightPatternResult()
  {
    StringBuilder result_string = new StringBuilder();

    for ( int v : ld_array_currrent )
    {
      result_string.append( v == 0 ? Day10Factory.CHAR_LIGHT_DIAGRAMM_OFF : Day10Factory.CHAR_LIGHT_DIAGRAMM_ON );
    }

    return result_string.toString();
  }

  public String getLightPatternRequired()
  {
    return light_diagram_required_str;
  }

  public String toString()
  {
    return " REQ " + FkString.getFeldLinksMin( getLightPatternRequired(), 15 ) + " C " + FkString.getFeldLinksMin( getLightPatternResult(), 15 ) + " " + FkString.getFeldRechtsMin( number_of_button_presses_current, 5 ) + "  " + FkString.getFeldRechtsMin( number_of_button_presses_min, 7 ) + "  " + number_of_combination_min + "  " + combination_min;
  }

  public int start( String p_wiring_schematicsm, boolean pKnzDebug )
  {
    List< String > list_combinations = Arrays.stream( p_wiring_schematicsm.replaceAll( " ", "" ).split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    int nr = 0;

    for ( String curr_combi : list_combinations )
    {
      if ( pKnzDebug )
      {
        wl( "nr " + nr + " " + curr_combi );
      }

      startCombination( nr, curr_combi, pKnzDebug );

      if ( number_of_combination_min == 1 )
      {
        break;
      }

      nr++;
    }

    startCombination( 10, combination_min, pKnzDebug );

    if ( pKnzDebug )
    {
      wl( "" );
      wl( toString() );
      wl( "" );
      wl( "Combination press            " + number_of_combination_min );
      wl( "" );
      wl( "number_of_button_presses_min " + number_of_button_presses_min );
      wl( "" );
      wl( "Combination best             " + combination_min );
      wl( "" );
    }

    return 1;
  }

  private int startCombination( int pIndex, String pCombination, boolean pKnzDebug )
  {
    reset();

    String[] btn_combinations_str = pCombination.split( "," );

    int[] array_switches = java.util.Arrays.stream( btn_combinations_str ).mapToInt( s -> Integer.parseInt( s.trim().replaceAll( "[()]", "" ) ) ).toArray();

    int combination_index = 0;

    while ( combination_index < array_switches.length )
    {
      switchButton( array_switches[ combination_index ] );

      combination_index++;
    }

    int result_combination_ok = 0;

    if ( checkLights() )
    {
      long count_combinations = pCombination.chars().filter( ch -> ch == ')' ).count();

      if ( count_combinations < number_of_combination_min ) // might be false, but 

      //if ( number_of_button_presses_min < number_of_button_presses_current ) // error because empty string 
      {
        number_of_button_presses_min = number_of_button_presses_current;

        button_press_order_min = button_press_order_current;

        number_of_combination_min = count_combinations;

        combination_min = pCombination;

        /*
         * Just for entertainment due calculation.
         * Some output, to say "I am alive"
         */

        //if ( pKnzDebug )
        {
          wl( "Nr " + FkString.getFeldRechtsMin( machine_nr, 4 ) + " Index " + FkString.getFeldRechtsMin( pIndex, 5 ) + " New Best " + toString() );
        }

        result_combination_ok = 1;
      }
    }

    return result_combination_ok;
  }

  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
