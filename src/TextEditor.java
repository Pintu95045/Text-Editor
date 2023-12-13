// Importing necessary libraries
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Declaration of the TextEditor class
public class TextEditor implements ActionListener {

    // Declaring properties of TextEditor
    JFrame frame;         // Frame for the text editor
    JMenuBar menuBar;      // Menu bar for the frame

    JMenu file, edit;      // Menus for file and edit options

    // File menu items
    JMenuItem newFile, openFile, saveFile;

    // Edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    // TextArea
    JTextArea textArea;

    // Constructor for the TextEditor class
    TextEditor() {
        // Initialize the frame
        frame = new JFrame();

        // Initialize the menu bar
        menuBar = new JMenuBar();

        // Initialize the text area
        textArea = new JTextArea();

        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to the file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add edit menu items to the edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to the menuBar
        menuBar.add(file);
        menuBar.add(edit);

        // Set the menuBar to the frame
        frame.setJMenuBar(menuBar);

        // create content panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        // add text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // add scroll pane to panel
        panel.add(scrollPane);

        // add panel to frame
        frame.add(panel);

        // Set the dimensions of the frame
        frame.setBounds(0, 0, 400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            // Perform cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            // Perform copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            // Perform paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            // Perform selectAll operation
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            // Perform close operation
            System.exit(0);
        }
        if (actionEvent.getSource() == openFile) {
            // Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            // If we click on the open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File file = fileChooser.getSelectedFile();

                // Get the path of the selected file
                String filePath = file.getPath();
                try {
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);

                    // Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate;
                    String output = "";

                    // Read content of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }

                    // Set the output string to text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == saveFile) {
            // initialize the picker
            JFileChooser fileChooser = new JFileChooser("C:");

            // get choose option from file chooser
            int chooseOption = fileChooser.showOpenDialog(null);

            // check if we click on save button
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
            try {
                // Initialize file writer
                FileWriter fileWriter = new FileWriter(file);

                // Initialize Buffered Writer
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // write contents of text area to file
                textArea.write(bufferedWriter);
                bufferedWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (actionEvent.getSource() == newFile) {
            // Open a new TextEditor window
            TextEditor newTextEditor = new TextEditor();
        }
    }

    // Main method to run the TextEditor
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}
