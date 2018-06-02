

const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp();

exports.sendDoctorNotification =
    functions.database
    .ref('/cghversion01/notification/{notificationID}')
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


exports.getDoctorInfo =
    functions.https.onCall((doctorID) => {

      const getDoctorTablePromise = admin.database()
              .ref('/cghversion01/doctor/' + doctorID).once('value');


     return Promise.all([getDoctorTablePromise]).then(results => {
             const doctorsSnapshot = results[0];

             console.log('doctors ', doctorsSnapshot.val());
             // Send notifications to all tokens.
             return JSON.parse(doctorsSnapshot);
           });

    });




