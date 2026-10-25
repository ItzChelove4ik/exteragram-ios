package org.telegram.messenger.voip;

import android.media.AudioManager;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Utilities;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class VoipAudioManager {
    private Boolean isSpeakerphoneOn;

    public static final class InstanceHolder {
        static final VoipAudioManager instance = null;

        static {
            instance = new VoipAudioManager(null);
        }

        private InstanceHolder() {
        }
    }

    /* renamed from: $r8$lambda$Uy8WgwUuc7Z63HOCKQXEBuQqQ-s, reason: not valid java name */
    public static /* synthetic */ void m5376$r8$lambda$Uy8WgwUuc7Z63HOCKQXEBuQqQs(Utilities.Callback2 r0, boolean r1, boolean r2) {
        r0.run(Boolean.valueOf(r1), Boolean.valueOf(r2));
    }

    public static /* synthetic */ void $r8$lambda$tZMn7poB5jgD9sRBvFrbflDozas(VoipAudioManager r0, Utilities.Callback2 r1) {
        r0.lambda$isBluetoothAndSpeakerOnAsync$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$yM9XGBMVT5UBlhELR0nIEFDWykI(AudioManager r0, boolean r1) {
        r0.setSpeakerphoneOn(r1);
    }

    private VoipAudioManager() {
    }

    public /* synthetic */ VoipAudioManager(VoipAudioManagerIA r1) {
        this();
    }

    public static VoipAudioManager get() {
        return InstanceHolder.instance;
    }

    private AudioManager getAudioManager() {
        return (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
    }

    private /* synthetic */ void lambda$isBluetoothAndSpeakerOnAsync$2(Utilities.Callback2 r3) {
        AudioManager r2 = getAudioManager();
        AndroidUtilities.runOnUIThread(new VoipAudioManager$$ExternalSyntheticLambda2(r3, r2.isBluetoothScoOn(), r2.isSpeakerphoneOn()));
    }

    public void isBluetoothAndSpeakerOnAsync(Utilities.Callback2<Boolean, Boolean> r3) {
        Utilities.globalQueue.postRunnable(new VoipAudioManager$$ExternalSyntheticLambda0(this, r3));
    }

    public boolean isSpeakerphoneOn() {
        Boolean r0 = this.isSpeakerphoneOn;
        if (r0 != null) goto L7;
        return getAudioManager().isSpeakerphoneOn();
    L7:
        return r0.booleanValue();
    }

    public void setSpeakerphoneOn(boolean r3) {
        this.isSpeakerphoneOn = Boolean.valueOf(r3);
        AudioManager r2 = getAudioManager();
        Utilities.globalQueue.postRunnable(new VoipAudioManager$$ExternalSyntheticLambda1(r2, r3));
    }
}
