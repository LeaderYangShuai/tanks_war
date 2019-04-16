package ̹�˴�ս5_0;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Member {

}
//����һ���ڵ�
class Node
{
	int x;
	int y;
	int direct;
	public Node(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;	
	}
	
}
//��¼�ļ���Ϣ����
class Recode
{
	private static int enNum=20;
	private static int Mylife=3;
	private static int allEnNum=0;
	
	
	//���ļ��лָ���¼
   private	static Vector<Node> nodes=new Vector<Node>();
	
	private static Vector<Enermy> etss=new Vector<Enermy>();
    
	
	private static FileReader fr=null;
	private static BufferedReader br=null;
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	 
	public static void ReduceEnNum()
	{
		enNum--;
	}
	public static void Myscore()
	{
		allEnNum++;
	}
	
	public static Vector <Node> getNodesAddEnNums()
	{
		try {
			fr=new FileReader("f:\\mytank.txt");
			br=new BufferedReader(fr);
			String n=" ";
		    n=br.readLine();
			allEnNum=Integer.parseInt(n);
		    while((n=br.readLine())!=null)
					{
						String []Recovery=n.split("");
				Node node=new Node(Integer.parseInt(Recovery[0]),Integer.parseInt(Recovery[1]),Integer.parseInt(Recovery[2]));
					
					nodes.add(node);
					}
			} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		return nodes;
		}		
	}
	//����ɼ���¼
        public static void keepRecoding() 
        {
        	try {
				fw=new FileWriter("f:\\mytank.txt");
				bw=new BufferedWriter(fw);
			    bw.write(allEnNum+"\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        }
      //������˵������͵��˵�̹�˵ķ����Լ�����
        public static void keepRecandEnermy()
        {
        	try {
				fw=new FileWriter("f:\\mytank.txt");
				bw=new BufferedWriter(fw);
				bw.write(allEnNum+"\r\n");
			//���浱ǰ�����ŵĵ��˵�̹�˵�����
     	for(int i=0;i<etss.size();i++)
		{	
     		//����
			Enermy et=etss.get(i);
			if(et.isLive)
			{
			String recode=et.x+" "+et.y+" "+et.direct;
					bw.write(recode+"\r\n");
			}
		}
				} catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			finally
				{
					try
					{
						bw.close();
						fw.close();
					} catch (IOException e)
                    {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
     	}
//���ļ��ж�ȡ��¼		
        public static void getRecoding() 
        {
        	try {
				fr=new FileReader("f:\\mytank.txt");
				br=new BufferedReader(fr);
                   
				String n =br.readLine();
				allEnNum=Integer.parseInt(n);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        }

	public static int getEnNum() {
		return enNum;
	}

	public static void setEnNum(int enNum) {
		Recode.enNum = enNum;
	}

	public static int getMylife() {
		return Mylife;
	}

	public static void setMylife(int mylife) {
		Mylife = mylife;
	}
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recode.allEnNum = allEnNum;
	}
	public static Vector<Enermy> getEts() {
		return etss;
	}
	public static void setEts(Vector<Enermy> ets) {
		etss = ets;
	}	
	
	
}

//ը����
class Bomb
{
	//����ը��������
	int x,y;
	//ը��������
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}else{
			this.isLive=false;
		}
	}
}
//�ӵ���
class Shot implements Runnable
{
	int x;
	int y;
	int direct;
	int speed=1;
	
	boolean isLive=true;
	
	public Shot(int x,int y,int direct)
    {		
		this.x=x;
		this.y=y;
		this.direct=direct;
    }
	public void run()
	{
		// TODO Auto-generated method stub
		while(true)
		{
			try{
				Thread.sleep(30);
			}catch(Exception e)
			{
				e.printStackTrace();				
			}
			//System.out.print("niho");
			switch(direct)
			{
			case 0://��
				y-=speed;
				break;
			case 1://��
				x+=speed;
				break;
			case 2://��
				y+=speed;
				break;
			case 3://��
				x-=speed;
				break;
			}
			//System.out.println("�ӵ�����x="+x+"y="+y);
			//�ж��ӵ��Ƿ�������Ե
			if(x<0||x>400||y<0||y>400)
			{
				this.isLive=false;
			}
		}
		
	}
	
}
//̹����
class Tank
{
    int x=0;//̹�˺�����
    int y=0;//̹��������
    int direct=0;
    int color=0;
    int speed=1;
    boolean isLive=true;
    Shot shot=null;
    public Tank(int x,int y)
    {
    	
        this.x=x;
        this.y=y;
    }
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
//���˵�̹�ˣ��ѵ��������߳�
class Enermy extends Tank implements Runnable
{
	// boolean isLive=true;
	 
	 int times=0;
	 Vector<Shot> ss=new Vector<Shot> ();
	 //Shot s=null;
	 //��������ӵ�Ӧ���ڸոմ�����̹�˺͵��˵�̹��������
     public  Enermy(int x,int y)
     {
         super(x,y);
     }
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			/*try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();				
			}*/
			switch(this.direct)
			{
			case 0://˵������̹����������
				for(int i=0;i<30;i++)
				{
					if(y>0)
					y-=speed;
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();				
					}
				}
				
				break;
			case 1://˵������̹����������
				for(int i=0;i<30;i++)
				{
					//��֤̹�˲������
					if(x<400)
					{
						x+=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();				
					}
				}
					
				break;
			case 2://˵������̹����������
				for(int i=0;i<30;i++)
				{
					//��֤̹�˲������
					if(y<300)
					{
						y+=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();				
					}
				}
					
				
				break;
			case 3://˵������̹����������
				for(int i=0;i<30;i++)
				{
					//��֤̹�˲����߽�
					if( x>0)
					{
						x-=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();				
					}
				}
				
				break;
			}
			this.direct=(int)(Math.random()*4);		
			//�жϵ��˵�̹���Ƿ�����
			if(this.isLive==false)
			{
				break;
			}
			this.times++;
			if(times%2==0)
			{
				if(isLive)
				{
					if(ss.size()<5)
					{
						Shot s=null;
						switch(direct)
						{
				    	 case 0:
				    		 //����һ���ӵ�
				    		 s=new Shot(x+10,y,0);
				    		 //���ӵ���������
				    		 ss.add(s);
				    		 break;
				    	 case 1:
				    		 //����һ���ӵ�
				    		 s=new Shot(x+30,y+10,1);
				    		 //���ӵ���������
				    		
				    		ss.add(s);
				    		 break;
				    	 case 2:
				    		 //����һ���ӵ�
				    		 s=new Shot(x+10,y+30,2);
				    		 //���ӵ���������
				    		
				    	ss.add(s);
				    		 break;
				    	 case 3:
				    		 //����һ���ӵ�
				    		 s=new Shot(x,y+10,3);
				    		 //���ӵ���������
				    		
				    		ss.add(s);
				    		 break;
				    	                                                        //������д
				    	 }
				    	
				    	//�����ӵ��߳�
				    	 Thread t=new Thread(s);
				    	 t.start();
					}
				}
				}
			}
		}

	}


       

//�ҵ�̹��
class Hero extends Tank
{
	//�ӵ�
	 Vector <Shot> ss=new Vector <Shot>();
	 Shot s=null;
     public Hero(int x,int y)
     {
         super(x,y);
     }
     //����
     public void shotEnermy()
     {
    	
    	 switch(this.direct)
    	 {
    	 case 0:
    		 //����һ���ӵ�
    		 s=new Shot(x+10,y,0);
    		 //���ӵ���������
    		 ss.add(s);
    		 break;
    	 case 1:
    		 //����һ���ӵ�
    		 s=new Shot(x+30,y+10,1);
    		 //���ӵ���������
    		
    		 ss.add(s);
    		 break;
    	 case 2:
    		 //����һ���ӵ�
    		 s=new Shot(x+10,y+30,2);
    		 //���ӵ���������
    		
    		 ss.add(s);
    		 break;
    	 case 3:
    		 //����һ���ӵ�
    		 s=new Shot(x,y+10,3);
    		 //���ӵ���������
    		
    		 ss.add(s);
    		 break;
    	                                                        //������д
    	 }
    	
    	//�����ӵ��߳�
    	 Thread t=new Thread(s);
    	 t.start();
     }
     public void moveUp()
	 {
		 y-=speed;
	 }
	 public void moveDown()
	 {
		 y+=speed;
	 }
	 
	 public void moveRight()
	 {
		 x+=speed;
	 }
	 public void moveLeft()
	 {
		 x-=speed;
	 }
}
