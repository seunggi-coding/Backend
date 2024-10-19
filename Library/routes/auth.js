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
  
  router.get("/", function (req, res) {
    mydb
      .collection("book")
      .find()
      .limit(5)
      .toArray()
      .then((featuredBooks) => {
        console.log(featuredBooks);
        if (req.session.user) {
          console.log("세션 유지");
          res.render("index.ejs", { user: req.session.user, featuredBooks });
        } else {
          console.log("user : null");
          res.render("index.ejs", { user: null, featuredBooks });
        }
      })
      .catch((err) => {
        console.error("메인 페이지 렌더링 실패:", err);
        res.status(500).send("서버 오류 발생");
      });
  });

  router.get("/login", function (req, res) {
    console.log(req.session);
    if (req.session.user) {
      console.log("세션 유지");
      res.render("index.ejs", { user: req.session.user });
    } else {
      console.log("로그인 페이지");
      res.render("login.ejs");
    }
  });

  router.post("/login", function (req, res) {
    console.log("이메일 : " + req.body.email);
    console.log("비밀번호 : " + req.body.password);
  
    mydb
      .collection("account")
      .findOne({ email: req.body.email })
      .then((result) => {
        console.log(result);
        if (result.password == sha(req.body.password)) {
          // 회원 정보를 세션에 저장
          req.session.user = {
            _id: result._id,
            name: result.name,
            email: result.email,
            password: result.password,
          };
  
          console.log("새로운 로그인");
  
          // 추천 도서 목록 가져오기
          mydb
            .collection("book")
            .find()
            .toArray()
            .then((books) => {
              // 추천 도서 목록을 선택하여 index.ejs에 전달
              const featuredBooks = books.slice(0, 6);
              res.render("index.ejs", { user: req.session.user, featuredBooks });
            })
            .catch((err) => {
              console.error(err);
              res.render("login.ejs");
            });
        } else {
          res.render("login.ejs");
        }
      });
  });
  
  router.get("/logout", function (req, res) {
    console.log("로그아웃");
    req.session.destroy();
    mydb
      .collection("book")
      .find()
      .limit(5)
      .toArray()
      .then((featuredBooks) => {
        console.log(featuredBooks);
        console.log("user : null");
          res.render("index.ejs", { user: null, featuredBooks });
      })
      .catch((err) => {
        console.error("메인 페이지 렌더링 실패:", err);
        res.status(500).send("서버 오류 발생");
      });
  });
  
  router.get("/register", function (req, res) {
    res.render("register.ejs");
  });
  
  router.post("/register", function (req, res) {
    console.log(req.body.name);
    console.log(sha(req.body.password));
    console.log(req.body.email);

    mydb
      .collection("account")
      .insertOne({
        email: req.body.email,
        name: req.body.name,
        password: sha(req.body.password)
      })
      .then((result) => {
        console.log("회원가입 성공");
      });
    res.redirect("/login");
  });

  router.get("/member/edit", function (req, res) {
    const user = req.session.user;
    console.log(user);
    res.render("edit.ejs", { user: user });
  });

  router.post("/member/edit", function (req, res) {
    const user = req.session.user;
    console.log(user);
    mydb
      .collection("account")
      .updateOne(
        { email: user.email, name: user.name },
        {
          $set: {
            name: req.body.name,
            email: req.body.email,
            password: sha(req.body.password)
          }
        }
      )
      .then((result) => {
        console.log("회원정보 수정 성공");
  
        req.session.user = {
          name: req.body.name,
          email: req.body.email,
          password: sha(req.body.password)
        };
  
        res.redirect("/");
      })
      .catch((err) => {
        console.error("회원정보 수정 실패:", err);
        res.redirect("/member/edit");
      });
  });

  module.exports = router;