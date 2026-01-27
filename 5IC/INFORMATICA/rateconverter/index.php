

<?php
$quantity = 0;



if (isset($_GET['quantity'])) {
    $base = $_GET['in'];
    $target = $_GET['out'];


    $content = file_get_contents("https://hexarate.paikama.co/api/rates/" . $base . "/" . $target . "/latest"); // &quot;
    $result = json_decode($content, true);
    //print_r($result);
    
    $out = $_GET['quantity'] * $result["data"]['mid'];
    print_r("The result is: " . $out);
}
?>

<form>
    <input type="number" name="quantity" id="quantity">
    <select id="in" name="in">
        <option value="EUR">EUR</option>
        <option value="USD">USD</option>
        <option value="RUB">RUB</option>
        <option value="GBP">GBP</option>
    </select>

    <select id="out" name="out">
        <option value="EUR">EUR</option>
        <option value="USD">USD</option>
        <option value="RUB">RUB</option>
        <option value="GBP">GBP</option>
    </select>

    <input type="submit">
</form>