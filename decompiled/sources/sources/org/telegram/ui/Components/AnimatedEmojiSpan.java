package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ReplacementSpan;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class AnimatedEmojiSpan extends ReplacementSpan {
    private static boolean lockPositionChanging;
    private boolean animateChanges;
    public int cacheType;
    public TLRPC.Document document;
    public String documentAbsolutePath;
    public long documentId;
    public String emoji;
    public float extraScale;
    private Paint.FontMetricsInt fontMetrics;
    public boolean fromEmojiKeyboard;
    public boolean full;
    public boolean invert;
    private boolean isAdded;
    private boolean isRemoved;
    float lastDrawnCx;
    float lastDrawnCy;
    public boolean local;
    protected int measuredSize;
    private int minimumLineHeight;
    private ValueAnimator moveAnimator;
    boolean positionChanged;
    private boolean preserveFontMetrics;
    private boolean recordPositions;
    private Runnable removedAction;
    private float scale;
    private ValueAnimator scaleAnimator;
    public float size;
    boolean spanDrawn;
    public boolean standard;
    public boolean top;

    /* renamed from: org.telegram.ui.Components.AnimatedEmojiSpan$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ AnimatedEmojiSpan this$0;

        public AnonymousClass1(AnimatedEmojiSpan r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            AnimatedEmojiSpan.m8800$$Nest$fputscaleAnimator(this.this$0, null);
            AnimatedEmojiSpan.m8802$$Nest$sfputlockPositionChanging(false);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimatedEmojiSpan$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ AnimatedEmojiSpan this$0;

        public AnonymousClass2(AnimatedEmojiSpan r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AnimatedEmojiSpan.m8800$$Nest$fputscaleAnimator(this.this$0, null);
            if (AnimatedEmojiSpan.m8796$$Nest$fgetremovedAction(this.this$0) == null) goto L6;
            AnimatedEmojiSpan.m8796$$Nest$fgetremovedAction(this.this$0).run();
            AnimatedEmojiSpan.m8799$$Nest$fputremovedAction(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimatedEmojiSpan$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ AnimatedEmojiSpan this$0;

        public AnonymousClass3(AnimatedEmojiSpan r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            AnimatedEmojiSpan.m8797$$Nest$fputmoveAnimator(this.this$0, null);
        }
    }

    public static class AnimatedEmojiHolder implements InvalidateHolder {
        public float alpha;
        private ImageReceiver.BackgroundThreadDrawHolder[] backgroundDrawHolder;
        public AnimatedEmojiDrawable drawable;
        public Rect drawableBounds;
        public float drawingYOffset;
        public boolean insideSpoiler;
        private final boolean invalidateInParent;
        public Layout layout;
        public boolean skipDraw;
        public AnimatedEmojiSpan span;
        public SpansChunk spansChunk;
        public Drawable thumbDrawable;
        private final WeakReference<View> view;

        /* renamed from: -$$Nest$fgetbackgroundDrawHolder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageReceiver.BackgroundThreadDrawHolder[] m8803$$Nest$fgetbackgroundDrawHolder(AnimatedEmojiHolder r0) {
            return r0.backgroundDrawHolder;
        }

        /* renamed from: -$$Nest$fgetview, reason: not valid java name */
        public static /* bridge */ /* synthetic */ WeakReference m8804$$Nest$fgetview(AnimatedEmojiHolder r0) {
            return r0.view;
        }

        public AnimatedEmojiHolder(View r2, boolean r3) {
            this.backgroundDrawHolder = new ImageReceiver.BackgroundThreadDrawHolder[2];
            this.view = new WeakReference(r2);
            this.invalidateInParent = r3;
        }

        public void draw(Canvas r4, long r5, float r7, float r8, float r9, ColorFilter r10) {
            int r2 = 1;
            if (r7 != 0.0f) goto L7;
            if (r8 != 0.0f) goto L7;
        L10:
            this.skipDraw = false;
            AnimatedEmojiDrawable r72 = this.drawable;
            if (r72 != null) goto L27;
            if (this.thumbDrawable == null) goto L45;
            float r52 = this.span.getExtraScale();
            this.thumbDrawable.setAlpha((int) ((r9 * 255.0f) * this.alpha));
            this.thumbDrawable.setBounds(this.drawableBounds);
            if (r52 == 1.0f) goto L17;
        L21:
            r4.save();
            if (this.span.invert == false) goto L24;
            r2 = -1;
        L24:
            r4.scale(r2 * r52, r52, this.drawableBounds.centerX(), this.drawableBounds.centerY());
            this.thumbDrawable.draw(r4);
            r4.restore();
            return;
        L17:
            if (this.span.invert == true) goto L21;
            this.thumbDrawable.draw(r4);
            return;
        L45:
            return;
        L27:
            if (r72.getImageReceiver() == null) goto L46;
            AnimatedEmojiDrawable r73 = this.drawable;
            if (r10 != null) goto L31;
            r10 = Theme.chat_animatedEmojiTextColorFilter;
        L31:
            r73.setColorFilter(r10);
            this.drawable.setTime(r5);
            float r53 = this.span.getExtraScale();
            if (r53 == 1.0f) goto L34;
        L37:
            r4.save();
            if (this.span.invert == false) goto L40;
            r2 = -1;
        L40:
            r4.scale(r2 * r53, r53, this.drawableBounds.centerX(), this.drawableBounds.centerY());
            this.drawable.draw(r4, this.drawableBounds, r9 * this.alpha);
            r4.restore();
        L42:
            if (AnimatedEmojiSpan.m8801$$Nest$misAnimating(this.span) == false) goto L47;
            invalidate();
            return;
        L47:
            return;
        L34:
            if (this.span.invert == true) goto L37;
            this.drawable.draw(r4, this.drawableBounds, r9 * this.alpha);
            goto L42
        L46:
            return;
        L7:
            if (outOfBounds(r7, r8) == false) goto L10;
            this.skipDraw = true;
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
        public void invalidate() {
            View r0 = this.view.get();
            if (r0 != null) goto L6;
            return;
        L6:
            if (this.invalidateInParent == true) goto L8;
        L11:
            r0.invalidate(this.drawableBounds);
            return;
        L8:
            if (r0.getParent() == null) goto L11;
            ((View) r0.getParent()).invalidate();
        }

        public boolean outOfBounds(float r2, float r3) {
            Rect r1 = this.drawableBounds;
            if (r1.bottom >= r2) goto L5;
            return true;
        L5:
            if (r1.top > r3) goto L11;
            return false;
        L11:
            return true;
        }

        public void prepareForBackgroundDraw(long r4, int r6) {
            AnimatedEmojiDrawable r0 = this.drawable;
            if (r0 == null) goto L17;
            ImageReceiver r02 = r0.getImageReceiver();
            this.drawable.update(r4);
            this.drawable.setBounds(this.drawableBounds);
            if (r02 == null) goto L16;
            AnimatedEmojiSpan r1 = this.span;
            if (r1 != null) goto L10;
        L14:
            r02.setAlpha(this.alpha);
            r02.setImageCoords(this.drawableBounds);
            ImageReceiver.BackgroundThreadDrawHolder[] r12 = this.backgroundDrawHolder;
            r12[r6] = r02.setDrawInBackgroundThread(r12[r6], r6);
            ImageReceiver.BackgroundThreadDrawHolder r03 = this.backgroundDrawHolder[r6];
            r03.overrideAlpha = this.alpha;
            r03.setBounds(this.drawableBounds);
            this.backgroundDrawHolder[r6].time = r4;
            return;
        L10:
            if (r1.document != null) goto L14;
            if (this.drawable.getDocument() == null) goto L14;
            AnimatedEmojiSpan r13 = this.span;
            r13.document = this.drawable.getDocument();
            goto L14
        L16:
            return;
        }

        public void releaseDrawInBackground(int r1) {
            ImageReceiver.BackgroundThreadDrawHolder r0 = this.backgroundDrawHolder[r1];
            if (r0 == null) goto L6;
            r0.release();
            return;
        }
    }

    public static class EmojiGroupedSpans {
        ArrayList<SpansChunk> backgroundDrawingArray;
        HashMap<Layout, SpansChunk> groupedByLayout;
        public ArrayList<AnimatedEmojiHolder> holders;

        public EmojiGroupedSpans() {
            this.holders = new ArrayList();
            this.groupedByLayout = new HashMap();
            this.backgroundDrawingArray = new ArrayList();
        }

        public void add(Layout r4, AnimatedEmojiHolder r5) {
            this.holders.add(r5);
            SpansChunk r0 = this.groupedByLayout.get(r4);
            if (r0 != null) goto L5;
            r0 = new SpansChunk(AnimatedEmojiHolder.m8804$$Nest$fgetview(r5), r4, false);
            this.groupedByLayout.put(r4, r0);
            this.backgroundDrawingArray.add(r0);
        L5:
            r0.add(r5);
            AnimatedEmojiDrawable r3 = r5.drawable;
            if (r3 == null) goto L9;
            r3.addView(r5);
            return;
        }

        public void clearPositions() {
            int r1 = 0;
        L4:
            if (r1 >= this.holders.size()) goto L6;
            this.holders.get(r1).span.spanDrawn = false;
            r1 = r1 + 1;
            goto L4
        }

        public void recordPositions(boolean r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.holders.size()) goto L6;
            AnimatedEmojiSpan.m8798$$Nest$fputrecordPositions(this.holders.get(r0).span, r3);
            r0 = r0 + 1;
            goto L4
        }

        public void release() {
        L3:
            if (this.holders.size() <= 0) goto L5;
            remove(0);
            goto L3
        }

        public void remove(int r4) {
            AnimatedEmojiHolder r42 = this.holders.remove(r4);
            SpansChunk r0 = this.groupedByLayout.get(r42.layout);
            if (r0 == null) goto L11;
            r0.remove(r42);
            if (r0.holders.isEmpty() == false) goto L7;
            this.groupedByLayout.remove(r42.layout);
            this.backgroundDrawingArray.remove(r0);
        L7:
            AnimatedEmojiDrawable r3 = r42.drawable;
            if (r3 == null) goto L13;
            r3.removeView(r42);
            return;
        L13:
            return;
        L11:
            GlShader$$ExternalSyntheticBUOutline1.m("!!!");
        }

        public void replaceLayout(Layout r3, Layout r4) {
            if (r4 == null) goto L12;
            SpansChunk r42 = this.groupedByLayout.remove(r4);
            if (r42 == null) goto L13;
            r42.layout = r3;
            int r0 = 0;
        L7:
            if (r0 >= r42.holders.size()) goto L9;
            r42.holders.get(r0).layout = r3;
            r0 = r0 + 1;
            goto L7
        L9:
            this.groupedByLayout.put(r3, r42);
            return;
        L13:
            return;
        }
    }

    public interface InvalidateHolder {
        void invalidate();
    }

    public static class SpansChunk {
        private final boolean allowBackgroundRendering;
        DrawingInBackgroundThreadDrawable backgroundThreadDrawable;
        ArrayList<AnimatedEmojiHolder> holders;
        Layout layout;
        final WeakReference<View> view;

        /* renamed from: org.telegram.ui.Components.AnimatedEmojiSpan$SpansChunk$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 extends DrawingInBackgroundThreadDrawable {
            private final ArrayList<AnimatedEmojiHolder> backgroundHolders;
            final /* synthetic */ SpansChunk this$0;

            public AnonymousClass1(SpansChunk r1) {
                this.this$0 = r1;
                this.backgroundHolders = new ArrayList();
            }

            private void invalidate() {
                View r1 = this.this$0.view.get();
                if (r1 != null) goto L5;
                return;
            L5:
                if (r1.getParent() == null) goto L9;
                ((View) r1.getParent()).invalidate();
                return;
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInBackground(Canvas r5) {
                int r0 = 0;
            L4:
                if (r0 >= this.backgroundHolders.size()) goto L13;
                AnimatedEmojiHolder r1 = this.backgroundHolders.get(r0);
                if (r1 == null) goto L12;
                if (r1.drawable == null) goto L12;
                if (AnimatedEmojiHolder.m8803$$Nest$fgetbackgroundDrawHolder(r1)[this.threadIndex] == null) goto L12;
                r1.drawable.draw(r5, AnimatedEmojiHolder.m8803$$Nest$fgetbackgroundDrawHolder(r1)[this.threadIndex], true);
            L12:
                r0 = r0 + 1;
                goto L4
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInUiThread(Canvas r10, float r11) {
                long r2 = System.currentTimeMillis();
                int r8 = 0;
            L4:
                if (r8 >= this.this$0.holders.size()) goto L10;
                AnimatedEmojiHolder r0 = this.this$0.holders.get(r8);
                if (r0.span.spanDrawn == true) goto L8;
                Canvas r1 = r10;
                float r6 = r11;
            L9:
                r8 = r8 + 1;
                r10 = r1;
                r11 = r6;
                goto L4
            L8:
                r1 = r10;
                r6 = r11;
                r0.draw(r1, r2, 0.0f, 0.0f, r6, null);
                goto L9
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void onFrameReady() {
                int r0 = 0;
            L3:
                int r1 = this.backgroundHolders.size();
                ArrayList<AnimatedEmojiHolder> r2 = this.backgroundHolders;
                if (r0 >= r1) goto L9;
                if (r2.get(r0) == null) goto L8;
                this.backgroundHolders.get(r0).releaseDrawInBackground(this.threadIndex);
            L8:
                r0 = r0 + 1;
                goto L3
            L9:
                r2.clear();
                invalidate();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void onPaused() {
                super.onPaused();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void onResume() {
                invalidate();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void prepareDraw(long r4) {
                this.backgroundHolders.clear();
                this.backgroundHolders.addAll(this.this$0.holders);
                int r0 = 0;
            L4:
                if (r0 >= this.backgroundHolders.size()) goto L10;
                AnimatedEmojiHolder r1 = this.backgroundHolders.get(r0);
                if (r1.span.spanDrawn == true) goto L8;
                this.backgroundHolders.remove(r0);
                r0 = r0 - 1;
            L9:
                r0 = r0 + 1;
                goto L4
            L8:
                r1.prepareForBackgroundDraw(r4, this.threadIndex);
                goto L9
            }
        }

        public SpansChunk(WeakReference<View> r2, Layout r3, boolean r4) {
            this.holders = new ArrayList();
            this.layout = r3;
            this.view = r2;
            this.allowBackgroundRendering = r4;
        }

        private void checkBackgroundRendering() {
            if (this.allowBackgroundRendering == false) goto L13;
            if (this.holders.size() < 10) goto L13;
            if (this.backgroundThreadDrawable != null) goto L13;
            if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD) == false) goto L13;
            AnonymousClass1 r0 = new AnonymousClass1(this);
            this.backgroundThreadDrawable = r0;
            r0.padding = AndroidUtilities.dp(3.0f);
            this.backgroundThreadDrawable.onAttachToWindow();
            return;
        L13:
            if (this.holders.size() >= 10) goto L18;
            DrawingInBackgroundThreadDrawable r02 = this.backgroundThreadDrawable;
            if (r02 == null) goto L19;
            r02.onDetachFromWindow();
            this.backgroundThreadDrawable = null;
            return;
        L19:
            return;
        }

        public void add(AnimatedEmojiHolder r2) {
            this.holders.add(r2);
            r2.spansChunk = this;
            checkBackgroundRendering();
        }

        public void draw(Canvas r15, List<SpoilerEffect> r16, long r17, float r19, float r20, float r21, float r22, ColorFilter r23) {
            int r2 = 0;
        L4:
            if (r2 >= this.holders.size()) goto L27;
            AnimatedEmojiHolder r5 = this.holders.get(r2);
            if (r5 == null) goto L26;
            AnimatedEmojiDrawable r3 = r5.drawable;
            if (r3 == null) goto L11;
            r3.setColorFilter(r23);
        L11:
            AnimatedEmojiSpan r32 = r5.span;
            if (r32.spanDrawn == false) goto L26;
            float r6 = r32.measuredSize / 2.0f;
            float r4 = r32.lastDrawnCx;
            float r33 = r32.lastDrawnCy;
            r5.drawableBounds.set((int) (r4 - r6), (int) (r33 - r6), (int) (r4 + r6), (int) (r33 + r6));
            if (r16 != null) goto L17;
        L22:
            float r34 = 1.0f;
        L23:
            r5.drawingYOffset = r21;
            r5.alpha = r34;
            if (this.backgroundThreadDrawable != null) goto L26;
            r5.draw(r15, r17, r19, r20, r22, r23);
            goto L26
        L17:
            if (r16.isEmpty() == true) goto L22;
            if (r5.insideSpoiler == false) goto L22;
            r34 = Math.max(0.0f, r16.get(0).getRippleProgress());
        L26:
            r2 = r2 + 1;
            goto L4
        L27:
            DrawingInBackgroundThreadDrawable r62 = this.backgroundThreadDrawable;
            if (r62 == null) goto L35;
            r62.draw(r15, r17, this.layout.getWidth(), this.layout.getHeight() + AndroidUtilities.dp(2.0f), r22);
            return;
        }

        public void remove(AnimatedEmojiHolder r2) {
            this.holders.remove(r2);
            r2.spansChunk = null;
            checkBackgroundRendering();
        }
    }

    public static class TextViewEmojis extends TextView {
        private int cacheType;
        private ColorFilter emojiColorFilter;
        EmojiGroupedSpans stack;

        public TextViewEmojis(Context r1) {
            super(r1);
            this.cacheType = 0;
        }

        @Override // android.widget.TextView, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.stack = AnimatedEmojiSpan.update(this.cacheType, this, this.stack, new Layout[]{getLayout()});
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AnimatedEmojiSpan.release(this, this.stack);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r17) {
            super.onDraw(r17);
            if ((getGravity() & 16) != 0) goto L5;
        L7:
            float r1 = 0.0f;
        L9:
            if (LocaleController.isRTL == false) goto L12;
            int r3 = getPaddingRight();
        L11:
            float r32 = r3;
            if (r1 == 0.0f) goto L16;
        L19:
            r17.save();
            Canvas r6 = r17;
            r6.translate(r32, r1);
        L20:
            AnimatedEmojiSpan.drawAnimatedEmojis(r6, getLayout(), this.stack, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, this.emojiColorFilter);
            if (r1 == 0.0f) goto L23;
        L26:
            r17.restore();
            return;
        L23:
            if (r32 != 0.0f) goto L26;
            return;
        L16:
            if (r32 != 0.0f) goto L19;
            r6 = r17;
            goto L20
        L12:
            r3 = getPaddingLeft();
            goto L11
        L5:
            if (getLayout() == null) goto L7;
            r1 = getPaddingTop() + ((((getHeight() - getPaddingTop()) - getPaddingBottom()) - getLayout().getHeight()) / 2.0f);
            goto L9
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, r3);
            this.stack = AnimatedEmojiSpan.update(this.cacheType, this, this.stack, new Layout[]{getLayout()});
        }

        public void setCacheType(int r3) {
            if (this.cacheType != r3) goto L5;
            return;
        L5:
            this.cacheType = r3;
            this.stack = AnimatedEmojiSpan.update(r3, this, this.stack, new Layout[]{getLayout()});
        }

        public void setEmojiColor(int r3) {
            this.emojiColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r2, TextView.BufferType r3) {
            super.setText(r2, r3);
            this.stack = AnimatedEmojiSpan.update(this.cacheType, this, this.stack, new Layout[]{getLayout()});
        }
    }

    public static /* synthetic */ void $r8$lambda$3B1SWM_gZRmgAauIKlLnpt2s9WQ(AnimatedEmojiSpan r0, float r1, float r2, float r3, float r4, ValueAnimator r5) {
        r0.lambda$animateChanges$2(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$GZFgI_JSdY65fp6Awx6farlgsvg(AnimatedEmojiSpan r0, ValueAnimator r1) {
        r0.lambda$getExtraScale$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$whN3WwnQItv5mabcaV_mZDattIY(AnimatedEmojiSpan r0, ValueAnimator r1) {
        r0.lambda$getExtraScale$1(r1);
    }

    /* renamed from: -$$Nest$fgetremovedAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m8796$$Nest$fgetremovedAction(AnimatedEmojiSpan r0) {
        return r0.removedAction;
    }

    /* renamed from: -$$Nest$fputmoveAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8797$$Nest$fputmoveAnimator(AnimatedEmojiSpan r0, ValueAnimator r1) {
        r0.moveAnimator = r1;
    }

    /* renamed from: -$$Nest$fputrecordPositions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8798$$Nest$fputrecordPositions(AnimatedEmojiSpan r0, boolean r1) {
        r0.recordPositions = r1;
    }

    /* renamed from: -$$Nest$fputremovedAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8799$$Nest$fputremovedAction(AnimatedEmojiSpan r0, Runnable r1) {
        r0.removedAction = r1;
    }

    /* renamed from: -$$Nest$fputscaleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8800$$Nest$fputscaleAnimator(AnimatedEmojiSpan r0, ValueAnimator r1) {
        r0.scaleAnimator = r1;
    }

    /* renamed from: -$$Nest$misAnimating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8801$$Nest$misAnimating(AnimatedEmojiSpan r0) {
        return r0.isAnimating();
    }

    /* renamed from: -$$Nest$sfputlockPositionChanging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8802$$Nest$sfputlockPositionChanging(boolean r0) {
        lockPositionChanging = r0;
    }

    public AnimatedEmojiSpan(long r3, float r5, Paint.FontMetricsInt r6) {
        this.extraScale = 1.0f;
        this.full = false;
        this.top = false;
        this.invert = false;
        this.size = AndroidUtilities.dp(20.0f);
        this.cacheType = -1;
        this.recordPositions = true;
        this.documentId = r3;
        this.scale = r5;
        this.fontMetrics = r6;
        if (r6 == null) goto L8;
        float r32 = Math.abs(r6.descent) + Math.abs(r6.ascent);
        this.size = r32;
        if (r32 != 0.0f) goto L9;
        this.size = AndroidUtilities.dp(20.0f);
        return;
    L9:
        return;
    }

    public AnimatedEmojiSpan(long r2, Paint.FontMetricsInt r4) {
        this(r2, 1.2f, r4);
    }

    public AnimatedEmojiSpan(TLRPC.Document r3, float r4, Paint.FontMetricsInt r5) {
        this(r3.id, r4, r5);
        this.document = r3;
    }

    public AnimatedEmojiSpan(TLRPC.Document r4, Paint.FontMetricsInt r5) {
        this(r4.id, 1.2f, r5);
        this.document = r4;
    }

    private boolean animateChanges(float r10, float r11) {
        if (this.moveAnimator == null) goto L6;
        return true;
    L6:
        if (this.animateChanges == true) goto L8;
        return false;
    L8:
        this.animateChanges = false;
        float r7 = this.lastDrawnCx;
        float r5 = this.lastDrawnCy;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.moveAnimator = r0;
        r0.addUpdateListener(new AnimatedEmojiSpan$$ExternalSyntheticLambda2(this, r5, r11, r7, r10));
        this.moveAnimator.addListener(new AnonymousClass3(this));
        this.moveAnimator.setDuration(140);
        this.moveAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.moveAnimator.start();
        return true;
    }

    public static void applyFontMetricsForString(CharSequence r3, Paint r4) {
        if ((r3 instanceof Spannable) == false) goto L9;
        int r2 = 0;
        AnimatedEmojiSpan[] r32 = (AnimatedEmojiSpan[]) ((Spannable) r3).getSpans(0, r3.length(), AnimatedEmojiSpan.class);
        if (r32 != null) goto L7;
        return;
    L7:
        if (r2 >= r32.length) goto L12;
        r32[r2].applyFontMetrics(r4.getFontMetricsInt());
        r2 = r2 + 1;
        goto L7
    L12:
        return;
    }

    public static AnimatedEmojiSpan cloneSpan(AnimatedEmojiSpan r5, Paint.FontMetricsInt r6) {
        TLRPC.Document r0 = r5.document;
        if (r0 == null) goto L9;
        float r2 = r5.scale;
        if (r6 == null) goto L7;
        Paint.FontMetricsInt r3 = r6;
    L8:
        AnimatedEmojiSpan r1 = new AnimatedEmojiSpan(r0, r2, r3);
    L14:
        if (r6 == null) goto L16;
        r1.size = r5.size;
    L16:
        r1.local = r5.local;
        r1.fromEmojiKeyboard = r5.fromEmojiKeyboard;
        r1.isAdded = r5.isAdded;
        r1.isRemoved = r5.isRemoved;
        return r1;
    L7:
        r3 = r5.fontMetrics;
        goto L8
    L9:
        long r22 = r5.documentId;
        float r02 = r5.scale;
        if (r6 == null) goto L12;
        Paint.FontMetricsInt r4 = r6;
    L13:
        r1 = new AnimatedEmojiSpan(r22, r02, r4);
        goto L14
    L12:
        r4 = r5.fontMetrics;
        goto L13
    }

    public static CharSequence cloneSpans(CharSequence r2) {
        return cloneSpans(r2, -1, null);
    }

    public static CharSequence cloneSpans(CharSequence r1, int r2) {
        return cloneSpans(r1, r2, null);
    }

    public static CharSequence cloneSpans(CharSequence r1, int r2, Paint.FontMetricsInt r3) {
        return cloneSpans(r1, r2, r3, 1.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.text.SpannableString] */
    public static CharSequence cloneSpans(CharSequence r8, int r9, Paint.FontMetricsInt r10, float r11) {
        if ((r8 instanceof Spanned) == true) goto L5;
        return r8;
    L5:
        Spanned r0 = (Spanned) r8;
        int r3 = 0;
        CharacterStyle[] r1 = (CharacterStyle[]) r0.getSpans(0, r0.length(), CharacterStyle.class);
        if (r1 != null) goto L8;
    L28:
        return r8;
    L8:
        if (r1.length <= 0) goto L28;
        AnimatedEmojiSpan[] r2 = (AnimatedEmojiSpan[]) r0.getSpans(0, r0.length(), AnimatedEmojiSpan.class);
        if (r2 != null) goto L13;
    L15:
        r8 = new SpannableString(r0);
    L17:
        if (r3 >= r1.length) goto L28;
        CharacterStyle r22 = r1[r3];
        if (r22 == null) goto L27;
        if ((r22 instanceof AnimatedEmojiSpan) == false) goto L27;
        int r23 = r0.getSpanStart(r22);
        int r4 = r0.getSpanEnd(r1[r3]);
        AnimatedEmojiSpan r5 = (AnimatedEmojiSpan) r1[r3];
        r8.removeSpan(r5);
        AnimatedEmojiSpan r6 = cloneSpan(r5, r10);
        if (r9 == (-1)) goto L26;
        r6.cacheType = r9;
    L26:
        r6.scale = r5.scale * r11;
        r8.setSpan(r6, r23, r4, 33);
    L27:
        r3 = r3 + 1;
        goto L17
    L13:
        if (r2.length > 0) goto L15;
        goto L15
    }

    public static void drawAnimatedEmojis(Canvas r10, Layout r11, EmojiGroupedSpans r12, float r13, List<SpoilerEffect> r14, float r15, float r16, float r17, float r18) {
        drawAnimatedEmojis(r10, r11, r12, r13, r14, r15, r16, r17, r18, null);
    }

    public static void drawAnimatedEmojis(Canvas r13, Layout r14, EmojiGroupedSpans r15, float r16, List<SpoilerEffect> r17, float r18, float r19, float r20, float r21, ColorFilter r22) {
        if (r13 == null) goto L25;
        if (r14 == null) goto L26;
        if (r15 == null) goto L28;
        int r2 = 0;
        if (Emoji.emojiDrawingYOffset == 0.0f) goto L9;
    L12:
        r13.save();
        r13.translate(0.0f, Emoji.emojiDrawingYOffset + AndroidUtilities.dp(20.0f * r16));
        boolean r0 = true;
    L13:
        long r6 = System.currentTimeMillis();
    L15:
        if (r2 >= r15.backgroundDrawingArray.size()) goto L20;
        SpansChunk r3 = r15.backgroundDrawingArray.get(r2);
        if (r3.layout == r14) goto L18;
        r2 = r2 + 1;
        goto L15
    L18:
        r3.draw(r13, r17, r6, r18, r19, r20, r21, r22);
    L20:
        if (r0 == false) goto L27;
        r13.restore();
        return;
    L27:
        return;
    L9:
        if (r16 != 0.0f) goto L12;
        r0 = false;
        goto L13
    L28:
        return;
    L26:
        return;
    }

    private static void expandFontMetrics(Paint.FontMetricsInt r3, int r4) {
        int r0 = r3.descent;
        int r1 = r3.ascent;
        int r2 = r0 - r1;
        if (r4 > r2) goto L5;
        return;
    L5:
        int r42 = r4 - r2;
        int r22 = (r42 + 1) / 2;
        int r12 = r1 - r22;
        r3.ascent = r12;
        r3.descent = r0 + (r42 - r22);
        r3.top = Math.min(r3.top, r12);
        r3.bottom = Math.max(r3.bottom, r3.descent);
    }

    private boolean isAnimating() {
        if (this.moveAnimator == null) goto L5;
        return true;
    L5:
        if (this.scaleAnimator != null) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isInsideSpoiler(Layout r3, int r4, int r5) {
        if (r3 != null) goto L5;
    L17:
        return false;
    L5:
        if ((r3.getText() instanceof Spanned) == false) goto L17;
        TextStyleSpan[] r32 = (TextStyleSpan[]) ((Spanned) r3.getText()).getSpans(Math.max(0, r4), Math.min(r3.getText().length() - 1, r5), TextStyleSpan.class);
        int r42 = 0;
    L8:
        if (r32 == null) goto L17;
        if (r42 >= r32.length) goto L17;
        TextStyleSpan r52 = r32[r42];
        if (r52 == null) goto L16;
        if (r52.isSpoiler() == false) goto L16;
        return true;
    L16:
        r42 = r42 + 1;
        goto L8
    }

    private /* synthetic */ void lambda$animateChanges$2(float r1, float r2, float r3, float r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.lastDrawnCy = AndroidUtilities.lerp(r1, r2, r52);
        this.lastDrawnCx = AndroidUtilities.lerp(r3, r4, r52);
    }

    private /* synthetic */ void lambda$getExtraScale$0(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.extraScale = r32;
        this.scale = AndroidUtilities.lerp(0.2f, 1.0f, r32);
        lockPositionChanging = false;
    }

    private /* synthetic */ void lambda$getExtraScale$1(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.extraScale = r32;
        this.scale = AndroidUtilities.lerp(0.0f, 1.0f, r32);
    }

    public static CharSequence onlyEmojiSpans(CharSequence r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r4);
        int r2 = 0;
        CharacterStyle[] r42 = (CharacterStyle[]) r0.getSpans(0, r0.length(), CharacterStyle.class);
    L7:
        if (r2 >= r42.length) goto L14;
        CharacterStyle r1 = r42[r2];
        if ((r1 instanceof AnimatedEmojiSpan) == true) goto L13;
        if ((r1 instanceof Emoji.EmojiSpan) == true) goto L13;
        r0.removeSpan(r1);
    L13:
        r2 = r2 + 1;
        goto L7
    L14:
        return r0;
    }

    public static void release(View r2, LongSparseArray<AnimatedEmojiDrawable> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        int r0 = 0;
    L6:
        if (r0 >= r3.size()) goto L11;
        AnimatedEmojiDrawable r1 = r3.valueAt(r0);
        if (r1 == null) goto L10;
        r1.removeView(r2);
    L10:
        r0 = r0 + 1;
        goto L6
    L11:
        r3.clear();
    }

    public static void release(View r0, EmojiGroupedSpans r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        r1.release();
    }

    public static LongSparseArray<AnimatedEmojiDrawable> update(int r8, View r9, ArrayList<AnimatedEmojiSpan> r10, LongSparseArray<AnimatedEmojiDrawable> r11) {
        if (r10 != null) goto L4;
        return r11;
    L4:
        if (r11 != null) goto L6;
        r11 = new LongSparseArray();
    L6:
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r1 >= r11.size()) goto L25;
        long r2 = r11.keyAt(r1);
        AnimatedEmojiDrawable r4 = r11.get(r2);
        if (r4 != null) goto L13;
        r11.remove(r2);
    L12:
        r1 = r1 - 1;
    L23:
        r1 = r1 + 1;
        goto L8
    L13:
        int r5 = 0;
    L15:
        if (r5 >= r10.size()) goto L22;
        if (r10.get(r5) == null) goto L21;
        if (r10.get(r5).getDocumentId() == r2) goto L23;
    L21:
        r5 = r5 + 1;
        goto L15
    L22:
        r4.addView(r9);
        r11.remove(r2);
    L25:
        if (r0 >= r10.size()) goto L38;
        AnimatedEmojiSpan r12 = r10.get(r0);
        if (r12 == null) goto L37;
        if (r11.get(r12.getDocumentId()) != null) goto L37;
        if (r12.standard == false) goto L33;
        int r22 = 8;
    L36:
        AnimatedEmojiDrawable r23 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, r22, r12.documentId);
        r23.addView(r9);
        r11.put(r12.getDocumentId(), r23);
        goto L37
    L33:
        r22 = r12.cacheType;
        if (r22 >= 0) goto L36;
        r22 = r8;
    L37:
        r0 = r0 + 1;
        goto L25
    L38:
        return r11;
    }

    public static LongSparseArray<AnimatedEmojiDrawable> update(int r8, View r9, AnimatedEmojiSpan[] r10, LongSparseArray<AnimatedEmojiDrawable> r11) {
        if (r10 != null) goto L4;
        return r11;
    L4:
        if (r11 != null) goto L6;
        r11 = new LongSparseArray();
    L6:
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r1 >= r11.size()) goto L25;
        long r2 = r11.keyAt(r1);
        AnimatedEmojiDrawable r4 = r11.get(r2);
        if (r4 != null) goto L13;
        r11.remove(r2);
    L12:
        r1 = r1 - 1;
    L23:
        r1 = r1 + 1;
        goto L8
    L13:
        int r5 = 0;
    L15:
        if (r5 >= r10.length) goto L22;
        AnimatedEmojiSpan r6 = r10[r5];
        if (r6 == null) goto L21;
        if (r6.getDocumentId() == r2) goto L23;
    L21:
        r5 = r5 + 1;
        goto L15
    L22:
        r4.removeView(r9);
        r11.remove(r2);
    L25:
        if (r0 >= r10.length) goto L42;
        AnimatedEmojiSpan r12 = r10[r0];
        if (r12 == null) goto L41;
        if (r11.get(r12.getDocumentId()) != null) goto L41;
        if (r12.standard == false) goto L33;
        int r22 = 8;
    L36:
        TLRPC.Document r3 = r12.document;
        if (r3 == null) goto L39;
        AnimatedEmojiDrawable r23 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, r22, r3);
    L40:
        r23.addView(r9);
        r11.put(r12.getDocumentId(), r23);
        goto L41
    L39:
        r23 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, r22, r12.documentId);
        goto L40
    L33:
        r22 = r12.cacheType;
        if (r22 >= 0) goto L36;
        r22 = r8;
    L41:
        r0 = r0 + 1;
        goto L25
    L42:
        return r11;
    }

    public static LongSparseArray<AnimatedEmojiDrawable> update(View r1, ArrayList<AnimatedEmojiSpan> r2, LongSparseArray<AnimatedEmojiDrawable> r3) {
        return update(0, r1, r2, r3);
    }

    public static LongSparseArray<AnimatedEmojiDrawable> update(View r1, AnimatedEmojiSpan[] r2, LongSparseArray<AnimatedEmojiDrawable> r3) {
        return update(0, r1, r2, r3);
    }

    public static EmojiGroupedSpans update(int r1, View r2, EmojiGroupedSpans r3, ArrayList<MessageObject.TextLayoutBlock> r4) {
        return update(r1, r2, r3, r4, false);
    }

    public static EmojiGroupedSpans update(int r6, View r7, EmojiGroupedSpans r8, ArrayList<MessageObject.TextLayoutBlock> r9, boolean r10) {
        return update(r6, r7, false, r8, r9, r10);
    }

    public static EmojiGroupedSpans update(int r1, View r2, EmojiGroupedSpans r3, Layout... r4) {
        return update(r1, r2, false, r3, r4);
    }

    public static EmojiGroupedSpans update(int r6, View r7, boolean r8, EmojiGroupedSpans r9, ArrayList<MessageObject.TextLayoutBlock> r10) {
        return update(r6, r7, r8, r9, r10, false);
    }

    public static EmojiGroupedSpans update(int r3, View r4, boolean r5, EmojiGroupedSpans r6, ArrayList<MessageObject.TextLayoutBlock> r7, boolean r8) {
        int r0 = 0;
        if (r7 != null) goto L5;
        int r1 = 0;
    L6:
        Layout[] r12 = new Layout[r1];
        if (r7 == null) goto L12;
    L9:
        if (r0 >= r7.size()) goto L12;
        r12[r0] = r7.get(r0).textLayout;
        r0 = r0 + 1;
    L12:
        return update(r3, r4, r5, r6, r8, r12);
    L5:
        r1 = r7.size();
        goto L6
    }

    public static EmojiGroupedSpans update(int r16, View r17, boolean r18, EmojiGroupedSpans r19, boolean r20, Layout... r21) {
        EmojiGroupedSpans r0 = r19;
        if (r21 != null) goto L5;
    L99:
        if (r0 == null) goto L101;
        r0.holders.clear();
        r0.release();
    L101:
        return null;
    L5:
        if (r21.length <= 0) goto L99;
        int r3 = 0;
        int r4 = 0;
    L9:
        if (r4 >= r21.length) goto L85;
        Layout r5 = r21[r4];
        if (r5 != null) goto L13;
    L67:
        int r192 = r4;
        AnimatedEmojiSpan[] r7 = null;
    L68:
        if (r0 == null) goto L84;
        int r32 = 0;
    L71:
        if (r32 >= r0.holders.size()) goto L84;
        if (r0.holders.get(r32).layout != r5) goto L83;
        AnimatedEmojiSpan r42 = r0.holders.get(r32).span;
        int r6 = 0;
    L75:
        if (r7 == null) goto L82;
        if (r6 >= r7.length) goto L82;
        if (r7[r6] == r42) goto L83;
        r6 = r6 + 1;
    L82:
        r0.remove(r32);
        r32 = r32 - 1;
    L83:
        r32 = r32 + 1;
    L84:
        r4 = r192 + 1;
        r3 = 0;
        goto L9
    L13:
        if ((r5.getText() instanceof Spanned) == false) goto L67;
        Spanned r62 = (Spanned) r5.getText();
        r7 = (AnimatedEmojiSpan[]) r62.getSpans(r3, r62.length(), AnimatedEmojiSpan.class);
        int r8 = r3;
    L15:
        if (r7 == null) goto L66;
        if (r8 >= r7.length) goto L66;
        AnimatedEmojiSpan r9 = r7[r8];
        if (r9 != null) goto L21;
        int r193 = r4;
    L65:
        r8 = r8 + 1;
        r4 = r193;
        r3 = 0;
        goto L15
    L21:
        if (r20 == true) goto L23;
    L25:
        if (r0 != null) goto L27;
        r0 = new EmojiGroupedSpans();
    L27:
        int r10 = r3;
    L29:
        if (r10 >= r0.holders.size()) goto L36;
        if (r0.holders.get(r10).span != r9) goto L35;
        if (r0.holders.get(r10).layout != r5) goto L35;
        AnimatedEmojiHolder r102 = r0.holders.get(r10);
    L37:
        if (r102 != null) goto L64;
        AnimatedEmojiHolder r103 = new AnimatedEmojiHolder(r17, r18);
        r103.layout = r5;
        if (r9.standard == false) goto L41;
        int r13 = 8;
    L45:
        if (r9.documentAbsolutePath == null) goto L47;
        r193 = r4;
        r103.drawable = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, r13, r9.getDocumentId(), r9.documentAbsolutePath);
    L53:
        int r33 = r9.cacheType;
        if (r33 == 20) goto L58;
        if (r33 == 21) goto L58;
    L63:
        r103.insideSpoiler = isInsideSpoiler(r5, r62.getSpanStart(r9), r62.getSpanEnd(r9));
        r103.drawableBounds = new Rect();
        r103.span = r9;
        r0.add(r5, r103);
    L58:
        if (TextUtils.isEmpty(r9.emoji) == true) goto L63;
        AnimatedEmojiDrawable r34 = r103.drawable;
        String r43 = r9.emoji;
        if (r34 == null) goto L62;
        r34.setupEmojiThumb(r43);
        goto L63
    L62:
        r103.thumbDrawable = Emoji.getEmojiDrawable(r43);
        goto L63
    L47:
        r193 = r4;
        TLRPC.Document r35 = r9.document;
        if (r35 == null) goto L50;
        r103.drawable = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, r13, r35);
        goto L53
    L50:
        long r36 = r9.documentId;
        if (r36 == 0) goto L53;
        r103.drawable = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, r13, r36, null);
        goto L53
    L41:
        r13 = r9.cacheType;
        if (r13 >= 0) goto L45;
        r13 = r16;
        goto L45
    L64:
        r193 = r4;
        r102.insideSpoiler = isInsideSpoiler(r5, r62.getSpanStart(r9), r62.getSpanEnd(r9));
    L35:
        r10 = r10 + 1;
        goto L29
    L36:
        r102 = null;
        goto L37
    L23:
        if ((r5.getText() instanceof Spannable) == false) goto L25;
        int r104 = r62.getSpanStart(r9);
        int r11 = r62.getSpanEnd(r9);
        Spannable r12 = (Spannable) r62;
        r12.removeSpan(r9);
        r9 = cloneSpan(r9, null);
        r7[r8] = r9;
        r12.setSpan(r9, r104, r11, 33);
    L66:
        r192 = r4;
        goto L68
    L85:
        if (r0 == null) goto L98;
        int r2 = 0;
    L88:
        if (r2 >= r0.holders.size()) goto L98;
        Layout r37 = r0.holders.get(r2).layout;
        int r44 = 0;
    L91:
        if (r44 >= r21.length) goto L96;
        if (r21[r44] == r37) goto L97;
        r44 = r44 + 1;
    L97:
        r2 = r2 + 1;
        goto L88
    L96:
        r0.remove(r2);
        r2 = r2 - 1;
    L98:
        return r0;
    }

    public static EmojiGroupedSpans update(int r6, View r7, boolean r8, EmojiGroupedSpans r9, Layout... r10) {
        return update(r6, r7, r8, r9, false, r10);
    }

    public void applyFontMetrics(Paint.FontMetricsInt r1) {
        this.fontMetrics = r1;
    }

    public void applyFontMetrics(Paint.FontMetricsInt r1, int r2) {
        this.fontMetrics = r1;
        this.cacheType = r2;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        if (this.recordPositions == false) goto L24;
        this.spanDrawn = true;
        float r52 = r5 + (this.measuredSize / 2.0f);
        float r22 = r6 + ((r8 - r6) / 2.0f);
        float r32 = this.lastDrawnCy;
        if (r22 != r32) goto L7;
    L8:
        float r33 = this.lastDrawnCx;
        if (r52 != r33) goto L11;
    L16:
        if (lockPositionChanging == false) goto L19;
        return;
    L19:
        if (r52 == this.lastDrawnCx) goto L21;
    L22:
        this.lastDrawnCx = r52;
        this.lastDrawnCy = r22;
        this.positionChanged = true;
        return;
    L21:
        if (r22 != this.lastDrawnCy) goto L22;
        return;
    L11:
        if (r33 == 0.0f) goto L16;
    L13:
        if (animateChanges(r52, r22) == false) goto L16;
        return;
    L7:
        if (r32 != 0.0f) goto L13;
    }

    public long getDocumentId() {
        TLRPC.Document r0 = this.document;
        if (r0 == null) goto L7;
        return r0.id;
    L7:
        return this.documentId;
    }

    public float getExtraScale() {
        if (this.isAdded == false) goto L9;
        lockPositionChanging = true;
        this.isAdded = false;
        this.extraScale = 0.0f;
        ValueAnimator r0 = this.scaleAnimator;
        if (r0 == null) goto L7;
        r0.removeAllListeners();
        this.scaleAnimator.cancel();
    L7:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.extraScale, 1.0f});
        this.scaleAnimator = r02;
        r02.addUpdateListener(new AnimatedEmojiSpan$$ExternalSyntheticLambda0(this));
        this.scaleAnimator.addListener(new AnonymousClass1(this));
        this.scaleAnimator.setDuration(130);
        this.scaleAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.scaleAnimator.start();
    L15:
        return this.extraScale;
    L9:
        if (this.isRemoved == false) goto L15;
        this.isRemoved = false;
        this.extraScale = 1.0f;
        ValueAnimator r03 = this.scaleAnimator;
        if (r03 == null) goto L13;
        r03.removeAllListeners();
        this.scaleAnimator.cancel();
    L13:
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{this.extraScale, 0.0f});
        this.scaleAnimator = r04;
        r04.addUpdateListener(new AnimatedEmojiSpan$$ExternalSyntheticLambda1(this));
        this.scaleAnimator.addListener(new AnonymousClass2(this));
        this.scaleAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.scaleAnimator.setDuration(130);
        this.scaleAnimator.start();
        goto L15
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r17, CharSequence r18, int r19, int r20, Paint.FontMetricsInt r21) {
        Paint.FontMetricsInt r1 = r21;
        if (this.preserveFontMetrics == false) goto L6;
        if (r1 == null) goto L6;
        boolean r2 = true;
    L7:
        if (r2 == false) goto L9;
        int r5 = r1.top;
    L10:
        if (r2 == false) goto L12;
        int r6 = r1.ascent;
    L13:
        if (r2 == false) goto L15;
        int r7 = r1.descent;
    L16:
        if (r2 == false) goto L18;
        int r8 = r1.bottom;
    L19:
        if (r2 == false) goto L21;
        int r9 = r1.leading;
    L22:
        if (r1 == null) goto L24;
    L26:
        if (r1 != null) goto L28;
        int r10 = 0;
    L29:
        if (r1 != null) goto L31;
        int r11 = 0;
    L32:
        Paint.FontMetricsInt r12 = this.fontMetrics;
        float r13 = this.size;
        if (r12 != null) goto L39;
        int r122 = (int) r13;
        int r132 = AndroidUtilities.dp(8.0f);
        int r14 = AndroidUtilities.dp(10.0f);
        if (r1 == null) goto L38;
        float r15 = (-r14) - r132;
        float r3 = this.scale;
        r1.top = (int) (r15 * r3);
        float r4 = r14 - r132;
        r1.bottom = (int) (r4 * r3);
        r1.ascent = (int) (r15 * r3);
        r1.descent = (int) (r4 * r3);
        r1.leading = 0;
    L38:
        this.measuredSize = (int) (r122 * this.scale);
    L45:
        if (r1 != null) goto L47;
    L49:
        if (r2 == false) goto L52;
        r1.top = r5;
        r1.ascent = r6;
        r1.descent = r7;
        r1.bottom = r8;
        r1.leading = r9;
        expandFontMetrics(r1, this.minimumLineHeight);
    L52:
        return Math.max(0, this.measuredSize - 1);
    L47:
        if (this.top == false) goto L49;
        int r32 = r1.ascent;
        int r42 = r1.descent;
        int r102 = ((r10 - r32) + (r11 - r42)) / 2;
        r1.ascent = r32 + r102;
        r1.descent = r42 - r102;
        goto L49
    L39:
        this.measuredSize = (int) (r13 * this.scale);
        if (r1 == null) goto L45;
        if (this.full == true) goto L44;
        r1.ascent = r12.ascent;
        r1.descent = r12.descent;
        r1.top = r12.top;
        r1.bottom = r12.bottom;
        goto L45
    L44:
        float r33 = Math.abs(r12.bottom) + Math.abs(this.fontMetrics.top);
        r1.ascent = (int) Math.ceil((this.fontMetrics.top / r33) * this.measuredSize);
        r1.descent = (int) Math.ceil((this.fontMetrics.bottom / r33) * this.measuredSize);
        r1.top = (int) Math.ceil((this.fontMetrics.top / r33) * this.measuredSize);
        r1.bottom = (int) Math.ceil((this.fontMetrics.bottom / r33) * this.measuredSize);
        goto L45
    L31:
        r11 = r1.descent;
        goto L32
    L28:
        r10 = r1.ascent;
        goto L29
    L24:
        if (this.top == false) goto L26;
        r1 = r17.getFontMetricsInt();
        goto L26
    L21:
        r9 = 0;
        goto L22
    L18:
        r8 = 0;
        goto L19
    L15:
        r7 = 0;
        goto L16
    L12:
        r6 = 0;
        goto L13
    L9:
        r5 = 0;
    L6:
        r2 = false;
        goto L7
    }

    public void replaceFontMetrics(Paint.FontMetricsInt r2) {
        this.fontMetrics = r2;
        if (r2 == null) goto L8;
        float r22 = Math.abs(r2.descent) + Math.abs(this.fontMetrics.ascent);
        this.size = r22;
        if (r22 != 0.0f) goto L9;
        this.size = AndroidUtilities.dp(20.0f);
        return;
    L9:
        return;
    }

    public void replaceFontMetrics(Paint.FontMetricsInt r1, int r2, int r3) {
        this.fontMetrics = r1;
        this.size = r2;
        this.cacheType = r3;
    }

    public void setAdded() {
        this.isAdded = true;
        this.extraScale = 0.0f;
    }

    public void setAnimateChanges() {
        this.animateChanges = true;
    }

    public AnimatedEmojiSpan setMinimumLineHeight(int r1) {
        this.minimumLineHeight = r1;
        return this;
    }

    public AnimatedEmojiSpan setPreserveFontMetrics(boolean r1) {
        this.preserveFontMetrics = r1;
        return this;
    }

    public void setRemoved(Runnable r1) {
        this.removedAction = r1;
        this.isRemoved = true;
        this.extraScale = 1.0f;
    }

    public AnimatedEmojiSpan setSize(int r1) {
        this.size = r1;
        return this;
    }
}
