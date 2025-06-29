package sistema;

import java.sql.*;

/**
 * Gerenciador de banco de dados SQLite para o sistema de locação
 * Responsável por criar tabelas e gerenciar conexões
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:locacao_imoveis.db";
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        initializeDatabase();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Inicializa o banco de dados criando as tabelas necessárias
     */
    private void initializeDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            createTables();
            System.out.println("✅ Banco de dados inicializado com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inicializar banco de dados: " + e.getMessage());
        }
    }

    /**
     * Cria todas as tabelas necessárias para o sistema
     */
    private void createTables() throws SQLException {
        // Tabela de usuários
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                senha_hash TEXT NOT NULL,
                telefone TEXT,
                genero TEXT,
                data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                ativo BOOLEAN DEFAULT TRUE
            )
        """;

        // Tabela de imóveis
        String createImoveisTable = """
            CREATE TABLE IF NOT EXISTS imoveis (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                tipo TEXT NOT NULL,
                titulo TEXT NOT NULL,
                endereco TEXT NOT NULL,
                preco REAL NOT NULL,
                descricao TEXT,
                proprietario_id INTEGER,
                forma_caucao TEXT,
                tipo_compartilhamento TEXT,
                data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                ativo BOOLEAN DEFAULT TRUE,
                FOREIGN KEY (proprietario_id) REFERENCES usuarios (id)
            )
        """;

        // Tabela de favoritos
        String createFavoritosTable = """
            CREATE TABLE IF NOT EXISTS favoritos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario_id INTEGER,
                imovel_id INTEGER,
                data_favorito TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
                FOREIGN KEY (imovel_id) REFERENCES imoveis (id),
                UNIQUE(usuario_id, imovel_id)
            )
        """;

        // Tabela de visualizações
        String createVisualizacoesTable = """
            CREATE TABLE IF NOT EXISTS visualizacoes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                imovel_id INTEGER,
                data_visualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                ip_visitor TEXT,
                FOREIGN KEY (imovel_id) REFERENCES imoveis (id)
            )
        """;

        // Tabela de contatos
        String createContatosTable = """
            CREATE TABLE IF NOT EXISTS contatos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                imovel_id INTEGER,
                nome_contato TEXT NOT NULL,
                email_contato TEXT,
                telefone_contato TEXT,
                mensagem TEXT,
                data_contato TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                respondido BOOLEAN DEFAULT FALSE,
                FOREIGN KEY (imovel_id) REFERENCES imoveis (id)
            )
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createImoveisTable);
            stmt.execute(createFavoritosTable);
            stmt.execute(createVisualizacoesTable);
            stmt.execute(createContatosTable);
        }
    }

    /**
     * Obtém uma conexão com o banco de dados
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    /**
     * Fecha a conexão com o banco de dados
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    /**
     * Executa uma consulta e retorna um ResultSet
     */
    public ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        return stmt.executeQuery();
    }

    /**
     * Executa uma atualização (INSERT, UPDATE, DELETE)
     */
    public int executeUpdate(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        return stmt.executeUpdate();
    }

    /**
     * Executa uma inserção e retorna o ID gerado
     */
    public int executeInsert(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        
        if (rs.next()) {
            return rs.getInt(1);
        }
        
        return -1;
    }

    /**
     * Verifica se uma tabela existe
     */
    public boolean tableExists(String tableName) {
        try {
            DatabaseMetaData meta = getConnection().getMetaData();
            ResultSet tables = meta.getTables(null, null, tableName, null);
            return tables.next();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Faz backup do banco de dados
     */
    public void backupDatabase(String backupPath) {
        try {
            String backupSQL = "BACKUP TO ?";
            PreparedStatement stmt = getConnection().prepareStatement(backupSQL);
            stmt.setString(1, backupPath);
            stmt.execute();
            System.out.println("✅ Backup criado em: " + backupPath);
        } catch (SQLException e) {
            System.err.println("❌ Erro ao criar backup: " + e.getMessage());
        }
    }

    /**
     * Obtém estatísticas do banco de dados
     */
    public void printDatabaseStats() {
        try {
            System.out.println("\n📊 ESTATÍSTICAS DO BANCO DE DADOS:");
            
            // Contar usuários
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM usuarios WHERE ativo = TRUE");
            if (rs.next()) {
                System.out.println("👥 Usuários ativos: " + rs.getInt(1));
            }
            
            // Contar imóveis
            rs = executeQuery("SELECT COUNT(*) FROM imoveis WHERE ativo = TRUE");
            if (rs.next()) {
                System.out.println("🏠 Imóveis ativos: " + rs.getInt(1));
            }
            
            // Contar favoritos
            rs = executeQuery("SELECT COUNT(*) FROM favoritos");
            if (rs.next()) {
                System.out.println("❤️ Favoritos: " + rs.getInt(1));
            }
            
            // Contar visualizações
            rs = executeQuery("SELECT COUNT(*) FROM visualizacoes");
            if (rs.next()) {
                System.out.println("👁️ Visualizações: " + rs.getInt(1));
            }
            
            // Contar contatos
            rs = executeQuery("SELECT COUNT(*) FROM contatos");
            if (rs.next()) {
                System.out.println("📞 Contatos: " + rs.getInt(1));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao obter estatísticas: " + e.getMessage());
        }
    }
} 