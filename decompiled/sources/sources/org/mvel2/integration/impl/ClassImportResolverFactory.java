package org.mvel2.integration.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mvel2.ParserConfiguration;
import org.mvel2.ast.Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ClassImportResolverFactory extends BaseVariableResolverFactory {
    private final ClassLoader classLoader;
    private Map<String, Object> dynImports;
    private final Map<String, Object> imports;
    private Set<String> packageImports;

    public ClassImportResolverFactory(ParserConfiguration r1, VariableResolverFactory r2, boolean r3) {
        if (r1 == null) goto L7;
        if (r3 == true) goto L6;
        this.packageImports = r1.getPackageImports();
    L6:
        this.classLoader = r1.getClassLoader();
        this.imports = Collections.unmodifiableMap(r1.getImports());
    L8:
        this.nextFactory = r2;
        return;
    L7:
        this.classLoader = Thread.currentThread().getContextClassLoader();
        this.imports = null;
        goto L8
    }

    public Class addClass(Class r2) {
        if (this.dynImports != null) goto L5;
        this.dynImports = new HashMap();
    L5:
        this.dynImports.put(r2.getSimpleName(), r2);
        return r2;
    }

    public void addPackageImport(String r2) {
        if (this.packageImports != null) goto L5;
        this.packageImports = new HashSet();
    L5:
        this.packageImports.add(r2);
    }

    public void clear() {
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r3, Object r4) {
        if (this.nextFactory != null) goto L6;
        this.nextFactory = new MapVariableResolverFactory(new HashMap());
    L6:
        return this.nextFactory.createVariable(r3, r4);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r3, Object r4, Class r5) {
        if (this.nextFactory != null) goto L6;
        this.nextFactory = new MapVariableResolverFactory(new HashMap());
    L6:
        return this.nextFactory.createVariable(r3, r4, r5);
    }

    public Map<String, Object> getImportedClasses() {
        return this.imports;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 != null) goto L7;
        return new HashSet(0);
    L7:
        return r1.getKnownVariables();
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r2) {
        if (isResolveable(r2) == false) goto L20;
        Map<String, Object> r0 = this.imports;
        if (r0 != null) goto L7;
    L10:
        Map<String, Object> r02 = this.dynImports;
        if (r02 != null) goto L13;
    L16:
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 == null) goto L20;
        return r1.getVariableResolver(r2);
    L13:
        if (r02.containsKey(r2) == false) goto L16;
        return new SimpleValueResolver(this.dynImports.get(r2));
    L7:
        if (r0.containsKey(r2) == false) goto L10;
        return new SimpleValueResolver(this.imports.get(r2));
    L20:
        Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0.m(r2);
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r7) {
        if (r7 != null) goto L5;
        return false;
    L5:
        Map<String, Object> r1 = this.imports;
        if (r1 != null) goto L8;
    L9:
        Map<String, Object> r12 = this.dynImports;
        if (r12 == null) goto L14;
        if (r12.containsKey(r7) == false) goto L14;
    L15:
        return true;
    L14:
        if (isNextResolveable(r7) == true) goto L15;
        Set<String> r13 = this.packageImports;
        if (r13 == null) goto L24;
        Iterator<String> r14 = r13.iterator();
    L20:
        if (r14.hasNext() == false) goto L24;
        String r3 = r14.next();
        addClass(this.classLoader.loadClass(r3 + "." + r7));     // Catch: Throwable -> L25
        return true;
    L24:
        return false;
    L8:
        if (r1.containsKey(r7) == true) goto L15;
        goto L9
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        Map<String, Object> r1 = this.imports;
        if (r1 != null) goto L8;
    L9:
        Map<String, Object> r2 = this.dynImports;
        if (r2 != null) goto L12;
    L15:
        return false;
    L12:
        if (r2.containsKey(r3) == false) goto L15;
        return true;
    L8:
        if (r1.containsKey(r3) == false) goto L9;
        return true;
    }
}
