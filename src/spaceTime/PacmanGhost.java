
package spaceTime;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class PacmanGhost extends Target {
	
	protected PacmanGhost( PApplet papplet, float startX, float startY, float speed ) {
		parent = papplet;
		red = parent.random( 255 );
		green = parent.random( 255 );
		blue = parent.random( 255 );
		alpha = 255;
		size = 140*sizeFactor;
		translation = new PVector( startX, startY, -5000 );
		this.speed = speed;
		points = (int)(speed*25);
	}
	
	private float sizeFactor = .5f;
	
	protected void draw() {
		parent.noStroke();
		
		parent.pushMatrix();
			parent.translate( translation.x-70, translation.y-70, translation.z );
			parent.scale( sizeFactor, sizeFactor, 0 );
			parent.translate( size, size, 0 );
			drawBody();
			if ( parent.frameCount % 90 < 45 ) {
				parent.translate( 140, 0, 0 );
				parent.scale( -1, 1, 1 );
			}
			drawEyes();
		parent.popMatrix();
	}
	
	private void drawBody() {
		drawHalfBody();
		parent.translate( 140, 0, 0 );
		parent.scale( -1, 1, 1 );
		drawHalfBody();
	}
	
	private void drawHalfBody() {
		parent.fill( red, green, blue, alpha );
		
		parent.beginShape();
			parent.vertex(   0,  60, 0 );
			parent.vertex(  10,  60, 0 );
			parent.vertex(  10,  30, 0 );
			parent.vertex(  20,  30, 0 );
			parent.vertex(  20,  20, 0 );
			parent.vertex(  30,  20, 0 );
			parent.vertex(  30,  10, 0 );
			parent.vertex(  50,  10, 0 );
			parent.vertex(  50,   0, 0 );
			parent.vertex(  70,   0, 0 );
			parent.vertex(  70, 120, 0 );
			parent.vertex(  60, 120, 0 );
			parent.vertex(  60, 140, 0 );
			parent.vertex(  40, 140, 0 );
			parent.vertex(  40, 130, 0 );
			parent.vertex(  30, 130, 0 );
			parent.vertex(  30, 120, 0 );
			parent.vertex(  20, 120, 0 );
			parent.vertex(  20, 130, 0 );
			parent.vertex(  10, 130, 0 );
			parent.vertex(  10, 140, 0 );
			parent.vertex(   0, 140, 0 );
			parent.vertex(   0,  60, 0 );
		parent.endShape();
	}
	
	private void drawEyes() {
		drawSingleEye();
		parent.translate( 60, 0, 0 );
		drawSingleEye();
	}
	
	private void drawSingleEye() {
		parent.fill( 255, 255, 255 );
		parent.beginShape();
			parent.vertex(  10,  40, 0 );
			parent.vertex(  20,  40, 0 );
			parent.vertex(  20,  30, 0 );
			parent.vertex(  40,  30, 0 );
			parent.vertex(  40,  40, 0 );
			parent.vertex(  50,  40, 0 );
			parent.vertex(  50,  70, 0 );
			parent.vertex(  40,  70, 0 );
			parent.vertex(  40,  80, 0 );
			parent.vertex(  20,  80, 0 );
			parent.vertex(  20,  70, 0 );
			parent.vertex(  30,  70, 0 );
			parent.vertex(  30,  50, 0 );
			parent.vertex(  10,  50, 0 );
			parent.vertex(  10,  40, 0 );
		parent.endShape();
		
		parent.fill( 0, 0, 127 );
		parent.beginShape();
			parent.vertex(  10,  50, 0 );
			parent.vertex(  30,  50, 0 );
			parent.vertex(  30,  70, 0 );
			parent.vertex(  10,  70, 0 );
			parent.vertex(  10,  50, 0 );
		parent.endShape();
	}
	
	protected void explode() {
		explosion++;
		alpha -= 12.25;
		
		parent.textSize( 80 );
		parent.textAlign( PConstants.CENTER );
		parent.fill( red, green, blue );
		parent.text( points, translation.x, translation.y-size, translation.z );
	};
	
}
