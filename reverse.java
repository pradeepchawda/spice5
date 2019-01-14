
class HelloCodiva {
  
  public static void print(int [] array){
        for (int i=0; i<array.length; i++){
	      System.out.print(array[i] + " ");
    }
  }
  
  public static void reverse1(int [] array){
    int start = 0;
    int end = array.length-1;
    
   while( start < end) {
      int tmp = array[start];
      array[start] = array[end];
      array[end] = tmp;
     start++;
     end--;
     
   }
    
    print (array);
  }
  
  
  public static void reverse2(int [] array){
    
    for (int i=0; i<array.length/2; i++){
      int tmp = array[i];
      array[i] = array[array.length-1-i];
      array[array.length-1-i] = tmp;
    }
    
	print(array);
  }

  
  
  
  public static void main(String[] args) {
    
    reverse1(new int [] { 1, 2, 3, 4});
        

  }
  
}
