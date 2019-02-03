	import java.applet.Applet;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	
	public class Paint extends Applet
	{
		Vector <Shape> shapes ;
		Button lineBtn,rectBtn,ovalBtn,freeBtn;
		Button blackBtn,redBtn,orangeBtn,yellowBtn,lightGreyBtn;
		Button undoBtn,resetBtn,eraserBtn,plusBtn,minusBtn;
		Checkbox solidBtn;
				
		static Color shapeColor=Color.black;
		protected static boolean solid = false;
		public static int mode =Shape.lineMode;
		
       
		public void init()
		{
		  shapes = new Vector(5,5);
		  
		  shapes.add(new Line());
          		  
		}
		public void start()
		{
		      this.addMouseListener( new MouseAdapter(){
			   public void mousePressed(MouseEvent ev)
			   {

				  shapes.lastElement().startXpoint=ev.getX();
				  shapes.lastElement().startYpoint=ev.getY();
				  shapes.lastElement().endXpoint=ev.getX();
				  shapes.lastElement().endYpoint=ev.getY();

			   }
			   
			   public void mouseReleased(MouseEvent event)
			   {

				 if(event.getX()>=getWidth())  shapes.lastElement().endXpoint=getWidth(); 
				 else  shapes.lastElement().endXpoint=event.getX();

				 if(event.getY()>=getHeight())  shapes.lastElement().endYpoint=getHeight();				
				 else  shapes.lastElement().endYpoint=event.getY();

				if( shapes.lastElement().startDrawing )
				{
					
				 switch(mode)
				 {
					 case Shape.lineMode:
						shapes.add(new Line());
				     break;
					 case Shape.rectMode:
					     shapes.add(new Rect());
					 break;
					 case Shape.ovalMode:
					     shapes.add(new Oval());
					 break;
					 case Shape.freeMode :
					     shapes.add(new FreeDrawing());
					 break;
					 case Shape.erasingMode:
				        shapes.add(new Eraser());
					 break;					 
				
				 }
				 shapes.lastElement().colour=shapeColor;
				  shapes.lastElement().filled=solid;
				}
				else{
					shapes.remove(shapes.size()-1);
				}
				    repaint();

			   }
			  }); 	 
			  
			  this.addMouseMotionListener(new MouseAdapter(){	  
				  public void mouseDragged(MouseEvent event)
				{
					   
				  shapes.lastElement().startDrawing=true;
				 shapes.lastElement().endXpoint=event.getX();
				 shapes.lastElement().endYpoint=event.getY();

				 repaint();
			   }  
			   
			   public void mouseMoved(MouseEvent event)
			   {
				    requestFocus();
				   if(mode==Shape.erasingMode)
				   {
					   shapes.lastElement().startXpoint= shapes.lastElement().endXpoint=event.getX();
					   shapes.lastElement().startYpoint= shapes.lastElement().endYpoint=event.getY();
					   repaint();
				   }
			   }
			   
		   });   
			
			lineBtn =new Button("/");
			lineBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				    shapes.remove(shapes.size()-1);  // remove the last element because it will be unused
				   	mode=Shape.lineMode;
					shapes.add(new Line());
					shapes.lastElement().colour=shapeColor;
					
			   }
			});
			add(lineBtn);

			rectBtn =new Button("[]");
			rectBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				   shapes.remove(shapes.size()-1);  // remove the last element because it will be unused
				   	mode=Shape.rectMode;
					shapes.add(new Rect());
					shapes.lastElement().colour=shapeColor;
					shapes.lastElement().filled=solid;
			   }
			});
			add(rectBtn);

			ovalBtn =new Button("O");
			ovalBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				   shapes.remove(shapes.size()-1);  // remove the last element because it will be unused
				   	mode=Shape.ovalMode;
					    shapes.add(new Oval());
						shapes.lastElement().colour=shapeColor;
						shapes.lastElement().filled=solid;
			   }
			});
			add(ovalBtn); 

			freeBtn =new Button("~");
			freeBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				    shapes.remove(shapes.size()-1);   // remove the last element because it will be unused
				   	mode=Shape.freeMode;
					shapes.add(new FreeDrawing());
					shapes.lastElement().colour=shapeColor;
			   }
			});
			add(freeBtn); 
            
			redBtn =new Button("  ");
			redBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				        shapeColor=Color.red;
						shapes.lastElement().colour=shapeColor;
			   }
			});
			redBtn.setBackground(Color.red);
			add(redBtn); 
			
			blackBtn =new Button("  ");
			blackBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				        shapeColor=Color.black;
						shapes.lastElement().colour=shapeColor;
			   }
			});
			blackBtn.setBackground(Color.black);
			add(blackBtn); 

			
			orangeBtn =new Button("  ");
			orangeBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				        shapeColor=Color.orange;
						shapes.lastElement().colour=shapeColor;
			   }
			});
			orangeBtn.setBackground(Color.orange);
			add(orangeBtn); 
			
			yellowBtn =new Button("  ");
			yellowBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				        shapeColor=Color.yellow;
						shapes.lastElement().colour=shapeColor;
			   }
			});
			yellowBtn.setBackground(Color.yellow);
			add(yellowBtn); 

			
			lightGreyBtn =new Button("  ");
			lightGreyBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				        shapeColor=Color.lightGray;
						shapes.lastElement().colour=shapeColor;
			   }
			});
			lightGreyBtn.setBackground(Color.lightGray);
			add(lightGreyBtn);
			
			undoBtn =new Button("Undo");
			undoBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				        if(shapes.size()!=1)  //1 element means that the applet is already empty , and this is the current object to paint
						{
						shapes.remove(shapes.size()-2); 
						repaint();
						}
			   }
			});
			add(undoBtn);

			resetBtn =new Button("Reset");
			resetBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
					shapes.removeAllElements();
					 switch(mode)
					 {
						 case Shape.lineMode:
							shapes.add(new Line());
						 break;
						 case Shape.rectMode:
							 shapes.add(new Rect());
						 break;
						 case Shape.ovalMode:
							 shapes.add(new Oval());
						 break;
						 case Shape.freeMode :
							 shapes.add(new FreeDrawing());
						 break;
						 case Shape.erasingMode:
						     shapes.add(new Eraser());
						 break;
					
					 }
					 shapes.lastElement().colour=shapeColor;
					  shapes.lastElement().filled=solid;
							
						repaint();
			   }
			});
			add(resetBtn);
			
			solidBtn =new Checkbox("Solid");
			solidBtn.addItemListener( new ItemListener()
			{
			   public void itemStateChanged(ItemEvent ev)
			   {
                 if(solidBtn.getState())
				 {
					 solid=true;
				 }
				 else
				 {
					solid=false;
				 }
				 shapes.lastElement().filled=solid;
			   }
			});
			add(solidBtn);			

			eraserBtn =new Button("Eraser");
			eraserBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				    shapes.remove(shapes.size()-1);  // remove the last element because it will be unused
				    mode=Shape.erasingMode;
					shapes.add(new Eraser());
			   }
			});
			add(eraserBtn);
			
			plusBtn =new Button("+");
			plusBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				    
				   if( mode==Shape.erasingMode ) shapes.lastElement().size+=1;
					
			   }
			});
			add(plusBtn);
			
			
			minusBtn =new Button("-");
			minusBtn.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ev)
			   {
				    
				   if( mode==Shape.erasingMode ) shapes.lastElement().size-=1;
					
			   }
			});
			add(minusBtn);
			}
		
		public void paint(Graphics g)
		{
			   for(int i=0;i<shapes.size();i++)
			   {
				   if( shapes.get(i).startDrawing || mode==Shape.erasingMode )
					{
						g.setColor(shapes.get(i).colour );
						shapes.get(i).draw(g,shapes.get(i).startXpoint, shapes.get(i).startYpoint, shapes.get(i).endXpoint, shapes.get(i).endYpoint);
			        }
				}
			
		}
		
		
		abstract class Shape
		{
			protected int startXpoint,startYpoint;
			protected int endXpoint,endYpoint;
			boolean startDrawing;
			public Color colour ;
			public boolean filled;
			int size; 
	         
			public static final int lineMode =0;
			public static final int rectMode =1;
			public static final int ovalMode =2;
			public static final int freeMode =3;
			public static final int erasingMode =4;
			
			
			public abstract void draw(Graphics graph,int x1, int y1, int x2, int y2);
			
			
		}
		
		class Line extends Shape
		{
			
			public void draw(Graphics graph,int x1, int y1, int x2, int y2)
			{	
				graph.drawLine(x1,y1,x2,y2);
			} 
		}
		
		class Rect extends Shape
		{
			private int height,width;
			public void draw(Graphics graph,int x1, int y1, int x2, int y2)
            {
				int temp=0;
				
				/*to check if the positions of x and y increased or decreased ,
                 because if it's decreased it won't paint it , due to the values of
                width and height will be zeros	*/
              if(y2<y1) 
			  { 
	            temp=y1;
				y1=y2;
				y2=temp;
			  }
			  
 			  if(x2<x1)
			  {
		       temp=x1;
			   x1=x2;
			   x2=temp;
			  }
			  width=x2-x1;
			  height=y2-y1;
			  
			  
			  if (filled)
			  {
				  graph.fillRect(x1,y1,width,height);
			  }else
 		     	 graph.drawRect(x1,y1,width,height);
			  } 
		}
		
		
		class Oval extends Shape
		{
			private int height,width;
			public void draw(Graphics graph,int x1, int y1, int x2, int y2)
            {
				int temp=0;
              if(y2<y1) 
			  { 
	            temp=y1;
				y1=y2;
				y2=temp;
			  }
			  
 			  if(x2<x1)
			  {
		       temp=x1;
			   x1=x2;
			   x2=temp;
			  }
			  width=x2-x1;
			  height=y2-y1;
			  if (filled)
			  {
				  graph.fillOval(x1,y1,width,height);
			  }else
 		     	 graph.drawOval(x1,y1,width,height);
			} 
		}

		class FreeDrawing extends Shape
		{
			private int oldX , oldY;
			Vector<Line> points;
			
			public FreeDrawing()
			 {
			    oldX=0;oldY=0;  
				points=new Vector();
			}
			
			public void draw(Graphics graph,int x1, int y1, int x2, int y2)
			{	
			  if(oldX == 0 && oldY == 0) {oldX=x1;oldY=y1;}
			    points.add(new Line());
				points.lastElement().startXpoint=oldX;
				points.lastElement().startYpoint=oldY;
				points.lastElement().endXpoint=x2;
				points.lastElement().endYpoint=y2;
				for(Line point : points)
				point.draw(graph,point.startXpoint,point.startYpoint,point.endXpoint,point.endYpoint);
			
			
				oldX=x2;
				oldY=y2;
			} 
			
		}
		
		
		class Eraser extends Shape
		{
			private int oldX , oldY;
			Vector<Rect> points;
			Rect movingEraser;
			
			
			public Eraser()
			 {
			    size=5;  
				points=new Vector();
			}
			
			public void draw(Graphics graph,int x1, int y1, int x2, int y2)
			{	
				
				  if(oldX == 0 && oldY == 0) {oldX=x1;oldY=y1;}
				   if(startDrawing)
				  {	
					points.add(new Rect());
					points.lastElement().startXpoint=oldX-size;
					points.lastElement().startYpoint=oldY-size;
					points.lastElement().endXpoint=x2+size;
					points.lastElement().endYpoint=y2+size;
				 
					points.lastElement().filled=true;
					graph.setColor(Color.white);
		         				  
						for(Rect point : points)
						point.draw(graph,point.startXpoint,point.startYpoint,point.endXpoint,point.endYpoint);
								
					
	                }				
				   else   
				    {
					    movingEraser=new Rect();
						movingEraser.startXpoint=oldX-size;
						movingEraser.startYpoint=oldY-size;
						movingEraser.endXpoint=x2+size;
						movingEraser.endYpoint=y2+size;
						movingEraser.filled=false;
						graph.setColor(Color.black);          				    
						movingEraser.draw(graph,movingEraser.startXpoint,movingEraser.startYpoint,movingEraser.endXpoint,movingEraser.endYpoint);
						   
					}
						oldX=x2;
						oldY=y2;
					
		   } 
			
	    }
	}
