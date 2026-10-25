package org.telegram.ui.Components;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public class TopViewCell extends LinearLayout implements Theme.Colorable {
    public int imageSize;
    public final BackupImageView imageView;
    private int lastIconResId;
    private final Theme.ResourcesProvider resourcesProvider;
    public final LinkSpanDrawable.LinksTextView textView;
    public final LinkSpanDrawable.LinksTextView titleView;

    public static /* synthetic */ void $r8$lambda$2fCTsEN68e_P1M0EoT3rF0V5igE(TopViewCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public TopViewCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.imageSize = 90;
        this.resourcesProvider = r12;
        setOrientation(1);
        BackupImageView r0 = new BackupImageView(r11);
        this.imageView = r0;
        r0.getImageReceiver().setAutoRepeatCount(1);
        r0.getImageReceiver().setAutoRepeat(1);
        r0.setOnClickListener(new TopViewCell$$ExternalSyntheticLambda0(this));
        addView(r0, LayoutHelper.createLinear(90, 90, 17, 0, 9, 0, 9));
        LinkSpanDrawable.LinksTextView r02 = new LinkSpanDrawable.LinksTextView(r11);
        this.titleView = r02;
        r02.setTextSize(1, 20.0f);
        r02.setGravity(17);
        r02.setTypeface(AndroidUtilities.bold());
        r02.setTextAlignment(4);
        addView(r02, LayoutHelper.createLinear(-1, -2, 17, 48, 0, 48, 10));
        LinkSpanDrawable.LinksTextView r03 = new LinkSpanDrawable.LinksTextView(r11);
        this.textView = r03;
        r03.setTextSize(1, 14.0f);
        r03.setGravity(17);
        r03.setTextAlignment(4);
        addView(r03, LayoutHelper.createLinear(-1, -2, 17, 48, 0, 48, 17));
        updateColors();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        this.imageView.getImageReceiver().startAnimation();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public void setEmoji(int r5) {
        if (this.lastIconResId == r5) goto L6;
        BackupImageView r0 = this.imageView;
        this.lastIconResId = r5;
        r0.setImageDrawable(new RLottieDrawable(r5, AndroidUtilities.dp(90.0f), AndroidUtilities.dp(90.0f)));
        this.imageView.getImageReceiver().setAutoRepeat(2);
        return;
    }

    public void setEmoji(String r3, String r4) {
        MediaDataController.getInstance(UserConfig.selectedAccount).setPlaceholderImage(this.imageView, r3, r4, "90_90");
    }

    public void setEmojiSize(int r2) {
        if (this.imageSize == r2) goto L6;
        this.imageSize = r2;
        updateColors();
        return;
    }

    public void setEmojiStatic(int r2) {
        if (this.lastIconResId == r2) goto L6;
        this.imageView.clearImage();
        BackupImageView r0 = this.imageView;
        this.lastIconResId = r2;
        r0.setImageResource(r2);
        return;
    }

    public void setText(CharSequence r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        this.textView.setVisibility(8);
    L6:
        updateColors();
        return;
    L5:
        this.titleView.setVisibility(8);
        this.textView.setVisibility(0);
        this.textView.setText(r3);
        LinkSpanDrawable.LinksTextView r0 = this.textView;
        r0.setMaxWidth(HintView2.cutInFancyHalf(r3, r0.getPaint()));
        this.textView.requestLayout();
        goto L6
    }

    public void setText(CharSequence r2, CharSequence r3) {
        this.titleView.setText(r2);
        this.titleView.setVisibility(0);
        this.textView.setText(r3);
        LinkSpanDrawable.LinksTextView r22 = this.textView;
        r22.setMaxWidth(HintView2.cutInFancyHalf(r3, r22.getPaint()));
        this.textView.requestLayout();
        updateColors();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        LinkSpanDrawable.LinksTextView r0 = this.titleView;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        LinkSpanDrawable.LinksTextView r02 = this.titleView;
        int r2 = Theme.key_chat_messageLinkIn;
        r02.setLinkTextColor(Theme.getColor(r2, this.resourcesProvider));
        LinkSpanDrawable.LinksTextView r03 = this.textView;
        if (this.titleView.getVisibility() == 0) goto L6;
        r1 = Theme.key_windowBackgroundWhiteGrayText4;
    L6:
        r03.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        this.textView.setLinkTextColor(Theme.getColor(r2, this.resourcesProvider));
        BackupImageView r04 = this.imageView;
        int r12 = this.imageSize;
        if (this.titleView.getVisibility() != 0) goto L10;
        int r8 = 0;
    L11:
        r04.setLayoutParams(LayoutHelper.createLinear(r12, r12, 17, 0, r8, 0, 9));
        return;
    L10:
        r8 = 9;
        goto L11
    }
}
