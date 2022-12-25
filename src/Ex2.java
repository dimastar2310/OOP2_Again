package Our_Proj;

import Our_Proj.api.DirectedWeightedGraph;
import Our_Proj.api.DirectedWeightedGraphAlgorithms;

import java.nio.file.Path;
import java.nio.file.Paths;

// org.w3c.dom.Node; //does are for working with gui i guess
//import javax.swing.*;
//import java.awt.*;


/**
 * This class is the main class for Our_Proj.Ex2 - your implementation will be tested using this class.
 */

public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    //here we checking with obj graph initing
    public static DirectedWeightedGraph getGrapg(String json_file) {
        //obj to json
        //json to obj

        // ****** Add your code here ******

        DirectedWeightedGraph ans = new _DirectedWeightedGraph(json_file);
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {


        // ****** Add your code here ******
        DirectedWeightedGraph g = new _DirectedWeightedGraph(json_file);
        DirectedWeightedGraphAlgorithms ans = new _DirectedWeighedGraphAlgorithms();
        ans.init(g);

        return ans;
        // ********************************

    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        //DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        _DirectedWeightedGraph _DWG = new _DirectedWeightedGraph(json_file);
        _DirectedWeighedGraphAlgorithms alg = new _DirectedWeighedGraphAlgorithms();
        alg.init(_DWG);
        // ****** Add your code here ******
        GraphPanel graph = new GraphPanel(alg); //naniah she taanti et hjson shelo be gpanel ifhsar laasot metoda show
        GFrame g = new GFrame(graph); //gphrame mishtamesh be jpanel
        // ********************************
    }



    public static void main(String[] args) {

       // GUI testGUI = new GUI();

        //String filename = args[0]; //taking first argument from user
        //Path path = Paths.get(filename); //Path library working with pats

       // System.out.println(path);
       // runGUI(path.toString());
        runGUI("data/G1.json");


//
//
//
//        //////////////// json working ////////////////////////////
//        //String jsonString = "{\"name\":\"Dima\", \"age\":29}";
//        //GsonBuilder builder = new GsonBuilder();
//        //Gson gson = new Gson();
//        //TestTest t = gson.fromJson(jsonString, TestTest.class);
//        //System.out.println(t.age);
//        ///////////////////////////////////////////////////////////
//        _DirectedWeightedGraph _DWG = new _DirectedWeightedGraph();
//        _NodeData T1 = new _NodeData(new _GeoLocation(1, 100, 0.0),1);
//        _NodeData T2 = new _NodeData(new _GeoLocation(2, 100, 0.0),2);
//        _NodeData T3 = new _NodeData(new _GeoLocation(3, 100, 0.0),3);
//        _NodeData T4 = new _NodeData(new _GeoLocation(4, 100, 0.0),4);
//        _NodeData T5 = new _NodeData(new _GeoLocation(5, 100, 0.0),5);
//        _NodeData T6 = new _NodeData(new _GeoLocation(6, 100, 0.0),6);
//        _NodeData T7 = new _NodeData(new _GeoLocation(7, 100, 0.0),7);
//        _DWG.addNode(T1);
//        _DWG.addNode(T2);
//        _DWG.addNode(T3);
//        _DWG.addNode(T4);
//        _DWG.addNode(T5);
//        _DWG.addNode(T6);
//        _DWG.addNode(T7);
//
//
//
//        _DWG.connect(T1.getKey(),T3.getKey(),1);
//        _DWG.connect(T1.getKey(),T4.getKey(),1);
//        _DWG.connect(T1.getKey(),T5.getKey(),1);
//
//        _DWG.connect(T2.getKey(),T6.getKey(),1);
//        _DWG.connect(T2.getKey(),T7.getKey(),1);
//        System.out.println("---printing nodes of graph-----");
//
//         Iterator<NodeData> NI = _DWG.nodeIter();
//         while ((NI.hasNext())){
//              System.out.println(NI.next());
//           }
//
//
//        //_DWG.PrintNodeGraph();
//        _DirectedWeighedGraphAlgorithms k = new _DirectedWeighedGraphAlgorithms();
//
//        k.init(_DWG);
//
//
//        System.out.println(k.shortestPathDist(1,3));
//
//        // _DWG.PrintEdgesGraph();
//
//        System.out.println("_____________________________________________________________\n\n\n");
//
//        _DWG.PrintEdgeGraph();
//
//
//
//        System.out.println("_____________________________________________________________\n\n\n");
//        Iterator<EdgeData> Ed = _DWG.edgeIter(1);
//
//        while (Ed.hasNext()){
//            System.out.println(Ed.next());
//        }
//
//        System.out.println("______________loading json file to graph obj______");
//        _DirectedWeightedGraph g5 = new _DirectedWeightedGraph("data/G1.json");
//
//
//
//        g5.PrintNodeGraph();
//        g5.PrintEdgeGraph();
//



    }



    }

