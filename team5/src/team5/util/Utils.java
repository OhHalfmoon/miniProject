package team5.util;

import java.util.Scanner;

public class Utils {
		private static Scanner scanner = new Scanner(System.in);
		
		public static String nextLine(String msg) {
			System.out.println(msg);
			return scanner.nextLine();
		}
		
		public static int nextInt(String msg) {
			return Integer.parseInt(nextLine(msg));
		}
		public static int checkRange(int num, int start, int end) {
			if (num < start || num > end) {
				throw new RangeException(start, end);
			}
			return num;
		}
	}
