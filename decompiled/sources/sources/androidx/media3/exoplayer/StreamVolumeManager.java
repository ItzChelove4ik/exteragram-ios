package androidx.media3.exoplayer;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Looper;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes4.dex */
final class StreamVolumeManager {
    private final Context applicationContext;
    private AudioManager audioManager;
    private final Listener listener;
    private VolumeChangeReceiver receiver;
    private final BackgroundThreadStateHandler<StreamVolumeState> stateHandler;
    private int volumeBeforeMute;

    /* renamed from: androidx.media3.exoplayer.StreamVolumeManager$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public interface Listener {
        void onStreamTypeChanged(int r1);

        void onStreamVolumeChanged(int r1, boolean r2);
    }

    public static final class StreamVolumeState {
        public final int maxVolume;
        public final int minVolume;
        public final boolean muted;
        public final int streamType;
        public final int volume;

        public StreamVolumeState(int r1, int r2, boolean r3, int r4, int r5) {
            this.streamType = r1;
            this.volume = r2;
            this.muted = r3;
            this.minVolume = r4;
            this.maxVolume = r5;
        }
    }

    public final class VolumeChangeReceiver extends BroadcastReceiver {
        final /* synthetic */ StreamVolumeManager this$0;

        public static /* synthetic */ void $r8$lambda$7abK6rV3Svyh_vRtWD88OaVAcJc(VolumeChangeReceiver r2) {
            if (StreamVolumeManager.access$100(r2.this$0) != null) goto L5;
            return;
        L5:
            int r0 = ((StreamVolumeState) StreamVolumeManager.access$000(r2.this$0).get()).streamType;
            StreamVolumeManager.access$000(r2.this$0).setStateInBackground(StreamVolumeManager.access$200(r2.this$0, r0));
        }

        private VolumeChangeReceiver(StreamVolumeManager r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ VolumeChangeReceiver(StreamVolumeManager r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            StreamVolumeManager.access$000(this.this$0).runInBackground(new StreamVolumeManager$VolumeChangeReceiver$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: $r8$lambda$6Um-2v01E--KjqzVTbgnE6U3MUk, reason: not valid java name */
    public static /* synthetic */ StreamVolumeState m787$r8$lambda$6Um2v01EKjqzVTbgnE6U3MUk(StreamVolumeManager r6, boolean r7, StreamVolumeState r8) {
        r6.getClass();
        int r1 = r8.streamType;
        if (r8.muted != r7) goto L6;
        int r62 = r8.volume;
    L5:
        int r2 = r62;
        return new StreamVolumeState(r1, r2, r7, r8.minVolume, r8.maxVolume);
    L6:
        if (r7 == false) goto L8;
        r62 = 0;
        goto L5
    L8:
        r62 = r6.volumeBeforeMute;
        goto L5
    }

    public static /* synthetic */ StreamVolumeState $r8$lambda$7z907T46SgICSLAj14y8V6N3eUc(int r6, StreamVolumeState r7) {
        return new StreamVolumeState(r6, r7.volume, r7.muted, r7.minVolume, r7.maxVolume);
    }

    /* renamed from: $r8$lambda$KIhM5OZt0889h-D-AjLv_I3prWc, reason: not valid java name */
    public static /* synthetic */ StreamVolumeState m788$r8$lambda$KIhM5OZt0889hDAjLv_I3prWc(StreamVolumeState r0) {
        return r0;
    }

    public static /* synthetic */ StreamVolumeState $r8$lambda$Lv87AXn_wtGrm7Lrft9dElZZaJc(int r6, StreamVolumeState r7) {
        int r1 = r7.streamType;
        int r4 = r7.minVolume;
        if (r6 >= r4) goto L5;
    L7:
        int r2 = r7.volume;
    L8:
        if (r6 != 0) goto L11;
        boolean r62 = true;
    L10:
        boolean r3 = r62;
        return new StreamVolumeState(r1, r2, r3, r4, r7.maxVolume);
    L11:
        r62 = false;
        goto L10
    L5:
        if (r6 > r7.maxVolume) goto L7;
        r2 = r6;
        goto L8
    }

    public static /* synthetic */ StreamVolumeState $r8$lambda$QjQg0w_TCjWgUbFZwOrpUNSkgyw(StreamVolumeState r6) {
        int r1 = r6.streamType;
        int r2 = r6.volume;
        int r4 = r6.minVolume;
        if (r2 <= r4) goto L5;
        int r3 = r2 - 1;
    L6:
        boolean r5 = true;
        if (r2 <= 1) goto L11;
        r5 = false;
    L11:
        return new StreamVolumeState(r1, r3, r5, r4, r6.maxVolume);
    L5:
        r3 = r4;
        goto L6
    }

    public static /* synthetic */ StreamVolumeState $r8$lambda$UIdlGJMbnopnc782QA0pHwsqTYs(StreamVolumeManager r3, int r4, StreamVolumeState r5) {
        r3.getClass();
        if (r5.volume > r5.minVolume) goto L5;
        return r5;
    L5:
        ((AudioManager) Assertions.checkNotNull(r3.audioManager)).adjustStreamVolume(r5.streamType, -1, r4);
        return r3.generateState(r5.streamType);
    }

    public static /* synthetic */ StreamVolumeState $r8$lambda$UXoHoi1PCghPL9lLucdwmHVjHOc(StreamVolumeManager r2, int r3, int r4, StreamVolumeState r5) {
        r2.getClass();
        if (r3 != r5.volume) goto L5;
    L11:
        return r5;
    L5:
        if (r3 < r5.minVolume) goto L11;
        if (r3 > r5.maxVolume) goto L11;
        ((AudioManager) Assertions.checkNotNull(r2.audioManager)).setStreamVolume(r5.streamType, r3, r4);
        return r2.generateState(r5.streamType);
    }

    /* renamed from: $r8$lambda$YKSPczww4-NjrUdpaoM4uNbFJTk, reason: not valid java name */
    public static /* synthetic */ StreamVolumeState m789$r8$lambda$YKSPczww4NjrUdpaoM4uNbFJTk(StreamVolumeManager r1, int r2, StreamVolumeState r3) {
        r1.getClass();
        if (r3.streamType != r2) goto L6;
        return r3;
    L6:
        return r1.generateState(r2);
    }

    public static /* synthetic */ StreamVolumeState $r8$lambda$aESO6Yj8p_XERWh6HFu_2Yf2BK4(StreamVolumeManager r3, StreamVolumeState r4) {
        VolumeChangeReceiver r0 = r3.receiver;
        if (r0 != null) goto L10;
    L9:
        return r4;
    L10:
        r3.applicationContext.unregisterReceiver(r0);     // Catch: RuntimeException -> L6
    L8:
        r3.receiver = null;
    L6:
        e = move-exception;
        Log.w("StreamVolumeManager", "Error unregistering stream volume receiver", e);
        goto L8
    }

    public static /* synthetic */ StreamVolumeState $r8$lambda$aLJubDeDzUOg9jrESe0uzfsCBK8(StreamVolumeManager r2, boolean r3, int r4, StreamVolumeState r5) {
        r2.getClass();
        if (r5.muted != r3) goto L5;
        return r5;
    L5:
        Assertions.checkNotNull(r2.audioManager);
        AudioManager r0 = r2.audioManager;
        int r1 = r5.streamType;
        if (r3 == false) goto L8;
        int r32 = -100;
    L9:
        r0.adjustStreamVolume(r1, r32, r4);
        return r2.generateState(r5.streamType);
    L8:
        r32 = 100;
        goto L9
    }

    /* renamed from: $r8$lambda$d1hFsoa-d9xxXi4UZMRqP5SkAfE, reason: not valid java name */
    public static /* synthetic */ StreamVolumeState m790$r8$lambda$d1hFsoad9xxXi4UZMRqP5SkAfE(StreamVolumeManager r3, int r4, StreamVolumeState r5) {
        r3.getClass();
        if (r5.volume < r5.maxVolume) goto L5;
        return r5;
    L5:
        ((AudioManager) Assertions.checkNotNull(r3.audioManager)).adjustStreamVolume(r5.streamType, 1, r4);
        return r3.generateState(r5.streamType);
    }

    /* renamed from: $r8$lambda$j82104AOu2fyNbKA-DWnRq1sW38, reason: not valid java name */
    public static /* synthetic */ void m791$r8$lambda$j82104AOu2fyNbKADWnRq1sW38(StreamVolumeManager r0, StreamVolumeState r1, StreamVolumeState r2) {
        r0.onStreamVolumeStateChanged(r1, r2);
    }

    /* renamed from: $r8$lambda$oA_l2caYj03yjvz2K6RQ-aNlEG4, reason: not valid java name */
    public static /* synthetic */ void m792$r8$lambda$oA_l2caYj03yjvz2K6RQaNlEG4(StreamVolumeManager r3, int r4) {
        r3.audioManager = (AudioManager) Assertions.checkStateNotNull((AudioManager) r3.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND));
        VolumeChangeReceiver r0 = new VolumeChangeReceiver(r3, null);
        IntentFilter r1 = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
        r3.applicationContext.registerReceiver(r0, r1);     // Catch: RuntimeException -> L5
        r3.receiver = r0;     // Catch: RuntimeException -> L5
    L7:
        r3.stateHandler.setStateInBackground(r3.generateState(r4));
        return;
    L5:
        e = move-exception;
        Log.w("StreamVolumeManager", "Error registering stream volume receiver", e);
        goto L7
    }

    /* renamed from: $r8$lambda$v7rh79Dpv5MW-8EGV3mJbRJHKfc, reason: not valid java name */
    public static /* synthetic */ StreamVolumeState m793$r8$lambda$v7rh79Dpv5MW8EGV3mJbRJHKfc(StreamVolumeState r6) {
        int r1 = r6.streamType;
        int r2 = r6.volume;
        int r5 = r6.maxVolume;
        if (r2 >= r5) goto L5;
        int r22 = r2 + 1;
    L7:
        return new StreamVolumeState(r1, r22, false, r6.minVolume, r5);
    L5:
        r22 = r5;
        goto L7
    }

    public StreamVolumeManager(Context r7, Listener r8, int r9, Looper r10, Looper r11, Clock r12) {
        this.applicationContext = r7.getApplicationContext();
        this.listener = r8;
        BackgroundThreadStateHandler<StreamVolumeState> r82 = new BackgroundThreadStateHandler(new StreamVolumeState(r9, 0, false, 0, 0), r10, r11, r12, new StreamVolumeManager$$ExternalSyntheticLambda0(this));
        this.stateHandler = r82;
        r82.runInBackground(new StreamVolumeManager$$ExternalSyntheticLambda1(this, r9));
    }

    public static /* synthetic */ BackgroundThreadStateHandler access$000(StreamVolumeManager r0) {
        return r0.stateHandler;
    }

    public static /* synthetic */ VolumeChangeReceiver access$100(StreamVolumeManager r0) {
        return r0.receiver;
    }

    public static /* synthetic */ StreamVolumeState access$200(StreamVolumeManager r0, int r1) {
        return r0.generateState(r1);
    }

    private StreamVolumeState generateState(int r8) {
        Assertions.checkNotNull(this.audioManager);
        return new StreamVolumeState(r8, AudioManagerCompat.getStreamVolume(this.audioManager, r8), AudioManagerCompat.isStreamMute(this.audioManager, r8), AudioManagerCompat.getStreamMinVolume(this.audioManager, r8), AudioManagerCompat.getStreamMaxVolume(this.audioManager, r8));
    }

    private void onStreamVolumeStateChanged(StreamVolumeState r4, StreamVolumeState r5) {
        boolean r0 = r4.muted;
        if (r0 == false) goto L5;
    L7:
        int r1 = r4.volume;
        int r2 = r5.volume;
        if (r1 == r2) goto L10;
    L11:
        this.listener.onStreamVolumeChanged(r2, r5.muted);
    L12:
        int r02 = r4.streamType;
        int r12 = r5.streamType;
        if (r02 == r12) goto L15;
    L20:
        this.listener.onStreamTypeChanged(r12);
        return;
    L15:
        if (r4.minVolume != r5.minVolume) goto L20;
        if (r4.maxVolume != r5.maxVolume) goto L20;
        return;
    L10:
        if (r0 == r5.muted) goto L12;
    L5:
        if (r5.muted == false) goto L7;
        this.volumeBeforeMute = r4.volume;
        goto L7
    }

    @SuppressLint({"WrongConstant"})
    public void decreaseVolume(int r4) {
        this.stateHandler.updateStateAsync(new StreamVolumeManager$$ExternalSyntheticLambda6(), new StreamVolumeManager$$ExternalSyntheticLambda7(this, r4));
    }

    public int getMaxVolume() {
        return this.stateHandler.get().maxVolume;
    }

    public int getMinVolume() {
        return this.stateHandler.get().minVolume;
    }

    public int getVolume() {
        return this.stateHandler.get().volume;
    }

    @SuppressLint({"WrongConstant"})
    public void increaseVolume(int r4) {
        this.stateHandler.updateStateAsync(new StreamVolumeManager$$ExternalSyntheticLambda12(), new StreamVolumeManager$$ExternalSyntheticLambda13(this, r4));
    }

    public boolean isMuted() {
        return this.stateHandler.get().muted;
    }

    public void release() {
        this.stateHandler.updateStateAsync(new StreamVolumeManager$$ExternalSyntheticLambda4(), new StreamVolumeManager$$ExternalSyntheticLambda5(this));
    }

    @SuppressLint({"WrongConstant"})
    public void setMuted(boolean r4, int r5) {
        this.stateHandler.updateStateAsync(new StreamVolumeManager$$ExternalSyntheticLambda10(this, r4), new StreamVolumeManager$$ExternalSyntheticLambda11(this, r4, r5));
    }

    public void setStreamType(int r4) {
        this.stateHandler.updateStateAsync(new StreamVolumeManager$$ExternalSyntheticLambda2(r4), new StreamVolumeManager$$ExternalSyntheticLambda3(this, r4));
    }

    @SuppressLint({"WrongConstant"})
    public void setVolume(int r4, int r5) {
        this.stateHandler.updateStateAsync(new StreamVolumeManager$$ExternalSyntheticLambda8(r4), new StreamVolumeManager$$ExternalSyntheticLambda9(this, r4, r5));
    }
}
