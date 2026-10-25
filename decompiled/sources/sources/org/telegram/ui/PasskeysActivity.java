package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import com.exteragram.messenger.utils.PasskeysUtil;
import java.util.ArrayList;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.PasskeysController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Stars.ExplainStarsSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class PasskeysActivity extends BaseFragment {

    @Keep
    public int addPasskeyRow;
    private UniversalRecyclerView listView;
    private ArrayList<TL_account.Passkey> passkeys;

    /* renamed from: org.telegram.ui.PasskeysActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PasskeysActivity this$0;

        public AnonymousClass1(PasskeysActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public static class PasskeyCell extends FrameLayout {
        private final int currentAccount;
        public String id;
        private final FrameLayout imageBackgroundView;
        private final BackupImageView imageView;
        private boolean needDivider;
        private final ImageView optionsView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView subtitleView;
        private final TextView titleView;

        public static class Factory extends UItem.UItemFactory<PasskeyCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(TL_account.Passkey r1, View.OnClickListener r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.clickCallback = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((PasskeyCell) r1).set((TL_account.Passkey) r2.object, r2.clickCallback, r3);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public PasskeyCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new PasskeyCell(r1, r3, r5);
            }
        }

        public PasskeyCell(Context r13, int r14, Theme.ResourcesProvider r15) {
            super(r13);
            this.currentAccount = r14;
            this.resourcesProvider = r15;
            FrameLayout r142 = new FrameLayout(r13);
            this.imageBackgroundView = r142;
            addView(r142, LayoutHelper.createFrame(36, 36.0f, 19, 18.5f, 0.0f, 0.0f, 0.0f));
            BackupImageView r0 = new BackupImageView(r13);
            this.imageView = r0;
            r0.setImageResource(R.drawable.msg2_permissions);
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            int r3 = Theme.multAlpha(Theme.getColor(r2, r15), 0.3f);
            PorterDuff.Mode r4 = PorterDuff.Mode.SRC_IN;
            r0.setColorFilter(new PorterDuffColorFilter(r3, r4));
            r142.addView(r0, LayoutHelper.createFrame(36, 36, 17));
            TextView r143 = TextHelper.makeTextView(r13, 15.0f, r2, true);
            this.titleView = r143;
            r143.setSingleLine();
            TextUtils.TruncateAt r02 = TextUtils.TruncateAt.END;
            r143.setEllipsize(r02);
            addView(r143, LayoutHelper.createFrame(-1, -2.0f, 55, 72.0f, 8.0f, 46.0f, 0.0f));
            int r144 = Theme.key_windowBackgroundWhiteGrayText;
            TextView r1 = TextHelper.makeTextView(r13, 13.0f, r144, false);
            this.subtitleView = r1;
            r1.setSingleLine();
            r1.setEllipsize(r02);
            addView(r1, LayoutHelper.createFrame(-1, -2.0f, 55, 72.0f, 31.0f, 46.0f, 0.0f));
            ImageView r03 = new ImageView(r13);
            this.optionsView = r03;
            r03.setScaleType(ImageView.ScaleType.CENTER);
            r03.setImageResource(R.drawable.ic_ab_other);
            r03.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r144), r4));
            r03.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r15)));
            addView(r03, LayoutHelper.createFrame(32, 32.0f, 21, 0.0f, 0.0f, 13.0f, 0.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.needDivider == false) goto L17;
            Paint r0 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
            if (r0 != null) goto L7;
            r0 = Theme.dividerPaint;
        L7:
            Paint r6 = r0;
            float r1 = 72.0f;
            if (LocaleController.isRTL == false) goto L10;
            float r02 = 0.0f;
        L11:
            float r03 = AndroidUtilities.dp(r02);
            float r3 = getMeasuredHeight() - 1;
            int r4 = getWidth();
            if (LocaleController.isRTL == true) goto L15;
            r1 = 0.0f;
        L15:
            r8.drawRect(r03, r3, r4 - AndroidUtilities.dp(r1), getMeasuredHeight(), r6);
            return;
        L10:
            r02 = 72.0f;
            goto L11
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        }

        public void set(TL_account.Passkey r7, View.OnClickListener r8, boolean r9) {
            this.id = r7.id;
            long r0 = r7.software_emoji_id;
            if (r0 == 0) goto L5;
            this.imageView.setAnimatedEmojiDrawable(AnimatedEmojiDrawable.make(this.currentAccount, 3, r0));
            this.imageBackgroundView.setBackground(null);
            this.imageView.setColorFilter(null);
            this.imageView.setScaleX(1.0f);
            this.imageView.setScaleY(1.0f);
        L6:
            boolean r02 = TextUtils.isEmpty(r7.name);
            TextView r1 = this.titleView;
            if (r02 == false) goto L9;
            r1.setText(LocaleController.getString(R.string.PasskeyUnknown));
        L10:
            int r03 = r7.last_usage_date;
            TextView r12 = this.subtitleView;
            if (r03 == 0) goto L13;
            r12.setText(LocaleController.formatString(R.string.PasskeyLastUsedOn, new Object[]{LocaleController.formatDateTime(r03, false)}));
        L14:
            this.optionsView.setOnClickListener(r8);
            this.needDivider = r9;
            setWillNotDraw(!r9);
            return;
        L13:
            r12.setText(LocaleController.formatString(R.string.PasskeyCreatedOn, new Object[]{LocaleController.formatDateTime(r7.date, false)}));
            goto L14
        L9:
            r1.setText(r7.name);
            goto L10
        L5:
            FrameLayout r04 = this.imageBackgroundView;
            int r13 = AndroidUtilities.dp(4.0f);
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r04.setBackground(Theme.createRoundRectDrawable(r13, Theme.multAlpha(Theme.getColor(r2, this.resourcesProvider), 0.04f)));
            this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(Theme.getColor(r2, this.resourcesProvider), 0.3f), PorterDuff.Mode.SRC_IN));
            this.imageView.setImageResource(R.drawable.msg2_permissions);
            this.imageView.setScaleX(0.666f);
            this.imageView.setScaleY(0.666f);
            this.imageView.setAnimatedEmojiDrawable(null);
            goto L6
        }
    }

    /* renamed from: $r8$lambda$2_XflqGuCl8heXiTJ8-2iRvHc0A, reason: not valid java name */
    public static /* synthetic */ void m16389$r8$lambda$2_XflqGuCl8heXiTJ82iRvHc0A(BottomSheet r0, DialogInterface r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$5j64GBgcmm__wMiJbWcuNZTZz1k(PasskeysActivity r0, int r1, TL_account.Passkey r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
        r0.lambda$openMenu$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$8DyVmp96C3qdZI3nL65SRH0qpZc(ButtonWithCounterView r0, Context r1, int r2, BottomSheet r3, View r4) {
        if (r0.isLoading() == false) goto L5;
        return;
    L5:
        r0.setLoading(true);
        PasskeysController.create(r1, r2, new PasskeysActivity$$ExternalSyntheticLambda10(r0, r1, r3, r2));
    }

    /* renamed from: $r8$lambda$IjsLdKNQgpI-sFobjMO60p983YE, reason: not valid java name */
    public static /* synthetic */ void m16390$r8$lambda$IjsLdKNQgpIsFobjMO60p983YE(BottomSheet r0, TL_account.Passkey r1, BulletinFactory r2, TL_account.Passkeys r3, TLRPC.TL_error r4) {
        if (r3 == null) goto L15;
        r0.dismiss();
        int r02 = 0;
    L5:
        if (r02 >= r3.passkeys.size()) goto L10;
        if (TextUtils.equals(r3.passkeys.get(r02).id, r1.id) == false) goto L9;
        r3.passkeys.remove(r02);
        r02 = r02 - 1;
    L9:
        r02 = r02 + 1;
        goto L5
    L10:
        BaseFragment r03 = LaunchActivity.getSafeLastFragment();
        if (r03 == null) goto L22;
        PasskeysActivity r22 = new PasskeysActivity(r3.passkeys);
        r03.presentFragment(r22);
        AndroidUtilities.runOnUIThread(new PasskeysActivity$$ExternalSyntheticLambda14(r22, r1), 150);
        return;
    L22:
        return;
    L15:
        if (r4 == null) goto L21;
        r2.showForError(r4);
        return;
    }

    public static /* synthetic */ void $r8$lambda$IvfE0737aN1OTFMa5Nrk0FPdOok(PasskeysActivity r0, TL_account.Passkey r1, String r2, int r3) {
        r0.lambda$openMenu$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$J2Z6ij2GsDb26qncSpVxWGqNoCg(PasskeysActivity r0, TL_account.Passkey r1, String r2) {
        r0.lambda$onItemClick$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NTuIvxtdV2efQwsigxkj7f55R7A(PasskeysActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$UvwTtNGAXwMXmLNSk3DuauWuYVA(PasskeysActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$W0aSn4QiyqubXTDyVZhckUsEopE(PasskeysActivity r0, TL_account.Passkey r1, String r2, int r3, AlertDialog r4, int r5) {
        r0.lambda$openMenu$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$XmLKudly43pt9uGAN_SpDDag5E0(PasskeysActivity r0) {
        r0.lambda$fillItems$0();
    }

    public static /* synthetic */ void $r8$lambda$YbuzlWVIPDdYORbpgGojRqxLWg4(ButtonWithCounterView r3, Context r4, BottomSheet r5, int r6, TL_account.Passkey r7, String r8) {
        r3.setLoading(false);
        if ("CANCELLED".equalsIgnoreCase(r8) == false) goto L6;
        return;
    L6:
        if ("EMPTY".equalsIgnoreCase(r8) == false) goto L9;
        new AlertDialog.Builder(r4).setTitle(LocaleController.getString(R.string.PasskeyNoOptionsTitle)).setMessage(LocaleController.getString(R.string.PasskeyNoOptionsText)).setPositiveButton(LocaleController.getString(R.string.OK), null).setOnDismissListener(new PasskeysActivity$$ExternalSyntheticLambda11(r5)).show();
        return;
    L9:
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 == null) goto L36;
        BulletinFactory r42 = BulletinFactory.of(r5.topBulletinContainer, r5.getResourcesProvider());
        if (r8 != null) goto L15;
        if (r7 == null) goto L34;
        MessagesController.getInstance(r6).removeSuggestion(0, "SETUP_PASSKEY");
        if ((r32 instanceof PasskeysActivity) == false) goto L26;
        r5.dismiss();
        ((PasskeysActivity) r32).added(r7);
        return;
    L26:
        if ((r32 instanceof PrivacySettingsActivity) == false) goto L32;
        r5.dismiss();
        PrivacySettingsActivity r43 = (PrivacySettingsActivity) r32;
        ArrayList<TL_account.Passkey> r52 = r43.currentPasskeys;
        if (r52 != null) goto L30;
        r52 = new ArrayList();
    L30:
        r52.add(r7);
        r43.updateRows(true);
        r32.presentFragment(new PasskeysActivity(r52));
        return;
    L32:
        ConnectionsManager.getInstance(r6).sendRequestTyped(new TL_account.getPasskeys(), new AiTonesController$$ExternalSyntheticLambda0(), new PasskeysActivity$$ExternalSyntheticLambda12(r5, r7, r42));
        return;
    L34:
        return;
    L15:
        if (r8.startsWith("No create options") == false) goto L18;
        PasskeysUtil.showUnsupportedBulletin(r42);
        return;
    L18:
        r42.showForError(r8);
        return;
    }

    public static /* synthetic */ void $r8$lambda$Yc2Haz5keCmjZBwC_6Tdj7JhCto(PasskeysActivity r0, TL_account.Passkey r1) {
        r0.added(r1);
    }

    public static /* synthetic */ void $r8$lambda$Yx8kF2Xjzezs6h9T5XcTtO7lsnk(PasskeysActivity r0) {
        r0.lambda$fillItems$1();
    }

    public static /* synthetic */ void $r8$lambda$l1PYe8P24wnC8WRMuYhx1KeEaYE(PasskeysActivity r0, View r1) {
        r0.openMenu(r1);
    }

    public static /* synthetic */ void $r8$lambda$lEDouRFUbWe9svwA3pZ0wHYLCB8(PasskeysActivity r0) {
        r0.lambda$fillItems$2();
    }

    public PasskeysActivity(ArrayList<TL_account.Passkey> r1) {
        this.passkeys = r1;
    }

    private void fillItems(ArrayList<UItem> r5, UniversalAdapter r6) {
        this.addPasskeyRow = -1;
        r5.add(UItem.asTopView(LocaleController.getString(R.string.PasskeyTopInfo), R.raw.passkey));
        int r1 = 0;
    L3:
        int r2 = this.passkeys.size();
        ArrayList<TL_account.Passkey> r3 = this.passkeys;
        if (r1 >= r2) goto L7;
        r5.add(PasskeyCell.Factory.of(r3.get(r1), new PasskeysActivity$$ExternalSyntheticLambda3(this)));
        r1 = r1 + 1;
        goto L3
    L7:
        if ((r3.size() + 1) > getMessagesController().config.passkeysAccountPasskeysMax.get()) goto L9;
        this.addPasskeyRow = r5.size();
        r5.add(UItem.asButton(-1, R.drawable.menu_passkey_add, LocaleController.getString(R.string.PasskeyAdd)).accent());
    L9:
        r5.add(UItem.asShadow(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.PasskeyInfo), new PasskeysActivity$$ExternalSyntheticLambda4(this)), true)));
        r5.add(UItem.asButton(-2, R.drawable.msg_settings, LocaleController.getString(R.string.Settings)));
        r5.add(UItem.asShadow(AndroidUtilities.replaceMultipleTags(LocaleController.getString(R.string.PasskeyInfo2), new Runnable[]{new PasskeysActivity$$ExternalSyntheticLambda5(this), new PasskeysActivity$$ExternalSyntheticLambda6(this)})));
    }

    private /* synthetic */ void lambda$fillItems$0() {
        Context r0 = getContext();
        int r1 = this.currentAccount;
        Theme.ResourcesProvider r2 = this.resourceProvider;
        boolean r4 = true;
        if ((this.passkeys.size() + 1) <= getMessagesController().config.passkeysAccountPasskeysMax.get()) goto L6;
        r4 = false;
    L6:
        showLearnSheet(r0, r1, r2, r4);
    }

    private /* synthetic */ void lambda$fillItems$1() {
        Browser.openUrl(getContext(), "https://github.com/bitwarden/android");
    }

    private /* synthetic */ void lambda$fillItems$2() {
        Browser.openUrl(getContext(), "https://github.com/Kunzisoft/KeePassDX");
    }

    private /* synthetic */ void lambda$onItemClick$6(TL_account.Passkey r4, String r5) {
        if (r5 != null) goto L4;
        if (r4 == null) goto L19;
        MessagesController.getInstance(this.currentAccount).removeSuggestion(0, "SETUP_PASSKEY");
        added(r4);
        return;
    L19:
        return;
    L4:
        if ("CANCELLED".equalsIgnoreCase(r5) == false) goto L7;
        return;
    L7:
        if ("EMPTY".equalsIgnoreCase(r5) == false) goto L10;
        new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.PasskeyNoOptionsTitle)).setMessage(LocaleController.getString(R.string.PasskeyNoOptionsText)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L10:
        BulletinFactory r3 = BulletinFactory.of(this);
        if (r5.startsWith("No create options") == false) goto L14;
        PasskeysUtil.showUnsupportedBulletin(r3);
        return;
    L14:
        r3.showForError(r5, true);
    }

    private /* synthetic */ void lambda$openMenu$3(int r3, TL_account.Passkey r4, TLRPC.Bool r5, TLRPC.TL_error r6) {
        if ((r5 instanceof TLRPC.TL_boolFalse) == false) goto L6;
        BulletinFactory.of(this).showForError("FALSE");
        ArrayList<TL_account.Passkey> r52 = this.passkeys;
        r52.add(Utilities.clamp(r3, r52.size(), 0), r4);
        this.listView.adapter.update(true);
        return;
    L6:
        if (r6 == null) goto L9;
        BulletinFactory.of(this).showForError(r6);
        ArrayList<TL_account.Passkey> r53 = this.passkeys;
        r53.add(Utilities.clamp(r3, r53.size(), 0), r4);
        this.listView.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$openMenu$4(TL_account.Passkey r2, String r3, int r4, AlertDialog r5, int r6) {
        this.passkeys.remove(r2);
        this.listView.adapter.update(true);
        TL_account.deletePasskey r52 = new TL_account.deletePasskey();
        r52.id = r3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r52, new AiTonesController$$ExternalSyntheticLambda0(), new PasskeysActivity$$ExternalSyntheticLambda13(this, r4, r2));
    }

    private /* synthetic */ void lambda$openMenu$5(TL_account.Passkey r4, String r5, int r6) {
        new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.PasskeyDeleteTitle)).setMessage(LocaleController.getString(R.string.PasskeyDeleteText)).setPositiveButton(LocaleController.getString(R.string.Delete), new PasskeysActivity$$ExternalSyntheticLambda9(this, r4, r5, r6)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
    }

    private void onItemClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r32 = r1.id;
        if (r32 != (-1)) goto L7;
        PasskeysController.create(getContext(), this.currentAccount, new PasskeysActivity$$ExternalSyntheticLambda2(this));
        return;
    L7:
        if (r32 != (-2)) goto L11;
        PasskeysUtil.openSettings(getParentActivity());
        return;
    L11:
        if (r1.object == null) goto L14;
        openMenu(r2);
        return;
    }

    private void openMenu(View r8) {
        boolean r0 = r8 instanceof ImageView;
        ViewParent r82 = r8;
        if (r0 == false) goto L5;
        r82 = r8.getParent();
    L5:
        PasskeyCell r83 = (PasskeyCell) r82;
        String r02 = r83.id;
        int r1 = 0;
    L7:
        if (r1 >= this.passkeys.size()) goto L12;
        if (r02.equals(this.passkeys.get(r1).id) == true) goto L13;
        r1 = r1 + 1;
    L13:
        if (r1 >= 0) goto L15;
        return;
    L15:
        if (r1 >= this.passkeys.size()) goto L22;
        TL_account.Passkey r2 = this.passkeys.get(r1);
        ItemOptions.makeOptions(this, r83).add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new PasskeysActivity$$ExternalSyntheticLambda7(this, r2, r02, r1)).setScrimViewBackground(this.listView.getClipBackground(r83)).show();
        return;
    L22:
        return;
    L12:
        r1 = -1;
        goto L13
    }

    public static void showLearnSheet(Context r17, int r18, Theme.ResourcesProvider r19, boolean r20) {
        BottomSheet.Builder r2 = new BottomSheet.Builder(r17, false, r19);
        LinearLayout r4 = new LinearLayout(r17);
        r4.setOrientation(1);
        r4.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        r2.setCustomView(r4);
        RLottieImageView r6 = new RLottieImageView(r17);
        r6.setAnimation(R.raw.passkey, AndroidUtilities.dp(115.0f), AndroidUtilities.dp(115.0f));
        r6.playAnimation();
        r4.addView(r6, LayoutHelper.createLinear(115, 115, 17, 0, 0, 0, 9));
        int r62 = Theme.key_dialogTextBlack;
        TextView r7 = TextHelper.makeTextView(r17, 18.0f, r62, true, r19);
        r7.setGravity(17);
        r7.setText(LocaleController.getString(R.string.PasskeyFeatureTitle));
        r4.addView(r7, LayoutHelper.createLinear(-1, -2, 32.0f, 0.0f, 32.0f, 6.0f));
        TextView r63 = TextHelper.makeTextView(r17, 14.0f, r62, false, r19);
        r63.setGravity(17);
        r63.setText(LocaleController.getString(R.string.PasskeyFeatureSubtitle));
        r4.addView(r63, LayoutHelper.createLinear(-1, -2, 32.0f, 0.0f, 32.0f, 24.0f));
        ExplainStarsSheet.FeatureCell r64 = new ExplainStarsSheet.FeatureCell(r17, 1, r19);
        r64.set(R.drawable.msg2_permissions, LocaleController.getString(R.string.PasskeyFeature1Title), LocaleController.getString(R.string.PasskeyFeature1Subtitle));
        r4.addView(r64, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 8.0f));
        ExplainStarsSheet.FeatureCell r65 = new ExplainStarsSheet.FeatureCell(r17, 1, r19);
        r65.set(R.drawable.menu_face, LocaleController.getString(R.string.PasskeyFeature2Title), LocaleController.getString(R.string.PasskeyFeature2Subtitle));
        r4.addView(r65, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 8.0f));
        ExplainStarsSheet.FeatureCell r66 = new ExplainStarsSheet.FeatureCell(r17, 1, r19);
        r66.set(R.drawable.menu_privacy, LocaleController.getString(R.string.PasskeyFeature3Title), LocaleController.getString(R.string.PasskeyFeature3Subtitle));
        r4.addView(r66, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 8.0f));
        BottomSheet r22 = r2.create();
        ButtonWithCounterView r1 = new ButtonWithCounterView(r17, r19).setRound();
        r1.setText(LocaleController.getString(R.string.PasskeyFeatureButton), false);
        r1.setOnClickListener(new PasskeysActivity$$ExternalSyntheticLambda8(r1, r17, r18, r22));
        if (r20 == false) goto L5;
        r4.addView(r1, LayoutHelper.createLinear(-1, 48, 0.0f, 16.0f, 0.0f, 8.0f));
    L5:
        r22.fixNavigationBar();
        r22.show();
    }

    public void added(TL_account.Passkey r5) {
        this.passkeys.add(r5);
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L7;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L7;
        r02.update(true);
    L7:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.passcode_lock_close, LocaleController.getString(R.string.PasskeyAddedTitle), LocaleController.formatString(R.string.PasskeyAddedText, new Object[]{r5.name})).setDuration(5000).show(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.Passkey));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r5);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        UniversalRecyclerView r52 = new UniversalRecyclerView(this, new PasskeysActivity$$ExternalSyntheticLambda0(this), new PasskeysActivity$$ExternalSyntheticLambda1(this), null);
        this.listView = r52;
        r52.setSections();
        this.listView.adapter.setApplyBackground(false);
        r0.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.fragmentView = r0;
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }
}
