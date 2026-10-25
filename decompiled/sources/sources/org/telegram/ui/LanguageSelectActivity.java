package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.preferences.GeneralPreferencesActivity;
import java.util.ArrayList;
import java.util.Collections;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LanguageCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextRadioCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class LanguageSelectActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {

    @Keep
    private int autoTranslationPosition;

    @Keep
    private int doNotTranslatePosition;
    private EmptyTextProgressView emptyView;
    private int infoPosition1;
    private int languagesStartsPosition;
    private ListAdapter listAdapter;
    private RecyclerListView listView;

    @Keep
    private int manualTranslationPosition;
    private ActionBarMenuItem searchItem;
    private ListAdapter searchListViewAdapter;
    private ArrayList<LocaleController.LocaleInfo> searchResult;
    private boolean searchWas;
    private boolean searching;
    private int settingsFromPosition;
    private int settingsToPosition;
    private ArrayList<LocaleController.LocaleInfo> sortedLanguages;
    private ArrayList<LocaleController.LocaleInfo> unofficialLanguages;

    /* renamed from: org.telegram.ui.LanguageSelectActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ LanguageSelectActivity this$0;

        public AnonymousClass1(LanguageSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.LanguageSelectActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ LanguageSelectActivity this$0;

        public AnonymousClass2(LanguageSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            this.this$0.search(null);
            LanguageSelectActivity.m15584$$Nest$fputsearching(this.this$0, false);
            LanguageSelectActivity.m15583$$Nest$fputsearchWas(this.this$0, false);
            if (LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0) == null) goto L6;
            LanguageSelectActivity.m15572$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0).setAdapter(LanguageSelectActivity.m15575$$Nest$fgetlistAdapter(this.this$0));
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            LanguageSelectActivity.m15584$$Nest$fputsearching(this.this$0, true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            String r22 = r2.getText().toString();
            this.this$0.search(r22);
            int r23 = r22.length();
            LanguageSelectActivity r0 = this.this$0;
            if (r23 == 0) goto L8;
            LanguageSelectActivity.m15583$$Nest$fputsearchWas(r0, true);
            if (LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0) == null) goto L12;
            LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0).setAdapter(LanguageSelectActivity.m15578$$Nest$fgetsearchListViewAdapter(this.this$0));
            return;
        L12:
            return;
        L8:
            LanguageSelectActivity.m15584$$Nest$fputsearching(r0, false);
            LanguageSelectActivity.m15583$$Nest$fputsearchWas(this.this$0, false);
            if (LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0) == null) goto L13;
            LanguageSelectActivity.m15572$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0).setAdapter(LanguageSelectActivity.m15575$$Nest$fgetlistAdapter(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.LanguageSelectActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends DefaultItemAnimator {
        final /* synthetic */ LanguageSelectActivity this$0;

        public AnonymousClass3(LanguageSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0).invalidate();
            LanguageSelectActivity.m15576$$Nest$fgetlistView(this.this$0).updateSelector();
        }
    }

    /* renamed from: org.telegram.ui.LanguageSelectActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ LanguageSelectActivity this$0;

        public AnonymousClass4(LanguageSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        private boolean search;
        final /* synthetic */ LanguageSelectActivity this$0;

        public ListAdapter(LanguageSelectActivity r1, Context r2, boolean r3) {
            this.this$0 = r1;
            this.mContext = r2;
            this.search = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = this.search;
            LanguageSelectActivity r1 = this.this$0;
            if (r0 == true) goto L5;
            int r12 = 4 + LanguageSelectActivity.m15580$$Nest$fgetsortedLanguages(r1).size();
            if (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).isEmpty() == false) goto L13;
            return r12;
        L13:
            return r12 + (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).size() + 1);
        L5:
            if (LanguageSelectActivity.m15579$$Nest$fgetsearchResult(r1) != null) goto L9;
            return 0;
        L9:
            return LanguageSelectActivity.m15579$$Nest$fgetsearchResult(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r6) {
            if (this.search == false) goto L5;
            return 0;
        L5:
            int r0 = r6 - 1;
            if (r6 != 0) goto L8;
            return 3;
        L8:
            int r3 = r6 - 2;
            if (r0 != 0) goto L12;
            return 4;
        L12:
            int r02 = r6 - 3;
            if (r3 != 0) goto L15;
            return 1;
        L15:
            int r32 = r6 - 4;
            if (r02 != 0) goto L19;
            return 3;
        L19:
            if (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).isEmpty() == true) goto L25;
            if (r32 != LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).size()) goto L23;
        L28:
            return 1;
        L23:
            if (r32 == ((LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).size() + LanguageSelectActivity.m15580$$Nest$fgetsortedLanguages(this.this$0).size()) + 1)) goto L28;
        L25:
            if (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).isEmpty() == true) goto L27;
        L29:
            LanguageSelectActivity.m15582$$Nest$fputlanguagesStartsPosition(this.this$0, r6 - r32);
            return 0;
        L27:
            if (r32 != LanguageSelectActivity.m15580$$Nest$fgetsortedLanguages(this.this$0).size()) goto L29;
            goto L28
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 != 0) goto L5;
            return true;
        L5:
            if (r0 != 4) goto L7;
            return true;
        L7:
            if (r0 != 5) goto L9;
            return true;
        L9:
            if (r0 == 2) goto L17;
            return false;
        L17:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            boolean r1 = false;
            int r12 = 0;
            boolean r2 = true;
            switch(r10.getItemViewType()) {
                case 0: goto L58;
                case 1: goto L44;
                case 2: goto L26;
                case 3: goto L15;
                case 4: goto L13;
                case 5: goto L11;
                case 6: goto L5;
                default: goto L99;
            };
        L5:
            TextInfoPrivacyCell r102 = (TextInfoPrivacyCell) r10.itemView;
            r102.updateRTL();
            if (r11 != LanguageSelectActivity.m15573$$Nest$fgetinfoPosition1(this.this$0)) goto L9;
            r102.setText(LocaleController.getString(R.string.TranslateMessagesInfo1));
            r102.setTopPadding(11);
            r102.setBottomPadding(16);
            return;
        L9:
            r102.setTopPadding(0);
            r102.setBottomPadding(16);
            return;
        L11:
            ((TextSettingsCell) r10.itemView).updateRTL();
            return;
        L13:
            TextSettingsCell r9 = (TextSettingsCell) r10.itemView;
            r9.updateRTL();
            r9.setText(LocaleController.getString(R.string.Preferences), false);
            return;
        L15:
            HeaderCell r103 = (HeaderCell) r10.itemView;
            if (r11 == 0) goto L18;
        L23:
            int r92 = R.string.Language;
        L24:
            r103.setText(LocaleController.getString(r92));
            return;
        L18:
            if (this.this$0.getMessagesController().isTranslationsManualEnabled() == false) goto L20;
        L21:
            r92 = R.string.TranslateMessages;
            goto L24
        L20:
            if (this.this$0.getMessagesController().isTranslationsAutoEnabled() == false) goto L23;
        L26:
            TextCheckCell r104 = (TextCheckCell) r10.itemView;
            r104.updateRTL();
            if (r11 != LanguageSelectActivity.m15577$$Nest$fgetmanualTranslationPosition(this.this$0)) goto L31;
            r104.setTextAndCheck(LocaleController.getString(R.string.ShowTranslateButton), LanguageSelectActivity.m15586$$Nest$mgetContextValue(this.this$0), true);
            r104.setCheckBoxIcon(0);
            return;
        L31:
            if (r11 != LanguageSelectActivity.m15571$$Nest$fgetautoTranslationPosition(this.this$0)) goto L98;
            String r112 = LocaleController.getString(R.string.ShowTranslateChatButton);
            boolean r0 = LanguageSelectActivity.m15585$$Nest$mgetChatValue(this.this$0);
            if (LanguageSelectActivity.m15586$$Nest$mgetContextValue(this.this$0) == false) goto L35;
        L38:
            r104.setTextAndCheck(r112, r0, r2);
            if (this.this$0.getUserConfig().isPremium() == true) goto L41;
            r12 = R.drawable.permission_locked;
        L41:
            r104.setCheckBoxIcon(r12);
            return;
        L35:
            if (LanguageSelectActivity.m15585$$Nest$mgetChatValue(this.this$0) == true) goto L38;
            r2 = false;
            goto L38
        L98:
            return;
        L99:
            return;
        L44:
            if (this.search == true) goto L46;
            r11 = r11 - 1;
        L46:
            ShadowSectionCell r105 = (ShadowSectionCell) r10.itemView;
            if (r11 != 2) goto L49;
        L55:
            r105.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
            return;
        L49:
            if (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).isEmpty() == false) goto L51;
        L53:
            r105.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            return;
        L51:
            if (r11 != LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).size()) goto L53;
        L58:
            if (this.search == true) goto L60;
            r11 = r11 - LanguageSelectActivity.m15574$$Nest$fgetlanguagesStartsPosition(this.this$0);
        L60:
            TextRadioCell r3 = (TextRadioCell) r10.itemView;
            r3.updateRTL();
            LocaleController.LocaleInfo r02 = null;
            if (this.search == false) goto L71;
            if (r11 < 0) goto L67;
            if (r11 >= LanguageSelectActivity.m15579$$Nest$fgetsearchResult(this.this$0).size()) goto L67;
            r02 = (LocaleController.LocaleInfo) LanguageSelectActivity.m15579$$Nest$fgetsearchResult(this.this$0).get(r11);
        L67:
            if (r11 != (LanguageSelectActivity.m15579$$Nest$fgetsearchResult(this.this$0).size() - 1)) goto L69;
        L68:
            boolean r93 = true;
        L88:
            if (r02 == null) goto L94;
            boolean r106 = r02.isLocal();
            String r4 = r02.name;
            if (r106 == false) goto L92;
            r3.setTextAndValueAndCheck(String.format("%1$s (%2$s)", new Object[]{r4, LocaleController.getString(R.string.LanguageCustom)}), r02.nameEnglish, false, false, !r93);
            goto L94
        L92:
            r3.setTextAndValueAndCheck(r4, r02.nameEnglish, false, false, !r93);
        L94:
            if (r02 != LocaleController.getInstance().getCurrentLocaleInfo()) goto L96;
            r1 = true;
        L96:
            r3.setChecked(r1);
            return;
        L69:
            r93 = false;
            goto L88
        L71:
            if (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).isEmpty() == true) goto L79;
            if (r11 < 0) goto L79;
            if (r11 >= LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).size()) goto L79;
            r02 = (LocaleController.LocaleInfo) LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).get(r11);
            if (r11 != (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).size() - 1)) goto L69;
        L79:
            if (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).isEmpty() == true) goto L81;
            r11 = r11 - (LanguageSelectActivity.m15581$$Nest$fgetunofficialLanguages(this.this$0).size() + 1);
        L81:
            if (r11 < 0) goto L86;
            if (r11 >= LanguageSelectActivity.m15580$$Nest$fgetsortedLanguages(this.this$0).size()) goto L86;
            r02 = (LocaleController.LocaleInfo) LanguageSelectActivity.m15580$$Nest$fgetsortedLanguages(this.this$0).get(r11);
        L86:
            if (r11 != (LanguageSelectActivity.m15580$$Nest$fgetsortedLanguages(this.this$0).size() - 1)) goto L69;
            goto L69
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L4;
            View r12 = new TextRadioCell(this.mContext);
        L20:
            return new RecyclerListView.Holder(r12);
        L4:
            if (r2 != 2) goto L6;
            r12 = new TextCheckCell(this.mContext);
            goto L20
        L6:
            if (r2 != 3) goto L8;
            r12 = new HeaderCell(this.mContext);
            goto L20
        L8:
            if (r2 != 4) goto L10;
        L15:
            r12 = new TextSettingsCell(this.mContext);
            goto L20
        L10:
            if (r2 == 5) goto L15;
            if (r2 == 6) goto L14;
            r12 = new ShadowSectionCell(this.mContext);
            goto L20
        L14:
            r12 = new TextInfoPrivacyCell(this.mContext);
            goto L20
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof TextRadioCell) == false) goto L6;
            ((TextRadioCell) r0).updateRTL();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$3XbrwU5qSgLvSf3iIcd9fzMmDxY(LanguageSelectActivity r0, ArrayList r1) {
        r0.lambda$updateSearchResults$10(r1);
    }

    /* renamed from: $r8$lambda$9F6KC6GYYO37NzGWoLaceE-00gY, reason: not valid java name */
    public static /* synthetic */ boolean m15569$r8$lambda$9F6KC6GYYO37NzGWoLaceE00gY(LanguageSelectActivity r0, View r1, int r2) {
        return r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$SHnsCWjwojK0j4sFxLt1js56wyU(LanguageSelectActivity r0, View r1, int r2) {
        r0.lambda$createView$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XDPGZQMoMnfsRaRgMT5xtr1b7WE(LanguageSelectActivity r0) {
        r0.lambda$createView$0();
    }

    public static /* synthetic */ void $r8$lambda$ZdgAvN_22yYWy0GX04ik_V3IXO8(LanguageSelectActivity r0) {
        r0.lambda$didReceivedNotification$6();
    }

    public static /* synthetic */ int $r8$lambda$eQXL73GlwHTjQxtwiJ9rFtwNBxA(LocaleController.LocaleInfo r1, LocaleController.LocaleInfo r2, LocaleController.LocaleInfo r3) {
        if (r2 == r1) goto L19;
        if (r3 == r1) goto L18;
        int r12 = r2.serverIndex;
        int r0 = r3.serverIndex;
        if (r12 == r0) goto L9;
        if (r12 <= r0) goto L13;
        return 1;
    L13:
        if (r12 >= r0) goto L16;
        return -1;
    L16:
        return 0;
    L9:
        return r2.name.compareTo(r3.name);
    L18:
        return 1;
    L19:
        return -1;
    }

    /* renamed from: $r8$lambda$mUiE-cbIP59BJOopYcuVZQbNlDc, reason: not valid java name */
    public static /* synthetic */ void m15570$r8$lambda$mUiEcbIP59BJOopYcuVZQbNlDc(LanguageSelectActivity r0, AlertDialog r1, boolean r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rMGImPH8OtNa403C2ZAbUrdXsTI(LanguageSelectActivity r0, String r1) {
        r0.lambda$processSearch$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$tLhz7XyboplQcFF8DpkozLz60_E(LanguageSelectActivity r0, LocaleController.LocaleInfo r1, AlertDialog r2, int r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$vyQ2ZMl_TVOhEI7uQw6VPSk2JyM(LanguageSelectActivity r0) {
        r0.lambda$onBecomeFullyVisible$8();
    }

    public static /* synthetic */ void $r8$lambda$zf4nx6qu17oaLc0WAqGp60ngvQ4(LanguageSelectActivity r0, int r1, DialogInterface r2) {
        r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: -$$Nest$fgetautoTranslationPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15571$$Nest$fgetautoTranslationPosition(LanguageSelectActivity r0) {
        return r0.autoTranslationPosition;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m15572$$Nest$fgetemptyView(LanguageSelectActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetinfoPosition1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15573$$Nest$fgetinfoPosition1(LanguageSelectActivity r0) {
        return r0.infoPosition1;
    }

    /* renamed from: -$$Nest$fgetlanguagesStartsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15574$$Nest$fgetlanguagesStartsPosition(LanguageSelectActivity r0) {
        return r0.languagesStartsPosition;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m15575$$Nest$fgetlistAdapter(LanguageSelectActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15576$$Nest$fgetlistView(LanguageSelectActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmanualTranslationPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15577$$Nest$fgetmanualTranslationPosition(LanguageSelectActivity r0) {
        return r0.manualTranslationPosition;
    }

    /* renamed from: -$$Nest$fgetsearchListViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m15578$$Nest$fgetsearchListViewAdapter(LanguageSelectActivity r0) {
        return r0.searchListViewAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15579$$Nest$fgetsearchResult(LanguageSelectActivity r0) {
        return r0.searchResult;
    }

    /* renamed from: -$$Nest$fgetsortedLanguages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15580$$Nest$fgetsortedLanguages(LanguageSelectActivity r0) {
        return r0.sortedLanguages;
    }

    /* renamed from: -$$Nest$fgetunofficialLanguages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15581$$Nest$fgetunofficialLanguages(LanguageSelectActivity r0) {
        return r0.unofficialLanguages;
    }

    /* renamed from: -$$Nest$fputlanguagesStartsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15582$$Nest$fputlanguagesStartsPosition(LanguageSelectActivity r0, int r1) {
        r0.languagesStartsPosition = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15583$$Nest$fputsearchWas(LanguageSelectActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15584$$Nest$fputsearching(LanguageSelectActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$mgetChatValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15585$$Nest$mgetChatValue(LanguageSelectActivity r0) {
        return r0.getChatValue();
    }

    /* renamed from: -$$Nest$mgetContextValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15586$$Nest$mgetContextValue(LanguageSelectActivity r0) {
        return r0.getContextValue();
    }

    public LanguageSelectActivity() {
        this.settingsFromPosition = -1;
        this.settingsToPosition = -1;
        this.manualTranslationPosition = -1;
        this.autoTranslationPosition = -1;
        this.doNotTranslatePosition = -1;
    }

    private void fillLanguages() {
        LanguageSelectActivity$$ExternalSyntheticLambda1 r1 = new LanguageSelectActivity$$ExternalSyntheticLambda1(LocaleController.getInstance().getCurrentLocaleInfo());
        this.sortedLanguages = new ArrayList();
        this.unofficialLanguages = new ArrayList(LocaleController.getInstance().unofficialLanguages);
        ArrayList<LocaleController.LocaleInfo> r0 = LocaleController.getInstance().languages;
        int r2 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        LocaleController.LocaleInfo r4 = r0.get(r3);
        if (r4.serverIndex == Integer.MAX_VALUE) goto L7;
        this.sortedLanguages.add(r4);
    L8:
        r3 = r3 + 1;
        goto L3
    L7:
        this.unofficialLanguages.add(r4);
        goto L8
    L9:
        Collections.sort(this.sortedLanguages, r1);
        Collections.sort(this.unofficialLanguages, r1);
    }

    private boolean getChatValue() {
        return getMessagesController().getTranslateController().isFeatureAvailable();
    }

    private boolean getContextValue() {
        return getMessagesController().getTranslateController().isContextTranslateEnabled();
    }

    private /* synthetic */ void lambda$createView$0() {
        this.actionBar.closeSearchField();
        updateLanguage();
    }

    private /* synthetic */ void lambda$createView$1(AlertDialog r3, boolean r4) {
        r3.dismiss();
        if (r4 == true) goto L6;
        AndroidUtilities.runOnUIThread(new LanguageSelectActivity$$ExternalSyntheticLambda7(this), 10);
        return;
    }

    private /* synthetic */ void lambda$createView$2(int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r1, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$createView$3(View r12, int r13) {
        boolean r1 = false;
        int r14 = 0;
        if ((r12 instanceof TextCheckCell) == false) goto L54;
        if (getContextValue() == false) goto L7;
    L10:
        boolean r0 = true;
    L12:
        if (r13 != this.manualTranslationPosition) goto L15;
        boolean r132 = !getContextValue();
        getMessagesController().getTranslateController().setContextTranslateEnabled(r132);     // Catch: Exception -> L95
        ((TextCheckCell) r12).setChecked(r132);     // Catch: Exception -> L95
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateSearchSettings, new Object[0]);     // Catch: Exception -> L95
    L24:
        if (getContextValue() == false) goto L26;
    L29:
        boolean r122 = true;
    L30:
        if (r122 == r0) goto L103;
        int r133 = this.autoTranslationPosition;     // Catch: Exception -> L95
        if (r133 >= 0) goto L35;
        r133 = this.manualTranslationPosition;     // Catch: Exception -> L95
    L35:
        TextCheckCell r02 = null;
    L37:
        if (r14 >= this.listView.getChildCount()) goto L44;
        View r3 = this.listView.getChildAt(r14);     // Catch: Exception -> L95
        if (this.listView.getChildAdapterPosition(r3) != r133) goto L43;
        if ((r3 instanceof TextCheckCell) == false) goto L43;
        r02 = (TextCheckCell) r3;     // Catch: Exception -> L95
    L43:
        r14 = r14 + 1;     // Catch: Exception -> L95
        goto L37
    L44:
        if (r02 == null) goto L46;
        r02.setDivider(r122);     // Catch: Exception -> L95
    L46:
        ListAdapter r11 = this.listAdapter;
        if (r122 == true) goto L49;
        r11.notifyItemRemoved(r133 + 1);     // Catch: Exception -> L95
        return;
    L49:
        r11.notifyItemInserted(r133 + 1);     // Catch: Exception -> L95
        return;
    L103:
        return;
    L26:
        if (getChatValue() == true) goto L29;
        r122 = false;
        goto L30
    L15:
        if (r13 != this.autoTranslationPosition) goto L24;
        boolean r134 = getChatValue();     // Catch: Exception -> L95
        boolean r32 = !r134;
        if (r134 == false) goto L19;
    L22:
        getMessagesController().getTranslateController().setChatTranslateEnabled(r32);     // Catch: Exception -> L95
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateSearchSettings, new Object[0]);     // Catch: Exception -> L95
        ((TextCheckCell) r12).setChecked(r32);     // Catch: Exception -> L95
        goto L24
    L19:
        if (getUserConfig().isPremium() == true) goto L22;
        showDialog(new PremiumFeatureBottomSheet(this, 13, false));     // Catch: Exception -> L95
        return;
    L7:
        if (getChatValue() == true) goto L10;
        r0 = false;
        goto L12
    L54:
        if ((r12 instanceof TextSettingsCell) == false) goto L58;
        presentFragment(new GeneralPreferencesActivity());     // Catch: Exception -> L95
        return;
    L58:
        if (getParentActivity() != null) goto L60;
        return;
    L60:
        if (this.parentLayout != null) goto L62;
        return;
    L62:
        if ((r12 instanceof TextRadioCell) == true) goto L65;
        return;
    L65:
        if (this.listView.getAdapter() != this.searchListViewAdapter) goto L67;
        Object[] r123 = true;
    L68:
        if (r123 == true) goto L70;
        r13 = r13 - this.languagesStartsPosition;     // Catch: Exception -> L95
    L70:
        if (r123 == false) goto L74;
        LocaleController.LocaleInfo r124 = this.searchResult.get(r13);     // Catch: Exception -> L95
    L72:
        LocaleController.LocaleInfo r4 = r124;
        if (r4 != null) goto L85;
        return;
    L85:
        if (LocaleController.getInstance().getCurrentLocaleInfo() != r4) goto L87;
        r1 = true;
    L87:
        AlertDialog r125 = new AlertDialog(getContext(), 3);     // Catch: Exception -> L95
        if (r1 == true) goto L90;
        r125.showDelayed(500);     // Catch: Exception -> L95
    L90:
        getMessagesController().getTranslateController().reset();     // Catch: Exception -> L95
        int r135 = LocaleController.getInstance().applyLanguage(r4, true, false, false, true, this.currentAccount, new LanguageSelectActivity$$ExternalSyntheticLambda5(this, r125, r1));     // Catch: Exception -> L95
        if (r135 == 0) goto L93;
        r125.setOnCancelListener(new LanguageSelectActivity$$ExternalSyntheticLambda6(this, r135));     // Catch: Exception -> L95
    L93:
        TranslateController.invalidateSuggestedLanguageCodes();     // Catch: Exception -> L95
        return;
    L74:
        if (this.unofficialLanguages.isEmpty() == true) goto L80;
        if (r13 < 0) goto L80;
        if (r13 >= this.unofficialLanguages.size()) goto L80;
        r124 = this.unofficialLanguages.get(r13);     // Catch: Exception -> L95
    L80:
        if (this.unofficialLanguages.isEmpty() == true) goto L82;
        r13 = r13 - (this.unofficialLanguages.size() + 1);     // Catch: Exception -> L95
    L82:
        r124 = this.sortedLanguages.get(r13);     // Catch: Exception -> L95
        goto L72
    L67:
        r123 = false;
    L95:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$createView$4(LocaleController.LocaleInfo r1, AlertDialog r2, int r3) {
        if (LocaleController.getInstance().deleteLanguage(r1, this.currentAccount) == false) goto L14;
        fillLanguages();
        ArrayList<LocaleController.LocaleInfo> r22 = this.searchResult;
        if (r22 == null) goto L7;
        r22.remove(r1);
    L7:
        ListAdapter r12 = this.listAdapter;
        if (r12 == null) goto L10;
        r12.notifyDataSetChanged();
    L10:
        ListAdapter r0 = this.searchListViewAdapter;
        if (r0 == null) goto L15;
        r0.notifyDataSetChanged();
        return;
    L15:
        return;
    }

    private /* synthetic */ boolean lambda$createView$5(View r5, int r6) {
    L16:
        e = move-exception;
        FileLog.e(e);
    L46:
        return true;
    L4:
        if (getParentActivity() != null) goto L6;
    L44:
        return false;
    L6:
        if (this.parentLayout == null) goto L44;
        if ((r5 instanceof TextRadioCell) == false) goto L44;
        if (this.listView.getAdapter() != this.searchListViewAdapter) goto L13;
        boolean r52 = true;
    L14:
        if (r52 == true) goto L18;
        r6 = r6 - this.languagesStartsPosition;     // Catch: Exception -> L16
    L18:
        if (r52 == false) goto L21;
        LocaleController.LocaleInfo r53 = this.searchResult.get(r6);     // Catch: Exception -> L16
    L30:
        if (r53 == null) goto L44;
        if (r53.pathToFile == null) goto L44;
        if (r53.builtIn == true) goto L44;
        if (r53.isRemote() == true) goto L38;
    L40:
        AlertDialog.Builder r62 = new AlertDialog.Builder(getParentActivity());     // Catch: Exception -> L16
        r62.setTitle(LocaleController.getString(R.string.DeleteLocalizationTitle));     // Catch: Exception -> L16
        r62.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("DeleteLocalizationText", R.string.DeleteLocalizationText, new Object[]{r53.name})));     // Catch: Exception -> L16
        r62.setPositiveButton(LocaleController.getString(R.string.Delete), new LanguageSelectActivity$$ExternalSyntheticLambda8(this, r53));     // Catch: Exception -> L16
        r62.setNegativeButton(LocaleController.getString(R.string.Cancel), null);     // Catch: Exception -> L16
        AlertDialog r54 = r62.create();     // Catch: Exception -> L16
        showDialog(r54);     // Catch: Exception -> L16
        TextView r4 = (TextView) r54.getButton(-1);     // Catch: Exception -> L16
        if (r4 == null) goto L46;
        r4.setTextColor(Theme.getColor(Theme.key_text_RedBold));     // Catch: Exception -> L16
        goto L46
    L38:
        if (r53.serverIndex == Integer.MAX_VALUE) goto L40;
    L21:
        if (this.unofficialLanguages.isEmpty() == true) goto L27;
        if (r6 < 0) goto L27;
        if (r6 >= this.unofficialLanguages.size()) goto L27;
        r53 = this.unofficialLanguages.get(r6);     // Catch: Exception -> L16
    L27:
        if (this.unofficialLanguages.isEmpty() == true) goto L29;
        r6 = r6 - (this.unofficialLanguages.size() + 1);     // Catch: Exception -> L16
    L29:
        r53 = this.sortedLanguages.get(r6);     // Catch: Exception -> L16
        goto L30
    L13:
        r52 = false;
        goto L14
    }

    private /* synthetic */ void lambda$didReceivedNotification$6() {
        this.listAdapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$onBecomeFullyVisible$8() {
        if (this.isPaused == true) goto L6;
        updateLanguage();
        return;
    }

    private /* synthetic */ void lambda$processSearch$9(String r7) {
        if (r7.trim().toLowerCase().length() != 0) goto L6;
        updateSearchResults(new ArrayList());
        return;
    L6:
        System.currentTimeMillis();
        ArrayList<LocaleController.LocaleInfo> r0 = new ArrayList();
        int r1 = this.unofficialLanguages.size();
        int r2 = 0;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L14;
        LocaleController.LocaleInfo r4 = this.unofficialLanguages.get(r3);
        if (r4.name.toLowerCase().startsWith(r7) == false) goto L11;
    L12:
        r0.add(r4);
    L13:
        r3 = r3 + 1;
        goto L7
    L11:
        if (r4.nameEnglish.toLowerCase().startsWith(r7) == false) goto L13;
    L14:
        int r12 = this.sortedLanguages.size();
    L15:
        if (r2 >= r12) goto L22;
        LocaleController.LocaleInfo r32 = this.sortedLanguages.get(r2);
        if (r32.name.toLowerCase().startsWith(r7) == false) goto L19;
    L20:
        r0.add(r32);
    L21:
        r2 = r2 + 1;
        goto L15
    L19:
        if (r32.nameEnglish.toLowerCase().startsWith(r7) == false) goto L21;
    L22:
        updateSearchResults(r0);
    }

    private /* synthetic */ void lambda$updateSearchResults$10(ArrayList r1) {
        this.searchResult = r1;
        this.searchListViewAdapter.notifyDataSetChanged();
    }

    private void processSearch(String r3) {
        Utilities.searchQueue.postRunnable(new LanguageSelectActivity$$ExternalSyntheticLambda9(this, r3));
    }

    private void updateLanguage() {
        if (this.actionBar == null) goto L7;
        String r2 = LocaleController.getString(R.string.Language);
        if (TextUtils.equals(this.actionBar.getTitle(), r2) == true) goto L7;
        this.actionBar.setTitleAnimated(r2, true, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
    L7:
        ListAdapter r7 = this.listAdapter;
        if (r7 == null) goto L11;
        r7.notifyItemRangeChanged(0, r7.getItemCount());
        return;
    }

    private void updateSearchResults(ArrayList<LocaleController.LocaleInfo> r2) {
        AndroidUtilities.runOnUIThread(new LanguageSelectActivity$$ExternalSyntheticLambda10(this, r2));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.searching = false;
        this.searchWas = false;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.Language));
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L5;
    L7:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenuItem r12 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r12;
        r12.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.listAdapter = new ListAdapter(this, r8, false);
        this.searchListViewAdapter = new ListAdapter(this, r8, true);
        FrameLayout r13 = new FrameLayout(r8);
        this.fragmentView = r13;
        r13.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r14 = (FrameLayout) this.fragmentView;
        EmptyTextProgressView r3 = new EmptyTextProgressView(r8);
        this.emptyView = r3;
        r3.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.showTextView();
        this.emptyView.setShowAtCenter(true);
        r14.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r32 = new RecyclerListView(r8);
        this.listView = r32;
        r32.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setEmptyView(this.emptyView);
        this.listView.setLayoutManager(new LinearLayoutManager(r8, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setAdapter(this.listAdapter);
        AnonymousClass3 r82 = new AnonymousClass3(this);
        r82.setDurations(400);
        r82.setDelayAnimations(false);
        r82.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.listView.setItemAnimator(r82);
        r14.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new LanguageSelectActivity$$ExternalSyntheticLambda2(this));
        this.listView.setOnItemLongClickListener(new LanguageSelectActivity$$ExternalSyntheticLambda3(this));
        this.listView.setOnScrollListener(new AnonymousClass4(this));
        return this.fragmentView;
    L5:
        if (r1.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.suggestedLangpack) goto L5;
        return;
    L5:
        if (this.listAdapter == null) goto L9;
        fillLanguages();
        AndroidUtilities.runOnUIThread(new LanguageSelectActivity$$ExternalSyntheticLambda4(this));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{LanguageCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LanguageCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LanguageCell.class}, new String[]{"textView2"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LanguageCell.class}, new String[]{"checkImage"}, null, null, null, Theme.key_featuredStickers_addedIcon));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        LocaleController.getInstance().checkForcePatchLangpack(this.currentAccount, new LanguageSelectActivity$$ExternalSyntheticLambda0(this));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        fillLanguages();
        LocaleController.getInstance().loadRemoteLanguages(this.currentAccount, false);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.suggestedLangpack);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.suggestedLangpack);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    public void search(String r2) {
        if (r2 != null) goto L7;
        this.searching = false;
        this.searchResult = null;
        if (this.listView == null) goto L9;
        this.emptyView.setVisibility(8);
        this.listView.setAdapter(this.listAdapter);
        return;
    L9:
        return;
    L7:
        processSearch(r2);
    }
}
