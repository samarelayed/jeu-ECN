package interGraph;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class plateau extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		VBox root= new VBox();
		
		Scene scene = new Scene(root,6000,600);
		scene.setFill(Color.GREEN);
		
		GridPane plateau=new GridPane();
		plateau.setVgap(3);
		plateau.setHgap(3);
		root.getChildren().addAll(plateau);
		
		Case[][] plateauTab=new Case[50][50];
		for(int i=0;i<50;i++) {
			for(int j=0; j<50;j++) {
				plateauTab[i][j]= new Case(i,j);
				plateau.add(plateauTab[i][j], j, i);
				plateau.getChildren().add(plateauTab[i][j]);
				
			}
		}
		
		primaryStage.setTitle("Word Of ECN");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
