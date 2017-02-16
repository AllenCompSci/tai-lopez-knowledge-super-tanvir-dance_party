import java.awt.*;

public class square {
    private square []surrounding;
    private boolean impassable;
    private boolean pig;
    private boolean blocked;
    private boolean edge;
    private boolean isPath;
    private Polygon squareset;
    private int x;
    private int y;
    private Color currColor;


    private boolean isOccupied;
    public square(boolean isEdge, Point center, int side, double h, double r, int x, int y) {
        setSquareset(new Polygon());
		/*for(int i=0; i<6; i++) {
			squareset.addPoint((int)(center.x + r*Math.cos(i*2*Math.PI/6)),(int)(center.y + r *Math.sin(i*2*Math.PI/6)));
			System.out.println("X : " + (int)(center.x + r*Math.cos(i*2*Math.PI/6)) + ", Y : " + (int)(center.y + r *Math.sin(i*2*Math.PI/6)));

		}*/
		currColor = new Color(0,0,0,0);
        setPath(false);
        setBlocked(false);
        setX(x);
        setY(y);
        squareset.addPoint((int)(center.x), (int)(center.y));
        squareset.addPoint((int)(center.x + r), (int)(center.y + h));
        squareset.addPoint((int)(center.x + r), (int)(center.y + side + h));
        squareset.addPoint((int)(center.x), (int)(center.y + side + h + h));
        squareset.addPoint((int)(center.x - r), (int)(center.y + side + h));
        squareset.addPoint((int)(center.x - r), (int)(center.y + h));
        //System.out.println( );
        //System.out.println( );
        setEdge(isEdge);
        setSurrounding(new square[6]);
        for(int i = 0; i < 6; i++){
            surrounding[i] = null;
        }
        setImpassable(false);
        setMax(false);
        //CLOCKWISE from top right to top left
        // {TopRight, Right, BottomRight, BottomLeft, Left, TopLeft}
        // TODO Auto-generated constructor stub

    }

    public square [] getSurrounding() {
        return surrounding;
    }
    public boolean isImpassable() {
        return impassable;
    }
    public boolean isEdge() {
        return edge;
    }

    public void setSurrounding(square [] surrounding) {
        this.surrounding = surrounding;
    }
    public void setImpassable(boolean impassable) {
        this.impassable = impassable;
    }
    public void setEdge(boolean edge) {
        this.edge = edge;
    }
    public Polygon getSquareset() {
        return squareset;
    }
    public void setSquareset(Polygon squareset) {
        this.squareset = squareset;
    }
    public boolean isPig() {
        return pig;
    }

    public Color getCurrColor() {
        return currColor;
    }

    public void setMax(boolean pig) {
        this.pig = pig;
        if(pig){
            currColor = Color.ORANGE;
        }
    }
    public void setVance(boolean pig) {
        this.pig = pig;
        if(pig){
            currColor = Color.CYAN;
        }
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isPath() {
        return isPath;
    }
    public void setPath(boolean isPath) {
        this.isPath = isPath;
    }
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }




}