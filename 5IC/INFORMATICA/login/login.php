<?php


$username = "us";
$password = "pw";
print_r($_POST);
if (isset($_POST["username"]) && isset($_POST["password"])) {
    $login = $_POST["username"];
    $pw = $_POST["password"];

    if ($username == $login && $password == $pw) {
        session_start();

        $_SESSION["username"] = $login;
        $_SESSION["password"] = $pw;
        setcookie( "username", $login, time() + (86400 /24), "/"); // 86400 = 1 day
        setcookie( "lastLogin", date('Y-m-d H:i:s', time()), time() + (86400 /24), "/"); // 86400 = 1 day

        header("Location:welcome.php");
        exit;
    }
}

?>

<html>

<body>

    <form action="login.php" method="post" style="display:flex; flex-direction: column; gap: 10px; width: fit-content;">
        <h1>SUPER COOL LOGIN</h1>
        <input id="username" name="username" type="text" placeholder="username"/>
        <input id="passsword" name="password" type="password" placeholder="password"/>
        <input type="submit">
    </form>
    

</body>

</html>