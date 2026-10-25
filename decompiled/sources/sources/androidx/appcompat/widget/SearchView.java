package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.R$attr;
import androidx.appcompat.view.CollapsibleActionView;

/* loaded from: classes3.dex */
public abstract class SearchView extends LinearLayoutCompat implements CollapsibleActionView {

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private boolean mHasPendingShowSoftInputRequest;
        final Runnable mRunShowSoftInputIfNecessary;
        private int mThreshold;

        /* renamed from: androidx.appcompat.widget.SearchView$SearchAutoComplete$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ SearchAutoComplete this$0;

            public AnonymousClass1(SearchAutoComplete r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$0.showSoftInputIfNecessary();
            }
        }

        public SearchAutoComplete(Context r2) {
            this(r2, null);
        }

        public SearchAutoComplete(Context r2, AttributeSet r3) {
            this(r2, r3, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context r1, AttributeSet r2, int r3) {
            super(r1, r2, r3);
            this.mRunShowSoftInputIfNecessary = new AnonymousClass1(this);
            this.mThreshold = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration r3 = getResources().getConfiguration();
            int r0 = r3.screenWidthDp;
            int r1 = r3.screenHeightDp;
            if (r0 < 960) goto L11;
            if (r1 < 720) goto L11;
            if (r3.orientation != 2) goto L11;
            return 256;
        L11:
            if (r0 < 600) goto L13;
            return 192;
        L13:
            if (r0 >= 640) goto L15;
            return 160;
        L15:
            if (r1 >= 480) goto L22;
            return 160;
        L22:
            return 192;
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            if (this.mThreshold > 0) goto L5;
            return true;
        L5:
            if (super.enoughToFilter() == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo r2) {
            InputConnection r22 = super.onCreateInputConnection(r2);
            if (this.mHasPendingShowSoftInputRequest == false) goto L5;
            removeCallbacks(this.mRunShowSoftInputIfNecessary);
            post(this.mRunShowSoftInputIfNecessary);
        L5:
            return r22;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean r1, int r2, Rect r3) {
            super.onFocusChanged(r1, r2, r3);
            throw null;
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int r3, KeyEvent r4) {
            if (r3 != 4) goto L25;
            if (r4.getAction() != 0) goto L13;
            if (r4.getRepeatCount() != 0) goto L13;
            KeyEvent.DispatcherState r32 = getKeyDispatcherState();
            if (r32 == null) goto L11;
            r32.startTracking(r4, this);
        L11:
            return true;
        L13:
            if (r4.getAction() != 1) goto L25;
            KeyEvent.DispatcherState r0 = getKeyDispatcherState();
            if (r0 == null) goto L18;
            r0.handleUpEvent(r4);
        L18:
            if (r4.isTracking() == false) goto L25;
            if (r4.isCanceled() == true) goto L25;
            throw null;
        L25:
            return super.onKeyPreIme(r3, r4);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean r1) {
            super.onWindowFocusChanged(r1);
            if (r1 == true) goto L6;
            return;
        L6:
            throw null;
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence r1) {
        }

        public void setImeVisibility(boolean r3) {
            InputMethodManager r0 = (InputMethodManager) getContext().getSystemService("input_method");
            if (r3 == true) goto L7;
            this.mHasPendingShowSoftInputRequest = false;
            removeCallbacks(this.mRunShowSoftInputIfNecessary);
            r0.hideSoftInputFromWindow(getWindowToken(), 0);
            return;
        L7:
            if (r0.isActive(this) == false) goto L10;
            this.mHasPendingShowSoftInputRequest = false;
            removeCallbacks(this.mRunShowSoftInputIfNecessary);
            r0.showSoftInput(this, 0);
            return;
        L10:
            this.mHasPendingShowSoftInputRequest = true;
        }

        public void setSearchView(SearchView r1) {
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int r1) {
            super.setThreshold(r1);
            this.mThreshold = r1;
        }

        public void showSoftInputIfNecessary() {
            if (this.mHasPendingShowSoftInputRequest == false) goto L6;
            ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
            this.mHasPendingShowSoftInputRequest = false;
            return;
        }
    }
}
