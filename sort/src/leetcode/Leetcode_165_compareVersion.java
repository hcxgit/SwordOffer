package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/15
 * @description Leetcode 165: 版本比较
 */
public class Leetcode_165_compareVersion {
    public int compareVersion(String version1, String version2) {
        /**
         转成数组，对应比较
         */

        String[] v1s = version1.split("\\.");
        String[] v2s = version2.split("\\.");

        int len1 = v1s.length,len2 = v2s.length;
        int num1,num2,index = 0;
        while(index < len1 || index < len2){
            if(index < len1){
                num1 = Integer.parseInt(v1s[index]);
            }else{
                num1 = 0;
            }

            if(index < len2){
                num2 = Integer.parseInt(v2s[index]);
            }else{
                num2 = 0;
            }

            if(num1 > num2){
                return 1;
            }else if(num1 < num2){
                return -1;
            }
            index ++;
        }
        return 0;
    }
}
