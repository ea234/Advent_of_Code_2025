package de.ea234.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day09MovieTheater
{
  /*
   *
   * --- Day 9: Movie Theater ---
   * https://adventofcode.com/2025/day/9
   * 
   */

  static class Point
  {
    long position_x, position_y;

    public Point( String pInputLine )
    {
      String[] arr_split = pInputLine.trim().split( "," );

      position_x = Long.parseLong( arr_split[ 0 ] );
      position_y = Long.parseLong( arr_split[ 1 ] );
    }
    
    public long getX() { return position_x; }
    public long getY() { return position_y; }
    
    public long calcSquare( Point pPointB ) 
    {
      long height = position_x < pPointB.getX() ? pPointB.getX() - position_x :  position_x - pPointB.getX(); 
      long width = position_y < pPointB.getY() ? pPointB.getY() - position_y :  position_y - pPointB.getY(); 
      
      return ( height + 1 ) * ( width + 1 );
    }
    
    public String toString()
    {
      return "" + position_x + "," + position_y;
    }
  }

  public static void main( String[] args )
  {
    String test_content = "7,1;11,1;11,7;9,7;9,5;2,5;2,3;7,3";

    List< String > test_content_list = Arrays.stream( test_content.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calcNewGrid( test_content_list, true );

    calcNewGrid( getListProd(), false );
  }

  private static void calcNewGrid( List< String > pListSplitPattern, boolean pKnzDebug )
  {
    List< Point > l_cb = new ArrayList< Point >();

    for ( String input_str : pListSplitPattern )
    {
      if ( input_str.length() > 0 )
      {
        Point new_con_box = new Point( input_str );

        l_cb.add( new_con_box );
      }
    }

    long max_square = 0;
    
    for ( Point new_con_box1 : l_cb )
    {
      for ( Point new_con_box2 : l_cb )
      {
        long cur_s =  new_con_box1.calcSquare( new_con_box2 );

        wl( new_con_box1.toString() + " - " + new_con_box2.toString() + " = " + cur_s + " (Max " + max_square + ")" );

        if ( cur_s > max_square ) 
        {
          
          max_square = cur_s;
        }
      }
    }
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day9_input.txt";

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
