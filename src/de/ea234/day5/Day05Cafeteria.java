package de.ea234.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day05Cafeteria
{
  private static final int        KNZ_PRODUCT_FRESH       = 1;

  private static final int        KNZ_PRODUCT_SPOILED     = 2;

  private static final int        KNZ_RANGE_STATUS_MERGED = 1;

  private static final int        KNZ_RANGE_STATUS_NEW    = 3;

  private static final BigDecimal BIG_DECIMAL_0           = new BigDecimal( "0" );

  public static void main( String[] args )
  {
    String test_content = "3-5,10-14,16-20,12-18,,1,5,8,11,17,32";

    //test_content = "1-15,1-14,1-20,10-88,,2,2,2,3,4,32,44,44,44,1111,1111,2222";

    //test_content = "1-15,13-14,20-30,40-80,12-18,,2,2,2,3,4,32,44,44,44,1111,1111,2222";

    //test_content = "10-20,200-210,12-18,14-16,30-40,35-50,25-30,60-60,70-79,4-5,6-8,80-82,,2,2,15,35";

    //test_content = "10-20,200-210,12-18,14-16,30-40,35-50,25-30,60-60,61-69,70-71,4-5,6-8,80-82,,2,2,15,35";

    //test_content = "10-19,20-29,30-39,40-49,1-3,4-5,6-9,80-89,,2,2,15,35";

    List< String > test_content_list = Arrays.stream( test_content.split( "," ) ).collect( Collectors.toList() );

    calcIDs( test_content_list, true );

    calcIDs( getListProd(), false );
  }

  private static void calcIDs( List< String > pList, boolean pKnzDebug )
  {
    /*
     * Range List
     */
    wl( "" );
    wl( "Reading Product-Ranges" );
    wl( "" );

    List< Day5Range > range_list = new ArrayList< Day5Range >();

    int input_list_size = pList.size();

    int input_list_index = 0;

    boolean knz_flag_range_building = true;

    while ( ( input_list_index < input_list_size ) && ( knz_flag_range_building ) )
    {
      String str_current_input_line = pList.get( input_list_index );

      knz_flag_range_building = !str_current_input_line.isBlank();

      if ( knz_flag_range_building )
      {
        Day5Range new_range = new Day5Range( str_current_input_line );

        range_list.add( new_range );

        wl( new_range.toString() );
      }

      input_list_index++;
    }

    range_list.sort( ( range1, range2 ) -> range1.getMinValue().compareTo( range2.getMinValue() ) );

    range_list = reduceRanges( range_list, pKnzDebug );

    wl( "" );
    wl( "Check Product-IDs (Part 1)" );
    wl( "" );

    int count_product_ids_sum = 0;
    int count_product_ids_fresh = 0;
    int count_product_ids_spoiled = 0;

    while ( input_list_index < input_list_size )
    {
      count_product_ids_sum++;

      String str_current_input_line = pList.get( input_list_index );

      if ( pKnzDebug )
      {
        wl( "Input-Line " + input_list_index + " - Nr " + count_product_ids_sum + " Product ID " + str_current_input_line );
      }

      BigDecimal product_id_big_decimal = new BigDecimal( str_current_input_line );

      int knz_flag_product_status = KNZ_PRODUCT_SPOILED;

      int range_index = 0;

      while ( ( range_index < range_list.size() ) && ( knz_flag_product_status == KNZ_PRODUCT_SPOILED ) )
      {
        if ( range_list.get( range_index ).isValueInRange( product_id_big_decimal ) )
        {
          knz_flag_product_status = KNZ_PRODUCT_FRESH;
        }
        else
        {
          range_index++;
        }
      }

      if ( knz_flag_product_status == KNZ_PRODUCT_FRESH )
      {
        count_product_ids_fresh++;
      }
      else
      {
        count_product_ids_spoiled++;
      }

      input_list_index++;
    }

    wl( "" );
    wl( "Calc valid Product-IDs" );
    wl( "" );

    BigDecimal count_product_ids_valid = BIG_DECIMAL_0;

    for ( Day5Range range_current : range_list )
    {
      wl( "Range " + range_current.toString() );

      count_product_ids_valid = count_product_ids_valid.add( range_current.getRangeValues() );
    }

    wl( "" );
    wl( "count_product_ids_sum     " + count_product_ids_sum );
    wl( "count_product_ids_fresh   " + count_product_ids_fresh );
    wl( "count_product_ids_spoiled " + count_product_ids_spoiled );
    wl( "" );
    wl( "count_product_ids_valid   " + count_product_ids_valid.toPlainString() );
  }

  private static List< Day5Range > reduceRanges( List< Day5Range > pList, boolean pKnzDebug )
  {
    if ( pKnzDebug )
    {
      wl( "" );
      wl( "Reducing Number of Ranges" );
      wl( "" );
    }

    List< Day5Range > range_list_old = pList;

    List< Day5Range > range_list_new = new ArrayList< Day5Range >();

    int loop_count = 0;

    boolean knz_do_loop = true;

    while ( knz_do_loop )
    {
      loop_count++;

      if ( pKnzDebug )
      {
        wl( "Reducing " + loop_count );
      }

      for ( Day5Range range_from_old_list : range_list_old )
      {
        int range_status = KNZ_RANGE_STATUS_NEW;

        int range_index_while = 0;

        while ( ( range_index_while < range_list_new.size() ) && ( range_status == KNZ_RANGE_STATUS_NEW ) )
        {
          Day5Range range_from_new_list = range_list_new.get( range_index_while );

          if ( range_from_new_list.isValueNeighbour( range_from_old_list.getMinValue() ) )
          //if ( range_from_new_list.isValueInRange( range_from_old_list.getMinValue() ) )
          {
            range_status = KNZ_RANGE_STATUS_MERGED;

            range_from_new_list.mergeRange( range_from_old_list.getMinValue(), range_from_old_list.getMaxValue() );
          }
          else
          {
            range_index_while++;
          }
        }

        if ( range_status == KNZ_RANGE_STATUS_NEW )
        {
          range_list_new.add( range_from_old_list );
        }
      }

      range_list_new.sort( ( range1, range2 ) -> range1.getMinValue().compareTo( range2.getMinValue() ) );

      if ( range_list_new.size() != range_list_old.size() )
      {
        range_list_old = range_list_new;

        range_list_new = new ArrayList< Day5Range >();
      }
      else
      {
        knz_do_loop = false;
      }
    }

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "Range-List after reduction" );
      wl( "" );

      for ( int range_index_for = 0; range_index_for < range_list_new.size(); range_index_for++ )
      {
        wl( range_list_new.get( range_index_for ).toString() );
      }
    }

    return range_list_new;
  }

  private static List< String > getListProd()
  {
    int row_count = 0;

    List< String > string_array = new ArrayList< String >();

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2025__day5_input.txt";

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
   * wl = write log 
   * 
   * @param pString der auszugebende String
   */
  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
