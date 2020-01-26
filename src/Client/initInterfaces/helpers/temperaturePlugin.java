package Client.initInterfaces.helpers;

import Client.Interface.Plugin;
import Client.initInterfaces.Request;
import Client.initInterfaces.Response;
import java.sql.*;

public class temperaturePlugin implements Plugin {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public float canHandle(Request req) {
        if (req.getUrl().getRawUrl().startsWith("/temp")) {
            return 1.0f;
        } else {
            return 0.0f;
        }
    }

    @Override
    public Response handle(Request req) {
        Response response = new Response();

        if (req.getUrl().getRawUrl().matches("/temp/\\d\\d\\d\\d/\\d\\d/\\d\\d")) {
            String time =
                    req.getUrl().getRawUrl().split("[/|?]")[2] + "-" +
                            req.getUrl().getRawUrl().split("[/|?]")[3] + "-" +
                            req.getUrl().getRawUrl().split("[/|?]")[4] + " 00:00:00";

            Timestamp ts = Timestamp.valueOf(time);
            response.setContent(getDataXML(ts));
        } else if (req.getUrl().getRawUrl().startsWith("/temp/search")) {

            String date = req.getUrl().getParameter().get("date");
            Timestamp ts = Timestamp.valueOf(date + " 00:00:00");

            response.setContent("<html><body>" +
                    "<h1>Temperatures:</h1>" +
                    "<form action=\"/temp/search\">\n" +
                    "  <input type=\"date\" name=\"date\" value=" + date + ">\n" +
                    "  <input type=\"submit\" value=\"Search\">\n" +
                    "</form> " +
                    "<form action=\"/temp\">\n" +
                    "  <input type=\"submit\" value=\"Overview\">\n" +
                    "</form> " +
                    "" +
                    "" +
                    "<br>" + getDataByDate(ts) + "</body></html>");
        } else {
            int offset = 0;
            int limit = 42;

            String param = null;

            try {
                param = req.getUrl().getParameter().get("offset");
                if (param != null) {
                    offset = Integer.parseInt(req.getUrl().getParameter().get("offset"));
                    if (offset < 0) offset = 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                param = req.getUrl().getParameter().get("limit");
                if (param != null) {
                    limit = Integer.parseInt(req.getUrl().getParameter().get("limit"));
                    if (limit < 0) limit = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.setContent("<html><body>" +
                    "<h1 style='color:orange'>Temperatures:</h1>" +
                    "<form action=\"/temp/search\">\n" +
                    "  Search date:<br>\n" +
                    "  <input type=\"date\" name=\"date\" value=\"2019-12-05\">\n" +
                    "  <input type=\"submit\" value=\"Submit\">\n" +
                    "</form> " +
                    "" +
                    "" +
                    "<br>" + getData(limit, offset) + "</body></html>");
        }

        return response;
    }

    /**
     * @return database connection
     * @throws SQLException
     */
    private Connection buildConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tempdb", "root", "");
    }

    private String getDataByDate(Timestamp date) {
        StringBuilder res = new StringBuilder(
                "<table border='1'><tr><td><a>Date&nbsp;&nbsp;</a></td><td><a>Temperature&nbsp;&nbsp;</a></td></tr>");

        try {
            Connection conn = buildConnection();
            PreparedStatement query = conn.prepareStatement("SELECT * FROM temperaturdb2 WHERE Datum = ? ORDER BY Datum DESC;");
            query.setTimestamp(1, date);

            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                res.append("<tr><td><a>");
                res.append(resultSet.getString("Datum"));
                res.append("</a></td><td><a>");
                res.append(resultSet.getString("Temp"));
                res.append("</a></td></tr>");
            }
            res.append("</table>");

            resultSet.close();
            query.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    private String getData(int limit, int offset) {
        StringBuilder res = new StringBuilder(
                "<table border='1'><tr><td><a>Date&nbsp;&nbsp;</a></td><td><a>Temperature&nbsp;&nbsp;</a></td></tr>");

        try {
            Connection conn = buildConnection();
            PreparedStatement query = conn.prepareStatement("select * from temperaturdb2 order by Datum desc limit ? offset ?");
            query.setInt(1, limit);
            query.setInt(2, offset);
            ResultSet resultSet = query.executeQuery();

            int i = 0;
            while (resultSet.next()) {
                if (i >= limit) break;
                res.append("<tr><td><a>");
                res.append(resultSet.getString("Datum"));
                res.append("</a></td><td><a>");
                res.append(resultSet.getString("Temp"));
                res.append("</a></td></tr>");
                i++;
            }

            res.append("</table>");

            if (offset == 0) {
                res.append("<a href='?offset=");
                res.append(offset + limit);
                res.append("'>next</a>");
            } else if (offset > 0) {
                res.append("<a href='?offset=");
                res.append(offset - limit);
                res.append("'>prev</a>&nbsp;&nbsp;");
                res.append("<a href='?offset=");
                res.append(offset + limit);
                res.append("'>next</a>");
            }

            resultSet.close();
            query.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res.toString();
    }

    private String getDataXML(Timestamp time) {
        StringBuilder res = new StringBuilder("<?xml version='1.0'?><temperatures>");

        try {
            Connection conn = buildConnection();

            PreparedStatement query = conn.prepareStatement("SELECT * FROM temperaturdb2 WHERE Datum = ? ORDER BY Datum DESC;");
            query.setTimestamp(1, time);

            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                res.append("<entry><daytemperature>");
                res.append(resultSet.getString("Temp"));
                res.append("</daytemperature><date>");
                res.append(resultSet.getString("Datum"));
                res.append("</date></entry>");
            }
            res.append("</temperatures>");

            resultSet.close();
            query.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    @Override
    public String toString() {
        return "temperaturePlugin{}";
    }
}