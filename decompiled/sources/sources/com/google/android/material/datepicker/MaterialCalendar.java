package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.GridView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

/* loaded from: classes5.dex */
public final class MaterialCalendar<S> extends PickerFragment<S> {
    private static final String CALENDAR_CONSTRAINTS_KEY = "CALENDAR_CONSTRAINTS_KEY";
    private static final String CURRENT_MONTH_KEY = "CURRENT_MONTH_KEY";
    private static final String DAY_VIEW_DECORATOR_KEY = "DAY_VIEW_DECORATOR_KEY";
    private static final String GRID_SELECTOR_KEY = "GRID_SELECTOR_KEY";
    static final Object MONTHS_VIEW_GROUP_TAG = null;
    static final Object NAVIGATION_NEXT_TAG = null;
    static final Object NAVIGATION_PREV_TAG = null;
    static final Object SELECTOR_TOGGLE_TAG = null;
    private static final int SMOOTH_SCROLL_MAX = 3;
    private static final String THEME_RES_ID_KEY = "THEME_RES_ID_KEY";
    private AccessibilityManager accessibilityManager;
    private CalendarConstraints calendarConstraints;
    private CalendarSelector calendarSelector;
    private CalendarStyle calendarStyle;
    private Month current;
    private DateSelector<S> dateSelector;
    private View dayFrame;
    private DayViewDecorator dayViewDecorator;
    private boolean isFullscreen;
    private MaterialButton monthDropSelect;
    private View monthNext;
    private View monthPrev;
    private PagerSnapHelper pagerSnapHelper;
    private RecyclerView recyclerView;
    private int themeResId;
    private View yearFrame;
    private RecyclerView yearSelector;

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityDelegateCompat {
        final /* synthetic */ MaterialCalendar this$0;

        public AnonymousClass1(MaterialCalendar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setCollectionInfo(null);
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$10, reason: invalid class name */
    public class AnonymousClass10 implements View.OnClickListener {
        final /* synthetic */ MaterialCalendar this$0;
        final /* synthetic */ MonthsPagerAdapter val$monthsPagerAdapter;

        public AnonymousClass10(MaterialCalendar r1, MonthsPagerAdapter r2) {
            this.this$0 = r1;
            this.val$monthsPagerAdapter = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r3) {
            int r32 = this.this$0.getLayoutManager().findFirstVisibleItemPosition();
            this.val$monthsPagerAdapter.setKeyboardFocusDirection(2);
            this.this$0.setCurrentMonth(this.val$monthsPagerAdapter.getPageMonth(r32 + 1));
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$11, reason: invalid class name */
    public class AnonymousClass11 implements View.OnClickListener {
        final /* synthetic */ MaterialCalendar this$0;
        final /* synthetic */ MonthsPagerAdapter val$monthsPagerAdapter;

        public AnonymousClass11(MaterialCalendar r1, MonthsPagerAdapter r2) {
            this.this$0 = r1;
            this.val$monthsPagerAdapter = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r3) {
            int r32 = this.this$0.getLayoutManager().findLastVisibleItemPosition();
            this.val$monthsPagerAdapter.setKeyboardFocusDirection(1);
            this.this$0.setCurrentMonth(this.val$monthsPagerAdapter.getPageMonth(r32 - 1));
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$12, reason: invalid class name */
    public class AnonymousClass12 implements Runnable {
        final /* synthetic */ MaterialCalendar this$0;
        final /* synthetic */ int val$position;

        public AnonymousClass12(MaterialCalendar r1, int r2) {
            this.this$0 = r1;
            this.val$position = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.access$000(this.this$0).smoothScrollToPosition(this.val$position);
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$2, reason: invalid class name */
    public class AnonymousClass2 extends SmoothCalendarLayoutManager {
        final /* synthetic */ MaterialCalendar this$0;
        final /* synthetic */ int val$orientation;

        public AnonymousClass2(MaterialCalendar r1, Context r2, int r3, boolean r4, int r5) {
            this.this$0 = r1;
            this.val$orientation = r5;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(RecyclerView.State r4, int[] r5) {
            int r42 = this.val$orientation;
            MaterialCalendar r0 = this.this$0;
            if (r42 != 0) goto L6;
            r5[0] = MaterialCalendar.access$000(r0).getWidth();
            r5[1] = MaterialCalendar.access$000(this.this$0).getWidth();
            return;
        L6:
            r5[0] = MaterialCalendar.access$000(r0).getHeight();
            r5[1] = MaterialCalendar.access$000(this.this$0).getHeight();
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$3, reason: invalid class name */
    public class AnonymousClass3 implements OnDayClickListener {
        final /* synthetic */ MaterialCalendar this$0;

        public AnonymousClass3(MaterialCalendar r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.MaterialCalendar.OnDayClickListener
        public void onDayClick(long r2) {
            if (MaterialCalendar.access$100(this.this$0).getDateValidator().isValid(r2) == false) goto L13;
            MaterialCalendar.access$200(this.this$0).select(r2);
            Iterator<OnSelectionChangedListener<S>> r22 = this.this$0.onSelectionChangedListeners.iterator();
        L6:
            if (r22.hasNext() == false) goto L8;
            r22.next().onSelectionChanged(MaterialCalendar.access$200(this.this$0).getSelection());
            goto L6
        L8:
            MaterialCalendar.access$000(this.this$0).getAdapter().notifyDataSetChanged();
            if (MaterialCalendar.access$300(this.this$0) == null) goto L14;
            MaterialCalendar.access$300(this.this$0).getAdapter().notifyDataSetChanged();
            return;
        L14:
            return;
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$4, reason: invalid class name */
    public class AnonymousClass4 implements OnMonthNavigationListener {
        final /* synthetic */ MaterialCalendar this$0;

        public AnonymousClass4(MaterialCalendar r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.datepicker.MaterialCalendar.OnMonthNavigationListener
        public boolean onMonthNavigationNext() {
            return MaterialCalendar.access$400(this.this$0, true);
        }

        @Override // com.google.android.material.datepicker.MaterialCalendar.OnMonthNavigationListener
        public boolean onMonthNavigationPrevious() {
            return MaterialCalendar.access$400(this.this$0, false);
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$5, reason: invalid class name */
    public class AnonymousClass5 extends AccessibilityDelegateCompat {
        final /* synthetic */ MaterialCalendar this$0;

        public AnonymousClass5(MaterialCalendar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setScrollable(false);
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.ItemDecoration {
        private final Calendar endItem;
        private final Calendar startItem;
        final /* synthetic */ MaterialCalendar this$0;

        public AnonymousClass6(MaterialCalendar r1) {
            this.this$0 = r1;
            this.startItem = UtcDates.getUtcCalendar();
            this.endItem = UtcDates.getUtcCalendar();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas r20, RecyclerView r21, RecyclerView.State r22) {
            if ((r21.getAdapter() instanceof YearGridAdapter) == true) goto L5;
            return;
        L5:
            if ((r21.getLayoutManager() instanceof GridLayoutManager) == false) goto L41;
            YearGridAdapter r1 = (YearGridAdapter) r21.getAdapter();
            GridLayoutManager r2 = (GridLayoutManager) r21.getLayoutManager();
            Iterator<Pair<Long, Long>> r3 = MaterialCalendar.access$200(this.this$0).getSelectedRanges().iterator();
        L9:
            if (r3.hasNext() == false) goto L42;
            Pair<Long, Long> r4 = r3.next();
            Long r5 = r4.first;
            if (r5 == null) goto L9;
            if (r4.second == null) goto L9;
            this.startItem.setTimeInMillis(r5.longValue());
            this.endItem.setTimeInMillis(r4.second.longValue());
            int r42 = r1.getPositionForYear(this.startItem.get(1));
            int r52 = r1.getPositionForYear(this.endItem.get(1));
            View r6 = r2.findViewByPosition(r42);
            View r7 = r2.findViewByPosition(r52);
            int r43 = r42 / r2.getSpanCount();
            int r53 = r52 / r2.getSpanCount();
            int r8 = r43;
        L16:
            if (r8 > r53) goto L9;
            View r9 = r2.findViewByPosition(r2.getSpanCount() * r8);
            if (r9 == null) goto L30;
            int r10 = r9.getTop() + MaterialCalendar.access$500(this.this$0).year.getTopInset();
            int r92 = r9.getBottom() - MaterialCalendar.access$500(this.this$0).year.getBottomInset();
            if (r8 != r43) goto L24;
            if (r6 == null) goto L24;
            int r11 = r6.getLeft() + (r6.getWidth() / 2);
        L25:
            if (r8 != r53) goto L28;
            if (r7 == null) goto L28;
            int r12 = r7.getLeft() + (r7.getWidth() / 2);
        L29:
            r20.drawRect(r11, r10, r12, r92, MaterialCalendar.access$500(this.this$0).rangeFill);
        L28:
            r12 = r21.getWidth();
        L24:
            r11 = 0;
        L30:
            r8 = r8 + 1;
            goto L16
        L42:
            return;
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$7, reason: invalid class name */
    public class AnonymousClass7 extends AccessibilityDelegateCompat {
        final /* synthetic */ MaterialCalendar this$0;

        public AnonymousClass7(MaterialCalendar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r2, r3);
            int r22 = MaterialCalendar.access$600(this.this$0).getVisibility();
            MaterialCalendar r1 = this.this$0;
            if (r22 != 0) goto L5;
            String r12 = r1.getString(R.string.mtrl_picker_toggle_to_year_selection);
        L6:
            r3.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, r12));
            return;
        L5:
            r12 = r1.getString(R.string.mtrl_picker_toggle_to_day_selection);
            goto L6
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ MaterialCalendar this$0;
        final /* synthetic */ MonthsPagerAdapter val$monthsPagerAdapter;

        public AnonymousClass8(MaterialCalendar r1, MonthsPagerAdapter r2) {
            this.this$0 = r1;
            this.val$monthsPagerAdapter = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r3 == 0) goto L4;
            return;
        L4:
            if (MaterialCalendar.access$700(this.this$0) == null) goto L14;
            View r32 = MaterialCalendar.access$700(this.this$0).findSnapView(this.this$0.getLayoutManager());
            if (r32 == null) goto L11;
            int r22 = r2.getChildAdapterPosition(r32);
            if (r22 == (-1)) goto L11;
            MaterialCalendar.access$802(this.this$0, this.val$monthsPagerAdapter.getPageMonth(r22));
            MaterialCalendar.access$900(this.this$0).setText(this.val$monthsPagerAdapter.getPageTitle(r22));
            MaterialCalendar.access$1000(this.this$0, r22);
        L11:
            MaterialCalendar.access$1100(this.this$0);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            MaterialCalendar r12 = this.this$0;
            if (r2 >= 0) goto L5;
            int r13 = r12.getLayoutManager().findFirstVisibleItemPosition();
        L7:
            if (MaterialCalendar.access$700(this.this$0) != null) goto L9;
            MaterialCalendar.access$802(this.this$0, this.val$monthsPagerAdapter.getPageMonth(r13));
        L9:
            MaterialCalendar.access$900(this.this$0).setText(this.val$monthsPagerAdapter.getPageTitle(r13));
            MaterialCalendar.access$1000(this.this$0, r13);
            return;
        L5:
            r13 = r12.getLayoutManager().findLastVisibleItemPosition();
            goto L7
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$9, reason: invalid class name */
    public class AnonymousClass9 implements View.OnClickListener {
        final /* synthetic */ MaterialCalendar this$0;

        public AnonymousClass9(MaterialCalendar r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            this.this$0.toggleVisibleSelector();
        }
    }

    public enum CalendarSelector extends Enum<CalendarSelector> {
        private static final /* synthetic */ CalendarSelector[] $VALUES = null;
        public static final CalendarSelector DAY = null;
        public static final CalendarSelector YEAR = null;

        private static /* synthetic */ CalendarSelector[] $values() {
            return new CalendarSelector[]{DAY, YEAR};
        }

        static {
            DAY = new CalendarSelector("DAY", 0);
            YEAR = new CalendarSelector("YEAR", 1);
            $VALUES = $values();
        }

        CalendarSelector(String r1, int r2) {
        }

        public static CalendarSelector valueOf(String r1) {
            return (CalendarSelector) Enum.valueOf(CalendarSelector.class, r1);
        }

        public static CalendarSelector[] values() {
            return (CalendarSelector[]) $VALUES.clone();
        }
    }

    public interface OnDayClickListener {
        void onDayClick(long r1);
    }

    public interface OnMonthNavigationListener {
        boolean onMonthNavigationNext();

        boolean onMonthNavigationPrevious();
    }

    static {
        MONTHS_VIEW_GROUP_TAG = "MONTHS_VIEW_GROUP_TAG";
        NAVIGATION_PREV_TAG = "NAVIGATION_PREV_TAG";
        NAVIGATION_NEXT_TAG = "NAVIGATION_NEXT_TAG";
        SELECTOR_TOGGLE_TAG = "SELECTOR_TOGGLE_TAG";
    }

    public MaterialCalendar() {
    }

    public static /* synthetic */ RecyclerView access$000(MaterialCalendar r0) {
        return r0.recyclerView;
    }

    public static /* synthetic */ CalendarConstraints access$100(MaterialCalendar r0) {
        return r0.calendarConstraints;
    }

    public static /* synthetic */ void access$1000(MaterialCalendar r0, int r1) {
        r0.updateNavigationButtonsEnabled(r1);
    }

    public static /* synthetic */ void access$1100(MaterialCalendar r0) {
        r0.updateCurrentVisibleMonth();
    }

    public static /* synthetic */ DateSelector access$200(MaterialCalendar r0) {
        return r0.dateSelector;
    }

    public static /* synthetic */ RecyclerView access$300(MaterialCalendar r0) {
        return r0.yearSelector;
    }

    public static /* synthetic */ boolean access$400(MaterialCalendar r0, boolean r1) {
        return r0.handleNavigateToMonthForKeyboard(r1);
    }

    public static /* synthetic */ CalendarStyle access$500(MaterialCalendar r0) {
        return r0.calendarStyle;
    }

    public static /* synthetic */ View access$600(MaterialCalendar r0) {
        return r0.dayFrame;
    }

    public static /* synthetic */ PagerSnapHelper access$700(MaterialCalendar r0) {
        return r0.pagerSnapHelper;
    }

    public static /* synthetic */ Month access$802(MaterialCalendar r0, Month r1) {
        r0.current = r1;
        return r1;
    }

    public static /* synthetic */ MaterialButton access$900(MaterialCalendar r0) {
        return r0.monthDropSelect;
    }

    private void addActionsToMonthNavigation(View r3, MonthsPagerAdapter r4) {
        MaterialButton r0 = (MaterialButton) r3.findViewById(R.id.month_navigation_fragment_toggle);
        this.monthDropSelect = r0;
        r0.setTag(SELECTOR_TOGGLE_TAG);
        ViewCompat.setAccessibilityDelegate(this.monthDropSelect, new AnonymousClass7(this));
        View r02 = r3.findViewById(R.id.month_navigation_previous);
        this.monthPrev = r02;
        r02.setTag(NAVIGATION_PREV_TAG);
        TooltipCompat.setTooltipText(this.monthPrev, getString(R.string.mtrl_picker_prev_month_tooltip));
        View r03 = r3.findViewById(R.id.month_navigation_next);
        this.monthNext = r03;
        r03.setTag(NAVIGATION_NEXT_TAG);
        TooltipCompat.setTooltipText(this.monthNext, getString(R.string.mtrl_picker_next_month_tooltip));
        this.yearFrame = r3.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.dayFrame = r3.findViewById(R.id.mtrl_calendar_day_selector_frame);
        setSelector(CalendarSelector.DAY);
        this.monthDropSelect.setText(this.current.getLongName());
        this.recyclerView.addOnScrollListener(new AnonymousClass8(this, r4));
        this.monthDropSelect.setOnClickListener(new AnonymousClass9(this));
        this.monthNext.setOnClickListener(new AnonymousClass10(this, r4));
        this.monthPrev.setOnClickListener(new AnonymousClass11(this, r4));
        updateNavigationButtonsEnabled(r4.getPosition(this.current));
    }

    private RecyclerView.ItemDecoration createItemDecoration() {
        return new AnonymousClass6(this);
    }

    public static int getDayHeight(Context r1) {
        return r1.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
    }

    private static int getDialogPickerHeight(Context r5) {
        Resources r52 = r5.getResources();
        int r0 = (r52.getDimensionPixelSize(R.dimen.mtrl_calendar_navigation_height) + r52.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_top_padding)) + r52.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_bottom_padding);
        int r1 = r52.getDimensionPixelSize(R.dimen.mtrl_calendar_days_of_week_height);
        int r2 = MonthAdapter.MAXIMUM_WEEKS;
        return ((r0 + r1) + ((r52.getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) * r2) + ((r2 - 1) * r52.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_vertical_padding)))) + r52.getDimensionPixelOffset(R.dimen.mtrl_calendar_bottom_padding);
    }

    private boolean handleNavigateToMonthForKeyboard(boolean r6) {
        if (this.isFullscreen == false) goto L6;
        return false;
    L6:
        if (this.recyclerView.getScrollState() == 0) goto L8;
        return true;
    L8:
        MonthsPagerAdapter r0 = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        if (r0 == null) goto L26;
        Month r3 = this.current;
        if (r3 == null) goto L26;
        int r32 = r0.getPosition(r3);
        if (r6 == false) goto L16;
        int r4 = 1;
    L17:
        int r33 = r32 + r4;
        if (r33 < 0) goto L26;
        if (r33 >= r0.getItemCount()) goto L26;
        if (r6 == false) goto L23;
        int r62 = 2;
    L24:
        r0.setKeyboardFocusDirection(r62);
        setCurrentMonth(r0.getPageMonth(r33));
        return true;
    L23:
        r62 = 1;
        goto L24
    L16:
        r4 = -1;
    L26:
        return false;
    }

    public static <T> MaterialCalendar<T> newInstance(DateSelector<T> r1, int r2, CalendarConstraints r3) {
        return newInstance(r1, r2, r3, null);
    }

    public static <T> MaterialCalendar<T> newInstance(DateSelector<T> r3, int r4, CalendarConstraints r5, DayViewDecorator r6) {
        MaterialCalendar<T> r0 = new MaterialCalendar();
        Bundle r1 = new Bundle();
        r1.putInt(THEME_RES_ID_KEY, r4);
        r1.putParcelable(GRID_SELECTOR_KEY, r3);
        r1.putParcelable(CALENDAR_CONSTRAINTS_KEY, r5);
        r1.putParcelable(DAY_VIEW_DECORATOR_KEY, r6);
        r1.putParcelable(CURRENT_MONTH_KEY, r5.getOpenAt());
        r0.setArguments(r1);
        return r0;
    }

    private void postSmoothRecyclerViewScroll(int r3) {
        this.recyclerView.post(new AnonymousClass12(this, r3));
    }

    private void setUpForAccessibility() {
        ViewCompat.setAccessibilityDelegate(this.recyclerView, new AnonymousClass5(this));
    }

    private void updateAccessibilityPaneTitle(View r3) {
        if (r3 == null) goto L13;
        CalendarSelector r0 = this.calendarSelector;
        if (r0 != CalendarSelector.YEAR) goto L9;
        ViewCompat.setAccessibilityPaneTitle(r3, getString(R.string.mtrl_picker_pane_title_year_view));
        return;
    L9:
        if (r0 != CalendarSelector.DAY) goto L12;
        ViewCompat.setAccessibilityPaneTitle(r3, getString(R.string.mtrl_picker_pane_title_calendar_view));
        return;
    L12:
        return;
    }

    private void updateCurrentVisibleMonth() {
        MonthsPagerAdapter r0 = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.isFullscreen == true) goto L9;
        r0.setVisibleMonth(this.current);
        return;
    }

    private void updateNavigationButtonsEnabled(int r6) {
        View r0 = this.monthNext;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L9:
        View r5 = this.monthPrev;
        if (r5 != null) goto L12;
        return;
    L12:
        if ((r6 - 1) < 0) goto L14;
        r1 = true;
    L14:
        r5.setEnabled(r1);
        return;
    L5:
        if ((r6 + 1) >= this.recyclerView.getAdapter().getItemCount()) goto L7;
        boolean r3 = true;
    L8:
        r0.setEnabled(r3);
        goto L9
    L7:
        r3 = false;
        goto L8
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> r1) {
        return super.addOnSelectionChangedListener(r1);
    }

    public CalendarConstraints getCalendarConstraints() {
        return this.calendarConstraints;
    }

    public CalendarStyle getCalendarStyle() {
        return this.calendarStyle;
    }

    public Month getCurrentMonth() {
        return this.current;
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public DateSelector<S> getDateSelector() {
        return this.dateSelector;
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) this.recyclerView.getLayoutManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle r2) {
        super.onCreate(r2);
        if (r2 != null) goto L5;
        r2 = getArguments();
    L5:
        this.themeResId = r2.getInt(THEME_RES_ID_KEY);
        this.dateSelector = (DateSelector) r2.getParcelable(GRID_SELECTOR_KEY);
        this.calendarConstraints = (CalendarConstraints) r2.getParcelable(CALENDAR_CONSTRAINTS_KEY);
        this.dayViewDecorator = (DayViewDecorator) r2.getParcelable(DAY_VIEW_DECORATOR_KEY);
        this.current = (Month) r2.getParcelable(CURRENT_MONTH_KEY);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater r11, ViewGroup r12, Bundle r13) {
        ContextThemeWrapper r6 = new ContextThemeWrapper(getContext(), this.themeResId);
        this.calendarStyle = new CalendarStyle(r6);
        LayoutInflater r0 = r11.cloneInContext(r6);
        this.accessibilityManager = (AccessibilityManager) requireContext().getSystemService("accessibility");
        Month r2 = this.calendarConstraints.getStart();
        boolean r3 = MaterialDatePicker.isFullscreen(r6);
        this.isFullscreen = r3;
        if (r3 == false) goto L5;
        int r32 = R.layout.mtrl_calendar_vertical;
        int r5 = 1;
    L6:
        View r9 = r0.inflate(r32, r12, false);
        r9.setMinimumHeight(getDialogPickerHeight(requireContext()));
        GridView r02 = (GridView) r9.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(r02, new AnonymousClass1(this));
        int r33 = this.calendarConstraints.getFirstDayOfWeek();
        if (r33 <= 0) goto L9;
        DaysOfWeekAdapter r4 = new DaysOfWeekAdapter(r33);
    L10:
        r02.setAdapter(r4);
        r02.setNumColumns(r2.daysInWeek);
        r02.setEnabled(false);
        this.recyclerView = (RecyclerView) r9.findViewById(R.id.mtrl_calendar_months);
        this.recyclerView.setLayoutManager(new AnonymousClass2(this, getContext(), r5, false, r5));
        this.recyclerView.setTag(MONTHS_VIEW_GROUP_TAG);
        MonthsPagerAdapter r03 = new MonthsPagerAdapter(r6, this.dateSelector, this.calendarConstraints, this.dayViewDecorator, new AnonymousClass3(this), new AnonymousClass4(this));
        this.recyclerView.setAdapter(r03);
        int r22 = r6.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView r34 = (RecyclerView) r9.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.yearSelector = r34;
        if (r34 == null) goto L14;
        r34.setHasFixedSize(true);
        this.yearSelector.setLayoutManager(new GridLayoutManager(r6, r22, 1, false));
        this.yearSelector.setAdapter(new YearGridAdapter(this));
        this.yearSelector.addItemDecoration(createItemDecoration());
    L14:
        if (this.isFullscreen == true) goto L17;
        PagerSnapHelper r1 = new PagerSnapHelper();
        this.pagerSnapHelper = r1;
        r1.attachToRecyclerView(this.recyclerView);
    L17:
        if (r9.findViewById(R.id.month_navigation_fragment_toggle) == null) goto L19;
        addActionsToMonthNavigation(r9, r03);
    L19:
        this.recyclerView.scrollToPosition(r03.getPosition(this.current));
        setUpForAccessibility();
        updateAccessibilityPaneTitle(r9);
        return r9;
    L9:
        r4 = new DaysOfWeekAdapter();
        goto L10
    L5:
        r32 = R.layout.mtrl_calendar_horizontal;
        r5 = 0;
        goto L6
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle r3) {
        super.onSaveInstanceState(r3);
        r3.putInt(THEME_RES_ID_KEY, this.themeResId);
        r3.putParcelable(GRID_SELECTOR_KEY, this.dateSelector);
        r3.putParcelable(CALENDAR_CONSTRAINTS_KEY, this.calendarConstraints);
        r3.putParcelable(DAY_VIEW_DECORATOR_KEY, this.dayViewDecorator);
        r3.putParcelable(CURRENT_MONTH_KEY, this.current);
    }

    public void sendAccessibilityFocusEventToMonthDropdown() {
        MaterialButton r1 = this.monthDropSelect;
        if (r1 == null) goto L6;
        r1.sendAccessibilityEvent(8);
        return;
    }

    public void setCurrentMonth(Month r7) {
        MonthsPagerAdapter r0 = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        int r1 = r0.getPosition(r7);
        AccessibilityManager r2 = this.accessibilityManager;
        if (r2 != null) goto L5;
    L7:
        int r02 = r1 - r0.getPosition(this.current);
        boolean r3 = false;
        if (Math.abs(r02) <= 3) goto L10;
        boolean r22 = true;
    L11:
        if (r02 <= 0) goto L13;
        r3 = true;
    L13:
        this.current = r7;
        if (r22 == false) goto L17;
        if (r3 == false) goto L17;
        this.recyclerView.scrollToPosition(r1 - 3);
        postSmoothRecyclerViewScroll(r1);
    L20:
        updateCurrentVisibleMonth();
        updateNavigationButtonsEnabled(r1);
        return;
    L17:
        if (r22 == false) goto L19;
        this.recyclerView.scrollToPosition(r1 + 3);
        postSmoothRecyclerViewScroll(r1);
        goto L20
    L19:
        postSmoothRecyclerViewScroll(r1);
        goto L20
    L10:
        r22 = false;
        goto L11
    L5:
        if (r2.isEnabled() == false) goto L7;
        this.current = r7;
        this.recyclerView.scrollToPosition(r1);
        goto L20
    }

    public void setSelector(CalendarSelector r5) {
        this.calendarSelector = r5;
        if (r5 != CalendarSelector.YEAR) goto L7;
        this.yearSelector.getLayoutManager().scrollToPosition(((YearGridAdapter) this.yearSelector.getAdapter()).getPositionForYear(this.current.year));
        this.yearFrame.setVisibility(0);
        this.dayFrame.setVisibility(8);
        this.monthPrev.setVisibility(8);
        this.monthNext.setVisibility(8);
        return;
    L7:
        if (r5 != CalendarSelector.DAY) goto L10;
        this.yearFrame.setVisibility(8);
        this.dayFrame.setVisibility(0);
        this.monthPrev.setVisibility(0);
        this.monthNext.setVisibility(0);
        setCurrentMonth(this.current);
        return;
    }

    public void toggleVisibleSelector() {
        CalendarSelector r0 = this.calendarSelector;
        CalendarSelector r1 = CalendarSelector.YEAR;
        if (r0 != r1) goto L6;
        setSelector(CalendarSelector.DAY);
    L8:
        updateAccessibilityPaneTitle(getView());
        return;
    L6:
        if (r0 != CalendarSelector.DAY) goto L8;
        setSelector(r1);
        goto L8
    }
}
