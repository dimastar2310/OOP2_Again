package Our_Proj;

//import Our_Proj.api.DirectedWeightedGraphAlgorithms;
import Our_Proj.api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
//Jpanel has double buffer

//I thing ActionListener maspik po
public class GraphPanel extends JPanel implements ActionListener {

    private DirectedWeightedGraphAlgorithms graph_algo;
    //JFrame frame; //has resizable properties ,fater of all here
    //JTextField tf; //writing test there
    JLabel l; //responsible for read only and text
    JButton b; //load
    JButton b1; //save
    JButton b2; //dijakstra
    //JPanel p; //has double buffer and better performents


    private double min_x = 30;
    private  double min_y = 30;
    private  double max_x = 40;
    private double max_y = 40;

    //private  double min_screen = 50;
    //private double

    private  int margin = 50;


    public GraphPanel(DirectedWeightedGraphAlgorithms alg) {
        super(); //nafile et Jpanel beikar
        b = new JButton("Show_Graph"); //getting the info from cmd
        //b.setText("Show_Graph");

        this.add(b);
        b1 = new JButton("Save_Graph"); //lets say i want to save picture to desktop or something
       // b1.setText("Save_Graph");
        b2 = new JButton("Dijakstra");
        //b2.setText("Dijakstra");
        l = new JLabel(); //maby i going to use to show the center
        this.graph_algo = alg;
        //this.setTitle("Graph guy"); goint to make it in
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //avoda shel gframe
       // int height = dimension.height;
        //int wight = dimension.width;   //ze le ihie masah mikapel
       // this.setSize(wight,height); //I dont thing its a good Idea I think Jpanel should be responsilbe for this

        l.setText("Please choose button first load then Dikastra");

        b.setBounds(50,150,150,30);
        b1.setBounds(50,300,150,30);
        b2.setBounds(50,500,100,30);
        //tf = new JFormattedTextField(); //this accepts text from user dont need
        //funkzia mekasheret
        b.addActionListener(this); //zarih lekabel kol mahlaka she me mamashet action listener
        b1.addActionListener(this); //be mikre shelanu jpanel
        b2.addActionListener(this);






        //p = new JPanel(); //this componenet is special
        this.setBackground(new Color(0x0808EA)); //erhavti jpanel kaet ani yahol lageshet le funkziot shel aba
        //this.addMouseListener(this); //kol mi she memamesh MouseListener bemikre haze GraphPanel nitan leakshiv

        //this.add(p); //naniah nosif le jpanel

        this.add(b1);
        this.add(b2);
        this.add(l);

        setLayout(null); //manager batahles yahol linahel li et hakomponentot
        //setVisible(true); //shel panel ahshav
        //this.add(tf);




    }

    void reset(){ //making new graph egain //leadaati ze mofia beathala
        graph_algo = new _DirectedWeighedGraphAlgorithms();
        repaint(); //nizaer mehadash ze shel label bihlal

    }


    @Override
    public void actionPerformed(ActionEvent e) { //this needs fix
        String command = e.getActionCommand();

        if(command.equals("Show_Graph")){
           // System.out.println("AIM AT ACTION PERFORMED");

             repaint(); //calling paint componenet

        }

    }

    @Override
    //Graphics hu shel gpanel minahel kaze
    protected void paintComponent(Graphics g) { //most imported i want my thing to be readen
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g2d); //ani mishtamesh be funkzia shel aba
        _DirectedWeightedGraph DW2 = (_DirectedWeightedGraph)graph_algo.getGraph(); //ani zarih et kol hathnuot
        g2d.setFont(new Font("name",Font.PLAIN,25));
        g2d.setColor(Color.white);
        Iterator<EdgeData> EI= DW2.edgeIter();
        Iterator<NodeData> NI = DW2.nodeIter();
        int nodesize = 12;
        while(NI.hasNext()){

            _NodeData n = (_NodeData) NI.next(); //return my next node
            System.out.println("n key is"+n.getKey());
           // int counter = 0;
            //kodem ani izaer hakodkod
            g2d.setColor(Color.white);
            _GeoLocation geo = (_GeoLocation) n.getLocation();
            //int new_a_x = (int)algoX(a.x());
            //int new_a_y = (int)algoY(a.y());
            double a_x = algoX(geo.x());
            double a_y = algoY(geo.y());
            g2d.translate(a_x,a_y);

            //a=  new _GeoLocation(new_a_x,new_a_y,0.0); //ani yahol laasot gam funkzia ani yodea
            //a_x = a_x*10;
           // a_y = a_y*10;
            g2d.fillOval((int)a_x ,(int)a_y ,12,12);

            System.out.println("new_a_x="+a_x);

            System.out.println("new_a_y="+a_y);
            Iterator<EdgeData> neigbors = DW2.edgeIter(n.getKey());
                    while (neigbors.hasNext()){
                        System.out.println("aim while");
                       // if(counter==0) { //a roze lizaer kodkod rishon kodem be zman natun ve az
                            _EdgeData e = (_EdgeData) neigbors.next(); //et hashhenim
                            //int src = e.getSrc(); //src is n i dont need it already have it
                            int dest = e.getDest();
                            _NodeData other = (_NodeData) DW2.getNode(dest);
                            _GeoLocation geo2 = (_GeoLocation)other.getLocation();

                             double b_x = algoX(geo2.x());

                           //  b_x = b_x*10;
                             double b_y = algoY(geo2.y());
                             //b_y = b_y*10;
                        System.out.println("old_b_x="+b_x);

                        System.out.println("old_b_x="+b_y);
                             g2d.translate(b_x,b_y);
                            //int new_b_x = (int)algoX(b.x());
                            //int new_b_y = (int)algoY(b.y());

                        System.out.println("new_b_x="+b_x);

                        System.out.println("new_b_x="+b_y);
                             geo2 = new _GeoLocation(b_x ,b_y,0.0);
                             Double dist = geo2.distance(geo);
                            String distS = dist.toString().substring(0,dist.toString().indexOf(".")+2); //ani roze 2 aharei
                            g2d.setColor(Color.white);
                            g2d.fillOval((int)b_x ,(int)b_y,12,12);
                            g2d.setColor(Color.red);
                            g2d.setFont(new Font("name",Font.PLAIN,10));
                            g2d.drawLine((int)a_x,(int)a_y,(int)b_x,(int)b_y);
                            g2d.drawString(distS,(int)((a_x+b_x)/2),(int)((a_y+b_y)/2));



                        }



        }




    }
    public double algoX(double x) {
        //lets say is new wight
        x = (x*10)/Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        System.out.println("x from algox"+x);
        //x = x - this.min_x; //min_x is 30
        // minscrean is 50
      //  x = (x / (this.max_x - this.min_x))*(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2*0.80-margin) +margin ;
      //  System.out.println("the x from algo"+x);
        double hight_devide_by_screen = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
     //           /Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        double res = x*hight_devide_by_screen ;
        System.out.println("the res for x is "+res);
        return res;
    }
    public double algoY(double y) {

        y = (y*10)/Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        System.out.println("y from algoy"+y);
        //y = y - this.min_y;
        //y = (y / (this.max_x - this.min_x))*(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2*0.80-margin) +margin ;
      //  System.out.println("the y from algo"+y);
        double hight_devide_by_screen = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
               // /Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        double res = y*hight_devide_by_screen ;
        System.out.println("the res for y is "+res);
        return res ;
    }
}
