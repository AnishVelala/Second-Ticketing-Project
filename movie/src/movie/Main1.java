package movie;


public class Main1 {
    public static void main(String[] args) {
        MyLinkedList<Integer> myList = new MyLinkedList<>();

        // Insert elements into the linked list
        myList.add(1);
        myList.add(2);
        myList.add(3);

        // Print the linked list
        System.out.println("Linked List: " + myList);

        // Get the size of the linked list
        int size = myList.getTheSize();
        System.out.println("Size of the linked list: " + size);

        // Insert an element at a specific index
       boolean success = myList.insert(15, 4);
        if (success) {
            System.out.println("Insertion successful!");
            System.out.println("Updated Linked List: " + myList);
        } else {
            System.out.println("Insertion failed! Index out of bounds.");
        }
    }
}
