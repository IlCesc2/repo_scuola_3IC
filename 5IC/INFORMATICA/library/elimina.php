<?php

$conn = mysqli_connect("localhost", "root", "", "ricette");

if (false === $conn) {
    exit("Errore: impossibile stabilire una connessione " . mysqli_connect_error());
}


$sql = "DELETE FROM ricetta WHERE Id =?";

$query = $conn->prepare($sql);
$query->bind_param("i",$_GET["id"] );

$query->execute();

$result =  $query->get_result();// mysqli_query($conn, $sql);

if ($result === false) {
    exit("Errore: impossibile eseguire la query." . mysqli_error($conn));
} else {
    echo "Cancellato con Successo!";
}


?>