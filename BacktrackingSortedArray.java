import org.junit.Before;

import java.rmi.server.ExportException;

public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    public int[] arr; // This field is public for grading purposes. By coding conventions and best practice it should be private.
    private int firstFreeIndex=0;
    // TODO: implement your code here

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
    }

    @Override
    public Integer get(int index){
        return arr[index];
    }

    @Override
    public Integer search(int k) {
        int index = binarySearch(arr, k, this.firstFreeIndex);
        return index;
    }

    int binarySearch(int arr[], int x, int FFI)
    {
        int l = 0, r = FFI - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    @Override
    public void insert(Integer x) {
        if(this.firstFreeIndex == this.arr.length){
            throw new IllegalArgumentException("can't insert to a full array");
        }
        int index = 0;
        while(arr[index]<x && index<this.firstFreeIndex){
            index++;
        }
        firstFreeIndex++;
        for (int i = firstFreeIndex-1; i>index ; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = x;
        int[] pushed = {1,index};
        stack.push(pushed);
    }

    @Override
    public void delete(Integer index) {
        if(firstFreeIndex==0){
            throw new IllegalArgumentException("can't delete from an empty array");
        }
        int[] pushed = {0,arr[index]};
        stack.push(pushed);
        for (int i = index ; i <firstFreeIndex-1 ; i++) {
            this.arr[i] = this.arr[i+1];
        }
        firstFreeIndex--;
    }

    @Override
    public Integer minimum() {
        if(this.firstFreeIndex==0){
            throw new IllegalArgumentException("can't find a minimum in an empty array");
        }
        return 0; // Minimum is at index 0 because array is sorted
    }

    @Override
    public Integer maximum() {
        if(arr.length==0){
            throw new IllegalArgumentException("can't find a maximum in an empty array");
        }
        return this.firstFreeIndex-1; // Max is at last index because array is sorted
    }

    @Override
    public Integer successor(Integer index) {
        if(index>=this.firstFreeIndex-1){
            throw new IllegalArgumentException("has no successor");
        }
        return index+1; // The array is sorted and the successor would be at index+1
    }

    @Override
    public Integer predecessor(Integer index) {
        if(index==0 || index>this.firstFreeIndex){
            throw new IllegalArgumentException("has no predecessor");
        }
        return index-1; // The array is sorted and the predecessor would be at index+1
    }

    @Override
    public void backtrack() {
        if(!stack.isEmpty()) {
            int[] popped = (int[]) stack.pop();
            if (popped[0]==0){
                this.insertBT(popped[1]);
            }
            else {
                this.deleteBT(popped[1]);
            }
        }
    }

    public void insertBT(Integer x) {
        if(this.firstFreeIndex == this.arr.length){
            throw new IllegalArgumentException("can't insert to a full array");
        }
        int index = 0;
        while(arr[index]<x && index<this.firstFreeIndex){
            index++;
        }
        firstFreeIndex++;
        for (int i = firstFreeIndex-1; i>index ; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = x;
    }


    public void deleteBT(Integer index) {
        if(firstFreeIndex==0){
            throw new IllegalArgumentException("can't delete from an empty array");
        }
        for (int i = index ; i <firstFreeIndex-1 ; i++) {
            this.arr[i] = this.arr[i+1];
        }
        firstFreeIndex--;
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
