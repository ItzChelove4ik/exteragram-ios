package com.google.firebase.components;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
abstract class CycleDetector {

    /* renamed from: com.google.firebase.components.CycleDetector$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class ComponentNode {
        private final Component<?> component;
        private final Set<ComponentNode> dependencies;
        private final Set<ComponentNode> dependents;

        public ComponentNode(Component<?> r2) {
            this.dependencies = new HashSet();
            this.dependents = new HashSet();
            this.component = r2;
        }

        public void addDependency(ComponentNode r1) {
            this.dependencies.add(r1);
        }

        public void addDependent(ComponentNode r1) {
            this.dependents.add(r1);
        }

        public Component<?> getComponent() {
            return this.component;
        }

        public Set<ComponentNode> getDependencies() {
            return this.dependencies;
        }

        public boolean isLeaf() {
            return this.dependencies.isEmpty();
        }

        public boolean isRoot() {
            return this.dependents.isEmpty();
        }

        public void removeDependent(ComponentNode r1) {
            this.dependents.remove(r1);
        }
    }

    public static class Dep {
        private final Qualified<?> anInterface;
        private final boolean set;

        private Dep(Qualified<?> r1, boolean r2) {
            this.anInterface = r1;
            this.set = r2;
        }

        public /* synthetic */ Dep(Qualified r1, boolean r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public static /* synthetic */ boolean access$100(Dep r0) {
            return r0.set;
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof Dep) == false) goto L10;
            Dep r42 = (Dep) r4;
            if (r42.anInterface.equals(this.anInterface) == false) goto L10;
            if (r42.set != this.set) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            int r0 = (this.anInterface.hashCode() ^ 1000003) * 1000003;
            return Boolean.valueOf(this.set).hashCode() ^ r0;
        }
    }

    public static void detect(List<Component<?>> r7) {
        Set<ComponentNode> r0 = toGraph(r7);
        Set<ComponentNode> r1 = getRoots(r0);
        int r2 = 0;
    L4:
        if (r1.isEmpty() == true) goto L12;
        ComponentNode r3 = r1.iterator().next();
        r1.remove(r3);
        r2 = r2 + 1;
        Iterator<ComponentNode> r4 = r3.getDependencies().iterator();
    L7:
        if (r4.hasNext() == false) goto L4;
        ComponentNode r5 = r4.next();
        r5.removeDependent(r3);
        if (r5.isRoot() == false) goto L7;
        r1.add(r5);
        goto L7
    L12:
        if (r2 != r7.size()) goto L14;
        return;
    L14:
        ArrayList r72 = new ArrayList();
        Iterator<ComponentNode> r02 = r0.iterator();
    L16:
        if (r02.hasNext() == false) goto L23;
        ComponentNode r12 = r02.next();
        if (r12.isRoot() == true) goto L16;
        if (r12.isLeaf() == true) goto L16;
        r72.add(r12.getComponent());
        goto L16
    L23:
        throw new DependencyCycleException(r72);
    }

    private static Set<ComponentNode> getRoots(Set<ComponentNode> r3) {
        HashSet r0 = new HashSet();
        Iterator<ComponentNode> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        ComponentNode r1 = r32.next();
        if (r1.isRoot() == false) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    private static Set<ComponentNode> toGraph(List<Component<?>> r9) {
        HashMap r0 = new HashMap(r9.size());
        Iterator<Component<?>> r92 = r9.iterator();
    L4:
        if (r92.hasNext() == false) goto L19;
        Component<?> r1 = r92.next();
        ComponentNode r3 = new ComponentNode(r1);
        Iterator<Qualified<? super Object>> r4 = r1.getProvidedInterfaces().iterator();
    L7:
        if (r4.hasNext() == false) goto L4;
        Qualified<? super Object> r5 = r4.next();
        Dep r6 = new Dep(r5, !r1.isValue(), null);
        if (r0.containsKey(r6) == true) goto L11;
        r0.put(r6, new HashSet());
    L11:
        Set r7 = (Set) r0.get(r6);
        if (r7.isEmpty() == true) goto L18;
        if (Dep.access$100(r6) == true) goto L18;
        FieldSet$$ExternalSyntheticBUOutline0.m("Multiple components provide %s.", new Object[]{r5});
        return null;
    L18:
        r7.add(r3);
        goto L7
    L19:
        Iterator r93 = r0.values().iterator();
    L21:
        if (r93.hasNext() == false) goto L38;
        Iterator r12 = ((Set) r93.next()).iterator();
    L24:
        if (r12.hasNext() == false) goto L21;
        ComponentNode r32 = (ComponentNode) r12.next();
        Iterator<Dependency> r42 = r32.getComponent().getDependencies().iterator();
    L27:
        if (r42.hasNext() == false) goto L24;
        Dependency r52 = r42.next();
        if (r52.isDirectInjection() == false) goto L27;
        Set r53 = (Set) r0.get(new Dep(r52.getInterface(), r52.isSet(), null));
        if (r53 == null) goto L27;
        Iterator r54 = r53.iterator();
    L36:
        if (r54.hasNext() == false) goto L27;
        ComponentNode r62 = (ComponentNode) r54.next();
        r32.addDependency(r62);
        r62.addDependent(r32);
        goto L36
    L38:
        HashSet r94 = new HashSet();
        Iterator r02 = r0.values().iterator();
    L40:
        if (r02.hasNext() == false) goto L42;
        r94.addAll((Set) r02.next());
        goto L40
    L42:
        return r94;
    }
}
