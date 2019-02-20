/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c_w;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
        

/**
 *
 * @author student
 */
class Student {

    private String name;
    private String DoB;
    private String spec;
    private int course;
    private int scolars;

    public Student(String name, String DoB, String spec, int course, int scolars) {
        this.name = name;
        this.DoB = DoB;
        this.spec = spec;
        this.course = course;
        this.scolars = scolars;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDoB(String DoB) {
        this.DoB = DoB;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setScolars(int scolars) {
        this.scolars = scolars;
    }

    public String getName() {
        return name;
    }

    public String getDoB() {
        return DoB;
    }

    public String getSpec() {
        return spec;
    }

    public int getCourse() {
        return course;
    }

    public int getScolars() {
        return scolars;
    }
    public void writeData(PrintWriter pw)
    {
        pw.println(name+'|'+DoB+'|'+spec+'|'+course+'|'+scolars);
    }
    public String print()
    {
        return name+'|'+DoB+'|'+spec+'|'+course+'|'+scolars;
    }
}
public class C_w extends Application{
    /**
     * @param args the command line arguments
     */
    ArrayList<Student> st_recordList = new ArrayList<Student>();
    ArrayList<Student> st_readList = new ArrayList<Student>();
    File file;
    public static void main(String[] args) throws IOException { 
        launch(args);
    }
    private void SaveFileDialog(Stage primaryStage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Выберите файл");
    FileChooser.ExtensionFilter extFilter = 
    new FileChooser.ExtensionFilter("txt files(*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);
    file = fileChooser.showSaveDialog(primaryStage);
    }
    private void OpenFileDialog(Stage primaryStage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Выберите файл");
    FileChooser.ExtensionFilter extFilter = 
    new FileChooser.ExtensionFilter("txt files(*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);
    file = fileChooser.showOpenDialog(primaryStage);
    }
    private void ErrorAlert(String str)
    {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка!");
        alert.setHeaderText(str);
        alert.setContentText(null);
        alert.showAndWait(); 
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Курсова робота");
        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(0, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(15);
        Scene scene = new Scene(gridPane,1024,600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        //Label
        Label  lblName = new Label("П.І.Б. студента");
        Label lblDoB = new Label("Дата народження");
        Label lblSpec = new Label("Спеціальність");
        Label lblCourse = new Label("Курс");
        Label lblScolars = new Label("Розмір стипендії, грн");
        Label lblElements = new Label("Кількість записів у буфері: 0");
        Label lblChangeCount = new Label("Кількість замін: 0");
        
        
        //TextField
        TextField textName = new TextField();
        textName.setPromptText("Введіть П.І.Б. студента ");
        TextField textDoB = new TextField();
        textDoB.setPromptText("Введіть дату народження");
        TextField textSpec = new TextField();
        textSpec.setPromptText("Введіть спеціальність навчання студента");
        TextField textCourse = new TextField();
        textCourse.setPromptText("Введіть курс навчання");
        TextField textScolars = new TextField();
        textScolars.setPromptText("Введіть розмір стипендії");
        
        //Button
        Button btnAdd = new Button("Додати у буфер");
        Button btnAccept = new Button("Записати у файл");
        
        //Label setting
        lblName.setFont(new Font("Calibri",14));
        lblDoB.setFont(new Font("Calibri",14));
        lblSpec.setFont(new Font("Calibri",14));
        lblCourse.setFont(new Font("Calibri",14));
        lblScolars.setFont(new Font("Calibri",14));
        lblElements.setFont(new Font("Calibri",14));
        lblChangeCount.setFont(new Font("Calibri",14));
        
        //TextField setting
        textName.setPrefSize(200, 20);
        textDoB.setPrefSize(200, 20);
        textSpec.setPrefSize(200, 20);
        textCourse.setPrefSize(200, 20);
        textScolars.setPrefSize(200, 20);
        
        //Button setting
        btnAdd.setFont(new Font("Calibri",14));
        btnAdd.setPrefSize(200, 20);
        btnAccept.setFont(new Font("Calibri",14));
        btnAccept.setPrefSize(200, 20);
        
        TitledPane tpane = new TitledPane();
        GridPane gridTPanel = new GridPane();
        gridTPanel.setPadding(new Insets(10,20,20,20));
        gridTPanel.setVgap(5);
        gridTPanel.setHgap(10);
        gridTPanel.setStyle("-fx-background-color: #56CDC7");
        
        //Name
        GridPane.setConstraints(lblName, 0, 1);
        GridPane.setHalignment(lblName, HPos.CENTER);
        gridTPanel.getChildren().add(lblName);
        GridPane.setConstraints(textName, 0, 2);
        gridTPanel.getChildren().add(textName);
        
        GridPane.setConstraints(lblDoB, 1, 1);
        GridPane.setHalignment(lblDoB, HPos.CENTER);
        gridTPanel.getChildren().add(lblDoB);
        GridPane.setConstraints(textDoB, 1, 2);
        gridTPanel.getChildren().add(textDoB);
        
        GridPane.setConstraints(lblSpec, 2, 1);
        GridPane.setHalignment(lblSpec, HPos.CENTER);
        gridTPanel.getChildren().add(lblSpec);
        GridPane.setConstraints(textSpec, 2, 2);
        gridTPanel.getChildren().add(textSpec);
        
        GridPane.setConstraints(lblCourse, 3, 1);
        GridPane.setHalignment(lblCourse, HPos.CENTER);
        gridTPanel.getChildren().add(lblCourse);
        GridPane.setConstraints(textCourse, 3, 2);
        gridTPanel.getChildren().add(textCourse);
        
        GridPane.setConstraints(lblScolars, 4, 1);
        GridPane.setHalignment(lblScolars, HPos.CENTER);
        gridTPanel.getChildren().add(lblScolars);
        GridPane.setConstraints(textScolars, 4, 2);
        gridTPanel.getChildren().add(textScolars);
        
        GridPane.setConstraints(btnAdd, 0, 5);
        gridTPanel.getChildren().add(btnAdd);
        GridPane.setConstraints(lblElements, 1, 5);
        GridPane.setColumnSpan(lblElements, 3);
        GridPane.setHalignment(lblElements, HPos.CENTER);
        gridTPanel.getChildren().add(lblElements);
        GridPane.setConstraints(btnAccept, 4, 5);
        gridTPanel.getChildren().add(btnAccept);
        
        tpane.setCollapsible(false);
        tpane.setText("Дані для заповнення");
        tpane.setContent(gridTPanel);
        
        GridPane.setConstraints(tpane, 0, 1);
        GridPane.setColumnSpan(tpane, 5);
        gridPane.getChildren().add(tpane);
        
        //TextArea
        TextArea textArea = new TextArea();
        textArea.setPrefSize(775, 400);
        textArea.setPromptText("Результат роботи");
        textArea.setEditable(false);
        
        //Button
        Button btnRead = new Button("Читання з файлу");
        btnRead.setFont(new Font("Calibri",14));
        btnRead.setPrefSize(200, 20);
        
        Button btnClear = new Button("Очистка");
        btnClear.setPrefSize(200, 20);
        btnClear.setFont(new Font("Calibri",14));
        
        Button btnSearch = new Button("Пошук");
        Button btnChange = new Button("Заміна");
        Button btnSaveChange = new Button("Зберігти зміни");
        
        //Combobox       
        ComboBox combobox = new ComboBox();
        combobox.setPromptText("Оберіть категорію заміни");
        combobox.getItems().add("П.І.Б");
        combobox.getItems().add("Дата народження");
        combobox.getItems().add("Спеціальність");
        combobox.getItems().add("Курс");
        combobox.getItems().add("Розмір стипендії");
        
        //TextField
        TextField textSearch = new TextField();
        textSearch.setPromptText("Введіть значення для пошуку");
        
        TextField textChange = new TextField();
        textChange.setPromptText("Введіть значення для заміни");
        
        TitledPane tpaneArray = new TitledPane();
        GridPane gridArray = new GridPane();
        gridArray.setPadding(new Insets(10));
        gridArray.setVgap(5);
        gridArray.setHgap(10);
        gridArray.setStyle("-fx-background-color: #56CDC7");
        
        tpaneArray.setCollapsible(false);
        tpaneArray.setText("Вивід");
        tpaneArray.setContent(gridArray);
        
        //tpaneSearch, gridSearch
        TitledPane tpaneSearch = new TitledPane();
        tpaneSearch.setPrefSize(100, 200);
        tpaneSearch.setText("Пошук");
        GridPane gridSearch = new GridPane();
        gridSearch.setPadding(new Insets(10));
        gridSearch.setVgap(5);
        gridSearch.setHgap(10);
        gridSearch.setStyle("-fx-background-color: #56CDC7");
        
        //Розміщення на сцені
        GridPane.setConstraints(textSearch, 1, 0);
        gridSearch.getChildren().add(textSearch);
        
        GridPane.setConstraints(btnSearch, 1, 1);
        GridPane.setHalignment(btnSearch, HPos.CENTER);
        gridSearch.getChildren().add(btnSearch);
        
        GridPane.setConstraints(combobox, 1, 2);
        gridSearch.getChildren().add(combobox);
        
        GridPane.setConstraints(textChange, 1, 3);
        GridPane.setHalignment(textChange, HPos.CENTER);
        gridSearch.getChildren().add(textChange);
        
        GridPane.setConstraints(btnChange, 1, 4);
        GridPane.setHalignment(btnChange, HPos.CENTER);
        gridSearch.getChildren().add(btnChange);
        
        GridPane.setConstraints(lblChangeCount, 1, 5);
        GridPane.setHalignment(lblChangeCount, HPos.CENTER);
        gridSearch.getChildren().add(lblChangeCount);
           
        GridPane.setConstraints(btnSaveChange, 1, 6);
        GridPane.setHalignment(btnSaveChange, HPos.CENTER);
        gridSearch.getChildren().add(btnSaveChange);       
        
        tpaneSearch.setContent(gridSearch);
        
        GridPane.setConstraints(tpaneSearch, 4, 4);
        gridArray.getChildren().add(tpaneSearch);
        
        GridPane.setConstraints(textArea, 0, 0);
        gridPane.setColumnSpan(textArea, 4);
        GridPane.setRowSpan(textArea,5);
        gridArray.getChildren().add(textArea);
        
        GridPane.setConstraints(btnRead, 4, 0);
        GridPane.setHalignment(btnRead, HPos.CENTER);
        gridArray.getChildren().add(btnRead);
        
        GridPane.setConstraints(btnClear, 4, 2);
        GridPane.setHalignment(btnClear, HPos.CENTER);
        gridArray.getChildren().add(btnClear);
        
        GridPane.setConstraints(tpaneArray, 0, 7);
        GridPane.setColumnSpan(tpaneArray, 5);
        gridPane.getChildren().add(tpaneArray);
        
        Menu filemen = new Menu("Файл");
        Menu men = new Menu("Налаштування");
        Menu color = new Menu("Кольорова схема");
        Menu font = new Menu("Шрифт");
        
        MenuItem fileopen = new MenuItem("Відкрити файл");
        MenuItem filecreate = new MenuItem("Створити файл");
        MenuItem fileclose = new MenuItem("Закрити файл");
        filemen.getItems().addAll(fileopen,filecreate,fileclose);
        
        ToggleGroup toggle = new ToggleGroup();
        RadioMenuItem color_gr = new RadioMenuItem("Сіра");
        color_gr.setToggleGroup(toggle);
        RadioMenuItem color_grn = new RadioMenuItem("Зелена");
        color_grn.setToggleGroup(toggle);
        RadioMenuItem color_bl = new RadioMenuItem("Блакитна");
        color_bl.setToggleGroup(toggle);
        color_bl.setSelected(true);
        RadioMenuItem color_rd = new RadioMenuItem("Червона");
        color_rd.setToggleGroup(toggle);
        color.getItems().addAll(color_gr, color_grn, color_bl, color_rd);;
        
        RadioMenuItem bold_font = new RadioMenuItem("Жирний");
        RadioMenuItem ital_font = new RadioMenuItem("Наклоний");
        RadioMenuItem normal_font = new RadioMenuItem("Нормальний");
        ToggleGroup font_toggle = new ToggleGroup();
        bold_font.setToggleGroup(font_toggle);
        ital_font.setToggleGroup(font_toggle);
        normal_font.setToggleGroup(font_toggle);
        normal_font.setSelected(true);
        font.getItems().addAll(bold_font,ital_font,normal_font);
        men.getItems().addAll(color, font);
        
        Menu about = new Menu("Справка");
        MenuItem ab_aut = new MenuItem("Об авторі");
        about.getItems().add(ab_aut);
        
        MenuBar mb = new MenuBar(filemen,men,about);
        mb.setStyle("-fx-background-color: #56CDC7");
        GridPane.setColumnSpan(mb, 5);
        gridPane.add(mb, 0, 0);
        
        fileopen.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            File old_file = file;
            OpenFileDialog(primaryStage);
            if(old_file!=file)
            {
                st_readList.removeAll(st_readList);
                textArea.clear();
                lblChangeCount.setText("Кількість замін: 0");
            }
            }
        });
        filecreate.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            File old_file = file;
            SaveFileDialog(primaryStage);
            if(old_file!=file)
            {
                st_readList.removeAll(st_readList);
                textArea.clear();
                lblChangeCount.setText("Кількість замін: 0");
            }
            }
        });
        fileclose.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            file=null;
            st_readList.removeAll(st_readList);
            textArea.clear();
            lblChangeCount.setText("Кількість замін: 0");
        }});
        color_gr.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            gridTPanel.setStyle("-fx-background-color: darkgray");
            gridArray.setStyle("-fx-background-color: darkgray");
            gridSearch.setStyle("-fx-background-color: darkgray");
            mb.setStyle("-fx-background-color: lightgray");
            }
        });
        color_grn.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            gridTPanel.setStyle("-fx-background-color: #82DD55");
            gridArray.setStyle("-fx-background-color: #82DD55");
            gridSearch.setStyle("-fx-background-color: #82DD55");
            mb.setStyle("-fx-background-color: #82DD55");
            }
        });
        color_bl.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            gridTPanel.setStyle("-fx-background-color: #56CDC7");
            gridArray.setStyle("-fx-background-color: #56CDC7");
            gridSearch.setStyle("-fx-background-color: #56CDC7");
            mb.setStyle("-fx-background-color: #56CDC7");
            }
        });
        color_rd.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            gridTPanel.setStyle("-fx-background-color: #CD5C5C");
            gridArray.setStyle("-fx-background-color: #CD5C5C");
            gridSearch.setStyle("-fx-background-color: #CD5C5C");
            mb.setStyle("-fx-background-color: #CD5C5C");
            }
        });
        bold_font.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                lblName.setStyle("-fx-font-weight: bold");
                lblDoB.setStyle("-fx-font-weight: bold");
                lblSpec.setStyle("-fx-font-weight: bold");
                lblCourse.setStyle("-fx-font-weight: bold");
                lblScolars.setStyle("-fx-font-weight: bold");
                lblElements.setStyle("-fx-font-weight: bold");
                lblChangeCount.setStyle("-fx-font-weight: bold");        
            }
        });
        ital_font.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                lblName.setStyle("-fx-font-style: italic");
                lblDoB.setStyle("-fx-font-style: italic");
                lblSpec.setStyle("-fx-font-style: italic");
                lblCourse.setStyle("-fx-font-style: italic");
                lblScolars.setStyle("-fx-font-style: italic");
                lblElements.setStyle("-fx-font-style: italic");
                lblChangeCount.setStyle("-fx-font-style: italic");
        }});
        normal_font.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                lblName.setStyle("-fx-font-style: normal");
                lblDoB.setStyle("-fx-font-style: normal");
                lblSpec.setStyle("-fx-font-style: normal");
                lblCourse.setStyle("-fx-font-style: normal");
                lblScolars.setStyle("-fx-font-style: normal");
                lblElements.setStyle("-fx-font-style: normal");
                lblChangeCount.setStyle("-fx-font-style: normal");
                lblName.setStyle("-fx-font-weight: normal");
                lblDoB.setStyle("-fx-font-weight: normal");
                lblSpec.setStyle("-fx-font-weight: normal");
                lblCourse.setStyle("-fx-font-weight: normal");
                lblScolars.setStyle("-fx-font-weight: normal");
                lblElements.setStyle("-fx-font-weight: normal");
                lblChangeCount.setStyle("-fx-font-weight: normal");
        }});
        ab_aut.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Об авторі");
                alert.setHeaderText("Солотін Єгор Русланович \n група 333Б");
                alert.setContentText("Варіант №25 \nТема: Студенти");
                alert.showAndWait();
            }
        });
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            if(!(textName.getText().isEmpty() || textDoB.getText().isEmpty() || textSpec.getText().isEmpty() || textCourse.getText().isEmpty() || textScolars.getText().isEmpty()))
            {
                try{
                Student st = new Student(textName.getText(),textDoB.getText(), textSpec.getText(), Integer.parseInt(textCourse.getText()), Integer.parseInt(textScolars.getText()));
                st_recordList.add(st);
                lblElements.setText("Кількість записів у буфері: "+st_recordList.size());
                
                textName.setText("");
                textDoB.setText("");
                textSpec.setText("");
                textCourse.setText("");
                textScolars.setText("");
                }
                catch(NumberFormatException e)
                {
                    ErrorAlert("Невірний формат!");
                }
            }
            else
            {
                ErrorAlert("Не всі поля заповненні!");
            }
            }
        });
        btnRead.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            try
            {
            if(file==null) OpenFileDialog(primaryStage);
            if(file==null) return;
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);    
            String str, name, DoB, spec;
            int course, scolar;
            textArea.clear();
            while(sc.hasNextLine())
            {      
                str=sc.nextLine();
                try
                {
                StringTokenizer token = new StringTokenizer(str,"|");
                name = token.nextToken();
                DoB = token.nextToken();
                spec = token.nextToken();
                course = Integer.parseInt(token.nextToken());
                scolar = Integer.parseInt(token.nextToken());
                
                textArea.appendText(str);
                textArea.appendText("\n");
                st_readList.add(new Student(name,DoB,spec,course,scolar));      
                }
                catch(Exception e)
                {
                }
            }
            fr.close();
            }
            catch(Exception e)
            {
                ErrorAlert("Файл не знайдено!");
            }
            }
        });
        btnAccept.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            try
            {
                if(file==null) SaveFileDialog(primaryStage);
                if(file==null) return;
                PrintWriter pw = new PrintWriter(new FileWriter(file,true));
                for(Student st:st_recordList) 
                    st.writeData(pw);
                pw.close();
                
                st_recordList.removeAll(st_recordList);
                lblElements.setText("Кількість записів у буфері: "+st_recordList.size());
            }
            catch(Exception e)
            {
                ErrorAlert("Файл не знайдено!");
            }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            textArea.clear();
            st_readList.removeAll(st_readList);
            }
        });
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            String str = textSearch.getText();
            textArea.clear();
            for(Student st:st_readList)
                if((st.getName()).contains(str) || st.getDoB().equals(str) || st.getSpec().equals(str) || String.valueOf(st.getCourse()).equals(str) || String.valueOf(st.getScolars()).equals(str))
                    textArea.appendText(st.print()+"\n");
            }
        });
        btnChange.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
            String oldstr = textSearch.getText();  
            String newstr = textChange.getText();
            textArea.clear();
            int k=0;
            for(Student st:st_readList)
            {
                try
                {
                switch(String.valueOf(combobox.getValue()))
                {
                    case "П.І.Б": 
                        if(st.getName().equals(oldstr)) 
                        {
                        st.setName(newstr);
                        k++;
                        }
                        break;
                    case "Дата народження":
                        if(st.getDoB().equals(oldstr)) 
                        {
                        st.setDoB(newstr);
                        k++;
                        }
                        break;
                    case "Спеціальність":
                        if(st.getSpec().equals(oldstr)) 
                        {
                        st.setSpec(newstr);
                        k++;
                        };
                        break;
                    
                    case "Курс":
                        if(String.valueOf(st.getCourse()).equals(oldstr)) 
                        {
                        st.setCourse(Integer.parseInt(newstr));
                        k++;
                        }
                        break;
                    case "Розмір стипендії":
                        if(String.valueOf(st.getScolars()).equals(oldstr)) 
                        {
                        st.setScolars(Integer.parseInt(newstr));
                        k++;
                        }
                        break;   
                    }
                }
                    catch(NumberFormatException e)
                    {
                    ErrorAlert("Невірний формат!");
                    }
                textArea.appendText(st.print()+"\n");
            }
            if(k==0) ErrorAlert("Збіги не знайдено!");
            k+=Integer.parseInt(lblChangeCount.getText().substring(17));
            lblChangeCount.setText("Кількість замін: "+k);
            }
        });
        btnSaveChange.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
            try
            {
                if(Integer.parseInt(lblChangeCount.getText().substring(17))==0)
                {
                    ErrorAlert("Змін не знайдено");
                    return;
                }
                PrintWriter pw = new PrintWriter(new FileWriter(file,false));
                for(Student st:st_readList) 
                    st.writeData(pw);
                pw.close();
                
                st_readList.removeAll(st_readList);
                lblChangeCount.setText("Кількість замін: 0");
            }
            catch(Exception e)
            {
                ErrorAlert("Файл не знайдено!");
            }           
        }});
        primaryStage.show();
    }
}
