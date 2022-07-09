package com.crm.practice;

public class HowToFindIntAndString {

	public static void main(String[] args) {
String s="1a3";
String num="";
for(int i=0; i<s.length();i++) {
	char ch=s.charAt(i);
	if(Character.isDigit(ch)) {
		num=num+s.charAt(i);//1
	}
	else {
		break;
	}
}
int columnNumber=0;
String columnName=null;
if(num.equalsIgnoreCase(s))
{
	columnNumber=Integer.parseInt(num);
}
else {
	columnName=s;
}
System.out.println(columnName);
System.out.println(columnNumber);
	}

}
