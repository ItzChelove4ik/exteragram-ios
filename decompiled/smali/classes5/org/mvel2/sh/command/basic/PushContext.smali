.class public Lorg/mvel2/sh/command/basic/PushContext;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/mvel2/sh/Command;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 24
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public execute(Lorg/mvel2/sh/ShellSession;[Ljava/lang/String;)Ljava/lang/Object;
    .locals 2

    .line 30
    array-length p0, p2

    if-nez p0, :cond_0

    const/4 p0, 0x0

    .line 31
    invoke-virtual {p1, p0}, Lorg/mvel2/sh/ShellSession;->pushCtxObject(Ljava/lang/Object;)Z

    move-result p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    .line 35
    :try_start_0
    const-string v0, " "

    invoke-static {v0, p2}, Lcom/exteragram/messenger/backup/InvisibleEncryptor$$ExternalSyntheticBackport0;->m(Ljava/lang/CharSequence;[Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getCtxObject()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getVariables()Ljava/util/Map;

    move-result-object v1

    invoke-static {p2, v0, v1}, Lorg/mvel2/MVEL;->eval(Ljava/lang/String;Ljava/lang/Object;Ljava/util/Map;)Ljava/lang/Object;

    move-result-object p2

    if-eqz p2, :cond_1

    .line 37
    invoke-virtual {p1, p2}, Lorg/mvel2/sh/ShellSession;->pushCtxObject(Ljava/lang/Object;)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p2

    .line 41
    sget-object v0, Ljava/lang/System;->err:Ljava/io/PrintStream;

    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v0, p2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 44
    :cond_1
    :goto_0
    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getCtxObject()Ljava/lang/Object;

    move-result-object p1

    if-eqz p0, :cond_2

    .line 46
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "Pushed context to "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    :cond_2
    return-object p1
.end method

.method public getDescription()Ljava/lang/String;
    .locals 0

    .line 52
    const-string p0, "pushes the given variable to the current context"

    return-object p0
.end method

.method public getHelp()Ljava/lang/String;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method
