package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.AlertController;

/* loaded from: classes3.dex */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    public static class Builder {
        private final AlertController.AlertParams P;
        private final int mTheme;

        public Builder(Context r2) {
            this(r2, AlertDialog.resolveDialogTheme(r2, 0));
        }

        public Builder(Context r4, int r5) {
            this.P = new AlertController.AlertParams(new ContextThemeWrapper(r4, AlertDialog.resolveDialogTheme(r4, r5)));
            this.mTheme = r5;
        }

        public AlertDialog create() {
            AlertDialog r0 = new AlertDialog(this.P.mContext, this.mTheme);
            this.P.apply(r0.mAlert);
            r0.setCancelable(this.P.mCancelable);
            if (this.P.mCancelable == false) goto L5;
            r0.setCanceledOnTouchOutside(true);
        L5:
            r0.setOnCancelListener(this.P.mOnCancelListener);
            r0.setOnDismissListener(this.P.mOnDismissListener);
            DialogInterface.OnKeyListener r3 = this.P.mOnKeyListener;
            if (r3 == null) goto L8;
            r0.setOnKeyListener(r3);
        L8:
            return r0;
        }

        public Context getContext() {
            return this.P.mContext;
        }

        public Builder setAdapter(ListAdapter r2, DialogInterface.OnClickListener r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mAdapter = r2;
            r0.mOnClickListener = r3;
            return this;
        }

        public Builder setCancelable(boolean r2) {
            this.P.mCancelable = r2;
            return this;
        }

        public Builder setCursor(Cursor r2, DialogInterface.OnClickListener r3, String r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mCursor = r2;
            r0.mLabelColumn = r4;
            r0.mOnClickListener = r3;
            return this;
        }

        public Builder setCustomTitle(View r2) {
            this.P.mCustomTitleView = r2;
            return this;
        }

        public Builder setIcon(int r2) {
            this.P.mIconId = r2;
            return this;
        }

        public Builder setIcon(Drawable r2) {
            this.P.mIcon = r2;
            return this;
        }

        public Builder setIconAttribute(int r4) {
            TypedValue r0 = new TypedValue();
            this.P.mContext.getTheme().resolveAttribute(r4, r0, true);
            AlertController.AlertParams r42 = this.P;
            r42.mIconId = r0.resourceId;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean r2) {
            this.P.mForceInverseBackground = r2;
            return this;
        }

        public Builder setItems(int r3, DialogInterface.OnClickListener r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mItems = r0.mContext.getResources().getTextArray(r3);
            this.P.mOnClickListener = r4;
            return this;
        }

        public Builder setItems(CharSequence[] r2, DialogInterface.OnClickListener r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mItems = r2;
            r0.mOnClickListener = r3;
            return this;
        }

        public Builder setMessage(int r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mMessage = r0.mContext.getText(r3);
            return this;
        }

        public Builder setMessage(CharSequence r2) {
            this.P.mMessage = r2;
            return this;
        }

        public Builder setMultiChoiceItems(int r3, boolean[] r4, DialogInterface.OnMultiChoiceClickListener r5) {
            AlertController.AlertParams r0 = this.P;
            r0.mItems = r0.mContext.getResources().getTextArray(r3);
            AlertController.AlertParams r32 = this.P;
            r32.mOnCheckboxClickListener = r5;
            r32.mCheckedItems = r4;
            r32.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor r2, String r3, String r4, DialogInterface.OnMultiChoiceClickListener r5) {
            AlertController.AlertParams r0 = this.P;
            r0.mCursor = r2;
            r0.mOnCheckboxClickListener = r5;
            r0.mIsCheckedColumn = r3;
            r0.mLabelColumn = r4;
            r0.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] r2, boolean[] r3, DialogInterface.OnMultiChoiceClickListener r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mItems = r2;
            r0.mOnCheckboxClickListener = r4;
            r0.mCheckedItems = r3;
            r0.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(int r3, DialogInterface.OnClickListener r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mNegativeButtonText = r0.mContext.getText(r3);
            this.P.mNegativeButtonListener = r4;
            return this;
        }

        public Builder setNegativeButton(CharSequence r2, DialogInterface.OnClickListener r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mNegativeButtonText = r2;
            r0.mNegativeButtonListener = r3;
            return this;
        }

        public Builder setNegativeButtonIcon(Drawable r2) {
            this.P.mNegativeButtonIcon = r2;
            return this;
        }

        public Builder setNeutralButton(int r3, DialogInterface.OnClickListener r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mNeutralButtonText = r0.mContext.getText(r3);
            this.P.mNeutralButtonListener = r4;
            return this;
        }

        public Builder setNeutralButton(CharSequence r2, DialogInterface.OnClickListener r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mNeutralButtonText = r2;
            r0.mNeutralButtonListener = r3;
            return this;
        }

        public Builder setNeutralButtonIcon(Drawable r2) {
            this.P.mNeutralButtonIcon = r2;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener r2) {
            this.P.mOnCancelListener = r2;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener r2) {
            this.P.mOnDismissListener = r2;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener r2) {
            this.P.mOnItemSelectedListener = r2;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener r2) {
            this.P.mOnKeyListener = r2;
            return this;
        }

        public Builder setPositiveButton(int r3, DialogInterface.OnClickListener r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mPositiveButtonText = r0.mContext.getText(r3);
            this.P.mPositiveButtonListener = r4;
            return this;
        }

        public Builder setPositiveButton(CharSequence r2, DialogInterface.OnClickListener r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mPositiveButtonText = r2;
            r0.mPositiveButtonListener = r3;
            return this;
        }

        public Builder setPositiveButtonIcon(Drawable r2) {
            this.P.mPositiveButtonIcon = r2;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean r2) {
            this.P.mRecycleOnMeasure = r2;
            return this;
        }

        public Builder setSingleChoiceItems(int r3, int r4, DialogInterface.OnClickListener r5) {
            AlertController.AlertParams r0 = this.P;
            r0.mItems = r0.mContext.getResources().getTextArray(r3);
            AlertController.AlertParams r32 = this.P;
            r32.mOnClickListener = r5;
            r32.mCheckedItem = r4;
            r32.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor r2, int r3, String r4, DialogInterface.OnClickListener r5) {
            AlertController.AlertParams r0 = this.P;
            r0.mCursor = r2;
            r0.mOnClickListener = r5;
            r0.mCheckedItem = r3;
            r0.mLabelColumn = r4;
            r0.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter r2, int r3, DialogInterface.OnClickListener r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mAdapter = r2;
            r0.mOnClickListener = r4;
            r0.mCheckedItem = r3;
            r0.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] r2, int r3, DialogInterface.OnClickListener r4) {
            AlertController.AlertParams r0 = this.P;
            r0.mItems = r2;
            r0.mOnClickListener = r4;
            r0.mCheckedItem = r3;
            r0.mIsSingleChoice = true;
            return this;
        }

        public Builder setTitle(int r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mTitle = r0.mContext.getText(r3);
            return this;
        }

        public Builder setTitle(CharSequence r2) {
            this.P.mTitle = r2;
            return this;
        }

        public Builder setView(int r3) {
            AlertController.AlertParams r0 = this.P;
            r0.mView = null;
            r0.mViewLayoutResId = r3;
            r0.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View r2) {
            AlertController.AlertParams r0 = this.P;
            r0.mView = r2;
            r0.mViewLayoutResId = 0;
            r0.mViewSpacingSpecified = false;
            return this;
        }

        @Deprecated
        public Builder setView(View r2, int r3, int r4, int r5, int r6) {
            AlertController.AlertParams r0 = this.P;
            r0.mView = r2;
            r0.mViewLayoutResId = 0;
            r0.mViewSpacingSpecified = true;
            r0.mViewSpacingLeft = r3;
            r0.mViewSpacingTop = r4;
            r0.mViewSpacingRight = r5;
            r0.mViewSpacingBottom = r6;
            return this;
        }

        public AlertDialog show() {
            AlertDialog r0 = create();
            r0.show();
            return r0;
        }
    }

    public AlertDialog(Context r2, int r3) {
        super(r2, resolveDialogTheme(r2, r3));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    public static int resolveDialogTheme(Context r2, int r3) {
        if (((r3 >>> 24) & 255) < 1) goto L5;
        return r3;
    L5:
        TypedValue r32 = new TypedValue();
        r2.getTheme().resolveAttribute(R$attr.alertDialogTheme, r32, true);
        return r32.resourceId;
    }

    public Button getButton(int r1) {
        return this.mAlert.getButton(r1);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        this.mAlert.installContent();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r2, KeyEvent r3) {
        if (this.mAlert.onKeyDown(r2, r3) == false) goto L7;
        return true;
    L7:
        return super.onKeyDown(r2, r3);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int r2, KeyEvent r3) {
        if (this.mAlert.onKeyUp(r2, r3) == false) goto L7;
        return true;
    L7:
        return super.onKeyUp(r2, r3);
    }

    public void setButton(int r7, CharSequence r8, DialogInterface.OnClickListener r9) {
        this.mAlert.setButton(r7, r8, r9, null, null);
    }

    public void setButton(int r7, CharSequence r8, Drawable r9, DialogInterface.OnClickListener r10) {
        this.mAlert.setButton(r7, r8, r10, null, r9);
    }

    public void setButton(int r7, CharSequence r8, Message r9) {
        this.mAlert.setButton(r7, r8, null, r9, null);
    }

    public void setButtonPanelLayoutHint(int r1) {
        this.mAlert.setButtonPanelLayoutHint(r1);
    }

    public void setCustomTitle(View r1) {
        this.mAlert.setCustomTitle(r1);
    }

    public void setIcon(int r1) {
        this.mAlert.setIcon(r1);
    }

    public void setIcon(Drawable r1) {
        this.mAlert.setIcon(r1);
    }

    public void setIconAttribute(int r4) {
        TypedValue r0 = new TypedValue();
        getContext().getTheme().resolveAttribute(r4, r0, true);
        this.mAlert.setIcon(r0.resourceId);
    }

    public void setMessage(CharSequence r1) {
        this.mAlert.setMessage(r1);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence r1) {
        super.setTitle(r1);
        this.mAlert.setTitle(r1);
    }

    public void setView(View r1) {
        this.mAlert.setView(r1);
    }

    public void setView(View r1, int r2, int r3, int r4, int r5) {
        this.mAlert.setView(r1, r2, r3, r4, r5);
    }
}
