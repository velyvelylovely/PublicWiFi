package dao;

import Database.DBConnect;
import dto.BookMarkGroupDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class BookMarkGroupDAO {
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;

    public int groupCount() {   //그룹에 현재 몇 개가 있는지 파악!!
        int count = 0;

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = " select count(*) from bookmark_group ";

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

    public int saveBookMarkGroup(BookMarkGroupDTO bookMarkGroupDTO){
        int affected = 0;

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = " insert into bookmark_group(name, order_no, register_dttm) "
                    + " values (?, ?, ?)" ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookMarkGroupDTO.getName());
            preparedStatement.setInt(2, bookMarkGroupDTO.getOrder());
            preparedStatement.setString(3, bookMarkGroupDTO.getRegister_dttm());

            affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("북마크 그룹 데이터 삽입 완료");
            } else {
                System.out.println("북마크 그룹 데이터 삽입 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection,preparedStatement,resultSet);
        }

        return affected;
    }

    public BookMarkGroupDTO selectBookMarkGroup(int id) {

        BookMarkGroupDTO bookMarkGroupDTO = new BookMarkGroupDTO();

        connection = null;
        resultSet = null;
        preparedStatement = null;

        try {
            connection = DBConnect.connectDB();

            String sql = " select * from bookmark_group where id = ? " ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bookMarkGroupDTO.setId(resultSet.getInt("id"));
                bookMarkGroupDTO.setName(resultSet.getString("name"));
                bookMarkGroupDTO.setOrder(resultSet.getInt("order_no"));
                bookMarkGroupDTO.setRegister_dttm(resultSet.getString("register_dttm"));
                bookMarkGroupDTO.setUpdate_dttm(resultSet.getString("update_dttm"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return bookMarkGroupDTO;
    }
    public List<BookMarkGroupDTO> showBookMarkGroup() {
        List<BookMarkGroupDTO> list = new ArrayList<>();

        connection = null;
        resultSet = null;
        preparedStatement = null;

        try {
            connection = DBConnect.connectDB();

            String sql = " select * from bookmark_group order by id ";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookMarkGroupDTO bookMarkGroupDTO = BookMarkGroupDTO.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .order(resultSet.getInt("order_no"))
                        .register_dttm(resultSet.getString("register_dttm"))
                        .update_dttm(resultSet.getString("update_dttm"))
                        .build();

                list.add(bookMarkGroupDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return list;
    }

    public int updateBookMarkGroup(int id, String name, int order) {
        int result = 0;

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = " update bookmark_group set name = ?, order_no = ?, update_dttm = ? where id = ? ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, order);
            preparedStatement.setString(3, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
            preparedStatement.setInt(4, id);

            result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("북마크 그룹 데이터 업데이트 완료");
            } else {
                System.out.println("북마크 그룹 데이터 업데이트 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return result;
    }

    public int deleteBookMarkGroup(int id) {
        int affected = 0;

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();

            preparedStatement = connection.prepareStatement("pragma foreign_keys = on");
            preparedStatement.executeUpdate();

            String sql = " delete from bookmark_group where id = ? ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("북마크 그룹 데이터 삭제 완료");
            } else {
                System.out.println("북마크 그룹 데이터 삭제 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return affected;
    }

}
