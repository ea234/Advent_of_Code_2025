package de.ea234.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.ea234.util.FkString;

public class Day1SecretEntrance
{
  /*
   * 
   * 0x434C49434B
   * 
   *    43 C
   *    4C L
   *    49 I
   *    43 C
   *    4B K
   * 
   * ------------------------------------------------------------------------
   * 
   *  Nr. 0 L68    L From   50 To   82    -18  Zero-Flag true    1  1
   *  Nr. 1 L30    L From   82 To   52     52  Zero-Flag false   0  1
   *  Nr. 2 R48    R From   52 To    0    100  Zero-Flag false   0  1
   *  Nr. 3 L5     L From    0 To   95     -5  Zero-Flag false   0  1
   *  Nr. 4 R60    R From   95 To   55    155  Zero-Flag true    1  2
   *  Nr. 5 L55    L From   55 To    0      0  Zero-Flag false   0  2
   *  Nr. 6 L1     L From    0 To   99     -1  Zero-Flag false   0  2
   *  Nr. 7 L99    L From   99 To    0      0  Zero-Flag false   0  2
   *  Nr. 8 R14    R From    0 To   14     14  Zero-Flag false   0  2
   *  Nr. 9 L82    L From   14 To   32    -68  Zero-Flag true    1  3
   * 
   * sum_dial_pointer_is_zero           3
   * sum_zero_positions_during_rotation 3
   * sum zeros                          6
   * 
   * 
   * 
   *  Nr. 0 L68    L From   50 To   82    -18  Zero-Flag true    1  1
   *  Nr. 1 L130   L From   82 To   52     52  Zero-Flag true    1  2 - Modification + 100
   *  Nr. 2 R48    R From   52 To    0    100  Zero-Flag false   0  2
   *  Nr. 3 L5     L From    0 To   95     -5  Zero-Flag false   0  2
   *  Nr. 4 R60    R From   95 To   55    155  Zero-Flag true    1  3
   *  Nr. 5 L55    L From   55 To    0      0  Zero-Flag false   0  3
   *  Nr. 6 L1     L From    0 To   99     -1  Zero-Flag false   0  3
   *  Nr. 7 L99    L From   99 To    0      0  Zero-Flag false   0  3
   *  Nr. 8 R14    R From    0 To   14     14  Zero-Flag false   0  3
   *  Nr. 9 L82    L From   14 To   32    -68  Zero-Flag true    1  4
   * 
   * sum_dial_pointer_is_zero           3
   * sum_zero_positions_during_rotation 4
   * sum zeros                          7
   * 
   * Challenges:
   * - In the input list there are sequences that exceded the Dial-Max limit. 
   *   This was not done in the example.
   *   
   * - What counts as a rotation over 0. 
   *   It must be ensured that going from 0 to any number always causes an overflow. 
   *   This overflow should not be considered in the second part.
   * 
   */

  public static void main( String[] args )
  {
    calcPassword( getListProd(), 50 );

    List< String > rotation_list_test = new ArrayList<>( Arrays.asList( "L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82" ) );

    calcPassword( rotation_list_test, 50 );

    List< String > rotation_list_test2 = new ArrayList<>( Arrays.asList( "L68", "L130", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82" ) );

    calcPassword( rotation_list_test2, 50 );
  }

  private static void calcPassword( List< String > pListRotationSequenzes, int pDialPointerStart )
  {
    int dial_pointer_start = pDialPointerStart;

    int zero_position_count = 1;

    int dial_pointer_max = 99;
    int dial_pointer_min = 0;

    /*
     * Zero counts as dial-position
     */
    int dial_pointer_positions = dial_pointer_max + zero_position_count;

    /*
     * String-Values from the rotation-sequenze-list 
     */
    String rotation_direction     = "+";
    String rotation_length_string =  "";

    /*
     * The length of the rotation from the list
     */
    int rotation_length_int_from_list = 0;

    /*
     * The lenght of the rotation for 1 rotation.
     * Excludes the full rotations.
     */
    int rotation_length_int_corrected = 0;

    /*
     * Dial-Pointer calculated. 
     * Can be negative or above the max-dial-positions
     */
    int dial_pointer_current_math = dial_pointer_start;

    /*
     * Dial-Pointer corrected, to stay in the boundaries of the dial
     */
    int dial_pointer_current_corr = dial_pointer_start;

    /*
     * Saves the old dial-pointer from the previous rotation
     */
    int dial_pointer_old = dial_pointer_start;

    int count_zero_positions_during_rotation = 0;

    /*
     * Summs up all rotation, where the dial-pointer points at zero
     */
    int sum_dial_pointer_is_zero = 0;

    int sum_zero_positions_during_rotation = 0;

    boolean sum_zero_positions_during_rotation_knz = false;

    int rotation_list_index = 0;
    
    for ( String rotation_code : pListRotationSequenzes )
    {
      /*
       * Get rotation direction from string
       */
      rotation_direction = left( rotation_code, 1 );

      /*
       * Get rotation length as string
       */
      rotation_length_string = getStringAb( rotation_code, 1 );

      /*
       * Convert rotation length to integer
       */
      rotation_length_int_from_list = parseInt( rotation_length_string, 0 );

      /*
       * Reset the counter for zero positions during rotation
       */
      count_zero_positions_during_rotation = 0;

      sum_zero_positions_during_rotation_knz = false;

      /*
       * Check if the rotation length exceeds the max dial-pointer positions 
       */
      if ( rotation_length_int_from_list > dial_pointer_positions )
      {
        /*
         * Calculate the remainder of rotations
         */
        rotation_length_int_corrected = rotation_length_int_from_list % dial_pointer_positions;

        /*
         * Calculate the full rotations
         */
        count_zero_positions_during_rotation = ( rotation_length_int_from_list - rotation_length_int_corrected ) / dial_pointer_positions;

        sum_zero_positions_during_rotation_knz = true;
      }
      else
      {
        /*
         * If the rotation length is less than the max dial-pointer positions, than 
         * the rotation_length is the length from the string.
         */
        rotation_length_int_corrected = rotation_length_int_from_list;
      }

      /*
       * Compute the new dial-pointer-position
       * 
       * R = addition    = result can be greater then dial_pointer_max 
       * L = subtraction = result can be negative
       * 
       * Mathmatical: The result can be above or below the boundaries.
       *   
       */
      if ( rotation_direction.equalsIgnoreCase( "R" ) )
      {
        dial_pointer_current_math += rotation_length_int_corrected;
      }
      else if ( rotation_direction.equalsIgnoreCase( "L" ) )
      {
        dial_pointer_current_math -= rotation_length_int_corrected;
      }

      /*
       * Calculate the new dial_pointer_position
       */

      if ( dial_pointer_current_math > dial_pointer_max )
      {
        /*
         * Value greater than the max-value
         * From the math-value we must subtract the number of dial-pointer-positions (Zero is a position)
         */
        dial_pointer_current_corr = dial_pointer_current_math - dial_pointer_positions;
      }
      else if ( dial_pointer_current_math < dial_pointer_min )
      {
        /*
         * Value less then 0.
         * 
         */
        dial_pointer_current_corr = dial_pointer_current_math + dial_pointer_positions;
      }
      else
      {
        /*
         * Value in between the boundaries.
         */
        dial_pointer_current_corr = dial_pointer_current_math;
      }

      /*
       * Check if the dial-pointer points to zero.
       * If it is, then increment the zero-counter.
       */
      if ( dial_pointer_current_corr == 0 )
      {
        sum_dial_pointer_is_zero++;
      }
      else
      {
        /*
         * Prevent counts
         * If the old pointer startet at the zero-position, then a left
         * or right rotation will always result in an undershoot oder overshoot to the boundaries.
         * That doesn't count. 
         */
        if ( ( dial_pointer_old != 0 ) && ( dial_pointer_current_math != 0 ) )
        {
          if ( dial_pointer_current_math > dial_pointer_max )
          {
            sum_zero_positions_during_rotation_knz = true;

            count_zero_positions_during_rotation++;
          }

          if ( dial_pointer_current_math < 0 )
          {
            sum_zero_positions_during_rotation_knz = true;

            count_zero_positions_during_rotation++;
          }
        }
      }

      sum_zero_positions_during_rotation += count_zero_positions_during_rotation;

      wl( " Nr. " + rotation_list_index + " " + FkString.getFeldLinksMin( rotation_code, 6 ) + " " + rotation_direction + " From " + FkString.getFeldRechtsMin( dial_pointer_old, 4 ) + " To " + FkString.getFeldRechtsMin( dial_pointer_current_corr, 4 ) + " " + FkString.getFeldRechtsMin( dial_pointer_current_math, 6 ) + "  Zero-Flag " + FkString.getFeldLinksMin( sum_zero_positions_during_rotation_knz, 6 ) + "  " + count_zero_positions_during_rotation + "  " + sum_zero_positions_during_rotation );

      /*
       * Set the current dial-pointer-math to the correct dial-pointer position
       */
      dial_pointer_current_math = dial_pointer_current_corr;

      dial_pointer_old = dial_pointer_current_corr;
      
      rotation_list_index++;
    }

    wl( "" );
    wl( "sum_dial_pointer_is_zero           " + sum_dial_pointer_is_zero );
    wl( "sum_zero_positions_during_rotation " + sum_zero_positions_during_rotation );
    wl( "sum zeros                          " + ( sum_dial_pointer_is_zero + sum_zero_positions_during_rotation ) );
    wl( "" );
    wl( "" );
    wl( "" );
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day1_input.txt";

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
   * <pre>
   * Ermittelt aus dem Parameter "pString" den Integerwert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert der im Fehlerfall zurueckzugebende Wert
   * @return der Wert als int oder der Vorgabewert
   */
  private static int parseInt( String pString, int pVorgabeWert )
  {
    try
    {
      if ( pString != null )
      {
        return Integer.parseInt( pString.trim() );
      }
    }
    catch ( NumberFormatException err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall der Vorgabewert zurueckgegeben wird
    }

    return pVorgabeWert;
  }

  /**
   * <pre>
   * Schneidet Anzahl-Stellen von dem uebergebenen String ab und gibt diesen zurueck.
   * 
   * Ist der Parameter "pString" gleich \"null\", wird ein Leerstring zurueckgegeben.
   * 
   * Uebersteigt die Anazhl der abzuschneidenden Stellen die Stringlaenge, wird der
   * Quellstring insgesamt zurueckgegeben.
   * 
   * Ist die Anzahl der abzuschneidenden Stellen negativ oder 0, wird ein Leerstring zurueckgegeben.
   * 
   * FkString.left( "ABC.DEF.GHI.JKL",  3 ) = "ABC"
   * FkString.left( "ABC.DEF.GHI.JKL",  4 ) = "ABC."
   * FkString.left( "ABC.DEF.GHI.JKL", 20 ) = "ABC.DEF.GHI.JKL"
   * 
   * FkString.left( "ABC.DEF.GHI.JKL", -3 ) = "" = negative Anzahl von Stellen = Leerstring
   * FkString.left(                "", 10 ) = "" = pString ist Leerstring      = Leerstring
   * FkString.left(              null, 10 ) = "" = pString ist null            = Leerstring
   * </pre>
   * 
   * @param pString der Quellstring
   * @param pAnzahlStellen die Anzahl der von links abzuschneidenden Stellen
   * @return den sich ergebenden String, Leerstring wenn die Anzahl der Stellen negativ ist oder pString null ist
   */
  private static String left( String pString, int pAnzahlStellen )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist der Parameter "pString" gleich "null" gibt es keinen String. 
     * Der Aufrufder bekommt einen Leerstring zurueck
     */
    if ( pString == null )
    {
      return "";
    }

    /*
     * Pruefung: Anzahl der Stellen negativ?
     * Ist die Anzahl der abzuschneidenden Stellen negativ, bleibt 
     * kein Teil von pString uebrig. Dieser Fall wird analog einer 
     * Uebergabe von 0 Zeichen abschneiden behandelt.  
     * 
     * Der Aufrufer bekommt einen Leerstring zurueck.
     */
    if ( pAnzahlStellen <= 0 )
    {
      return "";
    }

    /*
     * Pruefung: Teilstring zurueckgeben?
     * Ist die Anzahl der Stellen kleiner als die Laenge von "pString", 
     * wird ein Teilstring zurueckgegeben.
     *
     * Der Aufrufer bekommt den Teilstring ab der Position 0 bis zur
     * Anzahl der abzuschneidenden Stellen zurueck. 
     */  
    if ( pAnzahlStellen < pString.length() )
    {
      return pString.substring( 0, pAnzahlStellen );
    }

    /*
     * Ueberschreitet die Anzahl der abzuschneidenden Stellen die 
     * Laenge des Eingabestrings, muss kein Zeichen vom Eingabestring
     * abgeschnitten werden. 
     * 
     * Der Aufrufer bekommt die Eingabe zurueck.
     */
    return pString;
  }

  /**
   * <pre>
   * Fuehrt letztendlich "pString.substring( pAbPosition )" aus, behandelt aber Sonderfaelle 
   * 
   * FkString.getStringAb( "ABC.DEF.GHIJ.KLM",   7 ) = ".GHIJ.KLM"       
   * FkString.getStringAb( "ABC.DEF.GHIJ.KLM", -13 ) = "ABC.DEF.GHIJ.KLM"  = Ab-Position negativ = Ergebnis ist Eingabe
   * FkString.getStringAb( "ABC.DEF.GHIJ.KLM",  50 ) = ""                  = Ab-Position laenger als Eingabe = Leerstring
   * </pre>
   * 
   * @param pString die Zeichnfolge
   * @param pAbPosition die Stelle, ab welcher der Substring beginnen soll
   * @return der Substring ab der angegebenen Stelle
   */
  private static String getStringAb( String pString, int pAbPosition )
  {
    /*
     * Pruefung: Parameter "pString" gesetzt und kein Leerstring ?
     */
    if ( ( pString != null ) && ( pString.length() > 0 ) )
    {
      /*
       * Pruefung: "pAbPosition" kleiner Stringlaenge ?
       * 
       * Liegt die Ab-Position hinter dem Stringende, ist das Ergebnis ein Leerstring. 
       */
      if ( pAbPosition <= pString.length() )
      {
        /*
         * Pruefung: "pAbPosition" negativ ?
         * 
         * Ist die AB-Position negativ, wird die AB-Position auf das erste Zeichen gestellt.
         */
        if ( pAbPosition < 0 )
        {
          pAbPosition = 0;
        }

        /*
         * Der Aufrufer bekommt einen Teilstring ab der AB-Position bis
         * zum Stringende zurueck. 
         */
        return pString.substring( pAbPosition );
      }
    }

    /*
     * Standardrueckgabe ist ein Leerstring.
     */
    return "";
  }

  /**
   * wl = write log 
   * 
   * @param pString der auszugebende String
   */
  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
