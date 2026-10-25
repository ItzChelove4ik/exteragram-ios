package org.telegram.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LanguageCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckbox2Cell;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TranslateAlert2;

/* loaded from: classes3.dex */
public class RestrictedLanguagesSelectActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static boolean gotRestrictedLanguages;
    private static HashSet<String> restrictedLanguages;
    private ArrayList<TranslateController.Language> allLanguages;
    private EmptyTextProgressView emptyView;
    private HashSet<String> firstSelectedLanguages;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private ListAdapter searchListViewAdapter;
    private ArrayList<TranslateController.Language> searchResult;
    private HashSet<String> selectedLanguages;
    private int separatorRow;
    private final int type;

    /* renamed from: org.telegram.ui.RestrictedLanguagesSelectActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ RestrictedLanguagesSelectActivity this$0;

        public AnonymousClass1(RestrictedLanguagesSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.RestrictedLanguagesSelectActivity$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ RestrictedLanguagesSelectActivity this$0;

        public AnonymousClass2(RestrictedLanguagesSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            this.this$0.search(null);
            if (RestrictedLanguagesSelectActivity.m18493$$Nest$fgetlistView(this.this$0) == null) goto L6;
            RestrictedLanguagesSelectActivity.m18491$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            RestrictedLanguagesSelectActivity.m18493$$Nest$fgetlistView(this.this$0).setAdapter(RestrictedLanguagesSelectActivity.m18492$$Nest$fgetlistAdapter(this.this$0));
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            String r22 = r2.getText().toString();
            this.this$0.search(r22);
            int r23 = r22.length();
            RestrictedLanguagesSelectActivity r0 = this.this$0;
            if (r23 == 0) goto L9;
            if (RestrictedLanguagesSelectActivity.m18493$$Nest$fgetlistView(r0) == null) goto L12;
            RestrictedLanguagesSelectActivity.m18493$$Nest$fgetlistView(this.this$0).setAdapter(RestrictedLanguagesSelectActivity.m18494$$Nest$fgetsearchListViewAdapter(this.this$0));
            return;
        L12:
            return;
        L9:
            if (RestrictedLanguagesSelectActivity.m18493$$Nest$fgetlistView(r0) == null) goto L13;
            RestrictedLanguagesSelectActivity.m18491$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            RestrictedLanguagesSelectActivity.m18493$$Nest$fgetlistView(this.this$0).setAdapter(RestrictedLanguagesSelectActivity.m18492$$Nest$fgetlistAdapter(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.RestrictedLanguagesSelectActivity$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ RestrictedLanguagesSelectActivity this$0;

        public AnonymousClass3(RestrictedLanguagesSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    /* loaded from: classes6.dex */
    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        private boolean search;
        final /* synthetic */ RestrictedLanguagesSelectActivity this$0;

        public ListAdapter(RestrictedLanguagesSelectActivity r1, Context r2, boolean r3) {
            this.this$0 = r1;
            this.mContext = r2;
            this.search = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = this.search;
            RestrictedLanguagesSelectActivity r1 = this.this$0;
            int r2 = 0;
            if (r0 == false) goto L10;
            if (RestrictedLanguagesSelectActivity.m18495$$Nest$fgetsearchResult(r1) != null) goto L8;
            return 0;
        L8:
            return RestrictedLanguagesSelectActivity.m18495$$Nest$fgetsearchResult(this.this$0).size();
        L10:
            if (RestrictedLanguagesSelectActivity.m18497$$Nest$fgetseparatorRow(r1) < 0) goto L13;
            r2 = 1;
        L13:
            return r2 + RestrictedLanguagesSelectActivity.m18490$$Nest$fgetallLanguages(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (this.search == false) goto L6;
            return 0;
        L6:
            if (r3 != RestrictedLanguagesSelectActivity.m18497$$Nest$fgetseparatorRow(this.this$0)) goto L9;
            return 1;
        L9:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
            int r0 = r6.getItemViewType();
            if (r0 == 0) goto L8;
            if (r0 != 1) goto L40;
            ShadowSectionCell r5 = (ShadowSectionCell) r6.itemView;
            return;
        L40:
            return;
        L8:
            TextCheckbox2Cell r62 = (TextCheckbox2Cell) r6.itemView;
            TranslateController.Language r3 = null;
            if (this.search == false) goto L18;
            if (r7 < 0) goto L15;
            if (r7 >= RestrictedLanguagesSelectActivity.m18495$$Nest$fgetsearchResult(this.this$0).size()) goto L15;
            r3 = (TranslateController.Language) RestrictedLanguagesSelectActivity.m18495$$Nest$fgetsearchResult(this.this$0).get(r7);
        L15:
            if (r7 != (RestrictedLanguagesSelectActivity.m18495$$Nest$fgetsearchResult(this.this$0).size() - 1)) goto L28;
        L16:
            boolean r72 = true;
        L29:
            if (r3 != null) goto L31;
            return;
        L31:
            String r02 = r3.ownDisplayName;
            if (r02 != null) goto L34;
            r02 = r3.displayName;
        L34:
            r62.setTextAndValue(r02, r3.displayName, false, !r72);
            int r73 = RestrictedLanguagesSelectActivity.m18498$$Nest$fgettype(this.this$0);
            RestrictedLanguagesSelectActivity r52 = this.this$0;
            if (r73 != 1) goto L38;
            r62.setChecked(RestrictedLanguagesSelectActivity.m18499$$Nest$misTargetChecked(r52, r3.code));
            return;
        L38:
            r62.setChecked(RestrictedLanguagesSelectActivity.m18496$$Nest$fgetselectedLanguages(r52).contains(RestrictedLanguagesSelectActivity.m18500$$Nest$smnormalizeRestrictedLanguage(r3.code)));
            return;
        L28:
            r72 = false;
            goto L29
        L18:
            if (RestrictedLanguagesSelectActivity.m18497$$Nest$fgetseparatorRow(this.this$0) >= 0) goto L20;
        L22:
            if (r7 < 0) goto L28;
            if (r7 >= RestrictedLanguagesSelectActivity.m18490$$Nest$fgetallLanguages(this.this$0).size()) goto L28;
            r3 = (TranslateController.Language) RestrictedLanguagesSelectActivity.m18490$$Nest$fgetallLanguages(this.this$0).get(r7);
            if (r7 != (RestrictedLanguagesSelectActivity.m18490$$Nest$fgetallLanguages(this.this$0).size() - 1)) goto L28;
        L20:
            if (r7 <= RestrictedLanguagesSelectActivity.m18497$$Nest$fgetseparatorRow(this.this$0)) goto L22;
            r7 = r7 - 1;
            goto L22
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L4;
            View r12 = new TextCheckbox2Cell(this.mContext);
        L9:
            return new RecyclerListView.Holder(r12);
        L4:
            if (r2 == 2) goto L6;
            r12 = new ShadowSectionCell(this.mContext);
            goto L9
        L6:
            HeaderCell r13 = new HeaderCell(this.mContext);
            r13.setText(LocaleController.getString(R.string.ChooseLanguages));
            r12 = r13;
            goto L9
        }
    }

    public static /* synthetic */ void $r8$lambda$6DGdOuNr3aFPHx0pxeU5aiIj6DI(HashSet r6, Runnable r7) {
        InputMethodManager r0 = (InputMethodManager) ApplicationLoader.applicationContext.getSystemService("input_method");     // Catch: Exception -> L12
        Iterator<InputMethodInfo> r1 = r0.getEnabledInputMethodList().iterator();     // Catch: Exception -> L12
    L3:
        if (r1.hasNext() == false) goto L14;
        Iterator<InputMethodSubtype> r2 = r0.getEnabledInputMethodSubtypeList(r1.next(), true).iterator();     // Catch: Exception -> L12
    L7:
        if (r2.hasNext() == false) goto L3;
        InputMethodSubtype r3 = r2.next();     // Catch: Exception -> L12
        if ("keyboard".equals(r3.getMode()) == false) goto L7;
        addExtendedLanguage(r6, r3.getLocale());     // Catch: Exception -> L12
    L14:
        r7.run();
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L14
    }

    public static /* synthetic */ void $r8$lambda$ItedNn7uVkAe3jnEOjch4daoks0(HashSet r1, Runnable r2) {
        addExtendedLanguage(r1, LocaleController.getInstance().getCurrentLocaleInfo().pluralLangCode);     // Catch: Exception -> L4
    L6:
        r2.run();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    /* renamed from: $r8$lambda$QfU_N3GIEwZte-TQMNj9vFrzulw, reason: not valid java name */
    public static /* synthetic */ void m18488$r8$lambda$QfU_N3GIEwZteTQMNj9vFrzulw(RestrictedLanguagesSelectActivity r0, View r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    /* renamed from: $r8$lambda$SCr_jE1pemEr_qp8jIXIiV-z6yw, reason: not valid java name */
    public static /* synthetic */ void m18489$r8$lambda$SCr_jE1pemEr_qp8jIXIiVz6yw(Utilities.Callback r0, HashSet r1, Runnable r2) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$fZBhqylQcfoDkpJieiBTBpFGTiE(HashSet r5) {
        String r0 = getDefaultRestrictedLanguage();
        r5.addAll(getRestrictedLanguages());
        SharedPreferences.Editor r1 = MessagesController.getGlobalMainSettings().edit();
        if (r5.size() == 1) goto L5;
    L7:
        r1.putStringSet("translate_button_restricted_languages", r5);
    L8:
        r1.putInt("translate_button_restricted_languages_version", 2).apply();
        invalidateRestrictedLanguages();
        int r52 = 0;
    L10:
        if (r52 >= 16) goto L13;
        MessagesController.getInstance(r52).getTranslateController().checkRestrictedLanguagesUpdate();     // Catch: Exception -> L14
    L12:
        r52 = r52 + 1;
        goto L10
    L13:
        return;
    L5:
        if (TextUtils.equals((CharSequence) r5.iterator().next(), r0) == false) goto L7;
        r1.remove("translate_button_restricted_languages");
        goto L8
    }

    public static /* synthetic */ boolean $r8$lambda$mTmfCUvxJwc7NFIoYNVr2bVIibM(String r0, String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.equals(r0) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$xVT5ksQ4xck1jPOwK9QlVoUsGrY(HashSet r1, Runnable r2) {
        addExtendedLanguage(r1, Resources.getSystem().getConfiguration().locale.getLanguage());     // Catch: Exception -> L4
    L6:
        r2.run();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    /* renamed from: -$$Nest$fgetallLanguages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18490$$Nest$fgetallLanguages(RestrictedLanguagesSelectActivity r0) {
        return r0.allLanguages;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m18491$$Nest$fgetemptyView(RestrictedLanguagesSelectActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m18492$$Nest$fgetlistAdapter(RestrictedLanguagesSelectActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m18493$$Nest$fgetlistView(RestrictedLanguagesSelectActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetsearchListViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m18494$$Nest$fgetsearchListViewAdapter(RestrictedLanguagesSelectActivity r0) {
        return r0.searchListViewAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18495$$Nest$fgetsearchResult(RestrictedLanguagesSelectActivity r0) {
        return r0.searchResult;
    }

    /* renamed from: -$$Nest$fgetselectedLanguages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m18496$$Nest$fgetselectedLanguages(RestrictedLanguagesSelectActivity r0) {
        return r0.selectedLanguages;
    }

    /* renamed from: -$$Nest$fgetseparatorRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18497$$Nest$fgetseparatorRow(RestrictedLanguagesSelectActivity r0) {
        return r0.separatorRow;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18498$$Nest$fgettype(RestrictedLanguagesSelectActivity r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$misTargetChecked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18499$$Nest$misTargetChecked(RestrictedLanguagesSelectActivity r0, String r1) {
        return r0.isTargetChecked(r1);
    }

    /* renamed from: -$$Nest$smnormalizeRestrictedLanguage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m18500$$Nest$smnormalizeRestrictedLanguage(String r0) {
        return normalizeRestrictedLanguage(r0);
    }

    public RestrictedLanguagesSelectActivity() {
        this(0);
    }

    public RestrictedLanguagesSelectActivity(int r2) {
        this.separatorRow = -1;
        this.type = r2;
    }

    private static void addExtendedLanguage(HashSet<String> r1, String r2) {
        String r22 = normalizeRestrictedLanguage(r2);
        if (TextUtils.isEmpty(r22) == false) goto L6;
        return;
    L6:
        if (TranslateAlert2.languageName(r22) == null) goto L9;
        r1.add(r22);
        return;
    }

    public static void checkRestrictedLanguages(boolean r4) {
        boolean r0 = MessagesController.getGlobalMainSettings().getBoolean("translate_button_restricted_languages_changed", false);
        if (MessagesController.getGlobalMainSettings().getInt("translate_button_restricted_languages_version", 0) != 2) goto L8;
        if (r4 == false) goto L7;
        if (r0 == false) goto L8;
        return;
    L7:
        return;
    L8:
        getExtendedDoNotTranslate(new RestrictedLanguagesSelectActivity$$ExternalSyntheticLambda0());
    }

    public static void cleanup() {
        invalidateRestrictedLanguages();
        MessagesController.getGlobalMainSettings().edit().remove("translate_button_restricted_languages_changed").remove("translate_button_restricted_languages_version").remove("translate_button_restricted_languages").apply();
        checkRestrictedLanguages(false);
    }

    private void fillLanguages() {
        this.allLanguages = TranslateController.getLanguages();
        TranslateController.Language r1 = null;
        if (this.type != 1) goto L36;
        this.allLanguages = TranslatorUtils.getCurrentTargetLanguages();
        String r0 = TranslateAlert2.getToLanguage();
        boolean r5 = TranslatorUtils.isTargetLanguageFollowApp();
        TranslateController.Language r6 = new TranslateController.Language();
        r6.code = Common.ASSET_APP;
        r6.ownDisplayName = LocaleController.getString(R.string.TranslationTargetApp);
        String r7 = TranslatorUtils.getResolvedTargetLanguageCode(Common.ASSET_APP);
        String r8 = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r7));
        if (TextUtils.isEmpty(r8) == false) goto L10;
        if (r7 != null) goto L9;
        String r72 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r8 = r72;
        goto L10
    L9:
        r72 = r7.toUpperCase(Locale.US);
    L10:
        r6.displayName = r8;
        r6.q = (r6.ownDisplayName + " " + r6.displayName).toLowerCase(Locale.US);
        if (r5 == false) goto L13;
    L29:
        this.separatorRow = 0;
        this.allLanguages.add(0, r6);
        this.separatorRow++;
        if (r1 == null) goto L33;
        this.allLanguages.add(1, r1);
        this.separatorRow++;
    L33:
        if (this.separatorRow > 0) goto L64;
        this.separatorRow = -1;
        return;
    L64:
        return;
    L13:
        if (TextUtils.isEmpty(r0) == true) goto L29;
        int r52 = 0;
    L16:
        if (r52 >= this.allLanguages.size()) goto L21;
        TranslateController.Language r73 = this.allLanguages.get(r52);
        if (TextUtils.equals(r73.code, r0) == true) goto L19;
        r52 = r52 + 1;
        goto L16
    L19:
        this.allLanguages.remove(r52);
        r1 = r73;
    L21:
        if (r1 != null) goto L29;
        r1 = new TranslateController.Language();
        r1.code = r0;
        String r53 = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r0));
        r1.displayName = r53;
        if (TextUtils.isEmpty(r53) == false) goto L25;
        r1.displayName = r0.toUpperCase(Locale.US);
    L25:
        String r02 = TranslateAlert2.capitalFirst(TranslateAlert2.systemLanguageName(r0, true));
        r1.ownDisplayName = r02;
        if (TextUtils.isEmpty(r02) == false) goto L28;
        r1.ownDisplayName = r1.displayName;
    L28:
        r1.q = (r1.displayName + " " + r1.ownDisplayName).toLowerCase(Locale.US);
        goto L29
    L36:
        String r03 = getDefaultRestrictedLanguage();
        ArrayList r54 = new ArrayList();
        ArrayList r62 = new ArrayList(this.firstSelectedLanguages);
        int r74 = 0;
    L38:
        if (r74 >= this.allLanguages.size()) goto L46;
        TranslateController.Language r82 = this.allLanguages.get(r74);
        String r9 = normalizeRestrictedLanguage(r82.code);
        if (TextUtils.equals(r9, r03) == false) goto L43;
        r62.remove(r9);
        this.allLanguages.remove(r74);
        r74 = r74 - 1;
        r1 = r82;
    L45:
        r74 = r74 + 1;
        goto L38
    L43:
        if (this.firstSelectedLanguages.contains(r9) == false) goto L45;
        r54.add(r82);
        r62.remove(r9);
        this.allLanguages.remove(r74);
        r74 = r74 - 1;
        goto L45
    L46:
        int r04 = 0;
    L48:
        if (r04 >= r62.size()) goto L50;
        TranslateController.Language r75 = new TranslateController.Language();
        String r83 = (String) r62.get(r04);
        r75.code = r83;
        String r84 = r83.toUpperCase();
        r75.displayName = r84;
        r75.ownDisplayName = r84;
        r75.q = r75.code.toLowerCase();
        r54.add(r75);
        r04 = r04 + 1;
        goto L48
    L50:
        this.separatorRow = 0;
        this.allLanguages.addAll(0, r54);
        this.separatorRow += r54.size();
        if (r1 == null) goto L54;
        this.allLanguages.add(0, r1);
        this.separatorRow++;
    L54:
        if (this.separatorRow > 0) goto L65;
        this.separatorRow = -1;
        return;
    }

    private static String getDefaultRestrictedLanguage() {
        return normalizeRestrictedLanguage(TranslatorUtils.getResolvedTargetLanguageCode());
    }

    public static void getExtendedDoNotTranslate(Utilities.Callback<HashSet<String>> r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        HashSet r0 = new HashSet();
        Utilities.doCallbacks(new Utilities.Callback[]{new RestrictedLanguagesSelectActivity$$ExternalSyntheticLambda1(r0), new RestrictedLanguagesSelectActivity$$ExternalSyntheticLambda2(r0), new RestrictedLanguagesSelectActivity$$ExternalSyntheticLambda3(r0), new RestrictedLanguagesSelectActivity$$ExternalSyntheticLambda4(r5, r0)});
    }

    public static HashSet<String> getRestrictedLanguages() {
        if (gotRestrictedLanguages == true) goto L6;
        restrictedLanguages = sanitizeRestrictedLanguages(MessagesController.getGlobalMainSettings().getStringSet("translate_button_restricted_languages", null));
        gotRestrictedLanguages = true;
    L6:
        if (restrictedLanguages != null) goto L11;
        restrictedLanguages = Sets.newHashSet();
        String r0 = getDefaultRestrictedLanguage();
        if (TextUtils.isEmpty(r0) == true) goto L11;
        restrictedLanguages.add(r0);
    L11:
        return restrictedLanguages;
    }

    public static void invalidateRestrictedLanguages() {
        gotRestrictedLanguages = false;
    }

    private boolean isTargetChecked(String r1) {
        if (TextUtils.equals(r1, Common.ASSET_APP) == false) goto L7;
        return TranslatorUtils.isTargetLanguageFollowApp();
    L7:
        if (TranslatorUtils.isTargetLanguageFollowApp() == false) goto L11;
        return false;
    L11:
        return TextUtils.equals(r1, TranslateAlert2.getToLanguage());
    }

    private /* synthetic */ void lambda$createView$1(View r7, int r8) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (this.parentLayout != null) goto L7;
        return;
    L7:
        if ((r7 instanceof TextCheckbox2Cell) == false) goto L74;
        int r1 = 0;
        if (this.listView.getAdapter() != this.searchListViewAdapter) goto L12;
        boolean r72 = true;
    L14:
        if (r72 == false) goto L18;
        ArrayList<TranslateController.Language> r3 = this.searchResult;
        if (r3 == null) goto L18;
        TranslateController.Language r82 = r3.get(r8);
    L27:
        if (r82 == null) goto L72;
        String r83 = r82.code;
        if (r83 != null) goto L31;
        return;
    L31:
        if (this.type != 1) goto L34;
        TranslateAlert2.setToLanguage(r83);
        finishFragment();
        return;
    L34:
        String r84 = normalizeRestrictedLanguage(r83);
        String r32 = getDefaultRestrictedLanguage();
        boolean r4 = this.selectedLanguages.contains(r84);
        HashSet<String> r5 = this.selectedLanguages;
        if (r4 == false) goto L37;
        r5.removeIf(new RestrictedLanguagesSelectActivity$$ExternalSyntheticLambda6(r84));
    L39:
        if (this.selectedLanguages.size() == 1) goto L41;
    L43:
        updateRestrictedLanguages(this.selectedLanguages, Boolean.TRUE);
    L44:
        if (r72 == false) goto L52;
        int r73 = 0;
    L47:
        if (r1 >= this.searchResult.size()) goto L62;
        if (TextUtils.equals(normalizeRestrictedLanguage(this.searchResult.get(r1).code), r84) == false) goto L51;
        rebind(r73);
    L51:
        r1 = r1 + 1;
        r73 = r73 + 1;
    L62:
        MessagesController.getInstance(this.currentAccount).getTranslateController().checkRestrictedLanguagesUpdate();
        return;
    L52:
        int r74 = 0;
    L54:
        if (r1 >= this.allLanguages.size()) goto L62;
        if (r74 != this.separatorRow) goto L59;
        r74 = r74 + 1;
    L59:
        if (TextUtils.equals(normalizeRestrictedLanguage(this.allLanguages.get(r1).code), r84) == false) goto L61;
        rebind(r74);
    L61:
        r1 = r1 + 1;
        r74 = r74 + 1;
        goto L54
    L41:
        if (this.selectedLanguages.contains(r32) == false) goto L43;
        updateRestrictedLanguages(null, null);
        goto L44
    L37:
        r5.add(r84);
        goto L39
    L72:
        return;
    L18:
        int r33 = this.separatorRow;
        if (r33 < 0) goto L22;
        if (r8 <= r33) goto L22;
        r8 = r8 - 1;
    L22:
        if (r8 >= 0) goto L24;
    L26:
        r82 = null;
        goto L27
    L24:
        if (r8 >= this.allLanguages.size()) goto L26;
        r82 = this.allLanguages.get(r8);
        goto L27
    L12:
        r72 = false;
        goto L14
    }

    private static String normalizeRestrictedLanguage(String r3) {
        if (r3 != null) goto L5;
        String r32 = null;
    L6:
        String r33 = TranslatorUtils.normalizeLanguageCode(r32);
        if (TextUtils.isEmpty(r33) == false) goto L9;
        return null;
    L9:
        int r1 = r33.indexOf(45);
        if (r1 >= 0) goto L12;
        return r33;
    L12:
        return r33.substring(0, r1);
    L5:
        r32 = r3.split("_")[0];
        goto L6
    }

    private void processSearch(String r5) {
        String r52 = r5.trim().toLowerCase();
        ArrayList<TranslateController.Language> r0 = this.searchResult;
        if (r0 != null) goto L5;
        this.searchResult = new ArrayList();
    L6:
        int r1 = 0;
    L8:
        if (r1 >= this.allLanguages.size()) goto L16;
        TranslateController.Language r2 = this.allLanguages.get(r1);
        if (r2.q.startsWith(r52) == false) goto L13;
        this.searchResult.add(0, r2);
    L15:
        r1 = r1 + 1;
        goto L8
    L13:
        if (r2.q.contains(r52) == false) goto L15;
        this.searchResult.add(r2);
        goto L15
    L16:
        this.searchListViewAdapter.notifyDataSetChanged();
        return;
    L5:
        r0.clear();
        goto L6
    }

    private void rebind(int r6) {
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L15;
        View r2 = this.listView.getChildAt(r1);
        RecyclerView.ViewHolder r22 = this.listView.getChildViewHolder(r2);
        if (r22 == null) goto L14;
        int r3 = r22.getAdapterPosition();
        if (r3 == (-1)) goto L14;
        if (r3 != r6) goto L14;
        r0.onBindViewHolder(r22, r6);
        return;
    L14:
        r1 = r1 + 1;
        goto L4
    }

    private static HashSet<String> sanitizeRestrictedLanguages(Set<String> r4) {
        if (r4 != null) goto L5;
    L17:
        return null;
    L5:
        if (r4.isEmpty() == true) goto L17;
        HashSet<String> r1 = new HashSet();
        Iterator<String> r42 = r4.iterator();
    L9:
        if (r42.hasNext() == false) goto L14;
        String r2 = normalizeRestrictedLanguage(r42.next());
        if (TextUtils.isEmpty(r2) == true) goto L9;
        r1.add(r2);
        goto L9
    L14:
        if (r1.isEmpty() == false) goto L16;
        return null;
    L16:
        return r1;
    }

    public static boolean toggleLanguage(String r2, boolean r3) {
        if (r2 != null) goto L5;
        return false;
    L5:
        String r22 = normalizeRestrictedLanguage(r2);
        if (TextUtils.isEmpty(r22) == false) goto L8;
        return false;
    L8:
        String r0 = getDefaultRestrictedLanguage();
        HashSet<String> r1 = getRestrictedLanguages();
        if (r3 == true) goto L11;
        r1.remove(r22);
    L13:
        if (r1.size() == 1) goto L15;
    L17:
        updateRestrictedLanguages(r1, Boolean.FALSE);
    L18:
        TranslateController.invalidateSuggestedLanguageCodes();
        return true;
    L15:
        if (r1.contains(r0) == false) goto L17;
        updateRestrictedLanguages(null, Boolean.FALSE);
        goto L18
    L11:
        r1.add(r22);
        goto L13
    }

    public static void updateRestrictedLanguages(HashSet<String> r3, Boolean r4) {
        restrictedLanguages = sanitizeRestrictedLanguages(r3);
        gotRestrictedLanguages = true;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        HashSet<String> r1 = restrictedLanguages;
        if (r1 != null) goto L5;
        r0.remove("translate_button_restricted_languages");
    L7:
        if (r4 != null) goto L10;
        r0.remove("translate_button_restricted_languages_changed");
    L12:
        r0.apply();
        return;
    L10:
        if (r4.booleanValue() == false) goto L12;
        r0.putBoolean("translate_button_restricted_languages_changed", true);
        goto L12
    L5:
        r0.putStringSet("translate_button_restricted_languages", r1);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        ActionBar r0 = this.actionBar;
        if (this.type != 1) goto L5;
        int r2 = R.string.TranslationTarget;
    L6:
        r0.setTitle(LocaleController.getString(r2));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this)).setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.listAdapter = new ListAdapter(this, r8, false);
        this.searchListViewAdapter = new ListAdapter(this, r8, true);
        FrameLayout r02 = new FrameLayout(r8);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r03 = (FrameLayout) this.fragmentView;
        EmptyTextProgressView r22 = new EmptyTextProgressView(r8);
        this.emptyView = r22;
        r22.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.showTextView();
        this.emptyView.setShowAtCenter(true);
        r03.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r23 = new RecyclerListView(r8);
        this.listView = r23;
        r23.setSections();
        this.listView.setEmptyView(this.emptyView);
        this.listView.setLayoutManager(new LinearLayoutManager(r8, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setAdapter(this.listAdapter);
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setOnItemClickListener(new RestrictedLanguagesSelectActivity$$ExternalSyntheticLambda5(this));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        return this.fragmentView;
    L5:
        r2 = R.string.DoNotTranslate;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.suggestedLangpack) goto L5;
        return;
    L5:
        if (this.listAdapter == null) goto L9;
        fillLanguages();
        this.listAdapter.notifyDataSetChanged();
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
    public boolean onFragmentCreate() {
        if (this.type != 0) goto L5;
        this.firstSelectedLanguages = getRestrictedLanguages();
        this.selectedLanguages = getRestrictedLanguages();
    L5:
        fillLanguages();
        LocaleController.getInstance().loadRemoteLanguages(this.currentAccount);
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
        if (this.listAdapter == null) goto L6;
        fillLanguages();
        this.listAdapter.notifyDataSetChanged();
        return;
    }

    public void search(String r1) {
        if (r1 != null) goto L5;
        this.searchResult = null;
        return;
    L5:
        processSearch(r1);
    }
}
