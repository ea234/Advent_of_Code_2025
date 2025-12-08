package de.ea234.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import de.ea234.util.FkString;

public class Day08Playground
{

  /*
   *
   * --- Day 8: Playground ---
   * https://adventofcode.com/2025/day/8
   * 
   */

  public static void main( String[] args )
  {
    String test_content = ";;162,817,812;57,618,57;906,360,560;592,479,940;352,342,300;466,668,158;542,29,236;431,825,988;739,650,466;52,470,668;216,146,977;819,987,18;117,168,530;805,96,715;346,949,466;970,615,88;941,993,340;862,61,35;984,92,344;425,690,689";

    List< String > test_content_list = Arrays.stream( test_content.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calcNewGrid( test_content_list, true );
    
    calcNewGrid( getListProd(), false );
  }

  private static void calcNewGrid( List< String > pListSplitPattern, boolean pKnzDebug )
  {
    if ( pKnzDebug )
    {
      for ( String input_str : pListSplitPattern )
      {
        wl( input_str );
      }
    }

    wl( "" );
    wl( "Creating Connection-Box list " );
    wl( "" );

    List< Day08ConnectionBox > l_cb = new ArrayList< Day08ConnectionBox >();

    long connection_box_id = 0;

    for ( String input_str : pListSplitPattern )
    {
      if ( input_str.length() > 6 )
      {
        Day08ConnectionBox new_con_box = new Day08ConnectionBox( connection_box_id, input_str );

        l_cb.add( new_con_box );

        connection_box_id++;
      }
    }

    l_cb.sort( ( con_box_1, con_box_2 ) ->  (con_box_1.getPositionX() < con_box_2.getPositionX() ? -1 : 0 ) );

    wl( "Connection-Box count " + l_cb.size() );
    wl( "" );

    if ( pKnzDebug )
    {
      for ( Day08ConnectionBox new_con_box : l_cb )
      {
        wl( new_con_box.toStringF() );
      }
    }

    /*
     * Gruppen bilden
     * 
     * Max Ausdehnungen berechnen
     * max x
     * max y
     * max z
     */
    long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE;
    long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;
    long minZ = Long.MAX_VALUE, maxZ = Long.MIN_VALUE;

    for ( Day08ConnectionBox new_con_box : l_cb )
    {
      if ( new_con_box.getPositionX() < minX ) minX = new_con_box.getPositionX();
      if ( new_con_box.getPositionX() > maxX ) maxX = new_con_box.getPositionX();
      if ( new_con_box.getPositionY() < minY ) minY = new_con_box.getPositionY();
      if ( new_con_box.getPositionY() > maxY ) maxY = new_con_box.getPositionY();
      if ( new_con_box.getPositionZ() < minZ ) minZ = new_con_box.getPositionZ();
      if ( new_con_box.getPositionZ() > maxZ ) maxZ = new_con_box.getPositionZ();
    }

    wl( "" );
    wl( "Min X: " + minX + " Max X: " + maxX );
    wl( "Min Y: " + minY + " Max X: " + maxY );
    wl( "Min Z: " + minZ + " Max X: " + maxZ );
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day8_input.txt";

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
