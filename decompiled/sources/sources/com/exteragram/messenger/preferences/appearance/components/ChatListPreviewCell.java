package com.exteragram.messenger.preferences.appearance.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import com.exteragram.messenger.preferences.components.PreviewBackgroundDrawable;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class ChatListPreviewCell extends FrameLayout implements CustomPreferenceCell {
    private final ActionBar actionBar;
    private Drawable premiumStar;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable statusDrawable;

    /* renamed from: com.exteragram.messenger.preferences.appearance.components.ChatListPreviewCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedEmojiDrawable.WrapSizeDrawable {
        final /* synthetic */ ChatListPreviewCell this$0;

        public AnonymousClass1(ChatListPreviewCell r1, Drawable r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiDrawable.WrapSizeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            r3.save();
            r3.translate(0.0f, AndroidUtilities.dp(1.0f));
            super.draw(r3);
            r3.restore();
        }
    }

    public ChatListPreviewCell(Context r11) {
        super(r11);
        setWillNotDraw(false);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(null, AndroidUtilities.dp(26.0f));
        this.statusDrawable = r1;
        r1.center = true;
        ActionBar r12 = new ActionBar(r11);
        this.actionBar = r12;
        r12.setItemsColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), false);
        r12.setOccupyStatusBar(false);
        r12.createMenu().addItem(0, R.drawable.ic_ab_other);
        r12.setBackground(new PreviewBackgroundDrawable());
        r12.setSupportsHolidayImage(true);
        addView(r12, LayoutHelper.createFrame(-1, -2.0f, 17, 21.0f, 21.0f, 21.0f, 21.0f));
        updateStatus(false);
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ChatListPreviewCell) == true) goto L8;
        return false;
    L8:
        return Objects.equals(this.actionBar, ((ChatListPreviewCell) r2).actionBar);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.statusDrawable;
        if (r0 == null) goto L6;
        r0.attach();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.statusDrawable;
        if (r0 == null) goto L6;
        r0.detach();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, View.MeasureSpec.makeMeasureSpec(0, 0));
        setMeasuredDimension(View.MeasureSpec.getSize(r1), getMeasuredHeight());
    }

    public void updateCentered(boolean r1) {
        this.actionBar.refreshTitlePosition(r1);
    }

    public void updateStatus(boolean r5) {
        if (this.actionBar != null) goto L5;
        return;
    L5:
        TLRPC.User r0 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
        if (r0 != null) goto L8;
    L18:
        Drawable r02 = null;
    L19:
        ActionBar r4 = this.actionBar;
        if (r5 == false) goto L23;
        r4.setTitleAnimatedX(LocaleUtils.getActionBarTitle(), r02, true, 250);
        return;
    L23:
        r4.setTitle(LocaleUtils.getActionBarTitle(), r02);
        return;
    L8:
        if (ExteraConfig.getHideActionBarStatus() == true) goto L18;
        Long r1 = UserObject.getEmojiStatusDocumentId(r0);
        if (r1 == null) goto L13;
        this.statusDrawable.set(r1.longValue(), r5);
        this.statusDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_profile_verifiedBackground)));
        r02 = this.statusDrawable;
        goto L19
    L13:
        if (r0.premium == false) goto L18;
        if (this.premiumStar != null) goto L17;
        Drawable r03 = getContext().getResources().getDrawable(R.drawable.msg_premium_liststar).mutate();
        r03.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_profile_verifiedBackground), PorterDuff.Mode.MULTIPLY));
        this.premiumStar = new AnonymousClass1(this, r03, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
    L17:
        r02 = this.premiumStar;
        goto L19
    }
}
