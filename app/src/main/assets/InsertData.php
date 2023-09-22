<?php


$conect = mysqli_connect("localhost","id21208119_serverading","@Ashasd223#","id21208119_sertveradding");

$insert_data = " SELECT * FROM UserInformation ";

$conected = mysqli_query($conect,$insert_data);

$data = array();

foreach($conected as $item)
{

    $name = $item['name'];
    $number = $item['number'];
    $emal = $item['email'];

    $userinfo['name'] = $name;
    $userinfo['number'] = $number;
    $userinfo['email'] = $emal;
    array_push($data,$userinfo);


}

echo json_encode($data);



?>