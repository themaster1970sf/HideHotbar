package dev.lutsu.hidehotbar.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
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

        general.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.enabled"),
                                ToolBarConfig.enabled
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.enabled = value)
                        .build()
        );

        ConfigCategory unhide = builder.getOrCreateCategory(
                Text.translatable("hidehotbar.category.unhide_if")
        );

        unhide.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.damage"),
                                ToolBarConfig.unhide_on_damage
                        )
                        .setTooltip(Text.translatable("hidehotbar.option.unhideon.damage.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.unhide_on_damage = value)
                        .build()
        );
        unhide.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.low_hp"),
                                ToolBarConfig.unhide_on_low_hp
                        )
                        .setTooltip(Text.translatable("hidehotbar.option.unhideon.low_hp.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.unhide_on_low_hp = value)
                        .build()
        );

        unhide.addEntry(
                entryBuilder.startIntSlider(
                        Text.translatable("hidehotbar.option.unhideon.low_hp_level"),
                        1, 1, 100
                ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_hp_level.tooltip"))
                        .setDefaultValue(20)
                        .setSaveConsumer(value -> ToolBarConfig.low_hp_percentage = value)
                        .build()
        );

        builder.setSavingRunnable(ToolBarConfig::save);

        return builder.build();
    }
}
