package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public class PremiumFeatureCell extends FrameLayout {
    public PremiumPreviewFragment.PremiumFeatureData data;
    public final TextView description;
    boolean drawDivider;
    public AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable imageDrawable;
    public ImageView imageView;
    public final ImageView nextIcon;
    private Drawable premiumStar;
    public final SimpleTextView title;

    public static class Factory extends UItem.UItemFactory<PremiumFeatureCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((PremiumFeatureCell) r1).setData((PremiumPreviewFragment.PremiumFeatureData) r2.object, r3);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public PremiumFeatureCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new PremiumFeatureCell(r1, r5);
        }
    }

    public PremiumFeatureCell(Context r2) {
        this(r2, null);
    }

    public PremiumFeatureCell(Context r13, Theme.ResourcesProvider r14) {
        super(r13);
        LinearLayout r0 = new LinearLayout(r13);
        r0.setOrientation(1);
        setClipChildren(false);
        r0.setClipChildren(false);
        SimpleTextView r2 = new SimpleTextView(r13);
        this.title = r2;
        r2.setTypeface(AndroidUtilities.bold());
        r2.setTextSize(15);
        r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r14));
        r0.addView(r2, LayoutHelper.createLinear(-1, -2));
        TextView r22 = new TextView(r13);
        this.description = r22;
        r22.setTextSize(1, 14.0f);
        r22.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r14));
        r22.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r0.addView(r22, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 1, 0, 0));
        addView(r0, LayoutHelper.createFrame(-1, -2.0f, 0, 62.0f, 8.0f, 48.0f, 9.0f));
        this.imageView = new ImageView(r13);
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), PorterDuff.Mode.SRC_IN));
    L5:
        ImageView r02 = this.imageView;
        ImageView.ScaleType r1 = ImageView.ScaleType.CENTER_INSIDE;
        r02.setScaleType(r1);
        addView(this.imageView, LayoutHelper.createFrame(28, 28.0f, 0, 18.0f, 12.0f, 0.0f, 0.0f));
        ImageView r03 = new ImageView(r13);
        this.nextIcon = r03;
        r03.setScaleType(r1);
        r03.setImageResource(R.drawable.msg_arrowright);
        r03.setColorFilter(Theme.getColor(Theme.key_switchTrack, r14));
        addView(r03, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 18.0f, 0.0f));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        if (this.imageDrawable == null) goto L6;
        updateImageBounds();
        this.imageDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon)));
        this.imageDrawable.draw(r8);
    L6:
        if (this.drawDivider == false) goto L9;
        r8.drawRect(AndroidUtilities.dp(62.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight(), Theme.dividerPaint);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.imageDrawable;
        if (r0 == null) goto L5;
        r0.attach();
    L5:
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.imageDrawable;
        if (r0 == null) goto L5;
        r0.detach();
    L5:
        super.onDetachedFromWindow();
    }

    public void setData(PremiumPreviewFragment.PremiumFeatureData r5, boolean r6) {
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L5;
    L18:
        this.nextIcon.setVisibility(0);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.imageDrawable;
        if (r0 == null) goto L21;
        r0.detach();
        this.imageDrawable = null;
    L21:
        this.data = r5;
        this.title.setText(r5.title);
        this.description.setText(r5.description);
        this.imageView.setImageResource(r5.icon);
        this.drawDivider = r6;
        return;
    L5:
        if (r5.type != 12) goto L18;
        if (r5.icon != R.drawable.filled_premium_status2) goto L18;
        this.nextIcon.setVisibility(8);
        if (this.imageDrawable != null) goto L13;
        this.imageDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, false, AndroidUtilities.dp(24.0f), 13);
        if (isAttachedToWindow() == false) goto L13;
        this.imageDrawable.attach();
    L13:
        Long r02 = UserObject.getEmojiStatusDocumentId(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser());
        if (r02 != null) goto L16;
        long r2 = 0;
    L17:
        setEmoji(r2, false);
        goto L21
    L16:
        r2 = r02.longValue();
        goto L17
    }

    public void setEmoji(long r5, boolean r7) {
        if (this.imageDrawable != null) goto L8;
        this.imageDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, false, AndroidUtilities.dp(24.0f), 13);
        if (isAttachedToWindow() == false) goto L8;
        this.imageDrawable.attach();
    L8:
        if (r5 == 0) goto L10;
        this.imageDrawable.set(r5, r7);
        return;
    L10:
        if (this.premiumStar != null) goto L12;
        Drawable r52 = getContext().getResources().getDrawable(R.drawable.msg_premium_prolfilestar).mutate();
        this.premiumStar = r52;
        r52.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon), PorterDuff.Mode.SRC_IN));
    L12:
        this.imageDrawable.set(this.premiumStar, r7);
    }

    public void updateImageBounds() {
        this.imageDrawable.setBounds((getWidth() - this.imageDrawable.getIntrinsicWidth()) - AndroidUtilities.dp(21.0f), (getHeight() - this.imageDrawable.getIntrinsicHeight()) / 2, getWidth() - AndroidUtilities.dp(21.0f), (getHeight() + this.imageDrawable.getIntrinsicHeight()) / 2);
    }
}
