package org.telegram.ui.Components.voip;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaError;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes7.dex */
public class GroupCallStatusIcon {
    Callback callback;
    private Runnable checkRaiseRunnable;
    RLottieImageView iconView;
    boolean isSpeaking;
    boolean lastMuted;
    boolean lastRaisedHand;
    RLottieDrawable micDrawable;
    private boolean mutedByMe;
    TLRPC.GroupCallParticipant participant;
    private Runnable raiseHandCallback;
    private Runnable shakeHandCallback;
    RLottieDrawable shakeHandDrawable;
    private Runnable updateRunnable;
    boolean updateRunnableScheduled;

    public interface Callback {
        void onStatusChanged();
    }

    /* renamed from: $r8$lambda$2oADiVPXWIlklNQB9xh7-xsnkw8, reason: not valid java name */
    public static /* synthetic */ void m13854$r8$lambda$2oADiVPXWIlklNQB9xh7xsnkw8(GroupCallStatusIcon r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$AArKbeLZjxJmylwxnZxP4zUlua0(GroupCallStatusIcon r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$AawPLL-ib-EIcncag1BtXj9ih34, reason: not valid java name */
    public static /* synthetic */ void m13855$r8$lambda$AawPLLibEIcncag1BtXj9ih34(GroupCallStatusIcon r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$GsHsjZR7cLf7MVljKK6HuGGoNzU(GroupCallStatusIcon r0) {
        r0.lambda$new$2();
    }

    public GroupCallStatusIcon() {
        this.shakeHandCallback = new GroupCallStatusIcon$$ExternalSyntheticLambda0(this);
        this.raiseHandCallback = new GroupCallStatusIcon$$ExternalSyntheticLambda1(this);
        this.updateRunnable = new GroupCallStatusIcon$$ExternalSyntheticLambda2(this);
        this.checkRaiseRunnable = new GroupCallStatusIcon$$ExternalSyntheticLambda3(this);
        this.micDrawable = new RLottieDrawable(R.raw.voice_mini, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), true, null);
        this.shakeHandDrawable = new RLottieDrawable(R.raw.hand_2, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), true, null);
    }

    private /* synthetic */ void lambda$new$0() {
        this.shakeHandDrawable.setOnFinishCallback(null, 0);
        this.micDrawable.setOnFinishCallback(null, 0);
        RLottieImageView r0 = this.iconView;
        if (r0 == null) goto L6;
        r0.setAnimation(this.micDrawable);
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        int r0 = Utilities.random.nextInt(100);
        int r2 = 120;
        if (r0 >= 32) goto L5;
        int r3 = 0;
    L15:
        this.shakeHandDrawable.setCustomEndFrame(r2);
        this.shakeHandDrawable.setOnFinishCallback(this.shakeHandCallback, r2 - 1);
        this.shakeHandDrawable.setCurrentFrame(r3);
        RLottieImageView r02 = this.iconView;
        if (r02 == null) goto L19;
        r02.setAnimation(this.shakeHandDrawable);
        this.iconView.playAnimation();
        return;
    L19:
        return;
    L5:
        r3 = 240;
        if (r0 >= 64) goto L8;
    L7:
        int r4 = r3;
        r3 = r2;
        r2 = r4;
        goto L15
    L8:
        r2 = MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN;
        if (r0 < 97) goto L15;
        r3 = 540;
        if (r0 == 98) goto L7;
        r2 = 720;
        goto L15
    }

    private /* synthetic */ void lambda$new$2() {
        this.isSpeaking = false;
        Callback r1 = this.callback;
        if (r1 == null) goto L5;
        r1.onStatusChanged();
    L5:
        this.updateRunnableScheduled = false;
    }

    private /* synthetic */ void lambda$new$3() {
        updateIcon(true);
    }

    public boolean isMutedByAdmin() {
        TLRPC.GroupCallParticipant r1 = this.participant;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.muted == true) goto L7;
        return false;
    L7:
        if (r1.can_self_unmute == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isMutedByMe() {
        return this.mutedByMe;
    }

    public boolean isSpeaking() {
        return this.isSpeaking;
    }

    public void setAmplitude(double r3) {
        if (r3 > 1.5d) goto L5;
        return;
    L5:
        if (this.updateRunnableScheduled == false) goto L8;
        AndroidUtilities.cancelRunOnUIThread(this.updateRunnable);
    L8:
        if (this.isSpeaking == true) goto L12;
        this.isSpeaking = true;
        Callback r32 = this.callback;
        if (r32 == null) goto L12;
        r32.onStatusChanged();
    L12:
        AndroidUtilities.runOnUIThread(this.updateRunnable, 500);
        this.updateRunnableScheduled = true;
    }

    public void setCallback(Callback r3) {
        this.callback = r3;
        if (r3 != null) goto L6;
        this.isSpeaking = false;
        AndroidUtilities.cancelRunOnUIThread(this.updateRunnable);
        AndroidUtilities.cancelRunOnUIThread(this.raiseHandCallback);
        AndroidUtilities.cancelRunOnUIThread(this.checkRaiseRunnable);
        this.micDrawable.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        return;
    }

    public void setImageView(RLottieImageView r1) {
        this.iconView = r1;
        updateIcon(false);
    }

    public void setParticipant(TLRPC.GroupCallParticipant r1, boolean r2) {
        this.participant = r1;
        updateIcon(r2);
    }

    public void updateIcon(boolean r17) {
        if (this.iconView == null) goto L90;
        TLRPC.GroupCallParticipant r1 = this.participant;
        if (r1 != null) goto L7;
        return;
    L7:
        if (this.micDrawable != null) goto L10;
        return;
    L10:
        if (r1.muted_by_you == true) goto L12;
    L14:
        boolean r12 = false;
    L15:
        long r5 = SystemClock.elapsedRealtime();
        TLRPC.GroupCallParticipant r2 = this.participant;
        if ((r5 - r2.lastVoiceUpdateTime) >= 500) goto L18;
        boolean r52 = r2.hasVoiceDelayed;
    L20:
        if (r2.self == false) goto L31;
        if (VoIPService.getSharedInstance() != null) goto L24;
    L29:
        boolean r22 = false;
    L37:
        TLRPC.GroupCallParticipant r53 = this.participant;
        if (r53.muted == true) goto L40;
    L41:
        if (r12 == false) goto L49;
    L42:
        boolean r6 = r53.can_self_unmute;
        if (r6 == false) goto L45;
        if (r12 == false) goto L49;
    L45:
        if (r6 == true) goto L49;
        if (r53.raise_hand_rating == 0) goto L49;
        boolean r54 = true;
    L51:
        if (r54 == false) goto L59;
        long r122 = SystemClock.elapsedRealtime();
        long r14 = this.participant.lastRaiseHandDate;
        long r123 = r122 - r14;
        if (r14 != 0) goto L55;
    L58:
        boolean r7 = this.micDrawable.setCustomEndFrame(136);
    L69:
        if (r17 == false) goto L83;
        if (r7 == false) goto L84;
        if (r54 == false) goto L73;
        this.micDrawable.setCurrentFrame(99);
        this.micDrawable.setCustomEndFrame(136);
    L82:
        this.iconView.playAnimation();
        this.iconView.invalidate();
        goto L84
    L73:
        if (r22 == true) goto L75;
    L78:
        RLottieDrawable r3 = this.micDrawable;
        if (r22 == false) goto L81;
        r3.setCurrentFrame(69);
        this.micDrawable.setCustomEndFrame(99);
        goto L82
    L81:
        r3.setCurrentFrame(36);
        this.micDrawable.setCustomEndFrame(69);
        goto L82
    L75:
        if (this.lastRaisedHand == false) goto L78;
        if (r54 == true) goto L78;
        this.micDrawable.setCurrentFrame(0);
        this.micDrawable.setCustomEndFrame(36);
    L84:
        this.iconView.setAnimation(this.micDrawable);
        this.lastMuted = r22;
        this.lastRaisedHand = r54;
        if (this.mutedByMe == r12) goto L92;
        this.mutedByMe = r12;
        Callback r0 = this.callback;
        if (r0 == null) goto L93;
        r0.onStatusChanged();
        return;
    L93:
        return;
    L92:
        return;
    L83:
        RLottieDrawable r62 = this.micDrawable;
        r62.setCurrentFrame(r62.getCustomEndFrame() - 1, false, true);
        this.iconView.invalidate();
        goto L84
    L55:
        if (r123 > 5000) goto L58;
        AndroidUtilities.runOnUIThread(this.checkRaiseRunnable, 5000 - r123);
        goto L58
    L59:
        this.iconView.setAnimation(this.micDrawable);
        this.micDrawable.setOnFinishCallback(null, 0);
        if (r22 == true) goto L62;
    L64:
        RLottieDrawable r72 = this.micDrawable;
        if (r22 == false) goto L67;
        int r8 = 99;
    L68:
        r7 = r72.setCustomEndFrame(r8);
        goto L69
    L67:
        r8 = 69;
        goto L68
    L62:
        if (this.lastRaisedHand == false) goto L64;
        r7 = this.micDrawable.setCustomEndFrame(36);
    L49:
        r54 = false;
        goto L51
    L40:
        if (this.isSpeaking == false) goto L42;
    L24:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L29;
        if (this.isSpeaking == false) goto L28;
        if (r52 == true) goto L29;
    L28:
        r22 = true;
        goto L37
    L31:
        if (r2.muted == true) goto L33;
    L35:
        if (r12 == false) goto L29;
    L33:
        if (this.isSpeaking == false) goto L28;
        if (r52 == false) goto L28;
    L18:
        r52 = r2.hasVoice;
        goto L20
    L12:
        if (r1.self == true) goto L14;
        r12 = true;
        goto L15
    }
}
