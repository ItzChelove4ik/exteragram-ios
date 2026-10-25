package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class FragmentContextViewWavesDrawable {
    private float amplitude;
    private float amplitude2;
    private float animateAmplitudeDiff;
    private float animateAmplitudeDiff2;
    private float animateToAmplitude;
    WeavingState currentState;
    private long lastUpdateTime;
    Paint paint;
    ArrayList<View> parents;
    Path path;
    WeavingState pausedState;
    WeavingState previousState;
    float progressToState;
    WeavingState[] states;

    public static class WeavingState {
        int blueKey1;
        int blueKey2;
        int color1;
        int color2;
        int color3;
        private final int currentState;
        private float duration;
        int greenKey1;
        int greenKey2;
        private final Matrix matrix;
        int mutedByAdmin;
        int mutedByAdmin2;
        int mutedByAdmin3;
        public Shader shader;
        private float startX;
        private float startY;
        private float targetX;
        private float targetY;
        private float time;

        /* renamed from: -$$Nest$fgetcurrentState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10801$$Nest$fgetcurrentState(WeavingState r0) {
            return r0.currentState;
        }

        public WeavingState(int r2) {
            this.targetX = -1.0f;
            this.targetY = -1.0f;
            this.matrix = new Matrix();
            this.greenKey1 = Theme.key_voipgroup_topPanelGreen1;
            this.greenKey2 = Theme.key_voipgroup_topPanelGreen2;
            this.blueKey1 = Theme.key_voipgroup_topPanelBlue1;
            this.blueKey2 = Theme.key_voipgroup_topPanelBlue2;
            this.mutedByAdmin = Theme.key_voipgroup_mutedByAdminGradient;
            this.mutedByAdmin2 = Theme.key_voipgroup_mutedByAdminGradient2;
            this.mutedByAdmin3 = Theme.key_voipgroup_mutedByAdminGradient3;
            this.currentState = r2;
            createGradients();
        }

        private void createGradients() {
            int r0 = this.currentState;
            if (r0 != 0) goto L7;
            int r02 = Theme.getColor(this.greenKey1);
            this.color1 = r02;
            int r2 = Theme.getColor(this.greenKey2);
            this.color2 = r2;
            this.shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{r02, r2}, null, Shader.TileMode.CLAMP);
            return;
        L7:
            if (r0 != 1) goto L11;
            int r03 = Theme.getColor(this.blueKey1);
            this.color1 = r03;
            int r1 = Theme.getColor(this.blueKey2);
            this.color2 = r1;
            this.shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{r03, r1}, null, Shader.TileMode.CLAMP);
            return;
        L11:
            if (r0 != 3) goto L14;
            int r04 = Theme.getColor(this.mutedByAdmin);
            this.color1 = r04;
            int r3 = Theme.getColor(this.mutedByAdmin3);
            this.color3 = r3;
            int r4 = Theme.getColor(this.mutedByAdmin2);
            this.color2 = r4;
            this.shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{r04, r3, r4}, new float[]{0.0f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
            return;
        }

        public void checkColor() {
            int r0 = this.currentState;
            if (r0 != 0) goto L11;
            if (this.color1 == Theme.getColor(this.greenKey1)) goto L7;
        L8:
            createGradients();
            return;
        L7:
            if (this.color2 != Theme.getColor(this.greenKey2)) goto L8;
            return;
        L11:
            if (r0 != 1) goto L19;
            if (this.color1 == Theme.getColor(this.blueKey1)) goto L15;
        L16:
            createGradients();
            return;
        L15:
            if (this.color2 != Theme.getColor(this.blueKey2)) goto L16;
            return;
        L19:
            if (r0 == 3) goto L21;
            return;
        L21:
            if (this.color1 == Theme.getColor(this.mutedByAdmin)) goto L23;
        L24:
            createGradients();
            return;
        L23:
            if (this.color2 != Theme.getColor(this.mutedByAdmin2)) goto L24;
        }

        public void setToPaint(Paint r5) {
            int r0 = this.currentState;
            if (r0 == 0) goto L11;
            if (r0 == 1) goto L11;
            if (r0 == 3) goto L11;
            r5.setShader(null);
            r5.setColor(Theme.getColor(Theme.key_voipgroup_topPanelGray));
            return;
        L11:
            if (LiteMode.isEnabled(512) == true) goto L18;
            r5.setShader(null);
            int r02 = this.currentState;
            int r1 = this.color1;
            if (r02 != 3) goto L16;
            r5.setColor(ColorUtils.blendARGB(ColorUtils.blendARGB(r1, this.color2, 0.5f), this.color3, 0.5f));
            return;
        L16:
            r5.setColor(ColorUtils.blendARGB(r1, this.color2, 0.5f));
            return;
        L18:
            r5.setShader(this.shader);
        }

        public void update(int r12, int r13, long r14, float r16) {
            if (this.currentState != 2) goto L5;
            return;
        L5:
            float r0 = this.duration;
            if (r0 != 0.0f) goto L8;
        L9:
            this.duration = Utilities.random.nextInt(700) + MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
            this.time = 0.0f;
            if (this.targetX != (-1.0f)) goto L17;
            int r02 = this.currentState;
            if (r02 != 3) goto L14;
            this.targetX = ((Utilities.random.nextInt(100) * 0.05f) / 100.0f) - 0.3f;
            this.targetY = ((Utilities.random.nextInt(100) * 0.05f) / 100.0f) + 0.7f;
            goto L17
        L14:
            if (r02 != 0) goto L16;
            this.targetX = ((Utilities.random.nextInt(100) * 0.2f) / 100.0f) - 0.3f;
            this.targetY = ((Utilities.random.nextInt(100) * 0.3f) / 100.0f) + 0.7f;
            goto L17
        L16:
            this.targetX = ((Utilities.random.nextInt(100) / 100.0f) * 0.2f) + 1.1f;
            this.targetY = (Utilities.random.nextInt(100) * 4.0f) / 100.0f;
        L17:
            this.startX = this.targetX;
            this.startY = this.targetY;
            int r03 = this.currentState;
            if (r03 != 3) goto L20;
            this.targetX = ((Utilities.random.nextInt(100) * 0.05f) / 100.0f) - 0.3f;
            this.targetY = ((Utilities.random.nextInt(100) * 0.05f) / 100.0f) + 0.7f;
        L23:
            float r1 = r14;
            float r04 = this.time + (((BlobDrawable.GRADIENT_SPEED_MIN + 0.5f) * r1) + ((r1 * (BlobDrawable.GRADIENT_SPEED_MAX * 2.0f)) * r16));
            this.time = r04;
            float r15 = this.duration;
            if (r04 <= r15) goto L26;
            this.time = r15;
        L26:
            float r05 = CubicBezierInterpolator.EASE_OUT.getInterpolation(this.time / r15);
            float r132 = r13;
            float r17 = this.startX;
            float r18 = ((r17 + ((this.targetX - r17) * r05)) * r132) - 200.0f;
            float r4 = this.startY;
            float r122 = (r12 * (r4 + ((this.targetY - r4) * r05))) - 200.0f;
            float r133 = r132 / 400.0f;
            int r06 = this.currentState;
            if (r06 == 0) goto L31;
            if (r06 == 3) goto L31;
            float r07 = 1.5f;
        L32:
            float r134 = r133 * r07;
            this.matrix.reset();
            this.matrix.postTranslate(r18, r122);
            this.matrix.postScale(r134, r134, r18 + 200.0f, r122 + 200.0f);
            this.shader.setLocalMatrix(this.matrix);
            return;
        L31:
            r07 = 3.0f;
            goto L32
        L20:
            if (r03 != 0) goto L22;
            this.targetX = ((Utilities.random.nextInt(100) * 0.2f) / 100.0f) - 0.3f;
            this.targetY = ((Utilities.random.nextInt(100) * 0.3f) / 100.0f) + 0.7f;
            goto L23
        L22:
            this.targetX = ((Utilities.random.nextInt(100) / 100.0f) * 0.2f) + 1.1f;
            this.targetY = (Utilities.random.nextInt(100) * 4.0f) / 100.0f;
            goto L23
        L8:
            if (this.time < r0) goto L23;
            goto L9
        }
    }

    public FragmentContextViewWavesDrawable() {
        this.states = new WeavingState[4];
        this.progressToState = 1.0f;
        this.parents = new ArrayList();
        this.paint = new Paint(1);
        this.path = new Path();
        int r1 = 0;
    L3:
        if (r1 >= 4) goto L5;
        this.states[r1] = new WeavingState(r1);
        r1 = r1 + 1;
        goto L3
    }

    private void checkColors() {
        int r0 = 0;
    L3:
        WeavingState[] r1 = this.states;
        if (r0 >= r1.length) goto L6;
        r1[r0].checkColor();
        r0 = r0 + 1;
        goto L3
    }

    private void setState(int r2, boolean r3) {
        WeavingState r0 = this.currentState;
        if (r0 == null) goto L8;
        if (WeavingState.m10801$$Nest$fgetcurrentState(r0) != r2) goto L8;
        return;
    L8:
        if (VoIPService.getSharedInstance() == null) goto L10;
    L13:
        if (r3 == false) goto L15;
        WeavingState r32 = this.currentState;
    L16:
        this.previousState = r32;
        this.currentState = this.states[r2];
        if (r32 == null) goto L20;
        this.progressToState = 0.0f;
        return;
    L20:
        this.progressToState = 1.0f;
        return;
    L15:
        r32 = null;
        goto L16
    L10:
        if (this.currentState != null) goto L13;
        this.currentState = this.pausedState;
    }

    public void addParent(View r2) {
        if (this.parents.contains(r2) == true) goto L6;
        this.parents.add(r2);
        return;
    }

    public void draw(float r21, float r22, float r23, float r24, Canvas r25, FragmentContextView r26, float r27) {
        checkColors();
        int r1 = 0;
        if (r26 != null) goto L6;
    L4:
        boolean r3 = false;
    L9:
        if (r22 > r24) goto L96;
        WeavingState r4 = this.currentState;
        if (r4 != null) goto L14;
    L24:
        boolean r42 = false;
    L25:
        if (r3 == false) goto L33;
        long r5 = SystemClock.elapsedRealtime();
        long r7 = r5 - this.lastUpdateTime;
        this.lastUpdateTime = r5;
        if (r7 <= 20) goto L30;
        r7 = 17;
    L30:
        if (r7 >= 3) goto L32;
        r3 = false;
    L32:
        long r8 = r7;
        if (r3 == false) goto L65;
        float r52 = this.animateToAmplitude;
        float r6 = this.amplitude;
        if (r52 == r6) goto L47;
        float r72 = this.animateAmplitudeDiff;
        float r62 = r6 + (r8 * r72);
        this.amplitude = r62;
        if (r72 <= 0.0f) goto L44;
        if (r62 <= r52) goto L46;
        this.amplitude = r52;
    L46:
        r26.invalidate();
        goto L47
    L44:
        if (r62 >= r52) goto L46;
        this.amplitude = r52;
    L47:
        float r53 = this.animateToAmplitude;
        float r63 = this.amplitude2;
        if (r53 == r63) goto L59;
        float r73 = this.animateAmplitudeDiff2;
        float r64 = r63 + (r8 * r73);
        this.amplitude2 = r64;
        if (r73 <= 0.0f) goto L55;
        if (r64 <= r53) goto L57;
        this.amplitude2 = r53;
    L57:
        r26.invalidate();
        goto L59
    L55:
        if (r64 >= r53) goto L57;
        this.amplitude2 = r53;
    L59:
        if (this.previousState == null) goto L65;
        float r54 = this.progressToState + (r8 / 250.0f);
        this.progressToState = r54;
        if (r54 <= 1.0f) goto L63;
        this.progressToState = 1.0f;
        this.previousState = null;
    L63:
        r26.invalidate();
    L65:
        if (r1 >= 2) goto L103;
        if (r1 == 0) goto L68;
    L70:
        if (r1 != 0) goto L72;
        float r55 = 1.0f - this.progressToState;
        this.previousState.setToPaint(this.paint);
    L83:
        if (r1 != 1) goto L86;
        if (r42 == false) goto L86;
        this.paint.setAlpha(255);
    L91:
        if (r1 != 1) goto L94;
        if (r42 == false) goto L94;
        this.path.rewind();
        float r12 = ((r23 - r21) * 1.1f) * r55;
        this.path.addCircle(r23 - AndroidUtilities.dp(18.0f), r22 + ((r24 - r22) / 2.0f), r12, Path.Direction.CW);
        r25.save();
        r25.clipPath(this.path);
        r25.drawRoundRect(r21, r22, r23, r24, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), this.paint);
        r25.restore();
    L95:
        r1 = r1 + 1;
    L94:
        r25.drawRoundRect(r21, r22, r23, r24, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), this.paint);
    L86:
        Paint r74 = this.paint;
        if (r1 != 1) goto L89;
        r74.setAlpha((int) (255.0f * r55));
        goto L91
    L89:
        r74.setAlpha(255);
        goto L91
    L72:
        WeavingState r56 = this.currentState;
        if (r56 == null) goto L104;
        if (this.previousState == null) goto L78;
        float r122 = this.progressToState;
    L79:
        if (r3 == false) goto L81;
        r56.update((int) (r24 - r22), (int) (r23 - r21), r8, this.amplitude);
    L81:
        this.currentState.setToPaint(this.paint);
        r55 = r122;
        goto L83
    L78:
        r122 = 1.0f;
        goto L79
    L104:
        return;
    L68:
        if (this.previousState != null) goto L70;
    L103:
        return;
    L33:
        r7 = 0;
        goto L32
    L14:
        if (this.previousState == null) goto L24;
        if (WeavingState.m10801$$Nest$fgetcurrentState(r4) != 1) goto L20;
        if (WeavingState.m10801$$Nest$fgetcurrentState(this.previousState) != 0) goto L20;
    L23:
        r42 = true;
    L20:
        if (WeavingState.m10801$$Nest$fgetcurrentState(this.previousState) != 1) goto L24;
        if (WeavingState.m10801$$Nest$fgetcurrentState(this.currentState) != 0) goto L24;
    L96:
        return;
    L6:
        if (this.parents.size() <= 0) goto L4;
        r3 = true;
        goto L9
    }

    public void removeParent(View r2) {
        this.parents.remove(r2);
        if (this.parents.isEmpty() == false) goto L6;
        this.pausedState = this.currentState;
        this.currentState = null;
        this.previousState = null;
        return;
    }

    public void setAmplitude(float r4) {
        this.animateToAmplitude = r4;
        float r0 = this.amplitude;
        this.animateAmplitudeDiff = (r4 - r0) / 250.0f;
        this.animateAmplitudeDiff2 = (r4 - r0) / 120.0f;
    }

    public void updateState(boolean r7) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L33;
        int r1 = r0.getCallState();
        if (r0.isSwitchingStream() == false) goto L7;
    L15:
        ChatObject.Call r12 = r0.groupCall;
        if (r12 == null) goto L31;
        TLRPC.GroupCallParticipant r13 = r12.participants.get(r0.getSelfId());
        if (r13 == null) goto L26;
        if (r13.can_self_unmute == true) goto L26;
        if (r13.muted == false) goto L26;
        if (ChatObject.canManageCalls(r0.getChat()) == true) goto L26;
    L27:
        r0.setMicMute(true, false, false);
        setState(3, r7);
        return;
    L26:
        if (r0.groupCall.call.rtmp_stream == true) goto L27;
        setState(r0.isMicMute() ? 1 : 0, r7);
        return;
    L31:
        setState(r0.isMicMute() ? 1 : 0, r7);
        return;
    L7:
        if (r1 == 1) goto L13;
        if (r1 == 2) goto L13;
        if (r1 == 6) goto L13;
        if (r1 != 5) goto L15;
    L13:
        setState(2, r7);
        return;
    }
}
