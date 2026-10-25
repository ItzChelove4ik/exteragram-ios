package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import java.util.Random;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GroupCallUserCell;
import org.telegram.ui.Stories.StoriesGradientTools;

/* loaded from: classes3.dex */
public class AvatarsDrawable {
    public DrawingState[] animatingStates;
    private boolean attached;
    boolean centered;
    public int count;
    public DrawingState[] currentStates;
    int currentStyle;
    public boolean drawStoriesCircle;
    public int height;
    private boolean isInCall;
    public float maxX;
    private float overrideAlpha;
    private int overrideSize;
    private float overrideSizeStepFactor;
    private Paint paint;
    View parent;
    Random random;
    private boolean showSavedMessages;
    StoriesGradientTools storiesTools;
    public int strokeWidth;
    public long transitionDuration;
    private boolean transitionInProgress;
    public Interpolator transitionInterpolator;
    float transitionProgress;
    ValueAnimator transitionProgressAnimator;
    boolean updateAfterTransition;
    Runnable updateDelegate;
    boolean wasDraw;
    public int width;
    private Paint xRefP;

    /* renamed from: org.telegram.ui.Components.AvatarsDrawable$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ AvatarsDrawable this$0;

        public AnonymousClass1(AvatarsDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AvatarsDrawable r22 = this.this$0;
            if (r22.transitionProgressAnimator == null) goto L10;
            r22.transitionProgress = 1.0f;
            AvatarsDrawable.m8963$$Nest$mswapStates(r22);
            AvatarsDrawable r23 = this.this$0;
            if (r23.updateAfterTransition == false) goto L9;
            r23.updateAfterTransition = false;
            Runnable r24 = r23.updateDelegate;
            if (r24 == null) goto L9;
            r24.run();
        L9:
            AvatarsDrawable.m8962$$Nest$minvalidate(this.this$0);
        L10:
            this.this$0.transitionProgressAnimator = null;
        }
    }

    public static class DrawingState {
        private int animationType;
        public AvatarDrawable avatarDrawable;
        private long id;
        private ImageReceiver imageReceiver;
        private long lastSpeakTime;
        private long lastUpdateTime;
        private int moveFromIndex;
        private TLObject object;
        TLRPC.GroupCallParticipant participant;
        private GroupCallUserCell.AvatarWavesDrawable wavesDrawable;

        /* renamed from: -$$Nest$fgetanimationType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8964$$Nest$fgetanimationType(DrawingState r0) {
            return r0.animationType;
        }

        /* renamed from: -$$Nest$fgetid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m8965$$Nest$fgetid(DrawingState r2) {
            return r2.id;
        }

        /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageReceiver m8966$$Nest$fgetimageReceiver(DrawingState r0) {
            return r0.imageReceiver;
        }

        /* renamed from: -$$Nest$fgetlastSpeakTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m8967$$Nest$fgetlastSpeakTime(DrawingState r2) {
            return r2.lastSpeakTime;
        }

        /* renamed from: -$$Nest$fgetlastUpdateTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m8968$$Nest$fgetlastUpdateTime(DrawingState r2) {
            return r2.lastUpdateTime;
        }

        /* renamed from: -$$Nest$fgetmoveFromIndex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8969$$Nest$fgetmoveFromIndex(DrawingState r0) {
            return r0.moveFromIndex;
        }

        /* renamed from: -$$Nest$fgetobject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLObject m8970$$Nest$fgetobject(DrawingState r0) {
            return r0.object;
        }

        /* renamed from: -$$Nest$fgetwavesDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GroupCallUserCell.AvatarWavesDrawable m8971$$Nest$fgetwavesDrawable(DrawingState r0) {
            return r0.wavesDrawable;
        }

        /* renamed from: -$$Nest$fputanimationType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8972$$Nest$fputanimationType(DrawingState r0, int r1) {
            r0.animationType = r1;
        }

        /* renamed from: -$$Nest$fputid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8973$$Nest$fputid(DrawingState r0, long r1) {
            r0.id = r1;
        }

        /* renamed from: -$$Nest$fputimageReceiver, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8974$$Nest$fputimageReceiver(DrawingState r0, ImageReceiver r1) {
            r0.imageReceiver = r1;
        }

        /* renamed from: -$$Nest$fputlastSpeakTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8975$$Nest$fputlastSpeakTime(DrawingState r0, long r1) {
            r0.lastSpeakTime = r1;
        }

        /* renamed from: -$$Nest$fputlastUpdateTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8976$$Nest$fputlastUpdateTime(DrawingState r0, long r1) {
            r0.lastUpdateTime = r1;
        }

        /* renamed from: -$$Nest$fputmoveFromIndex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8977$$Nest$fputmoveFromIndex(DrawingState r0, int r1) {
            r0.moveFromIndex = r1;
        }

        /* renamed from: -$$Nest$fputobject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8978$$Nest$fputobject(DrawingState r0, TLObject r1) {
            r0.object = r1;
        }

        /* renamed from: -$$Nest$fputwavesDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8979$$Nest$fputwavesDrawable(DrawingState r0, GroupCallUserCell.AvatarWavesDrawable r1) {
            r0.wavesDrawable = r1;
        }

        public DrawingState() {
        }
    }

    public static /* synthetic */ void $r8$lambda$kKL9_jENFbp74tvbP4eADeRAVqM(AvatarsDrawable r0, ValueAnimator r1) {
        r0.lambda$commitTransition$0(r1);
    }

    /* renamed from: -$$Nest$minvalidate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8962$$Nest$minvalidate(AvatarsDrawable r0) {
        r0.invalidate();
    }

    /* renamed from: -$$Nest$mswapStates, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8963$$Nest$mswapStates(AvatarsDrawable r0) {
        r0.swapStates();
    }

    public AvatarsDrawable(View r9, boolean r10) {
        this.currentStates = new DrawingState[3];
        this.animatingStates = new DrawingState[3];
        this.transitionProgress = 1.0f;
        this.paint = new Paint(1);
        this.xRefP = new Paint(1);
        this.strokeWidth = AndroidUtilities.dp(1.67f);
        this.overrideSizeStepFactor = 0.8f;
        this.overrideAlpha = 1.0f;
        this.transitionDuration = 220;
        this.transitionInterpolator = CubicBezierInterpolator.DEFAULT;
        this.random = new Random();
        this.parent = r9;
        int r2 = 0;
    L3:
        if (r2 >= 3) goto L5;
        this.currentStates[r2] = new DrawingState();
        DrawingState.m8974$$Nest$fputimageReceiver(this.currentStates[r2], new ImageReceiver(r9));
        DrawingState.m8966$$Nest$fgetimageReceiver(this.currentStates[r2]).setInvalidateAll(true);
        DrawingState.m8966$$Nest$fgetimageReceiver(this.currentStates[r2]).setRoundRadius(ExteraConfig.getAvatarCorners(24.0f));
        this.currentStates[r2].avatarDrawable = new AvatarDrawable();
        this.currentStates[r2].avatarDrawable.setTextSize(AndroidUtilities.dp(16.0f));
        this.animatingStates[r2] = new DrawingState();
        DrawingState.m8974$$Nest$fputimageReceiver(this.animatingStates[r2], new ImageReceiver(r9));
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r2]).setInvalidateAll(true);
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r2]).setRoundRadius(ExteraConfig.getAvatarCorners(24.0f));
        this.animatingStates[r2].avatarDrawable = new AvatarDrawable();
        this.animatingStates[r2].avatarDrawable.setTextSize(AndroidUtilities.dp(16.0f));
        r2 = r2 + 1;
        goto L3
    L5:
        this.isInCall = r10;
        this.xRefP.setColor(0);
        this.xRefP.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    private void invalidate() {
        View r0 = this.parent;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$commitTransition$0(ValueAnimator r1) {
        this.transitionProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void swapStates() {
        int r0 = 0;
    L4:
        if (r0 >= 3) goto L6;
        DrawingState[] r1 = this.currentStates;
        DrawingState r2 = r1[r0];
        DrawingState[] r3 = this.animatingStates;
        r1[r0] = r3[r0];
        r3[r0] = r2;
        r0 = r0 + 1;
        goto L4
    }

    public void animateFromState(AvatarsDrawable r6, int r7, boolean r8) {
        if (r6 != null) goto L4;
        return;
    L4:
        ValueAnimator r0 = r6.transitionProgressAnimator;
        int r1 = 0;
        if (r0 == null) goto L9;
        r0.cancel();
        if (this.transitionInProgress == false) goto L9;
        this.transitionInProgress = false;
        swapStates();
    L9:
        TLObject[] r2 = new TLObject[3];
        int r3 = 0;
    L10:
        if (r3 >= 3) goto L12;
        r2[r3] = DrawingState.m8970$$Nest$fgetobject(this.currentStates[r3]);
        setObject(r3, r7, DrawingState.m8970$$Nest$fgetobject(r6.currentStates[r3]));
        r3 = r3 + 1;
        goto L10
    L12:
        commitTransition(false);
    L13:
        if (r1 >= 3) goto L15;
        setObject(r1, r7, r2[r1]);
        r1 = r1 + 1;
        goto L13
    L15:
        this.wasDraw = true;
        commitTransition(true, r8);
    }

    public void commitTransition(boolean r2) {
        commitTransition(r2, true);
    }

    public void commitTransition(boolean r11, boolean r12) {
        if (this.wasDraw == false) goto L48;
        if (r11 == false) goto L48;
        DrawingState[] r0 = new DrawingState[3];
        int r3 = 0;
        boolean r4 = false;
    L8:
        if (r3 >= 3) goto L14;
        DrawingState[] r6 = this.currentStates;
        r0[r3] = r6[r3];
        if (DrawingState.m8965$$Nest$fgetid(r6[r3]) == DrawingState.m8965$$Nest$fgetid(this.animatingStates[r3])) goto L12;
        r4 = true;
    L13:
        r3 = r3 + 1;
        goto L8
    L12:
        DrawingState.m8975$$Nest$fputlastSpeakTime(this.currentStates[r3], DrawingState.m8967$$Nest$fgetlastSpeakTime(this.animatingStates[r3]));
        goto L13
    L14:
        if (r4 == true) goto L17;
        this.transitionProgress = 1.0f;
        return;
    L17:
        int r1 = 0;
    L19:
        if (r1 >= 3) goto L31;
        int r42 = 0;
    L21:
        if (r42 >= 3) goto L29;
        if (DrawingState.m8965$$Nest$fgetid(this.currentStates[r42]) == DrawingState.m8965$$Nest$fgetid(this.animatingStates[r1])) goto L24;
        r42 = r42 + 1;
        goto L21
    L24:
        r0[r42] = null;
        DrawingState[] r62 = this.animatingStates;
        if (r1 != r42) goto L27;
        DrawingState.m8972$$Nest$fputanimationType(r62[r1], -1);
        GroupCallUserCell.AvatarWavesDrawable r32 = DrawingState.m8971$$Nest$fgetwavesDrawable(this.animatingStates[r1]);
        DrawingState.m8979$$Nest$fputwavesDrawable(this.animatingStates[r1], DrawingState.m8971$$Nest$fgetwavesDrawable(this.currentStates[r1]));
        DrawingState.m8979$$Nest$fputwavesDrawable(this.currentStates[r1], r32);
    L30:
        r1 = r1 + 1;
        goto L19
    L27:
        DrawingState.m8972$$Nest$fputanimationType(r62[r1], 2);
        DrawingState.m8977$$Nest$fputmoveFromIndex(this.animatingStates[r1], r42);
        goto L30
    L29:
        DrawingState.m8972$$Nest$fputanimationType(this.animatingStates[r1], 0);
        goto L30
    L31:
        int r13 = 0;
    L32:
        if (r13 >= 3) goto L37;
        DrawingState r43 = r0[r13];
        if (r43 == null) goto L36;
        DrawingState.m8972$$Nest$fputanimationType(r43, 1);
    L36:
        r13 = r13 + 1;
        goto L32
    L37:
        ValueAnimator r112 = this.transitionProgressAnimator;
        if (r112 == null) goto L42;
        r112.removeAllListeners();
        this.transitionProgressAnimator.cancel();
        if (this.transitionInProgress == false) goto L42;
        swapStates();
        this.transitionInProgress = false;
    L42:
        this.transitionProgress = 0.0f;
        if (r12 == false) goto L45;
        ValueAnimator r113 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.transitionProgressAnimator = r113;
        r113.addUpdateListener(new AvatarsDrawable$$ExternalSyntheticLambda0(this));
        this.transitionProgressAnimator.addListener(new AnonymousClass1(this));
        this.transitionProgressAnimator.setDuration(this.transitionDuration);
        this.transitionProgressAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.transitionProgressAnimator.start();
    L46:
        invalidate();
        return;
    L45:
        this.transitionInProgress = true;
    L48:
        this.transitionProgress = 1.0f;
        swapStates();
    }

    public float getMaxX() {
        return this.maxX;
    }

    public int getSize() {
        int r0 = this.overrideSize;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r1 = this.currentStyle;
        if (r1 != 4) goto L8;
    L11:
        float r12 = 32.0f;
    L13:
        return AndroidUtilities.dp(r12);
    L8:
        if (r1 == 10) goto L11;
        r12 = 24.0f;
        goto L13
    }

    public float getUsedWidth() {
        int r0 = this.currentStyle;
        int r2 = 0;
        if (r0 != 4) goto L5;
    L8:
        boolean r1 = true;
    L10:
        if (r0 != 11) goto L12;
        int r02 = AndroidUtilities.dp(12.0f);
    L19:
        int r12 = 0;
        int r3 = 0;
    L21:
        if (r12 >= 3) goto L26;
        if (DrawingState.m8965$$Nest$fgetid(this.currentStates[r12]) == 0) goto L25;
        r3 = r3 + 1;
    L25:
        r12 = r12 + 1;
        goto L21
    L26:
        int r13 = Math.max(0, r3 - 1) * r02;
        if (r3 <= 0) goto L30;
        r2 = getSize();
    L30:
        return r13 + r2;
    L12:
        int r03 = this.overrideSize;
        if (r03 == 0) goto L15;
        r02 = (int) (r03 * this.overrideSizeStepFactor);
        goto L19
    L15:
        if (r1 == false) goto L17;
        float r04 = 24.0f;
    L18:
        r02 = AndroidUtilities.dp(r04);
        goto L19
    L17:
        r04 = 20.0f;
        goto L18
    L5:
        if (r0 == 10) goto L8;
        r1 = false;
        goto L10
    }

    public void onAttachedToWindow() {
        if (this.attached == true) goto L9;
        this.attached = true;
        int r0 = 0;
    L7:
        if (r0 >= 3) goto L11;
        DrawingState.m8966$$Nest$fgetimageReceiver(this.currentStates[r0]).onAttachedToWindow();
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r0]).onAttachedToWindow();
        r0 = r0 + 1;
        goto L7
    L11:
        return;
    }

    public void onDetachedFromWindow() {
        if (this.attached == false) goto L15;
        int r0 = 0;
        this.attached = false;
        this.wasDraw = false;
    L7:
        if (r0 >= 3) goto L10;
        DrawingState.m8966$$Nest$fgetimageReceiver(this.currentStates[r0]).onDetachedFromWindow();
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r0]).onDetachedFromWindow();
        r0 = r0 + 1;
        goto L7
    L10:
        if (this.currentStyle != 3) goto L14;
        Theme.getFragmentContextViewWavesDrawable().setAmplitude(0.0f);
        return;
    L14:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas r41) {
        int r9 = 1;
        this.wasDraw = true;
        int r1 = this.currentStyle;
        int r10 = 10;
        int r12 = 4;
        if (r1 == 4) goto L7;
        if (r1 == 10) goto L7;
        boolean r13 = false;
    L8:
        int r14 = getSize();
        int r15 = 11;
        if (this.currentStyle != 11) goto L12;
        int r16 = AndroidUtilities.dp(12.0f);
    L11:
        int r162 = r16;
        int r17 = 0;
    L21:
        if (r17 >= 3) goto L23;
        DrawingState.m8965$$Nest$fgetid(this.currentStates[r17]);
        r17 = r17 + 1;
        goto L21
    L23:
        int r18 = this.currentStyle;
        if (r18 == 0) goto L29;
        if (r18 == 10) goto L29;
        if (r18 == 11) goto L29;
        int r172 = AndroidUtilities.dp(10.0f);
    L31:
        if (this.centered == false) goto L33;
        int r182 = (this.width - ((int) getUsedWidth())) / 2;
    L35:
        if (VoIPService.getSharedInstance() != null) goto L37;
    L39:
        boolean r19 = false;
    L40:
        int r4 = this.currentStyle;
        if (r4 != 4) goto L43;
        this.paint.setColor(Theme.getColor(Theme.key_inappPlayerBackground));
    L49:
        int r110 = 0;
        int r192 = 0;
    L50:
        if (r110 >= 3) goto L55;
        if (DrawingState.m8965$$Nest$fgetid(this.animatingStates[r110]) == 0) goto L54;
        r192 = r192 + 1;
    L54:
        r110 = r110 + 1;
        goto L50
    L55:
        int r111 = this.currentStyle;
        if (r111 == 0) goto L65;
        if (r111 == 1) goto L65;
        if (r111 == 3) goto L65;
        if (r111 == 4) goto L65;
        if (r111 == 5) goto L65;
        if (r111 == 10) goto L65;
        if (r111 == 11) goto L65;
        boolean r20 = false;
    L67:
        if (r20 == false) goto L75;
        if (r111 != 10) goto L70;
        float r112 = AndroidUtilities.dp(16.0f);
    L72:
        if (this.drawStoriesCircle == false) goto L74;
        r112 = r112 + AndroidUtilities.dp(20.0f);
    L74:
        float r2 = -r112;
        float r6 = this.width + r112;
        float r7 = this.height + r112;
        Canvas r113 = r41;
        float r8 = 0.0f;
        int r11 = 2;
        int r24 = 0;
        r113.saveLayerAlpha(r2, r2, r6, r7, 255, 31);
    L76:
        this.maxX = r8;
        int r3 = -1;
        if (this.drawStoriesCircle == false) goto L157;
        int r22 = r11;
    L79:
        if (r22 < 0) goto L157;
        int r42 = r24;
    L81:
        if (r42 >= r11) goto L156;
        if (r42 == 0) goto L84;
    L86:
        if (r42 != 0) goto L88;
        DrawingState[] r5 = this.animatingStates;
    L89:
        if (r42 == 1) goto L91;
    L95:
        ImageReceiver r62 = DrawingState.m8966$$Nest$fgetimageReceiver(r5[r22]);
        if (r62.hasImageSet() == false) goto L155;
        if (r42 == 0) goto L100;
        r62.setImageX(r182 + (r162 * r22));
    L109:
        int r72 = this.currentStyle;
        if (r72 == 0) goto L118;
        if (r72 == r10) goto L118;
        if (r72 == r15) goto L118;
        if (r72 != r12) goto L116;
        float r73 = 8.0f;
    L117:
        r62.setImageY(AndroidUtilities.dp(r73));
    L120:
        if (this.transitionProgress != 1.0f) goto L122;
    L148:
        int r74 = r24;
        float r52 = 1.0f;
    L149:
        float r53 = r52 * this.overrideAlpha;
        float r122 = (getSize() / 2.0f) + AndroidUtilities.dp(4.0f);
        if (this.storiesTools != null) goto L152;
        this.storiesTools = new StoriesGradientTools();
    L152:
        this.storiesTools.setBounds(r8, r8, this.parent.getMeasuredHeight(), AndroidUtilities.dp(40.0f));
        this.storiesTools.paint.setAlpha((int) (r53 * 255.0f));
        r113.drawCircle(r62.getCenterX(), r62.getCenterY(), r122, this.storiesTools.paint);
        if (r74 == 0) goto L155;
        r113.restore();
        goto L155
    L122:
        if (DrawingState.m8964$$Nest$fgetanimationType(r5[r22]) != 1) goto L126;
        r113.save();
        float r54 = this.transitionProgress;
        r113.scale(1.0f - r54, 1.0f - r54, r62.getCenterX(), r62.getCenterY());
        r52 = 1.0f - this.transitionProgress;
    L124:
        r74 = 1;
        goto L149
    L126:
        if (DrawingState.m8964$$Nest$fgetanimationType(r5[r22]) != 0) goto L129;
        r113.save();
        float r55 = this.transitionProgress;
        r113.scale(r55, r55, r62.getCenterX(), r62.getCenterY());
        r52 = this.transitionProgress;
        goto L124
    L129:
        if (DrawingState.m8964$$Nest$fgetanimationType(r5[r22]) != r11) goto L140;
        if (this.centered == false) goto L137;
        int r75 = this.width - (r192 * r162);
        if (r13 == false) goto L135;
        float r123 = 8.0f;
    L136:
        int r76 = (r75 - AndroidUtilities.dp(r123)) / r11;
    L138:
        int r56 = r182 + (DrawingState.m8969$$Nest$fgetmoveFromIndex(r5[r22]) * r162);
        float r77 = r76 + (r162 * r22);
        float r124 = this.transitionProgress;
        r62.setImageX((int) ((r77 * r124) + (r56 * (1.0f - r124))));
        goto L148
    L135:
        r123 = 4.0f;
        goto L136
    L137:
        r76 = r172;
        goto L138
    L140:
        if (DrawingState.m8964$$Nest$fgetanimationType(r5[r22]) != r3) goto L148;
        if (this.centered == false) goto L148;
        int r57 = this.width - (r192 * r162);
        if (r13 == false) goto L146;
        float r78 = 8.0f;
    L147:
        int r58 = ((r57 - AndroidUtilities.dp(r78)) / r11) + (r162 * r22);
        float r125 = this.transitionProgress;
        r62.setImageX((int) ((r58 * r125) + ((r182 + r7) * (1.0f - r125))));
        goto L148
    L146:
        r78 = 4.0f;
        goto L147
    L116:
        r73 = 6.0f;
    L118:
        r62.setImageY((this.height - r14) / 2.0f);
        goto L120
    L100:
        if (this.centered == false) goto L106;
        int r79 = this.width - (r192 * r162);
        if (r13 == false) goto L104;
        float r29 = 8.0f;
    L105:
        int r710 = (r79 - AndroidUtilities.dp(r29)) / r11;
    L107:
        r62.setImageX(r710 + (r162 * r22));
        goto L109
    L104:
        r29 = 4.0f;
        goto L105
    L106:
        r710 = r172;
    L155:
        r42 = r42 + 1;
        r3 = -1;
        r10 = 10;
        r12 = 4;
        r15 = 11;
        goto L81
    L91:
        if (this.transitionProgress == 1.0f) goto L95;
        if (DrawingState.m8964$$Nest$fgetanimationType(r5[r22]) == 1) goto L95;
    L88:
        r5 = this.currentStates;
        goto L89
    L84:
        if (this.transitionProgress != 1.0f) goto L86;
    L156:
        r22 = r22 - 1;
        r3 = -1;
        r10 = 10;
        r12 = 4;
        r15 = 11;
    L157:
        int r102 = r11;
    L158:
        if (r102 < 0) goto L321;
        int r126 = r24;
    L160:
        if (r126 >= r11) goto L320;
        if (r126 == 0) goto L163;
    L165:
        if (r126 != 0) goto L168;
        DrawingState[] r23 = this.animatingStates;
    L167:
        DrawingState[] r152 = r23;
        if (r126 == r9) goto L171;
    L175:
        ImageReceiver r25 = DrawingState.m8966$$Nest$fgetimageReceiver(r152[r102]);
        if (r25.hasImageSet() == false) goto L164;
        if (r126 == 0) goto L180;
        r25.setImageX(r182 + (r162 * r102));
    L189:
        int r32 = this.currentStyle;
        if (r32 != 0) goto L192;
    L201:
        char r43 = 11;
    L202:
        r25.setImageY((this.height - r14) / 2.0f);
    L204:
        if (this.transitionProgress != 1.0f) goto L206;
    L223:
        char r59 = 65535;
    L233:
        int r30 = r24;
        float r33 = 1.0f;
    L234:
        float r34 = r33 * this.overrideAlpha;
        if (r102 == (r152.length - r9)) goto L237;
    L240:
        int r63 = this.currentStyle;
        if (r63 != r9) goto L243;
        ImageReceiver r92 = r25;
        float r114 = r34;
        float r39 = r8;
        char r35 = '\n';
    L288:
        r113.drawRoundRect(r92.getCenterX() + AndroidUtilities.dp(13.0f), r92.getCenterY() - AndroidUtilities.dp(13.0f), r92.getCenterX() - AndroidUtilities.dp(13.0f), r92.getCenterY() + AndroidUtilities.dp(13.0f), ExteraConfig.getAvatarCorners(26.0f), ExteraConfig.getAvatarCorners(26.0f), this.xRefP);
        if (DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]) != null) goto L295;
        if (this.currentStyle != 5) goto L293;
        DrawingState.m8979$$Nest$fputwavesDrawable(r152[r102], new GroupCallUserCell.AvatarWavesDrawable(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f)));
        goto L295
    L293:
        DrawingState.m8979$$Nest$fputwavesDrawable(r152[r102], new GroupCallUserCell.AvatarWavesDrawable(AndroidUtilities.dp(17.0f), AndroidUtilities.dp(21.0f)));
    L295:
        if (this.currentStyle != 5) goto L297;
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_voipgroup_speakingText), (int) (r114 * 76.5f)));
    L297:
        DrawingState r26 = r152[r102];
        TLRPC.GroupCallParticipant r36 = r26.participant;
        if (r36 != null) goto L300;
    L302:
        int r510 = 1;
        int r44 = 0;
        DrawingState.m8971$$Nest$fgetwavesDrawable(r26).setShowWaves(false, this.parent);
    L304:
        if (this.currentStyle == 5) goto L306;
    L308:
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).update();
        if (this.currentStyle != 5) goto L311;
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).draw(r113, r92.getCenterX(), r92.getCenterY(), this.parent);
        invalidate();
    L311:
        float r27 = DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).getAvatarScale();
    L312:
        r92.setAlpha(r114);
        if (r27 == 1.0f) goto L315;
        r113.save();
        r113.scale(r27, r27, r92.getCenterX(), r92.getCenterY());
        r92.draw(r113);
        r113.restore();
    L316:
        this.maxX = Math.max(this.maxX, r92.getCenterX() + ((r92.getImageWidth() / 2.0f) * r27));
        if (r30 == 0) goto L319;
        r113.restore();
    L319:
        r126 = r126 + 1;
        r24 = r44;
        r9 = r510;
        r8 = r39;
        r11 = 2;
        goto L160
    L315:
        r92.draw(r113);
        goto L316
    L306:
        if ((SystemClock.uptimeMillis() - r152[r102].participant.lastSpeakTime) <= 500) goto L308;
        this.updateDelegate.run();
        goto L308
    L300:
        if (r36.amplitude <= r39) goto L302;
        r510 = 1;
        DrawingState.m8971$$Nest$fgetwavesDrawable(r26).setShowWaves(true, this.parent);
        DrawingState r28 = r152[r102];
        DrawingState.m8971$$Nest$fgetwavesDrawable(r28).setAmplitude(r28.participant.amplitude * 15.0f);
        r44 = 0;
        goto L304
    L243:
        if (r63 != 3) goto L245;
    L246:
        r92 = r25;
        r114 = r34;
        r39 = r8;
        r35 = '\n';
        goto L288
    L245:
        if (r63 == 5) goto L246;
        if (r63 == 4) goto L252;
        if (r63 == 10) goto L252;
        float r45 = (getSize() / 2.0f) + this.strokeWidth;
        float r64 = r25.getRoundRadius()[r24] + this.strokeWidth;
        if (r20 == false) goto L256;
        float r153 = r25.getCenterX() + r45;
        float r31 = r25.getCenterY() - r45;
        float r322 = r25.getCenterX() - r45;
        float r342 = r25.getCenterY() + r45;
        ImageReceiver r154 = r25;
        r114 = r34;
        r39 = r8;
        r113.drawRoundRect(r153, r31, r322, r342, r64, r64, this.xRefP);
    L262:
        r113 = r41;
        r92 = r154;
        r44 = r24;
        r27 = 1.0f;
        r510 = 1;
        goto L312
    L256:
        r154 = r25;
        r114 = r34;
        r39 = r8;
        int r115 = this.paint.getAlpha();
        if (r114 == 1.0f) goto L259;
        this.paint.setAlpha((int) (r115 * r114));
    L259:
        r41.drawRoundRect(r154.getCenterX() + r45, r154.getCenterY() - r45, r154.getCenterX() - r45, r154.getCenterY() + r45, r64, r64, this.xRefP);
        if (r114 == 1.0f) goto L262;
        this.paint.setAlpha(r115);
    L252:
        r92 = r25;
        r114 = r34;
        r39 = r8;
        r113 = r41;
        r113.drawRoundRect(AndroidUtilities.dp(17.0f) + r92.getCenterX(), r92.getCenterY() - AndroidUtilities.dp(17.0f), r92.getCenterX() - AndroidUtilities.dp(17.0f), AndroidUtilities.dp(17.0f) + r92.getCenterY(), ExteraConfig.getAvatarCorners(34.0f), ExteraConfig.getAvatarCorners(34.0f), this.xRefP);
        if (DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]) != null) goto L268;
        DrawingState.m8979$$Nest$fputwavesDrawable(r152[r102], new GroupCallUserCell.AvatarWavesDrawable(AndroidUtilities.dp(17.0f), AndroidUtilities.dp(21.0f)));
    L268:
        if (this.currentStyle != 10) goto L270;
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_voipgroup_speakingText), (int) (r114 * 76.5f)));
    L271:
        long r210 = System.currentTimeMillis();
        if ((r210 - DrawingState.m8968$$Nest$fgetlastUpdateTime(r152[r102])) <= 100) goto L285;
        DrawingState.m8976$$Nest$fputlastUpdateTime(r152[r102], r210);
        char r37 = '\n';
        if (this.currentStyle != 10) goto L282;
        DrawingState r211 = r152[r102];
        TLRPC.GroupCallParticipant r46 = r211.participant;
        if (r46 != null) goto L278;
    L280:
        DrawingState.m8971$$Nest$fgetwavesDrawable(r211).setShowWaves(r24, this.parent);
    L286:
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).update();
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).draw(r113, r92.getCenterX(), r92.getCenterY(), this.parent);
        r27 = DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).getAvatarScale();
        r44 = 0;
        r510 = 1;
        goto L312
    L278:
        if (r46.amplitude <= r39) goto L280;
        DrawingState.m8971$$Nest$fgetwavesDrawable(r211).setShowWaves(true, this.parent);
        DrawingState r212 = r152[r102];
        DrawingState.m8971$$Nest$fgetwavesDrawable(r212).setAmplitude(r212.participant.amplitude * 15.0f);
        goto L286
    L282:
        if ((ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime() - DrawingState.m8967$$Nest$fgetlastSpeakTime(r152[r102])) > 5) goto L284;
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).setShowWaves(true, this.parent);
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).setAmplitude(this.random.nextInt() % 100);
        goto L286
    L284:
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).setShowWaves(false, this.parent);
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).setAmplitude(0.0d);
        goto L286
    L285:
        r37 = '\n';
        goto L286
    L270:
        DrawingState.m8971$$Nest$fgetwavesDrawable(r152[r102]).setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_voipgroup_listeningText), (int) (r114 * 76.5f)));
        goto L271
    L237:
        if (this.drawStoriesCircle == true) goto L240;
        r154 = r25;
        r114 = r34;
        r39 = r8;
        goto L262
    L206:
        if (DrawingState.m8964$$Nest$fgetanimationType(r152[r102]) != r9) goto L210;
        r113.save();
        float r38 = this.transitionProgress;
        r113.scale(1.0f - r38, 1.0f - r38, r25.getCenterX(), r25.getCenterY());
        r33 = 1.0f - this.transitionProgress;
    L208:
        r30 = r9;
        r59 = 65535;
        goto L234
    L210:
        if (DrawingState.m8964$$Nest$fgetanimationType(r152[r102]) != 0) goto L213;
        r113.save();
        float r310 = this.transitionProgress;
        r113.scale(r310, r310, r25.getCenterX(), r25.getCenterY());
        r33 = this.transitionProgress;
        goto L208
    L213:
        if (DrawingState.m8964$$Nest$fgetanimationType(r152[r102]) == r11) goto L215;
        r59 = 65535;
        if (DrawingState.m8964$$Nest$fgetanimationType(r152[r102]) != (-1)) goto L233;
        if (this.centered == false) goto L233;
        int r311 = this.width - (r192 * r162);
        if (r13 == false) goto L231;
        float r65 = 8.0f;
    L232:
        int r312 = ((r311 - AndroidUtilities.dp(r65)) / r11) + (r162 * r102);
        float r711 = this.transitionProgress;
        r25.setImageX((int) ((r312 * r711) + ((r182 + r6) * (1.0f - r711))));
        goto L233
    L231:
        r65 = 4.0f;
        goto L232
    L215:
        if (this.centered == false) goto L221;
        int r313 = this.width - (r192 * r162);
        if (r13 == false) goto L219;
        float r511 = 8.0f;
    L220:
        int r314 = (r313 - AndroidUtilities.dp(r511)) / r11;
    L222:
        int r315 = r314 + (r162 * r102);
        int r512 = r182 + (DrawingState.m8969$$Nest$fgetmoveFromIndex(r152[r102]) * r162);
        float r66 = this.transitionProgress;
        r25.setImageX((int) ((r315 * r66) + (r512 * (1.0f - r66))));
        goto L223
    L219:
        r511 = 4.0f;
        goto L220
    L221:
        r314 = r172;
        goto L222
    L192:
        if (r32 == 10) goto L201;
        r43 = 11;
        if (r32 == 11) goto L202;
        if (r32 != 4) goto L199;
        float r316 = 8.0f;
    L200:
        r25.setImageY(AndroidUtilities.dp(r316));
        goto L204
    L199:
        r316 = 6.0f;
        goto L200
    L180:
        if (this.centered == false) goto L186;
        int r317 = this.width - (r192 * r162);
        if (r13 == false) goto L184;
        float r47 = 8.0f;
    L185:
        int r318 = (r317 - AndroidUtilities.dp(r47)) / r11;
    L187:
        r25.setImageX(r318 + (r162 * r102));
        goto L189
    L184:
        r47 = 4.0f;
        goto L185
    L186:
        r318 = r172;
    L164:
        r39 = r8;
        r510 = r9;
        r44 = r24;
        goto L319
    L171:
        if (this.transitionProgress == 1.0f) goto L175;
        if (DrawingState.m8964$$Nest$fgetanimationType(r152[r102]) == r9) goto L175;
    L168:
        r23 = this.currentStates;
        goto L167
    L163:
        if (this.transitionProgress != 1.0f) goto L165;
    L320:
        r102 = r102 - 1;
        r11 = 2;
        goto L158
    L321:
        if (r20 == false) goto L340;
        r113.restore();
        return;
    L340:
        return;
    L70:
        r112 = 0.0f;
        goto L72
    L75:
        r113 = r41;
        r11 = 2;
        r8 = 0.0f;
        r24 = 0;
    L65:
        r20 = true;
        goto L67
    L43:
        if (r4 == 3) goto L49;
        Paint r48 = this.paint;
        if (r19 == false) goto L47;
        int r116 = Theme.key_returnToCallMutedBackground;
    L48:
        r48.setColor(Theme.getColor(r116));
        goto L49
    L47:
        r116 = Theme.key_returnToCallBackground;
        goto L48
    L37:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L39;
        r19 = true;
        goto L40
    L33:
        r182 = r172;
    L29:
        r172 = 0;
        goto L31
    L12:
        int r117 = this.overrideSize;
        if (r117 == 0) goto L15;
        r16 = (int) (r117 * this.overrideSizeStepFactor);
        goto L11
    L15:
        if (r13 == false) goto L17;
        float r118 = 24.0f;
    L18:
        r16 = AndroidUtilities.dp(r118);
        goto L11
    L17:
        r118 = 20.0f;
    L7:
        r13 = true;
        goto L8
    }

    public void reset() {
        int r1 = 0;
    L4:
        if (r1 >= this.animatingStates.length) goto L6;
        setObject(0, 0, null);
        r1 = r1 + 1;
        goto L4
    }

    public void setAlpha(float r1) {
        this.overrideAlpha = r1;
    }

    public void setAvatarsTextSize(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= 3) goto L16;
        DrawingState r1 = this.currentStates[r0];
        if (r1 == null) goto L10;
        AvatarDrawable r12 = r1.avatarDrawable;
        if (r12 == null) goto L10;
        r12.setTextSize(r3);
    L10:
        DrawingState r13 = this.animatingStates[r0];
        if (r13 == null) goto L15;
        AvatarDrawable r14 = r13.avatarDrawable;
        if (r14 == null) goto L15;
        r14.setTextSize(r3);
    L15:
        r0 = r0 + 1;
        goto L4
    }

    public void setCentered(boolean r1) {
        this.centered = r1;
    }

    public void setCount(int r1) {
        this.count = r1;
        View r0 = this.parent;
        if (r0 == null) goto L6;
        r0.requestLayout();
        return;
    }

    public void setDelegate(Runnable r1) {
        this.updateDelegate = r1;
    }

    public void setObject(int r24, int r25, TLObject r26) {
        DrawingState.m8973$$Nest$fputid(this.animatingStates[r24], 0);
        DrawingState r3 = this.animatingStates[r24];
        r3.participant = null;
        if (r26 != null) goto L6;
        DrawingState.m8966$$Nest$fgetimageReceiver(r3).setImageBitmap(null);
        invalidate();
        return;
    L6:
        DrawingState.m8975$$Nest$fputlastSpeakTime(r3, -1);
        DrawingState.m8978$$Nest$fputobject(this.animatingStates[r24], r26);
        if ((r26 instanceof TLRPC.GroupCallParticipant) == false) goto L24;
        TLRPC.GroupCallParticipant r32 = (TLRPC.GroupCallParticipant) r26;
        this.animatingStates[r24].participant = r32;
        long r9 = MessageObject.getPeerId(r32.peer);
        if (DialogObject.isUserDialog(r9) == false) goto L11;
        TLRPC.User r11 = MessagesController.getInstance(r25).getUser(Long.valueOf(r9));
        this.animatingStates[r24].avatarDrawable.setInfo(r25, r11);
        TLRPC.Chat r12 = null;
    L13:
        if (this.currentStyle == 4) goto L15;
        DrawingState.m8975$$Nest$fputlastSpeakTime(this.animatingStates[r24], r32.active_date);
    L22:
        DrawingState.m8973$$Nest$fputid(this.animatingStates[r24], r9);
    L36:
        int r1 = getSize();
        if ((r26 instanceof TL_stories.StoryItem) == false) goto L44;
        TL_stories.StoryItem r2 = (TL_stories.StoryItem) r26;
        DrawingState.m8973$$Nest$fputid(this.animatingStates[r24], r2.id);
        TLRPC.MessageMedia r33 = r2.media;
        TLRPC.Document r4 = r33.document;
        if (r4 == null) goto L41;
        TLRPC.PhotoSize r34 = FileLoader.getClosestPhotoSizeWithSize(r4.thumbs, 50, true, null, false);
        TLRPC.PhotoSize r42 = FileLoader.getClosestPhotoSizeWithSize(r2.media.document.thumbs, 50, true, r34, true);
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r24]).setImage(ImageLocation.getForDocument(r42, r2.media.document), r1 + "_" + r1, ImageLocation.getForDocument(r34, r2.media.document), r1 + "_" + r1, 0, null, r2, 0);
    L52:
        float r13 = r1;
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r24]).setRoundRadius(ExteraConfig.getAvatarCorners(r13, true));
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r24]).setImageCoords(0.0f, 0.0f, r13, r13);
        invalidate();
        return;
    L41:
        TLRPC.Photo r35 = r33.photo;
        if (r35 == null) goto L52;
        TLRPC.PhotoSize r36 = FileLoader.getClosestPhotoSizeWithSize(r35.sizes, 50, true, null, false);
        TLRPC.PhotoSize r43 = FileLoader.getClosestPhotoSizeWithSize(r2.media.photo.sizes, 50, true, r36, true);
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r24]).setImage(ImageLocation.getForPhoto(r43, r2.media.photo), r1 + "_" + r1, ImageLocation.getForPhoto(r36, r2.media.photo), r1 + "_" + r1, 0, null, r2, 0);
        goto L52
    L44:
        if (r11 != null) goto L46;
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r24]).setForUserOrChat(r12, this.animatingStates[r24].avatarDrawable);
        goto L52
    L46:
        if (r11.self == true) goto L48;
    L50:
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r24]).setForUserOrChat(r11, this.animatingStates[r24].avatarDrawable);
        goto L52
    L48:
        if (this.showSavedMessages == false) goto L50;
        DrawingState.m8966$$Nest$fgetimageReceiver(this.animatingStates[r24]).setImageBitmap(this.animatingStates[r24].avatarDrawable);
        goto L52
    L15:
        if (r9 != AccountInstance.getInstance(r25).getUserConfig().getClientUserId()) goto L17;
        DrawingState.m8975$$Nest$fputlastSpeakTime(this.animatingStates[r24], 0);
        goto L22
    L17:
        boolean r14 = this.isInCall;
        DrawingState[] r44 = this.animatingStates;
        if (r14 == false) goto L20;
        DrawingState.m8975$$Nest$fputlastSpeakTime(r44[r24], r32.lastActiveDate);
        goto L22
    L20:
        DrawingState.m8975$$Nest$fputlastSpeakTime(r44[r24], r32.active_date);
        goto L22
    L11:
        TLRPC.Chat r112 = MessagesController.getInstance(r25).getChat(Long.valueOf(-r9));
        this.animatingStates[r24].avatarDrawable.setInfo(r25, r112);
        r12 = r112;
        r11 = null;
        goto L13
    L24:
        if ((r26 instanceof TLRPC.User) == false) goto L33;
        r11 = (TLRPC.User) r26;
        if (r11.self == true) goto L28;
    L30:
        this.animatingStates[r24].avatarDrawable.setAvatarType(0);
        this.animatingStates[r24].avatarDrawable.setScaleSize(1.0f);
        this.animatingStates[r24].avatarDrawable.setInfo(r25, r11);
    L31:
        DrawingState.m8973$$Nest$fputid(this.animatingStates[r24], r11.id);
        r12 = null;
        goto L36
    L28:
        if (this.showSavedMessages == false) goto L30;
        this.animatingStates[r24].avatarDrawable.setAvatarType(1);
        this.animatingStates[r24].avatarDrawable.setScaleSize(0.6f);
        goto L31
    L33:
        if ((r26 instanceof TLRPC.Chat) == false) goto L35;
        r12 = (TLRPC.Chat) r26;
        this.animatingStates[r24].avatarDrawable.setAvatarType(0);
        this.animatingStates[r24].avatarDrawable.setScaleSize(1.0f);
        this.animatingStates[r24].avatarDrawable.setInfo(r25, r12);
        DrawingState.m8973$$Nest$fputid(this.animatingStates[r24], -r12.id);
        r11 = null;
        goto L36
    L35:
        r11 = null;
        r12 = null;
        goto L36
    }

    public void setShowSavedMessages(boolean r1) {
        this.showSavedMessages = r1;
    }

    public void setSize(int r1) {
        this.overrideSize = r1;
    }

    public void setStepFactor(float r1) {
        this.overrideSizeStepFactor = r1;
    }

    public void setStyle(int r1) {
        this.currentStyle = r1;
        invalidate();
    }

    public void setTransitionProgress(float r2) {
        if (this.transitionInProgress == true) goto L5;
        return;
    L5:
        if (this.transitionProgress == r2) goto L11;
        this.transitionProgress = r2;
        if (r2 != 1.0f) goto L12;
        swapStates();
        this.transitionInProgress = false;
        return;
    L12:
        return;
    }

    public void updateAfterTransitionEnd() {
        this.updateAfterTransition = true;
    }
}
