package org.aspectj.lang;

/* loaded from: classes5.dex */
public interface JoinPoint {

    public interface StaticPart {
        String toString();
    }

    Object getTarget();
}
