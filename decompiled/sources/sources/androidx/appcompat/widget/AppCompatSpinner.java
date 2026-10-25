package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.util.ObjectsCompat;

/* loaded from: classes3.dex */
public class AppCompatSpinner extends Spinner {

    @SuppressLint({"ResourceType"})
    private static final int[] ATTRS_ANDROID_SPINNERMODE = null;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    private SpinnerPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$1, reason: invalid class name */
    public class AnonymousClass1 extends ForwardingListener {
        final /* synthetic */ AppCompatSpinner this$0;
        final /* synthetic */ DropdownPopup val$popup;

        public AnonymousClass1(AppCompatSpinner r1, View r2, DropdownPopup r3) {
            this.this$0 = r1;
            this.val$popup = r3;
            super(r2);
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public ShowableListMenu getPopup() {
            return this.val$popup;
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public boolean onForwardingStarted() {
            if (this.this$0.getInternalPopup().isShowing() == true) goto L7;
            this.this$0.showPopup();
            return true;
        L7:
            return true;
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$2, reason: invalid class name */
    public class AnonymousClass2 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ AppCompatSpinner this$0;

        public AnonymousClass2(AppCompatSpinner r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (this.this$0.getInternalPopup().isShowing() == true) goto L5;
            this.this$0.showPopup();
        L5:
            ViewTreeObserver r0 = this.this$0.getViewTreeObserver();
            if (r0 == null) goto L9;
            r0.removeOnGlobalLayoutListener(this);
            return;
        }
    }

    public static final class Api23Impl {
        public static void setDropDownViewTheme(ThemedSpinnerAdapter r1, Resources.Theme r2) {
            if (ObjectsCompat.equals(r1.getDropDownViewTheme(), r2) == true) goto L6;
            r1.setDropDownViewTheme(r2);
            return;
        }
    }

    public class DialogPopup implements SpinnerPopup, DialogInterface.OnClickListener {
        private ListAdapter mListAdapter;
        AlertDialog mPopup;
        private CharSequence mPrompt;
        final /* synthetic */ AppCompatSpinner this$0;

        public DialogPopup(AppCompatSpinner r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void dismiss() {
            AlertDialog r0 = this.mPopup;
            if (r0 == null) goto L6;
            r0.dismiss();
            this.mPopup = null;
            return;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public Drawable getBackground() {
            return null;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public CharSequence getHintText() {
            return this.mPrompt;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public int getHorizontalOffset() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public int getVerticalOffset() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public boolean isShowing() {
            AlertDialog r0 = this.mPopup;
            if (r0 != null) goto L5;
            return false;
        L5:
            return r0.isShowing();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface r4, int r5) {
            this.this$0.setSelection(r5);
            if (this.this$0.getOnItemClickListener() == null) goto L5;
            this.this$0.performItemClick(null, r5, this.mListAdapter.getItemId(r5));
        L5:
            dismiss();
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setAdapter(ListAdapter r1) {
            this.mListAdapter = r1;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setBackgroundDrawable(Drawable r1) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setHorizontalOffset(int r1) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setHorizontalOriginalOffset(int r1) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setPromptText(CharSequence r1) {
            this.mPrompt = r1;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setVerticalOffset(int r1) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void show(int r4, int r5) {
            if (this.mListAdapter != null) goto L5;
            return;
        L5:
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getPopupContext());
            CharSequence r1 = this.mPrompt;
            if (r1 == null) goto L8;
            r0.setTitle(r1);
        L8:
            AlertDialog r02 = r0.setSingleChoiceItems(this.mListAdapter, this.this$0.getSelectedItemPosition(), this).create();
            this.mPopup = r02;
            ListView r03 = r02.getListView();
            r03.setTextDirection(r4);
            r03.setTextAlignment(r5);
            this.mPopup.show();
        }
    }

    public static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter r2, Resources.Theme r3) {
            this.mAdapter = r2;
            if ((r2 instanceof ListAdapter) == false) goto L5;
            this.mListAdapter = (ListAdapter) r2;
        L5:
            if (r3 != null) goto L7;
            return;
        L7:
            if ((r2 instanceof ThemedSpinnerAdapter) == false) goto L11;
            Api23Impl.setDropDownViewTheme((ThemedSpinnerAdapter) r2, r3);
            return;
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter r0 = this.mListAdapter;
            if (r0 != null) goto L5;
            return true;
        L5:
            return r0.areAllItemsEnabled();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter r0 = this.mAdapter;
            if (r0 != null) goto L7;
            return 0;
        L7:
            return r0.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int r1, View r2, ViewGroup r3) {
            SpinnerAdapter r0 = this.mAdapter;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.getDropDownView(r1, r2, r3);
        }

        @Override // android.widget.Adapter
        public Object getItem(int r1) {
            SpinnerAdapter r0 = this.mAdapter;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.getItem(r1);
        }

        @Override // android.widget.Adapter
        public long getItemId(int r1) {
            SpinnerAdapter r0 = this.mAdapter;
            if (r0 != null) goto L7;
            return -1;
        L7:
            return r0.getItemId(r1);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int r1, View r2, ViewGroup r3) {
            return getDropDownView(r1, r2, r3);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter r0 = this.mAdapter;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.hasStableIds() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            if (getCount() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int r1) {
            ListAdapter r0 = this.mListAdapter;
            if (r0 != null) goto L5;
            return true;
        L5:
            return r0.isEnabled(r1);
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver r1) {
            SpinnerAdapter r0 = this.mAdapter;
            if (r0 == null) goto L6;
            r0.registerDataSetObserver(r1);
            return;
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver r1) {
            SpinnerAdapter r0 = this.mAdapter;
            if (r0 == null) goto L6;
            r0.unregisterDataSetObserver(r1);
            return;
        }
    }

    public class DropdownPopup extends ListPopupWindow implements SpinnerPopup {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private int mOriginalHorizontalOffset;
        private final Rect mVisibleRect;
        final /* synthetic */ AppCompatSpinner this$0;

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$DropdownPopup$1, reason: invalid class name */
        public class AnonymousClass1 implements AdapterView.OnItemClickListener {
            final /* synthetic */ DropdownPopup this$1;
            final /* synthetic */ AppCompatSpinner val$this$0;

            public AnonymousClass1(DropdownPopup r1, AppCompatSpinner r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> r3, View r4, int r5, long r6) {
                this.this$1.this$0.setSelection(r5);
                if (this.this$1.this$0.getOnItemClickListener() == null) goto L5;
                DropdownPopup r32 = this.this$1;
                r32.this$0.performItemClick(r4, r5, r32.mAdapter.getItemId(r5));
            L5:
                this.this$1.dismiss();
            }
        }

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$DropdownPopup$2, reason: invalid class name */
        public class AnonymousClass2 implements ViewTreeObserver.OnGlobalLayoutListener {
            final /* synthetic */ DropdownPopup this$1;

            public AnonymousClass2(DropdownPopup r1) {
                this.this$1 = r1;
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                DropdownPopup r0 = this.this$1;
                boolean r02 = r0.isVisibleToUser(r0.this$0);
                DropdownPopup r1 = this.this$1;
                if (r02 == true) goto L6;
                r1.dismiss();
                return;
            L6:
                r1.computeContentWidth();
                DropdownPopup.access$001(this.this$1);
            }
        }

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$DropdownPopup$3, reason: invalid class name */
        public class AnonymousClass3 implements PopupWindow.OnDismissListener {
            final /* synthetic */ DropdownPopup this$1;
            final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;

            public AnonymousClass3(DropdownPopup r1, ViewTreeObserver.OnGlobalLayoutListener r2) {
                this.this$1 = r1;
                this.val$layoutListener = r2;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver r0 = this.this$1.this$0.getViewTreeObserver();
                if (r0 == null) goto L6;
                r0.removeGlobalOnLayoutListener(this.val$layoutListener);
                return;
            }
        }

        public DropdownPopup(AppCompatSpinner r1, Context r2, AttributeSet r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.mVisibleRect = new Rect();
            setAnchorView(r1);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new AnonymousClass1(this, r1));
        }

        public static /* synthetic */ void access$001(DropdownPopup r0) {
            super.show();
        }

        public void computeContentWidth() {
            Drawable r0 = getBackground();
            AppCompatSpinner r1 = this.this$0;
            if (r0 == null) goto L8;
            r0.getPadding(r1.mTempRect);
            boolean r02 = ViewUtils.isLayoutRtl(this.this$0);
            AppCompatSpinner r12 = this.this$0;
            if (r02 == false) goto L7;
            int r03 = r12.mTempRect.right;
        L9:
            int r13 = this.this$0.getPaddingLeft();
            int r2 = this.this$0.getPaddingRight();
            int r3 = this.this$0.getWidth();
            AppCompatSpinner r4 = this.this$0;
            int r5 = r4.mDropDownWidth;
            if (r5 != (-2)) goto L16;
            int r42 = r4.compatMeasureContentWidth((SpinnerAdapter) this.mAdapter, getBackground());
            int r52 = this.this$0.getContext().getResources().getDisplayMetrics().widthPixels;
            Rect r6 = this.this$0.mTempRect;
            int r53 = (r52 - r6.left) - r6.right;
            if (r42 <= r53) goto L14;
            r42 = r53;
        L14:
            setContentWidth(Math.max(r42, (r3 - r13) - r2));
        L20:
            if (ViewUtils.isLayoutRtl(this.this$0) == false) goto L22;
            int r04 = r03 + (((r3 - r2) - getWidth()) - getHorizontalOriginalOffset());
        L23:
            setHorizontalOffset(r04);
            return;
        L22:
            r04 = r03 + (r13 + getHorizontalOriginalOffset());
            goto L23
        L16:
            if (r5 != (-1)) goto L18;
            setContentWidth((r3 - r13) - r2);
            goto L20
        L18:
            setContentWidth(r5);
            goto L20
        L7:
            r03 = -r12.mTempRect.left;
            goto L9
        L8:
            Rect r05 = r1.mTempRect;
            r05.right = 0;
            r05.left = 0;
            r03 = 0;
            goto L9
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public CharSequence getHintText() {
            return this.mHintText;
        }

        public int getHorizontalOriginalOffset() {
            return this.mOriginalHorizontalOffset;
        }

        public boolean isVisibleToUser(View r2) {
            if (r2.isAttachedToWindow() == true) goto L5;
            return false;
        L5:
            if (r2.getGlobalVisibleRect(this.mVisibleRect) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.appcompat.widget.ListPopupWindow, androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setAdapter(ListAdapter r1) {
            super.setAdapter(r1);
            this.mAdapter = r1;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setHorizontalOriginalOffset(int r1) {
            this.mOriginalHorizontalOffset = r1;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void setPromptText(CharSequence r1) {
            this.mHintText = r1;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
        public void show(int r4, int r5) {
            boolean r0 = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            super.show();
            ListView r1 = getListView();
            r1.setChoiceMode(1);
            r1.setTextDirection(r4);
            r1.setTextAlignment(r5);
            setSelection(this.this$0.getSelectedItemPosition());
            if (r0 == true) goto L10;
            ViewTreeObserver r42 = this.this$0.getViewTreeObserver();
            if (r42 == null) goto L9;
            AnonymousClass2 r52 = new AnonymousClass2(this);
            r42.addOnGlobalLayoutListener(r52);
            setOnDismissListener(new AnonymousClass3(this, r52));
            return;
        L9:
            return;
        }
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        boolean mShowDropdown;

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r1) {
                return new SavedState(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1) {
            super(r1);
            if (r1.readByte() == 0) goto L5;
            boolean r12 = true;
        L6:
            this.mShowDropdown = r12;
            return;
        L5:
            r12 = false;
            goto L6
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeByte(this.mShowDropdown ? 1 : 0);
        }
    }

    public interface SpinnerPopup {
        void dismiss();

        Drawable getBackground();

        CharSequence getHintText();

        int getHorizontalOffset();

        int getVerticalOffset();

        boolean isShowing();

        void setAdapter(ListAdapter r1);

        void setBackgroundDrawable(Drawable r1);

        void setHorizontalOffset(int r1);

        void setHorizontalOriginalOffset(int r1);

        void setPromptText(CharSequence r1);

        void setVerticalOffset(int r1);

        void show(int r1, int r2);
    }

    static {
        ATTRS_ANDROID_SPINNERMODE = new int[]{R.attr.spinnerMode};
    }

    public AppCompatSpinner(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.spinnerStyle);
    }

    public AppCompatSpinner(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, -1);
    }

    public AppCompatSpinner(Context r7, AttributeSet r8, int r9, int r10) {
        this(r7, r8, r9, r10, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v7, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.view.View, androidx.appcompat.widget.AppCompatSpinner] */
    public AppCompatSpinner(Context r7, AttributeSet r8, int r9, int r10, Resources.Theme r11) {
        super(r7, r8, r9);
        this.mTempRect = new Rect();
        ThemeUtils.checkAppCompatTheme(this, getContext());
        TintTypedArray r0 = TintTypedArray.obtainStyledAttributes(r7, r8, R$styleable.Spinner, r9, 0);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        if (r11 == null) goto L5;
        this.mPopupContext = new ContextThemeWrapper(r7, r11);
    L9:
        ?? r112 = -1;
        TypedArray r2 = null;
        if (r10 != (-1)) goto L32;
        TypedArray r113 = r7.obtainStyledAttributes(r8, ATTRS_ANDROID_SPINNERMODE, r9, 0);     // Catch: Throwable -> L21 Exception -> L23
        boolean r3 = r113.hasValue(0);     // Catch: Throwable -> L16 Exception -> L18
        r112 = r113;
        if (r3 == false) goto L20;
        r10 = r113.getInt(0, 0);     // Catch: Throwable -> L16 Exception -> L18
        r112 = r113;
    L20:
        r112.recycle();
    L18:
        e = e;
    L25:
        Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);     // Catch: Throwable -> L16
        r112 = r113;
        if (r113 == null) goto L32;
    L23:
        e = e;
        r113 = null;
    L21:
        th = th;
    L28:
        if (r2 == null) goto L30;
        r2.recycle();
    L30:
        throw th;
    L32:
        if (r10 == 0) goto L36;
        if (r10 != 1) goto L37;
        DropdownPopup r102 = new DropdownPopup(this, this.mPopupContext, r8, r9);
        TintTypedArray r1 = TintTypedArray.obtainStyledAttributes(this.mPopupContext, r8, R$styleable.Spinner, r9, 0);
        this.mDropDownWidth = r1.getLayoutDimension(R$styleable.Spinner_android_dropDownWidth, -2);
        r102.setBackgroundDrawable(r1.getDrawable(R$styleable.Spinner_android_popupBackground));
        r102.setPromptText(r0.getString(R$styleable.Spinner_android_prompt));
        r1.recycle();
        this.mPopup = r102;
        this.mForwardingListener = new AnonymousClass1(this, this, r102);
    L37:
        CharSequence[] r103 = r0.getTextArray(R$styleable.Spinner_android_entries);
        if (r103 == null) goto L40;
        ArrayAdapter r12 = new ArrayAdapter(r7, R.layout.simple_spinner_item, r103);
        r12.setDropDownViewResource(R$layout.support_simple_spinner_dropdown_item);
        setAdapter(r12);
    L40:
        r0.recycle();
        this.mPopupSet = true;
        SpinnerAdapter r72 = this.mTempAdapter;
        if (r72 == null) goto L43;
        setAdapter(r72);
        this.mTempAdapter = null;
    L43:
        this.mBackgroundTintHelper.loadFromAttributes(r8, r9);
        return;
    L36:
        DialogPopup r104 = new DialogPopup(this);
        this.mPopup = r104;
        r104.setPromptText(r0.getString(R$styleable.Spinner_android_prompt));
    L16:
        th = th;
        r2 = r112;
        goto L28
    L5:
        int r114 = r0.getResourceId(R$styleable.Spinner_popupTheme, 0);
        if (r114 == 0) goto L8;
        this.mPopupContext = new ContextThemeWrapper(r7, r114);
        goto L9
    L8:
        this.mPopupContext = r7;
        goto L9
    }

    public int compatMeasureContentWidth(SpinnerAdapter r11, Drawable r12) {
        int r0 = 0;
        if (r11 != null) goto L5;
        return 0;
    L5:
        int r1 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int r2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int r3 = Math.max(0, getSelectedItemPosition());
        int r4 = Math.min(r11.getCount(), r3 + 15);
        int r6 = Math.max(0, r3 - (15 - (r4 - r3)));
        View r7 = null;
        int r32 = 0;
    L6:
        if (r6 >= r4) goto L14;
        int r8 = r11.getItemViewType(r6);
        if (r8 == r0) goto L10;
        r7 = null;
        r0 = r8;
    L10:
        r7 = r11.getView(r6, r7, this);
        if (r7.getLayoutParams() != null) goto L13;
        r7.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    L13:
        r7.measure(r1, r2);
        r32 = Math.max(r32, r7.getMeasuredWidth());
        r6 = r6 + 1;
        goto L6
    L14:
        if (r12 == null) goto L20;
        r12.getPadding(this.mTempRect);
        Rect r10 = this.mTempRect;
        return r32 + (r10.left + r10.right);
    L20:
        return r32;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.applySupportBackgroundTint();
        return;
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L7;
        return r0.getHorizontalOffset();
    L7:
        return super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L7;
        return r0.getVerticalOffset();
    L7:
        return super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.mPopup == null) goto L7;
        return this.mDropDownWidth;
    L7:
        return super.getDropDownWidth();
    }

    public final SpinnerPopup getInternalPopup() {
        return this.mPopup;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L7;
        return r0.getBackground();
    L7:
        return super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.mPopupContext;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L7;
        return r0.getHintText();
    L7:
        return super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SpinnerPopup r0 = this.mPopup;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isShowing() == false) goto L9;
        this.mPopup.dismiss();
        return;
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, r4);
        if (this.mPopup != null) goto L5;
        return;
    L5:
        if (View.MeasureSpec.getMode(r3) != Integer.MIN_VALUE) goto L9;
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(r3)), getMeasuredHeight());
        return;
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        if (r22.mShowDropdown == false) goto L8;
        ViewTreeObserver r23 = getViewTreeObserver();
        if (r23 == null) goto L9;
        r23.addOnGlobalLayoutListener(new AnonymousClass2(this));
        return;
    L9:
        return;
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState(super.onSaveInstanceState());
        SpinnerPopup r2 = this.mPopup;
        if (r2 != null) goto L5;
    L7:
        boolean r22 = false;
    L8:
        r0.mShowDropdown = r22;
        return r0;
    L5:
        if (r2.isShowing() == false) goto L7;
        r22 = true;
        goto L8
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        ForwardingListener r0 = this.mForwardingListener;
        if (r0 == null) goto L9;
        if (r0.onTouch(this, r2) == false) goto L9;
        return true;
    L9:
        return super.onTouchEvent(r2);
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L10;
        if (r0.isShowing() == true) goto L11;
        showPopup();
        return true;
    L11:
        return true;
    L10:
        return super.performClick();
    }

    @Override // android.widget.AdapterView
    public /* bridge */ /* synthetic */ void setAdapter(Adapter r1) {
        setAdapter((SpinnerAdapter) r1);
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner
    public void setAdapter(SpinnerAdapter r3) {
        if (this.mPopupSet == true) goto L6;
        this.mTempAdapter = r3;
        return;
    L6:
        super.setAdapter(r3);
        if (this.mPopup == null) goto L13;
        Context r0 = this.mPopupContext;
        if (r0 != null) goto L11;
        r0 = getContext();
    L11:
        this.mPopup.setAdapter(new DropDownAdapter(r3, r0.getTheme()));
        return;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r1) {
        super.setBackgroundDrawable(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundDrawable(r1);
        return;
    }

    @Override // android.view.View
    public void setBackgroundResource(int r1) {
        super.setBackgroundResource(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundResource(r1);
        return;
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int r2) {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L6;
        r0.setHorizontalOriginalOffset(r2);
        this.mPopup.setHorizontalOffset(r2);
        return;
    L6:
        super.setDropDownHorizontalOffset(r2);
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int r2) {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L6;
        r0.setVerticalOffset(r2);
        return;
    L6:
        super.setDropDownVerticalOffset(r2);
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int r2) {
        if (this.mPopup == null) goto L6;
        this.mDropDownWidth = r2;
        return;
    L6:
        super.setDropDownWidth(r2);
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable r2) {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L6;
        r0.setBackgroundDrawable(r2);
        return;
    L6:
        super.setPopupBackgroundDrawable(r2);
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int r2) {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), r2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence r2) {
        SpinnerPopup r0 = this.mPopup;
        if (r0 == null) goto L6;
        r0.setPromptText(r2);
        return;
    L6:
        super.setPrompt(r2);
    }

    public void setSupportBackgroundTintList(ColorStateList r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintList(r1);
        return;
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintMode(r1);
        return;
    }

    public void showPopup() {
        this.mPopup.show(getTextDirection(), getTextAlignment());
    }
}
