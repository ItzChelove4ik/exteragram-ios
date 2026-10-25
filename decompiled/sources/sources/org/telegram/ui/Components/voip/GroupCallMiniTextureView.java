package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BlobDrawable;
import org.telegram.ui.Components.CrossOutDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.GroupCallFullscreenAdapter;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.voip.GroupCallStatusIcon;
import org.telegram.ui.GroupCallActivity;
import org.webrtc.GlGenericDrawer;
import org.webrtc.RendererCommon;
import org.webrtc.TextureViewRenderer;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class GroupCallMiniTextureView extends FrameLayout implements GroupCallStatusIcon.Callback {
    GroupCallActivity activity;
    boolean animateEnter;
    int animateToColor;
    public boolean animateToFullscreen;
    public boolean animateToScrimView;
    boolean attached;
    ArrayList<GroupCallMiniTextureView> attachedRenderers;
    ImageView blurredFlippingStub;
    ChatObject.Call call;
    private Drawable castingScreenDrawable;
    private boolean checkScale;
    int collapseSize;
    ValueAnimator colorAnimator;
    int currentAccount;
    public boolean drawFirst;
    ValueAnimator flipAnimator;
    boolean flipHalfReached;
    public boolean forceDetached;
    int fullSize;
    Paint gradientPaint;
    LinearGradient gradientShader;
    int gridItemsCount;
    public boolean hasVideo;
    private Runnable hideRunnable;
    ImageReceiver imageReceiver;
    boolean inPinchToZoom;
    FrameLayout infoContainer;
    private boolean invalidateFromChild;
    boolean isFullscreenMode;
    int lastIconColor;
    private boolean lastLandscapeMode;
    private int lastSize;
    int lastSpeakingFrameColor;
    private final RLottieImageView micIconView;
    private final SimpleTextView nameView;
    private Runnable noRtmpStreamCallback;
    private TextView noRtmpStreamTextView;
    ValueAnimator noVideoStubAnimator;
    private NoVideoStubLayout noVideoStubLayout;
    ArrayList<Runnable> onFirstFrameRunnables;
    float overlayIconAlpha;
    GroupCallRenderersContainer parentContainer;
    public ChatObject.VideoParticipant participant;
    private CrossOutDrawable pausedVideoDrawable;
    float pinchCenterX;
    float pinchCenterY;
    float pinchScale;
    float pinchTranslationX;
    float pinchTranslationY;
    private boolean postedNoRtmpStreamCallback;
    public GroupCallGridCell primaryView;
    private float progressToBackground;
    public float progressToNoVideoStub;
    float progressToSpeaking;
    private Rect rect;
    private final ImageView screencastIcon;
    public GroupCallFullscreenAdapter.GroupCallUserCell secondaryView;
    private boolean showingAsScrimView;
    public boolean showingInFullscreen;
    float spanCount;
    Paint speakingPaint;
    private GroupCallStatusIcon statusIcon;
    private TextView stopSharingTextView;
    private boolean swipeToBack;
    private float swipeToBackDy;
    public GroupCallGridCell tabletGridView;
    public VoIPTextureView textureView;
    Bitmap thumb;
    Paint thumbPaint;
    private boolean updateNextLayoutAnimated;
    boolean useSpanSize;
    private boolean videoIsPaused;
    private float videoIsPausedProgress;

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$1, reason: invalid class name */
    public class AnonymousClass1 extends VoIPTextureView {
        float overlayIconAlphaFrom;
        final /* synthetic */ GroupCallMiniTextureView this$0;
        final /* synthetic */ GroupCallActivity val$activity;
        final /* synthetic */ ChatObject.Call val$call;
        final /* synthetic */ StaticLayout val$noVideoLayout;
        final /* synthetic */ GroupCallRenderersContainer val$parentContainer;
        final /* synthetic */ String val$sharingScreenString;
        final /* synthetic */ StaticLayout val$staticLayout;
        final /* synthetic */ TextPaint val$textPaint;
        final /* synthetic */ TextPaint val$textPaint2;
        final /* synthetic */ float val$textW;
        final /* synthetic */ float val$textW3;
        final /* synthetic */ String val$videoOnPauseString;

        /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$1$1, reason: invalid class name and collision with other inner class name */
        public class C00621 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00621(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                if (this.this$1.this$0.blurredFlippingStub.getParent() == null) goto L6;
                GroupCallMiniTextureView r0 = this.this$1.this$0;
                r0.textureView.removeView(r0.blurredFlippingStub);
                return;
            }
        }

        public AnonymousClass1(GroupCallMiniTextureView r1, Context r2, boolean r3, boolean r4, boolean r5, boolean r6, ChatObject.Call r7, GroupCallRenderersContainer r8, TextPaint r9, StaticLayout r10, TextPaint r11, String r12, float r13, StaticLayout r14, GroupCallActivity r15, String r16, float r17) {
            this.this$0 = r1;
            this.val$call = r7;
            this.val$parentContainer = r8;
            this.val$textPaint = r9;
            this.val$noVideoLayout = r10;
            this.val$textPaint2 = r11;
            this.val$sharingScreenString = r12;
            this.val$textW3 = r13;
            this.val$staticLayout = r14;
            this.val$activity = r15;
            this.val$videoOnPauseString = r16;
            this.val$textW = r17;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.voip.VoIPTextureView
        public void animateToLayout() {
            super.animateToLayout();
            this.overlayIconAlphaFrom = this.this$0.overlayIconAlpha;
        }

        @Override // org.telegram.ui.Components.voip.VoIPTextureView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r23) {
            if (this.renderer.isFirstFrameRendered() == false) goto L13;
            if (this.renderer.getAlpha() == 1.0f) goto L9;
            if (this.blurRenderer.getAlpha() != 1.0f) goto L13;
        L9:
            if (GroupCallMiniTextureView.m13829$$Nest$fgetvideoIsPaused(this.this$0) == true) goto L13;
            float r16 = 48.0f;
            float r17 = 2.0f;
            float r18 = 255.0f;
        L114:
            GroupCallMiniTextureView.m13820$$Nest$fgetnoRtmpStreamTextView(this.this$0).setTranslationY((((getMeasuredHeight() - GroupCallMiniTextureView.m13820$$Nest$fgetnoRtmpStreamTextView(this.this$0).getMeasuredHeight()) / r17) + GroupCallMiniTextureView.m13828$$Nest$fgetswipeToBackDy(this.this$0)) - this.currentClipVertical);
            GroupCallMiniTextureView.m13820$$Nest$fgetnoRtmpStreamTextView(this.this$0).setTranslationX(((getMeasuredWidth() - GroupCallMiniTextureView.m13820$$Nest$fgetnoRtmpStreamTextView(this.this$0).getMeasuredWidth()) / r17) - this.currentClipHorizontal);
            ImageView r2 = this.this$0.blurredFlippingStub;
            if (r2 != null) goto L117;
        L119:
            super.dispatchDraw(r23);
            float r22 = (getMeasuredHeight() - this.currentClipVertical) - AndroidUtilities.dp(80.0f);
            if (this.this$0.participant == this.val$call.videoNotAvailableParticipant) goto L132;
            r23.save();
            GroupCallMiniTextureView r3 = this.this$0;
            if (r3.showingInFullscreen == true) goto L126;
            if (r3.animateToFullscreen == true) goto L126;
        L130:
            r23.translate(0.0f, r22);
            r23.drawPaint(this.this$0.gradientPaint);
            r23.restore();
        L126:
            if (GroupCallActivity.isLandscapeMode == true) goto L130;
            if (GroupCallActivity.isTabletMode == true) goto L130;
            float r32 = AndroidUtilities.dp(90.0f);
            GroupCallRenderersContainer r4 = this.val$parentContainer;
            r22 = r22 - ((r32 * r4.progressToFullscreenMode) * (1.0f - r4.progressToHideUi));
        L132:
            if (GroupCallMiniTextureView.m13829$$Nest$fgetvideoIsPaused(this.this$0) == true) goto L136;
            if (GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(this.this$0) != 0.0f) goto L136;
            return;
        L136:
            if (GroupCallMiniTextureView.m13829$$Nest$fgetvideoIsPaused(this.this$0) == false) goto L144;
            if (GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(this.this$0) == 1.0f) goto L144;
            GroupCallMiniTextureView r24 = this.this$0;
            GroupCallMiniTextureView.m13835$$Nest$fputvideoIsPausedProgress(r24, GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(r24) + 0.064f);
            if (GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(this.this$0) <= 1.0f) goto L142;
            GroupCallMiniTextureView.m13835$$Nest$fputvideoIsPausedProgress(this.this$0, 1.0f);
        L151:
            float r25 = GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(this.this$0);
            if (isInAnimation() == false) goto L154;
            float r33 = this.overlayIconAlphaFrom;
            float r42 = this.animationProgress;
            float r34 = (r33 * (1.0f - r42)) + (this.this$0.overlayIconAlpha * r42);
        L155:
            float r26 = r25 * r34;
            if (r26 <= 0.0f) goto L171;
            float r35 = AndroidUtilities.dp(r16);
            float r43 = (getMeasuredWidth() - r35) / r17;
            float r7 = (getMeasuredHeight() - r35) / r17;
            if (this.this$0.participant != this.val$call.videoNotAvailableParticipant) goto L160;
            r7 = r7 - (r35 / 2.5f);
        L160:
            RectF r8 = AndroidUtilities.rectTmp;
            float r72 = r7 + r35;
            r8.set((int) r43, (int) r7, (int) (r43 + r35), (int) r72);
            if (r26 == 1.0f) goto L163;
            r23.saveLayerAlpha(r8, (int) (r26 * r18), 31);
        L164:
            GroupCallMiniTextureView.m13822$$Nest$fgetpausedVideoDrawable(this.this$0).setBounds((int) r8.left, (int) r8.top, (int) r8.right, (int) r8.bottom);
            GroupCallMiniTextureView.m13822$$Nest$fgetpausedVideoDrawable(this.this$0).draw(r23);
            r23.restore();
            float r27 = r26 * this.val$parentContainer.progressToFullscreenMode;
            if (r27 > 0.0f) goto L167;
            return;
        L167:
            if (this.this$0.participant == this.val$call.videoNotAvailableParticipant) goto L173;
            this.val$textPaint.setAlpha((int) (r27 * r18));
            r23.drawText(this.val$videoOnPauseString, (r43 - (this.val$textW / r17)) + (r35 / r17), r72 + AndroidUtilities.dp(16.0f), this.val$textPaint);
            return;
        L173:
            return;
        L163:
            r23.save();
            goto L164
        L171:
            return;
        L154:
            r34 = this.this$0.overlayIconAlpha;
            goto L155
        L142:
            invalidate();
        L144:
            if (GroupCallMiniTextureView.m13829$$Nest$fgetvideoIsPaused(this.this$0) == true) goto L151;
            if (GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(this.this$0) == 0.0f) goto L151;
            GroupCallMiniTextureView r28 = this.this$0;
            GroupCallMiniTextureView.m13835$$Nest$fputvideoIsPausedProgress(r28, GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(r28) - 0.064f);
            if (GroupCallMiniTextureView.m13830$$Nest$fgetvideoIsPausedProgress(this.this$0) >= 0.0f) goto L150;
            GroupCallMiniTextureView.m13835$$Nest$fputvideoIsPausedProgress(this.this$0, 0.0f);
            goto L151
        L150:
            invalidate();
            goto L151
        L117:
            if (r2.getParent() == null) goto L119;
            GroupCallMiniTextureView r29 = this.this$0;
            r29.blurredFlippingStub.setScaleX(r29.textureView.renderer.getScaleX());
            GroupCallMiniTextureView r210 = this.this$0;
            r210.blurredFlippingStub.setScaleY(r210.textureView.renderer.getScaleY());
        L13:
            if (GroupCallMiniTextureView.m13824$$Nest$fgetprogressToBackground(this.this$0) == 1.0f) goto L18;
            GroupCallMiniTextureView r211 = this.this$0;
            GroupCallMiniTextureView.m13834$$Nest$fputprogressToBackground(r211, GroupCallMiniTextureView.m13824$$Nest$fgetprogressToBackground(r211) + 0.10666667f);
            if (GroupCallMiniTextureView.m13824$$Nest$fgetprogressToBackground(this.this$0) <= 1.0f) goto L17;
            GroupCallMiniTextureView.m13834$$Nest$fputprogressToBackground(this.this$0, 1.0f);
            goto L18
        L17:
            invalidate();
        L18:
            GroupCallMiniTextureView r212 = this.this$0;
            if (r212.thumb == null) goto L24;
            r23.save();
            float r213 = this.currentThumbScale;
            r23.scale(r213, r213, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
            GroupCallMiniTextureView r214 = this.this$0;
            if (r214.thumbPaint != null) goto L23;
            r214.thumbPaint = new Paint(1);
            this.this$0.thumbPaint.setFilterBitmap(true);
        L23:
            r23.drawBitmap(this.this$0.thumb, (getMeasuredWidth() - this.this$0.thumb.getWidth()) / 2.0f, (getMeasuredHeight() - this.this$0.thumb.getHeight()) / 2.0f, this.this$0.thumbPaint);
            r23.restore();
        L25:
            GroupCallMiniTextureView r215 = this.this$0;
            ChatObject.VideoParticipant r82 = r215.participant;
            if (r82 != this.val$call.videoNotAvailableParticipant) goto L37;
            if (r215.showingInFullscreen == false) goto L30;
        L31:
            float r216 = AndroidUtilities.dp(48.0f);
            this.val$textPaint.setAlpha(255);
            r23.save();
            r23.translate((((getMeasuredWidth() - r216) / 2.0f) - (AndroidUtilities.dp(400.0f) / 2.0f)) + (r216 / 2.0f), (((getMeasuredHeight() / 2) - r216) + r216) + AndroidUtilities.dp(10.0f));
            this.val$noVideoLayout.draw(r23);
            r23.restore();
        L33:
            if (GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).getVisibility() == 4) goto L35;
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setVisibility(4);
        L35:
            r16 = 48.0f;
            r17 = 2.0f;
            r18 = 255.0f;
        L113:
            invalidate();
            goto L114
        L30:
            if (this.val$parentContainer.inFullscreenMode == true) goto L33;
        L37:
            if (r82.presentation == true) goto L39;
        L109:
            r16 = 48.0f;
            r17 = 2.0f;
            r18 = 255.0f;
            if (GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(r215).getVisibility() == 4) goto L112;
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setVisibility(4);
        L112:
            this.val$activity.cellFlickerDrawable.draw(r23, this.this$0);
            goto L113
        L39:
            if (r82.participant.self == false) goto L109;
            if (GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(r215).getVisibility() == 0) goto L44;
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setVisibility(0);
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setScaleX(1.0f);
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setScaleY(1.0f);
        L44:
            if (this.this$0.drawFirst == false) goto L46;
            float r217 = 0.0f;
        L47:
            int r83 = AndroidUtilities.dp(33.0f);
            GroupCallMiniTextureView r9 = this.this$0;
            if (r9.animateToFullscreen == false) goto L50;
        L61:
            float r84 = r83;
            float r92 = AndroidUtilities.dp(10.0f) + (AndroidUtilities.dp(39.0f) * this.val$parentContainer.progressToFullscreenMode);
        L60:
            int r85 = (int) (r84 + r92);
            int r93 = (getMeasuredWidth() - r85) / 2;
            if (GroupCallMiniTextureView.m13825$$Nest$fgetshowingAsScrimView(this.this$0) == false) goto L65;
        L68:
            float r11 = this.val$parentContainer.progressToScrimView;
        L69:
            GroupCallMiniTextureView r13 = this.this$0;
            if (r13.showingInFullscreen == false) goto L73;
            float r132 = r217;
        L83:
            float r14 = ((getMeasuredHeight() - r85) / 2) - AndroidUtilities.dp(28.0f);
            r16 = 48.0f;
            float r36 = AndroidUtilities.dp(17.0f);
            r18 = 255.0f;
            float r44 = AndroidUtilities.dp(74.0f);
            r17 = 2.0f;
            GroupCallMiniTextureView r73 = this.this$0;
            if (r73.showingInFullscreen == false) goto L86;
        L89:
            float r74 = this.val$parentContainer.progressToFullscreenMode;
        L90:
            int r37 = (int) ((r14 - ((r36 + (r44 * r74)) * r217)) + (AndroidUtilities.dp(17.0f) * r132));
            GroupCallMiniTextureView.m13817$$Nest$fgetcastingScreenDrawable(this.this$0).setBounds(r93, r37, r93 + r85, r37 + r85);
            GroupCallMiniTextureView.m13817$$Nest$fgetcastingScreenDrawable(this.this$0).draw(r23);
            float r38 = this.val$parentContainer.progressToFullscreenMode;
            if (r38 <= 0.0f) goto L93;
        L96:
            float r39 = Math.max(r38, r11) * r217;
            this.val$textPaint2.setAlpha((int) (r39 * 255.0f));
            GroupCallMiniTextureView r218 = this.this$0;
            if (r218.animateToFullscreen == false) goto L99;
        L102:
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(r218).setAlpha(r39 * (1.0f - r11));
        L103:
            r23.drawText(this.val$sharingScreenString, (r93 - (this.val$textW3 / 2.0f)) + (r85 / 2.0f), AndroidUtilities.dp(32.0f) + r10, this.val$textPaint2);
        L104:
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setTranslationY(((AndroidUtilities.dp(72.0f) + r10) + GroupCallMiniTextureView.m13828$$Nest$fgetswipeToBackDy(this.this$0)) - this.currentClipVertical);
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setTranslationX(((getMeasuredWidth() - GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).getMeasuredWidth()) / 2.0f) - this.currentClipHorizontal);
            if (this.val$parentContainer.progressToFullscreenMode >= 1.0f) goto L113;
            if (r11 >= 1.0f) goto L113;
            this.val$textPaint.setAlpha((int) (255.0d * (1.0d - Math.max(r2, r11))));
            r23.save();
            r23.translate((r93 - (AndroidUtilities.dp(400.0f) / 2.0f)) + (r85 / 2.0f), r10 + AndroidUtilities.dp(10.0f));
            this.val$staticLayout.draw(r23);
            r23.restore();
            goto L113
        L99:
            if (r218.showingInFullscreen == true) goto L102;
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(r218).setAlpha(0.0f);
            goto L103
        L93:
            if (r11 > 0.0f) goto L96;
            GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).setAlpha(0.0f);
            goto L104
        L86:
            if (r73.animateToFullscreen == true) goto L89;
            r74 = 0.0f;
            goto L90
        L73:
            if (r13.animateToFullscreen == false) goto L75;
            r217 = this.val$parentContainer.progressToFullscreenMode;
        L77:
            if (GroupCallMiniTextureView.m13825$$Nest$fgetshowingAsScrimView(r13) == false) goto L79;
        L82:
            r132 = this.val$parentContainer.progressToScrimView;
            goto L83
        L79:
            if (this.this$0.animateToScrimView == true) goto L82;
            r132 = this.val$parentContainer.progressToFullscreenMode;
            goto L83
        L75:
            r217 = r11;
            goto L77
        L65:
            if (this.this$0.animateToScrimView == true) goto L68;
            r11 = 0.0f;
            goto L69
        L50:
            if (r9.showingInFullscreen == true) goto L61;
            r84 = r83;
            float r94 = AndroidUtilities.dp(10.0f);
            float r112 = 1.0f - this.val$parentContainer.progressToFullscreenMode;
            if (GroupCallMiniTextureView.m13825$$Nest$fgetshowingAsScrimView(this.this$0) == false) goto L55;
        L58:
            float r133 = this.val$parentContainer.progressToScrimView;
        L59:
            r92 = r94 * Math.max(r112, r133);
            goto L60
        L55:
            if (this.this$0.animateToScrimView == true) goto L58;
            r133 = 0.0f;
            goto L59
        L46:
            r217 = this.val$parentContainer.progressToFullscreenMode;
            goto L47
        L24:
            r212.imageReceiver.setImageCoords(this.currentClipHorizontal, this.currentClipVertical, getMeasuredWidth() - (this.currentClipHorizontal * 2.0f), getMeasuredHeight() - (this.currentClipVertical * 2.0f));
            GroupCallMiniTextureView r219 = this.this$0;
            r219.imageReceiver.setAlpha(GroupCallMiniTextureView.m13824$$Nest$fgetprogressToBackground(r219));
            this.this$0.imageReceiver.draw(r23);
            goto L25
        }

        @Override // org.telegram.ui.Components.voip.VoIPTextureView, android.view.ViewGroup
        public boolean drawChild(Canvas r4, View r5, long r6) {
            GroupCallMiniTextureView r0 = this.this$0;
            if (r0.inPinchToZoom == false) goto L9;
            if (r5 != r0.textureView.renderer) goto L9;
            r4.save();
            GroupCallMiniTextureView r02 = this.this$0;
            float r1 = r02.pinchScale;
            r4.scale(r1, r1, r02.pinchCenterX, r02.pinchCenterY);
            GroupCallMiniTextureView r03 = this.this$0;
            r4.translate(r03.pinchTranslationX, r03.pinchTranslationY);
            boolean r3 = super.drawChild(r4, r5, r6);
            r4.restore();
            return r3;
        L9:
            return super.drawChild(r4, r5, r6);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            GroupCallMiniTextureView.m13832$$Nest$fputinvalidateFromChild(this.this$0, true);
            this.this$0.invalidate();
            GroupCallMiniTextureView.m13832$$Nest$fputinvalidateFromChild(this.this$0, false);
        }

        @Override // org.telegram.ui.Components.voip.VoIPTextureView
        public void onFirstFrameRendered() {
            invalidate();
            ChatObject.Call r0 = this.val$call;
            if (r0 == null) goto L10;
            if (r0.call.rtmp_stream == false) goto L10;
            if (GroupCallMiniTextureView.m13823$$Nest$fgetpostedNoRtmpStreamCallback(this.this$0) == false) goto L10;
            AndroidUtilities.cancelRunOnUIThread(GroupCallMiniTextureView.m13819$$Nest$fgetnoRtmpStreamCallback(this.this$0));
            GroupCallMiniTextureView.m13833$$Nest$fputpostedNoRtmpStreamCallback(this.this$0, false);
            GroupCallMiniTextureView.m13820$$Nest$fgetnoRtmpStreamTextView(this.this$0).animate().cancel();
            GroupCallMiniTextureView.m13820$$Nest$fgetnoRtmpStreamTextView(this.this$0).animate().alpha(0.0f).setDuration(150).start();
            this.this$0.textureView.animate().cancel();
            this.this$0.textureView.animate().alpha(1.0f).setDuration(150).start();
        L10:
            if (GroupCallMiniTextureView.m13829$$Nest$fgetvideoIsPaused(this.this$0) == false) goto L12;
        L14:
            TextureView r02 = this.blurRenderer;
            if (r02 != null) goto L17;
        L19:
            ImageView r03 = this.this$0.blurredFlippingStub;
            if (r03 != null) goto L22;
        L29:
            TextureViewRenderer r04 = this.renderer;
            int r1 = r04.rotatedFrameHeight;
            if (r1 == 0) goto L37;
            int r05 = r04.rotatedFrameWidth;
            if (r05 == 0) goto L38;
            ChatObject.VideoParticipant r2 = this.this$0.participant;
            if (r2 == null) goto L39;
            r2.setAspectRatio(r05, r1, this.val$call);
            return;
        L39:
            return;
        L38:
            return;
        L37:
            return;
        L22:
            if (r03.getParent() == null) goto L29;
            float r06 = this.this$0.blurredFlippingStub.getAlpha();
            GroupCallMiniTextureView r22 = this.this$0;
            if (r06 != 1.0f) goto L27;
            r22.blurredFlippingStub.animate().alpha(0.0f).setDuration(300).setListener(new C00621(this)).start();
            goto L29
        L27:
            if (r22.blurredFlippingStub.getParent() == null) goto L29;
            GroupCallMiniTextureView r07 = this.this$0;
            r07.textureView.removeView(r07.blurredFlippingStub);
            goto L29
        L17:
            if (r02.getAlpha() == 1.0f) goto L19;
            this.blurRenderer.animate().setDuration(300).alpha(1.0f);
            goto L19
        L12:
            if (this.renderer.getAlpha() == 1.0f) goto L14;
            this.renderer.animate().setDuration(300).alpha(1.0f);
            goto L14
        }

        @Override // org.telegram.ui.Components.voip.VoIPTextureView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            GroupCallMiniTextureView r0 = this.this$0;
            if (r0.attached == true) goto L5;
        L24:
            super.onLayout(r3, r4, r5, r6, r7);
            TextureViewRenderer r32 = this.renderer;
            int r42 = r32.rotatedFrameHeight;
            if (r42 == 0) goto L32;
            int r33 = r32.rotatedFrameWidth;
            if (r33 == 0) goto L33;
            ChatObject.VideoParticipant r52 = this.this$0.participant;
            if (r52 == null) goto L34;
            r52.setAspectRatio(r33, r42, this.val$call);
            return;
        L34:
            return;
        L33:
            return;
        L32:
            return;
        L5:
            if (GroupCallMiniTextureView.m13818$$Nest$fgetcheckScale(r0) == false) goto L24;
            TextureViewRenderer r02 = this.renderer;
            if (r02.rotatedFrameHeight == 0) goto L24;
            if (r02.rotatedFrameWidth == 0) goto L24;
            boolean r03 = GroupCallMiniTextureView.m13825$$Nest$fgetshowingAsScrimView(this.this$0);
            GroupCallMiniTextureView r1 = this.this$0;
            if (r03 == false) goto L14;
            VoIPTextureView r04 = r1.textureView;
            r04.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
        L23:
            GroupCallMiniTextureView.m13831$$Nest$fputcheckScale(this.this$0, false);
            goto L24
        L14:
            if (r1.showingInFullscreen == false) goto L17;
            VoIPTextureView r05 = r1.textureView;
            r05.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
            goto L23
        L17:
            if (this.val$parentContainer.inFullscreenMode == false) goto L20;
            VoIPTextureView r06 = r1.textureView;
            r06.scaleType = VoIPTextureView.SCALE_TYPE_FILL;
            goto L23
        L20:
            if (r1.participant.presentation == false) goto L22;
            VoIPTextureView r07 = r1.textureView;
            r07.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
            goto L23
        L22:
            VoIPTextureView r08 = r1.textureView;
            r08.scaleType = VoIPTextureView.SCALE_TYPE_ADAPTIVE;
            goto L23
        }

        @Override // org.telegram.ui.Components.voip.VoIPTextureView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            this.this$0.requestLayout();
            super.requestLayout();
        }

        @Override // org.telegram.ui.Components.voip.VoIPTextureView
        public void updateRendererSize() {
            super.updateRendererSize();
            ImageView r0 = this.this$0.blurredFlippingStub;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getParent() == null) goto L9;
            ViewGroup.LayoutParams r02 = this.this$0.blurredFlippingStub.getLayoutParams();
            r02.width = this.this$0.textureView.renderer.getMeasuredWidth();
            ViewGroup.LayoutParams r03 = this.this$0.blurredFlippingStub.getLayoutParams();
            r03.height = this.this$0.textureView.renderer.getMeasuredHeight();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$2, reason: invalid class name */
    public class AnonymousClass2 implements RendererCommon.RendererEvents {
        final /* synthetic */ GroupCallMiniTextureView this$0;

        public AnonymousClass2(GroupCallMiniTextureView r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            int r0 = 0;
        L3:
            int r1 = this.this$0.onFirstFrameRunnables.size();
            GroupCallMiniTextureView r2 = this.this$0;
            if (r0 >= r1) goto L6;
            AndroidUtilities.cancelRunOnUIThread(r2.onFirstFrameRunnables.get(r0));
            this.this$0.onFirstFrameRunnables.get(r0).run();
            r0 = r0 + 1;
            goto L3
        L6:
            r2.onFirstFrameRunnables.clear();
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$3, reason: invalid class name */
    public class AnonymousClass3 extends TextView {
        final /* synthetic */ GroupCallMiniTextureView this$0;

        public AnonymousClass3(GroupCallMiniTextureView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (Math.abs(GroupCallMiniTextureView.m13827$$Nest$fgetstopSharingTextView(this.this$0).getAlpha() - 1.0f) <= 0.001f) goto L7;
            return false;
        L7:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallMiniTextureView this$0;
        final /* synthetic */ boolean val$full;
        final /* synthetic */ GroupCallMiniTextureView val$viewToRemove;

        public AnonymousClass4(GroupCallMiniTextureView r1, GroupCallMiniTextureView r2, boolean r3) {
            this.this$0 = r1;
            this.val$viewToRemove = r2;
            this.val$full = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$viewToRemove.setScaleX(1.0f);
            this.val$viewToRemove.setScaleY(1.0f);
            this.val$viewToRemove.setAlpha(1.0f);
            if (this.val$full == false) goto L5;
            this.this$0.parentContainer.removeView(this.val$viewToRemove);
            this.this$0.release();
        L5:
            this.val$viewToRemove.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallMiniTextureView this$0;

        public AnonymousClass5(GroupCallMiniTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            GroupCallMiniTextureView r0 = this.this$0;
            r0.animateEnter = false;
            r0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallMiniTextureView this$0;

        public AnonymousClass6(GroupCallMiniTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            GroupCallMiniTextureView r22 = this.this$0;
            if (r22.hasVideo == false) goto L5;
            float r0 = 0.0f;
        L6:
            r22.progressToNoVideoStub = r0;
            GroupCallMiniTextureView.m13821$$Nest$fgetnoVideoStubLayout(r22).setAlpha(this.this$0.progressToNoVideoStub);
            NoVideoStubLayout r23 = GroupCallMiniTextureView.m13821$$Nest$fgetnoVideoStubLayout(this.this$0);
            if (this.this$0.hasVideo == false) goto L9;
            int r02 = 8;
        L10:
            r23.setVisibility(r02);
            this.this$0.textureView.invalidate();
            return;
        L9:
            r02 = 0;
            goto L10
        L5:
            r0 = 1.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallMiniTextureView this$0;
        final /* synthetic */ int val$newColor;
        final /* synthetic */ int val$newSpeakingFrameColor;

        public AnonymousClass7(GroupCallMiniTextureView r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$newColor = r2;
            this.val$newSpeakingFrameColor = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            GroupCallMiniTextureView r22 = this.this$0;
            int r0 = this.val$newColor;
            r22.lastIconColor = r0;
            r22.animateToColor = r0;
            int r02 = this.val$newSpeakingFrameColor;
            r22.lastSpeakingFrameColor = r02;
            r22.speakingPaint.setColor(r02);
            GroupCallMiniTextureView r1 = this.this$0;
            if (r1.progressToSpeaking <= 0.0f) goto L6;
            r1.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallMiniTextureView$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallMiniTextureView this$0;

        public AnonymousClass8(GroupCallMiniTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            GroupCallMiniTextureView r22 = this.this$0;
            r22.flipAnimator = null;
            r22.textureView.setRotationY(0.0f);
            GroupCallMiniTextureView r1 = this.this$0;
            if (r1.flipHalfReached == true) goto L6;
            r1.textureView.renderer.clearImage();
            return;
        }
    }

    public class NoVideoStubLayout extends View {
        float amplitude;
        float animateAmplitudeDiff;
        float animateToAmplitude;
        AvatarDrawable avatarDrawable;
        public ImageReceiver avatarImageReceiver;
        public ImageReceiver backgroundImageReceiver;
        Paint backgroundPaint;
        BlobDrawable bigWaveDrawable;
        private GroupCallActivity.WeavingState currentState;
        float cx;
        float cy;
        int muteButtonState;
        Paint paint;
        private GroupCallActivity.WeavingState prevState;
        float speakingProgress;
        private GroupCallActivity.WeavingState[] states;
        float switchProgress;
        final /* synthetic */ GroupCallMiniTextureView this$0;
        BlobDrawable tinyWaveDrawable;
        float wavesEnter;

        /* renamed from: -$$Nest$mupdateMuteButtonState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13836$$Nest$mupdateMuteButtonState(NoVideoStubLayout r0, boolean r1) {
            r0.updateMuteButtonState(r1);
        }

        public NoVideoStubLayout(GroupCallMiniTextureView r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.avatarImageReceiver = new ImageReceiver();
            this.backgroundImageReceiver = new ImageReceiver();
            this.avatarDrawable = new AvatarDrawable();
            this.paint = new Paint(1);
            this.backgroundPaint = new Paint(1);
            this.wavesEnter = 0.0f;
            this.states = new GroupCallActivity.WeavingState[3];
            this.muteButtonState = -1;
            this.switchProgress = 1.0f;
            this.tinyWaveDrawable = new BlobDrawable(9);
            this.bigWaveDrawable = new BlobDrawable(12);
            this.tinyWaveDrawable.minRadius = AndroidUtilities.dp(76.0f);
            this.tinyWaveDrawable.maxRadius = AndroidUtilities.dp(92.0f);
            this.tinyWaveDrawable.generateBlob();
            this.bigWaveDrawable.minRadius = AndroidUtilities.dp(80.0f);
            this.bigWaveDrawable.maxRadius = AndroidUtilities.dp(95.0f);
            this.bigWaveDrawable.generateBlob();
            this.paint.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_listeningText), Theme.getColor(Theme.key_voipgroup_speakingText), this.speakingProgress));
            this.paint.setAlpha(102);
            this.backgroundPaint.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
        }

        private void updateMuteButtonState(boolean r10) {
            if (GroupCallMiniTextureView.m13826$$Nest$fgetstatusIcon(this.this$0).isMutedByMe() == false) goto L5;
        L11:
            int r0 = 2;
        L13:
            if (r0 != this.muteButtonState) goto L15;
            return;
        L15:
            this.muteButtonState = r0;
            GroupCallActivity.WeavingState[] r3 = this.states;
            if (r3[r0] != null) goto L24;
            r3[r0] = new GroupCallActivity.WeavingState(r0);
            int r02 = this.muteButtonState;
            if (r02 != 2) goto L20;
            this.states[r02].shader = new LinearGradient(0.0f, 400.0f, 400.0f, 0.0f, new int[]{Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient), Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3), Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient2)}, null, Shader.TileMode.CLAMP);
            goto L24
        L20:
            GroupCallActivity.WeavingState[] r1 = this.states;
            if (r02 != 1) goto L23;
            r1[r02].shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{Theme.getColor(Theme.key_voipgroup_muteButton), Theme.getColor(Theme.key_voipgroup_muteButton3)}, null, Shader.TileMode.CLAMP);
            goto L24
        L23:
            r1[r02].shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{Theme.getColor(Theme.key_voipgroup_unmuteButton2), Theme.getColor(Theme.key_voipgroup_unmuteButton)}, null, Shader.TileMode.CLAMP);
        L24:
            GroupCallActivity.WeavingState r03 = this.states[this.muteButtonState];
            GroupCallActivity.WeavingState r12 = this.currentState;
            if (r03 == r12) goto L32;
            this.prevState = r12;
            this.currentState = r03;
            if (r12 == null) goto L31;
            if (r10 == false) goto L31;
            this.switchProgress = 0.0f;
        L31:
            this.switchProgress = 1.0f;
            this.prevState = null;
        L32:
            invalidate();
            return;
        L5:
            if (GroupCallMiniTextureView.m13826$$Nest$fgetstatusIcon(this.this$0).isMutedByAdmin() == true) goto L11;
            if (GroupCallMiniTextureView.m13826$$Nest$fgetstatusIcon(this.this$0).isSpeaking() == false) goto L10;
            r0 = 1;
            goto L13
        L10:
            r0 = 0;
            goto L13
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.avatarImageReceiver.onAttachedToWindow();
            this.backgroundImageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.avatarImageReceiver.onDetachedFromWindow();
            this.backgroundImageReceiver.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            super.onDraw(r12);
            RectF r0 = AndroidUtilities.rectTmp;
            float r1 = this.this$0.textureView.getX();
            VoIPTextureView r2 = this.this$0.textureView;
            float r13 = r1 + r2.currentClipHorizontal;
            float r22 = r2.getY();
            VoIPTextureView r3 = this.this$0.textureView;
            float r23 = r22 + r3.currentClipVertical;
            float r32 = r3.getX() + this.this$0.textureView.getMeasuredWidth();
            VoIPTextureView r4 = this.this$0.textureView;
            r0.set(r13, r23, r32 - r4.currentClipHorizontal, (r4.getY() + this.this$0.textureView.getMeasuredHeight()) + this.this$0.textureView.currentClipVertical);
            this.backgroundImageReceiver.setImageCoords(r0.left, r0.top, r0.width(), r0.height());
            this.backgroundImageReceiver.setRoundRadius((int) this.this$0.textureView.roundRadius);
            this.backgroundImageReceiver.draw(r12);
            float r14 = this.this$0.textureView.roundRadius;
            r12.drawRoundRect(r0, r14, r14, this.backgroundPaint);
            float r02 = this.animateToAmplitude;
            float r15 = this.amplitude;
            if (r02 == r15) goto L12;
            float r24 = this.animateAmplitudeDiff;
            float r16 = r15 + (16.0f * r24);
            this.amplitude = r16;
            if (r24 <= 0.0f) goto L10;
            if (r16 <= r02) goto L12;
            this.amplitude = r02;
            goto L12
        L10:
            if (r16 >= r02) goto L12;
            this.amplitude = r02;
        L12:
            float r03 = this.switchProgress;
            if (r03 != 1.0f) goto L15;
        L20:
            float r25 = (this.amplitude * 0.8f) + 1.0f;
            r12.save();
            r12.scale(r25, r25, this.cx, this.cy);
            GroupCallActivity.WeavingState r42 = this.currentState;
            if (r42 == null) goto L23;
            r42.update((int) (this.cy - AndroidUtilities.dp(100.0f)), (int) (this.cx - AndroidUtilities.dp(100.0f)), AndroidUtilities.dp(200.0f), 16, this.amplitude);
        L23:
            this.bigWaveDrawable.update(this.amplitude, 1.0f);
            this.tinyWaveDrawable.update(this.amplitude, 1.0f);
            int r04 = 0;
        L25:
            if (r04 >= 2) goto L37;
            if (r04 != 0) goto L31;
            GroupCallActivity.WeavingState r26 = this.prevState;
            if (r26 == null) goto L31;
            this.paint.setShader(r26.shader);
            float r27 = 1.0f - this.switchProgress;
        L35:
            this.paint.setAlpha((int) (r27 * 76.0f));
            this.bigWaveDrawable.draw(this.cx, this.cy, r12, this.paint);
            this.tinyWaveDrawable.draw(this.cx, this.cy, r12, this.paint);
        L36:
            r04 = r04 + 1;
        L31:
            if (r04 != 1) goto L36;
            GroupCallActivity.WeavingState r28 = this.currentState;
            if (r28 == null) goto L36;
            this.paint.setShader(r28.shader);
            r27 = this.switchProgress;
            goto L35
        L37:
            r12.restore();
            float r29 = (this.amplitude * 0.2f) + 1.0f;
            r12.save();
            r12.scale(r29, r29, this.cx, this.cy);
            this.avatarImageReceiver.draw(r12);
            r12.restore();
            invalidate();
            return;
        L15:
            if (this.prevState == null) goto L18;
            this.switchProgress = r03 + 0.07272727f;
        L18:
            if (this.switchProgress < 1.0f) goto L20;
            this.switchProgress = 1.0f;
            this.prevState = null;
            goto L20
        }

        @Override // android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            float r32 = AndroidUtilities.dp(157.0f);
            this.cx = getMeasuredWidth() >> 1;
            float r42 = getMeasuredHeight() >> 1;
            if (GroupCallActivity.isLandscapeMode == false) goto L5;
            float r0 = 0.0f;
        L6:
            this.cy = r42 + r0;
            float r02 = r32 / 2.0f;
            this.avatarImageReceiver.setRoundRadius((int) r02);
            this.avatarImageReceiver.setImageCoords(this.cx - r02, this.cy - r02, r32, r32);
            return;
        L5:
            r0 = (-getMeasuredHeight()) * 0.12f;
            goto L6
        }

        public void setAmplitude(double r2) {
            float r22 = ((float) r2) / 80.0f;
            float r3 = 1.0f;
            if (r22 <= 1.0f) goto L5;
        L4:
            r22 = r3;
        L8:
            this.animateToAmplitude = r22;
            this.animateAmplitudeDiff = (r22 - this.amplitude) / 200.0f;
            return;
        L5:
            r3 = 0.0f;
            if (r22 >= 0.0f) goto L8;
            goto L8
        }
    }

    public static /* synthetic */ void $r8$lambda$EoeGZKcHNOuo9KbOjf4LvgcUt8g(GroupCallMiniTextureView r0, int r1, int r2, int r3, int r4, ValueAnimator r5) {
        r0.lambda$updateIconColor$6(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$EsfMw3ZEf7AjCYd3MQSZ_8338c4(GroupCallMiniTextureView r0, boolean r1, View r2) {
        r0.lambda$updateAttachState$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$SFfpU_74IHkHXLIDuMxojlKtNoY(GroupCallMiniTextureView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$VC38PU6xI6pRkB5wefZjnvo0kvY(GroupCallMiniTextureView r0, ValueAnimator r1) {
        r0.lambda$startFlipAnimation$7(r1);
    }

    /* renamed from: $r8$lambda$hL16xp0fAuzBdHTZ-Dhj8Xgf-GY, reason: not valid java name */
    public static /* synthetic */ void m13816$r8$lambda$hL16xp0fAuzBdHTZDhj8XgfGY(GroupCallMiniTextureView r0, ValueAnimator r1) {
        r0.lambda$updateAttachState$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$rGVlvWM_XY_M3iHRps3MEGJvDvc(GroupCallMiniTextureView r0, Bitmap r1) {
        r0.lambda$saveThumb$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$tKS1RRr7TPFHU42xlfUSeIha4R4(GroupCallMiniTextureView r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$truy39HWBXNbRJaLDlDIWloaTa0(GroupCallMiniTextureView r0, Bitmap r1, int r2) {
        r0.lambda$saveThumb$5(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcastingScreenDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m13817$$Nest$fgetcastingScreenDrawable(GroupCallMiniTextureView r0) {
        return r0.castingScreenDrawable;
    }

    /* renamed from: -$$Nest$fgetcheckScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13818$$Nest$fgetcheckScale(GroupCallMiniTextureView r0) {
        return r0.checkScale;
    }

    /* renamed from: -$$Nest$fgetnoRtmpStreamCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13819$$Nest$fgetnoRtmpStreamCallback(GroupCallMiniTextureView r0) {
        return r0.noRtmpStreamCallback;
    }

    /* renamed from: -$$Nest$fgetnoRtmpStreamTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m13820$$Nest$fgetnoRtmpStreamTextView(GroupCallMiniTextureView r0) {
        return r0.noRtmpStreamTextView;
    }

    /* renamed from: -$$Nest$fgetnoVideoStubLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NoVideoStubLayout m13821$$Nest$fgetnoVideoStubLayout(GroupCallMiniTextureView r0) {
        return r0.noVideoStubLayout;
    }

    /* renamed from: -$$Nest$fgetpausedVideoDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CrossOutDrawable m13822$$Nest$fgetpausedVideoDrawable(GroupCallMiniTextureView r0) {
        return r0.pausedVideoDrawable;
    }

    /* renamed from: -$$Nest$fgetpostedNoRtmpStreamCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13823$$Nest$fgetpostedNoRtmpStreamCallback(GroupCallMiniTextureView r0) {
        return r0.postedNoRtmpStreamCallback;
    }

    /* renamed from: -$$Nest$fgetprogressToBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13824$$Nest$fgetprogressToBackground(GroupCallMiniTextureView r0) {
        return r0.progressToBackground;
    }

    /* renamed from: -$$Nest$fgetshowingAsScrimView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13825$$Nest$fgetshowingAsScrimView(GroupCallMiniTextureView r0) {
        return r0.showingAsScrimView;
    }

    /* renamed from: -$$Nest$fgetstatusIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallStatusIcon m13826$$Nest$fgetstatusIcon(GroupCallMiniTextureView r0) {
        return r0.statusIcon;
    }

    /* renamed from: -$$Nest$fgetstopSharingTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m13827$$Nest$fgetstopSharingTextView(GroupCallMiniTextureView r0) {
        return r0.stopSharingTextView;
    }

    /* renamed from: -$$Nest$fgetswipeToBackDy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13828$$Nest$fgetswipeToBackDy(GroupCallMiniTextureView r0) {
        return r0.swipeToBackDy;
    }

    /* renamed from: -$$Nest$fgetvideoIsPaused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13829$$Nest$fgetvideoIsPaused(GroupCallMiniTextureView r0) {
        return r0.videoIsPaused;
    }

    /* renamed from: -$$Nest$fgetvideoIsPausedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13830$$Nest$fgetvideoIsPausedProgress(GroupCallMiniTextureView r0) {
        return r0.videoIsPausedProgress;
    }

    /* renamed from: -$$Nest$fputcheckScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13831$$Nest$fputcheckScale(GroupCallMiniTextureView r0, boolean r1) {
        r0.checkScale = r1;
    }

    /* renamed from: -$$Nest$fputinvalidateFromChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13832$$Nest$fputinvalidateFromChild(GroupCallMiniTextureView r0, boolean r1) {
        r0.invalidateFromChild = r1;
    }

    /* renamed from: -$$Nest$fputpostedNoRtmpStreamCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13833$$Nest$fputpostedNoRtmpStreamCallback(GroupCallMiniTextureView r0, boolean r1) {
        r0.postedNoRtmpStreamCallback = r1;
    }

    /* renamed from: -$$Nest$fputprogressToBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13834$$Nest$fputprogressToBackground(GroupCallMiniTextureView r0, float r1) {
        r0.progressToBackground = r1;
    }

    /* renamed from: -$$Nest$fputvideoIsPausedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13835$$Nest$fputvideoIsPausedProgress(GroupCallMiniTextureView r0, float r1) {
        r0.videoIsPausedProgress = r1;
    }

    public GroupCallMiniTextureView(GroupCallRenderersContainer r30, ArrayList<GroupCallMiniTextureView> r31, ChatObject.Call r32, GroupCallActivity r33) {
        super(r30.getContext());
        this.gradientPaint = new Paint(1);
        this.speakingPaint = new Paint(1);
        this.progressToNoVideoStub = 1.0f;
        this.imageReceiver = new ImageReceiver();
        this.onFirstFrameRunnables = new ArrayList();
        this.noRtmpStreamCallback = new GroupCallMiniTextureView$$ExternalSyntheticLambda5(this);
        this.rect = new Rect();
        this.call = r32;
        this.currentAccount = r33.getCurrentAccount();
        CrossOutDrawable r0 = new CrossOutDrawable(r30.getContext(), R.drawable.calls_video, -1);
        this.pausedVideoDrawable = r0;
        r0.setCrossOut(true, false);
        this.pausedVideoDrawable.setOffsets(-AndroidUtilities.dp(4.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        this.pausedVideoDrawable.setStrokeWidth(AndroidUtilities.dpf2(3.4f));
        this.castingScreenDrawable = r30.getContext().getResources().getDrawable(R.drawable.screencast_big).mutate();
        TextPaint r10 = new TextPaint(1);
        r10.setTypeface(AndroidUtilities.bold());
        r10.setTextSize(AndroidUtilities.dp(13.0f));
        r10.setColor(-1);
        TextPaint r02 = new TextPaint(1);
        r02.setTypeface(AndroidUtilities.bold());
        r02.setTextSize(AndroidUtilities.dp(15.0f));
        r02.setColor(-1);
        String r6 = LocaleController.getString(R.string.VoipVideoOnPause);
        String r9 = LocaleController.getString(R.string.VoipVideoScreenSharingTwoLines);
        int r11 = AndroidUtilities.dp(400.0f);
        Layout.Alignment r12 = Layout.Alignment.ALIGN_CENTER;
        StaticLayout r14 = new StaticLayout(r9, r10, r11, r12, 1.0f, 0.0f, false);
        TLRPC.Chat r8 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r32.chatId));
        StaticLayout r82 = new StaticLayout(LocaleController.formatString("VoipVideoNotAvailable", R.string.VoipVideoNotAvailable, new Object[]{LocaleController.formatPluralString("Participants", MessagesController.getInstance(this.currentAccount).groupCallVideoMaxParticipants, new Object[0])}), r10, AndroidUtilities.dp(400.0f), r12, 1.0f, 0.0f, false);
        String r122 = LocaleController.getString(R.string.VoipVideoScreenSharing);
        float r17 = r10.measureText(r6);
        AnonymousClass1 r03 = new AnonymousClass1(this, r30.getContext(), false, false, true, true, r32, r30, r10, r82, r02, r122, r02.measureText(r122), r14, r33, r6, r17);
        this.textureView = r03;
        r03.renderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT);
        this.parentContainer = r30;
        this.attachedRenderers = r31;
        this.activity = r33;
        this.textureView.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass2(this));
        this.textureView.attachBackgroundRenderer();
        setClipChildren(false);
        this.textureView.renderer.setAlpha(0.0f);
        addView(this.textureView);
        NoVideoStubLayout r2 = new NoVideoStubLayout(this, getContext());
        this.noVideoStubLayout = r2;
        addView(r2);
        SimpleTextView r22 = new SimpleTextView(r30.getContext());
        this.nameView = r22;
        r22.setTextSize(13);
        r22.setTextColor(ColorUtils.setAlphaComponent(-1, TLRPC.LAYER));
        r22.setTypeface(AndroidUtilities.bold());
        r22.setFullTextMaxLines(1);
        r22.setBuildFullLayout(true);
        FrameLayout r4 = new FrameLayout(r30.getContext());
        this.infoContainer = r4;
        r4.addView(r22, LayoutHelper.createFrame(-1, -2.0f, 19, 32.0f, 0.0f, 8.0f, 0.0f));
        addView(this.infoContainer, LayoutHelper.createFrame(-1, 32.0f));
        this.speakingPaint.setStyle(Paint.Style.STROKE);
        this.speakingPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.speakingPaint.setColor(Theme.getColor(Theme.key_voipgroup_speakingText));
        this.infoContainer.setClipChildren(false);
        RLottieImageView r23 = new RLottieImageView(r30.getContext());
        this.micIconView = r23;
        addView(r23, LayoutHelper.createFrame(24, 24.0f, 0, 4.0f, 6.0f, 4.0f, 0.0f));
        ImageView r24 = new ImageView(r30.getContext());
        this.screencastIcon = r24;
        addView(r24, LayoutHelper.createFrame(24, 24.0f, 0, 4.0f, 6.0f, 4.0f, 0.0f));
        r24.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r24.setImageDrawable(ContextCompat.getDrawable(r30.getContext(), R.drawable.voicechat_screencast));
        r24.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        Drawable r25 = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(19.0f), 0, ColorUtils.setAlphaComponent(-1, 100));
        AnonymousClass3 r42 = new AnonymousClass3(this, r30.getContext());
        this.stopSharingTextView = r42;
        r42.setText(LocaleController.getString(R.string.VoipVideoScreenStopSharing));
        this.stopSharingTextView.setTextSize(1, 15.0f);
        this.stopSharingTextView.setTypeface(AndroidUtilities.bold());
        this.stopSharingTextView.setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(21.0f), 0);
        this.stopSharingTextView.setTextColor(-1);
        this.stopSharingTextView.setBackground(r25);
        this.stopSharingTextView.setGravity(17);
        this.stopSharingTextView.setOnClickListener(new GroupCallMiniTextureView$$ExternalSyntheticLambda6(this));
        addView(this.stopSharingTextView, LayoutHelper.createFrame(-2, 38, 51));
        TextView r43 = new TextView(r30.getContext());
        this.noRtmpStreamTextView = r43;
        r43.setTextSize(1, 15.0f);
        this.noRtmpStreamTextView.setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(21.0f), 0);
        this.noRtmpStreamTextView.setTextColor(Theme.getColor(Theme.key_voipgroup_lastSeenText));
        this.noRtmpStreamTextView.setBackground(r25);
        this.noRtmpStreamTextView.setGravity(17);
        this.noRtmpStreamTextView.setAlpha(0.0f);
        boolean r04 = ChatObject.canManageCalls(r8);
        TextView r26 = this.noRtmpStreamTextView;
        if (r04 == false) goto L5;
        r26.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.NoRtmpStreamFromAppOwner)));
    L6:
        addView(this.noRtmpStreamTextView, LayoutHelper.createFrame(-2, -2, 51));
        return;
    L5:
        r26.setText(AndroidUtilities.replaceTags(LocaleController.formatString("NoRtmpStreamFromAppViewer", R.string.NoRtmpStreamFromAppViewer, new Object[]{r8.title})));
        goto L6
    }

    public static GroupCallMiniTextureView getOrCreate(ArrayList<GroupCallMiniTextureView> r2, GroupCallRenderersContainer r3, GroupCallGridCell r4, GroupCallFullscreenAdapter.GroupCallUserCell r5, GroupCallGridCell r6, ChatObject.VideoParticipant r7, ChatObject.Call r8, GroupCallActivity r9) {
        int r0 = 0;
    L4:
        if (r0 >= r2.size()) goto L9;
        if (r7.equals(r2.get(r0).participant) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        GroupCallMiniTextureView r72 = r2.get(r0);
    L10:
        if (r72 != null) goto L12;
        r72 = new GroupCallMiniTextureView(r3, r2, r8, r9);
    L12:
        if (r4 == null) goto L14;
        r72.setPrimaryView(r4);
    L14:
        if (r5 == null) goto L16;
        r72.setSecondaryView(r5);
    L16:
        if (r6 == null) goto L18;
        r72.setTabletGridView(r6);
    L18:
        return r72;
    L9:
        r72 = null;
        goto L10
    }

    private /* synthetic */ void lambda$new$0() {
        if (this.textureView.renderer.isFirstFrameRendered() == false) goto L5;
        return;
    L5:
        this.textureView.animate().cancel();
        this.textureView.animate().alpha(0.0f).setDuration(150).start();
        this.noRtmpStreamTextView.animate().cancel();
        this.noRtmpStreamTextView.animate().alpha(1.0f).setDuration(150).start();
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        if (VoIPService.getSharedInstance() == null) goto L5;
        VoIPService.getSharedInstance().stopScreenCapture();
    L5:
        this.stopSharingTextView.animate().alpha(0.0f).scaleX(0.0f).scaleY(0.0f).setDuration(180).start();
    }

    private /* synthetic */ void lambda$saveThumb$4(Bitmap r3) {
        HashMap<String, Bitmap> r0 = this.call.thumbs;
        ChatObject.VideoParticipant r2 = this.participant;
        boolean r1 = r2.presentation;
        TLRPC.GroupCallParticipant r22 = r2.participant;
        if (r1 == false) goto L5;
        String r23 = r22.presentationEndpoint;
    L6:
        r0.put(r23, r3);
        return;
    L5:
        r23 = r22.videoEndpoint;
        goto L6
    }

    private /* synthetic */ void lambda$saveThumb$5(Bitmap r2, int r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.getPixel(0, 0) == 0) goto L8;
        Utilities.stackBlurBitmap(r2, Math.max(7, Math.max(r2.getWidth(), r2.getHeight()) / 180));
        AndroidUtilities.runOnUIThread(new GroupCallMiniTextureView$$ExternalSyntheticLambda7(this, r2));
        return;
    }

    private /* synthetic */ void lambda$startFlipAnimation$7(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        if (r42 >= 0.5f) goto L5;
        boolean r0 = false;
    L6:
        if (r0 == true) goto L8;
    L10:
        float r43 = r42 * 180.0f;
        this.blurredFlippingStub.setRotationY(r43);
        this.textureView.renderer.setRotationY(r43);
        return;
    L8:
        if (this.flipHalfReached == true) goto L10;
        this.blurredFlippingStub.setAlpha(1.0f);
        this.flipHalfReached = true;
        this.textureView.renderer.clearImage();
        goto L10
    L5:
        r42 = r42 - 1.0f;
        r0 = true;
        goto L6
    }

    private /* synthetic */ void lambda$updateAttachState$2(boolean r1, View r2) {
        if (r1 == false) goto L4;
        this.parentContainer.removeView(r2);
    L4:
        r2.setVisibility(8);
        this.hideRunnable = null;
    }

    private /* synthetic */ void lambda$updateAttachState$3(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.progressToNoVideoStub = r22;
        this.noVideoStubLayout.setAlpha(r22);
        this.textureView.invalidate();
    }

    private /* synthetic */ void lambda$updateIconColor$6(int r1, int r2, int r3, int r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.lastIconColor = ColorUtils.blendARGB(r1, r2, r52);
        int r12 = ColorUtils.blendARGB(r3, r4, r52);
        this.lastSpeakingFrameColor = r12;
        this.speakingPaint.setColor(r12);
        if (this.progressToSpeaking <= 0.0f) goto L6;
        invalidate();
        return;
    }

    private void loadThumb() {
        if (this.thumb != null) goto L32;
        HashMap<String, Bitmap> r1 = this.call.thumbs;
        ChatObject.VideoParticipant r2 = this.participant;
        boolean r3 = r2.presentation;
        TLRPC.GroupCallParticipant r22 = r2.participant;
        if (r3 == false) goto L8;
        String r23 = r22.presentationEndpoint;
    L9:
        Bitmap r12 = r1.get(r23);
        this.thumb = r12;
        this.textureView.setThumb(r12);
        if (this.thumb != null) goto L31;
        long r13 = MessageObject.getPeerId(this.participant.participant.peer);
        ChatObject.VideoParticipant r32 = this.participant;
        if (r32.participant.self == true) goto L14;
    L17:
        int r4 = this.currentAccount;
        if (r13 <= 0) goto L25;
        TLRPC.User r15 = MessagesController.getInstance(r4).getUser(Long.valueOf(r13));
        ImageLocation r11 = ImageLocation.getForUser(this.currentAccount, r15, 1);
        if (r15 == null) goto L22;
        int r14 = AvatarDrawable.getColorForId(r15.id);
    L23:
        GradientDrawable r132 = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{ColorUtils.blendARGB(r14, -16777216, 0.2f), ColorUtils.blendARGB(r14, -16777216, 0.4f)});
        this.imageReceiver.setImage(r11, "50_50_b", r132, null, r15, 0);
        return;
    L22:
        r14 = ColorUtils.blendARGB(-16777216, -1, 0.2f);
        goto L23
    L25:
        TLRPC.Chat r152 = MessagesController.getInstance(r4).getChat(Long.valueOf(-r13));
        ImageLocation r112 = ImageLocation.getForChat(this.currentAccount, r152, 1);
        if (r152 == null) goto L28;
        int r16 = AvatarDrawable.getColorForId(r152.id);
    L29:
        GradientDrawable r133 = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{ColorUtils.blendARGB(r16, -16777216, 0.2f), ColorUtils.blendARGB(r16, -16777216, 0.4f)});
        this.imageReceiver.setImage(r112, "50_50_b", r133, null, r152, 0);
        return;
    L28:
        r16 = ColorUtils.blendARGB(-16777216, -1, 0.2f);
        goto L29
    L14:
        if (r32.presentation == false) goto L17;
        this.imageReceiver.setImageBitmap(new MotionBackgroundDrawable(-14602694, -13935795, -14395293, -14203560, true));
        return;
    L31:
        return;
    L8:
        r23 = r22.videoEndpoint;
        goto L9
    }

    private void updateIconColor(boolean r9) {
        GroupCallStatusIcon r0 = this.statusIcon;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isMutedByMe() == false) goto L10;
        int r02 = Theme.getColor(Theme.key_voipgroup_mutedByAdminIcon);
    L8:
        int r5 = r02;
        int r7 = r5;
    L14:
        if (this.animateToColor != r5) goto L16;
        return;
    L16:
        ValueAnimator r03 = this.colorAnimator;
        if (r03 == null) goto L19;
        r03.removeAllListeners();
        this.colorAnimator.cancel();
    L19:
        if (r9 == true) goto L22;
        Paint r92 = this.speakingPaint;
        this.lastSpeakingFrameColor = r7;
        r92.setColor(r7);
        return;
    L22:
        int r4 = this.lastIconColor;
        int r6 = this.lastSpeakingFrameColor;
        this.animateToColor = r5;
        ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.colorAnimator = r93;
        r93.addUpdateListener(new GroupCallMiniTextureView$$ExternalSyntheticLambda8(this, r4, r5, r6, r7));
        this.colorAnimator.addListener(new AnonymousClass7(this, r5, r7));
        this.colorAnimator.start();
        return;
    L10:
        if (this.statusIcon.isSpeaking() == false) goto L12;
        r02 = Theme.getColor(Theme.key_voipgroup_speakingText);
        goto L8
    L12:
        r7 = Theme.getColor(Theme.key_voipgroup_speakingText);
        r5 = -1;
        goto L14
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        if (this.attached == false) goto L40;
        float r0 = (((this.textureView.getY() + this.textureView.getMeasuredHeight()) - this.textureView.currentClipVertical) - this.infoContainer.getMeasuredHeight()) + this.swipeToBackDy;
        if (this.showingAsScrimView == false) goto L7;
    L24:
        this.infoContainer.setAlpha(1.0f - this.parentContainer.progressToScrimView);
        this.micIconView.setAlpha(1.0f - this.parentContainer.progressToScrimView);
    L26:
        if (this.showingInFullscreen == false) goto L28;
    L31:
        this.nameView.setFullAlpha(this.parentContainer.progressToFullscreenMode);
    L32:
        this.micIconView.setTranslationX(this.infoContainer.getX());
        this.micIconView.setTranslationY(r0 - AndroidUtilities.dp(2.0f));
        if (this.screencastIcon.getVisibility() != 0) goto L35;
        this.screencastIcon.setTranslationX((this.textureView.getMeasuredWidth() - (this.textureView.currentClipHorizontal * 2.0f)) - AndroidUtilities.dp(32.0f));
        this.screencastIcon.setTranslationY(r0 - AndroidUtilities.dp(2.0f));
        ImageView r3 = this.screencastIcon;
        GroupCallRenderersContainer r4 = this.parentContainer;
        r3.setAlpha(Math.min(1.0f - r4.progressToFullscreenMode, 1.0f - r4.progressToScrimView));
    L35:
        this.infoContainer.setTranslationY(r0);
        FrameLayout r02 = this.infoContainer;
        if (this.drawFirst == false) goto L38;
        float r32 = 0.0f;
    L39:
        r02.setTranslationX(r32);
        goto L40
    L38:
        r32 = AndroidUtilities.dp(6.0f) * this.parentContainer.progressToFullscreenMode;
        goto L39
    L28:
        if (this.animateToFullscreen == true) goto L31;
        this.nameView.setFullAlpha(0.0f);
        goto L32
    L7:
        if (this.animateToScrimView == true) goto L24;
        if (this.showingInFullscreen == true) goto L19;
        if (this.animateToFullscreen == true) goto L19;
        GroupCallFullscreenAdapter.GroupCallUserCell r33 = this.secondaryView;
        FrameLayout r42 = this.infoContainer;
        if (r33 == null) goto L17;
        r42.setAlpha(1.0f - this.parentContainer.progressToFullscreenMode);
        this.micIconView.setAlpha(1.0f - this.parentContainer.progressToFullscreenMode);
        goto L26
    L17:
        r42.setAlpha(1.0f);
        this.micIconView.setAlpha(1.0f);
    L19:
        if (GroupCallActivity.isLandscapeMode == false) goto L21;
    L23:
        this.infoContainer.setAlpha(1.0f);
        this.micIconView.setAlpha(1.0f);
        goto L26
    L21:
        if (GroupCallActivity.isTabletMode == true) goto L23;
        float r34 = AndroidUtilities.dp(90.0f);
        GroupCallRenderersContainer r43 = this.parentContainer;
        r0 = r0 - ((r34 * r43.progressToFullscreenMode) * (1.0f - r43.progressToHideUi));
    L40:
        super.dispatchDraw(r9);
        if (this.attached == false) goto L63;
        GroupCallStatusIcon r03 = this.statusIcon;
        if (r03 == null) goto L59;
        boolean r04 = r03.isSpeaking;
        if (r04 == false) goto L52;
        float r44 = this.progressToSpeaking;
        if (r44 == 1.0f) goto L52;
        float r45 = r44 + 0.053333335f;
        this.progressToSpeaking = r45;
        if (r45 <= 1.0f) goto L51;
        this.progressToSpeaking = 1.0f;
        goto L59
    L51:
        invalidate();
    L52:
        if (r04 == true) goto L59;
        float r05 = this.progressToSpeaking;
        if (r05 == 0.0f) goto L59;
        float r06 = r05 - 0.053333335f;
        this.progressToSpeaking = r06;
        if (r06 >= 0.0f) goto L58;
        this.progressToSpeaking = 0.0f;
        goto L59
    L58:
        invalidate();
    L59:
        float r07 = this.progressToSpeaking;
        GroupCallRenderersContainer r35 = this.parentContainer;
        float r46 = ((1.0f - r35.progressToFullscreenMode) * r07) * (1.0f - r35.progressToScrimView);
        if (r07 <= 0.0f) goto L64;
        this.speakingPaint.setAlpha((int) (r46 * 255.0f));
        float r08 = (Math.max(0.0f, 1.0f - (Math.abs(this.swipeToBackDy) / AndroidUtilities.dp(300.0f))) * 0.1f) + 0.9f;
        r9.save();
        RectF r2 = AndroidUtilities.rectTmp;
        float r36 = this.textureView.getX();
        VoIPTextureView r47 = this.textureView;
        float r37 = r36 + r47.currentClipHorizontal;
        float r48 = r47.getY();
        VoIPTextureView r5 = this.textureView;
        float r49 = r48 + r5.currentClipVertical;
        float r52 = r5.getX() + this.textureView.getMeasuredWidth();
        VoIPTextureView r6 = this.textureView;
        r2.set(r37, r49, r52 - r6.currentClipHorizontal, (r6.getY() + this.textureView.getMeasuredHeight()) - this.textureView.currentClipVertical);
        r9.scale(r08, r08, r2.centerX(), r2.centerY());
        r9.translate(0.0f, this.swipeToBackDy);
        float r09 = this.textureView.roundRadius;
        r9.drawRoundRect(r2, r09, r09, this.speakingPaint);
        r9.restore();
        return;
    L64:
        return;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r7, View r8, long r9) {
        if (this.swipeToBack == false) goto L11;
        if (r8 != this.textureView) goto L7;
    L8:
        float r1 = (Math.max(0.0f, 1.0f - (Math.abs(this.swipeToBackDy) / AndroidUtilities.dp(300.0f))) * 0.1f) + 0.9f;
        r7.save();
        r7.scale(r1, r1, r8.getX() + (r8.getMeasuredWidth() / 2.0f), r8.getY() + (r8.getMeasuredHeight() / 2.0f));
        r7.translate(0.0f, this.swipeToBackDy);
        boolean r6 = super.drawChild(r7, r8, r9);
        r7.restore();
        return r6;
    L7:
        if (r8 == this.noVideoStubLayout) goto L8;
    L11:
        return super.drawChild(r7, r8, r9);
    }

    public void forceDetach(boolean r3) {
        this.forceDetached = true;
        this.attached = false;
        this.parentContainer.detach(this);
        if (r3 == true) goto L5;
    L14:
        saveThumb();
        ValueAnimator r32 = this.noVideoStubAnimator;
        if (r32 == null) goto L17;
        r32.removeAllListeners();
        this.noVideoStubAnimator.cancel();
    L17:
        this.textureView.renderer.release();
        return;
    L5:
        if (this.participant.participant.self == false) goto L10;
        if (VoIPService.getSharedInstance() == null) goto L14;
        VoIPService.getSharedInstance().setLocalSink(null, this.participant.presentation);
        goto L14
    L10:
        if (VoIPService.getSharedInstance() == null) goto L14;
        if (RTMPStreamPipOverlay.isVisible() == true) goto L14;
        VoIPService r33 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r0 = this.participant;
        r33.removeRemoteSink(r0.participant, r0.presentation);
        goto L14
    }

    public String getName() {
        long r0 = MessageObject.getPeerId(this.participant.participant.peer);
        if (DialogObject.isUserDialog(r0) == false) goto L7;
        return UserObject.getUserName(AccountInstance.getInstance(UserConfig.selectedAccount).getMessagesController().getUser(Long.valueOf(r0)));
    L7:
        return AccountInstance.getInstance(UserConfig.selectedAccount).getMessagesController().getChat(Long.valueOf(-r0)).title;
    }

    public GroupCallGridCell getPrimaryView() {
        return this.primaryView;
    }

    public void getRenderBufferBitmap(GlGenericDrawer.TextureCallback r1) {
        this.textureView.renderer.getRenderBufferBitmap(r1);
    }

    public boolean hasImage() {
        if (this.textureView.stubVisibleProgress != 1.0f) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (this.invalidateFromChild == true) goto L5;
        this.textureView.invalidate();
    L5:
        GroupCallGridCell r0 = this.primaryView;
        if (r0 == null) goto L10;
        r0.invalidate();
        if (this.activity.getScrimView() != this.primaryView) goto L10;
        this.activity.getContainerView().invalidate();
    L10:
        GroupCallFullscreenAdapter.GroupCallUserCell r02 = this.secondaryView;
        if (r02 == null) goto L16;
        r02.invalidate();
        if (this.secondaryView.getParent() == null) goto L16;
        ((View) this.secondaryView.getParent()).invalidate();
    L16:
        if (getParent() == null) goto L19;
        ((View) getParent()).invalidate();
        return;
    }

    public boolean isAttached() {
        return this.attached;
    }

    public boolean isFullyVisible() {
        if (this.showingInFullscreen == false) goto L5;
    L15:
        return false;
    L5:
        if (this.animateToFullscreen == true) goto L15;
        if (this.attached == false) goto L15;
        if (this.textureView.renderer.isFirstFrameRendered() == false) goto L15;
        if (getAlpha() != 1.0f) goto L15;
        return true;
    }

    public boolean isInsideStopScreenButton(float r3, float r4) {
        this.stopSharingTextView.getHitRect(this.rect);
        return this.rect.contains((int) r3, (int) r4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r14, int r15) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.infoContainer.getLayoutParams();
        int r1 = r0.leftMargin;
        if (this.call.call.rtmp_stream == false) goto L5;
        float r12 = 0.0f;
    L6:
        boolean r4 = this.lastLandscapeMode;
        boolean r5 = GroupCallActivity.isLandscapeMode;
        if (r4 == r5) goto L9;
        this.checkScale = true;
        this.lastLandscapeMode = r5;
    L9:
        int r52 = AndroidUtilities.dp(2.0f);
        r0.rightMargin = r52;
        r0.leftMargin = r52;
        boolean r02 = this.updateNextLayoutAnimated;
        SimpleTextView r53 = this.nameView;
        if (r02 == false) goto L12;
        r53.animate().scaleX(r12).scaleY(r12).start();
        this.micIconView.animate().scaleX(r12).scaleY(r12).start();
    L13:
        int r03 = 0;
        this.updateNextLayoutAnimated = false;
        if (this.showingInFullscreen == false) goto L29;
        updateSize(0);
        this.overlayIconAlpha = 1.0f;
        if (GroupCallActivity.isTabletMode == false) goto L19;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r14) - AndroidUtilities.dp(328.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r15) - AndroidUtilities.dp(4.0f), TLObject.FLAG_30));
    L72:
        int r152 = View.MeasureSpec.getSize(r15) + (View.MeasureSpec.getSize(r14) << 16);
        if (this.lastSize == r152) goto L75;
        this.lastSize = r152;
        LinearGradient r54 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(120.0f), 0, ColorUtils.setAlphaComponent(-16777216, 120), Shader.TileMode.CLAMP);
        this.gradientShader = r54;
        this.gradientPaint.setShader(r54);
    L75:
        this.nameView.setPivotX(0.0f);
        this.nameView.setPivotY(r13.getMeasuredHeight() / 2.0f);
        return;
    L19:
        if (GroupCallActivity.isLandscapeMode == true) goto L24;
        int r04 = View.MeasureSpec.getSize(r15);
        if (this.call.call.rtmp_stream == true) goto L23;
        r04 = r04 - AndroidUtilities.dp(92.0f);
    L23:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r14), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r04, TLObject.FLAG_30));
        goto L72
    L24:
        int r05 = View.MeasureSpec.getSize(r14);
        if (this.call.call.rtmp_stream == true) goto L27;
        r05 = r05 - AndroidUtilities.dp(92.0f);
    L27:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r05, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r15), TLObject.FLAG_30));
        goto L72
    L29:
        if (this.showingAsScrimView == false) goto L32;
        this.overlayIconAlpha = 1.0f;
        int r06 = Math.min(View.MeasureSpec.getSize(r14), View.MeasureSpec.getSize(r15)) - (AndroidUtilities.dp(14.0f) * 2);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r06, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r06 + getPaddingBottom(), TLObject.FLAG_30));
        goto L72
    L32:
        if (this.useSpanSize == false) goto L71;
        this.overlayIconAlpha = 1.0f;
        int r3 = 6;
        if (GroupCallActivity.isTabletMode == false) goto L39;
        if (this.tabletGridView == null) goto L39;
    L43:
        if (this.tabletGridView == null) goto L47;
        int r07 = View.MeasureSpec.getSize(r14) - AndroidUtilities.dp(344.0f);
    L45:
        float r08 = r07;
    L53:
        float r13 = (this.spanCount / r3) * r08;
        GroupCallGridCell r32 = this.tabletGridView;
        if (r32 == null) goto L58;
        float r09 = r32.getItemHeight() - AndroidUtilities.dp(4.0f);
        int r33 = AndroidUtilities.dp(4.0f);
    L56:
        float r16 = r13 - r33;
        FrameLayout.LayoutParams r34 = (FrameLayout.LayoutParams) this.infoContainer.getLayoutParams();
        if (this.screencastIcon.getVisibility() != 0) goto L69;
        float r55 = r16 - AndroidUtilities.dp(28.0f);
    L70:
        updateSize((int) r55);
        r34.width = (int) (r55 - (r34.leftMargin * 2));
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) r16, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) r09, TLObject.FLAG_30));
        goto L72
    L69:
        r55 = r16;
        goto L70
    L58:
        if (GroupCallActivity.isTabletMode == false) goto L61;
        r09 = r08 / 2.0f;
    L65:
        r33 = AndroidUtilities.dp(2.0f);
        goto L56
    L61:
        if (GroupCallActivity.isLandscapeMode == false) goto L63;
        int r35 = 3;
    L64:
        r09 = r08 / r35;
        goto L65
    L63:
        r35 = 2;
        goto L64
    L47:
        if (GroupCallActivity.isTabletMode == false) goto L49;
        r07 = AndroidUtilities.dp(320.0f);
        goto L45
    L49:
        int r17 = View.MeasureSpec.getSize(r14) - (AndroidUtilities.dp(14.0f) * 2);
        if (GroupCallActivity.isLandscapeMode == false) goto L52;
        r03 = -AndroidUtilities.dp(90.0f);
    L52:
        r08 = r17 + r03;
    L39:
        if (GroupCallActivity.isLandscapeMode == true) goto L43;
        r3 = 2;
        goto L43
    L71:
        this.overlayIconAlpha = 0.0f;
        super.onMeasure(r14, r15);
        goto L72
    L12:
        r53.animate().cancel();
        this.nameView.setScaleX(r12);
        this.nameView.setScaleY(r12);
        this.micIconView.animate().cancel();
        this.micIconView.setScaleX(r12);
        this.micIconView.setScaleY(r12);
        this.infoContainer.animate().cancel();
        goto L13
    L5:
        r12 = 1.0f;
        goto L6
    }

    @Override // org.telegram.ui.Components.voip.GroupCallStatusIcon.Callback
    public void onStatusChanged() {
        invalidate();
        updateIconColor(true);
        if (this.noVideoStubLayout.getVisibility() != 0) goto L6;
        NoVideoStubLayout.m13836$$Nest$mupdateMuteButtonState(this.noVideoStubLayout, true);
        return;
    }

    public void release() {
        this.textureView.renderer.release();
        GroupCallStatusIcon r0 = this.statusIcon;
        if (r0 == null) goto L5;
        this.activity.statusIconPool.add(r0);
        this.statusIcon.setCallback(null);
        this.statusIcon.setImageView(null);
    L5:
        this.statusIcon = null;
    }

    public void runDelayedAnimations() {
        int r0 = 0;
    L3:
        int r1 = this.onFirstFrameRunnables.size();
        ArrayList<Runnable> r2 = this.onFirstFrameRunnables;
        if (r0 >= r1) goto L6;
        r2.get(r0).run();
        r0 = r0 + 1;
        goto L3
    L6:
        r2.clear();
    }

    public void runOnFrameRendered(Runnable r3) {
        if (this.textureView.renderer.isFirstFrameRendered() == false) goto L6;
        r3.run();
        return;
    L6:
        AndroidUtilities.runOnUIThread(r3, 250);
        this.onFirstFrameRunnables.add(r3);
    }

    public void saveThumb() {
        if (this.participant != null) goto L5;
        return;
    L5:
        if (this.textureView.renderer.getMeasuredHeight() != 0) goto L7;
        return;
    L7:
        if (this.textureView.renderer.getMeasuredWidth() == 0) goto L12;
        getRenderBufferBitmap(new GroupCallMiniTextureView$$ExternalSyntheticLambda0(this));
        return;
    }

    public void setAmplitude(double r2) {
        this.statusIcon.setAmplitude(r2);
        this.noVideoStubLayout.setAmplitude(r2);
    }

    public void setFullscreenMode(boolean r2, boolean r3) {
        if (this.isFullscreenMode == r2) goto L13;
        this.isFullscreenMode = r2;
        if (this.primaryView == null) goto L7;
    L8:
        if (r3 == false) goto L10;
        boolean r22 = true;
    L11:
        updateAttachState(r22);
        return;
    L10:
        r22 = false;
        goto L11
    L7:
        if (this.tabletGridView == null) goto L10;
    }

    public void setPrimaryView(GroupCallGridCell r2) {
        if (this.primaryView == r2) goto L6;
        this.primaryView = r2;
        this.checkScale = true;
        updateAttachState(true);
        return;
    }

    public void setSecondaryView(GroupCallFullscreenAdapter.GroupCallUserCell r2) {
        if (this.secondaryView == r2) goto L6;
        this.secondaryView = r2;
        this.checkScale = true;
        updateAttachState(true);
        return;
    }

    public void setShowingAsScrimView(boolean r1, boolean r2) {
        this.showingAsScrimView = r1;
        updateAttachState(r2);
    }

    public void setShowingInFullscreen(boolean r2, boolean r3) {
        if (this.showingInFullscreen == r2) goto L6;
        this.showingInFullscreen = r2;
        this.checkScale = true;
        updateAttachState(r3);
        return;
    }

    public void setSwipeToBack(boolean r2, float r3) {
        if (this.swipeToBack == r2) goto L5;
    L8:
        this.swipeToBack = r2;
        this.swipeToBackDy = r3;
        this.textureView.invalidate();
        invalidate();
        return;
    L5:
        if (this.swipeToBackDy != r3) goto L8;
    }

    public void setTabletGridView(GroupCallGridCell r2) {
        if (this.tabletGridView == r2) goto L6;
        this.tabletGridView = r2;
        updateAttachState(true);
        return;
    }

    public void setViews(GroupCallGridCell r1, GroupCallFullscreenAdapter.GroupCallUserCell r2, GroupCallGridCell r3) {
        this.primaryView = r1;
        this.secondaryView = r2;
        this.tabletGridView = r3;
    }

    @Override // android.view.View
    public void setVisibility(int r1) {
        super.setVisibility(r1);
    }

    public void setZoom(boolean r2, float r3, float r4, float r5, float r6, float r7) {
        if (this.pinchScale == r3) goto L5;
    L14:
        this.inPinchToZoom = r2;
        this.pinchScale = r3;
        this.pinchCenterX = r4;
        this.pinchCenterY = r5;
        this.pinchTranslationX = r6;
        this.pinchTranslationY = r7;
        this.textureView.invalidate();
        return;
    L5:
        if (this.pinchCenterX != r4) goto L14;
        if (this.pinchCenterY != r5) goto L14;
        if (this.pinchTranslationX != r6) goto L14;
        if (this.pinchTranslationY != r7) goto L14;
    }

    public void startFlipAnimation() {
        if (this.flipAnimator == null) goto L5;
        return;
    L5:
        this.flipHalfReached = false;
        ImageView r0 = this.blurredFlippingStub;
        if (r0 != null) goto L8;
        this.blurredFlippingStub = new ImageView(getContext());
    L10:
        if (this.textureView.renderer.isFirstFrameRendered() == false) goto L15;
        Bitmap r02 = this.textureView.blurRenderer.getBitmap(100, 100);
        if (r02 == null) goto L14;
        Utilities.blurBitmap(r02, 3);
        BitmapDrawable r1 = new BitmapDrawable(r02);
        this.blurredFlippingStub.setBackground(r1);
    L14:
        this.blurredFlippingStub.setAlpha(0.0f);
    L17:
        if (this.blurredFlippingStub.getParent() != null) goto L19;
        this.textureView.addView(this.blurredFlippingStub);
    L19:
        ((FrameLayout.LayoutParams) this.blurredFlippingStub.getLayoutParams()).gravity = 17;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.flipAnimator = r03;
        r03.addUpdateListener(new GroupCallMiniTextureView$$ExternalSyntheticLambda4(this));
        this.flipAnimator.addListener(new AnonymousClass8(this));
        this.flipAnimator.setDuration(400);
        this.flipAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.flipAnimator.start();
        return;
    L15:
        this.blurredFlippingStub.setAlpha(1.0f);
        goto L17
    L8:
        r0.animate().cancel();
        goto L10
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateAttachState(boolean r24) {
        if (this.forceDetached == true) goto L376;
        boolean r2 = false;
        if (this.call.call.rtmp_stream == false) goto L13;
        if (this.showingInFullscreen == false) goto L10;
        float r1 = 36.0f;
    L11:
        int r12 = AndroidUtilities.dp(r1);
        this.noRtmpStreamTextView.setPadding(r12, 0, r12, 0);
        goto L13
    L10:
        r1 = 21.0f;
    L13:
        if (this.participant != null) goto L26;
        GroupCallGridCell r13 = this.primaryView;
        if (r13 == null) goto L17;
    L20:
        if (r13 == null) goto L22;
        this.participant = r13.getParticipant();
        goto L26
    L22:
        GroupCallGridCell r14 = this.tabletGridView;
        if (r14 == null) goto L25;
        this.participant = r14.getParticipant();
        goto L26
    L25:
        this.participant = this.secondaryView.getVideoParticipant();
        goto L26
    L17:
        if (this.secondaryView != null) goto L20;
        if (this.tabletGridView != null) goto L20;
    L26:
        boolean r15 = this.attached;
        float r8 = 1.0f;
        if (r15 == true) goto L29;
    L90:
        if (r15 == false) goto L92;
    L162:
        boolean r16 = r24;
        boolean r5 = false;
    L163:
        ChatObject.VideoParticipant r10 = this.participant;
        ChatObject.VideoParticipant r11 = this.call.videoNotAvailableParticipant;
        SimpleTextView r122 = this.nameView;
        if (r10 != r11) goto L169;
        if (r122.getVisibility() == 4) goto L172;
        this.nameView.setVisibility(4);
        this.micIconView.setVisibility(4);
    L172:
        if (this.attached == true) goto L174;
    L374:
        updateInfo();
        return;
    L174:
        if (GroupCallActivity.isTabletMode == true) goto L176;
    L182:
        boolean r102 = false;
    L183:
        int r123 = -1;
        if (this.showingInFullscreen == false) goto L187;
    L185:
        int r132 = 0;
        boolean r142 = false;
        float r112 = 1.0f;
    L186:
        int r103 = -1;
        boolean r143 = r142;
    L221:
        ViewGroup.MarginLayoutParams r152 = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if (r103 != 0) goto L224;
    L242:
        ChatObject.VideoParticipant r4 = this.participant;
        if (r4.participant.self == true) goto L245;
    L249:
        this.textureView.renderer.setMirror(false);
        this.textureView.renderer.setRotateTextureWithScreen(true);
        this.textureView.renderer.setUseCameraRotation(false);
    L250:
        this.textureView.updateRotation();
        boolean r42 = this.participant.participant.self;
        VoIPTextureView r52 = this.textureView;
        if (r42 == false) goto L253;
        r52.renderer.setMaxTextureSize(720);
    L254:
        ChatObject.VideoParticipant r43 = this.participant;
        if (ChatObject.Call.videoIsActive(r43.participant, r43.presentation, this.call) == false) goto L262;
        ChatObject.Call r44 = this.call;
        if (r44.canStreamVideo == false) goto L259;
    L261:
        boolean r45 = true;
    L274:
        if (r16 == true) goto L276;
    L281:
        boolean r53 = false;
    L283:
        if (r45 == this.hasVideo) goto L311;
        if (r53 == true) goto L311;
        this.hasVideo = r45;
        ValueAnimator r46 = this.noVideoStubAnimator;
        if (r46 == null) goto L288;
        r46.removeAllListeners();
        this.noVideoStubAnimator.cancel();
    L288:
        boolean r47 = this.hasVideo;
        if (r16 == false) goto L299;
        if (r47 == false) goto L292;
    L294:
        float r48 = this.progressToNoVideoStub;
        if (this.hasVideo == false) goto L297;
        float r54 = 0.0f;
    L298:
        ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{r48, r54});
        this.noVideoStubAnimator = r3;
        r3.addUpdateListener(new GroupCallMiniTextureView$$ExternalSyntheticLambda3(this));
        this.noVideoStubAnimator.addListener(new AnonymousClass6(this));
        this.noVideoStubAnimator.start();
    L308:
        if (this.hasVideo == false) goto L311;
        NoVideoStubLayout.m13836$$Nest$mupdateMuteButtonState(this.noVideoStubLayout, false);
        goto L311
    L297:
        r54 = 1.0f;
        goto L298
    L292:
        if (this.noVideoStubLayout.getVisibility() == 0) goto L294;
        this.noVideoStubLayout.setVisibility(0);
        this.noVideoStubLayout.setAlpha(0.0f);
        goto L294
    L299:
        if (r47 == false) goto L301;
        float r32 = 0.0f;
    L302:
        this.progressToNoVideoStub = r32;
        NoVideoStubLayout r33 = this.noVideoStubLayout;
        if (r47 == false) goto L305;
        int r49 = 8;
    L306:
        r33.setVisibility(r49);
        this.noVideoStubLayout.setAlpha(this.progressToNoVideoStub);
        this.textureView.invalidate();
        goto L308
    L305:
        r49 = 0;
        goto L306
    L301:
        r32 = 1.0f;
    L311:
        if (this.participant.participant.self == true) goto L313;
    L315:
        this.statusIcon.setParticipant(this.participant.participant, r16);
        if (this.noVideoStubLayout.getVisibility() != 0) goto L318;
        NoVideoStubLayout.m13836$$Nest$mupdateMuteButtonState(this.noVideoStubLayout, true);
    L318:
        ChatObject.VideoParticipant r17 = this.participant;
        if (r17.presentation == false) goto L325;
        TLRPC.TL_groupCallParticipantVideo r18 = r17.participant.presentation;
        if (r18 == null) goto L331;
        if (r18.paused == false) goto L331;
    L324:
        r2 = true;
    L331:
        if (this.videoIsPaused == r2) goto L337;
        this.videoIsPaused = r2;
        ViewPropertyAnimator r19 = this.textureView.renderer.animate();
        if (this.videoIsPaused == false) goto L335;
        r8 = 0.0f;
    L335:
        r19.alpha(r8).setDuration(250).start();
        this.textureView.invalidate();
    L337:
        if (GroupCallActivity.paused == true) goto L361;
        if (this.hasVideo == false) goto L361;
        if (this.textureView.renderer.isFirstFrameRendered() == true) goto L345;
        loadThumb();
    L345:
        if (this.participant.participant.self == false) goto L350;
        if (VoIPService.getSharedInstance() == null) goto L373;
        VoIPService.getSharedInstance().setLocalSink(this.textureView.renderer, this.participant.presentation);
    L373:
        updateIconColor(true);
        goto L374
    L350:
        if (VoIPService.getSharedInstance() == null) goto L373;
        VoIPService r110 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r22 = this.participant;
        r110.addRemoteSink(r22.participant, r22.presentation, this.textureView.renderer, null);
        VoIPService r111 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r23 = this.participant;
        r111.addRemoteSink(r23.participant, r23.presentation, this.textureView.renderer, null);
        ChatObject.Call r113 = this.call;
        if (r113 == null) goto L373;
        if (r113.call.rtmp_stream == false) goto L373;
        if (this.textureView.renderer.isFirstFrameRendered() == true) goto L373;
        if (this.postedNoRtmpStreamCallback == true) goto L373;
        AndroidUtilities.runOnUIThread(this.noRtmpStreamCallback, 15000);
        this.postedNoRtmpStreamCallback = true;
    L361:
        if (this.participant.participant.self == false) goto L366;
        if (VoIPService.getSharedInstance() == null) goto L369;
        VoIPService.getSharedInstance().setLocalSink(null, this.participant.presentation);
    L369:
        if (GroupCallActivity.paused == false) goto L373;
        if (this.textureView.renderer.isFirstFrameRendered() == false) goto L373;
        saveThumb();
        this.textureView.renderer.clearFirstFrame();
        this.textureView.renderer.setAlpha(0.0f);
        this.textureView.blurRenderer.setAlpha(0.0f);
        goto L373
    L366:
        if (VoIPService.getSharedInstance() == null) goto L369;
        VoIPService r114 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r25 = this.participant;
        r114.removeRemoteSink(r25.participant, r25.presentation);
        VoIPService r115 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r26 = this.participant;
        r115.removeRemoteSink(r26.participant, r26.presentation);
        goto L369
    L325:
        TLRPC.TL_groupCallParticipantVideo r116 = r17.participant.video;
        if (r116 == null) goto L331;
        if (r116.paused == false) goto L331;
    L313:
        if (VoIPService.getSharedInstance() == null) goto L315;
        VoIPService.getSharedInstance().setLocalSink(this.textureView.renderer, this.participant.presentation);
        goto L315
    L276:
        if (this.secondaryView == null) goto L281;
        if (this.showingInFullscreen == true) goto L281;
        if (r45 == true) goto L281;
        r53 = true;
        goto L283
    L259:
        if (this.participant == r44.videoNotAvailableParticipant) goto L261;
    L262:
        this.noVideoStubLayout.avatarImageReceiver.setCurrentAccount(this.currentAccount);
        long r410 = MessageObject.getPeerId(this.participant.participant.peer);
        if (DialogObject.isUserDialog(r410) == false) goto L266;
        TLRPC.User r411 = AccountInstance.getInstance(this.currentAccount).getMessagesController().getUser(Long.valueOf(r410));
        this.noVideoStubLayout.avatarDrawable.setInfo(this.currentAccount, r411);
        ImageLocation r55 = ImageLocation.getForUser(this.currentAccount, r411, 0);
        ImageLocation r104 = ImageLocation.getForUser(this.currentAccount, r411, 1);
        TLRPC.Chat r412 = r411;
    L265:
        TLRPC.Chat r21 = r412;
        ImageLocation r172 = r55;
        Drawable r413 = this.noVideoStubLayout.avatarDrawable;
        if (r104 == null) goto L272;
        Drawable r56 = ImageLoader.getInstance().getImageFromMemory(r104.location, null, "50_50");
        if (r56 == null) goto L272;
        Drawable r192 = r56;
    L273:
        this.noVideoStubLayout.avatarImageReceiver.setImage(r172, null, r192, null, r21, 0);
        this.noVideoStubLayout.backgroundImageReceiver.setImage(r172, "50_50_b", new ColorDrawable(Theme.getColor(Theme.key_voipgroup_listViewBackground)), null, r21, 0);
        r45 = false;
    L272:
        r192 = r413;
        goto L273
    L266:
        TLRPC.Chat r414 = AccountInstance.getInstance(UserConfig.selectedAccount).getMessagesController().getChat(Long.valueOf(-r410));
        this.noVideoStubLayout.avatarDrawable.setInfo(this.currentAccount, r414);
        r55 = ImageLocation.getForChat(this.currentAccount, r414, 0);
        r104 = ImageLocation.getForChat(this.currentAccount, r414, 1);
        r412 = r414;
        goto L265
    L253:
        r52.renderer.setMaxTextureSize(0);
        goto L254
    L245:
        if (r4.presentation == true) goto L249;
        if (VoIPService.getSharedInstance() == null) goto L249;
        this.textureView.renderer.setMirror(VoIPService.getSharedInstance().isFrontFaceCamera());
        this.textureView.renderer.setRotateTextureWithScreen(true);
        this.textureView.renderer.setUseCameraRotation(true);
        goto L250
    L224:
        if (r152.height != r103) goto L233;
        if (r5 == true) goto L233;
        if (this.useSpanSize != r143) goto L233;
        if (r143 == 0) goto L232;
        if (this.spanCount != r112) goto L233;
    L232:
        if (this.gridItemsCount == r132) goto L242;
    L233:
        r152.height = r103;
        if (r143 != 0) goto L237;
        r123 = r103;
    L237:
        r152.width = r123;
        this.useSpanSize = r143;
        this.spanCount = r112;
        this.checkScale = true;
        VoIPTextureView r415 = this.textureView;
        if (r16 == false) goto L240;
        r415.animateToLayout();
        this.updateNextLayoutAnimated = true;
    L241:
        AndroidUtilities.runOnUIThread(new GroupCallMiniTextureView$$ExternalSyntheticLambda2(this));
        this.parentContainer.requestLayout();
        invalidate();
        goto L242
    L240:
        r415.requestLayout();
        goto L241
    L187:
        GroupCallFullscreenAdapter.GroupCallUserCell r117 = this.secondaryView;
        if (r117 == null) goto L196;
        if (this.primaryView != null) goto L196;
        if (this.parentContainer.inFullscreenMode == true) goto L196;
    L193:
        r103 = 0;
        r132 = 0;
    L194:
        r143 = r132;
        r112 = 1.0f;
    L196:
        if (this.showingAsScrimView == true) goto L185;
        if (r117 != null) goto L201;
    L204:
        GroupCallGridCell r144 = this.tabletGridView;
        if (r144 == null) goto L211;
        if (r102 == false) goto L211;
        if (r144 == null) goto L210;
        float r105 = r144.spanCount;
        int r118 = r144.gridAdapter.getItemCount();
        boolean r145 = true;
        r132 = r118;
    L209:
        r112 = r105;
        r142 = r145;
        goto L186
    L210:
        r103 = AndroidUtilities.dp(46.0f);
    L203:
        r132 = 0;
    L211:
        GroupCallGridCell r106 = this.primaryView;
        if (r106 == null) goto L215;
        if (r117 != null) goto L215;
    L216:
        if (r106 == null) goto L218;
        r105 = r106.spanCount;
        r132 = 0;
        r145 = true;
        goto L209
    L218:
        r103 = AndroidUtilities.dp(46.0f);
    L215:
        if (this.isFullscreenMode == false) goto L216;
        if (r106 == null) goto L193;
        r103 = AndroidUtilities.dp(80.0f);
        goto L203
    L201:
        if (this.primaryView != null) goto L204;
        r103 = AndroidUtilities.dp(80.0f);
        goto L203
    L176:
        if (this.parentContainer.inFullscreenMode == true) goto L178;
    L181:
        r102 = true;
        goto L183
    L178:
        if (this.secondaryView != null) goto L182;
        if (this.primaryView != null) goto L182;
    L169:
        if (r122.getVisibility() == 0) goto L172;
        this.nameView.setVisibility(0);
        this.micIconView.setVisibility(0);
        goto L172
    L92:
        if (VoIPService.getSharedInstance() != null) goto L94;
        return;
    L94:
        GroupCallGridCell r119 = this.primaryView;
        if (r119 == null) goto L97;
    L102:
        if (r119 == null) goto L104;
        this.participant = r119.getParticipant();
    L110:
        ChatObject.VideoParticipant r120 = this.participant;
        TLRPC.GroupCallParticipant r107 = r120.participant;
        if (r107.self == true) goto L113;
        ChatObject.Call r1110 = this.call;
        if (r1110.canStreamVideo == true) goto L123;
        if (r120 == r1110.videoNotAvailableParticipant) goto L123;
    L117:
        boolean r121 = false;
    L126:
        if (this.showingInFullscreen == true) goto L132;
        VoIPService r108 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r1111 = this.participant;
        if (r108.isFullscreen(r1111.participant, r1111.presentation) == true) goto L162;
        VoIPService r109 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r1112 = this.participant;
        if (r109.isFullscreen(r1112.participant, r1112.presentation) == true) goto L162;
        if (r121 == false) goto L162;
    L132:
        this.attached = true;
        if (this.activity.statusIconPool.size() <= 0) goto L135;
        ArrayList<GroupCallStatusIcon> r124 = this.activity.statusIconPool;
        this.statusIcon = r124.remove(r124.size() - 1);
    L136:
        this.statusIcon.setCallback(this);
        this.statusIcon.setImageView(this.micIconView);
        updateIconColor(false);
        Runnable r125 = this.hideRunnable;
        if (r125 == null) goto L140;
        AndroidUtilities.cancelRunOnUIThread(r125);
        this.hideRunnable = null;
    L140:
        if (getParent() != null) goto L143;
        this.parentContainer.addView(this, LayoutHelper.createFrame(46, 46, 51));
        this.parentContainer.attach(this);
        setVisibility(0);
    L145:
        this.checkScale = true;
        this.animateEnter = false;
        animate().setListener(null).cancel();
        if (this.textureView.currentAnimation == null) goto L148;
    L154:
        setScaleY(1.0f);
        setScaleX(1.0f);
        setAlpha(1.0f);
    L155:
        loadThumb();
        ImageView r126 = this.screencastIcon;
        if (this.participant.presentation == true) goto L158;
    L160:
        int r57 = 8;
    L161:
        r126.setVisibility(r57);
        r16 = false;
        r5 = true;
        goto L163
    L158:
        if (this.call.call.rtmp_stream == true) goto L160;
        r57 = 0;
        goto L161
    L148:
        if (this.secondaryView == null) goto L154;
        if (this.primaryView != null) goto L154;
        if (hasImage() == true) goto L154;
        setScaleX(0.5f);
        setScaleY(0.5f);
        setAlpha(0.0f);
        this.animateEnter = true;
        invalidate();
        animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setListener(new AnonymousClass5(this)).setDuration(100).start();
        invalidate();
        goto L155
    L143:
        if (getVisibility() != 8) goto L145;
        setVisibility(0);
        goto L145
    L135:
        this.statusIcon = new GroupCallStatusIcon();
    L123:
        if (ChatObject.Call.videoIsActive(r107, r120.presentation, r1110) == false) goto L117;
    L116:
        r121 = true;
        goto L126
    L113:
        if (VoIPService.getSharedInstance() == null) goto L117;
        if (VoIPService.getSharedInstance().getVideoState(this.participant.presentation) != 2) goto L117;
    L104:
        GroupCallFullscreenAdapter.GroupCallUserCell r127 = this.secondaryView;
        if (r127 == null) goto L107;
        this.participant = r127.getVideoParticipant();
        goto L110
    L107:
        GroupCallGridCell r128 = this.tabletGridView;
        if (r128 == null) goto L110;
        this.participant = r128.getParticipant();
        goto L110
    L97:
        if (this.secondaryView != null) goto L102;
        if (this.tabletGridView != null) goto L102;
        if (this.showingInFullscreen == false) goto L162;
    L29:
        if (this.showingInFullscreen == true) goto L90;
        if (VoIPService.getSharedInstance() != null) goto L33;
        boolean r129 = true;
    L35:
        if (GroupCallActivity.paused == true) goto L46;
        ChatObject.VideoParticipant r1010 = this.participant;
        if (r1010 == null) goto L46;
        if (this.secondaryView == null) goto L41;
    L47:
        if (r129 == false) goto L49;
    L58:
        this.attached = false;
        saveThumb();
        if (SharedConfig.getDevicePerformanceClass() > 0) goto L61;
        boolean r1011 = true;
    L63:
        if (this.textureView.currentAnimation != null) goto L68;
        if (r129 == false) goto L68;
        if (r1011 == false) goto L67;
        this.parentContainer.detach(this);
    L67:
        animate().scaleX(0.5f).scaleY(0.5f).alpha(0.0f).setListener(new AnonymousClass4(this, this, r1011)).setDuration(150).start();
    L80:
        if (this.participant.participant.self == false) goto L85;
        if (VoIPService.getSharedInstance() == null) goto L87;
        VoIPService.getSharedInstance().setLocalSink(null, this.participant.presentation);
    L87:
        invalidate();
        ValueAnimator r130 = this.noVideoStubAnimator;
        if (r130 == null) goto L162;
        r130.removeAllListeners();
        this.noVideoStubAnimator.cancel();
        goto L162
    L85:
        if (VoIPService.getSharedInstance() == null) goto L87;
        VoIPService r131 = VoIPService.getSharedInstance();
        ChatObject.VideoParticipant r58 = this.participant;
        r131.removeRemoteSink(r58.participant, r58.presentation);
    L68:
        GroupCallRenderersContainer r133 = this.parentContainer;
        if (r133.inLayout == false) goto L74;
        Runnable r134 = this.hideRunnable;
        if (r134 == null) goto L73;
        AndroidUtilities.cancelRunOnUIThread(r134);
        this.hideRunnable = null;
    L73:
        GroupCallMiniTextureView$$ExternalSyntheticLambda1 r135 = new GroupCallMiniTextureView$$ExternalSyntheticLambda1(this, r1011, this);
        this.hideRunnable = r135;
        AndroidUtilities.runOnUIThread(r135);
    L77:
        if (r1011 == false) goto L80;
        this.parentContainer.detach(this);
        release();
        goto L80
    L74:
        if (r1011 == false) goto L76;
        r133.removeView(this);
    L76:
        setVisibility(8);
        goto L77
    L61:
        r1011 = false;
        goto L63
    L49:
        if (this.primaryView != null) goto L162;
        if (this.secondaryView != null) goto L162;
        if (this.tabletGridView != null) goto L162;
        if (this.showingAsScrimView == true) goto L162;
        if (this.animateToScrimView == true) goto L162;
    L41:
        if (ChatObject.Call.videoIsActive(r1010.participant, r1010.presentation, this.call) == false) goto L46;
        ChatObject.Call r1012 = this.call;
        if (r1012.canStreamVideo == true) goto L47;
        if (this.participant == r1012.videoNotAvailableParticipant) goto L47;
    L46:
        r129 = true;
        goto L47
    L33:
        r129 = false;
        goto L35
    }

    public void updateInfo() {
        if (this.attached == true) goto L5;
        return;
    L5:
        long r0 = MessageObject.getPeerId(this.participant.participant.peer);
        boolean r2 = DialogObject.isUserDialog(r0);
        int r3 = this.currentAccount;
        if (r2 == false) goto L8;
        String r02 = UserObject.getUserName(AccountInstance.getInstance(r3).getMessagesController().getUser(Long.valueOf(r0)));
    L12:
        this.nameView.setText(r02);
        return;
    L8:
        TLRPC.Chat r03 = AccountInstance.getInstance(r3).getMessagesController().getChat(Long.valueOf(-r0));
        if (r03 == null) goto L11;
        r02 = r03.title;
        goto L12
    L11:
        r02 = null;
        goto L12
    }

    public void updatePosition(ViewGroup r9, ViewGroup r10, RecyclerListView r11, GroupCallRenderersContainer r12) {
        if (this.showingAsScrimView == false) goto L5;
        return;
    L5:
        if (this.animateToScrimView == false) goto L7;
        return;
    L7:
        if (this.forceDetached == true) goto L103;
        boolean r0 = false;
        this.drawFirst = false;
        float r1 = r12.progressToFullscreenMode;
        if (this.animateToFullscreen == false) goto L12;
    L68:
        GroupCallGridCell r112 = this.primaryView;
        if (r112 == null) goto L71;
    L74:
        GroupCallGridCell r2 = this.tabletGridView;
        if (r2 == null) goto L77;
        r112 = r2;
    L77:
        if (r2 == null) goto L79;
        r9 = r10;
    L79:
        float r102 = ((r112.getX() + r9.getX()) - getLeft()) - r12.getLeft();
        float r113 = (((r112.getY() + AndroidUtilities.dp(2.0f)) + r9.getY()) - getTop()) - r12.getTop();
        float r92 = 1.0f - r1;
        float r02 = 0.0f * r1;
        setTranslationX((r102 * r92) + r02);
        setTranslationY((r113 * r92) + r02);
    L80:
        this.textureView.setRoundCorners(AndroidUtilities.dp(8.0f));
        GroupCallFullscreenAdapter.GroupCallUserCell r93 = this.secondaryView;
        if (r93 == null) goto L84;
        r93.setAlpha(r1);
    L84:
        if (this.showingInFullscreen == true) goto L92;
        if (this.primaryView != null) goto L92;
        if (this.tabletGridView != null) goto L92;
        setAlpha(r1);
        return;
    L92:
        if (this.animateEnter == true) goto L102;
        setAlpha(1.0f);
        return;
    L102:
        return;
    L71:
        if (this.tabletGridView != null) goto L74;
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        goto L80
    L12:
        if (this.showingInFullscreen == true) goto L68;
        GroupCallFullscreenAdapter.GroupCallUserCell r22 = this.secondaryView;
        if (r22 != null) goto L17;
        GroupCallGridCell r114 = this.primaryView;
        if (r114 == null) goto L37;
    L38:
        GroupCallGridCell r23 = this.tabletGridView;
        if (r23 == null) goto L50;
        if (r114 == null) goto L50;
        if (GroupCallActivity.isTabletMode == true) goto L44;
    L46:
        if (r0 == false) goto L48;
        r114 = r23;
    L48:
        if (r0 == false) goto L54;
    L49:
        r9 = r10;
    L54:
        setTranslationX(((r114.getX() + r9.getX()) - getLeft()) - r12.getLeft());
        setTranslationY((((r114.getY() + AndroidUtilities.dp(2.0f)) + r9.getY()) - getTop()) - r12.getTop());
        this.textureView.setRoundCorners(AndroidUtilities.dp(8.0f));
        if (this.attached == true) goto L57;
        return;
    L57:
        if (this.animateEnter == false) goto L59;
        return;
    L59:
        if (GroupCallActivity.isTabletMode == true) goto L63;
        this.drawFirst = true;
        setAlpha((1.0f - r1) * r114.getAlpha());
        return;
    L63:
        if (this.primaryView != null) goto L65;
        return;
    L65:
        if (this.tabletGridView != null) goto L101;
        setAlpha(r1 * r114.getAlpha());
        return;
    L101:
        return;
    L44:
        if (this.parentContainer.inFullscreenMode == true) goto L46;
        r0 = true;
    L50:
        if (r23 == null) goto L52;
        r114 = r23;
    L52:
        if (r23 == null) goto L54;
    L37:
        if (this.tabletGridView != null) goto L38;
        return;
    L17:
        if (r22.isRemoving(r11) == false) goto L20;
        setAlpha(this.secondaryView.getAlpha());
    L32:
        setTranslationX((this.secondaryView.getX() + r11.getX()) - getLeft());
        float r5 = 1.0f - r1;
        setTranslationY((((AndroidUtilities.dp(2.0f) * r5) + this.secondaryView.getY()) + r11.getY()) - getTop());
        this.textureView.setRoundCorners((AndroidUtilities.dp(13.0f) * r1) + (AndroidUtilities.dp(8.0f) * r5));
        return;
    L20:
        if (this.primaryView == null) goto L22;
        this.secondaryView.setAlpha(1.0f);
        if (this.attached == false) goto L32;
        if (this.animateEnter == true) goto L32;
        setAlpha(1.0f);
        goto L32
    L22:
        if (this.attached == true) goto L24;
    L26:
        this.secondaryView.setAlpha(r1);
        r1 = 1.0f;
        goto L32
    L24:
        if (this.animateEnter == true) goto L26;
        setAlpha(r1);
        goto L26
    }

    public void updateSize(int r3) {
        int r0 = this.parentContainer.getMeasuredWidth() - AndroidUtilities.dp(6.0f);
        if (this.collapseSize == r3) goto L6;
        if (r3 <= 0) goto L6;
    L8:
        if (r3 == 0) goto L10;
        this.collapseSize = r3;
    L10:
        if (r0 == 0) goto L12;
        this.fullSize = r0;
    L12:
        this.nameView.setFullLayoutAdditionalWidth(r0 - r3, 0);
        return;
    L6:
        if (this.fullSize == r0) goto L14;
        if (r0 > 0) goto L8;
        return;
    }
}
