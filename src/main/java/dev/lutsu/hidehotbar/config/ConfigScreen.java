package dev.lutsu.hidehotbar.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;

public class ConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("hidehotbar.title.config"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(
                Text.translatable("hidehotbar.category.general")
        );

        SubCategoryBuilder general_sub = entryBuilder.startSubCategory(
                Text.translatable("hidehotbar.category.general")
        );


        general_sub.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.enabled"),
                                ToolBarConfig.enabled
                        ).setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.enabled = value)
                        .build()
        );

        general_sub.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.is_hidden"),
                                ToolBarConfig.hid
                        ).setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.hid = value)
                        .build()
        );

        general_sub.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.debug"),
                                ToolBarConfig.debug
                        ).setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.debug = value)
                        .build()
        );

        general.addEntry(general_sub.build());

        SubCategoryBuilder unhide = entryBuilder.startSubCategory(
                Text.translatable("hidehotbar.category.unhide_if")
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.restart"),
                                ToolBarConfig.unhide_on_restart
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.damage.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.unhide_on_restart = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.slot_change"),
                                ToolBarConfig.unhide_on_slot_change
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.slot_change.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.unhide_on_slot_change = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.damage"),
                                ToolBarConfig.unhide_on_damage
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.damage.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.unhide_on_damage = value)
                        .build()
        );
        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.low_hp"),
                                ToolBarConfig.unhide_on_low_hp
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_hp.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.unhide_on_low_hp = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startIntSlider(
                                Text.translatable("hidehotbar.option.unhideon.low_hp_level"),
                                1, 1, 100
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_hp_level.tooltip"))
                        .setDefaultValue(20)
                        .setSaveConsumer(value -> ToolBarConfig.low_hp_percentage = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.low_food"),
                                ToolBarConfig.unhide_on_low_food
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_food.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.unhide_on_low_food = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startIntSlider(
                                Text.translatable("hidehotbar.option.unhideon.low_food_level"),
                                ToolBarConfig.low_food_percentage, 1, 100
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_food_level.tooltip"))
                        .setDefaultValue(20)
                        .setSaveConsumer(value -> ToolBarConfig.low_food_percentage = value)
                        .build()
        );

        general.addEntry(unhide.build());

        if (ToolBarConfig.debug) {
//            ConfigCategory devOptions =
            addDevCategory(builder);
        }

        builder.setSavingRunnable(ToolBarConfig::save);

        return builder.build();
    }

    private static void addDevBooleanToggle(
            ConfigCategory category,
            ConfigEntryBuilder entryBuilder,
            String title,
            boolean currentValue,
            java.util.function.Consumer<Boolean> saveConsumer
    ) {
        category.addEntry(
                entryBuilder.startBooleanToggle(Text.literal(title), currentValue)
                        .setDefaultValue(false)
                        .setSaveConsumer(saveConsumer)
                        .build()
        );
    }

    private static void addDevCategory(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory devOptions = builder.getOrCreateCategory(
                Text.literal("Dev options")
        );

        devOptions.setDescription(new StringVisitable[]{
                StringVisitable.plain("Use at your own risk!"),
                StringVisitable.plain("To disable them set debug to false"),
                StringVisitable.plain("No translations available")
        });

        addDevBooleanToggle(devOptions, entryBuilder,
                "Hide hotbar v1 mixin",
                ToolBarConfig.DEV_OPTIONS.hide_hotbar_v1,
                val -> ToolBarConfig.DEV_OPTIONS.hide_hotbar_v1 = val
        );

        addDevBooleanToggle(devOptions, entryBuilder,
                "Hide armor v1 mixin",
                ToolBarConfig.DEV_OPTIONS.hide_armor_v1,
                val -> ToolBarConfig.DEV_OPTIONS.hide_armor_v1 = val
        );

        addDevBooleanToggle(devOptions, entryBuilder,
                "Hide health bar v1 mixin",
                ToolBarConfig.DEV_OPTIONS.hide_healthbar_v1,
                val -> ToolBarConfig.DEV_OPTIONS.hide_healthbar_v1 = val
        );

        addDevBooleanToggle(devOptions, entryBuilder,
                "Hide food bar v1 mixin",
                ToolBarConfig.DEV_OPTIONS.hide_food_v1,
                val -> ToolBarConfig.DEV_OPTIONS.hide_food_v1 = val
        );

        addDevBooleanToggle(devOptions, entryBuilder,
                "Hide bubbles v1 mixin",
                ToolBarConfig.DEV_OPTIONS.hide_bubbles_v1,
                val -> ToolBarConfig.DEV_OPTIONS.hide_bubbles_v1 = val
        );

    }
}
