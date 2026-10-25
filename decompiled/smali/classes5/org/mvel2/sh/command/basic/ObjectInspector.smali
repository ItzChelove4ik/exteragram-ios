.class public Lorg/mvel2/sh/command/basic/ObjectInspector;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/mvel2/sh/Command;


# static fields
.field private static final PADDING:I = 0x11


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static renderClassHeirarchy(Ljava/lang/Class;)Ljava/lang/String;
    .locals 4

    .line 109
    new-instance v0, Ljava/util/LinkedList;

    invoke-direct {v0}, Ljava/util/LinkedList;-><init>()V

    .line 110
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 112
    :goto_0
    invoke-virtual {p0}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 113
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 116
    :cond_0
    new-instance p0, Lorg/mvel2/util/StringAppender;

    invoke-direct {p0}, Lorg/mvel2/util/StringAppender;-><init>()V

    .line 118
    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    :goto_1
    const/4 v2, -0x1

    if-eq v1, v2, :cond_2

    .line 119
    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-virtual {p0, v3}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    add-int/lit8 v3, v1, -0x1

    if-eq v3, v2, :cond_1

    .line 120
    const-string v2, " -> "

    invoke-virtual {p0, v2}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    :cond_1
    add-int/lit8 v1, v1, -0x1

    goto :goto_1

    .line 123
    :cond_2
    invoke-virtual {p0}, Lorg/mvel2/util/StringAppender;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static renderFields(Ljava/lang/Class;)V
    .locals 3

    .line 127
    invoke-virtual {p0}, Ljava/lang/Class;->getFields()[Ljava/lang/reflect/Field;

    move-result-object p0

    const/4 v0, 0x0

    .line 129
    :goto_0
    array-length v1, p0

    if-ge v0, v1, :cond_0

    .line 130
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    aget-object v2, p0, v0

    invoke-virtual {v2}, Ljava/lang/reflect/Field;->getType()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    aget-object v2, p0, v0

    invoke-virtual {v2}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v2, ""

    invoke-static {v2, v1}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method private static renderMethods(Ljava/lang/Class;)V
    .locals 9

    .line 135
    invoke-virtual {p0}, Ljava/lang/Class;->getMethods()[Ljava/lang/reflect/Method;

    move-result-object v0

    .line 136
    new-instance v1, Lorg/mvel2/sh/command/basic/ObjectInspector$1;

    invoke-direct {v1}, Lorg/mvel2/sh/command/basic/ObjectInspector$1;-><init>()V

    invoke-static {v0, v1}, Ljava/util/Arrays;->sort([Ljava/lang/Object;Ljava/util/Comparator;)V

    .line 143
    new-instance v1, Lorg/mvel2/util/StringAppender;

    invoke-direct {v1}, Lorg/mvel2/util/StringAppender;-><init>()V

    const/4 v2, 0x0

    move v3, v2

    .line 145
    :cond_0
    :goto_0
    array-length v4, v0

    if-ge v3, v4, :cond_6

    const/16 v4, 0x20

    const/16 v5, 0x13

    .line 146
    invoke-static {v4, v5}, Lorg/mvel2/sh/text/TextUtil;->paint(CI)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    .line 147
    aget-object v4, v0, v3

    .line 152
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    move-result-object v5

    const-string v6, "("

    invoke-virtual {v5, v6}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    .line 153
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v5

    move v6, v2

    .line 154
    :cond_1
    :goto_1
    array-length v7, v5

    if-ge v6, v7, :cond_3

    .line 155
    aget-object v7, v5, v6

    invoke-virtual {v7}, Ljava/lang/Class;->isArray()Z

    move-result v7

    if-eqz v7, :cond_2

    .line 156
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    aget-object v8, v5, v6

    invoke-virtual {v8}, Ljava/lang/Class;->getComponentType()Ljava/lang/Class;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v8

    invoke-static {v8}, Lorg/mvel2/sh/command/basic/ObjectInspector;->shorter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, "[]"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v1, v7}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    goto :goto_2

    .line 159
    :cond_2
    aget-object v7, v5, v6

    invoke-virtual {v7}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lorg/mvel2/sh/command/basic/ObjectInspector;->shorter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v1, v7}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    :goto_2
    add-int/lit8 v6, v6, 0x1

    .line 161
    array-length v7, v5

    if-ge v6, v7, :cond_1

    const-string v7, ", "

    invoke-virtual {v1, v7}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    goto :goto_1

    .line 163
    :cond_3
    const-string v5, ")"

    invoke-virtual {v1, v5}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    .line 164
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v5

    .line 165
    const-string/jumbo v6, "void"

    invoke-virtual {v6, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-nez v6, :cond_4

    .line 166
    const-string v6, " -> "

    invoke-virtual {v1, v6}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    move-result-object v6

    invoke-static {v5}, Lorg/mvel2/sh/command/basic/ObjectInspector;->shorter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v6, v5}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    .line 168
    :cond_4
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v5

    if-eq v5, p0, :cond_5

    .line 169
    const-string v5, "    ["

    invoke-virtual {v1, v5}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    move-result-object v5

    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lorg/mvel2/sh/command/basic/ObjectInspector;->shorter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v5, v4}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    move-result-object v4

    const-string v5, "]"

    invoke-virtual {v4, v5}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    :cond_5
    add-int/lit8 v3, v3, 0x1

    .line 173
    array-length v4, v0

    if-ge v3, v4, :cond_0

    const/16 v4, 0xa

    invoke-virtual {v1, v4}, Lorg/mvel2/util/StringAppender;->append(C)Lorg/mvel2/util/StringAppender;

    goto/16 :goto_0

    .line 176
    :cond_6
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {v1}, Lorg/mvel2/util/StringAppender;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-void
.end method

.method private static shorter(Ljava/lang/String;)Ljava/lang/String;
    .locals 8

    .line 180
    const-string v0, "java.lang."

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 183
    :cond_0
    const-string v6, "ref"

    const-string v7, "reflect"

    const-string v2, "annotation"

    const-string v3, "instrument"

    const-string v4, "invoke"

    const-string v5, "management"

    filled-new-array/range {v2 .. v7}, [Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 184
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "."

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_1

    :goto_0
    return-object p0

    .line 188
    :cond_2
    const-string v0, "^java\\.lang\\."

    const-string v1, ""

    invoke-virtual {p0, v0, v1}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static write(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 3

    .line 192
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, ": "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/16 v1, 0x11

    invoke-static {p0, p1, v1}, Lorg/mvel2/sh/text/TextUtil;->padTwo(Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public execute(Lorg/mvel2/sh/ShellSession;[Ljava/lang/String;)Ljava/lang/Object;
    .locals 6

    .line 45
    array-length p0, p2

    const/4 v0, 0x0

    const/4 v1, 0x0

    if-nez p0, :cond_0

    .line 46
    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getCtxObject()Ljava/lang/Object;

    move-result-object p0

    if-nez p0, :cond_2

    .line 48
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string p1, "inspect: requires an argument or a session context."

    invoke-virtual {p0, p1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-object v0

    .line 52
    :cond_0
    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getVariables()Ljava/util/Map;

    move-result-object p0

    aget-object v2, p2, v1

    invoke-interface {p0, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 53
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance p1, Ljava/lang/StringBuilder;

    const-string v2, "inspect: no such variable: "

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    aget-object p2, p2, v1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-object v0

    .line 57
    :cond_1
    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getVariables()Ljava/util/Map;

    move-result-object p0

    aget-object p1, p2, v1

    invoke-interface {p0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 60
    :cond_2
    sget-object p1, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v2, "Object Inspector"

    invoke-virtual {p1, v2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 61
    sget-object p1, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const/16 v2, 0x2d

    const/16 v3, 0x11

    invoke-static {v2, v3}, Lorg/mvel2/sh/text/TextUtil;->paint(CI)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    if-nez p0, :cond_3

    .line 64
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string p1, "[Value is Null]"

    invoke-virtual {p0, p1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-object v0

    .line 69
    :cond_3
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p1

    .line 74
    :try_start_0
    new-instance v2, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v2}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 75
    new-instance v3, Ljava/io/ObjectOutputStream;

    new-instance v4, Ljava/io/BufferedOutputStream;

    invoke-direct {v4, v2}, Ljava/io/BufferedOutputStream;-><init>(Ljava/io/OutputStream;)V

    invoke-direct {v3, v4}, Ljava/io/ObjectOutputStream;-><init>(Ljava/io/OutputStream;)V

    .line 76
    invoke-virtual {v3, p0}, Ljava/io/ObjectOutputStream;->writeObject(Ljava/lang/Object;)V

    .line 77
    invoke-virtual {v3}, Ljava/io/ObjectOutputStream;->flush()V

    .line 78
    invoke-virtual {v2}, Ljava/io/OutputStream;->flush()V

    .line 80
    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->size()I

    move-result v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    int-to-long v2, v2

    const/4 v4, 0x1

    goto :goto_0

    :catch_0
    const-wide/16 v2, 0x0

    move v4, v1

    .line 86
    :goto_0
    array-length v5, p2

    if-lez v5, :cond_4

    .line 87
    const-string v5, "VariableName"

    aget-object p2, p2, v1

    invoke-static {v5, p2}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 89
    :cond_4
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string p2, "Hashcode"

    invoke-static {p2, p0}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 90
    const-string p0, "ClassType"

    invoke-virtual {p1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p2}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 91
    const-string p0, "Serializable"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p2

    invoke-static {p0, p2}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    if-eqz v4, :cond_5

    .line 93
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p2, " bytes"

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const-string p2, "SerializedSize"

    invoke-static {p2, p0}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 95
    :cond_5
    const-string p0, "ClassHierarchy"

    invoke-static {p1}, Lorg/mvel2/sh/command/basic/ObjectInspector;->renderClassHeirarchy(Ljava/lang/Class;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p2}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 96
    invoke-virtual {p1}, Ljava/lang/Class;->getFields()[Ljava/lang/reflect/Field;

    move-result-object p0

    array-length p0, p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string p2, "Fields"

    invoke-static {p2, p0}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 97
    invoke-static {p1}, Lorg/mvel2/sh/command/basic/ObjectInspector;->renderFields(Ljava/lang/Class;)V

    .line 99
    invoke-virtual {p1}, Ljava/lang/Class;->getMethods()[Ljava/lang/reflect/Method;

    move-result-object p0

    array-length p0, p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string p2, "Methods"

    invoke-static {p2, p0}, Lorg/mvel2/sh/command/basic/ObjectInspector;->write(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 100
    invoke-static {p1}, Lorg/mvel2/sh/command/basic/ObjectInspector;->renderMethods(Ljava/lang/Class;)V

    .line 103
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {p0}, Ljava/io/PrintStream;->println()V

    return-object v0
.end method

.method public getDescription()Ljava/lang/String;
    .locals 0

    .line 196
    const-string p0, "inspects an object"

    return-object p0
.end method

.method public getHelp()Ljava/lang/String;
    .locals 0

    .line 200
    const-string p0, "No help yet"

    return-object p0
.end method
