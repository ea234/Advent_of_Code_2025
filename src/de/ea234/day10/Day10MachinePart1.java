package de.ea234.day10;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.ea234.util.FkString;

class Day10MachinePart1
{
  
  /*
   * File Row Count 189 189
   * 
   * Calculating Machines --------------------------------------------
   * 
   * Nr    0 Index    37 New Best  REQ .#.###       Current .#.###           3  (0,1,2),(3,5),(0,2,4)
   * Nr    1 Index    18 New Best  REQ .##.##.#     Current .##.##.#         2  (0,1,2,3,4,5,6),(0,3,6,7)
   * Nr    2 Index     0 New Best  REQ ..##         Current ..##             1  (2,3)
   *  
   *  ...
   *  
   * Nr  187 Index     5 New Best  REQ .######      Current .######          1  (1,2,3,4,5,6)
   * Nr  188 Index   127 New Best  REQ ..####...    Current ..####...        4  (0,1,2,3,4,6,7),(2,7),(2,3,7),(0,1,3,5,6,7)
   * 
   * Result ----------------------------------------------------------
   * 
   *  REQ .#.###       Current .#.###           3  (0,1,2),(3,5),(0,2,4)
   *  REQ .##.##.#     Current .##.##.#         2  (0,1,2,3,4,5,6),(0,3,6,7)
   *  
   *  ...
   *  
   *  REQ .######      Current .######          1  (1,2,3,4,5,6)
   *  REQ ..####...    Current .###..###        4  (0,1,2,3,4,6,7),(2,7),(2,3,7),(0,1,3,5,6,7)
   * 
   * Result Part 1 481
   * Runtime       00:00:16:577
   * 
   * 
   * Calculating Machines --------------------------------------------
   * 
   * 
   * Calc Machine 0 ----------------------------------------------------------
   * 
   * nr 0 (3)
   * nr 1 (2)
   * nr 2 (1,3)
   * nr 3 (2,3)
   * nr 4 (0,2)
   * nr 5 (0,1)
   * nr 6 (3),(2)
   * nr 7 (3),(1,3)
   * nr 8 (1,3),(2)
   * nr 9 (3),(2,3)
   * nr 10 (2),(2,3)
   * nr 11 (3),(0,2)
   * nr 12 (2),(0,2)
   * nr 13 (3),(0,1)
   * nr 14 (2),(0,1)
   * nr 15 (1,3),(2,3)
   * Nr    0 Index    15 New Best  REQ .##.         Current .##.             2  (1,3),(2,3)
   * nr 16 (1,3),(0,2)
   * nr 17 (2,3),(0,2)
   * nr 18 (1,3),(0,1)
   * nr 19 (2,3),(0,1)
   * nr 20 (0,2),(0,1)
   * nr 21 (3),(1,3),(2)
   * nr 22 (3),(2),(2,3)
   * nr 23 (3),(2),(0,2)
   * nr 24 (3),(2),(0,1)
   * nr 25 (3),(1,3),(2,3)
   * nr 26 (1,3),(2),(2,3)
   * nr 27 (3),(1,3),(0,2)
   * nr 28 (1,3),(2),(0,2)
   * nr 29 (3),(2,3),(0,2)
   * nr 30 (2),(2,3),(0,2)
   * nr 31 (3),(1,3),(0,1)
   * nr 32 (1,3),(2),(0,1)
   * nr 33 (3),(2,3),(0,1)
   * nr 34 (2),(2,3),(0,1)
   * nr 35 (3),(0,2),(0,1)
   * nr 36 (2),(0,2),(0,1)
   * nr 37 (1,3),(2,3),(0,2)
   * nr 38 (1,3),(2,3),(0,1)
   * nr 39 (1,3),(0,2),(0,1)
   * nr 40 (2,3),(0,2),(0,1)
   * nr 41 (3),(1,3),(2),(2,3)
   * nr 42 (3),(1,3),(2),(0,2)
   * nr 43 (3),(2),(2,3),(0,2)
   * nr 44 (3),(1,3),(2),(0,1)
   * nr 45 (3),(2),(2,3),(0,1)
   * nr 46 (3),(2),(0,2),(0,1)
   * nr 47 (3),(1,3),(2,3),(0,2)
   * nr 48 (1,3),(2),(2,3),(0,2)
   * nr 49 (3),(1,3),(2,3),(0,1)
   * nr 50 (1,3),(2),(2,3),(0,1)
   * nr 51 (3),(1,3),(0,2),(0,1)
   * nr 52 (1,3),(2),(0,2),(0,1)
   * nr 53 (3),(2,3),(0,2),(0,1)
   * nr 54 (2),(2,3),(0,2),(0,1)
   * nr 55 (1,3),(2,3),(0,2),(0,1)
   * nr 56 (3),(1,3),(2),(2,3),(0,2)
   * nr 57 (3),(1,3),(2),(2,3),(0,1)
   * nr 58 (3),(1,3),(2),(0,2),(0,1)
   * nr 59 (3),(2),(2,3),(0,2),(0,1)
   * nr 60 (3),(1,3),(2,3),(0,2),(0,1)
   * nr 61 (1,3),(2),(2,3),(0,2),(0,1)
   * nr 62 (3),(1,3),(2),(2,3),(0,2),(0,1)
   * 
   *  REQ .##.         Current .##.             2  (1,3),(2,3)
   * 
   * Combination press  2
   * Combination best   (1,3),(2,3)
   * 
   * 
   * Calc Machine 1 ----------------------------------------------------------
   * 
   * nr 0 (2,3)
   * nr 1 (0,4)
   * nr 2 (0,1,2)
   * nr 3 (0,2,3,4)
   * nr 4 (1,2,3,4)
   * nr 5 (2,3),(0,4)
   * nr 6 (2,3),(0,1,2)
   * nr 7 (0,4),(0,1,2)
   * nr 8 (0,2,3,4),(2,3)
   * nr 9 (0,2,3,4),(0,4)
   * nr 10 (2,3),(1,2,3,4)
   * nr 11 (0,4),(1,2,3,4)
   * nr 12 (0,2,3,4),(0,1,2)
   * nr 13 (0,1,2),(1,2,3,4)
   * nr 14 (0,2,3,4),(1,2,3,4)
   * nr 15 (2,3),(0,4),(0,1,2)
   * nr 16 (0,2,3,4),(2,3),(0,4)
   * nr 17 (2,3),(0,4),(1,2,3,4)
   * nr 18 (0,2,3,4),(2,3),(0,1,2)
   * nr 19 (0,2,3,4),(0,4),(0,1,2)
   * nr 20 (2,3),(0,1,2),(1,2,3,4)
   * nr 21 (0,4),(0,1,2),(1,2,3,4)
   * Nr    1 Index    21 New Best  REQ ...#.        Current ...#.            3  (0,4),(0,1,2),(1,2,3,4)
   * nr 22 (0,2,3,4),(2,3),(1,2,3,4)
   * nr 23 (0,2,3,4),(0,4),(1,2,3,4)
   * nr 24 (0,2,3,4),(0,1,2),(1,2,3,4)
   * nr 25 (0,2,3,4),(2,3),(0,4),(0,1,2)
   * nr 26 (2,3),(0,4),(0,1,2),(1,2,3,4)
   * nr 27 (0,2,3,4),(2,3),(0,4),(1,2,3,4)
   * nr 28 (0,2,3,4),(2,3),(0,1,2),(1,2,3,4)
   * nr 29 (0,2,3,4),(0,4),(0,1,2),(1,2,3,4)
   * nr 30 (0,2,3,4),(2,3),(0,4),(0,1,2),(1,2,3,4)
   * 
   *  REQ ...#.        Current ...#.            3  (0,4),(0,1,2),(1,2,3,4)
   * 
   * Combination press  3
   * Combination best   (0,4),(0,1,2),(1,2,3,4)
   * 
   * 
   * Calc Machine 2 ----------------------------------------------------------
   * 
   * nr 0 (1,2)
   * nr 1 (0,3,4)
   * nr 2 (0,1,2,3,4)
   * nr 3 (0,1,2,4,5)
   * nr 4 (0,3,4),(1,2)
   * nr 5 (0,1,2,3,4),(1,2)
   * nr 6 (0,1,2,4,5),(1,2)
   * nr 7 (0,1,2,3,4),(0,3,4)
   * nr 8 (0,3,4),(0,1,2,4,5)
   * Nr    2 Index     8 New Best  REQ .###.#       Current .###.#           2  (0,3,4),(0,1,2,4,5)
   * nr 9 (0,1,2,3,4),(0,1,2,4,5)
   * nr 10 (0,1,2,3,4),(0,3,4),(1,2)
   * nr 11 (0,3,4),(0,1,2,4,5),(1,2)
   * nr 12 (0,1,2,3,4),(0,1,2,4,5),(1,2)
   * nr 13 (0,1,2,3,4),(0,3,4),(0,1,2,4,5)
   * nr 14 (0,1,2,3,4),(0,3,4),(0,1,2,4,5),(1,2)
   * 
   *  REQ .###.#       Current .###.#           2  (0,3,4),(0,1,2,4,5)
   * 
   * Combination press  2
   * Combination best   (0,3,4),(0,1,2,4,5)
   * 
   * 
   * Result ----------------------------------------------------------
   * 
   *  REQ .##.         Current .##.             2  (1,3),(2,3)
   *  REQ ...#.        Current ...#.            3  (0,4),(0,1,2),(1,2,3,4)
   *  REQ .###.#       Current .###.#           2  (0,3,4),(0,1,2,4,5)
   * 
   * Result Part 1 7
   * Runtime       00:00:00:002
   */
  
  
  private static final char LIGHT_DIAGRAMM_ON_CHAR     = '#';

  private static final int  LIGHT_DIAGRAMM_ON_VALUE    = 1;

  private static final char LIGHT_DIAGRAMM_OFF_CHAR    = '.';

  private static final int  LIGHT_DIAGRAMM_OFF_VALUE   = 0;

  private String            light_diagram_required_str = null;

  private String            combination_min_pattern    = "";

  private long              combination_min_presses    = Integer.MAX_VALUE;

  private int               machine_nr                 = 0;

  private int[]             light_diagram_array_required;

  private int[]             light_diagram_array_currrent;

  Day10MachinePart1( int pNr, String pInput )
  {
    /*                                              1         2         3         4         5         6   
     *                                    0123456789012345678901234567890123456789012345678901234567890123456789
     * pInput                             [.##.] (1,2) (2,3) (1,3,0) (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
     * light_diagram_pos_end           5       |
     * joltage_requirements_pos_start 59                                                             | 
     * 
     */

    machine_nr = pNr;

    int light_diagram_pos_end = pInput.indexOf( "]" );

    int joltage_requirements_pos_start = pInput.indexOf( "{" );

    if ( ( light_diagram_pos_end > 0 ) && ( joltage_requirements_pos_start > light_diagram_pos_end ) )
    {
      /*
       * Removing brackets from light diagramm.
       * So light diagram starts at position 1 (... not position 0)
       */
      light_diagram_required_str = pInput.substring( 1, light_diagram_pos_end );

      light_diagram_array_required = light_diagram_required_str.chars().map( c -> c == LIGHT_DIAGRAMM_OFF_CHAR ? LIGHT_DIAGRAMM_OFF_VALUE : LIGHT_DIAGRAMM_ON_VALUE ).toArray();

      light_diagram_array_currrent = light_diagram_required_str.chars().map( c -> 0 ).toArray();
    }
  }

  public int start( String p_wiring_schematicsm, boolean pKnzDebug )
  {
    /* 
     * pWiringSchematic
     * 
     * (3) ; (2) ; (1,2) ; (2,3) ; (1,3) ; (2,3) ; (0,2) ; (0,1) ; (1,3,0) ; (3), (2) ; (1,2), (3) ; (2,3), (3) ; (3), (1,3) ; (1,2), (2) ; (2,3), (2) ; (1,3),  ....   ; (1,2), (2,3), (1,3,0), (1,3), (2), (2,3), (0,2), (0,1) ; (1,2), (2,3), (1,3,0), (3), (1,3), (2), (2,3), (0,2), (0,1) ;
     * 
     * Constructing a list of Strings from the wiring-schematic.
     * 
     */
    List< String > list_combinations = Arrays.stream( p_wiring_schematicsm.replaceAll( " ", "" ).split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    int nr = 0;

    for ( String curr_combi : list_combinations )
    {
      if ( pKnzDebug )
      {
        wl( "nr " + nr + " " + curr_combi );
      }

      startCombination( nr, curr_combi, pKnzDebug );

      if ( combination_min_presses == 1 )
      {
        break;
      }

      nr++;
    }

    startCombination( 10, combination_min_pattern, pKnzDebug );

    if ( pKnzDebug )
    {
      wl( "" );
      wl( toString() );
      wl( "" );
      wl( "Combination press  " + combination_min_presses );
      wl( "Combination best   " + combination_min_pattern );
      wl( "" );
    }

    return 1;
  }

  private int startCombination( int pIndex, String pCombination, boolean pKnzDebug )
  {
    int result_combination_ok = 0;

    long count_combinations = pCombination.chars().filter( ch -> ch == ')' ).count();

    /*
     * Prevent checking combinations, which will not end up in a better result
     */

    if ( ( count_combinations > 0 ) && ( count_combinations < combination_min_presses ) )
    {
      reset();

      String[] btn_combinations_str = pCombination.split( "," );

      int[] array_switches = java.util.Arrays.stream( btn_combinations_str ).mapToInt( s -> Integer.parseInt( s.trim().replaceAll( "[()]", "" ) ) ).toArray();

      /*
       * Switching all the Buttons according to the button-numbers from the input.
       */

      int combination_index = 0;

      while ( combination_index < array_switches.length )
      {
        switchButton( array_switches[ combination_index ] );

        combination_index++;
      }

      /*
       * Checking the lights after button switches
       */
      if ( checkLights() )
      {
        if ( count_combinations < combination_min_presses )
        {
          combination_min_presses = count_combinations;

          combination_min_pattern = pCombination;

          wl( "Nr " + FkString.getFeldRechtsMin( machine_nr, 4 ) + " Index " + FkString.getFeldRechtsMin( pIndex, 5 ) + " New Best " + toString() );

          result_combination_ok = 1;
        }
      }
    }

    return result_combination_ok;
  }

  public long getNrPressCombinationMin()
  {
    return combination_min_presses;
  }

  private void reset()
  {
    for ( int arr_index = 0; arr_index < light_diagram_array_currrent.length; arr_index++ )
    {
      light_diagram_array_currrent[ arr_index ] = 0;
    }
  }

  private boolean checkLights()
  {
    for ( int arr_index = 0; arr_index < light_diagram_array_required.length; arr_index++ )
    {
      if ( light_diagram_array_currrent[ arr_index ] != light_diagram_array_required[ arr_index ] )
      {
        return false; // lights do not match
      }
    }

    return true;
  }

  private void switchButton( int pButton )
  {
    if ( ( pButton >= 0 ) && ( pButton <= light_diagram_array_currrent.length ) )
    {
      light_diagram_array_currrent[ pButton ] = light_diagram_array_currrent[ pButton ] == LIGHT_DIAGRAMM_OFF_VALUE ? LIGHT_DIAGRAMM_ON_VALUE : LIGHT_DIAGRAMM_OFF_VALUE;
    }
  }

  private String getLightPatternResult()
  {
    StringBuilder result_string = new StringBuilder();

    for ( int light_value : light_diagram_array_currrent )
    {
      result_string.append( light_value == 0 ? LIGHT_DIAGRAMM_OFF_CHAR : LIGHT_DIAGRAMM_ON_CHAR );
    }

    return result_string.toString();
  }

  public String toString()
  {
    return " REQ " + FkString.getFeldLinksMin( light_diagram_required_str, 12 ) + " Current " + FkString.getFeldLinksMin( getLightPatternResult(), 12 ) + " " + FkString.getFeldRechtsMin( combination_min_presses, 5 ) + "  " + combination_min_pattern;
  }

  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
