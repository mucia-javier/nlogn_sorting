/** Author: Javier Mucia
 *  Class: CS-323
 *  Prof. Dr. Yuan
 */
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Sorting {
	public static int count;
	public static void main(String[] args) {
						// Name of the files to be processed
		String [] files = { "Num8.txt",     "Num16.txt", "Num32.txt",    "Num64.txt",    "Num128.txt",
							"Num256.txt",   "Num512.txt",   "Num1024.txt",  "Num2048.txt",  "Num4096.txt",
							"Num8192.txt",  "Num16384.txt" };

						// the column headers of the summary file/table | summary string will be saved onto a *.csv file
		String summary =    "Algorithm, Num8.txt, Num16.txt, Num32.txt, Num64.txt, Num128.txt, Num256.txt, " +
							"Num512.txt, Num1024.txt, Num2048.txt, Num4096.txt, Num8192.txt, Num16384.txt";

	/*<<<<<<<<<<<<<<<<<<<<<<<<<  APPLY INSERTION SORT    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		summary +="\nInsertion";        //new entry for Insertion_Sort on the summary file/table

		// get the data from the text files and apply insertion sort one file at a time
		for (String file_name: files) {
			count = 0;                                      // Restart count for this algorithm on the current file
			int number_of_elements = Integer.parseInt( file_name.substring(3, file_name.length() - 4) );    // # of values on this file
			int[] data = new int[number_of_elements];       // create array to hold exactly the number of values in current file
			FromFile_ToArray("./data/"+file_name, data);    // Fill up the array with values from the file

			Insertion_Sort(data);   // Apply the Insertion Sort to the data of current file
			summary +=","+count;    // save the value of 'count' for insertion sort on this file

			// Save the sorted array into a new file (one value per line), include size of array and value of 'count'
			FromArray_ToFile(data, "ArrayLength: "+data.length+"\nCount: "+count, "InsertionSRT_"+file_name);
			}



	/*<<<<<<<<<<<<<<<<<<<<<<<<<  APPLY MERGE SORT   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		//new entry for Merge_Sort on the summary file/table
		summary +="\nMerge";

		// get the data from the text files and apply Merge sort one file at a time
		for (String file_name: files) {
			count = 0;                  // Restart count for this algorithm on the current file
			int number_of_elements = Integer.parseInt( file_name.substring(3, file_name.length() - 4) ); // # of values on this file
			int[] data = new int[number_of_elements];    // create array to hold exactly the number of values in current file
			FromFile_ToArray("./data/"+file_name, data); // Fill up the array with values from the file

			Merge_Sort(data, 0, data.length-1); // Apply the Merge Sort to the data of current file
			summary +=","+count;                // save the value of 'count' for insertion sort on this file

			// Save the sorted array into a new file (one value per line), include size of array and value of 'count'
			FromArray_ToFile(data, "ArrayLength: "+data.length+"\nCount: "+count, "MergeSRT_"+file_name);
		}


	/*<<<<<<<<<<<<<<<<<<<<<<<<   APPLY HEAP SORT     >>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		//new entry for Heap sort on the summary file/table
		summary +="\nHeap";

		// get the data from the text files and apply HEAP sort one file at a time
		for (String file_name: files) {
			count = 0;      // Restart count for this algorithm on the current file
			int number_of_elements = Integer.parseInt( file_name.substring(3, file_name.length() - 4) ); // # of values on this file
			int[] data = new int[number_of_elements];   // create array to hold exactly the number of values in current file
			FromFile_ToArray("./data/"+file_name, data); // Fill up the array with values from the file

			Heap_Sort( data );      // Apply the HEAP Sort to the data of current file
			summary +=","+count;    // save the value of 'count' for insertion sort on this file

			// Save the sorted array into a new file (one value per line), include size of array and value of 'count'
			FromArray_ToFile(data, "ArrayLength: "+data.length+"\nCount: "+count, "HeapSRT_"+file_name);
		}

	/*<<<<<<<<<<<<<<<<<<<<<<<<    APPLY QUICK SORT     >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		//new entry for QUICK sort on the summary file/table
		summary +="\nQuick";

		// get the data from the text files and apply HEAP sort one file at a time
		for (String file_name: files) {
			count = 0;          // Restart count for this algorithm on the current file
			int number_of_elements = Integer.parseInt( file_name.substring(3, file_name.length() - 4) ); // # of values on this file
			int[] data = new int[number_of_elements];   // create array to hold exactly the number of values in current file
			FromFile_ToArray("./data/"+file_name, data); // Fill up the array with values from the file

			Quick_Sort( data );     // Apply the QUICK Sort to the data of current file
			summary +=","+count;    // save the value of 'count' for insertion sort on this file

			// Save the sorted array into a new file (one value per line), include size of array and value of 'count'
			FromArray_ToFile(data, "ArrayLength: "+data.length+"\nCount: "+count, "QuickSRT_"+file_name);
		}

	/*<<<<<<<<<<<<<<<<<     SAVE THE SUMMARY OF THE RESULTS IN A CSV FILE TO DO A GRAPH     >>>>>>>>>>>>>>>>>>>>>>>*/
		try(  PrintWriter out = new PrintWriter( "SUMMARY.csv" )  ){  out.println(summary); }
		catch ( IOException e){ System.out.println("Something wrong happened while saving the summary."); }
	}






	/************************    DEFINITION OF INSERTION SORT    ***********************/
	public static void Insertion_Sort(int [] anArray){
		for (int j = 1; j < anArray.length; ++j){
			int key = anArray[j];
			int i = j - 1;
			while( i >= 0 && anArray[i] > key ){
				count = count +1;
				anArray[i+1] = anArray[i];
				i = i - 1;
				}
			anArray[i+1] = key;
			}
		}








	/************************    DEFINITION OF MERGE SORT    *************************/
	public static void Merge_Sort(int [] anArray, int p, int r){
		if( p<r ){
			int q = ( p+r )/2;
			Merge_Sort(anArray, p, q);
			Merge_Sort(anArray, q+1, r);
			Merge(anArray, p, q, r);
			}
		}
	public static void Merge(int [] anArray, int p, int q, int r){
		int n1 = ( q-p )+1;                 // size of Left-subarray
		int n2 = ( r-q );                   // size of right-subarray
		int [] leftArray = new int[ n1+1 ];
		int [] rightArray= new int[ n2+1 ];

		for(int i=0; i<n1; ++i)             // fill leftArray with first half of the given array
			leftArray[i] = anArray[ p+i ];

		for(int j=0; j<n2; ++j)             // fill rightArray with second half of the given array
			rightArray[j] = anArray[ q+j+1 ];

		leftArray[ n1 ] = 999999999;
		rightArray[n2 ] = 999999999;

		int i = 0;  // index of 1st element in leftArray
		int j = 0;  // index of 1st element in rightArray
		for(int k = p; k<=r; ++k){
			count = count + 1;
			if( leftArray[i]<=rightArray[j] ){
				anArray[k] = leftArray[i];
				i = i+1;
				}
			else{
				anArray[k] = rightArray[j];
				j = j+1;
				}
			}
		}












	/************************    DEFINITION OF HEAP SORT    ***************************/
	public static void Heap_Sort(int [] anArray){
		Build_Max_Heap(anArray);        // Transform array into a Heap

		for(int i = anArray.length-1; i>=0; --i){
			exchange(anArray, 0, i);    // Exchange anArray[0] <-> anArray[i]  (first and last elements)
			Max_Heapify(anArray, 0, i); // Decrease the size of the heap array
			}
		}
	public static void Build_Max_Heap(int [] anArray){
		int n = anArray.length;
		for(int i = (anArray.length/2)-1; i>=0; --i)
			Max_Heapify(anArray, i, n);
		}
	// i is the index of the root node
	// n is the size of the given heap
	public static void Max_Heapify(int [] anArray, int i, int n){
		int largest = i;  // Assume the largest is the root node
		int left  = 2*i+1; // nice formula to find the left child of 'i'
		int right = 2*i+2; // formula to find the right child of 'i'

		// left child may be larger than root
		if( left<n  && anArray[left] > anArray[largest] )
			largest = left;

		// right child may be larger than root
		if( right<n && anArray[right] > anArray[largest] )
			largest = right;

		// the largest is not root
		if(largest != i){
			exchange(anArray, i, largest);
			count = count + 1;
			Max_Heapify(anArray, largest, n);
			}
		}
	// Exchange A[i] <-> A[j]
	public static void exchange(int A[], int i, int j){
		int temporary = A[i];
		A[i] = A[j];
		A[j] = temporary;
	}







	/************************    DEFINITION OF QUICK SORT    **************************/
	public static void Quick_Sort(int [] anArray){
		QUICKSORT(anArray, 0, anArray.length-1 );
		}
	// p: starting index, r: ending index in the array
	public static void QUICKSORT(int [] anArray, int p, int r){
		if(p<r){
			int q = PARTITION(anArray, p, r);   // q: is partitioning index
			QUICKSORT(anArray, p, q-1);         // all elements before partitioning index
			QUICKSORT(anArray, q+1, r);         // all elements after partitioning  index
			}
		}
	public static int PARTITION(int [] anArray, int p, int r){
		int x = anArray[r];     // PIVOT rightmost element on the (sub)array
		int i = p - 1;
		for( int j = p; j<r; ++j ){
			count = count + 1;
			if( anArray[j] <= x) {          // current element <= to the pivot
				i = i + 1;                  // increment index of smallest element
				exchange(anArray, i, j);
				}
			}
		exchange(anArray, i+1, r);
		return i+1;
		}



	/************************    AUXILIARY FUNCTIONS   **************************/
	// opens a file with the name 'Filename' containing a number per line and fills upn an array with those vales
	public static void FromFile_ToArray(String Filename, int[] anArray){
		try {
			FileReader fileReader = new FileReader(new File(Filename));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			for(int i=0; i<anArray.length; ++i){
				String aLine = bufferedReader.readLine();
				anArray[i] = Integer.parseInt(aLine);
				}
			fileReader.close();
		} catch (IOException e) { e.printStackTrace(); }

		}
	// saves the values of an array into a new file (one value per line). the given string is at the head of the file
	public static void FromArray_ToFile( int[] anArray, String initialString,  String Filename ){
		try{
			PrintWriter writer = new PrintWriter(Filename);
			writer.println(initialString);
			for(int i = 0; i<anArray.length; ++i)
				writer.println(anArray[i]);
			writer.close();
		} catch (IOException e) { System.out.print("Something happened while saving to a file."); }
		}

	// Function that checks that all the elements in an array are sorted in ascending order
	public static boolean isSorted(int[] A) {
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] > A[i + 1]) {
				return false; // It is proven that the array is not sorted.
			}
		}
		return true; // If this part has been reached, the array must be sorted.
		}


}