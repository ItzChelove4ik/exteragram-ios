package org.telegram.ui.Business;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class BusinessLinksEmptyView extends LinearLayout {
    private TextView descriptionView;
    private ImageView imageView;
    private TextView linkView;

    public static /* synthetic */ void $r8$lambda$pzWkOivmibCKpFrm3b5_o8bc3JQ(TL_account.TL_businessChatLink r0, BaseFragment r1, View r2) {
        AndroidUtilities.addToClipboard(r0.link);
        BulletinFactory.of(r1).createCopyLinkBulletin().show();
    }

    public BusinessLinksEmptyView(Context r18, BaseFragment r19, TL_account.TL_businessChatLink r20, Theme.ResourcesProvider r21) {
        super(r18);
        setOrientation(1);
        int r5 = Theme.key_chat_serviceText;
        Theme.getColor(r5, r21);
        Theme.getColor(r5);
        ImageView r6 = new ImageView(r18);
        this.imageView = r6;
        r6.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        this.imageView.setImageResource(R.drawable.filled_chatlink_large);
        addView(this.imageView, LayoutHelper.createLinear(78, 78, 49, 17, 17, 17, 9));
        TextView r62 = new TextView(r18);
        this.descriptionView = r62;
        r62.setTextAlignment(4);
        this.descriptionView.setTextSize(1, 13.0f);
        this.descriptionView.setTextColor(Theme.getColor(r5, r21));
        this.descriptionView.setGravity(1);
        this.descriptionView.setMaxWidth(AndroidUtilities.dp(208.0f));
        this.descriptionView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.BusinessLinksIntro)));
        addView(this.descriptionView, LayoutHelper.createLinear(-2, -2, 49, 17, 0, 17, 9));
        TextView r63 = new TextView(r18);
        this.linkView = r63;
        r63.setTextAlignment(4);
        this.linkView.setTextSize(1, 13.0f);
        this.linkView.setTextColor(Theme.getColor(r5, r21));
        TextView r1 = this.linkView;
        r1.setTypeface(r1.getTypeface(), 1);
        this.linkView.setGravity(1);
        this.linkView.setMaxWidth(AndroidUtilities.dp(208.0f));
        this.linkView.setText(BusinessLinksController.stripHttps(r20.link));
        this.linkView.setBackground(Theme.createRadSelectorDrawable(503316480, 503316480, 5, 5));
        this.linkView.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), 0);
        this.linkView.setOnClickListener(new BusinessLinksEmptyView$$ExternalSyntheticLambda0(r20, r19));
        addView(this.linkView, LayoutHelper.createLinear(-2, -2, 49, 17, 0, 17, 17));
    }
}
