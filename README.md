# SwingTutorial

Repository for the [Swing Tutorial](http://zetcode.com/tutorials/javaswingtutorial/) for COS 420.

The tutorial includes:

- [x] Introduction
- [x] First programs
- [x] Menus and toolbars
- [x] Swing layout management
- [x] GroupLayout manager
- [x] Swing events
- [x] Basic Swing components
- [x] Basic Swing components II
- [x] Swing dialogs
- [x] Swing models
- [x] Drag and drop
- [x] Painting
- [x] Resizable component
- [x] Puzzle
- [x] Tetris


## First Programs

`JFrame` is a top-level container. The basic purpose of containers is to hold components of the application.

The `invokeLater()` method places the application on the Swing Event Queue. It is used to ensure that all UI updates are concurrency-safe.

`var` allows variable type inference; the type for the local variable will be inferred by the compiler.

Plug an action listener to the button. The action terminates the application:

```
quitButton.addActionListener((event) -> System.exit(0));
```

The content pane of a JFrame is an area where child components are placed. The children are organized by special non-visible components called layout managers.

`GroupLayout` manager defines the layout for each dimension independently. In one step, components are placed alongside the horizontal axis; in the other step, components are placed alongside the vertical axis. Components can be arranged sequentially or in parallel:
- In a horizontal layout, a row of components is called a sequential group and a column of components is called a parallel group.
- In a vertical layout, a column of components is called a sequential group and a row of components is called a parallel group.

`ImageIcon` is used to create the icon. Then, `setIconImage()` sets the image to be displayed as the icon for this window. The `getImage()` returns the icon's `Image`.

```
var webIcon = new ImageIcon("src/resources/web.png");
setIconImage(webIcon.getImage());
```

Tooltips are part of the internal applicatin's help system. Swing shows a small window if we hover a mouse pointer over an object that has a tooltip set.

A content pane is an instance of a `JPanel` component. The `getContentPane()` method returns a `Container` type. Since setting a tooltip requires a `JComponent` instance, the object is cast to a `JPanel`.

```
var pane = (JPanel) getContentPane();
```

`pack()` automatically sizes `JFrame` based on the size of its components. It takes the defined space into account. The window will display the button and the spaces set with the `addGap()` method.

Mnemonics are shortcut keys that activate a component that supports mnemonics. For instance, they can be used with labels, buttons, or menus.

There are three ways to activate the button: a left mouse click, the `Alt+B` shortcut, and the `Space` key (provided the button has the focus). The `Space` key binding was automatically created by Swing. (Under Metal look and feel, the focus is visually represented by a small rectangle around the button's label.)

`setMnemonic()` method sets a keyboard mnemonic for the button. The mnemonic key is specified with a virtual keycode from the `KeyEvent` class. The mnemonic is combined with the look and feel's mouseless modifier (usually `Alt`).

`JLabel` component is a specific component with a default transparent background. In order to paint on a label, `isOpaque()` is overridden, returning true.

The `setBackground()` method sets the background color for a component.

And alternative code to add the labels to the `GroupLayout`, more intuitive althouth more verbose:

```java
// add columns to the horizontal group
GroupLayout.ParallelGroup hGroup = gl.createParallelGroup();
        
GroupLayout.SequentialGroup row1 = gl.createSequentialGroup();
row1.addComponent(labels[0]);
row1.addComponent(labels[1]);
row1.addComponent(labels[2]);
row1.addComponent(labels[3]);
hGroup.addGroup(row1);
        
GroupLayout.SequentialGroup row2 = gl.createSequentialGroup();
row2.addComponent(labels[4]);
row2.addComponent(labels[5]);
row2.addComponent(labels[6]);
row2.addComponent(labels[7]);
hGroup.addGroup(row2);
        
GroupLayout.SequentialGroup row3 = gl.createSequentialGroup();
row3.addComponent(labels[8]);
row3.addComponent(labels[9]);
row3.addComponent(labels[10]);
row3.addComponent(labels[11]);
hGroup.addGroup(row3);
        
GroupLayout.SequentialGroup row4 = gl.createSequentialGroup();
row4.addComponent(labels[12]);
hGroup.addGroup(row4);
        
// add rows to the vertical group
GroupLayout.ParallelGroup vGroup = gl.createParallelGroup();
        
GroupLayout.SequentialGroup col1 = gl.createSequentialGroup();
col1.addComponent(labels[0]);
col1.addComponent(labels[4]);
col1.addComponent(labels[8]);
col1.addComponent(labels[12]);
vGroup.addGroup(col1);
        
GroupLayout.SequentialGroup col2 = gl.createSequentialGroup();
col2.addComponent(labels[1]);
col2.addComponent(labels[5]);
col2.addComponent(labels[9]);
vGroup.addGroup(col2);
        
GroupLayout.SequentialGroup col3 = gl.createSequentialGroup();
col3.addComponent(labels[2]);
col3.addComponent(labels[6]);
col3.addComponent(labels[10]);
vGroup.addGroup(col3);
        
GroupLayout.SequentialGroup col4 = gl.createSequentialGroup();
col4.addComponent(labels[3]);
col4.addComponent(labels[7]);
col4.addComponent(labels[11]);
vGroup.addGroup(col4);
        
gl.setHorizontalGroup(hGroup);
gl.setVerticalGroup(vGroup);
pack();
```

MouseMotionAdapter is used for receiving mouse motion events.

This code overrides the mouseMoved() method of the adapter. From the MouseEvent object, it is possible to get the x and y coordinates of the mouse pointer, build a string and set it to the label.

```java
addMouseMotionListener(new MouseMotionAdapter()
{
    @Override
    public void mouseMoved(MouseEvent e)
    {
        super.mouseMoved(e);
        int x = e.getX();
        int y = e.getY();
        
       var text = String.format("x: %d, y: %d", x, y);
       
       coords.setText(text);
    }
});
```

## Menus and Toolbars

A menu is a group of commands located in a menubar. A toolbar has buttons with some common commands in the application.

A menubar is created with JMenuBar. A menu object is created with JMenu. A menu object consists of menu items. A menu item is created with JMenuItem. JMenuItem is a special kind of a button component and can have an action listener added to it.

The menu item is added to the menu object and the menu object is inserted into the menu bar.

```
fileMenu.add(eMenuItem);
menuBar.add(fileMenu);
```

`setJMenuBar()` method sets the menubar for the JFrame container.

Menus can also have submenus, similar commands can be put into groups. Within a menu, commands can be separated with a separator. The separator is a simple line. In addition to mnemonics, menu commands can be launched via accelerators.

A submenu is just like any other menu. It is created the same way. Just add a menu to existing menu.

A separator is a visual line that visually separates menu items. It can be used to group items into some logical places.

Mnemonics and accelerators are shortcut keys that enable executing commands via keyboard. Mnemonics navigate the menu hierarchy to select a specific menu item whereas accelerators bypass menu hierarchy and directly activate the menu item. To use a mnemonic, the component must be visible on the screen. First, the menu object must be activated, which makes the Exit menu item visible on the screen. An accelerator is a key shortcut that directly launches a menu item. Mnemonics are visually hinted by underlined characters, the accelerators have their shortcut keys shown next to the menu item's label.

Actions use various keys to define their functionality. The putValue() method associates string values with the specified keys.

A JCheckBoxMenuItem is a meny item that can be selected or deselected. If selected, the menu item typically appears with a checkmark next to it. If unselected or deselected, the menu item appears without a checkmark. Like a regular menu item, a check box menu item can have either a text or a graphic icon associated with it, or both.

Choose which letter is going to be underlined.

```
showStatusBarMenuItem.setDisplayedMnemonicIndex(5);
```

`JCheckBoxMenuItem` is a special kind of a button component. It implements the `ItemSelectable` interface. An `ItemListener` can be used to listen to its state changes. The `getStateChange()` determines the type of state change. If the change is `ItemEvent.SELECTED`, the status statusbar is changed.

```
showStatusBarMenuItem.addItemListener((e) ->
{
    if (e.getStateChange() == ItemEvent.SELECTED)
        statusBar.setVisible(true);
    else
        statusBar.setVisible(false);
});
```

JRadioButtonMenuItem enabled to select from a mutually exclusive list of options. Selecting a particular JRadioButtonMenuItem deselects all other items. JRadioButtonMenuItems are placed into a ButtonGroup.

The ButtonGroup is used to create a multiple-exclusion scope for a set of buttons. The JRadioButtonMenuItem must be placed on both the JMenu and the ButtonGroup.

`Box.createHorizontalGlue()` adds a horizontal glue to the menubar. A glue absorbs all the extra space available, pushing the remaining menus to the right of the menubar.

A popup menu is another type of menu. It is also called a context menu and usually shown when we right click on a component. The idea is to provide only the commands that are relevant in the current context. Say we have an image. By right clicking on the image, we get a popup window with commands to save, scale, or move the image.

A popup menu consists of `JMenuItems`. The `getExtendedState()` method determines the state of the frame. The available states are `NORMAL`, `ICONIFIED`, `MAXIMIZED_HORIZ`, `MAXIMIZED_VERT`, and `MAXIMIZED_BOTH`. Once the frame is maximized, the menu item is disabled with `setEnabled()` method.

```
addMouseListener(new MouseAdapter()
{
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (getExtendedState() != JFrame.MAXIMIZED_BOTH)
            maximizeMenuItem.setEnabled(true);
        
        if (e.getButton() == MouseEvent.BUTTON3)
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
});
```

Toolbars provide a quick access to the most frequently used commands. In Java Swing, the JToolBar class creates a toolbar in an application.

A toolbar is created with `JToolBar`. A button inserted into a toolbar is a regular `JButton`. The toolbar is placed to the north area of the `BorderLayout`. `BorderLayout` is the default layout manager for the content pane of a `JFrame`, `JWindow`, `JDialog`, `JInternalFrame`, and `Applet`.

## Swing Layout Management

Java Seing has two kind of components: containers and children. The containers group children into suitable layouts. *Layout managers* are used to create layouts. There are three layout managers that are more suitable in modern UI creation: `MigLayout`, `GroupLayout`, and `FormLayout`. These are powerful, flexible layout managers that can cope with most layout requirements. `FlowLayout`, `GridLayout`, `CardLayout`, `BoxLayout`, `GridBagLayout` are obsolete layout managers that cannot fulfill requirements of a modern UI.

Obsolete layout managers are either too simple or unnecessarily complex. They use fixed gaps between components, which is not portable since the user interface becomes broken once the program is run on different screen resolutions. Obsolete layout managers try to fix their weaknesses by a technique called nesting: developers use several different layout managers in multiple panels. While feasible, it brings additional unnecessary complexity to the code.

The `FlowLayout` manager is the simplest layout manager in the Java Swing toolkit. It is the default layout manager for the `JPanel` component. When calculating its children size, a `FlowLayout` lets each component assume its natural (preferred) size. The manager puts components into a row in the order they were added; if a component does not fit into a row, it goes into the next one. Components can be added from the right to the left or from the left to the right. The manager allows to align the components, but implicitly the components are centered and there is a 5px space between components and components and the edges of the container.

Without setting the preferred size for the `JTextArea` component, the component would have a size of its text. Without the text, the component would not be visible at all. Writing or deleting some text in the component makes it grow and shrink accordingly.

The `GridLayout` layout manager lays out components in a rectangular grid. The container is divided into equally sized rectangles. One component is placed in each rectangle, the components fill the area of each cell. One of the constructors of the `GridLayout` takes four parameters: the number of rows, the number of columns and the horizontal and vertical gaps between components.

`BorderLayout` is the default layout manager for `JFrame`, `JWindow`, `JDialog`, `JInternalFrame`, and `JApplet`. It sets the gaps between its children in pixels, thus creating rigid layouts. This leads to non-portable UI.
 
`BorderLayout` divides the space into five regions: north, west, south, east, and center. Each region can have only one component. To put more components into a region, a panel needs to be put there with its own manager. The components in N, W, S, E regions get their preferred size. The component in the center takes up the whole space left.

It does not look good if child components are too close to each other, space should be added among them. Each component in Swing toolkit can have borders around its edges. To create a border, either create a new instance of an EmptyBorder class or use a BorderFactory.

A top panel is placed into a bottom panel. The bottom panel has the `BorderLayout` manager. The top panel is placed into the center area of the bottom panel's `BorderLayout` manager.

```
var bottomPanel = new JPanel(new BorderLayout());
var topPanel = new JPanel();
...
bottomPanel.add(topPanel);
```

Create a 20px border around the bottom panel:

```
bottomPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
```

`CardLayout` manager treats each component as a card. The container is a stack of these cards. Only one component is visible at a time, the rest is hidden. The first component added to the container is visible by default when the container is initially displayed. This layout manager can be used to create a wizard or a tabbed pane. Calling the `CardLayout`'s `previous()` or `next()` methods flips to the previous or next card of the specified container.

`BoxLayout` manager organizes components in a column or a row. It can create quite sophisticated layouts with nesting. However, this raises the complexity of the layout creation and uses additional resources, notably many other `JPanel` components. `BoxLayout` is only able to create fixed spaces; therefore, its layouts are not portable. The constructor creates a layout manager that will lay out components along the given axis. Unlike other layout managers, `BoxLayout` takes a container instance as the first parameter in the constructor. The second parameter determines the orientation of the manager: to create a horizontal box, the `LINE_AXIS` or `X_AXIS` constants can be used; to create a vertical box, the `PAGE_AXIS` or `Y_AXIS` constants can be used.

The `BoxLayout` manager is often used with the `Box` class. This class creates several invisible components, which affect the final layout: glue, strut, rigid area. `Glue` creates an expandable area; `rigid area` always assume the specified size.

It is possible to go without a layout manager. There might be a few situations where a layout manager is not needed (e.g. positioning some images at some irregular locations). But in most cases to create truly portable, complex applications, layout managers are needed. Without any manager, components are positioned using absolute values. To use absolute positioning, provide null to the `setLayout()` method. (The `JFrame` component has a default layout manager, the `BorderLayout`). The `setBounds()` method positions the component.

## `GroupLayout` manager

This is the only built-in manager that can create multi-platform layouts. All other managers are either very simplistic or use fixed sized gaps that are not suitable for user interfaces on different platforms and screen resolutions. The third-party `MigLayout` can also be used to create multi-platform layouts in Java.
 
`GroupLayout` separates components from the actual layout; all components can be set up in one place and the layout in another one. It defines the layout for each dimension independently. In one dimension, components are placed alongside the horizontal axis; in the other dimension, components are placed along the vertical axis. In both kinds of layouts, components can be arranged sequentially or in parallel. In a horizontal layout, a row of components is called a sequential group and a column of components is called a parallel group. In a vertical layout, a column of components is called a sequential group and a row of components is called a parallel group.

`GroupLayout` uses three types of gaps between components or components and borders: `RELATED` (used for related components), `UNRELATED` (used for unrelated components), and `INDENTED` (used for indents between components). These gaps are resolution independent; i.e. they have different size in pixels on different resolution screens. Other built-in managers use fixes size gaps on all resolutions.

A component is added to the layout manager with the `addComponent()` method. The parameters are the minimum, preferred, and maximum size values. Either specific absolute values can be passed, or the `GroupLayout.DEFAULT_SIZE` or the `GroupLayout.PREFERRED_SIZE` can be provided. The `GroupLayout.DEFAULT_SIZE` indicates that the corresponding size from the component should be used. The `GroupLayout.PREFERRED_SIZE` is determined by calling the component's `getPreferredSize()` method.

Changing a component's maximum size to `GroupLayout.PREFERRED_SIZE` makes it non-expandable in the horizontal direction beyond its preferred size. The difference between the preferred size and the maximum size is the component's tendency to grow. This applies to managers that honor these values.

```
addComponent(field, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
```

In the vertical layout, `createParallelGroupt()` can receive `false` for its second parameter. This way, the component is prevented from growing in the vertical direction.

```
GroupLayout.ParallelGroup pg = gl.createParallelGroup(LEADING, false);
```

Baseline alignment is aligning components along the baseline of the text the they contain. It works for components which renders a textual information (e.g. `JLabel`, `JButton`, etc). With `Alignment.BASELINE` alignment, the `GroupLayout` will align such components so that the baseline of their text stays on the same (horizontal) line (makes for an easier reading, the eye does not need to jump up/down on the virtual line of text).

A stretchable gap can be added to a `sequentialGroup` with the `addPreferredGap()` method call. Its parameters are the type of the gap, the preferred and the minimum sizes of the gap. The difference between the maximum and the preferred values is the ability of the gap to stretch. When both values are the same, the gap has a fixed size.

```
gl.createSequentialGroup().addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
```

To create a form with the labels aligned in the horizontal direction, and vertically aligned to their baseline with their corresponding text fields:

- Horizontally, the layout consists of two parallel groups packed in a sequential group. Labels and fields are put separately into their parallel groups. The parallel group of labels has the `GroupLayout.Alignment.TRAILING` alignment, which makes the labels right aligned.

```        
gl.setHorizontalGroup(gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(TRAILING)
                .addComponent(serviceLbl)
                .addComponent(userNameLbl)
                .addComponent(passwordLbl))
        .addGroup(gl.createParallelGroup()
                .addComponent(field1)
                .addComponent(field2)
                .addComponent(field3))
);
```

- In the vertical layout, the labels are aligned with their text fields to their baseline. To do this, the labels and their corresponding fields are grouped into parallel groups with the `GroupLayout.Alignment.BASELINE` alignment.

```
gl.setVerticalGroup(gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(BASELINE)
                .addComponent(serviceLbl)
                .addComponent(field1))
        .addGroup(gl.createParallelGroup(BASELINE)
                .addComponent(userNameLbl)
                .addComponent(field2))
        .addGroup(gl.createParallelGroup(BASELINE)
                .addComponent(passwordLbl)
                .addComponent(field3))
);
```

## Swing Events

All GUI applications are event-driven. An application reacts to different even types which are generated during its life. Events can be generated by the user of an application or by other means such as an Internet connection, a window manager, or a timer.

In the event model there are three participants: the *event source* (the object whose state changes, generating Events), the *event object* (encapsulates the state changes in the event source), and the *event listener* (the object that wants to be notified). The *event source* object delegates the task of handling an event to the *event listener*. Event handling in Java Swing uses *Event Delegation Model*: the objects that are to be notified when a specific event occurs are specified.

When something happens in an application (click a button, select an item from a list, etc.), an event object is created. There are several types of events, including `ActionEvent`, `TextEvent`, `FocusEvent` and `ComponentEvent`, each created under specific conditions. An event object holds information about an even that has occurred.

The `actionPerformed()` method is invoked when an action occurs. Its parameter is an `ActionEvent` object.

```
public void actionPerformed(ActionEvent e)
```

The `e.getWhen()` method returns time value in milliseconds.

There are several ways event handler can be implemented in Java Swing: anonymous inner class, inner class, derived class.

A button is the event source; it will generate events. An action listener is registered with the button. The events are sent to the event target. The event target in this case is `ActionListener` class; in this code, anonymous inner class is used.

```
var closeBtn = new JButton("Close");
closeBtn.addActionListener(new ActionListener()
{
    @Override
    public void actionPerformed(ActionEvent event)
    {
        System.exit(0);
    }
});
```

An action listener can be defined as an inner class.

```
private class ButtonCloseListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}
```

The `MyButton` class is extended from the `JButton` class. It implements the `ActionListener` interface. This way, the event handling is managed within the `MyButton` class.

```
private class MyButton extends JButton implements ActionListener
{
    public MyButton(String text)
    {
        super.setText(text);
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
    
}
```

A listener can be plugged into several sources.

It is possible to register several listeners for one event:

```
JButton addBtn = new JButton("+");
addBtn.addActionListener(new ButtonListener1());
addBtn.addActionListener(new ButtonListener2());
```
It is possible to remove the registered listeners with the `removeActionListener()` method. A non-anonymous listener must be created if it is to be removed later.

To get the window position, the `ComponentListener` is used. The main class implements the `ComponentListener` interface.

```
addComponentListener(this);
```

An adapter is a convenient class that provides empty implementations for all required methods, so unnecessary coding can be avoided by using adapters and implementing only those methods that are actually needed. There is no adapter for a button click event because there there is only one method to implement: `actionPerformed()`. Adapters can be used in situations where more than one method has to be implemented.

```
addComponentListener(new MoveAdapter());

private class MoveAdapter extends ComponentAdapter
{
    @Override
    public void componentMoved(ComponentEvent e)
    {
        ...
    }
}
```

## Basic Swing Components

Swing components are basic building blocks of an application. Swing has a wide range of various components, including buttons, check boxes, sliders, and list boxes.

`JButton` is an implementation of a push button. It is used to trigger an action if the user clicks on it. `JButton` can display a text, an icon, or both. Many components can be decorated with icons, using the `ImageIcon` class.

`JLabel` is a simple component for displaying text, images or both. It does not react to input events. HTML tags can be used in a `JLabel` component. `JLabel` can take an `ImageIcon` as a parameter. An icon is a fixed-sized image. `ImageIcon` paints an icon from a `GIF`, `JPEG`, or `PNG` image.

`JTextField` is a text component that allows editing of a single line of non-formatted text. The number of columns received by the constructor does not set the number of characters allowed in the field; it is used to calculate the preferred width of the field. The `getDocument()` method fetches the model associated with the editor. Each Swing component has a model, which manages its state or data.

`JPasswordField` is a `JTextField` subclass that does not show the characters that the user types. As a security precaution, a password field stores its value as an array of characters, rather than as a string. The array of characters is returned by the `getPassword()` method. Once the password has been processed, it is recommended to set the array's elements to zero.

```
var passwd = passField.getPassword();
...
Arrays.fill(passwd, '0');
```

## Basic Swing Components II

`JCheckBox` is a box with a label that has two states: on and off. If the check box is selected, it is represented by a tick in a box. With `JCkecBox`, it is possible to use an `ActionListener` or an `ItemListener` (usually the `ItemListener` is used). `ItemListener` is the interface for receiving item events. The class that is interested in processing an item event, e.g. the observer, implements this interface. The observer object is registered with a component using the component's `addItemListener()` method. When an item selection event occurs, the observer's `itemStateChange()` method is invoked.

The `ItemEvent`'s `getStateChange()` method determines the state of the component. `ItemEvent` is a semantic event which indicates that an item was selected or deselected. It is sent to the registered observer.

`JRadioButton` allows the user to select a single exclusive choice from a group of options. It is used with the `ButtonGroup` component. When a radio button is selected, two events are actually triggered: one for selection and one for deselection.

`JSlider` is a component that lets the user graphically select a value by sliding a knob within a bounded interval. Moving the slider's knob, the `stateChanged()` method of the slider's `ChangeListener` is called. A `ChangeEvent` is triggered when the slider has changed in some way. `JSlider` can optionally show tick marks for the range of its values. The tick marks are controlled with the `setMinerTickSpacing()`, `setMajorTickSpacing()`, and `setPaintTicks()` methods.

`JComboBox` is a component that combines a button or editable field and a drop-down list. The user can select a value from the drop-down list, which appears at the user's request. If the combo box is editable, then the combo box includes an editable field into which the user can type a value. The combo box uses its `ItemListener` to detect changes. The `itemStateChanged()` is invoked when an item has been selected or deselected by the user.

A progress bar is a components that is used when we process lengthy tasks. It is animated so that the user knows that the task is progressing. The `JProgressBar` component provides a horizontal or a vertical progress bar. The initial and minimum value are 0 and the maximum is 100.

`JToggleButton` is a button that has two states: pressed and not pressed. Clicking on it toggles between these two states.

`JList` is a component that displays a list of objects. It allows the user to select one or more items. Events in list selection are grouped. Events for both selecting and deselecting of items are received. To filter only the selecting events, use the `getValueIsAdjusting()` method. `JList` can have more items than it is physically possible to show on the window; it is not scrollable by default but it can be put into a `JScrollPane` to make it scrollable.

`JTabbedPane` is a component that lets a user switch between a group of components by clicking on a tab. Create a new tab with the `addTab()` method. The first parameter is the title displayed by the tab. The second parameter is a component to be displayed when the tab is clicked.

A `JTextArea` is a multiline text area that displays plain text. It is lightweight component for working with text. The component does not handle scrolling. For this task, use a `JScrollPane` component. The `setLineWrap()` method makes the lines wrapped if they are too long to fit the text area's width. The `setWrapStyleWord()` method specifies how line is going to be wrapped.

`JTextPane` component is a more advanced component for working with text. The component can do some complex formatting operations over the text. It can also display HTML documents. The component does not handle scrolling.


## Swing Dialogs

A dialog is defined as a conversation between two or more persons. In a computer application, a dialog is a window which is used to "talk" to the application. A dialog is used to input data, modify data, change the application settings, etc. Dialogs are important means of communication between a user and a computer program.

In Java Swing, two kinds of dialog can be created: standard dialogs and custom dialogs. *Custom dialogs* are created by programmers based on the `JDialog`. *Standard dialogs* are predefined dialogs available in the Swing toolkit (`JColorChooser`, `JFileChooser`, etc.) for common programming tasks like showing text, receiving input, loading and saving files, etc.

There are two basic types of dialogs: modal and modeless. *Modal* dialogs block input to other top-level windows (e.g. open file dialog). *Modeless* dialogs allow input to other windows (e.g. find text dialog).

Message dialogs are simple dialogs that provide information to the user. Message dialogs are created with the `JOptionPane.showMessageDialog()` method, providing the dialog's parent, message text, title and message type (`ERROR_MESSAGE`, `WARNING_MESSAGE`, `QUESTION_MESSAGE`, `INFORMATION_MESSAGE`). The displayed icon depends on the message type.

The custom dialog is based on the `JDialog` class. The `setModalityType()` method set the modality type of the dialog. The `ModalityType.APPLICATION_MODAL` blocks input from all top-level windows of the same application.

The `setDefaultCloseOperation()` sets what happens when the user clicks on the windoe's `Close` button. The dialog will be hidden and disposed.

```
setDefaultCloseOperation(DISPOSE_ON_CLOSE);
```

The `setLocationRelativeTo()` method centers the dialog window over the area of the frame window.

```
setLocationRelativeTo(getParent());
```

The dialog window is shown on the screen with the `setVisible()` method.

```
private void showAboutDialog()
{
    var aboutDialog = new AboutDialog(this);
    aboutDialog.setVisible(true);
}
```

`JFileChooser` is a standard dialog for selecting a file from the file system. The `showDialog()` method displays the dialog on the screen. The `JFileChooser.APPROVE_OPTION` is returned when the `Yes` or `OK` buttons are clicked.

`JColorChooser` is a standard dialog for selecting a color. The `showDialog()` method returns the selected color value.

## Swing Models

The traditional Model View Controller (MVC) design pattern divides an application into three parts:

- A *model*: represents the data in the application.
- A *view*: the  visual representation of the data.
- A *controller*: processes and responds to events (user actions) and may invoke changes on the model.

The idea is to separate the data access and business logic from data presentation and user interaction by introducing an intermediate component: the controller.

Swing toolkit implements a modified MVC pattern that enabled efficient handling of data and using plugglable look and feel at runtime. It has a single *UI object* for both the view and the controller. This is also called a *separable model architecture*.

Every Swing component has its model. There are two types of models in Swing toolkit:

- State models: handle the state of the component (e.g. the model keeps track whether the component is selected or pressed).
- Data models: handle the data (e.g. a list component keeps a list of items that it is displaying).

Swing components often need to get a model instance in order to manipulate the data in the component. However, for convenience, some methods return data without the need for a programmer to access the model; it would be an overkill to work with models directly in simple situations, so Swing provides some convenience methods. For example, the access to the JSlider model is done behind the scenes in the `getValue()` method:

```
public int getValue()
{ 
    return getModel().getValue(); 
}
```

To query the state of the model, there are two types of notifications:

- Lightweight notifications: uses a `ChangeListener` class. There is only one single event (`ChangeEvent`) for all notifications coming from the component.
- Stateful notification: used for more complex components, there are different kinds of events (for example, the JList component has the `ListDataEvent` and the `ListSelectionEvent`).

If a model is not set for a component, a default one is created. For example, the `JButton` component has a `DefaultButtonModel` model that is created at the construction of the component:

```
public JButton(String text, Icon icon)
{
    // Create the model
    setModel(new DefaultButtonModel());
    
    // initialize
    init(text, icon);
}
```

The model is used for various kinds of buttons, like push buttons, check boxes, radio boxes and for menu items. A ChangeListener is used to listen for buttons state changes. Get the default button model and query the model whether the button is enabled. The label is updated accordingly:

```
var model = (DefaultButtonModel) okBtn.getModel();

if (model.isEnabled())
```

Several components have two models; `JList` is one of them. It has the `ListModel`, which handles data, and the `ListSelectionModel`, which works with the selection state of the list. The `add()`, `remove()`, and `clear()` methods of the list data model can be used to work with the data. A list selection model can be used in order to find out the selected item.

A document model is a good example of a separation of a data from the visual representation. In a `JTextPane` component, a `StyleDocument` is used for setting the style of the text data.


## Drag and Drop

In computer graphical user interfaces, drag-and-drop is the action of clicking on a virtual object and dragging it to a different location or onto another virtual object. It can be used to invoke many kinds of actions or create various types of associations between two abstract objects. It enables users to do complex things intuitively.

Usually, two things can be dragged and dropped: data (e.g. an image) or a graphical object (e.g. a Firefox tab). The component where the drag operation begins must have a `DragSource` object registered; a `DropTarget` is an object responsible for accepting drops; a `Transferable` encapsulates data being transfered; a `DataFlavor` object provides information about the data being transferred.

Several Swing components have already a built-in support for drag-and-drop operations. In such cases, a `TransferHandler` can be used to manage the drag-and-drop functionality. In situations where there is no built-in support, everything must be created from scratch.

The JTextField has a built-in support for dragging that must be enabled:

```
field.setDragEnabled(true);
```

The `TransferHandler` is a class responsible for transferring data between components. The constructor takes a property name as a parameter:

```
button.setTransferHandler(new TransferHandler("text"));
```

Some Java Swing components, such as `JLabel`, do not have built-in drag support. This functionality must be coded. The drag support is not enabled by default for the label; this can be done by registering a custom mouse adapter.

```
var listener = new DragMouseAdapter();
label1.addMouseListener(listener);
```

The `TransferHandler` is needed for both drag sources and drag targets.

To initiate the drag support, get the drag source, get its `TransferHandler` object, and initiate the drag support with the `exportAsDrag()` method call.

```
private class DragMouseAdapter extends MouseAdapter
{
    public void mousePressed(MouseEvent e)
    {
        var c = (JComponent) e.getSource();
        var handler = c.getTransferHandler();
        handler.exportAsDrag(c, e, TransferHandler.COPY);
    }
}
```

Some components, such as the `JList`, do not have a default drop support. There is a good reason for this: it is not known if the data will be inserted into one row, or two or more rows. The drop support for the list component must be implemented manually.

The `DropMode.INSERT` specifies that new items are going to be inserted into the list component, i.e. new items are dropped onto the existing ones:

```
myList.setDropMode(DropMode.INSERT);
```

Set a custom transfer handler class:

```
myList.setTransferHandler(new ListHandler());
```

Tests the suitability of a drop operation. Filter out the clipboard past operations and allow only String drop operations. If the method returns false, the drop operation is cancelled.

```
public boolean canImport(TransferSupport support)
{
    if (!support.isDrop())
        return false;
            
    return support.isDataFlavorSupported(DataFlavor.stringFlavor);
}
```

The `importData()` method transfers the data from the clipboard or from the drag-and-drop operation to the drop location. The `Transferable` is the class where the data is bundled:

```
var transferable = support.getTransferable();
```

Several classes are needed to create a drag gesture: `DragSource`, `DragGestureEvent`, `DragGestureListener`, `Transferable`. The drag gesture is created when a component is clicked and the mouse pointer is moved while the button is pressed. `DragGestureListener` interface listens for drag gestures; `Transferable` interface handles data for a transfer operation.

Create a `DragSource` object and register it for a `JPane`. The `DragSource` is the entity responsible for the initiation of the drag-and-drop operation. The `createDefaultDragGestureRecognizer()` associates a drag source and the `DragGestureListener` with a particular component.

The `dragGestureRecognized()` method responds to a drag gesture. The `startDrag()` method of the `DragGestureEvent` finally starts the drag operation. Two parameters are specified: the cursor type and the `Transferable` object.

Register a drop target listener:

```
var mtl = new MyDropTargetListener(rightPanel);
```

If the conditions for a drag-and-drop operation are fulfilled, accept the drop with the specified action. Otherwise, reject it:

```
event.acceptDrop(DnDConstants.ACTION_COPY);
...
event.rejectDrop();
```

Create a new `DataFlavor` object:

```
protected static final DataFlavor colorFlavor = new DataFlavor(Color.class, "A Color Object");
```

Specify what data flavors are supported (a custom defined color flavor and a predefined `DataFlavor.stringFlavor`):

```
protected static final DataFlavor[] supportedFlavors = { colorFlavor, DataFlavor.stringFlavor };
```

Return an object for a specific data flavor:

```
public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
{
    if (flavor.equals(colorFlavor))
        return color;
    else if (flavor.equals(DataFlavor.stringFlavor))
        return color.toString();
    else 
        throw new UnsupportedFlavorException(flavor);
}
```

## Painting

Swing's painting system is able to render vector graphics, images and outline font-based text. Painting is needed in applications when it is desirable to change or enhance an existing widget, or when creating a custom widget from scratch. To do the painting, the painting API provided by the Swing toolkit is used. The painting is done within the `paintConponent()` method. In the painting process, the `Graphics2D` object is used.

There are two different computer graphics:

- Vector: uses geometrical primitives (such as points, lines, polylines, polygons, circles, ellipses, Splines) to represent images. These primitives are created using mathematical equations.
- Raster: represents images as a collection of pixels.

The advantages of vector graphics over raster graphics include smaller size, ability to zoom indefinitely, and moving, scaling, filling or rotating does not degrade the quality of an image.

The most simple graphics primitive is point: it is a single dot on the window. There is no method to draw a point in Swing, instead the `drawLine()` method is used receiving the same point twice. Custom painting is performed inside the `paintContainer()` method. The `super.paingComponent()` method calls the method of the parent class, which does some necessary work to prepare the component for drawing.

Painting in Swing is done on the `Graphics2D` object.

```
private void doDrawing(Graphics g)
{
    var g2d = (Graphics2D) g;
    ...
}
```

The size of the window includes borders and titlebar. Painting is not done there. Calculate the area where the points will be effectively painted:
 
```
var size = getSize();
var insets = getInsets();

int w = size.width - insets.left - insets.right;
int h = size.height - insets.top - insets.bottom;
```

A line is a simple graphics primitive. It is drawn using two points. The stroke is created using the `BasicStroke` class. It defines a basic set of rendering attributes for the outlines of graphics primitives: line width, end caps, line joins, miter limit, dash, and the dash phase.

To draw rectangles, use the `drawRect()` method. To fill rectangles with the current color, use the `fillRect()` method.

A texture is a bitmap image applied to a shape. To work with textures in Java 2D, use the `TexturePaing` class.

`BufferedImage` is a rectangle of pixels stored in memory. It is one of the most important image types in Swing. Many Swing methods return a `BufferedImage` to work with.

Read the image into the buffered image using `ImageIO.read()` method. It takes a `File` object and returns a `BufferedImage`:

```
slate = ImageIO.read(new File("src/Resources/painting/slate.png"));
```

Create a `TexturePaint` class out of the buffered image:

```
var slateTp = new TexturePaint(slate, new Rectangle(0, 0, 90, 60));
```

Fill the rectangle with the texture:

```
g2d.setPaint(slateTp);
g2d.fillRect(10, 15, 90, 60);
```

Fill the rectangle with the texture:

```
g2d.setColor(new Color(125, 167, 116));
g2d.setPaint(slateTp);
g2d.fillRect(10, 15, 90, 60);
```

Fill the rectangle with a solid color:

```
g2d.setPaint(slateTp);
g2d.setColor(new Color(125, 167, 116));
g2d.fillRect(10, 15, 90, 60);
```

In computer graphics, gradient is a smooth blending of shades from light to dark or from one color to another. In 2D drawing programs and paint programs, gradients are used to create colorful backgrounds and special effects, as well as to simulate lights and shadows. To work with gradients, use Java Swing's `GradientPaint` class. By manipulating the color values and the starting and ending points, different types of gradients can be obtained. The gradient is activated calling the `setPaint()` method.

Drawing text is done with the `drawString()` method. The string that is to be drawn and the position of the text on the window area are specified.

An image is an array of pixels, each pixel representing a color at a given position. Components, such as `JLabel`, can be used to display an image, or the image can be drawn using the Java 2D API. Using the `ImageIcon` class simplifies the work with the images in Java Swing. The image is drawn on the component using the `drawImage()` method.

## Resizable Component

In order to create a component that can be freely dragged over a panel, a panel with absolute positioning enabled must be used. The `setBounds()` method relocates and resizes a component. The `revalidate()` method causes the component to be redrawn.

## Puzzle

The goal for this game is to form a picture. Buttons containing images are moved by clicking on them. Only buttons adjacent to the empty button can be moved.

When a mouse is hovered over the button, its border changes to yellow color:

```
addMouseListener(new MouseAdapter()
{
    @Override
    public void mouseEntered(MouseEvent e)
    {
        setBorder(BorderFactory.createLineBorder(Color.yellow));
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
        setBorder(BorderFactory.createLineBorder(Color.gray));
    }
});
```

`CropImageFilter` is used to cut a rectangular shape from the already resized image source. It is meant to be used in conjunction with a `FilteredImageSource` object to produce cropped versions of existing images:

```
image = createImage(new FilteredImageSource(resized.getSource(), new CropImageFilter(j * width / 3, i * height / 4, width / 3, height / 4)));
```

Buttons are identified by their `position` client property:

```
button.putClientProperty("position", new Point(i, j));
```

The original image is resized by creating a new `BufferedImage` with new dimensions. It paints from the original image into this new buffered image:

```
private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type)
{
    var resizedImage = new BufferedImage(width, height, type);
    var g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, width, height, null);
    g.dispose();
    
    return resizedImage;
}
```

First, all components are removed with the `removeAll()` method. A for loop is used to go through the buttons list to add the reordered buttons back to the panel's layout manager. Finally, the `validate()` method implements the new layout:

```
private void updateButtons()
{
    panel.removeAll();
    for (JComponent btn : buttons)
        panel.add(btn);
    
    panel.validate();
}
```

## Tetris

Java enums are full classes. The `ordinal()` method returns the current position of the enum type in the enum object.

The game is controlled with a keyboard. The control mechanism is implemented with a `KeyAdapter`. If, for example, the left key is pressed, the falling piece is attempted to be moved to the left:

```
@Override
public void keyPressed(KeyEvent e)
{
    ...
    int keycode = e.getKeyCode();
    ...
    switch (keycode)
    {
        case KeyEvent.VK_LEFT:
            tryMove(curPiece, curX - 1, curY);
            break;
    }
}
```
