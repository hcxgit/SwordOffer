package datastruct;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 三笠阿克曼
 * @date 2023/8/14
 * @description 并查集
 *
 *      1、map
 *      2、数组
 */
public class UnionFind {
    private Map<Integer,Integer> father;

    public UnionFind() {
        father = new HashMap<>();
    }

    public void add(int x){
        if(!father.containsKey(x)){
            father.put(x, null);
        }
    }

    public int find(int x){
        int root = x;
        while(father.get(root) != null){
            root = father.get(root);
        }

        // 路径压缩
        while( x!= root){
            int oldFather = father.get(x);
            father.put(x,root);
            x = oldFather;
        }
        return root;
    }

    public boolean isConnected(int x,int y) {
        return find(x) == find(y);
    }

    public void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY){
            father.put(rootY,rootX);
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind();
        uf.add(1);
        uf.add(2);
        uf.add(3);
        uf.add(4);
        uf.union(1,2);
        uf.union(2,3);
        System.out.println(uf.isConnected(1,3));
        System.out.println(uf.isConnected(1,4));

        System.out.println(uf.find(3));
        System.out.println(uf.find(4));
        uf.union(3,4);
        System.out.println(uf.find(4));
    }
}
