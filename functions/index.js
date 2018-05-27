const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp();


exports.updateMessage = functions.https.onCall((data) => {
  const text = data.text;

  return admin.database().ref('/message').push({
    text: data
  }).then(() => {
    console.log('New Message written');
    return { text: data };
  })
  .catch((error) => {
    throw new functions.https.HttpsError('unknown', error.message, error);
  });

});

exports.sendNotification = functions.database.ref('/message').onWrite(event=>{

var ref = admin.database().ref('/token');
return ref.once("value", function(snapshot){

    const payload = {
          notification: {
            title: 'update message.',
            body: 'Successfully'
          }
        };

        admin.messaging().sendToDevice(snapshot.val(), payload)

        },
    function (errorObject) {
        console.log("The read failed: " + errorObject.code);
});
})