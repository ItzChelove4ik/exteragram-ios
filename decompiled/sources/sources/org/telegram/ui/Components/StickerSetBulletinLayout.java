package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.TextView;
import java.util.ArrayList;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class StickerSetBulletinLayout extends Bulletin.TwoLineLayout {
    /* renamed from: $r8$lambda$57QwHCV-IjZ3fyt7ykEQYpLyG_I, reason: not valid java name */
    public static /* synthetic */ void m13069$r8$lambda$57QwHCVIjZ3fyt7ykEQYpLyG_I(Context r2) {
        Activity r22 = AndroidUtilities.findActivity(r2);
        if ((r22 instanceof LaunchActivity) == false) goto L6;
        ((LaunchActivity) r22).presentFragment(new PremiumPreviewFragment(LimitReachedBottomSheet.limitTypeToServerString(9)));
        return;
    }

    public static /* synthetic */ void $r8$lambda$H3iEL8c72YVk4MXdEAWXkJoksi0(Context r2) {
        Activity r22 = AndroidUtilities.findActivity(r2);
        if ((r22 instanceof LaunchActivity) == false) goto L6;
        ((LaunchActivity) r22).presentFragment(new PremiumPreviewFragment(LimitReachedBottomSheet.limitTypeToServerString(10)));
        return;
    }

    public StickerSetBulletinLayout(Context r12, TLObject r13, int r14, int r15, TLRPC.Document r16, Theme.ResourcesProvider r17) {
        super(r12, r17);
        boolean r0 = r13 instanceof TLRPC.TL_messages_stickerSet;
        TLObject r1 = null;
        if (r0 == false) goto L12;
        TLRPC.TL_messages_stickerSet r2 = (TLRPC.TL_messages_stickerSet) r13;
        TLRPC.StickerSet r3 = r2.set;
        ArrayList<TLRPC.Document> r22 = r2.documents;
        if (r22 != null) goto L7;
    L9:
        TLRPC.Document r23 = r16;
    L10:
        TLRPC.Document r8 = r23;
    L35:
        if (r3 != null) goto L40;
        if (r8 == null) goto L40;
        TLRPC.TL_messages_stickerSet r24 = MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSet(MessageObject.getInputStickerSet(r8), true);
        if (r24 == null) goto L40;
        r3 = r24.set;
    L40:
        TLRPC.StickerSet r10 = r3;
        if (r8 != null) goto L43;
        this.imageView.setImage(null, null, "webp", null, r13);
    L74:
        if (MessageObject.isTextColorEmoji(r8) == false) goto L77;
        this.imageView.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L77:
        switch(r15) {
            case 0: goto L130;
            case 1: goto L119;
            case 2: goto L104;
            case 3: goto L102;
            case 4: goto L100;
            case 5: goto L98;
            case 6: goto L91;
            case 7: goto L79;
            default: goto L148;
        };
    L79:
        boolean r02 = UserConfig.getInstance(UserConfig.selectedAccount).isPremium();
        if (MessagesController.getInstance(UserConfig.selectedAccount).premiumFeaturesBlocked() == true) goto L84;
        if (r02 == true) goto L84;
        this.titleTextView.setText(LocaleController.formatString(R.string.LimitReachedFavoriteGifs, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).savedGifsLimitDefault)}));
        this.subtitleTextView.setText(AndroidUtilities.premiumText(LocaleController.formatString(R.string.LimitReachedFavoriteGifsSubtitle, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).savedGifsLimitPremium)}), new StickerSetBulletinLayout$$ExternalSyntheticLambda1(r12)));
        return;
    L84:
        TextView r122 = this.titleTextView;
        int r18 = R.string.LimitReachedFavoriteGifs;
        if (r02 == false) goto L87;
        int r03 = MessagesController.getInstance(UserConfig.selectedAccount).savedGifsLimitPremium;
    L88:
        r122.setText(LocaleController.formatString(r18, new Object[]{Integer.valueOf(r03)}));
        this.subtitleTextView.setText(LocaleController.getString(R.string.LimitReachedFavoriteGifsSubtitlePremium));
        return;
    L87:
        r03 = MessagesController.getInstance(UserConfig.selectedAccount).savedGifsLimitDefault;
        goto L88
    L98:
        this.titleTextView.setText(LocaleController.getString(R.string.AddedToFavorites));
        this.subtitleTextView.setVisibility(8);
        return;
    L100:
        this.titleTextView.setText(LocaleController.getString(R.string.RemovedFromFavorites));
        this.subtitleTextView.setVisibility(8);
        return;
    L102:
        this.titleTextView.setText(LocaleController.getString(R.string.RemovedFromRecent));
        this.subtitleTextView.setVisibility(8);
        return;
    L104:
        if (r10 != null) goto L106;
        return;
    L106:
        if (r10.masks == false) goto L109;
        this.titleTextView.setText(LocaleController.getString(R.string.AddMasksInstalled));
        this.subtitleTextView.setText(LocaleController.formatString("AddMasksInstalledInfo", R.string.AddMasksInstalledInfo, new Object[]{r10.title}));
        return;
    L109:
        boolean r123 = r10.emojis;
        TextView r04 = this.titleTextView;
        if (r123 == false) goto L117;
        r04.setText(LocaleController.getString(R.string.AddEmojiInstalled));
        TextView r11 = this.subtitleTextView;
        if (r14 <= 1) goto L115;
        r11.setText(LocaleController.formatPluralString("AddEmojiMultipleInstalledInfo", r14, new Object[0]));
        return;
    L115:
        r11.setText(LocaleController.formatString("AddEmojiInstalledInfo", R.string.AddEmojiInstalledInfo, new Object[]{r10.title}));
        return;
    L117:
        r04.setText(LocaleController.getString(R.string.AddStickersInstalled));
        this.subtitleTextView.setText(LocaleController.formatString("AddStickersInstalledInfo", R.string.AddStickersInstalledInfo, new Object[]{r10.title}));
        return;
    L119:
        if (r10 != null) goto L121;
        return;
    L121:
        if (r10.masks == false) goto L124;
        this.titleTextView.setText(LocaleController.getString(R.string.MasksArchived));
        this.subtitleTextView.setText(LocaleController.formatString("MasksArchivedInfo", R.string.MasksArchivedInfo, new Object[]{r10.title}));
        return;
    L124:
        boolean r124 = r10.emojis;
        TextView r05 = this.titleTextView;
        if (r124 == false) goto L128;
        r05.setText(LocaleController.getString(R.string.EmojiArchived));
        this.subtitleTextView.setText(LocaleController.formatString("EmojiArchivedInfo", R.string.EmojiArchivedInfo, new Object[]{r10.title}));
        return;
    L128:
        r05.setText(LocaleController.getString(R.string.StickersArchived));
        this.subtitleTextView.setText(LocaleController.formatString("StickersArchivedInfo", R.string.StickersArchivedInfo, new Object[]{r10.title}));
        return;
    L130:
        if (r10 != null) goto L132;
        return;
    L132:
        if (r10.masks == false) goto L135;
        this.titleTextView.setText(LocaleController.getString(R.string.MasksRemoved));
        this.subtitleTextView.setText(LocaleController.formatString("MasksRemovedInfo", R.string.MasksRemovedInfo, new Object[]{r10.title}));
        return;
    L135:
        boolean r125 = r10.emojis;
        TextView r06 = this.titleTextView;
        if (r125 == false) goto L143;
        r06.setText(LocaleController.getString(R.string.EmojiRemoved));
        TextView r112 = this.subtitleTextView;
        if (r14 <= 1) goto L141;
        r112.setText(LocaleController.formatPluralString("EmojiRemovedMultipleInfo", r14, new Object[0]));
        return;
    L141:
        r112.setText(LocaleController.formatString("EmojiRemovedInfo", R.string.EmojiRemovedInfo, new Object[]{r10.title}));
        return;
    L143:
        r06.setText(LocaleController.getString(R.string.StickersRemoved));
        this.subtitleTextView.setText(LocaleController.formatString("StickersRemovedInfo", R.string.StickersRemovedInfo, new Object[]{r10.title}));
        return;
    L148:
        return;
    L91:
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L93;
    L96:
        this.titleTextView.setText(LocaleController.formatString("LimitReachedFavoriteStickers", R.string.LimitReachedFavoriteStickers, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).stickersFavedLimitPremium)}));
        this.subtitleTextView.setText(LocaleController.formatString("LimitReachedFavoriteStickersSubtitlePremium", R.string.LimitReachedFavoriteStickersSubtitlePremium, new Object[0]));
        return;
    L93:
        if (MessagesController.getInstance(UserConfig.selectedAccount).premiumFeaturesBlocked() == true) goto L96;
        this.titleTextView.setText(LocaleController.formatString("LimitReachedFavoriteStickers", R.string.LimitReachedFavoriteStickers, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).stickersFavedLimitDefault)}));
        this.subtitleTextView.setText(AndroidUtilities.premiumText(LocaleController.formatString("LimitReachedFavoriteStickersSubtitle", R.string.LimitReachedFavoriteStickersSubtitle, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).stickersFavedLimitPremium)}), new StickerSetBulletinLayout$$ExternalSyntheticLambda0(r12)));
        return;
    L43:
        if (r10 == null) goto L46;
        r1 = FileLoader.getClosestPhotoSizeWithSize(r10.thumbs, 90);
    L46:
        if (r1 != null) goto L48;
        r1 = r8;
    L48:
        boolean r32 = r1 instanceof TLRPC.Document;
        if (r32 == false) goto L52;
        ImageLocation r07 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r8.thumbs, 90), r8);
    L51:
        ImageLocation r19 = r07;
        if (r32 == true) goto L61;
    L67:
        if (r19 != null) goto L69;
    L71:
        this.imageView.setImage(r19, "50_50", "webp", null, r13);
        goto L74
    L69:
        if (r19.imageType != 1) goto L71;
        this.imageView.setImage(r19, "50_50", "tgs", null, r13);
        goto L74
    L61:
        if (MessageObject.isAnimatedStickerDocument(r8, true) == false) goto L63;
    L66:
        this.imageView.setImage(ImageLocation.getForDocument(r8), "50_50", r19, null, 0, r13);
        goto L74
    L63:
        if (MessageObject.isVideoSticker(r8) == true) goto L66;
        if (MessageObject.isGifDocument(r8) == false) goto L67;
    L52:
        TLRPC.PhotoSize r110 = (TLRPC.PhotoSize) r1;
        if ((r13 instanceof TLRPC.StickerSetCovered) == false) goto L55;
        int r08 = ((TLRPC.StickerSetCovered) r13).set.thumb_version;
    L58:
        r07 = ImageLocation.getForSticker(r110, r8, r08);
        goto L51
    L55:
        if (r0 == false) goto L57;
        r08 = ((TLRPC.TL_messages_stickerSet) r13).set.thumb_version;
        goto L58
    L57:
        r08 = 0;
        goto L58
    L7:
        if (r22.isEmpty() == true) goto L9;
        r23 = r22.get(0);
        goto L10
    L12:
        if ((r13 instanceof TLRPC.StickerSetCovered) == false) goto L26;
        TLRPC.StickerSetCovered r25 = (TLRPC.StickerSetCovered) r13;
        r3 = r25.set;
        TLRPC.Document r4 = r25.cover;
        if (r4 == null) goto L17;
        r23 = r4;
        goto L10
    L17:
        if (r25.covers.isEmpty() == true) goto L20;
        r23 = r25.covers.get(0);
        goto L10
    L20:
        if ((r25 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L9;
        TLRPC.TL_stickerSetFullCovered r26 = (TLRPC.TL_stickerSetFullCovered) r25;
        ArrayList<TLRPC.Document> r42 = r26.documents;
        if (r42 == null) goto L9;
        if (r42.isEmpty() == true) goto L9;
        r23 = r26.documents.get(0);
        goto L10
    L26:
        if (r16 != null) goto L33;
        if (r13 == null) goto L33;
        if (BuildVars.DEBUG_VERSION == false) goto L33;
        Buffer$$ExternalSyntheticBUOutline4.m("Invalid type of the given setObject: ", r13.getClass());
        throw null;
    L33:
        r8 = r16;
        r3 = null;
        goto L35
    }

    public StickerSetBulletinLayout(Context r8, TLObject r9, int r10, TLRPC.Document r11, Theme.ResourcesProvider r12) {
        this(r8, r9, 1, r10, r11, r12);
    }
}
