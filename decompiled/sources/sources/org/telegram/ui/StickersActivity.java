package org.telegram.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.FeaturedStickerSetCell2;
import org.telegram.ui.Cells.StickerSetCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.ReorderingBulletinLayout;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Components.TrendingStickersAlert;
import org.telegram.ui.Components.TrendingStickersLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class StickersActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int activeReorderingRequests;
    private ActionBarMenuItem archiveMenuItem;

    @Keep
    private int archivedRow;
    private int currentType;
    private ActionBarMenuItem deleteMenuItem;

    @Keep
    private int dynamicPackOrder;
    private int emojiPacksRow;
    private ArrayList<TLRPC.StickerSetCovered> featured;

    @Keep
    private int featuredRow;
    ArrayList<TLRPC.TL_messages_stickerSet> frozenEmojiPacks;

    @Keep
    private int largeEmojiRow;
    private LinearLayoutManager layoutManager;
    private UniversalRecyclerView listView;
    private final List<Long> loadingFeaturedStickerSets;
    private boolean needReorder;
    private int reactionsDoubleTapRow;
    private NumberTextView selectedCountTextView;
    private final HashSet<Long> selectedSets;
    private Runnable sendReorderRunnable;
    private ArrayList<TLRPC.TL_messages_stickerSet> sets;
    private ActionBarMenuItem shareMenuItem;

    @Keep
    private int suggestRow;
    private TrendingStickersAlert trendingStickersAlert;

    /* renamed from: org.telegram.ui.StickersActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ StickersActivity this$0;

        public AnonymousClass1(StickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            StickersActivity r0 = this.this$0;
            if (r3 == (-1)) goto L5;
            StickersActivity.m19295$$Nest$mprocessSelectionMenu(r0, r3);
            return;
        L5:
            if (r0.onBackPressed(true) == false) goto L10;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.StickersActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayoutManager {
        final /* synthetic */ StickersActivity this$0;

        public AnonymousClass2(StickersActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(RecyclerView.State r1, int[] r2) {
            r2[1] = StickersActivity.m19294$$Nest$fgetlistView(this.this$0).getHeight();
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.StickersActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends TrendingStickersLayout.Delegate {
        final /* synthetic */ StickersActivity this$0;

        public AnonymousClass3(StickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public void onStickerSetAdd(TLRPC.StickerSetCovered r8, boolean r9) {
            MediaDataController.getInstance(StickersActivity.access$000(this.this$0)).toggleStickerSet(this.this$0.getParentActivity(), r8, 2, this.this$0, false, false);
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public void onStickerSetRemove(TLRPC.StickerSetCovered r9) {
            MediaDataController.getInstance(StickersActivity.access$100(this.this$0)).toggleStickerSet(this.this$0.getParentActivity(), r9, 0, this.this$0, false, false);
        }
    }

    /* renamed from: org.telegram.ui.StickersActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends URLSpanNoUnderline {
        final /* synthetic */ StickersActivity this$0;

        public AnonymousClass4(StickersActivity r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r3) {
            MessagesController.getInstance(StickersActivity.access$200(this.this$0)).openByUserName("stickers", this.this$0, 3);
        }
    }

    /* renamed from: org.telegram.ui.StickersActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements ShareAlert.ShareAlertDelegate {
        final /* synthetic */ StickersActivity this$0;

        public AnonymousClass5(StickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ShareAlert.ShareAlertDelegate
        public boolean didCopy() {
            this.this$0.clearSelected();
            return true;
        }

        @Override // org.telegram.ui.Components.ShareAlert.ShareAlertDelegate
        public void didShare() {
            this.this$0.clearSelected();
        }
    }

    /* renamed from: $r8$lambda$-VHiuWDm3Pu4CaUNiv0W4LGaKPM, reason: not valid java name */
    public static /* synthetic */ void m19289$r8$lambda$VHiuWDm3Pu4CaUNiv0W4LGaKPM(StickersActivity r0, ArrayList r1, int r2, AlertDialog r3, int r4) {
        r0.lambda$processSelectionMenu$13(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$6S1ydFLfpYC4N8XO8CZ7xDxOyow(StickersActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$sendReorder$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7wP8CbMBrMJiWvg9fMQUVUVbQRA(StickersActivity r0) {
        r0.lambda$new$5();
    }

    /* renamed from: $r8$lambda$82lk-V8GduopcPmCZZgvj8a0iHc, reason: not valid java name */
    public static /* synthetic */ void m19290$r8$lambda$82lkV8GduopcPmCZZgvj8a0iHc(StickersActivity r0, View r1) {
        r0.onStickerSetButtonClick(r1);
    }

    public static /* synthetic */ void $r8$lambda$8K5rbn3DXzUBLU4mUe9Ff5sQT70(View r2) {
        SharedConfig.setSuggestStickers(1);
        ((TextSettingsCell) r2).setValue(LocaleController.getString(R.string.SuggestStickersInstalled), true);
    }

    /* renamed from: $r8$lambda$FfCm-EVwzYlU0sfpxvFc-HRCQms, reason: not valid java name */
    public static /* synthetic */ void m19291$r8$lambda$FfCmEVwzYlU0sfpxvFcHRCQms(StickersActivity r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$openStickerSetOptions$11(r1);
    }

    /* renamed from: $r8$lambda$G-N_thVmDZAlTa-USYJOIjneYps, reason: not valid java name */
    public static /* synthetic */ void m19292$r8$lambda$GN_thVmDZAlTaUSYJOIjneYps(StickersActivity r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$openStickerSetOptions$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$GRwIdhRiI2TAk_FTkjMxFxb0XCc(StickersActivity r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$openStickerSetOptions$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$IqXvhUBY50Bhj0yCxfWSEqn5CRI(View r2) {
        SharedConfig.setSuggestStickers(0);
        ((TextSettingsCell) r2).setValue(LocaleController.getString(R.string.SuggestStickersAll), true);
    }

    public static /* synthetic */ void $r8$lambda$MSKeK3sNfzaR8XLm1OxQo8epxNY(StickersActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$SBRsrJsDmiJ5OjtAtm_4dKwex6c(StickersActivity r0, View r1) {
        r0.openStickerSetOptions(r1);
    }

    public static /* synthetic */ void $r8$lambda$V_ttmwdKbVchGdIbCi3IV2COQo4(StickersActivity r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$openStickerSetOptions$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$dCkAm_TljNDgkYSoU4uhell2Imo(StickersActivity r0, View r1) {
        r0.onFeaturedAddClick(r1);
    }

    public static /* synthetic */ int $r8$lambda$epCMCTLMBkxBVdFrCyoe1qrvRjM(StickersActivity r0, TLRPC.TL_messages_stickerSet r1, TLRPC.TL_messages_stickerSet r2) {
        return r0.lambda$whenReordered$4(r1, r2);
    }

    /* renamed from: $r8$lambda$h0xD4nT-0IcuWAfHCeg6RA9zKL4, reason: not valid java name */
    public static /* synthetic */ void m19293$r8$lambda$h0xD4nT0IcuWAfHCeg6RA9zKL4(StickersActivity r0, View r1) {
        r0.setQuickReactionImage(r1);
    }

    public static /* synthetic */ void $r8$lambda$iaUOfPcKgIJ4Hbxczo1wKLEV55I(StickersActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$jN36uPJIDSDxQkHLg8mXiFzPbzk(StickersActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ohvYuUetgKUAp9Hnpx7Pdppbn0A(View r2) {
        SharedConfig.setSuggestStickers(2);
        ((TextSettingsCell) r2).setValue(LocaleController.getString(R.string.SuggestStickersNone), true);
    }

    public static /* synthetic */ void $r8$lambda$pNbplyab2L7fnvVXaSm_nm23aNU(StickersActivity r0, StickerSetCell r1) {
        r0.lambda$openStickerSetOptions$10(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$rCjDdM67qMAN32fq_ATfRf1ng0Y(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$urGRg4Ro2WZmDZ5p9iqiDSJ2ywo(StickersActivity r0, int r1, ArrayList r2) {
        r0.whenReordered(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wxX0Ae5TG0YgDmKrrgxBRP0HxAA(StickersActivity r0) {
        r0.lambda$sendReorder$6();
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m19294$$Nest$fgetlistView(StickersActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$mprocessSelectionMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19295$$Nest$mprocessSelectionMenu(StickersActivity r0, int r1) {
        r0.processSelectionMenu(r1);
    }

    public StickersActivity(int r2, ArrayList<TLRPC.TL_messages_stickerSet> r3) {
        this.loadingFeaturedStickerSets = new ArrayList();
        this.selectedSets = new HashSet();
        this.sendReorderRunnable = new StickersActivity$$ExternalSyntheticLambda11(this);
        this.currentType = r2;
        this.frozenEmojiPacks = r3;
    }

    public static /* synthetic */ int access$000(StickersActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(StickersActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(StickersActivity r0) {
        return r0.currentAccount;
    }

    private CharSequence addStickersBotSpan(String r5) {
        int r1 = r5.indexOf("@stickers");
        if (r1 != (-1)) goto L9;
    L8:
        return r5;
    L9:
        SpannableStringBuilder r2 = new SpannableStringBuilder(r5);     // Catch: Exception -> L6
        r2.setSpan(new AnonymousClass4(this, "@stickers"), r1, r1 + 9, 18);     // Catch: Exception -> L6
        return r2;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private void checkActionMode() {
        int r0 = getSelectedCount();
        boolean r1 = this.actionBar.isActionModeShowed();
        if (r0 <= 0) goto L12;
        checkActionModeIcons();
        this.selectedCountTextView.setNumber(r0, r1);
        if (r1 == true) goto L15;
        this.actionBar.showActionMode();
        this.listView.allowReorder(true);
        if (SharedConfig.stickersReorderingHintUsed == false) goto L9;
        return;
    L9:
        if (this.currentType == 5) goto L17;
        SharedConfig.setStickersReorderingHintUsed(true);
        String r02 = LocaleController.getString(R.string.StickersReorderHint);
        Bulletin.make(this, new ReorderingBulletinLayout(getContext(), r02, null), 3250).show();
        return;
    L17:
        return;
    L15:
        return;
    L12:
        if (r1 == false) goto L18;
        this.actionBar.hideActionMode();
        this.listView.allowReorder(false);
        sendReorder();
        return;
    }

    private void checkActionModeIcons() {
        if (hasSelected() == false) goto L25;
        ArrayList<TLRPC.TL_messages_stickerSet> r0 = this.sets;
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
    L5:
        if (r3 >= r1) goto L14;
        TLRPC.TL_messages_stickerSet r4 = r0.get(r3);
        r3 = r3 + 1;
        TLRPC.TL_messages_stickerSet r42 = r4;
        if (this.selectedSets.contains(Long.valueOf(r42.set.id)) == false) goto L5;
        TLRPC.StickerSet r43 = r42.set;
        if (r43.official == false) goto L5;
        if (r43.emojis == true) goto L5;
        r2 = 8;
    L14:
        if (this.deleteMenuItem.getVisibility() == r2) goto L26;
        this.deleteMenuItem.setVisibility(r2);
        return;
    L26:
        return;
    }

    private void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        MediaDataController r2 = MediaDataController.getInstance(this.currentAccount);
        UniversalRecyclerView r3 = this.listView;
        if (r3 == null) goto L11;
        if (r3.isReorderAllowed() == false) goto L7;
    L14:
        this.featured = new ArrayList(getFeaturedSets());
        int r32 = 0;
        int r5 = 0;
    L15:
        boolean r7 = true;
        if (r5 >= this.featured.size()) goto L21;
        if (this.loadingFeaturedStickerSets.contains(Long.valueOf(this.featured.get(r5).set.id)) == false) goto L20;
        this.featured.remove(r5);
        r5 = r5 - 1;
    L20:
        r5 = r5 + 1;
        goto L15
    L21:
        int r52 = this.featured.size();
        int r6 = r2.getArchivedStickersCount(this.currentType);
        int r22 = r2.getStickerSets(5).size();
        if (this.currentType != 0) goto L40;
        this.featuredRow = r17.size();
        int r8 = R.drawable.msg2_trending;
        String r12 = LocaleController.getString(R.string.FeaturedStickers);
        String r13 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r52 <= 0) goto L26;
        String r53 = LocaleController.formatNumber(r52, ',');
    L27:
        r17.add(UItem.asButton(1, r8, r12, r53));
        if (r6 <= 0) goto L36;
        this.archivedRow = r17.size();
        int r54 = this.currentType;
        if (r54 != 0) goto L32;
        r17.add(UItem.asButton(2, R.drawable.msg2_archived_stickers, LocaleController.getString(R.string.ArchivedStickers), LocaleController.formatNumber(r6, ',')));
        goto L36
    L32:
        if (r54 != 5) goto L34;
        int r55 = R.string.ArchivedEmojiPacks;
    L35:
        r17.add(UItem.asButton(2, LocaleController.getString(r55), LocaleController.formatNumber(r6, ',')));
        goto L36
    L34:
        r55 = R.string.ArchivedMasks;
    L36:
        this.emojiPacksRow = r17.size();
        int r56 = R.drawable.msg2_smile_status;
        String r62 = LocaleController.getString(R.string.Emoji);
        if (r22 <= 0) goto L39;
        r13 = LocaleController.formatNumber(r22, ',');
    L39:
        r17.add(UItem.asSettingsCell(3, r56, r62, r13));
    L52:
        if (this.currentType != 0) goto L59;
        this.reactionsDoubleTapRow = r17.size();
        r17.add(UItem.asSettingsCell(4, R.drawable.msg2_reactions2, LocaleController.getString(R.string.DoubleTapSetting)).onBind(new StickersActivity$$ExternalSyntheticLambda12(this)));
        if (this.currentType != 5) goto L56;
        int r23 = R.string.EmojiBotInfo;
    L57:
        r17.add(UItem.asShadow(addStickersBotSpan(LocaleController.getString(r23))));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.StickersSettings)));
        this.suggestRow = r17.size();
        r17.add(UItem.asSettingsCell(5, LocaleController.getString(R.string.SuggestStickers), suggestStickersName()));
        this.largeEmojiRow = r17.size();
        r17.add(UItem.asCheck(6, LocaleController.getString(R.string.LargeEmoji)).setChecked(SharedConfig.allowBigEmoji));
        this.dynamicPackOrder = r17.size();
        r17.add(UItem.asCheck(7, LocaleController.getString(R.string.DynamicPackOrder)).setChecked(SharedConfig.updateStickersOrderOnSend));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.DynamicPackOrderInfo)));
        goto L59
    L56:
        r23 = R.string.StickersBotInfo;
    L59:
        if (this.currentType != 5) goto L62;
        r17.add(UItem.asCheck(9, LocaleController.getString(R.string.SuggestAnimatedEmoji)).setChecked(SharedConfig.suggestAnimatedEmoji));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.SuggestAnimatedEmojiInfo)));
    L62:
        if (this.sets.size() <= 0) goto L84;
        r18.whiteSectionStart();
        if (this.currentType == 5) goto L70;
        if (this.featured.isEmpty() == false) goto L68;
    L74:
        r18.reorderSectionStart();
        ArrayList<TLRPC.TL_messages_stickerSet> r24 = this.sets;
        int r63 = r24.size();
        int r82 = 0;
    L75:
        if (r82 >= r63) goto L77;
        TLRPC.TL_messages_stickerSet r10 = r24.get(r82);
        r82 = r82 + 1;
        TLRPC.TL_messages_stickerSet r102 = r10;
        r17.add(StickerSetCell.Factory.of(r102).setClickCallback(new StickersActivity$$ExternalSyntheticLambda13(this)).setClickCallback2(new StickersActivity$$ExternalSyntheticLambda14(this)).setChecked(this.selectedSets.contains(Long.valueOf(r102.set.id))));
        goto L75
    L77:
        r18.reorderSectionEnd();
        r18.whiteSectionEnd();
        int r25 = this.currentType;
        if (r25 == 1) goto L81;
        if (r25 == 5) goto L81;
        r17.add(UItem.asShadow(null));
    L81:
        if (r25 != 1) goto L84;
        r17.add(UItem.asShadow(LocaleController.getString(R.string.MasksInfo)));
        goto L84
    L68:
        if (this.currentType != 0) goto L74;
    L70:
        if (this.currentType != 5) goto L72;
        int r26 = R.string.ChooseStickerMyEmojiPacks;
    L73:
        r17.add(UItem.asHeader(LocaleController.getString(r26)));
        goto L74
    L72:
        r26 = R.string.ChooseStickerMyStickerSets;
    L84:
        if (this.featured.size() <= 3) goto L86;
        this.featured = new ArrayList(this.featured.subList(0, 3));
    L88:
        if (this.currentType == 5) goto L90;
    L103:
        int r27 = this.currentType;
        if (r27 != 5) goto L115;
        if (r27 != 5) goto L107;
        int r28 = R.string.EmojiBotInfo;
    L108:
        r17.add(UItem.asShadow(addStickersBotSpan(LocaleController.getString(r28))));
        return;
    L107:
        r28 = R.string.StickersBotInfo;
        goto L108
    L115:
        return;
    L90:
        if (this.featured.isEmpty() == true) goto L103;
        if (this.sets.size() <= 0) goto L95;
        r17.add(UItem.asShadow(null));
    L95:
        if (this.currentType != 5) goto L97;
        int r29 = R.string.FeaturedEmojiPacks;
    L98:
        r17.add(UItem.asHeader(LocaleController.getString(r29)));
        ArrayList<TLRPC.StickerSetCovered> r210 = this.featured;
        int r57 = r210.size();
    L99:
        if (r32 >= r57) goto L101;
        TLRPC.StickerSetCovered r64 = r210.get(r32);
        r32 = r32 + 1;
        TLRPC.StickerSetCovered r65 = r64;
        r17.add(FeaturedStickerSetCell2.Factory.of(r65).setClickCallback(new StickersActivity$$ExternalSyntheticLambda15(this)).setLocked(this.loadingFeaturedStickerSets.contains(Long.valueOf(r65.set.id))));
        goto L99
    L101:
        if (r7 == false) goto L103;
        r17.add(UItem.asButton(8, R.drawable.msg2_trending, LocaleController.getString(R.string.ShowMoreEmojiPacks)).accent());
        goto L103
    L97:
        r29 = R.string.FeaturedStickers;
        goto L98
    L86:
        r7 = false;
        goto L88
    L26:
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L27
    L40:
        if (r6 <= 0) goto L52;
        this.archivedRow = r17.size();
        int r211 = this.currentType;
        if (r211 != 0) goto L44;
        r17.add(UItem.asButton(2, R.drawable.msg2_archived_stickers, LocaleController.getString(R.string.ArchivedStickers), LocaleController.formatNumber(r6, ',')));
    L49:
        if (this.currentType != 1) goto L52;
        r17.add(UItem.asShadow(LocaleController.getString(R.string.ArchivedMasksInfo)));
        goto L52
    L44:
        if (r211 != 5) goto L46;
        int r212 = R.string.ArchivedEmojiPacks;
    L47:
        r17.add(UItem.asButton(2, LocaleController.getString(r212), LocaleController.formatNumber(r6, ',')));
        goto L49
    L46:
        r212 = R.string.ArchivedMasks;
        goto L47
    L7:
        if (this.needReorder == true) goto L14;
        if (this.activeReorderingRequests > 0) goto L14;
    L11:
        if (this.currentType != 5) goto L13;
        ArrayList<TLRPC.TL_messages_stickerSet> r33 = new ArrayList(MessagesController.getInstance(this.currentAccount).filterPremiumStickers(r2.getStickerSets(this.currentType)));
        this.frozenEmojiPacks = r33;
        this.sets = r33;
        goto L14
    L13:
        this.sets = new ArrayList(MessagesController.getInstance(this.currentAccount).filterPremiumStickers(r2.getStickerSets(this.currentType)));
        goto L14
    }

    private ArrayList<TLRPC.StickerSetCovered> getFeaturedSets() {
        MediaDataController r0 = MediaDataController.getInstance(this.currentAccount);
        if (this.currentType != 5) goto L15;
        ArrayList<TLRPC.StickerSetCovered> r5 = new ArrayList(r0.getFeaturedEmojiSets());
        int r2 = 0;
    L6:
        if (r2 >= r5.size()) goto L13;
        if (r5.get(r2) != null) goto L10;
    L11:
        r5.remove(r2);
        r2 = r2 - 1;
    L12:
        r2 = r2 + 1;
        goto L6
    L10:
        if (r0.isStickerPackInstalled(r5.get(r2).set.id, false) == false) goto L12;
    L13:
        return r5;
    L15:
        return r0.getFeaturedStickerSets();
    }

    private String getLinkForSet(TLRPC.TL_messages_stickerSet r4) {
        Locale r0 = Locale.US;
        StringBuilder r1 = new StringBuilder("https://");
        r1.append(MessagesController.getInstance(this.currentAccount).linkPrefix);
        r1.append("/");
        if (r4.set.emojis == false) goto L5;
        String r3 = "addemoji";
    L6:
        r1.append(r3);
        r1.append("/%s");
        return String.format(r0, r1.toString(), new Object[]{r4.set.short_name});
    L5:
        r3 = "addstickers";
        goto L6
    }

    private /* synthetic */ void lambda$new$5() {
        sendReorder();
    }

    private /* synthetic */ void lambda$openStickerSetOptions$10(StickerSetCell r1) {
        toggleSelected(r1);
    }

    private /* synthetic */ void lambda$openStickerSetOptions$11(TLRPC.TL_messages_stickerSet r3) {
        Intent r0 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L4
        r0.setType("text/plain");     // Catch: Exception -> L4
        r0.putExtra("android.intent.extra.TEXT", getLinkForSet(r3));     // Catch: Exception -> L4
        getParentActivity().startActivityForResult(Intent.createChooser(r0, LocaleController.getString(R.string.StickersShare)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$openStickerSetOptions$12(TLRPC.TL_messages_stickerSet r9) {
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getParentActivity(), r9, 0, this, true, true);
    }

    private /* synthetic */ void lambda$openStickerSetOptions$8(TLRPC.TL_messages_stickerSet r9) {
        MediaDataController r1 = MediaDataController.getInstance(this.currentAccount);
        Activity r2 = getParentActivity();
        if (r9.set.archived == true) goto L6;
        int r0 = 1;
    L7:
        r1.toggleStickerSet(r2, r9, r0, this, true, true);
        return;
    L6:
        r0 = 2;
        goto L7
    }

    private /* synthetic */ void lambda$openStickerSetOptions$9(TLRPC.TL_messages_stickerSet r3) {
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", getLinkForSet(r3)));     // Catch: Exception -> L4
        BulletinFactory.createCopyLinkBulletin(this).show();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$processSelectionMenu$13(ArrayList r7, int r8, AlertDialog r9, int r10) {
        clearSelected();
        MediaDataController r0 = MediaDataController.getInstance(this.currentAccount);
        int r2 = this.currentType;
        int r92 = 1;
        if (r8 != 1) goto L5;
        r92 = 0;
    L5:
        r0.toggleStickerSets(r7, r2, r92, this, true);
    }

    private /* synthetic */ void lambda$sendReorder$6() {
        this.activeReorderingRequests--;
    }

    private /* synthetic */ void lambda$sendReorder$7(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StickersActivity$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ int lambda$whenReordered$4(TLRPC.TL_messages_stickerSet r2, TLRPC.TL_messages_stickerSet r3) {
        int r22 = this.sets.indexOf(r2);
        int r1 = this.sets.indexOf(r3);
        if (r22 < 0) goto L7;
        if (r1 >= 0) goto L6;
        return 0;
    L6:
        return r22 - r1;
    L7:
        return 0;
    }

    private void onClick(UItem r10, View r11, int r12, float r13, float r14) {
        Object r3 = r10.object;
        if ((r3 instanceof TLRPC.TL_messages_stickerSet) == false) goto L21;
        TLRPC.TL_messages_stickerSet r4 = (TLRPC.TL_messages_stickerSet) r3;
        if (this.selectedSets.isEmpty() == false) goto L19;
        ArrayList<TLRPC.Document> r0 = r4.documents;
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.isEmpty() == true) goto L79;
        TLRPC.StickerSet r02 = r4.set;
        if (r02 != null) goto L14;
    L17:
        showDialog(new StickersAlert(getParentActivity(), this, null, r4, null, false));
        return;
    L14:
        if (r02.emojis == false) goto L17;
        ArrayList r03 = new ArrayList();
        TLRPC.TL_inputStickerSetID r1 = new TLRPC.TL_inputStickerSetID();
        TLRPC.StickerSet r32 = r4.set;
        r1.id = r32.id;
        r1.access_hash = r32.access_hash;
        r03.add(r1);
        showDialog(new EmojiPacksAlert(this, getParentActivity(), getResourceProvider(), r03));
        return;
    L79:
        return;
    L19:
        toggleSelected((StickerSetCell) r11);
        return;
    L21:
        boolean r6 = true;
        if ((r3 instanceof TLRPC.StickerSetCovered) == false) goto L29;
        TLRPC.TL_inputStickerSetID r04 = new TLRPC.TL_inputStickerSetID();
        TLRPC.StickerSet r15 = ((TLRPC.StickerSetCovered) r3).set;
        r04.id = r15.id;
        r04.access_hash = r15.access_hash;
        if (this.currentType != 5) goto L27;
        ArrayList r16 = new ArrayList(1);
        r16.add(r04);
        showDialog(new EmojiPacksAlert(this, getParentActivity(), getResourceProvider(), r16));
        return;
    L27:
        showDialog(new StickersAlert(getParentActivity(), this, r04, null, null, false));
        return;
    L29:
        int r42 = 0;
        switch(r10.id) {
            case 1: goto L59;
            case 2: goto L56;
            case 3: goto L54;
            case 4: goto L52;
            case 5: goto L38;
            case 6: goto L36;
            case 7: goto L34;
            case 8: goto L59;
            case 9: goto L32;
            default: goto L78;
        };
    L32:
        SharedConfig.toggleSuggestAnimatedEmoji();
        ((TextCheckCell) r11).setChecked(SharedConfig.suggestAnimatedEmoji);
        return;
    L34:
        SharedConfig.toggleUpdateStickersOrderOnSend();
        ((TextCheckCell) r11).setChecked(SharedConfig.updateStickersOrderOnSend);
        return;
    L36:
        SharedConfig.toggleBigEmoji();
        ((TextCheckCell) r11).setChecked(SharedConfig.allowBigEmoji);
        return;
    L38:
        ItemOptions r05 = ItemOptions.makeOptions(this, r11);
        if (SharedConfig.suggestStickers != 0) goto L41;
        boolean r2 = true;
    L42:
        ItemOptions r06 = r05.addChecked(r2, LocaleController.getString(R.string.SuggestStickersAll), new StickersActivity$$ExternalSyntheticLambda7(r11));
        if (SharedConfig.suggestStickers != 1) goto L45;
        boolean r22 = true;
    L46:
        ItemOptions r07 = r06.addChecked(r22, LocaleController.getString(R.string.SuggestStickersInstalled), new StickersActivity$$ExternalSyntheticLambda8(r11));
        if (SharedConfig.suggestStickers == 2) goto L50;
        r6 = false;
    L50:
        r07.addChecked(r6, LocaleController.getString(R.string.SuggestStickersNone), new StickersActivity$$ExternalSyntheticLambda9(r11)).show();
        return;
    L45:
        r22 = false;
        goto L46
    L41:
        r2 = false;
        goto L42
    L52:
        presentFragment(new ReactionsDoubleTapManageActivity());
        return;
    L54:
        presentFragment(new StickersActivity(5, null));
        return;
    L56:
        presentFragment(new ArchivedStickersActivity(this.currentType));
        return;
    L78:
        return;
    L59:
        if (this.currentType != 5) goto L72;
        ArrayList r08 = new ArrayList();
        ArrayList<TLRPC.StickerSetCovered> r17 = getFeaturedSets();
        if (r17 != null) goto L63;
    L70:
        MediaDataController.getInstance(this.currentAccount).markFeaturedStickersAsRead(true, true);
        showDialog(new EmojiPacksAlert(this, getParentActivity(), getResourceProvider(), r08));
        return;
    L63:
        if (r42 >= r17.size()) goto L70;
        TLRPC.StickerSetCovered r33 = r17.get(r42);
        if (r33 == null) goto L69;
        if (r33.set == null) goto L69;
        TLRPC.TL_inputStickerSetID r5 = new TLRPC.TL_inputStickerSetID();
        TLRPC.StickerSet r34 = r33.set;
        r5.id = r34.id;
        r5.access_hash = r34.access_hash;
        r08.add(r5);
    L69:
        r42 = r42 + 1;
        goto L63
    L72:
        TrendingStickersAlert r18 = new TrendingStickersAlert(getContext(), this, new TrendingStickersLayout(getContext(), new AnonymousClass3(this)), null);
        this.trendingStickersAlert = r18;
        r18.show();
    }

    private void onFeaturedAddClick(View r8) {
        FeaturedStickerSetCell2 r82 = (FeaturedStickerSetCell2) r8.getParent();
        TLRPC.StickerSetCovered r2 = r82.getStickerSet();
        if (this.loadingFeaturedStickerSets.contains(Long.valueOf(r2.set.id)) == false) goto L5;
        return;
    L5:
        this.loadingFeaturedStickerSets.add(Long.valueOf(r2.set.id));
        r82.setDrawProgress(true, true);
        boolean r83 = r82.isInstalled();
        int r0 = this.currentAccount;
        if (r83 == false) goto L9;
        MediaDataController.getInstance(r0).toggleStickerSet(getParentActivity(), r2, 0, this, false, false);
        return;
    L9:
        MediaDataController.getInstance(r0).toggleStickerSet(getParentActivity(), r2, 2, this, false, false);
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (this.selectedSets.isEmpty() == true) goto L6;
        return false;
    L6:
        if ((r1.object instanceof TLRPC.TL_messages_stickerSet) == false) goto L9;
        toggleSelected((StickerSetCell) r2);
        return true;
    L9:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5, types: [org.telegram.tgnet.TLRPC$StickerSetCovered] */
    /* JADX WARN: Type inference failed for: r12v8 */
    private void onStickerSetButtonClick(View r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        if ((r12.getParent() instanceof ViewGroup) == false) goto L48;
        ViewGroup r0 = (ViewGroup) r12.getParent();
        if ((r0.getParent() instanceof StickerSetCell) == false) goto L49;
        StickerSetCell r02 = (StickerSetCell) r0.getParent();
        TLRPC.TL_messages_stickerSet r3 = r02.getStickersSet();
        if (r3 != null) goto L12;
        return;
    L12:
        if (r3.set == null) goto L50;
        int r2 = 0;
        if (r02.addButtonView != r12) goto L36;
        ArrayList<TLRPC.StickerSetCovered> r122 = getMediaDataController().getFeaturedEmojiSets();
    L18:
        if (r2 >= r122.size()) goto L23;
        if (r3.set.id == r122.get(r2).set.id) goto L21;
        r2 = r2 + 1;
        goto L18
    L21:
        ?? r123 = r122.get(r2);
    L24:
        if (r123 != 0) goto L26;
    L29:
        MediaDataController r4 = MediaDataController.getInstance(this.currentAccount);
        Activity r5 = getParentActivity();
        if (r123 != 0) goto L32;
        TLRPC.TL_messages_stickerSet r6 = r3;
    L33:
        r4.toggleStickerSet(r5, r6, 2, this, false, false);
        return;
    L32:
        r6 = r123;
        goto L33
    L26:
        if (this.loadingFeaturedStickerSets.contains(Long.valueOf(r123.set.id)) == true) goto L51;
        this.loadingFeaturedStickerSets.add(Long.valueOf(r123.set.id));
        goto L29
    L51:
        return;
    L23:
        r123 = 0;
        goto L24
    L36:
        if (r02.removeButtonView != r12) goto L40;
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getParentActivity(), r3, 0, this, false, true);
        return;
    L40:
        if (r02.premiumButtonView != r12) goto L47;
        showDialog(new PremiumFeatureBottomSheet(this, 11, false));
        return;
    L47:
        return;
    L50:
        return;
    L49:
        return;
    }

    private void openStickerSetOptions(View r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        if ((r12.getParent() instanceof StickerSetCell) == false) goto L9;
        StickerSetCell r122 = (StickerSetCell) r12.getParent();
        TLRPC.TL_messages_stickerSet r0 = r122.getStickersSet();
        ItemOptions.makeOptions(this, r122).add(R.drawable.msg_archive, LocaleController.getString(R.string.StickersHide), new StickersActivity$$ExternalSyntheticLambda17(this, r0)).addIf(!r0.set.official, R.drawable.msg_link, LocaleController.getString(R.string.StickersCopy), new StickersActivity$$ExternalSyntheticLambda18(this, r0)).add(R.drawable.msg_reorder, LocaleController.getString(R.string.StickersReorder), new StickersActivity$$ExternalSyntheticLambda19(this, r122)).addIf(!r0.set.official, R.drawable.msg_share, LocaleController.getString(R.string.StickersShare), new StickersActivity$$ExternalSyntheticLambda20(this, r0)).addIf(!r0.set.official, R.drawable.msg_delete, LocaleController.getString(R.string.StickersRemove), true, new StickersActivity$$ExternalSyntheticLambda21(this, r0)).setMinWidth(190).show();
        return;
    }

    private void processSelectionMenu(int r10) {
        int r1 = 0;
        if (r10 != 2) goto L16;
        StringBuilder r102 = new StringBuilder();
        int r0 = this.sets.size();
    L5:
        if (r1 >= r0) goto L13;
        TLRPC.TL_messages_stickerSet r2 = this.sets.get(r1);
        if (this.selectedSets.contains(Long.valueOf(r2.set.id)) == false) goto L12;
        if (r102.length() == 0) goto L11;
        r102.append("\n");
    L11:
        r102.append(getLinkForSet(r2));
    L12:
        r1 = r1 + 1;
        goto L5
    L13:
        String r4 = r102.toString();
        ShareAlert r103 = ShareAlert.createShareAlert(this.fragmentView.getContext(), null, r4, false, r4, false);
        r103.setDelegate(new AnonymousClass5(this));
        r103.show();
        return;
    L16:
        if (r10 == 0) goto L18;
        if (r10 == 1) goto L18;
        return;
    L18:
        ArrayList r22 = new ArrayList(this.selectedSets.size());
        int r3 = this.sets.size();
        int r42 = 0;
    L19:
        if (r42 >= r3) goto L24;
        TLRPC.TL_messages_stickerSet r5 = this.sets.get(r42);
        if (this.selectedSets.contains(Long.valueOf(r5.set.id)) == false) goto L23;
        r22.add(r5.set);
    L23:
        r42 = r42 + 1;
        goto L19
    L24:
        int r32 = r22.size();
        if (r32 == 0) goto L54;
        if (r32 == 1) goto L37;
        AlertDialog.Builder r43 = new AlertDialog.Builder(getParentActivity());
        if (r10 != 1) goto L30;
        r43.setTitle(LocaleController.formatString(R.string.DeleteStickerSetsAlertTitle, new Object[]{LocaleController.formatPluralString("StickerSets", r32, new Object[0])}));
        r43.setMessage(LocaleController.formatString(R.string.DeleteStickersAlertMessage, new Object[]{Integer.valueOf(r32)}));
        String r12 = LocaleController.getString(R.string.Delete);
    L31:
        r43.setPositiveButton(r12, new StickersActivity$$ExternalSyntheticLambda16(this, r22, r10));
        r43.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r13 = r43.create();
        showDialog(r13);
        if (r10 != 1) goto L55;
        TextView r9 = (TextView) r13.getButton(-1);
        if (r9 == null) goto L56;
        r9.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L56:
        return;
    L55:
        return;
    L30:
        r43.setTitle(LocaleController.formatString(R.string.ArchiveStickerSetsAlertTitle, new Object[]{LocaleController.formatPluralString("StickerSets", r32, new Object[0])}));
        r43.setMessage(LocaleController.formatString(R.string.ArchiveStickersAlertMessage, new Object[]{Integer.valueOf(r32)}));
        r12 = LocaleController.getString(R.string.Archive);
        goto L31
    L37:
        int r02 = this.sets.size();
    L38:
        if (r1 >= r02) goto L43;
        TLRPC.TL_messages_stickerSet r23 = this.sets.get(r1);
        if (this.selectedSets.contains(Long.valueOf(r23.set.id)) == true) goto L41;
        r1 = r1 + 1;
        goto L38
    L41:
        processSelectionOption(r10, r23);
    L43:
        clearSelected();
        return;
    }

    private void processSelectionOption(int r8, TLRPC.TL_messages_stickerSet r9) {
        int r3 = 1;
        if (r8 != 0) goto L11;
        MediaDataController r0 = MediaDataController.getInstance(this.currentAccount);
        Activity r1 = getParentActivity();
        if (r9.set.archived == false) goto L8;
        r3 = 2;
    L8:
        r0.toggleStickerSet(r1, r9, r3, this, true, true);
        return;
    L11:
        if (r8 != 1) goto L14;
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getParentActivity(), r9, 0, this, true, true);
        return;
    L14:
        if (r8 != 2) goto L20;
        Intent r02 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L17
        r02.setType("text/plain");     // Catch: Exception -> L17
        r02.putExtra("android.intent.extra.TEXT", getLinkForSet(r9));     // Catch: Exception -> L17
        getParentActivity().startActivityForResult(Intent.createChooser(r02, LocaleController.getString(R.string.StickersShare)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L17
        return;
    L17:
        e = move-exception;
        FileLog.e(e);
        return;
    L20:
        if (r8 != 3) goto L26;
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", getLinkForSet(r9)));     // Catch: Exception -> L23
        BulletinFactory.createCopyLinkBulletin(this).show();     // Catch: Exception -> L23
        return;
    L23:
        e = move-exception;
        FileLog.e(e);
        return;
    L26:
        if (r8 != 4) goto L35;
        toggleSelected(r9);
        return;
    }

    private void sendReorder() {
        if (this.needReorder == false) goto L23;
        int r0 = 0;
        this.needReorder = false;
        MediaDataController.getInstance(this.currentAccount).calcNewHash(this.currentType);
        this.activeReorderingRequests++;
        TLRPC.TL_messages_reorderStickerSets r1 = new TLRPC.TL_messages_reorderStickerSets();
        int r3 = this.currentType;
        if (r3 != 1) goto L8;
        boolean r4 = true;
    L9:
        r1.masks = r4;
        if (r3 != 5) goto L12;
        boolean r32 = true;
    L13:
        r1.emojis = r32;
    L15:
        if (r0 >= this.sets.size()) goto L17;
        r1.order.add(Long.valueOf(this.sets.get(r0).set.id));
        r0 = r0 + 1;
        goto L15
    L17:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StickersActivity$$ExternalSyntheticLambda0(this));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(this.currentType), Boolean.TRUE});
        if (SharedConfig.updateStickersOrderOnSend == false) goto L22;
        SharedConfig.toggleUpdateStickersOrderOnSend();
        BulletinFactory.of(this).createSimpleBulletin(R.raw.filter_reorder, LocaleController.getString(R.string.DynamicPackOrderOff), LocaleController.getString(R.string.DynamicPackOrderOffInfo)).show();
        this.listView.adapter.update(true);
        return;
    L22:
        return;
    L12:
        r32 = false;
        goto L13
    L8:
        r4 = false;
        goto L9
    }

    private void setQuickReactionImage(View r8) {
        if ((r8 instanceof TextSettingsCell) == false) goto L18;
        TextSettingsCell r82 = (TextSettingsCell) r8;
        String r0 = MediaDataController.getInstance(this.currentAccount).getDoubleTapReaction();
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.startsWith("animated_") == true) goto L16;
        TLRPC.TL_availableReaction r5 = MediaDataController.getInstance(this.currentAccount).getReactionsMap().get(r0);
        if (r5 == null) goto L20;
        SvgHelper.SvgDrawable r3 = DocumentObject.getSvgThumb(r5.static_icon.thumbs, Theme.key_windowBackgroundGray, 1.0f);
        r82.getValueBackupImageView().getImageReceiver().setImage(ImageLocation.getForDocument(r5.center_icon), "100_100_lastreactframe", r3, "webp", r5, 1);
        return;
    L20:
        return;
    L16:
        long r02 = Long.parseLong(r0.substring(9));     // Catch: Exception -> L15
        AnimatedEmojiDrawable r7 = AnimatedEmojiDrawable.make(this.currentAccount, 2, r02);     // Catch: Exception -> L15
        r82.getValueBackupImageView().setAnimatedEmojiDrawable(r7);     // Catch: Exception -> L15
        return;
    L21:
        return;
    }

    private String suggestStickersName() {
        int r1 = SharedConfig.suggestStickers;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L9;
        return LocaleController.getString(R.string.SuggestStickersNone);
    L9:
        return LocaleController.getString(R.string.SuggestStickersInstalled);
    L11:
        return LocaleController.getString(R.string.SuggestStickersAll);
    }

    private void toggleSelected(TLRPC.TL_messages_stickerSet r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        boolean r0 = this.selectedSets.contains(Long.valueOf(r5.set.id));
        HashSet<Long> r1 = this.selectedSets;
        if (r0 == false) goto L7;
        r1.remove(Long.valueOf(r5.set.id));
    L8:
        this.listView.adapter.update(true);
        checkActionMode();
        return;
    L7:
        r1.add(Long.valueOf(r5.set.id));
        goto L8
    }

    private void toggleSelected(StickerSetCell r5) {
        TLRPC.TL_messages_stickerSet r0 = r5.getStickersSet();
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r1 = this.selectedSets.contains(Long.valueOf(r0.set.id));
        HashSet<Long> r2 = this.selectedSets;
        if (r1 == false) goto L8;
        r2.remove(Long.valueOf(r0.set.id));
        r5.setChecked(false, true);
    L9:
        this.listView.adapter.update(true);
        checkActionMode();
        return;
    L8:
        r2.add(Long.valueOf(r0.set.id));
        r5.setChecked(true, true);
        goto L9
    }

    private void whenReordered(int r5, ArrayList<UItem> r6) {
        ArrayList<TLRPC.TL_messages_stickerSet> r52 = new ArrayList();
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        UItem r2 = r6.get(r1);
        r1 = r1 + 1;
        Object r22 = r2.object;
        if ((r22 instanceof TLRPC.TL_messages_stickerSet) == false) goto L3;
        r52.add((TLRPC.TL_messages_stickerSet) r22);
        goto L3
    L7:
        this.sets = r52;
        this.needReorder = true;
        Collections.sort(MediaDataController.getInstance(this.currentAccount).getStickerSets(this.currentType), new StickersActivity$$ExternalSyntheticLambda10(this));
        AndroidUtilities.cancelRunOnUIThread(this.sendReorderRunnable);
        AndroidUtilities.runOnUIThread(this.sendReorderRunnable, 1000);
    }

    public void clearSelected() {
        this.selectedSets.clear();
        this.listView.adapter.update(true);
        checkActionMode();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"ClickableViewAccessibility"})
    public View createView(Context r13) {
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
        this.actionBar.setTitle(LocaleController.getString(R.string.StickersName));
    L9:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r02 = this.actionBar.createActionMode();
        NumberTextView r4 = new NumberTextView(r02.getContext());
        this.selectedCountTextView = r4;
        r4.setTextSize(18);
        this.selectedCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        r02.addView(this.selectedCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 72, 0, 0, 0));
        this.selectedCountTextView.setOnTouchListener(new StickersActivity$$ExternalSyntheticLambda1());
        this.shareMenuItem = r02.addItemWithWidth(2, R.drawable.msg_share, AndroidUtilities.dp(54.0f));
        this.archiveMenuItem = r02.addItemWithWidth(0, R.drawable.msg_archive, AndroidUtilities.dp(54.0f));
        this.deleteMenuItem = r02.addItemWithWidth(1, R.drawable.msg_delete, AndroidUtilities.dp(54.0f));
        if (this.currentType != 5) goto L14;
        ArrayList<TLRPC.TL_messages_stickerSet> r03 = this.frozenEmojiPacks;
        if (r03 == null) goto L14;
        this.sets = r03;
    L15:
        this.featured = getFeaturedSets();
        FrameLayout r04 = new FrameLayout(r13);
        this.fragmentView = r04;
        r04.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        UniversalRecyclerView r2 = new UniversalRecyclerView(this, new StickersActivity$$ExternalSyntheticLambda2(this), new StickersActivity$$ExternalSyntheticLambda3(this), new StickersActivity$$ExternalSyntheticLambda4(this));
        this.listView = r2;
        r2.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setFocusable(true);
        this.listView.setTag(7);
        this.listView.listenReorder(new StickersActivity$$ExternalSyntheticLambda5(this));
        AnonymousClass2 r22 = new AnonymousClass2(this, r13);
        this.layoutManager = r22;
        r22.setOrientation(1);
        this.listView.setLayoutManager(this.layoutManager);
        r04.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        return this.fragmentView;
    L14:
        this.sets = new ArrayList(MessagesController.getInstance(this.currentAccount).filterPremiumStickers(MediaDataController.getInstance(this.currentAccount).getStickerSets(this.currentType)));
        goto L15
    L5:
        if (r0 != 1) goto L7;
        this.actionBar.setTitle(LocaleController.getString(R.string.Masks));
        goto L9
    L7:
        if (r0 != 5) goto L9;
        this.actionBar.setTitle(LocaleController.getString(R.string.Emoji));
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.stickersDidLoad) goto L10;
        if (((Integer) r5[0]).intValue() != this.currentType) goto L7;
        this.loadingFeaturedStickerSets.clear();
    L7:
        this.listView.adapter.update(true);
        return;
    L10:
        if (r3 != NotificationCenter.featuredStickersDidLoad) goto L12;
    L20:
        this.listView.adapter.update(true);
        return;
    L12:
        if (r3 == NotificationCenter.featuredEmojiDidLoad) goto L20;
        if (r3 == NotificationCenter.archivedStickersCountDidLoad) goto L17;
        return;
    L17:
        if (((Integer) r5[0]).intValue() != this.currentType) goto L23;
        this.listView.adapter.update(true);
        return;
    }

    public int getSelectedCount() {
        return this.selectedSets.size();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        int r21 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{StickerSetCell.class, TextSettingsCell.class, TextCheckCell.class}, null, null, null, r21));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        ActionBar r3 = this.actionBar;
        int r4 = ThemeDescription.FLAG_AB_AM_ITEMSCOLOR;
        int r20 = Theme.key_actionBarActionModeDefaultIcon;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_BACKGROUND, null, null, null, null, Theme.key_actionBarActionModeDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_TOPBACKGROUND, null, null, null, null, Theme.key_actionBarActionModeDefaultTop));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultSelector));
        r1.add(new ThemeDescription(this.selectedCountTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r35 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r35));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r35));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerSetCell.class}, new String[]{"textView"}, null, null, null, r35));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerSetCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{StickerSetCell.class}, new String[]{"optionsButton"}, null, null, null, Theme.key_stickers_menuSelector));
        int r19 = Theme.key_stickers_menu;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerSetCell.class}, new String[]{"optionsButton"}, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerSetCell.class}, new String[]{"reorderButton"}, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOX, new Class[]{StickerSetCell.class}, new String[]{"checkBox"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{StickerSetCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxCheck));
        TrendingStickersAlert r0 = this.trendingStickersAlert;
        if (r0 == null) goto L5;
        r1.addAll(r0.getThemeDescriptions());
    L5:
        return r1;
    }

    public boolean hasSelected() {
        if (this.selectedSets.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.selectedSets.isEmpty() == true) goto L9;
        if (r2 == false) goto L10;
        clearSelected();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        MediaDataController.getInstance(this.currentAccount).checkStickers(this.currentType);
        int r0 = this.currentType;
        if (r0 != 0) goto L6;
        MediaDataController.getInstance(this.currentAccount).checkFeaturedStickers();
        MediaDataController.getInstance(this.currentAccount).checkStickers(1);
        MediaDataController.getInstance(this.currentAccount).checkStickers(5);
    L8:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.archivedStickersCountDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.featuredStickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        return true;
    L6:
        if (r0 != 6) goto L8;
        MediaDataController.getInstance(this.currentAccount).checkFeaturedEmoji();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.featuredEmojiDidLoad);
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.currentType != 6) goto L5;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.featuredEmojiDidLoad);
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.archivedStickersCountDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.featuredStickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"NotifyDataSetChanged"})
    public void onResume() {
        super.onResume();
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L6;
        r1.adapter.update(true);
        return;
    }
}
