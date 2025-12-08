package de.ea234.day08;

import de.ea234.util.FkString;

public class Day08ConnectionBox
{
  private long               m_position_x                 = 0;

  private long               m_position_y                 = 0;

  private long               m_position_z                 = 0;

  private long               m_distance_to_zero           = Long.MAX_VALUE;

  private long               m_distance_to_next_neighbour = Long.MAX_VALUE;

  private String             m_neighbour_box_id           = null;

  private Day08ConnectionBox m_neighbour_inst             = null;

  private String             m_box_id                     = "";

  public Day08ConnectionBox( long pBoxId, String pInputLine )
  {
    m_box_id = "" + pBoxId;

    String[] range_split = pInputLine.trim().split( "," );

    m_position_x = Long.parseLong( range_split[ 0 ] );
    m_position_y = Long.parseLong( range_split[ 1 ] );
    m_position_z = Long.parseLong( range_split[ 2 ] );
  }

  public void calcDistanceToZero( Day08ConnectionBox pConBox )
  {
    m_distance_to_zero = distanceNoSqrt( pConBox );
  }

  public boolean isID( String pID )
  {
    return this.m_box_id.equalsIgnoreCase( pID );
  }

  public boolean calcDistanceNearest( Day08ConnectionBox pConBox )
  {
    long distance_current = 0;

    if ( pConBox.isID( this.m_box_id ) )
    {
      /*
       * you cant pair with yourself
       */
      System.out.println( "##### Error ####" );

      return false;
    }

    if ( this.m_distance_to_zero < pConBox.getDistancToZero() )
    {
      distance_current = pConBox.getDistancToZero() - this.m_distance_to_zero;
    }
    else
    {
      distance_current = this.m_distance_to_zero - pConBox.getDistancToZero();
    }

    if ( distance_current < m_distance_to_next_neighbour )
    {
      /*
       * Found an improvement
       */

      m_distance_to_next_neighbour = distance_current;

      m_neighbour_box_id = pConBox.getBoxId();

      m_neighbour_inst = pConBox;

      /*
       * Could be a mistake
       */

      pConBox.setNeighbourInst( this );

      return true;
    }

    return false;
  }

  public Day08ConnectionBox getNeighbour()
  {
    return m_neighbour_inst;
  }

  public void setNeighbourInst( Day08ConnectionBox pConBox )
  {
    if ( pConBox.isID( this.m_box_id ) == false )
    {
      m_neighbour_inst = pConBox;
    }
  }

  public String getNeighbourXYZ()
  {
    if ( m_neighbour_inst != null )
    {
      return m_neighbour_inst.getXYZ();
    }

    return " NULL ";
  }

  public String getInfoNeighbour()
  {
    return " this.id " + this.m_box_id + " to " + m_neighbour_box_id + " Distance " + m_distance_to_next_neighbour;
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

  public long getDistancToZero()
  {
    return m_distance_to_zero;
  }

  public String getXYZ()
  {
    return " " +  FkString.getFeldRechtsMin( m_position_x, 5 ) + " " + FkString.getFeldRechtsMin( m_position_y, 5 ) + " " + FkString.getFeldRechtsMin( m_position_z, 5 );
  }

  public String toString()
  {
    return "ID " + m_box_id + " X " + m_position_x + " " + m_position_y + " " + m_position_z;
  }

  public String toStringF()
  {
    return "ID " + FkString.getFeldRechtsMin( m_box_id, 4 ) + " - " + FkString.getFeldRechtsMin( m_position_x, 5 ) + " - " + FkString.getFeldRechtsMin( m_position_y, 5 ) + " - " + FkString.getFeldRechtsMin( m_position_z, 5 ) + " - " + FkString.getFeldRechtsMin( m_distance_to_zero, 5 );
  }

  public String toStringNeighbour()
  {
    return "ID " + FkString.getFeldRechtsMin( m_box_id, 4 ) + " - " + getXYZ() + " and " + getNeighbourXYZ() + "  Distance " + FkString.getFeldRechtsMin( m_distance_to_next_neighbour, 10 );
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
