public class BinaryTree<T> {
    protected T data;
    protected BinaryTree<T> leftNode;
    protected BinaryTree<T> rightNode;
    protected BinaryTree<T> parent;

    public BinaryTree()
    {
        this.data = null;
        this.leftNode = null;
        this.rightNode = null;
        this.parent = null;

    }

    public BinaryTree(T data)
    {
        this.data = data;
        this.leftNode = null;
        this.rightNode = null;
        this.parent = null;

    }


    public void setLeftNode(BinaryTree<T> leftNode) {

        this.leftNode = leftNode;
        if (leftNode != null)
            this.leftNode.parent = this;
    }

    public void setRightNode(BinaryTree<T> rightNode)
    {
        this.rightNode = rightNode;
        if (rightNode != null)
            this.rightNode.parent = this;
    }

    public T getData()
    {
        return this.data;
    }
    public void setData(T data)
    {
        this.data = data;
    }

    public BinaryTree<T> getLeftNode(){
        return this.leftNode;
    }
    public BinaryTree<T> getRightNode()
    {
        return this.rightNode;
    }
    public void removeLeftNode()
    {
        this.setLeftNode(null);
    }
    public void removeRightNode()
    {
        this.setRightNode(null);
    }

    public boolean hasChildren()
    {
        return !(this.leftNode == null && this.rightNode == null);
    }
    public boolean isRoot()
    {
        return this.parent == null;
    }

    // Display method
    public void display() {
        System.out.println(this.data);
        if (this.leftNode != null)
            System.out.println("____" + this.leftNode.data);
        else
            System.out.println("____");

        if (this.rightNode != null)
            System.out.println("____" + this.rightNode.data);
        else
            System.out.println("____");


        // displayRecursive(this, 0);
    }

    private void displayRecursive(BinaryTree<T> node, int depth) {
        if (node != null) {
            // Print spaces for better visualization
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }

            // Print the data of the current node
            System.out.println(node.getData());

            // Recursively display the left and right subtrees
            displayRecursive(node.getLeftNode(), depth + 1);
            displayRecursive(node.getRightNode(), depth + 1);
        }
    }
    public static void main(String[] args) {
        BinaryTree<Integer> root = new BinaryTree<>(1);

        root.setLeftNode(new BinaryTree<>(2));
        root.setRightNode(new BinaryTree<>(3));

        root.getLeftNode().setLeftNode(new BinaryTree<>(4));
        // root.getLeftNode().setRightNode(new BinaryTree<>(5));

        root.getRightNode().setLeftNode(new BinaryTree<>(6));
        // root.getRightNode().setRightNode(new BinaryTree<>(7));



        root.display();
    }

}
