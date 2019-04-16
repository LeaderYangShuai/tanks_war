package ̹�˴�ս5_0;



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
    	jm=new JMenu("�˵�");
    	jmi1=new JMenuItem("��ʼ��Ϸ");
    	jmi2=new JMenuItem("������Ϸ");
    	jmi4=new JMenuItem("������Ϸ");
    	jmi3=new JMenuItem("�˳���Ϸ");
    	
    	jmi1.addActionListener(this);
    	jmi1.setActionCommand("��ʼ��Ϸ");
    	jmi2.addActionListener(this);
    	jmi2.setActionCommand("������Ϸ");
    	jmi3.addActionListener(this);
    	jmi3.setActionCommand("�˳���Ϸ");
    	jmi4.addActionListener(this);
    	jmi4.setActionCommand("������Ϸ");
    	
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
        this.setTitle("̹�˴�ս");
        //���ô�С,������
        this.setSize(600,500);
        this.setLocation(100,200);
        //��ֹ�û��ı䴰���С
      //  this.setResizable(false);
        //����Ĭ�ϵĹرմ��ڲ���
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʾ
        this.setVisible(true);        
   
    }
	public void actionPerformed(ActionEvent arg0) {
       
		if(arg0.getActionCommand().equals("��ʼ��Ϸ"))
		{
			 this.remove(msp);
			 mp=new Mypanel("��ʼ��Ϸ");
	         mp.setBackground(Color.BLACK);
	         Thread t=new Thread(mp);
	         t.start();
	        this.add(mp);
	        this.addKeyListener(mp); 	        
	        this.setVisible(true);     
		}
		else if(arg0.getActionCommand().equals("������Ϸ"))
		{
			mp=new Mypanel("������Ϸ");
			mp.setBackground(Color.BLACK);
	         Thread t=new Thread(mp);
	         t.start();
			 this.remove(msp);
			 //mp.nodes=new Recode().getNodesAddEnNums();
	         
	        this.add(mp);
	        this.addKeyListener(mp); 
	        this.setVisible(true); 
	
		}
		else if(arg0.getActionCommand().equals("������Ϸ"))
		{		
			//Recode rd=new Recode();

			Recode.setEts(mp.ets);
			Recode.getNodesAddEnNums();
			System.exit(0);
		}
		else if(arg0.getActionCommand().equals("�˳���Ϸ"))
		{
			Recode.keepRecoding();
			
			System.exit(0);
		}
		
	}
}
//����һ����ʼ���


class Mystartpanel extends JPanel implements Runnable 
{
	int times=0;
	public void paint (Graphics g)
	{
		super.paint(g);
		if(times%2==0)
		{
	    g.setColor(Color.YELLOW);
	    
	    Font myfont=new Font("������κ",Font.BOLD,30);
	    g.setFont(myfont);
	    g.drawString("��һ��", 230, 230);	
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
    //������˵�̹����
  Vector<Enermy> ets=new Vector<Enermy>();
  Vector<Node> nodes=new Vector<Node>();
  Vector<Bomb> bombs=new Vector<Bomb>();
  
    int enSize=3;
    //��������ͼƬ
    Image image1=null;
    Image image2=null;
    Image image3=null;
    

    public Mypanel(String flag)
    {
   	 
        hero=new Hero(100,100);
        //������Ϸ��¼
        Recode.getRecoding();
        if(flag.equals("��ʼ��Ϸ"))
        {

        //��ʼ�����˵�̹����
        for(int i=0;i<enSize;i++)
        {
        	//����һ�����˵�̹�˶���
           Enermy et=new Enermy((i+1)*50,0);
            et.setDirect(2);
            et.setColor(2);
            //����
            ets.add(et);

            //�������˵�̹��
            Thread t=new Thread(et);
            t.start();

            //������̹�˴���һ���ӵ�
            Shot s=new Shot(et.x+10,et.y+30,2);
           
            if(et.ss.size()==0)
            {
                //���һ���ӵ�
                et.ss.add(s);
                //�����ӵ����߳�
                Thread t2=new Thread(s);
                t2.start();
            }
        }
        }
        
        else if(flag.equals("������Ϸ"))
            {
        	nodes=new Recode().getNodesAddEnNums();
        	for(int j=0;j<nodes.size();j++)
            {
        		Node node=nodes.get(j);
            	//����һ�����˵�̹�˶���
               Enermy et=new Enermy(node.x,node.y);
                et.setDirect(node.direct);
                et.setColor(2);
               //et.setEts(ets);
                //����
                ets.add(et);
                //�������˵�̹��
                Thread t=new Thread(et);
                t.start();

                //������̹�˴���һ���ӵ�
                Shot s=new Shot(et.x+10,et.y+30,2);
               
                if(et.ss.size()==0)
                {
                    //���һ���ӵ�
                    et.ss.add(s);
                    //�����ӵ����߳�
                    Thread t2=new Thread(s);
                    t2.start();
                }
        }
        }
        //��ʼ��ͼƬ
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.jpg"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.jpg"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.jpg"));
 
    }
    //дһ��չʾ̹����Ϣ�ķ���
    public void showInfo(Graphics g)
    {
    	 this.drawTank(120, 320, g, 0, 0);
         g.setColor(Color.DARK_GRAY);
        g.drawString("���˵�̹��"+Recode.getEnNum()+"", 110, 380);
         
         
         this.drawTank(200, 320, g, 0, 1);
         g.setColor(Color.DARK_GRAY);
         g.drawString("�ҵ�̹��"+Recode.getMylife(), 190, 380);

         this.drawTank(480, 50, g, 0, 1);
         g.setColor(Color.DARK_GRAY);
         g.drawString("�ܳɼ�"+Recode.getAllEnNum()+"", 470, 100);
         
         
    }
    //����JPanel��paint����
    public void paint(Graphics g)
    {
    	//Mypanel mi=new Mypanel();
    	 //System.out.print("FGNOAB");
       //�Զ�����paint���� //Graphics�ǻ�ͼ����Ҫ���ߣ���������Ϊ����
       super.paint(g);     
       g.fillRect(0, 0, 400,300);
       //�����Լ���̹��
       this.showInfo(g);
       if(hero.isLive)
       {
    	   this.drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
       }
       
       //��SSȡ��ÿ���ӵ�
       for(int i=0;i<hero.ss.size();i++)
       {
    	   Shot myshot=hero.ss.get(i);
    	   //�����ӵ�������һ���ӵ�
    	   if(myshot!=null&&myshot.isLive==true)
    	   {
    		   g.draw3DRect(myshot.x, myshot.y,1, 1, false);
    	   }
    	   if(myshot.isLive==false)
    	   {
    		   //��Ssɾ�����ӵ�
    		   hero.ss.remove(myshot);
    	   }
       }
       //��������̹��
       for(int i=0;i<ets.size();i++)
       {
    	 Enermy et=ets.get(i);
    	//�������˵�̹��
		
    	 if(et.isLive)
    	 {
    		 this.drawTank(et.getX(),et.getY(),g,et.getDirect(),0);
    		 //�ڻ������˵��ӵ�
    		 //System.out.print(et.ss.size());
    		 for(int j=0;j<et.ss.size();j++)
    		 {
    			 //ȡ���ӵ�
    			
    			 Shot enermyShot=et.ss.get(j);	 
    			
    			 if(enermyShot.isLive)
    			 {
    				 //�����ӵ�
    				g.draw3DRect(enermyShot.x, enermyShot.y, 1, 1, false);
    				
    			 }
    			 else
    			 {
    				//�������̹���������Ͱ���ɾ��
    				 et.ss.remove(enermyShot);
    			 }
   
    		 }
    	 }
       }
       
    }
    //�ж��ӵ��Ƿ������
    public void hitMe()
    {
    	//ȡ��ÿһ�����˵�̹��
		for(int i=0;i<this.ets.size();i++)
		{
			//ȡ��̹��
			Enermy et=ets.get(i);
	        if(et.isLive)
        	 {
			//ȡ��ÿ���ӵ�
		     	for(int j=0;j<et.ss.size();j++)
		    	{
				//ȡ���ӵ�
			    Shot enermyshot=et.ss.get(j);
				   if(et.isLive)
				   {
			      
			         this.hitTank(enermyshot , hero);
			    	}
			    }
	         } 
		}
    	
    }
   //�ж��ҵ��ӵ��Ƿ���е��˵�̹��
     public void hitEnermyTank()
     {
    		//�ж��Ƿ���е��˵�̹��
			for(int i=0;i<hero.ss.size();i++)
			{
				//ȡ���ӵ�
				 Shot myshot=hero.ss.get(i);
		        if(myshot.isLive)
	        	 {
				//ȡ��ÿ��̹�˲���֮�ж�
			     	for(int j=0;j<ets.size();j++)
			    	{
					//ȡ��̹��
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
   
  //дһ�������ж��ӵ��Ƿ���е��˵�̹��
    public boolean hitTank(Shot s,Tank et)
    {
        boolean b2=false;
    	//�ж�̹�˵ķ���
    	switch(et.direct)
    	{
    	    case 0:
    	    case 2:
    	    	if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
    	    	{
    	    		//���� �ӵ�����
    	    		s.isLive=false;
    	    		//����̹������
    	    		et.isLive=false;
    	    		b2=true;
    	    		ets.remove(et);
    	    		//����һ��ը��������vector
    	    		System.out.print(ets.size());

    	    	}
    	
    	    	break;
    	    case 1:
    	    case 3:
    	    	if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
    	    	{
    	    		//���� �ӵ�����
    	    		s.isLive=false;
    	    		//����̹������
    	    		et.isLive=false;
    	    		
    	    		ets.remove(et);
    	    		System.out.print(ets.size());
    
    	    	}
    	  
    	    	break;
    	}
		return b2;
    }
    //����̹�˵ĺ���
    public void drawTank(int x,int y,Graphics g,int direct,int type)
    {
        switch(type)//ʲô����̹��
        {
            case 0:
            g.setColor(Color.yellow);
            break;
            case 1:
            g.setColor(Color.CYAN);
            break;
        }
           
        
//�жϷ���
          switch(direct)
        {
            case 0://����
                g.fill3DRect(x,y,5,30,false);//��߾���
                g.fill3DRect(x+15,y,5,30,false);//�ұ߾���
                g.fillOval(x+5,y+10,10,10);//����Բ��
                g.fill3DRect(x+5,y+5,10,20,false);//�м����
                g.drawLine(x+10,y+15,x+10,y);//������
                //g.draw3DRect(x, y, this.hero.shot.getX(),this.hero.shot.getY(), false) ;
                break;
            case 2://����
            	g.fill3DRect(x,y,5,30,false);//��߾���
                g.fill3DRect(x+15,y,5,30,false);//�ұ߾���
                g.fillOval(x+5,y+10,10,10);//����Բ��
                g.fill3DRect(x+5,y+5,10,20,false);//�м����
                g.drawLine(x+10,y+15,x+10,y+30);//������
               
                break;
            case 3://����
            	g.fill3DRect(x,y,30,5,false);//��߾���
                g.fill3DRect(x,y+15,30,5,false);//�ұ߾���
                g.fillOval(x+10,y+5,10,10);//����Բ��
                g.fill3DRect(x+5,y+5,20,10,false);//�м����
                g.drawLine(x+15,y+10,x,y+10);//����
                
                break;
            case 1://����
            	g.fill3DRect(x,y,30,5,false);//��߾���
                g.fill3DRect(x,y+15,30,5,false);//�ұ߾���
                g.fillOval(x+10,y+5,10,10);//����Բ��
                g.fill3DRect(x+5,y+5,20,10,false);//�м����
                g.drawLine(x+15,y+10,x+30,y+10);//������
              
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
			//�ж��Ƿ���j��
			//����
			this.hero.shotEnermy();
		
		}
		
		   //����repaint���������ػ�����
		
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
		//ÿ��100��ȥ�ػ�
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
			//�ж��ӵ��Ƿ������
			this.hitMe();
			this.repaint();
	    }
    }
}
       




