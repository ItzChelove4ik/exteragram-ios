package org.webrtc.audio;

import android.media.AudioManager;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.Timer;
import java.util.TimerTask;
import org.webrtc.Logging;

/* loaded from: classes6.dex */
class VolumeLogger {
    private static final String TAG = "VolumeLogger";
    private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
    private static final int TIMER_PERIOD_IN_SECONDS = 30;
    private final AudioManager audioManager;
    private Timer timer;

    public class LogVolumeTask extends TimerTask {
        private final int maxRingVolume;
        private final int maxVoiceCallVolume;
        final /* synthetic */ VolumeLogger this$0;

        public LogVolumeTask(VolumeLogger r1, int r2, int r3) {
            this.this$0 = r1;
            this.maxRingVolume = r2;
            this.maxVoiceCallVolume = r3;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            int r0 = VolumeLogger.m22411$$Nest$fgetaudioManager(this.this$0).getMode();
            if (r0 != 1) goto L7;
            Logging.d(VolumeLogger.TAG, "STREAM_RING stream volume: " + VolumeLogger.m22411$$Nest$fgetaudioManager(this.this$0).getStreamVolume(2) + " (max=" + this.maxRingVolume + ")");
            return;
        L7:
            if (r0 != 3) goto L10;
            Logging.d(VolumeLogger.TAG, "VOICE_CALL stream volume: " + VolumeLogger.m22411$$Nest$fgetaudioManager(this.this$0).getStreamVolume(0) + " (max=" + this.maxVoiceCallVolume + ")");
            return;
        }
    }

    /* renamed from: -$$Nest$fgetaudioManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioManager m22411$$Nest$fgetaudioManager(VolumeLogger r0) {
        return r0.audioManager;
    }

    public VolumeLogger(AudioManager r1) {
        this.audioManager = r1;
    }

    public void start() {
        Logging.d(TAG, "start" + WebRtcAudioUtils.getThreadInfo());
        if (this.timer == null) goto L5;
        return;
    L5:
        Logging.d(TAG, "audio mode is: " + WebRtcAudioUtils.modeToString(this.audioManager.getMode()));
        Timer r2 = new Timer(THREAD_NAME);
        this.timer = r2;
        r2.schedule(new LogVolumeTask(this, this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
    }

    public void stop() {
        Logging.d(TAG, "stop" + WebRtcAudioUtils.getThreadInfo());
        Timer r0 = this.timer;
        if (r0 == null) goto L6;
        r0.cancel();
        this.timer = null;
        return;
    }
}
