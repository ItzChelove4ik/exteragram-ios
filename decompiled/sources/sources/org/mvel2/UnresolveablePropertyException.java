package org.mvel2;

import org.mvel2.ast.ASTNode;

/* loaded from: classes5.dex */
public class UnresolveablePropertyException extends RuntimeException {
    private String name;

    public UnresolveablePropertyException(String r3) {
        super("unable to resolve token: " + r3);
        this.name = r3;
    }

    public UnresolveablePropertyException(ASTNode r3) {
        super("unable to resolve token: " + r3.getName());
        this.name = r3.getName();
    }

    public UnresolveablePropertyException(ASTNode r3, Throwable r4) {
        super("unable to resolve token: " + r3.getName(), r4);
        this.name = r3.getName();
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        monitor-enter(this);
        monitor-exit(this);
        return null;
    }

    public String getName() {
        return this.name;
    }
}
