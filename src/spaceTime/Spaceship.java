
package spaceTime;

import processing.core.PApplet;

public class Spaceship {
	
	private PApplet parent;
	
	protected Spaceship( PApplet papplet ) {
		parent = papplet;
	}
	
	private float rotationX = 0;
	private float rotationY = 0;
	private float rotationZ = 0;
	
	protected float getRotationX() {
		return rotationX;
	}
	protected float getRotationY() {
		return rotationY;
	}
	protected float getRotationZ() {
		return rotationZ;
	}
	
	protected void setRotationX( float x ) {
		rotationX = x;
	}
	protected void setRotationY( float y ) {
		rotationY = y;
	}
	protected void setRotationZ( float z ) {
		rotationZ = z;
	}
	
	protected void setRotation( float x, float y, float z ) {
		rotationX = x;
		rotationY = y;
		rotationZ = z;
	}
	
	protected void draw() {
		parent.noStroke();
		
		parent.pushMatrix();
			parent.rotateX( rotationX );
			parent.rotateY( rotationY );
			parent.rotateZ( rotationZ );
			
			drawTop();
			drawBottom();
			drawRear();
			drawEngines();
			drawWings();			
		parent.popMatrix();
	}
	
	private void drawTop() {
		parent.fill( 0, 127, 127 );
		
		parent.beginShape();
			parent.vertex(  -20,    0, -145 );//A
			parent.vertex(  -20,  -20, -100 );//D
			parent.vertex(  -45,    0, -100 );//C
			parent.vertex(  -20,  -20, -100 );//D
			parent.vertex(  -30,  -40,  -10 );//H
			parent.vertex(  -70,    0,  -10 );//G
			parent.vertex(  -30,  -40,  -10 );//H
			parent.vertex(  -45,  -50,  120 );//L
			parent.vertex(  -30,  -40,  -10 );//H
			parent.vertex(   30,  -40,  -10 );//I
			parent.vertex(   45,  -50,  120 );//M
			parent.vertex(   30,  -40,  -10 );//I
			parent.vertex(   70,    0,  -10 );//J
			parent.vertex(   30,  -40,  -10 );//I
			parent.vertex(   20,  -20, -100 );//E
			parent.vertex(   45,    0, -100 );//F
			parent.vertex(   20,  -20, -100 );//E
			parent.vertex(   20,    0, -145 );//B
			parent.vertex(   45,    0, -100 );//F
			parent.vertex(   70,    0,  -10 );//J
			parent.vertex(   80,    0,  120 );//N
			parent.vertex(   45,  -50,  120 );//M
			parent.vertex(  -45,  -50,  120 );//L
			parent.vertex(  -80,    0,  120 );//K
			parent.vertex(  -70,    0,  -10 );//G
			parent.vertex(  -45,    0, -100 );//C
			parent.vertex(  -20,    0, -145 );//A
			parent.vertex(   20,    0, -145 );//B
			parent.vertex(   20,  -20, -100 );//E
			parent.vertex(  -20,  -20, -100 );//D
			parent.vertex(   20,  -20, -100 );//E
			parent.vertex(   30,  -40,  -10 );//I
			parent.vertex(  -30,  -40,  -10 );//H
			parent.vertex(  -20,  -20, -100 );//D
			parent.vertex(  -20,    0, -145 );//A
		parent.endShape();
	}
	
	private void drawBottom() {
		parent.fill( 255, 0, 127 );
		
		parent.beginShape();
			parent.vertex(  -20,    0, -145 );//A
			parent.vertex(  -30,   15, -120 );//O
			parent.vertex(  -45,    0, -100 );//C
			parent.vertex(  -30,   15, -120 );//O
			parent.vertex(  -50,   30,  120 );//Q
			parent.vertex(  -80,    0,  120 );//K
			parent.vertex(  -50,   30,  120 );//Q
			parent.vertex(   50,   30,  120 );//R
			parent.vertex(   80,    0,  120 );//N
			parent.vertex(   50,   30,  120 );//R
			parent.vertex(   30,   15, -120 );//P
			parent.vertex(   45,    0, -100 );//F
			parent.vertex(   30,   15, -120 );//P
			parent.vertex(   20,    0, -145 );//B
			parent.vertex(   30,   15, -120 );//P
			parent.vertex(  -30,   15, -120 );//O
			parent.vertex(  -20,    0, -145 );//A
			parent.vertex(   20,    0, -145 );//B
			parent.vertex(   45,    0, -100 );//F
			parent.vertex(   70,    0,  -10 );//J
			parent.vertex(   80,    0,  120 );//N
			parent.vertex(   50,   30,  120 );//R
			parent.vertex(  -50,   30,  120 );//Q
			parent.vertex(  -80,    0,  120 );//K
			parent.vertex(  -70,    0,  -10 );//G
			parent.vertex(  -45,    0, -100 );//C
			parent.vertex(  -20,    0, -145 );//A
			parent.vertex(   20,    0, -145 );//B
			parent.vertex(  -20,    0, -145 );//A
			parent.vertex(  -30,   15, -120 );//O
			parent.vertex(   30,   15, -120 );//P
			parent.vertex(   50,   30,  120 );//R
			parent.vertex(  -50,   30,  120 );//Q
			parent.vertex(  -30,   15, -120 );//O
		parent.endShape();
	}
	
	private void drawRear() {
		parent.fill( 127, 255, 0 );
		
		parent.beginShape();
			parent.vertex(  -80,    0,  120 );//K
			parent.vertex(  -45,  -50,  120 );//L
			parent.vertex(   45,  -50,  120 );//M
			parent.vertex(   80,    0,  120 );//N
			parent.vertex(   50,   30,  120 );//R
			parent.vertex(  -50,   30,  120 );//Q
			parent.vertex(  -80,    0,  120 );//K
		parent.endShape();
	}
	
	private void drawEngines() {
		parent.fill( 127, 127, 127 );
		
		parent.pushMatrix();
			parent.translate( 30, -7, 0 );
			drawSingleEngine();//left
		parent.popMatrix();
		
		parent.pushMatrix();
			parent.translate( -30, -7, 0 );
			drawSingleEngine();//right
		parent.popMatrix();
	}
	
	private void drawSingleEngine() {
		parent.beginShape();
			parent.vertex(   10,   15,  121 );//a
			parent.vertex(   12,   20,  136 );//a*
			parent.vertex(   27,    0,  136 );//b*
			parent.vertex(   20,    0,  121 );//b
			parent.vertex(   10,   15,  121 );//a
		parent.endShape();
		parent.beginShape();
			parent.vertex(   20,    0,  121 );//b
			parent.vertex(   27,    0,  136 );//b*
			parent.vertex(   12,  -20,  136 );//c*
			parent.vertex(   10,  -15,  121 );//c
			parent.vertex(   20,    0,  121 );//b
		parent.endShape();
		parent.beginShape();
			parent.vertex(   10,  -15,  121 );//c
			parent.vertex(   12,  -20,  136 );//c*
			parent.vertex(  -12,  -20,  136 );//d*
			parent.vertex(  -10,  -15,  121 );//d
			parent.vertex(   10,  -15,  121 );//c
		parent.endShape();
		parent.beginShape();
			parent.vertex(  -10,  -15,  121 );//d
			parent.vertex(  -12,  -20,  136 );//d*
			parent.vertex(  -27,    0,  136 );//e*
			parent.vertex(  -20,    0,  121 );//e
			parent.vertex(  -10,  -15,  121 );//d
		parent.endShape();
		parent.beginShape();
			parent.vertex(  -20,    0,  121 );//e
			parent.vertex(  -27,    0,  136 );//e*
			parent.vertex(  -12,   20,  136 );//f*
			parent.vertex(  -10,   15,  121 );//f
			parent.vertex(  -20,    0,  121 );//e
		parent.endShape();
		parent.beginShape();
			parent.vertex(  -10,   15,  121 );//f
			parent.vertex(  -12,   20,  136 );//f*
			parent.vertex(   12,   20,  136 );//a*
			parent.vertex(   10,   15,  121 );//a
			parent.vertex(  -10,   15,  121 );//f
		parent.endShape();
		parent.beginShape();
			parent.vertex(   10,   15,  121 );//a
			parent.vertex(   20,    0,  121 );//b
			parent.vertex(   10,  -15,  121 );//c
			parent.vertex(  -10,  -15,  121 );//d
			parent.vertex(  -20,    0,  121 );//e
			parent.vertex(  -10,   15,  121 );//f
		parent.endShape();
	}
	
	private void drawWings() {
		parent.fill( 127, 127, 127 );
		
		drawSideWing();//left
		
		parent.pushMatrix();
			parent.scale( -1, 1, 1 );
			drawSideWing();//right
		parent.popMatrix();
		
		drawTopWing();//top
	}
	
	private void drawSideWing() {
		parent.beginShape();
			parent.vertex(   50,    0,  -70 );//o
			parent.vertex(   90,    0,   50 );//p
			parent.vertex(  115,    0,  110 );//q
			parent.vertex(   75,    0,  105 );//r
			parent.vertex(   50,    0,  -70 );//o
		parent.endShape();
	}
	
	private void drawTopWing() {
		parent.beginShape();
			parent.vertex(    0,  -40,   30 );//x
			parent.vertex(    0,  -70,  115 );//y
			parent.vertex(    0,  -50,  110 );//z
			parent.vertex(    0,  -40,   30 );//x
		parent.endShape();
	}
	
}
