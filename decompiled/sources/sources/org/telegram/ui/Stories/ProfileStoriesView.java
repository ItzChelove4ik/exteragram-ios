package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.OvershootInterpolator;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RadialProgress;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesUtilities;
import org.telegram.ui.Stories.StoryViewer;

/* loaded from: classes7.dex */
public class ProfileStoriesView extends View implements NotificationCenter.NotificationCenterDelegate {
    private float actionBarProgress;
    private boolean attached;
    private final View avatarContainer;
    private final ProfileActivity.AvatarImageView avatarImage;
    private float bounceScale;
    private final ArrayList<StoryCircle> circles;
    private final Paint clipOutAvatar;
    private final Path clipPath;
    private int count;
    private final int currentAccount;
    private float cy;
    private final long dialogId;
    private float expandProgress;
    private float expandRight;
    private boolean expandRightPad;
    private final AnimatedFloat expandRightPadAnimated;
    private float expandY;
    private float fragmentTransitionProgress;
    private final StoriesUtilities.StoryGradientTools gradientTools;
    private final boolean isTopic;
    private StoriesController.UploadingStory lastUploadingStory;
    private float left;
    private final Paint livePaint;
    private StoryCircle mainCircle;
    private ValueAnimator newStoryBounce;
    private float newStoryBounceT;
    private Runnable onLongPressRunnable;
    Paint paint;
    private TL_stories.PeerStories peerStories;
    private boolean progressIsDone;
    private float progressToInsets;
    private final AnimatedFloat progressToUploading;
    private boolean progressWasDrawn;
    private final StoryViewer.PlaceProvider provider;
    private RadialProgress radialProgress;
    private final Paint readPaint;
    private int readPaintAlpha;
    private final RectF rect1;
    private final RectF rect2;
    private final RectF rect3;
    private float right;
    private final AnimatedFloat rightAnimated;
    private final AnimatedFloat segmentsCountAnimated;
    private final AnimatedFloat segmentsUnreadCountAnimated;
    StoriesController storiesController;
    private long tapTime;
    private float tapX;
    private float tapY;
    private final AnimatedTextView.AnimatedTextDrawable titleDrawable;
    private int unreadCount;
    private int uploadingStoriesCount;
    float w;
    private final Paint whitePaint;

    /* renamed from: org.telegram.ui.Stories.ProfileStoriesView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ProfileStoriesView this$0;
        final /* synthetic */ boolean[] val$vibrated;

        public AnonymousClass1(ProfileStoriesView r1, boolean[] r2) {
            this.this$0 = r1;
            this.val$vibrated = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            boolean[] r32 = this.val$vibrated;
            if (r32[0] == true) goto L5;
            r32[0] = true;
            ProfileStoriesView.m19650$$Nest$mvibrateNewStory(this.this$0);
        L5:
            ProfileStoriesView.m19646$$Nest$fputnewStoryBounceT(this.this$0, 1.0f);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.ProfileStoriesView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ProfileStoriesView this$0;

        public AnonymousClass2(ProfileStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            ProfileActivity.AvatarImageView r32 = ProfileStoriesView.m19641$$Nest$fgetavatarImage(this.this$0);
            ProfileStoriesView.m19645$$Nest$fputbounceScale(this.this$0, 1.0f);
            r32.bounceScale = 1.0f;
            ProfileStoriesView.m19641$$Nest$fgetavatarImage(this.this$0).invalidate();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.ProfileStoriesView$3, reason: invalid class name */
    public class AnonymousClass3 implements StoryViewer.PlaceProvider {
        final /* synthetic */ ProfileStoriesView this$0;

        public static /* synthetic */ void $r8$lambda$SjqFQApuaYFC42Vp5VbxuBnWgsA(AnonymousClass3 r0, RectF r1, StoryCircle r2, RectF r3, StoryCircle r4, Canvas r5, RectF r6, float r7, boolean r8) {
            r0.lambda$findView$0(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public AnonymousClass3(ProfileStoriesView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$findView$0(RectF r5, StoryCircle r6, RectF r7, StoryCircle r8, Canvas r9, RectF r10, float r11, boolean r12) {
            r5.set(r6.cachedRect);
            r7.set(r8.cachedRect);
            r6.cachedRect.set(r10);
            float r122 = r10.width() / r5.width();     // Catch: Exception -> L6
            float r0 = r10.centerX() - ((r5.centerX() - r7.centerX()) * (((1.0f - r11) * 2.0f) + r122));     // Catch: Exception -> L6
            float r102 = r10.centerY();     // Catch: Exception -> L6
            float r1 = (r7.width() / 2.0f) * r122;     // Catch: Exception -> L6
            float r2 = (r7.height() / 2.0f) * r122;     // Catch: Exception -> L6
            r8.cachedRect.set(r0 - r1, r102 - r2, r0 + r1, r102 + r2);     // Catch: Exception -> L6
        L4:
            ProfileStoriesView.m19647$$Nest$mclipCircle(this.this$0, r9, r6, r8);
            r6.cachedRect.set(r5);
            r8.cachedRect.set(r7);
        }

        @Override // org.telegram.ui.Stories.StoryViewer.PlaceProvider
        public boolean findView(long r8, int r10, int r11, int r12, StoryViewer.TransitionViewHolder r13) {
            r13.avatarImage = null;
            r13.storyImage = null;
            if (ProfileStoriesView.m19643$$Nest$fgetexpandProgress(this.this$0) >= 0.2f) goto L6;
            r13.avatarImage = ProfileStoriesView.m19641$$Nest$fgetavatarImage(this.this$0).getImageReceiver();
            r13.storyImage = null;
            r13.view = ProfileStoriesView.m19641$$Nest$fgetavatarImage(this.this$0);
            r13.clipTop = 0.0f;
            r13.clipBottom = AndroidUtilities.displaySize.y;
            r13.clipParent = (View) this.this$0.getParent();
            r13.radialProgressUpload = ProfileStoriesView.m19644$$Nest$fgetradialProgress(this.this$0);
            r13.checkParentScale = true;
            return true;
        L6:
            int r0 = 0;
        L8:
            if (r0 >= ProfileStoriesView.m19642$$Nest$fgetcircles(this.this$0).size()) goto L23;
            StoryCircle r1 = (StoryCircle) ProfileStoriesView.m19642$$Nest$fgetcircles(this.this$0).get(r0);
            if (r1.scale < 1.0f) goto L22;
            if (r1.storyId != r11) goto L22;
            ProfileStoriesView r112 = this.this$0;
            int r2 = r0 - 1;
            if (r2 < 0) goto L16;
            StoryCircle r22 = (StoryCircle) ProfileStoriesView.m19642$$Nest$fgetcircles(r112).get(r2);
        L17:
            int r02 = r0 - 2;
            if (r02 < 0) goto L20;
            StoryCircle r03 = (StoryCircle) ProfileStoriesView.m19642$$Nest$fgetcircles(this.this$0).get(r02);
        L21:
            StoryCircle r113 = ProfileStoriesView.m19648$$Nest$mnearest(r112, r22, r03, r1);
            ImageReceiver r04 = r1.imageReceiver;
            StoryCircle r6 = r113;
            StoryCircle r4 = r1;
        L24:
            if (r04 != null) goto L26;
            return false;
        L26:
            r13.storyImage = r04;
            r13.avatarImage = null;
            ProfileStoriesView r9 = this.this$0;
            r13.view = r9;
            r13.clipTop = 0.0f;
            r13.clipBottom = AndroidUtilities.displaySize.y;
            r13.clipParent = (View) r9.getParent();
            if (r4 == null) goto L30;
            if (r6 == null) goto L30;
            r13.drawClip = new ProfileStoriesView$3$$ExternalSyntheticLambda0(this, new RectF(r4.cachedRect), r4, new RectF(r6.cachedRect), r6);
        L31:
            return true;
        L30:
            r13.drawClip = null;
            goto L31
        L20:
            r03 = null;
            goto L21
        L16:
            r22 = null;
        L22:
            r0 = r0 + 1;
            goto L8
        L23:
            r04 = null;
            r4 = null;
            r6 = null;
            goto L24
        }

        @Override // org.telegram.ui.Stories.StoryViewer.PlaceProvider
        public void preLayout(long r1, int r3, Runnable r4) {
            ProfileStoriesView.m19649$$Nest$mupdateStories(this.this$0, true, false);
            r4.run();
        }
    }

    public class StoryCircle {
        AvatarDrawable avatarDrawable;
        final RectF borderRect;
        float cachedIndex;
        float cachedRead;
        final RectF cachedRect;
        float cachedScale;
        ImageReceiver imageReceiver;
        int index;
        final AnimatedFloat indexAnimated;
        boolean live;
        boolean read;
        final AnimatedFloat readAnimated;
        float scale;
        final AnimatedFloat scaleAnimated;
        int storyId;
        final /* synthetic */ ProfileStoriesView this$0;

        public StoryCircle(ProfileStoriesView r5, TL_stories.StoryItem r6) {
            this.this$0 = r5;
            this.avatarDrawable = new AvatarDrawable();
            this.imageReceiver = new ImageReceiver();
            this.index = 0;
            this.read = false;
            this.scale = 1.0f;
            CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.readAnimated = new AnimatedFloat(r5, 420, r1);
            this.indexAnimated = new AnimatedFloat(r5, 420, r1);
            this.scaleAnimated = new AnimatedFloat(r5, 420, r1);
            this.cachedRect = new RectF();
            this.borderRect = new RectF();
            this.storyId = r6.id;
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(200.0f));
            this.imageReceiver.setParentView(r5);
            this.live = r6.media instanceof TLRPC.TL_messageMediaVideoStream;
            if (ProfileStoriesView.m19640$$Nest$fgetattached(r5) == false) goto L5;
            this.imageReceiver.onAttachedToWindow();
        L5:
            StoriesUtilities.setThumbImage(this.avatarDrawable, this.imageReceiver, r6, 25, 25);
        }

        public void apply() {
            this.readAnimated.set(this.read, true);
            this.indexAnimated.set(this.index, true);
            this.scaleAnimated.set(this.scale, true);
        }

        public void destroy() {
            this.imageReceiver.onDetachedFromWindow();
        }
    }

    /* renamed from: $r8$lambda$3bhy4y-VcRIXgElJtex2qp0Z11s, reason: not valid java name */
    public static /* synthetic */ void m19637$r8$lambda$3bhy4yVcRIXgElJtex2qp0Z11s(ProfileStoriesView r0, boolean[] r1, ValueAnimator r2) {
        r0.lambda$animateNewStory$1(r1, r2);
    }

    /* renamed from: $r8$lambda$JQNPIPP51-ie698WId-XQXlPmQs, reason: not valid java name */
    public static /* synthetic */ int m19638$r8$lambda$JQNPIPP51ie698WIdXQXlPmQs(StoryCircle r0, StoryCircle r1) {
        return (int) (r1.cachedIndex - r0.cachedIndex);
    }

    public static /* synthetic */ void $r8$lambda$LsbWL_1FPFdAU8lJgWbcneL4904(ProfileStoriesView r0) {
        r0.lambda$new$4();
    }

    /* renamed from: $r8$lambda$RAb6hdlFRazCNdq5kd8FlD--DSg, reason: not valid java name */
    public static /* synthetic */ void m19639$r8$lambda$RAb6hdlFRazCNdq5kd8FlDDSg(ProfileStoriesView r0, ValueAnimator r1) {
        r0.lambda$animateBounce$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$RSLy2hF5fkos4dQT5lHlrXSuGsU(ProfileStoriesView r0) {
        r0.lambda$vibrateNewStory$0();
    }

    /* renamed from: -$$Nest$fgetattached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19640$$Nest$fgetattached(ProfileStoriesView r0) {
        return r0.attached;
    }

    /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ProfileActivity.AvatarImageView m19641$$Nest$fgetavatarImage(ProfileStoriesView r0) {
        return r0.avatarImage;
    }

    /* renamed from: -$$Nest$fgetcircles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19642$$Nest$fgetcircles(ProfileStoriesView r0) {
        return r0.circles;
    }

    /* renamed from: -$$Nest$fgetexpandProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19643$$Nest$fgetexpandProgress(ProfileStoriesView r0) {
        return r0.expandProgress;
    }

    /* renamed from: -$$Nest$fgetradialProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgress m19644$$Nest$fgetradialProgress(ProfileStoriesView r0) {
        return r0.radialProgress;
    }

    /* renamed from: -$$Nest$fputbounceScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19645$$Nest$fputbounceScale(ProfileStoriesView r0, float r1) {
        r0.bounceScale = r1;
    }

    /* renamed from: -$$Nest$fputnewStoryBounceT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19646$$Nest$fputnewStoryBounceT(ProfileStoriesView r0, float r1) {
        r0.newStoryBounceT = r1;
    }

    /* renamed from: -$$Nest$mclipCircle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19647$$Nest$mclipCircle(ProfileStoriesView r0, Canvas r1, StoryCircle r2, StoryCircle r3) {
        r0.clipCircle(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mnearest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryCircle m19648$$Nest$mnearest(ProfileStoriesView r0, StoryCircle r1, StoryCircle r2, StoryCircle r3) {
        return r0.nearest(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateStories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19649$$Nest$mupdateStories(ProfileStoriesView r0, boolean r1, boolean r2) {
        r0.updateStories(r1, r2);
    }

    /* renamed from: -$$Nest$mvibrateNewStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19650$$Nest$mvibrateNewStory(ProfileStoriesView r0) {
        r0.vibrateNewStory();
    }

    public ProfileStoriesView(Context r18, int r19, long r20, boolean r22, View r23, ProfileActivity.AvatarImageView r24, Theme.ResourcesProvider r25) {
        super(r18);
        Paint r8 = new Paint(1);
        this.readPaint = r8;
        Paint r10 = new Paint(1);
        this.livePaint = r10;
        Paint r11 = new Paint(1);
        this.whitePaint = r11;
        AnimatedTextView.AnimatedTextDrawable r12 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.titleDrawable = r12;
        Paint r14 = new Paint(1);
        this.clipOutAvatar = r14;
        this.circles = new ArrayList();
        this.paint = new Paint(1);
        this.bounceScale = 1.0f;
        this.progressToInsets = 1.0f;
        this.gradientTools = new StoriesUtilities.StoryGradientTools(this, false);
        this.rect1 = new RectF();
        this.rect2 = new RectF();
        this.rect3 = new RectF();
        this.clipPath = new Path();
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.segmentsCountAnimated = new AnimatedFloat(this, 0, 480, r6);
        this.segmentsUnreadCountAnimated = new AnimatedFloat(this, 0, 240, r6);
        this.progressToUploading = new AnimatedFloat(this, 0, 150, CubicBezierInterpolator.DEFAULT);
        this.newStoryBounceT = 1.0f;
        this.expandRightPadAnimated = new AnimatedFloat(this, 0, 350, r6);
        this.rightAnimated = new AnimatedFloat(this, 0, 350, r6);
        this.provider = new AnonymousClass3(this);
        this.onLongPressRunnable = new ProfileStoriesView$$ExternalSyntheticLambda2(this);
        this.currentAccount = r19;
        this.dialogId = r20;
        this.isTopic = r22;
        this.avatarContainer = r23;
        this.avatarImage = r24;
        r24.getImageReceiver().setVisibleInvalidate(new ProfileStoriesView$$ExternalSyntheticLambda3(this));
        this.storiesController = MessagesController.getInstance(r19).getStoriesController();
        r8.setColor(1526726655);
        this.readPaintAlpha = r8.getAlpha();
        r8.setStrokeWidth(AndroidUtilities.dpf2(1.5f));
        Paint.Style r2 = Paint.Style.STROKE;
        r8.setStyle(r2);
        Paint.Cap r3 = Paint.Cap.ROUND;
        r8.setStrokeCap(r3);
        r10.setColor(Theme.getColor(Theme.key_stories_circle_live1, r25));
        r10.setStrokeWidth(AndroidUtilities.dpf2(1.5f));
        r10.setStyle(r2);
        r10.setStrokeCap(r3);
        r11.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, r25));
        r12.setTextSize(AndroidUtilities.dp(18.0f));
        r12.setAnimationProperties(0.4f, 0, 320, r6);
        r12.setTypeface(AndroidUtilities.bold());
        r12.setTextColor(-1);
        r12.setEllipsizeByGradient(true);
        r12.setCallback(this);
        r14.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.paint.setStrokeWidth(AndroidUtilities.dpf2(2.33f));
        this.paint.setStyle(r2);
        updateStories(false, false);
    }

    private void animateBounce() {
        AnimatorSet r0 = new AnimatorSet();
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{1.0f, 1.05f});
        r2.setDuration(100);
        r2.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{1.05f, 1.0f});
        r3.setDuration(250);
        r3.setInterpolator(new OvershootInterpolator());
        ProfileStoriesView$$ExternalSyntheticLambda5 r4 = new ProfileStoriesView$$ExternalSyntheticLambda5(this);
        r2.addUpdateListener(r4);
        r3.addUpdateListener(r4);
        r0.playSequentially(new Animator[]{r2, r3});
        r0.addListener(new AnonymousClass2(this));
        r0.start();
    }

    private void clipCircle(Canvas r9, StoryCircle r10, StoryCircle r11) {
        if (r11 != null) goto L4;
        return;
    L4:
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r11.cachedRect);
        float r1 = -(AndroidUtilities.dpf2(1.66f) * r11.cachedScale);
        r0.inset(r1, r1);
        float r12 = r11.cachedRect.centerX();
        float r112 = r11.cachedRect.width() / 2.0f;
        float r3 = r10.cachedRect.centerX();
        float r4 = r10.cachedRect.width() / 2.0f;
        this.clipPath.rewind();
        if (r12 <= r3) goto L7;
        float r113 = (float) Math.toDegrees(Math.acos(Math.abs((((r12 - r112) + (r3 + r4)) / 2.0f) - r3) / r4));
        this.clipPath.arcTo(r0, 180.0f + r113, (-r113) * 2.0f);
        this.clipPath.arcTo(r10.cachedRect, r113, 360.0f - (2.0f * r113));
    L8:
        this.clipPath.close();
        r9.save();
        r9.clipPath(this.clipPath);
        return;
    L7:
        float r114 = (float) Math.toDegrees(Math.acos(Math.abs((((r12 + r112) + (r3 - r4)) / 2.0f) - r3) / r4));
        float r2 = 2.0f * r114;
        this.clipPath.arcTo(r0, -r114, r2);
        this.clipPath.arcTo(r10.cachedRect, 180.0f - r114, -(360.0f - r2));
        goto L8
    }

    private void drawArc(Canvas r7, RectF r8, float r9, float r10, boolean r11, Paint r12) {
        if (isRoundRectRing() == false) goto L6;
        float r102 = r10 + r9;
        StoriesUtilities.drawRoundRectSegment(r7, r8, getRingCornerRadius(r8), Math.min(r9, r102), Math.max(r9, r102), r12);
        return;
    L6:
        r7.drawArc(r8, r9, r10, r11, r12);
    }

    private void drawArcs(Canvas r16, StoryCircle r17, StoryCircle r18, StoryCircle r19, Paint r20) {
        StoryCircle r0 = r17;
        if (r0 != null) goto L8;
        if (r19 != null) goto L8;
        drawArc(r16, r18.borderRect, 0.0f, 360.0f, false, r20);
        return;
    L8:
        if (r0 == null) goto L37;
        if (r19 == null) goto L37;
        float r6 = r0.borderRect.centerX();
        float r02 = r0.borderRect.width() / 2.0f;
        float r7 = r18.borderRect.centerX();
        float r8 = r18.borderRect.width() / 2.0f;
        float r9 = r19.borderRect.centerX();
        float r2 = r19.borderRect.width() / 2.0f;
        boolean r11 = false;
        if (r6 <= r7) goto L13;
        boolean r10 = true;
    L14:
        if (r10 == false) goto L17;
        double r13 = Math.toDegrees(Math.acos(Math.abs((((r6 - r02) + (r7 + r8)) / 2.0f) - r7) / r8));
    L16:
        float r03 = (float) r13;
        if (r9 <= r7) goto L21;
        r11 = true;
    L21:
        if (r11 == false) goto L24;
        double r62 = Math.toDegrees(Math.acos(Math.abs((((r9 - r2) + (r7 + r8)) / 2.0f) - r7) / r8));
    L23:
        float r22 = (float) r62;
        if (r10 == false) goto L29;
        if (r11 == false) goto L29;
        float r92 = Math.max(r03, r22);
        drawArc(r16, r18.borderRect, r92, 360.0f - (2.0f * r92), false, r20);
        return;
    L29:
        if (r10 == false) goto L32;
        drawArc(r16, r18.borderRect, r22 + 180.0f, 180.0f - (r03 + r22), false, r20);
        drawArc(r16, r18.borderRect, r03, (180.0f - r22) - r03, false, r20);
        return;
    L32:
        if (r11 == false) goto L35;
        drawArc(r16, r18.borderRect, r03 + 180.0f, 180.0f - (r22 + r03), false, r20);
        drawArc(r16, r18.borderRect, r22, (180.0f - r22) - r03, false, r20);
        return;
    L35:
        float r04 = Math.max(r03, r22);
        drawArc(r16, r18.borderRect, r04 + 180.0f, 360.0f - (r04 * 2.0f), false, r20);
        return;
    L24:
        r62 = Math.toDegrees(Math.acos(Math.abs((((r9 + r2) + (r7 - r8)) / 2.0f) - r7) / r8));
        goto L23
    L17:
        r13 = Math.toDegrees(Math.acos(Math.abs((((r6 + r02) + (r7 - r8)) / 2.0f) - r7) / r8));
        goto L16
    L13:
        r10 = false;
    L37:
        if (r0 != null) goto L41;
        if (r19 != null) goto L41;
        return;
    L41:
        if (r0 != null) goto L43;
        r0 = r19;
    L43:
        float r23 = r0.borderRect.centerX();
        float r05 = r0.borderRect.width() / 2.0f;
        float r63 = r18.borderRect.centerX();
        if (Math.abs(r23 - r63) <= (r05 + (r18.borderRect.width() / 2.0f))) goto L48;
        drawArc(r16, r18.borderRect, 0.0f, 360.0f, false, r20);
        return;
    L48:
        if (r23 <= r63) goto L51;
        float r93 = (float) Math.toDegrees(Math.acos(Math.abs((((r23 - r05) + (r63 + r7)) / 2.0f) - r63) / r7));
        drawArc(r16, r18.borderRect, r93, 360.0f - (2.0f * r93), false, r20);
        return;
    L51:
        float r06 = (float) Math.toDegrees(Math.acos(Math.abs((((r23 + r05) + (r63 - r7)) / 2.0f) - r63) / r7));
        drawArc(r16, r18.borderRect, r06 + 180.0f, 360.0f - (r06 * 2.0f), false, r20);
    }

    private float getExpandRight() {
        return this.expandRight - (this.expandRightPadAnimated.set(this.expandRightPad) * AndroidUtilities.dp(71.0f));
    }

    private float getRingCornerRadius(RectF r5) {
        float r0 = this.avatarContainer.getScaleY();
        return Math.max(0.0f, (this.avatarImage.getRoundRadiusForExpand() * r0) + ((r5.height() - ((this.avatarContainer.getHeight() - (this.avatarImage.getStoriesInset() * 2.0f)) * r0)) / 2.0f));
    }

    private boolean isRoundRectRing() {
        if (ChatObject.isForum(UserConfig.selectedAccount, this.dialogId) == true) goto L7;
        if (ExteraConfig.getAvatarCorners() < 28.0f) goto L7;
        return false;
    L7:
        if (this.expandProgress >= 0.2f) goto L12;
        return true;
    L12:
        return false;
    }

    private /* synthetic */ void lambda$animateBounce$3(ValueAnimator r2) {
        ProfileActivity.AvatarImageView r0 = this.avatarImage;
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.bounceScale = r22;
        r0.bounceScale = r22;
        this.avatarImage.invalidate();
        invalidate();
    }

    private /* synthetic */ void lambda$animateNewStory$1(boolean[] r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        if (r3[0] == false) goto L5;
    L7:
        this.newStoryBounceT = Math.max(1.0f, r42);
        invalidate();
        return;
    L5:
        if (r42 <= 0.2f) goto L7;
        r3[0] = true;
        vibrateNewStory();
        goto L7
    }

    private /* synthetic */ void lambda$new$4() {
        if (this.expandProgress != 0.0f) goto L6;
        onLongPress();
        return;
    }

    private /* synthetic */ void lambda$vibrateNewStory$0() {
        AndroidUtilities.vibrateCursor(this);
    }

    private void lerpCentered(RectF r3, RectF r4, float r5, RectF r6) {
        float r2 = AndroidUtilities.lerp(r3.centerX(), r4.centerX(), r5);
        float r0 = AndroidUtilities.lerp(r3.centerY(), r4.centerY(), r5);
        float r32 = AndroidUtilities.lerp(Math.min(r3.width(), r3.height()), Math.min(r4.width(), r4.height()), r5) / 2.0f;
        r6.set(r2 - r32, r0 - r32, r2 + r32, r0 + r32);
    }

    private StoryCircle nearest(StoryCircle r3, StoryCircle r4, StoryCircle r5) {
        if (r5 == null) goto L16;
        if (r3 != null) goto L6;
        if (r4 != null) goto L6;
        return null;
    L6:
        if (r3 == null) goto L13;
        if (r4 == null) goto L13;
        if (Math.min(Math.abs(r3.borderRect.left - r5.borderRect.right), Math.abs(r3.borderRect.right - r5.borderRect.left)) <= Math.min(Math.abs(r4.borderRect.left - r5.borderRect.right), Math.abs(r4.borderRect.right - r5.borderRect.left))) goto L12;
        return r3;
    L12:
        return r4;
    L13:
        if (r3 == null) goto L15;
        return r3;
    L15:
        return r4;
    L16:
        return null;
    }

    private void updateStories(boolean r21, boolean r22) {
        if (this.isTopic == false) goto L6;
        return;
    L6:
        if (this.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L8;
        boolean r2 = true;
    L9:
        int r5 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        TL_stories.PeerStories r6 = MessagesController.getInstance(this.currentAccount).getStoriesController().getStoriesFromFullPeer(this.dialogId);
        TL_stories.PeerStories r7 = MessagesController.getInstance(this.currentAccount).getStoriesController().getStories(this.dialogId);
        if (this.dialogId != 0) goto L12;
        TL_stories.PeerStories r8 = null;
    L13:
        if (r6 == null) goto L15;
        int r12 = Math.max(0, r6.max_read_id);
    L16:
        if (r7 == null) goto L18;
        r12 = Math.max(r12, r7.max_read_id);
    L18:
        if (r8 == null) goto L21;
        ArrayList<TL_stories.StoryItem> r13 = r8.stories;
        if (r13 == null) goto L21;
    L22:
        ArrayList r14 = new ArrayList();
        int r15 = this.unreadCount;
        this.unreadCount = 0;
        if (r13 == null) goto L77;
        int r11 = 0;
        int r18 = 0;
    L26:
        if (r11 >= r13.size()) goto L35;
        TL_stories.StoryItem r3 = r13.get(r11);
        if ((r3 instanceof TL_stories.TL_storyItemDeleted) == true) goto L34;
        if (r3.id <= r12) goto L33;
        this.unreadCount++;
    L33:
        r18 = r18 + 1;
    L34:
        r11 = r11 + 1;
        goto L26
    L35:
        int r32 = 0;
    L37:
        if (r32 >= r13.size()) goto L75;
        TL_stories.StoryItem r9 = r13.get(r32);
        if ((r9 instanceof TL_stories.TL_storyItemDeleted) == false) goto L42;
    L74:
        r32 = r32 + 1;
        goto L37
    L42:
        if ((r9 instanceof TL_stories.TL_storyItemSkipped) == false) goto L64;
        int r112 = r9.id;
        if (r7 == null) goto L52;
        int r4 = 0;
    L47:
        if (r4 >= r7.stories.size()) goto L52;
        if (r7.stories.get(r4).id == r112) goto L50;
        r4 = r4 + 1;
        goto L47
    L50:
        r9 = r7.stories.get(r4);
    L52:
        boolean r42 = r9 instanceof TL_stories.TL_storyItemSkipped;
        if (r42 == false) goto L62;
        if (r6 == null) goto L74;
        int r43 = 0;
    L57:
        if (r43 >= r6.stories.size()) goto L74;
        if (r6.stories.get(r43).id == r112) goto L60;
        r43 = r43 + 1;
        goto L57
    L60:
        TL_stories.StoryItem r44 = r6.stories.get(r43);
        goto L74
    L62:
        if (r42 == true) goto L74;
    L64:
        int r45 = r9.expire_date;
        if (r45 == 0) goto L68;
        if (r5 > r45) goto L74;
    L68:
        if (r2 == false) goto L70;
    L71:
        r14.add(r9);
        int r92 = 3;
        if (r14.size() < 3) goto L74;
    L76:
        int r33 = r18;
    L79:
        if (r14.size() >= r92) goto L120;
        int r46 = 0;
    L82:
        if (r46 >= r13.size()) goto L120;
        TL_stories.StoryItem r93 = r13.get(r46);
        if ((r93 instanceof TL_stories.TL_storyItemSkipped) == false) goto L108;
        int r10 = r93.id;
        if (r7 == null) goto L94;
        int r113 = 0;
    L89:
        if (r113 >= r7.stories.size()) goto L94;
        if (r7.stories.get(r113).id == r10) goto L92;
        r113 = r113 + 1;
        goto L89
    L92:
        r93 = r7.stories.get(r113);
    L94:
        boolean r114 = r93 instanceof TL_stories.TL_storyItemSkipped;
        if (r114 == false) goto L105;
        if (r6 == null) goto L119;
        int r94 = 0;
    L99:
        if (r94 >= r6.stories.size()) goto L119;
        if (r6.stories.get(r94).id == r10) goto L102;
        r94 = r94 + 1;
        goto L99
    L102:
        TL_stories.StoryItem r95 = r6.stories.get(r94);
    L119:
        r46 = r46 + 1;
        goto L82
    L105:
        if (r114 == true) goto L119;
    L108:
        if ((r93 instanceof TL_stories.TL_storyItemDeleted) == true) goto L119;
        int r102 = r93.expire_date;
        if (r102 == 0) goto L115;
        if (r5 > r102) goto L119;
    L115:
        if (r14.contains(r93) == true) goto L119;
        r14.add(r93);
        if (r14.size() < 3) goto L119;
    L120:
        int r47 = 0;
    L122:
        if (r47 >= this.circles.size()) goto L146;
        StoryCircle r52 = this.circles.get(r47);
        int r96 = 0;
    L125:
        if (r96 >= r14.size()) goto L130;
        TL_stories.StoryItem r103 = (TL_stories.StoryItem) r14.get(r96);
        if (r103.id == r52.storyId) goto L131;
        r96 = r96 + 1;
    L131:
        if (r96 != (-1)) goto L133;
        r52.scale = 0.0f;
    L143:
        if (r21 == true) goto L145;
        r52.apply();
    L145:
        r47 = r47 + 1;
        goto L122
    L133:
        r52.index = r96;
        if (r2 == true) goto L141;
        if (r8 == null) goto L140;
        if (r103 == null) goto L140;
        if (r103.id <= this.storiesController.getMaxStoriesReadId(this.dialogId)) goto L141;
    L140:
        boolean r62 = false;
    L142:
        r52.read = r62;
    L141:
        r62 = true;
        goto L142
    L130:
        r96 = -1;
        r103 = null;
        goto L131
    L146:
        int r48 = 0;
    L148:
        if (r48 >= r14.size()) goto L171;
        TL_stories.StoryItem r53 = (TL_stories.StoryItem) r14.get(r48);
        int r97 = 0;
    L151:
        if (r97 >= this.circles.size()) goto L156;
        if (this.circles.get(r97).storyId == r53.id) goto L157;
        r97 = r97 + 1;
    L157:
        if (r97 != (-1)) goto L170;
        r53.dialogId = this.dialogId;
        StoryCircle r98 = new StoryCircle(this, r53);
        r98.index = r48;
        r98.scale = 1.0f;
        r98.scaleAnimated.set(0.0f, true);
        if (r2 == true) goto L165;
        if (r8 != null) goto L162;
    L164:
        boolean r115 = false;
    L166:
        r98.read = r115;
        if (r21 == true) goto L169;
        r98.apply();
    L169:
        this.circles.add(r98);
        goto L170
    L162:
        if (r53.id > r8.max_read_id) goto L164;
    L165:
        r115 = true;
    L170:
        r48 = r48 + 1;
        goto L148
    L156:
        r97 = -1;
        goto L157
    L171:
        this.mainCircle = null;
        int r23 = 0;
    L173:
        if (r23 >= this.circles.size()) goto L178;
        StoryCircle r49 = this.circles.get(r23);
        if (r49.scale > 0.0f) goto L176;
        r23 = r23 + 1;
        goto L173
    L176:
        this.mainCircle = r49;
    L178:
        ArrayList<StoriesController.UploadingStory> r24 = this.storiesController.getUploadingStories(this.dialogId);
        if (r24 != null) goto L181;
        int r25 = 0;
    L182:
        this.uploadingStoriesCount = r25;
        int r116 = Math.max(r14.size(), r33);
        if (r116 == 0) goto L185;
    L187:
        if (r22 == false) goto L194;
        if (r21 == false) goto L194;
        boolean r19 = true;
        if (r116 == (this.count + 1)) goto L192;
    L195:
        this.count = r116;
        AnimatedTextView.AnimatedTextDrawable r26 = this.titleDrawable;
        if (r116 <= 0) goto L198;
        boolean r410 = false;
        String r34 = LocaleController.formatPluralString("Stories", r116, new Object[0]);
    L199:
        if (r21 == true) goto L201;
    L203:
        r26.setText(r34, r410);
        long r27 = this.dialogId;
        int r35 = this.currentAccount;
        if (r27 < 0) goto L211;
        TLRPC.User r28 = MessagesController.getInstance(r35).getUser(Long.valueOf(this.dialogId));
        if (r28 == null) goto L210;
        TLRPC.EmojiStatus r36 = r28.emoji_status;
        if ((r36 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L210;
        this.gradientTools.setColor(MessagesController.PeerColor.fromCollectible(r36), r21);
    L217:
        invalidate();
        return;
    L210:
        this.gradientTools.setUser(r28, r21);
        goto L217
    L211:
        TLRPC.Chat r29 = MessagesController.getInstance(r35).getChat(Long.valueOf(-this.dialogId));
        if (r29 == null) goto L216;
        TLRPC.EmojiStatus r37 = r29.emoji_status;
        if ((r37 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L216;
        this.gradientTools.setColor(MessagesController.PeerColor.fromCollectible(r37), r21);
    L216:
        this.gradientTools.setChat(r29, r21);
        goto L217
    L201:
        if (LocaleController.isRTL == true) goto L203;
        r410 = r19;
        goto L203
    L198:
        r410 = false;
        r34 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L199
    L192:
        if (this.unreadCount != (r15 + 1)) goto L195;
        animateNewStory();
    L194:
        r19 = true;
        goto L195
    L185:
        if (this.uploadingStoriesCount == 0) goto L187;
        r116 = 1;
        goto L187
    L181:
        r25 = r24.size();
        goto L182
    L70:
        if (r9.id <= r12) goto L74;
    L75:
        r92 = 3;
        goto L76
    L77:
        r92 = 3;
        r33 = 0;
    L21:
        r13 = new ArrayList();
        goto L22
    L15:
        r12 = 0;
        goto L16
    L12:
        r8 = r6;
        goto L13
    L8:
        r2 = false;
        goto L9
    }

    private void vibrateNewStory() {
        if (SharedConfig.getDevicePerformanceClass() > 0) goto L5;
        return;
    L5:
        AndroidUtilities.vibrateCursor(this);
        AndroidUtilities.runOnUIThread(new ProfileStoriesView$$ExternalSyntheticLambda4(this), 180);
    }

    public void animateNewStory() {
        ValueAnimator r0 = this.newStoryBounce;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        boolean[] r02 = {false};
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.newStoryBounce = r1;
        r1.addUpdateListener(new ProfileStoriesView$$ExternalSyntheticLambda1(this, r02));
        this.newStoryBounce.addListener(new AnonymousClass1(this, r02));
        this.newStoryBounce.setInterpolator(new OvershootInterpolator(3.0f));
        this.newStoryBounce.setDuration(400);
        this.newStoryBounce.setStartDelay(120);
        this.newStoryBounce.start();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.storiesUpdated) goto L6;
        updateStories(true, true);
        return;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r37) {
        ProfileStoriesView r0 = this;
        Canvas r1 = r37;
        float r7 = r0.rightAnimated.set(r0.right);
        float r2 = Utilities.clamp((r0.avatarContainer.getScaleX() - 1.0f) / 0.4f, 1.0f, 0.0f);
        float r3 = AndroidUtilities.lerp(AndroidUtilities.dpf2(4.0f), AndroidUtilities.dpf2(3.5f), r2) * r0.progressToInsets;
        float r4 = r0.avatarContainer.getX() + (r0.avatarContainer.getScaleX() * r3);
        float r5 = r0.avatarContainer.getY() + (r0.avatarContainer.getScaleY() * r3);
        float r32 = r3 * 2.0f;
        r0.rect1.set(r4, r5, ((r0.avatarContainer.getWidth() - r32) * r0.avatarContainer.getScaleX()) + r4, ((r0.avatarContainer.getHeight() - r32) * r0.avatarContainer.getScaleY()) + r5);
        float r11 = r0.left;
        int r33 = 0;
    L4:
        if (r33 >= r0.circles.size()) goto L16;
        StoryCircle r42 = r0.circles.get(r33);
        float r52 = r42.scaleAnimated.set(r42.scale);
        r42.cachedScale = r52;
        if (r52 <= 0.0f) goto L8;
    L10:
        r42.cachedIndex = r42.indexAnimated.set(r42.index);
        r42.cachedRead = r42.readAnimated.set(r42.read);
        if (r33 <= 0) goto L15;
        if (r0.circles.get(r33 - 1).cachedIndex <= r42.cachedIndex) goto L15;
        Collections.sort(r0.circles, new ProfileStoriesView$$ExternalSyntheticLambda0());
    L15:
        r33 = r33 + 1;
        goto L4
    L8:
        if (r42.scale > 0.0f) goto L10;
        r42.destroy();
        r0.circles.remove(r33);
        r33 = r33 - 1;
    L16:
        float r34 = Utilities.clamp(1.0f - (r0.expandProgress / 0.2f), 1.0f, 0.0f);
        boolean r53 = r0.storiesController.isLastUploadingFailed(r0.dialogId);
        boolean r6 = r0.storiesController.hasUploadingStories(r0.dialogId);
        if (r6 == true) goto L23;
        StoriesController.UploadingStory r14 = r0.lastUploadingStory;
        if (r14 == null) goto L23;
        if (r14.canceled == false) goto L23;
        r0.progressWasDrawn = false;
        r0.progressIsDone = false;
        r0.progressToUploading.set(false, true);
    L23:
        if (r6 == false) goto L26;
        if (r53 == true) goto L26;
    L29:
        boolean r62 = true;
    L31:
        float r63 = AndroidUtilities.lerp(0.0f, r0.progressToUploading.set(r62), r0.fragmentTransitionProgress);
        r1.save();
        float r142 = r0.bounceScale;
        r1.scale(r142, r142, r0.rect1.centerX(), r0.rect1.centerY());
        float r143 = AndroidUtilities.lerp(r0.rect1.centerY(), r0.expandY, r0.expandProgress);
        r0.lastUploadingStory = null;
        if (r63 <= 0.0f) goto L69;
        float r19 = 2.0f;
        r0.rect2.set(r0.rect1);
        r0.rect2.inset(-AndroidUtilities.dpf2(3.775f), -AndroidUtilities.dpf2(3.775f));
        Paint r43 = r0.gradientTools.getPaint(r0.rect2);
        if (r0.radialProgress != null) goto L36;
        RadialProgress r8 = new RadialProgress(r0);
        r0.radialProgress = r8;
        r8.setBackground(null, true, false);
    L36:
        RadialProgress r82 = r0.radialProgress;
        if (r0.isRoundRectRing() == false) goto L39;
        float r10 = r0.getRingCornerRadius(r0.rect2);
    L40:
        r82.setRoundRectRadius(r10);
        float r21 = r143;
        if (r0.storiesController.hasUploadingStories(r0.dialogId) == true) goto L43;
    L56:
        float r83 = 1.0f;
    L57:
        r0.radialProgress.setDiff(0);
        int r102 = r43.getAlpha();
        r43.setAlpha((int) ((r102 * r34) * r63));
        r43.setStrokeWidth(AndroidUtilities.dpf2(2.33f));
        r0.radialProgress.setPaint(r43);
        RadialProgress r13 = r0.radialProgress;
        RectF r144 = r0.rect2;
        r13.setProgressRect((int) r144.left, (int) r144.top, (int) r144.right, (int) r144.bottom);
        r0.radialProgress.setProgress(Utilities.clamp(r83, 1.0f, 0.0f), true);
        if (r0.avatarImage.drawAvatar == false) goto L60;
        r0.radialProgress.draw(r1);
    L60:
        r43.setAlpha(r102);
        r0.progressWasDrawn = true;
        boolean r84 = r0.progressIsDone;
        if (r0.radialProgress.getAnimatedProgress() < 0.98f) goto L63;
        boolean r9 = true;
    L64:
        r0.progressIsDone = r9;
        if (r84 == r9) goto L67;
        r0.segmentsCountAnimated.set(r0.count, true);
        r0.segmentsUnreadCountAnimated.set(r0.unreadCount, true);
        r0.animateBounce();
    L67:
        Paint r92 = r43;
    L70:
        float r145 = 12.0f;
        float r15 = 255.0f;
        if (r63 >= 1.0f) goto L158;
        float r12 = Utilities.clamp(1.0f - (r0.expandProgress / 0.2f), 1.0f, 0.0f) * (1.0f - r63);
        float r35 = r0.segmentsCountAnimated.set(r0.count);
        float r16 = r0.segmentsUnreadCountAnimated.set(r0.unreadCount);
        if (r53 == false) goto L80;
        r0.rect2.set(r0.rect1);
        r0.rect2.inset(-AndroidUtilities.dpf2(3.775f), -AndroidUtilities.dpf2(3.775f));
        Paint r22 = StoriesUtilities.getErrorPaint(r0.rect2);
        r22.setStrokeWidth(AndroidUtilities.dp(r19));
        r22.setAlpha((int) (r12 * 255.0f));
        boolean r36 = ChatObject.isForum(UserConfig.selectedAccount, r0.dialogId);
        RectF r44 = r0.rect2;
        if (r36 == false) goto L77;
        float r38 = r44.height() * 0.32f;
        r1.drawRoundRect(r0.rect2, r38, r38, r22);
    L78:
        float r29 = 12.0f;
        float r322 = 255.0f;
        r92 = r92;
    L159:
        r0.getExpandRight();
        if (r0.expandProgress > 0.0f) goto L162;
    L233:
        ProfileStoriesView r132 = r0;
    L234:
        if (r92 == null) goto L236;
        r92.setStrokeWidth(AndroidUtilities.dpf2(2.3f));
    L236:
        r1.restore();
        float r02 = Math.max(0.0f, (r132.expandProgress - 0.5f) * r19);
        if (r02 <= 0.0f) goto L261;
        float r23 = AndroidUtilities.lerp(r132.rect1.right + AndroidUtilities.dp(16.0f), r11 + AndroidUtilities.dp(r29), r132.expandProgress);
        float r39 = AndroidUtilities.lerp(r132.getWidth(), r7, r132.expandProgress);
        float r45 = AndroidUtilities.lerp(r132.rect1.centerY(), r132.cy, r132.expandProgress);
        r132.titleDrawable.setBounds((int) r23, (int) (r45 - AndroidUtilities.dp(18.0f)), (int) r39, (int) (r45 + AndroidUtilities.dp(18.0f)));
        r132.titleDrawable.setAlpha((int) (r02 * r322));
        r132.titleDrawable.draw(r1);
        return;
    L261:
        return;
    L162:
        if (r12 >= 1.0f) goto L233;
        r0.w = 0.0f;
        int r24 = 0;
    L165:
        if (r24 >= r0.circles.size()) goto L167;
        r0.w += AndroidUtilities.dp(14.0f) * r0.circles.get(r24).cachedScale;
        r24 = r24 + 1;
        goto L165
    L167:
        float r25 = 0.0f;
        int r310 = 0;
    L169:
        if (r310 >= r0.circles.size()) goto L171;
        StoryCircle r46 = r0.circles.get(r310);
        float r54 = r46.cachedScale;
        float r64 = r46.cachedRead;
        float r93 = (AndroidUtilities.dp(28.0f) / r19) * r54;
        float r103 = (r0.left + r93) + r25;
        r25 = r25 + (AndroidUtilities.dp(18.0f) * r54);
        float r133 = r103 + r93;
        r11 = Math.max(r11, r133);
        r0.rect2.set(r103 - r93, r21 - r93, r133, r21 + r93);
        r0.lerpCentered(r0.rect1, r0.rect2, r0.expandProgress, r0.rect3);
        r46.cachedRect.set(r0.rect3);
        r46.borderRect.set(r0.rect3);
        float r65 = (-AndroidUtilities.lerp(AndroidUtilities.dpf2(2.66f), AndroidUtilities.lerp(AndroidUtilities.dpf2(1.33f), AndroidUtilities.dpf2(2.33f), r0.expandProgress), r64 * r0.expandProgress)) * r54;
        r46.borderRect.inset(r65, r65);
        r310 = r310 + 1;
        goto L169
    L171:
        r0.readPaint.setColor(ColorUtils.blendARGB(1526726655, -2135178036, r0.expandProgress));
        r0.readPaintAlpha = r0.readPaint.getAlpha();
        Paint r55 = r0.gradientTools.getPaint(r0.rect2);
        r55.setStrokeWidth(AndroidUtilities.lerp(AndroidUtilities.dpf2(2.33f), AndroidUtilities.dpf2(1.5f), r0.expandProgress));
        r0.readPaint.setStrokeWidth(AndroidUtilities.lerp(AndroidUtilities.dpf2(1.125f), AndroidUtilities.dpf2(1.5f), r0.expandProgress));
        r0.livePaint.setStrokeWidth(AndroidUtilities.lerp(AndroidUtilities.dpf2(1.125f), AndroidUtilities.dpf2(1.5f), r0.expandProgress));
        int r26 = 0;
    L173:
        if (r26 >= r0.circles.size()) goto L217;
        StoryCircle r311 = r0.circles.get(r26);
        int r47 = r26 - 2;
        if (r47 < 0) goto L177;
        StoryCircle r48 = r0.circles.get(r47);
    L178:
        int r66 = r26 - 1;
        if (r66 < 0) goto L181;
        StoryCircle r67 = r0.circles.get(r66);
    L182:
        StoryCircle r49 = r0.nearest(r48, r67, r311);
        int r68 = r26 + 1;
        if (r68 >= r0.circles.size()) goto L185;
        StoryCircle r94 = r0.circles.get(r68);
    L186:
        int r27 = r26 + 2;
        if (r27 >= r0.circles.size()) goto L189;
        StoryCircle r28 = r0.circles.get(r27);
    L190:
        StoryCircle r210 = r0.nearest(r94, r28, r311);
        if (r49 != null) goto L193;
    L197:
        if (r210 == null) goto L204;
        if (Math.abs(r210.borderRect.centerX() - r311.borderRect.centerX()) >= Math.abs((r311.borderRect.width() / r19) - (r210.borderRect.width() / r19))) goto L201;
    L202:
        r210 = null;
        goto L204
    L201:
        if (Math.abs(r210.borderRect.centerX() - r311.borderRect.centerX()) > ((r210.borderRect.width() / r19) + (r311.borderRect.width() / r19))) goto L202;
    L204:
        if (r311.cachedRead >= 1.0f) goto L206;
        int r95 = r55.getAlpha();
        r55.setAlpha((int) (((r95 * r311.cachedScale) * (1.0f - r311.cachedRead)) * (1.0f - r12)));
        StoryCircle r352 = r49;
        StoryCircle r410 = r210;
        StoryCircle r211 = r352;
        r0.drawArcs(r1, r211, r311, r410, r55);
        Paint r104 = r55;
        r104.setAlpha(r95);
    L208:
        if (r311.cachedRead > 0.0f) goto L210;
        ProfileStoriesView r134 = r0;
    L216:
        r1 = r37;
        r26 = r68;
        r55 = r104;
        r0 = r134;
        goto L173
    L210:
        if (r311.live == false) goto L213;
        Paint r17 = r0.livePaint;
    L212:
        Paint r56 = r17;
        int r96 = r56.getAlpha();
        r56.setAlpha((int) (((r96 * r311.cachedScale) * r311.cachedRead) * (1.0f - r12)));
        r0.drawArcs(r37, r211, r311, r410, r56);
        r134 = r0;
        r56.setAlpha(r96);
        goto L216
    L213:
        r17 = r0.readPaint;
        goto L212
    L206:
        StoryCircle r105 = r49;
        r410 = r210;
        r211 = r105;
        r104 = r55;
        goto L208
    L193:
        if (Math.abs(r49.borderRect.centerX() - r311.borderRect.centerX()) >= Math.abs((r311.borderRect.width() / r19) - (r49.borderRect.width() / r19))) goto L195;
    L196:
        r49 = null;
        goto L197
    L195:
        if (Math.abs(r49.borderRect.centerX() - r311.borderRect.centerX()) <= ((r49.borderRect.width() / r19) + (r311.borderRect.width() / r19))) goto L197;
    L189:
        r28 = null;
        goto L190
    L185:
        r94 = null;
        goto L186
    L181:
        r67 = null;
        goto L182
    L177:
        r48 = null;
        goto L178
    L217:
        r132 = r0;
        Paint r106 = r55;
        r37.saveLayerAlpha(0.0f, 0.0f, r132.getWidth(), r132.getHeight(), (int) ((r132.expandProgress * r322) * (1.0f - r12)), 31);
        r1 = r37;
        int r03 = r132.circles.size() - 1;
    L218:
        if (r03 < 0) goto L232;
        StoryCircle r212 = r132.circles.get(r03);
        if (r212.imageReceiver.getVisible() == false) goto L231;
        int r312 = r1.getSaveCount();
        int r411 = r03 - 1;
        if (r411 < 0) goto L225;
        StoryCircle r412 = r132.circles.get(r411);
    L226:
        int r57 = r03 - 2;
        if (r57 < 0) goto L229;
        StoryCircle r58 = r132.circles.get(r57);
    L230:
        r132.clipCircle(r1, r212, r132.nearest(r412, r58, r212));
        r212.imageReceiver.setImageCoords(r212.cachedRect);
        r212.imageReceiver.draw(r1);
        r1.restoreToCount(r312);
        goto L231
    L229:
        r58 = null;
        goto L230
    L225:
        r412 = null;
    L231:
        r03 = r03 - 1;
        goto L218
    L232:
        r1.restore();
        r92 = r106;
        goto L234
    L77:
        r1.drawCircle(r44.centerX(), r0.rect2.centerY(), r0.rect2.width() / r19, r22);
        goto L78
    L80:
        if (r0.mainCircle != null) goto L84;
        if (r0.uploadingStoriesCount <= 0) goto L78;
    L84:
        if (r12 <= 0.0f) goto L78;
        r0.rect2.set(r0.rect1);
        r0.rect2.inset(-AndroidUtilities.dpf2(3.775f), -AndroidUtilities.dpf2(3.775f));
        r0.rect3.set(r0.rect1);
        r0.rect3.inset(-AndroidUtilities.dpf2(3.41f), -AndroidUtilities.dpf2(3.41f));
        RectF r413 = r0.rect2;
        RectF r59 = r0.rect3;
        AndroidUtilities.lerp(r413, r59, r2, r59);
        Paint r18 = r92;
        float r414 = AndroidUtilities.lerp(0.0f, (float) ((AndroidUtilities.dpf2(4.23f) / (r0.rect1.width() * 3.141592653589793d)) * 360.0d), Utilities.clamp(r35 - 1.0f, 1.0f, 0.0f) * r12);
        int r85 = Math.min(r0.count, 50);
        float r97 = Math.min(r35, 50.0f);
        if (r85 <= 20) goto L89;
        int r313 = 3;
    L90:
        if (r85 > 1) goto L92;
        r313 = 0;
    L92:
        float r242 = AndroidUtilities.lerp(r313 * 2, r414, r2);
        float r213 = (360.0f - (Math.max(0.0f, r97) * r242)) / Math.max(1.0f, r97);
        r0.readPaint.setColor(ColorUtils.blendARGB(1526726655, 973078528, r0.actionBarProgress));
        r0.readPaintAlpha = r0.readPaint.getAlpha();
        float r314 = (-90.0f) - (r242 / r19);
        int r415 = 0;
        boolean r252 = false;
    L93:
        if (r415 >= r85) goto L101;
        if (r415 >= r0.circles.size()) goto L99;
        if (r0.circles.get(r415).live == false) goto L99;
        r252 = true;
    L99:
        r415 = r415 + 1;
        goto L93
    L101:
        if (r252 == false) goto L109;
        RectF r214 = AndroidUtilities.rectTmp;
        r214.set(r0.rect3);
        r214.inset(-AndroidUtilities.dp(12.0f), -AndroidUtilities.dp(12.0f));
        r1.saveLayerAlpha(r214, 255, 31);
        float r315 = ((r0.newStoryBounceT - 1.0f) / 2.5f) + 1.0f;
        if (r315 == 1.0f) goto L105;
        r1.save();
        r1.scale(r315, r315, r0.rect2.centerX(), r0.rect2.centerY());
    L105:
        int r98 = r0.livePaint.getAlpha();
        r0.livePaint.setAlpha((int) (r98 * r12));
        r214.set(r0.rect3);
        r214.inset(-AndroidUtilities.dp(3.0f), -AndroidUtilities.dp(3.0f));
        r0.livePaint.setStrokeWidth(AndroidUtilities.dpf2(2.5f));
        r0.drawArc(r1, r0.rect3, 0.0f, 360.0f, false, r0.livePaint);
        r0.livePaint.setAlpha(r98);
        if (r315 == 1.0f) goto L108;
        r1.restore();
    L108:
        r29 = r145;
        r322 = r15;
        r92 = r18;
        if (r252 == false) goto L159;
        StoriesUtilities.drawLive(r1, r0.rect3, r12, r0.avatarImage.getImageReceiver().getVisible(), r0.fragmentTransitionProgress);
        r1.restore();
        goto L159
    L109:
        int r416 = 0;
    L110:
        if (r416 >= r85) goto L108;
        float r510 = r416;
        float r292 = r145;
        float r30 = 1.0f - Utilities.clamp(r16 - r510, 1.0f, 0.0f);
        float r31 = 1.0f - Utilities.clamp((r85 - r97) - r510, 1.0f, 0.0f);
        if (r31 >= 0.0f) goto L114;
        float r135 = r213;
        int r342 = r85;
        float r323 = r15;
        int r152 = r416;
    L154:
        r416 = r152 + 1;
        r213 = r135;
        r145 = r292;
        r15 = r323;
        r85 = r342;
        goto L110
    L114:
        if (r416 != 0) goto L116;
        float r511 = ((r0.newStoryBounceT - 1.0f) / 2.5f) + 1.0f;
    L118:
        if (r511 == 1.0f) goto L121;
        r1.save();
        r1.scale(r511, r511, r0.rect2.centerX(), r0.rect2.centerY());
    L121:
        if (r416 < r0.circles.size()) goto L123;
    L126:
        boolean r107 = false;
    L128:
        if (r30 >= 1.0f) goto L138;
        if (r107 == false) goto L131;
        Paint r69 = r0.livePaint;
    L132:
        int r512 = r69.getAlpha();
        r323 = r15;
        r69.setAlpha((int) ((r512 * (1.0f - r30)) * r12));
        if (r107 == false) goto L135;
        float r153 = 3.0f;
    L136:
        r69.setStrokeWidth(AndroidUtilities.dpf2(r153));
        r135 = r213;
        r152 = r416;
        r342 = r85;
        r0.drawArc(r1, r0.rect2, r314, (-r213) * r31, false, r69);
        r69.setAlpha(r512);
    L140:
        if (r30 <= 0.0f) goto L150;
        if (r107 == false) goto L144;
        Paint r110 = r0.livePaint;
    L143:
        Paint r610 = r110;
        int r86 = r610.getAlpha();
        r610.setAlpha((int) ((r86 * r30) * r12));
        if (r107 == false) goto L148;
        float r111 = 3.0f;
    L149:
        r610.setStrokeWidth(AndroidUtilities.dpf2(r111));
        r1 = r37;
        r0.drawArc(r1, r0.rect3, r314, (-r135) * r31, false, r610);
        r610.setAlpha(r86);
    L151:
        if (r511 == 1.0f) goto L153;
        r1.restore();
    L153:
        r314 = r314 - ((r135 * r31) + (r31 * r242));
        goto L154
    L148:
        r111 = 1.5f;
        goto L149
    L144:
        r110 = r0.readPaint;
        goto L143
    L150:
        r1 = r37;
        goto L151
    L135:
        r153 = 2.33f;
        goto L136
    L131:
        r18 = r0.gradientTools.getPaint(r0.rect2);
        r69 = r18;
        goto L132
    L138:
        r135 = r213;
        r342 = r85;
        r323 = r15;
        r152 = r416;
        goto L140
    L123:
        if (r0.circles.get(r416).live == false) goto L126;
        r107 = true;
        goto L128
    L116:
        r511 = 1.0f;
        goto L118
    L89:
        r313 = 5;
        goto L90
    L158:
        r29 = 12.0f;
        r322 = 255.0f;
        r12 = r34;
        goto L159
    L63:
        r9 = false;
        goto L64
    L43:
        if (r0.storiesController.isLastUploadingFailed(r0.dialogId) == true) goto L56;
        ArrayList<StoriesController.UploadingStory> r87 = r0.storiesController.getUploadingStories(r0.dialogId);
        if (r87 != null) goto L48;
        r83 = 0.0f;
        goto L57
    L48:
        if (r87.size() <= 0) goto L50;
        r0.lastUploadingStory = r87.get(0);
    L50:
        float r146 = 0.0f;
        int r136 = 0;
    L52:
        if (r136 >= r87.size()) goto L54;
        r146 = r146 + r87.get(r136).progress;
        r136 = r136 + 1;
        goto L52
    L54:
        r83 = r146 / r87.size();
        goto L57
    L39:
        r10 = -1.0f;
        goto L40
    L69:
        r19 = 2.0f;
        r21 = r143;
        r0.progressWasDrawn = false;
        r92 = null;
    L26:
        if (r0.progressWasDrawn == true) goto L28;
    L30:
        r62 = false;
        goto L31
    L28:
        if (r0.progressIsDone == true) goto L30;
        goto L29
    }

    @Keep
    public float getFragmentTransitionProgress() {
        return this.fragmentTransitionProgress;
    }

    public boolean isEmpty() {
        return this.circles.isEmpty();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        int r0 = 0;
    L4:
        if (r0 >= this.circles.size()) goto L6;
        this.circles.get(r0).imageReceiver.onAttachedToWindow();
        r0 = r0 + 1;
        goto L4
    L6:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesUpdated);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = 0;
        this.attached = false;
    L4:
        if (r0 >= this.circles.size()) goto L6;
        this.circles.get(r0).imageReceiver.onDetachedFromWindow();
        r0 = r0 + 1;
        goto L4
    L6:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesUpdated);
    }

    public void onLongPress() {
    }

    public void onTap(StoryViewer.PlaceProvider r1) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        if (this.expandProgress >= 0.9f) goto L6;
        boolean r0 = this.rect2.contains(r7.getX(), r7.getY());
    L13:
        if (r0 == false) goto L19;
        if (r7.getAction() != 0) goto L19;
        this.tapTime = System.currentTimeMillis();
        this.tapX = r7.getX();
        this.tapY = r7.getY();
        AndroidUtilities.cancelRunOnUIThread(this.onLongPressRunnable);
        AndroidUtilities.runOnUIThread(this.onLongPressRunnable, ViewConfiguration.getLongPressTimeout());
        return true;
    L19:
        if (r7.getAction() != 1) goto L35;
        AndroidUtilities.cancelRunOnUIThread(this.onLongPressRunnable);
        if (r0 == false) goto L38;
        if ((System.currentTimeMillis() - this.tapTime) > ViewConfiguration.getTapTimeout()) goto L38;
        if (MathUtils.distance(this.tapX, this.tapY, r7.getX(), r7.getY()) > AndroidUtilities.dp(12.0f)) goto L38;
        if (this.storiesController.hasUploadingStories(this.dialogId) == false) goto L29;
    L32:
        onTap(this.provider);
        return true;
    L29:
        if (this.storiesController.hasStories(this.dialogId) == true) goto L32;
        if (this.circles.isEmpty() == false) goto L32;
    L38:
        return super.onTouchEvent(r7);
    L35:
        if (r7.getAction() != 3) goto L38;
        this.tapTime = -1;
        AndroidUtilities.cancelRunOnUIThread(this.onLongPressRunnable);
        goto L38
    L6:
        if (r7.getX() >= this.left) goto L8;
    L12:
        r0 = false;
        goto L13
    L8:
        if (r7.getX() > this.right) goto L12;
        if (Math.abs(r7.getY() - this.cy) >= AndroidUtilities.dp(32.0f)) goto L12;
        r0 = true;
        goto L13
    }

    public void setActionBarActionMode(float r2) {
        if (Theme.isCurrentThemeDark() == false) goto L5;
        return;
    L5:
        this.actionBarProgress = r2;
        invalidate();
    }

    public void setBounds(float r4, float r5, float r6, boolean r7) {
        if (Math.abs(r4 - this.left) <= 0.1f) goto L5;
    L10:
        boolean r0 = true;
    L11:
        this.left = r4;
        this.right = r5;
        if (r7 == true) goto L14;
        this.rightAnimated.set(r5, true);
    L14:
        this.cy = r6;
        if (r0 == false) goto L18;
        invalidate();
        return;
    L18:
        return;
    L5:
        if (Math.abs(r5 - this.right) > 0.1f) goto L10;
        if (Math.abs(r6 - this.cy) > 0.1f) goto L10;
        r0 = false;
        goto L11
    }

    public void setExpandCoords(float r1, boolean r2, float r3) {
        this.expandRight = r1;
        this.expandRightPad = r2;
        this.expandY = r3;
        invalidate();
    }

    public void setExpandProgress(float r2) {
        if (this.expandProgress == r2) goto L6;
        this.expandProgress = r2;
        invalidate();
        return;
    }

    @Keep
    public void setFragmentTransitionProgress(float r2) {
        if (this.fragmentTransitionProgress != r2) goto L5;
        return;
    L5:
        this.fragmentTransitionProgress = r2;
        invalidate();
    }

    public void setProgressToStoriesInsets(float r2) {
        if (this.progressToInsets != r2) goto L5;
        return;
    L5:
        this.progressToInsets = r2;
        invalidate();
    }

    public void setStories(TL_stories.PeerStories r2) {
        this.peerStories = r2;
        updateStories(true, false);
    }

    public void update() {
        updateStories(true, true);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.titleDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
