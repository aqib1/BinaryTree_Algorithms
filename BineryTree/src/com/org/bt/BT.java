package com.org.bt;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

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
		switch (bt) {
		case IN_ORDER: {
			inOrderTraversal(root);
			break;
		}
		case PRE_ORDER: {
			preOrderTraversal(root);
			break;
		}
		case POST_ORDER: {
			postOrderTraversal(root);
			break;
		}
		case LEVEL_ORDER: {
			// levelOrderUsingHeight();
			levelOrderUsingQueue();
			break;
		}
		default:
			inOrderTraversal(root);
		}
	}

	// O(n) where n is no of nodes
	private void levelOrderUsingQueue() {
		levelOrderPrintUsingQueue(root);

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

	// O(n) + O(n * n)
	private void levelOrderUsingHeight() {
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
