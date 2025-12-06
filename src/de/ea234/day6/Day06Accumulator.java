package de.ea234.day6;

import java.math.BigDecimal;
import java.util.List;

public class Day06Accumulator
{

  private static final int  FLAG_NO_NUMBER_FOUND = 0;
  private static final int  FLAG_NUMBER_FOUND = 1;
  
  private static final int        MOD_UNDEFINED = 0;

  private static final int        MOD_ADD       = 1;

  private static final int        MOD_MUL       = 3;

  private int                     m_index_nr    = 0;

  private int                     m_index_von   = 0;

  private int                     m_index_bis   = 0;

  private int                     m_modus       = MOD_UNDEFINED;

  private String                  m_aufgabe     = "";

  private BigDecimal              m_accumulator = new BigDecimal( "0" );

  public Day06Accumulator( int pIndexNr, String pModus, int pIndexVon, int pIndexBis )
  {
    m_index_nr = pIndexNr;

    m_index_von = pIndexVon;
    m_index_bis = pIndexBis;

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

        m_accumulator = new BigDecimal( "1" );
      }
    }
  }

  public void calcInputList( List< String > pList )
  {
    for ( int col_nr = m_index_bis; col_nr >= m_index_von; col_nr-- )
    {
      String current_nr_input = "";

      int flag_found_number = FLAG_NO_NUMBER_FOUND;

      int input_index_current_line = 0;

      while ( input_index_current_line < pList.size() - 1 )
      {
        String current_input_line = pList.get( input_index_current_line );

        char current_char = ' ';

        if ( current_input_line.length() >= col_nr )
        {
          current_char = current_input_line.charAt( col_nr );
        }

        if ( ( current_char >= '0' ) && ( current_char <= '9' ) )
        {
          current_nr_input += current_char;

          flag_found_number = FLAG_NUMBER_FOUND;
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

      if ( flag_found_number == FLAG_NUMBER_FOUND )
      {
        addInput( current_nr_input );
      }
    }
  }

  public String toString()
  {
    return "Aufgabe nr " + m_index_nr + " from " + m_index_von + " to " + m_index_bis + " (" + ( ( m_index_bis - m_index_von ) + 1 ) + ") Modus " + m_modus + " " + m_accumulator.toPlainString() + " Aufgabe " + m_aufgabe;
  }

  public void addInput( String pInputString )
  {
    BigDecimal bd_input = getBigDecimal( pInputString, null );

    if ( bd_input != null )
    {
      m_aufgabe += "," + pInputString;

      if ( m_modus == MOD_ADD )
      {
        m_accumulator = m_accumulator.add( bd_input );
      }
      else if ( m_modus == MOD_MUL )
      {
        m_accumulator = m_accumulator.multiply( bd_input );
      }
    }
    else
    {
      m_aufgabe += ",#### Error #### " + pInputString + " ####";
    }
  }

  public BigDecimal getAccumulatorValue()
  {
    return m_accumulator;
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
