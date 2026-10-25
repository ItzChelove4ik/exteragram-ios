package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.camera.core.impl.Config;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public class OptionsBundle implements Config {
    private static final OptionsBundle EMPTY_BUNDLE = null;
    protected static final Comparator<Config.Option<?>> ID_COMPARE = null;
    protected final TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> mOptions;

    public static /* synthetic */ int $r8$lambda$C9CGqjvkmgl13ch_OdKbxfPlMW4(Config.Option r0, Config.Option r1) {
        return r0.getId().compareTo(r1.getId());
    }

    static {
        OptionsBundle$$ExternalSyntheticLambda0 r0 = new OptionsBundle$$ExternalSyntheticLambda0();
        ID_COMPARE = r0;
        EMPTY_BUNDLE = new OptionsBundle(new TreeMap(r0));
    }

    public OptionsBundle(TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> r1) {
        this.mOptions = r1;
    }

    public static OptionsBundle emptyBundle() {
        return EMPTY_BUNDLE;
    }

    public static OptionsBundle from(Config r7) {
        if (OptionsBundle.class.equals(r7.getClass()) == true) goto L5;
        TreeMap r0 = new TreeMap(ID_COMPARE);
        Iterator<Config.Option<?>> r1 = r7.listOptions().iterator();
    L8:
        if (r1.hasNext() == false) goto L15;
        Config.Option<?> r2 = r1.next();
        Set<Config.OptionPriority> r3 = r7.getPriorities(r2);
        ArrayMap r4 = new ArrayMap();
        Iterator<Config.OptionPriority> r32 = r3.iterator();
    L11:
        if (r32.hasNext() == false) goto L13;
        Config.OptionPriority r5 = r32.next();
        r4.put(r5, r7.retrieveOptionWithPriority(r2, r5));
        goto L11
    L13:
        r0.put(r2, r4);
        goto L8
    L15:
        return new OptionsBundle(r0);
    L5:
        return (OptionsBundle) r7;
    }

    @Override // androidx.camera.core.impl.Config
    public boolean containsOption(Config.Option<?> r1) {
        return this.mOptions.containsKey(r1);
    }

    @Override // androidx.camera.core.impl.Config
    public void findOptions(String r3, Config.OptionMatcher r4) {
        Config.Option<?> r0 = Config.Option.create(r3, Void.class);
        Iterator<Map.Entry<Config.Option<?>, Map<Config.OptionPriority, Object>>> r2 = this.mOptions.tailMap(r0).entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L10;
        Map.Entry<Config.Option<?>, Map<Config.OptionPriority, Object>> r02 = r2.next();
        if (r02.getKey().getId().startsWith(r3) == false) goto L15;
        if (r4.onOptionMatched(r02.getKey()) == true) goto L4;
        return;
    L15:
        return;
    }

    @Override // androidx.camera.core.impl.Config
    public Config.OptionPriority getOptionPriority(Config.Option<?> r1) {
        Map<Config.OptionPriority, Object> r0 = this.mOptions.get(r1);
        if (r0 != null) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Option does not exist: ", r1);
        return null;
    L5:
        return (Config.OptionPriority) Collections.min(r0.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    public Set<Config.OptionPriority> getPriorities(Config.Option<?> r1) {
        Map<Config.OptionPriority, Object> r0 = this.mOptions.get(r1);
        if (r0 != null) goto L7;
        return Collections.EMPTY_SET;
    L7:
        return Collections.unmodifiableSet(r0.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    public Set<Config.Option<?>> listOptions() {
        return Collections.unmodifiableSet(this.mOptions.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    public <ValueT> ValueT retrieveOption(Config.Option<ValueT> r1) {
        Map<Config.OptionPriority, Object> r0 = this.mOptions.get(r1);
        if (r0 != null) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Option does not exist: ", r1);
        return null;
    L5:
        return (ValueT) r0.get((Config.OptionPriority) Collections.min(r0.keySet()));
    }

    @Override // androidx.camera.core.impl.Config
    public <ValueT> ValueT retrieveOption(Config.Option<ValueT> r1, ValueT r2) {
        Map<Config.OptionPriority, Object> r0 = this.mOptions.get(r1);
        if (r0 != null) goto L6;
        return r2;
    L6:
        return (ValueT) r0.get((Config.OptionPriority) Collections.min(r0.keySet()));
    }

    @Override // androidx.camera.core.impl.Config
    public <ValueT> ValueT retrieveOptionWithPriority(Config.Option<ValueT> r4, Config.OptionPriority r5) {
        Map<Config.OptionPriority, Object> r3 = this.mOptions.get(r4);
        if (r3 != null) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Option does not exist: ", r4);
        return null;
    L5:
        if (r3.containsKey(r5) == true) goto L7;
        Utils$$ExternalSyntheticBUOutline2.m("Option does not exist: ", r4, " with priority=", r5);
        return null;
    L7:
        return (ValueT) r3.get(r5);
    }
}
