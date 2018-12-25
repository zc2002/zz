package Data;
import java.sql.*;


/**
 * @author Mr.Tong
 *	数据库操作类
 */
public class BaseDao {
	
	public PreparedStatement pstmt = null;
	
	public ResultSet rs = null;
	
	public Connection conn = null;
	
	public BaseDao(Connection conn) {
		this.conn = conn;
	}
	
	public BaseDao(){}
	
	/**
     * @param preparedSql	sql语句
     * @param param	查询条件参数
     * @return	增、删、改、返回结果
     */
    public int exceuteUpdate(String preparedSql,Object[] param){
        int result = 0;
        try{
            pstmt = conn.prepareStatement(preparedSql);
            if (param != null){
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i+1,param[i]);
                }
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	DataBasePool.release(null, pstmt, null);
        }
        return result;
    }


    /**
     * @param preparedSql	sql语句
     * @param param	查询条件参数
     * @return	ResultSet结果集
     */
    public ResultSet executeQuery(String preparedSql,Object[] param){
        try{
        	pstmt = conn.prepareStatement(preparedSql);
            if (param != null){
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

