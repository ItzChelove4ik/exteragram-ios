package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Scroller;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.StaticLayoutEx;
import org.telegram.ui.Stories.SelfStoryViewsView;

/* loaded from: classes7.dex */
public abstract class SelfStoriesPreviewView extends View {
    boolean checkScroll;
    int childPadding;
    GestureDetector gestureDetector;
    GradientDrawable gradientDrawable;
    ArrayList<ImageHolder> imageReceiversTmp;
    public int imagesFromH;
    public int imagesFromW;
    public int imagesFromY;
    private boolean isAttachedToWindow;
    private int lastClosestPosition;
    ArrayList<ImageHolder> lastDrawnImageReceivers;
    float maxScroll;
    float minScroll;
    float progressToOpen;
    ValueAnimator scrollAnimator;
    private int scrollToPositionInLayout;
    float scrollX;
    Scroller scroller;
    ArrayList<SelfStoryViewsView.StoryItemInternal> storyItems;
    private float textWidth;
    float topPadding;
    private int viewH;
    private int viewW;

    /* renamed from: org.telegram.ui.Stories.SelfStoriesPreviewView$1, reason: invalid class name */
    public class AnonymousClass1 implements GestureDetector.OnGestureListener {
        final /* synthetic */ SelfStoriesPreviewView this$0;

        public AnonymousClass1(SelfStoriesPreviewView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r2) {
            this.this$0.scroller.abortAnimation();
            ValueAnimator r22 = this.this$0.scrollAnimator;
            if (r22 == null) goto L5;
            r22.removeAllListeners();
            this.this$0.scrollAnimator.cancel();
            this.this$0.scrollAnimator = null;
        L5:
            SelfStoriesPreviewView r1 = this.this$0;
            r1.checkScroll = false;
            r1.onDragging();
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r10, MotionEvent r11, float r12, float r13) {
            SelfStoriesPreviewView r102 = this.this$0;
            r102.scroller.fling((int) r102.scrollX, 0, (int) (-r12), 0, (int) r102.minScroll, (int) r102.maxScroll, 0, 0);
            this.this$0.invalidate();
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent r1) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            SelfStoriesPreviewView r0 = this.this$0;
            float r12 = r0.scrollX + r3;
            r0.scrollX = r12;
            float r22 = r0.minScroll;
            if (r12 >= r22) goto L5;
            r0.scrollX = r22;
        L5:
            float r13 = r0.scrollX;
            float r23 = r0.maxScroll;
            if (r13 <= r23) goto L8;
            r0.scrollX = r23;
        L8:
            r0.invalidate();
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent r1) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r7) {
            int r1 = 0;
        L4:
            if (r1 >= this.this$0.lastDrawnImageReceivers.size()) goto L12;
            ImageHolder r2 = this.this$0.lastDrawnImageReceivers.get(r1);
            if (this.this$0.lastDrawnImageReceivers.get(r1).receiver.getDrawRegion().contains(r7.getX(), r7.getY()) == false) goto L11;
            int r3 = SelfStoriesPreviewView.m19652$$Nest$fgetlastClosestPosition(this.this$0);
            int r22 = r2.position;
            SelfStoriesPreviewView r4 = this.this$0;
            if (r3 == r22) goto L10;
            r4.scrollToPosition(r22, true, false);
            goto L11
        L10:
            r4.onCenteredImageTap();
        L11:
            r1 = r1 + 1;
            goto L4
        L12:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoriesPreviewView$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ SelfStoriesPreviewView this$0;

        public AnonymousClass2(SelfStoriesPreviewView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            SelfStoriesPreviewView r0 = this.this$0;
            r0.scrollX = ((Float) r2.getAnimatedValue()).floatValue();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoriesPreviewView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ SelfStoriesPreviewView this$0;

        public AnonymousClass3(SelfStoriesPreviewView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.scrollAnimator = null;
        }
    }

    public class ImageHolder {
        StaticLayout layout;
        TextPaint paint;
        int position;
        ImageReceiver receiver;
        SelfStoryViewsView.StoryItemInternal storyItem;
        final /* synthetic */ SelfStoriesPreviewView this$0;

        public ImageHolder(SelfStoriesPreviewView r2) {
            this.this$0 = r2;
            this.receiver = new ImageReceiver(r2);
            this.paint = new TextPaint(1);
            this.receiver.setAllowLoadingOnAttachedOnly(true);
            this.receiver.setRoundRadius(AndroidUtilities.dp(6.0f));
            this.paint.setColor(-1);
            this.paint.setTextSize(AndroidUtilities.dp(13.0f));
        }

        private void updateLayout() {
            SpannableStringBuilder r1 = new SpannableStringBuilder();
            TL_stories.StoryItem r2 = this.storyItem.storyItem;
            if (r2 == null) goto L6;
            SelfStoriesPreviewView.m19654$$Nest$mformatCounterText(this.this$0, r1, r2.views, false);
        L6:
            if (r1.length() != 0) goto L9;
            this.layout = null;
            return;
        L9:
            TextPaint r22 = this.paint;
            int r3 = (int) (SelfStoriesPreviewView.m19653$$Nest$fgettextWidth(this.this$0) + 1.0f);
            Layout.Alignment r15 = Layout.Alignment.ALIGN_CENTER;
            StaticLayout r12 = StaticLayoutEx.createStaticLayout(r1, r22, r3, r15, 1.0f, 0.0f, false, null, Integer.MAX_VALUE, 1);
            this.layout = r12;
            if (r12.getLineCount() <= 1) goto L13;
            SpannableStringBuilder r122 = new SpannableStringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
            SelfStoriesPreviewView.m19654$$Nest$mformatCounterText(this.this$0, r122, this.storyItem.storyItem.views, true);
            this.layout = StaticLayoutEx.createStaticLayout(r122, this.paint, (int) (SelfStoriesPreviewView.m19653$$Nest$fgettextWidth(this.this$0) + 1.0f), r15, 1.0f, 0.0f, false, null, Integer.MAX_VALUE, 2);
            return;
        }

        public void draw(Canvas r2, float r3, float r4, int r5, int r6, int r7, int r8) {
            this.receiver.setImageCoords(r5, r6, r7, r8);
            this.receiver.setAlpha(r3);
            this.receiver.draw(r2);
            this.receiver.setAlpha(1.0f);
            if (this.layout == null) goto L6;
            int r32 = (int) (r3 * 255.0f);
            this.paint.setAlpha(r32);
            this.this$0.gradientDrawable.setAlpha(r32);
            this.this$0.gradientDrawable.setBounds((int) this.receiver.getImageX(), (int) (this.receiver.getImageY2() - (AndroidUtilities.dp(24.0f) * r4)), (int) this.receiver.getImageX2(), ((int) this.receiver.getImageY2()) + 2);
            this.this$0.gradientDrawable.draw(r2);
            r2.save();
            r2.scale(r4, r4, this.receiver.getCenterX(), this.receiver.getImageY2() - (AndroidUtilities.dp(8.0f) * r4));
            r2.translate(this.receiver.getCenterX() - (SelfStoriesPreviewView.m19653$$Nest$fgettextWidth(this.this$0) / 2.0f), (this.receiver.getImageY2() - (AndroidUtilities.dp(8.0f) * r4)) - this.layout.getHeight());
            this.layout.draw(r2);
            r2.restore();
            return;
        }

        public void onBind(int r3) {
            if (r3 >= 0) goto L4;
            return;
        L4:
            if (r3 >= this.this$0.storyItems.size()) goto L16;
            this.storyItem = this.this$0.storyItems.get(r3);
            if (SelfStoriesPreviewView.m19651$$Nest$fgetisAttachedToWindow(this.this$0) == false) goto L9;
            this.receiver.onAttachedToWindow();
        L9:
            SelfStoryViewsView.StoryItemInternal r32 = this.storyItem;
            TL_stories.StoryItem r0 = r32.storyItem;
            ImageReceiver r1 = this.receiver;
            if (r0 == null) goto L12;
            StoriesUtilities.setImage(r1, r0);
        L13:
            updateLayout();
            return;
        L12:
            StoriesUtilities.setImage(r1, r32.uploadingStory);
            goto L13
        }

        public void onDetach() {
            this.receiver.onDetachedFromWindow();
        }

        public void update() {
            updateLayout();
        }
    }

    /* renamed from: -$$Nest$fgetisAttachedToWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19651$$Nest$fgetisAttachedToWindow(SelfStoriesPreviewView r0) {
        return r0.isAttachedToWindow;
    }

    /* renamed from: -$$Nest$fgetlastClosestPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19652$$Nest$fgetlastClosestPosition(SelfStoriesPreviewView r0) {
        return r0.lastClosestPosition;
    }

    /* renamed from: -$$Nest$fgettextWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19653$$Nest$fgettextWidth(SelfStoriesPreviewView r0) {
        return r0.textWidth;
    }

    /* renamed from: -$$Nest$mformatCounterText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19654$$Nest$mformatCounterText(SelfStoriesPreviewView r0, SpannableStringBuilder r1, TL_stories.StoryViews r2, boolean r3) {
        r0.formatCounterText(r1, r2, r3);
    }

    public SelfStoriesPreviewView(Context r4) {
        super(r4);
        this.scrollToPositionInLayout = -1;
        this.storyItems = new ArrayList();
        this.imageReceiversTmp = new ArrayList();
        this.lastDrawnImageReceivers = new ArrayList();
        this.gestureDetector = new GestureDetector(new AnonymousClass1(this));
        this.scroller = new Scroller(r4, new OvershootInterpolator());
        this.gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, ColorUtils.setAlphaComponent(-16777216, 160)});
    }

    private ImageHolder findOrCreateImageReceiver(int r3, ArrayList<ImageHolder> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L10;
        if (r4.get(r0).position == r3) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r4.remove(r0);
    L10:
        ImageHolder r42 = new ImageHolder(this);
        r42.onBind(r3);
        r42.position = r3;
        return r42;
    }

    private void formatCounterText(SpannableStringBuilder r6, TL_stories.StoryViews r7, boolean r8) {
        if (r7 != null) goto L5;
        int r0 = 0;
    L6:
        if (r0 <= 0) goto L16;
        r6.append("d");
        r6.setSpan(new ColoredImageSpan(R.drawable.msg_views), r6.length() - 1, r6.length(), 0);
        r6.append(" ").append(AndroidUtilities.formatWholeNumber(r0, 0));
        if (r7 != null) goto L10;
        return;
    L10:
        if (r7.reactions_count <= 0) goto L18;
        if (r8 == false) goto L13;
        String r82 = "\n";
    L14:
        r6.append(r82);
        r6.append("d");
        r6.setSpan(new ColoredImageSpan(R.drawable.mini_like_filled), r6.length() - 1, r6.length(), 0);
        r6.append(" ").append(AndroidUtilities.formatWholeNumber(r7.reactions_count, 0));
        return;
    L13:
        r82 = "  ";
        goto L14
    L18:
        return;
    L16:
        return;
    L5:
        r0 = r7.views_count;
        goto L6
    }

    private void scrollToClosest() {
        int r0 = this.lastClosestPosition;
        if (r0 < 0) goto L6;
        scrollToPosition(r0, true, true);
        return;
    }

    private void updateScrollParams() {
        int r0 = getMeasuredWidth();
        int r1 = this.viewW;
        this.minScroll = (-(r0 - r1)) / 2.0f;
        this.maxScroll = ((((r1 + this.childPadding) * this.storyItems.size()) - this.childPadding) - getMeasuredWidth()) + ((getMeasuredWidth() - this.viewW) / 2.0f);
    }

    public void abortScroll() {
        this.scroller.abortAnimation();
        ValueAnimator r0 = this.scrollAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.scrollAnimator = null;
    L5:
        scrollToPosition(this.lastClosestPosition, false, true);
    }

    public ImageHolder getCenteredImageReciever() {
        int r0 = 0;
    L4:
        if (r0 >= this.lastDrawnImageReceivers.size()) goto L10;
        if (this.lastDrawnImageReceivers.get(r0).position == this.lastClosestPosition) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.lastDrawnImageReceivers.get(r0);
    L10:
        return null;
    }

    public int getClosestPosition() {
        return this.lastClosestPosition;
    }

    public float getFinalHeight() {
        return AndroidUtilities.dp(180.0f);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
    }

    public abstract void onCenteredImageTap();

    public void onClosestPositionChanged(int r1) {
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = 0;
        this.isAttachedToWindow = false;
    L3:
        int r1 = this.lastDrawnImageReceivers.size();
        ArrayList<ImageHolder> r2 = this.lastDrawnImageReceivers;
        if (r0 >= r1) goto L6;
        r2.get(r0).onDetach();
        r0 = r0 + 1;
        goto L3
    L6:
        r2.clear();
    }

    public abstract void onDragging();

    @Override // android.view.View
    public void onDraw(Canvas r21) {
        super.onDraw(r21);
        if (this.scroller.computeScrollOffset() == false) goto L6;
        this.scrollX = this.scroller.getCurrX();
        invalidate();
        this.checkScroll = true;
    L8:
        float r3 = 2.0f;
        float r2 = getMeasuredWidth() / 2.0f;
        this.imageReceiversTmp.clear();
        this.imageReceiversTmp.addAll(this.lastDrawnImageReceivers);
        this.lastDrawnImageReceivers.clear();
        int r5 = -1;
        float r6 = 2.1474836E9f;
        int r8 = -1;
        int r7 = 0;
    L10:
        if (r7 >= this.storyItems.size()) goto L45;
        float r9 = -this.scrollX;
        float r92 = r9 + ((this.childPadding + r10) * r7);
        float r10 = ((this.viewW / r3) + r92) - r2;
        float r11 = Math.abs(r10);
        if (r11 >= this.viewW) goto L14;
        float r12 = 1.0f - (Math.abs(r10) / this.viewW);
        float r15 = (0.2f * r12) + 1.0f;
    L15:
        if (r8 != r5) goto L17;
    L18:
        r8 = r7;
        r6 = r11;
    L19:
        int r112 = this.viewW;
        if (r10 >= 0.0f) goto L22;
        float r93 = r92 - ((r112 * 0.1f) * (1.0f - r12));
    L24:
        if (r93 <= getMeasuredWidth()) goto L26;
    L27:
        float r18 = r2;
        float r16 = r3;
        float r19 = r6;
    L43:
        r7 = r7 + 1;
        r3 = r16;
        r2 = r18;
        r6 = r19;
        r5 = -1;
        goto L10
    L26:
        if ((this.viewW + r93) < 0.0f) goto L27;
        ImageHolder r102 = findOrCreateImageReceiver(r7, this.imageReceiversTmp);
        int r113 = this.viewW;
        r16 = r3;
        float r32 = r113 * r15;
        int r4 = this.viewH;
        float r52 = r4 * r15;
        float r94 = r93 - ((r32 - r113) / r16);
        float r114 = this.topPadding - ((r52 - r4) / r16);
        if (this.progressToOpen == 0.0f) goto L32;
        int r42 = this.lastClosestPosition;
        if (r7 == r42) goto L32;
        float r43 = (r7 - r42) * getMeasuredWidth();
        float r13 = this.imagesFromY;
        float r152 = this.imagesFromH;
        float r17 = 1.0f;
        r18 = r2;
        r19 = r6;
        r102.receiver.setImageCoords(AndroidUtilities.lerp(r43, r94, this.progressToOpen), AndroidUtilities.lerp(r13, r114, this.progressToOpen), AndroidUtilities.lerp(this.imagesFromW, r32, this.progressToOpen), AndroidUtilities.lerp(r152, r52, this.progressToOpen));
    L36:
        if (this.progressToOpen != r17) goto L38;
    L39:
        r102.receiver.draw(r21);
        if (r102.layout == null) goto L42;
        int r33 = (int) (((r12 * 0.3f) + 0.7f) * 255.0f);
        this.gradientDrawable.setAlpha(r33);
        this.gradientDrawable.setBounds((int) r102.receiver.getImageX(), (int) (r102.receiver.getImageY2() - AndroidUtilities.dp(24.0f)), (int) r102.receiver.getImageX2(), ((int) r102.receiver.getImageY2()) + 2);
        this.gradientDrawable.draw(r21);
        r21.save();
        r21.translate(r102.receiver.getCenterX() - (this.textWidth / r16), (r102.receiver.getImageY2() - AndroidUtilities.dp(8.0f)) - r102.layout.getHeight());
        r102.paint.setAlpha(r33);
        r102.layout.draw(r21);
        r21.restore();
    L42:
        this.lastDrawnImageReceivers.add(r102);
        goto L43
    L38:
        if (r7 == this.lastClosestPosition) goto L42;
    L32:
        r18 = r2;
        r19 = r6;
        r17 = 1.0f;
        r102.receiver.setImageCoords(r94, r114, r32, r52);
        goto L36
    L22:
        r93 = r92 + ((r112 * 0.1f) * (1.0f - r12));
        goto L24
    L17:
        if (r11 >= r6) goto L19;
    L14:
        r12 = 0.0f;
        r15 = 1.0f;
        goto L15
    L45:
        if (this.scrollAnimator == null) goto L47;
    L49:
        int r44 = 0;
    L50:
        int r1 = this.imageReceiversTmp.size();
        ArrayList<ImageHolder> r22 = this.imageReceiversTmp;
        if (r44 >= r1) goto L53;
        r22.get(r44).onDetach();
        r44 = r44 + 1;
        goto L50
    L53:
        r22.clear();
        return;
    L47:
        if (this.lastClosestPosition == r8) goto L49;
        this.lastClosestPosition = r8;
        onClosestPositionChanged(r8);
        goto L49
    L6:
        if (this.checkScroll == false) goto L8;
        scrollToClosest();
        goto L8
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, r4);
        this.childPadding = AndroidUtilities.dp(8.0f);
        int r0 = (int) (AndroidUtilities.dp(180.0f) / 1.2f);
        this.viewH = r0;
        int r02 = (int) ((r0 / 16.0f) * 9.0f);
        this.viewW = r02;
        float r32 = r02 - AndroidUtilities.dp(8.0f);
        this.topPadding = ((AndroidUtilities.dp(180.0f) - this.viewH) / 2.0f) + AndroidUtilities.dp(20.0f);
        updateScrollParams();
        int r03 = 0;
        if (this.scrollToPositionInLayout < 0) goto L8;
        if (getMeasuredWidth() <= 0) goto L8;
        this.lastClosestPosition = -1;
        scrollToPosition(this.scrollToPositionInLayout, false, false);
        this.scrollToPositionInLayout = -1;
    L8:
        if (this.textWidth == r32) goto L13;
        this.textWidth = r32;
    L11:
        if (r03 >= this.lastDrawnImageReceivers.size()) goto L15;
        this.lastDrawnImageReceivers.get(r03).onBind(this.lastDrawnImageReceivers.get(r03).position);
        r03 = r03 + 1;
        goto L11
    L15:
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        this.gestureDetector.onTouchEvent(r3);
        if (r3.getAction() == 1) goto L7;
        if (r3.getAction() == 3) goto L7;
    L9:
        return true;
    L7:
        if (this.scroller.isFinished() == false) goto L9;
        scrollToClosest();
        goto L9
    }

    public void scrollToPosition(int r4, boolean r5, boolean r6) {
        if (this.lastClosestPosition != r4) goto L6;
        if (r6 == true) goto L6;
        return;
    L6:
        if (getMeasuredHeight() > 0) goto L9;
        return;
    L9:
        if (this.lastClosestPosition == r4) goto L11;
        this.lastClosestPosition = r4;
        onClosestPositionChanged(r4);
    L11:
        this.scroller.abortAnimation();
        this.checkScroll = false;
        ValueAnimator r0 = this.scrollAnimator;
        if (r0 == null) goto L15;
        r0.removeAllListeners();
        this.scrollAnimator.cancel();
        this.scrollAnimator = null;
    L15:
        if (r5 == true) goto L18;
        this.scrollX = (((-getMeasuredWidth()) / 2.0f) + (this.viewW / 2.0f)) + ((r6 + this.childPadding) * r4);
        invalidate();
        return;
    L18:
        float r52 = (((-getMeasuredWidth()) / 2.0f) + (this.viewW / 2.0f)) + ((r1 + this.childPadding) * r4);
        float r42 = this.scrollX;
        if (r52 == r42) goto L23;
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, r52});
        this.scrollAnimator = r43;
        r43.addUpdateListener(new AnonymousClass2(this));
        this.scrollAnimator.addListener(new AnonymousClass3(this));
        this.scrollAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.scrollAnimator.setDuration(200);
        this.scrollAnimator.start();
        return;
    }

    public void scrollToPositionWithOffset(int r7, float r8) {
        this.scroller.abortAnimation();
        if (Math.abs(r8) <= 1.0f) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.scrollAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.scrollAnimator = null;
    L8:
        float r02 = (((-getMeasuredWidth()) / 2.0f) + (this.viewW / 2.0f)) + ((r2 + this.childPadding) * r7);
        if (r8 <= 0.0f) goto L11;
        float r3 = (((-getMeasuredWidth()) / 2.0f) + (this.viewW / 2.0f)) + ((r4 + this.childPadding) * (r7 + 1));
    L13:
        if (r8 != 0.0f) goto L15;
        this.scrollX = r02;
    L16:
        this.checkScroll = false;
        invalidate();
        return;
    L15:
        this.scrollX = AndroidUtilities.lerp(r02, r3, r8);
        goto L16
    L11:
        r3 = (((-getMeasuredWidth()) / 2.0f) + (this.viewW / 2.0f)) + ((r4 + this.childPadding) * (r7 - 1));
        r8 = -r8;
        goto L13
    }

    public void setItems(ArrayList<SelfStoryViewsView.StoryItemInternal> r2, int r3) {
        this.storyItems.clear();
        this.storyItems.addAll(r2);
        updateScrollParams();
        int r0 = 0;
        if (getMeasuredHeight() <= 0) goto L5;
        scrollToPosition(r3, false, false);
    L7:
        if (r0 >= this.lastDrawnImageReceivers.size()) goto L9;
        this.lastDrawnImageReceivers.get(r0).onBind(this.lastDrawnImageReceivers.get(r0).position);
        r0 = r0 + 1;
        goto L7
    L9:
        return;
    L5:
        this.scrollToPositionInLayout = r3;
        goto L7
    }

    public void setProgressToOpen(float r2) {
        if (this.progressToOpen != r2) goto L5;
        return;
    L5:
        this.progressToOpen = r2;
        invalidate();
    }

    public void update() {
        int r0 = 0;
    L4:
        if (r0 >= this.lastDrawnImageReceivers.size()) goto L6;
        this.lastDrawnImageReceivers.get(r0).update();
        r0 = r0 + 1;
        goto L4
    }
}
