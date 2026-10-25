package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class LinkCell extends FrameLayout {
    private ImageView imageView;
    private String link;
    private FrameLayout linkContainer;
    private SpoilersTextView linkView;
    private String slug;

    public static /* synthetic */ void $r8$lambda$1K_XjcvM42ovWL9kcitvjX3LGd8(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$CQtLtIEejY5ypXfPmKeQ2qFnETY(LinkCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$EJvwZxd7Ew4keGKjs9VbAO2Nufc(LinkCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public LinkCell(Context r18, BaseFragment r19, Theme.ResourcesProvider r20) {
        super(r18);
        this.linkContainer = new FrameLayout(r18);
        SpoilersTextView r3 = new SpoilersTextView(r18);
        this.linkView = r3;
        r3.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(50.0f), AndroidUtilities.dp(13.0f));
        this.linkView.setTextSize(1, 16.0f);
        this.linkView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.linkView.setSingleLine(true);
        this.linkView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r20));
        SpoilersTextView r1 = this.linkView;
        r1.allowClickSpoilers = false;
        this.linkContainer.addView(r1, LayoutHelper.createFrame(-2, -2, 17));
        FrameLayout r12 = this.linkContainer;
        int r5 = AndroidUtilities.dp(8.0f);
        int r6 = Theme.getColor(Theme.key_graySection, r20);
        int r7 = Theme.key_listSelector;
        r12.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r5, r6, ColorUtils.setAlphaComponent(Theme.getColor(r7, r20), 76)));
        addView(this.linkContainer, LayoutHelper.createFrame(-1, -2.0f, 0, 14.0f, 0.0f, 14.0f, 0.0f));
        this.linkContainer.setOnClickListener(new LinkCell$$ExternalSyntheticLambda0(this));
        ImageView r13 = new ImageView(getContext());
        this.imageView = r13;
        r13.setImageResource(R.drawable.menu_copy_s);
        this.imageView.setColorFilter(Theme.getColor(Theme.key_dialogTextBlack, r20));
        this.imageView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.imageView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r7, r20), 76)));
        addView(this.imageView, LayoutHelper.createFrame(40, 40.0f, 21, 15.0f, 0.0f, 17.0f, 0.0f));
        this.imageView.setOnClickListener(new LinkCell$$ExternalSyntheticLambda1(this));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        AndroidUtilities.addToClipboard(this.link);
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        AndroidUtilities.addToClipboard(this.link);
    }

    public void hideSlug(Runnable r6) {
        this.imageView.setVisibility(4);
        this.linkView.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(18.0f));
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags |= 256;
        SpannableStringBuilder r1 = new SpannableStringBuilder("t.me/giftcode/" + this.slug);
        if (this.slug != null) goto L5;
        r1.append("1234567891011123654897566536223");
    L5:
        r1.setSpan(new TextStyleSpan(r0), 0, r1.length(), 33);
        this.linkView.setText(r1);
        this.linkContainer.setOnClickListener(new LinkCell$$ExternalSyntheticLambda2(r6));
    }

    public void setSlug(String r3) {
        this.slug = r3;
        this.link = "https://t.me/giftcode/" + r3;
        this.linkView.setText("t.me/giftcode/" + r3);
    }
}
