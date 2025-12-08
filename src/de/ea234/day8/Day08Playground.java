package de.ea234.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day08Playground
{

  /*
   *
   * --- Day 8: Playground ---
   * https://adventofcode.com/2025/day/8
   * 
   * 
   * 
   * 
   * 
  162,817,812 and 
  425,690,689
  
  ID   18 -   862 -    61 -    35 - 747990
  ID    6 -   466 -   668 -   158 - 688344
  ID   10 -    52 -   470 -   668 - 669828
  ID    2 -    57 -   618 -    57 - 388422
  ID    7 -   542 -    29 -   236 - 350301
  ID    5 -   352 -   342 -   300 - 330868
  ID   13 -   117 -   168 -   530 - 322813
  ID   17 -   941 -   993 -   340 - 1987130
  ID    8 -   431 -   825 -   988 - 1842530
  ID   12 -   819 -   987 -    18 - 1645254
  ID    4 -   592 -   479 -   940 - 1463505
  
  ID    1 -   162 -   817 -   812 - 1353077
  
  ID   16 -   970 -   615 -    88 - 1326869
  ID    3 -   906 -   360 -   560 - 1264036
  ID   15 -   346 -   949 -   466 - 1237473
  ID    9 -   739 -   650 -   466 - 1185777
  ID   14 -   805 -    96 -   715 - 1168466
  
  ID   20 -   425 -   690 -   689 - 1131446
  
  ID   19 -   984 -    92 -   344 - 1095056
  ID   11 -   216 -   146 -   977 - 1022501
   * 
   * 
   * 
  ID   10 -    52 -   470 -   668 - 205029
  ID    2 -    57 -   618 -    57 - 368961
  ID   13 -   117 -   168 -   530 - 222422
  
  ID    1 -   162 -   817 -   812 - 311570
  ID   20 -   425 -   690 -   689 - 86561
  
  ID   11 -   216 -   146 -   977 - 417460
  ID   15 -   346 -   949 -   466 - 232850
  ID    5 -   352 -   342 -   300 - 66821
  ID    8 -   431 -   825 -   988 - 371883
  ID    6 -   466 -   668 -   158 - 141525
  ID    7 -   542 -    29 -   236 - 272986
  ID    4 -   592 -   479 -   940 - 222910
  ID    9 -   739 -   650 -   466 - 103114
  ID   14 -   805 -    96 -   715 - 316817
  ID   12 -   819 -   987 -    18 - 597723
  ID   18 -   862 -    61 -    35 - 536557
  ID    3 -   906 -   360 -   560 - 214109
  ID   17 -   941 -   993 -   340 - 507771
  ID   16 -   970 -   615 -    88 - 429314
  ID   19 -   984 -    92 -   344 - 440305
   * 
   * 
   * 
   */

  public static void main( String[] args )
  {
    String test_content = ";;162,817,812;57,618,57;906,360,560;592,479,940;352,342,300;466,668,158;542,29,236;431,825,988;739,650,466;52,470,668;216,146,977;819,987,18;117,168,530;805,96,715;346,949,466;970,615,88;941,993,340;862,61,35;984,92,344;425,690,689";

    List< String > test_content_list = Arrays.stream( test_content.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calcNewGrid( test_content_list, true );

    // calcNewGrid( getListProd(), false );
  }

  private static void calcNewGrid( List< String > pListSplitPattern, boolean pKnzDebug )
  {
    if ( pKnzDebug )
    {
      for ( String input_str : pListSplitPattern )
      {
        wl( input_str );

      }
    }

    Day08ConnectionBox conbox_zero = new Day08ConnectionBox( 0, "0,0,0" );

    wl( "" );
    wl( "Creating Connection-Box list " );
    wl( "" );

    List< Day08ConnectionBox > l_cb = new ArrayList< Day08ConnectionBox >();

    long connection_box_id = 1;

    for ( String input_str : pListSplitPattern )
    {
      if ( input_str.length() > 6 )
      {
        Day08ConnectionBox new_con_box = new Day08ConnectionBox( connection_box_id, input_str );

        new_con_box.calcDistanceToZero( conbox_zero );

        l_cb.add( new_con_box );

        connection_box_id++;
      }
    }

    l_cb.sort( ( con_box_1, con_box_2 ) -> ( con_box_1.getPositionX() < con_box_2.getPositionX() ? -1 : 0 ) );

    wl( "Connection-Box count " + l_cb.size() );
    wl( "" );

    if ( pKnzDebug )
    {

      for ( Day08ConnectionBox new_con_box : l_cb )
      {
        wl( new_con_box.toStringF() );
      }
    }

    int index_aussen = 0;

    int anzahl_cb = l_cb.size();

    while ( index_aussen < anzahl_cb - 1 )
    {
      Day08ConnectionBox conbox_outer = l_cb.get( index_aussen );

      int index_innnen = index_aussen + 1;

      while ( index_innnen < anzahl_cb )
      {
        Day08ConnectionBox conbox_inner = l_cb.get( index_innnen );

        if ( conbox_outer.calcDistanceNearest( conbox_inner ) )
        {
          if ( pKnzDebug )
          {
            wl( "Found Improvement " + conbox_outer.getInfoNeighbour() );
          }
        }

        index_innnen++;
      }

      index_aussen++;
    }

    for ( Day08ConnectionBox new_con_box : l_cb )
    {
      if ( new_con_box.getNeighbour() == null )
      {
        wl( "kein nachbar fuer " + new_con_box.getBoxId() );
      }
      else
      {
        wl( new_con_box.toStringNeighbour() );
      }
    }

//    for ( Day08ConnectionBox new_con_box : l_cb )
//    {
//      wl( new_con_box.toStringF() );
//    }
//
//    /*
//     * Gruppen bilden
//     * 
//     * Max Ausdehnungen berechnen
//     * max x
//     * max y
//     * max z
//     */
//    long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE, midX = Long.MIN_VALUE;
//    long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE, midY = Long.MIN_VALUE;
//    long minZ = Long.MAX_VALUE, maxZ = Long.MIN_VALUE, midZ = Long.MIN_VALUE;
//
//    for ( Day08ConnectionBox new_con_box : l_cb )
//    {
//      if ( new_con_box.getPositionX() < minX ) minX = new_con_box.getPositionX();
//      if ( new_con_box.getPositionX() > maxX ) maxX = new_con_box.getPositionX();
//      if ( new_con_box.getPositionY() < minY ) minY = new_con_box.getPositionY();
//      if ( new_con_box.getPositionY() > maxY ) maxY = new_con_box.getPositionY();
//      if ( new_con_box.getPositionZ() < minZ ) minZ = new_con_box.getPositionZ();
//      if ( new_con_box.getPositionZ() > maxZ ) maxZ = new_con_box.getPositionZ();
//    }
//
//    wl( "" );
//    wl( "Min X: " + minX + " Max X: " + maxX );
//    wl( "Min Y: " + minY + " Max X: " + maxY );
//    wl( "Min Z: " + minZ + " Max X: " + maxZ );
//
//    midX = (long) ( ( maxX - minX ) / 2 );
//    midY = (long) ( ( maxY - minY ) / 2 );
//    midZ = (long) ( ( maxZ - minZ ) / 2 );
//
//    Day08ConnectionBox conbox_media = new Day08ConnectionBox( 0, midX + "," + midY + "," + midZ );
//
//    if ( pKnzDebug )
//    {
//      for ( Day08ConnectionBox new_con_box : l_cb )
//      {
//        wl( new_con_box.toStringF() );
//      }
//    }
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day8_input.txt";

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
   * Ausgabe auf System.out
   * 
   * @param pString der auszugebende String
   */
  private static void wl( String pString )
  {
    System.out.println( pString );
  }

  /*
  AI Prompt:
  
  Programmiersprache ist Java. Bitte erstelle alle beispiele in Java.
  gegeben ist eine menge von koordinaten x y z.
  aus dieser menge soll ich nun diejenigen herausfinden, welche dicht bei einander liegen.
  Es muss also ein kürzester wege algorithmus in 3 D gefunden werden.
  nun habe ich keine Ahnung wie ich dass in 3 D mache.
  
  
  Hier ist die beispielmenge:
  
  
  162,817,812
  57,618,57
  906,360,560
  592,479,940
  352,342,300
  466,668,158
  542,29,236
  431,825,988<dependency>
    <groupId>net.datafaker</groupId>
    <artifactId>datafaker</artifactId>
    <version>2.0.2</version>
  </dependency>    
  
  739,650,466
  52,470,668
  216,146,977
  819,987,18
  117,168,530
  805,96,715
  346,949,466
  970,615,88
  941,993,340
  862,61,35
  984,92,344
  425,690,689
  
  Hier ist der Beispieltext aus der Aufgabe (in Englisch):gleiches Problem:
  gegeben ist eine menge von koordinaten x y z.
  aus dieser menge soll ich nun diejenigen herausfinden, welche dicht bei einander liegen.
  Es muss also ein kürzester wege algorithmus in 3 D gefunden werden.
  nun habe ich keine Ahnung wie ich dass in 3 D mache.
  
  
  Hier ist die beispielmenge:
  
  
  162,817,812
  57,618,57
  906,360,560
  592,479,940
  352,342,300
  466,668,158
  542,29,236
  431,825,988
  739,650,466
  52,470,668
  216,146,977
  819,987,18
  117,168,530
  805,96,715
  346,949,466
  970,615,88
  941,993,340
  862,61,35
  984,92,344
  425,690,689
  
  Hier ist der Beispieltext aus der Aufgabe (in Englisch):
  This list describes the position of 20 junction boxes, one per line. Each position is given as X,Y,Z coordinates. So, the first junction box in the list is at X=162, Y=817, Z=812.
  
  To save on string lights, the Elves would like to focus on connecting pairs of junction boxes that are as close together as possible according to straight-line distance. In this example, the two junction boxes which are closest together are 162,817,812 and 425,690,689.
  
  Erstmal möchte ich die max und min werte der koordinaten berechnen.
  eine Schleife über die Eingabe und dann jeweils den minimalen und maximalen wert bestimmen.
  so dass ich danach irgendwie gruppieren kann.
  This list describes the position of 20 junction boxes, one per line. Each position is given as X,Y,Z coordinates. So, the first junction box in the list is at X=162, Y=817, Z=812.
  
  To save on string lights, the Elves would like to focus on connecting pairs of junction boxes that are as close together as possible according to straight-line distance. In this example, the two junction boxes which are closest together are 162,817,812 and 425,690,689.
  
  Wie berechne ich den kürzesten weg in einem 3 D universum?
  Erstelle mir ein Beispiel in Java.
  
  
  
  -----------------------------------------
  
  
  
  Kurz gesagt: Für Paare von Punkten in 3D suchst du das Paar mit dem geringsten euklidischen Abstand.
  
  Schritte (einfach umsetzbar):
  
    Gegebene Punkte als Tupel speichern: P_i = (x_i, y_i, z_i).
    
    Abstand zweier Punkte berechnen: dist(P_i, P_j) = sqrt((x_i - x_j)^2 + (y_i - y_j)^2 + (z_i - z_j)^2).
    
    Alle Paare durchgehen und den kleinsten Abstand finden:
        Für i von 1 bis n-1
            Für j von i+1 bis n
                berechne dist(P_i, P_j)
                falls kleiner als bisheriger Minimum, aktualisiere Min-Paar und Min-Abstand.
  
  Wichtige Hinweise:
  
    Komplexität: O(n^2) Abstände. Bei 20 Punkten ist das trivial (190 Paare).
    Du kannst vermeiden, sqrt zu berechnen, wenn du nur vergleichen willst: vergleiche dist^2, also: d2 = (dx)^2 + (dy)^2 + (dz)^2. Nur am Ende, falls nötig, nimm sqrt(d2).
  
  Beispiel mit deinen Punkten (Python-ähnlich, kompakt):
  
  punkte = [ (162,817,812), (57,618,57), (906,360,560), (592,479,940), (352,342,300), (466,668,158), (542,29,236), (431,825,988), (739,650,466), (52,470,668), (216,146,977), (819,987,18), (117,168,530), (805,96,715), (346,949,466), (970,615,88), (941,993,340), (862,61,35), (984,92,344), (425,690,689), ]
  
  min_pair = None min_d2 = float('inf') for i in range(len(punkte)): for j in range(i+1, len(punkte)): dx = punkte[i][0] - punkte[j][0] dy = punkte[i][1] - punkte[j][1] dz = punkte[i][2] - punkte[j][2] d2 = dxdx + dydy + dz*dz if d2 < min_d2: min_d2 = d2 min_pair = (punkte[i], punkte[j])
  
  print("Nächste Nachbarn:", min_pair, "mit Abstand sqrt(", min_d2, ")")
  
  Hinweis zur Frage: Du fragtest nach „kürzesten Weg in einem 3D-Universum“. Wenn du wirklich eine Verbindungskette (z.B. ein minimales Spannen oder einen Pfad durch alle Punkte) suchst (wie TSP in 3D), ist das ein anderes Problem. Für das einfache „welches Paar liegt am dichtesten“ reicht oben der Ansatz. Wenn du wirklich einen Weg suchen willst, der alle Punkte verbindet (z. B. Minimaler Spannbaum), sag kurz Bescheid – ich erkläre dir auch das, z. B. mit Kruskal/Prim im 3D-Raum.
  
  
  
  
  
  
  
  Danke für die klare Beschreibung. Kurz gesagt: Du suchst die paarweise nächsten Punkte in 3D nach dem euklidischen Abstand.
  Ein “kürzester Weg”-Algorithmus im 3D-Uspace ist hier nicht nötig; 
  es geht um das Finden des kleinsten Abstands zwischen zwei Punkten aus einer Menge.
  
  Was du brauchst:
  
    Abstand zweier Punkte P1(x1,y1,z1) und P2(x2,y2,z2) ist: d = sqrt((x1-x2)^2 + (y1-y2)^2 + (z1-z2)^2)
  
    Dann prüfst du alle Paare und nimmst das Paar mit dem kleinsten Abstand.
  
  Beispiel-Algorithmus in Java (einfach, O(n^2), geeignet für kleinere Mengen):
  
    Punkt-Klasse
    Hauptlogik zum Durchlaufen aller Paare und Verfolgen des kleinsten Abstands
  
  Java-Beispiel:
  
  public class ClosestPair3D { static class Point { long x, y, z; Point(long x, long y<dependency>
    <groupId>net.datafaker</groupId>
    <artifactId>datafaker</artifactId>
    <version>2.0.2</version>
  </dependency>    
  , long z) { this.x = x; this.y = y; this.z = z; } }
  
  public static double distance(Point a, Point b) {
    long dx = a.x - b.x;
    long dy = a.y - b.y;
    long dz = a.z - b.z;
    return Math.sqrt(dx*dx + dy*dy + dz*dz);
  }
  
  public static void main(String[] args) {
    Point[] points = new Point[] {
        new Point(162,817,812),
        new Point(57,618,57),
        new Point(906,360,560),
        new Point(592,479,940),
        new Point(352,342,300),
        new Point(466,668,158),
        new Point(542,29,236),
        new Point(431,825,988),
        new Point(739,650,466),
        new Point(52,470,668),
        new Point(216,146,977),
        new Point(819,987,18),
        new Point(117,168,530),
        new Point(805,96,715),
        new Point(346,949,466),
        new Point(970,615,88),
        new Point(941,993,340),
        new Point(862,61,35),
        new Point(984,92,344),
        new Point(425,690,689)
    };
  
    double minDist = Double.POSITIVE_INFINITY;
    Point p1 = null, p2 = null;
  
    for (int i = 0; i < points.length; i++) {
        for (int j = i + 1; j < points.length; j++) {
            double d = distance(points[i], points[j]);
            if (d < minDist) {
                minDist = d;
                p1 = points[i];
                p2 = points[j];
            }
        }
    }
  
    if (p1 != null && p2 != null) {
        System.out.printf("Nächstes Paar: (%d,%d,%d) und (%d,%d,%d)%n", 
            p1.x, p1.y, p1.z, p2.x, p2.y, p2.z);
        System.out.printf("Abstand: %.3f%n", minDist);
    } else {
        System.out.println("Wenige Punkte vorhanden.");
    }
  }
  
  }
  
  Hinweise:
  
    Für größere Mengen (>10k Punkte) lohnt sich ein effizienterer Ansatz (z. B. kd-tree, raumaufteilung (divide-and-conquer) in 3D oder Gitternachbarschaften) um die Komplexität zu reduzieren.
    
    Falls du nur die Distanz zum nächsten Nachbarn eines bestimmten Punktes suchst, kannst du innerhalb einer Schleife nur Paare zugunsten dieses Punktes prüfen.
  
  Wenn du magst, passe ich das Beispiel an deine konkrete Eingabe (Datei lesen, Input-Format, Fehlerbehandlung) an.
  
  
  
   */
}
