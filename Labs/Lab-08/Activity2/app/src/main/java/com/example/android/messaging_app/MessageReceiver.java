package com.example.android.messaging_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MessageReceiver extends BroadcastReceiver {

    private static MessageListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0; i<pdus.length; i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String message = "Sender : " + smsMessage.getDisplayOriginatingAddress()
                    + "Msg From: " + smsMessage.getEmailFrom()
                    + "Msg Body: " + smsMessage.getEmailBody()
                    + "Display message body: " + smsMessage.getDisplayMessageBody()
                    + "Time in millisecond: " + smsMessage.getTimestampMillis()
                    + "Message: " + smsMessage.getMessageBody();
            mListener.messageReceived(message);
        }
    }

    public static void bindListener(MessageListener listener){
        mListener = listener;
    }
}