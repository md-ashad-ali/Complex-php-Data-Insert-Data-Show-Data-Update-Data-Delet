<?php

$conect = mysqli_connect("localhost","id21209981_mydata","@Ashad2023#","id21209981_mydata");

$id= $_GET['ie'];


$delet = " DELETE FROM UserInfo WHERE Id = $id ";

$result = mysqli_query($conect,$delet);




?>