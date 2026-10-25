package org.telegram.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.SlideIntChooseView;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.HintView;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.bots.AffiliateProgramFragment;

/* loaded from: classes6.dex */
public class PrivacyControlActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, ImageUpdater.ImageUpdaterDelegate {
    private int alwaysShareRow;
    private TLRPC.PhotoSize avatarForRest;
    private TLRPC.Photo avatarForRestPhoto;
    private RLottieDrawable cameraDrawable;
    private boolean currentGiftChannelsValue;
    private boolean currentGiftIconValue;
    private boolean currentGiftLimitedValue;
    private boolean currentGiftPremiumValue;
    private boolean currentGiftUniqueValue;
    private boolean currentGiftUnlimitedValue;
    private ArrayList<Long> currentMinus;

    @Keep
    private int currentPhotoForRestRow;
    private ArrayList<Long> currentPlus;
    private final boolean[] currentPlusChannels;
    private final boolean[] currentPlusMiniapps;
    private final boolean[] currentPlusPremium;
    private boolean currentReadValue;
    private long currentStars;
    private int currentSubType;
    private int currentType;
    private int detailRow;
    private int detailRow2;
    private View doneButton;
    private CrossfadeDrawable doneButtonDrawable;

    @Keep
    private int everybodyRow;
    private int giftTypeChannelsRow;
    private int giftTypeLimitedRow;
    private int giftTypePremiumRow;
    private int giftTypeUniqueRow;
    private int giftTypeUnlimitedRow;

    @Keep
    private int giftTypesHeaderRow;
    private int giftTypesInfoRow;
    ImageUpdater imageUpdater;
    private ArrayList<Long> initialMinus;
    private ArrayList<Long> initialPlus;
    private final boolean[] initialPlusChannels;
    private final boolean[] initialPlusMiniapps;
    private final boolean[] initialPlusPremium;
    private int initialRulesSubType;
    private int initialRulesType;
    private long initialStars;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private CharSequence lockSpan;
    private MessageCell messageCell;
    private int messageRow;
    private int myContactsRow;
    private int neverShareRow;

    @Keep
    private int nobodyRow;
    private BackupImageView oldAvatarView;
    private TextCell oldPhotoCell;
    private int p2pDetailRow;
    private int p2pRow;
    private int p2pSectionRow;
    private int payRow;
    private int phoneContactsRow;
    private int phoneDetailRow;
    private int phoneEverybodyRow;
    private int phoneSectionRow;
    private int photoForRestDescriptionRow;

    @Keep
    private int photoForRestRow;
    private boolean prevSubtypeContacts;
    private int priceButtonRow;
    private int priceHeaderRow;
    private int priceInfoRow;

    @Keep
    private int priceRow;
    private int readDetailRow;
    private int readPremiumDetailRow;
    private int readPremiumRow;

    @Keep
    private int readRow;
    private int rowCount;
    private int rulesType;
    private int sectionRow;
    private boolean selectedGiftChannelsValue;
    private boolean selectedGiftIconValue;
    private boolean selectedGiftLimitedValue;
    private boolean selectedGiftPremiumValue;
    private boolean selectedGiftUniqueValue;
    private boolean selectedGiftUnlimitedValue;
    private boolean selectedReadValue;
    private TextCell setAvatarCell;

    @Keep
    private int setBirthdayRow;
    private int shakeDp;
    private int shareDetailRow;
    private int shareSectionRow;
    private int showGiftIconInfoRow;

    @Keep
    private int showGiftIconRow;

    /* renamed from: org.telegram.ui.PrivacyControlActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PrivacyControlActivity this$0;

        public AnonymousClass1(PrivacyControlActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            PrivacyControlActivity.m17699$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (PrivacyControlActivity.m17698$$Nest$mcheckDiscard(this.this$0, true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PrivacyControlActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends DefaultItemAnimator {
        final /* synthetic */ PrivacyControlActivity this$0;

        public AnonymousClass2(PrivacyControlActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            PrivacyControlActivity.m17647$$Nest$fgetlistView(this.this$0).invalidate();
        }
    }

    public class DiffCallback extends DiffUtil.Callback {
        SparseIntArray newPositionToItem;
        SparseIntArray oldPositionToItem;
        int oldRowCount;
        final /* synthetic */ PrivacyControlActivity this$0;

        private DiffCallback(PrivacyControlActivity r1) {
            this.this$0 = r1;
            this.oldPositionToItem = new SparseIntArray();
            this.newPositionToItem = new SparseIntArray();
        }

        public /* synthetic */ DiffCallback(PrivacyControlActivity r1, PrivacyControlActivityIA r2) {
            this(r1);
        }

        private void put(int r1, int r2, SparseIntArray r3) {
            if (r2 < 0) goto L5;
            r3.put(r2, r1);
            return;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return areItemsTheSame(r1, r2);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r3, int r4) {
            int r32 = this.oldPositionToItem.get(r3, -1);
            if (r32 != this.newPositionToItem.get(r4, -1)) goto L7;
            if (r32 < 0) goto L9;
            return true;
        L9:
            return false;
        L7:
            return false;
        }

        public void fillPositions(SparseIntArray r3) {
            r3.clear();
            put(1, PrivacyControlActivity.m17650$$Nest$fgetmessageRow(this.this$0), r3);
            put(2, PrivacyControlActivity.m17676$$Nest$fgetsectionRow(this.this$0), r3);
            put(3, PrivacyControlActivity.m17639$$Nest$fgeteverybodyRow(this.this$0), r3);
            put(4, PrivacyControlActivity.m17651$$Nest$fgetmyContactsRow(this.this$0), r3);
            put(5, PrivacyControlActivity.m17653$$Nest$fgetnobodyRow(this.this$0), r3);
            put(6, PrivacyControlActivity.m17659$$Nest$fgetpayRow(this.this$0), r3);
            put(7, PrivacyControlActivity.m17637$$Nest$fgetdetailRow(this.this$0), r3);
            put(8, PrivacyControlActivity.m17687$$Nest$fgetshareSectionRow(this.this$0), r3);
            put(9, PrivacyControlActivity.m17625$$Nest$fgetalwaysShareRow(this.this$0), r3);
            put(10, PrivacyControlActivity.m17652$$Nest$fgetneverShareRow(this.this$0), r3);
            put(11, PrivacyControlActivity.m17686$$Nest$fgetshareDetailRow(this.this$0), r3);
            put(12, PrivacyControlActivity.m17663$$Nest$fgetphoneSectionRow(this.this$0), r3);
            put(13, PrivacyControlActivity.m17662$$Nest$fgetphoneEverybodyRow(this.this$0), r3);
            put(14, PrivacyControlActivity.m17660$$Nest$fgetphoneContactsRow(this.this$0), r3);
            put(15, PrivacyControlActivity.m17661$$Nest$fgetphoneDetailRow(this.this$0), r3);
            put(16, PrivacyControlActivity.m17665$$Nest$fgetphotoForRestRow(this.this$0), r3);
            put(17, PrivacyControlActivity.m17630$$Nest$fgetcurrentPhotoForRestRow(this.this$0), r3);
            put(18, PrivacyControlActivity.m17664$$Nest$fgetphotoForRestDescriptionRow(this.this$0), r3);
            put(19, PrivacyControlActivity.m17658$$Nest$fgetp2pSectionRow(this.this$0), r3);
            put(20, PrivacyControlActivity.m17657$$Nest$fgetp2pRow(this.this$0), r3);
            put(21, PrivacyControlActivity.m17656$$Nest$fgetp2pDetailRow(this.this$0), r3);
            put(22, PrivacyControlActivity.m17673$$Nest$fgetreadRow(this.this$0), r3);
            put(23, PrivacyControlActivity.m17670$$Nest$fgetreadDetailRow(this.this$0), r3);
            put(24, PrivacyControlActivity.m17672$$Nest$fgetreadPremiumRow(this.this$0), r3);
            put(25, PrivacyControlActivity.m17671$$Nest$fgetreadPremiumDetailRow(this.this$0), r3);
            put(26, PrivacyControlActivity.m17667$$Nest$fgetpriceHeaderRow(this.this$0), r3);
            put(27, PrivacyControlActivity.m17669$$Nest$fgetpriceRow(this.this$0), r3);
            put(28, PrivacyControlActivity.m17668$$Nest$fgetpriceInfoRow(this.this$0), r3);
            put(29, PrivacyControlActivity.m17689$$Nest$fgetshowGiftIconRow(this.this$0), r3);
            put(30, PrivacyControlActivity.m17688$$Nest$fgetshowGiftIconInfoRow(this.this$0), r3);
            put(31, PrivacyControlActivity.m17645$$Nest$fgetgiftTypesHeaderRow(this.this$0), r3);
            put(32, PrivacyControlActivity.m17641$$Nest$fgetgiftTypeLimitedRow(this.this$0), r3);
            put(33, PrivacyControlActivity.m17644$$Nest$fgetgiftTypeUnlimitedRow(this.this$0), r3);
            put(34, PrivacyControlActivity.m17643$$Nest$fgetgiftTypeUniqueRow(this.this$0), r3);
            put(35, PrivacyControlActivity.m17640$$Nest$fgetgiftTypeChannelsRow(this.this$0), r3);
            put(36, PrivacyControlActivity.m17642$$Nest$fgetgiftTypePremiumRow(this.this$0), r3);
            put(37, PrivacyControlActivity.m17646$$Nest$fgetgiftTypesInfoRow(this.this$0), r3);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return PrivacyControlActivity.m17674$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.oldRowCount;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ PrivacyControlActivity this$0;

        /* renamed from: org.telegram.ui.PrivacyControlActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends TextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.TextCell, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
                super.onLayout(r1, r2, r3, r4, r5);
                int r12 = AndroidUtilities.dp(21.0f);
                int r22 = (getMeasuredHeight() - PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(this.this$1.this$0).getMeasuredHeight()) / 2;
                PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(this.this$1.this$0).layout(r12, r22, PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(this.this$1.this$0).getMeasuredWidth() + r12, PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(this.this$1.this$0).getMeasuredHeight() + r22);
            }

            @Override // org.telegram.ui.Cells.TextCell, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r4, int r5) {
                super.onMeasure(r4, r5);
                PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(this.this$1.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
                PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(this.this$1.this$0).setRoundRadius(ExteraConfig.getAvatarCorners(30.0f));
            }
        }

        /* renamed from: org.telegram.ui.PrivacyControlActivity$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends ClickableSpan {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ String val$phoneLinkStr;

            public AnonymousClass2(ListAdapter r1, String r2) {
                this.this$1 = r1;
                this.val$phoneLinkStr = r2;
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View r3) {
                ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.val$phoneLinkStr));
                BulletinFactory.of(this.this$1.this$0).createCopyLinkBulletin(LocaleController.getString(R.string.LinkCopied), this.this$1.this$0.getResourceProvider()).show();
            }
        }

        /* renamed from: $r8$lambda$0BiE-6LHEYsdDOpJ_jeIeSlLg08, reason: not valid java name */
        public static /* synthetic */ CharSequence m17702$r8$lambda$0BiE6LHEYsdDOpJ_jeIeSlLg08(ListAdapter r0, Integer r1, Integer r2) {
            return r0.lambda$onBindViewHolder$6(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$3rrJKigOBVeFn3Pn8q1AMsgwSMw(ListAdapter r0) {
            r0.lambda$onBindViewHolder$5();
        }

        public static /* synthetic */ void $r8$lambda$5oQwBY6eZ4pp7QdOzMYi9M7bcic(ListAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        /* renamed from: $r8$lambda$9CRpMtfKE_oWwzio2fvh-Fjw6vY, reason: not valid java name */
        public static /* synthetic */ void m17703$r8$lambda$9CRpMtfKE_oWwzio2fvhFjw6vY(ListAdapter r0, Integer r1) {
            r0.lambda$onBindViewHolder$7(r1);
        }

        /* renamed from: $r8$lambda$H_zWYnTRl79SlhGWARbpEQxcz-o, reason: not valid java name */
        public static /* synthetic */ void m17704$r8$lambda$H_zWYnTRl79SlhGWARbpEQxczo(ListAdapter r0, TLRPC.UserFull r1, TL_account.TL_birthday r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onBindViewHolder$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$YC4K4PXhZaDR_B9iAvD3woOQ78Q(ListAdapter r0) {
            r0.lambda$onBindViewHolder$1();
        }

        /* renamed from: $r8$lambda$pCRvf1j4-0M4eI7o81xvhgKNe60, reason: not valid java name */
        public static /* synthetic */ void m17705$r8$lambda$pCRvf1j40M4eI7o81xvhgKNe60(ListAdapter r0, TLObject r1, TLRPC.UserFull r2, TL_account.TL_birthday r3, TLRPC.TL_error r4) {
            r0.lambda$onBindViewHolder$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$uh11n3pfsvX82ztjE0pV8zVPxyU(ListAdapter r0, TL_account.TL_birthday r1) {
            r0.lambda$onBindViewHolder$4(r1);
        }

        public ListAdapter(PrivacyControlActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private int getUsersCount(ArrayList<Long> r7) {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r0 >= r7.size()) goto L12;
            long r2 = r7.get(r0).longValue();
            if (r2 <= 0) goto L8;
            r1 = r1 + 1;
        L11:
            r0 = r0 + 1;
            goto L4
        L8:
            TLRPC.Chat r22 = this.this$0.getMessagesController().getChat(Long.valueOf(-r2));
            if (r22 == null) goto L11;
            r1 = r1 + r22.participants_count;
            goto L11
        L12:
            return r1;
        }

        private /* synthetic */ void lambda$onBindViewHolder$1() {
            this.this$0.presentFragment(new PremiumPreviewFragment("noncontacts"));
        }

        private /* synthetic */ void lambda$onBindViewHolder$2(TLObject r1, TLRPC.UserFull r2, TL_account.TL_birthday r3, TLRPC.TL_error r4) {
            if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L6;
            BulletinFactory.of(this.this$0).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.PrivacyBirthdaySetDone)).setDuration(5000).show();
            return;
        L6:
            if (r2 == null) goto L12;
            int r12 = r2.flags2;
            if (r3 != null) goto L10;
            r2.flags2 = r12 & (-33);
        L11:
            r2.birthday = r3;
            this.this$0.getMessagesStorage().updateUserInfo(r2, false);
            goto L12
        L10:
            r2.flags2 = r12 | 32;
        L12:
            if (r4 == null) goto L21;
            String r13 = r4.text;
            if (r13 == null) goto L21;
            if (r13.startsWith("FLOOD_WAIT_") == false) goto L21;
            if (this.this$0.getContext() == null) goto L23;
            PrivacyControlActivity r14 = this.this$0;
            r14.showDialog(new AlertDialog.Builder(r14.getContext(), PrivacyControlActivity.access$1400(this.this$0)).setTitle(LocaleController.getString(R.string.PrivacyBirthdayTooOftenTitle)).setMessage(LocaleController.getString(R.string.PrivacyBirthdayTooOftenMessage)).setPositiveButton(LocaleController.getString(R.string.OK), null).create());
            return;
        L23:
            return;
        L21:
            BulletinFactory.of(this.this$0).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
        }

        private /* synthetic */ void lambda$onBindViewHolder$3(TLRPC.UserFull r7, TL_account.TL_birthday r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda7(this, r9, r7, r8, r10));
        }

        private /* synthetic */ void lambda$onBindViewHolder$4(TL_account.TL_birthday r7) {
            TL_account.updateBirthday r0 = new TL_account.updateBirthday();
            r0.flags |= 1;
            r0.birthday = r7;
            TLRPC.UserFull r1 = this.this$0.getMessagesController().getUserFull(this.this$0.getUserConfig().getClientUserId());
            if (r1 == null) goto L5;
            TL_account.TL_birthday r3 = r1.birthday;
        L7:
            if (r1 == null) goto L9;
            r1.flags2 |= 32;
            r1.birthday = r7;
            this.this$0.getMessagesStorage().updateUserInfo(r1, false);
        L9:
            this.this$0.getMessagesController().invalidateContentSettings();
            this.this$0.getConnectionsManager().sendRequest(r0, new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda6(this, r1, r3), 1024);
            MessagesController.getInstance(PrivacyControlActivity.access$1200(this.this$0)).removeSuggestion(0, "BIRTHDAY_SETUP");
            NotificationCenter.getInstance(PrivacyControlActivity.access$1300(this.this$0)).postNotificationName(NotificationCenter.premiumPromoUpdated, new Object[0]);
            PrivacyControlActivity.m17701$$Nest$mupdateRows(this.this$0, true);
            return;
        L5:
            r3 = null;
            goto L7
        }

        private /* synthetic */ void lambda$onBindViewHolder$5() {
            PrivacyControlActivity r0 = this.this$0;
            r0.showDialog(AlertsCreator.createBirthdayPickerDialog(r0.getContext(), LocaleController.getString(R.string.EditProfileBirthdayTitle), LocaleController.getString(R.string.EditProfileBirthdayButton), null, new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda5(this), null, false, false, this.this$0.getResourceProvider()).create());
        }

        private /* synthetic */ CharSequence lambda$onBindViewHolder$6(Integer r6, Integer r7) {
            if (r6.intValue() != 0) goto L14;
            if (this.this$0.getUserConfig().isPremium() == true) goto L12;
            if (PrivacyControlActivity.m17648$$Nest$fgetlockSpan(this.this$0) != null) goto L9;
            SpannableString r62 = new SpannableString("l");
            ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.msg_mini_lock3);
            r1.translate(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f));
            r62.setSpan(r1, 0, 1, 33);
            PrivacyControlActivity.m17692$$Nest$fputlockSpan(this.this$0, r62);
        L9:
            SpannableStringBuilder r63 = new SpannableStringBuilder();
            r63.append(PrivacyControlActivity.m17648$$Nest$fgetlockSpan(this.this$0));
            r63.append(" ");
            r63.append(LocaleController.formatPluralStringComma("Stars", r7.intValue()));
            return r63;
        L12:
            return LocaleController.formatPluralStringComma("Stars", r7.intValue());
        L14:
            return LocaleController.formatNumber(r7.intValue(), ',');
        }

        private /* synthetic */ void lambda$onBindViewHolder$7(Integer r4) {
            PrivacyControlActivity.m17691$$Nest$fputcurrentStars(this.this$0, r4.intValue());
            AndroidUtilities.updateVisibleRow(PrivacyControlActivity.m17647$$Nest$fgetlistView(this.this$0), PrivacyControlActivity.m17668$$Nest$fgetpriceInfoRow(this.this$0));
            PrivacyControlActivity.m17700$$Nest$mupdateDoneButton(this.this$0);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r4) {
            this.this$0.showDialog(new PremiumFeatureBottomSheet(this.this$0, 27, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return PrivacyControlActivity.m17674$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != PrivacyControlActivity.m17625$$Nest$fgetalwaysShareRow(this.this$0)) goto L5;
        L108:
            return 0;
        L5:
            if (r3 == PrivacyControlActivity.m17652$$Nest$fgetneverShareRow(this.this$0)) goto L108;
            if (r3 == PrivacyControlActivity.m17657$$Nest$fgetp2pRow(this.this$0)) goto L108;
            if (r3 == PrivacyControlActivity.m17672$$Nest$fgetreadPremiumRow(this.this$0)) goto L108;
            if (r3 != PrivacyControlActivity.m17686$$Nest$fgetshareDetailRow(this.this$0)) goto L14;
            return 1;
        L14:
            if (r3 != PrivacyControlActivity.m17637$$Nest$fgetdetailRow(this.this$0)) goto L16;
            return 1;
        L16:
            if (r3 != PrivacyControlActivity.m17638$$Nest$fgetdetailRow2(this.this$0)) goto L18;
            return 1;
        L18:
            if (r3 != PrivacyControlActivity.m17668$$Nest$fgetpriceInfoRow(this.this$0)) goto L20;
            return 1;
        L20:
            if (r3 != PrivacyControlActivity.m17656$$Nest$fgetp2pDetailRow(this.this$0)) goto L22;
            return 1;
        L22:
            if (r3 != PrivacyControlActivity.m17664$$Nest$fgetphotoForRestDescriptionRow(this.this$0)) goto L24;
            return 1;
        L24:
            if (r3 != PrivacyControlActivity.m17670$$Nest$fgetreadDetailRow(this.this$0)) goto L26;
            return 1;
        L26:
            if (r3 != PrivacyControlActivity.m17671$$Nest$fgetreadPremiumDetailRow(this.this$0)) goto L28;
            return 1;
        L28:
            if (r3 != PrivacyControlActivity.m17685$$Nest$fgetsetBirthdayRow(this.this$0)) goto L30;
            return 1;
        L30:
            if (r3 != PrivacyControlActivity.m17688$$Nest$fgetshowGiftIconInfoRow(this.this$0)) goto L32;
            return 1;
        L32:
            if (r3 != PrivacyControlActivity.m17646$$Nest$fgetgiftTypesInfoRow(this.this$0)) goto L35;
            return 1;
        L35:
            if (r3 != PrivacyControlActivity.m17676$$Nest$fgetsectionRow(this.this$0)) goto L37;
            return 2;
        L37:
            if (r3 != PrivacyControlActivity.m17667$$Nest$fgetpriceHeaderRow(this.this$0)) goto L39;
            return 2;
        L39:
            if (r3 != PrivacyControlActivity.m17687$$Nest$fgetshareSectionRow(this.this$0)) goto L41;
            return 2;
        L41:
            if (r3 != PrivacyControlActivity.m17658$$Nest$fgetp2pSectionRow(this.this$0)) goto L43;
            return 2;
        L43:
            if (r3 != PrivacyControlActivity.m17663$$Nest$fgetphoneSectionRow(this.this$0)) goto L45;
            return 2;
        L45:
            if (r3 != PrivacyControlActivity.m17645$$Nest$fgetgiftTypesHeaderRow(this.this$0)) goto L48;
            return 2;
        L48:
            if (r3 != PrivacyControlActivity.m17639$$Nest$fgeteverybodyRow(this.this$0)) goto L50;
            return 3;
        L50:
            if (r3 != PrivacyControlActivity.m17651$$Nest$fgetmyContactsRow(this.this$0)) goto L52;
            return 3;
        L52:
            if (r3 != PrivacyControlActivity.m17653$$Nest$fgetnobodyRow(this.this$0)) goto L54;
            return 3;
        L54:
            if (r3 != PrivacyControlActivity.m17659$$Nest$fgetpayRow(this.this$0)) goto L56;
            return 3;
        L56:
            if (r3 != PrivacyControlActivity.m17662$$Nest$fgetphoneEverybodyRow(this.this$0)) goto L58;
            return 3;
        L58:
            if (r3 != PrivacyControlActivity.m17660$$Nest$fgetphoneContactsRow(this.this$0)) goto L61;
            return 3;
        L61:
            if (r3 != PrivacyControlActivity.m17650$$Nest$fgetmessageRow(this.this$0)) goto L65;
            return 4;
        L65:
            if (r3 != PrivacyControlActivity.m17661$$Nest$fgetphoneDetailRow(this.this$0)) goto L69;
            return 5;
        L69:
            if (r3 != PrivacyControlActivity.m17665$$Nest$fgetphotoForRestRow(this.this$0)) goto L73;
            return 6;
        L73:
            if (r3 != PrivacyControlActivity.m17630$$Nest$fgetcurrentPhotoForRestRow(this.this$0)) goto L77;
            return 7;
        L77:
            if (r3 != PrivacyControlActivity.m17673$$Nest$fgetreadRow(this.this$0)) goto L79;
            return 8;
        L79:
            if (r3 != PrivacyControlActivity.m17689$$Nest$fgetshowGiftIconRow(this.this$0)) goto L81;
            return 8;
        L81:
            if (r3 != PrivacyControlActivity.m17643$$Nest$fgetgiftTypeUniqueRow(this.this$0)) goto L83;
            return 8;
        L83:
            if (r3 != PrivacyControlActivity.m17640$$Nest$fgetgiftTypeChannelsRow(this.this$0)) goto L85;
            return 8;
        L85:
            if (r3 != PrivacyControlActivity.m17642$$Nest$fgetgiftTypePremiumRow(this.this$0)) goto L87;
            return 8;
        L87:
            if (r3 != PrivacyControlActivity.m17644$$Nest$fgetgiftTypeUnlimitedRow(this.this$0)) goto L89;
            return 8;
        L89:
            if (r3 != PrivacyControlActivity.m17641$$Nest$fgetgiftTypeLimitedRow(this.this$0)) goto L92;
            return 8;
        L92:
            if (r3 != PrivacyControlActivity.m17669$$Nest$fgetpriceRow(this.this$0)) goto L96;
            return 9;
        L96:
            if (r3 != PrivacyControlActivity.m17666$$Nest$fgetpriceButtonRow(this.this$0)) goto L99;
            return 10;
        L99:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            int r32 = r3.getAdapterPosition();
            if (r32 != PrivacyControlActivity.m17657$$Nest$fgetp2pRow(this.this$0)) goto L7;
            if (ContactsController.getInstance(PrivacyControlActivity.access$500(this.this$0)).getLoadingPrivacyInfo(3) == true) goto L7;
            return true;
        L7:
            if (r32 != PrivacyControlActivity.m17630$$Nest$fgetcurrentPhotoForRestRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r32 != PrivacyControlActivity.m17664$$Nest$fgetphotoForRestDescriptionRow(this.this$0)) goto L11;
            return true;
        L11:
            if (r32 != PrivacyControlActivity.m17665$$Nest$fgetphotoForRestRow(this.this$0)) goto L13;
            return true;
        L13:
            if (r32 != PrivacyControlActivity.m17673$$Nest$fgetreadRow(this.this$0)) goto L15;
            return true;
        L15:
            if (r32 != PrivacyControlActivity.m17689$$Nest$fgetshowGiftIconRow(this.this$0)) goto L17;
            return true;
        L17:
            if (r32 != PrivacyControlActivity.m17672$$Nest$fgetreadPremiumRow(this.this$0)) goto L19;
            return true;
        L19:
            if (r32 != PrivacyControlActivity.m17643$$Nest$fgetgiftTypeUniqueRow(this.this$0)) goto L21;
            return true;
        L21:
            if (r32 != PrivacyControlActivity.m17640$$Nest$fgetgiftTypeChannelsRow(this.this$0)) goto L23;
            return true;
        L23:
            if (r32 != PrivacyControlActivity.m17642$$Nest$fgetgiftTypePremiumRow(this.this$0)) goto L25;
            return true;
        L25:
            if (r32 != PrivacyControlActivity.m17641$$Nest$fgetgiftTypeLimitedRow(this.this$0)) goto L27;
            return true;
        L27:
            if (r32 != PrivacyControlActivity.m17644$$Nest$fgetgiftTypeUnlimitedRow(this.this$0)) goto L29;
            return true;
        L29:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 12) goto L33;
            if (PrivacyControlActivity.m17697$$Nest$mareAllStarGiftsDisabled(this.this$0) == false) goto L33;
            return false;
        L33:
            if (r32 != PrivacyControlActivity.m17653$$Nest$fgetnobodyRow(this.this$0)) goto L35;
            return true;
        L35:
            if (r32 != PrivacyControlActivity.m17651$$Nest$fgetmyContactsRow(this.this$0)) goto L37;
            return true;
        L37:
            if (r32 != PrivacyControlActivity.m17659$$Nest$fgetpayRow(this.this$0)) goto L39;
            return true;
        L39:
            if (r32 != PrivacyControlActivity.m17639$$Nest$fgeteverybodyRow(this.this$0)) goto L41;
            return true;
        L41:
            if (r32 != PrivacyControlActivity.m17652$$Nest$fgetneverShareRow(this.this$0)) goto L43;
            return true;
        L43:
            if (r32 == PrivacyControlActivity.m17625$$Nest$fgetalwaysShareRow(this.this$0)) goto L66;
            return false;
        L66:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r17, int r18) {
            int r3 = r17.getItemViewType();
            boolean r12 = true;
            boolean r13 = false;
            int r132 = 0;
            r132 = 0;
            int r133 = 0;
            r133 = 0;
            int r134 = 0;
            r134 = 0;
            int r135 = 0;
            r135 = 0;
            int r136 = 0;
            r136 = 0;
            boolean r137 = false;
            boolean r138 = false;
            boolean r139 = false;
            boolean r1310 = false;
            boolean r1311 = false;
            boolean r1312 = false;
            boolean r1313 = false;
            if (r3 != 0) goto L5;
            TextSettingsCell r1 = (TextSettingsCell) r17.itemView;
            r1.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            int r32 = PrivacyControlActivity.m17625$$Nest$fgetalwaysShareRow(this.this$0);
            PrivacyControlActivity r5 = this.this$0;
            if (r18 == r32) goto L394;
            int r33 = PrivacyControlActivity.m17652$$Nest$fgetneverShareRow(r5);
            PrivacyControlActivity r52 = this.this$0;
            if (r18 == r33) goto L450;
            int r34 = PrivacyControlActivity.m17657$$Nest$fgetp2pRow(r52);
            PrivacyControlActivity r4 = this.this$0;
            if (r18 != r34) goto L487;
            if (ContactsController.getInstance(PrivacyControlActivity.access$1100(r4)).getLoadingPrivacyInfo(3) == false) goto L483;
            String r0 = LocaleController.getString(R.string.Loading);
        L484:
            r1.setTextAndValue(LocaleController.getString(R.string.PrivacyP2P2), r0, false);
            return;
        L483:
            r0 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 3);
            goto L484
        L487:
            if (r18 == PrivacyControlActivity.m17672$$Nest$fgetreadPremiumRow(r4)) goto L489;
            return;
        L489:
            if (this.this$0.getUserConfig().isPremium() == false) goto L491;
            int r2 = R.string.PrivacyLastSeenPremiumForPremium;
        L492:
            r1.setText(LocaleController.getString(r2), false);
            r1.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlueText));
            return;
        L491:
            r2 = R.string.PrivacyLastSeenPremium;
            goto L492
        L450:
            if (PrivacyControlActivity.m17629$$Nest$fgetcurrentMinus(r52).size() == 0) goto L452;
            String r22 = LocaleController.formatPluralString("Users", getUsersCount(PrivacyControlActivity.m17629$$Nest$fgetcurrentMinus(this.this$0)), new Object[0]);
        L454:
            if (PrivacyControlActivity.m17632$$Nest$fgetcurrentPlusMiniapps(this.this$0)[PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0)] == false) goto L465;
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 0) goto L465;
            if (PrivacyControlActivity.m17629$$Nest$fgetcurrentMinus(this.this$0) != null) goto L460;
        L463:
            r22 = LocaleController.formatString(R.string.PrivacyValueBots, new Object[0]);
            goto L465
        L460:
            if (PrivacyControlActivity.m17629$$Nest$fgetcurrentMinus(this.this$0).isEmpty() == true) goto L463;
            r22 = LocaleController.formatString(R.string.PrivacyValueBotsAnd, new Object[]{r22});
        L465:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 0) goto L467;
        L473:
            r1.setTextAndValue(LocaleController.getString(R.string.NeverShareWith), r22, false);
        L475:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 12) goto L499;
            r1.setEnabled(!PrivacyControlActivity.m17697$$Nest$mareAllStarGiftsDisabled(this.this$0));
            return;
        L499:
            return;
        L467:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) == 4) goto L473;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) == 9) goto L473;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) == 14) goto L473;
            r1.setTextAndValue(LocaleController.getString(R.string.NeverAllow), r22, false);
            goto L475
        L452:
            r22 = LocaleController.getString(R.string.EmpryUsersPlaceholder);
            goto L454
        L394:
            if (PrivacyControlActivity.m17631$$Nest$fgetcurrentPlus(r5).size() == 0) goto L396;
            String r23 = LocaleController.formatPluralString("Users", getUsersCount(PrivacyControlActivity.m17631$$Nest$fgetcurrentPlus(this.this$0)), new Object[0]);
        L397:
            boolean[] r35 = PrivacyControlActivity.m17633$$Nest$fgetcurrentPlusPremium(this.this$0);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 2) goto L400;
            char r53 = 0;
        L402:
            if (r35[r53] == false) goto L411;
            if (PrivacyControlActivity.m17631$$Nest$fgetcurrentPlus(this.this$0) != null) goto L406;
        L409:
            r23 = LocaleController.formatString(R.string.PrivacyPremium, new Object[0]);
            goto L411
        L406:
            if (PrivacyControlActivity.m17631$$Nest$fgetcurrentPlus(this.this$0).isEmpty() == true) goto L409;
            r23 = LocaleController.formatString(R.string.PrivacyPremiumAnd, new Object[]{r23});
        L411:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) == 10) goto L425;
            if (PrivacyControlActivity.m17632$$Nest$fgetcurrentPlusMiniapps(this.this$0)[PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0)] == false) goto L425;
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) == 0) goto L425;
            if (PrivacyControlActivity.m17631$$Nest$fgetcurrentPlus(this.this$0) != null) goto L420;
        L423:
            r23 = LocaleController.formatString(R.string.PrivacyValueBots, new Object[0]);
            goto L425
        L420:
            if (PrivacyControlActivity.m17631$$Nest$fgetcurrentPlus(this.this$0).isEmpty() == true) goto L423;
            r23 = LocaleController.formatString(R.string.PrivacyValueBotsAnd, new Object[]{r23});
        L425:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 10) goto L428;
            r1.setTextAndValue(LocaleController.getString(R.string.PrivateMessagesExceptions), r23, false);
        L444:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 12) goto L498;
            r1.setEnabled(!PrivacyControlActivity.m17697$$Nest$mareAllStarGiftsDisabled(this.this$0));
            return;
        L498:
            return;
        L428:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 0) goto L430;
        L439:
            String r36 = LocaleController.getString(R.string.AlwaysShareWith);
            if (PrivacyControlActivity.m17652$$Nest$fgetneverShareRow(this.this$0) == (-1)) goto L442;
            r13 = true;
        L442:
            r1.setTextAndValue(r36, r23, r13);
            goto L444
        L430:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) == 4) goto L439;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) == 9) goto L439;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) == 14) goto L439;
            String r37 = LocaleController.getString(R.string.AlwaysAllow);
            if (PrivacyControlActivity.m17652$$Nest$fgetneverShareRow(this.this$0) == (-1)) goto L438;
            r1313 = true;
        L438:
            r1.setTextAndValue(r37, r23, r1313);
            goto L444
        L400:
            r53 = 1;
            goto L402
        L396:
            r23 = LocaleController.getString(R.string.EmpryUsersPlaceholder);
            goto L397
        L5:
            if (r3 == 1) goto L264;
            if (r3 == 2) goto L188;
            if (r3 == 3) goto L68;
            if (r3 == 8) goto L15;
            if (r3 != 9) goto L501;
            SlideIntChooseView r14 = (SlideIntChooseView) r17.itemView;
            if (r18 != PrivacyControlActivity.m17669$$Nest$fgetpriceRow(this.this$0)) goto L494;
            r14.set((int) Utilities.clamp(PrivacyControlActivity.m17634$$Nest$fgetcurrentStars(this.this$0), this.this$0.getMessagesController().starsPaidMessageAmountMax, 1), SlideIntChooseView.Options.make(1, SlideIntChooseView.cut(new int[]{1, 10, 50, 100, 200, 250, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000, 2500, 5000, 7500, 9000, 10000}, (int) this.this$0.getMessagesController().starsPaidMessageAmountMax), 20, new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda3(this)), new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda4(this));
            return;
        L494:
            return;
        L501:
            return;
        L15:
            TextCheckCell r15 = (TextCheckCell) r17.itemView;
            r15.setCheckBoxIcon(0);
            if (r18 != PrivacyControlActivity.m17673$$Nest$fgetreadRow(this.this$0)) goto L20;
            r15.setTextAndCheck(LocaleController.getString(R.string.HideReadTime), PrivacyControlActivity.m17683$$Nest$fgetselectedReadValue(this.this$0), false);
            return;
        L20:
            if (r18 != PrivacyControlActivity.m17689$$Nest$fgetshowGiftIconRow(this.this$0)) goto L24;
            r15.setTextAndCheck(LocaleController.getString(R.string.PrivacyGiftsShowIcon), PrivacyControlActivity.m17678$$Nest$fgetselectedGiftIconValue(this.this$0), false);
            return;
        L24:
            if (r18 != PrivacyControlActivity.m17644$$Nest$fgetgiftTypeUnlimitedRow(this.this$0)) goto L33;
            r15.setTextAndCheck(LocaleController.getString(R.string.PrivacyGiftsTypeUnlimited), PrivacyControlActivity.m17682$$Nest$fgetselectedGiftUnlimitedValue(this.this$0), true);
            if (this.this$0.getUserConfig().isPremium() == false) goto L28;
        L30:
            r15.setCheckBoxIcon(r132);
            return;
        L28:
            if (PrivacyControlActivity.m17682$$Nest$fgetselectedGiftUnlimitedValue(this.this$0) == false) goto L30;
            r132 = R.drawable.permission_locked;
            goto L30
        L33:
            if (r18 != PrivacyControlActivity.m17641$$Nest$fgetgiftTypeLimitedRow(this.this$0)) goto L42;
            r15.setTextAndCheck(LocaleController.getString(R.string.PrivacyGiftsTypeLimited), PrivacyControlActivity.m17679$$Nest$fgetselectedGiftLimitedValue(this.this$0), true);
            if (this.this$0.getUserConfig().isPremium() == false) goto L37;
        L39:
            r15.setCheckBoxIcon(r133);
            return;
        L37:
            if (PrivacyControlActivity.m17679$$Nest$fgetselectedGiftLimitedValue(this.this$0) == false) goto L39;
            r133 = R.drawable.permission_locked;
            goto L39
        L42:
            if (r18 != PrivacyControlActivity.m17643$$Nest$fgetgiftTypeUniqueRow(this.this$0)) goto L51;
            r15.setTextAndCheck(LocaleController.getString(R.string.PrivacyGiftsTypeUnique), PrivacyControlActivity.m17681$$Nest$fgetselectedGiftUniqueValue(this.this$0), true);
            if (this.this$0.getUserConfig().isPremium() == false) goto L46;
        L48:
            r15.setCheckBoxIcon(r134);
            return;
        L46:
            if (PrivacyControlActivity.m17681$$Nest$fgetselectedGiftUniqueValue(this.this$0) == false) goto L48;
            r134 = R.drawable.permission_locked;
            goto L48
        L51:
            if (r18 != PrivacyControlActivity.m17640$$Nest$fgetgiftTypeChannelsRow(this.this$0)) goto L60;
            r15.setTextAndCheck(LocaleController.getString(R.string.PrivacyGiftsTypeFromChannels), PrivacyControlActivity.m17677$$Nest$fgetselectedGiftChannelsValue(this.this$0), true);
            if (this.this$0.getUserConfig().isPremium() == false) goto L55;
        L57:
            r15.setCheckBoxIcon(r135);
            return;
        L55:
            if (PrivacyControlActivity.m17677$$Nest$fgetselectedGiftChannelsValue(this.this$0) == false) goto L57;
            r135 = R.drawable.permission_locked;
            goto L57
        L60:
            if (r18 != PrivacyControlActivity.m17642$$Nest$fgetgiftTypePremiumRow(this.this$0)) goto L495;
            r15.setTextAndCheck(LocaleController.getString(R.string.PrivacyGiftsTypePremium), PrivacyControlActivity.m17680$$Nest$fgetselectedGiftPremiumValue(this.this$0), false);
            if (this.this$0.getUserConfig().isPremium() == false) goto L64;
        L66:
            r15.setCheckBoxIcon(r136);
            return;
        L64:
            if (PrivacyControlActivity.m17680$$Nest$fgetselectedGiftPremiumValue(this.this$0) == false) goto L66;
            r136 = R.drawable.permission_locked;
            goto L66
        L495:
            return;
        L68:
            RadioCell r16 = (RadioCell) r17.itemView;
            r16.setRadioIcon(null);
            if (r18 != PrivacyControlActivity.m17639$$Nest$fgeteverybodyRow(this.this$0)) goto L71;
        L90:
            int r42 = PrivacyControlActivity.m17639$$Nest$fgeteverybodyRow(this.this$0);
            PrivacyControlActivity r7 = this.this$0;
            if (r18 == r42) goto L93;
            int r43 = PrivacyControlActivity.m17651$$Nest$fgetmyContactsRow(r7);
            PrivacyControlActivity r72 = this.this$0;
            if (r18 == r43) goto L105;
            int r44 = PrivacyControlActivity.m17659$$Nest$fgetpayRow(r72);
            PrivacyControlActivity r73 = this.this$0;
            if (r18 != r44) goto L162;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r73) == 10) goto L154;
        L156:
            String r24 = LocaleController.getString(R.string.PrivateMessagesChargePrice);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 3) goto L159;
            boolean r45 = true;
        L160:
            r16.setText(r24, r45, false);
        L185:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 12) goto L496;
            r16.setEnabled(!PrivacyControlActivity.m17697$$Nest$mareAllStarGiftsDisabled(this.this$0), null);
            return;
        L496:
            return;
        L159:
            r45 = false;
            goto L160
        L154:
            if (this.this$0.getUserConfig().isPremium() == true) goto L156;
            r16.setRadioIcon(this.this$0.getContext().getResources().getDrawable(R.drawable.mini_switch_lock).mutate());
            goto L156
        L162:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r73) != 8) goto L166;
            if (this.this$0.getUserConfig().isPremium() == true) goto L166;
        L171:
            r16.setRadioIcon(this.this$0.getContext().getResources().getDrawable(R.drawable.mini_switch_lock).mutate());
        L173:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 3) goto L179;
            String r25 = LocaleController.getString(R.string.P2PNobody);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 1) goto L177;
            boolean r46 = true;
        L178:
            r16.setText(r25, r46, false);
            goto L185
        L177:
            r46 = false;
            goto L178
        L179:
            String r26 = LocaleController.getString(R.string.LastSeenNobody);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 1) goto L182;
            boolean r47 = true;
        L183:
            r16.setText(r26, r47, false);
            goto L185
        L182:
            r47 = false;
        L166:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 10) goto L173;
            if (this.this$0.getMessagesController().newNoncontactPeersRequirePremiumWithoutOwnpremium == true) goto L173;
            if (this.this$0.getUserConfig().isPremium() == true) goto L173;
        L105:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r72) != 8) goto L109;
            if (this.this$0.getUserConfig().isPremium() == true) goto L109;
        L114:
            r16.setRadioIcon(this.this$0.getContext().getResources().getDrawable(R.drawable.mini_switch_lock).mutate());
        L116:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 3) goto L128;
            String r27 = LocaleController.getString(R.string.P2PContacts);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 2) goto L120;
            boolean r48 = true;
        L122:
            if (PrivacyControlActivity.m17653$$Nest$fgetnobodyRow(this.this$0) == (-1)) goto L124;
        L125:
            r1310 = true;
        L126:
            r16.setText(r27, r48, r1310);
            goto L185
        L124:
            if (PrivacyControlActivity.m17659$$Nest$fgetpayRow(this.this$0) == (-1)) goto L126;
        L120:
            r48 = false;
            goto L122
        L128:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 10) goto L139;
            String r28 = LocaleController.getString(R.string.PrivacyMessagesContactsAndPremium);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 2) goto L132;
            boolean r49 = true;
        L134:
            if (PrivacyControlActivity.m17653$$Nest$fgetnobodyRow(this.this$0) == (-1)) goto L136;
        L137:
            r1311 = true;
        L138:
            r16.setText(r28, r49, r1311);
            goto L185
        L136:
            if (PrivacyControlActivity.m17659$$Nest$fgetpayRow(this.this$0) == (-1)) goto L138;
        L132:
            r49 = false;
            goto L134
        L139:
            String r29 = LocaleController.getString(R.string.LastSeenContacts);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 2) goto L142;
            boolean r410 = true;
        L144:
            if (PrivacyControlActivity.m17653$$Nest$fgetnobodyRow(this.this$0) == (-1)) goto L146;
        L147:
            r1312 = true;
        L148:
            r16.setText(r29, r410, r1312);
            goto L185
        L146:
            if (PrivacyControlActivity.m17659$$Nest$fgetpayRow(this.this$0) == (-1)) goto L148;
        L142:
            r410 = false;
        L109:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 10) goto L116;
            if (this.this$0.getMessagesController().newNoncontactPeersRequirePremiumWithoutOwnpremium == true) goto L116;
            if (this.this$0.getUserConfig().isPremium() == true) goto L116;
        L93:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r7) != 3) goto L98;
            String r210 = LocaleController.getString(R.string.P2PEverybody);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 0) goto L97;
            r138 = true;
        L97:
            r16.setText(r210, r138, true);
            goto L185
        L98:
            String r211 = LocaleController.getString(R.string.LastSeenEverybody);
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 0) goto L101;
            r139 = true;
        L101:
            r16.setText(r211, r139, true);
            goto L185
        L71:
            if (r18 == PrivacyControlActivity.m17651$$Nest$fgetmyContactsRow(this.this$0)) goto L90;
            if (r18 == PrivacyControlActivity.m17653$$Nest$fgetnobodyRow(this.this$0)) goto L90;
            if (r18 == PrivacyControlActivity.m17659$$Nest$fgetpayRow(this.this$0)) goto L90;
            if (r18 != PrivacyControlActivity.m17660$$Nest$fgetphoneContactsRow(this.this$0)) goto L85;
            String r212 = LocaleController.getString(R.string.LastSeenContacts);
            if (PrivacyControlActivity.m17635$$Nest$fgetcurrentSubType(this.this$0) != 1) goto L82;
            boolean r411 = true;
        L83:
            r16.setText(r212, r411, false);
            goto L185
        L82:
            r411 = false;
            goto L83
        L85:
            if (r18 != PrivacyControlActivity.m17662$$Nest$fgetphoneEverybodyRow(this.this$0)) goto L185;
            String r213 = LocaleController.getString(R.string.LastSeenEverybody);
            if (PrivacyControlActivity.m17635$$Nest$fgetcurrentSubType(this.this$0) != 0) goto L89;
            r137 = true;
        L89:
            r16.setText(r213, r137, true);
            goto L185
        L188:
            HeaderCell r19 = (HeaderCell) r17.itemView;
            int r38 = PrivacyControlActivity.m17676$$Nest$fgetsectionRow(this.this$0);
            PrivacyControlActivity r8 = this.this$0;
            if (r18 == r38) goto L191;
            int r39 = PrivacyControlActivity.m17687$$Nest$fgetshareSectionRow(r8);
            PrivacyControlActivity r412 = this.this$0;
            if (r18 != r39) goto L249;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r412) != 10) goto L246;
            r19.setText(LocaleController.getString(R.string.PrivateMessagesExceptionsHeader));
            return;
        L246:
            r19.setText(LocaleController.getString(R.string.AddExceptions));
            return;
        L249:
            if (r18 != PrivacyControlActivity.m17658$$Nest$fgetp2pSectionRow(r412)) goto L253;
            r19.setText(LocaleController.getString(R.string.PrivacyP2PHeader));
            return;
        L253:
            if (r18 != PrivacyControlActivity.m17663$$Nest$fgetphoneSectionRow(this.this$0)) goto L257;
            r19.setText(LocaleController.getString(R.string.PrivacyPhoneTitle2));
            return;
        L257:
            if (r18 != PrivacyControlActivity.m17667$$Nest$fgetpriceHeaderRow(this.this$0)) goto L261;
            r19.setText(LocaleController.getString(R.string.PrivateMessagesPriceHeader));
            return;
        L261:
            if (r18 != PrivacyControlActivity.m17645$$Nest$fgetgiftTypesHeaderRow(this.this$0)) goto L497;
            r19.setText(LocaleController.getString(R.string.PrivacyGiftsTypeHeader));
            return;
        L497:
            return;
        L191:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r8) != 6) goto L195;
            r19.setText(LocaleController.getString(R.string.PrivacyPhoneTitle));
            return;
        L195:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 5) goto L199;
            r19.setText(LocaleController.getString(R.string.PrivacyForwardsTitle));
            return;
        L199:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 4) goto L203;
            r19.setText(LocaleController.getString(R.string.PrivacyProfilePhotoTitle));
            return;
        L203:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 9) goto L207;
            r19.setText(LocaleController.getString(R.string.PrivacyBioTitle));
            return;
        L207:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 14) goto L211;
            r19.setText(LocaleController.getString(R.string.PrivacyMusicTitle));
            return;
        L211:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 3) goto L215;
            r19.setText(LocaleController.getString(R.string.P2PEnabledWith));
            return;
        L215:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 2) goto L219;
            r19.setText(LocaleController.getString(R.string.WhoCanCallMe));
            return;
        L219:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 1) goto L223;
            r19.setText(LocaleController.getString(R.string.WhoCanAddMe));
            return;
        L223:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 8) goto L227;
            r19.setText(LocaleController.getString(R.string.PrivacyVoiceMessagesTitle));
            return;
        L227:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 10) goto L231;
            r19.setText(LocaleController.getString(R.string.PrivacyMessagesTitle));
            return;
        L231:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 11) goto L235;
            r19.setText(LocaleController.getString(R.string.PrivacyBirthdayTitle));
            return;
        L235:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 12) goto L238;
            r19.setText(LocaleController.getString(R.string.PrivacyGiftsTitle));
            return;
        L238:
            r19.setText(LocaleController.getString(R.string.LastSeenTitle));
            return;
        L264:
            TextInfoPrivacyCell r110 = (TextInfoPrivacyCell) r17.itemView;
            if (r18 != PrivacyControlActivity.m17638$$Nest$fgetdetailRow2(this.this$0)) goto L268;
            r110.setText(LocaleController.getString(R.string.PrivateMessagesChargePriceInfo));
        L389:
            r110.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
            return;
        L268:
            if (r18 != PrivacyControlActivity.m17637$$Nest$fgetdetailRow(this.this$0)) goto L273;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 10) goto L273;
            r110.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.PrivacyMessagesInfo), new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda1(this)));
        L273:
            if (r18 != PrivacyControlActivity.m17637$$Nest$fgetdetailRow(this.this$0)) goto L278;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 8) goto L278;
            r110.setText(LocaleController.getString(R.string.PrivacyVoiceMessagesInfo));
        L278:
            if (r18 != PrivacyControlActivity.m17685$$Nest$fgetsetBirthdayRow(this.this$0)) goto L280;
            r110.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.PrivacyBirthdaySet), new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda2(this)), true));
            goto L389
        L280:
            int r310 = PrivacyControlActivity.m17637$$Nest$fgetdetailRow(this.this$0);
            PrivacyControlActivity r82 = this.this$0;
            if (r18 != r310) goto L322;
            int r214 = PrivacyControlActivity.m17675$$Nest$fgetrulesType(r82);
            PrivacyControlActivity r311 = this.this$0;
            if (r214 != 6) goto L295;
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(r311) == 1) goto L287;
        L289:
            r12 = false;
        L290:
            PrivacyControlActivity.m17695$$Nest$fputprevSubtypeContacts(r311, r12);
            if (r12 == false) goto L293;
            r110.setText(LocaleController.getString(R.string.PrivacyPhoneInfo3));
            goto L389
        L293:
            SpannableStringBuilder r215 = new SpannableStringBuilder();
            String r312 = String.format(Locale.ENGLISH, "https://t.me/+%s", new Object[]{this.this$0.getUserConfig().getClientPhone()});
            SpannableString r413 = new SpannableString(r312);
            r413.setSpan(new AnonymousClass2(this, r312), 0, r312.length(), 33);
            r215.append(LocaleController.getString(R.string.PrivacyPhoneInfo)).append("\n\n").append(LocaleController.getString(R.string.PrivacyPhoneInfo4)).append("\n").append(r413);
            r110.setText(r215);
            goto L389
        L287:
            if (PrivacyControlActivity.m17635$$Nest$fgetcurrentSubType(this.this$0) != 1) goto L289;
        L295:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r311) != 5) goto L298;
            r110.setText(LocaleController.getString(R.string.PrivacyForwardsInfo));
            goto L389
        L298:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 4) goto L301;
            r110.setText(LocaleController.getString(R.string.PrivacyProfilePhotoInfo));
            goto L389
        L301:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 9) goto L304;
            r110.setText(LocaleController.getString(R.string.PrivacyBioInfo3));
            goto L389
        L304:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 14) goto L307;
            r110.setText(LocaleController.getString(R.string.PrivacyMusicInfo3));
            goto L389
        L307:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 11) goto L310;
            r110.setText(LocaleController.getString(R.string.PrivacyBirthdayInfo));
            goto L389
        L310:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 12) goto L313;
            r110.setText(LocaleController.getString(R.string.PrivacyGiftsInfo));
            goto L389
        L313:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 3) goto L316;
            r110.setText(LocaleController.getString(R.string.PrivacyCallsP2PHelp));
            goto L389
        L316:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 2) goto L319;
            r110.setText(LocaleController.getString(R.string.WhoCanCallMeInfo));
            goto L389
        L319:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 1) goto L321;
            r110.setText(LocaleController.getString(R.string.WhoCanAddMeInfo));
            goto L389
        L321:
            r110.setText(LocaleController.getString(R.string.CustomHelp));
            goto L389
        L322:
            int r313 = PrivacyControlActivity.m17686$$Nest$fgetshareDetailRow(r82);
            PrivacyControlActivity r83 = this.this$0;
            if (r18 != r313) goto L368;
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r83) != 6) goto L328;
            r110.setText(LocaleController.getString(R.string.PrivacyPhoneInfo2));
            goto L389
        L328:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 5) goto L330;
            r110.setText(LocaleController.getString(R.string.PrivacyForwardsInfo2));
            goto L389
        L330:
            int r216 = PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0);
            PrivacyControlActivity r314 = this.this$0;
            if (r216 != 4) goto L340;
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(r314) != 2) goto L336;
            r110.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PrivacyProfilePhotoInfo5)));
            goto L389
        L336:
            if (PrivacyControlActivity.m17636$$Nest$fgetcurrentType(this.this$0) != 0) goto L338;
            r110.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PrivacyProfilePhotoInfo3)));
            goto L389
        L338:
            r110.setText(LocaleController.getString(R.string.PrivacyProfilePhotoInfo4));
            goto L389
        L340:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(r314) != 3) goto L343;
            r110.setText(LocaleController.getString(R.string.CustomP2PInfo));
            goto L389
        L343:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 9) goto L346;
            r110.setText(LocaleController.getString(R.string.PrivacyBioInfo));
            goto L389
        L346:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 14) goto L349;
            r110.setText(LocaleController.getString(R.string.PrivacyMusicInfo));
            goto L389
        L349:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 11) goto L352;
            r110.setText(LocaleController.getString(R.string.PrivacyBirthdayInfo3));
            goto L389
        L352:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 2) goto L355;
            r110.setText(LocaleController.getString(R.string.CustomCallInfo));
            goto L389
        L355:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 1) goto L358;
            r110.setText(LocaleController.getString(R.string.CustomShareInfo));
            goto L389
        L358:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 12) goto L361;
            r110.setText(LocaleController.getString(R.string.CustomShareGiftsInfo));
            goto L389
        L361:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 8) goto L364;
            r110.setText(LocaleController.getString(R.string.PrivacyVoiceMessagesInfo2));
            goto L389
        L364:
            if (PrivacyControlActivity.m17675$$Nest$fgetrulesType(this.this$0) != 10) goto L366;
            r110.setText(LocaleController.getString(R.string.PrivateMessagesExceptionsInfo));
            goto L389
        L366:
            r110.setText(LocaleController.getString(R.string.CustomShareSettingsHelp));
            goto L389
        L368:
            if (r18 != PrivacyControlActivity.m17664$$Nest$fgetphotoForRestDescriptionRow(r83)) goto L371;
            r110.setText(LocaleController.getString(R.string.PhotoForRestDescription));
            goto L389
        L371:
            if (r18 != PrivacyControlActivity.m17670$$Nest$fgetreadDetailRow(this.this$0)) goto L373;
            r110.setText(LocaleController.getString(R.string.HideReadTimeInfo));
            goto L389
        L373:
            int r315 = PrivacyControlActivity.m17671$$Nest$fgetreadPremiumDetailRow(this.this$0);
            PrivacyControlActivity r414 = this.this$0;
            if (r18 == r315) goto L376;
            int r316 = PrivacyControlActivity.m17668$$Nest$fgetpriceInfoRow(r414);
            PrivacyControlActivity r415 = this.this$0;
            if (r18 != r316) goto L384;
            float r217 = r415.getMessagesController().starsPaidMessageCommissionPermille / 1000.0f;
            r110.setText(LocaleController.formatString(R.string.PrivateMessagesPriceInfo, new Object[]{AffiliateProgramFragment.percents(this.this$0.getMessagesController().starsPaidMessageCommissionPermille), String.valueOf(((int) (((PrivacyControlActivity.m17634$$Nest$fgetcurrentStars(this.this$0) * r217) / 1000.0d) * this.this$0.getMessagesController().starsUsdWithdrawRate1000)) / 100.0d)}));
            goto L389
        L384:
            if (r18 != PrivacyControlActivity.m17688$$Nest$fgetshowGiftIconInfoRow(r415)) goto L387;
            SpannableString r02 = new SpannableString(ImageLoader.AUTOPLAY_FILTER);
            ColoredImageSpan r218 = new ColoredImageSpan(R.drawable.msg_input_gift);
            r218.setScale(0.583f, 0.583f);
            r02.setSpan(r218, 0, 1, 33);
            r110.setText(LocaleController.formatSpannable(R.string.PrivacyGiftsShowIconInfo, new Object[]{r02}));
            goto L389
        L387:
            if (r18 != PrivacyControlActivity.m17646$$Nest$fgetgiftTypesInfoRow(this.this$0)) goto L389;
            r110.setText(LocaleController.getString(R.string.PrivacyGiftsTypeInfo));
            goto L389
        L376:
            if (r414.getUserConfig().isPremium() == false) goto L378;
            int r03 = R.string.PrivacyLastSeenPremiumInfoForPremium;
        L379:
            r110.setText(LocaleController.getString(r03));
            goto L389
        L378:
            r03 = R.string.PrivacyLastSeenPremiumInfo;
            goto L379
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v36, types: [android.view.ViewGroup, android.widget.FrameLayout] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r10, int r11) {
            switch(r11) {
                case 0: goto L25;
                case 1: goto L24;
                case 2: goto L23;
                case 3: goto L22;
                case 4: goto L21;
                case 5: goto L4;
                case 6: goto L16;
                case 7: goto L9;
                case 8: goto L8;
                case 9: goto L7;
                case 10: goto L5;
                default: goto L4;
            };
        L4:
            View r102 = new ShadowSectionCell(this.mContext);
        L27:
            return new RecyclerListView.Holder(r102);
        L5:
            ?? r112 = new FrameLayout(this.mContext);
            ButtonWithCounterView r1 = new ButtonWithCounterView(this.mContext, PrivacyControlActivity.access$1000(this.this$0)).setRound();
            ColoredImageSpan r2 = new ColoredImageSpan(R.drawable.msg_mini_lock3);
            SpannableStringBuilder r3 = new SpannableStringBuilder(LocaleController.getString(R.string.PrivateMessagesChargePremiumLocked));
            r3.append(" l");
            r3.setSpan(r2, r3.length() - 1, r3.length(), 33);
            r1.setText(r3, false);
            r1.setOnClickListener(new PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda0(this));
            r112.addView(r1, LayoutHelper.createFrame(-1, 48.0f, 119, 18.0f, 0.0f, 18.0f, 16.0f));
            TextSettingsCell r113 = r112;
        L6:
            r102 = r113;
            goto L27
        L7:
            r102 = new SlideIntChooseView(this.mContext, PrivacyControlActivity.access$900(this.this$0));
            goto L27
        L8:
            r102 = new TextCheckCell(this.mContext, PrivacyControlActivity.access$800(this.this$0));
            goto L27
        L9:
            PrivacyControlActivity.m17693$$Nest$fputoldAvatarView(this.this$0, new BackupImageView(this.this$0.getContext()));
            PrivacyControlActivity.m17694$$Nest$fputoldPhotoCell(this.this$0, new AnonymousClass1(this, this.this$0.getContext()));
            if (PrivacyControlActivity.m17626$$Nest$fgetavatarForRest(this.this$0) == null) goto L15;
            TLRPC.Photo r103 = PrivacyControlActivity.m17627$$Nest$fgetavatarForRestPhoto(this.this$0);
            PrivacyControlActivity r114 = this.this$0;
            if (r103 == null) goto L14;
            PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(r114).setImage(ImageLocation.getForPhoto(PrivacyControlActivity.m17626$$Nest$fgetavatarForRest(this.this$0), PrivacyControlActivity.m17627$$Nest$fgetavatarForRestPhoto(this.this$0)), "50_50", null, UserConfig.getInstance(PrivacyControlActivity.access$600(this.this$0)).getCurrentUser());
            goto L15
        L14:
            PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(r114).setImage(ImageLocation.getForLocal(PrivacyControlActivity.m17626$$Nest$fgetavatarForRest(this.this$0).location), "50_50", null, UserConfig.getInstance(PrivacyControlActivity.access$700(this.this$0)).getCurrentUser());
        L15:
            PrivacyControlActivity.m17655$$Nest$fgetoldPhotoCell(this.this$0).addView(PrivacyControlActivity.m17654$$Nest$fgetoldAvatarView(this.this$0), LayoutHelper.createFrame(30, 30.0f, 16, 21.0f, 0.0f, 21.0f, 0.0f));
            PrivacyControlActivity.m17655$$Nest$fgetoldPhotoCell(this.this$0).setText(LocaleController.getString(R.string.RemovePublicPhoto), false);
            PrivacyControlActivity.m17655$$Nest$fgetoldPhotoCell(this.this$0).getImageView().setVisibility(0);
            PrivacyControlActivity.m17655$$Nest$fgetoldPhotoCell(this.this$0).setBackgroundDrawable(Theme.getSelectorDrawable(false));
            TextCell r104 = PrivacyControlActivity.m17655$$Nest$fgetoldPhotoCell(this.this$0);
            int r115 = Theme.key_text_RedRegular;
            r104.setColors(r115, r115);
            r102 = PrivacyControlActivity.m17655$$Nest$fgetoldPhotoCell(this.this$0);
            goto L27
        L16:
            PrivacyControlActivity.m17696$$Nest$fputsetAvatarCell(this.this$0, new TextCell(this.this$0.getContext()));
            TLRPC.PhotoSize r116 = PrivacyControlActivity.m17626$$Nest$fgetavatarForRest(this.this$0);
            PrivacyControlActivity r12 = this.this$0;
            if (r116 != null) goto L19;
            PrivacyControlActivity.m17684$$Nest$fgetsetAvatarCell(r12).setTextAndIcon(LocaleController.formatString(R.string.SetPhotoForRest, new Object[0]), R.drawable.msg_addphoto, false);
        L20:
            PrivacyControlActivity.m17684$$Nest$fgetsetAvatarCell(this.this$0).setBackgroundDrawable(Theme.getSelectorDrawable(false));
            PrivacyControlActivity.m17684$$Nest$fgetsetAvatarCell(this.this$0).setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            PrivacyControlActivity.m17690$$Nest$fputcameraDrawable(this.this$0, new RLottieDrawable(R.raw.camera_outline, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), false, null));
            PrivacyControlActivity.m17684$$Nest$fgetsetAvatarCell(this.this$0).imageView.setTranslationX(-AndroidUtilities.dp(8.0f));
            PrivacyControlActivity.m17684$$Nest$fgetsetAvatarCell(this.this$0).imageView.setAnimation(PrivacyControlActivity.m17628$$Nest$fgetcameraDrawable(this.this$0));
            r102 = PrivacyControlActivity.m17684$$Nest$fgetsetAvatarCell(this.this$0);
            goto L27
        L19:
            PrivacyControlActivity.m17684$$Nest$fgetsetAvatarCell(r12).setTextAndIcon(LocaleController.formatString("UpdatePhotoForRest", R.string.UpdatePhotoForRest, new Object[0]), R.drawable.msg_addphoto, true);
            goto L20
        L21:
            r102 = PrivacyControlActivity.m17649$$Nest$fgetmessageCell(this.this$0);
            goto L27
        L22:
            r102 = new RadioCell(this.mContext);
            goto L27
        L23:
            r102 = new HeaderCell(this.mContext);
            goto L27
        L24:
            r102 = new TextInfoPrivacyCell(this.mContext);
            goto L27
        L25:
            TextSettingsCell r117 = new TextSettingsCell(this.mContext);
            r117.setCanDisable(true);
            r113 = r117;
            goto L6
        }
    }

    public class MessageCell extends FrameLayout {
        private Drawable backgroundDrawable;
        private BackgroundGradientDrawable.Disposable backgroundGradientDisposable;
        private ChatMessageCell cell;
        private HintView hintView;
        private final Runnable invalidateRunnable;
        private MessageObject messageObject;
        private Drawable shadowDrawable;
        final /* synthetic */ PrivacyControlActivity this$0;

        /* renamed from: org.telegram.ui.PrivacyControlActivity$MessageCell$1, reason: invalid class name */
        public class AnonymousClass1 implements ChatMessageCell.ChatMessageCellDelegate {
            final /* synthetic */ MessageCell this$1;
            final /* synthetic */ PrivacyControlActivity val$this$0;

            public AnonymousClass1(MessageCell r1, PrivacyControlActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }
        }

        /* renamed from: -$$Nest$fgetcell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ChatMessageCell m17706$$Nest$fgetcell(MessageCell r0) {
            return r0.cell;
        }

        /* renamed from: -$$Nest$fgethintView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HintView m17707$$Nest$fgethintView(MessageCell r0) {
            return r0.hintView;
        }

        /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MessageObject m17708$$Nest$fgetmessageObject(MessageCell r0) {
            return r0.messageObject;
        }

        public MessageCell(PrivacyControlActivity r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            this.invalidateRunnable = new PrivacyControlActivity$MessageCell$$ExternalSyntheticLambda0(this);
            setWillNotDraw(false);
            setClipToPadding(false);
            this.shadowDrawable = Theme.getThemedDrawableByKey(r11, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow);
            setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f));
            int r1 = (int) (System.currentTimeMillis() / 1000);
            TLRPC.User r2 = MessagesController.getInstance(PrivacyControlActivity.access$100(r10)).getUser(Long.valueOf(UserConfig.getInstance(PrivacyControlActivity.access$000(r10)).getClientUserId()));
            TLRPC.TL_message r3 = new TLRPC.TL_message();
            r3.message = LocaleController.getString(R.string.PrivacyForwardsMessageLine);
            r3.date = r1 - 3540;
            r3.dialog_id = 1;
            r3.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED;
            r3.from_id = new TLRPC.TL_peerUser();
            r3.id = 1;
            TLRPC.TL_messageFwdHeader r6 = new TLRPC.TL_messageFwdHeader();
            r3.fwd_from = r6;
            r6.from_name = ContactsController.formatName(r2.first_name, r2.last_name);
            r3.media = new TLRPC.TL_messageMediaEmpty();
            r3.out = false;
            TLRPC.TL_peerUser r22 = new TLRPC.TL_peerUser();
            r3.peer_id = r22;
            r22.user_id = UserConfig.getInstance(PrivacyControlActivity.access$200(r10)).getClientUserId();
            MessageObject r23 = new MessageObject(PrivacyControlActivity.access$300(r10), r3, true, false);
            this.messageObject = r23;
            r23.eventId = 1;
            r23.resetLayout();
            ChatMessageCell r24 = new ChatMessageCell(r11, PrivacyControlActivity.access$400(r10));
            this.cell = r24;
            r24.setDelegate(new AnonymousClass1(this, r10));
            ChatMessageCell r102 = this.cell;
            r102.isChat = false;
            r102.setFullyDraw(true);
            this.cell.setMessageObject(this.messageObject, null, false, false, false);
            addView(this.cell, LayoutHelper.createLinear(-1, -2));
            HintView r103 = new HintView(r11, 1, true);
            this.hintView = r103;
            addView(r103, LayoutHelper.createFrame(-2, -2.0f, 51, 19.0f, 0.0f, 19.0f, 0.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            super.dispatchDraw(r2);
            this.hintView.showForMessageCell(this.cell, false);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchSetPressed(boolean r1) {
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.cell.invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BackgroundGradientDrawable.Disposable r0 = this.backgroundGradientDisposable;
            if (r0 == null) goto L6;
            r0.dispose();
            this.backgroundGradientDisposable = null;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            Drawable r0 = Theme.getCachedWallpaperNonBlocking();
            if (r0 != null) goto L5;
        L10:
            Drawable r02 = this.backgroundDrawable;
            if ((r02 instanceof ColorDrawable) == false) goto L13;
        L25:
            r02.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            Drawable r03 = this.backgroundDrawable;
            if ((r03 instanceof BackgroundGradientDrawable) == false) goto L28;
            this.backgroundGradientDisposable = ((BackgroundGradientDrawable) r03).drawExactBoundsSize(r7, this);
        L29:
            this.shadowDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            this.shadowDrawable.draw(r7);
            return;
        L28:
            r03.draw(r7);
            goto L29
        L13:
            if ((r02 instanceof GradientDrawable) == true) goto L25;
            if ((r02 instanceof MotionBackgroundDrawable) == true) goto L25;
            if ((r02 instanceof BitmapDrawable) == true) goto L20;
            super.onDraw(r7);
            goto L29
        L20:
            if (((BitmapDrawable) r02).getTileModeX() != Shader.TileMode.REPEAT) goto L22;
            r7.save();
            float r04 = 2.0f / AndroidUtilities.density;
            r7.scale(r04, r04);
            this.backgroundDrawable.setBounds(0, 0, (int) Math.ceil(getMeasuredWidth() / r04), (int) Math.ceil(getMeasuredHeight() / r04));
        L23:
            this.backgroundDrawable.draw(r7);
            r7.restore();
            goto L29
        L22:
            int r05 = getMeasuredHeight();
            float r1 = Math.max(getMeasuredWidth() / this.backgroundDrawable.getIntrinsicWidth(), r05 / this.backgroundDrawable.getIntrinsicHeight());
            int r3 = (int) Math.ceil(this.backgroundDrawable.getIntrinsicWidth() * r1);
            int r12 = (int) Math.ceil(this.backgroundDrawable.getIntrinsicHeight() * r1);
            int r4 = (getMeasuredWidth() - r3) / 2;
            int r06 = (r05 - r12) / 2;
            r7.save();
            r7.clipRect(0, 0, r3, getMeasuredHeight());
            this.backgroundDrawable.setBounds(r4, r06, r3 + r4, r12 + r06);
            goto L23
        L5:
            if (this.backgroundDrawable == r0) goto L10;
            BackgroundGradientDrawable.Disposable r13 = this.backgroundGradientDisposable;
            if (r13 == null) goto L9;
            r13.dispose();
            this.backgroundGradientDisposable = null;
        L9:
            this.backgroundDrawable = r0;
            goto L10
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: $r8$lambda$-ssKo-Wr6L8GSTw3x3Y02eA54vc, reason: not valid java name */
    public static /* synthetic */ void m17616$r8$lambda$ssKoWr6L8GSTw3x3Y02eA54vc(PrivacyControlActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$0o3BObn6pM7dAnNh74UnZCV2VG4(PrivacyControlActivity r0, TLRPC.TL_error r1, TLObject r2, AtomicInteger r3) {
        r0.lambda$applyCurrentPrivacySettings$20(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$2k_w2TQOp91lkuZhATqgqb24OwM(PrivacyControlActivity r0) {
        r0.lambda$createView$7();
    }

    /* renamed from: $r8$lambda$3OTqSVhEKwaLQJ5arg1ds-CB2a8, reason: not valid java name */
    public static /* synthetic */ void m17617$r8$lambda$3OTqSVhEKwaLQJ5arg1dsCB2a8(PrivacyControlActivity r0, TLObject r1) {
        r0.lambda$didUploadPhoto$0(r1);
    }

    /* renamed from: $r8$lambda$6YGR5ExIaDsQ-fxK_N5c69bh85w, reason: not valid java name */
    public static /* synthetic */ void m17618$r8$lambda$6YGR5ExIaDsQfxK_N5c69bh85w(PrivacyControlActivity r0, DialogInterface r1) {
        r0.lambda$createView$5(r1);
    }

    /* renamed from: $r8$lambda$85icQLxxQow-JjNRgVxZF2DSYls, reason: not valid java name */
    public static /* synthetic */ void m17619$r8$lambda$85icQLxxQowJjNRgVxZF2DSYls(PrivacyControlActivity r0, boolean[] r1, TLRPC.GlobalPrivacySettings r2, TL_account.setGlobalPrivacySettings r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyCurrentPrivacySettings$17(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$GnziGnQUheWpTRoW1FdCE1cmBnQ(PrivacyControlActivity r0, TLRPC.GlobalPrivacySettings r1, TL_account.setGlobalPrivacySettings r2, AtomicInteger r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyCurrentPrivacySettings$23(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$JCBa1yz7BbU1OEayqsS5QpmZEF0(PrivacyControlActivity r0, TLRPC.TL_error r1, boolean[] r2, TLRPC.GlobalPrivacySettings r3, TL_account.setGlobalPrivacySettings r4) {
        r0.lambda$applyCurrentPrivacySettings$16(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$LxPsmzuJv6Jns19eDxafNH9d6RQ(PrivacyControlActivity r0, SharedPreferences r1, AlertDialog r2, int r3) {
        r0.lambda$processDone$26(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MTsO0Wx8cKy98TqCTS8abWRkEDk(PrivacyControlActivity r0, AtomicInteger r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$applyCurrentPrivacySettings$19(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$NPRUw1VZrsPecsGLT-kDaX9zLAg, reason: not valid java name */
    public static /* synthetic */ void m17620$r8$lambda$NPRUw1VZrsPecsGLTkDaX9zLAg(PrivacyControlActivity r0, TLRPC.GlobalPrivacySettings r1, TL_account.setGlobalPrivacySettings r2, AtomicInteger r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyCurrentPrivacySettings$25(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$OGCUO1uhokWzbaw35lLhmFrsniE(PrivacyControlActivity r0, TLRPC.PhotoSize r1, TLRPC.InputFile r2, TLRPC.InputFile r3, double r4, TLRPC.VideoSize r6, TLRPC.PhotoSize r7) {
        r0.lambda$didUploadPhoto$2(r1, r2, r3, r4, r6, r7);
    }

    /* renamed from: $r8$lambda$R7Nw8kcynJwV7tJ9U-vLM_zGZtU, reason: not valid java name */
    public static /* synthetic */ void m17621$r8$lambda$R7Nw8kcynJwV7tJ9UvLM_zGZtU(PrivacyControlActivity r0) {
        r0.lambda$showPremiumBulletin$10();
    }

    public static /* synthetic */ void $r8$lambda$SyFgD8qEUmpG7VMU2I9H0f_PsI8(PrivacyControlActivity r0, View r1, int r2) {
        r0.lambda$createView$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$T5s2cHcBdf6K0zQBRzRWqKnhDp8(PrivacyControlActivity r0, int r1, boolean r2, boolean r3, boolean r4, ArrayList r5) {
        r0.lambda$createView$8(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$a89puv6i8p8uoCQWCggfPP7-vy0, reason: not valid java name */
    public static /* synthetic */ void m17622$r8$lambda$a89puv6i8p8uoCQWCggfPP7vy0(PrivacyControlActivity r0) {
        r0.lambda$createView$3();
    }

    /* renamed from: $r8$lambda$bPX239E0nMOn6TIIK-m5ha0R0oQ, reason: not valid java name */
    public static /* synthetic */ void m17623$r8$lambda$bPX239E0nMOn6TIIKm5ha0R0oQ(PrivacyControlActivity r0, TLRPC.TL_error r1, TLObject r2, boolean[] r3) {
        r0.lambda$applyCurrentPrivacySettings$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$eiDpoieJ4TwKaBTew2sexs3E9gA(PrivacyControlActivity r0, AlertDialog r1, int r2) {
        r0.lambda$finished$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fDwhaA5VELYvf8QTjuuVq4O8dlQ(PrivacyControlActivity r0, boolean[] r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$applyCurrentPrivacySettings$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$fNE3EJ8QksZZCQ2_PliTvvlEyy4(PrivacyControlActivity r0, boolean[] r1) {
        r0.lambda$applyCurrentPrivacySettings$13(r1);
    }

    /* renamed from: $r8$lambda$hLhVvDXwJFi_-vFRVG4Xn07NOa0, reason: not valid java name */
    public static /* synthetic */ void m17624$r8$lambda$hLhVvDXwJFi_vFRVG4Xn07NOa0(PrivacyControlActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$hxYd1VDGffLE0cR0FnKn_DRB_dU(PrivacyControlActivity r0, TLRPC.GlobalPrivacySettings r1, TL_account.setGlobalPrivacySettings r2, AtomicInteger r3) {
        r0.lambda$applyCurrentPrivacySettings$22(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jFAh15gvtqaGjs2iKJ5LI8QJvlQ(PrivacyControlActivity r0, int r1, AlertDialog r2, int r3) {
        r0.lambda$finished$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$m9pq7hTbZg_Oqvc6bCKfiHNjVZE(PrivacyControlActivity r0, TLRPC.GlobalPrivacySettings r1, TL_account.setGlobalPrivacySettings r2, AtomicInteger r3) {
        r0.lambda$applyCurrentPrivacySettings$24(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nN_DLaOHisteRSIgWAUhurvjMoo(PrivacyControlActivity r0, TLRPC.TL_error r1, TLObject r2, AtomicInteger r3) {
        r0.lambda$applyCurrentPrivacySettings$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nQ0e258vBx5D9HCiJLJPh6RYrJ8(PrivacyControlActivity r0, AtomicInteger r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$applyCurrentPrivacySettings$21(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$oTLVYxj7qvAGG6ap5_4RXRIJrtM(PrivacyControlActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$didUploadPhoto$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rD7gKLWvSgvYugH_Wd72fnx0sTE() {
    }

    public static /* synthetic */ void $r8$lambda$rMQJwMD5kBAYXhyzihBaCnbUunM(PrivacyControlActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$28(r1, r2);
    }

    /* renamed from: -$$Nest$fgetalwaysShareRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17625$$Nest$fgetalwaysShareRow(PrivacyControlActivity r0) {
        return r0.alwaysShareRow;
    }

    /* renamed from: -$$Nest$fgetavatarForRest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.PhotoSize m17626$$Nest$fgetavatarForRest(PrivacyControlActivity r0) {
        return r0.avatarForRest;
    }

    /* renamed from: -$$Nest$fgetavatarForRestPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Photo m17627$$Nest$fgetavatarForRestPhoto(PrivacyControlActivity r0) {
        return r0.avatarForRestPhoto;
    }

    /* renamed from: -$$Nest$fgetcameraDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieDrawable m17628$$Nest$fgetcameraDrawable(PrivacyControlActivity r0) {
        return r0.cameraDrawable;
    }

    /* renamed from: -$$Nest$fgetcurrentMinus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m17629$$Nest$fgetcurrentMinus(PrivacyControlActivity r0) {
        return r0.currentMinus;
    }

    /* renamed from: -$$Nest$fgetcurrentPhotoForRestRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17630$$Nest$fgetcurrentPhotoForRestRow(PrivacyControlActivity r0) {
        return r0.currentPhotoForRestRow;
    }

    /* renamed from: -$$Nest$fgetcurrentPlus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m17631$$Nest$fgetcurrentPlus(PrivacyControlActivity r0) {
        return r0.currentPlus;
    }

    /* renamed from: -$$Nest$fgetcurrentPlusMiniapps, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean[] m17632$$Nest$fgetcurrentPlusMiniapps(PrivacyControlActivity r0) {
        return r0.currentPlusMiniapps;
    }

    /* renamed from: -$$Nest$fgetcurrentPlusPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean[] m17633$$Nest$fgetcurrentPlusPremium(PrivacyControlActivity r0) {
        return r0.currentPlusPremium;
    }

    /* renamed from: -$$Nest$fgetcurrentStars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m17634$$Nest$fgetcurrentStars(PrivacyControlActivity r2) {
        return r2.currentStars;
    }

    /* renamed from: -$$Nest$fgetcurrentSubType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17635$$Nest$fgetcurrentSubType(PrivacyControlActivity r0) {
        return r0.currentSubType;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17636$$Nest$fgetcurrentType(PrivacyControlActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetdetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17637$$Nest$fgetdetailRow(PrivacyControlActivity r0) {
        return r0.detailRow;
    }

    /* renamed from: -$$Nest$fgetdetailRow2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17638$$Nest$fgetdetailRow2(PrivacyControlActivity r0) {
        return r0.detailRow2;
    }

    /* renamed from: -$$Nest$fgeteverybodyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17639$$Nest$fgeteverybodyRow(PrivacyControlActivity r0) {
        return r0.everybodyRow;
    }

    /* renamed from: -$$Nest$fgetgiftTypeChannelsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17640$$Nest$fgetgiftTypeChannelsRow(PrivacyControlActivity r0) {
        return r0.giftTypeChannelsRow;
    }

    /* renamed from: -$$Nest$fgetgiftTypeLimitedRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17641$$Nest$fgetgiftTypeLimitedRow(PrivacyControlActivity r0) {
        return r0.giftTypeLimitedRow;
    }

    /* renamed from: -$$Nest$fgetgiftTypePremiumRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17642$$Nest$fgetgiftTypePremiumRow(PrivacyControlActivity r0) {
        return r0.giftTypePremiumRow;
    }

    /* renamed from: -$$Nest$fgetgiftTypeUniqueRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17643$$Nest$fgetgiftTypeUniqueRow(PrivacyControlActivity r0) {
        return r0.giftTypeUniqueRow;
    }

    /* renamed from: -$$Nest$fgetgiftTypeUnlimitedRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17644$$Nest$fgetgiftTypeUnlimitedRow(PrivacyControlActivity r0) {
        return r0.giftTypeUnlimitedRow;
    }

    /* renamed from: -$$Nest$fgetgiftTypesHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17645$$Nest$fgetgiftTypesHeaderRow(PrivacyControlActivity r0) {
        return r0.giftTypesHeaderRow;
    }

    /* renamed from: -$$Nest$fgetgiftTypesInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17646$$Nest$fgetgiftTypesInfoRow(PrivacyControlActivity r0) {
        return r0.giftTypesInfoRow;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m17647$$Nest$fgetlistView(PrivacyControlActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlockSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m17648$$Nest$fgetlockSpan(PrivacyControlActivity r0) {
        return r0.lockSpan;
    }

    /* renamed from: -$$Nest$fgetmessageCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageCell m17649$$Nest$fgetmessageCell(PrivacyControlActivity r0) {
        return r0.messageCell;
    }

    /* renamed from: -$$Nest$fgetmessageRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17650$$Nest$fgetmessageRow(PrivacyControlActivity r0) {
        return r0.messageRow;
    }

    /* renamed from: -$$Nest$fgetmyContactsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17651$$Nest$fgetmyContactsRow(PrivacyControlActivity r0) {
        return r0.myContactsRow;
    }

    /* renamed from: -$$Nest$fgetneverShareRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17652$$Nest$fgetneverShareRow(PrivacyControlActivity r0) {
        return r0.neverShareRow;
    }

    /* renamed from: -$$Nest$fgetnobodyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17653$$Nest$fgetnobodyRow(PrivacyControlActivity r0) {
        return r0.nobodyRow;
    }

    /* renamed from: -$$Nest$fgetoldAvatarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m17654$$Nest$fgetoldAvatarView(PrivacyControlActivity r0) {
        return r0.oldAvatarView;
    }

    /* renamed from: -$$Nest$fgetoldPhotoCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextCell m17655$$Nest$fgetoldPhotoCell(PrivacyControlActivity r0) {
        return r0.oldPhotoCell;
    }

    /* renamed from: -$$Nest$fgetp2pDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17656$$Nest$fgetp2pDetailRow(PrivacyControlActivity r0) {
        return r0.p2pDetailRow;
    }

    /* renamed from: -$$Nest$fgetp2pRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17657$$Nest$fgetp2pRow(PrivacyControlActivity r0) {
        return r0.p2pRow;
    }

    /* renamed from: -$$Nest$fgetp2pSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17658$$Nest$fgetp2pSectionRow(PrivacyControlActivity r0) {
        return r0.p2pSectionRow;
    }

    /* renamed from: -$$Nest$fgetpayRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17659$$Nest$fgetpayRow(PrivacyControlActivity r0) {
        return r0.payRow;
    }

    /* renamed from: -$$Nest$fgetphoneContactsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17660$$Nest$fgetphoneContactsRow(PrivacyControlActivity r0) {
        return r0.phoneContactsRow;
    }

    /* renamed from: -$$Nest$fgetphoneDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17661$$Nest$fgetphoneDetailRow(PrivacyControlActivity r0) {
        return r0.phoneDetailRow;
    }

    /* renamed from: -$$Nest$fgetphoneEverybodyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17662$$Nest$fgetphoneEverybodyRow(PrivacyControlActivity r0) {
        return r0.phoneEverybodyRow;
    }

    /* renamed from: -$$Nest$fgetphoneSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17663$$Nest$fgetphoneSectionRow(PrivacyControlActivity r0) {
        return r0.phoneSectionRow;
    }

    /* renamed from: -$$Nest$fgetphotoForRestDescriptionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17664$$Nest$fgetphotoForRestDescriptionRow(PrivacyControlActivity r0) {
        return r0.photoForRestDescriptionRow;
    }

    /* renamed from: -$$Nest$fgetphotoForRestRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17665$$Nest$fgetphotoForRestRow(PrivacyControlActivity r0) {
        return r0.photoForRestRow;
    }

    /* renamed from: -$$Nest$fgetpriceButtonRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17666$$Nest$fgetpriceButtonRow(PrivacyControlActivity r0) {
        return r0.priceButtonRow;
    }

    /* renamed from: -$$Nest$fgetpriceHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17667$$Nest$fgetpriceHeaderRow(PrivacyControlActivity r0) {
        return r0.priceHeaderRow;
    }

    /* renamed from: -$$Nest$fgetpriceInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17668$$Nest$fgetpriceInfoRow(PrivacyControlActivity r0) {
        return r0.priceInfoRow;
    }

    /* renamed from: -$$Nest$fgetpriceRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17669$$Nest$fgetpriceRow(PrivacyControlActivity r0) {
        return r0.priceRow;
    }

    /* renamed from: -$$Nest$fgetreadDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17670$$Nest$fgetreadDetailRow(PrivacyControlActivity r0) {
        return r0.readDetailRow;
    }

    /* renamed from: -$$Nest$fgetreadPremiumDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17671$$Nest$fgetreadPremiumDetailRow(PrivacyControlActivity r0) {
        return r0.readPremiumDetailRow;
    }

    /* renamed from: -$$Nest$fgetreadPremiumRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17672$$Nest$fgetreadPremiumRow(PrivacyControlActivity r0) {
        return r0.readPremiumRow;
    }

    /* renamed from: -$$Nest$fgetreadRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17673$$Nest$fgetreadRow(PrivacyControlActivity r0) {
        return r0.readRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17674$$Nest$fgetrowCount(PrivacyControlActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetrulesType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17675$$Nest$fgetrulesType(PrivacyControlActivity r0) {
        return r0.rulesType;
    }

    /* renamed from: -$$Nest$fgetsectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17676$$Nest$fgetsectionRow(PrivacyControlActivity r0) {
        return r0.sectionRow;
    }

    /* renamed from: -$$Nest$fgetselectedGiftChannelsValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17677$$Nest$fgetselectedGiftChannelsValue(PrivacyControlActivity r0) {
        return r0.selectedGiftChannelsValue;
    }

    /* renamed from: -$$Nest$fgetselectedGiftIconValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17678$$Nest$fgetselectedGiftIconValue(PrivacyControlActivity r0) {
        return r0.selectedGiftIconValue;
    }

    /* renamed from: -$$Nest$fgetselectedGiftLimitedValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17679$$Nest$fgetselectedGiftLimitedValue(PrivacyControlActivity r0) {
        return r0.selectedGiftLimitedValue;
    }

    /* renamed from: -$$Nest$fgetselectedGiftPremiumValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17680$$Nest$fgetselectedGiftPremiumValue(PrivacyControlActivity r0) {
        return r0.selectedGiftPremiumValue;
    }

    /* renamed from: -$$Nest$fgetselectedGiftUniqueValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17681$$Nest$fgetselectedGiftUniqueValue(PrivacyControlActivity r0) {
        return r0.selectedGiftUniqueValue;
    }

    /* renamed from: -$$Nest$fgetselectedGiftUnlimitedValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17682$$Nest$fgetselectedGiftUnlimitedValue(PrivacyControlActivity r0) {
        return r0.selectedGiftUnlimitedValue;
    }

    /* renamed from: -$$Nest$fgetselectedReadValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17683$$Nest$fgetselectedReadValue(PrivacyControlActivity r0) {
        return r0.selectedReadValue;
    }

    /* renamed from: -$$Nest$fgetsetAvatarCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextCell m17684$$Nest$fgetsetAvatarCell(PrivacyControlActivity r0) {
        return r0.setAvatarCell;
    }

    /* renamed from: -$$Nest$fgetsetBirthdayRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17685$$Nest$fgetsetBirthdayRow(PrivacyControlActivity r0) {
        return r0.setBirthdayRow;
    }

    /* renamed from: -$$Nest$fgetshareDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17686$$Nest$fgetshareDetailRow(PrivacyControlActivity r0) {
        return r0.shareDetailRow;
    }

    /* renamed from: -$$Nest$fgetshareSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17687$$Nest$fgetshareSectionRow(PrivacyControlActivity r0) {
        return r0.shareSectionRow;
    }

    /* renamed from: -$$Nest$fgetshowGiftIconInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17688$$Nest$fgetshowGiftIconInfoRow(PrivacyControlActivity r0) {
        return r0.showGiftIconInfoRow;
    }

    /* renamed from: -$$Nest$fgetshowGiftIconRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17689$$Nest$fgetshowGiftIconRow(PrivacyControlActivity r0) {
        return r0.showGiftIconRow;
    }

    /* renamed from: -$$Nest$fputcameraDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17690$$Nest$fputcameraDrawable(PrivacyControlActivity r0, RLottieDrawable r1) {
        r0.cameraDrawable = r1;
    }

    /* renamed from: -$$Nest$fputcurrentStars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17691$$Nest$fputcurrentStars(PrivacyControlActivity r0, long r1) {
        r0.currentStars = r1;
    }

    /* renamed from: -$$Nest$fputlockSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17692$$Nest$fputlockSpan(PrivacyControlActivity r0, CharSequence r1) {
        r0.lockSpan = r1;
    }

    /* renamed from: -$$Nest$fputoldAvatarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17693$$Nest$fputoldAvatarView(PrivacyControlActivity r0, BackupImageView r1) {
        r0.oldAvatarView = r1;
    }

    /* renamed from: -$$Nest$fputoldPhotoCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17694$$Nest$fputoldPhotoCell(PrivacyControlActivity r0, TextCell r1) {
        r0.oldPhotoCell = r1;
    }

    /* renamed from: -$$Nest$fputprevSubtypeContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17695$$Nest$fputprevSubtypeContacts(PrivacyControlActivity r0, boolean r1) {
        r0.prevSubtypeContacts = r1;
    }

    /* renamed from: -$$Nest$fputsetAvatarCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17696$$Nest$fputsetAvatarCell(PrivacyControlActivity r0, TextCell r1) {
        r0.setAvatarCell = r1;
    }

    /* renamed from: -$$Nest$mareAllStarGiftsDisabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17697$$Nest$mareAllStarGiftsDisabled(PrivacyControlActivity r0) {
        return r0.areAllStarGiftsDisabled();
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17698$$Nest$mcheckDiscard(PrivacyControlActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17699$$Nest$mprocessDone(PrivacyControlActivity r0) {
        r0.processDone();
    }

    /* renamed from: -$$Nest$mupdateDoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17700$$Nest$mupdateDoneButton(PrivacyControlActivity r0) {
        r0.updateDoneButton();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17701$$Nest$mupdateRows(PrivacyControlActivity r0, boolean r1) {
        r0.updateRows(r1);
    }

    public PrivacyControlActivity(int r2) {
        this(r2, false);
    }

    public PrivacyControlActivity(int r4, boolean r5) {
        this.initialPlus = new ArrayList();
        this.initialMinus = new ArrayList();
        this.initialPlusPremium = new boolean[4];
        this.initialPlusMiniapps = new boolean[4];
        this.initialPlusChannels = new boolean[4];
        this.currentPlusPremium = new boolean[4];
        this.currentPlusMiniapps = new boolean[4];
        this.currentPlusChannels = new boolean[4];
        this.currentStars = 10;
        this.shakeDp = 4;
        this.rulesType = r4;
        if (r5 == false) goto L6;
        ContactsController.getInstance(this.currentAccount).loadPrivacySettings();
    L6:
        if (this.rulesType != 4) goto L13;
        ImageUpdater r42 = new ImageUpdater(true, 0, true);
        this.imageUpdater = r42;
        r42.parentFragment = this;
        r42.setDelegate(this);
        TLRPC.UserFull r43 = getMessagesController().getUserFull(getUserConfig().clientUserId);
        if (UserObject.hasFallbackPhoto(r43) == false) goto L14;
        TLRPC.PhotoSize r52 = FileLoader.getClosestPhotoSizeWithSize(r43.fallback_photo.sizes, 1000);
        if (r52 == null) goto L15;
        this.avatarForRest = r52;
        this.avatarForRestPhoto = r43.fallback_photo;
        return;
    L15:
        return;
    L14:
        return;
    }

    public static /* synthetic */ int access$000(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1000(PrivacyControlActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$1100(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1300(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1400(PrivacyControlActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$200(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(PrivacyControlActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$800(PrivacyControlActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(PrivacyControlActivity r0) {
        return r0.resourceProvider;
    }

    private void applyCurrentPrivacySettings() {
        char r4 = 0;
        boolean r42 = false;
        if (this.rulesType != 10) goto L38;
        boolean[] r0 = {true, false};
        if (this.currentType != 3) goto L26;
        Collections.sort(this.initialPlus);
        Collections.sort(this.currentPlus);
        if (this.initialPlus.equals(this.currentPlus) == true) goto L26;
        r0[0] = false;
        TL_account.setPrivacy r1 = new TL_account.setPrivacy();
        r1.key = new TLRPC.TL_inputPrivacyKeyNoPaidMessages();
        r1.rules.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
        if (this.currentType != 0) goto L11;
    L25:
        getConnectionsManager().sendRequest(r1, new PrivacyControlActivity$$ExternalSyntheticLambda13(this, r0));
        goto L26
    L11:
        if (this.currentPlus.size() <= 0) goto L25;
        TLRPC.TL_inputPrivacyValueAllowUsers r6 = new TLRPC.TL_inputPrivacyValueAllowUsers();
        TLRPC.TL_inputPrivacyValueAllowChatParticipants r7 = new TLRPC.TL_inputPrivacyValueAllowChatParticipants();
        int r8 = 0;
    L14:
        if (r8 >= this.currentPlus.size()) goto L24;
        Long r9 = this.currentPlus.get(r8);
        long r10 = r9.longValue();
        if (DialogObject.isUserDialog(r10) == false) goto L22;
        TLRPC.User r92 = MessagesController.getInstance(this.currentAccount).getUser(r9);
        if (r92 == null) goto L23;
        TLRPC.InputUser r93 = MessagesController.getInstance(this.currentAccount).getInputUser(r92);
        if (r93 == null) goto L23;
        r6.users.add(r93);
    L23:
        r8 = r8 + 1;
        goto L14
    L22:
        r7.chats.add(Long.valueOf(-r10));
        goto L23
    L24:
        r1.rules.add(r6);
        r1.rules.add(r7);
    L26:
        TL_account.setGlobalPrivacySettings r12 = new TL_account.setGlobalPrivacySettings();
        r12.settings = new TLRPC.TL_globalPrivacySettings();
        TLRPC.GlobalPrivacySettings r62 = getContactsController().getGlobalPrivacySettings();
        if (r62 == null) goto L29;
        TLRPC.GlobalPrivacySettings r72 = r12.settings;
        r72.flags = r62.flags;
        r72.disallowed_stargifts = r62.disallowed_stargifts;
        r72.archive_and_mute_new_noncontact_peers = r62.archive_and_mute_new_noncontact_peers;
        r72.noncontact_peers_paid_stars = r62.noncontact_peers_paid_stars;
        r72.keep_archived_folders = r62.keep_archived_folders;
        r72.keep_archived_unmuted = r62.keep_archived_unmuted;
        r72.hide_read_marks = r62.hide_read_marks;
    L29:
        int r73 = this.currentType;
        TLRPC.GlobalPrivacySettings r82 = r12.settings;
        if (r73 != 3) goto L32;
        r82.flags |= 32;
        r82.noncontact_peers_paid_stars = this.currentStars;
        r82.new_noncontact_peers_require_premium = false;
    L36:
        getConnectionsManager().sendRequest(r12, new PrivacyControlActivity$$ExternalSyntheticLambda14(this, r0, r62, r12));
        return;
    L32:
        r82.flags |= 32;
        r82.noncontact_peers_paid_stars = 0;
        if (r73 != 2) goto L35;
        r42 = true;
    L35:
        r82.new_noncontact_peers_require_premium = r42;
        goto L36
    L38:
        AtomicInteger r02 = new AtomicInteger(0);
        TL_account.setPrivacy r13 = new TL_account.setPrivacy();
        int r63 = this.rulesType;
        if (r63 != 6) goto L48;
        r13.key = new TLRPC.TL_inputPrivacyKeyPhoneNumber();
        if (this.currentType != 1) goto L75;
        TL_account.setPrivacy r2 = new TL_account.setPrivacy();
        r2.key = new TLRPC.TL_inputPrivacyKeyAddedByPhone();
        int r64 = this.currentSubType;
        ArrayList<TLRPC.InputPrivacyRule> r74 = r2.rules;
        if (r64 != 0) goto L45;
        r74.add(new TLRPC.TL_inputPrivacyValueAllowAll());
    L46:
        r02.incrementAndGet();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new PrivacyControlActivity$$ExternalSyntheticLambda15(this, r02), 2);
        goto L75
    L45:
        r74.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
    L75:
        if (this.currentType == 0) goto L92;
        if (this.currentPlus.size() <= 0) goto L92;
        TLRPC.TL_inputPrivacyValueAllowUsers r22 = new TLRPC.TL_inputPrivacyValueAllowUsers();
        TLRPC.TL_inputPrivacyValueAllowChatParticipants r65 = new TLRPC.TL_inputPrivacyValueAllowChatParticipants();
        int r75 = 0;
    L80:
        if (r75 >= this.currentPlus.size()) goto L90;
        Long r94 = this.currentPlus.get(r75);
        long r102 = r94.longValue();
        if (DialogObject.isUserDialog(r102) == false) goto L88;
        TLRPC.User r95 = MessagesController.getInstance(this.currentAccount).getUser(r94);
        if (r95 == null) goto L89;
        TLRPC.InputUser r96 = MessagesController.getInstance(this.currentAccount).getInputUser(r95);
        if (r96 == null) goto L89;
        r22.users.add(r96);
    L89:
        r75 = r75 + 1;
        goto L80
    L88:
        r65.chats.add(Long.valueOf(-r102));
        goto L89
    L90:
        r13.rules.add(r22);
        r13.rules.add(r65);
    L92:
        if (this.currentType != 1) goto L94;
    L108:
        int r23 = this.currentType;
        if (r23 != 0) goto L111;
        r13.rules.add(new TLRPC.TL_inputPrivacyValueAllowAll());
    L115:
        int r24 = this.currentType;
        if (r24 == 0) goto L124;
        boolean[] r66 = this.currentPlusPremium;
        if (r24 == 2) goto L122;
        r4 = 1;
    L122:
        if (r66[r4] == false) goto L124;
        r13.rules.add(new TLRPC.TL_inputPrivacyValueAllowPremium());
    L124:
        boolean[] r25 = this.currentPlusMiniapps;
        int r43 = this.currentType;
        if (r25[r43] == false) goto L130;
        ArrayList<TLRPC.InputPrivacyRule> r26 = r13.rules;
        if (r43 != 0) goto L129;
        r26.add(new TLRPC.TL_inputPrivacyValueDisallowBots());
        goto L130
    L129:
        r26.add(new TLRPC.TL_inputPrivacyValueAllowBots());
    L130:
        r02.incrementAndGet();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r13, new PrivacyControlActivity$$ExternalSyntheticLambda16(this, r02), 2);
        if (this.rulesType != 0) goto L136;
        if (this.selectedReadValue == this.currentReadValue) goto L136;
        r02.incrementAndGet();
        TL_account.setGlobalPrivacySettings r14 = new TL_account.setGlobalPrivacySettings();
        r14.settings = new TLRPC.TL_globalPrivacySettings();
        TLRPC.GlobalPrivacySettings r27 = getContactsController().getGlobalPrivacySettings();
        TLRPC.GlobalPrivacySettings r3 = r14.settings;
        r3.archive_and_mute_new_noncontact_peers = r27.archive_and_mute_new_noncontact_peers;
        r3.keep_archived_folders = r27.keep_archived_folders;
        r3.keep_archived_unmuted = r27.keep_archived_unmuted;
        r3.new_noncontact_peers_require_premium = r27.new_noncontact_peers_require_premium;
        r3.noncontact_peers_paid_stars = r27.noncontact_peers_paid_stars;
        r3.hide_read_marks = this.selectedReadValue;
        r3.display_gifts_button = r27.display_gifts_button;
        getConnectionsManager().sendRequest(r14, new PrivacyControlActivity$$ExternalSyntheticLambda17(this, r27, r14, r02));
    L136:
        if (this.rulesType != 12) goto L152;
        if (this.selectedGiftIconValue == this.currentGiftIconValue) goto L140;
    L147:
        r02.incrementAndGet();
        TL_account.setGlobalPrivacySettings r15 = new TL_account.setGlobalPrivacySettings();
        r15.settings = new TLRPC.TL_globalPrivacySettings();
        TLRPC.GlobalPrivacySettings r28 = getContactsController().getGlobalPrivacySettings();
        TLRPC.GlobalPrivacySettings r32 = r15.settings;
        r32.archive_and_mute_new_noncontact_peers = r28.archive_and_mute_new_noncontact_peers;
        r32.keep_archived_folders = r28.keep_archived_folders;
        r32.keep_archived_unmuted = r28.keep_archived_unmuted;
        r32.new_noncontact_peers_require_premium = r28.new_noncontact_peers_require_premium;
        r32.noncontact_peers_paid_stars = r28.noncontact_peers_paid_stars;
        r32.hide_read_marks = r28.hide_read_marks;
        r32.display_gifts_button = this.selectedGiftIconValue;
        if (getUserConfig().isPremium() == false) goto L150;
        TLRPC.GlobalPrivacySettings r33 = r15.settings;
        r33.flags |= 64;
        r33.disallowed_stargifts = new TLRPC.DisallowedGiftsSettings();
        TLRPC.DisallowedGiftsSettings r34 = r15.settings.disallowed_stargifts;
        r34.disallow_unlimited_stargifts = !this.selectedGiftUnlimitedValue;
        r34.disallow_limited_stargifts = !this.selectedGiftLimitedValue;
        r34.disallow_unique_stargifts = !this.selectedGiftUniqueValue;
        r34.disallow_premium_gifts = !this.selectedGiftPremiumValue;
    L150:
        getConnectionsManager().sendRequest(r15, new PrivacyControlActivity$$ExternalSyntheticLambda18(this, r28, r15, r02));
        goto L152
    L140:
        if (this.selectedGiftLimitedValue != this.currentGiftLimitedValue) goto L147;
        if (this.selectedGiftUnlimitedValue != this.currentGiftUnlimitedValue) goto L147;
        if (this.selectedGiftUniqueValue != this.currentGiftUniqueValue) goto L147;
        if (this.selectedGiftPremiumValue != this.currentGiftPremiumValue) goto L147;
    L152:
        if (r02.get() <= 0) goto L170;
        this.doneButtonDrawable.animateToProgress(1.0f);
        return;
    L170:
        return;
    L111:
        if (r23 != 1) goto L113;
        r13.rules.add(new TLRPC.TL_inputPrivacyValueDisallowAll());
        goto L115
    L113:
        if (r23 != 2) goto L115;
        r13.rules.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
        goto L115
    L94:
        if (this.currentMinus.size() <= 0) goto L108;
        TLRPC.TL_inputPrivacyValueDisallowUsers r29 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
        TLRPC.TL_inputPrivacyValueDisallowChatParticipants r67 = new TLRPC.TL_inputPrivacyValueDisallowChatParticipants();
        int r76 = 0;
    L97:
        if (r76 >= this.currentMinus.size()) goto L107;
        Long r97 = this.currentMinus.get(r76);
        long r103 = r97.longValue();
        if (DialogObject.isUserDialog(r103) == false) goto L105;
        TLRPC.User r98 = getMessagesController().getUser(r97);
        if (r98 == null) goto L106;
        TLRPC.InputUser r99 = getMessagesController().getInputUser(r98);
        if (r99 == null) goto L106;
        r29.users.add(r99);
    L106:
        r76 = r76 + 1;
        goto L97
    L105:
        r67.chats.add(Long.valueOf(-r103));
        goto L106
    L107:
        r13.rules.add(r29);
        r13.rules.add(r67);
        goto L108
    L48:
        if (r63 != 5) goto L51;
        r13.key = new TLRPC.TL_inputPrivacyKeyForwards();
        goto L75
    L51:
        if (r63 != 4) goto L54;
        r13.key = new TLRPC.TL_inputPrivacyKeyProfilePhoto();
        goto L75
    L54:
        if (r63 != 9) goto L57;
        r13.key = new TLRPC.TL_inputPrivacyKeyAbout();
        goto L75
    L57:
        if (r63 != 14) goto L59;
        r13.key = new TLRPC.TL_inputPrivacyKeySavedMusic();
        goto L75
    L59:
        if (r63 != 3) goto L61;
        r13.key = new TLRPC.TL_inputPrivacyKeyPhoneP2P();
        goto L75
    L61:
        if (r63 != 2) goto L63;
        r13.key = new TLRPC.TL_inputPrivacyKeyPhoneCall();
        goto L75
    L63:
        if (r63 != 1) goto L66;
        r13.key = new TLRPC.TL_inputPrivacyKeyChatInvite();
        goto L75
    L66:
        if (r63 != 8) goto L69;
        r13.key = new TLRPC.TL_inputPrivacyKeyVoiceMessages();
        goto L75
    L69:
        if (r63 != 11) goto L71;
        r13.key = new TLRPC.TL_inputPrivacyKeyBirthday();
        goto L75
    L71:
        if (r63 != 12) goto L73;
        r13.key = new TLRPC.TL_inputPrivacyKeyStarGiftsAutoSave();
        goto L75
    L73:
        r13.key = new TLRPC.TL_inputPrivacyKeyStatusTimestamp();
        goto L75
    }

    private boolean areAllStarGiftsDisabled() {
        if (this.selectedGiftUniqueValue == false) goto L5;
        return false;
    L5:
        if (this.selectedGiftUnlimitedValue == false) goto L7;
        return false;
    L7:
        if (this.selectedGiftLimitedValue == true) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean checkDiscard(boolean r3) {
        if (this.doneButton.getAlpha() != 1.0f) goto L8;
        if (r3 == false) goto L10;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setTitle(LocaleController.getString(R.string.UserRestrictionsApplyChanges));
        r32.setMessage(LocaleController.getString(R.string.PrivacySettingsChangedAlert));
        r32.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new PrivacyControlActivity$$ExternalSyntheticLambda1(this));
        r32.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new PrivacyControlActivity$$ExternalSyntheticLambda2(this));
        showDialog(r32.create());
        return false;
    L10:
        return false;
    L8:
        return true;
    }

    private void checkPrivacy() {
        int r1 = this.rulesType;
        int r4 = 2;
        int r5 = 0;
        if (r1 != 10) goto L32;
        TLRPC.GlobalPrivacySettings r12 = ContactsController.getInstance(this.currentAccount).getGlobalPrivacySettings();
        if (r12 != null) goto L7;
    L9:
        r4 = 0;
    L10:
        this.currentType = r4;
        this.initialRulesType = r4;
        this.currentMinus = new ArrayList();
        this.currentPlus = new ArrayList();
        if (r12 != null) goto L13;
    L15:
        this.currentStars = 10;
        this.initialStars = 10;
    L16:
        ArrayList<TLRPC.PrivacyRule> r13 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(13);
        this.currentPlus = new ArrayList();
        if (r13 == null) goto L30;
        int r2 = 0;
    L20:
        if (r2 >= r13.size()) goto L30;
        TLRPC.PrivacyRule r3 = r13.get(r2);
        if ((r3 instanceof TLRPC.TL_privacyValueAllowChatParticipants) == false) goto L27;
        TLRPC.TL_privacyValueAllowChatParticipants r32 = (TLRPC.TL_privacyValueAllowChatParticipants) r3;
        int r42 = r32.chats.size();
        int r6 = 0;
    L24:
        if (r6 >= r42) goto L29;
        this.currentPlus.add(Long.valueOf(-r32.chats.get(r6).longValue()));
        r6 = r6 + 1;
    L29:
        r2 = r2 + 1;
        goto L20
    L27:
        if ((r3 instanceof TLRPC.TL_privacyValueAllowUsers) == false) goto L29;
        this.currentPlus.addAll(((TLRPC.TL_privacyValueAllowUsers) r3).users);
    L30:
        this.initialPlus.clear();
        this.initialPlus.addAll(this.currentPlus);
        return;
    L13:
        if ((r12.flags & 32) == 0) goto L15;
        long r14 = Utilities.clamp(r12.noncontact_peers_paid_stars, getMessagesController().starsPaidMessageAmountMax, 1);
        this.currentStars = r14;
        this.initialStars = r14;
        this.currentType = 3;
        this.initialRulesType = 3;
        goto L16
    L7:
        if (r12.new_noncontact_peers_require_premium == false) goto L9;
    L32:
        boolean[] r22 = this.currentPlusPremium;
        boolean[] r62 = this.initialPlusPremium;
        if (r1 != 1) goto L35;
        boolean r8 = true;
    L36:
        r62[0] = r8;
        r22[0] = r8;
        r62[1] = false;
        r22[1] = false;
        boolean[] r23 = this.currentPlusMiniapps;
        boolean[] r63 = this.initialPlusMiniapps;
        r63[0] = false;
        r23[0] = false;
        if (r1 != 12) goto L39;
        boolean r9 = true;
    L40:
        r63[1] = r9;
        r23[1] = r9;
        r63[2] = false;
        r23[2] = false;
        r63[3] = false;
        r23[3] = false;
        boolean[] r24 = this.currentPlusChannels;
        boolean[] r64 = this.initialPlusChannels;
        r64[0] = false;
        r24[0] = false;
        if (r1 != 12) goto L43;
        boolean r15 = true;
    L44:
        r64[1] = r15;
        r24[1] = r15;
        r64[2] = false;
        r24[2] = false;
        r64[3] = false;
        r24[3] = false;
        this.currentPlus = new ArrayList();
        this.currentMinus = new ArrayList();
        ArrayList<TLRPC.PrivacyRule> r16 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(this.rulesType);
        if (r16 != null) goto L47;
    L132:
        this.currentType = 1;
    L133:
        this.initialPlus.clear();
        this.initialMinus.clear();
        this.initialRulesType = this.currentType;
        this.initialPlus.addAll(this.currentPlus);
        this.initialMinus.addAll(this.currentMinus);
        if (this.rulesType != 6) goto L156;
        ArrayList<TLRPC.PrivacyRule> r17 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(7);
        if (r17 == null) goto L153;
        if (r17.size() == 0) goto L153;
        int r25 = 0;
    L142:
        if (r25 >= r17.size()) goto L154;
        TLRPC.PrivacyRule r33 = r17.get(r25);
        if ((r33 instanceof TLRPC.TL_privacyValueAllowAll) == true) goto L145;
        if ((r33 instanceof TLRPC.TL_privacyValueDisallowAll) == true) goto L148;
        if ((r33 instanceof TLRPC.TL_privacyValueAllowContacts) == true) goto L151;
        r25 = r25 + 1;
        goto L142
    L151:
        this.currentSubType = 1;
        goto L154
    L148:
        this.currentSubType = 2;
        goto L154
    L145:
        this.currentSubType = 0;
    L154:
        this.initialRulesSubType = this.currentSubType;
    L153:
        this.currentSubType = 0;
    L156:
        if (this.rulesType != 0) goto L165;
        TLRPC.GlobalPrivacySettings r18 = getContactsController().getGlobalPrivacySettings();
        if (r18 != null) goto L160;
    L162:
        boolean r19 = false;
    L163:
        this.currentReadValue = r19;
        this.selectedReadValue = r19;
        goto L165
    L160:
        if (r18.hide_read_marks == false) goto L162;
        r19 = true;
    L165:
        if (this.rulesType != 12) goto L179;
        TLRPC.GlobalPrivacySettings r110 = getContactsController().getGlobalPrivacySettings();
        if (r110 != null) goto L169;
    L171:
        boolean r26 = false;
    L172:
        this.currentGiftIconValue = r26;
        this.selectedGiftIconValue = r26;
        if (r110 == null) goto L178;
        TLRPC.DisallowedGiftsSettings r111 = r110.disallowed_stargifts;
        if (r111 == null) goto L178;
        boolean r27 = !r111.disallow_unlimited_stargifts;
        this.selectedGiftUnlimitedValue = r27;
        this.currentGiftUnlimitedValue = r27;
        boolean r28 = !r111.disallow_limited_stargifts;
        this.selectedGiftLimitedValue = r28;
        this.currentGiftLimitedValue = r28;
        boolean r29 = !r111.disallow_unique_stargifts;
        this.selectedGiftUniqueValue = r29;
        this.currentGiftUniqueValue = r29;
        boolean r210 = !r111.disallow_stargifts_from_channels;
        this.selectedGiftChannelsValue = r210;
        this.currentGiftChannelsValue = r210;
        boolean r112 = !r111.disallow_premium_gifts;
        this.selectedGiftPremiumValue = r112;
        this.currentGiftPremiumValue = r112;
    L178:
        this.selectedGiftUnlimitedValue = true;
        this.currentGiftUnlimitedValue = true;
        this.selectedGiftLimitedValue = true;
        this.currentGiftLimitedValue = true;
        this.selectedGiftUniqueValue = true;
        this.currentGiftUniqueValue = true;
        this.selectedGiftChannelsValue = true;
        this.currentGiftChannelsValue = true;
        this.selectedGiftPremiumValue = true;
        this.currentGiftPremiumValue = true;
        goto L179
    L169:
        if (r110.display_gifts_button == false) goto L171;
        r26 = true;
    L179:
        updateRows(false);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L47:
        if (r16.size() == 0) goto L132;
        Boolean r34 = null;
        char r92 = 65535;
        int r65 = 0;
        boolean r10 = false;
        boolean r11 = false;
    L51:
        if (r65 >= r16.size()) goto L96;
        TLRPC.PrivacyRule r122 = r16.get(r65);
        if ((r122 instanceof TLRPC.TL_privacyValueAllowChatParticipants) == false) goto L58;
        TLRPC.TL_privacyValueAllowChatParticipants r123 = (TLRPC.TL_privacyValueAllowChatParticipants) r122;
        int r132 = r123.chats.size();
        int r142 = r5;
    L55:
        if (r142 >= r132) goto L57;
        this.currentPlus.add(Long.valueOf(-r123.chats.get(r142).longValue()));
        r142 = r142 + 1;
        r65 = r65;
        goto L55
    L57:
        int r162 = r65;
    L95:
        r65 = r162 + 1;
        r5 = 0;
        goto L51
    L58:
        r162 = r65;
        if ((r122 instanceof TLRPC.TL_privacyValueDisallowChatParticipants) == false) goto L64;
        TLRPC.TL_privacyValueDisallowChatParticipants r124 = (TLRPC.TL_privacyValueDisallowChatParticipants) r122;
        int r52 = r124.chats.size();
        int r66 = 0;
    L61:
        if (r66 >= r52) goto L95;
        this.currentMinus.add(Long.valueOf(-r124.chats.get(r66).longValue()));
        r66 = r66 + 1;
        goto L61
    L64:
        if ((r122 instanceof TLRPC.TL_privacyValueAllowUsers) == false) goto L67;
        this.currentPlus.addAll(((TLRPC.TL_privacyValueAllowUsers) r122).users);
        goto L95
    L67:
        if ((r122 instanceof TLRPC.TL_privacyValueDisallowUsers) == false) goto L70;
        this.currentMinus.addAll(((TLRPC.TL_privacyValueDisallowUsers) r122).users);
        goto L95
    L70:
        if ((r122 instanceof TLRPC.TL_privacyValueAllowPremium) == false) goto L73;
        r10 = true;
        goto L95
    L73:
        if ((r122 instanceof TLRPC.TL_privacyValueAllowBots) == false) goto L76;
        r34 = Boolean.TRUE;
        goto L95
    L76:
        if ((r122 instanceof TLRPC.TL_privacyValueDisallowBots) == false) goto L78;
        r34 = Boolean.FALSE;
        goto L95
    L78:
        boolean r53 = r122 instanceof TLRPC.TL_privacyValueAllowAll;
        if (r53 == false) goto L81;
    L80:
        r92 = 0;
        goto L95
    L81:
        boolean r67 = r122 instanceof TLRPC.TL_privacyValueDisallowAll;
        if (r67 == false) goto L86;
        if (r11 == true) goto L86;
    L84:
        r92 = 1;
    L86:
        if ((r122 instanceof TLRPC.TL_privacyValueAllowContacts) == false) goto L88;
        r92 = 2;
        r11 = true;
        goto L95
    L88:
        if (r92 != 65535) goto L95;
        if (r53 == true) goto L80;
        if (r67 == false) goto L94;
        if (r11 == false) goto L84;
    L94:
        r92 = 2;
        goto L95
    L96:
        if (r92 == 0) goto L120;
        if (r92 == 65535) goto L99;
    L104:
        if (r92 == 2) goto L119;
        if (r92 == 65535) goto L107;
    L111:
        if (r92 == 1) goto L118;
        if (r92 == 65535) goto L114;
    L121:
        int r113 = this.currentType;
        if (r113 != 2) goto L124;
        char r211 = 0;
    L125:
        boolean[] r54 = this.currentPlusPremium;
        this.initialPlusPremium[r211] = r10;
        r54[r211] = r10;
        boolean[] r212 = this.currentPlusMiniapps;
        boolean[] r55 = this.initialPlusMiniapps;
        if (r34 == null) goto L128;
        boolean r35 = true;
    L129:
        r55[r113] = r35;
        r212[r113] = r35;
        boolean[] r213 = this.currentPlusChannels;
        this.initialPlusChannels[r113] = false;
        r213[r113] = false;
        View r114 = this.doneButton;
        if (r114 == null) goto L133;
        r114.setAlpha(0.0f);
        this.doneButton.setScaleX(0.0f);
        this.doneButton.setScaleY(0.0f);
        this.doneButton.setEnabled(false);
        goto L133
    L128:
        r35 = false;
        goto L129
    L124:
        r211 = 1;
        goto L125
    L114:
        if (this.currentPlus.size() > 0) goto L118;
        if (r34 == null) goto L121;
        if (r34.booleanValue() == false) goto L121;
    L118:
        this.currentType = 1;
        goto L121
    L107:
        if (this.currentMinus.size() <= 0) goto L111;
        if (this.currentPlus.size() <= 0) goto L111;
    L119:
        this.currentType = 2;
        goto L121
    L99:
        if (this.currentMinus.size() > 0) goto L120;
        if (r34 == null) goto L104;
        if (r34.booleanValue() == true) goto L104;
    L120:
        this.currentType = 0;
        goto L121
    L43:
        r15 = false;
        goto L44
    L39:
        r9 = false;
        goto L40
    L35:
        r8 = false;
        goto L36
    }

    private void finished() {
        this.doneButtonDrawable.animateToProgress(0.0f);
        int r0 = this.rulesType;
        if (r0 == 10) goto L7;
        if (r0 == 1) goto L7;
        if (r0 == 2) goto L7;
    L31:
        finishFragment();
        return;
    L7:
        if (this.currentType == 0) goto L31;
        TLRPC.GlobalPrivacySettings r02 = ContactsController.getInstance(this.currentAccount).getGlobalPrivacySettings();
        if (r02 == null) goto L31;
        if (r02.new_noncontact_peers_require_premium == false) goto L13;
    L14:
        int[] r03 = {1, 2};
        int r1 = 0;
    L15:
        if (r1 >= 2) goto L31;
        int r4 = r03[r1];
        ArrayList<TLRPC.PrivacyRule> r5 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(r4);
        if (r4 == this.rulesType) goto L30;
        if (ContactsController.findRule(r5, TLRPC.TL_privacyValueAllowAll.class) == null) goto L30;
        AlertDialog.Builder r04 = new AlertDialog.Builder(getContext(), this.resourceProvider);
        if (r4 != 1) goto L23;
        int r12 = R.string.CheckPrivacyInviteTitle;
    L24:
        AlertDialog.Builder r05 = r04.setTitle(LocaleController.getString(r12));
        if (r4 != 1) goto L27;
        int r13 = R.string.CheckPrivacyInviteText;
    L28:
        r05.setMessage(LocaleController.getString(r13)).setPositiveButton(LocaleController.getString(R.string.CheckPrivacyReview), new PrivacyControlActivity$$ExternalSyntheticLambda27(this, r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), new PrivacyControlActivity$$ExternalSyntheticLambda28(this)).show();
        return;
    L27:
        r13 = R.string.CheckPrivacyCallsText;
        goto L28
    L23:
        r12 = R.string.CheckPrivacyCallsTitle;
    L30:
        r1 = r1 + 1;
        goto L15
    L13:
        if (r02.noncontact_peers_paid_stars <= 0) goto L31;
        goto L14
    }

    private boolean hasChanges() {
        if (this.rulesType == 0) goto L5;
    L13:
        int r0 = this.rulesType;
        if (r0 == 12) goto L16;
    L28:
        int r2 = this.initialRulesType;
        int r3 = this.currentType;
        if (r2 == r3) goto L32;
        return true;
    L32:
        if (r0 != 6) goto L38;
        if (r3 != 1) goto L38;
        if (this.initialRulesSubType == this.currentSubType) goto L38;
        return true;
    L38:
        if (r3 == 0) goto L51;
        boolean[] r4 = this.initialPlusPremium;
        if (r3 != 2) goto L42;
        char r6 = 0;
    L43:
        boolean r42 = r4[r6];
        boolean[] r62 = this.currentPlusPremium;
        if (r3 != 2) goto L46;
        char r5 = 0;
    L48:
        if (r42 == r62[r5]) goto L51;
        return true;
    L46:
        r5 = 1;
        goto L48
    L42:
        r6 = 1;
    L51:
        if (r0 != 10) goto L57;
        if (r3 != 3) goto L57;
        if (this.currentStars == this.initialStars) goto L57;
        return true;
    L57:
        if (this.initialPlusMiniapps[r3] == this.currentPlusMiniapps[r3]) goto L60;
        return true;
    L60:
        if (this.initialMinus.size() == this.currentMinus.size()) goto L63;
        return true;
    L63:
        if (this.initialPlus.size() == this.currentPlus.size()) goto L66;
        return true;
    L66:
        if (this.rulesType == 10) goto L68;
    L69:
        Collections.sort(this.initialPlus);
        Collections.sort(this.currentPlus);
        if (this.initialPlus.equals(this.currentPlus) == true) goto L72;
        return true;
    L72:
        Collections.sort(this.initialMinus);
        Collections.sort(this.currentMinus);
        if (this.initialMinus.equals(this.currentMinus) == true) goto L75;
        return true;
    L75:
        return false;
    L68:
        if (this.currentType != 3) goto L75;
    L16:
        if (this.currentGiftIconValue == this.selectedGiftIconValue) goto L18;
    L27:
        return true;
    L18:
        if (this.currentGiftLimitedValue != this.selectedGiftLimitedValue) goto L27;
        if (this.currentGiftUnlimitedValue != this.selectedGiftUnlimitedValue) goto L27;
        if (this.currentGiftUniqueValue != this.selectedGiftUniqueValue) goto L27;
        if (this.currentGiftChannelsValue != this.selectedGiftChannelsValue) goto L27;
        if (this.currentGiftPremiumValue == this.selectedGiftPremiumValue) goto L28;
    L5:
        if (this.currentType != 0) goto L11;
        ArrayList<Long> r02 = this.currentMinus;
        if (r02 == null) goto L13;
        if (r02.isEmpty() == true) goto L13;
    L11:
        if (this.currentReadValue == this.selectedReadValue) goto L13;
        return true;
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$13(boolean[] r3) {
        r3[0] = true;
        if (r3[1] == false) goto L6;
        finished();
        return;
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$14(TLRPC.TL_error r3, TLObject r4, boolean[] r5) {
        if (r3 != null) goto L5;
        TL_account.privacyRules r42 = (TL_account.privacyRules) r4;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
        ContactsController.getInstance(this.currentAccount).setPrivacyRules(r42.rules, 13);
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda26(this, r5));
        return;
    L5:
        showErrorAlert();
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$15(boolean[] r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda20(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$16(TLRPC.TL_error r5, boolean[] r6, TLRPC.GlobalPrivacySettings r7, TL_account.setGlobalPrivacySettings r8) {
        if (r5 == null) goto L5;
        showErrorAlert();
        return;
    L5:
        r6[1] = true;
        if (r7 == null) goto L12;
        TLRPC.GlobalPrivacySettings r52 = r8.settings;
        r7.new_noncontact_peers_require_premium = r52.new_noncontact_peers_require_premium;
        int r82 = r52.flags;
        r7.flags = r82;
        r7.disallowed_stargifts = r52.disallowed_stargifts;
        long r0 = r52.noncontact_peers_paid_stars;
        if (r0 <= 0) goto L10;
        r7.flags = r82 | 32;
        r7.noncontact_peers_paid_stars = r0;
        goto L12
    L10:
        r7.flags = r82 & (-33);
        r7.noncontact_peers_paid_stars = 0;
    L12:
        if (r6[0] == false) goto L14;
        finished();
    L14:
        getNotificationCenter().postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$17(boolean[] r1, TLRPC.GlobalPrivacySettings r2, TL_account.setGlobalPrivacySettings r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda23(this, r5, r1, r2, r3));
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$18(TLRPC.TL_error r2, TLObject r3, AtomicInteger r4) {
        if (r2 != null) goto L7;
        ContactsController.getInstance(this.currentAccount).setPrivacyRules(((TL_account.privacyRules) r3).rules, 7);
        if (r4.decrementAndGet() != 0) goto L9;
        finished();
        return;
    L9:
        return;
    L7:
        BulletinFactory.of(this).showForError(r2);
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$19(AtomicInteger r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda19(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$20(TLRPC.TL_error r3, TLObject r4, AtomicInteger r5) {
        if (r3 != null) goto L7;
        TL_account.privacyRules r42 = (TL_account.privacyRules) r4;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
        ContactsController.getInstance(this.currentAccount).setPrivacyRules(r42.rules, this.rulesType);
        if (r5.decrementAndGet() != 0) goto L9;
        finished();
        return;
    L9:
        return;
    L7:
        showErrorAlert();
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$21(AtomicInteger r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda21(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$22(TLRPC.GlobalPrivacySettings r1, TL_account.setGlobalPrivacySettings r2, AtomicInteger r3) {
        boolean r22 = r2.settings.hide_read_marks;
        this.currentReadValue = r22;
        r1.hide_read_marks = r22;
        if (r3.decrementAndGet() != 0) goto L6;
        finished();
        return;
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$23(TLRPC.GlobalPrivacySettings r1, TL_account.setGlobalPrivacySettings r2, AtomicInteger r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda24(this, r1, r2, r3));
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$24(TLRPC.GlobalPrivacySettings r4, TL_account.setGlobalPrivacySettings r5, AtomicInteger r6) {
        if (r4.display_gifts_button == r5.settings.display_gifts_button) goto L7;
        TLRPC.UserFull r0 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r0 == null) goto L7;
        r0.display_gifts_button = r5.settings.display_gifts_button;
        getMessagesStorage().updateUserInfo(r0, false);
    L7:
        boolean r02 = r5.settings.display_gifts_button;
        this.currentGiftIconValue = r02;
        r4.display_gifts_button = r02;
        if (r4.disallowed_stargifts != null) goto L10;
        r4.disallowed_stargifts = new TLRPC.DisallowedGiftsSettings();
    L10:
        TLRPC.DisallowedGiftsSettings r52 = r5.settings.disallowed_stargifts;
        if (r52 == null) goto L14;
        r4.flags |= 64;
        TLRPC.DisallowedGiftsSettings r42 = r4.disallowed_stargifts;
        boolean r03 = r52.disallow_unlimited_stargifts;
        r42.disallow_unlimited_stargifts = r03;
        this.currentGiftUnlimitedValue = !r03;
        boolean r04 = r52.disallow_limited_stargifts;
        r42.disallow_limited_stargifts = r04;
        this.currentGiftLimitedValue = !r04;
        boolean r05 = r52.disallow_unique_stargifts;
        r42.disallow_unique_stargifts = r05;
        this.currentGiftUniqueValue = !r05;
        boolean r06 = r52.disallow_stargifts_from_channels;
        r42.disallow_stargifts_from_channels = r06;
        this.currentGiftChannelsValue = !r06;
        boolean r53 = r52.disallow_premium_gifts;
        r42.disallow_premium_gifts = r53;
        this.currentGiftPremiumValue = !r53;
    L14:
        if (r6.decrementAndGet() != 0) goto L17;
        finished();
        return;
    }

    private /* synthetic */ void lambda$applyCurrentPrivacySettings$25(TLRPC.GlobalPrivacySettings r1, TL_account.setGlobalPrivacySettings r2, AtomicInteger r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda25(this, r1, r2, r3));
    }

    private /* synthetic */ void lambda$checkDiscard$27(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$checkDiscard$28(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$3() {
        this.avatarForRest = null;
        this.avatarForRestPhoto = null;
        TLRPC.UserFull r1 = getMessagesController().getUserFull(getUserConfig().clientUserId);
        if (r1 == null) goto L12;
        TLRPC.Photo r2 = r1.fallback_photo;
        if (r2 == null) goto L13;
        r1.flags &= -4194305;
        r1.fallback_photo = null;
        getMessagesStorage().updateUserInfo(r1, true);
        updateAvatarForRestInfo();
        updateRows(true);
        TLRPC.TL_inputPhoto r0 = new TLRPC.TL_inputPhoto();
        r0.id = r2.id;
        r0.access_hash = r2.access_hash;
        byte[] r12 = r2.file_reference;
        r0.file_reference = r12;
        if (r12 != null) goto L10;
        r0.file_reference = new byte[0];
    L10:
        MessagesController.getInstance(this.currentAccount).deleteUserPhoto(r0);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.reloadDialogPhotos, new Object[0]);
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$createView$5(DialogInterface r2) {
        boolean r22 = this.imageUpdater.isUploadingImage();
        RLottieDrawable r0 = this.cameraDrawable;
        if (r22 == true) goto L6;
        r0.setCustomEndFrame(86);
        this.setAvatarCell.imageView.playAnimation();
        return;
    L6:
        r0.setCurrentFrame(0, false);
    }

    private /* synthetic */ void lambda$createView$6() {
        presentFragment(new PremiumPreviewFragment("noncontacts"));
    }

    private /* synthetic */ void lambda$createView$7() {
        presentFragment(new PremiumPreviewFragment("settings"));
    }

    private /* synthetic */ void lambda$createView$8(int r5, boolean r6, boolean r7, boolean r8, ArrayList r9) {
        int r1 = 0;
        boolean r2 = true;
        if (r5 != this.neverShareRow) goto L13;
        this.currentMinus = r9;
        boolean[] r52 = this.currentPlusMiniapps;
        int r72 = this.currentType;
        if (r6 == false) goto L8;
        if (r8 == false) goto L8;
    L9:
        r52[r72] = r2;
    L11:
        if (r1 >= this.currentMinus.size()) goto L26;
        this.currentPlus.remove(this.currentMinus.get(r1));
        r1 = r1 + 1;
    L26:
        updateDoneButton();
        this.listAdapter.notifyDataSetChanged();
        return;
    L8:
        r2 = false;
        goto L9
    L13:
        boolean[] r53 = this.currentPlusPremium;
        int r0 = this.currentType;
        if (r0 != 2) goto L16;
        char r3 = 0;
    L17:
        r53[r3] = r7;
        boolean[] r54 = this.currentPlusMiniapps;
        if (r6 == false) goto L21;
        if (r8 == false) goto L21;
    L22:
        r54[r0] = r2;
        this.currentPlus = r9;
    L24:
        if (r1 >= this.currentPlus.size()) goto L26;
        this.currentMinus.remove(this.currentPlus.get(r1));
        r1 = r1 + 1;
    L21:
        r2 = false;
        goto L22
    L16:
        r3 = 1;
        goto L17
    }

    private /* synthetic */ void lambda$createView$9(View r10, int r11) {
        if (r11 != this.currentPhotoForRestRow) goto L6;
        AlertDialog r9 = AlertsCreator.createSimpleAlert(getContext(), LocaleController.getString(R.string.RemovePublicPhoto), LocaleController.getString(R.string.RemovePhotoForRestDescription), LocaleController.getString(R.string.Remove), new PrivacyControlActivity$$ExternalSyntheticLambda3(this), null).create();
        r9.show();
        r9.redPositive();
        return;
    L6:
        int r1 = 0;
        boolean r12 = false;
        r12 = false;
        int r13 = 0;
        if (r11 != this.photoForRestRow) goto L13;
        ImageUpdater r102 = this.imageUpdater;
        if (r102 == null) goto L237;
        r102.openMenu(false, new PrivacyControlActivity$$ExternalSyntheticLambda4(), new PrivacyControlActivity$$ExternalSyntheticLambda5(this), 0);
        this.cameraDrawable.setCurrentFrame(0);
        this.cameraDrawable.setCustomEndFrame(43);
        this.setAvatarCell.imageView.playAnimation();
        return;
    L237:
        return;
    L13:
        if (this.rulesType != 10) goto L23;
        if (r11 != this.myContactsRow) goto L23;
        if (getMessagesController().newNoncontactPeersRequirePremiumWithoutOwnpremium == true) goto L23;
        if (getUserConfig().isPremium() == true) goto L23;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.star_premium_2, LocaleController.getString(R.string.OptionPremiumRequiredTitle), AndroidUtilities.replaceTags(LocaleController.getString(R.string.OptionPremiumRequiredMessage)), LocaleController.getString(R.string.OptionPremiumRequiredButton), new PrivacyControlActivity$$ExternalSyntheticLambda6(this)).show();
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        int r112 = -this.shakeDp;
        this.shakeDp = r112;
        AndroidUtilities.shakeViewSpring(r10, r112);
        return;
    L23:
        if (this.rulesType != 8) goto L33;
        if (r11 == this.myContactsRow) goto L29;
        if (r11 != this.nobodyRow) goto L33;
    L29:
        if (getUserConfig().isPremium() == true) goto L33;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.star_premium_2, LocaleController.getString(R.string.OptionPremiumRequiredTitle), AndroidUtilities.replaceTags(LocaleController.getString(R.string.OptionPremiumRequiredMessage)), LocaleController.getString(R.string.OptionPremiumRequiredButton), new PrivacyControlActivity$$ExternalSyntheticLambda7(this)).show();
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        int r113 = -this.shakeDp;
        this.shakeDp = r113;
        AndroidUtilities.shakeViewSpring(r10, r113);
        return;
    L33:
        if (r11 == this.nobodyRow) goto L220;
        if (r11 == this.everybodyRow) goto L220;
        if (r11 == this.myContactsRow) goto L220;
        if (r11 == this.payRow) goto L220;
        if (r11 == this.phoneContactsRow) goto L211;
        if (r11 == this.phoneEverybodyRow) goto L211;
        if (r11 == this.neverShareRow) goto L153;
        if (r11 == this.alwaysShareRow) goto L153;
        if (r11 != this.p2pRow) goto L56;
        presentFragment(new PrivacyControlActivity(3));
        return;
    L56:
        if (r11 != this.readRow) goto L60;
        this.selectedReadValue = !this.selectedReadValue;
        updateDoneButton();
        ((TextCheckCell) r10).setChecked(this.selectedReadValue);
        return;
    L60:
        if (r11 != this.readPremiumRow) goto L64;
        presentFragment(new PremiumPreviewFragment("lastseen"));
        return;
    L64:
        if (r11 != this.showGiftIconRow) goto L68;
        this.selectedGiftIconValue = !this.selectedGiftIconValue;
        updateDoneButton();
        ((TextCheckCell) r10).setChecked(this.selectedGiftIconValue);
        return;
    L68:
        if (r11 != this.giftTypeLimitedRow) goto L85;
        if (this.selectedGiftLimitedValue == true) goto L72;
    L75:
        boolean r114 = areAllStarGiftsDisabled();
        this.selectedGiftLimitedValue = !this.selectedGiftLimitedValue;
        updateDoneButton();
        TextCheckCell r103 = (TextCheckCell) r10;
        r103.setChecked(this.selectedGiftLimitedValue);
        if (this.selectedGiftLimitedValue == false) goto L81;
        if (getUserConfig().isPremium() == true) goto L81;
        r103.setCheckBoxIcon(R.drawable.permission_locked);
    L81:
        if (r114 == areAllStarGiftsDisabled()) goto L240;
        updateRows(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L240:
        return;
    L72:
        if (getUserConfig().isPremium() == true) goto L75;
        int r115 = -this.shakeDp;
        this.shakeDp = r115;
        AndroidUtilities.shakeViewSpring(r10, r115);
        showPremiumBulletin();
        return;
    L85:
        if (r11 != this.giftTypeUnlimitedRow) goto L102;
        if (this.selectedGiftUnlimitedValue == true) goto L89;
    L92:
        boolean r116 = areAllStarGiftsDisabled();
        this.selectedGiftUnlimitedValue = !this.selectedGiftUnlimitedValue;
        updateDoneButton();
        TextCheckCell r104 = (TextCheckCell) r10;
        r104.setChecked(this.selectedGiftUnlimitedValue);
        if (this.selectedGiftUnlimitedValue == false) goto L98;
        if (getUserConfig().isPremium() == true) goto L98;
        r104.setCheckBoxIcon(R.drawable.permission_locked);
    L98:
        if (r116 == areAllStarGiftsDisabled()) goto L241;
        updateRows(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L241:
        return;
    L89:
        if (getUserConfig().isPremium() == true) goto L92;
        int r117 = -this.shakeDp;
        this.shakeDp = r117;
        AndroidUtilities.shakeViewSpring(r10, r117);
        showPremiumBulletin();
        return;
    L102:
        if (r11 != this.giftTypeUniqueRow) goto L119;
        if (this.selectedGiftUniqueValue == true) goto L106;
    L109:
        boolean r118 = areAllStarGiftsDisabled();
        this.selectedGiftUniqueValue = !this.selectedGiftUniqueValue;
        updateDoneButton();
        TextCheckCell r105 = (TextCheckCell) r10;
        r105.setChecked(this.selectedGiftUniqueValue);
        if (this.selectedGiftUniqueValue == false) goto L115;
        if (getUserConfig().isPremium() == true) goto L115;
        r105.setCheckBoxIcon(R.drawable.permission_locked);
    L115:
        if (r118 == areAllStarGiftsDisabled()) goto L242;
        updateRows(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L242:
        return;
    L106:
        if (getUserConfig().isPremium() == true) goto L109;
        int r119 = -this.shakeDp;
        this.shakeDp = r119;
        AndroidUtilities.shakeViewSpring(r10, r119);
        showPremiumBulletin();
        return;
    L119:
        if (r11 != this.giftTypeChannelsRow) goto L136;
        if (this.selectedGiftChannelsValue == true) goto L123;
    L126:
        boolean r1110 = areAllStarGiftsDisabled();
        this.selectedGiftChannelsValue = !this.selectedGiftChannelsValue;
        updateDoneButton();
        TextCheckCell r106 = (TextCheckCell) r10;
        r106.setChecked(this.selectedGiftChannelsValue);
        if (this.selectedGiftChannelsValue == false) goto L132;
        if (getUserConfig().isPremium() == true) goto L132;
        r106.setCheckBoxIcon(R.drawable.permission_locked);
    L132:
        if (r1110 == areAllStarGiftsDisabled()) goto L243;
        updateRows(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L243:
        return;
    L123:
        if (getUserConfig().isPremium() == true) goto L126;
        int r1111 = -this.shakeDp;
        this.shakeDp = r1111;
        AndroidUtilities.shakeViewSpring(r10, r1111);
        showPremiumBulletin();
        return;
    L136:
        if (r11 == this.giftTypePremiumRow) goto L138;
        return;
    L138:
        if (this.selectedGiftPremiumValue == true) goto L140;
    L143:
        boolean r1112 = areAllStarGiftsDisabled();
        this.selectedGiftPremiumValue = !this.selectedGiftPremiumValue;
        updateDoneButton();
        TextCheckCell r107 = (TextCheckCell) r10;
        r107.setChecked(this.selectedGiftPremiumValue);
        if (this.selectedGiftPremiumValue == false) goto L149;
        if (getUserConfig().isPremium() == true) goto L149;
        r107.setCheckBoxIcon(R.drawable.permission_locked);
    L149:
        if (r1112 == areAllStarGiftsDisabled()) goto L245;
        updateRows(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L245:
        return;
    L140:
        if (getUserConfig().isPremium() == true) goto L143;
        int r1113 = -this.shakeDp;
        this.shakeDp = r1113;
        AndroidUtilities.shakeViewSpring(r10, r1113);
        showPremiumBulletin();
        return;
    L153:
        if (this.rulesType != 12) goto L159;
        if (areAllStarGiftsDisabled() == false) goto L159;
        int r1114 = -this.shakeDp;
        this.shakeDp = r1114;
        AndroidUtilities.shakeViewSpring(r10, r1114);
        return;
    L159:
        if (r11 != this.neverShareRow) goto L161;
        ArrayList<Long> r108 = this.currentMinus;
    L162:
        Bundle r0 = new Bundle();
        if (r11 != this.neverShareRow) goto L165;
        String r3 = "isNeverShare";
    L166:
        r0.putBoolean(r3, true);
        if (this.rulesType == 0) goto L169;
        int r32 = 1;
    L170:
        r0.putInt("chatAddType", r32);
        if (r11 != this.alwaysShareRow) goto L176;
        if (this.rulesType != 1) goto L176;
        r0.putBoolean("allowPremium", true);
    L176:
        if (this.rulesType != 12) goto L190;
        int r33 = this.currentType;
        if (r33 == 1) goto L180;
        if (r33 == 2) goto L184;
        if (r33 != 0) goto L190;
        if (r11 != this.neverShareRow) goto L190;
    L181:
        boolean r34 = true;
    L191:
        r0.putBoolean("allowMiniapps", r34);
        GroupCreateActivity r5 = new GroupCreateActivity(r0);
        if (this.rulesType != 10) goto L195;
        r5.setTitle(LocaleController.getString(R.string.RemoveMessageFeeTitle));
    L195:
        if (r11 != this.alwaysShareRow) goto L203;
        boolean[] r02 = this.currentPlusPremium;
        if (this.currentType != 2) goto L199;
        char r2 = 0;
    L201:
        if (r02[r2] == false) goto L203;
        boolean r03 = true;
    L204:
        if (r34 == true) goto L206;
    L208:
        r5.select(r108, r03, r12);
        r5.setDelegate(new PrivacyControlActivity$$ExternalSyntheticLambda8(this, r11, r34));
        r5.setShowDiscardConfirm(true);
        presentFragment(r5);
        return;
    L206:
        if (this.currentPlusMiniapps[this.currentType] == false) goto L208;
        r12 = true;
        goto L208
    L199:
        r2 = 1;
    L203:
        r03 = false;
        goto L204
    L184:
        if (r11 != this.alwaysShareRow) goto L190;
    L180:
        if (r11 == this.alwaysShareRow) goto L181;
    L190:
        r34 = false;
        goto L191
    L169:
        r32 = 0;
        goto L170
    L165:
        r3 = "isAlwaysShare";
        goto L166
    L161:
        r108 = this.currentPlus;
    L211:
        if (r11 == this.phoneEverybodyRow) goto L215;
        r13 = 1;
    L215:
        if (r13 == this.currentSubType) goto L247;
        this.currentSubType = r13;
        updateDoneButton();
        updateRows(true);
        return;
    L247:
        return;
    L220:
        if (this.rulesType != 12) goto L226;
        if (areAllStarGiftsDisabled() == false) goto L226;
        int r1115 = -this.shakeDp;
        this.shakeDp = r1115;
        AndroidUtilities.shakeViewSpring(r10, r1115);
        return;
    L226:
        if (r11 != this.nobodyRow) goto L229;
        r1 = 1;
    L236:
        if (r1 == this.currentType) goto L246;
        this.currentType = r1;
        Bulletin.hideVisible();
        updateDoneButton();
        updateRows(true);
        return;
    L246:
        return;
    L229:
        if (r11 == this.everybodyRow) goto L236;
        if (r11 != this.payRow) goto L234;
        r1 = 3;
        goto L236
    L234:
        r1 = 2;
        goto L236
    }

    private /* synthetic */ void lambda$didUploadPhoto$0(TLObject r10) {
        if (r10 == null) goto L13;
        TLRPC.TL_photos_photo r102 = (TLRPC.TL_photos_photo) r10;
        TLRPC.UserFull r0 = getMessagesController().getUserFull(getUserConfig().clientUserId);
        r0.flags |= 4194304;
        r0.fallback_photo = r102.photo;
        getMessagesStorage().updateUserInfo(r0, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.reloadDialogPhotos, new Object[0]);
        TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r102.photo.sizes, 100);
        TLRPC.PhotoSize r103 = FileLoader.getClosestPhotoSizeWithSize(r102.photo.sizes, 1000);
        if (r02 != null) goto L6;
    L8:
        if (r103 != null) goto L10;
        return;
    L10:
        if (this.avatarForRest == null) goto L15;
        FileLoader.getInstance(this.currentAccount).getPathToAttach(this.avatarForRest.location, true).renameTo(FileLoader.getInstance(this.currentAccount).getPathToAttach(r103, true));
        return;
    L15:
        return;
    L6:
        if (this.avatarForRest == null) goto L8;
        FileLoader.getInstance(this.currentAccount).getPathToAttach(this.avatarForRest, true).renameTo(FileLoader.getInstance(this.currentAccount).getPathToAttach(r02, true));
        ImageLoader.getInstance().replaceImageInCache(this.avatarForRest.location.volume_id + "_" + this.avatarForRest.location.local_id + "@50_50", r02.location.volume_id + "_" + r02.location.local_id + "@50_50", ImageLocation.getForLocal(r02.location), false);
        goto L8
    }

    private /* synthetic */ void lambda$didUploadPhoto$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda22(this, r1));
    }

    private /* synthetic */ void lambda$didUploadPhoto$2(TLRPC.PhotoSize r3, TLRPC.InputFile r4, TLRPC.InputFile r5, double r6, TLRPC.VideoSize r8, TLRPC.PhotoSize r9) {
        this.avatarForRest = r3;
        this.avatarForRestPhoto = null;
        updateAvatarForRestInfo();
        if (r4 != null) goto L5;
        if (r5 != null) goto L5;
    L13:
        updateRows(false);
        return;
    L5:
        TLRPC.TL_photos_uploadProfilePhoto r0 = new TLRPC.TL_photos_uploadProfilePhoto();
        if (r4 == null) goto L8;
        r0.file = r4;
        r0.flags |= 1;
    L8:
        if (r5 == null) goto L10;
        r0.video = r5;
        int r42 = r0.flags;
        r0.video_start_ts = r6;
        r0.flags = r42 | 6;
    L10:
        if (r8 == null) goto L12;
        r0.video_emoji_markup = r8;
        r0.flags |= 16;
    L12:
        r0.fallback = true;
        r0.flags |= 8;
        getConnectionsManager().sendRequest(r0, new PrivacyControlActivity$$ExternalSyntheticLambda12(this));
        TLRPC.TL_user r43 = new TLRPC.TL_user();
        TLRPC.TL_userProfilePhoto r52 = new TLRPC.TL_userProfilePhoto();
        r43.photo = r52;
        r52.photo_small = r3.location;
        r52.photo_big = r9.location;
        r43.first_name = getUserConfig().getCurrentUser().first_name;
        r43.last_name = getUserConfig().getCurrentUser().last_name;
        r43.access_hash = getUserConfig().getCurrentUser().access_hash;
        BulletinFactory.of(this).createUsersBulletin(Collections.singletonList(r43), LocaleController.getString(R.string.PhotoForRestTooltip)).show();
        goto L13
    }

    private /* synthetic */ void lambda$finished$11(int r1, AlertDialog r2, int r3) {
        presentFragment(new PrivacyControlActivity(r1), true);
    }

    private /* synthetic */ void lambda$finished$12(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$processDone$26(SharedPreferences r1, AlertDialog r2, int r3) {
        applyCurrentPrivacySettings();
        r1.edit().putBoolean("privacyAlertShowed", true).apply();
    }

    private /* synthetic */ void lambda$showPremiumBulletin$10() {
        presentFragment(new PremiumPreviewFragment("noncontacts"));
    }

    private void processDone() {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (this.rulesType != 10) goto L14;
        if (this.currentType != 3) goto L14;
        if (getUserConfig().isPremium() == true) goto L14;
        showDialog(new PremiumFeatureBottomSheet(this, 27, true));
        return;
    L14:
        if (this.currentType != 0) goto L16;
    L27:
        applyCurrentPrivacySettings();
        return;
    L16:
        if (this.rulesType != 0) goto L27;
        if (getUserConfig().isPremium() == true) goto L27;
        SharedPreferences r0 = MessagesController.getGlobalMainSettings();
        if (r0.getBoolean("privacyAlertShowed", false) == true) goto L27;
        AlertDialog.Builder r1 = new AlertDialog.Builder(getParentActivity());
        if (this.rulesType != 1) goto L24;
        r1.setMessage(LocaleController.getString(R.string.WhoCanAddMeInfo));
    L25:
        r1.setTitle(LocaleController.getString(R.string.AppName));
        r1.setPositiveButton(LocaleController.getString(R.string.OK), new PrivacyControlActivity$$ExternalSyntheticLambda10(this, r0));
        r1.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r1.create());
        return;
    L24:
        r1.setMessage(LocaleController.getString(R.string.CustomHelp));
        goto L25
    }

    private void setMessageText() {
        MessageCell r0 = this.messageCell;
        if (r0 == null) goto L13;
        TLRPC.MessageFwdHeader r02 = MessageCell.m17708$$Nest$fgetmessageObject(r0).messageOwner.fwd_from;
        r02.from_id = new TLRPC.TL_peerUser();
        int r03 = this.currentType;
        if (r03 != 0) goto L7;
        MessageCell.m17707$$Nest$fgethintView(this.messageCell).setOverrideText(LocaleController.getString(R.string.PrivacyForwardsEverybody));
        MessageCell.m17708$$Nest$fgetmessageObject(this.messageCell).messageOwner.fwd_from.from_id.user_id = 0;
    L11:
        MessageCell.m17706$$Nest$fgetcell(this.messageCell).forceResetMessageObject();
        return;
    L7:
        MessageCell r3 = this.messageCell;
        if (r03 != 1) goto L10;
        MessageCell.m17707$$Nest$fgethintView(r3).setOverrideText(LocaleController.getString(R.string.PrivacyForwardsNobody));
        MessageCell.m17708$$Nest$fgetmessageObject(this.messageCell).messageOwner.fwd_from.from_id.user_id = 1;
        goto L11
    L10:
        MessageCell.m17707$$Nest$fgethintView(r3).setOverrideText(LocaleController.getString(R.string.PrivacyForwardsContacts));
        MessageCell.m17708$$Nest$fgetmessageObject(this.messageCell).messageOwner.fwd_from.from_id.user_id = 0;
        goto L11
    }

    private void showErrorAlert() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.AppName));
        r0.setMessage(LocaleController.getString(R.string.PrivacyFloodControlError));
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r0.create());
    }

    private void showPremiumBulletin() {
        BulletinFactory.of(this).createSimpleBulletin(R.raw.star_premium_2, LocaleController.getString(R.string.OptionPremiumRequiredTitle), AndroidUtilities.replaceTags(LocaleController.getString(R.string.OptionPremiumRequiredMessage)), LocaleController.getString(R.string.OptionPremiumRequiredButton), new PrivacyControlActivity$$ExternalSyntheticLambda11(this)).show();
    }

    private void updateAvatarForRestInfo() {
        TextCell r0 = this.setAvatarCell;
        if (r0 != null) goto L5;
    L8:
        BackupImageView r02 = this.oldAvatarView;
        if (r02 == null) goto L18;
        TLRPC.PhotoSize r1 = this.avatarForRest;
        if (r1 == null) goto L19;
        TLRPC.Photo r2 = this.avatarForRestPhoto;
        if (r2 == null) goto L16;
        r02.setImage(ImageLocation.getForPhoto(r1, r2), "50_50", null, UserConfig.getInstance(this.currentAccount).getCurrentUser());
        return;
    L16:
        r02.setImage(ImageLocation.getForLocal(r1.location), "50_50", null, UserConfig.getInstance(this.currentAccount).getCurrentUser());
        return;
    L19:
        return;
    L18:
        return;
    L5:
        if (this.avatarForRest != null) goto L7;
        r0.getTextView().setText(LocaleController.formatString("SetPhotoForRest", R.string.SetPhotoForRest, new Object[0]));
        this.setAvatarCell.setNeedDivider(false);
        goto L8
    L7:
        r0.getTextView().setText(LocaleController.formatString("UpdatePhotoForRest", R.string.UpdatePhotoForRest, new Object[0]));
        this.setAvatarCell.setNeedDivider(true);
        goto L8
    }

    private void updateDoneButton() {
        boolean r0 = hasChanges();
        this.doneButton.setEnabled(r0);
        ViewPropertyAnimator r4 = this.doneButton.animate();
        float r1 = 0.0f;
        if (r0 == false) goto L5;
        float r3 = 1.0f;
    L6:
        ViewPropertyAnimator r42 = r4.alpha(r3);
        if (r0 == false) goto L9;
        float r32 = 1.0f;
    L10:
        ViewPropertyAnimator r43 = r42.scaleX(r32);
        if (r0 == false) goto L13;
        r1 = 1.0f;
    L13:
        r43.scaleY(r1).setDuration(180).start();
        return;
    L9:
        r32 = 0.0f;
        goto L10
    L5:
        r3 = 0.0f;
        goto L6
    }

    private void updateRows(boolean r17) {
        DiffCallback r1 = null;
        if (r17 == false) goto L5;
        DiffCallback r2 = new DiffCallback(this, null);
        r2.fillPositions(r2.oldPositionToItem);
        r2.oldRowCount = this.rowCount;
        r1 = r2;
    L5:
        this.photoForRestRow = -1;
        this.currentPhotoForRestRow = -1;
        this.photoForRestDescriptionRow = -1;
        this.messageRow = -1;
        this.setBirthdayRow = -1;
        this.phoneDetailRow = -1;
        this.phoneSectionRow = -1;
        this.phoneEverybodyRow = -1;
        this.phoneContactsRow = -1;
        this.alwaysShareRow = -1;
        this.neverShareRow = -1;
        this.p2pSectionRow = -1;
        this.p2pRow = -1;
        this.detailRow = -1;
        this.detailRow2 = -1;
        this.p2pDetailRow = -1;
        this.readDetailRow = -1;
        this.readRow = -1;
        this.nobodyRow = -1;
        this.shareSectionRow = -1;
        this.shareDetailRow = -1;
        this.payRow = -1;
        this.priceHeaderRow = -1;
        this.priceRow = -1;
        this.priceInfoRow = -1;
        this.priceButtonRow = -1;
        this.readPremiumRow = -1;
        this.readPremiumDetailRow = -1;
        this.showGiftIconRow = -1;
        this.showGiftIconInfoRow = -1;
        this.giftTypesHeaderRow = -1;
        this.giftTypeUnlimitedRow = -1;
        this.giftTypeLimitedRow = -1;
        this.giftTypeUniqueRow = -1;
        this.giftTypeChannelsRow = -1;
        this.giftTypePremiumRow = -1;
        this.giftTypesInfoRow = -1;
        this.rowCount = 0;
        int r3 = this.rulesType;
        if (r3 != 12) goto L9;
        this.showGiftIconRow = 0;
        this.rowCount = 1 + 1;
        this.showGiftIconInfoRow = 1;
    L9:
        if (r3 != 11) goto L15;
        TLRPC.UserFull r32 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r32 == null) goto L15;
        if (r32.birthday != null) goto L15;
        int r33 = this.rowCount;
        this.rowCount = r33 + 1;
        this.setBirthdayRow = r33;
    L15:
        int r34 = this.rulesType;
        if (r34 != 5) goto L18;
        int r8 = this.rowCount;
        this.rowCount = r8 + 1;
        this.messageRow = r8;
    L18:
        int r82 = this.rowCount;
        this.sectionRow = r82;
        this.everybodyRow = r82 + 1;
        int r9 = r82 + 3;
        this.rowCount = r9;
        this.myContactsRow = r82 + 2;
        if (r34 != 4) goto L21;
    L33:
        this.rowCount = r82 + 4;
        this.nobodyRow = r9;
    L35:
        if (getMessagesController().starsPaidMessagesAvailable == true) goto L37;
    L39:
        int r35 = this.rulesType;
        if (r35 == 6) goto L42;
    L44:
        if (r35 == 10) goto L46;
    L48:
        int r7 = this.rowCount;
        this.rowCount = r7 + 1;
        this.detailRow = r7;
    L49:
        if (r35 == 10) goto L51;
        if (r35 == 8) goto L60;
    L61:
        int r36 = this.rowCount;
        int r6 = r36 + 1;
        this.rowCount = r6;
        this.shareSectionRow = r36;
        int r72 = this.currentType;
        if (r72 == 1) goto L64;
        if (r72 == 2) goto L64;
    L65:
        if (r72 == 0) goto L67;
        if (r72 == 2) goto L67;
    L68:
        int r37 = this.rowCount;
        int r62 = r37 + 1;
        this.rowCount = r62;
        this.shareDetailRow = r37;
        int r73 = this.rulesType;
        if (r73 != 2) goto L71;
        this.p2pSectionRow = r62;
        this.p2pRow = r37 + 2;
        this.rowCount = r37 + 4;
        this.p2pDetailRow = r37 + 3;
    L71:
        if (r73 != 4) goto L82;
        if (this.currentMinus.size() > 0) goto L77;
        int r38 = this.currentType;
        if (r38 == 2) goto L77;
        if (r38 != 1) goto L82;
    L77:
        int r39 = this.rowCount;
        int r63 = r39 + 1;
        this.rowCount = r63;
        this.photoForRestRow = r39;
        if (this.avatarForRest == null) goto L80;
        this.rowCount = r39 + 2;
        this.currentPhotoForRestRow = r63;
    L80:
        int r310 = this.rowCount;
        this.rowCount = r310 + 1;
        this.photoForRestDescriptionRow = r310;
    L82:
        if (this.rulesType != 0) goto L91;
        if (this.currentType != 0) goto L89;
        ArrayList<Long> r311 = this.currentMinus;
        if (r311 == null) goto L91;
        if (r311.isEmpty() == true) goto L91;
    L89:
        int r312 = this.rowCount;
        this.readRow = r312;
        this.rowCount = r312 + 2;
        this.readDetailRow = r312 + 1;
    L91:
        if (this.rulesType == 0) goto L93;
    L96:
        if (this.rulesType != 12) goto L98;
        int r313 = this.rowCount;
        this.giftTypesHeaderRow = r313;
        this.giftTypeLimitedRow = r313 + 1;
        this.giftTypeUnlimitedRow = r313 + 2;
        this.giftTypeUniqueRow = r313 + 3;
        this.giftTypeChannelsRow = r313 + 4;
        this.giftTypePremiumRow = r313 + 5;
        this.rowCount = r313 + 7;
        this.giftTypesInfoRow = r313 + 6;
    L98:
        setMessageText();
        ListAdapter r314 = this.listAdapter;
        if (r314 == null) goto L151;
        if (r17 == false) goto L144;
        int r315 = this.listView.getChildCount();
        int r4 = 0;
    L102:
        if (r4 >= r315) goto L142;
        View r64 = this.listView.getChildAt(r4);
        if ((r64 instanceof RadioCell) == false) goto L141;
        RecyclerView.ViewHolder r74 = this.listView.findContainingViewHolder(r64);
        if (r74 == null) goto L141;
        int r75 = r74.getAdapterPosition();
        RadioCell r65 = (RadioCell) r64;
        int r83 = this.everybodyRow;
        if (r75 != r83) goto L112;
    L127:
        if (r75 != r83) goto L130;
        int r76 = 0;
    L137:
        if (this.currentType != r76) goto L139;
        boolean r77 = true;
    L140:
        r65.setChecked(r77, true);
        goto L141
    L139:
        r77 = false;
        goto L140
    L130:
        if (r75 != this.myContactsRow) goto L133;
        r76 = 2;
        goto L137
    L133:
        if (r75 != this.nobodyRow) goto L135;
        r76 = 1;
        goto L137
    L135:
        r76 = 3;
        goto L137
    L112:
        if (r75 == this.myContactsRow) goto L127;
        if (r75 == this.nobodyRow) goto L127;
        if (r75 == this.payRow) goto L127;
        if (r75 != this.phoneContactsRow) goto L121;
        int r78 = 1;
    L123:
        if (this.currentSubType != r78) goto L125;
        boolean r79 = true;
    L126:
        r65.setChecked(r79, true);
        goto L141
    L125:
        r79 = false;
        goto L126
    L121:
        r78 = 0;
    L141:
        r4 = r4 + 1;
        goto L102
    L142:
        r1.fillPositions(r1.newPositionToItem);
        DiffUtil.calculateDiff(r1).dispatchUpdatesTo(this.listAdapter);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L144:
        r314.notifyDataSetChanged();
        return;
    L151:
        return;
    L93:
        if (getMessagesController().premiumFeaturesBlocked() == true) goto L96;
        int r316 = this.rowCount;
        this.readPremiumRow = r316;
        this.rowCount = r316 + 2;
        this.readPremiumDetailRow = r316 + 1;
    L67:
        int r317 = this.rowCount;
        this.rowCount = r317 + 1;
        this.neverShareRow = r317;
    L64:
        this.rowCount = r36 + 2;
        this.alwaysShareRow = r6;
        goto L65
    L60:
        if (getUserConfig().isPremium() == false) goto L96;
    L51:
        if (this.currentType != 3) goto L96;
        int r318 = this.rowCount;
        this.priceHeaderRow = r318;
        this.rowCount = r318 + 2;
        this.priceRow = r318 + 1;
        if (getUserConfig().isPremium() == true) goto L55;
        int r319 = this.rowCount;
        this.rowCount = r319 + 1;
        this.priceButtonRow = r319;
    L55:
        int r320 = this.rowCount;
        this.rowCount = r320 + 1;
        this.priceInfoRow = r320;
        if (getUserConfig().isPremium() == false) goto L96;
        int r321 = this.rowCount;
        this.shareSectionRow = r321;
        this.alwaysShareRow = r321 + 1;
        this.rowCount = r321 + 3;
        this.shareDetailRow = r321 + 2;
        goto L96
    L46:
        if (this.currentType != 3) goto L48;
        int r710 = this.rowCount;
        this.rowCount = r710 + 1;
        this.detailRow2 = r710;
        goto L49
    L42:
        if (this.currentType != 1) goto L44;
        int r711 = this.rowCount;
        this.phoneDetailRow = r711;
        this.phoneSectionRow = r711 + 1;
        this.phoneEverybodyRow = r711 + 2;
        this.rowCount = r711 + 4;
        this.phoneContactsRow = r711 + 3;
        goto L44
    L37:
        if (this.rulesType != 10) goto L39;
        int r322 = this.rowCount;
        this.rowCount = r322 + 1;
        this.payRow = r322;
        goto L39
    L21:
        if (r34 == 9) goto L33;
        if (r34 == 14) goto L33;
        if (r34 == 0) goto L33;
        if (r34 == 2) goto L33;
        if (r34 == 3) goto L33;
        if (r34 == 5) goto L33;
        if (r34 == 6) goto L33;
        if (r34 == 8) goto L33;
        if (r34 == 1) goto L33;
        if (r34 == 11) goto L33;
        if (r34 != 12) goto L35;
        goto L33
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        return checkDiscard(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        if (this.rulesType != 5) goto L5;
        this.messageCell = new MessageCell(this, r8);
    L5:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r0 = this.rulesType;
        if (r0 != 6) goto L8;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyPhone));
    L41:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r02 = this.actionBar.createMenu();
        Drawable r1 = r8.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r4 = Theme.key_actionBarDefaultIcon;
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4), PorterDuff.Mode.MULTIPLY));
        CrossfadeDrawable r3 = new CrossfadeDrawable(r1, new CircularProgressDrawable(Theme.getColor(r4)));
        this.doneButtonDrawable = r3;
        this.doneButton = r02.addItemWithWidth(1, r3, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        boolean r03 = hasChanges();
        View r12 = this.doneButton;
        float r32 = 0.0f;
        if (r03 == false) goto L44;
        float r5 = 1.0f;
    L45:
        r12.setAlpha(r5);
        View r13 = this.doneButton;
        if (r03 == false) goto L48;
        float r52 = 1.0f;
    L49:
        r13.setScaleX(r52);
        View r14 = this.doneButton;
        if (r03 == false) goto L52;
        r32 = 1.0f;
    L52:
        r14.setScaleY(r32);
        this.doneButton.setEnabled(r03);
        this.listAdapter = new ListAdapter(this, r8);
        FrameLayout r04 = new FrameLayout(r8);
        this.fragmentView = r04;
        r04.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        RecyclerListView r15 = new RecyclerListView(r8);
        this.listView = r15;
        r15.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setLayoutManager(new LinearLayoutManager(r8, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        r04.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new PrivacyControlActivity$$ExternalSyntheticLambda0(this));
        AnonymousClass2 r82 = new AnonymousClass2(this);
        r82.setDurations(350);
        r82.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r82.setDelayAnimations(false);
        this.listView.setItemAnimator(r82);
        setMessageText();
        return this.fragmentView;
    L48:
        r52 = 0.0f;
        goto L49
    L44:
        r5 = 0.0f;
        goto L45
    L8:
        if (r0 != 5) goto L11;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyForwards));
        goto L41
    L11:
        if (r0 != 4) goto L14;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyProfilePhoto));
        goto L41
    L14:
        if (r0 != 9) goto L17;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyBio));
        goto L41
    L17:
        if (r0 != 14) goto L20;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyMusic));
        goto L41
    L20:
        if (r0 != 3) goto L23;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyP2P));
        goto L41
    L23:
        if (r0 != 2) goto L25;
        this.actionBar.setTitle(LocaleController.getString(R.string.Calls));
        goto L41
    L25:
        if (r0 != 1) goto L28;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyInvites));
        goto L41
    L28:
        if (r0 != 8) goto L30;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyVoiceMessages));
        goto L41
    L30:
        if (r0 != 0) goto L33;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyLastSeen));
        goto L41
    L33:
        if (r0 != 10) goto L36;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyMessages));
        goto L41
    L36:
        if (r0 != 11) goto L39;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyBirthday));
        goto L41
    L39:
        if (r0 != 12) goto L41;
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyGifts));
        goto L41
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.privacyRulesUpdated) goto L7;
        checkPrivacy();
        return;
    L7:
        if (r1 != NotificationCenter.emojiLoaded) goto L11;
        this.listView.invalidateViews();
        return;
    L11:
        if (r1 != NotificationCenter.didSetNewWallpapper) goto L16;
        MessageCell r0 = this.messageCell;
        if (r0 == null) goto L17;
        r0.invalidate();
        return;
    L17:
        return;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didStartUpload(boolean r1, boolean r2) {
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadPhoto(TLRPC.InputFile r1, TLRPC.InputFile r2, double r3, String r5, TLRPC.PhotoSize r6, TLRPC.PhotoSize r7, boolean r8, TLRPC.VideoSize r9) {
        AndroidUtilities.runOnUIThread(new PrivacyControlActivity$$ExternalSyntheticLambda9(this, r7, r1, r2, r3, r9, r6));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        int r20 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, HeaderCell.class, RadioCell.class}, null, null, null, r20));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r22 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        int r21 = Theme.key_windowBackgroundGray;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ShadowSectionCell.class}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{RadioCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackground));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackgroundChecked));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgInDrawable, Theme.chat_msgInMediaDrawable}, null, Theme.key_chat_inBubble));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgInSelectedDrawable, Theme.chat_msgInMediaSelectedDrawable}, null, Theme.key_chat_inBubbleSelected));
        RecyclerListView r3 = this.listView;
        Drawable[] r7 = Theme.chat_msgInDrawable.getShadowDrawables();
        int r202 = Theme.key_chat_inBubbleShadow;
        r1.add(new ThemeDescription(r3, 0, null, null, r7, null, r202));
        r1.add(new ThemeDescription(this.listView, 0, null, null, Theme.chat_msgInMediaDrawable.getShadowDrawables(), null, r202));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubble));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient1));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient2));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient3));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutSelectedDrawable, Theme.chat_msgOutMediaSelectedDrawable}, null, Theme.key_chat_outBubbleSelected));
        RecyclerListView r4 = this.listView;
        Drawable[] r8 = Theme.chat_msgOutDrawable.getShadowDrawables();
        int r16 = Theme.key_chat_outBubbleShadow;
        r1.add(new ThemeDescription(r4, 0, null, null, r8, null, r16));
        r1.add(new ThemeDescription(this.listView, 0, null, null, Theme.chat_msgOutMediaDrawable.getShadowDrawables(), null, r16));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_messageTextIn));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_messageTextOut));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckDrawable}, null, Theme.key_chat_outSentCheck));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckSelected));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckReadDrawable, Theme.chat_msgOutHalfCheckDrawable}, null, Theme.key_chat_outSentCheckRead));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckReadSelectedDrawable, Theme.chat_msgOutHalfCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckReadSelected));
        r1.add(new ThemeDescription(this.listView, 0, null, null, new Drawable[]{Theme.chat_msgMediaCheckDrawable, Theme.chat_msgMediaHalfCheckDrawable}, null, Theme.key_chat_mediaSentCheck));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_inReplyLine));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_outReplyLine));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_inReplyNameText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_outReplyNameText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_inReplyMessageText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_outReplyMessageText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_inReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_outReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_inTimeText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_outTimeText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_inTimeSelectedText));
        r1.add(new ThemeDescription(this.listView, 0, null, null, null, null, Theme.key_chat_outTimeSelectedText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r1) {
        return checkDiscard(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        checkPrivacy();
        updateRows(false);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.privacyRulesUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.privacyRulesUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L6;
        r0.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        updateRows(false);
        ImageUpdater r1 = this.imageUpdater;
        if (r1 == null) goto L6;
        r1.onResume();
        return;
    }
}
