
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


        $sql = "SELECT * FROM ricetta";

        $result = mysqli_query($conn, $sql);

        if ($result === false) {
            exit("Errore: impossibile eseguire la query." . mysqli_error($conn));
        }


        while (($row = mysqli_fetch_assoc($result)) !== null) {
            echo '<tr>';
            foreach ($row as $key=>$value) {
                echo '<td>' . $value. '</td>';
            };
            echo '</tr>';
        }

        mysqli_free_result($result);


        mysqli_close($conn);
        ?>
    </table>

<style>

table, th, td,tr {
  border:1px solid black;
}
</style>