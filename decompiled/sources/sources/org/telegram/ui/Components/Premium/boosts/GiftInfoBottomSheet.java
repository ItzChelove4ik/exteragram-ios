package org.telegram.ui.Components.Premium.boosts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet;
import org.telegram.ui.Components.Premium.boosts.adapters.GiftInfoAdapter;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public class GiftInfoBottomSheet extends BottomSheetWithRecyclerListView {
    private GiftInfoAdapter adapter;
    private final TLRPC.TL_payments_checkedGiftCode giftCode;
    private final boolean isUnused;
    private String slug;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.GiftInfoBottomSheet$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements Bulletin.Delegate {
        final /* synthetic */ GiftInfoBottomSheet this$0;

        public AnonymousClass1(GiftInfoBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return AndroidUtilities.statusBarHeight;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.GiftInfoBottomSheet$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends GiftInfoAdapter {
        final /* synthetic */ GiftInfoBottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$3KEzd46P82ggmhu97MLdtc5Tz5E(AnonymousClass2 r0) {
            r0.lambda$afterCodeApplied$0();
        }

        public AnonymousClass2(GiftInfoBottomSheet r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$afterCodeApplied$0() {
            PremiumPreviewBottomSheet r0 = new PremiumPreviewBottomSheet(this.this$0.getBaseFragment(), GiftInfoBottomSheet.access$100(this.this$0), null, null, null, GiftInfoBottomSheet.access$200(this.this$0)).setAnimateConfetti(true).setOutboundGift(true);
            this.this$0.getBaseFragment().showDialog(r0);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.adapters.GiftInfoAdapter
        public void afterCodeApplied() {
            AndroidUtilities.runOnUIThread(new GiftInfoBottomSheet$2$$ExternalSyntheticLambda0(this), 200);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.adapters.GiftInfoAdapter
        public void dismiss() {
            this.this$0.dismiss();
        }

        @Override // org.telegram.ui.Components.Premium.boosts.adapters.GiftInfoAdapter
        public void onHiddenLinkClicked() {
            if (GiftInfoBottomSheet.m12110$$Nest$fgetslug(this.this$0) == null) goto L7;
            if (GiftInfoBottomSheet.m12110$$Nest$fgetslug(this.this$0).isEmpty() == true) goto L7;
        L9:
            String r0 = LocaleController.getString(R.string.BoostingOnlyRecipientCode);
        L10:
            GiftInfoBottomSheet r4 = this.this$0;
            BulletinFactory.of(r4.container, GiftInfoBottomSheet.access$000(r4)).createSimpleBulletin(R.raw.chats_infotip, r0).show(true);
            return;
        L7:
            if (GiftInfoBottomSheet.m12109$$Nest$fgetgiftCode(this.this$0).to_id != (-1)) goto L9;
            r0 = LocaleController.getString(R.string.BoostingOnlyGiveawayCreatorSeeLink);
            goto L10
        }

        @Override // org.telegram.ui.Components.Premium.boosts.adapters.GiftInfoAdapter
        public void onObjectClicked(TLObject r4) {
            dismiss();
            if ((r4 instanceof TLRPC.Chat) == false) goto L7;
            this.this$0.getBaseFragment().presentFragment(ChatActivity.of(-((TLRPC.Chat) r4).id));
            return;
        L7:
            if ((r4 instanceof TLRPC.User) == false) goto L10;
            this.this$0.getBaseFragment().presentFragment(ChatActivity.of(((TLRPC.User) r4).id));
            return;
        L10:
            Bundle r42 = new Bundle();
            r42.putLong("chat_id", -DialogObject.getPeerDialogId(GiftInfoBottomSheet.m12109$$Nest$fgetgiftCode(this.this$0).from_id));
            r42.putInt("message_id", GiftInfoBottomSheet.m12109$$Nest$fgetgiftCode(this.this$0).giveaway_msg_id);
            ChatActivity r0 = new ChatActivity(r42);
            this.this$0.getBaseFragment().presentFragment(r0);
        }
    }

    public static /* synthetic */ void $r8$lambda$Y7glcthtbPhwh6Sao6m83JvUN7w(AtomicBoolean r0, Browser.Progress r1, TLRPC.TL_error r2) {
        if (r0.get() == true) goto L9;
        if (r1 == null) goto L8;
        r1.end();
        return;
    L8:
        return;
    }

    public static /* synthetic */ void $r8$lambda$YUYLW_38Gm6o4KeCIm9DBgReAdw(AtomicBoolean r6, BaseFragment r7, String r8, Browser.Progress r9, TLRPC.TL_payments_checkedGiftCode r10) {
        if (r6.get() == false) goto L6;
        return;
    L6:
        if (r7.getParentActivity() != null) goto L9;
        return;
    L9:
        if (r10.from_id != null) goto L23;
        TLRPC.TL_premiumGiftOption r62 = new TLRPC.TL_premiumGiftOption();
        r62.months = r10.months;
        if ((r7 instanceof ChatActivity) == false) goto L13;
        TLRPC.User r72 = ((ChatActivity) r7).getCurrentUser();
    L14:
        if (r72 != null) goto L16;
    L17:
        r72 = new TLRPC.TL_user();
    L19:
        if (r10.used_date == 0) goto L21;
        boolean r102 = true;
    L22:
        PremiumPreviewGiftLinkBottomSheet.show(r8, r62, r72, r102);
    L24:
        if (r9 == null) goto L27;
        r9.end();
        return;
    L27:
        return;
    L21:
        r102 = false;
        goto L22
    L16:
        if (r72.self == false) goto L19;
    L13:
        r72 = null;
        goto L14
    L23:
        r7.showDialog(new GiftInfoBottomSheet(r7, false, true, r10, r8));
        goto L24
    }

    public static /* synthetic */ void $r8$lambda$s63Gb3e5i8HfeBrRuje953UHWAA(AtomicBoolean r1) {
        r1.set(true);
    }

    /* renamed from: -$$Nest$fgetgiftCode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_payments_checkedGiftCode m12109$$Nest$fgetgiftCode(GiftInfoBottomSheet r0) {
        return r0.giftCode;
    }

    /* renamed from: -$$Nest$fgetslug, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m12110$$Nest$fgetslug(GiftInfoBottomSheet r0) {
        return r0.slug;
    }

    public GiftInfoBottomSheet(BaseFragment r1, boolean r2, boolean r3, TLRPC.TL_payments_checkedGiftCode r4, String r5) {
        super(r1, r2, r3);
        if (r4.used_date != 0) goto L5;
        boolean r22 = true;
    L6:
        this.isUnused = r22;
        this.giftCode = r4;
        this.slug = r5;
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        fixNavigationBar();
        updateTitle();
        this.adapter.init(r1, r4, r5, this.container);
        return;
    L5:
        r22 = false;
        goto L6
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(GiftInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$100(GiftInfoBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(GiftInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static boolean handleIntent(Intent r3, Browser.Progress r4) {
        Uri r32 = r3.getData();
        if (r32 == null) goto L33;
        String r0 = r32.getScheme();
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.equals("http") == false) goto L9;
    L20:
        String r02 = r32.getHost().toLowerCase();
        if (r02.equals("telegram.me") == false) goto L23;
    L26:
        String r03 = r32.getPath();
        if (r03 == null) goto L40;
        String r33 = r32.getLastPathSegment();
        if (r03.startsWith("/giftcode") == false) goto L41;
        if (r33 == null) goto L42;
        show(LaunchActivity.getLastFragment(), r33, r4);
        return true;
    L42:
        return false;
    L41:
        return false;
    L40:
        return false;
    L23:
        if (r02.equals("t.me") == true) goto L26;
        if (r02.equals("telegram.dog") == true) goto L26;
        return false;
    L9:
        if (r0.equals("https") == true) goto L20;
        if (r0.equals("tg") == false) goto L36;
        String r04 = r32.toString();
        String r34 = r32.getLastPathSegment();
        if (r04.startsWith("tg:giftcode") == false) goto L16;
    L17:
        if (r34 == null) goto L38;
        show(LaunchActivity.getLastFragment(), r34, r4);
        return true;
    L38:
        return false;
    L16:
        if (r04.startsWith("tg://giftcode") == true) goto L17;
        return false;
    L36:
        return false;
    L33:
        return false;
    }

    public static void show(BaseFragment r1, String r2) {
        show(r1, r2, null);
    }

    public static void show(BaseFragment r2, String r3, Browser.Progress r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        AtomicBoolean r0 = new AtomicBoolean(false);
        if (r4 == null) goto L7;
        r4.init();
        r4.onCancel(new GiftInfoBottomSheet$$ExternalSyntheticLambda0(r0));
    L7:
        BoostRepository.checkGiftCode(r3, new GiftInfoBottomSheet$$ExternalSyntheticLambda1(r0, r2, r3, r4), new GiftInfoBottomSheet$$ExternalSyntheticLambda2(r0, r4));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r2) {
        AnonymousClass2 r22 = new AnonymousClass2(this, this.resourcesProvider);
        this.adapter = r22;
        return r22;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        Bulletin.removeDelegate(this.container);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.isUnused == false) goto L7;
        return LocaleController.getString(R.string.BoostingGiftLink);
    L7:
        return LocaleController.getString(R.string.BoostingUsedGiftLink);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onViewCreated(FrameLayout r2) {
        super.onViewCreated(r2);
        Bulletin.addDelegate(this.container, new AnonymousClass1(this));
    }
}
