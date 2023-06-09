/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SuzumeGUI;


import java.util.*;

/**
 *
 * @author HP
 */
public class SuzumeFloor {
    private final FloorTile[][] tiles;
    private int width;
    private int height;
//    StationLinkedlist listStation = new StationLinkedlist();
    
    private Collection<FloorListener> floorListeners = new ArrayList<>();
    private Player player = null;
    private boolean isGameOver = false;
    private boolean getTTT = false;
    
    public SuzumeFloor(int[][] arraymap){
        this.width=arraymap.length;
        this.height=arraymap[0].length;
        this.tiles = new FloorTile[height][width];
        setTiles(arraymap);
    }   
    
    public void setTiles(int[][] arraymap){
        int numstation =0;
        for (int j = 0; j < arraymap[0].length; j++) {
            for (int i = 0; i < arraymap.length; i++) {
                if (arraymap[i][j]==0)tiles[j][i]=FloorTile.EMPTY_SPACES;
                else if(arraymap[i][j]==1)tiles[j][i]=FloorTile.OBSTACLES;
                else if(arraymap[i][j]==2){
                    tiles[j][i]=FloorTile.STATIONS;
//                    listStation.addLast(numstation, j, i);
                    numstation++;
                    
                }
                else if(arraymap[i][j]==3)tiles[j][i]=FloorTile.FINAL_DESTINATION;
            }
        }
    }
    
     
    
     public static int pixelToSquare(int pixelCoord){
	return ((pixelCoord + SuzumeComponent.getSquareSize()-1) / SuzumeComponent.getSquareSize())-1;
    }

    public FloorTile getFloorTile(int rowIndex, int colIndex) {
	return tiles[rowIndex][colIndex];
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public Player getPlayer() {
	return player;
    }
    
    public boolean getIsGameOver() {
	return isGameOver;
    }

    public void setIsGameOver(boolean value) {
	isGameOver = value;
    }
    
    public boolean isGetTTT() {
        return getTTT;
    }
    public void setGetTTT(boolean getTTT) {
        this.getTTT = getTTT;
    }

    public void createPlayer(SuzumeComponent suzumeComponent, SuzumeFloor floor){
	player = new Player(suzumeComponent, floor);
    }

    public int squareToPixel(int squareCoord){
	return squareCoord * SuzumeComponent.getSquareSize();
    }
    
    public void addFloorListener(FloorListener bl) {
	floorListeners.add(bl);
    }

    public void notifyListeners() {
	for (FloorListener b : floorListeners) {
	    b.floorChanged();
	}
    }
    
    public boolean collisionWithFInal(AbstractCharacter abstractCharacter){
	//Maybe create if statements to only check nearby squares
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		if(getFloorTile(i, j) == FloorTile.FINAL_DESTINATION ){
		    boolean isIntersecting = squareCircleInstersect(i, j, abstractCharacter);
		    if (isIntersecting) {
			return true;
		    }
		}
                
	    }
	}
	return false;
    }
    
    public boolean collisionWithObstacle(AbstractCharacter abstractCharacter){
	//Maybe create if statements to only check nearby squares
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		if(getFloorTile(i, j) == FloorTile.OBSTACLES ){
		    boolean isIntersecting = squareCircleInstersect(i, j, abstractCharacter);
		    if (isIntersecting) {
			return true;
		    }
		}
	    }
	}
	return false;
    }
    
    public boolean collisionWithStation(AbstractCharacter abstractCharacter){
	//Maybe create if statements to only check nearby squares
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		if(getFloorTile(i, j) == FloorTile.STATIONS){
//                    if(listStation.checkPass(i,j));
                    boolean isIntersecting = StationInstersect(i, j, abstractCharacter);
		    if (isIntersecting) {
			return true;
		    }
		}
	    }
	}
	return false;
    }
    
      private boolean collidingCircles(AbstractCharacter abstractCharacter, int x, int y){
	int a = abstractCharacter.getX() - x - SuzumeComponent.getSquareMiddle();
	int b = abstractCharacter.getY() - y - SuzumeComponent.getSquareMiddle();
	int a2 = a * a;
	int b2 = b * b;
	double c = Math.sqrt(a2 + b2);
	return(abstractCharacter.getSize() > c);
    }

    private boolean squareCircleInstersect(int row, int col, AbstractCharacter abstractCharacter) {
	//http://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
	int characterX = abstractCharacter.getX();
	int characterY = abstractCharacter.getY();

	int circleRadius = abstractCharacter.getSize() / 2;
	int squareSize = SuzumeComponent.getSquareSize();
	int squareCenterX = (col*squareSize)+(squareSize/2);
	int squareCenterY = (row*squareSize)+(squareSize/2);

	int circleDistanceX = Math.abs(characterX - squareCenterX);
	int circleDistanceY = Math.abs(characterY - squareCenterY);

	if (circleDistanceX > (squareSize/2 + circleRadius)) { return false; }
	if (circleDistanceY > (squareSize/2 + circleRadius)) { return false; }

	if (circleDistanceX <= (squareSize/2)) { return true; }
	if (circleDistanceY <= (squareSize/2)) { return true; }

	int cornerDistance = (circleDistanceX - squareSize/2)^2 +
							      (circleDistanceY - squareSize/2)^2;

	return (cornerDistance <= (circleRadius^2));
    }
    private boolean StationInstersect(int row, int col, AbstractCharacter abstractCharacter) {
	//http://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
	int characterX = abstractCharacter.getX();//12
	int characterY = abstractCharacter.getY();//12

	int circleRadius = abstractCharacter.getSize() / 2;//20/2=10
	int squareSize = SuzumeComponent.getSquareSize();//24
	int squareCenterX = (col*squareSize)+(squareSize/2);// col +12
	int squareCenterY = (row*squareSize)+(squareSize/2);// row +12

	int circleDistanceX = Math.abs(characterX - squareCenterX);
	int circleDistanceY = Math.abs(characterY - squareCenterY);

	if (circleDistanceX > (0)) { return false; }//12+10=22
	if (circleDistanceY > (0)) { return false; }

	if (circleDistanceX <= (squareSize/2)) { return true; }
	if (circleDistanceY <= (squareSize/2)) { return true; }

	int cornerDistance = (circleDistanceX - squareSize/2)^2 +
							      (circleDistanceY - squareSize/2)^2;

	return (cornerDistance <= (circleRadius^2));

    }
}
