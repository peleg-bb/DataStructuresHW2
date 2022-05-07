

public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int firstFreeIndex = 0;

    // TODO: implement your code here
    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
    }

    @Override
    public Integer get(int index){
        return arr[index];
    }

    @Override
    public Integer search(int k) {
        if(firstFreeIndex==0){
            return -1;
        }
        int index = 0;
        while (index<firstFreeIndex){
            if(this.arr[index] != k ) {
                index++;
            }
            else {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void insert(Integer x) {
        if (!(firstFreeIndex<this.arr.length)){
            throw new IllegalArgumentException("No space in the array");
        }

        else {
            int[] pushed = {1};
            this.arr[firstFreeIndex] = x;
            this.firstFreeIndex ++;
            stack.push(pushed); //If an insertion was made - push 1 to stack to indicate insertion.
        }

    }

    @Override
    public void delete(Integer index) {
        if (this.firstFreeIndex == 0){
            throw new IllegalArgumentException("can't delete from an empty array");
        }
        else {
            int[] pushed = {0,index, this.arr[index]}; //if a deletion was made, push 0 to indicate a deletion.
            this.stack.push(pushed);
            if (index==firstFreeIndex-1){
                firstFreeIndex--;
            }
            else {
                this.arr[index] = this.arr[firstFreeIndex - 1];
                this.firstFreeIndex--;
            }
        }
    }

    @Override
    public Integer minimum() {
        if(firstFreeIndex==0){
            throw new IllegalArgumentException("can't find a minimum in an empty array");
        }
        int min = 0;
        for (int i = 0; i < firstFreeIndex; i++) {
            if (arr[i]<arr[min]){
                min = i;
            }
        }
        return min;
    }

    @Override
    public Integer maximum() {
        if(firstFreeIndex==0){
            throw new IllegalArgumentException("can't find a maximum in an empty array");
        }
        int max = 0;
        for (int i = 0; i < firstFreeIndex; i++) {
            if (arr[i]>arr[max]){
                max = i;
            }
        }
        return max;
    }

    @Override
    public Integer successor(Integer index) {
        int diff = Math.abs(this.arr[0] - this.arr[index]);
        int next = 0;

        if (maximum() == index) {
            throw new IllegalArgumentException("Max has no successor");
        } else {
            for (int i = 0; i < this.firstFreeIndex; i++) {
                if ((Math.abs(this.arr[i] - this.arr[index]) < diff) && ((this.arr[i] - this.arr[index]) > 0)) {
                    diff = Math.abs(this.arr[i] - this.arr[index]);
                    next = i;
                }
            }

            return next;
        }
    }

//        }
//        for (int i = 0; i < this.firstFreeIndex; i++) {
//            if (Math.abs(arr[i]-arr[index]) < Math.abs(arr[next]-arr[index]) && (arr[i]>arr[index])) {
//                next = i;
//            }
//        }
//        if (arr[next]-arr[index] > 0){
//            return next;


    @Override
    public Integer predecessor(Integer index) {
        int pred = 0;
        if (minimum() == index) {
            throw new IllegalArgumentException("Min has no predecessor");
        }
        for (int i = 0; i < firstFreeIndex; i++) {
            if (Math.abs(arr[i]-arr[index]) < Math.abs(arr[pred]-arr[index]) && (arr[i]<arr[index])) {
                pred = i;
            }
        }
        if (arr[index] - arr[pred] > 0){
            return pred;
        }
        return -1;
    }

    @Override
    public void backtrack() {
        if (!stack.isEmpty())
        {
            int[] popped = (int[])stack.pop();
            if (popped[0] == 1){
                firstFreeIndex--;
            }
            else {
                this.arr[firstFreeIndex] = this.arr[popped[1]];
                this.arr[popped[1]] = popped[2];
                firstFreeIndex++;
            }
        }
    }

    @Override
    public void retrack() {
        /////////////////////////////////////
        // Do not implement anything here! //
        /////////////////////////////////////
    }

    @Override
    public void print() {
        String s = "";
        for (int i = 0; i < this.firstFreeIndex; i++) {
            s += arr[i] + " ";
        }
        System.out.println(s);;
    }

}
