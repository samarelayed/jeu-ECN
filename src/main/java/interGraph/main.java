package interGraph;


import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application  {

	
	


@Override
public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub
	Stage primaryStage=new Stage();
	plateau plateau =new plateau();
	plateau.start(primaryStage);
}

public static void main(String[] args) throws Exception {
	launch(args);
    	
    }

	

}
