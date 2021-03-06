package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class _239_滑动窗口最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;
        /**
         * 有k-1个元素不能作为滑动窗口的第一个元素,那么剩下的滑动窗口的数量就是nums.length - (k - 1)
         */
        int[] maxes = new int[nums.length - (k - 1)];
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            //只要nums[队尾]<=nums[i]就删除队尾
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //将i加到队尾
            deque.offerLast(i);

            //检查窗口的索引是否合法
            int w = i - (k - 1);
            if (w < 0) continue;

            //检查对头的合法性
            if (deque.peekFirst() < w) {
                //队头不在滑动窗口内,删除队头
                deque.pollFirst();
            }

            //设置窗口的最大值
            maxes[w] = nums[deque.peekFirst()];

        }

        return maxes;
    }

    /**
     * 在leeCode提交一直显示超出时间限制....
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;
        /**
         * 有k-1个元素不能作为滑动窗口的第一个元素,那么剩下的滑动窗口的数量就是nums.length - (k - 1)
         */
        int[] maxes = new int[nums.length - (k - 1)];
        //当前滑动窗口的最大值索引
        int maxIdx = 0;
        //求出前k个元素的最大值的索引
        for (int i = 1; i < k; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        //li是滑动窗口的最左索引
        for (int li = 0; li < maxes.length; li++) {
            //ri是滑动窗口的最右索引
            int ri = li + (k - 1);
            if (maxIdx < li) {//最大值的索引不在滑动窗口范围内
                maxIdx = li;
                //那就重新计算[li,ri]范围内的最大值的索引
                for (int i = li + 1; i <= ri; i++) {
                    if (nums[i] > nums[maxIdx]) maxIdx = i;
                }
            } else if (nums[ri] >= nums[maxIdx]) {//最大值的索引不在滑动窗口范围内,并且新加入的那个元素比之前的最大值还要大
                maxIdx = ri;
            }
            maxes[li] = nums[maxIdx];
        }
        return maxes;
    }
}
