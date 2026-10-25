.class public final Lorg/chromium/net/httpflags/FlagValue$Builder;
.super Lcom/google/protobuf/GeneratedMessageLite$Builder;
.source "SourceFile"

# interfaces
.implements Lorg/chromium/net/httpflags/FlagValueOrBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/httpflags/FlagValue;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/google/protobuf/GeneratedMessageLite$Builder<",
        "Lorg/chromium/net/httpflags/FlagValue;",
        "Lorg/chromium/net/httpflags/FlagValue$Builder;",
        ">;",
        "Lorg/chromium/net/httpflags/FlagValueOrBuilder;"
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 1

    .line 1864
    invoke-static {}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$sfgetDEFAULT_INSTANCE()Lorg/chromium/net/httpflags/FlagValue;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;-><init>(Lcom/google/protobuf/GeneratedMessageLite;)V

    return-void
.end method

.method public synthetic constructor <init>(Lorg/chromium/net/httpflags/FlagValue-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/httpflags/FlagValue$Builder;-><init>()V

    return-void
.end method


# virtual methods
.method public addAllConstrainedValues(Ljava/lang/Iterable;)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Iterable<",
            "+",
            "Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;",
            ">;)",
            "Lorg/chromium/net/httpflags/FlagValue$Builder;"
        }
    .end annotation

    .line 2009
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 2010
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-static {v0, p1}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$maddAllConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;Ljava/lang/Iterable;)V

    return-object p0
.end method

.method public addConstrainedValues(ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue$Builder;)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 1993
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 1994
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    .line 1995
    invoke-virtual {p2}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->build()Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p2

    check-cast p2, Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;

    .line 1994
    invoke-static {v0, p1, p2}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$maddConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)V

    return-object p0
.end method

.method public addConstrainedValues(ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 1963
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 1964
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-static {v0, p1, p2}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$maddConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)V

    return-object p0
.end method

.method public addConstrainedValues(Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue$Builder;)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 1978
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 1979
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-virtual {p1}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->build()Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p1

    check-cast p1, Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;

    invoke-static {v0, p1}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$maddConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)V

    return-object p0
.end method

.method public addConstrainedValues(Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 1948
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 1949
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-static {v0, p1}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$maddConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)V

    return-object p0
.end method

.method public clearConstrainedValues()Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 2023
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 2024
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-static {v0}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$mclearConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;)V

    return-object p0
.end method

.method public getConstrainedValues(I)Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;
    .locals 0

    .line 1905
    iget-object p0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast p0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-virtual {p0, p1}, Lorg/chromium/net/httpflags/FlagValue;->getConstrainedValues(I)Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;

    move-result-object p0

    return-object p0
.end method

.method public getConstrainedValuesCount()I
    .locals 0

    .line 1893
    iget-object p0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast p0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-virtual {p0}, Lorg/chromium/net/httpflags/FlagValue;->getConstrainedValuesCount()I

    move-result p0

    return p0
.end method

.method public getConstrainedValuesList()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;",
            ">;"
        }
    .end annotation

    .line 1879
    iget-object p0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast p0, Lorg/chromium/net/httpflags/FlagValue;

    .line 1880
    invoke-virtual {p0}, Lorg/chromium/net/httpflags/FlagValue;->getConstrainedValuesList()Ljava/util/List;

    move-result-object p0

    .line 1879
    invoke-static {p0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public removeConstrainedValues(I)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 2037
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 2038
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-static {v0, p1}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$mremoveConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;I)V

    return-object p0
.end method

.method public setConstrainedValues(ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue$Builder;)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 1933
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 1934
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    .line 1935
    invoke-virtual {p2}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->build()Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p2

    check-cast p2, Lorg/chromium/net/httpflags/FlagValue$ConstrainedValue;

    .line 1934
    invoke-static {v0, p1, p2}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$msetConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)V

    return-object p0
.end method

.method public setConstrainedValues(ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)Lorg/chromium/net/httpflags/FlagValue$Builder;
    .locals 1

    .line 1918
    invoke-virtual {p0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;->copyOnWrite()V

    .line 1919
    iget-object v0, p0, Lcom/google/protobuf/GeneratedMessageLite$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lorg/chromium/net/httpflags/FlagValue;

    invoke-static {v0, p1, p2}, Lorg/chromium/net/httpflags/FlagValue;->-$$Nest$msetConstrainedValues(Lorg/chromium/net/httpflags/FlagValue;ILorg/chromium/net/httpflags/FlagValue$ConstrainedValue;)V

    return-object p0
.end method
