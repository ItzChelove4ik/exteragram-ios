package org.telegram.ui.Components.Premium.boosts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.adapters.SelectorAdapter;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorBtnCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorHeaderCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PrivacyControlActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes3.dex */
public class UserSelectorBottomSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private static UserSelectorBottomSheet instance;
    private final ButtonWithCounterView actionButton;
    private final HashMap<Long, TLRPC.User> allSelectedObjects;
    private BirthdayController.BirthdayState birthdays;
    private final FrameLayout bulletinContainer;
    private final SelectorBtnCell buttonContainer;
    private final List<TLRPC.TL_contact> contacts;
    private final List<String> contactsLetters;
    private final Map<String, List<TLRPC.TL_contact>> contactsMap;
    private String customTitle;
    private final HashSet<Long> excludeUserIds;
    private final SelectorHeaderCell headerView;
    private final List<TLRPC.TL_topPeer> hints;
    private boolean includeTonOption;
    private boolean isHintSearchText;
    private final ArrayList<SelectorAdapter.Item> items;
    private int listPaddingTop;
    private final ArrayList<SelectorAdapter.Item> oldItems;
    private Runnable onShareCallLinkListener;
    private Utilities.Callback<Long> onUserSelectedListener;
    private Utilities.Callback2<Boolean, HashSet<Long>> onUsersSelectedListener;
    private final List<TLRPC.TL_premiumGiftCodeOption> paymentOptions;
    private String query;
    private float recipientsBtnExtraSpace;
    private ReplacementSpan recipientsBtnSpaceSpan;
    private final Runnable remoteSearchRunnable;
    private int runningRequest;
    private final SelectorSearchCell searchField;
    private final ArrayList<TLObject> searchResult;
    private final View sectionCell;
    private final HashSet<Long> selectedIds;
    private SelectorAdapter selectorAdapter;
    private int tonDays;
    private Drawable tonIcon;
    public int type;
    private long userId;
    private CheckBox2 videoCheckbox;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends UserSelectorBottomSheet {
        final /* synthetic */ int val$finalType;

        public AnonymousClass1(Context r1, int r2, long r3, BirthdayController.BirthdayState r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9) {
            this.val$finalType = r9;
            super(r1, r2, r3, r5, r6, r7, r8);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass2(UserSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            String r0 = UserSelectorBottomSheet.m12145$$Nest$fgetquery(this.this$0);
            if (r0 == null) goto L6;
            UserSelectorBottomSheet.m12152$$Nest$msearch(this.this$0, r0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends ReplacementSpan {
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass3(UserSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (int) UserSelectorBottomSheet.m12146$$Nest$fgetrecipientsBtnExtraSpace(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends SelectorHeaderCell {
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass4(UserSelectorBottomSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorHeaderCell
        public int getHeaderHeight() {
            if (getResources().getConfiguration().orientation != 2) goto L7;
            return AndroidUtilities.dp(48.0f);
        L7:
            return AndroidUtilities.dp(54.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends SelectorSearchCell {
        private boolean isKeyboardVisible;
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass5(UserSelectorBottomSheet r1, Context r2, Theme.ResourcesProvider r3, Runnable r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            UserSelectorBottomSheet.m12149$$Nest$fputlistPaddingTop(this.this$0, getMeasuredHeight() + AndroidUtilities.dp(64.0f));
            UserSelectorBottomSheet.m12148$$Nest$fgetselectorAdapter(this.this$0).notifyChangedLast();
            if (this.isKeyboardVisible == this.this$0.isKeyboardVisible()) goto L8;
            boolean r12 = this.this$0.isKeyboardVisible();
            this.isKeyboardVisible = r12;
            if (r12 == false) goto L9;
            this.this$0.scrollToTop(true);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends View {
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass6(UserSelectorBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r2) {
            r2.drawColor(UserSelectorBottomSheet.access$000(this.this$0, Theme.key_graySection));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends ButtonWithCounterView {
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass7(UserSelectorBottomSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView
        public float calculateCounterWidth(float r3, float r4) {
            if (UserSelectorBottomSheet.m12146$$Nest$fgetrecipientsBtnExtraSpace(this.this$0) != 0.0f) goto L5;
            boolean r42 = true;
        L6:
            UserSelectorBottomSheet.m12150$$Nest$fputrecipientsBtnExtraSpace(this.this$0, r3);
            if (r42 == false) goto L9;
            UserSelectorBottomSheet.m12151$$Nest$mcreateRecipientsBtnSpaceSpan(this.this$0);
            UserSelectorBottomSheet.m12153$$Nest$mupdateActionButton(this.this$0, false);
        L9:
            return r3;
        L5:
            r42 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass8(UserSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(UserSelectorBottomSheet.m12147$$Nest$fgetsearchField(this.this$0).getEditText());
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends RecyclerView.ItemDecoration {
        final /* synthetic */ UserSelectorBottomSheet this$0;

        public AnonymousClass9(UserSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            super.getItemOffsets(r1, r2, r3, r4);
            if (r3.getChildAdapterPosition(r2) != UserSelectorBottomSheet.m12143$$Nest$fgetitems(this.this$0).size()) goto L6;
            r1.bottom = UserSelectorBottomSheet.m12144$$Nest$fgetlistPaddingTop(this.this$0);
            return;
        }
    }

    /* renamed from: $r8$lambda$09zTmQKVsPjnHSh6ct--wkCNq5g, reason: not valid java name */
    public static /* synthetic */ void m12134$r8$lambda$09zTmQKVsPjnHSh6ctwkCNq5g(UserSelectorBottomSheet r0) {
        r0.lambda$didReceivedNotification$25();
    }

    public static /* synthetic */ void $r8$lambda$22rvJ_bo9cQh0nfTIQyDLB2w4H8(UserSelectorBottomSheet r0) {
        r0.lambda$updateItems$18();
    }

    public static /* synthetic */ void $r8$lambda$2PU2M0KEYj0AFBnY0cspq1iFPu8(UserSelectorBottomSheet r0, int r1, Theme.ResourcesProvider r2, int r3, View r4, int r5, float r6, float r7) {
        r0.lambda$new$8(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$2eiyr2IQNdiGp8l_EIS1vfU0t7A(UserSelectorBottomSheet r0, ArrayList r1, View r2) {
        r0.lambda$updateItems$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$42WFZavXH_xG4xuXHnIT95eSor8(UserSelectorBottomSheet r0, TLObject r1) {
        r0.lambda$search$0(r1);
    }

    /* renamed from: $r8$lambda$6CBRB5rLewuL2NzN-MCvS3OIisc, reason: not valid java name */
    public static /* synthetic */ void m12135$r8$lambda$6CBRB5rLewuL2NzNMCvS3OIisc(UserSelectorBottomSheet r0, long r1, View r3) {
        r0.lambda$decorate$15(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$7GT9krL3acyQOW_5JbIY6CvZ0qs(UserSelectorBottomSheet r0) {
        r0.lambda$openBirthdaySetup$29();
    }

    public static /* synthetic */ void $r8$lambda$9XtCNQgr22Vm3CSy2G_LrE2CA70(UserSelectorBottomSheet r0, List r1) {
        r0.lambda$new$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$9kMwPdVJND7yFYOiBn9Ujn6eu2g(UserSelectorBottomSheet r0, TLRPC.UserFull r1, TL_account.TL_birthday r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$openBirthdaySetup$27(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$CXBRyHTocBboLSskVn-1q-z5u-I, reason: not valid java name */
    public static /* synthetic */ void m12136$r8$lambda$CXBRyHTocBboLSskVn1qz5uI(UserSelectorBottomSheet r0) {
        r0.lambda$checkEditTextHint$3();
    }

    public static /* synthetic */ void $r8$lambda$ESCqfsY_gxLyQgVVkpVFCawc2VE(UserSelectorBottomSheet r0) {
        r0.lambda$new$7();
    }

    public static /* synthetic */ void $r8$lambda$Hcq12wWcMZGTerxWxtFYqxQ3z2Y(UserSelectorBottomSheet r0) {
        r0.lambda$new$9();
    }

    public static /* synthetic */ void $r8$lambda$PAwoXsrNlhKhovpslkkGeMLb3ok(UserSelectorBottomSheet r0) {
        r0.lambda$didReceivedNotification$24();
    }

    public static /* synthetic */ void $r8$lambda$Pk6WehkoI39GsbSL3sABFbAI2JM(UserSelectorBottomSheet r0, TLObject r1, TLRPC.UserFull r2, TL_account.TL_birthday r3, TLRPC.TL_error r4) {
        r0.lambda$openBirthdaySetup$26(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$RW1ou2t6-Uln9UUiTyDGh8o6tQ0, reason: not valid java name */
    public static /* synthetic */ boolean m12137$r8$lambda$RW1ou2t6Uln9UUiTyDGh8o6tQ0(UserSelectorBottomSheet r0, int r1, View r2, int r3, float r4, float r5) {
        return r0.lambda$new$11(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$UKi2WP_XUgAsy2bBFJcqcE-fu8c, reason: not valid java name */
    public static /* synthetic */ void m12138$r8$lambda$UKi2WP_XUgAsy2bBFJcqcEfu8c(UserSelectorBottomSheet r0) {
        r0.lambda$new$12();
    }

    public static /* synthetic */ void $r8$lambda$UTWC_KMZm_UmKKprZAskAcMq9bw(UserSelectorBottomSheet r0, long r1, View r3) {
        r0.lambda$decorate$14(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$VbCHO9Y79MedoyO0ngHnBp3i3Kk(UserSelectorBottomSheet r0, TL_account.TL_birthday r1) {
        r0.lambda$openBirthdaySetup$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$WSt4E616AteUvKCMqblD5DpMorE(UserSelectorBottomSheet r0) {
        r0.lambda$new$6();
    }

    public static /* synthetic */ void $r8$lambda$cSyYMoEucxZvJuoJHuU3rgWHA1Q(UserSelectorBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$search$1(r1, r2);
    }

    /* renamed from: $r8$lambda$cUE1-74fY15e8Zi6HrLdfbIqgBg, reason: not valid java name */
    public static /* synthetic */ void m12139$r8$lambda$cUE174fY15e8Zi6HrLdfbIqgBg(UserSelectorBottomSheet r0, String r1) {
        r0.onSearch(r1);
    }

    public static /* synthetic */ void $r8$lambda$dPR5tTPBBYVKXt6d68V8fRbUln4(UserSelectorBottomSheet r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$fhDCsDNAGnYwnkiMsXKDsUCcL3k(UserSelectorBottomSheet r0, TLRPC.User r1, View r2) {
        r0.lambda$openOptions$22(r1, r2);
    }

    /* renamed from: $r8$lambda$j-88E1hjHfFbGXL8hGewCopMikU, reason: not valid java name */
    public static /* synthetic */ void m12140$r8$lambda$j88E1hjHfFbGXL8hGewCopMikU(UserSelectorBottomSheet r0, TLRPC.User r1) {
        r0.lambda$openOptions$20(r1);
    }

    /* renamed from: $r8$lambda$k9I-Z5kV81w-XbYVv7WYIOGEWSw, reason: not valid java name */
    public static /* synthetic */ void m12141$r8$lambda$k9IZ5kV81wXbYVv7WYIOGEWSw(UserSelectorBottomSheet r0) {
        r0.lambda$checkEditTextHint$2();
    }

    public static /* synthetic */ void $r8$lambda$ojAiXFfcvVTh_m44DeSeF0EQoSI(UserSelectorBottomSheet r0, View r1) {
        r0.lambda$new$4(r1);
    }

    /* renamed from: $r8$lambda$qQOSVr0Zv7r5FP3rkILFwVlt-ms, reason: not valid java name */
    public static /* synthetic */ void m12142$r8$lambda$qQOSVr0Zv7r5FP3rkILFwVltms(UserSelectorBottomSheet r0) {
        r0.lambda$new$10();
    }

    public static /* synthetic */ void $r8$lambda$qvBcQBvk3xl7zReMNg5ypqp2p8k(UserSelectorBottomSheet r0) {
        r0.lambda$didReceivedNotification$23();
    }

    public static /* synthetic */ void $r8$lambda$tMZdceJKEhy2qEwe_QpQ7Zba2QY(UserSelectorBottomSheet r0, TLRPC.User r1) {
        r0.lambda$openOptions$21(r1);
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12143$$Nest$fgetitems(UserSelectorBottomSheet r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlistPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12144$$Nest$fgetlistPaddingTop(UserSelectorBottomSheet r0) {
        return r0.listPaddingTop;
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m12145$$Nest$fgetquery(UserSelectorBottomSheet r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fgetrecipientsBtnExtraSpace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12146$$Nest$fgetrecipientsBtnExtraSpace(UserSelectorBottomSheet r0) {
        return r0.recipientsBtnExtraSpace;
    }

    /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectorSearchCell m12147$$Nest$fgetsearchField(UserSelectorBottomSheet r0) {
        return r0.searchField;
    }

    /* renamed from: -$$Nest$fgetselectorAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectorAdapter m12148$$Nest$fgetselectorAdapter(UserSelectorBottomSheet r0) {
        return r0.selectorAdapter;
    }

    /* renamed from: -$$Nest$fputlistPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12149$$Nest$fputlistPaddingTop(UserSelectorBottomSheet r0, int r1) {
        r0.listPaddingTop = r1;
    }

    /* renamed from: -$$Nest$fputrecipientsBtnExtraSpace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12150$$Nest$fputrecipientsBtnExtraSpace(UserSelectorBottomSheet r0, float r1) {
        r0.recipientsBtnExtraSpace = r1;
    }

    /* renamed from: -$$Nest$mcreateRecipientsBtnSpaceSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12151$$Nest$mcreateRecipientsBtnSpaceSpan(UserSelectorBottomSheet r0) {
        r0.createRecipientsBtnSpaceSpan();
    }

    /* renamed from: -$$Nest$msearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12152$$Nest$msearch(UserSelectorBottomSheet r0, String r1) {
        r0.search(r1);
    }

    /* renamed from: -$$Nest$mupdateActionButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12153$$Nest$mupdateActionButton(UserSelectorBottomSheet r0, boolean r1) {
        r0.updateActionButton(r1);
    }

    public UserSelectorBottomSheet(Context r27, int r28, long r29, BirthdayController.BirthdayState r31, int r32, boolean r33, Theme.ResourcesProvider r34) {
        super(r27, null, r33, false, false, r34);
        this.oldItems = new ArrayList();
        ArrayList<SelectorAdapter.Item> r2 = new ArrayList();
        this.items = r2;
        HashSet<Long> r3 = new HashSet();
        this.selectedIds = r3;
        this.contacts = new ArrayList();
        this.hints = new ArrayList();
        this.searchResult = new ArrayList();
        this.contactsMap = new HashMap();
        this.contactsLetters = new ArrayList();
        LinkedHashMap r4 = new LinkedHashMap();
        this.allSelectedObjects = r4;
        this.listPaddingTop = AndroidUtilities.dp(120.0f);
        this.paymentOptions = new ArrayList();
        this.isHintSearchText = false;
        this.remoteSearchRunnable = new AnonymousClass2(this);
        this.runningRequest = -1;
        this.excludeUserIds = new HashSet();
        this.currentAccount = r28;
        int r12 = Theme.key_dialogBackground;
        fixNavigationBar(Theme.getColor(r12, r34));
        this.drawDoubleNavigationBar = false;
        this.type = r32;
        this.birthdays = r31;
        SelectorAdapter r13 = this.selectorAdapter;
        if (r13 == null) goto L5;
        r13.setNeedChecks2(needChecks());
    L5:
        this.userId = r29;
        if (r29 != 0) goto L8;
    L10:
        AnonymousClass4 r42 = new AnonymousClass4(this, getContext(), r34);
        this.headerView = r42;
        r42.setOnCloseClickListener(new UserSelectorBottomSheet$$ExternalSyntheticLambda3(this));
        r42.setText(getTitle());
        r42.setCloseImageVisible(false);
        r42.backDrawable.setRotation(0.0f, false);
        createRecipientsBtnSpaceSpan();
        AnonymousClass5 r8 = new AnonymousClass5(this, getContext(), r34, null);
        this.searchField = r8;
        r8.setBackgroundColor(getThemedColor(r12));
        r8.setOnSearchTextChange(new UserSelectorBottomSheet$$ExternalSyntheticLambda4(this));
        if (r3.isEmpty() == false) goto L19;
        if (r32 == 1) goto L19;
        if (r32 == 2) goto L19;
        if (r32 == 3) goto L19;
        if (r32 == 4) goto L19;
        int r132 = R.string.GiftPremiumUsersSearchHint;
    L20:
        r8.setHintText(LocaleController.getString(r132), false);
        AnonymousClass6 r133 = new AnonymousClass6(this, getContext());
        this.sectionCell = r133;
        ViewGroup r15 = this.containerView;
        int r11 = this.backgroundPaddingLeft;
        r15.addView(r42, 0, LayoutHelper.createFrameMarginPx(-1, -2.0f, 55, r11, 0, r11, 0));
        ViewGroup r112 = this.containerView;
        int r152 = this.backgroundPaddingLeft;
        r112.addView(r8, LayoutHelper.createFrameMarginPx(-1, -2.0f, 55, r152, 0, r152, 0));
        ViewGroup r113 = this.containerView;
        int r153 = this.backgroundPaddingLeft;
        r113.addView(r133, LayoutHelper.createFrameMarginPx(-1, 1.0f, 55, r153, 0, r153, 0));
        SelectorBtnCell r114 = new SelectorBtnCell(getContext(), r34, null);
        this.buttonContainer = r114;
        r114.setClickable(true);
        r114.setOrientation(1);
        r114.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r114.setBackgroundColor(Theme.getColor(r12, r34));
        if (r32 != 4) goto L23;
        LinearLayout r9 = new LinearLayout(r27);
        r9.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f));
        r9.setClipToPadding(false);
        r9.setOrientation(0);
        r9.setBackground(Theme.createRadSelectorDrawable(getThemedColor(Theme.key_listSelector), 6, 6));
        CheckBox2 r122 = new CheckBox2(r27, 24, r34);
        this.videoCheckbox = r122;
        r122.setColor(Theme.key_featuredStickers_addButton, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        this.videoCheckbox.setDrawUnchecked(true);
        this.videoCheckbox.setChecked(false, false);
        this.videoCheckbox.setDrawBackgroundAsArc(10);
        r9.addView(this.videoCheckbox, LayoutHelper.createLinear(26, 26, 16, 0, 0, 0, 0));
        TextView r123 = new TextView(r27);
        r123.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r123.setTextSize(1, 14.0f);
        r123.setText(LocaleController.getString(R.string.ConferenceCallWithVideo));
        r9.addView(r123, LayoutHelper.createLinear(-2, -2, 16, 9, 0, 0, 0));
        ScaleStateListAnimator.apply(r9, 0.025f, 1.5f);
        r9.setOnClickListener(new UserSelectorBottomSheet$$ExternalSyntheticLambda5(this));
        r114.addView(r9, LayoutHelper.createLinear(-2, -2, 17, 0, 0, 0, 8));
    L23:
        AnonymousClass7 r1 = new AnonymousClass7(this, getContext(), r34);
        this.actionButton = r1;
        if (r32 != 4) goto L26;
        float r92 = 0.0f;
        r114.setAlpha(0.0f);
        r114.setVisibility(8);
    L27:
        r1.setOnClickListener(new UserSelectorBottomSheet$$ExternalSyntheticLambda6(this));
        r114.addView(r1, LayoutHelper.createLinear(-1, 48, 87));
        if (r32 != 4) goto L30;
        ViewGroup r14 = this.containerView;
        int r124 = this.backgroundPaddingLeft;
        r14.addView(r114, LayoutHelper.createFrameMarginPx(-1, -2.0f, 87, r124, 0, r124, 0));
    L30:
        FrameLayout r16 = new FrameLayout(getContext());
        this.bulletinContainer = r16;
        ViewGroup r115 = this.containerView;
        int r125 = this.backgroundPaddingLeft;
        r115.addView(r16, LayoutHelper.createFrameMarginPx(-1, 300.0f, 87, r125, 0, r125, AndroidUtilities.dp(68.0f)));
        this.selectorAdapter.setData(r2, this.recyclerListView);
        RecyclerListView r17 = this.recyclerListView;
        int r22 = this.backgroundPaddingLeft;
        if (r32 == 1) goto L33;
        r92 = 60.0f;
    L33:
        r17.setPadding(r22, 0, r22, AndroidUtilities.dp(r92));
        this.recyclerListView.addOnScrollListener(new AnonymousClass8(this));
        this.recyclerListView.setOnItemClickListener(new UserSelectorBottomSheet$$ExternalSyntheticLambda7(this, r32, r34, r28));
        if (r32 != 4) goto L36;
        this.recyclerListView.setOnItemLongClickListener(new UserSelectorBottomSheet$$ExternalSyntheticLambda8(this, r32));
    L36:
        DefaultItemAnimator r18 = new DefaultItemAnimator();
        r18.setDurations(350);
        r18.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r18.setDelayAnimations(false);
        r18.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(r18);
        this.recyclerListView.addItemDecoration(new AnonymousClass9(this));
        r8.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r8.spansContainer.removeAllSpans(false);
        r8.updateSpans(false, r3, new UserSelectorBottomSheet$$ExternalSyntheticLambda9(this), null);
        r42.setText(getTitle());
        ActionBar r19 = this.actionBar;
        if (r19 == null) goto L39;
        r19.setTitle(getTitle());
    L39:
        updateActionButton(false);
        initContacts(false);
        initHints(false);
        updateList(false, true);
        if (r32 == 0) goto L42;
        if (r32 == 2) goto L42;
    L43:
        if (r32 == 0) goto L47;
        if (r32 == 2) goto L47;
        return;
    L47:
        StarsController.getInstance(r28).loadStarGifts();
        return;
    L42:
        BoostRepository.loadGiftOptions(r28, null, new UserSelectorBottomSheet$$ExternalSyntheticLambda10(this));
        goto L43
    L26:
        r92 = 0.0f;
    L19:
        r132 = R.string.Search;
        goto L20
    L8:
        if (r3.contains(Long.valueOf(r29)) == true) goto L10;
        TLRPC.User r82 = MessagesController.getInstance(r28).getUser(Long.valueOf(r29));
        r3.add(Long.valueOf(r82.id));
        r4.put(Long.valueOf(r82.id), r82);
        goto L10
    }

    public static /* synthetic */ int access$000(UserSelectorBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private int addSection(ArrayList<SelectorAdapter.Item> r9, CharSequence r10, ArrayList<TLRPC.User> r11, boolean r12) {
        int r0 = 0;
        if (r11.isEmpty() == false) goto L5;
        return 0;
    L5:
        ArrayList r122 = new ArrayList();
        int r1 = r11.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L22;
        TLRPC.User r3 = r11.get(r2);
        r2 = r2 + 1;
        TLRPC.User r32 = r3;
        if (r32 == null) goto L6;
        if (r32.bot == true) goto L6;
        if (UserObject.isService(r32.id) == true) goto L6;
        long r4 = r32.id;
        if (r4 == this.userId) goto L6;
        if (this.excludeUserIds.contains(Long.valueOf(r4)) == true) goto L6;
        this.selectedIds.contains(Long.valueOf(r32.id));
        r0 = r0 + AndroidUtilities.dp(56.0f);
        r122.add(decorate(SelectorAdapter.Item.asUser(r32, this.selectedIds.contains(Long.valueOf(r32.id)))));
        goto L6
    L22:
        if (r122.isEmpty() == false) goto L24;
        return r0;
    L24:
        int r02 = r0 + AndroidUtilities.dp(32.0f);
        r9.add(SelectorAdapter.Item.asTopSection(r10));
        r9.addAll(r122);
        return r02;
    }

    private void cancelSearch() {
        if (this.runningRequest < 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.runningRequest, true);
        this.runningRequest = -1;
        return;
    }

    private void checkEditTextHint() {
        if (this.selectedIds.isEmpty() == false) goto L18;
        int r0 = this.type;
        if (r0 == 1) goto L18;
        if (r0 == 2) goto L18;
        if (r0 == 3) goto L18;
        if (r0 == 4) goto L18;
        if (this.isHintSearchText == false) goto L21;
        this.isHintSearchText = false;
        AndroidUtilities.runOnUIThread(new UserSelectorBottomSheet$$ExternalSyntheticLambda18(this), 10);
        return;
    L21:
        return;
    L18:
        if (this.isHintSearchText == true) goto L22;
        this.isHintSearchText = true;
        AndroidUtilities.runOnUIThread(new UserSelectorBottomSheet$$ExternalSyntheticLambda17(this), 10);
        return;
    }

    private void clearSearchAfterSelect() {
        if (isSearching() == false) goto L6;
        this.query = null;
        this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
        updateItems(true, true);
        return;
    }

    private void createRecipientsBtnSpaceSpan() {
        this.recipientsBtnSpaceSpan = new AnonymousClass3(this);
    }

    private SelectorAdapter.Item decorate(SelectorAdapter.Item r5) {
        if (this.type != 4) goto L10;
        TLRPC.User r0 = r5.user;
        if (r0 != null) goto L7;
        return r5;
    L7:
        long r02 = r0.id;
        return r5.withCall(new UserSelectorBottomSheet$$ExternalSyntheticLambda15(this, r02), new UserSelectorBottomSheet$$ExternalSyntheticLambda16(this, r02));
    L10:
        return r5.withOptions(openOptions(r5.user));
    }

    public static boolean handleIntent(Intent r2, Browser.Progress r3) {
        Uri r22 = r2.getData();
        if (r22 == null) goto L31;
        String r32 = r22.getScheme();
        if (r32 != null) goto L7;
        return false;
    L7:
        if (r32.equals("http") == false) goto L9;
    L19:
        String r33 = r22.getHost().toLowerCase();
        if (r33.equals("telegram.me") == false) goto L22;
    L25:
        String r23 = r22.getPath();
        if (r23 != null) goto L28;
        return false;
    L28:
        if (r23.startsWith("/premium_multigift") == false) goto L38;
        open();
        return true;
    L38:
        return false;
    L22:
        if (r33.equals("t.me") == true) goto L25;
        if (r33.equals("telegram.dog") == true) goto L25;
        return false;
    L9:
        if (r32.equals("https") == true) goto L19;
        if (r32.equals("tg") == false) goto L34;
        String r24 = r22.toString();
        if (r24.startsWith("tg:premium_multigift") == false) goto L16;
    L17:
        open();
        return true;
    L16:
        if (r24.startsWith("tg://premium_multigift") == true) goto L17;
        return false;
    L34:
        return false;
    L31:
        return false;
    }

    private void initContacts(boolean r3) {
        if (this.contacts.isEmpty() == false) goto L8;
        this.contacts.addAll(ContactsController.getInstance(this.currentAccount).contacts);
        this.contactsMap.putAll(ContactsController.getInstance(this.currentAccount).usersSectionsDict);
        this.contactsLetters.addAll(ContactsController.getInstance(this.currentAccount).sortedUsersSectionsArray);
        if (r3 == false) goto L9;
        updateItems(true, true);
        return;
    L9:
        return;
    }

    private void initHints(boolean r3) {
        if (this.hints.isEmpty() == false) goto L8;
        this.hints.addAll(MediaDataController.getInstance(this.currentAccount).hints);
        if (r3 == false) goto L9;
        updateItems(true, true);
        return;
    L9:
        return;
    }

    private boolean isSearching() {
        return !TextUtils.isEmpty(this.query);
    }

    private /* synthetic */ void lambda$checkEditTextHint$2() {
        this.searchField.setHintText(LocaleController.getString(R.string.Search), true);
    }

    private /* synthetic */ void lambda$checkEditTextHint$3() {
        this.searchField.setHintText(LocaleController.getString(R.string.GiftPremiumUsersSearchHint), true);
    }

    private /* synthetic */ void lambda$decorate$14(long r1, View r3) {
        this.selectedIds.add(Long.valueOf(r1));
        Utilities.Callback2<Boolean, HashSet<Long>> r12 = this.onUsersSelectedListener;
        if (r12 == null) goto L5;
        r12.run(Boolean.FALSE, this.selectedIds);
        this.onUsersSelectedListener = null;
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$decorate$15(long r1, View r3) {
        this.selectedIds.add(Long.valueOf(r1));
        Utilities.Callback2<Boolean, HashSet<Long>> r12 = this.onUsersSelectedListener;
        if (r12 == null) goto L5;
        r12.run(Boolean.TRUE, this.selectedIds);
        this.onUsersSelectedListener = null;
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$didReceivedNotification$23() {
        initContacts(true);
    }

    private /* synthetic */ void lambda$didReceivedNotification$24() {
        initHints(true);
    }

    private /* synthetic */ void lambda$didReceivedNotification$25() {
        updateItems(true, true);
    }

    private /* synthetic */ void lambda$new$10() {
        checkEditTextHint();
        updateList(true, false);
    }

    private /* synthetic */ boolean lambda$new$11(int r6, View r7, int r8, float r9, float r10) {
        if ((r7 instanceof SelectorUserCell) == false) goto L46;
        SelectorUserCell r72 = (SelectorUserCell) r7;
        TLRPC.User r82 = r72.getUser();
        TLRPC.Chat r73 = r72.getChat();
        if (r82 == null) goto L7;
        long r0 = r82.id;
    L9:
        if (r6 == 4) goto L11;
    L14:
        boolean r2 = true;
    L15:
        boolean r3 = this.selectedIds.contains(Long.valueOf(r0));
        HashSet<Long> r4 = this.selectedIds;
        if (r3 == false) goto L18;
        r4.remove(Long.valueOf(r0));
    L20:
        if (this.selectedIds.size() != (getLimit() + 1)) goto L23;
        this.selectedIds.remove(Long.valueOf(r0));
        showMaximumUsersToast();
        return true;
    L23:
        if (r6 == 4) goto L25;
    L28:
        boolean r62 = true;
    L30:
        if (r2 == r62) goto L44;
        this.buttonContainer.setVisibility(0);
        ViewPropertyAnimator r83 = this.buttonContainer.animate();
        float r92 = 0.0f;
        if (r62 == false) goto L34;
        float r02 = 1.0f;
    L35:
        ViewPropertyAnimator r84 = r83.alpha(r02);
        if (r62 == true) goto L39;
        r92 = AndroidUtilities.dp(12.0f);
    L39:
        ViewPropertyAnimator r85 = r84.translationY(r92).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320);
        if (r62 == true) goto L42;
        UserSelectorBottomSheet$$ExternalSyntheticLambda20 r93 = new UserSelectorBottomSheet$$ExternalSyntheticLambda20(this);
    L43:
        r85.withEndAction(r93).start();
        this.selectorAdapter.setCallButtonsVisible(!r62);
        goto L44
    L42:
        r93 = null;
        goto L43
    L34:
        r02 = 0.0f;
    L44:
        checkEditTextHint();
        this.searchField.updateSpans(true, this.selectedIds, new UserSelectorBottomSheet$$ExternalSyntheticLambda21(this), null);
        updateList(true, true);
        clearSearchAfterSelect();
        return true;
    L25:
        if (this.selectedIds.isEmpty() == false) goto L28;
        r62 = false;
        goto L30
    L18:
        r4.add(Long.valueOf(r0));
        this.allSelectedObjects.put(Long.valueOf(r0), r82);
        goto L20
    L11:
        if (this.selectedIds.isEmpty() == false) goto L14;
        r2 = false;
        goto L15
    L7:
        r0 = -r73.id;
        goto L9
    L46:
        return false;
    }

    private /* synthetic */ void lambda$new$12() {
        checkEditTextHint();
        updateList(true, false);
    }

    private /* synthetic */ void lambda$new$13(List r2) {
        this.paymentOptions.clear();
        this.paymentOptions.addAll(r2);
        if (this.actionButton.isLoading() == false) goto L8;
        this.actionButton.setLoading(false);
        if (this.recyclerListView.isAttachedToWindow() == false) goto L9;
        next();
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$4(View r2) {
        this.videoCheckbox.setChecked(!r1.isChecked(), true);
    }

    private /* synthetic */ void lambda$new$5(View r1) {
        next();
    }

    private /* synthetic */ void lambda$new$6() {
        this.buttonContainer.setVisibility(8);
    }

    private /* synthetic */ void lambda$new$7() {
        checkEditTextHint();
        updateList(true, false);
    }

    private /* synthetic */ void lambda$new$8(int r17, Theme.ResourcesProvider r18, int r19, View r20, int r21, float r22, float r23) {
        if ((r20 instanceof TextCell) == false) goto L12;
        if (r17 != 4) goto L9;
        Runnable r1 = this.onShareCallLinkListener;
        if (r1 == null) goto L111;
        r1.run();
        dismiss();
        return;
    L111:
        return;
    L9:
        openBirthdaySetup();
        return;
    L12:
        if ((r20 instanceof SelectorUserCell) == false) goto L112;
        SelectorUserCell r3 = (SelectorUserCell) r20;
        TLRPC.User r4 = r3.getUser();
        TLRPC.Chat r32 = r3.getChat();
        if (r4 != null) goto L21;
        if (r32 != null) goto L21;
        if (r17 != 3) goto L21;
        Utilities.Callback<Long> r0 = this.onUserSelectedListener;
        if (r0 == null) goto L113;
        r0.run(-99L);
        return;
    L113:
        return;
    L21:
        if (r4 != null) goto L24;
        if (r32 != null) goto L24;
        return;
    L24:
        if (r4 == null) goto L27;
        long r7 = r4.id;
    L26:
        long r12 = r7;
        if (r17 != 3) goto L33;
        Utilities.Callback<Long> r02 = this.onUserSelectedListener;
        if (r02 == null) goto L114;
        r02.run(Long.valueOf(r12));
        return;
    L114:
        return;
    L33:
        boolean r33 = true;
        if (r17 != 1) goto L44;
        SelectorSearchCell r13 = this.searchField;
        if (r13 == null) goto L38;
        AndroidUtilities.hideKeyboard(r13.getEditText());
    L38:
        StarsIntroActivity.GiftStarsSheet r14 = new StarsIntroActivity.GiftStarsSheet(getContext(), r18, r4, new UserSelectorBottomSheet$$ExternalSyntheticLambda3(this));
        if (AndroidUtilities.isTablet() == true) goto L41;
        r14.makeAttached(this.attachedFragment);
    L41:
        r14.show();
        return;
    L44:
        if (r17 == 0) goto L100;
        if (r17 == 2) goto L100;
        if (r17 == 4) goto L51;
    L63:
        if (r17 == 4) goto L65;
    L68:
        boolean r72 = true;
    L69:
        boolean r8 = this.selectedIds.contains(Long.valueOf(r12));
        HashSet<Long> r9 = this.selectedIds;
        if (r8 == false) goto L72;
        r9.remove(Long.valueOf(r12));
    L74:
        if (this.selectedIds.size() != (getLimit() + 1)) goto L77;
        this.selectedIds.remove(Long.valueOf(r12));
        showMaximumUsersToast();
        return;
    L77:
        if (r17 == 4) goto L79;
    L82:
        boolean r15 = true;
    L83:
        if (r72 == r15) goto L97;
        this.buttonContainer.setVisibility(0);
        ViewPropertyAnimator r42 = this.buttonContainer.animate();
        float r5 = 0.0f;
        if (r15 == false) goto L87;
        float r6 = 1.0f;
    L88:
        ViewPropertyAnimator r43 = r42.alpha(r6);
        if (r15 == true) goto L92;
        r5 = AndroidUtilities.dp(12.0f);
    L92:
        ViewPropertyAnimator r44 = r43.translationY(r5).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320);
        if (r15 == true) goto L95;
        UserSelectorBottomSheet$$ExternalSyntheticLambda13 r52 = new UserSelectorBottomSheet$$ExternalSyntheticLambda13(this);
    L96:
        r44.withEndAction(r52).start();
        this.selectorAdapter.setCallButtonsVisible(!r15);
        goto L97
    L95:
        r52 = null;
        goto L96
    L87:
        r6 = 0.0f;
    L97:
        checkEditTextHint();
        this.searchField.updateSpans(true, this.selectedIds, new UserSelectorBottomSheet$$ExternalSyntheticLambda14(this), null);
        updateList(true, true);
        clearSearchAfterSelect();
        return;
    L79:
        if (this.selectedIds.isEmpty() == false) goto L82;
        r15 = false;
        goto L83
    L72:
        r9.add(Long.valueOf(r12));
        this.allSelectedObjects.put(Long.valueOf(r12), r4);
        goto L74
    L65:
        if (this.selectedIds.isEmpty() == false) goto L68;
        r72 = false;
        goto L69
    L51:
        if (this.selectedIds.isEmpty() == false) goto L63;
        this.selectedIds.add(Long.valueOf(r12));
        Utilities.Callback2<Boolean, HashSet<Long>> r16 = this.onUsersSelectedListener;
        if (r16 == null) goto L61;
        CheckBox2 r45 = this.videoCheckbox;
        if (r45 != null) goto L57;
    L59:
        r33 = false;
    L60:
        r16.run(Boolean.valueOf(r33), this.selectedIds);
        this.onUsersSelectedListener = null;
        goto L61
    L57:
        if (r45.isChecked() == false) goto L59;
    L61:
        dismiss();
        return;
    L100:
        if (UserObject.areGiftsDisabled(r12) == false) goto L103;
        BulletinFactory.of(this.container, r18).createSimpleBulletin(R.raw.error, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserDisallowedGifts, new Object[]{DialogObject.getShortName(r12)}))).show();
        return;
    L103:
        GiftSheet r92 = new GiftSheet(getContext(), r19, r12, BoostRepository.filterGiftOptionsByBilling(BoostRepository.filterGiftOptions(this.paymentOptions, 1)), new UserSelectorBottomSheet$$ExternalSyntheticLambda12(this));
        BirthdayController.BirthdayState r03 = this.birthdays;
        if (r03 != null) goto L106;
    L108:
        r33 = false;
    L109:
        r92.setBirthday(r33).show();
        return;
    L106:
        if (r03.contains(r12) == false) goto L108;
    L27:
        r7 = -r32.id;
        goto L26
    }

    private /* synthetic */ void lambda$new$9() {
        this.buttonContainer.setVisibility(8);
    }

    private /* synthetic */ void lambda$openBirthdaySetup$26(TLObject r1, TLRPC.UserFull r2, TL_account.TL_birthday r3, TLRPC.TL_error r4) {
        if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.PrivacyBirthdaySetDone)).setDuration(5000).show();
        return;
    L6:
        if (r2 == null) goto L12;
        int r12 = r2.flags2;
        if (r3 != null) goto L10;
        r2.flags2 = r12 & (-33);
    L11:
        r2.birthday = r3;
        MessagesStorage.getInstance(this.currentAccount).updateUserInfo(r2, false);
        goto L12
    L10:
        r2.flags2 = r12 | 32;
    L12:
        if (r4 == null) goto L21;
        String r13 = r4.text;
        if (r13 == null) goto L21;
        if (r13.startsWith("FLOOD_WAIT_") == false) goto L21;
        if (getContext() == null) goto L23;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.PrivacyBirthdayTooOftenTitle)).setMessage(LocaleController.getString(R.string.PrivacyBirthdayTooOftenMessage)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L23:
        return;
    L21:
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
    }

    private /* synthetic */ void lambda$openBirthdaySetup$27(TLRPC.UserFull r7, TL_account.TL_birthday r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new UserSelectorBottomSheet$$ExternalSyntheticLambda30(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$openBirthdaySetup$28(TL_account.TL_birthday r6) {
        TL_account.updateBirthday r0 = new TL_account.updateBirthday();
        r0.flags |= 1;
        r0.birthday = r6;
        TLRPC.UserFull r1 = MessagesController.getInstance(this.currentAccount).getUserFull(UserConfig.getInstance(this.currentAccount).getClientUserId());
        if (r1 == null) goto L5;
        TL_account.TL_birthday r3 = r1.birthday;
    L6:
        if (r1 == null) goto L8;
        r1.flags2 |= 32;
        r1.birthday = r6;
    L8:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new UserSelectorBottomSheet$$ExternalSyntheticLambda29(this, r1, r3), 1024);
        MessagesController.getInstance(this.currentAccount).invalidateContentSettings();
        MessagesController.getInstance(this.currentAccount).removeSuggestion(0, "BIRTHDAY_SETUP");
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.newSuggestionsAvailable, new Object[0]);
        updateItems(true, true);
        return;
    L5:
        r3 = null;
        goto L6
    }

    private /* synthetic */ void lambda$openBirthdaySetup$29() {
        if (getBaseFragment() != null) goto L5;
        return;
    L5:
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        getBaseFragment().showAsSheet(new PrivacyControlActivity(11), r0);
    }

    private /* synthetic */ void lambda$openOptions$20(TLRPC.User r6) {
        if (r6 == null) goto L13;
        BaseFragment r5 = getBaseFragment();
        if (r5 != null) goto L11;
        BaseFragment r52 = LaunchActivity.getSafeLastFragment();
        BaseFragment.BottomSheetParams r1 = new BaseFragment.BottomSheetParams();
        r1.transitionFromLeft = true;
        r1.allowNestedScroll = false;
        if (r52 != null) goto L9;
        return;
    L9:
        Bundle r2 = new Bundle();
        r2.putLong("user_id", r6.id);
        r52.showAsSheet(new ChatActivity(r2), r1);
        return;
    L11:
        Bundle r12 = new Bundle();
        r12.putLong("user_id", r6.id);
        r5.presentFragment(new ChatActivity(r12));
        return;
    }

    private /* synthetic */ void lambda$openOptions$21(TLRPC.User r6) {
        if (r6 == null) goto L13;
        BaseFragment r5 = getBaseFragment();
        if (r5 != null) goto L11;
        BaseFragment r52 = LaunchActivity.getSafeLastFragment();
        if (r52 != null) goto L9;
        return;
    L9:
        BaseFragment.BottomSheetParams r1 = new BaseFragment.BottomSheetParams();
        r1.transitionFromLeft = true;
        r1.allowNestedScroll = false;
        Bundle r2 = new Bundle();
        r2.putLong("user_id", r6.id);
        r52.showAsSheet(new ProfileActivity(r2), r1);
        return;
    L11:
        Bundle r12 = new Bundle();
        r12.putLong("user_id", r6.id);
        r5.presentFragment(new ProfileActivity(r12));
        return;
    }

    private /* synthetic */ void lambda$openOptions$22(TLRPC.User r4, View r5) {
        ItemOptions.makeOptions(this.container, this.resourcesProvider, (View) r5.getParent()).add(R.drawable.msg_discuss, LocaleController.getString(R.string.SendMessage), new UserSelectorBottomSheet$$ExternalSyntheticLambda27(this, r4)).add(R.drawable.msg_openprofile, LocaleController.getString(R.string.OpenProfile), new UserSelectorBottomSheet$$ExternalSyntheticLambda28(this, r4)).show();
    }

    private /* synthetic */ void lambda$search$0(TLObject r11) {
        this.searchResult.clear();
        this.runningRequest = -1;
        if ((r11 instanceof TLRPC.TL_contacts_found) == false) goto L22;
        TLRPC.TL_contacts_found r112 = (TLRPC.TL_contacts_found) r11;
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        int r2 = 0;
        r0.putUsers(r112.users, false);
        r0.putChats(r112.chats, false);
        HashSet r1 = new HashSet();
        ArrayList<TLRPC.Peer> r3 = r112.my_results;
        int r4 = r3.size();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L13;
        TLRPC.Peer r6 = r3.get(r5);
        r5 = r5 + 1;
        long r62 = DialogObject.getPeerDialogId(r6);
        if (r1.contains(Long.valueOf(r62)) == true) goto L5;
        TLObject r8 = r0.getUserOrChat(r62);
        if (r8 == null) goto L5;
        this.searchResult.add(r8);
        r1.add(Long.valueOf(r62));
        goto L5
    L13:
        ArrayList<TLRPC.Peer> r113 = r112.results;
        int r32 = r113.size();
    L14:
        if (r2 >= r32) goto L22;
        TLRPC.Peer r42 = r113.get(r2);
        r2 = r2 + 1;
        long r43 = DialogObject.getPeerDialogId(r42);
        if (r1.contains(Long.valueOf(r43)) == true) goto L14;
        TLObject r63 = r0.getUserOrChat(r43);
        if (r63 == null) goto L14;
        this.searchResult.add(r63);
        r1.add(Long.valueOf(r43));
    L22:
        updateList(true, true);
    }

    private /* synthetic */ void lambda$search$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new UserSelectorBottomSheet$$ExternalSyntheticLambda26(this, r1));
    }

    private /* synthetic */ void lambda$updateItems$18() {
        checkEditTextHint();
        updateList(true, false);
    }

    private /* synthetic */ void lambda$updateItems$19(ArrayList r4, View r5) {
        int r52 = r4.size();
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L5;
        Object r1 = r4.get(r0);
        r0 = r0 + 1;
        Long r12 = (Long) r1;
        r12.longValue();
        this.selectedIds.remove(r12);
        this.allSelectedObjects.remove(r12);
        goto L3
    L5:
        checkEditTextHint();
        this.searchField.updateSpans(true, this.selectedIds, new UserSelectorBottomSheet$$ExternalSyntheticLambda19(this), null);
        updateList(true, true);
        clearSearchAfterSelect();
    }

    private void next() {
        if (this.selectedIds.size() != 0) goto L5;
        return;
    L5:
        if (this.paymentOptions.isEmpty() == false) goto L12;
        int r0 = this.type;
        if (r0 == 0) goto L12;
        if (r0 == 2) goto L12;
        if (r0 == 4) goto L12;
        return;
    L12:
        ArrayList r02 = new ArrayList();
        Iterator<TLRPC.User> r2 = this.allSelectedObjects.values().iterator();
    L14:
        if (r2.hasNext() == false) goto L18;
        TLRPC.User r3 = r2.next();
        if (this.selectedIds.contains(Long.valueOf(r3.id)) == false) goto L14;
        r02.add(r3);
        goto L14
    L18:
        AndroidUtilities.hideKeyboard(this.searchField.getEditText());
        int r22 = this.type;
        boolean r32 = true;
        if (r22 != 1) goto L22;
        return;
    L22:
        if (r22 != 4) goto L34;
        Utilities.Callback2<Boolean, HashSet<Long>> r03 = this.onUsersSelectedListener;
        if (r03 == null) goto L32;
        CheckBox2 r1 = this.videoCheckbox;
        if (r1 != null) goto L28;
    L30:
        r32 = false;
    L31:
        r03.run(Boolean.valueOf(r32), this.selectedIds);
        this.onUsersSelectedListener = null;
        goto L32
    L28:
        if (r1.isChecked() == false) goto L30;
    L32:
        dismiss();
        return;
    L34:
        List<TLRPC.TL_premiumGiftCodeOption> r10 = BoostRepository.filterGiftOptionsByBilling(BoostRepository.filterGiftOptions(this.paymentOptions, r02.size()));
        if (r02.size() != 1) goto L54;
        long r8 = ((TLRPC.User) r02.get(0)).id;
        if (UserObject.areGiftsDisabled(r8) == false) goto L40;
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletin(R.raw.error, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserDisallowedGifts, new Object[]{DialogObject.getShortName(r8)}))).show();
        return;
    L40:
        GiftSheet r5 = new GiftSheet(getContext(), this.currentAccount, r8, r10, new UserSelectorBottomSheet$$ExternalSyntheticLambda12(this));
        BirthdayController.BirthdayState r12 = this.birthdays;
        if (r12 != null) goto L43;
    L45:
        r32 = false;
    L46:
        r5.setBirthday(r32).show();
        return;
    L43:
        if (r12.contains(r8) == false) goto L45;
    }

    private void onSearch(String r3) {
        this.query = r3;
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
        AndroidUtilities.runOnUIThread(this.remoteSearchRunnable, 350);
    }

    public static UserSelectorBottomSheet open() {
        return open(0, null);
    }

    public static UserSelectorBottomSheet open(int r12, long r13, BirthdayController.BirthdayState r15) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L6;
        return null;
    L6:
        UserSelectorBottomSheet r1 = instance;
        if (r1 == null) goto L9;
        return r1;
    L9:
        AnonymousClass1 r2 = new AnonymousClass1(r0.getContext(), r0.getCurrentAccount(), r13, r15, r12, true, r0.getResourceProvider(), r12);
        if (AndroidUtilities.isTablet() == false) goto L12;
    L14:
        r0.showDialog(r2);
        instance = r2;
        return r2;
    L12:
        if (AndroidUtilities.hasDialogOnTop(r0) == true) goto L14;
        r2.makeAttached(r0);
        goto L14
    }

    public static UserSelectorBottomSheet open(long r1, BirthdayController.BirthdayState r3) {
        return open(0, r1, r3);
    }

    private void openBirthdaySetup() {
        AlertsCreator.createBirthdayPickerDialog(getContext(), LocaleController.getString(R.string.EditProfileBirthdayTitle), LocaleController.getString(R.string.EditProfileBirthdayButton), null, new UserSelectorBottomSheet$$ExternalSyntheticLambda23(this), new UserSelectorBottomSheet$$ExternalSyntheticLambda24(this), false, false, this.resourcesProvider).show();
    }

    private void search(String r3) {
        cancelSearch();
        TLRPC.TL_contacts_search r0 = new TLRPC.TL_contacts_search();
        r0.q = r3;
        this.runningRequest = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new UserSelectorBottomSheet$$ExternalSyntheticLambda25(this));
    }

    private void showMaximumUsersToast() {
        if (this.type != 4) goto L5;
        String r0 = LocaleController.formatPluralStringComma("UserSelectorLimit", getLimit());
    L6:
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletin(R.raw.chats_infotip, r0).show(true);
        this.container.performHapticFeedback(3, 2);     // Catch: Exception -> L9
        return;
    L12:
        return;
    L5:
        r0 = LocaleController.getString(R.string.BoostingSelectUpToWarningUsers);
        goto L6
    }

    private void updateActionButton(boolean r9) {
        boolean r1 = false;
        this.actionButton.setShowZero(false);
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        if (this.type != 4) goto L6;
        r0.append(LocaleController.getString(R.string.CallInviteMembersButton));
    L12:
        this.actionButton.setCount(this.selectedIds.size(), true);
        this.actionButton.setText(r0, r9, false);
        ButtonWithCounterView r92 = this.actionButton;
        if (this.selectedIds.size() <= 0) goto L15;
        r1 = true;
    L15:
        r92.setEnabled(r1);
        return;
    L6:
        if (this.selectedIds.size() == 0) goto L8;
        r0.append(LocaleController.getString("GiftPremiumProceedBtn", R.string.GiftPremiumProceedBtn));
        goto L12
    L8:
        if (LocaleController.isRTL == false) goto L10;
        r0.append(LocaleController.getString("GiftPremiumChooseRecipientsBtn", R.string.GiftPremiumChooseRecipientsBtn));
        r0.append("d").setSpan(this.recipientsBtnSpaceSpan, r0.length() - 1, r0.length(), 33);
        goto L12
    L10:
        r0.append("d").setSpan(this.recipientsBtnSpaceSpan, 0, 1, 33);
        r0.append(LocaleController.getString("GiftPremiumChooseRecipientsBtn", R.string.GiftPremiumChooseRecipientsBtn));
        goto L12
    }

    private void updateCheckboxes(boolean r10) {
        int r3 = -1;
        int r2 = 0;
        int r4 = 0;
    L4:
        if (r2 >= this.recyclerListView.getChildCount()) goto L23;
        View r5 = this.recyclerListView.getChildAt(r2);
        if ((r5 instanceof SelectorUserCell) == false) goto L22;
        int r6 = this.recyclerListView.getChildAdapterPosition(r5);
        int r7 = r6 - 1;
        if (r7 < 0) goto L22;
        if (r7 >= this.items.size()) goto L22;
        if (r3 != (-1)) goto L14;
        r3 = r6;
    L14:
        SelectorAdapter.Item r42 = this.items.get(r7);
        SelectorUserCell r52 = (SelectorUserCell) r5;
        r52.setChecked(r42.checked, r10);
        TLRPC.Chat r43 = r42.chat;
        float r72 = 1.0f;
        if (r43 != null) goto L17;
        r52.setCheckboxAlpha(1.0f, r10);
    L21:
        r4 = r6;
        goto L22
    L17:
        if (this.selectorAdapter.getParticipantsCount(r43) <= 200) goto L19;
        r72 = 0.3f;
    L19:
        r52.setCheckboxAlpha(r72, r10);
    L22:
        r2 = r2 + 1;
        goto L4
    L23:
        if (r10 == false) goto L31;
        this.selectorAdapter.notifyItemRangeChanged(0, r3);
        SelectorAdapter r9 = this.selectorAdapter;
        r9.notifyItemRangeChanged(r4, r9.getItemCount() - r4);
        return;
    }

    private void updateList(boolean r1, boolean r2) {
        updateItems(r1, r2);
        updateCheckboxes(r1);
        updateActionButton(r1);
    }

    public void addTONOption(int r2) {
        this.includeTonOption = true;
        this.tonDays = r2;
        updateItems(false, true);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r4) {
        SelectorAdapter r42 = new SelectorAdapter(getContext(), false, this.resourcesProvider);
        this.selectorAdapter = r42;
        r42.setGreenSelector(true);
        return this.selectorAdapter;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.giftsToUserSent) goto L7;
        dismiss();
        return;
    L7:
        if (r1 != NotificationCenter.contactsDidLoad) goto L11;
        AndroidUtilities.runOnUIThread(new UserSelectorBottomSheet$$ExternalSyntheticLambda0(this));
        return;
    L11:
        if (r1 != NotificationCenter.reloadHints) goto L15;
        AndroidUtilities.runOnUIThread(new UserSelectorBottomSheet$$ExternalSyntheticLambda1(this));
        return;
    L15:
        if (r1 != NotificationCenter.userInfoDidLoad) goto L18;
        AndroidUtilities.runOnUIThread(new UserSelectorBottomSheet$$ExternalSyntheticLambda2(this));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        AndroidUtilities.hideKeyboard(this.searchField.getEditText());
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        instance = null;
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
    }

    public UserSelectorBottomSheet exceptUsers(Collection<Long> r2) {
        this.excludeUserIds.addAll(r2);
        updateItems(false, true);
        return this;
    }

    public UserSelectorBottomSheet exceptUsers(long... r7) {
        int r0 = r7.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.excludeUserIds.add(Long.valueOf(r7[r2]));
        r2 = r2 + 1;
        goto L3
    L5:
        updateItems(false, true);
        return this;
    }

    public int getLimit() {
        if (this.type == 4) goto L5;
        return 10;
    L5:
        return Math.max(0, (MessagesController.getInstance(this.currentAccount).conferenceCallSizeLimit - this.excludeUserIds.size()) - 1);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        String r0 = this.customTitle;
        if (r0 == null) goto L5;
        return r0;
    L5:
        int r1 = this.type;
        if (r1 == 0) goto L20;
        if (r1 == 1) goto L18;
        if (r1 == 2) goto L20;
        if (r1 == 4) goto L16;
        return LocaleController.getString(R.string.GiftTelegramPremiumTitle);
    L16:
        return LocaleController.getString(R.string.VoipConferenceAddPeople);
    L18:
        return LocaleController.getString(R.string.GiftStarsTitle);
    L20:
        return LocaleController.getString(R.string.GiftTelegramPremiumOrStarsTitle);
    }

    public boolean needChecks() {
        if (this.type != 4) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.giftsToUserSent);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.userInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.reloadHints);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        updateItems(false, true);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.giftsToUserSent);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.userInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.reloadHints);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onPreDraw(Canvas r2, int r3, float r4) {
        this.headerView.setTranslationY(Math.max(r3, AndroidUtilities.statusBarHeight - AndroidUtilities.dp(8.0f)) + AndroidUtilities.dp(8.0f));
        this.searchField.setTranslationY(this.headerView.getTranslationY() + this.headerView.getMeasuredHeight());
        this.sectionCell.setTranslationY(this.searchField.getTranslationY() + this.searchField.getMeasuredHeight());
        this.recyclerListView.setTranslationY(((this.headerView.getMeasuredHeight() + this.searchField.getMeasuredHeight()) + this.sectionCell.getMeasuredHeight()) - AndroidUtilities.dp(8.0f));
    }

    public View.OnClickListener openOptions(TLRPC.User r3) {
        if (this.type != 3) goto L7;
        return null;
    L7:
        return new UserSelectorBottomSheet$$ExternalSyntheticLambda22(this, r3);
    }

    public void scrollToTop(boolean r4) {
        if (r4 == false) goto L5;
        LinearSmoothScrollerCustom r42 = new LinearSmoothScrollerCustom(getContext(), 2, 0.6f);
        r42.setTargetPosition(1);
        r42.setOffset(AndroidUtilities.dp(36.0f));
        this.recyclerListView.getLayoutManager().startSmoothScroll(r42);
        return;
    L5:
        this.recyclerListView.scrollToPosition(0);
    }

    public UserSelectorBottomSheet setOnShareCallLinkListener(Runnable r2) {
        this.onShareCallLinkListener = r2;
        updateItems(false, true);
        return this;
    }

    public void setOnUserSelector(Utilities.Callback<Long> r1) {
        this.onUserSelectedListener = r1;
    }

    public UserSelectorBottomSheet setOnUsersSelector(Utilities.Callback2<Boolean, HashSet<Long>> r1) {
        this.onUsersSelectedListener = r1;
        return this;
    }

    public void setTitle(String r2) {
        this.customTitle = r2;
        ActionBar r22 = this.actionBar;
        if (r22 == null) goto L5;
        r22.setTitle(getTitle());
    L5:
        SelectorHeaderCell r23 = this.headerView;
        if (r23 == null) goto L9;
        r23.setText(getTitle());
        return;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void updateItems(boolean r17, boolean r18) {
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        if (isSearching() == false) goto L30;
        ArrayList<TLObject> r1 = this.searchResult;
        int r5 = r1.size();
        int r6 = 0;
        int r7 = 0;
    L5:
        if (r7 >= r5) goto L140;
        TLObject r8 = r1.get(r7);
        r7 = r7 + 1;
        TLObject r82 = r8;
        if ((r82 instanceof TLRPC.User) == true) goto L8;
        if ((r82 instanceof TLRPC.Chat) == false) goto L5;
        TLRPC.Chat r83 = (TLRPC.Chat) r82;
        if (this.type != 3) goto L5;
        if (ChatObject.isChannelAndNotMegaGroup(r83) == false) goto L5;
        long r9 = -r83.id;
        r6 = r6 + AndroidUtilities.dp(56.0f);
        if (this.excludeUserIds.contains(Long.valueOf(-r83.id)) == true) goto L5;
        this.items.add(SelectorAdapter.Item.asChat(r83, this.selectedIds.contains(Long.valueOf(r9))));
        goto L5
    L8:
        TLRPC.User r84 = (TLRPC.User) r82;
        if (r84.bot == true) goto L5;
        if (UserObject.isService(r84.id) == true) goto L5;
        long r92 = r84.id;
        r6 = r6 + AndroidUtilities.dp(56.0f);
        if (this.excludeUserIds.contains(Long.valueOf(r84.id)) == true) goto L5;
        this.items.add(decorate(SelectorAdapter.Item.asUser(r84, this.selectedIds.contains(Long.valueOf(r92)))));
    L140:
        if (this.items.isEmpty() == false) goto L142;
        this.items.add(SelectorAdapter.Item.asNoUsers());
        r6 = r6 + AndroidUtilities.dp(150.0f);
    L142:
        this.items.add(SelectorAdapter.Item.asPad(Math.max(0, ((int) (AndroidUtilities.displaySize.y * 0.6f)) - r6)));
        if (r18 == false) goto L214;
        SelectorAdapter r12 = this.selectorAdapter;
        if (r12 == null) goto L215;
        if (r17 == false) goto L149;
        r12.setItems(this.oldItems, this.items);
        return;
    L149:
        r12.notifyDataSetChanged();
        return;
    L215:
        return;
    L214:
        return;
    L30:
        if (this.includeTonOption == true) goto L32;
    L41:
        TLRPC.UserFull r13 = MessagesController.getInstance(this.currentAccount).getUserFull(UserConfig.getInstance(this.currentAccount).getClientUserId());
        if (r13 != null) goto L44;
        MessagesController.getInstance(this.currentAccount).loadFullUser(UserConfig.getInstance(this.currentAccount).getCurrentUser(), 0, true);
    L44:
        int r72 = this.type;
        if (r72 == 0) goto L47;
        if (r72 == 2) goto L47;
    L51:
        int r14 = 0;
    L53:
        if (this.onShareCallLinkListener == null) goto L58;
        if (this.type != 4) goto L58;
        this.items.add(SelectorAdapter.Item.asButton(3, R.drawable.msg2_link2, LocaleController.getString(R.string.VoipConferenceShareLink)));
    L58:
        if (this.birthdays == null) goto L60;
        r14 = ((r14 + addSection(this.items, LocaleController.getString(R.string.BirthdayToday), this.birthdays.today, true)) + addSection(this.items, LocaleController.getString(R.string.BirthdayYesterday), this.birthdays.yesterday, true)) + addSection(this.items, LocaleController.getString(R.string.BirthdayTomorrow), this.birthdays.tomorrow, true);
    L60:
        int r2 = this.type;
        if (r2 == 0) goto L63;
        if (r2 == 2) goto L63;
    L66:
        ArrayList r22 = new ArrayList();
        SelectorAdapter.Item r73 = null;
        if (this.hints.isEmpty() == true) goto L99;
        ArrayList r52 = new ArrayList();
        Iterator<TLRPC.TL_topPeer> r85 = this.hints.iterator();
    L70:
        if (r85.hasNext() == false) goto L97;
        TLRPC.User r93 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r85.next().peer.user_id));
        if (r93 == null) goto L70;
        long r10 = r93.id;
        if (r10 == this.userId) goto L70;
        if (r93.self == true) goto L70;
        if (r93.bot == true) goto L70;
        if (UserObject.isService(r10) == true) goto L70;
        if (UserObject.isDeleted(r93) == true) goto L70;
        BirthdayController.BirthdayState r102 = this.birthdays;
        if (r102 == null) goto L90;
        if (r102.contains(r93.id) == true) goto L70;
    L90:
        if (this.excludeUserIds.contains(Long.valueOf(r93.id)) == true) goto L70;
        if (this.selectedIds.contains(Long.valueOf(r93.id)) == false) goto L95;
        r22.add(Long.valueOf(r93.id));
    L95:
        r14 = r14 + AndroidUtilities.dp(56.0f);
        r52.add(decorate(SelectorAdapter.Item.asUser(r93, this.selectedIds.contains(Long.valueOf(r93.id)))));
        goto L70
    L97:
        if (r52.isEmpty() == true) goto L99;
        r14 = r14 + AndroidUtilities.dp(32.0f);
        r73 = SelectorAdapter.Item.asTopSection(LocaleController.getString(R.string.GiftPremiumFrequentContacts));
        this.items.add(r73);
        this.items.addAll(r52);
    L99:
        Iterator<String> r53 = this.contactsLetters.iterator();
    L101:
        if (r53.hasNext() == false) goto L132;
        String r86 = r53.next();
        ArrayList r94 = new ArrayList();
        Iterator<TLRPC.TL_contact> r103 = this.contactsMap.get(r86).iterator();
    L104:
        if (r103.hasNext() == false) goto L130;
        TLRPC.TL_contact r11 = r103.next();
        long r122 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        long r142 = r11.user_id;
        if (r142 == r122) goto L104;
        if (r142 == this.userId) goto L104;
        BirthdayController.BirthdayState r123 = this.birthdays;
        if (r123 == null) goto L116;
        if (r123.contains(r142) == true) goto L104;
    L116:
        if (this.excludeUserIds.contains(Long.valueOf(r11.user_id)) == true) goto L104;
        TLRPC.User r112 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r11.user_id));
        if (r112 == null) goto L104;
        if (r112.bot == true) goto L104;
        if (UserObject.isService(r112.id) == true) goto L104;
        r14 = r14 + AndroidUtilities.dp(56.0f);
        if (this.selectedIds.contains(Long.valueOf(r112.id)) == false) goto L128;
        r22.add(Long.valueOf(r112.id));
    L128:
        r94.add(decorate(SelectorAdapter.Item.asUser(r112, this.selectedIds.contains(Long.valueOf(r112.id)))));
        goto L104
    L130:
        if (r94.isEmpty() == true) goto L101;
        r14 = r14 + AndroidUtilities.dp(32.0f);
        this.items.add(SelectorAdapter.Item.asLetter(r86.toUpperCase()));
        this.items.addAll(r94);
        goto L101
    L132:
        if (r73 != null) goto L134;
    L138:
        r6 = r14;
        goto L140
    L134:
        if (r22.size() <= 0) goto L138;
        if (this.selectedIds.isEmpty() == true) goto L138;
        r73.withRightText(LocaleController.getString(R.string.DeselectAll), new UserSelectorBottomSheet$$ExternalSyntheticLambda11(this, r22));
    L63:
        TLRPC.User r23 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (r23 == null) goto L66;
        this.items.add(SelectorAdapter.Item.asTopSection(LocaleController.getString(R.string.Gift2MyselfSection)));
        SelectorAdapter.Item r24 = SelectorAdapter.Item.asUser(r23, this.selectedIds.contains(Long.valueOf(r23.id)));
        r24.subtext = LocaleController.getString(R.string.Gift2Myself);
        this.items.add(r24);
    L47:
        if (r13 == null) goto L51;
        if (r13.birthday != null) goto L51;
        r14 = AndroidUtilities.dp(50.0f);
        this.items.add(SelectorAdapter.Item.asButton(1, R.drawable.menu_birthday, LocaleController.getString(R.string.GiftsBirthdaySetup)));
        goto L53
    L32:
        if (this.type != 3) goto L41;
        if (this.tonIcon != null) goto L36;
        CombinedDrawable r15 = new CombinedDrawable(Theme.createCircleDrawable(AndroidUtilities.dp(46.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)), getContext().getResources().getDrawable(R.drawable.mini_gram_72).mutate());
        r15.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.tonIcon = r15;
    L36:
        ArrayList<SelectorAdapter.Item> r16 = this.items;
        Drawable r62 = this.tonIcon;
        String r74 = LocaleController.getString(R.string.Gift2ExportTONTitle);
        int r87 = this.tonDays;
        if (r87 <= 0) goto L39;
        String r88 = LocaleController.formatPluralString("Gift2ExportTONUnlocksIn", r87, new Object[0]);
    L40:
        r16.add(SelectorAdapter.Item.asCustomUser(2, r62, r74, r88));
        goto L41
    L39:
        r88 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L40
    }
}
