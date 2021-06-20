import java.util.*;
import java.lang.*;
import java.io.*;
//import com.google.common.primitives.Doubles;
  
class Simpdnew {
    // A utility function to find the vertex with earliest pickup time,
    // from the set of vertices not yet included in shortest path tree
    static final int V = 42;
    public static int cap=0;
    public static int truck=1;
    public static int truck_index=0;
    public static int drop_index=0;
    //find the first location with earliest location
    int earliestpickup(Boolean sptSet[],int capacity,int[] pickup,HashMap<Integer,Integer>pickdrop,double[] pktime)
    {
        // Initialize min value
        double min = Integer.MAX_VALUE; 
        int min_index = -1;
        //double mintime= Integer.MAX_VALUE;
        //double avg=9.0;
        // Check if the vertex doesnt have any drop location before itself
        for (int v = 0; v < V; v++)
        {
            //System.out.println("v= "+v);
            /*for(int k=0;k<v;k++)
            {
                if(pd.get(k)==v)
                {
                    if(sptSet[k]==false)
                    flag=1;
                    //System.out.println(pd.get(k));
                    break;
                }
            }*/
            //avg=avg+((dist[v])*2)/60;
            //System.out.println("hm= "+hm[v]);

            
            if (sptSet[v] == false  && capacity>=pickup[v] && min>=pktime[v]){ //&& sptSet[pd.get(v)]==false && dist[v] <= min){
                //cap=cap+pickup[v];
                //truckorder[truck_index]=v;
                //truck_index++;
                //System.out.println("spt v "+v+" "+sptSet[v]);
                //min = dist[v];
                min=pktime[v];
                min_index = v;
                //time[v]=avg;
                //mintime=hm.get(v);
                //System.out.println(min);
                
            }
            /*if(flag==1)
            {
                continue;
            }*/
        }
        //System.out.println("minindex="+min);
        //System.out.println("avg"+avg);

        return min_index;
    }


  

    void routing(double graph[][], int src, HashMap<Integer,Integer>pickdrop,int capacity,int[] pickup,double[] pktime) 
    {
        try{
            FileWriter fw = new FileWriter("C:/PDFs/Internship/newout42.csv");
            PrintWriter out = new PrintWriter(fw);
            //out1.print("fdfd");
        out.print("Location,Drop,Order id,Picked Weight,Dropped Weight,Truck,Estimated Time,Actual Time,cap");
        out.println();

        Boolean sptSet[] = new Boolean[V]; //list with visisted nodes as True and unvisited as False
        int vcount=0;        //count of visisted vertices                  
        double total=0.0;      //total distance travelled
  
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            //dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        double dist=0.0;
        // Distance of source vertex from itself is always 0
        int truckorder[]=new int[V*2];
        HashMap<Integer,Integer> drop=new HashMap<Integer,Integer>(); //hashmap containing the orders and their wieghts to be dropped in the truck
        //int truck=1;
        //int truck_index=0;
        //double time[]=new double[V]; 
        //int count=0;
        // Find shortest path for all vertices
        //double time[]=new double[V];
        //double avg=9.0;
        double delay=0.0;
        int u = earliestpickup(sptSet,capacity,pickup,pickdrop,pktime);
        delay=pktime[u];
        for (int count = 0; count < V; count++) {
        //    while(vcount<=5){
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            //time[0]=9.00;
            //double minList = Collections.min(Arrays.asList(hm));
            //int u=findIndex(hm,minList);
            //int u = earliestpickup(sptSet,capacity,pickup,pd,hm);
            //int u=15;
            int feas[]=new int[V];     //stores feasible locations
            double time1=0;
            for(int y=0;y<V;y++)
                {
                    if(y==u)
                    {
                        feas[y]=-1;
                        continue;
                    }
                   time1=pktime[u]*60+graph[u][y]*3;
                   if(time1>=(pktime[y]*60) && sptSet[y]!=true)
                   {
                       feas[y]=y;
                       //System.out.println(feas[y]);
                   }
                   else
                   {
                       feas[y]=-1;
                       //System.out.print(feas[y]);
                   }
                }
                //delay=time1/60;
            //System.out.println();
            System.out.println(u);

          /*if(u1==-1)
            {
                delay=hm[u];
                u1=u;
                System.out.println("u= "+u+" delay= "+delay);
            }
            else{
                dist1=dist1+graph[u1][u];
                //dist1=dist1%60;
                //System.out.println("dist1="+dist1);
                delay=((hm[u]*60)+dist1)/60;
                System.out.println("u1= "+u1+" u="+u+" actual="+delay);
                //System.out.println("dist= "+graph[u1][u]+" total dist"+dist1);
                u1=u;
            }*/
            //avg=avg+((dist[u]*2)/60);
            //System.out.println("avg= "+avg);
            /*for(int k=0;k<V;k++)
                    {
                    System.out.print(sptSet[k]+" ");
                    }
                System.out.println();
            /*if(u==-1)
            {
                u=pd.get(u);
            }*/
            // Mark the picked vertex as processed
            if(u!=-1&& sptSet[u]!=true) 
            {
            sptSet[u] = true;
            vcount++;
            //System.out.println("vcount "+vcount);
            //System.out.println(drop);
            boolean test= drop.containsKey(u);  //check for drop order in location u
            if(test==true)
            {
                cap=cap-drop.get(u);    //remove order from truck
                //System.out.println("cap="+cap);
                //out.println(drop.get(u)+","+truck+","+delay);
                //drop.remove(u);
            }
            if(capacity==cap||capacity<=cap+pickup[u])     //when truck capacity is full
            {
                //System.out.println("capin= "+drop);
                //double min=Integer.MAX_VALUE;
                //int key=mindist(drop, truckorder, truck_index);
                //double time=delay;
                int flag;
                int c=0;
                int val=0;
                int ver=truckorder[truck_index-1];
                while(cap!=0)
                {
                    double min=Integer.MAX_VALUE;
                    flag=0;
                for ( int key : drop.keySet() ) {
                    //System.out.println("key= "+key+"val= "+drop.get(key));
                    double mindis=graph[ver][key];
                    //double min=Integer.MAX_VALUE;
                    if(drop.get(key)!=0 && min>=mindis)     //for nremaining drops in truck find location with minimum distance
                    {
                        min=mindis;
                        val=key;
                        //System.out.println("val= "+val);
                        flag=1;
                        //truckorder[truck_index]=key;
                        //truck_index++;
                        //out.println(key+","+-1+","+"O"+key+","+-1+","+drop.get(key)+","+truck);
                        //drop.replace(key,0);
                    }
                    if(flag==0)
                    {
                        val=key;
                        break;
                    }
                }
                //System.out.println("ver= "+ver+" val= "+val);
                //dist=dist+graph[ver][val];
                //delay=((time*60)+dist)/60;
                //System.out.println("dist= "+dist);
                //time=delay;
                if(ver==val)
                {
                    break;
                }
                ver=val;
                c=c+1;
                truckorder[truck_index]=val;
                truck_index++;
                //System.out.println("ver"+ver);
                //System.out.println("cap="+cap);
                cap=cap-drop.get(val);
                out.println(val+","+-1+","+"O"+val+","+-1+","+drop.get(val)+","+truck+",null,null");
                drop.remove(val);
            }
            //double time=delay;
            //System.out.println(delay);
                for(int k=0;k<truck_index-1;k++)
                    {
                        for(int l=k+1;l<truck_index;l++)
                        {
                            int x=truckorder[k];
                            int y=truckorder[l];
                            //System.out.print(x+" "+y);
                            dist=dist+graph[x][y];
                            //delay=((time*60)+dist)/60;
                            //System.out.println("delay= "+delay);
                            //out.println(delay);
                            //delay=time;
                            break;
                        }
                    }
                    //System.out.println("Path for truck "+truck);
                    /*for(int k=0;k<truck_index;k++)
                    {
                    System.out.print(truckorder[k]+"-> ");
                    }
                    System.out.println();*/
                    //System.out.println("Truck "+truck+ " capacity is full "+(cap));
                    System.out.println("Total distacnce travelled by truck "+truck+" = "+dist);
                    total=total+dist;
                    //cap=0;
                    truck++;
                    truck_index=0;
                    dist=0;
                    
                    //delay=hm[u];
                    //continue;
            }
            //System.out.println("truck_index="+truck_index);
            
            //cap=(int)(cap+graph[u][pd.get(u)]);
            //boolean test= drop.containsKey(u);
            //if(pickup[u]!=0)
            //{
                truckorder[truck_index]=u;
                truck_index++;
                cap=cap+pickup[u];  //pickup order
                out.print(u+","+pickdrop.get(u)+","+"O"+u+","+pickup[u]+",");
                if(test==true)
                {
                    //cap=cap-drop.get(u);
                    out.println(drop.get(u)+","+truck+","+delay+","+pktime[u]);
                    drop.remove(u);
                }
                else{
                    out.println(-1+","+truck+","+delay+","+pktime[u]);
                }
                if(drop.containsKey(pickdrop.get(u))==true)
                {
                    drop.put(pickdrop.get(u),drop.get(pickdrop.get(u))+pickup[u]);
                }
            else{
            drop.put(pickdrop.get(u),pickup[u]);
            drop_index++;
            }
            //System.out.println("cap="+cap);
            //System.out.println("drop"+drop);
            //System.out.println("vcount= "+vcount);
            /*truckorder[truck_index]=pd.get(u);
            cap=cap-pickup[u];
            if(pickup[pd.get(u)]!=0)
            {
                cap=cap+pickup[pd.get(u)];
            }
            System.out.println("cap="+cap);
            sptSet[pd.get(u)]=true;
            vcount++;
            truck_index++; 
            //4}  
            /*if(pd.get(u)==-1)
            {
                truckorder[truck_index]=u;
                truck_index++;
            }*/
        }
        //System.out.println("vco= "+vcount);
        if(vcount==V)       //when all the vertices are vsiisted
        {
                
                //System.out.println("dist "+dist);
                //System.out.println("Path for truck "+truck);
                int c=0;
                int val=0;
                //double min=Integer.MAX_VALUE;
                int ver=truckorder[truck_index-1];
                int flag=0;
                double time=delay;
                while(cap!=0)
                {
                    double min=Integer.MAX_VALUE;
                    flag=0;
                for ( int key : drop.keySet() ) {
                    double mindis=graph[ver][key];
                    //System.out.println("ver= "+ver+"key="+key);
                    //System.out.println("mindis="+mindis+"min="+min);
                    if(drop.get(key)!=0 && min>=mindis)
                    {
                        //System.out.println(drop);
                        min=mindis;
                        val=key;
                        flag=1;
                    }
                    if(drop.get(key)==0)
                    {
                        drop.remove(key);
                    }
                    if(flag==0)
                    {
                        val=key;
                        break;
                    }
                }
                //dist=dist+graph[ver][val];
                //delay=((time*60)+dist)/60;
                //time=delay;
                //System.out.println(" val= "+val);
                if(ver==val)
                {
                    break;
                }
                ver=val;
                c=c+1;
                truckorder[truck_index]=val;
                truck_index++;
                //System.out.println("dropout"+drop);
                //System.out.println(val);
                cap=cap-drop.get(val);
                out.println(val+","+-1+","+"O"+val+","+-1+","+drop.get(val)+","+truck+",null,null");
                drop.remove(val);  
                
            }
                //System.out.println();
                for(int k=0;k<truck_index-1;k++)
                    {
                        for(int l=k+1;l<truck_index;l++)
                        {
                            int x=truckorder[k];
                            int y=truckorder[l];
                            System.out.println("x="+x+" y="+y);
                            dist=dist+graph[x][y];
                            //delay=((time*60)+dist)/60;
                            //System.out.println("delay= "+graph[x][y]);
                            //out.println(delay);
                            //delay=time;
                            //System.out.println("dist="+dist);
                            break;
                        }
                    }
                //System.out.println("Truck "+truck+ " capacity is full "+(cap)); 
                total=total+dist;
                System.out.println("Total distacnce travelled by truck "+truck+" = "+dist);
                System.out.println(total);
        }
        double mindis=Integer.MAX_VALUE;
        int vertex=-1;
        for(int x=0;x<V;x++)    //choose next location from feasible list with minimum distance
        {
            if(feas[x]==-1)
            {
                continue;
            }
            if(mindis>graph[u][x] && sptSet[x]==false)
            {
                mindis=graph[u][x];
                vertex=x;                
            }

        }
        double est=0.0;
        if(vertex!=-1)
        {
        delay=pktime[vertex];
        pktime[vertex]=(pktime[u]*60+graph[u][vertex]*3)/60;
        u=vertex;
        System.out.println("updated u="+u);
        }
        /*if(vertex!=1 && inc==true)
        {
            u=vertex;
            inc=false;
            System.out.println("updated u="+u+" time="+hm[u]);
        }
        /*if(est>20.00)
        {
            truck++;
        }
        else{
            hm[u]=est;
        }*/

        }
        out.flush();
       
        //Close the Print Writer
        out.close();
            
        //Close the File Writer
        fw.close(); 
       
        }
        catch(IOException e)
        {
            System.out.print("error");
        }

}
    


    public static void main(String[] args)
    {
        /* Let us create the example graph discussed above */
        /*File file = new File("C:/PDFs/Internship/VRP_Instances_Belgium/VRP_Instances_Belgium/Antwerp1.txt");
        Scanner sc = new Scanner(file);
  
    // we just need to use \\Z as delimiter
        while(sc.hasNext())
        {
            String a= sc.next();
            String b= sc.next();
            String c= sc.next();
            System.out.println(a+" "+b+" "+c);
        }*/

        /*double graph[][] = new double[][] {{0, 34.49710977, 15.9671301,  32.6197163,  26.18149374, 13.25090103, 32.09095285, 33.67779921, 26.97163183, 13.32753917},
        {34.49710977, 0, 20.89064017,  2.75215067,8.99093507, 21.3101261,   3.75650706,  6.67056882,  7.8245949,  23.6261497}, 
        {15.9671301, 20.89064017,0, 19.80265762, 11.90445992,  7.57606839, 17.73032607,22.51032045, 13.09500495,  2.88753362}, 
        {32.6197163,  2.75215067, 19.80265762,0, 8.19682093, 19.37210031,  4.77239128,  4.57946549,  6.84166255, 22.41323887},
        {26.18149374,  8.99093507, 11.90445992,  8.19682093,0, 13.40950732,  5.96139049,11.80530494,  1.40131929, 14.6669426},
        {13.25090103, 21.3101261,   7.57606839,19.37210031, 13.40950732, 0,19.14009916, 20.57665527, 14.00041811,  7.49106143},
        {32.09095285,  3.75650706, 17.73032607,  4.77239128,  5.96139049, 19.14009916,0, 9.35144675,  5.14219114, 20.54548172}, 
        {33.67779921,  6.67056882, 22.51032045, 4.57946549, 11.80530494, 20.57665527,  9.35144675,0, 10.40621693, 24.87539121}, 
        {26.97163183,  7.8245949,  13.09500495,  6.84166255,  1.40131929, 14.00041811, 5.14219114, 10.40621693,0, 15.8056535},  
        {13.32753917, 23.6261497,   2.88753362, 22.41323887, 14.6669426,   7.49106143, 20.54548172, 24.87539121, 15.8056535,0}};
        //String loc[]= new String[]{"Navalur","Chepauk","Chrompet","Egmore","Guindy","Kundrathur","Mylapore","Perambur","Saidapet","Tambaram"}; 
        double hm[]=new double[]{9.00,11.15,10.45,10.15,9.30,10.00,9.45,10.30,9.15,11.00};
        int pickup[]=new int[]{30,10,30,40,20,10,30,20,10,0};*/
        //int purepick[]=new int[]{0,0,20};
        //int puredel[]=new int[]{0,0,0,0,0,0,0,0,0,20};
        //int drop[]=new int[]{-1,-1,-1,-1,-1,3,1,4,0,2};
        int pickup[]=new int[V];
        double mat[][]=new double[V][V];
        double pktime[]=new double[V];
        HashMap<Integer,Integer> pickdrop=new HashMap<Integer,Integer>();
        //System.out.println("djskjdkjs");
        try
      {
        BufferedReader br = new BufferedReader(new FileReader("C:/PDFs/Internship/demand42.csv"));   //weight of orders
          String line;
          //System.out.println("hsej");
          line=br.readLine();
          for(int i=0;i<V;i++)
        {
            //System.out.println(i);
            line=br.readLine();
            //System.out.println(line+" "+i);
              pickup[i]=Integer.parseInt(line);
              //System.out.println(pickup[i]);
            //System.out.println(mat[i][j]+" ");
          //System.out.println();
        }
        //System.out.println(pd.keySet());
        br.close();
        BufferedReader br1 = new BufferedReader(new FileReader("C:/PDFs/Internship/drop42.csv"));   //drop locations
        line="";
        //System.out.println(pd.keySet());
        line=br1.readLine();
        //System.out.println(line);
        for(int i=0;i<V;i++)
        {
          line=br1.readLine();
          //String part[]=line.split(",");
          pickdrop.put(i,Integer.parseInt(line));
        }
          //System.out.println(pd);
        br1.close();

        BufferedReader br2 = new BufferedReader(new FileReader("C:/PDFs/Internship/kolkata42.csv"));    //distance matrix
        line="";
    //System.out.println("rowList");
    //int i=0;
    //int j=0;
    //while ((line = br.readLine()) != null) {
      line=br2.readLine();
      //System.out.println(line);
      for(int i=0;i<V;i++)
      {
        for(int j=0;j<V;j++)
        {
          //System.out.println(line);
          if(i==j)
          {
            mat[i][j]=0.0;
            //System.out.println(mat[i][j]+" ");
            continue;
          }
          line=br2.readLine();
            //System.out.println(line);
          mat[i][j]=Double.parseDouble(line);
          //System.out.println(line);
          //System.out.println(mat[i][j]+" ");
        }
        //System.out.println();
      }
      br2.close();

      BufferedReader br3 = new BufferedReader(new FileReader("C:/PDFs/Internship/pickup42.csv"));   //pickup times
      line="";
      //System.out.println("hsej");
      line=br3.readLine();
      for(int i=0;i<V;i++)
    {
        line=br3.readLine();
        //System.out.println(line);
        //String[] part = line.split(",");
        //System.out.println(part[0]);
        //System.out.println(part[1]);
          //System.out.println(line);
          pickdrop[i]=Double.parseDouble(line);
          //System.out.println(hm[i]);
        //System.out.println(mat[i][j]+" ");
      //System.out.println();
    }
    //System.out.println(pd.keySet());
      br3.close();


      }
      catch(Exception e){
        // Handle any I/O problems
        }
       /* HashMap<Integer,Integer> pd=new HashMap<Integer,Integer>();
        pd.put(0,2);
        pd.put(1,4);
        pd.put(2,3);
        pd.put(3,6);
        pd.put(4,5);
        pd.put(5,7);
        pd.put(6,8);
        pd.put(7,9);
        pd.put(8,9);
        pd.put(9,9);*/
        //pd.put(-1,9);
        int capacity=250;
        Simpdnew t = new Simpdnew();
        t.routing(mat, 0,pickdrop,capacity,pickup,pktime);
    }
}