package org.telegram.ui.Components;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PeerColorActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes3.dex */
public final class BulletinFactory {
    private final FrameLayout containerLayout;
    private final BaseFragment fragment;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.Components.BulletinFactory$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ BulletinFactory this$0;
        final /* synthetic */ Runnable val$callback;

        public AnonymousClass1(BulletinFactory r1, Runnable r2) {
            this.this$0 = r1;
            this.val$callback = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            this.val$callback.run();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.Components.BulletinFactory$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends ClickableSpan {
        final /* synthetic */ BulletinFactory this$0;

        public AnonymousClass2(BulletinFactory r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r2) {
            BulletinFactory.m9090$$Nest$fgetfragment(this.this$0).presentFragment(new PremiumPreviewFragment("settings"));
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    /* loaded from: classes7.dex */
    public enum FileType extends Enum<FileType> {
        private static final /* synthetic */ FileType[] $VALUES = null;
        public static final FileType AUDIO = null;
        public static final FileType AUDIOS = null;
        public static final FileType EMOJI = null;
        public static final FileType GIF = null;
        public static final FileType GIF_TO_DOWNLOADS = null;
        public static final FileType GIF_TO_GALLERY = null;
        public static final FileType LIVEPHOTO = null;
        public static final FileType LIVEPHOTOS = null;
        public static final FileType MEDIA = null;
        public static final FileType PHOTO = null;
        public static final FileType PHOTOS = null;
        public static final FileType PHOTO_TO_DOWNLOADS = null;
        public static final FileType STICKER = null;
        public static final FileType UNKNOWN = null;
        public static final FileType UNKNOWNS = null;
        public static final FileType VIDEO = null;
        public static final FileType VIDEOS = null;
        public static final FileType VIDEO_TO_DOWNLOADS = null;
        private final Icon icon;
        private final String localeKey;
        private final int localeRes;
        private final boolean plural;

        public enum Icon extends Enum<Icon> {
            private static final /* synthetic */ Icon[] $VALUES = null;
            public static final Icon SAVED_TO_DOWNLOADS = null;
            public static final Icon SAVED_TO_GALLERY = null;
            public static final Icon SAVED_TO_GIFS = null;
            public static final Icon SAVED_TO_MUSIC = null;
            private final String[] layers;
            private final int paddingBottom;
            private final int resId;

            private static /* synthetic */ Icon[] $values() {
                return new Icon[]{SAVED_TO_DOWNLOADS, SAVED_TO_GALLERY, SAVED_TO_MUSIC, SAVED_TO_GIFS};
            }

            /* renamed from: -$$Nest$fgetlayers, reason: not valid java name */
            public static /* bridge */ /* synthetic */ String[] m9093$$Nest$fgetlayers(Icon r0) {
                return r0.layers;
            }

            /* renamed from: -$$Nest$fgetpaddingBottom, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m9094$$Nest$fgetpaddingBottom(Icon r0) {
                return r0.paddingBottom;
            }

            /* renamed from: -$$Nest$fgetresId, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m9095$$Nest$fgetresId(Icon r0) {
                return r0.resId;
            }

            static {
                SAVED_TO_DOWNLOADS = new Icon("SAVED_TO_DOWNLOADS", 0, R.raw.ic_download, 2, new String[]{"Box", "Arrow"});
                SAVED_TO_GALLERY = new Icon("SAVED_TO_GALLERY", 1, R.raw.ic_save_to_gallery, 0, new String[]{"Box", "Arrow", "Mask", "Arrow 2", "Splash"});
                SAVED_TO_MUSIC = new Icon("SAVED_TO_MUSIC", 2, R.raw.ic_save_to_music, 2, new String[]{"Box", "Arrow"});
                SAVED_TO_GIFS = new Icon("SAVED_TO_GIFS", 3, R.raw.ic_save_to_gifs, 0, new String[]{"gif"});
                $VALUES = $values();
            }

            Icon(String r1, int r2, int r3, int r4, String... r5) {
                this.resId = r3;
                this.paddingBottom = r4;
                this.layers = r5;
            }

            public static Icon valueOf(String r1) {
                return (Icon) Enum.valueOf(Icon.class, r1);
            }

            public static Icon[] values() {
                return (Icon[]) $VALUES.clone();
            }
        }

        private static /* synthetic */ FileType[] $values() {
            return new FileType[]{STICKER, EMOJI, PHOTO, PHOTOS, VIDEO, VIDEOS, GIF_TO_GALLERY, LIVEPHOTO, LIVEPHOTOS, MEDIA, PHOTO_TO_DOWNLOADS, VIDEO_TO_DOWNLOADS, GIF, GIF_TO_DOWNLOADS, AUDIO, AUDIOS, UNKNOWN, UNKNOWNS};
        }

        /* renamed from: -$$Nest$fgeticon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Icon m9091$$Nest$fgeticon(FileType r0) {
            return r0.icon;
        }

        /* renamed from: -$$Nest$mgetText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m9092$$Nest$mgetText(FileType r0, int r1) {
            return r0.getText(r1);
        }

        static {
            int r4 = R.string.StickerSavedHint;
            Icon r10 = Icon.SAVED_TO_GALLERY;
            STICKER = new FileType("STICKER", 0, "StickerSavedHint", r4, r10);
            EMOJI = new FileType("EMOJI", 1, "EmojiSavedHint", R.string.EmojiSavedHint, r10);
            PHOTO = new FileType("PHOTO", 2, "PhotoSavedHint", R.string.PhotoSavedHint, r10);
            PHOTOS = new FileType("PHOTOS", 3, "PhotosSavedHint", r10);
            VIDEO = new FileType("VIDEO", 4, "VideoSavedHint", R.string.VideoSavedHint, r10);
            VIDEOS = new FileType("VIDEOS", 5, "VideosSavedHint", r10);
            GIF_TO_GALLERY = new FileType("GIF_TO_GALLERY", 6, "GifGallerySavedHint", R.string.GifGallerySavedHint, r10);
            LIVEPHOTO = new FileType("LIVEPHOTO", 7, "LivePhotoSavedHint", R.string.LivePhotoSavedHint, r10);
            LIVEPHOTOS = new FileType("LIVEPHOTOS", 8, "LivePhotosSavedHint", r10);
            MEDIA = new FileType("MEDIA", 9, "MediaSavedHint", r10);
            int r8 = R.string.PhotoSavedToDownloadsHintLinked;
            Icon r14 = Icon.SAVED_TO_DOWNLOADS;
            PHOTO_TO_DOWNLOADS = new FileType("PHOTO_TO_DOWNLOADS", 10, "PhotoSavedToDownloadsHintLinked", r8, r14);
            VIDEO_TO_DOWNLOADS = new FileType("VIDEO_TO_DOWNLOADS", 11, "VideoSavedToDownloadsHintLinked", R.string.VideoSavedToDownloadsHintLinked, r14);
            GIF = new FileType("GIF", 12, "GifSavedHint", R.string.GifSavedHint, Icon.SAVED_TO_GIFS);
            GIF_TO_DOWNLOADS = new FileType("GIF_TO_DOWNLOADS", 13, "GifSavedToDownloadsHintLinked", R.string.GifSavedToDownloadsHintLinked, r14);
            int r42 = R.string.AudioSavedHint;
            Icon r5 = Icon.SAVED_TO_MUSIC;
            AUDIO = new FileType("AUDIO", 14, "AudioSavedHint", r42, r5);
            AUDIOS = new FileType("AUDIOS", 15, "AudiosSavedHint", r5);
            UNKNOWN = new FileType("UNKNOWN", 16, "FileSavedHintLinked", R.string.FileSavedHintLinked, r14);
            UNKNOWNS = new FileType("UNKNOWNS", 17, "FilesSavedHintLinked", r14);
            $VALUES = $values();
        }

        FileType(String r1, int r2, String r3, int r4, Icon r5) {
            this.localeKey = r3;
            this.localeRes = r4;
            this.icon = r5;
            this.plural = false;
        }

        FileType(String r1, int r2, String r3, Icon r4) {
            this.localeKey = r3;
            this.icon = r4;
            this.localeRes = 0;
            this.plural = true;
        }

        private String getText(int r3) {
            boolean r0 = this.plural;
            String r1 = this.localeKey;
            if (r0 == false) goto L7;
            return LocaleController.formatPluralString(r1, r3, new Object[0]);
        L7:
            return LocaleController.getString(r1, this.localeRes);
        }

        public static FileType valueOf(String r1) {
            return (FileType) Enum.valueOf(FileType.class, r1);
        }

        public static FileType[] values() {
            return (FileType[]) $VALUES.clone();
        }
    }

    /* loaded from: classes7.dex */
    public static class UndoObject {
        public Runnable onAction;
        public Runnable onUndo;
        public CharSequence undoText;

        public UndoObject() {
        }
    }

    public static /* synthetic */ void $r8$lambda$4nMiwG2pwddTbafjYrNEajoYRFs(Utilities.Callback r0, TLRPC.InputStickerSet r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$8J3OiIFslIyIXkHdLTVDv63vF-s, reason: not valid java name */
    public static /* synthetic */ void m9089$r8$lambda$8J3OiIFslIyIXkHdLTVDv63vFs() {
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isFinishing() == true) goto L10;
        Intent r02 = new Intent("android.intent.action.VIEW_DOWNLOADS");
        r02.setFlags(268468224);
        LaunchActivity.instance.startActivity(r02);
        return;
    }

    public static /* synthetic */ void $r8$lambda$bNvrD_IXAgHTRtL8BQwlhaZLiQI(Bulletin r0, CharSequence r1) {
        r0.onLoaded(r1);
    }

    public static /* synthetic */ void $r8$lambda$cmdwCWTjWScaKVp1N2mPSyjQJGc(boolean[] r2, Runnable r3) {
        if (r2[0] == true) goto L6;
        r2[0] = true;
        r3.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$efZiD0xXuti1KR9p63wAu8mw3Qo(Utilities.Callback r0, TLRPC.InputStickerSet r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$fjFmkJXXjN4xDfr4GJX14k_w6Wg(Bulletin.LottieLayout r2) {
        r2.performHapticFeedback(3, 2);
    }

    public static /* synthetic */ void $r8$lambda$o9EtOcsiEQq5tWAmMCGKcHX1oHw(Bulletin.LottieLayout r2) {
        r2.performHapticFeedback(3, 2);
    }

    public static /* synthetic */ void $r8$lambda$rGqRMmPy33W71zUl4SsdlueKl8w(Runnable r0, BaseFragment r1, long r2) {
        if (r0 == null) goto L4;
        r0.run();
    L4:
        if (r1 == null) goto L7;
        r1.presentFragment(ChatActivity.of(r2));
        return;
    }

    public static /* synthetic */ void $r8$lambda$yygjfMhXwGKIBOss6ktGnM1K6ow(int r1, Bulletin r2, long r3, TLRPC.TL_messages_stickerSet r5) {
        if (r5 == null) goto L12;
        TLRPC.StickerSet r52 = r5.set;
        if (r52 == null) goto L12;
        if (r1 != 1) goto L8;
        CharSequence r12 = AndroidUtilities.replaceTags(LocaleController.formatString("TopicContainsEmojiPackSingle", R.string.TopicContainsEmojiPackSingle, new Object[]{r52.title}));
    L13:
        AndroidUtilities.runOnUIThread(new BulletinFactory$$ExternalSyntheticLambda4(r2, r12), Math.max(1, 750 - (System.currentTimeMillis() - r3)));
        return;
    L8:
        String r53 = r52.title;
        if (r1 != 2) goto L11;
        r12 = AndroidUtilities.replaceTags(LocaleController.formatString("StoryContainsEmojiPackSingle", R.string.StoryContainsEmojiPackSingle, new Object[]{r53}));
        goto L13
    L11:
        r12 = AndroidUtilities.replaceTags(LocaleController.formatString("MessageContainsEmojiPackSingle", R.string.MessageContainsEmojiPackSingle, new Object[]{r53}));
    L12:
        r12 = LocaleController.getString(R.string.AddEmojiNotFound);
        goto L13
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m9090$$Nest$fgetfragment(BulletinFactory r0) {
        return r0.fragment;
    }

    private BulletinFactory(FrameLayout r1, Theme.ResourcesProvider r2) {
        this.containerLayout = r1;
        this.fragment = null;
        this.resourcesProvider = r2;
    }

    private BulletinFactory(BaseFragment r3) {
        Theme.ResourcesProvider r0 = null;
        if (r3 != null) goto L5;
    L10:
        this.fragment = r3;
        this.containerLayout = null;
        if (r3 == null) goto L14;
        r0 = r3.getResourceProvider();
    L14:
        this.resourcesProvider = r0;
        return;
    L5:
        if (r3.getLastStoryViewer() == null) goto L10;
        if (r3.getLastStoryViewer().attachedToParent() == false) goto L10;
        this.fragment = null;
        this.containerLayout = r3.getLastStoryViewer().getContainerForBulletin();
        this.resourcesProvider = r3.getLastStoryViewer().getResourceProvider();
    }

    public static boolean canShowBulletin(BaseFragment r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r1.getLayoutContainer() == null) goto L12;
        return true;
    L12:
        return false;
    }

    public static Bulletin createAddedAsAdminBulletin(BaseFragment r4, String r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r4.getParentActivity(), r4.getResourceProvider());
        r0.setAnimation(R.raw.ic_admin, new String[]{"Shield"});
        r0.textView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("UserAddedAsAdminHint", R.string.UserAddedAsAdminHint, new Object[]{r5})));
        return Bulletin.make(r4, r0, 1500);
    }

    public static Bulletin createBanBulletin(BaseFragment r6, boolean r7) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r6.getParentActivity(), r6.getResourceProvider());
        if (r7 == false) goto L5;
        r0.setAnimation(R.raw.ic_ban, new String[]{"Hand"});
        String r72 = LocaleController.getString(R.string.UserBlocked);
    L6:
        r0.textView.setText(AndroidUtilities.replaceTags(r72));
        return Bulletin.make(r6, r0, 1500);
    L5:
        r0.setAnimation(R.raw.ic_unban, new String[]{"Main", "Finger 1", "Finger 2", "Finger 3", "Finger 4"});
        r72 = LocaleController.getString(R.string.UserUnblocked);
        goto L6
    }

    public static Bulletin createCopyLinkBulletin(FrameLayout r1) {
        return of(r1, null).createCopyLinkBulletin();
    }

    public static Bulletin createCopyLinkBulletin(BaseFragment r0) {
        return of(r0).createCopyLinkBulletin();
    }

    public static Bulletin createDissableSharingBulletin(BaseFragment r3, String r4, boolean r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r3.getParentActivity(), r3.getResourceProvider());
        if (r4 == null) goto L8;
        if (r5 == false) goto L6;
        int r1 = R.string.DisableSharingToastDisabledPending;
    L7:
        String r12 = LocaleController.formatString(r1, new Object[]{r4});
    L12:
        r0.textView.setText(AndroidUtilities.replaceTags(r12));
        if (r5 == true) goto L17;
        if (r4 != null) goto L17;
        int r42 = R.raw.contact_check;
    L18:
        r0.setAnimation(r42, new String[0]);
        return Bulletin.make(r3, r0, 5000);
    L17:
        r42 = R.raw.e_hand_2;
        goto L18
    L6:
        r1 = R.string.DisableSharingToastEnabledPending;
        goto L7
    L8:
        if (r5 == false) goto L10;
        int r13 = R.string.DisableSharingToastDisabled;
    L11:
        r12 = LocaleController.getString(r13);
        goto L12
    L10:
        r13 = R.string.DisableSharingToastEnabled;
        goto L11
    }

    public static Bulletin createForwardedBulletin(Context r12, BaseFragment r13, FrameLayout r14, int r15, long r16, int r18, int r19, int r20, int r21) {
        return createForwardedBulletin(r12, r13, r14, r15, r16, r18, r19, r20, r21, null, null);
    }

    public static Bulletin createForwardedBulletin(Context r13, BaseFragment r14, FrameLayout r15, int r16, long r17, int r19, int r20, int r21, int r22, Runnable r23, Runnable r24) {
        return createForwardedBulletin(r13, r14, r15, r16, r17, r19, r20, r21, r22, false, r23, r24);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [org.telegram.ui.Components.Bulletin] */
    /* JADX WARN: Type inference failed for: r17v12 */
    /* JADX WARN: Type inference failed for: r17v5 */
    public static Bulletin createForwardedBulletin(Context r18, BaseFragment r19, FrameLayout r20, int r21, long r22, int r24, int r25, int r26, int r27, boolean r28, Runnable r29, Runnable r30) {
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L11;
        if (r19 == null) goto L11;
        if (r21 > 1) goto L11;
        if (r22 != UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L11;
        if (r28 == true) goto L11;
        Bulletin.LottieLayout r9 = new Bulletin.LottieLayoutWithReactions(r19, r24);
    L17:
        if (r30 != null) goto L21;
        if (r29 != null) goto L21;
        boolean r13 = false;
    L22:
        boolean[] r14 = {false};
        if (r30 == null) goto L25;
        BulletinFactory$$ExternalSyntheticLambda6 r15 = new BulletinFactory$$ExternalSyntheticLambda6(r14, r30);
    L27:
        if (r21 > 1) goto L63;
        Bulletin.LottieLayout r182 = r9;
        if (r22 != UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L38;
        if (r24 > 1) goto L36;
        String r2 = LocaleController.getString(R.string.FwdMessageToSavedMessages);
        if (r28 == false) goto L34;
        Runnable r3 = new BulletinFactory$$ExternalSyntheticLambda7();
    L35:
        SpannableStringBuilder r23 = AndroidUtilities.replaceSingleTag(r2, -1, 2, r3);
    L37:
        r9 = r182;
        r9.setAnimation(R.raw.saved_messages, 30, 30, new String[0]);
        ?? r17 = 0;
    L68:
        r9.textView.setText(r23);
        if (r13 == false) goto L75;
        Context r32 = r9.getContext();
        if (r19 == null) goto L73;
        Theme.ResourcesProvider r4 = r19.getResourceProvider();
    L74:
        r9.setButton(new Bulletin.UndoButton(r32, true, true, r4).setUndoAction(r29).setDelayedAction(r15));
        goto L75
    L73:
        r4 = r17;
    L75:
        r9.postDelayed(new BulletinFactory$$ExternalSyntheticLambda9(r9), 300);
        if (r20 == null) goto L78;
        Bulletin r0 = Bulletin.make(r20, r9, r27);
    L81:
        if ((r9 instanceof Bulletin.LottieLayoutWithReactions) == false) goto L83;
        r9.textView.setSingleLine(false);
        r9.textView.setMaxLines(2);
        ((Bulletin.LottieLayoutWithReactions) r9).setBulletin(r0);
        r0.hideAfterBottomSheet(false);
    L83:
        return r0;
    L78:
        if (r19 == null) goto L84;
        r0 = Bulletin.make(r19, r9, r27);
        goto L81
    L84:
        Segment$$ExternalSyntheticBUOutline0.m();
        return r17;
    L34:
        r3 = new BulletinFactory$$ExternalSyntheticLambda5();
        goto L35
    L36:
        r23 = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.FwdMessagesToSavedMessages), -1, 2, new BulletinFactory$$ExternalSyntheticLambda5());
        goto L37
    L38:
        r9 = r182;
        BulletinFactory$$ExternalSyntheticLambda8 r210 = new BulletinFactory$$ExternalSyntheticLambda8(r15, r19, r22);
        if (DialogObject.isChatDialog(r22) == false) goto L48;
        boolean r172 = false;
        r172 = false;
        r172 = false;
        r172 = false;
        TLRPC.Chat r33 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r22));
        if (r24 > 1) goto L45;
        if (r19 == null) goto L44;
        r23 = AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.FwdMessageToGroup, new Object[]{r33.title}), -1, 2, r210);
    L62:
        r9.setAnimation(R.raw.forward, 30, 30, new String[0]);
        r17 = r172;
        goto L68
    L44:
        r23 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.FwdMessageToGroup, new Object[]{r33.title}));
        goto L62
    L45:
        if (r19 == null) goto L47;
        r23 = AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.FwdMessagesToGroup, new Object[]{r33.title}), -1, 2, r210);
        goto L62
    L47:
        r23 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.FwdMessagesToGroup, new Object[]{r33.title}));
        goto L62
    L48:
        r172 = false;
        r172 = false;
        r172 = false;
        r172 = false;
        TLRPC.User r34 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r22));
        if (r24 > 1) goto L56;
        if (r13 == false) goto L52;
        int r42 = R.string.FwdMessageToUserShort;
    L53:
        if (r19 == null) goto L55;
        r23 = AndroidUtilities.replaceSingleTag(LocaleController.formatString(r42, new Object[]{UserObject.getFirstName(r34)}), -1, 2, r210);
        goto L62
    L55:
        r23 = AndroidUtilities.replaceTags(LocaleController.formatString(r42, new Object[]{UserObject.getFirstName(r34)}));
        goto L62
    L52:
        r42 = R.string.FwdMessageToUser;
        goto L53
    L56:
        if (r13 == false) goto L58;
        int r43 = R.string.FwdMessagesToUserShort;
    L59:
        if (r19 == null) goto L61;
        r23 = AndroidUtilities.replaceSingleTag(LocaleController.formatString(r43, new Object[]{UserObject.getFirstName(r34)}), -1, 2, r210);
        goto L62
    L61:
        r23 = AndroidUtilities.replaceTags(LocaleController.formatString(r43, new Object[]{UserObject.getFirstName(r34)}));
        goto L62
    L58:
        r43 = R.string.FwdMessagesToUser;
        goto L59
    L63:
        r17 = 0;
        if (r24 > 1) goto L66;
        r23 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("FwdMessageToManyChats", r21, new Object[0]));
    L67:
        r9.setAnimation(R.raw.forward, 30, 30, new String[0]);
        goto L68
    L66:
        r23 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("FwdMessagesToManyChats", r21, new Object[0]));
        goto L67
    L25:
        r15 = null;
    L21:
        r13 = true;
    L11:
        if (r19 == null) goto L14;
        Theme.ResourcesProvider r12 = r19.getResourceProvider();
    L15:
        r9 = new Bulletin.LottieLayout(r18, r12, r25, r26);
        goto L17
    L14:
        r12 = null;
        goto L15
    }

    public static Bulletin createInviteSentBulletin(Context r3, FrameLayout r4, int r5, long r6, int r8, int r9, int r10) {
        Bulletin.LottieLayout r82 = new Bulletin.LottieLayout(r3, null, r9, r10);
        int r92 = 300;
        if (r5 <= 1) goto L5;
        SpannableStringBuilder r32 = AndroidUtilities.replaceTags(LocaleController.formatString("InvLinkToChats", R.string.InvLinkToChats, new Object[]{LocaleController.formatPluralString("Chats", r5, new Object[0])}));
        r82.setAnimation(R.raw.forward, 30, 30, new String[0]);
    L13:
        r82.textView.setText(r32);
        if (r92 <= 0) goto L17;
        r82.postDelayed(new BulletinFactory$$ExternalSyntheticLambda10(r82), r92);
    L17:
        return Bulletin.make(r4, r82, 1500);
    L5:
        if (r6 != UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L8;
        r32 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.InvLinkToSavedMessages));
        r82.setAnimation(R.raw.saved_messages, 30, 30, new String[0]);
        r92 = -1;
        goto L13
    L8:
        if (DialogObject.isChatDialog(r6) == false) goto L10;
        r32 = AndroidUtilities.replaceTags(LocaleController.formatString("InvLinkToGroup", R.string.InvLinkToGroup, new Object[]{MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r6)).title}));
    L11:
        r82.setAnimation(R.raw.forward, 30, 30, new String[0]);
        goto L13
    L10:
        r32 = AndroidUtilities.replaceTags(LocaleController.formatString("InvLinkToUser", R.string.InvLinkToUser, new Object[]{UserObject.getFirstName(MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r6)))}));
        goto L11
    }

    public static Bulletin createMuteBulletin(BaseFragment r2, int r3) {
        return createMuteBulletin(r2, r3, 0, null);
    }

    public static Bulletin createMuteBulletin(BaseFragment r5, int r6, int r7, Theme.ResourcesProvider r8) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r5.getParentActivity(), r8);
        boolean r2 = true;
        if (r6 == 0) goto L21;
        if (r6 != 1) goto L6;
        String r62 = LocaleController.formatString("NotificationsMutedForHint", R.string.NotificationsMutedForHint, new Object[]{LocaleController.formatPluralString("Hours", 8, new Object[0])});
    L18:
        boolean r72 = true;
        r2 = false;
    L22:
        if (r2 == false) goto L24;
        r0.setAnimation(R.raw.mute_for, new String[0]);
    L27:
        r0.textView.setText(r62);
        return Bulletin.make(r5, r0, 1500);
    L24:
        if (r72 == false) goto L26;
        r0.setAnimation(R.raw.ic_mute, new String[]{"Body Main", "Body Top", "Line", "Curve Big", "Curve Small"});
        goto L27
    L26:
        r0.setAnimation(R.raw.ic_unmute, new String[]{"BODY", "Wibe Big", "Wibe Big 3", "Wibe Small"});
        goto L27
    L6:
        if (r6 != 2) goto L8;
        r62 = LocaleController.formatString("NotificationsMutedForHint", R.string.NotificationsMutedForHint, new Object[]{LocaleController.formatPluralString("Days", 2, new Object[0])});
        goto L18
    L8:
        if (r6 != 3) goto L10;
        r62 = LocaleController.getString(R.string.NotificationsMutedHint);
        goto L18
    L10:
        if (r6 != 4) goto L12;
        r62 = LocaleController.getString(R.string.NotificationsUnmutedHint);
        r72 = false;
        r2 = false;
        goto L22
    L12:
        if (r6 != 5) goto L14;
        r62 = LocaleController.formatString("NotificationsMutedForHint", R.string.NotificationsMutedForHint, new Object[]{LocaleController.formatTTLString(r7)});
        r72 = true;
        goto L22
    L14:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L21:
        r62 = LocaleController.formatString("NotificationsMutedForHint", R.string.NotificationsMutedForHint, new Object[]{LocaleController.formatPluralString("Hours", 1, new Object[0])});
        goto L18
    }

    public static Bulletin createMuteBulletin(BaseFragment r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r4.getParentActivity(), r7);
        TextView r72 = r0.textView;
        if (r5 == false) goto L5;
        String r62 = LocaleController.formatPluralString("NotificationsMutedHintChats", r6, new Object[0]);
    L6:
        r72.setText(r62);
        if (r5 == false) goto L9;
        r0.setAnimation(R.raw.ic_mute, new String[]{"Body Main", "Body Top", "Line", "Curve Big", "Curve Small"});
    L11:
        return Bulletin.make(r4, r0, 1500);
    L9:
        r0.setAnimation(R.raw.ic_unmute, new String[]{"BODY", "Wibe Big", "Wibe Big 3", "Wibe Small"});
        goto L11
    L5:
        r62 = LocaleController.formatPluralString("NotificationsUnmutedHintChats", r6, new Object[0]);
        goto L6
    }

    public static Bulletin createMuteBulletin(BaseFragment r1, boolean r2, Theme.ResourcesProvider r3) {
        if (r2 == false) goto L4;
        int r22 = 3;
    L6:
        return createMuteBulletin(r1, r22, 0, r3);
    L4:
        r22 = 4;
        goto L6
    }

    public static Bulletin createPinMessageBulletin(BaseFragment r2, Theme.ResourcesProvider r3) {
        return createPinMessageBulletin(r2, true, null, null, r3);
    }

    private static Bulletin createPinMessageBulletin(BaseFragment r4, boolean r5, Runnable r6, Runnable r7, Theme.ResourcesProvider r8) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r4.getParentActivity(), r8);
        if (r5 == false) goto L5;
        int r1 = R.raw.ic_pin;
    L6:
        r0.setAnimation(r1, 28, 28, new String[]{"Pin", "Line"});
        TextView r12 = r0.textView;
        if (r5 == false) goto L9;
        String r2 = "MessagePinnedHint";
    L10:
        if (r5 == false) goto L12;
        int r3 = R.string.MessagePinnedHint;
    L13:
        r12.setText(LocaleController.getString(r2, r3));
        if (r5 == true) goto L16;
        r0.setButton(new Bulletin.UndoButton(r4.getParentActivity(), true, r8).setUndoAction(r6).setDelayedAction(r7));
    L16:
        if (r5 == false) goto L18;
        int r52 = 1500;
    L20:
        return Bulletin.make(r4, r0, r52);
    L18:
        r52 = 5000;
        goto L20
    L12:
        r3 = R.string.MessageUnpinnedHint;
        goto L13
    L9:
        r2 = "MessageUnpinnedHint";
        goto L10
    L5:
        r1 = R.raw.ic_unpin;
        goto L6
    }

    public static Bulletin createPromoteToAdminBulletin(BaseFragment r4, String r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r4.getParentActivity(), r4.getResourceProvider());
        r0.setAnimation(R.raw.ic_admin, new String[]{"Shield"});
        r0.textView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("UserSetAsAdminHint", R.string.UserSetAsAdminHint, new Object[]{r5})));
        return Bulletin.make(r4, r0, 1500);
    }

    public static Bulletin createRemoveFromChatBulletin(BaseFragment r3, TLRPC.User r4, String r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r3.getParentActivity(), r3.getResourceProvider());
        r0.setAnimation(R.raw.ic_ban, new String[]{"Hand"});
        if (r4.deleted == false) goto L5;
        String r42 = LocaleController.formatString("HiddenName", R.string.HiddenName, new Object[0]);
    L6:
        r0.textView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("UserRemovedFromChatHint", R.string.UserRemovedFromChatHint, new Object[]{r42, r5})));
        return Bulletin.make(r3, r0, 1500);
    L5:
        r42 = r4.first_name;
        goto L6
    }

    public static Bulletin createSaveMediaToGalleryBulletin(FrameLayout r1, int r2, boolean r3, boolean r4, boolean r5, int r6, int r7) {
        int r32 = r3 ? 1 : 0;
        if ((((r4 ? 1 : 0) + r32) + (r5 ? 1 : 0)) <= 1) goto L5;
        FileType r33 = FileType.MEDIA;
    L17:
        return of(r1, null).createDownloadBulletin(r33, r2, r6, r7);
    L5:
        if (r5 == false) goto L9;
        if (r2 <= 1) goto L8;
        r33 = FileType.LIVEPHOTOS;
        goto L17
    L8:
        r33 = FileType.LIVEPHOTO;
        goto L17
    L9:
        if (r3 == false) goto L13;
        if (r2 <= 1) goto L12;
        r33 = FileType.VIDEOS;
        goto L17
    L12:
        r33 = FileType.VIDEO;
        goto L17
    L13:
        if (r2 <= 1) goto L15;
        r33 = FileType.PHOTOS;
        goto L17
    L15:
        r33 = FileType.PHOTO;
        goto L17
    }

    public static Bulletin createSaveToGalleryBulletin(FrameLayout r1, boolean r2, int r3, int r4) {
        BulletinFactory r12 = of(r1, null);
        if (r2 == false) goto L5;
        FileType r22 = FileType.VIDEO;
    L7:
        return r12.createDownloadBulletin(r22, 1, r3, r4);
    L5:
        r22 = FileType.PHOTO;
        goto L7
    }

    public static Bulletin createSaveToGalleryBulletin(FrameLayout r0, boolean r1, Theme.ResourcesProvider r2) {
        BulletinFactory r02 = of(r0, r2);
        if (r1 == false) goto L5;
        FileType r12 = FileType.VIDEO;
    L7:
        return r02.createDownloadBulletin(r12, r2);
    L5:
        r12 = FileType.PHOTO;
        goto L7
    }

    public static Bulletin createSaveToGalleryBulletin(FrameLayout r1, boolean r2, boolean r3, int r4, int r5) {
        BulletinFactory r12 = of(r1, null);
        if (r3 == false) goto L5;
        FileType r22 = FileType.LIVEPHOTO;
    L9:
        return r12.createDownloadBulletin(r22, 1, r4, r5);
    L5:
        if (r2 == false) goto L7;
        r22 = FileType.VIDEO;
        goto L9
    L7:
        r22 = FileType.PHOTO;
        goto L9
    }

    public static Bulletin createSaveToGalleryBulletin(BaseFragment r0, boolean r1, Theme.ResourcesProvider r2) {
        BulletinFactory r02 = of(r0);
        if (r1 == false) goto L5;
        FileType r12 = FileType.VIDEO;
    L7:
        return r02.createDownloadBulletin(r12, r2);
    L5:
        r12 = FileType.PHOTO;
        goto L7
    }

    public static Bulletin createSaveToGalleryBulletin(BaseFragment r0, boolean r1, boolean r2, Theme.ResourcesProvider r3) {
        BulletinFactory r02 = of(r0);
        if (r2 == false) goto L5;
        FileType r12 = FileType.LIVEPHOTO;
    L9:
        return r02.createDownloadBulletin(r12, r3);
    L5:
        if (r1 == false) goto L7;
        r12 = FileType.VIDEO;
        goto L9
    L7:
        r12 = FileType.PHOTO;
        goto L9
    }

    public static Bulletin createSoundEnabledBulletin(BaseFragment r2, int r3, Theme.ResourcesProvider r4) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(r2.getParentActivity(), r4);
        boolean r42 = true;
        if (r3 == 0) goto L8;
        if (r3 != 1) goto L6;
        String r32 = LocaleController.getString(R.string.SoundOffHint);
        r42 = false;
    L9:
        if (r42 == false) goto L11;
        r0.setAnimation(R.raw.sound_on, new String[0]);
    L12:
        r0.textView.setText(r32);
        return Bulletin.make(r2, r0, 1500);
    L11:
        r0.setAnimation(R.raw.sound_off, new String[0]);
        goto L12
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L8:
        r32 = LocaleController.getString(R.string.SoundOnHint);
        goto L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Bulletin createUnpinAllMessagesBulletin(BaseFragment r4, int r5, boolean r6, Runnable r7, Runnable r8, Theme.ResourcesProvider r9) {
        if (r4.getParentActivity() != null) goto L9;
        if (r8 == null) goto L14;
        r8.run();
        return null;
    L14:
        return null;
    L9:
        if (r6 == false) goto L11;
        Bulletin.TwoLineLottieLayout r52 = new Bulletin.TwoLineLottieLayout(r4.getParentActivity(), r9);
        r52.setAnimation(R.raw.ic_unpin, 28, 28, new String[]{"Pin", "Line"});
        r52.titleTextView.setText(LocaleController.getString(R.string.PinnedMessagesHidden));
        r52.subtitleTextView.setText(LocaleController.getString(R.string.PinnedMessagesHiddenInfo));
        Bulletin.LottieLayout r53 = r52;
    L12:
        r53.setButton(new Bulletin.UndoButton(r4.getParentActivity(), true, r9).setUndoAction(r7).setDelayedAction(r8));
        return Bulletin.make(r4, r53, 5000);
    L11:
        Bulletin.LottieLayout r62 = new Bulletin.LottieLayout(r4.getParentActivity(), r9);
        r62.setAnimation(R.raw.ic_unpin, 28, 28, new String[]{"Pin", "Line"});
        r62.textView.setText(LocaleController.formatPluralString("MessagesUnpinned", r5, new Object[0]));
        r53 = r62;
        goto L12
    }

    public static Bulletin createUnpinMessageBulletin(BaseFragment r1, Runnable r2, Runnable r3, Theme.ResourcesProvider r4) {
        return createPinMessageBulletin(r1, false, r2, r3, r4);
    }

    public static BulletinFactory global() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L5;
        Dialog r1 = r0.visibleDialog;
        if ((r1 instanceof BottomSheet) == false) goto L11;
        return of(((BottomSheet) r1).container, r0.getResourceProvider());
    L11:
        return of(r0);
    L5:
        return of(Bulletin.BulletinWindow.make(ApplicationLoader.applicationContext), null);
    }

    public static BulletinFactory of(FrameLayout r1, Theme.ResourcesProvider r2) {
        return new BulletinFactory(r1, r2);
    }

    public static BulletinFactory of(BaseFragment r1) {
        if (r1 != null) goto L6;
        return global();
    L6:
        return new BulletinFactory(r1);
    }

    public static void showError(TLRPC.TL_error r2) {
        if (LaunchActivity.isActive == false) goto L11;
        if (r2 != null) goto L7;
    L9:
        global().createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r2.text})).show();
        return;
    L7:
        if (r2.code != 406) goto L9;
        return;
    }

    public Bulletin create(Bulletin.Layout r2, int r3) {
        BaseFragment r0 = this.fragment;
        if (r0 == null) goto L7;
        return Bulletin.make(r0, r2, r3);
    L7:
        return Bulletin.make(this.containerLayout, r2, r3);
    }

    public Bulletin createAdReportedBulletin(CharSequence r4) {
        if (getContext() == null) goto L5;
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(R.raw.ic_admin, new String[]{"Shield"});
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(3);
        r0.textView.setText(r4);
        return create(r0, 2750);
    L5:
        return new Bulletin.EmptyBulletin();
    }

    public Bulletin createBanBulletin(boolean r7) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        if (r7 == false) goto L5;
        r0.setAnimation(R.raw.ic_ban, new String[]{"Hand"});
        String r72 = LocaleController.getString(R.string.UserBlocked);
    L6:
        r0.textView.setText(AndroidUtilities.replaceTags(r72));
        return create(r0, 1500);
    L5:
        r0.setAnimation(R.raw.ic_unban, new String[]{"Main", "Finger 1", "Finger 2", "Finger 3", "Finger 4"});
        r72 = LocaleController.getString(R.string.UserUnblocked);
        goto L6
    }

    public Bulletin createCaptionLimitBulletin(int r8, Runnable r9) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), null);
        r0.setAnimation(R.raw.caption_limit, new String[0]);
        String r82 = LocaleController.formatPluralString("ChannelCaptionLimitPremiumPromo", r8, new Object[0]);
        SpannableStringBuilder r1 = SpannableStringBuilder.valueOf(AndroidUtilities.replaceTags(r82));
        int r4 = r82.indexOf(42);
        int r5 = r4 + 1;
        int r3 = r82.indexOf(42, r5);
        r1.replace(r4, r3 + 1, r82.substring(r5, r3));
        r1.setSpan(new AnonymousClass1(this, r9), r4, r3 - 1, 33);
        r0.textView.setText(r1);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(3);
        return create(r0, 5000);
    }

    public Bulletin createChatsBulletin(List<TLObject> r10, CharSequence r11, CharSequence r12) {
        Context r1 = getContext();
        if (r12 == null) goto L5;
        boolean r4 = true;
    L6:
        Bulletin.UsersLayout r0 = new Bulletin.UsersLayout(r1, r4, this.resourcesProvider);
        if (r10 == null) goto L22;
        int r13 = 0;
        int r42 = 0;
    L10:
        if (r13 >= r10.size()) goto L18;
        if (r42 >= 3) goto L18;
        TLObject r5 = r10.get(r13);
        if (r5 == null) goto L17;
        int r7 = r42 + 1;
        r0.avatarsImageView.setCount(r7);
        r0.avatarsImageView.setObject(r42, UserConfig.selectedAccount, r5);
        r42 = r7;
    L17:
        r13 = r13 + 1;
    L18:
        int r102 = r10.size();
        AvatarsImageView r14 = r0.avatarsImageView;
        if (r102 != 1) goto L21;
        r14.setTranslationX(AndroidUtilities.dp(4.0f));
        r0.avatarsImageView.setScaleX(1.2f);
        r0.avatarsImageView.setScaleY(1.2f);
    L23:
        r0.avatarsImageView.commitTransition(false);
        TextView r103 = r0.textView;
        if (r12 == null) goto L31;
        r103.setSingleLine(true);
        r0.textView.setMaxLines(1);
        r0.textView.setText(r11);
        r0.subtitleView.setText(r12);
        r0.subtitleView.setSingleLine(true);
        r0.subtitleView.setMaxLines(1);
        if ((r0.linearLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L38;
        int r104 = AndroidUtilities.dp(74 - ((3 - r42) * 12));
        boolean r112 = LocaleController.isRTL;
        LinearLayout r122 = r0.linearLayout;
        if (r112 == false) goto L30;
        ((ViewGroup.MarginLayoutParams) r122.getLayoutParams()).rightMargin = r104;
        goto L38
    L30:
        ((ViewGroup.MarginLayoutParams) r122.getLayoutParams()).leftMargin = r104;
    L38:
        if (LocaleController.isRTL == false) goto L41;
        r0.avatarsImageView.setTranslationX(AndroidUtilities.dp(32 - ((r42 - 1) * 12)));
    L41:
        return create(r0, 5000);
    L31:
        r103.setSingleLine(false);
        r0.textView.setMaxLines(2);
        r0.textView.setText(r11);
        if ((r0.textView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L38;
        int r105 = AndroidUtilities.dp(74 - ((3 - r42) * 12));
        boolean r113 = LocaleController.isRTL;
        TextView r123 = r0.textView;
        if (r113 == false) goto L36;
        ((ViewGroup.MarginLayoutParams) r123.getLayoutParams()).rightMargin = r105;
        goto L38
    L36:
        ((ViewGroup.MarginLayoutParams) r123.getLayoutParams()).leftMargin = r105;
        goto L38
    L21:
        r14.setScaleX(1.0f);
        r0.avatarsImageView.setScaleY(1.0f);
        goto L23
    L22:
        r42 = 0;
        goto L23
    L5:
        r4 = false;
        goto L6
    }

    public Bulletin createContainsEmojiBulletin(TLRPC.Document r9, int r10, Utilities.Callback<TLRPC.InputStickerSet> r11) {
        TLRPC.InputStickerSet r0 = MessageObject.getInputStickerSet(r9);
        if (r0 != null) goto L5;
        return null;
    L5:
        TLRPC.TL_messages_stickerSet r2 = MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSet(r0, true);
        if (r2 == null) goto L18;
        TLRPC.StickerSet r22 = r2.set;
        if (r22 == null) goto L18;
        if (r10 != 1) goto L12;
        CharSequence r102 = AndroidUtilities.replaceTags(LocaleController.formatString("TopicContainsEmojiPackSingle", R.string.TopicContainsEmojiPackSingle, new Object[]{r22.title}));
    L16:
        return createEmojiBulletin(r9, r102, LocaleController.getString(R.string.ViewAction), new BulletinFactory$$ExternalSyntheticLambda3(r11, r0));
    L12:
        if (r10 != 2) goto L14;
        r102 = AndroidUtilities.replaceTags(LocaleController.formatString("StoryContainsEmojiPackSingle", R.string.StoryContainsEmojiPackSingle, new Object[]{r22.title}));
        goto L16
    L14:
        r102 = AndroidUtilities.replaceTags(LocaleController.formatString("MessageContainsEmojiPackSingle", R.string.MessageContainsEmojiPackSingle, new Object[]{r22.title}));
    L18:
        if (r10 != 1) goto L20;
        SpannableStringBuilder r3 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatString("TopicContainsEmojiPackSingle", R.string.TopicContainsEmojiPackSingle, new Object[]{"<{LOADING}>"})));
    L23:
        int r23 = r3.toString().indexOf("<{LOADING}>");
        if (r23 < 0) goto L26;
        LoadingSpan r4 = new LoadingSpan(null, AndroidUtilities.dp(100.0f), AndroidUtilities.dp(2.0f), this.resourcesProvider);
        r3.setSpan(r4, r23, r23 + 11, 33);
        int r24 = Theme.key_undo_infoColor;
        r4.setColors(ColorUtils.setAlphaComponent(Theme.getColor(r24, this.resourcesProvider), 32), ColorUtils.setAlphaComponent(Theme.getColor(r24, this.resourcesProvider), 72));
    L27:
        long r5 = System.currentTimeMillis();
        Bulletin r8 = createEmojiLoadingBulletin(r9, r3, LocaleController.getString(R.string.ViewAction), new BulletinFactory$$ExternalSyntheticLambda1(r11, r0));
        if (r4 != null) goto L30;
    L32:
        MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSet(r0, null, false, new BulletinFactory$$ExternalSyntheticLambda2(r10, r8, r5));
        return r8;
    L30:
        if ((r8.getLayout() instanceof Bulletin.LoadingLottieLayout) == false) goto L32;
        r4.setView(((Bulletin.LoadingLottieLayout) r8.getLayout()).textLoadingView);
        goto L32
    L26:
        r4 = null;
        goto L27
    L20:
        if (r10 != 2) goto L22;
        r3 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatString("StoryContainsEmojiPackSingle", R.string.StoryContainsEmojiPackSingle, new Object[]{"<{LOADING}>"})));
        goto L23
    L22:
        r3 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatString("MessageContainsEmojiPackSingle", R.string.MessageContainsEmojiPackSingle, new Object[]{"<{LOADING}>"})));
        goto L23
    }

    public Bulletin createCopyBulletin(String r2) {
        return createCopyBulletin(r2, null);
    }

    public Bulletin createCopyBulletin(String r5, Theme.ResourcesProvider r6) {
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L5;
        Bulletin.LottieLayout r62 = new Bulletin.LottieLayout(getContext(), null);
        r62.setAnimation(R.raw.copy, 36, 36, new String[]{"NULL ROTATION", "Back", "Front"});
        r62.textView.setText(r5);
        return create(r62, 1500);
    L5:
        return new Bulletin.EmptyBulletin();
    }

    public Bulletin createCopyLinkBulletin() {
        return createCopyLinkBulletin(false);
    }

    public Bulletin createCopyLinkBulletin(String r4, Theme.ResourcesProvider r5) {
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L5;
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), r5);
        r0.setAnimation(R.raw.voip_invite, 36, 36, new String[]{"Wibe", "Circle"});
        r0.textView.setText(r4);
        return create(r0, 1500);
    L5:
        return new Bulletin.EmptyBulletin();
    }

    public Bulletin createCopyLinkBulletin(boolean r6) {
        if (AndroidUtilities.shouldShowClipboardToast() == true) goto L7;
        return new Bulletin.EmptyBulletin();
    L7:
        if (r6 == false) goto L10;
        Bulletin.TwoLineLottieLayout r62 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r62.setAnimation(R.raw.voip_invite, 36, 36, new String[]{"Wibe", "Circle"});
        r62.titleTextView.setText(LocaleController.getString(R.string.LinkCopied));
        r62.subtitleTextView.setText(LocaleController.getString(R.string.LinkCopiedPrivateInfo));
        return create(r62, 2750);
    L10:
        Bulletin.LottieLayout r63 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r63.setAnimation(R.raw.voip_invite, 36, 36, new String[]{"Wibe", "Circle"});
        r63.textView.setText(LocaleController.getString(R.string.LinkCopied));
        return create(r63, 1500);
    }

    public Bulletin createDownloadBulletin(FileType r2) {
        return createDownloadBulletin(r2, this.resourcesProvider);
    }

    public Bulletin createDownloadBulletin(FileType r7, int r8, int r9, int r10) {
        return createDownloadBulletin(r7, r8, r9, r10, null);
    }

    public Bulletin createDownloadBulletin(FileType r3, int r4, int r5, int r6, Theme.ResourcesProvider r7) {
        if (r5 == 0) goto L5;
        if (r6 == 0) goto L5;
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), r7, r5, r6);
    L6:
        r0.setAnimation(FileType.Icon.m9095$$Nest$fgetresId(FileType.m9091$$Nest$fgeticon(r3)), FileType.Icon.m9093$$Nest$fgetlayers(FileType.m9091$$Nest$fgeticon(r3)));
        r0.textView.setText(AndroidUtilities.replaceSingleTag(FileType.m9092$$Nest$mgetText(r3, r4), new BulletinFactory$$ExternalSyntheticLambda0()));
        if (FileType.Icon.m9094$$Nest$fgetpaddingBottom(FileType.m9091$$Nest$fgeticon(r3)) == 0) goto L10;
        r0.setIconPaddingBottom(FileType.Icon.m9094$$Nest$fgetpaddingBottom(FileType.m9091$$Nest$fgeticon(r3)));
    L10:
        return create(r0, 1500);
    L5:
        r0 = new Bulletin.LottieLayout(getContext(), r7);
        goto L6
    }

    public Bulletin createDownloadBulletin(FileType r7, int r8, Theme.ResourcesProvider r9) {
        return createDownloadBulletin(r7, r8, 0, 0, r9);
    }

    public Bulletin createDownloadBulletin(FileType r2, Theme.ResourcesProvider r3) {
        return createDownloadBulletin(r2, 1, r3);
    }

    public Bulletin createEmojiBulletin(long r6, String r8, String r9) {
        Bulletin.TwoLineBackupLayout r0 = new Bulletin.TwoLineBackupLayout(getContext(), this.resourcesProvider);
        r0.imageView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(1, UserConfig.selectedAccount, r6));
        r0.imageView.setEmojiColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        r0.titleTextView.setText(r8);
        r0.subtitleTextView.setText(r9);
        return create(r0, 2750);
    }

    public Bulletin createEmojiBulletin(String r5, CharSequence r6) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(MediaDataController.getInstance(UserConfig.selectedAccount).getEmojiAnimatedSticker(r5), 36, 36, new String[0]);
        r0.textView.setText(AndroidUtilities.replaceTags(r6));
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(2);
        return create(r0, 2750);
    }

    public Bulletin createEmojiBulletin(TLRPC.Document r6, CharSequence r7) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        if (MessageObject.isTextColorEmoji(r6) == false) goto L5;
        r0.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_undo_infoColor), PorterDuff.Mode.SRC_IN));
    L5:
        r0.setAnimation(r6, 36, 36, new String[0]);
        if (r0.imageView.getImageReceiver() == null) goto L8;
        r0.imageView.getImageReceiver().setRoundRadius(AndroidUtilities.dp(5.0f));
    L8:
        r0.textView.setText(r7);
        r0.textView.setTextSize(1, 14.0f);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(3);
        return create(r0, 2750);
    }

    public Bulletin createEmojiBulletin(TLRPC.Document r6, CharSequence r7, CharSequence r8) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        if (MessageObject.isTextColorEmoji(r6) == false) goto L5;
        r0.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_undo_infoColor), PorterDuff.Mode.SRC_IN));
    L5:
        r0.setAnimation(r6, 36, 36, new String[0]);
        r0.titleTextView.setText(r7);
        r0.subtitleTextView.setText(r8);
        if ((r7.length() + r8.length()) >= 20) goto L8;
        int r62 = 1500;
    L10:
        return create(r0, r62);
    L8:
        r62 = 2750;
        goto L10
    }

    public Bulletin createEmojiBulletin(TLRPC.Document r6, CharSequence r7, CharSequence r8, Runnable r9) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        if (MessageObject.isTextColorEmoji(r6) == false) goto L5;
        r0.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_undo_infoColor), PorterDuff.Mode.SRC_IN));
    L5:
        r0.setAnimation(r6, 36, 36, new String[0]);
        if (r0.imageView.getImageReceiver() == null) goto L8;
        r0.imageView.getImageReceiver().setRoundRadius(AndroidUtilities.dp(8.0f));
    L8:
        TextView r62 = r0.textView;
        if (r7.toString().contains("**") == false) goto L11;
        r7 = AndroidUtilities.replaceTags(r7);
    L11:
        r62.setText(r7);
        r0.textView.setTextSize(1, 14.0f);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(4);
        if (r8 == null) goto L16;
        if (r9 == null) goto L16;
        r0.setButton(new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(r8).setUndoAction(r9));
    L16:
        return create(r0, 2750);
    }

    public Bulletin createEmojiLoadingBulletin(TLRPC.Document r6, CharSequence r7, CharSequence r8, Runnable r9) {
        Bulletin.LoadingLottieLayout r0 = new Bulletin.LoadingLottieLayout(getContext(), this.resourcesProvider);
        if (MessageObject.isTextColorEmoji(r6) == false) goto L5;
        r0.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_undo_infoColor), PorterDuff.Mode.SRC_IN));
    L5:
        r0.setAnimation(r6, 36, 36, new String[0]);
        r0.textView.setTextSize(1, 14.0f);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(3);
        r0.textLoadingView.setText(r7);
        r0.textLoadingView.setTextSize(1, 14.0f);
        r0.textLoadingView.setSingleLine(false);
        r0.textLoadingView.setMaxLines(3);
        r0.setButton(new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(r8).setUndoAction(r9));
        return create(r0, 2750);
    }

    public Bulletin createErrorBulletin(CharSequence r2) {
        return createErrorBulletin(r2, null);
    }

    public Bulletin createErrorBulletin(CharSequence r4, Theme.ResourcesProvider r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), r5);
        r0.setAnimation(R.raw.chats_infotip, new String[0]);
        r0.textView.setText(AndroidUtilities.replaceTags(r4.toString()));
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(2);
        return create(r0, 1500);
    }

    public Bulletin createErrorBulletinSubtitle(CharSequence r3, CharSequence r4, Theme.ResourcesProvider r5) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), r5);
        r0.setAnimation(R.raw.chats_infotip, new String[0]);
        r0.titleTextView.setText(r3);
        r0.subtitleTextView.setText(r4);
        return create(r0, 1500);
    }

    public Bulletin createImageBulletin(int r4, CharSequence r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setBackground(Theme.getColor(Theme.key_undo_background, this.resourcesProvider), 12);
        r0.imageView.setImageResource(r4);
        r0.textView.setText(r5);
        r0.textView.setSingleLine(false);
        r0.textView.setLines(2);
        r0.textView.setMaxLines(4);
        TextView r42 = r0.textView;
        r42.setMaxWidth(HintView2.cutInFancyHalf(r42.getText(), r0.textView.getPaint()));
        r0.textView.setLineSpacing(AndroidUtilities.dp(1.33f), 1.0f);
        ((ViewGroup.MarginLayoutParams) r0.textView.getLayoutParams()).rightMargin = AndroidUtilities.dp(12.0f);
        r0.setWrapWidth();
        return create(r0, 5000);
    }

    public Bulletin createMessagesTaggedBulletin(int r9, TLRPC.Document r10, Runnable r11) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(R.raw.tag_icon_3, 36, 36, new String[0]);
        r0.removeView(r0.textView);
        AnimatedEmojiSpan.TextViewEmojis r1 = new AnimatedEmojiSpan.TextViewEmojis(r0.getContext());
        r0.textView = r1;
        r1.setTypeface(Typeface.SANS_SERIF);
        r0.textView.setTextSize(1, 15.0f);
        r0.textView.setEllipsize(TextUtils.TruncateAt.END);
        r0.textView.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
        TextPaint r12 = new TextPaint();
        r12.setTextSize(AndroidUtilities.dp(20.0f));
        SpannableString r3 = new SpannableString("d");
        r3.setSpan(new AnimatedEmojiSpan(r10, r12.getFontMetricsInt()), 0, r3.length(), 33);
        TextView r102 = r0.textView;
        if (r9 <= 1) goto L5;
        String r92 = LocaleController.formatPluralString("SavedTagMessagesTagged", r9, new Object[0]);
    L6:
        r102.setText(new SpannableStringBuilder(r92).append(" ").append(r3));
        if (r11 == null) goto L9;
        r0.setButton(new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(LocaleController.getString(R.string.ViewAction)).setUndoAction(r11));
    L9:
        r0.setTextColor(Theme.getColor(Theme.key_undo_infoColor, this.resourcesProvider));
        r0.addView(r0.textView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 2.0f, 8.0f, 0.0f));
        return create(r0, 2750);
    L5:
        r92 = LocaleController.getString(R.string.SavedTagMessageTagged);
        goto L6
    }

    public Bulletin createReportSent(Theme.ResourcesProvider r3) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), r3);
        r0.setAnimation(R.raw.chats_infotip, new String[0]);
        r0.textView.setText(LocaleController.getString(R.string.ReportChatSent));
        return create(r0, 1500);
    }

    public Bulletin createRestrictVoiceMessagesPremiumBulletin() {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), null);
        r0.setAnimation(R.raw.voip_muted, new String[0]);
        String r1 = LocaleController.getString(R.string.PrivacyVoiceMessagesPremiumOnly);
        SpannableStringBuilder r3 = new SpannableStringBuilder(r1);
        int r5 = r1.indexOf(42);
        int r4 = r1.lastIndexOf(42);
        if (r5 < 0) goto L5;
        r3.replace(r5, r4 + 1, r1.substring(r5 + 1, r4));
        r3.setSpan(new AnonymousClass2(this), r5, r4 - 1, 33);
    L5:
        r0.textView.setText(r3);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(2);
        return create(r0, 2750);
    }

    public Bulletin createSimpleBulletin(int r2, CharSequence r3) {
        return createSimpleBulletinWithIconSize(r2, r3, 36);
    }

    public Bulletin createSimpleBulletin(int r9, CharSequence r10, int r11) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(r9, 36, 36, new String[0]);
        if (r10 == null) goto L16;
        String r92 = r10.toString();
        if ((r10 instanceof SpannableStringBuilder) == false) goto L7;
        SpannableStringBuilder r2 = (SpannableStringBuilder) r10;
    L8:
        int r4 = r92.indexOf(10);
        int r5 = 0;
    L9:
        if (r4 < 0) goto L15;
        if (r4 >= r10.length()) goto L15;
        if (r5 < r11) goto L14;
        r2.replace(r4, r4 + 1, " ");
    L14:
        r5 = r5 + 1;
        r4 = r92.indexOf(10, r4 + 1);
    L15:
        r10 = r2;
        goto L16
    L7:
        r2 = new SpannableStringBuilder(r10);
    L16:
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(r11);
        r0.textView.setText(r10);
        if (r10.length() >= 20) goto L19;
        int r93 = 1500;
    L21:
        return create(r0, r93);
    L19:
        r93 = 2750;
        goto L21
    }

    public Bulletin createSimpleBulletin(int r9, CharSequence r10, int r11, int r12) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(r9, 36, 36, new String[0]);
        if (r10 == null) goto L16;
        String r92 = r10.toString();
        if ((r10 instanceof SpannableStringBuilder) == false) goto L7;
        SpannableStringBuilder r2 = (SpannableStringBuilder) r10;
    L8:
        int r4 = r92.indexOf(10);
        int r5 = 0;
    L9:
        if (r4 < 0) goto L15;
        if (r4 >= r10.length()) goto L15;
        if (r5 < r11) goto L14;
        r2.replace(r4, r4 + 1, " ");
    L14:
        r5 = r5 + 1;
        r4 = r92.indexOf(10, r4 + 1);
    L15:
        r10 = r2;
        goto L16
    L7:
        r2 = new SpannableStringBuilder(r10);
    L16:
        r0.textView.setText(r10);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(r11);
        return create(r0, r12);
    }

    public Bulletin createSimpleBulletin(int r4, CharSequence r5, CharSequence r6) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(r4, 36, 36, new String[0]);
        r0.titleTextView.setText(r5);
        r0.subtitleTextView.setText(r6);
        if ((r5.length() + r6.length()) >= 20) goto L5;
        int r42 = 1500;
    L7:
        return create(r0, r42);
    L5:
        r42 = 2750;
        goto L7
    }

    public Bulletin createSimpleBulletin(int r8, CharSequence r9, CharSequence r10, int r11, Runnable r12) {
        return createSimpleBulletin(r8, r9, r10, r11, false, r12);
    }

    public Bulletin createSimpleBulletin(int r6, CharSequence r7, CharSequence r8, int r9, boolean r10, Runnable r11) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        if (r6 == 0) goto L5;
        r0.setAnimation(r6, 36, 36, new String[0]);
    L6:
        r0.textView.setTextSize(1, 14.0f);
        r0.textView.setTextDirection(5);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(4);
        r0.textView.setText(AndroidUtilities.replaceTags(r7.toString()));
        r0.setButton(new Bulletin.UndoButton(getContext(), true, r10, this.resourcesProvider).setText(r8).setUndoAction(r11));
        return create(r0, r9);
    L5:
        r0.imageView.setVisibility(4);
        ViewGroup.MarginLayoutParams r62 = (ViewGroup.MarginLayoutParams) r0.textView.getLayoutParams();
        r62.leftMargin = AndroidUtilities.dp(16.0f);
        goto L6
    }

    public Bulletin createSimpleBulletin(int r4, CharSequence r5, CharSequence r6, CharSequence r7, Runnable r8) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(r4, 36, 36, new String[0]);
        r0.titleTextView.setText(r5);
        r0.subtitleTextView.setText(r6);
        r0.setButton(new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(r7).setUndoAction(r8));
        return create(r0, 5000);
    }

    public Bulletin createSimpleBulletin(int r8, CharSequence r9, CharSequence r10, Runnable r11) {
        if (r9.length() >= 20) goto L6;
        int r0 = 1500;
    L8:
        return createSimpleBulletin(r8, r9, r10, r0, r11);
    L6:
        r0 = 2750;
        goto L8
    }

    public Bulletin createSimpleBulletin(Drawable r4, CharSequence r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.imageView.setImageDrawable(r4);
        if ((r4 instanceof PeerColorActivity.PeerColorDrawable) == false) goto L5;
        ((PeerColorActivity.PeerColorDrawable) r4).setView(r0.imageView);
    L5:
        r0.textView.setText(r5);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(2);
        return create(r0, 2750);
    }

    public Bulletin createSimpleBulletin(Drawable r4, CharSequence r5, CharSequence r6) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r0.imageView.setImageDrawable(r4);
        r0.titleTextView.setText(r5);
        r0.subtitleTextView.setText(r6);
        return create(r0, 2750);
    }

    public Bulletin createSimpleBulletin(Drawable r4, CharSequence r5, CharSequence r6, String r7, Runnable r8) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r0.imageView.setImageDrawable(r4);
        r0.titleTextView.setText(r5);
        r0.subtitleTextView.setText(r6);
        r0.setButton(new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(r7).setUndoAction(r8));
        return create(r0, 2750);
    }

    public Bulletin createSimpleBulletin(CharSequence r4, CharSequence r5) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r0.hideImage();
        r0.titleTextView.setText(r4);
        r0.subtitleTextView.setText(r5);
        return create(r0, 5000);
    }

    public Bulletin createSimpleBulletin(CharSequence r4, CharSequence r5, int r6) {
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r0.setSticker(r6);
        r0.titleTextView.setText(r4);
        r0.subtitleTextView.setText(r5);
        return create(r0, 5000);
    }

    public Bulletin createSimpleBulletin(TLRPC.Document r20, CharSequence r21) {
        if (r20 == null) goto L5;
        Bulletin.TwoLineLayout r2 = new Bulletin.TwoLineLayout(getContext(), this.resourcesProvider);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, AndroidUtilities.dp(28.0f), true, null, false);
        TLRPC.PhotoSize r4 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, AndroidUtilities.dp(28.0f), true, r3, true);
        r2.imageView.setImage(ImageLocation.getForDocument(r4, r20), "28_28", ImageLocation.getForDocument(r3, r20), "28_28", null, 0, 0, null);
        r2.imageView.getImageReceiver().setRoundRadius(AndroidUtilities.dp(8.0f));
        r2.titleTextView.setText(r21);
        r2.titleTextView.setSingleLine(false);
        r2.titleTextView.setTextSize(1, 15.0f);
        r2.titleTextView.setMaxLines(2);
        r2.titleTextView.setTypeface(null);
        r2.subtitleTextView.setVisibility(8);
        if (r21.length() >= 20) goto L9;
        int r1 = 1500;
    L11:
        return create(r2, r1);
    L9:
        r1 = 2750;
        goto L11
    L5:
        return new Bulletin.EmptyBulletin();
    }

    public Bulletin createSimpleBulletin(TLRPC.Document r20, CharSequence r21, CharSequence r22) {
        if (r20 == null) goto L5;
        Bulletin.TwoLineLayout r2 = new Bulletin.TwoLineLayout(getContext(), this.resourcesProvider);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, AndroidUtilities.dp(28.0f), true, null, false);
        TLRPC.PhotoSize r4 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, AndroidUtilities.dp(28.0f), true, r3, true);
        r2.imageView.setImage(ImageLocation.getForDocument(r4, r20), "28_28", ImageLocation.getForDocument(r3, r20), "28_28", null, 0, 0, null);
        r2.imageView.getImageReceiver().setRoundRadius(AndroidUtilities.dp(5.0f));
        r2.titleTextView.setText(r21);
        r2.titleTextView.setSingleLine(true);
        r2.titleTextView.setTextSize(1, 15.0f);
        r2.titleTextView.setMaxLines(1);
        r2.titleTextView.setTypeface(AndroidUtilities.bold());
        r2.subtitleTextView.setText(r22);
        r2.subtitleTextView.setSingleLine(false);
        r2.subtitleTextView.setMaxLines(5);
        if (r22.length() >= 20) goto L9;
        int r1 = 1500;
    L11:
        return create(r2, r1);
    L9:
        r1 = 2750;
        goto L11
    L5:
        return new Bulletin.EmptyBulletin();
    }

    public Bulletin createSimpleBulletinDetail(int r5, CharSequence r6) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(r5, 36, 36, new String[0]);
        r0.textView.setText(r6);
        r0.textView.setSingleLine(false);
        r0.textView.setTextSize(1, 14.0f);
        r0.textView.setMaxLines(4);
        if (r6.length() >= 20) goto L5;
        int r52 = 1500;
    L7:
        return create(r0, r52);
    L5:
        r52 = 2750;
        goto L7
    }

    public Bulletin createSimpleBulletinWithIconSize(int r4, CharSequence r5, int r6) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r0.setAnimation(r4, r6, r6, new String[0]);
        r0.textView.setText(AndroidUtilities.replaceTags(r5.toString()));
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(2);
        if (r5.length() >= 20) goto L5;
        int r42 = 1500;
    L7:
        return create(r0, r42);
    L5:
        r42 = 2750;
        goto L7
    }

    public Bulletin createSimpleLargeBulletin(int r4, CharSequence r5, CharSequence r6) {
        Bulletin.TwoLineLayout r0 = new Bulletin.TwoLineLayout(getContext(), this.resourcesProvider);
        r0.imageView.setImageResource(r4);
        r0.titleTextView.setText(r5);
        r0.subtitleTextView.setText(r6);
        r0.subtitleTextView.setSingleLine(false);
        r0.subtitleTextView.setMaxLines(5);
        return create(r0, 5000);
    }

    public Bulletin createSimpleMultiBulletin(TLRPC.Document r20, CharSequence r21) {
        if (r20 == null) goto L5;
        Bulletin.TwoLineLayout r2 = new Bulletin.TwoLineLayout(getContext(), this.resourcesProvider);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, AndroidUtilities.dp(28.0f), true, null, false);
        TLRPC.PhotoSize r4 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, AndroidUtilities.dp(28.0f), true, r3, true);
        r2.imageView.setImage(ImageLocation.getForDocument(r4, r20), "28_28", ImageLocation.getForDocument(r3, r20), "28_28", null, 0, 0, null);
        r2.imageView.getImageReceiver().setRoundRadius(AndroidUtilities.dp(5.0f));
        r2.titleTextView.setSingleLine(false);
        r2.titleTextView.setText(r21);
        r2.titleTextView.setTextSize(1, 14.0f);
        r2.titleTextView.setMaxLines(3);
        r2.titleTextView.setTypeface(null);
        r2.subtitleTextView.setVisibility(8);
        if (r21.length() >= 20) goto L9;
        int r1 = 1500;
    L11:
        return create(r2, r1);
    L9:
        r1 = 2750;
        goto L11
    L5:
        return new Bulletin.EmptyBulletin();
    }

    public Bulletin createStaticEmojiBulletin(TLRPC.Document r6, CharSequence r7) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        if (MessageObject.isTextColorEmoji(r6) == false) goto L5;
        r0.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_undo_infoColor), PorterDuff.Mode.SRC_IN));
    L5:
        r0.setAnimation(r6, 36, 36, new String[0]);
        r0.imageView.stopAnimation();
        r0.textView.setText(r7);
        r0.textView.setTextSize(1, 14.0f);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(3);
        return create(r0, 2750);
    }

    public Bulletin createSuccessBulletin(CharSequence r2) {
        return createSuccessBulletin(r2, null);
    }

    public Bulletin createSuccessBulletin(CharSequence r4, Theme.ResourcesProvider r5) {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), r5);
        r0.setAnimation(R.raw.contact_check, new String[0]);
        r0.textView.setText(r4);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(2);
        return create(r0, 1500);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Bulletin createUndoBulletin(CharSequence r4, CharSequence r5, boolean r6, Runnable r7, Runnable r8) {
        if (TextUtils.isEmpty(r5) == true) goto L5;
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(getContext(), this.resourcesProvider);
        r0.titleTextView.setText(r4);
        r0.subtitleTextView.setText(r5);
        Bulletin.LottieLayout r02 = r0;
    L6:
        r02.setTimer();
        r02.setButton(new Bulletin.UndoButton(getContext(), true, r6, this.resourcesProvider).setText(LocaleController.getString(R.string.UndoNoCaps)).setUndoAction(r7).setDelayedAction(r8));
        return create(r02, 5000);
    L5:
        Bulletin.LottieLayout r03 = new Bulletin.LottieLayout(getContext(), this.resourcesProvider);
        r03.textView.setText(r4);
        r03.textView.setSingleLine(false);
        r03.textView.setMaxLines(2);
        r02 = r03;
        goto L6
    }

    public Bulletin createUndoBulletin(CharSequence r2, Runnable r3, Runnable r4) {
        return createUndoBulletin(r2, false, r3, r4);
    }

    public Bulletin createUndoBulletin(CharSequence r7, boolean r8, Runnable r9, Runnable r10) {
        return createUndoBulletin(r7, null, r8, r9, r10);
    }

    public Bulletin createUsersAddedBulletin(ArrayList<TLRPC.User> r4, TLRPC.Chat r5) {
        if (r4 != null) goto L4;
    L16:
        SpannableStringBuilder r52 = null;
    L18:
        return createUsersBulletin(r4, r52);
    L4:
        if (r4.size() == 0) goto L16;
        if (r4.size() != 1) goto L13;
        if (ChatObject.isChannelAndNotMegaGroup(r5) == false) goto L11;
        r52 = AndroidUtilities.replaceTags(LocaleController.formatString("HasBeenAddedToChannel", R.string.HasBeenAddedToChannel, new Object[]{"**" + UserObject.getFirstName(r4.get(0)) + "**"}));
        goto L18
    L11:
        r52 = AndroidUtilities.replaceTags(LocaleController.formatString("HasBeenAddedToGroup", R.string.HasBeenAddedToGroup, new Object[]{"**" + UserObject.getFirstName(r4.get(0)) + "**"}));
        goto L18
    L13:
        if (ChatObject.isChannelAndNotMegaGroup(r5) == false) goto L15;
        r52 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("AddedMembersToChannel", r4.size(), new Object[0]));
        goto L18
    L15:
        r52 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("AddedSubscribersToChannel", r4.size(), new Object[0]));
        goto L18
    }

    public Bulletin createUsersBulletin(List<? extends TLObject> r2, CharSequence r3) {
        return createUsersBulletin(r2, r3, null, null);
    }

    public Bulletin createUsersBulletin(List<? extends TLObject> r2, CharSequence r3, CharSequence r4) {
        return createUsersBulletin(r2, r3, r4, null);
    }

    public Bulletin createUsersBulletin(List<? extends TLObject> r18, CharSequence r19, CharSequence r20, UndoObject r21) {
        Context r6 = getContext();
        if (r20 == null) goto L5;
        boolean r9 = true;
    L6:
        Bulletin.UsersLayout r5 = new Bulletin.UsersLayout(r6, r9, this.resourcesProvider);
        if (r18 == null) goto L22;
        int r11 = 0;
        int r12 = 0;
    L10:
        if (r11 >= r18.size()) goto L12;
        if (r12 >= 3) goto L12;
        TLObject r13 = r18.get(r11);
        if (r13 == null) goto L17;
        int r15 = r12 + 1;
        r5.avatarsImageView.setCount(r15);
        r5.avatarsImageView.setObject(r12, UserConfig.selectedAccount, r13);
        r12 = r15;
    L17:
        r11 = r11 + 1;
    L12:
        float r16 = 4.0f;
        int r1 = r18.size();
        AvatarsImageView r92 = r5.avatarsImageView;
        if (r1 != 1) goto L21;
        r92.setTranslationX(AndroidUtilities.dp(4.0f));
        r5.avatarsImageView.setScaleX(1.2f);
        r5.avatarsImageView.setScaleY(1.2f);
    L23:
        r5.avatarsImageView.commitTransition(false);
        TextView r14 = r5.textView;
        if (r20 == null) goto L34;
        r14.setSingleLine(true);
        r5.textView.setMaxLines(1);
        r5.textView.setText(r19);
        r5.subtitleView.setText(r20);
        r5.subtitleView.setSingleLine(false);
        r5.subtitleView.setMaxLines(3);
        if ((r5.linearLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L43;
        int r17 = AndroidUtilities.dp(70 - ((3 - r12) * 12));
        if (r12 != 1) goto L30;
        r17 = r17 + AndroidUtilities.dp(r16);
    L30:
        boolean r2 = LocaleController.isRTL;
        LinearLayout r3 = r5.linearLayout;
        if (r2 == false) goto L33;
        ((ViewGroup.MarginLayoutParams) r3.getLayoutParams()).rightMargin = r17;
        goto L43
    L33:
        ((ViewGroup.MarginLayoutParams) r3.getLayoutParams()).leftMargin = r17;
    L43:
        if (r21 == null) goto L46;
        r5.setButton(new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(LocaleController.getString(R.string.UndoNoCaps)).setUndoAction(r21.onUndo).setDelayedAction(r21.onAction));
    L46:
        return create(r5, 5000);
    L34:
        r14.setSingleLine(false);
        r5.textView.setMaxLines(4);
        r5.textView.setText(r19);
        if ((r5.textView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L43;
        int r110 = AndroidUtilities.dp(70 - ((3 - r12) * 12));
        if (r12 != 1) goto L39;
        r5.textView.setTranslationY(-AndroidUtilities.dp(1.0f));
        r110 = r110 + AndroidUtilities.dp(r16);
    L39:
        boolean r22 = LocaleController.isRTL;
        TextView r32 = r5.textView;
        if (r22 == false) goto L42;
        ((ViewGroup.MarginLayoutParams) r32.getLayoutParams()).rightMargin = r110;
        goto L43
    L42:
        ((ViewGroup.MarginLayoutParams) r32.getLayoutParams()).leftMargin = r110;
        goto L43
    L21:
        r92.setScaleX(1.0f);
        r5.avatarsImageView.setScaleY(1.0f);
        goto L23
    L22:
        r16 = 4.0f;
        r12 = 0;
        goto L23
    L5:
        r9 = false;
        goto L6
    }

    public Bulletin createUsersBulletin(TLObject r2, CharSequence r3) {
        return createUsersBulletin(Arrays.asList(new TLObject[]{r2}), r3, null, null);
    }

    public Bulletin createUsersBulletin(TLObject r2, CharSequence r3, CharSequence r4) {
        return createUsersBulletin(Arrays.asList(new TLObject[]{r2}), r3, r4, null);
    }

    public Context getContext() {
        BaseFragment r0 = this.fragment;
        if (r0 == null) goto L9;
        Context r02 = r0.getParentActivity();
        if (r02 == null) goto L7;
    L13:
        if (r02 == null) goto L15;
        return r02;
    L15:
        return ApplicationLoader.applicationContext;
    L7:
        if (this.fragment.getLayoutContainer() == null) goto L13;
        r02 = this.fragment.getLayoutContainer().getContext();
        goto L13
    L9:
        FrameLayout r2 = this.containerLayout;
        if (r2 == null) goto L12;
        r02 = r2.getContext();
        goto L13
    L12:
        r02 = null;
        goto L13
    }

    public Theme.ResourcesProvider getResourcesProvider() {
        return this.resourcesProvider;
    }

    public Bulletin makeForError(TLRPC.TL_error r2) {
        if (LaunchActivity.isActive == false) goto L5;
        if (r2 != null) goto L10;
        return createErrorBulletin(LocaleController.formatString(R.string.UnknownError, new Object[0]));
    L10:
        return createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r2.text}));
    L5:
        return new Bulletin.EmptyBulletin();
    }

    public void showForError(String r2) {
        showForError(r2, false);
    }

    public void showForError(String r3, boolean r4) {
        if (LaunchActivity.isActive == true) goto L6;
        return;
    L6:
        if (TextUtils.isEmpty(r3) == false) goto L9;
        Bulletin r2 = createErrorBulletin(LocaleController.formatString(R.string.UnknownError, new Object[0]));
        r2.hideAfterBottomSheet = false;
        r2.show(r4);
        return;
    L9:
        Bulletin r22 = createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r3}));
        r22.hideAfterBottomSheet = false;
        r22.show(r4);
    }

    public void showForError(TLRPC.TL_error r2) {
        showForError(r2, false);
    }

    public void showForError(TLRPC.TL_error r4, boolean r5) {
        if (LaunchActivity.isActive == true) goto L6;
        return;
    L6:
        if (r4 != null) goto L10;
        Bulletin r3 = createErrorBulletin(LocaleController.formatString(R.string.UnknownError, new Object[0]));
        r3.hideAfterBottomSheet = false;
        r3.show(r5);
        return;
    L10:
        if (r4.code == 406) goto L13;
        Bulletin r32 = createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r4.text}));
        r32.hideAfterBottomSheet = false;
        r32.show(r5);
        return;
    }

    public boolean showForwardedBulletinWithTag(long r6, int r8) {
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L5;
    L15:
        return false;
    L5:
        if (this.fragment == null) goto L15;
        Bulletin.LottieLayoutWithReactions r0 = new Bulletin.LottieLayoutWithReactions(this.fragment, r8);
        if (r6 != UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L15;
        if (r8 > 1) goto L12;
        SpannableStringBuilder r62 = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.FwdMessageToSavedMessages), -1, 2, new BulletinFactory$$ExternalSyntheticLambda5());
    L13:
        r0.setAnimation(R.raw.saved_messages, 36, 36, new String[0]);
        r0.textView.setText(r62);
        r0.textView.setSingleLine(false);
        r0.textView.setMaxLines(2);
        Bulletin r5 = create(r0, 3500);
        r0.setBulletin(r5);
        r5.hideAfterBottomSheet(false);
        r5.show(true);
        return true;
    L12:
        r62 = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.FwdMessagesToSavedMessages), -1, 2, new BulletinFactory$$ExternalSyntheticLambda5());
        goto L13
    }
}
