package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;

/* loaded from: classes5.dex */
public class CheckableImageButton extends AppCompatImageButton implements Checkable {
    private static final int[] DRAWABLE_STATE_CHECKED = null;
    private boolean checkable;
    private boolean checked;
    private OnFocusableChangedListener onFocusableChangedListener;
    private boolean pressable;

    /* renamed from: com.google.android.material.internal.CheckableImageButton$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityDelegateCompat {
        final /* synthetic */ CheckableImageButton this$0;

        public AnonymousClass1(CheckableImageButton r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View r1, AccessibilityEvent r2) {
            super.onInitializeAccessibilityEvent(r1, r2);
            r2.setChecked(this.this$0.isChecked());
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setCheckable(this.this$0.isCheckable());
            r2.setChecked(this.this$0.isChecked());
        }
    }

    public interface OnFocusableChangedListener {
        void onFocusableChanged(View r1, boolean r2);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        boolean checked;

        /* renamed from: com.google.android.material.internal.CheckableImageButton$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return new SavedState(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return createFromParcel(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            readFromParcel(r1);
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        private void readFromParcel(Parcel r2) {
            boolean r0 = true;
            if (r2.readInt() == 1) goto L6;
            r0 = false;
        L6:
            this.checked = r0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeInt(this.checked ? 1 : 0);
        }
    }

    static {
        DRAWABLE_STATE_CHECKED = new int[]{R.attr.state_checked};
    }

    public CheckableImageButton(Context r2) {
        this(r2, null);
    }

    public CheckableImageButton(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.imageButtonStyle);
    }

    public CheckableImageButton(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.checkable = true;
        this.pressable = true;
        ViewCompat.setAccessibilityDelegate(this, new AnonymousClass1(this));
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    public boolean isPressable() {
        return this.pressable;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int r3) {
        if (this.checked == false) goto L7;
        int[] r0 = DRAWABLE_STATE_CHECKED;
        return View.mergeDrawableStates(super.onCreateDrawableState(r3 + r0.length), r0);
    L7:
        return super.onCreateDrawableState(r3);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        this.onFocusableChangedListener = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        setChecked(r22.checked);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        r1.checked = this.checked;
        return r1;
    }

    public void setCheckable(boolean r2) {
        if (this.checkable == r2) goto L6;
        this.checkable = r2;
        sendAccessibilityEvent(0);
        return;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean r2) {
        if (this.checkable == true) goto L5;
        return;
    L5:
        if (this.checked == r2) goto L9;
        this.checked = r2;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
        return;
    }

    @Override // android.view.View
    public void setFocusable(boolean r2) {
        boolean r0 = isFocusable();
        super.setFocusable(r2);
        if (r0 == r2) goto L8;
        OnFocusableChangedListener r02 = this.onFocusableChangedListener;
        if (r02 == null) goto L9;
        r02.onFocusableChanged(this, r2);
        return;
    L9:
        return;
    }

    public void setOnFocusableChangedListener(OnFocusableChangedListener r1) {
        this.onFocusableChangedListener = r1;
    }

    public void setPressable(boolean r1) {
        this.pressable = r1;
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        if (this.pressable == false) goto L6;
        super.setPressed(r2);
        return;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }
}
