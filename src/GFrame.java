package Our_Proj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GFrame extends JFrame { //ani roze halon she ihi po hakol
  GraphPanel panel;

//GraphPanel p
  public GFrame(GraphPanel p){ //ba main po ani agdir et Graph
      super();
      this.panel = p;
      this.add(this.panel);
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setVisible(true);



  }

//   public static void main(String[] args) {
//       _DirectedWeightedGraph _DWG = new _DirectedWeightedGraph("data/G1.json");
//       _DirectedWeighedGraphAlgorithms alg = new _DirectedWeighedGraphAlgorithms();
//        alg.init(_DWG);
//       // ****** Add your code here ******
//      GraphPanel graph = new GraphPanel(alg); //naniah she taanti et hjson shelo be gpanel ifhsar laasot metoda show
//        new GFrame(graph); //gphrame mishtamesh be jpanel
////
////        //new GFrame();/
//        }


}






