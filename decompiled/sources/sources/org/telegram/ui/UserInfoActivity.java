package org.telegram.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.NowPlayingInfoDTO;
import com.exteragram.messenger.api.model.NowPlayingServiceType;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.nowplaying.NowPlayingController;
import com.exteragram.messenger.nowplaying.ui.SetupNowPlayingActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
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
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.BusinessChatbotController;
import org.telegram.ui.Business.ChatbotsActivity;
import org.telegram.ui.Business.OpeningHoursActivity;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.IconBackgroundColors;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.SettingsActivity;

/* loaded from: classes6.dex */
public class UserInfoActivity extends UniversalFragment implements NotificationCenter.NotificationCenterDelegate {
    private final ArrayList<Integer> accountNumbers;

    @Keep
    public int addAccountRow;
    private EditTextCell bioEdit;
    private CharSequence bioInfo;
    private int bioInfoHash;

    @Keep
    public int bioRow;
    private TL_account.TL_birthday birthday;
    private CharSequence birthdayInfo;

    @Keep
    public int birthdayRow;
    private ArrayList<TL_account.TL_connectedBot> bots;
    private TLRPC.Chat channel;

    @Keep
    public int channelRow;
    private AdminedChannelsFetcher channels;
    private String currentBio;
    private TL_account.TL_birthday currentBirthday;
    private long currentChannel;
    private String currentFirstName;
    private String currentLastName;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private EditTextCell firstNameEdit;

    @Keep
    public int firstNameRow;
    private boolean hadHours;
    private boolean hadLocation;
    private EditTextCell lastNameEdit;

    @Keep
    public int lastNameRow;
    public UniversalRecyclerView listView;

    @Keep
    public int logoutRow;
    private NowPlayingServiceType nowPlayingService;

    @Keep
    public int numberRow;
    private int shiftDp;

    @Keep
    public int usernameRow;
    private boolean valueSet;
    private boolean wasSaved;

    /* renamed from: org.telegram.ui.UserInfoActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextCell {
        final /* synthetic */ UserInfoActivity this$0;

        public AnonymousClass1(UserInfoActivity r1, Context r2, String r3, boolean r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r1) {
            super.onTextChanged(r1);
            UserInfoActivity.m21095$$Nest$mcheckDone(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.UserInfoActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextCell {
        final /* synthetic */ UserInfoActivity this$0;

        public AnonymousClass2(UserInfoActivity r1, Context r2, String r3, boolean r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r1) {
            super.onTextChanged(r1);
            UserInfoActivity.m21095$$Nest$mcheckDone(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.UserInfoActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextCell {
        final /* synthetic */ UserInfoActivity this$0;

        public AnonymousClass3(UserInfoActivity r1, Context r2, String r3, boolean r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r2) {
            super.onTextChanged(r2);
            UserInfoActivity.m21095$$Nest$mcheckDone(this.this$0, true);
            UserInfoActivity.m21097$$Nest$mupdateBioInfo(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.UserInfoActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ UserInfoActivity this$0;

        public AnonymousClass4(UserInfoActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            UserInfoActivity.m21096$$Nest$mprocessDone(this.this$0, true);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    public static class AdminedChannelsFetcher {
        private ArrayList<Runnable> callbacks;
        public final ArrayList<TLRPC.Chat> chats;
        public final int currentAccount;
        public final boolean for_personal;
        public boolean loaded;
        public boolean loading;

        /* renamed from: $r8$lambda$I4-1mOL-oj8lOldSfqFLggIwlEQ, reason: not valid java name */
        public static /* synthetic */ void m21098$r8$lambda$I41mOLoj8lOldSfqFLggIwlEQ(AdminedChannelsFetcher r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$fetch$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$_zb193DAoVPahAdLLBfyz1rJQBQ(AdminedChannelsFetcher r0, TLObject r1) {
            r0.lambda$fetch$0(r1);
        }

        public AdminedChannelsFetcher(int r2, boolean r3) {
            this.chats = new ArrayList();
            this.callbacks = new ArrayList();
            this.currentAccount = r2;
            this.for_personal = r3;
        }

        private /* synthetic */ void lambda$fetch$0(TLObject r4) {
            if ((r4 instanceof TLRPC.messages_Chats) == false) goto L5;
            this.chats.clear();
            this.chats.addAll(((TLRPC.messages_Chats) r4).chats);
        L5:
            int r1 = 0;
            MessagesController.getInstance(this.currentAccount).putChats(this.chats, false);
            this.loading = false;
            this.loaded = true;
            ArrayList<Runnable> r42 = this.callbacks;
            int r0 = r42.size();
        L6:
            if (r1 >= r0) goto L8;
            Runnable r2 = r42.get(r1);
            r1 = r1 + 1;
            r2.run();
            goto L6
        L8:
            this.callbacks.clear();
        }

        private /* synthetic */ void lambda$fetch$1(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new UserInfoActivity$AdminedChannelsFetcher$$ExternalSyntheticLambda1(this, r1));
        }

        public void fetch() {
            if (this.loaded == false) goto L5;
            return;
        L5:
            if (this.loading == true) goto L10;
            this.loading = true;
            TLRPC.TL_channels_getAdminedPublicChannels r0 = new TLRPC.TL_channels_getAdminedPublicChannels();
            r0.for_personal = this.for_personal;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new UserInfoActivity$AdminedChannelsFetcher$$ExternalSyntheticLambda0(this));
            return;
        }

        public void invalidate() {
            this.loaded = false;
        }

        public void subscribe(Runnable r2) {
            if (this.loaded == false) goto L6;
            r2.run();
            return;
        L6:
            this.callbacks.add(r2);
        }
    }

    public static class ChooseChannelFragment extends UniversalFragment {
        private AdminedChannelsFetcher channels;
        private boolean invalidateAfterPause;
        private String query;
        private ActionBarMenuItem searchItem;
        private long selectedChannel;
        private Utilities.Callback<TLRPC.Chat> whenSelected;

        /* renamed from: org.telegram.ui.UserInfoActivity$ChooseChannelFragment$1, reason: invalid class name */
        public class AnonymousClass1 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
            final /* synthetic */ ChooseChannelFragment this$0;

            public AnonymousClass1(ChooseChannelFragment r1) {
                this.this$0 = r1;
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
            public void onSearchCollapse() {
                ChooseChannelFragment.m21099$$Nest$fputquery(this.this$0, null);
                UniversalRecyclerView r2 = this.this$0.listView;
                if (r2 == null) goto L6;
                r2.adapter.update(true);
                return;
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
            public void onSearchExpand() {
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
            public void onTextChanged(EditText r2) {
                ChooseChannelFragment.m21099$$Nest$fputquery(this.this$0, r2.getText().toString());
                UniversalRecyclerView r1 = this.this$0.listView;
                if (r1 == null) goto L6;
                r1.adapter.update(true);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$e6_WFh9WtUxT_LxysglE9pQunws(ChooseChannelFragment r0) {
            r0.lambda$onResume$1();
        }

        public static /* synthetic */ void $r8$lambda$pH4eKCFaH_olPMXeMTUkGv7fSyw(ChooseChannelFragment r0) {
            r0.lambda$new$0();
        }

        /* renamed from: -$$Nest$fputquery, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21099$$Nest$fputquery(ChooseChannelFragment r0, String r1) {
            r0.query = r1;
        }

        public ChooseChannelFragment(AdminedChannelsFetcher r2, long r3, Utilities.Callback<TLRPC.Chat> r5) {
            this.invalidateAfterPause = false;
            this.channels = r2;
            this.selectedChannel = r3;
            this.whenSelected = r5;
            r2.subscribe(new UserInfoActivity$ChooseChannelFragment$$ExternalSyntheticLambda1(this));
        }

        private /* synthetic */ void lambda$new$0() {
            UniversalRecyclerView r1 = this.listView;
            if (r1 == null) goto L6;
            r1.adapter.update(true);
            return;
        }

        private /* synthetic */ void lambda$onResume$1() {
            UniversalRecyclerView r1 = this.listView;
            if (r1 == null) goto L6;
            r1.adapter.update(true);
            return;
        }

        @Override // org.telegram.ui.Components.UniversalFragment, org.telegram.ui.ActionBar.BaseFragment
        public View createView(Context r5) {
            ActionBarMenuItem r0 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search, getResourceProvider()).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass1(this));
            this.searchItem = r0;
            r0.setSearchFieldHint(LocaleController.getString(R.string.Search));
            this.searchItem.setContentDescription(LocaleController.getString(R.string.Search));
            this.searchItem.setVisibility(8);
            super.createView(r5);
            this.listView.setSections();
            this.actionBar.setAdaptiveBackground(this.listView);
            return this.fragmentView;
        }

        @Override // org.telegram.ui.Components.UniversalFragment
        public void fillItems(ArrayList<UItem> r14, UniversalAdapter r15) {
            if (TextUtils.isEmpty(this.query) == false) goto L8;
            if (this.selectedChannel == 0) goto L8;
            r14.add(UItem.asButton(1, R.drawable.msg_archive_hide, LocaleController.getString(R.string.EditProfileChannelHide)).red());
            r14.add(UItem.asShadow(null));
        L8:
            if (TextUtils.isEmpty(this.query) == false) goto L10;
            r14.add(UItem.asHeader(LocaleController.getString(R.string.EditProfileChannelSelect)));
        L10:
            ArrayList<TLRPC.Chat> r152 = this.channels.chats;
            int r2 = r152.size();
            int r3 = 0;
            int r4 = 0;
            int r5 = 0;
        L11:
            if (r5 >= r2) goto L34;
            TLRPC.Chat r6 = r152.get(r5);
            r5 = r5 + 1;
            TLRPC.Chat r62 = r6;
            if (r62 == null) goto L11;
            if (ChatObject.isMegagroup(r62) == true) goto L11;
            r4 = r4 + 1;
            if (TextUtils.isEmpty(this.query) == true) goto L28;
            String r7 = this.query.toLowerCase();
            String r8 = AndroidUtilities.translitSafe(r7);
            String r9 = r62.title.toLowerCase();
            String r10 = AndroidUtilities.translitSafe(r9);
            if (r9.startsWith(r7) == true) goto L28;
            if (r9.contains(" " + r7) == true) goto L28;
            if (r10.startsWith(r8) == true) goto L28;
            if (r10.contains(" " + r8) == false) goto L11;
        L28:
            UItem r72 = UItem.asFilterChat(true, -r62.id);
            if (this.selectedChannel != r62.id) goto L31;
            boolean r63 = true;
        L32:
            r14.add(r72.setChecked(r63));
            goto L11
        L31:
            r63 = false;
            goto L32
        L34:
            if (TextUtils.isEmpty(this.query) == false) goto L37;
            if (r4 != 0) goto L37;
            r14.add(UItem.asButton(2, R.drawable.msg_channel_create, LocaleController.getString(R.string.EditProfileChannelStartNew)).accent());
        L37:
            r14.add(UItem.asShadow(null));
            ActionBarMenuItem r13 = this.searchItem;
            if (r13 != null) goto L40;
            return;
        L40:
            if (r4 > 5) goto L43;
            r3 = 8;
        L43:
            r13.setVisibility(r3);
        }

        @Override // org.telegram.ui.Components.UniversalFragment
        public CharSequence getTitle() {
            return LocaleController.getString(R.string.EditProfileChannelTitle);
        }

        @Override // org.telegram.ui.Components.UniversalFragment
        public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
            int r22 = r1.id;
            if (r22 != 1) goto L7;
            this.whenSelected.run(null);
            finishFragment();
            return;
        L7:
            if (r22 != 2) goto L17;
            this.invalidateAfterPause = true;
            SharedPreferences r12 = MessagesController.getGlobalMainSettings();
            if (BuildVars.DEBUG_VERSION == false) goto L11;
        L14:
            presentFragment(new ActionIntroActivity(0));
            r12.edit().putBoolean("channel_intro", true).apply();
            return;
        L11:
            if (r12.getBoolean("channel_intro", false) == false) goto L14;
            Bundle r13 = new Bundle();
            r13.putInt("step", 0);
            presentFragment(new ChannelCreateActivity(r13));
            return;
        L17:
            if (r1.viewType != 12) goto L20;
            finishFragment();
            this.whenSelected.run(getMessagesController().getChat(Long.valueOf(-r1.dialogId)));
            return;
        }

        @Override // org.telegram.ui.Components.UniversalFragment
        public boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onResume() {
            super.onResume();
            if (this.invalidateAfterPause == false) goto L6;
            this.channels.invalidate();
            this.channels.subscribe(new UserInfoActivity$ChooseChannelFragment$$ExternalSyntheticLambda0(this));
            this.invalidateAfterPause = false;
            return;
        }
    }

    public static class InfoCell extends LinearLayout {
        private boolean accent;
        private final ImageView icon2View;
        private final ImageView iconView;
        private boolean red;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView subtitleView;
        private final LinearLayout textLayout;
        private final TextView titleView;

        public static class Factory extends UItem.UItemFactory<InfoCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(int r1, int r2, CharSequence r3, CharSequence r4, int r5) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.iconResId = r2;
                r0.text = r3;
                r0.subtext = r4;
                r0.intValue = r5;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r8, UItem r9, boolean r10, UniversalAdapter r11, UniversalRecyclerView r12) {
                ((InfoCell) r8).set(r9.iconResId, r9.text, r9.subtext, r9.accent, r9.red, r9.intValue);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public InfoCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new InfoCell(r1, r5);
            }
        }

        public InfoCell(Context r14, Theme.ResourcesProvider r15) {
            super(r14);
            setOrientation(0);
            this.resourcesProvider = r15;
            ImageView r152 = new ImageView(r14);
            this.iconView = r152;
            ImageView.ScaleType r1 = ImageView.ScaleType.CENTER;
            r152.setScaleType(r1);
            addView(r152, LayoutHelper.createLinear(40, 40, 19, 12, 0, 12, 0));
            LinearLayout r153 = new LinearLayout(r14);
            this.textLayout = r153;
            r153.setOrientation(1);
            r153.setPadding(0, AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f));
            addView(r153, LayoutHelper.createLinear(0, -2, 1.0f, 23, 0, 0, 32, 0));
            TextView r0 = new TextView(r14);
            this.titleView = r0;
            r0.setTextSize(1, 16.0f);
            r153.addView(r0, LayoutHelper.createLinear(-1, -2, 7, 0, 0, 0, 0));
            TextView r02 = new TextView(r14);
            this.subtitleView = r02;
            r02.setTextSize(1, 13.0f);
            r153.addView(r02, LayoutHelper.createLinear(-1, -2, 7, 0.0f, 4.33f, 0.0f, 0.0f));
            ImageView r154 = new ImageView(r14);
            this.icon2View = r154;
            r154.setScaleType(r1);
            addView(r154, LayoutHelper.createLinear(40, 40, 21, 12, 0, 12, 0));
        }

        private void updateColors() {
            ImageView r0 = this.iconView;
            if (this.red == false) goto L6;
            int r2 = Theme.key_text_RedBold;
        L9:
            int r22 = Theme.getColor(r2, this.resourcesProvider);
            PorterDuff.Mode r3 = PorterDuff.Mode.SRC_IN;
            r0.setColorFilter(new PorterDuffColorFilter(r22, r3));
            ImageView r02 = this.icon2View;
            if (this.red == false) goto L13;
            int r23 = Theme.key_text_RedBold;
        L16:
            r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r23, this.resourcesProvider), r3));
            TextView r03 = this.titleView;
            if (this.red == false) goto L20;
            int r1 = Theme.key_text_RedRegular;
        L23:
            r03.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            TextView r04 = this.subtitleView;
            if (this.red == false) goto L27;
            int r12 = Theme.key_text_RedRegular;
        L30:
            r04.setTextColor(Theme.getColor(r12, this.resourcesProvider));
            return;
        L27:
            if (this.accent == false) goto L29;
            r12 = Theme.key_windowBackgroundWhiteBlueText;
            goto L30
        L29:
            r12 = Theme.key_windowBackgroundWhiteGrayText;
            goto L30
        L20:
            if (this.accent == false) goto L22;
            r1 = Theme.key_windowBackgroundWhiteBlueText;
            goto L23
        L22:
            r1 = Theme.key_windowBackgroundWhiteBlackText;
            goto L23
        L13:
            if (this.accent == false) goto L15;
            r23 = Theme.key_windowBackgroundWhiteBlueText;
            goto L16
        L15:
            r23 = Theme.key_windowBackgroundWhiteBlackText;
            goto L16
        L6:
            if (this.accent == false) goto L8;
            r2 = Theme.key_windowBackgroundWhiteBlueText;
            goto L9
        L8:
            r2 = Theme.key_windowBackgroundWhiteBlackText;
            goto L9
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(int r1, CharSequence r2, CharSequence r3, boolean r4, boolean r5, int r6) {
            this.accent = r4;
            this.red = r5;
            this.iconView.setImageResource(r1);
            ImageView r12 = this.icon2View;
            int r42 = 8;
            if (r6 == 0) goto L5;
            r12.setVisibility(0);
            this.icon2View.setImageResource(r6);
        L6:
            this.titleView.setText(r2);
            this.subtitleView.setText(r3);
            TextView r13 = this.subtitleView;
            if (TextUtils.isEmpty(r3) == true) goto L10;
            r42 = 0;
        L10:
            r13.setVisibility(r42);
            if (TextUtils.isEmpty(r3) == false) goto L13;
            float r14 = 15.0f;
        L14:
            int r15 = AndroidUtilities.dp(r14);
            this.textLayout.setPadding(0, r15, 0, r15);
            updateColors();
            return;
        L13:
            r14 = 10.0f;
            goto L14
        L5:
            r12.setVisibility(8);
            goto L6
        }
    }

    /* renamed from: $r8$lambda$-EEdZr-LoaNfePj9c9wxBiufMU0, reason: not valid java name */
    public static /* synthetic */ void m21092$r8$lambda$EEdZrLoaNfePj9c9wxBiufMU0(UserInfoActivity r0, TLObject r1, TL_account.TL_birthday r2, TLRPC.UserFull r3, int[] r4, ArrayList r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$processDone$10(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$2CdBzJk573FimflIbSWieEAV0Lk(UserInfoActivity r0) {
        r0.lambda$fillItems$2();
    }

    public static /* synthetic */ void $r8$lambda$GBlCghvrLhS0OaN06YExsnJpiVM(UserInfoActivity r0) {
        r0.lambda$fillItems$1();
    }

    public static /* synthetic */ void $r8$lambda$Jmg5919uLMlzviCnSkp7aLpXUt8(UserInfoActivity r0, TLRPC.TL_error r1, TLObject r2, TL_account.TL_birthday r3, TLRPC.UserFull r4, TLObject r5, int[] r6, ArrayList r7) {
        r0.lambda$processDone$9(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ int $r8$lambda$KOVUqJtbyR3NKG4ztFlfsPzpfV8(Integer r2, Integer r3) {
        long r0 = UserConfig.getInstance(r2.intValue()).loginTime;
        long r22 = UserConfig.getInstance(r3.intValue()).loginTime;
        if (r0 <= r22) goto L6;
        return 1;
    L6:
        if (r0 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$KmyMs3ocGP4iobkrBf9bRPwY_lU(UserInfoActivity r0, TLRPC.Chat r1) {
        r0.lambda$onClick$4(r1);
    }

    /* renamed from: $r8$lambda$Ldf9NtjiRsqLAx-ODHDibL3i6rE, reason: not valid java name */
    public static /* synthetic */ void m21093$r8$lambda$Ldf9NtjiRsqLAxODHDibL3i6rE(UserInfoActivity r0) {
        r0.lambda$onResume$6();
    }

    public static /* synthetic */ void $r8$lambda$aE5xcc_cc9DC_6GpfSgO2Yp2vjw(UserInfoActivity r0) {
        r0.openBioSettings();
    }

    public static /* synthetic */ void $r8$lambda$eaGTHGal9S4xtYMaarf3R7G1hkk(UserInfoActivity r0, TL_account.TL_birthday r1) {
        r0.lambda$onClick$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$sgu5f246btwmNdnBA7IhjpT67H4(UserInfoActivity r0, String r1, int r2) {
        r0.lambda$onLongClick$5(r1, r2);
    }

    /* renamed from: $r8$lambda$y-NEMVPDY_0sr5BiiMuPyJgRBOk, reason: not valid java name */
    public static /* synthetic */ void m21094$r8$lambda$yNEMVPDY_0sr5BiiMuPyJgRBOk(UserInfoActivity r0, NowPlayingInfoDTO r1) {
        r0.lambda$setValue$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$zFX0ey6eaT3ttfENyVnay4qJ2Co(UserInfoActivity r0, NowPlayingInfoDTO r1) {
        r0.lambda$setValue$7(r1);
    }

    /* renamed from: -$$Nest$mcheckDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21095$$Nest$mcheckDone(UserInfoActivity r0, boolean r1) {
        r0.checkDone(r1);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21096$$Nest$mprocessDone(UserInfoActivity r0, boolean r1) {
        r0.processDone(r1);
    }

    /* renamed from: -$$Nest$mupdateBioInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21097$$Nest$mupdateBioInfo(UserInfoActivity r0) {
        r0.updateBioInfo();
    }

    public UserInfoActivity() {
        this.bioInfoHash = Integer.MIN_VALUE;
        this.bots = new ArrayList();
        this.accountNumbers = new ArrayList();
        this.channels = new AdminedChannelsFetcher(this.currentAccount, true);
        this.wasSaved = false;
        this.shiftDp = -4;
    }

    public static String birthdayString(TL_account.TL_birthday r5) {
        if (r5 != null) goto L6;
        return "—";
    L6:
        if ((r5.flags & 1) == 0) goto L9;
        Calendar r0 = Calendar.getInstance();
        r0.set(1, r5.year);
        r0.set(2, r5.month - 1);
        r0.set(5, r5.day);
        return LocaleController.getInstance().getFormatterBoostExpired().format(r0.getTimeInMillis());
    L9:
        Calendar r02 = Calendar.getInstance();
        r02.set(2, r5.month - 1);
        r02.set(5, r5.day);
        return LocaleController.getInstance().getFormatterDayMonth().format(r02.getTimeInMillis());
    }

    public static boolean birthdaysEqual(TL_account.TL_birthday r4, TL_account.TL_birthday r5) {
        if (r4 != null) goto L5;
        boolean r2 = true;
    L6:
        if (r5 == null) goto L8;
        boolean r3 = true;
    L9:
        if (r2 == r3) goto L18;
        if (r4 != null) goto L12;
    L17:
        return true;
    L12:
        if (r4.day != r5.day) goto L18;
        if (r4.month != r5.month) goto L18;
        if (r4.year == r5.year) goto L17;
    L18:
        return false;
    L8:
        r3 = false;
        goto L9
    L5:
        r2 = false;
        goto L6
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

    private /* synthetic */ void lambda$fillItems$1() {
        presentFragment(new PrivacyControlActivity(11));
    }

    private /* synthetic */ void lambda$fillItems$2() {
        presentFragment(new PremiumPreviewFragment("add_account"));
    }

    private /* synthetic */ void lambda$onClick$3(TL_account.TL_birthday r2) {
        this.birthday = r2;
        UniversalRecyclerView r22 = this.listView;
        if (r22 == null) goto L5;
        r22.adapter.update(true);
    L5:
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$4(TLRPC.Chat r3) {
        if (this.channel == r3) goto L13;
        this.channel = r3;
        if (r3 == null) goto L8;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.EditProfileChannelSet)).show();
    L8:
        checkDone(true);
        UniversalRecyclerView r2 = this.listView;
        if (r2 == null) goto L12;
        r2.adapter.update(true);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$onLongClick$5(String r1, int r2) {
        AndroidUtilities.addToClipboard(r1);
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L6;
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(r2)).show();
        return;
    }

    private /* synthetic */ void lambda$onResume$6() {
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L6;
        r1.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$processDone$10(TLObject r10, TL_account.TL_birthday r11, TLRPC.UserFull r12, int[] r13, ArrayList r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new UserInfoActivity$$ExternalSyntheticLambda4(this, r16, r10, r11, r12, r15, r13, r14));
    }

    private /* synthetic */ void lambda$processDone$9(TLRPC.TL_error r3, TLObject r4, TL_account.TL_birthday r5, TLRPC.UserFull r6, TLObject r7, int[] r8, ArrayList r9) {
        if (r3 == null) goto L21;
        this.doneButtonDrawable.animateToProgress(0.0f);
        boolean r42 = r4 instanceof TL_account.updateBirthday;
        if (r42 == false) goto L13;
        String r72 = r3.text;
        if (r72 == null) goto L13;
        if (r72.startsWith("FLOOD_WAIT_") == false) goto L13;
        if (getContext() == null) goto L14;
        showDialog(new AlertDialog.Builder(getContext(), this.resourceProvider).setTitle(LocaleController.getString(R.string.PrivacyBirthdayTooOftenTitle)).setMessage(LocaleController.getString(R.string.PrivacyBirthdayTooOftenMessage)).setPositiveButton(LocaleController.getString(R.string.OK), null).create());
    L14:
        if (r42 == false) goto L31;
        if (r5 == null) goto L17;
        r6.flags |= 32;
    L18:
        r6.birthday = r5;
        getMessagesStorage().updateUserInfo(r6, false);
        return;
    L17:
        r6.flags &= -33;
        goto L18
    L31:
        return;
    L13:
        BulletinFactory.showError(r3);
        goto L14
    L21:
        if ((r7 instanceof TLRPC.TL_boolFalse) == false) goto L24;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L24:
        this.wasSaved = true;
        int r43 = r8[0] + 1;
        r8[0] = r43;
        if (r43 == r9.size()) goto L27;
        return;
    L27:
        if (ExteraConfig.getTitleText() != 2) goto L29;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.currentUserPremiumStatusChanged, new Object[0]);
    L29:
        finishFragment();
    }

    private /* synthetic */ void lambda$setValue$7(NowPlayingInfoDTO r1) {
        if (r1 != null) goto L4;
        NowPlayingServiceType r12 = null;
    L5:
        this.nowPlayingService = r12;
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L9;
        r0.adapter.update(true);
        return;
    L9:
        return;
    L4:
        r12 = r1.getServiceType();
        goto L5
    }

    private /* synthetic */ void lambda$setValue$8(NowPlayingInfoDTO r2) {
        AndroidUtilities.runOnUIThread(new UserInfoActivity$$ExternalSyntheticLambda9(this, r2), 100);
    }

    private void openBioSettings() {
        presentFragment(new PrivacyControlActivity(9, true));
    }

    private void processDone(boolean r13) {
        if (this.doneButtonDrawable.getProgress() > 0.0f) goto L60;
        if (r13 == true) goto L7;
    L10:
        this.doneButtonDrawable.animateToProgress(1.0f);
        TLRPC.User r132 = getUserConfig().getCurrentUser();
        TLRPC.UserFull r7 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r132 == null) goto L59;
        if (r7 == null) goto L61;
        ArrayList r9 = new ArrayList();
        if (TextUtils.isEmpty(this.firstNameEdit.getText()) == false) goto L17;
    L27:
        TL_account.TL_birthday r6 = r7.birthday;
        if (birthdaysEqual(this.currentBirthday, this.birthday) == true) goto L34;
        TL_account.updateBirthday r133 = new TL_account.updateBirthday();
        TL_account.TL_birthday r2 = this.birthday;
        int r3 = r7.flags2;
        if (r2 == null) goto L32;
        r7.flags2 = r3 | 32;
        r7.birthday = r2;
        r133.flags |= 1;
        r133.birthday = r2;
    L33:
        r9.add(r133);
        getMessagesController().invalidateContentSettings();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.premiumPromoUpdated, new Object[0]);
        goto L34
    L32:
        r7.flags2 = r3 & (-33);
        r7.birthday = null;
    L34:
        long r22 = this.currentChannel;
        TLRPC.Chat r134 = this.channel;
        if (r134 == null) goto L37;
        long r10 = r134.id;
    L39:
        if (r22 == r10) goto L49;
        TL_account.updatePersonalChannel r135 = new TL_account.updatePersonalChannel();
        r135.channel = MessagesController.getInputChannel(this.channel);
        TLRPC.Chat r23 = this.channel;
        int r32 = r7.flags;
        if (r23 == null) goto L46;
        r7.flags = r32 | 64;
        long r33 = r7.personal_channel_id;
        long r102 = r23.id;
        if (r33 == r102) goto L45;
        r7.personal_channel_message = 0;
    L45:
        r7.personal_channel_id = r102;
    L47:
        r9.add(r135);
        goto L49
    L46:
        r7.flags = r32 & (-65);
        r7.personal_channel_message = 0;
        r7.personal_channel_id = 0;
    L49:
        if (r9.isEmpty() == false) goto L52;
        finishFragment();
        return;
    L52:
        int[] r8 = {0};
        int r136 = 0;
    L54:
        if (r136 >= r9.size()) goto L56;
        TLObject r5 = (TLObject) r9.get(r136);
        UserInfoActivity r4 = this;
        this.getConnectionsManager().sendRequest(r5, new UserInfoActivity$$ExternalSyntheticLambda3(r4, r5, r6, r7, r8, r9), 1024);
        r136 = r136 + 1;
        UserInfoActivity this = r4;
        goto L54
    L56:
        UserInfoActivity r42 = this;
        r42.getMessagesStorage().updateUserInfo(r7, false);
        r42.getUserConfig().saveConfig(true);
        NotificationCenter.getInstance(r42.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        NotificationCenter.getInstance(r42.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
        return;
    L37:
        r10 = 0;
        goto L39
    L17:
        if (TextUtils.equals(this.currentFirstName, this.firstNameEdit.getText().toString()) == true) goto L19;
    L22:
        TL_account.updateProfile r0 = new TL_account.updateProfile();
        r0.flags |= 1;
        String r24 = this.firstNameEdit.getText().toString();
        r132.first_name = r24;
        r0.first_name = r24;
        r0.flags |= 2;
        String r25 = this.lastNameEdit.getText().toString();
        r132.last_name = r25;
        r0.last_name = r25;
        r0.flags |= 4;
        String r137 = this.bioEdit.getText().toString();
        r7.about = r137;
        r0.about = r137;
        boolean r138 = TextUtils.isEmpty(r137);
        int r26 = r7.flags;
        if (r138 == false) goto L25;
        int r139 = r26 & (-3);
    L26:
        r7.flags = r139;
        r9.add(r0);
        goto L27
    L25:
        r139 = r26 | 2;
        goto L26
    L19:
        if (TextUtils.equals(this.currentLastName, this.lastNameEdit.getText().toString()) == false) goto L22;
        if (TextUtils.equals(this.currentBio, this.bioEdit.getText().toString()) == true) goto L27;
    L61:
        return;
    L59:
        return;
    L7:
        if (TextUtils.isEmpty(this.firstNameEdit.getText()) == false) goto L10;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        EditTextCell r1310 = this.firstNameEdit;
        int r02 = -this.shiftDp;
        this.shiftDp = r02;
        AndroidUtilities.shakeViewSpring(r1310, r02);
        return;
    }

    private void setValue() {
        if (this.valueSet == true) goto L32;
        TLRPC.UserFull r0 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r0 != null) goto L9;
        getMessagesController().loadUserInfo(getUserConfig().getCurrentUser(), true, getClassGuid());
        return;
    L9:
        TLRPC.User r2 = r0.user;
        if (r2 != null) goto L12;
        r2 = getUserConfig().getCurrentUser();
    L12:
        if (r2 != null) goto L14;
        return;
    L14:
        EditTextCell r3 = this.firstNameEdit;
        String r4 = r2.first_name;
        this.currentFirstName = r4;
        r3.setText(r4);
        EditTextCell r32 = this.lastNameEdit;
        String r22 = r2.last_name;
        this.currentLastName = r22;
        r32.setText(r22);
        EditTextCell r23 = this.bioEdit;
        String r33 = r0.about;
        this.currentBio = r33;
        r23.setText(r33);
        TL_account.TL_birthday r24 = r0.birthday;
        this.currentBirthday = r24;
        this.birthday = r24;
        if ((r0.flags2 & 64) == 0) goto L17;
        this.currentChannel = r0.personal_channel_id;
        this.channel = getMessagesController().getChat(Long.valueOf(this.currentChannel));
    L18:
        boolean r34 = false;
        if (r0.business_work_hours == null) goto L21;
        boolean r25 = true;
    L22:
        this.hadHours = r25;
        if (r0.business_location == null) goto L25;
        r34 = true;
    L25:
        this.hadLocation = r34;
        NowPlayingController.getNowPlayingInfo(new UserInfoActivity$$ExternalSyntheticLambda0(this));
        checkDone(true);
        UniversalRecyclerView r02 = this.listView;
        if (r02 == null) goto L30;
        UniversalAdapter r03 = r02.adapter;
        if (r03 == null) goto L30;
        r03.update(true);
    L30:
        this.valueSet = true;
        return;
    L21:
        r25 = false;
        goto L22
    L17:
        this.currentChannel = 0;
        this.channel = null;
        goto L18
    }

    private void updateAccounts() {
        this.accountNumbers.clear();
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L11;
        if (UserConfig.getInstance(r0).isClientActivated() == false) goto L10;
        if (this.currentAccount == r0) goto L10;
        this.accountNumbers.add(Integer.valueOf(r0));
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        Collections.sort(this.accountNumbers, new UserInfoActivity$$ExternalSyntheticLambda11());
    }

    private void updateBioInfo() {
        int r0 = this.bioInfoHash;
        EditTextCell r1 = this.bioEdit;
        int r2 = 0;
        if (r1 != null) goto L5;
    L89:
        this.bioInfo = LocaleController.getString(R.string.EditProfileBioInfo2);
        this.bioInfoHash = Objects.hash(new Object[]{0});
    L91:
        if (r0 == this.bioInfoHash) goto L109;
        UniversalRecyclerView r15 = this.listView;
        if (r15 == null) goto L110;
        r15.adapter.update(true);
        return;
    L110:
        return;
    L109:
        return;
    L5:
        if (TextUtils.isEmpty(r1.getText()) == true) goto L89;
        ArrayList<TLRPC.PrivacyRule> r12 = getContactsController().getPrivacyRules(9);
        if (r12 != null) goto L9;
        this.bioInfo = LocaleController.getString(R.string.Loading);
        this.bioInfoHash = Objects.hash(new Object[]{1});
        goto L91
    L9:
        int r5 = 0;
        int r7 = 0;
        int r8 = 0;
        boolean r9 = false;
        char r6 = 65535;
    L11:
        if (r5 >= r12.size()) goto L52;
        TLRPC.PrivacyRule r10 = r12.get(r5);
        if ((r10 instanceof TLRPC.TL_privacyValueAllowChatParticipants) == false) goto L21;
        TLRPC.TL_privacyValueAllowChatParticipants r102 = (TLRPC.TL_privacyValueAllowChatParticipants) r10;
        int r11 = r102.chats.size();
        int r122 = 0;
    L15:
        if (r122 >= r11) goto L51;
        TLRPC.Chat r13 = getMessagesController().getChat(r102.chats.get(r122));
        if (r13 == null) goto L19;
        r8 = r8 + Math.max(0, r13.participants_count - 1);
    L19:
        r122 = r122 + 1;
    L51:
        r5 = r5 + 1;
        goto L11
    L21:
        if ((r10 instanceof TLRPC.TL_privacyValueDisallowChatParticipants) == false) goto L29;
        TLRPC.TL_privacyValueDisallowChatParticipants r103 = (TLRPC.TL_privacyValueDisallowChatParticipants) r10;
        int r112 = r103.chats.size();
        int r123 = 0;
    L23:
        if (r123 >= r112) goto L51;
        TLRPC.Chat r132 = getMessagesController().getChat(r103.chats.get(r123));
        if (r132 == null) goto L27;
        r7 = r7 + Math.max(0, r132.participants_count - 1);
    L27:
        r123 = r123 + 1;
        goto L23
    L29:
        if ((r10 instanceof TLRPC.TL_privacyValueAllowUsers) == false) goto L32;
        r8 = r8 + ((TLRPC.TL_privacyValueAllowUsers) r10).users.size();
        goto L51
    L32:
        if ((r10 instanceof TLRPC.TL_privacyValueDisallowUsers) == false) goto L34;
        r7 = r7 + ((TLRPC.TL_privacyValueDisallowUsers) r10).users.size();
        goto L51
    L34:
        boolean r124 = r10 instanceof TLRPC.TL_privacyValueAllowAll;
        if (r124 == false) goto L37;
    L36:
        r6 = 0;
        goto L51
    L37:
        boolean r133 = r10 instanceof TLRPC.TL_privacyValueDisallowAll;
        if (r133 == false) goto L42;
        if (r9 == true) goto L42;
    L40:
        r6 = 1;
    L42:
        if ((r10 instanceof TLRPC.TL_privacyValueAllowContacts) == false) goto L45;
        r9 = true;
    L44:
        r6 = 2;
        goto L51
    L45:
        if (r6 != 65535) goto L51;
        if (r124 == true) goto L36;
        if (r133 == false) goto L44;
        if (r9 == true) goto L44;
    L52:
        if (r6 == 0) goto L66;
        if (r6 != 65535) goto L55;
        if (r7 > 0) goto L66;
    L55:
        if (r6 == 2) goto L65;
        if (r6 != 65535) goto L60;
        if (r7 <= 0) goto L60;
        if (r8 > 0) goto L65;
    L60:
        if (r6 == 1) goto L64;
        if (r6 != 65535) goto L63;
        if (r8 > 0) goto L64;
    L63:
        r2 = -1;
    L64:
        r2 = 1;
    L65:
        r2 = 2;
    L66:
        if (r2 != 0) goto L70;
        if (r7 > 0) goto L69;
        this.bioInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.EditProfileBioInfoEveryone), new UserInfoActivity$$ExternalSyntheticLambda1(this)), true);
    L88:
        this.bioInfoHash = Objects.hash(new Object[]{Integer.valueOf(r2 + 10), Integer.valueOf(r8), Integer.valueOf(r7)});
        goto L91
    L69:
        this.bioInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.EditProfileBioInfoEveryoneExcept, new Object[]{Integer.valueOf(r7)}), new UserInfoActivity$$ExternalSyntheticLambda1(this)), true);
        goto L88
    L70:
        if (r2 != 2) goto L83;
        if (r7 > 0) goto L74;
        if (r8 > 0) goto L74;
        this.bioInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.EditProfileBioInfoContacts), new UserInfoActivity$$ExternalSyntheticLambda1(this)), true);
    L74:
        if (r8 <= 0) goto L76;
        String r14 = "+" + r8;
    L77:
        if (r7 > 0) goto L79;
    L82:
        this.bioInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.EditProfileBioInfoContactsExtra, new Object[]{r14}), new UserInfoActivity$$ExternalSyntheticLambda1(this)), true);
        goto L88
    L79:
        if (r14.length() <= 0) goto L81;
        r14 = r14.concat(", ");
    L81:
        r14 = r14 + "-" + r7;
        goto L82
    L76:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L77
    L83:
        if (r2 != 0) goto L87;
        if (r8 > 0) goto L86;
        this.bioInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.EditProfileBioInfoNobody), new UserInfoActivity$$ExternalSyntheticLambda1(this)), true);
        goto L88
    L86:
        this.bioInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.EditProfileBioInfoNobodyExcept, new Object[]{Integer.valueOf(r8)}), new UserInfoActivity$$ExternalSyntheticLambda1(this)), true);
        goto L88
    L87:
        this.bioInfo = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.EditProfileBioInfoUnknown), new UserInfoActivity$$ExternalSyntheticLambda1(this));
        goto L88
    }

    @Override // org.telegram.ui.Components.UniversalFragment, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        AnonymousClass1 r0 = new AnonymousClass1(this, r10, LocaleController.getString(R.string.EditProfileFirstName), false, false, -1, this.resourceProvider);
        this.firstNameEdit = r0;
        r0.setDivider(true);
        this.firstNameEdit.hideKeyboardOnEnter();
        AnonymousClass2 r02 = new AnonymousClass2(this, r10, LocaleController.getString(R.string.EditProfileLastName), false, false, -1, this.resourceProvider);
        this.lastNameEdit = r02;
        r02.hideKeyboardOnEnter();
        AnonymousClass3 r03 = new AnonymousClass3(this, r10, LocaleController.getString(R.string.EditProfileBioHint2), true, false, getMessagesController().getAboutLimit(), this.resourceProvider);
        this.bioEdit = r03;
        r03.setShowLimitWhenEmpty(true);
        updateBioInfo();
        this.bioInfo = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.EditProfileBioInfo2), new UserInfoActivity$$ExternalSyntheticLambda1(this));
        super.createView(r10);
        UniversalRecyclerView r04 = this.listView;
        this.listView = r04;
        r04.setSections();
        this.listView.setClipToPadding(false);
        this.actionBar.setAdaptiveBackground(this.listView);
        INavigationLayout r05 = this.parentLayout;
        if (r05 != null) goto L5;
    L7:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass4(this));
        Drawable r06 = r10.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r4 = Theme.key_actionBarDefaultIcon;
        r06.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r06, new CircularProgressDrawable(Theme.getColor(r4)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        setValue();
        return this.fragmentView;
    L5:
        if (r05.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.userInfoDidLoad) goto L7;
        setValue();
        return;
    L7:
        if (r2 != NotificationCenter.updateInterfaces) goto L13;
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L39;
        r1.adapter.update(true);
        return;
    L39:
        return;
    L13:
        if (r2 == NotificationCenter.privacyRulesUpdated) goto L30;
        if (r2 == NotificationCenter.nowPlayingUpdated) goto L30;
        if (r2 != NotificationCenter.updatedChatbot) goto L40;
        TL_account.connectedBots r22 = BusinessChatbotController.getInstance(this.currentAccount).getValue();
        if (r22 == null) goto L24;
        ArrayList<TL_account.TL_connectedBot> r23 = r22.connected_bots;
        if (r23 == null) goto L24;
    L25:
        this.bots = r23;
        UniversalRecyclerView r12 = this.listView;
        if (r12 == null) goto L41;
        r12.adapter.update(true);
        return;
    L41:
        return;
    L24:
        r23 = new ArrayList();
        goto L25
    L40:
        return;
    L30:
        if (r2 != NotificationCenter.nowPlayingUpdated) goto L34;
        Object r24 = r4[0];
        if ((r24 instanceof NowPlayingServiceType) == false) goto L35;
        this.nowPlayingService = (NowPlayingServiceType) r24;
    L35:
        UniversalRecyclerView r13 = this.listView;
        if (r13 == null) goto L42;
        r13.adapter.update(true);
        return;
    L42:
        return;
    L34:
        updateBioInfo();
        goto L35
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void fillItems(ArrayList<UItem> r13, UniversalAdapter r14) {
        this.addAccountRow = -1;
        this.numberRow = -1;
        updateAccounts();
        r13.add(UItem.asHeader(LocaleController.getString(R.string.EditProfileName)));
        this.firstNameRow = r13.size();
        r13.add(UItem.asCustom(this.firstNameEdit));
        this.lastNameRow = r13.size();
        r13.add(UItem.asCustom(this.lastNameEdit));
        r13.add(UItem.asShadow(-1, null));
        this.bioRow = r13.size();
        r13.add(UItem.asCustom(this.bioEdit));
        r13.add(UItem.asShadow(this.bioInfo));
        TLRPC.User r142 = getUserConfig().getCurrentUser();
        r13.add(UItem.asHeader(LocaleController.getString(R.string.EditAccountInfoHeader)));
        if (r142 == null) goto L5;
        this.numberRow = r13.size();
        IconBackgroundColors r1 = IconBackgroundColors.GREEN;
        r13.add(SettingsActivity.SettingCell.Factory.of(7, r1.top, r1.bottom, R.drawable.settings_calls, PhoneFormat.getInstance().format("+" + r142.phone), LocaleController.getString(R.string.TapToChangePhone)));
    L5:
        this.usernameRow = r13.size();
        if (UserObject.getPublicUsername(r142) == null) goto L8;
        IconBackgroundColors r12 = IconBackgroundColors.ORANGE;
        r13.add(SettingsActivity.SettingCell.Factory.of(8, r12.top, r12.bottom, R.drawable.filled_chatlist_mention, "@" + UserObject.getPublicUsername(r142), LocaleController.getString(R.string.Username)));
    L9:
        this.birthdayRow = r13.size();
        TL_account.TL_birthday r143 = this.birthday;
        if (r143 == null) goto L12;
        IconBackgroundColors r15 = IconBackgroundColors.BLUE;
        r13.add(SettingsActivity.SettingCell.Factory.of(9, r15.top, r15.bottom, R.drawable.filled_birthday, birthdayString(r143), LocaleController.getString(R.string.ContactBirthday)));
    L14:
        if (getContactsController().getLoadingPrivacyInfo(11) == true) goto L34;
        ArrayList<TLRPC.PrivacyRule> r144 = getContactsController().getPrivacyRules(11);
        if (r144 == null) goto L34;
        if (this.birthdayInfo != null) goto L34;
        String r4 = LocaleController.getString(R.string.EditProfileBirthdayInfoContacts);
        if (r144.isEmpty() == true) goto L33;
        int r5 = 0;
    L23:
        if (r5 >= r144.size()) goto L33;
        if ((r144.get(r5) instanceof TLRPC.TL_privacyValueAllowContacts) == true) goto L26;
        if ((r144.get(r5) instanceof TLRPC.TL_privacyValueAllowAll) == false) goto L30;
    L31:
        r4 = LocaleController.getString(R.string.EditProfileBirthdayInfo);
    L32:
        r5 = r5 + 1;
        goto L23
    L30:
        if ((r144.get(r5) instanceof TLRPC.TL_privacyValueDisallowAll) == false) goto L32;
    L26:
        r4 = LocaleController.getString(R.string.EditProfileBirthdayInfoContacts);
    L33:
        this.birthdayInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(r4, new UserInfoActivity$$ExternalSyntheticLambda7(this)), true);
    L34:
        r13.add(UItem.asShadow(this.birthdayInfo));
        if (BadgesController.INSTANCE.hasBadge() == true) goto L37;
    L39:
        this.channelRow = r13.size();
        if (this.channel != null) goto L42;
        IconBackgroundColors r145 = IconBackgroundColors.ORANGE;
        r13.add(SettingsActivity.SettingCell.Factory.of(3, r145.top, r145.bottom, R.drawable.msg_filled_menu_channels, LocaleController.getString(R.string.EditProfileChannelTitle), null, LocaleController.getString(R.string.EditProfileChannelAdd)));
    L44:
        if (this.hadHours == false) goto L47;
        IconBackgroundColors r146 = IconBackgroundColors.ORANGE_DEEP;
        r13.add(SettingsActivity.SettingCell.Factory.of(4, r146.top, r146.bottom, R.drawable.filled_premium_hours, LocaleController.getString(R.string.EditProfileHours)));
    L47:
        if (this.hadLocation == false) goto L49;
        IconBackgroundColors r147 = IconBackgroundColors.RED;
        r13.add(SettingsActivity.SettingCell.Factory.of(5, r147.top, r147.bottom, R.drawable.filled_location, LocaleController.getString(R.string.EditProfileLocation)));
    L49:
        ArrayList<TL_account.TL_connectedBot> r148 = this.bots;
        if (r148 != null) goto L52;
    L62:
        IconBackgroundColors r149 = IconBackgroundColors.PURPLE;
        r13.add(SettingsActivity.SettingCell.Factory.of(6, r149.top, r149.bottom, R.drawable.premium_ai_editor, TextCell.applyNewSpan(LocaleController.getString(R.string.EditProfileChatAutomation))));
    L63:
        r13.add(UItem.asShadow(-3, LocaleController.getString(R.string.EditProfileChatAutomationInfo)));
        if (UserConfig.getActivatedAccountsCount() >= 16) goto L66;
        boolean r1410 = true;
    L67:
        if (r1410 == false) goto L70;
        this.addAccountRow = r13.size();
        r13.add(InfoCell.Factory.of(10, R.drawable.outline_add_account, LocaleController.getString(R.string.AddAccount), null, 0).accent());
    L70:
        if (this.accountNumbers.isEmpty() == true) goto L85;
        if (r1410 == true) goto L73;
        r13.add(UItem.asHeader(LocaleController.getString(R.string.SettingsAccounts)));
    L73:
        int r1411 = 0;
    L75:
        if (r1411 >= this.accountNumbers.size()) goto L78;
        r13.add(SettingsActivity.AccountCell.Factory.of(r1411, this.accountNumbers.get(r1411).intValue()));
        r1411 = r1411 + 1;
        goto L75
    L78:
        if (UserConfig.hasPremiumOnAccounts() == true) goto L84;
        int r1412 = Math.max(0, UserConfig.getMaxAccountCount() - UserConfig.getActivatedAccountsCount());
        if (r1412 <= 0) goto L82;
        String r1413 = LocaleController.formatPluralStringComma("AddAccountInfo1", r1412) + " ";
    L83:
        r13.add(UItem.asShadow(TextUtils.concat(new CharSequence[]{r1413, AndroidUtilities.replaceSingleTag(LocaleController.formatPluralStringComma("AddAccountInfo2", UserConfig.getMaxAccountCount()), new UserInfoActivity$$ExternalSyntheticLambda8(this))})));
        goto L85
    L82:
        r1413 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L83
    L84:
        r13.add(UItem.asShadow(null));
    L85:
        this.logoutRow = r13.size();
        r13.add(InfoCell.Factory.of(11, R.drawable.msg_leave, LocaleController.getString(R.string.LogOut), null, 0).red());
        r13.add(UItem.asShadow(-4, null));
        return;
    L66:
        r1410 = false;
        goto L67
    L52:
        if (r148.isEmpty() == true) goto L62;
        StringBuilder r9 = new StringBuilder();
        ArrayList<TL_account.TL_connectedBot> r1414 = this.bots;
        int r42 = r1414.size();
        int r52 = 0;
    L54:
        if (r52 >= r42) goto L61;
        TL_account.TL_connectedBot r6 = r1414.get(r52);
        r52 = r52 + 1;
        TLRPC.User r62 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r6.bot_id));
        if (r62 == null) goto L54;
        if (r9.length() <= 0) goto L60;
        r9.append(", ");
    L60:
        r9.append(UserObject.getUserName(r62));
        goto L54
    L61:
        IconBackgroundColors r1415 = IconBackgroundColors.PURPLE;
        r13.add(SettingsActivity.SettingCell.Factory.of(6, r1415.top, r1415.bottom, R.drawable.premium_ai_editor, LocaleController.getString(R.string.EditProfileChatAutomation), r9));
        goto L63
    L42:
        IconBackgroundColors r1416 = IconBackgroundColors.ORANGE;
        r13.add(SettingsActivity.SettingCell.Factory.of(3, r1416.top, r1416.bottom, R.drawable.msg_filled_menu_channels, LocaleController.getString(R.string.EditProfileChannelTitle), this.channel.title));
        goto L44
    L37:
        if (this.nowPlayingService == null) goto L39;
        IconBackgroundColors r1417 = IconBackgroundColors.CYAN;
        r13.add(SettingsActivity.SettingCell.Factory.of(12, r1417.top, r1417.bottom, R.drawable.msg_filled_data_music, LocaleController.getString(R.string.ScrobblingService), null, this.nowPlayingService.getDisplayName()));
        goto L39
    L12:
        IconBackgroundColors r1418 = IconBackgroundColors.BLUE;
        r13.add(SettingsActivity.SettingCell.Factory.of(9, r1418.top, r1418.bottom, R.drawable.filled_birthday, LocaleController.getString(R.string.AddBirthday), null));
        goto L14
    L8:
        IconBackgroundColors r1419 = IconBackgroundColors.ORANGE;
        r13.add(SettingsActivity.SettingCell.Factory.of(8, r1419.top, r1419.bottom, R.drawable.filled_chatlist_mention, LocaleController.getString(R.string.AddUsername), null));
        goto L9
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.EditAccountInfo2);
    }

    public boolean hasChanges() {
        String r0 = this.currentFirstName;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 != null) goto L6;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (TextUtils.equals(r0, this.firstNameEdit.getText().toString()) == false) goto L29;
        String r02 = this.currentLastName;
        if (r02 != null) goto L11;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        if (TextUtils.equals(r02, this.lastNameEdit.getText().toString()) == false) goto L31;
        String r03 = this.currentBio;
        if (r03 == null) goto L17;
        r1 = r03;
    L17:
        if (TextUtils.equals(r1, this.bioEdit.getText().toString()) == true) goto L19;
        return true;
    L19:
        if (birthdaysEqual(this.currentBirthday, this.birthday) == false) goto L33;
        long r04 = this.currentChannel;
        TLRPC.Chat r4 = this.channel;
        if (r4 == null) goto L23;
        long r2 = r4.id;
    L25:
        if (r04 != r2) goto L34;
        return false;
    L34:
        return true;
    L23:
        r2 = 0;
        goto L25
    L33:
        return true;
    L31:
        return true;
    L29:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void onClick(UItem r12, View r13, int r14, float r15, float r16) {
        Integer r152 = null;
        int r0 = 0;
        if (r12.id != 10) goto L24;
        int r122 = 15;
    L5:
        if (r122 < 0) goto L13;
        if (UserConfig.getInstance(r122).isClientActivated() == true) goto L11;
        r0 = r0 + 1;
        if (r152 != null) goto L11;
        r152 = Integer.valueOf(r122);
    L11:
        r122 = r122 - 1;
        goto L5
    L13:
        if (UserConfig.hasPremiumOnAccounts() == true) goto L15;
        r0 = r0 - 8;
    L15:
        if (r0 <= 0) goto L20;
        if (r152 == null) goto L20;
        presentFragment(new LoginActivity(r152.intValue()));
        return;
    L20:
        if (UserConfig.hasPremiumOnAccounts() == true) goto L87;
        showDialog(new LimitReachedBottomSheet(this, getContext(), 7, this.currentAccount, null));
        return;
    L87:
        return;
    L24:
        if (r12.instanceOf(SettingsActivity.AccountCell.Factory.class) == false) goto L29;
        int r11 = r12.intValue;
        LaunchActivity r123 = LaunchActivity.instance;
        if (r123 == null) goto L88;
        r123.switchToAccount(r11, true);
        return;
    L88:
        return;
    L29:
        int r124 = r12.id;
        if (r124 != 1) goto L32;
    L77:
        Context r2 = getContext();
        String r3 = LocaleController.getString(R.string.EditProfileBirthdayTitle);
        String r4 = LocaleController.getString(R.string.EditProfileBirthdayButton);
        TL_account.TL_birthday r5 = this.birthday;
        UserInfoActivity$$ExternalSyntheticLambda5 r6 = new UserInfoActivity$$ExternalSyntheticLambda5(this);
        if (this.birthday == null) goto L80;
        boolean r9 = true;
    L81:
        showDialog(AlertsCreator.createBirthdayPickerDialog(r2, r3, r4, r5, r6, null, false, r9, getResourceProvider()).create());
        return;
    L80:
        r9 = false;
        goto L81
    L32:
        if (r124 == 9) goto L77;
        if (r124 != 2) goto L42;
        this.birthday = null;
        UniversalRecyclerView r125 = this.listView;
        if (r125 == null) goto L39;
        r125.adapter.update(true);
    L39:
        checkDone(true);
        return;
    L42:
        if (r124 != 3) goto L50;
        AdminedChannelsFetcher r132 = this.channels;
        TLRPC.Chat r142 = this.channel;
        if (r142 != null) goto L46;
        long r143 = 0;
    L47:
        presentFragment(new ChooseChannelFragment(r132, r143, new UserInfoActivity$$ExternalSyntheticLambda6(this)));
        return;
    L46:
        r143 = r142.id;
        goto L47
    L50:
        if (r124 != 5) goto L54;
        presentFragment(new org.telegram.ui.Business.LocationActivity());
        return;
    L54:
        if (r124 != 12) goto L58;
        presentFragment(new SetupNowPlayingActivity());
        return;
    L58:
        if (r124 != 4) goto L62;
        presentFragment(new OpeningHoursActivity());
        return;
    L62:
        if (r124 != 6) goto L66;
        presentFragment(new ChatbotsActivity());
        return;
    L66:
        if (r124 != 7) goto L70;
        presentFragment(new ActionIntroActivity(3));
        return;
    L70:
        if (r124 != 8) goto L74;
        presentFragment(new ChangeUsernameActivity());
        return;
    L74:
        if (r124 != 11) goto L89;
        presentFragment(new LogoutActivity());
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.userInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.privacyRulesUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().addObserver(this, NotificationCenter.nowPlayingUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.updatedChatbot);
        getContactsController().loadPrivacySettings();
        BusinessChatbotController.getInstance(this.currentAccount).load(null);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.userInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.privacyRulesUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().removeObserver(this, NotificationCenter.nowPlayingUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.updatedChatbot);
        super.onFragmentDestroy();
        if (this.wasSaved == true) goto L6;
        processDone(false);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public boolean onLongClick(UItem r2, View r3, int r4, float r5, float r6) {
        TLRPC.User r42 = getUserConfig().getCurrentUser();
        if (r2.id != 7) goto L9;
        if (r42 == null) goto L9;
        if (TextUtils.isEmpty(r42.phone) == true) goto L9;
        String r22 = "+" + r42.phone;
        int r43 = R.string.PhoneCopied;
    L13:
        ItemOptions.makeOptions(this, r3).setScrimViewBackground(this.listView.getClipBackground(r3)).add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new UserInfoActivity$$ExternalSyntheticLambda10(this, r22, r43)).setGravity(3).show();
        return true;
    L9:
        if (r2.id == 8) goto L11;
        return false;
    L11:
        if (TextUtils.isEmpty(UserObject.getPublicUsername(r42)) == true) goto L17;
        r22 = "@" + UserObject.getPublicUsername(r42);
        r43 = R.string.UsernameCopied;
        goto L13
    L17:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.channels.invalidate();
        this.channels.subscribe(new UserInfoActivity$$ExternalSyntheticLambda2(this));
        this.channels.fetch();
        this.birthdayInfo = null;
        UniversalRecyclerView r2 = this.listView;
        if (r2 == null) goto L6;
        r2.adapter.update(true);
        return;
    }
}
