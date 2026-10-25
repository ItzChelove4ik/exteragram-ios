package androidx.core.view;

import android.os.Build;
import android.view.ScrollFeedbackProvider;
import android.view.View;

/* loaded from: classes3.dex */
public class ScrollFeedbackProviderCompat {
    private final ScrollFeedbackProviderImpl mImpl;

    /* renamed from: androidx.core.view.ScrollFeedbackProviderCompat$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class ScrollFeedbackProviderApi35Impl implements ScrollFeedbackProviderImpl {
        private final ScrollFeedbackProvider mProvider;

        public ScrollFeedbackProviderApi35Impl(View r1) {
            this.mProvider = ScrollFeedbackProvider.createProvider(r1);
        }

        @Override // androidx.core.view.ScrollFeedbackProviderCompat.ScrollFeedbackProviderImpl
        public void onScrollLimit(int r1, int r2, int r3, boolean r4) {
            this.mProvider.onScrollLimit(r1, r2, r3, r4);
        }

        @Override // androidx.core.view.ScrollFeedbackProviderCompat.ScrollFeedbackProviderImpl
        public void onScrollProgress(int r1, int r2, int r3, int r4) {
            this.mProvider.onScrollProgress(r1, r2, r3, r4);
        }
    }

    public static class ScrollFeedbackProviderBaseImpl implements ScrollFeedbackProviderImpl {
        private ScrollFeedbackProviderBaseImpl() {
        }

        public /* synthetic */ ScrollFeedbackProviderBaseImpl(AnonymousClass1 r1) {
            this();
        }

        @Override // androidx.core.view.ScrollFeedbackProviderCompat.ScrollFeedbackProviderImpl
        public void onScrollLimit(int r1, int r2, int r3, boolean r4) {
        }

        @Override // androidx.core.view.ScrollFeedbackProviderCompat.ScrollFeedbackProviderImpl
        public void onScrollProgress(int r1, int r2, int r3, int r4) {
        }
    }

    public interface ScrollFeedbackProviderImpl {
        void onScrollLimit(int r1, int r2, int r3, boolean r4);

        void onScrollProgress(int r1, int r2, int r3, int r4);
    }

    private ScrollFeedbackProviderCompat(View r3) {
        if (Build.VERSION.SDK_INT < 35) goto L6;
        this.mImpl = new ScrollFeedbackProviderApi35Impl(r3);
        return;
    L6:
        this.mImpl = new ScrollFeedbackProviderBaseImpl(null);
    }

    public static ScrollFeedbackProviderCompat createProvider(View r1) {
        return new ScrollFeedbackProviderCompat(r1);
    }

    public void onScrollLimit(int r1, int r2, int r3, boolean r4) {
        this.mImpl.onScrollLimit(r1, r2, r3, r4);
    }

    public void onScrollProgress(int r1, int r2, int r3, int r4) {
        this.mImpl.onScrollProgress(r1, r2, r3, r4);
    }
}
