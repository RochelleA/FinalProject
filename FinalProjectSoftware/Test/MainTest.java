import core.*;
import gui.*;
import view.*;
public class MainTest {

	public static void main(String[] args) {
		      View MyView = new View();
			  Model MyModel = new Model();
			  Controller MyController = new Controller(MyView,MyModel);
			  MyView.setVisible(true);
		 }

}
