package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;
import com.exteragram.messenger.appicons.AppIcon;
import com.exteragram.messenger.appicons.AppIconController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.ws.WebSocketProtocol;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.StarParticlesView;

/* loaded from: classes7.dex */
public class PremiumAppIconsPreviewView extends FrameLayout implements PagerHeaderView {
    private StarryIconView bottomLeftIcon;
    private StarryIconView bottomRightIcon;
    private List<AppIcon> icons;
    boolean isEmpty;
    private final Theme.ResourcesProvider resourcesProvider;
    private StarryIconView topIcon;

    public class StarryIconView extends AdaptiveIconImageView {
        StarParticlesView.Drawable drawable;
        Paint paint;
        float particlesScale;
        final /* synthetic */ PremiumAppIconsPreviewView this$0;

        public StarryIconView(PremiumAppIconsPreviewView r3, Context r4, int r5) {
            this.this$0 = r3;
            super(r4);
            this.drawable = new StarParticlesView.Drawable(20);
            this.paint = new Paint(1);
            StarParticlesView.Drawable r42 = this.drawable;
            r42.size1 = 12;
            r42.size2 = 8;
            r42.size3 = 6;
            if (r5 != 1) goto L5;
            r42.type = WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY;
        L5:
            if (r5 != 0) goto L7;
            r42.type = 1002;
        L7:
            r42.resourcesProvider = PremiumAppIconsPreviewView.m12022$$Nest$fgetresourcesProvider(r3);
            StarParticlesView.Drawable r32 = this.drawable;
            r32.colorKey = Theme.key_premiumStartSmallStarsColor2;
            r32.init();
            this.paint.setColor(-1);
        }

        @Override // org.telegram.ui.Components.Premium.AdaptiveIconImageView, android.view.View
        public void draw(Canvas r8) {
            int r0 = AndroidUtilities.dp(10.0f);
            this.drawable.excludeRect.set(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), getMeasuredWidth() - AndroidUtilities.dp(5.0f), getMeasuredHeight() - AndroidUtilities.dp(5.0f));
            float r2 = -r0;
            this.drawable.rect.set(r2, r2, getWidth() + r0, getHeight() + r0);
            r8.save();
            float r02 = this.particlesScale;
            r8.scale(1.0f - r02, 1.0f - r02, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
            this.drawable.onDraw(r8);
            r8.restore();
            invalidate();
            RectF r03 = AndroidUtilities.rectTmp;
            r03.set(0.0f, 0.0f, getWidth(), getHeight());
            r8.drawRoundRect(r03, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.paint);
            super.draw(r8);
        }
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m12022$$Nest$fgetresourcesProvider(PremiumAppIconsPreviewView r0) {
        return r0.resourcesProvider;
    }

    public PremiumAppIconsPreviewView(Context r4, Theme.ResourcesProvider r5) {
        super(r4);
        this.icons = new ArrayList();
        this.resourcesProvider = r5;
        Iterator<AppIcon> r52 = AppIconController.getAllIcons().iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        AppIcon r0 = r52.next();
        this.icons.add(r0);
        if (this.icons.size() != 3) goto L4;
    L8:
        if (this.icons.size() >= 3) goto L11;
        FileLog.e(new IllegalArgumentException("There should be at least 3 premium icons!"));
        this.isEmpty = true;
        return;
    L11:
        this.topIcon = newIconView(r4, 0);
        this.bottomLeftIcon = newIconView(r4, 1);
        this.bottomRightIcon = newIconView(r4, 2);
        setClipChildren(false);
    }

    private StarryIconView newIconView(Context r10, int r11) {
        AppIcon r0 = this.icons.get(r11);
        StarryIconView r1 = new StarryIconView(this, r10, r11);
        r1.setLayoutParams(LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 52.0f, 0.0f, 0.0f));
        r1.setForeground(r0.getForeground());
        r1.setBackgroundResource(r0.getBackground());
        r1.setPadding(AndroidUtilities.dp(8.0f));
        r1.setBackgroundOuterPadding(AndroidUtilities.dp(32.0f));
        addView(r1);
        return r1;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        super.onMeasure(r4, r5);
        if (this.isEmpty == false) goto L5;
        return;
    L5:
        int r42 = Math.min(View.MeasureSpec.getSize(r4), View.MeasureSpec.getSize(r5));
        int r52 = AndroidUtilities.dp(76.0f);
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.topIcon.getLayoutParams();
        r0.height = r52;
        r0.width = r52;
        float r1 = r52;
        r0.bottomMargin = (int) ((r42 * 0.1f) + r1);
        FrameLayout.LayoutParams r43 = (FrameLayout.LayoutParams) this.bottomLeftIcon.getLayoutParams();
        r43.height = r52;
        r43.width = r52;
        int r02 = (int) (r1 * 0.95f);
        r43.rightMargin = r02;
        FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) this.bottomRightIcon.getLayoutParams();
        r3.height = r52;
        r3.width = r52;
        r3.leftMargin = r02;
    }

    @Override // org.telegram.ui.Components.Premium.PagerHeaderView
    public void setOffset(float r9) {
        if (this.isEmpty == false) goto L5;
        return;
    L5:
        float r92 = Math.abs(r9 / getMeasuredWidth());
        float r0 = CubicBezierInterpolator.EASE_IN.getInterpolation(r92);
        this.bottomRightIcon.setTranslationX((((getRight() - this.bottomRightIcon.getRight()) + (this.bottomRightIcon.getWidth() * 1.5f)) + AndroidUtilities.dp(32.0f)) * r0);
        this.bottomRightIcon.setTranslationY(AndroidUtilities.dp(16.0f) * r0);
        float r1 = 1.0f;
        float r02 = Utilities.clamp(AndroidUtilities.lerp(1.0f, 1.5f, r0), 1.0f, 0.0f);
        this.bottomRightIcon.setScaleX(r02);
        this.bottomRightIcon.setScaleY(r02);
        this.topIcon.setTranslationY((((getTop() - this.topIcon.getTop()) - (this.topIcon.getHeight() * 1.8f)) - AndroidUtilities.dp(32.0f)) * r92);
        this.topIcon.setTranslationX(AndroidUtilities.dp(16.0f) * r92);
        float r03 = Utilities.clamp(AndroidUtilities.lerp(1.0f, 1.8f, r92), 1.0f, 0.0f);
        this.topIcon.setScaleX(r03);
        this.topIcon.setScaleY(r03);
        float r04 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r92);
        this.bottomLeftIcon.setTranslationX((((getLeft() - this.bottomLeftIcon.getLeft()) - (this.bottomLeftIcon.getWidth() * 2.5f)) + AndroidUtilities.dp(32.0f)) * r04);
        this.bottomLeftIcon.setTranslationY(r04 * (((getBottom() - this.bottomLeftIcon.getBottom()) + (this.bottomLeftIcon.getHeight() * 2.5f)) + AndroidUtilities.dp(32.0f)));
        float r05 = Utilities.clamp(AndroidUtilities.lerp(1.0f, 2.5f, r92), 1.0f, 0.0f);
        this.bottomLeftIcon.setScaleX(r05);
        this.bottomLeftIcon.setScaleY(r05);
        if (r92 >= 0.4f) goto L8;
        r1 = r92 / 0.4f;
    L8:
        this.bottomRightIcon.particlesScale = r1;
        this.topIcon.particlesScale = r1;
        this.bottomLeftIcon.particlesScale = r1;
    }
}
