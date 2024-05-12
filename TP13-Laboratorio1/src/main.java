
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String user = "root";
        String pass = "";
        String url = "jdbc:mariadb://localhost/universidadulp";

        String sql = "INSERT INTO alumno"
                    + "( dni, apellido, nombre, fechaNacimiento, estado)"
                    + " VALUES (1233, 'menichetti', 'cosme', '2000-12-01', 1),"
                    + "(12333, 'mesa', 'termo', '2005-1-01', 1),"
                    + "(122333, 'stieger', 'perri', '1999-07-01', 1)";
        
        String sqlMaterias = "INSERT INTO materia"
                    + "( nombre, anio, estado)"
                    + " VALUES ('Matematica', 1, 1),"
                    + "('Eda', 5, 1),"
                    + "('Laboratorio', 3, 1),"
                + "('Web', 2, 1);";
        
        String sqlInscripcion = "INSERT INTO inscripcion"
                    + "( nota, idAlumno, idMateria)"
                    + " VALUES (8,2,1),"
                    + "(5,2,4),"
                    + "(1,1,4),"
                    + "(6,1,2),"
                    + "(10,3,3),"
                + "(8,3,1);";
        
        String consultaMas8 = "Select DISTINCT * "
                + "from inscripcion i  JOIN alumno a "
                + "where nota > 8 and i.idAlumno = a.idAlumno;";
        
        String borrarAlumno = "Delete from inscripcion where idInscripcion = 1";
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, user, pass);
            
            Statement st = conexion.createStatement();
            //Cargar la variable de la consulta para update, insert o delete
            int retorno = st.executeUpdate(borrarAlumno);
            
            if (retorno > 0) {
                System.out.println("Se cargo/borro correctamente");
            }else{
                System.out.println("No se pudo cargar/borrar dato");
            }
            
            //Cargar query tipo select
            ResultSet fila = st.executeQuery(consultaMas8);
            while (fila.next()) {
                
                int idAlumno = fila.getInt("idAlumno");
                int dni = fila.getInt("dni");
                String apellido = fila.getString("apellido");
                String nombre = fila.getString("nombre");
                String fechaNacimiento = fila.getDate("fechaNacimiento").toString();
                boolean estado = fila.getBoolean("estado");
                        
                System.out.println("-----------------");
                System.out.println("id: " + idAlumno);
                System.out.println("dni: " + dni);
                System.out.println("apellido: " + apellido);
                System.out.println("nombre: " + nombre);
                System.out.println("nacimiento: " + fechaNacimiento);
                System.out.println("estado: " + estado);
                System.out.println("--------------");
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro driver");
        } catch (SQLException ex) {
            System.out.println("Falla en la conexion con DB");
            System.out.println("error " + ex.getStackTrace().toString());
            System.out.println(ex);
        }

        
        
        
        
        
    }
}
