package interGraph;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Case extends Parent{
	private int emplacementX;
	private int emplacementY;
	
	public Case(int x,int y) {
		emplacementX=x;
		emplacementY=y;
		
		Rectangle fond=new Rectangle(0,0,12,12);
		fond.setFill(Color.DARKBLUE);
		this.getChildren().add(fond);
	}
	
	public int getEmplacementX() {
		return emplacementX;
	}
	public void setEmplacementX(int emplacementX) {
		this.emplacementX = emplacementX;
	}
	public int getEmplacementY() {
		return emplacementY;
	}
	public void setEmplacementY(int emplacementY) {
		this.emplacementY = emplacementY;
	}
	

}
