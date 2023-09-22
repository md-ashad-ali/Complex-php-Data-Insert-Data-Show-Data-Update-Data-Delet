<?php

$conect = mysqli_connect("localhost","id21209981_mydata","@Ashad2023#","id21209981_mydata");

$id= $_GET['io'];
$name = $_GET['no'];
$number = $_GET['po'];
$email = $_GET['eo'];

$update = " UPDATE UserInfo SET Name = '$name',Number = '$number',Email = '$email' WHERE Id = $id ";

$result = mysqli_query($conect,$update);




?>