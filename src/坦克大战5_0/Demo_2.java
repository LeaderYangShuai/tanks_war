package 坦克大战5_0;



import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.*;

public class Demo_2 extends JFrame implements ActionListener
{
      Mypanel mp=null;
      Mystartpanel msp=null;
      JMenuBar jmb=null;
      JMenu jm=null;
      
      JMenuItem jmi1=null;
      JMenuItem jmi2=null;
      JMenuItem jmi3=null;
      JMenuItem jmi4=null;
      
    public static void main( String[]  args)
    {
        Demo_2 demo_2=new Demo_2();
       
    }
    public Demo_2()
    {
    	jmb=new JMenuBar();
    	jm=new JMenu("菜单");
    	jmi1=new JMenuItem("开始游戏");
    	jmi2=new JMenuItem("保存游戏");
    	jmi4=new JMenuItem("继续游戏");
    	jmi3=new JMenuItem("退出游戏");
    	
    	jmi1.addActionListener(this);
    	jmi1.setActionCommand("开始游戏");
    	jmi2.addActionListener(this);
    	jmi2.setActionCommand("保存游戏");
    	jmi3.addActionListener(this);
    	jmi3.setActionCommand("退出游戏");
    	jmi4.addActionListener(this);
    	jmi4.setActionCommand("继续游戏");
    	
    	jm.add(jmi1);
    	jm.add(jmi2);
    	jm.add(jmi4);
    	jm.add(jmi3);
    	
    	jmb.add(jm);
   
    	this.setJMenuBar(jmb);
    	
        
        msp=new Mystartpanel();
        msp.setBackground(Color.BLACK);
        this.add(msp);
        Thread t1=new Thread(msp);
        t1.start();
        this.setTitle("坦克大战");
        //设置大小,按像素
        this.setSize(600,500);
        this.setLocation(100,200);
        //禁止用户改变窗体大小
      //  this.setResizable(false);
        //设置默认的关闭窗口操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示
        this.setVisible(true);        
   
    }
	public void actionPerformed(ActionEvent arg0) {
       
		if(arg0.getActionCommand().equals("开始游戏"))
		{
			 this.remove(msp);
			 mp=new Mypanel("开始游戏");
	         mp.setBackground(Color.BLACK);
	         Thread t=new Thread(mp);
	         t.start();
	        this.add(mp);
	        this.addKeyListener(mp); 	        
	        this.setVisible(true);     
		}
		else if(arg0.getActionCommand().equals("继续游戏"))
		{
			mp=new Mypanel("继续游戏");
			mp.setBackground(Color.BLACK);
	         Thread t=new Thread(mp);
	         t.start();
			 this.remove(msp);
			 //mp.nodes=new Recode().getNodesAddEnNums();
	         
	        this.add(mp);
	        this.addKeyListener(mp); 
	        this.setVisible(true); 
	
		}
		else if(arg0.getActionCommand().equals("保存游戏"))
		{		
			//Recode rd=new Recode();

			Recode.setEts(mp.ets);
			Recode.getNodesAddEnNums();
			System.exit(0);
		}
		else if(arg0.getActionCommand().equals("退出游戏"))
		{
			Recode.keepRecoding();
			
			System.exit(0);
		}
		
	}
}
//定义一个初始面板


class Mystartpanel extends JPanel implements Runnable 
{
	int times=0;
	public void paint (Graphics g)
	{
		super.paint(g);
		if(times%2==0)
		{
	    g.setColor(Color.YELLOW);
	    
	    Font myfont=new Font("华文新魏",Font.BOLD,30);
	    g.setFont(myfont);
	    g.drawString("第一关", 230, 230);	
		}
	}

	public void run()
	{
	
		while (true)
		{
			try
			{
				Thread.sleep(500);
				
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			this.repaint();
		}

	}
	
	
	
	
}

class Mypanel extends JPanel implements  KeyListener,Runnable
{


    Hero hero=null;
    //定义的人的坦克组
  Vector<Enermy> ets=new Vector<Enermy>();
  Vector<Node> nodes=new Vector<Node>();
  Vector<Bomb> bombs=new Vector<Bomb>();
  
    int enSize=3;
    //定义三张图片
    Image image1=null;
    Image image2=null;
    Image image3=null;
    

    public Mypanel(String flag)
    {
   	 
        hero=new Hero(100,100);
        //怀府游戏记录
        Recode.getRecoding();
        if(flag.equals("开始游戏"))
        {

        //初始化敌人的坦克呢
        for(int i=0;i<enSize;i++)
        {
        	//创建一辆敌人的坦克对象
           Enermy et=new Enermy((i+1)*50,0);
            et.setDirect(2);
            et.setColor(2);
            //加入
            ets.add(et);

            //启动敌人的坦克
            Thread t=new Thread(et);
            t.start();

            //给敌人坦克创建一颗子但
            Shot s=new Shot(et.x+10,et.y+30,2);
           
            if(et.ss.size()==0)
            {
                //添加一颗子弹
                et.ss.add(s);
                //启动子弹的线程
                Thread t2=new Thread(s);
                t2.start();
            }
        }
        }
        
        else if(flag.equals("继续游戏"))
            {
        	nodes=new Recode().getNodesAddEnNums();
        	for(int j=0;j<nodes.size();j++)
            {
        		Node node=nodes.get(j);
            	//创建一辆敌人的坦克对象
               Enermy et=new Enermy(node.x,node.y);
                et.setDirect(node.direct);
                et.setColor(2);
               //et.setEts(ets);
                //加入
                ets.add(et);
                //启动敌人的坦克
                Thread t=new Thread(et);
                t.start();

                //给敌人坦克创建一颗子但
                Shot s=new Shot(et.x+10,et.y+30,2);
               
                if(et.ss.size()==0)
                {
                    //添加一颗子弹
                    et.ss.add(s);
                    //启动子弹的线程
                    Thread t2=new Thread(s);
                    t2.start();
                }
        }
        }
        //初始化图片
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.jpg"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.jpg"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.jpg"));
 
    }
    //写一个展示坦克信息的方法
    public void showInfo(Graphics g)
    {
    	 this.drawTank(120, 320, g, 0, 0);
         g.setColor(Color.DARK_GRAY);
        g.drawString("敌人的坦克"+Recode.getEnNum()+"", 110, 380);
         
         
         this.drawTank(200, 320, g, 0, 1);
         g.setColor(Color.DARK_GRAY);
         g.drawString("我的坦克"+Recode.getMylife(), 190, 380);

         this.drawTank(480, 50, g, 0, 1);
         g.setColor(Color.DARK_GRAY);
         g.drawString("总成绩"+Recode.getAllEnNum()+"", 470, 100);
         
         
    }
    //覆盖JPanel的paint方法
    public void paint(Graphics g)
    {
    	//Mypanel mi=new Mypanel();
    	 //System.out.print("FGNOAB");
       //自动调用paint方法 //Graphics是画图的重要工具，你可以理解为画笔
       super.paint(g);     
       g.fillRect(0, 0, 400,300);
       //画出自己的坦克
       this.showInfo(g);
       if(hero.isLive)
       {
    	   this.drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
       }
       
       //从SS取出每颗子弹
       for(int i=0;i<hero.ss.size();i++)
       {
    	   Shot myshot=hero.ss.get(i);
    	   //画出子弹，画出一颗子弹
    	   if(myshot!=null&&myshot.isLive==true)
    	   {
    		   g.draw3DRect(myshot.x, myshot.y,1, 1, false);
    	   }
    	   if(myshot.isLive==false)
    	   {
    		   //从Ss删除该子弹
    		   hero.ss.remove(myshot);
    	   }
       }
       //画出敌人坦克
       for(int i=0;i<ets.size();i++)
       {
    	 Enermy et=ets.get(i);
    	//画出敌人的坦克
		
    	 if(et.isLive)
    	 {
    		 this.drawTank(et.getX(),et.getY(),g,et.getDirect(),0);
    		 //在画出敌人的子弹
    		 //System.out.print(et.ss.size());
    		 for(int j=0;j<et.ss.size();j++)
    		 {
    			 //取出子弹
    			
    			 Shot enermyShot=et.ss.get(j);	 
    			
    			 if(enermyShot.isLive)
    			 {
    				 //画出子弹
    				g.draw3DRect(enermyShot.x, enermyShot.y, 1, 1, false);
    				
    			 }
    			 else
    			 {
    				//如果敌人坦克死亡，就把他删除
    				 et.ss.remove(enermyShot);
    			 }
   
    		 }
    	 }
       }
       
    }
    //判断子弹是否击中我
    public void hitMe()
    {
    	//取出每一个敌人的坦克
		for(int i=0;i<this.ets.size();i++)
		{
			//取出坦克
			Enermy et=ets.get(i);
	        if(et.isLive)
        	 {
			//取出每个子弹
		     	for(int j=0;j<et.ss.size();j++)
		    	{
				//取出子弹
			    Shot enermyshot=et.ss.get(j);
				   if(et.isLive)
				   {
			      
			         this.hitTank(enermyshot , hero);
			    	}
			    }
	         } 
		}
    	
    }
   //判断我的子弹是否击中敌人的坦克
     public void hitEnermyTank()
     {
    		//判断是否击中敌人的坦克
			for(int i=0;i<hero.ss.size();i++)
			{
				//取出子弹
				 Shot myshot=hero.ss.get(i);
		        if(myshot.isLive)
	        	 {
				//取出每个坦克并与之判断
			     	for(int j=0;j<ets.size();j++)
			    	{
					//取出坦克
					Enermy et=ets.get(j);
					   if(et.isLive)
					   {				      
				         if(this.hitTank(myshot, et))
				         {
				        	 Recode.ReduceEnNum();
			    	    		Recode.Myscore();
				         }
				    	}
				    }
		         } 
			}
     }
   
  //写一个函数判断子弹是否击中敌人的坦克
    public boolean hitTank(Shot s,Tank et)
    {
        boolean b2=false;
    	//判断坦克的方向
    	switch(et.direct)
    	{
    	    case 0:
    	    case 2:
    	    	if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
    	    	{
    	    		//击中 子弹死亡
    	    		s.isLive=false;
    	    		//敌人坦克死亡
    	    		et.isLive=false;
    	    		b2=true;
    	    		ets.remove(et);
    	    		//创建一颗炸弹，放入vector
    	    		System.out.print(ets.size());

    	    	}
    	
    	    	break;
    	    case 1:
    	    case 3:
    	    	if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
    	    	{
    	    		//击中 子弹死亡
    	    		s.isLive=false;
    	    		//敌人坦克死亡
    	    		et.isLive=false;
    	    		
    	    		ets.remove(et);
    	    		System.out.print(ets.size());
    
    	    	}
    	  
    	    	break;
    	}
		return b2;
    }
    //画出坦克的函数
    public void drawTank(int x,int y,Graphics g,int direct,int type)
    {
        switch(type)//什么样的坦克
        {
            case 0:
            g.setColor(Color.yellow);
            break;
            case 1:
            g.setColor(Color.CYAN);
            break;
        }
           
        
//判断方向
          switch(direct)
        {
            case 0://向上
                g.fill3DRect(x,y,5,30,false);//左边矩形
                g.fill3DRect(x+15,y,5,30,false);//右边矩形
                g.fillOval(x+5,y+10,10,10);//画出圆形
                g.fill3DRect(x+5,y+5,10,20,false);//中间举行
                g.drawLine(x+10,y+15,x+10,y);//画出线
                //g.draw3DRect(x, y, this.hero.shot.getX(),this.hero.shot.getY(), false) ;
                break;
            case 2://向下
            	g.fill3DRect(x,y,5,30,false);//左边矩形
                g.fill3DRect(x+15,y,5,30,false);//右边矩形
                g.fillOval(x+5,y+10,10,10);//画出圆形
                g.fill3DRect(x+5,y+5,10,20,false);//中间举行
                g.drawLine(x+10,y+15,x+10,y+30);//画出线
               
                break;
            case 3://向左
            	g.fill3DRect(x,y,30,5,false);//左边矩形
                g.fill3DRect(x,y+15,30,5,false);//右边矩形
                g.fillOval(x+10,y+5,10,10);//画出圆形
                g.fill3DRect(x+5,y+5,20,10,false);//中间举行
                g.drawLine(x+15,y+10,x,y+10);//画出
                
                break;
            case 1://向右
            	g.fill3DRect(x,y,30,5,false);//左边矩形
                g.fill3DRect(x,y+15,30,5,false);//右边矩形
                g.fillOval(x+10,y+5,10,10);//画出圆形
                g.fill3DRect(x+5,y+5,20,10,false);//中间举行
                g.drawLine(x+15,y+10,x+30,y+10);//画出线
              
                break;
            	
        }
    }
    
	public void keyPressed(KeyEvent e) 
	{
		
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		   {
			this.hero.setDirect(2);
		      this.hero.moveDown();
		   }
		   else if(e.getKeyCode()==KeyEvent.VK_UP)
		   {
			  this. hero.setDirect(0);
			  this.hero.moveUp();

		   }
		   else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		   {
			   this.hero.setDirect(1);
			   this.hero.moveRight();

		   }
		   else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		   {
			  this. hero.setDirect(3);
			  this.hero.moveLeft();
		   }
		if(e.getKeyCode()==KeyEvent.VK_J)
		{
			//判断是否按下j贱
			//开火
			this.hero.shotEnermy();
		
		}
		
		   //调用repaint函数，来重画界面
		
		   this.repaint();
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void run()
	{
		//每隔100秒去重绘
		while(true)
		{
			try
			{
				Thread.sleep(100);
			}
			catch(Exception e)
			{
				e.printStackTrace();		
			}
			this.hitEnermyTank();
			//判断子弹是否击中我
			this.hitMe();
			this.repaint();
	    }
    }
}
       




