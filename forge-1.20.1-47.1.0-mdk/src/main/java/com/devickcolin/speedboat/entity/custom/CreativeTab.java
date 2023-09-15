package com.devickcolin.speedboat.entity.custom;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

public enum CreativeTab {
	TOOLS_AND_UTILITIES(CreativeModeTabs.TOOLS_AND_UTILITIES),
	DEFAULT();

	private ResourceKey<CreativeModeTab> tab;

	CreativeTab(ResourceKey<CreativeModeTab> toolsAndUtilities) {
		this.tab = toolsAndUtilities;
	}

	CreativeTab() {
	}

	private ResourceKey<CreativeModeTab> getKey() {
		return tab;
	}

	static public CreativeTab keyGetter(ResourceKey<CreativeModeTab> tab2) {
		for (CreativeTab c : CreativeTab.values()) {

			if (c.getKey() == tab2) {
				return c;
			}

		}
		return DEFAULT;
	}
}
