public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root;

    public RedBlackTree() {
        this.root = null;
    }

    public void insert(T data) {
        Node<T> node = new Node<>(data);
        if (root == null) {
            node.setColor(NodeColor.BLACK);
            root = node;
        } else {
            Node<T> current = root;
            while (true) {
                int compare = data.compareTo(current.getData());
                if (compare < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(node);
                        break;
                    } else {
                        current = current.getLeft();
                    }
                } else if (compare > 0) {
                    if (current.getRight() == null) {
                        current.setRight(node);
                        break;
                    } else {
                        current = current.getRight();
                    }
                } else {
                    // Data already exists in tree
                    break;
                }
            }
            fixAfterInsertion(node);
        }
    }

    private void fixAfterInsertion(Node<T> node) {
        node.setColor(NodeColor.RED);
        while (node != null && node != root && node.getParent() != null && node.getParent().isRed()) {
            if (node.getParent() == node.getGrandparent().getLeft()) {
                Node<T> uncle = node.getGrandparent().getRight();
                if (uncle != null && uncle.isRed()) {
                    node.getParent().setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node.getGrandparent().setColor(NodeColor.RED);
                    node = node.getGrandparent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getGrandparent().setColor(NodeColor.RED);
                    rotateRight(node.getGrandparent());
                }
            } else {
                Node<T> uncle = node.getGrandparent().getLeft();
                if (uncle != null && uncle.isRed()) {
                    node.getParent().setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node.getGrandparent().setColor(NodeColor.RED);
                    node = node.getGrandparent();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rotateRight(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getGrandparent().setColor(NodeColor.RED);
                    rotateLeft(node.getGrandparent());
                }
            }
        }
        root.setColor(NodeColor.BLACK);
    }

    private void rotateLeft(Node<T> node) {
        Node<T> right = node.getRight();
        node.setRight(right.getLeft());
        if (right.getLeft() != null) {
            right.getLeft().setParent(node);
        }
        right.setParent(node.getParent());
        if (node.getParent() == null) {
            root = right;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(right);
        } else {
            node.getParent().setRight(right);
        }
        right.setLeft(node);
        node.setParent(right);
    }

    private void rotateRight(Node<T> node) {
        Node<T> left = node.getLeft();
        node.setLeft(left.getRight());
        if (left.getRight() != null) {
            left.getRight().setParent(node);
        }
        left.setParent(node.getParent());
        if (node.getParent() == null) {
            root = left;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(left);
        } else {
            node.getParent().setRight(left);
        }
        left.setRight(node);
        node.setParent(left);
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node<T> node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├──" : "└──") + node.getData() + " [" + node.getColor() + "]");
            printTree(node.getLeft(), prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.getRight(), prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}