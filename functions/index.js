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
return ref.once("value",function(snapshot){

    const payload = {
          notification: {
            title: 'update message.',
            body: 'Successfully'
          }
        };

        admin.messaging().sendToDevice(snapshot.val(), payload)

        },function (errorObject) {
        console.log("The read failed: " + errorObject.code);
});
})

exports.sendDoctorNotification = functions.database.ref('/cghversion01/notification/{notificationID}')
    .onWrite((change, context) => {
      const notificationID = context.params.notificationID;
      // If un-follow we exit the function.
      if (!change.after.val()) {
        return console.log('Notification ', notificationID);
      }
      console.log('Notification ', notificationID);

      // Get the list of device notification tokens.
      const getDeviceTokensPromise = admin.database()
          .ref('/cghversion01/notification/' + notificationID + '/notificationTokens').once('value');

      const getNotificationPromise = admin.database()
           .ref('/cghversion01/notification/' + notificationID + '/sentence').once('value');

      const getPatientPromise = admin.database()
            .ref('/cghversion01/notification/' + notificationID + '/patientName').once('value');

      // The snapshot to the user's tokens.
      let tokensSnapshot;

      // The array containing all the user's tokens.
      let tokens;

      return Promise.all([getDeviceTokensPromise, getNotificationPromise, getPatientPromise]).then(results => {
        tokensSnapshot = results[0];
        const notificationDetail = results[1];
        const patientDetail = results[2];
        // Check if there are any device tokens.
//        if (!tokensSnapshot.hasChildren()) {
//          return console.log('There are no notification tokens to send to.');
//        }
//        console.log('There are', tokensSnapshot.numChildren(), 'tokens to send notifications to.');
//        console.log('Fetched follower profile', follower);

        // Notification details.
        const payload = {
          notification: {
            title: patientDetail.val(),
            body: notificationDetail.val() + "."
          }
        };
        console.log('sentence ', notificationDetail.val() + ".");
        console.log('patientDetail ', patientDetail.val() + ".");

        // Listing all tokens as an array.
        tokens = tokensSnapshot.val();
        console.log('tokens ', tokens);
        // Send notifications to all tokens.
        return admin.messaging().sendToDevice(tokens, payload);
      });
    });