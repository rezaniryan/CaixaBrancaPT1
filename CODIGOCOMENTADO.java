package login; 
import java.sql.Connection;
// Importa a classe Connection 
import java.sql.DriverManager;
// Importa o DriverManager
import java.sql.ResultSet;
// Importa ResultSet
import java.sql.Statement;
// Importa Statement

public class User {
    // Declaração da classe

    public Connection conectarBD() {
        // Método que retorna uma conexão com o banco de dados
        Connection conn = null;
        // Inicializa a variável de conexão como nula
        try {
            Class.forName("com.mysql.Driver.Manager").newInstance();
            // Carrega a classe do driver JDBC do MySQL
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            // Define a URL de conexão com o banco
            conn = DriverManager.getConnection(url);
            // Abre a conexão usando a URL fornecida
        } catch (Exception e) {
            // Captura qualquer exceção
        }
        return conn;
        // Retorna a conexão, se der null siginifica que falhou
    }

    public String nome = "";
    // Variável pública que armazena o nome do usuário retornado do banco

    public boolean result = false;
    // Variável pública que indica se a autenticação foi concluida

    public boolean verificarUsuario(String login, String senha) {
        // Método que verifica se existe usuário com o login e senha informados
        String sql = "";
        // Inicializa a string que tem a instrução SQL
        Connection conn = conectarBD();
        // Cria uma conexão com o banco chamando o método conectarBD()

        // inicio SQL
        sql = "select nome from usuarios ";
        // Monta a primeira parte do comando SQL
        sql += "where login = '" + login + "'";
        // Adiciona condição filtrando pelo login 
        sql += " and senha = '" + senha + "'";
        // Adiciona condição filtrando pela senha 

        try {
            Statement st = conn.createStatement();
            // Cria um Statement para executar comandos SQL no banco
            ResultSet rs = st.executeQuery(sql);
            // Executa a consulta SQL e guarda o resultado no ResultSet
            if (rs.next()) {
                // Verifica se encontrou algum registro
                result = true;
                // Atribui true ao resultado
                nome = rs.getString("nome");
                // Pega o valor da coluna "nome" do usuário retornado
            }
        } catch (Exception e) {
            // Captura exceções
        }
        return result;
        // Retorna true se encontrou o usuário, false se for oposto
    }
}
// fim da classe
