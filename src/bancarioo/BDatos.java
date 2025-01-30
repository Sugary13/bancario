
package bancarioo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BDatos {
    public static void cargarDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc");
        }
        catch (Exception obj){
            throw obj;
        }
    }
    
    public static Connection getConnection() throws SQLException {
        Connection cn;
        try {
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bancos", "root", "");
            return cn;
        }
          
        catch (SQLException obje){
            throw obje;
        }
    }
    public static void insertarCliente(Cliente cliente)throws Exception{
        PreparedStatement st;
        Connection cn;
        try {
             cn=getConnection();
             st=cn.prepareStatement("insert into clientes(nombre,rfc,telefono) "
                      +"values(?,?,?)",
                       PreparedStatement.RETURN_GENERATED_KEYS);
                      
             //st.setInt(1,cliente.getIdCliente());
             st.setString(1,cliente.getNombre());
             st.setString(2, cliente.getRfc());
             st.setString(3, cliente.getTelefono());
             st.executeUpdate();
             ResultSet rs= st.getGeneratedKeys();
             if (rs.next()) 
             {
                 int id=rs.getInt(1);
                 cliente.setId(id);
                 
             } 
             
             cn.close();
             
        }    
        catch (SQLException obje){
           throw obje; 
        }
    }
    public static void actualizarCliente(Cliente cliente)throws Exception{
        PreparedStatement st;
        Connection cn;
        try {
             cn=getConnection();
             st=cn.prepareStatement("update clientes set nombre=?, rfc=?, telefono=? where id=?");
             st.setInt(4,cliente.getId());
             st.setString(1,cliente.getNombre());
             st.setString(2, cliente.getRfc());
             st.setString(3, cliente.getTelefono());
             st.executeUpdate();
             cn.close();
        }
        catch (SQLException obje){
           throw obje;
        }
}
     public static Cliente getCliente(int pkId) throws Exception{
        PreparedStatement st;
        Connection cn;
        ResultSet rs;
        Cliente cliente=null;
        try {
             cn=getConnection();
             st=cn.prepareStatement("select * from clientes where id=?");
             st.setInt(1,pkId);
             rs=st.executeQuery();
             if (rs.next()){
                 cliente=new Cliente();
                 cliente.setId(pkId);
                 cliente.setNombre(rs.getString("nombre"));
                 cliente.setRfc(rs.getString("rfc"));
                 cliente.setTelefono(rs.getString("telefono"));

             }
             cn.close();
             return cliente;
        }
        catch (SQLException obje){
           throw obje; 
        }
}
     public static void registrarCuenta(Cuenta cuenta)throws Exception{
        PreparedStatement st;
        Connection cn;
        try {
             cn=getConnection();
             st=cn.prepareStatement("insert into"
               + " cuentas(numcta,saldoant,depositos,retiros,id,interes)"
               + " values(?,?,?,?,?,?)");
             //st.setInt(1,cliente.getIdCliente());
             st.setString(1,cuenta.getNumcta());
             st.setDouble(2, 0.0);
             st.setDouble(3, 0.0);
             st.setDouble(4, 0.0);
             st.setInt(5,cuenta.getId());
             st.setDouble(6, cuenta.getInteres());
               
             st.executeUpdate();
             cn.close();
        }    
        catch (SQLException obje){
           throw obje; 
        }
    }
     public static void actualizarCuenta(Cuenta cuenta)throws Exception{
        PreparedStatement st;
        Connection cn;
        try {
             cn=getConnection();
             st=cn.prepareStatement("UPDATE cuentas "
               + " SET id=?, interes=? "
               + " WHERE numcta=?");
             
             st.setInt(1,cuenta.getId());
             st.setDouble(2, cuenta.getInteres());
             st.setString(3, cuenta.getNumcta());
             st.executeUpdate();
             cn.close();
        }    
        catch (SQLException obje){
           throw obje; 
        }
        
    }
     public static void registrarTransaccion(Transaccion tran, Cuenta cta)throws Exception{
        PreparedStatement st;
        Connection cn=null;
        try {
             cn=getConnection();
             cn.setAutoCommit(false);
             st=cn.prepareStatement("insert into"
               + " transacciones(numcta,fecha,mov,importe)"
               + " values(?,?,?,?)");
             //st.setInt(1,cliente.getIdCliente());
             st.setString(1,tran.getNumcta());
             st.setString(2,tran.getFecha());
             st.setInt(3,tran.getMov());
             st.setDouble(4, tran.getImporte());
               
             st.executeUpdate();
             st=cn.prepareStatement("UPDATE CUENTAS "
                + "SET depositos=?, retiros=? "
                + " WHERE numcta=?");
             st.setDouble(1, cta.getDepositos());
             st.setDouble(2, cta.getRetiros());
             st.setString(3, tran.getNumcta());
             st.executeUpdate();
             cn.commit();
             cn.close();
        }    
        catch (SQLException ob){
           
         if(cn !=null){
            try {
              cn.rollback();///// -----> Deshacer operaciones
             } catch (SQLException ex) {
                      throw ex;
            }
           throw ob; 
        }
    }
} 
     public static Cuenta getCuenta(String numCta) throws Exception{
        PreparedStatement st;
        Connection cn;
        ResultSet rs;
        Cuenta cuenta=null;
        try {
             cn=getConnection();
             st=cn.prepareStatement("select * from cuentas"
                     +" where numcta=?");
             st.setString(1,numCta);
             rs=st.executeQuery();
             if (rs.next()){
                 cuenta=new Cuenta();
                 cuenta.setNumcta(numCta);
                 cuenta.setSaldoAnt(rs.getDouble("saldoant"));
                 cuenta.setDepositos(rs.getDouble("depositos"));
                 cuenta.setRetiros(rs.getDouble("retiros"));
                 cuenta.setId(rs.getInt("id"));
                 cuenta.setInteres(rs.getDouble("interes"));
                
             }
             cn.close();
             return cuenta;
        }
        catch (SQLException obje){
           throw obje; 
        }
}
}

