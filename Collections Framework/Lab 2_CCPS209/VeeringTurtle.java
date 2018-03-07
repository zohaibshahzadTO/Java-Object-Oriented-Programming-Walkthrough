// Lab 2 - CCPS209

public class VeeringTurtle extends BasicTurtle
{
  private double veer;

  public VeeringTurtle(double veer) {
    this.veer = veer;
  }

  @Override public void move(double dist) {
    super.move(dist);
    turn(veer);
  }

  @Override public String toString()
  {
    return "VeeringTurtle";
  }
}
