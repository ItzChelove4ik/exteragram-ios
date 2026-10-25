package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import me.vkryl.android.util.ClickHelper;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class UnsupportedBlockDrawable extends Drawable {
    private final Drawable bubbleDrawable;
    private final Paint buttonBackgroundPaint;
    private final ButtonBounce buttonBounce;
    private final int buttonGap;
    private final int buttonHeight;
    private StaticLayout buttonLayout;
    private final int buttonPaddingH;
    private final int buttonRadius;
    private final RectF buttonRect;
    private CharSequence buttonText;
    private final TextPaint buttonTextPaint;
    private final ClickHelper clickHelper;
    private int measuredHeight;
    private int measuredWidth;
    private Runnable onClickListener;
    private final int paddingV;
    private final Drawable planeDrawable;
    private Theme.ResourcesProvider resourcesProvider;
    private CharSequence subtitle;
    private StaticLayout subtitleLayout;
    private final TextPaint subtitlePaint;
    private final int textLeft;
    private CharSequence title;
    private StaticLayout titleLayout;
    private final TextPaint titlePaint;
    private final int titleSubtitleGap;

    /* renamed from: org.telegram.ui.Components.UnsupportedBlockDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements ClickHelper.Delegate {
        final /* synthetic */ UnsupportedBlockDrawable this$0;

        public AnonymousClass1(UnsupportedBlockDrawable r1) {
            this.this$0 = r1;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public boolean needClickAt(View r3, float r4, float r5) {
            int r32 = AndroidUtilities.dp(9.0f);
            float r1 = -r32;
            UnsupportedBlockDrawable.m13495$$Nest$fgetbuttonRect(this.this$0).inset(r1, r1);
            boolean r42 = UnsupportedBlockDrawable.m13495$$Nest$fgetbuttonRect(this.this$0).contains(r4, r5);
            float r33 = r32;
            UnsupportedBlockDrawable.m13495$$Nest$fgetbuttonRect(this.this$0).inset(r33, r33);
            return r42;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onClickAt(View r1, float r2, float r3) {
            if (UnsupportedBlockDrawable.m13496$$Nest$fgetonClickListener(this.this$0) == null) goto L6;
            UnsupportedBlockDrawable.m13496$$Nest$fgetonClickListener(this.this$0).run();
            return;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onClickTouchDown(View r1, float r2, float r3) {
            UnsupportedBlockDrawable.m13494$$Nest$fgetbuttonBounce(this.this$0).setPressed(true);
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onClickTouchUp(View r1, float r2, float r3) {
            UnsupportedBlockDrawable.m13494$$Nest$fgetbuttonBounce(this.this$0).setPressed(false);
        }
    }

    /* renamed from: -$$Nest$fgetbuttonBounce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonBounce m13494$$Nest$fgetbuttonBounce(UnsupportedBlockDrawable r0) {
        return r0.buttonBounce;
    }

    /* renamed from: -$$Nest$fgetbuttonRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m13495$$Nest$fgetbuttonRect(UnsupportedBlockDrawable r0) {
        return r0.buttonRect;
    }

    /* renamed from: -$$Nest$fgetonClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13496$$Nest$fgetonClickListener(UnsupportedBlockDrawable r0) {
        return r0.onClickListener;
    }

    public UnsupportedBlockDrawable(Theme.ResourcesProvider r7) {
        TextPaint r0 = new TextPaint(1);
        this.titlePaint = r0;
        TextPaint r2 = new TextPaint(1);
        this.subtitlePaint = r2;
        TextPaint r3 = new TextPaint(1);
        this.buttonTextPaint = r3;
        this.buttonBackgroundPaint = new Paint(1);
        this.buttonRect = new RectF();
        ButtonBounce r1 = new ButtonBounce(null);
        this.buttonBounce = r1;
        this.clickHelper = new ClickHelper(new AnonymousClass1(this));
        this.textLeft = AndroidUtilities.dp(62.33f);
        this.buttonPaddingH = AndroidUtilities.dp(12.0f);
        this.buttonHeight = AndroidUtilities.dp(30.0f);
        this.buttonRadius = AndroidUtilities.dp(15.0f);
        this.paddingV = AndroidUtilities.dp(7.0f);
        this.buttonGap = AndroidUtilities.dp(12.0f);
        this.titleSubtitleGap = AndroidUtilities.dp(2.0f);
        this.resourcesProvider = r7;
        this.planeDrawable = ApplicationLoader.applicationContext.getDrawable(R.drawable.send_plane_26).mutate();
        this.bubbleDrawable = ApplicationLoader.applicationContext.getDrawable(R.drawable.large_unsupported).mutate();
        r1.setAdditionalInvalidate(new UnsupportedBlockDrawable$$ExternalSyntheticLambda0(this));
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextSize(AndroidUtilities.dp(14.0f));
        r2.setTextSize(AndroidUtilities.dp(12.0f));
        r3.setTypeface(AndroidUtilities.bold());
        r3.setTextSize(AndroidUtilities.dp(14.0f));
        updateColors();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r10) {
        if (this.titleLayout != null) goto L5;
        return;
    L5:
        if (this.subtitleLayout != null) goto L7;
        return;
    L7:
        if (this.buttonLayout == null) goto L13;
        int r0 = getBounds().left;
        int r1 = getBounds().right;
        int r2 = getBounds().centerY();
        int r3 = r2 - (((this.titleLayout.getHeight() + this.titleSubtitleGap) + this.subtitleLayout.getHeight()) / 2);
        r10.save();
        r10.translate(this.textLeft + r0, r3);
        this.titleLayout.draw(r10);
        r10.translate(0.0f, this.titleLayout.getHeight() + this.titleSubtitleGap);
        this.subtitleLayout.draw(r10);
        r10.restore();
        int r12 = r1 - AndroidUtilities.dp(11.0f);
        float r8 = r2 - (this.buttonHeight / 2);
        this.buttonRect.set(r12 - ((int) (this.buttonLayout.getWidth() + (this.buttonPaddingH * 2))), r8, r12, r5 + r4);
        float r13 = this.buttonBounce.getScale(0.05f);
        r10.save();
        r10.scale(r13, r13, this.buttonRect.centerX(), this.buttonRect.centerY());
        Theme.multAlpha(-1, 0.18f);
        RectF r14 = this.buttonRect;
        int r4 = this.buttonRadius;
        r10.drawRoundRect(r14, r4, r4, this.buttonBackgroundPaint);
        r10.save();
        r10.translate(r3 + this.buttonPaddingH, r8 + ((this.buttonHeight - this.buttonLayout.getHeight()) / 2.0f));
        this.buttonLayout.draw(r10);
        r10.restore();
        r10.restore();
        float r22 = r2 + 1;
        DrawableUtils.setBounds(this.bubbleDrawable, AndroidUtilities.dp(29.66f) + r0, r22, 17);
        this.bubbleDrawable.draw(r10);
        DrawableUtils.setBounds(this.planeDrawable, r0 + AndroidUtilities.dp(29.66f), r22, 17);
        this.planeDrawable.draw(r10);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public int measure(int r19) {
        this.measuredWidth = r19;
        TextPaint r2 = this.buttonTextPaint;
        CharSequence r3 = this.buttonText;
        float r22 = r2.measureText(r3, 0, r3.length());
        CharSequence r7 = this.buttonText;
        TextPaint r8 = this.buttonTextPaint;
        int r9 = (int) Math.ceil(r22);
        Layout.Alignment r14 = Layout.Alignment.ALIGN_NORMAL;
        this.buttonLayout = new StaticLayout(r7, r8, r9, r14, 1.0f, 0.0f, false);
        int r13 = (((r19 - this.textLeft) - ((int) ((this.buttonPaddingH * 2) + r22))) - this.buttonGap) - AndroidUtilities.dp(11.0f);
        this.titleLayout = new StaticLayout(TextUtils.ellipsize(this.title, this.titlePaint, r13, TextUtils.TruncateAt.END), this.titlePaint, r13, r14, 1.0f, 0.0f, false);
        this.subtitleLayout = new StaticLayout(this.subtitle, this.subtitlePaint, r13, r14, 1.0f, 0.0f, false);
        int r1 = Math.max((this.titleLayout.getHeight() + this.titleSubtitleGap) + this.subtitleLayout.getHeight(), this.buttonHeight) + (this.paddingV * 2);
        this.measuredHeight = r1;
        setBounds(0, 0, this.measuredWidth, r1);
        return this.measuredHeight;
    }

    public boolean onTouchEvent(View r1, MotionEvent r2) {
        return this.clickHelper.onTouchEvent(r1, r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    public void setButtonText(CharSequence r1) {
        this.buttonText = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setOnClickListener(Runnable r1) {
        this.onClickListener = r1;
    }

    public void setSubtitle(CharSequence r1) {
        this.subtitle = r1;
    }

    public void setTitle(CharSequence r1) {
        this.title = r1;
    }

    public void updateColors() {
        int r0 = Theme.getColor(Theme.key_chat_serviceText);
        Drawable r1 = this.planeDrawable;
        PorterDuff.Mode r3 = PorterDuff.Mode.MULTIPLY;
        r1.setColorFilter(new PorterDuffColorFilter(r0, r3));
        this.bubbleDrawable.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(-16777216, 0.11f), r3));
        this.titlePaint.setColor(r0);
        this.subtitlePaint.setColor(ColorUtils.setAlphaComponent(r0, 179));
        this.buttonTextPaint.setColor(r0);
        this.buttonBackgroundPaint.setColor(Theme.multAlpha(-16777216, 0.11f));
    }
}
