package dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public final class SetBuilder<T> {
    private final List<T> contributions;

    private SetBuilder(int r2) {
        this.contributions = new ArrayList(r2);
    }

    public static <T> SetBuilder<T> newSetBuilder(int r1) {
        return new SetBuilder(r1);
    }

    public SetBuilder<T> add(T r3) {
        this.contributions.add(Preconditions.checkNotNull(r3, "Set contributions cannot be null"));
        return this;
    }

    public Set<T> build() {
        if (this.contributions.isEmpty() == false) goto L7;
        return Collections.EMPTY_SET;
    L7:
        if (this.contributions.size() != 1) goto L11;
        return Collections.singleton(this.contributions.get(0));
    L11:
        return Collections.unmodifiableSet(new HashSet(this.contributions));
    }
}
