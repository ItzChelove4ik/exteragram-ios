package com.google.firebase.components;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> componentsInCycle;

    public DependencyCycleException(List<Component<?>> r3) {
        super("Dependency cycle detected: " + Arrays.toString(r3.toArray()));
        this.componentsInCycle = r3;
    }
}
