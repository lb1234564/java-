package 计算器;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class calculator extends JFrame implements ActionListener{ 
	private double result;//结果
	private String op="=";//当前运算符
	private boolean first=true;//标志用户按下的是否为第一个数字
	private final String [] pkey= {"C","7","4","1"};
	private JButton pkey1[]=new JButton[pkey.length];
	private final String [] nkey= {"+/-","Delete","8","9","5","6","2","3"};
	private JButton nkey1[]=new JButton[nkey.length];
	private final String [] skey= {"/","*","+","-"};
	private JButton skey1[]=new JButton[skey.length];
	private final String [] mkey= {" ","0",".","="};
	private JButton mkey1[]=new JButton[mkey.length];
	private JTextField Text=new JTextField("0");//计算结果文本框
	//构造函数
	public calculator() {
		super("计算器");
		//初始化计算器
		Text.setHorizontalAlignment(JTextField.LEFT);//文本框内容左对齐
		Text.setEditable(false);//不允许修改文本框内容
		Text.setBackground(Color.RED);//文本框颜色
		JPanel p=new JPanel();//新建一个p面板
		p.setLayout(new GridLayout(4,1,3,3));
		for (int i=0;i<pkey.length;i++){
			pkey1[i]=new JButton(pkey[i]);
			p.add(pkey1[i]);//将按钮添加到面板上
			pkey1[i].setForeground(Color.blue);
			pkey1[i].addActionListener(this);
		}
		JPanel s=new JPanel();//新建一个s面板
		s.setLayout(new GridLayout(4,1,3,3));
		for (int i=0;i<skey.length;i++){
			skey1[i]=new JButton(skey[i]);
			s.add(skey1[i]);//将按钮添加到面板上
			skey1[i].setForeground(Color.blue);
			skey1[i].addActionListener(this);
		}
		JPanel n=new JPanel();//新建一个n面板
		n.setLayout(new GridLayout(4,2,3,3));
		for (int i=0;i<nkey.length;i++){
			nkey1[i]=new JButton(nkey[i]);
			n.add(nkey1[i]);//将按钮添加到面板上
			nkey1[i].setForeground(Color.blue);
			nkey1[i].addActionListener(this);
		}
		JPanel m=new JPanel();//新建一个m面板
		m.setLayout(new GridLayout(1,4,3,3));
		for (int i=0;i<mkey.length;i++){
			mkey1[i]=new JButton(mkey[i]);
			m.add(mkey1[i]);//将按钮添加到面板上
			mkey1[i].setForeground(Color.blue);
			mkey1[i].addActionListener(this);
		}
		JPanel text=new JPanel();//建立一个text面板放文本框
		text.add(Text);//将文本框添加进text面板中
		getContentPane().setLayout(new BorderLayout(4,4));//每个组件的水平和垂直间隔为4个像素
		getContentPane().add(p,BorderLayout.WEST);
		getContentPane().add(n,BorderLayout.CENTER);
		getContentPane().add(s,BorderLayout.EAST);
		getContentPane().add(m,BorderLayout.SOUTH);
		getContentPane().add(text,BorderLayout.NORTH);
		setBackground(Color.green);//设置计算器背景颜色
		setLocation(500,300);//设置计算器位置
		setResizable(false);//不改变计算器的大小
		pack();//使计算器各组件大小合适
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
		if(s.equals(nkey[1])){
			Delete();//删除函数
		}
		else if (s.equals(pkey[0])) {//按下清零
			C();
		}
		else if("0123456789.".indexOf(s)>=0) {//按下数字键（包括小数点)
			num(s);
		}
		else {
			operate(s);//按下操作键（+，-，*，/）
		}
	}
	public void Delete() {
		String t=Text.getText();
		if(t.length()>0) {
			t=t.substring(0,t.length()-1);//截取字符串
			if(t.length()==0){
				Text.setText("0");
			}
			else {
				Text.setText(t);
			}
		}
	}
	public void C() {
		Text.setText("0");//初始化计算器
	}
	public void num(String a) {
		if (first)Text.setText(a);
		else if((a.equals("."))&&(Text.getText().indexOf(".")<0)) {//如果输入小数点并且之前没有小数点，直接在文本框中加入小数点
			Text.setText(Text.getText()+".");
		}
		else {//输入的不是小数点
			Text.setText(Text.getText()+a);
		}
		first = false;
		System.out.println(Text.getText());
	}
	public void operate(String a) {
		op=a;
		if(op.equals("/")) {//除法运算
			if(result==0.0)
				Text.setText("操作错误");
			else
				result/=number();
		}
		else if(op.equals("*")){//乘法运算
			result*=number();
		}
		else if(op.equals("+")){//加法运算
			result+=number();
		}
		else if(op.equals("-")){//减法运算
			result-=number();
		}
		else if(op.equals("+/-")){//正负号运算
			result=result*(-1);
		}
		else if(op.equals("=")) {
			result=number();
		}
		first=true;
	}
	//获取数字
	public double number() {
		double c=0;
		c=Double.valueOf(Text.getText()).doubleValue();
		return c;
	}
	public static void main(String[] args) {
		new calculator();
	}
}
