
package spaceTime;

import processing.core.PApplet;
import processing.core.PVector;

public class Asteroid extends Target {
	
	protected Asteroid( PApplet papplet, float size, float startX, float startY, float speed ) {
		parent = papplet;
		red = parent.random( 255 );
		green = parent.random( 255 );
		blue = parent.random( 255 );
		alpha = 255;
		this.size = size;
		translation = new PVector( startX, startY, -5000 );
		this.speed = speed;
		points = (int)((Math.max( 0, 200-size )*speed)/50);
	}
	
	protected void draw() {
		parent.noStroke();
		parent.fill( red, green, blue, alpha );
		
		parent.pushMatrix();
			parent.translate( translation.x, translation.y, translation.z );
			parent.sphere( size );
		parent.popMatrix();
	}
	
}
