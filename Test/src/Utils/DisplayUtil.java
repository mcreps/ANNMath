package Utils;

public class DisplayUtil {

	private static final String  FORMAT = "%.6f";
	
	/**
	 * 
	 * @param outputWeights
	 * @return
	 */
	public static String displayDouble(double[] arry) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int x=0; x<arry.length;x++) {
			if (x>0) {sb.append(", ");}
			sb.append(String.format(FORMAT, arry[x]));
		}			
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 
	 * @param arry
	 * @return
	 */
	public static String displayDouble(double[][] arry) {
		StringBuilder sb = new StringBuilder();
		sb.append("{{");
		for(int i=0;i<arry.length;i++) {
			if (i>0) {sb.append(", {");}
			for (int x=0; x<arry[i].length;x++) {
				if (x>0) {sb.append(", ");}
				sb.append(String.format(FORMAT, arry[i][x]));
			}	
			sb.append("}");
		}
		sb.append("}");
		return sb.toString();
	}
}
