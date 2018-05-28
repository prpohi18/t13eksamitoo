package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class Controller {

    @FXML
    private Canvas joonis = new Canvas();

    @FXML
    private ChoiceBox<Integer> choiceBox1 = new ChoiceBox<>(); //FXID nimed
    @FXML
    private ChoiceBox<String> choiceBox2 = new ChoiceBox<>(); //FXID nimed

    private ObservableList<Integer> valik1 = FXCollections.observableArrayList(0, 1, 2, 3, 4); // Choicebox1 sisu (int)
    private ObservableList<String> valik2 = FXCollections.observableArrayList("Ring", "Ruut", "Kolmnurk"); //Choicebox2 sisu (str)

    private GraphicsContext gc;

    private int[] x, y;
    private int z = 50;

    //kasutaja sisestab soovitud värvi
    private void sisend(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Sisesta värv (punane, kollane, sinine, roosa): ");
        String sone = reader.nextLine();

        //Default värviks must
        switch (sone){
            case "punane":
                gc.setFill(Color.RED);
                break;
            case "kollane":
                gc.setFill(Color.YELLOW);
                break;
            case "sinine":
                gc.setFill(Color.BLUE);
                break;
            case "roosa":
                gc.setFill(Color.HOTPINK);
                break;
        }
    }

    //hiire vajutused
    @FXML
    private void click(MouseEvent e){
        gc.clearRect(0, 0, joonis.getWidth(), joonis.getHeight());
        joonis.requestFocus();
        koordinaadid();
        action();
    }

    //joonistamine
    private void action(){
        gc.clearRect(0, 0, joonis.getWidth(), joonis.getHeight());
        switch(choiceBox2.getValue()){
            case "Ring": ring(); break;
            case "Ruut": ruut(); break;
            case "Kolmnurk": kolmnurk(); break;
        }
    }

    //keyboardi vajutused
    @FXML
    private void klaver(KeyEvent e){
        String code = e.getCode().toString(); // teeb enteri nupuvajutuse codeks
        //System.out.println(code);
        switch (code){
            case "ENTER": choiceBox1.setValue(0); gc.clearRect(0, 0, joonis.getWidth(), joonis.getHeight());
            case "PAGE_UP": z=z+2; action(); break;
            case "PAGE_DOWN": z=z-2; action(); break;
            case "HOME": z = 30; action(); break;
        }
    }

    //for loop koordinaatide jaoks
    private void koordinaadid(){
        for(int i = 0; i < valik1.size(); i++){
            //-z, et ei läheks piiridest välja
            x[i] = (int)(Math.random()*(joonis.getWidth()-z));
            y[i] = (int)(Math.random()*(joonis.getHeight()-z));
        }

    }
    //ringi joonistamine
    private void ring(){
        for (int i = 0; i < choiceBox1.getValue(); i++){
            gc.strokeOval(x[i], y[i], z, z);
            gc.fillOval(x[i], y[i], z, z);
        }
    }

    //ruudu joonistamine
    private void ruut(){
        for (int i = 0; i < choiceBox1.getValue(); i++){
            gc.strokeRect(x[i], y[i], z, z);
            gc.fillRect(x[i], y[i], z, z);
        }
    }

    //kolmnurga joonistamine
    private void kolmnurk() {
        for (int i = 0; i < choiceBox1.getValue(); i++) {
            double[] xs = new double[3];
            double[] ys = new double[3];

            //X koordinaadid
            xs[0] = (double)x[i];
            xs[1] = xs[0] + z/2.0;
            xs[2] = xs[0] + z;

            //Y koordinaadid
            ys[0] = (double)y[i];
            ys[1] = ys[0] + z;
            ys[2] = ys[0];

            gc.strokePolygon(xs, ys, 3);
            gc.fillPolygon(xs, ys, 3);
        }
    }

    @FXML
    private void initialize(){
        gc = joonis.getGraphicsContext2D();
        sisend();

        //Määran array suuruseks nii mitu valikut esimeses valikus on

        x = new int[valik1.size()];
        y = new int[valik1.size()];

        choiceBox1.setItems(valik1);
        choiceBox1.setValue(valik1.get(1));
        choiceBox2.setItems(valik2);
        choiceBox2.setValue(valik2.get(0));
    }
}
