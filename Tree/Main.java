public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(15);
        tree.insert(5);
        tree.insert(30);

        tree.printTree();
    }
}
