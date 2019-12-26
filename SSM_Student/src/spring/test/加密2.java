package spring.test;
import java.util.Scanner;
public class 加密2 {
	public static void main(String[] args) {
		// TODO 锟皆讹拷锟斤拷锟缴的凤拷锟斤拷锟斤拷锟�
		Scanner reader=new Scanner(System.in);
		pass Tim=new pass();
		int count=0;
		String sourceString="xzk971013";
		String password="肖志锟斤拷"; //锟斤拷钥
		boolean panduan=true;
		String serect=Tim.encrypt(sourceString, password);
		System.out.print("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷钥锟侥伙拷锟结，");
		while(panduan) {
			System.out.print("锟斤拷锟斤拷锟斤拷钥锟斤拷");
		    String password0=reader.nextLine();
		    if(password0.compareTo(password)==0) {
		    String source=Tim.decrypt(serect, password0);
		    System.out.print("锟斤拷锟斤拷锟斤拷确锟斤拷锟斤拷锟斤拷为锟斤拷"+source);
		    panduan=false;
	    }
	    else {
	    	++count;
	    	if(count>=3) {
	    		System.out.print("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟皆匡拷锟斤拷薹锟斤拷锟斤拷锟斤拷锟斤拷耄★拷锟�");
	    		panduan=false;
	    	}
	    	else
	    	System.out.print("锟斤拷"+count+"锟轿达拷锟斤拷锟睫凤拷锟斤拷取锟斤拷锟斤拷\n锟斤拷锟斤拷锟斤拷");
	    }
		}
	}
}
