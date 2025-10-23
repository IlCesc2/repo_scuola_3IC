import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:word_generator/word_generator.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  static const String _title = 'Flutter Stateful Clicker Counter';
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        // useMaterial3: false,
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});
  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.
  // This class is the configuration for the state.
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

final DEFAULT_TEXT = "Hello! Guess which word I'm thinking?";
final WINNING_TEXT = "You won! GG";
final LOSING_TEXT = "You have lost.. the word was ";

class _MyHomePageState extends State<MyHomePage> {
  var currentGuess = 0;
  var maxGuesses = 5;

  var rightColor = Colors.green;
  var maybeColor = Colors.yellow;

  String title = DEFAULT_TEXT;

  String word = "";
  late List<List<TextEditingController>> fields;

  late List<List<MaterialColor>> fieldColors;

  @override
  void initState() {
    init();
    super.initState();
  }

  void init() {
    final r = Random();
    final wordGenerator = WordGenerator();

    while (word.length != 5) {
      word = r.nextInt(2) == 0
          ? wordGenerator.randomNoun()
          : wordGenerator.randomVerb();
    }

    fields = List.generate(
      5,
      (_) => List.generate(5, (_) => TextEditingController()),
    );
    fieldColors = List.generate(5, (_) => List.filled(5, Colors.grey));

    //print(word);
  }

  void reset() {
    setText(DEFAULT_TEXT);
    currentGuess = 0;
    maxGuesses = 5;
    init();
  }

  void buttonAction() async {
    setState(() {
      List<TextEditingController> f = fields[currentGuess];
      String nonGuessedLetters = word;
      for (var i = 0; i < maxGuesses; i++) {
        String currentTile = f[i].text.toLowerCase();
        if (currentTile.isEmpty) {
          return;
        } else if (currentTile == word[i]) {
          fieldColors[currentGuess][i] = rightColor;
          nonGuessedLetters = replaceCharAt(nonGuessedLetters, i, " ");
        } else if (nonGuessedLetters.contains(currentTile)) {
          fieldColors[currentGuess][i] = maybeColor;
        }
      }

      if (nonGuessedLetters.isEmpty) {
        currentGuess = -2;
        setText(WINNING_TEXT);
      } else if (currentGuess == maxGuesses - 1) {
        setText(LOSING_TEXT + word);
      }
      currentGuess++;
    });
  }

  void setText(String t) {
    setState(() {
      title = t;
    });
  }

  String replaceCharAt(String oldString, int index, String newChar) {
    return oldString.substring(0, index) +
        newChar +
        oldString.substring(index + 1);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text(
          'Wordle',
          style: TextStyle(
            fontSize: 25,
            fontWeight: FontWeight.w700,
            color: Colors.white,
          ),
        ),
        backgroundColor: Colors.blueGrey,
      ),
      body: Center(
        child: Column(
          spacing: 20,
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              title,
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            for (int i = 0; i < maxGuesses; i++)
              Row(
                spacing: 10,
                mainAxisAlignment: MainAxisAlignment.center,

                children: [
                  for (int j = 0; j < maxGuesses; j++)
                    Container(
                      width: 60,
                      child: TextField(
                        maxLength: 1,
                        controller: fields[i][j],
                        enabled: currentGuess == i,
                        textAlign: TextAlign.center,
                        style: TextStyle(fontWeight: FontWeight.bold),

                        decoration: InputDecoration(
                          enabledBorder: OutlineInputBorder(
                            borderSide: BorderSide(width: 2),
                          ),
                          border: OutlineInputBorder(),
                          filled: i < currentGuess,
                          fillColor: fieldColors[i][j],
                          counterText: '',
                        ),

                        inputFormatters: [
                          // USE TO CAPITALISE LETTERSS
                          TextInputFormatter.withFunction((oldValue, newValue) {
                            return TextEditingValue(
                              text: newValue.text.toUpperCase(),
                              selection: newValue.selection,
                            );
                          }),
                        ],
                      ),
                    ),
                ],
              ),

            Padding(
              padding: const EdgeInsets.only(top: 40),
              child: ElevatedButton(
                onPressed: () =>
                    currentGuess == maxGuesses ? reset() : buttonAction(),
                style: ElevatedButton.styleFrom(
                  backgroundColor: (currentGuess == maxGuesses
                      ? Colors.red
                      : Colors.blueGrey),
                ),
                child: Padding(
                  padding: const EdgeInsets.only(
                    left: 70,
                    right: 70,
                    top: 15,
                    bottom: 15,
                  ),
                  child: Text(
                    (currentGuess == maxGuesses ? "RESET" : "GUESS"),
                    style: TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        foregroundColor: Colors.white,
        backgroundColor: Colors.blueAccent,
        shape: CircleBorder(),
        onPressed: () {
          showDialog(
            context: context,
            builder: (context) => AlertDialog(
              title: Text('How to Play'),
              content: Text(
                'Guess the word in $maxGuesses tries.\n\n'
                'Each guess must be a valid 5-letter word\n'
                'After each guess, the color of the tiles will change:\n\n'
                '• Green: Letter is correct and in the right spot\n'
                '• Yellow: Letter is in the word but wrong spot\n'
                '• Gray: Letter is not in the word\n\n'
                'Good luck!',
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: Text('Got it!'),
                ),
              ],
            ),
          );
        },
        child: Icon(Icons.question_mark),
      ),
    );
  }
}
