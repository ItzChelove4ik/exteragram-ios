package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.LongSparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes6.dex */
public class ChatbotsActivity extends BaseFragment {
    private static final int BUTTON_DELETE = 0;
    private static final int PERMISSION_GIFTS = 0;
    private static final int PERMISSION_GIFTS_SELL = 0;
    private static final int PERMISSION_GIFTS_SETTINGS = 0;
    private static final int PERMISSION_GIFTS_TRANSFER = 0;
    private static final int PERMISSION_GIFTS_TRANSFER_STARS = 0;
    private static final int PERMISSION_GIFTS_VIEW = 0;
    private static final int PERMISSION_MESSAGES = 0;
    private static final int PERMISSION_MESSAGES_DELETE_RECEIVED = 0;
    private static final int PERMISSION_MESSAGES_DELETE_SENT = 0;
    private static final int PERMISSION_MESSAGES_MARK_AS_READ = 0;
    private static final int PERMISSION_MESSAGES_READ = 0;
    private static final int PERMISSION_MESSAGES_REPLY = 0;
    private static final int PERMISSION_PROFILE = 0;
    private static final int PERMISSION_PROFILE_BIO = 0;
    private static final int PERMISSION_PROFILE_NAME = 0;
    private static final int PERMISSION_PROFILE_PICTURE = 0;
    private static final int PERMISSION_PROFILE_USERNAME = 0;
    private static final int PERMISSION_STORIES = 0;
    private static final int RADIO_EXCLUDE = 0;
    private static final int RADIO_INCLUDE = 0;
    private static int ids;
    public TL_account.TL_connectedBot currentBot;
    public TL_account.connectedBots currentValue;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private EditTextBoldCursor editText;
    private FrameLayout editTextContainer;
    private View editTextDivider;
    private FrameLayout emptyView;
    private ImageView emptyViewLoading;
    private TextView emptyViewText;
    public boolean exclude;
    private boolean expandedGiftsSection;
    private boolean expandedMessagesSection;
    private boolean expandedProfileSection;
    private LongSparseArray<TLRPC.User> foundBots;
    private String lastQuery;
    private UniversalRecyclerView listView;
    private boolean loading;
    private BusinessRecipientsHelper recipientsHelper;
    public TL_account.TL_businessBotRights rights;
    private boolean scheduledLoading;
    private Runnable search;
    private SearchAdapterHelper searchHelper;
    private int searchId;
    private TLRPC.User selectedBot;
    private int shakeDp;
    private boolean shownGiftsPermissionsAlert;
    private boolean shownUsernamePermissionsAlert;
    private boolean valueSet;
    private boolean wasLoading;

    /* renamed from: org.telegram.ui.Business.ChatbotsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatbotsActivity this$0;

        public AnonymousClass1(ChatbotsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            ChatbotsActivity.m6362$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Business.ChatbotsActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ ChatbotsActivity this$0;

        public AnonymousClass2(ChatbotsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            ChatbotsActivity.m6363$$Nest$mscheduleSearch(this.this$0);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Business.ChatbotsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ ChatbotsActivity this$0;

        public AnonymousClass3(ChatbotsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Business.ChatbotsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends CircularProgressDrawable {
        final /* synthetic */ ChatbotsActivity this$0;

        public AnonymousClass4(ChatbotsActivity r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.CircularProgressDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return (int) (this.size + (this.thickness * 2.0f));
        }

        @Override // org.telegram.ui.Components.CircularProgressDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return (int) (this.size + (this.thickness * 2.0f));
        }
    }

    /* renamed from: org.telegram.ui.Business.ChatbotsActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements SearchAdapterHelper.SearchAdapterHelperDelegate {
        final /* synthetic */ ChatbotsActivity this$0;

        /* renamed from: $r8$lambda$UzqSUs-KJBsediXvn2cvzHRtOBI, reason: not valid java name */
        public static /* synthetic */ void m6365$r8$lambda$UzqSUsKJBsediXvn2cvzHRtOBI(AnonymousClass5 r0) {
            r0.lambda$onDataSetChanged$0();
        }

        public AnonymousClass5(ChatbotsActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onDataSetChanged$0() {
            ChatbotsActivity.m6361$$Nest$fgetlistView(this.this$0).adapter.update(true);
            ChatbotsActivity.m6364$$Nest$mupdateSearchLoading(this.this$0);
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public void onDataSetChanged(int r1) {
            AndroidUtilities.runOnUIThread(new ChatbotsActivity$5$$ExternalSyntheticLambda0(this));
        }
    }

    public static /* synthetic */ void $r8$lambda$4F4Ucfg7a3W_o4htdK_8i7KEUF0(ChatbotsActivity r0, View r1) {
        r0.lambda$onClick$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$61Xz8QbzUhD5IyY5WtZF83c0gH8(ChatbotsActivity r0, View r1) {
        r0.lambda$fillItems$10(r1);
    }

    /* renamed from: $r8$lambda$8J6wc-ISWTecE2_3SGWVESrGGYg, reason: not valid java name */
    public static /* synthetic */ void m6349$r8$lambda$8J6wcISWTecE2_3SGWVESrGGYg(ChatbotsActivity r0, View r1) {
        r0.lambda$onClick$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$ByccFaO8uO43vhAnQ3bbFkz67_4(ChatbotsActivity r0, int[] r1, ArrayList r2, boolean r3, TLRPC.User r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$processDone$20(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$FlQrlXeDM-nY8xeXe6KTGDvf8KM, reason: not valid java name */
    public static /* synthetic */ void m6350$r8$lambda$FlQrlXeDMnY8xeXe6KTGDvf8KM(ChatbotsActivity r0, View r1) {
        r0.lambda$onClick$12(r1);
    }

    /* renamed from: $r8$lambda$G4Y5sUK8jU-IWMnYFntn4aaVIOA, reason: not valid java name */
    public static /* synthetic */ void m6351$r8$lambda$G4Y5sUK8jUIWMnYFntn4aaVIOA(ChatbotsActivity r0, Runnable r1, AlertDialog r2, int r3) {
        r0.lambda$checkAlert$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$IHJXmzECItjNMDn2MPPDj9zsAR4(ChatbotsActivity r0) {
        r0.lambda$createView$1();
    }

    public static /* synthetic */ void $r8$lambda$KRwXkoABAhxSlBNqNeMD0Na1bO4(ChatbotsActivity r0, TLObject r1) {
        r0.lambda$processDone$18(r1);
    }

    /* renamed from: $r8$lambda$OMoKxvpWAo-unodom2_u-1v3mik, reason: not valid java name */
    public static /* synthetic */ void m6352$r8$lambda$OMoKxvpWAounodom2_u1v3mik(ChatbotsActivity r0, Runnable r1, AlertDialog r2, int r3) {
        r0.lambda$checkAlert$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SBFnpGdzw65amSGumlKUWKVb9ew(ChatbotsActivity r0, View r1) {
        r0.lambda$fillItems$5(r1);
    }

    /* renamed from: $r8$lambda$Z45co-A2vmX3ZP7z2NZA30sbup4, reason: not valid java name */
    public static /* synthetic */ void m6353$r8$lambda$Z45coA2vmX3ZP7z2NZA30sbup4(ChatbotsActivity r0, View r1) {
        r0.lambda$fillItems$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$Zwv56h0lc2aJRGN9be2wnhqis6M(ChatbotsActivity r0, TLRPC.TL_error r1, TLObject r2, int[] r3, ArrayList r4, boolean r5, TLRPC.User r6) {
        r0.lambda$processDone$19(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$Zywy_J8UrBoYpU-FWJ7UUkK3GKg, reason: not valid java name */
    public static /* synthetic */ void m6354$r8$lambda$Zywy_J8UrBoYpUFWJ7UUkK3GKg(ChatbotsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$aavqAsygGCh25D_0sGHM844pudE(ChatbotsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$23(r1, r2);
    }

    /* renamed from: $r8$lambda$b7DyDu-HumMUpY6ZX2aPgue3mtI, reason: not valid java name */
    public static /* synthetic */ void m6355$r8$lambda$b7DyDuHumMUpY6ZX2aPgue3mtI(ChatbotsActivity r0, View r1) {
        r0.lambda$onClick$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$biBzRXpg70ckzGVvw17KesPh1B0(ChatbotsActivity r0) {
        r0.lambda$onClick$17();
    }

    public static /* synthetic */ boolean $r8$lambda$eln3t24hycCXk_Zdv_qNYrKWjhU(ChatbotsActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$f1DNvVGd5Ayq338aXd97nK8A-T4, reason: not valid java name */
    public static /* synthetic */ void m6356$r8$lambda$f1DNvVGd5Ayq338aXd97nK8AT4(ChatbotsActivity r0, View r1) {
        r0.lambda$onClick$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$gdFE1G2t7Jq6X92M_45YTeShj6g(ChatbotsActivity r0) {
        r0.lambda$fillItems$6();
    }

    public static /* synthetic */ void $r8$lambda$j8QmSCzJnIf8cIqnlIAV5la1vxQ(ChatbotsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$22(r1, r2);
    }

    /* renamed from: $r8$lambda$kpgdagUY-jRxk72FWnuc-5g_-Xo, reason: not valid java name */
    public static /* synthetic */ void m6357$r8$lambda$kpgdagUYjRxk72FWnuc5g_Xo(ChatbotsActivity r0) {
        r0.lambda$fillItems$8();
    }

    /* renamed from: $r8$lambda$mEHXWoP2JINnNbIF5q10-MT_iNw, reason: not valid java name */
    public static /* synthetic */ void m6358$r8$lambda$mEHXWoP2JINnNbIF5q10MT_iNw(ChatbotsActivity r0, View r1) {
        r0.lambda$fillItems$9(r1);
    }

    /* renamed from: $r8$lambda$pujsdkdi91PZqoe2X0T-0Bjqbnc, reason: not valid java name */
    public static /* synthetic */ void m6359$r8$lambda$pujsdkdi91PZqoe2X0T0Bjqbnc(ChatbotsActivity r0, View r1) {
        r0.clear(r1);
    }

    public static /* synthetic */ void $r8$lambda$rmgVrPODn_wabKn0mxlOf6YyrRA(ChatbotsActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$wwFW0fBYbtXxedu8qZOcG4r-DEk, reason: not valid java name */
    public static /* synthetic */ void m6360$r8$lambda$wwFW0fBYbtXxedu8qZOcG4rDEk(ChatbotsActivity r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$xCUSQx1sOYhSD58rlgdgMZUlwl8(ChatbotsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xGf3HWsi2C_VqW1FyakbVlIuKAo(ChatbotsActivity r0, View r1) {
        r0.lambda$onClick$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$xKbZDUI4NbmBxJNEsAbdYiWnP9A(ChatbotsActivity r0, TL_account.connectedBots r1) {
        r0.lambda$setValue$21(r1);
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m6361$$Nest$fgetlistView(ChatbotsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6362$$Nest$mprocessDone(ChatbotsActivity r0) {
        r0.processDone();
    }

    /* renamed from: -$$Nest$mscheduleSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6363$$Nest$mscheduleSearch(ChatbotsActivity r0) {
        r0.scheduleSearch();
    }

    /* renamed from: -$$Nest$mupdateSearchLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6364$$Nest$mupdateSearchLoading(ChatbotsActivity r0) {
        r0.updateSearchLoading();
    }

    static {
        int r0 = 0 - 1;
        RADIO_EXCLUDE = r0;
        RADIO_INCLUDE = r0 - 1;
        BUTTON_DELETE = r0 - 2;
        PERMISSION_MESSAGES = r0 - 3;
        PERMISSION_MESSAGES_READ = r0 - 4;
        PERMISSION_MESSAGES_REPLY = r0 - 5;
        PERMISSION_MESSAGES_MARK_AS_READ = r0 - 6;
        PERMISSION_MESSAGES_DELETE_SENT = r0 - 7;
        PERMISSION_MESSAGES_DELETE_RECEIVED = r0 - 8;
        PERMISSION_PROFILE = r0 - 9;
        PERMISSION_PROFILE_NAME = r0 - 10;
        PERMISSION_PROFILE_BIO = r0 - 11;
        PERMISSION_PROFILE_PICTURE = r0 - 12;
        PERMISSION_PROFILE_USERNAME = r0 - 13;
        PERMISSION_GIFTS = r0 - 14;
        PERMISSION_GIFTS_VIEW = r0 - 15;
        PERMISSION_GIFTS_SELL = r0 - 16;
        PERMISSION_GIFTS_SETTINGS = r0 - 17;
        PERMISSION_GIFTS_TRANSFER = r0 - 18;
        PERMISSION_GIFTS_TRANSFER_STARS = r0 - 19;
        int r02 = r0 - 20;
        ids = r02;
        PERMISSION_STORIES = r02;
    }

    public ChatbotsActivity() {
        this.searchId = 0;
        this.search = new ChatbotsActivity$$ExternalSyntheticLambda8(this);
        this.rights = TL_account.TL_businessBotRights.makeDefault();
        this.selectedBot = null;
        this.foundBots = new LongSparseArray();
        this.shakeDp = -4;
        this.expandedMessagesSection = true;
        this.expandedProfileSection = false;
        this.expandedGiftsSection = false;
    }

    private void checkAlert(int r4, boolean r5, Runnable r6) {
        if (this.shownUsernamePermissionsAlert == true) goto L10;
        if (r4 != PERMISSION_PROFILE_USERNAME) goto L10;
        if (r5 == false) goto L10;
        new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.BusinessBotPermissionsWarning)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BusinessBotPermissionsUsernamesWarningText, new Object[]{UserObject.getPublicUsername(this.selectedBot)}))).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(LocaleController.getString(R.string.Allow), new ChatbotsActivity$$ExternalSyntheticLambda25(this, r6)).makeRed(-1).show();
        return;
    L10:
        if (this.shownGiftsPermissionsAlert == true) goto L22;
        if (r5 == false) goto L22;
        if (r4 != PERMISSION_GIFTS_SELL) goto L15;
    L20:
        new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.BusinessBotPermissionsWarning)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BusinessBotPermissionsGiftsWarningText, new Object[]{UserObject.getPublicUsername(this.selectedBot)}))).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(LocaleController.getString(R.string.Allow), new ChatbotsActivity$$ExternalSyntheticLambda26(this, r6)).makeRed(-1).show();
        return;
    L15:
        if (r4 == PERMISSION_GIFTS_SETTINGS) goto L20;
        if (r4 == PERMISSION_GIFTS_TRANSFER) goto L20;
        if (r4 == PERMISSION_GIFTS_TRANSFER_STARS) goto L20;
    L22:
        r6.run();
    }

    private void checkDone(boolean r5) {
        if (this.doneButton != null) goto L5;
        return;
    L5:
        boolean r0 = hasChanges();
        this.doneButton.setEnabled(r0);
        ActionBarMenuItem r1 = this.doneButton;
        float r2 = 0.0f;
        if (r5 == false) goto L20;
        ViewPropertyAnimator r4 = r1.animate();
        if (r0 == false) goto L10;
        float r52 = 1.0f;
    L11:
        ViewPropertyAnimator r42 = r4.alpha(r52);
        if (r0 == false) goto L14;
        float r53 = 1.0f;
    L15:
        ViewPropertyAnimator r43 = r42.scaleX(r53);
        if (r0 == false) goto L18;
        r2 = 1.0f;
    L18:
        r43.scaleY(r2).setDuration(180).start();
        return;
    L14:
        r53 = 0.0f;
        goto L15
    L10:
        r52 = 0.0f;
        goto L11
    L20:
        if (r0 == false) goto L22;
        float r54 = 1.0f;
    L23:
        r1.setAlpha(r54);
        ActionBarMenuItem r55 = this.doneButton;
        if (r0 == false) goto L26;
        float r12 = 1.0f;
    L27:
        r55.setScaleX(r12);
        ActionBarMenuItem r44 = this.doneButton;
        if (r0 == false) goto L30;
        r2 = 1.0f;
    L30:
        r44.setScaleY(r2);
        return;
    L26:
        r12 = 0.0f;
        goto L27
    L22:
        r54 = 0.0f;
        goto L23
    }

    private void clear(View r2) {
        this.selectedBot = null;
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private void fillItems(ArrayList<UItem> r11, UniversalAdapter r12) {
        r11.add(UItem.asTopView(LocaleController.getString(R.string.BusinessBots2), LocaleController.getString(R.string.BusinessBots2Info), 120, AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME_2, "🤖🏝️"));
        boolean r1 = false;
        if (this.selectedBot == null) goto L5;
        r12.whiteSectionStart();
        r11.add(UItem.asAddChat(Long.valueOf(this.selectedBot.id)).setChecked(true).setCloseIcon(new ChatbotsActivity$$ExternalSyntheticLambda17(this)));
        r12.whiteSectionEnd();
    L41:
        r11.add(UItem.asShadow(LocaleController.getString(R.string.BusinessBotLinkInfo2)));
        r12.whiteSectionStart();
        UItem r0 = UItem.asHeader(LocaleController.getString(R.string.BusinessBotChats2));
        if (this.selectedBot == null) goto L44;
        boolean r3 = true;
    L45:
        r11.add(r0.setEnabled(r3));
        UItem r02 = UItem.asRadio(RADIO_EXCLUDE, LocaleController.getString(R.string.BusinessChatsAllPrivateExcept2)).setChecked(this.exclude);
        if (this.selectedBot == null) goto L48;
        boolean r32 = true;
    L49:
        r11.add(r02.setEnabled(r32));
        UItem r03 = UItem.asRadio(RADIO_INCLUDE, LocaleController.getString(R.string.BusinessChatsOnlySelected2)).setChecked(!this.exclude);
        if (this.selectedBot == null) goto L52;
        boolean r33 = true;
    L53:
        r11.add(r03.setEnabled(r33));
        r12.whiteSectionEnd();
        r11.add(UItem.asShadow(null));
        BusinessRecipientsHelper r34 = this.recipientsHelper;
        if (this.selectedBot == null) goto L56;
        boolean r4 = true;
    L57:
        r34.fillItems(r11, r12, r4);
        r11.add(UItem.asShadow(LocaleController.getString(R.string.BusinessBotChatsInfo2)));
        if (this.selectedBot == null) goto L109;
        r12.whiteSectionStart();
        r11.add(UItem.asHeader(LocaleController.getString(R.string.BusinessBotPermissions)));
        int r35 = PERMISSION_MESSAGES;
        String r42 = LocaleController.getString(R.string.BusinessBotPermissionsMessagesSection);
        StringBuilder r5 = new StringBuilder();
        TL_account.TL_businessBotRights r6 = this.rights;
        r5.append(((((r6.reply ? 1 : 0) + 1) + (r6.read_messages ? 1 : 0)) + (r6.delete_sent_messages ? 1 : 0)) + (r6.delete_received_messages ? 1 : 0));
        r5.append("/5");
        UItem r36 = UItem.asExpandableSwitch(r35, r42, r5.toString());
        TL_account.TL_businessBotRights r43 = this.rights;
        if (r43.reply == true) goto L62;
    L68:
        boolean r44 = false;
    L69:
        r11.add(r36.setChecked(r44).setCollapsed(!this.expandedMessagesSection).setClickCallback(new ChatbotsActivity$$ExternalSyntheticLambda18(this)));
        if (this.expandedMessagesSection == false) goto L72;
        r11.add(UItem.asRoundCheckbox(PERMISSION_MESSAGES_READ, LocaleController.getString(R.string.BusinessBotPermissionsMessagesRead)).setChecked(true).setEnabled(false).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_MESSAGES_REPLY, LocaleController.getString(R.string.BusinessBotPermissionsMessagesReply)).setChecked(this.rights.reply).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_MESSAGES_MARK_AS_READ, LocaleController.getString(R.string.BusinessBotPermissionsMessagesMarkAsRead)).setChecked(this.rights.read_messages).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_MESSAGES_DELETE_SENT, LocaleController.getString(R.string.BusinessBotPermissionsMessagesDeleteSent)).setChecked(this.rights.delete_sent_messages).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_MESSAGES_DELETE_RECEIVED, LocaleController.getString(R.string.BusinessBotPermissionsMessagesDeleteReceived)).setChecked(this.rights.delete_received_messages).setPad(1));
    L72:
        int r37 = PERMISSION_PROFILE;
        String r45 = LocaleController.getString(R.string.BusinessBotPermissionsProfileSection);
        StringBuilder r52 = new StringBuilder();
        TL_account.TL_businessBotRights r7 = this.rights;
        r52.append((((r7.edit_name ? 1 : 0) + (r7.edit_bio ? 1 : 0)) + (r7.edit_profile_photo ? 1 : 0)) + (r7.edit_username ? 1 : 0));
        r52.append("/4");
        UItem r38 = UItem.asExpandableSwitch(r37, r45, r52.toString());
        TL_account.TL_businessBotRights r46 = this.rights;
        if (r46.edit_name == true) goto L75;
    L81:
        boolean r47 = false;
    L82:
        r11.add(r38.setChecked(r47).setCollapsed(!this.expandedProfileSection).setClickCallback(new ChatbotsActivity$$ExternalSyntheticLambda19(this)));
        if (this.expandedProfileSection == false) goto L85;
        r11.add(UItem.asRoundCheckbox(PERMISSION_PROFILE_NAME, LocaleController.getString(R.string.BusinessBotPermissionsProfileName)).setChecked(this.rights.edit_name).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_PROFILE_BIO, LocaleController.getString(R.string.BusinessBotPermissionsProfileBio)).setChecked(this.rights.edit_bio).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_PROFILE_PICTURE, LocaleController.getString(R.string.BusinessBotPermissionsProfilePicture)).setChecked(this.rights.edit_profile_photo).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_PROFILE_USERNAME, LocaleController.getString(R.string.BusinessBotPermissionsProfileUsername)).setChecked(this.rights.edit_username).setPad(1));
    L85:
        int r39 = PERMISSION_GIFTS;
        String r48 = LocaleController.getString(R.string.BusinessBotPermissionsGiftsSection);
        StringBuilder r53 = new StringBuilder();
        TL_account.TL_businessBotRights r72 = this.rights;
        r53.append(((((r72.view_gifts ? 1 : 0) + (r72.sell_gifts ? 1 : 0)) + (r72.change_gift_settings ? 1 : 0)) + (r72.transfer_and_upgrade_gifts ? 1 : 0)) + (r72.transfer_stars ? 1 : 0));
        r53.append("/5");
        UItem r310 = UItem.asExpandableSwitch(r39, r48, r53.toString());
        TL_account.TL_businessBotRights r49 = this.rights;
        if (r49.view_gifts == true) goto L88;
    L96:
        r11.add(r310.setChecked(r1).setCollapsed(!this.expandedGiftsSection).setClickCallback(new ChatbotsActivity$$ExternalSyntheticLambda20(this)));
        if (this.expandedGiftsSection == false) goto L99;
        r11.add(UItem.asRoundCheckbox(PERMISSION_GIFTS_VIEW, LocaleController.getString(R.string.BusinessBotPermissionsGiftsView)).setChecked(this.rights.view_gifts).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_GIFTS_SELL, LocaleController.getString(R.string.BusinessBotPermissionsGiftsSell)).setChecked(this.rights.sell_gifts).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_GIFTS_SETTINGS, LocaleController.getString(R.string.BusinessBotPermissionsGiftsSettings)).setChecked(this.rights.change_gift_settings).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_GIFTS_TRANSFER, LocaleController.getString(R.string.BusinessBotPermissionsGiftsTransfer)).setChecked(this.rights.transfer_and_upgrade_gifts).setPad(1));
        r11.add(UItem.asRoundCheckbox(PERMISSION_GIFTS_TRANSFER_STARS, LocaleController.getString(R.string.BusinessBotPermissionsGiftsTransferStars)).setChecked(this.rights.transfer_stars).setPad(1));
    L99:
        r11.add(UItem.asExpandableSwitch(PERMISSION_STORIES, LocaleController.getString(R.string.BusinessBotPermissionsStories), _UrlKt.FRAGMENT_ENCODE_SET).setChecked(this.rights.manage_stories).setClickCallback(new ChatbotsActivity$$ExternalSyntheticLambda21(this)));
        r12.whiteSectionEnd();
        r11.add(UItem.asShadow(-4, null));
        r11.add(UItem.asShadow(-5, null));
        r11.add(UItem.asShadow(-6, null));
        r11.add(UItem.asShadow(-7, null));
        return;
    L88:
        if (r49.sell_gifts == false) goto L96;
        if (r49.change_gift_settings == false) goto L96;
        if (r49.transfer_and_upgrade_gifts == false) goto L96;
        if (r49.transfer_stars == false) goto L96;
        r1 = true;
        goto L96
    L75:
        if (r46.edit_bio == false) goto L81;
        if (r46.edit_profile_photo == false) goto L81;
        if (r46.edit_username == false) goto L81;
        r47 = true;
        goto L82
    L62:
        if (r43.read_messages == false) goto L68;
        if (r43.delete_received_messages == false) goto L68;
        if (r43.delete_sent_messages == false) goto L68;
        r44 = true;
        goto L69
    L109:
        return;
    L56:
        r4 = false;
        goto L57
    L52:
        r33 = false;
        goto L53
    L48:
        r32 = false;
        goto L49
    L44:
        r3 = false;
        goto L45
    L5:
        r12.whiteSectionStart();
        r11.add(UItem.asCustom(this.editTextContainer));
        this.foundBots.clear();
        int r04 = 0;
        boolean r311 = false;
    L7:
        if (r04 >= this.searchHelper.getLocalServerSearch().size()) goto L16;
        TLObject r410 = this.searchHelper.getLocalServerSearch().get(r04);
        if ((r410 instanceof TLRPC.User) == false) goto L15;
        TLRPC.User r411 = (TLRPC.User) r410;
        if (r411.bot == false) goto L15;
        r11.add(UItem.asAddChat(Long.valueOf(r411.id), this.lastQuery));
        this.foundBots.put(r411.id, r411);
        r311 = true;
    L15:
        r04 = r04 + 1;
        r311 = r311;
        goto L7
    L16:
        int r05 = 0;
        boolean r312 = r311;
    L18:
        if (r05 >= this.searchHelper.getGlobalSearch().size()) goto L27;
        TLObject r412 = this.searchHelper.getGlobalSearch().get(r05);
        if ((r412 instanceof TLRPC.User) == false) goto L26;
        TLRPC.User r413 = (TLRPC.User) r412;
        if (r413.bot == false) goto L26;
        r11.add(UItem.asAddChat(Long.valueOf(r413.id), this.lastQuery));
        this.foundBots.put(r413.id, r413);
        r312 = true;
    L26:
        r05 = r05 + 1;
        r312 = r312;
        goto L18
    L27:
        boolean r313 = r312;
        if (this.foundBots.size() <= 0) goto L30;
    L36:
        View r06 = this.editTextDivider;
        if (r313 == false) goto L39;
        int r314 = 0;
    L40:
        r06.setVisibility(r314);
        r12.whiteSectionEnd();
        goto L41
    L39:
        r314 = 8;
        goto L40
    L30:
        if (TextUtils.isEmpty(this.editText.getText().toString()) == true) goto L32;
    L35:
        r11.add(UItem.asCustom(this.emptyView));
        r313 = true;
        goto L36
    L32:
        if (this.searchHelper.isSearchInProgress() == true) goto L35;
        r313 = r312;
        if (this.scheduledLoading == false) goto L36;
        goto L35
    }

    private /* synthetic */ void lambda$checkAlert$3(Runnable r1, AlertDialog r2, int r3) {
        this.shownUsernamePermissionsAlert = true;
        r1.run();
    }

    private /* synthetic */ void lambda$checkAlert$4(Runnable r1, AlertDialog r2, int r3) {
        this.shownGiftsPermissionsAlert = true;
        r1.run();
    }

    private /* synthetic */ boolean lambda$createView$0(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L10;
        this.scheduledLoading = false;
        AndroidUtilities.cancelRunOnUIThread(this.search);
        if (TextUtils.isEmpty(this.editText.getText()) == false) goto L7;
        this.lastQuery = null;
        this.searchHelper.clear();
        this.listView.adapter.update(true);
    L8:
        updateSearchLoading();
        return true;
    L7:
        AndroidUtilities.runOnUIThread(this.search);
        goto L8
    L10:
        return false;
    }

    private /* synthetic */ void lambda$createView$1() {
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$fillItems$10(View r3) {
        this.rights.manage_stories = !r3.manage_stories;
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$fillItems$5(View r3) {
        TL_account.TL_businessBotRights r32 = this.rights;
        if (r32.reply == true) goto L5;
    L11:
        r32.delete_sent_messages = true;
        r32.delete_received_messages = true;
        r32.read_messages = true;
        r32.reply = true;
    L12:
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L5:
        if (r32.read_messages == false) goto L11;
        if (r32.delete_received_messages == false) goto L11;
        if (r32.delete_sent_messages == false) goto L11;
        r32.delete_sent_messages = false;
        r32.delete_received_messages = false;
        r32.read_messages = false;
        r32.reply = false;
        goto L12
    }

    private /* synthetic */ void lambda$fillItems$6() {
        TL_account.TL_businessBotRights r0 = this.rights;
        r0.edit_username = true;
        r0.edit_profile_photo = true;
        r0.edit_bio = true;
        r0.edit_name = true;
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$fillItems$7(View r3) {
        TL_account.TL_businessBotRights r32 = this.rights;
        if (r32.edit_name == true) goto L5;
    L12:
        checkAlert(PERMISSION_PROFILE_USERNAME, true, new ChatbotsActivity$$ExternalSyntheticLambda24(this));
        return;
    L5:
        if (r32.edit_bio == false) goto L12;
        if (r32.edit_profile_photo == false) goto L12;
        if (r32.edit_username == false) goto L12;
        r32.edit_username = false;
        r32.edit_profile_photo = false;
        r32.edit_bio = false;
        r32.edit_name = false;
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$fillItems$8() {
        TL_account.TL_businessBotRights r0 = this.rights;
        r0.transfer_stars = true;
        r0.transfer_and_upgrade_gifts = true;
        r0.change_gift_settings = true;
        r0.sell_gifts = true;
        r0.view_gifts = true;
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$fillItems$9(View r3) {
        TL_account.TL_businessBotRights r32 = this.rights;
        if (r32.view_gifts == true) goto L5;
    L14:
        checkAlert(PERMISSION_GIFTS_SELL, true, new ChatbotsActivity$$ExternalSyntheticLambda23(this));
        return;
    L5:
        if (r32.sell_gifts == false) goto L14;
        if (r32.change_gift_settings == false) goto L14;
        if (r32.transfer_and_upgrade_gifts == false) goto L14;
        if (r32.transfer_stars == false) goto L14;
        r32.transfer_stars = false;
        r32.transfer_and_upgrade_gifts = false;
        r32.change_gift_settings = false;
        r32.sell_gifts = false;
        r32.view_gifts = false;
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$new$2() {
        String r2 = this.editText.getText().toString();
        String r0 = this.lastQuery;
        if (r0 != null) goto L5;
    L7:
        this.scheduledLoading = false;
        if (TextUtils.isEmpty(r2) == false) goto L11;
        this.lastQuery = null;
        this.searchHelper.clear();
        this.listView.adapter.update(true);
        return;
    L11:
        SearchAdapterHelper r1 = this.searchHelper;
        this.lastQuery = r2;
        int r12 = this.searchId;
        this.searchId = r12 + 1;
        r1.queryServerSearch(r2, true, false, true, false, false, 0, false, 0, r12, 0);
        return;
    L5:
        if (TextUtils.equals(r0, r2) == false) goto L7;
    }

    private /* synthetic */ void lambda$onBackPressed$22(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$onBackPressed$23(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$onBackPressed$24(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$onClick$11(View r4) {
        TL_account.TL_businessBotRights r0 = this.rights;
        boolean r1 = !r0.edit_username;
        r0.edit_username = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$12(View r4) {
        TL_account.TL_businessBotRights r0 = this.rights;
        boolean r1 = !r0.view_gifts;
        r0.view_gifts = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$13(View r4) {
        TL_account.TL_businessBotRights r0 = this.rights;
        boolean r1 = !r0.sell_gifts;
        r0.sell_gifts = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$14(View r4) {
        TL_account.TL_businessBotRights r0 = this.rights;
        boolean r1 = !r0.change_gift_settings;
        r0.change_gift_settings = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$15(View r4) {
        TL_account.TL_businessBotRights r0 = this.rights;
        boolean r1 = !r0.transfer_and_upgrade_gifts;
        r0.transfer_and_upgrade_gifts = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$16(View r4) {
        TL_account.TL_businessBotRights r0 = this.rights;
        boolean r1 = !r0.transfer_stars;
        r0.transfer_stars = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$17() {
        this.rights.manage_stories = !r0.manage_stories;
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$processDone$18(TLObject r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r2, false);
    }

    private /* synthetic */ void lambda$processDone$19(TLRPC.TL_error r2, TLObject r3, int[] r4, ArrayList r5, boolean r6, TLRPC.User r7) {
        if (r2 == null) goto L7;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.showError(r2);
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_boolFalse) == false) goto L11;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L11:
        if ((r3 instanceof TLRPC.Updates) == false) goto L13;
        Utilities.stageQueue.postRunnable(new ChatbotsActivity$$ExternalSyntheticLambda27(this, r3));
    L13:
        int r32 = r4[0] + 1;
        r4[0] = r32;
        if (r32 != r5.size()) goto L27;
        BusinessChatbotController.getInstance(this.currentAccount).invalidate(true);
        getMessagesController().clearFullUsers();
        finishFragment();
        if (r6 == false) goto L22;
        if (r7 == null) goto L22;
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 == null) goto L28;
        BulletinFactory.of(r1).createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.BusinessBotDone, new Object[]{UserObject.getUserName(r7)})).show();
        return;
    L28:
        return;
    L22:
        if (r7 == null) goto L29;
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L30;
        BulletinFactory.of(r12).createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.BusinessBotUpdated, new Object[]{UserObject.getUserName(r7)})).show();
        return;
    L30:
        return;
    L29:
        return;
    }

    private /* synthetic */ void lambda$processDone$20(int[] r9, ArrayList r10, boolean r11, TLRPC.User r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new ChatbotsActivity$$ExternalSyntheticLambda22(this, r14, r13, r9, r10, r11, r12));
    }

    private /* synthetic */ void lambda$setValue$21(TL_account.connectedBots r4) {
        this.currentValue = r4;
        TL_account.TL_businessBotRecipients r0 = null;
        if (r4 != null) goto L5;
    L8:
        TL_account.TL_connectedBot r42 = null;
    L9:
        this.currentBot = r42;
        if (r42 != null) goto L12;
        TLRPC.User r43 = null;
    L13:
        this.selectedBot = r43;
        TL_account.TL_connectedBot r44 = this.currentBot;
        if (r44 == null) goto L16;
        TL_account.TL_businessBotRights r45 = TL_account.TL_businessBotRights.clone(r44.rights);
    L17:
        this.rights = r45;
        TL_account.TL_connectedBot r46 = this.currentBot;
        if (r46 == null) goto L20;
        boolean r2 = r46.recipients.exclude_selected;
    L21:
        this.exclude = r2;
        BusinessRecipientsHelper r22 = this.recipientsHelper;
        if (r22 == null) goto L27;
        if (r46 == null) goto L26;
        r0 = r46.recipients;
    L26:
        r22.setValue(r0);
    L27:
        UniversalRecyclerView r47 = this.listView;
        if (r47 == null) goto L32;
        UniversalAdapter r48 = r47.adapter;
        if (r48 == null) goto L32;
        r48.update(true);
    L32:
        checkDone(true);
        this.valueSet = true;
        return;
    L20:
        r2 = true;
        goto L21
    L16:
        r45 = TL_account.TL_businessBotRights.makeDefault();
        goto L17
    L12:
        r43 = getMessagesController().getUser(Long.valueOf(this.currentBot.bot_id));
        goto L13
    L5:
        if (r4.connected_bots.isEmpty() == true) goto L8;
        r42 = this.currentValue.connected_bots.get(0);
        goto L9
    }

    private void onClick(UItem r3, View r4, int r5, float r6, float r7) {
        if (r3.enabled == true) goto L6;
        return;
    L6:
        if (this.recipientsHelper.onClick(r3) == true) goto L105;
        int r52 = r3.id;
        if (r52 != RADIO_EXCLUDE) goto L13;
        BusinessRecipientsHelper r32 = this.recipientsHelper;
        this.exclude = true;
        r32.setExclude(true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L13:
        if (r52 != RADIO_INCLUDE) goto L17;
        BusinessRecipientsHelper r33 = this.recipientsHelper;
        this.exclude = false;
        r33.setExclude(false);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L17:
        if (r52 != BUTTON_DELETE) goto L21;
        this.selectedBot = null;
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L21:
        if (r3.viewType != 13) goto L32;
        TLRPC.User r34 = this.foundBots.get(r3.dialogId);
        if (r34 != null) goto L26;
        return;
    L26:
        if (r34.bot_business == true) goto L29;
        showDialog(new AlertDialog.Builder(getContext(), this.resourceProvider).setTitle(LocaleController.getString(R.string.BusinessBotNotSupportedTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.BusinessBotNotSupportedMessage))).setPositiveButton(LocaleController.getString(R.string.OK), null).create());
        return;
    L29:
        this.selectedBot = r34;
        AndroidUtilities.hideKeyboard(this.editText);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L32:
        if (r52 != PERMISSION_MESSAGES) goto L36;
        boolean r35 = !this.expandedMessagesSection;
        this.expandedMessagesSection = r35;
        ((TextCheckCell2) r4).setChecked(r35);
        this.listView.adapter.update(true);
        return;
    L36:
        if (r52 != PERMISSION_MESSAGES_READ) goto L40;
        int r36 = -this.shakeDp;
        this.shakeDp = r36;
        AndroidUtilities.shakeViewSpring(r4, r36);
        return;
    L40:
        if (r52 != PERMISSION_MESSAGES_REPLY) goto L44;
        TL_account.TL_businessBotRights r37 = this.rights;
        boolean r53 = !r37.reply;
        r37.reply = r53;
        ((CheckBoxCell) r4).setChecked(r53, true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L44:
        if (r52 != PERMISSION_MESSAGES_MARK_AS_READ) goto L48;
        TL_account.TL_businessBotRights r38 = this.rights;
        boolean r54 = !r38.read_messages;
        r38.read_messages = r54;
        ((CheckBoxCell) r4).setChecked(r54, true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L48:
        if (r52 != PERMISSION_MESSAGES_DELETE_SENT) goto L52;
        TL_account.TL_businessBotRights r39 = this.rights;
        boolean r55 = !r39.delete_sent_messages;
        r39.delete_sent_messages = r55;
        ((CheckBoxCell) r4).setChecked(r55, true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L52:
        if (r52 != PERMISSION_MESSAGES_DELETE_RECEIVED) goto L56;
        TL_account.TL_businessBotRights r310 = this.rights;
        boolean r56 = !r310.delete_received_messages;
        r310.delete_received_messages = r56;
        ((CheckBoxCell) r4).setChecked(r56, true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L56:
        if (r52 != PERMISSION_PROFILE) goto L60;
        boolean r311 = !this.expandedProfileSection;
        this.expandedProfileSection = r311;
        ((TextCheckCell2) r4).setChecked(r311);
        this.listView.adapter.update(true);
        return;
    L60:
        if (r52 != PERMISSION_PROFILE_NAME) goto L64;
        TL_account.TL_businessBotRights r312 = this.rights;
        boolean r57 = !r312.edit_name;
        r312.edit_name = r57;
        ((CheckBoxCell) r4).setChecked(r57, true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L64:
        if (r52 != PERMISSION_PROFILE_BIO) goto L68;
        TL_account.TL_businessBotRights r313 = this.rights;
        boolean r58 = !r313.edit_bio;
        r313.edit_bio = r58;
        ((CheckBoxCell) r4).setChecked(r58, true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L68:
        if (r52 != PERMISSION_PROFILE_PICTURE) goto L72;
        TL_account.TL_businessBotRights r314 = this.rights;
        boolean r59 = !r314.edit_profile_photo;
        r314.edit_profile_photo = r59;
        ((CheckBoxCell) r4).setChecked(r59, true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L72:
        if (r52 != PERMISSION_PROFILE_USERNAME) goto L76;
        checkAlert(r52, !this.rights.edit_username, new ChatbotsActivity$$ExternalSyntheticLambda9(this, r4));
        return;
    L76:
        if (r52 != PERMISSION_GIFTS) goto L80;
        boolean r315 = !this.expandedGiftsSection;
        this.expandedGiftsSection = r315;
        ((TextCheckCell2) r4).setChecked(r315);
        this.listView.adapter.update(true);
        return;
    L80:
        if (r52 != PERMISSION_GIFTS_VIEW) goto L84;
        checkAlert(r52, !this.rights.view_gifts, new ChatbotsActivity$$ExternalSyntheticLambda10(this, r4));
        return;
    L84:
        if (r52 != PERMISSION_GIFTS_SELL) goto L88;
        checkAlert(r52, !this.rights.sell_gifts, new ChatbotsActivity$$ExternalSyntheticLambda11(this, r4));
        return;
    L88:
        if (r52 != PERMISSION_GIFTS_SETTINGS) goto L92;
        checkAlert(r52, !this.rights.change_gift_settings, new ChatbotsActivity$$ExternalSyntheticLambda12(this, r4));
        return;
    L92:
        if (r52 != PERMISSION_GIFTS_TRANSFER) goto L96;
        checkAlert(r52, !this.rights.transfer_and_upgrade_gifts, new ChatbotsActivity$$ExternalSyntheticLambda13(this, r4));
        return;
    L96:
        if (r52 != PERMISSION_GIFTS_TRANSFER_STARS) goto L100;
        checkAlert(r52, !this.rights.transfer_stars, new ChatbotsActivity$$ExternalSyntheticLambda14(this, r4));
        return;
    L100:
        if (r52 != PERMISSION_STORIES) goto L103;
        checkAlert(r52, !this.rights.manage_stories, new ChatbotsActivity$$ExternalSyntheticLambda15(this));
        return;
    L103:
        return;
    }

    private void processDone() {
        if (this.doneButtonDrawable.getProgress() <= 0.0f) goto L6;
        return;
    L6:
        if (hasChanges() == true) goto L10;
        finishFragment();
        return;
    L10:
        if (this.recipientsHelper.validate(this.listView) == false) goto L42;
        TLRPC.User r6 = this.selectedBot;
        if (r6 == null) goto L19;
        TL_account.TL_connectedBot r2 = this.currentBot;
        if (r2 != null) goto L17;
    L18:
        boolean r5 = true;
    L20:
        ArrayList r4 = new ArrayList();
        TL_account.TL_connectedBot r22 = this.currentBot;
        if (r22 == null) goto L28;
        TLRPC.User r3 = this.selectedBot;
        if (r3 != null) goto L25;
    L26:
        TL_account.updateConnectedBot r23 = new TL_account.updateConnectedBot();
        r23.deleted = true;
        r23.bot = getMessagesController().getInputUser(this.currentBot.bot_id);
        r23.recipients = new TL_account.TL_inputBusinessBotRecipients();
        r4.add(r23);
        goto L28
    L25:
        if (r22.bot_id != r3.id) goto L26;
    L28:
        if (this.selectedBot == null) goto L33;
        TL_account.updateConnectedBot r0 = new TL_account.updateConnectedBot();
        r0.deleted = false;
        r0.rights = this.rights;
        r0.bot = getMessagesController().getInputUser(this.selectedBot);
        r0.recipients = this.recipientsHelper.getBotInputValue();
        r4.add(r0);
        TL_account.TL_connectedBot r02 = this.currentBot;
        if (r02 == null) goto L33;
        r02.bot_id = this.selectedBot.id;
        r02.recipients = this.recipientsHelper.getBotValue();
        this.currentBot.rights = this.rights;
    L33:
        if (r4.isEmpty() == false) goto L36;
        finishFragment();
        return;
    L36:
        int[] r32 = {0};
        int r03 = 0;
    L38:
        if (r03 >= r4.size()) goto L43;
        getConnectionsManager().sendRequest((TLObject) r4.get(r03), new ChatbotsActivity$$ExternalSyntheticLambda16(this, r32, r4, r5, r6));
        r03 = r03 + 1;
        goto L38
    L43:
        return;
    L17:
        if (r2.bot_id != r6.id) goto L18;
    L19:
        r5 = false;
        goto L20
    }

    private void scheduleSearch() {
        this.scheduledLoading = false;
        AndroidUtilities.cancelRunOnUIThread(this.search);
        if (TextUtils.isEmpty(this.editText.getText()) == false) goto L5;
        this.lastQuery = null;
        this.searchHelper.clear();
    L6:
        this.listView.adapter.update(true);
        updateSearchLoading();
        return;
    L5:
        this.scheduledLoading = true;
        AndroidUtilities.runOnUIThread(this.search, 800);
        goto L6
    }

    private void setValue() {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (this.valueSet == true) goto L10;
        this.loading = true;
        BusinessChatbotController.getInstance(this.currentAccount).load(new ChatbotsActivity$$ExternalSyntheticLambda0(this));
        return;
    }

    private void updateSearchLoading() {
        boolean r0 = this.wasLoading;
        boolean r2 = true;
        if (this.searchHelper.isSearchInProgress() == false) goto L5;
    L10:
        boolean r1 = true;
    L11:
        if (r0 != r1) goto L13;
        return;
    L13:
        if (this.searchHelper.isSearchInProgress() == false) goto L15;
    L20:
        this.wasLoading = r2;
        ViewPropertyAnimator r02 = this.emptyViewText.animate();
        float r12 = 1.0f;
        float r3 = 0.0f;
        if (r2 == false) goto L23;
        float r4 = 0.0f;
    L24:
        ViewPropertyAnimator r03 = r02.alpha(r4);
        if (r2 == false) goto L27;
        float r5 = -AndroidUtilities.dp(8.0f);
    L28:
        ViewPropertyAnimator r04 = r03.translationY(r5).setDuration(320);
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r04.setInterpolator(r7).start();
        ViewPropertyAnimator r8 = this.emptyViewLoading.animate();
        if (r2 == true) goto L32;
        r12 = 0.0f;
    L32:
        ViewPropertyAnimator r82 = r8.alpha(r12);
        if (r2 == true) goto L36;
        r3 = AndroidUtilities.dp(8.0f);
    L36:
        r82.translationY(r3).setDuration(320).setInterpolator(r7).start();
        return;
    L27:
        r5 = 0.0f;
        goto L28
    L23:
        r4 = 1.0f;
        goto L24
    L15:
        if (this.scheduledLoading == true) goto L20;
        if (this.foundBots.size() > 0) goto L20;
        r2 = false;
        goto L20
    L5:
        if (this.scheduledLoading == true) goto L10;
        if (this.foundBots.size() > 0) goto L10;
        r1 = false;
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r19) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.BusinessBots2));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        Drawable r2 = r19.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r5 = Theme.key_actionBarDefaultIcon;
        r2.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r2, new CircularProgressDrawable(Theme.getColor(r5)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        FrameLayout r4 = new FrameLayout(r19);
        r4.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        new LinearLayout(getContext()).setOrientation(0);
        EditTextBoldCursor r52 = new EditTextBoldCursor(getContext());
        this.editText = r52;
        r52.setTextSize(1, 17.0f);
        this.editText.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r53 = this.editText;
        int r6 = Theme.key_windowBackgroundWhiteBlackText;
        r53.setTextColor(Theme.getColor(r6));
        this.editText.setBackgroundDrawable(null);
        this.editText.setMaxLines(1);
        this.editText.setLines(1);
        this.editText.setPadding(0, 0, 0, 0);
        this.editText.setSingleLine(true);
        EditTextBoldCursor r54 = this.editText;
        if (LocaleController.isRTL == false) goto L5;
        int r8 = 5;
    L6:
        r54.setGravity(r8 | 48);
        this.editText.setInputType(180224);
        this.editText.setImeOptions(6);
        this.editText.setHint(LocaleController.getString(R.string.BusinessBotLink));
        this.editText.setCursorColor(Theme.getColor(r6));
        this.editText.setCursorSize(AndroidUtilities.dp(19.0f));
        this.editText.setCursorWidth(1.5f);
        this.editText.setOnEditorActionListener(new ChatbotsActivity$$ExternalSyntheticLambda4(this));
        this.editText.addTextChangedListener(new AnonymousClass2(this));
        FrameLayout r55 = new FrameLayout(r19);
        this.editTextContainer = r55;
        r55.addView(this.editText, LayoutHelper.createFrame(-1, -1.0f, 48, 21.0f, 15.0f, 21.0f, 15.0f));
        FrameLayout r56 = this.editTextContainer;
        int r62 = Theme.key_windowBackgroundWhite;
        r56.setBackgroundColor(getThemedColor(r62));
        View r57 = new View(r19);
        this.editTextDivider = r57;
        r57.setBackgroundColor(getThemedColor(Theme.key_divider));
        FrameLayout r58 = this.editTextContainer;
        View r82 = this.editTextDivider;
        float r12 = 1.0f / AndroidUtilities.density;
        boolean r9 = LocaleController.isRTL;
        int r10 = 21;
        if (r9 == false) goto L9;
        int r11 = 0;
    L10:
        float r14 = r11;
        if (r9 == true) goto L14;
        r10 = 0;
    L14:
        r58.addView(r82, LayoutHelper.createFrame(-1, r12, 87, r14, 0.0f, r10, 0.0f));
        AnonymousClass3 r59 = new AnonymousClass3(this, r19);
        this.emptyView = r59;
        r59.setBackgroundColor(getThemedColor(r62));
        TextView r510 = new TextView(r19);
        this.emptyViewText = r510;
        r510.setText(LocaleController.getString(R.string.BusinessBotNotFound));
        this.emptyViewText.setTextSize(1, 14.0f);
        TextView r511 = this.emptyViewText;
        int r63 = Theme.key_windowBackgroundWhiteGrayText2;
        r511.setTextColor(getThemedColor(r63));
        this.emptyView.addView(this.emptyViewText, LayoutHelper.createFrame(-2, -2, 17));
        this.emptyViewLoading = new ImageView(r19);
        AnonymousClass4 r1 = new AnonymousClass4(this, getThemedColor(r63));
        this.emptyViewLoading.setScaleType(ImageView.ScaleType.CENTER);
        this.emptyViewLoading.setImageDrawable(r1);
        this.emptyView.addView(this.emptyViewLoading, LayoutHelper.createFrame(-2, -2, 17));
        this.emptyViewLoading.setAlpha(0.0f);
        this.emptyViewLoading.setTranslationY(AndroidUtilities.dp(8.0f));
        SearchAdapterHelper r13 = new SearchAdapterHelper(true);
        this.searchHelper = r13;
        r13.setDelegate(new AnonymousClass5(this));
        BusinessRecipientsHelper r15 = new BusinessRecipientsHelper(this, new ChatbotsActivity$$ExternalSyntheticLambda5(this));
        this.recipientsHelper = r15;
        TL_account.TL_connectedBot r512 = this.currentBot;
        if (r512 != null) goto L17;
        TL_account.TL_businessBotRecipients r513 = null;
    L18:
        r15.setValue(r513);
        UniversalRecyclerView r16 = new UniversalRecyclerView(this, new ChatbotsActivity$$ExternalSyntheticLambda6(this), new ChatbotsActivity$$ExternalSyntheticLambda7(this), null);
        this.listView = r16;
        r16.setSections();
        this.listView.adapter.setApplyBackground(false);
        r4.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView, true);
        this.fragmentView = r4;
        return r4;
    L17:
        r513 = r512.recipients;
        goto L18
    L9:
        r11 = 21;
        goto L10
    L5:
        r8 = 3;
        goto L6
    }

    public boolean hasChanges() {
        if (this.valueSet == true) goto L5;
        return false;
    L5:
        TLRPC.User r0 = this.selectedBot;
        if (r0 == null) goto L8;
        boolean r3 = true;
    L9:
        TL_account.TL_connectedBot r4 = this.currentBot;
        if (r4 == null) goto L12;
        boolean r5 = true;
    L13:
        if (r3 == r5) goto L15;
        return true;
    L15:
        long r52 = 0;
        if (r0 != null) goto L18;
        long r7 = 0;
    L19:
        if (r4 == null) goto L23;
        r52 = r4.bot_id;
    L23:
        if (r7 == r52) goto L25;
        return true;
    L25:
        if (r0 != null) goto L27;
    L34:
        return false;
    L27:
        if (this.rights.equals(r4.rights) == true) goto L29;
        return true;
    L29:
        BusinessRecipientsHelper r9 = this.recipientsHelper;
        if (r9 == null) goto L34;
        if (r9.hasChanges() == false) goto L34;
        return true;
    L18:
        r7 = r0.id;
        goto L19
    L12:
        r5 = false;
        goto L13
    L8:
        r3 = false;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public boolean notSelectedBot() {
        if (this.selectedBot == null) goto L6;
        return false;
    L6:
        if (hasChanges() == false) goto L9;
        return false;
    L9:
        if (this.searchHelper.getLocalServerSearch().isEmpty() == true) goto L11;
        return true;
    L11:
        if (this.searchHelper.getGlobalSearch().isEmpty() == false) goto L16;
        return false;
    L16:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r4) {
        if (hasChanges() == false) goto L8;
        if (r4 == false) goto L6;
        AlertDialog.Builder r42 = new AlertDialog.Builder(getParentActivity());
        r42.setTitle(LocaleController.getString(R.string.UnsavedChanges));
        r42.setMessage(LocaleController.getString(R.string.BusinessBotUnsavedChanges));
        r42.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new ChatbotsActivity$$ExternalSyntheticLambda1(this));
        r42.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new ChatbotsActivity$$ExternalSyntheticLambda2(this));
        showDialog(r42.create());
    L6:
        return false;
    L8:
        if (notSelectedBot() == false) goto L13;
        if (r4 == false) goto L11;
        AlertDialog.Builder r43 = new AlertDialog.Builder(getParentActivity());
        r43.setTitle(LocaleController.getString(R.string.BusinessBotNoAddedTitle));
        r43.setMessage(LocaleController.getString(R.string.BusinessBotNoAddedText));
        r43.setPositiveButton(LocaleController.getString(R.string.BusinessBotNoAddedButton), new ChatbotsActivity$$ExternalSyntheticLambda3(this));
        r43.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r43.create());
    L11:
        return false;
    L13:
        return super.onBackPressed(r4);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        setValue();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }
}
