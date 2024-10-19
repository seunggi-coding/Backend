// scripts/initialize-data.js
const mongoclient = require("mongodb").MongoClient;

async function initializeData() {
    try {
        const mydb = (await mongoclient.connect(process.env.DB_URL)).db("project");
    
        const bookCount = await mydb.collection("book").countDocuments();
    
        if (bookCount === 0) {
            await mydb.collection("book").insertMany([
                {
                    title: "이처럼 사소한 것들",
                    author: "클레이 키건",
                    publisher: "다산책방",
                    category: "소설",
                    img: "/public/image/novel1.jpg"
                },
                {
                    title: "모순",
                    author: "양귀자",
                    publisher: "쓰다",
                    category: "소설",
                    img: "/public/image/novel2.jpg"
                },
                {
                    title: "남의 시선에 아랑곳하지 않기",
                    author: "차이웨이",
                    publisher: "미디어숲",
                    category: "자기계발",
                    img: "/public/image/personal_development1.jpg"
                },
                {
                    title: "똑똑한 사람은 어떻게 생각하고 질문하는가",
                    author: "이시한",
                    publisher: "북플레저",
                    category: "자기계발",
                    img: "/public/image/personal_development2.jpg"
                },
                {
                    title: "컬처, 문화로 쓴 세계사",
                    author: "마틴 푸크너",
                    publisher: "어크로스",
                    category: "역사/문화",
                    img: "/public/image/history_culture1.jpg"
                }
            ]);
            console.log("초기 데이터 삽입 완료");
            return mydb;
        } else {
            console.log("이미 초기 데이터가 존재합니다.");
            return mydb;
        }
    } catch (err) {
        console.error("초기 데이터 삽입 실패:", err);
        throw err
    }
}

module.exports = initializeData;
