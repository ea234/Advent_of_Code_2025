package de.ea234.day5;

import java.math.BigDecimal;

public class Day5Range
{
  private BigDecimal              m_min_value       = null;

  private BigDecimal              m_max_value       = null;

  private BigDecimal              m_bd_range_values = null;

  private BigDecimal              m_minus_one       = null;

  private BigDecimal              m_plus_one        = null;

  private static final BigDecimal BIG_DECIMAL_1     = new BigDecimal( "1" );

  public Day5Range( String pRange )
  {
    String[] range_split = pRange.trim().split( "-" );

    m_min_value = new BigDecimal( range_split[ 0 ] );
    m_max_value = new BigDecimal( range_split[ 1 ] );

    recalcRangeVal();
  }

  public boolean isValueInRange( BigDecimal pBigDecimal )
  {
    if ( pBigDecimal == null )
    {
      return false;
    }

    if ( pBigDecimal.compareTo( m_min_value ) < 0 )
    {
      return false;
    }

    if ( pBigDecimal.compareTo( m_max_value ) > 0 )
    {
      return false;
    }

    return true;
  }

  public boolean isValueNeighbour( BigDecimal pBigDecimal )
  {
    if ( pBigDecimal == null )
    {
      return false;
    }

    if ( pBigDecimal.compareTo( m_minus_one ) < 0 )
    {
      return false;
    }

    if ( pBigDecimal.compareTo( m_plus_one ) > 0 )
    {
      return false;
    }

    return true;
  }

  public void mergeRange( BigDecimal pBigDecimalMin, BigDecimal pBigDecimalMax )
  {
    m_min_value = min( m_min_value, pBigDecimalMin );

    m_max_value = max( m_max_value, pBigDecimalMax );

    recalcRangeVal();
  }

  private void recalcRangeVal()
  {
    m_minus_one = m_min_value.subtract( BIG_DECIMAL_1 );

    m_plus_one = m_max_value.add( BIG_DECIMAL_1 );

    m_bd_range_values = m_max_value.subtract( m_min_value ).add( BIG_DECIMAL_1 );
  }

  private static BigDecimal max( BigDecimal a, BigDecimal b )
  {
    return a.compareTo( b ) >= 0 ? a : b;
  }

  private static BigDecimal min( BigDecimal a, BigDecimal b )
  {
    return a.compareTo( b ) >= 0 ? b : a;
  }

  public BigDecimal getMinValue()
  {
    return m_min_value;
  }

  public BigDecimal getMaxValue()
  {
    return m_max_value;
  }

  public BigDecimal getRangeValues()
  {
    return m_bd_range_values;
  }

  public String toString()
  {
    return " " + m_min_value.toPlainString() + "  -  " + m_max_value.toPlainString() + "  Range Values " + m_bd_range_values.toPlainString();
  }
}
