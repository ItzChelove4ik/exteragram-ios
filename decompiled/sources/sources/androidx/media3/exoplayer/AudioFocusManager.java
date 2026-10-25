package androidx.media3.exoplayer;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.audio.AudioFocusRequestCompat;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.Objects;

/* loaded from: classes4.dex */
final class AudioFocusManager {
    private AudioAttributes audioAttributes;
    private AudioFocusRequestCompat audioFocusRequest;
    private int audioFocusState;
    private final Supplier<AudioManager> audioManager;
    private final Handler eventHandler;
    private int focusGainToRequest;
    private PlayerControl playerControl;
    private boolean rebuildAudioFocusRequest;
    private float volumeMultiplier;

    public interface PlayerControl {
        void executePlayerCommand(int r1);

        void setVolumeMultiplier(float r1);
    }

    /* renamed from: $r8$lambda$9-ZDmzK1qMcz_wrnYIW8vbeeQek, reason: not valid java name */
    public static /* synthetic */ AudioManager m763$r8$lambda$9ZDmzK1qMcz_wrnYIW8vbeeQek(Context r0) {
        return AudioManagerCompat.getAudioManager(r0);
    }

    /* renamed from: $r8$lambda$scC-8jFe87VG-4Seaqk0Pe0Rt0U, reason: not valid java name */
    public static /* synthetic */ void m764$r8$lambda$scC8jFe87VG4Seaqk0Pe0Rt0U(AudioFocusManager r0, int r1) {
        r0.handlePlatformAudioFocusChange(r1);
    }

    public AudioFocusManager(Context r2, Looper r3, PlayerControl r4) {
        this.volumeMultiplier = 1.0f;
        this.audioManager = Suppliers.memoize(new AudioFocusManager$$ExternalSyntheticLambda1(r2));
        this.playerControl = r4;
        this.eventHandler = new Handler(r3);
        this.audioFocusState = 0;
    }

    private void abandonAudioFocusIfHeld() {
        int r0 = this.audioFocusState;
        if (r0 == 1) goto L10;
        if (r0 != 0) goto L7;
        return;
    L7:
        if (this.audioFocusRequest == null) goto L11;
        AudioManagerCompat.abandonAudioFocusRequest(this.audioManager.get(), this.audioFocusRequest);
        return;
    L11:
        return;
    }

    private static int convertAudioAttributesToFocusGain(AudioAttributes r6) {
        if (r6 != null) goto L6;
        return 0;
    L6:
        switch(r6.usage) {
            case 0: goto L18;
            case 1: goto L17;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L16;
            case 5: goto L14;
            case 6: goto L14;
            case 7: goto L14;
            case 8: goto L14;
            case 9: goto L14;
            case 10: goto L14;
            case 11: goto L12;
            case 12: goto L14;
            case 13: goto L14;
            case 14: goto L17;
            case 15: goto L7;
            case 16: goto L9;
            default: goto L7;
        };
    L7:
        Log.w("AudioFocusManager", "Unidentified audio usage: " + r6.usage);
        return 0;
    L9:
        return 4;
    L14:
        return 3;
    L15:
        return 0;
    L16:
        return 2;
    L17:
        return 1;
    L18:
        Log.w("AudioFocusManager", "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
        return 1;
    L12:
        if (r6.contentType != 1) goto L14;
        return 2;
    }

    private void executePlayerCommand(int r1) {
        PlayerControl r0 = this.playerControl;
        if (r0 == null) goto L6;
        r0.executePlayerCommand(r1);
        return;
    }

    private void handlePlatformAudioFocusChange(int r3) {
        if (r3 == (-3)) goto L14;
        if (r3 == (-2)) goto L14;
        if (r3 == (-1)) goto L12;
        if (r3 == 1) goto L10;
        Log.w("AudioFocusManager", "Unknown focus change type: " + r3);
        return;
    L10:
        setAudioFocusState(2);
        executePlayerCommand(1);
        return;
    L12:
        executePlayerCommand(-1);
        abandonAudioFocusIfHeld();
        setAudioFocusState(1);
        return;
    L14:
        if (r3 != (-2)) goto L16;
    L20:
        executePlayerCommand(0);
        setAudioFocusState(3);
        return;
    L16:
        if (willPauseWhenDucked() == true) goto L20;
        setAudioFocusState(4);
    }

    private int requestAudioFocus() {
        if (this.audioFocusState != 2) goto L6;
        return 1;
    L6:
        if (requestAudioFocusInternal() != 1) goto L9;
        setAudioFocusState(2);
        return 1;
    L9:
        setAudioFocusState(1);
        return -1;
    }

    private int requestAudioFocusInternal() {
        AudioFocusRequestCompat r0 = this.audioFocusRequest;
        if (r0 != null) goto L5;
    L6:
        if (r0 != null) goto L8;
        AudioFocusRequestCompat.Builder r02 = new AudioFocusRequestCompat.Builder(this.focusGainToRequest);
    L9:
        boolean r1 = willPauseWhenDucked();
        this.audioFocusRequest = r02.setAudioAttributes((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).setWillPauseWhenDucked(r1).setOnAudioFocusChangeListener(new AudioFocusManager$$ExternalSyntheticLambda0(this), this.eventHandler).build();
        this.rebuildAudioFocusRequest = false;
    L11:
        return AudioManagerCompat.requestAudioFocus(this.audioManager.get(), this.audioFocusRequest);
    L8:
        r02 = r0.buildUpon();
        goto L9
    L5:
        if (this.rebuildAudioFocusRequest == false) goto L11;
        goto L6
    }

    private void setAudioFocusState(int r2) {
        if (this.audioFocusState == r2) goto L17;
        this.audioFocusState = r2;
        if (r2 != 4) goto L8;
        float r22 = 0.2f;
    L10:
        if (this.volumeMultiplier == r22) goto L18;
        this.volumeMultiplier = r22;
        PlayerControl r1 = this.playerControl;
        if (r1 == null) goto L16;
        r1.setVolumeMultiplier(r22);
        return;
    L16:
        return;
    L18:
        return;
    L8:
        r22 = 1.0f;
        goto L10
    }

    private boolean shouldHandleAudioFocus(int r2) {
        if (r2 != 1) goto L5;
        return false;
    L5:
        if (this.focusGainToRequest != 1) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean willPauseWhenDucked() {
        AudioAttributes r1 = this.audioAttributes;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.contentType != 1) goto L9;
        return true;
    L9:
        return false;
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public void release() {
        this.playerControl = null;
        abandonAudioFocusIfHeld();
        setAudioFocusState(0);
    }

    public void setAudioAttributes(AudioAttributes r2) {
        if (Objects.equals(this.audioAttributes, r2) == true) goto L11;
        this.audioAttributes = r2;
        int r22 = convertAudioAttributesToFocusGain(r2);
        this.focusGainToRequest = r22;
        boolean r1 = true;
        if (r22 == 1) goto L9;
        if (r22 == 0) goto L9;
        r1 = false;
    L9:
        Assertions.checkArgument(r1, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
        return;
    }

    public int updateAudioFocus(boolean r3, int r4) {
        if (shouldHandleAudioFocus(r4) == true) goto L6;
        abandonAudioFocusIfHeld();
        setAudioFocusState(0);
        return 1;
    L6:
        if (r3 == true) goto L8;
        int r2 = this.audioFocusState;
        if (r2 != 1) goto L12;
        return -1;
    L12:
        if (r2 == 3) goto L14;
        return 1;
    L14:
        return 0;
    L8:
        return requestAudioFocus();
    }
}
