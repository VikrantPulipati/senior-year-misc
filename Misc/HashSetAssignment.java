import java.util.ArrayList;
import java.util.HashSet;

public class HashSetAssignment {
	
	public HashSetAssignment () {
		ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
		int x = (int)(Math.random()*12+2);
		for (int i = 0; i < x; i++) {
			HashSet<Integer> hashSet = new HashSet<Integer>();
			for (int j = 0; j < 10; j++) {
				hashSet.add((int)(Math.random()*30+1));
			}
			list.add(hashSet);
		}
		
		System.out.println(list);
		
		HashSet<Integer> intersection = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			intersection = intersection(intersection, list.get(i));
		}
		System.out.println("Intersection: " + intersection);
		
		HashSet<Integer> union = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			union = union(union, list.get(i));
		}
		System.out.println("Union: " + union);
		
		HashSet<Integer> intersectionEven = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			intersectionEven = intersectionEven(intersectionEven, list.get(i));
		}
		System.out.println("Even Intersection: " + intersectionEven);
		
		HashSet<Integer> unionEven = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			unionEven = unionEven(unionEven, list.get(i));
		}
		System.out.println("Even Union: " + unionEven);
		
	}
	
	public HashSet<Integer> intersection (HashSet<Integer> list1, HashSet<Integer> list2) {
		HashSet<Integer> output = new HashSet<Integer>();
		for (int num:list1) {
			if (list2.contains(num)) {
				output.add(num);
			}
		}
		return output;
	}
	
	public HashSet<Integer> union (HashSet<Integer> list1, HashSet<Integer> list2) {
		HashSet<Integer> output = new HashSet<Integer>();
		for (int num:list1) {
			if (!(list2.contains(num))) {
				output.add(num);
			}
		}
		return output;
	}
	
	public HashSet<Integer> intersectionEven (HashSet<Integer> list1, HashSet<Integer> list2) {
		HashSet<Integer> output = new HashSet<Integer>();
		for (int num:list1) {
			if (list2.contains(num) && num%2==0) {
				output.add(num);
			}
		}
		return output;
	}
	
	public HashSet<Integer> unionEven (HashSet<Integer> list1, HashSet<Integer> list2) {
		HashSet<Integer> output = new HashSet<Integer>();
		for (int num:list1) {
			if (!list2.contains(num) && num%2==0) {
				output.add(num);
			}
		}
		return output;
	}
	
	public static void main (String[] args) {
		HashSetAssignment app = new HashSetAssignment();
	}
}
