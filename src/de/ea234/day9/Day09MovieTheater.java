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
  static class LocationPoint
  {
    long position_x, position_y;

    public LocationPoint( String pInputLine )
    {
      String[] arr_split = pInputLine.trim().split( "," );

      position_x = Long.parseLong( arr_split[ 0 ] );
      position_y = Long.parseLong( arr_split[ 1 ] );
    }

    public long getX()
    {
      return position_x;
    }

    public long getY()
    {
      return position_y;
    }

    public long calcSquare( LocationPoint pPointB )
    {
      long height = position_x < pPointB.getX() ? pPointB.getX() - position_x : position_x - pPointB.getX();
      long width = position_y < pPointB.getY() ? pPointB.getY() - position_y : position_y - pPointB.getY();

      return ( height + 1 ) * ( width + 1 );
    }

    public boolean withIn( LocationPoint pPointB )
    {
      if ( ( pPointB.getX() < position_x ) || ( pPointB.getX() > position_x ) )
      {
        return false;
      }

      if ( ( pPointB.getY() < position_y ) || ( pPointB.getY() > position_y ) )
      {
        return false;
      }

      return true;
    }

    public String toString()
    {
      return "" + position_x + "," + position_y;
    }
  }

  static class Line
  {
    long position_x1, position_y1;

    long position_x2, position_y2;

    public Line( LocationPoint pP1, LocationPoint pP2 )
    {
      long x1 = pP1.getX();
      long y1 = pP1.getY();
      long x2 = pP2.getX();
      long y2 = pP2.getY();

      if ( x1 > x2 )
      {
        position_x1 = x1;
        position_x2 = x2;
      }
      else
      {
        position_x1 = x2;
        position_x2 = x1;
      }
    }

    public boolean isPointOnline( LocationPoint pPointB )
    {
      if ( pPointB.getY() == position_y1 )
      {
        if ( ( pPointB.getX() >= position_x1 ) || ( pPointB.getX() <= position_x2 ) )
        {
          return true;
        }
      }

      if ( pPointB.getX() == position_x1 )
      {
        if ( ( pPointB.getY() >= position_y1 ) || ( pPointB.getY() <= position_y2 ) )
        {
          return false;
        }
      }

      return true;
    }

    public boolean isPointOnline( Rectangle pR )
    {
      boolean kx1 = isPointOnline( pR.getX1(), pR.getY1() );
      boolean kx2 = isPointOnline( pR.getX1(), pR.getY2() );
      boolean kx3 = isPointOnline( pR.getX2(), pR.getY1() );
      boolean kx4 = isPointOnline( pR.getX2(), pR.getY2() );

      return kx1 && kx2 && kx3 && kx4;
    }

    public boolean isPointOnline( long pX, long pY )
    {
      if ( pY == position_y1 )
      {
        if ( ( pX >= position_x1 ) || ( pX <= position_x2 ) )
        {
          return true;
        }
      }

      if ( pX == position_x1 )
      {
        if ( ( pY >= position_y1 ) || ( pY <= position_y2 ) )
        {
          return false;
        }
      }

      return true;
    }

  }

  static class Rectangle
  {
    long position_x1, position_y1;

    long position_x2, position_y2;

    public Rectangle( LocationPoint pP1, LocationPoint pP2 )
    {
      long x1 = pP1.getX();
      long y1 = pP1.getY();
      long x2 = pP2.getX();
      long y2 = pP2.getY();

      if ( x1 > x2 )
      {
        position_x1 = x1;
        position_x2 = x2;
      }
      else
      {
        position_x1 = x2;
        position_x2 = x1;
      }

//      public boolean withIn( LocationPoint pPointB )
//      {
//        if ( ( pPointB.getX() < position_x1 ) || ( pPointB.getX() > position_x2 ) )
//        {
//          return false;
//        }
//
//        if ( ( pPointB.getY() < position_y1 ) || ( pPointB.getY() > position_y2 ) )
//        {
//          return false;
//        }
//
//        return true;
//      }

    }

    public long getX1()
    {
      return position_x1;
    }

    public long getX2()
    {
      return position_x2;
    }

    public long getY1()
    {
      return position_y1;
    }

    public long getY2()
    {
      return position_y2;
    }

    public long calcSquare()
    {
      long height = position_x2 - position_x1;
      long width = position_y2 - position_y1;

      return ( height + 1 ) * ( width + 1 );
    }

    public boolean withIn( LocationPoint pPointB )
    {
      if ( ( pPointB.getX() < position_x1 ) || ( pPointB.getX() > position_x2 ) )
      {
        return false;
      }

      if ( ( pPointB.getY() < position_y1 ) || ( pPointB.getY() > position_y2 ) )
      {
        return false;
      }

      return true;
    }

    public String toString()
    {
      return "" + position_x1 + "," + position_y1 + " - " + position_x2 + "," + position_y2;
    }
  }

  public static void main( String[] args )
  {
    String test_content = "7,1;11,1;11,7;9,7;9,5;2,5;2,3;7,3";

    List< String > test_content_list = Arrays.stream( test_content.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calcNewGrid( test_content_list, true );

//    calcNewGrid( getListProd(), false );
//    
//    Point a =  new Point( "2,5" );
//    Point b =  new Point( "11,1" );
//    
//    long cur_s =  a.calcSquare( b );
//

//    wl( a.toString() + " - " + b.toString() + " = " + cur_s );
//

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
    wl( "Creating Location Point List" );
    wl( "" );

    List< LocationPoint > list_locaction_points = new ArrayList< LocationPoint >();

    for ( String input_str : pListSplitPattern )
    {
      if ( input_str.length() > 0 )
      {
        LocationPoint location_point = new LocationPoint( input_str );

        list_locaction_points.add( location_point );
      }
    }

    wl( "Points count " + list_locaction_points.size() );
    wl( "" );
    wl( "Calc Max-Square Part 1" );

    List< Rectangle > list_rectangles = new ArrayList< Rectangle >();

    long max_square = 0;

    for ( LocationPoint location_point1 : list_locaction_points )
    {
      for ( LocationPoint location_point2 : list_locaction_points )
      {
        Rectangle rect_new = new Rectangle( location_point1, location_point2 );

        list_rectangles.add( rect_new );

        long cur_s = rect_new.calcSquare();// location_point1.calcSquare( location_point2 );

        if ( cur_s > max_square )
        {
          wl( location_point1.toString() + " - " + location_point2.toString() + " = " + cur_s + " (Max " + max_square + ")" );

          max_square = cur_s;
        }
      }
    }

    wl( "" );
    wl( "Calc Max-Square Part 2" );

    wl( "" );
    wl( "Finding Lines" );

    max_square = 0;

    List< Line > list_lines = new ArrayList< Line >();

    for ( LocationPoint location_point1 : list_locaction_points )
    {
      for ( LocationPoint location_point2 : list_locaction_points )
      {
        if ( location_point1.getX() == location_point2.getX() )
        {
          /*
           * punkte auf gleicher X achse
           */
          Line new_line = new Line( location_point1, location_point2 );

          list_lines.add( new_line );
        }
        else if ( location_point1.getY() == location_point2.getY() )
        {
          /*
           * punkte auf gleicher X achse
           */
          Line new_line = new Line( location_point1, location_point2 );

          list_lines.add( new_line );
        }
      }
    }

    long max_p2 = 0;

    for ( Rectangle cur_rect : list_rectangles )
    {
      for ( Line line_curr : list_lines )
      {
        if ( line_curr.isPointOnline( cur_rect ) )
        {
          max_p2 = cur_rect.calcSquare();
        }
      }
    }

    wl( "max_p2 = " + max_p2 );

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
