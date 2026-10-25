package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SelectSendAsPremiumHintBulletinLayout extends Bulletin.MultiLineLayout {
    public SelectSendAsPremiumHintBulletinLayout(Context r5, Theme.ResourcesProvider r6, boolean r7, Runnable r8) {
        super(r5, r6);
        this.imageView.setImageDrawable(ContextCompat.getDrawable(r5, R.drawable.msg_premium_prolfilestar));
        this.imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_undo_infoColor), PorterDuff.Mode.SRC_IN));
        this.textView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.SelectSendAsPeerPremiumHint)));
        Bulletin.UndoButton r72 = new Bulletin.UndoButton(r5, true, r6);
        r72.setText(LocaleController.getString(R.string.SelectSendAsPeerPremiumOpen));
        r72.setUndoAction(r8);
        setButton(r72);
    }
}
