.class public Lorg/mvel2/sh/ShellSession;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/mvel2/sh/ShellSession$RunState;
    }
.end annotation


# static fields
.field private static final EMPTY:[Ljava/lang/String;

.field public static final PROMPT_VAR:Ljava/lang/String; = "$PROMPT"


# instance fields
.field private cdepth:I

.field private commandBuffer:Ljava/lang/String;

.field private final commands:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lorg/mvel2/sh/Command;",
            ">;"
        }
    .end annotation
.end field

.field private ctxObject:Ljava/lang/Object;

.field private ctxStack:Ljava/util/Stack;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Stack<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private depth:I

.field private env:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field inBuffer:Lorg/mvel2/util/StringAppender;

.field lvrf:Lorg/mvel2/integration/VariableResolverFactory;

.field private multi:Z

.field private multiIndentSize:I

.field private out:Ljava/io/PrintStream;

.field pCtx:Lorg/mvel2/ParserContext;

.field private prompt:Ljava/lang/String;

.field final readBuffer:Ljava/io/BufferedReader;

.field private variables:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x0

    .line 48
    new-array v0, v0, [Ljava/lang/String;

    sput-object v0, Lorg/mvel2/sh/ShellSession;->EMPTY:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 7

    .line 71
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 50
    new-instance v0, Ljava/util/TreeMap;

    invoke-direct {v0}, Ljava/util/TreeMap;-><init>()V

    iput-object v0, p0, Lorg/mvel2/sh/ShellSession;->commands:Ljava/util/Map;

    .line 54
    new-instance v1, Ljava/util/Stack;

    invoke-direct {v1}, Ljava/util/Stack;-><init>()V

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->ctxStack:Ljava/util/Stack;

    .line 56
    new-instance v1, Lorg/mvel2/ParserContext;

    invoke-direct {v1}, Lorg/mvel2/ParserContext;-><init>()V

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->pCtx:Lorg/mvel2/ParserContext;

    const/4 v1, 0x0

    .line 62
    iput-boolean v1, p0, Lorg/mvel2/sh/ShellSession;->multi:Z

    .line 63
    iput v1, p0, Lorg/mvel2/sh/ShellSession;->multiIndentSize:I

    .line 64
    sget-object v1, Ljava/lang/System;->out:Ljava/io/PrintStream;

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    .line 67
    new-instance v1, Lorg/mvel2/util/StringAppender;

    invoke-direct {v1}, Lorg/mvel2/util/StringAppender;-><init>()V

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    .line 69
    new-instance v1, Ljava/io/BufferedReader;

    new-instance v2, Ljava/io/InputStreamReader;

    sget-object v3, Ljava/lang/System;->in:Ljava/io/InputStream;

    invoke-direct {v2, v3}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    invoke-direct {v1, v2}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->readBuffer:Ljava/io/BufferedReader;

    .line 72
    sget-object v1, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v2, "Starting session..."

    invoke-virtual {v1, v2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 74
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->variables:Ljava/util/Map;

    .line 75
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    .line 77
    new-instance v1, Lorg/mvel2/sh/command/basic/BasicCommandSet;

    invoke-direct {v1}, Lorg/mvel2/sh/command/basic/BasicCommandSet;-><init>()V

    invoke-virtual {v1}, Lorg/mvel2/sh/command/basic/BasicCommandSet;->load()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->putAll(Ljava/util/Map;)V

    .line 78
    new-instance v1, Lorg/mvel2/sh/command/file/FileCommandSet;

    invoke-direct {v1}, Lorg/mvel2/sh/command/file/FileCommandSet;-><init>()V

    invoke-virtual {v1}, Lorg/mvel2/sh/command/file/FileCommandSet;->load()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->putAll(Ljava/util/Map;)V

    .line 80
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v1, "$PROMPT"

    const-string v2, "[@{ new java.text.SimpleDateFormat(\'hh:mmaa\').format(new java.util.Date(System.currentTimeMillis()))}] mvel2$ "

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 81
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v1, "os.name"

    invoke-static {v1}, Ljava/lang/System;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "$OS_NAME"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 82
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "os.version"

    invoke-static {v2}, Ljava/lang/System;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "$OS_VERSION"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 83
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "$JAVA_VERSION"

    invoke-static {}, Lorg/mvel2/util/PropertyTools;->getJavaVersion()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 84
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    new-instance v2, Ljava/io/File;

    const-string v3, "."

    invoke-direct {v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    const-string v3, "$CWD"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 85
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "$COMMAND_PASSTRU"

    const-string v3, "false"

    invoke-interface {v0, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 86
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "$PRINTOUTPUT"

    const-string/jumbo v4, "true"

    invoke-interface {v0, v2, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 87
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "$ECHO"

    invoke-interface {v0, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 88
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "$SHOW_TRACES"

    invoke-interface {v0, v2, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 89
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "$USE_OPTIMIZER_ALWAYS"

    invoke-interface {v0, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 90
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, ""

    const-string v3, "$PATH"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 93
    :try_start_0
    const-string v0, ".mvelsh.properties"

    invoke-static {v0}, Ljava/util/ResourceBundle;->getBundle(Ljava/lang/String;)Ljava/util/ResourceBundle;

    move-result-object v0

    .line 95
    invoke-virtual {v0}, Ljava/util/ResourceBundle;->getKeys()Ljava/util/Enumeration;

    move-result-object v2

    .line 97
    :goto_0
    invoke-interface {v2}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v4

    if-eqz v4, :cond_0

    .line 98
    iget-object v4, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    invoke-interface {v2}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-virtual {v0, v5}, Ljava/util/ResourceBundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-interface {v4, v5, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/util/MissingResourceException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 102
    :catch_0
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v2, "No config file found.  Loading default config."

    invoke-virtual {v0, v2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 104
    invoke-static {v1}, Ljava/lang/System;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    const-string/jumbo v1, "windows"

    invoke-static {v0, v1}, Lorg/mvel2/util/PropertyTools;->contains(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 105
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v1, "/bin:/usr/bin:/sbin:/usr/sbin"

    invoke-interface {v0, v3, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 110
    :cond_0
    new-instance v0, Lorg/mvel2/integration/impl/MapVariableResolverFactory;

    iget-object v1, p0, Lorg/mvel2/sh/ShellSession;->variables:Ljava/util/Map;

    new-instance v2, Lorg/mvel2/integration/impl/MapVariableResolverFactory;

    iget-object v3, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    invoke-direct {v2, v3}, Lorg/mvel2/integration/impl/MapVariableResolverFactory;-><init>(Ljava/util/Map;)V

    invoke-direct {v0, v1, v2}, Lorg/mvel2/integration/impl/MapVariableResolverFactory;-><init>(Ljava/util/Map;Lorg/mvel2/integration/VariableResolverFactory;)V

    iput-object v0, p0, Lorg/mvel2/sh/ShellSession;->lvrf:Lorg/mvel2/integration/VariableResolverFactory;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .locals 0

    .line 115
    invoke-direct {p0}, Lorg/mvel2/sh/ShellSession;-><init>()V

    .line 116
    invoke-virtual {p0, p1}, Lorg/mvel2/sh/ShellSession;->exec(Ljava/lang/String;)V

    return-void
.end method

.method private _exec()V
    .locals 16

    move-object/from16 v1, p0

    .line 123
    sget-object v5, Ljava/lang/System;->out:Ljava/io/PrintStream;

    .line 129
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v2, "$ECHO"

    invoke-interface {v0, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    const-string/jumbo v2, "true"

    invoke-virtual {v2, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 130
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, ">"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v4, v1, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 131
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    invoke-virtual {v0}, Ljava/io/PrintStream;->flush()V

    .line 134
    :cond_0
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    iget-object v3, v1, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    invoke-virtual {v0, v3}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    move-result-object v0

    invoke-virtual {v0}, Lorg/mvel2/util/StringAppender;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v3, "\\s"

    invoke-virtual {v0, v3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v6

    .line 136
    array-length v0, v6

    const/4 v3, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x1

    if-eqz v0, :cond_2

    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->commands:Ljava/util/Map;

    aget-object v4, v6, v7

    invoke-interface {v0, v4}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 138
    iput-object v3, v1, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    .line 141
    array-length v0, v6

    if-le v0, v8, :cond_1

    .line 142
    array-length v0, v6

    sub-int/2addr v0, v8

    new-array v2, v0, [Ljava/lang/String;

    invoke-static {v6, v8, v2, v7, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    goto :goto_0

    .line 145
    :cond_1
    sget-object v2, Lorg/mvel2/sh/ShellSession;->EMPTY:[Ljava/lang/String;

    .line 149
    :goto_0
    :try_start_0
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->commands:Ljava/util/Map;

    aget-object v3, v6, v7

    invoke-interface {v0, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/mvel2/sh/Command;

    invoke-interface {v0, v1, v2}, Lorg/mvel2/sh/Command;->execute(Lorg/mvel2/sh/ShellSession;[Ljava/lang/String;)Ljava/lang/Object;
    :try_end_0
    .catch Lorg/mvel2/sh/CommandException; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_2

    :catch_0
    move-exception v0

    .line 152
    iget-object v2, v1, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    const-string v3, "Error: "

    invoke-virtual {v2, v3}, Ljava/io/PrintStream;->append(Ljava/lang/CharSequence;)Ljava/io/PrintStream;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/io/PrintStream;->append(Ljava/lang/CharSequence;)Ljava/io/PrintStream;

    move-result-object v0

    const-string v2, "\n"

    invoke-virtual {v0, v2}, Ljava/io/PrintStream;->append(Ljava/lang/CharSequence;)Ljava/io/PrintStream;

    goto/16 :goto_2

    .line 156
    :cond_2
    iput-object v3, v1, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    .line 159
    :try_start_1
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    invoke-virtual {v1, v0}, Lorg/mvel2/sh/ShellSession;->shouldDefer(Lorg/mvel2/util/StringAppender;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 160
    iput-boolean v8, v1, Lorg/mvel2/sh/ShellSession;->multi:Z

    return-void

    :catch_1
    move-exception v0

    move-object v9, v0

    goto :goto_3

    .line 164
    :cond_3
    iput-boolean v7, v1, Lorg/mvel2/sh/ShellSession;->multi:Z

    .line 167
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v3, "$USE_OPTIMIZER_ALWAYS"

    invoke-interface {v0, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-static {v0}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 168
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    invoke-virtual {v0}, Lorg/mvel2/util/StringAppender;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/mvel2/MVEL;->compileExpression(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v0

    iget-object v3, v1, Lorg/mvel2/sh/ShellSession;->ctxObject:Ljava/lang/Object;

    iget-object v4, v1, Lorg/mvel2/sh/ShellSession;->lvrf:Lorg/mvel2/integration/VariableResolverFactory;

    invoke-static {v0, v3, v4}, Lorg/mvel2/MVEL;->executeExpression(Ljava/lang/Object;Ljava/lang/Object;Lorg/mvel2/integration/VariableResolverFactory;)Ljava/lang/Object;

    move-result-object v0

    goto :goto_1

    .line 171
    :cond_4
    new-instance v0, Lorg/mvel2/MVELInterpretedRuntime;

    iget-object v3, v1, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    invoke-virtual {v3}, Lorg/mvel2/util/StringAppender;->toString()Ljava/lang/String;

    move-result-object v3

    iget-object v4, v1, Lorg/mvel2/sh/ShellSession;->ctxObject:Ljava/lang/Object;

    iget-object v9, v1, Lorg/mvel2/sh/ShellSession;->lvrf:Lorg/mvel2/integration/VariableResolverFactory;

    iget-object v10, v1, Lorg/mvel2/sh/ShellSession;->pCtx:Lorg/mvel2/ParserContext;

    invoke-direct {v0, v3, v4, v9, v10}, Lorg/mvel2/MVELInterpretedRuntime;-><init>(Ljava/lang/String;Ljava/lang/Object;Lorg/mvel2/integration/VariableResolverFactory;Lorg/mvel2/ParserContext;)V

    .line 172
    invoke-virtual {v0}, Lorg/mvel2/MVELInterpretedRuntime;->parse()Ljava/lang/Object;

    move-result-object v0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    :goto_1
    if-eqz v0, :cond_6

    .line 346
    iget-object v3, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v4, "$PRINTOUTPUT"

    invoke-interface {v3, v4}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_6

    .line 347
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->isArray()Z

    move-result v2

    .line 351
    iget-object v3, v1, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    if-eqz v2, :cond_5

    .line 348
    check-cast v0, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto :goto_2

    .line 351
    :cond_5
    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 358
    :cond_6
    :goto_2
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    invoke-virtual {v0}, Lorg/mvel2/util/StringAppender;->reset()V

    return-void

    .line 176
    :goto_3
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v3, "$COMMAND_PASSTHRU"

    invoke-interface {v0, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_a

    .line 180
    aget-object v0, v6, v7

    const-string v2, "./"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_7

    .line 181
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    new-instance v3, Ljava/io/File;

    iget-object v4, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v10, "$CWD"

    invoke-interface {v4, v10}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-direct {v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v3, 0x2f

    invoke-virtual {v0, v3}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    invoke-virtual {v0, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 183
    new-array v2, v8, [Ljava/lang/String;

    aput-object v0, v2, v7

    :goto_4
    move-object v10, v0

    move-object v11, v2

    goto :goto_5

    .line 186
    :cond_7
    iget-object v2, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v3, "$PATH"

    invoke-interface {v2, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    const-string v3, "(:|;)"

    invoke-virtual {v2, v3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    goto :goto_4

    .line 192
    :goto_5
    array-length v12, v11

    move v0, v7

    move v13, v0

    :goto_6
    if-ge v13, v12, :cond_9

    aget-object v2, v11, v13

    .line 193
    new-instance v3, Ljava/io/File;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "/"

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v3, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_8

    invoke-virtual {v3}, Ljava/io/File;->isFile()Z

    move-result v2

    if-eqz v2, :cond_8

    .line 196
    array-length v0, v6

    new-array v0, v0, [Ljava/lang/String;

    .line 197
    invoke-virtual {v3}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    aput-object v2, v0, v7

    .line 199
    array-length v2, v6

    sub-int/2addr v2, v8

    invoke-static {v6, v8, v0, v8, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 202
    :try_start_2
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/Runtime;->exec([Ljava/lang/String;)Ljava/lang/Process;

    move-result-object v4

    .line 203
    invoke-virtual {v4}, Ljava/lang/Process;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v3

    .line 205
    invoke-virtual {v4}, Ljava/lang/Process;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    .line 206
    invoke-virtual {v4}, Ljava/lang/Process;->getErrorStream()Ljava/io/InputStream;

    .line 208
    new-instance v2, Lorg/mvel2/sh/ShellSession$RunState;

    invoke-direct {v2, v1}, Lorg/mvel2/sh/ShellSession$RunState;-><init>(Lorg/mvel2/sh/ShellSession;)V

    .line 210
    new-instance v14, Ljava/lang/Thread;

    new-instance v15, Lorg/mvel2/sh/ShellSession$1;

    invoke-direct {v15, v1, v0, v5, v2}, Lorg/mvel2/sh/ShellSession$1;-><init>(Lorg/mvel2/sh/ShellSession;Ljava/io/InputStream;Ljava/io/PrintStream;Lorg/mvel2/sh/ShellSession$RunState;)V

    invoke-direct {v14, v15}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 245
    new-instance v15, Ljava/lang/Thread;

    new-instance v0, Lorg/mvel2/sh/ShellSession$2;

    invoke-direct/range {v0 .. v5}, Lorg/mvel2/sh/ShellSession$2;-><init>(Lorg/mvel2/sh/ShellSession;Lorg/mvel2/sh/ShellSession$RunState;Ljava/io/OutputStream;Ljava/lang/Process;Ljava/io/PrintStream;)V

    invoke-direct {v15, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 298
    invoke-virtual {v14, v8}, Ljava/lang/Thread;->setPriority(I)V

    .line 299
    invoke-virtual {v14}, Ljava/lang/Thread;->start()V

    .line 301
    invoke-virtual {v15, v8}, Ljava/lang/Thread;->setPriority(I)V

    .line 302
    invoke-virtual {v15}, Ljava/lang/Thread;->start()V

    .line 303
    invoke-virtual {v15}, Ljava/lang/Thread;->join()V

    .line 307
    invoke-virtual {v14}, Ljava/lang/Object;->notify()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    :catch_2
    move v0, v8

    :cond_8
    add-int/lit8 v13, v13, 0x1

    goto :goto_6

    :cond_9
    if-eqz v0, :cond_a

    .line 321
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    invoke-virtual {v0}, Lorg/mvel2/util/StringAppender;->reset()V

    return-void

    .line 326
    :cond_a
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v0}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 327
    new-instance v2, Ljava/io/PrintStream;

    invoke-direct {v2, v0}, Ljava/io/PrintStream;-><init>(Ljava/io/OutputStream;)V

    .line 329
    invoke-virtual {v9, v2}, Ljava/lang/Throwable;->printStackTrace(Ljava/io/PrintStream;)V

    .line 330
    invoke-virtual {v2}, Ljava/io/PrintStream;->flush()V

    .line 332
    iget-object v2, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    new-instance v3, Ljava/lang/String;

    invoke-virtual {v0}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v0

    invoke-direct {v3, v0}, Ljava/lang/String;-><init>([B)V

    const-string v0, "$LAST_STACK_TRACE"

    invoke-interface {v2, v0, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 333
    iget-object v2, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v3, "$SHOW_TRACE"

    invoke-interface {v2, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-static {v2}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v2

    .line 337
    iget-object v3, v1, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    if-eqz v2, :cond_b

    .line 334
    iget-object v2, v1, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    invoke-interface {v2, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-virtual {v3, v0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto :goto_7

    .line 337
    :cond_b
    invoke-virtual {v9}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 340
    :goto_7
    iget-object v0, v1, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    invoke-virtual {v0}, Lorg/mvel2/util/StringAppender;->reset()V

    return-void
.end method

.method public static synthetic access$000(Lorg/mvel2/sh/ShellSession;)Z
    .locals 0

    .line 46
    iget-boolean p0, p0, Lorg/mvel2/sh/ShellSession;->multi:Z

    return p0
.end method

.method public static synthetic access$100(Lorg/mvel2/sh/ShellSession;)I
    .locals 0

    .line 46
    iget p0, p0, Lorg/mvel2/sh/ShellSession;->multiIndentSize:I

    return p0
.end method

.method public static synthetic access$102(Lorg/mvel2/sh/ShellSession;I)I
    .locals 0

    .line 46
    iput p1, p0, Lorg/mvel2/sh/ShellSession;->multiIndentSize:I

    return p1
.end method

.method public static synthetic access$200(Lorg/mvel2/sh/ShellSession;)Ljava/lang/String;
    .locals 0

    .line 46
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->prompt:Ljava/lang/String;

    return-object p0
.end method

.method public static synthetic access$202(Lorg/mvel2/sh/ShellSession;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 46
    iput-object p1, p0, Lorg/mvel2/sh/ShellSession;->prompt:Ljava/lang/String;

    return-object p1
.end method

.method public static synthetic access$300(Lorg/mvel2/sh/ShellSession;)Ljava/util/Map;
    .locals 0

    .line 46
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    return-object p0
.end method

.method public static synthetic access$400(Lorg/mvel2/sh/ShellSession;)Ljava/util/Map;
    .locals 0

    .line 46
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->variables:Ljava/util/Map;

    return-object p0
.end method

.method public static synthetic access$500(Lorg/mvel2/sh/ShellSession;)Ljava/io/PrintStream;
    .locals 0

    .line 46
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    return-object p0
.end method

.method public static synthetic access$600(Lorg/mvel2/sh/ShellSession;)I
    .locals 0

    .line 46
    iget p0, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    return p0
.end method


# virtual methods
.method public exec(Ljava/lang/String;)V
    .locals 4

    .line 479
    const-string v0, "\n"

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    array-length v0, p1

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_0

    aget-object v2, p1, v1

    .line 480
    iget-object v3, p0, Lorg/mvel2/sh/ShellSession;->inBuffer:Lorg/mvel2/util/StringAppender;

    invoke-virtual {v3, v2}, Lorg/mvel2/util/StringAppender;->append(Ljava/lang/String;)Lorg/mvel2/util/StringAppender;

    .line 481
    invoke-direct {p0}, Lorg/mvel2/sh/ShellSession;->_exec()V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public getCommandBuffer()Ljava/lang/String;
    .locals 0

    .line 471
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    return-object p0
.end method

.method public getCommands()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lorg/mvel2/sh/Command;",
            ">;"
        }
    .end annotation

    .line 434
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->commands:Ljava/util/Map;

    return-object p0
.end method

.method public getCtxObject()Ljava/lang/Object;
    .locals 0

    .line 446
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->ctxObject:Ljava/lang/Object;

    return-object p0
.end method

.method public getEnv()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 442
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    return-object p0
.end method

.method public getVariables()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation

    .line 438
    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->variables:Ljava/util/Map;

    return-object p0
.end method

.method public indent(I)Ljava/lang/String;
    .locals 2

    .line 428
    new-instance p0, Ljava/lang/StringBuffer;

    invoke-direct {p0}, Ljava/lang/StringBuffer;-><init>()V

    const/4 v0, 0x0

    :goto_0
    if-ge v0, p1, :cond_0

    .line 429
    const-string v1, " "

    invoke-virtual {p0, v1}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 430
    :cond_0
    invoke-virtual {p0}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public popCtxObject()Z
    .locals 1

    .line 463
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->ctxStack:Ljava/util/Stack;

    invoke-virtual {v0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 464
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->ctxStack:Ljava/util/Stack;

    invoke-virtual {v0}, Ljava/util/Stack;->pop()Ljava/lang/Object;

    move-result-object v0

    iput-object v0, p0, Lorg/mvel2/sh/ShellSession;->ctxObject:Ljava/lang/Object;

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public printPrompt()V
    .locals 3

    .line 387
    iget-boolean v0, p0, Lorg/mvel2/sh/ShellSession;->multi:Z

    if-nez v0, :cond_0

    .line 388
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->env:Ljava/util/Map;

    const-string v1, "$PROMPT"

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    iget-object v1, p0, Lorg/mvel2/sh/ShellSession;->variables:Ljava/util/Map;

    invoke-static {v0, v1}, Lorg/mvel2/templates/TemplateRuntime;->eval(Ljava/lang/String;Ljava/util/Map;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/mvel2/sh/ShellSession;->prompt:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    iput v0, p0, Lorg/mvel2/sh/ShellSession;->multiIndentSize:I

    .line 389
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    iget-object p0, p0, Lorg/mvel2/sh/ShellSession;->prompt:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/io/PrintStream;->append(Ljava/lang/CharSequence;)Ljava/io/PrintStream;

    return-void

    .line 392
    :cond_0
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->out:Ljava/io/PrintStream;

    const-string v1, ">"

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->append(Ljava/lang/CharSequence;)Ljava/io/PrintStream;

    move-result-object v0

    iget v1, p0, Lorg/mvel2/sh/ShellSession;->multiIndentSize:I

    add-int/lit8 v1, v1, -0x1

    iget v2, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    mul-int/lit8 v2, v2, 0x4

    add-int/2addr v1, v2

    invoke-virtual {p0, v1}, Lorg/mvel2/sh/ShellSession;->indent(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/io/PrintStream;->append(Ljava/lang/CharSequence;)Ljava/io/PrintStream;

    return-void
.end method

.method public pushCtxObject(Ljava/lang/Object;)Z
    .locals 2

    .line 454
    iget-object v0, p0, Lorg/mvel2/sh/ShellSession;->ctxObject:Ljava/lang/Object;

    if-eq v0, p1, :cond_0

    .line 455
    iget-object v1, p0, Lorg/mvel2/sh/ShellSession;->ctxStack:Ljava/util/Stack;

    invoke-virtual {v1, v0}, Ljava/util/Stack;->push(Ljava/lang/Object;)Ljava/lang/Object;

    .line 456
    iput-object p1, p0, Lorg/mvel2/sh/ShellSession;->ctxObject:Ljava/lang/Object;

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public run()V
    .locals 3

    .line 365
    new-instance v0, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    sget-object v2, Ljava/lang/System;->in:Ljava/io/InputStream;

    invoke-direct {v1, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    invoke-direct {v0, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 370
    :goto_0
    :try_start_0
    invoke-virtual {p0}, Lorg/mvel2/sh/ShellSession;->printPrompt()V

    .line 372
    iget-object v1, p0, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    if-nez v1, :cond_0

    .line 373
    invoke-virtual {v0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    .line 376
    :cond_0
    invoke-direct {p0}, Lorg/mvel2/sh/ShellSession;->_exec()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 380
    invoke-virtual {p0}, Ljava/lang/Throwable;->printStackTrace()V

    .line 381
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string/jumbo v0, "unexpected exception. exiting."

    invoke-virtual {p0, v0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-void
.end method

.method public setCommandBuffer(Ljava/lang/String;)V
    .locals 0

    .line 475
    iput-object p1, p0, Lorg/mvel2/sh/ShellSession;->commandBuffer:Ljava/lang/String;

    return-void
.end method

.method public setCtxObject(Ljava/lang/Object;)V
    .locals 0

    .line 450
    invoke-virtual {p0, p1}, Lorg/mvel2/sh/ShellSession;->pushCtxObject(Ljava/lang/Object;)Z

    return-void
.end method

.method public shouldDefer(Lorg/mvel2/util/StringAppender;)Z
    .locals 7

    .line 398
    invoke-virtual {p1}, Lorg/mvel2/util/StringAppender;->length()I

    move-result v0

    new-array v1, v0, [C

    .line 399
    invoke-virtual {p1}, Lorg/mvel2/util/StringAppender;->length()I

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {p1, v3, v2, v1, v3}, Lorg/mvel2/util/StringAppender;->getChars(II[CI)V

    .line 401
    iput v3, p0, Lorg/mvel2/sh/ShellSession;->cdepth:I

    iput v3, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    move p1, v3

    :goto_0
    const/4 v2, 0x1

    if-ge p1, v0, :cond_5

    .line 403
    aget-char v4, v1, p1

    const/16 v5, 0x2f

    const/16 v6, 0x2a

    if-eq v4, v6, :cond_3

    if-eq v4, v5, :cond_2

    const/16 v5, 0x7b

    if-eq v4, v5, :cond_1

    const/16 v5, 0x7d

    if-eq v4, v5, :cond_0

    goto :goto_1

    .line 419
    :cond_0
    iget v4, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    sub-int/2addr v4, v2

    iput v4, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    goto :goto_1

    .line 416
    :cond_1
    iget v4, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    add-int/2addr v4, v2

    iput v4, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    goto :goto_1

    :cond_2
    add-int/lit8 v4, p1, 0x1

    if-ge v4, v0, :cond_4

    .line 405
    aget-char v4, v1, v4

    if-ne v4, v6, :cond_4

    .line 406
    iget v4, p0, Lorg/mvel2/sh/ShellSession;->cdepth:I

    add-int/2addr v4, v2

    iput v4, p0, Lorg/mvel2/sh/ShellSession;->cdepth:I

    goto :goto_1

    :cond_3
    add-int/lit8 v4, p1, 0x1

    if-ge v4, v0, :cond_4

    .line 410
    aget-char v4, v1, v4

    if-ne v4, v5, :cond_4

    .line 411
    iget v4, p0, Lorg/mvel2/sh/ShellSession;->cdepth:I

    sub-int/2addr v4, v2

    iput v4, p0, Lorg/mvel2/sh/ShellSession;->cdepth:I

    :cond_4
    :goto_1
    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 424
    :cond_5
    iget p1, p0, Lorg/mvel2/sh/ShellSession;->depth:I

    iget p0, p0, Lorg/mvel2/sh/ShellSession;->cdepth:I

    add-int/2addr p1, p0

    if-lez p1, :cond_6

    return v2

    :cond_6
    return v3
.end method
