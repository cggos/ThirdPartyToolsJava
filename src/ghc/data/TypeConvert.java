package ghc.data;

import java.text.DecimalFormat;

public class TypeConvert {

	/**
	 * float转String，保留两位小数
	 * @param number
	 * @return
	 */
	public static String FloatToString(float number){
		//构造方法的字符格式这里如果小数不足2位,会以0补足.
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		return decimalFormat.format(number);
	}
}
