package de.ea234.day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day10SubSetGenerator
{
  private static List< List > generateSubsets( List pListItems )
  {
    int number_of_items_input = pListItems.size();

    List< List > result_list = new ArrayList<>();

    int total = 1 << number_of_items_input; // 2^n

    for ( int mask = 1; mask < total; mask++ )
    {
      List< String > new_subset = new ArrayList< String >();

      for ( int index_list_item = 0; index_list_item < number_of_items_input; index_list_item++ )
      {
        if ( ( mask & ( 1 << index_list_item ) ) != 0 )
        {
          new_subset.add( (String) pListItems.get( index_list_item ) );
        }
      }

      result_list.add( new_subset );
    }

    return result_list;
  }

  private static List< String > parseInput( String pInput )
  {
    List< String > list_items = new ArrayList<>();

    String[] parts = pInput.trim().split( "\\)\\s*\\(" );

    for ( String p : parts )
    {
      p = p.replace( "(", "" );
      p = p.replace( ")", "" );
      p = p.trim();

      if ( !p.isEmpty() ) list_items.add( "(" + p + ")" );
    }

    return list_items;
  }

  public static String getStringSubSets( String pInput )
  {
    List< String > list_items = parseInput( pInput );

    List< List > list_all_subsets = generateSubsets( list_items );

    String subset_delimiter = "  --  ";

    String result_str = "";

    for ( List< String > subset_list : list_all_subsets )
    {
      result_str += subset_list + subset_delimiter;
    }

    /*
     * Sorting for elements length
     */

    String[] array_subsets = result_str.split( subset_delimiter );

    List< String > subset_list_trimmed = new ArrayList<>();

    for ( String subset_string : array_subsets )
    {

      String trimmed = subset_string.trim();

      if ( !trimmed.isEmpty() )
      {
        subset_list_trimmed.add( trimmed );
      }
    }

    subset_list_trimmed.sort( Comparator.comparingInt( String::length ) );

    result_str = "";

    for ( String subset_string : subset_list_trimmed )
    {
      result_str += subset_string + subset_delimiter;
    }

    result_str = result_str.replaceAll( "\\[", "" ).replaceAll( "\\]", "" ).replaceAll( ",[  ]", ", " );//.replaceAll( ",", ", " ).replaceAll( ";", " ; " );

    result_str = result_str.replaceAll( subset_delimiter, " ; " );

    return result_str;
  }

  public static void main( String[] args )
  {
    String input = " (1,2) (2,3) (1,3,0) (3) (1,3) (2) (2,3) (0,2) (0,1) ";

    System.out.println( getStringSubSets( input ) );
  }

}
