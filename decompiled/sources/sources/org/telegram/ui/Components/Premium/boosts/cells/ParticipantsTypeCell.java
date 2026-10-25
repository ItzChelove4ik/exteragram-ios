package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import java.util.List;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ParticipantsTypeCell extends BaseCell {
    public static int TYPE_ALL = 0;
    public static int TYPE_NEW = 1;
    private int selectedType;

    static {
    }

    public ParticipantsTypeCell(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
        this.imageView.setVisibility(8);
    }

    public int getSelectedType() {
        return this.selectedType;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public boolean needCheck() {
        return true;
    }

    public void setType(int r3, boolean r4, boolean r5, List<TLRPC.TL_help_country> r6, TLRPC.Chat r7) {
        this.selectedType = r3;
        boolean r72 = ChatObject.isChannelAndNotMegaGroup(r7);
        if (r3 != TYPE_ALL) goto L10;
        SimpleTextView r32 = this.titleTextView;
        if (r72 == false) goto L7;
        int r73 = R.string.BoostingAllSubscribers;
    L8:
        r32.setText(LocaleController.formatString(r73, new Object[0]));
    L16:
        this.radioButton.setChecked(r4, false);
        setDivider(r5);
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2, this.resourcesProvider));
        if (r6.size() != 0) goto L21;
        setSubtitle(withArrow(LocaleController.getString(R.string.BoostingFromAllCountries)));
        return;
    L21:
        if (r6.size() <= 3) goto L23;
        setSubtitle(withArrow(LocaleController.formatPluralString("BoostingFromCountriesCount", r6.size(), new Object[0])));
        return;
    L23:
        if (r6.size() != 1) goto L27;
        setSubtitle(withArrow(LocaleController.formatString("BoostingFromAllCountries1", R.string.BoostingFromAllCountries1, new Object[]{r6.get(0).default_name})));
        return;
    L27:
        if (r6.size() != 2) goto L30;
        setSubtitle(withArrow(LocaleController.formatString("BoostingFromAllCountries2", R.string.BoostingFromAllCountries2, new Object[]{r6.get(0).default_name, r6.get(1).default_name})));
        return;
    L30:
        setSubtitle(withArrow(LocaleController.formatString("BoostingFromAllCountries3", R.string.BoostingFromAllCountries3, new Object[]{r6.get(0).default_name, r6.get(1).default_name, r6.get(2).default_name})));
        return;
    L7:
        r73 = R.string.BoostingAllMembers;
        goto L8
    L10:
        if (r3 != TYPE_NEW) goto L16;
        SimpleTextView r33 = this.titleTextView;
        if (r72 == false) goto L14;
        int r74 = R.string.BoostingNewSubscribers;
    L15:
        r33.setText(LocaleController.formatString(r74, new Object[0]));
        goto L16
    L14:
        r74 = R.string.BoostingNewMembers;
        goto L15
    }
}
