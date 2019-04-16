package 坦克大战5_0;

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
//定义一个节点
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
//记录文件信息的类
class Recode
{
	private static int enNum=20;
	private static int Mylife=3;
	private static int allEnNum=0;
	
	
	//从文件中恢复记录
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
	//保存成绩记录
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
      //保存敌人的数量和敌人的坦克的方向以及坐标
        public static void keepRecandEnermy()
        {
        	try {
				fw=new FileWriter("f:\\mytank.txt");
				bw=new BufferedWriter(fw);
				bw.write(allEnNum+"\r\n");
			//保存当前还活着的敌人的坦克的坐标
     	for(int i=0;i<etss.size();i++)
		{	
     		//遍历
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
//从文件中读取记录		
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

//炸弹类
class Bomb
{
	//定义炸弹的坐标
	int x,y;
	//炸弹的生命
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
//子弹类
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
			case 0://上
				y-=speed;
				break;
			case 1://右
				x+=speed;
				break;
			case 2://下
				y+=speed;
				break;
			case 3://左
				x-=speed;
				break;
			}
			//System.out.println("子弹坐标x="+x+"y="+y);
			//判断子弹是否碰到边缘
			if(x<0||x>400||y<0||y>400)
			{
				this.isLive=false;
			}
		}
		
	}
	
}
//坦克类
class Tank
{
    int x=0;//坦克横坐标
    int y=0;//坦克纵坐标
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
//敌人的坦克，把敌人做成线程
class Enermy extends Tank implements Runnable
{
	// boolean isLive=true;
	 
	 int times=0;
	 Vector<Shot> ss=new Vector<Shot> ();
	 //Shot s=null;
	 //敌人添加子弹应当在刚刚创建的坦克和敌人的坦克死亡后
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
			case 0://说明敌人坦克正在向上
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
			case 1://说明敌人坦克正在向右
				for(int i=0;i<30;i++)
				{
					//保证坦克不出便捷
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
			case 2://说明敌人坦克正在向下
				for(int i=0;i<30;i++)
				{
					//保证坦克不出便捷
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
			case 3://说明敌人坦克正在向左
				for(int i=0;i<30;i++)
				{
					//保证坦克不出边界
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
			//判断敌人的坦克是否死亡
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
				    		 //创建一颗子弹
				    		 s=new Shot(x+10,y,0);
				    		 //把子弹加入向量
				    		 ss.add(s);
				    		 break;
				    	 case 1:
				    		 //创建一颗子弹
				    		 s=new Shot(x+30,y+10,1);
				    		 //把子弹加入向量
				    		
				    		ss.add(s);
				    		 break;
				    	 case 2:
				    		 //创建一颗子弹
				    		 s=new Shot(x+10,y+30,2);
				    		 //把子弹加入向量
				    		
				    	ss.add(s);
				    		 break;
				    	 case 3:
				    		 //创建一颗子弹
				    		 s=new Shot(x,y+10,3);
				    		 //把子弹加入向量
				    		
				    		ss.add(s);
				    		 break;
				    	                                                        //还不会写
				    	 }
				    	
				    	//启动子弹线程
				    	 Thread t=new Thread(s);
				    	 t.start();
					}
				}
				}
			}
		}

	}


       

//我的坦克
class Hero extends Tank
{
	//子弹
	 Vector <Shot> ss=new Vector <Shot>();
	 Shot s=null;
     public Hero(int x,int y)
     {
         super(x,y);
     }
     //开火
     public void shotEnermy()
     {
    	
    	 switch(this.direct)
    	 {
    	 case 0:
    		 //创建一颗子弹
    		 s=new Shot(x+10,y,0);
    		 //把子弹加入向量
    		 ss.add(s);
    		 break;
    	 case 1:
    		 //创建一颗子弹
    		 s=new Shot(x+30,y+10,1);
    		 //把子弹加入向量
    		
    		 ss.add(s);
    		 break;
    	 case 2:
    		 //创建一颗子弹
    		 s=new Shot(x+10,y+30,2);
    		 //把子弹加入向量
    		
    		 ss.add(s);
    		 break;
    	 case 3:
    		 //创建一颗子弹
    		 s=new Shot(x,y+10,3);
    		 //把子弹加入向量
    		
    		 ss.add(s);
    		 break;
    	                                                        //还不会写
    	 }
    	
    	//启动子弹线程
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
