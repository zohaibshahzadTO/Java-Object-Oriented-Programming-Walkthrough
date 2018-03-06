# Advanced User Interfaces

In this section, we'll learn how to use the most common user-interface components in the Java Swing toolkit, and how to search the Java documentation for information about other components. We'll also delve into more event handling, so we can use timer events in animations and process mouse events in GUI.

# Layout Management

Up to now, you have had limited control over the layout of user-interface components. We learned how to add components to a panel, and the panel arranged the components from left to right. However, in many applications, you need more sophisticated arrangements.

In Java, you build up user interfaces by adding components into containers such as panels.
Each container has its own layout manager, which determines how components are laid out.
By default, a JPanel uses a flow layout. A flow layout simply arranges its components from left to right and starts a new row when there is no more room in the current row.

Another commonly used layout manager is the border layout. The border layout groups components into five areas: center, north, south, west, and east. Each area can hold a single component, or it can be empty. The border layout is the default layout manager for a frame (or, more technically, the frame’s content pane). But you can also use the border layout in a panel:

  *panel.setLayout(new BorderLayout());*

Now the panel is controlled by a border layout, not the flow layout. When adding a
component, you specify the position, like this:

  *panel.add(component, BorderLayout.NORTH);*

  The grid layout manager arranges components in a grid with a fixed number of rows and columns. All components are resized so that they all have the same width and height. Like the border layout, it also expands each component to fill the entire allotted area. (If that is not desirable, you need to place each component inside a panel.) To create a grid layout, you supply the number of rows and columns in the constructor, then add the components, row by row, left to right:


      *JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new GridLayout(4, 3));
       buttonPanel.add(button7);
       buttonPanel.add(button8);
       buttonPanel.add(button9);
       buttonPanel.add(button4);*

Sometimes you want to have a tabular arrangement of the components where columns have different sizes or one component spans multiple columns. A more complex layout manager called the grid bag layout can handle these situations. The grid bag layout is quite complex to us.

Fortunately, you can create acceptable-looking layouts in nearly all situations by nesting panels. You give each panel an appropriate layout manager. Panels don’t have visible borders, so you can use as many panels as you need to organize your components. The keypad buttons are contained in a panel with grid layout. That panel is itself contained in a larger panel with border layout. The text field is in the northern position of the larger panel.


# Choices

Now we'll see see how to present a finite set of choices to the user. Which Swing component you use depends on whether the choices are mutually exclusive or not, and on the amount of space you have for displaying the choices.

# Radio buttons

If the choices are mutually exclusive, use a set of radio buttons. In a radio button set, only one button can be selected at a time. When the user selects another button in the same set, the previously selected button is automatically turned off. (These buttons are called radio buttons because they work like the station selector buttons on a car radio: If you select a new station, the old station is automatically deselected.)

To create a set of radio buttons, first create each button individually, and then add
all buttons in the set to a ButtonGroup object:

  *JRadioButton smallButton = new JRadioButton("Small");
   JRadioButton mediumButton = new JRadioButton("Medium");
   JRadioButton largeButton = new JRadioButton("Large");

   ButtonGroup group = new ButtonGroup();
   group.add(smallButton);
   group.add(mediumButton);
   group.add(largeButton);*

Note that the button group does not place the buttons close to each other in the container.
The purpose of the button group is simply to find out which buttons to turn off when one of them is turned on. It is still your job to arrange the buttons on the screen
