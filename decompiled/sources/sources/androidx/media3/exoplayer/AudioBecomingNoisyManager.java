package androidx.media3.exoplayer;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;

/* loaded from: classes4.dex */
final class AudioBecomingNoisyManager {
    private final HandlerWrapper backgroundHandler;
    private final Context context;
    private boolean isEnabled;
    private final AudioBecomingNoisyReceiver receiver;

    public final class AudioBecomingNoisyReceiver extends BroadcastReceiver {
        private final HandlerWrapper eventHandler;
        private final EventListener listener;
        final /* synthetic */ AudioBecomingNoisyManager this$0;

        public static /* synthetic */ void $r8$lambda$IXMzoJXJzSz0_Ay3g9QeVVTpPvo(AudioBecomingNoisyReceiver r0) {
            r0.callListenerIfEnabled();
        }

        public AudioBecomingNoisyReceiver(AudioBecomingNoisyManager r1, HandlerWrapper r2, EventListener r3) {
            this.this$0 = r1;
            this.eventHandler = r2;
            this.listener = r3;
        }

        private void callListenerIfEnabled() {
            if (AudioBecomingNoisyManager.access$000(this.this$0) == false) goto L6;
            this.listener.onAudioBecomingNoisy();
            return;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            if ("android.media.AUDIO_BECOMING_NOISY".equals(r2.getAction()) == false) goto L6;
            this.eventHandler.post(new AudioBecomingNoisyManager$AudioBecomingNoisyReceiver$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    public interface EventListener {
        void onAudioBecomingNoisy();
    }

    public static /* synthetic */ void $r8$lambda$H_Z5Q7Cu3Yhl8bV9DiiByFBLNRw(AudioBecomingNoisyManager r3) {
        r3.context.registerReceiver(r3.receiver, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
    }

    public static /* synthetic */ void $r8$lambda$IA2MCkywAUSA9ZC86RffruH_OCw(AudioBecomingNoisyManager r1) {
        r1.context.unregisterReceiver(r1.receiver);
    }

    public AudioBecomingNoisyManager(Context r1, Looper r2, Looper r3, EventListener r4, Clock r5) {
        this.context = r1.getApplicationContext();
        this.backgroundHandler = r5.createHandler(r2, null);
        this.receiver = new AudioBecomingNoisyReceiver(this, r5.createHandler(r3, null), r4);
    }

    public static /* synthetic */ boolean access$000(AudioBecomingNoisyManager r0) {
        return r0.isEnabled;
    }

    @SuppressLint({"UnprotectedReceiver"})
    public void setEnabled(boolean r2) {
        if (r2 != this.isEnabled) goto L5;
        return;
    L5:
        HandlerWrapper r0 = this.backgroundHandler;
        if (r2 == false) goto L9;
        r0.post(new AudioBecomingNoisyManager$$ExternalSyntheticLambda0(this));
        this.isEnabled = true;
        return;
    L9:
        r0.post(new AudioBecomingNoisyManager$$ExternalSyntheticLambda1(this));
        this.isEnabled = false;
    }
}
