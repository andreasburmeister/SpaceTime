
package spaceTime;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public abstract class Target {
	
	protected PApplet parent;
	
	protected abstract void draw();
	
	protected float red;
	protected float green;
	protected float blue;
	protected float alpha;
	
	protected float size;
	
	protected float getSize() {
		return size;
	}
	
	protected PVector translation;
	
	protected PVector getTranslation() {
		return translation;
	}
	
	protected PVector getPosition() {
		parent.pushMatrix();
			parent.translate( translation.x, translation.y, translation.z );
			PVector position = new PVector( parent.modelX( 0, 0, 0 ), parent.modelY( 0, 0, 0 ), parent.modelZ( 0, 0, 0 ) );
		parent.popMatrix();
		
		return position;
	}
	
	protected float speed;
	
	protected void move() {
		translation.z += speed;
	}
	
	protected int points;
	
	protected int getPoints() {
		return points;
	}
	
	protected int explosion = 0;
	
	protected boolean isExploding() {
		return ( explosion > 0 );
	}
	
	protected boolean isExploded() {
		return ( explosion == 20 );
	}
	
	protected void keepExploding() {
		if ( isExploding() ) {
			explode();
		}
	}
	
	protected void explode() {
		explosion++;
		size += 5;
		alpha -= 12.25;
		
		parent.textSize( 80 );
		parent.textAlign( PConstants.CENTER );
		parent.fill( red, green, blue );
		parent.text( points, translation.x, translation.y-size, translation.z );
	};
	
	protected boolean collidesWith( Projectile p ) {
		if ( PVector.dist( p.getPosition(), this.getPosition() ) < p.getSize()+size+10 ) {
			return true;
		} else {
			return false;
		}
	};
	
}
