package me.chrisvle.rechordly;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by Goshujin on 11/30/15.
 */
public class WatchListener extends WearableListenerService {
    private static final String START_EDIT = "/start_edit";
    private static final String START_MAIN = "/start_main";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if ( messageEvent.getPath().equalsIgnoreCase( START_EDIT )) {
            Intent intent = new Intent(getBaseContext(), cropChooserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( START_MAIN )) {
            Intent intent = new Intent(getBaseContext(), HomeVocalsMusicActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            super.onMessageReceived(messageEvent);
        }
    }

}
