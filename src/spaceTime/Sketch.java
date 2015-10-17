
package spaceTime;

import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class Sketch extends PApplet {
	
	public static void main( String args[] ) {
		PApplet.main( new String[] { "--present", "spaceTime.Sketch" } );
	}
	
	public void settings() {
		fullScreen( P3D );
	}
	
	private PFont fontSmall;
	private PFont fontNormal;
	private PFont fontLarge;
	
	private int points;
	
	private Spaceship spaceship;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Target> targets;
	
	public void setup() {
		frameRate( 60 );
		background( 0 );
		noCursor();
		
		fontSmall = createFont( "Consolas", 20, true );
		fontNormal = createFont( "Consolas", 40, true );
		fontLarge = createFont( "Consolas", 80, true );
		
		points = 0;
		
		spaceship = new Spaceship( this );
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Target>();
	}
	
	public void draw() {
		background( 0 );
		lights();
		
		if ( paused ) {
			showPauseScreen();
		} else {
			pushMatrix();
				translate( width/2, height/2, z );
				
				handleSpaceship();
				handleAimingDevice();
				handleRapidFire();
				handleProjectiles();
				handleTargets();
				handleCollisions();
			popMatrix();
		}
		
		writeGameInfo();
	}
	
	public void mouseWheel( MouseEvent event ) {
		z += event.getCount()*100;
	}
	
	public void keyPressed( KeyEvent event ) {
		if ( event.getKey() == CODED ) {
			switch ( event.getKeyCode() ) {
				case CONTROL:
					if ( aimingDeviceUnlocked ) {
						aimingDevice = !aimingDevice;
					}
					break;
				case ALT:
					if ( rapidFireUnlocked ) {
						rapidFire = !rapidFire;
					}
			}
		} else {
			switch ( event.getKey() ) {
				case ' ':
					if ( !rapidFire && !paused ) {
						newProjectile();
					}
					break;
				case 'o':
					long time = java.lang.System.currentTimeMillis();
					saveFrame( "screenshots/SpaceTime-" + time + ".png" );
					break;
				case 'p':
					paused = !paused;
			}
		}
	}
	
	private boolean paused = false;
	
	private float z = 0;
	
	private int gun = 1;
	
	private void newProjectile() {
		int gunPositionX;
		if ( doubleFireUnlocked ) {
			gunPositionX = gun*20;
		} else {
			gunPositionX = 0;
		}
		
		int projectileSpeed = fasterBulletsUnlocked ? 20 : 10;
		
		int r, g, b;
		r = g = b = fasterBulletsUnlocked ? 127 : 255;
		
		Projectile p = new Projectile(
				this,
				spaceship.getRotationX(),
				spaceship.getRotationY(),
				spaceship.getRotationZ(),
				new PVector( gunPositionX, 0, -145 ),
				projectileSpeed,
				r, g, b
			);
		
		gun *= -1;
		projectiles.add( p );
		points = points > 0 ? points-1 : 0;
	}
	
	private void newAsteroid() {
		Asteroid a = new Asteroid(
				this,
				random( 5, 50 ),
				random( -width, width ),
				random( -height, height ),
				random( 5, 20 )
			);
		targets.add( a );
	}
	
	private void newSpaceInvader() {
		SpaceInvader s = new SpaceInvader(
				this,
				random( -width, width ),
				random( -height, height ),
				random( 5, 10 )
			);
		targets.add( s );
	}
	
	private void newPacmanGhost() {
		PacmanGhost p = new PacmanGhost (
				this,
				random( -width, width ),
				random( -height, height ),
				random( 5, 10 )
			);
		targets.add( p );
	}
	
	private void handleSpaceship() {
		spaceship.setRotationX( -cos(((float)(2*mouseY)/height)*HALF_PI) );
		spaceship.setRotationY( cos(((float)(2*mouseX)/width)*HALF_PI) );
		
		if ( keyPressed ) {
			switch ( key ) {
				case 'a':
					spaceship.setRotationZ( spaceship.getRotationZ() - PI/100 );
					break;
				case 'd':
					spaceship.setRotationZ( spaceship.getRotationZ() + PI/100 );
					break;
			}
		}
		
		spaceship.draw();
	}
	
	private int hitSpaceInvaders = 0;
	private int hitPacmanGhosts = 0;
	
	private void countSpaceInvaders() {
		hitSpaceInvaders++;
		switch ( hitSpaceInvaders ) {
			case 5:
				doubleFireUnlocked = true;
				newMessage( "Double fire unlocked!", "You can now shoot from two barrels.", 450 );
				break;
			case 10:
				aimingDeviceUnlocked = true;
				aimingDevice = true;
				newMessage( "Aiming device unlocked!", "Press [CTRL] to turn aiming device on/off.", 450 );
				break;
			case 15:
				fasterBulletsUnlocked = true;
				newMessage( "Faster bullets unlockled!", "You can now shoot with twice the speed.", 450 );
				break;
			case 20:
				rapidFireUnlocked = true;
				rapidFire = true;
				newMessage( "Rapid fire unlocked!", "Press [ALT] to turn rapid fire on/off.", 450 );
		}
	}
	
	private void countPacmanGhosts() {
		hitPacmanGhosts++;
	}
	
	private boolean doubleFireUnlocked = false;
	private boolean aimingDeviceUnlocked = false;
	private boolean fasterBulletsUnlocked = false;
	private boolean rapidFireUnlocked = false;	
	
	private boolean aimingDevice = false;
	private boolean rapidFire = false;
	
	private void handleAimingDevice() {
		if ( aimingDevice || ( aimingDeviceUnlocked && z <= -1000 ) ) {
			pushMatrix();
				rotateX( spaceship.getRotationX() );
				rotateY( spaceship.getRotationY() );
				rotateZ( spaceship.getRotationZ() );
				stroke( 0, 0, 255 );
				line( -10, 0, -500, 10, 0, -500 );
				line( 0, -10, -500, 0, 10, -500 );
				stroke( 255, 0, 0 );
				line( -10, 0, -1000, 10, 0, -1000 );
				line( 0, -10, -1000, 0, 10, -1000 );
			popMatrix();
		}
	}
	
	private void handleRapidFire() {
		if ( !rapidFire ) {
			return;
		}
		
		if ( keyPressed ) {
			if ( key == ' ' && ( frameCount % 3 == 0 ) ) {
				newProjectile();
			}
		}
	}
	
	private void handleProjectiles() {
		ArrayList<Projectile> oldProjectiles = new ArrayList<Projectile>();
		
		for ( Projectile p: projectiles ) {
			if ( p.getPosition().z < -5000 ) {
				oldProjectiles.add( p );
			} else {
				p.move();
				p.draw();
			}
		}
		
		projectiles.removeAll( oldProjectiles );
	}
	
	private void handleTargets() {
		if ( random( 1 ) < .05 ) {
			newAsteroid();
		}
		if ( frameCount > 1800 && random( 1 ) < .0025 ) {
			newSpaceInvader();
		}
		if ( frameCount > 3600 && random( 1 ) < .001 ) {
			newPacmanGhost();
		}
		
		ArrayList<Target> oldTargets = new ArrayList<Target>();
		
		for ( Target t: targets ) {
			if ( ( t.getTranslation().z > 2500 && !t.isExploding() ) || t.isExploded() ) {
				oldTargets.add( t );
			} else {
				t.move();
				t.draw();
				t.keepExploding();
			}
		}
		
		targets.removeAll( oldTargets );
	}
	
	private void handleCollisions() {
		for ( Projectile p: projectiles ) {
			for ( Target t: targets ) {
				if ( t.isExploding() ) {
					continue;
				}
				if ( t.collidesWith( p ) ) {
					t.explode();
					points += t.getPoints();
					if ( t.getClass() == SpaceInvader.class ) {
						countSpaceInvaders();
					} else if ( t.getClass() == PacmanGhost.class ) {
						countPacmanGhosts();
					}
				}
			}	
		}
	}
	
	private String messageHeading;
	private String messageDetail;
	private int messageTime;
	
	private void newMessage( String messageHeading, String messageDetail, int time ) {
		this.messageHeading = messageHeading;
		this.messageDetail = messageDetail;
		messageTime = time;
	}
	
	private void writeGameInfo() {
		textFont( fontSmall );
		textAlign( LEFT );
		fill( 95, 95, 95 );
		text( "[MOUSE-Y] - rotate x-axis\n"
				+ "[MOUSE-X] - rotate y-axis\n"
				+ "[A], [D]  - rotate z-axis\n"
				+ "[SCROLL]  - zoom in/out\n\n"
				+ "[CTRL]    - toggle aiming device\n"
				+ "[ALT]     - toggle rapid fire\n"
				+ "[SPACE]   - shoot projectile\n\n"
				+ "[O]       - take screenshot\n\n"
				+ "[P]       - pause/unpause game\n"
				+ "[ESCAPE]  - quit",
				30, 50, 0
			);
		
		textFont( fontNormal );
		textAlign( RIGHT );
		fill( 0, 0, 255 );
		String text = "";
		if ( aimingDeviceUnlocked ) {
			text += "aiming device: " + boolToOnOff( aimingDevice );
		}
		if ( rapidFireUnlocked ) {
			text += "\nrapid fire: " + boolToOnOff( rapidFire );
		}
		text( text, width-30, 50, 0 );
		
		textAlign( LEFT );
		fill( 0, 255, 0 );
		text( Math.round( frameRate ) + " FPS", 30, height-30, 0 );
		
		textFont( fontLarge );
		textAlign( RIGHT );
		fill( 255, 0, 0 );
		text( points + " PTS", width-30, height-30, 0 );
		
		if ( messageTime > 0 ) {
			textAlign( CENTER );
			fill( 255, 255, 255 );
			textFont( fontNormal );
			text( messageHeading, width/2, 50, 0 );
			textFont( fontSmall );
			text( messageDetail, width/2, 80, 0 );
			if ( !paused ) {
				messageTime--;
			}
		}
	}
	
	private void showPauseScreen() {
		fill( 127, 127, 127, 127 );
		rect( 0, height/2-150, width, 300 );
		
		textFont( fontLarge );
		textAlign( CENTER );
		fill( 255, 255, 255 );
		text( "GAME PAUSED", width/2, height/2 );
		
		textFont( fontSmall );
		text( "press 'P' to continue", width/2, height/2+60 );
	}
	
	private String boolToOnOff( boolean variable ) {
		if ( variable ) {
			return "ON ";
		} else {
			return "OFF";
		}
	}
	
}
