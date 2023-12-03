public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Worlds");
        BST tree = new BST();
        int[] toAdd = new int[] {22, 100, 33, 26, 43, 63};
        for(int num : toAdd) {
            tree.insert(num);
        } 
        tree.printListOfDepths();
        System.out.println();
        System.out.println(tree.countParentNodes());
        
        System.out.println(tree.isChild(tree.find(22), tree.find(100)));
    }
    
}