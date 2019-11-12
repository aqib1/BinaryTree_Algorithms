package com.org.bt;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

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

	// find element O(Logn)
	public Node<T> find(int key) {
		return search(root, key);
	}

	private Node<T> search(Node<T> node, int key) {
		if (node.key == key)
			return node;
		if (key > node.key)
			return search(node.right, key);
		else
			return search(node.left, key);
	}

	private void addRoot(T t, int key) {
		root = new Node<>(t, key);
	}

	public void print(BT_Traversal bt) {
		print(bt, root);
	}

	private void print(BT_Traversal bt, Node<T> node) {
		switch (bt) {
		case IN_ORDER: {
			inOrderTraversal(node);
			break;
		}
		case PRE_ORDER: {
			preOrderTraversal(node);
			break;
		}
		case POST_ORDER: {
			postOrderTraversal(node);
			break;
		}
		case LEVEL_ORDER: {
			// levelOrderUsingHeight();
			levelOrderUsingQueue(node);
			break;
		}
		case SPIRAL_ORDER: {
			spiralOrderTraversal(node);
			break;
		}
		default:
			inOrderTraversal(node);
		}
	}

	// Spiral solution using two stacks
	private void spiralOrderTraversal(BT<T>.Node<T> node) {
		Stack<Node<T>> leftToRight = new Stack<>();
		Stack<Node<T>> rightToLeft = new Stack<>();
		leftToRight.push(node);
		while (!leftToRight.isEmpty()) {
			Node<T> f = leftToRight.pop();
			if (!Objects.isNull(f)) {
				System.out.println(f);
				rightToLeft.push(f.right);
				rightToLeft.push(f.left);
				while (!rightToLeft.isEmpty()) {
					Node<T> s = rightToLeft.pop();
					if (!Objects.isNull(s)) {
						System.out.println(s);
						leftToRight.push(s.left);
						leftToRight.push(s.right);
					}
				}

			}
		}
	}

	// O(n) where n is no of nodes
	private void levelOrderUsingQueue(Node<T> node) {
		levelOrderPrintUsingQueue(node);

	}

	// leftNode
	public void leftNodeBT(BT_Traversal bt_Traversal) {
		print(bt_Traversal, root.left);
	}

	// rightNode
	public void rightNodeBT(BT_Traversal bt_Traversal) {
		print(bt_Traversal, root.right);
	}

	public int depth(int key) {
		return depth(root, key, -1);
	}

	private int depth(Node<T> node, int key, int i) {
		if (!Objects.isNull(node) && node.key == key)
			return i + 1;
		else if (Objects.isNull(node))
			return -1;

		if (key > node.key)
			return depth(node.right, key, i + 1);
		else
			return depth(node.left, key, i + 1);
	}

	// Outer Boundary Print
	public void outerBoundaryPrint(BT_Traversal bt_Traversal) {
		switch (bt_Traversal) {
		case IN_ORDER: {
			inOrderOuterBoundery(root);
			break;
		}
		case PRE_ORDER: {
			preOrderOuterBoundery(root);
			break;
		}
		case POST_ORDER: {
			postOrderOuterBounder(root);
			break;
		}
		default:
			inOrderOuterBoundery(root);
			break;
		}
	}

	// LEFT - RIGHT - R
	private void postOrderOuterBounder(BT<T>.Node<T> node) {
		if (Objects.isNull(node))
			return;
		printAllLeft(node.left);
		printAllRight(node.right);
		System.out.println(node);

	}

	// R - LEFT - RIGHT
	private void preOrderOuterBoundery(BT<T>.Node<T> node) {
		if (Objects.isNull(node))
			return;
		System.out.println(node);
		printAllLeft(node.left);
		printAllRight(node.right);

	}

	private void printAllLeft(BT<T>.Node<T> left) {
		if (Objects.isNull(left))
			return;
		printAllLeft(left.left);
		System.out.println(left);
	}

	private void printAllRight(BT<T>.Node<T> right) {
		if (Objects.isNull(right))
			return;
		printAllRight(right.right);
		System.out.println(right);
	}

	// LEFT - R - RIGHT
	private void inOrderOuterBoundery(Node<T> node) {
		if (Objects.isNull(node))
			return;
		printAllLeft(node.left);
		System.out.println(node);
		printAllRight(node.right);

		// inOrderOuterBoundery(node, true, false);
	}

	@Deprecated
	public void inOrderOuterBoundery(Node<T> node, boolean left, boolean right) {
		if (Objects.isNull(node))
			return;
		if (left)
			inOrderOuterBoundery(node.left, left, right);

		System.out.println(node);

		if (isRoot(node)) {
			left = false;
			right = true;
		}
		if (right)
			inOrderOuterBoundery(node.right, left, right);
	}

	private boolean isRoot(BT<T>.Node<T> node) {
		// TODO Auto-generated method stub
		return node.key == root.key;
	}

	private void levelOrderPrintUsingQueue(Node<T> node) {
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Node<T> head = queue.poll();
			System.out.println(head);
			if (!Objects.isNull(head.left)) {
				queue.add(head.left);
			}
			if (!Objects.isNull(head.right)) {
				queue.add(head.right);
			}
		}
	}

	@Deprecated
	// O(n) + O(n * n)
	public void levelOrderUsingHeight() {
		int height = height(); // O(n)
		for (int level = 0; level <= height; level++) {
			printLevelOrder(root, level);
		}

	}

	// O (n * n)
	private void printLevelOrder(Node<T> node, int level) {
		if (Objects.isNull(node))
			return;
		if (level == 0)
			System.out.println(node);
		else
			printLevelOrder(node.left, level - 1);
		printLevelOrder(node.right, level - 1);
	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> root) {
		if (Objects.isNull(root))
			return -1;
		int leftHeight = height(root.left);
		int rightHeight = height(root.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}

	private void postOrderTraversal(Node<T> node) {
		if (Objects.isNull(node))
			return;

		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		System.out.println(node);
	}

	private void preOrderTraversal(Node<T> node) {
		// Node, left, right
		if (Objects.isNull(node))
			return;

		System.out.println(node);
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
	}

	private void inOrderTraversal(Node<T> root) {
		// left, root, right
		if (Objects.isNull(root))
			return;

		inOrderTraversal(root.left);
		System.out.println(root);
		inOrderTraversal(root.right);
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
