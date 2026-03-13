<form action="index.php" method="POST">
    <input type="text" id="title" name="title" placeholder="Titolo" >
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
  

    <select name="difficolta" id="difficolta" placeholder="difficolta" >
        <option value="Facile">Facile</option>
        <option value="Medio">Medio</option>
        <option value="Difficile">Difficile</option>
    </select>

    <input type="submit">
</form>

<table>
    <th>
        <tr>
            Titolo
        </tr>
        <tr>Autore</tr>
        <tr>Tempo</tr>
        <tr>Numero di Ingredienti</tr>
        <tr>Difficulty</tr>
    </th>
    <?php
    $conn = mysqli_connect("localhost", "root", "", "ricette");

    if (false === $conn) {
        exit("Errore: impossibile stabilire una connessione " . mysqli_connect_error());
    }

    $sql = "SELECT * FROM ricetta ";
    

    if (isset($_POST["title"])) {

        $title = $_POST["title"];
        $autore = $_POST["autore"];

        $difficolta = $_POST["difficolta"];
    
        $sql .= " WHERE title LIKE '$title' AND AutoreID=$autore AND difficolta='$difficolta'"; //"INSERT INTO ricetta (title, AutoreID, tempo, numero_ingredienti, difficolta) VALUES ($title, $autore, $tempo, $num_ingr,$difficolta)";
    };



    $result = mysqli_query($conn, $sql);

    if ($result === false) {
        exit("Errore: impossibile eseguire la query." . mysqli_error($conn));
    }

    while (($row = mysqli_fetch_assoc($result)) !== null) {
        echo '<tr>';
        foreach ($row as $key => $value) {
            echo '<td>' . $value . '</td>';
        }
        ;
        echo '<td><a href="http://localhost/library/modifica.php?id=' . $row["Id"] . '"> Modifica </a></td>';
        echo '<td><a href="http://localhost/library/elimina.php?id=' . $row["Id"] . '" 
        onclick="return confirm(\'Sicuro di voler cancellare ' . $row["title"] . '?\')"> Elimina </a></td>';


        echo '</tr>';
    }

    mysqli_free_result($result);


    mysqli_close($conn);
    ?>


</table>



<style>
    table,
    th,
    td,
    tr {
        border: 1px solid black;
    }
</style>