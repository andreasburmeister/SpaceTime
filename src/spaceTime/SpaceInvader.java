
package spaceTime;

import processing.core.PApplet;
import processing.core.PVector;

public class SpaceInvader extends Target {
	
	protected SpaceInvader( PApplet papplet, float startX, float startY, float speed ) {
		parent = papplet;
		red = parent.random( 255 );
		green = parent.random( 255 );
		blue = parent.random( 255 );
		alpha = 255;
		size = 110;
		translation = new PVector( startX, startY, -5000 );
		this.speed = speed;
		points = (int)(speed*10);
	}
	
	protected void draw() {
		parent.noStroke();
		parent.fill( red, green, blue, alpha );
		
		parent.pushMatrix();
			parent.translate( translation.x-55, translation.y-40, translation.z );
			drawHalf();
			parent.translate( 110, 0, 0 );
			parent.scale( -1, 1, 1 );
			drawHalf();
		parent.popMatrix();
	}
	
	private void drawHalf() {
		drawBodyHalf();
		if ( parent.frameCount % 90 < 45 ) {
			drawArmsDownHalf();
		} else {
			drawArmsUpHalf();
		}
	}
	
	private void drawBodyHalf() {
		parent.beginShape();
			parent.vertex(  20,   0, 0 );
			parent.vertex(  20,  10, 0 );
			parent.vertex(  30,  10, 0 );
			parent.vertex(  30,   0, 0 );
			parent.vertex(  20,   0, 0 );
		parent.endShape();
		
		parent.beginShape();
			parent.vertex(  10,  50, 0 );
			parent.vertex(  10,  30, 0 );
			parent.vertex(  20,  30, 0 );
			parent.vertex(  20,  20, 0 );
			parent.vertex(  30,  20, 0 );
			parent.vertex(  30,  10, 0 );
			parent.vertex(  40,  10, 0 );
			parent.vertex(  40,  30, 0 );
			parent.vertex(  30,  30, 0 );
			parent.vertex(  30,  40, 0 );
			parent.vertex(  40,  40, 0 );
			parent.vertex(  40,  60, 0 );
			parent.vertex(  30,  60, 0 );
			parent.vertex(  30,  70, 0 );
			parent.vertex(  20,  70, 0 );
			parent.vertex(  20,  50, 0 );
			parent.vertex(  10,  50, 0 );
		parent.endShape();
		
		parent.beginShape();
			parent.vertex(  40,  20, 0 );
			parent.vertex(  55,  20, 0 );
			parent.vertex(  55,  60, 0 );
			parent.vertex(  40,  60, 0 );
			parent.vertex(  40,  20, 0 );
		parent.endShape();
	}
	
	private void drawArmsDownHalf() {
		parent.beginShape();
			parent.vertex(   0,  40, 0 );
			parent.vertex(  10,  40, 0 );
			parent.vertex(  10,  70, 0 );
			parent.vertex(   0,  70, 0 );
			parent.vertex(   0,  40, 0 );
		parent.endShape();
		
		parent.beginShape();
			parent.vertex(  30,  70, 0 );
			parent.vertex(  50,  70, 0 );
			parent.vertex(  50,  80, 0 );
			parent.vertex(  30,  80, 0 );
			parent.vertex(  30,  70, 0 );
		parent.endShape();
	}
	
	private void drawArmsUpHalf() {
		parent.beginShape();
			parent.vertex(   0,  40, 0 );
			parent.vertex(  10,  40, 0 );
			parent.vertex(  10,  10, 0 );
			parent.vertex(   0,  10, 0 );
			parent.vertex(   0,  40, 0 );
		parent.endShape();
		
		parent.beginShape();
			parent.vertex(  20,  70, 0 );
			parent.vertex(   0,  70, 0 );
			parent.vertex(   0,  80, 0 );
			parent.vertex(  20,  80, 0 );
			parent.vertex(  20,  70, 0 );
		parent.endShape();
	}
	
}
