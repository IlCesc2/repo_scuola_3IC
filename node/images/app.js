const express = require('express');
const fs = require('fs')
const app = express();
const port = 3000;


//const url = 'http://localhost:3000/src/'

app.get('/', (req, res) => {
    fs.readFile('index.html', function (err, data) {
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.write(data);
        return res.end();
    });
});

app.get('/randImg', (req, res) => {
    const images = fs.readdirSync('src');
    const randInt = Math.floor(Math.random() * images.length);
    //res.sendFile(images[randInt], { root: __dirname+"/src/" })
    res.send('src/'+images[randInt])
});

app.listen(port, () => {
    console.log(`Server is running at http://localhost:${port}`);
});

