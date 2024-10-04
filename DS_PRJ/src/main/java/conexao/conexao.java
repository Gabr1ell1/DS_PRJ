//essa clsasse será responsável pela conexão com MySql e terá 3 métodos
//Método 1: abertura da conexão
//Método 2: fechamento da conexão
//Método 3: execução do comando SQL

package conexao;
import javax.swing.JOptionPane;
import java.sql.*; //para execução do comando SQL no ambiente JAVA



public class conexao {
    final private String driver = "com.mysql.cj.jdbc.Driver";//definição do driver MySql para acesso aos dados 
    final private String url = "jdbc:mysql://localhost/clientes"; //acesso ao bd "clientes" no servidor (myAdmin)
    final private String usuario = "root";//usuário do MySql 
    final private String senha = "";//senha do MySql
    private Connection conexao;//variável que armazenará a conexao aberta 
    public Statement statement;//variável para a execuçaõ dos comandos SQL dentro do ambiente JAVA 
    public ResultSet resultset;//variável que armazenará o resultado da execução de um comando SQL
    
    public boolean conecta(){
        boolean result = true;
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conexão Estabelecida", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);        
        }
        catch(ClassNotFoundException Driver){
                JOptionPane.showMessageDialog(null, "Driver Não Localizado"+Driver, "Messagem do programa", JOptionPane.INFORMATION_MESSAGE);
            result = false;
        }
            catch(SQLException Fonte){
                JOptionPane.showMessageDialog(null, "Fonte de dados não lacalizada"+Fonte, "Messagem do programa", JOptionPane.INFORMATION_MESSAGE);
            result = false;
            }
                    return result;
         }
    public void desconecta(){
        try{
            conexao.close();
            JOptionPane.showMessageDialog(null, "Conexão com o banco fechada", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException fecha){
            JOptionPane.showMessageDialog(null, "Erro ao fechar o banco", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    
    public void executaSQL(String sql){
        try{
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }
        catch (SQLException excecao){
            JOptionPane.showMessageDialog(null, "Erro no comando SQL! \n Erro: " +excecao, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}





