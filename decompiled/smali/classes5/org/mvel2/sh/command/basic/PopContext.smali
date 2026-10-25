.class public Lorg/mvel2/sh/command/basic/PopContext;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/mvel2/sh/Command;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 23
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public execute(Lorg/mvel2/sh/ShellSession;[Ljava/lang/String;)Ljava/lang/Object;
    .locals 1

    .line 26
    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->popCtxObject()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 27
    sget-object p0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "Popped context to "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getCtxObject()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 29
    :cond_0
    invoke-virtual {p1}, Lorg/mvel2/sh/ShellSession;->getCtxObject()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public getDescription()Ljava/lang/String;
    .locals 0

    .line 33
    const-string p0, "pops the previous context from the context queue"

    return-object p0
.end method

.method public getHelp()Ljava/lang/String;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method
