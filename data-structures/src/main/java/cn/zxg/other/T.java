package cn.zxg.other;

import java.math.BigDecimal;

/**
 * x^3+y^3+z^3=42
 * @author zhangxg
 */
public class T {
 
	// 1�����뷨 �Ƚ�1 -  N^3 ��ֵȫ�������   Ȼ��
	
	// 2  ͨ�����������ܴ������һ���ӻ��߼���  Ȼ�� ��42 ��Ӽ�  Ȼ�󿪷�  
	//
	public static void main(String[] args) {
		BigDecimal b=new BigDecimal(Integer.MAX_VALUE).pow(3).add(new BigDecimal(Long.MAX_VALUE).pow(3));
		BigDecimal b1=new BigDecimal(Integer.MAX_VALUE).pow(3).subtract(new BigDecimal(Long.MAX_VALUE).pow(3));
		
		
		System.out.println(b.add(new BigDecimal("42")));
		System.out.println(b.subtract(new BigDecimal("42")));
		System.out.println("xxxxxxxxx");
	}
	
	
}
