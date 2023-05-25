package Animales;

import org.apache.log4j.Logger;

import java.sql.*;

public class Animal {
    public static void main(String[] args) {
        Logger LOGGER = Logger.getLogger(Animal.class);
        String CREATE = "DROP TABLE IF EXISTS ANIMALES; CREATE TABLE ANIMALES (ID INT AUTO INCREMENT PRIMERY KEY, NOMBRE VARCHAR(100) NOT NULL, TIPO VARCHAR(100) NOT NULL";
        String INSERT1 = "INSERT INTO ANIMALES (NOMBRE, TIPO) Values ('Homero', 'perro'), ('Cleo', 'perro'), ('China','gato'),('Greta','gato')";
        String DELETE = "DELETE FROM ANIMALES WHERE ID = 3";
        //String SELECT = "SELECT * FROM ANIMALES";

        Connection Connection = null;
        try{
            //ME CONECTO
            Connection = getConnection();
            //CREAR LA TABLA
            Statement statement = Connection.createStatement();
            statement.execute(CREATE);
            //AGREGANDO LOS ANIMALITOS
            statement.execute(INSERT1);
            //HACER UN SELECT PARA VER LOS REGISTROS INSERTADOS
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ANIMALES");
            //RECORRER EL RESULTSET
            while (resultSet.next()){
                LOGGER.info(resultSet.getInt(1)+ " - " + resultSet.getString(2) + " - " + resultSet.getString(3));
            }
            //ELIMINAR ANIMALITO
            statement.execute((DELETE));




        } catch (Exception e){
            LOGGER.error(e.getMessage());
        } finally {
            try {
                Connection.close();
            } catch (Exception ex){
                LOGGER.error(ex.getMessage());
            }
        }

    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/c9clase11","sa","sa");
    }


}