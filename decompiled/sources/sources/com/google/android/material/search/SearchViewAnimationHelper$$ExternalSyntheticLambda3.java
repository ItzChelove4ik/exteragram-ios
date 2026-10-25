package com.google.android.material.search;

/* loaded from: classes5.dex */
public final /* synthetic */ class SearchViewAnimationHelper$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ SearchView f$0;

    public /* synthetic */ SearchViewAnimationHelper$$ExternalSyntheticLambda3(SearchView r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.requestFocusAndShowKeyboardIfNeeded();
    }
}
