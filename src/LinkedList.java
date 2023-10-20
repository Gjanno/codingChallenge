import java.io.*;


public class LinkedList
{

    /// @brief Method to check if there is a cycle in linked list
    /// @param head: the head of the linked list
    /// @return true if there is a cycle and false if no cycle
    public static boolean hasCycle(Node head)
    {
        //Check if the linked list is empty
        if (head == null)
        {
            return false;//if head==null the linked list is empty
        }
        // Initialize two node slow and fast, then pointing to the head of the linked list
        Node slow = head;
        Node fast = head;

        //Traverse the linked list by moving the slow pointer one step and the fast pointer two steps faster
        while (slow != null && fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;// if slow==fast there must be a cycle
            }
        }
        return false;// if loop traverse until the end without returning true, there is no cycle
    }


}
