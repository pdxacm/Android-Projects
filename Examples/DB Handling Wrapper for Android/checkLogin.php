<?php
$con = mysql_connect("localhost", "root", "root");
if (!$con)
{
die("Could not connect: " . mysql_error());
}
$username = isset($_POST['username']) ? $_POST['username'] : die("No username");

mysql_select_db("someDB", $con);
$result = mysql_query("SELECT * FROM tableName where username='" . $username . "'");
while ($row = mysql_fetch_assoc($result))
{
$output[] = $row;
// Output the results in JSON to web browser.
print(json_encode($output));
}
mysql_close();
?>
