/*
 * This file is part of the Illarion Client.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion Client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Client.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.client.world.items;

import illarion.common.types.ItemId;

/**
 * This item is a ingredient to a craft.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class CraftingIngredientItem {
    /**
     * The ID of the ingredient.
     */
    private final ItemId itemId;

    /**
     * The amount of items required.
     */
    private final int count;

    /**
     * Constructor used to set the item.
     *
     * @param itemId the ID of the item
     * @param count  the amount of items required
     */
    public CraftingIngredientItem(final ItemId itemId, final int count) {
        this.itemId = itemId;
        this.count = count;
    }

    /**
     * The ID of this ingredient item.
     *
     * @return the item id
     */
    public ItemId getItemId() {
        return itemId;
    }

    /**
     * The count of items.
     *
     * @return the amount of items
     */
    public int getCount() {
        return count;
    }
}
