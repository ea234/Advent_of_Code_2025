package de.ea234.day10;

import java.util.List;

import de.ea234.day10.Day10Factory.WiringScheme;
import de.ea234.util.FkString;

public class Day10Machine
{
  private String   light_diagram_required_str       = null;

  private String   joltage_requirements_str         = null;

  private String   wiring_schematics_str            = null;

  private String[] wiring_schematics_array          = null;

  int[]            ld_array_required;

  int[]            ld_array_currrent;

  String           button_press_order_min           = "";

  String           button_press_order_current       = "";

  long             number_of_button_presses_min     = Integer.MAX_VALUE;

  long             number_of_button_presses_current = Integer.MAX_VALUE;

  public void reset()
  {
    number_of_button_presses_current = Integer.MAX_VALUE;

    for ( int arr_index = 0; arr_index < ld_array_currrent.length; arr_index++ )
    {
      ld_array_currrent[ arr_index ] = 0;
    }
  }

  public boolean checkLights()
  {
    for ( int arr_index = 0; arr_index < ld_array_required.length; arr_index++ )
    {
      if ( ld_array_currrent[ arr_index ] != ld_array_currrent[ arr_index ] )
      {
        return false; // lights do not match
      }
    }

    return true;
  }

  public boolean checkBest()
  {
    if ( checkLights() )
    {
      if ( number_of_button_presses_current < number_of_button_presses_min )
      {
        number_of_button_presses_min = number_of_button_presses_current;

        button_press_order_min = button_press_order_current;

        return true;
      }
    }

    return false;
  }

  public void switchButton( int pButton )
  {
    if ( ( pButton >= 0 ) && ( pButton <= ld_array_currrent.length ) )
    {
      ld_array_currrent[ pButton ] = ld_array_currrent[ pButton ] == 0 ? 1 : 0;

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
    return " " + getLightPatternRequired() + " " + getLightPatternResult() + " " + FkString.getFeldRechtsMin( number_of_button_presses_current, 5 ) + "  " + FkString.getFeldRechtsMin( number_of_button_presses_min, 7 );
  }

  public void Day10Machne( String pInput )
  {
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

  /*
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   *
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   */

//  
//  
//
//  public static int testButtonPress2( String pLdVorgabe, int[] pCombination, String p_wiring_schematicsm )
//  {
//    
//    
//    
//    
//    
//    
//    
//    int[] ld_array_currrent = pLdVorgabe.chars().map( c -> 0 ).toArray();
//
//    int number_of_button_presses = 0;
//
//    int index_combination = 0;
//
//    while ( index_combination < pCombination.length )
//    {
//      WiringScheme ws_c = list_wiring_schematicsm.get( index_combination );
//
//      int index_wiring_scheme = 0;
//
//      while ( index_wiring_scheme < ws_c.getLength() )
//      {
//        ld_array_currrent[ ws_c.getIndex( index_wiring_scheme ) ] = ld_array_currrent[ ws_c.getIndex( index_wiring_scheme ) ] == 0 ? 1 : 0;
//
//        number_of_button_presses++;
//
//        index_wiring_scheme++;
//      }
//
//      index_combination++;
//    }
//
//    StringBuilder result_string = new StringBuilder();
//
//    for ( int v : ld_array_currrent )
//    {
//      result_string.append( v == 0 ? CHAR_LIGHT_DIAGRAMM_OFF : CHAR_LIGHT_DIAGRAMM_ON );
//    }
//
//    String ld_pattern_after_button_presses = result_string.toString();
//
//    wl( "SB " + ld_pattern_after_button_presses );
//
//    if ( ld_pattern_after_button_presses.equalsIgnoreCase( pLdVorgabe ) )
//    {
//      return number_of_button_presses;
//    }
//
//    return Integer.MAX_VALUE;
//  }
//  
//  

}
