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
    $difficolta = $_POST["difficolta"];

    print_r($_POST);

    $sql = "INSERT INTO ricetta (title, AutoreID, tempo, numero_ingredienti, difficolta) VALUES (?,?,?,?,?)";

    
    $query = $conn->prepare($sql);
    $query->bind_param("siiis",$title, $autore, $tempo, $num_ingr,$difficolta );

    $query->execute();

    $result =  $query->get_result(); //mysqli_query($conn, $sql);

    mysqli_close($conn);
}


?>

<h1>Aggiungi nuova ricetta</h1>

<form action="add.php" method="POST">
    <input type="text" id="title" name="title" placeholder="Titolo" required>
  <!--  <input type="text" required> -->

    <select id="autore" name="autore" required>
        <?php
        $conn = mysqli_connect("localhost", "root", "", "ricette");

        $queryAutori = "SELECT * FROM autore";
        $autori = mysqli_query($conn, $queryAutori);

        foreach ($autori as $value) {
            echo '<option value=' . $value["id"] . '>' . $value["nome"] . '</option>';
        };
        ?>
    </select>
    <input type="number" id="tempo" name="tempo" placeholder="Tempo (in minuti)" required>
    <input type="number" id="numero_ingredienti" name="numero_ingredienti" placeholder="Numero di ingredienti" required>

    <select name="difficolta" id="difficolta" placeholder="difficolta" required>
        <option value="Facile">Facile</option>
        <option value="Medio">Medio</option>
        <option value="Difficile">Difficile</option>
    </select>

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