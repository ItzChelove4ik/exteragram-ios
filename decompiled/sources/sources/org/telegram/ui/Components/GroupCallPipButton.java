package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaError;
import java.util.Random;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class GroupCallPipButton extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, VoIPService.StateListener {
    float amplitude;
    float animateAmplitudeDiff;
    float animateToAmplitude;
    private RLottieDrawable bigMicDrawable;
    BlobDrawable blobDrawable;
    BlobDrawable blobDrawable2;
    private final int currentAccount;
    WeavingState currentState;
    long lastStubUpdateAmplitude;
    Matrix matrix;
    private RLottieImageView muteButton;
    OvershootInterpolator overshootInterpolator;
    Paint paint;
    float pinnedProgress;
    boolean prepareToRemove;
    private final LinearGradient prepareToRemoveShader;
    float pressedProgress;
    boolean pressedState;
    WeavingState previousState;
    float progressToPrepareRemove;
    float progressToState;
    Random random;
    float removeAngle;
    public boolean removed;
    WeavingState[] states;
    private boolean stub;
    float wavesEnter;

    public static class WeavingState {
        int color1;
        int color2;
        int color3;
        private final int currentState;
        private float duration;
        private final Matrix matrix;
        public Shader shader;
        private float startX;
        private float startY;
        private float targetX;
        private float targetY;
        private float time;

        /* renamed from: -$$Nest$fgetcurrentState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10853$$Nest$fgetcurrentState(WeavingState r0) {
            return r0.currentState;
        }

        public WeavingState(int r2) {
            this.targetX = -1.0f;
            this.targetY = -1.0f;
            this.matrix = new Matrix();
            this.currentState = r2;
        }

        private void updateTargets() {
            int r0 = this.currentState;
            if (r0 != 0) goto L7;
            this.targetX = ((Utilities.random.nextInt(100) * 0.1f) / 100.0f) + 0.2f;
            this.targetY = ((Utilities.random.nextInt(100) * 0.1f) / 100.0f) + 0.7f;
            return;
        L7:
            if (r0 != 3) goto L10;
            this.targetX = ((Utilities.random.nextInt(100) * 0.1f) / 100.0f) + 0.6f;
            this.targetY = (Utilities.random.nextInt(100) * 0.1f) / 100.0f;
            return;
        L10:
            this.targetX = ((Utilities.random.nextInt(100) / 100.0f) * 0.2f) + 0.8f;
            this.targetY = Utilities.random.nextInt(100) / 100.0f;
        }

        public void setToPaint(Paint r3) {
            if (this.currentState != 2) goto L6;
            r3.setShader(null);
            r3.setColor(Theme.getColor(Theme.key_voipgroup_topPanelGray));
            return;
        L6:
            r3.setShader(this.shader);
        }

        public void update(long r12, float r14) {
            int r0 = this.currentState;
            if (r0 != 0) goto L10;
            int r02 = this.color1;
            int r2 = Theme.key_voipgroup_overlayGreen1;
            if (r02 == Theme.getColor(r2)) goto L7;
        L8:
            int r03 = Theme.getColor(r2);
            this.color1 = r03;
            int r22 = Theme.getColor(Theme.key_voipgroup_overlayGreen2);
            this.color2 = r22;
            this.shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{r03, r22}, null, Shader.TileMode.CLAMP);
        L24:
            int r04 = AndroidUtilities.dp(130.0f);
            float r23 = this.duration;
            if (r23 != 0.0f) goto L27;
        L28:
            this.duration = Utilities.random.nextInt(700) + MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
            this.time = 0.0f;
            if (this.targetX != (-1.0f)) goto L31;
            updateTargets();
        L31:
            this.startX = this.targetX;
            this.startY = this.targetY;
            updateTargets();
        L32:
            float r122 = r12;
            float r4 = 2.0f;
            float r24 = this.time + (((BlobDrawable.GRADIENT_SPEED_MIN + 0.5f) * r122) + ((r122 * (BlobDrawable.GRADIENT_SPEED_MAX * 2.0f)) * r14));
            this.time = r24;
            float r123 = this.duration;
            if (r24 <= r123) goto L35;
            this.time = r123;
        L35:
            float r124 = CubicBezierInterpolator.EASE_OUT.getInterpolation(this.time / r123);
            float r13 = r04;
            float r142 = this.startX;
            float r143 = ((r142 + ((this.targetX - r142) * r124)) * r13) - 200.0f;
            float r25 = this.startY;
            float r26 = ((r25 + ((this.targetY - r25) * r124)) * r13) - 200.0f;
            if (this.currentState == 3) goto L39;
            r4 = 1.5f;
        L39:
            float r132 = (r13 / 400.0f) * r4;
            this.matrix.reset();
            this.matrix.postTranslate(r143, r26);
            this.matrix.postScale(r132, r132, r143 + 200.0f, r26 + 200.0f);
            this.shader.setLocalMatrix(this.matrix);
            return;
        L27:
            if (this.time < r23) goto L32;
        L7:
            if (this.color2 == Theme.getColor(Theme.key_voipgroup_overlayGreen2)) goto L24;
        L10:
            if (r0 != 1) goto L16;
            int r05 = this.color1;
            int r27 = Theme.key_voipgroup_overlayBlue1;
            if (r05 == Theme.getColor(r27)) goto L14;
        L15:
            int r06 = Theme.getColor(r27);
            this.color1 = r06;
            int r28 = Theme.getColor(Theme.key_voipgroup_overlayBlue2);
            this.color2 = r28;
            this.shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{r06, r28}, null, Shader.TileMode.CLAMP);
            goto L24
        L14:
            if (this.color2 == Theme.getColor(Theme.key_voipgroup_overlayBlue2)) goto L24;
        L16:
            if (r0 != 3) goto L41;
            int r07 = this.color1;
            int r29 = Theme.key_voipgroup_mutedByAdminGradient;
            if (r07 == Theme.getColor(r29)) goto L20;
        L23:
            int r08 = Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient2);
            this.color2 = r08;
            int r3 = Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3);
            this.color3 = r3;
            int r210 = Theme.getColor(r29);
            this.color1 = r210;
            this.shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{r08, r3, r210}, null, Shader.TileMode.CLAMP);
            goto L24
        L20:
            if (this.color2 != Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient2)) goto L23;
            if (this.color3 == Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3)) goto L24;
        }
    }

    public GroupCallPipButton(Context r9, int r10, boolean r11) {
        super(r9);
        this.paint = new Paint(1);
        this.blobDrawable = new BlobDrawable(8);
        this.blobDrawable2 = new BlobDrawable(9);
        this.progressToState = 1.0f;
        this.matrix = new Matrix();
        this.wavesEnter = 0.0f;
        this.random = new Random();
        this.states = new WeavingState[4];
        this.overshootInterpolator = new OvershootInterpolator();
        this.stub = r11;
        this.currentAccount = r10;
        int r1 = 0;
    L3:
        if (r1 >= 4) goto L5;
        this.states[r1] = new WeavingState(r1);
        r1 = r1 + 1;
        goto L3
    L5:
        this.blobDrawable.maxRadius = AndroidUtilities.dp(37.0f);
        this.blobDrawable.minRadius = AndroidUtilities.dp(32.0f);
        this.blobDrawable2.maxRadius = AndroidUtilities.dp(37.0f);
        this.blobDrawable2.minRadius = AndroidUtilities.dp(32.0f);
        this.blobDrawable.generateBlob();
        this.blobDrawable2.generateBlob();
        this.bigMicDrawable = new RLottieDrawable(R.raw.voice_outlined, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(30.0f), true, null);
        setWillNotDraw(false);
        RLottieImageView r0 = new RLottieImageView(r9);
        this.muteButton = r0;
        r0.setAnimation(this.bigMicDrawable);
        this.muteButton.setScaleType(ImageView.ScaleType.CENTER);
        addView(this.muteButton);
        this.prepareToRemoveShader = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(350.0f), 0.0f, new int[]{-2801343, -561538, 0}, new float[]{0.0f, 0.4f, 1.0f}, Shader.TileMode.CLAMP);
        if (r11 == false) goto L10;
        setState(0);
        return;
    }

    private void setAmplitude(double r3) {
        float r32 = (float) (Math.min(8500.0d, r3) / 8500.0d);
        this.animateToAmplitude = r32;
        this.animateAmplitudeDiff = (r32 - this.amplitude) / ((BlobDrawable.AMPLITUDE_SPEED * 500.0f) + 100.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateButtonState() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.groupCall == null) goto L34;
        int r1 = r0.getCallState();
        if (r1 == 1) goto L31;
        if (r1 == 2) goto L31;
        if (r1 == 6) goto L31;
        if (r1 == 5) goto L31;
        TLRPC.GroupCallParticipant r12 = r0.groupCall.participants.get(r0.getSelfId());
        if (r12 != null) goto L17;
    L29:
        setState(r0.isMicMute() ? 1 : 0);
        return;
    L17:
        if (r12.can_self_unmute == true) goto L29;
        if (r12.muted == false) goto L29;
        if (ChatObject.canManageCalls(r0.getChat()) == true) goto L29;
        if (r0.isMicMute() == true) goto L25;
        r0.setMicMute(true, false, false);
    L25:
        setState(3);
        long r13 = SystemClock.uptimeMillis();
        MotionEvent r02 = MotionEvent.obtain(r13, r13, 3, 0.0f, 0.0f, 0);
        if (getParent() == null) goto L35;
        ((View) getParent()).dispatchTouchEvent(r02);
        return;
    L35:
        return;
    L31:
        setState(2);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.webRtcMicAmplitudeEvent) goto L7;
        setAmplitude(((Float) r3[0]).floatValue() * 4000.0f);
        return;
    L7:
        if (r1 != NotificationCenter.groupCallUpdated) goto L10;
        updateButtonState();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.stub == true) goto L19;
        setAmplitude(0.0d);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.webRtcMicAmplitudeEvent);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupCallUpdated);
        if (VoIPService.getSharedInstance() != null) goto L7;
    L9:
        boolean r0 = false;
    L11:
        if (VoIPService.getSharedInstance() == null) goto L13;
        VoIPService.getSharedInstance().registerStateListener(this);
    L13:
        RLottieDrawable r3 = this.bigMicDrawable;
        if (r0 == false) goto L16;
        int r02 = 13;
    L17:
        r3.setCustomEndFrame(r02);
        RLottieDrawable r03 = this.bigMicDrawable;
        r03.setCurrentFrame(r03.getCustomEndFrame() - 1, false, true);
        updateButtonState();
        return;
    L16:
        r02 = 24;
        goto L17
    L7:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L9;
        r0 = true;
        goto L11
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onAudioSettingsChanged() {
        if (VoIPService.getSharedInstance() != null) goto L5;
    L7:
        boolean r0 = false;
    L8:
        RLottieDrawable r2 = this.bigMicDrawable;
        if (r0 == false) goto L11;
        int r3 = 13;
    L13:
        if (r2.setCustomEndFrame(r3) == false) goto L18;
        RLottieDrawable r22 = this.bigMicDrawable;
        if (r0 == false) goto L17;
        r22.setCurrentFrame(0);
        goto L18
    L17:
        r22.setCurrentFrame(12);
    L18:
        this.muteButton.playAnimation();
        updateButtonState();
        return;
    L11:
        r3 = 24;
        goto L13
    L5:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L7;
        r0 = true;
        goto L8
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.stub == true) goto L8;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.webRtcMicAmplitudeEvent);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupCallUpdated);
        if (VoIPService.getSharedInstance() == null) goto L9;
        VoIPService.getSharedInstance().unregisterStateListener(this);
        return;
    L9:
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r19) {
        super.onDraw(r19);
        if (getAlpha() == 0.0f) goto L140;
        float r2 = getMeasuredWidth() >> 1;
        float r5 = getMeasuredHeight() >> 1;
        boolean r6 = this.pressedState;
        if (r6 == false) goto L12;
        float r9 = this.pressedProgress;
        if (r9 == 1.0f) goto L12;
        float r92 = r9 + 0.10666667f;
        this.pressedProgress = r92;
        if (r92 <= 1.0f) goto L18;
        this.pressedProgress = 1.0f;
    L18:
        float r93 = 0.1f;
        float r62 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.pressedProgress) * 0.1f;
        float r10 = r62 + 1.0f;
        this.muteButton.setScaleY(r10);
        this.muteButton.setScaleX(r10);
        if (this.stub == false) goto L23;
        long r11 = System.currentTimeMillis();
        if ((r11 - this.lastStubUpdateAmplitude) <= 1000) goto L23;
        this.lastStubUpdateAmplitude = r11;
        float r7 = ((Math.abs(this.random.nextInt() % 100) * 0.5f) / 100.0f) + 0.5f;
        this.animateToAmplitude = r7;
        this.animateAmplitudeDiff = (r7 - this.amplitude) / ((BlobDrawable.AMPLITUDE_SPEED * 1500.0f) + 100.0f);
    L23:
        float r72 = this.animateToAmplitude;
        float r112 = this.amplitude;
        if (r72 == r112) goto L34;
        float r12 = this.animateAmplitudeDiff;
        float r113 = r112 + (16.0f * r12);
        this.amplitude = r113;
        if (r12 <= 0.0f) goto L31;
        if (r113 <= r72) goto L34;
        this.amplitude = r72;
        goto L34
    L31:
        if (r113 >= r72) goto L34;
        this.amplitude = r72;
    L34:
        if (this.previousState == null) goto L38;
        float r73 = this.progressToState + 0.064f;
        this.progressToState = r73;
        if (r73 <= 1.0f) goto L38;
        this.progressToState = 1.0f;
        this.previousState = null;
    L38:
        boolean r74 = this.prepareToRemove;
        if (r74 == false) goto L48;
        float r122 = this.progressToPrepareRemove;
        if (r122 == 1.0f) goto L48;
        float r123 = r122 + 0.045714285f;
        this.progressToPrepareRemove = r123;
        if (r123 <= 1.0f) goto L46;
        this.progressToPrepareRemove = 1.0f;
    L46:
        if (this.removed == false) goto L54;
        invalidate();
    L54:
        int r124 = 0;
        int r13 = 3;
        if (WeavingState.m10853$$Nest$fgetcurrentState(this.currentState) != 3) goto L57;
    L60:
        boolean r75 = false;
    L61:
        if (r75 == false) goto L67;
        float r15 = this.wavesEnter;
        if (r15 == 1.0f) goto L67;
        float r152 = r15 + 0.045714285f;
        this.wavesEnter = r152;
        if (r152 <= 1.0f) goto L73;
        this.wavesEnter = 1.0f;
    L73:
        float r76 = (this.overshootInterpolator.getInterpolation(this.wavesEnter) * 0.35f) + 0.65f;
        BlobDrawable r114 = this.blobDrawable;
        float r153 = this.amplitude;
        if (this.stub == false) goto L76;
        float r8 = 0.1f;
    L77:
        r114.update(r153, r8);
        BlobDrawable r82 = this.blobDrawable2;
        float r115 = this.amplitude;
        if (this.stub == true) goto L81;
        r93 = 0.8f;
    L81:
        r82.update(r115, r93);
    L82:
        if (r124 >= r13) goto L126;
        if (r124 != 0) goto L88;
        if (this.previousState != null) goto L88;
    L124:
        r124 = r124 + 1;
        r13 = 3;
    L88:
        if (r124 == 0) goto L90;
        if (r124 != 1) goto L106;
        WeavingState r116 = this.currentState;
        if (r116 == null) goto L141;
        if (this.progressToPrepareRemove == 1.0f) goto L124;
        if (this.previousState == null) goto L103;
        float r132 = this.progressToState;
    L104:
        r116.update(16, this.amplitude);
        this.currentState.setToPaint(this.paint);
        float r117 = r132;
    L109:
        this.blobDrawable.maxRadius = AndroidUtilities.dp(40.0f);
        this.blobDrawable.minRadius = AndroidUtilities.dp(32.0f);
        this.blobDrawable2.maxRadius = AndroidUtilities.dp(38.0f);
        this.blobDrawable2.minRadius = AndroidUtilities.dp(33.0f);
        Paint r83 = this.paint;
        if (r124 == 2) goto L112;
        r83.setAlpha((int) ((76.0f * r117) * (1.0f - this.progressToPrepareRemove)));
    L114:
        if (this.wavesEnter == 0.0f) goto L117;
        float r133 = Math.min((((this.amplitude * 0.3f) + 1.0f) + r62) * (1.0f - this.pinnedProgress), 1.3f) * r76;
        r19.save();
        r19.scale(r133, r133, r2, r5);
        this.blobDrawable.draw(r2, r5, r19, this.paint);
        r19.restore();
        float r84 = Math.min((((this.amplitude * 0.26f) + 1.0f) + r62) * (1.0f - this.pinnedProgress), 1.3f) * r76;
        r19.save();
        r19.scale(r84, r84, r2, r5);
        this.blobDrawable2.draw(r2, r5, r19, this.paint);
        r19.restore();
    L117:
        if (r124 != 2) goto L119;
        this.paint.setAlpha((int) (this.progressToPrepareRemove * 255.0f));
    L123:
        r19.save();
        r19.scale(r10, r10, r2, r5);
        r19.drawCircle(r2, r5, AndroidUtilities.dp(32.0f), this.paint);
        r19.restore();
        goto L124
    L119:
        Paint r134 = this.paint;
        if (r124 != 1) goto L122;
        r134.setAlpha((int) (r117 * 255.0f));
        goto L123
    L122:
        r134.setAlpha(255);
        goto L123
    L112:
        r83.setAlpha((int) ((76.0f * r117) * this.progressToPrepareRemove));
        goto L114
    L103:
        r132 = 1.0f;
        goto L104
    L141:
        return;
    L106:
        if (this.progressToPrepareRemove == 0.0f) goto L124;
        this.paint.setColor(Opcodes.V_PREVIEW);
        this.matrix.reset();
        this.matrix.postTranslate((-AndroidUtilities.dp(250.0f)) * (1.0f - this.progressToPrepareRemove), 0.0f);
        this.matrix.postRotate(this.removeAngle, r2, r5);
        this.prepareToRemoveShader.setLocalMatrix(this.matrix);
        this.paint.setShader(this.prepareToRemoveShader);
        r117 = 1.0f;
        goto L109
    L90:
        if (this.progressToPrepareRemove == 1.0f) goto L124;
        r117 = 1.0f - this.progressToState;
        this.previousState.update(16, this.amplitude);
        this.previousState.setToPaint(this.paint);
        goto L109
    L126:
        if (this.removed == false) goto L128;
        return;
    L128:
        if (this.wavesEnter <= 0.0f) goto L139;
        invalidate();
        return;
    L139:
        return;
    L76:
        r8 = 0.8f;
    L67:
        if (r75 == true) goto L73;
        float r77 = this.wavesEnter;
        if (r77 == 0.0f) goto L73;
        float r78 = r77 - 0.045714285f;
        this.wavesEnter = r78;
        if (r78 >= 0.0f) goto L73;
        this.wavesEnter = 0.0f;
        goto L73
    L57:
        if (WeavingState.m10853$$Nest$fgetcurrentState(this.currentState) == 2) goto L60;
        r75 = true;
    L48:
        if (r74 == true) goto L54;
        float r79 = this.progressToPrepareRemove;
        if (r79 == 0.0f) goto L54;
        float r710 = r79 - 0.045714285f;
        this.progressToPrepareRemove = r710;
        if (r710 >= 0.0f) goto L54;
        this.progressToPrepareRemove = 0.0f;
    L12:
        if (r6 == true) goto L18;
        float r63 = this.pressedProgress;
        if (r63 == 0.0f) goto L18;
        float r64 = r63 - 0.10666667f;
        this.pressedProgress = r64;
        if (r64 >= 0.0f) goto L18;
        this.pressedProgress = 0.0f;
        goto L18
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        if (GroupCallPip.getInstance() != null) goto L5;
        return;
    L5:
        if (GroupCallPip.getInstance().showAlert == false) goto L8;
        int r2 = R.string.AccDescrCloseMenu;
    L9:
        r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(r2)));
        return;
    L8:
        r2 = R.string.AccDescrOpenMenu2;
        goto L9
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onStateChanged(int r1) {
        updateButtonState();
    }

    public void prepareToRemove(boolean r2) {
        if (this.prepareToRemove == r2) goto L5;
        invalidate();
    L5:
        this.prepareToRemove = r2;
    }

    public void setPinnedProgress(float r1) {
        this.pinnedProgress = r1;
    }

    public void setPressedState(boolean r1) {
        this.pressedState = r1;
    }

    public void setRemoveAngle(double r1) {
        this.removeAngle = (float) r1;
    }

    public void setState(int r6) {
        WeavingState r0 = this.currentState;
        if (r0 != null) goto L5;
    L7:
        WeavingState r02 = this.currentState;
        this.previousState = r02;
        WeavingState r1 = this.states[r6];
        this.currentState = r1;
        float r4 = 0.0f;
        if (r02 == null) goto L10;
        this.progressToState = 0.0f;
    L17:
        VoIPService r03 = VoIPService.getSharedInstance();
        if (r03 != null) goto L20;
    L22:
        String r04 = LocaleController.getString(R.string.VoipGroupVoiceChat);
    L24:
        if (r6 != 0) goto L26;
        r04 = r04 + ", " + LocaleController.getString(R.string.VoipTapToMute);
    L30:
        setContentDescription(r04);
        invalidate();
        return;
    L26:
        if (r6 != 2) goto L28;
        r04 = r04 + ", " + LocaleController.getString(R.string.Connecting);
        goto L30
    L28:
        if (r6 != 3) goto L30;
        r04 = r04 + ", " + LocaleController.getString(R.string.VoipMutedByAdmin);
        goto L30
    L20:
        if (ChatObject.isChannelOrGiga(r03.getChat()) == false) goto L22;
        r04 = LocaleController.getString(R.string.VoipChannelVoiceChat);
        goto L24
    L10:
        this.progressToState = 1.0f;
        if (WeavingState.m10853$$Nest$fgetcurrentState(r1) != 3) goto L13;
    L16:
        this.wavesEnter = r4;
        goto L17
    L13:
        if (WeavingState.m10853$$Nest$fgetcurrentState(this.currentState) == 2) goto L16;
        r4 = 1.0f;
        goto L16
    L5:
        if (WeavingState.m10853$$Nest$fgetcurrentState(r0) != r6) goto L7;
    }
}
