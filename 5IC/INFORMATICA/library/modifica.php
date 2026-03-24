<?php
$conn = mysqli_connect("localhost", "root", "", "ricette");

if (false === $conn) {
    exit("Errore: impossibile stabilire una connessione " . mysqli_connect_error());
}

if (isset($_POST["title"])) {

    $title = $_POST["title"];
    $autore = $_POST["AutoreID"];
    $tempo = $_POST["tempo"];
    $num_ingr = $_POST["numero_ingredienti"];
    $difficolta = $_POST["difficulty"];

    $sql = "INSERT INTO ricetta (title, AutoreID, tempo, numero_ingredienti, difficolta) VALUES (?,?,?,?,?)";
    
    $query = $conn->prepare($sql);
    $query->bind_param("siiis",$title, $autore, $tempo, $num_ingr,$difficolta );

    $query->execute();

    $result =  $query->get_result(); 

    mysqli_close($conn);
}


?>


<h1>Modifica</h1>
<form action="modifica.php" method="POST">
    <?php

    if(!isset($_GET["id"])) {
        echo( "<h2>Bro aggiungi un id</h2>");
        return null;
    }
    $id = $_GET["id"];
    $conn = mysqli_connect("localhost", "root", "", "ricette");

    $queryRicette = "SELECT * FROM ricetta WHERE id=?" ;

    $query = $conn->prepare($queryRicette);
    $query->bind_param("i",$id );

    $query->execute();


    $ricetta = $query->get_result();
    //print_r($ricetta);
    //foreach ($ricetta as $riga)
    //echo "<input type = 'text'>".$riga['title'];


    $ass_result = mysqli_fetch_assoc($ricetta);

    //print_r($ass_result);

    foreach ($ass_result as $key => $value) {
        //echo $key." ".$value;
        if ($key == "Id" || $key == "difficolta")
            continue;

        echo '<input type="text" name=' . $key . ' id=' . $key . ' value=' . $value . '>';
    };

    // difficolta
    $difficolta = ["Facile", "Medio", "Difficile"];

    echo '<select id="difficulty" name="difficulty" required>';
    foreach ($difficolta as $d) {
        echo '<option value=' . $d . ' selected=' . ($ass_result["difficolta"] == $d) . '>' . $d . '</option>';
    };
    echo '</select>';

    // autori    
    $queryAutori = "SELECT * FROM autore";
    $autori = mysqli_query($conn, $queryAutori);
    
    echo '<select id="author" name="author" required>';
    foreach ($autori as $value) {
        echo '<option value=' . $value["id"] . ' selected=' . ($value["id"] = $id) . '>' . $value["nome"] . '</option>';
    };
    echo '</select>';


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