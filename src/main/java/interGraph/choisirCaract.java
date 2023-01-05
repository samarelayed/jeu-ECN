package interGraph;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class choisirCaract extends Application {
	private VBox root;
	private Button play;
	private Label label;
	private HBox hbox;
	private Button Guerrier;
	private Button Archer;
	private Label label1;


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		String Frontname= "Avenir Next";
		try {
			root=new VBox(20);
			root.setBackground(new Background(new BackgroundFill(Color.web("#F0F4F8"),null,null)));
			root.setPadding(new Insets(50));
			root.setAlignment(Pos.CENTER);
			
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
                        
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.centerOnScreen();
			primaryStage.setTitle("WorldOfECN");
			
			label=new Label("World Of ECN".toUpperCase());
			label.setPadding(new Insets(0,0,40,0));
			label.setTextFill(Color.web("#044E54"));
			label.setFont(Font.font(Frontname, FontWeight.BOLD,16));
			
			label1=new Label("choisit ton caractère".toUpperCase());
			label1.setPadding(new Insets(0,0,40,0));
			label1.setTextFill(Color.web("#044E54"));
			label1.setFont(Font.font(Frontname, FontWeight.BOLD,16));
			
			hbox=new HBox(20);
			hbox.setBackground(new Background(new BackgroundFill(Color.web("#F0F4F8"),null,null)));
			hbox.setPadding(new Insets(50));
			hbox.setAlignment(Pos.CENTER);
			
			Guerrier = new Button("Guerrier");
			Guerrier.setOnAction(new  EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent arg0) {
	                // TODO Auto-generated method stub
	                
	               
	            }
	 
	        });
			Guerrier.setMaxWidth(150);
            Guerrier.setTextFill(Color.WHITE);
            Guerrier.setBackground(new Background(new BackgroundFill(Color.web("E66A64",0.5),null,null)));
			Guerrier.setOnMouseEntered(arg0 -> scene.setCursor(Cursor.HAND));
			Guerrier.setOnMouseExited(arg0 -> scene.setCursor(Cursor.DEFAULT));
			
			Archer= new Button("Archer");
			Archer.setOnAction(new  EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent arg0) {
	                // TODO Auto-generated method stub
	                
	                
	            }
	 
	        });
			Archer.setMaxWidth(150);
			Archer.setTextFill(Color.WHITE);
			Archer.setBackground(new Background(new BackgroundFill(Color.web("E66A64",0.5),null,null)));
			Archer.setOnMouseEntered(arg0 -> scene.setCursor(Cursor.HAND));
			Archer.setOnMouseExited(arg0 -> scene.setCursor(Cursor.DEFAULT));
			
			hbox.getChildren().addAll(Guerrier,Archer);
			
			play = new Button("connect");
			play.setOnAction(new  EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent arg0) {
	                // TODO Auto-generated method stub
	                
	                plateau plateau =new plateau();
	                try {
						plateau.start(primaryStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	 
	        });
			play.setMaxWidth(150);
            play.setTextFill(Color.WHITE);
            play.setBackground(new Background(new BackgroundFill(Color.web("E66A64",0.5),null,null)));
			play.setOnMouseEntered(arg0 -> scene.setCursor(Cursor.HAND));
			play.setOnMouseExited(arg0 -> scene.setCursor(Cursor.DEFAULT));
			
			root.getChildren().addAll(label, label1, hbox,play);
		
	 } catch(Exception e) {
			e.printStackTrace();
		}
  }
}
