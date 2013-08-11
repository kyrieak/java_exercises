import java.util.*;

public class Problem13 {
  
  private double g = 9.81;
  private double m;
  private List<double[]> pathCoords;
  
  public static void main(String[] args) {
          
    Problem13 test = new Problem13();
    test.printResult();
  }
  
  public Problem13() {
    useSampleMass();
    useSamplePath();
  }
  
  public Problem13(double massKg, List<double[]> roboPath) {
    m = massKg;
    pathCoords = roboPath;
  }
  
  public double roboBattery() {
    double cap = 0.0;
    double minStartCap = 0.0;
    double deltaZ;
    double[] z = zVals();
    
    for(int idx = 1; idx < z.length; idx++) {
      deltaZ = z[idx] - z[idx - 1];

      if((cap - deltaZ) < 0) {
        minStartCap += deltaZ;
        cap = 0;
      } else {
        cap -= deltaZ;
        if(cap > minStartCap)
          cap = minStartCap;
      }
    }
    return (minStartCap * m * g);
  }
  
  private double[] zVals() {
    double[] vals = new double[pathCoords.size()];
    
    for(int idx = 0; idx < pathCoords.size(); idx++) {
      vals[idx] = pathCoords.get(idx)[2];
    }
    
    return vals;
  }
  
  public void useSamplePath() {
    pathCoords = new ArrayList<double[]>();
    pathCoords.add(new double[] {0.13,0.85,0.45});
    pathCoords.add(new double[] {0.82,0.22,0.75});
    pathCoords.add(new double[] {0.54,0.35,0.15});
    pathCoords.add(new double[] {0.28,0.84,0.11});
    pathCoords.add(new double[] {0.73,0.85,0.44});
    pathCoords.add(new double[] {0.53,0.867,0.21});
  }
  
  public void useSampleMass() {
    m = 5.589;
  }
  
  public void printResult() {
    System.out.println("Given a mass of " + m + "kg and path coordinates:");
    System.out.println(Arrays.deepToString(pathCoords.toArray()));
    System.out.print("The battery capacity must be at least ");
    System.out.print(roboBattery());
    System.out.println(" Joules to complete this path.");
  }
  
}
