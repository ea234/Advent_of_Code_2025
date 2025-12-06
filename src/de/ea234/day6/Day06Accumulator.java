package de.ea234.day6;

import java.math.BigDecimal;
import java.util.List;

public class Day06Accumulator
{
  private static final int FLAG_NO_NUMBER_FOUND = 0;

  private static final int FLAG_NUMBER_FOUND    = 1;

  private static final int MOD_UNDEFINED        = 0;

  private static final int MOD_ADD              = 1;

  private static final int MOD_MUL              = 3;

  private int              m_modus              = MOD_UNDEFINED;

  private int              m_index_nr           = 0;                    // for part 2

  private int              m_index_from         = 0;                    // for part 2

  private int              m_index_to           = 0;

  private String           m_aufgabe            = "";

  private BigDecimal       m_accumulator_value  = new BigDecimal( "0" );

  public Day06Accumulator( int pIndexNr, String pModus, int pIndexVon, int pIndexBis )
  {
    m_index_nr = pIndexNr;

    m_index_from = pIndexVon;

    m_index_to = pIndexBis;

    if ( pModus != null )
    {
      if ( pModus.contains( "+" ) )
      {
        m_modus = MOD_ADD;

        m_aufgabe = "+";
      }
      else if ( pModus.contains( "*" ) )
      {
        m_modus = MOD_MUL;

        m_aufgabe = "*";

        /*
         * The start-value must be 1, because if it is 0, you would get 0 as endresult.
         */
        m_accumulator_value = new BigDecimal( "1" );
      }
    }
  }

  public void calcInputList( List< String > pList )
  {
    for ( int column_index = m_index_to; column_index >= m_index_from; column_index-- )
    {
      String current_nr_input = "";

      int flag_parser_status = FLAG_NO_NUMBER_FOUND;

      int input_index_current_line = 0;

      while ( input_index_current_line < pList.size() - 1 )
      {
        String current_input_line = pList.get( input_index_current_line );

        char current_char = ' ';

        if ( current_input_line.length() >= column_index )
        {
          current_char = current_input_line.charAt( column_index );
        }

        if ( ( current_char >= '0' ) && ( current_char <= '9' ) )
        {
          current_nr_input += current_char;

          flag_parser_status = FLAG_NUMBER_FOUND;
        }
        else if ( current_char == ' ' )
        {
          // Space characters are ignored
        }
        else
        {
          System.out.println( "#### Error \"" + current_char + "\" " );
        }

        input_index_current_line++;
      }

      if ( flag_parser_status == FLAG_NUMBER_FOUND )
      {
        addInput( current_nr_input );
      }
    }
  }

  public void addInput( String pInputString )
  {
    BigDecimal bd_input = getBigDecimal( pInputString, null );

    if ( bd_input != null )
    {
      m_aufgabe += "," + pInputString;

      if ( m_modus == MOD_ADD )
      {
        m_accumulator_value = m_accumulator_value.add( bd_input );
      }
      else if ( m_modus == MOD_MUL )
      {
        m_accumulator_value = m_accumulator_value.multiply( bd_input );
      }
    }
    else
    {
      m_aufgabe += ",#### Error #### " + pInputString + " ####";
    }
  }

  public BigDecimal getAccumulatorValue()
  {
    return m_accumulator_value;
  }

  public String toString()
  {
    return "Task nr " + m_index_nr + " from " + m_index_from + " to " + m_index_to + " (" + ( ( m_index_to - m_index_from ) + 1 ) + ") Modus " + m_modus + " " + m_accumulator_value.toPlainString() + " = " + m_aufgabe;
  }

  /**
   * @param pText die Zeichenfolge, welche eine Zahl sein soll
   * @param pVorgabe eine Instanz von BigDecimal welche im Fehlerfall zurueckgegeben wird
   * @return eine Instanz von BigDecimal mit dem Wert aus pText oder pVorgabe-Instanz
   */
  public static BigDecimal getBigDecimal( String pText, BigDecimal pVorgabe )
  {
    try
    {
      if ( pText != null )
      {
        return new BigDecimal( pText );
      }
    }
    catch ( Exception err_inst )
    {
      // keine Aktion da Rueckgabe von der Vorgabe
    }

    return pVorgabe;
  }
}
