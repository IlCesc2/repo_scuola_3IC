<?php

session_start();
?>
<html>

<body>
    <?php

    session_unset();
    session_destroy();

    setcookie("username", "", time() - (86400 / 24), "/"); // 86400 = 1 day
    setcookie("lastLogin", "", time() - (86400 / 24), "/"); // 86400 = 1 day
    
    echo "You have successfully logged out"
    ?>
</body>

</html>