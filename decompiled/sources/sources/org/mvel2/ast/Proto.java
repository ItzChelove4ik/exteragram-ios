package org.mvel2.ast;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mvel2.DataConversion;
import org.mvel2.ParserContext;
import org.mvel2.UnresolveablePropertyException;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.integration.impl.SimpleValueResolver;
import org.mvel2.util.CallableProxy;
import org.mvel2.util.SimpleIndexHashMapWrapper;

/* loaded from: classes5.dex */
public class Proto extends ASTNode {
    private int cursorEnd;
    private int cursorStart;
    private String name;
    private Map<String, Receiver> receivers;

    /* renamed from: org.mvel2.ast.Proto$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$mvel2$ast$Proto$ReceiverType = null;

        static {
            int[] r0 = new int[ReceiverType.values().length];
            $SwitchMap$org$mvel2$ast$Proto$ReceiverType = r0;
            r0[ReceiverType.FUNCTION.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$org$mvel2$ast$Proto$ReceiverType[ReceiverType.PROPERTY.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$mvel2$ast$Proto$ReceiverType[ReceiverType.DEFERRED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public class ProtoContextFactory extends MapVariableResolverFactory {
        final /* synthetic */ Proto this$0;
        private final SimpleIndexHashMapWrapper<String, VariableResolver> variableResolvers;

        public ProtoContextFactory(Proto r2, SimpleIndexHashMapWrapper r3) {
            this.this$0 = r2;
            super(r3);
            this.variableResolvers = new SimpleIndexHashMapWrapper(r3, true);
        }

        @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public VariableResolver createIndexedVariable(int r1, String r2, Object r3) {
            VariableResolver r22 = this.variableResolvers.getByIndex(r1);
            if (r22 != null) goto L5;
            SimpleValueResolver r23 = new SimpleValueResolver(r3);
            this.variableResolvers.putAtIndex(r1, r23);
        L7:
            return this.indexedVariableResolvers[r1];
        L5:
            r22.setValue(r3);
            goto L7
        }

        @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public VariableResolver createIndexedVariable(int r2, String r3, Object r4, Class<?> r5) {
            SimpleIndexHashMapWrapper<String, VariableResolver> r52 = this.variableResolvers;
            if (r52 == null) goto L5;
            VariableResolver r22 = r52.getByIndex(r2);
        L6:
            if (r22 == null) goto L13;
            if (r22.getType() == null) goto L13;
            String r23 = "variable already defined within scope: " + r22.getType() + " " + r3;
            Proto r1 = this.this$0;
            Sign$$ExternalSyntheticBUOutline0.m(r23, r1.expr, r1.start);
            return null;
        L13:
            return createIndexedVariable(variableIndexOf(r3), r3, r4);
        L5:
            r22 = null;
            goto L6
        }

        @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public VariableResolver createVariable(String r4, Object r5) {
            VariableResolver r0 = getVariableResolver(r4);     // Catch: UnresolveablePropertyException -> L4
            r0.setValue(r5);     // Catch: UnresolveablePropertyException -> L4
            return r0;
        L4:
            ProtoResolver r02 = new ProtoResolver(this.this$0, this.variables, r4);
            addResolver(r4, r02).setValue(r5);
            return r02;
        }

        @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public VariableResolver createVariable(String r4, Object r5, Class<?> r6) {
            VariableResolver r1 = getVariableResolver(r4);     // Catch: UnresolveablePropertyException -> L5
        L6:
            if (r1 != null) goto L8;
        L12:
            ProtoResolver r0 = new ProtoResolver(this.this$0, this.variables, r4, r6);
            addResolver(r4, r0).setValue(r5);
            return r0;
        L8:
            if (r1.getType() == null) goto L12;
            String r42 = "variable already defined within scope: " + r1.getType() + " " + r4;
            Proto r3 = this.this$0;
            Sign$$ExternalSyntheticBUOutline0.m(r42, r3.expr, r3.start);
            return null;
        L5:
            r1 = null;
            goto L6
        }

        @Override // org.mvel2.integration.impl.BaseVariableResolverFactory
        public String[] getIndexedVariableNames() {
            return null;
        }

        @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public VariableResolver getIndexedVariableResolver(int r1) {
            return this.variableResolvers.getByIndex(r1);
        }

        @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public VariableResolver getVariableResolver(String r4) {
            VariableResolver r0 = this.variableResolvers.get(r4);
            if (r0 == null) goto L6;
            return r0;
        L6:
            if (this.variables.containsKey(r4) == false) goto L9;
            SimpleIndexHashMapWrapper<String, VariableResolver> r02 = this.variableResolvers;
            ProtoResolver r1 = new ProtoResolver(this.this$0, this.variables, r4);
            r02.put(r4, r1);
            return r1;
        L9:
            VariableResolverFactory r3 = this.nextFactory;
            if (r3 != null) goto L12;
            Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0.m(r4);
            return null;
        L12:
            return r3.getVariableResolver(r4);
        }

        @Override // org.mvel2.integration.impl.BaseVariableResolverFactory
        public void setIndexedVariableNames(String[] r1) {
        }

        @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public VariableResolver setIndexedVariableResolver(int r1, VariableResolver r2) {
            this.variableResolvers.putAtIndex(r1, r2);
            return r2;
        }

        @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
        public int variableIndexOf(String r1) {
            return this.variableResolvers.indexOf(r1);
        }
    }

    public class ProtoInstance implements Map<String, Receiver> {
        private VariableResolverFactory instanceStates;
        private Proto protoType;
        private SimpleIndexHashMapWrapper<String, Receiver> receivers;
        final /* synthetic */ Proto this$0;

        public ProtoInstance(Proto r4, Proto r5, Object r6, Object r7, VariableResolverFactory r8) {
            this.this$0 = r4;
            this.protoType = r5;
            this.receivers = new SimpleIndexHashMapWrapper();
            Iterator r52 = Proto.access$100(r5).entrySet().iterator();
        L4:
            if (r52.hasNext() == false) goto L6;
            Map.Entry r0 = (Map.Entry) r52.next();
            this.receivers.put((String) r0.getKey(), ((Receiver) r0.getValue()).init(this, r6, r7, r8));
            goto L4
        L6:
            this.instanceStates = new ProtoContextFactory(r4, this.receivers);
        }

        public static /* synthetic */ VariableResolverFactory access$000(ProtoInstance r0) {
            return r0.instanceStates;
        }

        @Override // java.util.Map
        public void clear() {
        }

        @Override // java.util.Map
        public boolean containsKey(Object r1) {
            return this.receivers.containsKey(r1);
        }

        @Override // java.util.Map
        public boolean containsValue(Object r1) {
            return this.receivers.containsValue(r1);
        }

        @Override // java.util.Map
        public Set<Map.Entry<String, Receiver>> entrySet() {
            return this.receivers.entrySet();
        }

        @Override // java.util.Map
        public /* bridge */ /* synthetic */ Receiver get(Object r1) {
            return get2(r1);
        }

        @Override // java.util.Map
        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Receiver get2(Object r1) {
            return this.receivers.get(r1);
        }

        public Proto getProtoType() {
            return this.protoType;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.receivers.isEmpty();
        }

        @Override // java.util.Map
        public Set<String> keySet() {
            return this.receivers.keySet();
        }

        @Override // java.util.Map
        public /* bridge */ /* synthetic */ Receiver put(String r1, Receiver r2) {
            return put2(r1, r2);
        }

        /* renamed from: put, reason: avoid collision after fix types in other method */
        public Receiver put2(String r1, Receiver r2) {
            return this.receivers.put(r1, r2);
        }

        @Override // java.util.Map
        public void putAll(Map<? extends String, ? extends Receiver> r1) {
        }

        @Override // java.util.Map
        public /* bridge */ /* synthetic */ Receiver remove(Object r1) {
            return remove2(r1);
        }

        @Override // java.util.Map
        /* renamed from: remove, reason: avoid collision after fix types in other method */
        public Receiver remove2(Object r1) {
            return this.receivers.remove(r1);
        }

        @Override // java.util.Map
        public int size() {
            return this.receivers.size();
        }

        @Override // java.util.Map
        public Collection<Receiver> values() {
            return this.receivers.values();
        }
    }

    public class ProtoResolver implements VariableResolver {
        private Class<?> knownType;
        private String name;
        final /* synthetic */ Proto this$0;
        private Map<String, Object> variableMap;

        public ProtoResolver(Proto r1, Map<String, Object> r2, String r3) {
            this.this$0 = r1;
            this.variableMap = r2;
            this.name = r3;
        }

        public ProtoResolver(Proto r1, Map<String, Object> r2, String r3, Class r4) {
            this.this$0 = r1;
            this.name = r3;
            this.knownType = r4;
            this.variableMap = r2;
        }

        @Override // org.mvel2.integration.VariableResolver
        public int getFlags() {
            return 0;
        }

        @Override // org.mvel2.integration.VariableResolver
        public String getName() {
            return this.name;
        }

        @Override // org.mvel2.integration.VariableResolver
        public Class getType() {
            return this.knownType;
        }

        @Override // org.mvel2.integration.VariableResolver
        public Object getValue() {
            return Receiver.access$200((Receiver) this.variableMap.get(this.name));
        }

        public void setName(String r1) {
            this.name = r1;
        }

        @Override // org.mvel2.integration.VariableResolver
        public void setStaticType(Class r1) {
            this.knownType = r1;
        }

        @Override // org.mvel2.integration.VariableResolver
        public void setValue(Object r3) {
            if (this.knownType == null) goto L15;
            if (r3 == null) goto L15;
            Class<?> r0 = r3.getClass();
            Class<?> r1 = this.knownType;
            if (r0 == r1) goto L15;
            if (DataConversion.canConvert(r1, r3.getClass()) == true) goto L17;
            String r32 = "cannot assign " + r3.getClass().getName() + " to type: " + this.knownType.getName();
            Proto r2 = this.this$0;
            Sign$$ExternalSyntheticBUOutline0.m(r32, r2.expr, r2.start);
            return;
        L17:
            r3 = DataConversion.convert(r3, this.knownType);     // Catch: Exception -> L11
        L11:
            String r33 = "cannot convert value of " + r3.getClass().getName() + " to: " + this.knownType.getName();
            Proto r22 = this.this$0;
            Sign$$ExternalSyntheticBUOutline0.m(r33, r22.expr, r22.start);
            return;
        L15:
            Receiver.access$202((Receiver) this.variableMap.get(this.name), r3);
        }
    }

    public class Receiver implements CallableProxy {
        private ExecutableStatement initValue;
        private ProtoInstance instance;
        private Object receiver;
        final /* synthetic */ Proto this$0;
        private ReceiverType type;

        public Receiver(Proto r1, ProtoInstance r2, ReceiverType r3, Object r4) {
            this.this$0 = r1;
            this.instance = r2;
            this.type = r3;
            this.receiver = r4;
        }

        public Receiver(Proto r1, ProtoInstance r2, ReceiverType r3, ExecutableStatement r4) {
            this.this$0 = r1;
            this.instance = r2;
            this.type = r3;
            this.initValue = r4;
        }

        public static /* synthetic */ Object access$200(Receiver r0) {
            return r0.receiver;
        }

        public static /* synthetic */ Object access$202(Receiver r0, Object r1) {
            r0.receiver = r1;
            return r1;
        }

        @Override // org.mvel2.util.CallableProxy
        public Object call(Object r3, Object r4, VariableResolverFactory r5, Object[] r6) {
            int r0 = AnonymousClass1.$SwitchMap$org$mvel2$ast$Proto$ReceiverType[this.type.ordinal()];
            if (r0 == 1) goto L15;
            if (r0 == 2) goto L13;
            if (r0 == 3) goto L10;
            return null;
        L10:
            Proto r2 = this.this$0;
            Sign$$ExternalSyntheticBUOutline0.m("unresolved prototype receiver", r2.expr, r2.start);
            return null;
        L13:
            return this.receiver;
        L15:
            return ((Function) this.receiver).call(r3, r4, new InvokationContextFactory(r5, ProtoInstance.access$000(this.instance)), r6);
        }

        public Receiver init(ProtoInstance r5, Object r6, Object r7, VariableResolverFactory r8) {
            Proto r1 = this.this$0;
            ReceiverType r2 = this.type;
            if (r2 != ReceiverType.PROPERTY) goto L7;
            ExecutableStatement r3 = this.initValue;
            if (r3 == null) goto L7;
            Object r4 = r3.getValue(r6, r7, r8);
        L9:
            return new Receiver(r1, r5, r2, r4);
        L7:
            r4 = this.receiver;
            goto L9
        }

        public void setInitValue(ExecutableStatement r1) {
            this.initValue = r1;
        }

        public void setType(ReceiverType r1) {
            this.type = r1;
        }
    }

    public enum ReceiverType extends Enum<ReceiverType> {
        private static final /* synthetic */ ReceiverType[] $VALUES = null;
        public static final ReceiverType DEFERRED = null;
        public static final ReceiverType FUNCTION = null;
        public static final ReceiverType PROPERTY = null;

        private static /* synthetic */ ReceiverType[] $values() {
            return new ReceiverType[]{DEFERRED, FUNCTION, PROPERTY};
        }

        static {
            DEFERRED = new ReceiverType("DEFERRED", 0);
            FUNCTION = new ReceiverType("FUNCTION", 1);
            PROPERTY = new ReceiverType("PROPERTY", 2);
            $VALUES = $values();
        }

        ReceiverType(String r1, int r2) {
        }

        public static ReceiverType valueOf(String r1) {
            return (ReceiverType) Enum.valueOf(ReceiverType.class, r1);
        }

        public static ReceiverType[] values() {
            return (ReceiverType[]) $VALUES.clone();
        }
    }

    public Proto(String r1, ParserContext r2) {
        super(r2);
        this.name = r1;
        this.receivers = new SimpleIndexHashMapWrapper();
    }

    public static /* synthetic */ Map access$100(Proto r0) {
        return r0.receivers;
    }

    public Receiver declareReceiver(String r3, Class r4, ExecutableStatement r5) {
        Receiver r42 = new Receiver(this, null, ReceiverType.PROPERTY, r5);
        this.receivers.put(r3, r42);
        return r42;
    }

    public Receiver declareReceiver(String r4, Function r5) {
        Receiver r0 = new Receiver(this, null, ReceiverType.FUNCTION, r5);
        this.receivers.put(r4, r0);
        return r0;
    }

    public Receiver declareReceiver(String r3, ReceiverType r4, ExecutableStatement r5) {
        Receiver r0 = new Receiver(this, null, r4, r5);
        this.receivers.put(r3, r0);
        return r0;
    }

    public int getCursorEnd() {
        return this.cursorEnd;
    }

    public int getCursorStart() {
        return this.cursorStart;
    }

    @Override // org.mvel2.ast.ASTNode
    public String getName() {
        return this.name;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        r3.createVariable(this.name, this);
        return this;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        r3.createVariable(this.name, this);
        return this;
    }

    public ProtoInstance newInstance(Object r7, Object r8, VariableResolverFactory r9) {
        return new ProtoInstance(this, this, r7, r8, r9);
    }

    public void setCursorPosition(int r1, int r2) {
        this.cursorStart = r1;
        this.cursorEnd = r2;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return "proto " + this.name;
    }
}
