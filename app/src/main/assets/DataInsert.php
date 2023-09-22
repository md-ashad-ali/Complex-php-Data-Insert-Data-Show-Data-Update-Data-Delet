<?php

$conect = mysqli_connect("localhost","id21209981_mydata","@Ashad2023#","id21209981_mydata");


$name = $_GET['n'];
$number = $_GET['p'];
$email = $_GET['e'];

$dataInsert = " INSERT INTO UserInfo (Id, Name, Number, Email)VALUES (NULL, '$name', '$number', '$email')  ";

$result = mysqli_query($conect,$dataInsert);

if($result)
{
    echo "data isert succesfully<br>";
}else{
    echo "server is not responding<br>";
}

if($conect) echo "conected sccesfully<br>";
else echo "not conected.....<br>";



?>