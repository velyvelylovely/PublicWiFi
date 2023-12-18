package dao;

import Database.DBConnect;
import dto.BookMarkDTO;
import dto.BookMarkGroupDTO;
import org.sqlite.core.DB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookMarkDAO {
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;

    public BookMarkDAO() {

    }

    public int count() {
        int count = 0;

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = " select count(*) from bookmark_list ";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    public int insertBookMark(BookMarkDTO dto) {
        int affected = 0;
        BookMarkDTO bookMarkDTO = new BookMarkDTO();

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = " insert or replace into bookmark_list" +
                    "(group_no, mgr_no, register_dttm)" +
                    " values (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dto.getGroupNo());
            preparedStatement.setString(2, dto.getMgrNo());
            preparedStatement.setString(3, String.valueOf(new Timestamp(System.currentTimeMillis())));

            affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("북마크 데이터 삽입 완료");
            } else {
                System.out.println("북마크 데이터 삽입 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return affected;
    }

    public int deleteBookMark(int id) {
        int affected = 0;

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = " delete from bookmark_list where id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("북마크 데이터 삭제 완료");
            } else {
                System.out.println("북마크 데이터 삭제 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return affected;
    }


    public BookMarkDTO selectBookMarkList(int id) {
        BookMarkDTO bookMarkDTO = new BookMarkDTO();

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try{
            connection = DBConnect.connectDB();
            String sql = " select * from bookmark_list where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bookMarkDTO.setId(resultSet.getInt("id"));
                bookMarkDTO.setGroupNo(resultSet.getInt("group_no"));
                bookMarkDTO.setMgrNo(resultSet.getString("mgr_no"));
                bookMarkDTO.setRegister_dttm(resultSet.getString("register_dttm"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return bookMarkDTO;
    }

    public List<BookMarkDTO> showBookMarklist() {
        connection = null;
        preparedStatement = null;
        resultSet = null;

        List<BookMarkDTO> list = new ArrayList<>();

        try {
            connection = DBConnect.connectDB();
            String sql = " select bookmark_list.* "
                    + " from bookmark_list "
                    + " inner join bookmark_group "
                    + " ON bookmark_list.group_no = bookmark_group.id "
                    + " order by bookmark_group.order_no";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookMarkDTO bookMarkDTO = BookMarkDTO.builder()
                        .id(resultSet.getInt("id"))
                        .groupNo(resultSet.getInt("group_no"))
                        .mgrNo(resultSet.getString("mgr_no"))
                        .register_dttm(resultSet.getString("register_dttm"))
                        .build();

                list.add(bookMarkDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return list;
    }
}
