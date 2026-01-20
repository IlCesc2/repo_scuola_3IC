<?php
//print_r($_GET);

$punteggio = 0;
$x = 0;
$y = 0;
$oldTile = ".";
$d = "";

if (isset($_GET['punteggio'])) {
    $punteggio = $_GET['punteggio'];
    $x = $_GET['x'];
    $y = $_GET['y'];
    $oldTile = $_GET['oldTile'];
    if(isset($_GET['nord'])){
        $d = "nord";
    } else if(isset($_GET['sud'])){
        $d = "sud";
    } else if(isset($_GET['est'])){
        $d = "est";
    } else {
        $d = "ovest";
    }

    // direction
    $xD =0;
    $yD =0;
    switch ($d) {
        case "nord":
            echo "1";
            $yD=-1;
            break;

        case "sud":
            echo "2";
            $yD=1;

            break;

        case "est":
            echo "3";

            $xD=1;

            break;

        case "ovest":
            echo "4";
            $xD=-1;
            break;

    }

    //movement

    $mappa[$y][$x] = $oldTile;

    $xP = $x + $xD;
    $yP = $y + $yD;


    if ($xP < count($mappa) && $xP >= 0) {
        $oldTile = $mappa[$y][$x];
        $x = $xP;
    }


    if ($yP < count($mappa) && $yP >= 0) {
        $oldTile = $mappa[$y][$x];
        $y = $yP;
    }
    echo count($mappa);
    echo $yP;

    echo "</br>";

    echo $x;
    echo $y;


    $mappa[$y][$x] = "P";


    // points

    switch ($mappa[$y][$x]) {
        case ".":
            $punteggio += 1;
        case "#":
            $punteggio += -1;
        case "~":
            $punteggio += -2;
        case "T":
            $punteggio += 2;
        case "E":
            endGame();
    }

}


$mappa = [
    ['P', '.', '#', '.', '.', '~', 'T', '.', '.', '.'],
    ['~', '.', '.', '#', '.', '.', 'T', 'T', '.', '.'],
    ['#', '~', '.', '.', '.', 'T', '.', '.', '.', '.'],
    ['T', '.', '.', '.', '.', '.', '.', '.', '.', '.'],
    ['.', '.', '.', '.', '~', '#', '#', '.', '.', 'E'], // Uscita alla posizione (4, 9)
];


function endGame()
{
    echo "The game is Over!";
}

// Funzione per stampare la mappa
function stampaMappa($mappa)
{
    echo "<pre>"; // Per formattazione leggibile
    foreach ($mappa as $riga) {
        echo implode(" ", $riga) . "\n";
    }
    echo "</pre>";
}

stampaMappa($mappa);
echo "Punteggio " . $punteggio;


?>

<form action="index.php" method="GET">
    <input type="hidden" id="punteggio" name="punteggio" value="<?php echo $punteggio; ?>" />
    <input type="hidden" id="x" name="x" value="<?php echo $x; ?>" />
    <input type="hidden" id="y" name="y" value="<?php echo $y; ?>" />
    <input type="hidden" id="oldTile" name="oldTile" value="<?php echo $oldTile; ?>" />
    <input type="submit" name="nord" id="nord" value="nord" />
    <input type="submit" name="sud" id="sud" value="sud" />
    <input type="submit" name="ovest" id="ovest" value="ovest" />
    <input type="submit" name="est" id="est" value="est" />
</form>