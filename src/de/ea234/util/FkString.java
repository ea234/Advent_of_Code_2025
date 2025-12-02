package de.ea234.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

/**
 * <pre>
 * Hilfsfunktionen fuer die Stringverarbeitung.
 *  
 * Die Funktionen sollen moeglichst nicht miteinander verwoben werden, damit
 * jede einzelne Funktion fuer sich allein funktionsfaehig kopierbar bleibt.
 * 
 * Die Funktionen sollen Java 1.3 kompatibel sein, daher keine Funktionsaufrufe 
 * von neueren Java-Funktionen. Warnungen "Unessecary Cast" wurden nicht entfernt, 
 * da in einer anderen Umgebung der Cast evtl. nicht mehr "Unessecary" ist. 
 * </pre>
 */
public class FkString
{
  /*
   * 1         2         3         4         5         6         7         8         9
   * 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
   * 
   * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   * A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z
   * 
   * a b c d e f g h i j k l m n o p q r s t u v w x y z
   * a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
   */

  public static final char    UMLAUT_U_KLEIN                = 0x00fc;                                                                                                                                                                                                                // " + FkString.UMLAUT_U_KLEIN + "

  public static final char    UMLAUT_U_GROSS                = 0x00dc;                                                                                                                                                                                                                // " + FkString.UMLAUT_U_GROSS + "

  public static final char    UMLAUT_SZ                     = 0x00df;                                                                                                                                                                                                                // " + FkString.UMLAUT_SZ      + "

  public static final char    UMLAUT_O_KLEIN                = 0x00f6;                                                                                                                                                                                                                // " + FkString.UMLAUT_O_KLEIN + "

  public static final char    UMLAUT_O_GROSS                = 0x00d6;                                                                                                                                                                                                                // " + FkString.UMLAUT_O_GROSS + "

  public static final char    UMLAUT_A_KLEIN                = 0x00e4;                                                                                                                                                                                                                // " + FkString.UMLAUT_A_KLEIN + "

  public static final char    UMLAUT_A_GROSS                = 0x00c4;                                                                                                                                                                                                                // " + FkString.UMLAUT_A_GROSS + "

  public static final char    CHAR_PARAGRAPH                = 167;                                                                                                                                                                                                                   // " + FkString.CHAR_PARAGRAPH + "

  public static final char    CHAR_DOLLAR                   = 36;                                                                                                                                                                                                                    // " + FkString.CHAR_DOLLAR    + "

  public static final char    CHAR_APOSTROPHE               = 0x0027;                                                                                                                                                                                                                // " + FkString.CHAR_APOSTROPHE + "

  public static final int     ASCII_NULL_CHARACTER          = 0;

  public static final int     ASCII_START_OF_HEADER         = 1;

  public static final int     ASCII_START_OF_TEXT           = 2;

  public static final int     ASCII_END_OF_TEXT             = 3;

  public static final int     ASCII_END_OF_TRANS            = 4;

  public static final int     ASCII_ENQUIRY                 = 5;

  public static final int     ASCII_ACKNOWLEDGEMENT         = 6;

  public static final int     ASCII_BELL                    = 7;

  public static final int     ASCII_BACKSPACE               = 8;

  public static final int     ASCII_HORIZONTAL_TAB          = 9;

  public static final int     ASCII_LINE_FEED               = 10;

  public static final int     ASCII_VERTICAL_TAB            = 11;

  public static final int     ASCII_FORM_FEED               = 12;

  public static final int     ASCII_CARRIAGE_RETURN         = 13;

  public static final int     ASCII_SHIFT_OUT               = 14;

  public static final int     ASCII_SHIFT_IN                = 15;

  public static final int     ASCII_DATA_LINK_ESCAPE        = 16;

  public static final int     ASCII_DEVICE_CONTROL_1        = 17;

  public static final int     ASCII_DEVICE_CONTROL_2        = 18;

  public static final int     ASCII_DEVICE_CONTROL_3        = 19;

  public static final int     ASCII_DEVICE_CONTROL_4        = 20;

  public static final int     ASCII_NEGATIVE_ACKNOWL        = 21;

  public static final int     ASCII_SYNCHRONOUS_IDLE        = 22;

  public static final int     ASCII_END_OF_TRANS_BLOCK      = 23;

  public static final int     ASCII_CANCEL                  = 24;

  public static final int     ASCII_END_OF_MEDIUM           = 25;

  public static final int     ASCII_SUBSTITUTE              = 26;

  public static final int     ASCII_ESCAPE                  = 27;

  public static final int     ASCII_FILE_SEPARATOR          = 28;

  public static final int     ASCII_GROUP_SEPARATOR         = 29;

  public static final int     ASCII_RECORD_SEPARATOR        = 30;

  public static final int     ASCII_UNIT_SEPARATOR          = 31;

  public static final int     ASCII_DELETE                  = 127;

  /** Konstante fuer ein Leerzeichen */
  public static final String  LEERZEICHEN                   = " ";

  /** Konstante fuer ein UNTER_STRICH */
  public static final String  UNTER_STRICH                  = "_";

  /** Konstante fuer einen Leerstring */
  public static final String  LEERSTRING                    = "";

  /** Konstante fuer einen String mit der Zahl 0 */
  public static final String  STR_ZAHL_0                    = "0";

  /** Konstante fuer einen String mit der Zahl 1 */
  public static final String  STR_ZAHL_1                    = "1";

  /** Alle Zeichenfolgen, welchen einen wahren boolschen Wert ergeben koennen. Durch Komma getrennt. ",true,yes,ja,y,j,1,on,+,ein,wahr,ok," */
  private static final String STR_WERTE_BOOLEAN_TRUE        = ",true,yes,ja,y,j,1,on,+,ein,wahr,ok,";

  private static final String STR_WERT_BOOLEAN_TRUE         = ",true,";

  /** Alle Zeichenfolgen, welchen einen nicht wahren boolschen Wert ergeben koennen. Durch Komma getrennt. ",false,no,nein,n,0,off,-,aus,falsch," */
  private static final String STR_WERTE_BOOLEAN_FALSE       = ",false,no,nein,n,0,off,-,aus,falsch,";

  private static final String STR_WERT_BOOLEAN_FALSE        = ",false,";

  /** Ein String mit den Zahlen als Inhalt */
  public static final String  STR_ZAHLEN                    = "0123456789";

  /** Definition der gueltigen Zeichen fuer ein Datum im String-Format = '0123456789. ' */
  public static final String  STR_ZEICHEN_DATUM_STRING      = "0123456789. ";

  /** Definition der gueltigen Zeichen fuer ein Datum im Long-Format = '0123456789' */
  public static final String  STR_ZEICHEN_DATUM_LONG        = "0123456789";

  /** Definition der gueltigen Zeichen fuer eine Betragsangabe = '0123456789,. ' */
  public static final String  STR_ZEICHEN_ZAHL_BETRAG       = "0123456789,. +-";

  /** Ein String mit den Buchstaben des ABC in Gross */
  public static final String  STR_ABC_GROSS                 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + FkString.getStringSonderzeichenDE( "", 1 );

  /** Ein String mit den Buchstaben des ABC in klein */
  public static final String  STR_ABC_KLEIN                 = "abcdefghijklmnopqrstuvwxyzss" + FkString.getStringSonderzeichenDE( "", -1 );

  public static final String  STR_ABC_GROSS_HAEUFIGKEIT_DE  = "ENISRATDHULCGMOBWFKZPVßJYXQ";

  public static final String  STR_ABC_KLEIN_HAEUFIGKEIT_DE  = "enisratdhulcgmobwfkzpvßjyxq";

  /** String mit Alphabeth klein und Gross und den Zahlen, sortiert nach der Haeufigkeit des Vorkommens in einem Text von 32000 Zeichen */
  public static final String  STR_ABC_ZAHLEN_HAEUFIGKEIT_DE = "enirstaudhgolcmfbkVvwz1paeSDA0E2RBGueMIPKF9UNW3L78oeH4T5CZJy6xjOUeYXqQ" + FkString.getStringSonderzeichenDE( "", 0 );

  public static final String  STR_ABC_ZAHLEN_HAEUFIGKEIT_EN = "etnoiarshlcdufpgmwvyb1AIPCT0k37S2FVR4B8x956GMDLXqJjENHzOKUWQQ" + FkString.getStringSonderzeichenDE( "", 0 );

  public static final String  STR_ZEICHEN_VERZEICHNIS       = "etnoiarshlcdufpgmwvyb1AIPCT0k37S2FVR4B8x956GMDLXqJjENHzOKUWQ\\/+-.,:" + FkString.getStringSonderzeichenDE( "", 0 );

  /** Ein String mit einigen Sonderzeichen */
  public static final String  STR_SONDERZEICHEN             = " _?!\"ß$%&/()<>[]{}=*'/*-+:;,.#\\/\n";

  /** Ein String fuer die Darstellung von 100 Zeichen-Lineal*/
  public static final String  STR_LINEAL_100_PUNKT_5        = "....+....1....+....2....+....3....+....4....+....5....+....6....+....7....+....8....+....9....+....0";

  public static final String  STR_GATTER_TRENSTRING_100     = "####################################################################################################";

  public static final String  STR_LEERZEICHEN_100           = "                                                                                                    ";

  public static final String  DEFAULT_EMPTY                 = "";

  public static final String  DEFAULT_TRUE                  = "true";

  public static final String  DEFAULT_FALSE                 = "false";

  public static final String  DEFAULT_ZERO                  = "0";

  public static final String  DEFAULT_ONE                   = "1";

  /**
   *  Eine Stringkonstante mit null. Zur Verwendung ueberall dort, wo ein String uebergeben werden muss, der aber null sein soll
   *  Vermeidung von:
   *  The method getResultFeldwert(Connection, String[], String, String) in the type FkJdbc is not applicable for the arguments (Connection, String, String, null) 
   */
  public static final String  STRING_NULL                   = null;

  /** Konstante fuer ein Komma */
  public static final String  KOMMA                         = ",";

  /** Konstante fuer einen Java-Zeilenumbruch */
  public static final String  ZEILENUMBRUCH                 = "\n";

  public static final String  J                             = "J";

  public static final String  N                             = "N";

  /** UTF-8 */
  public static final String  ZEICHEN_CODIERUNG_UTF8        = "UTF-8";

  /** String Cp1047*/
  public static final String  ZEICHEN_CODIERUNG_CP10470     = "Cp1047";

  public static final String  ZEICHEN_CODIERUNG_CP1252      = "Cp1252";

  public static final String  ZEICHEN_CODIERUNG_ASCII       = "ASCII";

  /** String ISO-8859-1*/
  public static final String  ZEICHEN_CODIERUNG_ISO_8859_1  = "ISO-8859-1";

  public static final String  ZEICHEN_CODIERUNG_EBCDIC      = "EBCDIC";

  public static String        STR_SONDERZEICHEN_DE          = "" + FkString.UMLAUT_A_GROSS + "" + FkString.UMLAUT_A_KLEIN + "" + FkString.UMLAUT_U_GROSS + "" + FkString.UMLAUT_U_KLEIN + "" + FkString.UMLAUT_O_GROSS + "" + FkString.UMLAUT_O_KLEIN + "" + FkString.UMLAUT_SZ + "";

// " + FkString.UMLAUT_A_GROSS + "," + FkString.UMLAUT_A_KLEIN + "," + FkString.UMLAUT_U_GROSS + "," + FkString.UMLAUT_U_KLEIN + "," + FkString.UMLAUT_O_GROSS + "," + FkString.UMLAUT_O_KLEIN + "," + FkString.UMLAUT_SZ + "

  /**
   * <pre>
   * Liefert einen String mit den deutschen Umlauten zurueck.
   * Notwendig um Zeichensatzprobleme zu vermeiden. 
   *
   * FkString.getStringSonderzeichenDE( null,  0 ) = ÄÖÜäöüß
   * FkString.getStringSonderzeichenDE( ", ",  0 ) = Ä, Ö, Ü, ä, ö, ü, ß
   * 
   * FkString.getStringSonderzeichenDE( null,  1 ) = ÄÖÜ
   * FkString.getStringSonderzeichenDE( ", ",  1 ) = Ä, Ö, Ü
   * 
   * FkString.getStringSonderzeichenDE( null, -1 ) = äöüß
   * FkString.getStringSonderzeichenDE( ", ", -1 ) = ä, ö, ü, ß
   * </pre>
   * 
   * @param pTrennzeichen das zu verwendende Trennzeichen
   * @param pKnz 0 gross und klein, 1 nur gross, -1 nur klein
   * @return den erstellten String 
   */
  public static String getStringSonderzeichenDE( String pTrennzeichen, int pKnz )
  {
    // " + FkString.getStringSonderzeichenDE( "" , 0 ) + "

    String str_trennzeichen = ( pTrennzeichen == null ? "" : pTrennzeichen );

    return ( pKnz >= 0 ? FkString.UMLAUT_A_GROSS + str_trennzeichen + FkString.UMLAUT_O_GROSS + str_trennzeichen + FkString.UMLAUT_U_GROSS : "" ) + ( pKnz == 0 ? str_trennzeichen : "" ) + ( pKnz <= 0 ? FkString.UMLAUT_A_KLEIN + str_trennzeichen + FkString.UMLAUT_O_KLEIN + str_trennzeichen + FkString.UMLAUT_U_KLEIN + str_trennzeichen + FkString.UMLAUT_SZ : "" );
  }

  public static void clearStringBuffer( StringBuffer pStringBuffer )
  {
    if ( pStringBuffer != null )
    {
      // pStringBuffer.delete( 0, m_string_buffer.length() );

      // pStringBuffer.replace( 0, m_string_buffer.length(), LEERSTRING );

      pStringBuffer.setLength( 0 );
    }
  }

  /**
   * <pre>
   * Fuehrt einen Class-Cast auf die Klasse "String" auf das uebergebene Objekt aus. 
   *
   * Kommt es zu einer ClassCastException wird <code>null</code> zurueckgegeben.
   * Ist das Objekt selber <code>null</code> wird <code>null</code> zurueckgegeben.
   *
   * String my_string = FkString.getClassCast( string_object );
   * </pre>
   *
   * @param pObject das zu castende Objekt
   * @return das Objekt als Instanz von String, oder null
   */
  public static String getClassCast( Object pObject )
  {
    if ( pObject != null )
    {
      try
      {
        return (String) pObject;
      }
      catch ( ClassCastException err_inst )
      {
        // keine Aktion, da Rueckgabe von null
      }
    }

    return null;
  }

  /**
   * <pre>
   * Fuehrt einen Class-Cast auf die Klasse "String" auf das uebergebene Objekt aus. 
   *
   * Kommt es zu einer ClassCastException wird der Vorgabewert zurueckgegeben.
   * Ist das Objekt selber <code>null</code> wird der Vorgabewert zurueckgegeben.
   *
   * String my_string = FkString.getClassCast( string_object, "Vorgabewert" );
   * </pre>
   *
   * @param pObject das zu castende Objekt
   * @return das Objekt als Instanz von String, oder null
   */
  public static String getClassCast( Object pObject, String pVorgabewert )
  {
    if ( pObject != null )
    {
      try
      {
        return (String) pObject;
      }
      catch ( ClassCastException err_inst )
      {
        // keine Aktion, da Rueckgabe des Vorgabewertes
      }
    }

    return pVorgabewert;
  }

  /**
   * <pre>
   * Fuehrt einen Class-Cast auf die Klasse "String" auf das uebergebene Objekt aus. 
   * Funktion ist equivalent zur "getClassCast"-Funktion. Wurde aber wegen des 
   * Funktionsnamens "getString" aufgenommen. 
   * 
   * Kommt es zu einer ClassCastException wird <code>null</code> zurueckgegeben.
   * Ist das Objekt selber <code>null</code> wird <code>null</code> zurueckgegeben.
   *
   * String my_string = FkString.getString( string_object );
   * 
   * Aufruf ist equivalent zu:
   * String my_string = FkString.getClassCast( string_object );
   * 
   * </pre>
   *
   * @param pObject das zu castende Objekt
   * @return das Objekt als Instanz von String, oder null
   */
  public static String getString( Object pObject )
  {
    if ( pObject != null )
    {
      try
      {
        return (String) pObject;
      }
      catch ( ClassCastException err_inst )
      {
        // keine Aktion, da Rueckgabe von null
      }
    }

    return null;
  }

  /**
   * @param pString der Eingabestring 
   * @param pKnzUpperLowerCase < 0 = Kleinbuchstaben, 0 = keine Veraenderung, > 0 = Grossbuchstaben
   * @return den gewandelten String, null wenn "pString" selber "null" ist
   */
  public static String getStringUL( String pString, int pKnzUpperLowerCase )
  {
    /*
     * Ist "pString" gleich "null", wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Ist der Parameter "pKnzUpperLowerCase" gleich 0, wird 
     * der unbehandelte String zurueckgegeben.
     */
    if ( pKnzUpperLowerCase == 0 )
    {
      return pString;
    }

    /*
     * Ist der Parameter "pKnzUpperLowerCase" groesser 0, wird 
     * "pString" in Grossbuchstaben gewandelt und zurueckgegeben.
     */
    if ( pKnzUpperLowerCase > 0 )
    {
      return pString.toUpperCase();
    }

    /*
     * Ist der Parameter "pKnzUpperLowerCase" kleiner 0, wird 
     * "pString" in Kleinbuchstaben gewandelt und zurueckgegeben.
     */
    return pString.toLowerCase();
  }

  /**
   * @param pZeichen das Zeichen
   * @return ( pZeichen >= 'A' ) && ( pZeichen <= 'Z' )
   */
  public static boolean istGrossBuchstabe( char pZeichen )
  {
    return ( pZeichen >= 'A' ) && ( pZeichen <= 'Z' );
  }

  /**
   * @param pZeichen das Zeichen
   * @return ( pZeichen >= 'a' ) && ( pZeichen <= 'z' )
   */
  public static boolean istKleinBuchstabe( char pZeichen )
  {
    return ( pZeichen >= 'a' ) && ( pZeichen <= 'z' );
  }

  /**
   * @param pZeichen das Zeichen
   * @return ( pZeichen >= '0' ) && ( pZeichen <= '9' )
   */
  public static boolean istZahl( char pZeichen )
  {
    return ( pZeichen >= '0' ) && ( pZeichen <= '9' );
  }

  /**
   * @param pString der mit pStringA zu vergleichende Wert
   * @param pStringA Wert A
   * @param pStringB Wert B
   * @return Wert B, wenn pString gleich Wert A ist. 
   */
  public static String getNegation( String pString, String pStringA, String pStringB )
  {
    return pString.equalsIgnoreCase( pStringA ) ? pStringB : pStringA;
  }

  /**
   * Fuehrt im Endeffekt "pStringA.compareTo( pStringB )" aus, kommt aber mit NULL-Referenzen klar.
   * 
   * @param pStringA der erste String 
   * @param pStringB der zweite String 
   * @return true wenn A und B gleich sind, sonst false
   */
  public static boolean compareString( String pStringA, String pStringB )
  {
    if ( pStringA != null )
    {
      if ( pStringB != null )
      {
        return pStringA.compareTo( pStringB ) == 0;
      }
    }

    return false;
  }

  /**
   * @param pWert
   * @param pStrWert1
   * @param pStrValue1
   * @param pStrWert2
   * @param pStrValue2
   * @param pElseStrValue
   * @return
   */
  public static String convertWert( String pWert, String pStrWert1, String pStrValue1, String pStrWert2, String pStrValue2, String pElseStrValue )
  {
    if ( pWert != null )
    {
      if ( pStrWert1 != null )
      {
        if ( pStrWert1.equalsIgnoreCase( pWert ) )
        {
          return pStrValue1;
        }
      }

      if ( pStrWert2 != null )
      {
        if ( pStrWert2.equalsIgnoreCase( pWert ) )
        {
          return pStrValue2;
        }
      }
    }

    return pElseStrValue;
  }

  public static String convertWert( String pWert, String pStrWert1, String pStrValue1, String pStrWert2, String pStrValue2, String pStrWert3, String pStrValue3, String pElseStrValue )
  {
    if ( pWert != null )
    {
      if ( pStrWert1 != null )
      {
        if ( pStrWert1.equalsIgnoreCase( pWert ) )
        {
          return pStrValue1;
        }
      }

      if ( pStrWert2 != null )
      {
        if ( pStrWert2.equalsIgnoreCase( pWert ) )
        {
          return pStrValue2;
        }
      }

      if ( pStrWert3 != null )
      {
        if ( pStrWert3.equalsIgnoreCase( pWert ) )
        {
          return pStrValue3;
        }
      }
    }

    return pElseStrValue;
  }

  public static String convertWert( String pWert, String pStrWert1, String pStrValue1, String pStrWert2, String pStrValue2, String pStrWert3, String pStrValue3, String pStrWert4, String pStrValue4, String pElseStrValue )
  {
    if ( pWert != null )
    {
      if ( pStrWert1 != null )
      {
        if ( pStrWert1.equalsIgnoreCase( pWert ) )
        {
          return pStrValue1;
        }
      }

      if ( pStrWert2 != null )
      {
        if ( pStrWert2.equalsIgnoreCase( pWert ) )
        {
          return pStrValue2;
        }
      }

      if ( pStrWert3 != null )
      {
        if ( pStrWert3.equalsIgnoreCase( pWert ) )
        {
          return pStrValue3;
        }
      }

      if ( pStrWert4 != null )
      {
        if ( pStrWert4.equalsIgnoreCase( pWert ) )
        {
          return pStrValue4;
        }
      }
    }

    return pElseStrValue;
  }

  /**
   * <pre>
   * http://de.wikipedia.org/wiki/ROT13
   * 
   * ROT13 (engl. rotate by 13 places, zu Deutsch in etwa "rotiere um 13 Stellen") 
   * ROT13 ist ein Verschiebechiffre, mit der auf einfache Weise Texte verschluesselt werden koennen. 
   * Dies geschieht durch Ersetzung von Buchstaben. Bei ROT13 im Speziellen wird jeder Buchstabe 
   * des lateinischen Alphabets durch den im Alphabet um 13 Stellen davor bzw. dahinter liegenden 
   * Buchstaben ersetzt.
   * 
   * ROT13 ist nicht zur sicheren Verschluesselung gedacht. Vielmehr dient ROT13 dazu, einen Text 
   * unlesbar zu machen, also zu verschleiern, so dass eine Handlung des Lesers erforderlich ist,
   * um den urspruenglichen Text lesen zu koennen. 
   * 
   * ROT13 selbst benutzt nur die 26 Buchstaben des lateinischen Alphabets, alle anderen 
   * Zeichen der Eingabe werden unbehandelt uebernommen.
   * 
   * FkString.rot13( "Das ist ein Test" ) = "Qnf vfg rva Grfg" 
   * FkString.rot13( "Qnf vfg rva Grfg" ) = "Das ist ein Test" 
   * 
   * </pre>
   * 
   * @param pString der zu ver- oder entschluesselnde String
   * @return den ent- oder verschluesselten String der Eingabe, Leerstring wenn die Eingabe null ist
   */
  public static String rot13( String pString )
  {
    String ergebnis = "";

    if ( pString != null )
    {
      int akt_index = 0;

      while ( akt_index < pString.length() )
      {
        char aktuelles_zeichen = pString.charAt( akt_index );

        if ( aktuelles_zeichen >= 'a' && aktuelles_zeichen <= 'm' ) aktuelles_zeichen += 13;
        else if ( aktuelles_zeichen >= 'n' && aktuelles_zeichen <= 'z' ) aktuelles_zeichen -= 13;
        else if ( aktuelles_zeichen >= 'A' && aktuelles_zeichen <= 'M' ) aktuelles_zeichen += 13;
        else if ( aktuelles_zeichen >= 'N' && aktuelles_zeichen <= 'Z' ) aktuelles_zeichen -= 13;

        ergebnis = ergebnis + aktuelles_zeichen;

        akt_index++;
      }
    }

    return ergebnis;
  }

  /**
   * <pre>
   * Verschachtelt die beiden Eingabestrings Zeichenweise miteinander. 
   * 
   * FkString.getStringX( "ABCDEF", "123456" ) = "A1B2C3D4E5F6" 
   * 
   * FkString.getStringX(     null, "123456" ) = "123456"        => String 1 ist null
   * FkString.getStringX( "ABCDEF",     null ) = "ABCDEF"        => String 2 ist null
   * FkString.getStringX(     null,     null ) = ""              => beide Strings sind null 
   * FkString.getStringX(       "",       "" ) = ""              => beide Strings Leerstring
   *  
   * FkString.getStringX( "ABCDEFGHIJKLMNOP", "123456" ) = "A1B2C3D4E5F6GHIJKLMNOP" => String 1 laenger als String 2 
   * </pre>
   * 
   * @param pString1 der erste String
   * @param pString2 der zweite String 
   * @return einen String mit zeichenweiser Schachtelung von String 1 und String 2
   */
  public static String getStringX( String pString1, String pString2 )
  {
    String str_ergebnis = "";

    /*
     * Variableninitialisierung
     */
    int laenge_string_1 = -1;
    int index_eingabe_1 = -1;

    int laenge_string_2 = -1;
    int index_eingabe_2 = -1;

    if ( pString1 != null )
    {
      laenge_string_1 = pString1.length();

      index_eingabe_1 = 0;
    }

    if ( pString2 != null )
    {
      laenge_string_2 = pString2.length();

      index_eingabe_2 = 0;
    }

    /*
     * Die Schleife wird solange ausgefuehrt wie...
     * ...die Index-Variablen (=Leseprozess) noch groesser gleich 0 sind (=der String noch nicht bis zu ende kopiert ist)
     * ...die Laengenvariablen einen Wert von groesser 0 haben (=Eingabe ist ungleich null)
     */
    while ( ( ( index_eingabe_1 >= 0 ) || ( index_eingabe_2 >= 0 ) ) && ( ( laenge_string_1 > 0 ) || ( laenge_string_2 > 0 ) ) )
    {
      /*
       * Zeichen String 1
       * Solange der Index groesser gleich 0 ist, ist der Leseprozess noch nicht
       * am Stringende angekommen. Das Zeichen an der Index-Position wird dem
       * Ergebnisstring hinzugefuegt. 
       * 
       * Der Indexzaehler wird um 1 erhoeht. Erreicht der Zaehler die Laenge des 
       * Eingabestrings wird der Index-Zaehler auf -1 gestellt. Somit wird im 
       * naechsten Lauf der String 1 nicht weiter mitaufgenommen. 
       * (Anmerkung: Stringposition beginnt bei 0)
       */
      if ( index_eingabe_1 >= 0 )
      {
        str_ergebnis += pString1.charAt( index_eingabe_1 );

        index_eingabe_1++;

        if ( index_eingabe_1 >= laenge_string_1 )
        {
          index_eingabe_1 = -1;
        }
      }

      /*
       * Zeichen String 2
       * ... analog zu der Stringverarbeitung fuer Parameter 1 nur mit anderen Zaehlvariablen.
       */
      if ( index_eingabe_2 >= 0 )
      {
        str_ergebnis += pString2.charAt( index_eingabe_2 );

        index_eingabe_2++;

        if ( index_eingabe_2 >= laenge_string_2 )
        {
          index_eingabe_2 = -1;
        }
      }
    }

    return str_ergebnis;
  }

  /**
   * <pre>
   * Wandelt den String in Grossbuchstaben, uebersetzt dabei die deutschen Umlaute und entfernt alle Sonderzeichen.
   * Zahlen bleiben erhalten.  
   *  
   * FkString.getStringDbVergleich( "Start Test oeaeueAeUeOe ss !�$%&/()=? ); Ende"   ) = "STARTTESTOEAEUEAEUEOESSENDE"
   * FkString.getStringDbVergleich( "Gross-Neumarkt-Str. 38"                          ) = "GROSSNEUMARKTSTR38"
   * 
   * Sonderzeichen werden nicht uebernommen:
   * 
   * FkString.getStringDbVergleich( " _?!"�$%&/()<>[]{}=*'/*-+:;,.#\/\n"       ) = ""
   * FkString.getStringDbVergleich( ""                                         ) = ""
   * FkString.getStringDbVergleich( null                                       ) = ""
   * </pre>
   * 
   * @param pString die umzuwandelnde Eingabezeichenfolge
   * @return die gewandelte Zeichenfolge (bei pString == null ein Leerstring) 
   */
  public static String getStringDbVergleich( String pString )
  {
    /*
     * Parameterpruefung:
     * pString == null       = Rueckgabe eines Leerstrings
     * pString.length() == 0 = Rueckgabe eines Leerstrings
     */
    if ( pString == null )
    {
      return "";
    }

    if ( pString.trim().length() == 0 )
    {
      return "";
    }

    StringBuffer str_ergebnis = new StringBuffer();

    String gueltige_zeichen = "enirstaudhgolcmfbkVvwzpSDAERBGMIPKFUNWLHTCZJyxjOYXqQ1234567890";

    char[] char_array = gueltige_zeichen.toCharArray();

    int char_array_anzahl = char_array.length;

    int char_array_zaehler = 0;

    char aktuelles_zeichen = '?';

    int anzahl_zeichen = pString.length();

    int akt_index = 0;

    /* 
     * Verarbeitungsschleife ueber die gesamte Laenge der Eingabezeichenfolge
     */
    while ( akt_index < anzahl_zeichen )
    {
      /*
       * Aktuelles Zeichen an der Leseposition lesen
       */
      aktuelles_zeichen = pString.charAt( akt_index );

      /*
       * Pruefung: Gueltige Zeichen mit Umwandlung
       * Es werden hier die Zeichen der deutschen Umlaute abgefragt. Liegt 
       * ein solches Zeichen vor, wird dem Ergebnisstring gleich die Variante
       * in Grossbuchstaben hinzugefuegt. 
       */
      switch ( aktuelles_zeichen )
      {
        case 0x00e4 :
          str_ergebnis.append( "AE" );
          break;

        case 0x00fc :
          str_ergebnis.append( "UE" );
          break;

        case 0x00f6 :
          str_ergebnis.append( "OE" );
          break;

        case 0x00d6 :
          str_ergebnis.append( "OE" );
          break;

        case 0x00c4 :
          str_ergebnis.append( "AE" );
          break;

        case 0x00dc :
          str_ergebnis.append( "UE" );
          break;

        case 0x00df :
          str_ergebnis.append( "SS" );
          break;

        default :

          /*
           * Pruefung: Gueltiges Zeichen ohne Umwandlung
           * Im String "gueltige_zeichen" sind alle Zeichen enthalten, welche ohne 
           * eine Umwandlung uebernommen werden koennen. Liegt ein solches Zeichen 
           * vor, wird dieses lediglich per Funktion "toUpperCase" dem Ergebnisstring 
           * hinzugefuegt. 
           */
          char_array_zaehler = 0;

          while ( char_array_zaehler < char_array_anzahl )
          {
            if ( char_array[ char_array_zaehler ] == aktuelles_zeichen )
            {
              str_ergebnis.append( ( "" + aktuelles_zeichen ).toUpperCase() );

              char_array_zaehler = char_array_anzahl;
            }

            char_array_zaehler++;
          }

          break;
      }

      /*
       * Leseposition um eine Position weiterstellen
       */
      akt_index++;
    }

    /*
     * Rueckgabe des aufgebauten Ergebniszeichenfolge
     */
    return str_ergebnis.toString();
  }


  /**
   * Aus der Eingabe werden nur die Buchstaben, Zahlen und Sonderzeichen extrahiert. 
   * (Sonderfunktion fuer hartnaeckige Zeichensatzprobleme)
   * 
   * @param pEingabe die Eingabe als String
   * @return einen String mit nur den definierten Rueckgabezeichen
   */
  public static String getStringX( String pEingabe )
  {
    String str_ergebnis = "";

    int laenge_eingabe = pEingabe.length();

    int akt_index = 0;

    while ( akt_index < laenge_eingabe )
    {
      char aktuelles_zeichen = pEingabe.charAt( akt_index );

      if ( pos( STR_ABC_KLEIN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ZAHLEN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ABC_GROSS, aktuelles_zeichen, 0 ) >= 0 | pos( STR_SONDERZEICHEN, aktuelles_zeichen, 0 ) >= 0 )
      {
        str_ergebnis = str_ergebnis + aktuelles_zeichen;
      }

      akt_index++;
    }

    return str_ergebnis;
  }

  /**
   * Aus der Eingabe werden nur die Buchstaben, Zahlen und Sonderzeichen extrahiert. 
   * (Sonderfunktion fuer hartnaeckige Zeichensatzprobleme)
   * 
   * @param pEingabe die Eingabe als byte-Array
   * @return einen String mit nur den definierten Rueckgabezeichen
   */
  public static String getStringX( byte[] pEingabe )
  {
    String str_ergebnis = "";

    int laenge_eingabe = pEingabe.length;

    int akt_index = 0;

    while ( akt_index < laenge_eingabe )
    {
      char aktuelles_zeichen = (char) pEingabe[ akt_index ];

      if ( pos( STR_ABC_KLEIN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ZAHLEN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ABC_GROSS, aktuelles_zeichen, 0 ) >= 0 | pos( STR_SONDERZEICHEN, aktuelles_zeichen, 0 ) >= 0 )
      {
        str_ergebnis = str_ergebnis + aktuelles_zeichen;
      }

      akt_index++;
    }

    return str_ergebnis;
  }

  /**
   * @param pEingabe die zu behandelnde Eingabe als Byte-string
   * @param pWeitereZeichen Zeichen, welche auch aus der Eingabe in das Ergebnis uebernommen werden (zusaetzlich zu ABC, Zahlen und Sonderzeichen)
   * @return einen String mit nur den definierten Rueckgabezeichen und den zusaetzlich angegebenen Zeichen
   */
  public static String getStringX( byte[] pEingabe, String pWeitereZeichen )
  {
    String ergebnis = "";

    int laenge_eingabe = pEingabe.length;

    int akt_index = 0;

    while ( akt_index < laenge_eingabe )
    {
      char aktuelles_zeichen = (char) pEingabe[ akt_index ];

      if ( pos( STR_ABC_KLEIN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ZAHLEN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ABC_GROSS, aktuelles_zeichen, 0 ) >= 0 | pos( STR_SONDERZEICHEN, aktuelles_zeichen, 0 ) >= 0 | pos( pWeitereZeichen, aktuelles_zeichen, 0 ) >= 0 )
      {
        ergebnis = ergebnis + aktuelles_zeichen;
      }

      akt_index++;
    }

    return ergebnis;
  }

  /**
   * @param pEingabe die Eingabe als String 
   * @return einen String mit einer Zeilenweisen Auflistung von Zeichen, Hex- und Dezimalwert.
   */
  public static String getCharValues( String pEingabe )
  {
    String ergebnis = "";

    int laenge_eingabe = pEingabe.length();

    laenge_eingabe = laenge_eingabe > 20 ? 20 : laenge_eingabe;

    int akt_index = 0;

    while ( akt_index < laenge_eingabe )
    {
      char aktuelles_zeichen = pEingabe.charAt( akt_index );

      ergebnis = ergebnis + "\nZeichen " + akt_index + " = " + Integer.toHexString( aktuelles_zeichen ) + " = \'" + aktuelles_zeichen + "\'";

      akt_index++;
    }

    return ergebnis;
  }

  /**
   * @param pString der zu pruefende String
   * @return TRUE wenn es sich um einen boolschen Wert handelt, sonst false
   */
  public static boolean istStringBooleanAngabe( String pString )
  {
    if ( STR_WERT_BOOLEAN_TRUE.indexOf( "," + pString.toLowerCase() ) >= 0 )
    {
      return true;
    }

    if ( STR_WERT_BOOLEAN_FALSE.indexOf( "," + pString.toLowerCase() ) >= 0 )
    {
      return true; // Uebereinstimmung gefunden --> Eingabe ist ein Boolscher-Wert --> TRUE
    }

    if ( STR_WERTE_BOOLEAN_TRUE.indexOf( "," + pString.toLowerCase() ) < 0 )
    {
      if ( STR_WERTE_BOOLEAN_FALSE.indexOf( "," + pString.toLowerCase() ) < 0 )
      {
        return false; // Keine Uebereinstimmung gefunden --> Eingabe kein Boolscher-Wert --> FALSE
      }
    }

    /*
     * Ziel der Pruefungen ist es zu belegen, dass die Eingabe KEIN
     * boolscher Wert ist. 
     */
    return true; // Uebereinstimmung mit Vorgaben wurde gefunden --> Eingabe ist ein Boolscher-WErt --> true
  }

  /**
   * <pre>
   * Fuehrt im Endeffekt <code>pString.indexOf( pSuchString, 0 )</code> aus.
   * Behandelt aber Pruefung auf eine Null-Referenz im Parameter pString
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pSuchString die zu suchende Zeichenfolge
   * @return die Position des Auftretens, oder -1 sofern der Suchstring nicht vorhanden oder pString = null ist
   */
  public static int pos( String pString, String pSuchString )
  {
    if ( pString == null )
    {
      return -1;
    }

    return pString.indexOf( pSuchString, 0 );
  }

  /**
   * <pre>
   * Fuehrt im Endeffekt <code>pString.indexOf( pSuchString, pAbPosition )</code> aus.
   * Behandelt aber Pruefung auf eine Null-Referenz im Parameter pString
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pSuchString die zu suchende Zeichenfolge
   * @param pAbPosition die Position ab welcher die Suche beginnen soll
   * @return die Position des Auftretens, oder -1 sofern der Suchstring nicht vorhanden oder pString = null ist
   */
  public static int pos( String pString, String pSuchString, int pAbPosition )
  {
    if ( pString == null )
    {
      return -1;
    }

    return pString.indexOf( pSuchString, pAbPosition );
  }

  /**
   * <pre>
   * Fuehrt im Endeffekt <code>pString.indexOf( pSuchString, pAbPosition )</code> aus.
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pSuchString das zu suchende Zeichen als char
   * @param pAbPosition die Position ab welcher die Suche beginnen soll
   * @return die Position des Auftretens, oder -1 sofern der Suchstring nicht vorhanden oder pString = null ist
   */
  public static int pos( String pString, char pSuchString, int pAbPosition )
  {
    if ( pString == null )
    {
      return -1;
    }

    return pString.indexOf( "" + pSuchString, pAbPosition );
  }

  /** Instanz fuer den Zufallszahlengenerator */
  private static Random random_instanz = new Random();

  /**
   * Rueckgabe einer zufaelligen Zeichenfolge in der angegebenen Laenge
   * 
   * @param pLaenge die Laenge des Rueckgabestrings
   * @return einen String in der angegebenen Laenge mit zufaelligen Zeichen
   */
  public static String getRandomString( int pLaenge )
  {
    /*
     * Ist der Parameterwert aus "pLaenge" kleiner gleich 0, wird 
     * ein Leerstring zurueckgegeben.
     */
    if ( pLaenge <= 0 )
    {
      return "";
    }

    /*
     * Deklaration der Grundmenge
     */
    String grundmenge_str = "EHOYABNAFRTJEPDBWIAAEEJIAOUEODIVLAROGUUOYUELUOEUOMIAXNOTFESIMWEAIUEUAOKAUZPOVHUQIKUQEUOAAZAIUIIGEXCCOIISZ";

    /*
     * Stellenanzahl der Grundmenge ermitteln. 
     * Das ist die Obergrenze fuer die zu erstellenden Zufallszahlen.
     */
    int grundmenge_laenge = grundmenge_str.length();

    /*
     * Stringbuffer fuer das Ergebnis erstellen
     */
    StringBuffer str_ergebnis = new StringBuffer();

    int zaehler_ergebnis = 1;

    /*
     * While-Schleife bis zur Laenge aus "pLaenge"
     */
    while ( zaehler_ergebnis <= pLaenge )
    {
      /*
       * Je Durchlauf einen zufaelligen Index erstellen,
       * damit aus der Grundmenge, das dort stehende 
       * Zeichen dem Ergebnis hinzufuegen. 
       */
      str_ergebnis.append( grundmenge_str.charAt( random_instanz.nextInt( grundmenge_laenge ) ) );

      /*
       * Zaehler erhoehen.
       */
      zaehler_ergebnis++;
    }

    return str_ergebnis.toString();
  }

  /**
   * Rueckgabe einer zufaelligen Zeichenfolge in der angegebenen Laenge
   * 
   * @param pLaenge die Laenge des Rueckgabestrings
   * @param pGrundmenge die Grundmenge 
   * @return einen String in der angegebenen Laenge mit zufaelligen Zeichen
   */
  public static String getRandomString( int pLaenge, String pStrBestehend, String pGrundmenge )
  {
    if ( pLaenge <= 0 )
    {
      return "";
    }

    if ( pGrundmenge == null )
    {
      pGrundmenge = "EHOYABNAFRTJEPDBWIAAEEJIAOUEODIVLAROGUUOYUELUOEUOMIAXNOTFESIMWEAIUEUAOKAUZPOVHUQIKUQEUOAAZAIUIIGEXCCOIISZ";
    }

    if ( pStrBestehend != null )
    {
      if ( pStrBestehend.length() >= pLaenge )
      {
        return pStrBestehend.substring( 0, pLaenge );
      }
    }

    int random_zahl = pGrundmenge.length();

    /*
     * Stringbuffer fuer das Ergebnis erstellen
     */
    StringBuffer str_ergebnis = new StringBuffer();

    int zaehler_ergebnis = 1;

    if ( pStrBestehend != null )
    {
      zaehler_ergebnis = pStrBestehend.length();

      str_ergebnis.append( pStrBestehend );
    }

    while ( zaehler_ergebnis <= pLaenge )
    {
      /*
       * Je Durchlauf einen zufaelligen Index erstellen,
       * damit aus der Grundmenge, das dort stehende 
       * Zeichen dem Ergebnis hinzufuegen. 
       */
      str_ergebnis.append( pGrundmenge.charAt( random_instanz.nextInt( random_zahl ) ) );

      /*
       * Zaehler erhoehen.
       */
      zaehler_ergebnis++;
    }

    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * Stellt die Zeichen der Eingabe zufaellig um.
   * 
   * Ist "pString" gleich null, wird null zurueckgegeben.
   * 
   * Ist die Laenge von "pString" gleich 1, wird "pString" zurueckgegeben.
   * 
   * Ist "pAnzahlDurchlaeufe" kleiner 1, wird ein Vertauschungsdurchlauf gemacht.
   * 
   * Eingabe: 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
   * Ausgabe: Z9j7LmPHIKA58JdgWfaFv2SCklGDYpstRXq0o1u6iVcNnr4eByzhMUEb3TOwxQ
   * </pre>
   * 
   * @param pAnzahlDurchlaeufe die Anzahl der Vertauschungsdurchlaeufe
   * @param pString die umzustellende Eingabezeichenfolge
   * @return die Eingabezeichenfolge mit zufaelliger Umstellung der Zeichenpositionen
   */
  public static String getRandomUmgestellt( int pAnzahlDurchlaeufe, String pString )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist der Parameter "pString" gleich "null" gibt es keinen String 
     * zum Umstellen. Der Aufrufder bekommt "null" zurueck.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Pruefung: Laenge gleich 1 Zeichen ?
     *
     * Ist die Laenge von "pString" gleich 1, wird keine Umstellung gemacht.
     * Es gibt keine sinnvolle Umstellung. Der Aufrufer bekommt "pString" zurueck.
     */
    if ( pString.length() == 1 )
    {
      return pString;
    }

    /*
     * Keine negativen Durchlaeufe und mindestens ein Durchlauf
     */
    if ( pAnzahlDurchlaeufe < 1 )
    {
      pAnzahlDurchlaeufe = 1;
    }

    /*
     * Die vertauschungen werden in einem Array durchgefuehrt
     */
    char[] array_ergebnis = pString.toCharArray();

    /*
     * Hilfsvariable fuer den Tausch
     */
    char temp_char = ' ';

    int anzahl_tausch_operationen = pString.length();

    int random_zahl_grenze = pString.length();

    int tausch_position_1 = 0;

    int tausch_position_2 = 0;

    /*
     * Verhinderung einer Endlosschleife
     */
    int zaehler = 0;

    /*
     * Der Zaehler fuer die Anzahl der Durchlaeufe wird auf 1 gestellt.
     */
    int nr_durchlauf = 1;

    /*
     * While-Schleife fuer die Vertauschungslaeufe.
     * Die While-Schleife laeuft solange, wie der Zaehler fuer die  Durchlaeufe noch 
     * nicht die Anzahl aus dem Parameter "pAnzahlDurchlaeufe" erreicht hat. 
     */
    while ( nr_durchlauf <= pAnzahlDurchlaeufe )
    {
      /*
       * Jeder Tauschdurchlauf startet beim ersten Zeichen des Strings.
       * Die Tausposition 1 wird auf 0 gestellt.
       */
      tausch_position_1 = 1;

      /*
       * Pruefung: Stringlaenge gleich 2 Zeichen ?
       *
       * Hat der zu vertauschende String nur 2 Zeichen, wird die
       * Tauschposition 2 auf das zweite Zeichen eingestellt und
       * die Positionen werden vertauscht.
       *
       * Sind mehr Zeichen im zu vertauschenden String vorhanden,
       * wird eine While-Schleife gestartet.
       */
      if ( anzahl_tausch_operationen == 2 )
      {
        tausch_position_2 = 1;

        temp_char = array_ergebnis[ tausch_position_1 ];

        array_ergebnis[ tausch_position_1 ] = array_ergebnis[ tausch_position_2 ];

        array_ergebnis[ tausch_position_2 ] = temp_char;

        /*
         * Per Zufall wird bestimmt, ob es noch einen weiteren Tausch-Durchlauf geben soll.
         *
         * Es wird eine Zufallszahl zwischen 0 und 100 erstellt.
         * Ist die Zufallszahl groesser 50, gibt es keinen zweiten Durchlauf
         *
         * Soll es keinen weiteren Durchlauf mehr geben, wird der Durchlaufzaehler auf
         * die Maximalanzahl der Durchlaeufe gesetzt.
         */
        if ( random_instanz.nextInt( 100 ) > 50 )
        {
          nr_durchlauf = pAnzahlDurchlaeufe + 1;
        }
      }
      else
      {
        /*
         * While-Schleife fuer jedes Zeichen der Eingabe.
         * Jede Position des Eingabestrings wird einmal vertauscht. 
         */
        while ( tausch_position_1 <= anzahl_tausch_operationen )
        {
          try
          {
            /*
             * Position 1 der Vertauschungen ist der Index der inneren While-Schleife
             * 
             * Position 2 der Vertauschungen wird per Zufall gewaehlt
             */
            tausch_position_2 = random_instanz.nextInt( random_zahl_grenze );

            zaehler = 0;

            /*
             * Mit einer dritten While-Schleife wird verhindert, dass die 
             * beiden Tauschpositionen gleich sind. 
             * 
             * In der While-Schleife wird die Tauschpositon 2 neu vergeben,
             * sollte diese gleich der ersten Tauschpositon sein. 
             * 
             * Es wird 10 mal versucht, unterschiedliche Tauschpositionen zu beommen
             */
            while ( ( tausch_position_2 == tausch_position_1 ) && ( zaehler < 10 ) )
            {
              tausch_position_2 = random_instanz.nextInt( random_zahl_grenze );

              zaehler++;
            }

            /*
             * Pruefung: Tauschpositionen unterschiedlich ?
             * 
             * Sind die Tauschpositionen unterschiedlich, wird die Vertauschung gemacht.
             * 
             * Sind die Tauschpoistionen gleich, wird keine Vertauschung gemacht.
             */
            if ( tausch_position_2 != tausch_position_1 )
            {
              temp_char = array_ergebnis[ tausch_position_1 ];

              array_ergebnis[ tausch_position_1 ] = array_ergebnis[ tausch_position_2 ];

              array_ergebnis[ tausch_position_2 ] = temp_char;
            }
          }
          catch ( Exception err_inst )
          {
            // nicht vorhandener Index 
          }

          /*
           * Am Ende der zweiten While-Schleife wird die Tauschposition 1 um 
           * eine Position weitergestellt.  
           */
          tausch_position_1++;
        }
      }

      /*
       * Am Ende der ersten While-Schleife wird der Durchlaufzaehler um 
       * eine Position weitergestellt.  
       */
      nr_durchlauf++;
    }

    /*
     * Am Ende der Funktion wird dem Aufrufer der umgestellte String zurueckgegeben. 
     */
    return new String( array_ergebnis );
  }

  /**
   * <pre>
   * Verfremdet die Eingabe mit dem Muster.
   * 
   * Die Variable pStringMuster gibt die Reihenfolge der Zeichen im Ergebnis wieder.
   * Es wird immer wieder das Stringmuster wiederholt. 
   * 
   * Die Variable pString gibt den "Satzbau" vor (Gross/Kleinschreibung/Satzzeichen).
   * 
   * FkString.getStringMuster( "Die Variable pString gibt den "Satzbau" vor (Gross/Kleinschreibung)", "AbcDef" ) = "Abc Defabcde fAbcdef abcd efa "Bcdefab" cde (Fabc/Defabcdefabcdef)" 
   * FkString.getStringMuster( "Die Variable pString gibt den "Satzbau" vor (Gross/Kleinschreibung)", "M"      ) = "Mmm Mmmmmmmm mMmmmmm mmmm mmm "Mmmmmmm" mmm (Mmmm/Mmmmmmmmmmmmmmm)"
   * </pre>
   * 
   * @param pString der Vorgabestring fuer den Satzbau
   * @param pStringMuster die Zeichenfolge fuer den Ergebnisstring 
   * @return einen String mit der Zeichenfolge aus pStringMuster, mit dem Satzbau aus pString
   */
  public static String getStringMuster( String pString, String pStringMuster )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist der Parameter "pString" gleich "null" gibt es keinen Eingabestring. 
     * Der Aufrufder bekommt "null" zurueck.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Ist der Parameter "pString" ein Leerstring, bekommt der Aufrufder einen 
     * Leerstring zurueck.
     */
    if ( pString.length() == 0 )
    {
      return pString;
    }

    if ( pStringMuster == null )
    {
      return null;
    }

    int anzahl_zeichen_muster = pStringMuster.length() - 1;

    if ( anzahl_zeichen_muster < 0 )
    {
      return null;
    }

    /*
     * Aktuelle Zeichen aus der Eingabezeichenfolge
     * Startwert ist hier nur wegen der Initialisierung der Variable vorhanden.
     */
    String akt_zeichen_eingabe = "";
    String akt_zeichen_muster = "";
    String sonder_zeichen = " :.;,_?!\"�$%&()<>[]{}=*'*-+#\\/@|~^�";
    String klein_buchstaben = "enrstliatdhucgmobwfkzpvssjyxqoeueae";

    int zaehler_muster = 0;
    int zaehler_eingabe = 0;

    StringBuffer str_ergebnis = new StringBuffer();

    while ( zaehler_eingabe < pString.length() )
    {
      akt_zeichen_eingabe = "" + pString.charAt( zaehler_eingabe );

      if ( sonder_zeichen.indexOf( akt_zeichen_eingabe ) >= 0 )
      {
        str_ergebnis.append( akt_zeichen_eingabe ); // Sonderzeichen immer uebernehmen          
      }
      else
      {
        akt_zeichen_muster = "" + pStringMuster.charAt( zaehler_muster );

        if ( zaehler_muster < anzahl_zeichen_muster )
        {
          zaehler_muster++;
        }
        else
        {
          zaehler_muster = 0;
        }

        if ( klein_buchstaben.indexOf( akt_zeichen_eingabe ) >= 0 )
        {
          str_ergebnis.append( akt_zeichen_muster.toLowerCase() ); // Kleinbuchstabe      
        }
        else
        {
          str_ergebnis.append( akt_zeichen_muster.toUpperCase() ); // Grossbuchstabe          
        }
      }

      zaehler_eingabe++;
    }

    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * Ist pString gleich der Zeichenkette "null" (nicht mit null-Pointer verwechseln)
   * wird dem Aufrufer ein null-Pointer zurueckgegeben.
   * 
   * Ansonsten bekommt der Aufrufer pString zurueck.
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @return null wenn pString mit null anfaengt
   */
  public static String getStringNull( String pString )
  {
    if ( startetAuf( pString, "null" ) )
    {
      return null;
    }

    return pString;
  }

  /**
   * @param pString der zu pruefende String 
   * @return einen Leerstring oder den getrimmten Eingabestring 
   */
  public static String getStringTrim( String pString )
  {
    if ( pString == null )
    {
      return "";
    }

    return pString.trim();
  }

  /**
   * <pre>
   * Schneidet die Anzahl-Stellen von dem uebergebenen String ab und gibt diesen zurueck.
   * 
   * Uebersteigt die Anazhl der abzuschneidenden Stellen die Stringlaenge, wird der 
   * Quellstring insgesamt zurueckgegeben.
   * 
   * Ist die Anzahl der abzuschneidenden Stellen negativ oder 0, wird ein Leerstring zurueckgegeben.
   * 
   * FkString.right( "ABC.DEF.GHI.JKL",  7 ) = "GHI.JKL"
   * FkString.right( "ABC.DEF.GHI.JKL", 20 ) = "ABC.DEF.GHI.JKL" = Anzahl Stellen uebersteigt Stringlaenge
   * FkString.right( "ABC.DEF.GHI.JKL",  0 ) = ""                = 0 Stellen abschneiden = Leerstring
   * FkString.right( "ABC.DEF.GHI.JKL", -7 ) = ""                = negative Anzahl       = Leerstring
   * 
   * </pre>
   * 
   * @param pString der Quellstring
   * @param pAnzahlStellen die Anzahl der von rechts abzuschneidenden Stellen
   * @return der ermittelte Teilstring
   */
  public static String right( String pString, int pAnzahlStellen )
  {
    if ( ( pString != null ) && ( pAnzahlStellen > 0 ) )
    {
      /*
       * Die Ab-Position ist die Laenge des Eingabestrings, abzueglich der von 
       * rechts abzuschneidenden Stellen. 
       * 
       * Die Ab-Postion darf aber nicht negativ werden. 
       * Die minimale Ab-Position ist der Stringanfang (Position 0).
       * 
       * Die Bis-Position ist die Laenge des Eingabestrings.
       */
      return pString.substring( Math.max( 0, pString.length() - pAnzahlStellen ), pString.length() );
    }

    /*
     * "pString" nicht gesetzt oder pAnzahlStellen < 0
     */
    return LEERSTRING;
  }

  /**
   * <pre>
   * Schneidet die Anzahl der uebergebenen Stellen von der uebegebenen Zahl ab.
   * 
   * FkString.right( 19680830,  4 ) = "0830"
   * FkString.right(        0,  4 ) =    "0" 
   * FkString.right( 19680830, -4 ) =     "" = negative Anzahl von Stellen = Leerstring 
   * </pre>
   * 
   * @param pIntZahl die Integerzahl
   * @param pAnzahlStellen die Anzahl der von rechts abzuschneidenden Stellen
   * @return den sich ergebenden String, Leerstring wenn die Anzahl der Stellen negativ ist
   */
  public static String right( int pIntZahl, int pAnzahlStellen )
  {
    /*
     * Pruefung: Anzahl Stellen negativ?
     * Ist die Anzahl der Stellen negativ, wird ein Leerstring zurueckgegeben
     */
    if ( pAnzahlStellen <= 0 )
    {
      return LEERSTRING;
    }

    /*
     * Die Zahl in einen String umwandeln
     */
    String temp_string = "" + pIntZahl;

    /*
     * Pruefung: Teilstring zurueckgeben?
     * Es wird ein Teilstring zurueckgegeben, wenn die Laenge des Temp-Strings
     * die Laenge der Stellenanzahl ueberschreitet.
     *
     * Ab-Position  = Laenge des Temp-Strings abzueglich der Stellenanzahl
     * Bis-Position = Laenge des Temp-Strings 
     */
    if ( pAnzahlStellen < temp_string.length() )
    {
      return temp_string.substring( temp_string.length() - pAnzahlStellen, temp_string.length() );
    }

    /*
     * Kein Teilstring
     * Es wird der komplette Temp-String zurueckgegeben, wenn die Stellenanzahl
     * groesser als die Laenge des Temp-Strings ist.
     * 
     * z.B. Es sollen 10 Zeichen (=pAnzahlStellen) zurueckgegeben werden, aber 
     *      der Temp-String ist nur 5 Zeichen lang.
     */
    return temp_string;
  }

  /**
   * <pre>
   * Schneidet die Anzahl der uebergebenen Stellen von der uebegebenen Zahl ab.
   * 
   * FkString.right( 19680830l,  20 ) = "19680830"
   * FkString.right( 19680830l,   4 ) = "0830"
   * FkString.right(        0l,   4 ) = "0" 
   * FkString.right( 19680830l,  -4 ) = "" = negative Anzahl von Stellen = Leerstring
   * 
   * FkString.right( -19680830l,  8 ) =  "19680830" = "-" wurde gekappt
   * FkString.right( -19680830l,  9 ) = "-19680830"
   * 
   * </pre>
   * 
   * @param pLongZahl eine Zahl als long
   * @param pAnzahlStellen die Anzahl der von rechts abzuschneidenden Stellen
   * @return den sich ergebenden String, Leerstring wenn die Anzahl der Stellen negativ ist
   */
  public static String right( long pLongZahl, int pAnzahlStellen )
  {
    /*
     * Pruefung: Anzahl Stellen negativ?
     * Ist die Anzahl der Stellen negativ, wird ein Leerstring zurueckgegeben
     */
    if ( pAnzahlStellen <= 0 )
    {
      return LEERSTRING;
    }

    /*
     * Die Zahl in einen String umwandeln
     */
    String temp_string = "" + pLongZahl;

    /*
     * Pruefung: Teilstring zurueckgeben?
     * Es wird ein Teilstring zurueckgegeben, wenn die Laenge des Temp-Strings
     * die Laenge der Stellenanzahl ueberschreitet.
     *
     * Ab-Position  = Laenge des Temp-Strings abzueglich der Stellenanzahl
     * Bis-Position = Laenge des Temp-Strings 
     */
    if ( temp_string.length() > pAnzahlStellen )
    {
      return temp_string.substring( temp_string.length() - pAnzahlStellen, temp_string.length() );
    }

    /*
     * Kein Teilstring
     * Es wird der komplette Temp-String zurueckgegeben, wenn die Stellenanzahl
     * groesser als die Laenge des Temp-Strings ist.
     * 
     * z.B. Es sollen 10 Zeichen (=pAnzahlStellen) zurueckgegeben werden, aber 
     *      der Temp-String ist nur 5 Zeichen lang.
     */
    return temp_string;
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
  public static String left( String pString, int pAnzahlStellen )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist der Parameter "pString" gleich "null" gibt es keinen String. 
     * Der Aufrufder bekommt einen Leerstring zurueck
     */
    if ( pString == null )
    {
      return LEERSTRING;
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
      return LEERSTRING;
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
   * Schneidet von der uebergebenen Zahl die Anzahl der Stellen von links ab. 
   * 
   * FkString.left(  "19680830",  4 ) =  "1968"   
   * FkString.left(         "0",  4 ) =  "0" 
   * FkString.left( "-19680830",  4 ) =  "-196"
   * FkString.left(  "19680830", -4 ) =  ""      = negative Anzahl von Stellen = Leerstring 
   * </pre>
   *  
   * @param pString der Eingabestring, von welchem abgeschnitten werden soll
   * @param pAnzahlStellen die Anzahl der abzuschneidenden Stellen 
   * @return den sich ergebenden String, Leerstring wenn die Anzahl der Stellen negativ ist
   */
  public static String left( String pString, long pAnzahlStellen )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist "pString" ein Nullpointer, wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Pruefung: Anzahl Stellen kleiner gleich 0 ?
     * 
     * Wenn von Links nichts oder negative Stellen abgeschnitten werden, 
     * bleibt von der Eingabe nichts als ein Leerstring uebrig.
     * 
     * Der Aufrufer bekommt in diesem Fall einen Leerstring zurueck.
     */
    if ( pAnzahlStellen <= 0 )
    {
      return LEERSTRING;
    }

    /*
     * Pruefung: Anzahl Stellen kleiner Stringlaenge ?
     * 
     * Ist der String aus dem Parameter "pString" laenger als die 
     * zurueckzugebenden Stellen, wird mit der "Substring"-Funktion 
     * von Position 0 ein String mit der geforderten Laenge erstellt
     * und zurueckgegeben. 
     */
    if ( pAnzahlStellen < pString.length() )
    {
      return pString.substring( 0, (int) pAnzahlStellen );
    }

    /*
     * Ist der String kuerzer als die geforderte Laenge,
     * wird der gesamte String zurueckgegeben.
     */
    return pString;
  }

  /**
   * <pre>
   * Schneidet von der uebergebenen Zahl die Anzahl der Stellen von links ab. 
   * Dazu wird die Zahl in einen String gewandelt. 
   * 
   * FkString.left(  19680830,  4 ) =  "1968"   
   * FkString.left(         0,  4 ) =  "0" 
   * FkString.left( -19680830,  4 ) =  "-196"
   * FkString.left(  19680830, -4 ) =  ""      = negative Anzahl von Stellen = Leerstring 
   * </pre>
   *  
   * @param pIntZahl eine Zahl als int
   * @param pAnzahlStellen die Anzahl der abzuschneidenden Stellen 
   * @return den sich ergebenden String, Leerstring wenn die Anzahl der Stellen negativ ist
   */
  public static String left( int pIntZahl, int pAnzahlStellen )
  {
    /*
     * Pruefung: Anzahl Stellen kleiner gleich 0 ?
     * 
     * Wenn von Links nichts oder negative Stellen abgeschnitten werden, 
     * bleibt von der Eingabe nichts als ein Leerstring uebrig.
     * 
     * Der Aufrufer bekommt in diesem Fall einen Leerstring zurueck.
     */
    if ( pAnzahlStellen <= 0 )
    {
      return LEERSTRING;
    }

    /*
     * Die Zahl des Parameters "pIntZahl" wird in einen String gewandelt.
     */
    String temp_string = "" + pIntZahl;

    /*
     * Pruefung: Anzahl Stellen kleiner Stringlaenge ?
     * 
     * Ist der gewandelte String laenger als die zurueckzugebenden Stellen,
     * wird mit der "Substring"-Funktion von Position 0 ein String mit 
     * der geforderten Laenge erstellt und zurueckgegeben. 
     */
    if ( pAnzahlStellen < temp_string.length() )
    {
      return temp_string.substring( 0, pAnzahlStellen );
    }

    /*
     * Ist der String kuerzer als die geforderte Laenge,
     * wird der gesamte String zurueckgegeben.
     */
    return temp_string;
  }

  /**
   * <pre>
   * Schneidet von der uebergebenen Zahl die Anzahl der Stellen von links ab. 
   * Dazu wird die Zahl in einen String gewandelt. 
   * 
   * FkString.left(  19680830l,  4 ) =  "1968"   
   * FkString.left(         0l,  4 ) =  "0" 
   * FkString.left( -19680830l,  4 ) =  "-196"
   * FkString.left(  19680830l, -4 ) =  ""      = negative Anzahl von Stellen = Leerstring 
   * </pre>
   *  
   * @param pLongZahl eine Zahl als long
   * @param pAnzahlStellen die Anzahl der abzuschneidenden Stellen 
   * @return den sich ergebenden String, Leerstring wenn die Anzahl der Stellen negativ ist
   */
  public static String left( long pLongZahl, int pAnzahlStellen )
  {
    /*
     * Pruefung: Anzahl Stellen kleiner gleich 0 ?
     * 
     * Wenn von Links nichts oder negative Stellen abgeschnitten werden, 
     * bleibt von der Eingabe nichts als ein Leerstring uebrig.
     * 
     * Der Aufrufer bekommt in diesem Fall einen Leerstring zurueck.
     */
    if ( pAnzahlStellen <= 0 )
    {
      return LEERSTRING;
    }

    /*
     * Die Zahl des Parameters "pLongZahl" wird in einen String gewandelt.
     */
    String temp_string = "" + pLongZahl;

    /*
     * Pruefung: Anzahl Stellen kleiner Stringlaenge ?
     * 
     * Ist der gewandelte String laenger als die zurueckzugebenden Stellen,
     * wird mit der "Substring"-Funktion von Position 0 ein String mit 
     * der geforderten Laenge erstellt und zurueckgegeben. 
     */
    if ( pAnzahlStellen < temp_string.length() )
    {
      return temp_string.substring( 0, pAnzahlStellen );
    }

    /*
     * Ist der gewandelte String kuerzer als die geforderte Laenge,
     * wird der gesamte String zurueckgegeben.
     */
    return temp_string;
  }

  /**
   * @param pString der zu pruefende String 
   * @param pMinLaenge Mindestlaenge die der Eingabestring haben muss (Pruefung nur wenn >= 0) 
   * @param pMaxLaenge Maximallaenge die der Eingabestring haben darf (Pruefung nur wenn >= 0)
   * @return -1 wenn String kleiner als Mindestlaenge, 1 wenn String laenger als Max-Laenge, 0 wenn der String dazwischen liegt (inklusive der Laengenparameter)
   */
  public static int checkLaengeMinMax( String pString, int pMinLaenge, int pMaxLaenge )
  {
    int laenge_eingabe = pString.length();

    /*
     * Ist eine Mindestlaenge gesetzt und die Laenge des 
     * Eingabestringes ist kuerzer als die Mindestlaenge, 
     * wird -1 zurueckgegeben
     */
    if ( ( pMinLaenge >= 0 ) && ( laenge_eingabe < pMinLaenge ) )
    {
      return -1;
    }

    /*
     * Ist eine Maximallaenge gesetzt und die Laenge des 
     * Eingabestringes ist laenge als die Maximallaenge, 
     * wird 1 zurueckgegeben
     */
    if ( ( pMaxLaenge >= 0 ) && ( laenge_eingabe > pMaxLaenge ) )
    {
      return 1; // Eingabestring laenger als maximal zulaessig
    }

    /*
     * Standardmaessig wird 0 zurueckgegeben, wenn
     * die Laenge des Eingabestrings in den 
     * gesetzten Grenzen ist. 
     */
    return 0;
  }

  /**
   * @param pString der zu pruefende String 
   * @param pMinLaenge die Mindeslaenge
   * @param pMaxLaenge die Maximale-Laenge
   * @return TRUE wenn der String in den Laengenbegrenzungen liegt, sonst false
   */
  public static boolean checkLaenge( String pString, int pMinLaenge, int pMaxLaenge )
  {
    try
    {
      return ( pString.length() >= pMinLaenge ) && ( pString.length() <= pMaxLaenge );
    }
    catch ( Exception err_inst )
    {
      // keine Fehlerbehandlung, da Rueckgabe von false
    }
    return false;
  }

  /**
   * <pre>
   * Kuerzt den String auf die Laenge von "pAnzahlZeichen", sofern dieses notwendig ist.
   * 
   * Gekuerzt wird, wenn der Eingabestring laenger als die vorgegebene Anzahl ist.
   *  
   * Ist der String gleich der vorgegebenen Anzahl wird nicht gekuerzt.
   * 
   * Wird der String gekuerzt, endet die Rueckgabe auf "...".
   * 
   * FkString.leftCut( "ABCDEFGHIJKLMNOPQ", 10 ) = ABCDEFG...
   * FkString.leftCut( "ABCDEFGHIJKLMNOPQ", 17 ) = ABCDEFGHIJKLMNOPQ
   * </pre>
   * 
   * @param pString der eventuell zu kuerzende String
   * @param pAnzahlZeichen die maximale Anzahl der Zeichen der Rueckgabe
   * @return den Eingabestring mit maximal der vorgegebenen Anzahl von Zeichen
   */
  public static String leftCut( String pString, int pAnzahlZeichen )
  {
    if ( ( pString != null ) && ( pString.length() > pAnzahlZeichen ) )
    {
      return pString.substring( 0, pAnzahlZeichen - 3 ) + "...";
    }

    return pString;
  }

  /**
   * <pre>
   * Prueft, ob der uebergebene String null oder per Trim()-Funktion einen LeerString
   * ergibt. Ist dem so, wird TRUE ansonsten FALSE zurueckgegeben.
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @return FALSE, wenn der String gesetzt und kein Leerstring ist.
   */
  public static boolean isLeerString( String pString )
  {
    if ( pString == null )
    {
      return true;
    }

    return pString.trim().length() == 0;
  }

  public static boolean isBlankString( String pString )
  {
    if ( pString != null )
    {
      int str_length = pString.length();

      int akt_index = 0;

      while ( akt_index < str_length )
      {
        if ( pString.charAt( akt_index ) == ' ' )
        {
          akt_index++;
        }
        else
        {
          /*
           * Enthaelt "pString" ein anderes Zeichen ausser einem Leerzeichen, ist es kein Leerstring. 
           * Der Aufrufer bekommt FALSE zurueck.
           */
          return false;
        }
      }
    }

    return true;
  }

  /**
   * <pre>
   * Ist der Parameter "pString" gleich null oder getrimmt ein Leerstring, wird der Vorgabestring zurueckgegeben.
   * 
   * FkString.getString( "A",  "B" ) = "A" = pString gesetzt
   *  
   * FkString.getString( "",   "B" ) = "B" = pString nicht gesetzt
   * FkString.getString( "  ", "B" ) = "B" = pString nicht gesetzt (trim)
   * FkString.getString( null, "B" ) = "B" = pString ist null
   * 
   * FkString.getString( null, null ) = null = Vorgabestring ist selber null 
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pVorgabeWert der String, welcher zurueckgegeben wird, wenn der String aus pString ein Leerstring ist.
   * @return den Eingabestring, sofern nicht null und mindestens 1 Zeichen, sonst den Vorgabewert
   */
  public static String getString( String pString, String pVorgabeWert )
  {
    /*
     * Pruefung: "pString" ungleich "null" und getrimmt eine Laenge groesser als 0 ?
     */
    if ( ( pString != null ) && ( pString.trim().length() > 0 ) )
    {
      /*
       * Ist der Eingabestring gesetzt und hat eine Laenge groesser als 0 Zeichen, 
       * wird der Eingabestring zurueckgegeben. 
       */
      return pString;
    }

    /*
     * Ist der Eingabestring null oder ein Leerstring, wird 
     * der Vorgabestring zurueckgegeben.
     */
    return pVorgabeWert;
  }

  /**
   * @param pVector der Vektor mit den Elementen
   * @param pTrennzeichen das zu benutzende Trennzeichen
   * @return einen String mit den einzelnen toString-Ergebnissen des Vektors
   */
  public static String toString( Object[] pVector, String pTrennzeichen )
  {
    StringBuffer str_ergebnis = new StringBuffer();

    if ( pVector != null )
    {
      int anzahl_elemente = pVector.length;

      int akt_index = 0;

      while ( akt_index < anzahl_elemente )
      {
        try
        {
          str_ergebnis.append( ( (String) pVector[ akt_index ] ) );
        }
        catch ( Exception err_inst )
        { // keine Fehlerbehandlung
        }

        str_ergebnis.append( pTrennzeichen );

        akt_index++;
      }
    }
    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * Prueft, ob im Parameter "pEingabe" die Zeichenfolge aus "pSuchString" enthalten ist.
   * Fuehrt im Endeffekt "pEingabe.indexOf( pSuchString ) >= 0" aus, behandelt aber null-Referenzen
   * 
   * Ist "pEingabe" gleich "null" wird false zurueckgegeben.
   * </pre>
   * 
   * @param pEingabe der zu durchsuchende String
   * @param pSuchString die zu suchende Zeichenfolge
   * @return TRUE wenn die zu suchende Zeichenfolge in der Eingabe enthalten ist.
   */
  public static boolean drEnthaelt( String pEingabe, String pSuchString )
  {
    if ( pEingabe != null )
    {
      if ( ( pSuchString != null ) && ( pSuchString.length() > 0 ) )
      {
        return pEingabe.indexOf( pSuchString ) >= 0;
      }
    }

    return false;
  }

  /**
   * <pre>
   * Prueft, ob im Parameter "pEingabe" die Zeichenfolge aus "pSuchString1" oder "pSuchString2" enthalten ist.
   * Der zweite Suchstring wird gesucht, sofern der erste Suchstring nicht gefunden werden konnte.
   * 
   * Fuehrt im Endeffekt "pEingabe.indexOf( pSuchString ) >= 0" aus, behandelt aber null-Referenzen
   * 
   * Ist "pEingabe" gleich "null" wird false zurueckgegeben.
   * </pre>
   * 
   * @param pEingabe der zu durchsuchende String
   * @param pSuchString1 die zuerst zu suchende Zeichenfolge
   * @param pSuchString2 die zweite zu suchende Zeichenfolge, wenn die erste Zeichenfolge nicht gefunden wurde
   * @return TRUE wenn die zu suchende Zeichenfolge in der Eingabe enthalten ist.
   */
  public static boolean drEnthaelt( String pEingabe, String pSuchString1, String pSuchString2 )
  {
    if ( pEingabe != null )
    {
      if ( ( pSuchString1 != null ) && ( pSuchString1.length() > 0 ) && ( pEingabe.indexOf( pSuchString1 ) >= 0 ) )
      {
        return true;
      }

      if ( ( pSuchString2 != null ) && ( pSuchString2.length() > 0 ) )
      {
        return ( pEingabe.indexOf( pSuchString2 ) >= 0 );
      }
    }

    return false;
  }

  /**
   * <pre>
   * Prueft, ob im Parameter "pEingabe" die Zeichenfolgen aus "pSuchString1" und "pSuchString2" enthalten sind.
   * 
   * Ist "pEingabe" gleich "null" wird false zurueckgegeben.
   * </pre>
   * 
   * @param pEingabe der zu durchsuchende String
   * @param pSuchString1 der erste String, welcher in "pEingabe" enthalten sein muss
   * @param pSuchString2 der zweite String, welcher in "pEingabe" enthalten sein muss
   * @return TRUE wenn beide Strings in der Eingabe enthalten sind.
   */
  public static boolean drEnthaeltX( String pEingabe, String pSuchString1, String pSuchString2 )
  {
    if ( pEingabe != null )
    {
      if ( ( pSuchString1 != null ) && ( pSuchString1.length() > 0 ) && ( pEingabe.indexOf( pSuchString1 ) >= 0 ) )
      {
        if ( ( pSuchString2 != null ) && ( pSuchString2.length() > 0 ) )
        {
          return ( pEingabe.indexOf( pSuchString2 ) >= 0 );
        }
      }
    }

    return false;
  }

  public static boolean drEnthaelt( String pEingabe, String pSuchString1, String pSuchString2, String pSuchString3 )
  {
    if ( pEingabe != null )
    {
      if ( ( pSuchString1 != null ) && ( pSuchString1.length() > 0 ) && ( pEingabe.indexOf( pSuchString1 ) >= 0 ) )
      {
        return true;
      }

      if ( ( pSuchString2 != null ) && ( pSuchString2.length() > 0 ) && ( pEingabe.indexOf( pSuchString2 ) >= 0 ) )
      {
        return true;
      }

      if ( ( pSuchString3 != null ) && ( pSuchString3.length() > 0 ) )
      {
        return ( pEingabe.indexOf( pSuchString3 ) >= 0 );
      }
    }

    return false;
  }

  public static boolean drEnthaelt( String pEingabe, String pSuchString1, String pSuchString2, String pSuchString3, String pSuchString4 )
  {
    if ( pEingabe != null )
    {
      if ( ( pSuchString1 != null ) && ( pSuchString1.length() > 0 ) && ( pEingabe.indexOf( pSuchString1 ) >= 0 ) )
      {
        return true;
      }

      if ( ( pSuchString2 != null ) && ( pSuchString2.length() > 0 ) && ( pEingabe.indexOf( pSuchString2 ) >= 0 ) )
      {
        return true;
      }

      if ( ( pSuchString3 != null ) && ( pSuchString3.length() > 0 ) && ( pEingabe.indexOf( pSuchString3 ) >= 0 ) )
      {
        return true;
      }

      if ( ( pSuchString4 != null ) && ( pSuchString4.length() > 0 ) )
      {
        return ( pEingabe.indexOf( pSuchString4 ) >= 0 );
      }
    }

    return false;
  }

  public static boolean drEnthaelt( String pEingabe, char pSuchString )
  {
    if ( pEingabe == null )
    {
      return false; // keine Eingabe --> kein Vorkommen des Suchstringes
    }

    return pEingabe.indexOf( pSuchString ) >= 0;
  }

  /**
   * <pre>
   * Prueft, ob in dem Parameter pEingabe die Zeichenfolge pSuchString enthalten ist.
   * </pre>
   * 
   * @param pEingabe der zu durchsuchende String
   * @param pSuchString die zu suchende Zeichenfolge
   * @param pKnzIgnoreCase Kennzeichen, ob die Gross- Kleinschreibung zu beachten ist
   * @return TRUE wenn die zu suchende Zeichenfolge in der Eingabe enthalten ist.
   */
  public static boolean drEnthaelt( String pEingabe, String pSuchString, boolean pKnzIgnoreCase )
  {
    /*
     * Pruefung: Parameter "pEingabe" gleich "null" ?
     * 
     * Ist keine Eingabe vorhanden, kann der Suchstring nicht enthalten sein. 
     * Der Aufrufer bekommt FALSE zurueck.
     */
    if ( pEingabe == null )
    {
      return false;
    }

    /*
     * Pruefung: Parameter "pSuchString" gleich "null" ?
     *
     * Ist kein Suchstring vorhanden, kann dieser auch nicht in er 
     * Eingabe enthalten sein. Der Aufrufer bekommt FALSE zurueck.
     */
    if ( pSuchString == null )
    {
      return false;
    }

    /*
     * Pruefung: Case-Sensitive unbeuecksichtigt ?
     * 
     * Soll die Gross- Kleinschreibung unberuecksichtig bleiben, werden die
     * Zeichenketten in Grossbuchstaben gewandelt und dann verglichen.
     */
    if ( pKnzIgnoreCase )
    {
      return pEingabe.toUpperCase().indexOf( pSuchString.toUpperCase() ) >= 0;
    }

    /*
     * Standardrueckgabe: Ergebnis der Funktion "indexOF"
     */
    return pEingabe.indexOf( pSuchString ) >= 0;
  }

  /**
   * <pre>
   * Erzeugt einen Substring der Eingabe bis zur vorgegebenen Position
   * Ist die Positon negativ oder der String nicht gesetzt, wird ein Leerstring zurueckgeben.
   * Ist die Positon groesser als die Laenge des Stringes wird der gesamte String zurueckgegeben.
   * 
   * Fuehrt im Endeffekt <code>pString.substring( 0, pBisPosition )</code> aus, behandelt 
   * aber null-Referenzen und Bedingung bei pBisPosition.
   * </pre>
   * 
   * @param pString der String
   * @param pBisPosition bis zu welcher Stelle abgeschnitten werden soll
   * @return einen Substring bis zur angegebenen Stelle 
   */
  public static String getStringBis( String pString, int pBisPosition )
  {
    /*
     * Parameterpruefung: pString muss gesetzt sein 
     *                    pBisPosition muss groesser gleich 0 sein.
     * 
     * Ist die BIS-Position kleiner 0, bleibt vom String nichts uebrig. 
     * Das Ergebnis ist ein Leerstring, welcher als Standardrueckgabe 
     * definiert ist. 
     */
    if ( ( pString != null ) && ( pString.length() > 0 ) && ( pBisPosition >= 0 ) )
    {
      /*
       * Pruefung: "pBisPosition" groesser gleich Stringlaenge ?
       * 
       * Liegt die Bis-Position hinter dem Stringende, ist das Ergebnis
       * der Eingabestring selber. Dieser wird zurueckgegeben. 
       */
      if ( pBisPosition >= pString.length() )
      {
        return pString;
      }

      /*
       * Liegt die Bis-Position vor dem Stringende, bekommt der Aufrufer 
       * einen String ab dem ersten Zeichen bis zur Bis-Position zurueck.
       */
      return pString.substring( 0, pBisPosition );
    }

    /*
     * Standardrueckgabe ist ein Leerstring.
     */
    return LEERSTRING;
  }

  /**
   * @param pString der String
   * @param pAbPosition Position ab welcher Stelle abgeschnitten werden soll
   * @return einen Substring ab der Stelle bis zum Stringende
   */
  public static String getStringAb( String pString, long pAbPosition )
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
        return pString.substring( (int) pAbPosition );
      }
    }

    /*
     * Standardrueckgabe ist ein Leerstring.
     */
    return LEERSTRING;
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
  public static String getStringAb( String pString, int pAbPosition )
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
    return LEERSTRING;
  }

  /**
   * <pre>
   * Liefert einen String ab der gefundenen Position des Trennzeichens mit dem Trennzeichen
   * 
   * FkString.getStringAb( "ABC-#-DEF-#-GHI-#-JKL", "-#-" ) = "-#-DEF-#-GHI-#-JKL" 
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pTrennzeichen das von links zu suchende Trennzeichen
   * @return einen String ab der gefundenen Position des Trennzeichens mit dem Trennzeichen
   */
  public static String getStringAb( String pString, String pTrennzeichen )
  {
    /*
     * Ermittlung der ersten Position des Trennzeichens in "pString"
     */
    int position_trennzeichen = pString.indexOf( pTrennzeichen );

    /*
     * Pruefung: Trennzeichen gefunden ?
     * Wurde das Trennzeichen nicht gefunden, ist das Ergebnis 
     * der Eingabe-String selber.
     */
    if ( position_trennzeichen == -1 )
    {
      return pString;
    }

    /*
     * Wurde das Trennzeichen gefunden, wird ein String ab der 
     * Trennzeichenstelle bis zum Stringende zurueckgegeben.
     * Das Trennzeichen ist Bestandteil der Rueckgabe.
     */
    return pString.substring( position_trennzeichen );
  }

  /**
   * <pre>
   * Liefert einen String ab der gefundenen Position des Trennzeichens (mit oder ohne dem Trennzeichen)
   * 
   * FkString.getStringAb( "ABC-#-DEF-#-GHI-#-JKL", "-#-", false ) = "-#-DEF-#-GHI-#-JKL" = Rueckgabe inklusive Trennzeichen
   * FkString.getStringAb( "ABC-#-DEF-#-GHI-#-JKL", "-#-", true  ) = "DEF-#-GHI-#-JKL"    = Rueckgabe ohne Trennzeichen
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pTrennzeichen das von links zu suchende Trennzeichen
   * @param pKnzTrennzeichenEntfernen das Kennzeichen, ob die Rueckgabe ohne das gefundene Trennzeichen kommen soll 
   * @return einen String ab der gefundenen Position des Trennzeichens (mit oder ohne dem Trennzeichen)
   */
  public static String getStringAb( String pString, String pTrennzeichen, boolean pKnzTrennzeichenEntfernen )
  {
    /*
     * Ermittlung der ersten Position des Trennzeichens in "pString"
     */
    int position_trennzeichen = pString.indexOf( pTrennzeichen );

    /*
     * Pruefung: Trennzeichen gefunden ?
     * 
     * Wurde das Trennzeichen nicht gefunden, ist das Ergebnis 
     * der Eingabe-String selber.
     */
    if ( position_trennzeichen == -1 )
    {
      return pString;
    }

    /*
     * Pruefung: Trennzeichen entfernen ?
     * 
     * Soll die Rueckgabe ohne das Trennzeichen erfolgen, wird 
     * die Position um die Laenge des Trennzeichens nach links 
     * verschoben.
     */
    if ( pKnzTrennzeichenEntfernen )
    {
      position_trennzeichen += pTrennzeichen.length();
    }

    /*
     * Es wird ein String ab der Position bis zum Stringende zurueckgegeben.
     * 
     * Die Position ist entweder die Position des ersten auftretens 
     * des Trennzeichens oder aber die angepasste Position.
     */
    return pString.substring( position_trennzeichen );
  }

  /**
   * <pre>
   * Prueft den Parameter pString daraufhin ab, ob dieser mit der Zeichenkette aus 
   * dem zweiten Parameter startet und endet.
   * 
   * FkString.startetEndetAuf( "A",    "A"  ) = FALSE = pString muss mindestens 2 * pStringStartEnde sein 
   * FkString.startetEndetAuf( "AB",   "AB" ) = FALSE = pString muss mindestens 2 * pStringStartEnde sein
   * FkString.startetEndetAuf( "ABAB", "AB" ) = TRUE  = Startet und endet auf AB
   * </pre>
   * 
   * @param pString der zu pruefende String 
   * @param pStringStartEnde der String fuer die Pruefung am Anfang und am Ende
   * @return TRUE wenn pString mit pStringStartEnde anfaengt und aufhoert  
   */
  public static boolean startetEndetAuf( String pString, String pStringStartEnde )
  {
    /*
     * Der Pruefstring muss vorhanden sein
     */
    if ( ( pStringStartEnde != null ) && ( pStringStartEnde.trim().length() > 0 ) )
    {
      /*
       * Der zu pruefende String muss gesetzt sein und eine Laenge von 
       * mindestens ueber zweimal der Laenge des Pruefstringes haben.
       * 
       * Ist die Laenge nur einmal so lang wie der Pruefstring, kann 
       * zwar "pString" auf den Pruefstring starten, aber nicht auf 
       * "pString" enden.
       */
      if ( ( pString != null ) && ( pString.trim().length() >= ( pStringStartEnde.trim().length() * 2 ) ) )
      {
        /*
         * Anfang wird mit der Funktion startsWith geprueft.
         * Ende wird durch Abschneiden am Ende geprueft. 
         */
        return pString.startsWith( pStringStartEnde ) && pString.substring( pString.length() - pStringStartEnde.length() ).equalsIgnoreCase( pStringStartEnde );
      }
    }

    /*
     * Standardrueckgabe ist FALSE.
     */
    return false;
  }

  /**
   * <pre>
   * Liefert die Information, ob ein String mit dem Startzeichen startet und auf dem Endzeichen endet.
   *
   * FkString.startetEndetAuf( "[ABC.DEF.GHI]", "[", "]"  ) = true
   * FkString.startetEndetAuf( " ABC.DEF.GHI]", "[", "]"  ) = false
   * FkString.startetEndetAuf( "[ABC.DEF.GHI ", "[", "]"  ) = false
   * FkString.startetEndetAuf( " ABC.DEF.GHI ", "[", "]"  ) = false
   * 
   * FkString.startetEndetAuf( "[ABC.DEF.GHI]", "[", ""   ) = false
   * FkString.startetEndetAuf( "[ABC.DEF.GHI]", "[", null ) = false
   * 
   * FkString.startetEndetAuf( "",              "[", "]"  ) = false
   * FkString.startetEndetAuf( null,            "[", "]"  ) = false
   * 
   * </pre>
   * 
   * @param pString der zu pruefende String 
   * @param pStringStart der String fuer die Pruefung des Starts
   * @param pStringEnde der String fuer die Pruefung des Endes
   * @return TRUE, sofern alle Parameter vorhanden sind und der Pruefstring mit dem Start anfaengt und auf dem Ende aufhoert
   */
  public static boolean startetEndetAuf( String pString, String pStringStart, String pStringEnde )
  {
    /*
     * Pruefung: Start- und Endstring muessen vorhanden sein.
     */
    if ( ( pStringStart != null ) && ( pStringStart.trim().length() > 0 ) )
    {
      if ( ( pStringEnde != null ) && ( pStringEnde.trim().length() > 0 ) )
      {
        /*
         * Pruefung: pString
         * Der zu pruefende String muss vorhanden sein und eine Laenge von 
         * mindesten "pStart" und "pEnde" haben 
         */
        if ( ( pString != null ) && ( pString.trim().length() >= ( pStringStart.trim().length() + pStringEnde.length() ) ) )
        {
          /*
           * Der Anfang wird mit der Funktion "startsWith" geprueft.
           * Das Ende wird durch ein Abschneiden am Stringende von "pString" geprueft. 
           */
          return pString.startsWith( pStringStart ) && ( pString.substring( pString.length() - pStringEnde.length() ).compareTo( pStringEnde ) == 0 );
        }
      }
    }

    /*
     * Standardrueckgabe ist FALSE.
     */
    return false;
  }

  /**
   * <pre>
   * Liefert die Information zurueck, ob "pString" mit einer der uebergebenen Zeichenfolgen startet.
   * 
   * Alle Parameter duerfen auch mit "null" uebergeben werden.
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pStart1 der erste String, mit dem geprueft wird
   * @param pStart2 der zweite String, mit dem geprueft wird
   * @return TRUE wenn der String entweder mit pStart1 oder mit pStart2 beginnt
   */
  public static boolean startetAuf( String pString, String pStart1, String pStart2 )
  {
    return startetAuf( pString, pStart1 ) || startetAuf( pString, pStart2 );
  }

  /**
   * <pre>
   * Liefert die Information zurueck, ob "pString" mit einer der uebergebenen Zeichenfolgen startet.
   * 
   * Alle Parameter duerfen auch mit "null" uebergeben werden.
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pStart1 der erste String, mit dem geprueft wird
   * @param pStart2 der zweite String, mit dem geprueft wird
   * @param pStart3 der dritte String, mit dem geprueft wird
   * @return TRUE wenn der String entweder mit pStart1 oder mit pStart2 oder mit pStart3 beginnt
   */
  public static boolean startetAuf( String pString, String pStart1, String pStart2, String pStart3 )
  {
    return startetAuf( pString, pStart1 ) || startetAuf( pString, pStart2 ) || startetAuf( pString, pStart3 );
  }

  /**
   * <pre>
   * Liefert die Information zurueck, ob "pString" mit einer der uebergebenen Zeichenfolgen startet.
   * 
   * Alle Parameter duerfen auch mit "null" uebergeben werden.
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pStart1 der erste String, mit dem geprueft wird
   * @param pStart2 der zweite String, mit dem geprueft wird
   * @param pStart3 der dritte String, mit dem geprueft wird
   * @param pStart4 der vierte String, mit dem geprueft wird
   * @return TRUE wenn der String entweder mit pStart1, pStart2, pStart3, oder mit pStart4 beginnt
   */
  public static boolean startetAuf( String pString, String pStart1, String pStart2, String pStart3, String pStart4 )
  {
    return startetAuf( pString, pStart1 ) || startetAuf( pString, pStart2 ) || startetAuf( pString, pStart3 ) || startetAuf( pString, pStart4 );
  }

  /**
   * <pre>
   * Liefert die Information, ob der Parameter "pString" mit der Zeichenfolge des 
   * Parameters "pStart" beginnt.
   * 
   * Ist "pString" gleich "null", wird false zurueckgegeben.
   * Ist "pStart" gleich "null", wird false zurueckgegeben.
   * 
   * Fuehrt im Endeffekt <code>pString.startsWith( pStart )</code> aus, behandelt 
   * aber null-Referenzen.
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pStart der String, mit dem geprueft wird
   * @return TRUE wenn der String mit der uebergebenen Zeichenfolge beginnt, sonst FALSE
   */
  public static boolean startetAuf( String pString, String pStart )
  {
    /*
     * Der zu untersuchende String muss gesetzt sein 
     */
    if ( ( pString != null ) && ( pString.trim().length() > 0 ) )
    {
      /*
       * Die zu pruefende Startzeichenfolge muss gesetzt sein 
       */
      if ( ( pStart != null ) && ( pStart.trim().length() > 0 ) )
      {
        /*
         * die Startzeichenfolge muss kleiner als der zu untersuchende String sein.
         */
        if ( pStart.length() <= pString.length() )
        {
          /*
           * Aufruf und Rueckgabe der Funktion "pString.startsWith( pStart )"
           */
          return pString.startsWith( pStart );
        }
      }
    }

    /*
     * Standardrueckgabe ist FALSE.
     */
    return false;
  }

  /**
   * <pre>
   * Liefert die Information, ob der erste Parameter mit der Zeichenfolge 
   * des zweiten Parameters beginnt.
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pStart der String, mit dem geprueft wird
   * @param pKnzIgnoreCase TRUE wenn die Grosskleinschreibung keine Rolle spielt
   * @return TRUE wenn der String mit der uebergebenen Zeichenfolge beginnt, sonst FALSE
   */
  public static boolean startetAuf( String pString, String pStart, boolean pKnzIgnoreCase )
  {
    /*
     * Pruefung: Parameter "pString" und "pStart" muessen gesetzt sein.
     */
    if ( ( pString != null ) && ( pStart != null ) )
    {
      /*
       * Die Startzeichenfolge muss kuerzer als der zu untersuchende String sein.
       */
      if ( pStart.length() <= pString.length() )
      {
        /*
         * Pruefung: Gross/Kleinschreibung beachten ?
         * 
         * Soll die Gross/Kleinschreibung ignoriert werden, wird die 
         * "startsWith" nur mit Grossbuchstaben aufgerufen. Dazu 
         * werden die beiden Parameter in Grossbuchstaben gewandelt.
         * 
         * Soll die Gross/Kleinschreibung beachtet werden, wird die 
         * "startsWith" normal aufgerufen.
         */
        if ( pKnzIgnoreCase )
        {
          return pString.toUpperCase().startsWith( pStart.toUpperCase() );
        }
        else
        {
          return pString.startsWith( pStart );
        }
      }
    }

    /*
     * Standardrueckgabe ist FALSE.
     */
    return false;
  }

  /**
   * <pre>
   * Liefert die Information, ob pString mit einem Buchstaben aus der Grundmenge startet. 
   * 
   * Es wird nur der erste Buchstabe geprueft.
   * 
   * Gross/Kleinschreibung wird beruecksichtigt.
   * 
   * FkString.startetMit( "Accc", "ABC" ) = true
   * FkString.startetMit( "accc", "ABC" ) = false
   * FkString.startetMit( "accc",   "a" ) = true
   * 
   * FkString.startetMit( null,     "a" ) = false
   * FkString.startetMit( "accc",  null ) = false
   * 
   * FkString.startetMit( "0001", "ABC" ) = false
   * FkString.startetMit( "0001", "ABC0" ) = true
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pGrundmenge die gueltige Grundmenge
   * @return TRUE wenn der String im ersten Zeichen, ein Zeichen der Grundmenge enthaelt.
   */
  public static boolean startetMit( String pString, String pGrundmenge )
  {
    /*
     * Pruefung: Parameter "pString" gesetzt und kein Leerstring ?
     */
    if ( ( pString != null ) && ( pString.trim().length() > 0 ) )
    {
      /*
       * Die zu pruefende Grundmenge muss gesetzt sein 
       */
      if ( ( pGrundmenge != null ) && ( pGrundmenge.trim().length() > 0 ) )
      {
        /*
         * Es wird geprueft, ob das erste Zeichen aus "pString" in der Menge
         * der gueltigen Zeichen vorkommt. Das ist der Fall, wenn die 
         * Rueckgabe der Funktion "indexOf" groesser gleich 0 ist.
         */
        return pGrundmenge.indexOf( "" + pString.charAt( 0 ) ) >= 0;
      }
    }

    /*
     * Standardrueckgabe ist FALSE.
     */
    return false;
  }

  /**
   * <pre>
   * Liefert die Information, ob der erste Parameter mit der Zeichenfolge 
   * des zweiten Parameters NICHT beginnt.
   * 
   * Fuehrt im Endeffekt <code>!pString.startsWith( pStart )</code> aus, behandelt 
   * aber null-Referenzen und laesst sich besser lesen.
   * </pre>
   * 
   * @param pString der zu pruefende String 
   * @param pStart der zu pruefende Start-String
   * @return TRUE wenn pString nicht auf pStart beginnt. FALSE wenn pString mit pStart beginnt.
   */
  public static boolean startetNichtAuf( String pString, String pStart )
  {
    /*
     * Der zu untersuchende String muss gesetzt sein 
     */
    if ( ( pString != null ) && ( pString.trim().length() > 0 ) )
    {
      /*
       * Die zu pruefende Startzeichenfolge muss gesetzt sein 
       */
      if ( ( pStart != null ) && ( pStart.trim().length() > 0 ) )
      {
        /*
         * Die Startzeichenfolge muss kuerzer als der zu untersuchende String sein.
         */
        if ( pStart.length() <= pString.length() )
        {
          /*
           * 
           */
          return pString.startsWith( pStart ) == false;
        }
      }
    }

    /*
     * Standardrueckgabe ist FALSE.
     */
    return false;
  }

  /**
   * <pre>
   * Liefert die Information, ob der erste Parameter mit der Zeichenfolge 
   * des zweiten Parameters endet. 
   * 
   * Bei der Pruefung wird nicht zwischen Gross/Klein-Schreibung unterschieden.
   * Leerzeichen am Ende werden NICHT per Trim abgeschnitten.
   * 
   * Es wird FALSE zurueckgegeben, wenn
   * ... pString null ist oder eine Laenge von 0 hat
   * ... pEnde null ist oder eine Laenge von 0 hat
   * ... pEnde laenger als pString ist
   * 
   * FkString.endetAuf( "ABCDEF",  "ABCDEF" ) = true
   * FkString.endetAuf( "ABCDEF",  "EF" )     = true
   * 
   * FkString.endetAuf( "ABCDEF",  "EA" )     = false
   * FkString.endetAuf( "ABCDEF ", "ABCDEF" ) = false, wegen Leerzeichen im ersten String
   * 
   * </pre>
   * @param pString der zu pruefende String
   * @param pEnde der String, mit dem geprueft wird, ob pString darauf endet
   * @return TRUE wenn der String mit der uebergebenen Zeichenfolge endet, sonst FALSE
   */
  public static boolean endetAuf( String pString, String pEnde )
  {
    /*
     * Der zu untersuchende String muss gesetzt sein 
     */
    if ( ( pString != null ) && ( pString.trim().length() > 0 ) )
    {
      /*
       * Die Endzeichenfolge muss gesetzt sein 
       */
      if ( ( pEnde != null ) && ( pEnde.trim().length() > 0 ) )
      {
        if ( pEnde.length() <= pString.length() )
        {
          return pString.substring( pString.length() - pEnde.length() ).equalsIgnoreCase( pEnde );
        }
      }
    }

    return false;
  }

  /**
   * <pre>
   * FkString.endetAuf( "ABCDEF", "AB", "EF" ) = TRUE
   * FkString.endetAuf( "ABCDEF", "AB", "VW" ) = FALSE
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pEnde1 der erste String, mit dem geprueft wird
   * @param pEnde2 der zweite String, mit dem geprueft wird
   * @return TRUE wenn der String entweder mit pEnde1 oder pEnde2 endet
   */
  public static boolean endetAuf( String pString, String pEnde1, String pEnde2 )
  {
    return endetAuf( pString, pEnde1 ) || endetAuf( pString, pEnde2 );
  }

  /**
   * <pre>
   * FkString.endetAuf( "ABCDEF", "AB", "CD", "EF" ) = TRUE
   * FkString.endetAuf( "ABCDEF", "AB", "CD", "VW" ) = FALSE
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pEnde1 der erste String, mit dem geprueft wird
   * @param pEnde2 der zweite String, mit dem geprueft wird
   * @param pEnde3 der dritte String, mit dem geprueft wird
   * @return TRUE wenn der String entweder mit pEnde1, pEnde2 oder pEnde3 endet
   */
  public static boolean endetAuf( String pString, String pEnde1, String pEnde2, String pEnde3 )
  {
    return endetAuf( pString, pEnde1 ) || endetAuf( pString, pEnde2 ) || endetAuf( pString, pEnde3 );
  }

  /**
   * @param pString der zu pruefende String
   * @param pEnde1 der erste String, mit dem geprueft wird
   * @param pEnde2 der zweite String, mit dem geprueft wird
   * @param pEnde3 der dritte String, mit dem geprueft wird
   * @param pEnde4 der vierte String, mit dem geprueft wird
   * @return TRUE wenn der String entweder mit pEnde1, pEnde2, pEnde3 oder pEnde4 endet
   */
  public static boolean endetAuf( String pString, String pEnde1, String pEnde2, String pEnde3, String pEnde4 )
  {
    return endetAuf( pString, pEnde1 ) || endetAuf( pString, pEnde2 ) || endetAuf( pString, pEnde3 ) || endetAuf( pString, pEnde4 );
  }

  /**
   * @param pString der zu pruefende String
   * @param pEnde der erste String, mit dem geprueft wird
   * @return TRUE wenn der String nicht auf das uebergebene Ende endet
   */
  public static boolean endetNichtAuf( String pString, String pEnde )
  {
    return !endetAuf( pString, pEnde );
  }

  /**
   * <pre>
   * Gibt den Parameterstring unter der Bedingung zurueck, dass dieser auf 
   * der im zweiten Parameter eingegebenen Zeichenfolge endet.
   * 
   * FkString.getEndeAuf( "dateiname",     ".txt" ) = dateiname.txt
   * FkString.getEndeAuf( "dateiname.txt", ".txt" ) = dateiname.txt
   * 
   * Die Gross/Kleinschreibung findet keine Beruecksichtigung:
   * 
   * FkString.getEndeAuf( "dateiname.TXT", ".txt" ) = dateiname.TXT 
   * </pre>
   *  
   * @param pString die Zeichenfolge
   * @param pEnde Zeichenfolge, auf welcher die Eingabe enden soll
   * @return pString + pEnde
   */
  public static String getEndeAuf( String pString, String pEnde )
  {
    /*
     * Parameterpruefung
     * Ist ein Parameter der Eingabe null, wuerde im unten stehenden 
     * Quelltext eine "NullpointerException" entstehen. Aus diesem Grund
     * wird hier in diesem Fall die Variable "pString" zurueckgegeben. 
     * 
     * Anmerkung:
     * Ist pString "null", bekommt der Aurufer "null" zurueck.
     * 
     * Ist pString gesetzt aber pEnde "null", erhaelt der Aufrufer
     * auf jedenfall pString zurueck. Gleichbedeutend mit:
     * 
     *     vorgegebenes Ende ist nicht definiert
     * 
     * Ist nur pEnde definiert, aber kein pString, wird null zurueckgegeben.
     */
    if ( ( pString == null ) || ( pEnde == null ) )
    {
      return pString;
    }

    if ( pString.length() >= pEnde.length() )
    {
      /* 
       * Pruefung: Ende bereits vorhanden
       * Endet der Eingabestring schon auf pEnde. Hierzu wird vom Eingabestring 
       * ein String von der laenge des vorgegebenen Endes abgeschnitten und mit 
       * dem uebergebenen Ende verglichen.
       * 
       * Stimmen beide Zeichenketten ueberein, ist das vorgegebene Ende schon  
       * enthalten und es muss nur der Eingabestring zurueckgegeben werden.  
       */
      if ( pString.substring( Math.max( 0, pString.length() - pEnde.length() ) ).equalsIgnoreCase( pEnde ) )
      {
        return pString;
      }
    }

    /* 
     * Das Ende ist noch nicht enthalten und wird der Eingabezeichenfolge angehaengt 
     */
    return pString + pEnde;
  }

  /**
   * <pre>
   * Sorgt dafuer, dass "pString" auf "pStart" startet. 
   * 
   * FkString.getStartAuf( "/ServletUrlPattern", "/"    ) = /ServletUrlPattern
   * FkString.getStartAuf( "ServletUrlPattern",  "/"    ) = /ServletUrlPattern
   * FkString.getStartAuf( "ServletUrlPattern",  null   ) = ServletUrlPattern
   * FkString.getStartAuf( "null",               "W"    ) = null
   * </pre>
   *     
   * @param pString der Eingabestring
   * @param pStart die Startzeichenfolge
   * @return einen String pStart + pString, so das pString immer auf pStart startet 
   */
  public static String getStartAuf( String pString, String pStart )
  {
    /*
     * Ist irgendwas in der Eingabe null, wuerde im unten stehenden 
     * Quelltext eine "NullpointerException" entstehen. Aus diesem Grund
     * wird hier dann der pString zurueckgegeben. 
     * 
     * Anmerkung:
     * Ist pString "null", bekommt der Aurufer "null" zurueck.
     * 
     * Ist pString gesetzt aber pStart "null", erhaelt der Aufrufer
     * auf jedenfall pString zurueck. Gleichbedeutend mit:
     * 
     *     Start ist nicht definiert.
     * 
     * Ist nur pEnde definiert, aber kein pString, wird null zurueckgegeben.
     */
    if ( ( pString == null ) || ( pStart == null ) )
    {
      return pString;
    }

    if ( pString.length() >= pStart.length() )
    {
      /*
       * Hier wird geprueft, ob der Eingabestring schon auf pStart startet.
       * Hierzu wird vom Eingabestring ein String von der laenge des vorgegebenen 
       * Anfanges abgeschnitten und mit dem vorgegebenen Start verglichen.
       * 
       * Stimmen beide Zeichenketten ueberein, ist der vorgegebene Start 
       * schon enthalten und es muss nur der Eingabestring zurueckgegeben werden.  
       */
      if ( pString.substring( 0, pStart.length() ).equalsIgnoreCase( pStart ) )
      {
        return pString;
      }
    }

    /*
     * Der start ist noch nicht enthalten und wird der Eingabezeichenfolge vorweggestellt 
     */
    return pStart + pString;
  }

  /**
   * <pre>
   * Gibt eine Zeichenfolge aus dem ersten String zurueck, welche ab der Position des zweiten
   * Parameters beginnt und eine Laenge des dritten Parameters umfasst. Bei unzulaenglichen Parametern
   * wird ein Leerstring zurueckgegeben. Falls sich das Ende des Substringes ausserhalb der Laenge des
   * Ausgangsstringes liegt, wird der Substring bis zum Ende des Ausgangstringes begrenzt.
   * 
   * FkString.getStringVonAnzahl( "ABC.DEF.GHI.JKL",  5,  3 ) = "DEF" 
   * FkString.getStringVonAnzahl( "ABC.DEF.GHI.JKL",  5, 90 ) = "DEF.GHI.JKL"
   * FkString.getStringVonAnzahl( "ABC.DEF.GHI.JKL", -5,  3 ) = "ABC"
   * FkString.getStringVonAnzahl( "ABC.DEF.GHI.JKL",  0, -5 ) = "ABC.DEF.GHI.JKL"
   * FkString.getStringVonAnzahl( "ABC.DEF.GHI.JKL", 10,  0 ) = ""
   * FkString.getStringVonAnzahl( "ABC.DEF.GHI.JKL", 20,  3 ) = ""  
   * </pre>
   * 
   * @param pEingabe die Zeichenfolge welche den Substring enthaelt
   * @param pStartIndex ab welcher Position soll begonnen werden (Startet bei 0)
   * @param pAnzahlStellen wie viele Stellen sollen enthalten sein
   * @return der Substring
   */
  public static String getStringVonAnzahl( String pEingabe, int pStartIndex, int pAnzahlStellen )
  {
    if ( pEingabe == null )
    {
      return LEERSTRING;
    }

    String ergebnis = LEERSTRING;

    if ( pAnzahlStellen < 0 )
    {
      pAnzahlStellen = pEingabe.length();
    }

    if ( pAnzahlStellen > 0 )
    {
      if ( pStartIndex < 0 )
      {
        pStartIndex = 0;
      }

      if ( pEingabe.length() >= pStartIndex )
      {
        if ( pStartIndex == 0 )
        {
          if ( ( pStartIndex + pAnzahlStellen ) <= pEingabe.length() )
          {
            ergebnis = pEingabe.substring( pStartIndex, pStartIndex + pAnzahlStellen );
          }
          else
          {
            ergebnis = pEingabe.substring( pStartIndex );
          }
        }
        else if ( ( ( pStartIndex - 1 ) + pAnzahlStellen ) <= pEingabe.length() )
        {
          ergebnis = pEingabe.substring( pStartIndex - 1, ( pStartIndex - 1 ) + pAnzahlStellen );
        }
        else
        {
          ergebnis = pEingabe.substring( pStartIndex - 1 );
        }
      }
    }

    return ergebnis;
  }

  /**
   * Liefert den Eingabestring zurueck, wenn dieser ungleich null ist.
   * Ist der Eingabestring gleich null, wird ein Leerstring zurueckgeliefert.
   * 
   * @param pString der zu pruefende String
   * @return der Eingabestring. Ist die Eingabe null, einen Leerstring.
   */
  public static String getString( String pString )
  {
    if ( pString != null )
    {
      return pString;
    }

    return "";
  }

  public static String getStringNullX( String pString )
  {
    if ( pString != null )
    {
      if ( pString.length() > 0 )
      {
        return pString;
      }
    }

    return null;
  }

  public static String getString( BigDecimal pBigDecimal )
  {
    if ( pBigDecimal != null )
    {
      return pBigDecimal.toPlainString();
    }

    return "";
  }

  public static String getString( BigDecimal pBigDecimal, String pVorgabe )
  {
    if ( pBigDecimal != null )
    {
      return pBigDecimal.toPlainString();
    }

    return pVorgabe;
  }


  /**
   * @param pKnz der auszugebende Wert
   * @param pVorgabe nur fuer die Funktionssignatur vorhandener Parameter (wird nicht benutzt / Generator)
   * @return den boolschen Parameter als String 
   */
  public static String getString( boolean pKnz, String pVorgabe )
  {
    return "" + pKnz;
  }

  /**
   * @param pLong der auszugebende Wert
   * @param pVorgabe nur fuer die Funktionssignatur vorhandener Parameter (wird nicht benutzt / Generator)
   * @return den long Parameter als String 
   */
  public static String getString( long pLong, String pVorgabe )
  {
    return "" + pLong;
  }

  /**
   * @param pInt der auszugebende Wert
   * @param pVorgabe nur fuer die Funktionssignatur vorhandener Parameter (wird nicht benutzt / Generator)
   * @return den int Parameter als String 
   */
  public static String getString( int pInt, String pVorgabe )
  {
    return "" + pInt;
  }

  /**
   * @param pDouble der auszugebende Wert
   * @param pVorgabe nur fuer die Funktionssignatur vorhandener Parameter (wird nicht benutzt / Generator)
   * @return den double Parameter als String 
   */
  public static String getString( double pDouble, String pVorgabe )
  {
    return "" + pDouble;
  }

  /**
   * Liefert einen String zurueck, welcher den Eingabestring x-mal wiederholt.
   * 
   * @param pString der zu vervielfaeltigende String 
   * @param pAnzahlWiederholungen die Anzahl der Wiederholungen
   * @return den Eingabe-String x-mal wiederholt, oder einen Leerstring, wenn der UebergabeString null ist, oder die Anzahl der Wiederholungen kleiner gleich 0 ist.
   */
  public static String getString( String pString, int pAnzahlWiederholungen )
  {
    String ergebnis = "";

    if ( pString != null )
    {
      int zaehler = 0;

      while ( zaehler < pAnzahlWiederholungen )
      {
        ergebnis += pString;

        zaehler++;
      }
    }

    return ergebnis;
  }

  public static String getString( String pString, long pAnzahlWiederholungen )
  {
    String ergebnis = "";

    if ( pString != null )
    {
      long zaehler = 0;

      while ( zaehler < pAnzahlWiederholungen )
      {
        ergebnis += pString;

        zaehler++;
      }
    }

    return ergebnis;
  }

  /**
   * @param pStringWiederholung der zu Wiederholende Stringbestandteil
   * @param pStringAbschluss der abschliesende Stringbestandteil
   * @param pAnzahlWiederholungen Anzahl der Wiederholungen vom Wiederholungsstring
   * @return einen String 
   */
  public static String getString( String pStringWiederholung, String pStringAbschluss, int pAnzahlWiederholungen )
  {
    String ergebnis = "";

    if ( pStringWiederholung != null )
    {
      int zaehler = 0;

      while ( zaehler < pAnzahlWiederholungen )
      {
        ergebnis += pStringWiederholung;

        zaehler++;
      }
    }

    return ergebnis + ( pStringAbschluss == null ? "" : pStringAbschluss );
  }

  /**
   * <pre>
   * Gibt einen String in der angegebenen Laenge und der angegebenen Zeichenfolge zurueck.
   *  
   * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
   * 
   * Ist der Parameeter "pZeichen" gleich null, wird ein Leerstring zurueckgegeben.
   * </pre>
   * 
   * @param pAnzahlStellen die Laenge
   * @param pZeichen das zu wiederholende Zeichen
   * @return einen String der angegebenen Laenge mit dem uebergebenen Zeichen
   */
  public static String nZeichen( int pAnzahlStellen, String pZeichen )
  {
    if ( pZeichen == null )
    {
      return "";
    }

    /*
     * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
     */
    if ( pAnzahlStellen <= 0 )
    {
      return "";
    }

    if ( pAnzahlStellen > 15000 )
    {
      pAnzahlStellen = 15000;
    }

    String ergebnis = pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen;

    /*
     * Der String "ergebnis" wird solange verdoppelt bis die Laenge groesser der Anzahl aus dem Parameter ist. 
     * Anschliessend wird ein Substring der Parameter-Laenge zurueckgegeben.
     */
    int zaehler = 1;

    while ( ( zaehler <= 50 ) && ( ergebnis.length() <= pAnzahlStellen ) )
    {
      ergebnis += ergebnis;

      zaehler++;
    }

    return ergebnis.substring( 0, pAnzahlStellen );
  }

  /**
   * @param pAnzahlStellenAlsString die Anzahl der Wiederholungen als String (=Stringlaenge wird ermittelt)
   * @param pZeichen das zu wiederholende Zeichen
   * @return einen String des vorgegebenen Zeichens, welcher so lang wie der erste Parameter ist.
   */
  public static String nZeichen( String pAnzahlStellenAlsString, String pZeichen )
  {
    if ( pAnzahlStellenAlsString == null )
    {
      return "";
    }

    int anzahl_stellen = pAnzahlStellenAlsString.length();

    /*
     * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
     */
    if ( anzahl_stellen <= 0 )
    {
      return "";
    }

    String str_ergebnis = "";

    if ( pZeichen != null )
    {
      str_ergebnis = pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen;
    }
    else
    {
      str_ergebnis = "          ";
    }

    /*
     * Der String "ergebnis" wird solange verdoppelt bis die Laenge groesser der Anzahl aus dem Parameter ist. 
     * Anschliessend wird ein Substring der Parameter-Laenge zurueckgegeben.
     */
    int zaehler = 1;

    while ( ( str_ergebnis.length() <= anzahl_stellen ) && ( zaehler < 20 ) )
    {
      str_ergebnis += str_ergebnis;

      zaehler++;
    }

    return str_ergebnis.substring( 0, anzahl_stellen );
  }

  /**
   * @param pAnzahlWiederholungen die Anzahl der Wiederholungen
   * @param pZeichen die zu wiederholende Zeichenkette
   * @return einen String aus pZeichen mit der vorgegebenen Anzahl von Wiederholungen
   */
  public static String nMal( int pAnzahlWiederholungen, String pZeichen )
  {
    /*
     * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
     */
    if ( pAnzahlWiederholungen <= 0 )
    {
      return "";
    }

    if ( pZeichen == null )
    {
      pZeichen = " ";
    }

    StringBuffer str_ergebnis = new StringBuffer();

    int zaehler = 1;

    while ( zaehler <= pAnzahlWiederholungen )
    {
      str_ergebnis.append( pZeichen );

      zaehler++;
    }

    return str_ergebnis.toString();
  }

  /**
   * Aufruf der gleichnamigen Funktion getFeldLinks mit dem Leerzeichen als Auffuellparameter.
   * 
   * @param pInhalt der Inhalt des Feldes
   * @param pLaenge die Laenge des Feldes
   * @return ein Feld in der angegebenen Laenge, wobei der Feldinhalt links steht und evtl. mit
   * Leerzeichen aufgefuellt wurde
   */
  public static String getFeldLinks( String pInhalt, int pLaenge )
  {
    return getFeldLinks( pInhalt, " ", pLaenge );
  }

  /**
   * @param pInhalt der Inhalt als int-Zahl
   * @param pLaenge die Laenge des Feldes
   * @return ein Feld in der angegebenen Laenge
   */
  public static String getFeldLinks( int pInhalt, int pLaenge )
  {
    return getFeldLinks( "" + pInhalt, " ", pLaenge );
  }

  /**
   * @param pInhalt der Inhalt als long-Zahl
   * @param pLaenge die Laenge des Feldes
   * @return ein Feld in der angegebenen Laenge
   */
  public static String getFeldLinks( long pInhalt, int pLaenge )
  {
    return getFeldLinks( "" + pInhalt, " ", pLaenge );
  }

  /**
   * Gibt einen String der uebergebenen Laenge zurueck, wobei der Feldinhalt links steht.
   * 
   * @param pInhalt der Inhalt des Feldes
   * @param pAuffuellZeichen das zur Auffuellung vorgegebene Zeichen
   * @param pLaenge die Laenge des Feldes
   * @return ein Feld in der angegebenen Laenge, wobei der Feldinhalt links steht und evtl. mit dem
   * uebergebenen Auffuellzeichen aufgefuellt wurde
   */
  public static String getFeldLinks( String pInhalt, String pAuffuellZeichen, int pLaenge )
  {
    if ( pInhalt == null )
    {
      pInhalt = "";
    }

    if ( pInhalt.length() >= pLaenge )
    {
      return pInhalt.substring( 0, pLaenge );
    }

    return pInhalt + nZeichen( pLaenge - pInhalt.length(), pAuffuellZeichen );
  }

  /**
   * @param pInhalt der Inhalt des Feldes
   * @param pMindestLaenge die vorgegebene Mindestlaenge des Rueckgabestrings
   * @return einen String, welcher mindestens pMindesLaenge breit ist (evtl. aufgefuellt mit Leerzeichen)
   */
  public static String getFeldLinksMin( String pInhalt, int pMindestLaenge )
  {
    if ( pInhalt == null )
    {
      pInhalt = "";
    }

    if ( pInhalt.length() >= pMindestLaenge )
    {
      return pInhalt;
    }

    return pInhalt + nZeichen( pMindestLaenge - pInhalt.length(), " " );
  }

  public static String getFeldLinksMin( int pInhalt, int pMindestLaenge )
  {
    return getFeldLinksMin( "" + pInhalt, " ", pMindestLaenge, pMindestLaenge );
  }

  public static String getFeldLinksMin( boolean pInhalt, int pMindestLaenge )
  {
    return getFeldLinksMin( "" + pInhalt, " ", pMindestLaenge, pMindestLaenge );
  }

  /**
   * @param pInhalt der Inhalt des Feldes
   * @param pAuffuellZeichen das zu verwendende Auffuellzeichen
   * @param pMindestLaenge die vorgegebene Mindestlaenge des Rueckgabestrings
   * @return einen String, welcher mindestens pMindesLaenge breit ist (evtl. aufgefuellt mit dem angegebenen Auffuellzeichen)
   */
  public static String getFeldLinksMin( String pInhalt, String pAuffuellZeichen, int pMindestLaenge )
  {
    return getFeldLinksMin( pInhalt, pAuffuellZeichen, pMindestLaenge, pMindestLaenge );
  }

  /**
   * @param pInhalt der Inhalt des Feldes
   * @param pAuffuellZeichen das zu verwendende Auffuellzeichen
   * @param pMindestLaenge1 die vorgegebene Mindestlaenge 1 des Rueckgabestrings
   * @param pMindestLaenge2 die vorgegebene Mindestlaenge 2 des Rueckgabestrings
   * @return einen String, welcher mindestens pMindesLaenge breit ist (evtl. aufgefuellt mit dem angegebenen Auffuellzeichen)
   */
  public static String getFeldLinksMin( String pInhalt, String pAuffuellZeichen, int pMindestLaenge1, int pMindestLaenge2 )
  {
    if ( pInhalt == null )
    {
      pInhalt = "";
    }

    if ( pInhalt.length() >= pMindestLaenge2 )
    {
      return pInhalt;
    }

    if ( pInhalt.length() >= pMindestLaenge1 )
    {
      return pInhalt;
    }

    return pInhalt + nZeichen( pMindestLaenge1 - pInhalt.length(), pAuffuellZeichen );
  }

  public static String getFeldRechtsMin( String pFeldWert, int pMindestLaenge )
  {
    String feld_wert = "" + pFeldWert;

    if ( feld_wert.length() >= pMindestLaenge )
    {
      return feld_wert;
    }

    return nZeichen( pMindestLaenge - feld_wert.length(), " " ) + feld_wert;
  }

  public static String getFeldRechtsMin( boolean pFeldWert, int pMindestLaenge )
  {
    return getFeldRechtsMin( "" + pFeldWert, pMindestLaenge );
  }

  public static String getFeldRechtsMin( BigDecimal pFeldWert, int pMindestLaenge )
  {
    if ( pFeldWert == null )
    {
      return getFeldRechtsMin( "null", pMindestLaenge );
    }

    return getFeldRechtsMin( pFeldWert.toPlainString(), pMindestLaenge );
  }

  public static String getFeldRechtsMin( int pFeldWert, int pMindestLaenge )
  {
    String feld_wert = "" + pFeldWert;

    if ( feld_wert.length() >= pMindestLaenge )
    {
      return feld_wert;
    }

    return nZeichen( pMindestLaenge - feld_wert.length(), " " ) + feld_wert;
  }

  public static String getFeldRechtsMin( double pFeldWert, int pMindestLaenge )
  {
    String feld_wert = "" + pFeldWert;

    if ( feld_wert.length() >= pMindestLaenge )
    {
      return feld_wert;
    }

    return nZeichen( pMindestLaenge - feld_wert.length(), " " ) + feld_wert;
  }

  public static String getFeldRechtsMin( long pFeldWert, int pMindestLaenge )
  {
    String feld_wert = "" + pFeldWert;

    if ( feld_wert.length() >= pMindestLaenge )
    {
      return feld_wert;
    }

    return nZeichen( pMindestLaenge - feld_wert.length(), " " ) + feld_wert;
  }

  public static String getFeldRechtsMin( Date pDatum, int pMindestLaenge )
  {
    String feld_wert = null;

    if ( pDatum != null )
    {
      Calendar datum = Calendar.getInstance();

      datum.setTime( pDatum );

      int tag = datum.get( Calendar.DATE );

      int monat = datum.get( Calendar.MONTH ) + 1;

      feld_wert = ( tag < 10 ? "0" : "" ) + tag + "." + ( monat < 10 ? "0" : "" ) + monat + "." + datum.get( Calendar.YEAR );
    }
    else
    {
      feld_wert = "null";
    }

    if ( feld_wert.length() >= pMindestLaenge )
    {
      return feld_wert;
    }

    return nZeichen( pMindestLaenge - feld_wert.length(), " " ) + feld_wert;
  }

  public static String getFeldRechtsMin( String pInhalt, String pAuffuellZeichen, int pMindestLaenge )
  {
    if ( pInhalt == null )
    {
      pInhalt = "";
    }

    if ( pInhalt.length() >= pMindestLaenge )
    {
      return pInhalt;
    }

    return nZeichen( pMindestLaenge - pInhalt.length(), pAuffuellZeichen ) + pInhalt;
  }

  /**
   * @param pInhalt der Inhalt des Feldes
   * @param pAuffuellZeichen das zu benutzende Auffuellzeichen
   * @param pLaenge die Laenge
   * @return ein String der vorgegebenen Laenge und dem Inhalt rechts ausgerichtet
   */
  public static String getFeldRechts( String pInhalt, String pAuffuellZeichen, int pLaenge )
  {
    if ( pInhalt == null )
    {
      pInhalt = "";
    }

    if ( pInhalt.length() >= pLaenge )
    {
      return pInhalt.substring( 0, pLaenge );
    }

    return nZeichen( pLaenge - pInhalt.length(), pAuffuellZeichen ) + pInhalt;
  }

  /**
   * @param pInhalt der Inhalt als int
   * @param pLaenge die vorgegebene Laenge
   * @return ein String der vorgegebebenen Laenge und dem Inhalt rechts ausgerichtet
   */
  public static String getFeldRechts( String pInhalt, int pLaenge )
  {
    return getFeldRechts( pInhalt, LEERZEICHEN, pLaenge );
  }

  /**
   * @param pInhalt der Inhalt als int
   * @param pLaenge die vorgegebene Laenge
   * @return ein String der vorgegebebenen Laenge und dem Inhalt rechts ausgerichtet
   */
  public static String getFeldRechts( int pInhalt, int pLaenge )
  {
    return getFeldRechts( LEERSTRING + pInhalt, LEERZEICHEN, pLaenge );
  }

  /**
  * @param pInhalt der Inhalt als long
   * @param pLaenge die vorgegebene Laenge
   * @return ein String der vorgegebebenen Laenge und dem Inhalt rechts ausgerichtet
   */
  public static String getFeldRechts( long pInhalt, int pLaenge )
  {
    return getFeldRechts( LEERSTRING + pInhalt, LEERZEICHEN, pLaenge );
  }

  /**
   * @param pZahl die Zahl
   * @param pLaenge die vorgegebene Laenge
   * @return ein String der vorgegebenen Laenge mit den fuehrenden Nullen.
   */
  public static String getZahlMitFuehrendenNullen( int pZahl, int pLaenge )
  {
    return getFeldRechts( "" + pZahl, "0", pLaenge );
  }

  /**
   * Ermittelt den boolschen Wert des Parameters "pWert" und gibt die entsptrechend angegebene
   * Stringbezeichnung zurueck.
   * 
   * @param pWert der zu pruefende boolsche Wert
   * @param pStringJa die Rueckgabe im Fall von TRUE
   * @param pStringNein die Rueckgabe im Fall von FALSE
   * @return entweder pStringJa oder pStringNein
   */
  public static String getBoolean( boolean pWert, String pStringJa, String pStringNein )
  {
    return pWert ? pStringJa : pStringNein;
  }

  /**
   * @param pWert der zu pruefende boolsche Wert
   * @param pStringJa die Rueckgabe im Fall von TRUE
   * @param pStringNein die Rueckgabe im Fall von FALSE
   * @return entweder pStringJa oder pStringNein, null sofern pWert null ist
   */
  public static String getBoolean( Boolean pWert, String pStringJa, String pStringNein )
  {
    if ( pWert == null )
    {
      return null;
    }

    return pWert.booleanValue() ? pStringJa : pStringNein;
  }

  /**
   * Ermittelt den boolschen Wert des Parameters "pWert" und gibt die entsptrechend angegebene
   * Stringbezeichnung zurueck.
   * 
   * @param pWert der zu pruefende boolsche Wert
   * @param pVorgabeWert der Vorgabewert, sollte pWert keinen boolschen Ausdruck ergeben oder null sein
   * @param pStringJa die Rueckgabe im Fall von TRUE
   * @param pStringNein die Rueckgabe im Fall von FALSE
   * @return entweder pStringJa oder pStringNein
   */
  public static String getBoolean( String pWert, boolean pVorgabeWert, String pStringJa, String pStringNein )
  {
    return getBoolean( pWert, pVorgabeWert ) ? pStringJa : pStringNein;
  }

  /**
   * <pre>
   * Ermittelt den boolschen Wert des Parameters "pWert" anhand der Vorgaben fuer true oder false.
   * 
   * FkString.getBoolean1( Vergleichswert, Vorgabewert, TRUE_WERT_1, FALSE_WERT_1, TRUE_WERT_2, FALSE_WERT_2 )
   * 
   * FkString.getBoolean1( 0, true, 0 , 1 , 2 , 3 ) = true   = 0 ist TRUE_WERT_1
   * FkString.getBoolean1( 1, true, 0 , 1 , 2 , 3 ) = false  = 1 ist FALSE_WERT_1
   * FkString.getBoolean1( 2, true, 0 , 1 , 2 , 3 ) = true   = 2 ist TRUE_WERT_2
   * FkString.getBoolean1( 3, true, 0 , 1 , 2 , 3 ) = false  = 3 ist FALSE_WERT_2
   * </pre>
   * 
   * @param pWert der zu pruefende boolsche Wert
   * @param pVorgabeWert der Vorgabewert, sollte pWert keinen boolschen Ausdruck ergeben oder null sein
   * @param pStringJa1 Stringbezeichnung 1 fuer "true"
   * @param pStringNein1 Stringbezeichnung 1 fuer "false"
   * @param pStringJa2 Stringbezeichnung 2 fuer "true"
   * @param pStringNein2 Stringbezeichnung 2 fuer "false"
   * @return je nach Uebereinstimmung "true" oder "false"
   */
  public static boolean getBoolean1( String pWert, boolean pVorgabeWert, String pStringJa1, String pStringNein1, String pStringJa2, String pStringNein2 )
  {
    if ( pWert != null )
    {
      if ( pStringJa1 != null )
      {
        if ( pWert.compareTo( pStringJa1 ) == 0 )
        {
          return true;
        }
      }

      if ( pStringJa2 != null )
      {
        if ( pWert.compareTo( pStringJa2 ) == 0 )
        {
          return true;
        }
      }

      if ( pStringNein1 != null )
      {
        if ( pWert.compareTo( pStringNein1 ) == 0 )
        {
          return false;
        }
      }

      if ( pStringNein2 != null )
      {
        if ( pWert.compareTo( pStringNein2 ) == 0 )
        {
          return false;
        }
      }
    }
    return pVorgabeWert;
  }

  /**
   * Fuegt die beiden Parameter unter der Beruecksichtitgung von Null-Strings zusammen. 
   * Sind beide Strings null wird dem Aufrufer ein Leerstring zurueckgegeben.
   * 
   * @param pString1 der erste String
   * @param pString2 der zweite String
   * @return mindestens ein Leerstring, oder eine addition der beiden Strings
   */
  public static String add( String pString1, String pString2 )
  {
    return ( pString1 == null ? "" : pString1 ) + ( pString2 == null ? "" : pString2 );
  }

  /**
   * Der Verbindungsstring wird nur gesetzt, wenn die beiden Stringparameter ungleich null sind.
   * @param pString1 der erste String
   * @param pString2 der zweite String
   * @param pVerbindungsString der String fuer die Verbindung der beiden Strings
   * @return mindestens ein Leerstring, oder eine addition der beiden Strings
  */
  public static String add( String pString1, String pString2, String pVerbindungsString )
  {
    return ( pString1 == null ? "" : pString1 ) + ( pVerbindungsString == null ? "" : ( ( pString1 == null ) || ( pString2 == null ) ) ? "" : pVerbindungsString ) + ( pString2 == null ? "" : pString2 );
  }

  /**
   * @param pString die erste Zeichenkette in der Rueckgabe, sowie die auf null zu pruefende Zeichenkette
   * @param pSuffix der eventuell anzuhaengende String, wenn der erste Parameter nicht null ist 
   * @return null wenn der Parameter pFuehrenderString null ist, ansonsten pFuehrenderString + pPlustString
   */
  public static String addNull( String pString, String pSuffix )
  {
    if ( pString == null )
    {
      return null;
    }

    if ( pSuffix == null )
    {
      return pString;
    }

    return pString + pSuffix;
  }

  /**
   * @param pString 
   * @param pPraefix der eventuell vorn anzuhaengende String, wenn der erste Parameter nicht null ist
   * @param pSuffix der eventuell hinten anzuhaengende String, wenn der erste Parameter nicht null ist
   * @return
   */
  public static String addNull( String pString, String pPraefix, String pSuffix )
  {
    if ( pString == null )
    {
      return null;
    }

    if ( ( pPraefix == null ) && ( pSuffix == null ) )
    {
      return pString;
    }

    if ( ( pPraefix != null ) && ( pSuffix != null ) )
    {
      return pPraefix + pString + pSuffix;
    }

    if ( pPraefix != null )
    {
      return pPraefix + pString;
    }

    return pString + pSuffix;
  }

  /**
   * <pre>
   * Ermittelt einen Boolschen Wert aus dem uebergebenen String unabhaengig von dder
   * Gross/Kleinschreibung. Bei null wird der Vorgabewert zurueckgegeben.
   * 
   * TRUE - 1, j, y, +, t, yes, true, ja, ein, an, wahr 
   * FALSE - 0, n, -, f, no, false, nein, aus, falsch
   * </pre>
   * @param pString der Wert, welcher entweder einen Zustand true oder false beschreibt
   * @param pVorgabeWert der Vorgabewert fuer keine Uebereinstimmung
   * @return true oder false
   */
  public static boolean getBoolean( String pString, boolean pVorgabeWert )
  {
    if ( ( pString != null ) && ( pString.length() > 0 ) )
    {
      /*
       * Damit keine Teilzeichenfolgen gefunden werden, wird ein Komma 
       * vor die Eingabe gestellt. Die Uebereinstimmung muss also mit einem
       * Komma beginnen. 
       */
      if ( STR_WERT_BOOLEAN_TRUE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return true;
      }

      if ( STR_WERT_BOOLEAN_FALSE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return false;
      }
    }

    return pVorgabeWert;
  }

  /**
   * <pre>
   * Ermittelt einen Boolschen Wert aus dem uebergebenen String unabhaengig von
   * Gross/Kleinschreibung und gibt eine Instanz von java.lang.Boolean zurueck.
   * Ist der Parameter null oder der boolsche Wert kann nicht ermittelt werden,  
   * wird null zurueckgegeben.
   * 
   * TRUE - 1, j, y, +, t, yes, true, ja, ein, an 
   * FALSE - 0, n, -, f, no, false, nein, aus, 
   * </pre>
   * 
   * @param pString der Wert, welcher entweder einen Zustand true oder false beschreibt
   * @param pVorgabeWert der Vorgabewert fuer keine Uebereinstimmung
   * @return true oder false
   */
  public static Boolean getBooleanInstanz( String pString )
  {
    if ( pString != null )
    {
      /*
       * Damit keine Teilzeichenfolgen gefunden werden, wird ein Komma 
       * vor die Eingabe gestellt. Die Uebereinstimmung muss also mit einem
       * Komma beginnen. 
       */
      if ( STR_WERTE_BOOLEAN_TRUE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return Boolean.TRUE;
      }

      if ( STR_WERTE_BOOLEAN_FALSE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return Boolean.FALSE;
      }
    }

    return null;
  }

  /**
   * @param pString der Wert, welcher entweder einen Zustand true oder false beschreibt
   * @param pVorgabeWert der Vorgabewert fuer keine Uebereinstimmung
   * @return true oder false
   */
  public static boolean isTrue( String pString )
  {
    if ( pString != null )
    {
      if ( STR_WERTE_BOOLEAN_TRUE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return true;
      }
    }

    return false;
  }

  /**
   * @param pString der zu pruefende String 
   * @return TRUE wenn in pString eine Zeichenfolge fuer FALSE enthalten ist-
   */
  public static boolean isFalse( String pString )
  {
    if ( pString != null )
    {
      if ( STR_WERTE_BOOLEAN_FALSE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return true;
      }
    }

    return false;
  }

  /**
   * @param pString der zu pruefende String 
   * @return TRUE bei W, Weiblich, Frau
   */
  public static boolean isWeiblich( String pString )
  {
    if ( pString != null )
    {
      if ( "W".equalsIgnoreCase( pString ) )
      {
        return true;
      }

      if ( "Weiblich".equalsIgnoreCase( pString ) )
      {
        return true;
      }

      if ( "Frau".equalsIgnoreCase( pString ) )
      {
        return true;
      }
    }

    return false;
  }

  /**
   * Macht zusaetzlich einen TRIM auf die Parameter
   * 
   * @param pString1 der erste String
   * @param pString2 der zweite String
   * @return TRUE wenn String1 und String2 gleich sind
   */
  public static boolean equalsIgnoreCaseTrim( String pString1, String pString2 )
  {
    if ( pString1 == null )
    {
      return false;
    }

    if ( pString2 == null )
    {
      return false;
    }

    return pString1.trim().equalsIgnoreCase( pString2.trim() );
  }

  /**
   * @param pString der zu pruefende String 
   * @param pVerglString1 der erste Vergleichsstring
   * @param pVerglString2 der optional zweite Vergleichsstring 
   * @return TRUE, wenn pString mit dem ersten oder zweiten String uebereinstimmt, sonst FALSE
   */
  public static boolean equalsIgnoreCaseTrim( String pString, String pVerglString1, String pVerglString2 )
  {
    if ( pString != null )
    {
      if ( pVerglString1 != null )
      {
        if ( pString.trim().equalsIgnoreCase( pVerglString1.trim() ) )
        {
          return true;
        }

        // bei false muss noch der zweite String verglichen werden
      }

      if ( pVerglString2 != null )
      {
        return pString.trim().equalsIgnoreCase( pVerglString2.trim() );
      }
    }

    return false;
  }

  /**
   * @param pString der erste String
   * @param pVerglString1 der zweite String
   * @return TRUE wenn String1 und pVerglString1 gleich sind
   */
  public static boolean equalsIgnoreCase( String pString, int pVerglString1 )
  {
    if ( pString != null )
    {
      return pString.equalsIgnoreCase( "" + pVerglString1 );
    }

    return false;
  }

  /**
   * @param pString der erste String
   * @param pVerglString1 der zweite String
   * @return TRUE wenn String1 und pVerglString1 gleich sind
   */
  public static boolean equalsIgnoreCase( String pString, String pVerglString1 )
  {
    if ( pString != null )
    {
      if ( pVerglString1 != null )
      {
        return pString.equalsIgnoreCase( pVerglString1 );
      }
    }

    return false;
  }

  /**
   * @param pString der zu pruefende String 
   * @param pVerglString1 der erste Vergleichsstring
   * @param pVerglString2 der optional zweite Vergleichsstring 
   * @return TRUE, wenn pString mit dem ersten oder zweiten String uebereinstimmt, sonst FALSE
   */
  public static boolean equalsIgnoreCase( String pString, String pVerglString1, String pVerglString2 )
  {
    /*
     * Pruefung: Parameter "pString" ungleich "null" ? 
     * 
     * Ist der Parameter "pString" gleich "null", ist kein String zum vergleichen vorhanden.
     * Der Aufrufer bekommt in diesem Fall "false" zurueck.
     */
    if ( pString != null )
    {
      /*
       * Pruefung: Erster Vergleichsstring gesetzt ?
       * Ist der erste Vergleichsstring gesetzt, wird dieser mit "pString" verglichen. 
       * Stimmen die beiden Strings ueberein, wird die Funktion mit der Rueckgabe von 
       * "true" verlassen. 
       * 
       * Stimmen die beiden Strings nicht ueberein, wird mit dem zweiten Vergleichsstring
       * weitergemacht.
       */
      if ( pVerglString1 != null )
      {
        if ( pString.equalsIgnoreCase( pVerglString1 ) )
        {
          return true;
        }
      }

      /*
       * Pruefung: Zweiter Vergleichsstring gesetzt ?
       * 
       * Ist der zweite Vergleichsstring gesetzt, wird "pString" mit diesem verglichen.
       * Das Ergebnis der Funktion "equalsIgnoreCase" wird zurueckgegeben. 
       */
      if ( pVerglString2 != null )
      {
        return pString.equalsIgnoreCase( pVerglString2 );
      }
    }

    return false;
  }

  /**
   * @param pString der zu pruefende String 
   * @param pVerglString1 der erste Vergleichsstring
   * @param pVerglString2 der optional zweite Vergleichsstring 
   * @param pVerglString3 der optional dritte Vergleichsstring
   * @return TRUE, wenn pString mit dem ersten, zweiten oder dritten String uebereinstimmt, sonst FALSE
   */
  public static boolean equalsIgnoreCase( String pString, String pVerglString1, String pVerglString2, String pVerglString3 )
  {
    /*
     * Pruefung: Parameter "pString" ungleich "null" ? 
     * 
     * Ist der Parameter "pString" gleich "null", ist kein String zum vergleichen vorhanden.
     * Der Aufrufer bekommt in diesem Fall "false" zurueck.
     */
    if ( pString != null )
    {
      /*
       * Pruefung: Erster Vergleichsstring gesetzt ?
       * Ist der erste Vergleichsstring gesetzt, wird dieser mit "pString" verglichen. 
       * Stimmen die beiden Strings ueberein, wird die Funktion mit der Rueckgabe von 
       * "true" verlassen. 
       * 
       * Stimmen die beiden Strings nicht ueberein, wird mit dem zweiten Vergleichsstring
       * weitergemacht.
       */
      if ( pVerglString1 != null )
      {
        if ( pString.equalsIgnoreCase( pVerglString1 ) )
        {
          return true;
        }

        // bei false muss noch der zweite String verglichen werden
      }

      /*
       * Pruefung: Zweiter Vergleichsstring gesetzt ?
       */
      if ( pVerglString2 != null )
      {
        if ( pString.equalsIgnoreCase( pVerglString2 ) )
        {
          return true;
        }

        // bei false muss noch der dritte String verglichen werden
      }

      /*
       * Pruefung: Dritter Vergleichsstring gesetzt ?
       * 
       * Ist der dritte Vergleichsstring gesetzt, wird "pString" mit diesem verglichen.
       * Das Ergebnis der Funktion "equalsIgnoreCase" wird zurueckgegeben. 
       */
      if ( pVerglString3 != null )
      {
        return pString.equalsIgnoreCase( pVerglString3 );
      }
    }

    return false;
  }

  /**
   * @param pString der erste String
   * @param pVerglString1 der zweite String
   * @return pString.compareTo( pVerglString1 ) == 0;
   */
  public static boolean istGleich( String pString, String pVerglString1 )
  {
    if ( pString != null )
    {
      if ( pVerglString1 != null )
      {
        return pString.compareTo( pVerglString1 ) == 0;
      }
    }

    return false;
  }

  public static boolean istGleich( String pString, String pVerglString1, String pVerglString2 )
  {
    if ( pString != null )
    {
      if ( pVerglString1 != null )
      {
        if ( pString.compareTo( pVerglString1 ) == 0 )
        {
          return true;
        } // bei false muss noch der zweite String verglichen werden
      }

      if ( pVerglString2 != null )
      {
        return pString.compareTo( pVerglString2 ) == 0;
      }
    }

    return false;
  }

  public static boolean istGleich( String pString, String pVerglString1, String pVerglString2, String pVerglString3 )
  {
    if ( pString != null )
    {
      if ( pVerglString1 != null )
      {
        if ( pString.compareTo( pVerglString1 ) == 0 )
        {
          return true;
        }

        // bei false muss noch der zweite String verglichen werden
      }

      if ( pVerglString2 != null )
      {
        if ( pString.compareTo( pVerglString2 ) == 0 )
        {
          return true;
        }

        // bei false muss noch der dritte String verglichen werden
      }

      if ( pVerglString3 != null )
      {
        return pString.compareTo( pVerglString3 ) == 0;
      }
    }

    return false;
  }

  public static boolean istGleich( String pString, String pVerglString1, String pVerglString2, String pVerglString3, String pVerglString4 )
  {
    if ( pString != null )
    {
      if ( pVerglString1 != null )
      {
        if ( pString.compareTo( pVerglString1 ) == 0 )
        {
          return true;
        }

        // bei false muss noch der zweite String verglichen werden
      }

      if ( pVerglString2 != null )
      {
        if ( pString.compareTo( pVerglString2 ) == 0 )
        {
          return true;
        }

        // bei false muss noch der dritte String verglichen werden
      }

      if ( pVerglString3 != null )
      {
        if ( pString.compareTo( pVerglString3 ) == 0 )
        {
          return true;
        }
      }

      if ( pVerglString4 != null )
      {
        return pString.compareTo( pVerglString4 ) == 0;
      }
    }

    return false;
  }

  public static boolean istGleich( String pString, String pVerglString1, String pVerglString2, String pVerglString3, String pVerglString4, String pVerglString5 )
  {
    if ( pString != null )
    {
      if ( pVerglString1 != null )
      {
        if ( pString.compareTo( pVerglString1 ) == 0 )
        {
          return true;
        }

        // bei false muss noch der zweite String verglichen werden
      }

      if ( pVerglString2 != null )
      {
        if ( pString.compareTo( pVerglString2 ) == 0 )
        {
          return true;
        }

        // bei false muss noch der dritte String verglichen werden
      }

      if ( pVerglString3 != null )
      {
        if ( pString.compareTo( pVerglString3 ) == 0 )
        {
          return true;
        }

        // bei false muss noch der dritte String verglichen werden
      }

      if ( pVerglString4 != null )
      {
        if ( pString.compareTo( pVerglString4 ) == 0 )
        {
          return true;
        }
      }

      if ( pVerglString5 != null )
      {
        return pString.compareTo( pVerglString4 ) == 0;
      }
    }

    return false;
  }

  /**
   * @param pString1 der erste String
   * @param pString2 der zweite String
   * @return pString1.compareTo( pString2 ) != 0
   */
  public static boolean istUngleich( String pString1, String pString2 )
  {
    return pString1.compareTo( pString2 ) != 0;
  }

  /**
   * ACHTUNG: Es erfolgt keine Pruefung auf null-Pointer-Zugriff
   * @param pEingabe der zu vergleichende String 
   * @param pString1 der erste Vergleichsstring
   * @param pString2 der zweite Vergleichsstring
   * @return ( pEingabe ungleich pString1 ) and ( pEingabe ungleich pString2 ) 
   */
  public static boolean istUngleich( String pEingabe, String pString1, String pString2 )
  {
    if ( ( pEingabe.equalsIgnoreCase( pString1 ) ) || ( pEingabe.equalsIgnoreCase( pString2 ) ) )
    {
      return false; // Der String ist mit String 1 oder String 2 identisch
    }

    return true; // Eingabe ist nicht mit einem der beiden Vergleichsstring uebereinstimmend
  }

  /**
   * @param pString der zu zentrierende String
   * @param pBreite die laenge der Breite (Aufruf von pBreite.length()) 
   * @return den ausgerichteten String
   */
  public static String arMittig( String pString, String pBreite )
  {
    return arMittig( pString, pBreite.length() );
  }

  /**
   * Erstellt einen String mit der uebergebenen Laenge mit einer Zentrierung des uebergebenen Strings.
   * 
   * @param pString der anzuzeigende Inhalt
   * @param pAnzahlStellen die Breite des Zielstrings
   * @return den ausgerichteten String
   */
  public static String arMittig( String pString, int pAnzahlStellen )
  {
    if ( pString == null )
    {
      pString = "";
    }

    if ( pString.length() >= pAnzahlStellen )
    {
      return pString;
    }

    StringBuffer str_ergebnis = new StringBuffer();

    int rest_zeichen_anzahl = (int) ( ( pAnzahlStellen - pString.length() ) / 2.0 );

    int rest_zeichen_zaehler = rest_zeichen_anzahl;

    while ( rest_zeichen_zaehler > 0 )
    {
      str_ergebnis.append( LEERZEICHEN );

      rest_zeichen_zaehler--;
    }

    str_ergebnis.append( pString );

    rest_zeichen_zaehler = rest_zeichen_anzahl;

    while ( rest_zeichen_zaehler > 0 )
    {
      str_ergebnis.append( LEERZEICHEN );

      rest_zeichen_zaehler--;
    }

    if ( str_ergebnis.length() < pAnzahlStellen )
    {
      str_ergebnis.append( LEERZEICHEN );
    }

    return str_ergebnis.toString();
  }

  public static String arMittigCsv( String pEinzug, String pText, int pAnzahlStellen, String pCsvTrennzeichen )
  {
    StringBuffer str_ergebnis = new StringBuffer();

    String csv_akt_string = null; // aktuell zu verarbeitender CSV-String
    String csv_trennzeichen = null; // das ermittelte Trennzeichen 
    boolean knz_weiterer_schleifendurchlauf = true; // Kennzeichen ob ein weiterer Schleifendurchlauf notwendig ist
    boolean knz_place_new_zeile = false; // Kennzeichen ob eine New-Line-Zeilen hinzugefuegt werden soll
    int aktuelle_startposition = 0; // die akutelle Start-Leseposition
    int csv_zaehler = 0; // Zaehler fuer Vermeidung von Endlosschleifen
    int naechste_position = 0; // Position des naechsten gefundenen Trennzeichens

    try
    {
      /* 
       * Pruefung: pCsvIds ungleich Leerstring ?
       */
      if ( ( pText != null ) && ( pText.length() > 0 ) )
      {
        /* 
         * Trennzeichen setzen
         */
        csv_trennzeichen = "\n";

        if ( pCsvTrennzeichen != null )
        {
          csv_trennzeichen = pCsvTrennzeichen;
        }

        /* 
         * Die Suchschleife laeuft solange wie...
         * ... die Variable "knz_weiterer_schleifendurchlauf" auf TRUE steht
         * ... der CSV-Zaehler noch kleiner als 32123 ist
         */
        while ( ( knz_weiterer_schleifendurchlauf ) && ( csv_zaehler <= 32123 ) )
        {
          /* 
           * Naechste Position Trennzeichen
           * Ab der aktuellen Startposition wird die naechste Position des Trennzeichens gesucht
           */
          naechste_position = pText.indexOf( csv_trennzeichen, aktuelle_startposition );

          /* 
           * Pruefung: Trennzeichen gefunden ?
           */
          if ( naechste_position >= 0 )
          {
            /* 
             * CSV-String lesen
             * Wurde eine naechste Position gefunden, wird der naechste CSV-String gelesen.
             * Das ist der Teilstring ab der aktuellen Startposition bis zur Fundstelle des
             * naechsten Trennzeichens.
             */
            csv_akt_string = pText.substring( aktuelle_startposition, naechste_position );

            /* 
             * Startposition aktualisieren
             * Die naechste aktuelle Startposition liegt ab der Fundstellt zuzueglich der
             * Laenge des Trennzeichens ( hier = Zeilenumbruchzeichens )
             */
            aktuelle_startposition = naechste_position + csv_trennzeichen.length();
          }
          else
          {
            /* 
             * Schleifendurchlauf beenden
             * Wurde kein weiteres Trennzeichen gefunden, ist in der Variablen
             * "naechste_position" ein Wert von -1 enthalten. Das ist der Hinweis, dass
             * die While-Schleife nicht nochmal durchlaufen werden muss. Die Variable
             * "knz_weiterer_schleifendurchlauf" wird auf FALSE gestellt.
             */
            knz_weiterer_schleifendurchlauf = false;

            /* 
             * Pruefung: Noch ungelesener Teilstring vorhanden ?
             * 
             * Dieses ist der Fall, wenn die letzte Leseposition kleiner
             * gleich der Stringlaenge ist. Von der letzten Leseposition 
             * wird bis zum Stringende die aktuelle Zeile ermittelt.
             * 
             * Ist das nicht der Fall, wird die letzte Startposition auf -1 gestellt.
             * Dieses ist der Hinweis, dass es keine aktuelle Zeile gibt und der
             * Inhalt der Variablen "csv_akt_string" nicht verarbeitet werden darf.
             */
            if ( aktuelle_startposition <= pText.length() )
            {
              csv_akt_string = pText.substring( aktuelle_startposition, pText.length() );
            }
            else
            {
              aktuelle_startposition = -1;
            }
          }

          /* 
           * Pruefung: aktuelle_startposition >= 0 ?
           * 
           * Nur wenn das der Fall ist, darf der Inhalt der Variablen "csv_akt_string" verwendet werden.
           */
          if ( aktuelle_startposition >= 0 )
          {
            if ( knz_place_new_zeile )
            {
              str_ergebnis.append( "\n" );
            }

            str_ergebnis.append( pEinzug );

            if ( csv_akt_string == null )
            {
              str_ergebnis.append( "" );
            }
            else if ( csv_akt_string.length() >= pAnzahlStellen )
            {
              /*
               * CSV-Zeile breiter als Gesamtzeilenbreite
               * Ist der aktuelle CSV-String laenger als die vorgegebene Zeilenbreite, kann 
               * der CSV-String nicht zentriert werden. 
               * 
               * Der aktuelle CSV-String wird dem Ergebnis insgesamt hinzugefuegt.
               */
              str_ergebnis.append( csv_akt_string );
            }
            else
            {
              StringBuffer str_akt_csv_string = new StringBuffer();

              /*
               * Rest-Zeichen
               * Die Laenge vom aktuellem CSV-String wird von der Gesamtzeilenbreite abegezogen.
               * Das Ergebnis wird durch 2 geteilt. 
               */
              int rest_zeichen_anzahl = (int) ( ( pAnzahlStellen - csv_akt_string.length() ) * 0.5 );

              /*
               * Vorlaufende Leerzeichen hinzufuegen 
               */
              int rest_zeichen_zaehler = rest_zeichen_anzahl;

              while ( rest_zeichen_zaehler > 0 )
              {
                str_akt_csv_string.append( LEERZEICHEN );

                rest_zeichen_zaehler--;
              }

              /*
               * Den aktuellen CSV-String hinzufuegen 
               */
              str_akt_csv_string.append( csv_akt_string );

              /*
               * Nachlaufende Leerzeichen hinzufuegen 
               */
              rest_zeichen_zaehler = rest_zeichen_anzahl;

              while ( rest_zeichen_zaehler > 0 )
              {
                str_akt_csv_string.append( LEERZEICHEN );

                rest_zeichen_zaehler--;
              }

              if ( str_akt_csv_string.length() < pAnzahlStellen )
              {
                str_ergebnis.append( " " );
              }

              str_ergebnis.append( str_akt_csv_string.toString() );

              knz_place_new_zeile = true;
            }
          }

          /* 
           * Endlosschleifenverhinderungszaehler weiterstellen
           */
          csv_zaehler = csv_zaehler + 1;
        }
      }
    }
    catch ( Exception err_inst )
    {
      FkLogger.wl( "Fehler ", err_inst );
    }

    return str_ergebnis.toString();
  }

  /**
   * Erstellt einen String mit der uebergebenen Laenge mit einer Linksausrichtung des uebergebenen Strings.
   * 
   * @param pString der Inhalt
   * @param pAnzahlStellen die Breite des Zielstrings
   * @return den ausgerichteten String
   */
  public static String arLinks( String pString, int pAnzahlStellen )
  {
    if ( pString == null )
    {
      pString = "";
    }

    if ( pString.length() >= pAnzahlStellen )
    {
      return pString;
    }

    StringBuffer str_ergebnis = new StringBuffer( pAnzahlStellen );

    str_ergebnis.append( pString );

    int rest_zeichen_zaehler = pAnzahlStellen - pString.length();

    while ( rest_zeichen_zaehler > 0 )
    {
      str_ergebnis.append( LEERZEICHEN );

      rest_zeichen_zaehler--;
    }

    return str_ergebnis.toString();
  }

  /**
   * @param pEingabe die Eingabe
   * @param pPraefix die Zeichenfolge welche vor dem Inhalt der neuen Zeile hinzugefuegt werden soll
   * @param pSuffix die Zeichenfolge welche nach dem Inhalt der neuen Zeile hinzugefuegt werden soll
   * @return den gewandelten Eingabestring mit Zeilenumbruechen
   */
  public static String stringNewLine( String pEingabeString, String pPraefix, String pSuffix )
  {
    return pPraefix + FkString.replace( pEingabeString, "\n", pSuffix + pPraefix ) + pSuffix;
  }

  /**
   * @param pEingabeString der zu modifizierende Eingabestring 
   * @param pPraefix der Praefix, welcher vor jeder Zeile eingefuegt wird
   * @param pSuffix der Suffix, welcher nach jeder Zeile eingefuegt wird
   * @param pKnzZeilenumbruchHinzufuegen Kennzeichen, ob ein Zeilenumbruch hinzugefuegt werden soll
   * @return den sich ergebenden String 
   */
  public static String stringNewLine( String pEingabeString, String pPraefix, String pSuffix, boolean pKnzZeilenumbruchHinzufuegen )
  {
    if ( pEingabeString == null )
    {
      return pEingabeString;
    }

    StringBuffer str_ergebnis = new StringBuffer();

    String str_zeilen_umbruch = "\n";

    int aktuelle_position = pEingabeString.indexOf( str_zeilen_umbruch );

    /*
     * Wird die Suchzeichenfolge nicht gefunden, ist die Rueckgabe der Eingabestring.
     * (Es gibt nichts zu ersetzen!)
     */
    if ( aktuelle_position == -1 )
    {
      return pEingabeString;
    }

    int laenge_suche_nach = str_zeilen_umbruch.length();
    int letzte_position = 0;

    String akt_sub_string = "";

    while ( ( aktuelle_position > -1 ) && ( letzte_position <= aktuelle_position ) )
    {
      /*
       * Aus der Eingabe die Zeichen von der letzten Fundstelle bis zur
       * aktuellen Fundstelle kopieren.
       */
      if ( letzte_position < aktuelle_position )
      {
        akt_sub_string = pEingabeString.substring( letzte_position, aktuelle_position );

        if ( akt_sub_string != null )
        {
          str_ergebnis.append( pPraefix );
          str_ergebnis.append( akt_sub_string );
          str_ergebnis.append( pSuffix );

          if ( pKnzZeilenumbruchHinzufuegen )
          {
            str_ergebnis.append( str_zeilen_umbruch );
          }
        }
      }

      /*
       *
       */
      letzte_position = aktuelle_position + laenge_suche_nach;

      aktuelle_position = pEingabeString.indexOf( str_zeilen_umbruch, letzte_position );
    }

    if ( letzte_position <= pEingabeString.length() ) // bleibt noch ein Reststring uebrig?
    {
      akt_sub_string = pEingabeString.substring( letzte_position );

      if ( akt_sub_string != null )
      {
        str_ergebnis.append( pPraefix );
        str_ergebnis.append( akt_sub_string );
        str_ergebnis.append( pSuffix );

        if ( pKnzZeilenumbruchHinzufuegen )
        {
          str_ergebnis.append( str_zeilen_umbruch );
        }
      }
    }

    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * Liefert von pString alle Zeichen bis zum ersten Vorkommen des Trennzeichens von rechts.
   * 
   * FkString.right( "ABC.DEF.GHI.JKL",   "." ) = JKL
   * FkString.right( "ABC.DEF.GHI.JKL", "DEF" ) = ".GHI.JKL"
   * FkString.right( "ABC.DEF.GHI.JKL",   "A" ) = "BC.DEF.GHI.JKL"
   * FkString.right( "ABC.DEF.GHI.JKL",  "KL" ) = ""
   * 
   * Trennzeichen ist null oder wird nicht gefunden = Rueckgabe Eingabestring
   * 
   * FkString.right( "ABC.DEF.GHI.JKL", "XYZ" ) = "ABC.DEF.GHI.JKL"
   * FkString.right( "ABC.DEF.GHI.JKL",  null ) = "ABC.DEF.GHI.JKL"
   *  
   * </pre>
   * 
   * @param pString der String 
   * @param pTrennzeichen das Trennzeichen (oder Trennstring)
   * @return den sich ergebenden String bis zur Fundstelle des Trennzeichens
   */
  public static String right( String pString, String pTrennzeichen )
  {
    /*
     * Es wird die Variable "pos_trennzeichen" deklariert und 
     * mit -1 vorbelegt. 
     */
    int pos_trennzeichen = -1;

    /*
     * Pruefung: Sind beide Parameter ungleich "null" ?
     * 
     * Wenn beide Parameter ungleich "null" sind, wird die 
     * letzte Position des Trennzeichens gesucht. Das 
     * Ergebnis wird in der Variablen "pos_trennzeichen"
     * gespeichert.
     */
    if ( ( pString != null ) && ( pTrennzeichen != null ) )
    {
      pos_trennzeichen = pString.lastIndexOf( pTrennzeichen );
    }

    /*
     * Wurde das Trennzeichen nicht gefunden, kann das Ergebnis nur 
     * der EingabeString sein. Ist der Eingabestring selber null 
     * erhaelt der Aufrufer "null" zurueck.
     */
    if ( pos_trennzeichen == -1 )
    {
      return pString;
    }

    /*
     * Wurde das Trennzeichen gefunden 
     */
    return pString.substring( pos_trennzeichen + pTrennzeichen.length(), pString.length() );
  }

  /**
   * <pre>
   * Liefert von pString alle Zeichen bis zum ersten Vorkommen des Trennzeichens von rechts.
   * Das Trennzeichen muss vorhanden sein.
   * 
   * Ist das Trennzeichen in "pString" nicht vorhanden, wird null zurueckgegeben.
   * 
   * FkString.rightX( "ABC.DEF",  "." ) = "DEF"
   * FkString.rightX( "ABC.DEF",  "=" ) = null = Trennzeichen nicht in Eingabe vorhanden
   * 
   * FkString.rightX(      null,  "." ) = null = pString ist null
   * FkString.rightX( "ABC.DEF", null ) = null = Trennzeichen ist null
   * FkString.rightX(      null, null ) = null = keine Eingaben
   * </pre>
   * 
   * @param pString der String 
   * @param pTrennzeichen das Trennzeichen (oder Trennstring)
   * @return den sich ergebenden String bis zur Fundstelle des Trennzeichens
   */
  public static String rightX( String pString, String pTrennzeichen )
  {
    /*
     * Es wird die Variable "pos_trennzeichen" deklariert und 
     * mit -1 vorbelegt. 
     */
    int pos_trennzeichen = -1;

    /*
     * Pruefung: Sind beide Parameter ungleich "null" ?
     * 
     * Wenn beide Parameter ungleich "null" sind, wird die 
     * letzte Position des Trennzeichens gesucht. Das 
     * Ergebnis wird in der Variablen "pos_trennzeichen"
     * gespeichert.
     */
    if ( ( pString != null ) && ( pTrennzeichen != null ) )
    {
      pos_trennzeichen = pString.lastIndexOf( pTrennzeichen );
    }

    /*
     * Wurde das Trennzeichen nicht gefunden, wird "null" zurueckgegeben. 
     */
    if ( pos_trennzeichen == -1 )
    {
      return null;
    }

    return pString.substring( pos_trennzeichen + pTrennzeichen.length(), pString.length() );
  }

  /**
   * <pre>
   * Fuehrt im Endeffekt "pString.substring( pAbStelle )" aus, behandelt aber Sonderfaelle.
   * 
   * FkString.rightLeft( "ABC.DEF.GHI.JKL", 7  ) = .GHI.JKL
   * 
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pAbStelle die Stelle von links, ab welcher abgeschnitten werden soll
   * @return den Reststring ab der vorgegebenen Stelle 
   */
  public static String rightLeft( String pString, int pAbStelle )
  {
    if ( pString == null )
    {
      return pString; // kein String, kein Ergebnis
    }

    if ( pAbStelle <= 0 )
    {
      return pString; // kleiner gleich 0 ist der Eingabestring selber
    }

    if ( pString.length() < pAbStelle )
    {
      return ""; // die Ab-Position liegt hinter dem Stringende
    }

    return pString.substring( pAbStelle );
  }

  /**
   * <pre>
   * Ermittelt einen Teilstring von Rechts bis zum ersten Auftreten des Trennzeichens von links.
   * Das Trennzeichen selber ist nicht Bestandteil des Ergebnisses.
   * 
   * Ist pString null, wird null zurueckgegeben.
   * Ist das Trennzeichen null wird pString zurueckgegeben.
   * 
   * FkString.rightLeft( "ABC.DEF.GHI.JKL",    "."  ) =  DEF.GHI.JKL
   * FkString.rightLeft( "ABC++DEF++GHI++JKL", "++" ) =  DEF++GHI++JKL = Trennzeichen ist 2 Stellen lang
   * FkString.rightLeft( "ABC++",              "++" ) =  ""            = Leerstring, wenn Trennzeichen = Stringende ist
   *  
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pTrennzeichen das definierende Trennzeichen fuer die Stelle von links, ab welcher abgeschnitten werden soll
   * @return den Teilstring ab der berechneten Stelle
   */
  public static String rightLeft( String pString, String pTrennzeichen )
  {
    if ( pString == null )
    {
      return ""; // kein String, kein Ergebnis
    }

    if ( pTrennzeichen == null )
    {
      return pString;
    }

    int erste_position_trennzeichen = pString.indexOf( pTrennzeichen );

    /*
     * Wurde das Trennzeichen nicht gefunden, kann das Ergebnis 
     * nur der EingabeString sein.
     */
    if ( erste_position_trennzeichen == -1 )
    {
      return pString;
    }

    /*
     * Das Ergebnis liegt nach dem Trennzeichen. Es wird geprueft, 
     * ob die Fundstelle zuzueglich der Laenge des Trennzeichens noch 
     * ein Rueckgabeergebnis ergibt. 
     */
    if ( pString.length() < ( erste_position_trennzeichen + pTrennzeichen.length() ) )
    {
      return ""; // die Ab-Position liegt hinter dem Stringende
    }

    return pString.substring( erste_position_trennzeichen + pTrennzeichen.length() );
  }

  /**
   * <pre>
   * Schneided von "pString" von links ab, bis zur letzten Position
   * von "pTrennzeichen".
   * 
   * Das Trennzeichen ist nicht auf eine Stelle begrenzt. 
   * Das Trennzeichen kann auch ein Suchstring sein.
   * 
   * Ist der Eingabestring "null", wird "null" zurueckgegeben. 
   * Ist das Trennzeichen "null", wird "pString" zurueckgegeben.
   * 
   * Wird das Trennzeichen nicht gefunden, wird "pString" zurueckgeben.
   * 
   * FkString.leftRight( "ABC.DEF.GHI.JKL",   "." ) = "ABC.DEF.GHI"
   * 
   * FkString.leftRight( "ABC.DEF.GHI.JKL", "GHI" ) = "ABC.DEF."
   * 
   * FkString.leftRight( "ABC.DEF.GHI.JKL", "ABC" ) = ""
   * 
   * FkString.leftRight( "ABC.DEF.GHI.JKL",    "" ) = "ABC.DEF.GHI.JKL"
   * 
   * FkString.leftRight( "ABC.DEF.GHI.JKL",  null ) = "ABC.DEF.GHI.JKL"
   * 
   * FkString.leftRight( null,                "." ) = "null"
   * 
   * </pre>
   * 
   * @param pString der String
   * @param pTrennzeichen das Trennzeichen (oder Suchstring)
   * @return den sich ergebenden String 
   */
  public static String leftRight( String pString, String pTrennzeichen )
  {
    /*
     * Parameterpruefung
     * "pString" und "pTrennzeichen" muessen gesetzt sein
     */
    if ( ( pString != null ) && ( pTrennzeichen != null ) )
    {
      /*
       * Position des Trennzeichens in pString ermitteln
       */
      int letzte_position_trennzeichen = pString.lastIndexOf( pTrennzeichen );

      /*
       * Wurde das Trennzeichen gefunden, wird bis zu 
       * der Position des Trennzeichens das Ergebnis aus 
       * pString herausgeschnitten und zurueckgegeben. 
       */
      if ( letzte_position_trennzeichen >= 0 )
      {
        return pString.substring( 0, letzte_position_trennzeichen );
      }
    }

    /*
     * Standardrueckgabe
     * Wurde das Trennzeichen nicht gefuden, oder einer 
     * der beiden Parameter "null" sein, wird "pString" 
     * zurueckgegeben.
     */
    return pString;
  }

  /**
   * <pre>
   * Schneidet vom uebergebenen String von links Zeichen bis zur angegebenen Position von Rechts ab.
   * 
   * FkString.leftRight( "ABC.DEF",  0 ) = "ABC.DEF" 
   * FkString.leftRight( "ABC.DEF",  1 ) = "ABC.DE"
   * FkString.leftRight( "ABC.DEF",  2 ) = "ABC.D"
   * FkString.leftRight( "ABC.DEF",  6 ) = "A" 
   * FkString.leftRight( "ABC.DEF",  7 ) = ""
   * FkString.leftRight( "ABC.DEF", -1 ) = "ABC.DEF" 
   * FkString.leftRight( "ABC.DEF", 90 ) = ""
   * FkString.leftRight(      null, 90 ) = null
   * </pre>
   * 
   * @param pString der String 
   * @param pBisRPos Stelle von Rechts bis zu der abgeschnitten werden soll
   * @return der sich ergebende String, null wenn die Eingabe selber null ist
   */
  public static String leftRight( String pString, int pBisRPos )
  {
    /*
     * Parameterpruefung
     * Der Parameter "pString" muss gesetzt sein und mindestens ein Zeichen 
     * beinhalten (ohne Trim).
     * 
     * Der Parameter "pBisRPost" muss groesser als 0 sein.
     */
    if ( ( pString != null ) && ( pString.length() > 0 ) && ( pBisRPos > 0 ) )
    {
      /*
       * Rueckgabe Leerstring?
       * Ist die Anzahl der abzuschneidenden Zeichen von Rechts groesser als 
       * die Laenge der Eingabe, bleibt kein Zeichen der Eingabe uebrig. Das 
       * Ergebnis ist ein Leerstring.
       */
      if ( pBisRPos >= pString.length() )
      {
        return "";
      }

      /*
       * Rueckgabe Teilstring
       * Die Rueckgabe bestimmt sich aus der Laenge von pString abzueglich der 
       * Stellen von pBisRPos.
       */
      return pString.substring( 0, pString.length() - pBisRPos );
    }

    /*
     * Rueckgabe von pString
     * Ist pString selber null           = Von Nichts kann nichts abgeschnitten werden. 
     * Ist die pBisRPos kleiner gleich 0 = 0-Stellen von Rechts bzw. noch weiter nach Rechts im Negativ-Fall
     */
    return pString;
  }

  /**
   * <pre>
   * Schneided vom uebergebenen String von links ab, bis zur Position des ersten
   * Auftretens von pTrennzeichen von links.
   * 
   * Wird das Trennzeichen nicht gefunden, wird der gesamte String wieder zurueckgegeben.
   * 
   * FkString.left( "ABC.DEF.GHI.JKL",   "." ) = "ABC"
   * FkString.left( "ABC.DEF.GHI.JKL", "GHI" ) = "ABC.DEF."
   * FkString.left( "ABC.DEF.GHI.JKL", "ABC" ) = ""
   * FkString.left( "ABC.DEF.GHI.JKL",    "" ) = ""
   * 
   * FkString.left( "ABC.DEF.GHI.JKL",  null ) = "ABC.DEF.GHI.JKL"
   * 
   * FkString.left(              null,   "." ) = "null"
   * </pre>
   * 
   * @param pString der String
   * @param pTrennzeichen das Trennzeichen
   * @return der String bis zum ersten Auftretens von "pTrennzeichen" 
   */
  public static String left( String pString, String pTrennzeichen )
  {
    /*
     * Parameterpruefung
     * "pString" und "pTrennzeichen" muessen gesetzt sein
     */
    if ( ( pString != null ) && ( pTrennzeichen != null ) )
    {
      /*
       * Position des Trennzeichens in pString ermitteln
       */
      int position_trennzeichen = pString.indexOf( pTrennzeichen );

      /*
       * Wurde das Trennzeichen gefunden, wird bis zu 
       * der Position des Trennzeichens das Ergebnis aus 
       * pString herausgeschnitten und zurueckgegeben. 
       */
      if ( position_trennzeichen >= 0 )
      {
        return pString.substring( 0, position_trennzeichen );
      }
    }

    /*
     * Standardrueckgabe
     * Wurde das Trennzeichen nicht gefuden, oder einer 
     * der beiden Parameter "null" sein, wird "pString" 
     * zurueckgegeben.
     */
    return pString;
  }

  /**
   * <pre>
   * Ermittelt den Teilstring zwischen den beiden Trennzeichen 1 und 2. 
   * 
   * FkString.subString( "ABC.DEF.GHIJ.KLM",  ".",  "." ) = DEF
   * FkString.subString( "ABC.DEF.GHIJ.KLM", "-.", ".-" ) = ABC.DEF.GHIJ.KLM
   * </pre>
   * 
   * @param pString die Eingabezeichenfolge
   * @param pTrennzeichen1 der erste von links zu suchende String
   * @param pTrennzeichen2 der zweite String, welcher ab der ersten Fundstelle gesucht wird
   * @return einen String von der ersten Fundstelle bis zur zweiten Fundstelle, oder den gesamten String, sofern die Trennzeichen null sind
   */
  public static String subString( String pString, String pTrennzeichen1, String pTrennzeichen2 )
  {
    /*
     * Pruefung: Parameter "pString" gesetzt?
     * 
     * Ist der Parameter "pString" gleich "null", wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Position AB
     * Es wird eine Variable deklariert, in welcher die Startposition
     * fuer den Teilstring gespeichert wird. 
     * 
     * Diese Variable wird initial auf 0 eingestellt (=erstes Zeichen der Eingabe)
     */
    int position_ab = 0;

    /*
     * Position BIS
     * Es wird eine Variable deklariert, in welcher die Endposition
     * fuer den Teilstring gespeichert wird. 
     * 
     * Diese Variable wird initial auf -1 eingestellt 
     */
    int position_bis = -1;

    /*
     * Pruefung: Trennzeichen 1 ungleich "null" ?
     */
    if ( pTrennzeichen1 != null )
    {
      position_ab = pString.indexOf( pTrennzeichen1, 0 );

      if ( position_ab < 0 )
      {
        position_ab = 0;
      }
      else
      {
        position_ab = position_ab + pTrennzeichen1.length();
      }
    }

    if ( pTrennzeichen2 != null )
    {
      position_bis = pString.indexOf( pTrennzeichen2, position_ab );
    }

    if ( position_bis < 0 )
    {
      position_bis = pString.length();
    }

    return pString.substring( position_ab, position_bis );
  }

  /**
   * <pre>
   * Sucht nach den Trennzeichn in der Eingabe und gibt im Ergfolgsfall den String 
   * bis zur Trennzeichenposition zurueck. 
   * 
   * Die Suche nach den Trennzeichen beginnt von links.
   * Es wird beim ersten auftreten des eines Trennzeichens getrennt.
   * 
   * Werden die Trennzeichen nicht gefunden, wird die Eingabe zurueckgegeben.
   * 
   * Ist die Eingabe null, wird null zurueckgegeben.
   * 
   * FkString.leftString( "aaaa?b=1&c=2",   '/', '?' ) = "aaaa"
   * FkString.leftString( "aaaa/?b=1&c=2",  '/', '?' ) = "aaaa"
   * 
   * FkString.leftString( "aaaa/?b=1&c=2",  '?', '?' ) = "aaaa/"
   * FkString.leftString( "aaaa/?b=1&c=2",  '-', '-' ) = "aaaa/?b=1&c=2" = Trennzeichen nicht in Eingabe vorhanden
   * FkString.leftString( "/aaaa/?b=1&c=2", '/', '?' ) = ""              = Trennzeichen an erster Stelle vorhanden 
   * FkString.leftString( "aaaaa?b=1&c=2/", '/', '/' ) = "aaaaa?b=1&c=2" = Trennzeichen an letzter Stelle vorhanden
   * FkString.leftString( null,             '/', '?' ) = null            = Eingabestring ist null
   *
   * </pre>
   * 
   * @param pEingabe der Eingabestring
   * @param pTrennzeichen1 das erste Trennzeichen
   * @param pTrennzeichen2 das zweite Trennzeichen
   * @return wurde ein Trennzeichen gefunden, wird ein String bis zum Trennzeichen zurueckgegeben.
   *         Wird kein Trennzeichen gefunden, wird die Eingabe zurueckgegeben.
   */
  public static String leftString( String pEingabe, char pTrennzeichen1, char pTrennzeichen2 )
  {
    /*
     * Pruefung: pEingabe ungleich null ?
     * Die Routine wird nur gestartet, wenn die Eingabe gesetzt ist. 
     */
    if ( pEingabe != null )
    {
      int anzahl_zeichen_eingabe = pEingabe.length();

      /*
       * Leseposition
       * Die Variable "position_trennzeichen" ist die Index-Position im Eingabestring. 
       * Die Index-Position beginnt bei 0. 
       */
      int position_trennzeichen = 0;

      /*
       * While-Schleife
       * Die While-Schleife laeuft solange, wie es noch Zeichen im Eingabestring gibt.
       * Es wird nach dem ersten "/" oder dem ersten "?" gesucht.
       */
      while ( position_trennzeichen < anzahl_zeichen_eingabe )
      {
        /*
         * Aktuelles Zeichen aus der Eingabe lesen
         */
        char akt_char = pEingabe.charAt( position_trennzeichen );

        /*
         * Zeichenpruefung: Ist das aktuelle Zeichen ein "/" oder ein "?"
         * Ist dem so, wird der Ergebnisstring bis zur Trennzeichenpostion
         * dem Aufrufer zurueckgegeben.
         */
        if ( ( akt_char == pTrennzeichen1 ) || ( akt_char == pTrennzeichen2 ) )
        {
          return pEingabe.substring( 0, position_trennzeichen );
        }

        /*
         * Leseprozess ein Zeichen weiter stellen 
         */
        position_trennzeichen++;
      }
    }

    /*
     * Trennzeichen wurden nicht gefunden. 
     * Die Rueckgabe ist der gesamte Eingabestring
     */
    return pEingabe;
  }

  /**
   * <pre>
   * Wandelt den uebergebenen String in Grossbuchstaben um.
   *  
   * Bei einer Uebergabe von null wird null zurueckgegeben.
   * 
   * Fuehrt im Endeffekt "pString.toUpperCase()" aus
   * </pre>
   * 
   * @param pString die zu wandelnde Zeichenkette
   * @return der String in Grossbuchstaben, oder null
   */
  public static String ucase( String pString )
  {
    if ( pString != null )
    {
      return pString.toUpperCase();
    }

    return null;
  }

  /**
   * <pre>
   * Wandelt den uebergebenen String in Kleinbuchstaben um.
   *  
   * Bei einer Uebergabe von null wird null zurueckgegeben.
   * 
   * Fuehrt im Endeffekt "pString.toLowerCase()" aus
   * </pre>
   * 
   * @param pString die zu wandelnde Zeichenkette
   * @return der String in Kleinbuchstaben, oder null
   */
  public static String lcase( String pString )
  {
    if ( pString != null )
    {
      return pString.toLowerCase();
    }

    return null;
  }

  /**
   * Wandelt den ersten Buchstaben in einen Grossbuchstaben und haengt den Rest in Kleinbuchstaben hinten dran.
   * 
   * @param pString die Zeichenfolge
   * @return einen String bei welchem der erste Buchstabe gross ist
   */
  public static String getErstenBuchstabenGrossDannKlein( String pString )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist "pString" gleich "null", wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Pruefung: "pString" gleich Leerstring ?
     * 
     * Ist "pString" ein Leerstring, wird ein Leerstring zurueckgegeben.
     */
    if ( pString.length() == 0 )
    {
      return LEERSTRING;
    }

    /*
     * Pruefung: Laenge von "pString" gleich 1 ?
     * 
     * In diesem Fall wird "pString.toUpperCase()" zurueckgegeben.
     */
    if ( pString.length() == 1 )
    {
      return pString.toUpperCase();
    }

    /*
     * Standardrueckgabe ist, dass das erste Zeichen von "pString" 
     * in einen Grossbuchstaben gewandelt wird und der Reststring
     * angehaengt wird. 
     */
    return pString.substring( 0, 1 ).toUpperCase() + pString.substring( 1 ).toLowerCase();
  }

  /**
   * Wandelt den ersten Buchstaben in einen Grossbuchstaben und haengt den Rest hinten dran.
   * 
   * @param pString die Zeichenfolge
   * @return einen String bei welchem der erste Buchstabe gross ist
   */
  public static String getErstenBuchstabenGross( String pString )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist "pString" gleich "null", wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Pruefung: "pString" gleich Leerstring ?
     * 
     * Ist "pString" ein Leerstring, wird ein Leerstring zurueckgegeben.
     */
    if ( pString.length() == 0 )
    {
      return LEERSTRING;
    }

    /*
     * Pruefung: Laenge von "pString" gleich 1 ?
     * 
     * In diesem Fall wird "pString.toUpperCase()" zurueckgegeben.
     */
    if ( pString.length() == 1 )
    {
      return pString.toUpperCase();
    }

    /*
     * Standardrueckgabe ist, dass das erste Zeichen von "pString" 
     * in einen Grossbuchstaben gewandelt wird und der Reststring
     * angehaengt wird. 
     */
    return pString.substring( 0, 1 ).toUpperCase() + pString.substring( 1 );
  }

  /**
   * Wandelt den ersten Buchstaben in einen Kleinbuchstaben und haengt den Rest hinten dran.
   * 
   * @param pString die Zeichenfolge
   * @return einen String bei welchem der erste Buchstabe klein ist
   */
  public static String getErstenBuchstabenKlein( String pString )
  {
    /*
     * Pruefung: "pString" gleich "null" ?
     * 
     * Ist "pString" gleich "null", wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Pruefung: "pString" gleich Leerstring ?
     * 
     * Ist "pString" ein Leerstring, wird ein Leerstring zurueckgegeben.
     */
    if ( pString.length() == 0 )
    {
      return LEERSTRING;
    }

    /*
     * Pruefung: Laenge von "pString" gleich 1 ?
     * 
     * In diesem Fall wird "pString.toLowerCase()" zurueckgegeben.
     */
    if ( pString.length() == 1 )
    {
      return pString.toLowerCase();
    }

    /*
     * Standardrueckgabe ist, dass das erste Zeichen von "pString" 
     * in einen Kleinbuchstaben gewandelt wird und der Reststring
     * angehaengt wird. 
     */
    return pString.substring( 0, 1 ).toLowerCase() + pString.substring( 1 );
  }

  /**
   * @param pString der String 
   * @return einen XML-Namen in Grossbuchstaben
   */
  public static String getXmlTagName( String pString )
  {
    return ucase( getKlartext( pString, '_' ) );
  }

  /**
   * <pre> 
   * Erstellt aus der Eingabe einen Camel-Case String
   * 
   * FkString.getStringCamel( " das ist mein camel case string  "        ) = "DasIstMeinCamelCaseString"  
   * FkString.getStringCamel( "DasWarSchonCamelCase"                     ) = "DasWarSchonCamelCase" 
   * FkString.getStringCamel( "Wiederholung Gross Buchstabe ABC mit xyz" ) = "WiederholungGrossBuchstabeAbcMitXyz"  
   * FkString.getStringCamel( "ABC123 ohne xyz456"                       ) = "Abc123OhneXyz456"  
   * FkString.getStringCamel( "kleinGross"                               ) = "KleinGross"  
   * FkString.getStringCamel( "A1B2C3D4E5F6"                             ) = "A1B2C3D4E5F6"
   * FkString.getStringCamel( "Accc _ Aeee +  Accc -- Aeee"              ) = "AcccAeeeAcccAeee"
   * FkString.getStringCamel( "MEIN_XML_TAG"                             ) = "MeinXmlTag"
   * FkString.getStringCamel( "Ärger  Öde  Uebel"                        ) = "AergerOedeUebel" 
   * </pre>
   * 
   * @param pString der String
   * @return einen String bei welchen Gross und Kleinbuchstaben vorhanden sind
   */
  public static String getStringCamel( String pString )
  {
    return getCamelCase( pString );
  }

  public static String getCamelCase( String pString )
  {
    /*
     * Parameterpruefung
     * pString = wenn null oder keine Zeichen vorhanden sind, wird null zurueckgegeben 
     */
    if ( pString == null )
    {
      return null;
    }

    if ( pString.trim().length() == 0 )
    {
      return null;
    }

    pString = FkString.replaceSpecialCharacters( pString );

    StringBuffer str_ergebnis = new StringBuffer();

    char aktuelles_zeichen = ' ';

    int zaehler = 0;
    boolean knz_letztes_zeichen_war_grossbuchstabe = false;
    boolean knz_forciere_kleinbuchstabe = false;
    boolean knz_next_zeichen_gross = true;
    boolean knz_hinzfuegen = false;

    /*
     * Schleife ueber die gesamte Laenge des Eingabestrings 
     */
    while ( zaehler < pString.length() )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe bestimmen
       */
      aktuelles_zeichen = pString.charAt( zaehler );

      /*
       * Kleinbuchstaben
       * Flag zum hinzufuegen setzen und forcierung von Kleinbuchstaben aufheben 
       */
      if ( aktuelles_zeichen >= 'a' && aktuelles_zeichen <= 'z' )
      {
        knz_hinzfuegen = true;

        knz_forciere_kleinbuchstabe = false;
      }

      /* 
       * Grossbuchstaben
       * Flag zum hinzufuegen und Flag fuer Grossbuchstabe setzen
       * 
       * War das letzte hinzugefuegte Zeichen selber schon ein Grossbuchstabe,
       * wird das Flag fuer das Forcieren von Kleinbuchstaben gesetzt.
       */
      else if ( aktuelles_zeichen >= 'A' && aktuelles_zeichen <= 'Z' )
      {
        knz_hinzfuegen = true;

        knz_next_zeichen_gross = true;

        if ( knz_letztes_zeichen_war_grossbuchstabe )
        {
          knz_forciere_kleinbuchstabe = true;
        }
      }

      /* 
       * Zahlen
       * Nur Flag zum hinzufuegen setzen
       */
      else if ( ( aktuelles_zeichen >= '0' ) && ( aktuelles_zeichen <= '9' ) )
      {
        knz_hinzfuegen = true;
      }

      /* 
       * Sonstige Zeichen 
       * Bei allen sonstigen Zeichen wird das Hinzufuegen-Flag auf False gesetzt.
       */
      else
      {
        knz_hinzfuegen = false;

        /* 
         * Definierte Sonderzeichen
         * Bei diesen Sonderzeichen wird der nachfolgend aufgenommene Buchstabe
         * ein Grossbuchstabe. Die Variable "knz_next_zeichen_gross" wird dazu
         * auf True gestellt.
         * 
         * Die Forcierung von Kleinbuchstaben wird aufgehoben (= Flag auf FALSE gesetzt).
         */
        if ( ( aktuelles_zeichen == '_' ) || ( aktuelles_zeichen == ' ' ) || ( aktuelles_zeichen == '-' ) || ( aktuelles_zeichen == '(' ) || ( aktuelles_zeichen == ')' ) )
        {
          knz_next_zeichen_gross = true;

          knz_forciere_kleinbuchstabe = false;
        }
      }

      /* 
       * Pruefung: aktuelles Zeichen hinzufuegen
       */
      if ( knz_hinzfuegen )
      {
        /* 
         * Pruefung: Kleinbuchstaben forcieren
         * Ist dem so, wird das aktuelle Zeichen in einen Kleinbuchstaben
         * umgewandelt. Desweiteren wird die Variable "knz_next_zeichen_gross" 
         * auf FALSE gestellt. Solange Kleinbuchstaben forciert werden sollen,
         * wird damit ein evtl. falsches Kennzeichen ausgenullt. Wuerde es nicht
         * gemacht werden, gaebe es einen Fehler bei z.B. TTest = TtEst.
         */
        if ( knz_forciere_kleinbuchstabe )
        {
          aktuelles_zeichen = Character.toLowerCase( aktuelles_zeichen );
          knz_next_zeichen_gross = false;
        }
        /* 
         * Pruefung: naechstes Zeichen als Grossbuchstabe
         */
        else if ( knz_next_zeichen_gross )
        {
          /* 
           * Weitere Bedingung fuer einen Grossbuchstaben ist, dass das zuletzt hinzugefuegte 
           * Zeichen kein Grossbuchstabe war (sonst stehen 2 Grossbuchstaben hintereinander).
           * Ist diese Bedingung erfuellt, wird das aktuelle Zeichen in einen Grossbuchstaben
           * umgewandelt.
           */
          if ( knz_letztes_zeichen_war_grossbuchstabe == false )
          {
            aktuelles_zeichen = Character.toUpperCase( aktuelles_zeichen );
          }

          /*
           * Flagvariable "knz_next_zeichen_gross" selber wird auf FALSE gestellt. Dieses
           * unabhaengig davon, ob das aktuelle Zeichen in einen Grossbuchstaben gewandelt wurde. 
           */
          knz_next_zeichen_gross = false;
        }

        /* 
         * Aufbau Ergebnis
         * Das Zeichen aus der Variablen "aktuelles_zeichen" wird dem Ergebnis-String hinzugefuegt.
         */
        str_ergebnis.append( aktuelles_zeichen );

        /* 
         * Das schlussendlich hinzugefuegte Zeichen, bestimmt den Wert fuer
         * die Variable "knz_letztes_zeichen_war_grossbuchstabe"
         */
        knz_letztes_zeichen_war_grossbuchstabe = ( aktuelles_zeichen >= 'A' && aktuelles_zeichen <= 'Z' );
      }

      /*
       * Zaehler um eine Position weiterstellen und mit dem naechsten Zeichen 
       * weiter machen, oder aber die Schleife beenden.
       */
      zaehler++;
    }

    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * Zieht einen Text mit Camel-Case oder Unterstrichen auseinander. 
   * 
   * FkString.getKlartext( "EinCamelCaseText"                  ) = "Ein Camel Case Text"
   * FkString.getKlartext( "MEIN_XML_TAG"                      ) = "Mein Xml Tag" 
   * FkString.getKlartext( "EinWeitererTextMitZahlen123"       ) = "Ein Weiterer Text Mit Zahlen 123"
   * FkString.getKlartext( " das ist mein camel case string  " ) = "Das Ist Mein Camel Case String"
   * 
   * Behandlung von Zahlen:
   * FkString.getKlartext( "Eins2Drei4Fuenf67AchtNeun10"       ) = "Eins 2 Drei 4 Fuenf 67 Acht Neun 10"
   * 
   * </pre>
   * 
   * @param pString der Text
   * @return einen Text mit Leerzeichen als Trenner
   */
  public static String getKlartext( String pString )
  {
    return getKlartext( pString, " " );
  }

  /**
   * <pre>
   * Zieht einen Text mit Camel-Case oder Unterstrichen auseinander. 
   * </pre>
   * 
   * @param pString der Text
   * @param pTrennZeichen das Trennzeichen fuer die Zwischenraeume
   * @return einen Text mit Leerzeichen als Trenner
   */
  public static String getKlartext( String pString, char pTrennZeichen )
  {
    return getKlartext( pString, "" + pTrennZeichen );
  }

  public static String getKlartext( String pString, String pTrennZeichen )
  {
    /*
     * Parameterpruefung
     * pString = wenn null oder keine Zeichen vorhanden sind, wird null zurueckgegeben 
     */
    if ( pString == null )
    {
      return null;
    }

    if ( pString.trim().length() == 0 )
    {
      return null;
    }

    StringBuffer str_ergebnis = new StringBuffer();

    char aktuelles_zeichen = ' ';
    char letztes_zeichen = ' ';
    String trenn_zeichen = pTrennZeichen;

    int akt_index = 0;
    boolean knz_letztes_zeichen_war_grossbuchstabe = false;
    boolean knz_forciere_kleinbuchstabe = false;
    boolean knz_trennzeichen_einfuegen = false;
    boolean knz_trennzeichen_erlaubt = false;
    boolean knz_next_zeichen_gross = true;
    boolean knz_hinzfuegen = false;

    /*
     * Schleife ueber die gesamte Laenge des Eingabestrings 
     */
    while ( akt_index < pString.length() )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe bestimmen (Zaehlerposition)
       */
      aktuelles_zeichen = pString.charAt( akt_index );

      /*
       * Kleinbuchstaben
       * Flag zum hinzufuegen setzen und forcierung von Kleinbuchstaben aufheben 
       */
      if ( aktuelles_zeichen >= 'a' && aktuelles_zeichen <= 'z' )
      {
        knz_hinzfuegen = true;
        knz_forciere_kleinbuchstabe = false;
      }

      /* 
       * Grossbuchstaben
       * Flag zum hinzufuegen und Flag fuer Grossbuchstabe setzen
       * 
       * War das letzte hinzugefuegte Zeichen selber schon ein Grossbuchstabe,
       * wird das Flag fuer das Forcieren von Kleinbuchstaben gesetzt.
       */
      else if ( aktuelles_zeichen >= 'A' && aktuelles_zeichen <= 'Z' )
      {
        knz_hinzfuegen = true;

        knz_next_zeichen_gross = true;

        if ( knz_letztes_zeichen_war_grossbuchstabe )
        {
          knz_forciere_kleinbuchstabe = true;
        }
      }
      /* 
       * Zahlen
       * Nur Flag zum hinzufuegen setzen
       */
      else if ( ( aktuelles_zeichen >= '0' ) && ( aktuelles_zeichen <= '9' ) )
      {
        knz_hinzfuegen = true;

        /*
         * Trennzeichen vor Zahlen
         * Vor einer Zahl wird ein Trennzeichen eingefuegt, wenn das letzte Zeichen 
         * KEINE Zahl war. Somit wird verhindert, dass vor jeder Zahl ein Trennzeichen 
         * steht. Zahlen sollen zusammenbleiben, aber fuer sich getrennt stehen.
         */
        knz_trennzeichen_einfuegen = !( ( letztes_zeichen >= '0' ) && ( letztes_zeichen <= '9' ) );
      }

      /* 
       * Sonstige Zeichen 
       * Bei allen sonstigen Zeichen wird das Hinzufuegen-Flag auf False gesetzt.
       */
      else
      {
        knz_hinzfuegen = false;

        /* 
         * Definierte Sonderzeichen
         * Bei diesen Sonderzeichen wird der nachfolgend aufgenommene Buchstabe ein Grossbuchstabe.
         * Damit dieses klappt muss die Variable "knz_next_zeichen_gross" auf True gestellt werden.
         * Die Forcierung von Kleinbuchstaben wird aufgehoben (= Flag auf FALSE gesetzt).
         */
        if ( ( aktuelles_zeichen == '_' ) || ( aktuelles_zeichen == ' ' ) || ( aktuelles_zeichen == '-' ) || ( aktuelles_zeichen == '(' ) || ( aktuelles_zeichen == ')' ) )
        {
          knz_next_zeichen_gross = true;
          knz_forciere_kleinbuchstabe = false;
        }
      }

      /* 
       * Pruefung: aktuelles Zeichen hinzufuegen
       */
      if ( knz_hinzfuegen )
      {
        /* 
         * Pruefung: Kleinbuchstaben forcieren
         * Ist dem so, wird das aktuelle Zeichen in einen Kleinbuchstaben umgewandelt.
         * Desweiteren wird die Flagvariable "knz_next_zeichen_gross" auf FALSE gestellt. 
         * Solange Kleinbuchstaben forciert werden sollen, wird damit ein evtl. falsches 
         * Kennzeichen ausgenullt. Wuerde es nicht gemacht werden, gaebe es einen Fehler 
         * bei z.B. TTest = TtEst.
         */
        if ( knz_forciere_kleinbuchstabe )
        {
          aktuelles_zeichen = Character.toLowerCase( aktuelles_zeichen );
          knz_next_zeichen_gross = false;
        }
        /* 
         * Pruefung: naechstes Zeichen als Grossbuchstabe
         */
        else if ( knz_next_zeichen_gross )
        {
          /* 
           * Weitere Bedingung fuer einen Grossbuchstaben ist, dass das zuletzt hinzugefuegte 
           * Zeichen kein Grossbuchstabe war (sonst stehen 2 Grossbuchstaben hintereinander).
           * Ist diese Bedingung erfuellt, wird das aktuelle Zeichen in einen Grossbuchstaben
           * umgewandelt.
           */
          if ( knz_letztes_zeichen_war_grossbuchstabe == false )
          {
            aktuelles_zeichen = Character.toUpperCase( aktuelles_zeichen );
            knz_trennzeichen_einfuegen = true;
          }

          /*
           * Flagvariable "knz_next_zeichen_gross" selber wird auf FALSE gestellt. Dieses
           * unabhaengig davon, ob das aktuelle Zeichen in einen Grossbuchstaben gewandelt wurde. 
           */
          knz_next_zeichen_gross = false;
        }

        /* 
         * Aufbau Ergebnis
         * Pruefung, ob vor dem aktuellen Zeichen ein Trennzeichen eingefuegt werden soll.
         * 
         * Bei dieser Pruefung wird zuerst das Flag fuer die Freischaltung von Trennzeichen 
         * geprueft. Dieses verhindert ein erstes Leerzeichen am Start. Das Flag wird nach 
         * dem ersten hinzugefuegten Zeichen im Ergebnisstring auf TRUE gestellt (=erlaubt)
         * 
         * Die zweite Pruefung greift auf das eigentliche Steuerflag zu. Dieses wird immer 
         * auf TRUE gestellt, wenn ein Grossbuchstabe oder eine Zahl hinzugefuegt 
         * werden soll.
         */
        if ( ( knz_trennzeichen_erlaubt ) && ( knz_trennzeichen_einfuegen ) )
        {
          str_ergebnis.append( trenn_zeichen );
        }

        /* 
         * Aufbau Ergebnis
         * Das Zeichen aus der Variablen "aktuelles_zeichen" wird dem Ergebnis-String hinzugefuegt.
         */
        str_ergebnis.append( aktuelles_zeichen );

        /* 
         * Das schlussendlich hinzugefuegte Zeichen, bestimmt den Wert fuer
         * die Variable "knz_letztes_zeichen_war_grossbuchstabe"
         */
        knz_letztes_zeichen_war_grossbuchstabe = ( aktuelles_zeichen >= 'A' && aktuelles_zeichen <= 'Z' );

        /*
         * Das aktuelle Zeichen wird fuer die weitere Verwendung in der Variablen 
         * "letztes_zeichen" gespeichert (fuer Zahlen).
         */
        letztes_zeichen = aktuelles_zeichen;

        /*
         * Flagvariable fuer das Zulassen von Trennzeichen auf TRUE stellen, da 
         * jetzt mindestens schon ein Zeichen im Ergebnis steht. 
         * 
         * Die Hinzufuege-Flagvariable "knz_trennzeichen_einfuegen" wird fuer den 
         * naechsten Schleifendurchlauf auf FALSE gestellt. 
         */
        knz_trennzeichen_erlaubt = true;
        knz_trennzeichen_einfuegen = false;
      }

      /*
       * Zaehler um eine Position weiterstellen und mit dem naechsten Zeichen 
       * weiter machen.
       */
      akt_index++;
    }

    return str_ergebnis.toString();
  }

  /**
   * Ermittelt die Laenge eines Stringes. Fuehrt im Endeffekt pString.length(); aus, behandelt aber auch NULL-Pointer
   * @param pString der String 
   * @return die Laenge des Stringes, bei einem Fehler 0
   */
  public static int len( String pString )
  {
    // ( str_trenn_string != null ? str_trenn_string.length() : 0 ) 

    if ( pString == null )
    {
      return 0;
    }

    return pString.length();
  }

  public static int len( String pString1, String pString2 )
  {
    return ( pString1 == null ? 0 : pString1.length() ) + ( pString2 == null ? 0 : pString2.length() );
  }

  public static int len( String pString1, String pString2, String pString3 )
  {
    return ( pString1 == null ? 0 : pString1.length() ) + ( pString2 == null ? 0 : pString2.length() ) + ( pString3 == null ? 0 : pString3.length() );
  }

  public static int len( String pString1, String pString2, String pString3, String pString4 )
  {
    return ( pString1 == null ? 0 : pString1.length() ) + ( pString2 == null ? 0 : pString2.length() ) + ( pString3 == null ? 0 : pString3.length() ) + ( pString4 == null ? 0 : pString4.length() );
  }

  public static int len( String pString1, String pString2, String pString3, String pString4, String pString5 )
  {
    return ( pString1 == null ? 0 : pString1.length() ) + ( pString2 == null ? 0 : pString2.length() ) + ( pString3 == null ? 0 : pString3.length() ) + ( pString4 == null ? 0 : pString4.length() ) + ( pString5 == null ? 0 : pString5.length() );
  }

  /**
   * <pre>
   * Ermittelt die Zeichenlaenge der uebergebnen int Zahl
   * </pre>
   * 
   * @param pIntZahl der String 
   * @return ( "" + pIntZahl ).length();
   */
  public static int len( int pIntZahl )
  {
    try
    {
      return ( "" + pIntZahl ).length();
    }
    catch ( Exception err_inst )
    {
      // keine Fehlerbehandlung, da Rueckgabe von 0
    }

    return 0;
  }

  /**
   * Entfernt ungueltige Zeichen aus der Eingabe
   * 
   * @param pEingabe der Eingabesting
   * @return der Eingabestring mit den gewandelten Umlauten 
   */
  public static String getSuchformat( String pEingabe )
  {
    if ( pEingabe == null )
    {
      return "";
    }

    String temp_string = pEingabe.trim().toLowerCase();

    if ( temp_string.length() == 0 )
    {
      return "";
    }

    StringBuffer str_ergebnis = new StringBuffer();

    int akt_index = 0;

    while ( akt_index < temp_string.length() )
    {
      char aktuelles_zeichen = temp_string.charAt( akt_index );

      switch ( aktuelles_zeichen )
      {
        case '-' :
          break;

        case '.' :
          break;

        case '(' :
          break;

        case ')' :
          break;

        case ' ' :
          break;

        case 0x00f6 :
          str_ergebnis.append( "oe" );
          break;

        case 0x00e4 :
          str_ergebnis.append( "ae" );
          break;

        case 0x00fc :
          str_ergebnis.append( "ue" );
          break;

        case 0x00c4 :
          str_ergebnis.append( "Ae" );
          break;

        case 0x00dc :
          str_ergebnis.append( "Ue" );
          break;

        case 0x00df :
          str_ergebnis.append( "ss" );
          break;

        default :
          str_ergebnis.append( aktuelles_zeichen );
      }

      akt_index++;
    }
    return str_ergebnis.toString();
  }

  public static String convertUmlautFehler( String pEingabe )
  {
    /*
     * http://www.excel-ist-sexy.de/unicode-umwandeln/
     */

    if ( pEingabe == null )
    {
      return null;
    }

    StringBuffer str_ergebnis = new StringBuffer();

    int akt_char = 0;
    int next_char = 0;
    int akt_index = 0;
    int len_eingabe = pEingabe.length();

    while ( akt_index < len_eingabe )
    {
      akt_char = (int) pEingabe.charAt( akt_index );

      if ( akt_char == 194 )
      {
        akt_index++;

        if ( akt_index < len_eingabe )
        {
          next_char = (int) pEingabe.charAt( akt_index );

          switch ( next_char )
          {
            case 8364 :
              akt_char = 214;
              break;

            case 165 :
              akt_char = 165;
              break;

            case 163 :
              akt_char = 163;
              break;

            default :
              akt_index--;
          }
        }
      }
      else if ( akt_char == 195 )
      {
        akt_index++;

        if ( akt_index < len_eingabe )
        {

          next_char = (int) pEingabe.charAt( akt_index );

          switch ( next_char )
          {
            case 8222 :
              akt_char = 196;
              break;

            case 8211 :
              akt_char = 246;
              break;

            case 8230 :
              akt_char = 165;
              break;

            case 339 :
              akt_char = 220;
              break;

            case 376 :
              akt_char = 223;
              break;

            case 164 :
              akt_char = 228;
              break;

            case 182 :
              akt_char = 246;
              break;

            case 188 :
              akt_char = 252;
              break;

            default :
              akt_index--;
          }
        }
      }

      str_ergebnis.append( ( (char) akt_char ) );

      akt_index++;
    }

    return str_ergebnis.toString();
  }

  /**
   * Konvertiert die in der Eingabe enthaltenen deutschen Umlaute in OE usw.
   * @param pEingabe die Eingabe
   * @return der konvertierte String 
   */
  public static String replaceSpecialCharacters( String pEingabe )
  {
    if ( pEingabe == null )
    {
      return null;
    }

    StringBuffer str_ergebnis = new StringBuffer();

    int akt_index = 0;

    while ( akt_index < pEingabe.length() )
    {
      switch ( pEingabe.charAt( akt_index ) )
      {
        case '@' :
          str_ergebnis.append( "At" );
          break;

        case 0x00f6 :
          str_ergebnis.append( "oe" );
          break;

        case 0x00e4 :
          str_ergebnis.append( "ae" );
          break;

        case 0x00fc :
          str_ergebnis.append( "ue" );
          break;

        case 0x00d6 :
          str_ergebnis.append( "Oe" );
          break;

        case 0x00c4 :
          str_ergebnis.append( "Ae" );
          break;

        case 0x00dc :
          str_ergebnis.append( "Ue" );
          break;

        case 0x00df :
          str_ergebnis.append( "ss" );
          break;

        default :
          str_ergebnis.append( pEingabe.charAt( akt_index ) );
      }

      akt_index++;
    }
    return str_ergebnis.toString();
  }

  /**
   * Konvertiert die in der Eingabe enthaltenen deutschen Umlaute in OE usw.
   * @param pEingabe die Eingabe
   * @return der konvertierte String 
   */
  public static String replaceSpecialCharactersNeu( String pEingabe )
  {
    if ( pEingabe == null )
    {
      return null;
    }

    char aktuelles_zeichen;

    StringBuffer str_ergebnis = new StringBuffer();

    int akt_index = 0;

    while ( akt_index < pEingabe.length() )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe bestimmen
       */
      aktuelles_zeichen = pEingabe.charAt( akt_index );

      switch ( aktuelles_zeichen )
      {
        case '@' :
          str_ergebnis.append( "At" );
          break;

        case ' ' :
        case '-' :
          str_ergebnis.append( "_" );
          break;

        case 0x00f6 :
          str_ergebnis.append( "oe" );
          break;

        case 0x00e4 :
          str_ergebnis.append( "ae" );
          break;

        case 0x00fc :
          str_ergebnis.append( "ue" );
          break;

        case 0x00d6 :
          str_ergebnis.append( "Oe" );
          break;

        case 0x00c4 :
          str_ergebnis.append( "Ae" );
          break;

        case 0x00dc :
          str_ergebnis.append( "Ue" );
          break;

        case 0x00df :
          str_ergebnis.append( "ss" );
          break;

        default :

          if ( ( aktuelles_zeichen >= 'A' && aktuelles_zeichen <= 'Z' ) || ( aktuelles_zeichen >= 'a' && aktuelles_zeichen <= 'z' ) || ( aktuelles_zeichen >= '0' && aktuelles_zeichen <= '9' ) )
          {
            str_ergebnis.append( aktuelles_zeichen );
          }
      }

      akt_index++;
    }

    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * Fuehrt einen Trim auf den uebergebenen String aus.
   *  
   * Ist der Parameter "pString" null oder ein Leerstring, wird "null" zurueckgeben.
   * 
   * FkString.trimNull( " A B " ) = "A B"
   * 
   * FkString.trimNull( "     " ) = null = Eingabe ist Leerstring
   * FkString.trimNull(    null ) = null = Eingabe ist nicht gesetzt
   * 
   * </pre> 
   * 
   * @param pString der zu trimmende String
   * @return null wenn der String null oder ein Leerstring ist, sonst der getrimmte String
   */
  public static String trimNull( String pString )
  {
    String str_ergebnis_trim = null;

    /*
     * Pruefung: "pString" ungleich "null" ?
     */
    if ( pString != null )
    {
      /*
       * Ist "pString" gesetzt, wird am dort die Funktion "trim" aufgerufen
       */
      str_ergebnis_trim = pString.trim();

      /*
       * Pruefung: Ist das Ergebnis der Trim-Funktion ein Leerstring ?
       * 
       * Ist es nach dem Trim ein Leerstring, wird die 
       * Ergebnisvariable auf "null" gesetzt.
       */
      if ( str_ergebnis_trim.length() == 0 )
      {
        str_ergebnis_trim = null;
      }
    }

    /*
     * Rueckgabe der Ergebnisstrings.
     */
    return str_ergebnis_trim;
  }

  /**
   * <pre>
   * Fuehrt einen Trim auf die gesamte Zeichenlaenge aus.
   * Es werden auch doppelte Leerzeichen innerhalb des Strings entfernt.
   * 
   * FkString.trimX( "    A  B    C  " ) = "A B C"
   * FkString.trimX( "    A  B    C"   ) = "A B C"
   * FkString.trimX( "ABC"             ) = "ABC"
   * FkString.trimX( "      "          ) = ""
   * FkString.trimX( ""                ) = ""
   * FkString.trimX( null              ) = null
   * </pre>
   * 
   * @param pString die Eingabezeichenfolge
   * @return einen String ohne vorlaufende und nachlaufende Leerzeichen und keinen doppelten Leerzeichen zwischen den Zeichen, null wenn die Eingabe selbst null ist.
   */
  public static String trimX( String pString )
  {
    /*
     * Pruefung: Ist "pString" gleich "null" ?
     * 
     * Ist der Eingabestring "null", wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    StringBuffer str_ergebnis = new StringBuffer();

    /* 
     * Variable "letztes_zeichen"
     * Speichert das zuletzt hinzugefuegt Zeichen im Ergebnis. Der Startwert 
     * eines Leerzeichens sorgt dafuer, dass die fuehrenden Leerzeichen entfernt
     * werden. 
     */
    char letztes_zeichen = ' ';

    /* 
     * Variable "aktuelles_zeichen"
     * Speichert das aktuelle Zeichen aus der Eingabezeichenfolge. Der Startwert
     * ist nur wegen der Initialisierung der Variable vorhanden.
     */
    char aktuelles_zeichen = '!';

    /*
     * Schleife Zeichenpruefung
     * Ueber eine While-Schleife wird jedes Zeichen der Eingabe geprueft.
     */
    int akt_index = 0;

    while ( akt_index < pString.length() )
    {
      aktuelles_zeichen = pString.charAt( akt_index );

      /*
       * Pruefung: aktuelles Zeichen ist Leerzeichen
       */
      if ( aktuelles_zeichen == ' ' )
      {
        /* Leerzeichen gefunden
         * Ist das aktuelle Zeichen ein Leerzeichen darf dieses nur dem
         * Ergebnis hinzugefuegt werden, wenn das zuletzt hinzugefuegte 
         * Zeichen ungleich einem Leerzeichen war. 
         * 
         * War das letzte Zeichen ein Leerzeichen, wird das neue Leerzeichen ueberlesen.
         */
        if ( letztes_zeichen != ' ' )
        {
          str_ergebnis.append( aktuelles_zeichen );

          letztes_zeichen = aktuelles_zeichen;
        }
      }
      else
      {
        /*
         * Zeichen ungleich Leerzeichen
         * Alle anderen Zeichen werden dem Ergebnisstring hinzugefuegt. Das aktuelle
         * Zeichen wird in der Variablen "letztes_zeichen" gespeichert.
         */
        str_ergebnis.append( aktuelles_zeichen );

        letztes_zeichen = aktuelles_zeichen;
      }

      akt_index++;
    }

    /* 
     * Abschlusspruefung
     * Pruefung, ob das Ergebnis auf ein Leerzeichen endet.
     * Das Ergebnis endet auf einem Leerzeichen, wenn das letzte hinzugefuegte Zeichen ein Leerzeichen war.
     */
    if ( letztes_zeichen == ' ' )
    {
      if ( str_ergebnis.length() == 0 )
      {
        return "";
      }

      return str_ergebnis.toString().substring( 0, str_ergebnis.length() - 1 );
    }

    return str_ergebnis.toString();
  }

  /**
   * @param pString der zu trimmende String
   * @param pTrimZeichen das noch wegzutrimmende Zeichen (ausser Leerzeichen, darf nur 1 Zeichen umfassen (sonst nur normales Trim))
   * @return den getrimmten String
   */
  public static String trim( String pString, String pTrimZeichen )
  {
    if ( pTrimZeichen != null )
    {
      if ( pTrimZeichen.length() == 1 )
      {
        return trim( pString, pTrimZeichen.charAt( 0 ) );
      }
    }

    return pString.trim();
  }

  /**
   * @param pString der zu trimmende String 
   * @return den getrimmten String, oder null, wenn pString null ist.
   */
  public static String trim( String pString )
  {
    /*
     * Pruefung: Ist "pString" gleich "null" ?
     * 
     * Ist der Eingabestring "null", wird "null" zurueckgegeben.
     */
    if ( pString == null )
    {
      return null;
    }

    /*
     * Ist "pString" gesetzt, wird dort die Funktion "trim" aufgerufen
     * und das Ergebnis dem Aurufer zurueckgegeben.
     */
    return pString.trim();
  }

  /**
   * <pre>
   * Fuehrt einen Trim auf pString aus, beruecksichtigt dabei aber noch das Zeichen 
   * aus dem zweiten Parameter.
   * 
   * FkString.trim( "  ;ABC;  ", ';' ) = "ABC"
   * FkString.trim( "  ;A;    ", ';' ) = "A"
   * FkString.trim( "  ; A ;  ", ';' ) = "A"
   * FkString.trim( "  ;;;    ", ';' ) = ""           = Rueckgabe Leerstring
   * FkString.trim( "ABCDEFGHI", ';' ) = "ABCDEFGHI"  = es gibt nichts zu trimmen
   * FkString.trim( null,        ';' ) = null         = Eingabe ist null = Ergebnis ist null
   * </pre>
   * 
   * @param pString der zu trimmende String
   * @param pTrimZeichen das noch wegzutrimmende Zeichen (ausser Leerzeichen)
   * @return den getrimmten String
   */
  public static String trim( String pString, char pTrimZeichen )
  {
    /*
     * Parameterpruefung
     * pString = darf nicht null sein, wenn doch wird null zurueckgegeben
     */
    if ( pString != null )
    {
      int position_start = 0;

      int position_ende = pString.length();

      char[] char_werte = pString.toCharArray();

      /*
       * Funktionsweise
       * Es laufen zwei Positionszeiger von vorne und von hinten los. Dabei werden
       * alle Leerzeichen und das zuzaetzliche Trimzeichen ueberlesen. Die beiden 
       * Suchschleifen sind beendet, wenn ein anderes als das Leerzeichen oder 
       * Suchzeichen gefunden wird, oder sich die beiden Zeiger kreuzen.
       *  
       * Startposition
       * Die Startposition wird vom Stringanfang gezaehlt. 
       * Die Startposition darf nicht laenger als das Stringende (in position_ende) sein.
       */
      while ( ( position_start < position_ende ) && ( char_werte[ position_start ] == ' ' || char_werte[ position_start ] == pTrimZeichen ) )
      {
        position_start++;
      }

      /*
       * Endposition ermitteln
       * Die Endposition fuer einen Teilstring beginnt am Ende des Eingabestrings und 
       * wird solange nach vorne bewegt, wie noch Leerzeichen und das zusaetzliche 
       * Zeichen gefunden werden. Dabei darf das Ende nicht kleiner als die 
       * Startposition werden.
       */
      while ( ( position_ende > position_start ) && ( char_werte[ position_ende - 1 ] == ' ' || char_werte[ position_ende - 1 ] == pTrimZeichen ) )
      {
        position_ende--;
      }

      char_werte = null;

      /*
       * Pruefung: Teilstring zurueckgeben
       * Liegen der Start oder das Ende nicht mehr an den Initialwerten (Stringgrenzen), 
       * ist die Rueckgabe ein Teilstring der Eingabe. Dieser Teilstring wird 
       * dem Aufrufer zurueckgegeben.
       */
      if ( ( position_start > 0 ) || ( position_ende < pString.length() ) )
      {
        return pString.substring( position_start, position_ende );
      }
    }

    /*
     * Rueckgabe des Eingabestrings
     * Ist der Eingabestring selber null oder es muss kein Teilstring zurueckgegeben 
     * werden, wird die Variable pString selber wieder zurueckgegeben.
     */
    return pString;
  }

  /**
   * <pre>
   * Fuehrt einen Trim auf pString aus, beruecksichtigt dabei aber noch die Zeichen 
   * aus den beiden Parametern.
   * 
   * FkString.trim( " .:ABC.DEF.GHI:. ", ':', '.' ) = "ABC.DEF.GHI"
   * FkString.trim( "ABC.DEF.GHI",       ':', '.' ) = "ABC.DEF.GHI"
   * FkString.trim( " .:B",              ':', '.' ) = "B"
   * FkString.trim( " .::. ",            ':', '.' ) = ""
   * FkString.trim( " .:ABC.DEF.GHI:. ", ':', '.' ) = "ABC.DEF.GHI"
   * 
   * FkString.trim(   "",                ':', '.' ) = ""
   * FkString.trim( null,                ':', '.' ) = null
   * </pre>
   * 
   * @param pString der zu trimmende String
   * @param pTrimZeichen das noch wegzutrimmende Zeichen (ausser Leerzeichen)
   * @return den getrimmten String
   */
  public static String trim( String pString, char pTrimZeichen1, char pTrimZeichen2 )
  {
    /*
     * Parameterpruefung
     * pString = darf nicht null sein, wenn doch wird null zurueckgegeben
     */
    if ( pString != null )
    {
      int position_start = 0;

      int position_ende = pString.length();

      char[] char_werte = pString.toCharArray();

      /*
       * Funktionsweise
       * Es laufen zwei Positionszeiger von vorne und von hinten los. Dabei werden alle 
       * Leerzeichen und das zuzaetzliche Trimzeichen ueberlesen. Die beiden Suchschleifen 
       * sind beendet, wenn ein anderes als das Leerzeichen oder Suchzeichen gefunden 
       * wird, oder sich die beiden Zeiger kreuzen.
       *  
       * Startposition
       * Die Startposition wird vom Stringanfang gezaehlt. 
       * Die Startposition darf nicht laenger als das Stringende (in position_ende) sein.
       */
      while ( ( position_start < position_ende ) && ( char_werte[ position_start ] == ' ' || char_werte[ position_start ] == pTrimZeichen1 || char_werte[ position_start ] == pTrimZeichen2 ) )
      {
        position_start++;
      }

      /*
       * Endposition ermitteln
       * Die Endposition fuer einen Teilstring beginnt am Ende des Eingabestrings und 
       * wird solange nach vorne bewegt, wie noch Leerzeichen und das zusaetzliche 
       * Zeichen gefunden werden. Dabei darf das Ende nicht kleiner als die 
       * Startposition werden.
       */
      while ( ( position_ende > position_start ) && ( char_werte[ position_ende - 1 ] == ' ' || char_werte[ position_ende - 1 ] == pTrimZeichen1 || char_werte[ position_ende - 1 ] == pTrimZeichen2 ) )
      {
        position_ende--;
      }

      char_werte = null;

      /*
       * Pruefung: Teilstring zurueckgeben
       * Liegen der Start oder das Ende nicht mehr an den Initialwerten (Stringgrenzen), 
       * ist die Rueckgabe ein Teilstring der Eingabe. Dieser Teilstring wird 
       * dem Aufrufer zurueckgegeben.
       */
      if ( ( position_start > 0 ) || ( position_ende < pString.length() ) )
      {
        return pString.substring( position_start, position_ende );
      }
    }

    /*
     * Rueckgabe des Eingabestrings
     * Ist der Eingabestring selber null oder es muss kein Teilstring zurueckgegeben 
     * werden, wird die Variable pString selber wieder zurueckgegeben.
     */
    return pString;
  }

  public static String trimT( String pString )
  {
    /*
     * Parameterpruefung
     * pString = darf nicht null sein, wenn doch wird null zurueckgegeben
     */
    if ( pString != null )
    {
      int position_start = 0;

      int position_ende = pString.length();

      char[] char_werte = pString.toCharArray();

      /*
       * Endposition ermitteln
       * Die Endposition fuer einen Teilstring beginnt am Ende des Eingabestrings und 
       * wird solange nach vorne bewegt, wie noch Leerzeichen und das zusaetzliche 
       * Zeichen gefunden werden. Dabei darf das Ende nicht kleiner als die 
       * Startposition werden.
       */
      while ( ( position_ende > position_start ) && ( char_werte[ position_ende - 1 ] == ' ' ) )
      {
        position_ende--;
      }

      char_werte = null;

      /*
       * Pruefung: Teilstring zurueckgeben
       * Liegen der Start oder das Ende nicht mehr an den Initialwerten (Stringgrenzen), 
       * ist die Rueckgabe ein Teilstring der Eingabe. Dieser Teilstring wird 
       * dem Aufrufer zurueckgegeben.
       */
      if ( ( position_start > 0 ) || ( position_ende < pString.length() ) )
      {
        return pString.substring( position_start, position_ende );
      }
    }

    /*
     * Rueckgabe des Eingabestrings
     * Ist der Eingabestring selber null oder es muss kein Teilstring zurueckgegeben 
     * werden, wird die Variable pString selber wieder zurueckgegeben.
     */
    return pString;
  }

  /**
   * <pre>
   * Loescht aus der Eingabe die Zeichenfolge "pStart", sofern "pString" mit dieser Zeichenfolge beginnt.
   * 
   * FkString.trimStart( "m_kv", "m_" ) = "kv"
   * 
   * Die Startzeichenfolge wird nur einmal entfernt:
   * FkString.trimStart( "ABC.ABC.ABC.DEF.GHI", "ABC." ) = "ABC.ABC.DEF.GHI"
   *  
   * Gross- Kleinschreibung wird beachtet: 
   * FkString.trimStart( "ABC.ABC.ABC.DEF.GHI", "abc." ) = "ABC.ABC.ABC.DEF.GHI"
   *  
   * pString = pStart
   * FkString.trimStart( "ABC.ABC.ABC.DEF.GHI", "ABC.ABC.ABC.DEF.GHI" ) = ""
   *   
   * FkString.trimStart( "ABC.ABC.ABC.DEF.GHI", null ) = "ABC.ABC.ABC.DEF.GHI" = kein pStart angegeben
   * FkString.trimStart( null, "ABC.ABC.ABC.DEF.GHI" ) = null                  = kein pString angegeben
   * </pre>
   * 
   * @param pString der eventuell zu kuerzende String
   * @param pStart der String, welcher eventuell einmalig zu entfernen ist.
   * @return pString ohne der Startzeichenfolge (Die Startzeichenfolge wird nur einmal entfernt).
   */
  public static String trimStart( String pString, String pStart )
  {
    /*
     * Der zu untersuchende String muss gesetzt sein 
     */
    if ( ( pString != null ) && ( pString.trim().length() > 0 ) )
    {
      /*
       * Die zu pruefende Startzeichenfolge muss gesetzt sein 
       */
      if ( ( pStart != null ) && ( pStart.trim().length() > 0 ) )
      {
        /*
         * Die Startzeichenfolge muss kleiner als der zu untersuchende String sein.
         */
        if ( pStart.length() <= pString.length() )
        {
          /*
           * Pruefung: beginnt pString mit pStart?
           */
          if ( pString.startsWith( pStart ) )
          {
            /*
             * Beginnt pString mit pStart, bekommt der Aufrufer den gekuerzten String zurueck. 
             */
            return pString.substring( pStart.length() );
          }
        }
      }
    }

    /*
     * Sollte ein Fehler in den Vorgaben vorliegen, oder aber  "pString" nicht 
     * mit "pStart" beginnen, bekommt der Aufrufer pString zurueck.
     */
    return pString;
  }

  /**
   * <pre>
   * Trimmt alle Vorkommen des Trimzeichens am Stringstart weg.
   * 
   * Leerzeichen werden nicht wegetrimmt.
   * 
   * Besteht die Eingabe nur aus Zeichen des Trimmzeichens, wird 
   * ein Leerstring zurueckgegeben.
   * 
   * FkString.trimStartX( "000123", '0' ) = "123"
   * FkString.trimStartX( "001000", '0' ) = "1000"
   * FkString.trimStartX( "000123", '9' ) = "000123" = Trimzeichen nicht im String
   * FkString.trimStartX( "000123", '1' ) = "000123" = Eingabe startet nicht mit Trimzeichen
   * FkString.trimStartX( "123456", '0' ) = "123456" = Trimzeichen nicht gefunden
   * FkString.trimStartX( " 00123", '0' ) = " 00123" = erstes Zeichen ist ein Leerzeichen
   * FkString.trimStartX( "000000", '0' ) = ""       = alle Zeichen werden weggetrimmt
   * FkString.trimStartX(       "", '0' ) = ""       = Eingabe ist Leerstring
   * FkString.trimStartX(     null, '0' ) = null     = der zu trimmende String ist null
   * </pre>
   * 
   * @param pString der zu trimmende String
   * @param pTrimZeichen das zu entfernende Zeichen am Start
   * @return den getrimmten String. Null, wenn die Eingabe null ist.
   */
  public static String trimStartX( String pString, char pTrimZeichen )
  {
    /*
     * Parameterpruefung
     * Ist "pString" null, bekommt der Aufrufer "null" zurueck.
     */
    if ( pString != null )
    {
      int position_start = 0;

      char[] char_werte = pString.toCharArray();

      /*
       * While-Schleife, laeuft solange wie
       * ... die Startposition noch nicht am Stringende angekommen ist
       * ... das Zeichen an der Startposition gleich dem Trimzeichen ist.
       */
      while ( ( position_start < pString.length() ) && ( char_werte[ position_start ] == pTrimZeichen ) )
      {
        position_start++;
      }

      char_werte = null;

      /*
       * Pruefung: Rueckgabe Leerstring ?
       * Ist die Postion plus 1 gleich der Laenge des Eingabestrings, 
       * bleibt von der Eingabe kein Zeichen mehr uebrig. In diesem 
       * Fall wird ein Leerstring zurueck gegeben.
       */
      if ( position_start + 1 == pString.length() )
      {
        return "";
      }

      /*
       * Pruefung: Startposition groesser 0 ?
       */
      if ( position_start > 0 )
      {
        return pString.substring( position_start );
      }
    }

    /*
     * Rueckgabe des Eingabestrings
     * Ist der Eingabestring selber null oder es muss kein Teilstring zurueckgegeben 
     * werden, wird die Variable pString selber wieder zurueckgegeben.
     */
    return pString;
  }

  /**
   * <pre>
   * Trimmt einen String mehrmalig weg
   * 
   * FkString.trimString( "ABC.ABC.ABC.DEF.GHI", "ABC." ) = "DEF.GHI"
   * </pre>
   * 
   * @param pString der zu pruefende String
   * @param pStart der String, mit dem geprueft wird
   * @return den sich ergebenden String
   */
  public static String trimString( String pString, String pStart )
  {
    /*
     * Parameterpruefung
     */
    if ( ( pString != null ) && ( pStart != null ) )
    {
      int trim_string_eingabe_len = pStart.length();

      int position_ende = pString.length();

      if ( trim_string_eingabe_len <= position_ende )
      {
        int position_trennzeichen = 0;

        int position_start = 0;

        char[] trim_zeichen = pStart.toCharArray();

        char[] char_werte = pString.toCharArray();

        while ( ( position_start < position_ende ) && ( char_werte[ position_start ] == trim_zeichen[ position_trennzeichen ] ) )
        {
          position_trennzeichen++;

          if ( position_trennzeichen >= trim_string_eingabe_len )
          {
            position_trennzeichen = 0;
          }

          position_start++;
        }

        trim_zeichen = null;

        char_werte = null;

        /*
         * Pruefung: Teilstring zurueckgeben
         * Liegt der Start oder das Ende nicht mehr an deren Initialwerten (Stringgrenzen), 
         * ist die Rueckgabe ein Teilstring der Eingabe. Dieser Teilstring wird 
         * dem Aufrufer zurueckgegeben.
         */
        if ( ( position_start >= 0 ) || ( position_ende < pString.length() ) )
        {
          return pString.substring( position_start, position_ende );
        }
      }
    }

    /*
     * Rueckgabe des Eingabestrings
     * Ist der Eingabestring selber null oder es muss kein Teilstring zurueckgegeben 
     * werden, wird die Variable pString selber wieder zurueckgegeben.
     */
    return pString;
  }

  /**
   * <pre>
   * Zaehlt die Anzahl der Vorkommen vom Suchstring im ersten Parameter. 
   * 
   * Es werden keine geschachtelten Vorkommen gezaehlt.
   * 
   * Es werden keine Leerstrings gezaehlt.
   * 
   * FkString.getAnzahl( " A   A   A  B  A ", "A"     ) = 4
   * FkString.getAnzahl( " A   A   A  B  A ", "B"     ) = 1
   * FkString.getAnzahl( " A   A   A  B  A ", "C"     ) = 0 = C nicht enthalten
   * FkString.getAnzahl( " A   A   A  B  A ", "A  "   ) = 3 = A mit zwei Leerzeichen
   * FkString.getAnzahl( " A   A   A  B  A ", "A   A" ) = 1 = keine geschachtelten Vorkommen
   *   
   * FkString.getAnzahl( " A   A   A  B  A ", " A   A   A  B  A " ) = 1 = pString gleich pSuchString
   *
   * FkString.getAnzahl(    "", " A " ) = 0 = pString ist ein Leerstring
   * FkString.getAnzahl( " A ",    "" ) = 0 = pSuchString ist ein Leerstring
   * FkString.getAnzahl(    "",    "" ) = 0 = beide Parameter sind ein Leerstring
   * 
   * FkString.getAnzahl(  null, " A " ) = 0 = pString nicht angegeben 
   * FkString.getAnzahl( " A ",  null ) = 0 = pSuchString nicht angegeben
   * FkString.getAnzahl(  null,  null ) = 0 = beide Parameter nicht angegeben
   *   
   * </pre>
   * 
   * @param pString der zu durchsuchende String
   * @param pSuchString der Suchstring, welcher gesucht (=gezaehlt) wird
   * @return die Anzahl der Vokommen von pSuchString in pString
   */
  public static int getAnzahl( String pString, String pSuchString )
  {
    int zaehler_vorkommen = 0; // Anzahl der gezaehlten Vorkommen ist initial 0 

    /*
     * Pruefung der Eingabeparameter
     * pString     = muss gesetzt sein und mindestens 1 Zeichen haben (kein Trim)
     * pSuchString = muss gesetzt sein 
     */
    if ( ( pString != null ) && ( pString.length() > 0 ) && ( pSuchString != null ) )
    {
      /* 
       * Laenge der Suchzeichenfolge
       * Die Laenge der Such-Zeichenfolge wird der aktuellen Startposition hinzugezaehlt.
       */
      int laenge_such_string = pSuchString.length();

      /* 
       * Pruefung: Suchstring = Leerstring
       * Bei einer Suche nach einem Leerstring wuerde es zu einer Endlosschleife kommen.
       * Um das zu verhindern, darf die Schleife nur bei einem Suchstring mit mehr
       * als 0 Zeichen gestartet werden.
       */
      if ( laenge_such_string > 0 )
      {
        /*
         * Startposition bestimmen
         * Die aktuelle Fundstelle des Suchstringes wird ermittelt. Ist die initialie
         * Fundstelle -1, wird die weitere Suchschleife nicht gestartet. 
         */
        int aktuelle_position = pString.indexOf( pSuchString );

        /* Suchschleife
         * Solange der Suchstring noch gefunden wird, ist die Variable "aktuelle_position" 
         * groesser als 0. Der Zaehler der Vorkommen wird erhoeht und anschliessend die 
         * neue aktuelle Position des Suchstrings ermittelt. 
         */
        while ( aktuelle_position >= 0 )
        {
          zaehler_vorkommen++;

          aktuelle_position = pString.indexOf( pSuchString, aktuelle_position + laenge_such_string );
        }
      }
    }

    /*
     * Zum Schluss wird dem Aufrufer die Variable mit den gezaehlten Vorkommen 
     * zurueckgegeben. Bei Parameterfehlern ist diese Variable 0.
     */
    return zaehler_vorkommen;
  }

  /**
   * @param pZeichen das Zeichen
   * @return den Ascii-Wert des Zeichens
   */
  public static int getAscii( char pZeichen )
  {
    return (int) pZeichen;
  }

  /**
   * @param pAscii der Ascii-Code
   * @return das Zeichen fuer den Ascii-Code
   */
  public static char getChar( int pAscii )
  {
    return (char) pAscii;
  }

  /**
   * @param pZeichen das Zeichen
   * @return einen String der Laenge 3 mit den Ascii-Wert des Zeichens
   */
  public static String getAsciiString( char pZeichen )
  {
    /*
     * Zur Zahl 1000 wird der ASCII-Wert des Zeichens hinzugefuegt. Das Ergebnis
     * wird zu einem String gewandelt, von welchem jetzt die Laenge von 4 Zeichen
     * bekannt ist. Von diesem String wird ab der zweiten Position der Substring
     * zurueckgegeben. Dieses bewirkt ein Ergebnis mit 3 Stellen und
     * fuehrenden Nullen.
     */
    return ( "" + ( 1000 + ( (int) pZeichen ) ) ).substring( 1 );
  }


  public static boolean checkBuchstabenUndZahlen( String pEingabe )
  {
    if ( pEingabe == null )
    {
      return false;
    }

    int len_pruef_str = pEingabe.length();

    int akt_index = 0;

    while ( akt_index < len_pruef_str )
    {
      char aktuelles_zeichen = pEingabe.charAt( akt_index );

      /*
       * Bedingungen fuer Zeichen aus dem ABC
       */
      if ( ( aktuelles_zeichen >= 'a' ) && ( aktuelles_zeichen <= 'z' ) )
      {
        // OK
      }
      else if ( ( aktuelles_zeichen >= 'A' ) && ( aktuelles_zeichen <= 'Z' ) )
      {
        // OK
      }
      else if ( ( aktuelles_zeichen >= '0' ) && ( aktuelles_zeichen <= '9' ) )
      {
        // OK
      }
      else if ( ( aktuelles_zeichen == 0x00c4 ) || ( aktuelles_zeichen == 0x00e4 ) || ( aktuelles_zeichen == 0x00d6 ) || ( aktuelles_zeichen == 0x00f6 ) || ( aktuelles_zeichen == 0x00dc ) || ( aktuelles_zeichen == 0x00fc ) || ( aktuelles_zeichen == 0x00df ) )
      {
        // OK
      }
      else
      {
        return false;
      }

      akt_index++;
    }

    /*
     * ... sind alle Pruefungen ueberstanden, wird true zurueckgegeben
     */
    return true;
  }

  public static boolean checkIstKonstantenName( String pEingabe )
  {
    if ( pEingabe == null )
    {
      return false;
    }

    int len_pruef_str = pEingabe.length();

    int akt_index = 0;

    while ( akt_index < len_pruef_str )
    {
      char aktuelles_zeichen = pEingabe.charAt( akt_index );

      if ( ( aktuelles_zeichen == '_' ) )
      {
        // OK
      }
      else if ( ( aktuelles_zeichen >= 'A' ) && ( aktuelles_zeichen <= 'Z' ) )
      {
        // OK
      }
      else if ( ( aktuelles_zeichen >= '0' ) && ( aktuelles_zeichen <= '9' ) )
      {
        // OK
      }
      else
      {
        return false;
      }

      akt_index++;
    }

    /*
     * ... sind alle Pruefungen ueberstanden, wird true zurueckgegeben
     */
    return true;
  }

  /*
   * FkString.checkKombinationGueltigeZeichen( "A1", "A", "1" ) = "true"
   * FkString.checkKombinationGueltigeZeichen( "A0", "A", "1" ) = "false"
   * FkString.checkKombinationGueltigeZeichen( "B1", "A", "1" ) = "false"
   * FkString.checkKombinationGueltigeZeichen( null, "A", "1" ) = "false"
   * FkString.checkKombinationGueltigeZeichen( "A1", null, "1" ) = "false"
   * FkString.checkKombinationGueltigeZeichen( "A1", "A", null ) = "false"
   * FkString.checkKombinationGueltigeZeichen( "ABC123", "ABC", "123" ) = "true"
   * FkString.checkKombinationGueltigeZeichen( "Axyz123", "ABCxyz", "123" ) = "true"
   * FkString.checkKombinationGueltigeZeichen( "A123($[", "ABCxyz", "123($[" ) = "true"
   * FkString.checkKombinationGueltigeZeichen( "A123($]", "ABCxyz", "123($[" ) = "false"
   */

  public static boolean checkKombinationGueltigeZeichen( String pEingabe, String pGueltigeZeichen1, String pGueltigeZeichen2 )
  {
    if ( pEingabe == null )
    {
      return false;
    }

    boolean knz_treffer_gueltige_zeichen_1 = false;

    boolean knz_treffer_gueltige_zeichen_2 = false;

    if ( ( pGueltigeZeichen1 != null ) && ( pGueltigeZeichen2 != null ) )
    {
      int len_pruef_str = pEingabe.length();

      int akt_index = 0;

      while ( akt_index < len_pruef_str )
      {
        String aktuelles_zeichen = "" + pEingabe.charAt( akt_index );

        if ( pGueltigeZeichen1.indexOf( aktuelles_zeichen ) >= 0 )
        {
          knz_treffer_gueltige_zeichen_1 = true;
        }
        else if ( pGueltigeZeichen2.indexOf( aktuelles_zeichen ) >= 0 )
        {
          knz_treffer_gueltige_zeichen_2 = true;
        }
        else
        {
          return false;
        }

        akt_index++;
      }
    }

    return knz_treffer_gueltige_zeichen_1 && knz_treffer_gueltige_zeichen_2;
  }

  public static boolean checkKombinationGueltigeZeichenOpt( String pEingabe, String pGueltigeZeichen1, String pGueltigeZeichen2 )
  {
    if ( pEingabe == null )
    {
      return false;
    }

    boolean knz_treffer_gueltige_zeichen_1 = false;

    boolean knz_treffer_gueltige_zeichen_2 = false;

    if ( ( pGueltigeZeichen1 != null ) && ( pGueltigeZeichen2 != null ) )
    {
      int len_pruef_str = pEingabe.length();

      int akt_index = 0;

      while ( akt_index < len_pruef_str )
      {
        String aktuelles_zeichen = "" + pEingabe.charAt( akt_index );

        if ( pGueltigeZeichen1.indexOf( aktuelles_zeichen ) >= 0 )
        {
          knz_treffer_gueltige_zeichen_1 = true;
        }
        else if ( pGueltigeZeichen2.indexOf( aktuelles_zeichen ) >= 0 )
        {
          knz_treffer_gueltige_zeichen_2 = true;
        }
        else
        {
          return false;
        }

        akt_index++;
      }
    }

    return knz_treffer_gueltige_zeichen_1;
  }

  public static boolean checkGueltigeZeichen( String pEingabe, String pGueltigeZeichen )
  {
    if ( pEingabe == null )
    {
      return false;
    }

    if ( pGueltigeZeichen != null )
    {
      int len_pruef_str = pEingabe.length();

      int akt_index = 0;

      while ( akt_index < len_pruef_str )
      {
        String aktuelles_zeichen = "" + pEingabe.charAt( akt_index );

        /*
         * Das aktuelle Zeichen muss im String der gueltigen Zeichen gefunden werden.
         *  
         * Wird das Zeichen nicht gefunden liefert die "indexOf"-Funktion einen Wert
         * kleiner als 0 zurueck. Ist das der Fall, wird die Funktion mit dem 
         * Rueckgabewert "false" verlassen. 
         */
        if ( pGueltigeZeichen.indexOf( aktuelles_zeichen ) < 0 )
        {
          return false;
        }

        akt_index++;
      }
    }
    /*
     * ... sind alle Pruefungen ueberstanden, wird true zurueckgegeben
     */
    return true;
  }

  /**
   * @param pEingabe
   * @param pUngueltigeZeichen
   * @return TRUE, wenn ein ungueltiges Zeichen gefunden wurde, sonst FALSE (kein ungueltiges Zeichen gefunden)
   */
  public static boolean checkUngueltigeZeichen( String pEingabe, String pUngueltigeZeichen )
  {
    if ( pEingabe == null )
    {
      return false;
    }

    if ( pUngueltigeZeichen != null )
    {
      int len_pruef_str = pEingabe.length();

      int akt_index = 0;

      while ( akt_index < len_pruef_str )
      {
        String aktuelles_zeichen = "" + pEingabe.charAt( akt_index );

        if ( pUngueltigeZeichen.indexOf( aktuelles_zeichen ) >= 0 )
        {
          return true;
        }

        akt_index++;
      }
    }
    /*
     * ... sind alle Pruefungen ueberstanden, wird false zurueckgegeben
     * (es wurden keine un
     */
    return false;
  }

  public static String checkGueltigeZeichenConvert( String pA, String pB )
  {
    boolean fk_erg = checkGueltigeZeichen( pA, pB );

    if ( fk_erg == false )
    {
      return escapeNonLatin( pA );
    }

    return pA;
  }

  private static String escapeNonLatin( CharSequence pCharSequence )
  {
    // http://stackoverflow.com/questions/1273986/converting-utf-8-to-iso-8859-1-in-java

    if ( pCharSequence == null )
    {
      return null;
    }

    StringBuilder str_ergebnis = new StringBuilder();

    int akt_index = 0;

    while ( akt_index < pCharSequence.length() )
    {
      char akt_char = pCharSequence.charAt( akt_index );

      if ( Character.UnicodeBlock.of( akt_char ) == Character.UnicodeBlock.BASIC_LATIN )
      {
        str_ergebnis.append( akt_char );
      }
      else
      {
        str_ergebnis.append( '_' );
      }

      akt_index++;
    }

    return str_ergebnis.toString();
  }


  /**
   * <pre>
   * Erstellt einen String mit einer maximalen Zeilenbreite der Ausgabe.
   * 
   * Ist die aktuelle Zeile aus dem Eingabestring kleiner als die Breite, 
   * wird die Zeile ohne Aenderung uebernommen. 
   * 
   * Ist die aktuelle Zeile laenger als die Breite, wird die aktuelle
   * Zeile in Zeilen der gewuenschten maxmimalen Laenge zerkleinert.
   * 
   * FkString.getStringMaxBreite( "AAAAAAAAAABBBBBBBBBBCCCCCCCCCC\n\n        \nENDE", null, 10, "praefix +", "+ suffix"  ) 
   * 
   * praefix +AAAAAAAAAA+ suffix
   * praefix +BBBBBBBBBB+ suffix
   * praefix +CCCCCCCCCC+ suffix
   * praefix ++ suffix
   * praefix +        + suffix
   * praefix +ENDE+ suffix
   * 
   * 
   * Test: Anzahl Zeilenbreite ist negativ
   * FkString.getStringMaxBreite( "AAAAAAAAAABBBBBBBBBBCCCCCCCCCC\n\n        \nENDE", null, -10, "praefix +", "+ suffix"  )
   * 
   * praefix +AAAAAAAAAABBBBBBBBBBCCCCCCCCCC+ suffix
   * praefix ++ suffix
   * praefix +        + suffix
   * praefix +ENDE+ suffix
   * 
   * 
   * Test: Trennzeichen nicht in Eingabe vorhanden 
   * FkString.getStringMaxBreite( "AAAAAAAAAABBBBBBBBBBCCCCCCCCCCENDE", "trenn_zeichen", 7, " praefix +", "+ suffix" )
   * 
   * praefix +AAAAAAA+ suffix
   * praefix +AAABBBB+ suffix
   * praefix +BBBBBBC+ suffix
   * praefix +CCCCCCC+ suffix
   * praefix +CCENDE+ suffix
   * 
   * Test: Trennzeichen ist eine Zeichenfolge:
   * FkString.getStringMaxBreite( "1 1234|8|2 12343 1234|8|4 1234|8|5 12", "|8|", 6, " praefix +", "+ suffix" ) 
   * 
   * praefix +1 1234+ suffix
   * praefix +2 1234+ suffix
   * praefix +3 1234+ suffix
   * praefix +4 1234+ suffix
   * praefix +5 12+ suffix
   * 
   * ----------------------------------------------------------------------------------------------------------- 
   * 
   * Properties test_properties = FkTest.getTestDaten();
   * 
   * String str_text         = FkProperties.getStrPropertiesKeyValue( test_properties, true, 50 );
   * String str_trennzeichen = null;
   * String str_praefix      = null;
   * String str_suffix       = null;
   *
   * int max_breite          = 1000;
   * 
   * FkDatei.schreibeDatei( FkSystem.getStdRootVerzeichnis() + "propertie_ausgabe.txt", FkString.getStringMaxBreite( str_text, str_trennzeichen, max_breite, str_praefix, str_suffix ) );
   *
   * </pre>
   * 
   * @param pText der zu formatierende Text
   * @param pTrennzeichen das zu verwendende Trennzeichen (null = Zeilenumbruch)
   * @param pMaxBreite die maximale Breite einer Zeile 
   * @param pPraefix (optional) Ist der Parameter ungleich null wird der hier stehende String dem aktuellen Teilstring vorangestellt
   * @param pSuffix (optional) Ist der Parameter ungleich null wird der hier stehende String dem aktuellen Teilstring drangehaengt
   */
  public static String getStringMaxBreite( String pText, String pTrennzeichen, int pMaxBreite, String pPraefix, String pSuffix )
  {
    StringBuffer str_ergebnis = new StringBuffer(); // Stringbuffer fuer die Aufnahme des Rueckgabestrings
    String str_zeilenumbruch = ""; // String fuer das Zeilenumbruchzeichen des Rueckgabestrings 
    String csv_trennzeichen = null; // das ermittelte Trennzeichen
    String csv_praefix = ( pPraefix == null ? "" : pPraefix );
    String csv_suffix = ( pSuffix == null ? "" : pSuffix );
    int aktuelle_startposition = 0; // die akutelle Start-Leseposition der aeusseren Schleife
    int csv_zaehler = 0; // Zaehler fuer Vermeidung von Endlosschleifen
    int csv_start = 0; // Startposition des aktuellen Strings aus dem Eingabeparameter "pText" 
    int csv_ende = 0; // Endposition des aktuellen Strings aus dem Eingabeparameter "pText" 
    int naechste_position = 0; // Position des naechsten gefundenen Trennzeichens
    int max_br_end = 0; // Endposition fuer die Zerkleinerungsschleife

    int max_br_toleranz = pMaxBreite + 30;
    int naechste_position_lz = 0;
    boolean knz_ausrichtung_an_wortgrenzen = false;

    try
    {
      /* 
       * Pruefung: pText gesetzt? 
       */
      if ( pText != null )
      {
        /* 
         * Trennzeichen setzen
         * Wurde ein Trennzeichen uebergeben, wird jenes genommen. 
         * Ist der Parameter "pTrennzeichen" null, wird als Trennzeichen 
         * das normale Zeilenumbruchszeichen genommen.
         */
        if ( pTrennzeichen != null )
        {
          csv_trennzeichen = pTrennzeichen;
        }
        else
        {
          csv_trennzeichen = "\n";
        }

        /* 
         * Die Suchschleife laeuft solange wie...
         * ... die Variable "naechste_position" groesser/gleich 0 ist 
         *     (das Trennzeichen noch gefunden wurde) 
         * ... der CSV-Zaehler noch kleiner als 32123 ist
         */
        while ( ( naechste_position >= 0 ) && ( csv_zaehler <= 32123 ) )
        {
          /* 
           * Naechste Position Trennzeichen
           * Ab der aktuellen Startposition wird die naechste Position des Trennzeichens gesucht
           */
          csv_start = aktuelle_startposition;

          naechste_position = pText.indexOf( csv_trennzeichen, csv_start );

          /* 
           * Pruefung: Trennzeichen gefunden ?
           */
          if ( naechste_position >= 0 )
          {
            /*
             * Endposition Teilstring
             * Die Position CSV-Ende ist die Position des naechsten gefundenen Trennzeichens.
             * Diese Position wird nicht Bestandteil des Ergebnisstrings, da die genutzte
             * "subString()"-Funktion BIS VOR diesesr Position abschneidet. 
             */
            csv_ende = naechste_position;

            /* 
             * Startposition aktualisieren
             * Die naechste aktuelle Startposition liegt ab der Fundstelle zuzueglich der
             * Laenge des Trennzeichens ( hier = Zeilenumbruchzeichens ).
             */
            aktuelle_startposition = naechste_position + csv_trennzeichen.length();
          }
          else
          {
            /* 
             * Pruefung: Noch ungelesener Teilstring vorhanden ?
             * 
             * Dieses ist der Fall, wenn die letzte Leseposition kleiner
             * gleich der Stringlaenge ist. Von der letzten Leseposition 
             * wird bis zum Stringende die aktuelle Zeile ermittelt.
             * 
             * Ist das nicht der Fall, wird die letzte Startposition auf -1 gestellt.
             */
            if ( csv_start <= pText.length() )
            {
              csv_ende = pText.length();
            }
            else
            {
              csv_start = -1;
            }
          }

          /* 
           * Pruefung: csv_start >= 0 ?
           * 
           * Nur wenn das der Fall ist, darf weitergemacht werden.
           */
          if ( csv_start >= 0 )
          {
            /*
             * Pruefung: Ist "csv_akt_string" = Leerzeile?
             * Damit Leerzeilen korrekt uebernommen werden, muss bei einer 
             * Laenge von 0 Zeichen einmal der Praefix, der Suffix und das 
             * Zeilenumbruchzeichen dem Ergebnis hinzugefuegt werden. 
             */
            if ( ( csv_ende - csv_start ) == 0 )
            {
              str_ergebnis.append( str_zeilenumbruch );
              str_ergebnis.append( csv_praefix );
              str_ergebnis.append( csv_suffix );

              str_zeilenumbruch = "\n";
            }
            else
            {
              /*
               * Startwert Schleife
               * Die erste End-Position ist die Startposition zuzueglich der maximalen 
               * Zeilenbreite aus dem Parameter "pMaxBreite".
               */
              max_br_end = csv_start + pMaxBreite;

              /*
               * Pruefung: Endwert kleiner/gleich Startwert
               * Das ist der Fall, wenn im Parameter "pMaxBreite" ein Wert 
               * von 0 oder kleiner uebergeben worden ist. 
               * 
               * In diesem Fall soll die Ausgabebreite vernachlaessigt werden, 
               * bzw. die vorgefundene Zeilenbreite uebernommen werden.
               *  
               * Die End-Position wird auf die Endposition des aktuellen CSV-Strings gesetzt. 
               * Die nachfolgenden Pruefungen sorgen dafuer, das die While-Schleife nur 
               * einmal ausgefuehrt wird. 
               */
              if ( max_br_end <= csv_start )
              {
                max_br_end = csv_ende;
              }

              /*
               * While-Schleife
               * Die "Zerkleinerungsschleife" wird solange ausgefuehrt, bis die Variable
               * fuer den Startwert die Endposition des aktuellen CSV-Strings erreicht
               * oder ueberschritten hat.
               */
              while ( csv_start < csv_ende )
              {
                /*
                 * Pruefung: Endposition groesser der CSV-Endposition
                 * Ist dem so, muss die Endposition angepasst werden, damit nur bis zum 
                 * aktuellen CSV-String-Ende der Teilstring abgeschnitten wird. 
                 */
                if ( max_br_end > csv_ende )
                {
                  max_br_end = csv_ende;
                }

                if ( knz_ausrichtung_an_wortgrenzen )
                {
                  naechste_position_lz = Math.min( pText.indexOf( " ", max_br_end ), pText.indexOf( ",", max_br_end ) );

                  if ( ( naechste_position_lz >= csv_start ) && ( naechste_position_lz < csv_ende ) && ( ( ( naechste_position_lz - csv_start ) < max_br_toleranz ) ) )
                  {
                    max_br_end = naechste_position_lz + 1; // Das gefundene Zeichen mit in die aktuelle Zeile aufnehmen.
                  }
                  else
                  {
                    naechste_position_lz = pText.indexOf( ";", max_br_end );

                    if ( ( naechste_position_lz >= csv_start ) && ( naechste_position_lz < csv_ende ) && ( ( naechste_position_lz - csv_start ) < max_br_toleranz ) )
                    {
                      max_br_end = naechste_position_lz + 1; // Das gefundene Zeichen mit in die aktuelle Zeile aufnehmen.
                    }
                    else
                    {
                      naechste_position_lz = pText.indexOf( ".", max_br_end );

                      if ( ( naechste_position_lz >= csv_start ) && ( naechste_position_lz < csv_ende ) && ( ( naechste_position_lz - csv_start ) < max_br_toleranz ) )
                      {
                        max_br_end = naechste_position_lz + 1; // Das gefundene Zeichen mit in die aktuelle Zeile aufnehmen.
                      }
                    }
                  }

                  if ( max_br_end > pText.length() )
                  {
                    max_br_end = pText.length();
                  }
                }

                /*
                 * Aufbau Ergebnis
                 * Es werden die Bestandteile Zeilenumbruch + Praefix + Teilstring + Suffix dem 
                 * Ergebnisstring hinzugefuegt.  
                 * 
                 * Der Zeilenumbruch ist im ersten Durchlauf noch ein Leerstring. Somit wird 
                 * im ersten Durchlauf kein falscher Zeilenumbruch hinzugefuegt.
                 *  
                 * Der Zeilenumbruch-String wird unten der Variablen hinzugefuegt. 
                 * Jeder weitere Durchlauf durch diese Routine setzt daher den Zeilenumbruch 
                 * fuer die vorhergehende Zeile. Dieses Vorgehen verhindert, dass der letzte
                 * Zeilenumbruch kein falscher Zeilenumbruch wird.
                 */
                str_ergebnis.append( str_zeilenumbruch );
                str_ergebnis.append( csv_praefix );
                str_ergebnis.append( pText.substring( csv_start, max_br_end ) );
                str_ergebnis.append( csv_suffix );

                /*
                 * Leseposition weiterschalten
                 * CSV-Start wird auf die aktuelle Endposition gestellt.
                 *  
                 * Die Endposition wird um die Max-Breite erhoeht. Sollte die sich
                 * ergebende Breite kleiner/gleich 0 sein, wurde schon durch die 
                 * obere Pruefung sichergestellt, dass in einem solchen fall hier
                 * der Wert der Startposition gleich der Max-Laenge des aktuellen
                 * Strings ist (d.h. die Schleifenendbedingung erreicht ist).
                 */
                csv_start = max_br_end;

                max_br_end = max_br_end + pMaxBreite;

                str_zeilenumbruch = "\n";
              }
            }
          }

          /* 
           * Endlosschleifenverhinderungszaehler 1 weiterstellen
           */
          csv_zaehler = csv_zaehler + 1;
        }
      }
    }
    catch ( Exception err_inst )
    {
      FkLogger.wl( "Fehler ", err_inst );
    }

    return str_ergebnis.toString();
  }

  /**
   * <pre>
   * NUR FUER DIE PROGRAMMIERUNG ENTHALTEN
   * ... im richtigen Leben macht diese Funktion natuerlich keinen Sinn, kann aber
   * in der Programmierung mal zweckdienlich sein, weil es keinen Compilerfehler gibt
   * </pre>
   * 
   * @param pString der  String 
   * @return 0 wenn der String nicht gesetzt ist, sonst die Laenge des Strings
   */
  public static int getMaxLen( String pString )
  {
    if ( pString != null )
    {
      return pString.length();
    }

    return 0;
  }

  /**
   * <pre>
   * NUR FUER DIE PROGRAMMIERUNG ENTHALTEN
   * ... im richtigen Leben macht diese Funktion natuerlich keinen Sinn, kann 
   * aber in der Programmierung mal zweckdienlich sein.
   * </pre>
   * 
   * @param pLaenge1 die Laenge
   * @return pLaenge1
   */
  public static int getMaxLen( int pLaenge1 )
  {
    return pLaenge1;
  }


  /**
   * Prueft ob ein String ungleich "null" und einen Inhalt hat. 
   * Bei der Laengenpruefung wird ein TRIM auf den Eingabestring gemacht.
   * 
   * @param pString der zu pruefende String
   * @return TRUE wenn ungleich "null" und mindestens ein Zeichen vorhanden ist
   */
  public static boolean isSet( String pString )
  {
    if ( ( pString != null ) && ( pString.trim().length() > 0 ) )
    {
      /*
       * JA, der uebergebene String ist gesetzt und kein Leerstring
       */
      return true; // 
    }

    return false; // NEIN, der uebergebene String ist NULL oder hat eine Laenge von 0
  }

  /**
   * Bei der Laengenpruefung wird ein TRIM auf den Eingabestring gemacht.
   * 
   * @param pString1 der erste String 
   * @param pString2 der zweite String
   * @return TRUE wenn beide Strings ungleich null und mindestens 1 Zeichen haben.
   */
  public static boolean isSet( String pString1, String pString2 )
  {
    if ( ( pString1 != null ) && ( pString2 != null ) )
    {
      if ( ( pString1.trim().length() > 0 ) && ( pString2.trim().length() > 0 ) )
      {
        return true;
      }
    }

    return false;
  }

  public static boolean isSetOr( String pString1, String pString2 )
  {
    return ( ( pString1 != null ) && ( pString1.trim().length() > 0 ) ) || ( ( pString2 != null ) && ( pString2.trim().length() > 0 ) );
  }

  public static boolean isSetOr( String pString1, String pString2, String pString3 )
  {
    return ( ( pString1 != null ) && ( pString1.trim().length() > 0 ) ) || ( ( pString2 != null ) && ( pString2.trim().length() > 0 ) ) || ( ( pString3 != null ) && ( pString3.trim().length() > 0 ) );
  }

  /**
   * Bei der Laengenpruefung wird ein TRIM auf den Eingabestring gemacht.
   * @param pString1 der erste String 
   * @param pString2 der zweite String
   * @param pString3 der dritte String 
   * @return TRUE wenn alle Strings ungleich null und mindestens 1 Zeichen haben.
   */
  public static boolean isSet( String pString1, String pString2, String pString3 )
  {
    if ( ( pString1 != null ) && ( pString2 != null ) && ( pString3 != null ) )
    {
      if ( ( pString1.trim().length() > 0 ) && ( pString2.trim().length() > 0 ) && ( pString3.trim().length() > 0 ) )
      {
        return true;
      }
    }

    return false;
  }

  public static boolean isSet( String pString1, String pString2, String pString3, String pString4 )
  {
    if ( ( pString1 != null ) && ( pString2 != null ) && ( pString3 != null ) && ( pString4 != null ) )
    {
      if ( ( pString1.trim().length() > 0 ) && ( pString2.trim().length() > 0 ) && ( pString3.trim().length() > 0 ) && ( pString4.trim().length() > 0 ) )
      {
        return true;
      }
    }

    return false;
  }

  public static boolean isSet( String pString1, String pString2, String pString3, String pString4, String pString5 )
  {
    if ( ( pString1 != null ) && ( pString2 != null ) && ( pString3 != null ) && ( pString4 != null ) && ( pString5 != null ) )
    {
      if ( ( pString1.trim().length() > 0 ) && ( pString2.trim().length() > 0 ) && ( pString3.trim().length() > 0 ) && ( pString4.trim().length() > 0 ) && ( pString5.trim().length() > 0 ) )
      {
        return true;
      }
    }

    return false;
  }

  public static boolean isSet( String pString1, String pString2, String pString3, String pString4, String pString5, String pString6 )
  {
    if ( ( pString1 != null ) && ( pString2 != null ) && ( pString3 != null ) && ( pString4 != null ) && ( pString5 != null ) && ( pString6 != null ) )
    {
      if ( ( pString1.trim().length() > 0 ) && ( pString2.trim().length() > 0 ) && ( pString3.trim().length() > 0 ) && ( pString4.trim().length() > 0 ) && ( pString5.trim().length() > 0 ) && ( pString6.trim().length() > 0 ) )
      {
        return true;
      }
    }

    return false;
  }

  public static boolean isSet( String pString1, String pString2, String pString3, String pString4, String pString5, String pString6, String pString7 )
  {
    if ( ( pString1 != null ) && ( pString2 != null ) && ( pString3 != null ) && ( pString4 != null ) && ( pString5 != null ) && ( pString6 != null ) && ( pString7 != null ) )
    {
      if ( ( pString1.trim().length() > 0 ) && ( pString2.trim().length() > 0 ) && ( pString3.trim().length() > 0 ) && ( pString4.trim().length() > 0 ) && ( pString5.trim().length() > 0 ) && ( pString6.trim().length() > 0 ) && ( pString7.trim().length() > 0 ) )
      {
        return true;
      }
    }

    return false;
  }

  public static boolean isSet( String pString1, String pString2, String pString3, String pString4, String pString5, String pString6, String pString7, String pString8 )
  {
    if ( ( pString1 != null ) && ( pString2 != null ) && ( pString3 != null ) && ( pString4 != null ) && ( pString5 != null ) && ( pString6 != null ) && ( pString7 != null ) && ( pString8 != null ) )
    {
      if ( ( pString1.trim().length() > 0 ) && ( pString2.trim().length() > 0 ) && ( pString3.trim().length() > 0 ) && ( pString4.trim().length() > 0 ) && ( pString5.trim().length() > 0 ) && ( pString6.trim().length() > 0 ) && ( pString7.trim().length() > 0 ) && ( pString8.trim().length() > 0 ) )
      {
        return true;
      }
    }

    return false;
  }

  /**
   * <pre>
   * Analoge Funktion fuer die mit Java 1.6 hinzugekommene String-Funktion isEmpty.
   * Es wird kein TRIM auf pString ausgefuehrt.
   * </pre>
   * 
   * @param pString der zu pruefende String 
   * @return TRUE wenn pString null oder "pString.length() == 0" ist
   */
  public static boolean isEmpty( String pString )
  {
    /*
     * Pruefung: Parameter "pString" gleich "null" ?
     * 
     * Ist pString nicht gesetzt, wird true zurueck gegeben.
     */
    if ( pString == null )
    {
      return true;
    }

    return pString.length() == 0;
  }

  public static boolean isTrimEmpty( String pString )
  {
//    if ( ( pString == null ) || ( pString.trim().length() == 0 ) )
//    {
//      return true;
//    }

    /*
     * Pruefung: Parameter "pString" gleich "null" ?
     * 
     * Ist pString nicht gesetzt, wird true zurueck gegeben.
     */
    if ( pString == null )
    {
      return true;
    }

    return pString.trim().length() == 0;
  }

  public static boolean isTrimSet( String pString )
  {
    /*
     * Pruefung: Parameter "pString" gleich "null" ?
     * 
     * Ist pString nicht gesetzt, wird false zurueck gegeben.
     */
    if ( pString == null )
    {
      return false;
    }

    return pString.trim().length() > 0;
  }

  /**
   * Prueft ob ein String NICHT gesetzt ist.
   * Bei der Laengenpruefung wird ein TRIM auf den Eingabestring gemacht.
   * 
   * @param pString der zu pruefende String
   * @return TRUE wenn der String null oder ein Leerstring ist.
   */
  public static boolean isNotSet( String pString )
  {
    if ( ( pString == null ) || ( pString.length() == 0 ) )
    {
      return true; // = Ja, der String ist null oder nicht gesetzt
    }

    /* Kopiervorlage - Pruefung auf String gesetzt */
    //if ( ( pString != null ) && ( pString.length() > 0 ) ) { return false; // Nein der String ist gesetzt und hat eine Laenge
    //}
    return false; // Nein der String ist gesetzt und hat eine Laenge
    //    if ( pString == null ) { return true; } // Ist der String null, ist der String nicht gesetzt
    //
    //    return pString.trim().length() == 0; // Hat der String eine Laenge von 0, ist der String nicht gesetzt
  }

  public static boolean istLeerstring( String pString )
  {
    if ( pString == null )
    {
      return true;
    }

    return pString.length() == 0;
  }

  public static boolean istKeinLeerstring( String pString )
  {
    if ( pString == null )
    {
      return false;
    }

    return pString.length() > 0;
  }

  /**
   * @param pStringBuffer der zu pruefende Stringbuffer
   * @return TRUE bei ungleich null und mindestens ein Zeichen, sonst FALSE
   */
  public static boolean isSet( StringBuffer pStringBuffer )
  {
    if ( pStringBuffer == null )
    {
      return false;
    }

    return pStringBuffer.toString().trim().length() > 0;
  }

  public static int InStr( String pZuDurchsuchenderString, String pSuchString )
  {
    if ( pZuDurchsuchenderString == null )
    {
      return -1;
    }

    if ( pSuchString == null )
    {
      return -1;
    }

    return pZuDurchsuchenderString.indexOf( pSuchString, 0 );
  }

  public static int InStr( long pStartPosition, String pZuDurchsuchenderString, String pSuchString )
  {
    if ( pZuDurchsuchenderString == null )
    {
      return -1;
    }

    if ( pSuchString == null )
    {
      return -1;
    }

    return pZuDurchsuchenderString.indexOf( pSuchString, (int) pStartPosition );
  }

  /**
   * <pre>
   * Gleichnamige Version aus Visual-Basic.
   * 
   * FkString.InStr( 0    , "ABC.DEF.GHI.KLM",  "ABC" )   =  0
   * FkString.InStr( 0    , "ABC.DEF.GHI.KLM",  "GHI" )   =  8
   * FkString.InStr( 1    , "ABC.DEF.GHI.KLM",  "ABC" )   = -1
   * FkString.InStr( 0    , null,               "ABC" )   = -1
   * FkString.InStr( 0    , "ABC.DEF.GHI.KLM",  "XYZ" )   = -1
   * FkString.InStr( 0    , "ABC.DEF.GHI.KLM",  null  )   = -1
   * FkString.InStr( 100  , "ABC.DEF.GHI.KLM",  "ABC" )   = -1
   * FkString.InStr( -10  , "ABC.DEF.GHI.KLM",  "ABC" )   =  0
   * </pre>
   * 
   * @param pStartPosition die Position ab welcher mit der Suche begonnen werden soll
   * @param pZuDurchsuchenderString der zu durchsuchende String 
   * @param pSuchString der String, welcher gesucht wird
   * @return die eventuell gefundene Position, im Fehlerfall -1
   */
  public static int InStr( int pStartPosition, String pZuDurchsuchenderString, String pSuchString )
  {
    if ( pZuDurchsuchenderString == null )
    {
      return -1;
    }

    if ( pSuchString == null )
    {
      return -1;
    }

    return pZuDurchsuchenderString.indexOf( pSuchString, pStartPosition );
  }

  /**
   * <pre>
   * Sucht in pString die Start- und die Endzeichenfolge und gibt den "eingeschlossenen" Teilstring zurueck. 
   * 
   * Wird die Startzeichenfolge nicht gefunden, wird ab Stringanfang abgeschnitten.
   * Wird die Endzeichenfolge nicht gefunden, wird bis zum Stringende abgeschnitten.
   * 
   * Ist die Startzeichenfolge "null" wird ab Stringanfang abgeschnitten.
   * Ist die Endzeichenfolge "null" wird bis zum Stringende abgeschnitten. 
   * 
   * FkString.MidX( "1234567890", "5", "6" )                               = ""
   * FkString.MidX( "1234567890", "6", "5" )                               = "7890"
   * FkString.MidX( "1234567890", "4", "7" )                               = "56"
   * FkString.MidX( "1234567890", "3", "8" )                               = "4567"
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei", "Fuenf" )   = " Drei Vier "
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", "Fuenf", "Zwei" )   = " Sechs"
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei", "Neun"  )   = " Drei Vier Fuenf Sechs"
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", Null,   "Fuenf" )   = "Eins Zwei Drei Vier "
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", Null,   "Neun"  )   = "Eins Zwei Drei Vier Fuenf Sechs"
   * FkString.MidX( null, "Zwei", "Fuenf" )                                = "null"
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", null, "Fuenf"  )    = "Eins Zwei Drei Vier "
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei", null   )    = " Drei Vier Fuenf Sechs"
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", "null", null   )    = "Eins Zwei Drei Vier Fuenf Sechs"
   * FkString.MidX( "Eins Zwei Drei Vier Fuenf Sechs", "Sechs", "Fuenf" )  = ""
   * </pre>
   * 
   * @param pString der String mit dem herauszutrennenden Teilstring
   * @param pAbString Suchzeichenfolge fuer den Start
   * @param pBisString Suchzeichenfolge fuer das Ende
   * @return den sich ergebenden Teilstring
   */
  public static String MidX( String pString, String pAbString, String pBisString )
  {
    /*
     * Pruefung: pString ungleich null?
     */
    if ( pString != null )
    {
      /*
       * Position der Startzeichenfolge mit -1 vorbelegen.
       */
      int ab_position = -1;

      /*
       * Pruefung: Abstring vorhanden?
       * Ist eine Startzeichenfolge vorhanden, wird diese im Eingabestring gesucht.
       */
      if ( pAbString != null )
      {
        ab_position = pString.indexOf( pAbString );
      }

      /*
       * Pruefung: Startzeichenfolge gefunden?
       * Wurde die Startzeichenfolge nicht gefunden, wird vom Stringanfang an 
       * abgeschnitten. Die Ab-Position wird auf 0 gestellt.
       */
      if ( ab_position < 0 )
      {
        ab_position = 0;
      }
      else
      {
        /* 
         * Wurde die Startzeichenfolge gefunden, muss die Ab-Position um die 
         * Laenge der Startzeichenfolge erhoeht werden. Die Startzeichenfolge 
         * ist nicht Bestandteil der Rueckgabe.  
         */
        ab_position += pAbString.length();
      }

      /*
       * Vorbelegung der Bis-Position mit -1.
       */
      int end_position = -1;

      /*
       * Pruefung: Endzeichenfolge uebergeben?
       * Wurde eine Endzeichenfolge uebergeben, wird die Endzeichenfolge im Eingabestring gesucht.
       * Die Endzeichenfolge wird ab der Ab-Positon gesucht.
       */
      if ( pBisString != null )
      {
        end_position = pString.indexOf( pBisString, ab_position );
      }

      /*
       * Pruefung: Endzeichenfolge gefunden?
       * Wurde die Endzeichenfolge nicht gefunden, wird bis zum Stringende abgeschnitten. 
       * Die Bis-Position ist daher gleich der Laenge des Eingabestrings.
       */
      if ( end_position < 0 )
      {
        end_position = pString.length();
      }

      /*
       * Rueckgabe des Strings von der Ab-Position bis zur End-Position.
       */
      return pString.substring( ab_position, end_position );
    }

    /*
     * Als Standardrueckgabe wird eine null-Referenz zurueckgegeben. 
     */
    return null;
  }

  /**
   * <pre>
   * Sucht in pXmlString das angegebene XML-Tag und liefert diesen im Erfolgsfall zurueck.
   * 
   * Es wird immer die erste gefundene XML-Klammer zurueckgegeben.
   * 
   * Geschachtelte XML-Klammern werden beruecksichtigt.
   * 
   * Wird das XML-Tag nicht gefunden wird null zurueckgegeben.
   *
   * Ist der Parameter "pXmlString" null, wird null zurueckgegeben.
   * 
   * Ist die XML-Klammerung falsch, wird null zurueckgegeben.
   * 
   * Sonderfall: leere XML-Tag's
   * Es wird von dieser Funktion immer ein Start- und ein End-Tag gesucht.
   * Leere XML-Klammern der Form "<a></a>" werden gefunden, welches ein Leerstring als Rueckgabe ergibt.
   * Leere XML-Klammern der Form "<a />" werden nicht gefunden, die Rueckgabe ist in diesem Fall null. 
   * 
   * FkString.midXml( "<a>1</a><b>2</b>", "a" ) = "1"
   * FkString.midXml( "<a>1</a><b>2</b>", "b" ) = "2"
   * 
   * FkString.midXml( "<a></a><b>2</b>",  "a" ) = ""  = XML-Tag ist leer
   * FkString.midXml( "<a> </a><b>2</b>", "a" ) = " " 
   * 
   * FkString.midXml( "<a>1</a><b>2</b>", "c"  ) = null = XML-Tag nicht vorhanden
   * 
   * FkString.midXml( "<a>1<a><b>2</b>",  "a"  ) = null = Kein End-Tag vorhanden
   * FkString.midXml( "</a>1<a><b>2</b>", "a"  ) = null = End-Tag vor Start-Tag
   * FkString.midXml( "<a /><b>2</b>",    "a"  ) = null = Leeres XML-Tag 
   * FkString.midXml( "<a>1</a><b>2</b>", ""   ) = null = Kein Tag-Name angegeben
   * FkString.midXml( "<a>1</a><b>2</b>", null ) = null = Tag-Name ist null
   * 
   * FkString.midXml( "",   "a" ) = null = pXmlString nicht gesetzt
   * FkString.midXml( null, "a" ) = null = pXmlString ist null
   * 
   * Geschachtelte XML-Klammern
   * FkString.midXml( "<a>TEST <a>1</a><b>2</b></a><b>2</b>", "a" ) = "TEST <a>1</a><b>2</b>"
   * 
   * FkString.midXml( "<a>TEST <a>1</a><b>2</b><a><b>2</b>", "a" )  = null = kein XML-End-Tag fuer das Start-Tag vorhanden
   *                                                                       = falsch XML-Klammerung
   * </pre>
   * 
   * @param pXmlString der String mit dem herauszutrennenden Teilstring
   * @param pXmlTagName XML-Tagname
   * @return den Teilstring, wenn Start und Ende gefunden wurden, sonst null
   */
  public static String midXml( String pXmlString, String pXmlTagName )
  {
    int position_start = 0; // Speichert die Startposition fuer die Rueckgabe
    int position_ende = 1; // Speichert die naechste Position des Trennzeichens ab der Startposition
    int position_temp_start = 1; // Position eines eventuellen Starttags zwischen Position-Start und Position-Ende

    /*
     * Der Rueckgabewert wird mit null als Vorgaberueckgabe versehen
     */
    String ergebnis_xml_wert = null;

    /*
     * Pruefung: Parameter gesetzt?
     * Der XML-String muss vorhanden sein.
     * Der XML-Tag-Name muss vorhanden und darf kein Leerstring sein. 
     */
    if ( ( pXmlString != null ) && ( pXmlTagName != null ) && ( pXmlTagName.trim().length() > 0 ) )
    {
      try
      {
        int xml_tag_index_zaehler = 0; // Zaehler fuer die XML-Tag Suchschleife 
        int xml_tag_index_gesucht = 1; // Der zu suchende XML-Tag-Index (das wievielte Tag soll es sein)
        int xml_tab_position_ab = 0;

        String xml_tag_start = "<" + pXmlTagName + ">";
        String xml_tag_ende = "</" + pXmlTagName + ">";

        /*
         * Position Starttag
         */
        if ( xml_tab_position_ab > 0 )
        {
          position_start = pXmlString.indexOf( xml_tag_start, xml_tab_position_ab );
        }
        else
        {
          position_start = pXmlString.indexOf( xml_tag_start );
        }

        /*
         * Suchschleife XML-Tag-Position
         * 
         * Die While-Schleife laeuft solange wie:
         * ... der Ergebnis-Index noch nicht gefunden wurde
         * ... die XML-Tag-Startposition groesser gleich 0 ist
         * ... die XML-Tag-Endposition groesser 0 ist
         */
        while ( ( xml_tag_index_zaehler < xml_tag_index_gesucht ) && ( position_start >= 0 ) && ( position_ende > 0 ) )
        {
          /*
           * XML-Tag-Index-Suchzaehler erhoehen.
           */
          xml_tag_index_zaehler++;

          /*
           * Pruefung: Nochmaliger Durchlauf?
           * Soll nicht die erste XML-Klammer zurueckgegeben werden, wird in einem 
           * weiteren Durchlauf die naechste Startposition des XML-Tags gesucht.
           * 
           * Die Positionssuche startet ab der aktuellen Endposition plus 1.
           */
          if ( xml_tag_index_zaehler > 1 )
          {
            position_start = position_ende + 1;

            position_start = pXmlString.indexOf( xml_tag_start, position_start );
          }

          /*
           * Pruefung: Startposition gefunden?
           * Wurde kein Starttag gefunden, muss auch keine End-Position gesucht werden.
           * 
           * Wurde ein Starttag gefunden, wird nach dem Endtag gesucht.
           */
          if ( position_start >= 0 )
          {
            /* 
             * End-Xml-Tag suchen
             * Das End-Xml-Tag wird ab der Startposition der Rueckgabe gesucht.
             * Der Suchstring ist jetzt das Muster fuer ein XML-Endtag.
             */
            position_ende = pXmlString.indexOf( xml_tag_ende, position_start );

            /*
             * Pruefung: XML-Endtag gefunden?
             */
            if ( position_ende == -1 )
            {
              /*
               * Kein XML-Endtag vorhanden
               * Es gibt 2 Moeglichkeiten:
               * 
               * 1. Keine Rueckgabe
               *    
               * 2. Reststring ab dem Starttag
               * 
               */
              position_ende = pXmlString.length();

              position_start = -1;
            }
            else
            {
              /*
               * XML-Endtag wurde gefunden
               * 
               * Pruefung: Geschachtelte XML-Tags vorhanden?
               */
              position_temp_start = position_start + 1;

              while ( ( position_temp_start > position_start ) && ( position_temp_start < position_ende ) )
              {
                //FkLogger.wl( " position_temp_start " + position_temp_start + "    position_ende " + position_ende + " " );

                /*
                 * Suche ein Starttag zwischen dem oben gefundenen Start und dem 
                 * gefundenen Endtag. 
                 * 
                 * Es darf dazwischen kein weiteres Starttag sein. 
                 * 
                 * Wird ein Starttag gefunden, ist das erste Endtag ungueltig. 
                 * Es muss ein neues Endtag, ab der gerade gefundenen End-Tag-Position, gesucht werden.
                 */
                position_temp_start = pXmlString.indexOf( xml_tag_start, position_temp_start + 1 );

                if ( ( position_temp_start > 0 ) && ( position_temp_start < position_ende ) )
                {
                  position_ende = pXmlString.indexOf( xml_tag_ende, position_ende + 1 );
                }
              }
            }
          }
        }

        if ( ( position_start >= 0 ) && ( position_ende > position_start ) )
        {
          /* 
           * Anpassung Startposition
           * Damit die Startposition auf das erste Zeichen der Rueckgabe zeigt,
           * wird zu der Startposition erstens die Laenge des Suchstringes
           * hinzuaddiert. Zweitens kommen noch 2 Zeichen fuer die eckigen 
           * Klammern hinzu.
           */
          position_start = position_start + pXmlTagName.length() + 2;

          if ( position_ende > position_start )
          {
            /* 
             * Ergebnissstring setzen
             * Der Ergebnisstring ist der Teilstring ab der Startposition 
             * bis zur Endposition. 
             */
            ergebnis_xml_wert = pXmlString.substring( position_start, position_ende );
          }
          else if ( position_ende == position_start )
          {
            /* 
             * Sonderfall leere XML-Klammer
             * Ist die Startposition gleich der Endposition liegt eine leere XML-Klammer vor. 
             * Der Aufrufer bekommt einen Leerstring zurueck, da das XML-Tag leer aber nicht null ist.
             */
            ergebnis_xml_wert = "";
          }
        }
      }
      catch ( Exception err_inst )
      {
        ergebnis_xml_wert = null;
      }
    }

    return ergebnis_xml_wert;
  }

  /**
   * <pre>
   * Sucht das Suchwort in pString und gibt diesen inklusive der Relativen Abschneidepositionen zurueck.
   * 
   * Anfangs- und Endposition des Raustrennens modifizieren
   * 
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",     0,   0 );  = "GHI"
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",    -1,   1 );  = ".GHI."
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",    -2,   2 );  = "F.GHI.J"
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",    -3,   3 );  = "EF.GHI.JK"
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",    -4,   4 );  = "DEF.GHI.JKL"
   * 
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",     1, -1 );  = "H"
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",     2, -2 );  = "" = Ab-Position groesser als Bis-Position = Leerstring
   * 
   *
   * 
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",     0, 100 );  = "GHI.JKL"      = Bis-Position laenger als Eingabe
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "GHI",  -100,   0 );  = "ABC.DEF.GHI"  = Ab-Position auf Start
   * 
   * FkString.getStringRelativ(              null, "GHI",    -1, 1 );  = null = kein Eingabetext gesetzt
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL",  null,    -1, 1 );  = null = kein Suchwort angegeben
   * FkString.getStringRelativ( "ABC.DEF.GHI.JKL", "   ",    -1, 1 );  = null = Suchwort wird nicht gefunden
   * 
   * </pre>
   * 
   * @param pString der String, in welchem das Suchwort zu finden ist
   * @param pSuchwort das Suchwort
   * @param pMinusAnfang relative Anzahl von Stellen, welche auf die Startposition des Suchwortes draufaddiert werden
   * @param pPlusEnde relative Anzahl von Stellen, welche auf die Endposition draufaddiert werden
   * @return den sich ergebenden String
   */
  public static String getStringRelativ( String pString, String pSuchwort, int pMinusAnfang, int pPlusEnde )
  {
    /*
     * Pruefung: Sind die Parameter ungleich null?
     */
    if ( ( pString != null ) && ( ( pSuchwort != null ) && ( pSuchwort.length() > 0 ) ) )
    {
      int ab_position = pString.indexOf( pSuchwort );

      /*
       * Pruefung: Suchwort gefunden?
       * Wurde das Suchwort nicht nicht gefunden, bekommt der Aufrufer "null" zurueck.
       */
      if ( ab_position >= 0 )
      {
        /*
         * Bis-Position
         * Die Bis-Position ist von der Position der ersten Fundstelle des 
         * Suchwortes, zuzueglich dessen Laenge und zuzueglich der Anzahl 
         * Stellen aus dem Parameter "pPlusEnde" zu finden.
         */
        int bis_position = ab_position + pSuchwort.length() + pPlusEnde;

        /*
         * Pruefung: Bis-Position groesser als Laenge "pString"?
         * Ist die berechnete Bis-Position laenger als der Eingeabestring "pString" 
         * selber, wird die Bis-Position auf das Stringende eingestellt.
         */
        if ( bis_position > pString.length() )
        {
          bis_position = pString.length();
        }

        /*
         * Pruefung: Bis-Position kleiner 0?
         * Ist die Bis-Position durch die Anzahl der Stellen aus dem 
         * Parameter "pPlusEnde" kleiner als 0, wird die Bis-Position 
         * auf 0 gestellt.
         */
        if ( bis_position < 0 )
        {
          bis_position = 0;
        }

        /*
         * Ab-Positon verschieben
         * Auf die Ab-Position werden die Anzahl von Stellen aus dem 
         * Parameter "pMinusAnfang" draufaddiert. 
         * 
         * Normalerweise sollter der Parameter einen negativen Wert haben.
         */
        ab_position = ab_position + pMinusAnfang;

        /*
         * Pruefung: Ab-Positon kleiner 0?
         * Ist die Ab-Position kleiner als 0, wird die Ab-Position auf 0 gestellt.
         */
        if ( ab_position < 0 )
        {
          ab_position = 0;
        }

        /*
         * Pruefung: Ab-Position groesser gleich der Bis-Position?
         * Ist die Ab-Position groesser/gleich der Bis-Position, ist das 
         * Funktionsergebnis ein Leerstring. 
         */
        if ( ab_position >= bis_position )
        {
          return "";
        }

        /*
         * Rueckgabe des sich ergebenden Strings
         */
        return pString.substring( ab_position, bis_position );
      }
    }

    /*
     * Als Standardrueckgabe wird eine null-Referenz zurueckgegeben. 
     */
    return null;
  }

  /**
   * <pre>
   * Sucht in pString die Start- und die Endzeichenfolge und gibt den 
   * "eingeschlossenen" Teilstring zurueck. 
   * 
   * Es wird ein String zurueckgeben, wenn die Start- und Endzeichenfolge 
   * gefunden wurden und hintereinander stehen. Es erfolgt keine automatische
   * Umkehrung, wenn der Start hinter dem Ende gefunden wird.  
   * In allen anderen Faellen wird null zurueckgegeben. 
   * 
   * FkString.Mid( "1234567890", "5", "6" )                                = ""
   * FkString.Mid( "1234567890", "6", "5" )                                = null
   * FkString.Mid( "1234567890", "4", "7" )                                = "56"
   * FkString.Mid( "1234567890", "3", "8" )                                = "4567"
   * 
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei",  "Fuenf"  )  = " Drei Vier "
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Fuenf", "Zwei"   )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei",  "Neun"   )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Null",  "Fuenf"  )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Null",  "Neun"   )  = null
   * FkString.Mid( null,                              "Zwei",  "Fuenf"  )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", null,    "Fuenf"  )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei",  null     )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", null,    null     )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Sechs", "Fuenf"  )  = null
   * </pre>
   * 
   * @param pString der String mit dem herauszutrennenden Teilstring
   * @param pAbString Suchzeichenfolge fuer den Start
   * @param pBisString Suchzeichenfolge fuer das Ende
   * @return den Teilstring, wenn Start und Ende gefunden wurden, sonst null
   */
  public static String Mid( String pString, String pAbString, String pBisString )
  {
    /*
     * Pruefung: Sind die Parameter ungleich null?
     */
    if ( ( pString != null ) && ( pAbString != null ) && ( pBisString != null ) )
    {
      /*
       * Position der Startzeichenfolge suchen.
       */
      int ab_position = pString.indexOf( pAbString );

      /*
       * Pruefung: Startzeichenfolge gefunden?
       * Wurde die Startzeichenfolge nicht gefunden, bekommt der Aufrufer "null" zurueck.
       */
      if ( ab_position >= 0 )
      {
        /* 
         * Wurde die Startzeichenfolge gefunden, muss die Ab-Position um die 
         * Laenge der Startzeichenfolge erhoeht werden. Die Startzeichenfolge 
         * ist nicht Bestandteil der Rueckgabe.  
         */
        ab_position += pAbString.length();

        /*
         * Die Bis-Zeichenfolge wird ab der Ab-Positon gesucht.
         */
        int bis_position = pString.indexOf( pBisString, ab_position );

        /*
         * Pruefung: Bis-Zeichenfolge gefunden?
         * Wurde zwar die Startzeichenfolge gefunden, aber die Bis-Zeichenfolge 
         * nicht im Suchstring enthalten ist, bekommt der Aufrufer ebenfalls null zurueck.
         */
        if ( bis_position >= 0 )
        {
          /*
           * Rueckgabe des eingeschlossenen Strings
           */
          return pString.substring( ab_position, bis_position );
        }
      }
    }

    /*
     * Als Standardrueckgabe wird eine null-Referenz zurueckgegeben. 
     */
    return null;
  }

  public static String Mid( String pString, long pAbPosition, long pLaenge )
  {
    try
    {
      if ( pString != null )
      {
        if ( pLaenge < 0 )
        {
          return pString.substring( (int) pAbPosition );
        }

        return pString.substring( (int) pAbPosition, (int) ( pAbPosition + pLaenge ) );
      }
    }
    catch ( Exception err_inst )
    {
    }

    return "";
  }

  public static String Mid( String pString, long pAbPosition, int pLaenge )
  {
    try
    {
      if ( pString != null )
      {
        if ( pLaenge < 0 )
        {
          return pString.substring( (int) pAbPosition );
        }

        return pString.substring( (int) pAbPosition, (int) ( pAbPosition + pLaenge ) );
      }
    }
    catch ( Exception err_inst )
    {
    }

    return "";
  }

  public static String Mid( String pString, long pAbPosition )
  {
    try
    {
      if ( pString != null )
      {
        return pString.substring( (int) pAbPosition );
      }
    }
    catch ( Exception err_inst )
    {
    }

    return "";
  }

  public static String Mid( String pString, int pAbPosition )
  {
    try
    {
      if ( pString != null )
      {
        return pString.substring( (int) pAbPosition );
      }
    }
    catch ( Exception err_inst )
    {
    }

    return "";
  }

  /**
   * <pre>
   * MID-Funktion
   * Liefert einen String, die eine festgelegte Zeichenanzahl aus einer Zeichenfolge enthaelt.
   * 
   * </pre>
   * 
   * @param pString der Quellstring, aus welchem herausgeschnitten werden soll
   * @param pAbPosition die Position, ab welcher abgeschnitten werden soll
   * @param pLaenge die Laenge der abzuschneidenden Zeichen 
   * @return einen String 
   */
  public static String Mid( String pString, int pAbPosition, int pLaenge )
  {
    try
    {
      if ( pString != null )
      {
        if ( pLaenge < 0 )
        {
          return pString.substring( pAbPosition );
        }

        return pString.substring( pAbPosition, pAbPosition + pLaenge );
      }
    }
    catch ( Exception err_inst )
    {
    }

    return "";

    /*
     * int trenn_pos_ab = (int) ( );
     * int trenn_laenge = (int) ( );
     * 
     * if ( trenn_laenge < 0 )
     * {
     *   ergebnis_mid = pEingabe.substring( trenn_pos_ab );
     * }
     * else if ( laenge_eingabe < trenn_pos_ab + trenn_laenge ) 
     * {
     *   ergebnis_mid = pEingabe.substring( trenn_pos_ab );
     * }
     * else
     * {
     *   ergebnis_mid = pEingabe.substring( trenn_pos_ab, trenn_pos_ab + trenn_laenge );
     * }
     */
  }

  /**
   * <pre>
   * MID-Funktion
   * Liefert einen String, die eine festgelegte Zeichenanzahl aus einer Zeichenfolge enthaelt.
   * 
   * </pre>
   * 
   * @param pString der Quellstring, aus welchem herausgeschnitten werden soll
   * @param pAbPosition die Position, ab welcher abgeschnitten werden soll
   * @param pLaenge die Laenge der abzuschneidenden Zeichen 
   * @return einen String 
   */
  public static String MidVB( String pString, int pAbPosition, int pLaenge )
  {
    try
    {
      if ( pString != null )
      {
        if ( pLaenge < 0 )
        {
          return pString.substring( pAbPosition );
        }

        int int_ab_pos = pAbPosition - 1;

        if ( pAbPosition < 0 )
        {

        }

        return pString.substring( pAbPosition, pAbPosition + pLaenge );
      }
    }
    catch ( Exception err_inst )
    {
    }

    return "";
  }

  public static String nString( int pAnzahlWiederholungen, String pString )
  {
    /*
     * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
     */
    if ( pAnzahlWiederholungen <= 0 )
    {
      return "";
    }

    if ( pAnzahlWiederholungen > 1500 )
    {
      pAnzahlWiederholungen = 1500;
    }

    String ergebnis = "";

    /*
     * Der String ergebnis wird solange verdoppelt bis die laenge groesser der Anzahl ist. Danach wird ein Substring der uebergebenen Laenge zurueckgegeben.
     */
    for ( int zaehler = 1; ergebnis.length() <= pAnzahlWiederholungen; zaehler++ )
    {
      ergebnis += pString;
    }

    return ergebnis;
  }

  public static String nString( long pAnzahlWiederholungen, String pString )
  {
    /*
     * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
     */
    if ( pAnzahlWiederholungen <= 0 )
    {
      return "";
    }

    if ( pAnzahlWiederholungen > 1500 )
    {
      pAnzahlWiederholungen = 1500;
    }

    String ergebnis = "";

    /*
     * Der String ergebnis wird solange verdoppelt bis die laenge groesser der Anzahl ist. Danach wird ein Substring der uebergebenen Laenge zurueckgegeben.
     */
    for ( int zaehler = 1; ergebnis.length() <= pAnzahlWiederholungen; zaehler++ )
    {
      ergebnis += pString;
    }

    return ergebnis;
  }

  /**
   *  Sucht den Eingabestring zeilenweise nach dem Suchstring durch.
   *  Liefert die Fund-Zeilen je nach Wert im Parameter "pFunktion" zurueck.
   *  
   * @param pString der Ausgangsstring
   * @param pSuchString Der String, welcher in den Zeilen von pString gefunden werden soll
   * @param pFunktion 1 = Aufnahme wenn Suchstring gefunden (Grep+), 0 = Aufname wenn Suchstring nicht gefunden (Grep-)
   * @return Je nach Wert in pFunktion die Zeilen mit dem Suchstring, oder die Zeilen ohne den Suchstring
   */
  public static String grep( String pString, String pSuchString, int pFunktion )
  {
    /*
     *  
     * Pruefung: pString und pSuchString ungleich Leerstring ?
     */
    if ( pString == null )
    {
      return "";
    }

    if ( pSuchString == null )
    {
      return "";
    }

    if ( pString.length() == 0 )
    {
      return "";
    }

    if ( pSuchString.length() == 0 )
    {
      return "";
    }

    String end_zeilen_umbruch = "";
    String zeichen_zeilenumbruch = "\n"; // das ermittelte Trennzeichen ( bzw. eben Zeilenumbruchzeichen )
    String aktuelle_zeile = ""; // die aktuell gefundene Zeile aus der Eingabe
    String ergebnis_fkt = ""; // Stringbuffer fuer die Rueckgabe
    int aktuelle_startposition = 1; // die akutelle Start-Leseposition
    int naechste_position = 0; // Position des naechsten gefundenen Trennzeichens
    int zeilen_zaehler = 0; // Zaehler fuer Vermeidung von Endlosschleifen
    boolean knz_weiterer_schleifendurchlauf = true; // Kennzeichen ob ein weiterer Schleifendurchlauf notwendig ist
    boolean knz_aufnehmen_wenn_gefunden = false; // Kennzeichen fuer Grep+
    boolean knz_aufnehmen_wenn_nicht_gefunden = false; // Kennzeichen fuer Grep-

    knz_aufnehmen_wenn_gefunden = pFunktion == 1;
    knz_aufnehmen_wenn_nicht_gefunden = !knz_aufnehmen_wenn_gefunden;

    /* 
     * Die Suchschleife laeuft solange wie...
     * ... die Variable "knz_weiterer_schleifendurchlauf" auf TRUE steht
     * ... der Zeilenzaehler noch unter 32123 ist
     */
    while ( ( knz_weiterer_schleifendurchlauf ) && ( zeilen_zaehler < 32123 ) )
    {
      /* 
       * Naechster Zeilenumbruch
       * Ab der aktuellen Startposition wird die naechste Position des
       * Zeilenumbruchzeichens gesucht.
       */
      naechste_position = pString.indexOf( zeichen_zeilenumbruch, aktuelle_startposition );

      /* 
       * Pruefung: Zeilenumbruchzeichen gefunden ?
       */
      if ( naechste_position > 0 )
      {
        /* 
         * Wurde eine naechste Position gefunden, wird die naechste aktuelle Zeile bestimmt.
         * Dafuer wird der Teilstring ab der aktuellen Startposition bis zur Fundstelle des
         * naechsten Trennzeichens bestimmt.
         */
        aktuelle_zeile = pString.substring( aktuelle_startposition, naechste_position );

        /* 
         * Die naechste aktuelle Startposition liegt ab der Fundstellt zuzueglich der
         * Laenge des Trennzeichens (hier = Zeilenumbruchzeichens)
         */
        aktuelle_startposition = naechste_position + zeichen_zeilenumbruch.length();
      }
      else
      {
        /* 
         * Wurde kein weiteres Zeilenumbruchzeichen gefunden, ist in der
         * Variablen "naechste_position" ein Wert von -1 enthalten.
         * 
         * Dieses ist der Hinweis, dass die While-Schleife nicht nochmal durchlaufen werden
         * muss. Die Variable "knz_weiterer_schleifendurchlauf" wird auf FALSE gestellt.
         */
        knz_weiterer_schleifendurchlauf = false;

        /* 
         * Pruefung: Noch ungelesener Teilstring vorhanden ?
         * 
         * Dieses ist der Fall, wenn die letzte Leseposition kleiner gleich
         * der Stringlaenge ist. Von der letzten Leseposition wird bis zum
         * Stringende die aktuelle Zeile ermittelt.
         * 
         * Ist das nicht der Fall, wird die letzte Startposition auf -1 gestellt.
         * Dieses ist der Hinweis, dass es keine aktuelle Zeile gibt und der
         * Inhalt der Variablen "aktuelle_zeile" nicht verarbeitet werden darf.
         */
        if ( aktuelle_startposition <= pString.length() )
        {
          aktuelle_zeile = pString.substring( aktuelle_startposition, pString.length() );
        }
        else
        {
          aktuelle_startposition = -1;
        }
      }
      /* 
       * Pruefung: aktuelle_startposition >= 0 ?
       * 
       * Nur wenn das der Fall ist, darf der Inhalt der Variablen "aktuelle_zeile" verwendet werden.
       */
      if ( aktuelle_startposition >= 0 )
      {
        if ( aktuelle_zeile != null )
        {
          if ( aktuelle_zeile.indexOf( pSuchString ) >= 0 )
          {
            if ( knz_aufnehmen_wenn_gefunden )
            {
              ergebnis_fkt += zeichen_zeilenumbruch + aktuelle_zeile;

              end_zeilen_umbruch = zeichen_zeilenumbruch;
            }
          }
          else
          {
            if ( knz_aufnehmen_wenn_nicht_gefunden )
            {
              ergebnis_fkt += zeichen_zeilenumbruch + aktuelle_zeile;

              end_zeilen_umbruch = zeichen_zeilenumbruch;
            }
          }
        }
      }

      /* 
       * Endlosschleifenverhinderungszaehler 1 weiterstellen
       */
      zeilen_zaehler = zeilen_zaehler + 1;
    }

    /* 
     * Dem Aufrufer das Ergebnis zurueckgeben
     */
    return ergebnis_fkt + end_zeilen_umbruch;
  }

  public static String grep( String pString, String pSuchString, int pFunktion, int pIndex )
  {
    /* 
     * Pruefung: pString und pSuchString ungleich Leerstring ?
     */
    if ( pString == null )
    {
      return null;
    }

    if ( pSuchString == null )
    {
      return null;
    }

    if ( pString.length() == 0 )
    {
      return null;
    }

    if ( pSuchString.length() == 0 )
    {
      return null;
    }

    if ( pIndex <= 0 )
    {
      return null;
    }

    String zeichen_zeilenumbruch = "\n"; // das ermittelte Trennzeichen ( bzw. eben Zeilenumbruchzeichen )
    String aktuelle_zeile = ""; // die aktuell gefundene Zeile aus der Eingabe
    int aktuelle_startposition = 1; // die akutelle Start-Leseposition
    int naechste_position = 0; // Position des naechsten gefundenen Trennzeichens
    int zeilen_zaehler = 0; // Zaehler fuer Vermeidung von Endlosschleifen
    int treffer_zaehler = 0;
    boolean knz_weiterer_schleifendurchlauf = true; // Kennzeichen ob ein weiterer Schleifendurchlauf notwendig ist
    boolean knz_aufnehmen_wenn_gefunden = false; // Kennzeichen fuer Grep+
    boolean knz_aufnehmen_wenn_nicht_gefunden = false; // Kennzeichen fuer Grep-

    knz_aufnehmen_wenn_gefunden = pFunktion == 1;
    knz_aufnehmen_wenn_nicht_gefunden = !knz_aufnehmen_wenn_gefunden;

    /* 
     * Die Suchschleife laeuft solange wie...
     * ... die Variable "knz_weiterer_schleifendurchlauf" auf TRUE steht
     * ... der Zeilenzaehler noch unter 32200 ist
     */
    while ( ( knz_weiterer_schleifendurchlauf ) && ( zeilen_zaehler < 32220 ) )
    {
      /* 
       * Naechster Zeilenumbruch
       * Ab der aktuellen Startposition wird die naechste Position des
       * Zeilenumbruchzeichens gesucht.
       */
      naechste_position = pString.indexOf( zeichen_zeilenumbruch, aktuelle_startposition );// InStr( aktuelle_startposition, pString, zeichen_zeilenumbruch, vbBinaryCompare );

      /* 
       * Pruefung: Zeilenumbruchzeichen gefunden ?
       */
      if ( naechste_position > 0 )
      {
        /* 
         * Wurde eine naechste Position gefunden, wird die naechste aktuelle Zeile bestimmt.
         * Dafuer wird der Teilstring ab der aktuellen Startposition bis zur Fundstelle des
         * naechsten Trennzeichens bestimmt.
         */
        aktuelle_zeile = pString.substring( aktuelle_startposition, naechste_position );

        /* 
         * Die naechste aktuelle Startposition liegt ab der Fundstellt zuzueglich der
         * Laenge des Trennzeichens ( hier = Zeilenumbruchzeichens )
         */
        aktuelle_startposition = naechste_position + zeichen_zeilenumbruch.length();
      }
      else
      {
        /* 
         * Wurde kein weiteres Zeilenumbruchzeichen gefunden, ist in der
         * Variablen "naechste_position" ein Wert von -1 enthalten.
         * 
         * Dieses ist der Hinweis, dass die While-Schleife nicht nochmal durchlaufen werden
         * muss. Die Variable "knz_weiterer_schleifendurchlauf" wird auf FALSE gestellt.
         */
        knz_weiterer_schleifendurchlauf = false;

        /* 
         * Pruefung: Noch ungelesener Teilstring vorhanden ?
         * 
         * Dieses ist der Fall, wenn die letzte Leseposition kleiner gleich 
         * der Stringlaenge ist. Von der letzten Leseposition wird bis zum 
         * Stringende die aktuelle Zeile ermittelt.
         * 
         * Ist das nicht der Fall, wird die letzte Startposition auf -1 gestellt.
         * Dieses ist der Hinweis, dass es keine aktuelle Zeile gibt und der
         * Inhalt der Variablen "aktuelle_zeile" nicht verarbeitet werden darf.
         */
        if ( aktuelle_startposition <= pString.length() )
        {
          aktuelle_zeile = pString.substring( aktuelle_startposition, pString.length() );
        }
        else
        {
          aktuelle_startposition = -1;
        }
      }

      /* 
       * Pruefung: aktuelle_startposition >= 0 ?
       * 
       * Nur wenn das der Fall ist, darf der Inhalt der Variablen "aktuelle_zeile" verwendet werden.
       */
      if ( aktuelle_startposition >= 0 )
      {
        if ( aktuelle_zeile != null )
        {
          if ( aktuelle_zeile.indexOf( pSuchString ) >= 0 )
          {
            if ( knz_aufnehmen_wenn_gefunden )
            {
              treffer_zaehler++;

              if ( treffer_zaehler == pIndex )
              {
                return aktuelle_zeile;
              }
            }
          }
          else
          {
            if ( knz_aufnehmen_wenn_nicht_gefunden )
            {
              treffer_zaehler++;

              if ( treffer_zaehler == pIndex )
              {
                return aktuelle_zeile;
              }
            }
          }
        }
      }

      /* 
       * Endlosschleifenverhinderungszaehler 1 weiterstellen
       */
      zeilen_zaehler = zeilen_zaehler + 1;
    }

    /* 
     * Dem Aufrufer das Ergebnis zurueckgeben
     */
    return null;
  }

  public static String replaceSubstring( String pString, String pSuchString, char pErsatzString )
  {
    return replaceSubstring( pString, pSuchString, "" + pErsatzString );
  }

  /**
   * <pre>
   * Ersetzt im Parameter "pString" den Suchstring gegen den Ersatzstring. 
   * 
   * FkString.replaceSubstring( "ABC.DEF.GHI.JKL", ".", " # " ); = "ABC # DEF # GHI # JKL"
   * FkString.replaceSubstring( "ABC.DEF.GHI.JKL", "-", " # " ); = "ABC.DEF.GHI.JKL"
   * FkString.replaceSubstring( "ABC.DEF.GHI.JKL", ".", ""    ); = "ABCDEFGHIJKL"
   * FkString.replaceSubstring( "ABC.DEF.GHI.JKL", ".", null  ); = "ABC.DEF.GHI.JKL"
   * FkString.replaceSubstring( null,              ".", null  ); = null
   * </pre>
   * 
   * @param pString der String in welchem ersetzt werden soll
   * @param pSuchString der zu suchende Text
   * @param pErsatzString der Ersatztext
   * @return pString mit den gemachten Ersetzungen
   */
  public static String replaceSubstring( String pString, String pSuchString, String pErsatzString )
  {
    /*
     * Parameterpruefung:
     * pString darf nicht null sein und muss eine Laenge von groesser 0 haben
     * pSuchString darf nicht null sein und muss eine Laenge von groesser 0 haben 
     * pErsatzString darf nicht null sein (darf aber eine Laenge von 0 haben)
     */
    if ( pString != null && pSuchString != null && pErsatzString != null && pString.length() > 0 && pSuchString.length() > 0 )
    {
      int laenge_such_string = pSuchString.length();
      int laenge_ersatz_string = pErsatzString.length();
      int aktuelle_start_position = 0;
      int aktuelle_position_gefunden = pString.indexOf( pSuchString, aktuelle_start_position );

      /*
       * While-Schleife
       * Die Schleife laeuft solange, wie noch der Suchstring in der Eingabe gefunden wird. 
       * Kann der Suchstring ab der aktuellen Position nicht mehr gefunden werden, ist der 
       * Wert in der Variablen "aktuelle_position_gefunden" gleich -1.
       */
      while ( aktuelle_position_gefunden >= 0 )
      {
        /*
         * Ersetzung machen
         * Ab Stringanfang bis zur Fundstelle, danach der Ersatztext und der 
         * Reststring ab Fundstelle zuzueglich der Laenge des Suchtextes.  
         */
        pString = pString.substring( 0, Math.max( 0, aktuelle_position_gefunden ) ) + pErsatzString + pString.substring( aktuelle_position_gefunden + laenge_such_string );

        /*
         * Neue Startposition
         */
        aktuelle_start_position = aktuelle_position_gefunden + laenge_ersatz_string;

        /*
         * Neue Fundstelle des Suchtextes ermitteln.
         */
        aktuelle_position_gefunden = pString.indexOf( pSuchString, aktuelle_start_position );
      }
    }

    /*
     * Rueckgabe
     * Am Funktionsende bekommt der Aufrufer "pString" zurueck.
     */
    return pString;
  }



  /**
   * <pre>
   * Ersetzt alle Vorkommen des Suchstrings in pQuellstring mit der Zeichenfolge pStringNeu.
   * </pre>
   * 
   * @param pQuellString der zu durchsuchende String
   * @param pSuchString der Suchstring
   * @param pStringNeu der Ersatzstring fuer den Suchstring (Leerstring oder null = Eliminierung Suchstring)
   * @return ein String, in welchem die Suchzeichenfolge durch die Ersatzzeichenfolge ersetzt wurde
   */
  public static String replace( String pQuellString, String pSuchString, String pStringNeu )
  {
    return replace( pQuellString, pSuchString, pStringNeu, 1 );
  }

  /**
   * <pre>
   * Ersetzt alle Vorkommen des Suchstrings in pQuellstring mit der Zeichenfolge pStringNeu.
   * 
   * Die Gross-Kleinschreibung kann mit der Uebergabe von 0 im Parameter "pKnzGrossKleinschreibung" ignoriert werden.
   * 
   * Alle anderen Werte im Parameter "pKnzGrossKleinschreibung" fuehren zu einer Beachtung der GK-Schreibung. 
   * 
   * Ist "pQuellString" oder "pSuchString" gleich "null", wird "null" zurueckgegeben.
   * 
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".XYZ.",  "-", 1 ) = "ABC-def.xyz.GHI.xYz.jkl"
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".XYZ.",  "-", 0 ) = "ABC-def-GHI-jkl"
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".jkl",   "-", 0 ) = "ABC.XYZ.def.xyz.GHI.xYz-"
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".jkl",  null, 0 ) = "ABC.XYZ.def.xyz.GHI.xYz"
   * </pre>
   * 
   * @param pQuellString der zu durchsuchende String
   * @param pSuchString der Suchstring
   * @param pStringNeu der Ersatzstring fuer den Suchstring (Leerstring oder null = Eliminierung Suchstring)
   * @param pKnzGrossKleinschreibung Kennzeichen, ob die Gross/Kleinschreibung beim Suchen beachtet werden soll (1=beachten (voreingestellt), 0=ignorieren)
   * @return ein String, in welchem die Suchzeichenfolge durch die Ersatzzeichenfolge ersetzt wurde
   */
  public static String replace( String pQuellString, String pSuchString, String pStringNeu, int pKnzGrossKleinschreibung )
  {
    /*
     * Pruefung: Ist der Quellstring, oder der Suchstring nicht gesetzt, wird der 
     * Quellstring zurueckgegeben.
     */
    if ( ( pQuellString == null ) || ( pSuchString == null ) )
    {
      return pQuellString;
    }

    String such_string_ucase = ""; // Suchtext 
    String quell_string_ucase = ""; // der zu durchsuchende Text
    String str_ergebnis = ""; // Stringbuffer fuer die Rueckgabe
    int position_such_string = 0; // die aktuell gefundene Startposition des Suchstrings
    int position_such_prozess = 0; // die aktuelle AB-Position fuer die Suche im Quellstring
    int position_such_bis = 32123; // die Position, bis zu welcher gesucht wird.
    int zaehler = 0; // Zaehler zur Vermeidung von Endlossschleifen

    /* 
     * Variableninitialisierung
     * Soll die Gross/Kleinschreibung beruecksichtigt werden, werden die beiden 
     * Strings unveraendert in die funktionsinternen Variablen uebernommen.
     *  
     * Soll die Gross/Kleinschreibung unberuecksichtigt bleiben, wrid der 
     * zu durchsuchende und der Suchstring in Grossbuchstaben gewandelt.
     */
    if ( pKnzGrossKleinschreibung == 0 )
    {
      such_string_ucase = pSuchString.toUpperCase();

      quell_string_ucase = pQuellString.toUpperCase();
    }
    else
    {
      such_string_ucase = pSuchString;

      quell_string_ucase = pQuellString;
    }

    /* 
     * Erste Position des Suchstrings ermitteln
     */
    position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

    /*
     * Pruefung: Suchstring nicht gefunden ?
     * Ist der Suchstring nicht im Quellstring vorhanden, ist das Funktionsergebnis 
     * gleich dem Quellstring, da es nichts zu ersetzen gibt. Durch Rueckgabe des 
     * Quellstringsie wird diese Funktion beendet.
     */
    if ( position_such_string < 0 )
    {
      return pQuellString;
    }

    /* 
     * Die Suchschleife wird solange durchlaufen wie
     * ... die Position des Suchstrings noch groesser als 0 ist
     * ... der Zaehler noch kleiner der position_such_bis ist (Vermeidung Endlossschleife)
     */
    while ( ( position_such_string >= 0 ) && ( zaehler < position_such_bis ) )
    {
      /* 
       * Pruefung: Suchstring gefunden ?
       * Das ist der Fall, wenn die Positon einen Wert groesser 0 hat.
       */
      if ( position_such_string >= 0 )
      {
        /* 
         * Ergebnisstring aufbauen
         * 1. Teilstring aus dem Quellstring ab der aktuellen Leseposition bis 
         * zur Fundstelle des Suchstrings in den Ergebnisstring kopieren. 
         */
        str_ergebnis += pQuellString.substring( position_such_prozess, position_such_string );

        /*
         * 2. Die Ersatzzeichenfolge dem Ergebnisstring hinzufuegen. Das Hinzufuegen
         * wird nur gemacht wenn die Ersatzzeichenfolge ungleich "null" ist. 
         *  
         * Ist "pStringNeu" ein Leerstring (oder eben ein Null-Pointer), wird durch 
         * diese Funktion nur der Suchstring aus dem Quellstring entfernt. Es gibt 
         * keinen Ersatzstring.
         */
        if ( pStringNeu != null )
        {
          str_ergebnis += pStringNeu;
        }

        /* 
         * Position Leseprozess setzen
         * Die neue Startposition fuer den naechsten Suchvorgang beginnt ab der
         * eben gefundenen Position des Suchstrings zuzueglich dessen Laenge.
         */
        position_such_prozess = position_such_string + such_string_ucase.length();
      }

      /* 
       * Position Suchstring ermitteln
       * Im Upper-Case-Quellstring wird der Upper-Case-Suchstring gesucht. Somit
       * wird die Gross/Klein-Schreibung eliminiert. Die Position wird in der
       * Variablen "position_such_string" gespeichert.
       */
      position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

      /* 
       * Zaehler erhoehen
       * Der Zaehler fuer die Vermeidung einer Endlosschleife wird um 1 erhoeht.
       */
      zaehler++;
    }

    /* 
     * Pruefung: wurden alle Zeichen der Eingabe behandelt ?
     * Ist nach der Schleife die Position des Suchprozesses kleiner als die
     * Laenge des Quellstrings, wird der Rest vom Quellstring ab der letzten
     * Leseposition dem Ergebnis hinzugefuegt. 
     */
    if ( position_such_prozess < pQuellString.length() )
    {
      str_ergebnis += pQuellString.substring( position_such_prozess );
    }

    return str_ergebnis;
  }

  public static String replacex( String pQuellString, String pSuchString, String pStringNeu, int pKnzGrossKleinschreibung )
  {
    /*
     * Pruefung: Ist der Quellstring, oder der Suchstring nicht gesetzt, wird der 
     * Quellstring zurueckgegeben.
     */
    if ( ( pQuellString == null ) || ( pSuchString == null ) )
    {
      return pQuellString;
    }

    String such_string_ucase = ""; // Suchtext 
    String quell_string_ucase = ""; // der zu durchsuchende Text
    String str_ergebnis = ""; // Stringbuffer fuer die Rueckgabe
    int position_such_string = 0; // die aktuell gefundene Startposition des Suchstrings
    int position_such_prozess = 0; // die aktuelle AB-Position fuer die Suche im Quellstring
    int position_such_bis = 32123; // die Position, bis zu welcher gesucht wird.
    int zaehler = 0; // Zaehler zur Vermeidung von Endlossschleifen

    /* 
     * Variableninitialisierung
     * Soll die Gross/Kleinschreibung beruecksichtigt werden, werden die beiden 
     * Strings unveraendert in die funktionsinternen Variablen uebernommen.
     *  
     * Soll die Gross/Kleinschreibung unberuecksichtigt bleiben, wrid der 
     * zu durchsuchende und der Suchstring in Grossbuchstaben gewandelt.
     */
    if ( pKnzGrossKleinschreibung == 0 )
    {
      such_string_ucase = pSuchString.toUpperCase();

      quell_string_ucase = pQuellString.toUpperCase();
    }
    else
    {
      such_string_ucase = pSuchString;

      quell_string_ucase = pQuellString;
    }

    /* 
     * Erste Position des Suchstrings ermitteln
     */
    position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

    /*
     * Pruefung: Suchstring nicht gefunden ?
     * Ist der Suchstring nicht im Quellstring vorhanden, ist das Funktionsergebnis 
     * gleich dem Quellstring, da es nichts zu ersetzen gibt. Durch Rueckgabe des 
     * Quellstringsie wird diese Funktion beendet.
     */
    if ( position_such_string < 0 )
    {
      return pQuellString;
    }

    /* 
     * Die Suchschleife wird solange durchlaufen wie
     * ... die Position des Suchstrings noch groesser als 0 ist
     * ... der Zaehler noch kleiner der position_such_bis ist ( Vermeidung Endlossschleife )
     */
    while ( ( position_such_string >= 0 ) && ( zaehler < position_such_bis ) )
    {
      /* 
       * Pruefung: Suchstring gefunden ?
       * Das ist der Fall, wenn die Positon einen Wert groesser 0 hat.
       */
      if ( position_such_string >= 0 )
      {
        /* 
         * Ergebnisstring aufbauen
         * 1. Teilstring aus dem Quellstring ab der aktuellen Leseposition bis 
         * zur Fundstelle des Suchstrings in den Ergebnisstring kopieren. 
         */
        str_ergebnis += pQuellString.substring( position_such_prozess, position_such_string );

        /*
         * 2. Die Ersatzzeichenfolge dem Ergebnisstring hinzufuegen. Das hinzufuegen
         * wird nur gemacht wenn die Ersatzzeichenfolge kein Null-Pointer ist. 
         *  
         * Ist "pStringNeu" ein Leerstring (oder eben ein Null-Pointer), wird durch 
         * diese Funktion nur der Suchstring aus dem Quellstring entfernt. Es gibt 
         * keinen Ersatzstring.
         */
        if ( pStringNeu != null )
        {
          str_ergebnis += pStringNeu;
        }

        /* 
         * Position Leseprozess setzen
         * Die neue Startposition fuer den naechsten Suchvorgang beginnt ab der
         * eben gefundenen Position des Suchstrings zuzueglich dessen Laenge.
         */
        position_such_prozess = position_such_string + such_string_ucase.length();
      }

      /* 
       * Position Suchstring ermitteln
       * Im Upper-Case-Quellstring wird der Upper-Case-Suchstring gesucht. Somit
       * wird die Gross/Klein-Schreibung eliminiert. Die Position wird in der
       * Variablen "position_such_string" gespeichert.
       */
      position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

      /* 
       * Zaehler erhoehen
       * Der Zaehler fuer die Vermeidung einer Endlosschleife wird um 1 erhoeht.
       */
      zaehler++;
    }

    /* 
     * Pruefung: wurden alle Zeichen der Eingabe behandelt ?
     * Ist nach der Schleife die Position des Suchprozesses kleiner als die
     * Laenge des Quellstrings, wird der Rest vom Quellstring ab der letzten
     * Leseposition dem Ergebnis hinzugefuegt. 
     */
    if ( position_such_prozess < pQuellString.length() )
    {
      str_ergebnis += pQuellString.substring( position_such_prozess );
    }

    return str_ergebnis;
  }

  /**
   * <pre>
   * Ersetzt im Quellstring alle Vorkommen des Suchstrings mit einer laufenden Zahl.
   * 
   * Mit den Parametern "pErsatzPraefix" und "pErsatzSuffix" kann vor und hinter 
   * der laufenden Zahl noch ein zusaetzlicher Text hinzugefuegt werden.
   * (z.B. Zahl in Anfuehrungszeichen setzen)  
   *  
   * Mit dem Parameter "pStartWertZaehler" wird der Startwert der laufenden Zahl gesetzt.
   * 
   *  try
   *  {
   *    int start_wert_zaehler = 11;
   *  
   *    String ersatz_praefix = " B Nr. \"";
   *    String ersatz_suffix  = "\" ";
   *  
   *    String such_string  = "SUCH_STRING_X";
   *  
   *    String quell_string = "";
   *  
   *    quell_string += "\n  A " + such_string + " C ";
   *    quell_string += "\n  A ";
   *    quell_string += "\n  A " + such_string + " C ";
   *    quell_string += "\n  A ";
   *    quell_string += "\n  A " + such_string + " C ";
   *    quell_string += "\n  A ";
   *    quell_string += "\n  A " + such_string + " C ";
   *  
   *    String ergebnis_string = replaceLfdZahl( quell_string, such_string, ersatz_praefix, ersatz_suffix, start_wert_zaehler );
   *  
   *    System.out.println( ergebnis_string );
   *  }
   *  catch ( Exception err_inst )
   *  {
   *    wl( "Fehler: errReplaceLfdZahl", err_inst );
   *  }
   *  
   *  
   *  
   * Erstellt die Ausgabe:  
   * 
   *   A  B Nr. "11"  C 
   *   A 
   *   A  B Nr. "12"  C 
   *   A 
   *   A  B Nr. "13"  C 
   *   A 
   *   A  B Nr. "14"  C 
   *  
   *  
   * </pre>
   * 
   * @param pQuellString der zu durchsuchende String
   * @param pSuchString der Suchstring
   * @param pErsatzPraefix optionaler String, welcher vor die Zahl gesetzt wird
   * @param pErsatzSuffix optionaler String, welcher hinter die Zahl gesetzt wird
   * @param pStartWertZaehler der Startwert fuer die laufende Zahl
   * @return den String mit den Ersetzungen
   */
  public static String replaceLfdZahl( String pQuellString, String pSuchString, String pErsatzPraefix, String pErsatzSuffix, int pStartWertZaehler )
  {
    if ( ( pQuellString == null ) || ( pSuchString == null ) )
    {
      return pQuellString;
    }

    String such_string_ucase = ""; // Suchtext 
    String quell_string_ucase = ""; // der zu durchsuchende Text
    String str_ergebnis = ""; // Stringbuffer fuer die Rueckgabe
    int position_such_string = 0; // die aktuell gefundene Startposition des Suchstrings
    int position_such_prozess = 0; // die aktuelle AB-Position fuer die Suche im Quellstring
    int position_such_bis = 32123; // die Position, bis zu welcher gesucht wird.
    int zaehler_while_schleife = 0; // Zaehler zur Vermeidung von Endlossschleifen
    int zaehler_laufend = pStartWertZaehler; // bei den Erstzungen hinzuzufuegender Wert (wird nach jedem Vorkommen des Suchtextes um eins hochgezaehlt)

    /* 
     * Variableninitialisierung
     * Soll die Gross/Kleinschreibung beruecksichtigt werden, werden die beiden 
     * Strings unveraendert in die funktionsinternen Variablen uebernommen.
     *  
     * Soll die Gross/Kleinschreibung unberuecksichtigt bleiben, wrid der 
     * zu durchsuchende und der Suchstring in Grossbuchstaben gewandelt.
     */
    int pKnzGrossKleinschreibung = 1;

    if ( pKnzGrossKleinschreibung == 0 )
    {
      such_string_ucase = pSuchString.toUpperCase();

      quell_string_ucase = pQuellString.toUpperCase();
    }
    else
    {
      such_string_ucase = pSuchString;

      quell_string_ucase = pQuellString;
    }

    /* 
     * Erste Position des Suchstrings ermitteln
     */
    position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

    /*
     * Pruefung: Suchstring nicht gefunden ?
     * Ist der Suchstring nicht im Quellstring vorhanden, ist das Funktionsergebnis 
     * gleich dem Quellstring, da es nichts zu ersetzen gibt. Durch Rueckgabe des 
     * Quellstringsie wird diese Funktion beendet.
     */
    if ( position_such_string < 0 )
    {
      return pQuellString;
    }

    /* 
     * Die Suchschleife wird solange durchlaufen wie
     * ... die Position des Suchstrings noch groesser als 0 ist
     * ... der Zaehler noch kleiner der position_such_bis ist ( Vermeidung Endlossschleife )
     */
    while ( ( position_such_string >= 0 ) && ( zaehler_while_schleife < position_such_bis ) && ( zaehler_laufend < Integer.MAX_VALUE ) )
    {
      /* 
       * Pruefung: Suchstring gefunden ?
       * Das ist der Fall, wenn die Positon einen Wert groesser 0 hat.
       */
      if ( position_such_string >= 0 )
      {
        /* 
         * Ergebnisstring aufbauen
         * 1. Teilstring aus dem Quellstring ab der aktuellen Leseposition bis 
         * zur Fundstelle des Suchstrings in den Ergebnisstring kopieren. 
         */
        str_ergebnis += pQuellString.substring( position_such_prozess, position_such_string );

        /*
         * 2. Stringersatz
         * 
         * Praefix hinzufuegen, wenn ungleich null
         * 
         * Laufenden Zaehler hinzufuegen
         * 
         * Suffix hinzufuegen, wenn ungleich null
         * 
         * Laufenden Zaehler um eins erhoehen
         */
        if ( pErsatzPraefix != null )
        {
          str_ergebnis += pErsatzPraefix;
        }

        str_ergebnis += zaehler_laufend;

        if ( pErsatzSuffix != null )
        {
          str_ergebnis += pErsatzSuffix;
        }

        zaehler_laufend++;

        /* 
         * Position Leseprozess setzen
         * Die neue Startposition fuer den naechsten Suchvorgang beginnt ab der
         * eben gefundenen Position des Suchstrings zuzueglich dessen Laenge.
         */
        position_such_prozess = position_such_string + such_string_ucase.length();
      }

      /* 
       * Position Suchstring ermitteln
       * Im Upper-Case-Quellstring wird der Upper-Case-Suchstring gesucht. Somit
       * wird die Gross/Klein-Schreibung eliminiert. Die Position wird in der
       * Variablen "position_such_string" gespeichert.
       */
      position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

      /* 
       * Zaehler erhoehen
       * Der Zaehler fuer die Vermeidung einer Endlosschleife wird um 1 erhoeht.
       */
      zaehler_while_schleife++;
    }

    /* 
     * Pruefung: wurden alle Zeichen der Eingabe behandelt ?
     * Wenn nach der Schleife die Position des Suchprozesses kleiner als die
     * Laenge des Quellstrings ist, wird der Rest vom Quellstring ab der letzten
     * Leseposition dem Ergebnis hinzugefuegt. 
     */
    if ( position_such_prozess < pQuellString.length() )
    {
      str_ergebnis += pQuellString.substring( position_such_prozess );
    }

    return str_ergebnis;
  }

  /**
   * <pre>
   * Ersetzt in den Zeilen von "pText", den "pSuchString1" mit dem "pReplaceString1". 
   * Dieses nur in denjenigen Zeilen, in welchen "pTextSuchString" gefunden wird und auch 
   * nur hinter dessen Position.
   *  
   * String text_string = "";
   *
   * text_string += "\n   ABC.DEF.GHI";
   * text_string += "\n * ABC.DEF.GHI";
   * text_string += "\n   ABC.DEF.GHI";
   * text_string += "\n * ABC.DEF.GHI";
   * 
   * String text_such_string = "*";
   * 
   * String such_string_1 = "ABC";
   * 
   * String replace_string_1 = "---";
   * 
   * String text_string_2 = FkString.replaceAb( text_string, text_such_string, such_string_1, replace_string_1 );
   * 
   * System.out.println( text_string_2 );
   *
   *     ABC.DEF.GHI
   *     * ---.DEF.GHI
   *     ABC.DEF.GHI
   *     * ---.DEF.GHI
   *  
   * </pre>
   * 
   * @param pText der zu bearbeitende Text
   * @param pTextSuchString der Suchstring im Text, dessen Vorkommen die AB-Positon der Ersetzungen bestimmt
   * @param pSuchString1 der im Teilstring zu suchende Text
   * @param pReplaceString1 der im Teilstring zu ersetzende Text
   * @return den sich ergebenden String
   */
  public static String replaceAb( String pText, String pTextSuchString, String pSuchString1, String pReplaceString1 )
  {
    String str_replace = "";
    String str_ergebnis = "";

    String text_such_str_1 = ( pTextSuchString == null ? "" : pTextSuchString );

    int len_text_such_str_1 = text_such_str_1.length();

    String csv_trennzeichen = "\n"; // das zu suchende CSV-Trennzeichen (hier eben ein Zeilenumbruch)
    String csv_akt_string = null; // speichert die aktuell gelesene Zeile aus dem Parameter "pText"

    int aktuelle_startposition = 0; // die akutelle Start-Leseposition der aeusseren Schleife
    int naechste_position = 0; // Position des naechsten gefundenen Trennzeichens
    int csv_zaehler = 0; // Zaehler fuer Vermeidung von Endlosschleifen
    int csv_start = 0; // Startposition des aktuellen Strings aus dem Eingabeparameter "pText" 
    int csv_ende = 0; // Endposition des aktuellen Strings aus dem Eingabeparameter "pText" 

    try
    {
      /* 
       * Pruefung: pText gesetzt? 
       */
      if ( pText == null )
      {
        /*
         * Kein Text vorhanden
         * Ist der Parameter "pText" gleich "null", wird "null" zurueckgegeben.
         */
        return null;
      }
      else if ( pSuchString1 == null )
      {
        /*
         * Kein Suchstring fuers Ersetzen vorhanden
         * Ist der Parameter "pSuchString1" gleich "null", wird "pText" zurueckgegeben.
         * Es gibt in "pText" nichts zu suchen, was sich ersetzen lassen wuerde.
         */
        return pText;
      }
      else if ( len_text_such_str_1 == 0 )
      {
        /*
         * Keine Einschraenkung fuer Replace-Funktion
         * Ist der Parameter "pTextSuchString" gleich einem Leerstring, gibt es keine 
         * Einschraenkung in der Replace-Funktion. Es werden in diesem Fall, alle 
         * Ersetzungen von "pSuchString1" gemacht.
         * 
         * Es wird ein normales Suchen/Ersetzen gemacht und dieses dem Aufrufer 
         * zurueckgegeben.
         */
        return FkString.replace( pText, pSuchString1, pReplaceString1 );
      }
      else
      {
        /*
         * Start der eigentlichen Funktion
         * 
         */
        /* 
         * Die Suchschleife laeuft solange wie...
         * ... die Variable "naechste_position" groesser/gleich 0 ist 
         *     (das Trennzeichen noch gefunden wurde) 
         * ... der CSV-Zaehler noch kleiner als 32123 ist
         */
        while ( ( naechste_position >= 0 ) && ( csv_zaehler <= 32123 ) )
        {
          /* 
           * Naechste Position Trennzeichen
           * Ab der aktuellen Startposition wird die naechste Position des Trennzeichens gesucht
           */
          csv_start = aktuelle_startposition;

          naechste_position = pText.indexOf( csv_trennzeichen, csv_start );

          /* 
           * Pruefung: Trennzeichen gefunden ?
           */
          if ( naechste_position >= 0 )
          {
            /*
             * Endposition Teilstring
             * Die Position CSV-Ende ist die Position des naechsten gefundenen Trennzeichens.
             * Diese Position wird nicht Bestandteil des Ergebnisstrings, da die genutzte
             * "subString()"-Funktion BIS VOR diesesr Position abschneidet. 
             */
            csv_ende = naechste_position;

            /* 
             * Startposition aktualisieren
             * Die naechste aktuelle Startposition liegt ab der Fundstelle zuzueglich der
             * Laenge des Trennzeichens (hier = Zeilenumbruchzeichens).
             */
            aktuelle_startposition = naechste_position + csv_trennzeichen.length();
          }
          else
          {
            /* 
             * Pruefung: Noch ungelesener Teilstring vorhanden ?
             * 
             * Ist die letzte Leseposition kleiner gleich der Stringlaenge, wird von 
             * der letzten Leseposition bis zum Stringende die aktuelle Zeile ermittelt.
             * 
             * Ist das nicht der Fall, wird die letzte Startposition auf -1 gestellt.
             */
            if ( csv_start <= pText.length() )
            {
              csv_ende = pText.length();
            }
            else
            {
              csv_start = -1;
            }
          }

          /* 
           * Pruefung: csv_start >= 0 ?
           * 
           * Nur wenn das der Fall ist, darf weitergemacht werden.
           */
          if ( csv_start >= 0 )
          {
            if ( csv_start >= csv_ende )
            {
              /*
               * Ist die Startposition groesser oder gleich der Endposition, ist 
               * der aktuelle csv-String ein Leerstring.
               * 
               * Der aktuelle String wird auf einen Leerstring gesetzt. 
               * Es muss kein String aus der Eingabe herausgelesen werden.
               */
              csv_akt_string = "";
            }
            else
            {
              /*
               * Ist die Startposition kleiner als die Endposition, ist der aktuelle 
               * String mindestens 1 Zeichen lang. 
               * 
               * Der aktuelle String wird aus dem Eingabe-String gelesen.
               */
              try
              {
                csv_akt_string = pText.substring( csv_start, csv_ende );
              }
              catch ( StringIndexOutOfBoundsException err_inst ) // Hartnaeckige Fehler umgehen
              {
                csv_akt_string = "Fehler " + csv_start + " " + csv_ende;

                csv_start = -1; // Abbruchbedingung setzten 
              }
            }

            int pos_suchstring_1 = csv_akt_string.indexOf( text_such_str_1 );

            if ( pos_suchstring_1 < 0 )
            {
              /*
               * Suchstring nicht gefunden
               * Wird der Suchstring innerhalb der aktuellen Zeile nicht gefunden, 
               * sind in der aktuellen Zeile keine Ersetzungen zu machen. 
               * 
               * Die aktuelle Zeile wird dem Ergebnisstring hinzugefuegt.
               */
              str_ergebnis += csv_akt_string + "\n";
            }
            else
            {
              /*
               * Suchstring gefunden
               * Wird der Suchstring gefunden, wird in der Variablen "str_replace" der 
               * Teilstring fuer die Replace-Funktion gespeichert. 
               * 
               * Der Teilstring ist derjenige, ab der Position des Suchstrings zuzueglich 
               * dessen Laenge, bis zum Ende des aktuellen Strings.
               * 
               * In diesem String wird dann die Replace-Funktion ausgefuehrt.
               * 
               * Zum Schluss werden die beiden Teilstrings dem Funktionsergebnis wieder 
               * hinzugefuegt. 
               * 
               * (In einer kopierten spezialisierten Version dieser Funktion, koennen 
               *  hier weitere zu machende Esetzungen eingebaut werden)
               */
              str_replace = csv_akt_string.substring( pos_suchstring_1 + len_text_such_str_1 );

              str_replace = FkString.replace( str_replace, pSuchString1, pReplaceString1 );

              str_ergebnis += csv_akt_string.substring( 0, pos_suchstring_1 + len_text_such_str_1 ) + str_replace + "\n";
            }
          }
        }
      }
    }
    catch ( Exception err_inst )
    {
      FkLogger.wl( "Fehler: errReplaceAb ", err_inst );
    }

    /*
     * Nach der While-Schleife werden die Variablen auf null gesetzt.
     */
    csv_trennzeichen = null;

    csv_akt_string = null;

    return str_ergebnis;
  }

  /**
   * <pre>
   * Fuegt nach Suchstring1 den PlaceString1 hinzu.
   * Fuegt nach Suchstring2 den PlaceString2 hinzu.
   * 
   * FkString.placeX( "ABCDEF  ABCDEF", "BC",  "EF", "->",  "<-" ) = "ABC->D<-EF  ABC->D<-EF"
   * 
   * FkString.placeX( "ABCDEF  ABCDEF", "XY",  "EF", "->",  "<-" ) = "ABCDEF  ABCDEF"
   * 
   * FkString.placeX( "ABCDEF  ABCDEF", "BC",  "EF", "->",  null ) = "ABC->DEF  ABC->DEF"
   * 
   * FkString.placeX( "ABCDEF  ABCDEF", "BC",  "EF", null,  "<-" ) = "ABCD<-EF  ABCD<-EF"
   * 
   * FkString.placeX( "ABCDEF  ABCDEF", "BC", null", "->",  "<-" ) = "ABC-><-DEF  ABC-><-DEF"
   * 
   * FkString.placeX( "ABCDEF  ABCDEF", null,  "EF", "->",  "<-" ) = "ABCDEF-><-  ABCDEF-><-"
   * 
   * Suchstrings stehen im Eingabestring hintereinander
   * 
   * FkString.placeX( "ABABAB", "A", "B", ">", "<" ) = "A><BA><BA><B"
   * 
   * Das Hinzufuegen erfolgt immer paarweise, falsche Fundstellen von 
   * Suchstring 1 werden uebersprungen.
   * 
   * FkString.placeX( "ACCACB", "A", "B", ">", "<" ) = "ACCA>C<B"
   * </pre>
   * 
   * @param pString der zu durchsuchende String 
   * @param pSuchString1 der erste zu suchende String
   * @param pSuchString2 der zweite zu suchende String
   * @param pPlace1 String1 nach pSuchString1
   * @param pPlace2 Strign2 vor pSuchstring2 
   * @return den sich ergebenden String
   */
  public static String placeX( String pString, String pSuchString1, String pSuchString2, String pPlace1, String pPlace2 )
  {
    /*
     * Setzen Ergebnisstring mit dem Eingabestring "pString"
     */
    String ergebnis_string = pString;

    /*
     * Suchstrings setzen
     * Die Parameter werden in interne Variablen kopiert. 
     * Ist Suchstring 1 gleich "null", wird der erste Suchstring mit dem 
     * zweiten Suchstring belegt. Der zweite Suchstring wird zu "null".
     */
    String such_string_1 = pSuchString1;
    String such_string_2 = pSuchString2;

    if ( such_string_1 == null )
    {
      such_string_1 = pSuchString2;
      such_string_2 = null;
    }

    /*
     * Pruefung: Suchparameter gesetzt?
     * Nur wenn ein Suchstring vorhanden ist, kann es einen sinvollen Rueckgabewert
     * geben. Ist kein Suchstring vorhanden, wird ein Leerstring zurueckgegeben. 
     * 
     * Es wird nur der erste Suchstring auf gesetzt geprueft. 
     * Der zweite Suchstring ist optional.
     */
    if ( ( ( ergebnis_string != null ) && ( ergebnis_string.trim().length() > 0 ) ) && ( ( such_string_1 != null ) && ( such_string_1.trim().length() > 0 ) ) )
    {
      /*
       * Uebernahme der einzufuegenden Place-Strings. 
       * 
       * Ist ein Parameter null, wird dieser Place-String zu einem Leerstring.
       */
      String str_place_1 = ( pPlace1 == null ? "" : pPlace1 );
      String str_place_2 = ( pPlace2 == null ? "" : pPlace2 );

      int position_suchstring_1 = 0; // Position Suchstring 1 in der aeusseren Schleife
      int position_suchstring_1b = 0; // Position Suchstring 1 fuer die Kontrollscheife  
      int position_suchstring_2 = 0; // Position Suchstring 2

      int zaehler = 0; // Endlosschleifenverhinderungszaehler

      /*
       * Suchstring 1 suchen
       * In der Eingabe wird die Postion des ersten Suchstrings ermittelt.
       */
      position_suchstring_1 = ergebnis_string.indexOf( such_string_1 );

      /* 
       * Pruefung: Gibt es eine Startposition?
       * Wurde der erste Suchstring gefunden, ist in der Variablen fuer
       * den Startwert ein Wert von groesser 0 enthalten. 
       * 
       * Wurde der erste Suchstring nicht gefunden werden, wird der 
       * zweite Suchstring nicht gesucht.
       */
      while ( ( position_suchstring_1 >= 0 ) && ( position_suchstring_2 >= 0 ) && ( zaehler < 32123 ) )
      {
        /*
         * Anpassung der Startposition fuer die Suche nach dem zweiten Suchstring 
         * und fuer das nachfolgende heraustrennen.
         */
        position_suchstring_1 = position_suchstring_1 + such_string_1.length();

        /*
         * Pruefung: Suchstring 2 vorhanden?
         * Gibt es keinen zweiten Suchstring, wird nur der erste Suchstring genommen.
         * Es werden in diesem Fall, aber auch beide Place-Strings hinzugefuegt.
         */
        if ( such_string_2 == null )
        {
          ergebnis_string = ergebnis_string.substring( 0, position_suchstring_1 ) + str_place_1 + str_place_2 + ergebnis_string.substring( position_suchstring_1 );
        }
        else
        {
          /* 
           * Suchstring 2 suchen
           * Ab der (korrigierten) Startposition 1 wird der Suchstring 2 gesucht.
           */
          position_suchstring_2 = ergebnis_string.indexOf( such_string_2, position_suchstring_1 );

          //FkLogger.wl( " " + position_suchstring_1 + " - " + position_suchstring_2 );

          /* 
           * Pruefung: Endposition gefunden?
           * Die Position des zweiten Suchstrings muss groesser oder gleich der Position 1 sein.
           * 
           * Sind die Positionsangaben gleich, stehen die beiden Suchbegriffe im String 
           * hintereinander.
           */
          if ( position_suchstring_2 >= position_suchstring_1 )
          {
            /*
             * Kontrolle Suchstring 1
             * Es kann passieren, dass durch das Suchen von Suchstring2, der erste Suchstring 
             * uebersprungen wird:
             * 
             *   A WERT B TTT  |  A> WERT <B TTT
             *   A WERT C TTT  |  A WERT C TTT    <---- Suchstring A wird gefunden, aber nicht B 
             * 
             * Es wird die letzte Position des Suchstrings 1 gesucht, welche 
             * sich noch vor der Position des zweiten Suchstrings befindet.
             */
            position_suchstring_1b = ergebnis_string.indexOf( such_string_1, position_suchstring_1 );

            while ( ( position_suchstring_1b > position_suchstring_1 ) && ( position_suchstring_1b < position_suchstring_2 ) )
            {
              if ( ( position_suchstring_1b > position_suchstring_1 ) && ( position_suchstring_1b < position_suchstring_2 ) )
              {
                position_suchstring_1 = position_suchstring_1b + such_string_1.length();

                position_suchstring_1b = ergebnis_string.indexOf( such_string_1, position_suchstring_1 );
              }
              else
              {
                position_suchstring_1b = -1;
              }
            }

            /*
             * Aufbau Ergebnisstring
             * 1. Den String von 0 bis zur Postion des Suchstrings 1. 
             * 
             * 2. Den Place 1 String 
             * 
             * 3. Den String zwischen Position Suchstring 1 und Suchstring 2 
             * 
             * 4. Den Place 2 String 
             * 
             * 5. Den String ab der Position Suchstring 2
             */
            ergebnis_string = ergebnis_string.substring( 0, position_suchstring_1 ) + str_place_1 + ergebnis_string.substring( position_suchstring_1, position_suchstring_2 ) + str_place_2 + ergebnis_string.substring( position_suchstring_2 );

            /*
             * Berechnung neue Startsuchposition 1
             * 
             * Dem Ergebnisstring wurden die Stringlaengen von "str_place_1" und "str_place_2" hinzugefuegt.
             * Die Postition 2 hat sich um diese Stringlaengen nach hinten verschoben. 
             * 
             * Die Stringlaenge des Suchstrings2 muss auch uebersprungen werden.
             * 
             * Die neue Startposition liegt somit ab Position-Suchstring 2 zuzueglich 
             * der zu ueberspringenden Stringlaengen. 
             */
            position_suchstring_1 = position_suchstring_2 + such_string_2.length() + str_place_1.length() + str_place_2.length();
          }
        }

        /*
         * Am Schleifenende wird die naechste Position des ersten Suchstrings ermittelt.
         * Wird der erste Suchstring nicht mehr gefunden ist die Schleife beendet.  
         */
        position_suchstring_1 = ergebnis_string.indexOf( such_string_1, position_suchstring_1 );

        /*
         * Endlosschleifenverhinderungszaehler erhoehen.
         */
        zaehler++;
      }
    }

    /*
     * Rueckgabe des Ergebnisses.
     */
    return ergebnis_string;
  }

  /**
   * @param pString der Eingabestring 
   * @param pSuchString die zu suchende Zeichenfolge
   * @return die Position des Auftretens
   */
  public static int getLetztePosition( String pString, String pSuchString )
  {
    if ( pString == null )
    {
      return -1;
    }

    if ( pSuchString == null )
    {
      return -1;
    }

    return pString.lastIndexOf( pSuchString );
  }

  /**
   * <pre>
   * Sucht in "pString" ab der Startposition, das naechste Worttrennzeichen.
   * 
   * Definierte Worttrennzeichen sind " :.;,\"()-+=\\/[]{}=*?!#<>|&%�"
   *
   *  0123456789012
   * " ABC DEF GHI"
   * 
   * FkString.getWortEnde( " ABC DEF GHI",   1 ) = 4
   * FkString.getWortEnde( " ABC DEF GHI",   2 ) = 4
   * FkString.getWortEnde( " ABC DEF GHI",   3 ) = 4
   * FkString.getWortEnde( " ABC DEF GHI",   4 ) = 4
   * 
   * FkString.getWortEnde( " ABC DEF GHI",   5 ) = 8
   * 
   * FkString.getWortEnde( " ABC DEF GHI",   9 ) = 11
   * 
   * FkString.getWortEnde( " ABC DEF GHI", -30 ) = 0
   * 
   * FkString.getWortEnde( " ABC DEF GHI",  30 ) = -1
   * 
   * FkString.getWortEnde( null,             2 ) = -1
   * </pre>
   * 
   * @param pString der Eingabestring
   * @param pStartPosition die Startposition im Ergebnisstring. Ist diese kleiner 0, faengt die Suche bei Position 0 an.
   * @return -1 wenn das Wortende nicht gefunden wurde, sonst die Position des Wortendes
   */
  public static int getWortEnde( String pString, int pStartPosition )
  {
    /*
     * Pruefung: Sind die Parameter ungleich null?
     */
    if ( pString != null )
    {
      /*
       * Definition der Trennzeichen, welche ein Wortende definieren sollen.
       * Die Trennzeichen sind nach der Haeufigkeit des Vorkommens hinterlegt.
       */
      String wort_trennzeichen = " :.;,\"()-+=\\/[]{}=*?!#<>|&%";

      /*
       * Eine Variable fuer die aufnahme des aktuellen Zeichens in 
       * der While-Schleife.
       */
      char aktuelles_zeichen = ' ';

      /*
       * Laenge des Eingabestrings ermitteln 
       */
      int len_eingabe = pString.length();

      /*
       * Der Leseprozess wird auf die Position aus dem 
       * Parameter "pStartPositon" gestellt. 
       * 
       * Ist die Startposition kleiner als 0 wird diese auf 0 gestellt.
       * 
       * Ist die Startposition groesser als die Stringlaenge, wird die 
       * anschliessende While-Schleife nicht ausgefuehrt. Der Aufrufer
       * bekommt den Initialwert der Variablen "pos_wort_ende" von -1 
       * zurueck.
       */
      int akt_index = ( pStartPosition < 0 ? 0 : pStartPosition );

      /*
       * While-Schleife fuer das Suchen des Wortendes.
       * 
       * Die While-Schleife laeuft solange wie der Leseprozess 
       * noch nicht das Stringende erreicht hat. 
       */
      while ( akt_index < len_eingabe )
      {
        /*
         * Es wird das Zeichen and der Index-Position aus der Eingabe gelesen.
         */
        aktuelles_zeichen = pString.charAt( akt_index );

        /*
         * Die Pruefung auf ein Trennzeichen muss nur gemacht werden, 
         * wenn das aktuelle Zeichen kein Buchstabe ist.
         */
        if ( ( ( ( aktuelles_zeichen >= 'a' ) && ( aktuelles_zeichen <= 'z' ) ) || ( ( aktuelles_zeichen >= 'A' ) && ( aktuelles_zeichen <= 'Z' ) ) ) == false )
        {
          /*
           * Liefert die "indexOf"-Funktion eine Position groesser 0 zurueck, ist das
           * aktuelle Zeichen im Trennzeichenstring enthalten und das Wortende wurde
           * gefunden.   
           * 
           * Der Aufrufer bekommt den aktuellen Index zurueck.
           */
          if ( wort_trennzeichen.indexOf( aktuelles_zeichen ) >= 0 )
          {
            return akt_index;
          }
        }

        /*
         * Am Schleifenende wird der Index-Zaehler erhoeht.
         */
        akt_index++;
      }

      /*
       * Pruefung: Wortende = Stringende ?
       * 
       * Wurde in der While-Schleife das Wortende nicht gefunden und
       * der aktuelle Index ist gleich der Laenge der Eingabe, ist 
       * das Wortende gleich dem Stringende.
       * 
       * Der Zaehler "akt_index" wurde in der While-Schleife dabei 
       * einmal zuviel erhoeht, welches korrigiert wird. 
       * 
       * Der Aufrufer bekommt den aktuellen Index abzueglich der 
       * einen Position zurueck.
       * 
       * War die Startposition laenger als der String, wurde die 
       * While-Schleife nicht ausgefuehrt. Der Wert in der 
       * Variablen "akt_index" ist somit nicht gleich der Stringlaenge.
       * (Ausnahmefall: Startposition ist die Stringlaenge...)
       */
      if ( akt_index == len_eingabe )
      {
        return akt_index - 1;
      }
    }

    /*
     * Wurde das Ende nicht gefunden, bekommt der Aufrufer -1 zurueck.
     */
    return -1;
  }

}
