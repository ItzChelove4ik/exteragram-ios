package org.telegram.ui.Components.Reactions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableStringBuilder;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class UpdateReactionsButton extends ButtonWithCounterView {
    private SpannableStringBuilder lock;

    public UpdateReactionsButton(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
    }

    public void removeLvlRequiredState() {
        setSubText(null, true);
    }

    public void setDefaultState() {
        setText(new SpannableStringBuilder(LocaleController.getString(R.string.ReactionUpdateReactionsBtn)), false);
        this.lock = new SpannableStringBuilder("l");
        ColoredImageSpan r0 = new ColoredImageSpan(R.drawable.mini_switch_lock);
        r0.setTopOffset(1);
        this.lock.setSpan(r0, 0, 1, 33);
    }

    public void setLvlRequiredState(int r5) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append(this.lock).append(LocaleController.formatPluralString("ReactionLevelRequiredBtn", r5, new Object[0]));
        setSubText(r0, true);
    }
}
