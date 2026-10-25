.class abstract Lorg/telegram/utils/settings/SettingsPreferences;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/settings/SettingsPreferences$Holder;
    }
.end annotation


# direct methods
.method public static get()Landroid/content/SharedPreferences;
    .locals 1

    .line 14
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences$Holder;->-$$Nest$sfgetINSTANCE()Landroid/content/SharedPreferences;

    move-result-object v0

    return-object v0
.end method
