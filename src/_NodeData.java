package Our_Proj;

import Our_Proj.api.GeoLocation;
import Our_Proj.api.NodeData;

//import Our_Proj.api.Our_Proj._EdgeData;
//must hold at list 20 edges 10 inner 10 outer
 class _NodeData implements NodeData,Comparable<_NodeData> {
    private GeoLocation position;
    private int key;
    private String info = "";
    private int Tag_;
    private double Weight_ = 0.0;
    private double x;
    private double y;
    private double z;
    int prev;

//HashMap is O(1)
    public _NodeData() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        Tag_ = 0;
    }

    public _NodeData(GeoLocation pos, int key){
        this.key = key;
        this.position = new _GeoLocation(pos);
        this.Weight_ = 0;      //default
        this.info = "pos: " + pos.toString() + ", key: " + key;      //default
        this.Tag_ = 0;       //default
    }
    public _NodeData(_NodeData o){ //need to fix a = _NodeData(_NodeData) the new object ,ani lokeah et haobject hamekori
        //ve mahnis le nohehi
        this.key = o.key;
        this.Tag_ = o.Tag_;
        this.info = o.info;
        this.Weight_ = o.Weight_;
        this.position = new _GeoLocation(o.getLocation());
        this.prev = o.prev;

    }


    public _NodeData(int key)
    {
        this.key = key;
    }
    //key == id

    public void setId(int id){
        this.key = id;
    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {



        return this.position;
    }

    @Override
    public String getInfo() {
        return this.info;
    }
    @Override
    public void setInfo(String s) {
        this.info = s;
    }
    @Override
    public int getTag() {
        return this.Tag_;
    }
    @Override
    public void setTag(int t) {
        this.Tag_ = t;
    }
 


    @Override
    public void setLocation(GeoLocation p) {
        this.position = new _GeoLocation(p);
    }


    @Override
    public double getWeight() {
        return this.Weight_;
    }
    @Override
    public void setWeight(double w) {
        this.Weight_ = w;
    }

    @Override
    //comparing NodeData with _NodeData its fine
    //but compareTo Only_NodeData should know
    //we working with NodeData in graph a lot
    public int compareTo(_NodeData o) {

        int ans = 0;
        if (this.getWeight()-o.getWeight()>0){
            ans=1;

        }
        else if (this.getWeight() - o.getWeight()<0){
             ans= -1;
        }
        //they equal
        return ans;


    }


    @Override
    public String toString(){
        String str = "key: " + this.key + " POS: " + this.position.toString();//printing with my location
        return str;
    }
}
