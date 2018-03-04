# Graphical User Interfaces with Java

A graphical application shows information inside a frame: a window with a title bar. In the following sections,  we'll delve into how to display a frame and how to place user-interface components inside it.

# Displaying a Frame

To show a frame, carry out the following steps:

1.) Construct an object of the JFrame class:

  JFrame frame = new JFrame();

2.) Set the size of the frame:

  final int FRAME_WIDTH = 300;
  final int FRAME_HEIGHT = 400;
  frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

  *To show a frame, construct a JFrame object, set its size, and make it visible*

3.) IF you'd like, set the title of the frame:

  frame.setTitle("An empty frame");

  If you omit this step, the title bar is simply left blank.

4.) Set the "default close operation":

  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  When the user closes the frame, the program automatically exits. Don't omit this step. If you do, the prorgam keeps running even after the frame is closed.

5.) Make the frame visible:

  frame.setVisible(true);

The simple program below shows all of these steps. It produces the empty frame. The JFrame class is a part of the javax.swing package. Swing is the nickname for the graphical User interface library in Java. The "x" in javax denotes the fact that Swing started out as a Java extension before it was added to the standard library.

    *import javax.swing.JFrame;

     // This program displays an empty frame.

     public class EmptyFrameViewer {
       public static void main(String[] args) {
         JFrame frame = new JFrame();

         final int FRAME_WIDTH = 300;
         final int FRAME_HEIGHT = 400;
         frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
         frame.setTitle("An empty frame");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         frame.setVisible(true);
      }
    }
***

# Adding User-Interface Components to a Frame

An empty frame is not very interesting. You will want to add some user-interface components, such as buttons and text labels. However, if you add components directly to the frame, they get placed on top of each other.

If you have more than one component, put them into a panel (a container for other
user-interface components), and then add the panel to the frame:

  JPanel panel = new JPanel();
  panel.add(button);
  panel.add(label);
  frame.add(panel);

You first construct the components, providing the text that should appear on them:

  JButton button = new JButton("Click me!");
  JLabel label = new JLabel("Hello World!");

Then you add the components to the frame. When you run the program, you can click the button, but nothing will happen. We'll see later on how to attach an action to a button.

See jpanelExample.java for the full example code.

# Using Inheritance to Customize Frames

As you add more user-interface components to a frame, the frame can get quite complex. Your programs will become easier to understand when you use inheritance for complex frames. To do so, design a subclass of JFrame. Store the components as instance variables. Initialize them in the constructor of your subclass. This approach makes it easy to add helper methods for organizing your code.

It is also a good idea to set the frame size in the frame constructor. The frame usually has a better idea of the preferred size than the program displaying it. For example,

    *public class FilledFrame extends JFrame {
      // Use instance variables for components
      private JButton button;
      private JLabel label;

      private static final int FRAME_WIDTH = 300;
      private static final int FRAME_HEIGHT = 100;

      public FilledFrame() {

        // Now we can use a helper method
        createComponents();

        // It is a good idea to set the size in the frame constructor
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
      }

    private void createComponents() {
      button = new JButton("Click me!");
      label = new JLabel("Hello, World!");
      JPanel panel = new JPanel();
      panel.add(button);
      panel.add(label);
      add(panel);
    }
  }*

Of course, we still need a class with a main method:

  *public class FilledFrameViewer2 {

    public static void main(String[] args) {
      JFrame frame = new FilledFrame();
      frame.setTitle("A frame with two components");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }
  }*

# Events and Event Handling

In an application that interacts with the user through a console window, user input is under control of the program. The program asks the user for input in a specific order. For example, a program might ask the user to supply first a name, then a dollar amount. But the programs that you use every day on your computer
don’t work like that. In a program with a modern graphical user interface, the user is in control. The user can use both the mouse and the keyboard and can manipulate many parts of the user interface in any desired order. For example, the user can enter information into text fields, pull down menus, click buttons, and drag scroll bars in any order. The program must react to the user commands in whatever order they arrive. Having to deal with many possible inputs in random order is quite a bit harder than simply forcing the user to supply input in a fixed order. In the following sections, you will learn how to write Java programs that can react to user-interface events.

# E
