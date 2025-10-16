import 'dart:math';

import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  static const String _title = 'Flutter Stateful Clicker Counter';
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
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

class _MyHomePageState extends State<MyHomePage> {
  var colors = [
    Colors.grey,
    Colors.red,
    Colors.blue,
    Colors.green,
    Colors.yellow
  ];
  var indexes = [0, 0, 0, 0];

  var winningComb = [0, 0, 0, 0];

  String message = "Guess the colors!";

  var remainingGuesses = 5;

 List<List<int>> get guesses => List.generate(5, (_) => List.generate(colors.length, (_) => 0)); // empty thing of guesses
  

  @override
  void initState() {
    init();
    super.initState();
  }

  void init() {
    var rng = Random();
    
    for (var i = 0; i < indexes.length; i++) {
      winningComb[i] = rng.nextInt(colors.length - 1) + 1;
      print(winningComb[i]);
    }
  }

  void changeColor(int index) {
    setState(() {
      if (indexes[index] == colors.length - 1) {
        indexes[index] = 1;
      } else {
        indexes[index]++;
      }
    });
  }

  void buttonAction() async {
    if (checkWin()) {
      setMessage("Won!");

      await Future.delayed(const Duration(seconds: 5), () {
        print("Copite");
        reset();
      });
    } else {
      setMessage("Keep guessing!");
    }
  }

  bool checkWin() {
    for (var i = 0; i < indexes.length; i++) {
      if (indexes[i] != winningComb[i]) return false;
    }

    return true;
  }

  void setMessage(String msg) {
    setState(() {
      message = msg;
    });
  }

  void reset() {
    setState(() {
      for (var i = 0; i < indexes.length; i++) {
        indexes[i] = 0;
      }
      setMessage("Guess the colors!");
      init();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Color guessing'),
      ),
      body: Column(
          mainAxisAlignment: MainAxisAlignment.end,
          children: [
            /*
            
            Text(message, style: TextStyle(fontSize: 20)),
            SizedBox(bottom
                ElevatedButton(
                  onPressed: () => changeColor(0),
                  child: Container(height: 55),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: colors[indexes[0]],
                  ),
                ),
                ElevatedButton(
                  onPressed: () => changeColor(1),
                  child: Container(height: 55),
                  style: ElevatedButton.styleFrom(
                      backgroundColor: colors[indexes[1]]),
                ),
                ElevatedButton(
                  onPressed: () => changeColor(2),
                  child: Container(height: 55),
                  style: ElevatedButton.styleFrom(
                      backgroundColor: colors[indexes[2]]),
                ),
                ElevatedButton(
                  onPressed: () => changeColor(3),
                  child: Container(height: 55),
                  style: ElevatedButton.styleFrom(
                      backgroundColor: colors[indexes[3]]),
                ),
              ],
            ),
          ],
      ),
      /*
      
      floatingActionButton: FloatingActionButton(
        onPressed: () => buttonAction(),
        tooltip: 'Reset',
        child: const Icon(Icons.question_mark),
      ),
       */
    );
  }
}
