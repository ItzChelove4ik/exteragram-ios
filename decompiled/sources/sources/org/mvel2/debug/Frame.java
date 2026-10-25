package org.mvel2.debug;

import org.mvel2.ast.LineLabel;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class Frame {
    private VariableResolverFactory factory;
    private LineLabel label;

    public Frame(LineLabel r1, VariableResolverFactory r2) {
        this.label = r1;
        this.factory = r2;
    }

    public VariableResolverFactory getFactory() {
        return this.factory;
    }

    public int getLineNumber() {
        return this.label.getLineNumber();
    }

    public String getSourceName() {
        return this.label.getSourceFile();
    }

    public void setFactory(VariableResolverFactory r1) {
        this.factory = r1;
    }
}
