package Our_Proj;

import Our_Proj.api.DirectedWeightedGraph;
import Our_Proj.api.EdgeData;
import Our_Proj.api.NodeData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;//parsing file into json annauncment
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class _DirectedWeightedGraph implements DirectedWeightedGraph {



    public HashMap<Integer,NodeData> g ;                  //  Nodes hashmap<int,NodeData>
    public HashMap<Integer,HashMap<Integer,EdgeData>> e; //  Edges hashmap<int,HashMap<int,EdgeData>>


    public ArrayList<EdgeData> ItHelper2;







    private static int ModeCaunt;
    private static int NC; //nodes caunt we have static method too doesnt metter
    private static int EC; //edge caunt


    public _DirectedWeightedGraph() {
     e = new HashMap<Integer,HashMap<Integer,EdgeData>>();
     g = new HashMap<Integer,NodeData>();
     ItHelper2 = new ArrayList<>();

    }
    public  _DirectedWeightedGraph(_DirectedWeightedGraph o){
        ModeCaunt=o.getMC();
        NC = o.nodeSize();
        EC = o.edgeSize();

        this.e = new HashMap<Integer,HashMap<Integer,EdgeData>>(o.e);//might not work
        this.g = new HashMap<Integer,NodeData>(o.g);
        this.ItHelper2 = new ArrayList<>(o.ItHelper2);

    }


    @Override

    public NodeData getNode(int key) {
        return this.g.get(key);//if doesnt exist hashmapwill drow exeption
    }


    @Override
    public EdgeData getEdge(int src, int dest) { //mahzirim mazbia le Edge
       EdgeData lookingFor;
       if(!e.containsKey(src)){
           return null;
       }
       lookingFor = e.get(src).get(dest);
       return  lookingFor;
    }

    @Override
    public void addNode(NodeData n) { //maby key to obj not allowed much
        g.put(n.getKey(),n);
        NC++;
        ModeCaunt++;
        //even if i dont have neibers i have potential nebers
        //we annaynsiges edges for new thing
        HashMap<Integer, EdgeData> naibers = new HashMap<Integer, EdgeData>();
        e.put(n.getKey(), naibers);
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData p1 = new _EdgeData(src,dest,w);


        if (e.containsKey(src)){
            e.get(src).put(dest,p1);
            ItHelper2.add(p1); //osef et haedges bishvil laasot iter alehem
            EC++;
        }else{
            e.put(src,new HashMap<Integer,EdgeData>());
            e.get(src).put(dest,p1);
            ItHelper2.add(p1);
            EC++;

        }



    }



    @Override
    public Iterator<NodeData> nodeIter() {

        return g.values().iterator();
    }





    @Override
    public Iterator<EdgeData> edgeIter() { //running over al edges

        return ItHelper2.iterator();

    }
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) { //itereting over neighbors of specific node
        Iterator<EdgeData> HashMapDestIter_EdgeData = e.get(node_id).values().iterator();
        return HashMapDestIter_EdgeData;
    }


    @Override
    public NodeData removeNode(int key) {
        NodeData r = getNode(key);
        g.remove(key);
        ModeCaunt++;
        e.get(key).clear();
        return r;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData edge = e.get(src).get(dest);
        e.get(src).remove(dest);
        ModeCaunt++;
        return edge;
    }




    @Override
    public int nodeSize() {
        return NC;
    }

    @Override
    public int edgeSize() {
        return EC;
    }

    @Override
    public int getMC() {
        return ModeCaunt;
    }


    public void PrintNodeGraph () {
        Iterator<NodeData> aaaaaaaa = nodeIter();
        while (aaaaaaaa.hasNext()){
            System.out.println(aaaaaaaa.next()+",");
        }
    }

    public void PrintEdgeGraph () {
        Iterator<EdgeData> aaaaaaaa = edgeIter();
        while (aaaaaaaa.hasNext()){
            System.out.println(aaaaaaaa.next()+",");
        }
    }


      public _DirectedWeightedGraph(String fileName){ //loading from json to graph
        this.g = new HashMap<>(); //intitializing if we didnt with init() method
        this.e = new HashMap<>();
        this.ItHelper2 = new ArrayList<>();
        ModeCaunt = 0;

        JSONParser jsonParser = new JSONParser();
        File f = new File(fileName);      // should receive file path as input -> fileName
        try(FileReader reader = new FileReader(f)){ //copying my file to new file //mazbia le kovez

            Object obj =  jsonParser.parse(reader); //parsing my file into file_elemnt witch is just object//mazbia le kovez
            //ani ose le serializable
            JSONObject NodeObject = (JSONObject) obj;//ok converting file_element torning to jsonobject

            JSONArray NodeList = (JSONArray) NodeObject.get("Nodes");//get return array of object [{},{},....,{}]
            JSONArray EdgeList = (JSONArray) NodeObject.get("Edges");//get return array of object [{},{},....,{}]

            NodeList.forEach(node -> ppNode((JSONObject) node));
            EdgeList.forEach(edge -> ppEdge((JSONObject) edge));

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private void ppEdge(JSONObject edge) {
        if(edge != null) {
            int src = ((Long) edge.get("src")).intValue(); //Json object explicity saying to turn in to int
            int dest = ((Long) edge.get("dest")).intValue();
            double w = (double) edge.get("w");
            this.connect(src, dest, w);
        }
    }


    private void ppNode(JSONObject node) { //zarih laasot be ofen meihuad
        if (node != null) {
            int id = ((Long) node.get("id")).intValue(); //methoda shel jsonObject
            String pos = (String) node.get("pos");
            _GeoLocation g = new _GeoLocation(pos);
            _NodeData n = new _NodeData(g, id);
            this.addNode(n);
        }
    }


}
    /**this filds and constructor for dwg obj and graph **/



