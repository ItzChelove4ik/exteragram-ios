package androidx.media3.common.audio;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Looper;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes3.dex */
public abstract class AudioManagerCompat {
    private static Context applicationContext;
    private static AudioManager audioManager;

    public static /* synthetic */ void $r8$lambda$1DKwaorNyy5mIYtOpjykiqi6f7A(Context r1, ConditionVariable r2) {
        audioManager = (AudioManager) r1.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        r2.open();
    }

    public static int abandonAudioFocusRequest(AudioManager r2, AudioFocusRequestCompat r3) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return r2.abandonAudioFocusRequest(r3.getAudioFocusRequest());
    L7:
        return r2.abandonAudioFocus(r3.getOnAudioFocusChangeListener());
    }

    public static synchronized AudioManager getAudioManager(Context r4) {
        monitor-enter(AudioManagerCompat.class);
        Context r42 = r4.getApplicationContext();     // Catch: Throwable -> L7
        if (applicationContext == r42) goto L9;
        audioManager = null;     // Catch: Throwable -> L7
    L9:
        AudioManager r1 = audioManager;     // Catch: Throwable -> L7
        if (r1 == null) goto L13;
        monitor-exit(AudioManagerCompat.class);
        return r1;
    L13:
        Looper r12 = Looper.myLooper();     // Catch: Throwable -> L7
        if (r12 != null) goto L16;
    L21:
        AudioManager r43 = (AudioManager) r42.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);     // Catch: Throwable -> L7
        audioManager = r43;     // Catch: Throwable -> L7
        AudioManager r44 = (AudioManager) Assertions.checkNotNull(r43);     // Catch: Throwable -> L7
        monitor-exit(AudioManagerCompat.class);
        return r44;
    L16:
        if (r12 == Looper.getMainLooper()) goto L21;
        ConditionVariable r13 = new ConditionVariable();     // Catch: Throwable -> L7
        BackgroundExecutor.get().execute(new AudioManagerCompat$$ExternalSyntheticLambda0(r42, r13));     // Catch: Throwable -> L7
        r13.blockUninterruptible();     // Catch: Throwable -> L7
        AudioManager r45 = (AudioManager) Assertions.checkNotNull(audioManager);     // Catch: Throwable -> L7
        monitor-exit(AudioManagerCompat.class);
        return r45;
    L7:
        th = move-exception;
        throw th;
    }

    public static int getStreamMaxVolume(AudioManager r0, int r1) {
        return r0.getStreamMaxVolume(r1);
    }

    public static int getStreamMinVolume(AudioManager r2, int r3) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return 0;
    L5:
        return r2.getStreamMinVolume(r3);
    }

    public static int getStreamVolume(AudioManager r3, int r4) {
        return r3.getStreamVolume(r4);
    L4:
        e = move-exception;
        Log.w("AudioManagerCompat", "Could not retrieve stream volume for stream type " + r4, e);
        return r3.getStreamMaxVolume(r4);
    }

    public static boolean isStreamMute(AudioManager r0, int r1) {
        return r0.isStreamMute(r1);
    }

    public static int requestAudioFocus(AudioManager r2, AudioFocusRequestCompat r3) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return r2.requestAudioFocus(r3.getAudioFocusRequest());
    L7:
        return r2.requestAudioFocus(r3.getOnAudioFocusChangeListener(), r3.getAudioAttributes().getStreamType(), r3.getFocusGain());
    }
}
