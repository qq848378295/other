package cn.zhangxg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  有四位同学中的一位做了好事，不留名，表扬信来了之后，校长问这四位是谁做的好事。
 * A说：不是我。
 * B说：是C。
 * C说：是D。
 * D说：C胡说。
 * n已知三个人说的是真话，一个人说的是假话。现在要根据这些信息，找出做了好事的人。
 * n思路:将A,B,C,D 映射 1,2,3,4 通过累加结果等于3来做判断结果
 */
public class App {
	public static void main(String[] args) {
//		System.out.println("Hello World!");
//		for (int i = 1; i < 5; i++) {
//			if (a(i) + b(i) + c(i) + d(i) == 3) {
//				System.out.println("result:" + i);
//				break;
//			}
//		}

//		int i=10;
//		//1 1 2 3 5 8 13 21 34 55 89
//		System.out.println(fb(i));
		
		
//		int i=14;
//		to2bit(i,3);
		move(65,'a','b','c');
		System.out.println(count);
	}
	static int count;
	static List<String> list=new ArrayList();
	private static void move(int i, char a, char b, char c) {
		if(i==0) {
			return ;
		}
		count++;
		move(i-1,a,c,b);
//		System.out.println("移动"+i+"从"+a+"到"+c);
		String s="移动"+i+"从"+a+"到"+c;
		System.out.println(s);
		s.intern();
		list.add(s);
		move(i-1,b,a,c);
	}
	//1010
//	     8421
	private static void to2bit(int i,int j) {
		if(i/j==0) {
			System.out.print(i);;
		}else {
			to2bit(i/j,j);
			System.out.print(i%j);
		}
	}
	public static int fb(int i) {
		if(i==1 ) {
			return 1;
		}
		else if(  i==2 ) {
			return 1;
		}else {
			int i1=fb(i-1);
			int i2=fb(i-2);
			return i1+i2;
		}
	}

	private static int a(int i) {
		return i != 1 ? 1 : 0;
	}

	private static int b(int i) {
		return i == 3 ? 1 : 0;
	}

	private static int c(int i) {
		return i == 4 ? 1 : 0;
	}

	private static int d(int i) {
		return 1 - c(i);
	}
	
	
	
	public static void main2(String[] args) {
//      System.out.println(1);
//      for(int i=0;i<10;i++){
//          for(int j=0;j<10;j++){
//              if( (50+i)*4158 ==(3+j*10)*3564 ){
//                  System.out.println("x:"+i+"\ty:"+j);
//                  return ;
//              }
//          }
//      }
      
//      baiqianbaiji();
      
//      diophantus();
//      int i=5000;
//      System.out.println(jiecheng(i));;
      for(int i=0;i<30;i++){
//          System.out.println(i*6+1 +"\t"+(i*6+2)+"\t"+(i*6+3)+"\t"+(i*6+4)+"\t"+(i*6+5)+"\t"+(i*6+6));
          int size=6;
          for(int j=1;j<=size;j++){
              System.out.print("\t"+((i*size)+j));
          }
          System.out.println();
      }
  }

  private static BigDecimal jiecheng(int i) {
       if(i==1){
           return new BigDecimal(1);
       }
//jiecheng(i-1);
  return  jiecheng(i-1).multiply(new BigDecimal(i));
  }

  private static void diophantus() {
      // 过路的人！
      //这儿埋葬着丢番图。
      //请计算下列数目，
      //便可知他一生经过了多少寒暑。
      //他一生的六分之一是幸福的童年，
      //十二分之一是无忧无虑的少年。
      //再过去七分之一的年程，
      //他建立了幸福的家庭。
      //五年后儿子出生，
      //不料儿子竟先其父四年而终，
      //只活到父亲岁数的一半。
      //晚年丧子老人真可怜，
      //悲痛之中度过了风烛残年。
      //请你算一算，丢番图活到多大，
      //才和死神见面？”
      //请你算一算，丢番图到底活到多少岁？
      for(int x=0;x<110;x++){

//          x/6 //童年
//          x/12 //少年
//          x/7 //到结婚
//          +5  //生子
//          x-(x/6+x/12+x/7+5) 自己剩余的时间
//          x-(x/6+x/12+x/7+5)-4  儿子的时间
//          x-(x/6+x/12+x/7+5) //儿子活的时间
//                  2*(x-(x/6+x/12+x/7+5))=x //
              if( (x-(x/6+x/12+x/7+5)-4)*2==x  ){
                  System.out.println(x);
              }
//          x/6+x/12+x/7+5  //生子的年龄
          //x-(x/6+x/12+x/7+5) //这是他儿子的年龄

      }
  }

  private static void baiqianbaiji() {
      for(int x=0;x<20;x++){
          for(int y=0;y<33;y++){
              int z=100-x-y;
              if(z%3!=0){
                  continue;
              }
              if(5*x+3*y+ z/3 ==100 ){
                  System.out.println("x:"+x+"\ty:"+y+"\tz:"+z);
              }
//              if(5*x+3*y+){
//
//              }
          }
      }
  }
}
