package cn.zxg.other;

import java.math.BigDecimal;

/**
 * x^3+y^3+z^3=42
 * @author zhangxg
 */
public class T {
 
	// 1个人想法 先将1 -  N^3 的值全部算出来   然后
	
	// 2  通过计算两个很大的数的一个加或者减法  然后 和42 相加减  然后开方  
	//
	public static void main(String[] args) {
		BigDecimal b=new BigDecimal(Integer.MAX_VALUE).pow(3).add(new BigDecimal(Long.MAX_VALUE).pow(3));
		BigDecimal b1=new BigDecimal(Integer.MAX_VALUE).pow(3).subtract(new BigDecimal(Long.MAX_VALUE).pow(3));
		
		
		System.out.println(b.add(new BigDecimal("42")));
		System.out.println(b.subtract(new BigDecimal("42")));
		System.out.println("xxxxxxxxx");
	}
	
	
}
