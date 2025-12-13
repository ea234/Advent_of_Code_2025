package de.ea234.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.ea234.util.FkString;

public class Day11Device extends Day11Reactor
{
  private String              m_device_id      = null;

  private String              m_input_line     = "";

  private List< Day11Device > m_connects_to    = new ArrayList< Day11Device >();

  private List< Day11Device > m_connected_from = new ArrayList< Day11Device >();

  private List< String >      m_paths_to_you   = new ArrayList< String >();

  private String              m_str_nachfolger = null;

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

  public void addFromDevice( Day11Device pDevice )
  {
    m_connected_from.add( pDevice );
  }

  public Day11Device getFromDevice( int pIndex )
  {
    if ( ( pIndex < 0 ) || ( pIndex > m_connected_from.size() ) )
    {
      return null;
    }

    return m_connects_to.get( pIndex );
  }

  public int getConnectsFromCount()
  {
    return m_connected_from.size();
  }

  public String getConnectsTo()
  {
    String str_connects_to = "";

    for ( Day11Device device_inst : m_connects_to )
    {
      str_connects_to += device_inst.getDeviceID() + ", ";
    }

    return str_connects_to;
  }

  public String getConnectedFrom()
  {
    String str_connected_from = "";

    for ( Day11Device device_inst : m_connected_from )
    {
      str_connected_from += device_inst.getDeviceID() + ", ";
    }

    return str_connected_from;
  }

  public String getPathsToYou()
  {
    String s_erg = "";

    for ( String cur_path : m_paths_to_you )
    {
      s_erg += cur_path + "\n";
    }

    return s_erg;
  }

  public String getPathsToYouFilter( String pMustHaveID )
  {
    String s_erg = "";

    int nr = 0;

    for ( String cur_path : m_paths_to_you )
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

  public boolean isConnectedFromDevice( String pDeviceID )
  {
    for ( Day11Device device_inst : m_connected_from )
    {
      if ( device_inst.isDeviceID( pDeviceID ) )
      {
        return true;
      }
    }

    return false;
  }

  public int getPath( int pIndexTo, String pPathToYou )
  {
    int count_nr = 1;

    m_paths_to_you.add( pPathToYou );

    if ( m_connects_to.size() > 0 )
    {
      for ( Day11Device device_inst : m_connects_to )
      {
        count_nr += device_inst.getPath( pIndexTo, pPathToYou + ", " + m_device_id );
      }
    }

    return count_nr;
  }

  public String toString()
  {
    return "Nr " + FkString.getFeldRechtsMin( m_input_line, 4 ) + "  Device " + m_device_id + " - to " + FkString.getFeldLinksMin( m_str_nachfolger, 50 ) + " - to " + FkString.getFeldLinksMin( getConnectsTo(), 50 ) + " - from " + FkString.getFeldLinksMin( getConnectedFrom(), 50 );
  }
}
