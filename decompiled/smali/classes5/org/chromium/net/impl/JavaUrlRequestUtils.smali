.class public abstract Lorg/chromium/net/impl/JavaUrlRequestUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/chromium/net/impl/JavaUrlRequestUtils$DirectPreventingExecutor;,
        Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;
    }
.end annotation


# direct methods
.method public static stateToString(I)Ljava/lang/String;
    .locals 1

    packed-switch p0, :pswitch_data_0

    .line 76
    const-string v0, "Unknown state "

    invoke-static {v0, p0}, Lkotlin/CharCodeKt$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    const/4 p0, 0x0

    return-object p0

    .line 74
    :pswitch_0
    const-string p0, "CANCELLED"

    return-object p0

    .line 72
    :pswitch_1
    const-string p0, "COMPLETE"

    return-object p0

    .line 70
    :pswitch_2
    const-string p0, "ERROR"

    return-object p0

    .line 68
    :pswitch_3
    const-string p0, "READING"

    return-object p0

    .line 66
    :pswitch_4
    const-string p0, "AWAITING_READ"

    return-object p0

    .line 64
    :pswitch_5
    const-string p0, "AWAITING_FOLLOW_REDIRECT"

    return-object p0

    .line 62
    :pswitch_6
    const-string p0, "REDIRECT_RECEIVED"

    return-object p0

    .line 60
    :pswitch_7
    const-string p0, "STARTED"

    return-object p0

    .line 58
    :pswitch_8
    const-string p0, "NOT_STARTED"

    return-object p0

    nop

    :pswitch_data_0
    .packed-switch 0x0
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
