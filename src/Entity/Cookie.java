package Entity;

import TileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Cookie extends MapObject
{

    public boolean hit;

    public boolean remove;
    
    public boolean isEnemy;

    public BufferedImage[] sprites;

    public BufferedImage[] hitSprites;


    public Cookie( TileMap tm, boolean right, boolean enemy )
    {
        super( tm );
        
        isEnemy = enemy;

        facingRight = right;

        moveSpeed = 3.8;
        if ( right )
        {
            dx = moveSpeed;
        }
        else
        {
            dx = -moveSpeed;
        }

        width = 30;
        height = 30;
        cwidth = 14;
        cheight = 14;

        // load sprites
        try
        {
            if(enemy)
            {
                BufferedImage spritesheet = ImageIO
                                .read( getClass().getResourceAsStream( "/Sprites/Player/fireball.gif" ) );

                            sprites = new BufferedImage[4];
                            for ( int i = 0; i < sprites.length; i++ )
                            {
                                sprites[i] = spritesheet.getSubimage( i * width, 0, width, height );
                            }

                            hitSprites = new BufferedImage[3];
                            for ( int i = 0; i < hitSprites.length; i++ )
                            {
                                hitSprites[i] = spritesheet.getSubimage( i * width, height, width, height );
                            }

                            animation = new Animation();
                            animation.setFrames( sprites );
                            animation.setDelay( 70 );
            }else
            {
                BufferedImage spritesheet = ImageIO
                                .read( getClass().getResourceAsStream( "/Sprites/Player/cookie.png" ) );

                            sprites = new BufferedImage[4];
                            for ( int i = 0; i < sprites.length; i++ )
                            {
                                sprites[i] = spritesheet.getSubimage( i * width, 0, width, height );
                            }

                            hitSprites = new BufferedImage[3];
                            for ( int i = 0; i < hitSprites.length; i++ )
                            {
                                hitSprites[i] = spritesheet.getSubimage( i * width, height, width, height );
                            }

                            animation = new Animation();
                            animation.setFrames( sprites );
                            animation.setDelay( 70 );
            }
            
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }


    public void setHit()
    {
        if ( hit )
        {
            return;
        }
        hit = true;
        animation.setFrames( hitSprites );
        animation.setDelay( 70 );
        dx = 0;

    }


    public boolean shouldRemove()
    {
        return remove;
    }


    public void update()
    {
        checkTileMapCollision();
        setPosition( xtemp, ytemp );

        if ( dx == 0 && !hit )
        {
            setHit();
        }

        animation.update();
        if ( hit && animation.hasPlayedOnce() )
        {
            remove = true;
        }
    }


    public void draw( Graphics2D g )
    {

        setMapPosition();

        super.draw( g );
    }
}
