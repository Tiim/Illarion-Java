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
package illarion.client.gui.util;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.render.NiftyImage;
import illarion.client.gui.EntitySlickRenderImage;
import illarion.client.resources.ItemFactory;
import illarion.client.world.items.MerchantItem;
import org.illarion.nifty.controls.MerchantListEntry;

/**
 * This implementation of the merchant item is very similar to the original merchant item. It just adds a few entries
 * of data that are needed so the item can be displayed properly in the GUI.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class NiftyMerchantItem extends MerchantItem implements MerchantListEntry {
    /**
     * The image that represents this merchant item.
     */
    private final NiftyImage itemImage;

    /**
     * Create a new instance of that merchant item.
     * <p/>
     * This constructor performs changes to the Nifty-GUI. Do not call it outside the regular update loop of the GUI.
     *
     * @param nifty the instance of the Nifty-GUI used to create the objects for the GUI
     * @param org   the original merchant item that contains the actual data
     */
    public NiftyMerchantItem(final Nifty nifty, final MerchantItem org) {
        super(org);

        itemImage = new NiftyImage(nifty.getRenderEngine(),
                new EntitySlickRenderImage(ItemFactory.getInstance().getPrototype(org.getItemId())));
    }

    /**
     * Get the image that is meant to display this merchant item in the list.
     *
     * @return the Nifty image
     */
    @Override
    public NiftyImage getItemImage() {
        return itemImage;
    }
}
