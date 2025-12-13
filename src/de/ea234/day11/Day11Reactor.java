package de.ea234.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11Reactor
{
  /*
  * --- Day 11: Reactor ---
  * https://adventofcode.com/2025/day/11
  */

  public static void main( String[] args )
  {
    String test_content = "aaa: you hhh,you: bbb ccc,bbb: ddd eee,ccc: ddd eee fff,ddd: ggg,eee: out,fff: out,ggg: out,hhh: ccc fff iii,iii: out";
    
    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).collect( Collectors.toList() );

   // calcPart1( test_content_list, true );
    
    calcPart1( getListProd(), false );
  }

  private static List< Day11Device > dev_list = new ArrayList< Day11Device >();

  private static Day11Device getDevice( String pDeviceID )
  {
    return dev_list.stream().filter( d -> pDeviceID.equals( d.getDeviceID() ) ).findFirst().orElse( null );
  }

  private static Day11Device getDeviceStart( String pDeviceIDStart )
  {
    return dev_list.stream().filter( d -> d.isDeviceID( pDeviceIDStart) ).findFirst().orElse( null );
  }

  public static void calcPart1( List< String > pList, boolean pKnzDebug )
  {
    dev_list = new ArrayList< Day11Device >();
    
    wl( "" );
    wl( "Setting up Devices" );
    wl( "" );

    int nr = 1;
    
    for ( String input_str : pList )
    {
      dev_list.add( new Day11Device( nr++, input_str.trim().replaceAll( " {2,}", " " ) ) );
    }

    Day11Device device_out = new Day11Device( nr++, "out:    " );
    
    dev_list.add( device_out );
    
    wl( "" );
    wl( "Setting Successors" );
    wl( "" );

    for ( Day11Device device_from : dev_list )
    {
      wl( device_from.toString() );

      String connects_to = device_from.getStrNachfolger();

      //if ( ( connects_to != null ) && ( connects_to.isBlank() == false ) )
      {
        String[] array_successor_ids = connects_to.replaceAll( " {2,}", " " ).split( " " );

        for ( String successor_id : array_successor_ids )
        {
          Day11Device successor_device = getDevice( successor_id );

          if ( successor_device != null )
          {
            successor_device.addFromDevice( device_from );
            
            device_from.addToDevice( successor_device );
            
            wl( "suc " + successor_id + " gefunden " + successor_device.toString() );
          }
          else
          {
            wl( "suc " + successor_id + " nicht gefunden " );
          }
        }
      }
    }
 
    
    wl( "" );
    wl( "Debug Output" );
    wl( "" );
    
    for ( Day11Device device_inst : dev_list )
    {
      wl( device_inst.toString() );
    }

    Day11Device d_start = getDeviceStart( "you" );
    
    if ( d_start == null ) 
    {
      wl( "Start Device not found" );
    }
    else
    {
      wl( "Start Device found " + d_start.toString()  );

      wl( "Start Device found " + d_start.getPath(0, "START" )  );
      
      wl( device_out.getPathsToYouFilter( "you" ) );
    }
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day11_input.txt";

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
