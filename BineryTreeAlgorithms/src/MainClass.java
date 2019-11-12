import java.util.Arrays;

import com.org.bt.BT;
import com.org.bt.BT_Traversal;

public class MainClass {

	public static void main(String[] args) {
		int[] arr = { 2, 3, 1, 23, 45, 6 };
		Arrays.sort(arr);
		
		BT<Integer> bt = new BT<>();
		for(int x:arr)
			bt.add(x, x);
		
		bt.print(BT_Traversal.LEVEL_ORDER);
		
		// BT<String> bt = new BT<>();
		// bt.add("Test", 5);
		// bt.add("t1", 4);
		// bt.add("t2", 6);
		// bt.add("t3", 1);
		// bt.add("t4", 3);
		// bt.add("t5", 8);
		// System.out.println(bt.find(4));
		// bt.print(BT_Traversal.SPIRAL_ORDER);
		// System.out.println(bt.height());
		// bt.outerBoundaryPrint(BT_Traversal.POST_ORDER);
		// System.out.println(bt.depth(3));
	}
}
