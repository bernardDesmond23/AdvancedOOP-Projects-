
package data;

public class Test {
public static void main(String args[]){
    DBConnection dbc= new DBOperations();
    dbc.selectOperation("1");
    dbc.insertOperation("2", "Black Rhino", "Rhire", "savannah", "66","Herbivore", "1");
    dbc.updateOperation("2", "White Rhino", "2");
    dbc.deleteOperation(2);
}    
}
