package de.ea234.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.ea234.util.FkString;

public class Day11Device extends Day11Reactor
{
  private String              m_device_id       = null;

  private String              m_input_line      = "";

  private List< Day11Device > m_connects_to     = new ArrayList< Day11Device >();

  //private List< Day11Device > m_connected_from = new ArrayList< Day11Device >();

  private List< String >      m_paths_to_device = new ArrayList< String >();

  private String              m_str_nachfolger  = null;

  public Day11Device( int pNr, String pInputStr )
  {
    int split_index = pInputStr.indexOf( ':' );

    m_device_id = pInputStr.substring( 0, split_index );

    m_input_line = "" + pNr;

    m_str_nachfolger = pInputStr.substring( split_index + 1 ).trim();
  }

  public String getStrNachfolger()
  {
    return m_str_nachfolger;
  }

  public void addToDevice( Day11Device pDevice )
  {
    m_connects_to.add( pDevice );
  }

  public Day11Device getToDevice( int pIndex )
  {
    if ( ( pIndex < 0 ) || ( pIndex > m_connects_to.size() ) )
    {
      return null;
    }

    return m_connects_to.get( pIndex );
  }

  public int getConnectsToCount()
  {
    return m_connects_to.size();
  }
  
  public List< Day11Device > getListConnctsTo()
  {
    return m_connects_to;
  }


//  public void addFromDevice( Day11Device pDevice )
//  {
//    m_connected_from.add( pDevice );
//  }
//
//  public Day11Device getFromDevice( int pIndex )
//  {
//    if ( ( pIndex < 0 ) || ( pIndex > m_connected_from.size() ) )
//    {
//      return null;
//    }
//
//    return m_connects_to.get( pIndex );
//  }
//
//  public int getConnectsFromCount()
//  {
//    return m_connected_from.size();
//  }
//
//  public String getConnectedFrom()
//  {
//    String str_connected_from = "";
//
  
//    for ( Day11Device device_inst : m_connected_from )
//    {
//      str_connected_from += device_inst.getDeviceID() + ", ";
//    }
//
//    return str_connected_from;
//  }
//
//  public boolean isConnectedFromDevice( String pDeviceID )
//  {
//    for ( Day11Device device_inst : m_connected_from )
//    {
//      if ( device_inst.isDeviceID( pDeviceID ) )
//      {
//        return true;
//      }
//    }
//
//    return false;
//  }
//

  public String getConnectsTo()
  {
    String str_connects_to = "";

    for ( Day11Device device_inst : m_connects_to )
    {
      str_connects_to += device_inst.getDeviceID() + ", ";
    }

    return str_connects_to;
  }

  public String getPathsToYou()
  
  {
    String s_erg = "";

    int nr = 0;

    for ( String cur_path : m_paths_to_device )
    {
      nr++;

      s_erg += "  " + FkString.getFeldRechtsMin( nr, 5 ) + " " + cur_path + "\n";
    }

    return s_erg;
  }

  public String getPathsToDeviceFilter( String pMustHaveID )
  {
    String s_erg = "";

    int nr = 0;

    for ( String cur_path : m_paths_to_device )
    {
      if ( cur_path.contains( pMustHaveID ) )
      {
        nr++;

        s_erg += "  " + FkString.getFeldRechtsMin( nr, 5 ) + " " + cur_path + "\n";
      }
    }

    return s_erg;
  }

  public String getDeviceID()
  {
    return m_device_id;
  }

  public boolean isDeviceID( String pDeviceID )
  {
    return m_device_id.equals( pDeviceID );
  }

  public boolean connectsToDevice( String pDeviceID )
  {
    for ( Day11Device device_inst : m_connects_to )
    {
      if ( device_inst.isDeviceID( pDeviceID ) )
      {
        return true;
      }
    }

    return false;
  }

  public int getPath( int pIndexTo, String pPathToDevice )
  {
    int count_nr = 1;

    m_paths_to_device.add( pPathToDevice );

    if ( m_connects_to.size() > 0 )
    {
      for ( Day11Device device_inst : m_connects_to )
      {
        count_nr += device_inst.getPath( pIndexTo, pPathToDevice + ", " + m_device_id );
      }
    }

    return count_nr;
  }

  public int getPath2( int pIndexTo, String pPathToDevice, String pDeviceIDEnd, String pDeviceIDAvoid )
  {
    int count_nr = 1;

    m_paths_to_device.add( pPathToDevice );

    if ( isDeviceID( pDeviceIDAvoid ) )
    {
      return 1;
    }

    if ( isDeviceID( pDeviceIDEnd ) )
    {
      return 1;
    }

    if ( m_connects_to.size() > 0 )
    {
      for ( Day11Device device_inst : m_connects_to )
      {
        count_nr += device_inst.getPath2( pIndexTo, pPathToDevice + ", " + m_device_id, pDeviceIDEnd, pDeviceIDAvoid );
      }
    }

    return count_nr;
  }

  public int getPath3( int pIndexTo, String pPathToDevice, String pDeviceIDAvoid )
  {
    if ( isDeviceID( pDeviceIDAvoid ) )
    {
      return 0;
    }

    int count_nr = 1;

    m_paths_to_device.add( pPathToDevice );

    if ( m_connects_to.size() > 0 )
    {
      for ( Day11Device device_inst : m_connects_to )
      {
        count_nr = device_inst.getPath3( pIndexTo, pPathToDevice + ", " + m_device_id, pDeviceIDAvoid );
      }
    }

    return count_nr;
  }

  public String toString()
  {
    return "Nr " + FkString.getFeldRechtsMin( m_input_line, 4 ) + "  Device " + m_device_id + " - to " + FkString.getFeldLinksMin( getConnectsTo(), 50 );
  }
}
