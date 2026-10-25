package org.telegram.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_fragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public abstract class FragmentUsernameBottomSheet {
    public static /* synthetic */ void $r8$lambda$QI9h6NNrb4RReuLs66VxLIbD1lo(Context r0, TL_fragment.TL_collectibleInfo r1, View r2) {
        Browser.openUrl(r0, r1.url);
    }

    /* renamed from: $r8$lambda$S7L-Cnu5wH87poy8I9lr8lGS5EI, reason: not valid java name */
    public static /* synthetic */ void m14854$r8$lambda$S7LCnu5wH87poy8I9lr8lGS5EI(String r0, int r1, BottomSheet r2, Theme.ResourcesProvider r3) {
        AndroidUtilities.addToClipboard(r0);
        if (r1 != 1) goto L6;
        BulletinFactory.of(r2.getContainer(), r3).createCopyBulletin(LocaleController.getString(R.string.PhoneCopied)).show();
        return;
    L6:
        BulletinFactory.of(r2.getContainer(), r3).createCopyLinkBulletin().show();
    }

    public static /* synthetic */ void $r8$lambda$djU1LRUXrKOTL5H3dgsGNzwLXMk(Runnable r0, BottomSheet r1, View r2) {
        r0.run();
        r1.dismiss();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v16, types: [android.view.View, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r6v0, types: [org.telegram.ui.ActionBar.BottomSheet] */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    public static void open(Context r30, int r31, String r32, TLObject r33, TL_fragment.TL_collectibleInfo r34, Theme.ResourcesProvider r35) {
        ?? r6 = new BottomSheet(r30, false, r35);
        r6.fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r35));
        ?? r8 = new LinearLayout(r30);
        r8.setOrientation(1);
        r8.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        FrameLayout r11 = new FrameLayout(r30);
        r11.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r35)));
        r8.addView(r11, LayoutHelper.createLinear(80, 80, 1, 0, 16, 0, 16));
        RLottieImageView r12 = new RLottieImageView(r30);
        r12.setScaleType(ImageView.ScaleType.CENTER);
        if (r31 != 0) goto L5;
        int r13 = 70;
    L6:
        if (r31 != 0) goto L8;
        int r14 = R.raw.fragment_username;
    L9:
        r12.setAnimation(r14, r13, r13);
        r12.playAnimation();
        if (Theme.isCurrentThemeMonet() == false) goto L12;
        int r142 = Theme.getColor(Theme.key_chats_actionIcon);
    L13:
        r12.setColorFilter(new PorterDuffColorFilter(r142, PorterDuff.Mode.SRC_IN));
        if (r31 != 0) goto L16;
        r12.setScaleX(0.86f);
        r12.setScaleY(0.86f);
    L17:
        r11.addView(r12, LayoutHelper.createLinear(-1, -1, 17));
        if ((r33 instanceof TLRPC.User) == false) goto L21;
        String r112 = UserObject.getUserName((TLRPC.User) r33);
    L24:
        String r10 = BillingController.getInstance().formatCurrency(r34.amount, r34.currency);
        String r7 = BillingController.getInstance().formatCurrency(r34.crypto_amount, r34.crypto_currency);
        if (r31 != 0) goto L31;
        String r9 = LocaleController.formatString(R.string.FragmentUsernameTitle, new Object[]{"@" + r32});
        int r122 = R.string.FragmentUsernameMessage;
        Object r21 = r8;
        String r82 = LocaleController.formatShortDateTime(r34.purchase_date);
        if (TextUtils.isEmpty(r10) == false) goto L29;
        String r92 = _UrlKt.FRAGMENT_ENCODE_SET;
    L30:
        String r72 = LocaleController.formatString(r122, new Object[]{r82, r7, r92});
        String r2 = MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix + "/" + r32;
        String r93 = r9;
        String r22 = r112;
    L38:
        if (r2 == null) goto L40;
        FragmentUsernameBottomSheet$$ExternalSyntheticLambda0 r83 = new FragmentUsernameBottomSheet$$ExternalSyntheticLambda0(r2, r31, r6, r35);
    L41:
        SpannableStringBuilder r23 = AndroidUtilities.replaceSingleTag(r93, r83);
        SpannableString r94 = new SpannableString("TON");
        ColoredImageSpan r113 = new ColoredImageSpan(R.drawable.mini_gram_16);
        r113.setWidth(AndroidUtilities.dp(13.0f));
        r94.setSpan(r113, 0, r94.length(), 33);
        SpannableStringBuilder r73 = AndroidUtilities.replaceCharSequence("TON", AndroidUtilities.replaceTags(r72), r94);
        LinkSpanDrawable.LinksTextView r95 = new LinkSpanDrawable.LinksTextView(r30);
        r95.setTypeface(AndroidUtilities.bold());
        r95.setGravity(17);
        int r102 = Theme.key_dialogTextBlack;
        r95.setTextColor(Theme.getColor(r102, r35));
        r95.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2, r35));
        r95.setTextSize(1, 16.0f);
        r95.setText(r23);
        ?? r114 = r21;
        r114.addView(r95, LayoutHelper.createLinear(-1, -2, 1, 42, 0, 42, 0));
        FrameLayout r24 = new FrameLayout(r30);
        r24.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), Theme.getColor(Theme.key_groupcreate_spanBackground, r35)));
        BackupImageView r132 = new BackupImageView(r30);
        r132.setRoundRadius(AndroidUtilities.dp(28.0f));
        AvatarDrawable r96 = new AvatarDrawable();
        r96.setInfo(r33);
        r132.setForUserOrChat(r33, r96);
        r24.addView(r132, LayoutHelper.createFrame(28, 28, 51));
        TextView r3 = new TextView(r30);
        r3.setTextColor(Theme.getColor(r102, r35));
        r3.setTextSize(1, 13.0f);
        r3.setSingleLine();
        r3.setText(Emoji.replaceEmoji(r22, r3.getPaint().getFontMetricsInt(), false));
        r24.addView(r3, LayoutHelper.createFrame(-2, -2.0f, 19, 37.0f, 0.0f, 10.0f, 0.0f));
        r114.addView(r24, LayoutHelper.createLinear(-2, 28, 1, 42, 10, 42, 18));
        TextView r25 = new TextView(r30);
        r25.setGravity(17);
        r25.setTextColor(Theme.getColor(r102, r35));
        r25.setTextSize(1, 14.0f);
        r25.setText(r73);
        r114.addView(r25, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 19));
        ButtonWithCounterView r26 = new ButtonWithCounterView(r30, r35).setRound();
        r26.setText(LocaleController.getString(R.string.FragmentUsernameOpen), false);
        r26.setOnClickListener(new FragmentUsernameBottomSheet$$ExternalSyntheticLambda1(r30, r34));
        r114.addView(r26, LayoutHelper.createLinear(-1, 48, 6.0f, 0.0f, 6.0f, 0.0f));
        if (r83 == null) goto L48;
        ButtonWithCounterView r0 = new ButtonWithCounterView(r30, r35).setRound().setNeutral();
        if (r31 != 0) goto L46;
        int r1 = R.string.FragmentUsernameCopy;
    L47:
        r0.setText(LocaleController.getString(r1), false);
        r0.setOnClickListener(new FragmentUsernameBottomSheet$$ExternalSyntheticLambda2(r83, r6));
        r114.addView(r0, LayoutHelper.createLinear(-1, 48, 6.0f, 6.0f, 6.0f, 0.0f));
        goto L48
    L46:
        r1 = R.string.FragmentPhoneCopy;
    L48:
        r6.setCustomView(r114);
        r6.show();
        return;
    L40:
        r83 = null;
        goto L41
    L29:
        r92 = "(" + r10 + ")";
        goto L30
    L31:
        r21 = r8;
        if (r31 != 1) goto L50;
        r93 = LocaleController.formatString(R.string.FragmentPhoneTitle, new Object[]{PhoneFormat.getInstance().format("+" + r32)});
        int r84 = R.string.FragmentPhoneMessage;
        r22 = r112;
        String r115 = LocaleController.formatShortDateTime(r34.purchase_date);
        if (TextUtils.isEmpty(r10) == false) goto L36;
        String r123 = _UrlKt.FRAGMENT_ENCODE_SET;
    L37:
        r72 = LocaleController.formatString(r84, new Object[]{r115, r7, r123});
        r2 = PhoneFormat.getInstance().format("+" + r32);
        goto L38
    L36:
        r123 = "(" + r10 + ")";
        goto L37
    L50:
        return;
    L21:
        if ((r33 instanceof TLRPC.Chat) == false) goto L23;
        r112 = ((TLRPC.Chat) r33).title;
        goto L24
    L23:
        r112 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L24
    L16:
        r12.setTranslationY(AndroidUtilities.dp(2.0f));
        goto L17
    L12:
        r142 = -1;
        goto L13
    L8:
        r14 = R.raw.fragment;
        goto L9
    L5:
        r13 = 78;
        goto L6
    }
}
