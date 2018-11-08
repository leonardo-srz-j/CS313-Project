import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;

public class GG5989 extends Application{

    protected ToggleGroup radio_buttons_group;
    protected RadioButton add_vertex,add_edge,more_vertex,shortest_path, change_weight;
    protected Button add_all_edges,random_weights,minimal_spanning_tree, help;
    protected Vertex currentVertex;
    protected Edge currentEdge;
    protected GraphMap saved_objects;
    private BorderPane main_pane;
    private VBox button_box;
    private AnchorPane graph;
    private Scene scene;

    public void myGui(){
        currentVertex = new Vertex();
        currentEdge = new Edge();
        saved_objects = new GraphMap();
        //----------Panes----------------------------
        main_pane = new BorderPane();
        button_box = new VBox();
        graph = new AnchorPane();
        //-------Set of radio Buttons----------------
        radio_buttons_group = new ToggleGroup();
        //Add Vertex
        add_vertex = new RadioButton("Add Vertex");
        add_vertex.setToggleGroup(radio_buttons_group);
        //Add Edge
        add_edge= new RadioButton("Add Edge");
        add_edge.setToggleGroup(radio_buttons_group);
        //More Vertex
        more_vertex= new RadioButton("More Vertex");
        more_vertex.setToggleGroup(radio_buttons_group);
        //Shortest Path
        shortest_path = new RadioButton("Shortest Path");
        shortest_path.setToggleGroup(radio_buttons_group);
        //Changing Weights
        change_weight = new RadioButton("Change a weight to: ");
        change_weight.setToggleGroup(radio_buttons_group);
        //--------Set of regular Buttons-----------------------
        add_all_edges = new Button("Add All Edges");
        random_weights = new Button("Random Weights");
        minimal_spanning_tree = new Button("Minimal Spanning Tree");
        help = new Button("Help");
        //setting width
        add_all_edges.setMaxWidth(Double.MAX_VALUE);
        random_weights.setMaxWidth(Double.MAX_VALUE);
        minimal_spanning_tree.setMaxWidth(Double.MAX_VALUE);
        help.setMaxWidth(Double.MAX_VALUE);
        //---------------Setting Elements-------------------------
        button_box.prefWidth(200);
        button_box.maxWidthProperty();
        button_box.setSpacing(10);
        button_box.setPadding(new Insets(60, 20, 0, 5));
        button_box.getChildren().addAll(add_vertex,
                add_edge, more_vertex, shortest_path,
                change_weight, add_all_edges, random_weights, minimal_spanning_tree, help);
        main_pane.setLeft(button_box);
        main_pane.setCenter(graph);
        //--------------mouse event-----------------------------
//----------------------------------------------------adding vertex
        add_vertex.setOnAction(event -> {
            graph.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
                double x = me.getX();
                double y = me.getY();
                Circle currentCircle;

                if (add_vertex.isSelected()) {
                    if (me.getButton().equals(MouseButton.PRIMARY)) {
                        me.getPickResult().getIntersectedPoint();

                        currentCircle = new Circle(x, y, 5, Color.RED);
                        Vertex vertex = new Vertex(x,y);
                        saved_objects.addVertex(vertex);
                        graph.getChildren().add(currentCircle);
                    }
                }
            });
        });
//---------------------------------------------------adding edges
        add_edge.setOnAction(event -> {
            graph.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
                double x;
                double y;
                Circle first_Click= new Circle();
                Circle second_Click;


               // me.get
                Line ghost;
                Circle tempV;
                if (add_edge.isSelected()) {
                    if (me.getButton().equals(MouseButton.PRIMARY)) {

                        if(first_Click == null) {

                            System.out.println("this is first circle" + me.getTarget());
                            try {
                                first_Click = (Circle) me.getTarget();
                                System.out.println("first circle turned green");
                                ((Circle) me.getTarget()).setFill(Color.GREENYELLOW);
                            } catch (NullPointerException ex) {
                                System.out.println("reached first null exception");
                                return;
                            }
                        }
                        else if (first_Click.getFill().equals(Color.GREENYELLOW)){
                            System.out.println("got to the else if statement");
                            second_Click = (Circle) me.getTarget();
                            System.out.println("this is second circle" + me.getTarget() );
                            try {
                                if(second_Click == null || first_Click==second_Click) throw new NullPointerException();
                                System.out.println("ghost was created");
                                ghost = new Line(first_Click.getLayoutX(),first_Click.getScaleY(),
                                    second_Click.getLayoutX(),second_Click.getLayoutY());
                                ghost.setFill(Color.BLUE);
                                ghost.setStrokeWidth(8);
                                System.out.println("ghost should be added");
                                graph.getChildren().add(ghost);
                            }catch (NullPointerException ex) {
                                first_Click.setFill(Color.RED);
                               // this.repaint();
                                first_Click=null;
                                second_Click=null;
                                return;
                            }
                            first_Click.setFill(Color.RED);
                           // this.repaint();
                            first_Click=null;
                            second_Click=null;

                        }



/*                        if (first_Click == null){
                            if (me.getTarget() instanceof Circle ){
                                System.out.println("we got to one circle");
                                ((Circle) me.getTarget()).setFill(Color.GREENYELLOW);
                                first_Click = (Circle) me.getTarget();
                            }
                            if(((Circle) me.getTarget()).setFill(Color.GREENYELLOW)) {
                                System.out.println("we got to second circle");
                                System.out.println(((Circle) me.getTarget()).getFill().equals(Color.GREENYELLOW));
                            }*/




                            //tempV.setStroke(Color.YELLOW);
                            //graph.getChildren().add(tempV);






                        /*


                        if(first_Click == null) {
                            first_Click = new (Circle) me.getTarget();
                            try{
                                ((Circle) me.getTarget()).setFill(Color.GREENYELLOW);
                            }catch (NullPointerException ex) {
                                return;
                            }
                        }else if(first_Click.getFill().equals(Color.GREENYELLOW)) {
                            second_Click = (Circle) me.getTarget();
                            b_point = saved_objects.findVertex(tempV);
                            try {
                                if(b_point == null || a_point==b_point) throw new NullPointerException();
                                ghost = new Line(a_point.getX_Axis(),a_point.getY_Axis(),b_point.x_Axis,b_point.y_Axis);
                                graph.getChildren().add(ghost);
                                saved_objects.addEdge(a_point,b_point);
                            }catch (NullPointerException ex) {
                                a_point.setVertex_color(Color.RED);
                               // graph.getChildren().add(tempV);
                                a_point=null;
                                b_point=null;
                                return;
                            }
                            a_point.setVertex_color(Color.RED);
                            //graph.getChildren().add(tempV);
                            a_point=null;
                            b_point=null;
                        }*/




                    }
                }
            });
        });
        //------------------------------------------move_vertex
        more_vertex.setOnAction(event -> {
            main_pane.getCenter().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
                double x = me.getX();
                double y = me.getY();
                Circle a_point;
                Circle b_point;

                if (more_vertex.isSelected()) {
                    if (me.getButton().equals(MouseButton.PRIMARY)) {
                        System.out.println("move vertex");



                        //get the new click; save it; bind the circle and line to the new point



                    }
                }

            });
        });
        //------------------------------------------shortest_path
        shortest_path.setOnAction(event -> {
            main_pane.getCenter().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
                double x = me.getX();
                double y = me.getY();
                Circle a_point;
                Circle b_point;

                if (shortest_path.isSelected()) {
                    if (me.getButton().equals(MouseButton.PRIMARY)) {
                        System.out.println("short path");



                    }
                }
            });
        });
        //---------------------------------------------change_weight
        change_weight.setOnAction(event -> {
            main_pane.getCenter().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
                double x = me.getX();
                double y = me.getY();
                Circle a_point;
                Circle b_point;

                if (change_weight.isSelected()) {
                    if (me.getButton().equals(MouseButton.PRIMARY)) {
                        System.out.println("change weight");



                    }
                }
            });
        });


    }







    public void GG5989(){
        myGui();
    }

    public void start(Stage primaryStage) {
        GG5989();
        scene = new Scene(main_pane, 900, 900);
        primaryStage.setTitle("Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) { Application.launch(args); }

     class Vertex {

        private double x_Axis, y_Axis;
        private Circle visual_point;
        private Color vertex_color;
        private int vertexNum;

        public Vertex(){
        }

        public Vertex(double x, double y){
            this.x_Axis = x;
            this.y_Axis = y;
            this.vertex_color = Color.RED;
            visual_point = new Circle(x,y,5, vertex_color);
            this.vertexNum++;
        }

        public double getX_Axis() {
            return visual_point.getLayoutX();
        }

        public void setX_Axis(double x_Axis) {
            this.x_Axis = x_Axis;
        }

        public double getY_Axis() {
            return visual_point.getLayoutY();
        }

        public void setY_Axis(double y_Axis) {
            this.y_Axis = y_Axis;
        }

        public Circle getVisual_point() {
            return visual_point;
        }

        public void setVisual_point(Circle screen_vertex) {
            this.visual_point = screen_vertex;
        }

        public Color getVertex_color() {
            return vertex_color;
        }

        public void setVertex_color(Color vertex_color) {
            this.vertex_color = vertex_color;
        }

        public int getVertexNum() {
            return vertexNum;
        }

        public void setVertexNum(int vertexNum) {
            this.vertexNum = vertexNum;
        }
    }

     class Edge {
        private Vertex start_point, end_point;
        private Color edge_color;
        private Line screen_Line;
        private int weight, edgeID;


        public Vertex getStart_point() {
            return start_point;
        }

        public void setStart_point(Vertex start_point) {
            this.start_point = start_point;
        }

        public Vertex getEnd_point() {
            return end_point;
        }

        public void setEnd_point(Vertex end_point) {
            this.end_point = end_point;
        }

        public Color getEdge_color() {
            return edge_color;
        }

        public void setEdge_color(Color edge_color) {
            this.edge_color = edge_color;
        }

        public Line getScreen_Line() {
            return screen_Line;
        }

        public void setScreen_Line(Line screen_Line) {
            this.screen_Line = screen_Line;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getEdgeID() {
            return edgeID;
        }

        public void setEdgeID(int edgeID) {
            this.edgeID = edgeID;
        }
        //implement later  private HashMap<>

        public Edge(){}

        public Edge(Vertex start,Vertex end){
            this.start_point = start;
            this.end_point = end;
            this.edge_color = Color.BLUE;
            this.screen_Line.setFill(edge_color);
            this.screen_Line = new Line(start.getX_Axis(),start.getY_Axis(),end.getX_Axis(),end.getY_Axis());
            edgeID++;
        }
    }

    public class GraphMap {
        private HashMap<Vertex, HashSet<Edge>> current_Map;
        int vertexID,edgeID;

        public GraphMap(){
            this.current_Map = new HashMap<>();
            this.vertexID = 0;
            this.edgeID = 0;
        }

        public void addVertex(Vertex v){
            if (!current_Map.containsKey(v)){
                current_Map.put(v,new HashSet<>());
                vertexID++;
            }
        }

   public Vertex findVertex(Circle userClick) {
        Point2D click = new Point2D(userClick.getCenterX(),userClick.getCenterY());
        for(Vertex v: current_Map.keySet()) {

            if(v.getVisual_point().contains(click)) {
                return v;
            }
        }
        return null;

    }

        public void addEdge(Vertex v1, Vertex v2) {
            if(edgeExists(v1,v2))return;
            Edge tempEdge = new Edge(v1,v2);
            current_Map.get(v1).add(tempEdge);
            current_Map.get(v2).add(tempEdge);
            edgeID += 1;
        }

        public boolean edgeExists(Vertex head, Vertex tail) {
            Edge exist = getEdge(head,tail);
            if(exist !=null)return true;
            return false;
        }

        public Edge getEdge(Vertex head, Vertex tail) {
            HashSet<Edge> edges = current_Map.get(head);
            for(Edge e: edges) {
                if(e.getEnd_point().equals(tail))return e;
            }
            return null;
        }

    }


}

