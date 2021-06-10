1.

/**
TC: O(n) && SC: O(1)
We start with two pointer technique. We start with left pointer at 0 a and right pointer at the end. 
As we go through the array we update the leftmax and rightmax. 
At any given point, if left is lesser than right then leftmax is lesser than rightmax and vice versa for when right is lesser than left. 
The height of the building - leftmax/rightmax gives us the amount of water that can be trapped on the building.
*/

class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        int n = height.length;
        int left=0;
        int right = n-1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;
        while(left <= right){
            if(height[left] <= height[right]){
                leftMax = Math.max(leftMax,height[left]);
                totalWater += leftMax - height[left++];
            }
            else{
                rightMax = Math.max(rightMax,height[right]);
                totalWater += rightMax - height[right--];
            }
        }
        return totalWater;
    }
    
}

2.
/**
TC: O(n) SC: O(n)
In this case we calculate the leftmax for each index and store in an array and we do the same way we store rightmax for each index in another array. 
Therefore the water that can be trapped at any index is equal to min(leftmax[i[,rightmax[i]) - height[i]
*/

class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        int n = height.length;
        int[] leftMax = getMax(height,true);
        int[] rightMax = getMax(height,false);
        int totalWater = 0;
        for(int i=0; i<n; i++){
            int amountPossible = Math.min(leftMax[i],rightMax[i]);
            int amount = amountPossible - height[i];
            totalWater += amount;
        }
        return totalWater;
    }
    
    public int[] getMax(int[] h,boolean left){
        int n = h.length;
        int[] max = new int[n];
        if(left){
            for(int i=0; i<n; i++){
                max[i]=(i > 0) ? Math.max(max[i-1],h[i]) : h[i];
            }
        }
        else{
            for(int i=n-1; i>=0; i--){
                max[i] = (i < (n-1)) ? Math.max(max[i+1],h[i]) : h[i];
            }
        }
        
        return max;
    }
}
