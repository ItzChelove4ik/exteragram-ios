package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class ArchiveHelp extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private int currentAccount;
    private Runnable linkCallback;
    private LinkSpanDrawable.LinksTextView subtitleTextView;

    /* renamed from: $r8$lambda$ihWRbktfxqy9VAfT73WUkX0-l-Y, reason: not valid java name */
    public static /* synthetic */ void m8843$r8$lambda$ihWRbktfxqy9VAfT73WUkX0lY(Runnable r0, View r1) {
        r0.run();
    }

    public ArchiveHelp(Context r11, int r12, Theme.ResourcesProvider r13, Runnable r14, Runnable r15) {
        super(r11);
        this.currentAccount = r12;
        this.linkCallback = r14;
        ContactsController.getInstance(r12).loadGlobalPrivacySetting();
        LinearLayout r122 = new LinearLayout(r11);
        r122.setOrientation(1);
        int r2 = -1;
        addView(r122, LayoutHelper.createFrame(-1, -2, 17));
        ImageView r0 = new ImageView(r11);
        r0.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_avatar_backgroundSaved, r13)));
        r0.setImageResource(R.drawable.large_archive);
        r0.setScaleType(ImageView.ScaleType.CENTER);
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        r2 = Theme.getColor(Theme.key_chats_actionIcon);
    L5:
        r0.setColorFilter(r2, PorterDuff.Mode.SRC_IN);
        if (r15 == null) goto L8;
        int r7 = 14;
    L9:
        r122.addView(r0, LayoutHelper.createLinear(80, 80, 49, 0, r7, 0, 14));
        TextView r02 = new TextView(r11);
        r02.setTextSize(1, 20.0f);
        r02.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r13));
        r02.setTypeface(AndroidUtilities.bold());
        r02.setGravity(1);
        r02.setText(LocaleController.getString(R.string.ArchiveHintHeader1));
        r122.addView(r02, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 9));
        LinkSpanDrawable.LinksTextView r03 = new LinkSpanDrawable.LinksTextView(r11);
        this.subtitleTextView = r03;
        r03.setTextSize(1, 14.0f);
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r13));
        this.subtitleTextView.setGravity(1);
        updateText();
        r122.addView(this.subtitleTextView, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 25));
        r122.addView(makeHint(R.drawable.msg_archive_archive, LocaleController.getString("ArchiveHintSection1"), LocaleController.getString("ArchiveHintSection1Info"), r13), LayoutHelper.createLinear(-1, -2, 7, 32, 0, 32, 16));
        r122.addView(makeHint(R.drawable.msg_archive_hide, LocaleController.getString("ArchiveHintSection2"), LocaleController.getString("ArchiveHintSection2Info"), r13), LayoutHelper.createLinear(-1, -2, 7, 32, 0, 32, 16));
        r122.addView(makeHint(R.drawable.msg_archive_stories, LocaleController.getString("ArchiveHintSection3"), LocaleController.getString("ArchiveHintSection3Info"), r13), LayoutHelper.createLinear(-1, -2, 7, 32, 0, 32, 16));
        if (r15 == null) goto L13;
        ButtonWithCounterView r10 = new ButtonWithCounterView(r11, r13).setRound();
        r10.setText(LocaleController.getString("GotIt"), false);
        r10.setOnClickListener(new ArchiveHelp$$ExternalSyntheticLambda0(r15));
        r122.addView(r10, LayoutHelper.createLinear(-1, 48, 14.0f, 18.0f, 14.0f, 0.0f));
        return;
    L13:
        return;
    L8:
        r7 = 0;
        goto L9
    }

    private FrameLayout makeHint(int r12, CharSequence r13, CharSequence r14, Theme.ResourcesProvider r15) {
        FrameLayout r0 = new FrameLayout(getContext());
        ImageView r1 = new ImageView(getContext());
        int r2 = Theme.key_dialogTextBlack;
        r1.setColorFilter(Theme.getColor(r2, r15));
        r1.setImageResource(r12);
        r0.addView(r1, LayoutHelper.createFrame(24, 24.0f, 51, 0.0f, 8.0f, 0.0f, 0.0f));
        LinearLayout r122 = new LinearLayout(getContext());
        r122.setOrientation(1);
        TextView r16 = new TextView(getContext());
        r16.setTextColor(Theme.getColor(r2, r15));
        r16.setTypeface(AndroidUtilities.bold());
        r16.setTextSize(0, AndroidUtilities.dp(14.0f));
        r16.setText(r13);
        r122.addView(r16, LayoutHelper.createLinear(-1, -2, 0.0f, 2.6f, 0.0f, 0.0f));
        TextView r132 = new TextView(getContext());
        r132.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r15));
        r132.setTextSize(0, AndroidUtilities.dp(14.0f));
        r132.setText(r14);
        r122.addView(r132, LayoutHelper.createLinear(-1, -2, 0.0f, 2.6f, 0.0f, 0.0f));
        r0.addView(r122, LayoutHelper.createFrame(-1, -2.0f, 55, 41.0f, 0.0f, 0.0f, 0.0f));
        return r0;
    }

    private void updateText() {
        TLRPC.GlobalPrivacySettings r0 = ContactsController.getInstance(this.currentAccount).getGlobalPrivacySettings();
        if (r0 == null) goto L5;
        boolean r02 = r0.keep_archived_unmuted;
    L6:
        if (r02 == false) goto L8;
        String r03 = "ArchiveHintSubtitle";
    L9:
        String r04 = LocaleController.getString(r03);
        int r1 = Theme.key_chat_messageLinkIn;
        SpannableStringBuilder r05 = AndroidUtilities.replaceSingleTag(r04, r1, 0, this.linkCallback);
        SpannableString r2 = new SpannableString(">");
        Drawable r5 = getContext().getResources().getDrawable(R.drawable.msg_arrowright).mutate();
        r5.setColorFilter(new PorterDuffColorFilter(r1, PorterDuff.Mode.SRC_IN));
        ColoredImageSpan r6 = new ColoredImageSpan(r5);
        r6.setColorKey(r1);
        r6.setSize(AndroidUtilities.dp(18.0f));
        r6.setWidth(AndroidUtilities.dp(11.0f));
        r6.setTranslateX(-AndroidUtilities.dp(5.0f));
        r2.setSpan(r6, 0, r2.length(), 33);
        this.subtitleTextView.setText(AndroidUtilities.replaceCharSequence(">", r05, r2));
        return;
    L8:
        r03 = "ArchiveHintSubtitleUnmutedMove";
        goto L9
    L5:
        r02 = true;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.privacyRulesUpdated) goto L6;
        updateText();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.privacyRulesUpdated);
        updateText();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.privacyRulesUpdated);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(400.0f), View.MeasureSpec.getSize(r2)), TLObject.FLAG_30), r3);
    }
}
