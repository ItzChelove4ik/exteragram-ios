.class public Lorg/mvel2/compiler/AbstractParser;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/mvel2/compiler/Parser;
.implements Ljava/io/Serializable;


# static fields
.field public static CLASS_LITERALS:Ljava/util/HashMap; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private static final EX_PRECACHE:Ljava/util/WeakHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/WeakHashMap<",
            "Ljava/lang/String;",
            "[C>;"
        }
    .end annotation
.end field

.field protected static final GET:I = 0x2

.field protected static final GET_OR_CREATE:I = 0x3

.field public static final LEVEL_0_PROPERTY_ONLY:I = 0x0

.field public static final LEVEL_1_BASIC_LANG:I = 0x1

.field public static final LEVEL_2_MULTI_STATEMENT:I = 0x2

.field public static final LEVEL_3_ITERATION:I = 0x3

.field public static final LEVEL_4_ASSIGNMENT:I = 0x4

.field public static final LEVEL_5_CONTROL_FLOW:I = 0x5

.field public static LITERALS:Ljava/util/HashMap; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field public static OPERATORS:Ljava/util/HashMap; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field protected static final OP_CONTINUE:I = 0x1

.field protected static final OP_NOT_LITERAL:I = -0x3

.field protected static final OP_OVERFLOW:I = -0x2

.field protected static final OP_RESET_FRAME:I = 0x0

.field protected static final OP_TERMINATE:I = -0x1

.field protected static final REMOVE:I = 0x1

.field protected static final SET:I


# instance fields
.field protected compileMode:Z

.field protected ctx:Ljava/lang/Object;

.field protected cursor:I

.field protected dStack:Lorg/mvel2/util/ExecutionStack;

.field protected debugSymbols:Z

.field protected end:I

.field protected expr:[C

.field protected fields:I

.field protected greedy:Z

.field protected lastLineStart:I

.field protected lastNode:Lorg/mvel2/ast/ASTNode;

.field protected lastWasComment:Z

.field protected lastWasIdentifier:Z

.field protected lastWasLineLabel:Z

.field protected length:I

.field protected line:I

.field protected literalOnly:I

.field protected pCtx:Lorg/mvel2/ParserContext;

.field protected splitAccumulator:Lorg/mvel2/util/ExecutionStack;

.field protected st:I

.field protected start:I

.field protected stk:Lorg/mvel2/util/ExecutionStack;

.field protected variableFactory:Lorg/mvel2/integration/VariableResolverFactory;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 137
    new-instance v0, Ljava/util/WeakHashMap;

    const/16 v1, 0xf

    invoke-direct {v0, v1}, Ljava/util/WeakHashMap;-><init>(I)V

    sput-object v0, Lorg/mvel2/compiler/AbstractParser;->EX_PRECACHE:Ljava/util/WeakHashMap;

    .line 154
    invoke-static {}, Lorg/mvel2/compiler/AbstractParser;->setupParser()V

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 157
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x1

    .line 124
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->greedy:Z

    const/4 v0, 0x0

    .line 125
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 126
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasLineLabel:Z

    .line 127
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasComment:Z

    .line 128
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->compileMode:Z

    const/4 v1, -0x1

    .line 130
    iput v1, p0, Lorg/mvel2/compiler/AbstractParser;->literalOnly:I

    .line 132
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastLineStart:I

    .line 133
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->line:I

    .line 144
    new-instance v1, Lorg/mvel2/util/ExecutionStack;

    invoke-direct {v1}, Lorg/mvel2/util/ExecutionStack;-><init>()V

    iput-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    .line 151
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->debugSymbols:Z

    .line 158
    new-instance v0, Lorg/mvel2/ParserContext;

    invoke-direct {v0}, Lorg/mvel2/ParserContext;-><init>()V

    iput-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    return-void
.end method

.method public constructor <init>(Lorg/mvel2/ParserContext;)V
    .locals 2

    .line 161
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x1

    .line 124
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->greedy:Z

    const/4 v0, 0x0

    .line 125
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 126
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasLineLabel:Z

    .line 127
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasComment:Z

    .line 128
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->compileMode:Z

    const/4 v1, -0x1

    .line 130
    iput v1, p0, Lorg/mvel2/compiler/AbstractParser;->literalOnly:I

    .line 132
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastLineStart:I

    .line 133
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->line:I

    .line 144
    new-instance v1, Lorg/mvel2/util/ExecutionStack;

    invoke-direct {v1}, Lorg/mvel2/util/ExecutionStack;-><init>()V

    iput-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    .line 151
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->debugSymbols:Z

    if-eqz p1, :cond_0

    goto :goto_0

    .line 162
    :cond_0
    new-instance p1, Lorg/mvel2/ParserContext;

    invoke-direct {p1}, Lorg/mvel2/ParserContext;-><init>()V

    :goto_0
    iput-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    return-void
.end method

.method private _captureBlock(Lorg/mvel2/ast/ASTNode;[CZI)Lorg/mvel2/ast/ASTNode;
    .locals 11

    .line 1617
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    const/16 v2, 0x30

    const/4 v3, 0x0

    .line 1629
    const-string v4, "expected \'{\' but found: "

    const/16 v6, 0x7b

    if-eq p4, v2, :cond_f

    const/16 v2, 0x64

    const-string/jumbo v7, "unexpected end of statement"

    if-eq p4, v2, :cond_c

    const/16 v2, 0x65

    if-eq p4, v2, :cond_a

    const/16 v2, 0x28

    if-eqz p3, :cond_1

    .line 1697
    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v8, p2, v4

    if-ne v8, v2, :cond_0

    .line 1705
    iget v8, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v9, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {p2, v4, v8, v2, v9}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v8

    add-int/lit8 v4, v4, 0x1

    add-int/lit8 v9, v8, 0x1

    .line 1708
    iput v9, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    .line 1698
    :cond_0
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v4, "expected \'(\' but encountered: "

    invoke-direct {v2, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v4, p2, v4

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v2, p2, v0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3

    :cond_1
    const/4 v8, 0x0

    move v4, v8

    .line 1712
    :goto_0
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1714
    iget v9, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v10, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ge v9, v10, :cond_9

    .line 1717
    aget-char v7, p2, v9

    if-ne v7, v6, :cond_2

    .line 1718
    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {p2, v9, v10, v6, v7}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v6

    iput v6, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_1

    :cond_2
    add-int/lit8 v9, v9, -0x1

    .line 1722
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToEOSorEOL()V

    .line 1723
    iget v6, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v6, v6, 0x1

    :goto_1
    const/16 v7, 0x800

    if-ne p4, v7, :cond_5

    .line 1727
    move-object v7, p1

    check-cast v7, Lorg/mvel2/ast/IfNode;

    if-eqz p1, :cond_4

    if-nez p3, :cond_3

    add-int/lit8 v9, v9, 0x1

    .line 1731
    invoke-virtual {p0, v9}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v2

    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-virtual {p0, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v3

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v3, v4

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v7, p2, v2, v3, v0}, Lorg/mvel2/ast/IfNode;->setElseBlock([CIILorg/mvel2/ParserContext;)Lorg/mvel2/ast/IfNode;

    move-result-object v0

    return-object v0

    :cond_3
    add-int/lit8 v9, v9, 0x1

    .line 1734
    invoke-virtual {p0, v9}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v3

    move v1, v4

    .line 1735
    invoke-virtual {p0, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v4

    move-object v0, p0

    move v5, p4

    move v2, v8

    .line 1734
    invoke-direct/range {v0 .. v5}, Lorg/mvel2/compiler/AbstractParser;->createBlockToken(IIIII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    check-cast v0, Lorg/mvel2/ast/IfNode;

    invoke-virtual {v7, v0}, Lorg/mvel2/ast/IfNode;->setElseIf(Lorg/mvel2/ast/IfNode;)Lorg/mvel2/ast/IfNode;

    move-result-object v0

    return-object v0

    :cond_4
    move v1, v4

    move v2, v8

    add-int/lit8 v3, v9, 0x1

    move-object v0, p0

    move v5, p4

    move v4, v6

    .line 1739
    invoke-direct/range {v0 .. v5}, Lorg/mvel2/compiler/AbstractParser;->createBlockToken(IIIII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :cond_5
    const/high16 v7, 0x10000

    if-ne p4, v7, :cond_8

    .line 1743
    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v4, v4, 0x1

    iput v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1744
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1745
    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1746
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToNextTokenJunction()V

    .line 1748
    new-instance v4, Ljava/lang/String;

    iget v7, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v8, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v8, v7

    invoke-direct {v4, p2, v7, v8}, Ljava/lang/String;-><init>([CII)V

    const-string/jumbo v7, "while"

    invoke-virtual {v7, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_6

    .line 1749
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1750
    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v4, v3, 0x1

    .line 1751
    iget v7, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v8, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {p2, v3, v7, v2, v8}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v2

    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v9, v9, 0x1

    .line 1752
    invoke-virtual {p0, v9}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v3

    move v1, v4

    invoke-virtual {p0, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v4

    move-object v0, p0

    move v5, p4

    invoke-direct/range {v0 .. v5}, Lorg/mvel2/compiler/AbstractParser;->createBlockToken(IIIII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1754
    :cond_6
    const-string/jumbo v5, "until"

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_7

    .line 1755
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1756
    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v4, v3, 0x1

    .line 1757
    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {p2, v3, v5, v2, v7}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v2

    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v9, v9, 0x1

    .line 1758
    invoke-virtual {p0, v9}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v3

    move v1, v4

    invoke-virtual {p0, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v4

    const/high16 v5, 0x20000

    move-object v0, p0

    invoke-direct/range {v0 .. v5}, Lorg/mvel2/compiler/AbstractParser;->createBlockToken(IIIII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1762
    :cond_7
    const-string v2, "expected \'while\' or \'until\' but encountered: "

    invoke-virtual {v2, v4}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v2, p2, v0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3

    :cond_8
    add-int/lit8 v9, v9, 0x1

    .line 1768
    invoke-virtual {p0, v9}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v3

    invoke-virtual {p0, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v1

    move v0, v4

    move v4, v1

    move v1, v0

    move-object v0, p0

    move v5, p4

    move v2, v8

    invoke-direct/range {v0 .. v5}, Lorg/mvel2/compiler/AbstractParser;->createBlockToken(IIIII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1715
    :cond_9
    invoke-static {v7, p2, v10}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3

    .line 1683
    :cond_a
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextNonBlank()I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v2, p2, v0

    if-ne v2, v6, :cond_b

    add-int/lit8 v2, v0, 0x1

    .line 1687
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {p2, v2, v0, v6, v3}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1689
    new-instance v0, Lorg/mvel2/ast/Stacklang;

    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v3, v2

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move-object v1, p2

    invoke-direct/range {v0 .. v5}, Lorg/mvel2/ast/Stacklang;-><init>([CIIILorg/mvel2/ParserContext;)V

    .line 1690
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1692
    iput-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 1684
    :cond_b
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v2, p2, v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v0, p2, v2}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3

    .line 1631
    :cond_c
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1633
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToNextTokenJunction()V

    .line 1635
    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v2, v4, :cond_e

    sub-int/2addr v2, v0

    .line 1642
    invoke-static {p2, v0, v2}, Lorg/mvel2/util/ParseTools;->createStringTrimmed([CII)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isReservedWord(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_d

    .line 1643
    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isNotValidNameorLabel(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_d

    move-object v1, v0

    .line 1646
    new-instance v0, Lorg/mvel2/util/FunctionParser;

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    sub-int/2addr v3, v2

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    move-object v4, p2

    invoke-direct/range {v0 .. v7}, Lorg/mvel2/util/FunctionParser;-><init>(Ljava/lang/String;II[CILorg/mvel2/ParserContext;Lorg/mvel2/util/ExecutionStack;)V

    .line 1647
    invoke-virtual {v0}, Lorg/mvel2/util/FunctionParser;->parse()Lorg/mvel2/ast/Function;

    move-result-object v1

    .line 1648
    invoke-virtual {v0}, Lorg/mvel2/util/FunctionParser;->getCursor()I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1650
    iput-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v1

    .line 1644
    :cond_d
    const-string v0, "illegal function name or use of reserved word"

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v0, p2, v2}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3

    .line 1636
    :cond_e
    invoke-static {v7, p2, v0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3

    .line 1653
    :cond_f
    invoke-static {}, Lorg/mvel2/util/ProtoParser;->isUnresolvedWaiting()Z

    move-result v0

    if-eqz v0, :cond_10

    .line 1654
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {p2, v0, v2}, Lorg/mvel2/util/ProtoParser;->checkForPossibleUnresolvedViolations([CILorg/mvel2/ParserContext;)V

    .line 1657
    :cond_10
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1658
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToNextTokenJunction()V

    .line 1660
    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v2, v0

    invoke-static {p2, v0, v2}, Lorg/mvel2/util/ParseTools;->createStringTrimmed([CII)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isReservedWord(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_12

    .line 1661
    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isNotValidNameorLabel(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_12

    .line 1664
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextNonBlank()I

    move-result v2

    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v5, p2, v2

    if-ne v5, v6, :cond_11

    add-int/lit8 v2, v2, 0x1

    .line 1668
    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v4, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {p2, v2, v3, v6, v4}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v3

    iput v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    move-object v4, v0

    .line 1670
    new-instance v0, Lorg/mvel2/util/ProtoParser;

    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    iget v6, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    move-object v1, p2

    invoke-direct/range {v0 .. v7}, Lorg/mvel2/util/ProtoParser;-><init>([CIILjava/lang/String;Lorg/mvel2/ParserContext;ILorg/mvel2/util/ExecutionStack;)V

    .line 1671
    invoke-virtual {v0}, Lorg/mvel2/util/ProtoParser;->parse()Lorg/mvel2/ast/Proto;

    move-result-object v1

    .line 1673
    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v3, v1}, Lorg/mvel2/ParserContext;->addImport(Lorg/mvel2/ast/Proto;)V

    .line 1675
    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v2, v3}, Lorg/mvel2/ast/Proto;->setCursorPosition(II)V

    .line 1676
    invoke-virtual {v0}, Lorg/mvel2/util/ProtoParser;->getCursor()I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1678
    invoke-static {v1}, Lorg/mvel2/util/ProtoParser;->notifyForLateResolution(Lorg/mvel2/ast/Proto;)V

    .line 1680
    iput-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v1

    .line 1665
    :cond_11
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v2, p2, v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v0, p2, v2}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3

    .line 1662
    :cond_12
    const-string v0, "illegal prototype name or use of reserved word"

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v0, p2, v2}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v3
.end method

.method private static asInt(Ljava/lang/Object;)I
    .locals 0

    .line 2806
    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0
.end method

.method private captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;
    .locals 8

    const/16 v0, 0x800

    const/4 v1, 0x0

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eq p1, v0, :cond_1

    const/high16 v0, 0x10000

    if-eq p1, v0, :cond_0

    .line 1610
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToNextTokenJunction()V

    .line 1611
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1612
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-direct {p0, v3, v0, v2, p1}, Lorg/mvel2/compiler/AbstractParser;->_captureBlock(Lorg/mvel2/ast/ASTNode;[CZI)Lorg/mvel2/ast/ASTNode;

    move-result-object p0

    return-object p0

    .line 1606
    :cond_0
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1607
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-direct {p0, v3, v0, v1, p1}, Lorg/mvel2/compiler/AbstractParser;->_captureBlock(Lorg/mvel2/ast/ASTNode;[CZI)Lorg/mvel2/ast/ASTNode;

    move-result-object p0

    return-object p0

    :cond_1
    move v4, v2

    move-object v0, v3

    :cond_2
    if-eqz v3, :cond_4

    .line 1583
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToNextTokenJunction()V

    .line 1584
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1585
    iget-object v4, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v6, v4, v5

    const/16 v7, 0x7b

    if-eq v6, v7, :cond_3

    const/16 v7, 0x69

    if-ne v6, v7, :cond_3

    add-int/lit8 v5, v5, 0x1

    iput v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v5, v4, v5

    const/16 v6, 0x66

    if-ne v5, v6, :cond_3

    .line 1586
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->incNextNonBlank()I

    move-result v5

    iput v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v4, v4, v5

    const/16 v5, 0x28

    if-ne v4, v5, :cond_3

    move v4, v2

    goto :goto_0

    :cond_3
    move v4, v1

    .line 1589
    :cond_4
    :goto_0
    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-direct {p0, v3, v5, v4, p1}, Lorg/mvel2/compiler/AbstractParser;->_captureBlock(Lorg/mvel2/ast/ASTNode;[CZI)Lorg/mvel2/ast/ASTNode;

    move-result-object v3

    move-object v5, v3

    check-cast v5, Lorg/mvel2/ast/IfNode;

    invoke-virtual {v5}, Lorg/mvel2/ast/IfNode;->getElseBlock()Lorg/mvel2/compiler/ExecutableStatement;

    move-result-object v5

    if-eqz v5, :cond_5

    .line 1590
    iget p1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/2addr p1, v2

    iput p1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    return-object v0

    :cond_5
    if-nez v0, :cond_6

    move-object v0, v3

    .line 1596
    :cond_6
    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v6, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v5, v6, :cond_7

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v6, v6, v5

    const/16 v7, 0x3b

    if-eq v6, v7, :cond_7

    add-int/lit8 v5, v5, 0x1

    .line 1597
    iput v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1600
    :cond_7
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->ifThenElseBlockContinues()Z

    move-result v5

    if-nez v5, :cond_2

    return-object v0
.end method

.method private createBlockToken(IIIII)Lorg/mvel2/ast/ASTNode;
    .locals 14

    move/from16 v0, p2

    move/from16 v1, p5

    const/4 v2, 0x0

    .line 1530
    iput-boolean v2, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1531
    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v3, v3, 0x1

    iput v3, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1533
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->isStatementNotManuallyTerminated()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 1534
    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v4, Lorg/mvel2/ast/EndOfStatement;

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v4, v5}, Lorg/mvel2/ast/EndOfStatement;-><init>(Lorg/mvel2/ParserContext;)V

    invoke-virtual {v3, v4}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    :cond_0
    sub-int v9, v0, p1

    sub-int v3, p4, p3

    if-gez v3, :cond_1

    move v11, v2

    goto :goto_0

    :cond_1
    move v11, v3

    :goto_0
    const/16 v2, 0x800

    if-eq v1, v2, :cond_a

    const/16 v2, 0x1000

    if-eq v1, v2, :cond_9

    const/16 v2, 0x4000

    if-eq v1, v2, :cond_8

    const v2, 0x8000

    if-eq v1, v2, :cond_7

    const/high16 v2, 0x10000

    if-eq v1, v2, :cond_6

    const/high16 v2, 0x20000

    if-eq v1, v2, :cond_5

    const/high16 v2, 0x40000

    if-eq v1, v2, :cond_2

    .line 1563
    new-instance v6, Lorg/mvel2/ast/WithNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v12, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v13, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v13}, Lorg/mvel2/ast/WithNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    return-object v6

    :cond_2
    move v1, p1

    :goto_1
    if-ge v1, v0, :cond_9

    .line 1547
    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v2, v7, v1

    const/16 v3, 0x3b

    if-ne v2, v3, :cond_3

    .line 1548
    new-instance v6, Lorg/mvel2/ast/ForNode;

    iget v12, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v13, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v13}, Lorg/mvel2/ast/ForNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    return-object v6

    :cond_3
    const/16 v3, 0x3a

    if-ne v2, v3, :cond_4

    goto :goto_2

    :cond_4
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 1561
    :cond_5
    new-instance v6, Lorg/mvel2/ast/DoUntilNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget-object v12, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v12}, Lorg/mvel2/ast/DoUntilNode;-><init>([CIIIILorg/mvel2/ParserContext;)V

    return-object v6

    .line 1559
    :cond_6
    new-instance v6, Lorg/mvel2/ast/DoNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v12, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v13, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v13}, Lorg/mvel2/ast/DoNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    return-object v6

    .line 1555
    :cond_7
    new-instance v6, Lorg/mvel2/ast/WhileNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v12, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v13, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v13}, Lorg/mvel2/ast/WhileNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    return-object v6

    .line 1557
    :cond_8
    new-instance v6, Lorg/mvel2/ast/UntilNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v12, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v13, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v13}, Lorg/mvel2/ast/UntilNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    return-object v6

    .line 1553
    :cond_9
    :goto_2
    new-instance v6, Lorg/mvel2/ast/ForEachNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v12, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v13, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v13}, Lorg/mvel2/ast/ForEachNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    return-object v6

    .line 1544
    :cond_a
    new-instance v6, Lorg/mvel2/ast/IfNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v12, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v13, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move v8, p1

    move/from16 v10, p3

    invoke-direct/range {v6 .. v13}, Lorg/mvel2/ast/IfNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    return-object v6
.end method

.method private createOperator([CII)Lorg/mvel2/ast/ASTNode;
    .locals 3

    const/4 v0, 0x0

    .line 1342
    iput-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1343
    new-instance v0, Lorg/mvel2/ast/OperatorNode;

    sget-object v1, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    new-instance v2, Ljava/lang/String;

    sub-int/2addr p3, p2

    invoke-direct {v2, p1, p2, p3}, Ljava/lang/String;-><init>([CII)V

    invoke-virtual {v1, v2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Integer;

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, p3, p1, p2, v1}, Lorg/mvel2/ast/OperatorNode;-><init>(Ljava/lang/Integer;[CILorg/mvel2/ParserContext;)V

    iput-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0
.end method

.method private createPropertyToken(II)Lorg/mvel2/ast/ASTNode;
    .locals 11

    .line 1375
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-static {v0, p1, p2}, Lorg/mvel2/util/ParseTools;->isPropertyOnly([CII)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_4

    .line 1376
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->hasImports()Z

    move-result v0

    if-eqz v0, :cond_1

    sub-int v0, p2, p1

    .line 1379
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    const/16 v3, 0x2e

    invoke-static {v3, p1, v0, v2}, Lorg/mvel2/util/ArrayTools;->findFirst(CII[C)I

    move-result v0

    const/4 v2, -0x1

    if-eq v0, v2, :cond_0

    .line 1380
    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    sub-int v4, v0, p1

    invoke-direct {v2, v3, p1, v4}, Ljava/lang/String;-><init>([CII)V

    .line 1381
    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v3, v2}, Lorg/mvel2/ParserContext;->hasImport(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1382
    iput-boolean v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1383
    new-instance v4, Lorg/mvel2/ast/LiteralDeepPropertyNode;

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v6, v0, 0x1

    sub-int/2addr p2, v0

    add-int/lit8 v7, p2, -0x1

    iget v8, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    .line 1384
    invoke-virtual {p1, v2}, Lorg/mvel2/ParserContext;->getImport(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v9

    iget-object v10, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v4 .. v10}, Lorg/mvel2/ast/LiteralDeepPropertyNode;-><init>([CIIILjava/lang/Object;Lorg/mvel2/ParserContext;)V

    iput-object v4, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 1388
    :cond_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v4, p1

    invoke-direct {v2, v3, p1, v4}, Ljava/lang/String;-><init>([CII)V

    invoke-virtual {v0, v2}, Lorg/mvel2/ParserContext;->hasImport(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1389
    iput-boolean v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1390
    new-instance p1, Lorg/mvel2/ast/LiteralNode;

    iget-object p2, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {p2, v2}, Lorg/mvel2/ParserContext;->getStaticOrClassImport(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p2

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {p1, p2, v0}, Lorg/mvel2/ast/LiteralNode;-><init>(Ljava/lang/Object;Lorg/mvel2/ParserContext;)V

    iput-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object p1

    .line 1395
    :cond_1
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    sub-int v4, p2, p1

    invoke-direct {v2, v3, p1, v4}, Ljava/lang/String;-><init>([CII)V

    invoke-virtual {v0, v2}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1396
    iput-boolean v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1397
    new-instance p1, Lorg/mvel2/ast/LiteralNode;

    sget-object p2, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    invoke-virtual {p2, v2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {p1, p2, v0}, Lorg/mvel2/ast/LiteralNode;-><init>(Ljava/lang/Object;Lorg/mvel2/ParserContext;)V

    iput-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object p1

    .line 1399
    :cond_2
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    invoke-virtual {v0, v2}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    const/4 p2, 0x0

    .line 1400
    iput-boolean p2, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1401
    new-instance p2, Lorg/mvel2/ast/OperatorNode;

    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    invoke-virtual {v0, v2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {p2, v0, v1, p1, v2}, Lorg/mvel2/ast/OperatorNode;-><init>(Ljava/lang/Integer;[CILorg/mvel2/ParserContext;)V

    iput-object p2, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object p2

    .line 1403
    :cond_3
    iget-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    if-eqz v0, :cond_4

    .line 1404
    invoke-direct {p0, v1}, Lorg/mvel2/compiler/AbstractParser;->procTypedNode(Z)Lorg/mvel2/ast/ASTNode;

    move-result-object p0

    return-object p0

    .line 1408
    :cond_4
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v0, :cond_5

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-static {v0, p1, p2}, Lorg/mvel2/util/ParseTools;->isArrayType([CII)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 1409
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v4, p1

    add-int/lit8 v4, v4, -0x2

    invoke-direct {v2, v3, p1, v4}, Ljava/lang/String;-><init>([CII)V

    invoke-virtual {v0, v2}, Lorg/mvel2/ParserContext;->hasImport(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 1410
    iput-boolean v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1411
    new-instance p2, Lorg/mvel2/ast/TypeDescriptor;

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v1, p1

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    invoke-direct {p2, v0, p1, v1, v2}, Lorg/mvel2/ast/TypeDescriptor;-><init>([CIII)V

    .line 1414
    :try_start_0
    new-instance v0, Lorg/mvel2/ast/LiteralNode;

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {p2, v1}, Lorg/mvel2/ast/TypeDescriptor;->getClassReference(Lorg/mvel2/ParserContext;)Ljava/lang/Class;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v1, v2}, Lorg/mvel2/ast/LiteralNode;-><init>(Ljava/lang/Object;Lorg/mvel2/ParserContext;)V

    iput-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    .line 1417
    :catch_0
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "could not resolve class: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2}, Lorg/mvel2/ast/TypeDescriptor;->getClassName()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-static {p2, p0, p1}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    const/4 p0, 0x0

    return-object p0

    .line 1422
    :cond_5
    iput-boolean v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 1424
    new-instance v0, Lorg/mvel2/ast/ASTNode;

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-virtual {p0, p1}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v2

    invoke-virtual {p0, p2}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result p2

    sub-int v3, p2, p1

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v0 .. v5}, Lorg/mvel2/ast/ASTNode;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0
.end method

.method private dreduce()V
    .locals 2

    .line 2548
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->copy2(Lorg/mvel2/util/ExecutionStack;)V

    .line 2549
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p0}, Lorg/mvel2/util/ExecutionStack;->op()V

    return-void
.end method

.method public static isArithmeticOperator(I)Z
    .locals 1

    const/4 v0, -0x1

    if-eq p0, v0, :cond_0

    const/4 v0, 0x6

    if-ge p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static loadLanguageFeaturesByLevel(I)Ljava/util/HashMap;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)",
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    const/16 v0, 0x19

    .line 2345
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const/16 v1, 0x15

    .line 2340
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const/16 v2, 0x64

    .line 2303
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    .line 2293
    new-instance v3, Ljava/util/HashMap;

    invoke-direct {v3}, Ljava/util/HashMap;-><init>()V

    packed-switch p0, :pswitch_data_0

    return-object v3

    :pswitch_0
    const/16 p0, 0x30

    .line 2296
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v4, "proto"

    invoke-virtual {v3, v4, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :pswitch_1
    const/16 p0, 0x27

    .line 2299
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v4, "if"

    invoke-virtual {v3, v4, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x28

    .line 2300
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v4, "else"

    invoke-virtual {v3, v4, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x1d

    .line 2301
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v4, "?"

    invoke-virtual {v3, v4, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x2c

    .line 2302
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v4, "switch"

    invoke-virtual {v3, v4, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 2303
    const-string p0, "function"

    invoke-virtual {v3, p0, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 2304
    const-string p0, "def"

    invoke-virtual {v3, p0, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x65

    .line 2305
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "stacklang"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :pswitch_2
    const/16 p0, 0x1f

    .line 2309
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x62

    .line 2310
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v2, "var"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x34

    .line 2311
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "+="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x35

    .line 2312
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "-="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x37

    .line 2313
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "/="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x38

    .line 2314
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "%="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :pswitch_3
    const/16 p0, 0x26

    .line 2317
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "foreach"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x29

    .line 2318
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v2, "while"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x2a

    .line 2319
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v2, "until"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x2b

    .line 2320
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "for"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x2d

    .line 2321
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "do"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :pswitch_4
    const/16 p0, 0x63

    .line 2324
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "return"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x25

    .line 2325
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, ";"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :pswitch_5
    const/4 p0, 0x0

    .line 2328
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "+"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x1

    .line 2329
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "-"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x2

    .line 2330
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "*"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x5

    .line 2331
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "**"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x3

    .line 2332
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "/"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x4

    .line 2333
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "%"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x12

    .line 2334
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "=="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x13

    .line 2335
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "!="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0xf

    .line 2336
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, ">"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x11

    .line 2337
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, ">="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0xe

    .line 2338
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "<"

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x10

    .line 2339
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v2, "<="

    invoke-virtual {v3, v2, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 2340
    const-string p0, "&&"

    invoke-virtual {v3, p0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 2341
    const-string p0, "and"

    invoke-virtual {v3, p0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x16

    .line 2342
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v1, "||"

    invoke-virtual {v3, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x17

    .line 2343
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v1, "or"

    invoke-virtual {v3, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x18

    .line 2344
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v1, "~="

    invoke-virtual {v3, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 2345
    const-string p0, "instanceof"

    invoke-virtual {v3, p0, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 2346
    const-string p0, "is"

    invoke-virtual {v3, p0, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x1a

    .line 2347
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "contains"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x1b

    .line 2348
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "soundslike"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x1c

    .line 2349
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "strsim"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x24

    .line 2350
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "convertable_to"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x2f

    .line 2351
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "isdef"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x14

    .line 2353
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "#"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x6

    .line 2355
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "&"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x7

    .line 2356
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v0, "|"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x8

    .line 2357
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "^"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0xa

    .line 2358
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "<<"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0xc

    .line 2359
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "<<<"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x9

    .line 2360
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, ">>"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0xb

    .line 2361
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, ">>>"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x22

    .line 2363
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "new"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x23

    .line 2364
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "in"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x2e

    .line 2366
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v0, "with"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x61

    .line 2368
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "assert"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x60

    .line 2369
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "import"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x5f

    .line 2370
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "import_static"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x32

    .line 2372
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "++"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/16 p0, 0x33

    .line 2373
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "--"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :pswitch_6
    const/16 p0, 0x1e

    .line 2376
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, ":"

    invoke-virtual {v3, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-object v3

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private procTypedNode(Z)Lorg/mvel2/ast/ASTNode;
    .locals 13

    .line 1435
    :goto_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getLiteralValue()Ljava/lang/Object;

    move-result-object v0

    instance-of v0, v0, Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 1436
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getLiteralValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->toCharArray()[C

    move-result-object v0

    .line 1437
    new-instance v1, Lorg/mvel2/ast/TypeDescriptor;

    array-length v2, v0

    const/4 v3, 0x0

    invoke-direct {v1, v0, v3, v2, v3}, Lorg/mvel2/ast/TypeDescriptor;-><init>([CIII)V

    .line 1440
    :try_start_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v2, v1}, Lorg/mvel2/ast/TypeDescriptor;->getClassReference(Lorg/mvel2/ParserContext;Lorg/mvel2/ast/TypeDescriptor;)Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/mvel2/ast/ASTNode;->setLiteralValue(Ljava/lang/Object;)V

    .line 1441
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->discard()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 1448
    :catch_0
    :cond_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->isLiteral()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getLiteralValue()Ljava/lang/Object;

    move-result-object v0

    instance-of v0, v0, Ljava/lang/Class;

    if-eqz v0, :cond_2

    .line 1449
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->discard()V

    .line 1451
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    if-eqz p1, :cond_1

    .line 1454
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v1, Lorg/mvel2/ast/DeclTypedVarNode;

    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    invoke-direct {v2, v3, v4, v5}, Ljava/lang/String;-><init>([CII)V

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 1455
    invoke-virtual {v6}, Lorg/mvel2/ast/ASTNode;->getLiteralValue()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Class;

    iget v7, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v7, v7, 0x80

    iget-object v8, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v1 .. v8}, Lorg/mvel2/ast/DeclTypedVarNode;-><init>(Ljava/lang/String;[CIILjava/lang/Class;ILorg/mvel2/ParserContext;)V

    .line 1454
    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto/16 :goto_1

    .line 1458
    :cond_1
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 1459
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v1, Lorg/mvel2/ast/TypedVarNode;

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v4, v3

    add-int/lit8 v4, v4, -0x1

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v5, v5, 0x80

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 1460
    invoke-virtual {v6}, Lorg/mvel2/ast/ASTNode;->getLiteralValue()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Class;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v1 .. v7}, Lorg/mvel2/ast/TypedVarNode;-><init>([CIIILjava/lang/Class;Lorg/mvel2/ParserContext;)V

    .line 1459
    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto/16 :goto_1

    .line 1463
    :cond_2
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    instance-of v0, v0, Lorg/mvel2/ast/Proto;

    if-eqz v0, :cond_4

    .line 1464
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 1470
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    if-eqz p1, :cond_3

    .line 1466
    new-instance v1, Lorg/mvel2/ast/DeclProtoVarNode;

    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    invoke-direct {v2, v3, v4, v5}, Ljava/lang/String;-><init>([CII)V

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    check-cast v3, Lorg/mvel2/ast/Proto;

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v4, v4, 0x80

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v1, v2, v3, v4, v5}, Lorg/mvel2/ast/DeclProtoVarNode;-><init>(Ljava/lang/String;Lorg/mvel2/ast/Proto;ILorg/mvel2/ParserContext;)V

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto/16 :goto_1

    .line 1470
    :cond_3
    new-instance v6, Lorg/mvel2/ast/ProtoVarNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v8, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v9, v1, v8

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v10, v1, 0x80

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    move-object v11, v1

    check-cast v11, Lorg/mvel2/ast/Proto;

    iget-object v12, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v6 .. v12}, Lorg/mvel2/ast/ProtoVarNode;-><init>([CIIILorg/mvel2/ast/Proto;Lorg/mvel2/ParserContext;)V

    invoke-virtual {v0, v6}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto/16 :goto_1

    .line 1476
    :cond_4
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    and-int/lit8 v0, v0, 0x10

    const/4 v1, 0x0

    const-string/jumbo v2, "unknown class or illegal statement: "

    if-nez v0, :cond_a

    .line 1477
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->peek()Ljava/lang/Object;

    move-result-object v0

    instance-of v0, v0, Ljava/lang/Class;

    if-eqz v0, :cond_6

    .line 1478
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 1484
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    if-eqz p1, :cond_5

    .line 1480
    new-instance v1, Lorg/mvel2/ast/DeclTypedVarNode;

    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    invoke-direct {v2, v3, v4, v5}, Ljava/lang/String;-><init>([CII)V

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    .line 1481
    invoke-virtual {v6}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Class;

    iget v7, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v7, v7, 0x80

    iget-object v8, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v1 .. v8}, Lorg/mvel2/ast/DeclTypedVarNode;-><init>(Ljava/lang/String;[CIILjava/lang/Class;ILorg/mvel2/ParserContext;)V

    .line 1480
    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto :goto_1

    .line 1484
    :cond_5
    new-instance v2, Lorg/mvel2/ast/TypedVarNode;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v5, v1, v4

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v6, v1, 0x80

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    .line 1485
    invoke-virtual {v1}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v1

    move-object v7, v1

    check-cast v7, Ljava/lang/Class;

    iget-object v8, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v2 .. v8}, Lorg/mvel2/ast/TypedVarNode;-><init>([CIIILjava/lang/Class;Lorg/mvel2/ParserContext;)V

    .line 1484
    invoke-virtual {v0, v2}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto :goto_1

    .line 1488
    :cond_6
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->peek()Ljava/lang/Object;

    move-result-object v0

    instance-of v0, v0, Lorg/mvel2/ast/Proto;

    if-eqz v0, :cond_9

    .line 1489
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 1495
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    if-eqz p1, :cond_7

    .line 1491
    new-instance v1, Lorg/mvel2/ast/DeclProtoVarNode;

    new-instance v2, Ljava/lang/String;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    invoke-direct {v2, v3, v4, v5}, Ljava/lang/String;-><init>([CII)V

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    .line 1492
    invoke-virtual {v3}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/mvel2/ast/Proto;

    iget v4, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v4, v4, 0x80

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v1, v2, v3, v4, v5}, Lorg/mvel2/ast/DeclProtoVarNode;-><init>(Ljava/lang/String;Lorg/mvel2/ast/Proto;ILorg/mvel2/ParserContext;)V

    .line 1491
    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto :goto_1

    .line 1495
    :cond_7
    new-instance v6, Lorg/mvel2/ast/ProtoVarNode;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v8, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v9, v1, v8

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v10, v1, 0x80

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    .line 1496
    invoke-virtual {v1}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v1

    move-object v11, v1

    check-cast v11, Lorg/mvel2/ast/Proto;

    iget-object v12, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v6 .. v12}, Lorg/mvel2/ast/ProtoVarNode;-><init>([CIIILorg/mvel2/ast/Proto;Lorg/mvel2/ParserContext;)V

    .line 1495
    invoke-virtual {v0, v6}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    .line 1507
    :goto_1
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1508
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ge v0, v1, :cond_8

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v1, v0

    const/16 v2, 0x2c

    if-ne v1, v2, :cond_8

    add-int/lit8 v0, v0, 0x1

    .line 1509
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1510
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v1, Lorg/mvel2/ast/EndOfStatement;

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v1, v2}, Lorg/mvel2/ast/EndOfStatement;-><init>(Lorg/mvel2/ParserContext;)V

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto/16 :goto_0

    .line 1513
    :cond_8
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/mvel2/ast/ASTNode;

    return-object p0

    .line 1500
    :cond_9
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getLiteralValue()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {p1, v0, p0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v1

    .line 1504
    :cond_a
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getLiteralValue()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {p1, v0, p0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-object v1
.end method

.method private reduce(III)V
    .locals 0

    packed-switch p2, :pswitch_data_0

    return-void

    :pswitch_0
    if-gez p1, :cond_0

    mul-int/lit8 p1, p1, -0x1

    .line 2682
    :cond_0
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    shl-int/2addr p1, p3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2690
    :pswitch_1
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    ushr-int/2addr p1, p3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2676
    :pswitch_2
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    shl-int/2addr p1, p3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2686
    :pswitch_3
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    shr-int/2addr p1, p3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2672
    :pswitch_4
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    xor-int/2addr p1, p3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2668
    :pswitch_5
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    or-int/2addr p1, p3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2664
    :pswitch_6
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    and-int/2addr p1, p3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x6
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private reduce(IIJ)V
    .locals 0

    packed-switch p2, :pswitch_data_0

    return-void

    :pswitch_0
    if-gez p1, :cond_0

    mul-int/lit8 p1, p1, -0x1

    .line 2716
    :cond_0
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p2, p3

    shl-int/2addr p1, p2

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2724
    :pswitch_1
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p2, p3

    ushr-int/2addr p1, p2

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2710
    :pswitch_2
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p2, p3

    shl-int/2addr p1, p2

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2720
    :pswitch_3
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p2, p3

    shr-int/2addr p1, p2

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2706
    :pswitch_4
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    int-to-long p1, p1

    xor-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2702
    :pswitch_5
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    int-to-long p1, p1

    or-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2698
    :pswitch_6
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    int-to-long p1, p1

    and-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    :pswitch_data_0
    .packed-switch 0x6
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private reduce(JII)V
    .locals 2

    packed-switch p3, :pswitch_data_0

    return-void

    :pswitch_0
    const-wide/16 v0, 0x0

    cmp-long p3, p1, v0

    if-gez p3, :cond_0

    const-wide/16 v0, -0x1

    mul-long/2addr p1, v0

    .line 2750
    :cond_0
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    shl-long/2addr p1, p4

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2758
    :pswitch_1
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    ushr-long/2addr p1, p4

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2744
    :pswitch_2
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    shl-long/2addr p1, p4

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2754
    :pswitch_3
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    shr-long/2addr p1, p4

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2740
    :pswitch_4
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    int-to-long p3, p4

    xor-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2736
    :pswitch_5
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    int-to-long p3, p4

    or-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2732
    :pswitch_6
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    int-to-long p3, p4

    and-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x6
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private reduce(JIJ)V
    .locals 2

    packed-switch p3, :pswitch_data_0

    return-void

    :pswitch_0
    const-wide/16 v0, 0x0

    cmp-long p3, p1, v0

    if-gez p3, :cond_0

    const-wide/16 v0, -0x1

    mul-long/2addr p1, v0

    .line 2784
    :cond_0
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p3, p4

    shl-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2792
    :pswitch_1
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p3, p4

    ushr-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2778
    :pswitch_2
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p3, p4

    shl-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2788
    :pswitch_3
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    long-to-int p3, p4

    shr-long/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2774
    :pswitch_4
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    xor-long/2addr p1, p4

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2770
    :pswitch_5
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    or-long/2addr p1, p4

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2766
    :pswitch_6
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    and-long/2addr p1, p4

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    :pswitch_data_0
    .packed-switch 0x6
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private reduceNumeric(I)V
    .locals 8

    .line 2641
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->peek2()Ljava/lang/Object;

    move-result-object v0

    .line 2642
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v1}, Lorg/mvel2/util/ExecutionStack;->pop2()Ljava/lang/Object;

    move-result-object v1

    .line 2643
    instance-of v2, v0, Ljava/lang/Integer;

    if-eqz v2, :cond_1

    .line 2644
    instance-of v2, v1, Ljava/lang/Integer;

    if-eqz v2, :cond_0

    .line 2645
    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-direct {p0, v0, p1, v1}, Lorg/mvel2/compiler/AbstractParser;->reduce(III)V

    return-void

    .line 2648
    :cond_0
    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    check-cast v1, Ljava/lang/Long;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v1

    invoke-direct {p0, v0, p1, v1, v2}, Lorg/mvel2/compiler/AbstractParser;->reduce(IIJ)V

    return-void

    .line 2652
    :cond_1
    instance-of v2, v1, Ljava/lang/Integer;

    if-eqz v2, :cond_2

    .line 2653
    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-direct {p0, v2, v3, p1, v0}, Lorg/mvel2/compiler/AbstractParser;->reduce(JII)V

    return-void

    .line 2656
    :cond_2
    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v3

    check-cast v1, Ljava/lang/Long;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v6

    move-object v2, p0

    move v5, p1

    invoke-direct/range {v2 .. v7}, Lorg/mvel2/compiler/AbstractParser;->reduce(JIJ)V

    return-void
.end method

.method public static setLanguageLevel(I)V
    .locals 1

    .line 2288
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->clear()V

    .line 2289
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    invoke-static {p0}, Lorg/mvel2/compiler/AbstractParser;->loadLanguageFeaturesByLevel(I)Ljava/util/HashMap;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/util/HashMap;->putAll(Ljava/util/Map;)V

    return-void
.end method

.method public static setupParser()V
    .locals 3

    .line 171
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    return-void

    .line 172
    :cond_1
    :goto_0
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    .line 173
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    .line 174
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    .line 179
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "System"

    const-class v2, Ljava/lang/System;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 180
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "String"

    const-class v2, Ljava/lang/String;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 181
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "CharSequence"

    const-class v2, Ljava/lang/CharSequence;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 183
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Integer"

    const-class v2, Ljava/lang/Integer;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 184
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "int"

    sget-object v2, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 186
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Long"

    const-class v2, Ljava/lang/Long;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 187
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "long"

    sget-object v2, Ljava/lang/Long;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 189
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Boolean"

    const-class v2, Ljava/lang/Boolean;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 190
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "boolean"

    sget-object v2, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 192
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Short"

    const-class v2, Ljava/lang/Short;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 193
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "short"

    sget-object v2, Ljava/lang/Short;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 195
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Character"

    const-class v2, Ljava/lang/Character;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 196
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "char"

    sget-object v2, Ljava/lang/Character;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 198
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Double"

    const-class v2, Ljava/lang/Double;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 199
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "double"

    sget-object v2, Ljava/lang/Double;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 201
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Float"

    const-class v2, Ljava/lang/Float;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 202
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "float"

    sget-object v2, Ljava/lang/Float;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 204
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Byte"

    const-class v2, Ljava/lang/Byte;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 205
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "byte"

    sget-object v2, Ljava/lang/Byte;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 207
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Math"

    const-class v2, Ljava/lang/Math;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 208
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Void"

    const-class v2, Ljava/lang/Void;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 209
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Object"

    const-class v2, Ljava/lang/Object;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 210
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Number"

    const-class v2, Ljava/lang/Number;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 212
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Class"

    const-class v2, Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 213
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "ClassLoader"

    const-class v2, Ljava/lang/ClassLoader;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 214
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Runtime"

    const-class v2, Ljava/lang/Runtime;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 215
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Thread"

    const-class v2, Ljava/lang/Thread;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 217
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Exception"

    const-class v2, Ljava/lang/Exception;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 218
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "Array"

    const-class v2, Ljava/lang/reflect/Array;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 219
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    const-string v1, "StringBuilder"

    const-class v2, Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 222
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    sget-object v1, Lorg/mvel2/compiler/AbstractParser;->CLASS_LITERALS:Ljava/util/HashMap;

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->putAll(Ljava/util/Map;)V

    .line 223
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    const-string/jumbo v1, "true"

    sget-object v2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 224
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    const-string v1, "false"

    sget-object v2, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 226
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    const-string v1, "null"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 227
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    const-string v1, "nil"

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 229
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->LITERALS:Ljava/util/HashMap;

    const-string v1, "empty"

    sget-object v2, Lorg/mvel2/compiler/BlankLiteral;->INSTANCE:Lorg/mvel2/compiler/BlankLiteral;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 231
    const-string v0, "mvel.future.lang.support"

    invoke-static {v0}, Ljava/lang/Boolean;->getBoolean(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 v0, 0x6

    goto :goto_1

    :cond_2
    const/4 v0, 0x5

    :goto_1
    invoke-static {v0}, Lorg/mvel2/compiler/AbstractParser;->setLanguageLevel(I)V

    return-void
.end method

.method private subArray(II)[C
    .locals 4

    const/4 v0, 0x0

    if-lt p1, p2, :cond_0

    .line 1355
    new-array p0, v0, [C

    return-object p0

    :cond_0
    sub-int/2addr p2, p1

    .line 1357
    new-array v1, p2, [C

    :goto_0
    if-eq v0, p2, :cond_1

    .line 1359
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int v3, v0, p1

    aget-char v2, v2, v3

    aput-char v2, v1, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-object v1
.end method


# virtual methods
.method public addFatalError(Ljava/lang/String;)V
    .locals 4

    .line 2273
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    new-instance v1, Lorg/mvel2/ErrorDetail;

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/4 v3, 0x1

    invoke-direct {v1, v2, p0, v3, p1}, Lorg/mvel2/ErrorDetail;-><init>([CIZLjava/lang/String;)V

    invoke-virtual {v0, v1}, Lorg/mvel2/ParserContext;->addError(Lorg/mvel2/ErrorDetail;)V

    return-void
.end method

.method public addFatalError(Ljava/lang/String;I)V
    .locals 3

    .line 2277
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    new-instance v1, Lorg/mvel2/ErrorDetail;

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    const/4 v2, 0x1

    invoke-direct {v1, p0, p2, v2, p1}, Lorg/mvel2/ErrorDetail;-><init>([CIZLjava/lang/String;)V

    invoke-virtual {v0, v1}, Lorg/mvel2/ParserContext;->addError(Lorg/mvel2/ErrorDetail;)V

    return-void
.end method

.method public arithmeticFunctionReduction(I)I
    .locals 9

    .line 2399
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_15

    .line 2400
    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getOperator()Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v3

    invoke-static {v3}, Lorg/mvel2/compiler/AbstractParser;->isArithmeticOperator(I)Z

    move-result v4

    if-eqz v4, :cond_13

    sget-object v4, Lorg/mvel2/Operator;->PTABLE:[I

    aget v5, v4, v3

    aget p1, v4, p1

    if-le v5, p1, :cond_13

    .line 2401
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->xswap()V

    .line 2406
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object p1

    .line 2412
    iget-boolean v0, p0, Lorg/mvel2/compiler/AbstractParser;->compileMode:Z

    const/4 v4, -0x2

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Lorg/mvel2/ast/ASTNode;->isLiteral()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2413
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v1, Lorg/mvel2/ast/OperatorNode;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v1, v2, v3, v5, p0}, Lorg/mvel2/ast/OperatorNode;-><init>(Ljava/lang/Integer;[CILorg/mvel2/ParserContext;)V

    invoke-virtual {v0, p1, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    return v4

    .line 2417
    :cond_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->ctx:Ljava/lang/Object;

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->variableFactory:Lorg/mvel2/integration/VariableResolverFactory;

    invoke-virtual {p1, v5, v5, v6}, Lorg/mvel2/ast/ASTNode;->getReducedValue(Ljava/lang/Object;Ljava/lang/Object;Lorg/mvel2/integration/VariableResolverFactory;)Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {v0, v2, p1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    move p1, v3

    .line 2422
    :cond_1
    :goto_0
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    const/16 v2, 0x25

    const/4 v5, -0x1

    if-eqz v0, :cond_5

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getOperator()Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v6

    if-eq v6, v5, :cond_4

    if-eq v6, v2, :cond_4

    sget-object v7, Lorg/mvel2/Operator;->PTABLE:[I

    aget v8, v7, v6

    aget v7, v7, v3

    if-le v8, v7, :cond_4

    .line 2426
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->isReduceable()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 2427
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0, v2}, Lorg/mvel2/util/ExecutionStack;->copyx2(Lorg/mvel2/util/ExecutionStack;)V

    .line 2433
    :cond_2
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    .line 2434
    iget-boolean v2, p0, Lorg/mvel2/compiler/AbstractParser;->compileMode:Z

    if-eqz v2, :cond_3

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->isLiteral()Z

    move-result v2

    if-nez v2, :cond_3

    .line 2435
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v2, Lorg/mvel2/ast/OperatorNode;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v2, p1, v3, v5, p0}, Lorg/mvel2/ast/OperatorNode;-><init>(Ljava/lang/Integer;[CILorg/mvel2/ParserContext;)V

    invoke-virtual {v1, v0, v2}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    return v4

    .line 2438
    :cond_3
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->ctx:Ljava/lang/Object;

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->variableFactory:Lorg/mvel2/integration/VariableResolverFactory;

    invoke-virtual {v0, v3, v3, v5}, Lorg/mvel2/ast/ASTNode;->getReducedValue(Ljava/lang/Object;Ljava/lang/Object;Lorg/mvel2/integration/VariableResolverFactory;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v2, p1, v0}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    move p1, v6

    :goto_1
    move v3, p1

    goto :goto_0

    :cond_4
    move p1, v6

    :cond_5
    const/4 v6, 0x1

    if-eqz v0, :cond_11

    if-eq p1, v5, :cond_11

    if-eq p1, v2, :cond_11

    .line 2443
    sget-object v2, Lorg/mvel2/Operator;->PTABLE:[I

    aget v7, v2, p1

    aget v2, v2, v3

    if-ne v7, v2, :cond_9

    .line 2444
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_6

    invoke-direct {p0}, Lorg/mvel2/compiler/AbstractParser;->dreduce()V

    goto :goto_3

    .line 2446
    :cond_6
    :goto_2
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->isReduceable()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 2447
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->xswap_op()V

    goto :goto_2

    .line 2454
    :cond_7
    :goto_3
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    .line 2455
    iget-boolean v2, p0, Lorg/mvel2/compiler/AbstractParser;->compileMode:Z

    if-eqz v2, :cond_8

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->isLiteral()Z

    move-result v2

    if-nez v2, :cond_8

    .line 2456
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v2, Lorg/mvel2/ast/OperatorNode;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v2, p1, v3, v5, p0}, Lorg/mvel2/ast/OperatorNode;-><init>(Ljava/lang/Integer;[CILorg/mvel2/ParserContext;)V

    invoke-virtual {v1, v0, v2}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    return v4

    .line 2459
    :cond_8
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->ctx:Ljava/lang/Object;

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->variableFactory:Lorg/mvel2/integration/VariableResolverFactory;

    invoke-virtual {v0, v5, v5, v6}, Lorg/mvel2/ast/ASTNode;->getReducedValue(Ljava/lang/Object;Ljava/lang/Object;Lorg/mvel2/integration/VariableResolverFactory;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v2, v3, v0}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_1

    .line 2467
    :cond_9
    :goto_4
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->size()I

    move-result v2

    if-le v2, v6, :cond_a

    .line 2468
    invoke-direct {p0}, Lorg/mvel2/compiler/AbstractParser;->dreduce()V

    goto :goto_4

    .line 2471
    :cond_a
    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getOperator()Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v3

    .line 2473
    :goto_5
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->size()I

    move-result v2

    if-eq v2, v6, :cond_b

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->peek2()Ljava/lang/Object;

    move-result-object v2

    instance-of v2, v2, Ljava/lang/Integer;

    if-eqz v2, :cond_b

    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    .line 2474
    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->peek2()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    sget-object v2, Lorg/mvel2/Operator;->PTABLE:[I

    array-length v7, v2

    if-ge p1, v7, :cond_b

    aget v7, v2, p1

    aget v2, v2, v3

    if-lt v7, v2, :cond_b

    .line 2476
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->xswap_op()V

    goto :goto_5

    .line 2494
    :cond_b
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v2

    if-eqz v2, :cond_1

    const/16 v6, 0x15

    if-eq v3, v6, :cond_f

    const/16 v6, 0x16

    if-eq v3, v6, :cond_d

    .line 2512
    iget-boolean v5, p0, Lorg/mvel2/compiler/AbstractParser;->compileMode:Z

    if-eqz v5, :cond_c

    invoke-virtual {v2}, Lorg/mvel2/ast/ASTNode;->isLiteral()Z

    move-result v5

    if-nez v5, :cond_c

    .line 2513
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p0, v0, v2}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    const/4 p0, -0x3

    return p0

    .line 2516
    :cond_c
    iget-object v5, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->ctx:Ljava/lang/Object;

    iget-object v7, p0, Lorg/mvel2/compiler/AbstractParser;->variableFactory:Lorg/mvel2/integration/VariableResolverFactory;

    invoke-virtual {v2, v6, v6, v7}, Lorg/mvel2/ast/ASTNode;->getReducedValue(Ljava/lang/Object;Ljava/lang/Object;Lorg/mvel2/integration/VariableResolverFactory;)Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {v5, v0, v2}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;Ljava/lang/Object;)V

    goto/16 :goto_0

    .line 2504
    :cond_d
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->peekBoolean()Ljava/lang/Boolean;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_e

    return v5

    .line 2506
    :cond_e
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p0, v2}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    return v6

    .line 2497
    :cond_f
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->peekBoolean()Ljava/lang/Boolean;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-nez p1, :cond_10

    return v5

    .line 2499
    :cond_10
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p0, v2}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    return v6

    .line 2485
    :cond_11
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->dStack:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->size()I

    move-result p1

    if-le p1, v6, :cond_12

    .line 2486
    invoke-direct {p0}, Lorg/mvel2/compiler/AbstractParser;->dreduce()V

    .line 2489
    :cond_12
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->isReduceable()Z

    move-result p1

    if-eqz p1, :cond_15

    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->xswap()V

    goto :goto_6

    .line 2521
    :cond_13
    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->isOperator()Z

    move-result p1

    if-eqz p1, :cond_14

    .line 2525
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->reduce()V

    .line 2526
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1, v0}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    goto :goto_6

    .line 2522
    :cond_14
    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo v2, "unexpected token: "

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-static {p1, v0, p0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return v1

    .line 2532
    :cond_15
    :goto_6
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->isReduceable()Z

    move-result p1

    if-eqz p1, :cond_16

    .line 2534
    :goto_7
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->reduce()V

    .line 2535
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->isReduceable()Z

    move-result p1

    if-eqz p1, :cond_16

    .line 2536
    iget-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {p1}, Lorg/mvel2/util/ExecutionStack;->xswap()V

    goto :goto_7

    :cond_16
    return v1
.end method

.method public captureIdentifier()V
    .locals 3

    .line 1902
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_3

    const/4 v0, 0x0

    .line 1903
    :goto_0
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v1, v2, :cond_2

    .line 1904
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v2, v1

    const/16 v2, 0x3b

    if-eq v1, v2, :cond_2

    .line 1909
    invoke-static {v1}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v1

    if-nez v1, :cond_1

    if-eqz v0, :cond_0

    goto :goto_1

    .line 1911
    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "unexpected symbol (was expecting an identifier): "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v1, v1, v2

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v0, v1, p0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-void

    .line 1919
    :cond_1
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/4 v1, 0x1

    add-int/2addr v0, v1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    move v0, v1

    goto :goto_0

    :cond_2
    :goto_1
    return-void

    .line 1902
    :cond_3
    const-string/jumbo v1, "unexpected end of statement: EOF"

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-static {v1, p0, v0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-void
.end method

.method public captureToEOS()V
    .locals 5

    .line 1865
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_3

    .line 1866
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v3, v2, v0

    const/16 v4, 0x22

    if-eq v3, v4, :cond_1

    const/16 v4, 0x2c

    if-eq v3, v4, :cond_3

    const/16 v4, 0x3b

    if-eq v3, v4, :cond_3

    const/16 v4, 0x5b

    if-eq v3, v4, :cond_0

    const/16 v4, 0x7b

    if-eq v3, v4, :cond_0

    const/16 v4, 0x7d

    if-eq v3, v4, :cond_3

    const/16 v4, 0x27

    if-eq v3, v4, :cond_1

    const/16 v4, 0x28

    if-eq v3, v4, :cond_0

    goto :goto_1

    .line 1870
    :cond_0
    iget-object v4, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v2, v0, v1, v3, v4}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-lt v0, v1, :cond_2

    goto :goto_2

    .line 1876
    :cond_1
    invoke-static {v3, v2, v0, v1}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1884
    :cond_2
    :goto_1
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    :cond_3
    :goto_2
    return-void
.end method

.method public captureToEOSorEOL()V
    .locals 3

    .line 1892
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_0

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v1, v0

    const/16 v2, 0xa

    if-eq v1, v2, :cond_0

    const/16 v2, 0xd

    if-eq v1, v2, :cond_0

    const/16 v2, 0x3b

    if-eq v1, v2, :cond_0

    add-int/lit8 v0, v0, 0x1

    .line 1893
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    :cond_0
    return-void
.end method

.method public captureToEOT()V
    .locals 5

    .line 1927
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1929
    :cond_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v2, v0, v1

    const/16 v3, 0x22

    if-eq v2, v3, :cond_6

    const/16 v3, 0x3b

    if-eq v2, v3, :cond_8

    const/16 v3, 0x3d

    if-eq v2, v3, :cond_8

    const/16 v3, 0x5b

    if-eq v2, v3, :cond_4

    const/16 v3, 0x2e

    if-eq v2, v3, :cond_3

    const/16 v4, 0x2f

    if-eq v2, v4, :cond_8

    const/16 v4, 0x7b

    if-eq v2, v4, :cond_4

    const/16 v4, 0x7c

    if-eq v2, v4, :cond_8

    packed-switch v2, :pswitch_data_0

    packed-switch v2, :pswitch_data_1

    .line 1964
    invoke-static {v2}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_7

    .line 1965
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1967
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ge v0, v1, :cond_2

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v2, v2, v0

    if-ne v2, v3, :cond_2

    if-eq v0, v1, :cond_1

    add-int/lit8 v0, v0, 0x1

    .line 1968
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1969
    :cond_1
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    goto :goto_0

    .line 1973
    :cond_2
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->trimWhitespace()V

    return-void

    :pswitch_0
    const/16 v2, 0x27

    .line 1957
    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    invoke-static {v2, v0, v1, v3}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    :cond_3
    add-int/lit8 v1, v1, 0x1

    .line 1951
    iput v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1952
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1953
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    .line 1933
    :cond_4
    :pswitch_1
    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v4, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v0, v1, v3, v2, v4}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_5

    goto :goto_0

    .line 1934
    :cond_5
    const-string/jumbo v1, "unbalanced braces"

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-static {v1, p0, v0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-void

    .line 1960
    :cond_6
    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    invoke-static {v3, v0, v1, v2}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v0

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1979
    :cond_7
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-lt v0, v1, :cond_0

    :cond_8
    :pswitch_2
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x25
        :pswitch_2
        :pswitch_2
        :pswitch_0
        :pswitch_1
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x2a
        :pswitch_2
        :pswitch_2
        :pswitch_2
    .end packed-switch
.end method

.method public captureToNextTokenJunction()V
    .locals 6

    .line 2081
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_3

    .line 2082
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v3, v2, v0

    const/16 v4, 0x28

    if-eq v3, v4, :cond_3

    const/16 v4, 0x2f

    const/16 v5, 0x5b

    if-eq v3, v4, :cond_1

    if-eq v3, v5, :cond_2

    const/16 v0, 0x7b

    if-eq v3, v0, :cond_3

    .line 2092
    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_1

    .line 2095
    :cond_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    :cond_1
    add-int/lit8 v3, v0, 0x1

    .line 2087
    aget-char v3, v2, v3

    const/16 v4, 0x2a

    if-ne v3, v4, :cond_2

    goto :goto_1

    .line 2089
    :cond_2
    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v2, v0, v1, v5, v3}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    :cond_3
    :goto_1
    return-void
.end method

.method public expectEOS()V
    .locals 5

    .line 1810
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1811
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_6

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v0, v1, v0

    const/16 v1, 0x3b

    if-eq v0, v1, :cond_6

    const/16 v1, 0x21

    const/16 v2, 0x3d

    if-eq v0, v1, :cond_3

    const/16 v1, 0x26

    if-eq v0, v1, :cond_2

    const/16 v1, 0x2d

    if-eq v0, v1, :cond_1

    const/16 v3, 0x2f

    if-eq v0, v3, :cond_1

    const/16 v3, 0x7c

    if-eq v0, v3, :cond_0

    const/16 v3, 0x2a

    if-eq v0, v3, :cond_1

    const/16 v4, 0x2b

    if-eq v0, v4, :cond_1

    packed-switch v0, :pswitch_data_0

    goto :goto_0

    .line 1828
    :pswitch_0
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    if-eq v0, v3, :cond_6

    if-eq v0, v4, :cond_6

    if-eq v0, v1, :cond_6

    if-eq v0, v2, :cond_6

    goto :goto_0

    .line 1817
    :cond_0
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    if-ne v0, v3, :cond_4

    goto :goto_2

    .line 1842
    :cond_1
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    if-ne v0, v2, :cond_4

    goto :goto_2

    .line 1814
    :cond_2
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    if-ne v0, v1, :cond_4

    goto :goto_2

    .line 1820
    :cond_3
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    if-ne v0, v2, :cond_4

    goto :goto_2

    .line 1846
    :cond_4
    :goto_0
    new-instance v0, Lorg/mvel2/CompileException;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "expected end of statement but encountered: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 1847
    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v3, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ne v2, v3, :cond_5

    const-string v2, "<end of stream>"

    goto :goto_1

    :cond_5
    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v2, v3, v2

    invoke-static {v2}, Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;

    move-result-object v2

    :goto_1
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v0, v1, v2, p0}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    :cond_6
    :goto_2
    :pswitch_1
    return-void

    :pswitch_data_0
    .packed-switch 0x3c
        :pswitch_1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public expectNextChar_IW(C)V
    .locals 3

    .line 2246
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextNonBlank()I

    .line 2247
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    if-eq v0, v1, :cond_1

    .line 2248
    aget-char v0, v2, v0

    if-ne v0, p1, :cond_0

    return-void

    .line 2249
    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "unexpected character (\'"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v1, v1, v2

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    const-string v1, "\'); was expecting: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-static {p1, v0, p0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-void

    .line 2247
    :cond_1
    const-string/jumbo p1, "unexpected end of statement"

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-static {p1, v2, p0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    return-void
.end method

.method public getCursor()I
    .locals 0

    .line 2798
    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    return p0
.end method

.method public getExpression()[C
    .locals 0

    .line 2802
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    return-object p0
.end method

.method public handleSubstatement(Lorg/mvel2/ast/Substatement;)Lorg/mvel2/ast/ASTNode;
    .locals 2

    .line 1296
    invoke-virtual {p1}, Lorg/mvel2/ast/Substatement;->getStatement()Lorg/mvel2/compiler/ExecutableStatement;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Lorg/mvel2/ast/Substatement;->getStatement()Lorg/mvel2/compiler/ExecutableStatement;

    move-result-object v0

    invoke-interface {v0}, Lorg/mvel2/compiler/ExecutableStatement;->isLiteralOnly()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1297
    new-instance v0, Lorg/mvel2/ast/LiteralNode;

    invoke-virtual {p1}, Lorg/mvel2/ast/Substatement;->getStatement()Lorg/mvel2/compiler/ExecutableStatement;

    move-result-object p1

    const/4 v1, 0x0

    invoke-interface {p1, v1, v1, v1}, Lorg/mvel2/compiler/Accessor;->getValue(Ljava/lang/Object;Ljava/lang/Object;Lorg/mvel2/integration/VariableResolverFactory;)Ljava/lang/Object;

    move-result-object p1

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, p1, p0}, Lorg/mvel2/ast/LiteralNode;-><init>(Ljava/lang/Object;Lorg/mvel2/ParserContext;)V

    return-object v0

    :cond_0
    return-object p1
.end method

.method public handleUnion(Lorg/mvel2/ast/ASTNode;)Lorg/mvel2/ast/ASTNode;
    .locals 10

    .line 1311
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_3

    .line 1312
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1314
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    const/4 v2, -0x1

    if-ge v0, v1, :cond_2

    .line 1315
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v1, v0

    const/16 v3, 0x2e

    if-eq v1, v3, :cond_1

    const/16 v3, 0x5b

    if-eq v1, v3, :cond_0

    goto :goto_1

    :cond_0
    :goto_0
    move v5, v0

    goto :goto_2

    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    :goto_1
    move v5, v2

    :goto_2
    if-eq v5, v2, :cond_3

    .line 1325
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1326
    new-instance v3, Lorg/mvel2/ast/Union;

    iget-object v4, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, p0, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v9, p0, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move-object v8, p1

    invoke-direct/range {v3 .. v9}, Lorg/mvel2/ast/Union;-><init>([CIIILorg/mvel2/ast/ASTNode;Lorg/mvel2/ParserContext;)V

    iput-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    :cond_3
    move-object v8, p1

    .line 1330
    iput-object v8, p0, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v8
.end method

.method public ifThenElseBlockContinues()Z
    .locals 7

    .line 1779
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v1, v0, 0x4

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    const/4 v3, 0x0

    if-ge v1, v2, :cond_2

    .line 1780
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v1, v0

    const/16 v2, 0x3b

    const/4 v4, 0x1

    if-eq v1, v2, :cond_0

    sub-int/2addr v0, v4

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1781
    :cond_0
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1783
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v1, v0, 0x4

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ge v1, v2, :cond_2

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v2, v1, v0

    const/16 v5, 0x65

    if-ne v2, v5, :cond_2

    add-int/lit8 v2, v0, 0x1

    aget-char v2, v1, v2

    const/16 v6, 0x6c

    if-ne v2, v6, :cond_2

    add-int/lit8 v2, v0, 0x2

    aget-char v2, v1, v2

    const/16 v6, 0x73

    if-ne v2, v6, :cond_2

    add-int/lit8 v2, v0, 0x3

    aget-char v2, v1, v2

    if-ne v2, v5, :cond_2

    add-int/lit8 v0, v0, 0x4

    aget-char v0, v1, v0

    .line 1784
    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 p0, p0, 0x4

    aget-char p0, v0, p0

    const/16 v0, 0x7b

    if-ne p0, v0, :cond_2

    :cond_1
    return v4

    :cond_2
    return v3
.end method

.method public incNextNonBlank()I
    .locals 1

    .line 2222
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 2223
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextNonBlank()I

    move-result p0

    return p0
.end method

.method public isNextIdentifier()Z
    .locals 3

    .line 1857
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    const/4 v2, 0x1

    if-eq v0, v1, :cond_0

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v0, v1, v0

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_0

    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/2addr v0, v2

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    .line 1858
    :cond_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_1

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char p0, p0, v0

    invoke-static {p0}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result p0

    if-eqz p0, :cond_1

    return v2

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public isNextIdentifierOrLiteral()Z
    .locals 3

    .line 2206
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 2207
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    const/4 v2, 0x0

    if-ne v0, v1, :cond_0

    return v2

    .line 2209
    :cond_0
    :goto_0
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_1

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v1, v0

    invoke-static {v1}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v1

    if-eqz v1, :cond_1

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 2210
    :cond_1
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ne v0, v1, :cond_2

    return v2

    .line 2211
    :cond_2
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char p0, p0, v0

    .line 2212
    invoke-static {p0}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v0

    if-nez v0, :cond_4

    invoke-static {p0}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v0

    if-nez v0, :cond_4

    const/16 v0, 0x27

    if-eq p0, v0, :cond_4

    const/16 v0, 0x22

    if-ne p0, v0, :cond_3

    goto :goto_1

    :cond_3
    return v2

    :cond_4
    :goto_1
    const/4 p0, 0x1

    return p0
.end method

.method public isStatementNotManuallyTerminated()Z
    .locals 3

    .line 2261
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    const/4 v2, 0x0

    if-lt v0, v1, :cond_0

    return v2

    .line 2263
    :cond_0
    :goto_0
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_1

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v1, v0

    invoke-static {v1}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v1

    if-eqz v1, :cond_1

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 2264
    :cond_1
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_3

    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char p0, p0, v0

    const/16 v0, 0x3b

    if-eq p0, v0, :cond_2

    goto :goto_1

    :cond_2
    return v2

    :cond_3
    :goto_1
    const/4 p0, 0x1

    return p0
.end method

.method public lastNonWhite(C)Z
    .locals 3

    .line 1984
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    .line 1985
    :goto_0
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v2, v2, v0

    invoke-static {v2}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v2

    if-eqz v2, :cond_0

    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    .line 1986
    :cond_0
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char p0, p0, v0

    if-ne p1, p0, :cond_1

    return v1

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public lookAhead()C
    .locals 3

    .line 2179
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v1, v0, 0x1

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v1, v2, :cond_0

    .line 2180
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v0, v0, 0x1

    aget-char p0, p0, v0

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public lookAhead(I)C
    .locals 3

    .line 2194
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int v1, v0, p1

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-lt v1, v2, :cond_0

    const/4 p0, 0x0

    return p0

    .line 2196
    :cond_0
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/2addr v0, p1

    aget-char p0, p0, v0

    return p0
.end method

.method public lookBehind()C
    .locals 2

    .line 2169
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-ne v0, v1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 2170
    :cond_0
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v0, v0, -0x1

    aget-char p0, p0, v0

    return p0
.end method

.method public lookToLast()C
    .locals 2

    .line 2155
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-ne v0, v1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 2158
    :cond_0
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-eq v0, v1, :cond_1

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v0, v0, -0x1

    aget-char v1, v1, v0

    invoke-static {v1}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v1

    if-nez v1, :cond_0

    .line 2160
    :cond_1
    iget-object p0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char p0, p0, v0

    return p0
.end method

.method public nextNonBlank()I
    .locals 3

    .line 2232
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v1, v0, 0x1

    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ge v1, v2, :cond_1

    .line 2236
    :goto_0
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_0

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v1, v1, v0

    invoke-static {v1}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v1

    if-eqz v1, :cond_0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return v0

    .line 2233
    :cond_1
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    const-string/jumbo v1, "unexpected end of statement"

    invoke-static {v1, v0, p0}, Lorg/mvel2/ast/Sign$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CI)V

    const/4 p0, 0x0

    return p0
.end method

.method public nextToken()Lorg/mvel2/ast/ASTNode;
    .locals 33

    move-object/from16 v1, p0

    .line 252
    const-string/jumbo v2, "unexpected end of statement"

    const/4 v3, 0x0

    :try_start_0
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;
    :try_end_0
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_0 .. :try_end_0} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_0 .. :try_end_0} :catch_0

    :try_start_1
    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->isEmpty()Z

    move-result v0
    :try_end_1
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_1 .. :try_end_1} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_1 .. :try_end_1} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_1 .. :try_end_1} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_1 .. :try_end_1} :catch_0

    if-nez v0, :cond_1

    .line 253
    :try_start_2
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/mvel2/ast/ASTNode;

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 254
    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-lt v4, v5, :cond_0

    instance-of v4, v0, Lorg/mvel2/ast/EndOfStatement;

    if-eqz v4, :cond_0

    .line 255
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0
    :try_end_2
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_2 .. :try_end_2} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_2 .. :try_end_2} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_2 .. :try_end_2} :catch_2
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_2 .. :try_end_2} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_2 .. :try_end_2} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    goto/16 :goto_1b

    :catch_1
    move-exception v0

    goto/16 :goto_1c

    :catch_2
    move-exception v0

    move-object/from16 v16, v3

    goto/16 :goto_1d

    :catch_3
    move-exception v0

    goto/16 :goto_1e

    :cond_0
    return-object v0

    .line 261
    :cond_1
    :try_start_3
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-lt v0, v4, :cond_2

    return-object v3

    .line 277
    :cond_2
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I
    :try_end_3
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_3 .. :try_end_3} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_3 .. :try_end_3} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_3 .. :try_end_3} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_3 .. :try_end_3} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_3 .. :try_end_3} :catch_0

    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_3

    .line 278
    :try_start_4
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->isDebugSymbols()Z

    move-result v0

    iput-boolean v0, v1, Lorg/mvel2/compiler/AbstractParser;->debugSymbols:Z
    :try_end_4
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_4 .. :try_end_4} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_4 .. :try_end_4} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_4 .. :try_end_4} :catch_2
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_4 .. :try_end_4} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_4 .. :try_end_4} :catch_0

    .line 281
    :cond_3
    :try_start_5
    iget-boolean v0, v1, Lorg/mvel2/compiler/AbstractParser;->debugSymbols:Z
    :try_end_5
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_5 .. :try_end_5} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_5 .. :try_end_5} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_5 .. :try_end_5} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_5 .. :try_end_5} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_5 .. :try_end_5} :catch_0

    const/4 v4, 0x0

    const/4 v5, 0x1

    if-eqz v0, :cond_8

    .line 282
    :try_start_6
    iget-boolean v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastWasLineLabel:Z

    if-nez v0, :cond_7

    .line 283
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->getSourceFile()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_6

    .line 287
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->getSourceFile()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Lorg/mvel2/ParserContext;->isLineMapped(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_4

    .line 288
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->getSourceFile()Ljava/lang/String;

    move-result-object v6

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    invoke-virtual {v0, v6, v7}, Lorg/mvel2/ParserContext;->initLineMapping(Ljava/lang/String;[C)V

    .line 291
    :cond_4
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 293
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-lt v0, v6, :cond_5

    return-object v3

    .line 297
    :cond_5
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->getSourceFile()Ljava/lang/String;

    move-result-object v6

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v0, v6, v7}, Lorg/mvel2/ParserContext;->getLineFor(Ljava/lang/String;I)I

    move-result v0

    .line 299
    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v6}, Lorg/mvel2/ParserContext;->getSourceFile()Ljava/lang/String;

    move-result-object v7

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v8, v0}, Lorg/mvel2/ParserContext;->setLineCount(I)I

    move-result v8

    invoke-virtual {v6, v7, v8}, Lorg/mvel2/ParserContext;->isVisitedLine(Ljava/lang/String;I)Z

    move-result v6

    if-nez v6, :cond_8

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v6}, Lorg/mvel2/ParserContext;->isBlockSymbols()Z

    move-result v6

    if-nez v6, :cond_8

    .line 300
    iput-boolean v5, v1, Lorg/mvel2/compiler/AbstractParser;->lastWasLineLabel:Z

    .line 301
    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v4}, Lorg/mvel2/ParserContext;->getSourceFile()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5, v0}, Lorg/mvel2/ParserContext;->visitLine(Ljava/lang/String;I)V

    .line 303
    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    new-instance v5, Lorg/mvel2/ast/LineLabel;

    invoke-virtual {v4}, Lorg/mvel2/ParserContext;->getSourceFile()Ljava/lang/String;

    move-result-object v6

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v5, v6, v0, v7}, Lorg/mvel2/ast/LineLabel;-><init>(Ljava/lang/String;ILorg/mvel2/ParserContext;)V

    invoke-virtual {v4, v5}, Lorg/mvel2/ParserContext;->setLastLineLabel(Lorg/mvel2/ast/LineLabel;)Lorg/mvel2/ast/LineLabel;

    move-result-object v0

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 284
    :cond_6
    new-instance v0, Lorg/mvel2/CompileException;

    const-string/jumbo v4, "unable to produce debugging symbols: source name must be provided."

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v4, v5, v6}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 307
    :cond_7
    iput-boolean v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastWasLineLabel:Z

    iput-boolean v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastWasComment:Z
    :try_end_6
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_6 .. :try_end_6} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_6 .. :try_end_6} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_6 .. :try_end_6} :catch_2
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_6 .. :try_end_6} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_6 .. :try_end_6} :catch_0

    .line 314
    :cond_8
    :try_start_7
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 321
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    move v0, v4

    move v6, v0

    .line 324
    :goto_0
    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v7, v8, :cond_81

    .line 325
    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v7, v8, v7

    invoke-static {v7}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v7
    :try_end_7
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_7 .. :try_end_7} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_7 .. :try_end_7} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_7 .. :try_end_7} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_7 .. :try_end_7} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_7 .. :try_end_7} :catch_0

    if-eqz v7, :cond_a

    .line 327
    :try_start_8
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/2addr v0, v5

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 329
    :goto_1
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v7, :cond_9

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v0, v7, v0

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v0

    if-eqz v0, :cond_9

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/2addr v0, v5

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I
    :try_end_8
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_8 .. :try_end_8} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_8 .. :try_end_8} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_8 .. :try_end_8} :catch_2
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_8 .. :try_end_8} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_8 .. :try_end_8} :catch_0

    goto :goto_1

    :cond_9
    move v0, v5

    :cond_a
    const/16 v12, 0x5d

    const/16 v3, 0x7b

    move/from16 v17, v5

    const/16 v5, 0x5b

    const/16 v10, 0x22

    const/16 v11, 0x3d

    const/16 v21, 0x3

    const/4 v14, -0x1

    if-eqz v0, :cond_4a

    .line 339
    :try_start_9
    sget-object v13, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    new-instance v15, Ljava/lang/String;

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v9, v8

    invoke-direct {v15, v7, v8, v9}, Ljava/lang/String;-><init>([CII)V

    invoke-virtual {v13, v15}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v7

    const/16 v8, 0x65

    if-eqz v7, :cond_1e

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    aget-char v7, v7, v9

    invoke-static {v7}, Ljava/lang/Character;->isDigit(C)Z

    move-result v7

    if-nez v7, :cond_1e

    .line 340
    sget-object v7, Lorg/mvel2/compiler/AbstractParser;->OPERATORS:Ljava/util/HashMap;

    invoke-virtual {v7, v15}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/Integer;

    invoke-virtual {v7}, Ljava/lang/Integer;->intValue()I

    move-result v7

    const/16 v9, 0x1a

    if-eq v7, v9, :cond_1d

    if-eq v7, v10, :cond_11

    packed-switch v7, :pswitch_data_0

    packed-switch v7, :pswitch_data_1

    packed-switch v7, :pswitch_data_2

    goto/16 :goto_7

    .line 450
    :pswitch_0
    invoke-direct {v1, v8}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :catch_4
    move-exception v0

    const/16 v16, 0x0

    goto/16 :goto_1d

    :pswitch_1
    const/16 v0, 0x64

    .line 481
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 482
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v3, v3, 0x1

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    return-object v0

    .line 421
    :pswitch_2
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v0

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 422
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 423
    new-instance v3, Lorg/mvel2/ast/ReturnNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/ReturnNode;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 487
    :pswitch_3
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v3, v3, 0x1

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 490
    :goto_2
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 491
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 492
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 494
    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    if-eq v5, v3, :cond_c

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v5, v7, v5

    if-ne v5, v11, :cond_c

    .line 495
    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iput v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    if-eq v3, v5, :cond_b

    goto/16 :goto_d

    .line 496
    :cond_b
    new-instance v0, Lorg/mvel2/CompileException;

    const-string v3, "illegal use of reserved word: var"

    invoke-direct {v0, v3, v7, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 501
    :cond_c
    new-instance v5, Ljava/lang/String;

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v9, v3, v8

    invoke-direct {v5, v7, v8, v9}, Ljava/lang/String;-><init>([CII)V

    .line 502
    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v7, :cond_d

    invoke-virtual {v7, v5}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v7

    if-eq v7, v14, :cond_d

    .line 503
    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v18, Lorg/mvel2/ast/IndexedDeclTypedVarNode;

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v21, v3, v8

    const-class v22, Ljava/lang/Object;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move-object/from16 v23, v3

    move/from16 v19, v7

    move/from16 v20, v8

    invoke-direct/range {v18 .. v23}, Lorg/mvel2/ast/IndexedDeclTypedVarNode;-><init>(IIILjava/lang/Class;Lorg/mvel2/ParserContext;)V

    move-object/from16 v3, v18

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v5, v3}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    goto :goto_3

    .line 506
    :cond_d
    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    new-instance v18, Lorg/mvel2/ast/DeclTypedVarNode;

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v22, v3, v9

    const-class v23, Ljava/lang/Object;

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move/from16 v24, v3

    move-object/from16 v19, v5

    move-object/from16 v20, v8

    move/from16 v21, v9

    move-object/from16 v25, v10

    invoke-direct/range {v18 .. v25}, Lorg/mvel2/ast/DeclTypedVarNode;-><init>(Ljava/lang/String;[CIILjava/lang/Class;ILorg/mvel2/ParserContext;)V

    move-object/from16 v3, v18

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v7, v3}, Lorg/mvel2/util/ExecutionStack;->add(Ljava/lang/Object;)V

    .line 511
    :goto_3
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v3, v5, :cond_f

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v5, v5, v3

    const/16 v7, 0x2c

    if-eq v5, v7, :cond_e

    goto :goto_4

    :cond_e
    add-int/lit8 v3, v3, 0x1

    .line 513
    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 514
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 515
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    goto/16 :goto_2

    .line 519
    :cond_f
    :goto_4
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->splitAccumulator:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 416
    :pswitch_4
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v0

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 417
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 418
    new-instance v3, Lorg/mvel2/ast/AssertNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v6, v0, -0x1

    iput v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/AssertNode;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 461
    :pswitch_5
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v0

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 462
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 463
    new-instance v0, Lorg/mvel2/ast/ImportNode;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4, v5, v6}, Lorg/mvel2/ast/ImportNode;-><init>([CIILorg/mvel2/ParserContext;)V

    .line 465
    invoke-virtual {v0}, Lorg/mvel2/ast/ImportNode;->isPackageImport()Z

    move-result v3
    :try_end_9
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_9 .. :try_end_9} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_9 .. :try_end_9} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_9 .. :try_end_9} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_9 .. :try_end_9} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_9 .. :try_end_9} :catch_0

    .line 469
    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_10

    .line 466
    :try_start_a
    invoke-virtual {v0}, Lorg/mvel2/ast/ImportNode;->getPackageImport()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lorg/mvel2/ParserContext;->addPackageImport(Ljava/lang/String;)V

    goto :goto_5

    .line 469
    :cond_10
    invoke-virtual {v0}, Lorg/mvel2/ast/ImportNode;->getImportClass()Ljava/lang/Class;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0}, Lorg/mvel2/ast/ImportNode;->getImportClass()Ljava/lang/Class;

    move-result-object v5

    invoke-virtual {v4, v3, v5}, Lorg/mvel2/ParserContext;->addImport(Ljava/lang/String;Ljava/lang/Class;)V

    .line 471
    :goto_5
    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 474
    :pswitch_6
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v0

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 475
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 476
    new-instance v0, Lorg/mvel2/ast/StaticImportNode;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v5}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v5

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v5, v6

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4, v5, v6}, Lorg/mvel2/ast/StaticImportNode;-><init>([CIILorg/mvel2/ParserContext;)V

    .line 477
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ast/StaticImportNode;->getMethod()Ljava/lang/reflect/Method;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0}, Lorg/mvel2/ast/StaticImportNode;->getMethod()Ljava/lang/reflect/Method;

    move-result-object v5

    invoke-virtual {v3, v4, v5}, Lorg/mvel2/ParserContext;->addImport(Ljava/lang/String;Ljava/lang/reflect/Method;)V

    .line 478
    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :pswitch_7
    const/16 v0, 0x30

    .line 453
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 456
    :pswitch_8
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v0

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 457
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToNextTokenJunction()V

    .line 458
    new-instance v0, Lorg/mvel2/ast/IsDef;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v5, v4

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4, v5, v6}, Lorg/mvel2/ast/IsDef;-><init>([CIILorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :pswitch_9
    const/16 v0, 0x2000

    .line 444
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_a
    const/high16 v0, 0x10000

    .line 447
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_b
    const/high16 v0, 0x40000

    .line 441
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_c
    const/16 v0, 0x4000

    .line 438
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_d
    const v0, 0x8000

    .line 435
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 429
    :pswitch_e
    new-instance v0, Lorg/mvel2/CompileException;

    const-string v3, "else without if"

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    :pswitch_f
    const/16 v0, 0x800

    .line 426
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_10
    const/16 v0, 0x1000

    .line 432
    invoke-direct {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->captureCodeBlock(I)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 342
    :cond_11
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v4

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    aget-char v0, v0, v4

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v0

    if-eqz v0, :cond_1c

    .line 351
    :cond_12
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToNextTokenJunction()V

    .line 352
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 354
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-ge v0, v4, :cond_13

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v6, v6, v0

    if-eq v6, v5, :cond_12

    :cond_13
    if-ge v0, v4, :cond_14

    .line 359
    invoke-virtual {v1, v12}, Lorg/mvel2/compiler/AbstractParser;->lastNonWhite(C)Z

    move-result v0

    if-nez v0, :cond_14

    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 361
    :cond_14
    new-instance v0, Lorg/mvel2/ast/TypeDescriptor;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v6

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v6, v7

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    invoke-direct {v0, v4, v5, v6, v7}, Lorg/mvel2/ast/TypeDescriptor;-><init>([CIII)V

    .line 363
    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v4}, Lorg/mvel2/ParserContext;->getFunctions()Ljava/util/Map;

    move-result-object v4

    invoke-virtual {v0}, Lorg/mvel2/ast/TypeDescriptor;->getClassName()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v4, v5}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_15

    .line 364
    new-instance v3, Lorg/mvel2/ast/NewObjectPrototype;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ast/TypeDescriptor;->getClassName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Lorg/mvel2/ParserContext;->getFunction(Ljava/lang/String;)Lorg/mvel2/ast/Function;

    move-result-object v0

    invoke-direct {v3, v4, v0}, Lorg/mvel2/ast/NewObjectPrototype;-><init>(Lorg/mvel2/ParserContext;Lorg/mvel2/ast/Function;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 367
    :cond_15
    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ast/TypeDescriptor;->getClassName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Lorg/mvel2/ParserContext;->hasProtoImport(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_16

    .line 368
    new-instance v3, Lorg/mvel2/ast/NewPrototypeNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v3, v0, v4}, Lorg/mvel2/ast/NewPrototypeNode;-><init>(Lorg/mvel2/ast/TypeDescriptor;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 372
    :cond_16
    new-instance v4, Lorg/mvel2/ast/NewObjectNode;

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v4, v0, v5, v6}, Lorg/mvel2/ast/NewObjectNode;-><init>(Lorg/mvel2/ast/TypeDescriptor;ILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 374
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 375
    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v4, v5, :cond_1a

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v4, v5, v4

    if-ne v4, v3, :cond_1a

    .line 376
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    check-cast v3, Lorg/mvel2/ast/NewObjectNode;

    invoke-virtual {v3}, Lorg/mvel2/ast/NewObjectNode;->getTypeDescr()Lorg/mvel2/ast/TypeDescriptor;

    move-result-object v3

    invoke-virtual {v3}, Lorg/mvel2/ast/TypeDescriptor;->isUndimensionedArray()Z

    move-result v3

    if-eqz v3, :cond_19

    .line 382
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 383
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    invoke-virtual {v3}, Lorg/mvel2/ast/ASTNode;->getEgressType()Ljava/lang/Class;

    move-result-object v3
    :try_end_a
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_a .. :try_end_a} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_a .. :try_end_a} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_a .. :try_end_a} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_a .. :try_end_a} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_a .. :try_end_a} :catch_0

    if-nez v3, :cond_17

    .line 387
    :try_start_b
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v3, v0}, Lorg/mvel2/ast/TypeDescriptor;->getClassReference(Lorg/mvel2/ParserContext;Lorg/mvel2/ast/TypeDescriptor;)Ljava/lang/Class;

    move-result-object v3
    :try_end_b
    .catch Ljava/lang/ClassNotFoundException; {:try_start_b .. :try_end_b} :catch_5
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_b .. :try_end_b} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_b .. :try_end_b} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_b .. :try_end_b} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_b .. :try_end_b} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_b .. :try_end_b} :catch_0

    :cond_17
    move-object v8, v3

    goto :goto_6

    :catch_5
    move-exception v0

    .line 390
    :try_start_c
    new-instance v3, Lorg/mvel2/CompileException;

    const-string v4, "could not instantiate class"

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v3, v4, v5, v6, v0}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CILjava/lang/Throwable;)V

    throw v3

    .line 395
    :goto_6
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v5, v0, v5

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v0, v3, v4, v5, v6}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 396
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->tokenContinues()Z

    move-result v0

    if-eqz v0, :cond_18

    .line 397
    new-instance v3, Lorg/mvel2/ast/InlineCollectionNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v9}, Lorg/mvel2/ast/InlineCollectionNode;-><init>([CIIILjava/lang/Class;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 399
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 400
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 401
    new-instance v3, Lorg/mvel2/ast/Union;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    add-int/lit8 v5, v0, 0x1

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v9}, Lorg/mvel2/ast/Union;-><init>([CIIILorg/mvel2/ast/ASTNode;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 404
    :cond_18
    new-instance v3, Lorg/mvel2/ast/InlineCollectionNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v9}, Lorg/mvel2/ast/InlineCollectionNode;-><init>([CIIILjava/lang/Class;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 377
    :cond_19
    new-instance v0, Lorg/mvel2/CompileException;

    const-string v3, "conflicting syntax: dimensioned array with initializer block"

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 408
    :cond_1a
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    check-cast v0, Lorg/mvel2/ast/NewObjectNode;

    invoke-virtual {v0}, Lorg/mvel2/ast/NewObjectNode;->getTypeDescr()Lorg/mvel2/ast/TypeDescriptor;

    move-result-object v0

    invoke-virtual {v0}, Lorg/mvel2/ast/TypeDescriptor;->isUndimensionedArray()Z

    move-result v0

    if-nez v0, :cond_1b

    .line 411
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 413
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 409
    :cond_1b
    new-instance v0, Lorg/mvel2/CompileException;

    const-string v3, "array initializer expected"

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 343
    :cond_1c
    new-instance v0, Lorg/mvel2/CompileException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string/jumbo v4, "unexpected character (expected identifier): "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v4, v4, v5

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 522
    :cond_1d
    iput-boolean v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 523
    new-instance v0, Lorg/mvel2/ast/OperatorNode;

    invoke-static {v9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4, v5, v6}, Lorg/mvel2/ast/OperatorNode;-><init>(Ljava/lang/Integer;[CILorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 528
    :cond_1e
    :goto_7
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 534
    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v7, v9, :cond_1f

    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v12, v10, v7

    const/16 v13, 0x28

    if-ne v12, v13, :cond_1f

    .line 535
    iget-object v12, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v10, v7, v9, v13, v12}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v7

    add-int/lit8 v7, v7, 0x1

    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 543
    :cond_1f
    :goto_8
    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v7, v9, :cond_49

    .line 544
    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v12, v10, v7

    if-eq v12, v5, :cond_48

    const/16 v13, 0x5e

    if-eq v12, v13, :cond_45

    const/16 v13, 0x7e

    if-eq v12, v13, :cond_44

    const/16 v13, 0xbb

    if-eq v12, v13, :cond_45

    if-eq v12, v3, :cond_42

    const/16 v13, 0x7c

    if-eq v12, v13, :cond_45

    const/16 v13, 0xab

    if-eq v12, v13, :cond_45

    const/16 v13, 0xac

    if-eq v12, v13, :cond_45

    packed-switch v12, :pswitch_data_3

    packed-switch v12, :pswitch_data_4

    const/16 v10, 0x45

    packed-switch v12, :pswitch_data_5

    packed-switch v12, :pswitch_data_6

    if-eq v7, v9, :cond_49

    .line 865
    invoke-static {v12}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v7

    if-eqz v7, :cond_22

    if-nez v6, :cond_20

    goto/16 :goto_11

    .line 869
    :cond_20
    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v7, v7, 0x1

    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 870
    :goto_9
    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v7, v9, :cond_21

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v7, v9, v7

    invoke-static {v7}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v7

    if-eqz v7, :cond_21

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v7, v7, 0x1

    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_9

    :cond_21
    :goto_a
    move-object/from16 v30, v15

    goto/16 :goto_10

    .line 872
    :cond_22
    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v9, v7, 0x1

    iget v10, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v9, v10, :cond_23

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v7, v7, 0x1

    aget-char v7, v9, v7

    invoke-static {v7}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v7

    if-eqz v7, :cond_23

    goto/16 :goto_11

    .line 876
    :cond_23
    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v7, v7, 0x1

    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_a

    .line 553
    :pswitch_11
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookToLast()C

    move-result v6

    const/16 v7, 0x2e

    if-eq v6, v7, :cond_24

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-ne v6, v7, :cond_49

    .line 555
    :cond_24
    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v6, v6, 0x1

    iput v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    move/from16 v6, v17

    goto/16 :goto_8

    .line 719
    :pswitch_12
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    const/16 v3, 0x3e

    if-ne v0, v3, :cond_49

    const/4 v0, 0x2

    .line 720
    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead(I)C

    move-result v3

    if-ne v3, v11, :cond_27

    .line 721
    new-instance v0, Ljava/lang/String;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v5}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v5, v7

    invoke-direct {v0, v3, v4, v5}, Ljava/lang/String;-><init>([CII)V

    .line 723
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v3, v3, 0x3

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 724
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    if-eqz v6, :cond_25

    .line 727
    new-instance v24, Lorg/mvel2/ast/DeepAssignmentNode;

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v27, v4, v3

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0x9

    move-object/from16 v25, v0

    move/from16 v26, v3

    move/from16 v28, v4

    move-object/from16 v31, v5

    move-object/from16 v30, v15

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/DeepAssignmentNode;-><init>([CIIIILjava/lang/String;Lorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 730
    :cond_25
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_26

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v9

    if-eq v9, v14, :cond_26

    .line 731
    new-instance v4, Lorg/mvel2/ast/IndexedOperativeAssign;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    iget v10, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v11, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v8, 0x9

    invoke-direct/range {v4 .. v11}, Lorg/mvel2/ast/IndexedOperativeAssign;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 735
    :cond_26
    new-instance v23, Lorg/mvel2/ast/OperativeAssign;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v27, v5, v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v28, 0x9

    move-object/from16 v24, v0

    move-object/from16 v25, v3

    move/from16 v26, v4

    move/from16 v29, v5

    move-object/from16 v30, v6

    invoke-direct/range {v23 .. v30}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v23

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :cond_27
    move-object/from16 v30, v15

    const/4 v0, 0x2

    .line 739
    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead(I)C

    move-result v0

    const/16 v3, 0x3e

    if-ne v0, v3, :cond_49

    move/from16 v0, v21

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->lookAhead(I)C

    move-result v0

    if-ne v0, v11, :cond_49

    .line 740
    new-instance v0, Ljava/lang/String;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v5}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v5, v7

    invoke-direct {v0, v3, v4, v5}, Ljava/lang/String;-><init>([CII)V

    .line 742
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v3, v3, 0x4

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 743
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    if-eqz v6, :cond_28

    .line 746
    new-instance v24, Lorg/mvel2/ast/DeepAssignmentNode;

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v27, v4, v3

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0xb

    move-object/from16 v25, v0

    move/from16 v26, v3

    move/from16 v28, v4

    move-object/from16 v31, v5

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/DeepAssignmentNode;-><init>([CIIIILjava/lang/String;Lorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 749
    :cond_28
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_29

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v9

    if-eq v9, v14, :cond_29

    .line 750
    new-instance v4, Lorg/mvel2/ast/IndexedOperativeAssign;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    iget v10, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v11, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v8, 0xb

    invoke-direct/range {v4 .. v11}, Lorg/mvel2/ast/IndexedOperativeAssign;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 754
    :cond_29
    new-instance v17, Lorg/mvel2/ast/OperativeAssign;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v21, v5, v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v22, 0xb

    move-object/from16 v18, v0

    move-object/from16 v19, v3

    move/from16 v20, v4

    move/from16 v23, v5

    move-object/from16 v24, v6

    invoke-direct/range {v17 .. v24}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v17

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :pswitch_13
    move-object v0, v15

    .line 788
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3
    :try_end_c
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_c .. :try_end_c} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_c .. :try_end_c} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_c .. :try_end_c} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_c .. :try_end_c} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_c .. :try_end_c} :catch_0

    const-string v5, "\'"

    const-string/jumbo v7, "unexpected symbol \'"

    const/16 v8, 0x2b

    if-ne v3, v8, :cond_2c

    .line 789
    :try_start_d
    new-instance v0, Ljava/lang/String;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v6

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v6, v8

    invoke-direct {v0, v3, v4, v6}, Ljava/lang/String;-><init>([CII)V

    .line 791
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v3, v3, 0x2

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 793
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->isNextIdentifierOrLiteral()Z

    move-result v3

    if-eqz v3, :cond_2b

    .line 797
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 799
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_2a

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v9

    if-eq v9, v14, :cond_2a

    .line 800
    new-instance v4, Lorg/mvel2/ast/IndexedOperativeAssign;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    iget v10, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v11, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/4 v8, 0x0

    invoke-direct/range {v4 .. v11}, Lorg/mvel2/ast/IndexedOperativeAssign;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 804
    :cond_2a
    new-instance v24, Lorg/mvel2/ast/OperativeAssign;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v28, v5, v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0x0

    move-object/from16 v25, v0

    move-object/from16 v26, v3

    move/from16 v27, v4

    move/from16 v30, v5

    move-object/from16 v31, v6

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 794
    :cond_2b
    new-instance v0, Lorg/mvel2/CompileException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v4, v4, v6

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 808
    :cond_2c
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    const/16 v8, 0x2d

    if-ne v3, v8, :cond_2f

    .line 809
    new-instance v0, Ljava/lang/String;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v6}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v6

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v6, v8

    invoke-direct {v0, v3, v4, v6}, Ljava/lang/String;-><init>([CII)V

    .line 811
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v3, v3, 0x2

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 813
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->isNextIdentifierOrLiteral()Z

    move-result v3

    if-eqz v3, :cond_2e

    .line 817
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 819
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_2d

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v9

    if-eq v9, v14, :cond_2d

    .line 820
    new-instance v4, Lorg/mvel2/ast/IndexedOperativeAssign;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    iget v10, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v11, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/4 v8, 0x1

    invoke-direct/range {v4 .. v11}, Lorg/mvel2/ast/IndexedOperativeAssign;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 824
    :cond_2d
    new-instance v24, Lorg/mvel2/ast/OperativeAssign;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v28, v5, v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0x1

    move-object/from16 v25, v0

    move-object/from16 v26, v3

    move/from16 v27, v4

    move/from16 v30, v5

    move-object/from16 v31, v6

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 814
    :cond_2e
    new-instance v0, Lorg/mvel2/CompileException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v4, v4, v6

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 828
    :cond_2f
    iget-boolean v3, v1, Lorg/mvel2/compiler/AbstractParser;->greedy:Z

    if-eqz v3, :cond_49

    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    if-eq v3, v11, :cond_49

    .line 829
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v3, v3, 0x1

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    if-eqz v6, :cond_30

    .line 832
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 834
    new-instance v7, Lorg/mvel2/ast/DeepAssignmentNode;

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v10, v0, v9

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v11, v0, 0x80

    iget-object v12, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v7 .. v12}, Lorg/mvel2/ast/DeepAssignmentNode;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v7

    .line 837
    :cond_30
    iget-boolean v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    if-eqz v3, :cond_31

    .line 838
    invoke-direct {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->procTypedNode(Z)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 840
    :cond_31
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_33

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v9

    if-eq v9, v14, :cond_33

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    .line 841
    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->isIndexAllocation()Z

    move-result v0

    if-eqz v0, :cond_33

    .line 842
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 844
    new-instance v4, Lorg/mvel2/ast/IndexedAssignmentNode;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v6

    iput v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 845
    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v0

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v7, v0, v3

    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v8, 0x80

    invoke-direct/range {v4 .. v10}, Lorg/mvel2/ast/IndexedAssignmentNode;-><init>([CIIIILorg/mvel2/ParserContext;)V

    if-ne v9, v14, :cond_32

    .line 849
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v4}, Lorg/mvel2/ast/IndexedAssignmentNode;->getVarName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lorg/mvel2/ParserContext;->addIndexedInput(Ljava/lang/String;)V

    .line 850
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0, v3}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {v4, v0}, Lorg/mvel2/ast/IndexedAssignmentNode;->setRegister(I)V

    .line 852
    :cond_32
    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 855
    :cond_33
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    .line 857
    new-instance v3, Lorg/mvel2/ast/AssignmentNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    or-int/lit16 v7, v0, 0x80

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/AssignmentNode;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    :pswitch_14
    move-object v0, v15

    .line 697
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    const/16 v4, 0x3c

    if-ne v3, v4, :cond_49

    const/4 v3, 0x2

    invoke-virtual {v1, v3}, Lorg/mvel2/compiler/AbstractParser;->lookAhead(I)C

    move-result v3

    if-ne v3, v11, :cond_49

    .line 698
    new-instance v3, Ljava/lang/String;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v7}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v7

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v7, v8

    invoke-direct {v3, v4, v5, v7}, Ljava/lang/String;-><init>([CII)V

    .line 700
    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v21, 0x3

    add-int/lit8 v4, v4, 0x3

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 701
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    if-eqz v6, :cond_34

    .line 704
    new-instance v24, Lorg/mvel2/ast/DeepAssignmentNode;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v27, v5, v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0xa

    move-object/from16 v30, v0

    move-object/from16 v25, v3

    move/from16 v26, v4

    move/from16 v28, v5

    move-object/from16 v31, v6

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/DeepAssignmentNode;-><init>([CIIIILjava/lang/String;Lorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 707
    :cond_34
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v0, :cond_35

    invoke-virtual {v0, v3}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v9

    if-eq v9, v14, :cond_35

    .line 708
    new-instance v4, Lorg/mvel2/ast/IndexedOperativeAssign;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    iget v10, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v11, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v8, 0xa

    invoke-direct/range {v4 .. v11}, Lorg/mvel2/ast/IndexedOperativeAssign;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 712
    :cond_35
    new-instance v22, Lorg/mvel2/ast/OperativeAssign;

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v26, v5, v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v27, 0xa

    move-object/from16 v24, v0

    move-object/from16 v23, v3

    move/from16 v25, v4

    move/from16 v28, v5

    move-object/from16 v29, v6

    invoke-direct/range {v22 .. v29}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v22

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :pswitch_15
    move-object/from16 v30, v15

    add-int/lit8 v7, v7, 0x1

    .line 547
    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 548
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    move/from16 v6, v17

    move-object/from16 v15, v30

    :goto_b
    const/16 v21, 0x3

    goto/16 :goto_8

    .line 609
    :pswitch_16
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    const/16 v3, 0x2d

    if-eq v0, v3, :cond_3a

    if-eq v0, v11, :cond_37

    .line 644
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v0

    if-eqz v0, :cond_49

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    move/from16 v3, v17

    if-le v0, v3, :cond_49

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v5, v0, -0x1

    aget-char v5, v3, v5

    if-eq v5, v10, :cond_36

    add-int/lit8 v5, v0, -0x1

    aget-char v5, v3, v5

    if-ne v5, v8, :cond_49

    :cond_36
    add-int/lit8 v0, v0, -0x2

    aget-char v0, v3, v0

    .line 646
    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v0

    if-eqz v0, :cond_49

    .line 647
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    :goto_c
    const/4 v0, 0x1

    :goto_d
    const/4 v3, 0x0

    const/4 v5, 0x1

    goto/16 :goto_0

    .line 625
    :cond_37
    new-instance v4, Ljava/lang/String;

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v5}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v5, v7

    invoke-direct {v4, v0, v3, v5}, Ljava/lang/String;-><init>([CII)V

    .line 626
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v0, v0, 0x2

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 628
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    if-eqz v6, :cond_38

    .line 631
    new-instance v3, Lorg/mvel2/ast/DeepOperativeAssignmentNode;

    move-object v9, v4

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v5

    iput v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v0

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v6, v0, v6

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/4 v8, 0x1

    invoke-direct/range {v3 .. v10}, Lorg/mvel2/ast/DeepOperativeAssignmentNode;-><init>([CIIIILjava/lang/String;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    :cond_38
    move-object v9, v4

    .line 634
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v0, :cond_39

    invoke-virtual {v0, v9}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v0

    if-eq v0, v14, :cond_39

    .line 635
    new-instance v17, Lorg/mvel2/ast/IndexedOperativeAssign;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v20, v5, v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v21, 0x1

    move/from16 v22, v0

    move-object/from16 v18, v3

    move/from16 v19, v4

    move/from16 v23, v5

    move-object/from16 v24, v6

    invoke-direct/range {v17 .. v24}, Lorg/mvel2/ast/IndexedOperativeAssign;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v17

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 639
    :cond_39
    new-instance v3, Lorg/mvel2/ast/OperativeAssign;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    move-object v4, v9

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/4 v8, 0x1

    invoke-direct/range {v3 .. v10}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 611
    :cond_3a
    new-instance v0, Ljava/lang/String;

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v4

    invoke-direct {v1, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->subArray(II)[C

    move-result-object v3

    invoke-direct {v0, v3}, Ljava/lang/String;-><init>([C)V

    .line 612
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_3b

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v3

    if-eq v3, v14, :cond_3b

    .line 613
    new-instance v0, Lorg/mvel2/ast/IndexedPostFixDecNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4}, Lorg/mvel2/ast/IndexedPostFixDecNode;-><init>(ILorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    goto :goto_e

    .line 616
    :cond_3b
    new-instance v3, Lorg/mvel2/ast/PostFixDecNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v3, v0, v4}, Lorg/mvel2/ast/PostFixDecNode;-><init>(Ljava/lang/String;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 618
    :goto_e
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v0, v0, 0x2

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 620
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->expectEOS()V

    .line 622
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 563
    :pswitch_17
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    const/16 v5, 0x2b

    if-eq v3, v5, :cond_40

    if-eq v3, v11, :cond_3d

    .line 599
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v3

    if-eqz v3, :cond_49

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/4 v5, 0x1

    if-le v3, v5, :cond_49

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v7, v3, -0x1

    aget-char v7, v5, v7

    if-eq v7, v10, :cond_3c

    add-int/lit8 v7, v3, -0x1

    aget-char v7, v5, v7

    if-ne v7, v8, :cond_49

    :cond_3c
    add-int/lit8 v3, v3, -0x2

    aget-char v3, v5, v3

    .line 601
    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v3

    if-eqz v3, :cond_49

    .line 602
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v3, v3, 0x1

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_d

    .line 580
    :cond_3d
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v4, v3

    invoke-static {v0, v3, v4}, Lorg/mvel2/util/ParseTools;->createStringTrimmed([CII)Ljava/lang/String;

    move-result-object v30

    .line 581
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v0, v0, 0x2

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 583
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    if-eqz v6, :cond_3e

    .line 586
    new-instance v24, Lorg/mvel2/ast/DeepOperativeAssignmentNode;

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-virtual {v1, v3}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v3

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v27, v4, v5

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0x0

    move-object/from16 v25, v0

    move/from16 v26, v3

    move/from16 v28, v4

    move-object/from16 v31, v5

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/DeepOperativeAssignmentNode;-><init>([CIIIILjava/lang/String;Lorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :cond_3e
    move-object/from16 v0, v30

    .line 589
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_3f

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v3

    if-eq v3, v14, :cond_3f

    .line 590
    new-instance v24, Lorg/mvel2/ast/IndexedAssignmentNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v27, v6, v5

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0x0

    move-object/from16 v30, v0

    move/from16 v31, v3

    move-object/from16 v25, v4

    move/from16 v26, v5

    move/from16 v28, v6

    move-object/from16 v32, v7

    invoke-direct/range {v24 .. v32}, Lorg/mvel2/ast/IndexedAssignmentNode;-><init>([CIIIILjava/lang/String;ILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :cond_3f
    move-object/from16 v30, v0

    .line 594
    new-instance v24, Lorg/mvel2/ast/OperativeAssign;

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-virtual {v1, v3}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v3

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v28, v4, v5

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v29, 0x0

    move-object/from16 v26, v0

    move/from16 v27, v3

    move-object/from16 v31, v5

    move-object/from16 v25, v30

    move/from16 v30, v4

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 565
    :cond_40
    new-instance v0, Ljava/lang/String;

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v4

    invoke-direct {v1, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->subArray(II)[C

    move-result-object v3

    invoke-direct {v0, v3}, Ljava/lang/String;-><init>([C)V

    .line 566
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_41

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v3

    if-eq v3, v14, :cond_41

    .line 567
    new-instance v0, Lorg/mvel2/ast/IndexedPostFixIncNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4}, Lorg/mvel2/ast/IndexedPostFixIncNode;-><init>(ILorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    goto :goto_f

    .line 570
    :cond_41
    new-instance v3, Lorg/mvel2/ast/PostFixIncNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v3, v0, v4}, Lorg/mvel2/ast/PostFixIncNode;-><init>(Ljava/lang/String;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 573
    :goto_f
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v0, v0, 0x2

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 575
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->expectEOS()V

    .line 577
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :pswitch_18
    move-object/from16 v30, v15

    .line 762
    iget-object v12, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    const/16 v13, 0x28

    invoke-static {v10, v7, v9, v13, v12}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v7

    const/16 v17, 0x1

    add-int/lit8 v7, v7, 0x1

    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_10

    :cond_42
    move-object/from16 v30, v15

    if-nez v6, :cond_43

    goto/16 :goto_11

    .line 771
    :cond_43
    iget-object v12, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v10, v7, v9, v3, v12}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v7

    const/16 v17, 0x1

    add-int/lit8 v7, v7, 0x1

    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_10

    .line 775
    :cond_44
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    if-ne v0, v11, :cond_49

    .line 777
    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 778
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    const/16 v23, 0x2

    add-int/lit8 v0, v0, 0x2

    .line 779
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 781
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 783
    new-instance v3, Lorg/mvel2/ast/RegExMatch;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v9, v0, v8

    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v10}, Lorg/mvel2/ast/RegExMatch;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 675
    :cond_45
    :pswitch_19
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    if-ne v0, v11, :cond_49

    .line 676
    new-instance v0, Ljava/lang/String;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v5}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v5, v7

    invoke-direct {v0, v3, v4, v5}, Ljava/lang/String;-><init>([CII)V

    .line 678
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v3, v3, 0x2

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 679
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOS()V

    if-eqz v6, :cond_46

    .line 682
    new-instance v24, Lorg/mvel2/ast/DeepOperativeAssignmentNode;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-virtual {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v4

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-virtual {v1, v5}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v5

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v27, v5, v6

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    .line 683
    invoke-static {v12}, Lorg/mvel2/util/ParseTools;->opLookup(C)I

    move-result v29

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move-object/from16 v30, v0

    move-object/from16 v25, v3

    move/from16 v26, v4

    move/from16 v28, v5

    move-object/from16 v31, v6

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/DeepOperativeAssignmentNode;-><init>([CIIIILjava/lang/String;Lorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 685
    :cond_46
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_47

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v9

    if-eq v9, v14, :cond_47

    .line 686
    new-instance v4, Lorg/mvel2/ast/IndexedOperativeAssign;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    .line 687
    invoke-static {v12}, Lorg/mvel2/util/ParseTools;->opLookup(C)I

    move-result v8

    iget v10, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v11, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v4 .. v11}, Lorg/mvel2/ast/IndexedOperativeAssign;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    .line 690
    :cond_47
    new-instance v24, Lorg/mvel2/ast/OperativeAssign;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v28, v5, v4

    .line 691
    invoke-static {v12}, Lorg/mvel2/util/ParseTools;->opLookup(C)I

    move-result v29

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move-object/from16 v25, v0

    move-object/from16 v26, v3

    move/from16 v27, v4

    move/from16 v30, v5

    move-object/from16 v31, v6

    invoke-direct/range {v24 .. v31}, Lorg/mvel2/ast/OperativeAssign;-><init>(Ljava/lang/String;[CIIIILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v24

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :cond_48
    move-object/from16 v30, v15

    .line 766
    iget-object v12, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v10, v7, v9, v5, v12}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v7

    const/16 v17, 0x1

    add-int/lit8 v7, v7, 0x1

    iput v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    :goto_10
    move-object/from16 v15, v30

    const/16 v17, 0x1

    goto/16 :goto_b

    .line 888
    :cond_49
    :goto_11
    :pswitch_1a
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->trimWhitespace()V

    .line 890
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v0, v3}, Lorg/mvel2/compiler/AbstractParser;->createPropertyToken(II)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 893
    :cond_4a
    iget-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v9, v7, v8

    if-eq v9, v5, :cond_7e

    if-eq v9, v12, :cond_7d

    const/16 v13, 0x5e

    if-eq v9, v13, :cond_7c

    packed-switch v9, :pswitch_data_7

    packed-switch v9, :pswitch_data_8

    packed-switch v9, :pswitch_data_9

    packed-switch v9, :pswitch_data_a

    packed-switch v9, :pswitch_data_b

    add-int/lit8 v8, v8, 0x1

    .line 1268
    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_d

    :pswitch_1b
    add-int/lit8 v0, v8, 0x1

    .line 1201
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v8, v8, -0x1

    if-nez v8, :cond_4b

    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookBehind()C

    move-result v0

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v0

    if-nez v0, :cond_4c

    :cond_4b
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v0, v0, v3

    .line 1202
    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v0

    if-eqz v0, :cond_4c

    .line 1203
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1204
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1205
    new-instance v3, Lorg/mvel2/ast/Invert;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/Invert;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 1207
    :cond_4c
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v4, v0, v3

    const/16 v13, 0x28

    if-ne v4, v13, :cond_4d

    add-int/lit8 v0, v3, -0x1

    .line 1208
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1209
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1210
    new-instance v4, Lorg/mvel2/ast/Invert;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v7, v0, v6

    iget v8, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v4 .. v9}, Lorg/mvel2/ast/Invert;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v4

    :cond_4d
    if-ne v4, v11, :cond_4e

    const/16 v17, 0x1

    add-int/lit8 v3, v3, 0x1

    .line 1213
    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1214
    :cond_4e
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v0, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_1c
    add-int/lit8 v0, v8, 0x1

    .line 1192
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v3, v7, v0

    const/16 v13, 0x7c

    if-ne v3, v13, :cond_4f

    .line 1193
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v23, 0x2

    add-int/lit8 v8, v8, 0x2

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v8}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1196
    :cond_4f
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v1, v7, v3, v0}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 909
    :pswitch_1d
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 910
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 912
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v0, :cond_50

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->getInterceptors()Ljava/util/Map;

    move-result-object v0

    if-eqz v0, :cond_50

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v0}, Lorg/mvel2/ParserContext;->getInterceptors()Ljava/util/Map;

    move-result-object v0

    new-instance v3, Ljava/lang/String;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v6, v5

    invoke-direct {v3, v4, v5, v6}, Ljava/lang/String;-><init>([CII)V

    .line 913
    invoke-interface {v0, v3}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_50

    .line 918
    new-instance v0, Lorg/mvel2/ast/InterceptorWrapper;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-virtual {v4}, Lorg/mvel2/ParserContext;->getInterceptors()Ljava/util/Map;

    move-result-object v4

    invoke-interface {v4, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/mvel2/integration/Interceptor;

    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v4

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/ast/InterceptorWrapper;-><init>(Lorg/mvel2/integration/Interceptor;Lorg/mvel2/ast/ASTNode;Lorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 914
    :cond_50
    new-instance v0, Lorg/mvel2/CompileException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "reference to undefined interceptor: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    new-instance v4, Ljava/lang/String;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v7, v6

    invoke-direct {v4, v5, v6, v7}, Ljava/lang/String;-><init>([CII)V

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 990
    :pswitch_1e
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-ne v8, v3, :cond_7c

    add-int/lit8 v8, v8, 0x1

    .line 991
    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_d

    :pswitch_1f
    add-int/lit8 v0, v8, 0x1

    .line 1144
    aget-char v0, v7, v0

    if-eq v0, v11, :cond_53

    const/16 v3, 0x3e

    if-eq v0, v3, :cond_51

    .line 1151
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v17, 0x1

    add-int/lit8 v8, v8, 0x1

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v8}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :cond_51
    add-int/lit8 v0, v8, 0x2

    .line 1146
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v0, v7, v0

    const/16 v3, 0x3e

    if-ne v0, v3, :cond_52

    const/16 v21, 0x3

    add-int/lit8 v8, v8, 0x3

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1147
    :cond_52
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v3}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1149
    :cond_53
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v23, 0x2

    add-int/lit8 v8, v8, 0x2

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v8}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 922
    :pswitch_20
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v23, 0x2

    add-int/lit8 v8, v8, 0x2

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v8}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_21
    add-int/lit8 v0, v8, 0x1

    .line 1156
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v3, v7, v0

    const/16 v4, 0x3c

    if-ne v3, v4, :cond_55

    add-int/lit8 v0, v8, 0x2

    .line 1157
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v0, v7, v0

    if-ne v0, v4, :cond_54

    const/16 v21, 0x3

    add-int/lit8 v8, v8, 0x3

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1158
    :cond_54
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v3}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :cond_55
    if-ne v3, v11, :cond_56

    .line 1161
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v23, 0x2

    add-int/lit8 v8, v8, 0x2

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v8}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1164
    :cond_56
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v1, v7, v3, v0}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_22
    const/16 v17, 0x1

    add-int/lit8 v8, v8, 0x1

    .line 985
    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 986
    iput-boolean v4, v1, Lorg/mvel2/compiler/AbstractParser;->lastWasIdentifier:Z

    .line 987
    new-instance v0, Lorg/mvel2/ast/EndOfStatement;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3}, Lorg/mvel2/ast/EndOfStatement;-><init>(Lorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :pswitch_23
    add-int/lit8 v8, v8, 0x1

    .line 895
    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 896
    aget-char v0, v7, v8

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v0

    if-eqz v0, :cond_57

    goto/16 :goto_c

    .line 900
    :cond_57
    invoke-virtual {v1, v3}, Lorg/mvel2/compiler/AbstractParser;->expectNextChar_IW(C)V

    .line 902
    new-instance v7, Lorg/mvel2/ast/ThisWithNode;

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v4, v0, v9

    const/16 v17, 0x1

    add-int/lit8 v10, v4, -0x1

    add-int/lit8 v11, v0, 0x1

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    .line 904
    invoke-static {v8, v0, v4, v3, v5}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v0

    add-int/lit8 v3, v0, 0x1

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v12, v0, -0x2

    iget v13, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v14, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v7 .. v14}, Lorg/mvel2/ast/ThisWithNode;-><init>([CIIIIILorg/mvel2/ParserContext;)V

    iput-object v7, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v7

    .line 925
    :pswitch_24
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3
    :try_end_d
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_d .. :try_end_d} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_d .. :try_end_d} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_d .. :try_end_d} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_d .. :try_end_d} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_d .. :try_end_d} :catch_0

    .line 939
    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v8, 0x2d

    if-ne v3, v8, :cond_59

    const/16 v23, 0x2

    add-int/lit8 v5, v5, 0x2

    .line 926
    :try_start_e
    iput v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 927
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 928
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 929
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureIdentifier()V

    .line 931
    new-instance v0, Ljava/lang/String;

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->subArray(II)[C

    move-result-object v3

    invoke-direct {v0, v3}, Ljava/lang/String;-><init>([C)V

    .line 932
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_58

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v3

    if-eq v3, v14, :cond_58

    .line 933
    new-instance v0, Lorg/mvel2/ast/IndexedPreFixDecNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4}, Lorg/mvel2/ast/IndexedPreFixDecNode;-><init>(ILorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 936
    :cond_58
    new-instance v3, Lorg/mvel2/ast/PreFixDecNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v3, v0, v4}, Lorg/mvel2/ast/PreFixDecNode;-><init>(Ljava/lang/String;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 939
    :cond_59
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-eq v5, v3, :cond_5a

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    if-eqz v3, :cond_5b

    instance-of v5, v3, Lorg/mvel2/ast/BooleanNode;

    if-nez v5, :cond_5a

    .line 940
    invoke-virtual {v3}, Lorg/mvel2/ast/ASTNode;->isOperator()Z

    move-result v3

    if-eqz v3, :cond_5b

    .line 941
    :cond_5a
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v3

    if-nez v3, :cond_5b

    .line 943
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 944
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 945
    new-instance v3, Lorg/mvel2/ast/Sign;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/Sign;-><init>([CIIILorg/mvel2/ParserContext;)V

    return-object v3

    .line 947
    :cond_5b
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-eq v3, v5, :cond_5c

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    if-eqz v3, :cond_5c

    instance-of v5, v3, Lorg/mvel2/ast/BooleanNode;

    if-nez v5, :cond_5c

    .line 948
    invoke-virtual {v3}, Lorg/mvel2/ast/ASTNode;->isOperator()Z

    move-result v3

    if-eqz v3, :cond_5d

    .line 949
    :cond_5c
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v3

    if-nez v3, :cond_5e

    .line 951
    :cond_5d
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v0, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 953
    :cond_5e
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v5, v3, -0x1

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->start:I

    if-ne v5, v7, :cond_60

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v3, v3, -0x1

    aget-char v3, v5, v3

    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v3

    if-nez v3, :cond_5f

    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v3

    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isDigit(I)Z

    move-result v3

    if-eqz v3, :cond_5f

    goto :goto_12

    .line 958
    :cond_5f
    new-instance v0, Lorg/mvel2/CompileException;

    const-string v3, "not a statement"

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 954
    :cond_60
    :goto_12
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v3, v3, 0x1

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_d

    .line 962
    :pswitch_25
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    const/16 v5, 0x2b

    if-ne v0, v5, :cond_62

    .line 963
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v23, 0x2

    add-int/lit8 v0, v0, 0x2

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 964
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 965
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 966
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureIdentifier()V

    .line 968
    new-instance v0, Ljava/lang/String;

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->subArray(II)[C

    move-result-object v3

    invoke-direct {v0, v3}, Ljava/lang/String;-><init>([C)V

    .line 969
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    if-eqz v3, :cond_61

    invoke-virtual {v3, v0}, Lorg/mvel2/ParserContext;->variableIndexOf(Ljava/lang/String;)I

    move-result v3

    if-eq v3, v14, :cond_61

    .line 970
    new-instance v0, Lorg/mvel2/ast/IndexedPreFixIncNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4}, Lorg/mvel2/ast/IndexedPreFixIncNode;-><init>(ILorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    .line 973
    :cond_61
    new-instance v3, Lorg/mvel2/ast/PreFixIncNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v3, v0, v4}, Lorg/mvel2/ast/PreFixIncNode;-><init>(Ljava/lang/String;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 976
    :cond_62
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v0, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 979
    :pswitch_26
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v0

    const/16 v3, 0x2a

    if-ne v0, v3, :cond_63

    .line 980
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 982
    :cond_63
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v0, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :pswitch_27
    const/16 v17, 0x1

    add-int/lit8 v8, v8, 0x1

    .line 1005
    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1009
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    const/4 v0, 0x1

    const/4 v3, 0x1

    .line 1010
    :goto_13
    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    const/16 v8, 0x27

    if-eq v6, v7, :cond_6c

    if-eqz v0, :cond_6c

    .line 1011
    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v11, v9, v6

    if-eq v11, v10, :cond_6a

    const/16 v13, 0x69

    if-eq v11, v13, :cond_64

    packed-switch v11, :pswitch_data_c

    const/16 v13, 0x2e

    if-eq v11, v13, :cond_6b

    if-eq v11, v5, :cond_6b

    if-eq v11, v12, :cond_6b

    .line 1078
    invoke-static {v11}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v6

    if-nez v6, :cond_6b

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v6, v6, v7

    const/16 v7, 0x2e

    if-eq v6, v7, :cond_6b

    move v3, v4

    goto/16 :goto_16

    :pswitch_28
    add-int/lit8 v0, v0, -0x1

    goto/16 :goto_16

    :pswitch_29
    add-int/lit8 v0, v0, 0x1

    goto/16 :goto_16

    .line 1019
    :pswitch_2a
    invoke-static {v8, v9, v6, v7}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v6

    iput v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_16

    :cond_64
    const/4 v6, 0x1

    if-ne v0, v6, :cond_6b

    .line 1025
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookBehind()C

    move-result v6

    invoke-static {v6}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v6

    if-eqz v6, :cond_6b

    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->lookAhead()C

    move-result v6

    const/16 v7, 0x6e

    if-ne v6, v7, :cond_6b

    const/4 v6, 0x2

    invoke-virtual {v1, v6}, Lorg/mvel2/compiler/AbstractParser;->lookAhead(I)C

    move-result v7

    invoke-static {v7}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v6

    if-eqz v6, :cond_6b

    move v3, v0

    .line 1027
    :goto_14
    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v4, v5, :cond_69

    .line 1028
    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v7, v6, v4

    if-eq v7, v10, :cond_67

    packed-switch v7, :pswitch_data_d

    goto/16 :goto_15

    :pswitch_2b
    add-int/lit8 v3, v3, -0x1

    if-ge v3, v0, :cond_68

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    .line 1034
    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1035
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    add-int/lit8 v0, v0, 0x1

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v5

    .line 1036
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, -0x1

    sub-int v6, v0, v5

    .line 1037
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->tokenContinues()Z

    move-result v0

    if-eqz v0, :cond_66

    .line 1038
    new-instance v3, Lorg/mvel2/ast/Fold;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/Fold;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 1040
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    aget-char v0, v0, v3

    const/16 v7, 0x2e

    if-ne v0, v7, :cond_65

    const/16 v17, 0x1

    add-int/lit8 v3, v3, 0x1

    iput v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1041
    :cond_65
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1042
    new-instance v3, Lorg/mvel2/ast/Union;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v5

    iput v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v9}, Lorg/mvel2/ast/Union;-><init>([CIIILorg/mvel2/ast/ASTNode;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 1046
    :cond_66
    new-instance v3, Lorg/mvel2/ast/Fold;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/Fold;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    :pswitch_2c
    add-int/lit8 v3, v3, 0x1

    goto :goto_15

    .line 1052
    :pswitch_2d
    invoke-static {v8, v6, v4, v5}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v4

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_15

    .line 1055
    :cond_67
    invoke-static {v10, v6, v4, v5}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v4

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1027
    :cond_68
    :goto_15
    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_14

    .line 1060
    :cond_69
    new-instance v0, Lorg/mvel2/CompileException;

    const-string/jumbo v3, "unterminated projection; closing parathesis required"

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 1022
    :cond_6a
    invoke-static {v10, v9, v6, v7}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v6

    iput v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1010
    :cond_6b
    :goto_16
    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v6, v6, 0x1

    iput v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_13

    :cond_6c
    if-nez v0, :cond_71

    if-eqz v3, :cond_70

    .line 1094
    new-instance v0, Lorg/mvel2/ast/TypeDescriptor;

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    invoke-virtual {v1, v4}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v4

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v5, v5, -0x1

    .line 1095
    invoke-virtual {v1, v5}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v5

    sub-int/2addr v5, v4

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    invoke-direct {v0, v3, v4, v5, v6}, Lorg/mvel2/ast/TypeDescriptor;-><init>([CIII)V
    :try_end_e
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_e .. :try_end_e} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_e .. :try_end_e} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_e .. :try_end_e} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_e .. :try_end_e} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_e .. :try_end_e} :catch_0

    .line 1099
    :try_start_f
    invoke-virtual {v0}, Lorg/mvel2/ast/TypeDescriptor;->isClass()Z

    move-result v3

    if-eqz v3, :cond_70

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v3, v0}, Lorg/mvel2/ast/TypeDescriptor;->getClassReference(Lorg/mvel2/ParserContext;Lorg/mvel2/ast/TypeDescriptor;)Ljava/lang/Class;

    move-result-object v22

    if-eqz v22, :cond_70

    .line 1103
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    :goto_17
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    array-length v4, v3

    if-ge v0, v4, :cond_70

    .line 1104
    aget-char v3, v3, v0

    const/16 v4, 0x20

    if-eq v3, v4, :cond_6f

    const/16 v4, 0x9

    if-ne v3, v4, :cond_6d

    goto :goto_18

    .line 1105
    :cond_6d
    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isIdentifierPart(I)Z

    move-result v3

    if-nez v3, :cond_6e

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v0, v3, v0

    if-eq v0, v8, :cond_6e

    if-eq v0, v10, :cond_6e

    const/16 v13, 0x28

    if-ne v0, v13, :cond_70

    .line 1110
    :cond_6e
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1112
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1115
    new-instance v18, Lorg/mvel2/ast/TypeCast;

    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v21, v4, v3

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    move-object/from16 v19, v0

    move/from16 v20, v3

    move/from16 v23, v4

    move-object/from16 v24, v5

    invoke-direct/range {v18 .. v24}, Lorg/mvel2/ast/TypeCast;-><init>([CIILjava/lang/Class;ILorg/mvel2/ParserContext;)V

    move-object/from16 v0, v18

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;
    :try_end_f
    .catch Ljava/lang/ClassNotFoundException; {:try_start_f .. :try_end_f} :catch_6
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_f .. :try_end_f} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_f .. :try_end_f} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_f .. :try_end_f} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_f .. :try_end_f} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_f .. :try_end_f} :catch_0

    return-object v0

    :cond_6f
    :goto_18
    add-int/lit8 v0, v0, 0x1

    goto :goto_17

    .line 1130
    :catch_6
    :cond_70
    :try_start_10
    new-instance v3, Lorg/mvel2/ast/Substatement;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    .line 1132
    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimRight(I)I

    move-result v5

    iput v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, -0x1

    .line 1133
    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->trimLeft(I)I

    move-result v0

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int v6, v0, v6

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/Substatement;-><init>([CIIILorg/mvel2/ParserContext;)V

    .line 1131
    invoke-virtual {v1, v3}, Lorg/mvel2/compiler/AbstractParser;->handleSubstatement(Lorg/mvel2/ast/Substatement;)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    .line 1130
    invoke-virtual {v1, v0}, Lorg/mvel2/compiler/AbstractParser;->handleUnion(Lorg/mvel2/ast/ASTNode;)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1087
    :cond_71
    new-instance v3, Lorg/mvel2/CompileException;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string/jumbo v5, "unbalanced braces in expression: ("

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, "):"

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v3, v0, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v3

    :pswitch_2e
    add-int/lit8 v0, v8, 0x1

    .line 1183
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v3, v7, v0

    const/16 v4, 0x26

    if-ne v3, v4, :cond_72

    .line 1184
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v23, 0x2

    add-int/lit8 v8, v8, 0x2

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v8}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1187
    :cond_72
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v1, v7, v3, v0}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1170
    :pswitch_2f
    new-instance v0, Lorg/mvel2/ast/LiteralNode;

    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v17, 0x1

    add-int/lit8 v3, v3, 0x1

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    .line 1171
    invoke-static {v9, v7, v8, v4}, Lorg/mvel2/util/ParseTools;->captureStringLiteral(C[CII)I

    move-result v4

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    sub-int/2addr v4, v5

    add-int/lit8 v4, v4, -0x1

    .line 1170
    invoke-static {v7, v3, v4}, Lorg/mvel2/util/ParseTools;->subset([CII)[C

    move-result-object v3

    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->handleStringEscapes([C)Ljava/lang/String;

    move-result-object v3

    const-class v4, Ljava/lang/String;

    iget-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/ast/LiteralNode;-><init>(Ljava/lang/Object;Ljava/lang/Class;Lorg/mvel2/ParserContext;)V

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 1174
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1176
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->tokenContinues()Z

    move-result v0
    :try_end_10
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_10 .. :try_end_10} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_10 .. :try_end_10} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_10 .. :try_end_10} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_10 .. :try_end_10} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_10 .. :try_end_10} :catch_0

    .line 1180
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    if-eqz v0, :cond_73

    .line 1177
    :try_start_11
    invoke-virtual {v1, v3}, Lorg/mvel2/compiler/AbstractParser;->handleUnion(Lorg/mvel2/ast/ASTNode;)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    iput-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v0

    :cond_73
    return-object v3

    :pswitch_30
    const/16 v17, 0x1

    add-int/lit8 v8, v8, 0x1

    .line 1218
    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1219
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->isNextIdentifier()Z

    move-result v0
    :try_end_11
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_11 .. :try_end_11} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_11 .. :try_end_11} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_11 .. :try_end_11} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_11 .. :try_end_11} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_11 .. :try_end_11} :catch_0

    const-string/jumbo v3, "unexpected operator \'!\'"

    if-eqz v0, :cond_78

    .line 1220
    :try_start_12
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    if-eqz v0, :cond_75

    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->isOperator()Z

    move-result v0

    if-eqz v0, :cond_74

    goto :goto_19

    .line 1221
    :cond_74
    new-instance v0, Lorg/mvel2/CompileException;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v4, v5}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 1224
    :cond_75
    :goto_19
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1225
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1226
    const-string v0, "new"

    new-instance v3, Ljava/lang/String;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v6, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int/2addr v6, v5

    invoke-direct {v3, v4, v5, v6}, Ljava/lang/String;-><init>([CII)V

    invoke-virtual {v0, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_77

    const-string v0, "isdef"

    .line 1227
    invoke-virtual {v0, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_76

    goto :goto_1a

    .line 1232
    :cond_76
    new-instance v3, Lorg/mvel2/ast/Negation;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/Negation;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 1228
    :cond_77
    :goto_1a
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1229
    new-instance v3, Lorg/mvel2/ast/Negation;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/Negation;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 1235
    :cond_78
    iget-object v0, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aget-char v5, v0, v4

    const/16 v13, 0x28

    if-ne v5, v13, :cond_79

    add-int/lit8 v0, v4, -0x1

    .line 1236
    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1237
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1238
    new-instance v5, Lorg/mvel2/ast/Negation;

    iget-object v6, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v8, v0, v7

    iget v9, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v10, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v5 .. v10}, Lorg/mvel2/ast/Negation;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v5, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v5

    :cond_79
    const/16 v6, 0x21

    if-ne v5, v6, :cond_7a

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    .line 1242
    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1243
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    :cond_7a
    if-ne v5, v11, :cond_7b

    .line 1248
    iget v3, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v17, 0x1

    add-int/lit8 v4, v4, 0x1

    iput v4, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v0, v3, v4}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1246
    :cond_7b
    new-instance v4, Lorg/mvel2/CompileException;

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/4 v6, 0x0

    invoke-direct {v4, v3, v0, v5, v6}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CILjava/lang/Throwable;)V

    throw v4

    .line 1001
    :cond_7c
    :pswitch_31
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    const/16 v17, 0x1

    add-int/lit8 v8, v8, 0x1

    iput v8, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-direct {v1, v7, v0, v8}, Lorg/mvel2/compiler/AbstractParser;->createOperator([CII)Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    .line 1140
    :cond_7d
    :pswitch_32
    new-instance v0, Lorg/mvel2/CompileException;

    const-string/jumbo v3, "unbalanced braces"

    iget v4, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-direct {v0, v3, v7, v4}, Lorg/mvel2/CompileException;-><init>(Ljava/lang/String;[CI)V

    throw v0

    .line 1254
    :cond_7e
    :pswitch_33
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->end:I

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-static {v7, v8, v0, v9, v3}, Lorg/mvel2/util/ParseTools;->balancedCaptureWithLineAccounting([CIICLorg/mvel2/ParserContext;)I

    move-result v0

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1255
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->tokenContinues()Z

    move-result v0

    if-eqz v0, :cond_80

    .line 1256
    new-instance v3, Lorg/mvel2/ast/InlineCollectionNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/InlineCollectionNode;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    .line 1257
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1258
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->captureToEOT()V

    .line 1259
    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    aget-char v3, v4, v0

    const/16 v7, 0x2e

    if-ne v3, v7, :cond_7f

    const/16 v17, 0x1

    add-int/lit8 v0, v0, 0x1

    iput v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    .line 1261
    :cond_7f
    new-instance v3, Lorg/mvel2/ast/Union;

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    iget-object v9, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v9}, Lorg/mvel2/ast/Union;-><init>([CIIILorg/mvel2/ast/ASTNode;Lorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 1264
    :cond_80
    new-instance v3, Lorg/mvel2/ast/InlineCollectionNode;

    iget-object v4, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v5, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    sub-int v6, v0, v5

    iget v7, v1, Lorg/mvel2/compiler/AbstractParser;->fields:I

    iget-object v8, v1, Lorg/mvel2/compiler/AbstractParser;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct/range {v3 .. v8}, Lorg/mvel2/ast/InlineCollectionNode;-><init>([CIIILorg/mvel2/ParserContext;)V

    iput-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->lastNode:Lorg/mvel2/ast/ASTNode;

    return-object v3

    .line 1273
    :cond_81
    iget v0, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    if-ne v0, v7, :cond_82

    const/16 v16, 0x0

    return-object v16

    .line 1276
    :cond_82
    invoke-direct {v1, v0, v7}, Lorg/mvel2/compiler/AbstractParser;->createPropertyToken(II)Lorg/mvel2/ast/ASTNode;

    move-result-object v0
    :try_end_12
    .catch Lorg/mvel2/ast/RedundantCodeException; {:try_start_12 .. :try_end_12} :catch_7
    .catch Ljava/lang/NumberFormatException; {:try_start_12 .. :try_end_12} :catch_3
    .catch Ljava/lang/StringIndexOutOfBoundsException; {:try_start_12 .. :try_end_12} :catch_4
    .catch Ljava/lang/ArrayIndexOutOfBoundsException; {:try_start_12 .. :try_end_12} :catch_1
    .catch Lorg/mvel2/CompileException; {:try_start_12 .. :try_end_12} :catch_0

    return-object v0

    .line 1291
    :goto_1b
    iget-object v2, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v1, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v0, v2, v1}, Lorg/mvel2/util/ErrorUtil;->rewriteIfNeeded(Lorg/mvel2/CompileException;[CI)Lorg/mvel2/CompileException;

    move-result-object v0

    throw v0

    .line 1288
    :goto_1c
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v1, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v2, v3, v1, v0}, Lorg/mvel2/ast/Negation$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CILjava/lang/Throwable;)V

    const/16 v16, 0x0

    return-object v16

    .line 1285
    :goto_1d
    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v1, v1, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    invoke-static {v2, v3, v1, v0}, Lorg/mvel2/ast/Negation$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CILjava/lang/Throwable;)V

    return-object v16

    .line 1282
    :goto_1e
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "badly formatted number: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    iget-object v3, v1, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget v1, v1, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-static {v2, v3, v1, v0}, Lorg/mvel2/ast/Negation$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CILjava/lang/Throwable;)V

    const/16 v16, 0x0

    return-object v16

    .line 1279
    :catch_7
    invoke-virtual {v1}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    return-object v0

    nop

    :pswitch_data_0
    .packed-switch 0x26
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x2d
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x5f
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :pswitch_data_3
    .packed-switch 0x21
        :pswitch_1a
        :pswitch_1a
        :pswitch_1a
    .end packed-switch

    :pswitch_data_4
    .packed-switch 0x25
        :pswitch_19
        :pswitch_19
        :pswitch_1a
        :pswitch_18
    .end packed-switch

    :pswitch_data_5
    .packed-switch 0x2a
        :pswitch_19
        :pswitch_17
        :pswitch_1a
        :pswitch_16
        :pswitch_15
        :pswitch_19
    .end packed-switch

    :pswitch_data_6
    .packed-switch 0x3a
        :pswitch_1a
        :pswitch_1a
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
    .end packed-switch

    :pswitch_data_7
    .packed-switch 0x21
        :pswitch_30
        :pswitch_2f
        :pswitch_31
    .end packed-switch

    :pswitch_data_8
    .packed-switch 0x25
        :pswitch_31
        :pswitch_2e
        :pswitch_2f
        :pswitch_27
        :pswitch_32
        :pswitch_26
        :pswitch_25
    .end packed-switch

    :pswitch_data_9
    .packed-switch 0x2d
        :pswitch_24
        :pswitch_23
        :pswitch_31
    .end packed-switch

    :pswitch_data_a
    .packed-switch 0x3a
        :pswitch_31
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
        :pswitch_1d
    .end packed-switch

    :pswitch_data_b
    .packed-switch 0x7b
        :pswitch_33
        :pswitch_1c
        :pswitch_32
        :pswitch_1b
    .end packed-switch

    :pswitch_data_c
    .packed-switch 0x27
        :pswitch_2a
        :pswitch_29
        :pswitch_28
    .end packed-switch

    :pswitch_data_d
    .packed-switch 0x27
        :pswitch_2d
        :pswitch_2c
        :pswitch_2b
    .end packed-switch
.end method

.method public nextTokenSkipSymbols()Lorg/mvel2/ast/ASTNode;
    .locals 3

    .line 236
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 237
    invoke-virtual {v0}, Lorg/mvel2/ast/ASTNode;->getFields()I

    move-result v1

    const/4 v2, -0x1

    if-ne v1, v2, :cond_0

    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->nextToken()Lorg/mvel2/ast/ASTNode;

    move-result-object p0

    return-object p0

    :cond_0
    return-object v0
.end method

.method public reduce()V
    .locals 5

    .line 2562
    :try_start_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-eqz v0, :cond_7

    const/4 v1, 0x1

    if-eq v0, v1, :cond_7

    const/4 v2, 0x2

    if-eq v0, v2, :cond_7

    const/4 v2, 0x3

    if-eq v0, v2, :cond_7

    const/4 v2, 0x4

    if-eq v0, v2, :cond_7

    const/4 v2, 0x5

    if-eq v0, v2, :cond_7

    const/16 v2, 0x24

    if-eq v0, v2, :cond_6

    const/4 v2, 0x0

    packed-switch v0, :pswitch_data_0

    .line 2626
    invoke-direct {p0, v0}, Lorg/mvel2/compiler/AbstractParser;->reduceNumeric(I)V

    return-void

    :catch_0
    move-exception v0

    goto/16 :goto_3

    :catch_1
    move-exception v0

    goto/16 :goto_4

    :catch_2
    move-exception v0

    goto/16 :goto_5

    .line 2622
    :pswitch_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v2

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/mvel2/util/ParseTools;->similarity(Ljava/lang/String;Ljava/lang/String;)F

    move-result v1

    invoke-static {v1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2617
    :pswitch_1
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/mvel2/util/Soundex;->soundex(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    .line 2618
    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v2

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/mvel2/util/Soundex;->soundex(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    .line 2617
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2613
    :pswitch_2
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->peek2()Ljava/lang/Object;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->pop2()Ljava/lang/Object;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/mvel2/util/ParseTools;->containsCheck(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2605
    :pswitch_3
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Class;

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2600
    :pswitch_4
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    .line 2601
    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v2

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/regex/Matcher;->matches()Z

    move-result v1

    .line 2600
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2590
    :pswitch_5
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v0

    .line 2591
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v1}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v1

    invoke-static {v1}, Lorg/mvel2/util/PropertyTools;->isEmpty(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-static {v0}, Lorg/mvel2/util/PropertyTools;->isEmpty(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    goto :goto_0

    .line 2596
    :cond_0
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2592
    :cond_1
    :goto_0
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->clear()V

    .line 2593
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-static {v1}, Lorg/mvel2/util/PropertyTools;->isEmpty(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_2

    move-object v0, v1

    :cond_2
    invoke-virtual {v2, v0}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2585
    :pswitch_6
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v0

    .line 2586
    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v3}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Boolean;

    invoke-virtual {v4}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    if-nez v4, :cond_4

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_3

    goto :goto_1

    :cond_3
    move v1, v2

    :cond_4
    :goto_1
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {v3, v0}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2580
    :pswitch_7
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v0

    .line 2581
    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v3}, Lorg/mvel2/util/ExecutionStack;->pop()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Boolean;

    invoke-virtual {v4}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    if-eqz v4, :cond_5

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_5

    goto :goto_2

    :cond_5
    move v1, v2

    :goto_2
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {v3, v0}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2609
    :cond_6
    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v0}, Lorg/mvel2/util/ExecutionStack;->peek2()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v2}, Lorg/mvel2/util/ExecutionStack;->pop2()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Class;

    invoke-static {v1, v2}, Lorg/mvel2/DataConversion;->canConvert(Ljava/lang/Class;Ljava/lang/Class;)Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/mvel2/util/ExecutionStack;->push(Ljava/lang/Object;)V

    return-void

    .line 2576
    :cond_7
    :pswitch_8
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->stk:Lorg/mvel2/util/ExecutionStack;

    invoke-virtual {v1, v0}, Lorg/mvel2/util/ExecutionStack;->op(I)V
    :try_end_0
    .catch Ljava/lang/ClassCastException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/ArithmeticException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 2636
    :goto_3
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    const-string v2, "failed to subEval expression"

    invoke-static {v2, v1, p0, v0}, Lorg/mvel2/ast/Negation$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CILjava/lang/Throwable;)V

    return-void

    .line 2633
    :goto_4
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "arithmetic error: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    invoke-static {v1, v2, p0, v0}, Lorg/mvel2/ast/Negation$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CILjava/lang/Throwable;)V

    return-void

    .line 2630
    :goto_5
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    iget p0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    const-string/jumbo v2, "syntax error or incompatable types"

    invoke-static {v2, v1, p0, v0}, Lorg/mvel2/ast/Negation$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[CILjava/lang/Throwable;)V

    return-void

    nop

    :pswitch_data_0
    .packed-switch 0xe
        :pswitch_8
        :pswitch_8
        :pswitch_8
        :pswitch_8
        :pswitch_8
        :pswitch_8
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public setExpression(Ljava/lang/String;)V
    .locals 4

    if-eqz p1, :cond_3

    .line 2113
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_3

    .line 2114
    sget-object v0, Lorg/mvel2/compiler/AbstractParser;->EX_PRECACHE:Ljava/util/WeakHashMap;

    monitor-enter v0

    .line 2115
    :try_start_0
    invoke-virtual {v0, p1}, Ljava/util/WeakHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [C

    iput-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    if-nez v1, :cond_2

    const/4 v1, 0x0

    .line 2118
    :goto_0
    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    if-ge v1, v2, :cond_0

    invoke-virtual {p1, v1}, Ljava/lang/String;->charAt(I)C

    move-result v2

    invoke-static {v2}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v2

    if-eqz v2, :cond_0

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_3

    .line 2121
    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    :goto_1
    if-lez v2, :cond_1

    .line 2122
    invoke-virtual {p1, v2}, Ljava/lang/String;->charAt(I)C

    move-result v3

    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v3

    if-eqz v3, :cond_1

    add-int/lit8 v2, v2, -0x1

    goto :goto_1

    :cond_1
    add-int/lit8 v2, v2, 0x1

    .line 2125
    invoke-virtual {p1, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->toCharArray()[C

    move-result-object v1

    iput-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    .line 2127
    array-length v2, v1

    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    .line 2129
    sget-object p0, Lorg/mvel2/compiler/AbstractParser;->EX_PRECACHE:Ljava/util/WeakHashMap;

    invoke-virtual {p0, p1, v1}, Ljava/util/WeakHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_2

    .line 2132
    :cond_2
    array-length p1, v1

    iput p1, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    iput p1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    .line 2134
    :goto_2
    monitor-exit v0

    return-void

    :goto_3
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    :cond_3
    return-void
.end method

.method public setExpression([C)V
    .locals 1

    .line 2144
    iput-object p1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    array-length p1, p1

    iput p1, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    iput p1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    .line 2145
    :goto_0
    iget p1, p0, Lorg/mvel2/compiler/AbstractParser;->start:I

    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    if-ge p1, v0, :cond_0

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char p1, v0, p1

    invoke-static {p1}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result p1

    if-eqz p1, :cond_0

    iget p1, p0, Lorg/mvel2/compiler/AbstractParser;->start:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lorg/mvel2/compiler/AbstractParser;->start:I

    goto :goto_0

    .line 2146
    :cond_0
    :goto_1
    iget p1, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    if-eqz p1, :cond_1

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 p1, p1, -0x1

    aget-char p1, v0, p1

    invoke-static {p1}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result p1

    if-eqz p1, :cond_1

    iget p1, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    add-int/lit8 p1, p1, -0x1

    iput p1, p0, Lorg/mvel2/compiler/AbstractParser;->length:I

    goto :goto_1

    :cond_1
    return-void
.end method

.method public skipWhitespace()V
    .locals 8

    .line 2020
    :cond_0
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_d

    .line 2021
    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v3, v2, v0

    const/16 v4, 0xa

    if-eq v3, v4, :cond_b

    const/16 v5, 0xd

    if-eq v3, v5, :cond_c

    const/16 v5, 0x2f

    if-eq v3, v5, :cond_1

    goto :goto_4

    :cond_1
    add-int/lit8 v6, v0, 0x1

    if-eq v6, v1, :cond_9

    add-int/lit8 v3, v0, 0x1

    .line 2030
    aget-char v3, v2, v3

    const/16 v6, 0x2a

    const/16 v7, 0x20

    if-eq v3, v6, :cond_5

    if-eq v3, v5, :cond_2

    goto/16 :goto_5

    :cond_2
    add-int/lit8 v1, v0, 0x1

    .line 2033
    iput v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aput-char v7, v2, v0

    .line 2034
    :goto_1
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v0, v1, :cond_3

    iget-object v2, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v3, v2, v0

    if-eq v3, v4, :cond_3

    add-int/lit8 v1, v0, 0x1

    .line 2035
    iput v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    aput-char v7, v2, v0

    goto :goto_1

    :cond_3
    if-eq v0, v1, :cond_4

    add-int/lit8 v0, v0, 0x1

    .line 2038
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 2041
    :cond_4
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->line:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->line:I

    .line 2042
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastLineStart:I

    goto :goto_0

    :cond_5
    add-int/lit8 v1, v1, -0x1

    add-int/lit8 v2, v0, 0x1

    .line 2049
    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 2051
    :goto_2
    iget v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    if-eq v2, v1, :cond_7

    iget-object v3, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v4, v3, v2

    if-ne v4, v6, :cond_6

    add-int/lit8 v4, v2, 0x1

    aget-char v3, v3, v4

    if-eq v3, v5, :cond_7

    :cond_6
    add-int/lit8 v2, v2, 0x1

    .line 2052
    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_2

    :cond_7
    if-eq v2, v1, :cond_8

    add-int/lit8 v2, v2, 0x2

    .line 2055
    iput v2, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 2058
    :cond_8
    :goto_3
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    if-ge v0, v1, :cond_0

    .line 2059
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aput-char v7, v1, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    .line 2070
    :cond_9
    :goto_4
    invoke-static {v3}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-nez v0, :cond_a

    goto :goto_5

    .line 2073
    :cond_a
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_0

    .line 2023
    :cond_b
    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->line:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lorg/mvel2/compiler/AbstractParser;->line:I

    .line 2024
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->lastLineStart:I

    :cond_c
    add-int/lit8 v0, v0, 0x1

    .line 2026
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto/16 :goto_0

    :cond_d
    :goto_5
    return-void
.end method

.method public tokenContinues()Z
    .locals 7

    .line 1795
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v1, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    const/4 v2, 0x0

    if-ne v0, v1, :cond_0

    return v2

    .line 1796
    :cond_0
    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v0, v1, v0

    const/4 v1, 0x1

    const/16 v3, 0x2e

    if-eq v0, v3, :cond_5

    const/16 v4, 0x5b

    if-ne v0, v4, :cond_1

    goto :goto_0

    .line 1797
    :cond_1
    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 1798
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    .line 1799
    invoke-virtual {p0}, Lorg/mvel2/compiler/AbstractParser;->skipWhitespace()V

    .line 1800
    iget v5, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    iget v6, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq v5, v6, :cond_3

    iget-object v6, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v5, v6, v5

    if-eq v5, v3, :cond_2

    if-ne v5, v4, :cond_3

    :cond_2
    return v1

    .line 1801
    :cond_3
    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    :cond_4
    return v2

    :cond_5
    :goto_0
    return v1
.end method

.method public trimLeft(I)I
    .locals 2

    .line 1997
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-le p1, v0, :cond_0

    move p1, v0

    :cond_0
    :goto_0
    if-lez p1, :cond_2

    .line 1998
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->st:I

    if-lt p1, v0, :cond_2

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v1, p1, -0x1

    aget-char v0, v0, v1

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v0, v0, v1

    const/16 v1, 0x3b

    if-ne v0, v1, :cond_2

    :cond_1
    add-int/lit8 p1, p1, -0x1

    goto :goto_0

    :cond_2
    return p1
.end method

.method public trimRight(I)I
    .locals 1

    .line 2010
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->end:I

    if-eq p1, v0, :cond_0

    iget-object v0, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    aget-char v0, v0, p1

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    :cond_0
    return p1
.end method

.method public trimWhitespace()V
    .locals 2

    .line 2104
    :goto_0
    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    if-eqz v0, :cond_0

    iget-object v1, p0, Lorg/mvel2/compiler/AbstractParser;->expr:[C

    add-int/lit8 v0, v0, -0x1

    aget-char v0, v1, v0

    invoke-static {v0}, Lorg/mvel2/util/ParseTools;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_0

    iget v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lorg/mvel2/compiler/AbstractParser;->cursor:I

    goto :goto_0

    :cond_0
    return-void
.end method
