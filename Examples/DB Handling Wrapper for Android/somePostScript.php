<?php
$con = mysqli_connect("localhost", "root", "root");
if(mysqli_connect_errno($connect))
{
    echo "Failed to connect to MySQL: " . mysql_connect_error();
}
else
{
    echo "success";
}
// If the value is set, then assign the value from the Android program, otherwise die and send error message.
$value = isset($_POST['value']) ? $_POST['value'] : die('No data sent');

$query = mysqli_query($connect, "insert into profiles(columnName) values ('$value') ");

mysqli_close($connect);
echo "Finished";
?>