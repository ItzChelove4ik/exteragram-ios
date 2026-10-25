package org.telegram.ui.bots;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;

/* loaded from: classes7.dex */
public class BotAdView extends FrameLayout {
    public final TextView channelTitleView;
    public final ImageView closeView;
    public final BackupImageView imageView;
    private final LinearLayout layout;
    public final TextView removeView;
    private final Theme.ResourcesProvider resourcesProvider;
    public final LinkSpanDrawable.LinksTextView textView;
    public final TextView titleView;

    public static /* synthetic */ void $r8$lambda$EEwx3MrNLrZ4Eznf9kZe3puoYlg(BotAdView r0, ChatActivity r1, MessageObject r2, String r3, View r4) {
        r0.lambda$set$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$OIb3kV9Ft2Li2M_cmO02FE0zoNY(BotAdView r0, ChatActivity r1, MessageObject r2, ClickableSpan r3) {
        r0.lambda$set$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$P25H35BgaKK2z3c9Musq0lGku4E(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$Va83yWJilzRQbxEY2IMw9hPpCRQ(Runnable r0, View r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$puxQK1w-OL9Nff1GJAcexXGGA-U, reason: not valid java name */
    public static /* synthetic */ void m21306$r8$lambda$puxQK1wOL9Nff1GJAcexXGGAU(Runnable r0, View r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public BotAdView(Context r23, Theme.ResourcesProvider r24) {
        super(r23);
        this.resourcesProvider = r24;
        LinearLayout r3 = new LinearLayout(r23);
        this.layout = r3;
        r3.setOrientation(0);
        r3.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(5.0f));
        ScaleStateListAnimator.apply(r3, 0.025f, 1.4f);
        addView(r3, LayoutHelper.createFrame(-1, -1, 119));
        int r5 = Theme.key_featuredStickers_addButton;
        setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(Theme.getColor(r5, r24), 0.1f), 0, 0));
        LinearLayout r7 = new LinearLayout(r23);
        r7.setOrientation(1);
        r3.addView(r7, LayoutHelper.createLinear(-1, -2, 1.0f, 3));
        LinearLayout r6 = new LinearLayout(r23);
        r6.setOrientation(0);
        r7.addView(r6, LayoutHelper.createLinear(-1, -2, 0.0f, 51, 0, 0, 0, 0));
        TextView r10 = new TextView(r23);
        this.titleView = r10;
        r10.setTextSize(1, 14.0f);
        int r13 = Theme.key_windowBackgroundWhiteBlackText;
        r10.setTextColor(Theme.getColor(r13, r24));
        r10.setTypeface(AndroidUtilities.bold());
        r6.addView(r10, LayoutHelper.createLinear(-2, -2, 0.0f, 16));
        NotificationCenter.listenEmojiLoading(r10);
        TextView r102 = new TextView(r23);
        this.removeView = r102;
        r102.setTextSize(1, 11.0f);
        r102.setTextColor(Theme.getColor(r5, r24));
        ScaleStateListAnimator.apply(r102, 0.1f, 1.5f);
        r102.setPadding(AndroidUtilities.dp(6.33f), 0, AndroidUtilities.dp(6.33f), 0);
        r102.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(9.0f), Theme.multAlpha(Theme.getColor(r5, r24), 0.1f)));
        r102.setText(LocaleController.getString(R.string.BotAdWhat));
        r6.addView(r102, LayoutHelper.createLinear(-2, 17, 0.0f, 19, 5, 1, 0, 0));
        TextView r4 = new TextView(r23);
        this.channelTitleView = r4;
        r4.setVisibility(8);
        r4.setTextSize(1, 14.0f);
        r4.setTextColor(Theme.getColor(r13, r24));
        r4.setTypeface(AndroidUtilities.bold());
        r7.addView(r4, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 2.0f));
        NotificationCenter.listenEmojiLoading(r4);
        LinkSpanDrawable.LinksTextView r42 = new LinkSpanDrawable.LinksTextView(r23);
        this.textView = r42;
        r42.setTextSize(1, 13.0f);
        r42.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r24));
        r42.setTextColor(Theme.getColor(r13, r24));
        r7.addView(r42, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        NotificationCenter.listenEmojiLoading(r42);
        BackupImageView r43 = new BackupImageView(r23);
        this.imageView = r43;
        r43.setRoundRadius(AndroidUtilities.dp(4.0f));
        r43.setVisibility(8);
        r3.addView(r43, LayoutHelper.createLinear(48, 48, 53, 10, 0, 2, 2));
        ImageView r44 = new ImageView(r23);
        this.closeView = r44;
        r44.setBackground(Theme.createSelectorDrawable(5, Theme.multAlpha(Theme.getColor(Theme.key_dialogEmptyImage, r24), 0.2f)));
        ScaleStateListAnimator.apply(r44);
        r44.setImageResource(R.drawable.msg_close);
        r44.setScaleType(ImageView.ScaleType.CENTER);
        r44.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_topPanelClose, r24), PorterDuff.Mode.SRC_IN));
        r44.setOnClickListener(new BotAdView$$ExternalSyntheticLambda4());
        r44.setVisibility(8);
        r3.addView(r44, LayoutHelper.createLinear(32, 32, 53, 10, 3, 0, 2));
    }

    private /* synthetic */ void lambda$set$1(ChatActivity r2, MessageObject r3, ClickableSpan r4) {
        if (r2 == null) goto L5;
        r2.logSponsoredClicked(r3, false, false);
    L5:
        if ((r4 instanceof URLSpan) == false) goto L17;
        String r32 = ((URLSpan) r4).getURL();
        if (r32 == null) goto L9;
        r32 = r32.trim();
    L9:
        if (r2 == null) goto L17;
        if (r32 == null) goto L17;
        if (r32.startsWith("$") == false) goto L14;
    L15:
        r2.openHashtagSearch(r32, true);
        return;
    L14:
        if (r32.startsWith("#") == true) goto L15;
    L17:
        r4.onClick(this.textView);
    }

    private /* synthetic */ void lambda$set$3(ChatActivity r11, MessageObject r12, String r13, View r14) {
        if (r11 == null) goto L4;
        r11.logSponsoredClicked(r12, false, false);
    L4:
        Browser.openUrl(getContext(), Uri.parse(r13), true, false, false, null, null, false, MessagesController.getInstance(UserConfig.selectedAccount).sponsoredLinksInappAllow, false);
    }

    public void set(ChatActivity r23, MessageObject r24, Runnable r25, Runnable r26) {
        if (r24 != null) goto L5;
        return;
    L5:
        CharSequence r3 = Emoji.replaceEmoji(r24.sponsoredTitle, this.titleView.getPaint().getFontMetricsInt(), false);
        CharSequence r4 = Emoji.replaceEmoji(r24.messageText, this.textView.getPaint().getFontMetricsInt(), false);
        String r6 = r24.sponsoredUrl;
        boolean r11 = true;
        if (r24.sponsoredMedia == null) goto L13;
        this.imageView.setVisibility(0);
        this.closeView.setVisibility(8);
        TLRPC.MessageMedia r7 = r24.sponsoredMedia;
        TLRPC.Document r12 = r7.document;
        if (r12 == null) goto L10;
        this.imageView.setImage(ImageLocation.getForDocument(r24.sponsoredMedia.document), "48_48", ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r12.thumbs, 48), r24.sponsoredMedia.document), "48_48", null, 0, 0, null);
    L17:
        SpannableStringBuilder r72 = new SpannableStringBuilder(LocaleController.getString(R.string.SponsoredMessageAd));
        int r10 = Theme.key_featuredStickers_addButton;
        r72.setSpan(new ForegroundColorSpan(Theme.getColor(r10, this.resourcesProvider)), 0, r72.length(), 33);
        r72.append(" \u2009");
        r72.append(r3);
        float r8 = this.titleView.getPaint().measureText(r72.toString());
        float r122 = ((AndroidUtilities.displaySize.x - AndroidUtilities.dp(44.660004f)) - this.removeView.getPaint().measureText(this.removeView.getText().toString())) - AndroidUtilities.dp(32.0f);
        if (r11 == false) goto L20;
        float r112 = 58.0f;
    L22:
        if (r8 <= (r122 - AndroidUtilities.dp(r112))) goto L24;
        r72 = new SpannableStringBuilder(LocaleController.getString(R.string.SponsoredMessageAd));
        r72.setSpan(new ForegroundColorSpan(Theme.getColor(r10, this.resourcesProvider)), 0, r72.length(), 33);
        this.channelTitleView.setVisibility(0);
        this.channelTitleView.setText(r3);
    L25:
        this.titleView.setText(r72);
        this.textView.setText(r4);
        this.textView.setOnLinkPressListener(new BotAdView$$ExternalSyntheticLambda0(this, r23, r24));
        this.removeView.setOnClickListener(new BotAdView$$ExternalSyntheticLambda1(r25));
        setOnClickListener(new BotAdView$$ExternalSyntheticLambda2(this, r23, r24, r6));
        this.closeView.setOnClickListener(new BotAdView$$ExternalSyntheticLambda3(r26));
        return;
    L24:
        this.channelTitleView.setVisibility(8);
        goto L25
    L20:
        r112 = 0.0f;
        goto L22
    L10:
        TLRPC.Photo r73 = r7.photo;
        if (r73 == null) goto L17;
        TLRPC.PhotoSize r74 = FileLoader.getClosestPhotoSizeWithSize(r73.sizes, 48, true, null, true);
        this.imageView.setImage(ImageLocation.getForPhoto(r74, r24.sponsoredMedia.photo), "48_48", ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r24.sponsoredMedia.photo.sizes, 48, true, r74, false), r24.sponsoredMedia.photo), "48_48", null, 0, 0, null);
        goto L17
    L13:
        TLRPC.Photo r75 = r24.sponsoredPhoto;
        if (r75 == null) goto L16;
        TLRPC.PhotoSize r76 = FileLoader.getClosestPhotoSizeWithSize(r75.sizes, 48, true, null, true);
        this.imageView.setImage(ImageLocation.getForPhoto(r76, r24.sponsoredPhoto), "48_48", ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r24.sponsoredPhoto.sizes, 48, true, r76, false), r24.sponsoredPhoto), "48_48", null, 0, 0, null);
        this.imageView.setVisibility(0);
        this.closeView.setVisibility(8);
        goto L17
    L16:
        this.imageView.setVisibility(8);
        this.closeView.setVisibility(0);
        r11 = false;
        goto L17
    }
}
