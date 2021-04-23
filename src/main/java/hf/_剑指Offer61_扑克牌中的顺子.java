package hf;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
 */
public class _剑指Offer61_扑克牌中的顺子 {
    public boolean isStraight(int[] nums) {
        int max = 14, min = -1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num == 0) continue;
            //重复则直接返回
            if (!set.add(num)) return false;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;
    }
}
