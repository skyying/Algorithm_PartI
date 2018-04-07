
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
        if( n > len){
            console.log("index out of boundary");
            return "Index out of boundary";
        }else if(n === 0){  // first position
            this.addToFirst(val);
        }else if(n === len){ // last position
            this.addToLast(val);
        }else{
            while(current){
                if(count === (n - 1) ){
                   tmp = current.next; 
                   current.next = newNode;
                   newNode.next = tmp;
                }
                current = current.next;
                count++;
            }
            return;
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
            }else if(current.val === val || (prev < val && val < current.val)){
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
   
        var current = this.head.next;
        var newList = new LinkedList();
        newList.head.val = this.head.val;

        while(current){
            newList.sortedInsert(current.val);
            current = current.next;
        } 
        this.head = newList.head;
        this.last = newList.last;

    };
   
    this.removeDuplicates = function(){
        var current = this.head;
        if(!current) return;

        while(current){
            if(current.next && current.val === current.next.val ){
                var nextNext = current.next.next;
                current.next = nextNext;
            }else{
                current = current.next;
            }
        }
        return this.head;
    };
   

}


//insert sorted
//Given a list, change it to be in sorted order (using SortedInsert())



var a  = new LinkedList();
a.head.val = 0;

a.addToFirst(0);
a.addToFirst(0);
a.addToFirst(0);
a.insertSorted();
a.removeDuplicates();
console.log("a.head", a.head);
//console.log(a.removeDuplicates())