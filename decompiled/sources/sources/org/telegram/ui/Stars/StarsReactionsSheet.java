package org.telegram.ui.Stars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.zxing.common.detector.MathUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import me.vkryl.android.animator.BoolAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BatchParticlesDrawHelper;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.HighlightMessageSheet;
import org.telegram.ui.Stories.LiveCommentsView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class StarsReactionsSheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private final BalanceCloud balanceCloud;
    private final ButtonWithCounterView buttonView;
    private ChatActivity chatActivity;
    private final CheckBox2 checkBox;
    private final LinearLayout checkLayout;
    private final View checkSeparatorView;
    private final TextView checkTextView;
    private boolean checkedVisiblity;
    private final ImageView closeView;
    private LiveCommentsView.Message commentMessage;
    private LiveCommentsView.LiveCommentView commentView;
    private LiveCommentsView commentsView;
    private final int currentAccount;
    private final BackupImageView dialogImageView;
    private final ImageView dialogSelectorIconView;
    private final FrameLayout dialogSelectorInnerLayout;
    private final FrameLayout dialogSelectorLayout;
    private final GLIconTextureView icon3dView;
    private ValueAnimator iconAnimator;
    public long lastSelectedPeer;
    private final LinearLayout layout;
    private final boolean liveStories;
    private View messageCell;
    private int messageId;
    private final MessageObject messageObject;
    private Utilities.Callback2Return<Long, Long, Integer> onSendListener;
    public long peer;
    private final ArrayList<TLRPC.MessageReactor> reactors;
    private final Theme.ResourcesProvider resourcesProvider;
    private final boolean sendEnabled;
    private boolean sending;
    private int sentMessageId;
    private final View separatorView;
    private final StarsSlider slider;
    private final ColoredImageSpan[] starRef;
    private final TextView statusView;
    private final TextView titleView;
    private final FrameLayout topLayout;
    private final TopSendersView topSendersView;
    private final LinearLayout toptopLayout;

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends StarsSlider {
        final /* synthetic */ StarsReactionsSheet this$0;
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ boolean val$liveStories;

        public AnonymousClass1(StarsReactionsSheet r1, Context r2, Theme.ResourcesProvider r3, boolean r4, int r5) {
            this.this$0 = r1;
            this.val$liveStories = r4;
            this.val$currentAccount = r5;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stars.StarsReactionsSheet.StarsSlider
        public void onValueChanged(int r7) {
            long r1 = r7;
            this.this$0.updateSenders(r1);
            if (StarsReactionsSheet.m19210$$Nest$fgetbuttonView(this.this$0) == null) goto L6;
            StarsReactionsSheet.m19210$$Nest$fgetbuttonView(this.this$0).setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.StarsReactionSend, new Object[]{LocaleController.formatNumber(r1, ',')}), StarsReactionsSheet.m19216$$Nest$fgetstarRef(this.this$0)), true);
        L6:
            if (this.val$liveStories == false) goto L9;
            StarsReactionsSheet.m19211$$Nest$fgetcommentMessage(this.this$0).stars = r1;
            StarsReactionsSheet.m19212$$Nest$fgetcommentView(this.this$0).set(StarsReactionsSheet.m19211$$Nest$fgetcommentMessage(this.this$0));
            setColor(HighlightMessageSheet.getTierOption(this.val$currentAccount, r7, HighlightMessageSheet.TIER_COLOR1), HighlightMessageSheet.getTierOption(this.val$currentAccount, r7, HighlightMessageSheet.TIER_COLOR2), true);
            return;
        }

        @Override // org.telegram.ui.Stars.StarsReactionsSheet.StarsSlider
        public void setValue(int r4) {
            super.setValue(r4);
            if (this.val$liveStories == false) goto L6;
            setColor(HighlightMessageSheet.getTierOption(this.val$currentAccount, r4, HighlightMessageSheet.TIER_COLOR1), HighlightMessageSheet.getTierOption(this.val$currentAccount, r4, HighlightMessageSheet.TIER_COLOR2), true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends TextView {
        final /* synthetic */ StarsReactionsSheet this$0;

        public AnonymousClass2(StarsReactionsSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(ActionBar.getCurrentActionBarHeight(), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends View {
        private final Paint backgroundPaint;
        private final LinearGradient gradient;
        private final Matrix gradientMatrix;
        private final Paint separatorPaint;
        private final Text text;
        final /* synthetic */ StarsReactionsSheet this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass3(StarsReactionsSheet r9, Context r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            this.val$resourcesProvider = r11;
            super(r10);
            this.gradient = new LinearGradient(0.0f, 0.0f, 255.0f, 0.0f, new int[]{-1135603, -404714}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradientMatrix = new Matrix();
            this.backgroundPaint = new Paint(1);
            this.separatorPaint = new Paint(1);
            this.text = new Text(LocaleController.getString(R.string.StarsReactionTopSenders), 14.16f, AndroidUtilities.bold());
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r21) {
            this.gradientMatrix.reset();
            this.gradientMatrix.postTranslate(AndroidUtilities.dp(14.0f), 0.0f);
            this.gradientMatrix.postScale((getWidth() - AndroidUtilities.dp(28.0f)) / 255.0f, 1.0f);
            this.gradient.setLocalMatrix(this.gradientMatrix);
            this.backgroundPaint.setShader(this.gradient);
            float r1 = this.text.getCurrentWidth() + AndroidUtilities.dp(30.0f);
            this.separatorPaint.setColor(Theme.getColor(Theme.key_divider, this.val$resourcesProvider));
            r21.drawRect(AndroidUtilities.dp(24.0f), (getHeight() / 2.0f) - 1.0f, ((getWidth() - r1) / 2.0f) - AndroidUtilities.dp(8.0f), getHeight() / 2.0f, this.separatorPaint);
            r21.drawRect(((getWidth() + r1) / 2.0f) + AndroidUtilities.dp(8.0f), (getHeight() / 2.0f) - 1.0f, getWidth() - AndroidUtilities.dp(24.0f), getHeight() / 2.0f, this.separatorPaint);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set((getWidth() - r1) / 2.0f, 0.0f, (getWidth() + r1) / 2.0f, getHeight());
            r21.drawRoundRect(r2, getHeight() / 2.0f, getHeight() / 2.0f, this.backgroundPaint);
            this.text.draw(r21, (getWidth() - this.text.getCurrentWidth()) / 2.0f, getHeight() / 2.0f, -1, 1.0f);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends ProfileActivity {
        final /* synthetic */ StarsReactionsSheet this$0;
        final /* synthetic */ boolean val$liveStories;

        public AnonymousClass4(StarsReactionsSheet r1, Bundle r2, boolean r3) {
            this.this$0 = r1;
            this.val$liveStories = r3;
            super(r2);
        }

        @Override // org.telegram.ui.ProfileActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onFragmentDestroy() {
            super.onFragmentDestroy();
            if (this.val$liveStories == true) goto L6;
            this.this$0.show();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends ChatActivity {
        final /* synthetic */ StarsReactionsSheet this$0;
        final /* synthetic */ boolean val$liveStories;

        public AnonymousClass5(StarsReactionsSheet r1, Bundle r2, boolean r3) {
            this.this$0 = r1;
            this.val$liveStories = r3;
            super(r2);
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onFragmentDestroy() {
            super.onFragmentDestroy();
            if (this.val$liveStories == true) goto L6;
            this.this$0.show();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends GLIconTextureView {
        final /* synthetic */ StarsReactionsSheet this$0;

        public AnonymousClass6(StarsReactionsSheet r1, Context r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView
        public void startIdleAnimation() {
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ StarsReactionsSheet this$0;
        final /* synthetic */ ReactionsLayoutInBubble.ReactionButton val$button;
        final /* synthetic */ View val$cell;
        final /* synthetic */ LiveCommentsView.LiveCommentView[] val$commentView;
        final /* synthetic */ boolean[] val$doneRipple;
        final /* synthetic */ Runnable val$pushed;
        final /* synthetic */ RectF val$to;

        public AnonymousClass7(StarsReactionsSheet r1, ReactionsLayoutInBubble.ReactionButton r2, View r3, LiveCommentsView.LiveCommentView[] r4, boolean[] r5, RectF r6, Runnable r7) {
            this.this$0 = r1;
            this.val$button = r2;
            this.val$cell = r3;
            this.val$commentView = r4;
            this.val$doneRipple = r5;
            this.val$to = r6;
            this.val$pushed = r7;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            StarsReactionsSheet.m19214$$Nest$fgeticon3dView(this.this$0).setVisibility(4);
            StarsReactionsSheet.m19214$$Nest$fgeticon3dView(this.this$0).setPaused(true);
            ReactionsLayoutInBubble.ReactionButton r52 = this.val$button;
            if (r52 == null) goto L5;
            r52.drawImage = true;
        L5:
            View r53 = this.val$cell;
            if (r53 == null) goto L8;
            r53.invalidate();
        L8:
            LiveCommentsView.LiveCommentView r54 = this.val$commentView[0];
            if (r54 == null) goto L11;
            r54.setDrawStar(true);
        L11:
            StarsReactionsSheet.access$001(this.this$0);
            boolean[] r55 = this.val$doneRipple;
            if (r55[0] == true) goto L18;
            r55[0] = true;
            LaunchActivity.makeRipple(this.val$to.centerX(), this.val$to.centerY(), 1.5f);
            this.this$0.container.performHapticFeedback(0, 1);     // Catch: Exception -> L24
        L15:
            Runnable r4 = this.val$pushed;
            if (r4 == null) goto L18;
            r4.run();
        L18:
            LaunchActivity r42 = LaunchActivity.instance;
            if (r42 != null) goto L21;
            return;
        L21:
            if (r42.getFireworksOverlay() == null) goto L28;
            LaunchActivity.instance.getFireworksOverlay().start(true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$8, reason: invalid class name */
    public class AnonymousClass8 implements Interpolator {
        final /* synthetic */ StarsReactionsSheet this$0;

        public AnonymousClass8(StarsReactionsSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r3) {
            return (float) Math.pow(r3, 2.0d);
        }
    }

    /* loaded from: classes3.dex */
    public static class Particles {

        /* renamed from: b, reason: collision with root package name */
        public final Bitmap f518b;
        public final Paint bPaint;
        private int bPaintColor;
        private BatchParticlesDrawHelper.BatchParticlesBuffer batchParticlesBuffer;
        private final Paint batchParticlesPaint;
        public final RectF bounds;
        private boolean firstDraw;
        private long lastInvalidateTime;
        private long lastTime;
        private float lifetime;
        public final ArrayList<Particle> particles;
        public final Rect rect;
        private float speed;
        public final int type;
        private int visibleCount;

        public class Particle {

            /* renamed from: a, reason: collision with root package name */
            public float f519a;
            public float la;
            public long lifetime;
            public float s;
            public long start;
            final /* synthetic */ Particles this$0;
            public float vx;
            public float vy;
            public float x;
            public float y;

            public Particle(Particles r1) {
                this.this$0 = r1;
            }

            public void draw(Canvas r7, int r8, float r9) {
                this.this$0.bPaint.setAlpha((int) (255.0f * r9));
                this.this$0.rect.set((int) (this.x - ((((r8.f518b.getWidth() / 2.0f) * this.f519a) * this.s) * r9)), (int) (this.y - ((((this.this$0.f518b.getHeight() / 2.0f) * this.f519a) * this.s) * r9)), (int) (this.x + ((((this.this$0.f518b.getWidth() / 2.0f) * this.f519a) * this.s) * r9)), (int) (this.y + ((((this.this$0.f518b.getHeight() / 2.0f) * this.f519a) * this.s) * r9)));
                Particles r6 = this.this$0;
                r7.drawBitmap(r6.f518b, null, r6.rect, r6.bPaint);
            }
        }

        public Particles(int r7, int r8) {
            this.bounds = new RectF();
            this.bPaint = new Paint(3);
            this.rect = new Rect();
            this.speed = 1.0f;
            this.lifetime = 1.0f;
            this.firstDraw = true;
            this.type = r7;
            this.visibleCount = r8;
            this.particles = new ArrayList(r8);
            int r72 = 0;
        L3:
            if (r72 >= r8) goto L5;
            this.particles.add(new Particle(this));
            r72 = r72 + 1;
            goto L3
        L5:
            int r73 = AndroidUtilities.dp(10.0f);
            Bitmap r0 = Bitmap.createBitmap(r73, r73, Bitmap.Config.ARGB_8888);
            this.f518b = r0;
            Path r1 = new Path();
            float r2 = r73 >> 1;
            int r3 = (int) (0.85f * r2);
            r1.moveTo(0.0f, r2);
            float r5 = r3;
            r1.lineTo(r5, r5);
            r1.lineTo(r2, 0.0f);
            float r32 = r73 - r3;
            r1.lineTo(r32, r5);
            float r74 = r73;
            r1.lineTo(r74, r2);
            r1.lineTo(r32, r32);
            r1.lineTo(r2, r74);
            r1.lineTo(r5, r32);
            r1.lineTo(0.0f, r2);
            r1.close();
            Canvas r75 = new Canvas(r0);
            Paint r22 = new Paint();
            r22.setColor(Theme.multAlpha(-1, 0.75f));
            r75.drawPath(r1, r22);
            if (BatchParticlesDrawHelper.isAvailable() == false) goto L9;
            BatchParticlesDrawHelper.BatchParticlesBuffer r76 = new BatchParticlesDrawHelper.BatchParticlesBuffer(r8);
            this.batchParticlesBuffer = r76;
            r76.fillParticleTextureCords(0.0f, 0.0f, r0.getWidth(), r0.getHeight());
            this.batchParticlesPaint = BatchParticlesDrawHelper.createBatchParticlesPaint(r0);
            return;
        L9:
            this.batchParticlesBuffer = null;
            this.batchParticlesPaint = null;
        }

        public static void insertPoint(PointF[][] r3, float r4, PointF r5) {
            int r0 = (int) Math.floor(r5.x / r4);
            r3[r0][(int) Math.floor(r5.y / r4)] = r5;
        }

        public static boolean isValidPoint(PointF[][] r6, int r7, int r8, float r9, int r10, int r11, PointF r12, float r13) {
            int r0 = AndroidUtilities.dp(15.0f) / 2;
            float r1 = r12.x;
            float r2 = r0;
            if (r1 >= r2) goto L5;
        L23:
            return false;
        L5:
            if (r1 >= (r7 - r0)) goto L23;
            float r72 = r12.y;
            if (r72 < r2) goto L23;
            if (r72 >= (r8 - r0)) goto L23;
            int r73 = (int) Math.floor(r1 / r9);
            int r82 = (int) Math.floor(r12.y / r9);
            int r92 = Math.max(r73 - 1, 0);
            int r74 = Math.min(r73 + 1, r10 - 1);
            int r102 = Math.max(r82 - 1, 0);
            int r83 = Math.min(r82 + 1, r11 - 1);
        L12:
            if (r92 > r74) goto L22;
            int r112 = r102;
        L14:
            if (r112 > r83) goto L21;
            PointF r14 = r6[r92][r112];
            if (r14 == null) goto L20;
            if (MathUtils.distance(r14.x, r14.y, r12.x, r12.y) >= r13) goto L20;
            return false;
        L20:
            r112 = r112 + 1;
            goto L14
        L21:
            r92 = r92 + 1;
            goto L12
        L22:
            return true;
        }

        private static ArrayList<PointF> poissonDiskSampling(float r23, int r24, int r25, int r26) {
            float r7 = r23;
            ArrayList<PointF> r8 = new ArrayList();
            ArrayList r9 = new ArrayList();
            int r10 = 0;
            PointF r0 = new PointF(AndroidUtilities.lerp(0, r24, Utilities.fastRandom.nextFloat()), AndroidUtilities.lerp(0, r25, Utilities.fastRandom.nextFloat()));
            float r3 = (float) Math.floor(r7 / Math.sqrt(2.0d));
            int r11 = 1;
            int r4 = ((int) Math.ceil(r24 / r3)) + 1;
            int r5 = ((int) Math.ceil(r25 / r3)) + 1;
            char r12 = 2;
            PointF[][] r6 = (PointF[][]) Array.newInstance(PointF.class, new int[]{r4, r5});
            int r13 = 0;
        L3:
            if (r13 >= r4) goto L8;
            int r14 = 0;
        L5:
            if (r14 >= r5) goto L7;
            r6[r13][r14] = null;
            r14 = r14 + 1;
            goto L5
        L7:
            r13 = r13 + 1;
            goto L3
        L8:
            insertPoint(r6, r3, r0);
            r8.add(r0);
            r9.add(r0);
        L10:
            if (r9.isEmpty() == true) goto L24;
            if (r9.size() <= r11) goto L14;
            int r132 = Utilities.fastRandom.nextInt(r9.size() - r11);
        L15:
            PointF r142 = (PointF) r9.get(r132);
            int r02 = r10;
        L17:
            if (r02 >= r26) goto L22;
            char r15 = 2;
            int r16 = r02;
            double r03 = AndroidUtilities.lerp(1, 2, Utilities.fastRandom.nextFloat()) * r7;
            double r102 = AndroidUtilities.lerp(r10, 360, Utilities.fastRandom.nextFloat());
            PointF[][] r2 = r6;
            PointF r62 = new PointF((float) (r142.x + (Math.cos(Math.toRadians(r102)) * r03)), (float) (r142.y + (Math.sin(Math.toRadians(r102)) * r03)));
            PointF[][] r04 = r2;
            if (isValidPoint(r04, r24, r25, r3, r4, r5, r62, r7) == true) goto L21;
            r7 = r23;
            r6 = r04;
            r02 = r16 + 1;
            r12 = 2;
            r10 = 0;
            goto L17
        L21:
            r8.add(r62);
            insertPoint(r04, r3, r62);
            r9.add(r62);
        L23:
            r7 = r23;
            r6 = r04;
            r12 = r15;
            r10 = 0;
            r11 = 1;
            goto L10
        L22:
            r04 = r6;
            r15 = r12;
            r9.remove(r132);
            goto L23
        L14:
            r132 = r10;
            goto L15
        L24:
            return r8;
        }

        public void draw(Canvas r2, int r3) {
            draw(r2, r3, 1.0f);
        }

        public void draw(Canvas r17, int r18, float r19) {
            if (LiteMode.isEnabled(131072) == true) goto L5;
            return;
        L5:
            int r3 = Math.min(this.visibleCount, this.particles.size());
            if (this.batchParticlesBuffer == null) goto L12;
            float r4 = this.f518b.getWidth();
            float r6 = this.f518b.getHeight();
            int r8 = 0;
        L8:
            if (r8 >= r3) goto L10;
            Particle r13 = this.particles.get(r8);
            float r7 = (r13.f519a * r13.s) * r19;
            float r10 = (r4 / 2.0f) * r7;
            float r9 = (r6 / 2.0f) * r7;
            BatchParticlesDrawHelper.BatchParticlesBuffer r72 = this.batchParticlesBuffer;
            float r11 = r13.x;
            float r14 = r13.y;
            r72.setParticleVertexCords(r8, r11 - r10, r14 - r9, r11 + r10, r9 + r14);
            this.batchParticlesBuffer.setParticleColor(r8, ColorUtils.setAlphaComponent(r18, (int) (Utilities.clamp01(r13.la * r19) * 255.0f)));
            r8 = r8 + 1;
            goto L8
        L10:
            BatchParticlesDrawHelper.draw(r17, this.batchParticlesBuffer, r3, this.batchParticlesPaint);
        L17:
            this.firstDraw = false;
            return;
        L12:
            if (this.bPaintColor == r18) goto L14;
            Paint r42 = this.bPaint;
            this.bPaintColor = r18;
            r42.setColorFilter(new PorterDuffColorFilter(r18, PorterDuff.Mode.SRC_IN));
        L14:
            int r43 = 0;
        L15:
            if (r43 >= r3) goto L17;
            Particle r62 = this.particles.get(r43);
            r62.draw(r17, r18, r62.la * r19);
            r43 = r43 + 1;
            goto L15
        }

        public void gen(Particle r3, long r4, boolean r6) {
            r3.start = r4;
            long r42 = (long) (AndroidUtilities.lerp(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 2500, Utilities.fastRandom.nextFloat()) * this.lifetime);
            r3.lifetime = r42;
            if (r6 == false) goto L5;
            r3.start -= (long) (r42 * Utilities.clamp01(Utilities.fastRandom.nextFloat()));
        L5:
            RectF r43 = this.bounds;
            r3.x = AndroidUtilities.lerp(r43.left, r43.right, Utilities.fastRandom.nextFloat());
            RectF r44 = this.bounds;
            r3.y = AndroidUtilities.lerp(r44.top, r44.bottom, Utilities.fastRandom.nextFloat());
            if (this.type != 0) goto L8;
            r3.vx = AndroidUtilities.dp(AndroidUtilities.lerp(-7.0f, -18.0f, Utilities.fastRandom.nextFloat()));
            r3.vy = AndroidUtilities.dp(AndroidUtilities.lerp(-2.0f, 2.0f, Utilities.fastRandom.nextFloat()));
        L9:
            r3.f519a = AndroidUtilities.lerp(0.4f, 1.0f, Utilities.fastRandom.nextFloat());
            r3.s = AndroidUtilities.lerp(0.8f, 1.2f, Utilities.fastRandom.nextFloat()) * 0.7f;
            return;
        L8:
            r3.vx = this.bounds.centerX() - r3.x;
            r3.vy = this.bounds.centerY() - r3.y;
            float r2 = AndroidUtilities.dp(AndroidUtilities.lerp(1.0f, 4.0f, Utilities.fastRandom.nextFloat()));
            float r45 = r3.vx;
            float r62 = r3.vy;
            float r22 = r2 / ((float) Math.sqrt((r45 * r45) + (r62 * r62)));
            r3.vx *= r22;
            r3.vy *= r22;
            goto L9
        }

        public void generateGrid() {
            ArrayList<PointF> r0 = poissonDiskSampling(AndroidUtilities.dp(30.0f), (int) this.bounds.width(), (int) this.bounds.height(), 15);
            int r1 = r0.size() - this.particles.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            this.particles.add(new Particle(this));
            r3 = r3 + 1;
            goto L3
        L5:
            int r12 = r0.size();
            this.visibleCount = r12;
            if (this.batchParticlesBuffer == null) goto L8;
            BatchParticlesDrawHelper.BatchParticlesBuffer r32 = new BatchParticlesDrawHelper.BatchParticlesBuffer(r12);
            this.batchParticlesBuffer = r32;
            r32.fillParticleTextureCords(0.0f, 0.0f, this.f518b.getWidth(), this.f518b.getHeight());
        L8:
            long r33 = System.currentTimeMillis();
        L10:
            if (r2 >= this.visibleCount) goto L12;
            Particle r13 = this.particles.get(r2);
            PointF r5 = r0.get(r2);
            gen(r13, r33, true);
            float r6 = r5.x;
            RectF r7 = this.bounds;
            r13.x = r6 + r7.left;
            r13.y = r5.y + r7.top;
            r13.la = AndroidUtilities.lerp(0.4f, 1.0f, Utilities.fastRandom.nextFloat());
            r13.s *= 1.25f;
            r2 = r2 + 1;
            goto L10
        }

        public boolean process() {
            if (LiteMode.isEnabled(131072) == true) goto L5;
            return false;
        L5:
            long r2 = System.currentTimeMillis();
            float r0 = (Math.min(this.lastTime - r2, 16) / 1000.0f) * this.speed;
            int r4 = 0;
        L7:
            if (r4 >= Math.min(this.visibleCount, this.particles.size())) goto L16;
            Particle r5 = this.particles.get(r4);
            long r8 = r5.lifetime;
            if (r8 > 0) goto L11;
            float r6 = 2.0f;
        L13:
            if (r6 <= 1.0f) goto L15;
            gen(r5, r2, this.firstDraw);
            r6 = 0.0f;
        L15:
            r5.x += r5.vx * r0;
            r5.y += r5.vy * r0;
            float r7 = 4.0f * r6;
            r5.la = r7 - (r6 * r7);
            r4 = r4 + 1;
            goto L7
        L11:
            r6 = (r2 - r5.start) / r8;
            goto L13
        L16:
            this.lastTime = r2;
            long r42 = this.lastInvalidateTime;
            if (r42 != 0) goto L19;
        L22:
            this.lastInvalidateTime = r2;
            return true;
        L19:
            if ((r42 - r2) >= 66) goto L22;
            return false;
        }

        public void removeParticlesOutside() {
            if (this.type != 2) goto L11;
            long r0 = System.currentTimeMillis();
            int r2 = 0;
        L6:
            if (r2 >= this.particles.size()) goto L15;
            Particle r3 = this.particles.get(r2);
            if (this.bounds.contains((int) r3.x, (int) r3.y) == true) goto L10;
            gen(r3, r0, this.firstDraw);
        L10:
            r2 = r2 + 1;
            goto L6
        L15:
            return;
        }

        public void setBounds(int r2, int r3, int r4, int r5) {
            this.bounds.set(r2, r3, r4, r5);
            removeParticlesOutside();
        }

        public void setBounds(Rect r2) {
            this.bounds.set(r2);
            removeParticlesOutside();
        }

        public void setBounds(RectF r2) {
            this.bounds.set(r2);
            removeParticlesOutside();
        }

        public void setSpeed(float r1) {
            this.speed = r1;
        }

        public void setVisible(float r2) {
            this.visibleCount = (int) (this.particles.size() * r2);
        }
    }

    public static class SenderData {
        public boolean anonymous;
        public long did;
        public boolean my;
        public long stars;

        public SenderData() {
        }

        public static SenderData of(boolean r1, boolean r2, long r3, long r5) {
            SenderData r0 = new SenderData();
            r0.anonymous = r1;
            r0.my = r2;
            r0.did = r3;
            r0.stars = r5;
            return r0;
        }
    }

    public static class StarsSlider extends View {
        public float aprogress;
        private final RectF arc;
        private final Drawable counterImage;
        private final AnimatedTextView.AnimatedTextDrawable counterSubText;
        private final AnimatedTextView.AnimatedTextDrawable counterText;
        private long currentTop;
        public boolean drawCounterImage;
        public boolean drawPlus;
        private LinearGradient gradient;
        private ValueAnimator gradientAnimator;
        private int gradientColor1;
        private int gradientColor2;
        private final Matrix gradientMatrix;
        private float lastX;
        private float lastY;
        private final AnimatedFloat overTop;
        private final AnimatedFloat overTopText;
        private final Paint plusPaint;
        private final Path plusPath;
        private int pointerId;
        private long pressTime;
        public float progress;
        private ValueAnimator progressAnimator;
        private final Theme.ResourcesProvider resourcesProvider;
        private final Paint sliderCirclePaint;
        private final RectF sliderCircleRect;
        private final Paint sliderInnerPaint;
        private final Path sliderInnerPath;
        private final RectF sliderInnerRect;
        private final Paint sliderPaint;
        private final Particles sliderParticles;
        private final Path sliderPath;
        private final RectF sliderRect;
        private final ColoredImageSpan[] starRef;
        public int steps;
        public int[] stops;
        private final BoolAnimator subTextVisible;
        private final Paint textBackgroundPaint;
        private final Particles textParticles;
        private final Path textPath;
        private final RectF textRect;
        private int toGradientColor1;
        private int toGradientColor2;
        private final Paint topPaint;
        private final Text topText;
        private boolean tracking;

        /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$StarsSlider$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ StarsSlider this$0;
            final /* synthetic */ int val$color1;
            final /* synthetic */ int val$color2;
            final /* synthetic */ int val$fromColor1;
            final /* synthetic */ int val$fromColor2;

            public AnonymousClass1(StarsSlider r1, int r2, int r3, int r4, int r5) {
                this.this$0 = r1;
                this.val$fromColor1 = r2;
                this.val$color1 = r3;
                this.val$fromColor2 = r4;
                this.val$color2 = r5;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r9) {
                StarsSlider.m19223$$Nest$fputgradientColor1(this.this$0, ColorUtils.blendARGB(this.val$fromColor1, this.val$color1, 1.0f));
                StarsSlider.m19224$$Nest$fputgradientColor2(this.this$0, ColorUtils.blendARGB(this.val$fromColor2, this.val$color2, 1.0f));
                StarsSlider.m19222$$Nest$fputgradient(this.this$0, new LinearGradient(0.0f, 0.0f, 255.0f, 0.0f, new int[]{StarsSlider.m19219$$Nest$fgetgradientColor1(this.this$0), StarsSlider.m19220$$Nest$fgetgradientColor2(this.this$0)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarsReactionsSheet$StarsSlider$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ StarsSlider this$0;
            final /* synthetic */ int val$pastValue;
            final /* synthetic */ float val$toProgress;

            public AnonymousClass2(StarsSlider r1, float r2, int r3) {
                this.this$0 = r1;
                this.val$toProgress = r2;
                this.val$pastValue = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                StarsSlider r22 = this.this$0;
                r22.progress = this.val$toProgress;
                if (r22.getValue() == this.val$pastValue) goto L5;
                StarsSlider r23 = this.this$0;
                r23.onValueChanged(r23.getValue());
            L5:
                this.this$0.invalidate();
            }
        }

        /* renamed from: $r8$lambda$NPJvJ-k3XTxhE7dZAO2ewU6pvqc, reason: not valid java name */
        public static /* synthetic */ void m19217$r8$lambda$NPJvJk3XTxhE7dZAO2ewU6pvqc(StarsSlider r0, int r1, int r2, int r3, int r4, ValueAnimator r5) {
            r0.lambda$setColor$0(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$w_aWeZy2aZ5Nwcqcpo9dpDj1Jh4(StarsSlider r0, ValueAnimator r1) {
            r0.lambda$animateProgressTo$1(r1);
        }

        /* renamed from: -$$Nest$fgetcounterImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m19218$$Nest$fgetcounterImage(StarsSlider r0) {
            return r0.counterImage;
        }

        /* renamed from: -$$Nest$fgetgradientColor1, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19219$$Nest$fgetgradientColor1(StarsSlider r0) {
            return r0.gradientColor1;
        }

        /* renamed from: -$$Nest$fgetgradientColor2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19220$$Nest$fgetgradientColor2(StarsSlider r0) {
            return r0.gradientColor2;
        }

        /* renamed from: -$$Nest$fgettracking, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19221$$Nest$fgettracking(StarsSlider r0) {
            return r0.tracking;
        }

        /* renamed from: -$$Nest$fputgradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19222$$Nest$fputgradient(StarsSlider r0, LinearGradient r1) {
            r0.gradient = r1;
        }

        /* renamed from: -$$Nest$fputgradientColor1, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19223$$Nest$fputgradientColor1(StarsSlider r0, int r1) {
            r0.gradientColor1 = r1;
        }

        /* renamed from: -$$Nest$fputgradientColor2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19224$$Nest$fputgradientColor2(StarsSlider r0, int r1) {
            r0.gradientColor2 = r1;
        }

        public StarsSlider(Context r17, Theme.ResourcesProvider r18) {
            super(r17);
            this.sliderInnerPaint = new Paint(1);
            this.sliderPaint = new Paint(1);
            this.plusPaint = new Paint(1);
            this.sliderCirclePaint = new Paint(1);
            this.textBackgroundPaint = new Paint(1);
            this.sliderParticles = new Particles(0, 300);
            this.textParticles = new Particles(2, 30);
            this.gradientColor1 = -1135603;
            this.gradientColor2 = -404714;
            this.toGradientColor1 = -1135603;
            this.toGradientColor2 = -404714;
            this.gradient = new LinearGradient(0.0f, 0.0f, 255.0f, 0.0f, new int[]{this.gradientColor1, this.gradientColor2}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradientMatrix = new Matrix();
            this.drawCounterImage = true;
            AnimatedTextView.AnimatedTextDrawable r8 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.counterText = r8;
            AnimatedTextView.AnimatedTextDrawable r9 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.counterSubText = r9;
            this.starRef = new ColoredImageSpan[1];
            Paint r10 = new Paint(1);
            this.topPaint = r10;
            this.topText = new Text(LocaleController.getString(R.string.StarsReactionTop), 14.0f, AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
            CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.overTop = new AnimatedFloat(this, 0, 320, r6);
            this.overTopText = new AnimatedFloat(this, 0, 320, r6);
            this.currentTop = -1;
            this.sliderInnerRect = new RectF();
            this.sliderRect = new RectF();
            this.sliderCircleRect = new RectF();
            this.arc = new RectF();
            this.sliderInnerPath = new Path();
            this.sliderPath = new Path();
            this.plusPath = new Path();
            this.textRect = new RectF();
            this.textPath = new Path();
            this.progress = 0.0f;
            this.subTextVisible = new BoolAnimator(this, r6, 320);
            this.resourcesProvider = r18;
            Drawable r0 = r17.getResources().getDrawable(R.drawable.msg_premium_liststar).mutate();
            this.counterImage = r0;
            r0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            r8.setTextColor(-1);
            r8.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            r8.setTextSize(AndroidUtilities.dp(21.0f));
            r8.setCallback(this);
            r8.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r8.setGravity(17);
            r9.setTextColor(-570425345);
            r9.setTextSize(AndroidUtilities.dp(11.0f));
            r9.setCallback(this);
            r9.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r9.setGravity(17);
            r10.setColor(Theme.getColor(Theme.key_dialogBackground, r18));
            r10.setStyle(Paint.Style.STROKE);
            r10.setStrokeWidth(AndroidUtilities.dp(1.0f));
        }

        private void animateProgressTo(float r6) {
            ValueAnimator r0 = this.progressAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{this.progress, r6});
            this.progressAnimator = r1;
            r1.addUpdateListener(new StarsReactionsSheet$StarsSlider$$ExternalSyntheticLambda0(this));
            int r12 = getValue();
            this.progressAnimator.addListener(new AnonymousClass2(this, r6, r12));
            this.progressAnimator.setDuration(320);
            this.progressAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.progressAnimator.start();
            if (getValue(r6) == r12) goto L8;
            onValueChanged(getValue(r6));
        L8:
            this.counterText.cancelAnimation();
            this.counterText.setText(StarsIntroActivity.replaceStars(LocaleController.formatNumber(getValue(r6), ','), this.starRef), true);
        }

        private /* synthetic */ void lambda$animateProgressTo$1(ValueAnimator r1) {
            this.progress = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$setColor$0(int r9, int r10, int r11, int r12, ValueAnimator r13) {
            float r132 = ((Float) r13.getAnimatedValue()).floatValue();
            this.gradientColor1 = ColorUtils.blendARGB(r9, r10, r132);
            this.gradientColor2 = ColorUtils.blendARGB(r11, r12, r132);
            this.gradient = new LinearGradient(0.0f, 0.0f, 255.0f, 0.0f, new int[]{this.gradientColor1, this.gradientColor2}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            invalidate();
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r42) {
            Canvas r1 = r42;
            super.dispatchDraw(r42);
            this.gradientMatrix.reset();
            this.gradientMatrix.postTranslate(this.sliderInnerRect.left, 0.0f);
            this.gradientMatrix.postScale(this.sliderInnerRect.width() / 255.0f, 1.0f);
            this.gradient.setLocalMatrix(this.gradientMatrix);
            this.sliderPaint.setShader(this.gradient);
            int r10 = ColorUtils.blendARGB(this.gradientColor1, this.gradientColor2, this.progress);
            this.sliderInnerPath.rewind();
            Path r2 = this.sliderInnerPath;
            RectF r3 = this.sliderInnerRect;
            float r4 = AndroidUtilities.dp(12.0f);
            float r5 = AndroidUtilities.dp(12.0f);
            Path.Direction r12 = Path.Direction.CW;
            r2.addRoundRect(r3, r4, r5, r12);
            this.sliderInnerPaint.setColor(Theme.multAlpha(this.gradientColor1, 0.15f));
            r1.drawPath(this.sliderInnerPath, this.sliderInnerPaint);
            this.sliderRect.set(this.sliderInnerRect);
            float r14 = getProgress(getValue());
            RectF r22 = this.sliderRect;
            r22.right = AndroidUtilities.lerp(r22.left + AndroidUtilities.dp(24.0f), this.sliderRect.right, r14);
            this.sliderPath.rewind();
            this.sliderPath.addRoundRect(this.sliderRect, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), r12);
            this.sliderParticles.setBounds(this.sliderInnerRect);
            this.sliderParticles.setSpeed((this.progress * 15.0f) + 1.0f);
            this.sliderParticles.setVisible((this.progress * 0.85f) + 0.15f);
            this.sliderParticles.process();
            r1.save();
            r1.clipPath(this.sliderInnerPath);
            this.sliderParticles.draw(r1, r10);
            long r23 = this.currentTop;
            if (r23 != (-1)) goto L5;
        L22:
            float r25 = 255.0f;
            int r8 = r10;
            float r26 = 12.0f;
        L23:
            r1.drawPath(this.sliderPath, this.sliderPaint);
            r1.clipPath(this.sliderPath);
            this.sliderParticles.draw(r1, -1);
            long r24 = this.currentTop;
            if (r24 != (-1)) goto L26;
        L43:
            r1.restore();
            invalidate();
            if (this.drawPlus == false) goto L46;
            RectF r27 = this.sliderInnerRect;
            float r52 = r27.right - (r27.height() / 2.0f);
            float r28 = this.sliderInnerRect.centerY();
            this.plusPaint.setColor(ColorUtils.blendARGB(this.sliderInnerPaint.getColor(), this.gradientColor2, 0.5f));
            this.plusPath.rewind();
            float r272 = 0.5f;
            float r282 = 2.0f;
            float r32 = r14;
            float r43 = 0.15f;
            this.plusPath.addRoundRect(r52 - AndroidUtilities.dp(1.0f), r28 - AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.0f) + r52, r28 + AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), r12);
            this.plusPath.addRoundRect(r52 - AndroidUtilities.dp(6.0f), r28 - AndroidUtilities.dp(1.0f), r52 + AndroidUtilities.dp(6.0f), r28 + AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), r12);
            r1.drawPath(this.plusPath, this.plusPaint);
        L47:
            this.sliderCircleRect.set((this.sliderRect.right - AndroidUtilities.dp(16.0f)) - AndroidUtilities.dp(4.0f), this.sliderRect.centerY() - (AndroidUtilities.dp(16.0f) / r282), this.sliderRect.right - AndroidUtilities.dp(4.0f), this.sliderRect.centerY() + (AndroidUtilities.dp(16.0f) / r282));
            r1.drawRoundRect(this.sliderCircleRect, AndroidUtilities.dp(r26), AndroidUtilities.dp(r26), this.sliderCirclePaint);
            float r29 = AndroidUtilities.dp(9.0f) / this.sliderInnerRect.width();
            RectF r53 = this.sliderCircleRect;
            float r210 = AndroidUtilities.lerp(AndroidUtilities.lerp(r53.left, r53.right, r32), AndroidUtilities.lerp(this.sliderCircleRect.left + AndroidUtilities.dp(9.0f), this.sliderCircleRect.right - AndroidUtilities.dp(9.0f), r32), Math.min(Utilities.clamp01(r32 / r29), Utilities.clamp01((1.0f - r32) / r29)));
            float r33 = Math.max(this.counterSubText.getCurrentWidth() + AndroidUtilities.dp(20.0f), this.counterText.getCurrentWidth() + AndroidUtilities.dp(50.0f));
            float r54 = AndroidUtilities.dp(44.0f);
            float r6 = Utilities.clamp(r210 - (r33 / r282), (this.sliderInnerRect.right - r33) - AndroidUtilities.dp(4.0f), this.sliderInnerRect.left + AndroidUtilities.dp(4.0f));
            this.textRect.set(r6, (this.sliderInnerRect.top - AndroidUtilities.dp(21.0f)) - r54, r33 + r6, this.sliderInnerRect.top - AndroidUtilities.dp(21.0f));
            float r34 = this.textRect.height();
            RectF r62 = this.textRect;
            float r211 = Utilities.clamp(r210, r62.right, r62.left);
            RectF r11 = this.textRect;
            float r63 = Utilities.clamp(r211 - AndroidUtilities.dp(9.0f), r11.right, r11.left);
            RectF r122 = this.textRect;
            float r112 = Utilities.clamp(AndroidUtilities.dp(9.0f) + r211, r122.right, r122.left);
            float r123 = Utilities.clamp(this.progress - this.aprogress, 1.0f, -1.0f) * 60.0f;
            float r13 = this.textRect.bottom + AndroidUtilities.dp(8.0f);
            this.textPath.rewind();
            RectF r15 = this.arc;
            float r16 = r43;
            RectF r44 = this.textRect;
            float r9 = r44.left;
            float r45 = r44.top;
            r15.set(r9, r45, r9 + r34, r45 + r34);
            this.textPath.arcTo(this.arc, -180.0f, 90.0f);
            RectF r46 = this.arc;
            RectF r92 = this.textRect;
            float r102 = r92.right;
            float r93 = r92.top;
            r46.set(r102 - r34, r93, r102, r93 + r34);
            this.textPath.arcTo(this.arc, -90.0f, 90.0f);
            RectF r47 = this.arc;
            RectF r7 = this.textRect;
            float r94 = r7.right;
            float r72 = r7.bottom;
            r47.set(r94 - r34, r72 - r34, r94, r72);
            this.textPath.arcTo(this.arc, 0.0f, (float) Utilities.clamp(((Math.acos(Utilities.clamp01((r112 - this.arc.centerX()) / (r34 / r282))) * 0.8500000238418579d) / 3.141592653589793d) * 180.0d, 90.0d, 0.0d));
            RectF r48 = this.textRect;
            float r95 = 0.7f * r34;
            if (r63 >= (r48.right - r95)) goto L50;
            this.textPath.lineTo(r112, r48.bottom);
            this.textPath.lineTo(r211 + r282, this.textRect.bottom + AndroidUtilities.dp(8.0f));
        L50:
            this.textPath.lineTo(r211, (this.textRect.bottom + AndroidUtilities.dp(8.0f)) + 1.0f);
            RectF r49 = this.textRect;
            if (r112 <= (r49.left + r95)) goto L53;
            this.textPath.lineTo(r211 - r282, r49.bottom + AndroidUtilities.dp(8.0f));
            this.textPath.lineTo(r63, this.textRect.bottom);
        L53:
            RectF r410 = this.arc;
            RectF r73 = this.textRect;
            float r96 = r73.left;
            float r74 = r73.bottom;
            r410.set(r96, r74 - r34, r34 + r96, r74);
            float r35 = ((float) Utilities.clamp(((Math.acos(Utilities.clamp01((r63 - this.arc.left) / r5)) * 0.8500000238418579d) / 3.141592653589793d) * 180.0d, 90.0d, 0.0d)) + 90.0f;
            this.textPath.arcTo(this.arc, r35, 180.0f - r35);
            Path r36 = this.textPath;
            RectF r411 = this.textRect;
            r36.lineTo(r411.left, r411.bottom);
            this.textPath.close();
            RectF r37 = AndroidUtilities.rectTmp;
            r37.set(this.textRect);
            r37.inset(-AndroidUtilities.dp(r26), -AndroidUtilities.dp(r26));
            this.textParticles.setBounds(r37);
            this.textParticles.setSpeed((this.progress * 15.0f) + 1.0f);
            this.textParticles.process();
            r1.save();
            this.textParticles.draw(r1, r8);
            r1.restore();
            r1.save();
            r1.rotate(r123, r211, r13);
            if (Math.abs(this.progress - this.aprogress) <= 0.001f) goto L56;
            this.aprogress = AndroidUtilities.lerp(this.aprogress, this.progress, 0.1f);
            invalidate();
        L56:
            this.textBackgroundPaint.setShader(this.gradient);
            r1.drawPath(this.textPath, this.textBackgroundPaint);
            r1.save();
            r1.clipPath(this.textPath);
            r1.rotate(-r123, r211, r13);
            this.textParticles.draw(r1, -1);
            r1.restore();
            r1.save();
            float r97 = 1.0f - (this.subTextVisible.getFloatValue() * r16);
            float r212 = this.textRect.centerX();
            RectF r38 = this.textRect;
            r1.scale(r97, r97, r212, r38.top - (r38.height() * r272));
            this.counterImage.setBounds((int) ((this.textRect.centerX() - (this.counterText.getCurrentWidth() / r282)) + AndroidUtilities.dp(-12.0f)), (int) (this.textRect.centerY() - AndroidUtilities.dp(10.0f)), (int) ((this.textRect.centerX() - (this.counterText.getCurrentWidth() / r282)) + AndroidUtilities.dp(8.0f)), (int) (this.textRect.centerY() + AndroidUtilities.dp(10.0f)));
            if (this.drawCounterImage == false) goto L59;
            this.counterImage.draw(r1);
        L59:
            AnimatedTextView.AnimatedTextDrawable r213 = this.counterText;
            float r39 = this.textRect.left + AndroidUtilities.dp(24.0f);
            RectF r412 = this.textRect;
            r213.setBounds(r39, r412.top, r412.right, r412.bottom);
            this.counterText.draw(r1);
            r1.restore();
            AnimatedTextView.AnimatedTextDrawable r214 = this.counterSubText;
            RectF r310 = this.textRect;
            float r413 = r310.left;
            float r311 = r310.top + AndroidUtilities.dp(10.0f);
            RectF r55 = this.textRect;
            r214.setBounds(r413, r311, r55.right, r55.bottom + AndroidUtilities.dp(10.0f));
            this.counterSubText.setAlpha((int) (this.subTextVisible.getFloatValue() * r25));
            this.counterSubText.draw(r1);
            r1.restore();
            return;
        L46:
            r272 = 0.5f;
            r282 = 2.0f;
            r43 = 0.15f;
            r32 = r14;
            goto L47
        L26:
            if (getProgress((int) r24) >= 1.0f) goto L43;
            if (getProgress((int) this.currentTop) <= 0.0f) goto L43;
            float r215 = (this.sliderInnerRect.left + AndroidUtilities.dp(r26)) + ((this.sliderInnerRect.width() - AndroidUtilities.dp(24.0f)) * Utilities.clamp01(getProgress((int) this.currentTop)));
            AnimatedFloat r312 = this.overTop;
            if (Math.abs((this.sliderRect.right - AndroidUtilities.dp(10.0f)) - r215) >= AndroidUtilities.dp(14.0f)) goto L32;
            boolean r414 = true;
        L33:
            float r313 = r312.set(r414);
            int r415 = AndroidUtilities.dp(9.0f);
            int r56 = AndroidUtilities.dp(16.0f);
            AnimatedFloat r64 = this.overTopText;
            if (Math.abs((this.sliderRect.right - AndroidUtilities.dp(10.0f)) - r215) >= AndroidUtilities.dp(r26)) goto L36;
            boolean r113 = true;
        L37:
            float r416 = AndroidUtilities.lerp(r415, r56, r64.set(r113));
            if (((this.topText.getCurrentWidth() + r215) + (AndroidUtilities.dp(16.0f) * 2)) <= this.sliderInnerRect.right) goto L41;
            float r417 = (r215 - r416) - this.topText.getCurrentWidth();
        L40:
            float r114 = r417;
            this.topPaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
            this.topPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider), 0.4f));
            RectF r418 = this.sliderInnerRect;
            float r419 = AndroidUtilities.lerp(r418.top, r418.centerY(), r313);
            RectF r57 = this.sliderInnerRect;
            r1.drawLine(r215, r419, r215, AndroidUtilities.lerp(r57.bottom, r57.centerY(), r313), this.topPaint);
            this.topText.draw(r42, r114, this.sliderInnerRect.centerY(), -1, 0.75f);
            r1 = r42;
            goto L43
        L41:
            r417 = r416 + r215;
            goto L40
        L36:
            r113 = false;
            goto L37
        L32:
            r414 = false;
            goto L33
        L5:
            if (getProgress((int) r23) >= 1.0f) goto L22;
            if (getProgress((int) this.currentTop) <= 0.0f) goto L22;
            float r216 = (this.sliderInnerRect.left + AndroidUtilities.dp(12.0f)) + ((this.sliderInnerRect.width() - AndroidUtilities.dp(24.0f)) * Utilities.clamp01(getProgress((int) this.currentTop)));
            AnimatedFloat r314 = this.overTop;
            if (Math.abs((this.sliderRect.right - AndroidUtilities.dp(10.0f)) - r216) >= AndroidUtilities.dp(14.0f)) goto L11;
            boolean r420 = true;
        L12:
            float r315 = r314.set(r420);
            int r421 = AndroidUtilities.dp(9.0f);
            int r58 = AndroidUtilities.dp(16.0f);
            AnimatedFloat r65 = this.overTopText;
            r25 = 255.0f;
            r26 = 12.0f;
            if (Math.abs((this.sliderRect.right - AndroidUtilities.dp(10.0f)) - r216) >= AndroidUtilities.dp(12.0f)) goto L15;
            boolean r82 = true;
        L16:
            float r422 = AndroidUtilities.lerp(r421, r58, r65.set(r82));
            if (((this.topText.getCurrentWidth() + r216) + (AndroidUtilities.dp(16.0f) * 2)) <= this.sliderInnerRect.right) goto L20;
            float r423 = (r216 - r422) - this.topText.getCurrentWidth();
        L19:
            float r83 = r423;
            this.topPaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
            this.topPaint.setColor(Theme.multAlpha(r10, 0.6f));
            RectF r424 = this.sliderInnerRect;
            float r425 = AndroidUtilities.lerp(r424.top, r424.centerY(), r315);
            RectF r59 = this.sliderInnerRect;
            r1.drawLine(r216, r425, r216, AndroidUtilities.lerp(r59.bottom, r59.centerY(), r315), this.topPaint);
            this.topText.draw(r42, r83, this.sliderInnerRect.centerY(), r10, 0.6f);
            r1 = r42;
            r8 = r10;
            goto L23
        L20:
            r423 = r422 + r216;
            goto L19
        L15:
            r82 = false;
            goto L16
        L11:
            r420 = false;
            goto L12
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r10) {
            if (r10.getAction() != 0) goto L6;
            this.lastX = r10.getX();
            this.lastY = r10.getY();
            this.pointerId = r10.getPointerId(0);
            this.pressTime = System.currentTimeMillis();
            this.tracking = false;
        L45:
            return true;
        L6:
            if (r10.getAction() != 2) goto L25;
            if (r10.getPointerId(0) != this.pointerId) goto L25;
            float r0 = r10.getX() - this.lastX;
            float r2 = r10.getY() - this.lastY;
            if (this.tracking == true) goto L19;
            if (Math.abs(r0) <= Math.abs(r2 * 1.5f)) goto L19;
            if (Math.abs(r0) <= AndroidUtilities.touchSlop) goto L19;
            getParent().requestDisallowInterceptTouchEvent(true);
            this.tracking = true;
            ValueAnimator r22 = this.progressAnimator;
            if (r22 == null) goto L19;
            r22.cancel();
        L19:
            if (this.tracking == false) goto L45;
            int r23 = getValue();
            this.progress = Utilities.clamp01(this.progress + (r0 / (getWidth() * 1.0f)));
            if (getValue() == r23) goto L23;
            onValueChanged(getValue());
            updateText(true);
        L23:
            this.lastX = r10.getX();
        L25:
            if (r10.getAction() == 1) goto L29;
            if (r10.getAction() != 3) goto L45;
        L29:
            if (this.tracking == false) goto L31;
        L44:
            this.tracking = false;
            goto L45
        L31:
            if (r10.getPointerId(0) != this.pointerId) goto L44;
            if (MathUtils.distance(this.lastX, this.lastY, r10.getX(), r10.getY()) >= AndroidUtilities.touchSlop) goto L44;
            if ((System.currentTimeMillis() - this.pressTime) > (ViewConfiguration.getTapTimeout() * 1.5f)) goto L44;
            if (onTapCustom(r10.getX(), r10.getY()) == true) goto L44;
            float r102 = r10.getX();
            RectF r02 = this.sliderInnerRect;
            float r103 = Utilities.clamp01((r102 - r02.left) / r02.width());
            long r3 = this.currentTop;
            if (r3 > 0) goto L41;
        L43:
            animateProgressTo(r103);
            goto L44
        L41:
            if (Math.abs(getProgress((int) r3) - r103) >= 0.035f) goto L43;
            r103 = Utilities.clamp01(getProgress((int) this.currentTop));
            goto L43
        }

        public float getProgress() {
            return this.progress;
        }

        public float getProgress(int r5) {
            int r1 = 1;
        L3:
            int[] r2 = this.stops;
            if (r1 >= r2.length) goto L10;
            if (r5 <= r2[r1]) goto L7;
            r1 = r1 + 1;
            goto L3
        L7:
            int r12 = r1 - 1;
            int r4 = r2[r12];
            return (r12 + ((r5 - r4) / (r3 - r4))) / (r2.length - 1);
        L10:
            return 1.0f;
        }

        public int getValue() {
            return getValue(this.progress);
        }

        public int getValue(float r6) {
            if (r6 <= 0.0f) goto L5;
            int[] r5 = this.stops;
            if (r6 >= 1.0f) goto L9;
            float r62 = r6 * (r5.length - 1);
            int r0 = (int) r62;
            float r63 = r62 - r0;
            float r2 = r5[r0];
            int r3 = r0 + 1;
            if (r3 >= r5.length) goto L15;
            r0 = r3;
        L15:
            return Math.round(r2 + (r63 * (r5[r0] - r1)));
        L9:
            return r5[r5.length - 1];
        L5:
            return this.stops[0];
        }

        @Override // android.view.View
        public void onMeasure(int r5, int r6) {
            setMeasuredDimension(View.MeasureSpec.getSize(r5), AndroidUtilities.dp(220.0f));
            int r52 = getMeasuredWidth();
            getMeasuredHeight();
            this.sliderInnerRect.set(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(135.0f), r52 - r6, r0 + AndroidUtilities.dp(24.0f));
            this.sliderPaint.setColor(-1069811);
            this.sliderCirclePaint.setColor(-1);
        }

        public boolean onTapCustom(float r1, float r2) {
            return false;
        }

        public void onValueChanged(int r1) {
        }

        public void setColor(int r12, int r13, boolean r14) {
            if (this.toGradientColor1 == r12) goto L5;
        L7:
            ValueAnimator r0 = this.gradientAnimator;
            if (r0 == null) goto L11;
            r0.cancel();
            this.gradientAnimator = null;
        L11:
            if (r14 == false) goto L14;
            int r3 = this.gradientColor1;
            int r5 = this.gradientColor2;
            this.toGradientColor1 = r12;
            this.toGradientColor2 = r13;
            ValueAnimator r142 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.gradientAnimator = r142;
            r142.addUpdateListener(new StarsReactionsSheet$StarsSlider$$ExternalSyntheticLambda1(this, r3, r12, r5, r13));
            this.gradientAnimator.addListener(new AnonymousClass1(this, r3, r12, r5, r13));
            this.gradientAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.gradientAnimator.setDuration(420);
            this.gradientAnimator.start();
            return;
        L14:
            this.toGradientColor1 = r12;
            this.gradientColor1 = r12;
            this.toGradientColor2 = r13;
            this.gradientColor2 = r13;
            this.gradient = new LinearGradient(0.0f, 0.0f, 255.0f, 0.0f, new int[]{this.gradientColor1, this.gradientColor2}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            invalidate();
            return;
        L5:
            if (this.toGradientColor2 != r13) goto L7;
        }

        public void setCounterSubText(String r3, boolean r4) {
            this.subTextVisible.setValue(!TextUtils.isEmpty(r3), r4);
            this.counterSubText.cancelAnimation();
            this.counterSubText.setText(r3, r4);
        }

        public void setStarsTop(long r1) {
            this.currentTop = r1;
            invalidate();
        }

        public void setSteps(int r1, int... r2) {
            this.steps = r1;
            this.stops = r2;
        }

        public void setTopText(String r1) {
            this.topText.setText(r1);
        }

        public void setValue(int r2) {
            setValue(r2, false);
        }

        public void setValue(int r1, boolean r2) {
            float r12 = getProgress(r1);
            this.progress = r12;
            if (r2 == true) goto L5;
            this.aprogress = r12;
        L5:
            updateText(true);
        }

        public void setValueAnimated(int r2) {
            if (r2 != getValue()) goto L5;
            return;
        L5:
            animateProgressTo(getProgress(r2));
        }

        public void updateText(boolean r5) {
            this.counterText.cancelAnimation();
            this.counterText.setText(StarsIntroActivity.replaceStars(LocaleController.formatNumber(getValue(), ','), this.starRef), r5);
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.counterText) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public class TopSendersView extends View {
        public final AnimatedFloat animatedCount;
        public final Paint backgroundPaint;
        private Utilities.Callback<Long> clickListener;
        public float count;
        public final boolean liveStories;
        public final ArrayList<Sender> oldSenders;
        private Sender pressedSender;
        public final ArrayList<Sender> senders;
        final /* synthetic */ StarsReactionsSheet this$0;

        public class Sender {
            public final AnimatedFloat animatedAnonymous;
            public final AnimatedFloat animatedPosition;
            public final AnimatedFloat animatedScale;
            public boolean anonymous;
            public final AvatarDrawable anonymousAvatarDrawable;
            public final AvatarDrawable avatarDrawable;
            public final ButtonBounce bounce;
            public final RectF clickBounds;
            private Drawable crown;
            private Drawable crownOutline;
            private int currentColor;
            public long did;
            public LinearGradient gradient;
            public Matrix gradientMatrix;
            public final ImageReceiver imageReceiver;
            public int index;
            public final boolean my;
            public final Paint paint;
            private int place;
            private Text placeText;
            public Text starsText;
            public Text text;
            final /* synthetic */ TopSendersView this$1;

            public Sender(TopSendersView r10, boolean r11, long r12) {
                this.this$1 = r10;
                this.clickBounds = new RectF();
                CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
                this.animatedPosition = new AnimatedFloat(r10, 0, 600, r8);
                this.animatedScale = new AnimatedFloat(r10, 0, 200, r8);
                this.animatedAnonymous = new AnimatedFloat(r10, 0, 350, r8);
                this.gradient = null;
                this.gradientMatrix = new Matrix();
                this.paint = new Paint(1);
                ImageReceiver r102 = new ImageReceiver(r10);
                this.imageReceiver = r102;
                AvatarDrawable r1 = new AvatarDrawable();
                this.avatarDrawable = r1;
                AvatarDrawable r2 = new AvatarDrawable();
                this.anonymousAvatarDrawable = r2;
                this.bounce = new ButtonBounce(r10);
                this.my = r11;
                this.did = r12;
                if (r12 < 0) goto L5;
                TLRPC.User r112 = MessagesController.getInstance(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(r10.this$0)).getUser(Long.valueOf(r12));
                String r122 = UserObject.getForcedFirstName(r112);
                r1.setInfo(r112);
                r102.setForUserOrChat(r112, r1);
            L10:
                r102.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f));
                r102.onAttachedToWindow();
                r102.setCrossfadeWithOldImage(true);
                r2.setAvatarType(21);
                r2.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f));
                r2.setColor(Theme.getColor(Theme.key_avatar_backgroundGray, StarsReactionsSheet.m19215$$Nest$fgetresourcesProvider(r10.this$0)));
                this.text = new Text(r122, 12.0f);
                return;
            L5:
                TLRPC.Chat r113 = MessagesController.getInstance(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(r10.this$0)).getChat(Long.valueOf(-r12));
                if (r113 != null) goto L8;
                r122 = _UrlKt.FRAGMENT_ENCODE_SET;
            L9:
                r1.setInfo(r113);
                r102.setForUserOrChat(r113, r1);
                goto L10
            L8:
                r122 = r113.title;
                goto L9
            }

            private long getPrivacy() {
                if (this.anonymous == false) goto L7;
                return UserObject.ANONYMOUS;
            L7:
                if (this.did != UserConfig.getInstance(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(this.this$1.this$0)).getClientUserId()) goto L11;
                return 0;
            L11:
                return this.did;
            }

            public void draw(Canvas r19) {
                float r1 = this.animatedPosition.set(this.index);
                AnimatedFloat r3 = this.animatedScale;
                int r4 = this.index;
                if (r4 >= 0) goto L5;
            L7:
                boolean r42 = false;
            L8:
                float r6 = r3.set(r42);
                r19.save();
                float r7 = (this.this$1.getWidth() - AndroidUtilities.dp(80.0f)) / Math.max(1.0f, this.this$1.count);
                float r9 = AndroidUtilities.dp(40.0f) + ((this.this$1.count - (r1 + 0.5f)) * r7);
                float r10 = AndroidUtilities.dp(40.0f);
                float r32 = r7 / 2.0f;
                this.clickBounds.set(r9 - r32, r10 - AndroidUtilities.dp(50.0f), r32 + r9, AndroidUtilities.dp(50.0f) + r10);
                float r12 = (0.3f * r6) + 0.7f;
                r19.scale(r12, r12, r9, r10);
                float r13 = this.bounce.getScale(0.04f);
                r19.scale(r13, r13, r9, r10);
                if (r6 <= 0.0f) goto L17;
                float r33 = this.animatedAnonymous.set(this.anonymous);
                if (r33 >= 1.0f) goto L13;
                float r16 = 255.0f;
                float r17 = 40.0f;
                this.imageReceiver.setImageCoords(r9 - (AndroidUtilities.dp(56.0f) / 2.0f), r10 - (AndroidUtilities.dp(56.0f) / 2.0f), AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
                this.imageReceiver.setAlpha(r6);
                this.imageReceiver.draw(r19);
                this.imageReceiver.setAlpha(1.0f);
            L15:
                if (r33 <= 0.0f) goto L18;
                int r5 = (int) r9;
                int r122 = (int) r10;
                this.anonymousAvatarDrawable.setBounds(r5 - (AndroidUtilities.dp(56.0f) / 2), r122 - (AndroidUtilities.dp(56.0f) / 2), r5 + (AndroidUtilities.dp(56.0f) / 2), r122 + (AndroidUtilities.dp(56.0f) / 2));
                this.anonymousAvatarDrawable.setAlpha((int) ((r6 * r16) * r33));
                this.anonymousAvatarDrawable.draw(r19);
                this.anonymousAvatarDrawable.setAlpha(255);
            L18:
                RectF r34 = AndroidUtilities.rectTmp;
                r34.set((r9 - (this.starsText.getCurrentWidth() / 2.0f)) - AndroidUtilities.dp(5.66f), (AndroidUtilities.dp(23.0f) + r10) - (AndroidUtilities.dp(16.0f) / 2.0f), ((this.starsText.getCurrentWidth() / 2.0f) + r9) + AndroidUtilities.dp(5.66f), (AndroidUtilities.dp(23.0f) + r10) + (AndroidUtilities.dp(16.0f) / 2.0f));
                r19.drawRoundRect(r34, r34.height() / 2.0f, r34.height() / 2.0f, this.this$1.backgroundPaint);
                int r123 = (int) (r6 * r16);
                this.paint.setAlpha(r123);
                if (this.gradient == null) goto L21;
                this.gradientMatrix.reset();
                this.gradientMatrix.postTranslate(0.0f, r34.top);
                this.gradient.setLocalMatrix(this.gradientMatrix);
            L21:
                r19.drawRoundRect(r34, r34.height() / 2.0f, r34.height() / 2.0f, this.paint);
                Text r14 = this.starsText;
                r14.draw(r19, r9 - (r14.getCurrentWidth() / 2.0f), AndroidUtilities.dp(23.0f) + r10, -1, r6);
                this.text.ellipsize(r7 - AndroidUtilities.dp(4.0f)).draw(r19, r9 - (this.text.getWidth() / 2.0f), r10 + AndroidUtilities.dp(42.0f), Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, StarsReactionsSheet.m19215$$Nest$fgetresourcesProvider(this.this$1.this$0)), r6);
                if (this.place <= 0) goto L24;
                int r35 = (int) r9;
                int r72 = (int) r10;
                this.crownOutline.setBounds(r35 - AndroidUtilities.dp(12.0f), r72 - AndroidUtilities.dp(r17), AndroidUtilities.dp(12.0f) + r35, r72 - AndroidUtilities.dp(16.0f));
                this.crown.setBounds(r35 - AndroidUtilities.dp(12.0f), r72 - AndroidUtilities.dp(r17), r35 + AndroidUtilities.dp(12.0f), r72 - AndroidUtilities.dp(16.0f));
                this.crownOutline.setAlpha(r123);
                this.crown.setAlpha(r123);
                this.crownOutline.draw(r19);
                this.crown.draw(r19);
                Text r0 = this.placeText;
                r0.draw(r19, r9 - (r0.getCurrentWidth() / 2.0f), r10 - AndroidUtilities.dp(27.0f), -1, r6);
            L24:
                r19.restore();
                return;
            L13:
                r17 = 40.0f;
                r16 = 255.0f;
                goto L15
            L17:
                r17 = 40.0f;
                r16 = 255.0f;
                goto L18
            L5:
                if (r4 >= this.this$1.senders.size()) goto L7;
                r42 = true;
                goto L8
            }

            public void setAnonymous(boolean r3) {
                if (this.my == false) goto L6;
                return;
            L6:
                if (this.anonymous == r3) goto L13;
                this.anonymous = r3;
                if (r3 == false) goto L10;
                String r32 = LocaleController.getString(R.string.StarsReactionAnonymous);
            L11:
                this.text = new Text(r32, 12.0f);
                this.this$1.invalidate();
                return;
            L10:
                r32 = DialogObject.getShortName(this.did);
                goto L11
            }

            public void setPlace(int r5) {
                this.place = r5;
                this.placeText = new Text(_UrlKt.FRAGMENT_ENCODE_SET + r5, 10.0f, AndroidUtilities.getTypeface("fonts/num.otf"));
                if (r5 > 0) goto L5;
                return;
            L5:
                if (this.crown != null) goto L9;
                Drawable r52 = this.this$1.getContext().getResources().getDrawable(R.drawable.filled_stream_crown).mutate();
                this.crown = r52;
                int r1 = this.currentColor;
                PorterDuff.Mode r2 = PorterDuff.Mode.SRC_IN;
                r52.setColorFilter(new PorterDuffColorFilter(r1, r2));
                Drawable r53 = this.this$1.getContext().getResources().getDrawable(R.drawable.filled_stream_crown_outline).mutate();
                this.crownOutline = r53;
                r53.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground, StarsReactionsSheet.m19215$$Nest$fgetresourcesProvider(this.this$1.this$0)), r2));
                return;
            }

            public void setPrivacy(long r5) {
                if (this.my == true) goto L6;
                return;
            L6:
                if (getPrivacy() != r5) goto L8;
                return;
            L8:
                if (r5 != UserObject.ANONYMOUS) goto L10;
                boolean r1 = true;
            L11:
                this.anonymous = r1;
                if (r5 == 0) goto L14;
                if (r5 == UserObject.ANONYMOUS) goto L14;
            L15:
                this.did = r5;
                if (this.anonymous == false) goto L18;
                String r52 = LocaleController.getString(R.string.StarsReactionAnonymous);
            L27:
                this.text = new Text(r52, 12.0f);
                this.this$1.invalidate();
                return;
            L18:
                TopSendersView r6 = this.this$1;
                if (r5 < 0) goto L22;
                TLRPC.User r53 = MessagesController.getInstance(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(r6.this$0)).getUser(Long.valueOf(this.did));
                String r62 = UserObject.getForcedFirstName(r53);
                this.avatarDrawable.setInfo(r53);
                this.imageReceiver.setForUserOrChat(r53, this.avatarDrawable);
            L21:
                r52 = r62;
                goto L27
            L22:
                TLRPC.Chat r54 = MessagesController.getInstance(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(r6.this$0)).getChat(Long.valueOf(-this.did));
                if (r54 != null) goto L25;
                r62 = _UrlKt.FRAGMENT_ENCODE_SET;
            L26:
                this.avatarDrawable.setInfo(r54);
                this.imageReceiver.setForUserOrChat(r54, this.avatarDrawable);
                goto L21
            L25:
                r62 = r54.title;
            L14:
                r5 = UserConfig.getInstance(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(this.this$1.this$0)).getClientUserId();
                goto L15
            L10:
                r1 = false;
                goto L11
            }

            public void setStars(long r10) {
                this.starsText = new Text(StarsIntroActivity.replaceStars("⭐️" + LocaleController.formatNumber(r10, ','), 0.85f), 12.0f, AndroidUtilities.getTypeface("fonts/num.otf"));
                if (this.this$1.liveStories == false) goto L5;
                int r102 = (int) r10;
                this.gradient = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(16.0f), new int[]{HighlightMessageSheet.getTierOption(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(this.this$1.this$0), r102, HighlightMessageSheet.TIER_COLOR2), HighlightMessageSheet.getTierOption(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(this.this$1.this$0), r102, HighlightMessageSheet.TIER_COLOR1)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
                this.currentColor = ColorUtils.blendARGB(HighlightMessageSheet.getTierOption(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(this.this$1.this$0), r102, HighlightMessageSheet.TIER_COLOR2), HighlightMessageSheet.getTierOption(StarsReactionsSheet.m19213$$Nest$fgetcurrentAccount(this.this$1.this$0), r102, HighlightMessageSheet.TIER_COLOR1), 0.5f);
                this.paint.setShader(this.gradient);
            L6:
                Drawable r103 = this.crown;
                if (r103 == null) goto L10;
                r103.setColorFilter(new PorterDuffColorFilter(this.currentColor, PorterDuff.Mode.SRC_IN));
                return;
            L10:
                return;
            L5:
                this.paint.setShader(null);
                Paint r104 = this.paint;
                this.currentColor = -1002750;
                r104.setColor(-1002750);
                goto L6
            }
        }

        public TopSendersView(StarsReactionsSheet r9, Context r10, boolean r11) {
            this.this$0 = r9;
            super(r10);
            this.senders = new ArrayList();
            this.oldSenders = new ArrayList();
            Paint r102 = new Paint(1);
            this.backgroundPaint = r102;
            this.animatedCount = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.liveStories = r11;
            r102.setStyle(Paint.Style.FILL_AND_STROKE);
            r102.setStrokeWidth(AndroidUtilities.dp(3.0f));
            r102.setColor(Theme.getColor(Theme.key_dialogBackground, StarsReactionsSheet.m19215$$Nest$fgetresourcesProvider(r9)));
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r4) {
            this.count = this.animatedCount.set(this.senders.size());
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r1 >= this.oldSenders.size()) goto L7;
            this.oldSenders.get(r1).draw(r4);
            r1 = r1 + 1;
        L7:
            if (r0 >= this.senders.size()) goto L9;
            this.senders.get(r0).draw(r4);
            r0 = r0 + 1;
            goto L7
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            int r0 = 0;
        L4:
            if (r0 >= this.senders.size()) goto L6;
            this.senders.get(r0).imageReceiver.onAttachedToWindow();
            r0 = r0 + 1;
            goto L4
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            int r0 = 0;
        L4:
            if (r0 >= this.senders.size()) goto L6;
            this.senders.get(r0).imageReceiver.onDetachedFromWindow();
            r0 = r0 + 1;
            goto L4
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            if (r7.getAction() != 0) goto L18;
            Sender r0 = this.pressedSender;
            if (r0 == null) goto L7;
            r0.bounce.setPressed(false);
        L7:
            this.pressedSender = null;
            int r02 = 0;
        L9:
            if (r02 >= this.senders.size()) goto L14;
            if (this.senders.get(r02).clickBounds.contains(r7.getX(), r7.getY()) == true) goto L12;
            r02 = r02 + 1;
            goto L9
        L12:
            this.pressedSender = this.senders.get(r02);
        L14:
            Sender r72 = this.pressedSender;
            if (r72 == null) goto L37;
            r72.bounce.setPressed(true);
        L37:
            if (this.pressedSender == null) goto L39;
            return true;
        L39:
            return false;
        L18:
            if (r7.getAction() == 1) goto L22;
            if (r7.getAction() != 3) goto L37;
        L22:
            if (r7.getAction() != 1) goto L32;
            Sender r03 = this.pressedSender;
            if (r03 == null) goto L32;
            if (r03.anonymous == true) goto L32;
            if (r03.clickBounds.contains(r7.getX(), r7.getY()) == false) goto L32;
            Utilities.Callback<Long> r73 = this.clickListener;
            if (r73 == null) goto L32;
            r73.run(Long.valueOf(this.pressedSender.did));
        L32:
            Sender r74 = this.pressedSender;
            if (r74 == null) goto L35;
            r74.bounce.setPressed(false);
        L35:
            this.pressedSender = null;
            goto L37
        }

        public void setMyPrivacy(long r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.senders.size()) goto L10;
            Sender r1 = this.senders.get(r0);
            if (r1.my == true) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            r1.setPrivacy(r4);
            return;
        }

        public void setOnSenderClickListener(Utilities.Callback<Long> r1) {
            this.clickListener = r1;
        }

        public void setSenders(ArrayList<SenderData> r12) {
            int r1 = 0;
        L3:
            SenderData r3 = null;
            if (r1 >= this.senders.size()) goto L22;
            Sender r2 = this.senders.get(r1);
            int r5 = 0;
        L7:
            if (r5 >= r12.size()) goto L19;
            SenderData r6 = r12.get(r5);
            boolean r7 = r6.my;
            if (r7 == false) goto L13;
            if (r2.my == false) goto L13;
        L17:
            r3 = r12.get(r5);
        L13:
            if (r2.my == true) goto L18;
            if (r7 == true) goto L18;
            if (r6.did == r2.did) goto L17;
        L18:
            r5 = r5 + 1;
        L19:
            if (r3 != null) goto L21;
            r2.imageReceiver.onDetachedFromWindow();
            this.senders.remove(r1);
            r1 = r1 - 1;
            r2.index = -1;
            this.oldSenders.add(r2);
        L21:
            r1 = r1 + 1;
            goto L3
        L22:
            int r13 = 0;
        L24:
            if (r13 >= r12.size()) goto L67;
            SenderData r22 = r12.get(r13);
            int r52 = 0;
        L27:
            if (r52 >= this.senders.size()) goto L39;
            Sender r62 = this.senders.get(r52);
            boolean r72 = r62.my;
            if (r72 == true) goto L31;
        L32:
            if (r72 == true) goto L38;
            if (r22.my == true) goto L38;
            if (r62.did != r22.did) goto L38;
        L37:
            Sender r53 = this.senders.get(r52);
        L40:
            if (r53 != null) goto L57;
            int r63 = 0;
        L43:
            if (r63 >= this.oldSenders.size()) goto L55;
            Sender r73 = this.oldSenders.get(r63);
            boolean r8 = r73.my;
            if (r8 == true) goto L47;
        L48:
            if (r8 == true) goto L54;
            if (r22.my == true) goto L54;
            if (r73.did != r22.did) goto L54;
        L53:
            r53 = this.oldSenders.get(r63);
        L54:
            r63 = r63 + 1;
            goto L43
        L47:
            if (r22.my == true) goto L53;
        L55:
            if (r53 == null) goto L57;
            this.oldSenders.remove(r53);
            r53.imageReceiver.onAttachedToWindow();
            this.senders.add(r53);
        L57:
            if (r53 != null) goto L59;
            r53 = new Sender(this, r22.my, r22.did);
            r53.animatedScale.set(0.0f, true);
            this.senders.add(r53);
            r53.animatedPosition.set((r12.size() - 1) - r13, true);
        L59:
            r53.index = (r12.size() - 1) - r13;
            r53.setStars(r22.stars);
            if (this.liveStories == false) goto L63;
            r53.setPlace(r13 + 1);
        L63:
            if (r22.my == false) goto L65;
            r53.setPrivacy(this.this$0.peer);
        L66:
            r13 = r13 + 1;
            goto L24
        L65:
            r53.setAnonymous(r22.anonymous);
        L38:
            r52 = r52 + 1;
            goto L27
        L31:
            if (r22.my == true) goto L37;
        L39:
            r53 = null;
            goto L40
        L67:
            invalidate();
        }
    }

    /* renamed from: $r8$lambda$-Wnz5cVZaGNCGeUorEqGPlV--9k, reason: not valid java name */
    public static /* synthetic */ void m19206$r8$lambda$Wnz5cVZaGNCGeUorEqGPlV9k(StarsReactionsSheet r0) {
        r0.lambda$animate3dIcon$13();
    }

    public static /* synthetic */ void $r8$lambda$ATls_R5i7g0DtbTDTps6fHzwTnU(StarsReactionsSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkVisibility$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AXgC4XPSeASc7uVBl7CEUay0lrs(StarsReactionsSheet r0, long r1, StarsController r3, MessageObject r4, ChatActivity r5) {
        r0.lambda$new$6(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$G9VSdNyqeAr6vEJ5DT7yE123v_A(StarsReactionsSheet r0, long r1, boolean r3) {
        r0.lambda$new$8(r1, r3);
    }

    /* renamed from: $r8$lambda$ICYp_Lfaz3Se5WBvd2A6_Lfst-4, reason: not valid java name */
    public static /* synthetic */ void m19207$r8$lambda$ICYp_Lfaz3Se5WBvd2A6_Lfst4(Context r0, Theme.ResourcesProvider r1, View r2) {
        new StarsIntroActivity.StarsOptionsSheet(r0, r1).show();
    }

    public static /* synthetic */ void $r8$lambda$ShexdI2hz0YejEWI3B6pZnVGYOg(StarsReactionsSheet r0, LiveCommentsView.LiveCommentView[] r1, int[] r2, RectF r3, View r4, ReactionsLayoutInBubble r5, ReactionsLayoutInBubble.ReactionButton r6) {
        r0.lambda$animate3dIcon$14(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$UtzgtX5eMGozy2DjnlGp3V0Dr9I(StarsReactionsSheet r0, StarsController.PendingPaidReactions r1) {
        r0.lambda$new$5(r1);
    }

    /* renamed from: $r8$lambda$Wym6ZqDjKHzRuxJ8-Grh9hYXMX4, reason: not valid java name */
    public static /* synthetic */ void m19208$r8$lambda$Wym6ZqDjKHzRuxJ8Grh9hYXMX4(StarsReactionsSheet r0, int r1, Theme.ResourcesProvider r2, long r3, boolean r5, View r6) {
        r0.lambda$new$9(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$YCMx_CF9F5oX6IYX3Tba6VEBHTw(StarsReactionsSheet r0, MessageObject r1, ChatActivity r2, int r3, boolean r4, Context r5, Theme.ResourcesProvider r6, long r7, TLRPC.Chat r9, View r10) {
        r0.lambda$new$7(r1, r2, r3, r4, r5, r6, r7, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$ZNb52E6wufesnPKXtMJQ3yWk1AY(StarsReactionsSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$ZiAMJObfV8Er-X2-rEVyMZ8I2MQ, reason: not valid java name */
    public static /* synthetic */ void m19209$r8$lambda$ZiAMJObfV8ErX2rEVyMZ8I2MQ(StarsReactionsSheet r0, int r1, boolean r2, Long r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$bCH_sQJJecgHsNrSQOp5eQYI_k8(SenderData r2, SenderData r3) {
        return (int) (r3.stars - r2.stars);
    }

    public static /* synthetic */ void $r8$lambda$gpINQbjFcZAgwazjQFm52nwhwQg(StarsReactionsSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$p5mXBWpWHJQlmNcGHp04AsTx7PA(StarsReactionsSheet r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ void $r8$lambda$rLFyN0GAmvRKSLKpMRi2UsC_SOE(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.StarsReactionTermsLink));
    }

    public static /* synthetic */ void $r8$lambda$yicMMkOo6MS7LXxBwvdidy5Was4(StarsReactionsSheet r0, Runnable r1, RectF r2, RectF r3, RectF r4, boolean[] r5, Runnable r6, ValueAnimator r7) {
        r0.lambda$animate3dIcon$15(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: -$$Nest$fgetbuttonView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonWithCounterView m19210$$Nest$fgetbuttonView(StarsReactionsSheet r0) {
        return r0.buttonView;
    }

    /* renamed from: -$$Nest$fgetcommentMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LiveCommentsView.Message m19211$$Nest$fgetcommentMessage(StarsReactionsSheet r0) {
        return r0.commentMessage;
    }

    /* renamed from: -$$Nest$fgetcommentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LiveCommentsView.LiveCommentView m19212$$Nest$fgetcommentView(StarsReactionsSheet r0) {
        return r0.commentView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19213$$Nest$fgetcurrentAccount(StarsReactionsSheet r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgeticon3dView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GLIconTextureView m19214$$Nest$fgeticon3dView(StarsReactionsSheet r0) {
        return r0.icon3dView;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m19215$$Nest$fgetresourcesProvider(StarsReactionsSheet r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetstarRef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColoredImageSpan[] m19216$$Nest$fgetstarRef(StarsReactionsSheet r0) {
        return r0.starRef;
    }

    public StarsReactionsSheet(Context r36, int r37, long r38, ChatActivity r40, MessageObject r41, ArrayList<TLRPC.MessageReactor> r42, boolean r43, boolean r44, long r45, Theme.ResourcesProvider r47) {
        super(r36, false, r47);
        this.starRef = new ColoredImageSpan[1];
        this.checkedVisiblity = false;
        this.resourcesProvider = r47;
        this.currentAccount = r37;
        this.messageObject = r41;
        this.reactors = r42;
        this.liveStories = r44;
        this.sendEnabled = r43;
        BalanceCloud r0 = new BalanceCloud(r36, r37, r47);
        this.balanceCloud = r0;
        r0.setScaleX(0.6f);
        r0.setScaleY(0.6f);
        r0.setAlpha(0.0f);
        this.container.addView(r0, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 48.0f, 0.0f, 0.0f));
        ScaleStateListAnimator.apply(r0);
        r0.setOnClickListener(new StarsReactionsSheet$$ExternalSyntheticLambda1(r36, r47));
        long r9 = UserConfig.getInstance(r37).getClientUserId();
        if (r42 == null) goto L18;
        int r02 = r42.size();
        int r8 = 0;
        TLRPC.MessageReactor r2 = null;
    L5:
        if (r8 >= r02) goto L17;
        TLRPC.MessageReactor r17 = r42.get(r8);
        r8 = r8 + 1;
        TLRPC.MessageReactor r15 = r17;
        long r19 = DialogObject.getPeerDialogId(r15.peer_id);
        if (r15.anonymous == false) goto L12;
        if (r15.my == false) goto L12;
        r19 = r9;
    L12:
        if (r15.my == false) goto L14;
    L15:
        r2 = r15;
        goto L5
    L14:
        if (r19 != r9) goto L5;
    L17:
        TLRPC.MessageReactor r13 = r2;
    L19:
        if (r42 != null) goto L21;
    L23:
        boolean r82 = false;
    L24:
        if (r44 == false) goto L35;
        if (r42 == null) goto L34;
        int r03 = 0;
    L28:
        if (r03 >= r42.size()) goto L34;
        if (r42.get(r03).my == true) goto L31;
        r03 = r03 + 1;
        goto L28
    L31:
        TLRPC.MessageReactor r04 = r42.get(r03);
    L34:
        this.peer = r45;
    L36:
        long r14 = this.peer;
        if (r14 == UserObject.ANONYMOUS) goto L40;
        r9 = r14;
    L40:
        this.lastSelectedPeer = r9;
        fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r47));
        LinearLayout r05 = new LinearLayout(r36);
        this.layout = r05;
        r05.setOrientation(1);
        FrameLayout r22 = new FrameLayout(r36);
        this.topLayout = r22;
        r05.addView(r22, LayoutHelper.createLinear(-1, -2));
        this.slider = new AnonymousClass1(this, r36, r47, r44, r37);
        int r06 = 9;
        int[] r23 = {1, 50, 100, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000, CastStatusCodes.AUTHENTICATION_FAILED, 5000, 7500, 10000};
        long r142 = MessagesController.getInstance(r37).starsPaidReactionAmountMax;
        ArrayList r92 = new ArrayList();
        int r10 = 0;
    L41:
        if (r10 >= r06) goto L49;
        int r07 = r23[r10];
        boolean r222 = r82;
        if (r07 > r142) goto L44;
        r92.add(Integer.valueOf(r07));
        if (r23[r10] == r142) goto L50;
        r10 = r10 + 1;
        r82 = r222;
        r06 = 9;
    L50:
        int[] r08 = new int[r92.size()];
        int r24 = 0;
    L52:
        if (r24 >= r92.size()) goto L54;
        r08[r24] = ((Integer) r92.get(r24)).intValue();
        r24 = r24 + 1;
        goto L52
    L54:
        this.slider.setSteps(100, r08);
        if (r43 == true) goto L57;
        if (r44 == true) goto L57;
    L68:
        LinearLayout r7 = new LinearLayout(r36);
        this.toptopLayout = r7;
        r7.setOrientation(0);
        if (r44 == true) goto L71;
        this.topLayout.addView(r7, LayoutHelper.createFrame(-1, -2.0f, 55, 0.0f, 0.0f, 0.0f, 0.0f));
    L71:
        FrameLayout r143 = new FrameLayout(r36);
        this.dialogSelectorLayout = r143;
        FrameLayout r83 = new FrameLayout(r36);
        this.dialogSelectorInnerLayout = r83;
        r83.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(14.0f), Theme.getColor(Theme.key_dialogBackgroundGray, r47)));
        BackupImageView r102 = new BackupImageView(r36);
        this.dialogImageView = r102;
        r102.setRoundRadius(AndroidUtilities.dp(14.0f));
        r102.getImageReceiver().setCrossfadeWithOldImage(true);
        updatePeerDialog();
        r83.addView(r102, LayoutHelper.createFrame(28, 28, 115));
        ImageView r09 = new ImageView(r36);
        this.dialogSelectorIconView = r09;
        ImageView.ScaleType r103 = ImageView.ScaleType.CENTER;
        r09.setScaleType(r103);
        int r93 = Theme.getColor(Theme.key_dialogTextGray3, r47);
        PorterDuff.Mode r152 = PorterDuff.Mode.SRC_IN;
        r09.setColorFilter(new PorterDuffColorFilter(r93, r152));
        r09.setImageResource(R.drawable.arrows_select);
        r83.addView(r09, LayoutHelper.createFrame(18, 18.0f, 21, 0.0f, 0.0f, 4.0f, 0.0f));
        r143.addView(r83, LayoutHelper.createFrame(52, 28, 17));
        r143.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), 0);
        r7.addView(r143, LayoutHelper.createLinear(-2, -1, 0.0f, 115, 6, 4, 6, 0));
        ScaleStateListAnimator.apply(r143);
        BotStarsController.getInstance(r37).loadAdminedChannels();
        AnonymousClass2 r010 = new AnonymousClass2(this, r36);
        this.titleView = r010;
        int r25 = Theme.key_windowBackgroundWhiteBlackText;
        r010.setTextColor(Theme.getColor(r25, r47));
        r010.setTextSize(1, 20.0f);
        r010.setGravity(17);
        r010.setText(LocaleController.getString(R.string.StarsReactionTitle2));
        r010.setTypeface(AndroidUtilities.bold());
        r010.setEllipsize(TextUtils.TruncateAt.END);
        r7.addView(r010, LayoutHelper.createLinear(-1, -2, 1.0f, 119, 2, 0, 2, 0));
        updateCanSwitchPeer(false);
        ImageView r011 = new ImageView(r36);
        this.closeView = r011;
        r011.setScaleType(r103);
        r011.setImageResource(R.drawable.ic_close_white);
        r011.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogEmptyImage, r47), r152));
        ScaleStateListAnimator.apply(r011);
        r011.setOnClickListener(new StarsReactionsSheet$$ExternalSyntheticLambda2(this));
        r7.addView(r011, LayoutHelper.createLinear(48, 48, 0.0f, 53, 0, 6, 6, 0));
        LinearLayout r012 = new LinearLayout(r36);
        r012.setOrientation(1);
        FrameLayout r72 = this.topLayout;
        if (r44 == false) goto L74;
        float r31 = 0.0f;
    L78:
        r72.addView(r012, LayoutHelper.createFrame(-1, -2.0f, 55, 0.0f, r31, 0.0f, 15.0f));
        TLRPC.Chat r73 = MessagesController.getInstance(r37).getChat(Long.valueOf(-r38));
        TextView r11 = new TextView(r36);
        this.statusView = r11;
        r11.setTextColor(Theme.getColor(r25, r47));
        r11.setTextSize(1, 14.0f);
        r11.setGravity(17);
        r11.setSingleLine(false);
        r11.setMaxLines(3);
        if (r13 == null) goto L81;
        String r12 = LocaleController.formatPluralStringComma("StarsReactionTextSent", r13.count);
    L86:
        r11.setText(Emoji.replaceEmoji(AndroidUtilities.replaceTags(r12), r11.getPaint().getFontMetricsInt(), false));
        if (r43 == false) goto L91;
        if (r44 == true) goto L91;
        r012.addView(r11, LayoutHelper.createLinear(-1, -2, 55, 40, 0, 40, 0));
    L91:
        if (r222 == false) goto L104;
        if (r44 == true) goto L94;
        AnonymousClass3 r84 = new AnonymousClass3(this, r36, r47);
        this.separatorView = r84;
        r012.addView(r84, LayoutHelper.createLinear(-1, 30, 55, 0, 20, 0, 0));
    L95:
        TopSendersView r013 = new TopSendersView(this, r36, r44);
        this.topSendersView = r013;
        r013.setOnSenderClickListener(new StarsReactionsSheet$$ExternalSyntheticLambda3(this, r37, r44));
        LinearLayout r85 = this.layout;
        if (r44 == false) goto L98;
        float r312 = -50.0f;
    L99:
        r85.addView(r013, LayoutHelper.createLinear(-1, 110, 0.0f, r312, 0.0f, 0.0f));
        View r014 = new View(r36);
        this.checkSeparatorView = r014;
        r014.setBackgroundColor(Theme.getColor(Theme.key_divider, r47));
        if (r44 == true) goto L105;
        if (r43 == true) goto L103;
        if (r13 == null) goto L105;
    L103:
        this.layout.addView(r014, LayoutHelper.createLinear(-1, 1.0f / AndroidUtilities.density, 7, 24, 0, 24, 0));
    L105:
        if (r44 == false) goto L117;
        int r015 = Theme.key_dialogTextBlack;
        TextView r86 = TextHelper.makeTextView(r36, 20.0f, r015, true, r47);
        r86.setGravity(17);
        if (r43 == false) goto L109;
        int r122 = R.string.LiveStoryReactTitle;
    L110:
        r86.setText(LocaleController.getString(r122));
        this.layout.addView(r86, LayoutHelper.createLinear(-1, -2, 7, 32, 6, 32, 9));
        TextView r016 = TextHelper.makeTextView(r36, 14.0f, r015, false, r47);
        r016.setGravity(17);
        if (r43 == false) goto L113;
        int r87 = R.string.LiveStoryReactText;
    L116:
        r016.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r87, new Object[]{DialogObject.getName(r38)})));
        this.layout.addView(r016, LayoutHelper.createLinear(-1, -2, 7, 32, 0, 32, 20));
        goto L117
    L113:
        if (r222 == false) goto L115;
        r87 = R.string.LiveStoryReactAdminText;
        goto L116
    L115:
        r87 = R.string.LiveStoryReactAdminEmptyText;
        goto L116
    L109:
        r122 = R.string.LiveStoryReactAdminTitle;
    L117:
        TLRPC.MessageReactor r153 = r13;
        if (r44 == false) goto L120;
        LiveCommentsView.Message r017 = new LiveCommentsView.Message();
        this.commentMessage = r017;
        TLRPC.Chat r46 = r73;
        r017.dialogId = this.peer;
        r017.stars = 50;
        r017.isReaction = true;
        LiveCommentsView.LiveCommentView r018 = new LiveCommentsView.LiveCommentView(r36, r37, true);
        this.commentView = r018;
        r018.set(this.commentMessage);
        this.layout.addView(this.commentView, LayoutHelper.createLinear(-2, -2, 17, 32, 0, 32, 20));
    L121:
        CheckBox2 r019 = new CheckBox2(r36, 21, r47);
        this.checkBox = r019;
        r019.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        r019.setDrawUnchecked(true);
        if (this.peer == UserObject.ANONYMOUS) goto L125;
        boolean r74 = true;
    L126:
        r019.setChecked(r74, false);
        TopSendersView r75 = this.topSendersView;
        if (r75 == null) goto L129;
        r75.setMyPrivacy(this.peer);
    L129:
        r019.setDrawBackgroundAsArc(10);
        TextView r88 = new TextView(r36);
        this.checkTextView = r88;
        r88.setTextColor(Theme.getColor(r25, r47));
        r88.setTextSize(1, 14.0f);
        r88.setText(LocaleController.getString(R.string.StarsReactionShowMeInTopSenders));
        LinearLayout r26 = new LinearLayout(r36);
        this.checkLayout = r26;
        r26.setOrientation(0);
        r26.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
        r26.addView(r019, LayoutHelper.createLinear(21, 21, 16, 0, 0, 9, 0));
        r26.addView(r88, LayoutHelper.createLinear(-2, -2, 16));
        r26.setOnClickListener(new StarsReactionsSheet$$ExternalSyntheticLambda4(this));
        ScaleStateListAnimator.apply(r26, 0.05f, 1.2f);
        r26.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r47), 6, 6));
        if (r44 == true) goto L138;
        if (r43 == true) goto L133;
        if (r153 == null) goto L138;
    L133:
        LinearLayout r020 = this.layout;
        if (r222 == false) goto L136;
        int r313 = 10;
    L137:
        r020.addView(r26, LayoutHelper.createLinear(-2, -2, 1, 0, r313, 0, 10));
        goto L138
    L136:
        r313 = 4;
    L138:
        ButtonWithCounterView r123 = new ButtonWithCounterView(r36, r47);
        this.buttonView = r123;
        r123.setRound();
        if (r43 == true) goto L143;
        if (r44 == true) goto L143;
    L147:
        updateSenders(0);
        r123.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.StarsReactionSend, new Object[]{LocaleController.formatNumber(50, ',')}), this.starRef), true);
        if (r43 == false) goto L150;
        int r132 = 17;
        long r172 = 0;
        Context r76 = r36;
        r123.setOnClickListener(new StarsReactionsSheet$$ExternalSyntheticLambda5(this, r41, r40, r37, r44, r36, r47, r38, r46));
    L151:
        r143.setOnClickListener(new StarsReactionsSheet$$ExternalSyntheticLambda6(this, r37, r47, r38, r44));
        LinkSpanDrawable.LinksTextView r021 = new LinkSpanDrawable.LinksTextView(r76, r47);
        r021.setTextSize(1, 13.0f);
        r021.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r47));
        if (r44 == false) goto L155;
        if (r43 == true) goto L155;
        r021.setText(LocaleController.getString(R.string.LiveStoryReactAdminCant));
    L156:
        r021.setGravity(r132);
        r021.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        if (r43 == true) goto L159;
        if (r44 == true) goto L159;
    L160:
        setCustomView(this.layout);
        AnonymousClass6 r022 = new AnonymousClass6(this, r76, 1, 2);
        this.icon3dView = r022;
        GLIconRenderer r27 = r022.mRenderer;
        r27.colorKey1 = Theme.key_starsGradient1;
        r27.colorKey2 = Theme.key_starsGradient2;
        r27.updateColors();
        r022.mRenderer.white = 1.0f;
        r022.setVisibility(4);
        r022.setPaused(true);
        this.container.addView(r022, LayoutHelper.createFrame(150, 150.0f));
        this.slider.setValue(50);
        if (r42 == null) goto L187;
        long r77 = r172;
        int r133 = 0;
    L164:
        if (r133 >= r42.size()) goto L169;
        long r28 = r42.get(r133).count;
        if (r28 <= r77) goto L168;
        r77 = r28;
    L168:
        r133 = r133 + 1;
        goto L164
    L169:
        if (r153 == null) goto L172;
        r77 = r77 - r153.count;
    L172:
        if (r77 <= r172) goto L188;
        this.slider.setStarsTop(r77 + 1);
        return;
    L188:
        return;
    L187:
        return;
    L159:
        this.layout.addView(r021, LayoutHelper.createLinear(-1, -2, 17, 14, 8, 14, 12));
    L155:
        r021.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsReactionTerms), new StarsReactionsSheet$$ExternalSyntheticLambda7(r76)));
        goto L156
    L150:
        r76 = r36;
        r132 = 17;
        r172 = 0;
    L143:
        if (r43 == true) goto L146;
        r123.setAlpha(0.5f);
        r123.setEnabled(false);
    L146:
        this.layout.addView(r123, LayoutHelper.createLinear(-1, 48, 14.0f, 0.0f, 14.0f, 0.0f));
        goto L147
    L125:
        r74 = false;
        goto L126
    L120:
        r46 = r73;
        goto L121
    L98:
        r312 = 0.0f;
        goto L99
    L94:
        this.separatorView = null;
        goto L95
    L104:
        this.separatorView = null;
        this.topSendersView = null;
        this.checkSeparatorView = null;
        goto L105
    L81:
        int r124 = R.string.StarsReactionText;
        if (r73 != null) goto L84;
        String r154 = _UrlKt.FRAGMENT_ENCODE_SET;
    L85:
        r12 = LocaleController.formatString(r124, new Object[]{r154});
        goto L86
    L84:
        r154 = r73.title;
        goto L85
    L74:
        if (r43 == false) goto L77;
        float r94 = 179.0f;
    L76:
        r31 = r94;
        goto L78
    L77:
        r94 = 45.0f;
    L57:
        if (r43 == true) goto L59;
        this.slider.setAlpha(0.5f);
    L59:
        FrameLayout r78 = this.topLayout;
        StarsSlider r89 = this.slider;
        if (r44 == false) goto L62;
        float r272 = -50.0f;
    L63:
        if (r44 == false) goto L66;
        if (r222 == true) goto L66;
        float r29 = -40.0f;
    L67:
        r78.addView(r89, LayoutHelper.createFrame(-1, -2.0f, 55, 0.0f, r272, 0.0f, r29));
    L66:
        r29 = 0.0f;
        goto L67
    L62:
        r272 = 0.0f;
        goto L63
    L44:
        r92.add(Integer.valueOf((int) r142));
        goto L50
    L49:
        r222 = r82;
        goto L50
    L35:
        this.peer = StarsController.getInstance(r37).getPaidReactionsDialogId(r41);
        goto L36
    L21:
        if (r42.isEmpty() == true) goto L23;
        r82 = true;
        goto L24
    L18:
        r13 = null;
        goto L19
    }

    public static /* synthetic */ void access$001(StarsReactionsSheet r0) {
        super.dismissInternal();
    }

    private void animate3dIcon(Runnable r17) {
        ReactionsLayoutInBubble.ReactionButton r2 = null;
        MessageObject r22 = null;
        if (this.messageObject == null) goto L43;
        View r0 = this.chatActivity.fragmentView;
        if (r0 == null) goto L43;
        if (r0.isAttachedToWindow() == false) goto L43;
        View r02 = this.messageCell;
        if ((r02 instanceof ChatMessageCell) == false) goto L13;
        ReactionsLayoutInBubble r3 = ((ChatMessageCell) r02).reactionsLayoutInBubble;
        ReactionsLayoutInBubble.ReactionButton r4 = r3.getReactionButton(ReactionsLayoutInBubble.VisibleReaction.asStar());
    L16:
        if (r4 != null) goto L38;
        if (r3 == null) goto L38;
        MessageObject.GroupedMessages r5 = this.chatActivity.getValidGroupedMessage(this.messageObject);
        if (r5 != null) goto L21;
    L33:
        if (r02 != null) goto L36;
        return;
    L36:
        if ((r02 instanceof ChatMessageCell) == false) goto L38;
        ReactionsLayoutInBubble r23 = ((ChatMessageCell) r02).reactionsLayoutInBubble;
        r3 = r23;
        r2 = r23.getReactionButton(ReactionsLayoutInBubble.VisibleReaction.asStar());
    L39:
        if (r2 == null) goto L70;
        ReactionsLayoutInBubble r6 = r3;
        View r32 = r02;
    L46:
        View r52 = r32;
        int[] r33 = new int[2];
        RectF r10 = new RectF();
        this.slider.getLocationInWindow(r33);
        r10.set(StarsSlider.m19218$$Nest$fgetcounterImage(this.slider).getBounds());
        r10.inset(-AndroidUtilities.dp(3.5f), -AndroidUtilities.dp(3.5f));
        r10.offset(r33[0], r33[1]);
        this.icon3dView.whenReady(new StarsReactionsSheet$$ExternalSyntheticLambda15(this));
        if (r2 == null) goto L49;
        r2.drawImage = false;
    L49:
        if (r52 == null) goto L51;
        r52.invalidate();
    L51:
        LiveCommentsView.LiveCommentView[] r42 = new LiveCommentsView.LiveCommentView[1];
        if (this.liveStories == false) goto L56;
        LiveCommentsView r03 = this.commentsView;
        if (r03 == null) goto L56;
        r42[0] = r03.findComment(this.sentMessageId);
    L56:
        ReactionsLayoutInBubble.ReactionButton r7 = r2;
        RectF r43 = new RectF();
        StarsReactionsSheet$$ExternalSyntheticLambda16 r04 = new StarsReactionsSheet$$ExternalSyntheticLambda16(this, r42, r33, r43, r52, r6, r7);
        r04.run();
        this.icon3dView.setPaused(false);
        this.icon3dView.setVisibility(0);
        RectF r53 = new RectF();
        r53.set(r10);
        this.icon3dView.setTranslationX(r53.centerX() - (AndroidUtilities.dp(150.0f) / 2.0f));
        this.icon3dView.setTranslationY(r53.centerY() - (AndroidUtilities.dp(150.0f) / 2.0f));
        this.icon3dView.setScaleX(r53.width() / AndroidUtilities.dp(150.0f));
        this.icon3dView.setScaleY(r53.height() / AndroidUtilities.dp(150.0f));
        ValueAnimator r24 = this.iconAnimator;
        if (r24 == null) goto L59;
        r24.cancel();
    L59:
        boolean[] r62 = new boolean[1];
        ValueAnimator r8 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.iconAnimator = r8;
        r8.addUpdateListener(new StarsReactionsSheet$$ExternalSyntheticLambda17(this, r04, r10, r43, r53, r62, r17));
        this.iconAnimator.addListener(new AnonymousClass7(this, r7, r52, r42, r62, r43, r17));
        this.iconAnimator.setDuration(800);
        this.iconAnimator.setInterpolator(new AnonymousClass8(this));
        this.iconAnimator.start();
        return;
    L70:
        return;
    L21:
        if (r5.posArray.isEmpty() == true) goto L33;
        ArrayList<MessageObject> r63 = r5.messages;
        int r72 = r63.size();
        int r9 = 0;
    L23:
        if (r9 >= r72) goto L31;
        MessageObject r102 = r63.get(r9);
        r9 = r9 + 1;
        MessageObject r103 = r102;
        MessageObject.GroupedMessagePosition r11 = r5.getPosition(r103);
        if (r11 == null) goto L23;
        int r112 = r11.flags;
        if ((r112 & 1) == 0) goto L23;
        if ((r112 & 8) == 0) goto L23;
        r22 = r103;
    L31:
        if (r22 == null) goto L33;
        r02 = this.chatActivity.findMessageCell(r22.getId(), false);
    L38:
        r2 = r4;
        goto L39
    L13:
        if ((r02 instanceof ChatActionCell) == false) goto L15;
        r3 = ((ChatActionCell) r02).reactionsLayoutInBubble;
        r4 = r3.getReactionButton(ReactionsLayoutInBubble.VisibleReaction.asStar());
        goto L16
    L15:
        r3 = null;
        r4 = null;
    L43:
        if (this.commentsView != null) goto L45;
        return;
    L45:
        r32 = null;
        r6 = null;
        goto L46
    }

    private boolean canSwitchPeer() {
        if (this.liveStories == false) goto L5;
        return false;
    L5:
        ArrayList<TLObject> r5 = BotStarsController.getInstance(this.currentAccount).getAdminedChannels();
        int r0 = r5.size();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L13;
        TLObject r3 = r5.get(r2);
        r2 = r2 + 1;
        if ((r3 instanceof TLRPC.Chat) == false) goto L6;
        if (ChatObject.isChannelAndNotMegaGroup((TLRPC.Chat) r3) == false) goto L6;
        return true;
    L13:
        return false;
    }

    private void checkVisibility() {
        if (this.checkedVisiblity == true) goto L23;
        this.checkedVisiblity = true;
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L24;
        Long r02 = r0.getMyPaidReactionPeer();
        if (r02 != null) goto L11;
    L14:
        this.messageObject.setMyPaidReactionDialogId(this.peer);
        StarsController.MessageId r03 = StarsController.MessageId.from(this.messageObject);
        TLRPC.TL_messages_togglePaidReactionPrivacy r1 = new TLRPC.TL_messages_togglePaidReactionPrivacy();
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r03.did);
        r1.msg_id = r03.mid;
        long r2 = this.peer;
        if (r2 != 0) goto L18;
        r1.privacy = new TL_stars.paidReactionPrivacyDefault();
    L21:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starReactionAnonymousUpdate, new Object[]{Long.valueOf(r03.did), Integer.valueOf(r03.mid), Long.valueOf(this.peer)});
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StarsReactionsSheet$$ExternalSyntheticLambda0(this));
        return;
    L18:
        if (r2 != UserObject.ANONYMOUS) goto L20;
        r1.privacy = new TL_stars.paidReactionPrivacyAnonymous();
        goto L21
    L20:
        TL_stars.paidReactionPrivacyPeer r22 = new TL_stars.paidReactionPrivacyPeer();
        r1.privacy = r22;
        r22.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.peer);
        goto L21
    L11:
        if (r02.longValue() != this.peer) goto L14;
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$animate3dIcon$13() {
        StarsSlider r1 = this.slider;
        r1.drawCounterImage = false;
        r1.invalidate();
    }

    private /* synthetic */ void lambda$animate3dIcon$14(LiveCommentsView.LiveCommentView[] r5, int[] r6, RectF r7, View r8, ReactionsLayoutInBubble r9, ReactionsLayoutInBubble.ReactionButton r10) {
        if (this.liveStories == false) goto L11;
        LiveCommentsView.LiveCommentView r82 = r5[0];
        if (r82 != null) goto L8;
        r82 = this.commentsView.findComment(this.sentMessageId);
        r5[0] = r82;
    L8:
        if (r82 == null) goto L13;
        r82.setDrawStar(false);
        r82.getLocationInWindow(r6);
        r82.getStarLocation(r7);
        r7.offset(r6[0], r6[1]);
        return;
    L13:
        return;
    L11:
        r8.getLocationInWindow(r6);
        r7.set(((r6[0] + r9.x) + r10.x) + AndroidUtilities.dp(4.0f), ((r6[1] + r9.y) + r10.y) + ((r10.height - AndroidUtilities.dp(22.0f)) / 2.0f), ((r6[0] + r9.x) + r10.x) + AndroidUtilities.dp(26.0f), ((r6[1] + r9.y) + r10.y) + ((r10.height + AndroidUtilities.dp(22.0f)) / 2.0f));
    }

    private /* synthetic */ void lambda$animate3dIcon$15(Runnable r5, RectF r6, RectF r7, RectF r8, boolean[] r9, Runnable r10, ValueAnimator r11) {
        float r112 = ((Float) r11.getAnimatedValue()).floatValue();
        r5.run();
        AndroidUtilities.lerp(r6, r7, r112, r8);
        this.icon3dView.setTranslationX(r8.centerX() - (AndroidUtilities.dp(150.0f) / 2.0f));
        this.icon3dView.setTranslationY(r8.centerY() - (AndroidUtilities.dp(150.0f) / 2.0f));
        float r52 = AndroidUtilities.lerp(Math.max(r8.width() / AndroidUtilities.dp(150.0f), r8.height() / AndroidUtilities.dp(150.0f)), 1.0f, (float) Math.sin(r112 * 3.141592653589793d));
        this.icon3dView.setScaleX(r52);
        this.icon3dView.setScaleY(r52);
        GLIconRenderer r53 = this.icon3dView.mRenderer;
        r53.angleX = 360.0f * r112;
        r53.white = Math.max(0.0f, 1.0f - (4.0f * r112));
        if (r9[0] == false) goto L5;
        return;
    L5:
        if (r112 <= 0.95f) goto L15;
        r9[0] = true;
        LaunchActivity.makeRipple(r7.centerX(), r7.centerY(), 1.5f);
        this.container.performHapticFeedback(0, 1);     // Catch: Exception -> L11
    L8:
        if (r10 == null) goto L16;
        r10.run();
        return;
    L16:
        return;
    }

    private /* synthetic */ void lambda$checkVisibility$12(TLObject r10, TLRPC.TL_error r11) {
        if ((r10 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        MessagesStorage.getInstance(this.currentAccount).putMessages(new ArrayList(Arrays.asList(new TLRPC.Message[]{this.messageObject.messageOwner})), true, true, true, 0, 0, 0);
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$2(int r7, boolean r8, Long r9) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L6;
        return;
    L6:
        if (r9.longValue() < 0) goto L11;
        Bundle r1 = new Bundle();
        r1.putLong("user_id", r9.longValue());
        if (r9.longValue() != UserConfig.getInstance(r7).getClientUserId()) goto L10;
        r1.putBoolean("my_profile", true);
    L10:
        r0.presentFragment(new AnonymousClass4(this, r1, r8));
        dismiss();
    L12:
        dismiss();
        return;
    L11:
        Bundle r72 = new Bundle();
        r72.putLong("chat_id", -r9.longValue());
        r0.presentFragment(new AnonymousClass5(this, r72, r8));
        goto L12
    }

    private /* synthetic */ void lambda$new$3(View r3) {
        this.checkBox.setChecked(!r3.isChecked(), true);
        if (this.checkBox.isChecked() == false) goto L5;
        long r0 = this.lastSelectedPeer;
    L6:
        this.peer = r0;
        updatePeerDialog();
        TopSendersView r32 = this.topSendersView;
        if (r32 == null) goto L10;
        r32.setMyPrivacy(this.peer);
        return;
    L10:
        return;
    L5:
        r0 = UserObject.ANONYMOUS;
        goto L6
    }

    private /* synthetic */ void lambda$new$4() {
        this.sending = true;
        animate3dIcon(null);
        AndroidUtilities.runOnUIThread(new StarsReactionsSheet$$ExternalSyntheticLambda14(this), 240);
    }

    private /* synthetic */ void lambda$new$5(StarsController.PendingPaidReactions r3) {
        this.sending = true;
        Objects.requireNonNull(r3);
        animate3dIcon(new StarsReactionsSheet$$ExternalSyntheticLambda13(r3));
        AndroidUtilities.runOnUIThread(new StarsReactionsSheet$$ExternalSyntheticLambda14(this), 240);
    }

    private /* synthetic */ void lambda$new$6(long r9, StarsController r11, MessageObject r12, ChatActivity r13) {
        Utilities.Callback2Return<Long, Long, Integer> r0 = this.onSendListener;
        long r1 = this.peer;
        if (r0 == null) goto L10;
        int r92 = r0.run(Long.valueOf(r1), Long.valueOf(r9)).intValue();
        this.sentMessageId = r92;
        if (r92 != Integer.MIN_VALUE) goto L8;
        dismiss();
        return;
    L8:
        AndroidUtilities.runOnUIThread(new StarsReactionsSheet$$ExternalSyntheticLambda11(this));
        return;
    L10:
        StarsController.PendingPaidReactions r93 = r11.sendPaidReaction(r12, r13, r9, false, true, Long.valueOf(r1));
        if (r93 != null) goto L13;
        return;
    L13:
        AndroidUtilities.runOnUIThread(new StarsReactionsSheet$$ExternalSyntheticLambda12(this, r93));
    }

    private /* synthetic */ void lambda$new$7(MessageObject r12, ChatActivity r13, int r14, boolean r15, Context r16, Theme.ResourcesProvider r17, long r18, TLRPC.Chat r20, View r21) {
        if (this.sending == true) goto L34;
        long r4 = this.slider.getValue();
        if (this.onSendListener != null) goto L10;
        if (r12 == null) goto L11;
        if (r13 != null) goto L10;
        return;
    L11:
        return;
    L10:
        if (this.iconAnimator == null) goto L13;
        return;
    L13:
        if (MessagesController.getInstance(r14).isFrozen() == false) goto L16;
        AccountFrozenAlert.show(r14);
        return;
    L16:
        StarsController r42 = StarsController.getInstance(r14);
        StarsReactionsSheet$$ExternalSyntheticLambda10 r8 = new StarsReactionsSheet$$ExternalSyntheticLambda10(this, r4, r42, r12, r13);
        if (r42.balanceAvailable() == true) goto L19;
    L30:
        r8.run();
        return;
    L19:
        if (r42.getBalance().amount >= r4) goto L30;
        if (r15 == false) goto L24;
        new StarsIntroActivity.StarsNeededSheet(r16, r17, r4, 17, DialogObject.getShortName(r14, r18), r8, r18).show();
        return;
    L24:
        if (r20 != null) goto L27;
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L28:
        new StarsIntroActivity.StarsNeededSheet(r16, r17, r4, 5, r0, r8, r18).show();
        return;
    L27:
        r0 = r20.title;
        goto L28
    }

    private /* synthetic */ void lambda$new$8(long r1, boolean r3) {
        this.lastSelectedPeer = r1;
        this.peer = r1;
        if (r3 == false) goto L5;
        LiveCommentsView.Message r32 = this.commentMessage;
        r32.dialogId = r1;
        this.commentView.set(r32);
    L5:
        updatePeerDialog();
        this.checkBox.setChecked(true, true);
        TopSendersView r12 = this.topSendersView;
        if (r12 == null) goto L9;
        r12.setMyPrivacy(this.peer);
        return;
    }

    private /* synthetic */ void lambda$new$9(int r12, Theme.ResourcesProvider r13, long r14, boolean r16, View r17) {
        ArrayList<TLObject> r0 = BotStarsController.getInstance(r12).getAdminedChannels();
        r0.add(0, UserConfig.getInstance(r12).getCurrentUser());
        ItemOptions r132 = ItemOptions.makeOptions(this.containerView, r13, this.dialogSelectorInnerLayout);
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L27;
        TLObject r4 = r0.get(r3);
        r3 = r3 + 1;
        TLObject r42 = r4;
        if ((r42 instanceof TLRPC.User) == true) goto L6;
        if ((r42 instanceof TLRPC.Chat) == false) goto L3;
        TLRPC.Chat r5 = (TLRPC.Chat) r42;
        if (ChatObject.isChannelAndNotMegaGroup(r5) == false) goto L3;
        long r52 = -r5.id;
    L14:
        if (r52 == r14) goto L3;
        long r7 = this.peer;
        if (r52 != r7) goto L19;
    L24:
        boolean r72 = true;
    L25:
        r132.addChat(r42, r72, new StarsReactionsSheet$$ExternalSyntheticLambda9(this, r52, r16));
        goto L3
    L19:
        if (r7 == 0) goto L21;
    L23:
        r72 = false;
        goto L25
    L21:
        if (r52 != UserConfig.getInstance(r12).getClientUserId()) goto L23;
    L6:
        r52 = ((TLRPC.User) r42).id;
        goto L14
    L27:
        r132.setDrawScrim(false).setOnTopOfScrim().setDimAlpha(0).setGravity(5).show();
    }

    private void updateCanSwitchPeer(boolean r4) {
        int r1 = 0;
        if (this.dialogSelectorLayout.getVisibility() != 0) goto L5;
        boolean r0 = true;
    L7:
        if (r0 == canSwitchPeer()) goto L19;
        FrameLayout r02 = this.dialogSelectorLayout;
        if (canSwitchPeer() == true) goto L12;
        r1 = 8;
    L12:
        r02.setVisibility(r1);
        if (r4 == true) goto L15;
        return;
    L15:
        if (canSwitchPeer() == false) goto L17;
        this.dialogSelectorLayout.setScaleX(0.4f);
        this.dialogSelectorLayout.setScaleY(0.4f);
        this.dialogSelectorLayout.setAlpha(0.0f);
        this.dialogSelectorLayout.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).start();
    L17:
        ChangeBounds r42 = new ChangeBounds();
        r42.setDuration(200);
        TransitionManager.beginDelayedTransition(this.toptopLayout, r42);
        return;
    L19:
        return;
    L5:
        r0 = false;
        goto L7
    }

    private void updatePeerDialog() {
        AvatarDrawable r0 = new AvatarDrawable();
        r0.setScaleSize(0.42f);
        long r1 = this.peer;
        if (r1 != UserObject.ANONYMOUS) goto L6;
        r0.setAvatarType(21);
        int r12 = Theme.key_avatar_backgroundGray;
        r0.setColor(Theme.getColor(r12, this.resourcesProvider), Theme.getColor(r12, this.resourcesProvider));
        this.dialogImageView.setForUserOrChat(null, r0);
        return;
    L6:
        int r2 = this.currentAccount;
        if (r1 < 0) goto L10;
        TLRPC.User r13 = MessagesController.getInstance(r2).getUser(Long.valueOf(this.peer));
        r0.setInfo(r13);
        this.dialogImageView.setForUserOrChat(r13, r0);
        return;
    L10:
        TLRPC.Chat r14 = MessagesController.getInstance(r2).getChat(Long.valueOf(-this.peer));
        r0.setInfo(r14);
        this.dialogImageView.setForUserOrChat(r14, r0);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void appendOpenAnimator(boolean r9, ArrayList<Animator> r10) {
        BalanceCloud r0 = this.balanceCloud;
        Property r1 = View.ALPHA;
        float r2 = 1.0f;
        if (r9 == false) goto L5;
        float r3 = 1.0f;
    L6:
        r10.add(ObjectAnimator.ofFloat(r0, r1, new float[]{r3}));
        BalanceCloud r02 = this.balanceCloud;
        Property r12 = View.SCALE_X;
        if (r9 == false) goto L9;
        float r5 = 1.0f;
    L10:
        r10.add(ObjectAnimator.ofFloat(r02, r12, new float[]{r5}));
        BalanceCloud r8 = this.balanceCloud;
        Property r03 = View.SCALE_Y;
        if (r9 == true) goto L14;
        r2 = 0.6f;
    L14:
        r10.add(ObjectAnimator.ofFloat(r8, r03, new float[]{r2}));
        return;
    L9:
        r5 = 0.6f;
        goto L10
    L5:
        r3 = 0.0f;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        if (StarsSlider.m19221$$Nest$fgettracking(this.slider) == false) goto L7;
        return false;
    L7:
        return super.canDismissWithSwipe();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.adminedChannelsLoaded) goto L6;
        updateCanSwitchPeer(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        if (this.sending == true) goto L5;
        checkVisibility();
    L5:
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        ValueAnimator r0 = this.iconAnimator;
        if (r0 != null) goto L5;
    L7:
        super.dismissInternal();
        return;
    L5:
        if (r0.isRunning() == false) goto L7;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean isTouchOutside(float r3, float r4) {
        if (r3 < this.balanceCloud.getX()) goto L13;
        if (r3 > (this.balanceCloud.getX() + this.balanceCloud.getWidth())) goto L13;
        if (r4 < this.balanceCloud.getY()) goto L13;
        if (r4 > (this.balanceCloud.getY() + this.balanceCloud.getHeight())) goto L13;
        return false;
    L13:
        return super.isTouchOutside(r3, r4);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.adminedChannelsLoaded);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.adminedChannelsLoaded);
    }

    public StarsReactionsSheet setLiveCommentsView(LiveCommentsView r1) {
        this.commentsView = r1;
        return this;
    }

    public void setMessageCell(ChatActivity r1, int r2, View r3) {
        this.chatActivity = r1;
        this.messageId = r2;
        this.messageCell = r3;
    }

    public StarsReactionsSheet setOnSend(Utilities.Callback2Return<Long, Long, Integer> r1) {
        this.onSendListener = r1;
        return this;
    }

    public void updateSenders(long r21) {
        if (this.liveStories == false) goto L10;
        if (this.sendEnabled == true) goto L10;
        if (r21 <= 0) goto L10;
        return;
    L10:
        if (this.topSendersView == null) goto L43;
        ArrayList r1 = new ArrayList();
        long r7 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        boolean r5 = true;
        long r9 = 0;
        if (this.reactors == null) goto L31;
        int r4 = 0;
    L15:
        if (r4 >= this.reactors.size()) goto L31;
        TLRPC.MessageReactor r6 = this.reactors.get(r4);
        long r12 = DialogObject.getPeerDialogId(r6.peer_id);
        boolean r14 = r6.anonymous;
        if (r14 == true) goto L19;
    L22:
        long r16 = r12;
    L24:
        if (r6.my == false) goto L26;
    L29:
        r9 = r6.count;
    L30:
        r4 = r4 + 1;
        goto L15
    L26:
        if (r16 == r7) goto L29;
        r1.add(SenderData.of(r14, false, r16, r6.count));
        goto L30
    L19:
        if (r6.my == false) goto L21;
        r16 = r7;
        goto L24
    L21:
        r12 = (-r4) - 1;
    L31:
        long r92 = r9 + r21;
        if (r92 > 0) goto L34;
    L38:
        Collections.sort(r1, new StarsReactionsSheet$$ExternalSyntheticLambda8());
        this.topSendersView.setSenders(new ArrayList(r1.subList(0, Math.min(3, r1.size()))));
        return;
    L34:
        if (this.peer == UserObject.ANONYMOUS) goto L37;
        r5 = false;
    L37:
        r1.add(SenderData.of(r5, true, r7, r92));
        goto L38
    }
}
