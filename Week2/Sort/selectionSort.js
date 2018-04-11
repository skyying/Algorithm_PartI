

function selectSort(arr){
    for(var i=0; i<arr.length; i++){
        var min = i;
        for(var j=i; j<arr.length; j++){
            if(arr[min] > arr[j]){
                min = j;
            }
        }
        
        var tmp = arr[i];
        arr[i] = arr[min];
        arr[min] = tmp;

    }
    return arr;
}

var c = [2, 2, 3, 1];



console.log(selectSort(c));