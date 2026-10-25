package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ReorderingBulletinLayout extends Bulletin.SimpleLayout {
    private final ReorderingHintDrawable hintDrawable;

    public ReorderingBulletinLayout(Context r1, String r2, Theme.ResourcesProvider r3) {
        super(r1, r3);
        this.textView.setText(r2);
        this.textView.setTranslationY(-1.0f);
        ImageView r12 = this.imageView;
        ReorderingHintDrawable r22 = new ReorderingHintDrawable();
        this.hintDrawable = r22;
        r12.setImageDrawable(r22);
    }

    @Override // org.telegram.ui.Components.Bulletin.Layout
    public void onEnterTransitionEnd() {
        super.onEnterTransitionEnd();
        this.hintDrawable.startAnimation();
    }

    @Override // org.telegram.ui.Components.Bulletin.Layout
    public void onExitTransitionEnd() {
        super.onExitTransitionEnd();
        this.hintDrawable.resetAnimation();
    }
}
