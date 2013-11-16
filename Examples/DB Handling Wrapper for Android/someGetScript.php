<?php
$con = mysql_connect("localhost", "root", "root");
if (!$con)
{
die("Could not connect: " . mysql_error());
}
mysql_select_db("someDB", $con);
$result = mysql_query("SELECT * FROM table");
while ($row = mysql_fetch_assoc($result))
{
$output[] = $row;
// Echo JSON results to web browser
print(json_encode($output));
}
mysql_close();
?>