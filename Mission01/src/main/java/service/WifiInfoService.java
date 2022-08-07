package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DistanceDTO;
import dto.HistoryDTO;
import dto.RowDTO;

public class WifiInfoService {	
	
    public void truncateWifiInfo() {
        String url = "jdbc:mariadb://3.34.44.198:3306/kobis_db";
        String dbUserId = "kobis_user";
        String dbPassword = "kobis1234";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int i = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            connection.setAutoCommit(false);
            String sql = " truncate WIFI_INFO ";

            preparedStatement = connection.prepareStatement(sql);
         
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {rs.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {preparedStatement.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {connection.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public int insertWifiInfo(List<RowDTO> dtoList) {
        String url = "jdbc:mariadb://3.34.44.198:3306/kobis_db";
        String dbUserId = "kobis_user";
        String dbPassword = "kobis1234";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int i = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            connection.setAutoCommit(false);
            String sql = " insert into WIFI_INFO(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1,"
            		+ "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE,"
            		+ "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3,"
            		+ "LAT, LNT, WORK_DTTM) "+
            " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            
            for (RowDTO dto : dtoList) {
            	i++;
                preparedStatement.setString(1, dto.getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, dto.getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, dto.getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, dto.getX_SWIFI_ADRES1());
                preparedStatement.setString(5, dto.getX_SWIFI_ADRES2());
                preparedStatement.setString(6, dto.getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, dto.getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, dto.getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, dto.getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, dto.getX_SWIFI_CMCWR());
                preparedStatement.setString(11, dto.getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, dto.getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, dto.getX_SWIFI_REMARS3());
                preparedStatement.setBigDecimal(14, dto.getLNT());
                preparedStatement.setBigDecimal(15, dto.getLAT());
                preparedStatement.setString(16, dto.getWORK_DTTM());
                
                preparedStatement.addBatch();
                preparedStatement.clearParameters();
                
                if (i % 10000 == 0) {
                	preparedStatement.executeBatch();
                	
                	preparedStatement.clearBatch();
                	
                	connection.commit();
                }
            }
            
            preparedStatement.executeBatch();
            connection.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {rs.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {preparedStatement.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {connection.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return dtoList.size();
    }
    
    public ArrayList<DistanceDTO> selectWifiInfo(BigDecimal lnt, BigDecimal lat) {
        String url = "jdbc:mariadb://3.34.44.198:3306/kobis_db";
        String dbUserId = "kobis_user";
        String dbPassword = "kobis1234";

        //1. 드라이버로드
        //2. 커넥션 객체 생성
        //3. 스테이트먼트 객체 생성
        //4. 쿼리 실행
        //5. 결과 수행
        //6. 객체 연결 해체
        ArrayList<DistanceDTO> dtoList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int i = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            connection.setAutoCommit(false);
            String sql = " select X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1,"
            		+ "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE,"
            		+ "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3,"
            		+ "TRIM(TRAILING '0' FROM LAT) as LAT, TRIM(TRAILING '0' FROM LNT) as LNT, WORK_DTTM, "
            		+ "ST_Distance_Sphere(POINT(LNT, LAT), POINT(?, ?)) as distance "
            		+ " from WIFI_INFO "
            		+ " where LAT > -90 and LAT < 90 "
            		+ " ORDER BY distance "
            		+ " LIMIT 20 ";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setBigDecimal(1, lnt);
            preparedStatement.setBigDecimal(2, lat);
            
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	DistanceDTO dto = new DistanceDTO();
            	Double dist = Math.round((rs.getDouble("distance") * 0.001)*10000)/10000.0;
            	dto.setDistance(dist);
            	dto.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
            	dto.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
            	dto.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
            	dto.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
            	dto.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
            	dto.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
            	dto.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
            	dto.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
            	dto.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
            	dto.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
            	dto.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
            	dto.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
            	dto.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
            	dto.setLAT(rs.getBigDecimal("LAT"));
            	dto.setLNT(rs.getBigDecimal("LNT"));
            	dto.setWORK_DTTM(rs.getString("WORK_DTTM"));
            	
            	dtoList.add(dto);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {rs.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {preparedStatement.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {connection.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return dtoList;
    }

    public ArrayList<HistoryDTO> selectLocationHistory() {
        String url = "jdbc:mariadb://3.34.44.198:3306/kobis_db";
        String dbUserId = "kobis_user";
        String dbPassword = "kobis1234";

        ArrayList<HistoryDTO> dtoList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int i = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql = " select ID, LAT, LNT, QUERY_DATE "+
            " from LOCATION_HISTORY " +
            " ORDER BY ID DESC " +
            " LIMIT 20 ";

            preparedStatement = connection.prepareStatement(sql);
            
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	HistoryDTO dto = new HistoryDTO();
            	dto.setId(rs.getInt("ID"));
            	dto.setLat(rs.getBigDecimal("LAT"));
            	dto.setLnt(rs.getBigDecimal("LNT"));
            	dto.setQuery_date(rs.getTimestamp("QUERY_DATE"));
            	
            	dtoList.add(dto);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {rs.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {preparedStatement.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {connection.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return dtoList;
    }
    
    public int deleteLocationHistory(int id) {
        String url = "jdbc:mariadb://3.34.44.198:3306/kobis_db";
        String dbUserId = "kobis_user";
        String dbPassword = "kobis1234";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            connection.setAutoCommit(false);
            String sql = " delete from LOCATION_HISTORY "+
            " where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
            
            connection.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {rs.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {preparedStatement.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {connection.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return 1;
    }
    
    public int insertLocationHistory(BigDecimal lat, BigDecimal lnt) {
        String url = "jdbc:mariadb://3.34.44.198:3306/kobis_db";
        String dbUserId = "kobis_user";
        String dbPassword = "kobis1234";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            connection.setAutoCommit(false);
            String sql = " insert into LOCATION_HISTORY(LAT, LNT, QUERY_DATE) "+
            " values(?, ?, NOW()) ";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setBigDecimal(1, lat);
            preparedStatement.setBigDecimal(2, lnt);
            
            preparedStatement.executeUpdate();
            
            connection.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {rs.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {preparedStatement.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {connection.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return 1;
    }
}
