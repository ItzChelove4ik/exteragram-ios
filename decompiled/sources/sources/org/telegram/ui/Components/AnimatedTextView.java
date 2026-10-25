package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;

/* loaded from: classes3.dex */
public class AnimatedTextView extends View {
    public boolean adaptWidth;
    private Drawable backgroundDrawable;
    private final AnimatedTextDrawable drawable;
    private boolean first;
    private boolean hideBackgroundIfEmpty;
    private int lastMaxWidth;
    private int maxWidth;
    private boolean toSetMoveDown;
    private CharSequence toSetText;

    public static class AnimatedTextDrawable extends Drawable {
        private boolean allowCancel;
        private int alpha;
        private long animateDelay;
        private long animateDuration;
        private TimeInterpolator animateInterpolator;
        private float animateWave;
        private ValueAnimator animator;
        private final Rect bounds;
        public boolean centerY;
        private ValueAnimator colorAnimator;
        private float currentHeight;
        private Part[] currentParts;
        private CharSequence currentText;
        private float currentWidth;
        private boolean ellipsizeByGradient;
        private LinearGradient ellipsizeGradient;
        private Matrix ellipsizeGradientMatrix;
        private Paint ellipsizePaint;
        private int emojiCacheType;
        private int emojiColor;
        private ColorFilter emojiColorFilter;
        private boolean enforceByLetter;
        private int gravity;
        public boolean ignoreRTL;
        private boolean includeFontPadding;
        private boolean isRTL;
        private float moveAmplitude;
        private boolean moveDown;
        private float oldHeight;
        private Part[] oldParts;
        private CharSequence oldText;
        private float oldWidth;
        private Runnable onAnimationFinishListener;
        private int overrideFullWidth;
        private boolean preserveIndex;
        private float rightPadding;
        private float scaleAmplitude;
        private int shadowColor;
        private float shadowDx;
        private float shadowDy;
        private float shadowRadius;
        private boolean shadowed;
        private boolean splitByWords;
        private boolean startFromEnd;
        private float t;
        private final TextPaint textPaint;
        private CharSequence toSetText;
        private boolean toSetTextMoveDown;
        public boolean updateAll;
        private Runnable widthUpdatedListener;

        /* renamed from: org.telegram.ui.Components.AnimatedTextView$AnimatedTextDrawable$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnimatedTextDrawable this$0;

            public AnonymousClass1(AnimatedTextDrawable r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r5) {
                super.onAnimationEnd(r5);
                AnimatedTextDrawable.m8842$$Nest$mclearOldParts(this.this$0);
                AnimatedTextDrawable.m8837$$Nest$fputoldText(this.this$0, null);
                AnimatedTextDrawable.m8838$$Nest$fputoldWidth(this.this$0, 0.0f);
                AnimatedTextDrawable.m8839$$Nest$fputt(this.this$0, 0.0f);
                this.this$0.invalidateSelf();
                if (AnimatedTextDrawable.m8835$$Nest$fgetwidthUpdatedListener(this.this$0) == null) goto L5;
                AnimatedTextDrawable.m8835$$Nest$fgetwidthUpdatedListener(this.this$0).run();
            L5:
                AnimatedTextDrawable.m8836$$Nest$fputanimator(this.this$0, null);
                CharSequence r52 = AnimatedTextDrawable.m8833$$Nest$fgettoSetText(this.this$0);
                AnimatedTextDrawable r1 = this.this$0;
                if (r52 == null) goto L10;
                r1.setText(AnimatedTextDrawable.m8833$$Nest$fgettoSetText(r1), true, AnimatedTextDrawable.m8834$$Nest$fgettoSetTextMoveDown(this.this$0));
                AnimatedTextDrawable.m8840$$Nest$fputtoSetText(this.this$0, null);
                AnimatedTextDrawable.m8841$$Nest$fputtoSetTextMoveDown(this.this$0, false);
                return;
            L10:
                if (AnimatedTextDrawable.m8832$$Nest$fgetonAnimationFinishListener(r1) == null) goto L13;
                AnimatedTextDrawable.m8832$$Nest$fgetonAnimationFinishListener(this.this$0).run();
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.AnimatedTextView$AnimatedTextDrawable$2, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnimatedTextDrawable this$0;
            final /* synthetic */ int val$to;

            public AnonymousClass2(AnimatedTextDrawable r1, int r2) {
                this.this$0 = r1;
                this.val$to = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                this.this$0.setTextColor(this.val$to);
            }
        }

        public class Part {
            AnimatedEmojiSpan.EmojiGroupedSpans emoji;
            StaticLayout layout;
            float left;
            float offset;
            final /* synthetic */ AnimatedTextDrawable this$0;
            int toOppositeIndex;
            float width;

            public Part(AnimatedTextDrawable r3, StaticLayout r4, float r5, int r6) {
                this.this$0 = r3;
                this.layout = r4;
                this.toOppositeIndex = r6;
                layout(r5);
                if ((r3.getCallback() instanceof View) == false) goto L6;
                View r52 = (View) r3.getCallback();
                this.emoji = AnimatedEmojiSpan.update(AnimatedTextDrawable.m8829$$Nest$fgetemojiCacheType(r3), r52, this.emoji, new Layout[]{r4});
                return;
            }

            public void detach() {
                if ((this.this$0.getCallback() instanceof View) == false) goto L6;
                AnimatedEmojiSpan.release((View) this.this$0.getCallback(), this.emoji);
                return;
            }

            public void draw(Canvas r12, float r13) {
                this.layout.draw(r12);
                AnimatedEmojiSpan.drawAnimatedEmojis(r12, this.layout, this.emoji, 0.0f, null, 0.0f, 0.0f, 0.0f, r13, AnimatedTextDrawable.m8830$$Nest$fgetemojiColorFilter(this.this$0));
            }

            public void layout(float r3) {
                this.offset = r3;
                StaticLayout r32 = this.layout;
                float r0 = 0.0f;
                if (r32 != null) goto L5;
            L8:
                float r33 = 0.0f;
            L9:
                this.left = r33;
                StaticLayout r34 = this.layout;
                if (r34 != null) goto L12;
            L15:
                this.width = r0;
                return;
            L12:
                if (r34.getLineCount() <= 0) goto L15;
                r0 = this.layout.getLineWidth(0);
                goto L15
            L5:
                if (r32.getLineCount() <= 0) goto L8;
                r33 = this.layout.getLineLeft(0);
                goto L9
            }
        }

        public interface RegionCallback {
            void run(CharSequence r1, int r2, int r3);
        }

        public static class WordSequence implements CharSequence {
            private final int length;
            private final CharSequence[] words;

            public WordSequence(CharSequence r10) {
                if (r10 != null) goto L6;
                this.words = new CharSequence[0];
                this.length = 0;
                return;
            L6:
                this.length = r10.length();
                int r1 = 0;
                int r2 = 0;
            L8:
                if (r1 >= this.length) goto L13;
                if (r10.charAt(r1) != ' ') goto L12;
                r2 = r2 + 1;
            L12:
                r1 = r1 + 1;
                goto L8
            L13:
                this.words = new CharSequence[r2 + 1];
                int r22 = 0;
                int r3 = 0;
                int r5 = 0;
            L14:
                int r6 = this.length;
                if (r22 > r6) goto L25;
                if (r22 != r6) goto L18;
            L19:
                CharSequence[] r62 = this.words;
                int r7 = r3 + 1;
                if (r22 >= this.length) goto L22;
                int r8 = 1;
            L23:
                r62[r3] = r10.subSequence(r5, r8 + r22);
                r5 = r22 + 1;
                r3 = r7;
            L24:
                r22 = r22 + 1;
                goto L14
            L22:
                r8 = 0;
                goto L23
            L18:
                if (r10.charAt(r22) != ' ') goto L24;
            }

            @Override // java.lang.CharSequence
            public char charAt(int r5) {
                int r1 = 0;
            L3:
                CharSequence[] r2 = this.words;
                if (r1 >= r2.length) goto L10;
                int r22 = r2[r1].length();
                CharSequence[] r3 = this.words;
                if (r5 < r22) goto L8;
                r5 = r5 - r3[r1].length();
                r1 = r1 + 1;
                goto L3
            L8:
                return r3[r1].charAt(r5);
            L10:
                return 0;
            }

            @Override // java.lang.CharSequence
            public IntStream chars() {
                return toCharSequence().chars();
            }

            @Override // java.lang.CharSequence
            public IntStream codePoints() {
                return toCharSequence().codePoints();
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.words.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int r1, int r2) {
                return TextUtils.concat((CharSequence[]) Arrays.copyOfRange(this.words, r1, r2));
            }

            public CharSequence toCharSequence() {
                return TextUtils.concat(this.words);
            }

            @Override // java.lang.CharSequence
            public String toString() {
                StringBuilder r0 = new StringBuilder();
                int r1 = 0;
            L3:
                CharSequence[] r2 = this.words;
                if (r1 >= r2.length) goto L7;
                r0.append(r2[r1]);
                r1 = r1 + 1;
                goto L3
            L7:
                return r0.toString();
            }

            public CharSequence wordAt(int r2) {
                if (r2 < 0) goto L8;
                CharSequence[] r1 = this.words;
                if (r2 < r1.length) goto L7;
                return null;
            L7:
                return r1[r2];
            L8:
                return null;
            }
        }

        public static /* synthetic */ void $r8$lambda$0vTYwCodCss9eIngKJ_vLbAYYTs(AnimatedTextDrawable r0, int r1, ArrayList r2, CharSequence r3, int r4, int r5) {
            r0.lambda$setText$2(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$6VvrGEsQhszgZjlaqlpWXoQfvYc(AnimatedTextDrawable r0, int r1, int r2, ValueAnimator r3) {
            r0.lambda$setTextColor$9(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$9c_C23FHwNpZjuPxuA2EAFec6XM(AnimatedTextDrawable r0, int r1, ArrayList r2, CharSequence r3, int r4, int r5) {
            r0.lambda$setText$1(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$P0qz-Y7Ra5Ps3Ybe-tZtTeftQs8, reason: not valid java name */
        public static /* synthetic */ void m8826$r8$lambda$P0qzY7Ra5Ps3YbetZtTeftQs8(AnimatedTextDrawable r0, ValueAnimator r1) {
            r0.lambda$setText$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$gt3Y5UHzfQse3367JB7zeoDvTRE(AnimatedTextDrawable r0, int r1, ArrayList r2, ArrayList r3, CharSequence r4, int r5, int r6) {
            r0.lambda$setText$0(r1, r2, r3, r4, r5, r6);
        }

        /* renamed from: -$$Nest$fgetallowCancel, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m8827$$Nest$fgetallowCancel(AnimatedTextDrawable r0) {
            return r0.allowCancel;
        }

        /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ValueAnimator m8828$$Nest$fgetanimator(AnimatedTextDrawable r0) {
            return r0.animator;
        }

        /* renamed from: -$$Nest$fgetemojiCacheType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8829$$Nest$fgetemojiCacheType(AnimatedTextDrawable r0) {
            return r0.emojiCacheType;
        }

        /* renamed from: -$$Nest$fgetemojiColorFilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ColorFilter m8830$$Nest$fgetemojiColorFilter(AnimatedTextDrawable r0) {
            return r0.emojiColorFilter;
        }

        /* renamed from: -$$Nest$fgetgravity, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8831$$Nest$fgetgravity(AnimatedTextDrawable r0) {
            return r0.gravity;
        }

        /* renamed from: -$$Nest$fgetonAnimationFinishListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m8832$$Nest$fgetonAnimationFinishListener(AnimatedTextDrawable r0) {
            return r0.onAnimationFinishListener;
        }

        /* renamed from: -$$Nest$fgettoSetText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CharSequence m8833$$Nest$fgettoSetText(AnimatedTextDrawable r0) {
            return r0.toSetText;
        }

        /* renamed from: -$$Nest$fgettoSetTextMoveDown, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m8834$$Nest$fgettoSetTextMoveDown(AnimatedTextDrawable r0) {
            return r0.toSetTextMoveDown;
        }

        /* renamed from: -$$Nest$fgetwidthUpdatedListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m8835$$Nest$fgetwidthUpdatedListener(AnimatedTextDrawable r0) {
            return r0.widthUpdatedListener;
        }

        /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8836$$Nest$fputanimator(AnimatedTextDrawable r0, ValueAnimator r1) {
            r0.animator = r1;
        }

        /* renamed from: -$$Nest$fputoldText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8837$$Nest$fputoldText(AnimatedTextDrawable r0, CharSequence r1) {
            r0.oldText = r1;
        }

        /* renamed from: -$$Nest$fputoldWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8838$$Nest$fputoldWidth(AnimatedTextDrawable r0, float r1) {
            r0.oldWidth = r1;
        }

        /* renamed from: -$$Nest$fputt, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8839$$Nest$fputt(AnimatedTextDrawable r0, float r1) {
            r0.t = r1;
        }

        /* renamed from: -$$Nest$fputtoSetText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8840$$Nest$fputtoSetText(AnimatedTextDrawable r0, CharSequence r1) {
            r0.toSetText = r1;
        }

        /* renamed from: -$$Nest$fputtoSetTextMoveDown, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8841$$Nest$fputtoSetTextMoveDown(AnimatedTextDrawable r0, boolean r1) {
            r0.toSetTextMoveDown = r1;
        }

        /* renamed from: -$$Nest$mclearOldParts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8842$$Nest$mclearOldParts(AnimatedTextDrawable r0) {
            r0.clearOldParts();
        }

        public AnimatedTextDrawable() {
            this(false, false, false);
        }

        public AnimatedTextDrawable(boolean r2, boolean r3, boolean r4) {
            this(r2, r3, r4, false);
        }

        public AnimatedTextDrawable(boolean r6, boolean r7, boolean r8, boolean r9) {
            this.textPaint = new TextPaint(1);
            this.gravity = 0;
            this.isRTL = false;
            this.emojiCacheType = 0;
            this.t = 0.0f;
            this.moveDown = true;
            this.animateDelay = 0;
            this.animateDuration = 320;
            this.animateInterpolator = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.animateWave = -1.0f;
            this.moveAmplitude = 0.3f;
            this.scaleAmplitude = 0.0f;
            this.alpha = 255;
            this.bounds = new Rect();
            this.includeFontPadding = true;
            this.centerY = true;
            this.shadowed = false;
            this.splitByWords = r6;
            this.preserveIndex = r7;
            this.startFromEnd = r8;
            this.enforceByLetter = r9;
        }

        private void applyAlphaInternal(float r5) {
            this.textPaint.setAlpha((int) (this.alpha * r5));
            if (this.shadowed == false) goto L6;
            this.textPaint.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, Theme.multAlpha(this.shadowColor, r5));
            return;
        }

        private void clearCurrentParts() {
            if (this.currentParts == null) goto L8;
            int r0 = 0;
        L5:
            Part[] r1 = this.currentParts;
            if (r0 >= r1.length) goto L8;
            r1[r0].detach();
            r0 = r0 + 1;
        L8:
            this.currentParts = null;
        }

        private void clearOldParts() {
            if (this.oldParts == null) goto L8;
            int r0 = 0;
        L5:
            Part[] r1 = this.oldParts;
            if (r0 >= r1.length) goto L8;
            r1[r0].detach();
            r0 = r0 + 1;
        L8:
            this.oldParts = null;
        }

        private void diff(CharSequence r17, CharSequence r18, RegionCallback r19, RegionCallback r20, RegionCallback r21) {
            boolean r7 = false;
            if (this.updateAll == false) goto L7;
            part(r21, r17, 0, r17.length());
            part(r20, r18, 0, r18.length());
            return;
        L7:
            if (this.preserveIndex == false) goto L71;
            int r6 = Math.min(r18.length(), r17.length());
            if (this.startFromEnd == false) goto L48;
            ArrayList r9 = new ArrayList();
            int r10 = 0;
            int r12 = 0;
            boolean r11 = true;
            boolean r13 = true;
        L11:
            if (r10 > r6) goto L30;
            int r14 = (r18.length() - r10) - 1;
            int r15 = (r17.length() - r10) - 1;
            if (r14 < 0) goto L18;
            if (r15 < 0) goto L18;
            if (partEquals(r18, r17, r14, r15) == false) goto L18;
            boolean r142 = true;
        L19:
            if (r11 != r142) goto L21;
            if (r10 == r6) goto L21;
        L29:
            r10 = r10 + 1;
        L21:
            int r122 = r10 - r12;
            if (r122 > 0) goto L24;
        L28:
            r12 = r10;
            r11 = r142;
            goto L29
        L24:
            if (r9.size() == 0) goto L27;
            r11 = r13;
        L27:
            r9.add(Integer.valueOf(r122));
            r13 = r11;
        L18:
            r142 = false;
            goto L19
        L30:
            int r102 = r18.length() - r6;
            int r112 = r17.length() - r6;
            if (r102 <= 0) goto L33;
            part(r20, r18.subSequence(0, r102), 0, r102);
        L33:
            if (r112 <= 0) goto L35;
            part(r21, r17.subSequence(0, r112), 0, r112);
        L35:
            int r62 = r9.size() - 1;
        L36:
            if (r62 < 0) goto L110;
            int r123 = ((Integer) r9.get(r62)).intValue();
            if ((r62 % 2) != 0) goto L40;
            boolean r143 = true;
        L41:
            if (r143 == r13) goto L43;
            int r144 = r102 + r123;
            part(r20, r18.subSequence(r102, r144), r102, r144);
            int r145 = r112 + r123;
            part(r21, r17.subSequence(r112, r145), r112, r145);
        L47:
            r102 = r102 + r123;
            r112 = r112 + r123;
            r62 = r62 - 1;
            goto L36
        L43:
            if (r18.length() <= r17.length()) goto L45;
            int r146 = r102 + r123;
            r19.run(r18.subSequence(r102, r146), r102, r146);
            goto L47
        L45:
            int r147 = r112 + r123;
            r19.run(r17.subSequence(r112, r147), r112, r147);
            goto L47
        L40:
            r143 = false;
            goto L41
        L110:
            return;
        L48:
            int r92 = 0;
            int r113 = 0;
            boolean r103 = true;
        L49:
            if (r92 > r6) goto L65;
            if (r92 < r6) goto L52;
        L54:
            boolean r124 = false;
        L55:
            if (r103 != r124) goto L58;
            if (r92 == r6) goto L58;
        L63:
            r92 = r92 + 1;
        L58:
            if ((r92 - r113) <= 0) goto L62;
            if (r103 == false) goto L61;
            part(r19, r18.subSequence(r113, r92), r113, r92);
            goto L62
        L61:
            part(r20, r18.subSequence(r113, r92), r113, r92);
            part(r21, r17.subSequence(r113, r92), r113, r92);
        L62:
            r113 = r92;
            r103 = r124;
            goto L63
        L52:
            if (partEquals(r18, r17, r92, r92) == false) goto L54;
            r124 = true;
            goto L55
        L65:
            if ((r18.length() - r6) <= 0) goto L68;
            part(r20, r18.subSequence(r6, r18.length()), r6, r18.length());
        L68:
            if ((r17.length() - r6) <= 0) goto L96;
            part(r21, r17.subSequence(r6, r17.length()), r6, r17.length());
            return;
        L96:
            return;
        L71:
            int r63 = Math.min(r18.length(), r17.length());
            int r93 = 0;
            int r104 = 0;
            int r125 = 0;
            int r132 = 0;
            boolean r114 = true;
        L72:
            if (r93 > r63) goto L111;
            if (r93 < r63) goto L75;
        L77:
            boolean r148 = r7;
        L78:
            if (r114 != r148) goto L80;
            if (r93 == r63) goto L80;
        L93:
            if (r148 == false) goto L95;
            r104 = r104 + 1;
        L95:
            r93 = r93 + 1;
            r7 = false;
        L80:
            if (r93 != r63) goto L82;
            r93 = r18.length();
            r104 = r17.length();
        L82:
            int r152 = r93 - r125;
            int r72 = r104 - r132;
            if (r152 > 0) goto L85;
            if (r72 > 0) goto L85;
        L92:
            r125 = r93;
            r132 = r104;
            r114 = r148;
        L85:
            if (r152 != r72) goto L88;
            if (r114 == false) goto L88;
            r19.run(r18.subSequence(r125, r93), r125, r93);
        L88:
            if (r152 <= 0) goto L90;
            part(r20, r18.subSequence(r125, r93), r125, r93);
        L90:
            if (r72 <= 0) goto L92;
            part(r21, r17.subSequence(r132, r104), r132, r104);
            goto L92
        L75:
            if (partEquals(r18, r17, r93, r104) == false) goto L77;
            r148 = true;
            goto L78
        }

        private /* synthetic */ void lambda$setText$0(int r2, ArrayList r3, ArrayList r4, CharSequence r5, int r6, int r7) {
            StaticLayout r22 = makeLayout(r5, r2 - ((int) Math.ceil(Math.min(this.currentWidth, this.oldWidth))));
            Part r52 = new Part(this, r22, this.currentWidth, r3.size());
            Part r62 = new Part(this, r22, this.oldWidth, r3.size());
            r4.add(r52);
            r3.add(r62);
            float r32 = r52.width;
            this.currentWidth += r32;
            this.oldWidth += r32;
            this.currentHeight = Math.max(this.currentHeight, r22.getHeight());
            this.oldHeight = Math.max(this.oldHeight, r22.getHeight());
        }

        private /* synthetic */ void lambda$setText$1(int r1, ArrayList r2, CharSequence r3, int r4, int r5) {
            Part r32 = new Part(this, makeLayout(r3, r1 - ((int) Math.ceil(this.currentWidth))), this.currentWidth, -1);
            r2.add(r32);
            this.currentWidth += r32.width;
            this.currentHeight = Math.max(this.currentHeight, r1.getHeight());
        }

        private /* synthetic */ void lambda$setText$2(int r1, ArrayList r2, CharSequence r3, int r4, int r5) {
            Part r32 = new Part(this, makeLayout(r3, r1 - ((int) Math.ceil(this.oldWidth))), this.oldWidth, -1);
            r2.add(r32);
            this.oldWidth += r32.width;
            this.oldHeight = Math.max(this.oldHeight, r1.getHeight());
        }

        private /* synthetic */ void lambda$setText$3(ValueAnimator r1) {
            this.t = ((Float) r1.getAnimatedValue()).floatValue();
            invalidateSelf();
            Runnable r0 = this.widthUpdatedListener;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        private /* synthetic */ void lambda$setTextColor$9(int r1, int r2, ValueAnimator r3) {
            setTextColor(ColorUtils.blendARGB(r1, r2, ((Float) r3.getAnimatedValue()).floatValue()));
            invalidateSelf();
        }

        private StaticLayout makeLayout(CharSequence r4, int r5) {
            if (r5 > 0) goto L5;
            Point r52 = AndroidUtilities.displaySize;
            r5 = Math.min(r52.x, r52.y);
        L5:
            return StaticLayout.Builder.obtain(r4, 0, r4.length(), this.textPaint, r5).setMaxLines(1).setLineSpacing(0.0f, 1.0f).setAlignment(Layout.Alignment.ALIGN_NORMAL).setEllipsize(TextUtils.TruncateAt.END).setEllipsizedWidth(r5).setIncludePad(this.includeFontPadding).build();
        }

        private void part(RegionCallback r3, CharSequence r4, int r5, int r6) {
            if (this.enforceByLetter == true) goto L5;
        L11:
            r3.run(r4, r5, r6);
            return;
        L5:
            if (r4.length() <= 1) goto L11;
            int r2 = 0;
        L8:
            if (r2 >= r4.length()) goto L10;
            int r62 = r2 + 1;
            CharSequence r0 = r4.subSequence(r2, r62);
            int r22 = r2 + r5;
            r3.run(r0, r22, r22 + 1);
            r2 = r62;
            goto L8
        }

        public static boolean partEquals(CharSequence r3, CharSequence r4, int r5, int r6) {
            if ((r3 instanceof WordSequence) == true) goto L5;
        L14:
            if (r3 != null) goto L16;
            if (r4 != null) goto L16;
        L20:
            return true;
        L16:
            if (r3 == null) goto L21;
            if (r4 == null) goto L21;
            if (r3.charAt(r5) == r4.charAt(r6)) goto L20;
        L21:
            return false;
        L5:
            if ((r4 instanceof WordSequence) == false) goto L14;
            CharSequence r32 = ((WordSequence) r3).wordAt(r5);
            CharSequence r42 = ((WordSequence) r4).wordAt(r6);
            if (r32 != null) goto L9;
            if (r42 != null) goto L9;
        L12:
            return true;
        L9:
            if (r32 != null) goto L11;
        L13:
            return false;
        L11:
            if (r32.equals(r42) == false) goto L13;
            goto L12
        }

        public void cancelAnimation() {
            ValueAnimator r0 = this.animator;
            if (r0 == null) goto L6;
            r0.cancel();
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r20) {
            if (this.ellipsizeByGradient == false) goto L5;
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(this.bounds);
            r2.right -= this.rightPadding;
            r20.saveLayerAlpha(r2, 255, 31);
        L5:
            r20.save();
            Rect r22 = this.bounds;
            r20.translate(r22.left, r22.top);
            int r23 = this.bounds.width();
            int r3 = this.bounds.height();
            float r7 = 2.0f;
            if (this.currentParts == null) goto L102;
            if (this.oldParts == null) goto L102;
            float r4 = this.t;
            if (r4 == 1.0f) goto L102;
            float r42 = AndroidUtilities.lerp(this.oldWidth, this.currentWidth, r4);
            float r10 = AndroidUtilities.lerp(this.oldHeight, this.currentHeight, this.t);
            if (this.centerY == false) goto L14;
            r20.translate(0.0f, (r3 - r10) / 2.0f);
        L14:
            int r32 = 0;
        L15:
            Part[] r102 = this.currentParts;
            float r12 = -1.0f;
            if (r32 >= r102.length) goto L64;
            Part r11 = r102[r32];
            int r13 = r11.toOppositeIndex;
            float r14 = r11.offset;
            if (this.isRTL == true) goto L20;
        L22:
            float r5 = this.t;
            float r15 = this.animateWave;
            if (r15 <= 0.0f) goto L25;
            float r17 = r7;
            r5 = AndroidUtilities.cascade(r5, r32, r102.length, r15);
        L26:
            if (r13 < 0) goto L33;
            Part r52 = this.oldParts[r13];
            float r72 = r52.offset;
            if (this.isRTL == true) goto L30;
        L32:
            float r53 = AndroidUtilities.lerp(r72 - r52.left, r14 - r11.left, this.t);
            applyAlphaInternal(1.0f);
            float r103 = 0.0f;
        L37:
            r20.save();
            if (r13 < 0) goto L40;
            float r73 = r42;
        L41:
            int r122 = this.gravity;
            if ((r122 | (-4)) != (-1)) goto L44;
        L55:
            r20.translate(r53, r103);
            if (r13 >= 0) goto L60;
            float r54 = this.scaleAmplitude;
            if (r54 <= 0.0f) goto L60;
            float r55 = AndroidUtilities.lerp(1.0f - r54, 1.0f, this.t);
            r20.scale(r55, r55, r11.width / r17, r11.layout.getHeight() / r17);
        L60:
            if (r13 < 0) goto L62;
            float r56 = 1.0f;
        L63:
            r11.draw(r20, r56);
            r20.restore();
            r32 = r32 + 1;
            r7 = r17;
            goto L15
        L62:
            r56 = this.t;
            goto L63
        L44:
            if ((r122 | (-6)) != (-1)) goto L48;
        L45:
            float r123 = r23 - r73;
        L46:
            r53 = r53 + r123;
            goto L55
        L48:
            if ((r122 | (-2)) != (-1)) goto L51;
            r123 = (r23 - r73) / r17;
            goto L46
        L51:
            if (this.isRTL == false) goto L55;
            if (this.ignoreRTL == true) goto L55;
        L40:
            r73 = this.currentWidth;
            goto L41
        L30:
            if (this.ignoreRTL == true) goto L32;
            r72 = this.oldWidth - (r72 + r52.width);
            goto L32
        L33:
            float r74 = r14 - r11.left;
            float r104 = ((-this.textPaint.getTextSize()) * this.moveAmplitude) * (1.0f - r5);
            if (this.moveDown == false) goto L36;
            r12 = 1.0f;
        L36:
            r103 = r104 * r12;
            applyAlphaInternal(r5);
            r53 = r74;
            goto L37
        L25:
            r17 = r7;
            goto L26
        L20:
            if (this.ignoreRTL == true) goto L22;
            r14 = this.currentWidth - (r14 + r11.width);
            goto L22
        L64:
            float r172 = r7;
            int r57 = 0;
        L65:
            Part[] r33 = this.oldParts;
            if (r57 >= r33.length) goto L129;
            Part r43 = r33[r57];
            if (r43.toOppositeIndex >= 0) goto L100;
            float r75 = this.t;
            float r105 = this.animateWave;
            if (r105 <= 0.0f) goto L73;
            r75 = AndroidUtilities.cascade(r75, r57, r33.length, r105);
        L73:
            float r34 = r43.offset;
            float r106 = (this.textPaint.getTextSize() * this.moveAmplitude) * r75;
            if (this.moveDown == false) goto L76;
            float r112 = 1.0f;
        L77:
            float r107 = r106 * r112;
            float r76 = 1.0f - r75;
            applyAlphaInternal(r76);
            r20.save();
            boolean r113 = this.isRTL;
            if (r113 == true) goto L80;
        L82:
            float r35 = r34 - r43.left;
            int r132 = this.gravity;
            if ((r132 | (-4)) != (-1)) goto L85;
        L96:
            r20.translate(r35, r107);
            float r36 = this.scaleAmplitude;
            if (r36 <= 0.0f) goto L99;
            float r37 = AndroidUtilities.lerp(1.0f, 1.0f - r36, this.t);
            r20.scale(r37, r37, r43.width / r172, r43.layout.getHeight() / r172);
        L99:
            r43.draw(r20, r76);
            r20.restore();
            goto L100
        L85:
            if ((r132 | (-6)) != (-1)) goto L90;
            float r114 = r23;
            float r133 = this.oldWidth;
        L87:
            float r115 = r114 - r133;
        L88:
            r35 = r35 + r115;
            goto L96
        L90:
            if ((r132 | (-2)) != (-1)) goto L92;
            r115 = (r23 - this.oldWidth) / r172;
            goto L88
        L92:
            if (r113 == false) goto L96;
            if (this.ignoreRTL == true) goto L96;
            r114 = r23;
            r133 = this.oldWidth;
            goto L87
        L80:
            if (this.ignoreRTL == true) goto L82;
            r34 = this.oldWidth - (r34 + r43.width);
            goto L82
        L76:
            r112 = -1.0f;
        L100:
            r57 = r57 + 1;
        L129:
            r20.restore();
            if (this.ellipsizeByGradient == false) goto L147;
            float r134 = AndroidUtilities.dp(16.0f);
            if (this.ellipsizeGradient != null) goto L134;
            this.ellipsizeGradient = new LinearGradient(0.0f, 0.0f, r134, 0.0f, new int[]{16711680, Opcodes.V_PREVIEW}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.ellipsizeGradientMatrix = new Matrix();
            Paint r24 = new Paint(1);
            this.ellipsizePaint = r24;
            r24.setShader(this.ellipsizeGradient);
            this.ellipsizePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        L134:
            this.ellipsizeGradientMatrix.reset();
            this.ellipsizeGradientMatrix.postTranslate((this.bounds.right - this.rightPadding) - r134, 0.0f);
            this.ellipsizeGradient.setLocalMatrix(this.ellipsizeGradientMatrix);
            int r38 = this.bounds.right;
            float r58 = this.rightPadding;
            r20.drawRect((r38 - r58) - r134, r2.top, (r38 - r58) + AndroidUtilities.dp(1.0f), this.bounds.bottom, this.ellipsizePaint);
            r20.restore();
            return;
        L147:
            return;
        L102:
            if (this.centerY == false) goto L105;
            r20.translate(0.0f, (r3 - this.currentHeight) / 2.0f);
        L105:
            if (this.currentParts == null) goto L129;
            applyAlphaInternal(1.0f);
            int r59 = 0;
        L108:
            if (r59 >= this.currentParts.length) goto L129;
            r20.save();
            Part r39 = this.currentParts[r59];
            float r44 = r39.offset;
            boolean r77 = this.isRTL;
            if (r77 == true) goto L112;
        L114:
            float r45 = r44 - r39.left;
            int r108 = this.gravity;
            if ((r108 | (-4)) == (-1)) goto L128;
            if ((r108 | (-6)) != (-1)) goto L122;
            float r78 = r23;
            float r109 = this.currentWidth;
        L119:
            float r79 = r78 - r109;
        L120:
            r45 = r45 + r79;
            goto L128
        L122:
            if ((r108 | (-2)) != (-1)) goto L124;
            r79 = (r23 - this.currentWidth) / 2.0f;
            goto L120
        L124:
            if (r77 == false) goto L128;
            if (this.ignoreRTL == true) goto L128;
            r78 = r23;
            r109 = this.currentWidth;
        L128:
            r20.translate(r45, 0.0f);
            r39.draw(r20, 1.0f);
            r20.restore();
            r59 = r59 + 1;
            goto L108
        L112:
            if (this.ignoreRTL == true) goto L114;
            r44 = this.currentWidth - (r44 + r39.width);
            goto L114
        }

        public float getAnimateToWidth() {
            return this.currentWidth;
        }

        public float getCurrentWidth() {
            if (this.currentParts == null) goto L9;
            if (this.oldParts == null) goto L9;
            return AndroidUtilities.lerp(this.oldWidth, this.currentWidth, this.t);
        L9:
            return this.currentWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public Rect getDirtyBounds() {
            return this.bounds;
        }

        public int getGravity() {
            return this.gravity;
        }

        public float getHeight() {
            return this.currentHeight;
        }

        @Override // android.graphics.drawable.Drawable
        @Deprecated
        public int getOpacity() {
            return -2;
        }

        public TextPaint getPaint() {
            return this.textPaint;
        }

        public float getRightPadding() {
            return this.rightPadding;
        }

        public CharSequence getText() {
            return this.currentText;
        }

        public int getTextColor() {
            return this.textPaint.getColor();
        }

        public float getTextSize() {
            return this.textPaint.getTextSize();
        }

        public float getWidth() {
            return Math.max(this.currentWidth, this.oldWidth);
        }

        public boolean isAnimating() {
            ValueAnimator r0 = this.animator;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isRunning() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public float isNotEmpty() {
            CharSequence r0 = this.oldText;
            float r1 = 0.0f;
            float r2 = 1.0f;
            if (r0 != null) goto L5;
        L8:
            float r02 = 0.0f;
        L9:
            CharSequence r3 = this.currentText;
            if (r3 == null) goto L16;
            if (r3.length() <= 0) goto L16;
            r1 = 1.0f;
        L16:
            if (this.oldText == null) goto L20;
            r2 = this.t;
        L20:
            return AndroidUtilities.lerp(r02, r1, r2);
        L5:
            if (r0.length() <= 0) goto L8;
            r02 = 1.0f;
            goto L9
        }

        public void relayoutText() {
            CharSequence r0 = this.currentText;
            if (r0 != null) goto L5;
            return;
        L5:
            this.currentText = null;
            setText(r0, false, true);
        }

        public void setAllowCancel(boolean r1) {
            this.allowCancel = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.alpha = r1;
        }

        public void setAnimationProperties(float r1, long r2, long r4, float r6, TimeInterpolator r7) {
            this.moveAmplitude = r1;
            this.animateDelay = r2;
            this.animateDuration = r4;
            this.animateWave = r6;
            this.animateInterpolator = r7;
        }

        public void setAnimationProperties(float r9, long r10, long r12, TimeInterpolator r14) {
            setAnimationProperties(r9, r10, r12, 1.0f, r14);
        }

        public void setBounds(float r1, float r2, float r3, float r4) {
            int r12 = (int) r1;
            int r22 = (int) r2;
            int r32 = (int) r3;
            int r42 = (int) r4;
            super.setBounds(r12, r22, r32, r42);
            this.bounds.set(r12, r22, r32, r42);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int r1, int r2, int r3, int r4) {
            super.setBounds(r1, r2, r3, r4);
            this.bounds.set(r1, r2, r3, r4);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(Rect r1) {
            super.setBounds(r1);
            this.bounds.set(r1);
        }

        public void setBounds(RectF r4) {
            setBounds((int) r4.left, (int) r4.top, (int) r4.right, (int) r4.bottom);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.textPaint.setColorFilter(r1);
        }

        public void setEllipsizeByGradient(boolean r1) {
            this.ellipsizeByGradient = r1;
            invalidateSelf();
        }

        public void setEmojiCacheType(int r1) {
            this.emojiCacheType = r1;
        }

        public void setEmojiColor(int r3) {
            if (this.emojiColor == r3) goto L6;
            this.emojiColor = r3;
            this.emojiColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
            return;
        }

        public void setEmojiColorFilter(ColorFilter r1) {
            this.emojiColorFilter = r1;
        }

        public void setGravity(int r1) {
            this.gravity = r1;
        }

        public void setHacks(boolean r2, boolean r3, boolean r4) {
            setHacks(r2, r3, r4, false);
        }

        public void setHacks(boolean r1, boolean r2, boolean r3, boolean r4) {
            this.splitByWords = r1;
            this.preserveIndex = r2;
            this.startFromEnd = r3;
            this.enforceByLetter = r4;
        }

        public void setIncludeFontPadding(boolean r1) {
            this.includeFontPadding = r1;
        }

        public void setOnAnimationFinishListener(Runnable r1) {
            this.onAnimationFinishListener = r1;
        }

        public void setOnWidthUpdatedListener(Runnable r1) {
            this.widthUpdatedListener = r1;
        }

        public void setOverrideFullWidth(int r1) {
            this.overrideFullWidth = r1;
        }

        public void setRightPadding(float r1) {
            this.rightPadding = r1;
            invalidateSelf();
        }

        public void setScaleProperty(float r1) {
            this.scaleAmplitude = r1;
        }

        public void setShadowLayer(float r2, float r3, float r4, int r5) {
            this.shadowed = true;
            TextPaint r0 = this.textPaint;
            this.shadowRadius = r2;
            this.shadowDx = r3;
            this.shadowDy = r4;
            this.shadowColor = r5;
            r0.setShadowLayer(r2, r3, r4, r5);
        }

        public void setSplitByWords(boolean r1) {
            this.splitByWords = r1;
        }

        public void setText(CharSequence r2) {
            setText(r2, true);
        }

        public void setText(CharSequence r2, boolean r3) {
            setText(r2, r3, true);
        }

        public void setText(CharSequence r11, boolean r12, boolean r13) {
            if (this.currentText == null) goto L5;
            if (r11 == null) goto L5;
        L6:
            if (r11 != null) goto L8;
            r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            int r0 = this.overrideFullWidth;
            if (r0 > 0) goto L13;
            r0 = this.bounds.width();
        L13:
            if (r12 == true) goto L15;
            ValueAnimator r10 = this.animator;
            if (r10 == null) goto L56;
            r10.cancel();
        L56:
            this.animator = null;
            this.toSetText = null;
            this.toSetTextMoveDown = false;
            this.t = 0.0f;
            if (r11.equals(this.currentText) == true) goto L59;
            clearCurrentParts();
            this.currentParts = new Part[]{new Part(this, makeLayout(r11, r0), 0.0f, -1)};
            this.currentText = r11;
            this.currentWidth = this.currentParts[0].width;
            this.currentHeight = r10.layout.getHeight();
            this.isRTL = AndroidUtilities.isRTL(this.currentText);
        L59:
            clearOldParts();
            this.oldText = null;
            this.oldWidth = 0.0f;
            this.oldHeight = 0.0f;
            invalidateSelf();
            Runnable r102 = this.widthUpdatedListener;
            if (r102 == null) goto L63;
            r102.run();
            return;
        L63:
            return;
        L15:
            if (TextUtils.equals(r11, this.currentText) == false) goto L18;
            return;
        L18:
            if (this.allowCancel == false) goto L23;
            ValueAnimator r122 = this.animator;
            if (r122 == null) goto L26;
            r122.cancel();
            this.animator = null;
        L26:
            this.oldText = this.currentText;
            this.currentText = r11;
            ArrayList r112 = new ArrayList();
            ArrayList r123 = new ArrayList();
            this.currentHeight = 0.0f;
            this.currentWidth = 0.0f;
            this.oldHeight = 0.0f;
            this.oldWidth = 0.0f;
            this.isRTL = AndroidUtilities.isRTL(this.currentText);
            AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda0 r7 = new AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda0(this, r0, r123, r112);
            AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda1 r8 = new AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda1(this, r0, r112);
            AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda2 r9 = new AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda2(this, r0, r123);
            boolean r02 = this.splitByWords;
            CharSequence r1 = this.oldText;
            if (r02 == false) goto L29;
            CharSequence r5 = new WordSequence(r1);
        L30:
            boolean r03 = this.splitByWords;
            CharSequence r14 = this.currentText;
            if (r03 == false) goto L34;
            CharSequence r6 = new WordSequence(r14);
        L35:
            diff(r5, r6, r7, r8, r9);
            clearCurrentParts();
            Part[] r103 = this.currentParts;
            if (r103 != null) goto L38;
        L39:
            this.currentParts = new Part[r112.size()];
        L40:
            r112.toArray(this.currentParts);
            clearOldParts();
            Part[] r104 = this.oldParts;
            if (r104 != null) goto L43;
        L44:
            this.oldParts = new Part[r123.size()];
        L45:
            r123.toArray(this.oldParts);
            ValueAnimator r105 = this.animator;
            if (r105 == null) goto L48;
            r105.cancel();
        L48:
            this.moveDown = r13;
            this.t = 0.0f;
            this.animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            Runnable r106 = this.widthUpdatedListener;
            if (r106 == null) goto L51;
            r106.run();
        L51:
            this.animator.addUpdateListener(new AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda3(this));
            this.animator.addListener(new AnonymousClass1(this));
            this.animator.setStartDelay(this.animateDelay);
            this.animator.setDuration(this.animateDuration);
            this.animator.setInterpolator(this.animateInterpolator);
            this.animator.start();
            return;
        L43:
            if (r104.length == r123.size()) goto L45;
        L38:
            if (r103.length == r112.size()) goto L40;
        L34:
            r6 = r14;
            goto L35
        L29:
            r5 = r1;
            goto L30
        L23:
            if (isAnimating() == false) goto L26;
            this.toSetText = r11;
            this.toSetTextMoveDown = r13;
            return;
        L5:
            r12 = false;
            goto L6
        }

        public void setTextColor(int r2) {
            this.textPaint.setColor(r2);
            this.alpha = Color.alpha(r2);
        }

        public void setTextColor(int r3, boolean r4) {
            ValueAnimator r0 = this.colorAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.colorAnimator = null;
        L5:
            if (r4 == true) goto L8;
            setTextColor(r3);
            return;
        L8:
            int r42 = getTextColor();
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.colorAnimator = r02;
            r02.addUpdateListener(new AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda4(this, r42, r3));
            this.colorAnimator.addListener(new AnonymousClass2(this, r3));
            this.colorAnimator.setDuration(240);
            this.colorAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.colorAnimator.start();
        }

        public void setTextSize(float r10) {
            float r0 = this.textPaint.getTextSize();
            this.textPaint.setTextSize(r10);
            if (Math.abs(r0 - r10) <= 0.5f) goto L24;
            int r102 = this.overrideFullWidth;
            if (r102 > 0) goto L8;
            r102 = this.bounds.width();
        L8:
            int r1 = 0;
            if (this.currentParts == null) goto L15;
            this.currentWidth = 0.0f;
            this.currentHeight = 0.0f;
            int r02 = 0;
        L11:
            Part[] r3 = this.currentParts;
            if (r02 >= r3.length) goto L15;
            StaticLayout r32 = makeLayout(r3[r02].layout.getText(), r102 - ((int) Math.ceil(Math.min(this.currentWidth, this.oldWidth))));
            Part[] r4 = this.currentParts;
            Part r5 = r4[r02];
            r4[r02] = new Part(this, r32, r5.offset, r5.toOppositeIndex);
            r5.detach();
            this.currentWidth = this.currentWidth + this.currentParts[r02].width;
            this.currentHeight = Math.max(this.currentHeight, r4.layout.getHeight());
            r02 = r02 + 1;
        L15:
            if (this.oldParts == null) goto L20;
            this.oldWidth = 0.0f;
            this.oldHeight = 0.0f;
        L17:
            Part[] r03 = this.oldParts;
            if (r1 >= r03.length) goto L20;
            StaticLayout r04 = makeLayout(r03[r1].layout.getText(), r102 - ((int) Math.ceil(Math.min(this.currentWidth, this.oldWidth))));
            Part[] r2 = this.oldParts;
            Part r33 = r2[r1];
            r2[r1] = new Part(this, r04, r33.offset, r33.toOppositeIndex);
            r33.detach();
            this.oldWidth = this.oldWidth + this.oldParts[r1].width;
            this.oldHeight = Math.max(this.oldHeight, r2.layout.getHeight());
            r1 = r1 + 1;
        L20:
            invalidateSelf();
            return;
        }

        public void setTypeface(Typeface r1) {
            this.textPaint.setTypeface(r1);
        }
    }

    /* renamed from: $r8$lambda$rFM7dhdbRyAs-rxKduKU-wbrlLo, reason: not valid java name */
    public static /* synthetic */ void m8825$r8$lambda$rFM7dhdbRyAsrxKduKUwbrlLo(AnimatedTextView r0) {
        r0.lambda$new$0();
    }

    public AnimatedTextView(Context r2) {
        this(r2, false, false, false);
    }

    public AnimatedTextView(Context r1, boolean r2, boolean r3, boolean r4) {
        super(r1);
        this.adaptWidth = true;
        this.first = true;
        AnimatedTextDrawable r12 = new AnimatedTextDrawable(r2, r3, r4);
        this.drawable = r12;
        r12.setCallback(this);
        r12.setOnAnimationFinishListener(new AnimatedTextView$$ExternalSyntheticLambda0(this));
        setTypeface(AndroidUtilities.regular());
    }

    private /* synthetic */ void lambda$new$0() {
        CharSequence r0 = this.toSetText;
        if (r0 == null) goto L6;
        setText(r0, this.toSetMoveDown, true);
        this.toSetText = null;
        this.toSetMoveDown = false;
        return;
    }

    public void cancelAnimation() {
        this.drawable.cancelAnimation();
    }

    public AnimatedTextDrawable getDrawable() {
        return this.drawable;
    }

    public TextPaint getPaint() {
        return this.drawable.getPaint();
    }

    public float getRightPadding() {
        return this.drawable.getRightPadding();
    }

    public Drawable getSizeableBackground() {
        return this.backgroundDrawable;
    }

    public CharSequence getText() {
        return this.drawable.getText();
    }

    public int getTextColor() {
        return this.drawable.getTextColor();
    }

    public int getTextHeight() {
        return getPaint().getFontMetricsInt().descent - getPaint().getFontMetricsInt().ascent;
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        super.invalidateDrawable(r1);
        invalidate();
    }

    public boolean isAnimating() {
        return this.drawable.isAnimating();
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        if (this.backgroundDrawable != null) goto L5;
    L13:
        this.drawable.setBounds(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());
        this.drawable.draw(r7);
        return;
    L5:
        if (this.hideBackgroundIfEmpty == true) goto L7;
    L8:
        int r0 = (int) ((getPaddingLeft() + this.drawable.getCurrentWidth()) + getPaddingRight());
        int r1 = AnimatedTextDrawable.m8831$$Nest$fgetgravity(this.drawable) & 7;
        Drawable r2 = this.backgroundDrawable;
        if (r1 != 5) goto L11;
        r2.setBounds(getWidth() - r0, 0, getWidth(), getHeight());
    L12:
        this.backgroundDrawable.draw(r7);
        goto L13
    L11:
        r2.setBounds(0, 0, r0, getHeight());
        goto L12
    L7:
        if (this.drawable.isNotEmpty() <= 0.0f) goto L13;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.TextView");
        r2.setText(getText());
    }

    @Override // android.view.View
    public void onMeasure(int r7, int r8) {
        int r0 = View.MeasureSpec.getSize(r7);
        int r82 = View.MeasureSpec.getSize(r8);
        int r1 = this.maxWidth;
        if (r1 <= 0) goto L6;
        r0 = Math.min(r0, r1);
    L6:
        if (this.lastMaxWidth != r0) goto L8;
    L10:
        this.lastMaxWidth = r0;
        if (this.adaptWidth == true) goto L13;
    L15:
        setMeasuredDimension(r0, r82);
        return;
    L13:
        if (View.MeasureSpec.getMode(r7) != Integer.MIN_VALUE) goto L15;
        r0 = getPaddingRight() + (getPaddingLeft() + ((int) Math.ceil(this.drawable.getAnimateToWidth())));
        goto L15
    L8:
        if (getLayoutParams().width == 0) goto L10;
        this.drawable.setBounds(getPaddingLeft(), getPaddingTop(), r0 - getPaddingRight(), r82 - getPaddingBottom());
        this.drawable.relayoutText();
        goto L10
    }

    public void setAllowCancel(boolean r1) {
        this.drawable.setAllowCancel(r1);
    }

    public void setAnimationProperties(float r1, long r2, long r4, TimeInterpolator r6) {
        this.drawable.setAnimationProperties(r1, r2, r4, r6);
    }

    public void setEllipsizeByGradient(boolean r1) {
        this.drawable.setEllipsizeByGradient(r1);
    }

    public void setEmojiCacheType(int r1) {
        this.drawable.setEmojiCacheType(r1);
    }

    public void setEmojiColor(int r2) {
        this.drawable.setEmojiColor(r2);
        invalidate();
    }

    public void setEmojiColorFilter(ColorFilter r2) {
        this.drawable.setEmojiColorFilter(r2);
        invalidate();
    }

    public void setGravity(int r1) {
        this.drawable.setGravity(r1);
    }

    public void setHideBackgroundIfEmpty(boolean r1) {
        this.hideBackgroundIfEmpty = r1;
    }

    public void setIgnoreRTL(boolean r1) {
        this.drawable.ignoreRTL = r1;
    }

    public void setIncludeFontPadding(boolean r1) {
        this.drawable.setIncludeFontPadding(r1);
    }

    public void setMaxWidth(int r1) {
        this.maxWidth = r1;
    }

    public void setOnWidthUpdatedListener(Runnable r1) {
        this.drawable.setOnWidthUpdatedListener(r1);
    }

    public void setRightPadding(float r1) {
        this.drawable.setRightPadding(r1);
    }

    public void setScaleProperty(float r1) {
        this.drawable.setScaleProperty(r1);
    }

    public void setSizeableBackground(Drawable r1) {
        this.backgroundDrawable = r1;
        invalidate();
    }

    public void setText(CharSequence r2) {
        setText(r2, true, true);
    }

    public void setText(CharSequence r2, boolean r3) {
        setText(r2, r3, true);
    }

    public void setText(CharSequence r8, boolean r9, boolean r10) {
        if (this.first == true) goto L6;
        if (r9 == false) goto L6;
        boolean r92 = true;
    L7:
        this.first = false;
        if (r92 == true) goto L10;
    L20:
        int r0 = (int) this.drawable.getAnimateToWidth();
        this.drawable.setBounds(getPaddingLeft(), getPaddingTop(), this.lastMaxWidth - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());
        this.drawable.setText(r8, r92, r10);
        if (r0 != ((int) this.drawable.getAnimateToWidth())) goto L25;
        if (r92 == false) goto L25;
        return;
    L25:
        requestLayout();
        return;
    L10:
        if (TextUtils.equals(r8, this.drawable.getText()) == true) goto L20;
        boolean r02 = AnimatedTextDrawable.m8827$$Nest$fgetallowCancel(this.drawable);
        AnimatedTextDrawable r1 = this.drawable;
        if (r02 == false) goto L17;
        if (AnimatedTextDrawable.m8828$$Nest$fgetanimator(r1) == null) goto L20;
        AnimatedTextDrawable.m8828$$Nest$fgetanimator(this.drawable).cancel();
        AnimatedTextDrawable.m8836$$Nest$fputanimator(this.drawable, null);
        goto L20
    L17:
        if (r1.isAnimating() == false) goto L20;
        this.toSetText = r8;
        this.toSetMoveDown = r10;
        return;
    L6:
        r92 = false;
        goto L7
    }

    public void setTextColor(int r2) {
        this.drawable.setTextColor(r2);
        invalidate();
    }

    public void setTextColor(int r2, boolean r3) {
        this.drawable.setTextColor(r2, r3);
        invalidate();
    }

    public void setTextSize(float r1) {
        this.drawable.setTextSize(r1);
    }

    public void setTypeface(Typeface r1) {
        this.drawable.setTypeface(r1);
    }

    public int width() {
        return (getPaddingLeft() + ((int) Math.ceil(this.drawable.getCurrentWidth()))) + getPaddingRight();
    }
}
