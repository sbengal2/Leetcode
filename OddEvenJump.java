/**
TC:O(n log n) SC: O(n)
This is a DP problem. We can consider a boolean array for odd step from each index and boolean array for even step from index. Since we know that the final index can be reached from that index itself, the both odd and even jump can be taken from this index and that would be a good jump. Therefore we can fill the last index as true in both the odd and even boolean arrays. We use a treemap to get the minimum of all the values greater than the value at a index and get the ,aximum of all the values lesser than the value at index. We can map the values to their indices so that whenever we find the min-greater or max-lesser value we can retrieve their index. Now for each index we try to find if we take an odd jump or even step from this index would it end up reaching the last index. if we take odd jump from this index, the next jump we take needs to be even. if we take odd jump from this index, the next jump would be even and would be to a value min-greater than this value. So we check the even boolean array index of this min-greater value, if that is true, then this the odd jump from this index would be good.We do the same for even jumps by checking the index of max-lesser value for each value in the odd boolean array. Now since from any index the step we take is 1 which is an odd number, the odd boolean array will say if the jump from that index is good or not. Therefore the answer would be the number of true values in odd boolean array.
*/

class Solution {
    public int oddEvenJumps(int[] arr) {
        if(arr == null || arr.length == 0)
            return 0;
        int n = arr.length;
        int numberOfGoodJumps = 1;
        
        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];
        even[n-1] = odd[n-1] = true;
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(arr[n-1],n-1);
        
        for(int i=n-2; i>=0; i--){
            Map.Entry minOfAllGreaterElements= treeMap.ceilingEntry(arr[i]);
            Map.Entry maxOfAllLowerElements = treeMap.floorEntry(arr[i]);
            if(minOfAllGreaterElements != null){
                odd[i] = even[(int)minOfAllGreaterElements.getValue()];
            }
            if(maxOfAllLowerElements != null){
                even[i] = odd[(int)maxOfAllLowerElements.getValue()];
            }
            
            if(odd[i]){
               numberOfGoodJumps++; 
            }
            treeMap.put(arr[i],i);
        }
        
        return numberOfGoodJumps;
    }
}
