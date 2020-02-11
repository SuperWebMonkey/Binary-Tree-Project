/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs241.prog_assgmnt_4;

import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * Programming Assignment #N
 *
 * Tree class that 
 *
 * @param <K>
 */
public class Tree<K extends Comparable<K>, V> extends BinaryTree
implements TreeInterface<K,V>
{
    BinaryTree<K, V> prev, cur, root, next;
    
    /**
     * a field that is the key index of a node
     */
    private K key;
    /**
     * represents a value that is inside a specific node
     */
    private V value;
    
    /**
     * These fields represent the color of the node
     */
    private static boolean RED = true;
    private static boolean BLACK = false;

    
    /**
     * 
     *
     */
    public Tree()
    {
        //root = null;
    }
    
    /**
     * Sets the key and value inside the node
     * @param key
     *      location index inside the Binary Tree
     * @param value 
     *      value in the specfic node
     */
    //public Tree(K key, V value)
    //{
    //    super(key, value);
    //}
    
    /**
     * Determines if the node is a leaf or not a leaf
     * @param node
     * @return true if the node if a leaf
     *         false otherwise
     */
    public boolean isLeaf(Tree node)
    {
        return left == null && right == null;
    }
    
    /**
     *
     * @param key
     * @param value
     */
    public void add(K key, V value)
    {
        root = add(root, key, value);
        cur = root;
        
        //System.out.println(cur.getKey() + " " + cur.getValue() +
        //                      " " + cur.getNodeColor());
       
    }
    
    
    /**
     * Creates a new node with a special key and value
     * 
     * @param node
     *        current node in the list
     * @param key
     *       location index of a node
     * @param value 
     *        the data is inside the node
     * @return node
     *        the data that is added to the list
     */
    public BinaryTree add(BinaryTree node, K key, V value)
    {
        cur = node;
        if(isEmpty())
        {
            node = new BinaryTree(key, value);
            node.setColor(BLACK);
            cur = node;
            parent = cur;
            return node;
        }
        else
        {
            int compare = node.getKey().compareTo(key);
            //System.out.println("Compare: " + node.getKey() + " "
            //                          + key);
          
            if(compare <= 0)
            {
                parent = cur;
                cur = node;
                node.setLeft(add(node.getLeft(), key, value));
                node.getLeft().setKey(key);
                node.getLeft().setValue(value);
                node.getLeft().setColor(RED);
                return node;
            }
            else if(compare > 0)
            {
                parent = cur;
                cur = node;
                node.setRight(add(node.getRight(), key, value));
                node.getRight().setKey(key);
                node.getRight().setValue(key);
                node.getLeft().setColor(RED);
                return node;
            }
            
            
        }
        
        return cur;
        //balanceAdd(cur);
    }
    
    /**
     * Removes the node in a Binary tree
     * 
     * @param key
     *      the location index in a binary tree
     * @return value
     *      the value inside the specific binary tree
     */
    public V remove(K key)
    {
        cur = remove(cur, key);
        cur.setColor(BLACK);
        return cur.getValue();
    }
    
    /**
     * Removes the node from in a binary tree
     * 
     * @param node
     *      the current node in a binary tree
     * @param key
     *      the location index in a binary tree
     * @return node
     *      returns the node of the binary tree
     *      
     */
    public BinaryTree remove(BinaryTree node, K key)
    {
        int compare = node.getKey().compareTo(key);
        
        if(node == null)
        {
            //do nothing
        }
        else if(compare < 0)
        {
            node.setLeft(remove(node.getLeft(), key));
        }
        else if(compare > 0)
        {
            node.setRight(remove(node.getRight(), key));
        }
        else
        {
            if(node.getRight() == null)
            {
                node = node.getLeft();
            }
            else
            {
                K success = min(node.getRight());
                node.setKey(success);
                node.setRight(remove(node.getRight(), success));
            }
        }
        
        //delBalance(node);
        
        return node;
    }
    
    /**
     * Returns the successor key of the node
     * @param node
     * @return the minimum key
     */
    public K min(BinaryTree node)
    {
        if(node.getLeft() == null)
            return (K)node.getKey();
        else
            return min(node.getLeft());
    }
    
    /**
     * Searches for the value found from the key and returns it
     * @param key is the index where the value is located
     * @return the value that is inside the node
     */
   public V lookup(K key)
    {
        while(cur != null)
        {
            int compare = cur.getKey().compareTo(key);
            if(compare == 0)
                return cur.getValue();
            else if(compare < 0)
                cur = cur.getLeft();
            else if(compare > 0)
                cur = cur.getRight();
        }
        
        return null;
    }
    
    /**
     * 
     * @return 
     */
    
    public boolean isEmpty()
    {
        return cur == null;
    }
   
    /**
     * Rotates the node to the left of the the position
     * @param node 
     * 
     * @return  cur
     */
    public BinaryTree rotateLeft(BinaryTree node)
    {
        cur = node.getRight();
        node.setLeft(cur.getLeft());
        cur.setLeft(node);
        cur.setColor(node.getColor());
        node.setColor(RED);   
        return cur;
    }
    
    /**
     * Rotates the current tree to the right
     *    
     * @param node 
     *      node is now rotated to the right
     * @return cur
     *      the rotated section of the binary tree
     */
    public BinaryTree rotateRight(BinaryTree node)
    {
        cur  = node.getLeft();
        node.setLeft(cur.getRight());
        cur.setRight(node);
        cur.setColor(node.getColor());
        node.setColor(RED);
        return cur;
    }
    
    /**
     * Maintains the 5 invariants of the Red Black Tree
     * 
     * @param node
     *      an unbalanced node in a binary tree
     * @return node
     *      rebalanced node
    */
    public void balanceAdd(BinaryTree node)
    {
        if(node == root)
        {
            node.setColor(BLACK);
        }
        else
        {
            if(node.getParent().color2 == RED && node.getUncle().color2 == RED)
            {
                node = caseA1(node);
            }
            else if(node.getUncle().color2 == BLACK && isInternal(node))
            {
                node = caseA2(node);
            }
            else if(node.getUncle().color2 == BLACK && !isInternal(node))
            {
                node = caseA3(node);
            }
        }
        
        //balanceAdd(node);
    }
    
    /**
     * If case1 occurs, turn parent's color and uncle's color
     *          into black.
     * @param node
     * @return 
     */
    public BinaryTree caseA1(BinaryTree node)
    {
        node.getParent().setColor(BLACK);
        node.getUncle().setColor(BLACK);
        node.getParent().getParent().setColor(RED);
        next = node.getParent().getParent();
        return node;
    }
    
    /**
     * If case2 occurs, rotate the parent of the node
     * 
     * @param node
     *      node is the current node in a linked list
     * @return node
     *      Returns a corrected node
     */
    public BinaryTree caseA2(BinaryTree node)
    {
        
        node = rotateLeft(node.getParent());
        return node;
    }
    
    /**
     * If case 3 occurs, rotate the parent of the node
     *    and set the node to the root
     * 
     * @param node
     *      node is a node in a linked list
     * @return node
     *      returns a balanced tree
     */
    public BinaryTree caseA3(BinaryTree node)
    {
        rotateRight(node.getParent());
        node = root;
        return node;
        
    }
    
    /**
     * Maintains the properties of a Red Black node
     * 
     * @param node
     *      the current node in a series of linked list
     * @return 
     *      returns a balanced node
     */
    public BinaryTree delBalance(BinaryTree node)
    {
        sibling = node.getSibling();
        if(node == root)
        {
            
        }
        else if(sibling.getColor() == RED)
        {
            node = caseD2(node);
        }
        else if(sibling.getColor() == BLACK
                && bothBlack(node) == false)
        {
            
        }
        else if(sibling.getColor() == BLACK &&
                sibling.getLeft().getColor() == RED &&
                sibling.getRight().getColor() == BLACK)
        {
            node = caseD3(node);
        }
        else if(sibling.getColor() == BLACK && 
                sibling.getRight().getColor() == RED)
        {
            node = caseD4(node);
        }
        
        return node;
    }
    
    public BinaryTree caseD1(BinaryTree node)
    {
        node.getParent().setColor(node.getSibling().getColor());
        node = rotateLeft(node.getParent());
        return node;
    }
    
    public BinaryTree caseD2(BinaryTree node)
    {
        
        //rotateRight(node.getSibling().getRight());
        
        return node;
    }
    
    public BinaryTree caseD3(BinaryTree node)
    {
        node.getSibling().setColor(RED);
        node.getSibling().getLeft().setColor(BLACK);
        rotateRight(node.getSibling());
        return node;
    }
    
    public BinaryTree caseD4(BinaryTree node)
    {
        sibling = node.getSibling();
        sibling.setColor(node.getParent().getColor());
        node.getParent().setColor(BLACK);
        sibling.getRight().setColor(BLACK);
        rotateLeft(node.getParent());
        node = root;
        
        return node;
    }
    
    /**
     * 
     * @param node
     * @return true if both children are black
     *         false if both or either child is red
     */
    public boolean bothBlack(BinaryTree node)
    {
        return node.getLeft().getColor() == BLACK &&
               node.getRight().getColor() == BLACK;
    }
    public boolean isLeftChild(BinaryTree node)
    {
        if(node.getParent() == null)
            return false;
        return this == node.getParent().left;
        
    }
    
    public boolean isRightChild(BinaryTree node)
    {
        if(node.getParent() == null)
            return false;
        return this == node.getParent().right;
    }
    
    public boolean isExternal(BinaryTree node)
    {
        return node.getValue() == null;
    }
    
    public boolean isInternal(BinaryTree node)
    {
        return node.getValue() != null;
    }
    
    public BinaryTree parentOf(BinaryTree node)
    {
        if(node == null)
            return null;
        else
            return node.getParent();
    }
    
    /**
     * Counts the number of nodes that is inserted in the list
     * @return count
     */
    public int countNodes()
    {
        int count = 0;
        return count++;
    }
    
    /**
     * Flips the color of node: turns red to black or black to red
     * @param node 
     */
    public void flip(BinaryTree node)
    {
        node.setColor(!node.getColor());
        node.getLeft().setColor(!node.getLeft().getColor());
        node.getRight().setColor(!node.getRight().getColor());
    }
    
    /**
     * Returns the Color and string inside the node in a Binary
     * Tree format
     * 
     * @return str
     *      returns the value and color of the node in a certain format
     */
    public String toPrettyString()
    {   
        
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(cur);
        String str = "";
        
        while(!queue.isEmpty())
        {
            BinaryTree temp = queue.poll();
            str =  root.getValue() + " " + root.getNodeColor() + "\n";
            str += root.getLeft() + " ";
            str += root.getRight() + " ";
           
            if(temp.getLeft() != null)
                queue.add(temp.getLeft());
            
            if(temp.getRight() != null)
                queue.add(temp.getRight());
            
        }
        
        return str;
    }
   
}
