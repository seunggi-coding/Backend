const dotenv = require("dotenv").config();

const mongoclient = require("mongodb").MongoClient;
const ObjId = require("mongodb").ObjectId;

const url = process.env.DB_URL;
console.log('MongoDB URL:', process.env.DB_URL);  // 연결 문자열 확인

let mydb;
mongoclient
  .connect(url)
  .then((client) => {
    mydb = client.db("project");
  
    require("./scripts/initialize-data")();
    app.listen(process.env.PORT, function () {
      console.log("포트 2530으로 서버 대기중 ... ");
    });
  })
  .catch((err) => {
    console.log(err);
  });

const express = require("express");
const app = express();

const bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({ extended: true }));
app.set("view engine", "ejs");

app.use("/public", express.static("public"));
app.use('/', require('./routes/post.js'))
app.use('/', require('./routes/auth.js'))
