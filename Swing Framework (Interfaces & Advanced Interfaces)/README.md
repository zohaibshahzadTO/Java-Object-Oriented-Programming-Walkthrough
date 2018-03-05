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

# Listening to Events

Whenever the user of a graphical program types characters or uses the mouse anywhere inside one of the windows of the program, the program receives
a notification that an event has occurred. For example, whenever the mouse moves a tiny interval over a window, a “mouse move” event is generated. Clicking
a button or selecting a menu item generates an “action” event.

Most programs don’t want to be flooded by irrelevant events. For example, when a button is clicked with the mouse, the mouse moves over the button, then the mouse button is pressed, and finally the button is released. Rather than receiving all these mouse events, a program can indicate that it only cares about button clicks, not about the underlying mouse events. On the other hand, if the mouse input is used for drawing shapes on a virtual canvas, a program needs to closely track mouse events.

Every program must indicate which events it needs to receive. It does that  installing event listener objects. These objects are instances of classes that you must provide. The methods of your event listener classes contain the instructions that you want to have executed when the events occur.

To install a listener, you need to know the event source. The event source is the
user-interface component, such as a button, that generates a particular event. You add an event listener object to the appropriate event sources. Whenever the event occurs, the event source calls the appropriate methods of all attached event listeners.

This sounds somewhat abstract, so let’s run through an extremely simple program
that prints a message whenever a button is clicked. Button listeners must belong to a class that implements the ActionListener interface:

  *public interface ActionListener {
    void actionPerformed(ActionEvent event);
  }*

This particular interface has a single method, *actionPerformed*. It is our job to supply a class whose *actionPerformed* method contains the instructions that we want executed whenever the button is clicked. The following is a very simple example of such as a listener class:

  *See ClickListener.java file*

We ignore the event parameter variable of the actionPerformed method—it contains
additional details about the event, such as the time at which it occurred. Note that the event handling classes are defined in the java.awt.event package. (AWT is the Abstract Window Toolkit, the Java library for dealing with windows and events.)

Once the listener class has been declared, we need to construct an object of the class and add it to the button:

  *ActionListener listener = new ClickListener();
   button.addActionListener(listener);*

Whenever the button is clicked, the Java event handling library calls:

  *Listener.actionPerformed(event);*

As a result, the message is printed.

# Using Inner Classes for Listeners

In the preceding section, you saw how to specify button actions. The code for the
button action is placed into a listener class. It is common to implement listener classes as inner classes like this:


    *public class ButtonFrame2 extends JFrame
    {
    . . .
      // This inner class is declared inside the frame class
      class ClickListener implements ActionListener
      {
        . . .
      }

      private void createComponents()
      {
        button = new JButton("Click me!");
        ActionListener listener = new ClickListener();
        button.addActionListener(listener);
      . . .
      }
    }*

An inner class is simply a class that is declared inside another class.
There are two advantages to making a listener class into an inner class. First, listener classes tend to be very short. You can put the inner class close to where it is needed, without cluttering up the remainder of the project. Moreover, inner classes have a very attractive feature: Their methods can access instance variables and methods of the surrounding class.

This feature is particularly useful when implementing event handlers. It allows
the inner class to access variables without having to receive them as constructor or method arguments.

Let’s look at an example. Instead of printing the message “I was clicked”, we want
to show it in a label. If we make the action listener into an inner class of the frame class, its actionPerformed method can access the label instance variable and call the setText method, which changes the label text.

    *public class ButtonFrame2 extends JFrame
    {
      private JButton button;
      private JLabel label;
      . . .
      class ClickListener implements ActionListener
      {
        public void actionPerformed(ActionEvent event)
        {
          // Accesses label variable from surrounding class
          label.setText("I was clicked");
        }
      }
      . . .
    }*


# Application: Showing Growth of an Investment

In this section, we will build a practical application with a graphical user interface. A frame displays the amount of money in a bank account. Whenever the user clicks a button, 5 percent interest is added, and the new balance is displayed.

We need a button and a label for the user interface. We also need to store the current balance:

  *public class InvestmentFrame extends JFrame {
    private JButton button;
    private JLabel resultLabel;
    private double balance;

    private static final double INTEREST_RATE = 5;
    private static final double INITIAL_BALANCE = 1000;
    ...    
  }*

We initialize the balance when the frame is constructed. Then we add the button and label to a panel. and the panel to the frame:

  *public InvestmentFrame() {
    balance = INITIAL_BALANCE;

     createComponents();
     setSize(FRAME_WIDTH, FRAME_HEIGHT);
  }*

Now we are ready for the hard part—the event listener that handles button
clicks. As in the preceding section, it is necessary to declare a class that implements the ActionListener interface, and to place the button action into the actionPerformed method. Our listener class adds interest and displays the new balance:

  *class AddInterestListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
        double interest = balance * INTEREST_RATE / 100;
        balance = balance + interest;
        resultLabel.setText("Balance: " + balance);
      }
  }*

We make this class an inner class so that it can access the balance and resultLabel instance variables. Finally, we need to add an instance of the listener class to the button:

  *private void createComponents() {
    button = new JButton("Add Interest");
    ActionListener listener = new AddInterestListener();
    button.AddInterestListener(listener);
    ...
  }*

  See the complete program in the InvestmentFrame.java file within the repository.


# Processing Text Input

We continue our discussion with graphical user interfaces that accept text input. Of course, a graphical application can receive text input by calling the showInputDialog method of the JOptionPane class, but popping up a separate dialog box for each input is not a natural user interface. Most graphical programs collect text input through text components. In the following two sections, we will learn how to add text components to a graphical application, and how to read what the user types into them.

# Text fields

The *JTextField* class provides a text field for reading a single line of text. When we construct a text field, we need to supply the width - the approximate number of characters that we expect the user to type.

  *final int FIELD_WIDTH = 10;
   rateField = new JTextField(FIELD_WIDTH);*

Users can type additional characters, but then a part of the contents of the field becomes invisible. We'll want to label each text field so that the user knows what to type into it. Construct a JLabel object for each label.

  *JLabel rateLabel = new JLabel("Interest rate: ");*

You want to give the user an opportunity to enter all information into the text fields before processing it. Therefore, you should supply a button that the user can press to indicate that the input is ready for processing.

When that button is clicked, its actionPerformed method should read the user input
from each text field, using the getText method of the JTextField class. The getText method returns a String object. In our sample program, we turn the string into a number, using the Double.parseDouble method. After updating the account, we show the balance in another label.

  *class AddInterestListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      double rate = Double.parseDouble(rateField.getText());
      double interest = balance * rate / 100;
      balance = balance + interest;
      resultLabel.setText("Balance: " + balance);
    }
  }*

# Text Areas

In the preceding section, you saw how to construct text fields. A text field holds a single line of text. To display multiple lines of text, use the JTextArea class.

When constructing a text area, you can specify the number of rows and columns:

  *final int ROWS = 10; // lines of text
   final int COLUMNS = 30; // characters in each row
   JTextArea textArea = new JTextArea(ROWS, COLUMNS);*

Use the setText method to set the text of a text field or text area. The append method adds text to the end of a text area. Use newline characters to seperate lines, like this:

  *textArea.append(balance + "\n");*

If we want to use a text field or text area for display purposes only, call the set-Editable method like this:

  *textArea.setEditable(false);*

Now the user can no longer edit the contents of the field, but the program can still call setText and append to change it.

To add scroll bars to a text area, use JScrollPane, like this:

  *JTextArea textArea = new JTextArea(ROWS, COLUMNS);
   JScrollPane scrollPane = new JScrollPane(textArea);*

Then add the scroll pane to the panel.
