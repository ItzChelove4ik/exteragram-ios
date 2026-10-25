package org.telegram.ui.Stories.recorder;

import android.view.MotionEvent;
import android.view.View;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$GifPage$$ExternalSyntheticLambda1 implements View.OnTouchListener {
    public final /* synthetic */ EmojiBottomSheet.GifPage f$0;
    public final /* synthetic */ RecyclerListView.OnItemClickListener f$1;

    public /* synthetic */ EmojiBottomSheet$GifPage$$ExternalSyntheticLambda1(EmojiBottomSheet.GifPage r1, RecyclerListView.OnItemClickListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View r2, MotionEvent r3) {
        return EmojiBottomSheet.GifPage.m19964$r8$lambda$aSbDKLY4VnhrVVh2TK8DNU9Ms(this.f$0, this.f$1, r2, r3);
    }
}
