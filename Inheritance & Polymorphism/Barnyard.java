/*
The cliched first example of inheritance, akin to factorial for recursion or that "output a rectangle of asterisks"
for loops. But there is a reason why every cliche became a cliche in the first place. For convenience, all classes
are given here as static nested classes, but they might as well be separate top level classes. An abstract superclass
that defines the common behaviour of each animal. Cannot be used to create objects, but serves as abstract parameter type
for polymorphic methods that works for all animals.
*/

public abstract static class Animal {
  private static int count = 0; // counter sharted by all animals
  public static int getCount() { return count; }

  public Animal() {
    System.out.println("Default constructor of Animal"); // for educational purposes
    count++;
  }

  public abstract String getSound();
  public abstract String getSpecies();

  @Override public String toString() {
    return getSpecies() + " that says " + getSound();
  }
}

// A concrete subclass of the previous abstract superclass

public static class Cat extends Animal {
  private static Random rng = new Random();
  public Cat() {
    System.out.println("Default constructor of Cat");
  }

  @Override public String getSound() {
    return rng.nextInt(100) < 50? "meow": "purrr";
  }
  @Override public String getSpecies(){ return "cat"; }
}
