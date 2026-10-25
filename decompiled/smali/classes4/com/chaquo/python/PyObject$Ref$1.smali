.class Lcom/chaquo/python/PyObject$Ref$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/chaquo/python/PyObject$Ref;->add()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/chaquo/python/PyObject$Ref;


# direct methods
.method public constructor <init>(Lcom/chaquo/python/PyObject$Ref;)V
    .locals 0

    .line 125
    iput-object p1, p0, Lcom/chaquo/python/PyObject$Ref$1;->this$0:Lcom/chaquo/python/PyObject$Ref;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 0

    .line 127
    invoke-static {}, Lcom/chaquo/python/PyObject$Ref;->access$000()V

    return-void
.end method
