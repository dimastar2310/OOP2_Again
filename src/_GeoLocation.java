package Our_Proj;

import Our_Proj.api.GeoLocation;

public class _GeoLocation implements GeoLocation {
    private double x;
    private double y;
    private double z;

    public _GeoLocation(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public _GeoLocation(String pos){
        String[] indexes = pos.split(",");
        this.x = Double.parseDouble(indexes[0]);
        this.y = Double.parseDouble(indexes[1]);
        this.z = 0.0;
    }

    public _GeoLocation(GeoLocation g){ //copy constructor


        this(g.x(),g.y(),g.z()); //calls diffault constructor
    }



    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return 0.0;
    }

    @Override
    public double distance(GeoLocation g) {
        double distx = Math.pow(this.x - g.x(), 2);
        double disty = Math.pow(this.y - g.y(), 2);

        double dist = Math.sqrt(distx - disty);
        return dist;
    }

    @Override
    public String toString(){
        return this.x() + " , " + this.y() + " , " + this.z();
    }
}
