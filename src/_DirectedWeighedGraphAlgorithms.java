package Our_Proj;

import Our_Proj.api.DirectedWeightedGraph;
import Our_Proj.api.DirectedWeightedGraphAlgorithms;
import Our_Proj.api.EdgeData;
import Our_Proj.api.NodeData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class _DirectedWeighedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {

    private _DirectedWeightedGraph g;
    private ArrayList<int[]>PathsList;
    private Double Big_Number = Double.POSITIVE_INFINITY;
    private ArrayList<NodeData> DikstraPath;


    public _DirectedWeighedGraphAlgorithms(){

        this.g = null;
    }

    //inits graph here
    @Override
    public void init(DirectedWeightedGraph g) {
        this.g = new _DirectedWeightedGraph((_DirectedWeightedGraph) g);
        PathsList = new ArrayList<>();
        Big_Number = Double.POSITIVE_INFINITY;
        DikstraPath = new ArrayList<>();
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return g;
    }
    //need to do copy constructor
    @Override
    /**computes deep copy of this graph**/
    public DirectedWeightedGraph copy() {
        _DirectedWeightedGraph g1 = new _DirectedWeightedGraph(this.g);
        return g1;
    }
    //we checking from each to each node if visited
    private void DFS(int key) {
        Stack<NodeData> s = new Stack<NodeData>();
        NodeData t = this.g.getNode(key);
        s.push(t);
        t.setTag(1); //each and evrynode if visited
        while (!s.isEmpty()) {
            t = s.pop();
            Iterator<EdgeData> e = this.g.edgeIter(t.getKey());
            while (e.hasNext()) {
                EdgeData ed = e.next();
                //checking on neiighobs
                if (this.g.getNode(ed.getDest()).getTag() != 1) {//check if  visited
                    this.g.getNode(ed.getDest()).setTag(1);//set as visited
                    s.push(this.g.getNode(ed.getDest()));
                }
            }
        }
    }


    @Override
    public boolean isConnected() {
        /**dfs for each node**/
       if (this.g == null)
           return true;
       if (this.g.edgeSize() == 0 || this.g.nodeSize() == 1)
           return true;
       if (this.g.nodeSize() > this.g.edgeSize() + 1)
           return false;
       Iterator<NodeData> e = this.g.nodeIter(); //running on vertesis
       while (e.hasNext()) {
           NodeData t = e.next();
           DFS(t.getKey());
           Iterator<NodeData> i = this.g.nodeIter();
           while (i.hasNext()) {
               t = i.next();
               if (t.getTag() != 1) { //check if not visited,if there is one vertex like that return false
                   return false;
               }
             t.setTag(0);
           }
       }
       return true;
    }
    @Override
    public double shortestPathDist(int src, int dest) {
         //first node is 0 all other is ininity
        PriorityQueue<_NodeData> q = new PriorityQueue<_NodeData>();
        //java uses fibonnaci heap
        Iterator<NodeData> k = g.nodeIter();
        while (k.hasNext()) {
            NodeData t = k.next();
            t.setWeight(Big_Number);
            t.setInfo("white");
            q.add((_NodeData) (t)); //adding the nodes with inf and white tags
        }
        g.getNode(src).setWeight(0);
        DikstraPath.add(g.getNode(src));
        while (!q.isEmpty()){
            //extract mimium weight
            _NodeData u = q.remove(); //getting first Node
            //need to put it back to _NodeData
            Iterator<EdgeData> e = g.edgeIter(u.getKey());
            while (e.hasNext()){
                EdgeData ed = e.next();
                //we getting pointers
                //prev its u
                _NodeData next = (_NodeData) g.getNode(ed.getDest());
                //iam asking for neighbors
                if(next.getInfo()!="red"){
                    //weight that we checkin on
                    double t = u.getWeight() + ed.getWeight();
                    if(next.getWeight()>t){ //cheaper path
                        next.setWeight(t); //weigh
                        next.prev = u.getKey();
                        q.remove(u);
                         q.add(next);
                    }
                }
                u.setInfo("red"); //visited
            }
        }
        return g.getNode(dest).getWeight();
    }
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        double set_Dijekstra = shortestPathDist(src,dest); //we need bettween 2 point
        //theres is Dijekstra that going to help us
        Iterator<EdgeData> p = g.edgeIter(dest);
        LinkedList<NodeData> res = new LinkedList<>();
        List<NodeData> res2 = new ArrayList<>();
       // res = DikstraPath;
        //int t = dest;
        _NodeData curr = (_NodeData)g.getNode(dest);
        res.addFirst(curr);
        int pred = curr.prev;
       while (curr.getKey()!=src){
           int k = curr.prev;
           curr =  (_NodeData) g.getNode(k);
           res.addFirst(curr);
        }
        return res;
    }

    public void resetWeightAfterDijekstra () {
        Iterator<NodeData> ite111 = g.nodeIter();
        while (ite111.hasNext()){
            ite111.next().setWeight(0);
        }
    }


    @Override
    public NodeData center() {
        Iterator<NodeData> ite111 = g.nodeIter();
        double NimbusMaximus = -1;
        double NimbusMinimus = Integer.MAX_VALUE;
        resetWeightAfterDijekstra();
        List<Double> ecentricity = new ArrayList<>();
        List<Integer> firstVertexList = new ArrayList<>();
        List<Integer> secondVertexList = new ArrayList<>();
        double ecentricityMinimum = Integer.MAX_VALUE;
        int dummy1 = 0;
        int dummy2 = 0;
        while (ite111.hasNext()){
            resetWeightAfterDijekstra();
            Iterator<NodeData> ite222 = g.nodeIter();
            int temp = ite111.next().getKey();
            while (ite222.hasNext()){
                int temp2 = ite222.next().getKey();
                double ActuallDistance = shortestPathDist(temp,temp2);
                if ((temp!=temp2)&&((ActuallDistance>0)&&(ActuallDistance<Integer.MAX_VALUE))){
                    if (ActuallDistance>NimbusMaximus){
                        NimbusMaximus = ActuallDistance;
                        dummy1 = temp;
                        dummy2 = temp2;
                    }
                    //System.out.println("("+temp+","+temp2+")    =  "+ActuallDistance);
                    ActuallDistance = 0;
                }
            }
            if (NimbusMaximus>0){
                //System.out.println("******************"+NimbusMaximus);
                ecentricity.add(NimbusMaximus);
                firstVertexList.add(dummy1);
                secondVertexList.add(dummy2);
                NimbusMaximus = -1;
            }
        }
        //System.out.print("\n\n[");
        for(int i = 0 ; i < ecentricity.size() ; i++){
            if (ecentricity.get(i)<ecentricityMinimum){
                ecentricityMinimum = ecentricity.get(i);
                dummy1 = firstVertexList.get(i);
                dummy2 = secondVertexList.get(i);
            }
            //System.out.print(ecentricity.get(i)+",");
        }
        //System.out.print("]");
        System.out.println("\n\n("+dummy1+","+dummy2+")   "+ecentricityMinimum);
        return g.getNode(dummy1);
    }






    public  void getPermutations(int[] array){

        this.helper(array, 0);
    }
    private  void helper(int[] array, int pos){
        if(pos >= array.length - 1){
            int[] tempFinalarr = new int[array.length];
            for(int i = 0; i < array.length - 1; i++){
                tempFinalarr[i] = array[i];
                if (this.isThereEdgeConnecting(array[i],array[i+1])==0){
                    break;
                }
            }
            if(array.length > 0)
                tempFinalarr[array.length - 1] = array[array.length - 1];
            if (TraverseOnArrayToCheckIfPathExists_RtrnZeroIfNoPath(tempFinalarr)!=0){
                PathsList.add(tempFinalarr);
                return;
            }
        }
        for(int i = pos; i < array.length; i++){
            int t = array[pos];
            array[pos] = array[i];
            array[i] = t;
            helper(array, pos+1);
            t = array[pos];
            array[pos] = array[i];
            array[i] = t;
        }
    }
    public double isThereEdgeConnecting(int src, int dest) {
        if (this.g.e.size()!=0){
            if (this.g.e.get(src).get(dest)!=null){
                return this.g.e.get(src).get(dest).getWeight();
            }
        }
        return 0;
    }
    public double TraverseOnArrayToCheckIfPathExists_RtrnZeroIfNoPath(int a[]){
        double TotalDistance = 0;
        for (int i = 1 ; i < a.length ; i++){
            if (isThereEdgeConnecting(a[i-1],a[i])>0){
                TotalDistance = TotalDistance + isThereEdgeConnecting(a[i-1],a[i]);
            }else{
                return 0;
            }
        }
        return TotalDistance;
    }
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {

            ArrayList<NodeData> ReturnPath = new ArrayList<>();
            //~~~~~~~~~~~~ LIST OF CITIES TO PRIMITIVE INT ARRAY OF VERTICES ~~~~~~~~~~~//
            int[] arrOfKeys = new int[cities.size()];
            for(int i = 0; i < cities.size(); i++) {
                if (cities.get(i) != null) {
                    arrOfKeys[i] = cities.get(i).getKey();
                }
            }
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
            getPermutations(arrOfKeys);
            if (PathsList.size()>0){
                int [] ShortestPath = new int[PathsList.get(0).length];
                double ShortestPathLength = Integer.MAX_VALUE;
                for ( int iii = 0;  iii < PathsList.size() ; iii++){
                    if (TraverseOnArrayToCheckIfPathExists_RtrnZeroIfNoPath(PathsList.get(iii))<ShortestPathLength){
                        ShortestPathLength = TraverseOnArrayToCheckIfPathExists_RtrnZeroIfNoPath(PathsList.get(iii));
                        ShortestPath = PathsList.get(iii);
                    }
                }
                for (int j = 0 ; j < ShortestPath.length ; j++){
                    ReturnPath.add(g.g.get(ShortestPath[j]));
                }
                return ReturnPath;
            }

        return null;
    }

    @Override
    public boolean save(String file) { //returns json file
        //writing this json file to current object
        //osim serialize
        Iterator<NodeData> itrNode = this.g.nodeIter();
        Iterator<EdgeData> itrEdge =  this.g.edgeIter();
        JSONObject obj = new JSONObject();
        JSONArray edges = new JSONArray();
        while(itrEdge.hasNext()){
            EdgeData e = itrEdge.next();
            JSONObject edge = new JSONObject();
            edge.put("dest", e.getDest());
            edge.put("w", e.getWeight());
            edge.put("src", e.getSrc());
            edges.add(edge);
        }

        JSONArray nodes = new JSONArray(); //maarah shel object
        while(itrNode.hasNext()){
            NodeData n = itrNode.next();
            JSONObject node = new JSONObject();
            node.put("id" , n.getKey());
            node.put("pos" , n.getLocation().toString());
            nodes.add(node);

        }

        //ok now we have our files and object ,now we putting them to file with FileWriter
        FileWriter file2 = null;
        try{
            file2 = new FileWriter(file); //mazbia le kovez
            obj.put("Nodes", nodes); //inserting {[],[],[]..}
            obj.put("Edges", edges);
            file2.write(obj.toString());
        }catch(IOException e){
            return false;
        } finally{
            if (file2 != null) {
                try {
                    file2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean load(String file) { //returns object
        //loading json file to graph obj
        //osim deserialize
        _DirectedWeightedGraph grp = new _DirectedWeightedGraph(file); //DirectedWg has loader

        if (g == null){
            this.g = new _DirectedWeightedGraph();
            this.init(grp);
            return true;
        }
        else if (grp.nodeSize() != 0 || grp.edgeSize() != 0) {
            this.init(grp);
            return true;
        }
        else
            return false;
    }
}



