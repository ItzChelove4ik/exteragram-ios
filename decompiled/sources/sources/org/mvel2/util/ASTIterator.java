package org.mvel2.util;

import java.io.Serializable;
import org.mvel2.ast.ASTNode;

/* loaded from: classes5.dex */
public interface ASTIterator extends Serializable {
    void addTokenNode(ASTNode r1);

    void addTokenNode(ASTNode r1, ASTNode r2);

    void back();

    void finish();

    ASTNode firstNode();

    boolean hasMoreNodes();

    int index();

    ASTNode nextNode();

    ASTNode nodesAhead(int r1);

    ASTNode nodesBack(int r1);

    ASTNode peekLast();

    ASTNode peekNext();

    ASTNode peekNode();

    void reset();

    String showNodeChain();

    int size();

    void skipNode();
}
