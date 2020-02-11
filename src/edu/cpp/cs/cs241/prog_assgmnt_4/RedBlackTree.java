/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs241.prog_assgmnt_4;

/**
 * CS 241: Data Structures and Algorithms II
 *
 * Programming Assignment #N4
 *
 * Main program that sends in information to the tree class
 *
 */
public class RedBlackTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Tree node = new Tree();
        String str = "";
        
        //Test for the add functoin
        for(int i = 0; i < 10; i++)
        {
            node.add(i, i + 2);
            str = node.toPrettyString();
        }
        
        System.out.print(str);
        
        //Test for the look up function
       
    }
    
}
