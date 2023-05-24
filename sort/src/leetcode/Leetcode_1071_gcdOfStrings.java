package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/25
 * @description Leetcode 1071: 字符串的最大公因子
 */
public class Leetcode_1071_gcdOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        //根据长度求出最大公约数，然后再截取前i个字符串,分别校验

        int common = gcd(str1.length(),str2.length());

        String s = str1.substring(0,common);
        // boolean res = check1(str1,str2,s);
        boolean res = check(str1,s) && check(str2,s);
        return res? s : "";
    }
    /**
     * 校验：1、按照字符遍历，分别检验
     */
    public boolean check1(String str1,String str2,String s){
        int l=0,r=0;
        int len1 = str1.length(),len2=str2.length(),common=s.length();
        while(l < Math.max(len1,len2)){
            r = l%(common);
            if(l < len1){
                if(str1.charAt(l) != s.charAt(r)){
                    return false;
                }
            }

            if(l < len2){
                if(str2.charAt(l) != s.charAt(r)){
                    return false;
                }
            }
            l++;
        }
        return true;
    }


    /**
     * 校验：2、按照字符串，拼n个common字符串检验
     */
    public boolean check(String str,String s){
        int len = str.length(),common = s.length();
        int strLen = len/common;
        StringBuffer sb = new StringBuffer();

        for(int i=1;i<=strLen;i++){
            sb.append(s);
        }

        return sb.toString().equals(str);
    }
    /**
     * 最大公约数：1、公质因数法
     */
    public int gcd1(int num1,int num2){
        int res = 1;
        int index = 2;
        while(index <= num1 && index <= num2){
            if(num1%index == 0 && num2%index == 0){
                num1 = num1/index;
                num2 = num2/index;
                res *=index;
            }
            else{
                index++;
            }
        }
        return res;
    }
    /**
     * 最大公约数：2、辗转相除法
     */
    public int gcd(int num1,int num2){
        int remainder = num1 % num2;
        while(remainder !=0){
            num1 = num2;
            num2 = remainder;
            remainder = num1 % num2;
        }
        return num2;
    }
}
