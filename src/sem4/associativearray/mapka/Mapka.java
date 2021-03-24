package sem4.associativearray.mapka;

import sem3.linkedlistik.list.LinkedListik;

import java.util.Comparator;

/**
 * author: Bikchurin Alexey, 9302;
 *
 * How to use:
 * Mapka<Integer, String> map = new Mapka<>();
 *
 * map.insert(1, "Cat");
 * map.insert(2, "Dog");
 *
 * System.out.print(map.size());
 *
 * @param <K> key
 * @param <V> value
 */
public class Mapka <K extends Comparable<K>, V extends Comparable<V>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public void insert(K key, V value){
        nullCheck(key);

        if(value == null)
            return;

        root = insert(root, key, value);
        root.color = BLACK;
    }

    public void remove(K key){
        nullCheck(key);

        if(!contains(key))
            return;

        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = remove(root, key);

        if(root != null)
            root.color = BLACK;
    }

    public V find(K key){
        nullCheck(key);

        return find(root, key);
    }

    public void clear(){
        root = null;
    }

    public LinkedListik<K> getKeys(){
        if(root == null)
            return new LinkedListik<>();

        K left = min();
        K right = max();
        LinkedListik<K> keys = new LinkedListik<>();

        nullCheck(left);
        nullCheck(right);
        getKeys(root, keys, left, right);

        return keys;
    }

    public LinkedListik<V> getValues(){
        if(root == null)
            return new LinkedListik<>();

        K left = min();
        K right = max();
        LinkedListik<V> values = new LinkedListik<>();

        nullCheck(left);
        nullCheck(right);
        getValues(root, values, left, right);

        return values;
    }

    public void print(){
        System.out.println(toString());
    }

    public int size(){
        return size(root);
    }

    // |============================| \\
    // |    OTHER SIMPLE METHODS    | \\
    // |============================| \\

    @Override
    public String toString() {
        LinkedListik<K> keys = getKeys();
        StringBuilder builder = new StringBuilder();

        builder.append("{\n");
        for(K key: keys){
            builder.append("\t")
                    .append(key.toString())
                    .append(" = ")
                    .append(find(key).toString())
                    .append("\n");
        }
        builder.append("}\n");

        return builder.toString();
    }

    public boolean contains(K key){
        return find(key) != null;
    }

    private boolean isRed(Node node){
        if(node == null)
            return false;

        return node.color == RED;
    }

    /**
     * Check if variable is null
     *
     * @param value variable to check
     * @param <T> type of variable
     */
    private<T> void nullCheck(T value){
        if(value == null)
            throw new IllegalArgumentException("Some argument is null!");
    }

    private int size(Node node){
        return (node == null)? 0 : node.size;
    }

    private K min(){
        nullCheck(root);

        return min(root).key;
    }

    private Node min(Node node){
        return (node.left == null)? node : min(node.left);
    }

    private K max(){
        nullCheck(root);

        return max(root).key;
    }

    private Node max(Node node){
        return (node.right == null)? node : max(node.right);
    }

    // |===========================| \\
    // |       OTHER METHODS       | \\
    // |===========================| \\

    public Entry<K, V>[] getSortEntryArray(Comparator<? super Entry<K, V>> comparator) {
        LinkedListik<K> keys = getKeys();
        LinkedListik<V> values = getValues();
        Entry<K, V>[] entry = new Entry[keys.getSize()];

        for(int i = 0; i < keys.getSize(); i++)
            entry[i] = new Entry<>(keys.at(i), values.at(i));

        ArraySorting.quickSort(entry, comparator);

        return entry;
    }

    /**
     * Find element value by key and
     *
     * @param node starting peak
     * @param key to find value
     * @return value of element
     */
    private V find(Node node, K key){
        while(node != null){
            int temp = key.compareTo(node.key);

            if(temp < 0)
                node = node.left;
            else if(temp > 0)
                node = node.right;
            else
                return node.value;
        }

        return null;
    }

    /**
     * Insert element by value and key
     *
     * @param node starting peak
     * @param key for element
     * @param value for element
     * @return
     */
    private Node insert(Node node, K key, V value){
        if(node == null)
            return new Node(RED, key, value, 1);

        int temp = key.compareTo(node.key);

        if(temp < 0)
            node.left = insert(node.left, key, value);
        else if(temp > 0)
            node.right = insert(node.right, key, value);
        else
            node.value = value;

        if(isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);

        if(isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);

        if(isRed(node.right) && isRed(node.left))
            flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private Node rotateLeft(Node node){
        Node temp = node.right;

        node.right = temp.left;
        temp.left = node;
        temp.color = temp.left.color;
        temp.left.color = RED;
        temp.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;

        return temp;
    }

    private Node rotateRight(Node node){
        Node temp = node.left;

        node.left = temp.right;
        temp.right = node;
        temp.color = temp.right.color;
        temp.right.color = RED;
        temp.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;

        return temp;
    }

    private void flipColors(Node node){
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node moveRedLeft(Node node){
        flipColors(node);

        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);

            flipColors(node);
        }

        return node;
    }

    private Node moveRedRight(Node node){
        flipColors(node);

        if (isRed(node.left.left)) {
            node = rotateRight(node);

            flipColors(node);
        }

        return node;
    }

    private Node balance(Node node){
        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private Node removeMin(Node node){
        if(node.left == null)
            return null;

        if(!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);

        node.left = removeMin(node.left);

        return balance(node);
    }

    private Node remove(Node node, K key){
        if(key.compareTo(node.key) < 0){
            if(!isRed(node.left) && !isRed(node.left.left))
                node = moveRedLeft(node);

            node.left = remove(node.left, key);
        } else {
            if(isRed(node.left))
                node = rotateRight(node);

            if(key.compareTo(node.key) == 0 && node.right == null)
                return null;

            if(!isRed(node.right) && !isRed(node.right.left))
                node = moveRedRight(node);

            if(key.compareTo(node.key) == 0){
                Node min = min(node.right);
                node.key = min.key;
                node.value = min.value;
                node.right = removeMin(node.right);
            } else
                node.right = remove(node.right, key);
        }

        return balance(node);
    }

    private void getKeys(Node node, LinkedListik<K> ans, K left, K right){
        if(node == null)
            return;

        int cl = left.compareTo(node.key);
        int cr = right.compareTo(node.key);

        if(cl < 0)
            getKeys(node.left, ans, left, right);

        if(cl <= 0 && cr >= 0)
            ans.pushBack(node.key);

        if(cr > 0)
            getKeys(node.right, ans, left, right);
    }

    private void getValues(Node node, LinkedListik<V> ans, K left, K right){
        if(node == null)
            return;

        int cl = left.compareTo(node.key);
        int cr = right.compareTo(node.key);

        if(cl < 0)
            getValues(node.left, ans, left, right);

        if(cl <= 0 && cr >= 0)
            ans.pushBack(node.value);

        if(cr > 0)
            getValues(node.right, ans, left, right);
    }

    private class Node {

        boolean color;
        K key;
        V value;
        Node left;
        Node right;
        int size;

        public Node(boolean color, K key, V value, int size) {
            this.color = color;
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /**
     * Class like Map.Entry<K, V>
     *
     * @param <K> key
     * @param <V> value
     */
    public static class Entry<K, V>{

        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
