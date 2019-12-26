package spring.test;
import java.util.Scanner;
public class 加密 {
	public static void main(String[] args) {
		// TODO 锟皆讹拷锟斤拷锟缴的凤拷锟斤拷锟斤拷锟�
		Scanner reader=new Scanner(System.in);
		System.out.print("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷耄�");
		String sourceString=reader.nextLine();
		pass Tom=new pass();
		System.out.println("锟斤拷锟斤拷锟斤拷锟诫："+sourceString);
		System.out.print("确锟斤拷锟斤拷锟斤拷锟斤拷钥锟斤拷");
		String password=reader.nextLine();
		String serect=Tom.encrypt(sourceString, password);
		System.out.println("锟窖硷拷锟斤拷锟斤拷锟侥ｏ拷"+serect);
		System.out.print("锟斤拷锟斤拷锟斤拷锟斤拷锟皆匡拷锟�");
		password=reader.nextLine();
		String source=Tom.decrypt(serect, password);
		System.out.println("锟斤拷锟诫："+source);
	}
}
class pass {
	        /******锟斤拷锟斤拷锟姐法******/
	 	String encrypt (String sourceString, String password) {
		char p[]=password.toCharArray();
		int lenp=p.length;
		char sou[]=sourceString.toCharArray();
		int lens=sou.length;
		for(int i=0; i<lens; i++) {
			int mima=sou[i]+p[i%lenp]; //锟斤拷锟杰癸拷锟斤拷
			sou[i]=(char)mima;
		}
		return new String(sou); //锟斤拷锟斤拷锟窖硷拷锟斤拷锟斤拷锟斤拷
	}
	 	String decrypt (String sourceString, String password) {
	 		char p[]=password.toCharArray();
			int lenp=p.length;
			char sou[]=sourceString.toCharArray();
			int lens=sou.length;
			for(int i=0; i<lens; i++) {
				int mima=sou[i]-p[i%lenp]; //锟斤拷锟杰癸拷锟斤拷
				sou[i]=(char)mima;
			}
			return new String(sou); //锟斤拷锟斤拷锟斤拷锟斤拷
	 	}
}
