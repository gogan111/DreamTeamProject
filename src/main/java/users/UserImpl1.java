package users;

public class UserImpl1 implements Users{
    private  String URL = "jdbc:postgresql://127.0.0.1:5432/";
    private  String USER = "alex";
    private String PASSWORD = "1234";
    private String DATABASE = "alex";
    private String DRIVER = "org.postgresql.Driver";

    private String SELECT_TABLE = "SELECT * FROM andersen ORDER BY id";
    private String SELECT_BY_ID = "SELECT * FROM andersen WHERE id = ";
    private String DELETE = "DELETE FROM andersen WHERE id = ?";
    private String SAVE_DATA = "INSERT INTO andersen (name,surname,age,mail) VALUES (?,?,?,?)";
    private String UPDATE_DATA = "UPDATE andersen SET "
            + " name = ?,"
            + "surname = ?, "
            + "age = ?, "
            + "mail = ?"
            + "WHERE id =";


    @Override
    public String getURL() {
        return URL+DATABASE;
    }

    @Override
    public String getPass() {
        return PASSWORD;
    }

    @Override
    public String getUser() {
        return USER;
    }

    public String getSELECT_TABLE() {
        return SELECT_TABLE;
    }

    public String getSELECT_BY_ID() {
        return SELECT_BY_ID;
    }

    public String getDELETE() {
        return DELETE;
    }

    public String getSAVE_DATA() {
        return SAVE_DATA;
    }

    public String getUPDATE_DATA() {
        return UPDATE_DATA;
    }

    public String getDRIVER() {
        return DRIVER;
    }
}
