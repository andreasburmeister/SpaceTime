
package spaceTime;

import processing.core.PApplet;
import processing.core.PVector;

public class Projectile {
	
	private PApplet parent;
	
	protected Projectile( PApplet papplet, float rotationX, float rotationY, float rotationZ, PVector startPosition, float speed, int r, int g, int b ) {
		parent = papplet;
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
		translation = startPosition;
		this.speed = speed;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	private final float size = 3;
	
	protected float getSize() {
		return size;
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
	
	private PVector translation;
	
	protected PVector getTranslation() {
		return translation;
	}
	
	protected PVector getPosition() {
		parent.pushMatrix();
			parent.rotateX( rotationX );
			parent.rotateY( rotationY );
			parent.rotateZ( rotationZ );
			parent.translate( translation.x, translation.y, translation.z );
			PVector pPosition = new PVector( parent.modelX( 0, 0, 0 ), parent.modelY( 0, 0, 0 ), parent.modelZ( 0, 0, 0 ) );
		parent.popMatrix();
		
		return pPosition;
	}
	
	private float speed;
	
	protected void move() {
		translation.z -= speed;
	}
	
	private int r;
	private int g;
	private int b;
	
	protected void draw() {
		parent.noStroke();
		parent.fill( r, g, b );
		
		parent.pushMatrix();
			parent.rotateX( rotationX );
			parent.rotateY( rotationY );
			parent.rotateZ( rotationZ );
			parent.translate( translation.x, translation.y, translation.z );
			parent.sphere( size );
		parent.popMatrix();
	}
	
}
