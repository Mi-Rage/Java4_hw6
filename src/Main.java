
class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Tree tree = new Tree();
            while (tree.maxDepth() < 6) {
                int value = (int) (Math.random() * 201) - 100;
                System.out.print(value + " ");
                tree.insert(value);
            }
            System.out.println();
            System.out.println("Display Tree:");
            tree.displayTree();
            System.out.println();
            System.out.println("root:" + tree.root + " maxDepth:" + tree.maxDepth());

        }

    }
}


class Tree {

    private class TreeNode {
        private int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data:" + data +
                    '}';
        }


    }

    TreeNode root;
    static int count = 0;  // Добавлено для метода определния глубины ноды

    public void insert(int data) {
        TreeNode node = new TreeNode(data);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                parent = current;
                if (data < current.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else if (data > current.data) {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);

            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    private void preOrderTraverse(TreeNode current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preOrderTraverse(current.left);
            preOrderTraverse(current.right);
        }
    }

    public void displayTree() {
        preOrderTraverse(root);
    }


    // Возможно пригодится для определения сбалансированности дерева
    public int findDepth(int data) {
        count = 0;
        return findDepth(root, data);
    }

    private int findDepth(TreeNode node, int data) {
        if (node == null) {
            return -1;
        }

        if (data == node.data) {
            return count;
        } else if (data < node.data) {
            count++;
            return findDepth(node.left, data);
        } else {
            count++;
            return findDepth(node.right, data);
        }
    }
}