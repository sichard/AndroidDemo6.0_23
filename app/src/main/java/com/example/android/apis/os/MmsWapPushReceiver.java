//package com.example.android.apis.os;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.provider.Telephony;
//import android.util.Log;
//
//import com.google.android.mms.ContentType;
//import com.google.android.mms.pdu.GenericPdu;
//import com.google.android.mms.pdu.NotificationInd;
//import com.google.android.mms.pdu.PduHeaders;
//import com.google.android.mms.pdu.PduParser;
//
///**
// * Receiver for MMS WAP push
// */
//public class MmsWapPushReceiver extends BroadcastReceiver {
//    private static final String TAG = "MmsMessagingDemo";
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (Telephony.Sms.Intents.WAP_PUSH_RECEIVED_ACTION.equals(intent.getAction())
//                && ContentType.MMS_MESSAGE.equals(intent.getType())) {
//            final byte[] data = intent.getByteArrayExtra("data");
//            final PduParser parser = new PduParser(
//                    data, PduParserUtil.shouldParseContentDisposition());
//            GenericPdu pdu = null;
//            try {
//                pdu = parser.parse();
//            } catch (final RuntimeException e) {
//                Log.e(TAG, "Invalid MMS WAP push", e);
//            }
//            if (pdu == null) {
//                Log.e(TAG, "Invalid WAP push data");
//                return;
//            }
//            switch (pdu.getMessageType()) {
//                case PduHeaders.MESSAGE_TYPE_NOTIFICATION_IND: {
//                    final NotificationInd nInd = (NotificationInd) pdu;
//                    final String location = new String(nInd.getContentLocation());
//                    Log.v(TAG, "Received MMS notification: " + location);
//                    final Intent di = new Intent();
//                    di.setClass(context, MmsMessagingDemo.class);
//                    di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    di.putExtra(MmsMessagingDemo.EXTRA_NOTIFICATION_URL, location);
//                    context.startActivity(di);
//                    break;
//                }
//                // FLAG (ywen): impl. handling of the following push
//                case PduHeaders.MESSAGE_TYPE_DELIVERY_IND: {
//                    Log.v(TAG, "Received delivery report");
//                    break;
//                }
//                case PduHeaders.MESSAGE_TYPE_READ_ORIG_IND: {
//                    Log.v(TAG, "Received read report");
//                    break;
//                }
//            }
//        }
//    }
//}
