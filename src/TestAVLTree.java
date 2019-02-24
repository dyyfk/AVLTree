/**
 * Filename:   TestAVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, Ruoyu He, Shirui Chen
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    TODO: replace with your lecture number
 * 
 * Due Date:   as specified in Canvas
 * Version:    1.0
 * 
 * Credits:    n/a
 * 
 * Bugs:       no known bugs, but not complete either
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.junit.Test;

/**
 * A JUnit class that tests the functionality of AVLtree and implements its own class that implements the
 * same checkForBalancedTree and checkForBinarySearchTree as AVLTree to test those two methods.
 * 
 * @author Ruoyu He, Shirui Chen
 *
 */
public class TestAVLTree {



    /**
     * Tests that an AVLTree is empty upon initialization.
     * @result fail if the tree is not empty when constructed.
     */
    @Test
    public void test01isEmpty() {
        AVLTree<Integer> tree = new AVLTree<Integer>();

        assertTrue(tree.isEmpty());
    }

    /**
     * Tests that an AVLTree is not empty after adding a node.
     * @result fail if avltree is empty after adding a node
     */
    @Test
    public void test02isNotEmpty() {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        try {
            tree.insert(1);
            assertFalse(tree.isEmpty());
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests functionality of a single delete following several inserts.
     * @result fail if a random number from 0 to maxn is not found 
     *         after deleted from the tree
     */

    @Test
    public void test03insertManyDeleteOne() {
        AVLTree<Integer> tree = new AVLTree<>();
        try {
            final int maxn = 1000000;
            ArrayList<Integer> in = new ArrayList<>();
            for(int i = 0; i < maxn; i++) {
                in.add(i);
            }
            Collections.shuffle(in);
            for(int insertItem : in) {
                tree.insert(insertItem);
            }
            Random r = new Random();
            int tmp = r.nextInt(maxn);
            tree.delete(tmp);
            assertFalse(tree.search(tmp));
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Tests functionality of many deletes following several inserts.
     * @result fail if any unexpected exception is thrown when inserting 0 to 
     *         maxn in a random manner and deleted in order afterwards. Also 
     *         fail if deleted item is found in the tree.
     */
    @Test
    public void test04insertManyDeleteMany() {
        AVLTree<Integer> tree = new AVLTree<>();
        try {
            final int maxn = 1000000;
            ArrayList<Integer> in = new ArrayList<>();
            for(int i = 0; i < maxn; i++) {
                in.add(i);
            }
            Collections.shuffle(in);
            for(int insertItem : in) {
                tree.insert(insertItem);
            }
            for(int i = maxn-1; i >= 0; i--) {
                tree.delete(i);
                assertFalse(tree.search(i));
            }
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Test if insert throws expected exception
     * @result fail if DuplicateKeyException is not thrown when two same numbers
     *         are inserted 
     * @throws DuplicateKeyException
     */
    @Test(expected = DuplicateKeyException.class)
    public void test05duplicateKeyException() throws DuplicateKeyException{
        AVLTree<Integer> tree = new AVLTree<>();
        Random r = new Random();
        int tmp = r.nextInt();//the integer to be inserted.
        tree.insert(tmp);
        tree.insert(tmp);
    }
    
    /**
     * Test the functionality of search
     * @result false it throws an unexpected exception or fails to return true when a key is in the 
     * AVL tree, true if returns correctly when the key is in AVLTree 
     */
    @Test
    public void test06search(){
        AVLTree<Integer> tree = new AVLTree<>();
        try {
            final int maxn = 1000000;
            ArrayList<Integer> in = new ArrayList<>();
            for(int i = 0; i < maxn; i++) {
                in.add(i);
            }
            Collections.shuffle(in);
            for(int insertItem : in) {
                tree.insert(insertItem);
            }
            Random r = new Random();
            int tmp = r.nextInt(maxn);
            assertTrue(tree.search(tmp));
            assertFalse(tree.search(maxn*2));
        }catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Test the functionality of print, print should return a string of in-order traversal of AVLTree
     * @result false it throws an unexpected exception, true if returns an in-order traversal of AVLtree.
     */
    @Test
    public void test07print() {
        AVLTree<Integer> tree = new AVLTree<>();
        try {
            final int maxn = 10000;
            ArrayList<Integer> in = new ArrayList<>();
            for(int i = 0; i < maxn; i++) {
                in.add(i);
            }
            Collections.shuffle(in);
            for(int insertItem : in) {
                tree.insert(insertItem);
            }
            String s = "";
            for(int i = 0; i < maxn-1; i++) {
                s = s + i + " ";
            }
            s = s + (maxn-1);
            assertEquals(tree.print().trim(),s);
        }catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Tests the checkForBalancedTree method using AVLtree and an unbalanced binary search tree
     * implementations.
     * @result returns true if the implementation of a class is a balancedTree.
     * @throws IllegalArgumentException
     * @throws DuplicateKeyException
     */
    @Test
    public void test08checkForBalancedTree() throws IllegalArgumentException, DuplicateKeyException{
        AVLTree<Integer> tree = new AVLTree<>();
        unbalancedBST<Integer> unbalancedTree = new unbalancedBST<>();
            final int maxn = 500;
            ArrayList<Integer> in = new ArrayList<>();
            for(int i = 0; i < maxn; i++) {
                in.add(i);
            }
            Collections.shuffle(in);
            for(int insertItem : in) {
                tree.insert(insertItem);
                unbalancedTree.insert(insertItem);
            }
            assertTrue(tree.checkForBalancedTree());
            assertFalse(unbalancedTree.checkForBalancedTree());
        }
    
    /**
     * Tests the checkForBinarySearchTree method using AVLtree and an unbalanced binary search tree
     * implementations.
     * @result returns true if the implementation of a class is a binarySearchTree.
     * @throws IllegalArgumentException
     * @throws DuplicateKeyException
     */
    @Test
    public void test09checkForBinarySeachTree() throws IllegalArgumentException, DuplicateKeyException{
        AVLTree<Integer> tree = new AVLTree<>();
        unbalancedBST<Integer> unbalancedTree = new unbalancedBST<>();
            final int maxn = 500;
            ArrayList<Integer> in = new ArrayList<>();
            for(int i = 0; i < maxn; i++) {
                in.add(i);
            }
            Collections.shuffle(in);
            for(int insertItem : in) {
                tree.insert(insertItem);
                unbalancedTree.insert(insertItem);
            }
            assertTrue(tree.checkForBinarySearchTree());
            assertFalse(unbalancedTree.checkForBinarySearchTree());
        }

}
    

/**
 * An unbalanced BST that implemented the same checkForBalancedTree and checkForBinarySearchTree
 * with AVLTree class. It inserts and deletes the nodes without balancing and should return false 
 * on both methods.
 * 
 * @author Ruoyu He
 *  
 * @param <K>
 */
class unbalancedBST <K extends Comparable<K>> implements AVLTreeADT<K> {
    class BSTNode<K> {
        /* fields */
        private K key;  // the key stored in the node
        private int height; // the height of the node
        private BSTNode<K> left, right; // left child and right child of the node
        
        /**
         * Constructor for a BST node.
         * @param key
         */
        BSTNode(K key) {
            this.key = key;
            this.height = 1;
        }
        
        BSTNode(K key, BSTNode<K> left,BSTNode<K> right) {
            this.key = key;
            this.left = left;
            this.right = right;  
        }
        
        //accessor
        int getHeight(){
            return height;
        }
        
        K getKey(){
            return key;
        }
        
        BSTNode<K> getLeft() {
            return left;
        }
        
        BSTNode<K> getRight() {
            return right;
        }
        
        //mutator
        void setKey(K key){
            this.key = key;
        }
        
        void setLeft(BSTNode<K> left) {
            this.left = left;
        }
        
        void setRight(BSTNode<K> right) {
            this.right = right;
        }
        
        void setHeight(int height){
            this.height = height;
        }
    }
    BSTNode<K> root;
    
    public unbalancedBST(BSTNode<K> key) {
        this.root = key;
    }
    
    public unbalancedBST() {

    }
    
    private int getBranchingFactor(BSTNode<K> node){
        int leftHeight = node.getLeft()==null ? 0 : node.getLeft().getHeight();
        int rightHeight = node.getRight()==null ? 0 : node.getRight().getHeight();
        int branchingFactor = leftHeight-rightHeight;
        return branchingFactor;
    }
    private int calHeight(BSTNode<K> node){
        if(node==null) return 0;
        int rightHeight = node.getRight()==null ? 0 : node.getRight().getHeight();
        int leftHeight = node.getLeft()==null ? 0 : node.getLeft().getHeight();
        node.setHeight(Math.max(leftHeight, rightHeight)+1);
        return node.getHeight();
    }
    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public void insert(K key) throws DuplicateKeyException, IllegalArgumentException {
        if (key == null) 
            throw new IllegalArgumentException("Key is null");
        root = insert(key, root);
    }
    
    private BSTNode<K> insert(K key, BSTNode<K> node) throws DuplicateKeyException {
        if (node == null) return new BSTNode<K>(key);
        if (node.key.compareTo(key)==0)
            throw new DuplicateKeyException("Duplicate items inserted to the tree");
        if (key.compareTo(node.key)>0)
            node.right= insert(key, node.right);
        else
            node.left= insert(key, node.left);
        calHeight(node);
        return node;
    }
    
    @Override
    public void delete(K key) throws IllegalArgumentException {
        if(key==null) throw new IllegalArgumentException("Key is null");
        try{
            root = delete(key, root);
        }catch(IllegalArgumentException e){
            System.out.println("This element is not in the tree.");
        }
        
    }

    private BSTNode<K> delete(K key, BSTNode<K> node) {
        if (node == null)//if key is not found
            throw new IllegalArgumentException();
        if(key.compareTo(node.key)==0) {
            if (node.left==null&&node.right== null) {
                return null;// deleted node is a leave
            }else if(node.left == null&& node.right!=null){
                return node.right;//deleted node has only right child
            }else if(node.left != null && node.right == null ) {
                return node.left;//deleted node has only left child
            }else {
                //deleted node has two children
                BSTNode<K> cur = node.left;
                while(cur.right!=null) {
                    // finding the in-order predecessor
                    cur = cur.right;
                }
                node.key = cur.key;// replace the key from the in-order predecessor
                node.left = delete(cur.key, node.left);// delete the predecessor from the old subtree 
                
                return node;
            }
        }
        calHeight(node);
        return node;
    }

    @Override
    public boolean search(K key) throws IllegalArgumentException {
        return search(key,root);
    }
    private boolean search(K key, BSTNode<K> node) throws IllegalArgumentException{
        if (key == null)
            throw new IllegalArgumentException("Key is null");
        if (node == null) return false;
        if (key.compareTo(node.key)==0) return true;
        if (key.compareTo(node.key)>0) return search(key, node.right);
        else return search(key, node.left);
    }

    @Override
    public String print() {
        if(root==null)
            return "It is an empty tree";   
        return print(root);
    }
    
    private String print(BSTNode<K> node) {
        String s ="";
        if(node.left!= null) s+=print(node.left);
        s+=node.key+" ";
        if (node.right != null) s+=print(node.right);
        return s;
    }

    //Same method implementations from AVLTree
    @Override
    public boolean checkForBinarySearchTree() {
        return checkForBinarySearchTree(root);
    }
    private boolean checkForBinarySearchTree(BSTNode<K> node){
        if(node == null) return true;
        boolean leftCorrect = node.left==null ? true : node.left.key.compareTo(node.key)<0;
        //check if the left is smaller than right
        boolean rightCorrect = node.right==null ? true : node.right.key.compareTo(node.key)>0;
        // check if the right is larger than the left
        return leftCorrect && rightCorrect && checkForBalancedTree(node.left)
            && checkForBalancedTree(node.right);
    }
    
    //Same method implementations from AVLTree
    @Override
    public boolean checkForBalancedTree() {
        return checkForBalancedTree(root);
    }
    private boolean checkForBalancedTree(BSTNode<K> node) {
        if(node == null) return true;
        int branchingFactor = getBranchingFactor(node);
        if(branchingFactor>1 || branchingFactor<-1) return false;
        return checkForBalancedTree(node.left) && checkForBalancedTree(node.right);
    }
    
}