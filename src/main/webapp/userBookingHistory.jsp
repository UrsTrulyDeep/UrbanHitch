<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.autoavenue.model.Booking" %>
<%@ page import="com.autoavenue.model.User" %>
<%@ page import="com.autoavenue.service.BookingService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Booking History</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        h2 {
            margin-left: auto;
            margin-right: auto;
            border-collapse: collapse;
            width: 1200px;
            text-align: center;
        }

        table, th, td {
            border: 1px solid blue;
            margin-left: auto;
            margin-right: auto;
            border-collapse: collapse;
            width: 1200px;
            text-align: center;
            font-size: 20px;
        }

        th, td {
            padding: 15px 1px 15px 1px;
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            // Get the logged-in user from the session
            User loggedInUser = (User) session.getAttribute("loggedInUser");

            if (loggedInUser != null) {
                BookingService bookingService = new BookingService();
                List<Booking> bookingHistory = bookingService.getBookingHistoryForUser(loggedInUser);

                if (bookingHistory != null && !bookingHistory.isEmpty()) {
        %>
                    <h2><i class="fas fa-history"></i> User Booking History</h2>
                    <table>
                        <tr>
                            <th><i class="fas fa-id-badge"></i> Booking ID</th>
                            <th><i class="fas fa-map-marker-alt"></i> Source Location</th>
                            <th><i class="fas fa-map-marker-alt"></i> Destination Location</th>
                            <th><i class="fas fa-road"></i> Distance</th>
                            <th><i class="far fa-clock"></i> Estimated Time</th>
                            <th><i class="fas fa-dollar-sign"></i> Cost</th>
                            <th><i class="far fa-clock"></i> Booking Time</th>
                        </tr>
        <%
                        for (Booking booking : bookingHistory) {
        %>
                            <tr>
                                <td><%= booking.getBookingId() %></td>
                                <td><%= booking.getSourceLocation() %></td>
                                <td><%= booking.getDestinationLocation() %></td>
                                <td><%= booking.getDistance() %></td>
                                <td><%= booking.getEstimatedTime() %></td>
                                <td><%= booking.getCost() %></td>
                                <td><%= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(booking.getBookingTime()) %></td>
                            </tr>
        <%
                        }
        %>
                    </table>
        <%
                } else {
        %>
                    <p style="margin: 200px; text-align: center; color: red;">No booking history found.</p>
        <%
                }
            } else {
        %>
                <p style="margin: 200px; text-align: center; color: red;">User not logged in.</p>
        <%
            }
        %>
    </div>
</body>
</html>
