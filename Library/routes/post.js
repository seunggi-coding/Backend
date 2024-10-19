var router = require('express').Router();

const mongoclient = require("mongodb").MongoClient;
const ObjId = require("mongodb").ObjectId;
const url = process.env.DB_URL;

let mydb;
mongoclient
  .connect(url)
  .then((client) => {
    mydb = client.db("project");
  })
  .catch((err) => {
    console.log(err);
  });

  const sha = require('sha256');
  let session = require("express-session");
  router.use(
    session({
      secret: "dkufe8938493j4e08349u",
      resave: false,
      saveUninitialized: true,
    })
  );

  router.get("/list", function (req, res) {
    mydb
      .collection("book")
      .find()
      .toArray()
      .then((result) => {
        console.log(result);
        res.render("list.ejs", { allBooks: result });
      });
  });

  router.get("/library/book/:id", function (req, res) {
    if (!req.session.user) {
      return res.redirect("/login");
    }
    console.log(req.session);
    const user = req.session.user;
  
    console.log(req.params.id);
    mydb
      .collection("book")
      .findOne({ _id: new ObjId(req.params.id) })
      .then((result) => {
        console.log("result: ", result);
        res.render("content.ejs", { book: result, user: user });
      });
  });

module.exports = router;