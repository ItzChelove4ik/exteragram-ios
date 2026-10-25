package org.telegram.ui.ActionBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Iterator;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.android.animator.ReplaceAnimator;
import me.vkryl.core.lambda.Destroyable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EllipsizeSpanAnimator;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public abstract class ActionBarAnimatedSubtitleOverlayContainer extends FrameLayout implements ReplaceAnimator.Callback {
    private final EllipsizeSpanAnimator ellipsizeSpanAnimator;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ReplaceAnimator<SimpleTextViewReplaceable> titleOverlayAnimator;

    /* loaded from: classes6.dex */
    public class SimpleTextViewReplaceable extends TextView implements Destroyable {
        final /* synthetic */ ActionBarAnimatedSubtitleOverlayContainer this$0;

        public SimpleTextViewReplaceable(ActionBarAnimatedSubtitleOverlayContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // me.vkryl.core.lambda.Destroyable
        public void performDestroy() {
            AndroidUtilities.removeFromParent(this);
            ActionBarAnimatedSubtitleOverlayContainer.m5429$$Nest$fgetellipsizeSpanAnimator(this.this$0).removeView(this);
        }
    }

    /* renamed from: -$$Nest$fgetellipsizeSpanAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EllipsizeSpanAnimator m5429$$Nest$fgetellipsizeSpanAnimator(ActionBarAnimatedSubtitleOverlayContainer r0) {
        return r0.ellipsizeSpanAnimator;
    }

    public ActionBarAnimatedSubtitleOverlayContainer(Context r4, Theme.ResourcesProvider r5, EllipsizeSpanAnimator r6) {
        super(r4);
        this.titleOverlayAnimator = new ReplaceAnimator(this, CubicBezierInterpolator.EASE_OUT_QUINT, 350);
        this.resourcesProvider = r5;
        this.ellipsizeSpanAnimator = r6;
    }

    private void checkUi_titleOverlayTextAnimation() {
        Iterator<ListAnimator.Entry<SimpleTextViewReplaceable>> r4 = this.titleOverlayAnimator.iterator();
    L4:
        if (r4.hasNext() == false) goto L10;
        ListAnimator.Entry<SimpleTextViewReplaceable> r0 = r4.next();
        float r1 = r0.getVisibility();
        float r2 = AndroidUtilities.lerp(0.85f, 1.0f, r1);
        r0.item.setAlpha(r1);
        r0.item.setScaleX(r2);
        r0.item.setScaleY(r2);
        SimpleTextViewReplaceable r22 = r0.item;
        if (r0.isAffectingList() == false) goto L8;
        float r02 = 9.0f;
    L9:
        r22.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dp(r02), 0, r1));
        goto L4
    L8:
        r02 = -9.0f;
        goto L9
    }

    public float getTotalVisibility() {
        return this.titleOverlayAnimator.getMetadata().getTotalVisibility();
    }

    public void onItemChanged(ReplaceAnimator<?> r1) {
        checkUi_titleOverlayTextAnimation();
    }

    public void setText(CharSequence r6, boolean r7) {
        if (TextUtils.isEmpty(r6) == false) goto L6;
        this.titleOverlayAnimator.clear(r7);
        return;
    L6:
        int r0 = TextUtils.indexOf(r6, "...");
        if (r0 < 0) goto L9;
        SpannableString r62 = SpannableString.valueOf(r6);
        this.ellipsizeSpanAnimator.wrap(r62, r0);
        boolean r02 = true;
        CharSequence r63 = r62;
    L10:
        SimpleTextViewReplaceable r2 = new SimpleTextViewReplaceable(this, getContext());
        int r3 = Theme.key_telegram_color_dialogsLogo;
        r2.setTextColor(Theme.getColor(r3, this.resourcesProvider));
        r2.setLinkTextColor(Theme.getColor(r3, this.resourcesProvider));
        r2.setTextSize(1, 14.0f);
        r2.setAlpha(0.0f);
        r2.setText(r63);
        if (r02 == false) goto L13;
        this.ellipsizeSpanAnimator.addView(r2);
    L13:
        addView(r2, LayoutHelper.createFrame(-2, -2.0f));
        this.titleOverlayAnimator.replace(r2, r7);
        return;
    L9:
        r02 = false;
        r63 = r6;
        goto L10
    }

    public void updateColors() {
        Iterator<ListAnimator.Entry<SimpleTextViewReplaceable>> r0 = this.titleOverlayAnimator.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ListAnimator.Entry<SimpleTextViewReplaceable> r1 = r0.next();
        SimpleTextViewReplaceable r2 = r1.item;
        int r3 = Theme.key_telegram_color_dialogsLogo;
        r2.setTextColor(Theme.getColor(r3, this.resourcesProvider));
        r1.item.setLinkTextColor(Theme.getColor(r3, this.resourcesProvider));
        goto L4
    }
}
