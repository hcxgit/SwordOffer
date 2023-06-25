package leetcode;

import java.util.*;

/**
 * @author 三笠阿克曼
 * @date 2023/6/21
 * @description Leetcode 207: 课程表
 *
 *  1、拓扑排序
 */
public class Leetcode_207_canFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0){
            return true;
        }
        /**
         1、拓扑排序
         */

        // nextCourses<i,list>: 课程i的后置课程list(邻接表)、 indegree<i,degree>: 课程i的入度
        Map<Integer,List<Integer>> nextCourses = new HashMap<>();
        Map<Integer,Integer> indegree = new HashMap<>();

        //1、初始化indegree,每个节点入度为0
        for(int i=0;i<numCourses;i++){
            indegree.put(i,0);
        }

        //1、初始化list、更新indegree
        for(int i=0;i < prerequisites.length; i++){
            int next = prerequisites[i][0];
            int course = prerequisites[i][1];

            // 初始化list邻接表
            if(!nextCourses.containsKey(course)){
                nextCourses.put(course,new ArrayList());
            }
            nextCourses.get(course).add(next);

            // 更新入度
            indegree.put(next,indegree.get(next) + 1);
        }

        return sort(nextCourses,indegree);
    }
    /**
     拓扑排序
     */
    public boolean sort(Map<Integer,List<Integer>> nextCourses, Map<Integer,Integer> indegree){
        Deque<Integer> dq = new LinkedList<>();

        // 1、将入度为0的节点，加入队列
        for(int c: indegree.keySet()){
            if(indegree.get(c) == 0){
                dq.add(c);
            }
        }

        // 2、只要存在【入度为0】的节点，就【加入队列】，并【删除图中该点和边】
        while(!dq.isEmpty()){
            int course = dq.remove();

            // 到尾节点，跳过
            if(!nextCourses.containsKey(course)){
                continue;
            }

            // 删除节点和边(后置节点入度-1 )
            List<Integer> nextCs = nextCourses.get(course);
            for(int nc:nextCs){
                indegree.put(nc,indegree.get(nc)-1);
                // 度减到0时，加入队列
                if(indegree.get(nc) == 0){
                    dq.add(nc);
                }
            }
        }

        // 3、遍历indegree,如果还【存在入度不为0】的节点，则说明有环
        for(int c: indegree.keySet()){
            if(indegree.get(c) != 0){
                return false;
            }
        }
        return true;
    }
}
