package com.exteragram.messenger.plugins.hooks;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.AppUtils;
import de.robv.android.xposed.XC_MethodHook;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 32\u00020\u0001:\u000223B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020(J\u0012\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u0003H\u0002J\u001c\u0010/\u001a\u00020(2\b\u00100\u001a\u0004\u0018\u00010\u00012\b\u00101\u001a\u0004\u0018\u00010\u0001H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R.\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0013j\n\u0012\u0004\u0012\u00020\u0000\u0018\u0001`\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\u0005R \u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00064"}, d2 = {"Lcom/exteragram/messenger/plugins/hooks/HookFilter;", _UrlKt.FRAGMENT_ENCODE_SET, "filterType", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "getFilterType", "()Ljava/lang/String;", "typeId", _UrlKt.FRAGMENT_ENCODE_SET, "compiledCondition", "Lcom/exteragram/messenger/plugins/hooks/HookFilter$CompiledCondition;", "argIndex", "getArgIndex", "()Ljava/lang/Integer;", "setArgIndex", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "orFilters", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getOrFilters", "()Ljava/util/ArrayList;", "setOrFilters", "(Ljava/util/ArrayList;)V", "mvelExpression", "getMvelExpression", "setMvelExpression", "instanceOf", "Ljava/lang/Class;", "getInstanceOf", "()Ljava/lang/Class;", "setInstanceOf", "(Ljava/lang/Class;)V", "object", "getObject", "()Ljava/lang/Object;", "setObject", "(Ljava/lang/Object;)V", "execute", _UrlKt.FRAGMENT_ENCODE_SET, "param", "Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;", "isBefore", "getCompiledExpression", "Ljava/io/Serializable;", "expression", "valuesEqual", "a", "b", "CompiledCondition", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HookFilter {
    public static final Companion Companion = null;
    private static final int TYPE_ARGUMENT_EQUAL = 3;
    private static final int TYPE_ARGUMENT_IS_FALSE = 6;
    private static final int TYPE_ARGUMENT_IS_INSTANCE_OF = 5;
    private static final int TYPE_ARGUMENT_IS_NULL = 7;
    private static final int TYPE_ARGUMENT_IS_TRUE = 8;
    private static final int TYPE_ARGUMENT_NOT_EQUAL = 4;
    private static final int TYPE_ARGUMENT_NOT_NULL = 9;
    private static final int TYPE_CONDITION = 2;
    private static final int TYPE_OR = 1;
    private static final int TYPE_RESULT_EQUAL = 10;
    private static final int TYPE_RESULT_IS_FALSE = 12;
    private static final int TYPE_RESULT_IS_INSTANCE_OF = 13;
    private static final int TYPE_RESULT_IS_NULL = 14;
    private static final int TYPE_RESULT_IS_TRUE = 15;
    private static final int TYPE_RESULT_NOT_EQUAL = 11;
    private static final int TYPE_RESULT_NOT_NULL = 16;
    private static final int TYPE_UNKNOWN = 0;
    private Integer argIndex;
    private volatile CompiledCondition compiledCondition;
    private final String filterType;
    private Class<?> instanceOf;
    private String mvelExpression;
    private Object object;
    private ArrayList<HookFilter> orFilters;
    private final int typeId;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/exteragram/messenger/plugins/hooks/HookFilter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TYPE_UNKNOWN", _UrlKt.FRAGMENT_ENCODE_SET, "TYPE_OR", "TYPE_CONDITION", "TYPE_ARGUMENT_EQUAL", "TYPE_ARGUMENT_NOT_EQUAL", "TYPE_ARGUMENT_IS_INSTANCE_OF", "TYPE_ARGUMENT_IS_FALSE", "TYPE_ARGUMENT_IS_NULL", "TYPE_ARGUMENT_IS_TRUE", "TYPE_ARGUMENT_NOT_NULL", "TYPE_RESULT_EQUAL", "TYPE_RESULT_NOT_EQUAL", "TYPE_RESULT_IS_FALSE", "TYPE_RESULT_IS_INSTANCE_OF", "TYPE_RESULT_IS_NULL", "TYPE_RESULT_IS_TRUE", "TYPE_RESULT_NOT_NULL", "typeIdFor", "filterType", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ int access$typeIdFor(Companion r0, String r1) {
            return r0.typeIdFor(r1);
        }

        private final int typeIdFor(String r1) {
            switch(r1.hashCode()) {
                case -1842277382: goto L81;
                case -1842101247: goto L76;
                case -1369450155: goto L71;
                case -1284007664: goto L66;
                case -1248106702: goto L61;
                case -861311717: goto L56;
                case -170795378: goto L51;
                case 3555: goto L46;
                case 180205237: goto L41;
                case 488295718: goto L36;
                case 516769938: goto L31;
                case 1282972614: goto L26;
                case 1461304240: goto L21;
                case 1877750766: goto L16;
                case 1987059034: goto L11;
                case 1987235169: goto L6;
                default: goto L86;
            };
        L86:
            return 0;
        L6:
            if (r1.equals(PluginsConstants.HookFilterTypes.RESULT_IS_TRUE) == false) goto L87;
            return 15;
        L87:
            return 0;
        L11:
            if (r1.equals(PluginsConstants.HookFilterTypes.RESULT_IS_NULL) == false) goto L88;
            return 14;
        L88:
            return 0;
        L16:
            if (r1.equals(PluginsConstants.HookFilterTypes.ARGUMENT_IS_INSTANCE_OF) == false) goto L89;
            return 5;
        L89:
            return 0;
        L21:
            if (r1.equals(PluginsConstants.HookFilterTypes.RESULT_IS_FALSE) == false) goto L90;
            return 12;
        L90:
            return 0;
        L26:
            if (r1.equals(PluginsConstants.HookFilterTypes.ARGUMENT_NOT_EQUAL) == false) goto L91;
            return 4;
        L91:
            return 0;
        L31:
            if (r1.equals(PluginsConstants.HookFilterTypes.RESULT_EQUAL) == false) goto L92;
            return 10;
        L92:
            return 0;
        L36:
            if (r1.equals(PluginsConstants.HookFilterTypes.RESULT_NOT_EQUAL) == false) goto L93;
            return 11;
        L93:
            return 0;
        L41:
            if (r1.equals(PluginsConstants.HookFilterTypes.ARGUMENT_NOT_NULL) == false) goto L94;
            return 9;
        L94:
            return 0;
        L46:
            if (r1.equals(PluginsConstants.HookFilterTypes.OR) == false) goto L95;
            return 1;
        L95:
            return 0;
        L51:
            if (r1.equals(PluginsConstants.HookFilterTypes.RESULT_IS_INSTANCE_OF) == false) goto L96;
            return 13;
        L96:
            return 0;
        L56:
            if (r1.equals("condition") == false) goto L97;
            return 2;
        L97:
            return 0;
        L61:
            if (r1.equals(PluginsConstants.HookFilterTypes.ARGUMENT_EQUAL) == false) goto L98;
            return 3;
        L98:
            return 0;
        L66:
            if (r1.equals(PluginsConstants.HookFilterTypes.ARGUMENT_IS_FALSE) == false) goto L99;
            return 6;
        L99:
            return 0;
        L71:
            if (r1.equals(PluginsConstants.HookFilterTypes.RESULT_NOT_NULL) == false) goto L100;
            return 16;
        L100:
            return 0;
        L76:
            if (r1.equals(PluginsConstants.HookFilterTypes.ARGUMENT_IS_TRUE) == false) goto L101;
            return 8;
        L101:
            return 0;
        L81:
            if (r1.equals(PluginsConstants.HookFilterTypes.ARGUMENT_IS_NULL) == true) goto L84;
            return 0;
        L84:
            return 7;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/plugins/hooks/HookFilter$CompiledCondition;", _UrlKt.FRAGMENT_ENCODE_SET, "source", _UrlKt.FRAGMENT_ENCODE_SET, "expression", "Ljava/io/Serializable;", "<init>", "(Ljava/lang/String;Ljava/io/Serializable;)V", "getSource", "()Ljava/lang/String;", "getExpression", "()Ljava/io/Serializable;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class CompiledCondition {
        private final Serializable expression;
        private final String source;

        public CompiledCondition(String r1, Serializable r2) {
            this.source = r1;
            this.expression = r2;
        }

        public final Serializable getExpression() {
            return this.expression;
        }

        public final String getSource() {
            return this.source;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public HookFilter(String r2) {
        this.filterType = r2;
        this.typeId = Companion.access$typeIdFor(Companion, r2);
    }

    private final Serializable getCompiledExpression(String r3) {
        CompiledCondition r0 = this.compiledCondition;
        if (r0 != null) goto L5;
    L14:
        Serializable r02 = MVEL.compileExpression(r3);     // Catch: Exception -> L10
    L12:
        this.compiledCondition = new CompiledCondition(r3, r02);
        return r02;
    L10:
        e = move-exception;
        AppUtils.log(e);
        r02 = null;
        goto L12
    L5:
        if (Intrinsics.areEqual(r0.getSource(), r3) == false) goto L14;
        return r0.getExpression();
    }

    private final boolean valuesEqual(Object r5, Object r6) {
        if (Intrinsics.areEqual(r5, r6) == false) goto L6;
        return true;
    L6:
        if ((r5 instanceof Number) == true) goto L8;
    L25:
        return false;
    L8:
        if ((r6 instanceof Number) == false) goto L25;
        if ((r5 instanceof Double) == true) goto L23;
        if ((r5 instanceof Float) == true) goto L23;
        if ((r6 instanceof Double) == true) goto L23;
        if ((r6 instanceof Float) == true) goto L23;
        if (((Number) r5).longValue() != ((Number) r6).longValue()) goto L21;
        return true;
    L21:
        return false;
    L23:
        if (((Number) r5).doubleValue() != ((Number) r6).doubleValue()) goto L25;
        return true;
    }

    public final boolean execute(XC_MethodHook.MethodHookParam r5, boolean r6) {
    L19:
        e = move-exception;
        AppUtils.log(e);
    L107:
        return false;
    L4:
        switch(this.typeId) {
            case 1: goto L97;
            case 2: goto L82;
            case 3: goto L43;
            case 4: goto L43;
            case 5: goto L43;
            case 6: goto L43;
            case 7: goto L43;
            case 8: goto L43;
            case 9: goto L43;
            case 10: goto L6;
            case 11: goto L6;
            case 12: goto L6;
            case 13: goto L6;
            case 14: goto L6;
            case 15: goto L6;
            case 16: goto L6;
            default: goto L107;
        };     // Catch: Exception -> L19
    L6:
        if (r6 == false) goto L8;
        return false;
    L8:
        Object r52 = r5.getResult();     // Catch: Exception -> L19
        switch(this.typeId) {
            case 10: goto L42;
            case 11: goto L38;
            case 12: goto L32;
            case 13: goto L25;
            case 14: goto L22;
            case 15: goto L15;
            case 16: goto L11;
            default: goto L10;
        };     // Catch: Exception -> L19
    L10:
        return false;
    L11:
        if (r52 == null) goto L13;
        return true;
    L13:
        return false;
    L22:
        if (r52 != null) goto L24;
        return true;
    L24:
        return false;
    L25:
        Class<?> r4 = this.instanceOf;     // Catch: Exception -> L19
        if (r4 != null) goto L28;
    L30:
        return false;
    L28:
        if (r4.isInstance(r52) != true) goto L30;
        return true;
    L15:
        if ((r52 instanceof Boolean) == true) goto L17;
    L21:
        return false;
    L17:
        if (((Boolean) r52).booleanValue() == false) goto L21;
        return true;
    L32:
        if ((r52 instanceof Boolean) == true) goto L34;
    L36:
        return false;
    L34:
        if (((Boolean) r52).booleanValue() == true) goto L36;
        return true;
    L38:
        if (valuesEqual(r52, this.object) == true) goto L40;
        return true;
    L40:
        return false;
    L42:
        return valuesEqual(r52, this.object);
    L43:
        Integer r62 = this.argIndex;     // Catch: Exception -> L19
        Object[] r53 = r5.args;     // Catch: Exception -> L19
        if (r62 != null) goto L46;
    L81:
        return false;
    L46:
        if (ArraysKt.getIndices(r53).contains(r62.intValue()) == false) goto L81;
        Object r54 = r53[r62.intValue()];     // Catch: Exception -> L19
        switch(this.typeId) {
            case 3: goto L80;
            case 4: goto L76;
            case 5: goto L69;
            case 6: goto L64;
            case 7: goto L60;
            case 8: goto L55;
            case 9: goto L51;
            default: goto L50;
        };     // Catch: Exception -> L19
    L50:
        return false;
    L51:
        if (r54 == null) goto L53;
        return true;
    L53:
        return false;
    L60:
        if (r54 != null) goto L62;
        return true;
    L62:
        return false;
    L69:
        Class<?> r42 = this.instanceOf;     // Catch: Exception -> L19
        if (r42 != null) goto L72;
    L74:
        return false;
    L72:
        if (r42.isInstance(r54) != true) goto L74;
        return true;
    L55:
        if ((r54 instanceof Boolean) == true) goto L57;
    L59:
        return false;
    L57:
        if (((Boolean) r54).booleanValue() == false) goto L59;
        return true;
    L64:
        if ((r54 instanceof Boolean) == true) goto L66;
    L68:
        return false;
    L66:
        if (((Boolean) r54).booleanValue() == true) goto L68;
        return true;
    L76:
        if (valuesEqual(r54, this.object) == true) goto L78;
        return true;
    L78:
        return false;
    L80:
        return valuesEqual(r54, this.object);
    L82:
        HashMap r1 = new HashMap(4);     // Catch: Exception -> L19
        r1.put("param", r5);     // Catch: Exception -> L19
        if (r6 == false) goto L85;
        Object r63 = null;
    L86:
        r1.put("result", r63);     // Catch: Exception -> L19
        r1.put("object", this.object);     // Catch: Exception -> L19
        String r64 = this.mvelExpression;     // Catch: Exception -> L19
        if (r64 != null) goto L89;
        return false;
    L89:
        Serializable r43 = getCompiledExpression(r64);     // Catch: Exception -> L19
        if (r43 != null) goto L92;
        return false;
    L92:
        Boolean r44 = (Boolean) MVEL.executeExpression(r43, r5.thisObject, r1, Boolean.TYPE);     // Catch: Exception -> L19
        if (r44 != null) goto L95;
        return false;
    L95:
        return r44.booleanValue();
    L85:
        r63 = r5.getResult();     // Catch: Exception -> L19
        goto L86
    L97:
        ArrayList<HookFilter> r45 = this.orFilters;     // Catch: Exception -> L19
        if (r45 == null) goto L105;
        Iterator<HookFilter> r46 = r45.iterator();     // Catch: Exception -> L19
    L101:
        if (r46.hasNext() == false) goto L105;
        if (r46.next().execute(r5, r6) == false) goto L101;
        return true;
    L105:
        return false;
    }

    public final Integer getArgIndex() {
        return this.argIndex;
    }

    public final String getFilterType() {
        return this.filterType;
    }

    public final Class<?> getInstanceOf() {
        return this.instanceOf;
    }

    public final String getMvelExpression() {
        return this.mvelExpression;
    }

    public final Object getObject() {
        return this.object;
    }

    public final ArrayList<HookFilter> getOrFilters() {
        return this.orFilters;
    }

    public final void setArgIndex(Integer r1) {
        this.argIndex = r1;
    }

    public final void setInstanceOf(Class<?> r1) {
        this.instanceOf = r1;
    }

    public final void setMvelExpression(String r1) {
        this.mvelExpression = r1;
    }

    public final void setObject(Object r1) {
        this.object = r1;
    }

    public final void setOrFilters(ArrayList<HookFilter> r1) {
        this.orFilters = r1;
    }
}
