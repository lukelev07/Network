/* Set.java */
package player;
import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  protected List set;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    set = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return set.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c){
    ListNode curr = set.front();
    while (curr.isValidNode()) {
      try {
        if (c.compareTo(curr.item()) == 0) {
          return;
        }
        if (c.compareTo(curr.item()) < 0) {
          curr.insertBefore(c);
          // System.out.println("inserted");
          return;
        }
        curr = curr.next();
      }
      catch (InvalidNodeException e) {
        return;
      }
    }
    set.insertBack(c);
  }

  public boolean contains(Comparable c) {
    ListNode curr = set.front();
    while (curr.isValidNode()) {
      try {
        if (curr.item() == c) {
          return true;
        }
        curr = curr.next();
      }
      catch (InvalidNodeException e) {
        return false;
      }
    }
    return false;
  }
  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    ListNode this_curr = this.set.front();
    ListNode s_curr = s.set.front();
    try{
      while (this_curr.isValidNode()) {
        if (((Comparable) s_curr.item()).compareTo(this_curr.item()) > 0) {
          this_curr = this_curr.next();
        }
        else {
          if (((Comparable) s_curr.item()).compareTo(this_curr.item()) < 0) {
            this_curr.insertBefore(s_curr.item());
          }
          s_curr = s_curr.next();
        }
      }
      while (s_curr.isValidNode()) {
        set.insertBack(s_curr.item());
        s_curr = s_curr.next();
      }
    }
    catch (InvalidNodeException e) {
      return;
    }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
      //Your solution here.
    ListNode this_curr = set.front();
    ListNode s_curr = s.set.front();
    while (s_curr.isValidNode()) {
      try {
        if (((Comparable)this_curr.item()).compareTo((Comparable)s_curr.item()) == 0) {
          this_curr = this_curr.next();
          s_curr = s_curr.next();
        }
        else if (((Comparable)this_curr.item()).compareTo((Comparable)s_curr.item()) > 0) {
          s_curr = s_curr.next();
        }
        else {
          ListNode temp = this_curr.next();
          this_curr.remove();
          this_curr = temp;
        }
      }
      catch (InvalidNodeException e) {
        break;
      }
      
    }
    while (this_curr.isValidNode()) {
      try {
        ListNode temp = this_curr.next();
        this_curr.remove();
        this_curr = temp;
      }
      catch (InvalidNodeException f) {
        break;
      }
    }
    
  }


  public void remove(Object i) {
    this.set.remove(i);
  }

  // public void intersect(Set s) {
  //   ListNode this_curr = this.set.front();
  //   ListNode s_curr = s.set.front();
  //   boolean past = false;
  //   try {
  //     while (this_curr.isValidNode()) {
  //         if (((Comparable) s_curr.item()).compareTo(this_curr.item()) == 0) {
  
  //           s_curr = s_curr.next();
  //           s_curr.item();
  //           past = false;
  //           this_curr = this_curr.next();
  
  //         }
  //         if (((Comparable) s_curr.item()).compareTo(this_curr.item()) < 0) {
  
  //           s_curr = s_curr.next();
  //           s_curr.item();
  //           past = false;
  
  //         }
  //         if (((Comparable) s_curr.item()).compareTo(this_curr.item()) > 0) {
  //           s_curr.item();
  //           this_curr = this_curr.next();
  //           this_curr.prev().remove();
  //           if (s_curr == s.set.back()) past = true;
  //         }
  //     }
  //   }
  //   catch (InvalidNodeException e) {
  //     try {
  //     if (past) this_curr = this_curr.prev();
  //     //if (this_curr.isValidNode()) System.out.println("this_curr is " + this_curr.item());  
  //       while (this_curr.isValidNode() && this_curr != set.back()) {
  //         set.back().remove();
  //       }
  //     }
  //     catch (InvalidNodeException f) {
  //       return;
  //     }
  //   }
  // }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    String temp = set.toString();
    return "{" + temp.substring(1,temp.length()-1) + "}";
  }

  public static void main(String[] argv) {
    Set test = new Set();
    for (int i = 0; i < 10; i += 2) {
      test.insert(new Integer(i));
    }
    Set test1 = new Set();
    for (int i = 1; i < 10; i += 2) {
      test1.insert(new Integer(i));
    }
    System.out.println(test);
    System.out.println(test1);
    test.union(test1);
    System.out.println(""+test+"\n");
    System.out.println(test);
    System.out.println(test1);
    test.intersect(test1);
    System.out.println(""+test+"\n");

    Set test2 = new Set();
    Set test3 = new Set();
    for (int i = 0; i < 16; i += 3) {
      test2.insert(new Integer(i));
      test3.insert(new Integer(i));
    }
    Set test4 = new Set();
    Set test5 = new Set();
    for (int i = 0; i < 30; i += 2) {
      test4.insert(new Integer(i));
      test5.insert(new Integer(i));
    }
    System.out.println(test2);
    test2.union(test3);
    System.out.println(test2);
    test2.intersect(test3);
    System.out.println(test2);
    System.out.println("hello");



    System.out.println("test5 is the set: " +test5);
    System.out.println("test3 is the set: " +test3);
    test5.intersect(test3);
    
    System.out.println("test5 intersect test3 is: "+test5);    


    Set test6 = new Set();
    test6.intersect(test2);
    System.out.println(test6);
    test6.union(test2);
    System.out.println(test6);







    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }}
