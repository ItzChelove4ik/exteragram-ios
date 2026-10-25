package org.telegram.ui.Components.Premium.boosts.cells;

import android.content.Context;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;

/* loaded from: classes7.dex */
public class SwitcherCell extends TextCheckCell {
    public static int TYPE_ADDITION_PRIZE = 1;
    public static int TYPE_WINNERS;
    private int type;

    static {
    }

    public SwitcherCell(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
    }

    public int getType() {
        return this.type;
    }

    public void setData(CharSequence r1, boolean r2, boolean r3, int r4) {
        this.type = r4;
        setTextAndCheck(r1, r2, r3);
    }
}
