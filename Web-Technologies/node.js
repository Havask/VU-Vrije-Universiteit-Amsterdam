const sqlite3 = require('sqlite3').verbose();
const express = require("express");
var bodyParser = require('body-parser');
var app = express();

//sets up the server
const HTTP_PORT = 3000
app.listen(HTTP_PORT, () => {
    console.log("Server is listening on port " + HTTP_PORT);
});

const db = new sqlite3.Database('./db/database.db', (err) => {
  if (err) {
      console.error("Erro opening database " + err.message);
  } else {
    
    console.log('Connected to the author database.');

      db.run('CREATE TABLE authors( \
          author_id INTEGER PRIMARY KEY,\
          author,\
          alt ,\
          tags ,\
          image,\
          description \
      )', (err, rows)=>{
          
          let insert = 'INSERT INTO authors (author, alt, tags, image, description) VALUES (?,?,?,?,?)';
          db.run(insert, ["Grace Hopper", "Image of Grace Hopper at the UNIVAC I console", "programming,linking,navy", "https://upload.wikimedia.org/wikipedia/commons/3/37/Grace_Hopper_and_UNIVAC.jpg", "	Long description of image of Grace."]);
          db.run(insert, ["Enrico Fermi", "	Image of Fermi", "prof,manhattan,smart", "http://t1.gstatic.com/licensed-image?q=tbn:ANd9GcRVHVIpMCnQ_mmX4bzGBeiHx3d0k4y3H5LTF7ox0hD65ADHrA2lR3Kw1ouqiFemFFG_", "	Long description of image of Fermi."]);
          db.run(insert, ["Otto Hahn", "Image of Hahn", "prof,man,smart", "http://t1.gstatic.com/licensed-image?q=tbn:ANd9GcQDW9R_fsu5YgEkl26Nkt8g7rUcVo2vxcg7sBAs8Yq3_ZaDy8Y_TYhUhIrDhvaHvaZN", "Long description of image of Hahn"]);

      });
  }
});

//allows the content type
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Headers", "Content-Type");
  next();
  });

app.use(bodyParser.json());

//Get request method that returns the specified json object
app.get('/authors', (req, res) => {
  db.all('SELECT * FROM authors', (err, rows) => {
      if (err) {
          res.status(500).json({ error: err.message });
          return;
      }
      res.status(200).json(rows);
  });
});

//Post request that creates a new resource
app.post("/authors/", (req, res) => {

  var reqBody = req.body;

  db.run(`INSERT INTO authors (author, alt, tags,image, description) VALUES (?,?,?,?,?)`,
      [reqBody.author, reqBody.alt, reqBody.tags, reqBody.image, reqBody.description],
      function (err, result) {
          if (err) {
              res.status(400).json({ "error": err.message })
              return;
          }
          res.status(201).json({
              "author_id": this.lastID
          })
      });
});

//Put method that updates a existing resource
app.put("/authors/:author_id", (req, res) => {
  var reqBody = req.body;

  db.run(`UPDATE authors set author = ?, alt = ?, tags = ?, image = ?, description = ? WHERE author_id = ?`,
      [reqBody.author, reqBody.alt, reqBody.tags, reqBody.image, reqBody.description, reqBody.author_id],
      function (err) {
          if (err) {
              res.status(400).json({ "error": err.message });
              return;
          }
          res.status(200).json();
      });
});

//Delete request that deletes a specific field in the json object
app.delete("/authors/:author_id", (req, res) => {
  db.run(`DELETE FROM authors WHERE author_id = ?`,
  req.params.author_id,
      function (err, result) {
          if (err) {
              res.status(400).json({ "error": res.message })
              return;
          }
          res.status(200).json({ deletedID: this.changes });
      });
});