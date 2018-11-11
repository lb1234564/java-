package ������;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class calculator extends JFrame implements ActionListener{ 
	private double result;//���
	private String op="=";//��ǰ�����
	private boolean first=true;//��־�û����µ��Ƿ�Ϊ��һ������
	private final String [] pkey= {"C","7","4","1"};
	private JButton pkey1[]=new JButton[pkey.length];
	private final String [] nkey= {"+/-","Delete","8","9","5","6","2","3"};
	private JButton nkey1[]=new JButton[nkey.length];
	private final String [] skey= {"/","*","+","-"};
	private JButton skey1[]=new JButton[skey.length];
	private final String [] mkey= {" ","0",".","="};
	private JButton mkey1[]=new JButton[mkey.length];
	private JTextField Text=new JTextField("0");//�������ı���
	//���캯��
	public calculator() {
		super("������");
		//��ʼ��������
		Text.setHorizontalAlignment(JTextField.LEFT);//�ı������������
		Text.setEditable(false);//�������޸��ı�������
		Text.setBackground(Color.RED);//�ı�����ɫ
		JPanel p=new JPanel();//�½�һ��p���
		p.setLayout(new GridLayout(4,1,3,3));
		for (int i=0;i<pkey.length;i++){
			pkey1[i]=new JButton(pkey[i]);
			p.add(pkey1[i]);//����ť��ӵ������
			pkey1[i].setForeground(Color.blue);
			pkey1[i].addActionListener(this);
		}
		JPanel s=new JPanel();//�½�һ��s���
		s.setLayout(new GridLayout(4,1,3,3));
		for (int i=0;i<skey.length;i++){
			skey1[i]=new JButton(skey[i]);
			s.add(skey1[i]);//����ť��ӵ������
			skey1[i].setForeground(Color.blue);
			skey1[i].addActionListener(this);
		}
		JPanel n=new JPanel();//�½�һ��n���
		n.setLayout(new GridLayout(4,2,3,3));
		for (int i=0;i<nkey.length;i++){
			nkey1[i]=new JButton(nkey[i]);
			n.add(nkey1[i]);//����ť��ӵ������
			nkey1[i].setForeground(Color.blue);
			nkey1[i].addActionListener(this);
		}
		JPanel m=new JPanel();//�½�һ��m���
		m.setLayout(new GridLayout(1,4,3,3));
		for (int i=0;i<mkey.length;i++){
			mkey1[i]=new JButton(mkey[i]);
			m.add(mkey1[i]);//����ť��ӵ������
			mkey1[i].setForeground(Color.blue);
			mkey1[i].addActionListener(this);
		}
		JPanel text=new JPanel();//����һ��text�����ı���
		text.add(Text);//���ı�����ӽ�text�����
		getContentPane().setLayout(new BorderLayout(4,4));//ÿ�������ˮƽ�ʹ�ֱ���Ϊ4������
		getContentPane().add(p,BorderLayout.WEST);
		getContentPane().add(n,BorderLayout.CENTER);
		getContentPane().add(s,BorderLayout.EAST);
		getContentPane().add(m,BorderLayout.SOUTH);
		getContentPane().add(text,BorderLayout.NORTH);
		setBackground(Color.green);//���ü�����������ɫ
		setLocation(500,300);//���ü�����λ��
		setResizable(false);//���ı�������Ĵ�С
		pack();//ʹ�������������С����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر�
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
		if(s.equals(nkey[1])){
			Delete();//ɾ������
		}
		else if (s.equals(pkey[0])) {//��������
			C();
		}
		else if("0123456789.".indexOf(s)>=0) {//�������ּ�������С����)
			num(s);
		}
		else {
			operate(s);//���²�������+��-��*��/��
		}
	}
	public void Delete() {
		String t=Text.getText();
		if(t.length()>0) {
			t=t.substring(0,t.length()-1);//��ȡ�ַ���
			if(t.length()==0){
				Text.setText("0");
			}
			else {
				Text.setText(t);
			}
		}
	}
	public void C() {
		Text.setText("0");//��ʼ��������
	}
	public void num(String a) {
		if (first)Text.setText(a);
		else if((a.equals("."))&&(Text.getText().indexOf(".")<0)) {//�������С���㲢��֮ǰû��С���㣬ֱ�����ı����м���С����
			Text.setText(Text.getText()+".");
		}
		else {//����Ĳ���С����
			Text.setText(Text.getText()+a);
		}
		first = false;
		System.out.println(Text.getText());
	}
	public void operate(String a) {
		op=a;
		if(op.equals("/")) {//��������
			if(result==0.0)
				Text.setText("��������");
			else
				result/=number();
		}
		else if(op.equals("*")){//�˷�����
			result*=number();
		}
		else if(op.equals("+")){//�ӷ�����
			result+=number();
		}
		else if(op.equals("-")){//��������
			result-=number();
		}
		else if(op.equals("+/-")){//����������
			result=result*(-1);
		}
		else if(op.equals("=")) {
			result=number();
		}
		first=true;
	}
	//��ȡ����
	public double number() {
		double c=0;
		c=Double.valueOf(Text.getText()).doubleValue();
		return c;
	}
	public static void main(String[] args) {
		new calculator();
	}
}
