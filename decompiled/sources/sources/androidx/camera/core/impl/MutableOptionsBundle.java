package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.camera.core.impl.Config;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public final class MutableOptionsBundle extends OptionsBundle implements MutableConfig {
    private static final Config.OptionPriority DEFAULT_PRIORITY = null;

    static {
        DEFAULT_PRIORITY = Config.OptionPriority.OPTIONAL;
    }

    private MutableOptionsBundle(TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> r1) {
        super(r1);
    }

    public static MutableOptionsBundle create() {
        return new MutableOptionsBundle(new TreeMap(OptionsBundle.ID_COMPARE));
    }

    public static MutableOptionsBundle from(Config r7) {
        TreeMap r0 = new TreeMap(OptionsBundle.ID_COMPARE);
        Iterator<Config.Option<?>> r1 = r7.listOptions().iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        Config.Option<?> r2 = r1.next();
        Set<Config.OptionPriority> r3 = r7.getPriorities(r2);
        ArrayMap r4 = new ArrayMap();
        Iterator<Config.OptionPriority> r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        Config.OptionPriority r5 = r32.next();
        r4.put(r5, r7.retrieveOptionWithPriority(r2, r5));
        goto L7
    L9:
        r0.put(r2, r4);
        goto L4
    L11:
        return new MutableOptionsBundle(r0);
    }

    @Override // androidx.camera.core.impl.MutableConfig
    public <ValueT> void insertOption(Config.Option<ValueT> r5, Config.OptionPriority r6, ValueT r7) {
        Map<Config.OptionPriority, Object> r0 = this.mOptions.get(r5);
        if (r0 != null) goto L6;
        ArrayMap r02 = new ArrayMap();
        this.mOptions.put(r5, r02);
        r02.put(r6, r7);
        return;
    L6:
        Config.OptionPriority r4 = (Config.OptionPriority) Collections.min(r0.keySet());
        if (Objects.equals(r0.get(r4), r7) == false) goto L9;
    L13:
        r0.put(r6, r7);
        return;
    L9:
        if (Config.hasConflict(r4, r6) == false) goto L13;
        StringBuilder r2 = new StringBuilder("Option values conflicts: ");
        r2.append(r5.getId());
        r2.append(", existing value (");
        r2.append(r4);
        Object r42 = r0.get(r4);
        r2.append(")=");
        r2.append(r42);
        r2.append(", conflicting (");
        r2.append(r6);
        r2.append(")=");
        r2.append(r7);
        throw new IllegalArgumentException(r2.toString());
    }

    @Override // androidx.camera.core.impl.MutableConfig
    public <ValueT> void insertOption(Config.Option<ValueT> r2, ValueT r3) {
        insertOption(r2, DEFAULT_PRIORITY, r3);
    }

    public <ValueT> ValueT removeOption(Config.Option<ValueT> r1) {
        return (ValueT) this.mOptions.remove(r1);
    }
}
