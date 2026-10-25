package org.mvel2.templates;

import java.util.Iterator;
import java.util.Set;

/* loaded from: classes5.dex */
public interface TemplateRegistry {
    void addNamedTemplate(String r1, CompiledTemplate r2);

    boolean contains(String r1);

    CompiledTemplate getNamedTemplate(String r1);

    Set<String> getNames();

    Iterator iterator();
}
