package de.ea234.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Funktionen fuer Zahlenwerte
 * 
 * <pre>
 * 
 * char     =  16-Bit-Unicode-Zeichen (0x0000 ... 0xFFFF)
 * 
 * byte     =  -2^7  bis 2^7  - 1   = -128 ... 127
 * short    =  -2^15 bis 2^15 - 1   = -32.768 ... 32.767
 * int      =  -2^31 bis 2^31 - 1   = -2.147.483.648 ... 2.147.483.647
 * long     =  -2^63 bis 2^63 - 1   = -9.223.372.036.854.775.808 ... 9.223.372.036.854.775.807
 * 
 * float    =  1,40239846E-45f           bis  3,40282347E+38f
 * double   =  4,94065645841246544E-324  bis  1,79769131486231570E+308
 *
 * x = 1 - x; =  0101010101
 * 
 * </pre>
 */
public class FkZahl
{
  public static final Long    LONG_NULL      = null;

  public static final Integer INTEGER_NULL   = null;

  public static final Boolean BOOLEAN_NULL   = null;

  public static final Double  DOUBLE_NULL    = null;

  public static final Float   FLOAT_NULL     = null;

  public static final int     INT_ZAHL_0     = 0;

  public static final long    LONG_ZAHL_0    = 0;

  public static final double  DOUBLE_ZAHL_0  = 0.0;

  public static final String  STR_10_NULLEN  = "0000000000";

  public static final String  STR_50_NULLEN  = "00000000000000000000000000000000000000000000000000";

  public static final String  STR_100_NULLEN = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

  private static double       zufall_hp_zahl = 0.0;

  public static double getZufallszahlHp()
  {
    if ( zufall_hp_zahl == 0.0 )
    {
      zufall_hp_zahl = Double.parseDouble( "0." + Calendar.getInstance().getTimeInMillis() );
    }

    zufall_hp_zahl += 3.141592653589793; // = Math.PI;

    zufall_hp_zahl = Math.pow( zufall_hp_zahl, 8 );

    zufall_hp_zahl -= Math.floor( zufall_hp_zahl );

    return zufall_hp_zahl;
  }

  public static int getRandom( int pInteger )
  {
    double zufz_hp = getZufallszahlHp();

    return (int) ( pInteger * zufz_hp );
  }

  public static String formatIntWert( int pZahl )
  {
    DecimalFormat format_instanz = new DecimalFormat( "#,###,###,###,##0" );

    return format_instanz.format( pZahl );
  }

  private static int LAUFENDE_ZAHL = 1;

  /**
   * Erhoeht die laufende Zahl und gibt diese zurueck.
   * @return eine fortlaufende Zahl
   */
  public static int getLaufendeZahl()
  {
    /*
     * Die laufende Zahl wird um 1 erhoeht
     */
    LAUFENDE_ZAHL++;

    /*
     * Pruefung: laufende Zahl gleich Integer.MAX_VALUE ?
     * 
     * Erreicht die laufende Zahl den Wert Integer.MAX_VALUE, wird die 
     * laufende Zahl auf 0 gesetzt.
     */
    if ( LAUFENDE_ZAHL == Integer.MAX_VALUE )
    {
      LAUFENDE_ZAHL = 0;
    }

    /*
     * Am ende wird die Zahl dem Aufrufer zurueckgegeben.
     */
    return LAUFENDE_ZAHL;
  }

  /**
   * <pre>
   * Setzt den Wert fuer die laufende Zahl
   * Ist pWert negativ, wird die laufende Zahl auf 0 gesetzt.
   * </pre>
   * 
   * @param pWert den Wert fuer die laufende Zahl
   */
  public static void setLaufendeZahl( int pWert )
  {
    LAUFENDE_ZAHL = pWert;

    if ( LAUFENDE_ZAHL < 0 )
    {
      LAUFENDE_ZAHL = 0;
    }
  }

  /**
   * <pre>
   * Liefert die Anzahl der Stellen einer Zahl zurueck. 
   * 
   * 
   * FkZahl.getAnzahlStellen( -1  ) = 1
   * FkZahl.getAnzahlStellen( -10  ) = 1
   * FkZahl.getAnzahlStellen( -100  ) = 2
   * FkZahl.getAnzahlStellen( -1000  ) = 3
   * FkZahl.getAnzahlStellen( -10000  ) = 4
   * FkZahl.getAnzahlStellen( -100000  ) = 5
   * FkZahl.getAnzahlStellen( -1000000  ) = 6
   * FkZahl.getAnzahlStellen( -10000000  ) = 7
   * FkZahl.getAnzahlStellen( -100000000  ) = 8
   * FkZahl.getAnzahlStellen( -1000000000  ) = 9
   * 
   * </pre>
   * 
   * @param pZahl die Zahl
   * @return die Anzahl der Stellen der Zahl
   */
  public static int getAnzahlStellen( int pZahl )
  {
    if ( pZahl < 0 )
    {
      pZahl = pZahl * -1;
    }

    if ( pZahl < 10 )
    {
      return 1;
    }

    if ( pZahl < 100 )
    {
      return 2;
    }

    if ( pZahl < 1000 )
    {
      return 3;
    }

    if ( pZahl < 10000 )
    {
      return 4;
    }

    if ( pZahl < 100000 )
    {
      return 5;
    }

    if ( pZahl < 1000000 )
    {
      return 6;
    }

    if ( pZahl < 10000000 )
    {
      return 7;
    }

    if ( pZahl < 100000000 )
    {
      return 8;
    }

    if ( pZahl < 1000000000 )
    {
      return 9;
    }

    return 10;
  }

  public static double getDouble3nk( double pZahl )
  {
    return ( (int) ( ( (int) ( pZahl * 1000 ) ) + 0.5 ) ) * 0.001;
  }

  public static double getDouble3nkOhneRundung( double pZahl )
  {
    return ( (int) ( (int) ( pZahl * 1000 ) ) ) * 0.001;
  }

  public static double getDouble2nk( double pZahl )
  {
    int int_1 = (int) ( pZahl * 100 + 0.5 );

    return int_1 * 0.01;
  }

  public static double getDouble2nkOhneRundung( double pZahl )
  {
    return ( (int) ( (int) ( pZahl * 100 ) ) ) * 0.01;
  }

  public static int getZahl( int pZahl, int pUntergrenze, int pObergrenze )
  {
    return ( pZahl < pUntergrenze ? pUntergrenze : ( pZahl > pObergrenze ? pObergrenze : pZahl ) );
  }

  public static double getZahl( double pZahl, double pUntergrenze, double pObergrenze )
  {
    return ( pZahl < pUntergrenze ? pUntergrenze : ( pZahl > pObergrenze ? pObergrenze : pZahl ) );
  }

  public static long getZahl( long pZahl, long pUntergrenze, long pObergrenze )
  {
    return ( pZahl < pUntergrenze ? pUntergrenze : ( pZahl > pObergrenze ? pObergrenze : pZahl ) );
  }

  /**
   * <pre>
   * Liefert die Information zurueck, ob die uebergebene Zahl einen vollen Tausenderbetrag ergibt.
   * 
   * istVolleTausend(  "100.00" ) = false
   * istVolleTausend( "1000.00" ) = true
   * istVolleTausend( "1010.00" ) = false
   * istVolleTausend( "1000.10" ) = false
   * </pre>
   * 
   * @param pZahl die zu pruefende Zahl
   * @return TRUE wenn es sich um einen vollen Tausenderbetrag handelt sonst false
   */
  public static boolean istVolleTausend( BigDecimal pZahl )
  {
    BigDecimal bd_null = new BigDecimal( 0 );

    BigDecimal bss1NeuVT = new BigDecimal( pZahl.movePointLeft( 3 ).intValue() ).movePointRight( 3 );

    return pZahl.subtract( bss1NeuVT ).compareTo( bd_null ) == 0;
  }

  /**
   * <pre>
   * Rundet die Zahl auf den naechsten vollen tausender Betrag auf.
   * 
   * FkZahl.rundeAufNaechstenVollenTausender(  1000.00 ) =  1000.0
   * FkZahl.rundeAufNaechstenVollenTausender(  1000.01 ) =  2000.0
   * 
   * FkZahl.rundeAufNaechstenVollenTausender(     0.00 ) =     0.0
   * FkZahl.rundeAufNaechstenVollenTausender(     0.01 ) =  1000.0
   * FkZahl.rundeAufNaechstenVollenTausender(   999.99 ) =  1000.0
   * FkZahl.rundeAufNaechstenVollenTausender(  1999.99 ) =  2000.0
   * FkZahl.rundeAufNaechstenVollenTausender( -1999.99 ) = -1000.0
   * FkZahl.rundeAufNaechstenVollenTausender(  -999.99 ) =     0.0
   * FkZahl.rundeAufNaechstenVollenTausender(    -0.01 ) =     0.0
   * </pre>
   * 
   * @param pZahl die zu rundende Zahl
   * @return den naechsten vollen tausender Betrag
   */
  public static double rundeAufNaechstenVollenTausender( double pZahl )
  {
    return (double) ( ( (int) ( ( pZahl + 999.99 ) * 0.001 ) ) * 1000.0 );
  }

  /**
   * <pre>
   * Ermittelt die vollen Tausender von der Parameterzahl. 
   * 
   * FkZahl.getVolleTausender(  123456.78 ) = 123.0
   * FkZahl.getVolleTausender( -123456.78 ) = 123.0
   * FkZahl.getVolleTausender(     123.45 ) =   0.0
   * </pre>
   * 
   * @param pZahl die Zahl
   * @return die vollen Tausender von der Zahl
   */
  public static double getVolleTausender( double pZahl )
  {
    if ( pZahl < 0 )
    {
      return (double) ( (int) ( pZahl * -0.001 ) );
    }
    else
    {
      return (double) ( (int) ( pZahl * 0.001 ) );
    }
  }

  public static boolean istVolleTausend( String pZahl )
  {
    return istVolleTausend( new BigDecimal( pZahl ) );
  }

  /**
   * <pre>
   * Liefert die Information zurueck, ob in "pString" eine negative Zahl befindet.
   * 
   * 
   * Ist "pString" eine Zahl groesser gleich 0, wird false zurueckgegeben.
   * Ist "pString" null wird false zurueckgegeben.
   * Ist "pString" keine zahl wird false zurueckgegeben.
   * 
   * FkZahl.istNegativ( "1"         ) = false
   * FkZahl.istNegativ( "0"         ) = false
   * FkZahl.istNegativ( "-1"        ) = true
   * 
   * FkZahl.istNegativ( "    1   "  ) = false
   * FkZahl.istNegativ( "   -1   "  ) = true
   * 
   * FkZahl.istNegativ( "  0.001 "  ) = false
   * FkZahl.istNegativ( " -0.001 "  ) = true
   * 
   * FkZahl.istNegativ( null        ) = false
   * FkZahl.istNegativ( "ABC.DEF"   ) = false
   *
   * ----------------------------------------------------------------------
   * 
   * Rueckgabe ist:
   * 
   * return Double.parseDouble( pString ) < 0.0;
   * 
   * Ist in "pString" eine formatierte Zahl mit Tausenderpunkten enthalten,
   * wird diese Funktion die Zahl nicht erkennen. Es wird FALSE zurueckgegeben.
   * 
   * FkZahl.istNegativ( "-1.000,00" ) = false
   * </pre>
   *  
   * @param pString der zu ueberpruefende String
   * @return TRUE wenn der String eine Zahl und negativ ist, sonst false 
   */
  public static boolean istNegativ( String pString )
  {
    try
    {
      if ( pString != null )
      {
        return Double.parseDouble( pString ) < 0.0;
      }
    }
    catch ( Exception err_inst )
    {
      //
    }

    // return Double.parseDouble( FkZahl.getZahl( pString.replaceAll( EURO_SIGN_CHAR, "" ) , 2 ) ) < 0.0;

    return false;
  }

  /**
   * <pre> 
   * Errechnet den Mittelwert der beiden Angaben. 
   * Die Reihenfolge der Zahlenangabe ist egal, wird intern korrigiert.
   * 
   * FkZahl.getMittelwert(  10,  20 ) =  15
   * FkZahl.getMittelwert(  20,  10 ) =  15
   * FkZahl.getMittelwert(  15,  15 ) =  15
   * FkZahl.getMittelwert( -20,  20 ) =   0
   * FkZahl.getMittelwert( -20,  -7 ) = -14
   * </pre>
   *  
   * @param pZahl1 die erste Zahl
   * @param pZahl2 die zweite Zahl
   * @return den Mittelwert
   */
  public static int getMittelwert( int pZahl1, int pZahl2 )
  {
    if ( pZahl1 < pZahl2 )
    {
      return pZahl1 + ( (int) ( ( pZahl2 - pZahl1 ) * 0.5 ) );
    }
    else if ( pZahl1 > pZahl2 )
    {
      return pZahl2 + ( (int) ( ( pZahl1 - pZahl2 ) * 0.5 ) );
    }
    else
    {
      return pZahl1; // Untergrenze = Obergrenze      
    }
  }

  /**
   * <pre>
   * Errechnet den Mittelwert der beiden Angaben. 
   * Die Reihenfolge der Zahlenangabe ist egal, wird intern korrigiert.
   * 
   * FkZahl.getMittelwert(  10.0,   20.0 ) =  15.0
   * FkZahl.getMittelwert(  20.0,   10.0 ) =  15.0
   * FkZahl.getMittelwert(  15.0,   15.0 ) =  15.0
   * FkZahl.getMittelwert( -20.0,   20.0 ) =   0.0
   * FkZahl.getMittelwert( -20.0,   -7.0 ) = -13.5
   * FkZahl.getMittelwert( 123.45, 678.9 ) = 401.17499999999995
   * </pre> 
   * 
   * @param pZahl1 die erste Zahl
   * @param pZahl2 die zweite Zahl
   * @return den Mittelwert 
   */
  public static double getMittelwert( double pZahl1, double pZahl2 )
  {
    if ( pZahl1 < pZahl2 )
    {
      return pZahl1 + ( ( pZahl2 - pZahl1 ) * 0.5 );
    }
    else if ( pZahl1 > pZahl2 )
    {
      return pZahl2 + ( ( pZahl1 - pZahl2 ) * 0.5 );
    }
    else
    {
      return pZahl1; // Untergrenze = Obergrenze      
    }
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit negativen Vorzeichen
   */
  public static int getNegativ( int pZahl )
  {
    /*
     * Pruefung: Ist der Wert aus "pZahl" positiv ?
     */
    if ( pZahl >= 0 )
    {
      return -1 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit negativen Vorzeichen
   */
  public static long getNegativ( long pZahl )
  {
    if ( pZahl >= 0 )
    {
      return -1 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit negativen Vorzeichen
   */
  public static double getNegativ( double pZahl )
  {
    if ( pZahl >= 0.0 )
    {
      return -1.0 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit negativen Vorzeichen
   */
  public static float getNegativ( float pZahl )
  {
    if ( pZahl >= 0.0f )
    {
      return -1.0f * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit positiven Vorzeichen
   */
  public static int getPositiv( int pZahl )
  {
    if ( pZahl <= 0 )
    {
      return -1 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit positiven Vorzeichen
   */
  public static long getPositiv( long pZahl )
  {
    if ( pZahl <= 0 )
    {
      return -1 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit positiven Vorzeichen
   */
  public static double getPositiv( double pZahl )
  {
    if ( pZahl <= 0.0 )
    {
      return -1.0 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit positiven Vorzeichen
   */
  public static float getPositiv( float pZahl )
  {
    if ( pZahl <= 0.0f )
    {
      return -1.0f * pZahl;
    }

    return pZahl;
  }

  public static double getDurchschnitt( long pWert, long pAnzahl )
  {
    if ( pAnzahl == 0 )
    {
      return 0.0;
    }

    return pWert / pAnzahl;
  }

  public static double getDurchschnitt( double pWert, long pAnzahl )
  {
    if ( pAnzahl == 0 )
    {
      return 0.0;
    }

    return pWert / ( (double) pAnzahl );
  }

  public static double getDurchschnitt( long pWert, double pAnzahl )
  {
    if ( pAnzahl == 0 )
    {
      return 0.0;
    }

    return ( (double) pWert ) / pAnzahl;
  }

  /**
   * <pre>
   * Gibt nur die Zahlen aus dem String zurueck. 
   * Das Komma dient als Trennzeichen fuer die Nachkommastellen.
   * 
   * FkZahl.getZahlString( "123.456.789,12" ) =  123456789.12
   * FkZahl.getZahlString( "123.45"         ) =  12345
   * FkZahl.getZahlString( ""               ) =  ""
   * FkZahl.getZahlString( null             ) =  null
   * </pre>
   * 
   * @param pString die Eingabezeichenfolge
   * @return einen String der nur aus Zahlen und gegebenenfalls einem Punkt fuer die Nachkommastellen bestitzt
   */
  public static String getZahlString( String pString )
  {
    if ( pString != null )
    {
      pString = pString.trim();

      while ( pString.indexOf( "." ) != -1 )
      {
        int punkt_position = pString.indexOf( "." );

        pString = pString.substring( 0, punkt_position ) + pString.substring( punkt_position + 1 );
      }

      pString = pString.replace( ',', '.' );
    }

    return pString;
  }

  public static int getInteger( double pZahl )
  {
    return (int) pZahl;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den Integerwert.
   * 
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als int oder der Vorgabewert
   */
  public static int getInteger( String pString, int pVorgabeWert )
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
      // Keine Fehlermeldung, da im Fehlerfall der Vorgabewert zurueckgegeben wird
    }

    return pVorgabeWert;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den double-Wert.
   * 
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird 0.0 zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @return der Wert als double oder der Vorgabewert
   */
  public static double getDouble( String pString )
  {
    double fkt_ergebnis = 0.0;

    try
    {
      if ( pString != null )
      {
        fkt_ergebnis = Double.parseDouble( pString.trim() );
      }
    }
    catch ( Exception err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall Rueckgabe von 0.0
    }

    return fkt_ergebnis;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den double-Wert.
   * 
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als double oder der Vorgabewert
   */
  public static double getDouble( String pString, double pVorgabe )
  {
    try
    {
      if ( pString != null )
      {
        return Double.parseDouble( pString.trim() );
      }
    }
    catch ( Exception err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall der Vorgabewert zurueckgegeben wird
    }

    return pVorgabe;
  }

  public static long getLong( String pString )
  {
    long fkt_ergebnis = 0;

    try
    {
      if ( pString != null )
      {
        fkt_ergebnis = Long.parseLong( pString );
      }
    }
    catch ( Exception err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall Rueckgabe von 0
    }

    return fkt_ergebnis;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den Longwert.
   * 
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als long oder der Vorgabewert
   */
  public static long getLong( String pString, int pVorgabeWert )
  {
    try
    {
      if ( pString != null )
      {
        return Long.parseLong( pString.trim() );
      }
    }
    catch ( NumberFormatException err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall der Vorgabewert zurueckgegeben wird.
    }

    return pVorgabeWert;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den Longwert.
   * 
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als long oder der Vorgabewert
   */
  public static long getLong( String pString, long pVorgabeWert )
  {
    try
    {
      if ( pString != null )
      {
        return Long.parseLong( pString.trim() );
      }
    }
    catch ( NumberFormatException err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall der Vorgabewert zurueckgegeben wird
    }

    return pVorgabeWert;
  }

  public static int getInteger( String pString )
  {
    int ergebnis = 0;

    try
    {
      if ( pString != null )
      {
        ergebnis = Integer.parseInt( pString );
      }
    }
    catch ( Exception err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall Rueckgabe von 0
    }

    return ergebnis;
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
  public static int parseInt( String pString, int pVorgabeWert )
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
   * Ermittelt aus dem Parameter "pString" den Doublewert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert der im Fehlerfall zurueckzugebende Wert
   * @return der Wert als double oder der Vorgabewert
   */
  public static double parseDouble( String pString, double pVorgabeWert )
  {
    try
    {
      return Double.parseDouble( getZahl( pString, 2, true ) );
    }
    catch ( NumberFormatException err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall der Vorgabewert zurueckgegeben wird
    }

    return pVorgabeWert;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den long-Wert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert der im Fehlerfall zurueckzugebende Wert
   * @return der Wert als long oder der Vorgabewert
   */
  public static long parseLong( String pString, long pVorgabeWert )
  {
    try
    {
      if ( pString != null )
      {
        return Long.parseLong( pString.trim() );
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
   * Ermittelt aus dem Parameter "pString" den absoluten IntegerWert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * 
   * Der Vorgabewert kann negativ sein (bzw. auf "pVorgabewert" wird kein Math.abs() gemacht) 
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert der im Fehlerfall zurueckzugebende Wert (kann negativ sein)
   * @return der absolute Wert aus "pString", oder den Vorgabewert.
   */
  public static int parsePositivInt( String pString, int pVorgabeWert )
  {
    try
    {
      if ( pString != null )
      {
        return Math.abs( Integer.parseInt( pString.trim() ) );
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
   * Ermittelt aus dem Parameter "pString" den absoluten IntegerWert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * 
   * Der Vorgabewert kann negativ sein (bzw. auf "pVorgabewert" wird kein Math.abs() gemacht) .
   * Der Vorgabewert wird gegen die Min/Max-Werte geprueft.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert der im Fehlerfall zurueckzugebende Wert (kann negativ sein)
   * @param pMinWert der nicht zu unterschreitende Wert aus pString 
   * @param pMaxWert der nicht zu ueberschreitende Wert aus pString
   * @return der absolute Wert aus "pString", oder den Vorgabewert.
   */
  public static int parsePositivInt( String pString, int pVorgabeWert, int pMinWert, int pMaxWert )
  {
    int int_wert = pVorgabeWert;

    try
    {
      if ( pString != null )
      {
        int_wert = Math.abs( Integer.parseInt( pString.trim() ) );
      }
    }
    catch ( NumberFormatException err_inst )
    {
      int_wert = pVorgabeWert;
    }

    if ( ( pMinWert > 0 ) && ( int_wert < pMinWert ) )
    {
      int_wert = pMinWert;
    }

    if ( ( pMaxWert > 0 ) && ( int_wert > pMaxWert ) )
    {
      int_wert = pMaxWert;
    }

    return pVorgabeWert;
  }

  /**
   * Pruefung, ob die Obergrenze ueberschritten wird.
   * 
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Obergrenze wenn die uebergebene Zahl ueber dieser Grenze liegt
   */
  public static int checkObergrenze( int pObergrenze, int pZahl )
  {
    if ( pZahl > pObergrenze )
    {
      return pObergrenze;
    }

    return pZahl;
  }

  /**
   * Pruefung, ob die Obergrenze ueberschritten wird.
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Untergrenze wenn die uebergebene Zahl unterhalb dieser Grenze liegt
   */
  public static int checkUntergrenze( int pUntergrenze, int pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return pUntergrenze;
    }

    return pZahl;
  }

  /**
   * Pruefung, ob die Obergrenze ueberschritten wird.
   * 
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Obergrenze wenn die uebergebene Zahl ueber dieser Grenze liegt
   */
  public static long checkObergrenze( long pObergrenze, long pZahl )
  {
    if ( pZahl > pObergrenze )
    {
      return pObergrenze;
    }

    return pZahl;
  }

  /**
   * Pruefung, ob die Obergrenze ueberschritten wird.
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Untergrenze wenn die uebergebene Zahl unterhalb dieser Grenze liegt
   */
  public static long checkUntergrenze( long pUntergrenze, long pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return pUntergrenze;
    }

    return pZahl;
  }

  /**
   * Pruefung, ob die Obergrenze ueberschritten wird.
   * 
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Obergrenze wenn die uebergebene Zahl ueber dieser Grenze liegt
   */
  public static double checkObergrenze( double pObergrenze, double pZahl )
  {
    if ( pZahl > pObergrenze )
    {
      return pObergrenze;
    }

    return pZahl;
  }

  /**
   * Pruefung, ob die Obergrenze ueberschritten wird.
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Untergrenze wenn die uebergebene Zahl unterhalb dieser Grenze liegt
   */
  public static double checkUntergrenze( double pUntergrenze, double pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return pUntergrenze;
    }

    return pZahl;
  }

  /**
   * <pre>
   * Prueft den Parameter "pZahl" auf Einhaltung der uebergebenen Grenzen.
   * 
   * Ist "pZahl" kleiner als "pUntergrenze", wird "pUntergrenze" zurueckgegeben.
   * Ist "pZahl" groesser als "pObergrenze", wird "pObergrenze" zurueckgegeben.
   * </pre>
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Zahl wenn diese innerhalb der Grenzen liegt, sonst die jeweilige Grenze
   */
  public static int checkBereich( int pUntergrenze, int pObergrenze, int pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return pUntergrenze;
    }

    if ( pZahl > pObergrenze )
    {
      return pObergrenze;
    }

    return pZahl;
  }

  /**
   * <pre>
   * Prueft den Parameter "pZahl" auf Einhaltung der uebergebenen Grenzen.
   * 
   * Ist "pZahl" kleiner als "pUntergrenze", wird "pUntergrenze" zurueckgegeben.
   * Ist "pZahl" groesser als "pObergrenze", wird "pObergrenze" zurueckgegeben.
   * </pre>
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Zahl wenn diese innerhalb der Grenzen liegt, sonst die jeweilige Grenze
   */
  public static double checkBereich( double pUntergrenze, double pObergrenze, double pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return pUntergrenze;
    }

    if ( pZahl > pObergrenze )
    {
      return pObergrenze;
    }

    return pZahl;
  }

  /**
   * <pre>
   * Prueft den Parameter "pZahl" auf Einhaltung der uebergebenen Grenzen.
   * 
   * Ist "pZahl" kleiner als "pUntergrenze", wird "pUntergrenze" zurueckgegeben.
   * Ist "pZahl" groesser als "pObergrenze", wird "pObergrenze" zurueckgegeben.
   * </pre>
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @return die Zahl wenn diese innerhalb der Grenzen liegt, sonst die jeweilige Grenze
   */
  public static double checkBereich( long pUntergrenze, long pObergrenze, long pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return pUntergrenze;
    }

    if ( pZahl > pObergrenze )
    {
      return pObergrenze;
    }

    return pZahl;
  }

  /**
   * <pre>
   * Prueft den Parameter "pZahl" auf Einhaltung der uebergebenen Grenzen.
   * 
   * Ist "pZahl" kleiner als "pUntergrenze", wird "pFehlerRueckgabe" zurueckgegeben.
   * Ist "pZahl" groesser als "pObergrenze", wird "pFehlerRueckgabe" zurueckgegeben.
   * </pre>
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @param pFehlerRueckgabe die Rueckgabe bei ueber- oder Unterschreitung der Grenzen
   * @return die Zahl wenn diese innerhalb der Grenzen liegt, sonst die angegebene Zahl 
   */
  public static int checkBereich( int pUntergrenze, int pObergrenze, int pZahl, int pFehlerRueckgabe )
  {
    if ( pZahl < pUntergrenze )
    {
      return pFehlerRueckgabe;
    }

    if ( pZahl > pObergrenze )
    {
      return pFehlerRueckgabe;
    }

    return pZahl;
  }

  /**
   * <pre>
   * Prueft den Parameter "pZahl" auf Einhaltung der uebergebenen Grenzen.
   * 
   * Ist "pZahl" kleiner als "pUntergrenze", wird "pFehlerRueckgabe" zurueckgegeben.
   * Ist "pZahl" groesser als "pObergrenze", wird "pFehlerRueckgabe" zurueckgegeben.
   * </pre>
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @param pFehlerRueckgabe die Rueckgabe bei ueber- oder Unterschreitung der Grenzen
   * @return die Zahl wenn diese innerhalb der Grenzen liegt, sonst die angegebene Zahl 
   */
  public static long checkBereich( long pUntergrenze, long pObergrenze, long pZahl, long pFehlerRueckgabe )
  {
    if ( pZahl < pUntergrenze )
    {
      return pFehlerRueckgabe;
    }

    if ( pZahl > pObergrenze )
    {
      return pFehlerRueckgabe;
    }

    return pZahl;
  }

  /**
   * <pre>
   * Prueft den Parameter "pZahl" auf Einhaltung der uebergebenen Grenzen.
   * 
   * Ist "pZahl" kleiner als "pUntergrenze", wird "pFehlerRueckgabe" zurueckgegeben.
   * Ist "pZahl" groesser als "pObergrenze", wird "pFehlerRueckgabe" zurueckgegeben.
   * </pre>
   * 
   * @param pUntergrenze die nicht zu unterschreitende Untergrenze
   * @param pObergrenze die nicht zu ueberschreitende Obergrenze
   * @param pZahl die zu pruefende Zahl
   * @param pFehlerRueckgabe die Rueckgabe bei ueber- oder Unterschreitung der Grenzen
   * @return die Zahl wenn diese innerhalb der Grenzen liegt, sonst die angegebene Zahl 
   */
  public static double checkBereich( double pUntergrenze, double pObergrenze, double pZahl, double pFehlerRueckgabe )
  {
    if ( pZahl < pUntergrenze )
    {
      return pFehlerRueckgabe;
    }

    if ( pZahl > pObergrenze )
    {
      return pFehlerRueckgabe;
    }

    return pZahl;
  }

  /**
   * @param pString die zu pruefende Eingabezeichenfolge
   * @param pTrennzeichenNK das Zeichen fuer die Nachkommatrennstelle (darf xmal vorkommen aber nicht nach N)
   * @param pZeichenTausenderPunkt das Zeichen fuer den Tausenderpunkt (darf 1x vorkommen)
   * @return TRUE wenn der Wert ein Double ist, sonst false
   */
  public static boolean checkDouble( String pString, char pTrennzeichenNK, char pZeichenTausenderPunkt )
  {
    char aktuelles_zeichen;
    char zeichen_minus = '-';
    char zeichen_plus = '+';
    char zeichen_trennz_nk = pTrennzeichenNK;
    char zeichen_tausender_pkt = pZeichenTausenderPunkt;

    int knz_nk_aktiv = 0;
    int knz_vk_aktiv = 1;
    int knz_tp_aktiv = 0;
    int akt_index = 0;
    int zaehler_nk = 0;
    int zaehler_vk = 0;
    int zaehler_tausender_p = -1;

    if ( pString == null )
    {
      return false; // keine Zahl keine Ausgabe
    }

    String x_string = pString.trim();

    if ( x_string.length() == 0 )
    {
      return false; // Leerstring ist keine Zahl
    }

    boolean pKnzFallbackTrennzeichenEin = true;

    if ( ( pKnzFallbackTrennzeichenEin ) && ( pString.indexOf( "," ) == -1 ) )
    {
      zeichen_trennz_nk = '.';
    }

    while ( akt_index < x_string.length() )
    {
      aktuelles_zeichen = x_string.charAt( akt_index );

      if ( aktuelles_zeichen >= '0' && aktuelles_zeichen <= '9' )
      {
        zaehler_nk = zaehler_nk + knz_nk_aktiv;

        zaehler_vk = zaehler_vk + knz_vk_aktiv;

        zaehler_tausender_p += knz_tp_aktiv;
      }

      /*
       * Trennzeichen fuer Nachkommastellen gefunden
       * 
       * Da dieses Zeichen nur einmal auftreten darf wird das Zeichen 
       * fuer eine weitere Erkennung ausgenullt (Zuweisung von 1)
       * 
       * Da nach dem Trennzeichen kein Tausenderpunkt mehr kommen darf,
       * wird auch der Tausenderpunkt fuer eine Erkennung ausgenullt.
       * 
       * Sonderfall " ,12 "
       * In diesem Fall ist keine Vorkommastelle als Zahl vorhanden. 
       * Damit dieses aber als TRUE zurueckgegeben werden kann muss 
       * der Zaehler fuer die VK auf 1 gestellt werden. Siehe RETURN
       * am Funktionsende.
       */
      else if ( aktuelles_zeichen == zeichen_trennz_nk )
      {
        knz_nk_aktiv = 1;

        knz_vk_aktiv = 0;

        zaehler_vk = 1;

        zeichen_trennz_nk = '1';

        zeichen_tausender_pkt = '1';
      }
      /*
       * Plus- und Minus-Zeichen duerfen nur einmal vorkommen.
       * Nachdem ein Zeichen erkannt wurden, werden die internen Variablen 
       * fuer die Erkennung durch setzen von 1 ausgenullt.
       * 
       * Das Plus- oder Minus-Zeichen darf nur am Ende vorkommen, oder 
       * solange noch keine Zahlen gelesen worden sind. Alles andere 
       * ist ein Fehler. 
       */
      else if ( ( aktuelles_zeichen == zeichen_minus ) || ( aktuelles_zeichen == zeichen_plus ) )
      {
        if ( ( zaehler_vk == 0 ) || ( ( akt_index + 1 ) == x_string.length() ) )
        {
          zeichen_minus = '1';
          zeichen_plus = '1';
        }
        else
        {
          return false; // Plus-Minuszeichen an falscher Stelle in Zahl --> Fehler
        }
      }
      /*
       * Leerzeichen sind nur dann in Ordnung, wenn noch keine Vorkommazahlen 
       * gelesen worden sind.
       */
      else if ( ( aktuelles_zeichen == ' ' ) && ( zaehler_vk == 0 ) )
      {
        // zeichen durchlassen
      }
      else if ( aktuelles_zeichen == zeichen_tausender_pkt )
      {
        if ( ( zaehler_tausender_p >= 0 ) && ( zaehler_tausender_p < 3 ) )
        {
          return false; // Tausendertrennzeichen zu dicht am letzten Tausendertrennzeichen --> falsch --> keine Zahl
        }

        zaehler_tausender_p = 0;

        knz_tp_aktiv = 1;
      }
      else
      {
        return false; // ungueltiges Zeichen in der Eingabe gefunden --> keine Zahl
      }

      akt_index++;
    }

    return zaehler_vk > 0;
  }

  public static String checkBetrag( BigDecimal pEingabe, BigDecimal pMinBetrag, BigDecimal pMaxBetrag, boolean pMussFeld )
  {
    String fkt_ergebnis = null;

    if ( pEingabe == null )
    {
      if ( pMussFeld )
      {
        fkt_ergebnis = "Es muss ein gueltiger Betrag eingegeben werden!";
      }
    }
    else
    {
      if ( ( pEingabe.compareTo( pMinBetrag ) < 0 ) || ( pEingabe.compareTo( pMaxBetrag ) > 0 ) )
      {
        fkt_ergebnis = "Die Eingabe darf nicht kleiner als " + pMinBetrag + " und nicht groeßer als " + pMaxBetrag + " sein.";
      }
    }

    return fkt_ergebnis;
  }

  /**
   * Prueft die Eingabe auf Gesetzt und Einhaltung der Grenzen
   * 
   * @param pEingabe die Eingabe 
   * @param pMinBetrag die untere Grenze
   * @param pMaxBetrag die obere Grenze
   * @param pMussFeld Kennzeichen ob es ein Mussfeld ist
   * @return Einen Leerstring wenn alles in Ordnung ist, sonst den Fehlertext
   */
  public static String checkBetrag( String pEingabe, BigDecimal pMinBetrag, BigDecimal pMaxBetrag, boolean pMussFeld )
  {
    String fkt_ergebnis = "";

    if ( ( pEingabe == null ) || ( pEingabe.trim().length() == 0 ) )
    {
      if ( pMussFeld )
      {
        fkt_ergebnis = "Es muss ein gueltiger Betrag eingegeben werden!";
      }
    }
    else
    {
      if ( checkNumber( pEingabe ) == false )
      {
        fkt_ergebnis = "Die Eingabe ergibt keine Zahl.";
      }
      else
      {
        BigDecimal bd_eingabe = new BigDecimal( pEingabe );

        if ( ( bd_eingabe.compareTo( pMinBetrag ) < 0 ) || ( bd_eingabe.compareTo( pMaxBetrag ) > 0 ) )
        {
          fkt_ergebnis = "Die Eingabe darf nicht kleiner als " + pMinBetrag + " und nicht groeßer als " + pMaxBetrag + " sein.";
        }
      }
    }

    return fkt_ergebnis;
  }

  public static String checkBetrag( String pEingabe, BigDecimal pMinBetrag, BigDecimal pMaxBetrag, boolean pMussFeld, char pTrennzeichenNK, char pZeichenTausenderPunkt )
  {
    String fkt_ergebnis = "";

    if ( ( pEingabe == null ) || ( pEingabe.trim().length() == 0 ) )
    {
      if ( pMussFeld )
      {
        fkt_ergebnis = "Es muss ein gueltiger Betrag eingegeben werden!";
      }
    }
    else
    {
      if ( checkDouble( pEingabe, pTrennzeichenNK, pZeichenTausenderPunkt ) == false )
      {
        fkt_ergebnis = "Die Eingabe ergibt keine Zahl.";
      }
      else
      {
        BigDecimal bd_eingabe = new BigDecimal( getZahlString( pEingabe ) );

        if ( ( bd_eingabe.compareTo( pMinBetrag ) < 0 ) || ( bd_eingabe.compareTo( pMaxBetrag ) > 0 ) )
        {
          fkt_ergebnis = "Die Eingabe darf nicht kleiner als " + pMinBetrag + " und nicht groeßer als " + pMaxBetrag + " sein.";
        }
      }
    }

    return fkt_ergebnis;
  }

  public static String checkBetrag( String pEingabe, BigDecimal pMinBetrag, BigDecimal pMaxBetrag, boolean pMussFeld, char pTrennzeichenNK, char pZeichenTausenderPunkt, String pTextMussfeld, String pTextKeineZahl, String pTextGrenzen )
  {
    String fkt_ergebnis = "";

    if ( ( pEingabe == null ) || ( pEingabe.trim().length() == 0 ) )
    {
      if ( pMussFeld )
      {
        fkt_ergebnis = pTextMussfeld;
      }
    }
    else
    {
      if ( checkDouble( pEingabe, pTrennzeichenNK, pZeichenTausenderPunkt ) == false )
      {
        fkt_ergebnis = pTextKeineZahl;
      }
      else
      {
        BigDecimal bd_eingabe = new BigDecimal( getZahlString( pEingabe ) );

        if ( ( bd_eingabe.compareTo( pMinBetrag ) < 0 ) || ( bd_eingabe.compareTo( pMaxBetrag ) > 0 ) )
        {
          fkt_ergebnis = pTextGrenzen;
        }
      }
    }

    return fkt_ergebnis;
  }

  /**
   * <pre>
   * Berechnet die Quersumme von der uebergebenen Zahl
   * 
   * FkZahl.getQuersumme( 123     ) =  6
   * FkZahl.getQuersumme( 1234    ) = 10
   * FkZahl.getQuersumme( 12345   ) = 15
   * FkZahl.getQuersumme( 123456  ) = 21
   * </pre>
   *  
   * @param pZahl die Zahl
   * @return die Quersumme
   */
  public static int getQuersumme( int pZahl )
  {
    int temp_zahl = pZahl;

    int quer_summe = 0;

    while ( temp_zahl != 0 )
    {
      quer_summe += temp_zahl % 10;

      temp_zahl *= 0.1; // implizites Umwandeln in Int = Eliminierung der Nachkommastellen
    }

    return quer_summe;
  }

  /**
   * <pre>
   * Berechnet die Quersumme von dem uebergebenen String.
   * In dem Eingabestring duerfen nur Zahlen enthalten sein. 
   * Ist ein anderes Zeichen enthalten, wird eine Exception geworfen.
   * Ist pString nicht gesetzt, gibt es eine NullpointerException.
   * </pre>
   * 
   * @param pZahl die Zahl
   * @return die Quersumme
   */
  public static int getQuersumme( String pString ) throws Exception
  {
    char aktuelles_zeichen;

    int akt_index = 0;

    int quer_summe = 0;

    while ( akt_index < pString.length() )
    {
      aktuelles_zeichen = pString.charAt( akt_index );

      if ( ( aktuelles_zeichen < '0' ) || ( aktuelles_zeichen > '9' ) )
      {
        throw new Exception( "getQuersumme() - Zeichen " + akt_index + " ist keine Zahl " );
      }

      quer_summe += ( ( (int) aktuelles_zeichen ) - 48 );

      akt_index++;
    }

    return quer_summe;
  }

  /**
   * <pre>
   * Berechnet die Quersumme von dem uebergebenen String.
   * In dem Eingabestring duerfen nur Zahlen enthalten sein. 
   * Ist ein anderes Zeichen enthalten, wird die uebergebene Vorgabe zurueckgegeben.
   * Ist pString nicht gesetzt, wird die uebergebene Vorgabe zurueckgegeben.
   * </pre>
   * 
   * @param pZahl die Zahl
   * @param pVorgabe die Vorgaberueckgabe, wenn in "pString" ein ungueltiges Zeichen auftritt
   * @return die Quersumme
   */
  public static int getQuersumme( String pString, int pVorgabe )
  {
    if ( pString == null )
    {
      return pVorgabe;
    }

    char aktuelles_zeichen;
    int akt_index = 0;
    int quer_summe = 0;

    while ( akt_index < pString.length() )
    {
      aktuelles_zeichen = pString.charAt( akt_index );

      if ( ( aktuelles_zeichen < '0' ) || ( aktuelles_zeichen > '9' ) )
      {
        return pVorgabe;
      }

      quer_summe += ( ( (int) aktuelles_zeichen ) - 48 );

      akt_index++;
    }

    return quer_summe;
  }

  /**
   * <pre>
   * Versucht aus dem uebergebenen String eine Instanz von BigDecimal zurueckzugeben. Sollte
   * es dabei zu einem Fehler kommen, wird der uebergebene Vorgabewert zurueckgeliefert.
   * </pre>
   * 
   * @param pString der String der zu einem BigDecimal werden soll
   * @param pVorgabeWert der Vorgabewert im Fehlerfall
   * @return eine Instanz vom Typ BigDecimal
   */
  public static BigDecimal getBigDecimal( String pString, String pVorgabeWert )
  {
    try
    {
      return new BigDecimal( pString );
    }
    catch ( Exception err_inst )
    {
    }

    return new BigDecimal( pVorgabeWert );
  }

  /**
   * <pre>
   * Aus dem uebergebenen String werden nur die Zahlen zurueckgegeben.
   * Notwendig, wenn aus einer Telefonnummer stoerende Leer- und 
   * Sonderzeichen entfernt werden muessen (z.B. eMail an Fax-Server).
   * </pre>
   * 
   * @param pString die zu bearbeitende Zeichenfolge
   * @return nur die Zahlen aus dem Eingabestring, oder null wenn die Eingabe null ist.
   */
  public static String getNurZahlen( String pString )
  {
    if ( pString == null )
    {
      return null;
    }

    if ( pString.length() == 0 )
    {
      return "";
    }

    StringBuffer str_ergebnis = new StringBuffer();

    char aktuelles_zeichen;

    int akt_index = 0;

    while ( akt_index < pString.length() )
    {
      aktuelles_zeichen = pString.charAt( akt_index );

      if ( aktuelles_zeichen >= '0' && aktuelles_zeichen <= '9' )
      {
        str_ergebnis.append( aktuelles_zeichen );
      }

      akt_index++;
    }

    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * Aus dem uebergebenen String werden nur die Zahlen und die Buchstaben zurueckgegeben.
   * Deutsche Umlaute werden nicht beruecksichtigt.
   * </pre>
   * 
   * @param pString die zu bearbeitende Zeichenfolge
   * @return der sich ergebende String
   */
  public static String getNurZahlenUndBuchstaben( String pString )
  {
    if ( pString == null )
    {
      return null;
    }

    if ( pString.length() == 0 )
    {
      return "";
    }

    StringBuffer str_ergebnis = new StringBuffer();

    char aktuelles_zeichen;

    int akt_index = 0;

    while ( akt_index < pString.length() )
    {
      aktuelles_zeichen = pString.charAt( akt_index );

      if ( aktuelles_zeichen >= '0' && aktuelles_zeichen <= '9' )
      {
        str_ergebnis.append( aktuelles_zeichen );
      }
      else if ( aktuelles_zeichen >= 'a' && aktuelles_zeichen <= 'z' )
      {
        str_ergebnis.append( aktuelles_zeichen );
      }
      else if ( aktuelles_zeichen >= 'A' && aktuelles_zeichen <= 'Z' )
      {
        str_ergebnis.append( aktuelles_zeichen );
      }

      akt_index++;
    }

    return str_ergebnis.toString();
  }

  /**
   *<pre>
   * 
   *  Eingabe 2, 1             Ausgabe "2"
   *  Eingabe 2, 2             Ausgabe "4"
   *  Eingabe 2, 3             Ausgabe "8"
   *  Eingabe 2, 4             Ausgabe "16"
   *  Eingabe 2, 5             Ausgabe "32"
   *  Eingabe 2, 6             Ausgabe "64"
   *  Eingabe 2, 7             Ausgabe "128"
   *  
   *  Eingabe -2, 4            Ausgabe "-16"
   *  
   *  Eingabe 2, 0             Ausgabe "1"
   *</pre>
   * 
   * 
   * @param pBasisZahl Basis oder Grundzahl
   * @param pExponent Exponent oder Hochzahl
   * @return der Wert der Potenz.
   */
  public static int pow( int pBasisZahl, int pExponent )
  {
    int ergebnis = 1;

    if ( Math.abs( pExponent ) > 0 )
    {
      int akt_exponent = 1;

      ergebnis = pBasisZahl;

      while ( akt_exponent < Math.abs( pExponent ) )
      {
        ergebnis = ergebnis * pBasisZahl;

        akt_exponent++;
      }
    }

    return ergebnis * ( pBasisZahl < 0 ? -1 : 1 );
  }

  public static String getZahl( String pString )
  {
    return getZahl( pString, 2, false );
  }

  /**
   * @param pString die Eingabe 
   * @param pAnzahlNachkommaStellen die gewuenschten Anzahl von Nachkommastellen
   * @return eine formatierte Zahl 
   */
  public static String getZahl( String pString, int pAnzahlNachkommaStellen )
  {
    return getZahl( pString, pAnzahlNachkommaStellen, false );
  }

  public static String getZahl( String pString, int pAnzahlNachkommaStellen, boolean pKnzFallbackTrennzeichenEin )
  {
    return getZahl( pString, ".", pAnzahlNachkommaStellen, pKnzFallbackTrennzeichenEin );
  }

  public static String getZahl( double pZahl, int pAnzahlNachkommaStellen )
  {
    return getZahl( "" + pZahl, ".", pAnzahlNachkommaStellen, true );
  }

  /**
   *<pre>
   *Beispiele:
   *
   * FkZahl.getZahl("+150.000,123456 Euro"   , 2    ) =  "150000.12"
   * FkZahl.getZahl("+150.000,123456 Euro"   , 0    ) =  "0"
   * FkZahl.getZahl("150.000,123456 DM"      , -1   ) =  "150000.123456"
   * FkZahl.getZahl("DM 150.000,123456-"     , 2    ) =  "-150000.12"
   * FkZahl.getZahl("null"                   , 2    ) =  "0.00"
   * FkZahl.getZahl("DM,Euro,Reichsmark"     , 2    ) =  "0.00"
   * FkZahl.getZahl("DM 15,0.0,00,12,34,56-" , 2    ) =  "-15.00"
   * FkZahl.getZahl("100.12"                 , 2    ) =  "100.12"
   * FkZahl.getZahl("100.1234-"              , 3    ) =  "-100.123"
   * FkZahl.getZahl("100,-"                  , 3    ) =  "-100.000"
   *</pre>
   *
   * @param pString die Eingabe 
   * @param pTrennzeichenFuerNK das Trennzeichen fuer Nachkommastellen im Ergebnis
   * @param pAnzahlNachkommaStellen die Anzahl der Nachkommastellen im Ergebnis
   * @param pKnzFallbackTrennzeichenEin Kennzeichen, ob das Trennzeichen auch ein Punkt anstelle eines Kommas sein darf
   * @return eine formatierte Zahl 
   */
  private static String getZahl( String pString, String pTrennzeichenFuerNK, int pAnzahlNachkommaStellen, boolean pKnzFallbackTrennzeichenEin )
  {
    StringBuffer str_ergebnis = new StringBuffer();

    /*
     * Kennzeichen Zahl Negativ
     * 
     * Solange in der Eingabe noch kein "-" gefunden wurde, ist diese Variable
     * ein Leerstring. Wird ein "-" gefunden, enthaelt ist diese Variable "-". 
     */
    String knz_negativ = "";

    char aktuelles_zeichen;
    char trennzeichen_nk = ',';

    if ( pTrennzeichenFuerNK == null )
    {
      pTrennzeichenFuerNK = ".";
    }
//    else if (( pTrennzeichenFuerNK != null ) && ( pTrennzeichenFuerNK.length() > 0 ))
//    {
//      trennzeichen_nk = pTrennzeichenFuerNK.charAt( 0 );
//    }

    /*
     * 
     */
    int knz_nk_aktiv = 0;

    int akt_index = 0;
    int zaehler_nk = 0;
    int ziffern_zaehler = 0;

    if ( ( pString != null ) && ( pString.length() > 0 ) )
    {
      /*
       * Es wird ermittelt, ob das Nachkommatrennzeichen auf einen Punkt geaendert werden muss. 
       * Per Vorgabe wird das Komma als Trennzeichen genommen.
       * Wird in der Eingabe kein Komma gefunden, wird der Punkt als Trennzeichen genommen. 
       * 
       * Die Notwendigkeit ergab sich, da die Eingabe ja auch schon korrekt 
       * formatiert uebergeben werden kannm, z.B. aus den Werten einer DB.
       * 
       * Da dieses Vorgehen aber auch unerwartete Seiteneffekte haben kann,
       * kann dieses Vorgehen von aussen mit einer boolschen Variable gesteuert
       * werden.
       */
      if ( ( pKnzFallbackTrennzeichenEin ) && ( pString.indexOf( "," ) == -1 ) )
      {
        trennzeichen_nk = '.';
      }

      while ( akt_index < pString.length() )
      {
        aktuelles_zeichen = pString.charAt( akt_index );

        if ( aktuelles_zeichen >= '0' && aktuelles_zeichen <= '9' )
        {
          /*
           * Ist der Zaehler fuer die Nachkommastellen kleiner als die gewuenschte
           * Anzahl der Nachkommastellen, wird die aktuelle Zahl dem Ergebnis
           * hinzugefuegt. 
           * 
           * Dieses wird auch dann gemacht, wenn die Anzahl der gewuenschten 
           * Nachkommastellen 0 ist. In einem solchen Fall wird dem Aufrufer 
           * die Eingabe nur in eine Zahl konvertiert.
           */
          if ( ( zaehler_nk < pAnzahlNachkommaStellen ) || ( pAnzahlNachkommaStellen < 0 ) )
          {
            str_ergebnis.append( aktuelles_zeichen );

            zaehler_nk = zaehler_nk + knz_nk_aktiv; // knz_nk_aktiv = 1 wenn Leseprozess in Nachkommastellen

            ziffern_zaehler = ziffern_zaehler + 1;
          }
          /*
           * Soll die Anzahl der Nachkommastellen 0 sein, duerfen nur 
           * solange Zahlen hinzugefuegt werden, solange das Kennzeichen fuer 
           * "leseprozeß innerhalb Nachkommastellen" nicht gesetzt ist.
           * 
           * ... oder anders, wenn sich der Leseprozeß in den Nachkommastellen
           * befindet und die Anzahl der NK-Stellen soll 0 sein, werden alle
           * Zahlen ignoriert. 
           */
          else if ( ( pAnzahlNachkommaStellen == 0 ) && ( knz_nk_aktiv == 0 ) )
          {
            str_ergebnis.append( aktuelles_zeichen );

            ziffern_zaehler = ziffern_zaehler + 1;
          }
        }
        else if ( aktuelles_zeichen == trennzeichen_nk )
        {
          /*
           * Wurden bisher nur 0en gelesen, muss verhindert werden, das ein 
           * Ergebnis der Form "0000,xyz" zurueckgegeben wird. Der bisherige 
           * Stringbuffer wird ausgenullt und nur eine fuehrende 0 hinzugefuegt.
           */
          if ( ( ziffern_zaehler > 0 ) && ( parseInt( str_ergebnis.toString(), 0 ) == 0 ) )
          {
            str_ergebnis = new StringBuffer();

            str_ergebnis.append( '0' );
          }

          /*
           * Ist das aktuelle Zeichen ein Komma, wird dieses beim 
           * ersten Auftretetn in einen Punkt gewandelt. 
           * 
           * Das Umwandeln darf nicht doppelt gemacht werden und nur 
           * dann, wenn die gewuenschte Anzahl der NKStellen groeßer 0 ist.
           */
          if ( knz_nk_aktiv == 0 )
          {
            if ( pAnzahlNachkommaStellen > 0 )
            {
              str_ergebnis.append( pTrennzeichenFuerNK );
            }
          }

          /*
           * Kennzeichen wird auf 1 gesetzt
           */
          knz_nk_aktiv = 1;

          /*
           * Das Trennzeichen fuer die Nachkommastellen wird mit dem Zeichen 1 
           * versehen um zu verhindern, das mehrere Trennstellen gefunden werden.
           */
          trennzeichen_nk = '1';
        }
        else if ( aktuelles_zeichen == '-' )
        {
          knz_negativ = "-";
        }

        akt_index++;
      }
      /*
       * In der Eingabe waren keine Zahlen vorhanden z.B. " Test,Test".
       * In diesem Fall wuerde das Komma durch einen Punkt ersetzt werden. 
       * Der StringBuffer muss neu initialisiert werden, damit z.B. 0.00 
       * aus dem Rest der Routine hergestellt werden kann. 
       */
      if ( ziffern_zaehler == 0 )
      {
        str_ergebnis = new StringBuffer();
      }
    }

    /*
     * Ist die Eingabe null, ein Leerstring, oder durch vorangegangene 
     * Abfragen wieder ausgenullt worden ist, ist die Laenge des String-
     * Buffers 0. Damit jetzt eine korrekte Zahl erstellt werden 
     * kann, wird eine fuehrende 0 hinzugefuegt.
     * 
     * Abaenderung: Eine Pruefung des Intwertes aus "ziffern_zaehler" kann 
     * schneller gemacht werden.
     */
    //if ( ergebnis_string.length() == 0 )
    if ( ziffern_zaehler == 0 )
    {
      str_ergebnis.append( '0' );
    }

    /*
     * Hier werden die gewuenschten Anzahl der Nachkommastellen hinzugefuegt. 
     * 
     * Ist der Nachkommastellenzaehler noch 0, muss noch ein Punkt hinzugefuegt werden.
     * 
     * Das Hinzufuegen darf nur gemacht werden, wenn die Anzahl der Nachkommastellen 
     * groesser als 0 ist.
     */
    if ( pAnzahlNachkommaStellen > 0 )
    {
      while ( zaehler_nk < pAnzahlNachkommaStellen )
      {
        if ( ( zaehler_nk == 0 ) && ( knz_nk_aktiv == 0 ) )
        {
          str_ergebnis.append( pTrennzeichenFuerNK );
        }
        str_ergebnis.append( '0' );

        zaehler_nk = zaehler_nk + 1;
      }
    }

    /*
     * Die Rueckgabe der Funktion ist das Zeichen aus knz_negativ und dem 
     * Inhalt aus dem Stringbuffer. 
     */
    return knz_negativ + str_ergebnis.toString();
  }

  public static String getWaehrungsZahl( BigDecimal x, String pStrWaehrungsEinheit )
  {
    return getWaehrungsZahl( x, pStrWaehrungsEinheit, 2 );
  }

  public static String getWaehrungsZahl( BigDecimal x, String pStrWaehrungsEinheit, int pAnzahlNkStellen )
  {
    return getZahl( x.toString(), ",", pAnzahlNkStellen, true ) + pStrWaehrungsEinheit;
  }

  public static String getWaehrungsZahl( String pEingabe, String pStrWaehrungsEinheit, int pAnzahlNkStellen )
  {
    return getZahl( pEingabe, ",", pAnzahlNkStellen, false ) + pStrWaehrungsEinheit;
  }

  /**
   * @param pUntergrenze Untergrenze des Bereiches
   * @param pObergrenze Obergrenze des Bereiches
   * @param pZahl die zu pruefende Zahl
   * @return 0 wenn die Zahl im Bereich liegt, -1 wenn die Zahl kleiner als die Untergrenze ist, +1 wenn die Zahl groesser als die Obergrenze ist
   */
  public static int istInBereich( int pUntergrenze, int pObergrenze, int pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return -1;
    }

    if ( pZahl > pObergrenze )
    {
      return 1;
    }

    return 0;
  }

  /**
   * @param pUntergrenze Untergrenze des Bereiches
   * @param pObergrenze Obergrenze des Bereiches
   * @param pZahl die zu pruefende Zahl
   * @return 0 wenn die Zahl im Bereich liegt, -1 wenn die Zahl kleiner als die Untergrenze ist, +1 wenn die Zahl groesser als die Obergrenze ist
   */
  public static int istInBereich( double pUntergrenze, double pObergrenze, double pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return -1;
    }

    if ( pZahl > pObergrenze )
    {
      return 1;
    }

    return 0;
  }

  /**
   * @param pUntergrenze Untergrenze des Bereiches
   * @param pObergrenze Obergrenze des Bereiches
   * @param pZahl die zu pruefende Zahl
   * @return 0 wenn die Zahl im Bereich liegt, -1 wenn die Zahl kleiner als die Untergrenze ist, +1 wenn die Zahl groesser als die Obergrenze ist
   */
  public static int istInBereich( long pUntergrenze, long pObergrenze, long pZahl )
  {
    if ( pZahl < pUntergrenze )
    {
      return -1;
    }

    if ( pZahl > pObergrenze )
    {
      return 1;
    }

    return 0;
  }

  /**
   * <pre>
   * Prueft die Eingabe auf einen korrekten Betrag.
   * 
   * FkZahl.checkBetrag( "5000.00", 4000.00,  6000.00, true  ) = "0"
   * FkZahl.checkBetrag( "5123.45", 4000.00,  6000.00, false ) = "0" = Krumme Zahlen zugelassen
   * FkZahl.checkBetrag( "7000.00", 4000.00,     null, true  ) = "0" = keine Maximalgrenze vorgegeben
   * FkZahl.checkBetrag( "3000.00",    null,  6000.00, true  ) = "0" = keine Minimalgrenze vorgegeben
   * 
   * FkZahl.checkBetrag(      null, 4000.00,  6000.00, true  ) = "1" = Eingabe ist null
   * FkZahl.checkBetrag( "5000,00", 4000.00,  6000.00, true  ) = "1" = Eingabe ergibt keine Zahl (Trennzeichen Komma nicht erlaubt)
   * FkZahl.checkBetrag( "ABCD.EF", 4000.00,  6000.00, true  ) = "1" = Eingabe ergibt keine Zahl
   * 
   * FkZahl.checkBetrag( "5001.00", 4000.00,  6000.00, true  ) = "2" = Eingabe ist keine grade Zahl
   * FkZahl.checkBetrag( "5000.01", 4000.00,  6000.00, true  ) = "2"
   * 
   * FkZahl.checkBetrag( "3000.00", 4000.00,  6000.00, true  ) = "3" = Untergrenze unterschritten
   * FkZahl.checkBetrag( "7000.00", 4000.00,  6000.00, true  ) = "4" = Obergrenze unterschritten
   * </pre>
   * 
   * @param pEingabe die zu pruefende Eingabe 
   * @param pMinBetrag die zu pruefende Minimalgrenze der Eingabe (bei Uebergabe von null wird nichts geprueft) 
   * @param pMaxBetrag die zu pruefende Maximalgrenze der Eingabe (bei Uebergabe von null wird nichts geprueft) 
   * @param pKnzCheckAufVolleTausender Kennzeichen, ob die Pruefung auf volle Tausender gemacht werden soll
   * @return 0 sofern die Eingabe korrekt ist.
   */
  public static int getKnzCheckBetrag( String pEingabe, BigDecimal pMinBetrag, BigDecimal pMaxBetrag, boolean pKnzCheckAufVolleTausender )
  {
    BigDecimal betrag_eingabe = null;

    try
    {
      betrag_eingabe = new BigDecimal( pEingabe );
    }
    catch ( Exception err_inst )
    {
      betrag_eingabe = null;
    }

    if ( betrag_eingabe == null )
    {
      return 1;
    }

    if ( pKnzCheckAufVolleTausender )
    {
      BigDecimal big_decimal_null = new BigDecimal( 0 );

      BigDecimal big_decimal_neu = new BigDecimal( betrag_eingabe.movePointLeft( 3 ).intValue() ).movePointRight( 3 );

      if ( betrag_eingabe.subtract( big_decimal_neu ).compareTo( big_decimal_null ) != 0 )
      {
        return 2;
      }
    }

    if ( pMinBetrag != null )
    {
      if ( betrag_eingabe.compareTo( pMinBetrag ) < 0 )
      {
        return 3;
      }
    }

    if ( pMaxBetrag != null )
    {
      if ( betrag_eingabe.compareTo( pMaxBetrag ) > 0 )
      {
        return 4;
      }
    }

    return 0;
  }

  public static int getKnzCheckInteger( String pEingabe, Integer pMinWert, Integer pMaxWert )
  {
    Integer integer_eingabe = null;

    try
    {
      integer_eingabe = new Integer( pEingabe );
    }
    catch ( Exception err_inst )
    {
      integer_eingabe = null;
    }

    if ( integer_eingabe == null )
    {
      return 1;
    }

    if ( pMinWert != null )
    {
      if ( integer_eingabe.compareTo( pMinWert ) < 0 )
      {
        return 3;
      }
    }

    if ( pMaxWert != null )
    {
      if ( integer_eingabe.compareTo( pMaxWert ) > 0 )
      {
        return 4;
      }
    }

    return 0;
  }

  public int getIndexPostleitzahlBereich( String pPostleitzahl )
  {
    if ( checkInteger( pPostleitzahl, 5, 5 ) )
    {
      /*
       * Index = erste Stelle der Postleitzahl 
       */
      return FkZahl.getInteger( pPostleitzahl.substring( 0, 1 ), -1 );
    }

    return -1;
  }

  public static double round2DP( double pZahl )
  {
    return (double) ( (int) ( pZahl * 100 ) * 0.01 );// + 0.00000001;
  }

  public static double round3DP( double pZahl )
  {
    return (double) ( (int) ( pZahl * 1000 ) * 0.001 ) + 0.00000001;
  }


  public static int getIntKennzeichenFeld( String pString )
  {
    int int_wert = FkZahl.getInteger( pString );

    if ( int_wert < 0 )
    {
      return -1;
    }

    if ( int_wert > 0 )
    {
      return 1;
    }

    return 0;
  }

  /**
   * <pre>
   * Prueft, ob der Parameter "pString" nur Zahlen enthaelt.
   * 
   * Es wird nicht geprueft, ob aus "pString" ein Integer-Wert erstellt werden kann. 
   * (Overflowexception)
   * 
   * Auf die Eingabe wird kein TRIM gemacht. 
   * 
   * FkZahl.checkInteger( "12345", -1, -1 ) = true
   * FkZahl.checkInteger( "12345",  0,  0 ) = true = Laengenangaben greifen erst wenn diese 
   *                                                 groesser als 0 sind.
   * 
   * FkZahl.checkInteger( "123.45", -1, -1 ) = false = In der Eingabe darf kein Punkt enthalten sein
   * 
   * FkZahl.checkInteger( "12345",  6, -1 ) = false = zu kurz
   * FkZahl.checkInteger( "12345", -1,  4 ) = false = zu lang
   *  
   * FkZahl.checkInteger( "12345",  4,  6 ) = true  = Mindestlaenge groesser 4, aber nicht mehr als 6 
   * FkZahl.checkInteger( "12345",  5,  5 ) = true  = Mindestlaenge und Maximallaenge gleich 5
   * FkZahl.checkInteger( "12345",  4,  4 ) = false = Eingabe muss 4 Zeichen lang sein
   * FkZahl.checkInteger( "12345",  6,  6 ) = false = Eingabe muss 6 Zeichen lang sein
   * 
   * FkZahl.checkInteger( "12345",  6,  4 ) = false = Die Eingabe soll mindestens 6 Zeichen haben. 
   *                                                  Die maximale Laenge soll 4 Zeichen betragen.
   *                                                  Aufgrund der maximalen Laenge wird die Eingabe abgewiesen.
   *  
   * FkZahl.checkInteger(    null, -1, -1 ) = false = Keine Eingabe vorhanden
   * FkZahl.checkInteger(      "", -1, -1 ) = false = Eingabe ist Leerstring
   * FkZahl.checkInteger( "     ", -1, -1 ) = false = Keine Ziffern im Eingabestring
   * FkZahl.checkInteger( "abcde", -1, -1 ) = false  
   * FkZahl.checkInteger( "  123", -1, -1 ) = false = Vorlaufende Leerzeichen fuehren zum Fehler
   * FkZahl.checkInteger( "00123", -1, -1 ) = true  = Vorlaufende 0en sind Ziffern, daher OK
   * FkZahl.checkInteger( "00000", -1, -1 ) = true  = Es wird nicht auf einen Integerwert groesser 0 geprueft, 
   *                                                  das muss der Aufrufer machen.
   * </pre>
   * 
   * @param pString die zu pruefende Eingabezeichenfolge (es wird kein Trim gemacht)
   * @param pLaengeMinimal Mindestlaenge der Eingabe (muss groesser 0 fuer eine Pruefung sein)
   * @param pLaengeMaximal Maximallaenge der Eingabe (muss groesser 0 fuer eine Pruefung sein)
   * @return TRUE wenn die Eingabezeichenfolge ein Integerwert ist, sonst false
   */
  public static boolean checkInteger( String pString, int pLaengeMinimal, int pLaengeMaximal )
  {
    /*
     * Pruefung: Parameter "pString" gleich "null" ?
     * 
     * Ist der zu pruefende String nicht gesetzt, ist es kein Integer. 
     * Der Aufrufer bekommt false zurueck.
     */
    if ( pString == null )
    {
      return false;
    }

    /*
     * Ermittlung der Eingabestringlaenge
     */
    int laenge_eingabe = pString.length();

    /*
     * Pruefung: Eingabelaenge gleich 0 ?
     * 
     * Eine Eingabelaenge von 0, ist die Eingabe keine Zahl. Es wird false zurueckgegeben.
     */
    if ( laenge_eingabe == 0 )
    {
      return false;
    }

    /*
     * Pruefung auf Einhaltung einer Minimallaenge
     * 
     * Wurde eine Minimallaenge groesser 0 uebergeben, wird die Eingabelaenge 
     * auf Einhaltung dieser Mindestlaenge geprueft. Ist der Eingabestring 
     * kuerzer als die Vorgabelaenge, wird false zurueckgegeben.
     */
    if ( ( pLaengeMinimal > 0 ) && ( laenge_eingabe < pLaengeMinimal ) )
    {
      return false;
    }

    /*
     * Pruefung auf Einhaltung einer Maximallaenge
     * 
     * Wurde eine Maximallaenge groesser 0 uebergeben, wird die Eingabelaenge 
     * auf Einhaltung dieser Maximallaenge geprueft. Ist der Eingabestring 
     * laenger als die Vorgabelaenge, wird false zurueckgegeben.
     */
    if ( ( pLaengeMaximal > 0 ) && ( laenge_eingabe > pLaengeMaximal ) )
    {
      return false;
    }

    /*
     * Pruefung der Stringzeichen
     * 
     * In einer While-Schleife werden alle Zeichen der Eingabe auf 
     * Ziffer geprueft. Ist ein Zeichen keine Ziffer, wird false 
     * zurueckgegeben.
     */
    int akt_index = 0;

    while ( akt_index < laenge_eingabe )
    {
      char aktuelles_zeichen = pString.charAt( akt_index );

      if ( ( aktuelles_zeichen < '0' ) || ( aktuelles_zeichen > '9' ) )
      {
        return false;
      }

      akt_index++;
    }

    /*
     * Am Funktionsende wird TRUE zurueckgeben, da alle Pruefungen 
     * ohne Fehler gemacht werden konnten. 
     */
    return true;
  }

  /**
   * <pre>
   * Gibt die Information zurueck, ob die Zeichenfolge "pString" einen double-Wert enthaelt.
   * Auf pNummer wird ein TRIM ausgefuehrt. 
   * </pre>
   * 
   * @param pString die zu pruefende Zeichenfolge 
   * @return TRUE wenn die Eingabe eine Zahl ist, sonst FALSE
   */
  public static boolean checkNumber( String pString )
  {
    try
    {
      if ( pString != null )
      {
        Double.parseDouble( pString.trim() );

        return true;
      }
    }
    catch ( Exception err_inst )
    {
      // 
    }

    return false;
  }

  public static boolean checkNumberWithDecimal( String pString, int pAnzahlNachkommastellen )
  {
    /*
     * Pruefung: Parameter "pString" gleich "null" ?
     * 
     * Ist der zu pruefende String nicht gesetzt, ist es keine Zahl 
     * Der Aufrufer bekommt false zurueck.
     */
    if ( pString == null )
    {
      return false; // Struktur: Eingabe ist nicht gesetzt
    }

    /*
     * Ermittlung der Eingabestringlaenge
     */
    int laenge_eingabe = pString.length();

    /*
     * Pruefung: Eingabelaenge gleich 0 ?
     * 
     * Eine Eingabelaenge von 0, ist die Eingabe keine Zahl. Es wird false zurueckgegeben.
     */
    if ( laenge_eingabe == 0 )
    {
      return false; // Struktur: Eingabe ist ein Leerstring
    }

    if ( laenge_eingabe > 500 )
    {
      return false; // Struktur: Eingabe ist zu lang fuer einen Waehrungsbetrag
    }

    char aktuelles_zeichen = '-';

    int pos_punkt = -1;
    int zeichen_zaehler = 0;
    int akt_index = 0;

    /*
     * While-Schleife ueber alle Zeichen der Eingabe
     */
    while ( akt_index < laenge_eingabe )
    {
      aktuelles_zeichen = pString.charAt( akt_index );

      if ( ( aktuelles_zeichen >= '0' ) && ( aktuelles_zeichen <= '9' ) )
      {
        zeichen_zaehler++;

        if ( pos_punkt != -1 )
        {
          if ( zeichen_zaehler > 2 )
          {
            return false; // Struktur: Mehr Ziffern im Nachkommastellenteil
          }
        }
      }
      else if ( aktuelles_zeichen == '.' )
      {
        if ( pos_punkt != -1 )
        {
          return false; // Struktur: es darf nur einmal ein Punkt vorkommen
        }

        if ( akt_index == 0 )
        {
          return false; // Struktur: kein Start mit einem Punkt
        }

        pos_punkt = akt_index;

        zeichen_zaehler = 0;
      }
      else if ( aktuelles_zeichen == '-' )
      {
        if ( akt_index > 0 )
        {
          return false; // Struktur: Negativzeichen muss am Start stehen
        }
      }
      else
      {
        return false; // Struktur: ungueltiges Zeichen in der Eingabe gefunden
      }

      akt_index++;
    }

    if ( aktuelles_zeichen == '.' )
    {
      return false; // Struktur: der letzte Punkt darf nicht am Ende liegen
    }

    if ( pos_punkt == -1 )
    {
      return false; // Struktur: kein Punkt gefunden
    }

    if ( pAnzahlNachkommastellen > 0 )
    {
      if ( zeichen_zaehler != pAnzahlNachkommastellen )
      {
        return false; // Struktur: Es muessen genau x Nachkommastellen vorhanden sein
      }
    }

    return true;
  }

  /**
   * <pre>
   * Liefert die Zahl zurueck, welche sich hinter dem fuehrenden "D" befindet.
   * 
   * Bei einem Fehler wird -1 zurueckgegeben.
   * 
   * FkZahl.parseZahlDokumentIndex( "D0"     ) =  0
   * FkZahl.parseZahlDokumentIndex( "D1"     ) =  1
   * FkZahl.parseZahlDokumentIndex( "D02"    ) =  2
   * FkZahl.parseZahlDokumentIndex( "D003"   ) =  3
   * FkZahl.parseZahlDokumentIndex( "D0004"  ) =  4
   * 
   * FkZahl.parseZahlDokumentIndex( "D00005" ) = -1
   * FkZahl.parseZahlDokumentIndex( "0001"   ) = -1
   * FkZahl.parseZahlDokumentIndex( "D-01"   ) = -1
   * FkZahl.parseZahlDokumentIndex( ""       ) = -1
   * FkZahl.parseZahlDokumentIndex( "A1"     ) = -1
   * FkZahl.parseZahlDokumentIndex( null     ) = -1
   * 
   * </pre>
   * 
   * @param pEingabe die zu parsende Eingabe
   * @return die geparste Zahl, oder -1, wenn keine Zahl oder ein Formatfehler vorliegt.
   */
  public static int parseZahlDokumentIndex( String pEingabe )
  {
    /*
     * Pruefung: Ist die Eingabe null?
     * Ist die Eingabe null, bekommt der Aufrufer -1 zurueck.
     */
    if ( pEingabe == null )
    {
      return -1;
    }

    /*
     * Pruefung: Zeichenanzahl korrekt?
     * Die Eingabe darf nicht laenger als 5 Zeichen sein. 
     * Ist die Eingabe laenger, bekommt der Aufrufer -1 zurueck.
     */
    if ( ( pEingabe.length() == 0 ) || ( pEingabe.length() > 5 ) )
    {
      return -1;
    }

    int akt_zahl = 0;

    int akt_index = 0;

    /*
     * While-Schleife ueber die Eingabe zum lesen der Zahl aus der Eingabe
     */
    while ( akt_index < pEingabe.length() )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe lesen
       */
      char akt_char = pEingabe.charAt( akt_index );

      /*
       * Zeichenpruefung
       * 
       * Das Format ist "DZZZZ"
       */

      if ( akt_index == 0 )
      {
        /*
         * Das erstes Zeichen muss ein D sein. Alle anderen Zeichen 
         * fuehren zu einem FehlerK
         */
        if ( akt_char != 'D' )
        {
          return -1;
        }

      }
      else if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
      {
        /*
         * Alle Zeichen nach dem einleitendem D muessen Zahlen sein. 
         * Ist das nicht der Fall, wird 0 zurueckgegeben.
         */
        return -1;
      }
      else
      {
        /*
         * Berechnung Akt-Zahl
         * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
         * der Wert des aktuellen Zeichens hinzugezaehlt. 
         * 
         * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48. 
         */
        akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );
      }

      /*
       * Leseprozess ein Zeichen weiter stellen 
       */
      akt_index++;
    }

    /*
     * Nach der While-Schleife wird die gelesene Zahl zurueckgegeben.
     */
    return akt_zahl;
  }
}
