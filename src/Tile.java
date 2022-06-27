import java.awt.Color;

public class Tile {
	int value;
	Color color;
	
	public Tile() {
		value = 0;
	}
	
	public Tile(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setColor()
    {
        if ( this.getValue() == 2 )
        {
        	color = new Color( 238, 228, 218 );
        }
        else if ( this.getValue() == 4 )
        {
        	color = new Color( 237, 224, 200 );
        }
        else if ( this.getValue() == 8 )
        {
        	color = new Color( 242, 177, 121 );
        }
        else if ( this.getValue() == 16 )
        {
        	color = new Color( 245, 149, 99 );
        }
        else if ( this.getValue() == 32 )
        {
        	color = new Color( 246, 124, 95 );
        }
        else if ( this.getValue() == 64 )
        {
        	color = new Color( 246, 94, 59 );
        }
        else if ( this.getValue() == 128 )
        {
        	color = new Color( 237, 207, 114 );
        }
        else if ( this.getValue() == 256 )
        {
        	color = new Color( 237, 204, 97 );
        }
        else if ( this.getValue() == 512 )
        {
        	color = new Color( 237, 200, 80 );
        }
        else if ( this.getValue() == 1024 )
        {
        	color = new Color( 237, 197, 63 );
        }
        else
        {
        	color = new Color( 237, 194, 46 );
        }
    }
	
	public Color getColor() {
		return color;
	}
}
