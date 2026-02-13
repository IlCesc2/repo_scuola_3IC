<html>

<body>
    <?php
    session_start();
    if (isset($_SESSION["username"]) && isset($_COOKIE["username"])) {
        echo "The user: '" . $_SESSION["username"] . "' has logged last at " . $_COOKIE["lastLogin"] . "<br>" ."<a href='logout.php'>Logout</a>";
    } else {
        echo "You are not logged in!";
    }
    ?>
    
</body>

</html>