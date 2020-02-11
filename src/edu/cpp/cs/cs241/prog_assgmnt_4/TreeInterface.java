package edu.cpp.cs.cs241.prog_assgmnt_4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * CS 241: Data Structures and Algorithms II
 *
 * Programming Assignment #N
 *
 * Interface of the tree class
 *
 */
public interface TreeInterface<K extends Comparable<K>, V>
{   
  public void add(K key, V value);
  public V remove(K key);
  public V lookup(K key);
  public String toPrettyString();
}
