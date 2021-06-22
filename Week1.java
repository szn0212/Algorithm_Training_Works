class Solution {


    /**
        加1：https://leetcode-cn.com/problems/plus-one/ 
    */
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            if(digits[i] + 1 > 9){
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if(digits[0] == 0) {
            int[] array = new int[digits.length + 1];
            array[0] = 1;
            for(int i = 0; i < digits.length; i++){
                array[i + 1] = digits[i];
            }
            return array;
        }
        return digits;
    }


    /**
         合并两个有序链表：LeetCode https://leetcode-cn.com/problems/merge-two-sorted-lists/
    */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        //遍历组成一个新链表

        ListNode protect = new ListNode(0);
        ListNode last = protect;
        while(l1 != null && l2 != null){

            if(l1.val >= l2.val){
                last.next = l2;
                l2 = l2.next;
            } else {
                last.next = l1;
                l1 = l1.next;
            }

            last = last.next;
        }
        if(l1 != null){
            last.next = l1;
        }
        if(l2 != null){
            last.next = l2;
        }

        return protect.next;
        
    }


    /**  
        和为k的子数组：https://leetcode-cn.com/problems/subarray-sum-equals-k/
    */
    public int subarraySum(int[] nums, int k) {
        if(nums == null) return 0;
        int[] sums = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++){
            sums[i + 1] = sums[i] + nums[i];
        }

        int count = 0;
        for(int i = 1; i < sums.length ; i++){
            for(int j = 0; j < i; j++){
                if(sums[j] + k == sums[i]){
                    count++;
                }
            }
            
        }

        return count;
    }

}




/** 
    设计循环双端队列：https://leetcode-cn.com/problems/design-circular-deque/
 */
class MyCircularDeque {

    
    int count;
    int num = 0;
    ListNode front;
    ListNode last;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.count = k;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(num < count){
            if(front == null){
                front = new ListNode(value);
                if(last == null){
                    last = front;
                }
            } else {
                ListNode node = new ListNode(value, null, front);
                front.setPreNode(node);
                front = node;
            }
            num++;
            return true;
        }
        return false;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(num < count){
            if(last == null){
                last = new ListNode(value);
                if(front == null){
                    front = last;
                }
            } else {
                ListNode node = new ListNode(value, last, null);
                last.setNextNode(node);
                last = node;
            }
            num++;
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(num > 0 && front != null){
            front = front.next;
            if(front != null){
                front.setPreNode(null);
            } else {
                last = null;
            }
            num--;
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(num > 0 && last != null){
            last = last.pre;
            if(last != null){
                last.setNextNode(null);
            } else {
                front = null;
            }
            num--;
            return true;
        }
        return false;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if(num > 0 && front != null){
            return front.value;
        }
        return -1;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if(num > 0 && last != null){
            return last.value;
        }
        return -1;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return num == 0 || front == null;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return num == count;
    }

    class ListNode{
        int value;
        ListNode pre;
        ListNode next;
        public ListNode(){}

        public ListNode(int value){
            this.value = value;
        }

        public ListNode(int value, ListNode pre, ListNode next){
            this.value = value;
            this.pre = pre;
            this.next = next;
        }

        public void setPreNode(ListNode pre){
            this.pre = pre;
        }

        public void setNextNode(ListNode next){
            this.next = next;
        }
    }
}
