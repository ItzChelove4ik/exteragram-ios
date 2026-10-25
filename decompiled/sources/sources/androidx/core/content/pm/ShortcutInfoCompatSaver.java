package androidx.core.content.pm;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ShortcutInfoCompatSaver<T> {

    /* loaded from: classes3.dex */
    public static class NoopImpl extends ShortcutInfoCompatSaver<Void> {
        public NoopImpl() {
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void addShortcuts(List r1) {
            return addShortcuts2(r1);
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* renamed from: addShortcuts, reason: avoid collision after fix types in other method */
        public Void addShortcuts2(List<ShortcutInfoCompat> r1) {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void removeAllShortcuts() {
            return removeAllShortcuts2();
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* renamed from: removeAllShortcuts, reason: avoid collision after fix types in other method */
        public Void removeAllShortcuts2() {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void removeShortcuts(List r1) {
            return removeShortcuts2(r1);
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* renamed from: removeShortcuts, reason: avoid collision after fix types in other method */
        public Void removeShortcuts2(List<String> r1) {
            return null;
        }
    }

    public ShortcutInfoCompatSaver() {
    }

    public abstract T addShortcuts(List<ShortcutInfoCompat> r1);

    public List<ShortcutInfoCompat> getShortcuts() {
        return new ArrayList();
    }

    public abstract T removeAllShortcuts();

    public abstract T removeShortcuts(List<String> r1);
}
