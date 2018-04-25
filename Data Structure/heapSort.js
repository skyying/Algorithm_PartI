

function  maxHeapify(arr, i, n){

    let parentIndex = i;
    let leftIndex = (2*i)+ 1;
    let rightIndex = (2*i) + 2;
    let largestIndex = parentIndex;
    if( leftIndex < n && arr[leftIndex] > arr[parentIndex]){
        largestIndex = leftIndex;
    }

    if( rightIndex < n && arr[rightIndex] > arr[largestIndex]){
      // because parent should greater than child; so since largest might be leftIndex might be parent;
      // if right greater than largest, then we need to reset largestIndex 
      
      largestIndex = rightIndex;
    
    }

    if(largestIndex !== i){
      // means largestIndex has been changed 
      // swap largest index and i; 
      var tmp = arr[i];
      arr[i] = arr[largestIndex];
      arr[largestIndex] = tmp; 
      maxHeapify(arr, largestIndex, n);
    } 
      
}


// just build a maxHeapfiy

function buildMaxHeap(arr){
    
    for(var i = arr.length-1; i>=0 ; i--){
      
        maxHeapify(arr, i, arr.length - 1);
    
    }
}

function heapSort(arr){
  
    buildMaxHeap(arr);
    let n = arr.length - 1;
  for(var i = arr.length - 1; i>0; i--){
        
      
      var tmp = arr[i];
      arr[i] = arr[0];
      arr[0] = tmp;
      n--;
      maxHeapify(arr, 0, n);
  }
}

var arr01  = [5, 2, 1, 8, 0, 7, 9, 4] 

heapSort(arr01);
