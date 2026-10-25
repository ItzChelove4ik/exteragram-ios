package org.telegram.messenger.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import org.telegram.ui.ActionBar.ActionBarMenuItem;

/* loaded from: classes.dex */
public class SearchTextWatcher implements TextWatcher {
    private boolean doNotCloseAfterFieldEmpty;
    private final EditText editText;
    public final ActionBarMenuItem.ActionBarMenuItemSearchListener listener;
    private boolean searchIsExpanded;
    private String searchQuery;
    private final boolean toggleByFocus;

    public SearchTextWatcher(EditText r2, ActionBarMenuItem.ActionBarMenuItemSearchListener r3) {
        this(r2, r3, false);
    }

    public SearchTextWatcher(EditText r1, ActionBarMenuItem.ActionBarMenuItemSearchListener r2, boolean r3) {
        this.listener = r2;
        this.editText = r1;
        this.toggleByFocus = r3;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable r4) {
        String r42 = r4.toString();
        boolean r0 = TextUtils.isEmpty(this.searchQuery);
        boolean r1 = TextUtils.isEmpty(r42);
        if (r0 == false) goto L6;
        if (r1 == true) goto L6;
        toggleSearch(true);
    L6:
        this.searchQuery = r42;
        this.listener.onTextChanged(this.editText);
        if (r0 == true) goto L13;
        if (r1 == true) goto L10;
        return;
    L10:
        if (this.doNotCloseAfterFieldEmpty == true) goto L15;
        toggleSearch(false);
        return;
    L15:
        return;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
    }

    public void setDoNotCloseAfterFieldEmpty() {
        this.doNotCloseAfterFieldEmpty = true;
    }

    public boolean toggleSearch(boolean r3) {
        if (this.searchIsExpanded != r3) goto L5;
        return false;
    L5:
        this.listener.onPreToggleSearch();
        if (this.listener.canToggleSearch() == true) goto L8;
        return false;
    L8:
        ActionBarMenuItem.ActionBarMenuItemSearchListener r0 = this.listener;
        if (r3 == false) goto L11;
        r0.onSearchExpand();
    L12:
        this.searchIsExpanded = r3;
        return true;
    L11:
        r0.onSearchCollapse();
        goto L12
    }
}
