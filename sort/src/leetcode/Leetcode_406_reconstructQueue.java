package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2023/8/5
 * @description Leetcode 406: 根据身高重建队列
 *
 *          1、先排身高高的（h降序，k升序）
 *          - 身高高的先确定，后面低的不管怎么插入，都【不会影响前面身高高的相对排名】
 *          - 按照k插入就行
 */
public class Leetcode_406_reconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        // 先按照h降序，k升序
        Arrays.sort(people,(a, b) -> a[0] == b[0]?a[1] - b[1]:b[0] - a[0]);

        // 插入
        List<int[]> list = new LinkedList<>();
        for(int[] p:people){
            list.add(p[1],p);
        }

        return list.toArray(new int[people.length][]);
    }
}
