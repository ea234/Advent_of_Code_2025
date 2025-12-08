package de.ea234.day8;

import de.ea234.util.FkString;

public class Day08ConnectionBox
{
  private long   m_position_x = 0;

  private long   m_position_y = 0;

  private long   m_position_z = 0;

  private String m_box_id     = "";

  public Day08ConnectionBox( long pBoxId, String pInputLine )
  { 
    m_box_id = "" + pBoxId;

    String[] range_split = pInputLine.trim().split( "," );

    m_position_x = Long.parseLong( range_split[ 0 ] );
    m_position_y = Long.parseLong( range_split[ 1 ] );
    m_position_z = Long.parseLong( range_split[ 2 ] );
  }

  public long getPositionX()
  {
    return m_position_x;
  }
  
  public long getPositionY()
  {
    return m_position_y;
  }

  public long getPositionZ()
  {
    return m_position_z;
  }

  public String getBoxId()
  {
    return m_box_id;
  }

  public String toString()
  {
    return "ID " + m_box_id + " X " + m_position_x + " " + m_position_y + " " + m_position_z;
  }

  public String toStringF()
  {
    return "ID " + FkString.getFeldRechtsMin( m_box_id, 4 ) + " - " + FkString.getFeldRechtsMin( m_position_x, 5 ) + " - " + FkString.getFeldRechtsMin( m_position_y, 5 ) + " - " + FkString.getFeldRechtsMin( m_position_z, 5 );
  }

  public long distanceNoSqrt( Day08ConnectionBox pPositionB )
  {
    /*
     * Du kannst vermeiden, sqrt zu berechnen, wenn du nur vergleichen willst: 
     * vergleiche dist^2, also: d2 = (dx)^2 + (dy)^2 + (dz)^2. 
     * 
     * Nur am Ende, falls nötig, nimm sqrt(d2).
     * 
     */

    long distance_x = m_position_x - pPositionB.getPositionX();

    long distance_y = m_position_y - pPositionB.getPositionY();

    long distance_z = m_position_z - pPositionB.getPositionZ();

    //return Math.sqrt( ( distance_x * distance_x ) + ( distance_y * distance_y ) + ( distance_z * distance_z ) );

    return ( ( distance_x * distance_x ) + ( distance_y * distance_y ) + ( distance_z * distance_z ) );
  }
}
