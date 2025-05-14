package main;

public class BST<Key extends Comparable<Key>, Value> extends Tree<Key, Value> {

    /**
     * 일반적으로 BST는 중위순회를 하면 Key가 오름차순으로 출력됩니다.
     * <br/>그렇게 하면 재미가 없으니 중위순회를 하면 Key가 "내림차순"이 되도록 해봅시다.
     */
    // 그럼 왼쪽이 더 큰 값으로 되게
    public BST(Node<Key, Value> root) {
        super(root);
    }

    @Override
    public void put(Key key, Value value) {
        super.setRoot(put(super.getRoot(), key, value));
    }

    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value) {
        // TODO 해당 메소드를 완성하시오.
        if (node == null) {return null;}

        int compare = key.compareTo(node.getKey());
//        System.out.println("compare: "+compare);
        Node<Key, Value> child;
        if (compare >= 0) {
            child = node.getLeft();
            if (child != null) {put(node.getLeft(), key, value); return node;}
            node.setLeft(new Node<>(key, value));
        }
        else {
            child = node.getRight();
            if (child != null) {put(node.getRight(), key, value); return node;}
            node.setRight(new Node<>(key, value));
        }
//        System.out.println("[put] node key: " + node.getKey() + " | node value:" +node.getValue());
        return node;
    }

    @Override
    public void delete(Key key) {
        super.setRoot(deleteAll(super.getRoot(), key));
    }

    public Node<Key, Value> delete(Node<Key, Value> node, Key key) {
        // TODO 해당 메소드를 완성하시오.
        // TODO 강의 자료와 다르게 max와 deleteMax를 사용합시다.
        // key 가 일치하는 node를 찾아서 삭제하는 메서드인 것 같은데
        // 어떻게 max와 deleteMax를 사용할 수 있을까
        // 아몰랑
        if (node == null) {return null;}

        int compare = key.compareTo(node.getKey());
        if (compare > 0) {
            node.setLeft(delete(node.getLeft(), key));
        } else if (compare < 0) {
            node.setRight(delete(node.getRight(), key));
        } else{
            // 지우려는 노드를 찾았음
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            // 양쪽의 자식 노드가 모두 있음
            // 오른쪽에서 가장 큰 노드를 이 자리로 가져옴
            Node<Key, Value> rightMax = max(node.getRight());
            Node<Key, Value> right = deleteMax(node.getRight());

            rightMax.setRight(right);
            rightMax.setLeft(node.getLeft());
            return rightMax;
        }

        return node;
    }

    public Node<Key, Value> deleteAll(Node<Key, Value> root, Key key) {
        if (root == null) return null;

        //왼쪽, 오른쪽 서브트리에서 삭제
        root.setLeft(deleteAll(root.getLeft(), key));
        root.setRight(deleteAll(root.getRight(), key));

        if (root.getKey().compareTo(key) == 0) {return delete(root, key);}
        return root;
    }

    private Key max() {
        if (super.getRoot() == null) return null;
        return max(super.getRoot()).getKey();
    }

    /**
     * 강의 자료는 min을 찾지만 이번엔 max를 찾아봅시다.
     * 요구하는 BST의 중위순회의 Key 값이 내림차순임에 주의하세요.
     *
     * @param node
     * @return Key가 큰 노드
     */
    private Node<Key, Value> max(Node<Key, Value> node) {
        // TODO 해당 메소드를 완성하시오.
        if (node == null) {return null;}
        if (node.getLeft() != null) {return max(node.getLeft());}
        return node;
    }

    private void deleteMax() {
        if (super.getRoot() == null) return;
        super.setRoot(deleteMax(super.getRoot()));
    }

    /**
     * 강의 자료는 deleteMin이지만 이번엔 deleteMax를 해봅시다.
     * 요구하는 BST의 중위순회의 Key 값이 내림차순임에 주의하세요.
     *
     * @param node
     * @return node
     */
    private Node<Key, Value> deleteMax(Node<Key, Value> node) {
        // TODO 해당 메소드를 완성하시오.
        // root 노드가 node로 입력 됨
        if (node == null) return null;
        if (node.getLeft() == null) return node.getRight();
        node.setLeft(deleteMax(node.getLeft()));
        return node;
    }

    @Override
    public Tree<Key, Value> copy() {
        return new BST<>(copy(super.getRoot()));
    }

    /**
     * this에 대해서 루트를 포함한 모든 자식 노드들의 레퍼런스가 다르지만,
     * <br/>Key와 Value가 동일한 새로운 BST를 만들려고 합니다.
     *
     * @param my 카피할 노드를 인자로 받습니다.
     * @return 카피된 노드를 반환합니다.
     * @see BST#delete
     */
    private Node<Key, Value> copy(Node<Key, Value> my) {
        // TODO 해당 메소드를 완성하시오.
        if (my == null) {return null;}

        // 복사
        Node<Key, Value> newRoot = new Node<>(my.getKey(), my.getValue());

        // dfs, bfs 같은 트리 순회로 복사하기
        newRoot.setLeft(copy(my.getLeft()));
        newRoot.setRight(copy(my.getRight()));

        // root node를 반환할 것
        return newRoot;
    }

    @Override
    public Value get(Key key) {
        return get(super.getRoot(), key);
    }

    private Value get(Node<Key, Value> node, Key key) {
        // TODO 해당 메소드를 완성하시오.
        // TODO key가 존재하지 않는다면 null을 반환하시오.

        Node<Key, Value> curr = node;
        int compare = 0;

        while (curr != null) {
            compare = key.compareTo(curr.getKey());
            System.out.println("current key : "+curr.getKey());
            if (compare > 0) {
                curr = curr.getLeft();
            } else if (compare < 0) {
                curr = curr.getRight();
            } else if (compare == 0) {
                return curr.getValue();
            }
        }

        return null;
    }
}
