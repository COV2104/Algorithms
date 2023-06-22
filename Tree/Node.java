public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private NodeColor color;
    private Node<T> parent;

    public Node(T data) {
        this.data = data;
        this.color = NodeColor.RED;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    public boolean isRed() {
        return color == NodeColor.RED;
    }

    public boolean isBlack() {
        return color == NodeColor.BLACK;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getGrandparent() {
        if (parent != null) {
            return parent.getParent();
        } else {
            return null;
        }
    }
}