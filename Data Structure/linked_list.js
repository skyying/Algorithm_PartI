
function LinkedList(){
  var Node = function(val){
      this.val = val;
      this.next = null;
  };
  this.head = new Node(null);
  this.last = this.head;

  this.addToFirst = function(val){
    
        var newNode = new Node(val);
        newNode.next = this.head;
        this.head = newNode;
    
  };
  this.addToLast = function(val){
        var newNode = new Node(val);
        this.last.next = newNode;
        this.last = newNode;
  };
  this.count = function(n){
      var count = 0;
      var headCopy = this.head;
      while(headCopy){
          if(headCopy.data === n){
              count++;
          }
          headCopy = headCopy.next;
         
      }
      return count;
  };
  this.pop = function(){
        var firstNodeData = this.head.data;
        this.head = this.head.next;
        return firstNodeData;
  };
  this.length = function(){
      var conut = 0;
      var current = this.head;
      while(current){
        conut++;
        current = current.next;
      }
      return conut;
  };
  this.deleteList = function(){
      this.head = null;
  };

  this.addToNth = function(n, val){
        //todo writing this addToNth function    
        var len = this.length();
        var current  = this.head;
        var newNode = new Node(val); 
        var count = 0;
        
        if( n > this.length() + 1){
            return "Index out of boundary";
        }else if(n === 0){  // first position
            this.addToFirst(val);
        }else if(n === len + 1){ // last position
            this.addToLast(val);
        }else{
            
            while(current){
                if(count === (n - 1) ){
                   tmp = current.next; 
                   current.next = newNode;
                   newNode.next = tmp;
                   break; 
                }
                current = current.next;
                count++;
            }
        }
       
        return  this.head;
    };

    this.sortedInsert = function(val){
     // insert a value in sorted way;   
        var newNode = new Node(val);
        var len = this.length();
        var current = this.head;
        var count = 0;
        var prev;
        // find a  position less than val and greater than val;
        // find a position equal to val; 
        while(current){

            if(this.head.val > val){
                this.addToFirst(val);
                break;
            }else if(this.last.val < val){
                this.addToLast(val);
                break;
            }else if(current.data === val || (prev < val && val < current.val)){
                this.addToNth(count, val);
                break;
            }
            count ++;
            prev = current.val;
            current = current.next;
        }

        return this.head;

        
    };
   
    this.insertSorted = function(){
       // 把目前的list sorted過一次 
       
        var current = this.head.next;
        var aaa = new LinkedList();
        aaa.head.val = this.head.val;
     
        while(current){
            aaa.sortedInsert(current.val);
            current = current.next;
        } 
        this.head = aaa.head;
        this.last = aaa.last;
        return aaa.head;
    };
   

}


//insert sorted
//Given a list, change it to be in sorted order (using SortedInsert())



var a  = new LinkedList();
a.head.val = 5;
a.addToFirst(0);
a.addToFirst(3);

console.log("before, a.head: ", a.head);
console.log("before last: ", a.last);
console.log(a.insertSorted());
console.log("after first", a.head);
console.log("after last", a.last);