package net.yourzjf.corejava.beans;

public class 类初始化顺序 extends Sup {

	public static void main(String[] args) {
		new 类初始化顺序();
	}

	static Sample staticSamSub1 = new Sample("子类 静态成员staticSamSub1 初始化");

	类初始化顺序() {
		System.out.println("子类 TestInit默认构造函数被调用");
	}

	Sample sam1 = new Sample("子类 sam1成员 初始化");

	static Sample staticSamSub2 = new Sample("子类 静态成员staticSamSub2 初始化");

	static {
		System.out.println("子类 static块 初始化");
	}

	Sample sam2 = new Sample("子类 sam2成员 初始化");

}

class Sup {

	static {
		System.out.println("父类 static块1 初始化");
	}

	static Sample staticSam1 = new Sample("父类 静态成员staticSam1 初始化");

	Sample sam1 = new Sample("父类 sam1成员 初始化");

	static Sample staticSam2 = new Sample("父类 静态成员staticSam2 初始化");

	static {
		System.out.println("父类 static块2 初始化");
	}

	Sup() {
		System.out.println("父类 Test默认构造函数被调用");
	}

	Sample sam2 = new Sample("父类 sam2成员 初始化");

}

class Sample {

	Sample(String s) {
		System.out.println(s);
	}

	Sample() {
		System.out.println("Sample 默认构造函数被调用");
	}

}