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
The purpose of the button group is simply to find out which buttons to turn off when one of them is turned on. It is still your job to arrange the buttons on the screen/

The isSelected method is called to find out whether a button is currently selected
or not. For example,

  *if (largeButton.isSelected()) { size = LARGE_SIZE; }*

Unfortunately, there is no convenient way of finding out which button in a group is currently selected. You have to call isSelected on each button. Because users will expect one radio button in a radio button group to be selected, call setSelected(true) on the default radio button before making the enclosing frame visible.

If you have multiple button groups, it is a good idea to group them together visually. It is a good idea to use a panel for each set of radio buttons, but the panels themselves
are invisible. You can add a border to a panel to make it visible.

There are a large number of border types. We will show only a couple of variations and leave it to the border enthusiasts to look up the others in the Swing documentation.

The EtchedBorder class yields a border with a three-dimensional, etched effect. You can add a border to any component, but most commonly you apply it to a panel:

  *JPanel panel = new JPanel();
   panel.setBorder(new EtchedBorder());*

If you want to add a title to the border, you need to construct a Titled- Border. You make a titled border by supplying a basic border and then the title you want. Here is a typical example:

  *panel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));*


# Check Boxes

A check box is a user-interface component with two states: checked and unchecked.
You use a group of check boxes when one selection does not exclude another. For
example, the choices for “Bold” and “Italic” in Figure 4 are not exclusive. You can
choose either, both, or neither. Therefore, they are implemented as a set of separate check boxes. Radio buttons and check boxes have different visual appearances. Radio buttons are round and have a black dot when selected. Check boxes are square and have a check mark when selected.

You construct a check box by providing the name in the constructor:

  *JCheckBox italicCheckBox = new JCheckBox("Italic");*

Because check box settings do not exclude each other, you do not place a set of check
boxes inside a button group. As with radio buttons, you use the isSelected method to find out whether a check box is currently checked or not.

# Combo Boxes

If you have a large number of choices, you don’t want to make a set of radio buttons,
because that would take up a lot of space. Instead, you can use a combo box. This
component is called a combo box because it is a combination of a list and a text field.
The text field displays the name of the current selection. When you click on the arrow
to the right of the text field of a combo box, a list of selections drops down, and you
can choose one of the items in the list.

If the combo box is editable, you can also type in your own selection. To make a
combo box editable, call the setEditable method.

You add strings to a combo box with the addItem method.

   *JComboBox facenameCombo = new JComboBox();
    facenameCombo.addItem("Serif");
    facenameCombo.addItem("SansSerif");
    . . .*

You get the item that the user has selected by calling the getSelectedItem method.
However, because combo boxes can store other objects in addition to strings, the get- SelectedItem method has return type Object. Hence, in our example, you must cast the returned value back to String:

    *String selectedString = (String) facenameCombo.getSelectedItem();*

You can select an item for the user with the setSelectedItem method. Radio buttons, check boxes, and combo boxes generate an ActionEvent whenever the user selects an item. In the following program, we don’t care which component was clicked—all components notify the same listener object. Whenever the user clicks on any one of them, we simply ask each component for its current content, using the isSelected and getSelectedItem methods. We then redraw the label with the new font.

# Menus

Anyone who has ever used a graphical user interface is familiar with pull-down menus. At the top of the frame is a menu bar that contains the top-level menus. Each menu is a collection of menu items and submenus.

The sample program for this section builds up a small but typical menu and traps the action events from the menu items. The program allows the user to specify the font for a label by selecting a face name, font size, and font style. In Java it is easy to create these menus.
