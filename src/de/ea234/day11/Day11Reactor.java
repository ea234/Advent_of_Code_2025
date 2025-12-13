package de.ea234.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import de.ea234.util.FkString;

public class Day11Reactor
{
  /*
  * --- Day 11: Reactor ---
  */

  public static void main( String[] args )
  {
    String test_content = "aaa: you hhh,you: bbb ccc,bbb: ddd eee,ccc: ddd eee fff,ddd: ggg,eee: out,fff: out,ggg: out,hhh: ccc fff iii,iii: out";

    // test_content = "aaa: you    ,   you: bbb ccc      ,    bbb: out";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).collect( Collectors.toList() );

    // calcPart1( test_content_list, true );

    calcPart1( getListProd(), false );

    String tcon2 = "svr: aaa bbb,aaa: fft,fft: ccc,bbb: tty,tty: ccc,ccc: ddd eee,ddd: hub,hub: fff,eee: dac,dac: fff,fff: ggg hhh,ggg: out,hhh: out";

    List< String > t2 = Arrays.stream( tcon2.split( "," ) ).collect( Collectors.toList() );

    calcPart2( t2, false );

    // Just Dont - calcPart2( getListProd(), false );
  }

  private static List< Day11Device > dev_list = new ArrayList< Day11Device >();

  private static Day11Device getDevice( String pDeviceID )
  {
    return dev_list.stream().filter( d -> pDeviceID.equals( d.getDeviceID() ) ).findFirst().orElse( null );
  }

  private static Day11Device getDeviceByID( String pDeviceIDStart )
  {
    return dev_list.stream().filter( d -> d.isDeviceID( pDeviceIDStart ) ).findFirst().orElse( null );
  }

  private static Day11Device setupDevices( List< String > pList, boolean pKnzDebug )
  {
    /*
     * Create a new list of devices.
     */
    dev_list = new ArrayList< Day11Device >();

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "Setting up Devices" );
      wl( "" );
    }

    int nr = 1;

    /*
     * For every line from the input, create a new device
     */
    for ( String input_str : pList )
    {
      dev_list.add( new Day11Device( nr++, input_str.trim().replaceAll( " {2,}", " " ) ) );
    }

    /*
     * Add a device for the end-point "out".
     */
    Day11Device device_out = new Day11Device( nr++, "out:    " );

    dev_list.add( device_out );

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "Setting Successors" );
      wl( "" );
    }

    for ( Day11Device device_from : dev_list )
    {
      wl( device_from.toString() );

      String connects_to = device_from.getStrNachfolger();

      //if ( ( connects_to != null ) && ( connects_to.isBlank() == false ) )
      {
        /*
         * Replace two space characters into one space character
         */
        String[] array_successor_ids = connects_to.replaceAll( " {2,}", " " ).split( " " );

        /*
         * Search every successor-id in the list of devices,
         * and add those device-instance to the list of successors
         * to the current from-device. 
         */
        for ( String successor_id : array_successor_ids )
        {
          Day11Device successor_device = getDevice( successor_id );

          if ( successor_device != null )
          {
            //successor_device.addFromDevice( device_from );

            device_from.addToDevice( successor_device );

            wl( "suc " + successor_id + " gefunden " + successor_device.toString(), pKnzDebug );
          }
          else
          {
            wl( "suc " + successor_id + " nicht gefunden ", pKnzDebug );
          }
        }
      }
    }

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "Debug List" );
      wl( "" );
      wl( "###########################" );

      for ( Day11Device device_inst : dev_list )
      {
        wl( device_inst.toString() );
      }
    }

    return device_out;
  }

  public static void calcPart1( List< String > pList, boolean pKnzDebug )
  {
    Day11Device device_out = setupDevices( pList, pKnzDebug );

    Day11Device device_start = getDeviceByID( "you" );

    if ( device_start == null )
    {
      wl( "Start Device not found" );

      return;
    }

    wl( "Start Device found " + device_start.toString(), pKnzDebug );

    device_start.getPath( 0, "START" );

    wl( "####################################################################################################" );
    wl( "" );

    wl( device_out.getPathsToDeviceFilter( "you" ) );

    wl( "" );
    wl( "####################################################################################################" );
    wl( "" );
  }

  public static void calcPart2( List< String > pList, boolean pKnzDebug )
  {
    Day11Device device_out = setupDevices( pList, pKnzDebug );

    Day11Device device_start = getDeviceByID( "svr" );

    if ( device_start == null )
    {
      wl( "Start Device not found" );
    }
    else
    {
      /*
       * 
       * Version 1: Von Device-ID-Start nach Device-ID-End
       *            Um die Laufzeit zu minimieren sollten eventuell nur die Teil-Listen ermittelt werden
       * 
       * Stack 
       * 
       *  1 AAA CCC
       *  0 AAA BBB
       *  
       *  0. Hole das erste Element vom Stack runter
       *  
       *  0.1 der Stackpointer vermindert sich um 1. 
       *      Es wurde ein Element vom Stack geholt.
       *      
       *      Für das Element kommen eventuell neue Nachfolger hinzu. 
       *      
       *      ... oder 
       *      
       *      Das Element ist ein Endknoten.
       *      
       *  1. Von einem Device hole vom Stack-Element den letzten Nachfolger)
       *  2. Hole das Device mit der in 1. ermittelten ID aus der Liste der Devices
       *  
       *  3. Hat das Device aus 2 Nachfolger
       *     Nein -> End-Element -> weiter bei 5
       *     Ja   -> weiter bei 4
       *  
       *  4. Stackliste erweitern:
       *  4.1 Für jeden Nachfolger des Devices aus 2, ergänze die bisherige Liste aus 0.
       *      um den Nachfolger-String je Nachfolger ID.
       *      
       *      Jede Nachfolger-ID bekommt ein Element auf dem Stack zugewiesen. 
       *      
       *  4.2. Pushe die neue Liste aus 4.1 auf den Stack
       *       Erhoehe den Stackpointer
       *  
       *  
       *  5. Listenende gefunden (Stack minimieren)
       *  
       *  5.1 Es wurde ein Pfad zum letzten Element gefunden.
       *  
       *  5.2 Prüfe nun, ob das Element den Vogaben entspricht
       *      Ja   -> füge die gefundene Liste der Ergebnisliste hinzu
       *      Nein -> Mache mit dem nächsten Element des Stacks weiter 
       *      
       * 6. Die Routine ist beendet, wenn der Stackpointer negativ ist (-1) (.... oder 0 ist)
       *    Der Stackpointer wurde im Schritt 0 vermindert.
       *  
                    
Nr 55642981 Stack Size 88  Paths found 20341384
Nr 55642982 End Device fount 
Nr 55642982 Stack Size 87  Paths found 20341385
Nr 55642983 Stack Size 87  Paths found 20341385
                    
      
       */

      String device_id_start = "svr";
      String device_id_end = "fft";

      List< String > list_paths_to_end = new ArrayList< String >();

      Stack< String > device_stack = new Stack< String >();

      device_start = getDeviceByID( device_id_start );

      device_stack.push( device_start.getDeviceID() );

      long nr = 0;
      while ( ( device_stack.isEmpty() == false ) && ( nr < Long.MAX_VALUE ) )
      {
        String stack_path = device_stack.pop();

        wl( "", pKnzDebug );

        wl( "Nr " + FkString.getFeldLinksMin( nr, 6 ) + " Stack Path " + stack_path, pKnzDebug );

        String[] path_ids = stack_path.split( " " );

        Day11Device curr_stack_device = getDeviceByID( path_ids[ path_ids.length - 1 ] );

        if ( curr_stack_device != null )
        {
          wl( "Nr " + FkString.getFeldLinksMin( nr, 6 ) + " Stack Device " + curr_stack_device.toString(), pKnzDebug );

          if ( curr_stack_device.getConnectsToCount() == 0 )
          {
            wl( "Nr " + FkString.getFeldLinksMin( nr, 6 ) + " End Device fount " );

//            if ( stack_path.contains( " fft " ) && stack_path.contains( " dac " ) )
            {
              list_paths_to_end.add( stack_path + " " + curr_stack_device.getDeviceID() );
            }
          }
          else if ( curr_stack_device.isDeviceID( device_id_end ) )
          {
            wl( "Nr " + FkString.getFeldLinksMin( nr, 6 ) + " End Device " + device_id_end + " fount ", pKnzDebug );

            //list_paths_to_end.add( stack_path + " " + curr_stack_device.getDeviceID() );
          }
          else
          {
            for ( Day11Device successor_device : curr_stack_device.getListConnctsTo() )
            {
              device_stack.push( stack_path + " " + successor_device.getDeviceID() );

              wl( "Nr " + FkString.getFeldLinksMin( nr, 6 ) + " Push  " + stack_path + " " + successor_device.getDeviceID(), pKnzDebug );
            }
          }
        }

        wl( "Nr " + FkString.getFeldLinksMin( nr, 6 ) + " Stack Size " + device_stack.size() + "  Paths found " + list_paths_to_end.size() );

        nr++;
      }

      //( nr < Long.MAX_VALUE )
      wl( "" );
      wl( "####################################################################################################" );
      wl( "" );
      wl( "Paths" );

      long path_nr = 0;

      for ( String curr_path : list_paths_to_end )
      {
        path_nr++;

        wl( "Nr " + FkString.getFeldLinksMin( path_nr, 6 ) + " Path " + curr_path );
      }

      wl( "" );
      wl( "####################################################################################################" );
      wl( "" );
      wl( "" );
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

  private static void wl( String pString, boolean pKnzDebug )
  {
    if ( pKnzDebug ) System.out.println( pString );
  }
}
