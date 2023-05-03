package util;

import Content.Movies;
import Content.Series;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConnector {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Movies> movies = new ArrayList<>();
    ArrayList<Series> series = new ArrayList<>();

    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/Streaming";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Erp642puv";

    char quotes = '"';

   /* public void createUser(String Username, String Password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql1 = "insert into users (username, password) values (" + quotes + Username + quotes + "," + quotes + Password + quotes + ");";
            stmt = conn.prepareStatement(sql1);
            stmt.execute(sql1);

            //STEP 5: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    */

    public ArrayList<User> loadUsers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("loading users");
            String sql = "SELECT * FROM users";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name and add to users
                int id = Integer.parseInt(rs.getString("User_ID"));
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id,username,password);
                users.add(user);
            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return users;
    }

    public ArrayList<Movies> loadMovies() {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Loading Movies");
            String sql = "SELECT * FROM streaming.movies";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = Integer.parseInt(rs.getString("Movie_ID"));
                String title = rs.getString("Title");
                int year = Integer.parseInt(rs.getString("Year"));
                String genre = rs.getString("Genre");
                String[] genArr = genre.split(".");
                List<String> genres = new ArrayList<>(Arrays.asList(genre.trim().split(". ")));
                float rating = Float.parseFloat(rs.getString("Rating"));

                Movies movie = new Movies(id, title, genres, year, rating);
                movies.add(movie);
            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return movies;
    }

    public ArrayList<Series> loadSeries() {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("loading Series");
            String sql = "SELECT * FROM series";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                List<Integer> year = new ArrayList<>();
                //Retrieve by column name
                int id = Integer.parseInt(rs.getString("Series_ID"));
                String title = rs.getString("Title");

                String year1 = rs.getString("Year");
                String[] year2 = year1.split("-");
                int startdate = Integer.parseInt(year2[0]);
                int enddate;
                if(year2.length == 1 ) {
                    enddate = Integer.parseInt(Year.now().toString());
                } else {
                    enddate = Integer.parseInt(year2[1]);
                }

                for(int x = startdate; x <= enddate; x++){
                    year.add(x);
                }

                String genre = rs.getString("Genre");
                List<String> genres = List.of(genre.split(". "));
                float rating = Float.parseFloat(rs.getString("Rating"));
                Series serie = new Series(id, title, year, genres, rating);
                series.add(serie);
            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return series;
    }

    public void save(ArrayList<User> tempusers) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Execute a query
            System.out.println("Saving Users");
            for(User user : tempusers) {
                try {
                    //String sql1 = "insert into users (User_id, username, password) values (?, ?, ?)";
                    String sql1 = "insert into users (User_id, username, password) values (" + quotes + user.getId() + quotes + "," + quotes + user.getUsername() + quotes + "," + quotes + user.getPassword() + quotes + ");";
                    stmt = conn.prepareStatement(sql1);
                    // statement.setInt(1, user.getId());
                    stmt.executeUpdate(sql1);
                } catch (Exception e) {}
            }

            //STEP 5: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void loadLists() {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Almost there..");
            String sql = "SELECT * FROM streaming.watchedmovies;";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while (rs.next()) {
                for(User user: users) {
                    if(rs.getString("user_ID").equals(user.getId())){
                        for(Movies movie : movies){
                            System.out.println(rs.getInt("Movies_ID"));
                            if(rs.getInt("Movies_ID")==(movie.getMovies_ID())){
                                user.addToWatchedMovies(movie);
                            }
                        }
                    }
                }
            }
            stmt = conn.prepareStatement("SELECT * FROM streaming.watchedseries;");
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM streaming.watchedseries;");
            while (rs1.next()) {
                for(User user: users) {
                    if(rs1.getString("user_ID").equals(user.getId())){
                        for(Movies movie : movies){
                            if(rs1.getString("Movies_ID").equals(movie.getMovies_ID())){
                                user.addToSavedMovies(movie);
                            }
                        }
                    }
                }
            }
            stmt = conn.prepareStatement("SELECT * FROM streaming.savedmovies;");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM streaming.savedmovies;");
            while (rs2.next()) {
                for(User user: users) {
                    if(rs2.getString("user_ID").equals(user.getId())){
                        for(Series series : series){
                            if(rs2.getString("Series_ID").equals(series.getId())){
                                user.addToWatchedSeries(series);
                            }
                        }
                    }
                }
            }
            stmt = conn.prepareStatement("SELECT * FROM streaming.savedseries;");
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM streaming.savedseries;");
            while (rs3.next()) {
                for(User user: users) {
                    if(rs3.getString("user_ID").equals(user.getId())){
                        for(Series series : series){
                            if(rs3.getString("Series_ID").equals(series.getId())){
                                user.addToSavedSeries(series);
                            }
                        }
                    }
                }
            }

            //STEP 5: Clean-up environment
            rs.close();
            rs1.close();
            rs2.close();
            rs3.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void saveLists(ArrayList<User> users) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Execute a query
            System.out.println("Saving lists");
            for(User user : users) {
                for (Movies s : user.getWatchedMovies()) {
                    try {
                        //String sql1 = "insert into users (User_id, username, password) values (?, ?, ?)";
                        String sql1 = "INSERT INTO watchedmovies (User_id, Movies_ID) values (" + quotes + user.getId() + quotes + "," + quotes + s.getMovies_ID() + quotes + ");";
                        stmt = conn.prepareStatement(sql1);
                        // statement.setInt(1, user.getId());
                        stmt.executeUpdate(sql1);
                    } catch (Exception e) {}
                }
                for(Series s : user.getWatchedSeries()) {
                        try {
                            //String sql1 = "insert into users (User_id, username, password) values (?, ?, ?)";
                            String sql1 = "INSERT INTO watchedseries (User_id, Series_ID) values (" + quotes + user.getId() + quotes + "," + quotes + s.getId() + quotes + ");";
                            stmt = conn.prepareStatement(sql1);
                            // statement.setInt(1, user.getId());
                            stmt.executeUpdate(sql1);
                        } catch (Exception e) {}
                    }
                for(Movies s : user.getSavedMovies()) {
                    try {
                        //String sql1 = "insert into users (User_id, username, password) values (?, ?, ?)";
                        String sql1 = "INSERT INTO savedmovies (User_id, Movies_ID) values (" + quotes + user.getId() + quotes + "," + quotes + s.getMovies_ID() + quotes + ");";
                        stmt = conn.prepareStatement(sql1);
                        // statement.setInt(1, user.getId());
                        stmt.executeUpdate(sql1);
                    } catch (Exception e) {}
                }
                for(Series s : user.getSavedSeries()) {
                    try {
                        //String sql1 = "insert into users (User_id, username, password) values (?, ?, ?)";
                        String sql1 = "INSERT INTO savedseries (User_id, Series_ID) values (" + quotes + user.getId() + quotes + "," + quotes + s.getId() + quotes + ");";
                        stmt = conn.prepareStatement(sql1);
                        // statement.setInt(1, user.getId());
                        stmt.executeUpdate(sql1);
                    } catch (Exception e) {}
                }
            }

            //STEP 5: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}