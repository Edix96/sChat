
const functions = require('firebase-functions');

const admin = require ('firebase-admin');

admin.initializeApp();

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
/*exports.helloWorld = functions.https.onRequest((request, response) => {
response.send("Hello from Firebase!");
 });*/


 exports.onUserCreate = functions.database
 .ref("/Users/{usersId}")
 .onCreate((snapshot, context)=>{

   const nonce = random128Hex()
   const nextnonce = random128Hex()

   return snapshot.ref.update({nonce : nonce, nextnonce : nextnonce })
 })


exports.onNonceCreate = functions.database
.ref("/Users/{usersId}/")
.onUpdate((change, context)=> {

  const userDataBefore = change.before.val()
  const userData = change.after.val();
  console.log("Before:")
  console.log(userDataBefore)
  console.log("After:")
  console.log(userData)


  if (userDataBefore.response === userData.response){
    console.log("Nao houve mudança")
    return null
  }

  const nonce = userData.nextnonce
  const nextnonce = random128Hex();

  return change.after.ref.update({nonce, nextnonce})
 })

  function random128Hex() {
    function random16Hex() { return (0x10000 | Math.random() * 0x10000).toString(16).substr(1); }
    return random16Hex() + random16Hex() +
     "-" + random16Hex() +
     "-" + random16Hex() +
     "-" + random16Hex() +
     "-" + random16Hex() + random16Hex() + random16Hex();
  }