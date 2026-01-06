package com.example.one;
//
//public class FirstPro {
//    public static  void rotate(int[] nums,int k){
//        int n=nums.length;
//        reverse(nums,0,n-1);
//        reverse(nums,0,k-1);
//        reverse(nums,k,n-1);
//    }
//    private static void reverse(int[] nums,int start, int end){
//        while (start< end){
//            int temp=nums[start];
//            nums[start]=nums[end];
//            nums[end]=temp;
//            start++;
//            end--;
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] arr={1,2,3,4,5};
//        int k=3;
//        rotate(arr,k);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]+ " ");
//
//        }
//    }
//}


//public class FirstPro {
//    public static void main(String[] args) {
//        String str="MADAM";
//        String org_str=str;
//        String rev="";
//        int len=str.length();
//        for (int i=len-1; i >=0; i--) {
//            rev=rev+str.charAt(i);
//
//
//        }
//        if(org_str.equals(rev)){
//            System.out.println(org_str+"is palindrome string");
//        }
//        else{
//            System.out.println(org_str+"is not palindrome string");
//        }
//    }
//}


import java.util.*;
//
//public class FirstPro {
//    public static void main(String[] args) {
//        String str="Hello world";
//        vowelCount(str);
//    }
//    static void vowelCount(String str){
//        int vowel=0,consonent=0;
//        str=str.toLowerCase();
//        for (char c:str.toCharArray()){
//            if(c>='a' && c<='z'){
//                if(c=='a'|| c=='e'|| c=='i'|| c=='o'|| c=='u'){
//                    vowel++;
//                }
//                else {
//                    consonent++;
//                }
//            }
//
//        }
//        System.out.println("vowels"+ " "+ vowel);
//        System.out.println("consonents"+ " " + consonent);
//
//    }
//}

//public class FirstPro {
//    public static void main(String[] args) {
//        String s="java version 8";
//        s=s.toUpperCase();
//        char[] n=s.toCharArray();
//        HashSet<Character>unique=new HashSet<>();
//        for(char t:n){
//            unique.add(t);
//        }
//        System.out.println(unique);
//    }
//}

public class FirstPro {
    static boolean areAnagram(String s1,String s2){
        if(s1.length() !=s2.length()){
            return false;
        }
        HashMap<Character,Integer> charCount=new HashMap<>();

        for (char ch:s1.toCharArray()){
            charCount.put(ch, charCount.getOrDefault(ch,0)+1);
        }
        for (char ch:s2.toCharArray()){
            charCount.put(ch, charCount.getOrDefault(ch,0)-1);
        }
        for(var pair:charCount.entrySet()){
            if(pair.getValue()!=0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1="listen";
        String s2="silent";
        System.out.println(areAnagram(s1,s2)? "true": "false");
    }
}