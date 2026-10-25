package com.android.dx.util;

/* loaded from: classes.dex */
public interface AnnotatedOutput extends Output {
    void annotate(int r1, String r2);

    void annotate(String r1);

    boolean annotates();

    void endAnnotation();

    int getAnnotationWidth();

    boolean isVerbose();
}
