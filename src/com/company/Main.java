package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {



    public static void  HuffmanResult(Node root,String s,HashMap<String,String> result){
        if(root.right==null && root.left==null && !root.value.isEmpty()){
            //System.out.println(root.value+" -> "+s);
            result.put(root.value, s);

            return;
        }
        HuffmanResult(root.left,s+"0",result);
        HuffmanResult(root.right,s+"1",result);

    }


    public static void Compression() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        int n = s.length();
        HashMap<String, Integer> freq = new HashMap<>();
        HashMap<String,String> result = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>(n, new HuffmanComparator());

        // frequency of characters
        for (int i = 0; i < n; i++) {
            if (freq.containsKey(String.valueOf(s.charAt(i)))) {
                Integer curr = freq.get(String.valueOf(s.charAt(i)));
                freq.put(String.valueOf(s.charAt(i)), curr + 1);
            } else {
                freq.put(String.valueOf(s.charAt(i)), 1);
            }
        }
        // put nodes in priority queue
        for (Map.Entry<String, Integer> x : freq.entrySet()) {
           String key=x.getKey();
           Integer value=x.getValue();
           //System.out.println(key+"   "+value);
           Node node =new Node(value,key,null,null);
           pq.add(node);

        }
        Node root =new Node(0,"",null,null);
        while (pq.size()>1){
            Node node1 = pq.peek();
            pq.poll();

            Node node2 = pq.peek();
            pq.poll();

            Node newNode =new Node(node1.freq+node2.freq,"",node1,node2);
            root=newNode;
            pq.add(newNode);
        }
        int sizeAfter=0;
        int sizeBefore=n*8;
        System.out.println("Huffman Code:");
        if(freq.size()==1){
            System.out.println(s.charAt(0)+" : 0");
            sizeAfter=n;

        }else {
            HuffmanResult(root, "", result);
            for (Map.Entry<String, String> x : result.entrySet()) {
                System.out.println(x.getKey() + " : " + x.getValue());
                sizeAfter+=x.getValue().length()*freq.get(x.getKey());
            }
        }
        String decodedString="";
        for (int i=0;i<n;i++){
            String key=String.valueOf(s.charAt(i));
            decodedString+=result.get(key);
        }
        System.out.println();
        System.out.println("Decoded Data: "+decodedString);
        System.out.println("Data Size Before Compression: "+sizeBefore+ " bits");
        System.out.println("Data Size After Compression: "+sizeAfter+ " bits");


    }
    public static void Decompression(){
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        int nn=s.length();
        HashMap<String,String>decode = new HashMap<>();
        int n=input.nextInt();
        for (int i = 0; i < n; i++) {
            String code=input.next();
            String key=input.next();
            decode.put(key,code);
        }
        String temp="";
        String decoded="";
        for (int i = 0; i < nn; i++) {
            temp+=s.charAt(i);
            if(decode.containsKey(temp)){
                decoded+=decode.get(temp);
                temp="";
            }
        }
        System.out.println("Decoded Text: "+decoded);

    }

    public static void main(String[] args) {

        Compression();
        //Decompression();


    }
}

/*
abaacaadaa

a   1
b   010
c   00
d   011

15

80

101011001101111






*/
