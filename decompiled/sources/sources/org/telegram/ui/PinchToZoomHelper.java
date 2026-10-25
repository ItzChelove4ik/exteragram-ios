package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;

/* loaded from: classes6.dex */
public class PinchToZoomHelper {
    private ImageReceiver blurImage;
    Callback callback;
    private View child;
    private ImageReceiver childImage;
    private View childTextureView;
    private View childTextureViewContainer;
    ClipBoundsListener clipBoundsListener;
    private float[] clipTopBottom;
    private float enterProgress;
    private ColorMatrixColorFilter fancyBlurFilter;
    private float finishProgress;
    ValueAnimator finishTransition;
    float fragmentOffsetX;
    float fragmentOffsetY;
    private final ViewGroup fragmentView;
    private ImageReceiver fullImage;
    private float fullImageHeight;
    private float fullImageWidth;
    private boolean hasMediaSpoiler;
    private float imageHeight;
    private float imageWidth;
    private float imageX;
    private float imageY;
    private boolean inOverlayMode;
    private boolean isHardwareVideo;
    boolean isInPinchToZoomTouchMode;
    private final boolean isSimple;
    private SpoilerEffect mediaSpoilerEffect;
    private SpoilerEffect2 mediaSpoilerEffect2;
    private MessageObject messageObject;
    private ZoomOverlayView overlayView;
    float parentOffsetX;
    float parentOffsetY;
    private final ViewGroup parentView;
    private Path path;
    float pinchCenterX;
    float pinchCenterY;
    float pinchScale;
    float pinchStartCenterX;
    float pinchStartCenterY;
    float pinchStartDistance;
    float pinchTranslationX;
    float pinchTranslationY;
    private int pointerId1;
    private int pointerId2;
    private float progressToFullView;
    private float[] spoilerRadii;

    /* renamed from: org.telegram.ui.PinchToZoomHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ PinchToZoomHelper this$0;

        public AnonymousClass1(PinchToZoomHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PinchToZoomHelper r0 = this.this$0;
            if (r0.finishTransition == null) goto L6;
            r0.finishTransition = null;
            r0.clear();
            return;
        }
    }

    public interface Callback {
        default TextureView getCurrentTextureView() {
            return null;
        }

        default void onZoomFinished(MessageObject r1) {
        }

        void onZoomStarted(MessageObject r1);
    }

    public interface ClipBoundsListener {
        void getClipTopBottom(float[] r1);
    }

    public class ZoomOverlayView extends FrameLayout {
        private Paint aspectPaint;
        private Path aspectPath;
        private AspectRatioFrameLayout aspectRatioFrameLayout;
        private BackupImageView backupImageView;
        final /* synthetic */ PinchToZoomHelper this$0;
        private FrameLayout videoPlayerContainer;
        private TextureView videoTextureView;

        /* renamed from: org.telegram.ui.PinchToZoomHelper$ZoomOverlayView$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewOutlineProvider {
            final /* synthetic */ ZoomOverlayView this$1;
            final /* synthetic */ PinchToZoomHelper val$this$0;

            public AnonymousClass1(ZoomOverlayView r1, PinchToZoomHelper r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View r9, Outline r10) {
                ImageReceiver r8 = (ImageReceiver) r9.getTag(R.id.parent_tag);
                int r0 = 0;
                if (r8 == null) goto L10;
                int[] r82 = r8.getRoundRadius(true);
                int r1 = 0;
            L6:
                if (r0 >= 4) goto L8;
                r1 = Math.max(r1, r82[r0]);
                r0 = r0 + 1;
                goto L6
            L8:
                r10.setRoundRect(0, 0, r9.getMeasuredWidth(), r9.getMeasuredHeight(), r1);
                return;
            L10:
                int r83 = AndroidUtilities.roundMessageSize;
                r10.setOval(0, 0, r83, r83);
            }
        }

        /* renamed from: -$$Nest$fgetaspectRatioFrameLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AspectRatioFrameLayout m17443$$Nest$fgetaspectRatioFrameLayout(ZoomOverlayView r0) {
            return r0.aspectRatioFrameLayout;
        }

        /* renamed from: -$$Nest$fgetbackupImageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m17444$$Nest$fgetbackupImageView(ZoomOverlayView r0) {
            return r0.backupImageView;
        }

        /* renamed from: -$$Nest$fgetvideoPlayerContainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m17445$$Nest$fgetvideoPlayerContainer(ZoomOverlayView r0) {
            return r0.videoPlayerContainer;
        }

        /* renamed from: -$$Nest$fgetvideoTextureView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextureView m17446$$Nest$fgetvideoTextureView(ZoomOverlayView r0) {
            return r0.videoTextureView;
        }

        public ZoomOverlayView(PinchToZoomHelper r5, Context r6) {
            this.this$0 = r5;
            super(r6);
            this.aspectPath = new Path();
            this.aspectPaint = new Paint(1);
            FrameLayout r0 = new FrameLayout(r6);
            this.videoPlayerContainer = r0;
            r0.setOutlineProvider(new AnonymousClass1(this, r5));
            this.videoPlayerContainer.setClipToOutline(true);
            BackupImageView r52 = new BackupImageView(r6);
            this.backupImageView = r52;
            this.videoPlayerContainer.addView(r52);
            this.videoPlayerContainer.setWillNotDraw(false);
            AspectRatioFrameLayout r53 = new AspectRatioFrameLayout(r6);
            this.aspectRatioFrameLayout = r53;
            r53.setBackgroundColor(0);
            this.videoPlayerContainer.addView(this.aspectRatioFrameLayout, LayoutHelper.createFrame(-1, -1, 17));
            TextureView r54 = new TextureView(r6);
            this.videoTextureView = r54;
            r54.setOpaque(false);
            this.aspectRatioFrameLayout.addView(this.videoTextureView, LayoutHelper.createFrame(-1, -1.0f));
            addView(this.videoPlayerContainer, LayoutHelper.createFrame(-2, -2.0f));
            setWillNotDraw(false);
        }

        private void drawImage(Canvas r13) {
            if (PinchToZoomHelper.m17431$$Nest$fgetinOverlayMode(this.this$0) == true) goto L5;
            return;
        L5:
            if (PinchToZoomHelper.m17416$$Nest$fgetchild(this.this$0) != null) goto L7;
            return;
        L7:
            if (PinchToZoomHelper.m17436$$Nest$fgetparentView(this.this$0) == null) goto L78;
            PinchToZoomHelper.m17442$$Nest$mupdateViewsLocation(this.this$0);
            float r0 = this.this$0.parentOffsetX - getLeft();
            float r1 = this.this$0.parentOffsetY - getTop();
            r13.save();
            PinchToZoomHelper r2 = this.this$0;
            float r3 = ((r2.pinchScale * PinchToZoomHelper.m17422$$Nest$fgetfinishProgress(r2)) + 1.0f) - PinchToZoomHelper.m17422$$Nest$fgetfinishProgress(this.this$0);
            PinchToZoomHelper r4 = this.this$0;
            r13.scale(r3, r3, r4.pinchCenterX + r0, r4.pinchCenterY + r1);
            PinchToZoomHelper r42 = this.this$0;
            float r5 = (r42.pinchTranslationX * PinchToZoomHelper.m17422$$Nest$fgetfinishProgress(r42)) + r0;
            PinchToZoomHelper r43 = this.this$0;
            r13.translate(r5, (r43.pinchTranslationY * PinchToZoomHelper.m17422$$Nest$fgetfinishProgress(r43)) + r1);
            if (PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0) != null) goto L12;
        L20:
            float r44 = PinchToZoomHelper.m17429$$Nest$fgetimageX(this.this$0);
            float r52 = PinchToZoomHelper.m17430$$Nest$fgetimageY(this.this$0);
            if (PinchToZoomHelper.m17427$$Nest$fgetimageHeight(this.this$0) != PinchToZoomHelper.m17424$$Nest$fgetfullImageHeight(this.this$0)) goto L25;
            if (PinchToZoomHelper.m17428$$Nest$fgetimageWidth(this.this$0) != PinchToZoomHelper.m17425$$Nest$fgetfullImageWidth(this.this$0)) goto L25;
        L36:
            if (PinchToZoomHelper.m17432$$Nest$fgetisHardwareVideo(this.this$0) == false) goto L38;
            FrameLayout r22 = this.videoPlayerContainer;
            PinchToZoomHelper r6 = this.this$0;
            r22.setPivotX(r6.pinchCenterX - PinchToZoomHelper.m17429$$Nest$fgetimageX(r6));
            FrameLayout r23 = this.videoPlayerContainer;
            PinchToZoomHelper r62 = this.this$0;
            r23.setPivotY(r62.pinchCenterY - PinchToZoomHelper.m17430$$Nest$fgetimageY(r62));
            this.videoPlayerContainer.setScaleY(r3);
            this.videoPlayerContainer.setScaleX(r3);
            FrameLayout r24 = this.videoPlayerContainer;
            float r45 = r44 + r0;
            PinchToZoomHelper r02 = this.this$0;
            r24.setTranslationX(r45 + ((r02.pinchTranslationX * r3) * PinchToZoomHelper.m17422$$Nest$fgetfinishProgress(r02)));
            FrameLayout r03 = this.videoPlayerContainer;
            float r53 = r52 + r1;
            PinchToZoomHelper r12 = this.this$0;
            r03.setTranslationY(r53 + ((r12.pinchTranslationY * r3) * PinchToZoomHelper.m17422$$Nest$fgetfinishProgress(r12)));
        L68:
            if (PinchToZoomHelper.m17426$$Nest$fgethasMediaSpoiler(this.this$0) == false) goto L74;
            PinchToZoomHelper.m17415$$Nest$fgetblurImage(this.this$0).setAlpha(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getAlpha());
            PinchToZoomHelper.m17415$$Nest$fgetblurImage(this.this$0).setRoundRadius(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getRoundRadius(true));
            PinchToZoomHelper.m17415$$Nest$fgetblurImage(this.this$0).setImageCoords(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageWidth(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageHeight());
            PinchToZoomHelper.m17415$$Nest$fgetblurImage(this.this$0).draw(r13);
            int[] r04 = PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getRoundRadius(true);
            float[] r14 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float[] r32 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float r54 = r04[0];
            r32[1] = r54;
            r14[0] = r54;
            float[] r15 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float[] r33 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float r25 = r04[1];
            r33[3] = r25;
            r15[2] = r25;
            float[] r16 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float[] r26 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float r34 = r04[2];
            r26[5] = r34;
            r16[4] = r34;
            float[] r17 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float[] r27 = PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0);
            float r05 = r04[3];
            r27[7] = r05;
            r17[6] = r05;
            RectF r06 = AndroidUtilities.rectTmp;
            r06.set(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX2(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY2());
            PinchToZoomHelper.m17437$$Nest$fgetpath(this.this$0).rewind();
            PinchToZoomHelper.m17437$$Nest$fgetpath(this.this$0).addRoundRect(r06, PinchToZoomHelper.m17439$$Nest$fgetspoilerRadii(this.this$0), Path.Direction.CW);
            r13.save();
            r13.clipPath(PinchToZoomHelper.m17437$$Nest$fgetpath(this.this$0));
            SpoilerEffect2 r07 = PinchToZoomHelper.m17434$$Nest$fgetmediaSpoilerEffect2(this.this$0);
            PinchToZoomHelper r18 = this.this$0;
            if (r07 == null) goto L72;
            r13.translate(PinchToZoomHelper.m17417$$Nest$fgetchildImage(r18).getImageX(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY());
            PinchToZoomHelper.m17434$$Nest$fgetmediaSpoilerEffect2(this.this$0).draw(r13, PinchToZoomHelper.m17435$$Nest$fgetoverlayView(this.this$0), (int) PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageWidth(), (int) PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageHeight());
        L73:
            r13.restore();
            invalidate();
            goto L74
        L72:
            PinchToZoomHelper.m17433$$Nest$fgetmediaSpoilerEffect(r18).setColor(ColorUtils.setAlphaComponent(-1, (int) ((Color.alpha(-1) * 0.325f) * PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getAlpha())));
            PinchToZoomHelper.m17433$$Nest$fgetmediaSpoilerEffect(this.this$0).setBounds((int) PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX(), (int) PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY(), (int) PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX2(), (int) PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY2());
            PinchToZoomHelper.m17433$$Nest$fgetmediaSpoilerEffect(this.this$0).draw(r13);
        L74:
            r13.restore();
            return;
        L38:
            if (PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0) == null) goto L57;
            float r08 = PinchToZoomHelper.m17438$$Nest$fgetprogressToFullView(this.this$0);
            PinchToZoomHelper r19 = this.this$0;
            if (r08 != 1.0f) goto L42;
            PinchToZoomHelper.m17423$$Nest$fgetfullImage(r19).setImageCoords(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageWidth(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageHeight());
            PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).draw(r13);
            if (PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).getLottieAnimation() == null) goto L54;
        L55:
            invalidate();
            goto L57
        L54:
            if (PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).getAnimation() == null) goto L57;
        L42:
            if (PinchToZoomHelper.m17417$$Nest$fgetchildImage(r19).getLottieAnimation() == null) goto L44;
        L49:
            invalidate();
        L50:
            PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).draw(r13);
            PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).setImageCoords(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageWidth(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageHeight());
            PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).draw(r13);
            goto L57
        L44:
            if (PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getAnimation() != null) goto L49;
            if (PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).getLottieAnimation() != null) goto L49;
            if (PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).getAnimation() == null) goto L50;
        L57:
            if (PinchToZoomHelper.m17419$$Nest$fgetchildTextureViewContainer(this.this$0) == null) goto L68;
            if (PinchToZoomHelper.m17418$$Nest$fgetchildTextureView(this.this$0) != null) goto L61;
            PinchToZoomHelper.m17419$$Nest$fgetchildTextureViewContainer(this.this$0);
        L61:
            r13.save();
            r13.translate(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageX(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageY());
            float r09 = Math.max(PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageWidth() / PinchToZoomHelper.m17419$$Nest$fgetchildTextureViewContainer(this.this$0).getMeasuredWidth(), PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).getImageHeight() / PinchToZoomHelper.m17419$$Nest$fgetchildTextureViewContainer(this.this$0).getMeasuredHeight());
            if (PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).isAspectFit() == false) goto L64;
            r13.scale(r09, r09, PinchToZoomHelper.m17419$$Nest$fgetchildTextureViewContainer(this.this$0).getMeasuredWidth() / 2.0f, 0.0f);
        L65:
            PinchToZoomHelper.m17419$$Nest$fgetchildTextureViewContainer(this.this$0).draw(r13);
            r13.restore();
            goto L68
        L64:
            r13.scale(r09, r09);
        L25:
            if (r3 >= 1.0f) goto L28;
            float r46 = 0.0f;
        L31:
            float r55 = (PinchToZoomHelper.m17424$$Nest$fgetfullImageHeight(this.this$0) - PinchToZoomHelper.m17427$$Nest$fgetimageHeight(this.this$0)) / 2.0f;
            float r63 = ((PinchToZoomHelper.m17425$$Nest$fgetfullImageWidth(this.this$0) - PinchToZoomHelper.m17428$$Nest$fgetimageWidth(this.this$0)) / 2.0f) * r46;
            float r9 = PinchToZoomHelper.m17429$$Nest$fgetimageX(this.this$0) - r63;
            float r56 = r55 * r46;
            float r47 = PinchToZoomHelper.m17430$$Nest$fgetimageY(this.this$0) - r56;
            if (PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0) == null) goto L34;
            PinchToZoomHelper.m17417$$Nest$fgetchildImage(this.this$0).setImageCoords(r9, r47, PinchToZoomHelper.m17428$$Nest$fgetimageWidth(this.this$0) + (r63 * 2.0f), PinchToZoomHelper.m17427$$Nest$fgetimageHeight(this.this$0) + (r56 * 2.0f));
        L34:
            r52 = r47;
            r44 = r9;
            goto L36
        L28:
            if (r3 >= 1.4f) goto L30;
            r46 = (r3 - 1.0f) / 0.4f;
            goto L31
        L30:
            r46 = 1.0f;
            goto L31
        L12:
            if (PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).hasNotThumb() == false) goto L20;
            if (PinchToZoomHelper.m17438$$Nest$fgetprogressToFullView(this.this$0) == 1.0f) goto L19;
            PinchToZoomHelper r48 = this.this$0;
            PinchToZoomHelper.m17441$$Nest$fputprogressToFullView(r48, PinchToZoomHelper.m17438$$Nest$fgetprogressToFullView(r48) + 0.10666667f);
            float r49 = PinchToZoomHelper.m17438$$Nest$fgetprogressToFullView(this.this$0);
            PinchToZoomHelper r57 = this.this$0;
            if (r49 <= 1.0f) goto L18;
            PinchToZoomHelper.m17441$$Nest$fputprogressToFullView(r57, 1.0f);
            goto L19
        L18:
            r57.invalidateViews();
        L19:
            PinchToZoomHelper.m17423$$Nest$fgetfullImage(this.this$0).setAlpha(PinchToZoomHelper.m17438$$Nest$fgetprogressToFullView(this.this$0));
            goto L20
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r14) {
            PinchToZoomHelper r0 = this.this$0;
            if (r0.finishTransition == null) goto L5;
        L10:
            float r02 = PinchToZoomHelper.m17422$$Nest$fgetfinishProgress(this.this$0) * CubicBezierInterpolator.DEFAULT.getInterpolation(PinchToZoomHelper.m17421$$Nest$fgetenterProgress(this.this$0));
            float r1 = getMeasuredHeight();
            if (r02 == 1.0f) goto L15;
            PinchToZoomHelper r3 = this.this$0;
            ClipBoundsListener r5 = r3.clipBoundsListener;
            if (r5 == null) goto L15;
            r5.getClipTopBottom(PinchToZoomHelper.m17420$$Nest$fgetclipTopBottom(r3));
            r14.save();
            float r32 = 1.0f - r02;
            float r12 = PinchToZoomHelper.m17420$$Nest$fgetclipTopBottom(this.this$0)[0] * r32;
            float r33 = (getMeasuredHeight() * r02) + (PinchToZoomHelper.m17420$$Nest$fgetclipTopBottom(this.this$0)[1] * r32);
            r14.clipRect(0.0f, r12, getMeasuredWidth(), r33);
            drawImage(r14);
            super.dispatchDraw(r14);
            r14.restore();
            float r11 = r12;
            float r122 = r33;
        L16:
            this.this$0.drawOverlays(r14, 1.0f - r02, this.this$0.parentOffsetX - getLeft(), this.this$0.parentOffsetY - getTop(), r11, r122);
            return;
        L15:
            drawImage(r14);
            super.dispatchDraw(r14);
            r122 = r1;
            r11 = 0.0f;
            goto L16
        L5:
            if (PinchToZoomHelper.m17421$$Nest$fgetenterProgress(r0) == 1.0f) goto L10;
            PinchToZoomHelper r03 = this.this$0;
            PinchToZoomHelper.m17440$$Nest$fputenterProgress(r03, PinchToZoomHelper.m17421$$Nest$fgetenterProgress(r03) + 0.07272727f);
            float r04 = PinchToZoomHelper.m17421$$Nest$fgetenterProgress(this.this$0);
            PinchToZoomHelper r13 = this.this$0;
            if (r04 <= 1.0f) goto L9;
            PinchToZoomHelper.m17440$$Nest$fputenterProgress(r13, 1.0f);
            goto L10
        L9:
            r13.invalidateViews();
            goto L10
        }
    }

    public static /* synthetic */ void $r8$lambda$0fG9VzWZn2W_vMOvb3hUfK1Z36c(PinchToZoomHelper r0, ValueAnimator r1) {
        r0.lambda$finishZoom$0(r1);
    }

    /* renamed from: -$$Nest$fgetblurImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m17415$$Nest$fgetblurImage(PinchToZoomHelper r0) {
        return r0.blurImage;
    }

    /* renamed from: -$$Nest$fgetchild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m17416$$Nest$fgetchild(PinchToZoomHelper r0) {
        return r0.child;
    }

    /* renamed from: -$$Nest$fgetchildImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m17417$$Nest$fgetchildImage(PinchToZoomHelper r0) {
        return r0.childImage;
    }

    /* renamed from: -$$Nest$fgetchildTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m17418$$Nest$fgetchildTextureView(PinchToZoomHelper r0) {
        return r0.childTextureView;
    }

    /* renamed from: -$$Nest$fgetchildTextureViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m17419$$Nest$fgetchildTextureViewContainer(PinchToZoomHelper r0) {
        return r0.childTextureViewContainer;
    }

    /* renamed from: -$$Nest$fgetclipTopBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m17420$$Nest$fgetclipTopBottom(PinchToZoomHelper r0) {
        return r0.clipTopBottom;
    }

    /* renamed from: -$$Nest$fgetenterProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17421$$Nest$fgetenterProgress(PinchToZoomHelper r0) {
        return r0.enterProgress;
    }

    /* renamed from: -$$Nest$fgetfinishProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17422$$Nest$fgetfinishProgress(PinchToZoomHelper r0) {
        return r0.finishProgress;
    }

    /* renamed from: -$$Nest$fgetfullImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m17423$$Nest$fgetfullImage(PinchToZoomHelper r0) {
        return r0.fullImage;
    }

    /* renamed from: -$$Nest$fgetfullImageHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17424$$Nest$fgetfullImageHeight(PinchToZoomHelper r0) {
        return r0.fullImageHeight;
    }

    /* renamed from: -$$Nest$fgetfullImageWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17425$$Nest$fgetfullImageWidth(PinchToZoomHelper r0) {
        return r0.fullImageWidth;
    }

    /* renamed from: -$$Nest$fgethasMediaSpoiler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17426$$Nest$fgethasMediaSpoiler(PinchToZoomHelper r0) {
        return r0.hasMediaSpoiler;
    }

    /* renamed from: -$$Nest$fgetimageHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17427$$Nest$fgetimageHeight(PinchToZoomHelper r0) {
        return r0.imageHeight;
    }

    /* renamed from: -$$Nest$fgetimageWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17428$$Nest$fgetimageWidth(PinchToZoomHelper r0) {
        return r0.imageWidth;
    }

    /* renamed from: -$$Nest$fgetimageX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17429$$Nest$fgetimageX(PinchToZoomHelper r0) {
        return r0.imageX;
    }

    /* renamed from: -$$Nest$fgetimageY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17430$$Nest$fgetimageY(PinchToZoomHelper r0) {
        return r0.imageY;
    }

    /* renamed from: -$$Nest$fgetinOverlayMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17431$$Nest$fgetinOverlayMode(PinchToZoomHelper r0) {
        return r0.inOverlayMode;
    }

    /* renamed from: -$$Nest$fgetisHardwareVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17432$$Nest$fgetisHardwareVideo(PinchToZoomHelper r0) {
        return r0.isHardwareVideo;
    }

    /* renamed from: -$$Nest$fgetmediaSpoilerEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpoilerEffect m17433$$Nest$fgetmediaSpoilerEffect(PinchToZoomHelper r0) {
        return r0.mediaSpoilerEffect;
    }

    /* renamed from: -$$Nest$fgetmediaSpoilerEffect2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpoilerEffect2 m17434$$Nest$fgetmediaSpoilerEffect2(PinchToZoomHelper r0) {
        return r0.mediaSpoilerEffect2;
    }

    /* renamed from: -$$Nest$fgetoverlayView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ZoomOverlayView m17435$$Nest$fgetoverlayView(PinchToZoomHelper r0) {
        return r0.overlayView;
    }

    /* renamed from: -$$Nest$fgetparentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m17436$$Nest$fgetparentView(PinchToZoomHelper r0) {
        return r0.parentView;
    }

    /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m17437$$Nest$fgetpath(PinchToZoomHelper r0) {
        return r0.path;
    }

    /* renamed from: -$$Nest$fgetprogressToFullView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17438$$Nest$fgetprogressToFullView(PinchToZoomHelper r0) {
        return r0.progressToFullView;
    }

    /* renamed from: -$$Nest$fgetspoilerRadii, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m17439$$Nest$fgetspoilerRadii(PinchToZoomHelper r0) {
        return r0.spoilerRadii;
    }

    /* renamed from: -$$Nest$fputenterProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17440$$Nest$fputenterProgress(PinchToZoomHelper r0, float r1) {
        r0.enterProgress = r1;
    }

    /* renamed from: -$$Nest$fputprogressToFullView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17441$$Nest$fputprogressToFullView(PinchToZoomHelper r0, float r1) {
        r0.progressToFullView = r1;
    }

    /* renamed from: -$$Nest$mupdateViewsLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17442$$Nest$mupdateViewsLocation(PinchToZoomHelper r0) {
        return r0.updateViewsLocation();
    }

    public PinchToZoomHelper() {
        this.fullImage = new ImageReceiver();
        this.blurImage = new ImageReceiver();
        this.mediaSpoilerEffect = new SpoilerEffect();
        this.path = new Path();
        this.spoilerRadii = new float[8];
        this.clipTopBottom = new float[2];
        this.parentView = null;
        this.fragmentView = null;
        this.isSimple = true;
    }

    public PinchToZoomHelper(ViewGroup r2, ViewGroup r3) {
        this.fullImage = new ImageReceiver();
        this.blurImage = new ImageReceiver();
        this.mediaSpoilerEffect = new SpoilerEffect();
        this.path = new Path();
        this.spoilerRadii = new float[8];
        this.clipTopBottom = new float[2];
        this.parentView = r2;
        this.fragmentView = r3;
        this.isSimple = false;
    }

    private boolean checkPointerIds(MotionEvent r5) {
        if (r5.getPointerCount() >= 2) goto L6;
        return false;
    L6:
        if (this.pointerId1 != r5.getPointerId(0)) goto L11;
        if (this.pointerId2 != r5.getPointerId(1)) goto L11;
        return true;
    L11:
        if (this.pointerId1 == r5.getPointerId(1)) goto L13;
    L15:
        return false;
    L13:
        if (this.pointerId2 != r5.getPointerId(0)) goto L15;
        return true;
    }

    private ColorMatrixColorFilter getFancyBlurFilter() {
        if (this.fancyBlurFilter != null) goto L6;
        ColorMatrix r0 = new ColorMatrix();
        AndroidUtilities.multiplyBrightnessColorMatrix(r0, 0.9f);
        AndroidUtilities.adjustSaturationColorMatrix(r0, 0.6f);
        this.fancyBlurFilter = new ColorMatrixColorFilter(r0);
    L6:
        return this.fancyBlurFilter;
    }

    private ImageLocation getImageLocation(MessageObject r6, int[] r7) {
        TLRPC.Message r5 = r6.messageOwner;
        if ((r5 instanceof TLRPC.TL_messageService) == true) goto L5;
        TLRPC.MessageMedia r52 = r5.media;
        if ((r52 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L22;
        if (r52.photo == null) goto L22;
    L26:
        if (r6.isGif() == true) goto L28;
        TLRPC.PhotoSize r53 = FileLoader.getClosestPhotoSizeWithSize(r6.photoThumbs, AndroidUtilities.getPhotoSize(), false, null, true);
        if (r53 == null) goto L37;
        if (r7 == null) goto L36;
        int r0 = r53.size;
        r7[0] = r0;
        if (r0 != 0) goto L36;
        r7[0] = -1;
    L36:
        return ImageLocation.getForObject(r53, r6.photoThumbsObject);
    L37:
        if (r7 == null) goto L54;
        r7[0] = -1;
    L54:
        return null;
    L28:
        return ImageLocation.getForDocument(r6.getDocument());
    L22:
        if ((r52 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L40;
        if (r52.webpage != null) goto L26;
    L40:
        if ((r52 instanceof TLRPC.TL_messageMediaInvoice) == false) goto L44;
        return ImageLocation.getForWebFile(WebFile.createWithWebDocument(((TLRPC.TL_messageMediaInvoice) r52).webPhoto));
    L44:
        if (r6.getDocument() == null) goto L54;
        TLRPC.Document r54 = r6.getDocument();
        if (MessageObject.isDocumentHasThumb(r6.getDocument()) == false) goto L54;
        TLRPC.PhotoSize r62 = FileLoader.getClosestPhotoSizeWithSize(r54.thumbs, 90);
        if (r7 == null) goto L53;
        int r02 = r62.size;
        r7[0] = r02;
        if (r02 != 0) goto L53;
        r7[0] = -1;
    L53:
        return ImageLocation.getForDocument(r62, r54);
    L5:
        if ((r5.action instanceof TLRPC.TL_messageActionUserUpdatedPhoto) == false) goto L7;
        return null;
    L7:
        TLRPC.PhotoSize r55 = FileLoader.getClosestPhotoSizeWithSize(r6.photoThumbs, AndroidUtilities.getPhotoSize());
        if (r55 == null) goto L15;
        if (r7 == null) goto L14;
        int r03 = r55.size;
        r7[0] = r03;
        if (r03 != 0) goto L14;
        r7[0] = -1;
    L14:
        return ImageLocation.getForObject(r55, r6.photoThumbsObject);
    L15:
        if (r7 == null) goto L54;
        r7[0] = -1;
        goto L54
    }

    private /* synthetic */ void lambda$finishZoom$0(ValueAnimator r1) {
        this.finishProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidateViews();
    }

    private void setFullImage(MessageObject r13) {
        if (r13 != null) goto L5;
        return;
    L5:
        if (r13.isPhoto() == true) goto L7;
        return;
    L7:
        ImageLocation r2 = getImageLocation(r13, new int[1]);
        if (r2 == null) goto L10;
        this.fullImage.setImage(r2, null, null, null, null, r0[0], null, r13, r13.isWebpage() ? 1 : 0);
        this.fullImage.setCrossfadeAlpha((byte) 2);
    L10:
        updateViewsLocation();
    }

    private boolean updateViewsLocation() {
        View r0 = this.child;
        float r1 = 0.0f;
        float r2 = 0.0f;
        float r3 = 0.0f;
    L4:
        if (r0 == this.parentView) goto L10;
        if (r0 == null) goto L6;
        r2 = r2 + r0.getLeft();
        r3 = r3 + r0.getTop();
        if ((r0.getParent() instanceof View) == false) goto L10;
        r0 = (View) r0.getParent();
        goto L4
    L6:
        return false;
    L10:
        View r02 = this.child;
        float r4 = 0.0f;
    L12:
        if (r02 == this.fragmentView) goto L16;
        if (r02 == null) goto L14;
        r1 = r1 + r02.getLeft();
        r4 = r4 + r02.getTop();
        r02 = (View) r02.getParent();
        goto L12
    L14:
        return false;
    L16:
        this.fragmentOffsetX = r1;
        this.fragmentOffsetY = r4;
        this.parentOffsetX = r2;
        this.parentOffsetY = r3;
        return true;
    }

    public void applyTransform(Canvas r5) {
        if (this.inOverlayMode == false) goto L6;
        r5.save();
        float r0 = this.pinchScale;
        float r1 = this.finishProgress;
        float r02 = ((r0 * r1) + 1.0f) - r1;
        r5.scale(r02, r02, this.parentOffsetX + this.pinchCenterX, this.parentOffsetY + this.pinchCenterY);
        float r03 = this.parentOffsetX;
        float r12 = this.pinchTranslationX;
        float r2 = this.finishProgress;
        r5.translate(r03 + (r12 * r2), this.parentOffsetY + (this.pinchTranslationY * r2));
        return;
    }

    public boolean checkPinchToZoom(MotionEvent r9, View r10, ImageReceiver r11, View r12, View r13, MessageObject r14) {
        return checkPinchToZoom(r9, r10, r11, r12, r13, r14, 0);
    }

    public boolean checkPinchToZoom(MotionEvent r13, View r14, ImageReceiver r15, View r16, View r17, MessageObject r18, int r19) {
        if (zoomEnabled(r14, r15) == true) goto L6;
        return false;
    L6:
        if (r13.getActionMasked() == 0) goto L52;
        if (r13.getActionMasked() == 5) goto L52;
        if (r13.getActionMasked() != 2) goto L36;
        if (this.isInPinchToZoomTouchMode == false) goto L36;
        int r8 = -1;
        int r9 = -1;
        int r4 = 0;
    L16:
        if (r4 >= r13.getPointerCount()) goto L24;
        if (this.pointerId1 != r13.getPointerId(r4)) goto L21;
        r8 = r4;
    L21:
        if (this.pointerId2 != r13.getPointerId(r4)) goto L23;
        r9 = r4;
    L23:
        r4 = r4 + 1;
        goto L16
    L24:
        if (r8 == (-1)) goto L33;
        if (r9 == (-1)) goto L33;
        float r0 = ((float) Math.hypot(r13.getX(r9) - r13.getX(r8), r13.getY(r9) - r13.getY(r8))) / this.pinchStartDistance;
        this.pinchScale = r0;
        if (r0 > 1.005f) goto L30;
    L32:
        float r1 = (r13.getX(r8) + r13.getX(r9)) / 2.0f;
        float r3 = (r13.getY(r8) + r13.getY(r9)) / 2.0f;
        float r132 = this.pinchStartCenterX - r1;
        float r12 = this.pinchStartCenterY - r3;
        float r32 = this.pinchScale;
        this.pinchTranslationX = (-r132) / r32;
        this.pinchTranslationY = (-r12) / r32;
        invalidateViews();
    L57:
        return isInOverlayModeFor(r14);
    L30:
        if (isInOverlayMode() == true) goto L32;
        this.pinchStartDistance = (float) Math.hypot(r13.getX(r9) - r13.getX(r8), r13.getY(r9) - r13.getY(r8));
        float r02 = (r13.getX(r8) + r13.getX(r9)) / 2.0f;
        this.pinchCenterX = r02;
        this.pinchStartCenterX = r02;
        float r03 = (r13.getY(r8) + r13.getY(r9)) / 2.0f;
        this.pinchCenterY = r03;
        this.pinchStartCenterY = r03;
        this.pinchScale = 1.0f;
        this.pinchTranslationX = 0.0f;
        this.pinchTranslationY = 0.0f;
        r14.getParent().requestDisallowInterceptTouchEvent(true);
        startZoom(r14, r15, r16, r17, r18, r19);
    L33:
        this.isInPinchToZoomTouchMode = false;
        r14.getParent().requestDisallowInterceptTouchEvent(false);
        finishZoom();
        return false;
    L36:
        if (r13.getActionMasked() == 1) goto L44;
        if (r13.getActionMasked() != 6) goto L42;
        if (checkPointerIds(r13) == true) goto L44;
    L42:
        if (r13.getActionMasked() != 3) goto L57;
    L44:
        if (this.isInPinchToZoomTouchMode == false) goto L57;
        this.isInPinchToZoomTouchMode = false;
        if (r14 != null) goto L48;
    L50:
        finishZoom();
        goto L57
    L48:
        if (r14.getParent() == null) goto L50;
        r14.getParent().requestDisallowInterceptTouchEvent(false);
    L52:
        if (this.isInPinchToZoomTouchMode == true) goto L57;
        if (r13.getPointerCount() != 2) goto L57;
        this.pinchStartDistance = (float) Math.hypot(r13.getX(1) - r13.getX(0), r13.getY(1) - r13.getY(0));
        float r42 = (r13.getX(0) + r13.getX(1)) / 2.0f;
        this.pinchCenterX = r42;
        this.pinchStartCenterX = r42;
        float r43 = (r13.getY(0) + r13.getY(1)) / 2.0f;
        this.pinchCenterY = r43;
        this.pinchStartCenterY = r43;
        this.pinchScale = 1.0f;
        this.pointerId1 = r13.getPointerId(0);
        this.pointerId2 = r13.getPointerId(1);
        this.isInPinchToZoomTouchMode = true;
        goto L57
    }

    public void clear() {
        if (this.inOverlayMode == false) goto L8;
        Callback r0 = this.callback;
        if (r0 == null) goto L7;
        r0.onZoomFinished(this.messageObject);
    L7:
        this.inOverlayMode = false;
    L8:
        ZoomOverlayView r02 = this.overlayView;
        if (r02 != null) goto L11;
    L20:
        View r03 = this.child;
        if (r03 == null) goto L23;
        r03.invalidate();
        this.child = null;
    L23:
        ImageReceiver r04 = this.childImage;
        if (r04 == null) goto L26;
        r04.onDetachedFromWindow();
        this.childImage.clearImage();
        this.childImage = null;
    L26:
        ImageReceiver r05 = this.fullImage;
        if (r05 == null) goto L29;
        r05.onDetachedFromWindow();
        this.fullImage.clearImage();
        this.fullImage = null;
    L29:
        ImageReceiver r06 = this.blurImage;
        if (r06 == null) goto L32;
        r06.onDetachedFromWindow();
        this.blurImage.clearImage();
        this.blurImage = null;
    L32:
        this.messageObject = null;
        return;
    L11:
        if (r02.getParent() == null) goto L20;
        this.parentView.removeView(this.overlayView);
        ZoomOverlayView.m17444$$Nest$fgetbackupImageView(this.overlayView).getImageReceiver().clearImage();
        SpoilerEffect2 r07 = this.mediaSpoilerEffect2;
        if (r07 == null) goto L15;
        r07.detach(this.overlayView);
        this.mediaSpoilerEffect2 = null;
    L15:
        ImageReceiver r08 = this.childImage;
        if (r08 == null) goto L20;
        Drawable r09 = r08.getDrawable();
        if ((r09 instanceof AnimatedFileDrawable) == false) goto L20;
        ((AnimatedFileDrawable) r09).removeSecondParentView(this.overlayView);
        goto L20
    }

    public void drawOverlays(Canvas r1, float r2, float r3, float r4, float r5, float r6) {
    }

    public void finishZoom() {
        if (this.finishTransition == null) goto L5;
        return;
    L5:
        if (this.inOverlayMode == true) goto L8;
        return;
    L8:
        if (this.isSimple == false) goto L10;
    L12:
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.finishTransition = r0;
        r0.addUpdateListener(new PinchToZoomHelper$$ExternalSyntheticLambda0(this));
        this.finishTransition.addListener(new AnonymousClass1(this));
        this.finishTransition.setDuration(220);
        this.finishTransition.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.finishTransition.start();
        return;
    L10:
        if (updateViewsLocation() == true) goto L12;
        clear();
        goto L12
    }

    public View getChild() {
        return this.child;
    }

    public ImageReceiver getPhotoImage() {
        return this.childImage;
    }

    public Bitmap getVideoBitmap(int r1, int r2) {
        ZoomOverlayView r0 = this.overlayView;
        if (r0 != null) goto L7;
        return null;
    L7:
        return ZoomOverlayView.m17446$$Nest$fgetvideoTextureView(r0).getBitmap(r1, r2);
    }

    public void invalidateViews() {
        if (this.isSimple == false) goto L7;
        View r0 = this.child;
        if (r0 == null) goto L7;
        r0.invalidate();
    L7:
        ZoomOverlayView r1 = this.overlayView;
        if (r1 == null) goto L11;
        r1.invalidate();
        return;
    }

    public boolean isInOverlayMode() {
        return this.inOverlayMode;
    }

    public boolean isInOverlayModeFor(View r2) {
        if (this.inOverlayMode == true) goto L5;
        return false;
    L5:
        if (r2 != this.child) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean onTouchEvent(MotionEvent r3) {
        if (updateViewsLocation() == true) goto L5;
        return false;
    L5:
        if (this.child == null) goto L10;
        r3.offsetLocation(-this.fragmentOffsetX, -this.fragmentOffsetY);
        return this.child.onTouchEvent(r3);
    L10:
        return false;
    }

    public void setCallback(Callback r1) {
        this.callback = r1;
    }

    public void setClipBoundsListener(ClipBoundsListener r1) {
        this.clipBoundsListener = r1;
    }

    public void startZoom(View r9, ImageReceiver r10, View r11, View r12, MessageObject r13, int r14) {
        this.child = r9;
        this.messageObject = r13;
        if (this.overlayView != null) goto L8;
        if (this.isSimple == true) goto L8;
        ZoomOverlayView r0 = new ZoomOverlayView(this, this.parentView.getContext());
        this.overlayView = r0;
        r0.setFocusable(false);
        this.overlayView.setFocusableInTouchMode(false);
        this.overlayView.setEnabled(false);
    L8:
        if (this.fullImage != null) goto L10;
        ImageReceiver r02 = new ImageReceiver();
        this.fullImage = r02;
        r02.setCrossfadeAlpha((byte) 2);
        this.fullImage.setCrossfadeWithOldImage(false);
        this.fullImage.onAttachedToWindow();
        ImageReceiver r03 = new ImageReceiver();
        this.blurImage = r03;
        r03.setCrossfadeAlpha((byte) 2);
        this.blurImage.setCrossfadeWithOldImage(false);
        this.blurImage.onAttachedToWindow();
    L10:
        this.inOverlayMode = true;
        this.finishProgress = 1.0f;
        this.progressToFullView = 0.0f;
        if (this.isSimple == true) goto L65;
        this.parentView.addView(this.overlayView);
        if (r13 != null) goto L15;
    L19:
        boolean r4 = false;
    L20:
        this.hasMediaSpoiler = r4;
        if (r4 == false) goto L30;
        if (this.mediaSpoilerEffect2 != null) goto L30;
        if (SpoilerEffect2.supports() == false) goto L30;
        SpoilerEffect2 r42 = SpoilerEffect2.getInstance(this.overlayView);
        this.mediaSpoilerEffect2 = r42;
        if (r42 == null) goto L30;
        r42.reassignAttach(this.overlayView, r14);
    L30:
        if (this.blurImage.getBitmap() == null) goto L33;
        this.blurImage.getBitmap().recycle();
        this.blurImage.setImageBitmap(null);
    L33:
        if (r10.getBitmap() != null) goto L35;
    L39:
        this.blurImage.setColorFilter(null);
    L40:
        setFullImage(r13);
        this.imageX = r10.getImageX();
        this.imageY = r10.getImageY() + r9.getPaddingTop();
        this.imageHeight = r10.getImageHeight();
        this.imageWidth = r10.getImageWidth();
        this.fullImageHeight = r10.getBitmapHeight();
        float r92 = r10.getBitmapWidth();
        this.fullImageWidth = r92;
        float r142 = this.fullImageHeight;
        float r43 = r142 / r92;
        float r5 = this.imageHeight;
        float r6 = this.imageWidth;
        if (r43 != (r5 / r6)) goto L43;
        this.fullImageHeight = r5;
        this.fullImageWidth = r6;
    L47:
        if (r13 != null) goto L49;
    L61:
        this.isHardwareVideo = false;
        ImageReceiver r93 = new ImageReceiver();
        this.childImage = r93;
        this.childTextureViewContainer = r11;
        this.childTextureView = r12;
        r93.onAttachedToWindow();
        Drawable r94 = r10.getDrawable();
        this.childImage.setImageBitmap(r94);
        if ((r94 instanceof AnimatedFileDrawable) == false) goto L64;
        AnimatedFileDrawable r95 = (AnimatedFileDrawable) r94;
        r95.addSecondParentView(this.overlayView);
        r95.setInvalidateParentViewWithSecond(true);
    L64:
        this.childImage.setImageCoords(this.imageX, this.imageY, this.imageWidth, this.imageHeight);
        this.childImage.setAspectFit(r10.isAspectFit());
        this.childImage.setRoundRadius(r10.getRoundRadius(true));
        this.fullImage.setRoundRadius(r10.getRoundRadius(true));
        this.fullImage.setAspectFit(r10.isAspectFit());
        ZoomOverlayView.m17445$$Nest$fgetvideoPlayerContainer(this.overlayView).setVisibility(8);
        goto L65
    L49:
        if (r13.isVideo() == false) goto L61;
        if (MediaController.getInstance().isPlayingMessage(r13) == false) goto L61;
        this.isHardwareVideo = true;
        MediaController.getInstance().setTextureView(ZoomOverlayView.m17446$$Nest$fgetvideoTextureView(this.overlayView), ZoomOverlayView.m17443$$Nest$fgetaspectRatioFrameLayout(this.overlayView), ZoomOverlayView.m17445$$Nest$fgetvideoPlayerContainer(this.overlayView), true);
        FrameLayout.LayoutParams r96 = (FrameLayout.LayoutParams) ZoomOverlayView.m17445$$Nest$fgetvideoPlayerContainer(this.overlayView).getLayoutParams();
        ZoomOverlayView.m17445$$Nest$fgetvideoPlayerContainer(this.overlayView).setTag(R.id.parent_tag, r10);
        if (r96.width == r10.getImageWidth()) goto L55;
    L56:
        ZoomOverlayView.m17443$$Nest$fgetaspectRatioFrameLayout(this.overlayView).setResizeMode(3);
        r96.width = (int) r10.getImageWidth();
        r96.height = (int) r10.getImageHeight();
        ZoomOverlayView.m17445$$Nest$fgetvideoPlayerContainer(this.overlayView).setLayoutParams(r96);
    L57:
        ZoomOverlayView.m17446$$Nest$fgetvideoTextureView(this.overlayView).setScaleX(1.0f);
        ZoomOverlayView.m17446$$Nest$fgetvideoTextureView(this.overlayView).setScaleY(1.0f);
        if (this.callback == null) goto L60;
        ZoomOverlayView.m17444$$Nest$fgetbackupImageView(this.overlayView).setImageBitmap(this.callback.getCurrentTextureView().getBitmap((int) this.fullImageWidth, (int) this.fullImageHeight));
        ZoomOverlayView.m17444$$Nest$fgetbackupImageView(this.overlayView).setSize((int) this.fullImageWidth, (int) this.fullImageHeight);
        ZoomOverlayView.m17444$$Nest$fgetbackupImageView(this.overlayView).getImageReceiver().setRoundRadius(r10.getRoundRadius(true));
    L60:
        ZoomOverlayView.m17445$$Nest$fgetvideoPlayerContainer(this.overlayView).setVisibility(0);
        goto L65
    L55:
        if (r96.height == r10.getImageHeight()) goto L57;
    L43:
        if ((r142 / r92) >= (r5 / r6)) goto L45;
        this.fullImageWidth = (r92 / r142) * r5;
        this.fullImageHeight = r5;
        goto L47
    L45:
        this.fullImageHeight = (r142 / r92) * r6;
        this.fullImageWidth = r6;
        goto L47
    L35:
        if (r10.getBitmap().isRecycled() == true) goto L39;
        if (this.hasMediaSpoiler == false) goto L39;
        this.blurImage.setImageBitmap(Utilities.stackBlurBitmapMax(r10.getBitmap()));
        this.blurImage.setColorFilter(getFancyBlurFilter());
        goto L40
    L15:
        if (r13.hasMediaSpoilers() == false) goto L19;
        if (r13.isMediaSpoilersRevealed == true) goto L19;
        r4 = true;
    L65:
        Callback r97 = this.callback;
        if (r97 == null) goto L68;
        r97.onZoomStarted(r13);
    L68:
        this.enterProgress = 0.0f;
    }

    public boolean zoomEnabled(View r1, ImageReceiver r2) {
        if (this.isSimple == false) goto L6;
        return true;
    L6:
        if ((r2.getDrawable() instanceof AnimatedFileDrawable) == false) goto L13;
        if (((AnimatedFileDrawable) r2.getDrawable()).isLoadingStream() == false) goto L11;
        return false;
    L11:
        return true;
    L13:
        return r2.hasNotThumbOrOnlyStaticThumb();
    }
}
