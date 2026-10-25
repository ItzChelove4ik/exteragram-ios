.class abstract Lorg/telegram/utils/settings/SettingsPreferences$Holder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/settings/SettingsPreferences;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Holder"
.end annotation


# static fields
.field private static final INSTANCE:Landroid/content/SharedPreferences;


# direct methods
.method public static bridge synthetic -$$Nest$sfgetINSTANCE()Landroid/content/SharedPreferences;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/utils/settings/SettingsPreferences$Holder;->INSTANCE:Landroid/content/SharedPreferences;

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 18
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v1, "mainconfig"

    const/4 v2, 0x0

    .line 19
    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SettingsPreferences$Holder;->INSTANCE:Landroid/content/SharedPreferences;

    return-void
.end method
