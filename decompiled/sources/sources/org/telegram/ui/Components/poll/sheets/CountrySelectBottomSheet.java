package org.telegram.ui.Components.poll.sheets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.GradientProtectionDrawable;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.FragmentSearchField;
import org.telegram.ui.Components.FragmentSpansContainer;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorCountryCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class CountrySelectBottomSheet extends BottomSheetWithRecyclerListView implements FactorAnimator.Target {
    private UniversalAdapter adapter;
    private final FactorAnimator animatorSelectorContainerHeight;
    private final BoolAnimator animatorTopSaveButtonVisibility;
    private final FrameLayout bulletinContainer;
    private final ButtonWithCounterView button;
    private final FrameLayout buttonContainer;
    private final List<String> countriesLetters;
    private final List<TLRPC.TL_help_country> countriesList;
    private final Map<String, List<TLRPC.TL_help_country>> countriesMap;
    private Set<String> countriesToSelect;
    private GroupCreateSpan currentDeletingSpan;
    private final TextView doneItem;
    private final GraySectionCell graySectionCell;
    private final Rect listViewClipBounds;
    private Listener listener;
    private final int maxCountriesCount;
    private String query;
    private final FrameLayout searchContainer;
    private final FragmentSearchField searchField;
    private final HashMap<String, GroupCreateSpan> selectedCountries;
    private int selectedCountriesHeight;
    private final FragmentSpansContainer spansContainer;

    /* renamed from: org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        final /* synthetic */ CountrySelectBottomSheet this$0;

        public AnonymousClass1(CountrySelectBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            CountrySelectBottomSheet.m13751$$Nest$mcheckUi_searchFieldY(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements RecyclerListView.OnItemClickListener {
        final /* synthetic */ CountrySelectBottomSheet this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: $r8$lambda$-2aoByosd-vLjte7i_HJavM9N4A, reason: not valid java name */
        public static /* synthetic */ void m13753$r8$lambda$2aoByosdvLjte7i_HJavM9N4A(CountrySelectBottomSheet r0, View r1) {
            CountrySelectBottomSheet.m13752$$Nest$monSpanClick(r0, r1);
        }

        public AnonymousClass2(CountrySelectBottomSheet r1, Theme.ResourcesProvider r2, Context r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r2;
            this.val$context = r3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
        public void onItemClick(View r5, int r6) {
            if (r6 == 0) goto L20;
            TLRPC.TL_help_country r62 = (TLRPC.TL_help_country) CountrySelectBottomSheet.m13741$$Nest$fgetadapter(this.this$0).getItem(r6 - 1).object;
            if (r62 != null) goto L7;
            return;
        L7:
            boolean r0 = CountrySelectBottomSheet.m13746$$Nest$fgetselectedCountries(this.this$0).containsKey(r62.iso2);
            CountrySelectBottomSheet r2 = this.this$0;
            if (r0 == false) goto L11;
            CountrySelectBottomSheet.m13747$$Nest$fgetspansContainer(this.this$0).removeSpan((GroupCreateSpan) CountrySelectBottomSheet.m13746$$Nest$fgetselectedCountries(r2).remove(r62.iso2));
            boolean r63 = false;
        L16:
            if ((r5 instanceof SelectorCountryCell) == false) goto L18;
            ((SelectorCountryCell) r5).setChecked(r63, true);
        L18:
            CountrySelectBottomSheet.m13741$$Nest$fgetadapter(this.this$0).update(true);
            CountrySelectBottomSheet.m13749$$Nest$mcheckUi_buttonCounter(this.this$0);
            return;
        L11:
            if (CountrySelectBottomSheet.m13746$$Nest$fgetselectedCountries(r2).size() < CountrySelectBottomSheet.m13744$$Nest$fgetmaxCountriesCount(this.this$0)) goto L14;
            BulletinFactory.of(CountrySelectBottomSheet.m13743$$Nest$fgetbulletinContainer(this.this$0), this.val$resourcesProvider).createSimpleBulletin(R.raw.info, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.PollV2YouCanAddXCountriesOnly, new Object[]{Integer.valueOf(CountrySelectBottomSheet.m13744$$Nest$fgetmaxCountriesCount(this.this$0))}))).show();
            return;
        L14:
            GroupCreateSpan r02 = new GroupCreateSpan(this.val$context, r62);
            r02.setOnClickListener(new CountrySelectBottomSheet$2$$ExternalSyntheticLambda0(this.this$0));
            CountrySelectBottomSheet.m13747$$Nest$fgetspansContainer(this.this$0).addSpan(r02);
            CountrySelectBottomSheet.m13746$$Nest$fgetselectedCountries(this.this$0).put(r62.iso2, r02);
            r63 = true;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends TextView {
        final Paint p;
        final /* synthetic */ CountrySelectBottomSheet this$0;

        public AnonymousClass3(CountrySelectBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.p = new Paint(1);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r13) {
            this.p.setColor(CountrySelectBottomSheet.access$000(this.this$0, Theme.key_featuredStickers_addButton));
            r13.drawRoundRect(0.0f, (getHeight() / 2.0f) - AndroidUtilities.dp(14.0f), getWidth(), (getHeight() / 2.0f) + AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.p);
            super.onDraw(r13);
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcherImpl {
        final /* synthetic */ CountrySelectBottomSheet this$0;

        public AnonymousClass4(CountrySelectBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            this.this$0.saveScrollPosition();
            CountrySelectBottomSheet.m13748$$Nest$fputquery(this.this$0, r2.toString());
            CountrySelectBottomSheet.m13741$$Nest$fgetadapter(this.this$0).update(true);
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        final GradientProtectionDrawable gradientProtectionDrawableBottom;
        final GradientProtectionDrawable gradientProtectionDrawableTop;
        final /* synthetic */ CountrySelectBottomSheet this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass5(CountrySelectBottomSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r3;
            super(r2);
            this.gradientProtectionDrawableTop = new GradientProtectionDrawable(2);
            this.gradientProtectionDrawableBottom = new GradientProtectionDrawable(8);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r6, View r7, long r8) {
            boolean r82 = super.drawChild(r6, r7, r8);
            int r9 = (int) CountrySelectBottomSheet.m13742$$Nest$fgetanimatorSelectorContainerHeight(this.this$0).getFactor();
            if (r7 != CountrySelectBottomSheet.m13747$$Nest$fgetspansContainer(this.this$0)) goto L6;
            if (r9 <= 0) goto L6;
            this.gradientProtectionDrawableTop.setBounds(0, AndroidUtilities.dp(40.0f), getWidth(), AndroidUtilities.dp(48.0f));
            GradientProtectionDrawable r72 = this.gradientProtectionDrawableTop;
            int r0 = Theme.key_dialogBackground;
            r72.setColor(Theme.getColor(r0, this.val$resourcesProvider));
            this.gradientProtectionDrawableTop.draw(r6);
            int r73 = AndroidUtilities.dp(48.0f) + r9;
            this.gradientProtectionDrawableBottom.setBounds(0, r73 - AndroidUtilities.dp(8.0f), getWidth(), r73);
            this.gradientProtectionDrawableBottom.setColor(Theme.getColor(r0, this.val$resourcesProvider));
            this.gradientProtectionDrawableBottom.draw(r6);
        L6:
            return r82;
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.ItemDecoration {
        final GradientProtectionDrawable gradientProtectionDrawable;
        final /* synthetic */ CountrySelectBottomSheet this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass6(CountrySelectBottomSheet r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            this.val$resourcesProvider = r2;
            this.gradientProtectionDrawable = new GradientProtectionDrawable(2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDrawOver(Canvas r5, RecyclerView r6, RecyclerView.State r7) {
            int r72 = Math.max(0, (((int) CountrySelectBottomSheet.m13745$$Nest$fgetsearchContainer(this.this$0).getTranslationY()) + AndroidUtilities.dp(80.0f)) + ((int) CountrySelectBottomSheet.m13742$$Nest$fgetanimatorSelectorContainerHeight(this.this$0).getFactor()));
            this.gradientProtectionDrawable.setColor(Theme.getColor(Theme.key_dialogBackground, this.val$resourcesProvider));
            this.gradientProtectionDrawable.setBounds(0, r72, r6.getWidth(), AndroidUtilities.dp(8.0f) + r72);
            this.gradientProtectionDrawable.draw(r5);
            CountrySelectBottomSheet.m13750$$Nest$mcheckUi_listViewClip(this.this$0);
            CountrySelectBottomSheet.m13751$$Nest$mcheckUi_searchFieldY(this.this$0);
        }
    }

    public static class Factory extends UItem.UItemFactory<SelectorCountryCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem asCountry(TLRPC.TL_help_country r2, boolean r3) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.text = r2.iso2;
            r0.object = r2;
            r0.checked = r3;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            SelectorCountryCell r12 = (SelectorCountryCell) r1;
            r12.setCountry((TLRPC.TL_help_country) r2.object, r3);
            r12.setChecked(r2.checked, false);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r1, UItem r2) {
            return super.contentsEquals(r1, r2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public SelectorCountryCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            SelectorCountryCell r0 = new SelectorCountryCell(r1, r5);
            r0.setBackground(null);
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            return super.equals(r1, r2);
        }
    }

    public interface Listener {
        void onCountrySelected(List<String> r1);
    }

    /* renamed from: $r8$lambda$-2aoByosd-vLjte7i_HJavM9N4A, reason: not valid java name */
    public static /* synthetic */ void m13737$r8$lambda$2aoByosdvLjte7i_HJavM9N4A(CountrySelectBottomSheet r0, View r1) {
        r0.onSpanClick(r1);
    }

    /* renamed from: $r8$lambda$2nSWssN9Jy5RPzg31UJU5UN-oLk, reason: not valid java name */
    public static /* synthetic */ void m13738$r8$lambda$2nSWssN9Jy5RPzg31UJU5UNoLk(CountrySelectBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$BUpRtmdrlcrsfUyEXmdTR9jlRxY(CountrySelectBottomSheet r0, String r1, List r2) {
        r0.lambda$loadCountries$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$BjjP3uWKg79wYln5x4VI3gc58q0(CountrySelectBottomSheet r0, Pair r1) {
        r0.lambda$loadCountries$6(r1);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$NFBH717y4dFsCDuVs2oX_nNYAfE(CountrySelectBottomSheet r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    /* renamed from: $r8$lambda$O0cajasIu-60XW6R7kVxDZKNw1c, reason: not valid java name */
    public static /* synthetic */ void m13739$r8$lambda$O0cajasIu60XW6R7kVxDZKNw1c(CountrySelectBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$Wq9LsLXzBX5_pXQSx9IG8WbCZgI(CountrySelectBottomSheet r0, int r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$apDk9il0whNA2a6Lfw6dfM8nk9k(CountrySelectBottomSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ibr8CLR7N95Cz8qbWIV7P8iF2ww(CountrySelectBottomSheet r0, View r1) {
        r0.lambda$new$4(r1);
    }

    /* renamed from: $r8$lambda$icrtcNkQW20kolyd3At-lWlKTTo, reason: not valid java name */
    public static /* synthetic */ void m13740$r8$lambda$icrtcNkQW20kolyd3AtlWlKTTo(CountrySelectBottomSheet r0) {
        r0.lambda$new$2();
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m13741$$Nest$fgetadapter(CountrySelectBottomSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetanimatorSelectorContainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FactorAnimator m13742$$Nest$fgetanimatorSelectorContainerHeight(CountrySelectBottomSheet r0) {
        return r0.animatorSelectorContainerHeight;
    }

    /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13743$$Nest$fgetbulletinContainer(CountrySelectBottomSheet r0) {
        return r0.bulletinContainer;
    }

    /* renamed from: -$$Nest$fgetmaxCountriesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13744$$Nest$fgetmaxCountriesCount(CountrySelectBottomSheet r0) {
        return r0.maxCountriesCount;
    }

    /* renamed from: -$$Nest$fgetsearchContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13745$$Nest$fgetsearchContainer(CountrySelectBottomSheet r0) {
        return r0.searchContainer;
    }

    /* renamed from: -$$Nest$fgetselectedCountries, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m13746$$Nest$fgetselectedCountries(CountrySelectBottomSheet r0) {
        return r0.selectedCountries;
    }

    /* renamed from: -$$Nest$fgetspansContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentSpansContainer m13747$$Nest$fgetspansContainer(CountrySelectBottomSheet r0) {
        return r0.spansContainer;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13748$$Nest$fputquery(CountrySelectBottomSheet r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$mcheckUi_buttonCounter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13749$$Nest$mcheckUi_buttonCounter(CountrySelectBottomSheet r0) {
        r0.checkUi_buttonCounter();
    }

    /* renamed from: -$$Nest$mcheckUi_listViewClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13750$$Nest$mcheckUi_listViewClip(CountrySelectBottomSheet r0) {
        r0.checkUi_listViewClip();
    }

    /* renamed from: -$$Nest$mcheckUi_searchFieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13751$$Nest$mcheckUi_searchFieldY(CountrySelectBottomSheet r0) {
        r0.checkUi_searchFieldY();
    }

    /* renamed from: -$$Nest$monSpanClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13752$$Nest$monSpanClick(CountrySelectBottomSheet r0, View r1) {
        r0.onSpanClick(r1);
    }

    public CountrySelectBottomSheet(Context r19, Theme.ResourcesProvider r20) {
        super(r19, null, true, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r20);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorSelectorContainerHeight = new FactorAnimator(3, this, r3, 350);
        this.animatorTopSaveButtonVisibility = new BoolAnimator(4, this, r3, 320);
        this.countriesMap = new HashMap();
        this.countriesLetters = new ArrayList();
        this.countriesList = new ArrayList();
        this.selectedCountries = new HashMap();
        this.listViewClipBounds = new Rect();
        this.occupyNavigationBar = true;
        this.drawNavigationBar = false;
        this.ignoreTouchActionBar = false;
        this.showShadow = false;
        this.maxCountriesCount = MessagesController.getInstance(this.currentAccount).config.pollCountriesMax.get();
        AndroidUtilities.enableEdgeToEdge(getWindow());
        RecyclerListView r4 = this.recyclerListView;
        int r5 = this.backgroundPaddingLeft;
        r4.setPadding(r5, 0, r5, AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(68.0f));
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.addOnScrollListener(new AnonymousClass1(this));
        this.recyclerListView.setOnItemClickListener(new AnonymousClass2(this, r20, r19));
        ButtonWithCounterView r42 = new ButtonWithCounterView(r19, r20);
        this.button = r42;
        r42.setRound();
        r42.setCountFilled(true);
        r42.setText(LocaleController.getString(R.string.Save));
        r42.setOnClickListener(new CountrySelectBottomSheet$$ExternalSyntheticLambda0(this));
        AnonymousClass3 r52 = new AnonymousClass3(this, r19);
        this.doneItem = r52;
        r52.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        r52.setText(LocaleController.getString(R.string.Save));
        r52.setTypeface(AndroidUtilities.bold());
        r52.setTextSize(1, 14.0f);
        r52.setGravity(17);
        r52.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r52.setVisibility(8);
        ScaleStateListAnimator.apply(r52);
        this.actionBar.createMenu().addView(r52, LayoutHelper.createLinear(-2, 48, 16, 12, 0, 12, 0));
        r52.setOnClickListener(new CountrySelectBottomSheet$$ExternalSyntheticLambda1(this));
        FragmentSearchField r0 = new FragmentSearchField(r19, r20);
        this.searchField = r0;
        r0.editText.setHint(LocaleController.getString(R.string.PollV2SearchHint));
        r0.editText.addTextChangedListener(new AnonymousClass4(this));
        FragmentSpansContainer r53 = new FragmentSpansContainer(r19, this.currentAccount);
        this.spansContainer = r53;
        r53.setDelegate(new CountrySelectBottomSheet$$ExternalSyntheticLambda2(this));
        AnonymousClass5 r7 = new AnonymousClass5(this, r19, r20);
        this.searchContainer = r7;
        int r10 = this.backgroundPaddingLeft;
        r7.setPadding(r10, 0, r10, 0);
        r7.addView(r0, LayoutHelper.createFrame(-1, 40.0f, 48, 10.0f, 0.0f, 10.0f, 0.0f));
        r7.addView(r53, LayoutHelper.createFrame(-1, 144.0f, 48, -3.0f, 40.0f, -3.0f, 0.0f));
        GraySectionCell r02 = new GraySectionCell(r19, 18, r20);
        this.graySectionCell = r02;
        r02.setTranslationY(AndroidUtilities.dp(48.0f));
        r02.setText(LocaleController.getString(R.string.SearchCountriesTitle), LocaleController.getString(R.string.DeselectAll), new CountrySelectBottomSheet$$ExternalSyntheticLambda3(this));
        r7.addView(r02, LayoutHelper.createFrame(-1, 32, 48));
        this.containerView.addView(r7, LayoutHelper.createFrame(-1, Opcodes.ADD_INT_LIT8, 48));
        FrameLayout r03 = new FrameLayout(r19);
        this.buttonContainer = r03;
        r03.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.backgroundPaddingLeft + AndroidUtilities.dp(10.0f), AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(10.0f));
        r03.addView(r42, LayoutHelper.createFrame(-1, 48.0f));
        this.containerView.addView(r03, LayoutHelper.createFrame(-1, -2, 80));
        FrameLayout r04 = new FrameLayout(r19);
        this.bulletinContainer = r04;
        r04.setTranslationY((-AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(68.0f));
        this.containerView.addView(r04, LayoutHelper.createFrame(-1, 150, 80));
        DefaultItemAnimator r05 = new DefaultItemAnimator();
        r05.setDurations(350);
        r05.setInterpolator(r3);
        r05.setDelayAnimations(false);
        r05.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(r05);
        this.recyclerListView.addItemDecoration(new AnonymousClass6(this, r20));
        loadCountries();
        ViewCompat.setOnApplyWindowInsetsListener(getContainer(), new CountrySelectBottomSheet$$ExternalSyntheticLambda4(this));
    }

    public static /* synthetic */ int access$000(CountrySelectBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private void checkUi_buttonCounter() {
        this.button.setCount(this.selectedCountries.size(), true);
    }

    private void checkUi_listViewClip() {
        int r0 = ((AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(56.0f)) + ((int) this.animatorSelectorContainerHeight.getFactor());
        int r1 = (this.containerView.getMeasuredHeight() - AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(34.0f);
        Rect r2 = this.listViewClipBounds;
        if (r2.top == r0) goto L5;
    L8:
        boolean r3 = true;
    L9:
        r2.set(0, r0, this.containerView.getMeasuredWidth(), r1);
        this.recyclerListView.setClipBounds(this.listViewClipBounds);
        if (r3 == false) goto L13;
        this.recyclerListView.invalidate();
        return;
    L13:
        return;
    L5:
        if (r2.bottom != r1) goto L8;
        r3 = false;
        goto L9
    }

    private void checkUi_searchFieldY() {
        float r0 = AndroidUtilities.displaySize.y;
        int r1 = 0;
    L4:
        if (r1 >= this.recyclerListView.getChildCount()) goto L11;
        View r2 = this.recyclerListView.getChildAt(r1);
        if (this.recyclerListView.getChildAdapterPosition(r2) < 1) goto L10;
        if (r2.getY() >= r0) goto L10;
        r0 = r2.getY();
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        float r02 = Math.max(AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight(), r0 + AndroidUtilities.dp(8.0f));
        if (this.searchContainer.getTranslationY() == r02) goto L19;
        this.searchContainer.setTranslationY(r02);
        this.recyclerListView.invalidate();
        return;
    }

    private void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        List<String> r82 = this.countriesLetters;
        if (r82 != null) goto L5;
        return;
    L5:
        if (r82.isEmpty() == true) goto L29;
        int r83 = ((AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(68.0f)) + AndroidUtilities.dp(13.0f);
        int r0 = AndroidUtilities.dp(88.0f) + this.selectedCountriesHeight;
        r7.add(UItem.asSpace(0, r0));
        int r84 = r83 - r0;
        Iterator<String> r02 = this.countriesLetters.iterator();
    L9:
        if (r02.hasNext() == false) goto L19;
        String r2 = r02.next();
        Iterator<TLRPC.TL_help_country> r22 = this.countriesMap.get(r2).iterator();
    L12:
        if (r22.hasNext() == false) goto L9;
        TLRPC.TL_help_country r3 = r22.next();
        if (isSearching() == false) goto L18;
        if (SelectorBottomSheet.matchLocal(r3, AndroidUtilities.translitSafe(this.query).toLowerCase()) == false) goto L12;
    L18:
        r84 = r84 - AndroidUtilities.dp(44.0f);
        r7.add(Factory.asCountry(r3, this.selectedCountries.containsKey(r3.iso2)));
        goto L12
    L19:
        r7.add(UItem.asSpace(1, Math.max(0, r84)));
        return;
    }

    private TLRPC.TL_help_country findCountry(String r5) {
        Iterator<String> r0 = this.countriesLetters.iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        String r1 = r0.next();
        Iterator<TLRPC.TL_help_country> r12 = this.countriesMap.get(r1).iterator();
    L7:
        if (r12.hasNext() == false) goto L4;
        TLRPC.TL_help_country r2 = r12.next();
        if (TextUtils.equals(r5, r2.iso2) == false) goto L7;
        return r2;
    L11:
        return null;
    }

    private boolean isSearching() {
        return !TextUtils.isEmpty(this.query);
    }

    private /* synthetic */ void lambda$loadCountries$5(String r1, List r2) {
        this.countriesList.addAll(r2);
    }

    private /* synthetic */ void lambda$loadCountries$6(Pair r4) {
        this.countriesMap.putAll((Map) r4.first);
        this.countriesLetters.addAll((Collection) r4.second);
        this.countriesMap.forEach(new CountrySelectBottomSheet$$ExternalSyntheticLambda8(this));
        Set<String> r42 = this.countriesToSelect;
        if (r42 == null) goto L10;
        Iterator<String> r43 = r42.iterator();
    L6:
        if (r43.hasNext() == false) goto L10;
        TLRPC.TL_help_country r0 = findCountry(r43.next());
        if (r0 == null) goto L6;
        GroupCreateSpan r1 = new GroupCreateSpan(getContext(), r0);
        r1.setOnClickListener(new CountrySelectBottomSheet$$ExternalSyntheticLambda9(this));
        this.spansContainer.addSpan(r1);
        this.selectedCountries.put(r0.iso2, r1);
    L10:
        this.adapter.update(true);
        checkUi_buttonCounter();
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        Listener r32 = this.listener;
        if (r32 == null) goto L5;
        r32.onCountrySelected(new ArrayList(this.selectedCountries.keySet()));
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        Listener r32 = this.listener;
        if (r32 == null) goto L5;
        r32.onCountrySelected(new ArrayList(this.selectedCountries.keySet()));
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$new$2() {
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$new$3(int r2) {
        int r0 = Math.min(r2, AndroidUtilities.dp(144.0f));
        if (r2 <= 0) goto L6;
        r0 = r0 - AndroidUtilities.dp(8.0f);
    L6:
        if (this.selectedCountriesHeight == r0) goto L9;
        this.selectedCountriesHeight = r0;
        this.animatorSelectorContainerHeight.animateTo(r0);
        this.spansContainer.postOnAnimation(new CountrySelectBottomSheet$$ExternalSyntheticLambda6(this));
        return;
    }

    private /* synthetic */ void lambda$new$4(View r2) {
        this.selectedCountries.clear();
        this.spansContainer.removeAllSpans(true);
        this.adapter.update(true);
        checkUi_buttonCounter();
    }

    private void loadCountries() {
        BoostRepository.loadCountriesForPolls(new CountrySelectBottomSheet$$ExternalSyntheticLambda7(this));
    }

    private WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        processLegacyContainerInsets(r2.toWindowInsets());
        BoolAnimator r0 = this.animatorTopSaveButtonVisibility;
        if (r2.getInsets(WindowInsetsCompat.Type.ime()).bottom <= 0) goto L5;
        boolean r12 = true;
    L6:
        r0.setValue(r12, true);
        return WindowInsetsCompat.CONSUMED;
    L5:
        r12 = false;
        goto L6
    }

    private void onSpanClick(View r2) {
        GroupCreateSpan r22 = (GroupCreateSpan) r2;
        if (r22.isDeleting() == false) goto L6;
        this.currentDeletingSpan = null;
        this.spansContainer.removeSpan(r22);
        this.selectedCountries.remove(r22.getCountryIso2());
        checkUi_buttonCounter();
        this.adapter.update(true);
        return;
    L6:
        GroupCreateSpan r0 = this.currentDeletingSpan;
        if (r0 == null) goto L9;
        r0.cancelDeleteAnimation();
    L9:
        this.currentDeletingSpan = r22;
        r22.startDeleteAnimation();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new CountrySelectBottomSheet$$ExternalSyntheticLambda5(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.BoostingSelectCountry);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerLayout(int r1, int r2, int r3, int r4) {
        super.onContainerLayout(r1, r2, r3, r4);
        checkUi_listViewClip();
        checkUi_searchFieldY();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 3) goto L7;
        checkUi_listViewClip();
        this.graySectionCell.setTranslationY(AndroidUtilities.dp(48.0f) + r2);
        this.searchContainer.invalidate();
        return;
    L7:
        if (r1 != 4) goto L10;
        FragmentFloatingButton.setAnimatedVisibility(this.doneItem, r2);
        return;
    }

    public void prepare(List<String> r2) {
        this.query = null;
        this.countriesToSelect = new HashSet(r2);
    }

    public void setListener(Listener r1) {
        this.listener = r1;
    }
}
