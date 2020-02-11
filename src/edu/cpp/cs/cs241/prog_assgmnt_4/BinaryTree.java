package edu.cpp.cs.cs241.prog_assgmnt_4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * CS 241: Data Structures and Algorithms II
 *
 * Programming Assignment #N4
 *
 * Binary Tree representation of the node that sets the standard values 
 * key, value, and the color of the node, as well as give access to the
 * values.
 *
 */

//

public class BinaryTree<K extends Comparable<K>, V>
{
    protected BinaryTree left, right, parent, uncle, sibling;
    private K key;
    private V value;
    protected String nodeCol;
    private static boolean RED = true;
    private static boolean BLACK = false;
    protected boolean color2;
    
    /**
     * Sets left, right, key, and value to null if no
     * data is entered
     */
    public BinaryTree()
    {
        key = null;
        value = null;
    }
    
    /**
     * Constructor that sets the key and value of the node
     * Sets RED to true
     * @param key
     *     key is the location index of the node
     * @param value 
     *     the data contained in the node
     */
    public BinaryTree(K key, V value)
    {
        setKey(key);
        setValue(value);
        color2 = RED;
        setColor(RED);
        //left.setColor(BLACK);
        //right.setColor(BLACK);
    }
    
    /**
     * Sets left to the node
     * @param node 
     *      Current location of the node in the list
     */
    public void setLeft(BinaryTree node)
    {
        left = node;
    }
    
    /**
     * Sets the right child of the node
     * @param node 
     *      current location of the node in the list
     */
    public void setRight(BinaryTree node)
    {
        right = node;
    }
    
    /**
     * Sets the parent of the list
     * @param node 
     *      current location of the node in the list
     */
    public void setParent(BinaryTree node)
    {
        parent = node;
    }
    
    /**
     * Sets the uncle of the current node
     * @param node 
     *      a node in the linked list
     */
    public void setUncle(BinaryTree node)
    {
        BinaryTree grandParent = node.getParent().getParent();
        
        if(grandParent == null)
        {
            uncle = null;
        }
        else if(node.getParent() == grandParent.getLeft())
        {
            uncle = grandParent.getRight();
        }
        else
        {
            uncle = grandParent.getLeft();
        }
    }
    
    /**
     * Sets current nodes sibling 
     * @param node 
     *      a node in a list 
     */
    public void setSibling(BinaryTree node)
    {
        if(node == null || node.getParent() == null)
            sibling = null;
        else if(node == node.getParent().getLeft())
        {
            sibling = node.getParent().getRight();
        }
        else
        {
            sibling = node.getParent().getLeft();
        }
    }
    
    /**
     * Returns the left child of the node
     * @return left
     *      the left child of the current node
     */
    public BinaryTree getLeft()
    {
        return left;
    }
    
    /**
     * Returns the right child of the node
     * @return right
     *      the right child of the list
     */
    public BinaryTree getRight()
    {
        return right;
    }
    
    /**
     * Returns the parent of the node
     * @return parent
     *      the parent of the current node
     */
    public BinaryTree getParent()
    {
        return parent;
    }
    
    /**
     * Returns the node of the current node
     * @return uncle
     *      the uncle of the current node
     */
    public BinaryTree getUncle()
    {
        return uncle;
    }
    
    /**
     * Returns the sibling of the current node
     * @return sibling
     *      
     */
    public BinaryTree getSibling()
    {
        return sibling;
    }
    
    /**
     * Sets the key of the node
     * @param key
     *      key is the location index where the value is located
     */
    public void setKey(K key)
    {
        this.key = key;
    }
    
    /**
     * Sets the value of the node
     * 
     * @param value 
     *      value that is inside the node
     */
    public void setValue(V value)
    {
        this.value = value;
    }
    
    /**
     * Sets the color of the node to red or black 
     * and sets the strings to the corresponding colors.
     * 
     * @param col 
     *      col represents the node as either Red or Black
     * ```` Red for true and Black for false
     */
    public void setColor(boolean col)
    {
        color2 = col;
        if(col == RED)
            nodeCol = "RED";
        else if(col == BLACK)
            nodeCol = "BLACK";    
    }
    
    /**
     * Returns the value of the node
     * 
     * @return value
     *      value is the value in the node
     */
    public V getValue()
    {
        return value;
    }
    
    /**
     * Returns the key of the node
     * @return key
     *      location index of the node
     */
    public K getKey()
    {
        return key;
    }
    
    /**
     * Returns the color of the node
     * @param none
     * @return  nodeCol
     *  node color is the string representation 
     *  of the node.
     */
    public String getNodeColor()
    {
        return nodeCol;
    }
    
    /**
     * Returns the boolean color of the node
     * 
     * @param none
     * 
     * @return color2
     *  color2 is the boolean representation of a red or black 
     *  node
     */
    public boolean getColor()
    {
        return color2;
    }
    
    /**
     * Compares the keys and returns a 0 or 1
     * 
     * @param key2
     *      key is index where the value is located
     * @return 
     *      returns either a 1 or 0
     */
    public int compareTo(K key2)
    {
        System.out.print(key.compareTo(key));
        if(key.compareTo(key2) > 0)
        {
            return 1;
        }
        else if(key.compareTo(key2) < 0)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    } 
   
}
