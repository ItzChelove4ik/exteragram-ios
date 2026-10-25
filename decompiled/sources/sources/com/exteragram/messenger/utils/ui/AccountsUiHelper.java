package com.exteragram.messenger.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.LoginActivity;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001&B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0015\u001a\u00020\u000b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0004\b\u0015\u0010\u0016JA\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010$\u001a\u00020#2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020!H\u0007¢\u0006\u0004\b$\u0010%¨\u0006'"}, d2 = {"Lcom/exteragram/messenger/utils/ui/AccountsUiHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Ljava/util/function/IntPredicate;", "filter", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "activated", "(Ljava/util/function/IntPredicate;)Ljava/util/List;", "account", _UrlKt.FRAGMENT_ENCODE_SET, "switchTo", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "hasFreeSlot", "()Z", "freeSlotWithinLimit", "()Ljava/lang/Integer;", "Lorg/telegram/ui/ActionBar/BaseFragment;", "fragment", "add", "(Lorg/telegram/ui/ActionBar/BaseFragment;)V", "Landroid/content/Context;", "context", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "resourcesProvider", "selected", "top", "bottom", "Landroid/widget/LinearLayout;", "row", "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;IZZZ)Landroid/widget/LinearLayout;", "Landroid/view/View;", "anchor", "Lcom/exteragram/messenger/utils/ui/AccountsUiHelper$Builder;", "menu", "(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lcom/exteragram/messenger/utils/ui/AccountsUiHelper$Builder;", "Builder", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAccountsUiHelper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountsUiHelper.kt\ncom/exteragram/messenger/utils/ui/AccountsUiHelper\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,261:1\n777#2:262\n873#2,2:263\n1068#2:265\n*S KotlinDebug\n*F\n+ 1 AccountsUiHelper.kt\ncom/exteragram/messenger/utils/ui/AccountsUiHelper\n*L\n60#1:262\n60#1:263,2\n61#1:265\n*E\n"})
/* loaded from: classes4.dex */
public final class AccountsUiHelper {
    public static final AccountsUiHelper INSTANCE = null;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\u0010\u0010\u0011\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u00002\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fJ\b\u0010\u0012\u001a\u00020\tH\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/exteragram/messenger/utils/ui/AccountsUiHelper$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "anchor", "Landroid/view/View;", "<init>", "(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)V", "fromBottom", _UrlKt.FRAGMENT_ENCODE_SET, "withAddAccount", "touchRelayView", "onSelected", "Ljava/util/function/IntConsumer;", "extraItems", "Ljava/util/function/Consumer;", "Lorg/telegram/ui/Components/ItemOptions;", "touchRelay", "show", "addAccountItem", _UrlKt.FRAGMENT_ENCODE_SET, "options", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAccountsUiHelper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountsUiHelper.kt\ncom/exteragram/messenger/utils/ui/AccountsUiHelper$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,261:1\n1#2:262\n1924#3,3:263\n*S KotlinDebug\n*F\n+ 1 AccountsUiHelper.kt\ncom/exteragram/messenger/utils/ui/AccountsUiHelper$Builder\n*L\n227#1:263,3\n*E\n"})
    public static final class Builder {
        private final View anchor;
        private Consumer<ItemOptions> extraItems;
        private final BaseFragment fragment;
        private boolean fromBottom;
        private IntConsumer onSelected;
        private View touchRelayView;
        private boolean withAddAccount;

        /* renamed from: $r8$lambda$8fbiyZvUjU4na0DcLlI4yRK7-4U, reason: not valid java name */
        public static void m1796$r8$lambda$8fbiyZvUjU4na0DcLlI4yRK74U(int r0, int r1, ItemOptions r2, Builder r3, View r4) {
            if (r0 == r1) goto L5;
            r2.dismiss();
            r3.onSelected.accept(r1);
            return;
        }

        public static boolean $r8$lambda$TmpqVMKRLharwB3hGMfpHrV48R0(ItemOptions r1, View r2, MotionEvent r3) {
            if (r1.isShown() == false) goto L10;
            ViewParent r22 = r2.getParent();
            if (r22 == null) goto L7;
            r22.requestDisallowInterceptTouchEvent(true);
        L7:
            r1.dispatchCapturedTouchEvent(r3);
            return false;
        L10:
            return false;
        }

        public static void $r8$lambda$_bKTccLBiqhzHXZDLRbsGpjkAro(Builder r0) {
            AccountsUiHelper.add(r0.fragment);
        }

        public static void $r8$lambda$pNJf8N6esKRC5LPiPZ7pw0qnIMc(int r0) {
            AccountsUiHelper.switchTo(r0);
        }

        public Builder(BaseFragment r1, View r2) {
            this.fragment = r1;
            this.anchor = r2;
            this.withAddAccount = true;
            this.onSelected = new AccountsUiHelper$Builder$$ExternalSyntheticLambda0();
        }

        private final void addAccountItem(ItemOptions r4) {
            r4.add(R.drawable.msg_addbot, LocaleController.getString(R.string.AddAccount), new AccountsUiHelper$Builder$$ExternalSyntheticLambda3(this));
        }

        public final Builder extraItems(Consumer<ItemOptions> r1) {
            this.extraItems = r1;
            return this;
        }

        public final Builder fromBottom(boolean r1) {
            this.fromBottom = r1;
            return this;
        }

        public final Builder onSelected(IntConsumer r1) {
            this.onSelected = r1;
            return this;
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public final boolean show() {
            Context r1 = this.fragment.getContext();
            if (r1 != null) goto L5;
            return false;
        L5:
            Theme.ResourcesProvider r2 = this.fragment.getResourceProvider();
            int r7 = this.fragment.getCurrentAccount();
            List r3 = AccountsUiHelper.activated$default(null, 1, null);
            if (this.fromBottom == false) goto L8;
            r3 = CollectionsKt.asReversed(r3);
        L8:
            List r9 = r3;
            ItemOptions r10 = ItemOptions.makeOptions(this.fragment, this.anchor);
            if (this.withAddAccount == true) goto L11;
        L13:
            boolean r11 = false;
        L14:
            if (r11 == true) goto L16;
        L18:
            Consumer<ItemOptions> r32 = this.extraItems;
            if (r32 == null) goto L22;
            r32.accept(r10);
        L22:
            if (r9.isEmpty() == true) goto L50;
            View r33 = this.touchRelayView;
            if (r33 == null) goto L27;
            r33.setOnTouchListener(new AccountsUiHelper$Builder$$ExternalSyntheticLambda1(r10));
        L27:
            if (r10.getItemsCount() <= 0) goto L29;
            r10.addGap();
        L29:
            Iterator r12 = r9.iterator();
            int r34 = 0;
        L31:
            if (r12.hasNext() == false) goto L50;
            Object r4 = r12.next();
            int r13 = r34 + 1;
            if (r34 >= 0) goto L35;
            CollectionsKt.throwIndexOverflow();
        L35:
            int r42 = ((Number) r4).intValue();
            int r5 = r34;
            if (r7 != r42) goto L38;
            boolean r43 = true;
        L39:
            boolean r6 = this.fromBottom;
            if (r6 == false) goto L43;
            if (r5 != 0) goto L43;
            int r14 = r5;
            boolean r52 = true;
        L44:
            if (r6 == false) goto L46;
        L48:
            boolean r62 = false;
        L49:
            LinearLayout r44 = AccountsUiHelper.row(r1, r2, r42, r43, r52, r62);
            r44.setOnClickListener(new AccountsUiHelper$Builder$$ExternalSyntheticLambda2(r7, r42, r10, this));
            r10.addView(r44, LayoutHelper.createLinear(230, 48));
            r34 = r13;
            goto L31
        L46:
            if (r14 != (r9.size() - 1)) goto L48;
            r62 = true;
        L43:
            r14 = r5;
            r52 = false;
            goto L44
        L38:
            r43 = false;
        L50:
            if (r11 == true) goto L52;
        L57:
            r10.setBlur(true).translate(0.0f, -AndroidUtilities.dp(4.0f)).setScrimViewBackground(MainTabsUiHelper.createMainTabsScrimBackground(r2, this.fromBottom)).setDismissOnMoveOutside(true).show();
            return true;
        L52:
            if (this.fromBottom == false) goto L57;
            if (r10.getItemsCount() <= 0) goto L56;
            r10.addGap();
        L56:
            addAccountItem(r10);
            goto L57
        L16:
            if (this.fromBottom == true) goto L18;
            addAccountItem(r10);
            goto L18
        L11:
            if (AccountsUiHelper.hasFreeSlot() == false) goto L13;
            r11 = true;
            goto L14
        }

        public final Builder touchRelay(View r1) {
            this.touchRelayView = r1;
            return this;
        }

        public final Builder withAddAccount(boolean r1) {
            this.withAddAccount = r1;
            return this;
        }
    }

    static {
        INSTANCE = new AccountsUiHelper();
    }

    private AccountsUiHelper() {
    }

    @JvmStatic
    @JvmOverloads
    public static final List<Integer> activated() {
        return activated$default(null, 1, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final List<Integer> activated(IntPredicate r5) {
        IntRange r0 = RangesKt.until(0, 16);
        ArrayList r1 = new ArrayList();
        Iterator<Integer> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L13;
        Integer r2 = r02.next();
        int r3 = r2.intValue();
        if (UserConfig.getInstance(r3).isClientActivated() == false) goto L4;
        if (r5 == null) goto L11;
        if (r5.test(r3) == false) goto L4;
    L11:
        r1.add(r2);
        goto L4
    L13:
        return CollectionsKt.sortedWith(r1, new AccountsUiHelper$activated$$inlined$sortedBy$1());
    }

    public static /* synthetic */ List activated$default(IntPredicate r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = null;
    L6:
        return activated(r0);
    }

    @JvmStatic
    @JvmOverloads
    public static final void add() {
        add$default(null, 1, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void add(BaseFragment r6) {
        if (r6 != null) goto L4;
        r6 = LaunchActivity.getSafeLastFragment();
    L4:
        BaseFragment r1 = r6;
        Integer r62 = freeSlotWithinLimit();
        if (r62 == null) goto L14;
        if (r1 == null) goto L9;
        r1.presentFragment(new LoginActivity(r62.intValue()));
        return;
    L9:
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L22;
        r0.presentFragment(new LoginActivity(r62.intValue()));
        return;
    L22:
        return;
    L14:
        if (UserConfig.hasPremiumOnAccounts() == true) goto L24;
        if (r1 == null) goto L23;
        Context r2 = r1.getContext();
        if (r2 == null) goto L25;
        r1.showDialog(new LimitReachedBottomSheet(r1, r2, 7, r1.getCurrentAccount(), null));
        return;
    L25:
        return;
    L23:
        return;
    }

    public static /* synthetic */ void add$default(BaseFragment r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L5;
        r0 = null;
    L5:
        add(r0);
    }

    @JvmStatic
    public static final Integer freeSlotWithinLimit() {
        int r0 = 0;
        int r2 = 15;
        Integer r3 = null;
    L4:
        if ((-1) >= r2) goto L12;
        if (UserConfig.getInstance(r2).isClientActivated() == true) goto L10;
        r0 = r0 + 1;
        if (r3 != null) goto L10;
        r3 = Integer.valueOf(r2);
    L10:
        r2 = r2 - 1;
        goto L4
    L12:
        if (UserConfig.hasPremiumOnAccounts() == true) goto L14;
        r0 = r0 - 8;
    L14:
        if (r0 <= 0) goto L16;
        return r3;
    L16:
        return null;
    }

    @JvmStatic
    public static final boolean hasFreeSlot() {
        if (UserConfig.getActivatedAccountsCount() >= 16) goto L6;
        return true;
    L6:
        return false;
    }

    @JvmStatic
    public static final Builder menu(BaseFragment r1, View r2) {
        return new Builder(r1, r2);
    }

    @JvmStatic
    public static final LinearLayout row(Context r18, Theme.ResourcesProvider r19, int r20, boolean r21, boolean r22, boolean r23) {
        LinearLayout r3 = new LinearLayout(r18);
        r3.setOrientation(0);
        r3.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r19), 0, 0));
        UIUtil.applyScaleStateListAnimator(r3, 12.0f, r22, r23, 3, 0.04f, 1.5f);
        TLRPC.User r4 = UserConfig.getInstance(r20).getCurrentUser();
        AvatarDrawable r5 = new AvatarDrawable();
        r5.setInfo(r4);
        AccountsUiHelper$row$avatarContainer$1 r6 = new AccountsUiHelper$row$avatarContainer$1(r18, r21, r19);
        r3.addView(r6, LayoutHelper.createLinear(34, 34, 16, 12, 0, 0, 0));
        BackupImageView r7 = new BackupImageView(r18);
        if (r21 == false) goto L5;
        r7.setScaleX(0.833f);
        r7.setScaleY(0.833f);
    L5:
        r7.setRoundRadius(ExteraConfig.getAvatarCorners$default(32.0f, false, false, 6, null));
        r7.getImageReceiver().setCurrentAccount(r20);
        r7.setForUserOrChat(r4, r5);
        r6.addView(r7, LayoutHelper.createLinear(32, 32, 17, 1, 1, 1, 1));
        TextView r2 = new TextView(r18);
        NotificationCenter.listenEmojiLoading(r2);
        r2.setTextSize(1, 16.0f);
        r2.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r19));
        r2.setText(Emoji.replaceEmoji(UserObject.getUserName(r4), r2.getPaint().getFontMetricsInt(), false));
        r2.setMaxLines(2);
        r2.setEllipsize(TextUtils.TruncateAt.END);
        r3.addView(r2, LayoutHelper.createLinear(0, -2, 1.0f, 16, 13, 0, 14, 0));
        return r3;
    }

    @JvmStatic
    public static final void switchTo(int r2) {
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L6;
        r0.switchToAccount(r2, true);
        return;
    }
}
