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
