import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {
	public int map[][];
	public int bx;
	public int by;
	
	public Bricks(int row, int col)
	{
		map = new int[row][col];
		for(int i = 0; i<map.length; i++)
			for(int j = 0; j<map[0].length; j++)
			{
				map[i][j] = 1;
			}
		bx= 540/col;
		by= 150/row;

	}
	
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<map.length; i++)
			for(int j = 0; j<map[0].length; j++)
			{
				if(map[i][j] > 0)
				{
					g.setColor(Color.orange);
					g.fillRect(j * bx + 80, i * by + 50, bx, by);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * bx + 80, i * by + 50, bx, by);
				}
			}
		

	}
	public void setValue(int value, int row, int col)
	{
		map[row][col] = value;
	}
}
