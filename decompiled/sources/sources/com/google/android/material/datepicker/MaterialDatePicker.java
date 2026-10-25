package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes5.dex */
public class MaterialDatePicker<S> extends DialogFragment {
    private static final String CALENDAR_CONSTRAINTS_KEY = "CALENDAR_CONSTRAINTS_KEY";
    private static final String CALENDAR_FRAGMENT_TAG = "CALENDAR_FRAGMENT_TAG";
    static final Object CANCEL_BUTTON_TAG = null;
    static final Object CONFIRM_BUTTON_TAG = null;
    private static final String DATE_SELECTOR_KEY = "DATE_SELECTOR_KEY";
    private static final String DAY_VIEW_DECORATOR_KEY = "DAY_VIEW_DECORATOR_KEY";
    public static final int INPUT_MODE_CALENDAR = 0;
    private static final String INPUT_MODE_KEY = "INPUT_MODE_KEY";
    public static final int INPUT_MODE_TEXT = 1;
    private static final String NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY = "NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY";
    private static final String NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY = "NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY";
    private static final String NEGATIVE_BUTTON_TEXT_KEY = "NEGATIVE_BUTTON_TEXT_KEY";
    private static final String NEGATIVE_BUTTON_TEXT_RES_ID_KEY = "NEGATIVE_BUTTON_TEXT_RES_ID_KEY";
    private static final String OVERRIDE_THEME_RES_ID = "OVERRIDE_THEME_RES_ID";
    private static final String POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY = "POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY";
    private static final String POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY = "POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY";
    private static final String POSITIVE_BUTTON_TEXT_KEY = "POSITIVE_BUTTON_TEXT_KEY";
    private static final String POSITIVE_BUTTON_TEXT_RES_ID_KEY = "POSITIVE_BUTTON_TEXT_RES_ID_KEY";
    private static final String TEXT_INPUT_FRAGMENT_TAG = "TEXT_INPUT_FRAGMENT_TAG";
    private static final String TITLE_TEXT_KEY = "TITLE_TEXT_KEY";
    private static final String TITLE_TEXT_RES_ID_KEY = "TITLE_TEXT_RES_ID_KEY";
    static final Object TOGGLE_BUTTON_TAG = null;
    private MaterialShapeDrawable background;
    private MaterialCalendar<S> calendar;
    private CalendarConstraints calendarConstraints;
    private Button confirmButton;
    private DateSelector<S> dateSelector;
    private DayViewDecorator dayViewDecorator;
    private boolean edgeToEdgeEnabled;
    private CharSequence fullTitleText;
    private boolean fullscreen;
    private TextView headerSelectionText;
    private TextView headerTitleTextView;
    private CheckableImageButton headerToggleButton;
    private int inputMode;
    private CharSequence negativeButtonContentDescription;
    private int negativeButtonContentDescriptionResId;
    private CharSequence negativeButtonText;
    private int negativeButtonTextResId;
    private final LinkedHashSet<DialogInterface.OnCancelListener> onCancelListeners;
    private final LinkedHashSet<DialogInterface.OnDismissListener> onDismissListeners;
    private final LinkedHashSet<View.OnClickListener> onNegativeButtonClickListeners;
    private final LinkedHashSet<MaterialPickerOnPositiveButtonClickListener<? super S>> onPositiveButtonClickListeners;
    private int overrideThemeResId;
    private PickerFragment<S> pickerFragment;
    private CharSequence positiveButtonContentDescription;
    private int positiveButtonContentDescriptionResId;
    private CharSequence positiveButtonText;
    private int positiveButtonTextResId;
    private CharSequence singleLineTitleText;
    private CharSequence titleText;
    private int titleTextResId;

    /* renamed from: com.google.android.material.datepicker.MaterialDatePicker$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ MaterialDatePicker this$0;
        final /* synthetic */ View val$headerLayout;
        final /* synthetic */ int val$originalHeaderHeight;
        final /* synthetic */ int val$originalPaddingLeft;
        final /* synthetic */ int val$originalPaddingRight;
        final /* synthetic */ int val$originalPaddingTop;

        public AnonymousClass1(MaterialDatePicker r1, int r2, View r3, int r4, int r5, int r6) {
            this.this$0 = r1;
            this.val$originalHeaderHeight = r2;
            this.val$headerLayout = r3;
            this.val$originalPaddingLeft = r4;
            this.val$originalPaddingTop = r5;
            this.val$originalPaddingRight = r6;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r5, WindowInsetsCompat r6) {
            Insets r52 = r6.getInsets(WindowInsetsCompat.Type.systemBars());
            if (this.val$originalHeaderHeight < 0) goto L5;
            ViewGroup.LayoutParams r0 = this.val$headerLayout.getLayoutParams();
            r0.height = this.val$originalHeaderHeight + r52.top;
            View r02 = this.val$headerLayout;
            r02.setLayoutParams(r02.getLayoutParams());
        L5:
            View r03 = this.val$headerLayout;
            r03.setPadding(this.val$originalPaddingLeft + r52.left, this.val$originalPaddingTop + r52.top, this.val$originalPaddingRight + r52.right, r03.getPaddingBottom());
            return r6;
        }
    }

    /* renamed from: com.google.android.material.datepicker.MaterialDatePicker$2, reason: invalid class name */
    public class AnonymousClass2 extends OnSelectionChangedListener<S> {
        final /* synthetic */ MaterialDatePicker this$0;

        public AnonymousClass2(MaterialDatePicker r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onIncompleteSelectionChanged() {
            MaterialDatePicker.access$100(this.this$0).setEnabled(false);
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onSelectionChanged(S r2) {
            MaterialDatePicker r22 = this.this$0;
            r22.updateHeader(r22.getHeaderText());
            MaterialDatePicker.access$100(this.this$0).setEnabled(MaterialDatePicker.access$000(this.this$0).isSelectionComplete());
        }
    }

    public static final class Builder<S> {
        CalendarConstraints calendarConstraints;
        final DateSelector<S> dateSelector;
        DayViewDecorator dayViewDecorator;
        int inputMode;
        CharSequence negativeButtonContentDescription;
        int negativeButtonContentDescriptionResId;
        CharSequence negativeButtonText;
        int negativeButtonTextResId;
        int overrideThemeResId;
        CharSequence positiveButtonContentDescription;
        int positiveButtonContentDescriptionResId;
        CharSequence positiveButtonText;
        int positiveButtonTextResId;
        S selection;
        CharSequence titleText;
        int titleTextResId;

        private Builder(DateSelector<S> r3) {
            this.overrideThemeResId = 0;
            this.titleTextResId = 0;
            this.titleText = null;
            this.positiveButtonTextResId = 0;
            this.positiveButtonText = null;
            this.positiveButtonContentDescriptionResId = 0;
            this.positiveButtonContentDescription = null;
            this.negativeButtonTextResId = 0;
            this.negativeButtonText = null;
            this.negativeButtonContentDescriptionResId = 0;
            this.negativeButtonContentDescription = null;
            this.selection = null;
            this.inputMode = 0;
            this.dateSelector = r3;
        }

        private Month createDefaultOpenAt() {
            if (this.dateSelector.getSelectedDays().isEmpty() == true) goto L7;
            Month r0 = Month.create(this.dateSelector.getSelectedDays().iterator().next().longValue());
            if (monthInValidRange(r0, this.calendarConstraints) == false) goto L7;
            return r0;
        L7:
            Month r02 = Month.current();
            if (monthInValidRange(r02, this.calendarConstraints) == false) goto L11;
            return r02;
        L11:
            return this.calendarConstraints.getStart();
        }

        public static <S> Builder<S> customDatePicker(DateSelector<S> r1) {
            return new Builder(r1);
        }

        public static Builder<Long> datePicker() {
            return new Builder(new SingleDateSelector());
        }

        public static Builder<Pair<Long, Long>> dateRangePicker() {
            return new Builder(new RangeDateSelector());
        }

        private static boolean monthInValidRange(Month r1, CalendarConstraints r2) {
            if (r1.compareTo2(r2.getStart()) >= 0) goto L5;
            return false;
        L5:
            if (r1.compareTo2(r2.getEnd()) > 0) goto L10;
            return true;
        L10:
            return false;
        }

        public MaterialDatePicker<S> build() {
            if (this.calendarConstraints != null) goto L6;
            this.calendarConstraints = new CalendarConstraints.Builder().build();
        L6:
            if (this.titleTextResId != 0) goto L8;
            this.titleTextResId = this.dateSelector.getDefaultTitleResId();
        L8:
            S r0 = this.selection;
            if (r0 == null) goto L12;
            this.dateSelector.setSelection(r0);
        L12:
            if (this.calendarConstraints.getOpenAt() != null) goto L15;
            this.calendarConstraints.setOpenAt(createDefaultOpenAt());
        L15:
            return MaterialDatePicker.newInstance(this);
        }

        public Builder<S> setCalendarConstraints(CalendarConstraints r1) {
            this.calendarConstraints = r1;
            return this;
        }

        public Builder<S> setDayViewDecorator(DayViewDecorator r1) {
            this.dayViewDecorator = r1;
            return this;
        }

        public Builder<S> setInputMode(int r1) {
            this.inputMode = r1;
            return this;
        }

        public Builder<S> setNegativeButtonContentDescription(int r1) {
            this.negativeButtonContentDescriptionResId = r1;
            this.negativeButtonContentDescription = null;
            return this;
        }

        public Builder<S> setNegativeButtonContentDescription(CharSequence r1) {
            this.negativeButtonContentDescription = r1;
            this.negativeButtonContentDescriptionResId = 0;
            return this;
        }

        public Builder<S> setNegativeButtonText(int r1) {
            this.negativeButtonTextResId = r1;
            this.negativeButtonText = null;
            return this;
        }

        public Builder<S> setNegativeButtonText(CharSequence r1) {
            this.negativeButtonText = r1;
            this.negativeButtonTextResId = 0;
            return this;
        }

        public Builder<S> setPositiveButtonContentDescription(int r1) {
            this.positiveButtonContentDescriptionResId = r1;
            this.positiveButtonContentDescription = null;
            return this;
        }

        public Builder<S> setPositiveButtonContentDescription(CharSequence r1) {
            this.positiveButtonContentDescription = r1;
            this.positiveButtonContentDescriptionResId = 0;
            return this;
        }

        public Builder<S> setPositiveButtonText(int r1) {
            this.positiveButtonTextResId = r1;
            this.positiveButtonText = null;
            return this;
        }

        public Builder<S> setPositiveButtonText(CharSequence r1) {
            this.positiveButtonText = r1;
            this.positiveButtonTextResId = 0;
            return this;
        }

        public Builder<S> setSelection(S r1) {
            this.selection = r1;
            return this;
        }

        public Builder<S> setTextInputFormat(SimpleDateFormat r2) {
            this.dateSelector.setTextInputFormat(r2);
            return this;
        }

        public Builder<S> setTheme(int r1) {
            this.overrideThemeResId = r1;
            return this;
        }

        public Builder<S> setTitleText(int r1) {
            this.titleTextResId = r1;
            this.titleText = null;
            return this;
        }

        public Builder<S> setTitleText(CharSequence r1) {
            this.titleText = r1;
            this.titleTextResId = 0;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InputMode {
    }

    public static /* synthetic */ void $r8$lambda$_7eg0aLhI4_FyxsSORjppBhoKm4(MaterialDatePicker r1, View r2) {
        r1.confirmButton.setEnabled(r1.getDateSelector().isSelectionComplete());
        r1.headerToggleButton.toggle();
        int r0 = 1;
        if (r1.inputMode != 1) goto L5;
        r0 = 0;
    L5:
        r1.inputMode = r0;
        r1.updateToggleContentDescription(r1.headerToggleButton);
        r1.updateToggleTooltip(r1.headerToggleButton);
        r1.startPickerFragment();
    }

    static {
        CONFIRM_BUTTON_TAG = "CONFIRM_BUTTON_TAG";
        CANCEL_BUTTON_TAG = "CANCEL_BUTTON_TAG";
        TOGGLE_BUTTON_TAG = "TOGGLE_BUTTON_TAG";
    }

    public MaterialDatePicker() {
        this.onPositiveButtonClickListeners = new LinkedHashSet();
        this.onNegativeButtonClickListeners = new LinkedHashSet();
        this.onCancelListeners = new LinkedHashSet();
        this.onDismissListeners = new LinkedHashSet();
    }

    public static /* synthetic */ DateSelector access$000(MaterialDatePicker r0) {
        return r0.getDateSelector();
    }

    public static /* synthetic */ Button access$100(MaterialDatePicker r0) {
        return r0.confirmButton;
    }

    private static Drawable createHeaderToggleDrawable(Context r3) {
        StateListDrawable r0 = new StateListDrawable();
        r0.addState(new int[]{R.attr.state_checked}, AppCompatResources.getDrawable(r3, com.google.android.material.R.drawable.material_ic_calendar_black_24dp));
        r0.addState(new int[0], AppCompatResources.getDrawable(r3, com.google.android.material.R.drawable.material_ic_edit_black_24dp));
        return r0;
    }

    private void enableEdgeToEdgeIfNeeded(Window r11) {
        if (this.edgeToEdgeEnabled == false) goto L5;
        return;
    L5:
        View r5 = requireView().findViewById(com.google.android.material.R.id.fullscreen_header);
        EdgeToEdgeUtils.applyEdgeToEdge(r11, true, ViewUtils.getBackgroundColor(r5), null);
        int r7 = r5.getPaddingTop();
        int r6 = r5.getPaddingLeft();
        int r8 = r5.getPaddingRight();
        ViewCompat.setOnApplyWindowInsetsListener(r5, new AnonymousClass1(this, r5.getLayoutParams().height, r5, r6, r7, r8));
        this.edgeToEdgeEnabled = true;
    }

    private DateSelector<S> getDateSelector() {
        if (this.dateSelector != null) goto L6;
        this.dateSelector = (DateSelector) getArguments().getParcelable(DATE_SELECTOR_KEY);
    L6:
        return this.dateSelector;
    }

    private static CharSequence getFirstLineBySeparator(CharSequence r3) {
        if (r3 == null) goto L7;
        String[] r0 = TextUtils.split(String.valueOf(r3), "\n");
        if (r0.length > 1) goto L6;
        return r3;
    L6:
        return r0[0];
    L7:
        return null;
    }

    private String getHeaderContentDescription() {
        return getDateSelector().getSelectionContentDescription(requireContext());
    }

    private static int getPaddedPickerWidth(Context r4) {
        Resources r42 = r4.getResources();
        int r0 = r42.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_content_padding);
        int r1 = Month.current().daysInWeek;
        return ((r0 * 2) + (r42.getDimensionPixelSize(com.google.android.material.R.dimen.mtrl_calendar_day_width) * r1)) + ((r1 - 1) * r42.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_month_horizontal_padding));
    }

    private int getThemeResId(Context r2) {
        int r0 = this.overrideThemeResId;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return getDateSelector().getDefaultThemeResId(r2);
    }

    private void initHeaderToggle(Context r3) {
        this.headerToggleButton.setTag(TOGGLE_BUTTON_TAG);
        this.headerToggleButton.setImageDrawable(createHeaderToggleDrawable(r3));
        CheckableImageButton r32 = this.headerToggleButton;
        if (this.inputMode == 0) goto L5;
        boolean r0 = true;
    L6:
        r32.setChecked(r0);
        ViewCompat.setAccessibilityDelegate(this.headerToggleButton, null);
        updateToggleContentDescription(this.headerToggleButton);
        updateToggleTooltip(this.headerToggleButton);
        this.headerToggleButton.setOnClickListener(new MaterialDatePicker$$ExternalSyntheticLambda0(this));
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static boolean isFullscreen(Context r1) {
        return readMaterialCalendarStyleBoolean(r1, R.attr.windowFullscreen);
    }

    private boolean isLandscape() {
        if (getResources().getConfiguration().orientation != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isNestedScrollable(Context r1) {
        return readMaterialCalendarStyleBoolean(r1, com.google.android.material.R.attr.nestedScrollable);
    }

    public static <S> MaterialDatePicker<S> newInstance(Builder<S> r4) {
        MaterialDatePicker<S> r0 = new MaterialDatePicker();
        Bundle r1 = new Bundle();
        r1.putInt(OVERRIDE_THEME_RES_ID, r4.overrideThemeResId);
        r1.putParcelable(DATE_SELECTOR_KEY, r4.dateSelector);
        r1.putParcelable(CALENDAR_CONSTRAINTS_KEY, r4.calendarConstraints);
        r1.putParcelable(DAY_VIEW_DECORATOR_KEY, r4.dayViewDecorator);
        r1.putInt(TITLE_TEXT_RES_ID_KEY, r4.titleTextResId);
        r1.putCharSequence(TITLE_TEXT_KEY, r4.titleText);
        r1.putInt(INPUT_MODE_KEY, r4.inputMode);
        r1.putInt(POSITIVE_BUTTON_TEXT_RES_ID_KEY, r4.positiveButtonTextResId);
        r1.putCharSequence(POSITIVE_BUTTON_TEXT_KEY, r4.positiveButtonText);
        r1.putInt(POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY, r4.positiveButtonContentDescriptionResId);
        r1.putCharSequence(POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY, r4.positiveButtonContentDescription);
        r1.putInt(NEGATIVE_BUTTON_TEXT_RES_ID_KEY, r4.negativeButtonTextResId);
        r1.putCharSequence(NEGATIVE_BUTTON_TEXT_KEY, r4.negativeButtonText);
        r1.putInt(NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY, r4.negativeButtonContentDescriptionResId);
        r1.putCharSequence(NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY, r4.negativeButtonContentDescription);
        r0.setArguments(r1);
        return r0;
    }

    public static boolean readMaterialCalendarStyleBoolean(Context r2, int r3) {
        TypedArray r22 = r2.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(r2, com.google.android.material.R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{r3});
        boolean r32 = r22.getBoolean(0, false);
        r22.recycle();
        return r32;
    }

    private void startPickerFragment() {
        int r0 = getThemeResId(requireContext());
        if (this.inputMode != 1) goto L5;
        String r1 = TEXT_INPUT_FRAGMENT_TAG;
    L6:
        Fragment r3 = getChildFragmentManager().findFragmentByTag(r1);
        if ((r3 instanceof PickerFragment) == false) goto L9;
        MaterialCalendar<S> r32 = (PickerFragment<S>) ((PickerFragment) r3);
    L10:
        if (r32 == null) goto L12;
    L15:
        this.pickerFragment = r32;
        MaterialCalendar<S> r33 = r32;
        r33.addOnSelectionChangedListener(new AnonymousClass2(this));
        updateTitle();
        updateHeader(getHeaderText());
        getChildFragmentManager().beginTransaction().replace(com.google.android.material.R.id.mtrl_calendar_frame, this.pickerFragment, r1).commitNow();
        return;
    L12:
        if (this.inputMode != 1) goto L14;
        r32 = MaterialTextInputPicker.newInstance(getDateSelector(), r0, this.calendarConstraints);
        goto L15
    L14:
        r32 = MaterialCalendar.newInstance(getDateSelector(), r0, this.calendarConstraints, this.dayViewDecorator);
        this.calendar = r32;
        goto L15
    L9:
        r32 = null;
        goto L10
    L5:
        r1 = CALENDAR_FRAGMENT_TAG;
        goto L6
    }

    public static long thisMonthInUtcMilliseconds() {
        return Month.current().timeInMillis;
    }

    public static long todayInUtcMilliseconds() {
        return UtcDates.getTodayCalendar().getTimeInMillis();
    }

    private void updateTitle() {
        TextView r0 = this.headerTitleTextView;
        if (this.inputMode == 1) goto L5;
    L7:
        CharSequence r3 = this.fullTitleText;
    L8:
        r0.setText(r3);
        return;
    L5:
        if (isLandscape() == false) goto L7;
        r3 = this.singleLineTitleText;
        goto L8
    }

    private void updateToggleContentDescription(CheckableImageButton r3) {
        if (this.inputMode != 1) goto L5;
        String r32 = r3.getContext().getString(com.google.android.material.R.string.mtrl_picker_toggle_to_calendar_input_mode);
    L6:
        this.headerToggleButton.setContentDescription(r32);
        return;
    L5:
        r32 = r3.getContext().getString(com.google.android.material.R.string.mtrl_picker_toggle_to_text_input_mode);
        goto L6
    }

    private void updateToggleTooltip(CheckableImageButton r3) {
        if (this.inputMode != 1) goto L5;
        String r32 = r3.getContext().getString(com.google.android.material.R.string.mtrl_picker_toggle_to_calendar_input_mode_tooltip);
    L6:
        TooltipCompat.setTooltipText(this.headerToggleButton, r32);
        return;
    L5:
        r32 = r3.getContext().getString(com.google.android.material.R.string.mtrl_picker_toggle_to_text_input_mode_tooltip);
        goto L6
    }

    public boolean addOnCancelListener(DialogInterface.OnCancelListener r1) {
        return this.onCancelListeners.add(r1);
    }

    public boolean addOnDismissListener(DialogInterface.OnDismissListener r1) {
        return this.onDismissListeners.add(r1);
    }

    public boolean addOnNegativeButtonClickListener(View.OnClickListener r1) {
        return this.onNegativeButtonClickListeners.add(r1);
    }

    public boolean addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener<? super S> r1) {
        return this.onPositiveButtonClickListeners.add(r1);
    }

    public void clearOnCancelListeners() {
        this.onCancelListeners.clear();
    }

    public void clearOnDismissListeners() {
        this.onDismissListeners.clear();
    }

    public void clearOnNegativeButtonClickListeners() {
        this.onNegativeButtonClickListeners.clear();
    }

    public void clearOnPositiveButtonClickListeners() {
        this.onPositiveButtonClickListeners.clear();
    }

    public String getHeaderText() {
        return getDateSelector().getSelectionDisplayString(getContext());
    }

    public int getInputMode() {
        return this.inputMode;
    }

    public final S getSelection() {
        return getDateSelector().getSelection();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r3) {
        Iterator<DialogInterface.OnCancelListener> r0 = this.onCancelListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onCancel(r3);
        goto L4
    L6:
        super.onCancel(r3);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle r2) {
        super.onCreate(r2);
        if (r2 != null) goto L5;
        r2 = getArguments();
    L5:
        this.overrideThemeResId = r2.getInt(OVERRIDE_THEME_RES_ID);
        this.dateSelector = (DateSelector) r2.getParcelable(DATE_SELECTOR_KEY);
        this.calendarConstraints = (CalendarConstraints) r2.getParcelable(CALENDAR_CONSTRAINTS_KEY);
        this.dayViewDecorator = (DayViewDecorator) r2.getParcelable(DAY_VIEW_DECORATOR_KEY);
        this.titleTextResId = r2.getInt(TITLE_TEXT_RES_ID_KEY);
        this.titleText = r2.getCharSequence(TITLE_TEXT_KEY);
        this.inputMode = r2.getInt(INPUT_MODE_KEY);
        this.positiveButtonTextResId = r2.getInt(POSITIVE_BUTTON_TEXT_RES_ID_KEY);
        this.positiveButtonText = r2.getCharSequence(POSITIVE_BUTTON_TEXT_KEY);
        this.positiveButtonContentDescriptionResId = r2.getInt(POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY);
        this.positiveButtonContentDescription = r2.getCharSequence(POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY);
        this.negativeButtonTextResId = r2.getInt(NEGATIVE_BUTTON_TEXT_RES_ID_KEY);
        this.negativeButtonText = r2.getCharSequence(NEGATIVE_BUTTON_TEXT_KEY);
        this.negativeButtonContentDescriptionResId = r2.getInt(NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY);
        this.negativeButtonContentDescription = r2.getCharSequence(NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY);
        CharSequence r22 = this.titleText;
        if (r22 != null) goto L9;
        r22 = requireContext().getResources().getText(this.titleTextResId);
    L9:
        this.fullTitleText = r22;
        this.singleLineTitleText = getFirstLineBySeparator(r22);
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle r6) {
        Dialog r62 = new Dialog(requireContext(), getThemeResId(requireContext()));
        Context r0 = r62.getContext();
        this.fullscreen = isFullscreen(r0);
        this.background = new MaterialShapeDrawable(r0, null, com.google.android.material.R.attr.materialCalendarStyle, com.google.android.material.R.style.Widget_MaterialComponents_MaterialCalendar);
        TypedArray r1 = r0.obtainStyledAttributes(null, com.google.android.material.R.styleable.MaterialCalendar, com.google.android.material.R.attr.materialCalendarStyle, com.google.android.material.R.style.Widget_MaterialComponents_MaterialCalendar);
        int r2 = r1.getColor(com.google.android.material.R.styleable.MaterialCalendar_backgroundTint, 0);
        r1.recycle();
        this.background.initializeElevationOverlay(r0);
        this.background.setFillColor(ColorStateList.valueOf(r2));
        this.background.setElevation(r62.getWindow().getDecorView().getElevation());
        return r62;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater r4, ViewGroup r5, Bundle r6) {
        if (this.fullscreen == false) goto L5;
        int r62 = com.google.android.material.R.layout.mtrl_picker_fullscreen;
    L6:
        View r42 = r4.inflate(r62, r5);
        Context r52 = r42.getContext();
        DayViewDecorator r63 = this.dayViewDecorator;
        if (r63 == null) goto L10;
        r63.initialize(r52);
    L10:
        if (this.fullscreen == false) goto L12;
        r42.findViewById(com.google.android.material.R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(r52), -2));
    L13:
        TextView r64 = (TextView) r42.findViewById(com.google.android.material.R.id.mtrl_picker_header_selection_text);
        this.headerSelectionText = r64;
        r64.setAccessibilityLiveRegion(1);
        this.headerToggleButton = (CheckableImageButton) r42.findViewById(com.google.android.material.R.id.mtrl_picker_header_toggle);
        this.headerTitleTextView = (TextView) r42.findViewById(com.google.android.material.R.id.mtrl_picker_title_text);
        initHeaderToggle(r52);
        this.confirmButton = (Button) r42.findViewById(com.google.android.material.R.id.confirm_button);
        boolean r53 = getDateSelector().isSelectionComplete();
        Button r65 = this.confirmButton;
        if (r53 == false) goto L16;
        r65.setEnabled(true);
    L17:
        this.confirmButton.setTag(CONFIRM_BUTTON_TAG);
        CharSequence r54 = this.positiveButtonText;
        if (r54 == null) goto L20;
        this.confirmButton.setText(r54);
    L23:
        CharSequence r55 = this.positiveButtonContentDescription;
        if (r55 == null) goto L27;
        this.confirmButton.setContentDescription(r55);
    L29:
        this.confirmButton.setOnClickListener(new MaterialDatePicker$$ExternalSyntheticLambda1(this));
        Button r56 = (Button) r42.findViewById(com.google.android.material.R.id.cancel_button);
        r56.setTag(CANCEL_BUTTON_TAG);
        CharSequence r66 = this.negativeButtonText;
        if (r66 == null) goto L32;
        r56.setText(r66);
    L35:
        CharSequence r67 = this.negativeButtonContentDescription;
        if (r67 == null) goto L39;
        r56.setContentDescription(r67);
    L41:
        r56.setOnClickListener(new MaterialDatePicker$$ExternalSyntheticLambda2(this));
        return r42;
    L39:
        if (this.negativeButtonContentDescriptionResId == 0) goto L41;
        r56.setContentDescription(getContext().getResources().getText(this.negativeButtonContentDescriptionResId));
        goto L41
    L32:
        int r68 = this.negativeButtonTextResId;
        if (r68 == 0) goto L35;
        r56.setText(r68);
        goto L35
    L27:
        if (this.positiveButtonContentDescriptionResId == 0) goto L29;
        this.confirmButton.setContentDescription(getContext().getResources().getText(this.positiveButtonContentDescriptionResId));
        goto L29
    L20:
        int r57 = this.positiveButtonTextResId;
        if (r57 == 0) goto L23;
        this.confirmButton.setText(r57);
        goto L23
    L16:
        r65.setEnabled(false);
        goto L17
    L12:
        r42.findViewById(com.google.android.material.R.id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(r52), -1));
        goto L13
    L5:
        r62 = com.google.android.material.R.layout.mtrl_picker_dialog;
        goto L6
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r3) {
        Iterator<DialogInterface.OnDismissListener> r0 = this.onDismissListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onDismiss(r3);
        goto L4
    L6:
        ViewGroup r02 = (ViewGroup) getView();
        if (r02 == null) goto L9;
        r02.removeAllViews();
    L9:
        super.onDismiss(r3);
    }

    public void onNegativeButtonClick(View r3) {
        Iterator<View.OnClickListener> r0 = this.onNegativeButtonClickListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onClick(r3);
        goto L4
    L6:
        dismiss();
    }

    public void onPositiveButtonClick(View r3) {
        Iterator<MaterialPickerOnPositiveButtonClickListener<? super S>> r32 = this.onPositiveButtonClickListeners.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        r32.next().onPositiveButtonClick(getSelection());
        goto L4
    L6:
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle r4) {
        super.onSaveInstanceState(r4);
        r4.putInt(OVERRIDE_THEME_RES_ID, this.overrideThemeResId);
        r4.putParcelable(DATE_SELECTOR_KEY, this.dateSelector);
        CalendarConstraints.Builder r0 = new CalendarConstraints.Builder(this.calendarConstraints);
        MaterialCalendar<S> r1 = this.calendar;
        if (r1 != null) goto L5;
        Month r12 = null;
    L6:
        if (r12 == null) goto L8;
        r0.setOpenAt(r12.timeInMillis);
    L8:
        r4.putParcelable(CALENDAR_CONSTRAINTS_KEY, r0.build());
        r4.putParcelable(DAY_VIEW_DECORATOR_KEY, this.dayViewDecorator);
        r4.putInt(TITLE_TEXT_RES_ID_KEY, this.titleTextResId);
        r4.putCharSequence(TITLE_TEXT_KEY, this.titleText);
        r4.putInt(INPUT_MODE_KEY, this.inputMode);
        r4.putInt(POSITIVE_BUTTON_TEXT_RES_ID_KEY, this.positiveButtonTextResId);
        r4.putCharSequence(POSITIVE_BUTTON_TEXT_KEY, this.positiveButtonText);
        r4.putInt(POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY, this.positiveButtonContentDescriptionResId);
        r4.putCharSequence(POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY, this.positiveButtonContentDescription);
        r4.putInt(NEGATIVE_BUTTON_TEXT_RES_ID_KEY, this.negativeButtonTextResId);
        r4.putCharSequence(NEGATIVE_BUTTON_TEXT_KEY, this.negativeButtonText);
        r4.putInt(NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY, this.negativeButtonContentDescriptionResId);
        r4.putCharSequence(NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY, this.negativeButtonContentDescription);
        return;
    L5:
        r12 = r1.getCurrentMonth();
        goto L6
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window r0 = requireDialog().getWindow();
        if (this.fullscreen == false) goto L5;
        r0.setLayout(-1, -1);
        r0.setBackgroundDrawable(this.background);
        enableEdgeToEdgeIfNeeded(r0);
    L6:
        startPickerFragment();
        return;
    L5:
        r0.setLayout(-2, -2);
        int r5 = getResources().getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_dialog_background_inset);
        Rect r1 = new Rect(r5, r5, r5, r5);
        r0.setBackgroundDrawable(new InsetDrawable(this.background, r5, r5, r5, r5));
        r0.getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(requireDialog(), r1));
        goto L6
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        this.pickerFragment.clearOnSelectionChangedListeners();
        super.onStop();
    }

    public boolean removeOnCancelListener(DialogInterface.OnCancelListener r1) {
        return this.onCancelListeners.remove(r1);
    }

    public boolean removeOnDismissListener(DialogInterface.OnDismissListener r1) {
        return this.onDismissListeners.remove(r1);
    }

    public boolean removeOnNegativeButtonClickListener(View.OnClickListener r1) {
        return this.onNegativeButtonClickListeners.remove(r1);
    }

    public boolean removeOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener<? super S> r1) {
        return this.onPositiveButtonClickListeners.remove(r1);
    }

    public void updateHeader(String r3) {
        this.headerSelectionText.setContentDescription(getHeaderContentDescription());
        this.headerSelectionText.setText(r3);
    }
}
