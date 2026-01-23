<?php
//print_r($_GET);

$punteggio = 0;
$x = 0;
$y = 0;
$oldTile = ".";
$d = "";

$isGameOver = false;

$mappa = [
    ['P', '.', '#', '.', '.', '~', 'T', '.', '.', '.'],
    ['~', '.', '.', '#', '.', '.', 'T', 'T', '.', '.'],
    ['#', '~', '.', '.', '.', 'T', '.', '.', '.', '.'],
    ['T', '.', '.', '.', '.', '.', '.', '.', '.', '.'],
    ['.', '.', '.', '.', '~', '#', '#', '.', '.', 'E'], // Uscita alla posizione (4, 9)
];


if (isset($_GET['punteggio'])) {
    $punteggio = $_GET['punteggio'];
    $x = $_GET['x'];
    $y = $_GET['y'];
    $oldTile = $_GET['oldTile'];
    if (isset($_GET['nord'])) {
        $d = "nord";
    } else if (isset($_GET['sud'])) {
        $d = "sud";
    } else if (isset($_GET['est'])) {
        $d = "est";
    } else {
        $d = "ovest";
    }

    //smth
    if($x !== 0 && $y !== 0){
        $mappa[0][0] =".";
    }

    // direction
    $xD = 0;
    $yD = 0;
    switch ($d) {
        case "nord":
            //echo "1";
            $yD = -1;
            break;

        case "sud":
            //echo "2";
            $yD = 1;

            break;

        case "est":
            //echo "3";

            $xD = 1;

            break;

        case "ovest":
            //echo "4";
            $xD = -1;
            break;

    }

    //movement

    $mappa[$y][$x] = $oldTile;
    $calcPointsX = false;
    $calcPointsY = false;
    $xP = $x + $xD;
    $yP = $y + $yD;


    if ($xP < count($mappa[0]) && $xP >= 0) {
        $x = $xP;
        $oldTile = $mappa[$y][$x] === "P" ? ".": $mappa[$y][$x];
        $calcPointsX = true;
    }


    if ($yP < count($mappa) && $yP >= 0) {
        $y = $yP;
        $oldTile = $mappa[$y][$x] === "P" ? ".": $mappa[$y][$x];
        $calcPointsY = true;
    }

    // points

    if ($calcPointsX && $calcPointsY) {
        switch ($mappa[$y][$x]) {
            case ".":
                $punteggio += 1;
                break;
            case "#":
                $punteggio += -1;
                break;
            case "~":
                $punteggio += -2;
                break;
            case "T":
                $punteggio += 2;
                break;
            case "E":
                $isGameOver=true;
                echo "The game is Over!";
                break;
        }
    }


    // we move the p later so that we can calc the points on the right spot
    $mappa[$y][$x] = "P";

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

if(!$isGameOver){
    stampaMappa($mappa);
    echo "Punteggio " . $punteggio;
}

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