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



<form action="add.php" method="POST">
    <input type="text" id="title" name="title" placeholder="Titolo" required>
    <input type="text" required>

    <select id="author" name="author" required>
        <?php
        $conn = mysqli_connect("localhost", "root", "", "ricette");

        $queryAutori = "SELECT * FROM autore";
        $autori = mysqli_query($conn, $queryAutori);

        foreach ($autori as $value) {
            echo '<option value=' . $value["id"] . '>' . $value["nome"] . '</option>';
        };
        ?>
    </select>
    <input type="number" id="minuti" name="minuti" placeholder="Tempo (in minuti)" required>
    <input type="number" id="numero_ingredienti" name="numero_ingredienti" placeholder="Numero di ingredienti" required>

    <select name="difficulty" id="difficulty" placeholder="Difficulty" required>
        <option value="Easy">Facile</option>
        <option value="Medium">Medio</option>
        <option value="Hard">Difficile</option>
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