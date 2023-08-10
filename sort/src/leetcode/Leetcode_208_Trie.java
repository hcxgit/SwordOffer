package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/8/10
 * @description Leetcode 208：实现Trie(前缀树)
 *  1、字典树
 *       - 26叉树，用数组实现
 *       - 每个节点存字符，字符串就是从root开始遍历
 *       - 节点中有属性isEnd：表示是否是字符串尾字符
 *          - 例如有abc和ab字符串，则到b和c节点的isEnd都是true
 */
public class Leetcode_208_Trie {


    private Leetcode_208_Trie[] next;
    private boolean isEnd;

    public Leetcode_208_Trie() {
        next = new Leetcode_208_Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Leetcode_208_Trie node = this;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            // 节点不存在，直接插入
            if(node.next[c-'a'] == null){
                node.next[c-'a'] = new Leetcode_208_Trie();
            }

            node = node.next[c-'a'];
        }
        // 更新isEnd
        node.isEnd = true;
    }

    public boolean search(String word) {
        Leetcode_208_Trie node =  searchWith(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Leetcode_208_Trie node =  searchWith(prefix);
        return node != null;
    }

    // 返回搜索的字符串的最后一个节点
    public Leetcode_208_Trie searchWith(String s){
        Leetcode_208_Trie node = this;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(node.next[c-'a'] == null){
                return null;
            }
            node = node.next[c-'a'];
        }
        return node;
    }
}
