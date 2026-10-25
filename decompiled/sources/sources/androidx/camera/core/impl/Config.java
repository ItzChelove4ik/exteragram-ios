package androidx.camera.core.impl;

import androidx.camera.core.impl.utils.ResolutionSelectorUtil;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public interface Config {

    public static abstract class Option<T> {
        public Option() {
        }

        public static <T> Option<T> create(String r1, Class<?> r2) {
            return create(r1, r2, null);
        }

        public static <T> Option<T> create(String r1, Class<?> r2, Object r3) {
            return new AutoValue_Config_Option(r1, r2, r3);
        }

        public abstract String getId();

        public abstract Object getToken();

        public abstract Class<T> getValueClass();
    }

    public interface OptionMatcher {
        boolean onOptionMatched(Option<?> r1);
    }

    public enum OptionPriority extends Enum<OptionPriority> {
        private static final /* synthetic */ OptionPriority[] $VALUES = null;
        public static final OptionPriority ALWAYS_OVERRIDE = null;
        public static final OptionPriority HIGH_PRIORITY_REQUIRED = null;
        public static final OptionPriority OPTIONAL = null;
        public static final OptionPriority REQUIRED = null;

        private static /* synthetic */ OptionPriority[] $values() {
            return new OptionPriority[]{ALWAYS_OVERRIDE, HIGH_PRIORITY_REQUIRED, REQUIRED, OPTIONAL};
        }

        static {
            ALWAYS_OVERRIDE = new OptionPriority("ALWAYS_OVERRIDE", 0);
            HIGH_PRIORITY_REQUIRED = new OptionPriority("HIGH_PRIORITY_REQUIRED", 1);
            REQUIRED = new OptionPriority("REQUIRED", 2);
            OPTIONAL = new OptionPriority("OPTIONAL", 3);
            $VALUES = $values();
        }

        OptionPriority(String r1, int r2) {
        }

        public static OptionPriority valueOf(String r1) {
            return (OptionPriority) Enum.valueOf(OptionPriority.class, r1);
        }

        public static OptionPriority[] values() {
            return (OptionPriority[]) $VALUES.clone();
        }
    }

    static boolean hasConflict(OptionPriority r1, OptionPriority r2) {
        OptionPriority r0 = OptionPriority.REQUIRED;
        if (r1 != r0) goto L7;
        if (r2 != r0) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    static Config mergeConfigs(Config r3, Config r4) {
        if (r3 != null) goto L6;
        if (r4 != null) goto L6;
        return OptionsBundle.emptyBundle();
    L6:
        if (r4 == null) goto L8;
        MutableOptionsBundle r0 = MutableOptionsBundle.from(r4);
    L9:
        if (r3 == null) goto L15;
        Iterator<Option<?>> r1 = r3.listOptions().iterator();
    L12:
        if (r1.hasNext() == false) goto L15;
        mergeOptionValue(r0, r4, r3, r1.next());
    L15:
        return OptionsBundle.from(r0);
    L8:
        r0 = MutableOptionsBundle.create();
        goto L9
    }

    static void mergeOptionValue(MutableOptionsBundle r2, Config r3, Config r4, Option<?> r5) {
        if (Objects.equals(r5, ImageOutputConfig.OPTION_RESOLUTION_SELECTOR) == false) goto L6;
        ResolutionSelector r1 = (ResolutionSelector) r4.retrieveOption(r5, null);
        ResolutionSelector r32 = (ResolutionSelector) r3.retrieveOption(r5, null);
        r2.insertOption(r5, r4.getOptionPriority(r5), ResolutionSelectorUtil.overrideResolutionSelectors(r32, r1));
        return;
    L6:
        r2.insertOption(r5, r4.getOptionPriority(r5), r4.retrieveOption(r5));
    }

    boolean containsOption(Option<?> r1);

    void findOptions(String r1, OptionMatcher r2);

    OptionPriority getOptionPriority(Option<?> r1);

    Set<OptionPriority> getPriorities(Option<?> r1);

    Set<Option<?>> listOptions();

    <ValueT> ValueT retrieveOption(Option<ValueT> r1);

    <ValueT> ValueT retrieveOption(Option<ValueT> r1, ValueT r2);

    <ValueT> ValueT retrieveOptionWithPriority(Option<ValueT> r1, OptionPriority r2);
}
