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

      position_x = Long.parseLong( arr_split[ 1 ] );
      position_y = Long.parseLong( arr_split[ 0 ] );
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

      if ( y1 > y2 )
      {
        position_y1 = y1;
        position_y2 = y2;
      }
      else
      {
        position_y1 = y2;
        position_y2 = y1;
      }

      wl( "Line x = new Line( " + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ");" );
    }

    public Line( long x1, long y1, long x2, long y2 )
    {
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

      if ( y1 > y2 )
      {
        position_y1 = y1;
        position_y2 = y2;
      }
      else
      {
        position_y1 = y2;
        position_y2 = y1;
      }
    }

    public boolean isPointOnline( LocationPoint pPointB )
    {
      return isPointOnline( pPointB.getX(), pPointB.getY() );
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
          return true;
        }
      }

      return false;
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

      if ( y1 > y2 )
      {
        position_y1 = y1;
        position_y2 = y2;
      }
      else
      {
        position_y1 = y2;
        position_y2 = y1;
      }
    }

    public Rectangle( long x1, long y1, long x2, long y2 )
    {
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

      if ( y1 > y2 )
      {
        position_y1 = y1;
        position_y2 = y2;
      }
      else
      {
        position_y1 = y2;
        position_y2 = y1;
      }
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
//
    calcNewGrid( getListProd(), false );

    LocationPoint a = new LocationPoint( "2,5" );
    LocationPoint b = new LocationPoint( "11,1" );

    Rectangle rr = new Rectangle( a, b );

    LocationPoint a1 = new LocationPoint( "2,3" );
    LocationPoint b1 = new LocationPoint( "9,5" );

    Rectangle rrvalid = new Rectangle( a1, b1 );

//    Line x = new Line( 7, 1, 11, 1);
//    Line x = new Line( 7, 1, 7, 3);

//    Line x = new Line( 11, 1, 7, 1);
//    Line x = new Line( 11, 1, 11, 7);
//    Line x = new Line( 11, 7, 11, 1);
//    Line x = new Line( 11, 7, 9, 7);
//  
    // Line x = new Line( 9, 7, 11, 7);
//    Line x = new Line( 9, 7, 9, 5);
//    Line x = new Line( 9, 5, 9, 7);
//    Line x = new Line( 9, 5, 2, 5);
//    Line x = new Line( 2, 5, 9, 5);
//    Line x = new Line( 2, 5, 2, 3);

//    Line x = new Line( 2, 3, 2, 5);
//    Line x = new Line( 2, 3, 7, 3);
//    Line x = new Line( 7, 3, 7, 1);
//    Line x = new Line( 7, 3, 2, 3);



//    wl( " " + x1.isPointOnline( rr1 ) );

//    wl( a.toString() + " - " + b.toString() + " = " + cur_s );
//

//          012345678901234
//        0 ..............
//        1 ..c....RgggA..
//        2 .......g...g..
//        3 ..RggggR...g..
//        4 ..g........g..
//        5 ..BggggggR.g..
//        6 .........g.g..
//        7 .........RgR..
//        8 ..............

//
//
//        C liegt ausserhalb 
//
//         C = 2 , 1 
//
//     befinded sich mein Eckpunkt auf einer Linie
//
//        * 7,1
//        * 11,1
//        * 11,7
//        * 9,7
//        * 9,5
//        * 2,5
//        * 2,3
//        * 7,3

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
        if ( location_point1.equals( location_point2 ) )
        {
          // keine Verbindung zu sich selber 
        }
        else if ( location_point1.getX() == location_point2.getX() )
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

    for ( Rectangle pR : list_rectangles )
    {
      /*
       * while schleife
       * - alle ecken muessen auf einem punkt liegen.
       * - checke die punkte dahingehend ab, dass diese auf 
       *   einer linie liegen.
       * 
       * nach der While-Schleife mussen alle Ecken true haben.
       */

      boolean kx1 = false;
      boolean kx2 = false;
      boolean kx3 = false;
      boolean kx4 = false;

      for ( Line line_curr : list_lines )
      {
        kx1 |= line_curr.isPointOnline( pR.getX1(), pR.getY1() );
        kx2 |= line_curr.isPointOnline( pR.getX1(), pR.getY2() );
        kx3 |= line_curr.isPointOnline( pR.getX2(), pR.getY1() );
        kx4 |= line_curr.isPointOnline( pR.getX2(), pR.getY2() );
      }

      if ( kx1 && kx2 && kx3 && kx4 )
      {
        if ( pR.calcSquare() > max_p2 )
        {
          max_p2 = pR.calcSquare();
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
