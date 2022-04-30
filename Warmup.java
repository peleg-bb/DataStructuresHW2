

public class Warmup {
    public static int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack) {
        int i=0;
        while (i < arr.length){
            for (int j = i; j < i+forward; j++) {
                myStack.push(arr[j]);
                if(arr[j]==x){
                    return j;
                }
                i = j;
            }
            for (int k = 0; k < back; k++) {
                myStack.pop();
                i--;
            }
        }
        return -1;
    }

    public static int consistentBinSearch(int[] arr, int x,Stack myStack) {
        int inconsistencies = Consistency.isConsistent(arr);
        int l = 0;
        int r = arr.length-1;
        while (r>=l){
            int mid = (l+r)/2;
            myStack.push(mid);
            if(arr[mid]==x){
                return mid;
            }
            if(arr[mid]<x){
                l = mid+1;
                if(inconsistencies>0){
                    r = 2*(int)myStack.pop()-l;
                    inconsistencies--;
                }
            }
            if(arr[mid]>x){
                r=mid-1;
                if(inconsistencies>0){
                    l = 2*(int)myStack.pop()-r;
                    inconsistencies--;
                }
            }
        }


        // Your implementation should contain this line:

        return -1; // temporal return command to prevent compilation error
    }
    
}
