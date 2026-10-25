package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
class AlertController {
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View.OnClickListener mButtonHandler;
    private final int mButtonIconDimen;
    Button mButtonNegative;
    private Drawable mButtonNegativeIcon;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    private Drawable mButtonNeutralIcon;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    private Drawable mButtonPositiveIcon;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mShowTitle;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    private final Window mWindow;

    /* renamed from: androidx.appcompat.app.AlertController$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ AlertController this$0;

        public AnonymousClass1(AlertController r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r3) {
            AlertController r0 = this.this$0;
            if (r3 != r0.mButtonPositive) goto L8;
            Message r1 = r0.mButtonPositiveMessage;
            if (r1 == null) goto L8;
            Message r32 = Message.obtain(r1);
        L18:
            if (r32 == null) goto L20;
            r32.sendToTarget();
        L20:
            AlertController r2 = this.this$0;
            r2.mHandler.obtainMessage(1, r2.mDialog).sendToTarget();
            return;
        L8:
            if (r3 != r0.mButtonNegative) goto L13;
            Message r12 = r0.mButtonNegativeMessage;
            if (r12 == null) goto L13;
            r32 = Message.obtain(r12);
        L13:
            if (r3 != r0.mButtonNeutral) goto L17;
            Message r33 = r0.mButtonNeutralMessage;
            if (r33 == null) goto L17;
            r32 = Message.obtain(r33);
        L17:
            r32 = null;
            goto L18
        }
    }

    public static class AlertParams {
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId;
        public int mIconId;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public Drawable mNegativeButtonIcon;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public Drawable mNeutralButtonIcon;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public Drawable mPositiveButtonIcon;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified;
        public int mViewSpacingTop;

        /* renamed from: androidx.appcompat.app.AlertController$AlertParams$1, reason: invalid class name */
        public class AnonymousClass1 extends ArrayAdapter<CharSequence> {
            final /* synthetic */ AlertParams this$0;
            final /* synthetic */ RecycleListView val$listView;

            public AnonymousClass1(AlertParams r1, Context r2, int r3, int r4, CharSequence[] r5, RecycleListView r6) {
                this.this$0 = r1;
                this.val$listView = r6;
                super(r2, r3, r4, r5);
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int r1, View r2, ViewGroup r3) {
                View r22 = super.getView(r1, r2, r3);
                boolean[] r32 = this.this$0.mCheckedItems;
                if (r32 != null) goto L5;
            L7:
                return r22;
            L5:
                if (r32[r1] == false) goto L7;
                this.val$listView.setItemChecked(r1, true);
                goto L7
            }
        }

        /* renamed from: androidx.appcompat.app.AlertController$AlertParams$2, reason: invalid class name */
        public class AnonymousClass2 extends CursorAdapter {
            private final int mIsCheckedIndex;
            private final int mLabelIndex;
            final /* synthetic */ AlertParams this$0;
            final /* synthetic */ AlertController val$dialog;
            final /* synthetic */ RecycleListView val$listView;

            public AnonymousClass2(AlertParams r1, Context r2, Cursor r3, boolean r4, RecycleListView r5, AlertController r6) {
                this.this$0 = r1;
                this.val$listView = r5;
                this.val$dialog = r6;
                super(r2, r3, r4);
                Cursor r22 = getCursor();
                this.mLabelIndex = r22.getColumnIndexOrThrow(r1.mLabelColumn);
                this.mIsCheckedIndex = r22.getColumnIndexOrThrow(r1.mIsCheckedColumn);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View r1, Context r2, Cursor r3) {
                ((CheckedTextView) r1.findViewById(R.id.text1)).setText(r3.getString(this.mLabelIndex));
                RecycleListView r12 = this.val$listView;
                int r22 = r3.getPosition();
                int r0 = r3.getInt(this.mIsCheckedIndex);
                boolean r32 = true;
                if (r0 == 1) goto L6;
                r32 = false;
            L6:
                r12.setItemChecked(r22, r32);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context r1, Cursor r2, ViewGroup r3) {
                return this.this$0.mInflater.inflate(this.val$dialog.mMultiChoiceItemLayout, r3, false);
            }
        }

        /* renamed from: androidx.appcompat.app.AlertController$AlertParams$3, reason: invalid class name */
        public class AnonymousClass3 implements AdapterView.OnItemClickListener {
            final /* synthetic */ AlertParams this$0;
            final /* synthetic */ AlertController val$dialog;

            public AnonymousClass3(AlertParams r1, AlertController r2) {
                this.this$0 = r1;
                this.val$dialog = r2;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> r1, View r2, int r3, long r4) {
                this.this$0.mOnClickListener.onClick(this.val$dialog.mDialog, r3);
                if (this.this$0.mIsSingleChoice == true) goto L6;
                this.val$dialog.mDialog.dismiss();
                return;
            }
        }

        /* renamed from: androidx.appcompat.app.AlertController$AlertParams$4, reason: invalid class name */
        public class AnonymousClass4 implements AdapterView.OnItemClickListener {
            final /* synthetic */ AlertParams this$0;
            final /* synthetic */ AlertController val$dialog;
            final /* synthetic */ RecycleListView val$listView;

            public AnonymousClass4(AlertParams r1, RecycleListView r2, AlertController r3) {
                this.this$0 = r1;
                this.val$listView = r2;
                this.val$dialog = r3;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> r1, View r2, int r3, long r4) {
                boolean[] r12 = this.this$0.mCheckedItems;
                if (r12 == null) goto L5;
                r12[r3] = this.val$listView.isItemChecked(r3);
            L5:
                this.this$0.mOnCheckboxClickListener.onClick(this.val$dialog.mDialog, r3, this.val$listView.isItemChecked(r3));
            }
        }

        public AlertParams(Context r2) {
            this.mIconId = 0;
            this.mIconAttrId = 0;
            this.mViewSpacingSpecified = false;
            this.mCheckedItem = -1;
            this.mRecycleOnMeasure = true;
            this.mContext = r2;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater) r2.getSystemService("layout_inflater");
        }

        private void createListView(AlertController r15) {
            RecycleListView r6 = (RecycleListView) this.mInflater.inflate(r15.mListLayout, null);
            if (this.mIsMultiChoice == true) goto L5;
            AlertParams r2 = this;
            AlertController r7 = r15;
            if (r2.mIsSingleChoice == false) goto L12;
            int r14 = r7.mSingleChoiceItemLayout;
        L11:
            int r10 = r14;
            if (r2.mCursor == null) goto L16;
            ListAdapter r1 = new SimpleCursorAdapter(r2.mContext, r10, r2.mCursor, new String[]{r2.mLabelColumn}, new int[]{R.id.text1});
        L20:
            r7.mAdapter = r1;
            r7.mCheckedItem = r2.mCheckedItem;
            if (r2.mOnClickListener == null) goto L24;
            r6.setOnItemClickListener(new AnonymousClass3(r2, r7));
        L26:
            AdapterView.OnItemSelectedListener r142 = r2.mOnItemSelectedListener;
            if (r142 == null) goto L30;
            r6.setOnItemSelectedListener(r142);
        L30:
            if (r2.mIsSingleChoice == false) goto L33;
            r6.setChoiceMode(1);
        L35:
            r7.mListView = r6;
            return;
        L33:
            if (r2.mIsMultiChoice == false) goto L35;
            r6.setChoiceMode(2);
            goto L35
        L24:
            if (r2.mOnCheckboxClickListener == null) goto L26;
            r6.setOnItemClickListener(new AnonymousClass4(r2, r6, r7));
            goto L26
        L16:
            r1 = r2.mAdapter;
            if (r1 != null) goto L20;
            r1 = new CheckedItemAdapter(r2.mContext, r10, R.id.text1, r2.mItems);
            goto L20
        L12:
            r14 = r7.mListItemLayout;
            goto L11
        L5:
            if (this.mCursor != null) goto L7;
            r2 = this;
            r1 = new AnonymousClass1(r2, this.mContext, r15.mMultiChoiceItemLayout, R.id.text1, this.mItems, r6);
            r6 = r6;
            r7 = r15;
            goto L20
        L7:
            r2 = this;
            r7 = r15;
            r1 = new AnonymousClass2(r2, r2.mContext, r2.mCursor, false, r6, r7);
            goto L20
        }

        public void apply(AlertController r14) {
            View r0 = this.mCustomTitleView;
            if (r0 == null) goto L5;
            r14.setCustomTitle(r0);
        L17:
            CharSequence r02 = this.mMessage;
            if (r02 == null) goto L20;
            r14.setMessage(r02);
        L20:
            CharSequence r3 = this.mPositiveButtonText;
            if (r3 == null) goto L23;
        L26:
            r14.setButton(-1, r3, this.mPositiveButtonListener, null, this.mPositiveButtonIcon);
            AlertController r7 = r14;
        L27:
            CharSequence r9 = this.mNegativeButtonText;
            if (r9 == null) goto L30;
        L31:
            r7.setButton(-2, r9, this.mNegativeButtonListener, null, this.mNegativeButtonIcon);
        L32:
            CharSequence r92 = this.mNeutralButtonText;
            if (r92 == null) goto L35;
        L36:
            r7.setButton(-3, r92, this.mNeutralButtonListener, null, this.mNeutralButtonIcon);
        L38:
            if (this.mItems == null) goto L40;
        L43:
            createListView(r7);
        L44:
            View r8 = this.mView;
            if (r8 != null) goto L47;
            int r13 = this.mViewLayoutResId;
            if (r13 == 0) goto L56;
            r7.setView(r13);
            return;
        L56:
            return;
        L47:
            if (this.mViewSpacingSpecified == false) goto L50;
            r7.setView(r8, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            return;
        L50:
            r7.setView(r8);
            return;
        L40:
            if (this.mCursor != null) goto L43;
            if (this.mAdapter == null) goto L44;
        L35:
            if (this.mNeutralButtonIcon == null) goto L38;
        L30:
            if (this.mNegativeButtonIcon == null) goto L32;
        L23:
            if (this.mPositiveButtonIcon != null) goto L26;
            r7 = r14;
            goto L27
        L5:
            CharSequence r03 = this.mTitle;
            if (r03 == null) goto L8;
            r14.setTitle(r03);
        L8:
            Drawable r04 = this.mIcon;
            if (r04 == null) goto L11;
            r14.setIcon(r04);
        L11:
            int r05 = this.mIconId;
            if (r05 == 0) goto L14;
            r14.setIcon(r05);
        L14:
            int r06 = this.mIconAttrId;
            if (r06 == 0) goto L17;
            r14.setIcon(r14.getIconAttributeResId(r06));
            goto L17
        }
    }

    public static final class ButtonHandler extends Handler {
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface r2) {
            this.mDialog = new WeakReference(r2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message r3) {
            int r0 = r3.what;
            if (r0 != (-3)) goto L5;
        L13:
            ((DialogInterface.OnClickListener) r3.obj).onClick(this.mDialog.get(), r3.what);
            return;
        L5:
            if (r0 == (-2)) goto L13;
            if (r0 == (-1)) goto L13;
            if (r0 == 1) goto L11;
            return;
        L11:
            ((DialogInterface) r3.obj).dismiss();
        }
    }

    public static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context r1, int r2, int r3, CharSequence[] r4) {
            super(r1, r2, r3, r4);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int r1) {
            return r1;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public static class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        public RecycleListView(Context r2) {
            this(r2, null);
        }

        public RecycleListView(Context r2, AttributeSet r3) {
            super(r2, r3);
            TypedArray r22 = r2.obtainStyledAttributes(r3, R$styleable.RecycleListView);
            this.mPaddingBottomNoButtons = r22.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.mPaddingTopNoTitle = r22.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean r3, boolean r4) {
            if (r4 == false) goto L6;
            if (r3 == false) goto L6;
            return;
        L6:
            int r0 = getPaddingLeft();
            if (r3 == false) goto L9;
            int r32 = getPaddingTop();
        L10:
            int r1 = getPaddingRight();
            if (r4 == false) goto L13;
            int r42 = getPaddingBottom();
        L14:
            setPadding(r0, r32, r1, r42);
            return;
        L13:
            r42 = this.mPaddingBottomNoButtons;
            goto L14
        L9:
            r32 = this.mPaddingTopNoTitle;
            goto L10
        }
    }

    public AlertController(Context r4, AppCompatDialog r5, Window r6) {
        this.mViewSpacingSpecified = false;
        this.mIconId = 0;
        this.mCheckedItem = -1;
        this.mButtonPanelLayoutHint = 0;
        this.mButtonHandler = new AnonymousClass1(this);
        this.mContext = r4;
        this.mDialog = r5;
        this.mWindow = r6;
        this.mHandler = new ButtonHandler(r5);
        TypedArray r42 = r4.obtainStyledAttributes(null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = r42.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = r42.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = r42.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = r42.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = r42.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = r42.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = r42.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.mButtonIconDimen = r42.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        r42.recycle();
        r5.supportRequestWindowFeature(1);
    }

    public static boolean canTextInput(View r4) {
        if (r4.onCheckIsTextEditor() == false) goto L6;
        return true;
    L6:
        if ((r4 instanceof ViewGroup) == true) goto L8;
        return false;
    L8:
        ViewGroup r42 = (ViewGroup) r4;
        int r0 = r42.getChildCount();
    L9:
        if (r0 <= 0) goto L13;
        r0 = r0 - 1;
        if (canTextInput(r42.getChildAt(r0)) == false) goto L9;
        return true;
    L13:
        return false;
    }

    private void centerButton(Button r2) {
        LinearLayout.LayoutParams r1 = (LinearLayout.LayoutParams) r2.getLayoutParams();
        r1.gravity = 1;
        r1.weight = 0.5f;
        r2.setLayoutParams(r1);
    }

    private ViewGroup resolvePanel(View r2, View r3) {
        if (r2 == null) goto L4;
        if (r3 == null) goto L13;
        ViewParent r1 = r3.getParent();
        if ((r1 instanceof ViewGroup) == false) goto L13;
        ((ViewGroup) r1).removeView(r3);
    L13:
        if ((r2 instanceof ViewStub) == false) goto L16;
        r2 = ((ViewStub) r2).inflate();
    L16:
        return (ViewGroup) r2;
    L4:
        if ((r3 instanceof ViewStub) == false) goto L7;
        r3 = ((ViewStub) r3).inflate();
    L7:
        return (ViewGroup) r3;
    }

    private int selectContentView() {
        int r0 = this.mButtonPanelSideLayout;
        if (r0 != 0) goto L7;
        return this.mAlertDialogLayout;
    L7:
        if (this.mButtonPanelLayoutHint != 1) goto L10;
        return r0;
    L10:
        return this.mAlertDialogLayout;
    }

    private void setScrollIndicators(ViewGroup r3, View r4, int r5, int r6) {
        View r0 = this.mWindow.findViewById(R$id.scrollIndicatorUp);
        View r2 = this.mWindow.findViewById(R$id.scrollIndicatorDown);
        ViewCompat.setScrollIndicators(r4, r5, r6);
        if (r0 == null) goto L5;
        r3.removeView(r0);
    L5:
        if (r2 == null) goto L8;
        r3.removeView(r2);
        return;
    }

    private void setupButtons(ViewGroup r8) {
        Button r0 = (Button) r8.findViewById(R.id.button1);
        this.mButtonPositive = r0;
        r0.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonPositiveText) == true) goto L5;
    L7:
        this.mButtonPositive.setText(this.mButtonPositiveText);
        Drawable r02 = this.mButtonPositiveIcon;
        if (r02 == null) goto L10;
        int r5 = this.mButtonIconDimen;
        r02.setBounds(0, 0, r5, r5);
        this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, null, null, null);
    L10:
        this.mButtonPositive.setVisibility(0);
        int r03 = 1;
    L11:
        Button r52 = (Button) r8.findViewById(R.id.button2);
        this.mButtonNegative = r52;
        r52.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNegativeText) == true) goto L14;
    L16:
        this.mButtonNegative.setText(this.mButtonNegativeText);
        Drawable r53 = this.mButtonNegativeIcon;
        if (r53 == null) goto L19;
        int r6 = this.mButtonIconDimen;
        r53.setBounds(0, 0, r6, r6);
        this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, null, null, null);
    L19:
        this.mButtonNegative.setVisibility(0);
        r03 = r03 | 2;
    L20:
        Button r54 = (Button) r8.findViewById(R.id.button3);
        this.mButtonNeutral = r54;
        r54.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNeutralText) == true) goto L23;
    L25:
        this.mButtonNeutral.setText(this.mButtonNeutralText);
        Drawable r55 = this.mButtonNeutralIcon;
        if (r55 == null) goto L28;
        int r62 = this.mButtonIconDimen;
        r55.setBounds(0, 0, r62, r62);
        this.mButtonNeutral.setCompoundDrawables(this.mButtonNeutralIcon, null, null, null);
    L28:
        this.mButtonNeutral.setVisibility(0);
        r03 = r03 | 4;
    L30:
        if (shouldCenterSingleButton(this.mContext) == false) goto L39;
        if (r03 != 1) goto L34;
        centerButton(this.mButtonPositive);
        goto L39
    L34:
        if (r03 != 2) goto L37;
        centerButton(this.mButtonNegative);
        goto L39
    L37:
        if (r03 != 4) goto L39;
        centerButton(this.mButtonNeutral);
    L39:
        if (r03 == 0) goto L41;
        return;
    L41:
        r8.setVisibility(8);
        return;
    L23:
        if (this.mButtonNeutralIcon != null) goto L25;
        this.mButtonNeutral.setVisibility(8);
        goto L30
    L14:
        if (this.mButtonNegativeIcon != null) goto L16;
        this.mButtonNegative.setVisibility(8);
        goto L20
    L5:
        if (this.mButtonPositiveIcon != null) goto L7;
        this.mButtonPositive.setVisibility(8);
        r03 = 0;
        goto L11
    }

    private void setupContent(ViewGroup r4) {
        NestedScrollView r0 = (NestedScrollView) this.mWindow.findViewById(R$id.scrollView);
        this.mScrollView = r0;
        r0.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        TextView r02 = (TextView) r4.findViewById(R.id.message);
        this.mMessageView = r02;
        if (r02 != null) goto L5;
        return;
    L5:
        CharSequence r1 = this.mMessage;
        if (r1 == null) goto L9;
        r02.setText(r1);
        return;
    L9:
        r02.setVisibility(8);
        this.mScrollView.removeView(this.mMessageView);
        if (this.mListView == null) goto L13;
        ViewGroup r42 = (ViewGroup) this.mScrollView.getParent();
        int r03 = r42.indexOfChild(this.mScrollView);
        r42.removeViewAt(r03);
        r42.addView(this.mListView, r03, new ViewGroup.LayoutParams(-1, -1));
        return;
    L13:
        r4.setVisibility(8);
    }

    private void setupCustomContent(ViewGroup r6) {
        View r0 = this.mView;
        boolean r1 = false;
        if (r0 == null) goto L6;
    L9:
        if (r0 == null) goto L11;
        r1 = true;
    L11:
        if (r1 == true) goto L13;
    L14:
        this.mWindow.setFlags(131072, 131072);
    L15:
        if (r1 == false) goto L23;
        FrameLayout r12 = (FrameLayout) this.mWindow.findViewById(R$id.custom);
        r12.addView(r0, new ViewGroup.LayoutParams(-1, -1));
        if (this.mViewSpacingSpecified == false) goto L20;
        r12.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
    L20:
        if (this.mListView == null) goto L25;
        ((LinearLayout.LayoutParams) ((LinearLayoutCompat.LayoutParams) r6.getLayoutParams())).weight = 0.0f;
        return;
    L25:
        return;
    L23:
        r6.setVisibility(8);
        return;
    L13:
        if (canTextInput(r0) == true) goto L15;
    L6:
        if (this.mViewLayoutResId == 0) goto L8;
        r0 = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, r6, false);
        goto L9
    L8:
        r0 = null;
        goto L9
    }

    private void setupTitle(ViewGroup r6) {
        if (this.mCustomTitleView == null) goto L6;
        ViewGroup.LayoutParams r0 = new ViewGroup.LayoutParams(-1, -2);
        r6.addView(this.mCustomTitleView, 0, r0);
        this.mWindow.findViewById(R$id.title_template).setVisibility(8);
        return;
    L6:
        this.mIconView = (ImageView) this.mWindow.findViewById(R.id.icon);
        if (TextUtils.isEmpty(this.mTitle) == false) goto L9;
    L20:
        this.mWindow.findViewById(R$id.title_template).setVisibility(8);
        this.mIconView.setVisibility(8);
        r6.setVisibility(8);
        return;
    L9:
        if (this.mShowTitle == false) goto L20;
        TextView r62 = (TextView) this.mWindow.findViewById(R$id.alertTitle);
        this.mTitleView = r62;
        r62.setText(this.mTitle);
        int r63 = this.mIconId;
        if (r63 == 0) goto L14;
        this.mIconView.setImageResource(r63);
        return;
    L14:
        Drawable r64 = this.mIcon;
        if (r64 == null) goto L18;
        this.mIconView.setImageDrawable(r64);
        return;
    L18:
        this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
        this.mIconView.setVisibility(8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setupView() {
        View r0 = this.mWindow.findViewById(R$id.parentPanel);
        View r1 = r0.findViewById(R$id.topPanel);
        View r2 = r0.findViewById(R$id.contentPanel);
        View r3 = r0.findViewById(R$id.buttonPanel);
        ViewGroup r02 = (ViewGroup) r0.findViewById(R$id.customPanel);
        setupCustomContent(r02);
        View r4 = r02.findViewById(R$id.topPanel);
        View r5 = r02.findViewById(R$id.contentPanel);
        View r6 = r02.findViewById(R$id.buttonPanel);
        ViewGroup r12 = resolvePanel(r4, r1);
        ViewGroup r22 = resolvePanel(r5, r2);
        ViewGroup r32 = resolvePanel(r6, r3);
        setupContent(r22);
        setupButtons(r32);
        setupTitle(r12);
        int r52 = 0;
        if (r02.getVisibility() == 8) goto L5;
        boolean r03 = true;
    L6:
        if (r12 != null) goto L8;
    L10:
        boolean r7 = 0;
    L11:
        if (r32 != null) goto L13;
    L15:
        boolean r33 = false;
    L16:
        if (r33 == true) goto L21;
        if (r22 == null) goto L21;
        View r62 = r22.findViewById(R$id.textSpacerNoButtons);
        if (r62 == null) goto L21;
        r62.setVisibility(0);
    L21:
        if (r7 == 0) goto L34;
        NestedScrollView r63 = this.mScrollView;
        if (r63 == null) goto L26;
        r63.setClipToPadding(true);
    L26:
        if (this.mMessage == null) goto L28;
    L31:
        View r13 = r12.findViewById(R$id.titleDividerNoCustom);
    L32:
        if (r13 == null) goto L38;
        r13.setVisibility(0);
    L38:
        ListView r14 = this.mListView;
        if ((r14 instanceof RecycleListView) == false) goto L41;
        ((RecycleListView) r14).setHasDecor(r7, r33);
    L41:
        if (r03 == true) goto L50;
        View r04 = this.mListView;
        if (r04 != null) goto L46;
        r04 = this.mScrollView;
    L46:
        if (r04 == null) goto L50;
        if (r33 == false) goto L49;
        r52 = 2;
    L49:
        setScrollIndicators(r22, r04, r7 | r52, 3);
    L50:
        ListView r05 = this.mListView;
        if (r05 == null) goto L58;
        ListAdapter r15 = this.mAdapter;
        if (r15 == null) goto L59;
        r05.setAdapter(r15);
        int r8 = this.mCheckedItem;
        if (r8 <= (-1)) goto L60;
        r05.setItemChecked(r8, true);
        r05.setSelection(r8);
        return;
    L60:
        return;
    L59:
        return;
    L58:
        return;
    L28:
        if (this.mListView != null) goto L31;
        r13 = null;
        goto L32
    L34:
        if (r22 == null) goto L38;
        View r16 = r22.findViewById(R$id.textSpacerNoTitle);
        if (r16 == null) goto L38;
        r16.setVisibility(0);
        goto L38
    L13:
        if (r32.getVisibility() == 8) goto L15;
        r33 = true;
        goto L16
    L8:
        if (r12.getVisibility() == 8) goto L10;
        r7 = 1;
        goto L11
    L5:
        r03 = false;
        goto L6
    }

    private static boolean shouldCenterSingleButton(Context r3) {
        TypedValue r0 = new TypedValue();
        r3.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, r0, true);
        if (r0.data == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public Button getButton(int r2) {
        if (r2 == (-3)) goto L15;
        if (r2 == (-2)) goto L13;
        if (r2 == (-1)) goto L11;
        return null;
    L11:
        return this.mButtonPositive;
    L13:
        return this.mButtonNegative;
    L15:
        return this.mButtonNeutral;
    }

    public int getIconAttributeResId(int r3) {
        TypedValue r0 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(r3, r0, true);
        return r0.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void installContent() {
        int r0 = selectContentView();
        this.mDialog.setContentView(r0);
        setupView();
    }

    public boolean onKeyDown(int r1, KeyEvent r2) {
        NestedScrollView r0 = this.mScrollView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.executeKeyEvent(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean onKeyUp(int r1, KeyEvent r2) {
        NestedScrollView r0 = this.mScrollView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.executeKeyEvent(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void setButton(int r1, CharSequence r2, DialogInterface.OnClickListener r3, Message r4, Drawable r5) {
        if (r4 != null) goto L6;
        if (r3 == null) goto L6;
        r4 = this.mHandler.obtainMessage(r1, r3);
    L6:
        if (r1 != (-3)) goto L8;
        this.mButtonNeutralText = r2;
        this.mButtonNeutralMessage = r4;
        this.mButtonNeutralIcon = r5;
        return;
    L8:
        if (r1 != (-2)) goto L10;
        this.mButtonNegativeText = r2;
        this.mButtonNegativeMessage = r4;
        this.mButtonNegativeIcon = r5;
        return;
    L10:
        if (r1 != (-1)) goto L13;
        this.mButtonPositiveText = r2;
        this.mButtonPositiveMessage = r4;
        this.mButtonPositiveIcon = r5;
        return;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Button does not exist");
    }

    public void setButtonPanelLayoutHint(int r1) {
        this.mButtonPanelLayoutHint = r1;
    }

    public void setCustomTitle(View r1) {
        this.mCustomTitleView = r1;
    }

    public void setIcon(int r2) {
        this.mIcon = null;
        this.mIconId = r2;
        ImageView r0 = this.mIconView;
        if (r0 == null) goto L9;
        if (r2 == 0) goto L7;
        r0.setVisibility(0);
        this.mIconView.setImageResource(this.mIconId);
        return;
    L7:
        r0.setVisibility(8);
        return;
    }

    public void setIcon(Drawable r3) {
        this.mIcon = r3;
        this.mIconId = 0;
        ImageView r1 = this.mIconView;
        if (r1 == null) goto L9;
        if (r3 == null) goto L7;
        r1.setVisibility(0);
        this.mIconView.setImageDrawable(r3);
        return;
    L7:
        r1.setVisibility(8);
        return;
    }

    public void setMessage(CharSequence r1) {
        this.mMessage = r1;
        TextView r0 = this.mMessageView;
        if (r0 == null) goto L6;
        r0.setText(r1);
        return;
    }

    public void setTitle(CharSequence r1) {
        this.mTitle = r1;
        TextView r0 = this.mTitleView;
        if (r0 == null) goto L6;
        r0.setText(r1);
        return;
    }

    public void setView(int r2) {
        this.mView = null;
        this.mViewLayoutResId = r2;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View r1) {
        this.mView = r1;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View r1, int r2, int r3, int r4, int r5) {
        this.mView = r1;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = r2;
        this.mViewSpacingTop = r3;
        this.mViewSpacingRight = r4;
        this.mViewSpacingBottom = r5;
    }
}
