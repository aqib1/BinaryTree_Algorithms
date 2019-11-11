import java.util.Objects;

public class BT<T> {
	private Node<T> root = null;

	public void add(T t, int key) {
		if (Objects.isNull(root)) {
			addRoot(t, key);
		} else {
			addChildren(root, t, key);
		}
	}

	private void addChildren(Node<T> node, T t, int key) {
		// if key is greater than node.key then move right
		if (key > node.key) {
			if (Objects.isNull(node.right)) {
				node.right = new Node<>(t, key);
			} else {
				addChildren(node.right, t, key);
			}
		} else {
			// if key is smaller than node.key then move left
			if (Objects.isNull(node.left)) {
				node.left = new Node<>(t, key);
			} else {
				addChildren(node.left, t, key);
			}
		}
	}
	
	//find element O(Logn)
	public Node<T> find(int key){
		return search(root, key);
	}

	private Node<T> search(Node<T> node, int key) {
		if(node.key == key) return node;
		if(key > node.key)
			return search(node.right, key);
		else return search(node.left, key);
	}

	private void addRoot(T t, int key) {
		root = new Node<>(t, key);
	}

	class Node<M> {
		private int key;
		private M val;
		private Node<M> left;
		private Node<M> right;

		public Node(M val, int key) {
			this.val = val;
			this.key = key;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public M getVal() {
			return val;
		}

		public void setVal(M val) {
			this.val = val;
		}

		public Node<M> getLeft() {
			return left;
		}

		public void setLeft(Node<M> left) {
			this.left = left;
		}

		public Node<M> getRight() {
			return right;
		}

		public void setRight(Node<M> right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", val=" + val + "]";
		}
		
	}
}
