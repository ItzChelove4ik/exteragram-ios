package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.DividerCell;
import org.telegram.ui.Cells.LetterSectionCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.BoostRepository$$ExternalSyntheticLambda23;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class CountrySelectActivity extends BaseFragment {
    private CountrySelectActivityDelegate delegate;
    private boolean disableAnonymousNumbers;
    private EmptyTextProgressView emptyView;
    private ArrayList<Country> existingCountries;
    private RecyclerListView listView;
    private CountryAdapter listViewAdapter;
    private boolean needPhoneCode;
    private CountrySearchAdapter searchListViewAdapter;
    private boolean searchWas;
    private boolean searching;

    /* renamed from: org.telegram.ui.CountrySelectActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CountrySelectActivity this$0;

        public AnonymousClass1(CountrySelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.CountrySelectActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ CountrySelectActivity this$0;

        public AnonymousClass2(CountrySelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            CountrySelectActivity.m14158$$Nest$fgetsearchListViewAdapter(this.this$0).search(null);
            CountrySelectActivity.m14162$$Nest$fputsearching(this.this$0, false);
            CountrySelectActivity.m14161$$Nest$fputsearchWas(this.this$0, false);
            CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0).setAdapter(CountrySelectActivity.m14156$$Nest$fgetlistViewAdapter(this.this$0));
            CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0).setFastScrollVisible(true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            CountrySelectActivity.m14162$$Nest$fputsearching(this.this$0, true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r4) {
            String r42 = r4.getText().toString();
            boolean r0 = TextUtils.isEmpty(r42);
            CountrySelectActivity r1 = this.this$0;
            if (r0 == false) goto L6;
            CountrySelectActivity.m14158$$Nest$fgetsearchListViewAdapter(r1).search(null);
            CountrySelectActivity.m14161$$Nest$fputsearchWas(this.this$0, false);
            CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0).setAdapter(CountrySelectActivity.m14156$$Nest$fgetlistViewAdapter(this.this$0));
            CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0).setFastScrollVisible(true);
            return;
        L6:
            CountrySelectActivity.m14158$$Nest$fgetsearchListViewAdapter(r1).search(r42);
            if (r42.length() == 0) goto L10;
            CountrySelectActivity.m14161$$Nest$fputsearchWas(this.this$0, true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.CountrySelectActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ CountrySelectActivity this$0;

        public AnonymousClass3(CountrySelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    /* renamed from: org.telegram.ui.CountrySelectActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnAttachStateChangeListener {
        private NotificationCenter.NotificationCenterDelegate listener;
        final /* synthetic */ TextSettingsCell val$view;

        public static /* synthetic */ void $r8$lambda$0DoFj1EMwzi9gkncDjaoCqlCJDY(TextSettingsCell r0, int r1, int r2, Object[] r3) {
            if (r1 != NotificationCenter.emojiLoaded) goto L6;
            r0.getTextView().invalidate();
            return;
        }

        public AnonymousClass4(TextSettingsCell r2) {
            this.val$view = r2;
            this.listener = new CountrySelectActivity$4$$ExternalSyntheticLambda0(r2);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r2) {
            NotificationCenter.getGlobalInstance().addObserver(this.listener, NotificationCenter.emojiLoaded);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            NotificationCenter.getGlobalInstance().removeObserver(this.listener, NotificationCenter.emojiLoaded);
        }
    }

    /* renamed from: org.telegram.ui.CountrySelectActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ReplacementSpan {
        public AnonymousClass5() {
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return AndroidUtilities.dp(16.0f);
        }
    }

    public static class Country {
        public String code;
        public String defaultName;
        public String name;
        public String shortname;

        public Country() {
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L15;
            Country r52 = (Country) r5;
            if (Objects.equals(this.name, r52.name) == false) goto L15;
            if (Objects.equals(this.code, r52.code) == false) goto L15;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.name, this.code});
        }
    }

    public class CountryAdapter extends RecyclerListView.SectionsAdapter {
        private HashMap<String, ArrayList<Country>> countries;
        private Context mContext;
        private ArrayList<String> sortedCountries;
        final /* synthetic */ CountrySelectActivity this$0;

        public static /* synthetic */ int $r8$lambda$ISbv4flrMof4PrwI_VdzjWl3ozQ(Comparator r0, Country r1, Country r2) {
            return r0.compare(r1.name, r2.name);
        }

        public CountryAdapter(CountrySelectActivity r6, Context r7, ArrayList<Country> r8, boolean r9) {
            this.this$0 = r6;
            this.countries = new HashMap();
            this.sortedCountries = new ArrayList();
            this.mContext = r7;
            if (r8 == null) goto L36;
            int r92 = 0;
        L6:
            if (r92 >= r8.size()) goto L28;
            Country r0 = r8.get(r92);
            String r1 = r0.name.substring(0, 1).toUpperCase();
            ArrayList<Country> r2 = this.countries.get(r1);
            if (r2 != null) goto L10;
            r2 = new ArrayList();
            this.countries.put(r1, r2);
            this.sortedCountries.add(r1);
        L10:
            r2.add(r0);
            r92 = r92 + 1;
        L28:
            if (LocaleController.getInstance().getCurrentLocale() == null) goto L30;
            Locale r62 = LocaleController.getInstance().getCurrentLocale();
        L31:
            Collator r63 = Collator.getInstance(r62);
            Objects.requireNonNull(r63);
            BoostRepository$$ExternalSyntheticLambda23 r72 = new BoostRepository$$ExternalSyntheticLambda23(r63);
            Collections.sort(this.sortedCountries, r72);
            Iterator<ArrayList<Country>> r5 = this.countries.values().iterator();
        L33:
            if (r5.hasNext() == false) goto L35;
            Collections.sort(r5.next(), new CountrySelectActivity$CountryAdapter$$ExternalSyntheticLambda0(r72));
            goto L33
        L35:
            return;
        L30:
            r62 = Locale.getDefault();
            goto L31
        L36:
            InputStream r82 = ApplicationLoader.applicationContext.getResources().getAssets().open("countries.txt");     // Catch: Exception -> L21
            BufferedReader r02 = new BufferedReader(new InputStreamReader(r82));     // Catch: Exception -> L21
        L12:
            String r12 = r02.readLine();     // Catch: Exception -> L21
            if (r12 == null) goto L24;
            String[] r13 = r12.split(";");     // Catch: Exception -> L21
            Country r22 = new Country();     // Catch: Exception -> L21
            r22.name = r13[2];     // Catch: Exception -> L21
            r22.code = r13[0];     // Catch: Exception -> L21
            String r14 = r13[1];     // Catch: Exception -> L21
            r22.shortname = r14;     // Catch: Exception -> L21
            if (r14.equals("FT") == false) goto L18;
            if (r9 == true) goto L12;
        L18:
            String r15 = r22.name.substring(0, 1).toUpperCase();     // Catch: Exception -> L21
            ArrayList<Country> r3 = this.countries.get(r15);     // Catch: Exception -> L21
            if (r3 != null) goto L23;
            r3 = new ArrayList();     // Catch: Exception -> L21
            this.countries.put(r15, r3);     // Catch: Exception -> L21
            this.sortedCountries.add(r15);     // Catch: Exception -> L21
        L23:
            r3.add(r22);     // Catch: Exception -> L21
            goto L12
        L24:
            r02.close();     // Catch: Exception -> L21
            r82.close();     // Catch: Exception -> L21
        L21:
            e = move-exception;
            FileLog.e(e);
            goto L28
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r3) {
            int r0 = this.countries.get(this.sortedCountries.get(r3)).size();
            if (r3 != (this.sortedCountries.size() - 1)) goto L5;
            return r0;
        L5:
            return r0 + 1;
        }

        public HashMap<String, ArrayList<Country>> getCountries() {
            return this.countries;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public /* bridge */ /* synthetic */ Object getItem(int r1, int r2) {
            return getItem(r1, r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Country getItem(int r3, int r4) {
            if (r3 >= 0) goto L5;
        L14:
            return null;
        L5:
            if (r3 >= this.sortedCountries.size()) goto L14;
            ArrayList<Country> r2 = this.countries.get(this.sortedCountries.get(r3));
            if (r4 < 0) goto L14;
            if (r4 >= r2.size()) goto L14;
            return r2.get(r4);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r2, int r3) {
            if (r3 >= this.countries.get(this.sortedCountries.get(r2)).size()) goto L6;
            return 0;
        L6:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r2) {
            int r22 = getSectionForPosition(r2);
            if (r22 != (-1)) goto L6;
            r22 = this.sortedCountries.size() - 1;
        L6:
            return this.sortedCountries.get(r22);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = (int) (getItemCount() * r2);
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getSectionCount() {
            return this.sortedCountries.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r1, View r2) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1, int r2, int r3) {
            if (r3 >= this.countries.get(this.sortedCountries.get(r2)).size()) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r3, int r4, RecyclerView.ViewHolder r5) {
            if (r5.getItemViewType() != 0) goto L10;
            Country r32 = this.countries.get(this.sortedCountries.get(r3)).get(r4);
            TextSettingsCell r42 = (TextSettingsCell) r5.itemView;
            CharSequence r52 = Emoji.replaceEmoji(CountrySelectActivity.m14164$$Nest$smgetCountryNameWithFlag(r32), r42.getTextView().getPaint().getFontMetricsInt(), false);
            if (CountrySelectActivity.m14157$$Nest$fgetneedPhoneCode(this.this$0) == false) goto L7;
            String r2 = "+" + r32.code;
        L8:
            r42.setTextAndValue(r52, r2, false);
            return;
        L7:
            r2 = null;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 == 0) goto L4;
            View r32 = new DividerCell(this.mContext);
            r32.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(8.0f));
        L6:
            return new RecyclerListView.Holder(r32);
        L4:
            r32 = CountrySelectActivity.m14163$$Nest$smcreateSettingsCell(this.mContext);
            goto L6
        }
    }

    public class CountrySearchAdapter extends RecyclerListView.SelectionAdapter {
        private List<Country> countryList;
        private Context mContext;
        private ArrayList<Country> searchResult;
        private Timer searchTimer;
        final /* synthetic */ CountrySelectActivity this$0;

        /* renamed from: org.telegram.ui.CountrySelectActivity$CountrySearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends TimerTask {
            final /* synthetic */ CountrySearchAdapter this$1;
            final /* synthetic */ String val$query;

            public AnonymousClass1(CountrySearchAdapter r1, String r2) {
                this.this$1 = r1;
                this.val$query = r2;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CountrySearchAdapter.m14165$$Nest$fgetsearchTimer(this.this$1).cancel();     // Catch: Exception -> L4
                CountrySearchAdapter.m14166$$Nest$fputsearchTimer(this.this$1, null);     // Catch: Exception -> L4
            L6:
                CountrySearchAdapter.m14167$$Nest$mprocessSearch(this.this$1, this.val$query);
                return;
            L4:
                e = move-exception;
                FileLog.e(e);
                goto L6
            }
        }

        public static /* synthetic */ void $r8$lambda$Dg07VA9jTh6DMUJH20MW7Cl8JSc(CountrySearchAdapter r0, ArrayList r1) {
            r0.lambda$updateSearchResults$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$jpYDvvNC8cRVR_aRGkpaSTje4JI(CountrySearchAdapter r0, String r1) {
            r0.lambda$processSearch$0(r1);
        }

        /* renamed from: -$$Nest$fgetsearchTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Timer m14165$$Nest$fgetsearchTimer(CountrySearchAdapter r0) {
            return r0.searchTimer;
        }

        /* renamed from: -$$Nest$fputsearchTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14166$$Nest$fputsearchTimer(CountrySearchAdapter r0, Timer r1) {
            r0.searchTimer = r1;
        }

        /* renamed from: -$$Nest$mprocessSearch, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14167$$Nest$mprocessSearch(CountrySearchAdapter r0, String r1) {
            r0.processSearch(r1);
        }

        public CountrySearchAdapter(CountrySelectActivity r2, Context r3, HashMap<String, ArrayList<Country>> r4) {
            this.this$0 = r2;
            this.countryList = new ArrayList();
            this.mContext = r3;
            Iterator<ArrayList<Country>> r22 = r4.values().iterator();
        L4:
            if (r22.hasNext() == false) goto L9;
            Iterator<Country> r32 = r22.next().iterator();
        L7:
            if (r32.hasNext() == false) goto L4;
            Country r42 = r32.next();
            this.countryList.add(r42);
            goto L7
        }

        private /* synthetic */ void lambda$processSearch$0(String r13) {
            String r132 = r13.trim().toLowerCase();
            if (r132.length() != 0) goto L6;
            updateSearchResults(new ArrayList());
            return;
        L6:
            String r0 = AndroidUtilities.translitSafe(r132);
            ArrayList<Country> r1 = new ArrayList();
            Iterator<Country> r2 = this.countryList.iterator();
        L8:
            if (r2.hasNext() == false) goto L43;
            Country r3 = r2.next();
            String r4 = r3.name;
            String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r4 != null) goto L12;
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            String r42 = r4.toLowerCase();
            String r6 = AndroidUtilities.translitSafe(r3.name).toLowerCase();
            String r7 = r3.defaultName;
            if (r7 != null) goto L15;
            r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        L15:
            String r72 = r7.toLowerCase();
            String r8 = AndroidUtilities.translitSafe(r3.defaultName).toLowerCase();
            String r9 = r3.code;
            if (r9 != null) goto L19;
            r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        L19:
            if (TextUtils.isEmpty(r9) == true) goto L23;
            r5 = "+" + r9;
        L23:
            if (r42.startsWith(r132) == true) goto L42;
            if (r42.contains(" ".concat(r132)) == true) goto L42;
            if (r6.startsWith(r0) == true) goto L42;
            if (r6.contains(" " + r0) == true) goto L42;
            if (r72.startsWith(r132) == true) goto L42;
            if (r72.contains(" ".concat(r132)) == true) goto L42;
            if (r8.startsWith(r0) == true) goto L42;
            if (r8.contains(" " + r0) == true) goto L42;
            if (r9.startsWith(r132) == true) goto L42;
            if (r5.startsWith(r132) == false) goto L8;
        L42:
            r1.add(r3);
            goto L8
        L43:
            updateSearchResults(r1);
        }

        private /* synthetic */ void lambda$updateSearchResults$1(ArrayList r2) {
            if (CountrySelectActivity.m14160$$Nest$fgetsearching(this.this$0) == true) goto L5;
            return;
        L5:
            this.searchResult = r2;
            if (CountrySelectActivity.m14159$$Nest$fgetsearchWas(this.this$0) == true) goto L8;
        L12:
            notifyDataSetChanged();
            return;
        L8:
            if (CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0) == null) goto L12;
            if (CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0).getAdapter() == CountrySelectActivity.m14158$$Nest$fgetsearchListViewAdapter(this.this$0)) goto L12;
            CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0).setAdapter(CountrySelectActivity.m14158$$Nest$fgetsearchListViewAdapter(this.this$0));
            CountrySelectActivity.m14155$$Nest$fgetlistView(this.this$0).setFastScrollVisible(false);
            goto L12
        }

        private void processSearch(String r3) {
            Utilities.searchQueue.postRunnable(new CountrySelectActivity$CountrySearchAdapter$$ExternalSyntheticLambda0(this, r3));
        }

        private void updateSearchResults(ArrayList<Country> r2) {
            AndroidUtilities.runOnUIThread(new CountrySelectActivity$CountrySearchAdapter$$ExternalSyntheticLambda1(this, r2));
        }

        public Country getItem(int r2) {
            ArrayList<Country> r0 = this.searchResult;
            if (r0 == null) goto L10;
            if (r2 >= 0) goto L6;
            return null;
        L6:
            if (r2 < r0.size()) goto L9;
            return null;
        L9:
            return this.searchResult.get(r2);
        L10:
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            ArrayList<Country> r0 = this.searchResult;
            if (r0 != null) goto L7;
            return 0;
        L7:
            return r0.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            Country r52 = this.searchResult.get(r5);
            TextSettingsCell r42 = (TextSettingsCell) r4.itemView;
            CharSequence r0 = Emoji.replaceEmoji(CountrySelectActivity.m14164$$Nest$smgetCountryNameWithFlag(r52), r42.getTextView().getPaint().getFontMetricsInt(), false);
            if (CountrySelectActivity.m14157$$Nest$fgetneedPhoneCode(this.this$0) == false) goto L5;
            String r3 = "+" + r52.code;
        L6:
            r42.setTextAndValue(r0, r3, false);
            return;
        L5:
            r3 = null;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return new RecyclerListView.Holder(CountrySelectActivity.m14163$$Nest$smcreateSettingsCell(this.mContext));
        }

        public void search(String r8) {
            if (r8 != null) goto L13;
            this.searchResult = null;
            return;
        L13:
            Timer r0 = this.searchTimer;     // Catch: Exception -> L9
            if (r0 == null) goto L11;
            r0.cancel();     // Catch: Exception -> L9
        L11:
            Timer r1 = new Timer();
            this.searchTimer = r1;
            r1.schedule(new AnonymousClass1(this, r8), 100, 300);
            return;
        L9:
            e = move-exception;
            FileLog.e(e);
            goto L11
        }
    }

    public interface CountrySelectActivityDelegate {
        void didSelectCountry(Country r1);
    }

    public static /* synthetic */ void $r8$lambda$XDduNvXfXSq0LWv9EVmhwwr49mM(CountrySelectActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m14155$$Nest$fgetlistView(CountrySelectActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlistViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CountryAdapter m14156$$Nest$fgetlistViewAdapter(CountrySelectActivity r0) {
        return r0.listViewAdapter;
    }

    /* renamed from: -$$Nest$fgetneedPhoneCode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14157$$Nest$fgetneedPhoneCode(CountrySelectActivity r0) {
        return r0.needPhoneCode;
    }

    /* renamed from: -$$Nest$fgetsearchListViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CountrySearchAdapter m14158$$Nest$fgetsearchListViewAdapter(CountrySelectActivity r0) {
        return r0.searchListViewAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14159$$Nest$fgetsearchWas(CountrySelectActivity r0) {
        return r0.searchWas;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14160$$Nest$fgetsearching(CountrySelectActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14161$$Nest$fputsearchWas(CountrySelectActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14162$$Nest$fputsearching(CountrySelectActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$smcreateSettingsCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextSettingsCell m14163$$Nest$smcreateSettingsCell(Context r0) {
        return createSettingsCell(r0);
    }

    /* renamed from: -$$Nest$smgetCountryNameWithFlag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m14164$$Nest$smgetCountryNameWithFlag(Country r0) {
        return getCountryNameWithFlag(r0);
    }

    public CountrySelectActivity(boolean r2) {
        this(r2, null);
    }

    public CountrySelectActivity(boolean r2, ArrayList<Country> r3) {
        if (r3 != null) goto L5;
    L7:
        this.needPhoneCode = r2;
        return;
    L5:
        if (r3.isEmpty() == true) goto L7;
        this.existingCountries = new ArrayList(r3);
        goto L7
    }

    private static TextSettingsCell createSettingsCell(Context r4) {
        TextSettingsCell r0 = new TextSettingsCell(r4);
        float r1 = 12.0f;
        if (LocaleController.isRTL == false) goto L5;
        float r42 = 16.0f;
    L6:
        int r43 = AndroidUtilities.dp(r42);
        if (LocaleController.isRTL == true) goto L10;
        r1 = 16.0f;
    L10:
        r0.setPadding(r43, 0, AndroidUtilities.dp(r1), 0);
        r0.addOnAttachStateChangeListener(new AnonymousClass4(r0));
        return r0;
    L5:
        r42 = 12.0f;
        goto L6
    }

    private static CharSequence getCountryNameWithFlag(Country r5) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        String r1 = LocaleController.getLanguageFlag(r5.shortname);
        if (r1 == null) goto L5;
        r0.append(r1).append(" ");
        r0.setSpan(new AnonymousClass5(), r1.length(), r1.length() + 1, 0);
    L5:
        r0.append(r5.name);
        return r0;
    }

    private /* synthetic */ void lambda$createView$0(View r3, int r4) {
        if (this.searching == true) goto L5;
    L7:
        int r32 = this.listViewAdapter.getSectionForPosition(r4);
        int r0 = this.listViewAdapter.getPositionInSectionForPosition(r4);
        if (r0 < 0) goto L20;
        if (r32 < 0) goto L23;
        Country r33 = this.listViewAdapter.getItem(r32, r0);
    L12:
        if (r4 < 0) goto L24;
        finishFragment();
        if (r33 == null) goto L21;
        CountrySelectActivityDelegate r2 = this.delegate;
        if (r2 == null) goto L22;
        r2.didSelectCountry(r33);
        return;
    L22:
        return;
    L21:
        return;
    L24:
        return;
    L23:
        return;
    L20:
        return;
    L5:
        if (this.searchWas == false) goto L7;
        r33 = this.searchListViewAdapter.getItem(r4);
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setTitle(LocaleController.getString(R.string.ChooseCountry));
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        ActionBar r0 = this.actionBar;
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setItemsColor(Theme.getColor(r2), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarWhiteSelector), false);
        this.actionBar.setTitleColor(Theme.getColor(r2));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenuItem r02 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search);
        int r3 = 1;
        r02.setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this)).setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.actionBar.setSearchTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText), true);
        this.actionBar.setSearchTextColor(Theme.getColor(r2), false);
        this.actionBar.setSearchCursorColor(Theme.getColor(r2));
        this.searching = false;
        this.searchWas = false;
        CountryAdapter r03 = new CountryAdapter(this, r8, this.existingCountries, this.disableAnonymousNumbers);
        this.listViewAdapter = r03;
        this.searchListViewAdapter = new CountrySearchAdapter(this, r8, r03.getCountries());
        FrameLayout r04 = new FrameLayout(r8);
        this.fragmentView = r04;
        EmptyTextProgressView r22 = new EmptyTextProgressView(r8);
        this.emptyView = r22;
        r22.showTextView();
        this.emptyView.setShowAtCenter(true);
        this.emptyView.setText(LocaleController.getString(R.string.NoResult));
        r04.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r23 = new RecyclerListView(r8);
        this.listView = r23;
        r23.setSectionsType(3);
        this.listView.setEmptyView(this.emptyView);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setFastScrollEnabled(0);
        this.listView.setFastScrollVisible(true);
        this.listView.setLayoutManager(new LinearLayoutManager(r8, 1, false));
        this.listView.setAdapter(this.listViewAdapter);
        RecyclerListView r82 = this.listView;
        if (LocaleController.isRTL == true) goto L6;
        r3 = 2;
    L6:
        r82.setVerticalScrollbarPosition(r3);
        r04.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new CountrySelectActivity$$ExternalSyntheticLambda0(this));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_windowBackgroundWhite);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        ActionBar r4 = this.actionBar;
        int r5 = ThemeDescription.FLAG_BACKGROUND;
        int r10 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollActive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollInactive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollText));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SECTIONS, new Class[]{LetterSectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        CountryAdapter r0 = this.listViewAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    public void setCountrySelectActivityDelegate(CountrySelectActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setDisableAnonymousNumbers(boolean r1) {
        this.disableAnonymousNumbers = r1;
    }
}
