# SwingTutorial

Repository for the [Swing Tutorial](http://zetcode.com/tutorials/javaswingtutorial/) for COS 420.

The tutorial includes:

- [x] Introduction
- [x] First programs
- [ ] Menus and toolbars
- [ ] Swing layout management
- [ ] GroupLayout manager
- [ ] Swing events
- [ ] Basic Swing components
- [ ] Basic Swing components II
- [ ] Swing dialogs
- [ ] Swing models
- [ ] Drag and drop
- [ ] Painting
- [ ] Resizable component
- [ ] Puzzle
- [ ] Tetris


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