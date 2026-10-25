package org.mvel2.compiler;

import java.io.Serializable;

/* loaded from: classes5.dex */
public interface AccessorNode extends Accessor, Serializable {
    AccessorNode getNextNode();

    AccessorNode setNextNode(AccessorNode r1);
}
