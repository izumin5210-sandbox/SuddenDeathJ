/**
 * Copyright (c) 2012 Masayuki IZUMI
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package info.izumin.java.library.suddendeathj;

public class SuddenDeathJ {

	private static final String TOP_SIDE = "＿", TOP_CENTER = "人", MIDDLE_LEFT = "＞ ", MIDDLE_RIGHT = " ＜", BOTTOM_SIDE = "￣";
	private static final String[] BOTTOM_CENTER = {"Y", "^"};

	public static String generate(String arg) {
		StringBuilder builder = new StringBuilder();
		
		String[] strings = arg.split("\n");
		int width = getStringWidth(strings);
		
		builder.append(TOP_SIDE);
		for (int i = 0; i < (width+1)/2; i++) builder.append(TOP_CENTER);
		builder.append(TOP_SIDE + "\n");
		
		for (String str : strings) {
			builder.append(MIDDLE_LEFT);
			if (getStringWidth(str) < width) {
				builder.append(genSpaces(width/2) + str + genSpaces((width/2)+(width%2)));
			} else {
				builder.append(str);
			}
			builder.append(MIDDLE_RIGHT + "\n");
		}
		
		builder.append(BOTTOM_SIDE);
		for (int i = 0; i < (width+1)/2; i++) builder.append(BOTTOM_CENTER[0] + BOTTOM_CENTER[1]);
		builder.append(BOTTOM_CENTER[0] + BOTTOM_SIDE);
		
		return builder.toString();
	}

	private static int getStringWidth(String str) {
		int width = 0;
		for (int i = 0; i < str.length(); i++) width += getCharWidth(str.charAt(i));
		return width;
	}

	private static int getStringWidth(String[] strings) {
		int maxwidth = 0;
		for (String str : strings) {
			int width = getStringWidth(str);
			if (width > maxwidth) maxwidth = width;
		}
		return maxwidth;
	}

	private static int getCharWidth(char c) {
		if ((c <= '\u007e') || // 半角英数字，記号
				(c == '\u00a5') || // ¥
				(c == '\u203e') || // ‾
				(c >= '\uff61' && c <= '\uff9f') // 半角カナ
				) {
			return 1;
		} else {
			return 2;
		}
	}

	private static String genSpaces(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) builder.append(" ");
		return builder.toString();
	}
}
