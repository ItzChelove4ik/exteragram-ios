package org.telegram.ui.Components;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class BottomPagerTabs extends View {
    private Utilities.Callback<Integer> onTabClick;
    private float progress;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean scrolling;
    private AnimatedFloat scrollingT;
    private final Paint selectPaint;
    private final Tab[] tabs;
    private boolean touchDown;
    private int value;

    public class Tab {
        private boolean active;
        final RectF clickRect;
        public int customEndFrameEnd;
        public int customEndFrameMid;
        public boolean customFrameInvert;
        final RLottieDrawable drawable;
        private int drawableColor;
        final int i;
        final StaticLayout layout;
        final float layoutLeft;
        final float layoutWidth;
        final AnimatedFloat nonscrollingT;
        final TextPaint paint;
        final Drawable ripple;
        final /* synthetic */ BottomPagerTabs this$0;

        /* renamed from: -$$Nest$fgetactive, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9033$$Nest$fgetactive(Tab r0) {
            return r0.active;
        }

        public Tab(BottomPagerTabs r11, int r12, int r13, int r14, int r15, CharSequence r16) {
            this.this$0 = r11;
            TextPaint r2 = new TextPaint(1);
            this.paint = r2;
            this.clickRect = new RectF();
            this.nonscrollingT = new AnimatedFloat(r11, 0, 200, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.drawableColor = -1;
            this.i = r12;
            this.customEndFrameMid = r14;
            this.customEndFrameEnd = r15;
            RLottieDrawable r122 = new RLottieDrawable(r13, AndroidUtilities.dp(29.0f), AndroidUtilities.dp(29.0f));
            this.drawable = r122;
            r122.setMasterParent(r11);
            r122.setAllowDecodeSingleFrame(true);
            r122.setPlayInDirectionOfCustomEndFrame(true);
            r122.setAutoRepeat(0);
            r2.setTypeface(AndroidUtilities.bold());
            r2.setTextSize(AndroidUtilities.dp(12.0f));
            int r123 = Theme.key_windowBackgroundWhiteBlackText;
            r2.setColor(Theme.getColor(r123, BottomPagerTabs.m9031$$Nest$fgetresourcesProvider(r11)));
            StaticLayout r0 = new StaticLayout(r16, r2, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.layout = r0;
            float r152 = 0.0f;
            if (r0.getLineCount() <= 0) goto L5;
            float r142 = r0.getLineWidth(0);
        L6:
            this.layoutWidth = r142;
            if (r0.getLineCount() <= 0) goto L9;
            r152 = r0.getLineLeft(0);
        L9:
            this.layoutLeft = r152;
            this.ripple = Theme.createSelectorDrawable(Theme.multAlpha(Theme.getColor(r123, BottomPagerTabs.m9031$$Nest$fgetresourcesProvider(r11)), 0.1f), 7, AndroidUtilities.dp(16.0f));
            return;
        L5:
            r142 = 0.0f;
            goto L6
        }

        public void setActive(boolean r4, boolean r5) {
            if (this.customFrameInvert == false) goto L6;
            r4 = !r4;
        L6:
            if (this.active != r4) goto L9;
            return;
        L9:
            if (BottomPagerTabs.m9032$$Nest$fgettabs(this.this$0)[this.i].customEndFrameMid == 0) goto L23;
            RLottieDrawable r52 = this.drawable;
            if (r4 == false) goto L19;
            r52.setCustomEndFrame(this.customEndFrameMid);
            if (this.drawable.getCurrentFrame() < (this.customEndFrameEnd - 2)) goto L15;
            this.drawable.setCurrentFrame(0, false);
        L15:
            int r53 = this.drawable.getCurrentFrame();
            int r0 = this.customEndFrameMid;
            RLottieDrawable r1 = this.drawable;
            if (r53 > r0) goto L18;
            r1.start();
        L27:
            this.active = r4;
            return;
        L18:
            r1.setCurrentFrame(r0);
            goto L27
        L19:
            int r54 = r52.getCurrentFrame();
            int r02 = this.customEndFrameMid - 1;
            RLottieDrawable r2 = this.drawable;
            if (r54 < r02) goto L22;
            r2.setCustomEndFrame(this.customEndFrameEnd - 1);
            this.drawable.start();
            goto L27
        L22:
            r2.setCustomEndFrame(0);
            this.drawable.setCurrentFrame(0);
            goto L27
        L23:
            if (r4 == false) goto L27;
            this.drawable.setCurrentFrame(0);
            if (r5 == false) goto L27;
            this.drawable.start();
            goto L27
        }

        public void setColor(int r3) {
            this.paint.setColor(r3);
            if (this.drawableColor == r3) goto L6;
            RLottieDrawable r0 = this.drawable;
            this.drawableColor = r3;
            r0.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
            return;
        }
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m9031$$Nest$fgetresourcesProvider(BottomPagerTabs r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Tab[] m9032$$Nest$fgettabs(BottomPagerTabs r0) {
        return r0.tabs;
    }

    public BottomPagerTabs(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.selectPaint = new Paint(1);
        this.scrollingT = new AnimatedFloat(this, 0, 210, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.resourcesProvider = r10;
        this.tabs = createTabs();
        setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        setProgress(0.0f, false);
    }

    private void setProgress(float r5, boolean r6) {
        float r52 = Utilities.clamp(r5, this.tabs.length, 0.0f);
        this.progress = r52;
        this.value = Math.round(r52);
        int r0 = 0;
    L3:
        Tab[] r1 = this.tabs;
        if (r0 >= r1.length) goto L14;
        Tab r12 = r1[r0];
        float r2 = Math.abs(this.value - r0);
        if (Tab.m9033$$Nest$fgetactive(this.tabs[r0]) == false) goto L8;
        float r3 = 0.25f;
    L10:
        if (r2 >= r3) goto L12;
        boolean r22 = true;
    L13:
        r12.setActive(r22, r6);
        r0 = r0 + 1;
        goto L3
    L12:
        r22 = false;
        goto L13
    L8:
        r3 = 0.35f;
        goto L10
    L14:
        invalidate();
    }

    public abstract Tab[] createTabs();

    @Override // android.view.View
    public void dispatchDraw(Canvas r20) {
        r20.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        r20.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.getShadowHeight(), Theme.dividerPaint);
        int r2 = ((getWidth() - getPaddingLeft()) - getPaddingRight()) / this.tabs.length;
        int r3 = Math.min(AndroidUtilities.dp(64.0f), r2);
        float r4 = this.scrollingT.set(this.scrolling);
        float r5 = 0.0f;
        float r7 = 41.0f;
        float r8 = 9.0f;
        float r9 = 16.0f;
        if (r4 <= 0.0f) goto L5;
        this.selectPaint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), (int) ((((Math.abs((Math.floor(this.progress) + 0.5d) - this.progress) * 1.2000000476837158d) + 0.4000000059604645d) * 18.0d) * r4)));
        float r11 = r2;
        float r13 = r11 / 2.0f;
        float r6 = getPaddingLeft() + AndroidUtilities.lerp((((float) Math.floor(this.progress)) * r11) + r13, (r11 * ((float) Math.ceil(this.progress))) + r13, this.progress - ((int) r13));
        RectF r112 = AndroidUtilities.rectTmp;
        float r12 = r3 / 2.0f;
        r112.set(r6 - r12, AndroidUtilities.dp(9.0f), r6 + r12, AndroidUtilities.dp(41.0f));
        r20.drawRoundRect(r112, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.selectPaint);
    L5:
        int r113 = 0;
    L6:
        Tab[] r122 = this.tabs;
        if (r113 >= r122.length) goto L16;
        Tab r123 = r122[r113];
        r123.clickRect.set(getPaddingLeft() + (r113 * r2), r5, r13 + r2, getHeight());
        float r62 = 1.0f - Math.min(1.0f, Math.abs(this.progress - r113));
        int r14 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6, this.resourcesProvider);
        int r15 = Theme.key_windowBackgroundWhiteBlackText;
        r123.setColor(ColorUtils.blendARGB(r14, Theme.getColor(r15, this.resourcesProvider), r62));
        Rect r52 = AndroidUtilities.rectTmp2;
        float r16 = r7;
        float r72 = r3 / 2.0f;
        float r17 = r8;
        float r18 = r9;
        r52.set((int) (r123.clickRect.centerX() - r72), AndroidUtilities.dp(r17), (int) (r123.clickRect.centerX() + r72), AndroidUtilities.dp(r16));
        AnimatedFloat r73 = r123.nonscrollingT;
        if (r62 <= 0.6f) goto L11;
        boolean r63 = true;
    L12:
        float r64 = r73.set(r63);
        if (r4 >= 1.0f) goto L15;
        this.selectPaint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(r15, this.resourcesProvider), (int) ((r64 * 18.0f) * (1.0f - r4))));
        RectF r65 = AndroidUtilities.rectTmp;
        r65.set(r52);
        r20.drawRoundRect(r65, AndroidUtilities.dp(r18), AndroidUtilities.dp(r18), this.selectPaint);
    L15:
        r123.ripple.setBounds(r52);
        r123.ripple.draw(r20);
        float r66 = AndroidUtilities.dp(29.0f) / 2.0f;
        r52.set((int) (r123.clickRect.centerX() - r66), (int) (AndroidUtilities.dpf2(24.66f) - r66), (int) (r123.clickRect.centerX() + r66), (int) (AndroidUtilities.dpf2(24.66f) + r66));
        r123.drawable.setBounds(r52);
        r123.drawable.draw(r20);
        r20.save();
        r20.translate((r123.clickRect.centerX() - (r123.layoutWidth / 2.0f)) - r123.layoutLeft, AndroidUtilities.dp(50.0f) - (r123.layout.getHeight() / 2.0f));
        r123.layout.draw(r20);
        r20.restore();
        r113 = r113 + 1;
        r7 = r16;
        r8 = r17;
        r9 = r18;
        r5 = 0.0f;
        goto L6
    L11:
        r63 = false;
        goto L12
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.getSize(r2), AndroidUtilities.dp(64.0f) + AndroidUtilities.getShadowHeight());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        if (r7.getAction() != 0) goto L7;
        this.touchDown = true;
        return true;
    L7:
        if (r7.getAction() != 1) goto L9;
    L19:
        float r0 = r7.getX();
        int r3 = 0;
    L20:
        Tab[] r4 = this.tabs;
        if (r3 >= r4.length) goto L33;
        RectF r42 = r4[r3].clickRect;
        if (r42.left >= r0) goto L32;
        if (r42.right <= r0) goto L32;
        if (r7.getAction() != 1) goto L29;
    L34:
        int r02 = 0;
    L36:
        if (r02 >= this.tabs.length) goto L42;
        if (r02 == r3) goto L39;
    L40:
        this.tabs[r02].ripple.setState(new int[0]);
    L41:
        r02 = r02 + 1;
        goto L36
    L39:
        if (r7.getAction() != 1) goto L41;
    L42:
        if (r3 >= 0) goto L44;
    L48:
        this.touchDown = false;
    L50:
        return super.onTouchEvent(r7);
    L44:
        if (this.value == r3) goto L48;
        Utilities.Callback<Integer> r03 = this.onTabClick;
        if (r03 == null) goto L48;
        r03.run(Integer.valueOf(r3));
        goto L48
    L29:
        if (this.touchDown == false) goto L31;
        this.tabs[r3].ripple.setState(new int[0]);
    L31:
        this.tabs[r3].ripple.setState(new int[]{R.attr.state_pressed, R.attr.state_enabled});
    L32:
        r3 = r3 + 1;
        goto L20
    L33:
        r3 = -1;
        goto L34
    L9:
        if (r7.getAction() == 2) goto L19;
        if (r7.getAction() != 3) goto L50;
        int r72 = 0;
    L14:
        Tab[] r04 = this.tabs;
        if (r72 >= r04.length) goto L17;
        r04[r72].ripple.setState(new int[0]);
        r72 = r72 + 1;
        goto L14
    L17:
        this.touchDown = false;
        return true;
    }

    public void setOnTabClick(Utilities.Callback<Integer> r1) {
        this.onTabClick = r1;
    }

    public void setProgress(float r2) {
        setProgress(r2, true);
    }

    public void setScrolling(boolean r2) {
        if (this.scrolling != r2) goto L5;
        return;
    L5:
        this.scrolling = r2;
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r4) {
        int r0 = 0;
    L3:
        Tab[] r1 = this.tabs;
        if (r0 >= r1.length) goto L11;
        if (r1[r0].ripple == r4) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return true;
    L11:
        return super.verifyDrawable(r4);
    }
}
