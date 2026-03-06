<?php
$conn = mysqli_connect("localhost", "root", "", "ricette");

if (false === $conn) {
    exit("Errore: impossibile stabilire una connessione " . mysqli_connect_error());
}

if (isset($_POST["title"])) {

    $title = $_POST["title"];
    $autore = $_POST["autore"];
    $tempo = $_POST["tempo"];
    $num_ingr = $_POST["numero_ingredienti"];
    $difficolta = $_POST["difficoltà"];

    $sql = "INSERT INTO ricetta (title, AutoreID, tempo, numero_ingredienti, difficolta) VALUES ($title, $autore, $tempo, $num_ingr,$difficolta)";
    $result = mysqli_query($conn, $sql);

    mysqli_free_result($result);
    mysqli_close($conn);
}


?>


<h1>Modifica</h1>
<form action="modifica.php" method="POST">
    <?php

    $id = $_GET["id"];
    $conn = mysqli_connect("localhost", "root", "", "ricette");

    $queryRicette = "SELECT * FROM ricetta WHERE id=" . $id;
    $ricetta = mysqli_query($conn, $queryRicette);
    //print_r($ricetta);
    //foreach ($ricetta as $riga)
    //echo "<input type = 'text'>".$riga['title'];


    $ass_result = mysqli_fetch_assoc($ricetta);

    print_r($ass_result);

    foreach ($ass_result as $key => $value) {
        echo $key." ".$value;
        if ($key == "Id")
            continue;

        echo '<input type="text" name=' . $key . ' id=' . $key . ' value=' . $value . '>';
    }
    ;

    echo '<select id="author" name="author" required>';

    $queryAutori = "SELECT * FROM autore";
    $autori = mysqli_query($conn, $queryAutori);

    foreach ($autori as $value) {
        echo '<option value=' . $value["id"] . 'selected=' . ($value["id"] = $id) . '>' . $value["nome"] . '</option>';
    }
    ;
    echo '</select>'


        ?>


    <input type="submit">
</form>
<style>
    form {
        display: flex;
        flex-direction: column;
        width: fit-content;
        gap: 10px;
    }
</style>