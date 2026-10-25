package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

/* loaded from: classes5.dex */
public class MusicPlayerReceiver extends BroadcastReceiver {
    public MusicPlayerReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r5, Intent r6) {
        if (r6.getAction().equals("android.intent.action.MEDIA_BUTTON") == true) goto L5;
        String r4 = r6.getAction();
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -1925342171: goto L72;
            case -1461225938: goto L68;
            case -1449542100: goto L64;
            case -1293741059: goto L60;
            case -1293675458: goto L56;
            case -549244379: goto L52;
            case 40087297: goto L48;
            case 1421978031: goto L44;
            default: goto L75;
        };
    L75:
        switch(r3) {
            case 0: goto L93;
            case 1: goto L91;
            case 2: goto L89;
            case 3: goto L87;
            case 4: goto L85;
            case 5: goto L89;
            case 6: goto L83;
            case 7: goto L78;
            default: goto L76;
        };
    L76:
        return;
    L83:
        MediaController.getInstance().playPreviousMessage();
        return;
    L85:
        MediaController.getInstance().playMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L87:
        MediaController.getInstance().playNextMessage();
        return;
    L89:
        MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L91:
        MediaController.getInstance().cleanupPlayer(true, true);
        return;
    L93:
        SharedConfig.setRepeatMode((SharedConfig.repeatMode + 1) % 3);
        return;
    L78:
        if (SharedConfig.shuffleMusic == false) goto L81;
        MediaController.getInstance().setPlaybackOrderType(0);
        return;
    L81:
        MediaController.getInstance().setPlaybackOrderType(2);
        return;
    L44:
        if (r4.equals(MusicPlayerService.NOTIFY_SHUFFLE) == false) goto L75;
        r3 = 7;
        goto L75
    L48:
        if (r4.equals(MusicPlayerService.NOTIFY_PREVIOUS) == false) goto L75;
        r3 = 6;
        goto L75
    L52:
        if (r4.equals("android.media.AUDIO_BECOMING_NOISY") == false) goto L75;
        r3 = 5;
        goto L75
    L56:
        if (r4.equals(MusicPlayerService.NOTIFY_PLAY) == false) goto L75;
        r3 = 4;
        goto L75
    L60:
        if (r4.equals(MusicPlayerService.NOTIFY_NEXT) == false) goto L75;
        r3 = 3;
        goto L75
    L64:
        if (r4.equals(MusicPlayerService.NOTIFY_PAUSE) == false) goto L75;
        r3 = 2;
        goto L75
    L68:
        if (r4.equals(MusicPlayerService.NOTIFY_CLOSE) == false) goto L75;
        r3 = 1;
        goto L75
    L72:
        if (r4.equals(MusicPlayerService.NOTIFY_REPEAT) == false) goto L75;
        r3 = 0;
        goto L75
    L5:
        if (r6.getExtras() == null) goto L95;
        KeyEvent r42 = (KeyEvent) r6.getExtras().get("android.intent.extra.KEY_EVENT");
        if (r42 != null) goto L11;
        return;
    L11:
        if (r42.getAction() != 0) goto L97;
        int r43 = r42.getKeyCode();
        if (r43 == 79) goto L35;
        if (r43 == 85) goto L35;
        if (r43 != 87) goto L20;
        MediaController.getInstance().playNextMessage();
        return;
    L20:
        if (r43 != 88) goto L22;
        MediaController.getInstance().playPreviousMessage();
        return;
    L22:
        if (r43 != 126) goto L24;
        MediaController.getInstance().playMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L24:
        if (r43 != 127) goto L98;
        MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L98:
        return;
    L35:
        if (MediaController.getInstance().isMessagePaused() == false) goto L38;
        MediaController.getInstance().playMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L38:
        MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L97:
        return;
    }
}
