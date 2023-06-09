package SuzumeGUI;

import java.awt.*;
import java.util.AbstractMap;
import java.util.EnumMap;
import javax.swing.*;


public class SuzumeComponent  extends JComponent implements FloorListener{
    
    private final static int SQUARE_SIZE = 24;
    private final static int CHARACTER_ADJUSTMENT_FOR_PAINT = 11;
    private final static int SQUARE_MIDDLE = SQUARE_SIZE/2;
    
    // Defining painting parameters
    private final static int PAINT_PARAMETER_13 = 13;
    private final static int PAINT_PARAMETER_15 = 15;
    private final static int PAINT_PARAMETER_17 = 17;
    private final static int PAINT_PARAMETER_18 = 18;
    private final static int PAINT_PARAMETER_19 = 19;
    private final static int PAINT_PARAMETER_20 = 20;
    private final static int PAINT_PARAMETER_24 = 24;
    
    private final SuzumeFloor floor;
    private final AbstractMap<FloorTile, Color> colorMap;
    
    public SuzumeComponent(SuzumeFloor floor) {
	this.floor = floor;

	colorMap = new EnumMap<>(FloorTile.class);
	colorMap.put(FloorTile.EMPTY_SPACES, Color.GREEN);
	colorMap.put(FloorTile.OBSTACLES, Color.BLACK);
	colorMap.put(FloorTile.STATIONS, Color.YELLOW);
        colorMap.put(FloorTile.FINAL_DESTINATION, Color.RED);
    }

    // This method is static since each square has the same size.
    public static int getSquareSize() {
	return SQUARE_SIZE;
    }

    // This method is static since each square has the same size.
    public static int getSquareMiddle() {
	return SQUARE_MIDDLE;
    }

    
    public Dimension getPreferredSize() {
	super.getPreferredSize();
	return new Dimension(this.floor.getWidth() * SQUARE_SIZE, this.floor.getHeight() * SQUARE_SIZE);
    }

    
    public void floorChanged() {
	repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int rowIndex = 0; rowIndex < floor.getHeight(); rowIndex++) {
	    for (int colIndex = 0; colIndex < floor.getWidth(); colIndex++) {
		g2d.setColor(colorMap.get(this.floor.getFloorTile(rowIndex, colIndex)));
		if(floor.getFloorTile(rowIndex, colIndex)==FloorTile.STATIONS){
		    paintStation(rowIndex, colIndex, g2d);
		}
		else if(floor.getFloorTile(rowIndex, colIndex)==FloorTile.OBSTACLES){
		    paintObstacle(rowIndex, colIndex, g2d);
		}
		else if(floor.getFloorTile(rowIndex, colIndex)==FloorTile.FINAL_DESTINATION){
		    paintFinalDestination(rowIndex, colIndex, g2d);
		}
		else{
		    paintEmptySpaces(rowIndex, colIndex, g2d);
		}
	    }
	}
	// Paint player:
	paintPlayer(floor.getPlayer(), g2d);
    }

    //station
//    private void paintStation(int rowIndex, int colIndex, Graphics g2d){
//	g2d.setColor(Color.lightGray);
//	g2d.fillRect(colIndex * SQUARE_SIZE, rowIndex * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
//	g2d.setColor(Color.BLUE);
//	g2d.drawLine(colIndex* SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+10, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+10);
//	g2d.drawLine(colIndex* SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE);
//	g2d.drawLine(colIndex* SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10);
//	g2d.drawLine(colIndex* SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+SQUARE_SIZE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
//
//	g2d.drawLine(colIndex* SQUARE_SIZE+10, rowIndex*SQUARE_SIZE+1, colIndex*SQUARE_SIZE+10, rowIndex*SQUARE_SIZE+10);
//	g2d.drawLine(colIndex* SQUARE_SIZE+SQUARE_MIDDLE+10, rowIndex*SQUARE_SIZE+1, colIndex*SQUARE_SIZE+SQUARE_MIDDLE+10, rowIndex*SQUARE_SIZE+10);
//
//	g2d.drawLine(colIndex* SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+10, colIndex*SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE);
//	g2d.drawLine(colIndex* SQUARE_SIZE+SQUARE_MIDDLE+1, rowIndex*SQUARE_SIZE+10, colIndex*SQUARE_SIZE+SQUARE_MIDDLE+1, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE);
//
//	g2d.drawLine(colIndex* SQUARE_SIZE+10, rowIndex*SQUARE_SIZE+1+SQUARE_MIDDLE, colIndex*SQUARE_SIZE+10, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10);
//	g2d.drawLine(colIndex* SQUARE_SIZE+SQUARE_MIDDLE+10, rowIndex*SQUARE_SIZE+1+SQUARE_MIDDLE, colIndex*SQUARE_SIZE+SQUARE_MIDDLE+10, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10);
//
//	g2d.drawLine(colIndex* SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10, colIndex*SQUARE_SIZE+1, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
//	g2d.drawLine(colIndex* SQUARE_SIZE+SQUARE_MIDDLE+1, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10, colIndex*SQUARE_SIZE+SQUARE_MIDDLE+1, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
//    }

    private void paintObstacle(int rowIndex, int colIndex, Graphics g2d){
	g2d.fillRect(colIndex * SQUARE_SIZE, rowIndex * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	g2d.setColor(Color.DARK_GRAY);
	g2d.drawLine(colIndex* SQUARE_SIZE, rowIndex*SQUARE_SIZE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE);
	g2d.drawLine(colIndex* SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
	g2d.drawLine(colIndex* SQUARE_SIZE, rowIndex*SQUARE_SIZE, colIndex*SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
	g2d.drawLine(colIndex* SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
    }
    
    private void paintFinalDestination(int rowIndex, int colIndex, Graphics g2d){
	g2d.fillRect(colIndex * SQUARE_SIZE, rowIndex * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	g2d.setColor(Color.RED);
	g2d.drawLine(colIndex* SQUARE_SIZE, rowIndex*SQUARE_SIZE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE);
	g2d.drawLine(colIndex* SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
	g2d.drawLine(colIndex* SQUARE_SIZE, rowIndex*SQUARE_SIZE, colIndex*SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
	g2d.drawLine(colIndex* SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE, colIndex*SQUARE_SIZE+SQUARE_SIZE, rowIndex*SQUARE_SIZE+SQUARE_SIZE);
    }

    private void paintEmptySpaces(int rowIndex, int colIndex, Graphics g2d){
	g2d.setColor(Color.white);
	g2d.fillRect(colIndex * SQUARE_SIZE, rowIndex * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	g2d.setColor(Color.CYAN);
	g2d.drawLine(colIndex* SQUARE_SIZE+5, rowIndex*SQUARE_SIZE+10, colIndex * SQUARE_SIZE + 10, rowIndex * SQUARE_SIZE + 5);
	g2d.drawLine(colIndex* SQUARE_SIZE+5, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE, colIndex * SQUARE_SIZE + SQUARE_MIDDLE, rowIndex * SQUARE_SIZE + 5);
	g2d.drawLine(colIndex* SQUARE_SIZE+5, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10, colIndex * SQUARE_SIZE + SQUARE_MIDDLE + 10, rowIndex * SQUARE_SIZE + 5);
    }
    
    private void paintStation(int rowIndex, int colIndex, Graphics g2d){
	g2d.setColor(Color.YELLOW);
	g2d.fillRect(colIndex * SQUARE_SIZE, rowIndex * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	g2d.setColor(Color.CYAN);
	g2d.drawLine(colIndex* SQUARE_SIZE+5, rowIndex*SQUARE_SIZE+10, colIndex * SQUARE_SIZE + 10, rowIndex * SQUARE_SIZE + 5);
	g2d.drawLine(colIndex* SQUARE_SIZE+5, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE, colIndex * SQUARE_SIZE + SQUARE_MIDDLE, rowIndex * SQUARE_SIZE + 5);
	g2d.drawLine(colIndex* SQUARE_SIZE+5, rowIndex*SQUARE_SIZE+SQUARE_MIDDLE+10, colIndex * SQUARE_SIZE + SQUARE_MIDDLE + 10, rowIndex * SQUARE_SIZE + 5);
    }


    private void paintPlayer(Player player, Graphics g2d){
	// Paint hat
//	g2d.setColor(Color.BLUE);
//	g2d.fillOval(player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+PAINT_PARAMETER_15, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT-2, PAINT_PARAMETER_15, PAINT_PARAMETER_15);
	// Paint body
	g2d.setColor(Color.LIGHT_GRAY);
	g2d.fillOval(player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT, player.getSize(), player.getSize());
	// Paint face
	g2d.setColor(Color.PINK);
	g2d.fillOval(player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+3, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT+3, player.getSize()-6, player.getSize()-6);
	// Paint eyes
	g2d.setColor(Color.BLACK);
//        g2d.drawOval(player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+10, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT+10, player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+10, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT+PAINT_PARAMETER_18);
//	g2d.drawLine(player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+10, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT+10, player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+10, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT+PAINT_PARAMETER_18);
//	g2d.drawLine(player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+PAINT_PARAMETER_20, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT+10, player.getX()-CHARACTER_ADJUSTMENT_FOR_PAINT+PAINT_PARAMETER_20, player.getY()-CHARACTER_ADJUSTMENT_FOR_PAINT+PAINT_PARAMETER_18);
    }
}
