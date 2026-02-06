<?php

$url = "https://opentdb.com/api.php?amount=10&category=9&difficulty=medium&type=boolean";
$json = '{
    "response_code": 0,
    "results": [
      {
        "type": "multiple",
        "difficulty": "easy",
        "category": "General Knowledge",
        "question": "Who is considered the &quot;Father of Modern Philosophy&quot;?",
        "correct_answer": "Ren&eacute; Descartes",
        "incorrect_answers": [
          "Plato",
          "Albert Einstein",
          "Antoine Lavoiser"
        ]
      }
    ],
  }';

$currentQuestion = '';

if (!isset($_SESSION['questions'])) {

  $response = file_get_contents($url);
  $data = json_decode($response, true);

  $_SESSION['questions'] = $data['results'];
  $_SESSION['questionIndex'] = 0;
  $currentQuestion = $data['question'];

} else if (isset($_GET['right'])) {
  $index = 
}

//session_start();


//print_r($data);
//echo $data['results'][0]['question'];
?>

<form action="index.php">
  <h1><?php echo $currentQuestion ?></h1>

  <select id="answer">
    <option value="True">True</option>
    <option value="False">False</option>
  </select>
  <input type="submit" />

</form>