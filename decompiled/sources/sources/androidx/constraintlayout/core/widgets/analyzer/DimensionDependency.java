package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import java.util.Iterator;

/* loaded from: classes3.dex */
class DimensionDependency extends DependencyNode {
    public int wrapValue;

    public DimensionDependency(WidgetRun r1) {
        super(r1);
        if ((r1 instanceof HorizontalWidgetRun) == false) goto L6;
        this.mType = DependencyNode.Type.HORIZONTAL_DIMENSION;
        return;
    L6:
        this.mType = DependencyNode.Type.VERTICAL_DIMENSION;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.DependencyNode
    public void resolve(int r2) {
        if (this.resolved == true) goto L9;
        this.resolved = true;
        this.value = r2;
        Iterator<Dependency> r1 = this.mDependencies.iterator();
    L7:
        if (r1.hasNext() == false) goto L11;
        Dependency r22 = r1.next();
        r22.update(r22);
        goto L7
    L11:
        return;
    }
}
