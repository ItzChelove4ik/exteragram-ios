package me.vkryl.android.animator;

import android.view.animation.Interpolator;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import me.vkryl.android.animator.ListAnimator;

/* loaded from: classes.dex */
public class ReplaceAnimator<T> implements Iterable<ListAnimator.Entry<T>> {
    private final ListAnimator<T> list;

    /* renamed from: me.vkryl.android.animator.ReplaceAnimator$1, reason: invalid class name */
    public class AnonymousClass1 implements ListAnimator.Callback {
        final /* synthetic */ ReplaceAnimator this$0;
        final /* synthetic */ Callback val$callback;

        public AnonymousClass1(ReplaceAnimator r1, Callback r2) {
            this.this$0 = r1;
            this.val$callback = r2;
        }

        @Override // me.vkryl.android.animator.ListAnimator.MetadataCallback
        public boolean hasChanges(ListAnimator<?> r1) {
            return this.val$callback.hasChanges(this.this$0);
        }

        @Override // me.vkryl.android.animator.ListAnimator.MetadataCallback
        public boolean onApplyMetadataAnimation(ListAnimator<?> r1, float r2) {
            return this.val$callback.onApplyMetadataAnimation(this.this$0, r2);
        }

        @Override // me.vkryl.android.animator.ListAnimator.MetadataCallback
        public void onFinishMetadataAnimation(ListAnimator<?> r1, boolean r2) {
            this.val$callback.onFinishMetadataAnimation(this.this$0, r2);
        }

        @Override // me.vkryl.android.animator.ListAnimator.MetadataCallback
        public void onForceApplyChanges(ListAnimator<?> r1) {
            this.val$callback.onForceApplyChanges(this.this$0);
        }

        @Override // me.vkryl.android.animator.ListAnimator.Callback
        public void onItemsChanged(ListAnimator<?> r1) {
            this.val$callback.onItemChanged(this.this$0);
        }

        @Override // me.vkryl.android.animator.ListAnimator.MetadataCallback
        public void onPrepareMetadataAnimation(ListAnimator<?> r1) {
            this.val$callback.onPrepareMetadataAnimation(this.this$0);
        }
    }

    public interface Callback {
        default boolean hasChanges(ReplaceAnimator<?> r1) {
            return false;
        }

        default boolean onApplyMetadataAnimation(ReplaceAnimator<?> r1, float r2) {
            return false;
        }

        default void onFinishMetadataAnimation(ReplaceAnimator<?> r1, boolean r2) {
        }

        default void onForceApplyChanges(ReplaceAnimator<?> r1) {
        }

        void onItemChanged(ReplaceAnimator<?> r1);

        default void onPrepareMetadataAnimation(ReplaceAnimator<?> r1) {
        }
    }

    public ReplaceAnimator(Callback r3, Interpolator r4, long r5) {
        this.list = new ListAnimator(new AnonymousClass1(this, r3), r4, r5);
    }

    public void clear(boolean r1) {
        this.list.clear(r1);
    }

    public ListAnimator.Metadata getMetadata() {
        return this.list.getMetadata();
    }

    @Override // java.lang.Iterable
    public Iterator<ListAnimator.Entry<T>> iterator() {
        return this.list.iterator();
    }

    public void replace(T r1, boolean r2) {
        ListAnimator<T> r0 = this.list;
        if (r1 == null) goto L5;
        List<T> r12 = Collections.singletonList(r1);
    L6:
        r0.reset(r12, r2);
        return;
    L5:
        r12 = null;
        goto L6
    }
}
