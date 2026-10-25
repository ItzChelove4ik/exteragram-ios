package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.widget.PopupWindowCompat;
import java.lang.reflect.Method;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class ListPopupWindow implements ShowableListMenu {
    private static Method sSetClipToWindowEnabledMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ ListPopupWindow this$0;

        public AnonymousClass2(ListPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            View r0 = this.this$0.getAnchorView();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getWindowToken() == null) goto L9;
            this.this$0.show();
            return;
        }
    }

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$3, reason: invalid class name */
    public class AnonymousClass3 implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ ListPopupWindow this$0;

        public AnonymousClass3(ListPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> r1, View r2, int r3, long r4) {
            if (r3 == (-1)) goto L8;
            DropDownListView r0 = this.this$0.mDropDownList;
            if (r0 == null) goto L9;
            r0.setListSelectionHidden(false);
            return;
        L9:
            return;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> r1) {
        }
    }

    public static class Api24Impl {
        public static int getMaxAvailableHeight(PopupWindow r0, View r1, int r2, boolean r3) {
            return r0.getMaxAvailableHeight(r1, r2, r3);
        }
    }

    public static class Api29Impl {
        public static void setEpicenterBounds(PopupWindow r0, Rect r1) {
            r0.setEpicenterBounds(r1);
        }

        public static void setIsClippedToScreen(PopupWindow r0, boolean r1) {
            r0.setIsClippedToScreen(r1);
        }
    }

    public class ListSelectorHider implements Runnable {
        final /* synthetic */ ListPopupWindow this$0;

        public ListSelectorHider(ListPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.clearListSelection();
        }
    }

    public class PopupDataSetObserver extends DataSetObserver {
        final /* synthetic */ ListPopupWindow this$0;

        public PopupDataSetObserver(ListPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (this.this$0.isShowing() == false) goto L6;
            this.this$0.show();
            return;
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            this.this$0.dismiss();
        }
    }

    public class PopupScrollListener implements AbsListView.OnScrollListener {
        final /* synthetic */ ListPopupWindow this$0;

        public PopupScrollListener(ListPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView r1, int r2, int r3, int r4) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView r1, int r2) {
            if (r2 == 1) goto L5;
            return;
        L5:
            if (this.this$0.isInputMethodNotNeeded() == false) goto L7;
            return;
        L7:
            if (this.this$0.mPopup.getContentView() == null) goto L12;
            ListPopupWindow r12 = this.this$0;
            r12.mHandler.removeCallbacks(r12.mResizePopupRunnable);
            this.this$0.mResizePopupRunnable.run();
            return;
        }
    }

    public class PopupTouchInterceptor implements View.OnTouchListener {
        final /* synthetic */ ListPopupWindow this$0;

        public PopupTouchInterceptor(ListPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r3, MotionEvent r4) {
            int r32 = r4.getAction();
            int r0 = (int) r4.getX();
            int r42 = (int) r4.getY();
            if (r32 != 0) goto L16;
            PopupWindow r1 = this.this$0.mPopup;
            if (r1 == null) goto L16;
            if (r1.isShowing() == false) goto L16;
            if (r0 < 0) goto L16;
            if (r0 >= this.this$0.mPopup.getWidth()) goto L16;
            if (r42 < 0) goto L16;
            if (r42 >= this.this$0.mPopup.getHeight()) goto L16;
            ListPopupWindow r2 = this.this$0;
            r2.mHandler.postDelayed(r2.mResizePopupRunnable, 250);
            return false;
        L16:
            if (r32 != 1) goto L21;
            ListPopupWindow r22 = this.this$0;
            r22.mHandler.removeCallbacks(r22.mResizePopupRunnable);
            return false;
        L21:
            return false;
        }
    }

    public class ResizePopupRunnable implements Runnable {
        final /* synthetic */ ListPopupWindow this$0;

        public ResizePopupRunnable(ListPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView r0 = this.this$0.mDropDownList;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isAttachedToWindow() == true) goto L7;
            return;
        L7:
            if (this.this$0.mDropDownList.getCount() <= this.this$0.mDropDownList.getChildCount()) goto L14;
            int r02 = this.this$0.mDropDownList.getChildCount();
            ListPopupWindow r1 = this.this$0;
            if (r02 > r1.mListItemExpandMaximum) goto L15;
            r1.mPopup.setInputMethodMode(2);
            this.this$0.show();
            return;
        L15:
            return;
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) goto L12;
        return;
    L12:
        sSetClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});     // Catch: NoSuchMethodException -> L7
    L14:
        sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});     // Catch: NoSuchMethodException -> L10
        return;
    L10:
        Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        return;
    L7:
        Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        goto L14
    }

    public ListPopupWindow(Context r3) {
        this(r3, null, R$attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, 0);
    }

    public ListPopupWindow(Context r4, AttributeSet r5, int r6, int r7) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable(this);
        this.mTouchInterceptor = new PopupTouchInterceptor(this);
        this.mScrollListener = new PopupScrollListener(this);
        this.mHideSelector = new ListSelectorHider(this);
        this.mTempRect = new Rect();
        this.mContext = r4;
        this.mHandler = new Handler(r4.getMainLooper());
        TypedArray r1 = r4.obtainStyledAttributes(r5, R$styleable.ListPopupWindow, r6, r7);
        this.mDropDownHorizontalOffset = r1.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int r0 = r1.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.mDropDownVerticalOffset = r0;
        if (r0 == 0) goto L5;
        this.mDropDownVerticalOffsetSet = true;
    L5:
        r1.recycle();
        AppCompatPopupWindow r02 = new AppCompatPopupWindow(r4, r5, r6, r7);
        this.mPopup = r02;
        r02.setInputMethodMode(1);
    }

    private int buildDropDown() {
        boolean r3 = true;
        if (this.mDropDownList != null) goto L25;
        Context r0 = this.mContext;
        this.mShowDropDownRunnable = new AnonymousClass2(this);
        DropDownListView r5 = createDropDownListView(r0, !this.mModal);
        this.mDropDownList = r5;
        Drawable r6 = this.mDropDownListHighlight;
        if (r6 == null) goto L7;
        r5.setSelector(r6);
    L7:
        this.mDropDownList.setAdapter(this.mAdapter);
        this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
        this.mDropDownList.setFocusable(true);
        this.mDropDownList.setFocusableInTouchMode(true);
        this.mDropDownList.setOnItemSelectedListener(new AnonymousClass3(this));
        this.mDropDownList.setOnScrollListener(this.mScrollListener);
        AdapterView.OnItemSelectedListener r52 = this.mItemSelectedListener;
        if (r52 == null) goto L10;
        this.mDropDownList.setOnItemSelectedListener(r52);
    L10:
        View r53 = this.mDropDownList;
        View r62 = this.mPromptView;
        if (r62 == null) goto L23;
        LinearLayout r7 = new LinearLayout(r0);
        r7.setOrientation(1);
        ViewGroup.LayoutParams r02 = new LinearLayout.LayoutParams(-1, 0, 1.0f);
        int r8 = this.mPromptPosition;
        if (r8 == 0) goto L17;
        if (r8 == 1) goto L16;
        Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
    L18:
        int r03 = this.mDropDownWidth;
        if (r03 < 0) goto L21;
        int r54 = Integer.MIN_VALUE;
    L22:
        r62.measure(View.MeasureSpec.makeMeasureSpec(r03, r54), 0);
        LinearLayout.LayoutParams r04 = (LinearLayout.LayoutParams) r62.getLayoutParams();
        int r05 = (r62.getMeasuredHeight() + r04.topMargin) + r04.bottomMargin;
        r53 = r7;
    L24:
        this.mPopup.setContentView(r53);
    L29:
        Drawable r55 = this.mPopup.getBackground();
        Rect r63 = this.mTempRect;
        if (r55 == null) goto L34;
        r55.getPadding(r63);
        Rect r56 = this.mTempRect;
        int r64 = r56.top;
        int r57 = r56.bottom + r64;
        if (this.mDropDownVerticalOffsetSet == true) goto L36;
        this.mDropDownVerticalOffset = -r64;
    L36:
        if (this.mPopup.getInputMethodMode() == 2) goto L39;
        r3 = false;
    L39:
        int r32 = getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset, r3);
        if (this.mDropDownAlwaysVisible == true) goto L58;
        if (this.mDropDownHeight == (-1)) goto L58;
        int r4 = this.mDropDownWidth;
        if (r4 != (-2)) goto L47;
        int r2 = this.mContext.getResources().getDisplayMetrics().widthPixels;
        Rect r42 = this.mTempRect;
        int r1 = View.MeasureSpec.makeMeasureSpec(r2 - (r42.left + r42.right), Integer.MIN_VALUE);
    L52:
        int r12 = this.mDropDownList.measureHeightOfChildrenCompat(r1, 0, -1, r32 - r05, -1);
        if (r12 <= 0) goto L56;
        r05 = r05 + (r57 + (this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom()));
    L56:
        return r12 + r05;
    L47:
        if (r4 == (-1)) goto L50;
        r1 = View.MeasureSpec.makeMeasureSpec(r4, TLObject.FLAG_30);
        goto L52
    L50:
        int r22 = this.mContext.getResources().getDisplayMetrics().widthPixels;
        Rect r43 = this.mTempRect;
        r1 = View.MeasureSpec.makeMeasureSpec(r22 - (r43.left + r43.right), TLObject.FLAG_30);
    L58:
        return r32 + r57;
    L34:
        r63.setEmpty();
        r57 = 0;
        goto L36
    L21:
        r03 = 0;
        r54 = 0;
        goto L22
    L16:
        r7.addView(r53, r02);
        r7.addView(r62);
        goto L18
    L17:
        r7.addView(r62);
        r7.addView(r53, r02);
        goto L18
    L23:
        r05 = 0;
        goto L24
    L25:
        ViewGroup r06 = (ViewGroup) this.mPopup.getContentView();
        View r07 = this.mPromptView;
        if (r07 == null) goto L28;
        LinearLayout.LayoutParams r58 = (LinearLayout.LayoutParams) r07.getLayoutParams();
        r05 = (r07.getMeasuredHeight() + r58.topMargin) + r58.bottomMargin;
        goto L29
    L28:
        r05 = 0;
        goto L29
    }

    private int getMaxAvailableHeight(View r1, int r2, boolean r3) {
        return Api24Impl.getMaxAvailableHeight(this.mPopup, r1, r2, r3);
    }

    private void removePromptView() {
        View r0 = this.mPromptView;
        if (r0 == null) goto L8;
        ViewParent r02 = r0.getParent();
        if ((r02 instanceof ViewGroup) == false) goto L9;
        ((ViewGroup) r02).removeView(this.mPromptView);
        return;
    L9:
        return;
    }

    private void setPopupClipToScreenEnabled(boolean r4) {
        if (Build.VERSION.SDK_INT > 28) goto L10;
        Method r0 = sSetClipToWindowEnabledMethod;
        if (r0 == null) goto L14;
        r0.invoke(this.mPopup, new Object[]{Boolean.valueOf(r4)});     // Catch: Exception -> L8
        return;
    L8:
        Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
        return;
    L14:
        return;
    L10:
        Api29Impl.setIsClippedToScreen(this.mPopup, r4);
    }

    public void clearListSelection() {
        DropDownListView r1 = this.mDropDownList;
        if (r1 == null) goto L6;
        r1.setListSelectionHidden(true);
        r1.requestLayout();
        return;
    }

    public DropDownListView createDropDownListView(Context r1, boolean r2) {
        return new DropDownListView(r1, r2);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        this.mPopup.dismiss();
        removePromptView();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mDropDownList;
    }

    public Object getSelectedItem() {
        if (isShowing() == true) goto L7;
        return null;
    L7:
        return this.mDropDownList.getSelectedItem();
    }

    public long getSelectedItemId() {
        if (isShowing() == true) goto L7;
        return Long.MIN_VALUE;
    L7:
        return this.mDropDownList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        if (isShowing() == true) goto L7;
        return -1;
    L7:
        return this.mDropDownList.getSelectedItemPosition();
    }

    public View getSelectedView() {
        if (isShowing() == true) goto L7;
        return null;
    L7:
        return this.mDropDownList.getSelectedView();
    }

    public int getVerticalOffset() {
        if (this.mDropDownVerticalOffsetSet == true) goto L7;
        return 0;
    L7:
        return this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public boolean isInputMethodNotNeeded() {
        if (this.mPopup.getInputMethodMode() != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isModal() {
        return this.mModal;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public void setAdapter(ListAdapter r3) {
        DataSetObserver r0 = this.mObserver;
        if (r0 != null) goto L5;
        this.mObserver = new PopupDataSetObserver(this);
    L8:
        this.mAdapter = r3;
        if (r3 == null) goto L11;
        r3.registerDataSetObserver(this.mObserver);
    L11:
        DropDownListView r32 = this.mDropDownList;
        if (r32 == null) goto L15;
        r32.setAdapter(this.mAdapter);
        return;
    L15:
        return;
    L5:
        ListAdapter r1 = this.mAdapter;
        if (r1 == null) goto L8;
        r1.unregisterDataSetObserver(r0);
        goto L8
    }

    public void setAnchorView(View r1) {
        this.mDropDownAnchorView = r1;
    }

    public void setAnimationStyle(int r1) {
        this.mPopup.setAnimationStyle(r1);
    }

    public void setBackgroundDrawable(Drawable r1) {
        this.mPopup.setBackgroundDrawable(r1);
    }

    public void setContentWidth(int r3) {
        Drawable r0 = this.mPopup.getBackground();
        if (r0 == null) goto L6;
        r0.getPadding(this.mTempRect);
        Rect r02 = this.mTempRect;
        this.mDropDownWidth = (r02.left + r02.right) + r3;
        return;
    L6:
        setWidth(r3);
    }

    public void setDropDownGravity(int r1) {
        this.mDropDownGravity = r1;
    }

    public void setEpicenterBounds(Rect r2) {
        if (r2 == null) goto L4;
        Rect r0 = new Rect(r2);
    L5:
        this.mEpicenterBounds = r0;
        return;
    L4:
        r0 = null;
        goto L5
    }

    public void setHorizontalOffset(int r1) {
        this.mDropDownHorizontalOffset = r1;
    }

    public void setInputMethodMode(int r1) {
        this.mPopup.setInputMethodMode(r1);
    }

    public void setModal(boolean r1) {
        this.mModal = r1;
        this.mPopup.setFocusable(r1);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener r1) {
        this.mPopup.setOnDismissListener(r1);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener r1) {
        this.mItemClickListener = r1;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener r1) {
        this.mItemSelectedListener = r1;
    }

    public void setOverlapAnchor(boolean r2) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = r2;
    }

    public void setPromptPosition(int r1) {
        this.mPromptPosition = r1;
    }

    public void setSelection(int r2) {
        DropDownListView r0 = this.mDropDownList;
        if (isShowing() == false) goto L9;
        if (r0 == null) goto L10;
        r0.setListSelectionHidden(false);
        r0.setSelection(r2);
        if (r0.getChoiceMode() == 0) goto L11;
        r0.setItemChecked(r2, true);
        return;
    L11:
        return;
    L10:
        return;
    }

    public void setVerticalOffset(int r1) {
        this.mDropDownVerticalOffset = r1;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int r1) {
        this.mDropDownWidth = r1;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        int r0 = buildDropDown();
        boolean r1 = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        boolean r3 = true;
        if (this.mPopup.isShowing() == true) goto L5;
        int r12 = this.mDropDownWidth;
        if (r12 != (-1)) goto L50;
        r12 = -1;
    L52:
        int r2 = this.mDropDownHeight;
        if (r2 != (-1)) goto L55;
        r0 = -1;
    L58:
        this.mPopup.setWidth(r12);
        this.mPopup.setHeight(r0);
        setPopupClipToScreenEnabled(true);
        PopupWindow r02 = this.mPopup;
        if (this.mForceIgnoreOutsideTouch == false) goto L61;
    L63:
        r3 = false;
    L64:
        r02.setOutsideTouchable(r3);
        this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
        if (this.mOverlapAnchorSet == false) goto L68;
        PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
    L68:
        if (Build.VERSION.SDK_INT > 28) goto L75;
        Method r03 = sSetEpicenterBoundsMethod;
        if (r03 != null) goto L85;
    L76:
        PopupWindowCompat.showAsDropDown(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
        this.mDropDownList.setSelection(-1);
        if (this.mModal == true) goto L79;
    L80:
        clearListSelection();
    L82:
        if (this.mModal == true) goto L87;
        this.mHandler.post(this.mHideSelector);
        return;
    L87:
        return;
    L79:
        if (this.mDropDownList.isInTouchMode() == false) goto L82;
    L85:
        r03.invoke(this.mPopup, new Object[]{this.mEpicenterBounds});     // Catch: Exception -> L73
    L73:
        e = move-exception;
        Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
        goto L76
    L75:
        Api29Impl.setEpicenterBounds(this.mPopup, this.mEpicenterBounds);
        goto L76
    L61:
        if (this.mDropDownAlwaysVisible == true) goto L63;
    L55:
        if (r2 == (-2)) goto L58;
        r0 = r2;
        goto L58
    L50:
        if (r12 != (-2)) goto L52;
        r12 = getAnchorView().getWidth();
        goto L52
    L5:
        if (getAnchorView().isAttachedToWindow() == false) goto L88;
        int r22 = this.mDropDownWidth;
        if (r22 != (-1)) goto L10;
        r22 = -1;
    L12:
        int r7 = this.mDropDownHeight;
        if (r7 != (-1)) goto L29;
        if (r1 == true) goto L17;
        r0 = -1;
    L17:
        PopupWindow r4 = this.mPopup;
        if (r1 == false) goto L25;
        if (this.mDropDownWidth != (-1)) goto L22;
        int r13 = -1;
    L23:
        r4.setWidth(r13);
        this.mPopup.setHeight(0);
    L32:
        PopupWindow r14 = this.mPopup;
        if (this.mForceIgnoreOutsideTouch == false) goto L35;
    L37:
        r3 = false;
    L38:
        r14.setOutsideTouchable(r3);
        PopupWindow r72 = this.mPopup;
        View r8 = getAnchorView();
        int r9 = this.mDropDownHorizontalOffset;
        int r10 = this.mDropDownVerticalOffset;
        if (r22 >= 0) goto L41;
        int r11 = -1;
    L42:
        if (r0 >= 0) goto L44;
        int r122 = -1;
    L45:
        r72.update(r8, r9, r10, r11, r122);
        return;
    L44:
        r122 = r0;
        goto L45
    L41:
        r11 = r22;
        goto L42
    L35:
        if (this.mDropDownAlwaysVisible == true) goto L37;
    L22:
        r13 = 0;
        goto L23
    L25:
        if (this.mDropDownWidth != (-1)) goto L27;
        int r15 = -1;
    L28:
        r4.setWidth(r15);
        this.mPopup.setHeight(-1);
        goto L32
    L27:
        r15 = 0;
        goto L28
    L29:
        if (r7 == (-2)) goto L32;
        r0 = r7;
        goto L32
    L10:
        if (r22 != (-2)) goto L12;
        r22 = getAnchorView().getWidth();
        goto L12
    }
}
