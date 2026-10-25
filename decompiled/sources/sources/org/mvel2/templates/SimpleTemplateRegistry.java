package org.mvel2.templates;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class SimpleTemplateRegistry implements TemplateRegistry {
    private Map<String, CompiledTemplate> NAMED_TEMPLATES;

    public SimpleTemplateRegistry() {
        this.NAMED_TEMPLATES = new HashMap();
    }

    @Override // org.mvel2.templates.TemplateRegistry
    public void addNamedTemplate(String r1, CompiledTemplate r2) {
        this.NAMED_TEMPLATES.put(r1, r2);
    }

    @Override // org.mvel2.templates.TemplateRegistry
    public boolean contains(String r1) {
        return this.NAMED_TEMPLATES.containsKey(r1);
    }

    @Override // org.mvel2.templates.TemplateRegistry
    public CompiledTemplate getNamedTemplate(String r3) {
        CompiledTemplate r2 = this.NAMED_TEMPLATES.get(r3);
        if (r2 == null) goto L6;
        return r2;
    L6:
        throw new TemplateError("no named template exists '" + r3 + "'");
    }

    @Override // org.mvel2.templates.TemplateRegistry
    public Set<String> getNames() {
        return this.NAMED_TEMPLATES.keySet();
    }

    @Override // org.mvel2.templates.TemplateRegistry
    public Iterator iterator() {
        return this.NAMED_TEMPLATES.keySet().iterator();
    }
}
