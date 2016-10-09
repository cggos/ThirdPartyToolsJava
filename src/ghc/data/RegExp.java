package ghc.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式，一个十分古老而又强大的文本处理工具，经常被用于字段或任意字符串的校验，
 * 仅仅用一段非常简短的表达式语句，便能够快速实现一个非常复杂的业务逻辑。
 * @author Gordon
 *
 */
public class RegExp {

	//国内 13、15、18开头的手机号正则表达式
	private static final String patternPhoneNum = 
			"^(13[0-9]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
	
	//身份证号码的正则校验，15 或 18位
	@SuppressWarnings("unused")
	private static final String patternIDNum15 = 
			"^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	private static final String patternIDNum18 = 
			"^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
	
	//金额校验，精确到2位小数
	private static final String patternMoney = "^[0-9]+(.[0-9]{2})?$";
	
	//“yyyy-mm-dd“ 格式的日期校验，已考虑平闰年
	private static final String patternDate = 
			"^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
	
	//字符串仅能是中文
	private static final String patternChinese = "^[\\u4e00-\\u9fa5]{0,}$";
	
	//由数字、26个英文字母或下划线组成的字符串
	private static final String patternAlphaNumericUnderline = "^\\w+$";
	
	//密码的强度必须是包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间
	private static final String patternPasswordStrength = 
			"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
	
	//E-mail地址合规性的正则检查语句
	private static final String patternEMailAddr = 
			"[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
	
	//IP4 和 IP6 正则语句
	private static final String patternIPv4 = 
			"\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
	@SuppressWarnings("unused")
	private static final String patternIPv6 =
			"(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";
	
	//验证文件路径和扩展名
	private static final String patternFilePath = 
			"^([a-zA-Z]\\:|\\\\)\\\\([^\\\\]+\\\\)*[^\\/:*?\"<>|]+\\.(pdf)$";
	
	//URL链接
	private static final String patternURL = "(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?";
	
	//Color Hex Codes
	private static final String patternColorHexCodes = "\\#([a-fA-F]|[0-9]){3,6}";
	
	/**
	 * 校验手机号
	 * @param phoneNum
	 * @return
	 */
	public static boolean verifyPhoneNumber(String numPhone) {
		Pattern pattern = Pattern.compile(patternPhoneNum,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(numPhone);		
		return matcher.matches();
	}
	
	/**
	 * 校验身份证号码
	 * @param numID
	 * @return
	 */
	public static boolean verifyIdentityCardNumber(String numID) {		
		return verify(patternIDNum18,numID);
	}
	
	/**
	 * 校验金额
	 * @param money
	 * @return
	 */
	public static boolean verifyMoney(String money){
		return verify(patternMoney,money);
	}
	
	/**
	 * 校验日期
	 * @param date
	 * @return
	 */
	public static boolean verifyDate(String date){
		return verify(patternDate,date);
	}
	
	/**
	 * 校验中文
	 * @param chinese
	 * @return
	 */
	public static boolean verifyChinese(String chinese){
		return verify(patternChinese,chinese);
	}
	
	/**
	 * 校验中由数字、26个英文字母或下划线组成的字符串
	 * @param alphanumericunderline
	 * @return
	 */
	public static boolean verifyAlphaNumericUnderline(String alphanumericunderline){		
		return verify(patternAlphaNumericUnderline,alphanumericunderline);
	}
	
	/**
	 * 校验密码强度
	 * @param password
	 * @return
	 */
	public static boolean verifyPasswordStrength(String password){		
		return verify(patternPasswordStrength,password);
	}
	
	/**
	 * 校验E-Mail 地址
	 * @param addrEMail
	 * @return
	 */
	public static boolean verifyEMailAddress(String addrEMail){		
		return verify(patternEMailAddr,addrEMail);
	}
	
	/**
	 * 校验IP地址
	 * @param ip
	 * @return
	 */
	public static boolean verifyIP(String ip){		
		return verify(patternIPv4,ip);
	}
	
	/**
	 * 文件路径及扩展名校验
	 * @param filepath
	 * @return
	 */
	public static boolean verifyFilePath(String filepath){
		return verify(patternFilePath,filepath);
	}
	
	/**
	 * 提取URL链接
	 * @param strText
	 * @return
	 */
	public static String extractURL(String strText){
		return extract(patternURL,strText);
	}
	
	/**
	 * 抽取网页中的颜色代码
	 * @param strText
	 * @return
	 */
	public static String extractColorHexCodes(String strText){
		return extract(patternColorHexCodes,strText);
	}
	
	/**
	 * 
	 * @param strPattern
	 * @param strInput
	 * @return
	 */
	private static boolean verify(String strPattern,String strInput){
		Pattern pattern = Pattern.compile(strPattern);
		Matcher matcher = pattern.matcher(strInput);		
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param strPattern
	 * @param strInput
	 * @return
	 */
	private static String extract(String strPattern,String strInput){
		Pattern pattern = Pattern.compile(strPattern);
		Matcher matcher = pattern.matcher(strInput);	
		if (matcher.find()) {
			return matcher.group(0);
		}else {
			return null;
		}
	}
}
