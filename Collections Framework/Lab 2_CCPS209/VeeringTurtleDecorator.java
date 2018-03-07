// Lab 2 - CCPS209

public class VeeringTurtleDecorator extends TurtleDecorator
{
  private double veer;

  public VeeringTurtleDecorator (Turtle turtle, double veer)
  {
    super(turtle);
    this.veer = veer;
  }

  @Override public void move(double dist) {
    super.move(dist);
    turn(veer);
  }

  @Override public String toString() {
    return "VeeringTurtle(" + getTurtle().toString() + ")";
  }
}
