package trees;

import controller.GraphicsTree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class TestGUI extends Application {

	public static void main(String[] args) {
		// GUI
		launch(args);
	}

	public void start(Stage stage) {
		stage.setTitle("Drawing the BST");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 500, 500);

        BinaryTree<Integer> myTree = createModel();
		GraphicsTree<Integer> c = new GraphicsTree<>(myTree);


		c.widthProperty().bind(scene.widthProperty());
		c.heightProperty().bind(scene.heightProperty());
		root.getChildren().add(c);
		stage.setScene(scene);
		stage.show();
		

	}

	private BinaryTree<Integer> createModel() {
		BinaryTree<Integer> myTree = new BinaryTree<>();
		myTree.insert(50);
		myTree.insert(60);
		myTree.insert(80);
		myTree.insert(20);
		myTree.insert(70);
		myTree.insert(40);
		myTree.insert(44);
		myTree.insert(10);
		myTree.insert(40);
		
		

		return myTree;
	}



}